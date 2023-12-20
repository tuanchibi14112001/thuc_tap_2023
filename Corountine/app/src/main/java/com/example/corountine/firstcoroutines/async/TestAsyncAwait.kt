package com.example.corountine.firstcoroutines.async

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() = runBlocking<Unit> {
    val time = measureTimeMillis {
//        val one = printOne()
//        val two = printTwo()
//        println("The answer is ${one + two}")

        val one: Deferred<Int> = async { printOne() }
        val two: Deferred<Int> = async { printTwo() }
        println("The answer is ${one.await() + two.await()}")

    }
    println("Completed in $time ms")
}

suspend fun printOne(): Int {
    delay(1000L)
    return 10
}

suspend fun printTwo(): Int {
    delay(1000L)
    return 20
}