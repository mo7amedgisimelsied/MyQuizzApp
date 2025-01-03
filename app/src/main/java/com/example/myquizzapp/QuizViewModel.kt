package com.example.myquizzapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuizViewModel : ViewModel() {
    // MutableLiveData to hold the list of questions
    val questionsList = MutableLiveData<List<Question>>()

    // Function to fetch questions from the database using QuestionDao
    fun fetchQuestions(questionDao: QuestionDao, quizId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val questions = questionDao.getQuestionsByQuizId(quizId)
            questionsList.postValue(questions)
        }
    }
}