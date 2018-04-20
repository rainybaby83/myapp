//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package flanagan.interpolation;

import flanagan.math.Fmath;

public class TriCubicSpline {
    private int nPoints = 0;
    private int mPoints = 0;
    private int lPoints = 0;
    private double[][][] y = (double[][][])null;
    private double[] x1 = null;
    private double[] x2 = null;
    private double[] x3 = null;
    private double[] xMin = new double[3];
    private double[] xMax = new double[3];
    private BiCubicSpline[] bcsn = null;
    private CubicSpline csm = null;
    private double[][][] d2ydx2inner = (double[][][])null;
    private boolean derivCalculated = false;
    private boolean averageIdenticalAbscissae = false;
    private static double potentialRoundingError = 5.0E-15D;
    private static boolean roundingCheck = true;

    public TriCubicSpline(double[] var1, double[] var2, double[] var3, double[][][] var4) {
        this.nPoints = var1.length;
        this.mPoints = var2.length;
        this.lPoints = var3.length;
        if (this.nPoints != var4.length) {
            throw new IllegalArgumentException("Arrays x1 and y-row are of different length " + this.nPoints + " " + var4.length);
        } else if (this.mPoints != var4[0].length) {
            throw new IllegalArgumentException("Arrays x2 and y-column are of different length " + this.mPoints + " " + var4[0].length);
        } else if (this.lPoints != var4[0][0].length) {
            throw new IllegalArgumentException("Arrays x3 and y-column are of different length " + this.mPoints + " " + var4[0][0].length);
        } else if (this.nPoints >= 3 && this.mPoints >= 3 && this.lPoints >= 3) {
            this.csm = new CubicSpline(this.nPoints);
            this.bcsn = BiCubicSpline.oneDarray(this.nPoints, this.mPoints, this.lPoints);
            this.x1 = new double[this.nPoints];
            this.x2 = new double[this.mPoints];
            this.x3 = new double[this.lPoints];
            this.y = new double[this.nPoints][this.mPoints][this.lPoints];
            this.d2ydx2inner = new double[this.nPoints][this.mPoints][this.lPoints];

            int var5;
            for(var5 = 0; var5 < this.nPoints; ++var5) {
                this.x1[var5] = var1[var5];
            }

            this.xMin[0] = Fmath.minimum(this.x1);
            this.xMax[0] = Fmath.maximum(this.x1);

            for(var5 = 0; var5 < this.mPoints; ++var5) {
                this.x2[var5] = var2[var5];
            }

            this.xMin[1] = Fmath.minimum(this.x2);
            this.xMax[1] = Fmath.maximum(this.x2);

            for(var5 = 0; var5 < this.lPoints; ++var5) {
                this.x3[var5] = var3[var5];
            }

            this.xMin[2] = Fmath.minimum(this.x3);
            this.xMax[2] = Fmath.maximum(this.x3);

            int var6;
            int var7;
            for(var5 = 0; var5 < this.nPoints; ++var5) {
                for(var6 = 0; var6 < this.mPoints; ++var6) {
                    for(var7 = 0; var7 < this.lPoints; ++var7) {
                        this.y[var5][var6][var7] = var4[var5][var6][var7];
                    }
                }
            }

            double[][] var9 = new double[this.mPoints][this.lPoints];

            for(var6 = 0; var6 < this.nPoints; ++var6) {
                for(var7 = 0; var7 < this.mPoints; ++var7) {
                    for(int var8 = 0; var8 < this.lPoints; ++var8) {
                        var9[var7][var8] = var4[var6][var7][var8];
                    }
                }

                this.bcsn[var6].resetData(var2, var3, var9);
                this.d2ydx2inner[var6] = this.bcsn[var6].getDeriv();
            }

            this.derivCalculated = true;
        } else {
            throw new IllegalArgumentException("The tabulated 3D array must have a minimum size of 3 X 3 X 3");
        }
    }

    public TriCubicSpline(int var1, int var2, int var3) {
        this.nPoints = var1;
        this.mPoints = var2;
        this.lPoints = var3;
        if (this.nPoints >= 3 && this.mPoints >= 3 && this.lPoints >= 3) {
            this.csm = new CubicSpline(this.nPoints);
            this.bcsn = BiCubicSpline.oneDarray(this.nPoints, this.mPoints, this.lPoints);
            this.x1 = new double[this.nPoints];
            this.x2 = new double[this.mPoints];
            this.x3 = new double[this.lPoints];
            this.y = new double[this.nPoints][this.mPoints][this.lPoints];
            this.d2ydx2inner = new double[this.nPoints][this.mPoints][this.lPoints];
        } else {
            throw new IllegalArgumentException("The data matrix must have a minimum size of 3 X 3 X 3");
        }
    }

    public static void noRoundingErrorCheck() {
        roundingCheck = false;
        BiCubicSpline.noRoundingErrorCheck();
        CubicSpline.noRoundingErrorCheck();
    }

    public static void potentialRoundingError(double var0) {
        potentialRoundingError = var0;
        BiCubicSpline.potentialRoundingError(var0);
        CubicSpline.potentialRoundingError(var0);
    }

    public void averageIdenticalAbscissae() {
        this.averageIdenticalAbscissae = true;

        for(int var1 = 0; var1 < this.bcsn.length; ++var1) {
            this.bcsn[var1].averageIdenticalAbscissae();
        }

        CubicSpline var10000 = this.csm;
        CubicSpline.averageIdenticalAbscissae();
    }

    public static TriCubicSpline zero(int var0, int var1, int var2) {
        if (var0 >= 3 && var1 >= 3 && var2 >= 3) {
            TriCubicSpline var3 = new TriCubicSpline(var0, var1, var2);
            return var3;
        } else {
            throw new IllegalArgumentException("A minimum of three x three x three data points is needed");
        }
    }

    public static TriCubicSpline[] oneDarray(int var0, int var1, int var2, int var3) {
        if (var1 >= 3 && var2 >= 3 && var3 >= 3) {
            TriCubicSpline[] var4 = new TriCubicSpline[var0];

            for(int var5 = 0; var5 < var0; ++var5) {
                var4[var5] = zero(var1, var2, var3);
            }

            return var4;
        } else {
            throw new IllegalArgumentException("A minimum of three x three x three data points is needed");
        }
    }

    public void resetData(double[] var1, double[] var2, double[] var3, double[][][] var4) {
        if (var1.length != var4.length) {
            throw new IllegalArgumentException("Arrays x1 and y row are of different length");
        } else if (var2.length != var4[0].length) {
            throw new IllegalArgumentException("Arrays x2 and y column are of different length");
        } else if (var3.length != var4[0][0].length) {
            throw new IllegalArgumentException("Arrays x3 and y column are of different length");
        } else if (this.nPoints != var1.length) {
            throw new IllegalArgumentException("Original array length not matched by new array length");
        } else if (this.mPoints != var2.length) {
            throw new IllegalArgumentException("Original array length not matched by new array length");
        } else if (this.lPoints != var3.length) {
            throw new IllegalArgumentException("Original array length not matched by new array length");
        } else {
            int var5;
            for(var5 = 0; var5 < this.nPoints; ++var5) {
                this.x1[var5] = var1[var5];
            }

            this.xMin[0] = Fmath.minimum(this.x1);
            this.xMax[0] = Fmath.maximum(this.x1);

            for(var5 = 0; var5 < this.mPoints; ++var5) {
                this.x2[var5] = var2[var5];
            }

            this.xMin[1] = Fmath.minimum(this.x2);
            this.xMax[1] = Fmath.maximum(this.x2);

            for(var5 = 0; var5 < this.lPoints; ++var5) {
                this.x3[var5] = var3[var5];
            }

            this.xMin[2] = Fmath.minimum(this.x3);
            this.xMax[2] = Fmath.maximum(this.x3);

            int var6;
            int var7;
            for(var5 = 0; var5 < this.nPoints; ++var5) {
                for(var6 = 0; var6 < this.mPoints; ++var6) {
                    for(var7 = 0; var7 < this.lPoints; ++var7) {
                        this.y[var5][var6][var7] = var4[var5][var6][var7];
                    }
                }
            }

            this.csm = new CubicSpline(this.nPoints);
            this.bcsn = BiCubicSpline.oneDarray(this.nPoints, this.mPoints, this.lPoints);
            double[][] var9 = new double[this.mPoints][this.lPoints];

            for(var6 = 0; var6 < this.nPoints; ++var6) {
                for(var7 = 0; var7 < this.mPoints; ++var7) {
                    for(int var8 = 0; var8 < this.lPoints; ++var8) {
                        var9[var7][var8] = var4[var6][var7][var8];
                    }
                }

                this.bcsn[var6].resetData(var2, var3, var9);
                this.d2ydx2inner[var6] = this.bcsn[var6].getDeriv();
            }

            this.derivCalculated = true;
        }
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
            System.out.println("The limits to the x array " + var1 + " are " + this.xMin[var1] + " and " + this.xMax[var1]);
        }

        System.out.println(" ");
    }

    public double interpolate(double var1, double var3, double var5) {
        double[] var7 = new double[this.nPoints];

        for(int var8 = 0; var8 < this.nPoints; ++var8) {
            var7[var8] = this.bcsn[var8].interpolate(var3, var5);
        }

        this.csm.resetData(this.x1, var7);
        return this.csm.interpolate(var1);
    }

    public double[][][] getDeriv() {
        return this.d2ydx2inner;
    }

    public void setDeriv(double[][][] var1) {
        this.d2ydx2inner = var1;
        this.derivCalculated = true;
    }
}
