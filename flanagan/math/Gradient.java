//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package flanagan.math;

import flanagan.interpolation.BiCubicSplineFirstDerivative;
import flanagan.interpolation.CubicSpline;
import java.util.ArrayList;

public class Gradient {
    private double[] xArray = null;
    private double[] yArray = null;
    private int[] xSampling = null;
    private int[] ySampling = null;
    private double[] fArray1d = null;
    private double[][] fArray2d = (double[][])null;
    private int xSamplingStart = 0;
    private int ySamplingStart = 0;
    private int xSamplingEnd = 0;
    private int ySamplingEnd = 0;
    private int xSamplingPeriod = 1;
    private int ySamplingPeriod = 1;
    private boolean sampled = false;
    private ArrayList<Object> numDiffArray = new ArrayList();
    private double[] ndGrad1 = null;
    private double[][] ndGrad2_X = (double[][])null;
    private double[][] ndGrad2_Y = (double[][])null;
    private boolean numArrayDone = false;
    private int nDim = 0;
    private int xLength = 0;
    private int yLength = 0;
    private int xLengthS = 0;
    private int yLengthS = 0;
    private ArrayList<Object> splineDerivArray = new ArrayList();
    private ArrayList<Object> splineSecondDerivArray = new ArrayList();
    private double[] csGrad1F = null;
    private double[] csGrad1 = null;
    private double[][] bcsGrad2_XF = (double[][])null;
    private double[][] bcsGrad2_X = (double[][])null;
    private double[][] bcsGrad2_YF = (double[][])null;
    private double[][] bcsGrad2_Y = (double[][])null;
    private double[] cs2Grad1 = null;
    private double[][] bcs2Grad2_X2 = (double[][])null;
    private double[][] bcs2Grad2_Y2 = (double[][])null;
    private double[][] bcs2Grad2_XY = (double[][])null;
    private CubicSpline cs = null;
    private BiCubicSplineFirstDerivative bcs = null;
    private boolean splineDone = false;
    private CubicSpline cs2 = null;
    private BiCubicSplineFirstDerivative bcs2x = null;
    private BiCubicSplineFirstDerivative bcs2y = null;
    private boolean spline2Done = false;

    public Gradient(double[] var1, double[] var2) {
        this.xLength = var2.length;
        if (var1.length != this.xLength) {
            throw new IllegalArgumentException("x length, " + var1.length + ", does not match corresponding f length, " + this.xLength);
        } else {
            this.nDim = 1;
            this.xLengthS = this.xLength;
            this.xArray = Conv.copy(var1);
            this.fArray1d = Conv.copy(var2);
            this.xSampling = new int[this.xLengthS];

            for(int var3 = 0; var3 < this.xLengthS; this.xSampling[var3] = var3++) {
                ;
            }

            this.xSamplingStart = 0;
            this.xSamplingEnd = this.xLengthS - 1;
            this.xSamplingPeriod = 1;
            this.sampled = false;
        }
    }

    public Gradient(float[] var1, float[] var2) {
        this.xLength = var2.length;
        if (var1.length != this.xLength) {
            throw new IllegalArgumentException("x length, " + var1.length + ", does not match corresponding f length, " + this.xLength);
        } else {
            this.nDim = 1;
            this.xLengthS = this.xLength;
            float[] var3 = Conv.copy(var1);
            float[] var4 = Conv.copy(var2);
            this.xArray = new double[this.xLength];
            this.fArray1d = new double[this.xLength];

            int var5;
            for(var5 = 0; var5 < this.xLength; ++var5) {
                this.xArray[var5] = Conv.convert_float_to_double(var3[var5]);
                this.fArray1d[var5] = Conv.convert_float_to_double(var4[var5]);
            }

            this.xSampling = new int[this.xLengthS];

            for(var5 = 0; var5 < this.xLengthS; this.xSampling[var5] = var5++) {
                ;
            }

            this.xSamplingStart = 0;
            this.xSamplingEnd = this.xLengthS - 1;
            this.xSamplingPeriod = 1;
            this.sampled = false;
        }
    }

    public Gradient(long[] var1, long[] var2) {
        this.xLength = var2.length;
        if (var1.length != this.xLength) {
            throw new IllegalArgumentException("x length, " + var1.length + ", does not match corresponding f length, " + this.xLength);
        } else {
            this.nDim = 1;
            this.xLengthS = this.xLength;
            long[] var3 = Conv.copy(var1);
            long[] var4 = Conv.copy(var2);
            this.xArray = new double[this.xLength];
            this.fArray1d = new double[this.xLength];

            int var5;
            for(var5 = 0; var5 < this.xLength; ++var5) {
                this.xArray[var5] = Conv.convert_long_to_double(var3[var5]);
                this.fArray1d[var5] = Conv.convert_long_to_double(var4[var5]);
            }

            this.xSampling = new int[this.xLengthS];

            for(var5 = 0; var5 < this.xLengthS; this.xSampling[var5] = var5++) {
                ;
            }

            this.xSamplingStart = 0;
            this.xSamplingEnd = this.xLengthS - 1;
            this.xSamplingPeriod = 1;
            this.sampled = false;
        }
    }

    public Gradient(int[] var1, int[] var2) {
        this.xLength = var2.length;
        if (var1.length != this.xLength) {
            throw new IllegalArgumentException("x length, " + var1.length + ", does not match corresponding f length, " + this.xLength);
        } else {
            this.nDim = 1;
            this.xLengthS = this.xLength;
            int[] var3 = Conv.copy(var1);
            int[] var4 = Conv.copy(var2);
            this.xArray = new double[this.xLength];
            this.fArray1d = new double[this.xLength];

            int var5;
            for(var5 = 0; var5 < this.xLength; ++var5) {
                this.xArray[var5] = Conv.convert_int_to_double(var3[var5]);
                this.fArray1d[var5] = Conv.convert_int_to_double(var4[var5]);
            }

            this.xSampling = new int[this.xLengthS];

            for(var5 = 0; var5 < this.xLengthS; this.xSampling[var5] = var5++) {
                ;
            }

            this.xSamplingStart = 0;
            this.xSamplingEnd = this.xLengthS - 1;
            this.xSamplingPeriod = 1;
            this.sampled = false;
        }
    }

    public Gradient(double[] var1, double[] var2, double[][] var3) {
        this.xLength = var3.length;
        this.yLength = var3[0].length;
        if (var1.length != this.xLength) {
            throw new IllegalArgumentException("x length, " + var1.length + ", does not match corresponding f length, " + this.xLength);
        } else if (var2.length != this.yLength) {
            throw new IllegalArgumentException("y length, " + var2.length + ", does not match corresponding f length, " + this.yLength);
        } else {
            this.nDim = 2;
            this.xLengthS = this.xLength;
            this.yLengthS = this.yLength;
            this.xArray = Conv.copy(var1);
            this.yArray = Conv.copy(var2);
            this.fArray2d = Conv.copy(var3);
            this.xSampling = new int[this.xLengthS];

            int var4;
            for(var4 = 0; var4 < this.xLengthS; this.xSampling[var4] = var4++) {
                ;
            }

            this.ySampling = new int[this.yLengthS];

            for(var4 = 0; var4 < this.yLengthS; this.ySampling[var4] = var4++) {
                ;
            }

            this.xSamplingStart = 0;
            this.xSamplingEnd = this.xLengthS - 1;
            this.xSamplingPeriod = 1;
            this.ySamplingStart = 0;
            this.ySamplingEnd = this.yLengthS - 1;
            this.ySamplingPeriod = 1;
            this.sampled = false;
        }
    }

    public Gradient(float[] var1, float[] var2, float[][] var3) {
        this.xLength = var3.length;
        this.yLength = var3[0].length;
        if (var1.length != this.xLength) {
            throw new IllegalArgumentException("x length, " + var1.length + ", does not match corresponding f length, " + this.xLength);
        } else if (var2.length != this.yLength) {
            throw new IllegalArgumentException("y length, " + var2.length + ", does not match corresponding f length, " + this.yLength);
        } else {
            this.nDim = 2;
            this.xLengthS = this.xLength;
            this.yLengthS = this.yLength;
            float[] var4 = Conv.copy(var1);
            float[] var5 = Conv.copy(var2);
            float[][] var6 = Conv.copy(var3);
            this.xArray = new double[this.xLength];
            this.yArray = new double[this.yLength];
            this.fArray2d = new double[this.xLength][this.yLength];

            int var7;
            for(var7 = 0; var7 < this.xLength; ++var7) {
                this.xArray[var7] = Conv.convert_float_to_double(var4[var7]);

                for(int var8 = 0; var8 < this.yLength; ++var8) {
                    if (var7 == 0) {
                        this.yArray[var8] = Conv.convert_float_to_double(var5[var8]);
                    }

                    this.fArray2d[var7][var8] = Conv.convert_float_to_double(var6[var7][var8]);
                }
            }

            this.xSampling = new int[this.xLengthS];

            for(var7 = 0; var7 < this.xLengthS; this.xSampling[var7] = var7++) {
                ;
            }

            this.ySampling = new int[this.yLengthS];

            for(var7 = 0; var7 < this.yLengthS; this.ySampling[var7] = var7++) {
                ;
            }

            this.xSamplingStart = 0;
            this.xSamplingEnd = this.xLengthS - 1;
            this.xSamplingPeriod = 1;
            this.ySamplingStart = 0;
            this.ySamplingEnd = this.yLengthS - 1;
            this.ySamplingPeriod = 1;
            this.sampled = false;
        }
    }

    public Gradient(long[] var1, long[] var2, long[][] var3) {
        this.xLength = var3.length;
        this.yLength = var3[0].length;
        if (var1.length != this.xLength) {
            throw new IllegalArgumentException("x length, " + var1.length + ", does not match corresponding f length, " + this.xLength);
        } else if (var2.length != this.yLength) {
            throw new IllegalArgumentException("y length, " + var2.length + ", does not match corresponding f length, " + this.yLength);
        } else {
            this.nDim = 2;
            this.xLengthS = this.xLength;
            this.yLengthS = this.yLength;
            long[] var4 = Conv.copy(var1);
            long[] var5 = Conv.copy(var2);
            long[][] var6 = Conv.copy(var3);
            this.xArray = new double[this.xLength];
            this.yArray = new double[this.yLength];
            this.fArray2d = new double[this.xLength][this.yLength];

            int var7;
            for(var7 = 0; var7 < this.xLength; ++var7) {
                this.xArray[var7] = Conv.convert_long_to_double(var4[var7]);

                for(int var8 = 0; var8 < this.yLength; ++var8) {
                    if (var7 == 0) {
                        this.yArray[var8] = Conv.convert_long_to_double(var5[var8]);
                    }

                    this.fArray2d[var7][var8] = Conv.convert_long_to_double(var6[var7][var8]);
                }
            }

            this.xSampling = new int[this.xLengthS];

            for(var7 = 0; var7 < this.xLengthS; this.xSampling[var7] = var7++) {
                ;
            }

            this.ySampling = new int[this.yLengthS];

            for(var7 = 0; var7 < this.yLengthS; this.ySampling[var7] = var7++) {
                ;
            }

            this.xSamplingStart = 0;
            this.xSamplingEnd = this.xLengthS - 1;
            this.xSamplingPeriod = 1;
            this.ySamplingStart = 0;
            this.ySamplingEnd = this.yLengthS - 1;
            this.ySamplingPeriod = 1;
            this.sampled = false;
        }
    }

    public Gradient(int[] var1, int[] var2, int[][] var3) {
        this.xLength = var3.length;
        this.yLength = var3[0].length;
        if (var1.length != this.xLength) {
            throw new IllegalArgumentException("x length, " + var1.length + ", does not match corresponding f length, " + this.xLength);
        } else if (var2.length != this.yLength) {
            throw new IllegalArgumentException("y length, " + var2.length + ", does not match corresponding f length, " + this.yLength);
        } else {
            this.nDim = 2;
            this.xLengthS = this.xLength;
            this.yLengthS = this.yLength;
            int[] var4 = Conv.copy(var1);
            int[] var5 = Conv.copy(var2);
            int[][] var6 = Conv.copy(var3);
            this.xArray = new double[this.xLength];
            this.yArray = new double[this.yLength];
            this.fArray2d = new double[this.xLength][this.yLength];

            int var7;
            for(var7 = 0; var7 < this.xLength; ++var7) {
                this.xArray[var7] = Conv.convert_int_to_double(var4[var7]);

                for(int var8 = 0; var8 < this.yLength; ++var8) {
                    if (var7 == 0) {
                        this.yArray[var8] = Conv.convert_int_to_double(var5[var8]);
                    }

                    this.fArray2d[var7][var8] = Conv.convert_int_to_double(var6[var7][var8]);
                }
            }

            this.xSampling = new int[this.xLengthS];

            for(var7 = 0; var7 < this.xLengthS; this.xSampling[var7] = var7++) {
                ;
            }

            this.ySampling = new int[this.yLengthS];

            for(var7 = 0; var7 < this.yLengthS; this.ySampling[var7] = var7++) {
                ;
            }

            this.xSamplingStart = 0;
            this.xSamplingEnd = this.xLengthS - 1;
            this.xSamplingPeriod = 1;
            this.ySamplingStart = 0;
            this.ySamplingEnd = this.yLengthS - 1;
            this.ySamplingPeriod = 1;
            this.sampled = false;
        }
    }

    public void sampling(int var1) {
        this.xSamplingStart = 0;
        this.ySamplingStart = 0;
        this.xSamplingEnd = this.xLength - 1;
        this.ySamplingEnd = this.yLength - 1;
        this.xSamplingPeriod = var1;
        this.ySamplingPeriod = var1;
        this.samplingSelection();
    }

    public void sampling(int var1, int var2) {
        this.xSamplingStart = 0;
        this.ySamplingStart = 0;
        this.xSamplingEnd = this.xLength - 1;
        this.ySamplingEnd = this.yLength - 1;
        this.xSamplingPeriod = var1;
        this.ySamplingPeriod = var2;
        this.samplingSelection();
    }

    public void sampling(int var1, int var2, int var3) {
        this.xSamplingStart = var2;
        this.ySamplingStart = var2;
        this.xSamplingEnd = var3;
        this.ySamplingEnd = var3;
        this.xSamplingPeriod = var1;
        this.ySamplingPeriod = var1;
        this.samplingSelection();
    }

    public void sampling(int var1, int var2, int var3, int var4, int var5, int var6) {
        this.xSamplingStart = var2;
        this.ySamplingStart = var5;
        this.xSamplingEnd = var3;
        this.ySamplingEnd = var6;
        this.xSamplingPeriod = var1;
        this.ySamplingPeriod = var4;
        this.samplingSelection();
    }

    private void samplingSelection() {
        int var2;
        switch(this.nDim) {
            case 2:
                this.yLengthS = (this.ySamplingEnd - this.ySamplingStart + 1) / this.ySamplingPeriod;
                int var1 = this.yLength % this.ySamplingPeriod;
                if (var1 != 0) {
                    ++this.yLengthS;
                }

                this.ySampling = new int[this.yLengthS];
                this.ySampling[0] = this.ySamplingStart;

                for(var2 = 1; var2 < this.yLengthS; ++var2) {
                    this.ySampling[var2] = this.ySampling[var2 - 1] + this.ySamplingPeriod;
                }
            case 1:
                this.xLengthS = (this.xSamplingEnd - this.xSamplingStart + 1) / this.xSamplingPeriod;
                var2 = this.xLength % this.xSamplingPeriod;
                if (var2 != 0) {
                    ++this.xLengthS;
                }

                this.xSampling = new int[this.xLengthS];
                this.xSampling[0] = this.xSamplingStart;

                for(int var3 = 1; var3 < this.xLengthS; ++var3) {
                    this.xSampling[var3] = this.xSampling[var3 - 1] + this.xSamplingPeriod;
                }
        }

        this.numArrayDone = false;
    }

    public double[] splineDeriv_1D_array() {
        if (this.nDim != 1) {
            throw new IllegalArgumentException("method splineDeriv_1D_array is only applicable if the entered data is a ONE dimensional array");
        } else {
            if (!this.splineDone) {
                this.splineDerivativesArray();
            }

            return this.csGrad1;
        }
    }

    public double[][] splineDeriv_2D_x_direction() {
        if (this.nDim != 2) {
            throw new IllegalArgumentException("method splineDeriv_2D_x_direction is only applicable if the entered data is a TWO dimensional array");
        } else {
            if (!this.splineDone) {
                this.splineDerivativesArray();
            }

            return this.bcsGrad2_X;
        }
    }

    public double[][] splineDeriv_2D_y_direction() {
        if (this.nDim != 2) {
            throw new IllegalArgumentException("method splineDeriv_2D_y_direction is only applicable if the entered data is a TWO dimensional array");
        } else {
            if (!this.splineDone) {
                this.splineDerivativesArray();
            }

            return this.bcsGrad2_Y;
        }
    }

    public ArrayList<Object> splineDerivativesArray() {
        this.splineDerivArray = new ArrayList();
        int var1;
        switch(this.nDim) {
            case 1:
                this.cs = new CubicSpline(this.xArray, this.fArray1d);
                this.csGrad1F = new double[this.xLength];
                this.csGrad1 = new double[this.xLengthS];

                for(var1 = 0; var1 < this.xLength; ++var1) {
                    this.csGrad1F[var1] = this.cs.interpolate_for_y_and_dydx(this.xArray[var1])[1];
                }

                for(var1 = 0; var1 < this.xLengthS; ++var1) {
                    this.csGrad1[var1] = this.csGrad1F[this.xSampling[var1]];
                }

                this.splineDerivArray.add(this.csGrad1);
                break;
            case 2:
                this.bcs = new BiCubicSplineFirstDerivative(this.xArray, this.yArray, this.fArray2d);
                this.bcsGrad2_XF = new double[this.xLength][this.yLength];
                this.bcsGrad2_YF = new double[this.xLength][this.yLength];
                this.bcsGrad2_X = new double[this.xLengthS][this.yLengthS];
                this.bcsGrad2_Y = new double[this.xLengthS][this.yLengthS];

                int var2;
                for(var1 = 0; var1 < this.xLength; ++var1) {
                    for(var2 = 0; var2 < this.yLength; ++var2) {
                        double[] var3 = this.bcs.interpolate(this.xArray[var1], this.yArray[var2]);
                        this.bcsGrad2_XF[var1][var2] = var3[1];
                        this.bcsGrad2_YF[var1][var2] = var3[2];
                    }
                }

                for(var1 = 0; var1 < this.xLengthS; ++var1) {
                    for(var2 = 0; var2 < this.yLengthS; ++var2) {
                        this.bcsGrad2_X[var1][var2] = this.bcsGrad2_XF[this.xSampling[var1]][this.ySampling[var2]];
                        this.bcsGrad2_Y[var1][var2] = this.bcsGrad2_YF[this.xSampling[var1]][this.ySampling[var2]];
                    }
                }

                this.splineDerivArray.add(this.bcsGrad2_X);
                this.splineDerivArray.add(this.bcsGrad2_Y);
        }

        this.splineDone = true;
        return this.splineDerivArray;
    }

    public double splineDerivAtPoint(double var1) {
        if (this.nDim != 1) {
            throw new IllegalArgumentException("Only one coordinate entered for a multiple dimensioned array");
        } else if (var1 >= this.xArray[0] && var1 <= this.xArray[this.xLength - 1]) {
            if (!this.splineDone) {
                this.cs = new CubicSpline(this.xArray, this.fArray1d);
            }

            return this.cs.interpolate_for_y_and_dydx(var1)[1];
        } else {
            throw new IllegalArgumentException("Entered xx value, " + var1 + ", is outside the x value range, " + this.xArray[0] + " to " + this.xArray[this.xLength - 1]);
        }
    }

    public double[] splineDerivAtPoint(double var1, double var3) {
        if (this.nDim == 1) {
            throw new IllegalArgumentException("Two coordinates entered for a one dimension array");
        } else if (var1 >= this.xArray[0] && var1 <= this.xArray[this.xLength - 1]) {
            if (var3 >= this.yArray[0] && var3 <= this.yArray[this.yLength - 1]) {
                if (!this.splineDone) {
                    this.bcs = new BiCubicSplineFirstDerivative(this.xArray, this.yArray, this.fArray2d);
                }

                double[] var5 = new double[2];
                double[] var6 = this.bcs.interpolate(var1, var3);
                var5[0] = var6[1];
                var5[1] = var6[2];
                return var5;
            } else {
                throw new IllegalArgumentException("Entered yy value, " + var3 + ", is outside the y value range, " + this.yArray[0] + " to " + this.yArray[this.yLength - 1]);
            }
        } else {
            throw new IllegalArgumentException("Entered xx value, " + var1 + ", is outside the x value range, " + this.xArray[0] + " to " + this.xArray[this.xLength - 1]);
        }
    }

    public double[] numDeriv_1D_array() {
        if (this.nDim != 1) {
            throw new IllegalArgumentException("method numDeriv_1D_array is only applicable if the entered data is a ONE dimensional array");
        } else {
            if (!this.numArrayDone) {
                this.numericalDerivativesArray();
            }

            return this.ndGrad1;
        }
    }

    public double[][] numDeriv_2D_x_direction() {
        if (this.nDim != 2) {
            throw new IllegalArgumentException("method numDeriv_2D_x_direction is only applicable if the entered data is a TWO dimensional array");
        } else {
            if (!this.numArrayDone) {
                this.numericalDerivativesArray();
            }

            return this.ndGrad2_X;
        }
    }

    public double[][] numDeriv_2D_y_direction() {
        if (this.nDim != 2) {
            throw new IllegalArgumentException("method numDeriv_2D_y_direction is only applicable if the entered data is a TWO dimensional array");
        } else {
            if (!this.numArrayDone) {
                this.numericalDerivativesArray();
            }

            return this.ndGrad2_Y;
        }
    }

    public ArrayList<Object> numericalDerivativesArray() {
        this.numDiffArray = new ArrayList();
        int var1;
        switch(this.nDim) {
            case 1:
                this.ndGrad1 = new double[this.xLengthS];

                for(var1 = 0; var1 < this.xLengthS; ++var1) {
                    if (this.xSampling[var1] == 0) {
                        this.ndGrad1[var1] = (this.fArray1d[this.xSampling[var1] + 1] - this.fArray1d[this.xSampling[var1]]) / (this.xArray[this.xSampling[var1] + 1] - this.xArray[this.xSampling[var1]]);
                    } else if (this.xSampling[var1] == this.xLength - 1) {
                        this.ndGrad1[var1] = (this.fArray1d[this.xSampling[var1]] - this.fArray1d[this.xSampling[var1] - 1]) / (this.xArray[this.xSampling[var1]] - this.xArray[this.xSampling[var1] - 1]);
                    } else {
                        this.ndGrad1[var1] = ((this.fArray1d[this.xSampling[var1]] - this.fArray1d[this.xSampling[var1] - 1]) / (this.xArray[this.xSampling[var1]] - this.xArray[this.xSampling[var1] - 1]) + (this.fArray1d[this.xSampling[var1] + 1] - this.fArray1d[this.xSampling[var1]]) / (this.xArray[this.xSampling[var1] + 1] - this.xArray[this.xSampling[var1]])) / 2.0D;
                    }
                }

                this.numDiffArray.add(this.ndGrad1);
                break;
            case 2:
                this.ndGrad2_Y = new double[this.xLengthS][this.yLengthS];
                this.ndGrad2_X = new double[this.xLengthS][this.yLengthS];

                int var2;
                for(var1 = 0; var1 < this.xLengthS; ++var1) {
                    for(var2 = 0; var2 < this.yLengthS; ++var2) {
                        if (this.ySampling[var2] == 0) {
                            this.ndGrad2_Y[var1][var2] = (this.fArray2d[this.xSampling[var1]][this.ySampling[var2] + 1] - this.fArray2d[this.xSampling[var1]][this.ySampling[var2]]) / (this.yArray[this.ySampling[var2] + 1] - this.yArray[this.ySampling[var2]]);
                        } else if (this.ySampling[var2] == this.yLength - 1) {
                            this.ndGrad2_Y[var1][var2] = (this.fArray2d[this.xSampling[var1]][this.ySampling[var2]] - this.fArray2d[this.xSampling[var1]][this.ySampling[var2] - 1]) / (this.yArray[this.ySampling[var2]] - this.yArray[this.ySampling[var2] - 1]);
                        } else {
                            this.ndGrad2_Y[var1][var2] = ((this.fArray2d[this.xSampling[var1]][this.ySampling[var2]] - this.fArray2d[this.xSampling[var1]][this.ySampling[var2] - 1]) / (this.yArray[this.ySampling[var2]] - this.yArray[this.ySampling[var2] - 1]) + (this.fArray2d[this.xSampling[var1]][this.ySampling[var2] + 1] - this.fArray2d[this.xSampling[var1]][this.ySampling[var2]]) / (this.yArray[this.ySampling[var2] + 1] - this.yArray[this.ySampling[var2]])) / 2.0D;
                        }
                    }
                }

                this.numDiffArray.add(this.ndGrad2_Y);

                for(var1 = 0; var1 < this.yLengthS; ++var1) {
                    for(var2 = 0; var2 < this.xLengthS; ++var2) {
                        if (this.xSampling[var2] == 0) {
                            this.ndGrad2_X[var2][var1] = (this.fArray2d[this.xSampling[var2] + 1][this.ySampling[var1]] - this.fArray2d[this.xSampling[var2]][this.ySampling[var1]]) / (this.xArray[this.xSampling[var2] + 1] - this.xArray[this.xSampling[var2]]);
                        } else if (this.xSampling[var2] == this.xLength - 1) {
                            this.ndGrad2_X[var2][var1] = (this.fArray2d[this.xSampling[var2]][this.ySampling[var1]] - this.fArray2d[this.xSampling[var2] - 1][this.ySampling[var1]]) / (this.xArray[this.xSampling[var2]] - this.xArray[this.xSampling[var2] - 1]);
                        } else {
                            this.ndGrad2_X[var2][var1] = ((this.fArray2d[this.xSampling[var2]][this.ySampling[var1]] - this.fArray2d[this.xSampling[var2] - 1][this.ySampling[var1]]) / (this.xArray[this.xSampling[var2]] - this.xArray[this.xSampling[var2] - 1]) + this.ndGrad2_X[var2][var1] + (this.fArray2d[this.xSampling[var2] + 1][this.ySampling[var1]] - this.fArray2d[this.xSampling[var2]][this.ySampling[var1]]) / (this.xArray[this.xSampling[var2] + 1] - this.xArray[this.xSampling[var2]])) / 2.0D;
                        }
                    }
                }

                this.numDiffArray.add(this.ndGrad2_X);
                break;
            default:
                throw new IllegalArgumentException("Arrays of " + this.nDim + " dimensions are not handles by this class");
        }

        this.numArrayDone = true;
        return this.numDiffArray;
    }

    public double numDerivAtPoint(double var1) {
        if (this.nDim != 1) {
            throw new IllegalArgumentException("Only one coordinate entered for a multiple dimensioned array");
        } else if (var1 >= this.xArray[0] && var1 <= this.xArray[this.xLength - 1]) {
            double var3 = 0.0D;
            boolean var5 = true;
            int var6 = 0;

            while(var5) {
                if (var1 <= this.xArray[var6]) {
                    var5 = false;
                    if (var6 == 0) {
                        var3 = (this.fArray1d[var6 + 1] - this.fArray1d[var6]) / (this.xArray[var6 + 1] - this.xArray[var6]);
                    } else if (var1 == this.xArray[this.xLength - 1]) {
                        var3 = (this.fArray1d[var6] - this.fArray1d[var6 - 1]) / (this.xArray[var6] - this.xArray[var6 - 1]);
                    } else if (var1 == this.xArray[var6]) {
                        var3 = ((this.fArray1d[var6] - this.fArray1d[var6 - 1]) / (this.xArray[var6] - this.xArray[var6 - 1]) + (this.fArray1d[var6 + 1] - this.fArray1d[var6]) / (this.xArray[var6 + 1] - this.xArray[var6])) / 2.0D;
                    } else {
                        var3 = (this.fArray1d[var6] - this.fArray1d[var6 - 1]) / (this.xArray[var6] - this.xArray[var6 - 1]);
                    }
                } else {
                    ++var6;
                }
            }

            return var3;
        } else {
            throw new IllegalArgumentException("Entered xx value, " + var1 + ", is outside the x value range, " + this.xArray[0] + " to " + this.xArray[this.xLength - 1]);
        }
    }

    public double[] numDerivAtPoint(double var1, double var3) {
        if (this.nDim == 1) {
            throw new IllegalArgumentException("Two coordinates entered for a one dimension array");
        } else if (var1 >= this.xArray[0] && var1 <= this.xArray[this.xLength - 1]) {
            if (var3 >= this.yArray[0] && var3 <= this.yArray[this.yLength - 1]) {
                double[] var5 = new double[2];
                boolean var6 = true;
                boolean var7 = false;
                int var8 = 0;
                int var9 = 0;

                while(var6) {
                    if (var1 <= this.xArray[var9]) {
                        var6 = false;
                        if (var1 == this.xArray[var9]) {
                            var7 = true;
                        }

                        var8 = var9;
                    } else {
                        ++var9;
                    }
                }

                boolean var10 = true;
                boolean var11 = false;
                int var12 = 0;
                var9 = 0;

                while(var10) {
                    if (var3 <= this.yArray[var9]) {
                        var10 = false;
                        if (var3 == this.yArray[var9]) {
                            var11 = true;
                        }

                        var12 = var9;
                    } else {
                        ++var9;
                    }
                }

                byte var13 = 0;
                if (var7) {
                    var13 = 1;
                }

                if (var11) {
                    var13 = 2;
                }

                if (var7 && var11) {
                    var13 = 3;
                }

                if (!this.numArrayDone) {
                    this.numericalDerivativesArray();
                }

                System.out.println(var8 + " " + var12);
                switch(var13) {
                    case 0:
                        double var14 = (this.yArray[var12 - 1] - var3) / (this.yArray[var12 - 1] - this.yArray[var12]);
                        double var16 = (this.xArray[var8 - 1] - var1) / (this.xArray[var8 - 1] - this.xArray[var8]);
                        double var18 = this.ndGrad2_X[var8][var12 - 1] - (this.ndGrad2_X[var8][var12 - 1] - this.ndGrad2_X[var8][var12]) * var14;
                        double var20 = this.ndGrad2_X[var8 - 1][var12 - 1] - (this.ndGrad2_X[var8 - 1][var12 - 1] - this.ndGrad2_X[var8 - 1][var12]) * var14;
                        var5[0] = var20 - (var20 - var18) * var16;
                        var18 = this.ndGrad2_Y[var8 - 1][var12] - (this.ndGrad2_Y[var8 - 1][var12] - this.ndGrad2_Y[var8][var12]) * var16;
                        var20 = this.ndGrad2_Y[var8 - 1][var12 - 1] - (this.ndGrad2_Y[var8 - 1][var12 - 1] - this.ndGrad2_Y[var8][var12 - 1]) * var16;
                        var5[1] = var20 - (var20 - var18) * var14;
                        break;
                    case 1:
                        double var22 = (this.yArray[var12 - 1] - var3) / (this.yArray[var12 - 1] - this.yArray[var12]);
                        var5[0] = this.ndGrad2_X[var8][var12 - 1] - (this.ndGrad2_X[var8][var12 - 1] - this.ndGrad2_X[var8][var12]) * var22;
                        var5[1] = this.ndGrad2_Y[var8][var12 - 1] - (this.ndGrad2_Y[var8][var12 - 1] - this.ndGrad2_Y[var8][var12]) * var22;
                        break;
                    case 2:
                        double var24 = (this.xArray[var8 - 1] - var1) / (this.xArray[var8 - 1] - this.xArray[var8]);
                        var5[0] = this.ndGrad2_X[var8 - 1][var12] - (this.ndGrad2_X[var8 - 1][var12] - this.ndGrad2_X[var8][var12]) * var24;
                        var5[1] = this.ndGrad2_Y[var8 - 1][var12] - (this.ndGrad2_Y[var8 - 1][var12] - this.ndGrad2_Y[var8][var12]) * var24;
                        break;
                    case 3:
                        var5[0] = this.ndGrad2_X[var8][var12];
                        var5[1] = this.ndGrad2_Y[var8][var12];
                }

                return var5;
            } else {
                throw new IllegalArgumentException("Entered yy value, " + var3 + ", is outside the y value range, " + this.yArray[0] + " to " + this.yArray[this.yLength - 1]);
            }
        } else {
            throw new IllegalArgumentException("Entered xx value, " + var1 + ", is outside the x value range, " + this.xArray[0] + " to " + this.xArray[this.xLength - 1]);
        }
    }

    public Gradient copy() {
        Object var1 = null;
        double[][] var2 = (double[][])null;
        Gradient var3 = null;
        if (this.nDim == 1) {
            var3 = new Gradient(this.xArray, this.fArray1d);
        } else {
            var3 = new Gradient(this.xArray, this.yArray, this.fArray2d);
        }

        var3.xSampling = Conv.copy(this.xSampling);
        var3.ySampling = Conv.copy(this.ySampling);
        var3.xSamplingStart = this.xSamplingStart;
        var3.ySamplingStart = this.ySamplingStart;
        var3.xSamplingEnd = this.xSamplingEnd;
        var3.ySamplingEnd = this.ySamplingEnd;
        var3.xSamplingPeriod = this.xSamplingPeriod;
        var3.ySamplingPeriod = this.ySamplingPeriod;
        var3.sampled = this.sampled;
        double[] var4;
        if (this.numDiffArray == null) {
            var3.numDiffArray = null;
        } else {
            var3.numDiffArray = new ArrayList();
            if (this.nDim == 1) {
                var4 = (double[])((double[])this.numDiffArray.get(0));
                var3.numDiffArray.add(Conv.copy(var4));
            } else {
                var2 = (double[][])((double[][])this.numDiffArray.get(0));
                var3.numDiffArray.add(Conv.copy(var2));
                var2 = (double[][])((double[][])this.numDiffArray.get(1));
                var3.numDiffArray.add(Conv.copy(var2));
            }
        }

        var3.ndGrad1 = Conv.copy(this.ndGrad1);
        var3.ndGrad2_X = Conv.copy(this.ndGrad2_X);
        var3.ndGrad2_Y = Conv.copy(this.ndGrad2_Y);
        var3.numArrayDone = this.numArrayDone;
        var3.nDim = this.nDim;
        var3.xLength = this.xLength;
        var3.yLength = this.yLength;
        var3.xLengthS = this.xLengthS;
        var3.yLengthS = this.yLengthS;
        if (this.splineDerivArray == null) {
            var3.splineDerivArray = null;
        } else {
            var3.splineDerivArray = new ArrayList();
            if (this.nDim == 1) {
                var4 = (double[])((double[])this.splineDerivArray.get(0));
                var3.splineDerivArray.add(Conv.copy(var4));
            } else {
                var2 = (double[][])((double[][])this.splineDerivArray.get(0));
                var3.splineDerivArray.add(Conv.copy(var2));
                var2 = (double[][])((double[][])this.splineDerivArray.get(1));
                var3.splineDerivArray.add(Conv.copy(var2));
            }
        }

        var3.csGrad1 = Conv.copy(this.csGrad1);
        var3.bcsGrad2_X = Conv.copy(this.bcsGrad2_X);
        var3.bcsGrad2_Y = Conv.copy(this.bcsGrad2_Y);
        var3.cs = this.cs;
        var3.bcs = this.bcs;
        var3.splineDone = this.splineDone;
        return var3;
    }

    public Object clone() {
        return this.copy();
    }
}
