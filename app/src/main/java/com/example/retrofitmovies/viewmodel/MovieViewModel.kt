package com.example.retrofitmovies.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofitmovies.api.MoviesApi
import com.example.retrofitmovies.model.Movies
import com.example.retrofitmovies.model.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel : ViewModel() {
    var results: MutableLiveData<List<Result>> = MutableLiveData()
    //getter..........
    fun getResult(): LiveData<List<Result>> = results

    private val moviesApi: MoviesApi = MoviesApi()//obj build init block work
    //setter..........
    fun loadResult() {
        val apiCall = moviesApi.getMovies("59e90160f05dc382b043b086e34c75c5")

        apiCall.enqueue(object : Callback<Movies> {
            override fun onFailure(call: Call<Movies>, t: Throwable) {

            }

            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                val a = response.body()?.results
                Log.d("response>>>", a.toString())
                response.isSuccessful.let {
                    val resultList:List<Result> =response.body()?.results ?: emptyList()
                    results.value = resultList
                }
            }

        })
    }
}