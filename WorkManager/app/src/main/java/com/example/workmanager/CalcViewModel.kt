package com.example.workmanager

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.example.workmanager.work.SquareWorker
import com.example.workmanager.work.SummationWorker

class CalcViewModel(application: Application) : ViewModel() {
    internal var result: Int = 0
    internal var outputWorkInfos: LiveData<List<WorkInfo>>
    private val workManager = WorkManager.getInstance(application)


    init {
        outputWorkInfos = workManager.getWorkInfosByTagLiveData(TAG_OUTPUT)
    }
    internal fun add(numA: Int, numB: Int){
        val dataBuilder = Data.Builder().putInt(NUM_A, numA)
            .putInt(NUM_B, numB).build()
        val numRequest = OneTimeWorkRequestBuilder<SquareWorker>().setInputData(dataBuilder).build()
        //workManager.enqueue(numRequest)
        val summationRequest = OneTimeWorkRequestBuilder<SummationWorker>().addTag(TAG_OUTPUT).build()
//        var continuation = workManager.beginWith(numRequest)

        var continuation = workManager.beginUniqueWork(SUMMATION_WORK_NAME,ExistingWorkPolicy.REPLACE,numRequest)
        continuation =  continuation.then(summationRequest)
        continuation.enqueue()

    }

    internal fun cancel(){
        workManager.cancelUniqueWork(SUMMATION_WORK_NAME)
    }
    class CalcViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CalcViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CalcViewModel(application) as T
            }

            throw IllegalArgumentException("Unable construct viewModel")
        }
    }
}