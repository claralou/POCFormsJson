package com.everis.owl.formsjson

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), FormFragment.OnFragmentInteractionListener {
    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    lateinit var mAdapter : AdapterFragment
    var mListForms : List<FormData>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mListForms = ReadJsonUtils.readForms(this,"formSteps.json")

        mAdapter = AdapterFragment(supportFragmentManager, this, mListForms!!)

        viewPager.adapter = mAdapter
        buttonNavigation.setOnClickListener {
            var position : Int = viewPager.currentItem
            if (position < mAdapter.count - 1) {
                position++;
                viewPager.currentItem = position

            }
        }


    }
}
