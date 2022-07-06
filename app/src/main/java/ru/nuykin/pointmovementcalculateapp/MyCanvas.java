package ru.nuykin.pointmovementcalculateapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.res.ResourcesCompat;

import java.util.HashMap;
import java.util.Map;

public class MyCanvas extends View {
    private final int primaryColor = Color.BLUE;
    private final int secondaryColor = Color.GREEN;
    private int width;
    private int height;
    private Canvas canvas;
    private Boolean isStartAnimate = false;
    private PointMotionComputer.CalculatedValues values;
    private int currentStep = 0;
    private double coef;

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
            printBall();
            printPath(currentStep);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (currentStep == 0 && values != null) printPath(values.t.length);
        printBounce();
        invalidate();
    }

    public Map<Character, Double> getValuesOnTouch(MotionEvent event){
        Map<Character, Double> map = new HashMap<>();
        if (values.t.length > 0) {
            int index = ServiceFunctions.getIndexOfApproxElemInArr(values.x, event.getX() / coef);
            map.put('x', values.x[index]);
            map.put('h', values.h[index]);
            map.put('v', values.v[index]);
            map.put('a', values.alpha[index]);
            map.put('t', values.t[index]);
        }
        return map;
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
        Paint paint = new Paint();
        paint.setColor(primaryColor);
        paint.setStyle(Paint.Style.FILL);

        double hmax = ServiceFunctions.getMaxDoubleInArr(values.h) * 1.1;
        double xmax = values.x[values.x.length - 1] * 1.1;
        coef = Math.min(height / hmax, width / xmax);

        canvas.drawCircle(
                (int)(values.x[currentStep] * coef),
                (int)(height - values.h[currentStep] * coef),
                20,
                paint
        );

        currentStep+=1;
        if (currentStep == values.t.length) {
            isStartAnimate = false;
            currentStep = 0;
        }
    }

    protected void printPath(int maxStep){
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(secondaryColor);
        for (int i = 0; i < maxStep; i+=10) {
            canvas.drawCircle(
                    (int)(values.x[i] * coef),
                    (int)(height - values.h[i] * coef),
                    6,
                    paint
            );
        }
    }

    public void startAnimate(PointMotionComputer.CalculatedValues values){
        this.values = values;
        isStartAnimate = true;
        invalidate();
    }
    public void stopAnimate(){ isStartAnimate = false; }
}
