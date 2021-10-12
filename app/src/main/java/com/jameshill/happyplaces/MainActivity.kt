package com.jameshill.happyplaces

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        //calls the parent constructor
        super.onCreate(savedInstanceState)
        //aligns the xml view with this class
        setContentView(R.layout.activity_main)

        //sets the onclick listener for the fab button and calls the addhappyplace activity
        fab_AddHappyPlace.setOnClickListener{
            val intent = Intent(this@MainActivity, AddHappyPlaceActivity::class.java )
            startActivity(intent)
        }
    }
}