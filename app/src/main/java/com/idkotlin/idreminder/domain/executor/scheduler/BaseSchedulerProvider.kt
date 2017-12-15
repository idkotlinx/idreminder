package com.idkotlin.idreminder.domain.executor.scheduler

import android.support.annotation.NonNull
import io.reactivex.Scheduler


/**
 * Created by kodeartisan on 12/10/17.
 */
interface BaseSchedulerProvider {

    @NonNull
    fun computation() : Scheduler

    @NonNull
    fun multi() : Scheduler

    @NonNull
    fun io() : Scheduler

    @NonNull
    fun ui() : Scheduler



}