package by.brstu.poit.redlabrat.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.brstu.poit.redlabrat.myapplication.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    companion object {
        const val ARG_PLANET_NAME = "arg planet name"

        fun newInstance(planetName: String): DetailsFragment {
            val fragment = DetailsFragment()
            val arguments = Bundle()
            arguments.putString(ARG_PLANET_NAME, planetName)
            fragment.arguments = arguments
            return fragment
        }
    }

    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textView.text = arguments?.getString(ARG_PLANET_NAME)
    }

}