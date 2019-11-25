package com.cxsplay.pd.bskj

/**
 * Created by chuxiaoshan on 2019/11/25 15:42.
 *
 */
data class DataResponse<T>(var status: Int? = null, var msg: String? = null, var data: T? = null)

data class BaseHeader<T>(var list: T? = null)

data class BasePage<T>(var data: List<T>? = null)
