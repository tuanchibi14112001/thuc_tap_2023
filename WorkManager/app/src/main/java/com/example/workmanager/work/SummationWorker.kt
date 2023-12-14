package com.example.workmanager.work

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.workmanager.NUM_A
import com.example.workmanager.NUM_B
import com.example.workmanager.SUMMATION
import com.example.workmanager.makeStatusNotification
import com.example.workmanager.sleep

class SummationWorker(context: Context, parameters: WorkerParameters) : Worker(context, parameters) {
    override fun doWork(): Result {
        makeStatusNotification("Handling ", applicationContext)
        sleep()
        val numA = inputData.getInt(NUM_A, 0)
        val numB = inputData.getInt(NUM_B, 0)

        return try {
            val outputData = workDataOf(SUMMATION to (numA + numB))
            makeStatusNotification("Completed $numA + $numB = ${numA + numB}", applicationContext)
            Result.success(outputData)
        } catch (e: Exception) {
            Result.failure()
        }
    }

}