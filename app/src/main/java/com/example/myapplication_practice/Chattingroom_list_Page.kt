package com.example.myapplication_practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_chattingroom_list__page.*

class Chattingroom_list_Page : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chattingroom_list__page)

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
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                view_pager.setCurrentItem(tab!!.position)
            }
        })

    }
    fun Int.dp(): Int{
        val metrics = resources.displayMetrics
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), metrics).toInt()
    }
}

class FragmentPagerAdapter(
    fragmentManager: FragmentManager,
    val tabCount: Int
) : FragmentStatePagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                return FragmentOne()
            }
            1 -> {
                return FragmentTwo()
            }
            2 -> {
                return FragmentThree()
            }
            else -> return FragmentOne()
        }
    }

    override fun getCount(): Int {
        return tabCount
    }
}


//class Pager_Adapter(
//    val layoutInflater: LayoutInflater
//) : PagerAdapter() {
//
//    override fun instantiateItem(container: ViewGroup, position: Int): Any {
//        when (position) {
//            0 -> {
//                val view = layoutInflater.inflate(R.layout.activity_package_page, container, false)
//                container.addView(view)
//                return view
//            }
//            1 -> {
//                val view = layoutInflater.inflate(R.layout.fragment1, container, false)
//                container.addView(view)
//                return view
//            }
//            2 -> {
//                val view = layoutInflater.inflate(R.layout.fragment1, container, false)
//                container.addView(view)
//                return view
//            }
//            else -> {
//                val view = layoutInflater.inflate(R.layout.fragment1, container, false)
//                container.addView(view)
//                return view
//            }
//        }
//    }
//
//    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
//        container.removeView(`object` as View)
//    }
//
//    override fun isViewFromObject(view: View, `object`: Any): Boolean {
//        return view === `object` as View
//    }
//
//    override fun getCount(): Int {
//        return 3
//    }
//}


