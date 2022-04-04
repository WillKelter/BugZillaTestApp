package com.willkelter.bugzillatestapp.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.willkelter.bugzillatestapp.App
import com.willkelter.bugzillatestapp.ui.theme.BugZillaTestAppTheme
import com.willkelter.bugzillatestapp.view.viewmodels.BugsViewModel
import com.willkelter.bugzillatestapp.view.viewmodels.BugsViewModelFactory

class MainActivity : ComponentActivity() {

    private val bugsViewModel by viewModels<BugsViewModel>{
        BugsViewModelFactory(this.applicationContext as App)
    }
    private lateinit var navController: NavHostController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BugZillaTestAppTheme {
                navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NavGraph(navController = navController, viewModel = bugsViewModel)
                }

            }
        }
    }

}

