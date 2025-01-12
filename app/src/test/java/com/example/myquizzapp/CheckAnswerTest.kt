package com.example.myquizzapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.ui.graphics.Color
import com.example.myquizzapp.ui.theme.correct_answer
import com.example.myquizzapp.ui.theme.wrong_answer
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CheckAnswerTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: QuizViewModel

    @Before
    fun setup() {
        viewModel = QuizViewModel()
    }

    @Test
    fun `checkAnswer updates score and colors correctly for a correct answer`() {

        val question = listOf(
            Question(
                quizId = "1",
                questionText = "Which language is primarily used for Jetpack Compose?",
                option1 = "Java",
                option2 = "Kotlin",
                option3 = "Python",
                option4 = "C++",
                correctAnswer = 1,
                explanation = "Jetpack Compose is built with Kotlin and uses it as the primary language."
            ))
        viewModel.questionsList.postValue(question)
        viewModel.isAnswered = List(question.size) { false }.toMutableList()


        viewModel.checkAnswer(userAnswer = 1, index = 0)

        assertEquals(1, viewModel.score.value)
        assertEquals(listOf(Color.White, correct_answer, Color.White, Color.White), viewModel.optionsColors.value)
        assertEquals(false, viewModel.showExplanation.value)
    }

    @Test
    fun `checkAnswer shows explanation and updates colors for an incorrect answer`() {

        val question = listOf(
            Question(
            quizId = "1",
            questionText = "Which language is primarily used for Jetpack Compose?",
            option1 = "Java",
            option2 = "Kotlin",
            option3 = "Python",
            option4 = "C++",
            correctAnswer = 1,
            explanation = "Jetpack Compose is built with Kotlin and uses it as the primary language."
        ))
        viewModel.questionsList.postValue(question)
        viewModel.isAnswered = List(question.size) { false }.toMutableList()

        viewModel.checkAnswer(userAnswer = 0, index = 0)

        assertEquals(0, viewModel.score.value)
        assertEquals(
            listOf(wrong_answer, correct_answer, Color.White, Color.White),
            viewModel.optionsColors.value
        )
        assertEquals("Jetpack Compose is built with Kotlin and uses it as the primary language.", viewModel.explain.value)
        assertEquals(true, viewModel.showExplanation.value)
    }

    @Test
    fun `checkAnswer does not increment score if question already answered`() {

        val question = listOf(
            Question(
                quizId = "1",
                questionText = "Which language is primarily used for Jetpack Compose?",
                option1 = "Java",
                option2 = "Kotlin",
                option3 = "Python",
                option4 = "C++",
                correctAnswer = 1,
                explanation = "Jetpack Compose is built with Kotlin and uses it as the primary language."
            ))
        viewModel.questionsList.postValue(question)
        viewModel.isAnswered = List(question.size) { false }.toMutableList()

        viewModel.checkAnswer(userAnswer = 1, index = 0) // First attempt
        viewModel.checkAnswer(userAnswer = 1, index = 0) // Second attempt

        assertEquals(1, viewModel.score.value) // Score should only increment once
    }

    @Test
    fun `checkAnswer resets colors when called on a fresh question`() {
        // Arrange
        val question = listOf(
            Question(
                quizId = "1",
                questionText = "Which language is primarily used for Jetpack Compose?",
                option1 = "Java",
                option2 = "Kotlin",
                option3 = "Python",
                option4 = "C++",
                correctAnswer = 1,
                explanation = "Jetpack Compose is built with Kotlin and uses it as the primary language."
            ))
        viewModel.questionsList.postValue(question)

        viewModel.resetColors()

        assertEquals(
            listOf(Color.White, Color.White, Color.White, Color.White),
            viewModel.optionsColors.value
        )
    }
}
