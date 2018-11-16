package com.everis.owl.formsjson

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.everis.owl.formsjson.dataModel.FormData


class AdapterFragment(fm: FragmentManager?, ctx : Context, listForms : List<FormData>, view : IMainView) :  FragmentStatePagerAdapter(fm) {

    var mContext : Context? = null
    var mListForms : List<FormData>? = null
    var mView : IMainView? = null

    init {
        this.mContext = ctx
        this.mListForms = listForms
        this.mView = view
    }


    override fun getItem(p0: Int): Fragment {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        val formFragment : FormFragment = FormFragment.newInstance(mListForms!!.get(p0))
        formFragment.setMainViewListener(mView!!)
        return formFragment
        //return FormFragment.newInstance(mListForms!!.get(p0))

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