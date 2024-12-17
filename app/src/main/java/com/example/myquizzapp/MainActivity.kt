package com.example.myquizzapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myquizzapp.ui.theme.MyQuizzAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyQuizzAppTheme {

                Column (modifier = Modifier.fillMaxSize().padding(16.dp)) {

                    var index by remember { mutableStateOf(0) }
                    var userAnswer by remember { mutableStateOf(0) }
                    var result by remember { mutableStateOf("") }
                    var explain by remember { mutableStateOf("") }

                    Text(text = "Course Title", fontWeight = FontWeight.Bold)
                    Text(text = "Question ${index + 1}")
                    Text(text = questions[index].questionText)


                    // answer buttons
                    Button(onClick = {userAnswer = 0}, modifier = Modifier.fillMaxWidth()) { Text(text = questions[index].options[0]) }
                    Button(onClick = {userAnswer = 1}, modifier = Modifier.fillMaxWidth()) { Text(text = questions[index].options[1]) }
                    Button(onClick = {userAnswer = 2}, modifier = Modifier.fillMaxWidth()) { Text(text = questions[index].options[2]) }
                    Button(onClick = {userAnswer = 3}, modifier = Modifier.fillMaxWidth()) { Text(text = questions[index].options[3]) }

                    // check button
                    Button(onClick = {if (userAnswer == questions[index].correctAnswer) {result = "Correct!"} else {result = "Wrong!" ; explain = questions[index].explanation}}) { Text(text = "check") }

                    // navigation buttons
                    Row {
                        Button(onClick = {if (index > 0){ index-- }}, modifier = Modifier.fillMaxWidth().weight(1f)) { Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null) }
                        Button(onClick = {if (index < questions.size - 1){ index++ } else {index = 0} }, modifier = Modifier.fillMaxWidth().weight(1f)) { Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null) }
                    }

                    Text(text = result)
                    Text(text = explain)

                    Row {
                        for (i in 0 until questions.size) {
                            Text(text = i.toString(), modifier = if (i == index) {Modifier.background(Color.Gray).width(30.dp)} else {Modifier.width(30.dp)})

                        }
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
        Column (modifier = Modifier.fillMaxSize().padding(16.dp)) {

            var index by remember { mutableStateOf(0) }
            var userAnswer by remember { mutableStateOf(0) }
            var result by remember { mutableStateOf("") }
            var explain by remember { mutableStateOf("") }

            Text(text = "Course Title", fontWeight = FontWeight.Bold)
            Text(text = "Question ${index + 1}")
            Text(text = questions[index].questionText)


            // answer buttons
            Button(onClick = {userAnswer = 0}, modifier = Modifier.fillMaxWidth()) { Text(text = questions[index].options[0]) }
            Button(onClick = {userAnswer = 1}, modifier = Modifier.fillMaxWidth()) { Text(text = questions[index].options[1]) }
            Button(onClick = {userAnswer = 2}, modifier = Modifier.fillMaxWidth()) { Text(text = questions[index].options[2]) }
            Button(onClick = {userAnswer = 3}, modifier = Modifier.fillMaxWidth()) { Text(text = questions[index].options[3]) }

            // check button
            Button(onClick = {if (userAnswer == questions[index].correctAnswer) {result = "Correct!"} else {result = "Wrong!" ; explain = questions[index].explanation}}) { Text(text = "check") }

            // navigation buttons
            Row {
                Button(onClick = {if (index > 0){ index-- }}, modifier = Modifier.fillMaxWidth().weight(1f)) { Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null) }
                Button(onClick = {if (index < questions.size - 1){ index++ } else {index = 0} }, modifier = Modifier.fillMaxWidth().weight(1f)) { Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null) }
            }

            Text(text = result)
            Text(text = explain)

            Row {
                    for (i in 0 until questions.size) {
                        Text(text = i.toString(), modifier = if (i == index) {Modifier.background(Color.Gray).width(30.dp)} else {Modifier.width(30.dp)})

                    }

            }


        }



    }
}


// mock data:
data class Question(
    val questionText: String,
    val options: List<String>,
    val correctAnswer: Int,
    val explanation: String
)

val questions = listOf(
    Question(
        questionText = "question text 1",
        options = listOf("a", "b", "c", "d"),
        correctAnswer = 0,
        explanation = "explanation"
    ),

    Question(
        questionText = "question text 2",
        options = listOf("a", "b", "c", "d"),
        correctAnswer = 1,
        explanation = "explanation"
    )
)