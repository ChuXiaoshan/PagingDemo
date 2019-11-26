package com.cxsplay.pd

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.LogUtils
import com.cxsplay.pd.bskj.LoadStatus
import com.cxsplay.pd.bskj.OrderAdapter
import com.cxsplay.pd.bskj.OrdersVM
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val model by lazy { ViewModelProviders.of(this).get(OrdersVM::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        val adapter = OrderAdapter()
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter
        model.data.observe(this, Observer { adapter.submitList(it) })
        LogUtils.d("---loadStatus--->${model.loadStatus}")
        model.loadStatus.observe(this, Observer {
            srl.isRefreshing = it == LoadStatus.LOADING
            LogUtils.d("---model.loadStatus")
        })
        srl.setOnRefreshListener {
            model.refresh()
            LogUtils.d("---onRefreshListener")
        }
    }
}
