package com.rinkosen.mygank.ui.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by rinkousen on 17/11/9.
 */

public class RulerView extends View {

    /**
     * 最小刻度间隔
     */
    int minScaleWidth = 10;

    /**
     * 最小刻度数量
     */
    int minScaleNums = 5;

    int maxScaleHeight = 80;

    int middleScaleHeight = 40;

    int minScaleHeight = 20;

    /**
     * 范围最小值
     */
    int minValue = 5000;

    /**
     * 范围最大值
     */
    int maxValue = 10000;

    enum UNIT{
        KG, M
    }


    public RulerView(Context context) {
        super(context);
    }

    public RulerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RulerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        dx = getMeasuredWidth()/2;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawRuler(canvas);
        drawScale(canvas);
        drawNum(canvas);

    }

    private void drawRuler(Canvas canvas) {
//        canvas.save();
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5);
        paint.setTextSize(50);

//        canvas.drawLine(50 * minScaleWidth, getMeasuredHeight()/2 , 50 * minScaleWidth, getMeasuredHeight()/2 - middleScaleHeight, paint);

        int n = (maxValue - minValue) / 2;
        for (int i = 0; i < n + 1; i++) {
//            canvas.save();
            if ((i % 5 == 0) && (i % 50 != 0)) {
                canvas.drawLine(i * minScaleWidth + dx, getMeasuredHeight()/2 , i * minScaleWidth + dx, getMeasuredHeight()/2 - middleScaleHeight, paint);

            } else if (i % 50 == 0){
                canvas.drawLine(i * minScaleWidth + dx, getMeasuredHeight()/2 , i * minScaleWidth + dx, getMeasuredHeight()/2 - maxScaleHeight, paint);
                String num = ((i/50) + minValue /100) + "";
                canvas.drawText(num, 0, num.length(), i * minScaleWidth + dx, getMeasuredHeight()/2 + 50 , paint);
            } else {
                canvas.drawLine(i * minScaleWidth + dx, getMeasuredHeight()/2 , i * minScaleWidth + dx, getMeasuredHeight()/2 - minScaleHeight, paint);
            }
//            canvas.restore();
        }

    }

    private void drawScale(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(5);
        paint.setTextSize(50);
        canvas.drawLine(getMeasuredWidth()/2, getMeasuredHeight()/2 - 100, getMeasuredWidth()/2, getMeasuredHeight()/2 -200, paint);
        canvas.drawText(getNumText(), 0, getNumText().length(), getMeasuredWidth()/2, getMeasuredHeight()/2 -220, paint);
    }


    private void drawNum(Canvas canvas) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                orignalX = event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                dx += - (orignalX - event.getX());
                if(dx > getMeasuredWidth()/2){
                    dx = getMeasuredWidth()/2;
                }
                updateRuler(dx);
                orignalX = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                orignalX = 0;
                break;
        }
        return true;
    }

    private void updateRuler(float x) {
        Log.e("xxxxx", x + "");
//        Message msg = new Message();
//        Bundle bundle = new Bundle();
//        bundle.putFloat("x", x);
//        msg.setData(bundle);
        handler.sendEmptyMessage(0);

    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            invalidate();
        }
    };

    float orignalX = 0;
    float dx = 0;

    private String getNumText(){
        float num = minValue/100 - (dx - 540)/500;

        return num + "";
    }
}
