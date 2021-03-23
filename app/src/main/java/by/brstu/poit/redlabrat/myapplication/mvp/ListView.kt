package by.brstu.poit.redlabrat.myapplication.mvp

import by.brstu.poit.redlabrat.myapplication.model.SearchItem
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface ListView : MvpView {
    fun showListOfMovies(items: List<SearchItem>)

    fun showHideLoading(isProgressVisible: Boolean)
}