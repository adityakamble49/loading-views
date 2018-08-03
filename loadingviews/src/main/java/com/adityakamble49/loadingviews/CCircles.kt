package com.adityakamble49.loadingviews

import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.support.annotation.ColorInt
import android.support.v4.content.res.ResourcesCompat
import android.util.AttributeSet
import android.view.View
import com.adityakamble49.loadingviews.utils.f
import com.adityakamble49.loadingviews.utils.i


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

    private val circle1ColorDefault = R.color.ccircle_circle1_fill
    private val circle2ColorDefault = R.color.ccircle_circle2_fill

    @ColorInt private var circle1Color = ResourcesCompat.getColor(resources, circle1ColorDefault,
            null)
    @ColorInt private var circle2Color = ResourcesCompat.getColor(resources, circle2ColorDefault,
            null)

    companion object {
        private const val PN_CIRCLE_1_RADIUS = "circle_1_radius"
        private const val PN_CIRCLE_2_RADIUS = "circle_2_radius"
    }

    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        attrs?.let { collectAttributes(it) }
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs,
            defStyleAttr) {
        attrs?.let { collectAttributes(it) }
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int,
                defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        init()
    }

    /**
     * Collect Custom Attributes for View
     */
    private fun collectAttributes(attrs: AttributeSet) {
        // Collect attributes from in Typed Array
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CCircles, 0, 0)

        // Use Attributes
        circle1Color = typedArray.getColor(R.styleable.CCircles_circle1Color, circle1Color)
        circle2Color = typedArray.getColor(R.styleable.CCircles_circle2Color, circle2Color)

        // Recycle This array
        typedArray.recycle()
    }

    /**
     * Initialize View
     */
    private fun init() {

        // Initialize Paints
        circle1Paint = Paint(Paint.ANTI_ALIAS_FLAG)
        circle1Paint.color = circle1Color
        circle2Paint = Paint(Paint.ANTI_ALIAS_FLAG)
        circle2Paint.color = circle2Color
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        // Update size of custom views
        width = w.f
        height = h.f
        circle1Radius = (Math.min(width, height) / 2 * 0.8).i
        circle2Radius = (Math.min(width, height) / 2 * 0.4).i

        val circle1RadiusProperty = PropertyValuesHolder.ofInt(PN_CIRCLE_1_RADIUS, circle1Radius,
                circle2Radius)
        val circle2RadiusProperty = PropertyValuesHolder.ofInt(PN_CIRCLE_2_RADIUS, circle2Radius,
                circle1Radius)

        val circleAnimator = ValueAnimator()
        circleAnimator.setValues(circle1RadiusProperty, circle2RadiusProperty)
        circleAnimator.repeatMode = ValueAnimator.REVERSE
        circleAnimator.repeatCount = ValueAnimator.INFINITE
        circleAnimator.duration = 500
        circleAnimator.addUpdateListener { animation ->
            circle1Radius = animation.getAnimatedValue(PN_CIRCLE_1_RADIUS) as Int
            circle2Radius = animation.getAnimatedValue(PN_CIRCLE_2_RADIUS) as Int
            invalidate()
        }

        circleAnimator.start()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Draw Circles
        canvas.drawCircle(width / 2, height / 2, circle1Radius.f, circle1Paint)
        canvas.drawCircle(width / 2, height / 2, circle2Radius.f, circle2Paint)
    }
}