package com.rinkosen.mygank.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by rinkousen on 17/11/3.
 */

public class BezierView extends View {

    List<Point> mPoints;

    List<Point> secondPoints;

    /**
     * 波浪宽度
     */
    private int waveWidth = 1000;

    /**
     * 波浪高度
     */
    private int waveHeight = 200;

    /**
     * 移动速度
     */
    private float speed = 5f;

    /**
     * 波浪画笔
     */
    Paint wavePaint;

    Path wavePath;
    private MyTimerTask mTask;
    private Timer timer;


    public BezierView(Context context) {
        super(context);
        init();
    }

    public BezierView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BezierView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPoints = new ArrayList<>();
        timer = new Timer();
        initPaint();
    }

    private void initPaint(){
        wavePaint = new Paint();
        wavePaint.setStyle(Paint.Style.FILL);
        wavePaint.setColor(Color.BLUE);
        wavePaint.setStrokeWidth(5);
    }

    int totalLength;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            totalLength += speed;

            for (int i = 0; i < mPoints.size(); i++) {
                mPoints.get(i).setX(mPoints.get(i).getX() + speed);
                secondPoints.get(i).setX(secondPoints.get(i).getX() + speed/2);
                switch (i%4){
                    case 0:
                    case 2:

                        break;
                    case 1:

                        break;
                    case 3:

                        break;
                }

            }

            if(totalLength >= waveWidth){
                totalLength = 0;
                for (int i = 0; i < mPoints.size(); i++) {
                    mPoints.get(i).setX(mPoints.get(i).getX() - waveWidth);
                    secondPoints.get(i).setX(secondPoints.get(i).getX() - waveWidth/2);
                }
            }

            invalidate();
            
        }
    };

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mPoints = new ArrayList<>();
        secondPoints = new ArrayList<>();
        int mViewWidth = getMeasuredWidth();
        int mViewHeight = getMeasuredHeight();
        int n = (int) Math.round(mViewWidth / waveWidth + 0.5);
        int mLevelLine = mViewHeight / 2;
        for (int i = 0; i < (4 * n + 5); i++) {
            // 从P0开始初始化到P4n+4，总共4n+5个点
            float x = i * waveWidth / 4 - waveWidth;
            float y = 0;
            float y2 = 0;
            switch (i % 4) {
                case 0:
                case 2:
                    // 零点位于水位线上
                    y = mLevelLine;
                    y2 = mLevelLine;
                    break;
                case 1:
                    // 往下波动的控制点
                    y = mLevelLine + waveHeight;
                    y2 = mLevelLine - waveHeight;
                    break;
                case 3:
                    // 往上波动的控制点
                    y = mLevelLine - waveHeight;
                    y2 = mLevelLine + waveHeight;
                    break;
            }


            mPoints.add(new Point(x, y));
            secondPoints.add(new Point(x, y2));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        wavePath = new Path();

        wavePath.moveTo(mPoints.get(0).x, mPoints.get(0).y);
        for ( int i = 1;i < mPoints.size() - 1; i = i+ 2) {
            wavePath.quadTo(mPoints.get(i).x, mPoints.get(i).y,
                    mPoints.get(i + 1).x, mPoints.get(i + 1).y);
        }
        wavePath.lineTo(getWidth(), getHeight());
        wavePath.lineTo(0,getHeight());
        wavePath.close();
        canvas.save();
        canvas.drawPath(wavePath, wavePaint);
        canvas.restore();
        Path wavePath2 = new Path();
        wavePath2.moveTo(secondPoints.get(0).x, secondPoints.get(0).y);
        for ( int i = 1;i < secondPoints.size() - 1; i = i+ 2) {
            wavePath2.quadTo(secondPoints.get(i).x, secondPoints.get(i).y,
                    secondPoints.get(i + 1).x, secondPoints.get(i + 1).y);
        }
        wavePath2.lineTo(getWidth(), getHeight());
        wavePath2.lineTo(0,getHeight());

        wavePath2.close();
        canvas.drawPath(wavePath2, wavePaint);


    }

    class MyTimerTask extends TimerTask {
        Handler handler;

        public MyTimerTask(Handler handler) {
            this.handler = handler;
        }

        @Override
        public void run() {
            handler.sendMessage(handler.obtainMessage());
        }

    }

    public void start(){
        mTask = new MyTimerTask(handler);
        timer.schedule(mTask, 0, 10);
    }

//    private void flowingAnimation(){
//        ObjectAnimator animator = ObjectAnimator.ofFloat(this,"wave",0,100)
//                .setDuration(100);
//        animator.setRepeatCount(30000);
//        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator valueAnimator) {
//                dx = dx + speed;
//                shd_dx = shd_dx + speed/2;//Half the speed of the normal waves
//
//                rerefreshPoints();//更新初始化点
//                postInvalidate();
//            }
//        });
//        animator.start();
//    }

    class Point {
        float x;

        float y;

        public Point(float x, float y) {
            this.x = x;
            this.y = y;
        }

        public void setX(float x) {
            this.x = x;
        }

        public float getX() {
            return x;
        }
    }

}
