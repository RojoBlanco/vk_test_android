package ru.vk.test.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.vk.test.api.ApiFactory
import ru.vk.test.model.ProductsResponse

class ProductViewModel(application : Application) :AndroidViewModel(application) {

    private val compositeDispensable = CompositeDisposable()
    var products: LiveData<ProductsResponse>  = MutableLiveData()
    var isLoading: LiveData<Boolean> = MutableLiveData()
    var isError: LiveData<Boolean> = MutableLiveData()
    init{
        loadData()
    }
    fun loadData(skip: Int)
    {
        val disposable =  ApiFactory.apiService
            .getProducts(skip = skip)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe{
                (isError as MutableLiveData).value = false
                (isLoading as MutableLiveData).value = true
            }
            .doOnTerminate {
                (isLoading as MutableLiveData).value = false
            }
            .doOnError{
                (isError as MutableLiveData).value = true
            }
            .subscribe({
                (isError as MutableLiveData).value = false
                (products as MutableLiveData).value = it
                Log.d("TEST_OF_LOADING_DATA", it.products.toString())
            }, {

                Log.d("TEST_OF_LOADING_DATA", "feelsBadMan")
            })
        compositeDispensable.add(disposable)
    }
    fun loadData(){

        val disposable =  ApiFactory.apiService
            .getProducts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe{
                (isLoading as MutableLiveData).value = true
            }
            .doOnTerminate {
                (isLoading as MutableLiveData).value = false
            }
            .subscribe({
                (products as MutableLiveData).value = it
                Log.d("TEST_OF_LOADING_DATA", it.products.toString())
            }, {
                Log.d("TEST_OF_LOADING_DATA", "feelsBadMan")
            })
        compositeDispensable.add(disposable)
    }
    override fun onCleared() {
        super.onCleared()
        compositeDispensable.dispose()
    }
}