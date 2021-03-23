package by.brstu.poit.redlabrat.myapplication.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import by.brstu.poit.redlabrat.myapplication.model.SearchItem

@Dao
interface MovieSearchDao {
    @Query("select * from search_item")
    fun getSavedMovies(): List<SearchItem>

    @Insert
    fun saveMovie(movie: SearchItem)
    @Insert
    fun saveMoviesList(items: List<SearchItem>)
}