package com.example.myquizzapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuizViewModel : ViewModel() {

    val questionsList = MutableLiveData<List<Question>>()
    fun setQuestions(quizQuestions: List<Question>) {
        questionsList.value = quizQuestions
    }

}