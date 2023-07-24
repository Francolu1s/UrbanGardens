package com.hfad.urbangardens

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import java.text.DecimalFormat

class RegisterGoods : AppCompatActivity() {
    lateinit var nameView: TextView
    lateinit var imageView: ImageView
    lateinit var buttonAdd: Button
    private lateinit var editTextVolume: EditText
    private lateinit var editTextPrice: EditText
    private lateinit var editTextTotaUnits: EditText
    private lateinit var editTextMonth: EditText

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_goods)
        val intent = intent

        nameView = findViewById(R.id.name_text)
        imageView = findViewById(R.id.resourceImage)
        editTextVolume = findViewById(R.id.editVolume)
        editTextPrice = findViewById(R.id.editUnitPrice)
        editTextTotaUnits = findViewById(R.id.editTotalUnits)
        editTextMonth = findViewById(R.id.editMonth)
        buttonAdd = findViewById(R.id.buttonAddPurchase)
        nameView.text = intent.getStringExtra("position")
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
            val volume = editTextVolume.text.toString()
            val price = editTextPrice.text.toString()
            val totalUnits = editTextTotaUnits.text.toString()
            val month = editTextMonth.text.toString()
            if (volume.isNotEmpty() && price.isNotEmpty() && totalUnits.isNotEmpty() && month.isNotEmpty()) {
                val message = "Registration successful!\nname: ${nameView.text}\nVolume: $volume\nTotal Units: $totalUnits\nMonth: $month"
                Toast.makeText(this, message, Toast.LENGTH_LONG).show()
                val intent = Intent(this, MainScreen::class.java)
                startActivity(intent)
            }
            else {
                Toast.makeText(this, "Please fill in all the fields.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}