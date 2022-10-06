package com.example.myapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class HomePageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)


        var IVCourses = findViewById<ImageView>(R.id.courses_logo)
        var IVEvents = findViewById<ImageView>(R.id.events_logo)
        var IVNotices = findViewById<ImageView>(R.id.notices_logo)
        var IVCirculars = findViewById<ImageView>(R.id.circulars_logo)
        var IVInstagram=findViewById<ImageView>(R.id.instagram_logo)
        var IVTwitter=findViewById<ImageView>(R.id.twitter_logo)
        var IVKelkar=findViewById<ImageView>(R.id.kelkar)

        IVEvents.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                startActivity(Intent(this@HomePageActivity, EventsActivity::class.java))
            }
        })
        IVNotices.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                startActivity(Intent(this@HomePageActivity, NoticesActivity::class.java))
            }
        })
        IVCirculars.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                startActivity(Intent(this@HomePageActivity, CircularsActivity::class.java))
            }
        })
        IVCourses.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                startActivity(Intent(this@HomePageActivity, CoursesActivity::class.java))
            }
        })
        IVInstagram.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
//                fun getUrlFromIntent(view: View) {
                    val url = "https://www.instagram.com/vazecollegeautonomous/"
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(url)
                    startActivity(intent)

            }
        })
        IVKelkar.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
//                fun getUrlFromIntent(view: View) {
                val url = "http://vazecollege.net/"
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(url)
                startActivity(intent)

            }
        })
        IVTwitter.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
//                fun getUrlFromIntent(view: View) {
                val url = "https://twitter.com/vaze_v"
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(url)
                startActivity(intent)

            }
        })




    }
}

