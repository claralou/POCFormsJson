package com.everis.owl.formsjson

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter


class AdapterFragment(fm: FragmentManager?, ctx : Context, listForms : List<FormData>) :  FragmentStatePagerAdapter(fm) {

    var mContext : Context? = null
    var mListForms : List<FormData>? = null

    init {
        this.mContext = ctx
        this.mListForms = listForms
    }

    override fun getItem(p0: Int): Fragment {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return FormFragment.newInstance(mListForms!!.get(p0))

        /*when (p0) {
            0 -> return FormFragment.newInstance(R.layout.basic_question_button_layout, mListForms!!.get(0))
            1 -> return FormFragment.newInstance(R.layout.basic_question_text_layout, mListForms!!.get(1))
            2 -> return FormFragment.newInstance(R.layout.basic_question_button_layout, mListForms!!.get(2))
            3 -> return FormFragment.newInstance(R.layout.basic_question_text_layout, mListForms!!.get(3))
            else -> return FormFragment.newInstance(R.layout.basic_question_text_layout, mListForms!!.get(0))
        }*/

    }


    override fun getCount(): Int {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates
        this.mListForms?.let { list ->
            return list.size
        }
        return 0
    }
}