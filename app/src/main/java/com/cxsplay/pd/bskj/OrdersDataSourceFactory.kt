package com.cxsplay.pd.bskj

import androidx.paging.DataSource

/**
 * Created by chuxiaoshan on 2019/11/25 12:00.
 *
 */
class OrdersDataSourceFactory : DataSource.Factory<String, OrderBean>() {
    override fun create(): DataSource<String, OrderBean> {
        return OrdersDataSource()
    }
}