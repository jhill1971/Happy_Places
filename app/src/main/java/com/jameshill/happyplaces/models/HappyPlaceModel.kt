package com.jameshill.happyplaces.models

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
    val longitude: Double)
