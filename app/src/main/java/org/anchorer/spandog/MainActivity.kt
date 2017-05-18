package org.anchorer.spandog

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.text.Spannable
import android.text.SpannableString
import android.view.View
import android.widget.Toast
import org.anchorer.spandog.widget.TouchableSpan
import org.anchorer.spandog.widget.TouchableSpanClickListener
import org.anchorer.spandog.widget.TouchableTextView

/**
 * Main Activity
 * Created by Anchorer on 2017/5/18.
 */
class MainActivity : AppCompatActivity(), TouchableSpanClickListener  {

    private val SPANNABLE_TOTAL = "Hello World!!"
    private val SPANNABLE = "Hello"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById(R.id.text) as TouchableTextView

        val touchableSpan = TouchableSpan(
                ContextCompat.getColor(this, R.color.orange_1),
                ContextCompat.getColor(this, android.R.color.transparent),
                ContextCompat.getColor(this, R.color.gray_1))
        touchableSpan.data = SPANNABLE
        touchableSpan.clickListener = this

        val spannableString = SpannableString(SPANNABLE_TOTAL)
        var index = SPANNABLE_TOTAL.indexOf(SPANNABLE)
        spannableString.setSpan(touchableSpan, index, index + SPANNABLE.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        textView.text = spannableString
    }

    override fun onClick(view: View, data: Any?) {
        Toast.makeText(this, "Touchable Span Click: Data=" + data, Toast.LENGTH_SHORT).show()
    }

}