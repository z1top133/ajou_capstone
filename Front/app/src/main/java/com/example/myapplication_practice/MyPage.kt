package com.example.myapplication_practice

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_my_page.*
import java.io.File
import java.lang.Exception

class MyPage : AppCompatActivity() {

    var storage = FirebaseStorage.getInstance("gs://travle-with.appspot.com")
    val storageRef = storage.reference
    val mountainsRef = storageRef.child("upload_image/profile")
    //    val user_profile_photo = storageRef.child("upload_image/profile")
//    val localFile = File.createTempFile("images","jpg")
//    val imageView = findViewById<ImageView>(R.id.profile_photo)
    val localFile = File.createTempFile("images","jpg")

    private val OPEN_GALLERY = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page)
        if (firebaseAuth?.currentUser != null) {
            val filePath = mountainsRef.getFile(localFile)
            mountainsRef.getFile(localFile).addOnSuccessListener {
                var filePath = localFile.path
                var bitmap = BitmapFactory.decodeFile(filePath)
                profile_photo.setImageBitmap(bitmap)
            }
//            Glide.with(this)
//                .load("https://firebasestorage.googleapis.com/v0/b/travle-with.appspot.com/o/upload_image%2Fprofile?alt=media&token=a5b83eff-fb47-4efb-8e75-dc886d1a6cc7")
//                .centerCrop()
//                .into(profile_photo)
            Log.d("abcdef",storageRef.toString())
            var user_one: User_data? = null
            val db_ref = FirebaseFirestore.getInstance().collection("Users")
                .document(firebaseAuth!!.currentUser!!.uid)
            db_ref.get().addOnSuccessListener { documentSnapshot ->
                user_one = documentSnapshot.toObject(User_data::class.java)
                user_email.setText(user_one?.email)
                user_password.setText(user_one?.password)
//                Glide.with(this)
//                    .load(storageRef)
//                    .into(imageView)
            }

        }
        image_upload_button.setOnClickListener { openGallery() }
    }

    private fun openGallery() {
        val intent: Intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.setType("image/*")
        startActivityForResult(intent, OPEN_GALLERY)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == OPEN_GALLERY) {
                var currentImageUrl: Uri? = data?.data
                try {
                    val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, currentImageUrl)
//                    val baos = ByteArrayOutputStream()
//                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
//                    val data2 = baos.toByteArray()
                    var uploadTask = mountainsRef.putFile(currentImageUrl!!)
                    uploadTask.addOnFailureListener {
                        Toast.makeText(this, "Upload Failed", Toast.LENGTH_SHORT).show()
                    }.addOnSuccessListener {
                        Toast.makeText(this, "Upload Success", Toast.LENGTH_SHORT).show()
                    }
                    profile_photo.setImageBitmap(bitmap)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            } else {
                Log.d("ActivityResult", "something wrong")
            }
        }
    }
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if(resultCode == Activity.RESULT_OK){
//            if(requestCode == OPEN_GALLERY){
//                var currentImageUrl : Uri? =data?.data
//                try{
//                    val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, currentImageUrl)
//                    profile_photo.setImageBitmap(bitmap)
//                }catch(e:Exception){
//                    e.printStackTrace()
//                }
//            }else{
//                Log.d("ActivityResult", "something wrong")
//            }
//        }
//    }
}


