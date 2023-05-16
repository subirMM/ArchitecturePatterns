package com.example.architecturepatterns.mvp.presenter

interface Contract {
    interface View {
        fun showProgress()
        fun hideProgress()
        fun setNewNumber(number: Int)
    }

    interface Model {
        interface OnOperationFinishedListener {
            fun onFinish(number: Int)
        }

        fun performNextOperation(onFinishedListener: OnOperationFinishedListener)
    }

    interface Presenter {
        fun onButtonClick()
        fun onDestroy()
    }
}