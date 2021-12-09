package com.develop.zykov.backapp_2v.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.develop.zykov.backapp_2v.R
import com.develop.zykov.backapp_2v.data.login.dto.AuthRequest
import com.develop.zykov.backapp_2v.presentation.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.login(AuthRequest("12311", "12311"))
    }


}