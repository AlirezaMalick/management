package com.example.mangement.repo

sealed class ApiResponse<T>(val data:T? = null,val apiResponseError: ApiResponseError? = null)
{
    class Loading<T> : ApiResponse<T>()
    class Success<T>(data: T?) : ApiResponse<T>(data = data)
    class Error<T>(apiResponseError: ApiResponseError?) : ApiResponse<T>(apiResponseError = apiResponseError)
}
