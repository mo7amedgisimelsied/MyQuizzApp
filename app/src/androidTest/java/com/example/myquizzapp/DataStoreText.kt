package com.example.myquizzapp

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

// Set up DataStore for testing
val Context.dataStore by preferencesDataStore(name = "test_quiz_preferences")

@RunWith(AndroidJUnit4::class)
@SmallTest
class HighScoreInstrumentationTest {

    private lateinit var context: Context

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun testSaveHighScore() = runBlocking {
        val quizId = "1"
        val initialScore = 5
        val newScore = 10

        // Save the initial high score
        saveHighScore(context, quizId, initialScore)

        // Retrieve the high score
        val storedHighScore = getHighScore(context, quizId)

        // Check that the initial score is stored
        assertEquals(5, storedHighScore)

        // Save a higher score
        saveHighScore(context, quizId, newScore)

        // Retrieve the updated high score
        val updatedHighScore = getHighScore(context, quizId)

        // Check that the high score was updated correctly
        assertEquals(10, updatedHighScore)
    }

    @Test
    fun testGetHighScore() = runBlocking {
        val quizId = "2"
        val score = 7

        // Save the high score
        saveHighScore(context, quizId, score)

        // Retrieve the high score
        val highScore = getHighScore(context, quizId)

        // Assert that the retrieved score matches the saved high score
        assertEquals(score, highScore)
    }
}