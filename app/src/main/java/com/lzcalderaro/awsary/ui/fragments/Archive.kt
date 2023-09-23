package com.lzcalderaro.awsary.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import com.lzcalderaro.awsary.databinding.ArchiveFragmentBinding
import com.lzcalderaro.awsary.ui.adapters.AwsServicesAdapter
import com.lzcalderaro.awsary.viewModels.AwsServicesViewModel
import com.lzcalderaro.awsary.webservice.dto.AwsItem
import org.koin.androidx.viewmodel.ext.android.activityViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class Archive : Fragment() {

    private var _binding: ArchiveFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val awsList by activityViewModel<AwsServicesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ArchiveFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        awsList.getAwsServices().observe(viewLifecycleOwner) { awsServices ->

            if (awsServices != null) {
                populateGrid(awsServices)
            }
        }
    }

    private fun populateGrid(list: List<AwsItem>) {
        val grid: GridView = binding.servicesList
        val adapter = AwsServicesAdapter(list, requireContext())
        grid.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}