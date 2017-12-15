package com.tutorial.learnlinuxpro.presentation.ui.base

/**
 * Created by kodeartisan on 12/10/17.
 */
interface BaseContract {

    interface BaseView {

        fun showError(msg: String)

        fun complete()


    }

    interface BasePresenter<in T : BaseContract.BaseView>  {


        fun attachView(view: T)


        fun detachView()
    }

}