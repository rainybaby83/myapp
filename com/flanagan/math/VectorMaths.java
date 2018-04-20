//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.math;

import com.flanagan.circuits.Phasor;

public class VectorMaths {
    Point point0 = null;
    Point point1 = null;
    int nDimensionsEntered = 0;

    public VectorMaths(double[] var1) {
        this.setVector(var1);
    }

    public VectorMaths(double[] var1, double[] var2) {
        this.setVector(var1, var2);
    }

    public VectorMaths(double var1, double var3, double var5) {
        this.setVector(var1, var3, var5);
    }

    public VectorMaths(double var1, double var3) {
        this.setVector(var1, var3);
    }

    public VectorMaths(double var1) {
        this.setVector(var1);
    }

    public VectorMaths(Point var1) {
        this.setVector(var1);
    }

    public VectorMaths(Point var1, Point var2) {
        this.setVector(var1, var2);
    }

    public void setVector(double[] var1) {
        this.nDimensionsEntered = var1.length;
        if (this.nDimensionsEntered > 3) {
            throw new IllegalArgumentException("VectorMaths will not handle dimensional spaces greater than 3-dimensional");
        } else {
            double[] var2 = new double[3];

            int var3;
            for(var3 = 0; var3 < this.nDimensionsEntered; ++var3) {
                var2[var3] = var1[var3];
            }

            for(var3 = this.nDimensionsEntered; var3 < 3; ++var3) {
                var2[var3] = 0.0D;
            }

            this.point1 = new Point(var2);
            double[] var4 = new double[3];
            this.point0 = new Point(var4);
        }
    }

    public void setVector(double[] var1, double[] var2) {
        this.nDimensionsEntered = var1.length;
        int var3 = var2.length;
        if (var3 != this.nDimensionsEntered) {
            throw new IllegalArgumentException("The two dimensions, " + this.nDimensionsEntered + " and " + var3 + ", should be equal");
        } else if (this.nDimensionsEntered > 3) {
            throw new IllegalArgumentException("VectorMaths will not handle dimensional spaces greater than 3-dimensional");
        } else {
            double[] var4 = new double[3];
            double[] var5 = new double[3];

            int var6;
            for(var6 = 0; var6 < this.nDimensionsEntered; ++var6) {
                var4[var6] = var1[var6];
                var5[var6] = var2[var6];
            }

            for(var6 = this.nDimensionsEntered; var6 < 3; ++var6) {
                var4[var6] = 0.0D;
                var5[var6] = 0.0D;
            }

            this.point0 = new Point(var4);
            this.point1 = new Point(var5);
        }
    }

    public void setVector(double var1, double var3, double var5) {
        this.point1 = new Point(var1, var3, var5);
        this.nDimensionsEntered = 3;
        double[] var7 = new double[]{0.0D, 0.0D, 0.0D};
        this.point0 = new Point(var7);
    }

    public void setVector(double var1, double var3) {
        this.point1 = new Point(var1, var3, 0.0D);
        this.nDimensionsEntered = 2;
        double[] var5 = new double[]{0.0D, 0.0D, 0.0D};
        this.point0 = new Point(var5);
    }

    public void setVector(double var1) {
        this.point1 = new Point(var1, 0.0D, 0.0D);
        this.nDimensionsEntered = 1;
        double[] var3 = new double[]{0.0D, 0.0D, 0.0D};
        this.point0 = new Point(var3);
    }

    public void setVector(Point var1) {
        int var2 = var1.getPointDimensions();
        if (var2 > 3) {
            throw new IllegalArgumentException("VectorMaths will not handle dimensional spaces greater than 3-dimensional");
        } else {
            this.nDimensionsEntered = var2;
            double[] var3;
            if (var2 == 3) {
                this.point1 = var1;
            } else {
                var3 = var1.getPointCoordinates();
                double[] var4 = new double[3];

                int var5;
                for(var5 = 0; var5 < var2; ++var5) {
                    var4[var5] = var3[var5];
                }

                for(var5 = var2; var5 < 3; ++var5) {
                    var4[var5] = 0.0D;
                }

                this.point1 = new Point(var4);
            }

            var3 = new double[3];

            for(int var6 = 0; var6 < 3; ++var6) {
                var3[var6] = 0.0D;
            }

            this.point0 = new Point(var3);
        }
    }

    public void setVector(Point var1, Point var2) {
        this.nDimensionsEntered = var1.getPointDimensions();
        int var3 = var2.getPointDimensions();
        if (var3 != this.nDimensionsEntered) {
            throw new IllegalArgumentException("The dimensions of the two points, " + this.nDimensionsEntered + " and " + var3 + ", should be equal");
        } else if (this.nDimensionsEntered > 3) {
            throw new IllegalArgumentException("VectorMaths will not handle dimensional spaces greater than 3-dimensional");
        } else {
            if (var3 == 3) {
                this.point0 = var1.copy();
                this.point1 = var2.copy();
            } else {
                double[] var4 = var1.getPointCoordinates();
                double[] var5 = var2.getPointCoordinates();
                double[] var6 = new double[3];
                double[] var7 = new double[3];

                int var8;
                for(var8 = 0; var8 < var3; ++var8) {
                    var6[var8] = var4[var8];
                    var7[var8] = var5[var8];
                }

                for(var8 = var3; var8 < 3; ++var8) {
                    var6[var8] = 0.0D;
                    var7[var8] = 0.0D;
                }

                this.point0 = new Point(var6);
                this.point1 = new Point(var7);
            }

        }
    }

    public Point[] getVector() {
        Point[] var1 = Point.oneDarray(2);
        var1[0] = this.point0;
        var1[1] = this.point1;
        return var1;
    }

    public Point getInitialPoint() {
        return this.point0;
    }

    public Point getFinalPoint() {
        return this.point1;
    }

    public int getDimensionsEntered() {
        return this.nDimensionsEntered;
    }

    public VectorMaths copy() {
        Point var1 = this.point0.copy();
        Point var2 = this.point1.copy();
        VectorMaths var3 = new VectorMaths(var1, var2);
        var3.nDimensionsEntered = this.nDimensionsEntered;
        return var3;
    }

    public boolean isEqual(VectorMaths var1) {
        boolean var2 = true;
        Point var3 = var1.getInitialPoint();
        Point var4 = var1.getFinalPoint();
        if (!this.point0.isEqual(var3)) {
            var2 = false;
        } else if (!this.point1.isEqual(var4)) {
            var2 = false;
        }

        return var2;
    }

    public static boolean isEqual(VectorMaths var0, VectorMaths var1) {
        boolean var2 = true;
        Point var3 = var0.getInitialPoint();
        Point var4 = var0.getFinalPoint();
        Point var5 = var1.getInitialPoint();
        Point var6 = var1.getFinalPoint();
        if (!var3.isEqual(var5)) {
            var2 = false;
        } else if (!var4.isEqual(var6)) {
            var2 = false;
        }

        return var2;
    }

    public VectorMaths plus(VectorMaths var1) {
        double[] var2 = this.getInitialPoint().getPointCoordinates();
        double[] var3 = this.getFinalPoint().getPointCoordinates();
        double[] var4 = var1.getInitialPoint().getPointCoordinates();
        double[] var5 = var1.getFinalPoint().getPointCoordinates();
        byte var6 = 3;
        double[] var7 = new double[var6];
        double[] var8 = new double[var6];

        for(int var9 = 0; var9 < var6; ++var9) {
            var7[var9] = var2[var9] + var4[var9];
            var8[var9] = var3[var9] + var5[var9];
        }

        return new VectorMaths(var7, var8);
    }

    public static VectorMaths plus(VectorMaths var0, VectorMaths var1) {
        double[] var2 = var0.getInitialPoint().getPointCoordinates();
        double[] var3 = var0.getFinalPoint().getPointCoordinates();
        double[] var4 = var1.getInitialPoint().getPointCoordinates();
        double[] var5 = var1.getFinalPoint().getPointCoordinates();
        byte var6 = 3;
        double[] var7 = new double[var6];
        double[] var8 = new double[var6];

        for(int var9 = 0; var9 < var6; ++var9) {
            var7[var9] = var2[var9] + var4[var9];
            var8[var9] = var3[var9] + var5[var9];
        }

        return new VectorMaths(var7, var8);
    }

    public void plusEquals(VectorMaths var1) {
        double[] var2 = this.getInitialPoint().getPointCoordinates();
        double[] var3 = this.getFinalPoint().getPointCoordinates();
        double[] var4 = var1.getInitialPoint().getPointCoordinates();
        double[] var5 = var1.getFinalPoint().getPointCoordinates();
        byte var6 = 3;
        double[] var7 = new double[var6];
        double[] var8 = new double[var6];

        for(int var9 = 0; var9 < var6; ++var9) {
            var7[var9] = var2[var9] + var4[var9];
            var8[var9] = var3[var9] + var5[var9];
        }

        this.setVector(var7, var8);
    }

    public VectorMaths minus(VectorMaths var1) {
        double[] var2 = this.getInitialPoint().getPointCoordinates();
        double[] var3 = this.getFinalPoint().getPointCoordinates();
        double[] var4 = var1.getInitialPoint().getPointCoordinates();
        double[] var5 = var1.getFinalPoint().getPointCoordinates();
        byte var6 = 3;
        double[] var7 = new double[var6];
        double[] var8 = new double[var6];

        for(int var9 = 0; var9 < var6; ++var9) {
            var7[var9] = var2[var9] - var4[var9];
            var8[var9] = var3[var9] - var5[var9];
        }

        return new VectorMaths(var7, var8);
    }

    public static VectorMaths minus(VectorMaths var0, VectorMaths var1) {
        double[] var2 = var0.getInitialPoint().getPointCoordinates();
        double[] var3 = var0.getFinalPoint().getPointCoordinates();
        double[] var4 = var1.getInitialPoint().getPointCoordinates();
        double[] var5 = var1.getFinalPoint().getPointCoordinates();
        byte var6 = 3;
        double[] var7 = new double[var6];
        double[] var8 = new double[var6];

        for(int var9 = 0; var9 < var6; ++var9) {
            var7[var9] = var2[var9] - var4[var9];
            var8[var9] = var3[var9] - var5[var9];
        }

        return new VectorMaths(var7, var8);
    }

    public void minusEquals(VectorMaths var1) {
        double[] var2 = this.getInitialPoint().getPointCoordinates();
        double[] var3 = this.getFinalPoint().getPointCoordinates();
        double[] var4 = var1.getInitialPoint().getPointCoordinates();
        double[] var5 = var1.getFinalPoint().getPointCoordinates();
        byte var6 = 3;
        double[] var7 = new double[var6];
        double[] var8 = new double[var6];

        for(int var9 = 0; var9 < var6; ++var9) {
            var7[var9] = var2[var9] - var4[var9];
            var8[var9] = var3[var9] - var5[var9];
        }

        this.setVector(var7, var8);
    }

    public VectorMaths times(double var1) {
        byte var3 = 3;
        double[] var4 = new double[var3];
        double[] var5 = new double[var3];
        double[] var6 = this.getInitialPoint().getPointCoordinates();
        double[] var7 = this.getInitialPoint().getPointCoordinates();

        for(int var8 = 0; var8 < var3; ++var8) {
            var4[var8] = var6[var8] + var1;
            var5[var8] = var7[var8] + var1;
        }

        return new VectorMaths(var4, var5);
    }

    public static VectorMaths times(VectorMaths var0, double var1) {
        byte var3 = 3;
        double[] var4 = new double[var3];
        double[] var5 = new double[var3];
        double[] var6 = var0.getInitialPoint().getPointCoordinates();
        double[] var7 = var0.getInitialPoint().getPointCoordinates();

        for(int var8 = 0; var8 < var3; ++var8) {
            var4[var8] = var6[var8] + var1;
            var5[var8] = var7[var8] + var1;
        }

        return new VectorMaths(var4, var5);
    }

    public void timesEquals(double var1) {
        byte var3 = 3;
        double[] var4 = new double[var3];
        double[] var5 = new double[var3];
        double[] var6 = this.getInitialPoint().getPointCoordinates();
        double[] var7 = this.getInitialPoint().getPointCoordinates();

        for(int var8 = 0; var8 < var3; ++var8) {
            var4[var8] = var6[var8] + var1;
            var5[var8] = var7[var8] + var1;
        }

        this.setVector(var4, var5);
    }

    public double dot(VectorMaths var1) {
        double[] var2 = this.getInitialPoint().getPointCoordinates();
        double[] var3 = this.getFinalPoint().getPointCoordinates();
        double[] var4 = var1.getInitialPoint().getPointCoordinates();
        double[] var5 = var1.getFinalPoint().getPointCoordinates();
        double var6 = 0.0D;

        for(int var8 = 0; var8 < 3; ++var8) {
            var6 += (var3[var8] - var2[var8]) * (var5[var8] - var4[var8]);
        }

        return var6;
    }

    public static double dot(VectorMaths var0, VectorMaths var1) {
        double[] var2 = var0.getInitialPoint().getPointCoordinates();
        double[] var3 = var0.getFinalPoint().getPointCoordinates();
        double[] var4 = var1.getInitialPoint().getPointCoordinates();
        double[] var5 = var1.getFinalPoint().getPointCoordinates();
        double var6 = 0.0D;

        for(int var8 = 0; var8 < 3; ++var8) {
            var6 += (var3[var8] - var2[var8]) * (var5[var8] - var4[var8]);
        }

        return var6;
    }

    public VectorMaths cross(VectorMaths var1) {
        double[] var2 = this.getInitialPoint().getPointCoordinates();
        double[] var3 = this.getFinalPoint().getPointCoordinates();
        double[] var4 = var1.getInitialPoint().getPointCoordinates();
        double[] var5 = var1.getFinalPoint().getPointCoordinates();
        double[] var6 = new double[3];
        double[] var7 = new double[3];
        double[] var8 = new double[3];

        int var9;
        for(var9 = 0; var9 < 3; ++var9) {
            var6[var9] = var3[var9] - var2[var9];
            var7[var9] = var5[var9] - var4[var9];
        }

        for(var9 = 0; var9 < 3; ++var9) {
            var8[0] = var6[1] * var7[2] - var6[2] * var7[1];
            var8[1] = var6[2] * var7[0] - var6[0] * var7[2];
            var8[2] = var6[0] * var7[1] - var6[1] * var7[0];
        }

        VectorMaths var10 = new VectorMaths(var8);
        return var10;
    }

    public static VectorMaths cross(VectorMaths var0, VectorMaths var1) {
        double[] var2 = var0.getInitialPoint().getPointCoordinates();
        double[] var3 = var0.getFinalPoint().getPointCoordinates();
        double[] var4 = var1.getInitialPoint().getPointCoordinates();
        double[] var5 = var1.getFinalPoint().getPointCoordinates();
        double[] var6 = new double[3];
        double[] var7 = new double[3];
        double[] var8 = new double[3];

        int var9;
        for(var9 = 0; var9 < 3; ++var9) {
            var6[var9] = var3[var9] - var2[var9];
            var7[var9] = var5[var9] - var4[var9];
        }

        for(var9 = 0; var9 < 3; ++var9) {
            var8[0] = var6[1] * var7[2] - var6[2] * var7[1];
            var8[1] = var6[2] * var7[0] - var6[0] * var7[2];
            var8[2] = var6[0] * var7[1] - var6[1] * var7[0];
        }

        VectorMaths var10 = new VectorMaths(var8);
        return var10;
    }

    public double length() {
        double[] var1 = this.getInitialPoint().getPointCoordinates();
        double[] var2 = this.getFinalPoint().getPointCoordinates();
        double var3 = 0.0D;

        for(int var5 = 0; var5 < 3; ++var5) {
            var3 += (var2[var5] - var1[var5]) * (var2[var5] - var1[var5]);
        }

        return Math.sqrt(var3);
    }

    public double magnitude() {
        return this.length();
    }

    public double norm() {
        return this.length();
    }

    public double angleRadians(VectorMaths var1) {
        double var2 = this.norm();
        double var4 = var1.norm();
        double var6 = this.dot(var1);
        double var8 = var6 / (var2 * var4);
        return Math.acos(var8);
    }

    public double angleDegrees(VectorMaths var1) {
        return Math.toDegrees(this.angleRadians(var1));
    }

    public static double angleRadians(VectorMaths var0, VectorMaths var1) {
        double var2 = var0.norm();
        double var4 = var1.norm();
        double var6 = dot(var0, var1);
        double var8 = var6 / (var2 * var4);
        return Math.acos(var8);
    }

    public static double angleDegrees(VectorMaths var0, VectorMaths var1) {
        return Math.toDegrees(angleRadians(var0, var1));
    }

    public Phasor toPhasor() {
        double[] var1 = this.point0.getPointCoordinates();
        double[] var2 = this.point1.getPointCoordinates();
        double var3 = 0.0D;
        double var5 = 0.0D;
        switch(this.nDimensionsEntered) {
            case 1:
                var3 = Math.abs(var2[0] - var1[0]);
                var5 = 0.0D;
                break;
            case 2:
                var3 = Point.distance(this.point0, this.point1);
                var5 = Math.toDegrees(Math.atan2(var2[1] - var1[1], var2[0] - var1[0]));
                break;
            default:
                throw new IllegalArgumentException("Entered dimensions must be either 1 or 2");
        }

        return new Phasor(var3, var5);
    }
}
