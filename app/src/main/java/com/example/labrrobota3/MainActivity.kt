package com.example.labrrobota3

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.labrrobota3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Set the fact about Zhytomyr Oblast
        binding.factTextView.text =
            "Zhytomyr Oblast in Ukraine is known for its rich history, beautiful landscapes, and agricultural production. The region's capital, Zhytomyr, is one of Ukraine's oldest cities, and the area is renowned for its picturesque scenery and cultural landmarks."

        // Handle Weather button click
        binding.weatherButton.setOnClickListener {
            val intent = Intent(this, WeatherActivity::class.java)
            startActivity(intent)
        }
    }
}
