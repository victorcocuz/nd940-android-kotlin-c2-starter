package com.udacity.asteroidradar.main

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.database.AsteroidRadarDatabase
import com.udacity.asteroidradar.databinding.FragmentMainBinding
import com.udacity.asteroidradar.main.AsteroidListener as AsteroidListener

class MainFragment : Fragment() {

    // Create view model
    private val viewModel: MainFragmentViewModel by lazy {
        val application = requireNotNull(this.activity).application
        val dataSource = AsteroidRadarDatabase.getDatabase(application).asteroidRadarDatabaseDao
        val viewModelFactory = MainFragmentViewModelFactory(dataSource, application)
        ViewModelProvider(this, viewModelFactory).get(MainFragmentViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        setHasOptionsMenu(true)

        // RecyclerView Adapter
        val adapter = MainFragmentAdapter(AsteroidListener {
            asteroidId ->  viewModel.onAsteroidClicked(asteroidId)
        })
        binding.asteroidList.adapter = adapter

        viewModel.asteroids.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })
        viewModel.navigateToDetailFragment.observe(viewLifecycleOwner, Observer { asteroidId ->
            asteroidId?.let {
                this.findNavController().navigate(MainFragmentDirections.actionShowDetail(asteroidId))
                viewModel.onShowDetailNavigated()
            }
        })

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return true
    }
}
