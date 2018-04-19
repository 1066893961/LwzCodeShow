package com.xiaweizi.materialdesign.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by lwz on 2018/4/11.
 */

public class CircleView extends View {

    private int color = Color.GREEN;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public CircleView(Context context) {
        super(context);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int padL = getPaddingLeft();
        int padT = getPaddingTop();
        int padR = getPaddingRight();
        int padB = getPaddingBottom();
        int w = getWidth() - padL - padR;
        int h = getHeight() - padB - padT;
        int radius = Math.min(w, h) / 2;
        paint.setColor(color);

        canvas.drawCircle(w / 2 +padL, h / 2 + padT, radius, paint);
    }
}
