package com.example.myapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.realm.Realm
import io.realm.mongodb.App
import io.realm.mongodb.AppConfiguration
import io.realm.mongodb.Credentials
import io.realm.mongodb.User
import kotlin.properties.Delegates
import kotlinx.android.synthetic.main.activity_main.*



import android.widget.*

import io.realm.mongodb.mongo.MongoClient
import io.realm.mongodb.mongo.MongoCollection
import io.realm.mongodb.mongo.MongoDatabase
import org.bson.Document



class MainActivity : AppCompatActivity() {

    val Appid="mongodbcourse-xofpp"
    var isValid by Delegates.notNull<Boolean>()



    internal lateinit var eLogin:Button
    var eAttemptsInfo="Temp4"
    var inputName=""
    var inputPassword=""
    lateinit var eRegister:TextView

    var counter=5
    var user:User?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Realm.init(this)
        val app:App = App(AppConfiguration.Builder(Appid).build())


        eLogin=findViewById<Button>(R.id.btnLogin)
        eAttemptsInfo= (findViewById<TextView>(R.id.tvAttemptsInfo)).toString()
        eRegister= findViewById<TextView>(R.id.tvRegister)


        eRegister.setOnClickListener(object:View.OnClickListener{
            override fun onClick(v:View){
                startActivity(Intent(this@MainActivity, RegistrationActivity::class.java))

            }

        })








        eLogin.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {

                inputName=(findViewById<EditText>(R.id.etName)).text.toString()
                //inputName accepts email from MainActivity EditText
                inputPassword=(findViewById<EditText>(R.id.etPassword)).text.toString()
                //inputPassword=accepts Password from MainActivity EditText
                eAttemptsInfo=(findViewById<TextView>(R.id.tvAttemptsInfo)).text.toString()

                //////////////////////////
                var credentials = Credentials.emailPassword("tempuser0@gmail.com", "tempuser1")//should be inputName & inputPassword below


                if (inputName.isEmpty() || inputPassword.isEmpty()) {
                    Toast.makeText(this@MainActivity, "Please Enter Details", Toast.LENGTH_SHORT).show()

                }else{
                    credentials=Credentials.emailPassword(inputName,inputPassword)


                    //////////////////////////////////









 //                   val emailPasswordCredentials: Credentials = Credentials.emailPassword(
   //                         "<email>",
     //                       "<password>"
       //             )




                    /////////////


                    app.loginAsync(credentials) { result ->


                            if (result.isSuccess) {
                                Toast.makeText(this@MainActivity, "Login Successful", Toast.LENGTH_SHORT).show()


                                startActivity(Intent(this@MainActivity, HomePageActivity::class.java))
                                //Intent(this@MainActivity,HomePageActivity)
                                Log.v("user", "Eeett Logges in!")
                            } else {
                                counter--
                                Toast.makeText(this@MainActivity, "Incorrect Credentials Entered", Toast.LENGTH_SHORT).show()
                                val sb = StringBuilder()
                                val a = "Attempts Remaining:"
                                sb.append(a).append(counter)
                                Toast.makeText(this@MainActivity, sb, Toast.LENGTH_SHORT).show()

                                eAttemptsInfo = sb.toString()
                                if (counter == 0) {
                                    eLogin.isEnabled = false
                                }
                                Log.v("user", "failleddd")
                                Log.e("AUTH", result.error.toString())
                            }

                    }
                    ///////////////////////////

                }
            }
        })
    }
 /***   private fun validate(uname:String, upassword:String):Boolean {
        if (RegistrationActivity.credential!=null) {

            if(uname==(RegistrationActivity.credential.username) && upassword==(RegistrationActivity.credential.password)) {
                return true
            }
        }
            return false
 }*/


    /***
    private fun validate(uname:String, upassword:String):Boolean {
    if (RegistrationActivity.credential!=null) {

    if(uname==(RegistrationActivity.credential.username) && upassword==(RegistrationActivity.credential.password)) {
    return true
    }
    }
    return false
    }*/


    /***
     *
     *
     *     <fragment
    android:id="@+id/nav_host_fragment"
    android:name="androidx.navigation.fragment.NavHostFragment"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:defaultNavHost="true"
    app:layout_constraintBottom_toBottomOf="parent"
    app:layout_constraintLeft_toLeftOf="parent"
    app:layout_constraintRight_toRightOf="parent"
    app:layout_constraintTop_toTopOf="parent"
    app:navGraph="@navigation/nav_graph" />

     *
     *
     * */


}

