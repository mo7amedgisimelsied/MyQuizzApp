package com.example.myquizzapp

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController


@Composable
fun QuizPage(navController: NavHostController, quizViewModel: QuizViewModel, quiz: Quiz) {

    var index by remember { mutableStateOf(0) }
    var score by remember { mutableStateOf(0) }
    var explain by remember { mutableStateOf("") }
    val questions by quizViewModel.questionsList.observeAsState(emptyList())
    var showExplanation by remember { mutableStateOf(false) }
    val optionsColors = remember { mutableStateListOf(Color.White, Color.White, Color.White, Color.White)}
    var isAnsweredCorreclty = remember { mutableStateListOf(*Array(questions.size) { false }) }



    fun checkAnswer(userAnswer: Int) {
        optionsColors.fill(Color.White)
        if (userAnswer == questions[index].correctAnswer) {
            optionsColors[userAnswer] = Color(0xFF00C853)
            showExplanation = false
            if(!isAnsweredCorreclty[index])
            {
                score++
                isAnsweredCorreclty[index] = true
            }

        } else {
            explain = questions[index].explanation
            showExplanation = true
            optionsColors[userAnswer] = Color(0xFFD32F2F)
        }
    }

                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceAround,
                        modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFF000814))
                        .padding(16.dp)
                    ) {

                        Column (horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                fontSize = 25.sp,
                                text = quiz.name,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )

                            Spacer(Modifier.padding(7.dp))

                            Text(
                                textAlign = TextAlign.Center,
                                fontSize = 20.sp,
                                text = questions[index].questionText,
                                color = Color.White
                            )
                        }


                        Column (horizontalAlignment = Alignment.CenterHorizontally){
                            for (i in 0..3) {
                                Button(
                                    onClick = { checkAnswer(i) },
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = ButtonDefaults.buttonColors(optionsColors[i]),
                                    shape = RoundedCornerShape(10.dp)
                                ) {
                                    Text(
                                        color = Color.DarkGray,
                                        textAlign = TextAlign.Center,
                                        text = questions[index].options[i]
                                    )
                                }
                                Spacer(modifier = Modifier.height(2.dp))
                            }

                            Spacer(Modifier.padding(3.dp))

                            Row (
                                Modifier.fillMaxWidth(0.8f),
                                ){
                                Button(
                                    onClick = {
                                        if (index > 0) {
                                            index--
                                            optionsColors.fill(Color.White)
                                        }
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(1f)
                                        .border(2.dp, Color.White, shape = RoundedCornerShape(50.dp)),
                                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                                ) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = null,
                                        tint = Color.White
                                    )
                                }

                                Spacer(Modifier.weight(2f))

                                Button(
                                    onClick = {
                                        if (index < questions.size - 1) {
                                            index++; showExplanation = false
                                            optionsColors.fill(Color.White)
                                        } else {
                                            navController.navigate("Score_Page/${quiz.id}/$score")
                                        }
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(1f)
                                        .border(2.dp, Color.White, shape = RoundedCornerShape(50.dp)),
                                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                                ) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                        contentDescription = null,
                                        tint = Color.White
                                    )
                                }
                            }

                            Spacer(Modifier.padding(5.dp))

                            if (showExplanation) {
                                Text(
                                    color = Color.White,
                                    modifier = Modifier
                                        .background(Color(0xFFe63946))
                                        .padding(10.dp),
                                    text = explain
                                )
                            }
                        }



                    Column (horizontalAlignment = Alignment.CenterHorizontally){
                        Text(
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            text = "Your Score: $score"
                        )

                        Text(
                            text = "Question ${index + 1} of ${questions.size}",
                            color = Color.White
                        )
                    }



                }
}