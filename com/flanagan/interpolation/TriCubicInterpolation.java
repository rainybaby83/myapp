//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.interpolation;

import com.flanagan.math.ArrayMaths;
import com.flanagan.math.Conv;
import com.flanagan.math.Fmath;
import com.flanagan.math.Matrix;
import java.util.ArrayList;

public class TriCubicInterpolation {
    int[][] unitCube = new int[][]{{0, 0, 0}, {1, 0, 0}, {1, 1, 0}, {0, 1, 0}, {0, 0, 1}, {1, 0, 1}, {1, 1, 1}, {0, 1, 1}};
    private int lPoints = 0;
    private int mPoints = 0;
    private int nPoints = 0;
    private double[] x1 = null;
    private double[] x2 = null;
    private double[] x3 = null;
    private double[][][] y = (double[][][])null;
    private double[][][] dydx1 = (double[][][])null;
    private double[][][] dydx2 = (double[][][])null;
    private double[][][] dydx3 = (double[][][])null;
    private double[][][] d2ydx1dx2 = (double[][][])null;
    private double[][][] d2ydx1dx3 = (double[][][])null;
    private double[][][] d2ydx2dx3 = (double[][][])null;
    private double[][][] d3ydx1dx2dx3 = (double[][][])null;
    private boolean derivCalculated = false;
    private TriCubicSpline tcs = null;
    private double incrX1 = 0.0D;
    private double incrX2 = 0.0D;
    private double incrX3 = 0.0D;
    private double xx1 = 0.0D / 0.0;
    private double xx2 = 0.0D / 0.0;
    private double xx3 = 0.0D / 0.0;
    private ArrayList<Object> coeff = new ArrayList();
    private double[][] weights = new double[64][64];
    private int[] x1indices = null;
    private int[] x2indices = null;
    private int[] x3indices = null;
    private double[] xMin = new double[3];
    private double[] xMax = new double[3];
    private double interpolatedValue = 0.0D / 0.0;
    private double interpolatedDydx1 = 0.0D / 0.0;
    private double interpolatedDydx2 = 0.0D / 0.0;
    private double interpolatedDydx3 = 0.0D / 0.0;
    private double interpolatedD2ydx1dx2 = 0.0D / 0.0;
    private double interpolatedD2ydx1dx3 = 0.0D / 0.0;
    private double interpolatedD2ydx2dx3 = 0.0D / 0.0;
    private double interpolatedD3ydx1dx2dx3 = 0.0D / 0.0;
    private boolean numerDiffFlag = true;
    private static double delta = 0.001D;
    private static double potentialRoundingError = 5.0E-15D;
    private static boolean roundingCheck = false;

    public TriCubicInterpolation(double[] var1, double[] var2, double[] var3, double[][][] var4, int var5) {
        if (var5 == 0) {
            this.numerDiffFlag = false;
        } else {
            if (var5 != 1) {
                throw new IllegalArgumentException("The numerical differencing option, " + var5 + ", must be 0 or 1");
            }

            this.numerDiffFlag = true;
        }

        this.initialize(Conv.copy(var1), Conv.copy(var2), Conv.copy(var3), Conv.copy(var4));
        this.calcDeriv();
        this.gridCoefficients();
    }

    public TriCubicInterpolation(double[] var1, double[] var2, double[] var3, double[][][] var4, double[][][] var5, double[][][] var6, double[][][] var7, double[][][] var8, double[][][] var9, double[][][] var10, double[][][] var11) {
        this.initialize(Conv.copy(var1), Conv.copy(var2), Conv.copy(var3), Conv.copy(var4), Conv.copy(var5), Conv.copy(var6), Conv.copy(var7), Conv.copy(var8), Conv.copy(var9), Conv.copy(var10), Conv.copy(var11));
        this.gridCoefficients();
    }

    private void initialize(double[] var1, double[] var2, double[] var3, double[][][] var4) {
        this.initialize(var1, var2, var3, var4, (double[][][])null, (double[][][])null, (double[][][])null, (double[][][])null, (double[][][])null, (double[][][])null, (double[][][])null, false);
    }

    private void initialize(double[] var1, double[] var2, double[] var3, double[][][] var4, double[][][] var5, double[][][] var6, double[][][] var7, double[][][] var8, double[][][] var9, double[][][] var10, double[][][] var11) {
        this.initialize(var1, var2, var3, var4, var5, var6, this.dydx3, var8, var9, var10, var11, true);
    }

    private void initialize(double[] var1, double[] var2, double[] var3, double[][][] var4, double[][][] var5, double[][][] var6, double[][][] var7, double[][][] var8, double[][][] var9, double[][][] var10, double[][][] var11, boolean var12) {
        int var13 = var1.length;
        int var14 = var2.length;
        int var15 = var3.length;
        if (var13 != var4.length) {
            throw new IllegalArgumentException("Array x1 and y-row are of different length " + var13 + " " + var4.length);
        } else if (var14 != var4[0].length) {
            throw new IllegalArgumentException("Array x2 and y-column are of different length " + var14 + " " + var4[0].length);
        } else if (var15 != var4[0][0].length) {
            throw new IllegalArgumentException("Array x3 and y-column are of different length " + var15 + " " + var4[0][0].length);
        } else if (var13 >= 2 && var14 >= 2 && var15 >= 2) {
            this.calcWeights();
            ArrayMaths var16 = new ArrayMaths(var1);
            var16 = var16.sort();
            this.x1indices = var16.originalIndices();
            var1 = var16.array();
            double[][][] var17 = new double[var13][var14][var15];
            double[][][] var18 = (double[][][])null;
            double[][][] var19 = (double[][][])null;
            double[][][] var20 = (double[][][])null;

            int var21;
            int var22;
            int var23;
            for(var21 = 0; var21 < var13; ++var21) {
                for(var22 = 0; var22 < var14; ++var22) {
                    for(var23 = 0; var23 < var15; ++var23) {
                        var17[var21][var22][var23] = var4[this.x1indices[var21]][var22][var23];
                    }
                }
            }

            for(var21 = 0; var21 < var13; ++var21) {
                for(var22 = 0; var22 < var14; ++var22) {
                    for(var23 = 0; var23 < var15; ++var23) {
                        var4[var21][var22][var23] = var17[var21][var22][var23];
                    }
                }
            }

            if (var12) {
                var18 = new double[var13][var14][var15];
                var19 = new double[var13][var14][var15];
                var20 = new double[var13][var14][var15];

                for(var21 = 0; var21 < var13; ++var21) {
                    for(var22 = 0; var22 < var14; ++var22) {
                        for(var23 = 0; var23 < var15; ++var23) {
                            var18[var21][var22][var23] = var5[this.x1indices[var21]][var22][var23];
                            var19[var21][var22][var23] = var6[this.x1indices[var21]][var22][var23];
                            var20[var21][var22][var23] = var8[this.x1indices[var21]][var22][var23];
                        }
                    }
                }

                for(var21 = 0; var21 < var13; ++var21) {
                    for(var22 = 0; var22 < var14; ++var22) {
                        for(var23 = 0; var23 < var15; ++var23) {
                            var5[var21][var22][var23] = var18[var21][var22][var23];
                            var6[var21][var22][var23] = var19[var21][var22][var23];
                            var8[var21][var22][var23] = var20[var21][var22][var23];
                        }
                    }
                }
            }

            var16 = new ArrayMaths(var2);
            var16 = var16.sort();
            this.x2indices = var16.originalIndices();
            var2 = var16.array();

            for(var21 = 0; var21 < var13; ++var21) {
                for(var22 = 0; var22 < var14; ++var22) {
                    for(var23 = 0; var23 < var15; ++var23) {
                        var17[var21][var22][var23] = var4[var21][this.x2indices[var22]][var23];
                    }
                }
            }

            for(var21 = 0; var21 < var13; ++var21) {
                for(var22 = 0; var22 < var14; ++var22) {
                    for(var23 = 0; var23 < var15; ++var23) {
                        var4[var21][var22][var23] = var17[var21][var22][var23];
                    }
                }
            }

            if (var12) {
                for(var21 = 0; var21 < var13; ++var21) {
                    for(var22 = 0; var22 < var14; ++var22) {
                        for(var23 = 0; var23 < var15; ++var23) {
                            var18[var21][var22][var23] = var5[var21][this.x2indices[var22]][var23];
                            var19[var21][var22][var23] = var6[var21][this.x2indices[var22]][var23];
                            var20[var21][var22][var23] = var8[var21][this.x2indices[var22]][var23];
                        }
                    }
                }

                for(var21 = 0; var21 < var13; ++var21) {
                    for(var22 = 0; var22 < var14; ++var22) {
                        for(var23 = 0; var23 < var15; ++var23) {
                            var5[var21][var22][var23] = var18[var21][var22][var23];
                            var6[var21][var22][var23] = var19[var21][var22][var23];
                            var8[var21][var22][var23] = var20[var21][var22][var23];
                        }
                    }
                }
            }

            var16 = new ArrayMaths(var3);
            var16 = var16.sort();
            this.x3indices = var16.originalIndices();
            var3 = var16.array();

            for(var21 = 0; var21 < var13; ++var21) {
                for(var22 = 0; var22 < var14; ++var22) {
                    for(var23 = 0; var23 < var15; ++var23) {
                        var17[var21][var22][var23] = var4[var21][var22][this.x3indices[var23]];
                    }
                }
            }

            for(var21 = 0; var21 < var13; ++var21) {
                for(var22 = 0; var22 < var14; ++var22) {
                    for(var23 = 0; var23 < var15; ++var23) {
                        var4[var21][var22][var23] = var17[var21][var22][var23];
                    }
                }
            }

            if (var12) {
                for(var21 = 0; var21 < var13; ++var21) {
                    for(var22 = 0; var22 < var14; ++var22) {
                        for(var23 = 0; var23 < var15; ++var23) {
                            var18[var21][var22][var23] = var5[var21][var22][this.x3indices[var23]];
                            var19[var21][var22][var23] = var6[var21][var22][this.x3indices[var23]];
                            var20[var21][var22][var23] = var8[var21][var22][this.x3indices[var23]];
                        }
                    }
                }

                for(var21 = 0; var21 < var13; ++var21) {
                    for(var22 = 0; var22 < var14; ++var22) {
                        for(var23 = 0; var23 < var15; ++var23) {
                            var5[var21][var22][var23] = var18[var21][var22][var23];
                            var6[var21][var22][var23] = var19[var21][var22][var23];
                            var8[var21][var22][var23] = var20[var21][var22][var23];
                        }
                    }
                }
            }

            double var43;
            for(var21 = 1; var21 < var13; ++var21) {
                if (var1[var21] == var1[var21 - 1]) {
                    System.out.println("x1[" + this.x1indices[var21] + "] and x1[" + this.x1indices[var21 + 1] + "] are identical, " + var1[var21]);
                    var43 = (Fmath.maximum(var1) - Fmath.minimum(var1)) / 5.0E-4D;
                    var1[var21 - 1] -= var43;
                    var1[var21] += var43;
                    System.out.println("They have been separated by" + 2.0D * var43);
                }
            }

            for(var21 = 1; var21 < var14; ++var21) {
                if (var2[var21] == var2[var21 - 1]) {
                    System.out.println("x2[" + this.x2indices[var21] + "] and x2[" + this.x2indices[var21 + 1] + "] are identical, " + var2[var21]);
                    var43 = (Fmath.maximum(var2) - Fmath.minimum(var2)) / 5.0E-4D;
                    var2[var21 - 1] -= var43;
                    var2[var21] += var43;
                    System.out.println("They have been separated by" + 2.0D * var43);
                }
            }

            for(var21 = 1; var21 < var15; ++var21) {
                if (var3[var21] == var3[var21 - 1]) {
                    System.out.println("x3[" + this.x3indices[var21] + "] and x3[" + this.x3indices[var21 + 1] + "] are identical, " + var3[var21]);
                    var43 = (Fmath.maximum(var3) - Fmath.minimum(var3)) / 5.0E-4D;
                    var3[var21 - 1] -= var43;
                    var3[var21] += var43;
                    System.out.println("They have been separated by" + 2.0D * var43);
                }
            }

            this.lPoints = var13;
            this.mPoints = var14;
            this.nPoints = var15;
            this.x1 = new double[this.lPoints];
            this.x2 = new double[this.mPoints];
            this.x3 = new double[this.nPoints];
            this.y = new double[this.lPoints][this.mPoints][this.nPoints];
            this.dydx1 = new double[this.lPoints][this.mPoints][this.nPoints];
            this.dydx2 = new double[this.lPoints][this.mPoints][this.nPoints];
            this.dydx3 = new double[this.lPoints][this.mPoints][this.nPoints];
            this.d2ydx1dx2 = new double[this.lPoints][this.mPoints][this.nPoints];
            this.d2ydx1dx3 = new double[this.lPoints][this.mPoints][this.nPoints];
            this.d2ydx2dx3 = new double[this.lPoints][this.mPoints][this.nPoints];
            this.d3ydx1dx2dx3 = new double[this.lPoints][this.mPoints][this.nPoints];

            for(var21 = 0; var21 < this.lPoints; ++var21) {
                this.x1[var21] = var1[var21];
            }

            for(var21 = 0; var21 < this.mPoints; ++var21) {
                this.x2[var21] = var2[var21];
            }

            for(var21 = 0; var21 < this.nPoints; ++var21) {
                this.x3[var21] = var3[var21];
            }

            for(var21 = 0; var21 < this.lPoints; ++var21) {
                for(var22 = 0; var22 < this.mPoints; ++var22) {
                    for(var23 = 0; var23 < this.nPoints; ++var23) {
                        this.y[var21][var22][var23] = var4[var21][var22][var23];
                    }
                }
            }

            if (var12) {
                for(var21 = 0; var21 < this.lPoints; ++var21) {
                    for(var22 = 0; var22 < this.mPoints; ++var22) {
                        for(var23 = 0; var23 < this.nPoints; ++var23) {
                            this.dydx1[var21][var22][var23] = var5[var21][var22][var23];
                            this.dydx2[var21][var22][var23] = var6[var21][var22][var23];
                            this.dydx3[var21][var22][var23] = this.dydx3[var21][var22][var23];
                            this.d2ydx1dx2[var21][var22][var23] = var8[var21][var22][var23];
                            this.d2ydx1dx3[var21][var22][var23] = var9[var21][var22][var23];
                            this.d2ydx2dx3[var21][var22][var23] = var10[var21][var22][var23];
                            this.d3ydx1dx2dx3[var21][var22][var23] = var11[var21][var22][var23];
                        }
                    }
                }

                this.derivCalculated = true;
            }

            this.xMin[0] = Fmath.minimum(this.x1);
            this.xMax[0] = Fmath.maximum(this.x1);
            this.xMin[1] = Fmath.minimum(this.x2);
            this.xMax[1] = Fmath.maximum(this.x2);
            this.xMin[2] = Fmath.minimum(this.x3);
            this.xMax[2] = Fmath.maximum(this.x3);
            if (!var12 && this.numerDiffFlag) {
                double var45 = this.xMax[0] - this.xMin[0];
                double var44 = this.xMax[1] - this.xMin[1];
                double var25 = this.xMax[2] - this.xMin[2];
                double var27 = var45 / (double)this.lPoints;
                double var29 = var44 / (double)this.mPoints;
                double var31 = var25 / (double)this.nPoints;
                double var33 = this.x1[1] - this.x1[0];
                double var35 = var33;

                for(int var37 = 2; var37 < this.lPoints; ++var37) {
                    var33 = this.x1[var37] - this.x1[var37 - 1];
                    if (var33 < var35) {
                        var35 = var33;
                    }
                }

                var33 = this.x2[1] - this.x2[0];
                double var46 = var33;

                for(int var39 = 2; var39 < this.mPoints; ++var39) {
                    var33 = this.x2[var39] - this.x2[var39 - 1];
                    if (var33 < var46) {
                        var46 = var33;
                    }
                }

                var33 = this.x3[1] - this.x3[0];
                double var47 = var33;

                for(int var41 = 2; var41 < this.nPoints; ++var41) {
                    var33 = this.x3[var41] - this.x3[var41 - 1];
                    if (var33 < var47) {
                        var47 = var33;
                    }
                }

                this.incrX1 = var45 * delta;
                double var48 = var35;
                if (var35 < var27 / 10.0D) {
                    var48 = var27 / 10.0D;
                }

                if (this.incrX1 > var27) {
                    this.incrX1 = var48;
                }

                this.incrX2 = var44 * delta;
                var48 = var46;
                if (var46 < var29 / 10.0D) {
                    var48 = var29 / 10.0D;
                }

                if (this.incrX2 > var29) {
                    this.incrX2 = var48;
                }

                this.incrX3 = var25 * delta;
                var48 = var47;
                if (var47 < var31 / 10.0D) {
                    var48 = var31 / 10.0D;
                }

                if (this.incrX3 > var31) {
                    this.incrX3 = var48;
                }
            }

        } else {
            throw new IllegalArgumentException("The data matrix must have a minimum size of 2 X 2 X 2");
        }
    }

    private void calcWeights() {
        int var1 = 0;

        int var2;
        int var3;
        int var4;
        int var5;
        int var6;
        for(var2 = 0; var2 < 8; ++var2) {
            var3 = 0;

            for(var4 = 0; var4 < 4; ++var4) {
                for(var5 = 0; var5 < 4; ++var5) {
                    for(var6 = 0; var6 < 4; ++var6) {
                        this.weights[var1][var3] = Math.pow((double)this.unitCube[var2][0], (double)var4) * Math.pow((double)this.unitCube[var2][1], (double)var5) * Math.pow((double)this.unitCube[var2][2], (double)var6);
                        ++var3;
                    }
                }
            }

            ++var1;
        }

        for(var2 = 0; var2 < 8; ++var2) {
            var3 = 0;

            for(var4 = 0; var4 < 4; ++var4) {
                for(var5 = 0; var5 < 4; ++var5) {
                    for(var6 = 0; var6 < 4; ++var6) {
                        if (var4 == 0) {
                            this.weights[var1][var3] = 0.0D;
                        } else {
                            this.weights[var1][var3] = (double)var4 * Math.pow((double)this.unitCube[var2][0], (double)(var4 - 1)) * Math.pow((double)this.unitCube[var2][1], (double)var5) * Math.pow((double)this.unitCube[var2][2], (double)var6);
                        }

                        ++var3;
                    }
                }
            }

            ++var1;
        }

        for(var2 = 0; var2 < 8; ++var2) {
            var3 = 0;

            for(var4 = 0; var4 < 4; ++var4) {
                for(var5 = 0; var5 < 4; ++var5) {
                    for(var6 = 0; var6 < 4; ++var6) {
                        if (var5 == 0) {
                            this.weights[var1][var3] = 0.0D;
                        } else {
                            this.weights[var1][var3] = (double)var5 * Math.pow((double)this.unitCube[var2][0], (double)var4) * Math.pow((double)this.unitCube[var2][1], (double)(var5 - 1)) * Math.pow((double)this.unitCube[var2][2], (double)var6);
                        }

                        ++var3;
                    }
                }
            }

            ++var1;
        }

        for(var2 = 0; var2 < 8; ++var2) {
            var3 = 0;

            for(var4 = 0; var4 < 4; ++var4) {
                for(var5 = 0; var5 < 4; ++var5) {
                    for(var6 = 0; var6 < 4; ++var6) {
                        if (var6 == 0) {
                            this.weights[var1][var3] = 0.0D;
                        } else {
                            this.weights[var1][var3] = (double)var6 * Math.pow((double)this.unitCube[var2][0], (double)var4) * Math.pow((double)this.unitCube[var2][1], (double)var5) * Math.pow((double)this.unitCube[var2][2], (double)(var6 - 1));
                        }

                        ++var3;
                    }
                }
            }

            ++var1;
        }

        for(var2 = 0; var2 < 8; ++var2) {
            var3 = 0;

            for(var4 = 0; var4 < 4; ++var4) {
                for(var5 = 0; var5 < 4; ++var5) {
                    for(var6 = 0; var6 < 4; ++var6) {
                        if (var4 != 0 && var5 != 0) {
                            this.weights[var1][var3] = (double)(var4 * var5) * Math.pow((double)this.unitCube[var2][0], (double)(var4 - 1)) * Math.pow((double)this.unitCube[var2][1], (double)(var5 - 1)) * Math.pow((double)this.unitCube[var2][2], (double)var6);
                        } else {
                            this.weights[var1][var3] = 0.0D;
                        }

                        ++var3;
                    }
                }
            }

            ++var1;
        }

        for(var2 = 0; var2 < 8; ++var2) {
            var3 = 0;

            for(var4 = 0; var4 < 4; ++var4) {
                for(var5 = 0; var5 < 4; ++var5) {
                    for(var6 = 0; var6 < 4; ++var6) {
                        if (var4 != 0 && var6 != 0) {
                            this.weights[var1][var3] = (double)(var4 * var6) * Math.pow((double)this.unitCube[var2][0], (double)(var4 - 1)) * Math.pow((double)this.unitCube[var2][1], (double)var5) * Math.pow((double)this.unitCube[var2][2], (double)(var6 - 1));
                        } else {
                            this.weights[var1][var3] = 0.0D;
                        }

                        ++var3;
                    }
                }
            }

            ++var1;
        }

        for(var2 = 0; var2 < 8; ++var2) {
            var3 = 0;

            for(var4 = 0; var4 < 4; ++var4) {
                for(var5 = 0; var5 < 4; ++var5) {
                    for(var6 = 0; var6 < 4; ++var6) {
                        if (var5 != 0 && var6 != 0) {
                            this.weights[var1][var3] = (double)(var5 * var6) * Math.pow((double)this.unitCube[var2][0], (double)var4) * Math.pow((double)this.unitCube[var2][1], (double)(var5 - 1)) * Math.pow((double)this.unitCube[var2][2], (double)(var6 - 1));
                        } else {
                            this.weights[var1][var3] = 0.0D;
                        }

                        ++var3;
                    }
                }
            }

            ++var1;
        }

        for(var2 = 0; var2 < 8; ++var2) {
            var3 = 0;

            for(var4 = 0; var4 < 4; ++var4) {
                for(var5 = 0; var5 < 4; ++var5) {
                    for(var6 = 0; var6 < 4; ++var6) {
                        if (var4 != 0 && var5 != 0 && var6 != 0) {
                            this.weights[var1][var3] = (double)(var4 * var5 * var6) * Math.pow((double)this.unitCube[var2][0], (double)(var4 - 1)) * Math.pow((double)this.unitCube[var2][1], (double)(var5 - 1)) * Math.pow((double)this.unitCube[var2][2], (double)(var6 - 1));
                        } else {
                            this.weights[var1][var3] = 0.0D;
                        }

                        ++var3;
                    }
                }
            }

            ++var1;
        }

        Matrix var7 = new Matrix(this.weights);
        var7 = var7.inverse();
        this.weights = var7.getArrayCopy();
    }

    private void calcDeriv() {
        int var7;
        int var8;
        int var9;
        if (this.numerDiffFlag) {
            this.tcs = new TriCubicSpline(this.x1, this.x2, this.x3, this.y);
            double[] var1 = new double[this.lPoints];
            double[] var2 = new double[this.lPoints];
            double[] var3 = new double[this.mPoints];
            double[] var4 = new double[this.mPoints];
            double[] var5 = new double[this.nPoints];
            double[] var6 = new double[this.nPoints];

            for(var7 = 0; var7 < this.lPoints; ++var7) {
                var1[var7] = this.x1[var7] + this.incrX1;
                if (var1[var7] > this.x1[this.lPoints - 1]) {
                    var1[var7] = this.x1[this.lPoints - 1];
                }

                var2[var7] = this.x1[var7] - this.incrX1;
                if (var2[var7] < this.x1[0]) {
                    var2[var7] = this.x1[0];
                }
            }

            for(var7 = 0; var7 < this.mPoints; ++var7) {
                var3[var7] = this.x2[var7] + this.incrX2;
                if (var3[var7] > this.x2[this.mPoints - 1]) {
                    var3[var7] = this.x2[this.mPoints - 1];
                }

                var4[var7] = this.x2[var7] - this.incrX2;
                if (var4[var7] < this.x2[0]) {
                    var4[var7] = this.x2[0];
                }
            }

            for(var7 = 0; var7 < this.nPoints; ++var7) {
                var5[var7] = this.x3[var7] + this.incrX3;
                if (var5[var7] > this.x3[this.nPoints - 1]) {
                    var5[var7] = this.x3[this.nPoints - 1];
                }

                var6[var7] = this.x3[var7] - this.incrX3;
                if (var6[var7] < this.x3[0]) {
                    var6[var7] = this.x3[0];
                }
            }

            for(var7 = 0; var7 < this.lPoints; ++var7) {
                for(var8 = 0; var8 < this.mPoints; ++var8) {
                    for(var9 = 0; var9 < this.nPoints; ++var9) {
                        this.dydx1[var7][var8][var9] = (this.tcs.interpolate(var1[var7], this.x2[var8], this.x3[var9]) - this.tcs.interpolate(var2[var7], this.x2[var8], this.x3[var9])) / (var1[var7] - var2[var7]);
                        this.dydx2[var7][var8][var9] = (this.tcs.interpolate(this.x1[var7], var3[var8], this.x3[var9]) - this.tcs.interpolate(this.x1[var7], var4[var8], this.x3[var9])) / (var3[var8] - var4[var8]);
                        this.dydx3[var7][var8][var9] = (this.tcs.interpolate(this.x1[var7], this.x2[var8], var5[var9]) - this.tcs.interpolate(this.x1[var7], this.x2[var8], var6[var9])) / (var5[var9] - var6[var9]);
                        this.d2ydx1dx2[var7][var8][var9] = (this.tcs.interpolate(var1[var7], var3[var8], this.x3[var9]) - this.tcs.interpolate(var1[var7], var4[var8], this.x3[var9]) - this.tcs.interpolate(var2[var7], var3[var8], this.x3[var9]) + this.tcs.interpolate(var2[var7], var4[var8], this.x3[var9])) / ((var1[var7] - var2[var7]) * (var3[var8] - var4[var8]));
                        this.d2ydx1dx3[var7][var8][var9] = (this.tcs.interpolate(var1[var7], this.x2[var8], var5[var9]) - this.tcs.interpolate(var1[var7], this.x2[var8], var6[var9]) - this.tcs.interpolate(var2[var7], this.x2[var8], var5[var9]) + this.tcs.interpolate(var2[var7], this.x2[var8], var6[var9])) / ((var1[var7] - var2[var7]) * (var5[var9] - var6[var9]));
                        this.d2ydx2dx3[var7][var8][var9] = (this.tcs.interpolate(this.x1[var7], var3[var8], var5[var9]) - this.tcs.interpolate(this.x1[var7], var3[var8], var6[var9]) - this.tcs.interpolate(this.x1[var7], var4[var8], var5[var9]) + this.tcs.interpolate(this.x1[var7], var4[var8], var6[var9])) / ((var3[var8] - var4[var8]) * (var5[var9] - var6[var9]));
                        this.d3ydx1dx2dx3[var7][var8][var9] = (this.tcs.interpolate(var1[var7], var3[var8], var5[var9]) - this.tcs.interpolate(var1[var7], var4[var8], var5[var9]) - this.tcs.interpolate(var2[var7], var3[var8], var5[var9]) + this.tcs.interpolate(var2[var7], var4[var8], var5[var9]) - (this.tcs.interpolate(var1[var7], var3[var8], var6[var9]) - this.tcs.interpolate(var1[var7], var4[var8], var6[var9]) - this.tcs.interpolate(var2[var7], var3[var8], var6[var9]) + this.tcs.interpolate(var2[var7], var4[var8], var6[var9]))) / ((var1[var7] - var2[var7]) * (var3[var8] - var4[var8]) * (var5[var9] - var6[var9]));
                    }
                }
            }
        } else {
            boolean var10 = false;
            boolean var12 = false;
            boolean var14 = false;
            boolean var16 = false;
            boolean var18 = false;
            boolean var20 = false;

            for(var7 = 0; var7 < this.lPoints; ++var7) {
                int var11 = var7 + 1;
                if (var11 >= this.lPoints) {
                    var11 = this.lPoints - 1;
                }

                int var13 = var7 - 1;
                if (var13 < 0) {
                    var13 = 0;
                }

                for(var8 = 0; var8 < this.mPoints; ++var8) {
                    int var15 = var8 + 1;
                    if (var15 >= this.mPoints) {
                        var15 = this.mPoints - 1;
                    }

                    int var17 = var8 - 1;
                    if (var17 < 0) {
                        var17 = 0;
                    }

                    for(var9 = 0; var9 < this.nPoints; ++var9) {
                        int var19 = var9 + 1;
                        if (var19 >= this.nPoints) {
                            var19 = this.nPoints - 1;
                        }

                        int var21 = var9 - 1;
                        if (var21 < 0) {
                            var21 = 0;
                        }

                        this.dydx1[var7][var8][var9] = (this.y[var11][var8][var9] - this.y[var13][var8][var9]) / (this.x1[var11] - this.x1[var13]);
                        this.dydx2[var7][var8][var9] = (this.y[var7][var15][var9] - this.y[var7][var17][var9]) / (this.x2[var15] - this.x2[var17]);
                        this.dydx3[var7][var8][var9] = (this.y[var7][var8][var19] - this.y[var7][var8][var21]) / (this.x3[var19] - this.x3[var21]);
                        this.d2ydx1dx2[var7][var8][var9] = (this.y[var11][var15][var9] - this.y[var11][var17][var9] - this.y[var13][var15][var9] + this.y[var13][var17][var9]) / ((this.x1[var11] - this.x1[var13]) * (this.x2[var15] - this.x2[var17]));
                        this.d2ydx1dx3[var7][var8][var9] = (this.y[var11][var8][var19] - this.y[var11][var8][var21] - this.y[var13][var8][var19] + this.y[var13][var8][var21]) / ((this.x1[var11] - this.x1[var13]) * (this.x3[var19] - this.x3[var21]));
                        this.d2ydx2dx3[var7][var8][var9] = (this.y[var7][var15][var19] - this.y[var7][var15][var21] - this.y[var7][var17][var19] + this.y[var7][var17][var21]) / ((this.x2[var15] - this.x2[var17]) * (this.x3[var19] - this.x3[var21]));
                        this.d2ydx1dx2[var7][var8][var9] = (this.y[var11][var15][var19] - this.y[var11][var17][var19] - this.y[var13][var15][var19] + this.y[var13][var17][var19] - this.y[var11][var15][var21] + this.y[var11][var17][var21] + this.y[var13][var15][var21] - this.y[var13][var17][var21]) / ((this.x1[var11] - this.x1[var13]) * (this.x2[var15] - this.x2[var17]) * (this.x3[var19] - this.x3[var21]));
                    }
                }
            }
        }

        this.derivCalculated = true;
    }

    private void gridCoefficients() {
        double[] var1 = new double[8];
        double[] var2 = new double[8];
        double[] var3 = new double[8];
        double[] var4 = new double[8];
        double[] var5 = new double[8];
        double[] var6 = new double[8];
        double[] var7 = new double[8];
        double[] var8 = new double[8];
        double[] var9 = new double[64];
        double[] var10 = new double[64];
        double var11 = 0.0D;
        double var13 = 0.0D;
        double var15 = 0.0D;

        for(int var17 = 0; var17 < this.lPoints - 1; ++var17) {
            var11 = this.x1[var17 + 1] - this.x1[var17];

            for(int var18 = 0; var18 < this.mPoints - 1; ++var18) {
                var13 = this.x2[var18 + 1] - this.x2[var18];

                for(int var19 = 0; var19 < this.nPoints - 1; ++var19) {
                    var15 = this.x3[var19 + 1] - this.x3[var19];
                    double[][][] var20 = new double[4][4][4];
                    this.coeff.add(new Double(var11));
                    this.coeff.add(new Double(this.x1[var17]));
                    this.coeff.add(new Double(var13));
                    this.coeff.add(new Double(this.x2[var18]));
                    this.coeff.add(new Double(var15));
                    this.coeff.add(new Double(this.x3[var19]));

                    int var21;
                    for(var21 = 0; var21 < 8; ++var21) {
                        var1[var21] = this.y[var17 + this.unitCube[var21][0]][var18 + this.unitCube[var21][1]][var19 + this.unitCube[var21][2]];
                        var2[var21] = this.dydx1[var17 + this.unitCube[var21][0]][var18 + this.unitCube[var21][1]][var19 + this.unitCube[var21][2]];
                        var3[var21] = this.dydx2[var17 + this.unitCube[var21][0]][var18 + this.unitCube[var21][1]][var19 + this.unitCube[var21][2]];
                        var4[var21] = this.dydx3[var17 + this.unitCube[var21][0]][var18 + this.unitCube[var21][1]][var19 + this.unitCube[var21][2]];
                        var5[var21] = this.d2ydx1dx2[var17 + this.unitCube[var21][0]][var18 + this.unitCube[var21][1]][var19 + this.unitCube[var21][2]];
                        var6[var21] = this.d2ydx1dx3[var17 + this.unitCube[var21][0]][var18 + this.unitCube[var21][1]][var19 + this.unitCube[var21][2]];
                        var7[var21] = this.d2ydx2dx3[var17 + this.unitCube[var21][0]][var18 + this.unitCube[var21][1]][var19 + this.unitCube[var21][2]];
                        var8[var21] = this.d3ydx1dx2dx3[var17 + this.unitCube[var21][0]][var18 + this.unitCube[var21][1]][var19 + this.unitCube[var21][2]];
                    }

                    for(var21 = 0; var21 < 8; ++var21) {
                        var10[var21] = var1[var21];
                        var10[var21 + 8] = var2[var21] * var11;
                        var10[var21 + 16] = var3[var21] * var13;
                        var10[var21 + 24] = var4[var21] * var15;
                        var10[var21 + 32] = var5[var21] * var11 * var13;
                        var10[var21 + 40] = var6[var21] * var11 * var15;
                        var10[var21 + 48] = var7[var21] * var13 * var15;
                        var10[var21 + 56] = var8[var21] * var11 * var13 * var15;
                    }

                    double var27 = 0.0D;

                    int var23;
                    int var24;
                    for(var23 = 0; var23 < 64; ++var23) {
                        for(var24 = 0; var24 < 64; ++var24) {
                            var27 += this.weights[var23][var24] * var10[var24];
                        }

                        var9[var23] = var27;
                        var27 = 0.0D;
                    }

                    var23 = 0;

                    for(var24 = 0; var24 < 4; ++var24) {
                        for(int var25 = 0; var25 < 4; ++var25) {
                            for(int var26 = 0; var26 < 4; ++var26) {
                                var20[var24][var25][var26] = var9[var23++];
                            }
                        }
                    }

                    this.coeff.add(var20);
                }
            }
        }

    }

    public double interpolate(double var1, double var3, double var5) {
        if (var1 < this.x1[0]) {
            if (var1 < this.x1[0] - potentialRoundingError) {
                throw new IllegalArgumentException(var1 + " is outside the limits, " + this.x1[0] + " - " + this.x1[this.lPoints - 1]);
            }

            var1 = this.x1[0];
        }

        if (var3 < this.x2[0]) {
            if (var3 < this.x2[0] - potentialRoundingError) {
                throw new IllegalArgumentException(var3 + " is outside the limits, " + this.x2[0] + " - " + this.x2[this.mPoints - 1]);
            }

            var3 = this.x2[0];
        }

        if (var5 < this.x3[0]) {
            if (var5 < this.x3[0] - potentialRoundingError) {
                throw new IllegalArgumentException(var1 + " is outside the limits, " + this.x3[0] + " - " + this.x3[this.nPoints - 1]);
            }

            var5 = this.x3[0];
        }

        if (var1 > this.x1[this.lPoints - 1]) {
            if (var1 > this.x1[this.lPoints - 1] + potentialRoundingError) {
                throw new IllegalArgumentException(var1 + " is outside the limits, " + this.x1[0] + " - " + this.x1[this.lPoints - 1]);
            }

            var1 = this.x1[this.lPoints - 1];
        }

        if (var3 > this.x2[this.mPoints - 1]) {
            if (var3 > this.x2[this.mPoints - 1] + potentialRoundingError) {
                throw new IllegalArgumentException(var3 + " is outside the limits, " + this.x2[0] + " - " + this.x2[this.mPoints - 1]);
            }

            var3 = this.x2[this.mPoints - 1];
        }

        if (var5 > this.x3[this.nPoints - 1]) {
            if (var5 > this.x3[this.nPoints - 1] + potentialRoundingError) {
                throw new IllegalArgumentException(var5 + " is outside the limits, " + this.x3[0] + " - " + this.x3[this.nPoints - 1]);
            }

            var5 = this.x3[this.mPoints - 1];
        }

        this.xx1 = var1;
        this.xx2 = var3;
        this.xx3 = var5;
        int var7 = 0;
        double var8 = (Double)this.coeff.get(7 * var7);
        double var10 = (Double)this.coeff.get(7 * var7 + 1);
        double var12 = (Double)this.coeff.get(7 * var7 + 2);
        double var14 = (Double)this.coeff.get(7 * var7 + 3);
        double var16 = (Double)this.coeff.get(7 * var7 + 4);
        double var18 = (Double)this.coeff.get(7 * var7 + 5);
        boolean var20 = true;

        while(var20) {
            boolean var21 = false;
            boolean var22 = false;
            boolean var23 = false;
            if (var1 >= var10 && var1 <= var10 + var8) {
                var21 = true;
            }

            if (var3 >= var14 && var3 <= var14 + var12) {
                var22 = true;
            }

            if (var5 >= var18 && var5 <= var18 + var16) {
                var23 = true;
            }

            if (var21 && var22 && var23) {
                var20 = false;
            } else {
                ++var7;
                var8 = (Double)this.coeff.get(7 * var7);
                var10 = (Double)this.coeff.get(7 * var7 + 1);
                var12 = (Double)this.coeff.get(7 * var7 + 2);
                var14 = (Double)this.coeff.get(7 * var7 + 3);
                var16 = (Double)this.coeff.get(7 * var7 + 4);
                var18 = (Double)this.coeff.get(7 * var7 + 5);
            }
        }

        double[][][] var31 = (double[][][])((double[][][])this.coeff.get(7 * var7 + 6));
        double var32 = (var1 - var10) / var8;
        double var24 = (var3 - var14) / var12;
        double var26 = (var5 - var18) / var16;
        this.interpolatedValue = 0.0D;

        int var28;
        int var29;
        int var30;
        for(var28 = 0; var28 < 4; ++var28) {
            for(var29 = 0; var29 < 4; ++var29) {
                for(var30 = 0; var30 < 4; ++var30) {
                    this.interpolatedValue += var31[var28][var29][var30] * Math.pow(var32, (double)var28) * Math.pow(var24, (double)var29) * Math.pow(var26, (double)var30);
                }
            }
        }

        this.interpolatedDydx1 = 0.0D;

        for(var28 = 1; var28 < 4; ++var28) {
            for(var29 = 0; var29 < 4; ++var29) {
                for(var30 = 0; var30 < 4; ++var30) {
                    this.interpolatedDydx1 += (double)var28 * var31[var28][var29][var30] * Math.pow(var32, (double)(var28 - 1)) * Math.pow(var24, (double)var29) * Math.pow(var26, (double)var30);
                }
            }
        }

        this.interpolatedDydx2 = 0.0D;

        for(var28 = 0; var28 < 4; ++var28) {
            for(var29 = 1; var29 < 4; ++var29) {
                for(var30 = 0; var30 < 4; ++var30) {
                    this.interpolatedDydx2 += (double)var29 * var31[var28][var29][var30] * Math.pow(var32, (double)var28) * Math.pow(var24, (double)(var29 - 1)) * Math.pow(var26, (double)var30);
                }
            }
        }

        this.interpolatedDydx3 = 0.0D;

        for(var28 = 0; var28 < 4; ++var28) {
            for(var29 = 1; var29 < 4; ++var29) {
                for(var30 = 0; var30 < 4; ++var30) {
                    this.interpolatedDydx2 += (double)var30 * var31[var28][var29][var30] * Math.pow(var32, (double)var28) * Math.pow(var24, (double)var29) * Math.pow(var26, (double)(var30 - 1));
                }
            }
        }

        this.interpolatedD2ydx1dx2 = 0.0D;

        for(var28 = 1; var28 < 4; ++var28) {
            for(var29 = 1; var29 < 4; ++var29) {
                for(var30 = 0; var30 < 4; ++var30) {
                    this.interpolatedD2ydx1dx2 += (double)(var28 * var29) * var31[var28][var29][var30] * Math.pow(var32, (double)(var28 - 1)) * Math.pow(var24, (double)(var29 - 1)) * Math.pow(var26, (double)var30);
                }
            }
        }

        return this.interpolatedValue;
    }

    public double[] getInterpolatedValues() {
        double[] var1 = new double[]{this.interpolatedValue, this.interpolatedDydx1, this.interpolatedDydx2, this.interpolatedDydx3, this.interpolatedD2ydx1dx2, this.interpolatedD2ydx1dx3, this.interpolatedD2ydx2dx3, this.interpolatedD3ydx1dx2dx3, this.xx1, this.xx2, this.xx3};
        return var1;
    }

    public double[][][] getGridDydx1() {
        double[][][] var1 = new double[this.lPoints][this.mPoints][this.nPoints];

        for(int var2 = 0; var2 < this.lPoints; ++var2) {
            for(int var3 = 0; var3 < this.mPoints; ++var3) {
                for(int var4 = 0; var4 < this.nPoints; ++var4) {
                    var1[this.x1indices[var2]][this.x2indices[var3]][this.x3indices[var4]] = this.dydx1[var2][var3][var4];
                }
            }
        }

        return var1;
    }

    public double[][][] getGridDydx2() {
        double[][][] var1 = new double[this.lPoints][this.mPoints][this.nPoints];

        for(int var2 = 0; var2 < this.lPoints; ++var2) {
            for(int var3 = 0; var3 < this.mPoints; ++var3) {
                for(int var4 = 0; var4 < this.nPoints; ++var4) {
                    var1[this.x1indices[var2]][this.x2indices[var3]][this.x3indices[var4]] = this.dydx2[var2][var3][var4];
                }
            }
        }

        return var1;
    }

    public double[][][] getGridDydx3() {
        double[][][] var1 = new double[this.lPoints][this.mPoints][this.nPoints];

        for(int var2 = 0; var2 < this.lPoints; ++var2) {
            for(int var3 = 0; var3 < this.mPoints; ++var3) {
                for(int var4 = 0; var4 < this.nPoints; ++var4) {
                    var1[this.x1indices[var2]][this.x2indices[var3]][this.x3indices[var4]] = this.dydx3[var2][var3][var4];
                }
            }
        }

        return var1;
    }

    public double[][][] getGridD2ydx1dx2() {
        double[][][] var1 = new double[this.lPoints][this.mPoints][this.nPoints];

        for(int var2 = 0; var2 < this.lPoints; ++var2) {
            for(int var3 = 0; var3 < this.mPoints; ++var3) {
                for(int var4 = 0; var4 < this.nPoints; ++var4) {
                    var1[this.x1indices[var2]][this.x2indices[var3]][this.x3indices[var4]] = this.d2ydx1dx2[var2][var3][var4];
                }
            }
        }

        return var1;
    }

    public double[][][] getGridD2ydx1dx3() {
        double[][][] var1 = new double[this.lPoints][this.mPoints][this.nPoints];

        for(int var2 = 0; var2 < this.lPoints; ++var2) {
            for(int var3 = 0; var3 < this.mPoints; ++var3) {
                for(int var4 = 0; var4 < this.nPoints; ++var4) {
                    var1[this.x1indices[var2]][this.x2indices[var3]][this.x3indices[var4]] = this.d2ydx1dx3[var2][var3][var4];
                }
            }
        }

        return var1;
    }

    public double[][][] getGridD2ydx2dx3() {
        double[][][] var1 = new double[this.lPoints][this.mPoints][this.nPoints];

        for(int var2 = 0; var2 < this.lPoints; ++var2) {
            for(int var3 = 0; var3 < this.mPoints; ++var3) {
                for(int var4 = 0; var4 < this.nPoints; ++var4) {
                    var1[this.x1indices[var2]][this.x2indices[var3]][this.x3indices[var4]] = this.d2ydx2dx3[var2][var3][var4];
                }
            }
        }

        return var1;
    }

    public double[][][] getGridD3ydx1dx2dx3() {
        double[][][] var1 = new double[this.lPoints][this.mPoints][this.nPoints];

        for(int var2 = 0; var2 < this.lPoints; ++var2) {
            for(int var3 = 0; var3 < this.mPoints; ++var3) {
                for(int var4 = 0; var4 < this.nPoints; ++var4) {
                    var1[this.x1indices[var2]][this.x2indices[var3]][this.x3indices[var4]] = this.d3ydx1dx2dx3[var2][var3][var4];
                }
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
        double[] var1 = new double[]{this.xMin[0], this.xMax[0], this.xMin[1], this.xMax[1], this.xMin[2], this.xMax[2]};
        return var1;
    }

    public void displayLimits() {
        System.out.println(" ");

        for(int var1 = 0; var1 < 3; ++var1) {
            System.out.println("The limits to the x array x" + (var1 + 1) + " are " + this.xMin[var1] + " and " + this.xMax[var1]);
        }

        System.out.println(" ");
    }
}
