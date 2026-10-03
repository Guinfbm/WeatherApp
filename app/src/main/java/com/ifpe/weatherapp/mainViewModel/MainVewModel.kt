package com.ifpe.weatherapp.mainViewModel

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.ifpe.weatherapp.model.City
import com.ifpe.weatherapp.ui.getCities

class MainViewModel : ViewModel() {
    private val _cities = getCities().toMutableStateList()
    val cities get() = _cities

    fun remove(city: City) {
        _cities.remove(city)
    }
    fun add(name: String) {
        _cities.add(City(name = name))
    }
}
