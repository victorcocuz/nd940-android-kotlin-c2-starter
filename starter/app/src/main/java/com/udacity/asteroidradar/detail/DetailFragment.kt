package com.udacity.asteroidradar.detail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.FragmentDetailBinding
import com.udacity.asteroidradar.main.MainFragmentViewModel
import com.udacity.asteroidradar.MainFragmentViewModelFactory
import timber.log.Timber

class DetailFragment : Fragment() {
    // Create view model
    private val viewModel: DetailFragmentViewModel by lazy {
        val viewModelFactory = MainFragmentViewModelFactory(requireNotNull(this.activity).application)
        ViewModelProvider(this, viewModelFactory).get(DetailFragmentViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val asteroidId = DetailFragmentArgs.fromBundle(requireArguments()).selectedAsteroid
        viewModel.getAsteroidById(asteroidId)

        viewModel.asteroid.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.asteroid = viewModel.asteroid.value
            }
        })

        binding.helpButton.setOnClickListener {
            displayAstronomicalUnitExplanationDialog()
        }

        return binding.root
    }

    private fun displayAstronomicalUnitExplanationDialog() {
        val builder = AlertDialog.Builder(requireActivity())
            .setMessage(getString(R.string.astronomica_unit_explanation))
            .setPositiveButton(android.R.string.ok, null)
        builder.create().show()
    }
}
