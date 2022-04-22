package com.example.mangement.ui.navdrawer.customView.navdrawer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
  import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DrawerFragmentViewModel @Inject constructor() : ViewModel() {

    val onLogoutLiveData = MutableLiveData<Boolean>()
    val onCloseDrawerLiveData = MutableLiveData<Boolean>()


    fun logOut(){
        onLogoutLiveData.value = true
    }

    fun closeDrawer(){
        onCloseDrawerLiveData.value = true
    }



}