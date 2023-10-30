package com.example.demofragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment1.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment1 : Fragment(R.layout.fragment_1) {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_1, container, false)
//        var editTxT: TextView  = view.findViewById<TextView>(R.id.editTxt)
        val btnSubmit: Button = view.findViewById(R.id.btnSubmit)
        btnSubmit.setOnClickListener {
            var editTxT: EditText = view.findViewById(R.id.editTxt)
            var inputName = editTxT.text.toString()

            val bundle = Bundle()
            bundle.putString("data", inputName)
            val fragment2 = Fragment2()
            fragment2.arguments = bundle
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainer, fragment2).commit()
            }


        }

        return view
    }
}