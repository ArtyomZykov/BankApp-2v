package com.develop.zykov.backapp_2v.presentation.main

import androidx.lifecycle.ViewModel
import com.develop.zykov.backapp_2v.utils.SharedPrefs
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var sharedPrefs: SharedPrefs

}