package com.example.funtrip.funtrip.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.MenuItem
import android.view.View
import com.example.funtrip.funtrip.R
import com.example.funtrip.funtrip.db.DatabaseHandler
import com.example.funtrip.funtrip.models.Tasks
import kotlinx.android.synthetic.main.activity_hotel_registration.*



//class HotelRegistration : AppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_hotel_registration)
//    }
//}


class HotelRegistration : AppCompatActivity() {

    var dbHandler: DatabaseHandler? = null
    var isEditMode = false

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel_registration)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        initDB()
        initOperations()
    }

//    private fun initDB() {
//        dbHandler = DatabaseHandler(this)
//        btn_delete.visibility = View.INVISIBLE
//        if (intent != null && intent.getStringExtra("Mode") == "E") {
//            isEditMode = true
//            val tasks: Tasks = dbHandler!!.getTask(intent.getIntExtra("Id",0))
//            input_name.setText(tasks.name)
//            input_desc.setText(tasks.desc)
//            swt_completed.isChecked = tasks.completed == "Y"
//            btn_delete.visibility = View.VISIBLE
//        }
//    }

    private fun initOperations() {

        dbHandler = DatabaseHandler(this)


        hsave.setOnClickListener({
            var success: Boolean = false

                val tasks: Tasks = Tasks()
                tasks.name = hname.text.toString()
                tasks.desc = hlocation.text.toString()
                tasks.rate = hrate.text.toString()
                success = dbHandler?.addTask(tasks) as Boolean


            if (success)
                finish()
        })

//        btn_delete.setOnClickListener({
//            val dialog = AlertDialog.Builder(this).setTitle("Info").setMessage("Click 'YES' Delete the Task.")
//                .setPositiveButton("YES", { dialog, i ->
//                    val success = dbHandler?.deleteTask(intent.getIntExtra("Id", 0)) as Boolean
//                    if (success)
//                        finish()
//                    dialog.dismiss()
//                })
//                .setNegativeButton("NO", { dialog, i ->
//                    dialog.dismiss()
//                })
//            dialog.show()
//        })
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        val id = item.itemId
//        if (id == android.R.id.home) {
//            finish()
//            return true
//        }
//        return super.onOptionsItemSelected(item)
//    }
}



