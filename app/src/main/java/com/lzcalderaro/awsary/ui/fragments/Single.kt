package com.lzcalderaro.awsary.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.lzcalderaro.awsary.databinding.SingleFragmentBinding
import com.lzcalderaro.awsary.viewModels.AwsServicesViewModel
import io.noties.markwon.Markwon
import org.koin.androidx.viewmodel.ext.android.activityViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class Single : Fragment() {

    private var _binding: SingleFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val awsViewModel by activityViewModel<AwsServicesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = SingleFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadView()

/*        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SingleFragment_to_ArchiveFragment)
        }*/
    }

    private fun loadView() {
        Glide.with(requireContext()).load(awsViewModel.selectedItem?.imageURL).into(binding.headerImage)
        binding.headerTitle.text = awsViewModel.selectedItem?.longName

        val markdown = Markwon.create(requireContext())
        markdown.setMarkdown(binding.contentText, awsViewModel.selectedItem?.shortDescription.toString())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}