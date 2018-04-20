//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.circuits;

import com.flanagan.complex.Complex;
import com.flanagan.math.Fmath;

public class Impedance {
    protected static int numberOfModels = 44;

    public Impedance() {
    }

    public static Complex resistanceImpedance(double var0) {
        return new Complex(var0, 0.0D);
    }

    public static Complex capacitanceImpedance(double var0, double var2) {
        return new Complex(0.0D, -1.0D / (var0 * var2));
    }

    public static Complex inductanceImpedance(double var0, double var2) {
        return new Complex(0.0D, var0 * var2);
    }

    public static Complex infiniteWarburgImpedance(double var0, double var2) {
        double var4 = var0 / Math.sqrt(var2);
        return new Complex(var4, -var4);
    }

    public static Complex finiteWarburgImpedance(double var0, double var2, double var4) {
        Complex var6 = new Complex(var0 * Math.sqrt(var4), 0.0D);
        Complex var7 = new Complex(var2, 0.0D);
        Complex var8 = new Complex(0.0D, var4);
        Complex var9 = Complex.sqrt(var8);
        Complex var10 = var7.times(var9);
        Complex var11 = Complex.tanh(var10);
        Complex var12 = var6.times(var11);
        Complex var13 = Complex.plusOne().minus(Complex.plusJay());
        return var13.times(var12);
    }

    public static Complex constantPhaseElementImpedance(double var0, double var2, double var4) {
        Complex var6 = new Complex(0.0D, var4);
        Complex var7 = Complex.pow(var6, -var2);
        Complex var8 = new Complex(var0, 0.0D);
        return var8.times(var7);
    }

    public static Complex impedanceInSeries(Complex var0, Complex var1) {
        return var0.plus(var1);
    }

    public static Complex impedanceInSeries(double var0, Complex var2) {
        return var2.plus(var0);
    }

    public static Complex impedanceInSeries(Complex var0, double var1) {
        return var0.plus(var1);
    }

    public static Complex impedanceInSeries(double var0, double var2) {
        return new Complex(var0 + var2, 0.0D);
    }

    public static Complex rInSeriesWithC(double var0, double var2, double var4) {
        Complex var6 = new Complex(var0, -1.0D / (var2 * var4));
        return var6;
    }

    public static Complex rInSeriesWithL(double var0, double var2, double var4) {
        Complex var6 = new Complex(var0, var2 * var4);
        return var6;
    }

    public static Complex cInSeriesWithL(double var0, double var2, double var4) {
        Complex var6 = new Complex(0.0D, -1.0D / (var0 * var4));
        Complex var7 = new Complex(0.0D, var2 * var4);
        return var6.plus(var7);
    }

    public static Complex impedanceInParallel(Complex var0, Complex var1) {
        Complex var2 = var0.times(var1);
        return var2.over(var0.plus(var1));
    }

    public static Complex impedanceInParallel(Complex var0, double var1) {
        Complex var3 = var0.times(var1);
        return var3.over(var0.plus(var1));
    }

    public static Complex impedanceInParallel(double var0, Complex var2) {
        Complex var3 = var2.times(var0);
        return var3.over(var2.plus(var0));
    }

    public static Complex impedanceInParallel(double var0, double var2) {
        return new Complex(var0 * var2 / (var0 + var2), 0.0D);
    }

    public static Complex rInParallelWithC(double var0, double var2, double var4) {
        Complex var6 = new Complex(0.0D, -1.0D / (var2 * var4));
        Complex var7 = new Complex(var0, 0.0D);
        Complex var8 = var6.times(var7);
        return var8.over(var6.plus(var7));
    }

    public static Complex rInParallelWithL(double var0, double var2, double var4) {
        Complex var6 = new Complex(0.0D, var2 * var4);
        Complex var7 = new Complex(var0, 0.0D);
        Complex var8 = var6.times(var7);
        return var8.over(var6.plus(var7));
    }

    public static Complex cInParallelWithL(double var0, double var2, double var4) {
        Complex var6 = new Complex(0.0D, -1.0D / (var0 * var4));
        Complex var7 = new Complex(0.0D, var2 * var4);
        Complex var8 = var6.plus(var7);
        Complex var9 = var6.times(var7);
        return var9.over(var8);
    }

    public static String[] modelComponents(int var0) {
        String[][] var1 = new String[][]{{" "}, {"R1"}, {"C1"}, {"L1"}, {"W1"}, {"Fsigma1", "Fdelta1"}, {"Qsigma1", "Qalpha1"}, {"R1", "C1"}, {"R1", "L1"}, {"L1", "C1"}, {"R1", "C1"}, {"R1", "L1"}, {"L1", "C1"}, {"R1", "C1", "R2"}, {"R1", "C1", "R2", "L1"}, {"R1", "C1", "R2", "L1"}, {"R1", "C1", "C2"}, {"R1", "C1", "C2"}, {"R1", "C1", "R2", "C2"}, {"R1", "C1", "R2", "C2"}, {"R1", "C1", "R2", "C2", "R3"}, {"R1", "C1", "R2", "C2", "R3"}, {"R1", "C1", "R2", "C2", "R3", "C3"}, {"R1", "C1", "R2", "C2", "R3", "C3", "R4"}, {"R1", "C1", "W1", "R2"}, {"R1", "C1", "Fsigma1", "Fdelta1", "R2"}, {"R1", "C1", "Qsigma1", "Qalpha1", "R2"}, {"R1", "C1", "R2", "C2", "W1"}, {"R1", "C1", "R2", "C2", "W3", "C3", "R4"}, {"R1", "C1", "R2", "Qsigma1", "Qalpha1"}, {"R1", "C1", "R2", "Qsigma1", "Qalpha1", "R3"}, {"R1", "Qsigma1", "Qalpha1", "R2", "Qsigma2", "Qalpha2", "R3", "Qsigma3", "Qalpha3"}, {"R1", "Qsigma1", "Qalpha1", "R2", "Qsigma2", "Qalpha2", "R3", "Qsigma3", "Qalpha3", "R4"}, {"R1", "Qsigma1", "Qalpha1", "R2", "Qsigma2", "Qalpha2", "R3", "Qsigma3", "Qalpha3", "R4", "C1"}, {"C1", "Qsigma1", "Qalpha1", "C2", "Qsigma2", "Qalpha2", "C3", "Qsigma3", "Qalpha3"}, {"C1", "Qsigma1", "Qalpha1", "C2", "Qsigma2", "Qalpha2", "C3", "Qsigma3", "Qalpha3", "R1"}, {"R1", "Qsigma1", "Qalpha1", "C1", "R2", "Qsigma2", "Qalpha2", "C2", "R3", "Qsigma3", "Qalpha3", "C3"}, {"R1", "Qsigma1", "Qalpha1", "C1", "R2", "Qsigma2", "Qalpha2", "C2", "R3", "Qsigma3", "Qalpha3", "C3", "R4"}, {"R1", "Qsigma1", "Qalpha1", "C1", "R2", "Qsigma2", "Qalpha2", "C2"}, {"R1", "Qsigma1", "Qalpha1", "C1", "R2", "Qsigma2", "Qalpha2", "C2", "R3"}, {"R1", "Qsigma1", "Qalpha1", "R2", "Qsigma2", "Qalpha2", "R3", "Qsigma3", "Qalpha3", "R4", "C1"}, {"R1", "Qsigma1", "Qalpha1", "R2", "C2", "R3", "Qsigma3", "Qalpha3", "R4", "C1"}, {"R1", "Qsigma1", "Qalpha1", "R2", "C2", "R3", "Qsigma3", "Qalpha3", "R4"}, {"R1", "Qsigma1", "Qalpha1", "R2", "Qsigma2", "Qalpha2", "R3", "Qsigma3", "Qalpha3", "R4", "C1"}, {"R1", "Qsigma1", "Qalpha1", "R2", "Qsigma2", "Qalpha2", "R3", "C1"}};
        return var1[var0];
    }

    public static Complex modelImpedance(double[] var0, double var1, int var3) {
        Complex var4 = null;
        Complex var5 = null;
        Complex var6 = null;
        Complex var7 = null;
        Complex var8 = null;
        switch(var3) {
            case 1:
                var4 = resistanceImpedance(var0[0]);
                break;
            case 2:
                var4 = capacitanceImpedance(var0[0], var1);
                break;
            case 3:
                var4 = inductanceImpedance(var0[0], var1);
                break;
            case 4:
                var4 = infiniteWarburgImpedance(var0[0], var1);
                break;
            case 5:
                var4 = finiteWarburgImpedance(var0[0], var0[1], var1);
                break;
            case 6:
                var4 = constantPhaseElementImpedance(var0[0], var0[1], var1);
                break;
            case 7:
                var4 = rInSeriesWithC(var0[0], var0[1], var1);
                break;
            case 8:
                var4 = new Complex(var0[0], var0[1] * var1);
                break;
            case 9:
                var5 = new Complex(0.0D, -1.0D / (var0[0] * var1));
                var6 = new Complex(0.0D, var0[1] * var1);
                var4 = impedanceInSeries(var5, var6);
                break;
            case 10:
                var4 = rInParallelWithC(var0[0], var0[1], var1);
                break;
            case 11:
                var5 = new Complex(var0[0], 0.0D);
                var6 = new Complex(0.0D, var0[1] * var1);
                var4 = impedanceInParallel(var5, var6);
                break;
            case 12:
                var5 = new Complex(0.0D, -1.0D / (var0[0] * var1));
                var6 = new Complex(0.0D, var0[1] * var1);
                var4 = impedanceInParallel(var5, var6);
                break;
            case 13:
                var4 = rInParallelWithC(var0[0], var0[1], var1);
                var4 = var4.plus(var0[2]);
                break;
            case 14:
                var4 = rInParallelWithC(var0[0], var0[1], var1);
                var4 = var4.plus(var0[2]);
                var5 = new Complex(0.0D, var0[3] * var1);
                var4 = var4.plus(var5);
                break;
            case 15:
                var4 = rInParallelWithC(var0[0], var0[1], var1);
                var4 = var4.plus(var0[2]);
                var5 = new Complex(0.0D, var0[3] * var1);
                var4 = impedanceInParallel(var4, var5);
                break;
            case 16:
                var4 = rInParallelWithC(var0[0], var0[1], var1);
                var6 = new Complex(0.0D, -1.0D / (var0[2] * var1));
                var4 = var4.plus(var6);
                break;
            case 17:
                var5 = new Complex(var0[0], -1.0D / (var0[1] * var1));
                var6 = new Complex(0.0D, -1.0D / (var0[2] * var1));
                var4 = var5.times(var6).over(var6.plus(var5));
                break;
            case 18:
                var5 = rInParallelWithC(var0[0], var0[1], var1);
                var6 = new Complex(var0[2], -1.0D / (var0[3] * var1));
                var4 = var5.plus(var6);
                break;
            case 19:
                var5 = rInParallelWithC(var0[0], var0[1], var1);
                var6 = rInParallelWithC(var0[2], var0[3], var1);
                var4 = var5.plus(var6);
                break;
            case 20:
                var5 = rInParallelWithC(var0[0], var0[1], var1);
                var6 = rInParallelWithC(var0[2], var0[3], var1);
                var4 = var5.plus(var6);
                var4 = var4.plus(var0[4]);
                break;
            case 21:
                var5 = rInParallelWithC(var0[0], var0[1], var1);
                var6 = var5.plus(var0[2]);
                var7 = new Complex(0.0D, -1.0D / (var0[3] * var1));
                var8 = impedanceInParallel(var6, var7);
                var4 = var8.plus(var0[4]);
                break;
            case 22:
                var5 = rInParallelWithC(var0[0], var0[1], var1);
                var6 = rInParallelWithC(var0[2], var0[3], var1);
                var4 = var5.plus(var6);
                var7 = rInParallelWithC(var0[4], var0[5], var1);
                var4 = var4.plus(var7);
                break;
            case 23:
                var5 = rInParallelWithC(var0[0], var0[1], var1);
                var6 = rInParallelWithC(var0[2], var0[3], var1);
                var4 = var5.plus(var6);
                var7 = rInParallelWithC(var0[4], var0[5], var1);
                var4 = var4.plus(var7);
                var4 = var4.plus(var0[6]);
                break;
            case 24:
                var5 = infiniteWarburgImpedance(var0[2], var1);
                var6 = impedanceInSeries(var0[0], var5);
                var7 = capacitanceImpedance(var0[1], var1);
                var8 = impedanceInParallel(var6, var7);
                var4 = impedanceInSeries(var8, var0[3]);
                break;
            case 25:
                var5 = finiteWarburgImpedance(var0[2], var0[3], var1);
                var6 = impedanceInSeries(var0[0], var5);
                var7 = capacitanceImpedance(var0[1], var1);
                var8 = impedanceInParallel(var6, var7);
                var4 = impedanceInSeries(var8, var0[4]);
                break;
            case 26:
                var5 = constantPhaseElementImpedance(var0[2], var0[3], var1);
                var6 = impedanceInSeries(var0[0], var5);
                var7 = capacitanceImpedance(var0[1], var1);
                var8 = impedanceInParallel(var6, var7);
                var4 = impedanceInSeries(var8, var0[4]);
                break;
            case 27:
                var5 = rInParallelWithC(var0[0], var0[1], var1);
                var6 = rInParallelWithC(var0[2], var0[3], var1);
                var4 = var5.plus(var6);
                var7 = infiniteWarburgImpedance(var0[4], var1);
                var4 = var4.plus(var7);
                break;
            case 28:
                var5 = rInParallelWithC(var0[0], var0[1], var1);
                var6 = rInParallelWithC(var0[2], var0[3], var1);
                var4 = var5.plus(var6);
                var7 = infiniteWarburgImpedance(var0[4], var1);
                var8 = new Complex(0.0D, -1.0D / (var0[5] * var1));
                var8 = impedanceInParallel(var7, var8);
                var4 = var4.plus(var8);
                var4 = var4.plus(var0[6]);
                break;
            case 29:
                var5 = rInParallelWithC(var0[0], var0[1], var1);
                var6 = constantPhaseElementImpedance(var0[3], var0[4], var1);
                var7 = impedanceInParallel(var6, var0[2]);
                var4 = var5.plus(var7);
                break;
            case 30:
                var5 = rInParallelWithC(var0[0], var0[1], var1);
                var6 = constantPhaseElementImpedance(var0[3], var0[4], var1);
                var7 = impedanceInParallel(var6, var0[2]);
                var4 = var5.plus(var7);
                var4 = var4.plus(var0[5]);
                break;
            case 31:
                var5 = constantPhaseElementImpedance(var0[1], var0[2], var1);
                var4 = impedanceInParallel(var5, var0[0]);
                var5 = constantPhaseElementImpedance(var0[4], var0[5], var1);
                var6 = impedanceInParallel(var5, var0[3]);
                var4 = var4.plus(var6);
                var5 = constantPhaseElementImpedance(var0[7], var0[8], var1);
                var6 = impedanceInParallel(var5, var0[6]);
                var4 = var4.plus(var6);
                break;
            case 32:
                var5 = constantPhaseElementImpedance(var0[1], var0[2], var1);
                var4 = impedanceInParallel(var5, var0[0]);
                var5 = constantPhaseElementImpedance(var0[4], var0[5], var1);
                var6 = impedanceInParallel(var5, var0[3]);
                var4 = var4.plus(var6);
                var5 = constantPhaseElementImpedance(var0[7], var0[8], var1);
                var6 = impedanceInParallel(var5, var0[6]);
                var4 = var4.plus(var6);
                var4 = var4.plus(var0[9]);
                break;
            case 33:
                var5 = constantPhaseElementImpedance(var0[1], var0[2], var1);
                var4 = impedanceInParallel(var5, var0[0]);
                var5 = constantPhaseElementImpedance(var0[4], var0[5], var1);
                var6 = impedanceInParallel(var5, var0[3]);
                var4 = var4.plus(var6);
                var5 = constantPhaseElementImpedance(var0[7], var0[8], var1);
                var6 = impedanceInParallel(var5, var0[6]);
                var4 = var4.plus(var6);
                var4 = var4.plus(var0[9]);
                var7 = new Complex(0.0D, -1.0D / (var0[10] * var1));
                var4 = impedanceInParallel(var4, var7);
                break;
            case 34:
                var5 = new Complex(0.0D, -1.0D / (var0[0] * var1));
                var6 = constantPhaseElementImpedance(var0[1], var0[2], var1);
                var4 = impedanceInParallel(var5, var6);
                var5 = new Complex(0.0D, -1.0D / (var0[3] * var1));
                var6 = constantPhaseElementImpedance(var0[4], var0[5], var1);
                var7 = impedanceInParallel(var5, var6);
                var4 = var4.plus(var7);
                var5 = new Complex(0.0D, -1.0D / (var0[6] * var1));
                var6 = constantPhaseElementImpedance(var0[7], var0[8], var1);
                var7 = impedanceInParallel(var5, var6);
                var4 = var4.plus(var7);
                break;
            case 35:
                var5 = new Complex(0.0D, -1.0D / (var0[0] * var1));
                var6 = constantPhaseElementImpedance(var0[1], var0[2], var1);
                var4 = impedanceInParallel(var5, var6);
                var5 = new Complex(0.0D, -1.0D / (var0[3] * var1));
                var6 = constantPhaseElementImpedance(var0[4], var0[5], var1);
                var7 = impedanceInParallel(var5, var6);
                var4 = var4.plus(var7);
                var5 = new Complex(0.0D, -1.0D / (var0[6] * var1));
                var6 = constantPhaseElementImpedance(var0[7], var0[8], var1);
                var7 = impedanceInParallel(var5, var6);
                var4 = var4.plus(var7);
                var4 = var4.plus(var0[9]);
                break;
            case 36:
                var5 = constantPhaseElementImpedance(var0[1], var0[2], var1);
                var6 = var5.plus(var0[0]);
                var7 = new Complex(0.0D, -1.0D / (var0[3] * var1));
                var4 = impedanceInParallel(var6, var7);
                var5 = constantPhaseElementImpedance(var0[5], var0[6], var1);
                var6 = var5.plus(var0[4]);
                var7 = new Complex(0.0D, -1.0D / (var0[7] * var1));
                var8 = impedanceInParallel(var6, var7);
                var4 = var4.plus(var8);
                var5 = constantPhaseElementImpedance(var0[9], var0[10], var1);
                var6 = var5.plus(var0[8]);
                var7 = new Complex(0.0D, -1.0D / (var0[11] * var1));
                var8 = impedanceInParallel(var6, var7);
                var4 = var4.plus(var8);
                break;
            case 37:
                var5 = constantPhaseElementImpedance(var0[1], var0[2], var1);
                var6 = var5.plus(var0[0]);
                var7 = new Complex(0.0D, -1.0D / (var0[3] * var1));
                var4 = impedanceInParallel(var6, var7);
                var5 = constantPhaseElementImpedance(var0[5], var0[6], var1);
                var6 = var5.plus(var0[4]);
                var7 = new Complex(0.0D, -1.0D / (var0[7] * var1));
                var8 = impedanceInParallel(var6, var7);
                var4 = var4.plus(var8);
                var5 = constantPhaseElementImpedance(var0[9], var0[10], var1);
                var6 = var5.plus(var0[8]);
                var7 = new Complex(0.0D, -1.0D / (var0[11] * var1));
                var8 = impedanceInParallel(var6, var7);
                var4 = var4.plus(var8);
                var4 = var4.plus(var0[12]);
                break;
            case 38:
                var5 = constantPhaseElementImpedance(var0[1], var0[2], var1);
                var6 = var5.plus(var0[0]);
                var7 = new Complex(0.0D, -1.0D / (var0[3] * var1));
                var4 = impedanceInParallel(var6, var7);
                var5 = constantPhaseElementImpedance(var0[5], var0[6], var1);
                var6 = var5.plus(var0[4]);
                var7 = new Complex(0.0D, -1.0D / (var0[7] * var1));
                var8 = impedanceInParallel(var6, var7);
                var4 = var4.plus(var8);
                break;
            case 39:
                var5 = constantPhaseElementImpedance(var0[1], var0[2], var1);
                var6 = var5.plus(var0[0]);
                var7 = new Complex(0.0D, -1.0D / (var0[3] * var1));
                var4 = impedanceInParallel(var6, var7);
                var5 = constantPhaseElementImpedance(var0[5], var0[6], var1);
                var6 = var5.plus(var0[4]);
                var7 = new Complex(0.0D, -1.0D / (var0[7] * var1));
                var8 = impedanceInParallel(var6, var7);
                var4.plus(var8);
                var4 = var8.plus(var0[8]);
                break;
            case 40:
                var5 = constantPhaseElementImpedance(var0[1], var0[2], var1);
                var4 = impedanceInParallel(var5, var0[0]);
                var5 = constantPhaseElementImpedance(var0[4], var0[5], var1);
                var6 = impedanceInParallel(var5, var0[3]);
                var4 = var4.plus(var6);
                var5 = constantPhaseElementImpedance(var0[7], var0[8], var1);
                var6 = impedanceInParallel(var5, var0[6]);
                var4 = var4.plus(var6);
                var7 = new Complex(0.0D, -1.0D / (var0[10] * var1));
                var4 = impedanceInParallel(var4, var7);
                var4 = var4.plus(var0[9]);
                break;
            case 41:
                var5 = constantPhaseElementImpedance(var0[1], var0[2], var1);
                var4 = impedanceInParallel(var5, var0[0]);
                var5 = new Complex(0.0D, -1.0D / (var0[4] * var1));
                var6 = impedanceInParallel(var5, var0[3]);
                var4 = var4.plus(var6);
                var5 = constantPhaseElementImpedance(var0[6], var0[7], var1);
                var6 = impedanceInParallel(var5, var0[5]);
                var4 = var4.plus(var6);
                var7 = new Complex(0.0D, -1.0D / (var0[9] * var1));
                var4 = impedanceInParallel(var4, var7);
                var4 = var4.plus(var0[8]);
                break;
            case 42:
                var5 = constantPhaseElementImpedance(var0[1], var0[2], var1);
                var4 = impedanceInParallel(var5, var0[0]);
                var5 = new Complex(0.0D, -1.0D / (var0[4] * var1));
                var6 = impedanceInParallel(var5, var0[3]);
                var4 = var4.plus(var6);
                var5 = constantPhaseElementImpedance(var0[6], var0[7], var1);
                var6 = impedanceInParallel(var5, var0[5]);
                var4 = var4.plus(var6);
                var4 = var4.plus(var0[8]);
                break;
            case 43:
                var5 = constantPhaseElementImpedance(var0[1], var0[2], var1);
                var4 = impedanceInParallel(var5, var0[0]);
                var5 = constantPhaseElementImpedance(var0[4], var0[5], var1);
                var6 = impedanceInParallel(var5, var0[3]);
                var4 = var4.plus(var6);
                var5 = constantPhaseElementImpedance(var0[7], var0[8], var1);
                var6 = impedanceInParallel(var5, var0[6]);
                var4 = var4.plus(var6);
                var4 = var4.plus(var0[9]);
                var7 = new Complex(0.0D, -1.0D / (var0[10] * var1));
                var4 = impedanceInParallel(var4, var7);
                break;
            case 44:
                var5 = constantPhaseElementImpedance(var0[1], var0[2], var1);
                var4 = impedanceInParallel(var5, var0[0]);
                var5 = constantPhaseElementImpedance(var0[4], var0[5], var1);
                var6 = impedanceInParallel(var5, var0[3]);
                var4 = var4.plus(var6);
                var4 = var4.plus(var0[6]);
                var7 = new Complex(0.0D, -1.0D / (var0[7] * var1));
                var4 = impedanceInParallel(var4, var7);
                break;
            default:
                throw new IllegalArgumentException("No model " + var3 + " exists");
        }

        return var4;
    }

    public static double warburgSigma(double var0, double var2, double var4, double var6, double var8, double var10, int var12) {
        double var13 = 8.31447215D * (var10 - -273.15D) / (Fmath.square((double)var12 * 96485.341539D) * var0 * Math.sqrt(2.0D));
        double var15 = 1.0D / (var4 * Math.sqrt(var2));
        double var17 = 1.0D / (var8 * Math.sqrt(var6));
        return var13 * (var15 + var17);
    }

    public static double parallelPlateCapacitance(double var0, double var2, double var4) {
        return var0 * var4 * 8.854187817E-12D / var2;
    }

    public static double parallelPlateCapacitance(double var0, double var2, double var4, double var6) {
        return var0 * var2 * var6 * 8.854187817E-12D / var4;
    }

    public static double coaxialCapacitance(double var0, double var2, double var4, double var6) {
        return 6.283185307179586D * var6 * 8.854187817E-12D * var0 / Math.log(var4 / var2);
    }

    public static double parallelWiresCapacitance(double var0, double var2, double var4, double var6) {
        return 3.141592653589793D * var6 * 8.854187817E-12D * var0 / Math.log((var4 - var2) / var2);
    }

    public static double parallelPlateInductance(double var0, double var2, double var4, double var6) {
        return var6 * 1.2566370614359173E-6D * var4 * var0 / var2;
    }

    public static double coaxialInductance(double var0, double var2, double var4, double var6) {
        return var6 * 1.2566370614359173E-6D * var0 * Math.log(var4 / var2) / 6.283185307179586D;
    }

    public static double parallelWiresInductance(double var0, double var2, double var4, double var6) {
        return var6 * 1.2566370614359173E-6D * var0 * Math.log((var4 - var2) / var2) / 3.141592653589793D;
    }

    public static double getMagnitude(Complex var0) {
        return var0.abs();
    }

    public static double getPhaseRad(Complex var0) {
        return var0.argRad();
    }

    public static double getPhaseDeg(Complex var0) {
        return var0.argDeg();
    }

    public static Complex polarRad(double var0, double var2) {
        Complex var4 = new Complex();
        var4.polarRad(var0, var2);
        return var4;
    }

    public static Complex polarDeg(double var0, double var2) {
        Complex var4 = new Complex();
        var4.polarDeg(var0, var2);
        return var4;
    }

    public static double frequencyToRadialFrequency(double var0) {
        return 6.283185307179586D * var0;
    }

    public static double radialFrequencyToFrequency(double var0) {
        return var0 / 6.283185307179586D;
    }

    public static double resistanceAluminium(double var0, double var2, double var4) {
        double var6 = 2.824E-8D;
        double var8 = 0.0039D;
        double var10 = var6 * var0 / var2;
        return var10 * (1.0D + var8 * (var4 - 20.0D));
    }

    public static double resistanceAluminum(double var0, double var2, double var4) {
        double var6 = 2.824E-8D;
        double var8 = 0.0039D;
        double var10 = var6 * var0 / var2;
        return var10 * (1.0D + var8 * (var4 - 20.0D));
    }

    public static double resistanceHardDrawnCopper(double var0, double var2, double var4) {
        double var6 = 1.771E-8D;
        double var8 = 0.00382D;
        double var10 = var6 * var0 / var2;
        return var10 * (1.0D + var8 * (var4 - 20.0D));
    }

    public static double resistanceAnnealedCopper(double var0, double var2, double var4) {
        double var6 = 1.7241E-8D;
        double var8 = 0.00393D;
        double var10 = var6 * var0 / var2;
        return var10 * (1.0D + var8 * (var4 - 20.0D));
    }

    public static double resistanceIron(double var0, double var2, double var4) {
        double var6 = 1.0E-7D;
        double var8 = 0.005D;
        double var10 = var6 * var0 / var2;
        return var10 * (1.0D + var8 * (var4 - 20.0D));
    }

    public static double resistanceManganeseSteel(double var0, double var2, double var4) {
        double var6 = 7.0E-7D;
        double var8 = 0.001D;
        double var10 = var6 * var0 / var2;
        return var10 * (1.0D + var8 * (var4 - 20.0D));
    }

    public static double resistanceSiemensMartinSteel(double var0, double var2, double var4) {
        double var6 = 1.8E-7D;
        double var8 = 0.003D;
        double var10 = var6 * var0 / var2;
        return var10 * (1.0D + var8 * (var4 - 20.0D));
    }

    public static double resistanceBBSteel(double var0, double var2, double var4) {
        double var6 = 1.19E-7D;
        double var8 = 0.004D;
        double var10 = var6 * var0 / var2;
        return var10 * (1.0D + var8 * (var4 - 20.0D));
    }

    public static double resistanceEBBSteel(double var0, double var2, double var4) {
        double var6 = 1.04E-7D;
        double var8 = 0.005D;
        double var10 = var6 * var0 / var2;
        return var10 * (1.0D + var8 * (var4 - 20.0D));
    }

    public static double resistanceBrass(double var0, double var2, double var4) {
        double var6 = 7.4E-8D;
        double var8 = 0.002D;
        double var10 = var6 * var0 / var2;
        return var10 * (1.0D + var8 * (var4 - 20.0D));
    }

    public static double resistanceDrawnTunsten(double var0, double var2, double var4) {
        double var6 = 5.6E-8D;
        double var8 = 0.0045D;
        double var10 = var6 * var0 / var2;
        return var10 * (1.0D + var8 * (var4 - 20.0D));
    }

    public static double resistanceSilver(double var0, double var2, double var4) {
        double var6 = 1.59E-8D;
        double var8 = 0.0038D;
        double var10 = var6 * var0 / var2;
        return var10 * (1.0D + var8 * (var4 - 20.0D));
    }

    public static double resistanceGold(double var0, double var2, double var4) {
        double var6 = 2.84E-8D;
        double var8 = 0.0034D;
        double var10 = var6 * var0 / var2;
        return var10 * (1.0D + var8 * (var4 - 20.0D));
    }

    public static double resistancePlatinum(double var0, double var2, double var4) {
        double var6 = 1.0E-7D;
        double var8 = 0.003D;
        double var10 = var6 * var0 / var2;
        return var10 * (1.0D + var8 * (var4 - 20.0D));
    }

    public static double resistanceNickel(double var0, double var2, double var4) {
        double var6 = 7.8E-8D;
        double var8 = 0.006D;
        double var10 = var6 * var0 / var2;
        return var10 * (1.0D + var8 * (var4 - 20.0D));
    }

    public static double resistanceMolybdenum(double var0, double var2, double var4) {
        double var6 = 5.7E-8D;
        double var8 = 0.004D;
        double var10 = var6 * var0 / var2;
        return var10 * (1.0D + var8 * (var4 - 20.0D));
    }

    public static double resistancePhosphorBronze(double var0, double var2, double var4) {
        double var6 = 1.1E-7D;
        double var8 = 0.0033D;
        double var10 = var6 * var0 / var2;
        return var10 * (1.0D + var8 * (var4 - 20.0D));
    }

    public static double resistanceTin(double var0, double var2, double var4) {
        double var6 = 1.15E-7D;
        double var8 = 0.0042D;
        double var10 = var6 * var0 / var2;
        return var10 * (1.0D + var8 * (var4 - 20.0D));
    }

    public static double resistanceNichrome(double var0, double var2, double var4) {
        double var6 = 1.0E-6D;
        double var8 = 4.0E-4D;
        double var10 = var6 * var0 / var2;
        return var10 * (1.0D + var8 * (var4 - 20.0D));
    }

    public static double resistancePalladium(double var0, double var2, double var4) {
        double var6 = 1.1E-7D;
        double var8 = 0.0033D;
        double var10 = var6 * var0 / var2;
        return var10 * (1.0D + var8 * (var4 - 20.0D));
    }

    public static double resistanceTantalum(double var0, double var2, double var4) {
        double var6 = 1.55E-7D;
        double var8 = 0.0031D;
        double var10 = var6 * var0 / var2;
        return var10 * (1.0D + var8 * (var4 - 20.0D));
    }

    public static double resistanceTherlo(double var0, double var2, double var4) {
        double var6 = 4.7E-7D;
        double var8 = 1.0E-5D;
        double var10 = var6 * var0 / var2;
        return var10 * (1.0D + var8 * (var4 - 20.0D));
    }

    public static double resistanceMonelMetal(double var0, double var2, double var4) {
        double var6 = 4.2E-7D;
        double var8 = 0.002D;
        double var10 = var6 * var0 / var2;
        return var10 * (1.0D + var8 * (var4 - 20.0D));
    }

    public static double resistanceManganan(double var0, double var2, double var4) {
        double var6 = 4.4E-7D;
        double var8 = 1.0E-5D;
        double var10 = var6 * var0 / var2;
        return var10 * (1.0D + var8 * (var4 - 20.0D));
    }

    public static double resistanceConstantan(double var0, double var2, double var4) {
        double var6 = 4.9E-7D;
        double var8 = 1.0E-5D;
        double var10 = var6 * var0 / var2;
        return var10 * (1.0D + var8 * (var4 - 20.0D));
    }

    public static double resistanceAntimony(double var0, double var2, double var4) {
        double var6 = 4.17E-7D;
        double var8 = 0.0036D;
        double var10 = var6 * var0 / var2;
        return var10 * (1.0D + var8 * (var4 - 20.0D));
    }

    public static double resistanceCobalt(double var0, double var2, double var4) {
        double var6 = 9.8E-8D;
        double var8 = 0.0033D;
        double var10 = var6 * var0 / var2;
        return var10 * (1.0D + var8 * (var4 - 20.0D));
    }

    public static double resistanceMagnesium(double var0, double var2, double var4) {
        double var6 = 4.6E-8D;
        double var8 = 0.004D;
        double var10 = var6 * var0 / var2;
        return var10 * (1.0D + var8 * (var4 - 20.0D));
    }

    public static double resistanceZinc(double var0, double var2, double var4) {
        double var6 = 5.8E-8D;
        double var8 = 0.0037D;
        double var10 = var6 * var0 / var2;
        return var10 * (1.0D + var8 * (var4 - 20.0D));
    }

    public static double resistanceMercury(double var0, double var2, double var4) {
        double var6 = 9.5738E-7D;
        double var8 = 8.9E-4D;
        double var10 = var6 * var0 / var2;
        return var10 * (1.0D + var8 * (var4 - 20.0D));
    }

    public static double resistanceLead(double var0, double var2, double var4) {
        double var6 = 2.2E-7D;
        double var8 = 0.0039D;
        double var10 = var6 * var0 / var2;
        return var10 * (1.0D + var8 * (var4 - 20.0D));
    }

    public static double resistanceGermanSilver(double var0, double var2, double var4) {
        double var6 = 3.3E-7D;
        double var8 = 4.0E-4D;
        double var10 = var6 * var0 / var2;
        return var10 * (1.0D + var8 * (var4 - 20.0D));
    }

    public static double resistivityToResistance(double var0, double var2, double var4, double var6, double var8) {
        double var10 = var0 * var4 / var6;
        return var10 * (1.0D + var2 * (var8 - 20.0D));
    }

    public static double resistivityToResistance(double var0, double var2, double var4) {
        return var0 * var2 / var4;
    }

    public static double resistanceToResistivity(double var0, double var2, double var4) {
        return var0 * var4 / var2;
    }
}
