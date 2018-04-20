//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.interpolation;

import com.flanagan.math.Fmath;

public class QuadriCubicSpline {
    private int nPoints = 0;
    private int mPoints = 0;
    private int lPoints = 0;
    private int kPoints = 0;
    private double[][][][] y = (double[][][][])null;
    private double[] x1 = null;
    private double[] x2 = null;
    private double[] x3 = null;
    private double[] x4 = null;
    private double[] xMin = new double[4];
    private double[] xMax = new double[4];
    private TriCubicSpline[] tcsn = null;
    private CubicSpline csm = null;
    private double[][][][] d2ydx2inner = (double[][][][])null;
    private boolean derivCalculated = false;
    private boolean averageIdenticalAbscissae = false;
    private static double potentialRoundingError = 5.0E-15D;
    private static boolean roundingCheck = true;

    public QuadriCubicSpline(double[] var1, double[] var2, double[] var3, double[] var4, double[][][][] var5) {
        this.nPoints = var1.length;
        this.mPoints = var2.length;
        this.lPoints = var3.length;
        this.kPoints = var4.length;
        if (this.nPoints != var5.length) {
            throw new IllegalArgumentException("Arrays x1 and y-row are of different length " + this.nPoints + " " + var5.length);
        } else if (this.mPoints != var5[0].length) {
            throw new IllegalArgumentException("Arrays x2 and y-column are of different length " + this.mPoints + " " + var5[0].length);
        } else if (this.lPoints != var5[0][0].length) {
            throw new IllegalArgumentException("Arrays x3 and y-column are of different length " + this.mPoints + " " + var5[0][0].length);
        } else if (this.kPoints != var5[0][0][0].length) {
            throw new IllegalArgumentException("Arrays x4 and y-column are of different length " + this.kPoints + " " + var5[0][0][0].length);
        } else if (this.nPoints >= 3 && this.mPoints >= 3 && this.lPoints >= 3 && this.kPoints >= 3) {
            this.csm = new CubicSpline(this.nPoints);
            this.tcsn = TriCubicSpline.oneDarray(this.nPoints, this.mPoints, this.lPoints, this.kPoints);
            this.x1 = new double[this.nPoints];
            this.x2 = new double[this.mPoints];
            this.x3 = new double[this.lPoints];
            this.x4 = new double[this.kPoints];
            this.y = new double[this.nPoints][this.mPoints][this.lPoints][this.kPoints];
            this.d2ydx2inner = new double[this.nPoints][this.mPoints][this.lPoints][this.kPoints];

            int var6;
            for(var6 = 0; var6 < this.nPoints; ++var6) {
                this.x1[var6] = var1[var6];
            }

            this.xMin[0] = Fmath.minimum(this.x1);
            this.xMax[0] = Fmath.maximum(this.x1);

            for(var6 = 0; var6 < this.mPoints; ++var6) {
                this.x2[var6] = var2[var6];
            }

            this.xMin[1] = Fmath.minimum(this.x2);
            this.xMax[1] = Fmath.maximum(this.x2);

            for(var6 = 0; var6 < this.lPoints; ++var6) {
                this.x3[var6] = var3[var6];
            }

            this.xMin[2] = Fmath.minimum(this.x3);
            this.xMax[2] = Fmath.maximum(this.x3);

            for(var6 = 0; var6 < this.kPoints; ++var6) {
                this.x4[var6] = var4[var6];
            }

            this.xMin[3] = Fmath.minimum(this.x4);
            this.xMax[3] = Fmath.maximum(this.x4);

            int var7;
            int var8;
            int var9;
            for(var6 = 0; var6 < this.nPoints; ++var6) {
                for(var7 = 0; var7 < this.mPoints; ++var7) {
                    for(var8 = 0; var8 < this.lPoints; ++var8) {
                        for(var9 = 0; var9 < this.kPoints; ++var9) {
                            this.y[var6][var7][var8][var9] = var5[var6][var7][var8][var9];
                        }
                    }
                }
            }

            double[][][] var12 = new double[this.mPoints][this.lPoints][this.kPoints];

            for(var7 = 0; var7 < this.nPoints; ++var7) {
                for(var8 = 0; var8 < this.mPoints; ++var8) {
                    for(var9 = 0; var9 < this.lPoints; ++var9) {
                        for(int var10 = 0; var10 < this.kPoints; ++var10) {
                            var12[var8][var9][var10] = var5[var7][var8][var9][var10];
                        }
                    }
                }

                this.tcsn[var7].resetData(var2, var3, var4, var12);
                this.d2ydx2inner[var7] = this.tcsn[var7].getDeriv();
            }

            double[] var11 = new double[this.nPoints];
            this.derivCalculated = true;
        } else {
            throw new IllegalArgumentException("The tabulated 4D array must have a minimum size of 3 X 3 X 3 X 3");
        }
    }

    public static void noRoundingErrorCheck() {
        roundingCheck = false;
        TriCubicSpline.noRoundingErrorCheck();
        BiCubicSpline.noRoundingErrorCheck();
        CubicSpline.noRoundingErrorCheck();
    }

    public static void potentialRoundingError(double var0) {
        potentialRoundingError = var0;
        TriCubicSpline.potentialRoundingError(var0);
        BiCubicSpline.potentialRoundingError(var0);
        CubicSpline.potentialRoundingError(var0);
    }

    public void averageIdenticalAbscissae() {
        this.averageIdenticalAbscissae = true;

        for(int var1 = 0; var1 < this.tcsn.length; ++var1) {
            this.tcsn[var1].averageIdenticalAbscissae();
        }

        CubicSpline var10000 = this.csm;
        CubicSpline.averageIdenticalAbscissae();
    }

    public double[] getXmin() {
        return this.xMin;
    }

    public double[] getXmax() {
        return this.xMax;
    }

    public double[] getLimits() {
        double[] var1 = new double[]{this.xMin[0], this.xMax[0], this.xMin[1], this.xMax[1], this.xMin[2], this.xMax[2], this.xMin[3], this.xMax[3]};
        return var1;
    }

    public void displayLimits() {
        System.out.println(" ");

        for(int var1 = 0; var1 < 2; ++var1) {
            System.out.println("The limits to the x array " + var1 + " are " + this.xMin[var1] + " and " + this.xMax[var1]);
        }

        System.out.println(" ");
    }

    public double interpolate(double var1, double var3, double var5, double var7) {
        double[] var9 = new double[this.nPoints];

        for(int var10 = 0; var10 < this.nPoints; ++var10) {
            var9[var10] = this.tcsn[var10].interpolate(var3, var5, var7);
        }

        this.csm.resetData(this.x1, var9);
        return this.csm.interpolate(var1);
    }

    public double[][][][] getDeriv() {
        return this.d2ydx2inner;
    }

    public void setDeriv(double[][][][] var1) {
        this.d2ydx2inner = var1;
        this.derivCalculated = true;
    }
}
