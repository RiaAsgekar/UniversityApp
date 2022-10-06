package com.example.myapp


import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import io.realm.mongodb.User
import io.realm.mongodb.mongo.MongoClient
import io.realm.mongodb.mongo.MongoCollection
import io.realm.mongodb.mongo.MongoDatabase
import kotlinx.android.synthetic.main.event_info.*
import org.bson.Document



class EventInfo : Fragment() {

   private val args:EventInfoArgs by navArgs()

    var Appid = "mongodbcourse-xofpp"
    lateinit var mongoDatabase: MongoDatabase
    lateinit var mongoClient: MongoClient
    internal lateinit var user: User
    internal lateinit var mongoCollection: MongoCollection<Document>
    var emailto=MainActivity().inputName

    var datatemp="1"
    var uniqueid="uniqueIIDD"
    lateinit var RegButton:Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.event_info, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        RegButton=view.findViewById(R.id.ResisterEvent)

        val sharedPreference =  view.getContext().getSharedPreferences("prefs", Context.MODE_PRIVATE)
        var editor = sharedPreference.edit()

        productTitle.text =sharedPreference.getString("title",null)
        productDate.text = sharedPreference.getString("date",null);
        productDescription.text = sharedPreference.getString("description",null);
        productHostedBy.text = sharedPreference.getString("hostedby",null);
        productDepartment.text = sharedPreference.getString("department",null);


        RegButton.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view: View?) {
                emailto=MainActivity().inputName
                Toast.makeText(activity,"Registered Successfully", Toast.LENGTH_SHORT).show()
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" + emailto.toString()))
                intent.putExtra(Intent.EXTRA_SUBJECT, productTitle.getText().toString())
                intent.putExtra(Intent.EXTRA_TEXT, productDate.getText().toString())
                intent.putExtra(Intent.EXTRA_TEXT, productDescription.getText().toString())
                intent.putExtra(Intent.EXTRA_TEXT, productHostedBy.getText().toString())
                intent.putExtra(Intent.EXTRA_TEXT, productDepartment.getText().toString())
                startActivity(intent)




            }
        })




//        val productCode02=args.eventidArgument//argument should be the unique code id
// //       var objid= ObjectId("productCode02")
//
//
//        ////////////////
//        val app = App(AppConfiguration.Builder(Appid).build())
//        user = app.currentUser()!!
//        mongoClient = user.getMongoClient("mongodb-atlas")
//        mongoDatabase = mongoClient.getDatabase("CourseData")
//        mongoCollection = mongoDatabase.getCollection("EventData")
//        val queryFilter = Document("temp", "TempAdditionalField")
//        val findTask = mongoCollection.find(queryFilter).iterator()//earlier var
//        ////////////////////
//
//        var eventItems=mutableListOf<EventItem>(
//                //EventItem("temp1",10,"temp01"),
//
//
//        )
//        var itemtemp=EventItem("temp10in1stfragment", "10", "temp10", "id010frag","dept","2ndtempUID")
//        eventItems.add(itemtemp)
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//        findTask.getAsync { task ->
//            Log.v("task","entered task")
//
//            if(task.isSuccess){
//                Log.v("task","issuccess task")
//                val results = task.get()
//                while(results.hasNext()){
//                    val currentDocument:Document = results.next()//earlier var
//                    if(currentDocument.getString("data")!=null){
//
//                        datatemp = currentDocument.getString("data")
//                        uniqueid= ObjectId().toHexString()
////                 let this be       uniqueid=currentDocument.getString(objectidunique)
//
//                        Log.v("task","issuccess task 001")
//
//                        var itemtemp= EventItem(datatemp,"100", "hellooooo","ggg","xy","3rdtempUID")
//                        Log.v("task","issuccess task 002")
//
//                        eventItems.add(itemtemp)
//                        Log.v("task", eventItems.toString())
//                    }
//                }
//            }else{
//                Log.v("Task Error",task.error.toString())
//
//            }
//            Log.v("last checkpoint 1st",eventItems.toString())
//            }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
////
////
////        val product=eventItems.find {it.uniqueID==productCode02}
//////        val product=ProductData().allProducts().find {it.uniqueID==productCode02}
////
////
//////        val queryFilter = Document("_id", objid) //remving quotes - run//its a little slow. okay, VERY slow
//////
//////        val searchdoc=mongoCollection.find(queryFilter).iterator()
////
////
////
////        if(product!=null){
////
////            productTitle.text=product.name
////            WelcomeTextView.text=productCode02
////        }
//////        if(searchdoc!=null){
//////            productTitle.text=queryFilter.getString("data")//Adnan. We will have to put it into ProductData.
//////
//////        }
//

    }

}


