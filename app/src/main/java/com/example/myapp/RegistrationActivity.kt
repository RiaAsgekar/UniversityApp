package com.example.myapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.realm.mongodb.App
import io.realm.mongodb.AppConfiguration

class RegistrationActivity : AppCompatActivity() {


    var Appid="mongodbcourse-xofpp"
    //var isValidate by Delegates.notNull<Boolean>()
   // var eRegName=""
   // var eRegPassword=""
    lateinit var eRegister:Button
    var regUserName=""
    var regUserPassword=""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)



        var app = App(AppConfiguration.Builder(Appid).build())
        //eRegName= findViewById<EditText>(R.id.etRegName).toString()
        //eRegPassword= (findViewById<EditText>(R.id.etRegPassword)).toString()
        eRegister=findViewById<Button>(R.id.btnRegister)

        eRegister.setOnClickListener(object: View.OnClickListener{
            override fun onClick(view: View?) {
                regUserName=(findViewById<EditText>(R.id.etRegName)).text.toString()
                regUserPassword=(findViewById<EditText>(R.id.etRegPassword)).text.toString()
                /*** etName accepts the email and etRegPassword accepts the password from the RegistrationActivity EditTexts
                 *
                 */

                if(validated(regUserName,regUserPassword)){
                    if(isValidEmail(regUserName)){
                        app.emailPassword.registerUserAsync(regUserName, regUserPassword) { result ->
                            if (result.isSuccess) {
                                Toast.makeText(this@RegistrationActivity, "Registered Successfully!", Toast.LENGTH_SHORT).show()
                                startActivity(Intent(this@RegistrationActivity, MainActivity::class.java))
                                Log.v("user", "Registered")
                            } else {
                                Log.v("user", result.error.toString())
                            }
                        }
                    }
                }
            }
        })

    }
    fun validated(name:String, password:String):Boolean {
        /* Check if the name is empty and password field is 8 characters long */

        if (name.isEmpty() || (password.length < 8))
        {

            Toast.makeText(this, "Please enter your name and ensure password is more than 8 characters long!", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    fun isValidEmail(email:String):Boolean {
        val pattern = Patterns.EMAIL_ADDRESS

        if(!pattern.matcher(email).matches()){
            Toast.makeText(this, "Please Enter Valid Email",Toast.LENGTH_SHORT).show()
            return false
        }
        return true
        }



}