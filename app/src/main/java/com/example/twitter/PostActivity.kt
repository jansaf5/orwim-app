package com.example.twitter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.twitter.data.Post
import com.example.twitter.data.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class PostActivity : AppCompatActivity() {

    private lateinit var post: EditText
    private lateinit var button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)
        post = findViewById(R.id.postText)
        button = findViewById(R.id.button)
        var user= Firebase.auth.currentUser


        val database =
            FirebaseDatabase.getInstance("https://twitter-6756f-default-rtdb.europe-west1.firebasedatabase.app/")
        val myRef = database.getReference("users")
        if(user!=null){
            val uID=user.uid
            val username= myRef.child(uID).child("username").get().toString()
            button.setOnClickListener {
                var txtPost: String = post.text.toString()
                sendPost(username,txtPost)
            }
        }








    }


     fun sendPost(user: String, text: String) {

         val post= Post(text,user)
        val database =
            FirebaseDatabase.getInstance("https://twitter-6756f-default-rtdb.europe-west1.firebasedatabase.app/")
        val myRef = database.getReference("posts")
        val postKey=myRef.push().key


         if (postKey != null) {
             myRef.child(postKey).setValue(post)
         }











    }
}