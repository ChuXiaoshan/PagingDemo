package com.cxsplay.pd.bskj

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

/**
 * Created by chuxiaoshan on 2019/11/26 16:44.
 *
 */
data class Listing<T>(
    val pagedList: LiveData<PagedList<T>>,
    val networkState: LiveData<LoadStatus>,
    val refreshState: LiveData<LoadStatus>,
    val refresh: () -> Unit
)