package com.cursokotlin.misnotas

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cursokotlin.misnotas.adapter.TaskAdapter
import com.cursokotlin.misnotas.databinding.ActivityMainBinding
import com.cursokotlin.misnotas.modelo.TaskEntity
import com.google.android.material.internal.ViewUtils.hideKeyboard
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var btnAddTask: Button
    lateinit var etTask: EditText
    lateinit var recyclerView: RecyclerView

    lateinit var tasks: MutableList<TaskEntity>
    lateinit var adapter: TaskAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        btnAddTask = binding.btnAddTask
        etTask = binding.etTask
        recyclerView = binding.rvTask

        tasks = ArrayList()
        getTask()

        btnAddTask.setOnClickListener {
            addTask(TaskEntity(name = etTask.text.toString()))
        }
    }

    private fun addTask(task: TaskEntity) {
        lifecycleScope.launch(Dispatchers.IO) {
            val id = MisNotasApp.database.taskDao().addTask(task)
            val recoveryTask = MisNotasApp.database.taskDao().getTaskById(id)

            withContext(Dispatchers.Main) {
                tasks.add(recoveryTask)
                adapter.notifyItemInserted(tasks.size)
                clearFocus()
                hideKeyBoard()
            }
        }

    }

    private fun clearFocus() {
        etTask.setText("")
    }

    private fun hideKeyBoard() {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }

    private fun getTask() {
        lifecycleScope.launch(Dispatchers.IO) {
            tasks = MisNotasApp.database.taskDao().getAllTasks()

            withContext(Dispatchers.Main) {
                setUpRecyclerView(tasks)
            }

        }
    }

    private fun setUpRecyclerView(tasks: List<TaskEntity>) {
        adapter = TaskAdapter(
            tasks,
            { task -> updateTask(task) },
            { task -> deleteTask(task) })

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }


    private fun updateTask(task: TaskEntity) {
        lifecycleScope.launch(Dispatchers.IO) {
            task.isDone = !task.isDone
            MisNotasApp.database.taskDao().updateTask(task)
        }
    }

    private fun deleteTask(task: TaskEntity) {
        lifecycleScope.launch(Dispatchers.IO)  {
            val position = tasks.indexOf(task)
            MisNotasApp.database.taskDao().deleteTask(task)
            tasks.remove(task)

            withContext(Dispatchers.Main) {
                adapter.notifyItemRemoved(position)
            }

        }
    }


}