package com.adityakamble49.loadingviews

import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.support.annotation.ColorInt
import android.support.v4.content.res.ResourcesCompat
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import com.adityakamble49.loadingviews.utils.f
import timber.log.Timber


/**
 * MultiColors - Multiple Colors Loading View
 *
 * @author Aditya Kamble
 * @since 3/8/2018
 */
class MultiColors : View {

    private var width = 0.f
    private var height = 0.f
    private var arcSweepAngleStart = 30
    private var arcSweepAngleEnd = 290
    private var arcStartAngleStart = 0
    private var arcStartAngleEnd = 360
    private var arcStartAngle = arcStartAngleStart.f
    private var arcSweepAngle = arcSweepAngleStart.f

    private lateinit var oval: RectF
    private lateinit var arcPaint: Paint
    private val arcColorDefault = R.color.multicolors_arc_fill
    @ColorInt private var arcColor = ResourcesCompat.getColor(resources, arcColorDefault, null)

    private val PN_ARC_START_ANGLE = "arc_start_angle"
    private val PN_ARC_SWEEP_ANGLE = "arc_sweep_angle"

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

        val arcStartAngleProperty = PropertyValuesHolder.ofInt(PN_ARC_START_ANGLE,
                arcStartAngleStart, arcStartAngleEnd)
        val arcSweepAngleProperty = PropertyValuesHolder.ofInt(PN_ARC_SWEEP_ANGLE,
                arcSweepAngleStart, arcSweepAngleEnd)

        val startAngleAnimator = ValueAnimator()
        startAngleAnimator.interpolator = LinearInterpolator()
        startAngleAnimator.setValues(arcStartAngleProperty)
        startAngleAnimator.repeatMode = ValueAnimator.RESTART
        startAngleAnimator.repeatCount = ValueAnimator.INFINITE
        startAngleAnimator.duration = 1000
        startAngleAnimator.addUpdateListener { animation ->
            arcStartAngle = (animation.getAnimatedValue(PN_ARC_START_ANGLE) as Int).f
            Timber.i("arcStartAngle : $arcStartAngle")
            invalidate()
        }

        val sweepAngleAnimator = ValueAnimator()
        sweepAngleAnimator.interpolator = LinearInterpolator()
        sweepAngleAnimator.setValues(arcSweepAngleProperty)
        sweepAngleAnimator.repeatMode = ValueAnimator.REVERSE
        sweepAngleAnimator.repeatCount = ValueAnimator.INFINITE
        sweepAngleAnimator.duration = 1000
        sweepAngleAnimator.addUpdateListener { animation ->
            arcSweepAngle = (animation.getAnimatedValue(PN_ARC_SWEEP_ANGLE) as Int).f
            Timber.i("arcSweepAngle: $arcSweepAngle")
            invalidate()
        }

        startAngleAnimator.start()
        sweepAngleAnimator.start()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawArc(oval, arcStartAngle, arcSweepAngle, false, arcPaint)
    }
}