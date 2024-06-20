package com.example.animalapp.ui.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animalapp.R
import com.example.animalapp.base.BaseFragment
import com.example.animalapp.databinding.FragmentSearchBinding
import com.example.animalapp.model.SearchDetailItem
import com.example.animalapp.ui.gallery.ListSpecieImageAdapter
import com.example.animalapp.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(), SearchItemListener {
    private val viewModel: SearchViewModel by viewModels()
    private lateinit var searchViewAdapter: SearchViewAdapter
    private var searchDetail: MutableList<SearchDetailItem> = mutableListOf()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentSearchBinding.inflate(inflater, container, false)

    override fun prepareView(savedInstanceState: Bundle?) {
        viewModel.getPrepareSearch()
        observeModel()
        setRv()
        binding.btnBack.setOnClickListener{
            findNavController().popBackStack()
        }
    }

    private fun setSearchView() {
        binding.searchView.requestFocusFromTouch()
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.searchView.clearFocus()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }

        })
    }

    private fun filterList(query: String?) {
        query?.let {
            val filteredList = mutableListOf<SearchDetailItem>()
            for (searchItem in searchDetail) {
                if (searchItem.name.lowercase().contains(it.lowercase())) {
                    filteredList.add(searchItem)
                }
            }
            if (filteredList.isEmpty()) {
                Toast.makeText(requireContext(), "No Data found", Toast.LENGTH_SHORT).show()
            }
            searchViewAdapter.queryText = it
            setFilteredList(filteredList)
        }
        if (query.isNullOrEmpty()) {
            binding.rcvDetail.layoutManager?.scrollToPosition(0)

        }
    }

    private fun setFilteredList(mList: MutableList<SearchDetailItem>) {
        searchViewAdapter.submitList(mList)
        searchViewAdapter.notifyDataSetChanged()
        binding.rcvDetail.layoutManager?.scrollToPosition(0)
    }

    private fun setRv() {
        searchViewAdapter = SearchViewAdapter(this)
        binding.rcvDetail.apply {
            adapter = searchViewAdapter
            layoutManager = LinearLayoutManager(requireContext())
            (layoutManager as LinearLayoutManager).scrollToPosition(0)
            setHasFixedSize(true)
        }
    }

    private fun observeModel() {
        viewModel.dataFlow.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { list ->
                        searchDetail = list
                        searchViewAdapter.submitList(searchDetail)
                        searchViewAdapter.notifyDataSetChanged()
                        setSearchView()
                    }
                    hideLoading()
                }

                Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    hideLoading()
                }

                Status.LOADING -> {
                    showLoading()
                }
            }
        }
    }

    private fun observeSpecieDataModel() {
        viewModel.dataSpecieFlow.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { animalSpecieItem ->
                        val bundle = Bundle().apply {
                            putSerializable("animal_specie_item", animalSpecieItem)
                        }
                        findNavController().navigate(
                            R.id.action_searchFragment_to_specieDetailFragment,
                            bundle
                        )

                    }
                    hideLoading()
                }

                Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    hideLoading()
                }

                Status.LOADING -> {
                    showLoading()
                }
            }
        }
    }

    override fun itemOnclick(searchDetailItem: SearchDetailItem) {
        if (searchDetailItem.type == 1) {
            viewModel.getAnimalSpecie(searchDetailItem.name)
            observeSpecieDataModel()
        }
        if (searchDetailItem.type == 2) {
            val bundle = Bundle().apply {
                putInt("animal_breed_detail", searchDetailItem.id)
            }
            findNavController().navigate(R.id.action_searchFragment_to_animalBreedFragment, bundle)
        }
    }

    override fun onResume() {
        binding.searchView.setQuery("", false);
        super.onResume()
    }

}