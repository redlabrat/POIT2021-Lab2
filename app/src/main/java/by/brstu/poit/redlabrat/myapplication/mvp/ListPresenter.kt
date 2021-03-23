package by.brstu.poit.redlabrat.myapplication.mvp

import by.brstu.poit.redlabrat.myapplication.api.OmdbApi
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class ListPresenter(
    val apiService: OmdbApi
) : MvpPresenter<ListView>() {

    fun getMovies(searchKey: String) {
        apiService.searchMovie(searchKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result, error ->
                viewState.showListOfMovies(result.moviesList)
            }
    }

/*                .observeOn(Schedulers.computation())
                .map {
                    if (it != null) {
                        (activity?.application as? TestApp)?.movieDao?.apply {
                            for (movie in it.moviesList) {
                                saveMovie(movie)
                            }
                        }
                    }
                    it
                }*/

}