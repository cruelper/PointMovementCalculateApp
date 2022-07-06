package ru.nuykin.pointmovementcalculateapp;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Arrays;
import java.util.OptionalDouble;
import java.util.stream.DoubleStream;

public class ServiceFunctions {
    public static double getMaxDoubleInArr(double[] arr){
        DoubleStream doubleStream = Arrays.stream(arr);
        OptionalDouble optionalDouble = doubleStream.max();
        return optionalDouble.getAsDouble();
    }

    public static int getIndexOfApproxElemInArr(double[] arr, double num){
        int b =0, e = arr.length - 1, m;
        while(b <= e){
            m = b + (e - b) / 2;
            if (arr[m] == num){
                e = m;
                break;
            }
            else {
                if (arr[m] < num) b = m + 1;
                else e = m - 1;
            }
        }

        if(e >= 0 && b <= arr.length - 1) return arr[b] > arr[e] ? b : e;
        else if(b > arr.length - 1) return e;
        else return b;
    }
}
