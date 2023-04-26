package com.example.testyourskills

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LeaderBoardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leader_board2)

        val leaderboardDataRecyclerView =findViewById<RecyclerView>(R.id.leaderboardRecyclerview)
        leaderboardDataRecyclerView.layoutManager=LinearLayoutManager(this, RecyclerView.VERTICAL, false)

     var leaderboardDataList =ArrayList<LeaderboardData>()
        val myDBHelperClass = DBHelperClass(this)
        leaderboardDataList= myDBHelperClass.getLeaderboardData()
        val leaderboardAdapter =LeaderBoardAdapter(leaderboardDataList)
        leaderboardDataRecyclerView.adapter=leaderboardAdapter


        var homeButton = findViewById<Button>(R.id.home_button)

        homeButton.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }




    }
}