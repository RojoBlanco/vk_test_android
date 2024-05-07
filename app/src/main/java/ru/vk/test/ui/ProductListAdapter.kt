package ru.vk.test.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.vk.test.R
import ru.vk.test.model.Product

class ProductListAdapter : RecyclerView.Adapter<ProductListItem>() {

    private val dataset: MutableList<Product> = mutableListOf()
    fun setProducts(products: List<Product>?) {
        if (products.isNullOrEmpty()) {
            dataset.clear()
        } else {
            dataset.clear()
            dataset.addAll(products)
        }
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListItem {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductListItem(view)
    }

    override fun onBindViewHolder(holder: ProductListItem, position: Int) {
        holder.bind(dataset[position])
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}