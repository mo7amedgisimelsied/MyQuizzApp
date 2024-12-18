package com.example.myquizzapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
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
                    NavHost(navController = navController, startDestination = "Home") {
                        composable("Home") {
                            Home(navController)
                        }
                        composable("Quiz_Page") {
                            QuizPage()
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