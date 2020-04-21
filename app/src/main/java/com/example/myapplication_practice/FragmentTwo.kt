package com.example.myapplication_practice


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_package_page.*

class FragmentTwo : Fragment(){
    lateinit var  recyclerView1: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.activity_korea_page,container,false)
        val travelList = ArrayList<Travel>()
        for (i in 0 until 10) {
            travelList.add(Travel(R.drawable.italy, "Italy" + i))
        }
        recyclerView1 = rootView.findViewById(R.id.korea_travel_container)as RecyclerView
//        val adapter = RecyclerViewAdapter(travelList, LayoutInflater.from(this))
//        package_travel_container.adapter = adapter
//        package_travel_container.layoutManager = LinearLayoutManager(this@FragmentOne)
        recyclerView1.layoutManager = LinearLayoutManager(requireContext())
        recyclerView1.adapter = RecyclerViewAdapter(travelList, LayoutInflater.from(requireContext()))
        return rootView
    }
}