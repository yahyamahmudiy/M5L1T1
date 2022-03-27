package com.example.m5l1t1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.m5l1t1.Adapter.ViewPagerAdapter
import com.example.m5l1t1.Fragment.FragmentHello
import com.example.m5l1t1.Fragment.FragmentSafe
import com.example.m5l1t1.Fragment.FragmentUse
import com.example.m5l1t1.Fragment.FragmentWelcome
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator

class MainActivity : AppCompatActivity() {
    lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }

    fun initViews(){
        viewPager = findViewById(R.id.view_pager)
        val indicator = findViewById<SpringDotsIndicator>(R.id.indicator)
        val skip_text = findViewById<TextView>(R.id.skip)
        val start_btn = findViewById<Button>(R.id.start_btn)
        var fragments = ArrayList<Fragment>()
        fragments = loadFragments()
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, fragments)
        viewPager.adapter = viewPagerAdapter
        indicator.setViewPager(viewPager)

        skip_text.setOnClickListener {
            viewPager.setCurrentItem(fragments.size-1,true)
        }

        start_btn.setOnClickListener {
            start_btn.visibility = View.GONE
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, FragmentWelcome())
                .commit()
        }

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }
            override fun onPageSelected(position: Int) {
                if(position == fragments.size-1){
                    skip_text.visibility = View.INVISIBLE
                    start_btn.visibility = View.VISIBLE
                } else {
                    skip_text.visibility = View.VISIBLE
                    start_btn.visibility = View.INVISIBLE
                }
            }

        })
    }

    private fun loadFragments(): ArrayList<Fragment> {
        val fragments = ArrayList<Fragment>()

        fragments.add(FragmentHello())
        fragments.add(FragmentSafe())
        fragments.add(FragmentUse())
        return fragments
    }
}