package com.example.myapp




//import android.os.Bundle
//import android.view.View
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapp.data.EventItem
import io.realm.mongodb.App
import io.realm.mongodb.AppConfiguration
import io.realm.mongodb.User
import io.realm.mongodb.mongo.MongoClient
import io.realm.mongodb.mongo.MongoCollection
import io.realm.mongodb.mongo.MongoDatabase
import kotlinx.android.synthetic.main.fragment_first.*
import org.bson.Document
import org.bson.types.ObjectId


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

/////////////////////////////////////01ST MARCH
 

    var Appid = "mongodbcourse-xofpp"
    lateinit var mongoDatabase: MongoDatabase
    lateinit var mongoClient: MongoClient
    internal lateinit var user: User
    internal lateinit var mongoCollection: MongoCollection<Document>


    var datatemp="1"
    var ETitle="1"
    var EDate="1"
    var EDescription="1"
    var EHostedBy="1"
    var EDepartment="1"
    var uniqueid="uniqueIIDD"

    /////////////////////////////////////01ST MARCH



    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        ////////////
        val app = App(AppConfiguration.Builder(Appid).build())//earlier var


        ////////////////////////// 01ST MARCHHHHHHHHHHHHHHHHHHHHHHHHHHH

        user = app.currentUser()!!
        mongoClient = user.getMongoClient("mongodb-atlas")
        mongoDatabase = mongoClient.getDatabase("CourseData")
        mongoCollection = mongoDatabase.getCollection("EventData")


        val queryFilter = Document("temp", "finalized")
        val findTask = mongoCollection.find(queryFilter).iterator()//earlier var




        //val eventItems= ProductData().allProducts()






        ////////////////////////// 01ST MARCHHHHHHHHHHHHHHHHHHHHHHHHHHH

        var eventItems=mutableListOf<EventItem>(
            //EventItem("temp1",10,"temp01"),


        )
        var itemtemp=EventItem("Halloween Party!", "30th November,2020", "Get ready for a" +
                " night of Ghosts     and Ghouls" ,"Kelkar College Cultural Commitee", "Arts Department","temporaryunidueID")
        eventItems.add(itemtemp)


        findTask.getAsync { task ->
            Log.v("task","entered task")

            if(task.isSuccess){
                Log.v("task","issuccess task")
                val results = task.get()
                while(results.hasNext()){
                    val currentDocument:Document = results.next()//earlier var
                    if(currentDocument.getString("title")!=null){

//                        datatemp = currentDocument.getString("data")
                        ETitle=currentDocument.getString("title")
                        EDate=currentDocument.getString("date")
                        EDescription=currentDocument.getString("description")
                        EHostedBy=currentDocument.getString("hostedby")
                        EDepartment=currentDocument.getString("department")
                        uniqueid= ObjectId().toHexString()
                        //uniqueid=currentDocument.getString(objectidunique)

                        Log.v("task","issuccess task 001")

                        itemtemp=EventItem(ETitle,EDate,EDescription,EHostedBy,EDepartment,uniqueid)
                        Log.v("task","issuccess task 002")

                        eventItems.add(itemtemp)
                        Log.v("task", eventItems.toString())
                    }
                }
                eventsRecyclerView.apply {
                    layoutManager=LinearLayoutManager(activity)
                    adapter=EventsAdapter(eventItems)//earlier as MutableList<EventItem>




                }
            }else{
                Log.v("Task Error",task.error.toString())

            }
            Log.v("last checkpoint 1st",eventItems.toString())
        }



        ////////////////////////


    }
}