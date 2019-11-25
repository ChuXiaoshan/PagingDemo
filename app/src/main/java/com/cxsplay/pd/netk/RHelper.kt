package com.cxsplay.pd.netk

/**
 * Created by CxS on 2019/5/21
 */
object RHelper {

    var apiService: ApiService? = null
        private set

    init {
        val retrofit = RUtil.getRetrofitBuilder("http://sell.dev.vyicoo.com").build()
        apiService = retrofit.create(ApiService::class.java)
    }
}
