package by.brstu.poit.redlabrat.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import by.brstu.poit.redlabrat.myapplication.databinding.FragmentListBinding
import by.brstu.poit.redlabrat.myapplication.model.SearchItem
import by.brstu.poit.redlabrat.myapplication.mvp.ListPresenter
import by.brstu.poit.redlabrat.myapplication.mvp.ListView
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class ListFragment : MvpAppCompatFragment(), ListView {

    companion object {
        val listOfPlanets = listOf<String>(
            "Mercury",
            "Venus",
            "Earth",
            "Mars",
            "Jupiter",
            "Saturn",
            "Uranus",
            "Neptune"
        )
    }

    @InjectPresenter
    lateinit var presenter: ListPresenter

    @ProvidePresenter
    fun providePresenter(): ListPresenter {
        val app = (requireActivity().application as TestApp)
        return app.listPresenter
    }

    private lateinit var binding: FragmentListBinding
    private var adapter: PlanetListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = PlanetListAdapter(
            emptyList(),
            requireActivity() as? PlanetListAdapter.OnPlanetItemClick
        )
        binding.recyclerView.adapter = adapter
        binding.loadingProgress.isVisible = false
        binding.buttonSearch.setOnClickListener {
            binding.loadingProgress.isVisible = true
            presenter.getMovies("test")
        }
    }

    override fun showListOfMovies(items: List<SearchItem>) {
        val newList = items.map { it.title }
        adapter?.setNewItems(newList)
        binding.loadingProgress.isVisible = false
    }

 /*   private fun getMovies(searchKey: String) {
        (activity?.application as? TestApp)?.service?.apply {
            binding.loadingProgress.isVisible = true
            searchMovie(searchKey)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .map {
                    if (it != null) {
                        (activity?.application as? TestApp)?.movieDao?.apply {
                            for (movie in it.moviesList) {
                                saveMovie(movie)
                            }
                        }
                    }
                    it
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { result, error ->
                    if (result != null && error == null) {
                        val newList = result.moviesList.map { it.title }
                        adapter?.setNewItems(newList)
                    } else {
                        Toast.makeText(context, error.localizedMessage, Toast.LENGTH_LONG).show()
                    }
                    binding.loadingProgress.isVisible = false
                }
        }
    }*/
}