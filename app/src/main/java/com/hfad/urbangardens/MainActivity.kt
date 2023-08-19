package com.hfad.urbangardens

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {
    private lateinit var editTextName: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonLogin: Button
    private lateinit var buttonRegister: Button
    private lateinit var textViewResult: TextView
    private lateinit var viewModel: UserViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        //Careful with the main thread queries
        MyApplication.userDatabase = Room.databaseBuilder(
            applicationContext,
            UserDatabase::class.java,
            "user_database"
        ).allowMainThreadQueries().build()
        val intent = intent
        val userDao = MyApplication.userDatabase.userDao()
        editTextName = findViewById(R.id.editTextName)
        editTextPassword = findViewById(R.id.editTextPassword)
        buttonLogin = findViewById(R.id.buttonLogin)
        buttonRegister = findViewById(R.id.buttonRegister)
        textViewResult = findViewById(R.id.textViewResult)
        buttonLogin.setOnClickListener {
            val nickname = editTextName.text.toString().trim()
            val password = editTextPassword.text.toString().trim()
            var valid = false
            /*        GlobalScope.launch(Dispatchers.IO) {
            val user = userDao.getUserByCredentials(nickname, password)

            runOnUiThread {
                if (user != null) {
                }
            }
        }*/ val user = userDao.getUserByCredentials(nickname, password)
                    if (user != null) {
                        valid = true
                        MyApplication.userViewModel.loggedInUserId = user.id
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
}