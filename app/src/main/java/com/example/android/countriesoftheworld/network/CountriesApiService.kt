package com.example.android.countriesoftheworld.network

import com.example.android.countriesoftheworld.model.CountryData
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://restcountries.eu/rest/v2/all/"

    object ServiceBuilder {

    private val client = OkHttpClient
        .Builder()
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create())
        .client(client)
        .build()
        .create(CountriesApiService::class.java)

    interface CountriesApiService {
        @GET(".")
        fun getCountries(): Observable<List<CountryData>>
    }

    fun buildService(): CountriesApiService {
        return retrofit
    }

}