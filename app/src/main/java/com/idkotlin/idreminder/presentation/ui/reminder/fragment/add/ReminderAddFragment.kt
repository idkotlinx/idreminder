package com.idkotlin.idreminder.presentation.ui.reminder.fragment.add

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.InputType
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.*
import com.idkotlin.idreminder.R
import com.idkotlin.idreminder.data.entity.Reminder
import com.idkotlin.idreminder.presentation.receiver.AlarmReceiver
import com.idkotlin.idreminder.presentation.ui.reminder.fragment.list.ReminderFragment
import com.idkotlin.idreminder.presentation.util.ReminderUtil
import com.tutorial.learnlinuxpro.presentation.bus.Event
import com.tutorial.learnlinuxpro.presentation.ui.base.BaseFragmentMvp
import kotlinx.android.synthetic.main.fragment_reminder_add.*
import java.util.*

/**
 * Created by kodeartisan on 27/11/17.
 */
class ReminderAddFragment: BaseFragmentMvp<ReminderAddFragmentContract.Presenter<ReminderAddFragmentContract.View>, ReminderAddFragmentContract.View>(), ReminderAddFragmentContract.View {

    public val TAG = ReminderFragment::class.java.name

    private var mTitle = ""
    private var mDate = ""
    private var mTime = ""
    private var mActive = true
    private var mRepeat = true
    private var mRepeatNo = 1
    private var mRepeatType = "Hour"
    private var mRepeatTime: Long = 0

    private val mCalendar = Calendar.getInstance()
    private var mHour = mCalendar.get(Calendar.HOUR_OF_DAY)
    private var mMinute = mCalendar.get(Calendar.MINUTE)
    private var mYear = mCalendar.get(Calendar.YEAR)
    private var mMonth = mCalendar.get(Calendar.MONTH) + 1
    private var mDay = mCalendar.get(Calendar.DATE)


    override fun getLayoutId(): Int = R.layout.fragment_reminder_add

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun initDependencies() {
        super.initDependencies()
        getFragmentComponent().inject(this)
    }

    override fun initWidget() {
        super.initWidget()


    }

    override fun initVariables(savedInstanceState: Bundle?) {
        super.initVariables(savedInstanceState)
        mDate = "${mCalendar.get(Calendar.DATE)}/${mCalendar.get(Calendar.MONTH) + 1}/${mCalendar.get(Calendar.YEAR)}"
        mTime = "${mCalendar.get(Calendar.HOUR_OF_DAY)}:${mCalendar.get(Calendar.MINUTE)}"

        set_date.text = mDate
        set_time.text = mTime
        set_repeat_no.text = mRepeatNo.toString()
        set_repeat_type.text = mRepeatType
        set_repeat.text = "Every ${mRepeatNo} ${mRepeatType} (s)"
    }

    override fun initListener() {
        super.initListener()
        date.setOnClickListener { showDatePicker() }
        time.setOnClickListener { showTimePicker() }
        RepeatNo.setOnClickListener { showRepeatNoPicker() }
        RepeatType.setOnClickListener { showRepeatTypePicker() }
        repeat_switch.setOnCheckedChangeListener({ compoundButton: CompoundButton, isChecked: Boolean ->
            run {

                mRepeat = isChecked

                set_repeat.text = if(isChecked) "Every ${mRepeatNo} ${mRepeatType} (s)"
                                   else "Off"
            }
        })
        btn_active.setOnClickListener { setActiveState() }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.add, menu)
    }



    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.action_insert -> {
                insertReminder()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showTimePicker() {
        (TimePickerDialog(context,
                { timePicker: TimePicker, hourOfDay: Int, minute: Int -> run{
                    mHour = hourOfDay
                    mMinute = minute
                    mTime = if(mMinute < 10) "${hourOfDay}:0${minute}" else "${hourOfDay}:${minute}"
                    set_time.text = mTime
                } },
                mCalendar.get(Calendar.HOUR_OF_DAY),
                mCalendar.get(Calendar.MINUTE),
                true)).show()

    }

    private fun showDatePicker() {

        (DatePickerDialog(
                context,
                { _: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int -> run{

                    mDay = dayOfMonth
                    mMonth = monthOfYear
                    mYear = year
                    mDate = "${dayOfMonth}/${monthOfYear + 1}/${year}"

                    set_date.text = mDate
                }},
                mCalendar.get(Calendar.YEAR),
                mCalendar.get(Calendar.MONTH),
                mCalendar.get(Calendar.DAY_OF_MONTH)
        )).show()
    }

    private fun showRepeatNoPicker() {

        val input = EditText(context).apply {
            inputType = InputType.TYPE_CLASS_NUMBER
        }

        (AlertDialog.Builder(context).apply {
            setTitle("Enter a Number")
            setView(input)
            setPositiveButton("Ok", { dialogInterface, which->
                run {
                    mRepeatNo = if (input.text.toString().length == 0) 1 else input.text.toString().toInt()
                    set_repeat_no.text = mRepeatNo.toString()
                    set_repeat.text =  "Every ${mRepeatNo} ${mRepeatType} (s)"
                }
            })
            setNegativeButton("Cancel", {dialog, which -> {

            }})

        }).show()
    }

    private fun showRepeatTypePicker() {
        val repeatTypes = arrayOf("Minute", "Hour", "Day", "Week", "Month")

        (AlertDialog.Builder(context).apply {
            setTitle("Select Type")
            setItems(repeatTypes, { _, item ->
                run {
                    mRepeatType = repeatTypes[item]
                    set_repeat_type.text = mRepeatType
                    set_repeat.text =  "Every ${mRepeatNo} ${mRepeatType} (s)"
                }
            })
        }).show()
    }

    override fun showError(msg: String) {

    }

    override fun complete() {
    }

    private fun setActiveState() {
        mActive = !mActive
        val activeImageState = if(mActive) R.drawable.ic_notifications_active else R.drawable.ic_notifications_off

        btn_active.setImageResource(activeImageState)
    }

    private fun insertReminder() {
        mTitle = reminder_title.text.toString()
        val reminder = Reminder(mTitle, mDate, mTime, mRepeat, mRepeatNo, mRepeatType, mActive)
        mPresenter.insert(reminder)

    }

    override fun onInsertData(id: Long) {
        Log.d(TAG, mCalendar.get(Calendar.DAY_OF_MONTH).toString())
        mCalendar.apply {
           set(Calendar.MONTH, --mMonth)
           set(Calendar.YEAR, mYear)
           set(Calendar.DAY_OF_MONTH, mDay)
           set(Calendar.HOUR_OF_DAY, mHour)
           set(Calendar.MINUTE, mMinute)
           set(Calendar.SECOND, 0)
        }

        mRepeatTime = when(mRepeatType) {
            "Minute" -> { mRepeatNo * ReminderUtil.milMinute }
            "Hour" -> { mRepeatNo * ReminderUtil.milHour }
            "Day" -> { mRepeatNo * ReminderUtil.milDay }
            "Month" -> { mRepeatNo * ReminderUtil.milMonth }
            else -> { 0 }
        }

        if(mActive) {
            if(mRepeat) {
                ReminderUtil.setRepeatAlarm(activity.applicationContext, mCalendar, id.toInt(), mRepeatTime)
            } else {
                ReminderUtil.setAlarm(activity.applicationContext, mCalendar, id.toInt())
            }
        }

        eventBus.send(Event.BackPressed())


    }







}