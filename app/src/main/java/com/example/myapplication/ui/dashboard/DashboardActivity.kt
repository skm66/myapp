package com.example.myapplication.ui.dashboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {

    private val binding : ActivityDashboardBinding by lazy {
        ActivityDashboardBinding.inflate(layoutInflater)
    }

    lateinit var arrList : ArrayList<DashboardItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        arrList = ArrayList()

        addData()
        manageRecyclerView()
    }

    private fun addData() {
        arrList.add(DashboardItem(R.drawable.ic_launcher_background,100))
        arrList.add(DashboardItem(R.drawable.ic_launcher_background,200))
        arrList.add(DashboardItem(R.drawable.ic_launcher_background,300))
        arrList.add(DashboardItem(R.drawable.ic_launcher_background,400))
        arrList.add(DashboardItem(R.drawable.ic_launcher_background,500))
        arrList.add(DashboardItem(R.drawable.ic_launcher_background,500))
        arrList.add(DashboardItem(R.drawable.ic_launcher_background,600))
        arrList.add(DashboardItem(R.drawable.ic_launcher_background,700))
        arrList.add(DashboardItem(R.drawable.ic_launcher_background,700))
        arrList.add(DashboardItem(R.drawable.ic_launcher_background,700))

    }

    private fun manageRecyclerView() {
        val layoutManager = GridLayoutManager(this,2)
        binding.rvDashboard.layoutManager = layoutManager
        binding.rvDashboard.adapter = DashboardAdapter(this,arrList)
    }
}