package com.example.myapplication_practice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_login_page.*
import kotlinx.android.synthetic.main.activity_my_page.*

var firebaseAuth: FirebaseAuth? = null
var user_email_data : String ? = null
class LoginPage : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)
        firebaseAuth = FirebaseAuth.getInstance()
        log_in_button.setOnClickListener {
                loginEmail()
            val intent = Intent(this@LoginPage, MainPage::class.java)
            startActivity(intent)
            Toast.makeText(this,"Success",Toast.LENGTH_SHORT).show()
        }
        sign_up_button.setOnClickListener {
            val intent = Intent(this@LoginPage, SignUpPage::class.java)
            startActivity(intent)
        }
    }

    private fun loginEmail(){
        user_email_data = log_in_email.text.toString()
//        val db = FirebaseFirestore.getInstance()
//        val current_user = FirebaseAuth.getInstance().currentUser!!.uid
        firebaseAuth!!.signInWithEmailAndPassword(log_in_email.text.toString(),log_in_password.text.toString())
            .addOnCompleteListener(this){
                if(it.isSuccessful){
                    Toast.makeText(this,"Success",Toast.LENGTH_SHORT).show()
//                    val db = FirebaseFirestore.getInstance().collection("Users").document(
//                        current_user)
//                    var user_one : User_data ? = null
//                    db.get().addOnSuccessListener { documentSnapshot ->
//                        user_one = documentSnapshot.toObject(User_data::class.java)
//                        user_email.setText(user_one?.email)
//                        user_password.setText(user_one?.password)
//                    }
//                    val intent = Intent(this, MainPage::class.java)
//                    startActivity(intent)
                }else{
                    Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
                }
            }
    }
}
