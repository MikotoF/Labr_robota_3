package com.example.labrrobota3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.labrrobota3.databinding.ActivityWeatherBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.labrrobota3.WeatherService
import com.example.labrrobota3.WeatherData

class WeatherActivity : AppCompatActivity() {

    private val apiKey = "65a3eec2abd1b54345988b6f64d93fa0"
    private val latitude = 50.2
    private val longitude = 28.6

    private lateinit var binding: ActivityWeatherBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Create Retrofit instance
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Create WeatherService using Retrofit
        val weatherService = retrofit.create(WeatherService::class.java)

        // Make API call to get weather data
        val call = weatherService.getWeatherData(latitude, longitude, apiKey)
        call.enqueue(object : Callback<WeatherData> {
            override fun onResponse(call: Call<WeatherData>, response: Response<WeatherData>) {
                if (response.isSuccessful) {
                    val weatherData = response.body()
                    weatherData?.let {
                        // Convert temperature from Kelvin to Celsius
                        val temperatureCelsius = it.main.temperature - 273.15

                        // Update UI with weather data
                        binding.temperatureTextView.text = "${String.format("%.1f", temperatureCelsius)}°C"
                        binding.windTextView.text = "Wind: ${it.wind.speed} m/s"
                        binding.humidityTextView.text = "Humidity: ${it.main.humidity}%"
                    }
                }
            }

            override fun onFailure(call: Call<WeatherData>, t: Throwable) {
                // Handle failure
            }
        })

        // Handle Back button click
        binding.backButton.setOnClickListener {
            onBackPressed()
        }
    }
}
