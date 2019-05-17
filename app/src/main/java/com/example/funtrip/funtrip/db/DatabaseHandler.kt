package com.example.funtrip.funtrip.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.funtrip.funtrip.models.Tasks
import java.util.ArrayList

class DatabaseHandler(context: Context) : SQLiteOpenHelper(context, DatabaseHandler.DB_NAME, null, DatabaseHandler.DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TABLE = "CREATE TABLE $TABLE_NAME ($ID INTEGER PRIMARY KEY, $NAME TEXT,$DESC TEXT,$RATE TEXT,$ROOMS TEXT);"
        db.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
        db.execSQL(DROP_TABLE)
        onCreate(db)
    }

    fun addTask(tasks: Tasks): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(NAME, tasks.name)
        values.put(DESC, tasks.desc)
        values.put(RATE, tasks.rate)
        values.put(ROOMS, tasks.rooms)


        Log.d("insert",tasks.name)
        Log.d("insert",tasks.desc)
        Log.d("insert",tasks.rate)
        Log.d("insert",tasks.rooms)

//        values.put(COMPLETED, tasks.completed)
        val _success = db.insert(TABLE_NAME, null, values)
        db.close()
        Log.v("InsertedId", "$_success")
        return (Integer.parseInt("$_success") != -1)
    }

    fun getTask(_id: Int): Tasks {
        val tasks = Tasks()
        val db = writableDatabase
        val selectQuery = "SELECT  * FROM $TABLE_NAME WHERE $ID = $_id"
        val cursor = db.rawQuery(selectQuery, null)

        cursor?.moveToFirst()
        tasks.id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ID)))
        tasks.name = cursor.getString(cursor.getColumnIndex(NAME))
        tasks.desc = cursor.getString(cursor.getColumnIndex(DESC))
        tasks.rate = cursor.getString(cursor.getColumnIndex(RATE))

        cursor.close()
        return tasks
    }

    fun task(): List<Tasks> {
        val taskList = ArrayList<Tasks>()
        val db = writableDatabase
        val selectQuery = "SELECT  * FROM $TABLE_NAME"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val tasks = Tasks()
                    tasks.id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ID)))
                    tasks.name = cursor.getString(cursor.getColumnIndex(NAME))
                    tasks.desc = cursor.getString(cursor.getColumnIndex(DESC))
                    tasks.rate = cursor.getString(cursor.getColumnIndex(RATE))
                    tasks.rooms = cursor.getString(cursor.getColumnIndex(ROOMS))
//                    tasks.completed = cursor.getString(cursor.getColumnIndex(COMPLETED))
                    taskList.add(tasks)
                } while (cursor.moveToNext())
            }
        }
        cursor.close()
        return taskList
    }
    fun searchTask(_word: String): List<Tasks> {
        val taskList = ArrayList<Tasks>()
        val db = writableDatabase
       Log.d("oooooodata",_word)
//        val selectQuery = "SELECT  * FROM $TABLE_NAME"
        val selectQuery = "SELECT  * FROM $TABLE_NAME WHERE $DESC = '$_word'"
        Log.d("oooooodata","1")
        val cursor = db.rawQuery(selectQuery, null)
        Log.d("oooooodata","2")
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val tasks = Tasks()
                    Log.d("oooooodata","3")
                    tasks.id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ID)))
                    Log.d("oooodb", cursor.getString(cursor.getColumnIndex(NAME)))
                    tasks.name = cursor.getString(cursor.getColumnIndex(NAME))
                    tasks.desc = cursor.getString(cursor.getColumnIndex(DESC))
//                    tasks.completed = cursor.getString(cursor.getColumnIndex(COMPLETED))
                    taskList.add(tasks)
                } while (cursor.moveToNext())
            }
        }
        Log.d("oooooodata","4")
        cursor.close()
        return taskList
    }

//    fun updateTask(tasks: Tasks): Boolean {
//        val db = this.writableDatabase
//        val values = ContentValues()
//        values.put(NAME, tasks.name)
//        values.put(DESC, tasks.desc)
////        values.put(COMPLETED, tasks.completed)
//        val _success = db.update(TABLE_NAME, values, ID + "=?", arrayOf(tasks.id.toString())).toLong()
//        db.close()
//        return Integer.parseInt("$_success") != -1
//    }

    fun deleteTask(_id: Int): Boolean {
        val db = this.writableDatabase
        val _success = db.delete(TABLE_NAME, ID + "=?", arrayOf(_id.toString())).toLong()
        db.close()
        return Integer.parseInt("$_success") != -1
    }

//
//    fun deleteAllTasks(): Boolean {
//        val db = this.writableDatabase
//        val _success = db.delete(TABLE_NAME, null, null).toLong()
//        db.close()
//        return Integer.parseInt("$_success") != -1
//    }

    companion object {
        private val DB_VERSION = 1
        private val DB_NAME = "MyTrip"
        private val TABLE_NAME = "Tripdetails"
        private val ID = "Id"
        private val NAME = "Name"
        private val DESC = "Desc"
        private val RATE = "Rate"
        private val ROOMS = "Rooms"
    }
}
