package by.brstu.poit.redlabrat.myapplication.model

import com.google.gson.annotations.SerializedName

data class SearchResult(
    @SerializedName("Search")
    val moviesList: List<SearchItem>,
    val totalResults: String
)
/*
{
"Search":[],
"totalResults":"743",
"Response":"True"
}
 */