package com.semicolon.data.di

import android.util.Log
import com.semicolon.data.remote.ApiClient
import com.semicolon.data.remote.ApiService
import com.semicolon.data.remote.RemoteSource
import com.semicolon.data.remote.TwitterAuthInterceptor
import com.semicolon.data.repository.TwitterRepository
import com.semicolon.domain.repository.TwitterRepositoryInterface
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

//@Module
//@InstallIn(SingletonComponent::class)
//abstract class DataModule {
//    companion object {
//
//        @Provides
//        @Singleton
//        fun providesLoggingInterceptor(): HttpLoggingInterceptor {
//            val logging = HttpLoggingInterceptor { message ->
//                Log.d("OkHttp", message)
//            }
//            logging.setLevel(HttpLoggingInterceptor.Level.BODY) // Log request and response body
//            return logging
//        }
//
//        @Provides
//        @Singleton
//        fun providesTwitterAuthInterceptor(): Interceptor {
//            return TwitterAuthInterceptor(
//                apiKey = "eeEeATE8Rj0BvfJTFopJAqiJe",
//                apiSecretKey = "FmBY5z13CMdhZyJ4yHwnSY3SugtIZbPnd8JOvw9aky9IxjFw6u",
//                accessToken = "1863974668556001280-mIMfTYYhMJy5pU3TiJjPkQNTwZSyvH",
//                accessTokenSecret = "qqadJHOPovVOrrtJszObjHCALSkumNKw050cUIxQJPMcK"
//            )
//        }
//
//        @Provides
//        @Singleton
//        fun providesOkHttpClient(
//            loggingInterceptor: HttpLoggingInterceptor,
//            twitterAuthInterceptor: Interceptor
//        ): OkHttpClient {
//            return OkHttpClient.Builder()
//                .readTimeout(120, TimeUnit.SECONDS) // Set read timeout
//                .connectTimeout(120, TimeUnit.SECONDS) // Set connection timeout
//                .addInterceptor(loggingInterceptor) // Add logging interceptor
//                .addInterceptor(twitterAuthInterceptor) // Add Twitter auth interceptor
//                .build()
//        }
//
//        @Provides
//        @Singleton
//        fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
//            return Retrofit.Builder()
//                .baseUrl("https://api.twitter.com/") // Base URL for Twitter API
//                .client(okHttpClient)
//                .addConverterFactory(GsonConverterFactory.create()) // Gson for JSON parsing
//                .build()
//        }
//
//        @Provides
//        @Singleton
//        fun provideApi(retrofit: Retrofit): ApiService {
//            return retrofit.create(ApiService::class.java)
//        }
//    }
//    @Binds
//    @Singleton
//    abstract fun bindRemoteSource(
//        apiClient: ApiClient
//    ): RemoteSource
//
//    @Binds
//    @Singleton
//    abstract fun bindTwitterRepository(
//        twitterRepository: TwitterRepository
//    ): TwitterRepositoryInterface
//}

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    companion object {

        @Provides
        @Singleton
        fun providesLoggingInterceptor(): HttpLoggingInterceptor {
            val logging = HttpLoggingInterceptor { message ->
                Log.d("OkHttp", message)
            }
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            return logging
        }

        @Provides
        @Singleton
        fun providesTwitterAuthInterceptor(): Interceptor {
            return TwitterAuthInterceptor(
                apiKey = "9sXGtmL2gmDZI8tmc7dRI8FkL",
                apiSecretKey = "xHX6IPdwTSGaI2w2V35QHxteKnxA4x6Ev4rPiWl3qVPB2ZGgU9",
                accessToken = "1863974668556001280-zISw33h7V0r3dtcncShtMHFbdHTAR9",
                accessTokenSecret = "cA8T1xaFi6KblZZ53dJWpnHGTBdjnUbhIYh30JSv3nLUy"
            )
        }

        @Provides
        @Singleton
        fun providesOkHttpClient(
            loggingInterceptor: HttpLoggingInterceptor,
            twitterAuthInterceptor: Interceptor
        ): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(twitterAuthInterceptor)
                .build()
        }


        @Provides
        @Singleton
        fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://api.twitter.com/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        @Provides
        @Singleton
        fun provideApi(retrofit: Retrofit): ApiService {
            return retrofit.create(ApiService::class.java)
        }
    }

    @Binds
    @Singleton
    abstract fun bindRemoteSource(apiClient: ApiClient): RemoteSource

    @Binds
    @Singleton
    abstract fun bindTwitterRepository(twitterRepository: TwitterRepository): TwitterRepositoryInterface
}
