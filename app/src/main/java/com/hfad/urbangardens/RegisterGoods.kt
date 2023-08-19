package com.hfad.urbangardens

import android.annotation.SuppressLint
import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import java.text.DecimalFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.*

class RegisterGoods : AppCompatActivity() {
    lateinit var nameView: TextView
    lateinit var imageView: ImageView
    lateinit var buttonAdd: Button
    private lateinit var editTextVolume: EditText
    private lateinit var editTextPrice: EditText
    private lateinit var volumeUnits: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_goods)
        val intent = intent
        nameView = findViewById(R.id.name_text)
        imageView = findViewById(R.id.resourceImage)
        editTextVolume = findViewById(R.id.editVolume)
        editTextPrice = findViewById(R.id.editUnitPrice)
        buttonAdd = findViewById(R.id.buttonAddPurchase)
        volumeUnits = findViewById(R.id.textUnits)
        volumeUnits.text = intent.getStringExtra("units")

        val item = intent.getStringExtra("position")
        nameView.text = item

        imageView.setImageResource(intent.getIntExtra("image",R.drawable.water))
        editTextPrice.addTextChangedListener(object : TextWatcher {
            private val df = DecimalFormat("#,###.##")
            private var isEditing = false

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (isEditing || s.isNullOrBlank()) {
                    return
                }
                isEditing = true

                try {
                    val parsed = df.parse(s.toString().replace(",", ""))
                    val formatted = df.format(parsed)
                    editTextPrice.setText(formatted)
                    editTextPrice.setSelection(formatted.length)
                } catch (e: Exception) {
                    // Handle the exception or show an error message if needed
                }

                isEditing = false
            }
        })
        buttonAdd.setOnClickListener {
            val volume = editTextVolume.text.toString().toIntOrNull()
            val price = editTextPrice.text.toString().replace(",","").toIntOrNull()
            val timestamp = System.currentTimeMillis()
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = timestamp
            val month = calendar.get(Calendar.MONTH)
            val addItemDao = MyApplication.userDatabase.addItemDao()
            val loggedInUserId = MyApplication.userViewModel.loggedInUserId

            if (volume != null && price != null) {
                val message = "Registration successful!\nname: ${nameView.text}\nVolume: $volume\nMonth: $month"
                Toast.makeText(this, message, Toast.LENGTH_LONG).show()
                val addItem = AddItem(
                    userId = loggedInUserId?:-1,
                    item = item?:"noItem",
                    unitsBought = volume.toDouble(),
                    unitPrice = price.toDouble(),
                    month = month,
                    timestamp = timestamp)
                addItemDao.insertItem(addItem)
                val intent = Intent(this, MainScreen::class.java)
                startActivity(intent)
            }
            else {
                Toast.makeText(this, "Please fill in all the fields.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}