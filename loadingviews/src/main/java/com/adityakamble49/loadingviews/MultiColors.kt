package com.adityakamble49.loadingviews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.support.annotation.ColorInt
import android.support.v4.content.res.ResourcesCompat
import android.util.AttributeSet
import android.view.View
import com.adityakamble49.loadingviews.utils.f


/**
 * MultiColors - Multiple Colors Loading View
 *
 * @author Aditya Kamble
 * @since 3/8/2018
 */
class MultiColors : View {

    private var width = 0.f
    private var height = 0.f

    private lateinit var oval: RectF

    private lateinit var arcPaint: Paint

    private val arcColorDefault = R.color.multicolors_arc_fill

    @ColorInt private var arcColor = ResourcesCompat.getColor(resources, arcColorDefault, null)

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
    }

    /**
     * Initialize View
     */
    private fun init() {

        // Initialize Paints
        arcPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        arcPaint.style = Paint.Style.STROKE
        arcPaint.strokeWidth = 5f
        arcPaint.color = arcColor
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        // Update size of custom views
        width = w.f
        height = h.f

        val widthPadding = width * 0.1f
        val heightPadding = height * 0.1f

        oval = RectF()
        oval.set(widthPadding, heightPadding, width - widthPadding, height - heightPadding)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawArc(oval, 0f, 290f, false, arcPaint)
    }
}