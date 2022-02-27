package com.example.todoapp.fragments.add

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.todoapp.R
import com.example.todoapp.model.TaskToDo
import com.example.todoapp.viewmodel.TaskViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*
import java.util.*

class AddFragment : Fragment() {

    private lateinit var mTaskViewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_add, container, false)

        mTaskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        view.addTaskBtn.setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {
        val taskName = editTextTaskName.text.toString()
        val desc = editTextTaskDesc.text.toString()

        if(inputCheck(taskName, desc)){
            // Create Task Object
            val currentTime: Date = Calendar.getInstance().time
            val task = TaskToDo(0, taskName, currentTime.toString(), desc, 0)
            // Add data to Database
            mTaskViewModel.addTask(task)
            Toast.makeText(requireContext(), "Successfully added Task!", Toast.LENGTH_LONG).show()
            // Navigate back
            findNavController().navigate(R.id.action_addFragment_to_listFragment2)
        }else{
            Toast.makeText(requireContext(), "Please Fill All Fields!", Toast.LENGTH_LONG).show()
        }
    }
    private fun inputCheck(taskName: String, desc: String): Boolean{
        return !(TextUtils.isEmpty(taskName) && TextUtils.isEmpty(desc))
    }

}