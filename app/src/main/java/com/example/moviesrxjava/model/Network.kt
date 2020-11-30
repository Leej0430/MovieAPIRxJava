package com.example.moviesrxjava.model

import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

//or abstract class Network
class Network private constructor(){
    
    val movieApi: MovieAPI
    init{
        movieApi= Retrofit.Builder()
                .client(createOKHttpLogger())
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(MovieAPI::class.java)
    }

    private fun createOKHttpLogger(): OkHttpClient{

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BASIC
        val client = OkHttpClient.Builder()
        .addInterceptor(logging)
                .build()

        return client

    }
    companion object{
        @Volatile
        private var INSTANCE : Network? = null

        //@Synchronized
        fun getInstance():Network{
            if(INSTANCE != null)
                return INSTANCE!!
            else{
                synchronized(this){
                    if(INSTANCE != null)
                        return INSTANCE!!
                    else{
                        INSTANCE = Network()
                    }
                    return INSTANCE!!
                }
            }
        }
    }
    
}