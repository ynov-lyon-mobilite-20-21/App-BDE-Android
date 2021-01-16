package com.example.ynov_lyon_bde.domain.viewmodel.event

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ynov_lyon_bde.R
import kotlinx.android.synthetic.main.card.view.*


class RecyclerViewAdapter(private val data: List<EventObject>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {


    //Provides all the functionality for our list items
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var id: TextView? = null
        var nameEvent: TextView = itemView.findViewById(R.id.eventTitle)
        var type:TextView = itemView.findViewById(R.id.eventType)
        //var image: ImageView = itemView.findViewById(R.id.imageEvent)
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
        holder.nameEvent.text = data[index].nameEvent
        holder.type.text = data[index].type
        //holder.image.setImageResource(data[index].image)
        //holder.imageHour.setImageResource(data[index].imageHour)
    }
}
