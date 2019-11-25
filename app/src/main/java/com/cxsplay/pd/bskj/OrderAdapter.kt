package com.cxsplay.pd.bskj

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.cxsplay.pd.R

/**
 * Created by chuxiaoshan on 2019/11/25 14:39.
 *
 */

class OrderAdapter : PagedListAdapter<OrderBean, OrderViewHolder>(ORDER_COMPARATOR) {

    companion object {
        val ORDER_COMPARATOR = object : DiffUtil.ItemCallback<OrderBean>() {

            override fun areItemsTheSame(oldItem: OrderBean, newItem: OrderBean): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: OrderBean, newItem: OrderBean): Boolean = oldItem.pay_method == newItem.pay_method
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder = OrderViewHolder(parent)

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }
}

class OrderViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_order, parent, false)) {

    private val tvId = itemView.findViewById<TextView>(R.id.tv_id)
    private val tvType = itemView.findViewById<TextView>(R.id.tv_type)
    private var bean: OrderBean? = null

    fun bindTo(bean: OrderBean?) {
        this.bean = bean
        this.bean?.run {
            tvId.text = id
            tvType.text = pay_method
        }
    }
}
