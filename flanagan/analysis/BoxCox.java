//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package flanagan.analysis;

import flanagan.io.FileOutput;
import flanagan.math.ArrayMaths;
import flanagan.math.Conv;
import flanagan.math.Fmath;
import flanagan.math.Maximization;
import flanagan.plot.PlotGraph;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

public class BoxCox {
    private double[] originalData = null;
    private double[] sortedOriginalData = null;
    private double[] standardizedOriginalData = null;
    private Stat sod = null;
    private double[] shiftedStandardizedOriginalData = null;
    private int[] originalIndices = null;
    private int nData = 0;
    private double originalRange = 0.0D;
    private double originalMinimum = 0.0D;
    private double originalMaximum = 0.0D;
    private double originalMean = 0.0D;
    private double originalMedian = 0.0D;
    private double originalStandardDeviation = 0.0D;
    private double originalVariance = 0.0D;
    private double originalMomentSkewness = 0.0D;
    private double originalMedianSkewness = 0.0D;
    private double originalQuartileSkewness = 0.0D;
    private double originalExcessKurtosis = 0.0D;
    private double standardizedOriginalRange = 0.0D;
    private double standardizedOriginalMinimum = 0.0D;
    private double standardizedOriginalMaximum = 0.0D;
    private double standardizedOriginalMean = 0.0D;
    private double standardizedOriginalMedian = 0.0D;
    private double standardizedOriginalStandardDeviation = 1.0D;
    private double standardizedOriginalVariance = 1.0D;
    private double standardizedOriginalMomentSkewness = 0.0D;
    private double standardizedOriginalMedianSkewness = 0.0D;
    private double standardizedOriginalQuartileSkewness = 0.0D;
    private double standardizedOriginalExcessKurtosis = 0.0D;
    private double originalSampleR = 0.0D;
    private double originalIntercept = 0.0D;
    private double originalGradient = 0.0D;
    private double originalInterceptError = 0.0D;
    private double originalGradientError = 0.0D;
    private boolean initializationDone = false;
    private double[] transformedData = null;
    private double[] standardizedTransformedData = null;
    private double[] scaledTransformedData = null;
    private double[] sortedScaledTransformedData = null;
    private double transformedRange = 0.0D;
    private double transformedMinimum = 0.0D;
    private double transformedMaximum = 0.0D;
    private double transformedMean = 0.0D;
    private double transformedStandardDeviation = 0.0D;
    private double transformedMedian = 0.0D;
    private double transformedVariance = 0.0D;
    private double transformedMomentSkewness = 0.0D;
    private double transformedMedianSkewness = 0.0D;
    private double transformedQuartileSkewness = 0.0D;
    private double transformedExcessKurtosis = 0.0D;
    private double standardizedTransformedRange = 0.0D;
    private double standardizedTransformedMinimum = 0.0D;
    private double standardizedTransformedMaximum = 0.0D;
    private double standardizedTransformedMean = 0.0D;
    private double standardizedTransformedMedian = 0.0D;
    private double standardizedTransformedStandardDeviation = 1.0D;
    private double standardizedTransformedVariance = 1.0D;
    private double standardizedTransformedMomentSkewness = 0.0D;
    private double standardizedTransformedMedianSkewness = 0.0D;
    private double standardizedTransformedQuartileSkewness = 0.0D;
    private double standardizedTransformedExcessKurtosis = 0.0D;
    private double[] inverseData = null;
    private double lambdaThree = 0.0D;
    private double lambdaOne = 0.0D;
    private double lambdaTwo = 0.0D;
    private double transformedSampleR = 0.0D;
    private double transformedIntercept = 0.0D;
    private double transformedGradient = 0.0D;
    private double transformedInterceptError = 0.0D;
    private double transformedGradientError = 0.0D;
    private double[] uniformOrderMedians = null;
    private double[] gaussianOrderMedians = null;
    private boolean transformDone = false;
    private boolean inverseDone = false;

    public BoxCox(double[] var1) {
        this.sod = new Stat(var1);
    }

    public BoxCox(float[] var1) {
        this.sod = new Stat(var1);
    }

    public BoxCox(int[] var1) {
        this.sod = new Stat(var1);
    }

    public BoxCox(long[] var1) {
        this.sod = new Stat(var1);
    }

    public BoxCox(short[] var1) {
        this.sod = new Stat(var1);
    }

    public BoxCox(byte[] var1) {
        this.sod = new Stat(var1);
    }

    public BoxCox(BigDecimal[] var1) {
        this.sod = new Stat(var1);
    }

    public BoxCox(BigInteger[] var1) {
        this.sod = new Stat(var1);
    }

    public BoxCox(Stat var1) {
        this.sod = var1;
    }

    public BoxCox(ArrayMaths var1) {
        double[] var2 = var1.array();
        this.sod = new Stat(var2);
    }

    public BoxCox(ArrayList<Object> var1) {
        this.sod = new Stat(var1);
    }

    public BoxCox(Vector<Object> var1) {
        this.sod = new Stat(var1);
    }

    public void setDenominatorToN() {
        Stat.setStaticDenominatorToN();
    }

    private void initialize() {
        this.originalData = this.sod.array_as_double();
        this.originalMinimum = this.sod.minimum();
        this.originalMaximum = this.sod.maximum();
        this.originalMedian = this.sod.median();
        if (this.originalMinimum == this.originalMaximum) {
            throw new IllegalArgumentException("A Box-Cox transformation cannot be performed on a data array of identical values");
        } else {
            this.originalRange = this.originalMaximum - this.originalMinimum;
            this.originalMean = this.sod.mean();
            this.originalStandardDeviation = this.sod.standardDeviation();
            this.originalVariance = this.sod.variance();
            this.originalMomentSkewness = this.sod.momentSkewness();
            this.originalMedianSkewness = this.sod.medianSkewness();
            this.originalQuartileSkewness = this.sod.quartileSkewness();
            this.originalExcessKurtosis = this.sod.excessKurtosis();
            ArrayMaths var1 = this.sod.sort();
            this.sortedOriginalData = var1.array();
            this.originalIndices = var1.originalIndices();
            this.standardizedOriginalData = this.sod.standardize();
            this.standardizedOriginalMinimum = this.sod.minimum();
            this.standardizedOriginalMaximum = this.sod.maximum();
            this.standardizedOriginalMedian = this.sod.median();
            this.standardizedOriginalRange = this.standardizedOriginalMaximum - this.standardizedOriginalMinimum;
            this.standardizedOriginalMean = 0.0D;
            this.standardizedOriginalStandardDeviation = 1.0D;
            this.standardizedOriginalVariance = 1.0D;
            this.standardizedOriginalMomentSkewness = this.sod.momentSkewness();
            this.standardizedOriginalMedianSkewness = this.sod.medianSkewness();
            this.standardizedOriginalQuartileSkewness = this.sod.quartileSkewness();
            this.standardizedOriginalExcessKurtosis = this.sod.excessKurtosis();
            this.nData = this.originalData.length;
            this.uniformOrderMedians = Stat.uniformOrderStatisticMedians(this.nData);
            this.gaussianOrderMedians = Stat.gaussianOrderStatisticMedians(this.nData);
            Regression var2 = new Regression(this.gaussianOrderMedians, (new ArrayMaths(this.standardizedOriginalData)).sort().array());
            var2.linear();
            this.originalSampleR = var2.getSampleR();
            double[] var3 = var2.getBestEstimates();
            this.originalIntercept = var3[0];
            this.originalGradient = var3[1];
            var3 = var2.getBestEstimatesErrors();
            this.originalInterceptError = var3[0];
            this.originalGradientError = var3[1];
            this.initializationDone = true;
        }
    }

    private double[] transform() {
        if (!this.initializationDone) {
            this.initialize();
        }

        this.lambdaTwo = 0.1D * this.standardizedOriginalRange - this.standardizedOriginalMinimum;
        ArrayMaths var1 = this.sod.plus(this.lambdaTwo);
        this.shiftedStandardizedOriginalData = var1.getArray_as_double();
        BoxCoxFunction var2 = new BoxCoxFunction();
        var2.shiftedData = this.shiftedStandardizedOriginalData;
        var2.nData = this.nData;
        var2.yTransform = new double[this.nData];
        var2.gaussianOrderMedians = this.gaussianOrderMedians;
        Maximization var3 = new Maximization();
        double[] var4 = new double[]{1.0D};
        double[] var5 = new double[]{0.3D};
        double var6 = 1.0E-9D;
        var3.nelderMead(var2, var4, var5, var6);
        double[] var8 = var3.getParamValues();
        double var9 = var8[0];
        double var11 = var3.getMaximum();
        var4[0] = var9 - (var4[0] - var9);
        var3.nelderMead(var2, var4, var5, var6);
        var8 = var3.getParamValues();
        this.lambdaOne = var8[0];
        this.transformedSampleR = var3.getMaximum();
        if (var11 > this.transformedSampleR) {
            this.transformedSampleR = var11;
            this.lambdaOne = var9;
        }

        this.transformedData = new double[this.nData];
        int var13;
        if (this.lambdaOne == 0.0D) {
            for(var13 = 0; var13 < this.nData; ++var13) {
                this.transformedData[var13] = Math.exp(this.shiftedStandardizedOriginalData[var13]);
            }
        } else {
            for(var13 = 0; var13 < this.nData; ++var13) {
                this.transformedData[var13] = (Math.pow(this.shiftedStandardizedOriginalData[var13], this.lambdaOne) - 1.0D) / this.lambdaOne;
            }
        }

        this.standardizedTransformedData = (new Stat(this.transformedData)).standardize();
        this.standardizedTransformedDataStatistics(this.standardizedTransformedData);
        ArrayMaths var16 = new ArrayMaths(this.standardizedTransformedData);
        var16 = var16.sort();
        double[] var14 = var16.array();
        Regression var15 = new Regression(this.gaussianOrderMedians, var14);
        var15.linear();
        var8 = var15.getBestEstimates();
        this.transformedIntercept = var8[0];
        this.transformedGradient = var8[1];
        var8 = var15.getBestEstimatesErrors();
        this.transformedInterceptError = var8[0];
        this.transformedGradientError = var8[1];
        this.scaledTransformedData = Stat.scale(this.standardizedTransformedData, this.originalMean, this.originalStandardDeviation);
        this.transformedDataStatistics(this.scaledTransformedData);
        this.transformDone = true;
        return this.transformedData;
    }

    private void transformedDataStatistics(double[] var1) {
        Stat var2 = new Stat(var1);
        this.transformedMinimum = var2.minimum();
        this.transformedMaximum = var2.maximum();
        this.transformedMedian = var2.median();
        this.transformedRange = this.transformedMaximum - this.transformedMinimum;
        this.transformedMean = var2.mean();
        this.transformedStandardDeviation = var2.standardDeviation();
        this.transformedVariance = var2.variance();
        this.transformedMomentSkewness = var2.momentSkewness();
        this.transformedMedianSkewness = var2.medianSkewness();
        this.transformedQuartileSkewness = var2.quartileSkewness();
        this.transformedExcessKurtosis = var2.excessKurtosis();
        Stat var3 = new Stat(var1);
        this.sortedScaledTransformedData = var3.sort().array();
    }

    private void standardizedTransformedDataStatistics(double[] var1) {
        Stat var2 = new Stat(var1);
        this.standardizedTransformedMinimum = var2.minimum();
        this.standardizedTransformedMaximum = var2.maximum();
        this.standardizedTransformedMedian = var2.median();
        this.standardizedTransformedRange = this.standardizedTransformedMaximum - this.standardizedTransformedMinimum;
        this.standardizedTransformedMean = 0.0D;
        this.standardizedTransformedStandardDeviation = 1.0D;
        this.standardizedTransformedVariance = 1.0D;
        this.standardizedTransformedMomentSkewness = var2.momentSkewness();
        this.standardizedTransformedMedianSkewness = var2.medianSkewness();
        this.standardizedTransformedQuartileSkewness = var2.quartileSkewness();
        this.standardizedTransformedExcessKurtosis = var2.excessKurtosis();
    }

    public double[] inverseTransform(double var1, double var3) {
        this.lambdaOne = var1;
        this.lambdaTwo = var3;
        if (!this.initializationDone) {
            this.initialize();
        }

        if (this.originalData == null) {
            throw new IllegalArgumentException("No data has been entered (via a constructor)");
        } else {
            this.inverseData = new double[this.nData];
            double[] var5 = Conv.copy(this.originalData);
            int var6;
            if (this.originalMinimum < 0.0D && Fmath.isNaN(Math.pow(this.originalMinimum * this.lambdaOne + 1.0D, 1.0D / this.lambdaOne))) {
                this.lambdaThree = -0.999D / this.lambdaOne - this.originalMinimum;

                for(var6 = 0; var6 < this.nData; ++var6) {
                    var5[var6] += this.lambdaThree;
                }
            }

            if (this.lambdaOne == 0.0D) {
                for(var6 = 0; var6 < this.nData; ++var6) {
                    this.inverseData[var6] = Math.exp(var5[var6]) - this.lambdaTwo;
                }
            } else {
                for(var6 = 0; var6 < this.nData; ++var6) {
                    this.inverseData[var6] = Math.pow(var5[var6] * this.lambdaOne + 1.0D, 1.0D / this.lambdaOne) - this.lambdaTwo;
                }
            }

            this.transformedData = Conv.copy(this.inverseData);
            this.transformedDataStatistics(this.inverseData);
            this.standardizedTransformedData = (new Stat(this.transformedData)).standardize();
            this.standardizedTransformedDataStatistics(this.standardizedTransformedData);
            this.inverseDone = true;
            return this.inverseData;
        }
    }

    public double[] inverseTransform(double var1) {
        return this.inverseTransform(var1, 0.0D);
    }

    public double[] fixedValueTransform(double var1, double var3) {
        this.lambdaOne = var1;
        this.lambdaTwo = var3;
        if (!this.initializationDone) {
            this.initialize();
        }

        if (this.originalData == null) {
            throw new IllegalArgumentException("No data has been entered (via a constructor)");
        } else if (this.originalMinimum + this.lambdaTwo < 0.0D) {
            throw new IllegalArgumentException("Negative (data plus lambdaTwo) value, " + (this.originalMinimum + this.lambdaTwo));
        } else {
            this.transformedData = new double[this.nData];
            int var5;
            if (this.lambdaOne == 0.0D) {
                for(var5 = 0; var5 < this.nData; ++var5) {
                    this.transformedData[var5] = Math.exp(this.shiftedStandardizedOriginalData[var5]);
                }
            } else {
                for(var5 = 0; var5 < this.nData; ++var5) {
                    this.transformedData[var5] = (Math.pow(this.shiftedStandardizedOriginalData[var5], this.lambdaOne) - 1.0D) / this.lambdaOne;
                }
            }

            this.standardizedTransformedData = (new Stat(this.transformedData)).standardize();
            this.standardizedTransformedDataStatistics(this.standardizedTransformedData);
            ArrayMaths var9 = new ArrayMaths(this.standardizedTransformedData);
            var9 = var9.sort();
            double[] var6 = var9.array();
            Regression var7 = new Regression(this.gaussianOrderMedians, var6);
            var7.linear();
            double[] var8 = var7.getBestEstimates();
            this.transformedIntercept = var8[0];
            this.transformedGradient = var8[1];
            this.scaledTransformedData = Stat.scale(this.standardizedTransformedData, this.originalMean, this.originalStandardDeviation);
            this.transformedDataStatistics(this.scaledTransformedData);
            this.transformDone = true;
            return this.transformedData;
        }
    }

    public double[] fixedValueTransform(double var1) {
        return this.fixedValueTransform(var1, 0.0D);
    }

    public double lambdaOne() {
        if (!this.transformDone) {
            this.transform();
        }

        return this.lambdaOne;
    }

    public double lambdaTwo() {
        if (!this.transformDone) {
            this.transform();
        }

        return this.lambdaTwo;
    }

    public double lambdaThree() {
        if (!this.inverseDone) {
            System.out.println("BoxCox: method lambdaThree: no inverse transform has been performed, zero returned");
        }

        return this.lambdaThree;
    }

    public double transformedCorrelationCoefficient() {
        if (!this.transformDone) {
            this.transform();
        }

        return this.transformedSampleR;
    }

    public double transformedGradient() {
        if (!this.transformDone) {
            this.transform();
        }

        return this.transformedGradient;
    }

    public double transformedGradientError() {
        if (!this.transformDone) {
            this.transform();
        }

        return this.transformedGradientError;
    }

    public double transformedIntercept() {
        if (!this.transformDone) {
            this.transform();
        }

        return this.transformedIntercept;
    }

    public double transformedInterceptError() {
        if (!this.transformDone) {
            this.transform();
        }

        return this.transformedInterceptError;
    }

    public double originalCorrelationCoefficient() {
        if (!this.initializationDone) {
            this.initialize();
        }

        return this.originalSampleR;
    }

    public double originalGradient() {
        if (!this.initializationDone) {
            this.initialize();
        }

        return this.originalGradient;
    }

    public double originalGradientError() {
        if (!this.initializationDone) {
            this.initialize();
        }

        return this.originalGradientError;
    }

    public double originalIntercept() {
        if (!this.initializationDone) {
            this.initialize();
        }

        return this.originalIntercept;
    }

    public double originalInterceptError() {
        if (!this.initializationDone) {
            this.initialize();
        }

        return this.originalInterceptError;
    }

    public double[] originalData() {
        if (!this.initializationDone) {
            this.initialize();
        }

        return this.originalData;
    }

    public double[] standardizedOriginalData() {
        if (!this.initializationDone) {
            this.initialize();
        }

        return this.standardizedOriginalData;
    }

    public double[] sortedOriginalData() {
        if (!this.initializationDone) {
            this.initialize();
        }

        return this.sortedOriginalData;
    }

    public double[] shiftedStandardizedOriginalata() {
        if (!this.initializationDone) {
            this.initialize();
        }

        return this.shiftedStandardizedOriginalData;
    }

    public double[] transformedData() {
        if (!this.transformDone) {
            this.transform();
        }

        return this.transformedData;
    }

    public double[] scaledTransformedData() {
        if (!this.transformDone) {
            this.transform();
        }

        return this.scaledTransformedData;
    }

    public double[] standardizedTransformedData() {
        if (!this.transformDone) {
            this.transform();
        }

        return this.standardizedTransformedData;
    }

    public double[] orderedTransformedData() {
        if (!this.transformDone) {
            this.transform();
        }

        ArrayMaths var1 = new ArrayMaths(this.transformedData);
        double[] var2 = var1.sort().array();
        return var2;
    }

    public double[] orderedScaledTransformedData() {
        if (!this.transformDone) {
            this.transform();
        }

        return this.sortedScaledTransformedData;
    }

    public double originalMean() {
        if (!this.initializationDone) {
            this.initialize();
        }

        return this.originalMean;
    }

    public double originalMedian() {
        if (!this.initializationDone) {
            this.initialize();
        }

        return this.originalMedian;
    }

    public double originalStandardDeviation() {
        if (!this.initializationDone) {
            this.initialize();
        }

        return this.originalStandardDeviation;
    }

    public double originalStandardError() {
        if (!this.initializationDone) {
            this.initialize();
        }

        return this.originalStandardDeviation / Math.sqrt((double)this.nData);
    }

    public double originalVariance() {
        if (!this.initializationDone) {
            this.initialize();
        }

        return this.originalVariance;
    }

    public double originalMomentSkewness() {
        if (!this.initializationDone) {
            this.initialize();
        }

        return this.originalMomentSkewness;
    }

    public double originalMedianSkewness() {
        if (!this.initializationDone) {
            this.initialize();
        }

        return this.originalMedianSkewness;
    }

    public double originalQuartiletSkewness() {
        if (!this.initializationDone) {
            this.initialize();
        }

        return this.originalQuartileSkewness;
    }

    public double originalExcessKurtosis() {
        if (!this.initializationDone) {
            this.initialize();
        }

        return this.originalExcessKurtosis;
    }

    public double originalMaximum() {
        if (!this.initializationDone) {
            this.initialize();
        }

        return this.originalMaximum;
    }

    public double originalMinimum() {
        if (!this.initializationDone) {
            this.initialize();
        }

        return this.originalMinimum;
    }

    public double originalRange() {
        if (!this.initializationDone) {
            this.initialize();
        }

        return this.originalRange;
    }

    public double transformedMean() {
        if (!this.transformDone) {
            this.transform();
        }

        return this.transformedMean;
    }

    public double transformedMedian() {
        if (!this.transformDone) {
            this.transform();
        }

        return this.transformedMedian;
    }

    public double transformedStandardDeviation() {
        if (!this.transformDone) {
            this.transform();
        }

        return this.transformedStandardDeviation;
    }

    public double transformedStandardError() {
        if (!this.transformDone) {
            this.transform();
        }

        return this.transformedStandardDeviation / Math.sqrt((double)this.nData);
    }

    public double transformedVariance() {
        if (!this.transformDone) {
            this.transform();
        }

        return this.transformedVariance;
    }

    public double transformedMomentSkewness() {
        if (!this.transformDone) {
            this.transform();
        }

        return this.transformedMomentSkewness;
    }

    public double transformedMedianSkewness() {
        if (!this.transformDone) {
            this.transform();
        }

        return this.transformedMedianSkewness;
    }

    public double transformedQuartileSkewness() {
        if (!this.transformDone) {
            this.transform();
        }

        return this.transformedQuartileSkewness;
    }

    public double transformedExcessKurtosis() {
        if (!this.transformDone) {
            this.transform();
        }

        return this.transformedExcessKurtosis;
    }

    public double transformedMaximum() {
        if (!this.transformDone) {
            this.transform();
        }

        return this.transformedMaximum;
    }

    public double transformedMinimum() {
        if (!this.transformDone) {
            this.transform();
        }

        return this.transformedMinimum;
    }

    public double transformedRange() {
        if (!this.transformDone) {
            this.transform();
        }

        return this.transformedRange;
    }

    public void transformedProbabilityPlot() {
        if (!this.transformDone) {
            this.transform();
        }

        double[][] var1 = PlotGraph.data(2, this.nData);
        var1[0] = this.gaussianOrderMedians;
        var1[1] = (new ArrayMaths(this.standardizedTransformedData)).sort().array();
        var1[2] = this.gaussianOrderMedians;

        for(int var2 = 0; var2 < this.nData; ++var2) {
            var1[3][var2] = this.transformedIntercept + this.transformedGradient * this.gaussianOrderMedians[var2];
        }

        PlotGraph var7 = new PlotGraph(var1);
        int[] var3 = new int[]{4, 0};
        var7.setPoint(var3);
        int[] var4 = new int[]{0, 3};
        var7.setLine(var4);
        var7.setXaxisLegend("Gaussian [0,1] Order Statistic Medians");
        var7.setYaxisLegend("Ordered Response Values");
        String var5 = "Gausssian probability plot:  Box-Cox transformed data";
        String var6 = "lambdaOne = " + Fmath.truncate(this.lambdaOne, 4) + ",  lambdaTwo = " + Fmath.truncate(this.lambdaTwo, 4) + ",   gradient = " + Fmath.truncate(this.transformedGradient, 4) + ", intercept = " + Fmath.truncate(this.transformedIntercept, 4) + ",  R = " + Fmath.truncate(this.transformedSampleR, 4);
        var7.setGraphTitle(var5);
        var7.setGraphTitle2(var6);
        var7.plot();
    }

    public void originalProbabilityPlot() {
        if (!this.initializationDone) {
            this.initialize();
        }

        double[][] var1 = PlotGraph.data(2, this.nData);
        var1[0] = this.gaussianOrderMedians;
        var1[1] = (new ArrayMaths(this.standardizedOriginalData)).sort().array();
        var1[2] = this.gaussianOrderMedians;

        for(int var2 = 0; var2 < this.nData; ++var2) {
            var1[3][var2] = this.originalIntercept + this.originalGradient * this.gaussianOrderMedians[var2];
        }

        PlotGraph var7 = new PlotGraph(var1);
        int[] var3 = new int[]{4, 0};
        var7.setPoint(var3);
        int[] var4 = new int[]{0, 3};
        var7.setLine(var4);
        var7.setXaxisLegend("Gaussian [0,1] Order Statistic Medians");
        var7.setYaxisLegend("Ordered Response Values");
        String var5 = "Gausssian probability plot: original data for a Box-Cox transformation";
        String var6 = "gradient = " + Fmath.truncate(this.originalGradient, 4) + ", intercept = " + Fmath.truncate(this.originalIntercept, 4) + ",  R = " + Fmath.truncate(this.originalSampleR, 4);
        var7.setGraphTitle(var5);
        var7.setGraphTitle2(var6);
        var7.plot();
    }

    public void analysis() {
        this.analysis("BoxCoxAnalysis.txt");
    }

    public void analysis(String var1) {
        if (!this.transformDone) {
            this.transform();
        }

        this.originalProbabilityPlot();
        this.transformedProbabilityPlot();
        int var2 = var1.indexOf(".");
        if (var2 == -1) {
            var1 = var1 + ".txt";
        }

        FileOutput var3 = new FileOutput(var1);
        var3.println("Box-Cox Analysis");
        var3.println("File name:   " + var1);
        Date var4 = new Date();
        String var5 = DateFormat.getDateInstance().format(var4);
        String var6 = DateFormat.getTimeInstance().format(var4);
        var3.println("Program executed at " + var6 + " on " + var5);
        var3.println();
        byte var7 = 30;
        byte var8 = 15;
        var3.print("Box-Cox lambda one", var7);
        var3.println(Fmath.truncate(this.lambdaOne, 4));
        var3.print("Box-Cox lambda two", var7);
        var3.println(Fmath.truncate(this.lambdaTwo, 4));
        var3.println();
        var3.print("  ", var7);
        var3.print("Transformed", var8);
        var3.print("  ", var8);
        var3.println("Original   ");
        var3.print("  ", var7);
        var3.print("scaled data", var8);
        var3.print("  ", var8);
        var3.println("data   ");
        var3.println();
        var3.print("                            ", var7);
        var3.print("Value", var8);
        var3.print("Error", var8);
        var3.print("Value", var8);
        var3.println("Error");
        var3.println();
        var3.println("Gaussian Probability plot ");
        var3.print("  Correlation coefficient", var7);
        var3.print(Fmath.truncate(this.transformedSampleR, 4), var8);
        var3.print(" ", var8);
        var3.println(Fmath.truncate(this.originalSampleR, 4));
        var3.print("  Gradient", var7);
        var3.print(Fmath.truncate(this.transformedGradient, 4), var8);
        var3.print(Fmath.truncate(this.transformedGradientError, 4), var8);
        var3.print(Fmath.truncate(this.originalGradient, 4), var8);
        var3.println(Fmath.truncate(this.originalGradientError, 4));
        var3.print("  Intercept", var7);
        var3.print(Fmath.truncate(this.transformedIntercept, 4), var8);
        var3.print(Fmath.truncate(this.transformedInterceptError, 4), var8);
        var3.print(Fmath.truncate(this.originalIntercept, 4), var8);
        var3.println(Fmath.truncate(this.originalInterceptError, 4));
        var3.println();
        var3.print("Data ");
        var3.println();
        var3.print("  Mean", var7);
        var3.print(Fmath.truncate(this.transformedMean, 4), var8);
        var3.print("  ", var8);
        var3.println(Fmath.truncate(this.originalMean, 4));
        var3.print("  Median", var7);
        var3.print(Fmath.truncate(this.transformedMedian, 4), var8);
        var3.print("  ", var8);
        var3.println(Fmath.truncate(this.originalMedian, 4));
        var3.print("  Standard deviation", var7);
        var3.print(Fmath.truncate(this.transformedStandardDeviation, 4), var8);
        var3.print("  ", var8);
        var3.println(Fmath.truncate(this.originalStandardDeviation, 4));
        var3.print("  Standard error", var7);
        var3.print(Fmath.truncate(this.transformedStandardDeviation / Math.sqrt((double)this.nData), 4), var8);
        var3.print("  ", var8);
        var3.println(Fmath.truncate(this.originalStandardDeviation / Math.sqrt((double)this.nData), 4));
        var3.print("  Moment skewness", var7);
        var3.print(Fmath.truncate(this.transformedMomentSkewness, 4), var8);
        var3.print("  ", var8);
        var3.println(Fmath.truncate(this.originalMomentSkewness, 4));
        var3.print("  Median skewness", var7);
        var3.print(Fmath.truncate(this.transformedMedianSkewness, 4), var8);
        var3.print("  ", var8);
        var3.println(Fmath.truncate(this.originalMedianSkewness, 4));
        var3.print("  Quartile skewness", var7);
        var3.print(Fmath.truncate(this.transformedQuartileSkewness, 4), var8);
        var3.print("  ", var8);
        var3.println(Fmath.truncate(this.originalQuartileSkewness, 4));
        var3.print("  Excess kurtosis", var7);
        var3.print(Fmath.truncate(this.transformedExcessKurtosis, 4), var8);
        var3.print("  ", var8);
        var3.println(Fmath.truncate(this.originalExcessKurtosis, 4));
        var3.print("  Minimum", var7);
        var3.print(Fmath.truncate(this.transformedMinimum, 4), var8);
        var3.print("  ", var8);
        var3.println(Fmath.truncate(this.originalMinimum, 4));
        var3.print("  Maximum", var7);
        var3.print(Fmath.truncate(this.transformedMaximum, 4), var8);
        var3.print("  ", var8);
        var3.println(Fmath.truncate(this.originalMaximum, 4));
        var3.print("  Range", var7);
        var3.print(Fmath.truncate(this.transformedRange, 4), var8);
        var3.print("  ", var8);
        var3.println(Fmath.truncate(this.originalRange, 4));
        var3.close();
    }
}
