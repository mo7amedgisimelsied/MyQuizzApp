package com.example.myquizzapp

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class DatabaseTest {

    private lateinit var database: AppDatabase
    private lateinit var questionDao: QuestionDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        questionDao = database.questionDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun testInsertAndRetrieveQuestions() = runTest {

        val questions = listOf(
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
                questionText = "Which language does Jetpack Compose use?",
                option1 = "Kotlin",
                option2 = "Java",
                option3 = "Python",
                option4 = "C++",
                correctAnswer = 0,
                explanation = "Jetpack Compose is written in Kotlin."
            )
        )


        questionDao.insertQuestions(questions)
        val retrievedQuestions = questionDao.getQuestionsByQuizId("1")


        assertEquals(2, retrievedQuestions.size)
        assertEquals("What is Jetpack Compose?", retrievedQuestions[0].questionText)
    }

}