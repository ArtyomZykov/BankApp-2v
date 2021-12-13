package com.develop.zykov.backapp_2v.presentation.info

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
import com.develop.zykov.backapp_2v.data.loan.remote.dto.LoanResponse
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_info.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.text.DateFormat
import java.text.SimpleDateFormat

@AndroidEntryPoint
class InfoFragment : Fragment() {

    private val viewModel:InfoViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
        getData()

    }

    private fun getData() {
        val id : Int = arguments?.getInt("id", -1)!!
        viewModel.getData(id)
    }


    private fun observe() {
        viewModel.fragmentState
            .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { state -> handleStateChange(state) }
            .launchIn(lifecycleScope)
    }

    private fun handleStateChange(state: InfoFragmentState) {
        when (state) {
            is InfoFragmentState.Init -> Unit
            is InfoFragmentState.IsLoading -> handleLoading(state.isLoading)
            is InfoFragmentState.ErrorGetData -> handleErrorGetData(state.code)
            is InfoFragmentState.SuccessGetData -> {
                inflateFragmentData(state.response)
                visibilityFragmentView()
            }
        }
    }


    private fun inflateFragmentData(response: LoanResponse) {
        max_amount_text_view.text = response.firstName
        surname_text_view.text = response.lastName
        number_text_view.text = response.phoneNumber
        val pattern = "MM.dd.yyyy HH:mm"
        val df: DateFormat = SimpleDateFormat(pattern)
        date_text_view.text = df.format(response.date)
        status_text_view.text = response.state
        amount_text_view.text = response.amount
        percent_text_view.text = response.percent
        period_text_view.text = response.period
    }

    private fun handleLoading(isLoading: Boolean) {
        if (isLoading) {
            loading_bar.visibility = View.VISIBLE
        } else {
            loading_bar.visibility = View.GONE
        }
    }

    private fun inflateFragmentView(response: LoanResponse) {
        max_amount_text_view.text = response.firstName
        surname_text_view.text = response.lastName
        number_text_view.text = response.phoneNumber
        val pattern = "MM.dd.yyyy HH:mm"
        val df: DateFormat = SimpleDateFormat(pattern)
        date_text_view.text = df.format(response.date)
        status_text_view.text = response.state
        amount_text_view.text = response.amount
        percent_text_view.text = response.percent
        period_text_view.text = response.period
    }

    private fun visibilityFragmentView() {
        const_name_text_view.visibility = View.VISIBLE
        const_max_amount_text_view.visibility = View.VISIBLE
        const_number_text_view.visibility = View.VISIBLE
        const_date_text_view.visibility = View.VISIBLE
        const_status_text_view.visibility = View.VISIBLE
        const_amount_text_view.visibility = View.VISIBLE
        const_percent_text_view.visibility = View.VISIBLE
        const_period_text_view.visibility = View.VISIBLE
    }

    private fun handleErrorGetData(code: Int){

        when (code) {
            400 -> {

            }
            else -> {

            }
        }

    }
}