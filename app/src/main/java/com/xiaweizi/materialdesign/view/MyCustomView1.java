package com.xiaweizi.materialdesign.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.xiaweizi.materialdesign.R;

/**
 * @author lwz
 * @date 2018/3/30
 */

public class MyCustomView1 extends View {

    private int defaultSize;

    public MyCustomView1(Context context) {
        super(context);
    }

    public MyCustomView1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        //第二个参数就是我们在styles.xml文件中的<declare-styleable>标签
        //即属性集合的标签，在R文件中名称为R.styleable+name
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MyCustomView1);

        //第一个参数为属性集合里面的属性，R文件名称：R.styleable+属性集合名称+下划线+属性名称
        //第二个参数为，如果没有设置这个属性，则设置的默认的值
        defaultSize = a.getDimensionPixelSize(R.styleable.MyCustomView1_default_size, 200);

        //最后记得将TypedArray对象回收
        a.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int r = getMeasuredWidth() / 2;
        int centerX = r + getLeft();
        int centerY = r + getTop();

        Paint paint = new Paint();
        paint.setColor(Color.GREEN);

        canvas.drawCircle(centerX, centerY, r, paint);
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
//        layout(20,20,20,20);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMySize(100, widthMeasureSpec);
        int height = getMySize(100, heightMeasureSpec);
//1080  300
        if (width < height) {
            height = width;
        } else {
            width = height;
        }

        setMeasuredDimension(width, height);
    }

    private int getMySize(int defaultSize, int measureSpec) {
        int mySize = defaultSize;

        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);

        switch (mode) {
            case MeasureSpec.UNSPECIFIED: {//如果没有指定大小，就设置为默认大小
                mySize = defaultSize;
                break;
            }
            case MeasureSpec.AT_MOST: {//如果测量模式是最大取值为size
                //我们将大小取最大值,你也可以取其他值
                //xml中设置wrap_content  对应的测量模式为AT_MOST
                mySize = size;
                break;
            }
            case MeasureSpec.EXACTLY: {//如果是固定的大小，那就不要去改变它
                //xml中设置match_parent 对应的测量模式为EXACTLY
                //xml中设置view宽高为具体指  对应的测量模式为EXACTLY
                mySize = size;
                break;
            }
            default:
                break;
        }
        return mySize;
    }
}
