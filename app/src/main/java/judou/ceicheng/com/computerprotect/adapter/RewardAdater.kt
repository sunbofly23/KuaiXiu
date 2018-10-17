package com.example.sunbo.excel

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import judou.ceicheng.com.computerprotect.R
import judou.ceicheng.com.computerprotect.bean.RewordBean
import kotlinx.android.synthetic.main.item.view.*

/**
 * Created by sunbo on 2018/10/17.
 */
class RewardAdater(val items: MutableList<RewordBean>, var context:Context) : RecyclerView.Adapter<RewardAdater.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
                   Glide.with(context).load(items[position].pic).into(holder.itemView.image)
                   holder.itemView.username.text=items[position].name
        holder.itemView.word.text=items[position].describe
        holder.itemView.phone.text=items[position].phone
        holder.itemView.type.text=items[position].type
        holder.itemView.btn_accept.setOnClickListener {
            Toast.makeText(context,"accept reword",Toast.LENGTH_SHORT).show()
        }
        holder.itemView.btn_refuse.setOnClickListener {
            Toast.makeText(context,"ugly refuse",Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        if(items.isEmpty())
            return 1
        else
            return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView){

    }


}