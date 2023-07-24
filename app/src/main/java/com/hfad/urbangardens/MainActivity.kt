package com.hfad.urbangardens

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editTextName: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonLogin: Button
    private lateinit var buttonRegister: Button
    private lateinit var textViewResult: TextView
    var users = mutableListOf(UserRegistration("u",
        "user@gmail.com",
        "user",
        "g"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = intent
        users.add(
            UserRegistration(
                intent.getStringExtra("name").toString(),
                intent.getStringExtra("email").toString(),
                intent.getStringExtra("nickname").toString(),
                intent.getStringExtra("password").toString()
))
        editTextName = findViewById(R.id.editTextName)
        editTextPassword = findViewById(R.id.editTextPassword)
        buttonLogin = findViewById(R.id.buttonLogin)
        buttonRegister = findViewById(R.id.buttonRegister)
        textViewResult = findViewById(R.id.textViewResult)

        buttonLogin.setOnClickListener {
            val name = editTextName.text.toString()
            val password = editTextPassword.text.toString()
            var valid = false
            for(element in users) {
                if(name == element.name) {
                    if(password == element.password) {
                        valid = true
                        break
                    }
                }
            }
            if (valid) {
                textViewResult.text = "Login successful!"
                val intent = Intent(this, MainScreen::class.java)
                startActivity(intent)
            } else {
                textViewResult.text = "Login failed. Invalid credentials."
            }
            }
            buttonRegister.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }
    }
    fun addUser(user: UserRegistration) {
        users.add(user)
    }
}