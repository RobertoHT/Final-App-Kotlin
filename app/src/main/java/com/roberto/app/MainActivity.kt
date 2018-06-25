package com.roberto.app

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.roberto.app.adapter.PageAdapter
import com.roberto.app.fragments.ChatFragment
import com.roberto.app.fragments.InfoFragment
import com.roberto.app.fragments.RatesFragment
import com.roberto.mylibrary.ToolbarActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : ToolbarActivity() {
    private var prevBottomSelected: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbarToLoad(toolbarView as Toolbar)

        setUpViewPager(getPagerAdapter())
        setUpBottomNavigationBar()
    }

    private fun getPagerAdapter(): PageAdapter {
        val adapter = PageAdapter(supportFragmentManager)
        adapter.addFragments(InfoFragment())
        adapter.addFragments(RatesFragment())
        adapter.addFragments(ChatFragment())

        return adapter
    }

    private fun setUpViewPager(adapter: PageAdapter) {
        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                if (prevBottomSelected == null) {
                    bottomNavigation.menu.getItem(0).isChecked = false
                } else {
                    prevBottomSelected!!.isChecked = false
                }
                bottomNavigation.menu.getItem(position).isChecked = true
                prevBottomSelected = bottomNavigation.menu.getItem(position)
            }
        })
    }

    private fun setUpBottomNavigationBar() {
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.bottom_nav_info -> {
                    viewPager.currentItem = 0;true
                }
                R.id.bottom_nav_rates -> {
                    viewPager.currentItem = 1;true
                }
                R.id.bottom_nav_chat -> {
                    viewPager.currentItem = 2;true
                }
                else -> false
            }
        }
    }
}
