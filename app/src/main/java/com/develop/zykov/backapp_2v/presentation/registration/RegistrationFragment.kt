package com.develop.zykov.backapp_2v.presentation.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.develop.zykov.backapp_2v.R
import com.develop.zykov.backapp_2v.domain.registration.entity.RegistrationEntity
import com.develop.zykov.backapp_2v.utils.SharedPrefs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_registration.*
import kotlinx.android.synthetic.main.fragment_registration.loading_progress_bar
import kotlinx.android.synthetic.main.fragment_registration.login_edit_text
import kotlinx.android.synthetic.main.fragment_registration.password_edit_text
import kotlinx.android.synthetic.main.fragment_registration.registration_button
import kotlinx.android.synthetic.main.fragment_registration.response_text_view
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class RegistrationFragment : Fragment() {


    private val viewModel: RegistrationViewModel by viewModels()
    @Inject
    lateinit var sharedPrefs: SharedPrefs

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
        back_button.setOnClickListener { backPressed() }
        registration_button.setOnClickListener { registration() }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_registration, container, false)
    }



    private fun observe() {
        viewModel.fragmentState
            .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { state -> handleStateChange(state) }
            .launchIn(lifecycleScope)
    }

    private fun registration() {
        val login = login_edit_text.text.toString().trim()
        val password = password_edit_text.text.toString().trim()
        if (validate(login, password)) {
            val registrationRequest = RegistrationEntity(login = login, password = password)
            viewModel.registration(registrationRequest)
        }
    }

    private fun validate(login: String, password: String): Boolean {
        resetAllInputError()
        if (login.isEmpty()) {
            setLoginError(getString(R.string.error_login_not_valid))
            return false
        }

        if (password.length < 4) {
            setPasswordError(getString(R.string.error_password_not_valid))
            return false
        }
        return true
    }

    private fun resetAllInputError() {
        setLoginError(null)
        setPasswordError(null)
    }

    private fun setLoginError(e: String?) {
        login_input_r.error = e
    }

    private fun setPasswordError(e: String?) {
        password_input_r.error = e
    }


    private fun handleStateChange(state: RegistrationFragmentState) {
        when (state) {
            is RegistrationFragmentState.Init -> Unit
            is RegistrationFragmentState.IsLoading -> handleLoading(state.isLoading)
            is RegistrationFragmentState.ErrorLogin -> handleErrorLogin(state.code)
            is RegistrationFragmentState.SuccessLogin -> {
                // Make toast
                backPressed()
            }
        }
    }

    private fun handleErrorLogin(code: Int){

        when (code) {
            400 -> {
                response_text_view.text = context?.getString(R.string.incorrect_registration)
                response_text_view.visibility = View.VISIBLE
            }
            else -> {
                response_text_view.text = context?.getString(R.string.error)
                response_text_view.visibility = View.VISIBLE
            }
        }

    }

    private fun handleLoading(isLoading: Boolean) {
        registration_button.isEnabled = !isLoading
        loading_progress_bar.isIndeterminate = isLoading
        response_text_view.visibility = View.GONE
        if (!isLoading) {
            loading_progress_bar.progress = 0
        }
    }

    private fun backPressed() {
        parentFragmentManager.popBackStack()
    }




}