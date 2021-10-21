package com.jameshill.happyplaces.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.jameshill.happyplaces.R
import com.jameshill.happyplaces.adapters.HappyPlacesAdapter
import com.jameshill.happyplaces.database.DatabaseHandler
import com.jameshill.happyplaces.models.HappyPlaceModel
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
            startActivityForResult(intent, ADD_PLACE_ACTIVITY_REQUEST_CODE)
        } //end this section
        getHappyPlacesListFromLocalDB()
    }

    private fun setupHappyPlacesRecyclerView(happyPlaceList: ArrayList<HappyPlaceModel>){
        rv_happyPlaces.layoutManager = LinearLayoutManager(this)

        rv_happyPlaces.setHasFixedSize(true)
        val placesAdapter = HappyPlacesAdapter(this, happyPlaceList)
        rv_happyPlaces.adapter = placesAdapter

        placesAdapter.setOnClickListener(object : HappyPlacesAdapter.OnClickListener {
            override fun onClick(position: Int, model: HappyPlaceModel) {
                val intent = Intent(this@MainActivity, HappyPlaceDetailActivity::class.java)
                startActivity(intent)
            }
        })

    }
    private fun getHappyPlacesListFromLocalDB(){
        val dbHandler = DatabaseHandler(this)
        val getHappyPlaceList : ArrayList<HappyPlaceModel> = dbHandler.getHappyPlacesList()

        if (getHappyPlaceList.size > 0){
            rv_happyPlaces.visibility = View.VISIBLE
            tv_no_records_found.visibility = View.GONE
            setupHappyPlacesRecyclerView(getHappyPlaceList)
        }else{
            rv_happyPlaces.visibility = View.GONE
            tv_no_records_found.visibility = View.VISIBLE
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_PLACE_ACTIVITY_REQUEST_CODE){
            if (resultCode == Activity.RESULT_OK)
                getHappyPlacesListFromLocalDB()
        }else{
            Log.e("Activity", "Cancelled or backpressed")
        }
    }

    companion object {
        var ADD_PLACE_ACTIVITY_REQUEST_CODE = 1
    }
}