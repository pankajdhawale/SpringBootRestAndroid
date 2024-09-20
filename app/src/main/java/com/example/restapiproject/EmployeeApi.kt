package com.example.restapiproject

import com.example.restapiproject.model.Employee
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface EmployeeApi {
    // POST request to add a new employee
    @POST("employees")
    fun createEmployee(@Body employee: Employee): Call<String>

    // GET request to retrieve all employees
    @GET("employees")
    fun getAllEmployees(): Call<List<Employee>>

    // GET request to retrieve a specific employee by ID
    @GET("employees/{empid}")
    fun getEmployeeById(@Path("empid") empId: Int): Call<Employee>

    // PUT request to update an employee
    @PUT("employees/{empid}")
    fun updateEmployee(@Path("empid") empId: Long, @Body employee: Employee): Call<String>

    // DELETE request to delete an employee by ID
    @DELETE("employees/{empid}")
    fun deleteEmployee(@Path("empid") empId: Long): Call<String>

    // DELETE request to delete all employees
    @DELETE("employees")
    fun deleteAllEmployees():Call<String>

}