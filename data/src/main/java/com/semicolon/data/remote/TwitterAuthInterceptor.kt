package com.semicolon.data.remote



import com.github.scribejava.apis.TwitterApi
import com.github.scribejava.core.builder.ServiceBuilder
import com.github.scribejava.core.model.OAuth1AccessToken
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject


class TwitterAuthInterceptor @Inject constructor(
    private val apiKey: String,
    private val apiSecretKey: String,
    private val accessToken: String,
    private val accessTokenSecret: String
) : Interceptor {

    private val oauthService = ServiceBuilder(apiKey)
        .apiSecret(apiSecretKey)
        .build(TwitterApi.instance())

    private val oauthToken = OAuth1AccessToken(accessToken, accessTokenSecret)

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val url = originalRequest.url.toString()

        val oauthRequest = com.github.scribejava.core.model.OAuthRequest(
            com.github.scribejava.core.model.Verb.POST, url
        )

        originalRequest.body?.let { body ->
            val buffer = okio.Buffer()
            body.writeTo(buffer)
            val bodyContent = buffer.readUtf8()

            oauthRequest.addParameter("text", bodyContent)
        }

        oauthService.signRequest(oauthToken, oauthRequest)

        val signedRequest = originalRequest.newBuilder()
            .header("Authorization", oauthRequest.headers["Authorization"]!!)
            .header("Content-Type", "application/json")
            .build()

        return chain.proceed(signedRequest)
    }
}
