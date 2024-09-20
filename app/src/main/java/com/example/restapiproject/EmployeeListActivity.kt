package com.example.restapiproject

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restapiproject.Adapter.EmployeeAdapter
import com.example.restapiproject.databinding.ActivityEmployeeListBinding
import com.example.restapiproject.model.Employee
import retrofit2.Call
import retrofit2.Response

class EmployeeListActivity : AppCompatActivity() {
    private lateinit var binding:ActivityEmployeeListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityEmployeeListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.empRecy.layoutManager=LinearLayoutManager(this)
        loadEmployees()
    }

    private fun loadEmployees() {
        RetrofitClient.instance.getAllEmployees().enqueue(object:retrofit2.Callback<List<Employee>>{
            override fun onFailure(call: Call<List<Employee>>, t: Throwable) {
                Toast.makeText(baseContext,"Fail to fetch data",Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<List<Employee>>,
                response: Response<List<Employee>>
            ) {
               if(response.isSuccessful)
               {
                   val employeeList=response.body()
                   populateListView(employeeList)

               }
            }
        })
    }

    private fun populateListView(employeeList: List<Employee>?) {
        if (employeeList != null) {
           EmployeeAdapter(employeeList)
            binding.empRecy.adapter=EmployeeAdapter(employeeList)
        }
    }
}