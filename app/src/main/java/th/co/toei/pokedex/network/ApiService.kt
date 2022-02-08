package th.co.toei.pokedex.network

import androidx.viewbinding.BuildConfig
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiService {
    private val gson: Gson = Gson().newBuilder()
        .setLenient()
        .setPrettyPrinting()
        .create()

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    }

    private val okHttp = OkHttpClient.Builder()
        .connectTimeout(CONNECTION_TIME_OUT, TimeUnit.SECONDS)
        .readTimeout(READ_CONNECTION_TIME_OUT, TimeUnit.SECONDS)
        .writeTimeout(WRITE_CONNECTION_TIME_OUT, TimeUnit.SECONDS)
        .addInterceptor(loggingInterceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .client(okHttp)
        .baseUrl("https://run.mocky.io/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    fun <T> getEndpointInterface(mService: Class<T>): T {
        return retrofit.create(mService)
    }

    companion object {
        private const val CONNECTION_TIME_OUT = 60L
        private const val READ_CONNECTION_TIME_OUT = 60L
        private const val WRITE_CONNECTION_TIME_OUT = 60L
    }
}
