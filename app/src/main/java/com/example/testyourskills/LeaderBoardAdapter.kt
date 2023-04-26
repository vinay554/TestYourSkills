package com.example.testyourskills

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LeaderBoardAdapter(val leaderboardData:ArrayList<LeaderboardData>) :RecyclerView.Adapter<LeaderBoardAdapter.MyViewHolder>(){




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.each_leaderboardentry,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
      return leaderboardData.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val data : LeaderboardData = leaderboardData[position]

        //holder.userIdText.text=data.id.toString()
        holder.nameText.text = "User name: ${data.name}"
        holder.categoryText.text = "Exam Category: ${data.category}"
        holder.scoreText.text= "Marks scored: ${data.score.toString()}"



    }

    inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var userIdText:TextView = itemView.findViewById(R.id.userid)
        var nameText:TextView = itemView.findViewById(R.id.username)
        var categoryText:TextView = itemView.findViewById(R.id.category)
        var scoreText:TextView = itemView.findViewById(R.id.score)



    }
}
