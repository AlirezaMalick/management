package com.example.mangement.utils

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.view.View
import android.widget.Toast
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment

const val EMPTY_STRING = ""

val Context.isInternetAvailable: Boolean
    get() = kotlin.run {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting
    }


inline fun <T : Any> T?.nonNull(action: T.() -> Unit) = this?.apply { action(this) }


val Boolean.inverse
    get() = not()

val Boolean?.toString
    get() = if (this == null || this == false) "0" else "1"


val kotlin.Boolean?.default
    get() = this ?: false


fun Fragment.showToast(context: Context,Message:String) {
    Toast.makeText(context, "$Message", Toast.LENGTH_SHORT).show()
}

fun Activity.showToast(context: Context,Message:String) {
    Toast.makeText(context, "$Message", Toast.LENGTH_SHORT).show()
}



fun View.clickToAction(action: () -> Unit = {}) {
    setOnClickListener {
        hideKeyboard()
        action()
    }
}

fun View.hideKeyboard() {
    (context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
        windowToken,
        0
    )
}


fun String.getAuctionSimplifiedFloatString(x: Float): String? {
    val result = String.format("%.1f", x).replace("\\.?0*$".toRegex(), "")
    return if (result.endsWith(".0")) {
        result.replace(".0", "")
    } else result
}




