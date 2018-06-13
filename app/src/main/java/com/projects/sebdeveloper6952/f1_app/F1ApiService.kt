package com.projects.sebdeveloper6952.f1_app

import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_season_details.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface F1ApiService {

    @GET("{season}.json")
    fun getSeason(@Path("season") season: String?): Call<Season>

    companion object {

        const val baseUrl = "http://ergast.com/api/f1/"

        fun newInstance(): F1ApiService {
            // TODO: right now we can only deserialize to Season objects
            val gson = GsonBuilder()
                    .registerTypeAdapter(Season::class.java, SeasonJsonDeserializer())
                    .create()
            val retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
            val apiService = retrofit.create(F1ApiService::class.java)
            return apiService
        }
    }
}