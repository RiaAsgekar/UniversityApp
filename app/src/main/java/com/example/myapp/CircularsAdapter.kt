package com.example.myapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.data.CircularItem
import kotlinx.android.synthetic.main.a_single_circular_row.view.*

class CircularsAdapter(val circularItems:MutableList<CircularItem>):RecyclerView.Adapter<CircularsAdapter.ViewHolder>() {
    class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
        fun bind(circularItem: CircularItem){
            //itemView.eventTitle.text=eventItem.name
            itemView.CircularTitleTextView.text=circularItem.TitleCircular
            itemView.CircularDateTV.text=circularItem.DateCircular
            itemView.CircularInfoTV.text=circularItem.InfoCircular
            itemView.CircularDepartmentTV.text=circularItem.DepartmentCircular

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.a_single_circular_row,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val circularItem=circularItems[position]
        holder.bind(circularItem)

        Log.v("ErrorDebug","onBindViewHolder called")

    }

    override fun getItemCount()=circularItems.size
}