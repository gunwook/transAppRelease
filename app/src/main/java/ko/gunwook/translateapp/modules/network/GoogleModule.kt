package ko.gunwook.translateapp.modules.network

import android.content.Context
import androidx.annotation.NonNull
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import ko.gunwook.translateapp.BuildConfig
import ko.gunwook.translateapp.utils.CodeUtils
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

class GoogleModule {
    @Throws(IOException::class)
    private fun provideRetrofit(@NonNull okHttpClient : OkHttpClient) : Retrofit{
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(CodeUtils.Network.GOOGE_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
    @Throws(IOException::class)
    private fun provideOkHttpClient(context : Context) : OkHttpClient {
        val interceptor = HttpLoggingInterceptor()

        interceptor.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.NONE else HttpLoggingInterceptor.Level.BODY // logging

        return OkHttpClient.Builder()
            .connectTimeout(100, TimeUnit.SECONDS)
            .writeTimeout(100, TimeUnit.SECONDS)
            .readTimeout(300, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()
    }
    @Throws(IOException::class)
    private fun getApiCallInterface(retrofit : Retrofit) : GoogleApi {
        return retrofit.create(GoogleApi::class.java)
    }

    @Throws(IOException::class)
    fun getGoogleHelper(context : Context) : GoogleHelper {
        return GoogleHelper(getApiCallInterface(provideRetrofit(provideOkHttpClient(context))))
    }
}