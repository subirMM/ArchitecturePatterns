package com.example.architecturepatterns.mvc.view_controller

import android.app.Activity
import android.os.Bundle
import android.view.View
import com.example.architecturepatterns.databinding.ActivityCounterBinding
import com.example.architecturepatterns.mvc.model.Model
import java.util.*

class CounterActivity : Activity(), Observer, View.OnClickListener {

    private lateinit var model: Model
    private lateinit var binding: ActivityCounterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCounterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initObserver()
        initListener()
    }

    private fun initObserver() {
        model = Model()
        model.addObserver(this)
    }

    private fun initListener() {
        binding.buttonFirst.setOnClickListener(this)
        binding.buttonSecond.setOnClickListener(this)
        binding.buttonThird.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            binding.buttonFirst.id -> model.setValueAtIndex(0)
            binding.buttonSecond.id -> model.setValueAtIndex(1)
            binding.buttonThird.id -> model.setValueAtIndex(2)
        }
    }

    override fun update(p0: Observable?, p1: Any?) {
        binding.buttonFirst.text = model.getValueAtIndex(0)
        binding.buttonSecond.text = model.getValueAtIndex(1)
        binding.buttonThird.text = model.getValueAtIndex(2)
    }
}