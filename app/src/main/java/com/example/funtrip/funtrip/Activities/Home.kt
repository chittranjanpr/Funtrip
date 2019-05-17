package com.example.funtrip.funtrip.Activities

import android.app.ActivityManager
import android.content.*
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.widget.Toast
import com.example.funtrip.funtrip.R
import com.example.funtrip.funtrip.Receivers.BaseActivity
import com.example.funtrip.funtrip.Services.RandomNumberService
import com.example.funtrip.funtrip.ui.fragments.Frag1
import com.example.funtrip.funtrip.ui.fragments.Hotel
import com.example.funtrip.funtrip.ui.fragments.LayoutOne
//import com.example.funtrip.funtrip.Receivers.ConnectionReceivers
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.layout_one_fragment.*


class Home : BaseActivity() {


    lateinit var sharedpreferences: SharedPreferences


//    var taskRecyclerAdapter: TaskRecyclerAdapter? = null;
//    var fab: FloatingActionButton? = null
//    var recyclerView: RecyclerView? = null
//    var dbHandler: DatabaseHandler? = null
//    var listTasks: List<Tasks> = ArrayList<Tasks>()
//    var linearLayoutManager: LinearLayoutManager? = null

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {

            R.id.navigation_home -> {

                supportFragmentManager.beginTransaction().replace(R.id.container, LayoutOne.newInstance()).commitNow()
                 return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_location -> {

              val intent = Intent(baseContext, ModalActivity::class.java)
                startActivity(intent)

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_hotels -> {
                val intent = Intent(baseContext, Hotel::class.java)
                startActivity(intent)

//                supportFragmentManager.beginTransaction().replace(R.id.container, Hotel.newInstance()).commitNow()

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_account -> {
                sharedpreferences = getSharedPreferences(
                    Register.mypreference,
                    Context.MODE_PRIVATE
                )
                val editor = sharedpreferences.edit()
                editor.clear()
                editor.commit()

                val intent = Intent(baseContext, Login::class.java)
                startActivity(intent)

                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

       hreg.setOnClickListener {



            val intent = Intent(baseContext, HotelRegistration::class.java)
            startActivity(intent)
        }


        searchbox.setOnClickListener {
            val intent = Intent(baseContext, ModalActivity::class.java)
            startActivity(intent)
        }
        supportFragmentManager.beginTransaction().replace(R.id.container, LayoutOne.newInstance()).commitNow()

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

//        Button1.setOnClickListener {
//            val intent = Intent(baseContext, Login::class.java)
//            startActivity(intent)
//        }
//        initViews()
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


//    fun initDB() {
//        dbHandler = DatabaseHandler(this)
//        listTasks = (dbHandler as DatabaseHandler).task()
//        taskRecyclerAdapter = TaskRecyclerAdapter(tasksList = listTasks, context = applicationContext)
//        (recyclerView as RecyclerView).adapter = taskRecyclerAdapter
//    }
//
//    fun initViews() {
////        val toolbar = findViewById(R.id.toolbar) as Toolbar
////        setSupportActionBar(toolbar)
//        fab = findViewById(R.id.hreg) as FloatingActionButton
//        recyclerView = findViewById(R.id.recycler_view) as RecyclerView
//        taskRecyclerAdapter = TaskRecyclerAdapter(tasksList = listTasks, context = applicationContext)
//        linearLayoutManager = LinearLayoutManager(applicationContext)
//        (recyclerView as RecyclerView).layoutManager = linearLayoutManager
//    }
//
//
//    override fun onResume() {
//        super.onResume()
//        initDB()
//    }


    fun Context.toast(message:String){
        Toast.makeText(applicationContext,message, Toast.LENGTH_SHORT).show()
    }

}







//override fun onCreate(savedInstanceState: Bundle?) {
//    super.onCreate(savedInstanceState)
//    setContentView(R.layout.activity_main)
//    initViews()
//    initOperations()
//    //initDB()
//}
//
