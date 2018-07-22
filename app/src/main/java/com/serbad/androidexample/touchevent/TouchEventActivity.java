package com.serbad.androidexample.touchevent;

import android.app.Activity;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.serbad.androidexample.R;

public class TouchEventActivity extends Activity {
    private ConstraintLayout main;
    private MyButton click_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_event);
        click_btn = findViewById(R.id.click_btn);
        click_btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                boolean value = false;
                Log.i("xx", "MyButton--onTouch: " + value + event.getAction());

                return value;
            }
        });
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        boolean value = super.dispatchTouchEvent(event);
        Log.i("xx", "TouchEventActivity--dispatchTouchEvent: " + value + event.getAction());
        return value;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean value = super.onTouchEvent(event);
        Log.i("xx", "TouchEventActivity--onTouchEvent: " + value + event.getAction());
        return value;
    }


}
