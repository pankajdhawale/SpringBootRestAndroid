package com.example.restapiproject.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.restapiproject.R
import com.example.restapiproject.model.Employee

class EmployeeAdapter(private val employeeList: List<Employee>) : RecyclerView.Adapter<EmployeeAdapter.UserViewHolder>() {

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name:TextView=itemView.findViewById(R.id.tv_name)
        val city:TextView=itemView.findViewById(R.id.tv_city)
        val salary:TextView=itemView.findViewById(R.id.tv_salary)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_employee_item, parent, false)
        return UserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
            val currentItem=employeeList[position]
        holder.name.text=currentItem.emp_name
        holder.city.text=currentItem.emp_city
        holder.salary.text= currentItem.emp_salary.toString()
    }
    override fun getItemCount() =employeeList.size

    }


