package com.cxsplay.pd

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
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
    }
}
