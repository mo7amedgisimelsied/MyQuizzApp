package com.example.myquizzapp

import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController


@Composable
fun Home(navController: NavHostController, quizViewModel: QuizViewModel, context: Context) {
    val database = AppDatabase.getDatabase(context)
    val questionDao = database.questionDao()
    val questions by quizViewModel.questionsList.observeAsState(emptyList())

    // Container for the home screen content.
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().background(Color(0xFF000814))) {

        // Header Text
        Text(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            text = "Test Your Knowledge!")

        Spacer(Modifier.padding(10.dp)) // Adds vertical spacing below the header for better readability

    // The list of the quizzes are displayed as cards.
    // User can start the quiz by clicking on the start button.

        // Jetpack Compose Quiz
        QuizCard(
            quiz = quizes[0],
            context = context,
            questionDao = questionDao,
            quizViewModel = quizViewModel,
            navController = navController,
            questions = questions
        )

        // React Native Quiz
        QuizCard(
            quiz = quizes[1],
            context = context,
            questionDao = questionDao,
            quizViewModel = quizViewModel,
            navController = navController,
            questions = questions
        )
}
}


@Composable
fun QuizCard(
    quiz: Quiz,
    context: Context,
    questionDao: QuestionDao,
    quizViewModel: QuizViewModel,
    navController: NavHostController,
    questions: List<Question>
) {

    var isStartClicked by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth().padding(20.dp)
        ) {
            Column {
                Text(fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    text = quiz.name)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "High Score: ${getHighScore(context, quiz.id)}")
            }

            Button(onClick = {
                isStartClicked = true
                quizViewModel.fetchQuestions(questionDao, quiz.id)
            },
                colors = ButtonDefaults.buttonColors(Color(0xFF7b2cbf)),
                shape = RoundedCornerShape(10.dp)) {
                Text(color = Color.White, text = "Start")
            }
        }
    }

    // Navigate to QuizPage after questions are fetched
    LaunchedEffect(isStartClicked,questions) {
        if (isStartClicked && questions.isNotEmpty()) {
            navController.navigate("Quiz_Page/${quiz.id}/${quiz.name}")
            Log.d("Fetch", "questions fetched, quiz id: ${quiz.id}")
            isStartClicked = false
        }
    }
}

// Data class representing a quiz with an ID and name to organize quizzes.
data class Quiz(
    val id: String,
    val name: String
)

// List of quizzes available in the app
val quizes = listOf(
    Quiz(id = "1", name = "JetPack Compose"),
    Quiz(id = "2", name = "React Native")
)