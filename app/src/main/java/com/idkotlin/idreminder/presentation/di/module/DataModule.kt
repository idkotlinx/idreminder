package com.tutorial.learnlinuxpro.presentation.di.module

import com.idkotlin.idreminder.data.repository.ReminderImpl
import com.idkotlin.idreminder.domain.repository.IReminderRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by kodeartisan on 12/10/17.
 */
@Module
class DataModule {

    @Singleton
    @Provides
    fun reminderRepository(reminderImpl: ReminderImpl): IReminderRepository = reminderImpl
}