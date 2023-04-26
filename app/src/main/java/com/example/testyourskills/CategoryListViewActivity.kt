package com.example.testyourskills

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class CategoryListViewActivity : AppCompatActivity() {

    private var quizList = ArrayList<String>()
    private lateinit var  categorychoosen:String

    var firebaseRef = FirebaseDatabase.getInstance().getReference()
    val mycontext:Context = this

    val questionsList = ArrayList<Question>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_list_view)

        val categoryListView = findViewById<ListView>(R.id.categoryListView)
        val nameText = findViewById<TextView>(R.id.nametextView)
        nameText.text= intent.getStringExtra("name")


        val categoryListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {


                for(i in snapshot.child("Quiz").children){

                   // val cat =  i.key.toString()
                    quizList.add(i.key.toString())
                    //Log.d(" cffffffffffffffffffff",quizList.toString() )
                    categoryListView.adapter = ArrayAdapter<String>(mycontext,android.R.layout.simple_list_item_1,quizList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }



        }
       // quizList.add("test")
        firebaseRef.addListenerForSingleValueEvent(categoryListener)

        categoryListView.setOnItemClickListener{parent,view,position,id ->
             categorychoosen = quizList[position]

            val questionListener = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {




                    for(i in snapshot.child("Quiz").child(categorychoosen).children){

                        val description = i.child("description").value.toString()
                        val option1 = i.child("option1").value.toString()
                        val option2 = i.child("option2").value.toString()
                        val option3 = i.child("option3").value.toString()
                        val option4 = i.child("option4").value.toString()
                        val correctAnswer = i.child("correctAnswer").value.toString().toInt()


                        questionsList.add(Question(description,option1,option2,option3,option4,correctAnswer))

                        //categoryListView.adapter = ArrayAdapter<String>(mycontext,android.R.layout.simple_list_item_1,quizList)
                    }

                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }



            }
            val intent = Intent(mycontext,QuizActivity::class.java)
            intent.putExtra("questions",questionsList)
            intent.putExtra("name",nameText.text)
            intent.putExtra("category",categorychoosen)
            //Log.d("sdfghjkjbvcxcvbn",questionsList.toString())


            mycontext.startActivity(intent)
            firebaseRef.addListenerForSingleValueEvent(questionListener)



        }





    }
}