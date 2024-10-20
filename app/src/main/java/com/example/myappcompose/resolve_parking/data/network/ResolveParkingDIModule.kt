package com.example.myappcompose.resolve_parking.data.network

import com.example.myappcompose.resolve_parking.data.api.ResolveParkingApiService
import com.example.myappcompose.resolve_parking.utils.ResolveParkingConstants
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ResolveParkingDIModule {

    @Provides
    @Singleton
    @Named("Resolve")
    fun provideRetrofitResolve(): Retrofit {
        val okHttpClientForMoreAppsView: OkHttpClient = OkHttpClient.Builder()
            .callTimeout(8, TimeUnit.SECONDS)
            .connectTimeout(8, TimeUnit.SECONDS)
            .readTimeout(50, TimeUnit.SECONDS)
            .writeTimeout(50, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl(ResolveParkingConstants.BASE_URL)
            .client(provideOkHttpClient(provideLoggingInterceptor()))
            .client(okHttpClientForMoreAppsView)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()

    }

    @Provides
    @Singleton
    fun provideRetrofitServiceResolve( @Named("Resolve") retrofit: Retrofit): ResolveParkingApiService {
        return retrofit.create(ResolveParkingApiService::class.java)
    }


    private fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        val b = OkHttpClient.Builder()
        b.addInterceptor(interceptor)
        return b.build()
    }

    private fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

}