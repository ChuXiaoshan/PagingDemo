package com.cxsplay.pd.bskj

import androidx.paging.PageKeyedDataSource
import com.blankj.utilcode.util.LogUtils
import com.cxsplay.pd.netk.RHelper
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * Created by chuxiaoshan on 2019/11/25 11:03.
 *
 */

class OrdersDataSource : PageKeyedDataSource<String, OrderBean>() {

    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, OrderBean>) {
        LogUtils.d("---loadInitial--->${Thread.currentThread().name}")

        val paramss = mutableMapOf("pagesize" to "20", "page" to "1")
        paramss["store_id"] = "42"
        paramss["end_time"] = "2019-11-25 11:36:42"
        paramss["start_time"] = "2019-10-25 11:36:42"
        RHelper.apiService!!.orderListNew("Bearer PUC08D3681EBDAADAAB8", paramss)
            .compose(RxSchedulers.io_main())
            .subscribe(object : Observer<DataResponse<BaseHeader<BasePage<OrderBean>>>> {
                override fun onComplete() {
                    LogUtils.d("---onComplete---")
                }

                override fun onSubscribe(d: Disposable) {
                    LogUtils.d("---onSubscribe---")
                }

                override fun onNext(t: DataResponse<BaseHeader<BasePage<OrderBean>>>) {
                    LogUtils.d("---any---$t")
                    callback.onResult(t.data?.list?.data!!, null, "2")
                }

                override fun onError(e: Throwable) {
                    LogUtils.d("---e${e.message}")
                }
            })
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, OrderBean>) {
        LogUtils.d("---loadAfter--->${Thread.currentThread().name}")

        val paramss = mutableMapOf("pagesize" to "20", "page" to params.key)
        paramss["store_id"] = "42"
        paramss["end_time"] = "2019-11-25 11:36:42"
        paramss["start_time"] = "2019-10-25 11:36:42"
        RHelper.apiService!!.orderListNew("Bearer PUC08D3681EBDAADAAB8", paramss)
            .compose(RxSchedulers.io_main())
            .subscribe(object : Observer<DataResponse<BaseHeader<BasePage<OrderBean>>>> {
                override fun onComplete() {
                    LogUtils.d("---onComplete---")
                }

                override fun onSubscribe(d: Disposable) {
                    LogUtils.d("---onSubscribe---")
                }

                override fun onNext(t: DataResponse<BaseHeader<BasePage<OrderBean>>>) {
                    LogUtils.d("---any---$t")
                    callback.onResult(t.data?.list?.data!!, "${params.key.toInt() + 1}")
                }

                override fun onError(e: Throwable) {
                    LogUtils.d("---e${e.message}")
                }
            })
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, OrderBean>) {
        LogUtils.d("---loadBefore--->${Thread.currentThread().name}")
    }


    fun getList() {
        val params = mutableMapOf("pagesize" to "20", "page" to "1")
        params["store_id"] = "42"
        params["end_time"] = "2019-11-25 11:36:42"
        params["end_time"] = "2019-10-25 11:36:42"

        RHelper.apiService!!.orderListNew("", params)
            .compose(RxSchedulers.io_main())
            .subscribe(object : Observer<Any> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: Any) {
                    LogUtils.d("---any---$t")
                }

                override fun onError(e: Throwable) {
                    LogUtils.d("---e${e.message}")
                }
            })
    }
}