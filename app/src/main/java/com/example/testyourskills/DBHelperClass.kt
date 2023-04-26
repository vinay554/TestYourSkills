package com.example.testyourskills

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import kotlin.properties.Delegates


class DBHelperClass (context: Context, ) : SQLiteOpenHelper(context, "LeaderboardDB", null, 1){

    override fun onCreate(LeaderboardDB: SQLiteDatabase?) {
        val dbExists : Unit? = LeaderboardDB?.execSQL("SELECT name FROM sqlite_master Where type='table' AND name ='MyLeaderboard'")
        if (LeaderboardDB != null)
        {
            if (dbExists != null)
            {
                LeaderboardDB.execSQL("create table MyLeaderboard (userID INTEGER primary Key AutoIncrement, userName text, examCategory text, score Integer)")
            }//if
        }//if
    }// oncreate

    override fun onUpgrade(LeaderboardDB: SQLiteDatabase?, p1: Int, p2: Int) {

        LeaderboardDB!!.execSQL("drop table if exists MyLeaderboard")
    }//onUpgrade

    fun insertMyLeaderboard(userName: String?,examCategory: String?, score: String?) : Long {

        val LeaderboardDB = this.writableDatabase
        val contentValues = ContentValues()

        //contentValues.put("userID", userID)
        contentValues.put("userName", userName)
        contentValues.put("examCategory", examCategory)
        contentValues.put("score", score)


        val result = LeaderboardDB.insert("MyLeaderboard", null, contentValues)


        return result

    } // insert

    /*fun deleteStudentData (studentID : Long) : Long {
        var result by Delegates.notNull<Long>()
        val scDB = this.writableDatabase

        val cursor: Cursor = scDB.rawQuery("Select * from Students where studentID =?", arrayOf(studentID.toString()))

        if(cursor.count > 0)
        {
            result = scDB.delete("Students", "studentID = ?", arrayOf(studentID.toString())).toLong()
        }
        cursor.close()
        return  result
    }*/

    /*fun updateStudentsData(studentID:String, studentFName:String, studentLName: String?, address:String?, phoneNumber: String?) : Long{


        var result by Delegates.notNull<Long>()
        val myDB = this.writableDatabase

        val contentValues = ContentValues()

        contentValues.put("studentName", studentFName)
        contentValues.put("studentLName", studentLName)
        contentValues.put("address", address)
        contentValues.put("phoneNumber", phoneNumber)

        val cursor = myDB.rawQuery("Select * from Students Where studentID =?", arrayOf(studentID))

        if(cursor.count > 0)
        {
            result = myDB.update("Students", contentValues, "studentID =?", arrayOf(studentID) ).toLong()
        }
        cursor.close()
        return result
    }*/

    @SuppressLint("Range")
    fun getLeaderboardData() : ArrayList<LeaderboardData> {
        val LeaderboardDB = this.readableDatabase
        val cursor : Cursor = LeaderboardDB.rawQuery("Select * from MyLeaderboard ORDER BY score DESC", null)

        val dataList = ArrayList<LeaderboardData>()
        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndex("userID"))
            val name = cursor.getString(cursor.getColumnIndex("userName"))
            val cat = cursor.getString(cursor.getColumnIndex("examCategory"))
            val score = cursor.getInt(cursor.getColumnIndex("score"))

            val data = LeaderboardData(id, name, cat,score)
            dataList.add(data)
        }
        return dataList

    }




}