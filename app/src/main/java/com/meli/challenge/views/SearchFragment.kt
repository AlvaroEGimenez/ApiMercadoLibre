package com.meli.challenge.views

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.meli.challenge.R
import com.meli.challenge.adapters.SearchAdapter
import com.meli.challenge.databinding.FragmentSearchBinding
import com.meli.challenge.databinding.NetworkErrorBinding
import com.meli.challenge.utils.Resource
import com.meli.challenge.utils.autoCleared
import com.meli.challenge.utils.invisible
import com.meli.challenge.utils.visible
import com.meli.challenge.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val mainViewModel: MainViewModel by viewModels()
    private var binding: FragmentSearchBinding by autoCleared()
    private lateinit var adapter: SearchAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater,container, false)
        adapter = SearchAdapter { product ->
            val bundle = bundleOf("product" to product)
            findNavController().navigate(R.id.detailFragment, bundle)
        }
        setupObservers()
        return binding.root
    }

    private fun setupObservers() {
        mainViewModel.response.observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    if (it.data?.list?.isEmpty() == true){
                        binding.emptyStateTv.visible()
                        Log.i("Search Fragment","Data result is empty")
                    }else {
                        it.data?.let { resultList -> adapter.setItems(resultList.list)
                            Log.i("Search Fragment","Load data to adaper")}
                        binding.progressBar.invisible()
                    }
                }
                Resource.Status.LOADING -> binding.progressBar.visible()

                Resource.Status.ERROR -> {
                    binding.progressBar.invisible()
                    binding.errorLayout.constraintError.visible()
                    binding.errorLayout.btnBack.setOnClickListener {
                        activity?.onBackPressed()
                    }
                    Log.e("Search Fragment",it.message.toString())
                }
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerview.adapter = adapter
    }
}