package com.example.funtrip.funtrip.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.funtrip.funtrip.R
import com.example.funtrip.funtrip.db.BookingsDbHandler
import com.example.funtrip.funtrip.db.DatabaseHandler
import com.example.funtrip.funtrip.models.BookingTasks
import com.example.funtrip.funtrip.models.Tasks
import com.example.funtrip.funtrip.ui.fragments.Hotel
import kotlinx.android.synthetic.main.activity_book.*
import kotlinx.android.synthetic.main.activity_hotel_registration.*

class Book : AppCompatActivity() {

    var dbHandler: DatabaseHandler? = null
    var isEditMode = false


    var BookdbHandler: BookingsDbHandler? = null


     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_book)


         initDB()

         closmodal.setOnClickListener {
             val intent = Intent(baseContext, Home::class.java)
//            Toast.makeText(this, "Thanks for booking", Toast.LENGTH_LONG).show()
             startActivity(intent)
         }


         bsave.setOnClickListener {

             val dialog = AlertDialog.Builder(this).setTitle("Info").setMessage("Click 'YES' to book the room")
                 .setPositiveButton("YES", { dialog, i ->

                     BookdbHandler = BookingsDbHandler(this)

                     hsave.setOnClickListener({
                         var success: Boolean = false

                         val tasks: BookingTasks = BookingTasks()

                         tasks.name = bookbname.text.toString()
                         tasks.desc = booklocation.text.toString()
                         tasks.rate = bookrate.text.toString()
                         success = BookdbHandler?.addTask(tasks) as Boolean


                         if (success)
                             finish()
                     })

                     Toast.makeText(this, "Thanks for booking", Toast.LENGTH_LONG).show()

                     val intent = Intent(baseContext, Hotel::class.java)
                     startActivity(intent)

                     dialog.dismiss()
                 })
                 .setNegativeButton("NO", { dialog, i ->
                     dialog.dismiss()
                 })
             dialog.show()
         }

         bclose.setOnClickListener({
             val dialog = AlertDialog.Builder(this).setTitle("Info").setMessage("Click 'YES' Delete the Task.")
                 .setPositiveButton("YES", { dialog, i ->
                     val success = dbHandler?.deleteTask(intent.getIntExtra("Id", 0)) as Boolean
                     if (success)
                         finish()

                     dialog.dismiss()
                 })
                 .setNegativeButton("NO", { dialog, i ->
                     dialog.dismiss()
                 })
             dialog.show()
         })

}



    private fun initDB() {
        dbHandler = DatabaseHandler(this)
        if (intent != null && intent.getStringExtra("Mode") == "E") {

            isEditMode = true
            val tasks: Tasks = dbHandler!!.getTask(intent.getIntExtra("Id",0))
            bookbname.setText(tasks.name)
            booklocation.setText(tasks.desc)
            bookrate.setText(tasks.rate)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
