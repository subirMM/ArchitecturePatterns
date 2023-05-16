package com.example.architecturepatterns.mvp.model

import com.example.architecturepatterns.mvp.presenter.Contract
import kotlin.random.Random

class Model : Contract.Model {
    private val numberList = arrayListOf<Int>()

    init {
        List(MAX_CAPACITY) { index ->
            numberList.add(index)
        }
    }

    override fun performNextOperation(onFinishedListener: Contract.Model.OnOperationFinishedListener) {
        onFinishedListener.onFinish(getRandomNumber())
    }

    private fun getRandomNumber(): Int {
        val index = Random.nextInt(until = numberList.size)
        return numberList[index]
    }

    companion object {
        const val MAX_CAPACITY = 100
    }
}