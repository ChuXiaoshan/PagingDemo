package com.cxsplay.pd.bskj

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.toLiveData

/**
 * Created by chuxiaoshan on 2019/11/25 16:58.
 *
 */

class OrdersVM : ViewModel() {

    private val sourceFactory = OrdersDataSourceFactory()
    val data = sourceFactory.toLiveData(pageSize = 20)
    val loadStatus = Transformations.switchMap(sourceFactory.sourceLiveData) { it.initialLoad }

    fun refresh() {
        sourceFactory.sourceLiveData.value?.invalidate()
    }
}