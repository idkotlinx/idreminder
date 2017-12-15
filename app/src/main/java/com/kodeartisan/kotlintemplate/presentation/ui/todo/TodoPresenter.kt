package com.kodeartisan.kotlintemplate.presentation.ui.todo

import android.util.Log
import com.kodeartisan.kotlintemplate.domain.executor.scheduler.BaseSchedulerProvider
import com.kodeartisan.kotlintemplate.domain.repository.ITodoRepository
import com.tutorial.learnlinuxpro.presentation.ui.base.RxPresenter
import javax.inject.Inject

/**
 * Created by kodeartisan on 26/10/17.
 */
class TodoPresenter @Inject constructor(private val schedulerProvider: BaseSchedulerProvider, private val iExampleRepository: ITodoRepository): RxPresenter<TodoContract.View>(), TodoContract.Presenter<TodoContract.View>{

    private val TAG = TodoPresenter::class.java.name

    override fun getData() {
        addSubscribe(iExampleRepository.getTodos()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({ Log.d(TAG, it.size.toString())}, {}))

    }
}