package com.example.myapp

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.data.EventItem
import kotlinx.android.synthetic.main.a_single_event_row.view.*

class EventsAdapter(val eventItems: MutableList<EventItem>): RecyclerView.Adapter<EventsAdapter.ViewHolder>(){


    class ViewHolder(private val view: View):RecyclerView.ViewHolder(view) {

        fun bind(eventItem: EventItem) {

            ///////////////////////////////
            view.mainPhoto.setOnClickListener{
                val action=FirstFragmentDirections.actionFirstFragmentToEventInfo(eventItem.uniqueid)
                Log.v("ClickedEvent",eventItem.toString());

                val sharedPreference =  view.getContext().getSharedPreferences("prefs", Context.MODE_PRIVATE)
                var editor = sharedPreference.edit()
                editor.putString("title",eventItem.ETitle)
                editor.putString("date",eventItem.EDate.toString())
                editor.putString("description",eventItem.EDescription)
                editor.putString("hostedby",eventItem.EHostedBy)
                editor.putString("department",eventItem.EDepartment)
                //editor.putString("ID",eventItem.uniqueID)
                editor.commit()

                view.findNavController().navigate(action)
            }




    ////////////////////////////


            itemView.eventTitle.text=eventItem.ETitle
            itemView.EventDateTV.text=eventItem.EDate
            itemView.EventDescriptionTV.text=eventItem.EDescription
            itemView.EventHBTV.text=eventItem.EHostedBy
            itemView.EventDepartmentTV.text=eventItem.EDepartment

        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view=LayoutInflater.from(parent.context).inflate(R.layout.a_single_event_row,parent,false)
        return ViewHolder(view)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val eventItem=eventItems[position]
        holder.bind(eventItem)

        Log.v("ErrorDebug","onBindViewHolder called")
    }


    override fun getItemCount()= eventItems.size


}