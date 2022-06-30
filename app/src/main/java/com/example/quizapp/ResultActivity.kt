package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val userName = intent.getStringExtra(Constants.USER_NAME)
        val tv_name = findViewById<TextView>(R.id.tv_name)
        tv_name.text = userName

        val correctAnswer = intent.getStringExtra(Constants.CORRECT_ANSWERS)
        val totalQuestions = intent.getStringExtra(Constants.TOTAL_QUESTIONS)

        val tv_score = findViewById<TextView>(R.id.tv_score)
        tv_score.text = "Twój wynik to $correctAnswer z $totalQuestions"

        val btn_finish = findViewById<Button>(R.id.btn_finish)
        btn_finish.setOnClickListener{
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
    }
}