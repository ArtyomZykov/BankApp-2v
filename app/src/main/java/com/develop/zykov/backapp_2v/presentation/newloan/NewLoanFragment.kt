package com.develop.zykov.backapp_2v.presentation.newloan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.develop.zykov.backapp_2v.R
import com.develop.zykov.backapp_2v.data.loan.remote.dto.LoanConditionsResponse
import com.develop.zykov.backapp_2v.data.loan.remote.dto.LoanRequest
import com.develop.zykov.backapp_2v.domain.login.entity.AuthEntity
import com.develop.zykov.backapp_2v.presentation.start.StartFragment
import com.develop.zykov.backapp_2v.utils.SharedPrefs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_new_loan.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class NewLoanFragment : Fragment() {

    private val viewModel: NewLoanViewModel by viewModels()

    @Inject
    lateinit var sharedPrefs: SharedPrefs
    private var loanTerms: LoanConditionsResponse? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_new_loan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        observe()

    }

    private fun init() {
        viewModel.getLoanConditions()
        upload_button.setOnClickListener {
            uploadLoan()
        }
    }

    private fun uploadLoan() {
        val firstName = firstname_edit_text.text.toString().trim()
        val lastName = lastname_edit_text.text.toString().trim()
        val phone = phone_edit_text.text.toString().trim()
        val amount = amount_edit_text.text.toString().trim()

        if (validate(firstName = firstName, lastName = lastName, phone = phone, amount = amount)) {
            viewModel.createLoan(
                LoanRequest(
                    amount = amount.toInt(),
                    firstName = firstName,
                    lastName = lastName,
                    percent = loanTerms!!.percent,
                    period = loanTerms!!.period,
                    phoneNumber = phone
                )
            )
            sharedPrefs.saveName(firstName)
            sharedPrefs.saveSurname(lastName)
            sharedPrefs.savePhone(phone)
        }
    }

    private fun validate(
        firstName: String,
        lastName: String,
        phone: String,
        amount: String
    ): Boolean {
        resetAllInputError()

        if (firstName.isEmpty()) {
            setFirstNameError(getString(R.string.error_empty_not_valid))
            return false
        }

        if (lastName.isEmpty()) {
            setLastNameError(getString(R.string.error_empty_not_valid))
            return false
        }

        if (phone.isEmpty()) {
            setPhoneError(getString(R.string.error_empty_not_valid))
            return false
        }

        if (amount.toIntOrNull() == null) {
            setAmountError(getString(R.string.error_empty_not_valid))
            return false
        }

        if (amount.toInt() > loanTerms!!.maxAmount || amount.toInt() < 1) {
            setAmountError(getString(R.string.error_amount_not_valid))
            return false
        }

        return true
    }

    private fun resetAllInputError() {
        setFirstNameError(null)
        setLastNameError(null)
        setPhoneError(null)
        setAmountError(null)
    }

    private fun setFirstNameError(e: String?) {
        firstname_input_l.error = e
    }

    private fun setLastNameError(e: String?) {
        lastname_input_l.error = e
    }

    private fun setPhoneError(e: String?) {
        phone_input_l.error = e
    }

    private fun setAmountError(e: String?) {
        amount_input_l.error = e
    }

    private fun observe() {
        viewModel.fragmentState
            .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { state -> handleStateChange(state) }
            .launchIn(lifecycleScope)
    }

    private fun handleStateChange(state: NewLoanFragmentState) {
        when (state) {
            is NewLoanFragmentState.Init -> Unit
            is NewLoanFragmentState.IsLoading -> handleLoading(state.isLoading)

            is NewLoanFragmentState.ErrorGetLoanConditions -> TODO("Not realized")
            is NewLoanFragmentState.SuccessGetLoanConditions -> {
                loanTerms = state.response
                inflateTextViews(state.response)
                scroll_view.visibility = View.VISIBLE
            }

            is NewLoanFragmentState.ErrorCreateLoan -> TODO("Not realized")
            is NewLoanFragmentState.SuccessCreateLoan -> {
                goToStartFragment()
            }
        }
    }

    private fun goToStartFragment() {
        parentFragmentManager.popBackStack()
        parentFragmentManager.commit {
            replace<StartFragment>(R.id.container)
            setReorderingAllowed(true)
            addToBackStack("StartFragment")
        }
    }

    private fun inflateTextViews(response: LoanConditionsResponse) {
        if (sharedPrefs.getName().isNotEmpty()) {
            firstname_edit_text.setText(sharedPrefs.getName())
            lastname_edit_text.setText(sharedPrefs.getSurname())
            phone_edit_text.setText(sharedPrefs.getPhone())
        }
        max_amount_text_view.text = response.maxAmount.toString()
        percent_text_view.text = response.percent.toString()
        period_text_view.text = response.period.toString()
    }

    private fun handleLoading(isLoading: Boolean) {
        if (isLoading) {
            loading_horizontal_progress_bar.isIndeterminate = true
            loading_bar.visibility = View.VISIBLE
        } else {
            loading_horizontal_progress_bar.isIndeterminate = false
            loading_bar.visibility = View.GONE
        }
    }

}