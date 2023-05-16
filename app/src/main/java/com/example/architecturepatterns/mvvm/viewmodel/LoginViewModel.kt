package com.example.architecturepatterns.mvvm.viewmodel

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.architecturepatterns.mvvm.model.LoginModel

class LoginViewModel : ViewModel() {

    private var _state: MutableLiveData<ViewState> = MutableLiveData(ViewState.Init)
    val state: LiveData<ViewState> = _state

    private val loginModel = LoginModel()

    fun loginWithEmailPassword(email: String, password: String) {
        setLoginModel(email, password)
        _state.value = ViewState.Loading(showLoader = true)
        _state.value = if (isInputValid()) {
            ViewState.Success
        } else {
            ViewState.Failure
        }
        _state.value = ViewState.Loading(showLoader = false)
    }

    private fun setLoginModel(emailInput: String?, passwordInput: String?) {
        loginModel.apply {
            email = emailInput
            password = passwordInput
        }
    }

    private fun isInputValid(): Boolean {
        return isEmailValid() && isPasswordValid()
    }

    private fun isEmailValid(): Boolean {
        return loginModel.email?.let { email ->
            email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
        } ?: false
    }

    private fun isPasswordValid(): Boolean {
        return loginModel.password?.let { password ->
            password.length > 8
        } ?: false
    }

    sealed class ViewState {
        object Init : ViewState()
        object Success : ViewState()
        object Failure : ViewState()
        class Loading(val showLoader: Boolean) : ViewState()
    }
}