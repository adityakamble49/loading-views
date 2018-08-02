package com.adityakamble49.loadingviewssample

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.adityakamble49.loadingviewssample.utils.inflate
import kotlinx.android.synthetic.main.simple_list_item.view.*

/**
 * Loading View List Adapter
 *
 * @author Aditya Kamble
 * @since 2/8/2018
 */
class LoadingViewListAdapter : RecyclerView.Adapter<LoadingViewListAdapter.ViewHolder>() {

    var itemList: List<String> = mutableListOf()
    lateinit var onItemClickListener: AdapterView.OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.simple_list_item)
        return ViewHolder(view)
    }

    override fun getItemCount() = itemList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(position,
            itemList[position])

    inner class ViewHolder constructor(itemView: View?) : RecyclerView.ViewHolder(itemView),
            View.OnClickListener {

        override fun onClick(v: View?) {
            onItemClickListener.onItemClick(null, v, adapterPosition, 0)
        }

        fun bind(position: Int, itemTitle: String) {
            with(itemView) {
                item_title.text = itemTitle
            }
            itemView?.setOnClickListener(this)
        }
    }
}