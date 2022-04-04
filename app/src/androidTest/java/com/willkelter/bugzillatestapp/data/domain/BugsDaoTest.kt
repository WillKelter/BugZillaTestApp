package com.willkelter.bugzillatestapp.data.domain

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.willkelter.bugzillatestapp.data.BugModel
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class BugsDaoTest {
    private lateinit var database: BugsDatabase
    private lateinit var dao: BugsDao

    @Before
    fun setup(){
        database = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(), BugsDatabase::class.java).allowMainThreadQueries().build()
        dao = database.bugsDao()
    }

    @After
    fun teardown(){
        database.close()
    }

    @Test
    fun insertAll(){
        val bugModel = BugModel(1, "test","test","test", "test", "test",true, "test","test","test")
        val bugModelList = listOf<BugModel>(bugModel)
        dao.insertAll(bugModelList)

        val testList = dao.getAll()
        assertThat(testList).contains(bugModel)
    }
}