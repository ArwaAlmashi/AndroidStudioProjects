package com.example.coroutinesapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.coroutinesapp.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import java.lang.Exception
import java.net.URI
import java.net.URL

class MainActivity : AppCompatActivity() {

    lateinit var cars: ArrayList<String>
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cars = arrayListOf()
        binding.myRecyclerView.adapter = RVAdapter(cars)

        requestAPI()

    }

    private fun requestAPI() {
        CoroutineScope(IO).launch {
            val data = async{ fetchData() }.await()
            if (data.isNotEmpty()) {
                populateRV(data)
            } else {
                Toast.makeText(this@MainActivity, "Noy found data", Toast.LENGTH_LONG)
            }
        }
    }

    private fun fetchData(): String {
        var response = ""
        try {
            response =
                URL("https://raw.githubusercontent.com/AlminPiricDojo/JSON_files/main/cars.json").readText()
        } catch (ex: Exception) {
            Toast.makeText(this, "Error: ${ex.message}", Toast.LENGTH_LONG).show()

        }
        return response
    }


    private suspend fun populateRV(result: String){
        withContext(Main){

            val jsonArray = JSONArray(result)

            val make = jsonArray.getJSONObject(0).getString("make")
            val model = jsonArray.getJSONObject(0).getString("model")
            val year = jsonArray.getJSONObject(0).getString("year")
            Log.d("MainActivity", make)
            Log.d("MainActivity", model)
            Log.d("MainActivity", year)

            val ownerJsonArray = jsonArray.getJSONObject(0).getJSONArray("owners")
            for (i in 0 until ownerJsonArray.length()) {
                Log.d("MainActivity", "${ownerJsonArray[i]}")
            }
            cars.add(make)
            binding.myRecyclerView.adapter?.notifyDataSetChanged()
        }
    }
}