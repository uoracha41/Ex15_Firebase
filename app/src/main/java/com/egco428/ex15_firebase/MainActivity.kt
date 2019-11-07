package com.egco428.ex15_firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var dataReference: DatabaseReference
    lateinit var msgList: MutableList<Message>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        msgList = mutableListOf()
        dataReference = FirebaseDatabase.getInstance().getReference("dataMessage")

        saveBtn.setOnClickListener{
            saveData()
        }

        dataReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {}

            override fun onDataChange(p0: DataSnapshot?) {
                if(p0!!.exists()){
                    msgList.clear()
                    for(msg in p0.children){
                        val message = msg.getValue(Message::class.java)
                        msgList.add(message!!)
                    }
                    val adapter = MessageAdapter(applicationContext,R.layout.messages,msgList)
                    listView.adapter = adapter
                }
            }
        })
    }

    private fun saveData(){
        val msg = editText.text.toString()
        if(msg.isEmpty()){
            editText.error = "Please enter a message"
            return
        }
        val messageId = dataReference.push().key
        val messageData = Message(messageId,msg,ratingBar.rating.toFloat())
        dataReference.child(messageId).setValue(messageData).addOnCompleteListener {
            Toast.makeText(applicationContext,"Message saved succesfully",Toast.LENGTH_SHORT).show()
        }
    }
}
