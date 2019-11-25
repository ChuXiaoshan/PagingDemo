package com.cxsplay.pd.bskj

import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by CxS on 2017/9/22.
 * RxSchedulers
 */

object RxSchedulers {

    @JvmStatic
    fun <T> io_main(): ObservableTransformer<T, T> {
        return ObservableTransformer { observable ->
            observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }
}
