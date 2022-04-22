package com.example.mangement.utils

import android.content.Context
import android.content.SharedPreferences


class PreferenceClass {


    companion object {

        fun putBooleanPref(context: Context,keyPreferenceName:String, key: String, value: Boolean) {
            val prefs = context.getSharedPreferences(keyPreferenceName, Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = prefs.edit()
            editor.putBoolean(key, value)
            editor.apply()

        }

        fun getBooleanPref(context: Context,keyPreferenceName:String, key: String): Boolean {
            val prefs = context.getSharedPreferences(keyPreferenceName, Context.MODE_PRIVATE)
            return prefs.getBoolean(key, false)
        }

        fun putStringPref(context: Context,keyPreferenceName:String, key: String, value: String) {
            val prefs = context.getSharedPreferences(keyPreferenceName, Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = prefs.edit()
            editor.putString(key, value)
            editor.apply()

        }

        fun getStringPref(context: Context,keyPreferenceName:String, key: String): String? {
            val prefs = context.getSharedPreferences(keyPreferenceName, Context.MODE_PRIVATE)
            return prefs.getString(key, null)
        }


        fun clearPref(context: Context,keyPreferenceName:String){
            val prefs = context.getSharedPreferences(keyPreferenceName, Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = prefs.edit()
            editor.clear()
            editor.commit()
        }

    }

}