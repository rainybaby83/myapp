//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.analysis;

import com.flanagan.interpolation.BiCubicSpline;
import com.flanagan.math.ArrayMaths;
import com.flanagan.math.Conv;
import com.flanagan.math.Fmath;
import com.flanagan.math.Matrix;
import com.flanagan.plot.PlotGraph;
import java.math.BigDecimal;
import java.math.BigInteger;

public class SurfaceSmooth {
    private double[] xData = null;
    private double[] yData = null;
    private double[][] zData = (double[][])null;
    private BigDecimal[] xBDdata = null;
    private BigDecimal[] yBDdata = null;
    private BigDecimal[][] zBDdata = (BigDecimal[][])null;
    private int nPointsX = 0;
    private int nPointsY = 0;
    private int nPoints = 0;
    private boolean arbprec = false;
    private double[][] zDataMovAv = (double[][])null;
    private BigDecimal[][] zDataMovAvBD = (BigDecimal[][])null;
    private double[][] zDataSavGol = (double[][])null;
    private double[][] derivSavGol = (double[][])null;
    private int[][] sgCoeffIndices = (int[][])null;
    private int nSGcoeff = 0;
    private int lastMethod = 0;
    private int nMethods = 2;
    private int maWindowWidthx = 0;
    private int maWindowWidthy = 0;
    private int sgWindowWidthx = 0;
    private int sgWindowWidthy = 0;
    private int sgPolyDeg = 4;
    private double[][] sgArrayC = (double[][])null;
    private boolean calcSavGol = false;
    private boolean calcMovAv = false;
    private boolean nthSet = false;
    private double extentMovAv = -1.0D;
    private double extentSavGol = -1.0D;
    private BiCubicSpline bcsSavGol = null;
    private BiCubicSpline bcsMovAv = null;
    private int trunc = 4;

    public SurfaceSmooth(double[] var1, double[] var2, double[][] var3) {
        this.xData = var1;
        this.yData = var2;
        this.zData = var3;
        this.polyIndices();
        this.check();
        this.ascend();
    }

    public SurfaceSmooth(double[][] var1) {
        int var2 = var1.length;
        this.zData = var1;
        this.yData = new double[var2];

        int var3;
        for(var3 = 0; var3 < var2; ++var3) {
            this.yData[var3] = (double)var3;
        }

        var2 = var1[0].length;
        this.xData = new double[var2];

        for(var3 = 0; var3 < var2; ++var3) {
            this.xData[var3] = (double)var3;
        }

        this.polyIndices();
        this.check();
    }

    public SurfaceSmooth(float[] var1, float[] var2, float[][] var3) {
        ArrayMaths var4 = new ArrayMaths(var1);
        this.xData = var4.array();
        var4 = new ArrayMaths(var3);
        this.yData = var4.array();
        var4 = new ArrayMaths(var3[0]);
        this.zData[0] = var4.array();
        var4 = new ArrayMaths(var3[1]);
        this.zData[1] = var4.array();
        this.polyIndices();
        this.check();
        this.ascend();
    }

    public SurfaceSmooth(float[][] var1) {
        int var2 = var1.length;
        int var3 = var1[0].length;
        this.zData = new double[var2][var3];
        this.xData = new double[var3];
        this.yData = new double[var2];

        int var4;
        for(var4 = 0; var4 < var3; ++var4) {
            this.xData[var4] = (double)var4;
        }

        for(var4 = 0; var4 < var2; ++var4) {
            this.yData[var4] = (double)var4;
        }

        for(var4 = 0; var4 < var2; ++var4) {
            for(int var5 = 0; var5 < var3; ++var5) {
                this.zData[var4][var5] = (double)var1[var4][var5];
            }
        }

        this.polyIndices();
        this.check();
    }

    public SurfaceSmooth(long[] var1, long[] var2, long[][] var3) {
        ArrayMaths var4 = new ArrayMaths(var1);
        this.xData = var4.array();
        var4 = new ArrayMaths(var2);
        this.yData = var4.array();
        var4 = new ArrayMaths(var3[0]);
        this.zData[0] = var4.array();
        var4 = new ArrayMaths(var3[1]);
        this.zData[1] = var4.array();
        this.polyIndices();
        this.check();
        this.ascend();
    }

    public SurfaceSmooth(long[][] var1) {
        int var2 = var1.length;
        int var3 = var1[0].length;
        this.zData = new double[var2][var3];
        this.xData = new double[var3];
        this.yData = new double[var2];

        int var4;
        for(var4 = 0; var4 < var3; ++var4) {
            this.xData[var4] = (double)var4;
        }

        for(var4 = 0; var4 < var2; ++var4) {
            this.yData[var4] = (double)var4;
        }

        for(var4 = 0; var4 < var2; ++var4) {
            for(int var5 = 0; var5 < var3; ++var5) {
                this.zData[var4][var5] = (double)var1[var4][var5];
            }
        }

        this.polyIndices();
        this.check();
    }

    public SurfaceSmooth(int[] var1, int[] var2, int[][] var3) {
        ArrayMaths var4 = new ArrayMaths(var1);
        this.xData = var4.array();
        var4 = new ArrayMaths(var2);
        this.yData = var4.array();
        var4 = new ArrayMaths(var3[0]);
        this.zData[0] = var4.array();
        var4 = new ArrayMaths(var3[1]);
        this.zData[1] = var4.array();
        this.polyIndices();
        this.check();
        this.ascend();
    }

    public SurfaceSmooth(int[][] var1) {
        int var2 = var1.length;
        int var3 = var1[0].length;
        this.zData = new double[var2][var3];
        this.xData = new double[var3];
        this.yData = new double[var2];

        int var4;
        for(var4 = 0; var4 < var3; ++var4) {
            this.xData[var4] = (double)var4;
        }

        for(var4 = 0; var4 < var2; ++var4) {
            this.yData[var4] = (double)var4;
        }

        for(var4 = 0; var4 < var2; ++var4) {
            for(int var5 = 0; var5 < var3; ++var5) {
                this.zData[var4][var5] = (double)var1[var4][var5];
            }
        }

        this.polyIndices();
        this.check();
    }

    public SurfaceSmooth(BigDecimal[] var1, BigDecimal[] var2, BigDecimal[][] var3) {
        this.arbprec = true;
        this.xBDdata = var1;
        int var4 = var3.length;
        this.yBDdata = var2;
        int var5 = var3[0].length;
        ArrayMaths var6 = new ArrayMaths(var1);
        this.xData = var6.array();
        var6 = new ArrayMaths(var2);
        this.yData = var6.array();

        for(int var7 = 0; var7 < var4; ++var7) {
            for(int var8 = 0; var8 < var5; ++var8) {
                this.zData[var7][var8] = var3[var7][var8].doubleValue();
                this.zBDdata[var7][var8] = var3[var7][var8];
            }
        }

        this.polyIndices();
        this.check();
        this.ascend();
    }

    public SurfaceSmooth(BigDecimal[][] var1) {
        this.arbprec = true;
        int var2 = var1.length;
        int var3 = var1[0].length;
        this.zData = new double[var2][var3];
        this.xData = new double[var3];
        this.yData = new double[var2];
        this.zBDdata = new BigDecimal[var2][var3];
        this.xBDdata = new BigDecimal[var3];
        this.yBDdata = new BigDecimal[var2];

        int var4;
        String var5;
        for(var4 = 0; var4 < var3; ++var4) {
            this.xData[var4] = (double)var4;
            var5 = (new Integer(var4)).toString();
            this.xBDdata[var4] = new BigDecimal(var5);
        }

        for(var4 = 0; var4 < var2; ++var4) {
            this.yData[var4] = (double)var4;
            var5 = (new Integer(var4)).toString();
            this.yBDdata[var4] = new BigDecimal(var5);
        }

        for(var4 = 0; var4 < var2; ++var4) {
            for(int var6 = 0; var6 < var3; ++var6) {
                this.zData[var4][var6] = var1[var4][var6].doubleValue();
                this.zBDdata[var4][var6] = var1[var4][var6];
            }
        }

        this.polyIndices();
        this.check();
    }

    public SurfaceSmooth(BigInteger[] var1, BigInteger[] var2, BigInteger[][] var3) {
        this.arbprec = true;
        int var4 = var1.length;
        int var5 = var2.length;
        this.xData = new double[var4];
        this.yData = new double[var5];
        this.xBDdata = new BigDecimal[var4];
        this.yBDdata = new BigDecimal[var5];
        this.zBDdata = new BigDecimal[var3.length][var3[0].length];

        int var6;
        for(var6 = 0; var6 < var4; ++var6) {
            this.xBDdata[var6] = new BigDecimal(var1[var6]);
            this.xData[var6] = var1[var6].doubleValue();
        }

        for(var6 = 0; var6 < var5; ++var6) {
            this.yBDdata[var6] = new BigDecimal(var2[var6]);
            this.yData[var6] = var2[var6].doubleValue();
        }

        for(var6 = 0; var6 < var3.length; ++var6) {
            for(int var7 = 0; var7 < var3[0].length; ++var7) {
                this.zBDdata[var6][var7] = new BigDecimal(var3[var6][var7]);
                this.zData[var6][var7] = var3[var6][var7].doubleValue();
            }
        }

        this.polyIndices();
        this.check();
        this.ascend();
    }

    public SurfaceSmooth(BigInteger[][] var1) {
        this.arbprec = true;
        int var2 = var1.length;
        int var3 = var1[0].length;
        this.zData = new double[var2][var3];
        this.xData = new double[var2];
        this.yData = new double[var3];
        this.zBDdata = new BigDecimal[var2][var3];
        this.xBDdata = new BigDecimal[var2];
        this.yBDdata = new BigDecimal[var3];

        int var4;
        String var5;
        for(var4 = 0; var4 < var3; ++var4) {
            this.xData[var4] = (double)var4;
            var5 = (new Integer(var4)).toString();
            this.xBDdata[var4] = new BigDecimal(var5);
        }

        for(var4 = 0; var4 < var2; ++var4) {
            this.yData[var4] = (double)var4;
            var5 = (new Integer(var4)).toString();
            this.yBDdata[var4] = new BigDecimal(var5);
        }

        for(var4 = 0; var4 < var2; ++var4) {
            for(int var6 = 0; var6 < var3; ++var6) {
                this.zData[var4][var6] = var1[var4][var6].doubleValue();
                this.zBDdata[var4][var6] = new BigDecimal(var1[var4][var6]);
            }
        }

        this.polyIndices();
        this.check();
    }

    public SurfaceSmooth(double[] var1, double[] var2, Matrix var3) {
        this.xData = var1;
        this.yData = var2;
        this.zData = var3.getArrayCopy();
        this.polyIndices();
        this.check();
        this.ascend();
    }

    public SurfaceSmooth(Matrix var1) {
        this.zData = var1.getArrayCopy();
        int var2 = this.zData.length;
        int var3 = this.zData[0].length;

        int var4;
        for(var4 = 0; var4 < var3; ++var4) {
            this.xData[var4] = (double)var4;
        }

        for(var4 = 0; var4 < var2; ++var4) {
            this.yData[var4] = (double)var4;
        }

        this.polyIndices();
        this.polyIndices();
        this.check();
        this.ascend();
    }

    public SurfaceSmooth() {
    }

    private void polyIndices() {
        this.nSGcoeff = 0;

        int var1;
        for(var1 = 1; var1 <= this.sgPolyDeg + 1; ++var1) {
            this.nSGcoeff += var1;
        }

        this.sgCoeffIndices = new int[this.nSGcoeff][2];
        var1 = 0;

        for(int var2 = 0; var2 <= this.sgPolyDeg; ++var2) {
            for(int var3 = 0; var3 <= this.sgPolyDeg - var2; this.sgCoeffIndices[var1++][1] = var3++) {
                this.sgCoeffIndices[var1][0] = var2;
            }
        }

    }

    private void check() {
        this.nPointsY = this.yData.length;
        this.nPointsX = this.xData.length;
        this.nPoints = this.nPointsX * this.nPointsY;
        int var1 = this.zData.length;
        int var2 = this.zData[0].length;
        if (this.nPointsX == var2) {
            if (this.nPointsY != var1) {
                throw new IllegalArgumentException("The lengths of the x data arrays, " + this.nPointsX + " and " + this.nPointsY + ", do not match the dimensions of the  y data matrix, " + var1 + " and " + var2);
            }
        } else if (this.nPointsY == var2) {
            if (this.nPointsX != var1) {
                throw new IllegalArgumentException("The lengths of the x data arrays, " + this.nPointsX + " and " + this.nPointsY + ", do not match the dimensions of the  y data matrix, " + var1 + " and " + var2);
            }

            this.zData = this.transpose(this.zData);
            System.out.println("zData transposed to match the dimensions of the xDatas and yData");
        }

        if (!this.arbprec) {
            this.xBDdata = new BigDecimal[this.nPointsX];
            this.yBDdata = new BigDecimal[this.nPointsY];

            int var3;
            for(var3 = 0; var3 < this.nPointsX; ++var3) {
                this.xBDdata[var3] = new BigDecimal((new Double(this.xData[var3])).toString());
            }

            for(var3 = 0; var3 < this.nPointsY; ++var3) {
                this.yBDdata[var3] = new BigDecimal((new Double(this.yData[var3])).toString());
            }
        }

    }

    private double[][] transpose(double[][] var1) {
        int var2 = var1.length;
        int var3 = var1[0].length;
        double[][] var4 = new double[var3][var2];

        for(int var5 = 0; var5 < var2; ++var5) {
            for(int var6 = 0; var6 < var3; ++var6) {
                var4[var6][var5] = var1[var5][var6];
            }
        }

        return var4;
    }

    private void ascend() {
        boolean var1 = true;
        boolean var2 = true;
        int var3 = 1;

        while(var1) {
            if (this.yData[var3] < this.yData[var3 - 1]) {
                var1 = false;
                var2 = false;
            } else {
                ++var3;
                if (var3 >= this.nPointsY) {
                    var1 = false;
                }
            }
        }

        ArrayMaths var4;
        int[] var5;
        double[] var6;
        double[][] var7;
        BigDecimal[] var8;
        BigDecimal[][] var9;
        int var10;
        int var11;
        if (!var2) {
            var4 = new ArrayMaths(this.yData);
            var4 = var4.sort();
            var5 = var4.originalIndices();
            var6 = new double[this.nPointsY];
            var7 = new double[this.nPointsY][this.nPointsX];
            var8 = new BigDecimal[this.nPointsY];
            var9 = (BigDecimal[][])null;
            if (this.arbprec) {
                var9 = new BigDecimal[this.nPointsY][this.nPointsX];
            }

            for(var10 = 0; var10 < this.nPointsY; ++var10) {
                var6[var10] = this.yData[var5[var10]];
                var8[var10] = this.yBDdata[var5[var10]];

                for(var11 = 0; var11 < this.nPointsX; ++var11) {
                    var7[var10][var11] = this.zData[var5[var10]][var11];
                    if (this.arbprec) {
                        var9[var10][var11] = this.zBDdata[var5[var10]][var11];
                    }
                }
            }

            for(var10 = 0; var10 < this.nPointsY; ++var10) {
                this.yData[var10] = var6[var10];
                this.yBDdata[var10] = var8[var10];

                for(var11 = 0; var11 < this.nPointsX; ++var11) {
                    this.zData[var10][var11] = var7[var10][var11];
                    if (this.arbprec) {
                        this.zBDdata[var10][var11] = var9[var10][var11];
                    }
                }
            }
        }

        var1 = true;
        var2 = true;
        var3 = 1;

        while(var1) {
            if (this.xData[var3] < this.xData[var3 - 1]) {
                var1 = false;
                var2 = false;
            } else {
                ++var3;
                if (var3 >= this.nPointsX) {
                    var1 = false;
                }
            }
        }

        if (!var2) {
            var4 = new ArrayMaths(this.xData);
            var4 = var4.sort();
            var5 = var4.originalIndices();
            var6 = new double[this.nPointsX];
            var7 = new double[this.nPointsY][this.nPointsX];
            var8 = new BigDecimal[this.nPointsX];
            var9 = (BigDecimal[][])null;
            if (this.arbprec) {
                var9 = new BigDecimal[this.nPointsY][this.nPointsX];
            }

            for(var10 = 0; var10 < this.nPointsX; ++var10) {
                var6[var10] = this.xData[var5[var10]];
                var8[var10] = this.xBDdata[var5[var10]];

                for(var11 = 0; var11 < this.nPointsY; ++var11) {
                    var7[var11][var10] = this.zData[var11][var5[var10]];
                    if (this.arbprec) {
                        var9[var11][var10] = this.zBDdata[var11][var5[var10]];
                    }
                }
            }

            for(var10 = 0; var10 < this.nPointsX; ++var10) {
                this.xData[var10] = var6[var10];
                this.xBDdata[var10] = var8[var10];

                for(var11 = 0; var11 < this.nPointsY; ++var11) {
                    this.zData[var11][var10] = var7[var11][var10];
                    if (this.arbprec) {
                        this.zBDdata[var11][var10] = var9[var11][var10];
                    }
                }
            }
        }

    }

    public double[][] movingAverage(int var1, int var2) {
        this.lastMethod = 1;
        this.zDataMovAv = new double[this.nPointsY][this.nPointsX];
        this.zDataMovAvBD = new BigDecimal[this.nPointsY][this.nPointsX];
        this.maWindowWidthx = this.windowLength(var1);
        int var3 = (this.maWindowWidthx - 1) / 2;
        this.maWindowWidthy = this.windowLength(var2);
        int var4 = (this.maWindowWidthy - 1) / 2;
        boolean var5 = false;
        boolean var6 = false;
        boolean var7 = false;
        boolean var8 = false;

        for(int var9 = 0; var9 < this.nPointsX; ++var9) {
            int var17;
            if (var9 >= var3) {
                var17 = var9 - var3;
            } else {
                var17 = 0;
            }

            int var18;
            if (var9 <= this.nPointsX - var3 - 1) {
                var18 = var9 + var3;
            } else {
                var18 = this.nPointsX - 1;
            }

            int var10 = var18 - var17 + 1;

            for(int var11 = 0; var11 < this.nPointsY; ++var11) {
                int var19;
                if (var11 >= var4) {
                    var19 = var11 - var4;
                } else {
                    var19 = 0;
                }

                int var20;
                if (var11 <= this.nPointsY - var4 - 1) {
                    var20 = var11 + var4;
                } else {
                    var20 = this.nPointsY - 1;
                }

                int var12 = var20 - var19 + 1;
                int var15;
                if (this.arbprec) {
                    BigDecimal var21 = new BigDecimal("0.0");

                    for(int var14 = var17; var14 <= var18; ++var14) {
                        for(var15 = var19; var15 <= var20; ++var15) {
                            var21 = var21.add(this.zBDdata[var15][var14]);
                        }
                    }

                    String var22 = (new Integer(var10 * var12)).toString();
                    this.zDataMovAvBD[var11][var9] = var21.divide(new BigDecimal(var22), 4);
                    this.zDataMovAv[var11][var9] = this.zDataMovAvBD[var11][var9].doubleValue();
                } else {
                    double var13 = 0.0D;

                    for(var15 = var17; var15 <= var18; ++var15) {
                        for(int var16 = var19; var16 <= var20; ++var16) {
                            var13 += this.zData[var16][var15];
                        }
                    }

                    this.zDataMovAv[var11][var9] = var13 / (double)(var10 * var12);
                    String var23 = (new Double(this.zDataMovAv[var11][var9])).toString();
                    this.zDataMovAvBD[var11][var9] = new BigDecimal(var23);
                }
            }
        }

        this.bcsMovAv = new BiCubicSpline(this.yData, this.xData, this.zDataMovAv);
        this.calcMovAv = true;
        return Conv.copy(this.zDataMovAv);
    }

    public double[][] movingAverage(int var1) {
        return this.movingAverage(var1, var1);
    }

    public BigDecimal[][] movingAverageAsBigDecimal(int var1, int var2) {
        this.movingAverage(var1, var2);
        return Conv.copy(this.zDataMovAvBD);
    }

    public BigDecimal[][] movingAverageAsBigDecimal(int var1) {
        this.movingAverage(var1, var1);
        return Conv.copy(this.zDataMovAvBD);
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

    public double[][] savitzkyGolay(int var1, int var2) {
        this.lastMethod = 2;
        this.zDataSavGol = new double[this.nPointsY][this.nPointsX];
        this.sgWindowWidthx = this.windowLength(var1);
        this.sgWindowWidthy = this.windowLength(var2);
        this.savitzkyGolayCommon(this.sgWindowWidthx, this.sgWindowWidthy);
        this.bcsSavGol = new BiCubicSpline(this.yData, this.xData, Conv.copy(this.zDataSavGol));
        this.calcSavGol = true;
        return Conv.copy(this.zDataSavGol);
    }

    public double[][] savitzkyGolay(int var1) {
        return this.savitzkyGolay(var1, var1);
    }

    private double[][] savitzkyGolayCommon(int var1, int var2) {
        int var3 = (var1 - 1) / 2;
        int var4 = (var2 - 1) / 2;
        double[] var5 = this.savitzkyGolayFilter(var3, var3, var4, var4)[0];
        double[][] var6 = this.padData(this.zData, var3, var4);

        for(int var7 = var4; var7 < this.nPointsY + var4; ++var7) {
            for(int var8 = var3; var8 < this.nPointsX + var3; ++var8) {
                double var9 = 0.0D;
                int var11 = 0;

                for(int var12 = var7 - var4; var12 <= var7 + var4; ++var12) {
                    for(int var13 = var8 - var3; var13 <= var8 + var3; ++var13) {
                        var9 += var6[var12][var13] * var5[var11++];
                    }
                }

                this.zDataSavGol[var7 - var4][var8 - var3] = var9;
            }
        }

        return this.zDataSavGol;
    }

    private double[][] padData(double[][] var1, int var2, int var3) {
        int var4 = var1.length;
        int var5 = var1[0].length;
        double[][] var6 = new double[var4 + 2 * var3][var5 + 2 * var2];

        int var7;
        int var8;
        for(var7 = 0; var7 < var4; ++var7) {
            for(var8 = 0; var8 < var5; ++var8) {
                var6[var7 + var3][var8 + var2] = var1[var7][var8];
            }
        }

        for(var7 = 0; var7 < var3; ++var7) {
            for(var8 = var2; var8 < var5 + var2; ++var8) {
                var6[var7][var8] = var6[var3][var8];
            }
        }

        for(var7 = 0; var7 < var2; ++var7) {
            for(var8 = var3; var8 < var4 + var3; ++var8) {
                var6[var8][var7] = var6[var8][var2];
            }
        }

        for(var7 = var2 + var5; var7 < var5 + 2 * var2; ++var7) {
            for(var8 = var3; var8 < var4 + var3; ++var8) {
                var6[var8][var7] = var6[var8][var2 + var5 - 1];
            }
        }

        for(var7 = var3 + var4; var7 < var4 + 2 * var3; ++var7) {
            for(var8 = var2; var8 < var5 + var2; ++var8) {
                var6[var7][var8] = var6[var4 + var3 - 1][var8];
            }
        }

        for(var7 = 0; var7 < var3; ++var7) {
            for(var8 = 0; var8 < var2; ++var8) {
                var6[var7][var8] = var6[var3][var2];
            }
        }

        for(var7 = 0; var7 < var3; ++var7) {
            for(var8 = var5 + var2; var8 < var5 + 2 * var2; ++var8) {
                var6[var7][var8] = var6[var3][var5 + var2 - 1];
            }
        }

        for(var7 = var4 + var3; var7 < var4 + 2 * var3; ++var7) {
            for(var8 = 0; var8 < var2; ++var8) {
                var6[var7][var8] = var6[var4 + var3 - 1][var2];
            }
        }

        for(var7 = var4 + var3; var7 < var4 + 2 * var3; ++var7) {
            for(var8 = var5 + var2; var8 < var5 + 2 * var2; ++var8) {
                var6[var7][var8] = var6[var4 + var3 - 1][var5 + var2 - 1];
            }
        }

        return var6;
    }

    public double[][][] savitzkyGolay(int var1, int var2, int var3, int var4) {
        if (var4 + var3 > this.sgPolyDeg) {
            throw new IllegalArgumentException("The sum of the derivative orders " + var3 + " plus " + var4 + ", must be less than or equal to the polynomial degree, " + this.sgPolyDeg + ".");
        } else {
            this.lastMethod = 2;
            double[][][] var5 = new double[2][this.nPointsY][this.nPointsX];
            this.sgWindowWidthx = this.windowLength(var1);
            int var6 = (this.sgWindowWidthx - 1) / 2;
            this.sgWindowWidthy = this.windowLength(var2);
            int var7 = (this.sgWindowWidthy - 1) / 2;
            if (!this.calcSavGol) {
                this.savitzkyGolay(var1, var2);
            }

            var5[0] = this.zDataSavGol;
            int var8 = 0;
            boolean var9 = true;
            int var10 = this.sgCoeffIndices.length;

            while(var9) {
                if (this.sgCoeffIndices[var8][0] == var3 && this.sgCoeffIndices[var8][1] == var4) {
                    var9 = false;
                }

                ++var8;
                if (var8 >= var10) {
                    throw new IllegalArgumentException("It should not have been possible to reach this situation, m = " + var3 + ", n = " + var4);
                }
            }

            double[] var11 = this.sgArrayC[var8];
            double[][] var12 = this.padData(this.zData, var6, var7);

            for(int var13 = var7; var13 < this.nPointsY + var7; ++var13) {
                for(int var14 = var6; var14 < this.nPointsX + var6; ++var14) {
                    double var15 = 0.0D;
                    int var17 = 0;

                    for(int var18 = var13 - var7; var18 <= var13 + var7; ++var18) {
                        for(int var19 = var14 - var6; var19 <= var14 + var6; ++var19) {
                            var15 += var12[var18][var19] * var11[var17++];
                        }
                    }

                    var5[1][var13 - var7][var14 - var6] = var15;
                }
            }

            this.derivSavGol = var5[1];
            this.nthSet = true;
            return var5;
        }
    }

    public double[][] savitzkyGolayFilter(int var1, int var2, int var3, int var4) {
        int var5 = var1 + var2 + 1;
        int var6 = var3 + var4 + 1;
        int var7 = var5 * var6;
        double[] var8 = new double[var7];
        int[][] var9 = new int[var7][2];
        int var10 = 0;

        int var12;
        for(int var11 = 0; var11 < var5; ++var11) {
            for(var12 = 0; var12 < var6; ++var12) {
                var9[var10][0] = var11 - var1;
                var9[var10++][1] = var12 - var3;
            }
        }

        double[][] var17 = new double[var7][this.nSGcoeff];

        for(var12 = 0; var12 < var7; ++var12) {
            for(int var13 = 0; var13 < this.nSGcoeff; ++var13) {
                var17[var12][var13] = Math.pow((double)var9[var12][0], (double)this.sgCoeffIndices[var13][0]) * Math.pow((double)var9[var12][1], (double)this.sgCoeffIndices[var13][1]);
            }
        }

        Matrix var18 = new Matrix(var17);
        Matrix var19 = var18.transpose();
        Matrix var14 = var19.times(var18);
        Matrix var15 = var14.inverse();
        Matrix var16 = var15.times(var19);
        this.sgArrayC = var16.getArrayCopy();
        return this.sgArrayC;
    }

    public static double[][] savitzkyGolayFilter(int var0, int var1, int var2, int var3, int var4) {
        SurfaceSmooth var5 = new SurfaceSmooth();
        var5.setSGpolyDegree(var4);
        return var5.savitzkyGolayFilter(var0, var1, var2, var3);
    }

    public double[][] getSGcoefficients() {
        if (this.sgArrayC == null) {
            throw new IllegalArgumentException("No Savitzky-Golay coefficients have been calculated");
        } else {
            return this.sgArrayC;
        }
    }

    public int[][] getSGPolyIndices() {
        return this.sgCoeffIndices;
    }

    public static int[][] filterIndices(int var0) {
        SurfaceSmooth var1 = new SurfaceSmooth();
        var1.setSGpolyDegree(var0);
        return var1.getSGPolyIndices();
    }

    public void setSGpolyDegree(int var1) {
        this.sgPolyDeg = var1;
        this.polyIndices();
    }

    public int getSGpolyDegree() {
        return this.sgPolyDeg;
    }

    public double[][] getMovingAverageValues() {
        if (!this.calcMovAv) {
            throw new IllegalArgumentException("No moving average smoothing method has been called");
        } else {
            return Conv.copy(this.zDataMovAv);
        }
    }

    public BigDecimal[][] getMovingAverageValuesAsBigDecimal() {
        if (!this.calcMovAv) {
            throw new IllegalArgumentException("No moving average smoothing method has been called");
        } else {
            return Conv.copy(this.zDataMovAvBD);
        }
    }

    public double[][] getSavitzkyGolaySmoothedValues() {
        if (!this.calcSavGol) {
            throw new IllegalArgumentException("No Savitzky-Golay smoothing method has been called");
        } else {
            return Conv.copy(this.zDataSavGol);
        }
    }

    public double[][] getSavitzkyDerivatives() {
        if (!this.nthSet) {
            throw new IllegalArgumentException("No Savitzky-Golay derivative smoothing method has been called");
        } else {
            return Conv.copy(this.derivSavGol);
        }
    }

    public double extentMovingAverage() {
        if (!this.calcMovAv) {
            throw new IllegalArgumentException("No moving average smoothing method has been called");
        } else {
            this.extentMovAv = this.extent(this.zData, this.zDataMovAv);
            return this.extentMovAv;
        }
    }

    public double extentSavitzkyGolay() {
        if (!this.calcSavGol) {
            throw new IllegalArgumentException("No Savitzky-Golay smoothing method has been called");
        } else {
            this.extentSavGol = this.extent(this.zData, this.zDataSavGol);
            return this.extentSavGol;
        }
    }

    private double extent(double[][] var1, double[][] var2) {
        ArrayMaths var3 = new ArrayMaths(var1);
        double var4 = var3.getMinimum();
        double var6 = var3.getMaximum();
        double var8 = var6 - var4;
        double var10 = 0.0D;

        for(int var12 = 0; var12 < this.nPointsX; ++var12) {
            for(int var13 = 0; var13 < this.nPointsY; ++var13) {
                var10 += Math.abs(var1[var13][var12] - var2[var13][var12]) / var8;
            }
        }

        var10 /= (double)this.nPoints;
        return var10;
    }

    public double interpolateSavitzkyGolay(double var1, double var3) {
        if (!this.calcSavGol) {
            throw new IllegalArgumentException("No Savitzky-Golay smoothing method has been called");
        } else {
            return this.bcsSavGol.interpolate(var3, var1);
        }
    }

    public double interpolateMovingAverage(double var1, double var3) {
        if (!this.calcMovAv) {
            throw new IllegalArgumentException("No moving average smoothing method has been called");
        } else {
            return this.bcsMovAv.interpolate(var3, var1);
        }
    }

    public void plotSavitzkyGolayX(int var1) {
        if (!this.calcSavGol) {
            throw new IllegalArgumentException("No Savitzky-Golay smoothing method has been called");
        } else if (var1 >= this.nPointsY) {
            throw new IllegalArgumentException("The index, " + var1 + ", must be less than the number of y values, " + this.nPointsY);
        } else {
            byte var2 = 0;
            double var3 = Fmath.truncate(this.yData[var1], this.trunc);
            this.commonPlot(var2, var1, var3);
        }
    }

    public void plotSavitzkyGolayX(double var1) {
        if (!this.calcSavGol) {
            throw new IllegalArgumentException("No Savitzky-Golay smoothing method has been called");
        } else {
            byte var3 = 0;
            int var4 = this.findValue(this.yData, var1);
            var1 = Fmath.truncate(this.yData[var4], this.trunc);
            this.commonPlot(var3, var4, var1);
        }
    }

    public void plotSavitzkyGolayY(int var1) {
        if (!this.calcSavGol) {
            throw new IllegalArgumentException("No Savitzky-Golay smoothing method has been called");
        } else if (var1 >= this.nPointsX) {
            throw new IllegalArgumentException("The index, " + var1 + ", must be less than the number of x values, " + this.nPointsX);
        } else {
            byte var2 = 1;
            double var3 = Fmath.truncate(this.xData[var1], this.trunc);
            this.commonPlot(var2, var1, var3);
        }
    }

    public void plotSavitzkyGolayY(double var1) {
        if (!this.calcSavGol) {
            throw new IllegalArgumentException("No Savitzky-Golay smoothing method has been called");
        } else {
            byte var3 = 1;
            int var4 = this.findValue(this.xData, var1);
            var1 = Fmath.truncate(this.xData[var4], this.trunc);
            this.commonPlot(var3, var4, var1);
        }
    }

    public void plotMovingAverageX(int var1) {
        if (!this.calcMovAv) {
            throw new IllegalArgumentException("No moving average smoothing method has been called");
        } else if (var1 >= this.nPointsY) {
            throw new IllegalArgumentException("The index, " + var1 + ", must be less than the number of y values, " + this.nPointsY);
        } else {
            byte var2 = 2;
            double var3 = Fmath.truncate(this.yData[var1], this.trunc);
            this.commonPlot(var2, var1, var3);
        }
    }

    public void plotMovingAverageX(double var1) {
        if (!this.calcMovAv) {
            throw new IllegalArgumentException("No moving average smoothing method has been called");
        } else {
            byte var3 = 2;
            int var4 = this.findValue(this.yData, var1);
            var1 = Fmath.truncate(this.yData[var4], this.trunc);
            this.commonPlot(var3, var4, var1);
        }
    }

    public void plotMovingAverageY(int var1) {
        if (!this.calcMovAv) {
            throw new IllegalArgumentException("No moving average smoothing method has been called");
        } else if (var1 >= this.nPointsX) {
            throw new IllegalArgumentException("The index, " + var1 + ", must be less than the number of x values, " + this.nPointsX);
        } else {
            byte var2 = 3;
            double var3 = Fmath.truncate(this.xData[var1], this.trunc);
            this.commonPlot(var2, var1, var3);
        }
    }

    public void plotMovingAverageY(double var1) {
        if (!this.calcMovAv) {
            throw new IllegalArgumentException("No moving average smoothing method has been called");
        } else {
            byte var3 = 3;
            int var4 = this.findValue(this.xData, var1);
            var1 = Fmath.truncate(this.xData[var4], this.trunc);
            this.commonPlot(var3, var4, var1);
        }
    }

    private int findValue(double[] var1, double var2) {
        int var4 = var1.length;
        boolean var5 = true;
        int var6 = 0;

        while(var5) {
            if (Fmath.isEqualWithinLimits(var1[var6], var2, Math.abs(var2) * 0.001D)) {
                var5 = false;
            } else {
                ++var6;
                if (var6 >= var4) {
                    throw new IllegalArgumentException("The entered plot value, " + var2 + ",  must equal an entered data value");
                }
            }
        }

        return var6;
    }

    private void commonPlot(int var1, int var2, double var3) {
        String var5;
        String var6;
        String var8;
        String var9;
        double[][] var12;
        double[] var13;
        double[] var14;
        double[] var15;
        double[] var16;
        var5 = null;
        var6 = null;
        String var7 = ",  Original data - circles,  Smoothed data - squares";
        var8 = null;
        var9 = null;
        int[] var10 = new int[]{0, this.nPointsX / 4, this.nPointsX / 2, 3 * this.nPointsX / 4, this.nPointsX - 1};
        int[] var11 = new int[]{0, this.nPointsY / 4, this.nPointsY / 2, 3 * this.nPointsY / 4, this.nPointsY - 1};
        var12 = new double[8][];
        var13 = new double[5];
        var14 = new double[5];
        var15 = new double[5];
        var16 = new double[5];
        int var17;
        label59:
        switch(var1) {
            case 0:
                var5 = "Savitzky-Golay smoothing with an x by y window of " + this.sgWindowWidthx + " by " + this.sgWindowWidthy + " points";
                var6 = "Plot of z versus x values for a y value of " + var3 + var7;
                var8 = "x values";
                var9 = "y values";
                var12[0] = this.xData;
                var12[1] = this.zData[var2];
                var12[2] = this.xData;
                var12[3] = this.zDataSavGol[var2];
                var17 = 0;

                while(true) {
                    if (var17 >= 5) {
                        break label59;
                    }

                    var13[var17] = this.xData[var10[var17]];
                    var15[var17] = this.zData[var2][var10[var17]];
                    var14[var17] = this.xData[var10[var17]];
                    var16[var17] = this.zDataSavGol[var2][var10[var17]];
                    ++var17;
                }
            case 1:
                var5 = "Savitzky-Golay smoothing with an x by y window of " + this.sgWindowWidthx + " by " + this.sgWindowWidthy + " points";
                var6 = "Plot of z versus y values for a x value of " + var3 + var7;
                var8 = "y values";
                var9 = "x values";
                var12[0] = this.yData;
                var12[2] = this.yData;
                var12[1] = new double[this.nPointsY];
                var12[3] = new double[this.nPointsY];

                for(var17 = 0; var17 < this.nPointsY; ++var17) {
                    var12[1][var17] = this.zData[var17][var2];
                    var12[3][var17] = this.zDataSavGol[var17][var2];
                }

                var17 = 0;

                while(true) {
                    if (var17 >= 5) {
                        break label59;
                    }

                    var13[var17] = this.yData[var11[var17]];
                    var15[var17] = var12[1][var11[var17]];
                    var14[var17] = this.yData[var11[var17]];
                    var16[var17] = var12[3][var11[var17]];
                    ++var17;
                }
            case 2:
                var5 = "Moving Average smoothing with an x by y window of " + this.sgWindowWidthx + " by " + this.sgWindowWidthy + " points";
                var6 = "Plot of z versus x values for a y value of " + var3 + var7;
                var8 = "x values";
                var9 = "y values";
                var12[0] = this.xData;
                var12[1] = this.zData[var2];
                var12[2] = this.xData;
                var12[3] = this.zDataMovAv[var2];
                var17 = 0;

                while(true) {
                    if (var17 >= 5) {
                        break label59;
                    }

                    var13[var17] = this.xData[var10[var17]];
                    var15[var17] = this.zData[var2][var10[var17]];
                    var14[var17] = this.xData[var10[var17]];
                    var16[var17] = this.zDataMovAv[var2][var10[var17]];
                    ++var17;
                }
            case 3:
                var5 = "Moving Average smoothing with an x by y window of " + this.sgWindowWidthx + " by " + this.sgWindowWidthy + " points";
                var6 = "Plot of z versus y values for a x value of " + var3 + var7;
                var8 = "y values";
                var9 = "x values";
                var12[0] = this.yData;
                var12[2] = this.yData;
                var12[1] = new double[this.nPointsY];
                var12[3] = new double[this.nPointsY];

                for(var17 = 0; var17 < this.nPointsY; ++var17) {
                    var12[1][var17] = this.zData[var17][var2];
                    var12[3][var17] = this.zDataMovAv[var17][var2];
                }

                for(var17 = 0; var17 < 5; ++var17) {
                    var13[var17] = this.yData[var11[var17]];
                    var15[var17] = var12[1][var11[var17]];
                    var14[var17] = this.yData[var11[var17]];
                    var16[var17] = var12[3][var11[var17]];
                }
        }

        var12[4] = var13;
        var12[5] = var15;
        var12[6] = var14;
        var12[7] = var16;
        PlotGraph var20 = new PlotGraph(var12);
        int[] var18 = new int[]{0, 0, 1, 2};
        int[] var19 = new int[]{3, 3, 0, 0};
        var20.setPoint(var18);
        var20.setLine(var19);
        var20.setGraphTitle(var5);
        var20.setGraphTitle2(var6);
        var20.setXaxisLegend(var8);
        var20.setYaxisLegend(var9);
        var20.plot();
    }
}
