package com.bivizul.guessthesoccerplayer.ui.widget

import android.content.ComponentName
import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsClient
import androidx.browser.customtabs.CustomTabsIntent
import androidx.browser.customtabs.CustomTabsServiceConnection
import androidx.browser.customtabs.CustomTabsSession
import com.bivizul.guessthesoccerplayer.domain.WEBIK_NAME

fun dopik(context: Context, dopik: String) {

    val mCustomTabsServiceConnection: CustomTabsServiceConnection?
    var mClient: CustomTabsClient?
    var mCustomTabsSession: CustomTabsSession? = null
    mCustomTabsServiceConnection = object : CustomTabsServiceConnection() {

        override fun onCustomTabsServiceConnected(
            componentName: ComponentName,
            customTabsClient: CustomTabsClient,
        ) {
            mClient = customTabsClient
            mClient?.warmup(0L)
            mCustomTabsSession = mClient?.newSession(null)
        }

        override fun onServiceDisconnected(name: ComponentName) {
            mClient = null
        }
    }
    CustomTabsClient.bindCustomTabsService(context,
        WEBIK_NAME,
        mCustomTabsServiceConnection)

    val customTabsIntent = CustomTabsIntent.Builder(mCustomTabsSession)
        .setShowTitle(false)
        .build()

    customTabsIntent.launchUrl(context, Uri.parse(dopik))
}