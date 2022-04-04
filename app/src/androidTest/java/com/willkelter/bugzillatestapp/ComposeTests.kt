package com.willkelter.bugzillatestapp

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.willkelter.bugzillatestapp.ui.theme.BugZillaTestAppTheme
import com.willkelter.bugzillatestapp.view.BugCard
import com.willkelter.bugzillatestapp.view.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ComposeTests {

    @get: Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun test_card_text(){
        composeRule.setContent {
            BugZillaTestAppTheme {
                BugCard(
                    id = 1,
                    summary = "Found a bug",
                    creator = "user@mail.com",
                    product = "product",
                    status = "CONFIRMED",
                    creationTime = "10.24.21 15:44"
                ){
                    composeRule.onNodeWithText("#").assertExists()
                    composeRule.onNodeWithText("creator:").assertExists()
                    composeRule.onNodeWithText("product:").assertExists()
                    composeRule.onNodeWithText("status:").assertExists()
                }

            }
        }
    }

}