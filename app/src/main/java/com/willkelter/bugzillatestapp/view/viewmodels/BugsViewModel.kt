package com.willkelter.bugzillatestapp.view.viewmodels

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.willkelter.bugzillatestapp.data.BugModel
import com.willkelter.bugzillatestapp.data.domain.BugsRepositoryImpl
import com.willkelter.bugzillatestapp.data.domain.LocalRepository
import com.willkelter.bugzillatestapp.utils.DataUpdateWorker
import com.willkelter.bugzillatestapp.utils.Sorting
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit


class BugsViewModel(context: Context): ViewModel() {
    private var bugsRepository = BugsRepositoryImpl()
    private var localRepository = LocalRepository(context)
    var bugListResponse = mutableStateListOf<BugModel>()
    private var errorMessage: String by mutableStateOf("")

    private fun getBugsList() {

            try {
                bugsRepository.getBugs(onSuccess = {
                    viewModelScope.launch {  localRepository.insertBugs(it)}

                }, onError = { Log.d("base", "ERROR") })
                } catch (e: Exception) {
                errorMessage = e.message.toString()
                Log.d("base", errorMessage)
            }
      viewModelScope.launch { bugListResponse.addAll(localRepository.getBugs()) }
    }

    init {
        getBugsList()
        val updateRequest: PeriodicWorkRequest = PeriodicWorkRequestBuilder<DataUpdateWorker>(15, TimeUnit.SECONDS).build()
        WorkManager.getInstance(context).enqueue(updateRequest)
    }


    fun sort(sort: Sorting){
        when(sort){
            Sorting.ID -> bugListResponse.sortBy { it.id }

            Sorting.STATUS ->  bugListResponse.sortBy { it.status }

            Sorting.PRODUCT -> bugListResponse.sortBy { it.product }

            Sorting.SUMMARY -> bugListResponse.sortBy { it.summary }
        }
    }
}

class BugsViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BugsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BugsViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}





