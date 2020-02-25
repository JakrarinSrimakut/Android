package com.example.h.kotlinyoutubelbta

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.video_row.view.*
import java.text.FieldPosition

class MainAdapter(val homeFeed: HomeFeed) : RecyclerView.Adapter<CustomViewHolder>() {

    val videoTitles = listOf("First title","Second", "3rd", "MOOOOORE TITLE")

    override fun getItemCount(): Int {
        return homeFeed.videos.count()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        // how do we even create a view
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.video_row, parent, false)
        return CustomViewHolder(cellForRow)
    }
    /*TODO: Using synthetic properties inside (by ref the layout id directly) causing view to be looked up each time onBindViewHolder is called.
        Fix
        1. Look view in ViewHolder and store in variable or
        2. Use Kotlin Android Extension's LayoutContainer interface (Keep it in onBindViewHolder like in video)
     */

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
//        val videoTitle = videoTitles.get(position)
        val video = homeFeed.videos.get(position)
        holder?.view?.textView_video_title?.text = video.name
    }

}

class CustomViewHolder(val view : View) : RecyclerView.ViewHolder(view){

}