package com.example.architecturepatterns.mvvm.view

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.architecturepatterns.R
import com.example.architecturepatterns.databinding.ActivityLoginBinding
import com.example.architecturepatterns.mvvm.viewmodel.LoginViewModel
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        setContentView(binding.root)
        initObservers()
    }

    private fun initObservers() {
        loginViewModel.state.observe(this, Observer { state ->
            when (state) {
                is LoginViewModel.ViewState.Init -> {
                    initListeners()
                }
                is LoginViewModel.ViewState.Loading -> {
                    binding.progressCircular.isVisible = state.showLoader
                }
                is LoginViewModel.ViewState.Success -> alert(R.string.success_login)
                is LoginViewModel.ViewState.Failure -> alert(R.string.failure_login)
            }
        })
    }

    private fun initListeners() {
        with(binding) {
            buttonLogin.setOnClickListener {
                val email: String = edtEmail.text.toString()
                val password: String = edtPassword.text.toString()
                loginViewModel.loginWithEmailPassword(email, password)
            }
        }
    }

    private fun alert(@StringRes resourceId: Int) {
        Snackbar.make(binding.root, getString(resourceId), Snackbar.LENGTH_SHORT).show()
    }
}