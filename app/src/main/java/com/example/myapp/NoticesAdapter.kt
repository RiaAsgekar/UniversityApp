package com.example.myapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.data.NoticeItem
import kotlinx.android.synthetic.main.a_single_notice_row.view.*

class NoticesAdapter(val noticeItems:MutableList<NoticeItem>):RecyclerView.Adapter<NoticesAdapter.ViewHolder>() {
    class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
        fun bind(noticeItem:NoticeItem){
            //itemView.eventTitle.text=eventItem.name
            itemView.NoticeTitleTextView.text=noticeItem.TitleNotice
            itemView.NoticeDateTV.text=noticeItem.DateNotice
            itemView.NoticeInfoTV.text=noticeItem.InfoNotice


        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.a_single_notice_row,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val noticeItem=noticeItems[position]
        holder.bind(noticeItem)

        Log.v("ErrorDebug","onBindViewHolder called")

    }

    override fun getItemCount()=noticeItems.size
}