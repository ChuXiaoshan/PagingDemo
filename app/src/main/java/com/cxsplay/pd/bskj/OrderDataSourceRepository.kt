package com.cxsplay.pd.bskj

import androidx.lifecycle.Transformations
import androidx.paging.toLiveData

/**
 * Created by chuxiaoshan on 2019/11/26 16:44.
 *
 */

class OrderDataSourceRepository {

    fun getOrderList(): Listing<OrderBean> {
        val sourceFactory = OrdersDataSourceFactory()
        val livePageList = sourceFactory.toLiveData(pageSize = 20)
        return Listing(
            pagedList = livePageList,
//            networkState = Transformations.switchMap(sourceFactory.sourceLiveData) { it.networkStatus },
            refreshState = Transformations.switchMap(sourceFactory.sourceLiveData) { it.initialLoad },
            refresh = { sourceFactory.sourceLiveData.value?.invalidate() })
    }
}