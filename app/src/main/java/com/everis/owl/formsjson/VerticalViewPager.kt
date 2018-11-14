package com.everis.owl.formsjson

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View


class VerticalViewPager(context: Context, attrs: AttributeSet?) : ViewPager(context, attrs) {


    init {
        init()
    }
    override fun canScrollHorizontally(direction: Int): Boolean {
        return false
    }

    /**
     * @return `true` iff a normal view pager would support horizontal scrolling at this time
     */
    override fun canScrollVertically(direction: Int): Boolean {
        return super.canScrollHorizontally(direction)
    }

    private fun init() {
        // Make page transit vertical
        setPageTransformer(true, VerticalPageTransformer())
        // Get rid of the overscroll drawing that happens on the left and right (the ripple)
        overScrollMode = View.OVER_SCROLL_NEVER
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        val toIntercept = super.onInterceptTouchEvent(flipXY(ev))
        // Return MotionEvent to normal
        flipXY(ev)
        return toIntercept
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        val toHandle = super.onTouchEvent(flipXY(ev))
        // Return MotionEvent to normal
        flipXY(ev)
        return toHandle
    }

    private fun flipXY(ev: MotionEvent): MotionEvent {
        val width = width.toFloat()
        val height = height.toFloat()
        val x = ev.y / height * width
        val y = ev.x / width * height
        ev.setLocation(x, y)
        return ev
    }

    private class VerticalPageTransformer : ViewPager.PageTransformer {
        override  fun transformPage(view: View, position: Float) {
            val pageWidth = view.getWidth()
            val pageHeight = view.getHeight()
            if (position < -1) {
                // This page is way off-screen to the left.
                view.setAlpha(0f)
            } else if (position <= 1) {
                view.setAlpha(1f)
                // Counteract the default slide transition
                view.setTranslationX(pageWidth * -position)
                // set Y position to swipe in from top
                val yPosition = position * pageHeight
                view.setTranslationY(yPosition)
            } else {
                // This page is way off-screen to the right.
                view.setAlpha(0f)
            }
        }
    }

}