package com.kicsit.myfirstapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        //Show received data if any
        val dataBundle = intent.extras;
        val dataSendText: String? = dataBundle?.getString("data_send_text");

        if(dataSendText != null){
            Toast.makeText(applicationContext, dataSendText, Toast.LENGTH_LONG).show()
        }


        val prev_btn = findViewById<Button>(R.id.prev_btn)

        prev_btn.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}