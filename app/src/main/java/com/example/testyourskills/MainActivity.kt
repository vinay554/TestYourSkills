package com.example.testyourskills

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val myInflater = menuInflater
        myInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId)
        {
            R.id.start_quiz-> {
                val intent = Intent(this, TestStartingScreenActivity::class.java)
                startActivity(intent)

            }
            R.id.add_quiz -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

            }
            R.id.leader_board -> {
                val intent = Intent(this, LeaderBoardActivity::class.java)
                startActivity(intent)
            }
            R.id.quit -> {
                moveTaskToBack(true)

            }
            R.id.homeMenuItem -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }

        return true
    } // menu select listner


}