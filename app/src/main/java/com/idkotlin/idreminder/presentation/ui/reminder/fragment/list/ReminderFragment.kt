package com.idkotlin.idreminder.presentation.ui.reminder.fragment.list

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.idkotlin.idreminder.R
import com.idkotlin.idreminder.data.entity.Reminder
import com.idkotlin.idreminder.presentation.ui.reminder.ReminderAdapter
import com.idkotlin.idreminder.presentation.ui.reminder.fragment.add.ReminderAddFragment
import com.tutorial.learnlinuxpro.presentation.bus.Event
import com.tutorial.learnlinuxpro.presentation.extension.toast.toast
import com.tutorial.learnlinuxpro.presentation.ui.base.BaseFragmentMvp
import kotlinx.android.synthetic.main.fragment_reminder_list.*

/**
 * Created by kodeartisan on 27/11/17.
 */
class ReminderFragment : BaseFragmentMvp<ReminderFragmentContract.Presenter<ReminderFragmentContract.View>, ReminderFragmentContract.View>(), ReminderFragmentContract.View {


    private val TAG = ReminderFragment::class.java.name

    private lateinit var mReminderAdapter: ReminderAdapter
    private var mReminders = mutableListOf<Reminder>()


    override fun getLayoutId(): Int = R.layout.fragment_reminder_list

    override fun initDependencies() = getFragmentComponent().inject(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun initVariables(savedInstanceState: Bundle?) {
        super.initVariables(savedInstanceState)
        mPresenter.start()

    }

    override fun initWidget() {
        super.initWidget()
        initRecyclerview()
    }

    private fun initRecyclerview() {
        mReminderAdapter = ReminderAdapter(context)
        mReminderAdapter.apply {
            setData(mReminders)
            setItemClickListener { view, i ->  toast("Clicked") }
        }

        recyclerview.apply {
            adapter = mReminderAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.main, menu)
    }



    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.action_add -> {
                eventBus.send(Event.ChangeFragment(ReminderAddFragment()))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showError(msg: String) {

    }

    override fun complete() {

    }

    override fun onGetReminders(reminders: List<Reminder>) {
        this.mReminders.clear()
        this.mReminders.addAll(reminders)
        mReminderAdapter.setData(this.mReminders)
    }


}