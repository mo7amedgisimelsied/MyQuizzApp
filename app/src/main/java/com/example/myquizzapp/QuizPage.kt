package com.example.myquizzapp

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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

    /*
     * The `index` variable tracks the current question in the quiz.
     * - It is incremented when the user clicks the forward arrow.
     * - It is decremented when the user clicks the back arrow.
     */
    var index by remember { mutableStateOf(0) }
    var score by remember { mutableStateOf(0) } // The `score` variable keeps track of the user's score.
    var explain by remember { mutableStateOf("") } // The `explain` variable stores the explanation for the current question.
    val questions by quizViewModel.questionsList.observeAsState(emptyList())
    var showExplanation by remember { mutableStateOf(false) } // The `showExplanation` variable determines whether to show the explanation or not.
    val optionsColors = remember { mutableStateListOf(Color.White, Color.White, Color.White, Color.White)} // The `optionsColors` variable stores the background colors of the options.
    /*
     * `isAnswered` is a list of booleans that tracks whether each question has been answered.
     *  By default, all values are `false` and set to `true` when user submits their answer.
     */
    var isAnswered = remember { mutableStateListOf(*Array(questions.size) { false }) }

    // Evaluates user answer and updates the UI accordingly.
    fun checkAnswer(userAnswer: Int) {
        optionsColors.fill(Color.White)

        // If the user's answer is correct:
        if (userAnswer == questions[index].correctAnswer) {
            // Highlight the selected option in green to indicate correctness.
            optionsColors[userAnswer] = Color(0xFF00C853)

            // Hide the explanation since the answer is correct.
            showExplanation = false

            // Increment the score only if the question has not already been answered correctly.
            if(!isAnswered[index])
            {
                score++
                isAnswered[index] = true
            }

        }
        // If the answer is incorrect, display the explanation for the question.
        else {
            explain = questions[index].explanation
            showExplanation = true
            isAnswered[index] = true // To prevent user from switching their answer after seeing the correct one.
            // Highlight the selected option in red to indicate incorrectness.
            optionsColors[userAnswer] = Color(0xFFD32F2F) // Highlight the selected option in red to indicate incorrectness.
            optionsColors[questions[index].correctAnswer] = Color(0xFF00C853) // Highlight the correct option in green.
        }
    }

    // Container for the QuizPage content.
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF000814))
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            // Go back to home page
                Icon(
                    modifier = Modifier.clickable { navController.navigate("Home") },
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                    tint = Color.White
                )

            // quiz title
            Text(
                fontSize = 20.sp,
                text = quiz.name,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        Spacer(Modifier.padding(7.dp))

        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            // question text
            Text(
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                text = questions[index].questionText,
                color = Color.White
            )
        }


        // Container for option buttons and navigation buttons.
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

                // Option 1
                Button(
                    onClick = { checkAnswer(0) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(optionsColors[0]),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        color = Color.DarkGray,
                        textAlign = TextAlign.Center,
                        text = questions[index].option1
                    )
                }

            Spacer(modifier = Modifier.height(2.dp)) // Adds vertical space between buttons for better readability.

            // Option 2
            Button(
                onClick = { checkAnswer(1) },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(optionsColors[1]),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    color = Color.DarkGray,
                    textAlign = TextAlign.Center,
                    text = questions[index].option2
                )
            }

            Spacer(modifier = Modifier.height(2.dp)) // Adds vertical space between buttons for better readability.

            // Option 3
            Button(
                onClick = { checkAnswer(2) },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(optionsColors[2]),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    color = Color.DarkGray,
                    textAlign = TextAlign.Center,
                    text = questions[index].option3
                )
            }

            Spacer(modifier = Modifier.height(2.dp)) // Adds vertical space between buttons for better readability.

            // Option 4
            Button(
                onClick = { checkAnswer(3) },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(optionsColors[3]),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    color = Color.DarkGray,
                    textAlign = TextAlign.Center,
                    text = questions[index].option4
                )
            }

            Spacer(Modifier.padding(3.dp)) // Adds vertical space between option buttons and navigation buttons.

            // Container for navigation buttons.
            Row(
                Modifier.fillMaxWidth(0.8f),
            ) {
                // Back button to go to the previous question.
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

                Spacer(Modifier.weight(2f)) // Adds horizontal space between navigation buttons.

                // Forward button to go to the next question.
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

            Spacer(Modifier.padding(5.dp)) // Adds vertical space between navigation buttons and explanation text.

            // Show explanation if the user has not answered correctly.
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


        // This column displays the user's current score and their progress in the quiz (current question out of total questions), centered at the bottom of the page.
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
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