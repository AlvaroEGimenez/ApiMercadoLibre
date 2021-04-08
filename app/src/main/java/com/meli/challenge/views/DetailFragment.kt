package com.meli.challenge.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.meli.challenge.R
import com.meli.challenge.databinding.FragmentDetailBinding
import com.meli.challenge.models.Product
import com.meli.challenge.utils.autoCleared
import java.text.NumberFormat
import java.util.*

class DetailFragment : Fragment() {

    private var binding: FragmentDetailBinding by autoCleared()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val product = arguments?.getParcelable<Product>("product")
        loadProduct(product)
    }

    private fun loadProduct(product: Product?) {
        val currencyFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
        Glide.with(this).load(product?.thumbnail)
            .into(binding.detailThumbnailIv)
        binding.detailTitleTv.text = product?.title
        val condition = if (product?.condition == "new") getString(R.string.productnew) else getString(R.string.used)
        binding.detailConditionTv.text = condition
        binding.detailAddressTv.text = product?.address?.stateName
        binding.detailPriceTv.text = currencyFormat.format(product?.price?.toDouble())
    }

}