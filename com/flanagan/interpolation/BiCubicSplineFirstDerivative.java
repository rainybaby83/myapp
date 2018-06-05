//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.interpolation;

import com.flanagan.math.Fmath;

public class BiCubicSplineFirstDerivative {
    private int nPoints = 0;
    private int mPoints = 0;
    private double[][] y = (double[][])null;
    private double[][] yTranspose = (double[][])null;
    private double[] x1 = null;
    private double[] x2 = null;
    private double[] xMin = new double[2];
    private double[] xMax = new double[2];
    private BiCubicSplinePartialDerivative cspdY = null;
    private BiCubicSplinePartialDerivative cspdYt = null;
    private boolean averageIdenticalAbscissae = false;
    private static double potentialRoundingError = 5.0E-15D;
    private static boolean roundingCheck = true;

    public BiCubicSplineFirstDerivative(double[] var1, double[] var2, double[][] var3) {
        this.nPoints = var1.length;
        this.mPoints = var2.length;
        if (this.nPoints != var3.length) {
            throw new IllegalArgumentException("Arrays x1 and y-row are of different length " + this.nPoints + " " + var3.length);
        } else if (this.mPoints != var3[0].length) {
            throw new IllegalArgumentException("Arrays x2 and y-column are of different length " + this.mPoints + " " + var3[0].length);
        } else if (this.nPoints >= 3 && this.mPoints >= 3) {
            this.x1 = new double[this.nPoints];
            this.x2 = new double[this.mPoints];
            this.y = new double[this.nPoints][this.mPoints];
            this.yTranspose = new double[this.mPoints][this.nPoints];

            int var4;
            for(var4 = 0; var4 < this.nPoints; ++var4) {
                this.x1[var4] = var1[var4];
            }

            this.xMin[0] = Fmath.minimum(this.x1);
            this.xMax[0] = Fmath.maximum(this.x1);

            for(var4 = 0; var4 < this.mPoints; ++var4) {
                this.x2[var4] = var2[var4];
            }

            this.xMin[1] = Fmath.minimum(this.x2);
            this.xMax[1] = Fmath.maximum(this.x2);

            int var5;
            for(var4 = 0; var4 < this.nPoints; ++var4) {
                for(var5 = 0; var5 < this.mPoints; ++var5) {
                    this.y[var4][var5] = var3[var4][var5];
                }
            }

            for(var4 = 0; var4 < this.nPoints; ++var4) {
                for(var5 = 0; var5 < this.mPoints; ++var5) {
                    this.yTranspose[var5][var4] = this.y[var4][var5];
                }
            }

            this.cspdY = new BiCubicSplinePartialDerivative(var1, var2, var3);
            this.cspdYt = new BiCubicSplinePartialDerivative(var2, var1, this.yTranspose);
        } else {
            throw new IllegalArgumentException("The fillData matrix must have a minimum size of 3 X 3");
        }
    }

    public static void noRoundingErrorCheck() {
        roundingCheck = false;
        CubicSpline.noRoundingErrorCheck();
    }

    public static void potentialRoundingError(double var0) {
        potentialRoundingError = var0;
        CubicSpline.potentialRoundingError(var0);
    }

    public void averageIdenticalAbscissae() {
        this.averageIdenticalAbscissae = true;
        this.cspdY.averageIdenticalAbscissae();
        this.cspdYt.averageIdenticalAbscissae();
    }

    public double[] getXmin() {
        return this.xMin;
    }

    public double[] getXmax() {
        return this.xMax;
    }

    public double[] getLimits() {
        double[] var1 = new double[]{this.xMin[0], this.xMax[0], this.xMin[1], this.xMax[1]};
        return var1;
    }

    public void displayLimits() {
        System.out.println(" ");

        for(int var1 = 0; var1 < 2; ++var1) {
            System.out.println("The limits to the x array " + var1 + " are " + this.xMin[var1] + " and " + this.xMax[var1]);
        }

        System.out.println(" ");
    }

    public double[] interpolate(double var1, double var3) {
        double[] var5 = this.cspdY.interpolate(var1, var3);
        double[] var6 = this.cspdYt.interpolate(var3, var1);
        double var7 = (var5[0] + var6[0]) / 2.0D;
        double[] var9 = new double[]{var7, var5[1], var6[1], var5[0], var6[0]};
        return var9;
    }
}
