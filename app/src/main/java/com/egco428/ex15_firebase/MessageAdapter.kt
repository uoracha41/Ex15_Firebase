package com.egco428.ex15_firebase

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class MessageAdapter(val mContext: Context, val layoutResId: Int, val messageList: List<Message>):
    ArrayAdapter<Message>(mContext, layoutResId, messageList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflator: LayoutInflater = LayoutInflater.from(mContext)
        val view: View = layoutInflator.inflate(layoutResId, null)
        val msgTextView = view.findViewById<TextView>(R.id.msgView)
        val ratingTextView = view.findViewById<TextView>(R.id.ratingView)
        val idTextView = view.findViewById<TextView>(R.id.idView)
        val msg = messageList[position]

        msgTextView.text = "Message : "+msg.message
        ratingTextView.text = "Ratitng : "+msg.rating.toString()
        idTextView.text = "ID : "+msg.id
        return view
    }
}
