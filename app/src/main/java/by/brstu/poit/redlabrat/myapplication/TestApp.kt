package by.brstu.poit.redlabrat.myapplication

import android.app.Application
import androidx.room.Room
import by.brstu.poit.redlabrat.myapplication.api.OmdbApi
import by.brstu.poit.redlabrat.myapplication.db.MovieSearchDao
import by.brstu.poit.redlabrat.myapplication.db.MovieSearchDb
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class TestApp : Application() {

    lateinit var service: OmdbApi
    lateinit var movieDao: MovieSearchDao

    override fun onCreate() {
        super.onCreate()

        service = Retrofit.Builder()
            .baseUrl("https://www.omdbapi.com/")
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OmdbApi::class.java)

        movieDao = Room.databaseBuilder(
            applicationContext,
            MovieSearchDb::class.java, "movie_db"
        )
            .build()
            .getMovieDao()
    }


}