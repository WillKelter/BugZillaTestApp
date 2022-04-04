package com.willkelter.bugzillatestapp.utils

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.willkelter.bugzillatestapp.App
import com.willkelter.bugzillatestapp.data.BugModel
import com.willkelter.bugzillatestapp.data.domain.BugsRepositoryImpl
import com.willkelter.bugzillatestapp.data.domain.LocalRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class DataUpdateWorker(context: Context, workerParams: WorkerParameters) : Worker(context,
    workerParams) {
    private val bugsRepository = BugsRepositoryImpl()
    private var localRepository = LocalRepository(context)
    private var serverData:List<BugModel> = listOf()

    override fun doWork(): Result {
        return try{
            val localData:List<BugModel> = localRepository.getBugs()

             bugsRepository.getBugs(onSuccess = {
                serverData = it
            }, onError = { Log.d("base", "ERROR") })
            if(localData != serverData){
                Log.d("base", "new data")
                Toast.makeText(applicationContext, "New data in base", Toast.LENGTH_SHORT).show()
            } else {Log.d("base", "no new data")}

            Result.success()
        } catch(throwable: Throwable){
            Result.failure()
        }
    }
}