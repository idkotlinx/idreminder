package com.kodeartisan.kotlintemplate.presentation.ui.reminder

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.kodeartisan.kotlintemplate.BaseApp
import com.kodeartisan.kotlintemplate.R
import com.kodeartisan.kotlintemplate.data.entity.Reminder
import com.kodeartisan.kotlintemplate.presentation.receiver.AlarmReceiver
import com.kodeartisan.kotlintemplate.presentation.ui.reminder.fragment.add.ReminderAddFragment
import com.kodeartisan.kotlintemplate.presentation.ui.reminder.fragment.list.ReminderFragment
import com.tutorial.learnlinuxpro.presentation.bus.Event
import com.tutorial.learnlinuxpro.presentation.bus.RxBus
import com.tutorial.learnlinuxpro.presentation.extension.activity.addFragment
import com.tutorial.learnlinuxpro.presentation.extension.activity.replaceFragment
import com.tutorial.learnlinuxpro.presentation.extension.toast.toast
import com.tutorial.learnlinuxpro.presentation.ui.base.BaseActivity
import com.tutorial.learnlinuxpro.presentation.ui.base.BaseActivityMvp
import kotlinx.android.synthetic.main.activity_reminder.*
import kotlinx.android.synthetic.main.fragment_reminder_list.*
import javax.inject.Inject

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
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)
        alarmManager.set(AlarmManager.RTC_WAKEUP, SystemClock.elapsedRealtime() + 3000, pendingIntent)

        
    }

    override fun initWidget() {
        super.initWidget()
        initToolbar()
        initFragment()
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
