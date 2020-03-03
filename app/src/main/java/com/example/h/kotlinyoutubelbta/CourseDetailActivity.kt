package com.example.h.kotlinyoutubelbta

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.course_lesson_row.view.*
import okhttp3.*
import java.io.IOException

class CourseDetailActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        //Don't reuse layout create a separate one
        setContentView(R.layout.activity_main)

        recyclerView_main.layoutManager = LinearLayoutManager(this)
        //recyclerView_main.adapter = CourseDetailAdapter()

        // Get intent extra from MainAdapter to populate activity
        // we'll change the nav bar title..
        val navBarTitle = intent.getStringExtra(CustomViewHolder.VIDEO_TITLE_KEY)
        supportActionBar?.title = navBarTitle


//        println(courseDetailUrl)

        fetchJSON()
    }

    private fun fetchJSON(){
        val videoId = intent.getIntExtra(CustomViewHolder.VIDEO_ID_KEY, -1)
        val courseDetailUrl = "https://api.letsbuildthatapp.com/youtube/course_detail?id=$videoId"

        val client = OkHttpClient()
        val request = Request.Builder().url(courseDetailUrl).build()
        client.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {

            }

            override fun onResponse(call: Call, response: Response) {
                val body = response?.body?.string()

                val gson = GsonBuilder().create()

                val courseLessons = gson.fromJson(body, Array<CourseLesson>::class.java)

                runOnUiThread{
                    recyclerView_main.adapter = CourseDetailAdapter(courseLessons)
                }
            }
        })
    }

    private class CourseDetailAdapter(val courseLessons: Array<CourseLesson>): RecyclerView.Adapter<CourseLessonViewHolder>(){
        //how your view look like. Set your row view to be shown.
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseLessonViewHolder {
            val layoutInflater = LayoutInflater.from(parent?.context)
            val customView = layoutInflater.inflate(R.layout.course_lesson_row, parent, false)

            return CourseLessonViewHolder(customView)

        }
        //number of items to render
        override fun getItemCount(): Int {
            return courseLessons.size;
        }
        //manipulate the row views values
        override fun onBindViewHolder(holder: CourseLessonViewHolder, position: Int) {
            val courseLesson = courseLessons.get(position)

            holder?.customView?.textView_course_lesson_title?.text = courseLesson.name
            holder?.customView?.textView_duration?.text = courseLesson.duration

            val imageView = holder?.customView?.imageView_course_lesson_thumbnail
            Picasso.with(holder?.customView?.context).load(courseLesson.imageUrl).into(imageView)

            //set position courseLesson to viewHolder so it can intent courseLesson link
            holder?.courseLesson = courseLesson
        }

    }
    //val can't be manipulated but var can
    class CourseLessonViewHolder(val customView: View, var courseLesson: CourseLesson? = null): RecyclerView.ViewHolder
        (customView){

        companion object {
            val COURSE_LESSON_LINK_KEY = "COURSE_LESSON_LINK"//key for receiving intent to get value coureseLesson.link
        }

        init {
            customView.setOnClickListener{
                val intent = Intent(customView.context, CourseLessonActivity::class.java)

                intent.putExtra(COURSE_LESSON_LINK_KEY, courseLesson?.link)

                customView.context.startActivity(intent)
            }
        }
    }
}

