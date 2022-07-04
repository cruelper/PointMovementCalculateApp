package ru.nuykin.pointmovementcalculateapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class MyCanvas extends View {
    private final int primaryColor = Color.BLUE;
    private final int secondaryColor = Color.YELLOW;
    private int width;
    private int height;
    private Canvas canvas;
    private Boolean isStartAnimate = false;

    private int x = 10;

    public MyCanvas(Context context) {
        super(context);
    }
    public MyCanvas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public MyCanvas(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public MyCanvas(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.width = getWidth();
        this.height = getHeight();
        this.canvas = canvas;

        if (isStartAnimate) {
            invalidate();
            printBall();
        }

        printBounce();
    }

    protected void printBounce(){
        @SuppressLint("DrawAllocation")
        Paint primaryColorPaint = new Paint();
        primaryColorPaint.setColor(primaryColor);
        primaryColorPaint.setStyle(Paint.Style.FILL);
        primaryColorPaint.setStrokeWidth(5);

        canvas.drawLine(0, 0, width, 0, primaryColorPaint);
        canvas.drawLine(0, 0, 0, height, primaryColorPaint);
        canvas.drawLine(width, 0, width, height, primaryColorPaint);
        canvas.drawLine(0, height, width, height, primaryColorPaint);
    }

    protected void printBall(){
        @SuppressLint("DrawAllocation")
        Paint primaryColorPaint = new Paint();
        primaryColorPaint.setColor(primaryColor);
        primaryColorPaint.setStyle(Paint.Style.FILL);
        primaryColorPaint.setStrokeWidth(5);

        canvas.drawLine(x, x, width, 0, primaryColorPaint);
        x = x + 10;
    }

    public void startAnimate(){

        invalidate();
        isStartAnimate = true;
    }
    public void stopAnimate(){ isStartAnimate = false; }
}
