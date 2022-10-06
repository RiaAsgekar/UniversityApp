package com.example.myapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapp.data.CourseItem
import io.realm.mongodb.App
import io.realm.mongodb.AppConfiguration
import io.realm.mongodb.User
import io.realm.mongodb.mongo.MongoClient
import io.realm.mongodb.mongo.MongoCollection
import io.realm.mongodb.mongo.MongoDatabase
import kotlinx.android.synthetic.main.fragment_first_courses.*
import org.bson.Document
import org.bson.types.ObjectId

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragmentCourses : Fragment() {
    ////////////////
    var Appid = "mongodbcourse-xofpp"
    lateinit var mongoDatabase: MongoDatabase
    lateinit var mongoClient: MongoClient
    internal lateinit var user: User
    internal lateinit var mongoCollection: MongoCollection<Document>
    var datatemp="1"
    var CoTitle="1"
    var CoDate="1"
    var CoInfo="1"
    var CoEligibility="1"
    var uniqueid="uniqueIIDD"
    /////////////////

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first_courses, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        view.findViewById<Button>(R.id.button_first).setOnClickListener {
        val app = App(AppConfiguration.Builder(Appid).build())
        user = app.currentUser()!!
        mongoClient = user.getMongoClient("mongodb-atlas")
        mongoDatabase = mongoClient.getDatabase("CourseData")
        mongoCollection = mongoDatabase.getCollection("CoursesData")


        val queryFilter = Document("temp", "finalized")//!!!!!!!!!!!!!!!!!!!!!!CHANGEEEEEEEEE
        val findTask = mongoCollection.find(queryFilter).iterator()//earlier var
//
        /////////////////////////

        var courseItems=mutableListOf<CourseItem>(
                //EventItem("temp1",10,"temp01"),
        )
        var itemtemp= CourseItem("Karate Coaching","Commencing 15th June","Learn Self Defense from a highly rated " +
                "state level karate champion","Open to all above 18 years of age with no comorbidities")
        courseItems.add(itemtemp)

        ///////////////////////////////////////








        findTask.getAsync { task ->
            Log.v("task","entered task")

            if(task.isSuccess){
                Log.v("task","issuccess task")
                val results = task.get()
                while(results.hasNext()){
                    val currentDocument:Document = results.next()//earlier var
                    if(currentDocument.getString("Title")!=null){

                        //datatemp = currentDocument.getString("Title")
                        CoTitle=currentDocument.getString("Title")
                        CoDate=currentDocument.getString("Date")
                        CoInfo=currentDocument.getString("Info")
                        CoEligibility=currentDocument.getString("Eligibility")
                        uniqueid= ObjectId().toHexString()
//                 let this be       uniqueid=currentDocument.getString(objectidunique)

                        Log.v("task","issuccess task 001")

                        itemtemp= CourseItem(CoTitle,CoDate,CoInfo,CoEligibility)
                        Log.v("task","issuccess task 002")

                        courseItems.add(itemtemp)
                        Log.v("task", courseItems.toString())
                    }
                }
                CoursesRecyclerView.apply {
                    layoutManager=LinearLayoutManager(activity)
                    adapter=CoursesAdapter(courseItems)//earlier as MutableList<EventItem>




                }
            }else{
                Log.v("Task Error",task.error.toString())

            }
            Log.v("last checkpoint 1st",courseItems.toString())
        }











        //////////////////////////////////////







        ///////////////////////////
//        CoursesRecyclerView.apply{
//            layoutManager=LinearLayoutManager(activity)
//            adapter=CoursesAdapter()
//        }

    }
}