package com.willkelter.bugzillatestapp.data.domain

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.willkelter.bugzillatestapp.data.BugModel
import com.willkelter.bugzillatestapp.utils.Screen
import com.willkelter.bugzillatestapp.view.MainActivity
import com.willkelter.bugzillatestapp.view.NavGraph
import com.willkelter.bugzillatestapp.view.viewmodels.BugsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NavigationTest {
    @get: Rule
    val composeRule = createAndroidComposeRule<MainActivity>()
    private lateinit var navController: NavHostController

    @Before
    fun setupRallyNavHost() {
        composeRule.setContent {
            navController = rememberNavController()
            val testBugViewModel = BugsViewModel(LocalContext.current)
            NavGraph(navController = navController, viewModel = testBugViewModel)

        }
    }
    @Test
    fun test_menu_is_clickable(){
        composeRule.onNodeWithContentDescription("menu").performClick()
    }

    @Test
    fun test_menu_sort_by_id_is_clickable(){
        composeRule.onNodeWithContentDescription("menu").performClick()
        composeRule.onNodeWithText("sort by id").performClick()
    }

    @Test
    fun test_menu_sort_by_status_is_clickable(){
        composeRule.onNodeWithContentDescription("menu").performClick()
        composeRule.onNodeWithText("sort by status").performClick()
    }

    @Test
    fun test_menu_sort_by_product_is_clickable(){
        composeRule.onNodeWithContentDescription("menu").performClick()
        composeRule.onNodeWithText("sort by product").performClick()
    }

    @Test
    fun test_menu_sort_by_summary_is_clickable(){
        composeRule.onNodeWithContentDescription("menu").performClick()
        composeRule.onNodeWithText("sort by summary").performClick()
    }

    @Test
    fun test_navigate_to_details() {
        val bug = BugModel(1,"test","test","test","test","test",true,"test", "test","test")

        runBlocking {
            withContext(Dispatchers.Main) { // Needs to run on the UI tread
                navController.currentBackStackEntry?.savedStateHandle?.set("bug", bug)
                navController.navigate(Screen.Detail.route)
            }
        }

        composeRule.onNodeWithText("id: ${bug.id}").assertIsDisplayed()
    }
}