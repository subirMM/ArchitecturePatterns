package com.example.architecturepatterns.mvp.view

import android.app.Activity
import android.os.Bundle
import androidx.core.view.isVisible
import com.example.architecturepatterns.databinding.ActivityLuckyNumberBinding
import com.example.architecturepatterns.mvp.model.Model
import com.example.architecturepatterns.mvp.presenter.Contract
import com.example.architecturepatterns.mvp.presenter.Presenter

class LuckyNumberActivity : Activity(), Contract.View {
    private lateinit var binding: ActivityLuckyNumberBinding
    private lateinit var presenter: Presenter

    override fun showProgress() {
        binding.progressCircular.isVisible = true
    }

    override fun hideProgress() {
        binding.progressCircular.isVisible = false
    }

    override fun setNewNumber(number: Int) {
        binding.tvNumber.text = number.toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLuckyNumberBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setPresenter()
        setListener()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    private fun setPresenter() {
        presenter = Presenter(view = this, model = Model())
    }

    private fun setListener() {
        binding.buttonFirst.setOnClickListener {
            presenter.onButtonClick()
        }
    }
}