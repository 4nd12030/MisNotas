package com.cursokotlin.misnotas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.cursokotlin.misnotas.databinding.ActivityMainBinding
import com.cursokotlin.misnotas.modelo.TaskEntity
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {

    private lateinit var bindibg: ActivityMainBinding

    lateinit var recyclerView: RecyclerView
    lateinit var tasks: MutableList<TaskEntity>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindibg = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindibg.root)

        tasks = ArrayList()
        getTask()
    }

    private fun getTask() = runBlocking{
        launch {
            tasks = MisNotasApp.database.taskDao().getAllTasks()
            runOnUiThread{
                setUpRecyclerView(tasks)
            }
        }
    }

    private fun setUpRecyclerView(tasks: MutableList<TaskEntity>) {

    }
}