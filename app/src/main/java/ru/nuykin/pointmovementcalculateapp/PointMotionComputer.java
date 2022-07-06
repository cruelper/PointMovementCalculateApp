package ru.nuykin.pointmovementcalculateapp;

public class PointMotionComputer {
    private double h0;
    private double v0;
    private double alpha;
    private final double g = 9.8;

    protected static double timeFrame;

    PointMotionComputer(){
        h0 = 10; v0 = 10; alpha = 10; timeFrame = 0.01;
    }

    public String updateH0(){ return updateH0(10); }
    public String updateH0(double newH0){ h0 = newH0 >= 0 ? newH0 : 0; return (int)h0 + "м"; }

    public String updateV0(){ return updateV0(10); }
    public String updateV0(double newV0){ v0 = newV0 >= 0 ? newV0 : 0; return (int)v0 + "м/с"; }

    public String updateAlpha(){ return updateAlpha(10); }
    public String updateAlpha(double newAlpha){
        alpha = newAlpha >= 0 ? newAlpha : 0;
        alpha = alpha < 90 ? alpha : 89;
        return (int)alpha + "°";
    }

    public double getH0() {
        return h0;
    }
    public double getV0() {
        return v0;
    }
    public double getAlpha() {
        return alpha;
    }

    static class CalculatedValues{
        public double[] x;
        public double[] h;
        public double[] v;
        public double[] alpha;
        public double[] t;

        CalculatedValues(int timeCount){
            this.x = new double[timeCount];
            this.h = new double[timeCount];
            this.v = new double[timeCount];
            this.t = new double[timeCount];
            this.alpha = new double[timeCount];

            for (double curTime = timeCount * timeFrame; timeCount > 0; curTime-=timeFrame) {
                this.t[timeCount - 1] = curTime; timeCount -= 1;
            }
        }
    }

    public CalculatedValues calculate(){
        double v0x = v0 * Math.cos(alpha * Math.PI / 180);
        double v0y = v0 * Math.sin(alpha * Math.PI / 180);

        double moveTime = (-v0y - Math.sqrt(v0y * v0y + 2 * g * h0)) / -g;

        int timeCount = (int)Math.ceil(moveTime / timeFrame);

        CalculatedValues values = new CalculatedValues(timeCount);

        for (int i = 0; i < timeCount; i+=1) {
            double t = values.t[i];
            values.x[i] = v0x * t;
            values.h[i] = h0 + v0y * t - g * t * t / 2;
            values.v[i] = Math.sqrt(v0x * v0x + (v0y - g * t) * (v0y - g * t));
            values.alpha[i] = Math.atan((v0y - g * t) / v0x);
        }

        return values;
    }
}
