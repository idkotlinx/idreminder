package com.tutorial.learnlinuxpro.presentation.ui.base

import android.os.Bundle
import android.support.annotation.NonNull
import android.support.v7.app.AppCompatActivity
import com.idkotlin.idreminder.BaseApp
import io.reactivex.disposables.CompositeDisposable;
import com.tutorial.learnlinuxpro.presentation.di.component.ActivityComponent
import com.tutorial.learnlinuxpro.presentation.di.component.DaggerActivityComponent
import com.tutorial.learnlinuxpro.presentation.bus.RxBus
import javax.inject.Inject



/**
 * Created by kodeartisan on 12/10/17.
 */
abstract class BaseActivity : AppCompatActivity() {

    protected val disposables = CompositeDisposable()

    @Inject
    lateinit var eventBus: RxBus

    abstract fun getLayoutId(): Int
    open fun initDependencies(){}
    open fun initWidget(){}
    open fun initVariables(savedInstanceState: Bundle?){}
    open fun initPresenter(){}
    open fun initListener(){}
    open fun handleEvent(@NonNull event: Any) {}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        getActivityComponent()
        initDependencies()
        initPresenter()
        initWidget()
        initListener()
        initVariables(savedInstanceState)

    }


    fun getActivityComponent(): ActivityComponent {
         return DaggerActivityComponent.builder()
                 .applicationComponent(BaseApp.appComponent)
                 .build()
    }

    override fun onResume() {
        super.onResume()
        disposables.add(eventBus.asFlowable()
                .subscribe(this::processEvent))
    }

    override fun onStop() {
        super.onStop()
        disposables.clear()
    }

    override fun onPause() {
        super.onPause()
        disposables.clear()
    }

    private fun processEvent(ob: Any) {
        if(ob != null) handleEvent(ob)
    }

}