package com.example.myapplication_practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_package_page.*

class PackagePage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_package_page)
        val travelList = ArrayList<Travel>()
        for (i in 0 until 10) {
            travelList.add(Travel(R.drawable.italy, "Italy" + i))
        }
        val adapter = RecyclerViewAdapter(travelList, LayoutInflater.from(this@PackagePage))
        package_travel_container.adapter = adapter
        package_travel_container.layoutManager = LinearLayoutManager(this@PackagePage)
    }
}
