package com.cxsplay.pd.bskj

import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList

/**
 * Created by chuxiaoshan on 2019/11/25 16:58.
 *
 */

class OrdersVM : ViewModel() {

    private val dataSourceFactory = OrdersDataSourceFactory()
    val loadStatus = dataSourceFactory.loadStatus

    var data = LivePagedListBuilder(
            dataSourceFactory,
            PagedList.Config
                .Builder()
                .setPageSize(20)
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(20)
                .build()
        ).build()

    fun refresh() {
        dataSourceFactory.refresh()
    }
}