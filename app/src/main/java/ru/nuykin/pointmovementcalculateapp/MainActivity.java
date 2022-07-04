package ru.nuykin.pointmovementcalculateapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends Activity {
    private PointMotionComputer pmc = new PointMotionComputer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);

        setOnClickListeners();
    }

    public void setOnClickListeners(){
        TextView h0 = findViewById(R.id.h0); h0.setText(pmc.updateH0(pmc.getH0()));
        TextView v0 = findViewById(R.id.v0); v0.setText(pmc.updateV0(pmc.getV0()));
        TextView alpha = findViewById(R.id.alpha); alpha.setText(pmc.updateAlpha(pmc.getAlpha()));

        findViewById(R.id.startAnimateBut).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { ((MyCanvas) findViewById(R.id.myCanvas)).startAnimate(); }
        });

        findViewById(R.id.h0ButUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { h0.setText(pmc.updateH0(pmc.getH0() + 1)); }
        });

        findViewById(R.id.h0ButDown).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { h0.setText(pmc.updateH0(pmc.getH0() - 1)); }
        });

        findViewById(R.id.v0ButUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { v0.setText(pmc.updateV0(pmc.getV0() + 1)); }
        });

        findViewById(R.id.v0ButDown).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { v0.setText(pmc.updateV0(pmc.getV0() - 1)); }
        });

        findViewById(R.id.alphaButUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { alpha.setText(pmc.updateAlpha(pmc.getAlpha() + 1)); }
        });

        findViewById(R.id.alphaButDown).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { alpha.setText(pmc.updateAlpha(pmc.getAlpha() - 1)); }
        });
    }
}