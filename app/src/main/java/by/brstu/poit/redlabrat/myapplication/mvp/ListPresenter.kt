package by.brstu.poit.redlabrat.myapplication.mvp

import by.brstu.poit.redlabrat.myapplication.api.OmdbApi
import by.brstu.poit.redlabrat.myapplication.db.MovieSearchDao
import by.brstu.poit.redlabrat.myapplication.db.MovieSearchDb
import by.brstu.poit.redlabrat.myapplication.model.SearchItem
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleOnSubscribe
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class ListPresenter(
    val apiService: OmdbApi,
    val movieDao: MovieSearchDao
) : MvpPresenter<ListView>() {

    var compositeDisposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        getCachedMoviesList()
    }

    fun getMovies(searchKey: String) {
        viewState.showHideLoading(true)
        compositeDisposable.add(
            apiService.searchMovie(searchKey)
                .subscribeOn(Schedulers.io())
                .map {
                    movieDao.saveMoviesList(it.moviesList)
                    it
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { result, error ->
                    viewState.showListOfMovies(result.moviesList)
                    viewState.showHideLoading(false)
                }
        )
    }

    fun getCachedMoviesList() {
        compositeDisposable.add(
            Single.create<List<SearchItem>> { singleEmitter ->
                SingleOnSubscribe<List<SearchItem>> { emitter ->
                    val items = movieDao.getSavedMovies()
                    emitter.onSuccess(items)
                }.subscribe(singleEmitter)
            }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { items, error ->
                    if (items != null && error == null) {
                        viewState.showListOfMovies(items)
                    }
                }
        )
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }
}