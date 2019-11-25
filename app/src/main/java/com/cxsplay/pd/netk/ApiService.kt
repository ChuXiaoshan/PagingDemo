package com.cxsplay.pd.netk

import com.cxsplay.pd.bskj.BaseHeader
import com.cxsplay.pd.bskj.BasePage
import com.cxsplay.pd.bskj.DataResponse
import com.cxsplay.pd.bskj.OrderBean
import io.reactivex.Observable
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

/**
 * Created by CxS on 2019/5/21
 */
interface ApiService {

    //新订单记录
    @FormUrlEncoded
    @POST("/api/order/list_new")
    fun orderListNew(
        @Header("Authorization") token: String?
        , @FieldMap params: MutableMap<String, String>
    ): Observable<DataResponse<BaseHeader<BasePage<OrderBean>>>>
}