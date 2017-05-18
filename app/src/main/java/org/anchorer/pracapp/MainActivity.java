package org.anchorer.pracapp;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.View;
import android.widget.Toast;

import org.anchorer.pracapp.widget.TouchableSpan;
import org.anchorer.pracapp.widget.TouchableSpanClickListener;
import org.anchorer.pracapp.widget.TouchableTextView;

public class MainActivity extends AppCompatActivity implements TouchableSpanClickListener {

    private static final String SPANNABLE_TOTAL = "Hello World!!";
    private static final String SPANNABLE = "Hello";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TouchableTextView textView = (TouchableTextView) findViewById(R.id.text);

        TouchableSpan touchableSpan = new TouchableSpan(ContextCompat.getColor(this, R.color.orange_1),
                ContextCompat.getColor(this, android.R.color.transparent), ContextCompat.getColor(this, R.color.gray_1));
        touchableSpan.setData(SPANNABLE);
        touchableSpan.setOnClickListener(this);
        SpannableString spannableString = new SpannableString(SPANNABLE_TOTAL);
        int index = SPANNABLE_TOTAL.indexOf(SPANNABLE);
        spannableString.setSpan(touchableSpan, index, index + SPANNABLE.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spannableString);
    }

    @Override
    public void onClick(View view, Object data) {
        Toast.makeText(this, "Touchable Span Click: " + view.getId() + ", Data: " + data, Toast.LENGTH_SHORT).show();
    }
}
