package com.khalid.codecracker.dagger

import android.content.Context
import com.khalid.codecracker.services.EndpointProvider
import com.khalid.codecracker.services.JavaCrackerServices
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

@Module
class JavaCrackerModule {

    @Provides
    fun providesJavaCrackerServides(context: Context, endpointProvider: EndpointProvider, client: OkHttpClient): JavaCrackerServices {
        return JavaCrackerServices(context, endpointProvider, client, AndroidSchedulers.mainThread(), Schedulers.io())
    }

}