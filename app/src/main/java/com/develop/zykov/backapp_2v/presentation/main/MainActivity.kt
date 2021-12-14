package com.develop.zykov.backapp_2v.presentation.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.develop.zykov.backapp_2v.R
import com.develop.zykov.backapp_2v.presentation.login.LoginFragment
import com.develop.zykov.backapp_2v.presentation.start.StartFragment
import com.develop.zykov.backapp_2v.utils.SharedPrefs
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    @Inject
    lateinit var sharedPrefs: SharedPrefs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (sharedPrefs.getToken().isEmpty()) {
            supportFragmentManager.commit {
                replace<LoginFragment>(R.id.container)
                setReorderingAllowed(true)
                addToBackStack("LoginFragment")
            }
        } else {
            supportFragmentManager.commit {
                replace<StartFragment>(R.id.container)
                setReorderingAllowed(true)
                addToBackStack("StartFragmentZ")
            }
        }

    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStack()
        } else {
            finish()
        }
    }

}