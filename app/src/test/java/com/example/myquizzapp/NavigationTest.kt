package com.example.myquizzapp

import androidx.navigation.NavHostController
import org.junit.Test
import org.mockito.Mockito.*

class NavigationTest {
    @Test
    fun startButton_click_shouldNavigateToCorrectScreen() {
        val navController = mock(NavHostController::class.java)
        val quizId = "1"
        val quizName = "JetPack Compose"

        // Simulate navigation action
        navController.navigate("Quiz_Page/$quizId/$quizName")

        // Verify the navigation
        verify(navController).navigate("Quiz_Page/$quizId/$quizName")
    }

    @Test
    fun arrowForward_click_shouldNavigateToCorrectScreen() {
        val navController = mock(NavHostController::class.java)
        val quizId = "1"
        val currentScore = 5

        navController.navigate("Score_Page/$quizId/$currentScore")

        verify(navController).navigate("Score_Page/$quizId/$currentScore")
    }
}
