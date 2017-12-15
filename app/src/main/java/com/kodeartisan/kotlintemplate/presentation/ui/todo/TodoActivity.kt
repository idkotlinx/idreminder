package com.kodeartisan.kotlintemplate.presentation.ui.todo

import android.os.Bundle
import com.kodeartisan.kotlintemplate.BaseApp
import com.kodeartisan.kotlintemplate.R
import com.kodeartisan.kotlintemplate.data.entity.Todo
import com.tutorial.learnlinuxpro.presentation.ui.base.BaseActivityMvp

class TodoActivity : BaseActivityMvp<TodoContract.Presenter<TodoContract.View>, TodoContract.View>(), TodoContract.View {

    override fun initDependencies() {
        super.initDependencies()
        getActivityComponent().inject(this)
    }

    override fun initVariables(savedInstanceState: Bundle?) {
        super.initVariables(savedInstanceState)
        mPresenter.getData()
    }

    override fun getLayoutId(): Int = R.layout.activity_example

    override fun showError(msg: String) {
    }

    override fun complete() {
    }

    override fun onGetData(examples: List<Todo>) {
    }


}
