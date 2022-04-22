package com.example.mangement.ui.activity.login

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
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
class LoginActivityViewModel @Inject constructor(application: Application) : AndroidViewModel(application) {

    companion object {
        val TAG = LoginActivityViewModel::class.java.simpleName
        val SHARED_PREFERENCE_NAME = "login_prefs"
        val USER_NAME = "username"
        val PASSWORD = "password"
    }

    val progressLiveData = MutableLiveData<Boolean>()
    var rememberMe = false
    var emailValue = ""
    var passwordValue = ""
    var passwordError = MutableLiveData<String?>()

    var sharedpreferences: SharedPreferences
    var emailError = MutableLiveData<String?>()
    val loginModelResponce = MutableLiveData<ApiResponse<ProjectLiveDataEvent<LoginModel>>>()
    val login =LoginModel()

   init {
       sharedpreferences = application .getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
       if (!getUserName().isNullOrEmpty()) {
           rememberMe = true
           val userName = getUserName()
           val password = getPassword()
           emailValue = userName?:""
           passwordValue = password ?: ""
       }
   }

    fun onLogin(v: View) {
        Utils.hideKeyboard(v.context, v)
        if (isCredentialsValid(v.context, emailValue, passwordValue)) {
            login(emailValue, passwordValue)
        }
        saveCredentials()
//        login(emailValue, passwordValue)
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

    fun saveCredentials() {
        if (rememberMe) {
            saveUserNameAndPassword(emailValue.trim(), passwordValue.trim())
        } else {
            saveUserNameAndPassword("", "")
        }
    }

    fun saveUserNameAndPassword(email: String, password: String) {
        saveStringInPreference(USER_NAME, email)
        saveStringInPreference(PASSWORD, password)
    }

    private fun saveStringInPreference(key: String, value: String) {
        val editor = sharedpreferences.edit()
        editor.putString(key, value)
        editor.commit()
    }

    fun getUserName(): String? {
        return getStringFromPreference(USER_NAME)
    }

    fun getPassword(): String? {
        return getStringFromPreference(PASSWORD)
    }

    private fun getStringFromPreference(key: String): String? {
        return sharedpreferences.getString(key, "")
    }


    fun login(username: String, password: String) {
//        progressLiveData.postValue(true)

        login.let {
            it.email=username
            it.password=password
        }
        if ((getApplication() as Context).isInternetAvailable.inverse) {

            loginModelResponce.postValue(
                ApiResponse.Error(ApiResponseError(0,
                    (getApplication() as Context).getString(R.string.internet_is_unavailable))))

        } else {
            viewModelScope.launch {
                try {
                    loginModelResponce.postValue(ApiResponse.Success(ProjectLiveDataEvent(login)))
                } catch (e: Exception) {
                    loginModelResponce.postValue(
                        ApiResponse.Error(
                            ApiResponseError(
                                0, e.message.toString())))
                }
            }
        }
    }



}


