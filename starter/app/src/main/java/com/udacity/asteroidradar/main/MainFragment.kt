package com.udacity.asteroidradar.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.database.AsteroidRadarDatabase
import com.udacity.asteroidradar.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private val viewModel: MainFragmentViewModel by lazy {
        val application = requireNotNull(this.activity).application
        val dataSource = AsteroidRadarDatabase.getInstance(application).asteroidRadarDatabaseDao
        val viewModelFactory = MainFragmentViewModelFactory(dataSource, application)
        ViewModelProvider(this, viewModelFactory).get(MainFragmentViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        setHasOptionsMenu(true)

//        val application = requireNotNull(this.activity).application
//        val dataSource = AsteroidDatabase.getInstance(application).asteroidDatabaseDao
//        val viewModelFactory = MainFragmentViewModelFactory(dataSource, application)
//        val mainFragmentViewModel = ViewModelProvider(this, viewModelFactory).get(MainFragmentViewModel::class.java)

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
