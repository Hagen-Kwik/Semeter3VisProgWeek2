package com.example.refreshmentexercisesemester3week1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        Handler().postDelayed({
            val intent = Intent(this, home::class.java)
            //since ga pake login page for now makae THIS INTENT TA LANGSUNG KE FRAGMENT HOME
            startActivity(intent)
            finish()
        }, 3000)
    }
}