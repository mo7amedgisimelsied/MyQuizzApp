package com.example.myquizzapp

import kotlinx.serialization.Serializable


@Serializable
data class Question(
    val questionText: String,
    val options: List<String>,
    val correctAnswer: Int,
    val explanation: String
)