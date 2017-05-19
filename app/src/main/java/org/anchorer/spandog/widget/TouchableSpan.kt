package org.anchorer.spandog.widget

import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.View

/**
 * Touchable Span, which implements click event on Span
 * Created by Anchorer on 2017/5/18.
 */
class TouchableSpan : ClickableSpan {

    var isPressed = false

    var textColor = 0
    var normalBgColor = 0
    var pressedBgColor = 0

    var data : Any? = null
    var clickListener : TouchableSpanClickListener? = null

    constructor(textColor: Int, normalBgColor: Int, pressedBgColor: Int) {
        this.textColor = textColor
        this.normalBgColor = normalBgColor
        this.pressedBgColor = pressedBgColor
    }

    override fun onClick(widget: View) {
        clickListener?.onClick(widget, data)
    }

    override fun updateDrawState(ds: TextPaint) {
        super.updateDrawState(ds)
        ds.color = textColor
        ds.bgColor = if (isPressed) pressedBgColor else normalBgColor
        ds.isUnderlineText = false
    }

}