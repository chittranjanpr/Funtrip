package com.example.funtrip.funtrip

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.funtrip.funtrip.ui.fragments.LayoutOne

class Display : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.display_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.container, LayoutOne.newInstance()).commitNow()
        }
    }

}
