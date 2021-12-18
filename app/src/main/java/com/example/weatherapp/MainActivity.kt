package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val lat = intent.getStringExtra("lat")
        val lon = intent.getStringExtra("long")
        getJsonData(lat, lon)
    }

    private fun getJsonData(lat: String?, lon: String?) {
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val apiKey: String = "5708ca44984af65e51ceb21553ff00c8"
        val url = "api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${lon}&appid=${apiKey}"

// Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->

            },
            Response.ErrorListener{})

// Add the request to the RequestQueue.
        queue.add(stringRequest)
    }
}