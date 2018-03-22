package com.xiaweizi.materialdesign.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by lwz on 2018/3/22.
 */

public class MyView2 extends View {
    Paint paint = new Paint();
    private int defaultWidth = 100;
    private int defaultHeight = 100;
    //view 当前位置
    private int rawX;
    private int rawY;

    //view之前的位置
    private int lastX;
    private int lastY;

    //拖动view时  xy的偏移量
    private int offsetX;
    private int offsetY;


    public MyView2(Context context) {
        super(context);
    }

    public MyView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(Color.GREEN);
        canvas.drawCircle(this.getWidth() / 2, this.getHeight() / 2, this.getWidth() / 2, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //手指落下时 记录xy坐标
                rawX = (int) event.getRawX();
                rawY = (int) event.getRawY();

                lastX = rawX;
                lastY = rawY;

                break;
            case MotionEvent.ACTION_MOVE:
                //手指移动时  记录xy的位置
                rawX = (int) event.getRawX();
                rawY = (int) event.getRawY();

                offsetX = rawX - lastX;
                offsetY = rawY - lastY;
                layout(getLeft() + offsetX,
                        getTop() + offsetY,
                        getRight() + offsetX,
                        getBottom() + offsetY);
                lastX = rawX;
                lastY = rawY;


                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(defaultWidth, defaultHeight);
        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(defaultWidth, heightSpecSize);
        } else if (heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSpecSize, defaultHeight);
        } else {
            setMeasuredDimension(widthSpecSize, heightSpecSize);
        }
    }
}
