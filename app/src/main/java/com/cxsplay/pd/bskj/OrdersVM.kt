package com.cxsplay.pd.bskj

import androidx.lifecycle.ViewModel

/**
 * Created by chuxiaoshan on 2019/11/25 16:58.
 *
 */

class OrdersVM : ViewModel() {
    private val repository = OrderDataSourceRepository()
    private val listing = repository.getOrderList()
    val data = listing.pagedList
    val loadStatus = listing.refreshState

    fun refresh() {
        listing.refresh.invoke()
    }
}