package ru.twotwobyte.news.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.twotwobyte.news.pojo.CommonInfo

interface ApiService {
    //https://newsapi.org/v2/top-headlines?country=us&apiKey=04b0c21a343843e9a55c8238169cc722

    @GET("top-headlines")
    fun getTopHeadlines(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = API,
        @Query(QUERY_PARAM_COUNTRY) language: String = COUNTRY
    ): Single<CommonInfo>


    //https://newsapi.org/v2/top-headlines?country=us&apiKey=04b0c21a343843e9a55c8238169cc722

    @GET("top-headlines")
    fun getSearchHeadlines(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = API,
        @Query(QUERY_PARAM_COUNTRY) language: String = COUNTRY,
        @Query(QUERY_PARAM_QUERY) query: String
    ): Single<CommonInfo>

    companion object {
        private const val QUERY_PARAM_API_KEY = "apiKey"
        private const val QUERY_PARAM_COUNTRY = "country"
        private const val QUERY_PARAM_QUERY = "q"

        private const val API = "04b0c21a343843e9a55c8238169cc722"
        private const val COUNTRY  = "us"
    }
}