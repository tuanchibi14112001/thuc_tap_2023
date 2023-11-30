package com.example.demoservice.model

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable


data class Song(var title: String, var singer: String, var image: Int, var resource: Int) :
    Serializable {

}