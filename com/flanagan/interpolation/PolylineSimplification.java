//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.interpolation;

import com.flanagan.math.Fmath;
import com.flanagan.math.Point;
import com.flanagan.math.VectorMaths;
import com.flanagan.plot.PlotGraph;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class PolylineSimplification {
    private Point[] originalPoints = null;
    private int nPoints = 0;
    private Point[] simplifiedPoints = null;
    private int[] simplifiedIndices = null;
    private int nSimplifiedPoints = 0;
    private int pointDimension = 0;
    private double tolerance = 0.0D;
    private double toleranceSquared = 0.0D;
    private boolean tolerenceEntered = false;
    private boolean simplifyDone = false;

    public PolylineSimplification(Point[] var1) {
        this.originalPoints = var1;
        int[] var2 = Point.getArrayDimensions(var1);
        this.nPoints = var2[0];
        this.pointDimension = var2[1];
        if (this.pointDimension <= 3 && this.pointDimension >= 2) {
            this.simplifyDone = false;
        } else {
            throw new IllegalArgumentException("This method will not operate on dimensions greater than 3");
        }
    }

    public PolylineSimplification(double[] var1, double[] var2) {
        this.pointDimension = 2;
        this.nPoints = var1.length;
        if (this.nPoints != var2.length) {
            throw new IllegalArgumentException("The number of x-coordinate points, " + this.nPoints + ", must equal the number of y-coordinate points, " + var2.length);
        } else {
            this.originalPoints = Point.oneDarray(var1, var2);
            this.simplifyDone = false;
        }
    }

    public PolylineSimplification(double[] var1, double[] var2, double[] var3) {
        this.pointDimension = 3;
        this.nPoints = var1.length;
        if (this.nPoints != var2.length) {
            throw new IllegalArgumentException("The number of x-coordinate points, " + this.nPoints + ", must equal the number of y-coordinate points, " + var2.length);
        } else if (this.nPoints != var3.length) {
            throw new IllegalArgumentException("The number of x-coordinate points, " + this.nPoints + ", must equal the number of z-coordinate points, " + var3.length);
        } else {
            this.originalPoints = Point.oneDarray(var1, var2, var3);
            this.simplifyDone = false;
        }
    }

    public PolylineSimplification(double[][] var1) {
        this.pointDimension = var1.length;
        this.nPoints = var1[0].length;
        this.originalPoints = Point.oneDarray(var1);
        if (this.pointDimension <= 3 && this.pointDimension >= 2) {
            this.simplifyDone = false;
        } else {
            throw new IllegalArgumentException("This method will not operate on dimensions greater than 3");
        }
    }

    public PolylineSimplification(Object var1) {
        Object var2 = Fmath.copyObject(var1);

        for(this.nPoints = 1; !((var2 = Array.get(var2, 0)) instanceof Double); ++this.nPoints) {
            ;
        }

        double[][] var3 = (double[][])((double[][])var1);
        this.pointDimension = var3.length;
        this.originalPoints = Point.oneDarray(var3);
        if (this.pointDimension <= 3 && this.pointDimension >= 2) {
            this.simplifyDone = false;
        } else {
            throw new IllegalArgumentException("This method will not operate on dimensions greater than 3 or less than 2");
        }
    }

    public Point[] douglasPeucker(double var1) {
        this.tolerance = var1;
        this.toleranceSquared = var1 * var1;
        this.tolerenceEntered = true;
        return this.douglasPeucker();
    }

    public Point[] douglasPeucker() {
        boolean var1 = false;
        boolean var2 = false;
        int var3 = 0;
        Point[] var4 = Point.oneDarray(this.nPoints);
        int[] var5 = new int[this.nPoints];
        boolean[] var6 = new boolean[this.nPoints];
        if (this.pointDimension == 2) {
            this.toThreeDimO();
        }

        var4[0] = this.originalPoints[0].copy();
        var5[0] = 0;
        int var14 = 1;

        int var13;
        for(var13 = 1; var13 < this.nPoints; ++var13) {
            if (Point.distanceSquared(this.originalPoints[var13], this.originalPoints[var3]) >= this.toleranceSquared) {
                var4[var14] = this.originalPoints[var13].copy();
                var5[var14] = var13;
                var3 = var13;
                ++var14;
            }
        }

        if (var3 < this.nPoints - 1) {
            var4[var14] = this.originalPoints[this.nPoints - 1].copy();
            var5[var14] = this.nPoints - 1;
            ++var14;
        }

        var6[0] = true;
        var6[var14 - 1] = true;
        this.douglasPeuckerSimplificationRoutine(var4, 0, var14 - 1, var6);
        ArrayList var7 = new ArrayList();
        ArrayList var8 = new ArrayList();

        for(var13 = 0; var13 < var14; ++var13) {
            if (var6[var13]) {
                var7.add(var4[var13]);
                var8.add(new Integer(var5[var13]));
            }
        }

        this.nSimplifiedPoints = var7.size();
        this.simplifiedPoints = Point.oneDarray(this.nSimplifiedPoints);
        this.simplifiedIndices = new int[this.nSimplifiedPoints];

        for(var13 = 0; var13 < this.nSimplifiedPoints; ++var13) {
            this.simplifiedPoints[var13] = (Point)var7.get(var13);
            this.simplifiedIndices[var13] = (Integer)var8.get(var13);
        }

        Point[] var9 = Point.copy(this.simplifiedPoints);
        int[] var10 = (int[])((int[])this.simplifiedIndices.clone());

        for(var13 = 0; var13 < this.nSimplifiedPoints - 1; ++var13) {
            for(int var11 = var13 + 1; var11 < this.nSimplifiedPoints; ++var11) {
                if (Point.isEqual(var9[var13], var9[var11])) {
                    for(int var12 = var11; var12 < this.nSimplifiedPoints - 1; ++var12) {
                        var9[var12] = var9[var12 + 1];
                        var10[var12] = var10[var12 + 1];
                    }

                    --this.nSimplifiedPoints;
                }
            }
        }

        this.simplifiedPoints = Point.oneDarray(this.nSimplifiedPoints);

        for(var13 = 0; var13 < this.nSimplifiedPoints; ++var13) {
            this.simplifiedPoints[var13] = var9[var13];
            this.simplifiedIndices[var13] = var10[var13];
        }

        if (this.pointDimension == 2) {
            this.toTwoDimO();
            this.toTwoDimS();
        }

        this.simplifyDone = true;
        return this.simplifiedPoints;
    }

    private void douglasPeuckerSimplificationRoutine(Point[] var1, int var2, int var3, boolean[] var4) {
        if (var3 > var2 + 1) {
            int var5 = var2;
            double var6 = 0.0D;
            Point[] var8 = Point.oneDarray(2);
            var8[0] = var1[var2];
            var8[1] = var1[var3];
            VectorMaths var9 = new VectorMaths(var1[var2], var1[var3]);
            double var10 = var1[var2].distanceSquared(var1[var3]);
            VectorMaths var12 = null;
            Point var13 = null;
            double var14 = 0.0D;
            double var16 = 0.0D;
            double var18 = 0.0D;

            for(int var20 = var2 + 1; var20 < var3; ++var20) {
                var12 = new VectorMaths(var8[0], var1[var20]);
                var16 = var12.dot(var9);
                if (var16 <= 0.0D) {
                    var18 = Point.distanceSquared(var1[var20], var8[0]);
                } else if (var10 <= var16) {
                    var18 = Point.distanceSquared(var1[var20], var8[1]);
                } else {
                    var14 = var16 / var10;
                    VectorMaths var21 = var9.times(var14);
                    VectorMaths var22 = (new VectorMaths(var8[0])).plus(var21);
                    var13 = var22.getFinalPoint();
                    var18 = Point.distanceSquared(var1[var20], var13);
                }

                if (var18 > var6) {
                    var5 = var20;
                    var6 = var18;
                }
            }

            if (var6 > this.toleranceSquared) {
                var4[var5] = true;
                this.douglasPeuckerSimplificationRoutine(var1, var2, var5, var4);
                this.douglasPeuckerSimplificationRoutine(var1, var5, var3, var4);
            }

        }
    }

    private void toThreeDimO() {
        for(int var1 = 0; var1 < this.nPoints; ++var1) {
            this.originalPoints[var1].toThreeD();
        }

    }

    private void toTwoDimO() {
        for(int var1 = 0; var1 < this.nPoints; ++var1) {
            this.originalPoints[var1].toTwoD();
        }

    }

    private void toTwoDimS() {
        for(int var1 = 0; var1 < this.nSimplifiedPoints; ++var1) {
            this.simplifiedPoints[var1].toTwoD();
        }

    }

    public double[][] simplifiedCurveCoordinates() {
        if (!this.simplifyDone) {
            if (!this.tolerenceEntered) {
                throw new IllegalArgumentException("No tolerance has been entered");
            }

            this.douglasPeucker();
        }

        return Point.getArrayCoordinates(this.simplifiedPoints);
    }

    public Point[] simplifiedCurve() {
        if (!this.simplifyDone) {
            if (!this.tolerenceEntered) {
                throw new IllegalArgumentException("No tolerance has been entered");
            }

            this.douglasPeucker();
        }

        return this.simplifiedPoints;
    }

    public int numberOfSimplifiedCurvePoints() {
        return this.nSimplifiedPoints;
    }

    public int[] simplifiedCurveIndices() {
        return this.simplifiedIndices;
    }

    public void setTolerance(double var1) {
        this.tolerance = var1;
        this.toleranceSquared = var1 * var1;
        this.tolerenceEntered = true;
    }

    public double getTolerance() {
        if (!this.tolerenceEntered) {
            System.out.println("No tolerance has been entered; 0.0 returned");
        }

        return this.tolerance;
    }

    public void plot() {
        if (this.pointDimension != 2) {
            throw new IllegalArgumentException("Plot will only function for an array of 2D points");
        } else {
            double[][] var1 = (double[][])Point.getArrayCoordinates(this.originalPoints);
            double[][] var2 = (double[][])this.simplifiedCurveCoordinates();
            double[][] var3 = new double[][]{var1[0], var1[1], var2[0], var2[1]};
            PlotGraph var4 = new PlotGraph(var3);
            int[] var5 = new int[]{3, 3};
            var4.setLine(var5);
            var4.setXaxisLegend("x-coordinate");
            var4.setYaxisLegend("y-coordinate");
            var4.setGraphTitle("Polyline Simplification: tolerance = " + this.tolerance);
            var4.setGraphTitle2("circles = original data, squares = simplified curve");
            var4.setFrame();
        }
    }
}
