package com.example.testyourskills

import java.io.Serializable

data class Question (



    val description: String = "",
    val option1: String="",
    val option2: String="",
    val option3: String="",
    val option4: String="",
    val correctAnswer:Int=0

): Serializable




