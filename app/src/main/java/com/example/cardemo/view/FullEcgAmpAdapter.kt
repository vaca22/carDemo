package com.example.cardemo.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.cardemo.R

class FullEcgAmpAdapter(var context: Context) :
    RecyclerView.Adapter<FullEcgAmpAdapter.ViewHolder>() {
    interface WantInfo {
        fun go(u: Int)
    }

    var myGo: WantInfo? = null


    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    val mem = ArrayList<EcgAmpItem>()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.item_ecg_amp_button, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (mem[position].select) {
            holder.text.background = ContextCompat.getDrawable(context, R.drawable.amp_button_bg)
            holder.text.setTextColor(ContextCompat.getColor(context, R.color.white))
        } else {
            holder.text.background = null
            holder.text.setTextColor(ContextCompat.getColor(context, R.color.black))
        }

        holder.text.text=mem[position].name

    }


    fun setSelect(j: Int) {
        for (k in mem.indices) {
            mem[k].select = k == j
        }
        notifyDataSetChanged()
    }

    // total number of cells
    override fun getItemCount(): Int {
        return mem.size
    }


    fun addAll(userBean: Array<String>) {
        mem.clear()
        for (k in userBean.indices) {
            mem.add(EcgAmpItem(userBean[k], false))
        }
    }

    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val text = itemView.findViewById<TextView>(R.id.name)

        override fun onClick(view: View) {
            myGo?.go(layoutPosition)
            for (k in 0 until mem.size) {
                mem[k].select = false
            }
            mem[layoutPosition].select = true
            notifyDataSetChanged()
        }

        init {
            itemView.setOnClickListener(this)
        }
    }


}