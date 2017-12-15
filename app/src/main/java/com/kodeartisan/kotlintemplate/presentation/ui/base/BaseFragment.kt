package com.tutorial.learnlinuxpro.presentation.ui.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kodeartisan.kotlintemplate.BaseApp
import com.tutorial.learnlinuxpro.presentation.bus.RxBus
import com.tutorial.learnlinuxpro.presentation.di.component.DaggerFragmentComponent
import com.tutorial.learnlinuxpro.presentation.di.component.FragmentComponent
import javax.inject.Inject

/**
 * Created by kodeartisan on 18/10/17.
 */
abstract class BaseFragment: Fragment() {

    @Inject
    lateinit var eventBus: RxBus

    protected lateinit var mInflater: LayoutInflater
    protected lateinit var mActivity: AppCompatActivity
    protected lateinit var mRootView: View

    abstract fun getLayoutId(): Int
    open fun initDependencies(){}
    open fun initWidget(){}
    open fun initVariables(savedInstanceState: Bundle?){}
    open fun initPresenter(){}
    open fun initListener(){}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDependencies()
        initPresenter()
        initVariables(savedInstanceState)
        initWidget()
        initListener()

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mRootView = inflater!!.inflate(getLayoutId(), container, false)
        mActivity = activity as AppCompatActivity
        mInflater = inflater

        return mRootView
    }

    private fun getSupportActivity(): FragmentActivity = super.getActivity()

    protected fun getFragmentComponent(): FragmentComponent = DaggerFragmentComponent
            .builder()
            .applicationComponent(BaseApp.appComponent)
            .build()


}