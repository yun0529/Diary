package com.example.mydiary.src.main.write

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.mydiary.R
import com.example.mydiary.config.BaseFragment
import com.example.mydiary.databinding.FragmentWriteBinding
import com.example.mydiary.src.main.MainActivity
import com.google.android.material.slider.RangeSlider




class WriteFragment : BaseFragment<FragmentWriteBinding>(FragmentWriteBinding::bind, R.layout.fragment_write) {
    private var activity : MainActivity? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.writeBtnSave.setOnClickListener {
            activity?.fragmentChange(0)
        }
        binding.writeBtnDelete.setOnClickListener {
            activity?.fragmentChange(0)
        }
        binding.writeBtnClose.setOnClickListener {
            activity?.fragmentChange(0)
        }
        binding.writeSbFeel.setOnSlideListener {   index ->

            Toast.makeText(context,index.toString(), Toast.LENGTH_SHORT).show()
        }

    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = getActivity() as MainActivity
    }

    override fun onDetach() {
        super.onDetach()
        activity = null
    }

}