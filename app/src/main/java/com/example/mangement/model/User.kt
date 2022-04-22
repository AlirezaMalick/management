package com.example.mangement.model

import java.io.Serializable

data class User(var Id :String?=null,
                var name :String?=null,
                var email :String?=null,
                var address :String?=null,
                var picture :String?=null,
                var dob :String?=null,
                var number :String?=null,
                var designation :String?=null):Serializable
