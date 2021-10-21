package com.jameshill.happyplaces.models

import java.io.Serializable

//This class is the pattern for each entry in Happy Places
//Each entry will have all of these values
data class HappyPlaceModel (
    val id: Int,
    val title: String,
    val image: String,
    val description: String,
    val date: String,
    val location: String,
    val latitude: Double,
    val longitude: Double): Serializable
// Serializable keyword allows us to pass this object one class to another.
// Parcelable is another option. In larger data sets there is a gain
// in transfer speed.
