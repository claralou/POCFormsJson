package com.everis.owl.formsjson

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.Toast
import com.everis.owl.formsjson.dataModel.ConditionsItem
import com.everis.owl.formsjson.dataModel.FormConditions
import com.everis.owl.formsjson.dataModel.FormData
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), FormFragment.OnFragmentInteractionListener, IMainView {
    override fun continueInSameStep() {
        var position : Int = viewPager.currentItem
        viewPager.currentItem = position
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun previousStep() {
        var position : Int = viewPager.currentItem
        if (position > 0) {
            position--
        }
        viewPager.currentItem = position
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    override fun onFragmentInteraction() {
        Log.d("TAG", "interaction")
    }

    lateinit var mAdapter : AdapterFragment
    var mListForms : List<FormData>? = null
    var mListConditions: List<FormConditions>? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mListForms = ReadJsonUtils.readForms(this,"formSteps.json")
        mListConditions = ReadJsonUtils.readFormsConditions(this, "formConditions.json")

        mAdapter = AdapterFragment(supportFragmentManager, this, mListForms!!, this)

        viewPager.adapter = mAdapter
        buttonNavigation.setOnClickListener {
            var position : Int = viewPager.currentItem
            /*if (position == 2) {
                position+=3
                viewPager.currentItem = position
            } else */if (position < mAdapter.count - 1) {
                position++
                viewPager.currentItem = position

            }
        }


    }

    fun evaluateConditionalStep(currentPosition : Int) : Int {
        var position : Int = currentPosition
        val idStep : String = mListForms!![currentPosition].idstep!!
        val hasCondition : Boolean = mListForms!![currentPosition].isconditional!!
        if (hasCondition) {
            for (i in 0 until mListConditions!!.size) {
                val auxCondition : FormConditions = mListConditions!![i]
                if (idStep.equals(auxCondition.idstep)) {
                    //tomamos la primera condición por defecto
                    val condition : ConditionsItem = auxCondition.conditions!!.get(0)!!


                }
            }

        } else {
            if (currentPosition < mAdapter.count - 1) {
                position++
                viewPager.currentItem = position
            }
        }

        return 0
    }
}
