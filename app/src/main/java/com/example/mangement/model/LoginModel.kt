package com.example.mangement.model

import java.io.Serializable

data class LoginModel(var name:String?=null,
                      var email:String?=null,
                      var password:String?=null):Serializable
