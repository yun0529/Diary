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

import com.github.mikephil.charting.utils.ColorTemplate

import com.github.mikephil.charting.utils.MPPointF

import android.graphics.drawable.Drawable
import com.github.mikephil.charting.components.YAxis

import com.github.mikephil.charting.formatter.ValueFormatter

import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList

import java.lang.Exception


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

        var pLegend = binding.statisticsPcFeelPercent.legend
        pLegend.isEnabled = false

        binding.statisticsPcFeelPercent.setEntryLabelColor(Color.WHITE)
        binding.statisticsPcFeelPercent.setEntryLabelTextSize(12f)

        setData1()

        binding.statisticsBcDayFeel.setDrawValueAboveBar(true)
        binding.statisticsBcDayFeel.description.isEnabled = false
        binding.statisticsBcDayFeel.setDrawGridBackground(false)

        var xAxis = binding.statisticsBcDayFeel.xAxis
        xAxis.isEnabled = false

        var leftAxis = binding.statisticsBcDayFeel.axisLeft
        leftAxis.setLabelCount(6, false)
        leftAxis.axisMinimum = 0.0f
        leftAxis.isGranularityEnabled = true
        leftAxis.granularity = 1f

        var rightAxis = binding.statisticsBcDayFeel.axisRight
        rightAxis.isEnabled = false

        var bLegend = binding.statisticsBcDayFeel.legend
        bLegend.isEnabled = false

        binding.statisticsBcDayFeel.animateXY(1500,1500)

        setData2()

        binding.statisticsLcFeelChange.description.isEnabled = false
        binding.statisticsLcFeelChange.setDrawGridBackground(false)
        binding.statisticsLcFeelChange.setBackgroundColor(Color.WHITE)
        binding.statisticsLcFeelChange.setViewPortOffsets(0f, 0f, 0f, 0f)

        val legend3: Legend = binding.statisticsLcFeelChange.legend
        legend3.isEnabled = false

        val xAxis3: XAxis = binding.statisticsLcFeelChange.xAxis
        xAxis3.position = XAxis.XAxisPosition.BOTTOM_INSIDE
        xAxis3.textSize = 10f
        xAxis3.textColor = Color.WHITE
        xAxis3.setDrawAxisLine(false)
        xAxis3.setDrawGridLines(true)
        xAxis3.textColor = Color.rgb(255, 192, 56)
        xAxis3.setCenterAxisLabels(true)
        xAxis3.granularity = 1f
        xAxis3.valueFormatter = object : ValueFormatter() {
            private val mFormat: SimpleDateFormat = SimpleDateFormat("MM-dd", Locale.KOREA)
            override fun getFormattedValue(value: Float): String {
                val date = Date()
                val millis: Long = date.time + TimeUnit.HOURS.toMillis(value.toLong())
                return mFormat.format(Date(millis))
            }
        }

        val leftAxis3: YAxis = binding.statisticsLcFeelChange.axisLeft
        leftAxis3.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART)
        leftAxis3.textColor = ColorTemplate.getHoloBlue()
        leftAxis3.setDrawGridLines(true)
        leftAxis3.isGranularityEnabled = true
        leftAxis3.axisMinimum = 0f
        leftAxis3.axisMaximum = 5f
        leftAxis3.yOffset = -5f
        leftAxis3.textColor = Color.rgb(255, 192, 56)

        val rightAxis3: YAxis = binding.statisticsLcFeelChange.axisRight
        rightAxis3.isEnabled = false

        setData3()
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
    private fun setData2() {
        val entries: ArrayList<BarEntry> = ArrayList()
        entries.add(BarEntry(1f,30f, R.drawable.small_icon_feel_01))
        entries.add(BarEntry(2f,40f, R.drawable.small_icon_feel_02))
        entries.add(BarEntry(3f,20f, R.drawable.small_icon_feel_03))
        entries.add(BarEntry(4f,34f, R.drawable.small_icon_feel_04))
        entries.add(BarEntry(5f,26f, R.drawable.small_icon_feel_05))
        entries.add(BarEntry(6f,30f, R.drawable.small_icon_feel_06))

        val dataSet2 = BarDataSet(entries, "요일별 기분")
        dataSet2.color = Color.rgb(240, 120, 124)
        val colors: ArrayList<Int> = ArrayList()
        for (c in 0 until entries.size) {
            if(c == 5){
                colors.add(Color.BLACK)
            }else{
                colors.add(ColorTemplate.JOYFUL_COLORS[c])
            }

        }
        dataSet2.setDrawIcons(true)
        dataSet2.colors = colors
        dataSet2.iconsOffset = MPPointF(0f, -20f)
        val data = BarData(dataSet2)
        data.setValueTextSize(10f)
        data.setDrawValues(false)
        data.barWidth = 0.5f
        binding.statisticsBcDayFeel.data = data
        binding.statisticsBcDayFeel.invalidate()
    }

    private fun setData3() {
        val entries: ArrayList<Entry> = ArrayList()
        entries.add(Entry(1.toFloat(),1f))
        entries.add(Entry(2.toFloat(),3f))
        entries.add(Entry(3.toFloat(),1f))
        entries.add(Entry(4.toFloat(),4f))
        entries.add(Entry(5.toFloat(),0f))
        entries.add(Entry(6.toFloat(),2f))
        val set1 = LineDataSet(entries, "기분 변화")
        set1.axisDependency = YAxis.AxisDependency.LEFT
        set1.color = ColorTemplate.getHoloBlue()
        set1.valueTextColor = ColorTemplate.getHoloBlue()
        set1.lineWidth = 1.5f
        set1.setDrawCircles(true)
        set1.setDrawValues(false)
        set1.fillAlpha = 65
        set1.fillColor = ColorTemplate.getHoloBlue()
        set1.highLightColor = Color.rgb(244, 117, 117)
        set1.setDrawCircleHole(false)
        val data = LineData(set1)
        data.setValueTextColor(Color.WHITE)
        data.setValueTextSize(8f)
        binding.statisticsLcFeelChange.data = data
        binding.statisticsLcFeelChange.invalidate()
    }
}