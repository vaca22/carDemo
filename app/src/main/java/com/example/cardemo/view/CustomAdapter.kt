package com.example.cardemo.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cardemo.CarData
import com.example.cardemo.R


class CustomAdapter() :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    val mCarDataList= arrayListOf<CarData>()


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        val valueView:TextView
        init {
            textView = view.findViewById(R.id.name)
            valueView = view.findViewById(R.id.value)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.text_row_item, viewGroup, false)

        return ViewHolder(view)
    }


    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.textView.text = mCarDataList[position].name

        val value = mCarDataList[position].value
        if(value is Float){
            viewHolder.valueView.text = value.toString()
        }else if(value is Int){
            viewHolder.valueView.text = value.toString()
        }else if(value is Boolean){
            viewHolder.valueView.text = value.toString()
        }else if(value is String){
            viewHolder.valueView.text = value
        }else if(value==null){
            viewHolder.valueView.text = "null"
        }
    }


    fun setProperty(id:Int,value:Any){
        //with index search id, notify index changed
        val index = mCarDataList.indexOfFirst { it.id==id }
        if(index!=-1){
            mCarDataList[index].value = value
            notifyItemChanged(index)
        }
    }

    override fun getItemCount() = mCarDataList.size

}
