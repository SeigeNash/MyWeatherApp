package com.example.myweatherapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myweatherapp.databinding.WeatherRecyclerviewItemBinding
import com.squareup.picasso.Picasso
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale

class WeatherAdapter(
    private var context : Context,
    private var weatherModelArrayList : ArrayList<WeatherModel>

) : RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    // assigns view to the recycler view item
    // we pass the binding
    // this code  will only execute when recycler view will be initiated first
    inner class ViewHolder(val binding : WeatherRecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = WeatherRecyclerviewItemBinding.inflate( LayoutInflater.from(parent.context), parent,  false )
        return ViewHolder( binding )

    }

    override fun getItemCount(): Int {

        return weatherModelArrayList.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with( holder ){
            with( weatherModelArrayList[position] ){

                // position of item at which we are passing the value
                // we need to assign value to each of the item of the View
                Picasso.get().load("https:".plus(icon)).into(binding.rvConditionIcon)
                binding.rvTemperature.text = context.getString(R.string.temperature_celcius, temperature)
                binding.rvWindSpeed.text = context.getString(R.string.wind_speed, windSpeed)
                val raw_time = SimpleDateFormat( "yyyy-mm-dd hh:mm", Locale.US)
                val structure_time = SimpleDateFormat("hh:mm aa", Locale.US)

                try{

                    val t = raw_time.parse( time )
                    binding.rvTime.text = t?.let {
                        structure_time.format(t).toString()
                    }
                }catch (e: ParseException){
                    e.printStackTrace()
                }
            }
        }
    }


}