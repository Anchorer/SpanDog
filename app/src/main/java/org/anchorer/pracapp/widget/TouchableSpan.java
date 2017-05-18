package org.anchorer.pracapp.widget;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

/**
 * 自定义Span：实现一个可点击的Span
 * Created by Anchorer on 2017/4/12.
 */
public class TouchableSpan extends ClickableSpan {
    private boolean mIsPressed;
    private int mTextColor;
    private int mNormalBgColor;
    private int mPressedBgColor;

    private Object data;
    private TouchableSpanClickListener mClickListener;

    public TouchableSpan(int normalTextColor, int normalBgColor, int pressedBgColor) {
        this.mTextColor = normalTextColor;
        this.mNormalBgColor = normalBgColor;
        this.mPressedBgColor = pressedBgColor;
    }

    public void setOnClickListener(TouchableSpanClickListener listener) {
        this.mClickListener = listener;
    }

    public void setPressed(boolean mIsPressed) {
        this.mIsPressed = mIsPressed;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    @Override
    public void onClick(View view) {
        if (mClickListener != null) {
            mClickListener.onClick(view, data);
        }
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);
        ds.setColor(mTextColor);
        ds.bgColor = mIsPressed ? mPressedBgColor : mNormalBgColor;
        ds.setUnderlineText(false);
    }
}
