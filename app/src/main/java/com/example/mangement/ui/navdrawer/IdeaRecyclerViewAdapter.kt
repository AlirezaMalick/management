package com.example.mangement.ui.navdrawer

import android.content.Context
import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import com.example.mangement.R
import com.example.mangement.databinding.IdeaMyRecyclerviewItemBinding
import com.example.mangement.utils.clickToActions


class IdeaRecyclerViewAdapter(
    list: ArrayList<IdeaRecyclerViewItemClass>? = null,
    val listeners: InterfaceMyIdea
) : RecyclerView.Adapter<IdeaRecyclerViewAdapter.ViewHolder>() {
     var adapterlist= list
    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val layoutInflator=LayoutInflater.from(parent.context)

        val binding: IdeaMyRecyclerviewItemBinding =DataBindingUtil.inflate(layoutInflator,
            R.layout.idea_my_recyclerview_item,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val selectedItem=adapterlist!![position]
        Log.d("item title",selectedItem.itemtitle())
        holder.bind(selectedItem)
    }

    override fun getItemCount(): Int {
           return adapterlist!!.size
    }


    inner class ViewHolder(val binding: IdeaMyRecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(selectedItem: IdeaRecyclerViewItemClass)
        {
            binding.viewmodel =selectedItem

            binding.itemView.clickToActions {


                listeners.onListClicked(selectedItem)
            }

        }

    }


    interface InterfaceMyIdea{
        fun onListClicked(content: IdeaRecyclerViewItemClass)
    }

}