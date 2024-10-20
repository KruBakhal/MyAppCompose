package com.example.myappcompose.di_module

import android.content.Context
import androidx.room.Room
import com.example.myappcompose.shopapp.di.network.APINetworkInterface
import com.example.myappcompose.shopapp.utils.Constants
import com.example.myappcompose.superball.database.SuperBallAppDatabase
import com.example.practicesession.superballgame.database.SuperBallDao
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
class NetworkModule {

    @Provides
    @Singleton
    @Named("Shop")
    fun provideRetrofit(): Retrofit {
        val okHttpClientForMoreAppsView: OkHttpClient = OkHttpClient.Builder()
            .callTimeout(8, TimeUnit.SECONDS)
            .connectTimeout(8, TimeUnit.SECONDS)
            .readTimeout(50, TimeUnit.SECONDS)
            .writeTimeout(50, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(provideOkHttpClient(provideLoggingInterceptor()))
            .client(okHttpClientForMoreAppsView)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()

    }

    @Provides
    @Singleton
    fun provideRetrofitService(@Named("Shop") retrofit: Retrofit): APINetworkInterface {
        return retrofit.create(APINetworkInterface::class.java)
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
    @Provides
    @Singleton
    fun getRoomDatabaseSuper(@ApplicationContext context: Context): SuperBallAppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            SuperBallAppDatabase::class.java,
            "super_db_compose.db").allowMainThreadQueries().fallbackToDestructiveMigration().build()
    }


    @Provides
    @Singleton
    fun provideChannelDaoSuper( appDatabase: SuperBallAppDatabase): SuperBallDao {
        return appDatabase.getArticleDBDao()
    }
}