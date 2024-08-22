package com.cursokotlin.misnotas.modelo

import androidx.room.Entity
import androidx.room.PrimaryKey

//Se usa para añadirle un nombre a la entidad como tabla de la BD
@Entity(tableName = "task_entity")
data class TaskEntity (
    //La anotacion inidca que la variable id es un valor que se autogenera al crear un objeta de esta clase y que no podra repetirse
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0,

    var name: String = "",
    var isDone:Boolean = false
)