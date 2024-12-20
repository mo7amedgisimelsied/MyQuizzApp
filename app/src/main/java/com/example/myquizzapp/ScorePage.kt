package com.example.myquizzapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun ScorePage(navController: NavHostController) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize().padding(20.dp)

    ){
        Text(
            fontSize = 30.sp,
            text = "Your Score: $5"
        )
        Button(onClick = {
            navController.navigate("Home")
        }) {
            Text(text = "Back to Home")
        }
    }


}

@Preview(showBackground = true)
@Composable
fun ScorePagePreview() {

}