package com.example.myapplication_practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_my_page.*

class MyPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page)

        if(firebaseAuth?.currentUser!= null) {
            var user_one: User_data? = null
            val db_ref = FirebaseFirestore.getInstance().collection("Users")
                .document(firebaseAuth!!.currentUser!!.uid)
            db_ref.get().addOnSuccessListener { documentSnapshot ->
                user_one = documentSnapshot.toObject(User_data::class.java)
                user_email.setText(user_one?.email)
                user_password.setText(user_one?.password)
            }
        }


    }
}
