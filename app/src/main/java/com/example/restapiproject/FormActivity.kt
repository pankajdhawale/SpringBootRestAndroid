package com.example.restapiproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.restapiproject.databinding.ActivityFormBinding
import com.example.restapiproject.model.Employee
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Handle system bars insets (Edge-to-Edge design)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Button click listener to submit form
        binding.empBtn.setOnClickListener {
            val empName = binding.empName.text.toString()
            val empSalaryStr = binding.empSalary.text.toString()
            val empCity = binding.empCity.text.toString()

            // Check if salary is empty or not a valid number
            val empSalary = empSalaryStr.toDoubleOrNull()
            if (empName.isBlank() || empSalary == null || empCity.isBlank()) {
                Toast.makeText(this, "Please fill out all fields correctly", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            Toast.makeText(this, "Form Info: Name: $empName, Salary: $empSalary, City: $empCity", Toast.LENGTH_LONG).show()

            // Create a new employee object
            val newEmployee = Employee(
                emp_name = empName,
                emp_salary = empSalary,
                emp_city = empCity
            )

            // Call the Retrofit function to create a new employee
            RetrofitClient.instance.createEmployee(newEmployee).enqueue(object : Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    if (response.isSuccessful) {
                        val message = response.body()
                        Toast.makeText(this@FormActivity, "Employee created successfully: $message", Toast.LENGTH_LONG).show()
                        Log.d("FormActivity", "Employee created successfully: $message")
                    } else {
                        val errorMessage = response.errorBody()?.string()
                        Toast.makeText(this@FormActivity, "Failed to create employee: $errorMessage", Toast.LENGTH_LONG).show()
                        Log.e("FormActivity", "Failed to create employee: $errorMessage")
                    }
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    Toast.makeText(this@FormActivity, "Error occurred: ${t.message}", Toast.LENGTH_LONG).show()
                    Log.e("FormActivity", "Error occurred: ${t.message}")
                }
            })
        }

    }
}
