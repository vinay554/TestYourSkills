package com.example.testyourskills


import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import java.util.*
import kotlin.collections.ArrayList


class QuizActivity : AppCompatActivity() {

    private lateinit var scoreText:TextView
    private lateinit var timerText :TextView
    private lateinit var questionCountText:TextView
    private lateinit var questionText:TextView
    private lateinit var radioGroup:RadioGroup
    private lateinit var option1Text:RadioButton
    private lateinit var option2Text:RadioButton
    private lateinit var option3Text:RadioButton
    private lateinit var option4Text:RadioButton
    private lateinit var confirmButton:Button
    private lateinit var textColorDefaultRadiobutton:ColorStateList
    private lateinit var textColorDefaultCT:ColorStateList
    private var questionsList:List<Question>?=null
    private val COUNT_DOWN_IN_MILLIS:Long = 10000
    private var questionCounter:Int = 0
    private var questionTotalCount:Int = 0
    private var currentQuestion: Question? =null
    private var score:Int = 0
    private var answered:Boolean = false
    private var backPressedTime:Long = 0
    private var countDownTimer:CountDownTimer? = null
    private var timeLeftInMillis:Long =0
    private lateinit var textname:TextView
    private lateinit var nam:String
    private lateinit var categ:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        timerText = findViewById(R.id.textTimer)

        scoreText = findViewById(R.id.textViewScore)
        questionCountText = findViewById(R.id.textViewQuestionCount)
        questionText = findViewById(R.id.textViewQuestion)
        radioGroup = findViewById(R.id.radioGroup)
        option1Text = findViewById(R.id.radioButton1)
        option2Text = findViewById(R.id.radioButton2)
        option3Text = findViewById(R.id.radioButton3)
        option4Text = findViewById(R.id.radioButton4)
        confirmButton = findViewById(R.id.buttonConfirm)
        textColorDefaultRadiobutton = option1Text.textColors
        textColorDefaultCT=timerText.textColors
        questionsList = intent.getSerializableExtra("questions") as ArrayList<Question>
        questionTotalCount = questionsList!!.size
        textname = findViewById(R.id.textName)
        nam = intent.getStringExtra("name").toString()
        categ = intent.getStringExtra("category").toString()
        textname.text = "${nam} you are taking ${categ} test"

        showNextQuestion()

        confirmButton.setOnClickListener{
            if(!answered){
                if(option1Text.isChecked || option2Text.isChecked || option3Text.isChecked || option4Text.isChecked){
                    checkAnswer()
                }else{
                    val snackBar = Snackbar.make(findViewById(android.R.id.content), "Please select an answer", Snackbar.LENGTH_LONG)
                    snackBar.setAction("OK") {
                        snackBar.dismiss()
                    }
                    snackBar.show()

                }
            }else{
                showNextQuestion()
            }
        }
    }

    private fun showNextQuestion() {
        option1Text.setTextColor(textColorDefaultRadiobutton)
        option2Text.setTextColor(textColorDefaultRadiobutton)
        option3Text.setTextColor(textColorDefaultRadiobutton)
        option4Text.setTextColor(textColorDefaultRadiobutton)

        radioGroup.clearCheck()

        if(questionCounter<questionTotalCount){
            currentQuestion = questionsList?.get(questionCounter)

            questionText.text = currentQuestion!!.description
            option1Text.text = currentQuestion!!.option1
            option2Text.text = currentQuestion!!.option2
            option3Text.text = currentQuestion!!.option3
            option4Text.text = currentQuestion!!.option4
            questionCounter++

            questionCountText.text = "Question: $questionCounter / $questionTotalCount"
            answered = false
            confirmButton.text = "confirm"

            timeLeftInMillis=COUNT_DOWN_IN_MILLIS
            startCountDown()
        }else{
            
            finishQuiz()

        }

    }

    private fun startCountDown() {
        countDownTimer = object: CountDownTimer(timeLeftInMillis,1000){
            override fun onTick(millisUntilFinished:Long){
                timeLeftInMillis = millisUntilFinished
                updateCountDownText()
            }
            override fun onFinish() {

                timeLeftInMillis=0
                updateCountDownText()
                checkAnswer()


            }

        }.start()
    }

    private fun updateCountDownText() {
       val minutes:Int = ((timeLeftInMillis/1000)/60).toInt()
        val seconds:Int = ((timeLeftInMillis/1000)%60).toInt()
        val timeFormatted:String = String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds)
        timerText.text = timeFormatted

        if(timeLeftInMillis<5000){
            timerText.setTextColor(Color.RED)
        }else{
            timerText.setTextColor(textColorDefaultCT)
        }
    }

    private fun checkAnswer() {
        answered = true
        countDownTimer?.cancel()
        var answer:Int = -1

        if(option1Text.isChecked || option2Text.isChecked || option3Text.isChecked || option4Text.isChecked) {


            val radioButtonSelected: RadioButton = findViewById(radioGroup.checkedRadioButtonId)
            //var answer: Int = radioGroup.indexOfChild(radioButtonSelected) + 1
            answer = radioGroup.indexOfChild(radioButtonSelected) + 1
        }
        if(answer== currentQuestion!!.correctAnswer){
            Toast.makeText(this, "Woah...!!! That's correct", Toast.LENGTH_SHORT).show()
            score++
            scoreText.text= "Score: $score"

        }else if(answer==-1){
            Toast.makeText(this, "Oops!! You ran out of time...", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, "Alas...!!  Wrong answer", Toast.LENGTH_SHORT).show()
        }
        showSolution()

    }

    private fun showSolution() {
        option1Text.setTextColor(Color.RED)
        option2Text.setTextColor(Color.RED)
        option3Text.setTextColor(Color.RED)
        option4Text.setTextColor(Color.RED)

        when(currentQuestion?.correctAnswer){
            1->option1Text.setTextColor(Color.GREEN)
            2->option2Text.setTextColor(Color.GREEN)
            3->option3Text.setTextColor(Color.GREEN)
            4->option4Text.setTextColor(Color.GREEN)

        }
        if(questionCounter<questionTotalCount){
            confirmButton.text="Next Question"
        }else{
            confirmButton.text="Finish"
        }

    }

    private fun finishQuiz() {
        if( confirmButton.text=="Finish"){
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("score",score)
            intent.putExtra("name",nam)
            intent.putExtra("category",categ)
            startActivity(intent)
        }

     finish()
    }


    override fun onBackPressed() {
        if(backPressedTime+2000 > System.currentTimeMillis()){
        finish()
        }else{
            Toast.makeText(this,"Press back again to Finish",Toast.LENGTH_SHORT).show()
        }
        backPressedTime = System.currentTimeMillis()

    }

    override fun onDestroy() {
        super.onDestroy()
      /* if(countDownTimer !=null){
            countDownTimer.cancel()
        }*/
    }

}


