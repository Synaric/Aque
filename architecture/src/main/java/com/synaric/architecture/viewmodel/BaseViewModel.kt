package com.synaric.architecture.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.synaric.architecture.ArtConstant
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout

open class BaseViewModel : ViewModel() {

    fun launchUI(block: suspend CoroutineScope.() -> Unit) = viewModelScope.launch {
        try {
            withTimeout(ArtConstant.DEFAULT_TIMEOUT * 1000) {
                block()
            }
        } catch (e: Exception) {

        } finally {

        }
    }
}