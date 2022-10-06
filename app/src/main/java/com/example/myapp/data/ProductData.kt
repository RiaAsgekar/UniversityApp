package com.example.myapp.data
//
//import android.util.Log
//import io.realm.mongodb.App
//import io.realm.mongodb.AppConfiguration
//import io.realm.mongodb.User
//import io.realm.mongodb.mongo.MongoClient
//import io.realm.mongodb.mongo.MongoCollection
//import io.realm.mongodb.mongo.MongoDatabase
//import org.bson.Document
//import org.bson.types.ObjectId
//
//class ProductData {
//    var Appid = "mongodbcourse-xofpp"
//    lateinit var mongoDatabase: MongoDatabase
//    lateinit var mongoClient: MongoClient
//    internal lateinit var user: User
//    internal lateinit var mongoCollection: MongoCollection<Document>
//    val app = App(AppConfiguration.Builder(Appid).build())
//
//
//    fun allProducts(): MutableList<EventItem> {
//        user = app.currentUser()!!
//        mongoClient = user.getMongoClient("mongodb-atlas")
//        mongoDatabase = mongoClient.getDatabase("CourseData")
//        mongoCollection = mongoDatabase.getCollection("EventData")//CHANGE THIS AS NEEDED
//        var datatemp="1"
//        var uniqueid="uniqueIIDD"
//
//
//
//
//
//
//        val queryFilter = Document("temp", "TempAdditionalField")
//        val findTask = mongoCollection.find(queryFilter).iterator()
//
//        var eventItems=mutableListOf<EventItem>(
//                EventItem("temp1",10,"temp01","id001"),
//                EventItem("temp2",10,"temp02","id002")
//                )
//
//        var itemtemp=EventItem("temp10new",10,"temp10new","id010new")
//        eventItems.add(itemtemp)
//
//        ////////////////////////////////////////
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
//
//                        Log.v("task","issuccess task 001")
//
//                        itemtemp=EventItem("$datatemp",100, "hellooooo","$uniqueid")
//                        Log.v("task","issuccess task 002")
//
//                        eventItems.add(itemtemp)
//                        Log.v("task", eventItems.toString())
//
//
//
//
//                    }
//                }
//
//                //return@getAsync eventItems
//            }else{
//                Log.v("Task Error",task.error.toString())
//
//            }
//            Log.v("after assigning 01",eventItems.toString())
//        }
//        itemtemp=EventItem("temp11new",10,"temp11new","id011new")
//        eventItems.add(itemtemp)
//        ////////////////////////////////////////
//        Log.v("Final Check",eventItems.toString())
//        return eventItems
//
//    }
//
//}