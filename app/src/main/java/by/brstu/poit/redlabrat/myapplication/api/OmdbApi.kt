package by.brstu.poit.redlabrat.myapplication.api

import by.brstu.poit.redlabrat.myapplication.model.SearchResult
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OmdbApi {
    @GET("/")
    fun searchMovie(@Query("s") request: String,
                    @Query("apikey") apiKey: String = "c8d2a36b"): Single<SearchResult>

}