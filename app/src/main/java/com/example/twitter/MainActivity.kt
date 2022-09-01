package com.example.twitter

import android.content.Intent
import android.graphics.Insets.add
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.twitter.data.Post
import com.example.twitter.data.User
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import example.javatpoint.com.kotlincustomlistview.PersonRecyclerAdapter
import org.json.JSONArray
import org.json.JSONObject


class MainActivity : AppCompatActivity() {
    private lateinit var personAdapter: PersonRecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        personAdapter = PersonRecyclerAdapter()
        personAdapter.postItemsList(setupData())
        initView()

        findViewById<FloatingActionButton>(R.id.floatingButton).setOnClickListener{
            val intent = Intent(this, PostActivity::class.java)

            startActivity(intent)
        }
    }
    private fun initView() {
        findViewById<RecyclerView>(R.id.recyclerView).apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = personAdapter
        }
    }
    private fun setupData(): ArrayList<Post> {
        val list = ArrayList<Post>()
    //    val database = FirebaseDatabase.getInstance("https://twitter-6756f-default-rtdb.europe-west1.firebasedatabase.app/")
    //    val myRef = database.getReference("posts")
    //    var postsString=myRef.get().toString()
    //    var posts= JSONArray(postsString)
    //    for(i in 0.. posts.length()){
    //        var items = JSONArray(posts.getJSONObject(i))
    //        list.add(
    //        Post(
    //            items[0] as String,
    //            items[1] as String
    //
    //            )
    //        )
    //    }
        list.add(
            Post(
                "Test Test Test",
                "Pero Perić",

            )
        )
        list.add(
            Post(
                "Test Test Test 2",
                "Ivan Ivić"

            )
        )



        return list
    }
}


