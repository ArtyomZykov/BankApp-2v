package com.develop.zykov.backapp_2v.presentation.newloan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.develop.zykov.backapp_2v.R
import com.develop.zykov.backapp_2v.presentation.login.LoginViewModel
import com.develop.zykov.backapp_2v.utils.SharedPrefs
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NewLoanFragment : Fragment() {

    private val viewModel: NewLoanViewModel by viewModels()
    @Inject
    lateinit var sharedPrefs: SharedPrefs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_new_loan, container, false)
    }

}