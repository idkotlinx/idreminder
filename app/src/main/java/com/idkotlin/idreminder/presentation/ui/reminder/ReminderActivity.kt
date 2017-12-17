package com.idkotlin.idreminder.presentation.ui.reminder

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.support.v4.app.Fragment
import com.idkotlin.idreminder.R
import com.idkotlin.idreminder.presentation.receiver.AlarmReceiver
import com.idkotlin.idreminder.presentation.ui.reminder.fragment.add.ReminderAddFragment
import com.idkotlin.idreminder.presentation.ui.reminder.fragment.list.ReminderFragment
import com.tutorial.learnlinuxpro.presentation.bus.Event
import com.tutorial.learnlinuxpro.presentation.extension.activity.addFragment
import com.tutorial.learnlinuxpro.presentation.extension.activity.replaceFragment
import com.tutorial.learnlinuxpro.presentation.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_reminder.*

class ReminderActivity : BaseActivity(){

    private val TAG = ReminderActivity::class.java.name

    private lateinit var currentFragment: Fragment
    private val reminderFragment = ReminderFragment()
    private val reminderAddFragment = ReminderAddFragment()

    override fun getLayoutId(): Int = R.layout.activity_reminder

    override fun initDependencies() {
        super.initDependencies()
        getActivityComponent().inject(this)
    }

    override fun initVariables(savedInstanceState: Bundle?) {
        super.initVariables(savedInstanceState)
        if(savedInstanceState == null) initFragment()

    }

    override fun initWidget() {
        super.initWidget()
        initToolbar()

    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = getString(R.string.app_name)
            setDisplayShowTitleEnabled(true)
        }
    }

    private fun initFragment() {
        val reminderFragment = ReminderFragment()
        currentFragment = reminderFragment
        addFragment(reminderFragment, R.id.container)
    }




    override fun handleEvent(event: Any) {

        when(event) {
            is Event.ChangeFragment-> {
                replaceFragment(event.fragment, R.id.container, "")
            }
            is Event.BackPressed -> {
                onBackPressed()
            }
        }
    }
}
