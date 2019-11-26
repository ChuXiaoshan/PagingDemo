package com.cxsplay.pd.bskj

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.blankj.utilcode.util.LogUtils

/**
 * Created by chuxiaoshan on 2019/11/25 12:00.
 *
 */
class OrdersDataSourceFactory : DataSource.Factory<String, OrderBean>() {

    private val ds by lazy { OrdersDataSource() }
    val loadStatus by lazy { ds.loadStatus }

    private val sourceLiveData = MutableLiveData<OrdersDataSource>()

    fun refresh() {
        sourceLiveData.postValue(ds)
        sourceLiveData.value?.invalidate()
        LogUtils.d("---refresh")
    }

    override fun create(): DataSource<String, OrderBean> {
        sourceLiveData.postValue(ds)
        return ds
    }
}