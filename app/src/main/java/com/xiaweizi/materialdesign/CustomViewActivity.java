package com.xiaweizi.materialdesign;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.xiaweizi.materialdesign.view.MyView;

/**
 * Created by lwz on 2018/3/21.
 */

public class CustomViewActivity extends AppCompatActivity {

    private View view;
    private MyView myView;
    private LinearLayout mBottomLl;
    private CardView mTopLl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customview);

        mBottomLl = (LinearLayout) findViewById(R.id.bottom_ll);
        mTopLl = (CardView) findViewById(R.id.top_ll);

        mTopLl.measure(0, 0);
//        int h = mTopLl.getLayoutParams().height;
//        int w = mTopLl.getLayoutParams().width;

        mBottomLl.measure(0, 0);
//        int h2 = mBottomLl.getLayoutParams().height;
//        int w2 = mBottomLl.getLayoutParams().width;
        final RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        final RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                int h = mTopLl.getHeight();
                int w = mTopLl.getWidth();
                int h2 = mBottomLl.getHeight();

                if (h > h2) {
                    params.height = h2 + dp2px(getApplicationContext(), 40);
                    params.width = w;
                    params.setMargins(dp2px(getApplicationContext(), 22),
                            dp2px(getApplicationContext(), 20), 0,
                            dp2px(getApplicationContext(), 10));
                    mTopLl.setLayoutParams(params);
                    params2.height = h2;
                    params2.width = LinearLayout.LayoutParams.MATCH_PARENT;
                    params2.setMargins(dp2px(getApplicationContext(), 12),
                            dp2px(getApplicationContext(), 40),
                            dp2px(getApplicationContext(), 12),
                            dp2px(getApplicationContext(), 10));
                    mBottomLl.setLayoutParams(params2);

                } else {
                    params.height = h2 + dp2px(getApplicationContext(), 40);
                    params.width = w;
                    params.setMargins(dp2px(getApplicationContext(), 22),
                            dp2px(getApplicationContext(), 20), 0,
                            dp2px(getApplicationContext(), 10));
                    mTopLl.setLayoutParams(params);
                    params2.height = h2;
                    params2.width = LinearLayout.LayoutParams.MATCH_PARENT;
                    params2.setMargins(dp2px(getApplicationContext(), 22),
                            dp2px(getApplicationContext(), 40),
                            dp2px(getApplicationContext(), 12),
                            dp2px(getApplicationContext(), 10));
                    mBottomLl.setLayoutParams(params2);
                }

            }
        }, 100);


        myView = (MyView) findViewById(R.id.view2);
        myView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "dian", Toast.LENGTH_SHORT).show();
                ObjectAnimator.ofFloat(myView, "translationX", 0, 100).setDuration(1000).start();
            }
        });
        myView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
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

    public int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


}
