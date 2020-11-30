package com.example.moviesrxjava.viewmodel

import com.example.moviesrxjava.model.MoviesResponse
import com.example.moviesrxjava.model.Network
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class MoviesViewModel {

    fun getMovies():Observable<List<MoviesResponse>>{

        return Network.getInstance().movieApi.getMovies()
                .subscribeOn(Schedulers.io()) //Modify the Upstream
                .observeOn(AndroidSchedulers.mainThread())   //Modify the Downstream

    }

}