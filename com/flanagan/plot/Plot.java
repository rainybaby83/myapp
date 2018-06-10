//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.plot;

import com.flanagan.interpolation.CubicSpline;
import com.flanagan.math.ArrayMaths;
import com.flanagan.math.Fmath;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class Plot extends Canvas implements Serializable {
    protected static final long serialVersionUID = 1L;
    protected double[][] data = (double[][])null;
    protected double[][] copy = (double[][])null;
    protected int nCurves = 0;
    protected int[] nPoints = null;
    protected int nmPoints = 0;
    protected int niPoints = 200;
    protected int[] pointOpt = null;
    protected int[] pointSize = null;
    protected int npTypes = 8;
    protected boolean[] errorBar = null;
    protected double[][] errors = (double[][])null;
    protected double[][] errorsCopy = (double[][])null;
    protected int[] lineOpt = null;
    protected int[] dashLength = null;
    protected boolean[] minMaxOpt = null;
    protected boolean[] trimOpt = null;
    protected int fontSize = 14;
    protected int xLength = 500;
    protected int yLength = 300;
    protected int xBottom = 50;
    protected int xTop;
    protected int yTop = 10;
    protected int yBottom;
    protected double xLow;
    protected double xHigh;
    protected double yLow;
    protected double yHigh;
    protected int xFac;
    protected int yFac;
    protected int xTicks;
    protected int yTicks;
    protected double xMin;
    protected double xMax;
    protected double yMin;
    protected double yMax;
    protected double xOffset;
    protected double yOffset;
    protected boolean noXoffset;
    protected boolean noYoffset;
    protected double xLowFac;
    protected double yLowFac;
    protected String graphTitle;
    protected String graphTitle2;
    protected String xAxisLegend;
    protected String xAxisUnits;
    protected String yAxisLegend;
    protected String yAxisUnits;
    protected boolean xZero;
    protected boolean yZero;
    protected boolean noXunits;
    protected boolean noYunits;
    protected double[] xAxisNo;
    protected double[] yAxisNo;
    protected String[] xAxisChar;
    protected String[] yAxisChar;
    protected int[] axisTicks;
    protected static double dataFill = 0.0D / 0.0;

    public Plot(double[][] var1) {
        this.xTop = this.xBottom + this.xLength;
        this.yBottom = this.yTop + this.yLength;
        this.xLow = 0.0D;
        this.xHigh = 0.0D;
        this.yLow = 0.0D;
        this.yHigh = 0.0D;
        this.xFac = 0;
        this.yFac = 0;
        this.xTicks = 0;
        this.yTicks = 0;
        this.xMin = 0.0D;
        this.xMax = 0.0D;
        this.yMin = 0.0D;
        this.yMax = 0.0D;
        this.xOffset = 0.0D;
        this.yOffset = 0.0D;
        this.noXoffset = false;
        this.noYoffset = false;
        this.xLowFac = 0.75D;
        this.yLowFac = 0.75D;
        this.graphTitle = "  ";
        this.graphTitle2 = "  ";
        this.xAxisLegend = "  ";
        this.xAxisUnits = "  ";
        this.yAxisLegend = "  ";
        this.yAxisUnits = "  ";
        this.xZero = false;
        this.yZero = false;
        this.noXunits = true;
        this.noYunits = true;
        this.xAxisNo = new double[50];
        this.yAxisNo = new double[50];
        this.xAxisChar = new String[50];
        this.yAxisChar = new String[50];
        this.axisTicks = new int[50];
        this.initialise(var1);
        this.setBackground(Color.white);
    }

    public Plot(double[] var1, double[] var2) {
        this.xTop = this.xBottom + this.xLength;
        this.yBottom = this.yTop + this.yLength;
        this.xLow = 0.0D;
        this.xHigh = 0.0D;
        this.yLow = 0.0D;
        this.yHigh = 0.0D;
        this.xFac = 0;
        this.yFac = 0;
        this.xTicks = 0;
        this.yTicks = 0;
        this.xMin = 0.0D;
        this.xMax = 0.0D;
        this.yMin = 0.0D;
        this.yMax = 0.0D;
        this.xOffset = 0.0D;
        this.yOffset = 0.0D;
        this.noXoffset = false;
        this.noYoffset = false;
        this.xLowFac = 0.75D;
        this.yLowFac = 0.75D;
        this.graphTitle = "  ";
        this.graphTitle2 = "  ";
        this.xAxisLegend = "  ";
        this.xAxisUnits = "  ";
        this.yAxisLegend = "  ";
        this.yAxisUnits = "  ";
        this.xZero = false;
        this.yZero = false;
        this.noXunits = true;
        this.noYunits = true;
        this.xAxisNo = new double[50];
        this.yAxisNo = new double[50];
        this.xAxisChar = new String[50];
        this.yAxisChar = new String[50];
        this.axisTicks = new int[50];
        int var3 = var1.length;
        int var4 = var2.length;
        if (var3 != var4) {
            throw new IllegalArgumentException("x-data length is not equal to the y-data length");
        } else {
            double[][] var5 = new double[2][var3];

            for(int var6 = 0; var6 < var3; ++var6) {
                var5[0][var6] = var1[var6];
                var5[1][var6] = var2[var6];
            }

            this.initialise(var5);
            this.setBackground(Color.white);
        }
    }

    private void initialise(double[][] var1) {
        this.nCurves = var1.length / 2;
        this.nPoints = new int[this.nCurves];
        this.lineOpt = new int[this.nCurves];
        this.dashLength = new int[this.nCurves];
        this.trimOpt = new boolean[this.nCurves];
        this.minMaxOpt = new boolean[this.nCurves];
        this.pointOpt = new int[this.nCurves];
        this.pointSize = new int[this.nCurves];
        this.errorBar = new boolean[this.nCurves];
        this.nmPoints = 0;
        boolean var2 = false;

        int i;
        for(i = 0; i < 2 * this.nCurves; ++i) {
            int var13;
            if ((var13 = var1[i].length) > this.nmPoints) {
                this.nmPoints = var13;
            }
        }

        this.data = new double[2 * this.nCurves][this.nmPoints];
        this.copy = new double[2 * this.nCurves][this.nmPoints];
        this.errors = new double[this.nCurves][this.nmPoints];
        this.errorsCopy = new double[this.nCurves][this.nmPoints];
        boolean var14 = false;
        boolean var4 = false;
        boolean var5 = false;
        boolean var6 = true;

        int var15;
        for(int j = 0; j < this.nCurves; ++j) {
            i = 2 * j;
            var6 = true;
            var15 = var1[i].length;
            int var16 = var1[i + 1].length;
            if (var15 != var16) {
                throw new IllegalArgumentException("an x and y array length differ");
            }

            this.nPoints[j] = var15;
        }

        i = 0;
        boolean var17 = true;

        int k;
        for(k = 0; k < this.nCurves; ++k) {
            var6 = true;
            var15 = this.nPoints[k];

            while(var6) {
                if (var15 < 0) {
                    throw new IllegalArgumentException("curve array index  " + i + ": blank array");
                }

                if (var1[i][var15 - 1] != var1[i][var15 - 1]) {
                    if (var1[i + 1][var15 - 1] != var1[i + 1][var15 - 1]) {
                        --var15;
                        var17 = false;
                    } else {
                        var6 = false;
                    }
                } else {
                    var6 = false;
                }
            }

            this.nPoints[k] = var15;
            i += 2;
        }

        i = 0;

        int m;
        for(k = 0; k < this.nCurves; ++k) {
            double[][] var9 = new double[2][this.nPoints[k]];

            for(m = 0; m < this.nPoints[k]; ++m) {
                var9[0][m] = var1[i][m];
                var9[1][m] = var1[i + 1][m];
            }

            var9 = doubleSelectionSort(var9);

            for(m = 0; m < this.nPoints[k]; ++m) {
                var1[i][m] = var9[0][m];
                var1[i + 1][m] = var9[1][m];
            }

            i += 2;
        }

        i = 0;
        k = 1;

        for(int var18 = 0; var18 < this.nCurves; ++var18) {
            m = 1;

            int var11;
            for(var11 = 1; var11 < this.nPoints[var18]; ++var11) {
                if (var1[i][var11] < var1[i][var11 - 1]) {
                    ++m;
                }
            }

            if (m == this.nPoints[var18]) {
                double[] var19 = new double[this.nPoints[var18]];

                int var12;
                for(var12 = 0; var12 < this.nPoints[var18]; ++var12) {
                    var19[var12] = var1[i][var12];
                }

                for(var12 = 0; var12 < this.nPoints[var18]; ++var12) {
                    var1[i][var12] = var19[this.nPoints[var18] - var12 - 1];
                }

                for(var12 = 0; var12 < this.nPoints[var18]; ++var12) {
                    var19[var12] = var1[i + 1][var12];
                }

                for(var12 = 0; var12 < this.nPoints[var18]; ++var12) {
                    var1[i + 1][var12] = var19[this.nPoints[var18] - var12 - 1];
                }
            }

            for(var11 = 0; var11 < this.nPoints[var18]; ++var11) {
                this.data[i][var11] = var1[i][var11];
                this.data[i + 1][var11] = var1[i + 1][var11];
                this.copy[i][var11] = var1[i][var11];
                this.copy[i + 1][var11] = var1[i + 1][var11];
            }

            this.lineOpt[var18] = 1;
            this.dashLength[var18] = 5;
            this.trimOpt[var18] = false;
            if (this.lineOpt[var18] == 1) {
                this.trimOpt[var18] = true;
            }

            this.minMaxOpt[var18] = true;
            this.pointSize[var18] = 6;
            this.errorBar[var18] = false;
            this.pointOpt[var18] = k;
            i += 2;
            ++k;
            if (k > this.npTypes) {
                k = 1;
            }
        }

    }

    public static double[][] doubleSelectionSort(double[][] var0) {
        boolean var1 = false;
        int var2 = -1;
        int var3 = var0[0].length;
        double var4 = 0.0D;
        double var6 = 0.0D;
        double[][] var8 = new double[2][var3];

        int var9;
        for(var9 = 0; var9 < var3; ++var9) {
            var8[0][var9] = var0[0][var9];
            var8[1][var9] = var0[1][var9];
        }

        while(var2 != var3 - 1) {
            int var10 = var2 + 1;

            for(var9 = var2 + 2; var9 < var3; ++var9) {
                if (var8[0][var9] < var8[0][var10]) {
                    var10 = var9;
                }
            }

            ++var2;
            var4 = var8[0][var10];
            var8[0][var10] = var8[0][var2];
            var8[0][var2] = var4;
            var6 = var8[1][var10];
            var8[1][var10] = var8[1][var2];
            var8[1][var2] = var6;
        }

        return var8;
    }

    public static double[][] fillData(int row, int col) {
        double[][] datas = new double[2 * row][col];

        for(int i = 0; i < 2 * row; ++i) {
            for(int j = 0; j < col; ++j) {
                datas[i][j] = dataFill;
            }
        }
        return datas;
    }


    public static void setDataFillValue(double var0) {
        dataFill = var0;
    }

    public static double getDataFillValue() {
        return dataFill;
    }

    public void setGraphTitle(String var1) {
        this.graphTitle = var1;
    }

    public void setGraphTitle2(String var1) {
        this.graphTitle2 = var1;
    }

    public void setXaxisLegend(String var1) {
        this.xAxisLegend = var1;
    }

    public void setYaxisLegend(String var1) {
        this.yAxisLegend = var1;
    }

    public void setXaxisUnitsName(String var1) {
        this.xAxisUnits = var1;
        this.noXunits = false;
    }

    public void setYaxisUnitsName(String var1) {
        this.yAxisUnits = var1;
        this.noYunits = false;
    }

    public int getXaxisLen() {
        return this.xLength;
    }

    public int getYaxisLen() {
        return this.yLength;
    }

    public int getXlow() {
        return this.xBottom;
    }

    public int getYhigh() {
        return this.yTop;
    }

    public int[] getPointsize() {
        return this.pointSize;
    }

    public int[] getDashlength() {
        return this.dashLength;
    }

    public double getXlowFac() {
        return 1.0D - this.xLowFac;
    }

    public double getYlowFac() {
        return 1.0D - this.yLowFac;
    }

    public double getXmin() {
        return this.xMin;
    }

    public double getXmax() {
        return this.xMax;
    }

    public double getYmin() {
        return this.yMin;
    }

    public double getYmax() {
        return this.yMax;
    }

    public int[] getLine() {
        return this.lineOpt;
    }

    public int[] getPoint() {
        return this.pointOpt;
    }

    public int getNiPoints() {
        return this.niPoints;
    }

    public int getFontSize() {
        return this.fontSize;
    }

    public void setXaxisLen(int var1) {
        this.xLength = var1;
        this.update();
    }

    public void setYaxisLen(int var1) {
        this.yLength = var1;
        this.update();
    }

    public void setXlow(int var1) {
        this.xBottom = var1;
        this.update();
    }

    public void setYhigh(int var1) {
        this.yTop = var1;
        this.update();
    }

    public void setXlowFac(double var1) {
        this.xLowFac = 1.0D - var1;
    }

    public void setYlowFac(double var1) {
        this.yLowFac = 1.0D - var1;
    }

    public void setNoXoffset(boolean var1) {
        this.noXoffset = var1;
    }

    public void setNoYoffset(boolean var1) {
        this.noYoffset = var1;
    }

    public void setNoOffset(boolean var1) {
        this.noXoffset = var1;
        this.noYoffset = var1;
    }

    public boolean getNoXoffset() {
        return this.noXoffset;
    }

    public boolean getNoYoffset() {
        return this.noYoffset;
    }

    protected void update() {
        this.xTop = this.xBottom + this.xLength;
        this.yBottom = this.yTop + this.yLength;
    }

    public void setLine(int[] var1) {
        int var2 = var1.length;
        if (var2 != this.nCurves) {
            throw new IllegalArgumentException("input array of wrong length");
        } else {
            int var3;
            for(var3 = 0; var3 < var2; ++var3) {
                if (var1[var3] < 0 || var1[var3] > 4) {
                    throw new IllegalArgumentException("lineOpt must be 0, 1, 2, 3 or 4");
                }
            }

            this.lineOpt = var1;

            for(var3 = 0; var3 < this.lineOpt.length; ++var3) {
                if (this.lineOpt[var3] == 1 || this.lineOpt[var3] == 2) {
                    boolean var4 = false;

                    int var5;
                    for(var5 = 1; var5 < this.nPoints[var3]; ++var5) {
                        if (this.data[var3][var5] < this.data[var3][var5 - 1]) {
                            var4 = true;
                        }
                    }

                    if (var4) {
                        var5 = 1;

                        int var6;
                        for(var6 = 1; var6 < this.nPoints[var3]; ++var6) {
                            if (this.data[2 * var3][var6] > this.data[2 * var3][var6 - 1]) {
                                ++var5;
                            }
                        }

                        if (var5 == this.nPoints[var3]) {
                            var1[var3] = -var1[var3];
                        } else {
                            var5 = 1;

                            for(var6 = 1; var6 < this.nPoints[var3]; ++var6) {
                                if (this.data[2 * var3][var6] < this.data[2 * var3][var6 - 1]) {
                                    ++var5;
                                }
                            }

                            if (var5 != this.nPoints[var3]) {
                                System.out.println("Curve " + var3 + " will not support interpolation");
                                System.out.println("Straight connecting line option used");
                                if (this.lineOpt[var3] == 1) {
                                    this.lineOpt[var3] = 3;
                                }

                                if (this.lineOpt[var3] == 2) {
                                    this.lineOpt[var3] = 4;
                                }
                            } else {
                                double[] var8 = new double[this.nPoints[var3]];

                                int var7;
                                for(var7 = 0; var7 < this.nPoints[var3]; ++var7) {
                                    var8[var7] = this.data[var3][var7];
                                }

                                for(var7 = 0; var7 < this.nPoints[var3]; ++var7) {
                                    this.data[var3][var7] = var8[this.nPoints[var3] - var7 - 1];
                                }

                                for(var7 = 0; var7 < this.nPoints[var3]; ++var7) {
                                    var8[var7] = this.data[2 * var3][var7];
                                }

                                for(var7 = 0; var7 < this.nPoints[var3]; ++var7) {
                                    this.data[2 * var3][var7] = var8[this.nPoints[var3] - var7 - 1];
                                }

                                this.lineOpt[var3] = -var1[var3];
                            }
                        }
                    }
                }
            }

        }
    }

    public void setLine(int var1) {
        if (var1 >= 0 && var1 <= 3) {
            for(int var2 = 0; var2 < this.nCurves; ++var2) {
                this.lineOpt[var2] = var1;
            }

        } else {
            throw new IllegalArgumentException("lineOpt must be 0, 1, 2 or 3");
        }
    }

    public void setDashLength(int[] var1) {
        if (var1.length != this.nCurves) {
            throw new IllegalArgumentException("input array of wrong length");
        } else {
            this.dashLength = var1;
        }
    }

    public void setDashLength(int var1) {
        for(int var2 = 0; var2 < this.nCurves; ++var2) {
            this.dashLength[var2] = var1;
        }

    }

    public void setPoint(int[] var1) {
        int var2 = var1.length;
        if (var2 != this.nCurves) {
            throw new IllegalArgumentException("input array of wrong length");
        } else {
            for(int var3 = 0; var3 < var2; ++var3) {
                if (var1[var3] < 0 || var1[var3] > 8) {
                    throw new IllegalArgumentException("pointOpt must be 0, 1, 2, 3, 4, 5, 6, 7, or 8");
                }
            }

            this.pointOpt = var1;
        }
    }

    public void setPoint(int var1) {
        if (var1 >= 0 && var1 <= 8) {
            for(int var2 = 0; var2 < this.nCurves; ++var2) {
                this.pointOpt[var2] = var1;
            }

        } else {
            throw new IllegalArgumentException("pointOpt must be 0, 1, 2, 3, 4, 5, 6, 7, or 8");
        }
    }

    public void setPointSize(int[] var1) {
        if (var1.length != this.nCurves) {
            throw new IllegalArgumentException("input array of wrong length");
        } else {
            for(int var2 = 0; var2 < this.nCurves; ++var2) {
                if (var1[var2] != var1[var2] / 2 * 2) {
                    ++var1[var2];
                }

                this.pointSize[var2] = var1[var2];
            }

        }
    }

    public void setPointSize(int var1) {
        if (var1 % 2 != 0) {
            ++var1;
        }

        for(int var2 = 0; var2 < this.nCurves; ++var2) {
            this.pointSize[var2] = var1;
        }

    }

    public void setErrorBars(int var1, double[] var2) {
        if (var2.length != this.nPoints[var1]) {
            throw new IllegalArgumentException("input array of wrong length");
        } else {
            this.errorBar[var1] = true;

            for(int var3 = 0; var3 < this.nPoints[var1]; ++var3) {
                this.errors[var1][var3] = var2[var3];
                this.errorsCopy[var1][var3] = var2[var3];
            }

        }
    }

    public void setNiPoints(int var1) {
        this.niPoints = var1;
    }

    public void setFontSize(int var1) {
        this.fontSize = var1;
    }

    public void setTrimOpt(boolean[] var1) {
        this.trimOpt = var1;
    }

    public void setMinMaxOpt(boolean[] var1) {
        this.minMaxOpt = var1;
    }

    public static int scale(double var0, double var2) {
        int var4 = 0;
        double var5 = 0.0D;
        boolean var7 = false;
        if (var0 >= 0.0D && var2 > 0.0D) {
            var5 = var2;
            var7 = true;
        } else if (var0 < 0.0D && var2 <= 0.0D) {
            var5 = -var0;
            var7 = true;
        } else if (var2 > 0.0D && var0 < 0.0D) {
            var5 = Math.max(var2, -var0);
            var7 = true;
        }

        if (var7) {
            if (var5 > 100.0D) {
                while(var5 > 1.0D) {
                    var5 /= 10.0D;
                    --var4;
                }
            }

            if (var5 <= 0.01D) {
                while(var5 <= 0.1D) {
                    var5 *= 10.0D;
                    ++var4;
                }
            }
        }

        return var4;
    }

    public static void limits(double var0, double var2, double var4, double[] var6) {
        double var7 = 1.0D;
        double var9 = 1.0D;
        if (Math.abs(var0) < 1.0D) {
            var7 = 10.0D;
        }

        if (Math.abs(var0) < 0.1D) {
            var7 = 100.0D;
        }

        if (Math.abs(var2) < 1.0D) {
            var9 = 10.0D;
        }

        if (Math.abs(var2) < 0.1D) {
            var9 = 100.0D;
        }

        double var11 = Math.floor(10.0D * var0 * var7) / var7;
        double var13 = Math.ceil(10.0D * var2 * var9) / var9;
        if (var11 >= 0.0D && var13 > 0.0D && var11 < var4 * var13) {
            var11 = 0.0D;
        }

        if (var11 < 0.0D && var13 <= 0.0D && -var13 <= -var4 * var11) {
            var13 = 0.0D;
        }

        var6[0] = var11 / 10.0D;
        var6[1] = var13 / 10.0D;
    }

    public static double offset(double var0, double var2) {
        double var4 = var2 - var0;
        double var6 = Fmath.sign(var2);
        double var8 = Fmath.sign(var0);
        double var10 = 0.0D;
        boolean var12 = false;
        boolean var13 = false;
        if (var6 == var8) {
            int var15 = (int)Math.floor(Fmath.log10(var4));
            int var14;
            if (var6 == 1.0D) {
                var14 = (int)Math.floor(Fmath.log10(var2));
                if (var14 - var15 > 1) {
                    var10 = Math.floor(var0 * Math.pow(10.0D, (double)(-var15))) * Math.pow(10.0D, (double)var15);
                }
            } else {
                var14 = (int)Math.floor(Fmath.log10(Math.abs(var0)));
                if (var14 - var15 > 1) {
                    var10 = Math.floor(var2 * Math.pow(10.0D, (double)(-var15))) * Math.pow(10.0D, (double)var15);
                }
            }
        }

        return var10;
    }

    public void axesScaleOffset() {
        double[] var1 = new double[2];
        int var2 = 0;

        int var3;
        int var4;
        for(var3 = 0; var3 < this.nCurves; ++var3) {
            for(var4 = 0; var4 < this.nPoints[var3]; ++var4) {
                this.data[var2][var4] = this.copy[var2][var4];
                this.data[var2 + 1][var4] = this.copy[var2 + 1][var4];
                this.errors[var3][var4] = this.errorsCopy[var3][var4];
                if (this.errorBar[var3]) {
                    this.errors[var3][var4] += this.data[var2 + 1][var4];
                }
            }

            var2 += 2;
        }

        this.minMax();
        if (!this.noXoffset) {
            this.xOffset = offset(this.xMin, this.xMax);
        }

        if (this.xOffset != 0.0D) {
            var2 = 0;

            for(var3 = 0; var3 < this.nCurves; ++var3) {
                for(var4 = 0; var4 < this.nPoints[var3]; ++var4) {
                    this.data[var2][var4] -= this.xOffset;
                }

                var2 += 2;
            }

            this.xMin -= this.xOffset;
            this.xMax -= this.xOffset;
        }

        if (!this.noYoffset) {
            this.yOffset = offset(this.yMin, this.yMax);
        }

        if (this.yOffset != 0.0D) {
            var2 = 1;

            for(var3 = 0; var3 < this.nCurves; ++var3) {
                for(var4 = 0; var4 < this.nPoints[var3]; ++var4) {
                    this.data[var2][var4] -= this.yOffset;
                    if (this.errorBar[var3]) {
                        this.errors[var3][var4] -= this.yOffset;
                    }
                }

                var2 += 2;
            }

            this.yMin -= this.yOffset;
            this.yMax -= this.yOffset;
        }

        this.xFac = scale(this.xMin, this.xMax);
        if (this.xFac != 0) {
            var2 = 0;

            for(var3 = 0; var3 < this.nCurves; ++var3) {
                for(var4 = 0; var4 < this.nPoints[var3]; ++var4) {
                    this.data[var2][var4] *= Math.pow(10.0D, (double)(this.xFac + 1));
                }

                var2 += 2;
            }

            this.xMin *= Math.pow(10.0D, (double)(this.xFac + 1));
            this.xMax *= Math.pow(10.0D, (double)(this.xFac + 1));
        }

        this.yFac = scale(this.yMin, this.yMax);
        if (this.yFac != 0) {
            var2 = 1;

            for(var3 = 0; var3 < this.nCurves; ++var3) {
                for(var4 = 0; var4 < this.nPoints[var3]; ++var4) {
                    this.data[var2][var4] *= Math.pow(10.0D, (double)(this.yFac + 1));
                    if (this.errorBar[var3]) {
                        this.errors[var3][var4] *= Math.pow(10.0D, (double)(this.yFac + 1));
                    }
                }

                var2 += 2;
            }

            this.yMin *= Math.pow(10.0D, (double)(this.yFac + 1));
            this.yMax *= Math.pow(10.0D, (double)(this.yFac + 1));
        }

        limits(this.xMin, this.xMax, this.xLowFac, var1);
        this.xLow = var1[0];
        this.xHigh = var1[1];
        if (this.xLow < 0.0D && this.xHigh > 0.0D) {
            this.xZero = true;
        }

        limits(this.yMin, this.yMax, this.yLowFac, var1);
        this.yLow = var1[0];
        this.yHigh = var1[1];
        if (this.yLow < 0.0D && this.yHigh > 0.0D) {
            this.yZero = true;
        }

        this.xTicks = ticks(this.xLow, this.xHigh, this.xAxisNo, this.xAxisChar);
        this.xHigh = this.xAxisNo[this.xTicks - 1];
        if (this.xLow != this.xAxisNo[0]) {
            if (this.xOffset != 0.0D) {
                this.xOffset = this.xOffset - this.xLow + this.xAxisNo[0];
            }

            this.xLow = this.xAxisNo[0];
        }

        this.yTicks = ticks(this.yLow, this.yHigh, this.yAxisNo, this.yAxisChar);
        this.yHigh = this.yAxisNo[this.yTicks - 1];
        if (this.yLow != this.yAxisNo[0]) {
            if (this.yOffset != 0.0D) {
                this.yOffset = this.yOffset - this.yLow + this.yAxisNo[0];
            }

            this.yLow = this.yAxisNo[0];
        }

    }

    public static int ticks(double var0, double var2, double[] var4, String[] var5) {
        int[] var6 = new int[]{1, 1, 1, 2, 3};
        double[] var7 = new double[]{1.0D, 10.0D, 1.0D, 0.1D, 0.01D};
        double[] var8 = new double[]{1.0D, 1.0D, 0.1D, 0.01D, 0.001D};
        double var9 = Math.abs(var2);
        double var11 = Math.abs(var0);
        if (var11 > var9) {
            var9 = var11;
        }

        byte var13 = 0;
        if (var9 <= 100.0D) {
            var13 = 1;
        }

        if (var9 <= 10.0D) {
            var13 = 2;
        }

        if (var9 <= 1.0D) {
            var13 = 3;
        }

        if (var9 <= 0.1D) {
            var13 = 4;
        }

        if (var9 > 100.0D || var9 < 0.01D) {
            var13 = 0;
        }

        double var14 = 0.0D;
        double var16 = 0.0D;
        double var18 = 0.0D;
        boolean var20 = false;
        boolean var21 = false;
        boolean var26;
        byte var36;
        byte var37;
        int var39;
        if (var2 > 0.0D && var0 >= 0.0D) {
            var14 = Math.ceil((var2 - var0) / var7[var13]) * var8[var13];
            var37 = 1;
            var16 = var0;
            var18 = var2;
            var36 = 1;
        } else if (var2 <= 0.0D && var0 < 0.0D) {
            var14 = Math.ceil((var2 - var0) / var7[var13]) * var8[var13];
            var37 = -1;
            var16 = var2;
            var18 = var0;
            var36 = -1;
        } else {
            double var22 = Math.abs(Math.ceil(var2));
            double var24 = Math.abs(Math.floor(var0));
            var26 = false;
            if (var22 >= var24) {
                var37 = 2;
                var39 = (int)Math.rint(10.0D * var22 / (var22 + var24));
                var14 = Math.ceil(var2 * 10.0D / (double)var39 / var7[var13]) * var8[var13];
                var16 = 0.0D;
                var18 = var2;
                var36 = 1;
            } else {
                var37 = -2;
                var39 = (int)Math.rint(10.0D * var24 / (var22 + var24));
                var14 = Math.ceil(Math.abs(var0 * 10.0D / (double)var39) / var7[var13]) * var8[var13];
                var16 = 0.0D;
                var18 = var0;
                var36 = -1;
            }
        }

        int var38 = 1;
        double var23 = var16;
        boolean var25 = true;

        while(var25) {
            var23 += (double)var36 * var14;
            ++var38;
            if (Math.abs(var23) >= Math.abs(var18)) {
                var25 = false;
            }
        }

        var26 = false;
        Object var27 = null;
        int var28;
        double[] var40;
        label117:
        switch(var37) {
            case -2:
                var39 = (int)Math.ceil(var2 / var14);
                var38 += var39;
                var40 = new double[var38];
                var40[0] = Fmath.truncate((double)var39 * var14, var6[var13]);

                for(var28 = 1; var28 < var38; ++var28) {
                    var40[var28] = Fmath.truncate(var40[var28 - 1] - var14, var6[var13]);
                }

                var40 = Fmath.reverseArray(var40);
                var28 = 0;

                while(true) {
                    if (var28 >= var38) {
                        break label117;
                    }

                    var4[var28] = var40[var28];
                    ++var28;
                }
            case -1:
                var40 = new double[var38];
                var40[0] = Fmath.truncate(var2, var6[var13]);

                for(var28 = 1; var28 < var38; ++var28) {
                    var40[var28] = Fmath.truncate(var40[var28 - 1] - var14, var6[var13]);
                }

                var40 = Fmath.reverseArray(var40);

                for(var28 = 0; var28 < var38; ++var28) {
                    var4[var28] = var40[var28];
                }
            case 0:
            default:
                break;
            case 1:
                var40 = new double[var38];
                var4[0] = Fmath.truncate(var0, var6[var13]);
                var28 = 1;

                while(true) {
                    if (var28 >= var38) {
                        break label117;
                    }

                    var4[var28] = Fmath.truncate(var4[var28 - 1] + var14, var6[var13]);
                    ++var28;
                }
            case 2:
                var39 = (int)Math.ceil(-var0 / var14);
                var38 += var39;
                var40 = new double[var38];
                var4[0] = Fmath.truncate((double)(-var39) * var14, var6[var13]);

                for(var28 = 1; var28 < var38; ++var28) {
                    var4[var28] = Fmath.truncate(var4[var28 - 1] + var14, var6[var13]);
                }
        }

        ArrayMaths var41 = new ArrayMaths(var4);
        double var29 = var41.maximum();
        double var31 = Math.abs(var41.minimum());
        boolean var33 = true;
        int var34 = 0;

        while(true) {
            while(var33) {
                if (Math.abs(var4[var34]) >= var29 * 1.0E-4D && Math.abs(var4[var34]) >= var31 * 1.0E-4D) {
                    ++var34;
                    if (var34 >= var38) {
                        var33 = false;
                    }
                } else {
                    var4[var34] = 0.0D;
                    var33 = false;
                }
            }

            for(int var35 = 0; var35 < var38; ++var35) {
                var5[var35] = String.valueOf(var4[var35]);
                var5[var35] = var5[var35].trim();
            }

            return var38;
        }
    }

    public void minMax() {
        boolean var1 = true;
        int var2 = 0;

        while(var1) {
            if (this.minMaxOpt[var2]) {
                var1 = false;
                this.xMin = this.data[2 * var2][0];
                this.xMax = this.data[2 * var2][0];
                this.yMin = this.data[2 * var2 + 1][0];
                if (this.errorBar[var2]) {
                    this.yMin = 2.0D * this.yMin - this.errors[var2][0];
                }

                this.yMax = this.data[2 * var2 + 1][0];
                if (this.errorBar[var2]) {
                    this.yMax = this.errors[var2][0];
                }
            } else {
                ++var2;
                if (var2 > this.nCurves) {
                    throw new IllegalArgumentException("At least one curve must be included in the maximum/minimum calculation");
                }
            }
        }

        int var3 = 0;
        double var4 = 0.0D;
        double var6 = 0.0D;

        for(int var8 = 0; var8 < this.nCurves; ++var8) {
            if (this.minMaxOpt[var8]) {
                for(int var9 = 0; var9 < this.nPoints[var8]; ++var9) {
                    if (this.xMin > this.data[var3][var9]) {
                        this.xMin = this.data[var3][var9];
                    }

                    if (this.xMax < this.data[var3][var9]) {
                        this.xMax = this.data[var3][var9];
                    }

                    var4 = this.data[var3 + 1][var9];
                    if (this.errorBar[var8]) {
                        var4 = 2.0D * var4 - this.errors[var8][var9];
                    }

                    if (this.yMin > var4) {
                        this.yMin = var4;
                    }

                    var6 = this.data[var3 + 1][var9];
                    if (this.errorBar[var8]) {
                        var6 = this.errors[var8][var9];
                    }

                    if (this.yMax < var6) {
                        this.yMax = var6;
                    }
                }
            }

            var3 += 2;
        }

        if (this.xMin == this.xMax) {
            if (this.xMin == 0.0D) {
                this.xMin = 0.1D;
                this.xMax = 0.1D;
            } else if (this.xMin < 0.0D) {
                this.xMin *= 1.1D;
            } else {
                this.xMax *= 1.1D;
            }
        }

        if (this.yMin == this.yMax) {
            if (this.yMin == 0.0D) {
                this.yMin = 0.1D;
                this.yMax = 0.1D;
            } else if (this.yMin < 0.0D) {
                this.yMin *= 1.1D;
            } else {
                this.yMax *= 1.1D;
            }
        }

    }

    protected static String offsetString(double var0) {
        String var2 = String.valueOf(var0);
        String var3 = "";
        String var4 = "";
        String var5 = "";
        String var6 = "0";
        int var7 = var2.indexOf(46);
        int var8 = var2.indexOf(69);
        if (var8 == -1) {
            return var2;
        } else {
            var3 = var2.substring(var8 + 1);
            int var9 = Integer.parseInt(var3);
            var3 = var2.substring(0, var8);
            int var10;
            if (var9 >= 0) {
                for(var10 = 0; var10 < var9; ++var10) {
                    var3 = var3 + var6;
                }

                return var3;
            } else {
                var4 = var3.substring(0, var7 + 1);
                var5 = var3.substring(var7 + 1);

                for(var10 = 0; var10 < -var9; ++var10) {
                    var4 = var3 + var6;
                }

                var4 = var4 + var5;
                return var4;
            }
        }
    }

    public boolean printCheck(boolean var1, int var2, int var3, int var4, int var5) {
        boolean var6 = true;
        if (var1) {
            if (var2 < this.xBottom) {
                var6 = false;
            }

            if (var2 > this.xTop) {
                var6 = false;
            }

            if (var3 < this.xBottom) {
                var6 = false;
            }

            if (var3 > this.xTop) {
                var6 = false;
            }

            if (var4 > this.yBottom) {
                var6 = false;
            }

            if (var4 < this.yTop) {
                var6 = false;
            }

            if (var5 > this.yBottom) {
                var6 = false;
            }

            if (var5 < this.yTop) {
                var6 = false;
            }
        }

        return var6;
    }

    public void graph(Graphics graphics) {
        graphics.setFont(new Font("serif", 0, this.fontSize));
        FontMetrics var2 = graphics.getFontMetrics();
        this.axesScaleOffset();
        String var3 = offsetString(this.xOffset);
        String var4 = offsetString(this.yOffset);
        String var5 = "  /( ";
        String var6 = " )";
        String var7 = "  / ";
        String var8 = " ";
        String var9 = " x 10";
        String var10 = "10";
        String var11 = " ";
        String var12 = var5;
        String var13 = var6;
        String var14 = var9;
        if (this.xFac == 0) {
            var12 = var7;
            var13 = "";
            var14 = "";
        }

        String var15 = var5;
        String var16 = var6;
        String var17 = var9;
        if (this.yFac == 0) {
            var15 = var7;
            var16 = "";
            var17 = "";
        }

        if (this.noXunits) {
            if (this.xFac == 0) {
                var12 = var11;
                var13 = var11;
                var14 = var11;
            } else {
                var12 = var7;
                var13 = var8;
                var14 = var10;
            }
        }

        if (this.noYunits) {
            if (this.yFac == 0) {
                var15 = var11;
                var16 = var11;
                var17 = var11;
            } else {
                var15 = var7;
                var16 = var8;
                var17 = var10;
            }
        }

        double var18 = (double)(this.xTop - this.xBottom);
        double var20 = (double)(this.yBottom - this.yTop);
        String var22 = " + ";
        String var23 = " - ";
        String var24 = var23;
//        graphics.drawString(this.graphTitle + " ", 15, 15);
//        graphics.drawString(this.graphTitle2 + " ", 15, 35);
//        if (this.xOffset < 0.0D) {
//            var24 = var22;
//            this.xOffset = -this.xOffset;
//        }
//
//        boolean var25 = false;
//        String var26 = "";
//        String var27 = "";
//        String var28 = "";
//        String var29 = "";
//        int var66;
//        //画X轴图例
//        if (this.xFac == 0 && this.xOffset == 0.0D) {
//            graphics.drawString(this.xAxisLegend + var12 + this.xAxisUnits + var13, xBottom - 4, this.yBottom + 32);
//        } else if (this.xOffset == 0.0D) {
//            var26 = this.xAxisLegend + var12 + this.xAxisUnits + var14;
//            var66 = var2.stringWidth(var26);
//            graphics.drawString(var26, this.xBottom - 4, this.yBottom + 42);
//            var28 = String.valueOf(-this.xFac - 1);
//            graphics.drawString(var28, this.xBottom - 4 + var66 + 1, this.yBottom + 32);
//            var66 += var2.stringWidth(var28);
//            graphics.drawString(var13, this.xBottom - 4 + var66 + 1, this.yBottom + 42);
//        } else if (this.xFac == 0) {
//            graphics.drawString(this.xAxisLegend + var24 + var3 + var12 + this.xAxisUnits + var13, this.xBottom - 4, this.yBottom + 30);
//        } else {
//            var26 = this.xAxisLegend + var24 + var3 + var12 + this.xAxisUnits + var14;
//            var66 = var2.stringWidth(var26);
//            graphics.drawString(var26, this.xBottom - 4, this.yBottom + 37);
//            var28 = String.valueOf(-this.xFac - 1);
//            graphics.drawString(var28, this.xBottom - 4 + var66 + 1, this.yBottom + 32);
//            var66 += var2.stringWidth(var28);
//            graphics.drawString(var13, this.xBottom - 4 + var66 + 1, this.yBottom + 37);
//        }
//
//        var24 = var23;
//        if (this.yOffset < 0.0D) {
//            var24 = var22;
//            this.yOffset = -this.yOffset;
//        }
//
//        //画Y轴图例
//        if (this.yFac == 0 && this.yOffset == 0.0D) {
//            graphics.drawString(this.yAxisLegend + " ", 15, this.yTop - 25);
//            graphics.drawString(var15 + this.yAxisUnits + var16, 15, this.yTop - 10);
//        } else if (this.yOffset == 0.0D) {
//            graphics.drawString(this.yAxisLegend, 15, this.yTop - 35);
//            var28 = var15 + this.yAxisUnits + var17;
//            graphics.drawString(var28, 15, this.yTop - 15);
//            var66 = var2.stringWidth(var28);
//            var29 = String.valueOf(-this.yFac - 1);
//            graphics.drawString(var29, 15 + var66 + 1, this.yTop - 20);
//            var66 += var2.stringWidth(var29);
//            graphics.drawString(var16, 15 + var66 + 1, this.yTop - 15);
//        } else if (this.yFac == 0) {
//            graphics.drawString(this.yAxisLegend + var24 + var4, 15, this.yTop - 25);
//            graphics.drawString(var15 + this.yAxisUnits + var16, 15, this.yTop - 10);
//        } else {
//            var27 = this.yAxisLegend + var24 + var4;
//            graphics.drawString(var27, 15, this.yTop - 35);
//            var28 = var15 + this.yAxisUnits + var17;
//            graphics.drawString(var28, 15, this.yTop - 15);
//            var66 = var2.stringWidth(var28);
//            var29 = String.valueOf(-this.yFac - 1);
//            graphics.drawString(var29, 15 + var66 + 1, this.yTop - 20);
//            var66 += var2.stringWidth(var29);
//            graphics.drawString(var16, 15 + var66 + 1, this.yTop - 15);
//        }
//
//        boolean var30 = false;
//        boolean var31 = false;
//        boolean var32 = false;
//        boolean var33 = false;
        double var34 = 0.0D;
        double var36 = this.xHigh - this.xLow;
        double var38 = this.yHigh - this.yLow;

        //画4条边界
        graphics.drawLine(this.xBottom, this.yBottom, this.xTop, this.yBottom);
        graphics.drawLine(this.xBottom, this.yTop, this.xTop, this.yTop);
        graphics.drawLine(this.xBottom, this.yBottom, this.xBottom, this.yTop);
        graphics.drawLine(this.xTop, this.yBottom, this.xTop, this.yTop);
        byte var67;
        int var68;
        int var69;
        int var70;
        if (this.xZero) {
            var67 = 8;
            var70 = this.xBottom + (int)((0.0D - this.xLow) / var36 * var18);
            graphics.drawLine(var70, this.yTop, var70, this.yTop + 8);
            graphics.drawLine(var70, this.yBottom, var70, this.yBottom - 8);

            for(var68 = this.yTop; var68 + var67 < this.yBottom; var68 = var69 + var67) {
                var69 = var68 + var67;
                graphics.drawLine(var70, var68, var70, var69);
            }
        }

        if (this.yZero) {
            var67 = 8;
            var70 = this.yBottom - (int)((0.0D - this.yLow) / var38 * var20);
            graphics.drawLine(this.xBottom, var70, this.xBottom + 8, var70);
            graphics.drawLine(this.xTop, var70, this.xTop - 8, var70);

            for(var68 = this.xBottom; var68 + var67 < this.xTop; var68 = var69 + var67) {
                var69 = var68 + var67;
                graphics.drawLine(var68, var70, var69, var70);
            }
        }

        boolean var40 = false;

        int var41;
        for(var41 = 0; var41 < this.xTicks; ++var41) {
            int var71 = this.xBottom + (int)((this.xAxisNo[var41] - this.xLow) / var36 * var18);
            graphics.drawLine(var71, this.yBottom, var71, this.yBottom - 8);
            graphics.drawLine(var71, this.yTop, var71, this.yTop + 8);
            graphics.drawString(this.xAxisChar[var41] + " ", var71 - 4, this.yBottom + 18);
        }

        boolean var72 = false;
        int var42 = this.yAxisChar[0].length();

        int var43;
        for(var43 = 1; var43 < this.yTicks; ++var43) {
            if (this.yAxisChar[var43].length() > var42) {
                var42 = this.yAxisChar[var43].length();
            }
        }

        var43 = (var42 - 3) * 5;
        double var44 = (double)(-this.yTop + this.yBottom) / (double)(this.yTicks - 1);

        int var46;
        for(var46 = 0; var46 < this.yTicks; ++var46) {
            var41 = this.yBottom - (int)Math.round((double)var46 * var44);
            var41 = this.yBottom - (int)((this.yAxisNo[var46] - this.yLow) / var38 * var20);
            graphics.drawLine(this.xBottom, var41, this.xBottom + 8, var41);
            graphics.drawLine(this.xTop, var41, this.xTop - 8, var41);
            graphics.drawString(this.yAxisChar[var46] + " ", this.xBottom - 30 - var43, var41 + 4);
        }

        var46 = 0;
        boolean var47 = true;
        int var48 = 0;
        boolean var49 = false;
        boolean var50 = false;
        boolean var51 = false;
        boolean var52 = false;
        boolean var53 = false;
        boolean var54 = false;
        boolean var55 = false;
        boolean var56 = false;
        boolean var57 = false;
        boolean var58 = false;
        double[] var59 = new double[this.niPoints];
        boolean var60 = true;

        for(int var61 = 0; var61 < this.nCurves; ++var61) {
            int var82 = this.nPoints[var61];
            double[] var62 = new double[var82];
            double[] var63 = new double[var82];
            CubicSpline var64;
            int var65;
            int var76;
            int var77;
            int var78;
            int var79;
            if (this.lineOpt[var61] == 1 || this.lineOpt[var61] == 2) {
                var64 = new CubicSpline(this.nPoints[var61]);

                for(var65 = 0; var65 < var82; ++var65) {
                    var62[var65] = this.data[var48][var65];
                }

                var34 = (var62[var82 - 1] - var62[0]) / (double)(this.niPoints - 1);
                var59[0] = var62[0];

                for(var65 = 1; var65 < this.niPoints; ++var65) {
                    var59[var65] = var59[var65 - 1] + var34;
                }

                var59[this.niPoints - 1] = var62[var82 - 1];

                for(var65 = 0; var65 < var82; ++var65) {
                    var63[var65] = this.data[var48 + 1][var65];
                }

                var64.resetData(var62, var63);
                var64.calcDeriv();
                var76 = this.xBottom + (int)((var62[0] - this.xLow) / var36 * var18);
                var78 = this.yBottom - (int)((var63[0] - this.yLow) / var38 * var20);

                for(var65 = 1; var65 < this.niPoints; ++var65) {
                    var77 = this.xBottom + (int)((var59[var65] - this.xLow) / var36 * var18);
                    var79 = this.yBottom - (int)((var64.interpolate(var59[var65]) - this.yLow) / var38 * var20);
                    var60 = this.printCheck(this.trimOpt[var61], var76, var77, var78, var79);
                    if (var60) {
                        if (this.lineOpt[var61] == 2) {
                            ++var46;
                            if (var46 > this.dashLength[var61]) {
                                var46 = 0;
                                if (var47) {
                                    var47 = false;
                                } else {
                                    var47 = true;
                                }
                            }
                        }

                        if (var47) {
                            graphics.drawLine(var76, var78, var77, var79);
                        }
                    }

                    var76 = var77;
                    var78 = var79;
                }
            }

            if (this.lineOpt[var61] == -1 || this.lineOpt[var61] == -2) {
                var64 = new CubicSpline(this.nPoints[var61]);

                for(var65 = 0; var65 < var82; ++var65) {
                    var62[var65] = this.data[var48][var65];
                }

                for(var65 = 0; var65 < var82; ++var65) {
                    var63[var65] = this.data[var48 + 1][var65];
                }

                var34 = (var63[var82 - 1] - var63[0]) / (double)(this.niPoints - 1);
                var59[0] = var63[0];

                for(var65 = 1; var65 < this.niPoints; ++var65) {
                    var59[var65] = var59[var65 - 1] + var34;
                }

                var59[this.niPoints - 1] = var63[var82 - 1];
                var64.resetData(var63, var62);
                var64.calcDeriv();
                var76 = this.xBottom + (int)((var62[0] - this.xLow) / var36 * var18);
                var78 = this.yBottom - (int)((var63[0] - this.yLow) / var38 * var20);

                for(var65 = 1; var65 < this.niPoints; ++var65) {
                    var79 = this.yBottom + (int)((var59[var65] - this.yLow) / var38 * var20);
                    var77 = this.xBottom - (int)((var64.interpolate(var59[var65]) - this.xLow) / var36 * var18);
                    var60 = this.printCheck(this.trimOpt[var61], var76, var77, var78, var79);
                    if (var60) {
                        if (this.lineOpt[var61] == 2) {
                            ++var46;
                            if (var46 > this.dashLength[var61]) {
                                var46 = 0;
                                if (var47) {
                                    var47 = false;
                                } else {
                                    var47 = true;
                                }
                            }
                        }

                        if (var47) {
                            graphics.drawLine(var76, var78, var77, var79);
                        }
                    }

                    var76 = var77;
                    var78 = var79;
                }
            }

            int var83;
            if (this.lineOpt[var61] == 3) {
                var46 = 0;
                var47 = true;
                var76 = this.xBottom + (int)((this.data[var48][0] - this.xLow) / var36 * var18);
                var78 = this.yBottom - (int)((this.data[var48 + 1][0] - this.yLow) / var38 * var20);

                for(var83 = 1; var83 < var82; ++var83) {
                    var77 = this.xBottom + (int)((this.data[var48][var83] - this.xLow) / var36 * var18);
                    var79 = this.yBottom - (int)((this.data[var48 + 1][var83] - this.yLow) / var38 * var20);
                    var60 = this.printCheck(this.trimOpt[var61], var76, var77, var78, var79);
                    if (var60) {
                        graphics.drawLine(var76, var78, var77, var79);
                    }

                    var76 = var77;
                    var78 = var79;
                }
            }

            if (this.lineOpt[var61] == 4) {
                var46 = 0;
                var47 = true;
                var76 = this.xBottom + (int)((this.data[var48][0] - this.xLow) / var36 * var18);
                var78 = this.yBottom - (int)((this.data[var48 + 1][0] - this.yLow) / var38 * var20);

                for(var83 = 1; var83 < var82; ++var83) {
                    ++var46;
                    if (var46 > this.dashLength[var61]) {
                        var46 = 0;
                        if (var47) {
                            var47 = false;
                        } else {
                            var47 = true;
                        }
                    }

                    var77 = this.xBottom + (int)((this.data[var48][var83] - this.xLow) / var36 * var18);
                    var79 = this.yBottom - (int)((this.data[var48 + 1][var83] - this.yLow) / var38 * var20);
                    this.printCheck(this.trimOpt[var61], var76, var77, var78, var79);
                    if (var47) {
                        graphics.drawLine(var76, var78, var77, var79);
                    }

                    var76 = var77;
                    var78 = var79;
                }
            }

            if (this.pointOpt[var61] > 0) {
                for(var83 = 0; var83 < var82; ++var83) {
                    int var80 = this.pointSize[var61];
                    int var81 = var80 / 2;
                    int var73 = this.xBottom + (int)((this.data[var48][var83] - this.xLow) / var36 * var18);
                    int var74 = this.yBottom - (int)((this.data[var48 + 1][var83] - this.yLow) / var38 * var20);
                    switch(this.pointOpt[var61]) {
                        case 1:
                            graphics.drawOval(var73 - var81, var74 - var81, var80, var80);
                            break;
                        case 2:
                            graphics.drawRect(var73 - var81, var74 - var81, var80, var80);
                            break;
                        case 3:
                            graphics.drawLine(var73 - var81, var74, var73, var74 + var81);
                            graphics.drawLine(var73, var74 + var81, var73 + var81, var74);
                            graphics.drawLine(var73 + var81, var74, var73, var74 - var81);
                            graphics.drawLine(var73, var74 - var81, var73 - var81, var74);
                            break;
                        case 4:
                            graphics.fillOval(var73 - var81, var74 - var81, var80, var80);
                            break;
                        case 5:
                            graphics.fillRect(var73 - var81, var74 - var81, var80, var80);
                            break;
                        case 6:
                            for(var65 = 0; var65 < var81; ++var65) {
                                graphics.drawLine(var73 - var65, var74 - var81 + var65, var73 + var65, var74 - var81 + var65);
                            }

                            for(var65 = 0; var65 <= var81; ++var65) {
                                graphics.drawLine(var73 - var81 + var65, var74 + var65, var73 + var81 - var65, var74 + var65);
                            }
                            break;
                        case 7:
                            graphics.drawLine(var73 - var81, var74 - var81, var73 + var81, var74 + var81);
                            graphics.drawLine(var73 - var81, var74 + var81, var73 + var81, var74 - var81);
                            break;
                        case 8:
                            graphics.drawLine(var73 - var81, var74, var73 + var81, var74);
                            graphics.drawLine(var73, var74 + var81, var73, var74 - var81);
                            break;
                        default:
                            graphics.drawLine(var73 - var81, var74 - var81, var73 + var81, var74 + var81);
                            graphics.drawLine(var73 - var81, var74 + var81, var73 + var81, var74 - var81);
                    }

                    if (this.errorBar[var61]) {
                        int var75 = this.yBottom - (int)((this.errors[var61][var83] - this.yLow) / var38 * var20);
                        graphics.drawLine(var73, var74, var73, var75);
                        graphics.drawLine(var73 - 4, var75, var73 + 4, var75);
                        var75 = 2 * var74 - var75;
                        graphics.drawLine(var73, var74, var73, var75);
                        graphics.drawLine(var73 - 4, var75, var73 + 4, var75);
                    }
                }
            }

            var48 += 2;
        }

    }

    public static long getSerialVersionUID() {
        return 1L;
    }
}
