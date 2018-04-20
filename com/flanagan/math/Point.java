//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.math;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Point {
    private double[] point = null;
    private int nDimensions = 0;

    public Point() {
        this.point = new double[3];
        this.point[0] = 0.0D;
        this.point[1] = 0.0D;
        this.point[2] = 0.0D;
        this.nDimensions = 3;
    }

    public Point(double[] var1) {
        this.setPoint(var1);
    }

    public Point(float[] var1) {
        this.setPoint(var1);
    }

    public Point(long[] var1) {
        this.setPoint(var1);
    }

    public Point(int[] var1) {
        this.setPoint(var1);
    }

    public Point(short[] var1) {
        this.setPoint(var1);
    }

    public Point(BigDecimal[] var1) {
        this.setPoint(var1);
    }

    public Point(BigInteger[] var1) {
        this.setPoint(var1);
    }

    public Point(double var1) {
        this.point = new double[1];
        this.point[0] = var1;
        this.nDimensions = 1;
    }

    public Point(double var1, double var3) {
        this.point = new double[2];
        this.point[0] = var1;
        this.point[1] = var3;
        this.nDimensions = 2;
    }

    public Point(double var1, double var3, double var5) {
        this.point = new double[3];
        this.point[0] = var1;
        this.point[1] = var3;
        this.point[2] = var5;
        this.nDimensions = 3;
    }

    public final void setPoint(double[] var1) {
        this.point = (double[])((double[])var1.clone());
        this.nDimensions = this.point.length;
    }

    public final void setPoint(float[] var1) {
        this.nDimensions = var1.length;
        ArrayMaths var2 = new ArrayMaths(var1);
        this.point = var2.array_as_double();
    }

    public final void setPoint(long[] var1) {
        this.nDimensions = var1.length;
        ArrayMaths var2 = new ArrayMaths(var1);
        this.point = var2.array_as_double();
    }

    public final void setPoint(int[] var1) {
        this.nDimensions = var1.length;
        ArrayMaths var2 = new ArrayMaths(var1);
        this.point = var2.array_as_double();
    }

    public final void setPoint(short[] var1) {
        this.nDimensions = var1.length;
        ArrayMaths var2 = new ArrayMaths(var1);
        this.point = var2.array_as_double();
    }

    public final void setPoint(BigDecimal[] var1) {
        this.nDimensions = var1.length;
        ArrayMaths var2 = new ArrayMaths(var1);
        this.point = var2.array_as_double();
    }

    public final void setPoint(BigInteger[] var1) {
        this.nDimensions = var1.length;
        ArrayMaths var2 = new ArrayMaths(var1);
        this.point = var2.array_as_double();
    }

    public void setPoint(double var1) {
        this.point = new double[1];
        this.point[0] = var1;
        this.nDimensions = 1;
    }

    public void setPoint(double var1, double var3) {
        this.point = new double[2];
        this.point[0] = var1;
        this.point[1] = var3;
        this.nDimensions = 2;
    }

    public void setPoint(double var1, double var3, double var5) {
        this.point = new double[3];
        this.point[0] = var1;
        this.point[1] = var3;
        this.point[2] = var5;
        this.nDimensions = 3;
    }

    public static Point[] oneDarray(int var0) {
        Point[] var1 = new Point[var0];

        for(int var2 = 0; var2 < var0; ++var2) {
            var1[var2] = new Point(0.0D, 0.0D, 0.0D);
        }

        return var1;
    }

    public static Point[] oneDarray(double[] var0) {
        int var1 = var0.length;
        Point[] var2 = new Point[var1];

        for(int var3 = 0; var3 < var1; ++var3) {
            var2[var3] = new Point(var0[var3]);
        }

        return var2;
    }

    public static Point[] oneDarray(double[] var0, double[] var1) {
        int var2 = var0.length;
        int var3 = var1.length;
        if (var3 != var2) {
            throw new IllegalArgumentException("the length of the xx array, " + var2 + ", and the lengh of the yy array, " + var3 + ", must be equal");
        } else {
            Point[] var4 = new Point[var2];

            for(int var5 = 0; var5 < var2; ++var5) {
                var4[var5] = new Point(var0[var5], var1[var5]);
            }

            return var4;
        }
    }

    public static Point[] oneDarray(double[] var0, double[] var1, double[] var2) {
        int var3 = var0.length;
        int var4 = var1.length;
        int var5 = var2.length;
        if (var4 != var3) {
            throw new IllegalArgumentException("the length of the xx array, " + var3 + ", and the lengh of the yy array, " + var4 + ", must be equal");
        } else if (var3 != var5) {
            throw new IllegalArgumentException("the length of the xx array, " + var3 + ", and the lengh of the zz array, " + var5 + ", must be equal");
        } else {
            Point[] var6 = new Point[var3];

            for(int var7 = 0; var7 < var3; ++var7) {
                var6[var7] = new Point(var0[var7], var1[var7], var2[var7]);
            }

            return var6;
        }
    }

    public static Point[] oneDarray(double[][] var0) {
        int var1 = var0.length;
        int var2 = var0[0].length;

        for(int var3 = 1; var3 < var1; ++var3) {
            if (var2 != var0[var3].length) {
                throw new IllegalArgumentException("the dimesions of all the points must be identical");
            }
        }

        Point[] var7 = new Point[var1];
        double[] var4 = new double[var2];

        for(int var5 = 0; var5 < var1; ++var5) {
            for(int var6 = 0; var6 < var2; ++var6) {
                var4[var6] = var0[var5][var6];
            }

            var7[var5] = new Point(var4);
        }

        return var7;
    }

    public static Point[] oneDarray(float[][] var0) {
        int var1 = var0.length;
        int var2 = var0[0].length;
        double[][] var3 = new double[var1][var2];
        ArrayMaths var4 = null;

        for(int var5 = 0; var5 < var1; ++var5) {
            var4 = new ArrayMaths(var0[var5]);
            var3[var5] = var4.array_as_double();
        }

        return oneDarray(var3);
    }

    public static Point[] oneDarray(long[][] var0) {
        int var1 = var0.length;
        int var2 = var0[0].length;
        double[][] var3 = new double[var1][var2];
        ArrayMaths var4 = null;

        for(int var5 = 0; var5 < var1; ++var5) {
            var4 = new ArrayMaths(var0[var5]);
            var3[var5] = var4.array_as_double();
        }

        return oneDarray(var3);
    }

    public static Point[] oneDarray(int[][] var0) {
        int var1 = var0.length;
        int var2 = var0[0].length;
        double[][] var3 = new double[var1][var2];
        ArrayMaths var4 = null;

        for(int var5 = 0; var5 < var1; ++var5) {
            var4 = new ArrayMaths(var0[var5]);
            var3[var5] = var4.array_as_double();
        }

        return oneDarray(var3);
    }

    public static Point[] oneDarray(short[][] var0) {
        int var1 = var0.length;
        int var2 = var0[0].length;
        double[][] var3 = new double[var1][var2];
        ArrayMaths var4 = null;

        for(int var5 = 0; var5 < var1; ++var5) {
            var4 = new ArrayMaths(var0[var5]);
            var3[var5] = var4.array_as_double();
        }

        return oneDarray(var3);
    }

    public static Point[] oneDarray(BigDecimal[][] var0) {
        int var1 = var0.length;
        int var2 = var0[0].length;
        double[][] var3 = new double[var1][var2];
        ArrayMaths var4 = null;

        for(int var5 = 0; var5 < var1; ++var5) {
            var4 = new ArrayMaths(var0[var5]);
            var3[var5] = var4.array_as_double();
        }

        return oneDarray(var3);
    }

    public static Point[] oneDarray(BigInteger[][] var0) {
        int var1 = var0.length;
        int var2 = var0[0].length;
        double[][] var3 = new double[var1][var2];
        ArrayMaths var4 = null;

        for(int var5 = 0; var5 < var1; ++var5) {
            var4 = new ArrayMaths(var0[var5]);
            var3[var5] = var4.array_as_double();
        }

        return oneDarray(var3);
    }

    public double[] getPointCoordinates() {
        return (double[])((double[])this.point.clone());
    }

    public static double[][] getArrayCoordinates(Point[] var0) {
        int var1 = var0.length;
        int var2 = var0[0].getPointDimensions();
        double[][] var3 = new double[var2][var1];

        for(int var4 = 0; var4 < var1; ++var4) {
            for(int var5 = 0; var5 < var2; ++var5) {
                var3[var5][var4] = var0[var4].getPointCoordinates()[var5];
            }
        }

        return var3;
    }

    public int getPointDimensions() {
        return this.nDimensions;
    }

    public static int[] getArrayDimensions(Point[] var0) {
        int[] var1 = new int[]{var0.length, var0[0].getPointDimensions()};
        return var1;
    }

    public double distanceSquared(Point var1) {
        int var2 = var1.nDimensions;
        if (var2 != this.nDimensions) {
            throw new IllegalArgumentException("The dimensions of the two points, " + this.nDimensions + " and " + var2 + ", should be equal");
        } else {
            if (var2 > 3) {
                System.out.println("Methods distance and distanceSquared are only meaningful for dimensions of 3 or less");
            }

            double var3 = 0.0D;
            double var5 = 0.0D;

            for(int var7 = 0; var7 < var2; ++var7) {
                var3 = this.point[var7] - var1.point[var7];
                var5 += var3 * var3;
            }

            return var5;
        }
    }

    public static double distanceSquared(Point var0, Point var1) {
        int var2 = var0.nDimensions;
        int var3 = var1.nDimensions;
        if (var3 != var2) {
            throw new IllegalArgumentException("The dimensions of the two points, " + var2 + " and " + var3 + ", should be equal");
        } else {
            if (var3 > 3) {
                System.out.println("Methods distance and distanceSquared are only meaningful for dimensions of 3 or less");
            }

            double var4 = 0.0D;
            double var6 = 0.0D;

            for(int var8 = 0; var8 < var3; ++var8) {
                var4 = var0.point[var8] - var1.point[var8];
                var6 += var4 * var4;
            }

            return var6;
        }
    }

    public double distance(Point var1) {
        double var2 = this.distanceSquared(var1);
        return Math.sqrt(var2);
    }

    public static double distance(Point var0, Point var1) {
        double var2 = distanceSquared(var0, var1);
        return Math.sqrt(var2);
    }

    public static Point centre(Point[] var0) {
        int var1 = var0.length;
        int var2 = var0[0].nDimensions;
        boolean var3 = false;

        for(int var4 = 1; var4 < var1; ++var4) {
            int var7 = var0[var4].nDimensions;
            if (var7 != var2) {
                throw new IllegalArgumentException("All points must have the same number of dimensions");
            }
        }

        double[] var8 = new double[var2];

        int var5;
        for(var5 = 0; var5 < var1; ++var5) {
            for(int var6 = 0; var6 < var2; ++var6) {
                var8[var6] += var0[var5].point[var6];
            }
        }

        for(var5 = 0; var5 < var2; ++var5) {
            var8[var5] /= (double)var1;
        }

        return new Point(var8);
    }

    public Point shift(double var1) {
        Point var3 = new Point();

        for(int var4 = 0; var4 < this.nDimensions; ++var4) {
            var3.setPoint(this.point[var4] + var1);
        }

        return null;
    }

    public boolean isEqual(Point var1) {
        boolean var2 = true;
        int var3 = var1.getPointDimensions();
        if (var3 != this.nDimensions) {
            var2 = false;
        } else {
            double[] var4 = this.getPointCoordinates();
            double[] var5 = var1.getPointCoordinates();

            for(int var6 = 0; var6 < this.nDimensions; ++var6) {
                if (var4[var6] != var5[var6]) {
                    var2 = false;
                    break;
                }
            }
        }

        return var2;
    }

    public static boolean isEqual(Point var0, Point var1) {
        boolean var2 = true;
        int var3 = var0.getPointDimensions();
        int var4 = var1.getPointDimensions();
        if (var3 != var4) {
            var2 = false;
        } else {
            double[] var5 = var0.getPointCoordinates();
            double[] var6 = var1.getPointCoordinates();

            for(int var7 = 0; var7 < var3; ++var7) {
                if (var5[var7] != var6[var7]) {
                    var2 = false;
                    break;
                }
            }
        }

        return var2;
    }

    public Point copy() {
        double[] var1 = new double[this.nDimensions];

        for(int var2 = 0; var2 < this.nDimensions; ++var2) {
            var1[var2] = this.point[var2];
        }

        Point var3 = new Point(var1);
        return var3;
    }

    public static Point copy(Point var0) {
        int var1 = var0.getPointDimensions();
        double[] var2 = new double[var1];

        for(int var3 = 0; var3 < var1; ++var3) {
            var2[var3] = var0.point[var3];
        }

        Point var4 = new Point(var2);
        return var4;
    }

    public static Point[] copy(Point[] var0) {
        int var1 = var0.length;
        Point[] var2 = oneDarray(var1);

        for(int var3 = 0; var3 < var1; ++var3) {
            var2[var3] = var0[var3].copy();
        }

        return var2;
    }

    public void toThreeD() {
        double[] var1 = (double[])((double[])this.point.clone());
        double[] var2 = new double[3];
        if (this.nDimensions > 3) {
            System.out.println("Method toThreeD:  Dimensions are greater than three so instance cannot be convertd to a three D Point");
        } else if (this.nDimensions < 3) {
            int var3;
            for(var3 = 0; var3 < this.nDimensions; ++var3) {
                var2[var3] = var1[var3];
            }

            for(var3 = this.nDimensions; var3 < 3; ++var3) {
                var2[var3] = 0.0D;
            }

            this.point = var2;
            this.nDimensions = 3;
        }

    }

    public void toTwoD() {
        double[] var1 = (double[])((double[])this.point.clone());
        double[] var2 = new double[2];
        boolean var3 = true;

        int var4;
        for(var4 = 2; var4 < this.nDimensions; ++var4) {
            if (var1[var4] != 0.0D) {
                var3 = false;
            }
        }

        if (!var3) {
            throw new IllegalArgumentException("There are non-zero values in the coordinate positions greater than 2D");
        } else {
            for(var4 = 0; var4 < 2; ++var4) {
                var2[var4] = var1[var4];
            }

            this.point = var2;
            this.nDimensions = 2;
        }
    }

    public VectorMaths toVectorMaths() {
        if (this.nDimensions > 3) {
            throw new IllegalArgumentException("VectorMaths is restricted to 2 or 3-dimensional space");
        } else {
            VectorMaths var1 = new VectorMaths(this.point);
            return var1;
        }
    }
}
