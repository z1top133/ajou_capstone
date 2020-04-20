//package com.example.myapplication_practice
//
//import android.app.Activity
//import android.content.Intent
//import android.graphics.Bitmap
//import android.graphics.BitmapFactory
//import android.graphics.ImageFormat.JPEG
//import android.net.Uri
//import android.os.AsyncTask
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.provider.MediaStore
//import android.util.Log
//import android.widget.ImageView
//import android.widget.Toast
//import com.bumptech.glide.Glide
//import com.google.android.gms.tasks.OnFailureListener
//import com.google.firebase.firestore.FirebaseFirestore
//import com.google.firebase.ktx.Firebase
//import com.google.firebase.storage.FirebaseStorage
//import com.google.firebase.storage.StorageException
//import kotlinx.android.synthetic.main.activity_image_uploadtest.*
//import kotlinx.android.synthetic.main.activity_my_page.*
//import java.io.ByteArrayOutputStream
//import java.lang.Exception
//import java.net.URL
//
//class ImageUploadtest : AppCompatActivity() {
//    private val OPEN_GALLERY = 1
//    var storage = FirebaseStorage.getInstance("gs://travle-with.appspot.com")
//    val storageRef = storage.reference
//    val mountainsRef = storageRef.child("person1.png")
//    val mountainImageRef = storageRef.child("images/mountains.jpg")
//    //    val imageView = findViewById<ImageView>(R.id.profile_photo11)
//    val gsReference = storage.getReferenceFromUrl("gs://travle-with.appspot.com/profile")
////    mountainsRef.name == mountainImageRef.name
////    mountainsRef.path == mountainImageRef.path
//
//    private var filePath: Uri? = null
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_image_uploadtest)
//        //        image_upload_button11.setOnClickListener { openGallery() }
//        val storageReference = FirebaseStorage.getInstance("gs://travle-with.appspot.com").reference
//        val imageView = findViewById<ImageView>(R.id.profile_photo11)
//        Glide.with(this)
//            .load("https://firebasestorage.googleapis.com/v0/b/travle-with.appspot.com/o/upload_image%2Fprofile?alt=media&token=5a283076-2e56-42a0-9390-9ea52817a664")
//            .centerCrop()
//            .into(imageView)
//    }
//
//    class URLtoBitmapTask() : AsyncTask<Void, Void, Bitmap>() {
//        //액티비티에서 설정해줌
//        lateinit var url: URL
//
//        override fun doInBackground(vararg params: Void?): Bitmap {
//            val bitmap = BitmapFactory.decodeStream(url.openStream())
//            return bitmap
//        }
//
//        override fun onPreExecute() {
//            super.onPreExecute()
//
//        }
//
//        override fun onPostExecute(result: Bitmap) {
//            super.onPostExecute(result)
//        }
//    }
//
//
//    private fun openGallery() {
//        val intent: Intent = Intent(Intent.ACTION_GET_CONTENT)
//        intent.setType("image/*")
//        startActivityForResult(intent, OPEN_GALLERY)
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (resultCode == Activity.RESULT_OK) {
//            if (requestCode == OPEN_GALLERY) {
//                filePath = data?.data
//                var currentImageUrl: Uri? = data?.data
//                try {
//                    val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, currentImageUrl)
////                    val baos = ByteArrayOutputStream()
////                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
////                    val data2 = baos.toByteArray()
//                    var uploadTask = mountainsRef.putFile(currentImageUrl!!)
//                    uploadTask.addOnFailureListener {
//                        Toast.makeText(this, "Upload Failed", Toast.LENGTH_SHORT).show()
//                    }.addOnSuccessListener {
//                        Toast.makeText(this, "Upload Success", Toast.LENGTH_SHORT).show()
//                    }
//                    profile_photo11.setImageBitmap(bitmap)
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                }
//            } else {
//                Log.d("ActivityResult", "something wrong")
//            }
//        }
//    }
//
//    private fun uploadFile() {
//
//
//    }
//}
