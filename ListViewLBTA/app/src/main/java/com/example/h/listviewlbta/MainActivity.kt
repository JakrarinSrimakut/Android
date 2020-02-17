package com.example.h.listviewlbta

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import kotlinx.android.synthetic.main.row_main.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView =  findViewById<ListView>(R.id.main_listview)
//        val redColor = Color.parseColor("#FF0000")
//        listView.setBackgroundColor(redColor)

        listView.adapter = MyCustomAdapter() // this needs to be my custom adapter telling my list what to render
    }

    //Adapter to show listview
    private class MyCustomAdapter(): BaseAdapter() {

//        private val mContext: Context

        private val names = arrayListOf<String>(
            "Donald Trump", "Steve Jobs", "Tim Cook", "Mark Zuckerberg", "Barack Obama",
            "Donald Trump", "Steve Jobs", "Tim Cook", "Mark Zuckerberg", "Barack Obama",
            "Donald Trump", "Steve Jobs", "Tim Cook", "Mark Zuckerberg", "Barack Obama",
            "Donald Trump", "Steve Jobs", "Tim Cook", "Mark Zuckerberg", "Barack Obama",
            "Donald Trump", "Steve Jobs", "Tim Cook", "Mark Zuckerberg", "Barack Obama"
        )

//        init {
//            mContext = context
//        }
        //Responsible for how many rows in my list
        override fun getCount(): Int {
            return names.size
        }

        //You can igonore this for now
        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        //You can igonore this for now
        override fun getItem(position: Int): Any {
            return "TEST STRING"
        }

        //Responsible for rendering out each row
        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {

            val rowMain: View

            // checking if convertView is null, meaning we have to inflate a new row
            if(convertView == null){
                val layoutInflater = LayoutInflater.from(viewGroup!!.context)//layout inflater expensive
                rowMain = layoutInflater.inflate(R.layout.row_main, viewGroup, false)

                Log.v("getView", "calling findViewById which is expensive")
//                val nTextView = rowMain.name_textview //kotlin allows calling view id w/o using findView also possible cache
//                val pTextView = rowMain.position_textview
//                val nameTextView = rowMain.findViewById<TextView>(R.id.name_textview)
//                val positionTextView = rowMain.findViewById<TextView>(R.id.position_textview)
                val viewHolder = ViewHolder(rowMain.name_textview, rowMain.position_textview)
                rowMain.tag = viewHolder

            }else{
                // well, we have our row as convertView, so just set rowMain as that view
                rowMain = convertView
            }


            val viewHolder = rowMain.tag as ViewHolder
            viewHolder.nameTextView.text = names.get(position)
            viewHolder.positionTextView.text = "Row number: $position"
            return rowMain
        }

        private class ViewHolder(val nameTextView: TextView, val positionTextView: TextView){

        }

    }
}
