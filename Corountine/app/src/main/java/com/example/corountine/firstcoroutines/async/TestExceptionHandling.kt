package com.example.corountine.firstcoroutines.async

import kotlinx.coroutines.*
import java.lang.NullPointerException


// Xu ly ngoai le
//fun main() {
//    runBlocking {
//        val handler= CoroutineExceptionHandler{_,exception ->
//            println("Error here: ${exception.toString()}")
//        }
//
//        val job=GlobalScope.launch(handler + Dispatchers.Default) {
//            println("Throw Exception from Launch")
//            throw NullPointerException()
//        }
//        job.join()
//
//        val deferred=GlobalScope.async(handler) {
//            println("Throw Exception from Async")
//            throw IndexOutOfBoundsException()
//        }
//
//        try {
//            deferred.await()
//        }catch (e:IndexOutOfBoundsException){
//            println(e.toString())
//        }
//
//    }
//}

// Nhieu ngoai le

fun main() {
    runBlocking {
        val handler= CoroutineExceptionHandler{_,exception ->
            println("Exception: $exception with suppressed ${exception.suppressed.contentToString()}")
        }

        val job = GlobalScope.launch(handler) {
            launch {
                println("Coroutine 1")
                delay(300)
                println("Coroutine 1 continue")
                throw IndexOutOfBoundsException("Coroutine 1")
            }

            launch {
                try {
                    delay(Long.MAX_VALUE)
                }finally {
                    throw ArithmeticException("Coroutine 2")
                }
            }

            launch {
                println("Coroutine 3")
                delay(400)
                println("Coroutine 3 continue")
                throw ArithmeticException("Coroutine 3")
            }
        }
        job.join()
        delay(1000)
    }
}