package com.kodeartisan.kotlintemplate.presentation.ui.todo

import com.kodeartisan.kotlintemplate.data.entity.Todo
import com.tutorial.learnlinuxpro.presentation.ui.base.BaseContract

/**
 * Created by kodeartisan on 26/10/17.
 */
interface TodoContract {

    interface View : BaseContract.BaseView {
        fun onGetData(examples: List<Todo>)
    }

    interface Presenter<T : BaseContract.BaseView>: BaseContract.BasePresenter<T> {

        fun getData()
    }
}