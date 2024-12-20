package com.example.myquizzapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myquizzapp.ui.theme.MyQuizzAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyQuizzAppTheme {
                    val navController = rememberNavController()
                    val quizViewModel: QuizViewModel = viewModel()
                    NavHost(navController = navController, startDestination = "Home") {
                        composable("Home") {
                            Home(navController, quizViewModel)
                        }
                        composable("Quiz_Page") {
                            QuizPage(navController, quizViewModel)
                        }
                        composable("Score_Page") {
                            ScorePage(navController)
                        }

                    }

            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

    MyQuizzAppTheme {

    }
}