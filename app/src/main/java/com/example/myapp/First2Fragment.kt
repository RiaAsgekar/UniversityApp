package com.example.myapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapp.data.NoticeItem
import io.realm.mongodb.App
import io.realm.mongodb.AppConfiguration
import io.realm.mongodb.User
import io.realm.mongodb.mongo.MongoClient
import io.realm.mongodb.mongo.MongoCollection
import io.realm.mongodb.mongo.MongoDatabase
import kotlinx.android.synthetic.main.fragment_first2.*
import org.bson.Document
import org.bson.types.ObjectId

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class First2Fragment : Fragment() {
    ////////////////
    var Appid = "mongodbcourse-xofpp"
    lateinit var mongoDatabase: MongoDatabase
    lateinit var mongoClient: MongoClient
    internal lateinit var user: User
    internal lateinit var mongoCollection: MongoCollection<Document>
    var datatemp="1"
    var NTitle=""
    var NDate=""
    var NInfo=""
    var uniqueid="uniqueIIDD"
    /////////////////

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        view.findViewById<Button>(R.id.button_first).setOnClickListener {
        val app = App(AppConfiguration.Builder(Appid).build())
        user = app.currentUser()!!
        mongoClient = user.getMongoClient("mongodb-atlas")
        mongoDatabase = mongoClient.getDatabase("CourseData")
        mongoCollection = mongoDatabase.getCollection("NoticesData")


        val queryFilter = Document("temp", "finalized")//!!!!!!!!!!!!!!!!!!!!!
        val findTask = mongoCollection.find(queryFilter).iterator()//earlier var
//
        /////////////////
        var noticeItems=mutableListOf<NoticeItem>(
                //EventItem("temp1",10,"temp01"),
        )
        var itemtemp= NoticeItem("Covid Update","01st March,2020","Please note the college" +
                "will be closed till further notice on account of Coronavirus. We are still available via email to guide you")
        noticeItems.add(itemtemp)



        findTask.getAsync { task ->
            Log.v("task","entered task")

            if(task.isSuccess){
                Log.v("task","issuccess task")
                val results = task.get()
                while(results.hasNext()){
                    val currentDocument:Document = results.next()//earlier var
                    if(currentDocument.getString("Title")!=null){

                        datatemp = currentDocument.getString("Title")
                        NTitle=currentDocument.getString("Title")
                        NDate=currentDocument.getString("Date")
                        NInfo=currentDocument.getString("Info")
                        uniqueid= ObjectId().toHexString()
//                 let this be       uniqueid=currentDocument.getString(objectidunique)

                        Log.v("task","issuccess task 001")

                        itemtemp= NoticeItem(NTitle,NDate,NInfo)
                        Log.v("task","issuccess task 002")

                        noticeItems.add(itemtemp)
                        Log.v("task", noticeItems.toString())
                    }
                }
                NoticesRecyclerView.apply {
                    layoutManager=LinearLayoutManager(activity)
                    adapter=NoticesAdapter(noticeItems)//earlier as MutableList<EventItem>




                }
            }else{
                Log.v("Task Error",task.error.toString())

            }
            Log.v("last checkpoint 1st",noticeItems.toString())
        }











        ///////////////////////

//
//        NoticesRecyclerView.apply {
//            layoutManager=LinearLayoutManager(activity)
//            adapter=NoticesAdapter()
//        }
    }
}