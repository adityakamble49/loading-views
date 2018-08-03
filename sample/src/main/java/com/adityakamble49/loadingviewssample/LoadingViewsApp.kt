package com.adityakamble49.loadingviewssample

import android.app.Application
import timber.log.Timber

/**
 * LoadingViewsApp
 * @author Aditya Kamble
 * @since 3/8/2018
 */
class LoadingViewsApp : Application() {

    override fun onCreate() {
        super.onCreate()

        setupTimber()
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}