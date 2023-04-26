package com.example.testyourskills

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.net.URI

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val score= intent.getIntExtra("score",0)
        val scoretext = findViewById<TextView>(R.id.textViewScore)
        val name = intent.getStringExtra("name")
        val category = intent.getStringExtra("category")

        val myDBHelperClass = DBHelperClass(this)
        myDBHelperClass.insertMyLeaderboard(name,category, score.toString())


        scoretext.text = "You have finished the ${category} quiz ${name}, your score is ${score}"

        val homebutton = findViewById<Button>(R.id.homebutton)

        homebutton.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val sharebutton = findViewById<Button>(R.id.shareButton)

        sharebutton.setOnClickListener{
            val emailID = "vxs19651@gmail.com"
            val emailIntent = Intent(Intent.ACTION_SEND)
            val subject:String = "Your score in ${category} exam"
            val message:String = "I have taken ${category} exam and scored ${score} marks in the exam "
            emailIntent.action = Intent.ACTION_SEND
            emailIntent.data = Uri.parse("mailto:")
            emailIntent.type = "text/plain"
            emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(emailID))
            emailIntent.putExtra(Intent.EXTRA_SUBJECT,subject)
            emailIntent.putExtra(Intent.EXTRA_TEXT,message)

            startActivity(Intent.createChooser(emailIntent,"Choose email program"))

        }


    }
}


