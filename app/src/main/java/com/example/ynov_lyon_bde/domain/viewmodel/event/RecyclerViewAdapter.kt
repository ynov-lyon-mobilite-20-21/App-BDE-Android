package com.example.ynov_lyon_bde.domain.viewmodel.event

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.ynov_lyon_bde.R
import com.example.ynov_lyon_bde.ui.screens.event.HomeFragmentDirections


class RecyclerViewAdapter(private val data: List<EventObject>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    //Provides all the functionality for our list items
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var id: TextView? = null
        var name: TextView = itemView.findViewById(R.id.eventTitle)
        var type:TextView = itemView.findViewById(R.id.eventType)
        var date: TextView = itemView.findViewById(R.id.dateEvent)
        var description:TextView = itemView.findViewById(R.id.eventDescription)
        var hour: TextView = itemView.findViewById(R.id.eventHour)
        var address:TextView = itemView.findViewById(R.id.eventAddress)

       /* init {
            itemView.setOnClickListener { v: View ->
                val position: Int = adapterPosition
                Toast.makeText(itemView.context,"you clicked on item ${position + 1}",Toast.LENGTH_SHORT).show()
            }
        }*/
    }


    //Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card, parent, false)
        return ViewHolder(view)
    }

    //Return the total count of items in the list
    override fun getItemCount(): Int {
        return data.size
    }

    //Updates list data; Associates ViewHolder data
    override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, index: Int) {
        holder.name.text = data[index].name
        holder.type.text = data[index].type
        holder.address.text = data[index].address
        holder.description.text = data[index].description
        holder.hour.text = data[index].hour
        holder.date.text = data[index].date

        holder.itemView.setOnClickListener { view ->

            val action = HomeFragmentDirections.actionHomeFragmentToCardDescription(data[index])
            view.findNavController().navigate(action)
        }

        //holder.image.setImageResource(data[index].image)
        //holder.imageHour.setImageResource(data[index].imageHour)
    }
}
