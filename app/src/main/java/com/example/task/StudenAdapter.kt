package com.example.task

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StudentAdapter : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    var students: List<Student> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_student, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]
        holder.textFirstName.text = student.firstName
        holder.textLastName.text = student.lastName
        holder.textPersonalNumber.text = student.personalNumber
        holder.textEmail.text = student.email
    }

    override fun getItemCount(): Int {
        return students.size
    }

    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textFirstName: TextView = itemView.findViewById(R.id.textFirstName)
        val textLastName: TextView = itemView.findViewById(R.id.textLastName)
        val textPersonalNumber: TextView = itemView.findViewById(R.id.textPersonalNumber)
        val textEmail: TextView = itemView.findViewById(R.id.textEmail)
    }
}
