package com.willkelter.bugzillatestapp.data.domain

import android.content.Context
import com.willkelter.bugzillatestapp.App
import com.willkelter.bugzillatestapp.data.BugModel

class LocalRepository(private val context: Context?) {
    private val roomDatabase = (context?.applicationContext as App).getDatabase()
    private val dao = roomDatabase.bugsDao()

     fun getBugs(): List<BugModel>{
        return dao.getAll()
    }

     fun insertBugs(bugs: List<BugModel>){
        return dao.insertAll(bugs)
    }
}