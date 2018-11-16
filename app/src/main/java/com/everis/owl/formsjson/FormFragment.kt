package com.everis.owl.formsjson

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.everis.owl.formsjson.dataModel.FormData


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [FormFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [FormFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class FormFragment : Fragment() {
    // TODO: Rename and change types of parameters
    @LayoutRes
    //private var resLayout: Int? = null
    private var formData: FormData? = null
    private var listener: OnFragmentInteractionListener? = null
    private var mainView : IMainView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            //resLayout = it.getInt(ARG_PARAM1)
            formData = it.getParcelable(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var vw : View



        when (formData!!.kindscreen) {
            "0" -> {
                vw = inflater.inflate(R.layout.basic_question_text_layout, container, false)
                val tx : TextView
                tx = vw.findViewById(R.id.textView) as TextView
                tx.text = formData!!.text
            }
            "1" -> {
                vw = inflater.inflate(R.layout.basic_question_button_layout, container, false)
                val btn : Button
                btn = vw.findViewById(R.id.button) as Button
                btn.text = formData!!.text
                btn.setOnClickListener {
                    //onButtonPressed()
                    this.mainView?.let { mainVListener ->
                        mainVListener.showToast("Mensaje desde el paso: " + formData!!.question)
                    }


                }

            }
            else -> {
                vw = inflater.inflate(R.layout.basic_question_button_layout, container, false)
                val btn : Button
                btn = vw.findViewById(R.id.button) as Button
                btn.text = formData!!.text

            }
        }
        return vw
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed() {
        listener?.onFragmentInteraction()
    }

    fun setMainViewListener(view : IMainView) {
        mainView = view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FormFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(/*param1: Int,*/ param2: FormData) =
            FormFragment().apply {
                arguments = Bundle().apply {
                    //putInt(ARG_PARAM1, param1)
                    putParcelable(ARG_PARAM2, param2)
                }
            }
    }
}
