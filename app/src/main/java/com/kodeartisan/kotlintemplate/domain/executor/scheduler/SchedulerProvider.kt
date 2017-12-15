package com.kodeartisan.kotlintemplate.domain.executor.scheduler

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by kodeartisan on 12/10/17.
 */
class SchedulerProvider : BaseSchedulerProvider {

    override fun computation(): Scheduler = Schedulers.computation()

    override fun multi(): Scheduler = Schedulers.newThread()

    override fun io(): Scheduler = Schedulers.io()

    override fun ui(): Scheduler = AndroidSchedulers.mainThread()
}