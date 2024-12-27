package com.example.myquizzapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuizViewModel : ViewModel() {
    // MutableLiveData to hold the list of questions
    val questionsList = MutableLiveData<List<Question>>()
    fun setQuestions(quizQuestions: List<Question>) {
        questionsList.value = quizQuestions
    }

}