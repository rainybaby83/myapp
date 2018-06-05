//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.interpolation;

import com.flanagan.math.Fmath;

public class BiCubicSplinePartialDerivative {
    private int nPoints = 0;
    private int mPoints = 0;
    private double[][] y = (double[][])null;
    private double[] x1 = null;
    private double[] x2 = null;
    private double[] xMin = new double[2];
    private double[] xMax = new double[2];
    private CubicSpline[] csn = null;
    private CubicSpline csm = null;
    private boolean derivCalculated = false;
    private boolean averageIdenticalAbscissae = false;
    private static double potentialRoundingError = 5.0E-15D;
    private static boolean roundingCheck = true;

    public BiCubicSplinePartialDerivative(double[] var1, double[] var2, double[][] var3) {
        this.nPoints = var1.length;
        this.mPoints = var2.length;
        if (this.nPoints != var3.length) {
            throw new IllegalArgumentException("Arrays x1 and y-row are of different length " + this.nPoints + " " + var3.length);
        } else if (this.mPoints != var3[0].length) {
            throw new IllegalArgumentException("Arrays x2 and y-column are of different length " + this.mPoints + " " + var3[0].length);
        } else if (this.nPoints >= 3 && this.mPoints >= 3) {
            this.csm = new CubicSpline(this.nPoints);
            this.csn = CubicSpline.oneDarray(this.nPoints, this.mPoints);
            this.x1 = new double[this.nPoints];
            this.x2 = new double[this.mPoints];
            this.y = new double[this.nPoints][this.mPoints];

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

            double[] var7 = new double[this.mPoints];

            for(var5 = 0; var5 < this.nPoints; ++var5) {
                for(int var6 = 0; var6 < this.mPoints; ++var6) {
                    var7[var6] = var3[var5][var6];
                }

                this.csn[var5].resetData(var2, var7);
                this.csn[var5].calcDeriv();
            }

            this.derivCalculated = true;
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

        CubicSpline var10000;
        for(int var1 = 0; var1 < this.csn.length; ++var1) {
            var10000 = this.csn[var1];
            CubicSpline.averageIdenticalAbscissae();
        }

        var10000 = this.csm;
        CubicSpline.averageIdenticalAbscissae();
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
        double[] var5 = new double[this.nPoints];

        for(int var6 = 0; var6 < this.nPoints; ++var6) {
            var5[var6] = this.csn[var6].interpolate(var3);
        }

        this.csm.resetData(this.x1, var5);
        return this.csm.interpolate_for_y_and_dydx(var1);
    }
}
