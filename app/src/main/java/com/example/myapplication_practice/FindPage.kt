package com.example.myapplication_practice

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_find_page.*

class FindPage : AppCompatActivity() {
    var user_email:Any? = null
    var auth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_page)
        find_button.setOnClickListener {
            find_password(this@FindPage)
        }
    }

    fun find_password(context: Context) {
        auth.setLanguageCode("ko")
        user_email = user_email_for_find.text
        FirebaseAuth.getInstance().sendPasswordResetEmail(user_email.toString())
            .addOnCompleteListener{ task ->
                if(task.isSuccessful){
                    Toast.makeText(this, "Check your email", Toast.LENGTH_SHORT).show()
                }

            }
    }
}
