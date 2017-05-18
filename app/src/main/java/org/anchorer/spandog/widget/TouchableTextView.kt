package org.anchorer.spandog.widget

import android.content.Context
import android.support.v7.widget.AppCompatTextView
import android.text.Selection
import android.text.Spannable
import android.text.method.LinkMovementMethod
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.TextView

/**
 * 自定义TextView，支持在其中添加TouchableSpan，使得点击时改变TouchableSpan的背景颜色
 * Created by Anchorer on 2017/5/18.
 */
class TouchableTextView : AppCompatTextView {

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        movementMethod = LinkTouchMovementMethod()
    }

    private class LinkTouchMovementMethod : LinkMovementMethod() {
        private var mPressedSpan : TouchableSpan? = null

        override fun onTouchEvent(textView: TextView, spannable: Spannable, event: MotionEvent): Boolean {
            var action = event.action
            if (action == MotionEvent.ACTION_DOWN) {
                mPressedSpan = getPressedSpan(textView, spannable, event)
                if (mPressedSpan != null) {
                    mPressedSpan!!.isPressed = true
                    Selection.setSelection(spannable, spannable.getSpanStart(mPressedSpan), spannable.getSpanEnd(mPressedSpan))
                }
            } else if (action == MotionEvent.ACTION_MOVE) {
                var touchedSpan : TouchableSpan? = getPressedSpan(textView, spannable, event)
                if (mPressedSpan != null && touchedSpan != mPressedSpan) {
                    mPressedSpan?.isPressed = false
                    mPressedSpan = null
                    Selection.removeSelection(spannable)
                }
            } else {
                if (mPressedSpan != null) {
                    mPressedSpan?.isPressed = false
                    super.onTouchEvent(textView, spannable, event)
                }
                mPressedSpan = null
                Selection.removeSelection(spannable)
            }
            return true
        }

        private fun getPressedSpan(textView: TextView, spannable: Spannable, event: MotionEvent) : TouchableSpan? {
            var x = event.x.toInt()
            var y = event.y.toInt()

            x -= textView.totalPaddingLeft
            y -= textView.totalPaddingTop

            x += textView.scrollX
            y += textView.scrollY

            var layout = textView.layout
            var line = layout.getLineForVertical(y)
            var off = layout.getOffsetForHorizontal(line, x.toFloat())

            var link : Array<TouchableSpan> = spannable.getSpans(off, off, TouchableSpan::class.java)
            if (link.isNotEmpty()) {
                return link[0]
            }
            return null
        }
    }

}