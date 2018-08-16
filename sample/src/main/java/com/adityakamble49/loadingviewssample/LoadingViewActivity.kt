package com.adityakamble49.loadingviewssample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class LoadingViewActivity : AppCompatActivity() {

    companion object {
        var LOADING_VIEW_POSITION = "loading_view_position"

        const val POS_CCIRCLES = 0
        const val POS_MULTICOLORS = 1
        const val POS_HEARTBEAT = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val position = intent.getIntExtra(LOADING_VIEW_POSITION, 0)
        setContentView(getLayout(position))
        supportActionBar?.title = getTitle(position)
    }

    private fun getLayout(loadingViewPosition: Int): Int {
        return when (loadingViewPosition) {
            POS_CCIRCLES -> R.layout.activity_ccircles
            POS_MULTICOLORS -> R.layout.activity_multicolors
            POS_HEARTBEAT -> R.layout.activity_heartbeat
            else -> R.layout.activity_ccircles
        }
    }

    private fun getTitle(loadingViewPosition: Int): String {
        return resources.getStringArray(R.array.loading_views)[loadingViewPosition]
    }
}