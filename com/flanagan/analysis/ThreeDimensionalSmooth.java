//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.analysis;

import com.flanagan.interpolation.TriCubicSpline;
import com.flanagan.math.ArrayMaths;
import com.flanagan.math.Conv;
import com.flanagan.math.Fmath;
import com.flanagan.math.Matrix;
import com.flanagan.plot.PlotGraph;
import java.math.BigDecimal;
import java.math.BigInteger;

public class ThreeDimensionalSmooth {
    private double[] xData = null;
    private double[] yData = null;
    private double[] zData = null;
    private double[][][] vData = (double[][][])null;
    private BigDecimal[] xBDdata = null;
    private BigDecimal[] yBDdata = null;
    private BigDecimal[] zBDdata = null;
    private BigDecimal[][][] vBDdata = (BigDecimal[][][])null;
    private int nPointsX = 0;
    private int nPointsY = 0;
    private int nPointsZ = 0;
    private int nPoints = 0;
    private boolean arbprec = false;
    private double[][][] vDataMovAv = (double[][][])null;
    private BigDecimal[][][] vDataMovAvBD = (BigDecimal[][][])null;
    private double[][][] vDataSavGol = (double[][][])null;
    private double[][][] derivSavGol = (double[][][])null;
    private int[][] sgCoeffIndices = (int[][])null;
    private int nSGcoeff = 0;
    private int lastMethod = 0;
    private int nMethods = 2;
    private int maWindowWidthx = 0;
    private int maWindowWidthy = 0;
    private int maWindowWidthz = 0;
    private int sgWindowWidthx = 0;
    private int sgWindowWidthy = 0;
    private int sgWindowWidthz = 0;
    private int sgPolyDeg = 4;
    private double[][] sgArrayC = (double[][])null;
    private boolean calcSavGol = false;
    private boolean calcMovAv = false;
    private boolean nthSet = false;
    private double extentMovAv = -1.0D;
    private double extentSavGol = -1.0D;
    private TriCubicSpline tcsSavGol = null;
    private TriCubicSpline tcsMovAv = null;
    private int trunc = 4;

    public ThreeDimensionalSmooth(double[] var1, double[] var2, double[] var3, double[][][] var4) {
        this.xData = var1;
        this.yData = var2;
        this.zData = var3;
        this.vData = var4;
        this.polyIndices();
        this.check();
        this.ascend();
    }

    public ThreeDimensionalSmooth(double[][][] var1) {
        int var2 = var1.length;
        this.vData = var1;
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

        var2 = var1[0][0].length;
        this.zData = new double[var2];

        for(var3 = 0; var3 < var2; ++var3) {
            this.zData[var3] = (double)var3;
        }

        this.polyIndices();
        this.check();
    }

    public ThreeDimensionalSmooth(float[] var1, float[] var2, float[] var3, float[][][] var4) {
        ArrayMaths var5 = new ArrayMaths(var1);
        this.xData = var5.array();
        var5 = new ArrayMaths(var2);
        this.yData = var5.array();
        var5 = new ArrayMaths(var1);
        this.zData = var5.array();
        int var6 = var4.length;
        int var7 = var4[0].length;
        int var8 = var4[0][0].length;
        this.vData = new double[var6][var7][var8];

        for(int var9 = 0; var9 < var6; ++var9) {
            for(int var10 = 0; var10 < var7; ++var10) {
                for(int var11 = 0; var11 < var8; ++var11) {
                    this.vData[var9][var10][var11] = Conv.convert_float_to_double(var4[var9][var10][var11]);
                }
            }
        }

        this.polyIndices();
        this.check();
        this.ascend();
    }

    public ThreeDimensionalSmooth(float[][][] var1) {
        int var2 = var1.length;
        int var3 = var1[0].length;
        int var4 = var1[0][0].length;
        this.vData = new double[var2][var3][var4];
        this.xData = new double[var2];
        this.yData = new double[var3];
        this.zData = new double[var4];

        int var5;
        for(var5 = 0; var5 < var2; ++var5) {
            this.xData[var5] = (double)var5;
        }

        for(var5 = 0; var5 < var3; ++var5) {
            this.yData[var5] = (double)var5;
        }

        for(var5 = 0; var5 < var4; ++var5) {
            this.zData[var5] = (double)var5;
        }

        for(var5 = 0; var5 < var2; ++var5) {
            for(int var6 = 0; var6 < var3; ++var6) {
                for(int var7 = 0; var7 < var4; ++var7) {
                    this.vData[var5][var6][var7] = Conv.convert_float_to_double(var1[var5][var6][var7]);
                }
            }
        }

        this.polyIndices();
        this.check();
    }

    public ThreeDimensionalSmooth(long[] var1, long[] var2, long[] var3, long[][][] var4) {
        ArrayMaths var5 = new ArrayMaths(var1);
        this.xData = var5.array();
        var5 = new ArrayMaths(var2);
        this.yData = var5.array();
        var5 = new ArrayMaths(var1);
        this.zData = var5.array();
        int var6 = var4.length;
        int var7 = var4[0].length;
        int var8 = var4[0][0].length;
        this.vData = new double[var6][var7][var8];

        for(int var9 = 0; var9 < var6; ++var9) {
            for(int var10 = 0; var10 < var7; ++var10) {
                for(int var11 = 0; var11 < var8; ++var11) {
                    this.vData[var9][var10][var11] = Conv.convert_long_to_double(var4[var9][var10][var11]);
                }
            }
        }

        this.polyIndices();
        this.check();
        this.ascend();
    }

    public ThreeDimensionalSmooth(long[][][] var1) {
        int var2 = var1.length;
        int var3 = var1[0].length;
        int var4 = var1[0][0].length;
        this.vData = new double[var2][var3][var4];
        this.xData = new double[var2];
        this.yData = new double[var3];
        this.zData = new double[var4];

        int var5;
        for(var5 = 0; var5 < var2; ++var5) {
            this.xData[var5] = (double)var5;
        }

        for(var5 = 0; var5 < var3; ++var5) {
            this.yData[var5] = (double)var5;
        }

        for(var5 = 0; var5 < var4; ++var5) {
            this.zData[var5] = (double)var5;
        }

        for(var5 = 0; var5 < var2; ++var5) {
            for(int var6 = 0; var6 < var3; ++var6) {
                for(int var7 = 0; var7 < var4; ++var7) {
                    this.vData[var5][var6][var7] = Conv.convert_long_to_double(var1[var5][var6][var7]);
                }
            }
        }

        this.polyIndices();
        this.check();
    }

    public ThreeDimensionalSmooth(int[] var1, int[] var2, int[] var3, int[][][] var4) {
        ArrayMaths var5 = new ArrayMaths(var1);
        this.xData = var5.array();
        var5 = new ArrayMaths(var2);
        this.yData = var5.array();
        var5 = new ArrayMaths(var1);
        this.zData = var5.array();
        int var6 = var4.length;
        int var7 = var4[0].length;
        int var8 = var4[0][0].length;
        this.vData = new double[var6][var7][var8];

        for(int var9 = 0; var9 < var6; ++var9) {
            for(int var10 = 0; var10 < var7; ++var10) {
                for(int var11 = 0; var11 < var8; ++var11) {
                    this.vData[var9][var10][var11] = (double)Conv.convert_long_to_int((long)var4[var9][var10][var11]);
                }
            }
        }

        this.polyIndices();
        this.check();
        this.ascend();
    }

    public ThreeDimensionalSmooth(int[][][] var1) {
        int var2 = var1.length;
        int var3 = var1[0].length;
        int var4 = var1[0][0].length;
        this.vData = new double[var2][var3][var4];
        this.xData = new double[var2];
        this.yData = new double[var3];
        this.zData = new double[var4];

        int var5;
        for(var5 = 0; var5 < var2; ++var5) {
            this.xData[var5] = (double)var5;
        }

        for(var5 = 0; var5 < var3; ++var5) {
            this.yData[var5] = (double)var5;
        }

        for(var5 = 0; var5 < var4; ++var5) {
            this.zData[var5] = (double)var5;
        }

        for(var5 = 0; var5 < var2; ++var5) {
            for(int var6 = 0; var6 < var3; ++var6) {
                for(int var7 = 0; var7 < var4; ++var7) {
                    this.vData[var5][var6][var7] = Conv.convert_int_to_double(var1[var5][var6][var7]);
                }
            }
        }

        this.polyIndices();
        this.check();
    }

    public ThreeDimensionalSmooth(BigDecimal[] var1, BigDecimal[] var2, BigDecimal[] var3, BigDecimal[][][] var4) {
        ArrayMaths var5 = new ArrayMaths(var1);
        this.xData = var5.array();
        this.xBDdata = var1;
        var5 = new ArrayMaths(var2);
        this.yData = var5.array();
        this.yBDdata = var2;
        var5 = new ArrayMaths(var3);
        this.zData = var5.array();
        this.zBDdata = var3;
        int var6 = var4.length;
        int var7 = var4[0].length;
        int var8 = var4[0][0].length;
        this.vData = new double[var6][var7][var8];
        this.vData = new double[var6][var7][var8];
        this.vBDdata = new BigDecimal[var6][var7][var8];

        for(int var9 = 0; var9 < var6; ++var9) {
            for(int var10 = 0; var10 < var7; ++var10) {
                for(int var11 = 0; var11 < var8; ++var11) {
                    this.vBDdata[var9][var10][var11] = var4[var9][var10][var11];
                    this.vData[var9][var10][var11] = this.vBDdata[var9][var10][var11].doubleValue();
                }
            }
        }

        this.polyIndices();
        this.check();
        this.ascend();
    }

    public ThreeDimensionalSmooth(BigDecimal[][][] var1) {
        int var2 = var1.length;
        int var3 = var1[0].length;
        int var4 = var1[0][0].length;
        this.vData = new double[var2][var3][var4];
        this.vBDdata = new BigDecimal[var2][var3][var4];
        this.xData = new double[var2];
        this.yData = new double[var3];
        this.zData = new double[var4];
        this.xBDdata = new BigDecimal[var2];
        this.yBDdata = new BigDecimal[var3];
        this.zBDdata = new BigDecimal[var4];

        int var5;
        for(var5 = 0; var5 < var2; ++var5) {
            this.xData[var5] = (double)var5;
            this.xBDdata[var5] = new BigDecimal((new Integer(var5)).toString());
        }

        for(var5 = 0; var5 < var3; ++var5) {
            this.yData[var5] = (double)var5;
            this.yBDdata[var5] = new BigDecimal((new Integer(var5)).toString());
        }

        for(var5 = 0; var5 < var4; ++var5) {
            this.zData[var5] = (double)var5;
            this.zBDdata[var5] = new BigDecimal((new Integer(var5)).toString());
        }

        for(var5 = 0; var5 < var2; ++var5) {
            for(int var6 = 0; var6 < var3; ++var6) {
                for(int var7 = 0; var7 < var4; ++var7) {
                    this.vBDdata[var5][var6][var7] = var1[var5][var6][var7];
                    this.vData[var5][var6][var7] = Conv.convert_BigDecimal_to_double(var1[var5][var6][var7]);
                }
            }
        }

        this.polyIndices();
        this.check();
    }

    public ThreeDimensionalSmooth(BigInteger[] var1, BigInteger[] var2, BigInteger[] var3, BigInteger[][][] var4) {
        ArrayMaths var5 = new ArrayMaths(var1);
        this.xData = var5.array();
        this.xBDdata = var5.array_as_BigDecimal();
        var5 = new ArrayMaths(var2);
        this.yData = var5.array();
        this.yBDdata = var5.array_as_BigDecimal();
        var5 = new ArrayMaths(var3);
        this.zData = var5.array();
        this.zBDdata = var5.array_as_BigDecimal();
        int var6 = var4.length;
        int var7 = var4[0].length;
        int var8 = var4[0][0].length;
        this.vData = new double[var6][var7][var8];
        this.vBDdata = new BigDecimal[var6][var7][var8];

        for(int var9 = 0; var9 < var6; ++var9) {
            for(int var10 = 0; var10 < var7; ++var10) {
                for(int var11 = 0; var11 < var8; ++var11) {
                    this.vBDdata[var9][var10][var11] = new BigDecimal(var4[var9][var10][var11]);
                    this.vData[var9][var10][var11] = this.vBDdata[var9][var10][var11].doubleValue();
                }
            }
        }

        this.polyIndices();
        this.check();
        this.ascend();
    }

    public ThreeDimensionalSmooth(BigInteger[][][] var1) {
        int var2 = var1.length;
        int var3 = var1[0].length;
        int var4 = var1[0][0].length;
        this.vData = new double[var2][var3][var4];
        this.vBDdata = new BigDecimal[var2][var3][var4];
        this.xData = new double[var2];
        this.yData = new double[var3];
        this.zData = new double[var4];
        this.xBDdata = new BigDecimal[var2];
        this.yBDdata = new BigDecimal[var3];
        this.zBDdata = new BigDecimal[var4];

        int var5;
        for(var5 = 0; var5 < var2; ++var5) {
            this.xData[var5] = (double)var5;
            this.xBDdata[var5] = new BigDecimal((new Integer(var5)).toString());
        }

        for(var5 = 0; var5 < var3; ++var5) {
            this.yData[var5] = (double)var5;
            this.yBDdata[var5] = new BigDecimal((new Integer(var5)).toString());
        }

        for(var5 = 0; var5 < var4; ++var5) {
            this.zData[var5] = (double)var5;
            this.zBDdata[var5] = new BigDecimal((new Integer(var5)).toString());
        }

        for(var5 = 0; var5 < var2; ++var5) {
            for(int var6 = 0; var6 < var3; ++var6) {
                for(int var7 = 0; var7 < var4; ++var7) {
                    this.vBDdata[var5][var6][var7] = new BigDecimal(var1[var5][var6][var7]);
                    this.vData[var5][var6][var7] = Conv.convert_BigInteger_to_double(var1[var5][var6][var7]);
                }
            }
        }

        this.polyIndices();
        this.check();
    }

    public ThreeDimensionalSmooth() {
    }

    private void polyIndices() {
        this.nSGcoeff = 0;

        int var1;
        int var2;
        for(var1 = 1; var1 <= this.sgPolyDeg + 1; ++var1) {
            for(var2 = 1; var2 <= var1; ++var2) {
                this.nSGcoeff += var2;
            }
        }

        this.sgCoeffIndices = new int[this.nSGcoeff][3];
        var1 = 0;

        for(var2 = 0; var2 <= this.sgPolyDeg; ++var2) {
            for(int var3 = 0; var3 <= this.sgPolyDeg - var2; ++var3) {
                for(int var4 = 0; var4 <= this.sgPolyDeg - var3 - var2; this.sgCoeffIndices[var1++][2] = var4++) {
                    this.sgCoeffIndices[var1][0] = var2;
                    this.sgCoeffIndices[var1][1] = var3;
                }
            }
        }

    }

    private void check() {
        this.nPointsZ = this.zData.length;
        this.nPointsY = this.yData.length;
        this.nPointsX = this.xData.length;
        this.nPoints = this.nPointsX * this.nPointsY * this.nPointsY;
        int var1 = this.vData.length;
        int var2 = this.vData[0].length;
        int var3 = this.vData[0][0].length;
        boolean var4 = true;
        boolean var5 = false;
        if (this.nPointsZ == var1 && this.nPointsY == var2 && this.nPointsX == var3) {
            var5 = true;
        } else {
            double[][][] var6;
            int var7;
            int var8;
            int var9;
            if (this.nPointsZ == var2 && this.nPointsY == var1 && this.nPointsX == var3) {
                var6 = this.copy3D(this.vData);
                this.vData = new double[var2][var1][var3];

                for(var7 = 0; var7 < var3; ++var7) {
                    for(var8 = 0; var8 < var2; ++var8) {
                        for(var9 = 0; var9 < var1; ++var9) {
                            this.vData[var8][var9][var7] = var6[var9][var8][var7];
                        }
                    }
                }

                var5 = true;
                System.out.println("vData transposed to match the dimensions of the yData and zData");
            } else if (this.nPointsZ == var1 && this.nPointsY == var3 && this.nPointsX == var2) {
                var6 = this.copy3D(this.vData);
                this.vData = new double[var1][var3][var2];

                for(var7 = 0; var7 < var1; ++var7) {
                    for(var8 = 0; var8 < var3; ++var8) {
                        for(var9 = 0; var9 < var2; ++var9) {
                            this.vData[var7][var8][var9] = var6[var7][var9][var8];
                        }
                    }
                }

                var5 = true;
                System.out.println("vData transposed to match the dimensions of the xDatas and yData");
            } else {
                if (this.nPointsZ == var3 && this.nPointsY == var2 && this.nPointsX == var1) {
                    var6 = this.copy3D(this.vData);
                    this.vData = new double[var3][var2][var1];

                    for(var7 = 0; var7 < var3; ++var7) {
                        for(var8 = 0; var8 < var1; ++var8) {
                            for(var9 = 0; var9 < var2; ++var9) {
                                this.vData[var8][var7][var9] = var6[var7][var9][var8];
                            }
                        }
                    }
                }

                var5 = true;
                System.out.println("vData transposed to match the dimensions of the xDatas and zData");
            }
        }

        if (!var5) {
            throw new IllegalArgumentException("The lengths of the x, y, and z fillData arrays, " + this.nPointsX + ", " + this.nPointsY + " and " + this.nPointsZ + ", do not match the dimensions of the z fillData matrix, " + var3 + ", " + var2 + " and " + var1);
        } else {
            if (!this.arbprec) {
                this.xBDdata = new BigDecimal[this.nPointsX];
                this.yBDdata = new BigDecimal[this.nPointsY];
                this.zBDdata = new BigDecimal[this.nPointsZ];

                int var10;
                for(var10 = 0; var10 < this.nPointsX; ++var10) {
                    this.xBDdata[var10] = new BigDecimal((new Double(this.xData[var10])).toString());
                }

                for(var10 = 0; var10 < this.nPointsY; ++var10) {
                    this.yBDdata[var10] = new BigDecimal((new Double(this.yData[var10])).toString());
                }

                for(var10 = 0; var10 < this.nPointsZ; ++var10) {
                    this.zBDdata[var10] = new BigDecimal((new Double(this.zData[var10])).toString());
                }
            }

        }
    }

    private double[][][] copy3D(double[][][] var1) {
        int var2 = var1.length;
        int var3 = var1[0].length;
        int var4 = var1[0][0].length;
        double[][][] var5 = new double[var2][var3][var4];

        for(int var6 = 0; var6 < var2; ++var6) {
            for(int var7 = 0; var7 < var3; ++var7) {
                for(int var8 = 0; var8 < var4; ++var8) {
                    var5[var6][var7][var8] = var1[var6][var7][var8];
                }
            }
        }

        return var5;
    }

    private void ascend() {
        boolean var1 = true;
        boolean var2 = true;
        int var3 = 1;

        while(var1) {
            if (this.zData[var3] < this.zData[var3 - 1]) {
                var1 = false;
                var2 = false;
            } else {
                ++var3;
                if (var3 >= this.nPointsZ) {
                    var1 = false;
                }
            }
        }

        ArrayMaths var4;
        int[] var5;
        double[] var6;
        double[][][] var7;
        BigDecimal[] var8;
        BigDecimal[][][] var9;
        int var10;
        int var11;
        int var12;
        if (!var2) {
            var4 = new ArrayMaths(this.zData);
            var4 = var4.sort();
            var5 = var4.originalIndices();
            var6 = new double[this.nPointsZ];
            var7 = new double[this.nPointsZ][this.nPointsY][this.nPointsX];
            var8 = new BigDecimal[this.nPointsZ];
            var9 = (BigDecimal[][][])null;
            if (this.arbprec) {
                var9 = new BigDecimal[this.nPointsZ][this.nPointsY][this.nPointsX];
            }

            for(var10 = 0; var10 < this.nPointsZ; ++var10) {
                var6[var10] = this.zData[var5[var10]];
                var8[var10] = this.zBDdata[var5[var10]];

                for(var11 = 0; var11 < this.nPointsY; ++var11) {
                    for(var12 = 0; var12 < this.nPointsX; ++var12) {
                        var7[var10][var11][var12] = this.vData[var5[var10]][var11][var12];
                        if (this.arbprec) {
                            var9[var10][var11][var12] = this.vBDdata[var5[var10]][var11][var12];
                        }
                    }
                }
            }

            for(var10 = 0; var10 < this.nPointsZ; ++var10) {
                this.zData[var10] = var6[var10];
                this.zBDdata[var10] = var8[var10];

                for(var11 = 0; var11 < this.nPointsY; ++var11) {
                    for(var12 = 0; var12 < this.nPointsX; ++var12) {
                        this.vData[var10][var11][var12] = var7[var10][var11][var12];
                        if (this.arbprec) {
                            this.vBDdata[var10][var11][var12] = var9[var10][var11][var12];
                        }
                    }
                }
            }
        }

        var1 = true;
        var2 = true;
        var3 = 1;

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

        if (!var2) {
            var4 = new ArrayMaths(this.yData);
            var4 = var4.sort();
            var5 = var4.originalIndices();
            var6 = new double[this.nPointsY];
            var7 = new double[this.nPointsZ][this.nPointsY][this.nPointsX];
            var8 = new BigDecimal[this.nPointsY];
            var9 = (BigDecimal[][][])null;
            if (this.arbprec) {
                var9 = new BigDecimal[this.nPointsZ][this.nPointsY][this.nPointsX];
            }

            for(var10 = 0; var10 < this.nPointsY; ++var10) {
                var6[var10] = this.yData[var5[var10]];
                var8[var10] = this.yBDdata[var5[var10]];

                for(var11 = 0; var11 < this.nPointsZ; ++var11) {
                    for(var12 = 0; var12 < this.nPointsX; ++var12) {
                        var7[var11][var10][var12] = this.vData[var11][var5[var10]][var12];
                        if (this.arbprec) {
                            var9[var11][var10][var12] = this.vBDdata[var11][var5[var10]][var12];
                        }
                    }
                }
            }

            for(var10 = 0; var10 < this.nPointsY; ++var10) {
                this.yData[var10] = var6[var10];
                this.yBDdata[var10] = var8[var10];

                for(var11 = 0; var11 < this.nPointsZ; ++var11) {
                    for(var12 = 0; var12 < this.nPointsX; ++var12) {
                        this.vData[var11][var10][var12] = var7[var11][var10][var12];
                        if (this.arbprec) {
                            this.vBDdata[var11][var10][var12] = var9[var11][var10][var12];
                        }
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
            var7 = new double[this.nPointsZ][this.nPointsY][this.nPointsX];
            var8 = new BigDecimal[this.nPointsX];
            var9 = (BigDecimal[][][])null;
            if (this.arbprec) {
                var9 = new BigDecimal[this.nPointsZ][this.nPointsY][this.nPointsX];
            }

            for(var10 = 0; var10 < this.nPointsX; ++var10) {
                var6[var10] = this.xData[var5[var10]];
                var8[var10] = this.xBDdata[var5[var10]];

                for(var11 = 0; var11 < this.nPointsZ; ++var11) {
                    for(var12 = 0; var12 < this.nPointsY; ++var12) {
                        var7[var11][var12][var5[var10]] = this.vData[var11][var12][var5[var10]];
                        if (this.arbprec) {
                            var9[var11][var12][var5[var10]] = this.vBDdata[var11][var12][var5[var10]];
                        }
                    }
                }
            }

            for(var10 = 0; var10 < this.nPointsX; ++var10) {
                this.xData[var10] = var6[var10];
                this.xBDdata[var10] = var8[var10];

                for(var11 = 0; var11 < this.nPointsZ; ++var11) {
                    for(var12 = 0; var12 < this.nPointsY; ++var12) {
                        this.vData[var11][var12][var10] = var7[var11][var12][var10];
                        if (this.arbprec) {
                            this.vBDdata[var11][var12][var10] = var9[var11][var12][var10];
                        }
                    }
                }
            }
        }

    }

    public double[][][] movingAverage(int var1, int var2, int var3) {
        this.lastMethod = 1;
        this.vDataMovAv = new double[this.nPointsZ][this.nPointsY][this.nPointsX];
        this.vDataMovAvBD = new BigDecimal[this.nPointsZ][this.nPointsY][this.nPointsX];
        this.maWindowWidthx = this.windowLength(var1);
        int var4 = (this.maWindowWidthx - 1) / 2;
        this.maWindowWidthy = this.windowLength(var2);
        int var5 = (this.maWindowWidthy - 1) / 2;
        this.maWindowWidthz = this.windowLength(var3);
        int var6 = (this.maWindowWidthz - 1) / 2;
        boolean var7 = false;
        boolean var8 = false;
        boolean var9 = false;
        boolean var10 = false;
        boolean var11 = false;
        boolean var12 = false;

        for(int var13 = 0; var13 < this.nPointsX; ++var13) {
            int var24;
            if (var13 >= var4) {
                var24 = var13 - var4;
            } else {
                var24 = 0;
            }

            int var25;
            if (var13 <= this.nPointsX - var4 - 1) {
                var25 = var13 + var4;
            } else {
                var25 = this.nPointsX - 1;
            }

            int var14 = var25 - var24 + 1;

            for(int var15 = 0; var15 < this.nPointsY; ++var15) {
                int var26;
                if (var15 >= var5) {
                    var26 = var15 - var5;
                } else {
                    var26 = 0;
                }

                int var27;
                if (var15 <= this.nPointsY - var5 - 1) {
                    var27 = var15 + var5;
                } else {
                    var27 = this.nPointsY - 1;
                }

                int var16 = var27 - var26 + 1;

                for(int var17 = 0; var17 < this.nPointsZ; ++var17) {
                    int var28;
                    if (var17 >= var6) {
                        var28 = var17 - var6;
                    } else {
                        var28 = 0;
                    }

                    int var29;
                    if (var17 <= this.nPointsZ - var5 - 1) {
                        var29 = var17 + var6;
                    } else {
                        var29 = this.nPointsZ - 1;
                    }

                    int var18 = var29 - var28 + 1;
                    int var21;
                    int var22;
                    if (this.arbprec) {
                        BigDecimal var30 = new BigDecimal("0.0");

                        for(int var20 = var24; var20 <= var25; ++var20) {
                            for(var21 = var26; var21 <= var27; ++var21) {
                                for(var22 = var28; var22 <= var29; ++var22) {
                                    var30 = var30.add(this.vBDdata[var22][var21][var20]);
                                }
                            }
                        }

                        String var31 = (new Integer(var14 * var16 + var18)).toString();
                        this.vDataMovAvBD[var17][var15][var13] = var30.divide(new BigDecimal(var31), 4);
                        this.vDataMovAv[var17][var15][var13] = this.vDataMovAvBD[var17][var15][var13].doubleValue();
                    } else {
                        double var19 = 0.0D;

                        for(var21 = var24; var21 <= var25; ++var21) {
                            for(var22 = var26; var22 <= var27; ++var22) {
                                for(int var23 = var28; var23 <= var29; ++var23) {
                                    var19 += this.vData[var23][var22][var21];
                                }
                            }
                        }

                        this.vDataMovAv[var17][var15][var13] = var19 / (double)(var14 * var16 * var18);
                        String var32 = (new Double(this.vDataMovAv[var17][var15][var13])).toString();
                        this.vDataMovAvBD[var17][var15][var13] = new BigDecimal(var32);
                    }
                }
            }
        }

        this.tcsMovAv = new TriCubicSpline(this.zData, this.yData, this.xData, this.vDataMovAv);
        this.calcMovAv = true;
        return Conv.copy(this.vDataMovAv);
    }

    public double[][][] movingAverage(int var1) {
        return this.movingAverage(var1, var1, var1);
    }

    public BigDecimal[][][] movingAverageAsBigDecimal(int var1, int var2, int var3) {
        this.movingAverage(var1, var2, var1);
        return Conv.copy(this.vDataMovAvBD);
    }

    public BigDecimal[][][] movingAverageAsBigDecimal(int var1) {
        this.movingAverage(var1, var1, var1);
        return Conv.copy(this.vDataMovAvBD);
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

    public double[][][] savitzkyGolay(int var1, int var2, int var3) {
        this.lastMethod = 2;
        this.vDataSavGol = new double[this.nPointsZ][this.nPointsY][this.nPointsX];
        this.sgWindowWidthx = this.windowLength(var1);
        this.sgWindowWidthy = this.windowLength(var2);
        this.sgWindowWidthz = this.windowLength(var3);
        this.savitzkyGolayCommon(this.sgWindowWidthx, this.sgWindowWidthy, this.sgWindowWidthz);
        this.tcsSavGol = new TriCubicSpline(this.zData, this.yData, this.xData, Conv.copy(this.vDataSavGol));
        this.calcSavGol = true;
        return Conv.copy(this.vDataSavGol);
    }

    public double[][][] savitzkyGolay(int var1) {
        return this.savitzkyGolay(var1, var1, var1);
    }

    private double[][][] savitzkyGolayCommon(int var1, int var2, int var3) {
        int var4 = (var1 - 1) / 2;
        int var5 = (var2 - 1) / 2;
        int var6 = (var3 - 1) / 2;
        double[] var7 = this.savitzkyGolayFilter(var4, var4, var5, var5, var6, var6)[0];
        double[][][] var8 = this.padData(this.vData, var4, var5, var6);

        for(int var9 = var6; var9 < this.nPointsZ + var6; ++var9) {
            for(int var10 = var5; var10 < this.nPointsY + var5; ++var10) {
                for(int var11 = var4; var11 < this.nPointsX + var4; ++var11) {
                    double var12 = 0.0D;
                    int var14 = 0;

                    for(int var15 = var9 - var6; var15 <= var9 + var6; ++var15) {
                        for(int var16 = var10 - var5; var16 <= var10 + var5; ++var16) {
                            for(int var17 = var11 - var4; var17 <= var11 + var4; ++var17) {
                                var12 += var8[var15][var16][var17] * var7[var14++];
                            }
                        }
                    }

                    this.vDataSavGol[var9 - var6][var10 - var5][var11 - var4] = var12;
                }
            }
        }

        return this.vDataSavGol;
    }

    private double[][][] padData(double[][][] var1, int var2, int var3, int var4) {
        int var5 = var1.length;
        int var6 = var1[0].length;
        int var7 = var1[0][0].length;
        double[][][] var8 = new double[var5 + 2 * var4][var6 + 2 * var3][var7 + 2 * var2];

        int var9;
        int var10;
        int var11;
        for(var9 = 0; var9 < var5; ++var9) {
            for(var10 = 0; var10 < var6; ++var10) {
                for(var11 = 0; var11 < var7; ++var11) {
                    var8[var9 + var4][var10 + var3][var11 + var2] = var1[var9][var10][var11];
                }
            }
        }

        for(var9 = 0; var9 < var2; ++var9) {
            for(var10 = var3; var10 < var6 + var3; ++var10) {
                for(var11 = var4; var11 < var5 + var4; ++var11) {
                    var8[var11][var10][var9] = var8[var11][var10][var2];
                }
            }
        }

        for(var9 = var7 + var2; var9 < var7 + 2 * var2; ++var9) {
            for(var10 = var3; var10 < var6 + var3; ++var10) {
                for(var11 = var4; var11 < var5 + var4; ++var11) {
                    var8[var11][var10][var9] = var8[var11][var10][var7 + var2 - 1];
                }
            }
        }

        for(var9 = 0; var9 < var3; ++var9) {
            for(var10 = var2; var10 < var7 + var2; ++var10) {
                for(var11 = var4; var11 < var5 + var4; ++var11) {
                    var8[var11][var9][var10] = var8[var11][var3][var10];
                }
            }
        }

        for(var9 = var6 + var3; var9 < var6 + 2 * var3; ++var9) {
            for(var10 = var2; var10 < var7 + var2; ++var10) {
                for(var11 = var4; var11 < var5 + var4; ++var11) {
                    var8[var11][var9][var10] = var8[var11][var6 + var3 - 1][var10];
                }
            }
        }

        for(var9 = 0; var9 < var4; ++var9) {
            for(var10 = var2; var10 < var7 + var2; ++var10) {
                for(var11 = var3; var11 < var6 + var3; ++var11) {
                    var8[var9][var11][var10] = var8[var4][var11][var10];
                }
            }
        }

        for(var9 = var5 + var4; var9 < var5 + 2 * var4; ++var9) {
            for(var10 = var2; var10 < var7 + var2; ++var10) {
                for(var11 = var3; var11 < var6 + var3; ++var11) {
                    var8[var9][var11][var10] = var8[var5 + var4 - 1][var11][var10];
                }
            }
        }

        for(var9 = 0; var9 < var4; ++var9) {
            for(var10 = 0; var10 < var3; ++var10) {
                for(var11 = 0; var11 < var2; ++var11) {
                    var8[var9][var10][var11] = var8[var4][var3][var2];
                }
            }
        }

        for(var9 = var5 + var4; var9 < var5 + 2 * var4; ++var9) {
            for(var10 = 0; var10 < var3; ++var10) {
                for(var11 = 0; var11 < var2; ++var11) {
                    var8[var9][var10][var11] = var8[var5 + var4 - 1][var3][var2];
                }
            }
        }

        for(var9 = 0; var9 < var4; ++var9) {
            for(var10 = var6 + var3; var10 < var6 + 2 * var3; ++var10) {
                for(var11 = 0; var11 < var2; ++var11) {
                    var8[var9][var10][var11] = var8[var4][var6 + var3 - 1][var2];
                }
            }
        }

        for(var9 = 0; var9 < var4; ++var9) {
            for(var10 = 0; var10 < var3; ++var10) {
                for(var11 = var7 + var2; var11 < var7 + 2 * var2; ++var11) {
                    var8[var9][var10][var11] = var8[var4][var3][var7 + var2 - 1];
                }
            }
        }

        for(var9 = var5 + var4; var9 < var5 + 2 * var4; ++var9) {
            for(var10 = var6 + var3; var10 < var6 + 2 * var3; ++var10) {
                for(var11 = var7 + var2; var11 < var7 + 2 * var2; ++var11) {
                    var8[var9][var10][var11] = var8[var5 + var4][var6 + var3][var7 + var2];
                }
            }
        }

        for(var9 = 0; var9 < var4; ++var9) {
            for(var10 = var6 + var3; var10 < var6 + 2 * var3; ++var10) {
                for(var11 = var7 + var2; var11 < var7 + 2 * var2; ++var11) {
                    var8[var9][var10][var11] = var8[var4][var6 + var3][var7 + var2];
                }
            }
        }

        for(var9 = var5 + var4; var9 < var5 + 2 * var4; ++var9) {
            for(var10 = 0; var10 < var3; ++var10) {
                for(var11 = var7 + var2; var11 < var7 + 2 * var2; ++var11) {
                    var8[var9][var10][var11] = var8[var5 + var4][var3][var7 + var2];
                }
            }
        }

        for(var9 = var5 + var4; var9 < var5 + 2 * var4; ++var9) {
            for(var10 = var6 + var3; var10 < var6 + 2 * var3; ++var10) {
                for(var11 = 0; var11 < var2; ++var11) {
                    var8[var9][var10][var11] = var8[var5 + var4][var6 + var3][var2];
                }
            }
        }

        return var8;
    }

    public double[][][][] savitzkyGolay(int var1, int var2, int var3, int var4, int var5, int var6) {
        if (var5 + var4 + var6 > this.sgPolyDeg) {
            throw new IllegalArgumentException("The sum of the derivative orders " + var4 + " plus " + var5 + " plus " + var6 + ", must be less than or equal to the polynomial degree, " + this.sgPolyDeg + ".");
        } else {
            this.lastMethod = 2;
            double[][][][] var7 = new double[2][this.nPointsZ][this.nPointsY][this.nPointsX];
            this.sgWindowWidthx = this.windowLength(var1);
            int var8 = (this.sgWindowWidthx - 1) / 2;
            this.sgWindowWidthy = this.windowLength(var2);
            int var9 = (this.sgWindowWidthy - 1) / 2;
            this.sgWindowWidthz = this.windowLength(var3);
            int var10 = (this.sgWindowWidthz - 1) / 2;
            if (!this.calcSavGol) {
                this.savitzkyGolay(var1, var2, var3);
            }

            var7[0] = this.vDataSavGol;
            int var11 = 0;
            boolean var12 = true;
            int var13 = this.sgCoeffIndices.length;

            while(var12) {
                if (this.sgCoeffIndices[var11][0] == var4 && this.sgCoeffIndices[var11][1] == var5) {
                    var12 = false;
                }

                ++var11;
                if (var11 >= var13) {
                    throw new IllegalArgumentException("It should not have been possible to reach this situation, m = " + var4 + ", n = " + var5);
                }
            }

            double[] var14 = this.sgArrayC[var11];
            double[][][] var15 = this.padData(this.vData, var8, var9, var10);

            for(int var16 = var10; var16 < this.nPointsZ + var10; ++var16) {
                for(int var17 = var9; var17 < this.nPointsY + var9; ++var17) {
                    for(int var18 = var8; var18 < this.nPointsX + var8; ++var18) {
                        double var19 = 0.0D;
                        int var21 = 0;

                        for(int var22 = var16 - var10; var22 <= var16 + var10; ++var22) {
                            for(int var23 = var17 - var9; var23 <= var17 + var9; ++var23) {
                                for(int var24 = var18 - var8; var24 <= var18 + var8; ++var24) {
                                    var19 += var15[var22][var23][var24] * var14[var21++];
                                }
                            }
                        }

                        var7[1][var16 - var10][var17 - var9][var18 - var8] = var19;
                    }
                }
            }

            this.derivSavGol = var7[1];
            this.nthSet = true;
            return var7;
        }
    }

    public double[][] savitzkyGolayFilter(int var1, int var2, int var3, int var4, int var5, int var6) {
        int var7 = var1 + var2 + 1;
        int var8 = var3 + var4 + 1;
        int var9 = var5 + var6 + 1;
        int var10 = var7 * var8 * var9;
        double[] var11 = new double[var10];
        int[][] var12 = new int[var10][3];
        int var13 = 0;

        int var15;
        int var16;
        for(int var14 = 0; var14 < var7; ++var14) {
            for(var15 = 0; var15 < var8; ++var15) {
                for(var16 = 0; var16 < var9; ++var16) {
                    var12[var13][0] = var14 - var1;
                    var12[var13][1] = var15 - var3;
                    var12[var13++][2] = var16 - var5;
                }
            }
        }

        double[][] var20 = new double[var10][this.nSGcoeff];

        for(var15 = 0; var15 < var10; ++var15) {
            for(var16 = 0; var16 < this.nSGcoeff; ++var16) {
                var20[var15][var16] = Math.pow((double)var12[var15][0], (double)this.sgCoeffIndices[var16][0]) * Math.pow((double)var12[var15][1], (double)this.sgCoeffIndices[var16][1]) * Math.pow((double)var12[var15][2], (double)this.sgCoeffIndices[var16][2]);
            }
        }

        Matrix var21 = new Matrix(var20);
        Matrix var22 = var21.transpose();
        Matrix var17 = var22.times(var21);
        Matrix var18 = var17.inverse();
        Matrix var19 = var18.times(var22);
        this.sgArrayC = var19.getArrayCopy();
        return this.sgArrayC;
    }

    public static double[][] savitzkyGolayFilter(int var0, int var1, int var2, int var3, int var4, int var5, int var6) {
        ThreeDimensionalSmooth var7 = new ThreeDimensionalSmooth();
        var7.setSGpolyDegree(var6);
        return var7.savitzkyGolayFilter(var0, var1, var2, var3, var4, var5);
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
        ThreeDimensionalSmooth var1 = new ThreeDimensionalSmooth();
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

    public double[][][] getMovingAverageValues() {
        if (!this.calcMovAv) {
            throw new IllegalArgumentException("No moving average smoothing method has been called");
        } else {
            return Conv.copy(this.vDataMovAv);
        }
    }

    public BigDecimal[][][] getMovingAverageValuesAsBigDecimal() {
        if (!this.calcMovAv) {
            throw new IllegalArgumentException("No moving average smoothing method has been called");
        } else {
            return Conv.copy(this.vDataMovAvBD);
        }
    }

    public double[][][] getSavitzkyGolaySmoothedValues() {
        if (!this.calcSavGol) {
            throw new IllegalArgumentException("No Savitzky-Golay smoothing method has been called");
        } else {
            return Conv.copy(this.vDataSavGol);
        }
    }

    public double[][][] getSavitzkyDerivatives() {
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
            this.extentMovAv = this.extent(this.vData, this.vDataMovAv);
            return this.extentMovAv;
        }
    }

    public double extentSavitzkyGolay() {
        if (!this.calcSavGol) {
            throw new IllegalArgumentException("No Savitzky-Golay smoothing method has been called");
        } else {
            this.extentSavGol = this.extent(this.vData, this.vDataSavGol);
            return this.extentSavGol;
        }
    }

    private double extent(double[][][] var1, double[][][] var2) {
        ArrayMaths var3 = new ArrayMaths(var1);
        double var4 = var3.getMinimum();
        double var6 = var3.getMaximum();
        double var8 = var6 - var4;
        double var10 = 0.0D;

        for(int var12 = 0; var12 < this.nPointsX; ++var12) {
            for(int var13 = 0; var13 < this.nPointsY; ++var13) {
                for(int var14 = 0; var14 < this.nPointsZ; ++var14) {
                    var10 += Math.abs(var1[var14][var13][var12] - var2[var14][var13][var12]) / var8;
                }
            }
        }

        var10 /= (double)this.nPoints;
        return var10;
    }

    public double interpolateSavitzkyGolay(double var1, double var3, double var5) {
        if (!this.calcSavGol) {
            throw new IllegalArgumentException("No Savitzky-Golay smoothing method has been called");
        } else {
            return this.tcsSavGol.interpolate(var5, var3, var1);
        }
    }

    public double interpolateMovingAverage(double var1, double var3, double var5) {
        if (!this.calcMovAv) {
            throw new IllegalArgumentException("No moving average smoothing method has been called");
        } else {
            return this.tcsMovAv.interpolate(var5, var3, var1);
        }
    }

    public void plotSavitzkyGolayX(int var1, int var2) {
        if (!this.calcSavGol) {
            throw new IllegalArgumentException("No Savitzky-Golay smoothing method has been called");
        } else if (var1 >= this.nPointsY) {
            throw new IllegalArgumentException("The index, " + var1 + ", must be less than the number of y values, " + this.nPointsY);
        } else if (var2 >= this.nPointsZ) {
            throw new IllegalArgumentException("The index, " + var2 + ", must be less than the number of y values, " + this.nPointsZ);
        } else {
            byte var3 = 0;
            double var4 = Fmath.truncate(this.yData[var1], this.trunc);
            double var6 = Fmath.truncate(this.zData[var2], this.trunc);
            this.commonPlot(var3, var1, var2, var4, var6);
        }
    }

    public void plotSavitzkyGolayX(double var1, double var3) {
        if (!this.calcSavGol) {
            throw new IllegalArgumentException("No Savitzky-Golay smoothing method has been called");
        } else {
            byte var5 = 0;
            int var6 = this.findValue(this.yData, var1);
            var1 = Fmath.truncate(this.yData[var6], this.trunc);
            int var7 = this.findValue(this.zData, var3);
            var3 = Fmath.truncate(this.zData[var7], this.trunc);
            this.commonPlot(var5, var6, var7, var1, var3);
        }
    }

    public void plotSavitzkyGolayY(int var1, int var2) {
        if (!this.calcSavGol) {
            throw new IllegalArgumentException("No Savitzky-Golay smoothing method has been called");
        } else if (var2 >= this.nPointsZ) {
            throw new IllegalArgumentException("The index, " + var2 + ", must be less than the number of y values, " + this.nPointsZ);
        } else if (var1 >= this.nPointsX) {
            throw new IllegalArgumentException("The index, " + var1 + ", must be less than the number of x values, " + this.nPointsX);
        } else {
            byte var3 = 1;
            double var4 = Fmath.truncate(this.yData[var2], this.trunc);
            double var6 = Fmath.truncate(this.xData[var1], this.trunc);
            this.commonPlot(var3, var1, var2, var6, var4);
        }
    }

    public void plotSavitzkyGolayY(double var1, double var3) {
        if (!this.calcSavGol) {
            throw new IllegalArgumentException("No Savitzky-Golay smoothing method has been called");
        } else {
            byte var5 = 1;
            int var6 = this.findValue(this.xData, var1);
            var1 = Fmath.truncate(this.xData[var6], this.trunc);
            int var7 = this.findValue(this.zData, var3);
            var3 = Fmath.truncate(this.zData[var7], this.trunc);
            this.commonPlot(var5, var6, var7, var1, var3);
        }
    }

    public void plotSavitzkyGolayZ(int var1, int var2) {
        if (!this.calcSavGol) {
            throw new IllegalArgumentException("No Savitzky-Golay smoothing method has been called");
        } else if (var2 >= this.nPointsY) {
            throw new IllegalArgumentException("The index, " + var2 + ", must be less than the number of y values, " + this.nPointsY);
        } else if (var1 >= this.nPointsX) {
            throw new IllegalArgumentException("The index, " + var1 + ", must be less than the number of x values, " + this.nPointsX);
        } else {
            byte var3 = 2;
            double var4 = Fmath.truncate(this.yData[var2], this.trunc);
            double var6 = Fmath.truncate(this.xData[var1], this.trunc);
            this.commonPlot(var3, var1, var2, var6, var4);
        }
    }

    public void plotSavitzkyGolayZ(double var1, double var3) {
        if (!this.calcSavGol) {
            throw new IllegalArgumentException("No Savitzky-Golay smoothing method has been called");
        } else {
            byte var5 = 2;
            int var6 = this.findValue(this.xData, var1);
            var1 = Fmath.truncate(this.xData[var6], this.trunc);
            int var7 = this.findValue(this.yData, var3);
            var3 = Fmath.truncate(this.yData[var7], this.trunc);
            this.commonPlot(var5, var6, var7, var1, var3);
        }
    }

    public void plotMovingAverageX(int var1, int var2) {
        if (!this.calcSavGol) {
            throw new IllegalArgumentException("No moving average smoothing method has been called");
        } else if (var1 >= this.nPointsY) {
            throw new IllegalArgumentException("The index, " + var1 + ", must be less than the number of y values, " + this.nPointsY);
        } else if (var2 >= this.nPointsZ) {
            throw new IllegalArgumentException("The index, " + var2 + ", must be less than the number of z values, " + this.nPointsZ);
        } else {
            byte var3 = 3;
            double var4 = Fmath.truncate(this.yData[var1], this.trunc);
            double var6 = Fmath.truncate(this.zData[var2], this.trunc);
            this.commonPlot(var3, var1, var2, var4, var6);
        }
    }

    public void plotMovingAverageX(double var1, double var3) {
        if (!this.calcMovAv) {
            throw new IllegalArgumentException("No moving average smoothing method has been called");
        } else {
            byte var5 = 3;
            int var6 = this.findValue(this.yData, var1);
            var1 = Fmath.truncate(this.yData[var6], this.trunc);
            int var7 = this.findValue(this.zData, var3);
            var3 = Fmath.truncate(this.zData[var7], this.trunc);
            this.commonPlot(var5, var6, var7, var1, var3);
        }
    }

    public void plotMovingAverageY(int var1, int var2) {
        if (!this.calcMovAv) {
            throw new IllegalArgumentException("No moving average smoothing method has been called");
        } else if (var2 >= this.nPointsY) {
            throw new IllegalArgumentException("The index, " + var2 + ", must be less than the number of y values, " + this.nPointsZ);
        } else if (var1 >= this.nPointsX) {
            throw new IllegalArgumentException("The index, " + var1 + ", must be less than the number of x values, " + this.nPointsX);
        } else {
            byte var3 = 4;
            double var4 = Fmath.truncate(this.yData[var2], this.trunc);
            double var6 = Fmath.truncate(this.yData[var1], this.trunc);
            this.commonPlot(var3, var1, var2, var6, var4);
        }
    }

    public void plotMovingAverageY(double var1, double var3) {
        if (!this.calcMovAv) {
            throw new IllegalArgumentException("No moving average smoothing method has been called");
        } else {
            byte var5 = 4;
            int var6 = this.findValue(this.xData, var1);
            var1 = Fmath.truncate(this.xData[var6], this.trunc);
            int var7 = this.findValue(this.zData, var3);
            var3 = Fmath.truncate(this.zData[var7], this.trunc);
            this.commonPlot(var5, var6, var7, var1, var3);
        }
    }

    public void plotMovingAverageZ(int var1, int var2) {
        if (!this.calcMovAv) {
            throw new IllegalArgumentException("No moving average smoothing method has been called");
        } else if (var2 >= this.nPointsY) {
            throw new IllegalArgumentException("The index, " + var2 + ", must be less than the number of y values, " + this.nPointsY);
        } else if (var1 >= this.nPointsX) {
            throw new IllegalArgumentException("The index, " + var1 + ", must be less than the number of x values, " + this.nPointsX);
        } else {
            byte var3 = 5;
            double var4 = Fmath.truncate(this.yData[var2], this.trunc);
            double var6 = Fmath.truncate(this.yData[var1], this.trunc);
            this.commonPlot(var3, var1, var2, var6, var4);
        }
    }

    public void plotMovingAverageZ(double var1, double var3) {
        if (!this.calcMovAv) {
            throw new IllegalArgumentException("No moving avrage smoothing method has been called");
        } else {
            byte var5 = 5;
            int var6 = this.findValue(this.xData, var1);
            var1 = Fmath.truncate(this.xData[var6], this.trunc);
            int var7 = this.findValue(this.yData, var3);
            var3 = Fmath.truncate(this.yData[var7], this.trunc);
            this.commonPlot(var5, var6, var7, var1, var3);
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
                    throw new IllegalArgumentException("The entered plot value, " + var2 + ",  must equal an entered fillData value");
                }
            }
        }

        return var6;
    }

    private void commonPlot(int var1, int var2, int var3, double var4, double var6) {
        String var8;
        String var9;
        String var11;
        String var12;
        double[][] var16;
        double[] var17;
        double[] var18;
        double[] var19;
        double[] var20;
        var8 = null;
        var9 = null;
        String var10 = ",  Original fillData - circles,  Smoothed fillData - squares";
        var11 = null;
        var12 = null;
        int[] var13 = new int[]{0, this.nPointsX / 4, this.nPointsX / 2, 3 * this.nPointsX / 4, this.nPointsX - 1};
        int[] var14 = new int[]{0, this.nPointsY / 4, this.nPointsY / 2, 3 * this.nPointsY / 4, this.nPointsY - 1};
        int[] var15 = new int[]{0, this.nPointsZ / 4, this.nPointsZ / 2, 3 * this.nPointsZ / 4, this.nPointsZ - 1};
        var16 = new double[8][];
        var17 = new double[5];
        var18 = new double[5];
        var19 = new double[5];
        var20 = new double[5];
        double[] var21 = new double[5];
        double[] var22 = new double[5];
        int var23;
        label95:
        switch(var1) {
            case 0:
                var8 = "Savitzky-Golay smoothing with an x by y by z window of " + this.maWindowWidthx + " x " + this.maWindowWidthy + " x " + this.maWindowWidthz + " points";
                var9 = "Plot of v versus x values for a y value of " + var4 + " a z value of " + var6 + var10;
                var11 = "x values";
                var12 = "v values";
                var16[0] = this.xData;
                var16[1] = this.vData[var3][var2];
                var16[2] = this.xData;
                var16[3] = this.vDataSavGol[var3][var2];
                var23 = 0;

                while(true) {
                    if (var23 >= 5) {
                        break label95;
                    }

                    var17[var23] = this.xData[var13[var23]];
                    var19[var23] = this.vData[var3][var2][var13[var23]];
                    var18[var23] = this.xData[var13[var23]];
                    var20[var23] = this.vDataSavGol[var3][var2][var13[var23]];
                    ++var23;
                }
            case 1:
                var8 = "Savitzky-Golay smoothing with an x by y by z window of " + this.maWindowWidthx + " x " + this.maWindowWidthy + " x " + this.maWindowWidthz + " points";
                var9 = "Plot of v versus y values for a x value of " + var4 + " a z value of " + var6 + var10;
                var11 = "y values";
                var12 = "v values";
                var16[0] = this.yData;
                var16[2] = this.yData;
                var16[1] = new double[this.nPointsY];
                var16[3] = new double[this.nPointsY];

                for(var23 = 0; var23 < this.nPointsZ; ++var23) {
                    var16[1][var23] = this.vData[var3][var23][var2];
                    var16[3][var23] = this.vDataSavGol[var3][var23][var2];
                }

                var23 = 0;

                while(true) {
                    if (var23 >= 5) {
                        break label95;
                    }

                    var17[var23] = this.yData[var14[var23]];
                    var19[var23] = var16[1][var14[var23]];
                    var18[var23] = this.yData[var14[var23]];
                    var20[var23] = var16[3][var14[var23]];
                    ++var23;
                }
            case 2:
                var8 = "Savitzky-Golay smoothing with an x by y by z window of " + this.maWindowWidthx + " x " + this.maWindowWidthy + " x " + this.maWindowWidthz + " points";
                var9 = "Plot of v versus z values for a x value of " + var4 + " a y value of " + var6 + var10;
                var11 = "z values";
                var12 = "v values";
                var16[0] = this.zData;
                var16[2] = this.zData;
                var16[1] = new double[this.nPointsZ];
                var16[3] = new double[this.nPointsZ];

                for(var23 = 0; var23 < this.nPointsZ; ++var23) {
                    var16[1][var23] = this.vData[var23][var3][var2];
                    var16[3][var23] = this.vDataSavGol[var23][var3][var2];
                }

                var23 = 0;

                while(true) {
                    if (var23 >= 5) {
                        break label95;
                    }

                    var17[var23] = this.zData[var15[var23]];
                    var19[var23] = var16[1][var15[var23]];
                    var18[var23] = this.zData[var15[var23]];
                    var20[var23] = var16[3][var15[var23]];
                    ++var23;
                }
            case 3:
                var8 = "Moving average smoothing with an x by y by z window of " + this.maWindowWidthx + " x " + this.maWindowWidthy + " x " + this.maWindowWidthz + " points";
                var9 = "Plot of v versus x values for a y value of " + var4 + " a z value of " + var6 + var10;
                var11 = "x values";
                var12 = "v values";
                var16[0] = this.xData;
                var16[1] = this.vData[var3][var2];
                var16[2] = this.xData;
                var16[3] = this.vDataMovAv[var3][var2];
                var23 = 0;

                while(true) {
                    if (var23 >= 5) {
                        break label95;
                    }

                    var17[var23] = this.xData[var13[var23]];
                    var19[var23] = this.vData[var3][var2][var13[var23]];
                    var18[var23] = this.xData[var13[var23]];
                    var20[var23] = this.vDataMovAv[var3][var2][var13[var23]];
                    ++var23;
                }
            case 4:
                var8 = "Moving average smoothing with an x by y by z window of " + this.maWindowWidthx + " x " + this.maWindowWidthy + " x " + this.maWindowWidthz + " points";
                var9 = "Plot of v versus y values for a x value of " + var4 + " a z value of " + var6 + var10;
                var11 = "y values";
                var12 = "v values";
                var16[0] = this.yData;
                var16[2] = this.yData;
                var16[1] = new double[this.nPointsY];
                var16[3] = new double[this.nPointsY];

                for(var23 = 0; var23 < this.nPointsZ; ++var23) {
                    var16[1][var23] = this.vData[var3][var23][var2];
                    var16[3][var23] = this.vDataMovAv[var3][var23][var2];
                }

                var23 = 0;

                while(true) {
                    if (var23 >= 5) {
                        break label95;
                    }

                    var17[var23] = this.yData[var14[var23]];
                    var19[var23] = var16[1][var14[var23]];
                    var18[var23] = this.yData[var14[var23]];
                    var20[var23] = var16[3][var14[var23]];
                    ++var23;
                }
            case 5:
                var8 = "Moving average smoothing with an x by y by z window of " + this.maWindowWidthx + " x " + this.maWindowWidthy + " x " + this.maWindowWidthz + " points";
                var9 = "Plot of v versus z values for a x value of " + var4 + " a y value of " + var6 + var10;
                var11 = "z values";
                var12 = "v values";
                var16[0] = this.yData;
                var16[2] = this.yData;
                var16[1] = new double[this.nPointsZ];
                var16[3] = new double[this.nPointsZ];

                for(var23 = 0; var23 < this.nPointsZ; ++var23) {
                    var16[1][var23] = this.vData[var23][var3][var2];
                    var16[3][var23] = this.vDataMovAv[var23][var3][var2];
                }

                for(var23 = 0; var23 < 5; ++var23) {
                    var17[var23] = this.yData[var14[var23]];
                    var19[var23] = var16[1][var14[var23]];
                    var18[var23] = this.yData[var14[var23]];
                    var20[var23] = var16[3][var14[var23]];
                }
        }

        var16[4] = var17;
        var16[5] = var19;
        var16[6] = var18;
        var16[7] = var20;
        PlotGraph var26 = new PlotGraph(var16);
        int[] var24 = new int[]{0, 0, 1, 2};
        int[] var25 = new int[]{3, 3, 0, 0};
        var26.setPoint(var24);
        var26.setLine(var25);
        var26.setGraphTitle(var8);
        var26.setGraphTitle2(var9);
        var26.setXaxisLegend(var11);
        var26.setYaxisLegend(var12);
        var26.plot();
    }
}
