package com.example.corountine.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

fun main (){
    runBlocking {
//        foo(5).collect{
//            println(it)
//        }

//        withTimeoutOrNull(3500){
//            foo(10).collect{
//                println(it)
//            }
//        }

        (1..5).asFlow().collect{
            println(it)
        }

        val arr = arrayOf("a", "b", "c", "d")
        arr.asFlow().collect{
            println(it)
        }


    }
}

fun foo(x : Int): Flow<Int> = flow{
    for(i in 0..x){
        delay(1000)
        emit(i)

    }

}