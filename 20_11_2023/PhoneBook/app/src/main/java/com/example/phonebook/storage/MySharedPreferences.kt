package com.example.phonebook.storage

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import com.example.phonebook.model.UserData
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject

class MySharedPreferences(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
    private val c = context
    public fun setUser(userData: UserData) {
        val gson = Gson()
        val strJsonUser = gson.toJson(userData)
        val editor = sharedPreferences.edit()
        editor.putString("USER", strJsonUser)
        editor.apply()
    }

    public fun getUser(): UserData {
        val gson = Gson()
        val strJsonUser = sharedPreferences.getString("USER", null).toString()
        val user = gson.fromJson(strJsonUser, UserData::class.java)
        Toast.makeText(c, strJsonUser, Toast.LENGTH_SHORT).show()

        return user
    }

    public fun seListtUser(listUserData: MutableList<UserData>) {
        val gson = Gson()
        val jsonArray = gson.toJsonTree(listUserData).asJsonArray
        val strJsonListUser = jsonArray.toString()
        val editor = sharedPreferences.edit()
        editor.putString("LIST_USER", strJsonListUser)
        editor.apply()
    }

    public fun getListUser() : MutableList<UserData> {
        val gson = Gson()
        val strJsonArray = sharedPreferences.getString("LIST_USER",null)
        val userList = mutableListOf<UserData>()
        if(strJsonArray == null) {
            Log.d("CHECK", "1")
            return userList
        }
        val jsonArray = JSONArray(strJsonArray)
        var user = UserData("t", "t")
        var jsonObject = JSONObject()
        var i = 0
        while( i < jsonArray.length()){
            jsonObject = jsonArray.getJSONObject(i)
            user = gson.fromJson(jsonObject.toString(),UserData::class.java)
            userList.add(user)
            i++
        }

        return userList
    }



    public fun putString() {
        val editor = sharedPreferences.edit()
        editor.putString("test", "alo")
        editor.apply()
    }

    public fun getString(): String {
        return sharedPreferences.getString("test", null).toString()
    }


}