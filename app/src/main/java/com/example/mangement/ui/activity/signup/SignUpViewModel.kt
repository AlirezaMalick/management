package com.example.mangement.ui.activity.signup

import android.app.Application
import android.content.Context
import android.view.View
import androidx.databinding.ObservableField
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
class SignUpViewModel @Inject constructor(application: Application) : AndroidViewModel(application) {

    val progressLiveData = MutableLiveData<Boolean>()
    var emailError = MutableLiveData<String?>()
    var passwordError = MutableLiveData<String?>()
    var retryPasswordError = MutableLiveData<String?>()
    var nameValue = ""
    var emailValue = ""
    var passwordValue = ""
    var retryPasswordValue = ""
    val login = LoginModel()

    val registerModelResponse = MutableLiveData<ApiResponse<ProjectLiveDataEvent<LoginModel>>>()



    fun onRegister(v: View) {
        Utils.hideKeyboard(v.context, v)
        if (isCredentialsValid(v.context, emailValue, passwordValue)) {
            register(nameValue,emailValue, passwordValue)
        }
    }


    fun isCredentialsValid(context: Context, email: String, password: String): Boolean {
        val isValidateEmail = validateEmail(context, email)
        val isvalidPassword = validatePassword(context, password)
        return isValidateEmail && isvalidPassword
    }


    private fun validateEmail(context: Context, email: String): Boolean {
        return if (Utils.isValidEmail(email)) {
            emailError.postValue(null)
            true
        } else {
            emailError.postValue(context.getString(R.string.error_email))
            false
        }
    }
    private fun validatePassword(context: Context, password: String): Boolean {
        return if (password.isNotEmpty()) {
            passwordError.postValue(null)
            true
        } else {
            passwordError.postValue(context.getString(R.string.error_password))
            false
        }
    }



    fun register(username: String,useremail: String, password: String) {
//        progressLiveData.postValue(true)

        login.let {
            it.name=username
            it.email=useremail
            it.password=password
        }
        if ((getApplication() as Context).isInternetAvailable.inverse) {

            registerModelResponse.postValue(
                ApiResponse.Error(
                    ApiResponseError(0,
                    (getApplication() as Context).getString(R.string.internet_is_unavailable))))

        } else {
            viewModelScope.launch {
                try {
                    registerModelResponse.postValue(ApiResponse.Success(ProjectLiveDataEvent(login)))
                } catch (e: Exception) {
                    registerModelResponse.postValue(
                        ApiResponse.Error(
                            ApiResponseError(0, e.message.toString())))
                }
            }
        }
    }



}