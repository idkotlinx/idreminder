package com.tutorial.learnlinuxpro.presentation.ui.base

import android.os.Bundle
import com.tutorial.learnlinuxpro.presentation.ui.base.BaseContract.BasePresenter
import com.tutorial.learnlinuxpro.presentation.ui.base.BaseContract.BaseView
import javax.inject.Inject

/**
 * Created by kodeartisan on 13/10/17.
 */
abstract class BaseActivityMvp<P: BasePresenter<V>, in V: BaseView> : BaseActivity(), BaseView {

    @Inject lateinit var mPresenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initPresenter() {
        super.initPresenter()
        mPresenter.attachView(view = this as V)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }
}