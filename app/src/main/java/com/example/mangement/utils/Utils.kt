package com.example.mangement.utils

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.example.mangement.R


object Utils {
    fun isValidEmail(target: CharSequence?): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }

   /* fun showSnackbar(
        context: Activity, msg: String?,
        action:String?=null,
        listener: View.OnClickListener? =null) {
        val snackbar = Snackbar.make(
            context.findViewById(android.R.id.content),
            msg!!, Snackbar.LENGTH_LONG
        )
        try {
            val sbView = snackbar.view
            val textView = sbView.findViewById<View>(R.id.snackbar_text) as TextView
            textView.setTextColor(Color.WHITE)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        if(action!=null) {
            snackbar.setAction(action, listener)
        }
        snackbar.show()
    }*/

    fun hideKeyboard(context: Context, view: View) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }


    fun showKeyboard(context: Context, view: View?) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    }

}