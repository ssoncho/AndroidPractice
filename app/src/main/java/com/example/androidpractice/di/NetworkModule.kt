package com.example.androidpractice.di

import android.content.Context
import android.util.Log
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.androidpractice.characterList.data.api.CharacterApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    factory { provideRetrofit(get()) }
    single { provideNetworkApi(get()) }
}

fun provideRetrofit(context: Context): Retrofit {
    val client = OkHttpClient.Builder().apply {
        addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        addInterceptor(ChuckerInterceptor(context))
    }.build()

    return Retrofit.Builder()
        .baseUrl("https://api.potterdb.com/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

}


fun provideNetworkApi(retrofit: Retrofit): CharacterApi =
    retrofit.create(CharacterApi::class.java)