package ru.nuykin.pointmovementcalculateapp;

public class PointMotionComputer {
    private double h0;
    private double v0;
    private double alpha;
    private int frameCount;
    private final double g = 9.8;

    PointMotionComputer(){
        h0 = 10; v0 = 10; alpha = 10; frameCount = 100;
    }

    public String updateH0(){ return updateH0(10); }
    public String updateH0(double newH0){ h0 = newH0; return (int)h0 + "м"; }

    public String updateV0(){ return updateV0(10); }
    public String updateV0(double newV0){ v0 = newV0; return (int)v0 + "м/с"; }

    public String updateAlpha(){ return updateAlpha(10); }
    public String updateAlpha(double newAlpha){ alpha = newAlpha; return (int)alpha + "°"; }

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

        CalculatedValues(double[] x, double[] h, double[] v, double[] alpha){
            this.x = x; this.h = h; this.v = v; this.alpha = alpha;
        }
    }

    public CalculatedValues calculate(){
        double[] x = new double[frameCount];
        double[] h = new double[frameCount];
        double[] v = new double[frameCount];
        double[] alpha = new double[frameCount];



        return new CalculatedValues(x, h, v, alpha);
    }
}
