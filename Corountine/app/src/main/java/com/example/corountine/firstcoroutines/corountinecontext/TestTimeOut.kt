package com.example.corountine.firstcoroutines.corountinecontext

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

fun main (){
    runBlocking {
        val result = withTimeoutOrNull(1800){
            repeat(4) {
                println("$it")
                delay(500)
            }
            "Done"
        }
        println(result)
    }
}