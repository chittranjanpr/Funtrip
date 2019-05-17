package com.example.funtrip.funtrip

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.funtrip.funtrip.Activities.Home
import com.example.funtrip.funtrip.Activities.Login
import com.example.funtrip.funtrip.Activities.Register

class Splashscreen : AppCompatActivity() {


    lateinit var sharedpreferences: SharedPreferences
//    lateinit var name: EditText
//    lateinit var email: EditText

    val Name = "nameKey"
    val Email = "emailKey"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        sharedpreferences = getSharedPreferences(
            com.example.funtrip.funtrip.Activities.Register.mypreference,
            Context.MODE_PRIVATE
        )
       val  cc =  sharedpreferences.getString(Name, "")

        val background = object : Thread(){
            override fun run() {
                try {
                    Thread.sleep(2000)

                        if (cc.length > 0 ){
                             Log.d("state","hello")
                            val intent = Intent(baseContext,Home::class.java)
                            startActivity(intent)
                        }
                        else
                        {
                            Log.d("state","byebye")
                            val intent = Intent(baseContext,Home::class.java)
                            startActivity(intent)
                        }


//                    val intent = Intent(baseContext,Login::class.java)
//                    startActivity(intent)

                }catch (e: Exception){
                    e.printStackTrace()
                }
            }
        }
        background.start()
    }
}