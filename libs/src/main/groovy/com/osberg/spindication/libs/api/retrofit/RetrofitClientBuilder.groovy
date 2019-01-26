package com.osberg.spindication.libs.api.retrofit

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory

class RetrofitClientBuilder {

    OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
    String url
    
    Retrofit build() {
        return new Retrofit.Builder()
            .baseUrl(url)
            .client(clientBuilder.build())
                .addConverterFactory(JacksonConverterFactory.create(new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    RetrofitClientBuilder addInterceptor(Interceptor interceptor) {
        clientBuilder.addInterceptor(interceptor)
        return this
    }


    RetrofitClientBuilder baseUrl(String baseUrl) {
        this.url = baseUrl
        return this
    }

}
