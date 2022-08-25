package com.adyen.android.assignment.base

import androidx.lifecycle.ViewModel
import com.adyen.android.assignment.network.ErrorManager

abstract class BaseViewModel() : ViewModel() {
    fun getStatusCode(throwable: Throwable): Int {
        return ErrorManager.getCode(throwable = throwable)
    } // fun of getStatusCode
}