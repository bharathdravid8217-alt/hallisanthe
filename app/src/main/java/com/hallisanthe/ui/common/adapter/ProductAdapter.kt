package com.hallisanthe.ui.common.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hallisanthe.R
import com.hallisanthe.data.model.Product
import com.hallisanthe.databinding.ItemProductBinding

class ProductAdapter(private val onClick: (Product) -> Unit) : ListAdapter<Product, ProductAdapter.ProductViewHolder>(Diff) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false), onClick)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) = holder.bind(getItem(position))

    class ProductViewHolder(private val binding: ItemProductBinding, private val onClick: (Product) -> Unit) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) = with(binding) {
            productName.text = product.productName
            productPrice.text = "₹%.0f".format(product.price)
            productMeta.text = product.artisanLocation
            productRating.text = "%.1f".format(product.averageRating)
            
            if (product.imageUrl.isNotEmpty()) {
                Glide.with(root.context).load(product.imageUrl).into(productImage)
            } else {
                productImage.setImageResource(R.drawable.bg_orange_gradient)
            }

            root.setOnClickListener { onClick(product) }
        }
    }

    object Diff : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product) = oldItem.productId == newItem.productId
        override fun areContentsTheSame(oldItem: Product, newItem: Product) = oldItem == newItem
    }
}
