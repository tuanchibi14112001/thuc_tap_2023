package com.example.animalapp.utils

import android.content.Context
import android.content.SharedPreferences

class MySharedPreferences(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
    private val c = context

    fun setUserToken(token: String) {
        val editor = sharedPreferences.edit()
        editor.putString("USER_TOKEN", token)
        editor.apply()
    }

    fun getUserToken(): String {
        return sharedPreferences.getString("USER_TOKEN", null).toString()
    }
}