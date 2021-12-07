package com.example.coroutinesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.coroutinesapp.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Response
import java.lang.Exception
import retrofit2.Callback

class MainActivity : AppCompatActivity() {

    lateinit var cars: ArrayList<String>
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cars = arrayListOf()
        binding.myRecyclerView.adapter = RVAdapter(cars)

        // Retrofit API
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)

        apiInterface?.getCars()?.enqueue(object : Callback<Cars> {
            override fun onResponse(call: Call<Cars>, response: Response<Cars>) {
                try {
                    cars.add(response.body()!![0].model)
                    binding.myRecyclerView.adapter?.notifyDataSetChanged()
                    Log.d("MainActivity", "MAKE: ${response.body()!![0].make}")
                    Log.d("MainActivity", "MODEL: ${response.body()!![0].model}")
                    Log.d("MainActivity", "YEAR: ${response.body()!![0].year}")
                    Log.d("MainActivity", "OWNERS: ${response.body()!![0].owners}")
                } catch (ex: Exception) {
                    Log.d("MainActivity", "Error: ${ex.message}")
                }
            }

            override fun onFailure(call: Call<Cars>, t: Throwable) {
                Log.d("MainActivity", "Enable to get data")
            }

        })

    }
}
