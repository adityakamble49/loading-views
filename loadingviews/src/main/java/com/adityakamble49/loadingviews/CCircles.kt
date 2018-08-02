package com.adityakamble49.loadingviews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.adityakamble49.loadingviews.utils.f
import com.adityakamble49.loadingviews.utils.i
import android.animation.ValueAnimator
import android.R.animator
import android.animation.PropertyValuesHolder
import android.R.attr.radius
import android.support.v4.content.res.ResourcesCompat


/**
 * CCircles - Concentric Circles Loading View
 *
 * @author Aditya Kamble
 * @since 2/8/2018
 */
class CCircles : View {

    private var width = 0.f
    private var height = 0.f

    private var circle1Radius = 0
    private var circle2Radius = 0

    private lateinit var circle1Paint: Paint
    private lateinit var circle2Paint: Paint

    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs,
            defStyleAttr) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int,
                defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        init()
    }

    /**
     * Initialize View
     */
    private fun init() {

        // Initialize Paints
        circle1Paint = Paint(Paint.ANTI_ALIAS_FLAG)
        circle1Paint.color = ResourcesCompat.getColor(resources, R.color.ccircle_circle1_fill, null)
        circle2Paint = Paint(Paint.ANTI_ALIAS_FLAG)
        circle2Paint.color = ResourcesCompat.getColor(resources, R.color.ccircle_circle2_fill, null)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        // Update size of custom views
        width = w.f
        height = h.f
        circle1Radius = (Math.min(width, height) / 2 * 0.8).i
        circle2Radius = (Math.min(width, height) / 2 * 0.4).i

        val circle1PropertyRadius = PropertyValuesHolder.ofInt("circle1Radius", circle1Radius,
                circle2Radius)

        val circle1Animator = ValueAnimator()
        circle1Animator.setValues(circle1PropertyRadius)
        circle1Animator.repeatMode = ValueAnimator.REVERSE
        circle1Animator.repeatCount = ValueAnimator.INFINITE
        circle1Animator.duration = 500
        circle1Animator.addUpdateListener { animation ->
            circle1Radius = animation.getAnimatedValue("circle1Radius") as Int
            invalidate()
        }

        circle1Animator.start()

        val circle2PropertyRadius = PropertyValuesHolder.ofInt("circle2Radius", circle2Radius,
                circle1Radius)

        val circle2Animator = ValueAnimator()
        circle2Animator.setValues(circle2PropertyRadius)
        circle2Animator.repeatMode = ValueAnimator.REVERSE
        circle2Animator.repeatCount = ValueAnimator.INFINITE
        circle2Animator.duration = 500
        circle2Animator.addUpdateListener { animation ->
            circle2Radius = animation.getAnimatedValue("circle2Radius") as Int
            invalidate()
        }

        circle2Animator.start()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Draw Circles
        canvas.drawCircle(width / 2, height / 2, circle1Radius.f, circle1Paint)
        canvas.drawCircle(width / 2, height / 2, circle2Radius.f, circle2Paint)
    }
}