package com.example.animalapp.ui.animal_specie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.animalapp.R
import com.example.animalapp.base.BaseFragment
import com.example.animalapp.databinding.FragmentListAnimalBinding
import com.example.animalapp.model.AnimalSpecieItem
import com.example.animalapp.utils.Status
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ListAnimalFragment : BaseFragment<FragmentListAnimalBinding>(), ItemClickListener {
    private val viewModel: ListAnimalViewModel by viewModels()
    private lateinit var listAnimalAdapter: ListAnimalAdapter

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentListAnimalBinding {
        return FragmentListAnimalBinding.inflate(inflater,container,false)
    }

    override fun prepareView(savedInstanceState: Bundle?) {
        val args = this.arguments
        val name = args?.getString("animal_type")
        viewModel.getAnimalSpecies(name?:"a")
        observeModel()

        listAnimalAdapter = ListAnimalAdapter(this)
        binding.mRecyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = listAnimalAdapter
            setHasFixedSize(true)
        }

    }

    private fun observeModel() {
        viewModel.dataFlow.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    listAnimalAdapter.submitList(it.data)
                   // Log.d("CHECK", it.data?.get(0).toString())
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

    override fun itemOnClick(animalSpecieItem: AnimalSpecieItem) {
        val bundle = Bundle().apply {
            putSerializable("animal_specie_item", animalSpecieItem)
        }
        findNavController().navigate(R.id.action_listAnimalFragment_to_specieDetailFragment, bundle)
    }
}