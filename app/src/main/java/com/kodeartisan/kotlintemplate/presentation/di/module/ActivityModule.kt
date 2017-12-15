package com.tutorial.learnlinuxpro.presentation.di.module

import android.support.v7.app.AppCompatActivity
import com.tutorial.learnlinuxpro.presentation.di.scope.ActivityScope
import dagger.Module
import dagger.Provides

/**
 * Created by kodeartisan on 12/10/17.
 */
@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @Provides
    @ActivityScope
    fun provideActivity() = activity

    @Provides
    @ActivityScope
    fun provideActivityContext() = activity.baseContext
}