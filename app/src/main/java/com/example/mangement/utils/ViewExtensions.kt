package com.example.mangement.utils

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import com.example.mangement.ui.navdrawer.customView.BottomNavigationHandler
import com.example.mangement.ui.navdrawer.customView.BottomNavigationVisibility

fun ViewGroup.inflates(@LayoutRes resourceId: Int): View = View.inflate(context, resourceId, this)



fun BottomNavigationHandler.hide() {
    visibility(BottomNavigationVisibility.HIDE)
}

fun BottomNavigationHandler.show() {
    visibility(BottomNavigationVisibility.SHOW)
}

fun View.clickToActions(action: () -> Unit = {}) {
    setOnClickListener {
        hideKeyboards()
        action()
    }
}

fun View.hideKeyboards() {
    (context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
        windowToken,
        0
    )
}

fun View.show() = run {
    visibility = View.VISIBLE
    this
}

fun View.invisible() = run {
    visibility = View.INVISIBLE
    this
}

fun View.hide() = run {
    visibility = View.GONE
    this
}

fun View.visibility(show: Boolean, invisible: Boolean = false) = run {
    if (show) show() else if (invisible) invisible() else hide()
}
