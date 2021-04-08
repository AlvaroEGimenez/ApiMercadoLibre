package com.meli.challenge.adapters

import android.view.View
import com.meli.challenge.R
import com.meli.challenge.utils.bindings
import com.meli.challenge.databinding.ResultItemBinding
import com.meli.challenge.models.Product

/**Custom adapter for all search results*/
class SearchAdapter(private val listener: (Product)-> Unit) : BaseAdapter() {

    override fun layout(type: String) = R.layout.result_item

    override fun viewHolder(layout: Int, view: View): BaseViewHolder {
        return RocketsViewHolder(view)
    }

    inner class RocketsViewHolder(view: View) : BaseViewHolder(view){

        private val binding: ResultItemBinding by bindings(view)

        override fun bindData(item: Any) {
            itemView.setOnClickListener {
                if (item is Product)
                    listener(item)
            }
            if(item is Product){
                binding.apply {
                    product = item
                }
            }
        }
    }
}