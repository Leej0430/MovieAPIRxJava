package com.example.moviesrxjava.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.moviesrxjava.R
import com.example.moviesrxjava.model.MoviesResponse
import com.example.moviesrxjava.viewmodel.MoviesViewModel
import com.google.android.material.snackbar.Snackbar
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.observers.DisposableObserver
import org.reactivestreams.Subscriber

class MainActivity : AppCompatActivity() {
private val TAG ="MainActivity"

   private val viewModel: MoviesViewModel by lazy {
    MoviesViewModel()
   }

    private var disposable :DisposableObserver<List<MoviesResponse>> =
            object :DisposableObserver<List<MoviesResponse>>() {
                override fun onNext(t: List<MoviesResponse>?) {
                    TODO("Not yet implemented")
                }

                override fun onError(e: Throwable?) {
                    TODO("Not yet implemented")
                }

                override fun onComplete() {
                    TODO("Not yet implemented")
                }
            }
    
                override fun onCreate(savedInstanceState: Bundle?) {
                    super.onCreate(savedInstanceState)
                    setContentView(R.layout.activity_main)

                    viewModel.getMovies().subscribe(
                            object : Observer<List<MoviesResponse>> {
                                override fun onSubscribe(d: Disposable?) {
                                }

                                override fun onNext(t: List<MoviesResponse>?) {
                                    t?.let {
                                        Log.d(TAG, "onError: ${it.toString()}")
                                    }
                                }

                                override fun onError(e: Throwable?) {
                                    e?.let {
                                        Snackbar.make(findViewById(R.id.main_activity_root),
                                                "${it.message}",
                                                Snackbar.LENGTH_LONG).show()
                                    }
                                }

                                override fun onComplete() {

                                }

                            }
                    )
                    //defensive code to prevent observation
                    //when the view is in a configuration change
                    viewModel.getMovies().subscribe(
                            disposable
                    )
                }

    override fun onStop() {
        super.onStop()
        //Disconnect the current Observation
        disposable.dispose()
    }
}