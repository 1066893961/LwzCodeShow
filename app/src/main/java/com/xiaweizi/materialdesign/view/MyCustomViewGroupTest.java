package com.xiaweizi.materialdesign.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by lwz on 2018/3/30.
 */

public class MyCustomViewGroupTest extends ViewGroup {
    public MyCustomViewGroupTest(Context context) {
        super(context);
    }

    public MyCustomViewGroupTest(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        measureChildren(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (childCount == 0){
            setMeasuredDimension(0,0);
        }else if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(getMaxChildViewWidth(childCount), getTotalViewHeight(childCount));
        } else if (widthMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(getMaxChildViewWidth(childCount), heightSize);
        } else if (heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSize, getTotalViewHeight(childCount));
        }


    }

    private int getMaxChildViewWidth(int childCount) {
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
        int childCount = getChildCount();
        int currentH = 0;

        for (int i = 0; i < childCount; i++) {
            View childview = getChildAt(i);
            int w = childview.getMeasuredWidth();
            int h = childview.getMeasuredHeight();
            childview.layout(l, currentH, w+l, h+currentH);

            currentH += h;
        }
    }
}
