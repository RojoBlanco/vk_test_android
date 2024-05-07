package ru.vk.test

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import ru.vk.test.ui.ProductListAdapter
import ru.vk.test.viewmodel.ProductViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ProductViewModel
    private var nowSkip = 0
    private lateinit var nextPageButton: Button
    private lateinit var previousPageButton: Button
    var isError: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nextPageButton = findViewById(R.id.nextPageButton)
        previousPageButton = findViewById(R.id.previousPageButton)
        val recyclerView = findViewById<RecyclerView>(R.id.rvProductList)

        val adapter = ProductListAdapter()
        recyclerView.adapter = adapter
        viewModel = ViewModelProvider(this)[ProductViewModel::class.java]

        viewModel.isError.observe(this) {
            if (it) {
                Toast.makeText(
                    this,
                    R.string.error_text,
                    Toast.LENGTH_LONG
                ).show()
                isError = true
            } else isError = false
        }
        viewModel.isLoading.observe(this) {
            if (it) findViewById<ProgressBar>(R.id.progressBar2).visibility = View.VISIBLE
            else findViewById<ProgressBar>(R.id.progressBar2).visibility = View.GONE
        }
        viewModel.products.observe(this, Observer {
            adapter.setProducts(it.products)

        })

        if (nowSkip <= 0) previousPageButton.visibility = View.GONE
        nextPageButton.setOnClickListener {
            if (!isError) nowSkip += 20
                if (nowSkip >= 80) nextPageButton.visibility = View.GONE
                else nextPageButton.visibility = View.VISIBLE
                if (nowSkip > 0) previousPageButton.visibility = View.VISIBLE
                viewModel.loadData(nowSkip)
                viewModel.products.observe(this) {
                    adapter.setProducts(it.products)

            }
        }
        previousPageButton.setOnClickListener {
            if (!isError) nowSkip -= 20
                if (nowSkip <= 0) previousPageButton.visibility = View.GONE
                else nextPageButton.visibility = View.VISIBLE
                viewModel.loadData(nowSkip)
                viewModel.products.observe(this) {
                    adapter.setProducts(it.products)
                }

        }
    }


}


