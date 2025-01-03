package com.example.myquizzapp

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// Fills the database with questions once the app is launched.
fun fillDatabase(context: Context) {
    val database = AppDatabase.getDatabase(context)
    val questionDao = database.questionDao()

    CoroutineScope(Dispatchers.IO).launch {
        // condition to prevent filling the database multiple times
        if (questionDao.getQuestionsByQuizId("1").isEmpty() && questionDao.getQuestionsByQuizId("2").isEmpty()) {
            // Questions for Jetpack Compose
            val jetpackComposeQs = listOf(
                Question(
                    quizId = "1",
                    questionText = "What is Jetpack Compose?",
                    option1 = "A modern toolkit for building native UIs",
                    option2 = "A database management tool",
                    option3 = "A backend development framework",
                    option4 = "A JavaScript library",
                    correctAnswer = 0,
                    explanation = "Jetpack Compose is a modern toolkit for building native user interfaces in Android."
                ),
                Question(
                    quizId = "1",
                    questionText = "Which language is primarily used for Jetpack Compose?",
                    option1 = "Java",
                    option2 = "Kotlin",
                    option3 = "Python",
                    option4 = "C++",
                    correctAnswer = 1,
                    explanation = "Jetpack Compose is built with Kotlin and uses it as the primary language."
                ),
                Question(
                    quizId = "1",
                    questionText = "What is the primary advantage of Jetpack Compose?",
                    option1 = "Declarative UI",
                    option2 = "High performance in database queries",
                    option3 = "Seamless integration with JavaScript",
                    option4 = "Built-in machine learning capabilities",
                    correctAnswer = 0,
                    explanation = "Jetpack Compose uses a declarative approach to building UI, making it simpler and more intuitive."
                ),
                Question(
                    quizId = "1",
                    questionText = "Which function is used to define a composable in Jetpack Compose?",
                    option1 = "@State",
                    option2 = "fun",
                    option3 = "@Composable",
                    option4 = "setContent",
                    correctAnswer = 2,
                    explanation = "In Jetpack Compose, functions annotated with @Composable define composables."
                ),
                Question(
                    quizId = "1",
                    questionText = "How do you manage state in Jetpack Compose?",
                    option1 = "Using ViewModel",
                    option2 = "Using LiveData",
                    option3 = "Using MutableState",
                    option4 = "All of the above",
                    correctAnswer = 3,
                    explanation = "You can manage state in Jetpack Compose using ViewModel, LiveData, or MutableState, depending on the context."
                ),
                Question(
                    quizId = "1",
                    questionText = "What is the purpose of remember in Jetpack Compose?",
                    option1 = "To save state across recompositions",
                    option2 = "To create animations",
                    option3 = "To define a navigation route",
                    option4 = "To manage dependencies",
                    correctAnswer = 0,
                    explanation = "The remember function is used to retain state across recompositions in a composable."
                ),
                Question(
                    quizId = "1",
                    questionText = "What is the purpose of LazyColumn?",
                    option1 = "To create a grid layout",
                    option2 = "To display a single item",
                    option3 = "To efficiently display a scrollable list",
                    option4 = "To add padding to a composable",
                    correctAnswer = 2,
                    explanation = "LazyColumn is used for efficiently displaying a scrollable list of items in Jetpack Compose."
                ),
                Question(
                    quizId = "1",
                    questionText = "Which Compose component is used for navigation?",
                    option1 = "NavController",
                    option2 = "NavHost",
                    option3 = "NavGraph",
                    option4 = "All of the above",
                    correctAnswer = 3,
                    explanation = "Jetpack Compose navigation involves NavController, NavHost, and NavGraph to handle navigation between screens."
                ),
                Question(
                    quizId = "1",
                    questionText = "What is the primary purpose of Modifier in Jetpack Compose?",
                    option1 = "To style and position elements",
                    option2 = "To create animations",
                    option3 = "To handle navigation",
                    option4 = "To manage state",
                    correctAnswer = 0,
                    explanation = "Modifiers in Jetpack Compose are used to style, position, and add behavior to composables."
                ),
                Question(
                    quizId = "1",
                    questionText = "Which of these is NOT a core Compose UI element?",
                    option1 = "Row",
                    option2 = "Column",
                    option3 = "Stack",
                    option4 = "RecyclerView",
                    correctAnswer = 3,
                    explanation = "RecyclerView is not a core Jetpack Compose UI element; it's a legacy View-based component."
                )
            )
            // Questions for React Native
            val reactNativeQs = listOf(
                Question(
                    quizId = "2",
                    questionText = "What is React Native?",
                    option1 = "A library for building backend applications",
                    option2 = "A framework for building native apps using JavaScript",
                    option3 = "A database management tool",
                    option4 = "A tool for creating serverless APIs",
                    correctAnswer = 1,
                    explanation = "React Native is a framework for building native mobile apps using JavaScript and React."
                ),
                Question(
                    quizId = "2",
                    questionText = "Which programming language is used in React Native?",
                    option1 = "Kotlin",
                    option2 = "Swift",
                    option3 = "JavaScript",
                    option4 = "Python",
                    correctAnswer = 2,
                    explanation = "React Native primarily uses JavaScript for application development."
                ),
                Question(
                    quizId = "2",
                    questionText = "How does React Native render UI components?",
                    option1 = "By using native components",
                    option2 = "By using web components",
                    option3 = "By using virtual components",
                    option4 = "By using server-side rendering",
                    correctAnswer = 0,
                    explanation = "React Native renders UI components using native components, providing a native-like experience."
                ),
                Question(
                    quizId = "2",
                    questionText = "What is the primary purpose of Expo in React Native?",
                    option1 = "To manage dependencies",
                    option2 = "To provide a development environment and tools",
                    option3 = "To replace the React library",
                    option4 = "To handle state management",
                    correctAnswer = 1,
                    explanation = "Expo provides tools and a development environment to simplify React Native app development."
                ),
                Question(
                    quizId = "2",
                    questionText = "Which command is used to create a new React Native project?",
                    option1 = "npx create-react-native-app",
                    option2 = "npm init native-app",
                    option3 = "react-native new project",
                    option4 = "expo init project",
                    correctAnswer = 0,
                    explanation = "The command `npx create-react-native-app` is commonly used to create a new React Native project."
                ),
                Question(
                    quizId = "2",
                    questionText = "What is a core component in React Native?",
                    option1 = "Component",
                    option2 = "View",
                    option3 = "Redux",
                    option4 = "Controller",
                    correctAnswer = 1,
                    explanation = "View is a core component in React Native, used to create UI elements."
                ),
                Question(
                    quizId = "2",
                    questionText = "Which of the following is NOT a valid React Native component?",
                    option1 = "ScrollView",
                    option2 = "TextInput",
                    option3 = "GridView",
                    option4 = "Image",
                    correctAnswer = 2,
                    explanation = "GridView is not a valid React Native component; it uses FlatList or other alternatives for lists."
                ),
                Question(
                    quizId = "2",
                    questionText = "What is the role of StyleSheet in React Native?",
                    option1 = "To handle navigation",
                    option2 = "To define and apply styles to components",
                    option3 = "To connect to APIs",
                    option4 = "To manage app state",
                    correctAnswer = 1,
                    explanation = "StyleSheet is used in React Native to define and apply styles to components."
                ),
                Question(
                    quizId = "2",
                    questionText = "How do you handle navigation in React Native?",
                    option1 = "Using React Navigation library",
                    option2 = "Using Redux",
                    option3 = "Using CSS",
                    option4 = "Using DOM elements",
                    correctAnswer = 0,
                    explanation = "React Navigation is a popular library for handling navigation in React Native apps."
                ),
                Question(
                    quizId = "2",
                    questionText = "What is the primary advantage of React Native?",
                    option1 = "Cross-platform development",
                    option2 = "Server-side rendering",
                    option3 = "Database integration",
                    option4 = "Backend development",
                    correctAnswer = 0,
                    explanation = "React Native enables cross-platform development, allowing developers to build apps for both Android and iOS with a single codebase."
                )
            )

            // insert the questions using question dao
            questionDao.insertQuestions(jetpackComposeQs)
            questionDao.insertQuestions(reactNativeQs)
        }
    }
}
