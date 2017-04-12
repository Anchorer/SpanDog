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

    private View.OnClickListener mClickListener;

    public TouchableSpan(int normalTextColor, int normalBgColor, int pressedBgColor) {
        this.mTextColor = normalTextColor;
        this.mNormalBgColor = normalBgColor;
        this.mPressedBgColor = pressedBgColor;
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.mClickListener = listener;
    }

    public void setPressed(boolean mIsPressed) {
        this.mIsPressed = mIsPressed;
    }

    @Override
    public void onClick(View widget) {
        if (mClickListener != null) {
            mClickListener.onClick(widget);
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
