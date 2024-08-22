package com.cursokotlin.misnotas

import android.app.Application
import androidx.room.Room
import com.cursokotlin.misnotas.bd.TaskDatabase

//con : Application() Le indicamos a Andorind que esta clase debe instanciarse al inicio de la aplicacion
//Tambien el nombre de la clase se debe agregar en el manisfest dentro de la etiqueta <Application>
class MisNotasApp: Application() {

    //Con esta declaracion podemos acceder a la bd desde cualquier lugar de la app
    companion object{
        lateinit var database: TaskDatabase
    }

    override fun onCreate() {
        super.onCreate()

        //Esta instancia recibe 3 parametros(contexto, la clase donde se encuentra la bd y el nombre que le daremos a la bd)
        database = Room.databaseBuilder(this, TaskDatabase::class.java, "tasks-db").build()
    }

}