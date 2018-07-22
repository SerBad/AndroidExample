package com.serbad.androidexample.touchevent;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;

public class MyConstraintLayout extends ConstraintLayout {
    public MyConstraintLayout(Context context) {
        super(context);
    }

    public MyConstraintLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyConstraintLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean value = super.onInterceptTouchEvent(ev);
        Log.i("xx", "MyConstraintLayout--onInterceptTouchEvent: " + value + ev.getAction());
        return value;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        boolean value = super.dispatchTouchEvent(event);
        Log.i("xx", "MyConstraintLayout--dispatchTouchEvent: " + value + event.getAction());
        return value;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean value = super.onTouchEvent(event);
        Log.i("xx", "MyConstraintLayout--onTouchEvent: " + value + event.getAction());
        return value;
    }

}
