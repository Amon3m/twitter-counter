package com.semicolon.data.remote


import android.util.Base64
import okhttp3.Interceptor
import okhttp3.Response
import java.net.URLEncoder
import java.util.UUID
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

class TwitterAuthInterceptor(
    private val apiKey: String,
    private val apiSecretKey: String,
    private val accessToken: String,
    private val accessTokenSecret: String
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val url = originalRequest.url.toString()

        val method = originalRequest.method
        val oauthParams = generateOAuthParams(url, method)

        val oauthHeader = buildOAuthHeader(oauthParams)

        val authenticatedRequest = originalRequest.newBuilder()
            .addHeader("Authorization", oauthHeader)
            .build()

        return chain.proceed(authenticatedRequest)
    }

    private fun generateOAuthParams(url: String, method: String): Map<String, String> {
        val timestamp = (System.currentTimeMillis() / 1000).toString()
        val nonce = UUID.randomUUID().toString()

        val params = mutableMapOf(
            "oauth_consumer_key" to apiKey,
            "oauth_token" to accessToken,
            "oauth_signature_method" to "HMAC-SHA1",
            "oauth_timestamp" to timestamp,
            "oauth_nonce" to nonce,
            "oauth_version" to "1.0"
        )

        val signature = generateSignature(url, method, params)
        params["oauth_signature"] = signature

        return params
    }

    private fun generateSignature(
        url: String,
        method: String,
        params: Map<String, String>
    ): String {
        val encodedParams = params.entries.sortedBy { it.key }
            .joinToString("&") { "${it.key.urlEncode()}=${it.value.urlEncode()}" }

        val baseString = "$method&${url.urlEncode()}&${encodedParams.urlEncode()}"

        val signingKey = "${apiSecretKey.urlEncode()}&${accessTokenSecret.urlEncode()}"

        return signWithHmacSHA1(baseString, signingKey)
    }

    private fun signWithHmacSHA1(data: String, key: String): String {
        val signingKey = SecretKeySpec(key.toByteArray(), "HmacSHA1")
        val mac = Mac.getInstance("HmacSHA1").apply { init(signingKey) }
        return Base64.encodeToString(mac.doFinal(data.toByteArray()), Base64.NO_WRAP)
    }

    private fun buildOAuthHeader(params: Map<String, String>): String {
        return "OAuth " + params.entries.joinToString(", ") {
            "${it.key}=\"${it.value.urlEncode()}\""
        }
    }

    private fun String.urlEncode(): String =
        URLEncoder.encode(this, "UTF-8").replace("+", "%20")
}
