package com.example.testyourskills

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class TestStartingScreenActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test_starting_activity)
        val buttonStartButton = findViewById<Button>(R.id.buttonStartQuiz)
        buttonStartButton.setOnClickListener {

            startQuiz()


        }

    }

    private fun startQuiz() {
        val editTextName = findViewById<EditText>(R.id.editTextName)
        if (editTextName.text.toString().isEmpty()) {
            Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show()
        } else {
            val intent = Intent(this, CategoryListViewActivity::class.java)
            intent.putExtra("name",editTextName.text.toString())
            startActivity(intent)

        }
    }
}