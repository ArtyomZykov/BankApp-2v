package com.develop.zykov.backapp_2v.presentation.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.develop.zykov.backapp_2v.R
import com.develop.zykov.backapp_2v.domain.login.entity.AuthEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
        login_button.setOnClickListener { login() }
    }

    private fun observe() {
        viewModel.nState
            .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { state -> handleStateChange(state) }
            .launchIn(lifecycleScope)
    }

    private fun handleStateChange(state: LoginFragmentState) {
        when (state) {
            is LoginFragmentState.Init -> Unit
            is LoginFragmentState.IsLoading -> handleLoading(state.isLoading)
        }
    }

    private fun handleLoading(isLoading: Boolean) {
        login_button.isEnabled = !isLoading
        registration_button.isEnabled = !isLoading
        loading_progress_bar.isIndeterminate = isLoading
        if (!isLoading) {
            loading_progress_bar.progress = 0
        }
    }

    private fun login() {
        val login = login_edit_text.text.toString().trim()
        val password = password_edit_text.text.toString().trim()
        if (validate(login, password)) {
            val loginRequest = AuthEntity(login = login, password = password)
            viewModel.login(loginRequest)
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
        login_input.error = e
    }

    private fun setPasswordError(e: String?) {
        password_input.error = e
    }

}