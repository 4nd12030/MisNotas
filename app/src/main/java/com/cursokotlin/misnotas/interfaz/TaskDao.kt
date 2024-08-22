package com.cursokotlin.misnotas.interfaz


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Index
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.cursokotlin.misnotas.modelo.TaskEntity

//Esta interfaz contendra las consultas de la bd


@Dao
interface TaskDao {
    @Query("SELECT * FROM task_entity")
    fun getAllTasks() : MutableList<TaskEntity>

    @Insert
    fun addTask(taskEntity: TaskEntity) : Long

    @Query("SELECT * FROM task_entity WHERE id LIKE  :id")
    fun getTaskById(id: Long): TaskEntity

    @Update
    fun updateTask(taskEntity: TaskEntity): Int

    @Delete
    fun deleteTask(taskEntity: TaskEntity): Int

}