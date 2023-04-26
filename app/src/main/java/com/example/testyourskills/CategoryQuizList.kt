/*
package com.example.testyourskills

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore

class CategoryQuizList : AppCompatActivity() {

   // lateinit var adapter: QuizAdapter
    private var quizList = ArrayList<Quiz>()
    var firebaseRef = FirebaseDatabase.getInstance().getReference()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_quiz_list)

        val quizRecyclerView = findViewById<RecyclerView>(R.id.listQuizRecyclerview)


        quizRecyclerView.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL, false)

        val categoryListener = object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
               
                for(i in snapshot.child("Quiz").children){
                    
                   val cat =  i.key.toString()
                    quizList.add(Quiz(cat))
                }
                
                val quizAdapter = QuizAdapter(quizList)

                quizRecyclerView.adapter = quizAdapter
                

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        


        }
        firebaseRef.addListenerForSingleValueEvent(categoryListener)

    */
/*private fun setUpFireBase(){

        var cat:String?=intent.getStringExtra("CAT")
        if(cat != null){

        }
        var collectionReference = firebase.collection("Quiz")
        collectionReference.addSnapshotListener{value, error ->
            if(value == null || error != null){
                Toast.makeText(this,"Error fetching data",Toast.LENGTH_SHORT).show()
                return@addSnapshotListener
            }
            Log.d("DATA", value.toObjects(Quiz::class.java).toString())
            quizList.clear()
            quizList.addAll(value.toObjects(Quiz::class.java))
            adapter.notifyDataSetChanged()
        }
    }*//*






    }
}





*/
