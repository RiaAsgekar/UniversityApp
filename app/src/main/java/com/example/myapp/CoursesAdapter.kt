package com.example.myapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.data.CourseItem
import kotlinx.android.synthetic.main.a_single_course_row.view.*

class CoursesAdapter(val courseItems:MutableList<CourseItem>):RecyclerView.Adapter<CoursesAdapter.ViewHolder>() {
    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        fun bind(courseItem:CourseItem){
            //itemView.eventTitle.text=eventItem.name
            itemView.CourseTitleTextView.text=courseItem.TitleCourse
            itemView.CourseDateTV.text=courseItem.DateCourse
            itemView.CourseInfoTV.text=courseItem.InfoCourse
            itemView.CourseEligibilityTV.text=courseItem.EligibilityCourse

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.a_single_course_row,parent,false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val courseItem=courseItems[position]
        holder.bind(courseItem)

        Log.v("ErrorDebug","onBindViewHolder called")

    }

    override fun getItemCount()=courseItems.size
}