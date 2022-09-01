package com.example.twitter.data

data class User( var Username:String,
                 var Email:String) {

    var Posts: List<String>?=null


}

data class Post(var Text: String , var Name:String)


