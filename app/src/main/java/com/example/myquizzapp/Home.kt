package com.example.myquizzapp

import android.content.Context
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController


@Composable
fun Home(navController: NavHostController, quizViewModel: QuizViewModel, context: Context) {


    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().background(Color(0xFF000814))) {
        Text(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            text = "Test Your Knowledge!")

        Spacer(Modifier.padding(10.dp))

    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White)


        ) {

        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth().padding(20.dp)){
            Column {
                Text(fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    text = quizes[0].name)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "High Score: ${getHighScore(context, quizes[0].id)}")
            }
            Button(onClick = {
                quizViewModel.setQuestions(jetpackComposeQs)
                navController.navigate("Quiz_Page/${quizes[0].id}/${quizes[0].name}")
            },
                colors = ButtonDefaults.buttonColors(Color(0xFF7b2cbf)),
                shape = RoundedCornerShape(10.dp)) {
                Text(
                    color = Color.White,
                    text = "Start")
            }
        }

    }


    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {

        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth().padding(20.dp)){
            Column {
                Text(fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    text = quizes[1].name)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "High Score: ${getHighScore(context, quizes[1].id)}")
            }


            Button(onClick = {
                quizViewModel.setQuestions(reactNativeQs)
                navController.navigate("Quiz_Page/${quizes[1].id}/${quizes[1].name}")
            },
                colors = ButtonDefaults.buttonColors(Color(0xFF7b2cbf)),
                shape = RoundedCornerShape(10.dp)) {
                Text(
                    color = Color.White,
                    text = "Start")
            }
        }

    }

}
}

data class Quiz(
    val id: String,
    val name: String
)

val quizes = listOf(
    Quiz(id = "1", name = "JetPack Compose"),
    Quiz(id = "2", name = "React Native")
)



val jetpackComposeQs: List<Question> = listOf(
    Question(
        questionText = "What is Jetpack Compose?",
        options = listOf("A framework for Android UI development", "A tool for backend development", "A database library", "A networking library"),
        correctAnswer = 0,
        explanation = "Jetpack Compose is a modern toolkit for building native Android UI with Kotlin."
    ),
    Question(
        questionText = "Which programming language is used with Jetpack Compose?",
        options = listOf("Java", "Swift", "Kotlin", "C++"),
        correctAnswer = 2,
        explanation = "Jetpack Compose is built for Kotlin, providing a declarative UI approach."
    ),
    Question(
        questionText = "How do you create a basic composable function in Jetpack Compose?",
        options = listOf("fun MyComposable() {}", "Composable fun MyComposable() {}", "fun Composable() {}", "Composable MyComposable() {}"),
        correctAnswer = 1,
        explanation = "In Jetpack Compose, a composable function is defined with the '@Composable' annotation."
    ),
    Question(
        questionText = "Which of the following is used to layout elements in Jetpack Compose?",
        options = listOf("LinearLayout", "ConstraintLayout", "Box", "Column and Row"),
        correctAnswer = 3,
        explanation = "Jetpack Compose uses 'Column' and 'Row' to arrange components vertically or horizontally."
    ),
    Question(
        questionText = "What is the purpose of 'Modifier' in Jetpack Compose?",
        options = listOf("To manage state", "To apply UI transformations like padding, sizing, etc.", "To create UI components", "To manage navigation"),
        correctAnswer = 1,
        explanation = "'Modifier' is used in Jetpack Compose to apply UI transformations like padding, alignment, and size to elements."
    ),
    Question(
        questionText = "What is a 'remember' function used for in Jetpack Compose?",
        options = listOf("To save UI data across recompositions", "To navigate between screens", "To handle gestures", "To initialize variables in composables"),
        correctAnswer = 0,
        explanation = "'remember' is used to store values in a composable function that should persist during recompositions."
    ),
    Question(
        questionText = "How do you define a state in Jetpack Compose?",
        options = listOf("var state = remember { mutableStateOf(0) }", "var state = stateOf(0)", "val state = MutableState(0)", "var state = remember { stateOf(0) }"),
        correctAnswer = 0,
        explanation = "To define a state in Jetpack Compose, you use 'remember { mutableStateOf() }' to store the value."
    ),
    Question(
        questionText = "Which of the following libraries is commonly used with Jetpack Compose for navigation?",
        options = listOf("Navigation Compose", "Jetpack Navigation", "Compose NavController", "ComposeNavKit"),
        correctAnswer = 0,
        explanation = "'Navigation Compose' is the Jetpack Compose library for handling navigation between composable screens."
    ),
    Question(
        questionText = "Which layout is used in Jetpack Compose to display items in a scrolling list?",
        options = listOf("RecyclerView", "LazyColumn", "ListView", "ScrollView"),
        correctAnswer = 1,
        explanation = "'LazyColumn' is used in Jetpack Compose to create efficient, scrollable lists."
    ),
    Question(
        questionText = "How do you apply a custom theme in Jetpack Compose?",
        options = listOf("By using the '@Composable' annotation", "By wrapping composables in a 'MaterialTheme'", "By modifying the layout of components", "By using styles in XML"),
        correctAnswer = 1,
        explanation = "Custom themes in Jetpack Compose are applied using the 'MaterialTheme' composable."
    )
)

val reactNativeQs: List<Question> = listOf(
    Question(
        questionText = "What is React Native primarily used for?",
        options = listOf("Web development", "Mobile app development", "Database management", "Game development"),
        correctAnswer = 1,
        explanation = "React Native is primarily used to develop mobile applications using JavaScript."
    ),
    Question(
        questionText = "Which language is primarily used to write React Native apps?",
        options = listOf("Java", "Kotlin", "JavaScript", "Python"),
        correctAnswer = 2,
        explanation = "React Native uses JavaScript to build mobile apps."
    ),
    Question(
        questionText = "What is the role of the 'View' component in React Native?",
        options = listOf("To create a scrollable list", "To structure and style content", "To handle user input", "To render images"),
        correctAnswer = 1,
        explanation = "The 'View' component is a container used to structure and style content in React Native."
    ),
    Question(
        questionText = "How can you style components in React Native?",
        options = listOf("Using CSS files", "Using inline styles or StyleSheet", "Using HTML attributes", "Using JavaScript methods"),
        correctAnswer = 1,
        explanation = "React Native uses the `StyleSheet` API or inline styles to style components."
    ),
    Question(
        questionText = "Which command is used to start a React Native project?",
        options = listOf("npm start", "npx react-native run", "npx create-react-native-app", "react-native init"),
        correctAnswer = 2,
        explanation = "You can start a new React Native project using 'npx create-react-native-app'."
    ),
    Question(
        questionText = "What does the 'useState' hook do in React Native?",
        options = listOf("Manages component state", "Handles animations", "Fetches data from APIs", "Renders lists efficiently"),
        correctAnswer = 0,
        explanation = "The 'useState' hook is used to manage state in functional components."
    ),
    Question(
        questionText = "What is the purpose of the 'FlatList' component?",
        options = listOf("To display grids", "To render large lists efficiently", "To create custom buttons", "To handle user gestures"),
        correctAnswer = 1,
        explanation = "The 'FlatList' component is used to render large lists efficiently in React Native."
    ),
    Question(
        questionText = "Which platform-specific code does React Native rely on?",
        options = listOf("HTML and CSS", "Java and Swift/Objective-C", "PHP and Ruby", "Node.js"),
        correctAnswer = 1,
        explanation = "React Native uses Java for Android and Swift/Objective-C for iOS under the hood."
    ),
    Question(
        questionText = "How can you handle navigation in a React Native app?",
        options = listOf("Using React Router", "Using React Native Navigation or React Navigation", "Using URL paths", "Using server-side routing"),
        correctAnswer = 1,
        explanation = "React Native apps use libraries like React Navigation or React Native Navigation for navigation."
    ),
    Question(
        questionText = "Which of the following is true about React Native?",
        options = listOf("It allows writing completely native code", "It allows code sharing between iOS and Android", "It is only for iOS apps", "It replaces Swift and Java"),
        correctAnswer = 1,
        explanation = "React Native allows sharing code between iOS and Android platforms, reducing development time."
    )
)