package com.example.myquizzapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myquizzapp.ui.theme.MyQuizzAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        fillDatabase(this)
        setContent {
            MyQuizzAppTheme {
                    /* The app contains three screens:
                     The Home screen displays a list of quizzes with their names and high scores.
                     The QuizPage screen allows the user to take a quiz by answering questions.
                     The ScorePage screen displays the user's score.
                    */
                    val navController = rememberNavController()

                    val quizViewModel: QuizViewModel = viewModel()

                    // The app uses a NavHost to navigate between the screens.
                    NavHost(navController = navController, startDestination = "Home") {

                        composable("Home") {
                            Home(navController, quizViewModel, applicationContext)
                        }

                        composable("Quiz_Page/{quizId}/{quizName}") {
                            backStackEntry ->
                            val quizId = backStackEntry.arguments?.getString("quizId") ?: ""
                            val quizName = backStackEntry.arguments?.getString("quizName") ?: ""
                            QuizPage(navController = navController, quizViewModel = quizViewModel, Quiz(quizId, quizName))
                        }

                        composable("Score_Page/{quizId}/{currentScore}") {
                            backStackEntry ->
                            val quizId = backStackEntry.arguments?.getString("quizId") ?: ""
                            val currentScore = backStackEntry.arguments?.getString("currentScore")?.toIntOrNull() ?: 0
                            ScorePage(navController = navController, context = applicationContext, currentScore = currentScore, quizId = quizId)
                        }

                    }

            }
        }
    }
}