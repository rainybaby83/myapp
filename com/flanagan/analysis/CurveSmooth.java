//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.analysis;

import com.flanagan.interpolation.CubicSpline;
import com.flanagan.math.ArrayMaths;
import com.flanagan.math.Conv;
import com.flanagan.math.Fmath;
import com.flanagan.math.Matrix;
import com.flanagan.plot.PlotGraph;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

public class CurveSmooth {
    private double[] xData = null;
    private double[] yData = null;
    private BigDecimal[] xBDdata = null;
    private BigDecimal[] yBDdata = null;
    private int nPoints = 0;
    private boolean arbprec = false;
    private double[] yDataMovAv = null;
    private BigDecimal[] yDataMovAvBD = null;
    private double[] yDataSavGol = null;
    private double[] yDataSavGolFirst = null;
    private double[] yDataSavGolSecond = null;
    private double[] yDataSavGolNth = null;
    private int lastMethod = 0;
    private int nMethods = 2;
    private int lastPlotMethod = 0;
    private int maWindowWidth = 0;
    private int sgWindowWidth = 0;
    private int sgPolyDeg = 4;
    private double[] derivSavGol = null;
    private int sgDerivOrderUsed = 0;
    private boolean calcSavGol = false;
    private boolean calcMovAv = false;
    private boolean firstDeriv = false;
    private boolean secondDeriv = false;
    private boolean nthSet = false;
    private double[][] sgArrayC = (double[][])null;
    private double[] sgCoeff = null;
    private double extentMovAv = -1.0D;
    private double extentSavGol = -1.0D;
    private ArrayList<Double> almin = new ArrayList();
    private ArrayList<Double> almax = new ArrayList();
    private ArrayList<BigDecimal> alminBD = new ArrayList();
    private ArrayList<BigDecimal> almaxBD = new ArrayList();
    private double[][][] minima = (double[][][])null;
    private double[][][] maxima = (double[][][])null;
    private BigDecimal[][][] minimaBD = (BigDecimal[][][])null;
    private BigDecimal[][][] maximaBD = (BigDecimal[][][])null;
    private int[] nMin = null;
    private int[] nMax = null;
    private boolean[] minimaCalc = null;
    private boolean[] maximaCalc = null;
    private CubicSpline csSavGol = null;
    private CubicSpline csMovAv = null;

    public CurveSmooth(double[] var1, double[] var2) {
        this.xData = var1;
        this.yData = var2;
        this.check();
        this.ascend();
    }

    public CurveSmooth(double[] var1) {
        int var2 = var1.length;
        this.yData = var1;
        this.xData = new double[var2];

        for(int var3 = 0; var3 < var2; ++var3) {
            this.xData[var3] = (double)var3;
        }

        this.check();
    }

    public CurveSmooth(float[] var1, float[] var2) {
        ArrayMaths var3 = new ArrayMaths(var1);
        this.xData = var3.array();
        var3 = new ArrayMaths(var2);
        this.yData = var3.array();
        this.check();
        this.ascend();
    }

    public CurveSmooth(float[] var1) {
        int var2 = var1.length;
        this.yData = new double[var2];
        this.xData = new double[var2];

        for(int var3 = 0; var3 < var2; ++var3) {
            this.xData[var3] = (double)var3;
            this.yData[var3] = (double)var1[var3];
        }

        this.check();
    }

    public CurveSmooth(long[] var1, long[] var2) {
        ArrayMaths var3 = new ArrayMaths(var1);
        this.xData = var3.array();
        var3 = new ArrayMaths(var2);
        this.yData = var3.array();
        this.check();
        this.ascend();
    }

    public CurveSmooth(long[] var1) {
        int var2 = var1.length;
        this.yData = new double[var2];
        this.xData = new double[var2];

        for(int var3 = 0; var3 < var2; ++var3) {
            this.xData[var3] = (double)var3;
            this.yData[var3] = (double)var1[var3];
        }

        this.check();
    }

    public CurveSmooth(int[] var1, int[] var2) {
        ArrayMaths var3 = new ArrayMaths(var1);
        this.xData = var3.array();
        var3 = new ArrayMaths(var2);
        this.yData = var3.array();
        this.check();
        this.ascend();
    }

    public CurveSmooth(int[] var1) {
        int var2 = var1.length;
        this.yData = new double[var2];
        this.xData = new double[var2];

        for(int var3 = 0; var3 < var2; ++var3) {
            this.xData[var3] = (double)var3;
            this.yData[var3] = (double)var1[var3];
        }

        this.check();
    }

    public CurveSmooth(BigDecimal[] var1, BigDecimal[] var2) {
        this.arbprec = true;
        this.xBDdata = var1;
        this.yBDdata = var2;
        ArrayMaths var3 = new ArrayMaths(var1);
        this.xData = var3.array();
        var3 = new ArrayMaths(var2);
        this.yData = var3.array();
        this.check();
        this.ascend();
    }

    public CurveSmooth(BigDecimal[] var1) {
        this.arbprec = true;
        int var2 = var1.length;
        this.yData = new double[var2];
        this.xData = new double[var2];
        this.yBDdata = new BigDecimal[var2];
        this.xBDdata = new BigDecimal[var2];

        for(int var3 = 0; var3 < var2; ++var3) {
            this.xData[var3] = (double)var3;
            this.yData[var3] = var1[var3].doubleValue();
            String var4 = (new Integer(var3)).toString();
            this.xBDdata[var3] = new BigDecimal(var4);
            this.yBDdata[var3] = var1[var3];
        }

        this.check();
    }

    public CurveSmooth(BigInteger[] var1, BigInteger[] var2) {
        this.arbprec = true;
        int var3 = var1.length;
        this.xBDdata = new BigDecimal[var3];
        this.yBDdata = new BigDecimal[var3];

        for(int var4 = 0; var4 < var3; ++var4) {
            this.xBDdata[var4] = new BigDecimal(var1[var4]);
            this.yBDdata[var4] = new BigDecimal(var2[var4]);
        }

        ArrayMaths var5 = new ArrayMaths(var1);
        this.xData = var5.array();
        var5 = new ArrayMaths(var2);
        this.yData = var5.array();
        this.check();
        this.ascend();
    }

    public CurveSmooth(BigInteger[] var1) {
        this.arbprec = true;
        int var2 = var1.length;
        this.yData = new double[var2];
        this.xData = new double[var2];
        this.yBDdata = new BigDecimal[var2];
        this.xBDdata = new BigDecimal[var2];

        for(int var3 = 0; var3 < var2; ++var3) {
            this.xData[var3] = (double)var3;
            this.yData[var3] = var1[var3].doubleValue();
            String var4 = (new Double((double)var3)).toString();
            this.xBDdata[var3] = new BigDecimal(var4);
            this.yBDdata[var3] = new BigDecimal(var1[var3]);
        }

        this.check();
    }

    public CurveSmooth(ArrayMaths var1, ArrayMaths var2) {
        this.xData = var1.array();
        this.yData = var2.array();
        String[] var3 = var2.originalArrayTypes();
        int var4 = 0;
        int var5 = var3.length;

        for(int var6 = 0; var6 < var3.length; ++var6) {
            if (var3[var6].equals("BigDecimal") || var3[var6].equals("BigInteger")) {
                ++var4;
            }
        }

        if (var4 == var5) {
            this.arbprec = true;
            this.yBDdata = var2.array_as_BigDecimal();
        }

        this.xData = new double[var5];
        this.xBDdata = var1.array_as_BigDecimal();
        this.check();
        this.ascend();
    }

    public CurveSmooth(ArrayMaths var1) {
        this.yData = var1.array();
        String[] var2 = var1.originalArrayTypes();
        int var3 = 0;
        int var4 = var2.length;

        int var5;
        for(var5 = 0; var5 < var2.length; ++var5) {
            if (var2[var5].equals("BigDecimal") || var2[var5].equals("BigInteger")) {
                ++var3;
            }
        }

        if (var3 == var4) {
            this.arbprec = true;
            this.yBDdata = var1.array_as_BigDecimal();
        }

        this.xData = new double[var4];
        if (this.arbprec) {
            this.xBDdata = new BigDecimal[var4];
        }

        for(var5 = 0; var5 < var4; ++var5) {
            this.xData[var5] = (double)var5;
            if (this.arbprec) {
                this.xBDdata[var5] = new BigDecimal((new Integer(var5)).toString());
            }
        }

        this.check();
    }

    private CurveSmooth() {
    }

    private void check() {
        this.nPoints = this.xData.length;
        int var1 = this.yData.length;
        if (var1 != this.nPoints) {
            throw new IllegalArgumentException("The length of the x data array, " + this.nPoints + ", must be the same as the length of the y data array, " + var1);
        } else if (var1 < 5) {
            throw new IllegalArgumentException("There must be at least five data points");
        } else {
            this.minima = new double[this.nMethods + 1][2][];
            this.maxima = new double[this.nMethods + 1][2][];
            this.minimaBD = new BigDecimal[this.nMethods + 1][2][];
            this.maximaBD = new BigDecimal[this.nMethods + 1][2][];
            this.nMin = new int[this.nMethods + 1];
            this.nMax = new int[this.nMethods + 1];
            this.minimaCalc = new boolean[this.nMethods + 1];
            this.maximaCalc = new boolean[this.nMethods + 1];

            int var2;
            for(var2 = 0; var2 < this.nMethods; ++var2) {
                this.minimaCalc[var2] = false;
                this.maximaCalc[var2] = false;
            }

            if (!this.arbprec) {
                this.xBDdata = new BigDecimal[this.nPoints];

                for(var2 = 0; var2 < this.nPoints; ++var2) {
                    this.xBDdata[var2] = new BigDecimal((new Double(this.xData[var2])).toString());
                }
            }

        }
    }

    private void ascend() {
        boolean var1 = true;
        boolean var2 = true;
        int var3 = 1;

        while(var1) {
            if (this.xData[var3] < this.xData[var3 - 1]) {
                var1 = false;
                var2 = false;
            } else {
                ++var3;
                if (var3 >= this.nPoints) {
                    var1 = false;
                }
            }
        }

        if (!var2) {
            ArrayMaths var4 = new ArrayMaths(this.xData);
            var4 = var4.sort();
            int[] var5 = var4.originalIndices();
            double[] var6 = new double[this.nPoints];
            double[] var7 = new double[this.nPoints];
            BigDecimal[] var8 = new BigDecimal[this.nPoints];
            BigDecimal[] var9 = null;
            if (this.arbprec) {
                var9 = new BigDecimal[this.nPoints];
            }

            int var10;
            for(var10 = 0; var10 < this.nPoints; ++var10) {
                var6[var10] = this.xData[var5[var10]];
                var7[var10] = this.yData[var5[var10]];
                var8[var10] = this.xBDdata[var5[var10]];
                if (this.arbprec) {
                    var9[var10] = this.yBDdata[var5[var10]];
                }
            }

            for(var10 = 0; var10 < this.nPoints; ++var10) {
                this.xData[var10] = var6[var10];
                this.yData[var10] = var7[var10];
                this.xBDdata[var10] = var8[var10];
                if (this.arbprec) {
                    this.yBDdata[var10] = var9[var10];
                }
            }
        }

    }

    public double[] movingAverage(int var1) {
        this.lastMethod = 1;
        this.lastPlotMethod = 1;
        this.yDataMovAv = new double[this.nPoints];
        this.yDataMovAvBD = new BigDecimal[this.nPoints];
        int var2 = this.windowHalf(var1);
        this.maWindowWidth = 2 * var2 + 1;
        boolean var3 = false;
        boolean var4 = false;

        for(int var5 = 0; var5 < this.nPoints; ++var5) {
            int var10;
            if (var5 >= var2) {
                var10 = var5 - var2;
            } else {
                var10 = 0;
            }

            int var11;
            if (var5 <= this.nPoints - var2 - 1) {
                var11 = var5 + var2;
            } else {
                var11 = this.nPoints - 1;
            }

            int var6 = var11 - var10 + 1;
            if (this.arbprec) {
                BigDecimal var12 = new BigDecimal("0.0");

                for(int var8 = var10; var8 <= var11; ++var8) {
                    var12 = var12.add(this.yBDdata[var8]);
                }

                String var13 = (new Integer(var6)).toString();
                this.yDataMovAvBD[var5] = var12.divide(new BigDecimal(var13), 4);
                this.yDataMovAv[var5] = this.yDataMovAvBD[var5].doubleValue();
            } else {
                double var7 = 0.0D;

                for(int var9 = var10; var9 <= var11; ++var9) {
                    var7 += this.yData[var9];
                }

                this.yDataMovAv[var5] = var7 / (double)var6;
                String var14 = (new Double(this.yDataMovAv[var5])).toString();
                this.yDataMovAvBD[var5] = new BigDecimal(var14);
            }
        }

        this.csMovAv = new CubicSpline(this.xData, this.yDataMovAv);
        this.calcMovAv = true;
        return Conv.copy(this.yDataMovAv);
    }

    public double[] movingAveragePlot(int var1) {
        double[] var2 = this.movingAverage(var1);
        this.plot();
        return var2;
    }

    public BigDecimal[] movingAverageAsBigDecimal(int var1) {
        this.movingAverage(var1);
        return Conv.copy(this.yDataMovAvBD);
    }

    public BigDecimal[] movingAverageAsBigDecimalPlot(int var1) {
        this.movingAverageAsBigDecimal(var1);
        this.plot();
        return Conv.copy(this.yDataMovAvBD);
    }

    private int windowHalf(int var1) {
        boolean var2 = false;
        int var3;
        if (Fmath.isEven(var1)) {
            var3 = var1 / 2;
        } else {
            var3 = (var1 - 1) / 2;
        }

        return var3;
    }

    private int windowLength(int var1) {
        boolean var2 = false;
        int var3;
        if (Fmath.isEven(var1)) {
            var3 = var1 + 1;
        } else {
            var3 = var1;
        }

        return var3;
    }

    public double[] savitzkyGolay(int var1) {
        this.lastMethod = 2;
        this.lastPlotMethod = 2;
        this.yDataSavGol = new double[this.nPoints];
        this.sgWindowWidth = this.windowLength(var1);
        this.savitzkyGolayCommon(this.sgWindowWidth);
        this.csSavGol = new CubicSpline(this.xData, Conv.copy(this.yDataSavGol));
        this.calcSavGol = true;
        return Conv.copy(this.yDataSavGol);
    }

    private double[] savitzkyGolayCommon(int var1) {
        int var2 = (var1 - 1) / 2;
        double[] var3 = this.savitzkyGolayFilter(var2, var2)[0];
        double[] var4 = this.padData(this.yData, var2);

        for(int var5 = var2; var5 < this.nPoints + var2; ++var5) {
            double var6 = 0.0D;
            int var8 = 0;

            for(int var9 = var5 - var2; var9 <= var5 + var2; ++var9) {
                var6 += var4[var9] * var3[var8++];
            }

            this.yDataSavGol[var5 - var2] = var6;
        }

        return this.yDataSavGol;
    }

    private double[] padData(double[] var1, int var2) {
        int var3 = var1.length;
        double[] var4 = new double[var3 + 2 * var2];

        int var5;
        for(var5 = 0; var5 < var3; ++var5) {
            var4[var5 + var2] = var1[var5];
        }

        for(var5 = 0; var5 < var2; ++var5) {
            var4[var5] = var4[var2];
        }

        for(var5 = var3 + var2; var5 < var3 + 2 * var2; ++var5) {
            var4[var5] = var4[var3 + var2 - 1];
        }

        return var4;
    }

    public double[][] savitzkyGolayFilter(int var1, int var2) {
        int var3 = var1 + var2 + 1;
        double[] var4 = new double[var3];
        int[] var5 = new int[var3];

        for(int var6 = 0; var6 < var3; ++var6) {
            var5[var6] = var6 - var1;
        }

        double[][] var12 = new double[var3][this.sgPolyDeg + 1];

        for(int var7 = 0; var7 < var3; ++var7) {
            for(int var8 = 0; var8 < this.sgPolyDeg + 1; ++var8) {
                var12[var7][var8] = Math.pow((double)var5[var7], (double)var8);
            }
        }

        Matrix var13 = new Matrix(var12);
        Matrix var14 = var13.transpose();
        Matrix var9 = var14.times(var13);
        Matrix var10 = var9.inverse();
        Matrix var11 = var10.times(var14);
        this.sgArrayC = var11.getArrayCopy();
        this.sgCoeff = this.sgArrayC[0];
        return this.sgArrayC;
    }

    public double[] savitzkyGolayPlot(int var1) {
        double[] var2 = this.savitzkyGolay(var1);
        this.plot();
        return var2;
    }

    public double[][] savitzkyGolayPlusFirstDeriv(int var1) {
        if (1 > this.sgPolyDeg) {
            throw new IllegalArgumentException("The derivative order, 1, must be less than or equal to the polynomial degree, " + this.sgPolyDeg + ".");
        } else {
            this.lastMethod = 2;
            this.lastPlotMethod = 3;
            double[][] var2 = this.savitzkyGolay(var1, 1);
            this.yDataSavGolFirst = var2[1];
            if (!this.nthSet) {
                this.yDataSavGolNth = var2[1];
            }

            this.nthSet = true;
            this.firstDeriv = true;
            return var2;
        }
    }

    public double[][] savitzkyGolayPlusFirstDerivPlot(int var1) {
        double[][] var2 = this.savitzkyGolayPlusFirstDeriv(var1);
        this.plot();
        this.firstDeriv = true;
        return var2;
    }

    public double[][] savitzkyGolayPlusSecondDeriv(int var1) {
        if (2 > this.sgPolyDeg) {
            throw new IllegalArgumentException("The derivative order, 2, must be less than or equal to the polynomial degree, " + this.sgPolyDeg + ".");
        } else {
            this.lastMethod = 2;
            this.lastPlotMethod = 4;
            double[][] var2 = this.savitzkyGolay(var1, 2);
            this.yDataSavGolSecond = var2[1];
            if (!this.nthSet) {
                this.yDataSavGolNth = var2[1];
            }

            this.nthSet = true;
            this.secondDeriv = true;
            return var2;
        }
    }

    public double[][] savitzkyGolayPlusSecondDerivPlot(int var1) {
        double[][] var2 = this.savitzkyGolayPlusSecondDeriv(var1);
        this.plot();
        this.secondDeriv = true;
        return var2;
    }

    public double[][] savitzkyGolay(int var1, int var2) {
        if (var2 > this.sgPolyDeg) {
            throw new IllegalArgumentException("The  derivative order " + var2 + ", must be less than or equal to the polynomial degree, " + this.sgPolyDeg + ".");
        } else {
            this.sgDerivOrderUsed = var2;
            double[][] var3 = new double[2][this.nPoints];
            this.sgWindowWidth = this.windowLength(var1);
            int var4 = (this.sgWindowWidth - 1) / 2;
            if (!this.calcSavGol) {
                this.savitzkyGolay(var1);
            }

            var3[0] = this.yDataSavGol;
            double[] var5 = this.sgArrayC[var2];
            double[] var6 = this.padData(this.yData, var4);

            for(int var7 = var4; var7 < this.nPoints + var4; ++var7) {
                double var8 = 0.0D;
                int var10 = 0;

                for(int var11 = var7 - var4; var11 <= var7 + var4; ++var11) {
                    var8 += var6[var11] * var5[var10++];
                }

                var3[1][var7 - var4] = var8;
            }

            this.derivSavGol = var3[1];
            if (var2 == 1) {
                this.yDataSavGolFirst = var3[1];
                this.firstDeriv = true;
            }

            if (var2 == 2) {
                this.yDataSavGolSecond = var3[1];
                this.secondDeriv = true;
            }

            this.nthSet = true;
            this.lastMethod = 2;
            this.lastPlotMethod = 5;
            if (var2 == 1) {
                this.lastPlotMethod = 3;
            }

            if (var2 == 2) {
                this.lastPlotMethod = 4;
            }

            return var3;
        }
    }

    public double[][] savitzkyGolayPlot(int var1, int var2) {
        double[][] var3 = this.savitzkyGolay(var1, var2);
        this.plot();
        return var3;
    }

    public static double[][] savitzkyGolayFilter(int var0, int var1, int var2) {
        CurveSmooth var3 = new CurveSmooth();
        var3.setSGpolyDegree(var2);
        return var3.savitzkyGolayFilter(var0, var1);
    }

    public static double[] savitzkyGolayFilter(int var0, int var1, int var2, int var3) {
        CurveSmooth var4 = new CurveSmooth();
        var4.setSGpolyDegree(var2);
        return var4.savitzkyGolayFilter(var0, var1)[var3];
    }

    public double[] getSGcoefficientsUsed() {
        if (this.sgCoeff == null) {
            throw new IllegalArgumentException("No Savitzky-Golay coefficients have been calculated");
        } else {
            return this.sgCoeff;
        }
    }

    public double[][] getSGcoefficients() {
        if (this.sgArrayC == null) {
            throw new IllegalArgumentException("No Savitzky-Golay coefficients have been calculated");
        } else {
            return this.sgArrayC;
        }
    }

    public void setSGpolyDegree(int var1) {
        this.sgPolyDeg = var1;
    }

    public int getSGpolyDegree() {
        return this.sgPolyDeg;
    }

    private void plot() {
        double[][] var1 = (double[][])null;
        String var2 = null;
        String[] var3 = new String[]{"  ", "first", "second", "third", "fourth", "th"};
        System.out.println("lm " + this.lastPlotMethod);
        switch(this.lastPlotMethod) {
            case 1:
                var1 = new double[8][];
                var1[3] = this.yDataMovAv;
                var2 = "Moving average of " + this.maWindowWidth + " points";
                break;
            case 2:
                var1 = new double[8][];
                var1[3] = this.yDataSavGol;
                var2 = "Savitzky-Golay filter with a window of " + this.sgWindowWidth + " points";
                break;
            case 3:
                var1 = new double[10][];
                var1[3] = this.yDataSavGol;
                var1[9] = this.yDataSavGolFirst;
                var2 = "Savitzky-Golay filter with a window of " + this.sgWindowWidth + " points  plus smoothed first derivative";
                break;
            case 4:
                var1 = new double[10][];
                var1[3] = this.yDataSavGol;
                var1[9] = this.yDataSavGolSecond;
                var2 = "Savitzky-Golay filter with a window of " + this.sgWindowWidth + " points  plus smoothed second derivative";
                break;
            case 5:
                var1 = new double[10][];
                var1[3] = this.yDataSavGol;
                var1[9] = this.yDataSavGolNth;
                if (this.sgDerivOrderUsed < 5) {
                    var2 = "Savitzky-Golay filter with a window of " + this.sgWindowWidth + " points  plus smoothed " + var3[this.sgDerivOrderUsed] + " derivative";
                } else {
                    var2 = "Savitzky-Golay filter with a window of " + this.sgWindowWidth + " points  plus smoothed " + this.sgDerivOrderUsed + "th derivative";
                }
        }

        var1[0] = this.xData;
        var1[1] = this.yData;
        var1[2] = this.xData;
        double[] var4 = new double[5];
        double[] var5 = new double[5];
        double[] var6 = new double[5];
        double[] var7 = new double[5];
        var4[0] = this.xData[0];
        var4[1] = this.xData[this.nPoints / 4];
        var4[2] = this.xData[this.nPoints / 2];
        var4[3] = this.xData[3 * this.nPoints / 4];
        var4[4] = this.xData[this.nPoints - 1];
        var6[0] = this.yData[0];
        var6[1] = this.yData[this.nPoints / 4];
        var6[2] = this.yData[this.nPoints / 2];
        var6[3] = this.yData[3 * this.nPoints / 4];
        var6[4] = this.yData[this.nPoints - 1];
        var5[0] = this.xData[0];
        var5[1] = this.xData[this.nPoints / 4 - 1];
        var5[2] = this.xData[this.nPoints / 2 - 1];
        var5[3] = this.xData[3 * this.nPoints / 4 - 1];
        var5[4] = this.xData[this.nPoints - 1];
        var7[0] = var1[3][0];
        var7[1] = var1[3][this.nPoints / 4 - 1];
        var7[2] = var1[3][this.nPoints / 2 - 1];
        var7[3] = var1[3][3 * this.nPoints / 4 - 1];
        var7[4] = var1[3][this.nPoints - 1];
        var1[4] = var4;
        var1[5] = var6;
        var1[6] = var5;
        var1[7] = var7;
        int[] var8 = new int[]{0, 0, 1, 2};
        int[] var9 = new int[]{3, 3, 0, 0};
        int[] var10 = var8;
        int[] var11 = var9;
        if (this.lastPlotMethod > 2) {
            var1[8] = this.xData;
            var10 = new int[5];
            var11 = new int[5];
            var10[4] = 0;
            var11[4] = 3;

            for(int var12 = 0; var12 < 4; ++var12) {
                var10[var12] = var8[var12];
                var11[var12] = var9[var12];
            }
        }

        var1[0] = this.xData;
        var1[1] = this.yData;
        var1[2] = this.xData;
        PlotGraph var13 = new PlotGraph(var1);
        var13.setPoint(var10);
        var13.setLine(var11);
        var13.setGraphTitle("Original (circles) and smoothed (squares) data");
        var13.setGraphTitle2(var2);
        var13.setXaxisLegend("x values");
        var13.setYaxisLegend("y values");
        var13.plot();
    }

    public double[] getSmoothedValues() {
        double[] var1 = null;
        switch(this.lastMethod) {
            case 0:
                throw new IllegalArgumentException("No smoothing method has been called");
            case 1:
                var1 = this.yDataMovAv;
                break;
            case 2:
                var1 = this.yDataSavGol;
        }

        return Conv.copy(var1);
    }

    public double[] getMovingAverageValues() {
        if (!this.calcMovAv) {
            throw new IllegalArgumentException("No moving average smoothing method has been called");
        } else {
            return Conv.copy(this.yDataMovAv);
        }
    }

    public BigDecimal[] getMovingAverageValuesAsBigDecimal() {
        if (!this.calcMovAv) {
            throw new IllegalArgumentException("No moving average smoothing method has been called");
        } else {
            return Conv.copy(this.yDataMovAvBD);
        }
    }

    public double[] getSavitzkyGolaySmoothedValues() {
        if (!this.calcSavGol) {
            throw new IllegalArgumentException("No Savitzky-Golay smoothing method has been called");
        } else {
            return Conv.copy(this.yDataSavGol);
        }
    }

    public double[] getSavitzkyGolayFirstDerivatives() {
        return this.getSavitzkyGolaySmoothedFirstDerivValues();
    }

    public double[] getSavitzkyGolaySmoothedFirstDerivValues() {
        if (!this.firstDeriv) {
            throw new IllegalArgumentException("No Savitzky-Golay first derivative smoothing method has been called");
        } else {
            return Conv.copy(this.yDataSavGolFirst);
        }
    }

    public double[] getSavitzkyGolaySecondDerivatives() {
        return this.getSavitzkyGolaySmoothedSecondDerivValues();
    }

    public double[] getSavitzkyGolaySmoothedSecondDerivValues() {
        if (!this.secondDeriv) {
            throw new IllegalArgumentException("No Savitzky-Golay second derivative smoothing method has been called");
        } else {
            return Conv.copy(this.yDataSavGolSecond);
        }
    }

    public double[] getSavitzkyGolayNthDerivatives() {
        return this.getSavitzkyGolaySmoothedNthDerivValues();
    }

    public double[] getSavitzkyGolaySmoothedNthDerivValues() {
        if (!this.nthSet) {
            throw new IllegalArgumentException("No Savitzky-Golay derivative smoothing method has been called");
        } else {
            return Conv.copy(this.yDataSavGolNth);
        }
    }

    public double[] getSavitzkyGolayDerivatives() {
        if (!this.nthSet) {
            throw new IllegalArgumentException("No Savitzky-Golay derivative smoothing method has been called");
        } else {
            return Conv.copy(this.yDataSavGolNth);
        }
    }

    public double[] getSavitzkyGolayNthDerivatives(int var1) {
        return this.getSavitzkyGolaySmoothedNthDerivValues(var1);
    }

    public double[] getSavitzkyGolayDerivatives(int var1) {
        return this.getSavitzkyGolaySmoothedNthDerivValues(var1);
    }

    public double[] getSavitzkyGolaySmoothedNthDerivValues(int var1) {
        if (!this.nthSet) {
            throw new IllegalArgumentException("No Savitzky-Golay derivative smoothing method has been called");
        } else {
            Object var2 = null;
            double[] var3;
            switch(var1) {
                case 0:
                    var3 = this.yDataSavGol;
                    break;
                case 1:
                    if (!this.firstDeriv) {
                        throw new IllegalArgumentException("No Savitzky-Golay first derivative smoothing method has been called");
                    }

                    var3 = this.yDataSavGolFirst;
                    break;
                case 2:
                    if (!this.secondDeriv) {
                        throw new IllegalArgumentException("No Savitzky-Golay second derivative smoothing method has been called");
                    }

                    var3 = this.yDataSavGolSecond;
                    break;
                default:
                    var3 = this.yDataSavGolNth;
            }

            return Conv.copy(var3);
        }
    }

    public double extentMovingAverage() {
        if (!this.calcMovAv) {
            throw new IllegalArgumentException("No moving average smoothing method has been called");
        } else {
            this.extentMovAv = this.extent(this.yData, this.yDataMovAv);
            return this.extentMovAv;
        }
    }

    public double extentSavitzkyGolay() {
        if (!this.calcSavGol) {
            throw new IllegalArgumentException("No Savitzky-Golay smoothing method has been called");
        } else {
            this.extentSavGol = this.extent(this.yData, this.yDataSavGol);
            return this.extentSavGol;
        }
    }

    private double extent(double[] var1, double[] var2) {
        double var3 = Fmath.maximum(var1) - Fmath.minimum(var1);
        double var5 = 0.0D;

        for(int var7 = 0; var7 < this.nPoints; ++var7) {
            var5 += Math.abs(var1[var7] - var2[var7]) / var3;
        }

        var5 /= (double)this.nPoints;
        return var5;
    }

    public double extremaReductionMovingAverage() {
        if (!this.calcMovAv) {
            throw new IllegalArgumentException("No moving average smoothing method has been called");
        } else {
            int var1 = this.getNumberOfExtremaUnsmoothed();
            int var2 = this.getNumberOfExtremaMovingAverage();
            return (double)var2 / (double)var1;
        }
    }

    public double extremaReductionSavitzkyGolay() {
        if (!this.calcSavGol) {
            throw new IllegalArgumentException("No Savitzky-Golay smoothing method has been called");
        } else {
            int var1 = this.getNumberOfExtremaUnsmoothed();
            int var2 = this.getNumberOfExtremaSavitzkyGolay();
            return (double)var2 / (double)var1;
        }
    }

    public double interpolateSavitzkyGolay(double var1) {
        if (!this.calcSavGol) {
            throw new IllegalArgumentException("No Savitzky-Golay smoothing method has been called");
        } else {
            return this.csSavGol.interpolate(var1);
        }
    }

    public double interpolateMovingAverage(double var1) {
        if (!this.calcMovAv) {
            throw new IllegalArgumentException("No moving average smoothing method has been called");
        } else {
            return this.csMovAv.interpolate(var1);
        }
    }

    public double[][] getMinimaUnsmoothed() {
        if (!this.minimaCalc[0]) {
            if (this.arbprec) {
                this.findMinAndMaxBD(this.xBDdata, this.yBDdata, 0);
            } else {
                this.findMinAndMax(this.xData, this.yData, 0);
            }
        }

        double[][] var1 = new double[2][this.nMin[0]];
        var1[0] = Conv.copy(this.minima[0][0]);
        var1[1] = Conv.copy(this.minima[0][1]);
        return var1;
    }

    public BigDecimal[][] getMinimaUnsmoothedAsBigDecimal() {
        if (!this.minimaCalc[0]) {
            if (this.arbprec) {
                this.findMinAndMaxBD(this.xBDdata, this.yBDdata, 0);
            } else {
                this.findMinAndMax(this.xData, this.yData, 0);
            }
        }

        BigDecimal[][] var1 = new BigDecimal[2][this.nMin[0]];
        var1[0] = Conv.copy(this.minimaBD[0][0]);
        var1[1] = Conv.copy(this.minimaBD[0][1]);
        return var1;
    }

    public double[][] getMaximaUnsmoothed() {
        if (!this.maximaCalc[0]) {
            if (this.arbprec) {
                this.findMinAndMaxBD(this.xBDdata, this.yBDdata, 0);
            } else {
                this.findMinAndMax(this.xData, this.yData, 0);
            }
        }

        double[][] var1 = new double[2][this.nMax[0]];
        var1[0] = Conv.copy(this.maxima[0][0]);
        var1[1] = Conv.copy(this.maxima[0][1]);
        return var1;
    }

    public BigDecimal[][] getMaximaUnsmoothedAsBigDecimal() {
        if (!this.maximaCalc[0]) {
            if (this.arbprec) {
                this.findMinAndMaxBD(this.xBDdata, this.yBDdata, 0);
            } else {
                this.findMinAndMax(this.xData, this.yData, 0);
            }
        }

        BigDecimal[][] var1 = new BigDecimal[2][this.nMax[0]];
        var1[0] = Conv.copy(this.maximaBD[0][0]);
        var1[1] = Conv.copy(this.maximaBD[0][1]);
        return var1;
    }

    public int getNumberOfMinimaUnsmoothed() {
        if (!this.minimaCalc[0]) {
            if (this.arbprec) {
                this.findMinAndMaxBD(this.xBDdata, this.yBDdata, 0);
            } else {
                this.findMinAndMax(this.xData, this.yData, 0);
            }
        }

        return this.nMin[0];
    }

    public int getNumberOfMaximaUnsmoothed() {
        if (!this.maximaCalc[0]) {
            if (this.arbprec) {
                this.findMinAndMaxBD(this.xBDdata, this.yBDdata, 0);
            } else {
                this.findMinAndMax(this.xData, this.yData, 0);
            }
        }

        return this.nMax[0];
    }

    public int getNumberOfExtremaUnsmoothed() {
        if (!this.minimaCalc[0]) {
            if (this.arbprec) {
                this.findMinAndMaxBD(this.xBDdata, this.yBDdata, 0);
            } else {
                this.findMinAndMax(this.xData, this.yData, 0);
            }
        }

        return this.nMin[0] + this.nMax[0];
    }

    public double[][] getMinimaMovingAverage() {
        if (!this.calcMovAv) {
            throw new IllegalArgumentException("No moving average smoothing method has been called");
        } else {
            if (!this.minimaCalc[1]) {
                if (this.arbprec) {
                    this.findMinAndMaxBD(this.xBDdata, this.yDataMovAvBD, 1);
                } else {
                    this.findMinAndMax(this.xData, this.yDataMovAv, 1);
                }
            }

            double[][] var1 = new double[2][this.nMin[1]];
            var1[0] = Conv.copy(this.minima[1][0]);
            var1[1] = Conv.copy(this.minima[1][1]);
            return var1;
        }
    }

    public BigDecimal[][] getMinimaMovingAverageAsBigDecimal() {
        if (!this.calcMovAv) {
            throw new IllegalArgumentException("No moving average smoothing method has been called");
        } else {
            if (!this.minimaCalc[1]) {
                if (this.arbprec) {
                    this.findMinAndMaxBD(this.xBDdata, this.yDataMovAvBD, 1);
                } else {
                    this.findMinAndMax(this.xData, this.yDataMovAv, 1);
                }
            }

            BigDecimal[][] var1 = new BigDecimal[2][this.nMin[1]];
            var1[0] = Conv.copy(this.minimaBD[1][0]);
            var1[1] = Conv.copy(this.minimaBD[1][1]);
            return var1;
        }
    }

    public double[][] getMaximaMovingAverage() {
        if (!this.calcMovAv) {
            throw new IllegalArgumentException("No moving average smoothing method has been called");
        } else {
            if (!this.maximaCalc[1]) {
                this.findMinAndMax(this.xData, this.yDataMovAv, 1);
            }

            double[][] var1 = new double[2][this.nMax[1]];
            var1[0] = Conv.copy(this.maxima[1][0]);
            var1[1] = Conv.copy(this.maxima[1][1]);
            return var1;
        }
    }

    public BigDecimal[][] getMaximaMovingAverageAsBigDecimal() {
        if (!this.calcMovAv) {
            throw new IllegalArgumentException("No moving average smoothing method has been called");
        } else {
            if (!this.maximaCalc[1]) {
                if (this.arbprec) {
                    this.findMinAndMaxBD(this.xBDdata, this.yDataMovAvBD, 1);
                } else {
                    this.findMinAndMax(this.xData, this.yDataMovAv, 1);
                }
            }

            BigDecimal[][] var1 = new BigDecimal[2][this.nMax[1]];
            var1[0] = Conv.copy(this.maximaBD[1][0]);
            var1[1] = Conv.copy(this.maximaBD[1][1]);
            return var1;
        }
    }

    public int getNumberOfMinimaMovingAverage() {
        if (!this.calcMovAv) {
            throw new IllegalArgumentException("No moving average smoothing method has been called");
        } else {
            if (!this.minimaCalc[1]) {
                if (this.arbprec) {
                    this.findMinAndMaxBD(this.xBDdata, this.yDataMovAvBD, 1);
                } else {
                    this.findMinAndMax(this.xData, this.yDataMovAv, 1);
                }
            }

            return this.nMin[1];
        }
    }

    public int getNumberOfMaximaMovingAverage() {
        if (!this.calcMovAv) {
            throw new IllegalArgumentException("No moving average smoothing method has been called");
        } else {
            if (!this.maximaCalc[1]) {
                if (this.arbprec) {
                    this.findMinAndMaxBD(this.xBDdata, this.yDataMovAvBD, 1);
                } else {
                    this.findMinAndMax(this.xData, this.yDataMovAv, 1);
                }
            }

            return this.nMax[1];
        }
    }

    public int getNumberOfExtremaMovingAverage() {
        if (!this.calcMovAv) {
            throw new IllegalArgumentException("No moving average smoothing method has been called");
        } else {
            if (!this.minimaCalc[1]) {
                if (this.arbprec) {
                    this.findMinAndMaxBD(this.xBDdata, this.yDataMovAvBD, 1);
                } else {
                    this.findMinAndMax(this.xData, this.yDataMovAv, 1);
                }
            }

            return this.nMin[1] + this.nMax[1];
        }
    }

    public double[][] getMinimaSavitzkyGolay() {
        if (!this.calcSavGol) {
            throw new IllegalArgumentException("No Savitzky-Golay smoothing method has been called");
        } else {
            if (!this.minimaCalc[1]) {
                this.findMinAndMax(this.xData, this.yDataSavGol, 2);
            }

            double[][] var1 = new double[2][this.nMin[2]];
            var1[0] = Conv.copy(this.minima[2][0]);
            var1[1] = Conv.copy(this.minima[2][1]);
            return var1;
        }
    }

    public double[][] getMaximaSavitzkyGolay() {
        if (!this.calcSavGol) {
            throw new IllegalArgumentException("No Savitzky-Golay smoothing method has been called");
        } else {
            if (!this.minimaCalc[1]) {
                this.findMinAndMax(this.xData, this.yDataSavGol, 2);
            }

            double[][] var1 = new double[2][this.nMax[2]];
            var1[0] = Conv.copy(this.maxima[2][0]);
            var1[1] = Conv.copy(this.maxima[2][1]);
            return var1;
        }
    }

    public int getNumberOfMinimaSavitzkyGolay() {
        if (!this.calcSavGol) {
            throw new IllegalArgumentException("No Savitzky-Golay smoothing method has been called");
        } else {
            if (!this.minimaCalc[2]) {
                this.findMinAndMax(this.xData, this.yDataSavGol, 2);
            }

            return this.nMin[2];
        }
    }

    public int getNumberOfMaximaSavitzkyGolay() {
        if (!this.calcSavGol) {
            throw new IllegalArgumentException("No Savitzky-Golay smoothing method has been called");
        } else {
            if (!this.maximaCalc[2]) {
                this.findMinAndMax(this.xData, this.yDataSavGol, 2);
            }

            return this.nMax[2];
        }
    }

    public int getNumberOfExtremaSavitzkyGolay() {
        if (!this.calcSavGol) {
            throw new IllegalArgumentException("No Savitzky-Golay smoothing method has been called");
        } else {
            if (!this.maximaCalc[2]) {
                this.findMinAndMax(this.xData, this.yDataSavGol, 2);
            }

            return this.nMin[2] + this.nMax[2];
        }
    }

    private void findMinAndMax(double[] var1, double[] var2, int var3) {
        boolean var4 = false;
        int var5 = 0;
        this.almin.clear();
        this.almax.clear();
        int var6 = 0;
        int var7 = 0;

        for(int var8 = 2; var8 < this.nPoints - 1; ++var8) {
            if (var4) {
                if (var2[var8] < var2[var8 - 1]) {
                    var4 = false;
                    if (var2[var8] < var2[var8 + 1]) {
                        ++var6;
                        this.almin.add(var1[var8]);
                        this.almin.add(var2[var8]);
                    } else if (var2[var8] == var2[var8 + 1]) {
                        var4 = true;
                        var5 = var8;
                    }
                } else if (var2[var8] == var2[var8 - 1]) {
                    if (var2[var8] < var2[var8 + 1]) {
                        var4 = false;
                        ++var6;
                        double var9 = (var1[var8] + var1[var5]) / 2.0D;
                        this.almin.add(var9);
                        this.almin.add(var2[var8]);
                    } else if (var2[var8] > var2[var8 + 1]) {
                        var4 = false;
                    }
                }
            } else if (var2[var8] < var2[var8 - 1] && var2[var8] < var2[var8 + 1]) {
                ++var6;
                this.almin.add(var1[var8]);
                this.almin.add(var2[var8]);
            } else if (var2[var8] < var2[var8 - 1] && var2[var8] == var2[var8 + 1]) {
                var4 = true;
                var5 = var8;
            }
        }

        this.nMin[var3] = var6;
        double[] var16 = new double[var6];
        double[] var17 = new double[var6];
        BigDecimal[] var10 = new BigDecimal[var6];
        BigDecimal[] var11 = new BigDecimal[var6];
        int var12 = 0;

        int var13;
        String var14;
        String var15;
        for(var13 = 0; var13 < var6; ++var13) {
            var16[var13] = (Double)this.almin.get(var12++);
            var17[var13] = (Double)this.almin.get(var12++);
            var14 = (new Double(var16[var13])).toString();
            var10[var13] = new BigDecimal(var14);
            var15 = (new Double(var17[var13])).toString();
            var11[var13] = new BigDecimal(var15);
        }

        this.minima[var3][0] = var16;
        this.minima[var3][1] = var17;
        this.minimaBD[var3][0] = var10;
        this.minimaBD[var3][1] = var11;
        this.minimaCalc[var3] = true;
        var4 = false;
        var5 = 0;

        for(var13 = 2; var13 < this.nPoints - 1; ++var13) {
            if (var4) {
                if (var2[var13] > var2[var13 - 1]) {
                    var4 = false;
                    if (var2[var13] > var2[var13 + 1]) {
                        ++var7;
                        this.almax.add(var1[var13]);
                        this.almax.add(var2[var13]);
                    } else if (var2[var13] == var2[var13 + 1]) {
                        var4 = true;
                        var5 = var13;
                    }
                } else if (var2[var13] == var2[var13 - 1]) {
                    if (var2[var13] > var2[var13 + 1]) {
                        var4 = false;
                        ++var7;
                        double var18 = (var1[var13] + var1[var5]) / 2.0D;
                        this.almax.add(var18);
                        this.almax.add(var2[var13]);
                    } else if (var2[var13] < var2[var13 + 1]) {
                        var4 = false;
                    }
                }
            } else if (var2[var13] > var2[var13 - 1] && var2[var13] > var2[var13 + 1]) {
                ++var7;
                this.almax.add(var1[var13]);
                this.almax.add(var2[var13]);
            } else if (var2[var13] > var2[var13 - 1] && var2[var13] == var2[var13 + 1]) {
                var4 = true;
                var5 = var13;
            }
        }

        this.nMax[var3] = var7;
        var16 = new double[var7];
        var17 = new double[var7];
        var10 = new BigDecimal[var7];
        var11 = new BigDecimal[var7];
        var12 = 0;

        for(var13 = 0; var13 < var7; ++var13) {
            var16[var13] = (Double)this.almax.get(var12++);
            var17[var13] = (Double)this.almax.get(var12++);
            var14 = (new Double(var16[var13])).toString();
            var10[var13] = new BigDecimal(var14);
            var15 = (new Double(var17[var13])).toString();
            var11[var13] = new BigDecimal(var15);
        }

        this.maxima[var3][0] = var16;
        this.maxima[var3][1] = var17;
        this.maximaBD[var3][0] = var10;
        this.maximaBD[var3][1] = var11;
        this.maximaCalc[var3] = true;
    }

    private void findMinAndMaxBD(BigDecimal[] var1, BigDecimal[] var2, int var3) {
        boolean var4 = false;
        int var5 = 0;
        this.almin.clear();
        this.almax.clear();
        int var6 = 0;
        int var7 = 0;

        for(int var8 = 2; var8 < this.nPoints - 1; ++var8) {
            if (var4) {
                if (var2[var8].compareTo(var2[var8 - 1]) == -1) {
                    var4 = false;
                    if (var2[var8].compareTo(var2[var8 + 1]) == -1) {
                        ++var6;
                        this.alminBD.add(var1[var8]);
                        this.alminBD.add(var2[var8]);
                    } else if (var2[var8] == var2[var8 + 1]) {
                        var4 = true;
                        var5 = var8;
                    }
                } else if (var2[var8].compareTo(var2[var8 - 1]) == 0) {
                    if (var2[var8].compareTo(var2[var8 + 1]) == -1) {
                        var4 = false;
                        ++var6;
                        BigDecimal var9 = var1[var8].add(var1[var5]).divide(new BigDecimal("2.0"), 4);
                        this.alminBD.add(var9);
                        this.alminBD.add(var2[var8]);
                    } else if (var2[var8].compareTo(var2[var8 + 1]) == 1) {
                        var4 = false;
                    }
                }
            } else if (var2[var8].compareTo(var2[var8 - 1]) == -1 && var2[var8].compareTo(var2[var8 + 1]) == -1) {
                ++var6;
                this.alminBD.add(var1[var8]);
                this.alminBD.add(var2[var8]);
            } else if (var2[var8].compareTo(var2[var8 - 1]) == -1 && var2[var8].compareTo(var2[var8 + 1]) == 0) {
                var4 = true;
                var5 = var8;
            }
        }

        this.nMin[var3] = var6;
        double[] var15 = new double[var6];
        double[] var16 = new double[var6];
        BigDecimal[] var10 = new BigDecimal[var6];
        BigDecimal[] var11 = new BigDecimal[var6];
        int var12 = 0;

        int var13;
        for(var13 = 0; var13 < var6; ++var13) {
            var10[var13] = (BigDecimal)this.alminBD.get(var12++);
            var11[var13] = (BigDecimal)this.alminBD.get(var12++);
            var15[var13] = var10[var13].doubleValue();
            var16[var13] = var11[var13].doubleValue();
        }

        this.minima[var3][0] = var15;
        this.minima[var3][1] = var16;
        this.minimaBD[var3][0] = var10;
        this.minimaBD[var3][1] = var11;
        this.minimaCalc[var3] = true;
        var4 = false;
        var5 = 0;

        for(var13 = 2; var13 < this.nPoints - 1; ++var13) {
            if (var4) {
                if (var2[var13].compareTo(var2[var13 - 1]) == 1) {
                    var4 = false;
                    if (var2[var13].compareTo(var2[var13 + 1]) == 1) {
                        ++var7;
                        this.almaxBD.add(var1[var13]);
                        this.almaxBD.add(var2[var13]);
                    } else if (var2[var13].compareTo(var2[var13 + 1]) == 0) {
                        var4 = true;
                        var5 = var13;
                    }
                } else if (var2[var13] == var2[var13 - 1]) {
                    if (var2[var13].compareTo(var2[var13 + 1]) == 1) {
                        var4 = false;
                        ++var7;
                        BigDecimal var14 = var1[var13].add(var1[var5]).divide(new BigDecimal("2.0"), 4);
                        this.almaxBD.add(var14);
                        this.almaxBD.add(var2[var13]);
                    } else if (var2[var13].compareTo(var2[var13 + 1]) == -1) {
                        var4 = false;
                    }
                }
            } else if (var2[var13].compareTo(var2[var13 - 1]) == 1 && var2[var13].compareTo(var2[var13 + 1]) == 1) {
                ++var7;
                this.almaxBD.add(var1[var13]);
                this.almaxBD.add(var2[var13]);
            } else if (var2[var13].compareTo(var2[var13 - 1]) == 1 && var2[var13].compareTo(var2[var13 + 1]) == 0) {
                var4 = true;
                var5 = var13;
            }
        }

        this.nMax[var3] = var7;
        var15 = new double[var7];
        var16 = new double[var7];
        var10 = new BigDecimal[var7];
        var11 = new BigDecimal[var7];
        var12 = 0;

        for(var13 = 0; var13 < var7; ++var13) {
            var10[var13] = (BigDecimal)this.alminBD.get(var12++);
            var11[var13] = (BigDecimal)this.alminBD.get(var12++);
            var15[var13] = var10[var13].doubleValue();
            var16[var13] = var11[var13].doubleValue();
        }

        this.maxima[var3][0] = var15;
        this.maxima[var3][1] = var16;
        this.maximaBD[var3][0] = var10;
        this.maximaBD[var3][1] = var11;
        this.maximaCalc[var3] = true;
    }
}
