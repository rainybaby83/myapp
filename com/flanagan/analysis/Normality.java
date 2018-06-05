//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.analysis;

import com.flanagan.io.FileChooser;
import com.flanagan.io.FileOutput;
import com.flanagan.math.ArrayMaths;
import com.flanagan.math.Fmath;
import com.flanagan.math.TimeAndDate;
import com.flanagan.util.Strings;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

public class Normality {
    private double[] data = null;
    private double[] orderedData = null;
    private int[] originalindices = null;
    private double[] orderStatisticMedians = null;
    private int nPoints = 0;
    private double mean = 0.0D / 0.0;
    private double standardDeviation = 0.0D / 0.0;
    private double median = 0.0D / 0.0;
    private double momentSkewness = 0.0D / 0.0;
    private double medianSkewness = 0.0D / 0.0;
    private double quartileSkewness = 0.0D / 0.0;
    private double kurtosis = 0.0D / 0.0;
    private double curtosis = 0.0D / 0.0;
    private double excessKurtosis = 0.0D / 0.0;
    private double excessCurtosis = 0.0D / 0.0;
    private double maximum = 0.0D / 0.0;
    private double minimum = 0.0D / 0.0;
    private double range = 0.0D / 0.0;
    private double sumOfSquares = 0.0D / 0.0;
    private double[] shapiroWilkCoeff = null;
    private int nSWcoeff = 0;
    private boolean swCoeffDone = false;
    private double shapiroWilkWvalue = 0.0D / 0.0;
    private boolean swWvalueDone = false;
    private double shapiroWilkPvalue = 0.0D / 0.0;
    private boolean swPvalueDone = false;
    private double[] swWvalues = null;
    private double shapiroWilkCriticalWvalue = 0.0D / 0.0;
    private boolean swPCritDone = false;
    private double significance = 0.05D;
    private int nSimulations = 10000;
    private ProbabilityPlot pp = null;
    private String filenameout = "NormalityAnalysis.txt";
    private String filenamein = null;
    private TimeAndDate tad = new TimeAndDate();
    private String time_date;
    private int trunc = 4;
    private int field0 = 35;
    private int field = 15;

    public Normality(double[] var1) {
        this.data = var1;
        this.initialise();
    }

    public Normality(float[] var1) {
        ArrayMaths var2 = new ArrayMaths(var1);
        this.data = var2.array();
        this.initialise();
    }

    public Normality(long[] var1) {
        ArrayMaths var2 = new ArrayMaths(var1);
        this.data = var2.array();
        this.initialise();
    }

    public Normality(int[] var1) {
        ArrayMaths var2 = new ArrayMaths(var1);
        this.data = var2.array();
        this.initialise();
    }

    public Normality(BigDecimal[] var1) {
        ArrayMaths var2 = new ArrayMaths(var1);
        this.data = var2.array_as_double();
        this.initialise();
    }

    public Normality(BigInteger[] var1) {
        ArrayMaths var2 = new ArrayMaths(var1);
        this.data = var2.array_as_double();
        this.initialise();
    }

    public Normality() {
        this.data = null;
    }

    public void readDataFromTextFile() {
        FileChooser var1 = new FileChooser();
        this.filenamein = var1.selectFile();
        ArrayList var2 = new ArrayList();
        int var3 = var1.numberOfLines();

        int var4;
        for(var4 = 0; var4 < var3; ++var4) {
            String var5 = var1.readLine();
            var5 = this.replacements(var5);
            String[] var6 = Strings.tokens(var5);
            int var7 = var6.length;

            for(int var8 = 0; var8 < var7; ++var8) {
                double var9 = Double.parseDouble(var6[var8]);
                var2.add(var9);
            }
        }

        var1.close();
        this.nPoints = var2.size();
        this.data = new double[this.nPoints];

        for(var4 = 0; var4 < this.nPoints; ++var4) {
            this.data[var4] = (Double)var2.get(var4);
        }

        this.initialise();
    }

    public String replacements(String var1) {
        int var2 = var1.length();
        char[] var3 = new char[var2];
        String var4 = "";

        for(int var5 = 0; var5 < var2; ++var5) {
            var3[var5] = var1.charAt(var5);
            if (var3[var5] == ',') {
                var3[var5] = ' ';
            }

            if (var3[var5] == ';') {
                var3[var5] = ' ';
            }

            if (var3[var5] == ':') {
                var3[var5] = ' ';
            }

            if (var3[var5] == '\t') {
                var3[var5] = ' ';
            }

            var4 = var4 + var3[var5];
        }

        return var4;
    }

    private void initialise() {
        this.nPoints = this.data.length;
        ArrayMaths var1 = new ArrayMaths(this.data);
        var1 = var1.sort();
        this.orderedData = var1.array();
        this.originalindices = var1.originalIndices();
        this.mean = Stat.mean(this.data);
        this.median = Stat.median(this.data);
        this.minimum = Fmath.minimum(this.data);
        this.maximum = Fmath.maximum(this.data);
        this.range = this.maximum - this.minimum;
        this.standardDeviation = Stat.standardDeviation(this.data);
        this.momentSkewness = Stat.momentSkewness(this.data);
        this.medianSkewness = Stat.medianSkewness(this.data);
        this.quartileSkewness = Stat.quartileSkewness(this.data);
        this.kurtosis = Stat.kurtosis(this.data);
        this.curtosis = Stat.kurtosis(this.data);
        this.excessKurtosis = Stat.excessKurtosis(this.data);
        this.excessCurtosis = Stat.excessKurtosis(this.data);
        this.sumOfSquares = 0.0D;

        for(int var2 = 0; var2 < this.nPoints; ++var2) {
            double var3 = this.data[var2] - this.mean;
            this.sumOfSquares += var3 * var3;
        }

        this.pp = new ProbabilityPlot(this.data);
        this.pp.suppressErrorMessages();
        this.pp.suppressFileOutput();
        this.time_date = this.tad.getShortTime24() + ", ";
        this.time_date = this.time_date + this.tad.getDate();
    }

    public void resetSignificanceLevel(double var1) {
        this.significance = var1;
    }

    public double getSignificanceLevel() {
        return this.significance;
    }

    public double mean() {
        return this.mean;
    }

    public double standardDeviation() {
        return this.standardDeviation;
    }

    public double momentSkewness() {
        return this.momentSkewness;
    }

    public double medianSkewness() {
        return this.medianSkewness;
    }

    public double quartileSkewness() {
        return this.quartileSkewness;
    }

    public double kurtosis() {
        return this.kurtosis;
    }

    public double curtosis() {
        return this.curtosis;
    }

    public double excessKurtosis() {
        return this.excessKurtosis;
    }

    public double excessCurtosis() {
        return this.excessCurtosis;
    }

    public double[] getData() {
        return this.data;
    }

    public double[] getOrderedOriginalData() {
        return this.orderedData;
    }

    public double[] getDataOrderStatisticMedians() {
        return this.orderStatisticMedians;
    }

    public double shapiroWilkWvalue() {
        if (!this.swCoeffDone) {
            this.shapiroWilkCoeff();
        }

        double var1 = 0.0D;

        for(int var3 = 0; var3 < this.nSWcoeff; ++var3) {
            int var4 = this.nPoints - var3 - 1;
            var1 += this.shapiroWilkCoeff[var3] * (this.orderedData[var4] - this.orderedData[var3]);
        }

        var1 *= var1;
        this.shapiroWilkWvalue = var1 / this.sumOfSquares;
        this.swWvalueDone = true;
        return this.shapiroWilkWvalue;
    }

    public double shapiroWilkPvalue() {
        if (!this.swCoeffDone) {
            this.shapiroWilkCoeff();
        }

        if (!this.swWvalueDone) {
            this.shapiroWilkWvalue();
        }

        this.swWvalues = new double[this.nSimulations];
        double[] var1 = new double[this.nPoints];
        int var2 = 0;

        for(int var3 = 0; var3 < this.nSimulations; ++var3) {
            var1 = Stat.gaussianRand(this.mean, this.standardDeviation, this.nPoints);
            Normality var4 = new Normality(var1);
            this.swWvalues[var3] = var4.shapiroWilkWvalue();
            if (this.shapiroWilkWvalue > this.swWvalues[var3]) {
                ++var2;
            }
        }

        ArrayMaths var5 = new ArrayMaths(this.swWvalues);
        this.swWvalues = var5.sort().array();
        this.shapiroWilkPvalue = (double)var2 / (double)this.nSimulations;
        this.swPvalueDone = true;
        return this.shapiroWilkPvalue;
    }

    public double shapiroWilkCriticalW() {
        if (!this.swCoeffDone) {
            this.shapiroWilkCoeff();
        }

        if (!this.swWvalueDone) {
            this.shapiroWilkWvalue();
        }

        if (!this.swPvalueDone) {
            this.shapiroWilkPvalue();
        }

        int var1 = (int)Math.round((double)this.nSimulations * this.significance);
        this.shapiroWilkCriticalWvalue = this.swWvalues[var1];
        this.swPCritDone = true;
        return this.shapiroWilkCriticalWvalue;
    }

    public double shapiroWilkCriticalW(double var1) {
        if (!this.swCoeffDone) {
            this.shapiroWilkCoeff();
        }

        if (!this.swWvalueDone) {
            this.shapiroWilkWvalue();
        }

        if (!this.swPvalueDone) {
            this.shapiroWilkPvalue();
        }

        int var3 = (int)Math.round((double)this.nSimulations * var1);
        return this.swWvalues[var3];
    }

    public double shapiroWilkCriticalW(double var1, int var3) {
        double var4 = 0.0D / 0.0;
        if (var3 == this.nPoints) {
            var4 = this.shapiroWilkCriticalW(var1);
        } else {
            double[] var6 = new double[this.nSimulations];
            double[] var7 = new double[var3];

            for(int var8 = 0; var8 < this.nSimulations; ++var8) {
                var7 = Stat.gaussianRand(0.0D, 1.0D, var3);
                Normality var9 = new Normality(var7);
                var6[var8] = var9.shapiroWilkWvalue();
            }

            ArrayMaths var10 = new ArrayMaths(var6);
            var6 = var10.sort().array();
            int var11 = (int)Math.round((double)this.nSimulations * var1);
            var4 = var6[var11];
        }

        return var4;
    }

    public double[] shapiroWilkCoeff() {
        return this.shapiroWilkCoeff(this.nPoints);
    }

    public double[] shapiroWilkCoeff(int var1) {
        int var3 = var1 / 2;
        this.nSWcoeff = var3;
        if (var1 < 2) {
            var3 = 1;
        }

        double[] var4 = new double[var3 + 1];
        var4[0] = 0.0D / 0.0;
        double var6 = 0.0D;
        double var8 = 0.0D;
        double[] var20 = new double[]{0.0D / 0.0, 0.0D, 0.221157D, -0.147981D, -2.07119D, 4.434685D, -2.706056D};
        double[] var21 = new double[]{0.0D / 0.0, 0.0D, 0.042981D, -0.293762D, -1.752461D, 5.682633D, -3.582633D};
        double var22 = 0.0D;
        double var24 = 1.0D;
        double var26 = 2.0D;
        double var28 = 0.70711D;
        double var30 = 0.25D;
        double var32 = 0.375D;
        if (var1 < 2) {
            var4[1] = 0.0D;
        } else if (var1 != 2 && var1 != 3) {
            double var14 = (double)var1 + var30;
            var6 = var22;

            int var34;
            for(var34 = 1; var34 <= var3; ++var34) {
                var4[var34] = this.ppnd(((double)var34 - var32) / var14);
                var6 += Math.pow(var4[var34], 2.0D);
            }

            var6 *= var26;
            var8 = Math.sqrt(var6);
            double var12 = var24 / Math.sqrt((double)var1);
            double var16 = this.poly(var20, 6, var12) - var4[1] / var8;
            byte var5;
            double var10;
            if (var1 > 5) {
                var5 = 3;
                double var18 = -var4[2] / var8 + this.poly(var21, 6, var12);
                var10 = Math.sqrt((var6 - var26 * Math.pow(var4[1], 2.0D) - var26 * Math.pow(var4[2], 2.0D)) / (var24 - var26 * Math.pow(var16, 2.0D) - var26 * Math.pow(var18, 2.0D)));
                var4[1] = var16;
                var4[2] = var18;
            } else {
                var5 = 2;
                var10 = Math.sqrt((var6 - var26 * Math.pow(var4[1], 2.0D)) / (var24 - var26 * Math.pow(var16, 2.0D)));
                var4[1] = var16;
            }

            for(var34 = var5; var34 <= var3; ++var34) {
                var4[var34] = -var4[var34] / var10;
            }
        } else {
            var4[1] = var28;
        }

        this.shapiroWilkCoeff = this.shiftDown(var4);
        this.swCoeffDone = true;
        return this.shapiroWilkCoeff;
    }

    public double ppnd(double var1) {
        double var3 = 0.42D;
        double var5 = 2.50662823884D;
        double var7 = -18.61500062529D;
        double var9 = 41.39119773534D;
        double var11 = -25.44106049637D;
        double var13 = -8.4735109309D;
        double var15 = 23.08336743743D;
        double var17 = -21.06224101826D;
        double var19 = 3.13082909833D;
        double var21 = -2.78718931138D;
        double var23 = -2.29796479134D;
        double var25 = 4.85014127135D;
        double var27 = 2.32121276858D;
        double var29 = 3.54388924762D;
        double var31 = 1.63706781897D;
        double var33 = 0.0D;
        double var35 = 1.0D;
        double var37 = 0.5D;
        boolean var39 = false;
        double var40 = var1 - var37;
        boolean var42 = false;
        if (Math.abs(var40) > var3) {
            var42 = true;
        }

        double var43;
        if (!var42) {
            var43 = var40 * var40;
            double var48 = var40 * (((var11 * var43 + var9) * var43 + var7) * var43 + var5) / ((((var19 * var43 + var17) * var43 + var15) * var43 + var13) * var43 + var35);
            return var48;
        } else {
            var43 = var1;
            if (var40 > var33) {
                var43 = var35 - var1;
            }

            boolean var45 = false;
            if (var43 <= var33) {
                var45 = true;
            }

            if (!var45) {
                var43 = Math.sqrt(-Math.log(var43));
                double var46 = (((var27 * var43 + var25) * var43 + var23) * var43 + var21) / ((var31 * var43 + var29) * var43 + var35);
                if (var40 < var33) {
                    var46 = -var46;
                }

                return var46;
            } else {
                return var33;
            }
        }
    }

    public double poly(double[] var1, int var2, double var3) {
        double var5 = var1[1];
        if (var2 == 1) {
            return var5;
        } else {
            double var7 = var3 * var1[var2];
            boolean var9 = false;
            if (var2 == 2) {
                var9 = true;
            }

            if (!var9) {
                int var10 = var2 - 2;
                int var11 = var10 + 1;

                for(int var12 = 1; var12 <= var10; ++var12) {
                    var7 = (var7 + var1[var11]) * var3;
                    --var11;
                }
            }

            var5 += var7;
            return var5;
        }
    }

    public double alnorm(double var1, boolean var3) {
        double var9 = 0.0D;
        double var11 = 1.0D;
        double var13 = 0.5D;
        double var15 = 7.0D;
        double var17 = 18.66D;
        double var19 = 1.28D;
        double var21 = 0.398942280444D;
        double var23 = 0.39990348504D;
        double var25 = 0.398942280385D;
        double var27 = 5.75885480458D;
        double var29 = 2.62433121679D;
        double var31 = 5.92885724438D;
        double var33 = -29.8213557807D;
        double var35 = 48.6959930692D;
        double var37 = -3.8052E-8D;
        double var39 = 3.98064794E-4D;
        double var41 = -0.151679116635D;
        double var43 = 4.8385912808D;
        double var45 = 0.742380924027D;
        double var47 = 3.99019417011D;
        double var49 = 1.00000615302D;
        double var51 = 1.98615381364D;
        double var53 = 5.29330324926D;
        double var55 = -15.1508972451D;
        double var57 = 30.789933034D;
        boolean var8 = var3;
        double var4 = var1;
        boolean var59 = false;
        if (var1 >= var9) {
            var59 = true;
        }

        if (!var59) {
            if (!var3) {
                var8 = true;
            } else {
                var8 = true;
            }

            var4 = -var1;
        }

        double var60 = 0.0D;
        boolean var62 = false;
        boolean var63 = false;
        boolean var64 = false;
        if (var4 <= var15 || var8 && var4 <= var17) {
            var64 = true;
        }

        if (!var64) {
            var60 = var9;
            var62 = true;
        }

        if (!var62) {
            double var6 = var13 * var4 * var4;
            if (var4 > var19) {
                var63 = true;
            }

            if (!var63) {
                var60 = var13 - var4 * (var21 - var23 * var6 / (var6 + var27 + var33 / (var6 + var29 + var35 / (var6 + var31))));
                var62 = true;
            }

            if (!var62) {
                var60 = var25 * Math.exp(-var6) / (var4 + var37 + var49 / (var4 + var39 + var51 / (var4 + var41 + var53 / (var4 + var43 + var55 / (var4 + var45 + var57 / (var4 + var47))))));
            }
        }

        if (!var8) {
            var60 = var11 - var60;
        }

        return var60;
    }

    public double swapSign(double var1, double var3) {
        double var5 = Math.abs(var1);
        if (var3 < 0.0D) {
            var5 = -var5;
        }

        return var5;
    }

    public int swapSign(int var1, int var2) {
        int var3 = Math.abs(var1);
        if (var2 < 0) {
            var3 = -var3;
        }

        return var3;
    }

    public double[] shiftUp(double[] var1) {
        int var2 = var1.length;
        double[] var3 = new double[var2 + 1];
        var3[0] = 0.0D / 0.0;

        for(int var4 = 0; var4 < var2; ++var4) {
            var3[var4 + 1] = var1[var4];
        }

        return var3;
    }

    public double[] shiftDown(double[] var1) {
        int var2 = var1.length;
        double[] var3 = new double[var2 - 1];

        for(int var4 = 0; var4 < var2 - 1; ++var4) {
            var3[var4] = var1[var4 + 1];
        }

        return var3;
    }

    public void resetNsimulation(int var1) {
        this.nSimulations = var1;
    }

    public int getNsimulation() {
        return this.nSimulations;
    }

    public void gaussianUserSuppliedInitialEstimates(double var1, double var3) {
        this.pp.gaussianUserSuppliedInitialEstimates(var1, var3);
    }

    public void normalUserSuppliedInitialEstimates(double var1, double var3) {
        this.pp.normalUserSuppliedInitialEstimates(var1, var3);
    }

    public void removeGaussianUserSuppliedInitialEstimates() {
        this.pp.removeGaussianUserSuppliedInitialEstimates();
    }

    public void removeNormalUserSuppliedInitialEstimates() {
        this.pp.removeNormalUserSuppliedInitialEstimates();
    }

    public void gaussianProbabilityPlot() {
        this.pp.gaussianProbabilityPlot();
    }

    public void normalProbabilityPlot() {
        this.pp.normalProbabilityPlot();
    }

    public double gaussianMu() {
        return this.pp.gaussianMu();
    }

    public double gaussianMuError() {
        return this.pp.gaussianMuError();
    }

    public double gaussianSigma() {
        return this.pp.gaussianSigma();
    }

    public double gaussianSigmaError() {
        return this.pp.gaussianSigmaError();
    }

    public double gaussianGradient() {
        return this.pp.gaussianGradient();
    }

    public double gaussianGradientError() {
        return this.pp.gaussianGradientError();
    }

    public double gaussianIntercept() {
        return this.pp.gaussianIntercept();
    }

    public double gaussianInterceptError() {
        return this.pp.gaussianInterceptError();
    }

    public double gaussianCorrelationCoefficient() {
        return this.pp.gaussianCorrelationCoefficient();
    }

    public double gaussianSumOfSquares() {
        return this.pp.gaussianSumOfSquares();
    }

    public double gaussianWeightedSumOfSquares() {
        return this.pp.gaussianWeightedSumOfSquares();
    }

    public double[] gaussianOrderStatisticMedians() {
        return this.pp.gaussianOrderStatisticMedians();
    }

    public double normalMu() {
        return this.pp.normalMu();
    }

    public double normalMuError() {
        return this.pp.normalMuError();
    }

    public double normalSigma() {
        return this.pp.normalSigma();
    }

    public double normalSigmaError() {
        return this.pp.normalSigmaError();
    }

    public double normalGradient() {
        return this.pp.normalGradient();
    }

    public double normalGradientError() {
        return this.pp.normalGradientError();
    }

    public double normalIntercept() {
        return this.pp.normalIntercept();
    }

    public double normalInterceptError() {
        return this.pp.normalInterceptError();
    }

    public double normalCorrelationCoefficient() {
        return this.pp.normalCorrelationCoefficient();
    }

    public double normalSumOfSquares() {
        return this.pp.normalSumOfSquares();
    }

    public double normalWeightedSumOfSquares() {
        return this.pp.normalWeightedSumOfSquares();
    }

    public double[] normalOrderStatisticMedians() {
        return this.pp.normalOrderStatisticMedians();
    }

    public void gaussianStandardProbabilityPlot() {
        this.pp.gaussianStandardProbabilityPlot();
    }

    public void normalStandardProbabilityPlot() {
        this.pp.normalStandardProbabilityPlot();
    }

    public double gaussianStandardGradient() {
        return this.pp.gaussianStandardGradient();
    }

    public double gaussianStandardGradientError() {
        return this.pp.gaussianStandardGradientError();
    }

    public double gaussianStandardIntercept() {
        return this.pp.gaussianStandardIntercept();
    }

    public double gaussianStandardInterceptError() {
        return this.pp.gaussianStandardInterceptError();
    }

    public double gaussianStandardCorrelationCoefficient() {
        return this.pp.gaussianStandardCorrelationCoefficient();
    }

    public double gaussianStandardSumOfSquares() {
        return this.pp.gaussianStandardSumOfSquares();
    }

    public double[] gaussianStandardOrderStatisticMedians() {
        return this.pp.gaussianStandardOrderStatisticMedians();
    }

    public double normalStandardGradient() {
        return this.pp.normalStandardGradient();
    }

    public double normalstandardGradientError() {
        return this.pp.normalstandardGradientError();
    }

    public double normalStandardInterceptError() {
        return this.pp.normalStandardInterceptError();
    }

    public double normalStandardCorrelationCoefficient() {
        return this.pp.normalStandardCorrelationCoefficient();
    }

    public double normalStandardSumOfSquares() {
        return this.pp.normalStandardSumOfSquares();
    }

    public double normalStandardWeightedSumOfSquares() {
        return this.pp.normalStandardWeightedSumOfSquares();
    }

    public double[] normalStandardOrderStatisticMedians() {
        return this.pp.normalStandardOrderStatisticMedians();
    }

    public void fullAnalysis() {
        this.fullAnalysis(this.filenameout);
    }

    public void fullAnalysis(String var1) {
        int var2 = var1.indexOf(".");
        if (var2 == -1) {
            var1 = var1 + ".txt";
        }

        this.filenameout = var1;
        FileOutput var3 = new FileOutput(this.filenameout);
        var3.println("Normality Analysis");
        if (this.filenamein != null) {
            var3.println("Input file: " + this.filenamein);
        }

        var3.println();
        var3.println("File name: " + this.filenameout);
        var3.println("Run date: " + this.time_date);
        var3.println();
        var3.print("Parameter / statistic", this.field0);
        var3.print("Value", this.field);
        var3.println("Error");
        var3.print("Number of data points: ", this.field0);
        var3.println(this.nPoints);
        var3.print("Sample mean: ", this.field0);
        var3.println(Fmath.truncate(this.mean, this.trunc));
        var3.print("Sample median: ", this.field0);
        var3.println(Fmath.truncate(this.median, this.trunc));
        var3.print("Sample minimum: ", this.field0);
        var3.println(Fmath.truncate(this.minimum, this.trunc));
        var3.print("Sample maximum: ", this.field0);
        var3.println(Fmath.truncate(this.maximum, this.trunc));
        var3.print("Sample range: ", this.field0);
        var3.println(Fmath.truncate(this.range, this.trunc));
        var3.print("Sample standard deviation: ", this.field0);
        var3.println(Fmath.truncate(this.standardDeviation, this.trunc));
        var3.print("Sample moment skewnesss: ", this.field0);
        var3.println(Fmath.truncate(this.momentSkewness, this.trunc));
        var3.print("Sample median skewnesss: ", this.field0);
        var3.println(Fmath.truncate(this.medianSkewness, this.trunc));
        var3.print("Sample quartile skewnesss: ", this.field0);
        var3.println(Fmath.truncate(this.quartileSkewness, this.trunc));
        var3.print("Sample kurtosis: ", this.field0);
        var3.println(Fmath.truncate(this.kurtosis, this.trunc));
        var3.print("Sample excess kurtosis: ", this.field0);
        var3.println(Fmath.truncate(this.excessKurtosis, this.trunc));
        var3.println();
        var3.println("Shapiro-Wilk W test");
        if (!this.swWvalueDone) {
            this.shapiroWilkWvalue = this.shapiroWilkWvalue();
        }

        var3.print(" Shapiro-Wilk W value: ", this.field0);
        var3.println(Fmath.truncate(this.shapiroWilkWvalue, this.trunc));
        if (!this.swPCritDone) {
            this.shapiroWilkCriticalWvalue = this.shapiroWilkCriticalW();
        }

        var3.print(" Shapiro-Wilk W critical value: ", this.field0);
        var3.println(Fmath.truncate(this.shapiroWilkCriticalWvalue, this.trunc));
        if (!this.swPvalueDone) {
            this.shapiroWilkPvalue = this.shapiroWilkPvalue();
        }

        var3.print(" Shapiro-Wilk P value: ", this.field0);
        var3.println(Fmath.truncate(this.shapiroWilkPvalue, this.trunc));
        String var4 = "Accepted";
        if (this.shapiroWilkWvalue < this.shapiroWilkCriticalWvalue) {
            var4 = "Rejected";
        }

        var3.print(" Data possibly normal: ", this.field0);
        var3.println(var4);
        var3.println();
        var3.println("Probability plot:");
        this.pp.gaussianProbabilityPlot();
        var3.print(" Correlation coefficient, r: ", this.field0);
        double var5 = this.pp.gaussianCorrelationCoefficient();
        var3.println(Fmath.truncate(var5, this.trunc));
        var3.println(" Critical value for r: ");
        double var7 = this.pp.correlationCoefficientCriticalValue();
        var3.print("  (" + this.significance * 100.0D + "% Significance level)", this.field0);
        var3.println(Fmath.truncate(var7, this.trunc));
        var3.print(" Data possibly normal: ", this.field0);
        String var9 = "Rejected";
        if (var5 >= var7) {
            var9 = "Accepted";
        }

        var3.println(var9);
        var3.print(" Gradient: ", this.field0);
        var3.print(Fmath.truncate(this.pp.gaussianGradient(), this.trunc), this.field);
        var3.println(Fmath.truncate(this.pp.gaussianGradientError(), this.trunc));
        var3.print(" Intercept: ", this.field0);
        var3.print(Fmath.truncate(this.pp.gaussianIntercept(), this.trunc), this.field);
        var3.println(Fmath.truncate(this.pp.gaussianInterceptError(), this.trunc));
        var3.print(" Mean (best estimate): ", this.field0);
        var3.print(Fmath.truncate(this.pp.gaussianMu(), this.trunc), this.field);
        var3.println(Fmath.truncate(this.pp.gaussianMuError(), this.trunc));
        var3.print(" Stand. devn. (best estimate): ", this.field0);
        var3.print(Fmath.truncate(this.pp.gaussianSigma(), this.trunc), this.field);
        var3.println(Fmath.truncate(this.pp.gaussianSigmaError(), this.trunc));
        var3.println();
        var3.println("Input data: ");
        int var10 = 0;

        for(int var11 = 0; var11 < this.nPoints; ++var11) {
            var3.print(this.data[var11] + "  ");
            ++var10;
            if (var10 == 10) {
                var3.println();
                var10 = 0;
            }
        }

        var3.close();
    }
}
