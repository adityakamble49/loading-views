package com.adityakamble49.loadingviewssample

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.AdapterView
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Main Activity
 *
 * @author Aditya Kamble
 * @since 2/8/2018
 */
class MainActivity : AppCompatActivity(), AdapterView.OnItemClickListener {

    private lateinit var loadingViewListAdapter: LoadingViewListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindView()
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        startActivity(Intent(this, LoadingViewActivity::class.java))
    }

    private fun bindView() {

        // Setup RecyclerView for Loading Views List
        loadingViewListAdapter = LoadingViewListAdapter()
        loadingViewListAdapter.itemList = resources.getStringArray(R.array.loading_views).asList()
        loadingViewListAdapter.onItemClickListener = this
        val linearLayoutManager = LinearLayoutManager(this)
        val decorator = DividerItemDecoration(this, linearLayoutManager.orientation)
        rv_loading_views_list.adapter = loadingViewListAdapter
        rv_loading_views_list.layoutManager = linearLayoutManager
        rv_loading_views_list.addItemDecoration(decorator)
    }
}
