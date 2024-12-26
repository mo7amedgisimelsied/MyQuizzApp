package com.example.myquizzapp

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking


val Context.dataStore by preferencesDataStore(name = "quiz_preferences")

object PreferencesKeys {
    fun highScoreKey(quizId: String): Preferences.Key<Int> {
        return intPreferencesKey("high_score_$quizId")
    }
}

fun saveHighScore(context: Context, quizId: String, score: Int) {
    runBlocking {
        context.dataStore.edit { preferences ->
            val highScoreKey = PreferencesKeys.highScoreKey(quizId)
            val currentHighScore = preferences[highScoreKey] ?: 0
            if (score > currentHighScore) {
                preferences[highScoreKey] = score
            }
        }
    }
}


fun getHighScore(context: Context, quizId: String): Int {
    return runBlocking {
        val highScoreKey = PreferencesKeys.highScoreKey(quizId)
        context.dataStore.data.first()[highScoreKey] ?: 0
    }
}



@Composable
fun ScorePage(context: Context, currentScore: Int, navController: NavHostController, quizId: String) {
    var highScore by remember { mutableStateOf(getHighScore(context, quizId)) }

    if (currentScore > highScore) {
        saveHighScore(context = context, quizId = quizId, score = currentScore)
        highScore = currentScore
    }

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF000814))
            .padding(20.dp)

    ){
        Text(
            color = Color.White,
            fontSize = 30.sp,
            text = "Your Score: $currentScore"
        )

        Text(
            color = Color.White,
            text = "High Score: $highScore")

        Spacer(Modifier.padding(10.dp))

        Button(onClick = {
            navController.navigate("Home")
        },
            colors = ButtonDefaults.buttonColors(Color(0xFF7b2cbf)),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                color = Color.White,
                text = "Back to Home")

        }


    }


}