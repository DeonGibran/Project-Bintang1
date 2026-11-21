package com.bintangg.finalproject

import android.accessibilityservice.AccessibilityService.GLOBAL_ACTION_BACK
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NavigationTest {
    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun switchingTabsDoesNotLeavePreviousTabsOnBackStack() {
        composeRule.onNodeWithText("Tasks").performClick()
        composeRule.onNodeWithText("Belum Selesai").assertIsDisplayed()
        composeRule.onNodeWithText("Stats").performClick()
        composeRule.onNodeWithText("Progress").assertIsDisplayed()
        composeRule.onNodeWithText("Tasks").performClick()
        composeRule.onNodeWithText("Tasks").performClick()

        pressSystemBack()

        composeRule.onNodeWithText("Dashboard").assertIsDisplayed()
    }

    @Test
    fun cancellingScheduleReturnsToDashboardWithoutBottomBarOnForm() {
        composeRule.onNodeWithText("Add Schedule").performScrollTo().performClick()
        composeRule.onNodeWithText("Tasks").assertDoesNotExist()
        composeRule.onNodeWithText("Batal / Reset").performClick()

        composeRule.onNodeWithText("Dashboard").assertIsDisplayed()
        composeRule.onNodeWithText("Tasks").assertIsDisplayed()
    }

    @Test
    fun cancellingNestedCategoryFormReturnsToCategoryList() {
        composeRule.onNodeWithText("Categories").performScrollTo().performClick()
        composeRule.onNodeWithContentDescription("Tambah").performClick()
        composeRule.onNodeWithText("Tambah Kategori Baru").assertIsDisplayed()
        composeRule.onNodeWithText("Batal").performScrollTo().performClick()

        composeRule.onNodeWithContentDescription("Tambah").assertIsDisplayed()
        composeRule.onNodeWithText("Tasks").assertDoesNotExist()
        pressSystemBack()
        composeRule.onNodeWithText("Dashboard").assertIsDisplayed()
    }

    @Test
    fun switchingLoginAndRegistrationDoesNotAccumulateForms() {
        composeRule.onNodeWithText("Create Account").performScrollTo().performClick()
        composeRule.onNodeWithText("Masuk di sini >").performScrollTo().performClick()
        composeRule.onNodeWithText("Daftar").performScrollTo().performClick()
        composeRule.onNodeWithText("Masuk di sini >").performScrollTo().performClick()
        composeRule.onNodeWithText("Tasks").assertDoesNotExist()

        pressSystemBack()

        composeRule.onNodeWithText("Dashboard").assertIsDisplayed()
    }

    private fun pressSystemBack() {
        val automation = InstrumentationRegistry.getInstrumentation().uiAutomation
        assertTrue(automation.performGlobalAction(GLOBAL_ACTION_BACK))
        composeRule.waitForIdle()
    }
}
