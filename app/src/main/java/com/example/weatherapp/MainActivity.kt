package com.example.weatherapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import org.w3c.dom.Text
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val lat = intent.getStringExtra("lat")
        val lon = intent.getStringExtra("long")
        getJsonData(lat, lon)
        window.statusBarColor = Color.parseColor("#1383C3")
    }

    private fun getJsonData(lat: String?, lon: String?) {
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val apiKey = "5708ca44984af65e51ceb21553ff00c8"
        val url = "https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${lon}&appid=${apiKey}"

// Request a string response from the provided URL.
        val jsonRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                setValues(response)
            },
            Response.ErrorListener{ Toast.makeText(this, "ERROR", Toast.LENGTH_LONG).show()})

// Add the request to the RequestQueue.
        queue.add(jsonRequest)
    }

    private fun setValues(response: JSONObject) {
        val city: TextView = findViewById(R.id.city)
        city.text = response.getString("name")

        val lat = response.getJSONObject("coord").getString("lat")
        val lon = response.getJSONObject("coord").getString("lon")
        val coordinates: TextView =  findViewById(R.id.coordinates)
        coordinates.text = "${lat}, ${lon}"

        val weather: TextView = findViewById(R.id.weather)
        weather.text = response.getJSONArray("weather").getJSONObject(0).getString("main")

        var tempr = response.getJSONObject("main").getString("temp")
        tempr = (tempr.toFloat() - 273.15).toInt().toString()
        val temperature: TextView = findViewById(R.id.temp)
        temperature.text = tempr + "°C"

        val minimumTemperature: TextView = findViewById(R.id.min_temp)
        var mintemp = response.getJSONObject("main").getString("temp_min")
        mintemp = (mintemp.toFloat() - 273.15).toInt().toString()
        minimumTemperature.text = mintemp + "°C"

        val maximumTemperature: TextView = findViewById(R.id.max_temp)
        var maxtemp = response.getJSONObject("main").getString("temp_max")
        maxtemp = ceil((maxtemp.toFloat()) - 273.15).toInt().toString()
        maximumTemperature.text = maxtemp + "°C"
    }
}