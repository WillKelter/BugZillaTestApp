package com.willkelter.bugzillatestapp

import android.app.Application
import androidx.room.Room
import com.willkelter.bugzillatestapp.data.domain.BugsDatabase

class App: Application() {
    lateinit var app: App
    private lateinit var database: BugsDatabase

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(this, BugsDatabase::class.java, "bugs_database")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    fun getInstance(): App {
        return app
    }
    fun getDatabase(): BugsDatabase {
        return database
    }
}