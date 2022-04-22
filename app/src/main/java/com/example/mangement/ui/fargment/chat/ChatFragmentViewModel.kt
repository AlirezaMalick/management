package com.example.mangement.ui.fargment.chat

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChatFragmentViewModel @Inject constructor(
    application: Application): AndroidViewModel(application){

}