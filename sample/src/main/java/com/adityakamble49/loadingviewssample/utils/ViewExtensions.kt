package com.adityakamble49.loadingviewssample.utils

import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * View Extensions
 *
 * @author Aditya Kamble
 * @since 2/8/2018
 */


fun ViewGroup.inflate(@LayoutRes layoutRes: Int): View? = LayoutInflater.from(context).inflate(
        layoutRes,
        this, false)