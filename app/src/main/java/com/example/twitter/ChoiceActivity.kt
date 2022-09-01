package com.example.twitter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class ChoiceActivity : AppCompatActivity() {
    //private lateinit var register : View;
    //private lateinit var login : View;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.choice_activity)
        //login= findViewById(R.id.login)
        //register=findViewById(R.id.register)

        findViewById<Button>(R.id.register).setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.login).setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }


}