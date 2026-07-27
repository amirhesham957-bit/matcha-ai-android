package com.example.matchaai.data

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.util.concurrent.TimeUnit

data class AnalysisRequest(val ticker: String)
data class AnalysisResponse(
    val status: String,
    val ticker: String,
    val session_id: String?,
    val verdict: String?,
    val message: String?
)

object ApiClient {
    // 10.0.2.2 is the special alias to your host loopback interface (127.0.0.1) from Android Emulator
    private const val BASE_URL = "http://10.0.2.2:8000"
    
    private val client = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .build()
        
    private val gson = Gson()
    private val JSON = "application/json; charset=utf-8".toMediaType()

    suspend fun analyzeTicker(ticker: String): AnalysisResponse? {
        return withContext(Dispatchers.IO) {
            try {
                val requestBody = gson.toJson(AnalysisRequest(ticker)).toRequestBody(JSON)
                val request = Request.Builder()
                    .url("$BASE_URL/analyze")
                    .post(requestBody)
                    .build()

                client.newCall(request).execute().use { response ->
                    if (!response.isSuccessful) return@withContext null
                    
                    val responseBody = response.body?.string()
                    if (responseBody != null) {
                        return@withContext gson.fromJson(responseBody, AnalysisResponse::class.java)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return@withContext null
        }
    }
}
