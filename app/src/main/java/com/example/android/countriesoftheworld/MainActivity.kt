package com.example.android.countriesoftheworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.countriesoftheworld.databinding.ActivityMainBinding
import com.example.android.countriesoftheworld.model.CountryData
import com.example.android.countriesoftheworld.network.ServiceBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.country_list_item.view.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val compositeDisposable = CompositeDisposable()
    private val countryAdapter = CountryAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.countryList.layoutManager = LinearLayoutManager(this@MainActivity)
        binding.countryList.adapter = countryAdapter

        compositeDisposable.add(
            ServiceBuilder.buildService().getCountries()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ response -> onResponse(response) }, { t -> onFailure(t) })
        )
    }

    private fun onFailure(t: Throwable) {
        Toast.makeText(this, t.message, Toast.LENGTH_LONG).show()
    }

    private fun onResponse(response: List<CountryData>) {
        countryAdapter.countries = response
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}