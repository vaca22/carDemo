package com.example.cardemo.utils

import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

object NetUtils {
    private val client = OkHttpClient();
    private val JSON: MediaType? = "application/json; charset=utf-8".toMediaTypeOrNull()
    private val JSON2: MediaType? = "multipart/form-data; charset=utf-8".toMediaTypeOrNull()


    //create a function to post a file , its content is raw data
    fun postFile(url: String, file: File): String {
        val requestBody: RequestBody = file.asRequestBody("application/octet-stream".toMediaTypeOrNull())
        val request: Request = Request.Builder()
            .url(url)
            .post(requestBody)
            .build()
        val response = client.newCall(request).execute()
        return response.body!!.string()
    }
}