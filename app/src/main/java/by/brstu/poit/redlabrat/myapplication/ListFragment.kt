package by.brstu.poit.redlabrat.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import by.brstu.poit.redlabrat.myapplication.databinding.FragmentListBinding

class ListFragment : Fragment() {

    companion object {
        val listOfPlanets = listOf<String>("Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune")
    }

    private lateinit var binding: FragmentListBinding


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
        binding.recyclerView.adapter = PlanetListAdapter(listOfPlanets,
            requireActivity() as? PlanetListAdapter.OnPlanetItemClick)
        binding.loadingProgress.isVisible = false
    }
}