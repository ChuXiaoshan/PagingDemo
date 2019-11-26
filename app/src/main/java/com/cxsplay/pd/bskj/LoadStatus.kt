package com.cxsplay.pd.bskj

/**
 * Created by chuxiaoshan on 2019/11/26 10:51.
 *
 */
enum class Status {
    RUNNING,
    SUCCESS,
    FAILED
}

@Suppress("DataClassPrivateConstructor")
data class LoadStatus private constructor(val status: Status, val msg: String? = null) {
    companion object {
        val LOADING = LoadStatus(Status.RUNNING)
        val LOADED = LoadStatus(Status.SUCCESS)
        fun error(msg: String?) = LoadStatus(Status.FAILED, msg)
    }
}