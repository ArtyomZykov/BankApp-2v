package com.develop.zykov.backapp_2v.presentation.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.develop.zykov.backapp_2v.R
import com.develop.zykov.backapp_2v.data.loan.remote.dto.LoanResponse
import com.develop.zykov.backapp_2v.presentation.info.InfoFragment
import com.develop.zykov.backapp_2v.presentation.login.LoginFragment
import com.develop.zykov.backapp_2v.presentation.newloan.NewLoanFragment
import com.develop.zykov.backapp_2v.utils.SharedPrefs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_start.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@AndroidEntryPoint
class StartFragment : Fragment() {

    private val viewModel: StartViewModel by viewModels()

    @Inject
    lateinit var sharedPrefs: SharedPrefs

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        observe()
        viewModel.getUserLoans()
        toolbarListener()

    }

    private fun init() {
        empty_list_text.visibility = View.GONE
        create_fab.setOnClickListener {
            parentFragmentManager.commit {
                replace<NewLoanFragment>(R.id.container)
                setReorderingAllowed(true)
                addToBackStack("NewLoanFragment")
            }
        }
    }

    private fun observe() {
        viewModel.fragmentState
            .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { state -> handleStateChange(state) }
            .launchIn(lifecycleScope)
    }

    private fun handleStateChange(state: StartFragmentState) {
        when (state) {
            is StartFragmentState.Init -> Unit
            is StartFragmentState.IsLoading -> handleLoading(state.isLoading)
            is StartFragmentState.ErrorGetUserLoans -> handleErrorLogin(state.code)
            is StartFragmentState.SuccessGetUserLoans -> {
                if (state.response.isEmpty()) {
                    empty_list_text.visibility = View.VISIBLE
                } else {
                    empty_list_text.visibility = View.GONE
                    buildRecycler(state.response)
                }

            }
        }
    }

    private fun handleLoading(isLoading: Boolean) {
        if (!isLoading) {
            loading_progress_bar.visibility = View.GONE
        }
    }


    private fun handleErrorLogin(code: Int) {
        when (code) {
            400 -> {

            }
            else -> {

            }
        }
    }

    private fun buildRecycler(dataList: List<LoanResponse>) {

        loan_recycler_view?.apply {
            val loanAdapter = LoanAdapter(onClick = {

                val bundle = Bundle()
                val nextFragment = InfoFragment()
                bundle.putInt("id", it.id)
                nextFragment.arguments = bundle

                parentFragmentManager.commit {
                    replace(R.id.container,  nextFragment)
                    setReorderingAllowed(true)
                    addToBackStack("InfoFragment")
                }

            })
            loanAdapter.dataList = dataList
            adapter = loanAdapter
        }

    }

    private fun toolbarListener() {
        top_bar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_sign_out -> {
                    signOut()
                    true
                }
                else -> {
                    super.onOptionsItemSelected(it)
                }
            }
        }
    }

    private fun signOut() {
        goToLoginFragment()
    }

    private fun goToLoginFragment() {
        parentFragmentManager.popBackStack()
        parentFragmentManager.commit {
            replace<LoginFragment>(R.id.container)
            setReorderingAllowed(true)
            addToBackStack("LoginFragment")
        }
    }

}