package com.hzl.administrator.test1;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

/**
 * @author hzl
 * @time 2016/5/26 10:25
 */
public class MyTextView extends TextView {
    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta=context.obtainStyledAttributes(attrs, R.styleable.test);

        String test1= ta.getString(R.styleable.test_text);
        int arr=ta.getInteger(R.styleable.test_textArr, -1);
        Log.e("Sdfsdf", "text = " + test1 + " , textAttr = " + arr);
        ta.recycle();
        Log.e("sdfsd","Recycle");
    }
}
