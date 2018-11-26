package com.everis.owl.formsjson

import android.content.Context
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View

open class OnSlidingTouchListener(context: Context) : View.OnTouchListener {
    private val gestureDetector: GestureDetector

    init {
        gestureDetector = GestureDetector(context, GestureListener())
    }




    override fun onTouch(v: View, event: MotionEvent): Boolean {
        this.gestureDetector.onTouchEvent(event)
        //return false
        return true
    }


    inner class GestureListener : GestureDetector.SimpleOnGestureListener() {
        private val TAG = GestureListener::class.java.simpleName

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


        override fun onScroll(e1: MotionEvent, e2: MotionEvent, distanceX: Float, distanceY: Float): Boolean {
            try {
                val deltaY = e2.y - e1.y
                val deltaX = e2.x - e1.x

                if (Math.abs(deltaX) > Math.abs(deltaY)) {
                    if (Math.abs(deltaX) > SLIDE_THRESHOLD) {
                        return if (deltaX > 0) {
                            onSlideRight()
                        } else {
                            onSlideLeft()
                        }
                    }
                } else {
                    if (Math.abs(deltaY) > SLIDE_THRESHOLD) {
                        return if (deltaY > 0) {
                            onSlideDown()
                        } else {
                            onSlideUp()
                        }
                    }
                }
            } catch (exception: Exception) {
                Log.e(TAG, exception.message)
            }

            return false
        }


    }

    companion object {

        private val SLIDE_THRESHOLD = 100
    }

    fun onSlideRight(): Boolean {
        return false
    }

    fun onSlideLeft(): Boolean {
        return false
    }

    open fun onSlideUp(): Boolean {
        return false
    }

    open fun onSlideDown(): Boolean {
        return false
    }

    open fun noSlide(e: MotionEvent): Boolean {
        return false
    }
}
