package com.bivizul.guessthesoccerplayer.data

import android.app.Activity
import android.content.Context
import com.bivizul.guessthesoccerplayer.domain.APP_PREFERENCES
import com.bivizul.guessthesoccerplayer.domain.KEY_COUNTER
import com.bivizul.guessthesoccerplayer.domain.KEY_LIST
import com.bivizul.guessthesoccerplayer.domain.KEY_SCORE
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SettingPreferences(activity: Activity) {

    val preferences = activity.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)

    fun clearData() {
        preferences.edit().clear().apply()
    }

    fun saveList(list: List<Int>) {
        val json: String = Gson().toJson(list)
        preferences.edit().putString(KEY_LIST, json).apply()
    }

    fun getList(): List<Int> {
        val json = preferences.getString(KEY_LIST, "")
        val type = object : TypeToken<List<Int>>() {}.type
        return Gson().fromJson(json, type)
    }

    fun getCounter() = preferences.getInt(KEY_COUNTER, 0)
    fun getScore() = preferences.getInt(KEY_SCORE, 0)

    fun incrementCounter(counter: Int) = preferences.edit().putInt(KEY_COUNTER, counter + 1).apply()
    fun incrementScore(score: Int) = preferences.edit().putInt(KEY_SCORE, score + 1).apply()

}