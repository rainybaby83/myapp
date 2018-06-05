//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.interpolation;

import com.flanagan.math.Conv;
import com.flanagan.math.Fmath;

public class LinearInterpolation {
    private int nPoints = 0;
    private int nPointsOriginal = 0;
    private double[] y = null;
    private double[] x = null;
    private double yy = 0.0D / 0.0;
    private double dydx = 0.0D / 0.0;
    private int[] newAndOldIndices;
    private double xMin = 0.0D / 0.0;
    private double xMax = 0.0D / 0.0;
    private double range = 0.0D / 0.0;
    private boolean checkPoints = false;
    private static boolean supress = false;
    private static boolean averageIdenticalAbscissae = false;
    private static double potentialRoundingError = 5.0E-15D;
    private static boolean roundingCheck = true;

    public LinearInterpolation(double[] var1, double[] var2) {
        this.nPoints = var1.length;
        this.nPointsOriginal = this.nPoints;
        if (this.nPoints != var2.length) {
            throw new IllegalArgumentException("Arrays x and y are of different length " + this.nPoints + " " + var2.length);
        } else if (this.nPoints < 3) {
            throw new IllegalArgumentException("A minimum of three data points is needed");
        } else {
            this.x = new double[this.nPoints];
            this.y = new double[this.nPoints];

            for(int var3 = 0; var3 < this.nPoints; ++var3) {
                this.x[var3] = var1[var3];
                this.y[var3] = var2[var3];
            }

            this.orderPoints();
            this.checkForIdenticalPoints();
        }
    }

    public static void noRoundingErrorCheck() {
        roundingCheck = false;
    }

    public static void potentialRoundingError(double var0) {
        potentialRoundingError = var0;
    }

    public static void averageIdenticalAbscissae() {
        averageIdenticalAbscissae = true;
    }

    public void orderPoints() {
        double[] var1 = new double[this.nPoints];
        this.newAndOldIndices = new int[this.nPoints];
        Fmath.selectionSort(this.x, var1, this.newAndOldIndices);
        Fmath.selectionSort(this.x, this.y, this.x, this.y);
        this.xMin = Fmath.minimum(this.x);
        this.xMax = Fmath.maximum(this.x);
        this.range = this.xMax - this.xMin;
    }

    public double getXmax() {
        return this.xMax;
    }

    public double getXmin() {
        return this.xMin;
    }

    public double[] getLimits() {
        double[] var1 = new double[]{this.xMin, this.xMax};
        return var1;
    }

    public void displayLimits() {
        System.out.println("\nThe limits of the abscissae (x-values) are " + this.xMin + " and " + this.xMax + "\n");
    }

    public void checkForIdenticalPoints() {
        int var1 = this.nPoints;
        boolean var2 = true;
        int var3 = 0;

        while(var2) {
            boolean var4 = true;
            int var5 = var3 + 1;

            while(var4) {
                if (this.x[var3] != this.x[var5]) {
                    ++var5;
                } else {
                    double[] var6;
                    double[] var7;
                    int[] var8;
                    int var9;
                    if (this.y[var3] == this.y[var5]) {
                        if (!supress) {
                            System.out.print("LinearInterpolation: Two identical points, " + this.x[var3] + ", " + this.y[var3]);
                            System.out.println(", in data array at indices " + this.newAndOldIndices[var3] + " and " + this.newAndOldIndices[var5] + ", latter point removed");
                        }

                        var6 = new double[this.nPoints - 1];
                        var7 = new double[this.nPoints - 1];
                        var8 = new int[this.nPoints - 1];

                        for(var9 = 0; var9 < var5; ++var9) {
                            var6[var9] = this.x[var9];
                            var7[var9] = this.y[var9];
                            var8[var9] = this.newAndOldIndices[var9];
                        }

                        for(var9 = var5; var9 < this.nPoints - 1; ++var9) {
                            var6[var9] = this.x[var9 + 1];
                            var7[var9] = this.y[var9 + 1];
                            var8[var9] = this.newAndOldIndices[var9 + 1];
                        }

                        --this.nPoints;
                        this.x = Conv.copy(var6);
                        this.y = Conv.copy(var7);
                        this.newAndOldIndices = Conv.copy(var8);
                    } else if (averageIdenticalAbscissae) {
                        if (!supress) {
                            System.out.print("LinearInterpolation: Two identical points on the absicca (x-axis) with different ordinate (y-axis) values, " + this.x[var3] + ": " + this.y[var3] + ", " + this.y[var5]);
                            System.out.println(", average of the ordinates taken");
                        }

                        this.y[var3] = (this.y[var3] + this.y[var5]) / 2.0D;
                        var6 = new double[this.nPoints - 1];
                        var7 = new double[this.nPoints - 1];
                        var8 = new int[this.nPoints - 1];

                        for(var9 = 0; var9 < var5; ++var9) {
                            var6[var9] = this.x[var9];
                            var7[var9] = this.y[var9];
                            var8[var9] = this.newAndOldIndices[var9];
                        }

                        for(var9 = var5; var9 < this.nPoints - 1; ++var9) {
                            var6[var9] = this.x[var9 + 1];
                            var7[var9] = this.y[var9 + 1];
                            var8[var9] = this.newAndOldIndices[var9 + 1];
                        }

                        --this.nPoints;
                        this.x = Conv.copy(var6);
                        this.y = Conv.copy(var7);
                        this.newAndOldIndices = Conv.copy(var8);
                    } else {
                        double var10 = this.range * 5.0E-4D;
                        if (!supress) {
                            System.out.print("LinearInterpolation: Two identical points on the absicca (x-axis) with different ordinate (y-axis) values, " + this.x[var3] + ": " + this.y[var3] + ", " + this.y[var5]);
                        }

                        boolean var11 = false;
                        if (var3 == 0) {
                            if (this.x[2] - this.x[1] <= var10) {
                                var10 = (this.x[2] - this.x[1]) / 2.0D;
                            }

                            if (this.y[0] > this.y[1]) {
                                if (this.y[1] > this.y[2]) {
                                    var11 = this.stay(var3, var5, var10);
                                } else {
                                    var11 = this.swap(var3, var5, var10);
                                }
                            } else if (this.y[2] <= this.y[1]) {
                                var11 = this.swap(var3, var5, var10);
                            } else {
                                var11 = this.stay(var3, var5, var10);
                            }
                        }

                        if (var5 == this.nPoints - 1) {
                            if (this.x[var1 - 2] - this.x[var1 - 3] <= var10) {
                                var10 = (this.x[var1 - 2] - this.x[var1 - 3]) / 2.0D;
                            }

                            if (this.y[var3] <= this.y[var5]) {
                                if (this.y[var3 - 1] <= this.y[var3]) {
                                    var11 = this.stay(var3, var5, var10);
                                } else {
                                    var11 = this.swap(var3, var5, var10);
                                }
                            } else if (this.y[var3 - 1] <= this.y[var3]) {
                                var11 = this.swap(var3, var5, var10);
                            } else {
                                var11 = this.stay(var3, var5, var10);
                            }
                        }

                        if (var3 != 0 && var5 != this.nPoints - 1) {
                            if (this.x[var3] - this.x[var3 - 1] <= var10) {
                                var10 = (this.x[var3] - this.x[var3 - 1]) / 2.0D;
                            }

                            if (this.x[var5 + 1] - this.x[var5] <= var10) {
                                var10 = (this.x[var5 + 1] - this.x[var5]) / 2.0D;
                            }

                            if (this.y[var3] > this.y[var3 - 1]) {
                                if (this.y[var5] > this.y[var3]) {
                                    if (this.y[var5] > this.y[var5 + 1]) {
                                        if (this.y[var3 - 1] <= this.y[var5 + 1]) {
                                            var11 = this.stay(var3, var5, var10);
                                        } else {
                                            var11 = this.swap(var3, var5, var10);
                                        }
                                    } else {
                                        var11 = this.stay(var3, var5, var10);
                                    }
                                } else if (this.y[var5 + 1] > this.y[var5]) {
                                    if (this.y[var5 + 1] > this.y[var3 - 1] && this.y[var5 + 1] > this.y[var3 - 1]) {
                                        var11 = this.stay(var3, var5, var10);
                                    }
                                } else {
                                    var11 = this.swap(var3, var5, var10);
                                }
                            } else if (this.y[var5] > this.y[var3]) {
                                if (this.y[var5 + 1] > this.y[var5]) {
                                    var11 = this.stay(var3, var5, var10);
                                }
                            } else if (this.y[var5 + 1] > this.y[var3 - 1]) {
                                var11 = this.stay(var3, var5, var10);
                            } else {
                                var11 = this.swap(var3, var5, var10);
                            }
                        }

                        if (!var11) {
                            this.stay(var3, var5, var10);
                        }

                        if (!supress) {
                            System.out.println(", the two abscissae have been separated by a distance " + var10);
                        }

                        ++var5;
                    }

                    if (this.nPoints - 1 == var3) {
                        var4 = false;
                    }
                }

                if (var5 >= this.nPoints) {
                    var4 = false;
                }
            }

            ++var3;
            if (var3 >= this.nPoints - 1) {
                var2 = false;
            }
        }

        if (this.nPoints < 3) {
            throw new IllegalArgumentException("Removal of duplicate points has reduced the number of points to less than the required minimum of three data points");
        } else {
            this.checkPoints = true;
        }
    }

    private boolean swap(int var1, int var2, double var3) {
        this.x[var1] += var3;
        this.x[var2] -= var3;
        double var5 = this.x[var1];
        this.x[var1] = this.x[var2];
        this.x[var2] = var5;
        var5 = this.y[var1];
        this.y[var1] = this.y[var2];
        this.y[var2] = var5;
        return true;
    }

    private boolean stay(int var1, int var2, double var3) {
        this.x[var1] -= var3;
        this.x[var2] += var3;
        return true;
    }

    public static void supress() {
        supress = true;
    }

    public static void unsupress() {
        supress = false;
    }

    public double interpolate(double var1) {
        if (var1 < this.x[0]) {
            if (!roundingCheck || Math.abs(this.x[0] - var1) > Math.pow(10.0D, Math.floor(Math.log10(Math.abs(this.x[0])))) * potentialRoundingError) {
                throw new IllegalArgumentException("x (" + var1 + ") is outside the range of data points (" + this.x[0] + " to " + this.x[this.nPoints - 1] + ")");
            }

            var1 = this.x[0];
        }

        if (var1 > this.x[this.nPoints - 1]) {
            if (!roundingCheck || Math.abs(var1 - this.x[this.nPoints - 1]) > Math.pow(10.0D, Math.floor(Math.log10(Math.abs(this.x[this.nPoints - 1])))) * potentialRoundingError) {
                throw new IllegalArgumentException("x (" + var1 + ") is outside the range of data points (" + this.x[0] + " to " + this.x[this.nPoints - 1] + ")");
            }

            var1 = this.x[this.nPoints - 1];
        }

        boolean var3 = true;

        int var4;
        for(var4 = 0; var4 < this.nPoints; ++var4) {
            if (var1 == this.x[var4]) {
                this.yy = this.y[var4];
                var3 = false;
            }

            if (!var3) {
                break;
            }
        }

        if (var3) {
            for(var4 = 1; var4 < this.nPoints; ++var4) {
                if (var1 < this.x[var4]) {
                    this.yy = this.y[var4] - (this.y[var4] - this.y[var4 - 1]) * (this.x[var4] - var1) / (this.x[var4] - this.x[var4 - 1]);
                    var3 = false;
                }

                if (!var3) {
                    break;
                }
            }
        }

        return this.yy;
    }
}
