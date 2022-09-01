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
import com.example.twitter.data.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.database.FirebaseDatabase






class SignUpActivity : AppCompatActivity() {
     private lateinit var auth: FirebaseAuth
     private  lateinit var username: EditText
     private lateinit var email: EditText
     private lateinit var password: EditText
     private  lateinit var register: Button
    //private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        val database = FirebaseDatabase.getInstance("https://twitter-6756f-default-rtdb.europe-west1.firebasedatabase.app/")
        val myRef = database.getReference("users")



        auth = Firebase.auth
        username=findViewById(R.id.username)
        email= findViewById(R.id.email)
        password=findViewById(R.id.password)
        register=findViewById(R.id.register)

        register.setOnClickListener{
            val txtEmail:String= email.text.toString()
            val txtPass : String=password.text.toString()
            val txtUser: String=username.text.toString()
            if(TextUtils.isEmpty(txtEmail) || TextUtils.isEmpty(txtPass) || TextUtils.isEmpty(txtUser)){
                Toast.makeText(this,"Fields not filled!",Toast.LENGTH_SHORT).show()

            }else if(txtPass.length<6){
                Toast.makeText(this,"Password needs to be at least 6 characters long!",Toast.LENGTH_SHORT).show()
            }

            else{
                createAccount(txtEmail,txtPass)
                Toast.makeText(this,"Account successfully created !",Toast.LENGTH_SHORT).show()
                val user=User (txtUser,txtEmail)
                val userId= auth.currentUser?.uid
                if (userId != null) {
                    myRef.child(userId).setValue(user)
                }

                val intent = Intent(this, MainActivity::class.java)

                startActivity(intent)


            }
        }

    }
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            currentUser.reload()
        }
    }

    private fun createAccount(email: String, password: String) {

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }

    }


    private fun updateUI(user: FirebaseUser?) {

    }




}