package com.tutorial.learnlinuxpro.presentation.di.module

import android.support.v4.app.Fragment
import com.tutorial.learnlinuxpro.presentation.di.scope.FragmentScope
import dagger.Module
import dagger.Provides

/**
 * Created by kodeartisan on 18/10/17.
 */
@Module
class FragmentModule(private val mFragment: Fragment) {

    @FragmentScope
    @Provides
    fun provideActivity() = mFragment.activity
}