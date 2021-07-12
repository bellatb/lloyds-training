package com.example.android.countriesoftheworld.model

data class CountryData(
    val name: String,
    val capital: String,
    val region: String,
    val subregion: String,
    val population: Int,
    var timezones: List<String>,
    val flag: String
)