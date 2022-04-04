package com.willkelter.bugzillatestapp.data.domain

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.willkelter.bugzillatestapp.data.BugModel

@Dao
interface BugsDao {

    @Query("SELECT * FROM bugmodel")
    fun getAll(): List<BugModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(bugs: List<BugModel>)
}