package com.rinkosen.mygank.ui.widget;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by rinkousen on 17/11/2.
 */

public class CircleCheck extends View {

    private ObjectAnimator animator1;
    private ObjectAnimator animator;
    int width;

    int height;

    int ringProgress = 0;

    int radius = 0;

    private Point[] mPoints;

    Line line1;

    Line line2;

    public CircleCheck(Context context) {
        super(context);
    }

    public CircleCheck(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        animator = ObjectAnimator.ofInt(this, "ringProgress", 0, 360);
        animator.setDuration(2000);
        animator.setInterpolator(null);

//        animator.start();

        animator1 = ObjectAnimator.ofInt(this, "radius", 250, 0);
        animator1.setDuration(1000);
        animator1.setInterpolator(new DecelerateInterpolator());
    }

    public CircleCheck(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        animator = ObjectAnimator.ofInt(this, "ringProgress", 0, 360);
        animator.setDuration(2000);
        animator.setInterpolator(null);

//        animator.start();

        animator1 = ObjectAnimator.ofInt(this, "radius", 250, 0);
        animator1.setDuration(1000);
        animator1.setInterpolator(new DecelerateInterpolator());
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);

        width = getMeasuredHeight();
        height = getHeight();
        radius = getWidth()/4;

        //设置打钩的几个点坐标
        Path path = new Path();
        line1 = new Line(new Point(getWidth() /8 * 3, getHeight() * 0.55f), new Point(getWidth() / 2, getHeight()/8*5));
        line2 = new Line(new Point(getWidth() /2, getHeight()/8*5), new Point(getWidth() * 0.65f, getHeight() * 0.45f));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);
        RectF rectF = new RectF(getWidth()/4, getHeight()/4, getWidth()/4*3, getHeight()/4*3);

        canvas.drawArc(rectF, 90, ringProgress, false, paint);
        //背景圆
        Paint circlePaint = new Paint();
        circlePaint.setColor(Color.GREEN);
        circlePaint.setStyle(Paint.Style.FILL);

        canvas.drawCircle(getWidth()/2, getHeight()/2, ringProgress == 360 ? getWidth()/4 : 0, circlePaint);

        if(ringProgress == 360){
            Paint smallPaint = new Paint();
            smallPaint.setColor(Color.WHITE);
            smallPaint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(getWidth()/2, getHeight()/2, ringProgress == 360 ? radius : 0, smallPaint);

            if(radius == 0){
                Paint linepaint = new Paint();
                linepaint.setColor(Color.WHITE);
                linepaint.setStrokeWidth(10);
                linepaint.setStyle(Paint.Style.STROKE);
                canvas.drawLine(line1.startPoint.x, line1.startPoint.y, line1.endPoint.x, line1.endPoint.y, linepaint);
                canvas.drawLine(line2.startPoint.x, line2.startPoint.y, line2.endPoint.x, line2.endPoint.y, linepaint);
            }
        }
    }

    public int getRingProgress() {
        return ringProgress;
    }

    public void setRingProgress(int ringProgress) {
        this.ringProgress = ringProgress;
        postInvalidate();
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
        postInvalidate();
    }

    public void start(){

//        animator1.start();

        AnimatorSet set = new AnimatorSet();
        set.playSequentially(animator, animator1);
        set.start();
    }

    class Line {
        Point startPoint;

        Point endPoint;

        //斜率
        float k;

        //偏移量
        float b;

        public Line(Point startPoint, Point endPoint){
            this.startPoint = startPoint;
            this.endPoint = endPoint;
            k = (endPoint.y - startPoint.y) / (endPoint.x - startPoint.x);
            b = startPoint.y - k * startPoint.x;
        }


        public float getY(float x){
            return k * x + b;
        }

    }

    class Point {
        float x;
        float y;

        public Point(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }
}
