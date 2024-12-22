package com.example.myquizzapp

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController

@Composable
fun QuizPage(navController: NavHostController, quizViewModel: QuizViewModel, quiz: Quiz) {

                    Column (modifier = Modifier.fillMaxSize().padding(16.dp)) {
                    var index by remember { mutableStateOf(0) }
                    var score by remember { mutableStateOf(0) }
                    var explain by remember { mutableStateOf("") }



                        val questions by quizViewModel.questionsList.observeAsState(emptyList())


                        Text(text = "Course Title", fontWeight = FontWeight.Bold)
                    Text(text = "Question ${index + 1}")
                    Text(text = questions[index].questionText)

                        fun checkAnswer(userAnswer: Int) {
                            if (userAnswer == questions[index].correctAnswer) {
                                score++
                            } else {
                                explain = questions[index].explanation
                            }
                        }
                    // answer buttons
                    Button(onClick = {checkAnswer(0)}, modifier = Modifier.fillMaxWidth()) { Text(text = questions[index].options[0]) }
                    Button(onClick = {checkAnswer(1)}, modifier = Modifier.fillMaxWidth()) { Text(text = questions[index].options[1]) }
                    Button(onClick = {checkAnswer(2)}, modifier = Modifier.fillMaxWidth()) { Text(text = questions[index].options[2]) }
                    Button(onClick = {checkAnswer(3)}, modifier = Modifier.fillMaxWidth()) { Text(text = questions[index].options[3]) }





                    // navigation buttons
                    Row {
                        Button(onClick = {if (index > 0){ index-- }}, modifier = Modifier.fillMaxWidth().weight(1f)) { Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null) }
                        Button(onClick = {if (index < questions.size - 1){ index++ } else {navController.navigate("Score_Page/${quiz.id}/$score")} }, modifier = Modifier.fillMaxWidth().weight(1f)) { Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null) }
                    }

                    Text(text = "Your Score: $score")
                    Text(text = explain)

                    Row {
                        for (i in 0 until questions.size) {
                            Text(text = "${i + 1}", modifier = if (i == index) {Modifier.background(Color.Gray).width(30.dp)} else {Modifier.width(30.dp)})

                        }
                    }

                }
}