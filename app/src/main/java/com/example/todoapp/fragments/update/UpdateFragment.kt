package com.example.todoapp.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todoapp.R
import com.example.todoapp.model.TaskToDo
import com.example.todoapp.viewmodel.TaskViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*
import java.util.*

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mTaskViewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mTaskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        view.updateTaskName.setText(args.currentTask.taskName)
        view.updateTaskDesc.setText(args.currentTask.desc)

        view.updateTaskBtn.setOnClickListener {
            updateItem()
        }

        // Add menu
        setHasOptionsMenu(true)

        return view

    }

    private fun updateItem(){
        val name = updateTaskName.text.toString()
        val desc = updateTaskDesc.text.toString()

        if(inputCheck(name, desc)){
            // Create Task Object
            val currentTime: Date = Calendar.getInstance().time
            val updateTask = TaskToDo(args.currentTask.id, name, currentTime.toString(), desc, 0)
            // Update current Task
            mTaskViewModel.updateTask(updateTask)
            Toast.makeText(requireContext(), "Task Updated Successfully!", Toast.LENGTH_LONG).show()
            // Navigate Back
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "Please Fill All Fields!", Toast.LENGTH_LONG).show()
        }



    }
    private fun inputCheck(taskName: String, desc: String): Boolean{
        return !(TextUtils.isEmpty(taskName) && TextUtils.isEmpty(desc))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete){
            deleteTask()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteTask() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_, _ ->
            mTaskViewModel.deleteTask(args.currentTask)
            Toast.makeText(requireContext(),
                "Successfully Deleted: ${args.currentTask.taskName}?",
                Toast.LENGTH_LONG).show()

            // Back to List
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No"){_, _ ->}

        builder.setTitle("Delete ${args.currentTask.taskName}?")
        builder.setMessage("Are you sure you want to Delete ${args.currentTask.taskName}")
        builder.create().show()


    }
}