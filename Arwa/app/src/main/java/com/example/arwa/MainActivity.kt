package com.example.arwa

import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.arwa.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    var textItemList: ArrayList<TextItem> = ArrayList()
    var balance: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Bank Account"

        // Shared Preference
        balance = loadBalance()
        binding.balanceTextView.text = "Balance: $balance"

        // Deposit
        binding.depositButton.setOnClickListener {

            // User input
            val userInput = binding.depositEditText.text.toString().toDouble()

            // Balance
            balance += userInput
            binding.balanceTextView.text = "Balance: $balance"
            binding.balanceTextView.setTextColor(Color.BLACK)
            saveBalance()

            // Recyclerview
            textItemList.add(TextItem("Deposit: $userInput", userInput))
            binding.myRecyclerView.adapter = MyAdapter(textItemList)
            binding.depositEditText.setText("")

            // Auto scroll
            binding.myRecyclerView.smoothScrollToPosition(textItemList.size - 1)
        }

        // Withdrawal
        binding.withdrawalButton.setOnClickListener {
            // User input
            val userInput = binding.withdrawalEditText.text.toString().toDouble()

            // Balance checked
            if (balance == 0.0) {
                Toast.makeText(this, "Your balance = 0.0 you can not withdrawal", Toast.LENGTH_LONG)
                    .show()
            } else if (userInput > balance) {
                balance -= 20
                binding.balanceTextView.text = "Balance: $balance"
                binding.balanceTextView.setTextColor(Color.RED)
                textItemList.add(TextItem("Negative Balance Fee: 20", 20.0))
            } else {
                textItemList.add(TextItem("Withdrawal: $userInput", userInput))
                balance -= userInput
                binding.balanceTextView.text = "Balance: $balance"
                binding.balanceTextView.setTextColor(Color.BLACK)
            }

            // Balance save
            saveBalance()

            // RecyclerView
            binding.myRecyclerView.adapter = MyAdapter(textItemList)
            binding.withdrawalEditText.setText("")

            // Auto scroll
            binding.myRecyclerView.smoothScrollToPosition(textItemList.size - 1)
        }

    }

    // Shared Preference
    private fun saveBalance() {
        val sharedPreferences: SharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.apply {
            putString("BALANCE", "$balance")
        }.apply()
    }

    private fun loadBalance() : Double {
        val sharedPreferences: SharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE)
        return sharedPreferences.getString("BALANCE", null)!!.toDouble()
    }

    // Save Balance value
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putDouble("balance", balance)
    }

    // Restore Balance value
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        balance = savedInstanceState.getDouble("balance")
    }

    // Menu (Delete icon)
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.delete_items, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.deleteButton -> {
                textItemList.removeAll(textItemList)
                val rv: RecyclerView = findViewById(R.id.myRecyclerView)
                rv.adapter = MyAdapter(textItemList)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}