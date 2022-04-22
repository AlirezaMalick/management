package com.example.mangement.ui.activity.forget

import android.app.Application
import android.content.Context
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mangement.R
import com.example.mangement.model.LoginModel
import com.example.mangement.repo.ApiResponse
import com.example.mangement.repo.ApiResponseError
import com.example.mangement.utils.ProjectLiveDataEvent
import com.example.mangement.utils.Utils
import com.example.mangement.utils.inverse
import com.example.mangement.utils.isInternetAvailable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgetPasswordActivityViewModel @Inject constructor(application: Application) : AndroidViewModel(application) {

    var emailValue = ""
    val registerModelResponse = MutableLiveData<ApiResponse<ProjectLiveDataEvent<LoginModel>>>()
    val login = LoginModel()
    val progressLiveData = MutableLiveData<Boolean>()


    fun onRegister(v: View) {
        Utils.hideKeyboard(v.context, v)
            register(emailValue)
    }


    fun register(username: String) {
        login.let {
            it.email=username
            it.password=""
        }
        if ((getApplication() as Context).isInternetAvailable.inverse) {

            registerModelResponse.postValue(
                ApiResponse.Error(ApiResponseError(0,
                    (getApplication() as Context).getString(R.string.internet_is_unavailable))))

        } else {
            viewModelScope.launch {
                try {
                    registerModelResponse.postValue(ApiResponse.Success(ProjectLiveDataEvent(login)))
                } catch (e: Exception) {
                    registerModelResponse.postValue(
                        ApiResponse.Error(ApiResponseError(0, e.message.toString())))
                }
            }
        }
    }

}