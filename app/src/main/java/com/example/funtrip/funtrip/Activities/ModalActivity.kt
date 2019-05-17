package com.example.funtrip.funtrip.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.EditText
import com.example.funtrip.funtrip.R
import com.example.funtrip.funtrip.adapter.RecyclerAdapter
import com.example.funtrip.funtrip.adapter.TaskRecyclerAdapter
import com.example.funtrip.funtrip.db.DatabaseHandler
import com.example.funtrip.funtrip.models.Tasks
import kotlinx.android.synthetic.main.activity_hotel_registration.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_modal.*

class ModalActivity : AppCompatActivity() {

    var taskRecyclerAdapter: RecyclerAdapter? = null;
    var fab: FloatingActionButton? = null
    var recyclerView: RecyclerView? = null
    var dbHandler: DatabaseHandler? = null
    var listTasks: List<Tasks> = ArrayList<Tasks>()
    var linearLayoutManager: LinearLayoutManager? = null

 var editdata = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modal)
        Log.d("oooo","kiko")

      closebtn.setOnClickListener {
          val intent = Intent(baseContext, Home::class.java)
                 startActivity(intent)
      }
        Log.d("oooo","kiko1")

        searchbtn.setOnClickListener {

            var  name = findViewById<View>(R.id.searchname) as EditText

            editdata = name.getText().toString()

            initDB()
            initViews()
        }
        Log.d("oooo","kiko2")
//
        initViews()

    }

    fun initDB() {

      if(editdata.length > 0) {
          Log.d("ooooo",editdata)
          dbHandler = DatabaseHandler(this)
          listTasks = (dbHandler as DatabaseHandler).searchTask(editdata)
          taskRecyclerAdapter = RecyclerAdapter(tasksList = listTasks, context = applicationContext)
          (recyclerView as RecyclerView).adapter = taskRecyclerAdapter
      }
        else {
          Log.d("ooopp",editdata)
          dbHandler = DatabaseHandler(this)
          listTasks = (dbHandler as DatabaseHandler).task()
          taskRecyclerAdapter = RecyclerAdapter(tasksList = listTasks, context = applicationContext)
          (recyclerView as RecyclerView).adapter = taskRecyclerAdapter
      }
    }

    fun initViews() {
        Log.d("ooopp","view")
        recyclerView = findViewById(R.id.recycler_view1) as RecyclerView
        taskRecyclerAdapter = RecyclerAdapter(tasksList = listTasks, context = applicationContext)
        linearLayoutManager = LinearLayoutManager(applicationContext)
        (recyclerView as RecyclerView).layoutManager = linearLayoutManager
    }

    override fun onResume() {
        super.onResume()
        initDB()
    }

}

