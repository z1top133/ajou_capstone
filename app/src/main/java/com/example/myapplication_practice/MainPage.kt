package com.example.myapplication_practice

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_chattingroom_list__page.*
import kotlinx.android.synthetic.main.activity_main_page.*
import kotlinx.android.synthetic.main.activity_main_page.tab_layout
import kotlinx.android.synthetic.main.activity_main_page.view_pager

val RC_SIGN_IN = 9001;

var firebaseAuth: FirebaseAuth? = null
class MainPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)
        if(firebaseAuth?.currentUser?.uid == null){
            FirebaseAuth.getInstance().signOut()
        }
//        FirebaseAuth.getInstance().signOut()
//        val travelList = ArrayList<Travel>()
//        for (i in 0 until 10) {
//            travelList.add(Travel(R.drawable.italy, "Italy" + i))
//        }
//        val adapter = RecyclerViewAdapter(travelList, LayoutInflater.from(this@MainPage))
//        travel_container.adapter = adapter
//        travel_container.layoutManager = LinearLayoutManager(this@MainPage)//recyclerView
        tab_layout.addTab(tab_layout.newTab().setText("ONE"))
        tab_layout.addTab(tab_layout.newTab().setText("TWO"))
        tab_layout.addTab(tab_layout.newTab().setText("THREE"))
        val tabs = tab_layout.getChildAt(0) as ViewGroup

        for(i in 0 until tabs.childCount){
            val tab = tabs.getChildAt(i)
            val layoutParams = tab.layoutParams as LinearLayout.LayoutParams
            layoutParams.marginEnd = 12.dp()
            layoutParams.marginStart = 12.dp()
            layoutParams.width = 10.dp()
            tab.layoutParams = layoutParams
            tab_layout.requestLayout()
        }

        val adapter = FragmentPagerAdapter(supportFragmentManager,3)
        view_pager.adapter = adapter
        view_pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_layout))

        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
                view_pager.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                view_pager.currentItem = tab!!.position
            }
        })
        view_pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_layout))

        person.setOnClickListener {
            //            login()
            if (FirebaseAuth.getInstance().currentUser == null) {
                val intent = Intent(this@MainPage, LoginPage::class.java)
                startActivity(intent)
            }else{
                val intent = Intent(this@MainPage, MyPage::class.java)
                startActivity(intent)
            }
        }
        logout_button.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            Toast.makeText(this, "Log_out success.", Toast.LENGTH_SHORT).show()
        }
    }
    fun Int.dp(): Int{
        val metrics = resources.displayMetrics
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), metrics).toInt()
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if (requestCode == RC_SIGN_IN) {
//            val response = IdpResponse.fromResultIntent(data)
//
//            if (resultCode == Activity.RESULT_OK) {
//                // Successfully signed in
//                val user = FirebaseAuth.getInstance().currentUser
//                // ...
//            } else {
//
//            }
//        }
//    }

//    fun login() {
//        if (FirebaseAuth.getInstance().currentUser == null) {
//            val providers = arrayListOf(
//                AuthUI.IdpConfig.EmailBuilder().build()
//            )
//            startActivityForResult(
//                AuthUI.getInstance()
//                    .createSignInIntentBuilder()
//                    .setAvailableProviders(providers)
//                    .build(),
//                RC_SIGN_IN
//            )
//        }
//    }

//    fun logout() {
//        AuthUI.getInstance()
//            .signOut(this)
//            .addOnCompleteListener {
//                Toast.makeText(this,"Logout success.", Toast.LENGTH_SHORT).show()
//            }
//    }
}


open class RecyclerViewAdapter(
    val itemList: ArrayList<Travel>,
    val inflater: LayoutInflater
) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val travelName: TextView
        val travelPhoto: ImageView

        init {
            travelName = itemView.findViewById(R.id.travel_name)
            travelPhoto = itemView.findViewById(R.id.travel_photo)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.item_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.travelName.setText(itemList.get(position).name)
        holder.travelPhoto.setImageResource(itemList.get(position).Photo)

    }
}

class Travel(val Photo: Int, val name: String) {

}

class user(
    val email: String, val password: String, val sex: Boolean, val age: Int,
    val travelStyle: Int, val travelSpot: Int, val Photo: Int, val profile: String,
    val language: Int
) {

}