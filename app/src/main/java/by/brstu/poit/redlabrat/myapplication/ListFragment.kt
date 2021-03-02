package by.brstu.poit.redlabrat.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import by.brstu.poit.redlabrat.myapplication.databinding.FragmentListBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class ListFragment : Fragment() {

    companion object {
        val listOfPlanets = listOf<String>("Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune")
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
        adapter = PlanetListAdapter(listOfPlanets,
            requireActivity() as? PlanetListAdapter.OnPlanetItemClick)
        binding.recyclerView.adapter = adapter
        binding.loadingProgress.isVisible = false
        getMovies("test")
    }

    private fun getMovies(searchKey: String) {
        (activity?.application as? TestApp)?.service?.apply {
            searchMovie(searchKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { result, error ->
                    if (result != null && error == null) {
                        val newList = result.moviesList.map { it.title }
                        adapter?.setNewItems(newList)
                    }
                }
        }
    }
}