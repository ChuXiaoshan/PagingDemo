package com.cxsplay.pd.netk

import android.text.TextUtils
import okhttp3.*
import okio.Buffer
import java.io.IOException
import java.util.*

/**
 * Created by CxS on 2018/11/27
 */
class BasicParamsInterceptor private constructor() : Interceptor {

    internal var headerParamsMap: MutableMap<String, String> = HashMap() // 公共 Headers 添加
    internal var queryParamsMap: MutableMap<String, String> = HashMap() // 添加到 URL 末尾，Get Post 方法都使用
    internal var headerLinesList: MutableList<String> = ArrayList() // 消息头 集合形式，一次添加一行
    internal var paramsMap: MutableMap<String, String> = HashMap() // 添加到公共参数到消息体，适用 Post 请求

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        var request: Request? = chain.request()
        val requestBuilder = request!!.newBuilder()

        // process header params inject
        val headerBuilder = request.headers().newBuilder()
        // 以 Entry 添加消息头
        if (headerParamsMap.isNotEmpty()) {
            val iterator = headerParamsMap.entries.iterator()
            while (iterator.hasNext()) {
                val entry = iterator.next()
                headerBuilder.add(entry.key, entry.value)
            }
        }
        // 以 String 形式添加消息头
        if (headerLinesList.size > 0) {
            for (line in headerLinesList) {
                headerBuilder.add(line)
            }
            requestBuilder.headers(headerBuilder.build())
        }
        // process header params end

        // process queryParams inject whatever it's GET or POST
        if (queryParamsMap.isNotEmpty()) {
            request = injectParamsIntoUrl(request.url().newBuilder(), requestBuilder, queryParamsMap)
        }

        // process post body inject
        if (paramsMap.isNotEmpty()) {
            if (canInjectIntoBody(request)) {
                val formBodyBuilder = FormBody.Builder()
                for ((key, value) in paramsMap) {
                    formBodyBuilder.add(key, value)
                }

                val formBody = formBodyBuilder.build()
                var postBodyString = bodyToString(request!!.body())
                postBodyString += (if (postBodyString.isNotEmpty()) "&" else "") + bodyToString(formBody)
                requestBuilder.post(RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;charset=UTF-8"), postBodyString))
            }
        }
        request = requestBuilder.build()
        return chain.proceed(request)
    }

    /**
     * 确认是否是 post 请求
     *
     * @param request 发出的请求
     * @return true 需要注入公共参数
     */
    private fun canInjectIntoBody(request: Request?): Boolean {
        if (request == null) {
            return false
        }
        if (!TextUtils.equals(request.method(), "POST")) {
            return false
        }
        val body = request.body() ?: return false
        val mediaType = body.contentType() ?: return false
        return TextUtils.equals(mediaType.subtype(), "x-www-form-urlencoded")
    }

    // func to inject params into url
    private fun injectParamsIntoUrl(httpUrlBuilder: HttpUrl.Builder, requestBuilder: Request.Builder, paramsMap: Map<String, String>): Request? {
        if (paramsMap.isNotEmpty()) {
            val iterator = paramsMap.entries.iterator()
            while (iterator.hasNext()) {
                val entry = iterator.next()
                httpUrlBuilder.addQueryParameter(entry.key, entry.value)
            }
            requestBuilder.url(httpUrlBuilder.build())
            return requestBuilder.build()
        }
        return null
    }

    private fun bodyToString(request: RequestBody?): String {
        try {
            val buffer = Buffer()
            if (request != null)
                request.writeTo(buffer)
            else
                return ""
            return buffer.readUtf8()
        } catch (e: IOException) {
            return "did not work"
        }

    }

    class Builder {

        private var interceptor: BasicParamsInterceptor = BasicParamsInterceptor()

        // 添加公共参数到 post 消息体
        fun addParam(key: String, value: String): Builder {
            interceptor.paramsMap[key] = value
            return this
        }

        // 添加公共参数到 post 消息体
        fun addParamsMap(paramsMap: Map<String, String>): Builder {
            interceptor.paramsMap.putAll(paramsMap)
            return this
        }

        // 添加公共参数到消息头
        fun addHeaderParam(key: String, value: String): Builder {
            interceptor.headerParamsMap[key] = value
            return this
        }

        // 添加公共参数到消息头
        fun addHeaderParamsMap(headerParamsMap: Map<String, String>): Builder {
            interceptor.headerParamsMap.putAll(headerParamsMap)
            return this
        }

        // 添加公共参数到消息头
        fun addHeaderLine(headerLine: String): Builder {
            val index = headerLine.indexOf(":")
            if (index == -1) {
                throw IllegalArgumentException("Unexpected header: $headerLine")
            }
            interceptor.headerLinesList.add(headerLine)
            return this
        }

        // 添加公共参数到消息头
        fun addHeaderLinesList(headerLinesList: List<String>): Builder {
            for (headerLine in headerLinesList) {
                val index = headerLine.indexOf(":")
                if (index == -1) {
                    throw IllegalArgumentException("Unexpected header: $headerLine")
                }
                interceptor.headerLinesList.add(headerLine)
            }
            return this
        }

        // 添加公共参数到 URL
        fun addQueryParam(key: String, value: String): Builder {
            interceptor.queryParamsMap[key] = value
            return this
        }

        // 添加公共参数到 URL
        fun addQueryParamsMap(queryParamsMap: Map<String, String>): Builder {
            interceptor.queryParamsMap.putAll(queryParamsMap)
            return this
        }

        fun build(): BasicParamsInterceptor {
            return interceptor
        }
    }
}