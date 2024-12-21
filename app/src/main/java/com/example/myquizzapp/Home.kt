package com.example.myquizzapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController


@Composable
fun Home(navController: NavHostController, quizViewModel: QuizViewModel) {


    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Home Screen")

    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),

        ) {

        Row (modifier = Modifier.fillMaxWidth().padding(10.dp)){
            Column {
                Text(fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    text = "JetPack Compose Quiz")
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Highest Score: ${getHighScore(navController.context)}")
            }
            Button(onClick = {
                quizViewModel.setQuestions(questions)
                navController.navigate("Quiz_Page")
            }) {
                Text(text = "Start Quiz")
            }
        }

    }

    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),

        ) {

        Row (modifier = Modifier.fillMaxWidth().padding(10.dp)){
            Column {
                Text(fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    text = "React Native Quiz")
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Highest Score: ${getHighScore(navController.context)}")
            }
            Button(onClick = {
                quizViewModel.setQuestions(questions2)
                navController.navigate("Quiz_Page")
            }) {
                Text(text = "Start Quiz")
            }
        }

    }

}
}


@Preview(showBackground = true)
@Composable
fun HomePreview() {

    }


val questions: List<Question> = listOf(
    Question(
        questionText = "Which function is used to declare a Composable function?",
        options = listOf("@Compose", "@Composable", "@Ui", "@Functional"),
        correctAnswer = 1,
        explanation = "The `@Composable` annotation is used to mark a function as a Composable function, indicating that it can be used to build UI elements."
    ),
    Question(
        questionText = "What is the purpose of the `remember` function in Compose?",
        options = listOf("To trigger recomposition", "To handle user input", "To store data across recompositions", "To create side effects"),
        correctAnswer = 2,
        explanation = "`remember` is used to store an object in the Composition, allowing it to survive recompositions and maintain its state."
    ),
    Question(
        questionText = "Which composable is used to display text?",
        options = listOf("Box", "Text", "Row", "Column"),
        correctAnswer = 1,
        explanation = "The `Text` composable is used to display text in a Compose UI."
    ),
    Question(
        questionText = "What is the role of a Modifier in Compose?",
        options = listOf("To handle user interactions", "To manage state", "To customize the appearance and behavior of a composable", "To define the layout structure"),
        correctAnswer = 2,
        explanation = "Modifiers are used to decorate or modify the properties of a composable, such as its size, padding, background color, and click behavior."
    ),
    Question(
        questionText = "How do you trigger a recomposition in Compose?",
        options = listOf("By using a `LaunchedEffect`", "By manually invoking the `compose` function", "By calling the `invalidate` function", "By changing the state of a composable"),
        correctAnswer = 3,
        explanation = "Recomposition is automatically triggered when the state of a composable changes. This ensures that the UI is always up-to-date with the latest data."
    )
)

val questions2: List<Question> = listOf(
    Question(
        questionText = "222",
        options = listOf("@Compose", "@Composable", "@Ui", "@Functional"),
        correctAnswer = 1,
        explanation = "The `@Composable` annotation is used to mark a function as a Composable function, indicating that it can be used to build UI elements."
    ),
    Question(
        questionText = "222",
        options = listOf("To trigger recomposition", "To handle user input", "To store data across recompositions", "To create side effects"),
        correctAnswer = 2,
        explanation = "`remember` is used to store an object in the Composition, allowing it to survive recompositions and maintain its state."
    ),
    Question(
        questionText = "222",
        options = listOf("Box", "Text", "Row", "Column"),
        correctAnswer = 1,
        explanation = "The `Text` composable is used to display text in a Compose UI."
    ),
    Question(
        questionText = "222",
        options = listOf("To handle user interactions", "To manage state", "To customize the appearance and behavior of a composable", "To define the layout structure"),
        correctAnswer = 2,
        explanation = "Modifiers are used to decorate or modify the properties of a composable, such as its size, padding, background color, and click behavior."
    ),
    Question(
        questionText = "222",
        options = listOf("By using a `LaunchedEffect`", "By manually invoking the `compose` function", "By calling the `invalidate` function", "By changing the state of a composable"),
        correctAnswer = 3,
        explanation = "Recomposition is automatically triggered when the state of a composable changes. This ensures that the UI is always up-to-date with the latest data."
    )
)