package com.sementa.theoportfolio.network.managers

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TokenManager {
    companion object {
        private val _token: MutableStateFlow<String> = MutableStateFlow("")
        val token: StateFlow<String> get() = _token

        fun updateToken(newToken: String) {
            _token.value = newToken
        }
    }

}