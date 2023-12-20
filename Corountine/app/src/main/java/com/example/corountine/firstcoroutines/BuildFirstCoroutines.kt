package com.example.corountine.firstcoroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main(){
    GlobalScope.launch {
        delay(1000L)
        println("Hello")
    }

    println("World")
    Thread.sleep(2000L)
}