package com.adityakamble49.loadingviewssample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class LoadingViewActivity : AppCompatActivity() {

    companion object {
        var LOADING_VIEW_POSITION = "loading_view_position"

        const val POS_CCIRCLES = 0
        const val POS_MULTICOLORS = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout(intent.getIntExtra(LOADING_VIEW_POSITION, 0)))
    }

    private fun getLayout(loadingViewPosition: Int): Int {
        return when (loadingViewPosition) {
            POS_CCIRCLES -> R.layout.activity_ccircles
            POS_MULTICOLORS -> R.layout.activity_multicolors
            else -> R.layout.activity_ccircles
        }
    }
}