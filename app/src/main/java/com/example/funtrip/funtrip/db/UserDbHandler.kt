package com.example.funtrip.funtrip.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.funtrip.funtrip.models.UserTasks
import java.util.*

class UserDbHandler(context: Context) : SQLiteOpenHelper(context, UserDbHandler.DB_NAME, null, UserDbHandler.DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TABLE = "CREATE TABLE $TABLE_NAME ($ID INTEGER PRIMARY KEY, $NAME TEXT,$PASSWORD TEXT,$PHNO TEXT);"
        db.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
        db.execSQL(DROP_TABLE)
        onCreate(db)
    }

    fun addTask(tasks: UserTasks): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(NAME, tasks.name)
        values.put(PASSWORD, tasks.password)
        values.put(PHNO, tasks.phno)
        val _success = db.insert(TABLE_NAME, null, values)
        db.close()
        Log.v("InsertedId1", "$_success")
        return (Integer.parseInt("$_success") != -1)
    }

//    fun getTask(_id: Int): UserTasks {
//        val tasks = UserTasks()
//        val db = writableDatabase
//        val selectQuery = "SELECT  * FROM $TABLE_NAME WHERE $ID = $_id"
//        val cursor = db.rawQuery(selectQuery, null)
//
//        cursor?.moveToFirst()
//        tasks.id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ID)))
//        tasks.name = cursor.getString(cursor.getColumnIndex(NAME))
//        tasks.desc = cursor.getString(cursor.getColumnIndex(DESC))
//        cursor.close()
//        return tasks
//    }

//    fun task(): List<UserTasks> {
//        val taskList = ArrayList<UserTasks>()
//        val db = writableDatabase
//        val selectQuery = "SELECT  * FROM $TABLE_NAME"
//        val cursor = db.rawQuery(selectQuery, null)
//        if (cursor != null) {
//            if (cursor.moveToFirst()) {
//                do {
//                    val tasks = UserTasks()
//                    tasks.id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ID)))
//                    tasks.name = cursor.getString(cursor.getColumnIndex(NAME))
//                    tasks.desc = cursor.getString(cursor.getColumnIndex(DESC))
//                    taskList.add(tasks)
//                } while (cursor.moveToNext())
//            }
//        }
//        cursor.close()
//        return taskList
//    }

//    fun updateTask(tasks: UserTasks): Boolean {
//        val db = this.writableDatabase
//        val values = ContentValues()
//        values.put(NAME, tasks.name)
//        values.put(DESC, tasks.desc)
//        val _success = db.update(TABLE_NAME, values, ID + "=?", arrayOf(tasks.id.toString())).toLong()
//        db.close()
//        return Integer.parseInt("$_success") != -1
//    }
//
//    fun deleteTask(_id: Int): Boolean {
//        val db = this.writableDatabase
//        val _success = db.delete(TABLE_NAME, ID + "=?", arrayOf(_id.toString())).toLong()
//        db.close()
//        return Integer.parseInt("$_success") != -1
//    }
//
//    fun deleteAllTasks(): Boolean {
//        val db = this.writableDatabase
//        val _success = db.delete(TABLE_NAME, null, null).toLong()
//        db.close()
//        return Integer.parseInt("$_success") != -1
//    }

    companion object {
        private val DB_VERSION = 1
        private val DB_NAME = "UserData"
        private val TABLE_NAME = "Details"
        private val ID = "Id"
        private val NAME = "Name"
        private val PHNO = "PHNO"
        private val PASSWORD = "PASSWORD"

    }
}