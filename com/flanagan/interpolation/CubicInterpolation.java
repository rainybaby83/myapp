//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.interpolation;

import com.flanagan.math.ArrayMaths;
import com.flanagan.math.Conv;
import com.flanagan.math.Fmath;

public class CubicInterpolation {
    private int nPoints = 0;
    private double[] x = null;
    private double[] y = null;
    private double[] dydx = null;
    private boolean derivCalculated = false;
    private CubicSpline cs = null;
    private double incrX = 0.0D;
    double[][] coeff = (double[][])null;
    private double xx = 0.0D / 0.0;
    private double[][] weights = new double[][]{{1.0D, 0.0D, 0.0D, 0.0D}, {0.0D, 0.0D, 1.0D, 0.0D}, {-3.0D, 3.0D, -2.0D, -1.0D}, {2.0D, -2.0D, 1.0D, 1.0D}};
    private int[] xIndices = null;
    private double xMin = 0.0D;
    private double xMax = 0.0D;
    private double interpolatedValue = 0.0D / 0.0;
    private double interpolatedDydx = 0.0D / 0.0;
    private boolean numerDiffFlag = true;
    private static double delta = 0.001D;
    private static double potentialRoundingError = 5.0E-15D;
    private static boolean roundingCheck = false;

    public CubicInterpolation(double[] var1, double[] var2, int var3) {
        if (var3 == 0) {
            this.numerDiffFlag = false;
        } else {
            if (var3 != 1) {
                throw new IllegalArgumentException("The numerical differencing option, " + var3 + ", must be 0 or 1");
            }

            this.numerDiffFlag = true;
        }

        this.initialize(Conv.copy(var1), Conv.copy(var2));
        this.calcDeriv();
        this.gridCoefficients();
    }

    public CubicInterpolation(double[] var1, double[] var2, double[] var3) {
        this.initialize(Conv.copy(var1), Conv.copy(var2), Conv.copy(var3));
        this.gridCoefficients();
    }

    private void initialize(double[] var1, double[] var2) {
        this.initialize(var1, var2, (double[])null, false);
    }

    private void initialize(double[] var1, double[] var2, double[] var3) {
        this.initialize(var1, var2, var3, true);
    }

    private void initialize(double[] var1, double[] var2, double[] var3, boolean var4) {
        byte var5 = 3;
        if (var4) {
            var5 = 2;
        }

        int var6 = var1.length;
        if (var6 != var2.length) {
            throw new IllegalArgumentException("Arrays x and y-row are of different length " + var6 + " " + var2.length);
        } else if (var6 < var5) {
            throw new IllegalArgumentException("The fillData matrix must have a minimum size of " + var5 + " X " + var5);
        } else {
            ArrayMaths var7 = new ArrayMaths(var1);
            var7 = var7.sort();
            this.xIndices = var7.originalIndices();
            var1 = var7.array();
            double[] var8 = new double[var6];

            int var9;
            for(var9 = 0; var9 < var6; ++var9) {
                var8[var9] = var2[this.xIndices[var9]];
            }

            for(var9 = 0; var9 < var6; ++var9) {
                var2[var9] = var8[var9];
            }

            if (var4) {
                for(var9 = 0; var9 < var6; ++var9) {
                    var8[var9] = var3[this.xIndices[var9]];
                }

                for(var9 = 0; var9 < var6; ++var9) {
                    var3[var9] = var8[var9];
                }
            }

            for(var9 = 1; var9 < var6; ++var9) {
                if (var1[var9] == var1[var9 - 1]) {
                    System.out.println("x[" + this.xIndices[var9] + "] and x[" + this.xIndices[var9 + 1] + "] are identical, " + var1[var9]);
                    System.out.println("The y values have been averaged and one point has been deleted");
                    var2[var9 - 1] = (var2[var9 - 1] + var2[var9]) / 2.0D;

                    int var10;
                    for(var10 = var9; var10 < var6 - 1; ++var10) {
                        var1[var10] = var1[var10 + 1];
                        var2[var10] = var2[var10 + 1];
                        this.xIndices[var10] = this.xIndices[var10 + 1];
                    }

                    if (var4) {
                        var3[var9 - 1] = (var3[var9 - 1] + var3[var9]) / 2.0D;

                        for(var10 = var9; var10 < var6 - 1; ++var10) {
                            var3[var10] = var3[var10 + 1];
                        }
                    }

                    --var6;
                }
            }

            this.nPoints = var6;
            this.x = new double[this.nPoints];
            this.y = new double[this.nPoints];
            this.dydx = new double[this.nPoints];

            for(var9 = 0; var9 < this.nPoints; ++var9) {
                this.x[var9] = var1[var9];
                this.y[var9] = var2[var9];
            }

            if (var4) {
                for(var9 = 0; var9 < this.nPoints; ++var9) {
                    this.dydx[var9] = var3[var9];
                }

                this.derivCalculated = true;
            }

            this.xMin = Fmath.minimum(this.x);
            this.xMax = Fmath.maximum(this.x);
            if (!var4 && this.numerDiffFlag) {
                double var19 = this.xMax - this.xMin;
                double var11 = var19 / (double)this.nPoints;
                double var13 = this.x[1] - this.x[0];
                double var15 = var13;

                for(int var17 = 2; var17 < this.nPoints; ++var17) {
                    var13 = this.x[var17] - this.x[var17 - 1];
                    if (var13 < var15) {
                        var15 = var13;
                    }
                }

                this.incrX = var19 * delta;
                double var20 = var15;
                if (var15 < var11 / 10.0D) {
                    var20 = var11 / 10.0D;
                }

                if (this.incrX > var11) {
                    this.incrX = var20;
                }
            }

        }
    }

    private void calcDeriv() {
        int var3;
        if (this.numerDiffFlag) {
            this.cs = new CubicSpline(this.x, this.y);
            double[] var1 = new double[this.nPoints];
            double[] var2 = new double[this.nPoints];

            for(var3 = 0; var3 < this.nPoints; ++var3) {
                var1[var3] = this.x[var3] + this.incrX;
                if (var1[var3] > this.x[this.nPoints - 1]) {
                    var1[var3] = this.x[this.nPoints - 1];
                }

                var2[var3] = this.x[var3] - this.incrX;
                if (var2[var3] < this.x[0]) {
                    var2[var3] = this.x[0];
                }
            }

            for(var3 = 0; var3 < this.nPoints; ++var3) {
                this.dydx[var3] = (this.cs.interpolate(var1[var3]) - this.cs.interpolate(var2[var3])) / (var1[var3] - var2[var3]);
            }
        } else {
            boolean var4 = false;
            boolean var6 = false;

            for(var3 = 0; var3 < this.nPoints; ++var3) {
                int var5 = var3 + 1;
                if (var5 >= this.nPoints) {
                    var5 = this.nPoints - 1;
                }

                int var7 = var3 - 1;
                if (var7 < 0) {
                    var7 = 0;
                }

                this.dydx[var3] = (this.y[var5] - this.y[var7]) / (this.x[var5] - this.x[var7]);
            }
        }

        this.derivCalculated = true;
    }

    private void gridCoefficients() {
        double[] var1 = new double[4];
        this.coeff = new double[this.nPoints][4];
        double var2 = 0.0D;

        for(int var4 = 0; var4 < this.nPoints - 1; ++var4) {
            var2 = this.x[var4 + 1] - this.x[var4];
            var1[0] = this.y[var4];
            var1[1] = this.y[var4 + 1];
            var1[2] = this.dydx[var4] * var2;
            var1[3] = this.dydx[var4 + 1] * var2;
            double var5 = 0.0D;

            for(int var7 = 0; var7 < 4; ++var7) {
                for(int var8 = 0; var8 < 4; ++var8) {
                    var5 += this.weights[var7][var8] * var1[var8];
                }

                this.coeff[var4][var7] = var5;
                var5 = 0.0D;
            }
        }

    }

    public double interpolate(double var1) {
        if (var1 < this.x[0]) {
            if (var1 < this.x[0] - potentialRoundingError) {
                throw new IllegalArgumentException(var1 + " is outside the limits, " + this.x[0] + " - " + this.x[this.nPoints - 1]);
            }

            var1 = this.x[0];
        }

        if (var1 > this.x[this.nPoints - 1]) {
            if (var1 > this.x[this.nPoints - 1] + potentialRoundingError) {
                throw new IllegalArgumentException(var1 + " is outside the limits, " + this.x[0] + " - " + this.x[this.nPoints - 1]);
            }

            var1 = this.x[this.nPoints - 1];
        }

        this.xx = var1;
        int var3 = 0;
        int var4 = 1;
        boolean var5 = true;

        while(var5) {
            if (var1 < this.x[var4]) {
                var3 = var4 - 1;
                var5 = false;
            } else {
                ++var4;
                if (var4 >= this.nPoints) {
                    var3 = this.nPoints - 2;
                    var5 = false;
                }
            }
        }

        double var6 = (var1 - this.x[var3]) / (this.x[var3 + 1] - this.x[var3]);
        this.interpolatedValue = 0.0D;

        int var8;
        for(var8 = 0; var8 < 4; ++var8) {
            this.interpolatedValue += this.coeff[var3][var8] * Math.pow(var6, (double)var8);
        }

        this.interpolatedDydx = 0.0D;

        for(var8 = 1; var8 < 4; ++var8) {
            this.interpolatedDydx += (double)var8 * this.coeff[var3][var8] * Math.pow(var6, (double)(var8 - 1));
        }

        return this.interpolatedValue;
    }

    public double[] getInterpolatedValues() {
        double[] var1 = new double[]{this.interpolatedValue, this.interpolatedDydx, this.xx};
        return var1;
    }

    public double[] getGridDydx() {
        double[] var1 = new double[this.nPoints];

        for(int var2 = 0; var2 < this.nPoints; ++var2) {
            var1[this.xIndices[var2]] = this.dydx[var2];
        }

        return var1;
    }

    public static void resetDelta(double var0) {
        delta = var0;
    }

    public static void noRoundingErrorCheck() {
        roundingCheck = false;
        potentialRoundingError = 0.0D;
    }

    public static void potentialRoundingError(double var0) {
        potentialRoundingError = var0;
    }

    public double getXmin() {
        return this.xMin;
    }

    public double getXmax() {
        return this.xMax;
    }

    public double[] getLimits() {
        double[] var1 = new double[]{this.xMin, this.xMax};
        return var1;
    }

    public void displayLimits() {
        System.out.println(" ");
        System.out.println("The limits to the x array are " + this.xMin + " and " + this.xMax);
        System.out.println(" ");
    }
}
