package com.cursokotlin.misnotas.bd


import androidx.room.Database
import androidx.room.RoomDatabase
import com.cursokotlin.misnotas.interfaz.TaskDao
import com.cursokotlin.misnotas.modelo.TaskEntity

//Indica que la bd sera una lista de TaskEntity y la version que sirve para migracion de datos
@Database(entities = [TaskEntity::class], version = 1)
abstract class TaskDatabase: RoomDatabase() {
    abstract fun taskDao(): TaskDao
}
