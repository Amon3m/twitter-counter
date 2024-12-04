package com.semicolon.data.di

import android.util.Log
import com.semicolon.data.BuildConfig
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
import javax.inject.Singleton


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
                apiKey = BuildConfig.API_KEY,
                apiSecretKey = BuildConfig.API_SECRET_KEY,
                accessToken = BuildConfig.ACCESS_TOKEN,
                accessTokenSecret = BuildConfig.ACCESS_TOKEN_SECRET
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
