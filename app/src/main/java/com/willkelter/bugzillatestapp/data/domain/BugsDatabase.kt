package com.willkelter.bugzillatestapp.data.domain

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.willkelter.bugzillatestapp.data.BugModel

@Database(entities = [BugModel::class], version = 1, exportSchema = false)
abstract class BugsDatabase: RoomDatabase() {
    abstract fun bugsDao(): BugsDao

}