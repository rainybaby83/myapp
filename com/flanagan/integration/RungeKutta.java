//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.integration;

import com.flanagan.interpolation.CubicSpline;
import com.flanagan.math.ArrayMaths;
import com.flanagan.math.DeepCopy;
import com.flanagan.plot.PlotGraph;
import java.util.ArrayList;

public class RungeKutta {
    private double x0 = 0.0D / 0.0;
    private double xn = 0.0D / 0.0;
    private double y0 = 0.0D / 0.0;
    private double[] yy0 = null;
    private int nODE = 0;
    private double step = 0.0D / 0.0;
    private double[][] retArray = (double[][])null;
    private double relTol = 1.0E-5D;
    private double absTol = 0.001D;
    private int maxIter = -1;
    private int nIter = 0;
    private double[] xArray = null;
    private double[][] yArray = (double[][])null;
    private int nPoints = 0;
    private ArrayList<Double> xValues = new ArrayList();
    private ArrayList<double[]> yValues = new ArrayList();
    private boolean initXset = false;
    private boolean finalXset = false;
    private boolean initYset = false;
    private boolean nPset = false;
    private boolean stepSizeSet = false;
    private boolean xArraySet = false;
    private boolean yArraySet = false;
    private boolean integrationDone = false;
    private String[] rkMethods = new String[]{"None", "Fourth Order Runge-Kutta", "Cash-Karp-Runge-Kutta", "Fehlberg-Runge-Kutta"};
    private int rkMethodNumber = 0;
    private static int nStepsMultiplier = 1000;
    private static double safetyFactor = 0.9D;
    private static double incrementFactor = -0.2D;
    private static double decrementFactor = -0.25D;

    public RungeKutta() {
    }

    public void setInitialValueOfX(double var1) {
        this.x0 = var1;
        this.initXset = true;
    }

    public void setFinalValueOfX(double var1) {
        this.xn = var1;
        this.finalXset = true;
    }

    public void setInitialValueOfY(double var1) {
        this.setInitialValuesOfY(var1);
    }

    public void setInitialValueOfY(double[] var1) {
        this.setInitialValuesOfY(var1);
    }

    public void setInitialValuesOfY(double var1) {
        this.y0 = var1;
        this.yy0 = new double[1];
        this.yy0[0] = var1;
        this.nODE = 1;
        this.initYset = true;
    }

    public void setInitialValuesOfY(double[] var1) {
        this.yy0 = var1;
        this.nODE = var1.length;
        if (this.nODE == 1) {
            this.y0 = var1[0];
        }

        this.initYset = true;
    }

    public void calcXarray() {
        double var1 = (this.xn - this.x0) / (double)(this.nPoints - 1);
        this.xArray = new double[this.nPoints];
        this.xArray[0] = this.x0;
        this.xValues.add(this.x0);
        this.xArray[this.nPoints - 1] = this.xn;

        for(int var3 = 1; var3 < this.nPoints - 1; ++var3) {
            this.xArray[var3] = this.xArray[var3 - 1] + var1;
        }

        this.xArraySet = true;
    }

    public void initYarray() {
        this.yArray = new double[this.nODE][this.nPoints];

        for(int var1 = 0; var1 < this.nODE; ++var1) {
            this.yArray[var1][0] = this.yy0[var1];
        }

        this.yValues.add(this.yy0);
        this.yArraySet = true;
    }

    public void setStepSize(double var1) {
        this.step = var1;
        this.stepSizeSet = true;
    }

    public void setToleranceScalingFactor(double var1) {
        this.relTol = var1;
    }

    public void setToleranceAdditionFactor(double var1) {
        this.absTol = var1;
    }

    public void setMaximumIterations(int var1) {
        this.maxIter = var1;
    }

    public int getNumberOfIterations() {
        return this.nIter;
    }

    public static void resetNstepsMultiplier(int var0) {
        nStepsMultiplier = var0;
    }

    public void enteredDataCheckS() {
        if (Double.isNaN(this.x0)) {
            throw new IllegalArgumentException("No initial x value has been entered");
        } else if (Double.isNaN(this.xn)) {
            throw new IllegalArgumentException("No final x value has been entered");
        } else if (Double.isNaN(this.y0)) {
            throw new IllegalArgumentException("No initial y value has been entered");
        } else if (Double.isNaN(this.step)) {
            throw new IllegalArgumentException("No step size has been entered");
        }
    }

    public void enteredDataCheckM() {
        if (Double.isNaN(this.x0)) {
            throw new IllegalArgumentException("No initial x value has been entered");
        } else if (Double.isNaN(this.xn)) {
            throw new IllegalArgumentException("No final x value has been entered");
        } else if (this.yy0 == null) {
            throw new IllegalArgumentException("No initial y values have been entered");
        } else if (Double.isNaN(this.step)) {
            throw new IllegalArgumentException("No step size has been entered");
        }
    }

    public double fourthOrder(DerivFunction var1) {
        this.enteredDataCheckS();
        this.rkMethodNumber = 1;
        this.xValues.add(this.x0);
        this.yValues.add(this.yy0);
        double var2 = 0.0D;
        double var4 = 0.0D;
        double var6 = 0.0D;
        double var8 = 0.0D;
        double var10 = 0.0D;
        double var12 = this.y0;
        double[] var14 = new double[this.nODE];
        double var15 = (this.xn - this.x0) / this.step;
        var15 = Math.ceil(var15);
        int var17 = (int)var15;
        this.nIter = var17;
        double var18 = (this.xn - this.x0) / var15;

        for(int var20 = 0; var20 < var17; ++var20) {
            var10 = this.x0 + (double)var20 * var18;
            var2 = var18 * var1.deriv(var10, var12);
            var4 = var18 * var1.deriv(var10 + var18 / 2.0D, var12 + var2 / 2.0D);
            var6 = var18 * var1.deriv(var10 + var18 / 2.0D, var12 + var4 / 2.0D);
            var8 = var18 * var1.deriv(var10 + var18, var12 + var6);
            var12 += var2 / 6.0D + var4 / 3.0D + var6 / 3.0D + var8 / 6.0D;
            this.xValues.add(var10 + var18);
            var14[0] = var12;
            this.yValues.add(DeepCopy.copy(var14));
        }

        this.integrationDone = true;
        return var12;
    }

    public double[][] fourthOrder(DerivFunction var1, int var2) {
        if (var2 < 2) {
            throw new IllegalArgumentException("The number of points, " + var2 + ", must be greater than 1");
        } else {
            this.enteredDataCheckS();
            this.nPoints = var2;
            this.retArray = new double[this.nODE + 1][this.nPoints];
            this.nPset = true;
            this.calcXarray();
            this.initYarray();
            this.fourthOrder(var1);
            int var3 = this.xValues.size();
            double[] var4 = new double[var3];
            double[][] var5 = new double[this.nODE][var3];

            int var6;
            int var8;
            for(var6 = 0; var6 < var3; ++var6) {
                var4[var6] = (Double)this.xValues.get(var6);
                double[] var7 = (double[])this.yValues.get(var6);

                for(var8 = 0; var8 < this.nODE; ++var8) {
                    var5[var8][var6] = var7[var8];
                }
            }

            if (this.xArray[this.nPoints - 1] > var4[var3 - 1]) {
                this.xArray[this.nPoints - 1] = var4[var3 - 1];
            }

            for(var6 = 0; var6 < this.nPoints; ++var6) {
                this.retArray[0][var6] = this.xArray[var6];
            }

            for(var6 = 0; var6 < this.nODE; ++var6) {
                CubicSpline var9 = new CubicSpline(var4, var5[var6]);

                for(var8 = 0; var8 < this.nPoints; ++var8) {
                    this.retArray[var6 + 1][var8] = var9.interpolate(this.xArray[var8]);
                }
            }

            this.nPoints = 0;
            return this.retArray;
        }
    }

    public double[][] fourthOrder(DerivFunction var1, double[] var2) {
        this.enteredDataCheckS();
        this.rkMethodNumber = 1;
        this.nPoints = var2.length;
        ArrayMaths var3 = new ArrayMaths(var2);
        var3 = var3.sort();
        var2 = var3.array_as_double();
        if (var2[0] < this.x0) {
            throw new IllegalArgumentException("Entered points must lie between the previously entered initial and final x values. They may include either or both of these values");
        } else if (var2[this.nPoints - 1] > this.xn) {
            throw new IllegalArgumentException("Entered points must lie between the previously entered initial and final x values. They may include either or both of these values");
        } else {
            ArrayList var4 = new ArrayList();
            if (var2[0] != this.x0) {
                var4.add(this.x0);
            }

            int var5;
            for(var5 = 1; var5 < this.nPoints; ++var5) {
                var4.add(var2[var5]);
            }

            if (var2[this.nPoints - 1] != this.xn) {
                var4.add(this.xn);
            }

            this.nPoints = var4.size();
            this.xArray = new double[this.nPoints];

            for(var5 = 1; var5 < this.nPoints; ++var5) {
                this.xArray[var5] = (Double)var4.get(var5);
            }

            this.nPset = true;
            this.xValues.add(this.x0);
            this.xArraySet = true;
            this.retArray = new double[this.nODE + 1][this.nPoints];
            this.initYarray();
            this.fourthOrder(var1);
            var5 = this.xValues.size();
            double[] var6 = new double[var5];
            double[][] var7 = new double[this.nODE][var5];

            int var8;
            int var10;
            for(var8 = 0; var8 < var5; ++var8) {
                var6[var8] = (Double)this.xValues.get(var8);
                double[] var9 = (double[])this.yValues.get(var8);

                for(var10 = 0; var10 < this.nODE; ++var10) {
                    var7[var10][var8] = var9[var10];
                }
            }

            if (this.xArray[this.nPoints - 1] > var6[var5 - 1]) {
                this.xArray[this.nPoints - 1] = var6[var5 - 1];
            }

            for(var8 = 0; var8 < this.nPoints; ++var8) {
                this.retArray[0][var8] = this.xArray[var8];
            }

            for(var8 = 0; var8 < this.nODE; ++var8) {
                CubicSpline var11 = new CubicSpline(var6, var7[var8]);

                for(var10 = 0; var10 < this.nPoints; ++var10) {
                    this.retArray[var8 + 1][var10] = var11.interpolate(this.xArray[var10]);
                }
            }

            this.nPoints = 0;
            return this.retArray;
        }
    }

    public static double fourthOrder(DerivFunction var0, double var1, double var3, double var5, double var7) {
        RungeKutta var9 = new RungeKutta();
        var9.setInitialValueOfX(var1);
        var9.setFinalValueOfX(var5);
        var9.setInitialValueOfY(var3);
        var9.setStepSize(var7);
        return var9.fourthOrder(var0);
    }

    public static double[][] fourthOrder(DerivFunction var0, int var1, double var2, double var4, double var6, double var8) {
        return fourthOrder(var0, var2, var4, var6, var8, var1);
    }

    public static double[][] fourthOrder(DerivFunction var0, double var1, double var3, double var5, double var7, int var9) {
        RungeKutta var10 = new RungeKutta();
        var10.setInitialValueOfX(var1);
        var10.setFinalValueOfX(var5);
        var10.setInitialValueOfY(var3);
        var10.setStepSize(var7);
        return var10.fourthOrder(var0, var9);
    }

    public static double[][] fourthOrder(DerivFunction var0, double[] var1, double var2, double var4, double var6, double var8) {
        return fourthOrder(var0, var2, var4, var6, var8, var1);
    }

    public static double[][] fourthOrder(DerivFunction var0, double var1, double var3, double var5, double var7, double[] var9) {
        RungeKutta var10 = new RungeKutta();
        var10.setInitialValueOfX(var1);
        var10.setFinalValueOfX(var5);
        var10.setInitialValueOfY(var3);
        var10.setStepSize(var7);
        return var10.fourthOrder(var0, var9);
    }

    public double[] fourthOrder(DerivnFunction var1) {
        this.enteredDataCheckM();
        this.rkMethodNumber = 1;
        double[] var2 = new double[this.nODE];
        double[] var3 = new double[this.nODE];
        double[] var4 = new double[this.nODE];
        double[] var5 = new double[this.nODE];
        double[] var6 = new double[this.nODE];
        double[] var7 = new double[this.nODE];
        double[] var8 = new double[this.nODE];
        double var9 = 0.0D;
        double var11 = (this.xn - this.x0) / this.step;
        var11 = Math.rint(var11);
        int var13 = (int)var11;
        this.nIter = var13;
        double var14 = (this.xn - this.x0) / var11;
        this.xValues.add(this.x0);
        this.yValues.add(this.yy0);

        int var16;
        for(var16 = 0; var16 < this.nODE; ++var16) {
            var6[var16] = this.yy0[var16];
        }

        for(var16 = 0; var16 < var13; ++var16) {
            var9 = this.x0 + (double)var16 * var14;
            var8 = var1.derivn(var9, var6);

            int var17;
            for(var17 = 0; var17 < this.nODE; ++var17) {
                var2[var17] = var14 * var8[var17];
            }

            for(var17 = 0; var17 < this.nODE; ++var17) {
                var7[var17] = var6[var17] + var2[var17] / 2.0D;
            }

            var8 = var1.derivn(var9 + var14 / 2.0D, var7);

            for(var17 = 0; var17 < this.nODE; ++var17) {
                var3[var17] = var14 * var8[var17];
            }

            for(var17 = 0; var17 < this.nODE; ++var17) {
                var7[var17] = var6[var17] + var3[var17] / 2.0D;
            }

            var8 = var1.derivn(var9 + var14 / 2.0D, var7);

            for(var17 = 0; var17 < this.nODE; ++var17) {
                var4[var17] = var14 * var8[var17];
            }

            for(var17 = 0; var17 < this.nODE; ++var17) {
                var7[var17] = var6[var17] + var4[var17];
            }

            var8 = var1.derivn(var9 + var14, var7);

            for(var17 = 0; var17 < this.nODE; ++var17) {
                var5[var17] = var14 * var8[var17];
            }

            for(var17 = 0; var17 < this.nODE; ++var17) {
                var6[var17] += var2[var17] / 6.0D + var3[var17] / 3.0D + var4[var17] / 3.0D + var5[var17] / 6.0D;
            }

            this.xValues.add(var9 + var14);
            this.yValues.add(DeepCopy.copy(var6));
        }

        this.integrationDone = true;
        return var6;
    }

    public double[][] fourthOrder(DerivnFunction var1, int var2) {
        if (var2 < 2) {
            throw new IllegalArgumentException("The number of points, " + var2 + ", must be greater than 1");
        } else {
            this.enteredDataCheckM();
            this.rkMethodNumber = 1;
            this.nPoints = var2;
            this.retArray = new double[this.nODE + 1][this.nPoints];
            this.nPset = true;
            this.calcXarray();
            this.initYarray();
            this.fourthOrder(var1);
            int var3 = this.xValues.size();
            double[] var4 = new double[var3];
            double[][] var5 = new double[this.nODE][var3];

            int var6;
            int var8;
            for(var6 = 0; var6 < var3; ++var6) {
                var4[var6] = (Double)this.xValues.get(var6);
                double[] var7 = (double[])this.yValues.get(var6);

                for(var8 = 0; var8 < this.nODE; ++var8) {
                    var5[var8][var6] = var7[var8];
                }
            }

            if (this.xArray[this.nPoints - 1] > var4[var3 - 1]) {
                this.xArray[this.nPoints - 1] = var4[var3 - 1];
            }

            for(var6 = 0; var6 < this.nPoints; ++var6) {
                this.retArray[0][var6] = this.xArray[var6];
            }

            for(var6 = 0; var6 < this.nODE; ++var6) {
                CubicSpline var9 = new CubicSpline(var4, var5[var6]);

                for(var8 = 0; var8 < this.nPoints; ++var8) {
                    this.retArray[var6 + 1][var8] = var9.interpolate(this.xArray[var8]);
                }
            }

            this.nPoints = 0;
            return this.retArray;
        }
    }

    public double[][] fourthOrder(DerivnFunction var1, double[] var2) {
        this.enteredDataCheckM();
        this.rkMethodNumber = 1;
        this.nPoints = var2.length;
        ArrayMaths var3 = new ArrayMaths(var2);
        var3 = var3.sort();
        var2 = var3.array_as_double();
        if (var2[0] < this.x0) {
            throw new IllegalArgumentException("Entered points must lie between the previously entered initial and final x values. They may include either or both of these values");
        } else if (var2[this.nPoints - 1] > this.xn) {
            throw new IllegalArgumentException("Entered points must lie between the previously entered initial and final x values. They may include either or both of these values");
        } else {
            ArrayList var4 = new ArrayList();
            if (var2[0] != this.x0) {
                var4.add(this.x0);
            }

            int var5;
            for(var5 = 1; var5 < this.nPoints; ++var5) {
                var4.add(var2[var5]);
            }

            if (var2[this.nPoints - 1] != this.xn) {
                var4.add(this.xn);
            }

            this.nPoints = var4.size();
            this.xArray = new double[this.nPoints];

            for(var5 = 1; var5 < this.nPoints; ++var5) {
                this.xArray[var5] = (Double)var4.get(var5);
            }

            this.nPset = true;
            this.xValues.add(this.x0);
            this.xArraySet = true;
            this.retArray = new double[this.nODE + 1][this.nPoints];
            this.initYarray();
            this.fourthOrder(var1);
            var5 = this.xValues.size();
            double[] var6 = new double[var5];
            double[][] var7 = new double[this.nODE][var5];

            int var8;
            int var10;
            for(var8 = 0; var8 < var5; ++var8) {
                var6[var8] = (Double)this.xValues.get(var8);
                double[] var9 = (double[])this.yValues.get(var8);

                for(var10 = 0; var10 < this.nODE; ++var10) {
                    var7[var10][var8] = var9[var10];
                }
            }

            if (this.xArray[this.nPoints - 1] > var6[var5 - 1]) {
                this.xArray[this.nPoints - 1] = var6[var5 - 1];
            }

            for(var8 = 0; var8 < this.nPoints; ++var8) {
                this.retArray[0][var8] = this.xArray[var8];
            }

            for(var8 = 0; var8 < this.nODE; ++var8) {
                CubicSpline var11 = new CubicSpline(var6, var7[var8]);

                for(var10 = 0; var10 < this.nPoints; ++var10) {
                    this.retArray[var8 + 1][var10] = var11.interpolate(this.xArray[var10]);
                }
            }

            this.nPoints = 0;
            return this.retArray;
        }
    }

    public static double[] fourthOrder(DerivnFunction var0, double var1, double[] var3, double var4, double var6) {
        RungeKutta var8 = new RungeKutta();
        var8.setInitialValueOfX(var1);
        var8.setFinalValueOfX(var4);
        var8.setInitialValuesOfY(var3);
        var8.setStepSize(var6);
        return var8.fourthOrder(var0);
    }

    public static double[][] fourthOrder(DerivnFunction var0, int var1, double var2, double[] var4, double var5, double var7) {
        return fourthOrder(var0, var2, var4, var5, var7, var1);
    }

    public static double[][] fourthOrder(DerivnFunction var0, double var1, double[] var3, double var4, double var6, int var8) {
        RungeKutta var9 = new RungeKutta();
        var9.setInitialValueOfX(var1);
        var9.setFinalValueOfX(var4);
        var9.setInitialValuesOfY(var3);
        var9.setStepSize(var6);
        return var9.fourthOrder(var0, var8);
    }

    public static double[][] fourthOrder(DerivnFunction var0, double[] var1, double var2, double[] var4, double var5, double var7) {
        return fourthOrder(var0, var2, var4, var5, var7, var1);
    }

    public static double[][] fourthOrder(DerivnFunction var0, double var1, double[] var3, double var4, double var6, double[] var8) {
        RungeKutta var9 = new RungeKutta();
        var9.setInitialValueOfX(var1);
        var9.setFinalValueOfX(var4);
        var9.setInitialValuesOfY(var3);
        var9.setStepSize(var6);
        return var9.fourthOrder(var0, var8);
    }

    public double cashKarp(DerivFunction var1) {
        this.enteredDataCheckS();
        this.rkMethodNumber = 2;
        double var2 = 0.0D;
        double var4 = 0.0D;
        double var6 = 0.0D;
        double var8 = 0.0D;
        double var10 = 0.0D;
        double var12 = 0.0D;
        double var14 = this.y0;
        double var16 = 0.0D;
        double var18 = 0.0D;
        double var20 = 0.0D;
        double var22 = 0.0D;
        double var24 = this.x0;
        double var26 = 0.0D;
        double var28 = 0.0D;
        double var30 = 0.0D;
        int var32 = 0;
        double[] var33 = new double[1];
        if (this.maxIter == -1) {
            this.maxIter = (int)((double)nStepsMultiplier * (this.xn - this.x0) / this.step);
        }

        double var34 = this.step;
        this.xValues.add(this.x0);
        this.yValues.add(this.yy0);

        while(var24 < this.xn) {
            ++var32;
            if (var32 > this.maxIter) {
                throw new ArithmeticException("Maximum number of iterations exceeded");
            }

            var22 = var1.deriv(var24, var14);
            var2 = var34 * var22;
            var20 = var14 + var2 / 5.0D;
            var22 = var1.deriv(var24 + var34 / 5.0D, var20);
            var4 = var34 * var22;
            var20 = var14 + (3.0D * var2 + 9.0D * var4) / 40.0D;
            var22 = var1.deriv(var24 + 3.0D * var34 / 10.0D, var20);
            var6 = var34 * var22;
            var20 = var14 + (3.0D * var2 - 9.0D * var4 + 12.0D * var6) / 10.0D;
            var22 = var1.deriv(var24 + 3.0D * var34 / 5.0D, var20);
            var8 = var34 * var22;
            var20 = var14 - 11.0D * var2 / 54.0D + 5.0D * var4 / 2.0D - 70.0D * var6 / 27.0D + 35.0D * var8 / 27.0D;
            var22 = var1.deriv(var24 + var34, var20);
            var10 = var34 * var22;
            var20 = var14 + 1631.0D * var2 / 55296.0D + 175.0D * var4 / 512.0D + 575.0D * var6 / 13824.0D + 44275.0D * var8 / 110592.0D + 253.0D * var10 / 4096.0D;
            var22 = var1.deriv(var24 + 7.0D * var34 / 8.0D, var20);
            var12 = var34 * var22;
            var16 = var14 + 2825.0D * var2 / 27648.0D + 18575.0D * var6 / 48384.0D + 13525.0D * var8 / 55296.0D + 277.0D * var10 / 14336.0D + var12 / 4.0D;
            var18 = var14 + 37.0D * var2 / 378.0D + 250.0D * var6 / 621.0D + 125.0D * var8 / 594.0D + 512.0D * var12 / 1771.0D;
            var26 = Math.abs(var18 - var16);
            var30 = var26 / (Math.abs(var16) * this.relTol + this.absTol);
            if (var30 <= 1.0D) {
                var24 += var34;
                var28 = safetyFactor * Math.pow(var30, incrementFactor);
                if (var28 > 4.0D) {
                    var34 *= 4.0D;
                } else if (var28 > 1.0D) {
                    var34 *= var28;
                }

                if (var24 + var34 > this.xn) {
                    var34 = this.xn - var24;
                }

                var14 = var16;
                this.xValues.add(var24);
                var33[0] = var16;
                this.yValues.add(DeepCopy.copy(var33));
            } else {
                var28 = safetyFactor * Math.pow(var30, decrementFactor);
                if (var28 < 0.1D) {
                    var34 *= 0.1D;
                } else {
                    var34 *= var28;
                }
            }
        }

        this.nIter = var32;
        this.integrationDone = true;
        return var14;
    }

    public double[][] cashKarp(DerivFunction var1, int var2) {
        if (var2 < 2) {
            throw new IllegalArgumentException("The number of points, " + var2 + ", must be greater than 1");
        } else {
            this.enteredDataCheckS();
            this.nPoints = var2;
            this.retArray = new double[this.nODE + 1][this.nPoints];
            this.nPset = true;
            this.calcXarray();
            this.initYarray();
            this.cashKarp(var1);
            int var3 = this.xValues.size();
            double[] var4 = new double[var3];
            double[][] var5 = new double[this.nODE][var3];

            int var6;
            int var8;
            for(var6 = 0; var6 < var3; ++var6) {
                var4[var6] = (Double)this.xValues.get(var6);
                double[] var7 = (double[])this.yValues.get(var6);

                for(var8 = 0; var8 < this.nODE; ++var8) {
                    var5[var8][var6] = var7[var8];
                }
            }

            if (this.xArray[this.nPoints - 1] > var4[var3 - 1]) {
                this.xArray[this.nPoints - 1] = var4[var3 - 1];
            }

            for(var6 = 0; var6 < this.nPoints; ++var6) {
                this.retArray[0][var6] = this.xArray[var6];
            }

            for(var6 = 0; var6 < this.nODE; ++var6) {
                CubicSpline var9 = new CubicSpline(var4, var5[var6]);

                for(var8 = 0; var8 < this.nPoints; ++var8) {
                    this.retArray[var6 + 1][var8] = var9.interpolate(this.xArray[var8]);
                }
            }

            this.nPoints = 0;
            return this.retArray;
        }
    }

    public double[][] cashKarp(DerivFunction var1, double[] var2) {
        this.enteredDataCheckS();
        this.nPoints = var2.length;
        ArrayMaths var3 = new ArrayMaths(var2);
        var3 = var3.sort();
        var2 = var3.array_as_double();
        if (var2[0] < this.x0) {
            throw new IllegalArgumentException("Entered points must lie between the previously entered initial and final x values. They may include either or both of these values");
        } else if (var2[this.nPoints - 1] > this.xn) {
            throw new IllegalArgumentException("Entered points must lie between the previously entered initial and final x values. They may include either or both of these values");
        } else {
            ArrayList var4 = new ArrayList();
            if (var2[0] != this.x0) {
                var4.add(this.x0);
            }

            int var5;
            for(var5 = 1; var5 < this.nPoints; ++var5) {
                var4.add(var2[var5]);
            }

            if (var2[this.nPoints - 1] != this.xn) {
                var4.add(this.xn);
            }

            this.nPoints = var4.size();
            this.xArray = new double[this.nPoints];

            for(var5 = 1; var5 < this.nPoints; ++var5) {
                this.xArray[var5] = (Double)var4.get(var5);
            }

            this.nPset = true;
            this.xValues.add(this.x0);
            this.xArraySet = true;
            this.retArray = new double[this.nODE + 1][this.nPoints];
            this.initYarray();
            this.cashKarp(var1);
            var5 = this.xValues.size();
            double[] var6 = new double[var5];
            double[][] var7 = new double[this.nODE][var5];

            int var8;
            int var10;
            for(var8 = 0; var8 < var5; ++var8) {
                var6[var8] = (Double)this.xValues.get(var8);
                double[] var9 = (double[])this.yValues.get(var8);

                for(var10 = 0; var10 < this.nODE; ++var10) {
                    var7[var10][var8] = var9[var10];
                }
            }

            if (this.xArray[this.nPoints - 1] > var6[var5 - 1]) {
                this.xArray[this.nPoints - 1] = var6[var5 - 1];
            }

            for(var8 = 0; var8 < this.nPoints; ++var8) {
                this.retArray[0][var8] = this.xArray[var8];
            }

            for(var8 = 0; var8 < this.nODE; ++var8) {
                CubicSpline var11 = new CubicSpline(var6, var7[var8]);

                for(var10 = 0; var10 < this.nPoints; ++var10) {
                    this.retArray[var8 + 1][var10] = var11.interpolate(this.xArray[var10]);
                }
            }

            this.nPoints = 0;
            return this.retArray;
        }
    }

    public static double cashKarp(DerivFunction var0, double var1, double var3, double var5, double var7, double var9, double var11, int var13) {
        RungeKutta var14 = new RungeKutta();
        var14.setInitialValueOfX(var1);
        var14.setFinalValueOfX(var5);
        var14.setInitialValueOfY(var3);
        var14.setStepSize(var7);
        var14.setToleranceScalingFactor(var11);
        var14.setToleranceAdditionFactor(var9);
        var14.setMaximumIterations(var13);
        return var14.cashKarp(var0);
    }

    public static double cashKarp(DerivFunction var0, double var1, double var3, double var5, double var7, double var9, double var11) {
        int var13 = (int)((double)nStepsMultiplier * (var5 - var1) / var7);
        RungeKutta var14 = new RungeKutta();
        var14.setInitialValueOfX(var1);
        var14.setFinalValueOfX(var5);
        var14.setInitialValueOfY(var3);
        var14.setStepSize(var7);
        var14.setToleranceScalingFactor(var11);
        var14.setToleranceAdditionFactor(var9);
        var14.setMaximumIterations(var13);
        return var14.cashKarp(var0);
    }

    public static double[][] cashKarp(DerivFunction var0, int var1, double var2, double[] var4, double var5, double var7, double var9, double var11, int var13) {
        RungeKutta var14 = new RungeKutta();
        var14.setInitialValueOfX(var2);
        var14.setFinalValueOfX(var5);
        var14.setInitialValuesOfY(var4);
        var14.setStepSize(var7);
        var14.setToleranceScalingFactor(var11);
        var14.setToleranceAdditionFactor(var9);
        var14.setMaximumIterations(var13);
        return var14.cashKarp(var0, var1);
    }

    public static double[][] cashKarp(DerivFunction var0, int var1, double var2, double var4, double var6, double var8, double var10, double var12) {
        int var14 = (int)((double)nStepsMultiplier * (var6 - var2) / var8);
        RungeKutta var15 = new RungeKutta();
        var15.setInitialValueOfX(var2);
        var15.setFinalValueOfX(var6);
        var15.setInitialValueOfY(var4);
        var15.setStepSize(var8);
        var15.setToleranceScalingFactor(var12);
        var15.setToleranceAdditionFactor(var10);
        var15.setMaximumIterations(var14);
        return var15.cashKarp(var0, var1);
    }

    public static double[][] cashKarp(DerivFunction var0, double[] var1, double var2, double var4, double var6, double var8, double var10, double var12, int var14) {
        RungeKutta var15 = new RungeKutta();
        var15.setInitialValueOfX(var2);
        var15.setFinalValueOfX(var6);
        var15.setInitialValueOfY(var4);
        var15.setStepSize(var8);
        var15.setToleranceScalingFactor(var12);
        var15.setToleranceAdditionFactor(var10);
        var15.setMaximumIterations(var14);
        return var15.cashKarp(var0, var1);
    }

    public static double[][] cashKarp(DerivFunction var0, double[] var1, double var2, double var4, double var6, double var8, double var10, double var12) {
        int var14 = (int)((double)nStepsMultiplier * (var6 - var2) / var8);
        RungeKutta var15 = new RungeKutta();
        var15.setInitialValueOfX(var2);
        var15.setFinalValueOfX(var6);
        var15.setInitialValueOfY(var4);
        var15.setStepSize(var8);
        var15.setToleranceScalingFactor(var12);
        var15.setToleranceAdditionFactor(var10);
        var15.setMaximumIterations(var14);
        return var15.cashKarp(var0, var1);
    }

    public double[] cashKarp(DerivnFunction var1) {
        this.enteredDataCheckM();
        this.rkMethodNumber = 2;
        double[] var2 = new double[this.nODE];
        double[] var3 = new double[this.nODE];
        double[] var4 = new double[this.nODE];
        double[] var5 = new double[this.nODE];
        double[] var6 = new double[this.nODE];
        double[] var7 = new double[this.nODE];
        double[] var8 = new double[this.nODE];
        double[] var9 = new double[this.nODE];
        double[] var10 = new double[this.nODE];
        double[] var11 = new double[this.nODE];
        double[] var12 = new double[this.nODE];
        double var13 = 0.0D;
        double var15 = 0.0D;
        double var17 = 0.0D;
        double var19 = 1.0D;
        int var21 = 0;
        this.xValues.add(this.x0);
        this.yValues.add(this.yy0);

        for(int var22 = 0; var22 < this.nODE; ++var22) {
            var8[var22] = this.yy0[var22];
        }

        double var27 = this.x0;
        if (this.maxIter == -1) {
            this.maxIter = (int)((double)nStepsMultiplier * (this.xn - this.x0) / this.step);
        }

        double var24 = this.step;

        while(var27 < this.xn) {
            ++var21;
            if (var21 > this.maxIter) {
                throw new ArithmeticException("Maximum number of iterations exceeded");
            }

            var12 = var1.derivn(var27, var8);

            int var26;
            for(var26 = 0; var26 < this.nODE; ++var26) {
                var2[var26] = var24 * var12[var26];
            }

            for(var26 = 0; var26 < this.nODE; ++var26) {
                var11[var26] = var8[var26] + var2[var26] / 5.0D;
            }

            var12 = var1.derivn(var27 + var24 / 5.0D, var11);

            for(var26 = 0; var26 < this.nODE; ++var26) {
                var3[var26] = var24 * var12[var26];
            }

            for(var26 = 0; var26 < this.nODE; ++var26) {
                var11[var26] = var8[var26] + (3.0D * var2[var26] + 9.0D * var3[var26]) / 40.0D;
            }

            var12 = var1.derivn(var27 + 3.0D * var24 / 10.0D, var11);

            for(var26 = 0; var26 < this.nODE; ++var26) {
                var4[var26] = var24 * var12[var26];
            }

            for(var26 = 0; var26 < this.nODE; ++var26) {
                var11[var26] = var8[var26] + (3.0D * var2[var26] - 9.0D * var3[var26] + 12.0D * var4[var26]) / 10.0D;
            }

            var12 = var1.derivn(var27 + 3.0D * var24 / 5.0D, var11);

            for(var26 = 0; var26 < this.nODE; ++var26) {
                var5[var26] = var24 * var12[var26];
            }

            for(var26 = 0; var26 < this.nODE; ++var26) {
                var11[var26] = var8[var26] - 11.0D * var2[var26] / 54.0D + 5.0D * var3[var26] / 2.0D - 70.0D * var4[var26] / 27.0D + 35.0D * var5[var26] / 27.0D;
            }

            var12 = var1.derivn(var27 + var24, var11);

            for(var26 = 0; var26 < this.nODE; ++var26) {
                var6[var26] = var24 * var12[var26];
            }

            for(var26 = 0; var26 < this.nODE; ++var26) {
                var11[var26] = var8[var26] + 1631.0D * var2[var26] / 55296.0D + 175.0D * var3[var26] / 512.0D + 575.0D * var4[var26] / 13824.0D + 44275.0D * var5[var26] / 110592.0D + 253.0D * var6[var26] / 4096.0D;
            }

            var12 = var1.derivn(var27 + 7.0D * var24 / 8.0D, var11);

            for(var26 = 0; var26 < this.nODE; ++var26) {
                var7[var26] = var24 * var12[var26];
            }

            var15 = 0.0D;

            for(var26 = 0; var26 < this.nODE; ++var26) {
                var10[var26] = var8[var26] + 2825.0D * var2[var26] / 27648.0D + 18575.0D * var4[var26] / 48384.0D + 13525.0D * var5[var26] / 55296.0D + 277.0D * var6[var26] / 14336.0D + var7[var26] / 4.0D;
                var9[var26] = var8[var26] + 37.0D * var2[var26] / 378.0D + 250.0D * var4[var26] / 621.0D + 125.0D * var5[var26] / 594.0D + 512.0D * var7[var26] / 1771.0D;
                var13 = Math.abs(var9[var26] - var10[var26]);
                var19 = Math.abs(var10[var26]) * this.relTol + this.absTol;
                var15 = Math.max(var15, var13 / var19);
            }

            if (var15 <= 1.0D) {
                var27 += var24;
                var17 = safetyFactor * Math.pow(var15, incrementFactor);
                if (var17 > 4.0D) {
                    var24 *= 4.0D;
                } else if (var17 > 1.0D) {
                    var24 *= var17;
                }

                if (var27 + var24 > this.xn) {
                    var24 = this.xn - var27;
                }

                var8 = DeepCopy.copy(var10);
                this.xValues.add(var27);
                this.yValues.add(DeepCopy.copy(var8));
            } else {
                var17 = safetyFactor * Math.pow(var15, decrementFactor);
                if (var17 < 0.1D) {
                    var24 *= 0.1D;
                } else {
                    var24 *= var17;
                }
            }
        }

        this.nIter = var21;
        this.integrationDone = true;
        return var8;
    }

    public double[][] cashKarp(DerivnFunction var1, int var2) {
        if (var2 < 2) {
            throw new IllegalArgumentException("The number of points, " + var2 + ", must be greater than 1");
        } else {
            this.enteredDataCheckM();
            this.nPoints = var2;
            this.retArray = new double[this.nODE + 1][this.nPoints];
            this.nPset = true;
            this.calcXarray();
            this.initYarray();
            this.cashKarp(var1);
            int var3 = this.xValues.size();
            double[] var4 = new double[var3];
            double[][] var5 = new double[this.nODE][var3];

            int var6;
            int var8;
            for(var6 = 0; var6 < var3; ++var6) {
                var4[var6] = (Double)this.xValues.get(var6);
                double[] var7 = (double[])this.yValues.get(var6);

                for(var8 = 0; var8 < this.nODE; ++var8) {
                    var5[var8][var6] = var7[var8];
                }
            }

            if (this.xArray[this.nPoints - 1] > var4[var3 - 1]) {
                this.xArray[this.nPoints - 1] = var4[var3 - 1];
            }

            for(var6 = 0; var6 < this.nPoints; ++var6) {
                this.retArray[0][var6] = this.xArray[var6];
            }

            for(var6 = 0; var6 < this.nODE; ++var6) {
                CubicSpline var9 = new CubicSpline(var4, var5[var6]);

                for(var8 = 0; var8 < this.nPoints; ++var8) {
                    this.retArray[var6 + 1][var8] = var9.interpolate(this.xArray[var8]);
                }
            }

            this.nPoints = 0;
            return this.retArray;
        }
    }

    public double[][] cashKarp(DerivnFunction var1, double[] var2) {
        this.enteredDataCheckM();
        this.nPoints = var2.length;
        ArrayMaths var3 = new ArrayMaths(var2);
        var3 = var3.sort();
        var2 = var3.array_as_double();
        if (var2[0] < this.x0) {
            throw new IllegalArgumentException("Entered points must lie between the previously entered initial and final x values. They may include either or both of these values");
        } else if (var2[this.nPoints - 1] > this.xn) {
            throw new IllegalArgumentException("Entered points must lie between the previously entered initial and final x values. They may include either or both of these values");
        } else {
            ArrayList var4 = new ArrayList();
            if (var2[0] != this.x0) {
                var4.add(this.x0);
            }

            int var5;
            for(var5 = 1; var5 < this.nPoints; ++var5) {
                var4.add(var2[var5]);
            }

            if (var2[this.nPoints - 1] != this.xn) {
                var4.add(this.xn);
            }

            this.nPoints = var4.size();
            this.xArray = new double[this.nPoints];

            for(var5 = 1; var5 < this.nPoints; ++var5) {
                this.xArray[var5] = (Double)var4.get(var5);
            }

            this.nPset = true;
            this.xValues.add(this.x0);
            this.xArraySet = true;
            this.retArray = new double[this.nODE + 1][this.nPoints];
            this.initYarray();
            this.cashKarp(var1);
            var5 = this.xValues.size();
            double[] var6 = new double[var5];
            double[][] var7 = new double[this.nODE][var5];

            int var8;
            int var10;
            for(var8 = 0; var8 < var5; ++var8) {
                var6[var8] = (Double)this.xValues.get(var8);
                double[] var9 = (double[])this.yValues.get(var8);

                for(var10 = 0; var10 < this.nODE; ++var10) {
                    var7[var10][var8] = var9[var10];
                }
            }

            if (this.xArray[this.nPoints - 1] > var6[var5 - 1]) {
                this.xArray[this.nPoints - 1] = var6[var5 - 1];
            }

            for(var8 = 0; var8 < this.nPoints; ++var8) {
                this.retArray[0][var8] = this.xArray[var8];
            }

            for(var8 = 0; var8 < this.nODE; ++var8) {
                CubicSpline var11 = new CubicSpline(var6, var7[var8]);

                for(var10 = 0; var10 < this.nPoints; ++var10) {
                    this.retArray[var8 + 1][var10] = var11.interpolate(this.xArray[var10]);
                }
            }

            this.nPoints = 0;
            return this.retArray;
        }
    }

    public static double[] cashKarp(DerivnFunction var0, double var1, double[] var3, double var4, double var6, double var8, double var10, int var12) {
        RungeKutta var13 = new RungeKutta();
        var13.setInitialValueOfX(var1);
        var13.setFinalValueOfX(var4);
        var13.setInitialValuesOfY(var3);
        var13.setStepSize(var6);
        var13.setToleranceScalingFactor(var10);
        var13.setToleranceAdditionFactor(var8);
        var13.setMaximumIterations(var12);
        return var13.cashKarp(var0);
    }

    public static double[] cashKarp(DerivnFunction var0, double var1, double[] var3, double var4, double var6, double var8, double var10) {
        double var12 = (var4 - var1) / var6;
        int var14 = (int)var12 * nStepsMultiplier;
        return cashKarp(var0, var1, var3, var4, var6, var8, var10, var14);
    }

    public static double[][] cashKarp(DerivnFunction var0, int var1, double var2, double[] var4, double var5, double var7, double var9, double var11, int var13) {
        RungeKutta var14 = new RungeKutta();
        var14.setInitialValueOfX(var2);
        var14.setFinalValueOfX(var5);
        var14.setInitialValuesOfY(var4);
        var14.setStepSize(var7);
        var14.setToleranceScalingFactor(var11);
        var14.setToleranceAdditionFactor(var9);
        var14.setMaximumIterations(var13);
        return var14.cashKarp(var0, var1);
    }

    public static double[][] cashKarp(DerivnFunction var0, int var1, double var2, double[] var4, double var5, double var7, double var9, double var11) {
        double var13 = (var5 - var2) / var7;
        int var15 = (int)var13 * nStepsMultiplier;
        return cashKarp(var0, var1, var2, var4, var5, var7, var9, var11, var15);
    }

    public static double[][] cashKarp(DerivnFunction var0, double[] var1, double var2, double[] var4, double var5, double var7, double var9, double var11, int var13) {
        RungeKutta var14 = new RungeKutta();
        var14.setInitialValueOfX(var2);
        var14.setFinalValueOfX(var5);
        var14.setInitialValuesOfY(var4);
        var14.setStepSize(var7);
        var14.setToleranceScalingFactor(var11);
        var14.setToleranceAdditionFactor(var9);
        var14.setMaximumIterations(var13);
        return var14.cashKarp(var0, var1);
    }

    public static double[][] cashKarp(DerivnFunction var0, double[] var1, double var2, double[] var4, double var5, double var7, double var9, double var11) {
        double var13 = (var5 - var2) / var7;
        int var15 = (int)var13 * nStepsMultiplier;
        return cashKarp(var0, var1, var2, var4, var5, var7, var9, var11, var15);
    }

    public double fehlberg(DerivFunction var1) {
        this.enteredDataCheckS();
        this.rkMethodNumber = 3;
        double var2 = 0.0D;
        double var4 = 0.0D;
        double var6 = 0.0D;
        double var8 = 0.0D;
        double var10 = 0.0D;
        double var12 = 0.0D;
        double var14 = this.x0;
        double var16 = this.y0;
        double var18 = 0.0D;
        double var20 = 0.0D;
        double var22 = 0.0D;
        double var24 = 0.0D;
        double var26 = 0.0D;
        int var28 = 0;
        double[] var29 = new double[1];
        if (this.maxIter == -1) {
            this.maxIter = (int)((double)nStepsMultiplier * (this.xn - this.x0) / this.step);
        }

        double var30 = this.step;
        this.xValues.add(this.x0);
        this.yValues.add(this.yy0);

        while(var14 < this.xn) {
            ++var28;
            if (var28 > this.maxIter) {
                throw new ArithmeticException("Maximum number of iterations exceeded");
            }

            var2 = var30 * var1.deriv(var14, var16);
            var4 = var30 * var1.deriv(var14 + var30 / 4.0D, var16 + var2 / 4.0D);
            var6 = var30 * var1.deriv(var14 + 3.0D * var30 / 8.0D, var16 + (3.0D * var2 + 9.0D * var4) / 32.0D);
            var8 = var30 * var1.deriv(var14 + 12.0D * var30 / 13.0D, var16 + (1932.0D * var2 - 7200.0D * var4 + 7296.0D * var6) / 2197.0D);
            var10 = var30 * var1.deriv(var14 + var30, var16 + 439.0D * var2 / 216.0D - 8.0D * var4 + 3680.0D * var6 / 513.0D - 845.0D * var8 / 4104.0D);
            var12 = var30 * var1.deriv(var14 + 0.5D * var30, var16 - 8.0D * var2 / 27.0D + 2.0D * var4 - 3544.0D * var6 / 2565.0D + 1859.0D * var8 / 4104.0D - 11.0D * var10 / 40.0D);
            var18 = var16 + 25.0D * var2 / 216.0D + 1408.0D * var6 / 2565.0D + 2197.0D * var8 / 4104.0D - var10 / 5.0D;
            var20 = var16 + 16.0D * var2 / 135.0D + 6656.0D * var6 / 12825.0D + 28561.0D * var8 / 56430.0D - 9.0D * var10 / 50.0D + 2.0D * var12 / 55.0D;
            var22 = Math.abs(var20 - var18);
            var26 = var22 / (Math.abs(var18) * this.relTol + this.absTol);
            if (var26 <= 1.0D) {
                var14 += var30;
                var24 = safetyFactor * Math.pow(var26, incrementFactor);
                if (var24 > 4.0D) {
                    var30 *= 4.0D;
                } else if (var24 < 1.0D) {
                    var30 *= var24;
                }

                if (var14 + var30 > this.xn) {
                    var30 = this.xn - var14;
                }

                var16 = var18;
                this.xValues.add(var14);
                var29[0] = var18;
                this.yValues.add(DeepCopy.copy(var29));
            } else {
                var24 = safetyFactor * Math.pow(var26, decrementFactor);
                if (var24 < 0.1D) {
                    var30 *= 0.1D;
                } else {
                    var30 *= var24;
                }
            }
        }

        this.nIter = var28;
        this.integrationDone = true;
        return var16;
    }

    public double[][] fehlberg(DerivFunction var1, int var2) {
        if (var2 < 2) {
            throw new IllegalArgumentException("The number of points, " + var2 + ", must be greater than 1");
        } else {
            this.enteredDataCheckS();
            this.nPoints = var2;
            this.retArray = new double[this.nODE + 1][this.nPoints];
            this.nPset = true;
            this.calcXarray();
            this.initYarray();
            this.fehlberg(var1);
            int var3 = this.xValues.size();
            double[] var4 = new double[var3];
            double[][] var5 = new double[this.nODE][var3];

            int var6;
            int var8;
            for(var6 = 0; var6 < var3; ++var6) {
                var4[var6] = (Double)this.xValues.get(var6);
                double[] var7 = (double[])this.yValues.get(var6);

                for(var8 = 0; var8 < this.nODE; ++var8) {
                    var5[var8][var6] = var7[var8];
                }
            }

            if (this.xArray[this.nPoints - 1] > var4[var3 - 1]) {
                this.xArray[this.nPoints - 1] = var4[var3 - 1];
            }

            for(var6 = 0; var6 < this.nPoints; ++var6) {
                this.retArray[0][var6] = this.xArray[var6];
            }

            for(var6 = 0; var6 < this.nODE; ++var6) {
                CubicSpline var9 = new CubicSpline(var4, var5[var6]);

                for(var8 = 0; var8 < this.nPoints; ++var8) {
                    this.retArray[var6 + 1][var8] = var9.interpolate(this.xArray[var8]);
                }
            }

            this.nPoints = 0;
            return this.retArray;
        }
    }

    public double[][] fehlberg(DerivFunction var1, double[] var2) {
        this.enteredDataCheckS();
        this.nPoints = var2.length;
        ArrayMaths var3 = new ArrayMaths(var2);
        var3 = var3.sort();
        var2 = var3.array_as_double();
        if (var2[0] < this.x0) {
            throw new IllegalArgumentException("Entered points must lie between the previously entered initial and final x values. They may include either or both of these values");
        } else if (var2[this.nPoints - 1] > this.xn) {
            throw new IllegalArgumentException("Entered points must lie between the previously entered initial and final x values. They may include either or both of these values");
        } else {
            ArrayList var4 = new ArrayList();
            if (var2[0] != this.x0) {
                var4.add(this.x0);
            }

            int var5;
            for(var5 = 1; var5 < this.nPoints; ++var5) {
                var4.add(var2[var5]);
            }

            if (var2[this.nPoints - 1] != this.xn) {
                var4.add(this.xn);
            }

            this.nPoints = var4.size();
            this.xArray = new double[this.nPoints];

            for(var5 = 1; var5 < this.nPoints; ++var5) {
                this.xArray[var5] = (Double)var4.get(var5);
            }

            this.nPset = true;
            this.xValues.add(this.x0);
            this.xArraySet = true;
            this.retArray = new double[this.nODE + 1][this.nPoints];
            this.initYarray();
            this.fehlberg(var1);
            var5 = this.xValues.size();
            double[] var6 = new double[var5];
            double[][] var7 = new double[this.nODE][var5];

            int var8;
            int var10;
            for(var8 = 0; var8 < var5; ++var8) {
                var6[var8] = (Double)this.xValues.get(var8);
                double[] var9 = (double[])this.yValues.get(var8);

                for(var10 = 0; var10 < this.nODE; ++var10) {
                    var7[var10][var8] = var9[var10];
                }
            }

            if (this.xArray[this.nPoints - 1] > var6[var5 - 1]) {
                this.xArray[this.nPoints - 1] = var6[var5 - 1];
            }

            for(var8 = 0; var8 < this.nPoints; ++var8) {
                this.retArray[0][var8] = this.xArray[var8];
            }

            for(var8 = 0; var8 < this.nODE; ++var8) {
                CubicSpline var11 = new CubicSpline(var6, var7[var8]);

                for(var10 = 0; var10 < this.nPoints; ++var10) {
                    this.retArray[var8 + 1][var10] = var11.interpolate(this.xArray[var10]);
                }
            }

            this.nPoints = 0;
            return this.retArray;
        }
    }

    public static double fehlberg(DerivFunction var0, double var1, double var3, double var5, double var7, double var9, double var11, int var13) {
        RungeKutta var14 = new RungeKutta();
        var14.setInitialValueOfX(var1);
        var14.setFinalValueOfX(var5);
        var14.setInitialValueOfY(var3);
        var14.setStepSize(var7);
        var14.setToleranceScalingFactor(var11);
        var14.setToleranceAdditionFactor(var9);
        var14.setMaximumIterations(var13);
        return var14.fehlberg(var0);
    }

    public static double fehlberg(DerivFunction var0, double var1, double var3, double var5, double var7, double var9, double var11) {
        double var13 = (var5 - var1) / var7;
        int var15 = (int)var13 * nStepsMultiplier;
        return fehlberg(var0, var1, var3, var5, var7, var9, var11, var15);
    }

    public static double[][] fehlberg(DerivFunction var0, int var1, double var2, double[] var4, double var5, double var7, double var9, double var11, int var13) {
        RungeKutta var14 = new RungeKutta();
        var14.setInitialValueOfX(var2);
        var14.setFinalValueOfX(var5);
        var14.setInitialValuesOfY(var4);
        var14.setStepSize(var7);
        var14.setToleranceScalingFactor(var11);
        var14.setToleranceAdditionFactor(var9);
        var14.setMaximumIterations(var13);
        return var14.fehlberg(var0, var1);
    }

    public static double[][] fehlberg(DerivFunction var0, int var1, double var2, double var4, double var6, double var8, double var10, double var12) {
        int var14 = (int)((double)nStepsMultiplier * (var6 - var2) / var8);
        RungeKutta var15 = new RungeKutta();
        var15.setInitialValueOfX(var2);
        var15.setFinalValueOfX(var6);
        var15.setInitialValueOfY(var4);
        var15.setStepSize(var8);
        var15.setToleranceScalingFactor(var12);
        var15.setToleranceAdditionFactor(var10);
        var15.setMaximumIterations(var14);
        return var15.fehlberg(var0, var1);
    }

    public static double[][] fehlberg(DerivFunction var0, double[] var1, double var2, double var4, double var6, double var8, double var10, double var12, int var14) {
        RungeKutta var15 = new RungeKutta();
        var15.setInitialValueOfX(var2);
        var15.setFinalValueOfX(var6);
        var15.setInitialValueOfY(var4);
        var15.setStepSize(var8);
        var15.setToleranceScalingFactor(var12);
        var15.setToleranceAdditionFactor(var10);
        var15.setMaximumIterations(var14);
        return var15.fehlberg(var0, var1);
    }

    public static double[][] fehlberg(DerivFunction var0, double[] var1, double var2, double var4, double var6, double var8, double var10, double var12) {
        int var14 = (int)((double)nStepsMultiplier * (var6 - var2) / var8);
        RungeKutta var15 = new RungeKutta();
        var15.setInitialValueOfX(var2);
        var15.setFinalValueOfX(var6);
        var15.setInitialValueOfY(var4);
        var15.setStepSize(var8);
        var15.setToleranceScalingFactor(var12);
        var15.setToleranceAdditionFactor(var10);
        var15.setMaximumIterations(var14);
        return var15.fehlberg(var0, var1);
    }

    public double[] fehlberg(DerivnFunction var1) {
        this.enteredDataCheckM();
        this.rkMethodNumber = 3;
        double[] var2 = new double[this.nODE];
        double[] var3 = new double[this.nODE];
        double[] var4 = new double[this.nODE];
        double[] var5 = new double[this.nODE];
        double[] var6 = new double[this.nODE];
        double[] var7 = new double[this.nODE];
        double[] var8 = new double[this.nODE];
        double[] var9 = new double[this.nODE];
        double[] var10 = new double[this.nODE];
        double[] var11 = new double[this.nODE];
        double[] var12 = new double[this.nODE];
        double var13 = 0.0D;
        double var15 = 0.0D;
        double var17 = 0.0D;
        double var19 = 1.0D;
        int var21 = 0;
        this.xValues.add(this.x0);
        this.yValues.add(this.yy0);

        for(int var22 = 0; var22 < this.nODE; ++var22) {
            var8[var22] = this.yy0[var22];
        }

        double var27 = this.x0;
        if (this.maxIter == -1) {
            this.maxIter = (int)((double)nStepsMultiplier * (this.xn - this.x0) / this.step);
        }

        double var24 = this.step;

        while(var27 < this.xn) {
            ++var21;
            if (var21 > this.maxIter) {
                throw new ArithmeticException("Maximum number of iterations exceeded");
            }

            var12 = var1.derivn(var27, var8);

            int var26;
            for(var26 = 0; var26 < this.nODE; ++var26) {
                var2[var26] = var24 * var12[var26];
            }

            for(var26 = 0; var26 < this.nODE; ++var26) {
                var11[var26] = var8[var26] + var2[var26] / 4.0D;
            }

            var12 = var1.derivn(var27 + var24 / 4.0D, var11);

            for(var26 = 0; var26 < this.nODE; ++var26) {
                var3[var26] = var24 * var12[var26];
            }

            for(var26 = 0; var26 < this.nODE; ++var26) {
                var11[var26] = var8[var26] + (3.0D * var2[var26] + 9.0D * var3[var26]) / 32.0D;
            }

            var12 = var1.derivn(var27 + 3.0D * var24 / 8.0D, var11);

            for(var26 = 0; var26 < this.nODE; ++var26) {
                var4[var26] = var24 * var12[var26];
            }

            for(var26 = 0; var26 < this.nODE; ++var26) {
                var11[var26] = var8[var26] + (1932.0D * var2[var26] - 7200.0D * var3[var26] + 7296.0D * var4[var26]) / 2197.0D;
            }

            var12 = var1.derivn(var27 + 12.0D * var24 / 13.0D, var11);

            for(var26 = 0; var26 < this.nODE; ++var26) {
                var5[var26] = var24 * var12[var26];
            }

            for(var26 = 0; var26 < this.nODE; ++var26) {
                var11[var26] = var8[var26] + 439.0D * var2[var26] / 216.0D - 8.0D * var3[var26] + 3680.0D * var4[var26] / 513.0D - 845.0D * var5[var26] / 4104.0D;
            }

            var12 = var1.derivn(var27 + var24, var11);

            for(var26 = 0; var26 < this.nODE; ++var26) {
                var6[var26] = var24 * var12[var26];
            }

            for(var26 = 0; var26 < this.nODE; ++var26) {
                var11[var26] = var8[var26] - 8.0D * var2[var26] / 27.0D + 2.0D * var3[var26] - 3544.0D * var4[var26] / 2565.0D + 1859.0D * var5[var26] / 4104.0D - 11.0D * var6[var26] / 40.0D;
            }

            var12 = var1.derivn(var27 + 0.5D * var24, var11);

            for(var26 = 0; var26 < this.nODE; ++var26) {
                var7[var26] = var24 * var12[var26];
            }

            var15 = 0.0D;

            for(var26 = 0; var26 < this.nODE; ++var26) {
                var10[var26] = var8[var26] + 25.0D * var2[var26] / 216.0D + 1408.0D * var4[var26] / 2565.0D + 2197.0D * var5[var26] / 4104.0D - var6[var26] / 5.0D;
                var9[var26] = var8[var26] + 16.0D * var2[var26] / 135.0D + 6656.0D * var4[var26] / 12825.0D + 28561.0D * var5[var26] / 56430.0D - 9.0D * var6[var26] / 50.0D + 2.0D * var7[var26] / 55.0D;
                var13 = Math.abs(var9[var26] - var10[var26]);
                var19 = var10[var26] * this.relTol + this.absTol;
                var15 = Math.max(var15, var13 / var19);
            }

            if (var15 <= 1.0D) {
                var27 += var24;
                var17 = safetyFactor * Math.pow(var15, incrementFactor);
                if (var17 > 4.0D) {
                    var24 *= 4.0D;
                } else if (var17 > 1.0D) {
                    var24 *= var17;
                }

                if (var27 + var24 > this.xn) {
                    var24 = this.xn - var27;
                }

                var8 = DeepCopy.copy(var10);
                this.xValues.add(var27);
                this.yValues.add(DeepCopy.copy(var8));
            } else {
                var17 = safetyFactor * Math.pow(var15, decrementFactor);
                if (var17 < 0.1D) {
                    var24 *= 0.1D;
                } else {
                    var24 *= var17;
                }
            }
        }

        this.nIter = var21;
        this.integrationDone = true;
        return var8;
    }

    public double[][] fehlberg(DerivnFunction var1, int var2) {
        if (var2 < 2) {
            throw new IllegalArgumentException("The number of points, " + var2 + ", must be greater than 1");
        } else {
            this.enteredDataCheckM();
            this.nPoints = var2;
            this.retArray = new double[this.nODE + 1][this.nPoints];
            this.nPset = true;
            this.calcXarray();
            this.initYarray();
            this.fehlberg(var1);
            int var3 = this.xValues.size();
            double[] var4 = new double[var3];
            double[][] var5 = new double[this.nODE][var3];

            int var6;
            int var8;
            for(var6 = 0; var6 < var3; ++var6) {
                var4[var6] = (Double)this.xValues.get(var6);
                double[] var7 = (double[])this.yValues.get(var6);

                for(var8 = 0; var8 < this.nODE; ++var8) {
                    var5[var8][var6] = var7[var8];
                }
            }

            if (this.xArray[this.nPoints - 1] > var4[var3 - 1]) {
                this.xArray[this.nPoints - 1] = var4[var3 - 1];
            }

            for(var6 = 0; var6 < this.nPoints; ++var6) {
                this.retArray[0][var6] = this.xArray[var6];
            }

            for(var6 = 0; var6 < this.nODE; ++var6) {
                CubicSpline var9 = new CubicSpline(var4, var5[var6]);

                for(var8 = 0; var8 < this.nPoints; ++var8) {
                    this.retArray[var6 + 1][var8] = var9.interpolate(this.xArray[var8]);
                }
            }

            this.nPoints = 0;
            return this.retArray;
        }
    }

    public double[][] fehlberg(DerivnFunction var1, double[] var2) {
        this.enteredDataCheckM();
        this.rkMethodNumber = 3;
        this.nPoints = var2.length;
        ArrayMaths var3 = new ArrayMaths(var2);
        var3 = var3.sort();
        var2 = var3.array_as_double();
        if (var2[0] < this.x0) {
            throw new IllegalArgumentException("Entered points must lie between the previously entered initial and final x values. They may include either or both of these values");
        } else if (var2[this.nPoints - 1] > this.xn) {
            throw new IllegalArgumentException("Entered points must lie between the previously entered initial and final x values. They may include either or both of these values");
        } else {
            ArrayList var4 = new ArrayList();
            if (var2[0] != this.x0) {
                var4.add(this.x0);
            }

            int var5;
            for(var5 = 1; var5 < this.nPoints; ++var5) {
                var4.add(var2[var5]);
            }

            if (var2[this.nPoints - 1] != this.xn) {
                var4.add(this.xn);
            }

            this.nPoints = var4.size();
            this.xArray = new double[this.nPoints];

            for(var5 = 1; var5 < this.nPoints; ++var5) {
                this.xArray[var5] = (Double)var4.get(var5);
            }

            this.nPset = true;
            this.xValues.add(this.x0);
            this.xArraySet = true;
            this.retArray = new double[this.nODE + 1][this.nPoints];
            this.initYarray();
            this.fehlberg(var1);
            var5 = this.xValues.size();
            double[] var6 = new double[var5];
            double[][] var7 = new double[this.nODE][var5];

            int var8;
            int var10;
            for(var8 = 0; var8 < var5; ++var8) {
                var6[var8] = (Double)this.xValues.get(var8);
                double[] var9 = (double[])this.yValues.get(var8);

                for(var10 = 0; var10 < this.nODE; ++var10) {
                    var7[var10][var8] = var9[var10];
                }
            }

            if (this.xArray[this.nPoints - 1] > var6[var5 - 1]) {
                this.xArray[this.nPoints - 1] = var6[var5 - 1];
            }

            for(var8 = 0; var8 < this.nPoints; ++var8) {
                this.retArray[0][var8] = this.xArray[var8];
            }

            for(var8 = 0; var8 < this.nODE; ++var8) {
                CubicSpline var11 = new CubicSpline(var6, var7[var8]);

                for(var10 = 0; var10 < this.nPoints; ++var10) {
                    this.retArray[var8 + 1][var10] = var11.interpolate(this.xArray[var10]);
                }
            }

            this.nPoints = 0;
            return this.retArray;
        }
    }

    public static double[] fehlberg(DerivnFunction var0, double var1, double[] var3, double var4, double var6, double var8, double var10, int var12) {
        RungeKutta var13 = new RungeKutta();
        var13.setInitialValueOfX(var1);
        var13.setFinalValueOfX(var4);
        var13.setInitialValuesOfY(var3);
        var13.setStepSize(var6);
        var13.setToleranceScalingFactor(var10);
        var13.setToleranceAdditionFactor(var8);
        var13.setMaximumIterations(var12);
        return var13.fehlberg(var0);
    }

    public static double[] fehlberg(DerivnFunction var0, double var1, double[] var3, double var4, double var6, double var8, double var10) {
        double var12 = (var4 - var1) / var6;
        int var14 = (int)var12 * nStepsMultiplier;
        return fehlberg(var0, var1, var3, var4, var6, var8, var10, var14);
    }

    public static double[][] fehlberg(DerivnFunction var0, int var1, double var2, double[] var4, double var5, double var7, double var9, double var11, int var13) {
        RungeKutta var14 = new RungeKutta();
        var14.setInitialValueOfX(var2);
        var14.setFinalValueOfX(var5);
        var14.setInitialValuesOfY(var4);
        var14.setStepSize(var7);
        var14.setToleranceScalingFactor(var11);
        var14.setToleranceAdditionFactor(var9);
        var14.setMaximumIterations(var13);
        return var14.fehlberg(var0, var1);
    }

    public static double[][] fehlberg(DerivnFunction var0, int var1, double var2, double[] var4, double var5, double var7, double var9, double var11) {
        double var13 = (var5 - var2) / var7;
        int var15 = (int)var13 * nStepsMultiplier;
        return fehlberg(var0, var1, var2, var4, var5, var7, var9, var11, var15);
    }

    public static double[][] fehlberg(DerivnFunction var0, double[] var1, double var2, double[] var4, double var5, double var7, double var9, double var11, int var13) {
        RungeKutta var14 = new RungeKutta();
        var14.setInitialValueOfX(var2);
        var14.setFinalValueOfX(var5);
        var14.setInitialValuesOfY(var4);
        var14.setStepSize(var7);
        var14.setToleranceScalingFactor(var11);
        var14.setToleranceAdditionFactor(var9);
        var14.setMaximumIterations(var13);
        return var14.fehlberg(var0, var1);
    }

    public static double[][] fehlberg(DerivnFunction var0, double[] var1, double var2, double[] var4, double var5, double var7, double var9, double var11) {
        double var13 = (var5 - var2) / var7;
        int var15 = (int)var13 * nStepsMultiplier;
        return fehlberg(var0, var1, var2, var4, var5, var7, var9, var11, var15);
    }

    public void plot() {
        if (this.retArray == null) {
            throw new IllegalArgumentException("No multiple points output method has been called");
        } else {
            PlotGraph var1 = new PlotGraph(DeepCopy.copy(this.retArray));
            var1.setXaxisLegend("x values");
            var1.setYaxisLegend("y values");
            var1.setGraphTitle(this.rkMethods[this.rkMethodNumber]);
            var1.setFrame();
        }
    }

    public void plotSteps() {
        if (!this.integrationDone) {
            throw new IllegalArgumentException("No non-static Runge-Kutta method has been called");
        } else {
            PlotGraph var1 = new PlotGraph(DeepCopy.copy(this.getAllValues()));
            var1.setXaxisLegend("x step values");
            var1.setYaxisLegend("y values");
            var1.setGraphTitle("All steps plot: " + this.rkMethods[this.rkMethodNumber]);
            var1.setFrame();
        }
    }

    public double[] getXvalueArray() {
        int var1 = this.xValues.size();
        double[] var2 = new double[var1];

        for(int var3 = 0; var3 < var1; ++var3) {
            var2[var3] = (Double)this.xValues.get(var3);
        }

        return var2;
    }

    public double[][] getYvalueArray() {
        int var1 = this.yValues.size();
        double[][] var2 = new double[this.nODE][var1];
        double[] var3 = new double[this.nODE];

        for(int var4 = 0; var4 < var1; ++var4) {
            var3 = (double[])this.yValues.get(var4);

            for(int var5 = 0; var5 < this.nODE; ++var5) {
                var2[var5][var4] = var3[var5];
            }
        }

        return var2;
    }

    public double[][] getAllValues() {
        int var1 = this.xValues.size();
        double[][] var2 = new double[this.nODE + 1][var1];

        for(int var3 = 0; var3 < var1; ++var3) {
            var2[0][var3] = (Double)this.xValues.get(var3);
        }

        double[] var6 = new double[this.nODE];

        for(int var4 = 0; var4 < var1; ++var4) {
            var6 = (double[])this.yValues.get(var4);

            for(int var5 = 0; var5 < this.nODE; ++var5) {
                var2[var5 + 1][var4] = var6[var5];
            }
        }

        return var2;
    }
}
