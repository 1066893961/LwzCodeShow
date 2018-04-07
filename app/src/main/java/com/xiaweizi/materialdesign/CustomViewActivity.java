package com.xiaweizi.materialdesign;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.xiaweizi.materialdesign.view.MyView;

/**
 * Created by lwz on 2018/3/21.
 */

public class CustomViewActivity extends AppCompatActivity {

    private View view;
    private MyView myView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customview);

        myView= (MyView)findViewById(R.id.view2);
        myView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "dian", Toast.LENGTH_SHORT).show();
                ObjectAnimator.ofFloat(myView, "translationX",0, 100).setDuration(1000).start();
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
