package com.example.architecturepatterns.mvp.presenter

class Presenter(
    private var view: Contract.View?,
    private val model: Contract.Model
) : Contract.Presenter, Contract.Model.OnOperationFinishedListener {

    override fun onButtonClick() {
        view?.showProgress()
        model.performNextOperation(this)
    }

    override fun onDestroy() {
        view = null
    }

    override fun onFinish(number: Int) {
        view?.setNewNumber(number)
        view?.hideProgress()
    }
}