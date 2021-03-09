package by.brstu.poit.redlabrat.myapplication.db

import androidx.room.Database
import androidx.room.RoomDatabase
import by.brstu.poit.redlabrat.myapplication.model.SearchItem

@Database(entities = [SearchItem::class], version = 1)
abstract class MovieSearchDb : RoomDatabase() {
    abstract fun getMovieDao(): MovieSearchDao
}