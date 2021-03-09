package by.brstu.poit.redlabrat.myapplication.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "search_item")
data class SearchItem(
        @SerializedName("Title")
        val title: String,
        @SerializedName("Year")
        val year: String,
        @PrimaryKey
        @SerializedName("imdbID")
        val imdbId: String,
        @SerializedName("Type")
        val type: String,
        @SerializedName("Poster")
        val poster: String
)

/*
{
"Title":"Beta Test",
"Year":"2016",
"imdbID":"tt4244162",
"Type":"movie",
"Poster":"https://m.media-amazon.com/images/M/MV5BODdlMjU0MDYtMWQ1NC00YjFjLTgxMDQtNDYxNTg2ZjJjZDFiXkEyXkFqcGdeQXVyMTU2NTcxNDg@._V1_SX300.jpg"
},
 */