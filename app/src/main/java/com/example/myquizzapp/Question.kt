package com.example.myquizzapp

import kotlinx.serialization.Serializable

// Represents a question with its text, options, correct answer, and explanation.
@Serializable
data class Question(
    val questionText: String,
    val options: List<String>,
    val correctAnswer: Int,
    val explanation: String
)