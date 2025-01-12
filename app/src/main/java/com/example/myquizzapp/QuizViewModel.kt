package com.example.myquizzapp

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myquizzapp.ui.theme.correct_answer
import com.example.myquizzapp.ui.theme.wrong_answer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// ViewModel to manage the state and logic for the app.
class QuizViewModel : ViewModel() {
    // MutableLiveData to hold the list of questions
    val questionsList = MutableLiveData<List<Question>>()

    // LiveData that keeps track of the user's score.
    val _score = MutableLiveData(0)
    val score: LiveData<Int> = _score

    // LiveData to store the explanation for the current question.
    private val _explain = MutableLiveData("")
    val explain: LiveData<String> = _explain

    // LiveData to determine whether to show the explanation or not.
    val _showExplanation = MutableLiveData(false)
    val showExplanation: LiveData<Boolean> = _showExplanation

    // LiveData to store the background colors of the options.
    private val _optionsColors = MutableLiveData(mutableListOf(Color.White, Color.White, Color.White, Color.White))
    val optionsColors: MutableLiveData<MutableList<Color>> = _optionsColors

    /*
     * `isAnswered` is a list of booleans that tracks whether each question has been answered.
     *  By default, all values are `false` and set to `true` when user submits their answer.
     */
    var isAnswered = mutableListOf<Boolean>()

    // Function to fetch questions from the database using QuestionDao
    fun fetchQuestions(questionDao: QuestionDao, quizId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val questions = questionDao.getQuestionsByQuizId(quizId)
            isAnswered.clear()
            isAnswered.addAll(List(questions.size) { false })
            questionsList.postValue(questions)
        }
    }

    // Evaluates user answer and updates the UI accordingly.
    fun checkAnswer(userAnswer: Int, index: Int) {
        val questions = questionsList.value ?: return
        val updatedColors = mutableListOf(Color.White, Color.White, Color.White, Color.White)

        // If the user's answer is correct:
        if (userAnswer == questions[index].correctAnswer) {
            // Highlight the selected option in green to indicate correctness.
            updatedColors[userAnswer] = correct_answer

            // Hide the explanation since the answer is correct.
            _showExplanation.postValue(false)

            // Increment the score only if the question has not already been answered correctly.
            if (!isAnswered[index]) {
                _score.postValue((_score.value ?: 0) + 1)
                isAnswered[index] = true
            }
        }
        // If the answer is incorrect, display the explanation for the question.
        else {
            _explain.postValue(questions[index].explanation)
            _showExplanation.postValue(true)
            isAnswered[index] = true // To prevent user from switching their answer after seeing the correct one.
            updatedColors[userAnswer] = wrong_answer // Highlight the selected option in red to indicate incorrectness.
            updatedColors[questions[index].correctAnswer] = correct_answer // Highlight the correct option in green.
        }
        _optionsColors.postValue(updatedColors)
    }

    // Resets the colors of the answer options to ensure they always start fresh for each new question, avoiding confusion.
    fun resetColors() {
        _optionsColors.postValue(
            mutableListOf(Color.White, Color.White, Color.White, Color.White)
        )
    }

    fun clearViewModel(){
        _score.postValue(0)
        _explain.postValue("")
        _showExplanation.postValue(false)
        _optionsColors.postValue(mutableListOf(Color.White, Color.White, Color.White, Color.White))
    }
}