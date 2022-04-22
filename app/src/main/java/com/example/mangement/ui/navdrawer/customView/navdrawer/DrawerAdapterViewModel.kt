package com.example.mangement.ui.navdrawer.customView.navdrawer

import androidx.lifecycle.ViewModel
import com.target.app.estidama.customviews.navdrawer.DrawerAdapter
import com.target.app.estidama.customviews.navdrawer.DrawerItem

class DrawerAdapterViewModel(
    val drawerItem: DrawerItem,
    val listener: DrawerAdapter.OnDrawerItemClickListener
) : ViewModel() {


    fun onItemClick(){
        listener.onDrawerItemClick(drawerItem)
    }

}