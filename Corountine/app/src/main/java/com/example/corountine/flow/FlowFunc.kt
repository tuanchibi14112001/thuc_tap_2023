package com.example.corountine.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.fold
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.reduce
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.runBlocking

fun main() {
    val list: List<Int> = listOf(1, 2, 3, 4, 5, 6, 7)
    runBlocking {
        //transform
        // bien doi nhieu gia tri truoc khi collect
//        list.asFlow().transform {
//            emit(it * it)
//            emit(it * 2)
//        }.collect {
//            println(it)
//        }

        //map
        // bien doi duoc 1 gia tri duy nhat
//        list.asFlow().map {
//            it*it // khong chay
//            it*2
//        }.collect{
//            println(it)
//        }


        //take
        // lay cac gia tri trong list
//        list.asFlow().take(3).collect{
//            println(it)
//        }

        //filter
        // loc flow theo dieu kien

//        list.asFlow().filter {
//            it % 2 == 0
//        }.collect{
//            println(it)
//        }

        //reduce
        // Tinh tong cong don trong flow
//        val sum = list.asFlow().reduce{ prev, value ->
//            prev + value // prev = prev + value
//        }
//        println(sum)

        //fold
        // Giong reduce them gia tri khoi dau
//        val sum2 = list.asFlow().fold(2){ prev, value ->
//        prev + value // prev = prev + value
//    }
//        println(sum)

        //single() and singleOrNull()
        // kiem tra so luong emit trong Flow neu != 1 bi loi


        //zip
        // ket hop 2 flow lai
//        val nums = listOf(1,2,3).asFlow()
//        val strs = listOf("a","b","c").asFlow()
//
//        nums.zip(strs){num,str ->
//            "num = $num and str = $str"
//        }.collect{
//            println(it)
//        }

        //flatMapConcat
        //cho doi cho flow 2 delay xong
        val startTime = System.currentTimeMillis()
        (1..3).asFlow().onEach { delay(100) }
            .flatMapLatest { request(it) }.collect {
                println("value = $it at ${System.currentTimeMillis() - startTime}")
            }


        //flatMap Merge
        //cai nao nhanh thi ghep truoc

        //flatMapLatest
        // cu delay la chuyen


    }
}
fun request(x : Int): Flow<String> = flow {
    emit("$x : First")
    delay(50)
    emit("$x : Second")

}

