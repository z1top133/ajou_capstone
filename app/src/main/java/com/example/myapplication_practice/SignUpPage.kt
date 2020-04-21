package com.example.myapplication_practice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_sign_up_page.*

//private var firebaseAuth: FirebaseAuth? = null

class SignUpPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_page)
//        val signUpEmail: String = sign_up_email.text.toString()
//        val signUpPassword: String = sign_up_password.text.toString()
        firebaseAuth = FirebaseAuth.getInstance()
        val sign_up_age = ArrayList<Int>()

        for (i in 1985 until 2002) {
            sign_up_age.add(i)
        }
        age_spinner.adapter =
            ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, sign_up_age)
//        val db = FirebaseFirestore.getInstance()
        signup_button.setOnClickListener {
            //            val user = User_data( sign_up_email.text.toString(), sign_up_password.text.toString())
            createEmail()
//            db.collection("Users").document(sign_up_email.text.toString()).set(user)
        }

    }

    fun createEmail() {
        val db = FirebaseFirestore.getInstance()
        var gender: Int? = null
        var age: String? = age_spinner.selectedItem.toString()

        if (gender_male.isChecked == true && gender_female.isChecked == false) {
            gender = 0
        } else if (gender_male.isChecked == false && gender_female.isChecked == true) {
            gender = 1
        } else {
            Toast.makeText(this, "Check gender please", Toast.LENGTH_SHORT).show()
            finish()
        }
        val user = User_data(
            sign_up_email.text.toString(),
            sign_up_password.text.toString(),
            gender,
            age,
            travel_Style_information(),
            travel_Spot_information(),
            available_language_information()
        )
        firebaseAuth!!.createUserWithEmailAndPassword(
//            signUpEmail, signUpPassword
            sign_up_email.text.toString(),
            sign_up_password.text.toString()
        )
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Authentication success.", Toast.LENGTH_SHORT).show()
                    db.collection("Users").document(FirebaseAuth.getInstance().currentUser!!.uid)
                        .set(user)
//                    db.collection("Users").document(FirebaseAuth.getInstance().currentUser!!.uid).set(user)
                    val intent = Intent(this, MainPage::class.java)
                    startActivity(intent)
//                  FirebaseAuth.getInstance().signOut()
                } else {
                    Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT).show()
                }
            }
    }

    fun travel_Style_information(): String? {
        var travel_Style: String = ""
        if (travelStyle_Photo.isChecked) {
            travel_Style = "photo"
        }
        if (travelStyle_Food.isChecked) {
            travel_Style = travel_Style + "," + "food"
        }
        if (travelStyle_View.isChecked) {
            travel_Style = travel_Style + "," + "view"
        }
        return travel_Style
    }

    fun travel_Spot_information(): String? {
        var travel_Spot: String = ""
        if (korea.isChecked) {
            travel_Spot = "korea"
        }
        if (abroad.isChecked) {
            travel_Spot = travel_Spot + "," + "abroad"
        }
        return travel_Spot
    }

    fun available_language_information(): String? {
        var language: String = ""
        if (english.isChecked) {
            language = "english"
        }
        if (chinese.isChecked) {
            language = language + ","+"chinese"
        }
        if (japanese.isChecked) {
            language = language + ","+"japanese"
        }
        if (french.isChecked) {
            language = language + ","+"french"
        }
        if (german.isChecked) {
            language = language + ","+"german"
        }
        return language

    }
}

data class User_data(
    val email: String? = null,
    val password: String? = null,
    var gender: Int? = null,
    var age: String? = null,
    var travel_Style: String? = null,
    var travel_Spot: String? = null,
    var available_language: String? = null
)
