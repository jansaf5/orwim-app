package com.example.twitter

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var email: EditText
    private lateinit var password: EditText
    private  lateinit var login: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = Firebase.auth
        email = findViewById(R.id.email)
        password=findViewById(R.id.password)
        login=findViewById(R.id.login)

        val database = FirebaseDatabase.getInstance("https://twitter-6756f-default-rtdb.europe-west1.firebasedatabase.app/")
        val myRef = database.getReference("users")


        login.setOnClickListener{
            var txtPass : String=password.text.toString()
            var txtEmail: String=email.text.toString()

            if(TextUtils.isEmpty(txtPass) || TextUtils.isEmpty(txtEmail)){
                Toast.makeText(this,"Fields not filled!",Toast.LENGTH_SHORT).show()

            }else if(txtPass.length<6){
                Toast.makeText(this,"Password needs to be at least 6 characters long!",Toast.LENGTH_SHORT).show()
            }else{
                userLogin(txtEmail,txtPass)
                Toast.makeText(this,"Logged in!",Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                //intent.putExtra("loginUser",txtEmail)
                startActivity(intent)
            }
        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            currentUser.reload();
        }
    }

    private fun userLogin(email:String, password:String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
    }

    private fun updateUI(user: FirebaseUser?) {

    }
}