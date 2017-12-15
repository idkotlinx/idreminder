package com.idkotlin.idreminder.presentation.ui.reminder

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.amulyakhare.textdrawable.TextDrawable
import com.amulyakhare.textdrawable.util.ColorGenerator
import com.idkotlin.idreminder.R
import com.idkotlin.idreminder.data.entity.Reminder
import com.tutorial.learnlinuxpro.presentation.ui.base.BaseAdapter

/**
 * Created by kodeartisan on 29/11/17.
 */
class ReminderAdapter(context: Context): BaseAdapter<Reminder>(context) {

    private val TAG = ReminderAdapter::class.java.simpleName



    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder {
        val view = mLayoutInflater.inflate(R.layout.adapter_reminder, parent, false)

        return ReminderViewholder(view)

    }

    inner class ReminderViewholder(itemView: View?) : BaseViewHolder(itemView) {

        private val TAG = ReminderViewholder::class.java.simpleName

        private lateinit var mReminder: Reminder
        private val mColorGenerator = ColorGenerator.DEFAULT
        private val mTxtTitle: TextView? by lazy { itemView?.findViewById<TextView>(R.id.title)}
        private val mIvThumbnail: ImageView? by lazy { itemView?.findViewById<ImageView>(R.id.thumbnail_image) }
        private val mIvActive: ImageView? by lazy { itemView?.findViewById<ImageView>(R.id.active_image) }
        private val mTxtDatetime: TextView? by lazy { itemView?.findViewById<TextView>(R.id.date_time) }
        private val mTxtRepeatInfo: TextView? by lazy { itemView?.findViewById<TextView>(R.id.repeat_info) }

        override fun fillView(position: Int) {
            mReminder = mData[position]
            setTitleDrawable()
            setTitle()
            setDateTime()
            setRepeatInfo()
            setActiveDrawable()

        }

        private fun setTitle() {
            mTxtTitle?.text = mReminder.title
        }

        private fun setTitleDrawable() {
            val letter = if(mReminder.title.isNotEmpty()) mReminder.title.substring(0, 1) else "A"
            val color = mColorGenerator.randomColor
            val drawableBuilder = TextDrawable.builder().buildRound(letter, color)
            mIvThumbnail?.setImageDrawable(drawableBuilder)
        }

        private fun setDateTime() {
            mTxtDatetime?.text = "${mReminder.date} - ${mReminder.time}"
        }

        private fun setRepeatInfo() {
            mTxtRepeatInfo?.text = if(mReminder.repeat) "Every ${mReminder.repeatNo} ${mReminder.repeatType}(s)"
                                   else "Repeat Off"

        }

        private fun setActiveDrawable() {

            val imageDrawable = if(mReminder.active) R.drawable.ic_notifications_active else R.drawable.ic_notifications_off
            mIvActive?.setImageResource(imageDrawable)

        }

    }


}