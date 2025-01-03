package com.example.myquizzapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

// Data Access Object (DAO) for the Question entity.
@Dao
interface QuestionDao {

    @Insert
    suspend fun insertQuestions(questions: List<Question>)

    @Query("SELECT * FROM questions WHERE quizId = :quizId")
    suspend fun getQuestionsByQuizId(quizId: String): List<Question>

}