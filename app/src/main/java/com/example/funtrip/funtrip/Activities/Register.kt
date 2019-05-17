package com.example.funtrip.funtrip.Activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.funtrip.funtrip.R
import com.example.funtrip.funtrip.db.DatabaseHandler
import com.example.funtrip.funtrip.db.UserDbHandler
import com.example.funtrip.funtrip.models.Tasks
import com.example.funtrip.funtrip.models.UserTasks
import kotlinx.android.synthetic.main.activity_hotel_registration.*
import kotlinx.android.synthetic.main.activity_register.*


class Register : AppCompatActivity() {

    lateinit var sharedpreferences: SharedPreferences
    lateinit var pass: EditText
    lateinit var email: EditText
    lateinit var num: EditText


    var dbHandler: UserDbHandler? = null
    var isEditMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        initDB()
//        initOperations()

        pass = findViewById<View>(R.id.password) as EditText
        email = findViewById<View>(R.id.etEmail) as EditText
        num =  findViewById<View>(R.id.number) as EditText
        sharedpreferences = getSharedPreferences(
            mypreference,
            Context.MODE_PRIVATE
        )

 }


//    private fun initOperations() {
//
//        dbHandler = UserDbHandler(this)
//
//
//        hsave.setOnClickListener({
//            var success: Boolean = false
//
//            val tasks: UserTasks = UserTasks()
//            tasks.name = etEmail.text.toString()
//            tasks.password = password.text.toString()
//            tasks.phno = number.text.toString()
//            success = dbHandler?.addTask(tasks) as Boolean
//
//
//            if (success)
//                finish()
//        })
//    }



    fun Save(view: View) {
        val n = pass.text.toString()
        val e = email.text.toString()

        if((n.length == 0) && (e.length == 0))
            Toast.makeText(this, "Fill all the fiedls ", Toast.LENGTH_LONG).show()
        else
        {
            val editor = sharedpreferences.edit()
            editor.putString(Name, n)
            editor.putString(Email, e)
            editor.commit()
            Toast.makeText(this, "Successfully registered", Toast.LENGTH_LONG).show()
            val intent = Intent(baseContext, Login::class.java)
            intent.putExtra("Username","1")
            startActivity(intent)

        }
    }

//    fun clear(view: View) {
//        name = findViewById<View>(R.id.etName) as EditText
//        email = findViewById<View>(R.id.etEmail) as EditText
//        name.setText("")
//        email.setText("")
//
//    }
//
//    fun Get(view: View) {
//        name = findViewById<View>(R.id.etName) as EditText
//        email = findViewById<View>(R.id.etEmail) as EditText
//        sharedpreferences = getSharedPreferences(
//            mypreference,
//            Context.MODE_PRIVATE
//        )
//
//        if (sharedpreferences.contains(Name)) {
//            name.setText(sharedpreferences.getString(Name, ""))
//        }
//        if (sharedpreferences.contains(Email)) {
//            email.setText(sharedpreferences.getString(Email, ""))
//
//        }
//    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.menu_main, menu)
//        return true
//    }

    companion object {

        val mypreference = "mypref"
        val Name = "nameKey"
        val Email = "emailKey"
        val Hotelname = "hotelkey"
        val Hotelloc = "lockey"
        val Hotelrate = "hotelrate"

    }

}



