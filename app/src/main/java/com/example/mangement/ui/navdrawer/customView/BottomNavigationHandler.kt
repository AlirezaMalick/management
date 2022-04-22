package com.example.mangement.ui.navdrawer.customView

import androidx.annotation.MenuRes

interface BottomNavigationHandler {
    fun updateMenu(@MenuRes menuRes: Int)
    fun visibility(bottomNavigationVisibility: BottomNavigationVisibility)
    fun hideBottomNavigations()
}

enum class BottomNavigationVisibility {
    SHOW,
    HIDE
}