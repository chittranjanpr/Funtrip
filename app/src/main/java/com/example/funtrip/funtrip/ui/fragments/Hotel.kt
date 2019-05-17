package com.example.funtrip.funtrip.ui.fragments

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.funtrip.funtrip.R
import com.example.funtrip.funtrip.adapter.TaskRecyclerAdapter
import com.example.funtrip.funtrip.db.DatabaseHandler
import com.example.funtrip.funtrip.models.Tasks

class Hotel : AppCompatActivity(){

var taskRecyclerAdapter: TaskRecyclerAdapter? = null;
var fab: FloatingActionButton? = null
var recyclerView: RecyclerView? = null
var dbHandler: DatabaseHandler? = null
var listTasks: List<Tasks> = ArrayList<Tasks>()
var linearLayoutManager: LinearLayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel)
//        initDB()

        initViews()

//        initOperations()

    }


    fun initDB() {
        dbHandler = DatabaseHandler(this)
        listTasks = (dbHandler as DatabaseHandler).task()
        taskRecyclerAdapter = TaskRecyclerAdapter(tasksList = listTasks, context = applicationContext)
        (recyclerView as RecyclerView).adapter = taskRecyclerAdapter
    }

    fun initViews() {
//        val toolbar = findViewById(R.id.toolbar) as Toolbar
//        setSupportActionBar(toolbar)
//        fab = findViewById(R.id.fab) as FloatingActionButton
        recyclerView = findViewById(R.id.recycler_view) as RecyclerView
        taskRecyclerAdapter = TaskRecyclerAdapter(tasksList = listTasks, context = applicationContext)
        linearLayoutManager = LinearLayoutManager(applicationContext)
        (recyclerView as RecyclerView).layoutManager = linearLayoutManager
    }

    override fun onResume() {
        super.onResume()
        initDB()
    }



}


//class Hotel : Fragment() {
//
//
//
//    companion object {
//        fun newInstance() = Hotel ()
//    }
//
//    private lateinit var viewModel: LayoutOneViewModel
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        return inflater.inflate(R.layout.activity_hotel, container, false)
//    }
//
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProviders.of(this).get(LayoutOneViewModel::class.java)
//        // TODO: Use the ViewModel
//    }
//
//}