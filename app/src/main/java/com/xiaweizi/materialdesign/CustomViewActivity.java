package com.xiaweizi.materialdesign;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.AssetManager;
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

import com.xiaweizi.materialdesign.view.MyTextView;
import com.xiaweizi.materialdesign.view.MyView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Created by lwz on 2018/3/21.
 */

public class CustomViewActivity extends AppCompatActivity {

    private View view;
    private MyView myView;
    private LinearLayout mBottomLl;
    private CardView mTopLl;
    private MyTextView textview11;
    public String ToDBC(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375)
                c[i] = (char) (c[i] - 65248);
        }
        return new String(c);
    }

    public String DBCToSBC(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 32) {
                c[i] = (char) 12288;
                continue;
            }
            if (c[i] < 127)
                c[i] = (char) (c[i] + 65248);
        }
        return new String(c);
    }

    public String StringFilter(String str) throws PatternSyntaxException {
        str=str.replaceAll("【","[").replaceAll("】","]").replaceAll("！","!");//替换中文标号
        String regEx="[『』]"; // 清除掉特殊字符
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customview);
        textview11 = (MyTextView)findViewById(R.id.textview11);
        String ss = "公司介绍是什么呀1234567890，减肥倒计时肯定就是的境况 撒了肯德基发发了——）（）*&……@！|{}“”：？》《?><\":{}|+_)(*&^%$#@!~~fsdjfklsjlfjafuljfwuriowejlrjeklfkldjfklsjdfjsdjfskjfklsjfklsdjf就开始减肥了开始减肥的垃圾发啦房间里的爱减肥啦EIREJKJRLKJEJDK.KMC.D,.SMCM.,XMCLDMLKFJDKLSJFKLDSJFKLSDJFKSDJFDSM,.九分裤娄底市解放路就爱了房间里开始的房间里三大房间里撒的分健康鲁大师爱减肥啦爱家乐福家乐福就大风大浪三发+————）——）（）";
        textview11.setText(ss);

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

    @Override
    protected void onStart() {
        super.onStart();
        mTopLl.post(new Runnable() {
            @Override
            public void run() {
                int h = mTopLl.getHeight();
                int w = mTopLl.getWidth();
            }
        });

        mBottomLl.post(new Runnable() {
            @Override
            public void run() {
                int h = mBottomLl.getHeight();
                int w = mBottomLl.getWidth();
            }
        });
    }

    public String getAssetsString(Context context,String fileName){
        StringBuffer sb = new StringBuffer();
        //根据语言选择加载
        try {
            AssetManager am = context.getAssets();
            InputStream in = am.open(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while((line = reader.readLine())!=null){
                line += ("\n");
                sb.append(line);
            }
            reader.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
