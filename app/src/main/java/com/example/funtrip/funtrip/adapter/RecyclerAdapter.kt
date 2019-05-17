package com.example.funtrip.funtrip.adapter

import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.funtrip.funtrip.Activities.Book
import com.example.funtrip.funtrip.Activities.HotelRegistration
import com.example.funtrip.funtrip.R
import com.example.funtrip.funtrip.models.Tasks
import java.util.ArrayList

class RecyclerAdapter(tasksList: List<Tasks>, internal var context: Context) : RecyclerView.Adapter<RecyclerAdapter.TaskViewHolder>() {

    internal var tasksList: List<Tasks> = ArrayList()
    init {
        this.tasksList = tasksList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.hotellist, parent, false)
        return TaskViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val tasks = tasksList[position]
        holder.name.text = tasks.name
        holder.desc.text = tasks.desc


        holder.itemView.setOnClickListener {
            val i = Intent(context, Book::class.java)
            i.putExtra("Mode", "E")
            i.putExtra("Id", tasks.id)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(i)
        }


    }

    override fun getItemCount(): Int {
        return tasksList.size
    }

    inner class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name: TextView = view.findViewById(R.id.hdbname) as TextView
        var desc: TextView = view.findViewById(R.id.hdblocation) as TextView
        var list_item: LinearLayout = view.findViewById(R.id.disphcontainer) as LinearLayout
    }

}
