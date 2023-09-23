package com.lzcalderaro.awsary.ui.fragments

import android.content.ClipData
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.lzcalderaro.awsary.R
import com.lzcalderaro.awsary.databinding.ArchiveFragmentBinding
import com.lzcalderaro.awsary.ui.adapters.AwsServicesAdapter
import com.lzcalderaro.awsary.utils.LocalResources
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

    private val awsViewModel by activityViewModel<AwsServicesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ArchiveFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        awsViewModel.getAwsServices().observe(viewLifecycleOwner) { awsServices ->

            if (awsServices != null) {
                populateGrid(awsServices)
            } else {
                Log.d("AWSARYDEBUG", "VIM DO OFFLINE?")

                awsViewModel.awsList = LocalResources(requireContext()).load()
                populateGrid(awsViewModel.awsList!!)
            }
        }
    }

    private fun bindButtons(myView: GridView, adapter: AwsServicesAdapter) {
        binding.search.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                adapter.filter.filter(p0)
                return false
            }

        })

        binding.search.setOnQueryTextFocusChangeListener { _, hasFocus ->
            binding.search.isSelected = hasFocus
            binding.search.isIconified = !hasFocus
        }

        myView.setOnItemClickListener { _, _, position, _ ->
            awsViewModel.selectedItem = adapter.awsFilterableList?.get(position)
            findNavController().navigate(R.id.action_ArchiveFragment_to_SingleFragment)
        }

        myView.setOnItemLongClickListener { _, view, position, _ ->

            val awsItem =  adapter.awsFilterableList?.get(position)?.imageURL

            val dragData = ClipData.newPlainText("image_path", awsItem)
            val dragShadowBuilder = View.DragShadowBuilder(view)

            // Start the drag operation
            view.startDragAndDrop(dragData, dragShadowBuilder, null, View.DRAG_FLAG_GLOBAL)
            true
        }
    }

    private fun populateGrid(list: List<AwsItem>) {

        val grid: GridView = binding.servicesList
        val adapter = AwsServicesAdapter(list, requireContext())

        grid.adapter = adapter

        bindButtons(grid, adapter)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}