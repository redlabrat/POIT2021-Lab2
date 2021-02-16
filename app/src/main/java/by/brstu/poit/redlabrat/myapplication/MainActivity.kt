package by.brstu.poit.redlabrat.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import by.brstu.poit.redlabrat.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), PlanetListAdapter.OnPlanetItemClick {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .add(R.id.contentContainer, ListFragment())
            .commit()
    }

    override fun onPlanetClick(planet: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.contentContainer, DetailsFragment.newInstance(planet))
            .addToBackStack(null)
            .commit()
    }
}