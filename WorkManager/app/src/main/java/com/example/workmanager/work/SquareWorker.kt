package com.example.workmanager.work

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.workmanager.NUM_A
import com.example.workmanager.NUM_B
import com.example.workmanager.makeStatusNotification
import com.example.workmanager.sleep

class SquareWorker(context: Context, parameters: WorkerParameters) : Worker(context, parameters) {
    override fun doWork(): Result {
        val numA = inputData.getInt(NUM_A, 0)
        val numB = inputData.getInt(NUM_B, 0)
        makeStatusNotification("Square $numA and num $numB", applicationContext)
        sleep()

        return try {
            val outputData = workDataOf(NUM_A to (numA * numA), NUM_B to (numB * numB))
            Result.success(outputData)
        } catch (e: Exception) {
            Result.failure()
        }


    }
}