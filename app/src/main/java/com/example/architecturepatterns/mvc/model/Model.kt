package com.example.architecturepatterns.mvc.model

import java.util.Observable

class Model : Observable() {
    private val countList = arrayListOf(0, 0, 0)

    @Throws(IndexOutOfBoundsException::class)
    fun getValueAtIndex(index: Int): String {
        return countList[index].toString()
    }

    @Throws(IndexOutOfBoundsException::class)
    fun setValueAtIndex(index: Int) {
        countList[index] = countList[index].inc()
        setChanged()
        notifyObservers()
    }
}