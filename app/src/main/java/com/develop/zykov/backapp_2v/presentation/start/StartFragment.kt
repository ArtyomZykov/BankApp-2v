package com.develop.zykov.backapp_2v.presentation.start

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.develop.zykov.backapp_2v.R
import com.develop.zykov.backapp_2v.presentation.login.LoginFragment
import com.develop.zykov.backapp_2v.utils.SharedPrefs
import kotlinx.android.synthetic.main.fragment_start.*
import java.sql.Time
import javax.inject.Inject


class StartFragment : Fragment() {

    @Inject
    lateinit var sharedPrefs: SharedPrefs


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_start, container, false)
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