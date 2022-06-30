package com.example.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import kotlin.math.log

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPosition:Int = 1
    private var mQuestionsList: ArrayList<Question>? = null
    private var mSelectedPosition:Int = 0
    private var mCorrectAnswer: Int = 0
    private var mUserName :String? = null

//    val tv_option_one = findViewById<TextView>(R.id.tv_option_one)
//    val tv_option_two = findViewById<TextView>(R.id.tv_option_two)
//    val tv_option_three = findViewById<TextView>(R.id.tv_option_three)
//    val tv_option_four = findViewById<TextView>(R.id.tv_option_four)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)


        mUserName = intent.getStringExtra(Constants.USER_NAME)

        mQuestionsList = Constants.getQuestions()

        val pb_progress = findViewById<ProgressBar>(R.id.progressBar)
        pb_progress.max = mQuestionsList!!.size

        setQuestion()

        val tv_option_one = findViewById<TextView>(R.id.tv_option_one)
        val tv_option_two = findViewById<TextView>(R.id.tv_option_two)
        val tv_option_three = findViewById<TextView>(R.id.tv_option_three)
        val tv_option_four = findViewById<TextView>(R.id.tv_option_four)

        tv_option_one.setOnClickListener(this)
        tv_option_two.setOnClickListener(this)
        tv_option_three.setOnClickListener(this)
        tv_option_four.setOnClickListener(this)

        val btn_submit = findViewById<Button>(R.id.btn_submit)
        btn_submit.setOnClickListener(this)


    }

    private fun setQuestion(){

        val question = mQuestionsList!![mCurrentPosition-1]

        defaultOptionsView()

        if (mCurrentPosition == mQuestionsList!!.size){
            val btn_submit = findViewById<Button>(R.id.btn_submit)
            btn_submit.text = "ZAKOŃCZ"
        }else{
            val btn_submit = findViewById<Button>(R.id.btn_submit)
            btn_submit.text = "POTWIERDZ"
        }

        val progresBar = findViewById<ProgressBar>(R.id.progressBar)
        progresBar.progress = mCurrentPosition
        val tv_progress = findViewById<TextView>(R.id.tv_progress)
        tv_progress.text = "$mCurrentPosition"+"/"+mQuestionsList!!.size

        val tv_question = findViewById<TextView>(R.id.tv_question)
        tv_question.text = question!!.question

        val tv_image = findViewById<ImageView>(R.id.tv_image)
        tv_image.setImageResource(question.image)

        val tv_option_one = findViewById<TextView>(R.id.tv_option_one)
        tv_option_one.text = question.optionOne

        val tv_option_two = findViewById<TextView>(R.id.tv_option_two)
        tv_option_two.text = question.optionTwo

        val tv_option_three = findViewById<TextView>(R.id.tv_option_three)
        tv_option_three.text = question.optionThree

        val tv_option_four = findViewById<TextView>(R.id.tv_option_four)
        tv_option_four.text = question.optionFour

    }

    private fun defaultOptionsView(){
        val options = ArrayList<TextView>()
        val tv_option_one = findViewById<TextView>(R.id.tv_option_one)
        options.add(0,tv_option_one)

        val tv_option_two = findViewById<TextView>(R.id.tv_option_two)
        options.add(1,tv_option_two)

        val tv_option_three = findViewById<TextView>(R.id.tv_option_three)
        options.add(2,tv_option_three)

        val tv_option_four = findViewById<TextView>(R.id.tv_option_four)
        options.add(3,tv_option_four)

        for (option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.tv_option_one ->{
                var tv_option_one = findViewById<TextView>(R.id.tv_option_one)
                selectedOptionView(tv_option_one, 1)
            }
            R.id.tv_option_two ->{
                var tv_option_two = findViewById<TextView>(R.id.tv_option_two)
                selectedOptionView(tv_option_two, 2)
            }
            R.id.tv_option_three ->{
                var tv_option_three = findViewById<TextView>(R.id.tv_option_three)
                selectedOptionView(tv_option_three, 3)
            }
            R.id.tv_option_four ->{
                var tv_option_four = findViewById<TextView>(R.id.tv_option_four)
                selectedOptionView(tv_option_four, 4)
            }
            R.id.btn_submit ->{
                if (mSelectedPosition == 0){
                    mCurrentPosition ++

                    when{
                        mCurrentPosition <= mQuestionsList!!.size ->{
                            setQuestion()
                        }else -> {
                            val intent = Intent(this, ResultActivity::class.java)
                            intent.putExtra(Constants.USER_NAME, mUserName)
                            intent.putExtra(Constants.CORRECT_ANSWERS,mCorrectAnswer.toString())
                            intent.putExtra(Constants.TOTAL_QUESTIONS,mQuestionsList!!.size.toString())
                            startActivity(intent)
                            finish()
                        }
                    }
                }else{
                    val question = mQuestionsList?.get(mCurrentPosition-1)
                    if(question!!.correctAnswer != mSelectedPosition){
                        answerView(mSelectedPosition,R.drawable.wrong_option_border_bg)
                    }else{
                        mCorrectAnswer++
                    }
                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                    if(mCurrentPosition == mQuestionsList!!.size){
                        val btn_submit = findViewById<Button>(R.id.btn_submit)
                        btn_submit.text = "ZAKOŃCZ"
                    }else{
                        val btn_submit = findViewById<Button>(R.id.btn_submit)
                        btn_submit.text = "NASTĘPNE PYTANIE"
                    }
                    mSelectedPosition = 0

                }
            }

        }
    }

    private fun answerView(answer: Int, drawableView: Int){
        when(answer){
            1 ->{
                var tv_option_one = findViewById<TextView>(R.id.tv_option_one)
                tv_option_one.background = ContextCompat.getDrawable(
                    this,drawableView
                )
            }
            2 ->{
                var tv_option_two = findViewById<TextView>(R.id.tv_option_two)
                tv_option_two.background = ContextCompat.getDrawable(
                    this,drawableView
                )
            }
            3 ->{
                var tv_option_three = findViewById<TextView>(R.id.tv_option_three)
                tv_option_three.background = ContextCompat.getDrawable(
                    this,drawableView
                )
            }
            4 ->{
                var tv_option_four = findViewById<TextView>(R.id.tv_option_four)
                tv_option_four.background = ContextCompat.getDrawable(
                    this,drawableView
                )
            }
        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int){
        defaultOptionsView()
        mSelectedPosition  = selectedOptionNum

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface,Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_border_bg)
    }
}