package com.example.mydiary.src.main.list

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mydiary.R
import com.example.mydiary.config.BaseFragment
import com.example.mydiary.databinding.FragmentListBinding

data class Content(val title : String)

class ListFragment : BaseFragment<FragmentListBinding>(FragmentListBinding::bind, R.layout.fragment_list) {

    private lateinit var rvAdapter : ListAdapter
    private var contents = arrayListOf<Content>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addData()
        rvAdapter = ListAdapter(contents)
        binding.listRvList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.listRvList.adapter = rvAdapter
        binding.listRvList.addItemDecoration(DividerItemDecoration(view.context, 1))

    }

    private fun addData(){
        for(i in 0 until 5) {
            contents.add(Content("테스트중~~~~~"))
        }
    }
}