package com.cxsplay.pd.bskj

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource

/**
 * Created by chuxiaoshan on 2019/11/25 12:00.
 *
 */
class OrdersDataSourceFactory : DataSource.Factory<String, OrderBean>() {
    val sourceLiveData = MutableLiveData<OrdersDataSource>()
    override fun create(): DataSource<String, OrderBean> {
        val source = OrdersDataSource()
        sourceLiveData.postValue(source)
        return source
    }
}