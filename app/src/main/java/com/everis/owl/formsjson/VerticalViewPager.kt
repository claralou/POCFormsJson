package com.everis.owl.formsjson

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View


class VerticalViewPager(context: Context, attrs: AttributeSet?) : ViewPager(context, attrs) {


    var gestureDetector : GestureDetector? = null

    init {
        init()
    }
    override fun canScrollHorizontally(direction: Int): Boolean {
        return true
    }




    /**
     * @return `true` iff a normal view pager would support horizontal scrolling at this time
     */
    override fun canScrollVertically(direction: Int): Boolean {
        //return super.canScrollHorizontally(direction)
        return true
    }

    private fun init() {
        gestureDetector = GestureDetector(context, GestureListener())
        // Make page transit vertical
        setPageTransformer(true, VerticalPageTransformer())
        // Get rid of the overscroll drawing that happens on the left and right (the ripple)
        overScrollMode = View.OVER_SCROLL_NEVER
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {

        return false
        val toIntercept = super.onInterceptTouchEvent(flipXY(ev))

        // Return MotionEvent to normal
        flipXY(ev)

        //return toIntercept
        return false
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        this.gestureDetector!!.onTouchEvent(ev)
        return true //Esto podría ser un problema a la hora de querer detectar un evento touch en un fragment. Probarlo
        //return super.dispatchTouchEvent(ev)
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        return false
        this.gestureDetector!!.onTouchEvent(ev)
        val toHandle = super.onTouchEvent(flipXY(ev))
        // Return MotionEvent to normal
        flipXY(ev)
        //return toHandle


        return false
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

    /*override fun onTouch(v: View, event: MotionEvent): Boolean {
        this.gestureDetector.onTouchEvent(event)
        return true
    }*/




    inner class GestureListener : GestureDetector.SimpleOnGestureListener() {


        private val TAG = GestureListener::class.java.simpleName

        private val SLIDE_THRESHOLD = 100

        override fun onSingleTapUp(e: MotionEvent): Boolean {
            return noSlide(e)
            //return super.onSingleTapUp(e);
        }

        override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
            try {
                val deltaY = e2.y - e1.y
                val deltaX = e2.x - e1.x

                return if (Math.abs(deltaX) > Math.abs(deltaY)) {
                    if (Math.abs(deltaX) > SLIDE_THRESHOLD) {
                        if (deltaX > 0) {
                            onSlideRight()
                        } else {
                            onSlideLeft()
                        }
                    } else {
                        noSlide(e1)
                    }
                } else {
                    if (Math.abs(deltaY) > SLIDE_THRESHOLD) {
                        if (deltaY > 0) {
                            onSlideDown()
                        } else {
                            onSlideUp()
                        }
                    } else {
                        noSlide(e1)
                    }
                }
            } catch (exception: Exception) {
                Log.e(TAG, exception.message)
            }

            return false
        }




        /*@Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            try {
                float deltaY = e2.getY() - e1.getY();
                float deltaX = e2.getX() - e1.getX();

                if (Math.abs(deltaX) > Math.abs(deltaY)) {
                    if (Math.abs(deltaX) > SLIDE_THRESHOLD) {
                        if (deltaX > 0) {
                            return onSlideRight();
                        } else {
                            return onSlideLeft();
                        }
                    }
                } else {
                    if (Math.abs(deltaY) > SLIDE_THRESHOLD) {
                        if (deltaY > 0) {
                            return onSlideDown();
                        } else {
                            return onSlideUp();
                        }
                    }
                }
            } catch (Exception exception) {
                Log.e(TAG, exception.getMessage());
            }

            return false;
        }*/
    }


    fun onSlideRight(): Boolean {
        return false
    }

    fun onSlideLeft(): Boolean {
        return false
    }

    fun onSlideUp(): Boolean {
        return false
    }

    fun onSlideDown(): Boolean {
        return false
    }

    fun noSlide(e: MotionEvent): Boolean {
        return false
    }

}