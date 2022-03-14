package com.example.mypinterest.network.service

import com.example.mypinterest.model.*
import com.example.mypinterest.model.Collection
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

@JvmSuppressWildcards
interface PhotosService {

    companion object {
        private const val ACCESS_KEY = "rX-ePPWWGMdwJ58-Jxp-hzleGrGHMp-22imUj-nigqw"
    }

    @Headers("Authorization: Client-ID $ACCESS_KEY")
    @GET("users/{username}")
    fun getUser(@Path("username") username: String = "maestro7205"): Call<Profile>

    @Headers("Authorization: Client-ID $ACCESS_KEY")
    @GET("photos")
    fun listPhotos(
        @Query("page") page: Int,
        @Query("per_page") per_page: Int = 20,
    ): Call<ArrayList<Photo>>

    @Headers("Authorization: Client-ID $ACCESS_KEY")
    @GET("photos/{id}/related")
    fun getRelatedPhotos(@Path("id") id: String): Call<RelatedPhotos>

    @Headers("Authorization: Client-ID $ACCESS_KEY")
    @GET("search/photos")
    fun searchPhoto(
        @Query("query") query: String,
        @Query("page") page: Int = 1,
        @Query("per_page") per_page: Int = 20,
    ): Call<Search>

    @Headers("Authorization: Client-ID $ACCESS_KEY")
    @GET("topics")
    fun getTopics(
        @Query("page") page: Int = 2,
        @Query("per_page") per_page: Int = 10,
    ): Call<ArrayList<Topic>>

    @Headers("Authorization: Client-ID $ACCESS_KEY")
    @GET("collections")
    fun getCollections(
        @Query("page") page: Int = 2,
        @Query("per_page") per_page: Int = 10,
    ): Call<ArrayList<Collection>>


}