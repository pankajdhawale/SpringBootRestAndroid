package com.example.restapiproject.model

import android.text.Editable

data class Employee(
    val emp_name:String,
    val emp_salary: Double,
    val emp_city:String) {

    val id: Any
        get() {
            TODO()
        }
}
