package com.xiaweizi.materialdesign.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by lwz on 2018/3/30.
 */

public class MyCustomViewGroup extends ViewGroup {

    public MyCustomViewGroup(Context context) {
        super(context);
    }

    public MyCustomViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //将所有的子view进行测量  这会触发每个子view的onMesure方法
        //注意要与mesureChild（）区分开， mesureChild是对单个view进行测量
        measureChildren(widthMeasureSpec, heightMeasureSpec);


        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int child = getChildCount();

        if (child == 0) {
            setMeasuredDimension(0, 0);
            //如果宽高都是wrap_content
        } else if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            //我们将宽度设置为子view中最大的宽度
            //将高度设置为所有子view的高度相加
            int height = getTotalViewHeight(child);
            int width = getMaxChildWidth(child);
            setMeasuredDimension(width, height);
        } else if (heightMode == MeasureSpec.AT_MOST) {//如果只有高度是包裹内容
            //宽度设置为ViewGroup自己的测量宽度，高度设置为所有子View的高度总和
            setMeasuredDimension(widthSize, getTotalViewHeight(child));
        } else if (widthMode == MeasureSpec.AT_MOST) {//如果只有宽度是包裹内容
            //宽度设置为子View中宽度最大的值，高度设置为ViewGroup自己的测量值
            setMeasuredDimension(getMaxChildWidth(child), heightSize);
        }
    }

    private int getMaxChildWidth(int childCount) {

        int maxWidth = 0;

        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            if (childView.getMeasuredWidth() > maxWidth) {
                maxWidth = childView.getMeasuredWidth();
            }
        }

        return maxWidth;
    }

    private int getTotalViewHeight(int childCount) {

        int totalHeight = 0;
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            totalHeight += childView.getMeasuredHeight();
        }
        return totalHeight;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        //记录当前的高度位置
        int currentHeight = t;
        for (int i = 0; i < count; i++) {
            View childView = getChildAt(i);
            int w = childView.getMeasuredWidth();
            int h = childView.getMeasuredHeight();
            childView.layout(l, currentHeight, l + w, currentHeight + h);

            currentHeight += h;
        }
    }
}
