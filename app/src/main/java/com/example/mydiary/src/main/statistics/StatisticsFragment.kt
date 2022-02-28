package com.example.mydiary.src.main.statistics

import android.graphics.Color
import android.os.Bundle
import android.view.View
import com.example.mydiary.R
import com.example.mydiary.config.BaseFragment
import com.example.mydiary.databinding.FragmentStatisticsBinding
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData

import com.github.mikephil.charting.utils.ColorTemplate

import com.github.mikephil.charting.utils.MPPointF

import com.github.mikephil.charting.data.PieDataSet

import com.github.mikephil.charting.data.PieEntry




class StatisticsFragment : BaseFragment<FragmentStatisticsBinding>(FragmentStatisticsBinding::bind, R.layout.fragment_statistics) {
//    private var pChart : PieChart? = null
//    private var bChart : BarChart? = null
//    private var lChart : LineChart? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.statisticsPcFeelPercent.setUsePercentValues(true)
        binding.statisticsPcFeelPercent.description.isEnabled = false
        binding.statisticsPcFeelPercent.centerText = "기분별 비율"
        binding.statisticsPcFeelPercent.setCenterTextSize(16f)
        binding.statisticsPcFeelPercent.setTransparentCircleColor(Color.WHITE)
        binding.statisticsPcFeelPercent.setTransparentCircleAlpha(110)
        binding.statisticsPcFeelPercent.holeRadius = 58f
        binding.statisticsPcFeelPercent.transparentCircleRadius = 61f

        var pLegend : Legend? = binding.statisticsPcFeelPercent.legend
        pLegend?.isEnabled = false

        binding.statisticsPcFeelPercent.setEntryLabelColor(Color.WHITE)
        binding.statisticsPcFeelPercent.setEntryLabelTextSize(12f)

        setData1()

    }

    private fun setData1() {
        val entries: ArrayList<PieEntry> = ArrayList()
        entries.add(PieEntry(20f, "", resources.getDrawable(R.drawable.small_icon_feel_01)))
        entries.add(PieEntry(20f, "", resources.getDrawable(R.drawable.small_icon_feel_02)))
        entries.add(PieEntry(20f, "", resources.getDrawable(R.drawable.small_icon_feel_03)))
        entries.add(PieEntry(20f, "", resources.getDrawable(R.drawable.small_icon_feel_04)))
        entries.add(PieEntry(10f, "", resources.getDrawable(R.drawable.small_icon_feel_05)))
        entries.add(PieEntry(10f, "", resources.getDrawable(R.drawable.small_icon_feel_06)))

        val dataSet = PieDataSet(entries, "기분별 비율")
        dataSet.setDrawIcons(true)
        dataSet.sliceSpace = 3f
        dataSet.iconsOffset = MPPointF(0f, -40f)
        dataSet.selectionShift = 5f
        val colors: ArrayList<Int> = ArrayList()
        for (c in 0 until entries.size) {
            if(c == 5){
                colors.add(Color.BLACK)
            }else{
                colors.add(ColorTemplate.JOYFUL_COLORS[c])
            }

        }
        dataSet.colors = colors
        val data = PieData(dataSet)
        data.setValueTextSize(22.0f)
        data.setValueTextColor(Color.WHITE)
        binding.statisticsPcFeelPercent.data = data
        binding.statisticsPcFeelPercent.invalidate()
    }
}