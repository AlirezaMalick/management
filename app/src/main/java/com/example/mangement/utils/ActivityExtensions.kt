package com.example.mangement.utils

import androidx.fragment.app.Fragment
import com.example.mangement.ui.activity.dashboard.MainActivity

fun MainActivity.navigate(fragment: Fragment) {
    replaceFragment(fragment)
}



fun MainActivity.navigateAndRegisterToBackStack(fragment:Fragment){
    this.apply { replaceFragment(fragment = fragment) }
}

//fun Activity.navigateToRoot(){
//    this.apply {
//        val intent = Intent(this, SplashActivity::class.java)
//        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//        this.startActivity(intent)
//        this.nonNull {
//            finish()
//        }
//    }
//}