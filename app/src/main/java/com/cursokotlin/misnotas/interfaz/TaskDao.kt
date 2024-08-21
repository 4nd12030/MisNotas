package com.cursokotlin.misnotas.interfaz

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.cursokotlin.misnotas.modelo.TaskEntity

//Esta interfaz contendra las consultas de la bd


@Dao
interface TaskDao {
    @Query("SELECT * FROM taskentity")
    fun getAllTasks() : MutableList<TaskEntity>
}