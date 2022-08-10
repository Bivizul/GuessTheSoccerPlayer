package com.bivizul.guessthesoccerplayer.domain

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.net.ConnectivityManager
import java.util.*

object Util {

    fun getPostZovServaka(context: Context): Locale = context.resources.configuration.locales[0]

    fun checkEthernet(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    fun getDialogErrorConnect(context: Context, activity: Activity) {
        AlertDialog.Builder(context).apply {
            setTitle("Connection error")
            setMessage("There is something wrong with the connection, please try again later.")

            setPositiveButton("go out") { _, _ ->
                activity.finish()
            }
            setCancelable(true)
        }.create().show()
    }

    fun createRandomList(): List<Int> {
        val randomList = mutableListOf<Int>()
        while (randomList.size < 10) {
            val randNum = (0 until 10).random()
            if (!randomList.contains(randNum)) {
                randomList.add(randNum)
            }
        }
        return randomList
    }

}

