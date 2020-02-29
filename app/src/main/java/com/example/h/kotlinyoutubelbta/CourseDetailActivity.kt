package com.example.h.kotlinyoutubelbta

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class CourseDetailActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        //Don't reuse layout create a separate one
        setContentView(R.layout.activity_main)

        recyclerView_main.layoutManager = LinearLayoutManager(this)
        recyclerView_main.adapter = CourseDetailAdapter()

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
            }

        })
    }

    private class CourseDetailAdapter: RecyclerView.Adapter<CourseLessonViewHolder>(){
        //how your view look like
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseLessonViewHolder {
            val layoutInflater = LayoutInflater.from(parent?.context)
            val customView = layoutInflater.inflate(R.layout.course_lesson_row, parent, false)

//            val blueView = View(parent?.context)
//            blueView.setBackgroundColor(Color.BLUE)
//            blueView.minimumHeight = 50
            return CourseLessonViewHolder(customView)

        }
        //items to render
        override fun getItemCount(): Int {
            return 5;
        }

        override fun onBindViewHolder(holder: CourseLessonViewHolder, position: Int) {

        }

    }

    private class CourseLessonViewHolder(val customView: View): RecyclerView.ViewHolder
        (customView){

    }
}

