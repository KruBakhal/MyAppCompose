package com.example.myappcompose

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myappcompose.shopapp.MainActivity
import com.google.android.material.button.MaterialButton

class AppMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_main)
        findViewById<MaterialButton>(R.id.btn_compose).setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java).putExtra("compose", true))
        }
        findViewById<MaterialButton>(R.id.btn_practice).setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java).putExtra("compose", false))

        }
    }
}