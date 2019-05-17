package com.example.funtrip.funtrip.Receivers

import android.annotation.SuppressLint
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.example.funtrip.funtrip.R
import kotlinx.android.synthetic.main.activity_home.view.*

//class BaseActivity {
//}

//THIS IS THE BASE ACTIVITY OF ALL ACTIVITIES OF THE APPLICATION.

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity(), ConnectivityReceiver.ConnectivityReceiverListener {
    private var mSnackBar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerReceiver(ConnectivityReceiver(),
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
    }


    private fun showMessage(isConnected: Boolean) {

        if (!isConnected) {

            val messageToUser = "You are offline now." //TODO

            mSnackBar = Snackbar.make(findViewById(R.id.container), messageToUser, Snackbar.LENGTH_LONG) //Assume "rootLayout" as the root layout of every activity.
            mSnackBar?.duration = Snackbar.LENGTH_LONG
            mSnackBar?.show()
        } else {
            mSnackBar?.dismiss()
        }
    }

    override fun onResume() {
        super.onResume()

        ConnectivityReceiver.connectivityReceiverListener = this
    }

    /**
     * Callback will be called when there is change
     */
    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        showMessage(isConnected)
    }
}