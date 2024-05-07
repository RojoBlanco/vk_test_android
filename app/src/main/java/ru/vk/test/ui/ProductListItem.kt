package ru.vk.test.ui

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.vk.test.R
import ru.vk.test.model.Product

class ProductListItem(view: View): RecyclerView.ViewHolder(view) {

    private val thumbnail: ImageView = view.findViewById(R.id.thumbnail_image_view)
    private val title: TextView = view.findViewById(R.id.title_textView)
    private val description: TextView = view.findViewById(R.id.description_textView)

    fun bind(product:Product) {
        Glide.with(thumbnail).load(product.thumbnail).into(thumbnail)
        title.text = "${product.title}"
        description.text = product.description
    }
}