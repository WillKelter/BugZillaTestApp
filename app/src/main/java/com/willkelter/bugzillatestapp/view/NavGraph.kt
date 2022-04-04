package com.willkelter.bugzillatestapp.view

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.willkelter.bugzillatestapp.data.BugModel
import com.willkelter.bugzillatestapp.utils.Screen
import com.willkelter.bugzillatestapp.utils.Sorting
import com.willkelter.bugzillatestapp.view.viewmodels.BugsViewModel


@Composable
fun NavGraph(navController: NavHostController, viewModel: BugsViewModel){

    NavHost(navController = navController, startDestination = Screen.Home.route){
        composable(route = Screen.Home.route){
            var showMenu by remember { mutableStateOf(false) }
            Scaffold(topBar = {
                TopAppBar(
                    title = {  },
                    actions = {
                        IconButton(onClick = { showMenu = !showMenu }) {
                            Icon(Icons.Default.List, "menu")
                        }
                        DropdownMenu(
                            expanded = showMenu,
                            onDismissRequest = { showMenu = false }
                        ) {
                            DropdownMenuItem(onClick = { showMenu = !showMenu
                            viewModel.sort(Sorting.ID)}) {
                                Text("sort by id")
                            }
                            DropdownMenuItem(onClick = { showMenu = !showMenu
                                viewModel.sort(Sorting.STATUS) }) {
                                Text(text = "sort by status")
                            }
                            DropdownMenuItem(onClick = { showMenu = !showMenu
                                viewModel.sort(Sorting.PRODUCT) }) {
                                Text(text = "sort by product")
                            }
                            DropdownMenuItem(onClick = { showMenu = !showMenu
                                viewModel.sort(Sorting.SUMMARY) }) {
                                Text(text = "sort by summary")
                            }
                        }
                    }
                )
            }) {
                MainScreen(viewModel = viewModel, navController)
            }
        }

        composable(Screen.Detail.route){
            val bugModel = navController.previousBackStackEntry?.savedStateHandle?.get<BugModel>("bug")
            bugModel?.let { DetailScreen(bugModel = bugModel) }

        }
    }
}