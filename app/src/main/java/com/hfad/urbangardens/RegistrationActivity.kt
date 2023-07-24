package com.hfad.urbangardens
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegistrationActivity : AppCompatActivity() {

    private lateinit var editTextName: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextNickname: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var checkBoxTerms: CheckBox
    private lateinit var buttonRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        editTextName = findViewById(R.id.editTextName)
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextNickname = findViewById(R.id.editTextNickname)
        editTextPassword = findViewById(R.id.editTextPassword)
        checkBoxTerms = findViewById(R.id.checkBoxTerms)
        buttonRegister = findViewById(R.id.buttonRegister)

        buttonRegister.setOnClickListener {
            val name = editTextName.text.toString()
            val email = editTextEmail.text.toString()
            val nickname = editTextNickname.text.toString()
            val password = editTextPassword.text.toString()

            if (name.isNotEmpty() && email.isNotEmpty() && nickname.isNotEmpty() && password.isNotEmpty()) {
                if (checkBoxTerms.isChecked) {
                    // Perform registration logic here (e.g., saving data to a database)
                    // For simplicity, let's just display a toast message for successful registration.

                    val message = "Registration successful!\nName: $name\nEmail: $email\nNickname: $nickname\nPassword: $password"
                    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("name", name)
                    intent.putExtra("email", email)
                    intent.putExtra("nickname", nickname)
                    intent.putExtra("password", password)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Please accept the terms and conditions.", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please fill in all the fields.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}