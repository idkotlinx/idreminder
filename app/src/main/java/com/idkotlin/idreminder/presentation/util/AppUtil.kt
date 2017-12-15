package com.tutorial.learnlinuxpro.presentation.util

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri

/**
 * Created by kodeartisan on 25/10/17.
 */
object AppUtil {

    fun goToMarket(packageName: String) {


        try {
            var intent: Intent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=${packageName}"))
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            Util.getContext().startActivity(intent)
        } catch (anfe: ActivityNotFoundException) {
            var intent: Intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=${packageName}"))
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Util.getContext().startActivity(intent)
        }
    }
}