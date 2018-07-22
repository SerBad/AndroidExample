package com.serbad.androidexample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.serbad.androidexample.mvp.MVPActivity;
import com.serbad.androidexample.touchevent.TouchEventActivity;

public class MainActivity extends Activity implements View.OnClickListener {

    private Button mvp_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mvp_button = (Button) findViewById(R.id.mvp_button);
        mvp_button.setOnClickListener(this);
        findViewById(R.id.touch_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mvp_button:
                Intent intent = new Intent(MainActivity.this, MVPActivity.class);
                startActivity(intent);
                break;
            case R.id.touch_button:
                startActivity(new Intent(MainActivity.this, TouchEventActivity.class));
                break;
        }

    }
}
