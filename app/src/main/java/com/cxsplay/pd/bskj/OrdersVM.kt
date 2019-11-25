package com.cxsplay.pd.bskj

import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList

/**
 * Created by chuxiaoshan on 2019/11/25 16:58.
 *
 */

class OrdersVM : ViewModel() {

    val data = LivePagedListBuilder(
        OrdersDataSourceFactory(), PagedList.Config.Builder()
            .setPageSize(20)
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(20)
            .build()
    ).build()
}