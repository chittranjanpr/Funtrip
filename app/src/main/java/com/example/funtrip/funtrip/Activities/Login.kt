package com.example.funtrip.funtrip.Activities

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.funtrip.funtrip.R
import com.example.funtrip.funtrip.Activities.Register
import com.example.funtrip.funtrip.Services.RandomNumberService
import kotlinx.android.synthetic.main.activity_login.*



class Login : AppCompatActivity() {



    lateinit var sharedpreferences: SharedPreferences

    val Name = "nameKey"
    val Email = "emailKey"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Variable to hold service class name
        val serviceClass = RandomNumberService::class.java

        // Initialize a new Intent instance
        val intent = Intent(applicationContext, serviceClass)

       val name = findViewById<View>(R.id.pass) as EditText
       val email = findViewById<View>(R.id.mail) as EditText

        val cc =intent.getStringExtra("Username")

        if(cc == "1")
        Toast.makeText(this, "You have been successfully registered ", Toast.LENGTH_LONG).show()

        sharedpreferences = getSharedPreferences(
            com.example.funtrip.funtrip.Activities.Register.mypreference,
            Context.MODE_PRIVATE
        )


         login.setOnClickListener {
//             Log.d("pev","nxt")
//             Log.d("pev", ""+email.getText().toString())
//             Log.d("pev",name.toString())
//
//             Log.d("spev",sharedpreferences.getString(Name, ""))
//             Log.d("spev",sharedpreferences.getString(Email, ""))

             if((name.getText().toString().length == 0) && (email.getText().toString().length == 0))
                 Toast.makeText(this, "Enter the correct login details ", Toast.LENGTH_LONG).show()

             if (sharedpreferences.contains(Name)) {
                 if (name.getText().toString() == sharedpreferences.getString(Name, ""))
                 {
                     if (sharedpreferences.contains(Email)) {
                         if(email.getText().toString() == sharedpreferences.getString(Email, "")) {


                             if (!isServiceRunning(serviceClass)) {
                                 // Start the service
                                 startService(intent)
                             } else {
                                 toast("Service already running.")
                             }
                             val intent = Intent(baseContext, Home::class.java)
//                             Toast.makeText(this, "Login Successfull ", Toast.LENGTH_LONG).show()

                             startActivity(intent)
                         }
                         else
                         {
                             Toast.makeText(this, "Enter the correct login details ", Toast.LENGTH_LONG).show()
                         }
                     }

                     }
                 else
                 {
                     Toast.makeText(this, "Enter the correct login details ", Toast.LENGTH_LONG).show()
                 }

             }


         }

        Register.setOnClickListener {
            val intent = Intent(baseContext,com.example.funtrip.funtrip.Activities.Register::class.java)
            startActivity(intent)
        }

//      Register.setOnClickListener {
//          val intent = Intent(baseContext,com.example.funtrip.funtrip.Activities.Register::class.java)
//          startActivity(intent)
//      }


//        if (sharedpreferences.contains(Name)) {
//            name.setText(sharedpreferences.getString(Name, ""))
//        }
//        if (sharedpreferences.contains(Email)) {
//            email.setText(sharedpreferences.getString(Email, ""))
//        }
    }

    // Custom method to determine whether a service is running
    private fun isServiceRunning(serviceClass: Class<*>): Boolean {
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

        // Loop through the running services
        for (service in activityManager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.name == service.service.className) {
                // If the service is running then return true
                return true
            }
        }
        return false
    }


}

// Extension function to show toast message
fun Context.toast(message:String){
    Toast.makeText(applicationContext,message,Toast.LENGTH_SHORT).show()
}
