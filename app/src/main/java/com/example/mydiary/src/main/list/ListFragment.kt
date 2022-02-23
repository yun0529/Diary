package com.example.mydiary.src.main.list

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mydiary.R
import com.example.mydiary.config.BaseFragment
import com.example.mydiary.databinding.FragmentListBinding
import com.example.mydiary.src.main.MainActivity
import com.google.android.material.tabs.TabLayout

data class Content(val title : String)

class ListFragment : BaseFragment<FragmentListBinding>(FragmentListBinding::bind, R.layout.fragment_list) {

    private lateinit var rvAdapter : ListAdapter
    private var contents = arrayListOf<Content>()
    private var activity : MainActivity? = MainActivity()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addData()

        rvAdapter = ListAdapter(contents,0)
        binding.listRvList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.listRvList.adapter = rvAdapter

        //binding.listRvList.addItemDecoration(DividerItemDecoration(view.context, 1))
        binding.listSbSelect.setOnSwitchListener { position, tabText ->
            if(tabText == "내용"){
                rvAdapter = ListAdapter(contents,0)
            }else{
                rvAdapter = ListAdapter(contents,1)
            }
            binding.listRvList.adapter = rvAdapter
            rvAdapter.notifyDataSetChanged()
        }
        binding.listBtnTodayWrite.setOnClickListener {
            activity?.fragmentChange(1)
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

    private fun addData(){
        for(i in 0 until 5) {
            contents.add(Content("테스트중~~~~~"))
        }
    }
}