package com.example.jsonappretrofit

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.jsonappretrofit.databinding.ActivityMainBinding
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import retrofit2.Retrofit
import android.widget.ArrayAdapter


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private val currencyList: List<String> = listOf("ada", "aed", "all", "vef", "scr")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSpinner()

        binding.convertButton.setOnClickListener {
            val currencyUser: String = binding.spinner.selectedItem.toString()
            getAllData(currencyUser)

        }



    }

    private fun setSpinner() {
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            applicationContext,
            R.layout.simple_spinner_dropdown_item,
            currencyList
        )
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)

        binding.spinner.setAdapter(adapter)
    }

    private fun getAllData(currency: String) {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/")
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData()
        retrofitData.enqueue(object : Callback<Currency> {
            override fun onResponse(call: Call<Currency>, response: Response<Currency>) {
                try {
                    val responseBody = response.body()!!
                    val date = responseBody.date
                    val eurData = responseBody.eur
                    var currencyValue = 0.0

                    when (currency) {
                        "ada" -> currencyValue = eurData.ada
                        "aed" -> currencyValue = eurData.aed
                        "all" -> currencyValue = eurData.all
                        "vef" -> currencyValue = eurData.vef
                        "scr" -> currencyValue = eurData.scr
                    }

                    runOnUiThread {
                        binding.dateText.text = date
                        val n :Double= binding.euroEditText.text.toString().toDouble()
                        binding.result.text = "${n*currencyValue}"
                        binding.euroEditText.setText("")
                    }

                } catch (ex: Exception) {
                    Log.d("MainActivity", "Error: ${ex.message}")
                }
            }

            override fun onFailure(call: Call<Currency>, t: Throwable) {
                Log.d("MainActivity", "${t.message}")
            }
        })
    }


}

