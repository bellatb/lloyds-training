package com.example.android.countriesoftheworld

import android.app.Activity
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.countriesoftheworld.model.CountryData
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou

class CountryAdapter(private val activity: Activity) : RecyclerView.Adapter<CountryAdapter.ViewHolder>() {


    inner class ViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {

        private val flagImageView: ImageView = itemView.findViewById(R.id.flag_image)
        private val nameTextView: TextView = itemView.findViewById(R.id.country_name)
        private val populationTextView: TextView = itemView.findViewById(R.id.population)
        private val capitalTextView: TextView = itemView.findViewById(R.id.capital_city)
        private val regionTextView: TextView = itemView.findViewById(R.id.region)
        private val subregionTextView: TextView = itemView.findViewById(R.id.subregion)
        private val timezonesTextView: TextView = itemView.findViewById(R.id.timezones)

        fun bind(country: CountryData) {
            nameTextView.text = country.name
            populationTextView.text = "Population: " + country.population
            capitalTextView.text = "Capital: " + country.capital
            regionTextView.text = "Region: " + country.region
            subregionTextView.text = "Subregion: " + country.subregion
            var timezoneString = country.timezones.toString().replace("[", "").replace("]", "")
            timezonesTextView.text = "Timezones: " + timezoneString
            GlideToVectorYou.justLoadImage(activity, Uri.parse(country.flag), flagImageView)
        }
    }

    var countries: List<CountryData> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.country_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val country = countries[position]
        return holder.bind(country)
    }

    override fun getItemCount() = countries.size

}


