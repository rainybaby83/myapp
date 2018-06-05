//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.interpolation;

import com.flanagan.math.ArrayMaths;
import com.flanagan.math.Conv;
import com.flanagan.math.Fmath;
import java.util.ArrayList;

public class BiCubicInterpolation {
    private int nPoints = 0;
    private int mPoints = 0;
    private double[] x1 = null;
    private double[] x2 = null;
    private double[][] y = (double[][])null;
    private double[][] dydx1 = (double[][])null;
    private double[][] dydx2 = (double[][])null;
    private double[][] d2ydx1dx2 = (double[][])null;
    private boolean derivCalculated = false;
    private BiCubicSpline bcs = null;
    private double incrX1 = 0.0D;
    private double incrX2 = 0.0D;
    private double xx1 = 0.0D / 0.0;
    private double xx2 = 0.0D / 0.0;
    private ArrayList<Object> coeff = new ArrayList();
    private double[][] weights = new double[][]{{1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D}, {0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D}, {-3.0D, 0.0D, 0.0D, 3.0D, 0.0D, 0.0D, 0.0D, 0.0D, -2.0D, 0.0D, 0.0D, -1.0D, 0.0D, 0.0D, 0.0D, 0.0D}, {2.0D, 0.0D, 0.0D, -2.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D, 0.0D, 0.0D, 1.0D, 0.0D, 0.0D, 0.0D, 0.0D}, {0.0D, 0.0D, 0.0D, 0.0D, 1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D}, {0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D, 0.0D, 0.0D, 0.0D}, {0.0D, 0.0D, 0.0D, 0.0D, -3.0D, 0.0D, 0.0D, 3.0D, 0.0D, 0.0D, 0.0D, 0.0D, -2.0D, 0.0D, 0.0D, -1.0D}, {0.0D, 0.0D, 0.0D, 0.0D, 2.0D, 0.0D, 0.0D, -2.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D, 0.0D, 0.0D, 1.0D}, {-3.0D, 3.0D, 0.0D, 0.0D, -2.0D, -1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D}, {0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, -3.0D, 3.0D, 0.0D, 0.0D, -2.0D, -1.0D, 0.0D, 0.0D}, {9.0D, -9.0D, 9.0D, -9.0D, 6.0D, 3.0D, -3.0D, -6.0D, 6.0D, -6.0D, -3.0D, 3.0D, 4.0D, 2.0D, 1.0D, 2.0D}, {-6.0D, 6.0D, -6.0D, 6.0D, -4.0D, -2.0D, 2.0D, 4.0D, -3.0D, 3.0D, 3.0D, -3.0D, -2.0D, -1.0D, -1.0D, -2.0D}, {2.0D, -2.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D}, {0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 2.0D, -2.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.0D, 0.0D}, {-6.0D, 6.0D, -6.0D, 6.0D, -3.0D, -3.0D, 3.0D, 3.0D, -4.0D, 4.0D, 2.0D, -2.0D, -2.0D, -2.0D, -1.0D, -1.0D}, {4.0D, -4.0D, 4.0D, -4.0D, 2.0D, 2.0D, -2.0D, -2.0D, 2.0D, -2.0D, -2.0D, 2.0D, 1.0D, 1.0D, 1.0D, 1.0D}};
    private int[] x1indices = null;
    private int[] x2indices = null;
    private double[] xMin = new double[2];
    private double[] xMax = new double[2];
    private double interpolatedValue = 0.0D / 0.0;
    private double interpolatedDydx1 = 0.0D / 0.0;
    private double interpolatedDydx2 = 0.0D / 0.0;
    private double interpolatedD2ydx1dx2 = 0.0D / 0.0;
    private boolean numerDiffFlag = true;
    private static double delta = 0.001D;
    private static double potentialRoundingError = 5.0E-15D;
    private static boolean roundingCheck = false;

    public BiCubicInterpolation(double[] var1, double[] var2, double[][] var3, int var4) {
        if (var4 == 0) {
            this.numerDiffFlag = false;
        } else {
            if (var4 != 1) {
                throw new IllegalArgumentException("The numerical differencing option, " + var4 + ", must be 0 or 1");
            }

            this.numerDiffFlag = true;
        }

        this.initialize(Conv.copy(var1), Conv.copy(var2), Conv.copy(var3));
        this.calcDeriv();
        this.gridCoefficients();
    }

    public BiCubicInterpolation(double[] var1, double[] var2, double[][] var3) {
        this.numerDiffFlag = true;
        this.initialize(Conv.copy(var1), Conv.copy(var2), Conv.copy(var3));
        this.calcDeriv();
        this.gridCoefficients();
    }

    public BiCubicInterpolation(double[] var1, double[] var2, double[][] var3, double[][] var4, double[][] var5, double[][] var6) {
        this.initialize(Conv.copy(var1), Conv.copy(var2), Conv.copy(var3), Conv.copy(var4), Conv.copy(var5), Conv.copy(var6));
        this.gridCoefficients();
    }

    private void initialize(double[] var1, double[] var2, double[][] var3) {
        this.initialize(var1, var2, var3, (double[][])null, (double[][])null, (double[][])null, false);
    }

    private void initialize(double[] var1, double[] var2, double[][] var3, double[][] var4, double[][] var5, double[][] var6) {
        this.initialize(var1, var2, var3, var4, var5, var6, true);
    }

    private void initialize(double[] var1, double[] var2, double[][] var3, double[][] var4, double[][] var5, double[][] var6, boolean var7) {
        int var8 = var1.length;
        int var9 = var2.length;
        if (var8 != var3.length) {
            throw new IllegalArgumentException("Arrays x1 and y-row are of different length " + var8 + " " + var3.length);
        } else if (var9 != var3[0].length) {
            throw new IllegalArgumentException("Arrays x2 and y-column are of different length " + var9 + " " + var3[0].length);
        } else if (var8 >= 2 && var9 >= 2) {
            ArrayMaths var10 = new ArrayMaths(var1);
            var10 = var10.sort();
            this.x1indices = var10.originalIndices();
            var1 = var10.array();
            double[][] var11 = new double[var8][var9];
            double[][] var12 = (double[][])null;
            double[][] var13 = (double[][])null;
            double[][] var14 = (double[][])null;

            int var15;
            int var16;
            for(var15 = 0; var15 < var8; ++var15) {
                for(var16 = 0; var16 < var9; ++var16) {
                    var11[var15][var16] = var3[this.x1indices[var15]][var16];
                }
            }

            for(var15 = 0; var15 < var8; ++var15) {
                for(var16 = 0; var16 < var9; ++var16) {
                    var3[var15][var16] = var11[var15][var16];
                }
            }

            if (var7) {
                var12 = new double[var8][var9];
                var13 = new double[var8][var9];
                var14 = new double[var8][var9];

                for(var15 = 0; var15 < var8; ++var15) {
                    for(var16 = 0; var16 < var9; ++var16) {
                        var12[var15][var16] = var4[this.x1indices[var15]][var16];
                        var13[var15][var16] = var5[this.x1indices[var15]][var16];
                        var14[var15][var16] = var6[this.x1indices[var15]][var16];
                    }
                }

                for(var15 = 0; var15 < var8; ++var15) {
                    for(var16 = 0; var16 < var9; ++var16) {
                        var4[var15][var16] = var12[var15][var16];
                        var5[var15][var16] = var13[var15][var16];
                        var6[var15][var16] = var14[var15][var16];
                    }
                }
            }

            var10 = new ArrayMaths(var2);
            var10 = var10.sort();
            this.x2indices = var10.originalIndices();
            double[] var32 = var10.array();

            for(var16 = 0; var16 < var9; ++var16) {
                var2[var16] = var32[var9 - 1 - var16];
            }

            int var17;
            for(var16 = 0; var16 < var9; ++var16) {
                for(var17 = 0; var17 < var8; ++var17) {
                    var11[var17][var16] = var3[var17][this.x2indices[var16]];
                }
            }

            for(var16 = 0; var16 < var8; ++var16) {
                for(var17 = 0; var17 < var9; ++var17) {
                    var3[var16][var17] = var11[var16][var9 - 1 - var17];
                }
            }

            if (var7) {
                for(var16 = 0; var16 < var9; ++var16) {
                    for(var17 = 0; var17 < var8; ++var17) {
                        var12[var17][var16] = var4[var17][this.x2indices[var16]];
                        var13[var17][var16] = var5[var17][this.x2indices[var16]];
                        var14[var17][var16] = var6[var17][this.x2indices[var16]];
                    }
                }

                for(var16 = 0; var16 < var8; ++var16) {
                    for(var17 = 0; var17 < var9; ++var17) {
                        var4[var16][var17] = var12[var16][var9 - 1 - var17];
                        var5[var16][var17] = var13[var16][var9 - 1 - var17];
                        var6[var16][var17] = var14[var16][var9 - 1 - var17];
                    }
                }
            }

            int var18;
            for(var16 = 1; var16 < var8; ++var16) {
                if (var1[var16] == var1[var16 - 1]) {
                    System.out.println("x1[" + this.x1indices[var16] + "] and x1[" + this.x1indices[var16 + 1] + "] are identical, " + var1[var16]);
                    System.out.println("The y values have been averaged and one point has been deleted");

                    for(var17 = var16; var17 < var8 - 1; ++var17) {
                        var1[var17] = var1[var17 + 1];
                        this.x1indices[var17] = this.x1indices[var17 + 1];
                    }

                    for(var17 = 0; var17 < var9; ++var17) {
                        var3[var16 - 1][var17] = (var3[var16 - 1][var17] + var3[var16][var17]) / 2.0D;

                        for(var18 = var16; var18 < var8 - 1; ++var18) {
                            var3[var18][var17] = var3[var18 + 1][var17];
                        }

                        if (var7) {
                            var4[var16 - 1][var17] = (var4[var16 - 1][var17] + var4[var16][var17]) / 2.0D;
                            var5[var16 - 1][var17] = (var5[var16 - 1][var17] + var5[var16][var17]) / 2.0D;
                            var6[var16 - 1][var17] = (var6[var16 - 1][var17] + var6[var16][var17]) / 2.0D;

                            for(var18 = var16; var18 < var8 - 1; ++var18) {
                                var4[var18][var17] = var4[var18 + 1][var17];
                                var5[var18][var17] = var5[var18 + 1][var17];
                                var6[var18][var17] = var6[var18 + 1][var17];
                            }
                        }
                    }

                    --var8;
                }
            }

            for(var16 = 1; var16 < var9; ++var16) {
                if (var2[var16] == var2[var16 - 1]) {
                    System.out.println("x2[" + this.x2indices[var16] + "] and x2[" + this.x2indices[var16] + "] are identical, " + var2[var16]);
                    System.out.println("The y values have been averaged and one point has been deleted");

                    for(var17 = var16; var17 < var9 - 1; ++var17) {
                        var2[var17] = var2[var17 + 1];
                        this.x2indices[var17] = this.x2indices[var17 + 1];
                    }

                    for(var17 = 0; var17 < var8; ++var17) {
                        var3[var17][var16 - 1] = (var3[var17][var16 - 1] + var3[var17][var16]) / 2.0D;

                        for(var18 = var16; var18 < var9 - 1; ++var18) {
                            var3[var17][var18] = var3[var17][var18 + 1];
                        }

                        if (var7) {
                            var4[var17][var16 - 1] = (var4[var17][var16 - 1] + var4[var17][var16]) / 2.0D;
                            var5[var17][var16 - 1] = (var5[var17][var16 - 1] + var5[var17][var16]) / 2.0D;
                            var6[var17][var16 - 1] = (var6[var17][var16 - 1] + var6[var17][var16]) / 2.0D;

                            for(var18 = var16; var18 < var8 - 1; ++var18) {
                                var4[var17][var18] = var4[var17][var18 + 1];
                                var5[var17][var18] = var5[var17][var18 + 1];
                                var6[var17][var18] = var6[var17][var18 + 1];
                            }
                        }
                    }

                    --var9;
                }
            }

            this.nPoints = var8;
            this.mPoints = var9;
            this.x1 = new double[this.nPoints];
            this.x2 = new double[this.mPoints];
            this.y = new double[this.nPoints][this.mPoints];
            this.dydx1 = new double[this.nPoints][this.mPoints];
            this.dydx2 = new double[this.nPoints][this.mPoints];
            this.d2ydx1dx2 = new double[this.nPoints][this.mPoints];

            for(var16 = 0; var16 < this.nPoints; ++var16) {
                this.x1[var16] = var1[var16];
            }

            for(var16 = 0; var16 < this.mPoints; ++var16) {
                this.x2[var16] = var2[var16];
            }

            for(var16 = 0; var16 < this.nPoints; ++var16) {
                for(var17 = 0; var17 < this.mPoints; ++var17) {
                    this.y[var16][var17] = var3[var16][var17];
                }

                if (var7) {
                    for(var17 = 0; var17 < this.mPoints; ++var17) {
                        this.dydx1[var16][var17] = var4[var16][var17];
                        this.dydx2[var16][var17] = var5[var16][var17];
                        this.d2ydx1dx2[var16][var17] = var6[var16][var17];
                    }
                }
            }

            if (var7) {
                this.derivCalculated = true;
            }

            this.xMin[0] = Fmath.minimum(this.x1);
            this.xMax[0] = Fmath.maximum(this.x1);
            this.xMin[1] = Fmath.minimum(this.x2);
            this.xMax[1] = Fmath.maximum(this.x2);
            if (!var7 && this.numerDiffFlag) {
                double var34 = this.xMax[0] - this.xMin[0];
                double var33 = this.xMax[1] - this.xMin[1];
                double var20 = var34 / (double)this.nPoints;
                double var22 = var33 / (double)this.mPoints;
                double var24 = this.x1[1] - this.x1[0];
                double var26 = var24;

                for(int var28 = 2; var28 < this.nPoints; ++var28) {
                    var24 = this.x1[var28] - this.x1[var28 - 1];
                    if (var24 < var26) {
                        var26 = var24;
                    }
                }

                var24 = this.x2[1] - this.x2[0];
                double var35 = var24;

                for(int var30 = 2; var30 < this.mPoints; ++var30) {
                    var24 = this.x2[var30] - this.x2[var30 - 1];
                    if (var24 < var35) {
                        var35 = var24;
                    }
                }

                this.incrX1 = var34 * delta;
                double var36 = var26;
                if (var26 < var20 / 10.0D) {
                    var36 = var20 / 10.0D;
                }

                if (this.incrX1 > var20) {
                    this.incrX1 = var36;
                }

                this.incrX2 = var33 * delta;
                var36 = var35;
                if (var35 < var22 / 10.0D) {
                    var36 = var22 / 10.0D;
                }

                if (this.incrX2 > var22) {
                    this.incrX2 = var36;
                }
            }

        } else {
            throw new IllegalArgumentException("The fillData matrix must have a minimum size of 2 X 2");
        }
    }

    private void calcDeriv() {
        if (this.numerDiffFlag) {
            this.bcs = new BiCubicSpline(this.x1, this.x2, this.y);
            double var1 = 0.0D;
            double var3 = 0.0D;
            double[] var5 = new double[this.nPoints];
            double[] var6 = new double[this.nPoints];
            double[] var7 = new double[this.mPoints];
            double[] var8 = new double[this.mPoints];

            int var9;
            for(var9 = 0; var9 < this.nPoints; ++var9) {
                var5[var9] = this.x1[var9] + this.incrX1;
                if (var5[var9] > this.x1[this.nPoints - 1]) {
                    var5[var9] = this.x1[this.nPoints - 1];
                }

                var6[var9] = this.x1[var9] - this.incrX1;
                if (var6[var9] < this.x1[0]) {
                    var6[var9] = this.x1[0];
                }
            }

            for(var9 = 0; var9 < this.mPoints; ++var9) {
                var7[var9] = this.x2[var9] + this.incrX2;
                if (var7[var9] > this.x2[0]) {
                    var7[var9] = this.x2[0];
                }

                var8[var9] = this.x2[var9] - this.incrX2;
                if (var8[var9] < this.x2[this.mPoints - 1]) {
                    var8[var9] = this.x2[this.mPoints - 1];
                }
            }

            for(var9 = 0; var9 < this.nPoints; ++var9) {
                for(int var10 = 0; var10 < this.mPoints; ++var10) {
                    this.dydx1[var9][var10] = (this.bcs.interpolate(var5[var9], this.x2[var10]) - this.bcs.interpolate(var6[var9], this.x2[var10])) / (var5[var9] - var6[var9]);
                    this.dydx2[var9][var10] = (this.bcs.interpolate(this.x1[var9], var7[var10]) - this.bcs.interpolate(this.x1[var9], var8[var10])) / (var7[var10] - var8[var10]);
                    this.d2ydx1dx2[var9][var10] = (this.bcs.interpolate(var5[var9], var7[var10]) - this.bcs.interpolate(var5[var9], var8[var10]) - this.bcs.interpolate(var6[var9], var7[var10]) + this.bcs.interpolate(var6[var9], var8[var10])) / ((var5[var9] - var6[var9]) * (var7[var10] - var8[var10]));
                }
            }
        } else {
            boolean var11 = false;
            boolean var2 = false;
            boolean var14 = false;
            boolean var4 = false;

            for(int var17 = 0; var17 < this.nPoints; ++var17) {
                int var12 = var17 + 1;
                if (var12 >= this.nPoints) {
                    var12 = this.nPoints - 1;
                }

                int var13 = var17 - 1;
                if (var13 < 0) {
                    var13 = 0;
                }

                for(int var18 = 0; var18 < this.mPoints; ++var18) {
                    int var15 = var18 + 1;
                    if (var15 >= this.mPoints) {
                        var15 = this.mPoints - 1;
                    }

                    int var16 = var18 - 1;
                    if (var16 < 0) {
                        var16 = 0;
                    }

                    this.dydx1[var17][var18] = (this.y[var12][var18] - this.y[var13][var18]) / (this.x1[var12] - this.x1[var13]);
                    this.dydx2[var17][var18] = (this.y[var17][var15] - this.y[var17][var16]) / (this.x2[var15] - this.x2[var16]);
                    this.d2ydx1dx2[var17][var18] = (this.y[var12][var15] - this.y[var12][var16] - this.y[var13][var15] + this.y[var13][var16]) / ((this.x1[var12] - this.x1[var13]) * (this.x2[var15] - this.x2[var16]));
                }
            }
        }

        this.derivCalculated = true;
    }

    private void gridCoefficients() {
        double[] var1 = new double[4];
        double[] var2 = new double[4];
        double[] var3 = new double[4];
        double[] var4 = new double[4];
        double[] var5 = new double[16];
        double[] var6 = new double[16];
        double var7 = 0.0D;
        double var9 = 0.0D;

        for(int var11 = 0; var11 < this.nPoints - 1; ++var11) {
            var7 = this.x1[var11 + 1] - this.x1[var11];

            for(int var12 = 0; var12 < this.mPoints - 1; ++var12) {
                double[][] var13 = new double[4][4];
                var9 = this.x2[var12] - this.x2[var12 + 1];
                this.coeff.add(new Double(var7));
                this.coeff.add(new Double(this.x1[var11]));
                this.coeff.add(new Double(var9));
                this.coeff.add(new Double(this.x2[var12 + 1]));
                var1[0] = this.y[var11][var12 + 1];
                var2[0] = this.dydx1[var11][var12 + 1];
                var3[0] = this.dydx2[var11][var12 + 1];
                var4[0] = this.d2ydx1dx2[var11][var12 + 1];
                var1[1] = this.y[var11 + 1][var12 + 1];
                var2[1] = this.dydx1[var11 + 1][var12 + 1];
                var3[1] = this.dydx2[var11 + 1][var12 + 1];
                var4[1] = this.d2ydx1dx2[var11 + 1][var12 + 1];
                var1[2] = this.y[var11 + 1][var12];
                var2[2] = this.dydx1[var11 + 1][var12];
                var3[2] = this.dydx2[var11 + 1][var12];
                var4[2] = this.d2ydx1dx2[var11 + 1][var12];
                var1[3] = this.y[var11][var12];
                var2[3] = this.dydx1[var11][var12];
                var3[3] = this.dydx2[var11][var12];
                var4[3] = this.d2ydx1dx2[var11][var12];

                for(int var14 = 0; var14 < 4; ++var14) {
                    var6[var14] = var1[var14];
                    var6[var14 + 4] = var2[var14] * var7;
                    var6[var14 + 8] = var3[var14] * var9;
                    var6[var14 + 12] = var4[var14] * var7 * var9;
                }

                double var19 = 0.0D;

                int var16;
                int var17;
                for(var16 = 0; var16 < 16; ++var16) {
                    for(var17 = 0; var17 < 16; ++var17) {
                        var19 += this.weights[var16][var17] * var6[var17];
                    }

                    var5[var16] = var19;
                    var19 = 0.0D;
                }

                var16 = 0;

                for(var17 = 0; var17 < 4; ++var17) {
                    for(int var18 = 0; var18 < 4; ++var18) {
                        var13[var17][var18] = var5[var16++];
                    }
                }

                this.coeff.add(var13);
            }
        }

    }

    public double interpolate(double var1, double var3) {
        if (var1 < this.x1[0]) {
            if (var1 < this.x1[0] - potentialRoundingError) {
                throw new IllegalArgumentException(var1 + " is outside the limits, " + this.x1[0] + " - " + this.x1[this.nPoints - 1]);
            }

            var1 = this.x1[0];
        }

        if (var3 < this.x2[this.mPoints - 1]) {
            if (var3 <= this.x2[this.mPoints - 1] - potentialRoundingError) {
                throw new IllegalArgumentException(var3 + " is outside the limits, " + this.x2[this.mPoints - 1] + " - " + this.x2[0]);
            }

            var3 = this.x2[this.mPoints - 1];
        }

        if (var1 > this.x1[this.nPoints - 1]) {
            if (var1 > this.x1[this.nPoints - 1] + potentialRoundingError) {
                throw new IllegalArgumentException(var1 + " is outside the limits, " + this.x1[0] + " - " + this.x1[this.nPoints - 1]);
            }

            var1 = this.x1[this.nPoints - 1];
        }

        if (var3 > this.x2[0]) {
            if (var3 > this.x2[0] + potentialRoundingError) {
                throw new IllegalArgumentException(var3 + " is outside the limits, " + this.x2[this.nPoints - 1] + " - " + this.x2[0]);
            }

            var3 = this.x2[0];
        }

        this.xx1 = var1;
        this.xx2 = var3;
        int var5 = 0;
        int var6 = 0;
        int var7 = 1;
        boolean var8 = true;

        while(var8) {
            if (var1 < this.x1[var7]) {
                var5 = var7 - 1;
                var8 = false;
            } else {
                ++var7;
                if (var7 >= this.nPoints) {
                    var5 = this.nPoints - 2;
                    var8 = false;
                }
            }
        }

        var7 = 0;
        var8 = true;

        while(true) {
            while(var8) {
                if (var3 >= this.x2[var7 + 1] && var3 <= this.x2[var7]) {
                    var6 = var7;
                    var8 = false;
                } else {
                    ++var7;
                }
            }

            int var9 = var5 * (this.mPoints - 1) + var6;
            double var10 = (Double)this.coeff.get(5 * var9);
            double var12 = (Double)this.coeff.get(5 * var9 + 1);
            double var14 = (Double)this.coeff.get(5 * var9 + 2);
            double var16 = (Double)this.coeff.get(5 * var9 + 3);
            double[][] var18 = (double[][])((double[][])this.coeff.get(5 * var9 + 4));
            double var19 = (var1 - var12) / var10;
            double var21 = (var3 - var16) / var14;
            this.interpolatedValue = 0.0D;

            int var23;
            int var24;
            for(var23 = 0; var23 < 4; ++var23) {
                for(var24 = 0; var24 < 4; ++var24) {
                    this.interpolatedValue += var18[var23][var24] * Math.pow(var19, (double)var23) * Math.pow(var21, (double)var24);
                }
            }

            this.interpolatedDydx1 = 0.0D;

            for(var23 = 1; var23 < 4; ++var23) {
                for(var24 = 0; var24 < 4; ++var24) {
                    this.interpolatedDydx1 += (double)var23 * var18[var23][var24] * Math.pow(var19, (double)(var23 - 1)) * Math.pow(var21, (double)var24);
                }
            }

            this.interpolatedDydx2 = 0.0D;

            for(var23 = 0; var23 < 4; ++var23) {
                for(var24 = 1; var24 < 4; ++var24) {
                    this.interpolatedDydx2 += (double)var24 * var18[var23][var24] * Math.pow(var19, (double)var23) * Math.pow(var21, (double)(var24 - 1));
                }
            }

            this.interpolatedD2ydx1dx2 = 0.0D;

            for(var23 = 1; var23 < 4; ++var23) {
                for(var24 = 1; var24 < 4; ++var24) {
                    this.interpolatedD2ydx1dx2 += (double)(var23 * var24) * var18[var23][var24] * Math.pow(var19, (double)(var23 - 1)) * Math.pow(var21, (double)(var24 - 1));
                }
            }

            return this.interpolatedValue;
        }
    }

    public double[] getInterpolatedValues() {
        double[] var1 = new double[]{this.interpolatedValue, this.interpolatedDydx1, this.interpolatedDydx2, this.interpolatedD2ydx1dx2, this.xx1, this.xx2};
        return var1;
    }

    public double[][] getGridDydx1() {
        double[][] var1 = new double[this.nPoints][this.mPoints];

        for(int var2 = 0; var2 < this.nPoints; ++var2) {
            for(int var3 = 0; var3 < this.mPoints; ++var3) {
                var1[this.x1indices[var2]][this.x2indices[var3]] = this.dydx1[var2][var3];
            }
        }

        return var1;
    }

    public double[][] getGridDydx2() {
        double[][] var1 = new double[this.nPoints][this.mPoints];

        for(int var2 = 0; var2 < this.nPoints; ++var2) {
            for(int var3 = 0; var3 < this.mPoints; ++var3) {
                var1[this.x1indices[var2]][this.x2indices[var3]] = this.dydx2[var2][var3];
            }
        }

        return var1;
    }

    public double[][] getGridD2ydx1dx2() {
        double[][] var1 = new double[this.nPoints][this.mPoints];

        for(int var2 = 0; var2 < this.nPoints; ++var2) {
            for(int var3 = 0; var3 < this.mPoints; ++var3) {
                var1[this.x1indices[var2]][this.x2indices[var3]] = this.d2ydx1dx2[var2][var3];
            }
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
}
