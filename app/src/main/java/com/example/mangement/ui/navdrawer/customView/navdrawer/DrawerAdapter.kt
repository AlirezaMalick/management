package com.target.app.estidama.customviews.navdrawer

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mangement.R
import com.example.mangement.ui.navdrawer.customView.navdrawer.DrawerAdapterViewModel
import com.example.mangement.databinding.NavDrawerRowBinding


class DrawerAdapter(private val context: Context,
                    private val data: List<DrawerItem>,
                    private val listener: OnDrawerItemClickListener
) :
    RecyclerView.Adapter<DrawerAdapter.DrawerViewHolder>() {

    companion object {
        private const val TYPE_PROFILE = 101
        private const val TYPE_ITEM = 102
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrawerViewHolder {
        val binding = DataBindingUtil.inflate<NavDrawerRowBinding>(LayoutInflater.from(parent.context), R.layout.nav_drawer_row, parent, false)
        return DrawerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DrawerViewHolder, position: Int) = holder.bind(data[position])

    override fun getItemCount(): Int {
        return data.size
    }

    inner class DrawerViewHolder(private val binding: NavDrawerRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(drawerItem: DrawerItem) {
            binding.viewModel = DrawerAdapterViewModel(drawerItem,listener)
        }
    }

    interface OnDrawerItemClickListener{
        fun onDrawerItemClick(drawerItem: DrawerItem)
    }
}