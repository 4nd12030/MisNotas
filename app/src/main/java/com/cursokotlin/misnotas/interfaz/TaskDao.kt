package com.cursokotlin.misnotas.interfaz


import androidx.room.Dao
import androidx.room.Query
import com.cursokotlin.misnotas.modelo.TaskEntity

//Esta interfaz contendra las consultas de la bd


@Dao
interface TaskDao {
    @Query("SELECT * FROM task_entity")
    fun getAllTasks() : MutableList<TaskEntity>
}