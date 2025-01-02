package com.example.myquizzapp

import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.junit.Assert.assertEquals
import org.junit.Test

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE, sdk = [35])
class QuizViewModelTest {

    @Test
    fun setQuestions_updatesQuestionsList() {
        val viewModel = QuizViewModel()
        val questions = listOf(
            Question(
                questionText = "Sample question?",
                options = listOf("Option 1", "Option 2"),
                correctAnswer = 0,
                explanation = "Sample explanation"
            )
        )

        viewModel.setQuestions(questions)
        assertEquals(questions, viewModel.getQuestions())
    }
}
