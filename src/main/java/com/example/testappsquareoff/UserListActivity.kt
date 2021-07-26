package com.example.testappsquareoff

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class UserListActivity : AppCompatActivity() {

    private lateinit var dbref : DatabaseReference
    private lateinit var userRecyclerview : RecyclerView
    private lateinit var userArrayList : ArrayList<User>
    private lateinit var manager: GridLayoutManager
    var rotation: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)

        if(resources.configuration.orientation==Configuration.ORIENTATION_LANDSCAPE) {
            rotation=2


            userRecyclerview = findViewById(R.id.userList)
            userRecyclerview.layoutManager = GridLayoutManager(this, 2)
            userRecyclerview.setHasFixedSize(true)
        }
        else{
            rotation=1
            userRecyclerview = findViewById(R.id.userList)
            userRecyclerview.layoutManager = LinearLayoutManager(this)
            userRecyclerview.setHasFixedSize(true)
        }

        userArrayList = arrayListOf<User>()
        getUserData()

    }

    private fun getUserData() {

        dbref = FirebaseDatabase.getInstance().getReference("trns")

        dbref.addValueEventListener(object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()){

                    for (userSnapshot in snapshot.children){


                        val user = userSnapshot.getValue(User::class.java)
                        userArrayList.add(user!!)

                    }


                    userRecyclerview.adapter = MyAdapter(userArrayList,rotation)


                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })

    }
}