package com.adityakamble49.loadingviews

import android.animation.Animator
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
import com.adityakamble49.loadingviews.utils.f


/**
 * HeartBeat - Heart Beat Loading View
 *
 * @author Aditya Kamble
 * @since 16/8/2018
 */
class HeartBeat : View {

    private var width = 0.f
    private var height = 0.f

    private var arcIncrementSweepAngleStart = 0
    private var arcIncrementSweepAngleEnd = 360
    private var arcDecrementSweepAngleStart = -360
    private var arcDecrementSweepAngleEnd = 0
    private var arcStartAngle = 270f

    private var arc1SweepAngle = arcIncrementSweepAngleStart.f
    private var arc2SweepAngle = arcIncrementSweepAngleStart.f
    private var arc3SweepAngle = arcIncrementSweepAngleStart.f

    private lateinit var arc1Paint: Paint
    private lateinit var arc2Paint: Paint
    private lateinit var arc3Paint: Paint

    @ColorInt private var arc1Color = ResourcesCompat.getColor(resources,
            R.color.heartbeat_arc_fill_1, null)
    @ColorInt private var arc2Color = ResourcesCompat.getColor(resources,
            R.color.heartbeat_arc_fill_2, null)
    @ColorInt private var arc3Color = ResourcesCompat.getColor(resources,
            R.color.heartbeat_arc_fill_3, null)

    private lateinit var oval: RectF

    companion object {
        private const val PN_ARC_1_SWEEP_ANGLE = "arc1_sweep_angle"
        private const val PN_ARC_2_SWEEP_ANGLE = "arc2_sweep_angle"
        private const val PN_ARC_3_SWEEP_ANGLE = "arc3_sweep_angle"
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
    }

    /**
     * Initialize View
     */
    private fun init() {

        // Initialize Paints
        arc1Paint = Paint(Paint.ANTI_ALIAS_FLAG)
        arc1Paint.color = arc1Color
        arc1Paint.style = Paint.Style.STROKE
        arc1Paint.strokeWidth = 15f

        arc2Paint = Paint(Paint.ANTI_ALIAS_FLAG)
        arc2Paint.color = arc2Color
        arc2Paint.style = Paint.Style.STROKE
        arc2Paint.strokeWidth = 15f

        arc3Paint = Paint(Paint.ANTI_ALIAS_FLAG)
        arc3Paint.color = arc3Color
        arc3Paint.style = Paint.Style.STROKE
        arc3Paint.strokeWidth = 15f
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        // Update size of custom views
        width = w.f
        height = h.f

        val widthPadding = width * 0.1f
        val heightPadding = height * 0.1f

        oval = RectF()
        oval.set(widthPadding, heightPadding, width - widthPadding, height - heightPadding)


        // ARC 1 Animator
        val arc1SweepAngleIncrease = PropertyValuesHolder.ofInt(PN_ARC_1_SWEEP_ANGLE,
                arcIncrementSweepAngleStart, arcIncrementSweepAngleEnd)

        val arc1IncreaseAnimator = ValueAnimator()
        arc1IncreaseAnimator.setValues(arc1SweepAngleIncrease)
        arc1IncreaseAnimator.duration = 1000
        arc1IncreaseAnimator.addUpdateListener { animation ->
            arc1SweepAngle = (animation.getAnimatedValue(PN_ARC_1_SWEEP_ANGLE) as Int).f
            invalidate()
        }

        val arc1SweepAngleDecrease = PropertyValuesHolder.ofInt(PN_ARC_1_SWEEP_ANGLE,
                arcDecrementSweepAngleStart, arcDecrementSweepAngleEnd)

        val arc1DecreaseAnimator = ValueAnimator()
        arc1DecreaseAnimator.setValues(arc1SweepAngleDecrease)
        arc1DecreaseAnimator.duration = 600
        arc1DecreaseAnimator.addUpdateListener { animation ->
            arc1SweepAngle = (animation.getAnimatedValue(PN_ARC_1_SWEEP_ANGLE) as Int).f
            invalidate()
        }

        // ARC 2 Animator
        val arc2SweepAngleIncrease = PropertyValuesHolder.ofInt(PN_ARC_2_SWEEP_ANGLE,
                arcIncrementSweepAngleStart, arcIncrementSweepAngleEnd)

        val arc2IncreaseAnimator = ValueAnimator()
        arc2IncreaseAnimator.setValues(arc2SweepAngleIncrease)
        arc2IncreaseAnimator.duration = 800
        arc2IncreaseAnimator.addUpdateListener { animation ->
            arc2SweepAngle = (animation.getAnimatedValue(PN_ARC_2_SWEEP_ANGLE) as Int).f
            invalidate()
        }

        val arc2SweepAngleDecrease = PropertyValuesHolder.ofInt(PN_ARC_2_SWEEP_ANGLE,
                arcDecrementSweepAngleStart, arcDecrementSweepAngleEnd)

        val arc2DecreaseAnimator = ValueAnimator()
        arc2DecreaseAnimator.setValues(arc2SweepAngleDecrease)
        arc2DecreaseAnimator.duration = 800
        arc2DecreaseAnimator.addUpdateListener { animation ->
            arc2SweepAngle = (animation.getAnimatedValue(PN_ARC_2_SWEEP_ANGLE) as Int).f
            invalidate()
        }

        // ARC 3 Animator
        val arc3SweepAngleIncrease = PropertyValuesHolder.ofInt(PN_ARC_3_SWEEP_ANGLE,
                arcIncrementSweepAngleStart, arcIncrementSweepAngleEnd)

        val arc3IncreaseAnimator = ValueAnimator()
        arc3IncreaseAnimator.setValues(arc3SweepAngleIncrease)
        arc3IncreaseAnimator.duration = 600
        arc3IncreaseAnimator.addUpdateListener { animation ->
            arc3SweepAngle = (animation.getAnimatedValue(PN_ARC_3_SWEEP_ANGLE) as Int).f
            invalidate()
        }

        val arc3SweepAngleDecrease = PropertyValuesHolder.ofInt(PN_ARC_3_SWEEP_ANGLE,
                arcDecrementSweepAngleStart, arcDecrementSweepAngleEnd)

        val arc3DecreaseAnimator = ValueAnimator()
        arc3DecreaseAnimator.setValues(arc3SweepAngleDecrease)
        arc3DecreaseAnimator.duration = 1000
        arc3DecreaseAnimator.addUpdateListener { animation ->
            arc3SweepAngle = (animation.getAnimatedValue(PN_ARC_3_SWEEP_ANGLE) as Int).f
            invalidate()
        }

        arc3IncreaseAnimator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {}
            override fun onAnimationEnd(animation: Animator?) {
                arc1DecreaseAnimator.startDelay = 300
                arc1DecreaseAnimator.start()
                arc2DecreaseAnimator.startDelay = 300
                arc2DecreaseAnimator.start()
                arc3DecreaseAnimator.startDelay = 300
                arc3DecreaseAnimator.start()
            }

            override fun onAnimationCancel(animation: Animator?) {}
            override fun onAnimationStart(animation: Animator?) {}
        })

        arc1IncreaseAnimator.start()
        arc2IncreaseAnimator.start()
        arc3IncreaseAnimator.start()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Draw Arcs
        canvas.drawArc(oval, arcStartAngle, arc3SweepAngle, false, arc3Paint)
        canvas.drawArc(oval, arcStartAngle, arc2SweepAngle, false, arc2Paint)
        canvas.drawArc(oval, arcStartAngle, arc1SweepAngle, false, arc1Paint)
    }
}