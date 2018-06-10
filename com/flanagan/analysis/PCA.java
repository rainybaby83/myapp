//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.analysis;

import com.flanagan.io.Db;
import com.flanagan.io.FileOutput;
import com.flanagan.math.ArrayMaths;
import com.flanagan.math.Conv;
import com.flanagan.math.Fmath;
import com.flanagan.math.Matrix;
import com.flanagan.math.PsRandom;
import com.flanagan.plot.PlotGraph;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.text.DateFormat;
import java.util.Date;

public class PCA extends Scores {
    private Matrix data = null;
    private Matrix dataMinusMeans = null;
    private Matrix dataMinusMeansTranspose = null;
    private Matrix covarianceMatrix = null;
    private Matrix correlationMatrix = null;
    private Matrix partialCorrelationMatrix = null;
    private double kmo = 0.0D;
    private double[] itemKMOs = null;
    private double chiSquareBartlett = 0.0D;
    private int dfBartlett = 0;
    private double probBartlett = 0.0D;
    private double sign10Bartlett = 0.0D;
    private double sign05Bartlett = 0.0D;
    private boolean bartlettDone = false;
    private double[] eigenValues = null;
    private double[] orderedEigenValues = null;
    private int[] eigenValueIndices = null;
    private double eigenValueTotal = 0.0D;
    private int[] rotatedIndices = null;
    private double[] rotatedEigenValues = null;
    private double[] usRotatedEigenValues = null;
    private int nMonteCarlo = 200;
    private double[][] randomEigenValues = (double[][])null;
    private double[] randomEigenValuesMeans = null;
    private double[] randomEigenValuesSDs = null;
    private double[] randomEigenValuesPercentiles = null;
    private double percentile = 95.0D;
    private boolean gaussianDeviates = false;
    private double[] proportionPercentage = null;
    private double[] cumulativePercentage = null;
    private double[] rotatedProportionPercentage = null;
    private double[] rotatedCumulativePercentage = null;
    private double[][] eigenVectorsAsColumns = (double[][])null;
    private double[][] eigenVectorsAsRows = (double[][])null;
    private double[][] orderedEigenVectorsAsColumns = (double[][])null;
    private double[][] orderedEigenVectorsAsRows = (double[][])null;
    private double[][] loadingFactorsAsColumns = (double[][])null;
    private double[][] loadingFactorsAsRows = (double[][])null;
    private double[][] rotatedLoadingFactorsAsColumns = (double[][])null;
    private double[][] rotatedLoadingFactorsAsRows = (double[][])null;
    private double[][] usRotatedLoadingFactorsAsColumns = (double[][])null;
    private double[][] usRotatedLoadingFactorsAsRows = (double[][])null;
    private double[] communalities = null;
    private double[] communalityWeights = null;
    private boolean covRhoOption = false;
    private int greaterThanOneLimit = 0;
    private int percentileCrossover = 0;
    private int meanCrossover = 0;
    private int nVarimaxMax = 1000;
    private int nVarimax = 0;
    private double varimaxTolerance = 1.0E-8D;
    private boolean varimaxOption = true;
    private boolean pcaDone = false;
    private boolean monteCarloDone = false;
    private boolean rotationDone = false;

    public PCA() {
        super.trunc = 4;
    }

    public void useCovarianceMatrix() {
        this.covRhoOption = true;
    }

    public void useCorrelationMatrix() {
        this.covRhoOption = false;
    }

    public void useNormalVarimax() {
        this.varimaxOption = true;
    }

    public void useRawVarimax() {
        this.varimaxOption = false;
    }

    public String getVarimaxOption() {
        return this.varimaxOption ? "normal varimax option" : "raw varimax option";
    }

    public void setNumberOfSimulations(int var1) {
        this.nMonteCarlo = var1;
    }

    public int getNumberOfSimulations() {
        return this.nMonteCarlo;
    }

    public void useGaussianDeviates() {
        this.gaussianDeviates = true;
    }

    public void useUniformDeviates() {
        this.gaussianDeviates = false;
    }

    public void setParallelAnalysisPercentileValue(double var1) {
        this.percentile = var1;
    }

    public double getParallelAnalysisPercentileValue() {
        return this.percentile;
    }

    public void pca() {
        if (!this.pcaDone) {
            if (this.nItems == 1) {
                throw new IllegalArgumentException("You have entered only one item - PCA is not meaningful");
            }

            if (this.nPersons == 1) {
                throw new IllegalArgumentException("You have entered only one score or measurement source - PCA is not meaningful");
            }

            if (!this.dataPreprocessed) {
                this.preprocessData();
            }

            this.data = new Matrix(super.scores0);
            this.dataMinusMeans = this.data.subtractRowMeans();
            this.dataMinusMeansTranspose = this.dataMinusMeans.transpose();
            this.covarianceMatrix = this.dataMinusMeans.times(this.dataMinusMeansTranspose);
            double var1 = (double)this.nPersons;
            if (!super.nFactorOption) {
                --var1;
            }

            this.covarianceMatrix = this.covarianceMatrix.times(1.0D / var1);
            boolean var3 = false;
            double[][] var4 = this.covarianceMatrix.getArrayCopy();
            double[][] var5 = new double[this.nItems][this.nItems];

            for(int var6 = 0; var6 < this.nItems; ++var6) {
                for(int var7 = 0; var7 < this.nItems; ++var7) {
                    if (var6 == var7) {
                        var5[var6][var7] = 1.0D;
                    } else {
                        var5[var6][var7] = var4[var6][var7] / Math.sqrt(var4[var6][var6] * var4[var7][var7]);
                        if (Fmath.isNaN(var5[var6][var7])) {
                            var5[var6][var7] = 0.0D;
                        }
                    }
                }
            }

            this.correlationMatrix = new Matrix(var5);
            double[][] var24 = new double[this.nItems][this.nItems];
            double[][] var25 = new double[this.nItems][this.nItems];

            int var8;
            int var9;
            for(var8 = 0; var8 < this.nItems; ++var8) {
                for(var9 = 0; var9 < this.nItems; ++var9) {
                    var24[var8][var9] = this.correlationMatrix.cofactor(var8, var9);
                }
            }

            for(var8 = 0; var8 < this.nItems; ++var8) {
                for(var9 = 0; var9 < this.nItems; ++var9) {
                    if (var24[var8][var9] == 0.0D && var24[var8][var8] == 0.0D && var24[var9][var9] == 0.0D) {
                        var25[var8][var9] = 1.0D;
                    } else if (var8 == var9) {
                        var25[var8][var9] = 1.0D;
                    } else {
                        var25[var8][var9] = -var24[var8][var9] / Math.sqrt(var24[var8][var8] * var24[var9][var9]);
                    }
                }
            }

            this.partialCorrelationMatrix = new Matrix(var25);
            double var26 = 0.0D;
            double var10 = 0.0D;

            int var12;
            int var13;
            for(var12 = 0; var12 < this.nItems; ++var12) {
                for(var13 = 0; var13 < this.nItems; ++var13) {
                    if (var12 != var13) {
                        var26 += var5[var12][var13] * var5[var12][var13];
                        var10 += var25[var12][var13] * var25[var12][var13];
                    }
                }
            }

            if (var26 == 0.0D && var10 == 0.0D) {
                this.kmo = 0.5D;
            } else {
                this.kmo = var26 / (var26 + var10);
            }

            this.itemKMOs = new double[this.nItems];

            for(var12 = 0; var12 < this.nItems; ++var12) {
                var26 = 0.0D;
                var10 = 0.0D;

                for(var13 = 0; var13 < this.nItems; ++var13) {
                    if (var12 != var13) {
                        var26 += var5[var12][var13] * var5[var12][var13];
                        var10 += var25[var12][var13] * var25[var12][var13];
                    }
                }

                if (var26 == 0.0D && var10 == 0.0D) {
                    this.itemKMOs[var12] = 0.5D;
                } else {
                    this.itemKMOs[var12] = var26 / (var26 + var10);
                }
            }

            double var27 = this.correlationMatrix.determinant();
            this.chiSquareBartlett = -((double)(this.nPersons - 1) - (double)(2 * this.nItems + 5) / 6.0D) * Math.log(Math.abs(var27));
            if (this.chiSquareBartlett >= 0.0D) {
                this.dfBartlett = this.nItems * (this.nItems - 1) / 2;
                this.probBartlett = 1.0D - Stat.chiSquareCDF(this.chiSquareBartlett, this.dfBartlett);
                this.sign10Bartlett = Stat.chiSquareInverseCDF(this.dfBartlett, 0.9D);
                this.sign05Bartlett = Stat.chiSquareInverseCDF(this.dfBartlett, 0.95D);
                this.bartlettDone = true;
            }

            Matrix var14 = null;
            if (this.covRhoOption) {
                var14 = this.covarianceMatrix;
            } else {
                var14 = this.correlationMatrix;
            }

            this.eigenValues = var14.getEigenValues();
            this.orderedEigenValues = var14.getSortedEigenValues();
            this.eigenValueIndices = var14.eigenValueIndices();
            this.eigenVectorsAsColumns = var14.getEigenVectorsAsColumns();
            this.eigenVectorsAsRows = var14.getEigenVectorsAsRows();
            this.orderedEigenVectorsAsColumns = var14.getSortedEigenVectorsAsColumns();
            this.orderedEigenVectorsAsRows = var14.getSortedEigenVectorsAsRows();
            ArrayMaths var15 = new ArrayMaths(this.orderedEigenValues);
            double var16 = var15.sum();
            var15 = var15.times(100.0D / var16);
            this.proportionPercentage = var15.array();
            this.cumulativePercentage = new double[this.nItems];
            this.cumulativePercentage[0] = this.proportionPercentage[0];
            this.eigenValueTotal = 0.0D;

            for(int var18 = 1; var18 < this.nItems; ++var18) {
                this.cumulativePercentage[var18] = this.cumulativePercentage[var18 - 1] + this.proportionPercentage[var18];
                this.eigenValueTotal += this.eigenValues[var18];
            }

            boolean var28 = true;
            int var19 = 0;

            while(var28) {
                if (this.orderedEigenValues[var19] < 1.0D) {
                    this.greaterThanOneLimit = var19;
                    var28 = false;
                } else {
                    ++var19;
                    if (var19 == this.nItems) {
                        this.greaterThanOneLimit = var19;
                        var28 = false;
                    }
                }
            }

            this.loadingFactorsAsColumns = new double[this.nItems][this.nItems];
            this.loadingFactorsAsRows = new double[this.nItems][this.nItems];

            int var20;
            for(var20 = 0; var20 < this.nItems; ++var20) {
                for(int var21 = 0; var21 < this.nItems; ++var21) {
                    this.loadingFactorsAsColumns[var20][var21] = this.orderedEigenVectorsAsColumns[var20][var21] * Math.sqrt(Math.abs(this.orderedEigenValues[var21]));
                    this.loadingFactorsAsRows[var20][var21] = this.orderedEigenVectorsAsRows[var20][var21] * Math.sqrt(Math.abs(this.orderedEigenValues[var20]));
                }
            }

            this.communalities = new double[this.nItems];
            this.communalityWeights = new double[this.nItems];

            for(var20 = 0; var20 < this.nItems; ++var20) {
                double var29 = 0.0D;

                for(int var23 = 0; var23 < this.nItems; ++var23) {
                    var29 += this.loadingFactorsAsRows[var23][var20] * this.loadingFactorsAsRows[var23][var20];
                }

                this.communalities[var20] = var29;
                this.communalityWeights[var20] = Math.sqrt(this.communalities[var20]);
            }
        }

        this.pcaDone = true;
    }

    public void monteCarlo() {
        if (!this.pcaDone) {
            this.pca();
        }

        double[] var1 = super.rawItemMeans();
        double[] var2 = super.rawItemStandardDeviations();
        double[][] var3 = new double[super.nItems][super.nPersons];
        this.randomEigenValues = new double[this.nMonteCarlo][super.nItems];
        PsRandom var4 = new PsRandom();

        int var6;
        for(int var5 = 0; var5 < this.nMonteCarlo; ++var5) {
            for(var6 = 0; var6 < this.nItems; ++var6) {
                if (this.gaussianDeviates) {
                    var3[var6] = var4.gaussianArray(var1[var6], var2[var6], super.nPersons);
                } else {
                    var3[var6] = var4.doubleArray(super.nPersons);
                    var3[var6] = Stat.scale(var3[var6], var1[var6], var2[var6]);
                }
            }

            PCA var14 = new PCA();
            if (this.covRhoOption) {
                var14.useCovarianceMatrix();
            } else {
                var14.useCorrelationMatrix();
            }

            var14.enterScoresAsRowPerItem(var3);
            this.randomEigenValues[var5] = var14.orderedEigenValues();
        }

        Matrix var13 = new Matrix(this.randomEigenValues);
        this.randomEigenValuesMeans = var13.columnMeans();
        this.randomEigenValuesSDs = var13.columnStandardDeviations();
        this.randomEigenValuesPercentiles = new double[this.nItems];
        var6 = (int)Math.ceil((double)this.nMonteCarlo * this.percentile / 100.0D);
        int var7 = var6 - 1;
        double var8 = this.percentile * (double)this.nMonteCarlo / 100.0D - (double)var7;
        --var6;
        --var7;

        for(int var10 = 0; var10 < this.nItems; ++var10) {
            double[] var11 = new double[this.nMonteCarlo];

            for(int var12 = 0; var12 < this.nMonteCarlo; ++var12) {
                var11[var12] = this.randomEigenValues[var12][var10];
            }

            ArrayMaths var17 = new ArrayMaths(var11);
            var17 = var17.sort();
            var11 = var17.array();
            this.randomEigenValuesPercentiles[var10] = var11[var7] + var8 * (var11[var6] - var11[var7]);
        }

        boolean var15 = true;
        int var16 = 0;

        while(var15) {
            if (this.orderedEigenValues[var16] <= this.randomEigenValuesPercentiles[var16]) {
                this.percentileCrossover = var16;
                var15 = false;
            } else {
                ++var16;
                if (var16 == this.nItems) {
                    this.percentileCrossover = var16;
                    var15 = false;
                }
            }
        }

        var15 = true;
        var16 = 0;

        while(var15) {
            if (this.orderedEigenValues[var16] <= this.randomEigenValuesMeans[var16]) {
                this.meanCrossover = var16;
                var15 = false;
            } else {
                ++var16;
                if (var16 == this.nItems) {
                    this.meanCrossover = var16;
                    var15 = false;
                }
            }
        }

        this.monteCarloDone = true;
    }

    public void screePlotDataAlone() {
        if (!this.pcaDone) {
            this.pca();
        }

        double[] var1 = new double[super.nItems];

        for(int var2 = 0; var2 < this.nItems; ++var2) {
            var1[var2] = (double)(var2 + 1);
        }

        PlotGraph var3 = new PlotGraph(var1, this.orderedEigenValues);
        var3.setGraphTitle("Principal Component Analysis Scree Plot");
        var3.setXaxisLegend("Component");
        var3.setYaxisLegend("Eigenvalues");
        var3.setLine(3);
        var3.setPoint(1);
        var3.setFrame();
    }

    public void screePlot() {
        if (!this.pcaDone) {
            this.pca();
        }

        if (!this.monteCarloDone) {
            this.monteCarlo();
        }

        double[][] var1 = new double[6][super.nItems];
        double[] var2 = new double[super.nItems];

        for(int var3 = 0; var3 < this.nItems; ++var3) {
            var2[var3] = (double)(var3 + 1);
        }

        var1[0] = var2;
        var1[1] = this.orderedEigenValues;
        var1[2] = var2;
        var1[3] = this.randomEigenValuesPercentiles;
        var1[4] = var2;
        var1[5] = this.randomEigenValuesMeans;
        PlotGraph var6 = new PlotGraph(var1);
        var6.setErrorBars(2, this.randomEigenValuesSDs);
        if (this.gaussianDeviates) {
            var6.setGraphTitle("Principal Component Analysis Scree Plot with Parallel Analysis using Gaussian deviates (" + this.nMonteCarlo + " simulations)");
        } else {
            var6.setGraphTitle("Principal Component Analysis Scree Plot with Parallel Analysis using uniform deviates (" + this.nMonteCarlo + " simulations)");
        }

        var6.setGraphTitle2("Closed squares - data eigenvalues; open circles = Monte Carlo eigenvalue " + this.percentile + "% percentiles; error bars = standard deviations about the Monte carlo means (crosses)");
        var6.setXaxisLegend("Component");
        var6.setYaxisLegend("Eigenvalue");
        int[] var4 = new int[]{3, 0, 3};
        var6.setLine(var4);
        int[] var5 = new int[]{5, 1, 7};
        var6.setPoint(var5);
        var6.setFrame();
    }

    public void setVarimaxTolerance(double var1) {
        this.varimaxTolerance = var1;
    }

    public void setVarimaxMaximumIterations(int var1) {
        this.nVarimaxMax = var1;
    }

    public int getVarimaxIterations() {
        return this.nVarimax;
    }

    public void varimaxRotation(int var1) {
        if (!this.pcaDone) {
            this.pca();
        }

        if (this.varimaxOption) {
            this.normalVarimaxRotation(var1);
        } else {
            this.rawVarimaxRotation(var1);
        }

    }

    public void varimaxRotation(double[][] var1) {
        if (this.varimaxOption) {
            System.out.println("Method varimaxRotation: communality weights not supplied - raw varimax option used");
        }

        this.rawVarimaxRotationInHouse(var1);
    }

    public void varimaxRotation(double[][] var1, double[] var2) {
        if (this.varimaxOption) {
            this.normalVarimaxRotationInHouse(var1, var2);
        } else {
            System.out.println("Method varimaxRotation: raw varimax option chosen, supplied communality weights ignored");
            this.rawVarimaxRotationInHouse(var1);
        }

    }

    public void rawVarimaxRotation(int var1) {
        if (!this.pcaDone) {
            this.pca();
        }

        double[][] var2 = new double[var1][this.nItems];

        for(int var3 = 0; var3 < var1; ++var3) {
            var2[var3] = this.loadingFactorsAsRows[var3];
        }

        double[] var5 = new double[this.nItems];

        for(int var4 = 0; var4 < this.nItems; ++var4) {
            var5[var4] = 1.0D;
        }

        this.normalVarimaxRotationInHouse(var2, var5);
    }

    private void rawVarimaxRotationInHouse(double[][] var1) {
        double[] var2 = new double[this.nItems];

        for(int var3 = 0; var3 < this.nItems; ++var3) {
            var2[var3] = 1.0D;
        }

        this.normalVarimaxRotationInHouse(var1, var2);
    }

    public void normalVarimaxRotation(int var1) {
        if (!this.pcaDone) {
            this.pca();
        }

        double[][] var2 = new double[var1][this.nItems];

        for(int var3 = 0; var3 < var1; ++var3) {
            var2[var3] = this.loadingFactorsAsRows[var3];
        }

        double[] var6 = new double[this.nItems];

        for(int var4 = 0; var4 < this.nItems; ++var4) {
            var6[var4] = 0.0D;

            for(int var5 = 0; var5 < var1; ++var5) {
                var6[var4] += var2[var5][var4] * var2[var5][var4];
            }
        }

        this.normalVarimaxRotationInHouse(var2, var6);
    }

    private void normalVarimaxRotationInHouse(double[][] var1, double[] var2) {
        if (!this.pcaDone) {
            this.pca();
        }

        int var3 = var1.length;
        int var4 = var1[0].length;
        this.usRotatedLoadingFactorsAsRows = new double[var3][var4];
        this.rotatedLoadingFactorsAsRows = new double[var3][var4];
        this.usRotatedEigenValues = new double[var3];
        this.rotatedEigenValues = new double[var3];
        this.rotatedProportionPercentage = new double[var3];
        this.rotatedCumulativePercentage = new double[var3];

        int var5;
        for(var5 = 0; var5 < var4; ++var5) {
            var2[var5] = Math.sqrt(var2[var5]);
        }

        for(var5 = 0; var5 < var3; ++var5) {
            for(int var6 = 0; var6 < var4; ++var6) {
                if (var1[var5][var6] == 0.0D && var2[var6] == 0.0D) {
                    var1[var5][var6] = 1.0D;
                } else {
                    var1[var5][var6] /= var2[var6];
                }

                this.usRotatedLoadingFactorsAsRows[var5][var6] = var1[var5][var6];
            }
        }

        double var32 = varimaxCriterion(this.usRotatedLoadingFactorsAsRows);
        double var7 = 0.0D;
        double var9 = 0.0D;
        boolean var11 = true;
        this.nVarimax = 0;

        int var12;
        int var13;
        while(var11) {
            for(var12 = 0; var12 < var3 - 1; ++var12) {
                for(var13 = var12 + 1; var13 < var3; ++var13) {
                    var9 = varimaxAngle(this.usRotatedLoadingFactorsAsRows, var12, var13);
                    this.usRotatedLoadingFactorsAsRows = singleRotation(this.usRotatedLoadingFactorsAsRows, var12, var13, var9);
                    var32 = varimaxCriterion(this.usRotatedLoadingFactorsAsRows);
                }
            }

            if (Math.abs(var32 - var7) < this.varimaxTolerance) {
                var11 = false;
            } else {
                var7 = var32;
                ++this.nVarimax;
                if (this.nVarimax > this.nVarimaxMax) {
                    var11 = false;
                    System.out.println("Method varimaxRotation: maximum iterations " + this.nVarimaxMax + " exceeded");
                    System.out.println("Tolerance = " + this.varimaxTolerance + ",     Comparison value = " + Math.abs(var32 - var32));
                    System.out.println("Current values returned");
                    if (super.sameCheck > 0) {
                        System.out.println("Presence of identical element row/s and/or column/s in the data probably impeding convergence");
                        System.out.println("Returned values are likely to be correct");
                    }
                }
            }
        }

        this.usRotatedLoadingFactorsAsColumns = new double[var4][var3];

        for(var12 = 0; var12 < var3; ++var12) {
            for(var13 = 0; var13 < var4; ++var13) {
                this.usRotatedLoadingFactorsAsRows[var12][var13] *= var2[var13];
                this.usRotatedLoadingFactorsAsColumns[var13][var12] = this.usRotatedLoadingFactorsAsRows[var12][var13];
                var1[var12][var13] *= var2[var13];
            }
        }

        double var33 = 0.0D;
        double var14 = 0.0D;

        for(int var16 = 0; var16 < var3; ++var16) {
            this.usRotatedEigenValues[var16] = 0.0D;

            for(int var17 = 0; var17 < var4; ++var17) {
                this.usRotatedEigenValues[var16] += this.usRotatedLoadingFactorsAsRows[var16][var17] * this.usRotatedLoadingFactorsAsRows[var16][var17];
            }

            var33 += this.usRotatedEigenValues[var16];
            var14 += this.orderedEigenValues[var16];
        }

        ArrayMaths var34 = new ArrayMaths(this.usRotatedEigenValues);
        var34 = var34.sort();
        this.usRotatedEigenValues = var34.array();
        int[] var35 = var34.originalIndices();
        int var18 = var3 / 2;
        double var19 = 0.0D;
        boolean var21 = false;

        int var22;
        for(var22 = 0; var22 < var18; ++var22) {
            var19 = this.usRotatedEigenValues[var22];
            this.usRotatedEigenValues[var22] = this.usRotatedEigenValues[var3 - 1 - var22];
            this.usRotatedEigenValues[var3 - 1 - var22] = var19;
            int var36 = var35[var22];
            var35[var22] = var35[var3 - 1 - var22];
            var35[var3 - 1 - var22] = var36;
        }

        var22 = this.usRotatedLoadingFactorsAsRows.length;
        int var23 = this.usRotatedLoadingFactorsAsRows[0].length;
        double[][] var24 = new double[var22][var23];

        int var26;
        for(int var25 = 0; var25 < var22; ++var25) {
            for(var26 = 0; var26 < var23; ++var26) {
                var24[var25][var26] = this.usRotatedLoadingFactorsAsRows[var35[var25]][var26];
            }
        }

        this.usRotatedLoadingFactorsAsRows = Conv.copy((double[][])var24);
        var22 = var35.length;
        this.rotatedIndices = new int[var22];
        int[] var37 = new int[var22];

        for(var26 = 0; var26 < var22; ++var26) {
            var37[var26] = this.eigenValueIndices[var35[var26]];
        }

        this.rotatedIndices = Conv.copy((int[])this.eigenValueIndices);

        for(var26 = 0; var26 < var22; ++var26) {
            this.rotatedIndices[var26] = var37[var26];
        }

        double var38 = Math.abs(var14 / var33);
        double var28 = Math.sqrt(var38);

        int var30;
        for(var30 = 0; var30 < var3; ++var30) {
            this.rotatedEigenValues[var30] = var38 * this.usRotatedEigenValues[var30];
            this.rotatedProportionPercentage[var30] = this.rotatedEigenValues[var30] * 100.0D / this.eigenValueTotal;

            for(int var31 = 0; var31 < var4; ++var31) {
                this.rotatedLoadingFactorsAsRows[var30][var31] = var28 * this.usRotatedLoadingFactorsAsRows[var30][var31];
            }
        }

        this.rotatedCumulativePercentage[0] = this.rotatedProportionPercentage[0];

        for(var30 = 1; var30 < var3; ++var30) {
            this.rotatedCumulativePercentage[var30] = this.rotatedCumulativePercentage[var30 - 1] + this.rotatedProportionPercentage[var30];
        }

        this.rotationDone = true;
    }

    public static double[][] rawVarimaxRotation(double[][] var0) {
        double var1 = 1.0E-4D;
        short var3 = 1000;
        return rawVarimaxRotation(var0, var1, var3);
    }

    public static double[][] rawVarimaxRotation(double[][] var0, double var1, int var3) {
        int var4 = var0.length;
        int var5 = var0[0].length;
        double[] var6 = new double[var5];

        for(int var7 = 0; var7 < var5; ++var7) {
            var6[var7] = 1.0D;
        }

        return normalVarimaxRotation(var0, var6, var1, var3);
    }

    public static double[][] normalVarimaxRotation(double[][] var0, double[] var1) {
        double var2 = 1.0E-4D;
        short var4 = 1000;
        return normalVarimaxRotation(var0, var1, var2, var4);
    }

    public static double[][] normalVarimaxRotation(double[][] var0, double[] var1, double var2, int var4) {
        int var5 = var0.length;
        int var6 = var0[0].length;

        for(int var7 = 1; var7 < var5; ++var7) {
            if (var0[var7].length != var6) {
                throw new IllegalArgumentException("All rows must be the same length");
            }
        }

        double[][] var18 = new double[var5][var6];

        for(int var8 = 0; var8 < var5; ++var8) {
            for(int var9 = 0; var9 < var6; ++var9) {
                var0[var8][var9] /= var1[var9];
                var18[var8][var9] = var0[var8][var9];
            }
        }

        double var19 = varimaxCriterion(var18);
        double var10 = 0.0D;
        double var12 = 0.0D;
        boolean var14 = true;
        int var15 = 0;

        int var16;
        int var17;
        while(var14) {
            for(var16 = 0; var16 < var5 - 1; ++var16) {
                for(var17 = var16 + 1; var17 < var5; ++var17) {
                    var12 = varimaxAngle(var18, var16, var17);
                    var18 = singleRotation(var18, var16, var17, var12);
                    var19 = varimaxCriterion(var18);
                }
            }

            if (Math.abs(var19 - var10) < var2) {
                var14 = false;
            } else {
                var10 = var19;
                ++var15;
                if (var15 > var4) {
                    var14 = false;
                    System.out.println("Method varimaxRotation: maximum iterations " + var4 + " exceeded");
                    System.out.println("Current values returned");
                }
            }
        }

        for(var16 = 0; var16 < var5; ++var16) {
            for(var17 = 0; var17 < var6; ++var17) {
                var18[var16][var17] *= var1[var17];
                var0[var16][var17] *= var1[var17];
            }
        }

        return var18;
    }

    public static double[][] transposeMatrix(double[][] var0) {
        int var1 = var0.length;
        int var2 = var0[0].length;

        for(int var3 = 1; var3 < var1; ++var3) {
            if (var0[var3].length != var2) {
                throw new IllegalArgumentException("All rows must be the same length");
            }
        }

        double[][] var6 = new double[var2][var1];

        for(int var4 = 0; var4 < var1; ++var4) {
            for(int var5 = 0; var5 < var2; ++var5) {
                var6[var5][var4] = var0[var4][var5];
            }
        }

        return var6;
    }

    public static double varimaxCriterion(double[][] var0) {
        int var1 = var0.length;
        int var2 = var0[0].length;
        double var3 = 0.0D;
        double var5 = 0.0D;
        double var7 = 0.0D;

        int var9;
        double var10;
        int var12;
        for(var9 = 0; var9 < var1; ++var9) {
            var10 = 0.0D;

            for(var12 = 0; var12 < var2; ++var12) {
                var10 += Math.pow(var0[var9][var12], 4.0D);
            }

            var3 += var10;
        }

        var3 *= (double)var2;

        for(var9 = 0; var9 < var1; ++var9) {
            var10 = 0.0D;

            for(var12 = 0; var12 < var2; ++var12) {
                var10 += Math.pow(var0[var9][var12], 2.0D);
            }

            var5 += var10 * var10;
        }

        var7 = var3 - var5;
        return var7;
    }

    public static double varimaxAngle(double[][] var0, int var1, int var2) {
        int var3 = var0[0].length;
        double var4 = 0.0D;
        double var6 = 0.0D;
        double var8 = 0.0D;
        double var10 = 0.0D;
        double var12 = 0.0D;
        double var14 = 0.0D;

        for(int var16 = 0; var16 < var3; ++var16) {
            double var17 = var0[var1][var16];
            double var19 = var0[var2][var16];
            var4 = var17 * var17 - var19 * var19;
            var6 = 2.0D * var17 * var19;
            var8 += var4;
            var10 += var6;
            var12 += var4 * var4 - var6 * var6;
            var14 += 2.0D * var4 * var6;
        }

        double var22 = var14 - 2.0D * var8 * var10 / (double)var3;
        double var18 = var12 - (var8 * var8 - var10 * var10) / (double)var3;
        double var20 = 0.25D * Math.atan2(var22, var18);
        return var20;
    }

    public static double[][] singleRotation(double[][] var0, int var1, int var2, double var3) {
        int var5 = var0.length;
        int var6 = var0[0].length;
        double[][] var7 = new double[var5][var6];

        for(int var8 = 0; var8 < var5; ++var8) {
            for(int var9 = 0; var9 < var6; ++var9) {
                var7[var8][var9] = var0[var8][var9];
            }
        }

        double var13 = Math.sin(var3);
        double var10 = Math.cos(var3);

        for(int var12 = 0; var12 < var6; ++var12) {
            var7[var1][var12] = var0[var1][var12] * var10 + var0[var2][var12] * var13;
            var7[var2][var12] = -var0[var1][var12] * var13 + var0[var2][var12] * var10;
        }

        return var7;
    }

    public double[] eigenValues() {
        if (!this.pcaDone) {
            this.pca();
        }

        return this.eigenValues;
    }

    public double[] orderedEigenValues() {
        if (!this.pcaDone) {
            this.pca();
        }

        return this.orderedEigenValues;
    }

    public int[] eigenValueIndices() {
        if (!this.pcaDone) {
            this.pca();
        }

        return this.eigenValueIndices;
    }

    public double eigenValueTotal() {
        if (!this.pcaDone) {
            this.pca();
        }

        return this.eigenValueTotal;
    }

    public double[] proportionPercentage() {
        if (!this.pcaDone) {
            this.pca();
        }

        return this.proportionPercentage;
    }

    public double[] cumulativePercentage() {
        if (!this.pcaDone) {
            this.pca();
        }

        return this.cumulativePercentage;
    }

    public double[] rotatedEigenValues() {
        if (!this.rotationDone) {
            throw new IllegalArgumentException("No rotation has been performed");
        } else {
            return this.rotatedEigenValues;
        }
    }

    public double[] rotatedProportionPercentage() {
        if (!this.rotationDone) {
            throw new IllegalArgumentException("No rotation has been performed");
        } else {
            return this.rotatedProportionPercentage;
        }
    }

    public double[] rotatedCumulativePercentage() {
        if (!this.rotationDone) {
            throw new IllegalArgumentException("No rotation has been performed");
        } else {
            return this.rotatedCumulativePercentage;
        }
    }

    public double[][] eigenVectors() {
        if (!this.pcaDone) {
            this.pca();
        }

        return this.eigenVectorsAsColumns;
    }

    public double[][] eigenVectorsAsRows() {
        if (!this.pcaDone) {
            this.pca();
        }

        return this.eigenVectorsAsRows;
    }

    public double[][] orderedEigenVectorsAsColumns() {
        if (!this.pcaDone) {
            this.pca();
        }

        return this.orderedEigenVectorsAsColumns;
    }

    public double[][] orderedEigenVectors() {
        if (!this.pcaDone) {
            this.pca();
        }

        return this.orderedEigenVectorsAsColumns;
    }

    public double[][] orderedEigenVectorsAsRows() {
        if (!this.pcaDone) {
            this.pca();
        }

        return this.orderedEigenVectorsAsRows;
    }

    public double[][] loadingFactorsAsColumns() {
        if (!this.pcaDone) {
            this.pca();
        }

        return this.loadingFactorsAsColumns;
    }

    public double[][] loadingFactorsAsRows() {
        if (!this.pcaDone) {
            this.pca();
        }

        return this.loadingFactorsAsRows;
    }

    public double[][] rotatedLoadingFactorsAsColumns() {
        if (!this.rotationDone) {
            throw new IllegalArgumentException("No rotation has been performed");
        } else {
            return this.rotatedLoadingFactorsAsColumns;
        }
    }

    public double[][] rotatedLoadingFactorsAsRows() {
        if (!this.rotationDone) {
            throw new IllegalArgumentException("No rotation has been performed");
        } else {
            return this.rotatedLoadingFactorsAsRows;
        }
    }

    public double[] communalities() {
        if (!this.pcaDone) {
            this.pca();
        }

        return this.communalities;
    }

    public double[] communalityWeights() {
        if (!this.pcaDone) {
            this.pca();
        }

        return this.communalityWeights;
    }

    public Matrix covarianceMatrix() {
        if (!this.pcaDone) {
            this.pca();
        }

        return this.covarianceMatrix;
    }

    public Matrix correlationMatrix() {
        if (!this.pcaDone) {
            this.pca();
        }

        return this.correlationMatrix;
    }

    public Matrix partialCorrelationMatrix() {
        if (!this.pcaDone) {
            this.pca();
        }

        return this.partialCorrelationMatrix;
    }

    public double[] monteCarloMeans() {
        if (!this.monteCarloDone) {
            this.monteCarlo();
        }

        return this.randomEigenValuesMeans;
    }

    public double[] monteCarloStandardDeviations() {
        if (!this.monteCarloDone) {
            this.monteCarlo();
        }

        return this.randomEigenValuesSDs;
    }

    public double[] monteCarloPercentiles() {
        if (!this.monteCarloDone) {
            this.monteCarlo();
        }

        return this.randomEigenValuesPercentiles;
    }

    public double[][] monteCarloEigenValues() {
        if (!this.monteCarloDone) {
            this.monteCarlo();
        }

        return this.randomEigenValues;
    }

    public Matrix originalData() {
        if (!this.pcaDone) {
            this.pca();
        }

        return this.data;
    }

    public Matrix xMatrix() {
        if (!this.pcaDone) {
            this.pca();
        }

        double var1 = (double)this.nItems;
        if (!super.nFactorOption) {
            --var1;
        }

        Matrix var3 = this.dataMinusMeans.times(1.0D / Math.sqrt(var1));
        return var3;
    }

    public Matrix xMatrixTranspose() {
        if (!this.pcaDone) {
            this.pca();
        }

        double var1 = (double)this.nItems;
        if (!super.nFactorOption) {
            --var1;
        }

        Matrix var3 = this.dataMinusMeansTranspose.times(1.0D / Math.sqrt(var1));
        return var3;
    }

    public int nEigenOneOrGreater() {
        if (!this.pcaDone) {
            this.pca();
        }

        return this.greaterThanOneLimit;
    }

    public int nMeanCrossover() {
        if (!this.monteCarloDone) {
            this.monteCarlo();
        }

        return this.meanCrossover;
    }

    public int nPercentileCrossover() {
        if (!this.monteCarloDone) {
            this.monteCarlo();
        }

        return this.percentileCrossover;
    }

    public double overallKMO() {
        if (!this.pcaDone) {
            this.pca();
        }

        return this.kmo;
    }

    public double kmo() {
        if (!this.pcaDone) {
            this.pca();
        }

        return this.kmo;
    }

    public double[] itemKMOs() {
        if (!this.pcaDone) {
            this.pca();
        }

        return this.itemKMOs;
    }

    public double chiSquareBartlett() {
        if (!this.pcaDone) {
            this.pca();
        }

        if (this.bartlettDone) {
            return this.chiSquareBartlett;
        } else {
            System.out.println("Method chiSquareBartlett(): Bartlett Sphericity Test nor performed, NaN returned");
            return 0.0D / 0.0;
        }
    }

    public int dofBartlett() {
        if (!this.pcaDone) {
            this.pca();
        }

        return this.dfBartlett;
    }

    public double probabilityBartlett() {
        if (!this.pcaDone) {
            this.pca();
        }

        if (this.bartlettDone) {
            return this.probBartlett;
        } else {
            System.out.println("Method probabilityBartlett(): Bartlett Sphericity Test nor performed, NaN returned");
            return 0.0D / 0.0;
        }
    }

    public void analysis() {
        this.outputFilename = "PCAOutput";
        if (this.fileOption == 1) {
            this.outputFilename = this.outputFilename + ".txt";
        } else {
            this.outputFilename = this.outputFilename + ".xls";
        }

        String var1 = "Output file name for the analysis details:";
        String var2 = "\nEnter the required name (as a single word) and click OK ";
        String var3 = "\nor simply click OK for default value";
        String var4 = var1 + var2 + var3;
        String var5 = this.outputFilename;
        this.outputFilename = Db.readLine(var4, var5);
        this.analysis(this.outputFilename);
    }

    public void analysis(String var1) {
        this.screePlot();
        this.outputFilename = var1;
        String var2 = null;
        String var3 = null;
        int var4 = var1.indexOf(46);
        if (var4 == -1) {
            if (this.fileOption == 1) {
                this.outputFilename = this.outputFilename + ".txt";
            } else {
                this.outputFilename = this.outputFilename + ".xls";
            }
        } else {
            var3 = var1.substring(var4).trim();
            var2 = var1.substring(0, var4).trim();
            String var5;
            String var6;
            String var7;
            String var8;
            String[] var9;
            String[] var10;
            byte var11;
            int var12;
            if (var3.equalsIgnoreCase(".xls") && this.fileOption == 1) {
                if (this.fileOptionSet) {
                    var5 = "Your entered output file type is .xls";
                    var6 = "\nbut you have chosen a .txt output";
                    var7 = var5 + var6;
                    var8 = "Your output file name extension";
                    var9 = new String[]{var7, "replace it with .txt [text file]"};
                    var10 = new String[]{"Retain", ".txt"};
                    var11 = 1;
                    var12 = Db.optionBox(var8, var9, var10, var11);
                    if (var12 == 2) {
                        this.outputFilename = var2 + ".txt";
                    }
                } else {
                    this.fileOption = 2;
                }
            }

            if (var3.equalsIgnoreCase(".txt") && this.fileOption == 2) {
                if (this.fileOptionSet) {
                    var5 = "Your entered output file type is .txt";
                    var6 = "\nbut you have chosen a .xls output";
                    var7 = var5 + var6;
                    var8 = "Your output file name extension";
                    var9 = new String[]{var7, "replace it with .xls [Excel file]"};
                    var10 = new String[]{"Retain", ".xls"};
                    var11 = 1;
                    var12 = Db.optionBox(var8, var9, var10, var11);
                    if (var12 == 2) {
                        this.outputFilename = var2 + ".xls";
                    }
                } else {
                    this.fileOption = 1;
                }
            }

            if (!var3.equalsIgnoreCase(".txt") && !var3.equalsIgnoreCase(".xls")) {
                var5 = "Your extension is " + var3;
                var6 = "\n    Do you wish to retain it:";
                var7 = var5 + var6;
                var8 = "Your output file name extension";
                var9 = new String[]{var7, "replace it with .txt [text file]", "replace it with .xls [MS Excel file]"};
                var10 = new String[]{"Retain", ".txt", ".xls"};
                var11 = 1;
                var12 = Db.optionBox(var8, var9, var10, var11);
                switch(var12) {
                    case 1:
                        this.fileOption = 1;
                        break;
                    case 2:
                        this.outputFilename = var2 + ".txt";
                        this.fileOption = 1;
                        break;
                    case 3:
                        this.outputFilename = var2 + ".xls";
                        this.fileOption = 2;
                }
            }
        }

        if (this.fileOption == 1) {
            this.analysisText();
        } else {
            this.analysisExcel();
        }

        System.out.println("The analysis has been written to the file " + this.outputFilename);
    }

    private void analysisText() {
        FileOutput var1 = null;
        if (this.fileNumberingSet) {
            var1 = new FileOutput(this.outputFilename, 'n');
        } else {
            var1 = new FileOutput(this.outputFilename);
        }

        if (!this.pcaDone) {
            this.pca();
        }

        if (!this.monteCarloDone) {
            this.monteCarlo();
        }

        var1.println("PRINCIPAL COMPONENT ANALYSIS");
        var1.println("Program: PCA - Analysis Output");

        for(int var2 = 0; var2 < this.titleLines; ++var2) {
            var1.println(this.title[var2]);
        }

        Date var18 = new Date();
        String var3 = DateFormat.getDateInstance().format(var18);
        String var4 = DateFormat.getTimeInstance().format(var18);
        var1.println("Program executed at " + var4 + " on " + var3);
        var1.println();
        if (this.covRhoOption) {
            var1.println("Covariance matrix used");
        } else {
            var1.println("Correlation matrix used");
        }

        var1.println();
        byte var5 = 10;
        byte var6 = 12;
        byte var7 = 2;
        var1.println("ALL EIGENVALUES");
        var1.print("Component ", var5);
        var1.print("Unordered ", var5);
        var1.print("Eigenvalue ", var6);
        var1.print("Proportion ", var6);
        var1.print("Cumulative ", var6);
        var1.println("Difference ");
        var1.print(" ", var5);
        var1.print("index", var5);
        var1.print(" ", var6);
        var1.print("as % ", var6);
        var1.print("percentage ", var6);
        var1.println(" ");

        int var8;
        for(var8 = 0; var8 < this.nItems; ++var8) {
            var1.print(var8 + 1, var5);
            var1.print(this.eigenValueIndices[var8] + 1, var5);
            var1.print(Fmath.truncate(this.orderedEigenValues[var8], this.trunc), var6);
            var1.print(Fmath.truncate(this.proportionPercentage[var8], this.trunc), var6);
            var1.print(Fmath.truncate(this.cumulativePercentage[var8], this.trunc), var6);
            if (var8 < this.nItems - 1) {
                var1.print(Fmath.truncate(this.orderedEigenValues[var8] - this.orderedEigenValues[var8 + 1], this.trunc), var6);
            } else {
                var1.print(" ", var6);
            }

            var1.print(" ", var7);
            var1.println();
        }

        var1.println();
        var8 = this.greaterThanOneLimit;
        if (var8 < this.meanCrossover) {
            var8 = this.meanCrossover;
        }

        if (var8 < this.percentileCrossover) {
            var8 = this.percentileCrossover;
        }

        var1.println("EXTRACTED EIGENVALUES");
        var1.print(" ", var5);
        var1.print("Greater than unity", 3 * var6 + var7);
        var1.print("Greater than Monte Carlo Mean ", 3 * var6 + var7);
        var1.println("Greater than Monte Carlo Percentile");
        var1.print("Component ", var5);
        var1.print("Eigenvalue ", var6);
        var1.print("Proportion ", var6);
        var1.print("Cumulative ", var6);
        var1.print(" ", var7);
        var1.print("Eigenvalue ", var6);
        var1.print("Proportion ", var6);
        var1.print("Cumulative ", var6);
        var1.print(" ", var7);
        var1.print("Eigenvalue ", var6);
        var1.print("Proportion ", var6);
        var1.print("Cumulative ", var6);
        var1.println(" ");
        var1.print(" ", var5);
        var1.print(" ", var6);
        var1.print("as % ", var6);
        var1.print("percentage ", var6);
        var1.print(" ", var7);
        var1.print(" ", var6);
        var1.print("as % ", var6);
        var1.print("percentage ", var6);
        var1.print(" ", var7);
        var1.print(" ", var6);
        var1.print("as % ", var6);
        var1.print("percentage ", var6);
        var1.println(" ");

        for(int var9 = 0; var9 < var8; ++var9) {
            var1.print(var9 + 1, var5);
            if (var9 < this.greaterThanOneLimit) {
                var1.print(Fmath.truncate(this.orderedEigenValues[var9], this.trunc), var6);
                var1.print(Fmath.truncate(this.proportionPercentage[var9], this.trunc), var6);
                var1.print(Fmath.truncate(this.cumulativePercentage[var9], this.trunc), var6 + var7);
            }

            if (var9 < this.meanCrossover) {
                var1.print(Fmath.truncate(this.orderedEigenValues[var9], this.trunc), var6);
                var1.print(Fmath.truncate(this.proportionPercentage[var9], this.trunc), var6);
                var1.print(Fmath.truncate(this.cumulativePercentage[var9], this.trunc), var6 + var7);
            }

            if (var9 < this.percentileCrossover) {
                var1.print(Fmath.truncate(this.orderedEigenValues[var9], this.trunc), var6);
                var1.print(Fmath.truncate(this.proportionPercentage[var9], this.trunc), var6);
                var1.print(Fmath.truncate(this.cumulativePercentage[var9], this.trunc));
            }

            var1.println();
        }

        var1.println();
        var1.println("PARALLEL ANALYSIS");
        var1.println("Number of simulations = " + this.nMonteCarlo);
        if (this.gaussianDeviates) {
            var1.println("Gaussian random deviates used");
        } else {
            var1.println("Uniform random deviates used");
        }

        var1.println("Percentile value used = " + this.percentile + " %");
        var1.println();
        var1.print("Component ", var5);
        var1.print("Data ", var6);
        var1.print("Proportion ", var6);
        var1.print("Cumulative ", var6);
        var1.print(" ", var7);
        var1.print("Data ", var6);
        var1.print("Monte Carlo ", var6);
        var1.print("Monte Carlo ", var6);
        var1.println("Monte Carlo ");
        var1.print(" ", var5);
        var1.print("Eigenvalue ", var6);
        var1.print("as % ", var6);
        var1.print("percentage ", var6);
        var1.print(" ", var7);
        var1.print("Eigenvalue ", var6);
        var1.print("Eigenvalue ", var6);
        var1.print("Eigenvalue ", var6);
        var1.println("Eigenvalue ");
        var1.print(" ", var5);
        var1.print(" ", var6);
        var1.print(" ", var6);
        var1.print(" ", var6);
        var1.print(" ", var7);
        var1.print(" ", var6);
        var1.print("Percentile ", var6);
        var1.print("Mean ", var6);
        var1.println("Standard Deviation ");

        int var10;
        for(var10 = 0; var10 < this.nItems; ++var10) {
            var1.print(var10 + 1, var5);
            var1.print(Fmath.truncate(this.orderedEigenValues[var10], this.trunc), var6);
            var1.print(Fmath.truncate(this.proportionPercentage[var10], this.trunc), var6);
            var1.print(Fmath.truncate(this.cumulativePercentage[var10], this.trunc), var6);
            var1.print(" ", var7);
            var1.print(Fmath.truncate(this.orderedEigenValues[var10], this.trunc), var6);
            var1.print(Fmath.truncate(this.randomEigenValuesPercentiles[var10], this.trunc), var6);
            var1.print(Fmath.truncate(this.randomEigenValuesMeans[var10], this.trunc), var6);
            var1.println(Fmath.truncate(this.randomEigenValuesSDs[var10], this.trunc));
        }

        var1.println();
        var1.println("CORRELATION MATRIX");
        var1.println("Original item indices in parenthesis");
        var1.println();
        var1.print(" ", var5);
        var1.print("item", var5);

        for(var10 = 0; var10 < this.nItems; ++var10) {
            var1.print(this.eigenValueIndices[var10] + 1 + " (" + (var10 + 1) + ")", var6);
        }

        var1.println();
        var1.println("item");

        int var11;
        for(var10 = 0; var10 < this.nItems; ++var10) {
            var1.print(this.eigenValueIndices[var10] + 1 + " (" + (var10 + 1) + ")", 2 * var5);

            for(var11 = 0; var11 < this.nItems; ++var11) {
                var1.print(Fmath.truncate(this.correlationMatrix.getElement(var11, var10), this.trunc), var6);
            }

            var1.println();
        }

        var1.println();
        var1.println("PARTIAL CORRELATION MATRIX");
        var1.println("Original item indices in parenthesis");
        var1.println();
        var1.print(" ", var5);
        var1.print("item", var5);

        for(var10 = 0; var10 < this.nItems; ++var10) {
            var1.print(this.eigenValueIndices[var10] + 1 + " (" + (var10 + 1) + ")", var6);
        }

        var1.println();
        var1.println("item");

        for(var10 = 0; var10 < this.nItems; ++var10) {
            var1.print(this.eigenValueIndices[var10] + 1 + " (" + (var10 + 1) + ")", 2 * var5);

            for(var11 = 0; var11 < this.nItems; ++var11) {
                var1.print(Fmath.truncate(this.partialCorrelationMatrix.getElement(var11, var10), this.trunc), var6);
            }

            var1.println();
        }

        var1.println();
        var1.println("COVARIANCE MATRIX");
        var1.println("Original item indices in parenthesis");
        var1.println();
        var1.print(" ", var5);
        var1.print("item", var5);

        for(var10 = 0; var10 < this.nItems; ++var10) {
            var1.print(this.eigenValueIndices[var10] + 1 + " (" + (var10 + 1) + ")", var6);
        }

        var1.println();
        var1.println("item");

        for(var10 = 0; var10 < this.nItems; ++var10) {
            var1.print(this.eigenValueIndices[var10] + 1 + " (" + (var10 + 1) + ")", 2 * var5);

            for(var11 = 0; var11 < this.nItems; ++var11) {
                var1.print(Fmath.truncate(this.covarianceMatrix.getElement(var11, var10), this.trunc), var6);
            }

            var1.println();
        }

        var1.println();
        var1.println("EIGENVECTORS");
        var1.println("Original component indices in parenthesis");
        var1.println("Vector corresponding to an ordered eigenvalues in each row");
        var1.println();
        var1.print(" ", var5);
        var1.print("component", var5);

        for(var10 = 0; var10 < this.nItems; ++var10) {
            var1.print(this.eigenValueIndices[var10] + 1 + " (" + (var10 + 1) + ")", var6);
        }

        var1.println();
        var1.println("component");

        for(var10 = 0; var10 < this.nItems; ++var10) {
            var1.print(var10 + 1 + " (" + (this.eigenValueIndices[var10] + 1) + ")", 2 * var5);

            for(var11 = 0; var11 < this.nItems; ++var11) {
                var1.print(Fmath.truncate(this.orderedEigenVectorsAsRows[var10][var11], this.trunc), var6);
            }

            var1.println();
        }

        var1.println();
        var1.println("LOADING FACTORS");
        var1.println("Original  indices in parenthesis");
        var1.println("Loading factors corresponding to an ordered eigenvalues in each row");
        var1.println();
        var1.print(" ", var5);
        var1.print("component", var5);

        for(var10 = 0; var10 < this.nItems; ++var10) {
            var1.print(this.eigenValueIndices[var10] + 1 + " (" + (var10 + 1) + ")", var6);
        }

        var1.print(" ", var5);
        var1.print("Eigenvalue", var6);
        var1.print("Proportion", var6);
        var1.println("Cumulative %");
        var1.println("factor");

        for(var10 = 0; var10 < this.nItems; ++var10) {
            var1.print(var10 + 1 + " (" + (this.eigenValueIndices[var10] + 1) + ")", 2 * var5);

            for(var11 = 0; var11 < this.nItems; ++var11) {
                var1.print(Fmath.truncate(this.loadingFactorsAsRows[var10][var11], this.trunc), var6);
            }

            var1.print(" ", var5);
            var1.print(Fmath.truncate(this.orderedEigenValues[var10], this.trunc), var6);
            var1.print(Fmath.truncate(this.proportionPercentage[var10], this.trunc), var6);
            var1.println(Fmath.truncate(this.cumulativePercentage[var10], this.trunc));
        }

        var1.println();
        var1.println("ROTATED LOADING FACTORS");
        if (this.varimaxOption) {
            var1.println("NORMAL VARIMAX");
        } else {
            var1.println("RAW VARIMAX");
        }

        String var19 = "The ordered eigenvalues with Monte Carlo means and percentiles in parenthesis";
        var19 = var19 + "\n (Total number of eigenvalues = " + this.nItems + ")";
        var11 = this.nItems;
        Dimension var12 = Toolkit.getDefaultToolkit().getScreenSize();
        int var13 = var12.height;
        int var14 = 20 * var13 / 800;
        if (var11 > var11) {
            var11 = var14;
        }

        int var15;
        for(var15 = 0; var15 < var11; ++var15) {
            var19 = var19 + "\n " + Fmath.truncate(this.orderedEigenValues[var15], 4) + " (" + Fmath.truncate(this.randomEigenValuesMeans[var15], 4) + "  " + Fmath.truncate(this.randomEigenValuesPercentiles[var15], 4) + ")";
        }

        if (var11 < this.nItems) {
            var19 = var19 + "\n . . . ";
        }

        var19 = var19 + "\nEnter number of eigenvalues to be extracted";
        var15 = this.greaterThanOneLimit;
        var15 = Db.readInt(var19, var15);
        this.varimaxRotation(var15);
        var1.println("Varimax rotation for " + var15 + " extracted factors");
        var1.println("Rotated loading factors and eigenvalues scaled to ensure total 'rotated variance' matches unrotated variance for the extracted factors");
        var1.println("Original  indices in parenthesis");
        var1.println();
        var1.print(" ", var5);
        var1.print("component", var5);

        int var16;
        for(var16 = 0; var16 < this.nItems; ++var16) {
            var1.print(this.rotatedIndices[var16] + 1 + " (" + (var16 + 1) + ")", var6);
        }

        var1.print(" ", var5);
        var1.print("Eigenvalue", var6);
        var1.print("Proportion", var6);
        var1.println("Cumulative %");
        var1.println("factor");

        int var17;
        for(var16 = 0; var16 < var15; ++var16) {
            var1.print(var16 + 1 + " (" + (this.rotatedIndices[var16] + 1) + ")", 2 * var5);

            for(var17 = 0; var17 < this.nItems; ++var17) {
                var1.print(Fmath.truncate(this.rotatedLoadingFactorsAsRows[var16][var17], this.trunc), var6);
            }

            var1.print(" ", var5);
            var1.print(Fmath.truncate(this.rotatedEigenValues[var16], this.trunc), var6);
            var1.print(Fmath.truncate(this.rotatedProportionPercentage[var16], this.trunc), var6);
            var1.println(Fmath.truncate(this.rotatedCumulativePercentage[var16], this.trunc));
        }

        var1.println();
        var1.println("Kaiser-Meyer-Olkin (KMO) statistic");
        var1.println("   Overall KMO statistic =   " + Fmath.truncate(this.kmo, this.trunc));
        var1.println("   KMO values for each item");
        var1.print("   ");

        for(var16 = 0; var16 < this.nItems; ++var16) {
            var1.print(var16 + 1, var6);
        }

        var1.println();
        var1.print("   ");

        for(var16 = 0; var16 < this.nItems; ++var16) {
            var1.print(Fmath.truncate(this.itemKMOs[var16], this.trunc), var6);
        }

        var1.println();
        var1.println();
        if (this.bartlettDone) {
            var1.println();
            var1.println("Bartlett Sphericity Test");
            var1.println("   Chi-Square =         " + Fmath.truncate(this.chiSquareBartlett, this.trunc));
            var1.println("   Probability value =  " + Fmath.truncate(this.probBartlett, this.trunc));
            var1.println("   Degrees of freedom = " + this.dfBartlett);
            var1.println("   Chi-Square value at the 5% significance level  (p = 0.05) = " + Fmath.truncate(this.sign05Bartlett, this.trunc));
            var1.println("   Chi-Square value at the 10% significance level (p = 0.10) = " + Fmath.truncate(this.sign10Bartlett, this.trunc));
            var1.println();
        }

        var1.println("DATA USED");
        var1.println("Number of items = " + this.nItems);
        var1.println("Number of persons = " + this.nPersons);
        if (this.originalDataType == 0) {
            var1.printtab("Item");

            for(var16 = 0; var16 < this.nPersons; ++var16) {
                var1.printtab(var16 + 1);
            }

            var1.println();

            for(var16 = 0; var16 < this.nItems; ++var16) {
                var1.printtab(this.itemNames[var16]);

                for(var17 = 0; var17 < this.nPersons; ++var17) {
                    var1.printtab(Fmath.truncate(this.scores0[var16][var17], this.trunc));
                }

                var1.println();
            }
        } else {
            var1.printtab("Person");

            for(var16 = 0; var16 < this.nItems; ++var16) {
                var1.printtab(this.itemNames[var16]);
            }

            var1.println();

            for(var16 = 0; var16 < this.nPersons; ++var16) {
                var1.printtab(var16 + 1);

                for(var17 = 0; var17 < this.nItems; ++var17) {
                    var1.printtab(Fmath.truncate(this.scores1[var16][var17], this.trunc));
                }

                var1.println();
            }
        }

        var1.close();
    }

    private void analysisExcel() {
        FileOutput var1 = null;
        if (this.fileNumberingSet) {
            var1 = new FileOutput(this.outputFilename, 'n');
        } else {
            var1 = new FileOutput(this.outputFilename);
        }

        if (!this.pcaDone) {
            this.pca();
        }

        if (!this.monteCarloDone) {
            this.monteCarlo();
        }

        var1.println("PRINCIPAL COMPONENT ANALYSIS");
        var1.println("Program: PCA - Analysis Output");

        for(int var2 = 0; var2 < this.titleLines; ++var2) {
            var1.println(this.title[var2]);
        }

        Date var15 = new Date();
        String var3 = DateFormat.getDateInstance().format(var15);
        String var4 = DateFormat.getTimeInstance().format(var15);
        var1.println("Program executed at " + var4 + " on " + var3);
        var1.println();
        if (this.covRhoOption) {
            var1.println("Covariance matrix used");
        } else {
            var1.println("Correlation matrix used");
        }

        var1.println();
        var1.println("ALL EIGENVALUES");
        var1.printtab("Component ");
        var1.printtab("Unordered ");
        var1.printtab("Eigenvalue ");
        var1.printtab("Proportion ");
        var1.printtab("Cumulative ");
        var1.println("Difference ");
        var1.printtab(" ");
        var1.printtab("index");
        var1.printtab(" ");
        var1.printtab("as % ");
        var1.printtab("percentage ");
        var1.println(" ");

        int var5;
        for(var5 = 0; var5 < this.nItems; ++var5) {
            var1.printtab(var5 + 1);
            var1.printtab(this.eigenValueIndices[var5] + 1);
            var1.printtab(Fmath.truncate(this.orderedEigenValues[var5], this.trunc));
            var1.printtab(Fmath.truncate(this.proportionPercentage[var5], this.trunc));
            var1.printtab(Fmath.truncate(this.cumulativePercentage[var5], this.trunc));
            if (var5 < this.nItems - 1) {
                var1.printtab(Fmath.truncate(this.orderedEigenValues[var5] - this.orderedEigenValues[var5 + 1], this.trunc));
            } else {
                var1.printtab(" ");
            }

            var1.printtab(" ");
            var1.println();
        }

        var1.println();
        var5 = this.greaterThanOneLimit;
        if (var5 < this.meanCrossover) {
            var5 = this.meanCrossover;
        }

        if (var5 < this.percentileCrossover) {
            var5 = this.percentileCrossover;
        }

        var1.println("EXTRACTED EIGENVALUES");
        var1.printtab(" ");
        var1.printtab("Greater than unity");
        var1.printtab(" ");
        var1.printtab(" ");
        var1.printtab(" ");
        var1.printtab("Greater than Monte Carlo Mean ");
        var1.printtab(" ");
        var1.printtab(" ");
        var1.printtab(" ");
        var1.println("Greater than Monte Carlo Percentile");
        var1.printtab("Component ");
        var1.printtab("Eigenvalue ");
        var1.printtab("Proportion ");
        var1.printtab("Cumulative ");
        var1.printtab(" ");
        var1.printtab("Eigenvalue ");
        var1.printtab("Proportion ");
        var1.printtab("Cumulative ");
        var1.printtab(" ");
        var1.printtab("Eigenvalue ");
        var1.printtab("Proportion ");
        var1.printtab("Cumulative ");
        var1.println(" ");
        var1.printtab(" ");
        var1.printtab(" ");
        var1.printtab("as % ");
        var1.printtab("percentage ");
        var1.printtab(" ");
        var1.printtab(" ");
        var1.printtab("as % ");
        var1.printtab("percentage ");
        var1.printtab(" ");
        var1.printtab(" ");
        var1.printtab("as % ");
        var1.printtab("percentage ");
        var1.println(" ");

        for(int var6 = 0; var6 < var5; ++var6) {
            var1.printtab(var6 + 1);
            if (var6 < this.greaterThanOneLimit) {
                var1.printtab(Fmath.truncate(this.orderedEigenValues[var6], this.trunc));
                var1.printtab(Fmath.truncate(this.proportionPercentage[var6], this.trunc));
                var1.printtab(Fmath.truncate(this.cumulativePercentage[var6], this.trunc));
                var1.printtab(" ");
            }

            if (var6 < this.meanCrossover) {
                var1.printtab(Fmath.truncate(this.orderedEigenValues[var6], this.trunc));
                var1.printtab(Fmath.truncate(this.proportionPercentage[var6], this.trunc));
                var1.printtab(Fmath.truncate(this.cumulativePercentage[var6], this.trunc));
                var1.printtab(" ");
            }

            if (var6 < this.percentileCrossover) {
                var1.printtab(Fmath.truncate(this.orderedEigenValues[var6], this.trunc));
                var1.printtab(Fmath.truncate(this.proportionPercentage[var6], this.trunc));
                var1.printtab(Fmath.truncate(this.cumulativePercentage[var6], this.trunc));
            }

            var1.println();
        }

        var1.println();
        var1.println("PARALLEL ANALYSIS");
        var1.println("Number of simulations = " + this.nMonteCarlo);
        if (this.gaussianDeviates) {
            var1.println("Gaussian random deviates used");
        } else {
            var1.println("Uniform random deviates used");
        }

        var1.println("Percentile value used = " + this.percentile + " %");
        var1.println();
        var1.printtab("Component ");
        var1.printtab("Data ");
        var1.printtab("Proportion ");
        var1.printtab("Cumulative ");
        var1.printtab(" ");
        var1.printtab("Data ");
        var1.printtab("Monte Carlo ");
        var1.printtab("Monte Carlo ");
        var1.println("Monte Carlo ");
        var1.printtab(" ");
        var1.printtab("Eigenvalue ");
        var1.printtab("as % ");
        var1.printtab("percentage ");
        var1.printtab(" ");
        var1.printtab("Eigenvalue ");
        var1.printtab("Eigenvalue ");
        var1.printtab("Eigenvalue ");
        var1.println("Eigenvalue ");
        var1.printtab(" ");
        var1.printtab(" ");
        var1.printtab(" ");
        var1.printtab(" ");
        var1.printtab(" ");
        var1.printtab(" ");
        var1.printtab("Percentile ");
        var1.printtab("Mean ");
        var1.println("Standard Deviation ");

        int var7;
        for(var7 = 0; var7 < this.nItems; ++var7) {
            var1.printtab(var7 + 1);
            var1.printtab(Fmath.truncate(this.orderedEigenValues[var7], this.trunc));
            var1.printtab(Fmath.truncate(this.proportionPercentage[var7], this.trunc));
            var1.printtab(Fmath.truncate(this.cumulativePercentage[var7], this.trunc));
            var1.printtab(" ");
            var1.printtab(Fmath.truncate(this.orderedEigenValues[var7], this.trunc));
            var1.printtab(Fmath.truncate(this.randomEigenValuesPercentiles[var7], this.trunc));
            var1.printtab(Fmath.truncate(this.randomEigenValuesMeans[var7], this.trunc));
            var1.println(Fmath.truncate(this.randomEigenValuesSDs[var7], this.trunc));
        }

        var1.println();
        var1.println("CORRELATION MATRIX");
        var1.println("Original item indices in parenthesis");
        var1.println();
        var1.printtab(" ");
        var1.printtab("item");

        for(var7 = 0; var7 < this.nItems; ++var7) {
            var1.printtab(this.eigenValueIndices[var7] + 1 + " (" + (var7 + 1) + ")");
        }

        var1.println();
        var1.println("item");

        int var8;
        for(var7 = 0; var7 < this.nItems; ++var7) {
            var1.printtab(this.eigenValueIndices[var7] + 1 + " (" + (var7 + 1) + ")");
            var1.printtab(" ");

            for(var8 = 0; var8 < this.nItems; ++var8) {
                var1.printtab(Fmath.truncate(this.correlationMatrix.getElement(var8, var7), this.trunc));
            }

            var1.println();
        }

        var1.println();
        var1.println("PARTIAL CORRELATION MATRIX");
        var1.println("Original item indices in parenthesis");
        var1.println();
        var1.printtab(" ");
        var1.printtab("item");

        for(var7 = 0; var7 < this.nItems; ++var7) {
            var1.printtab(this.eigenValueIndices[var7] + 1 + " (" + (var7 + 1) + ")");
        }

        var1.println();
        var1.println("item");

        for(var7 = 0; var7 < this.nItems; ++var7) {
            var1.printtab(this.eigenValueIndices[var7] + 1 + " (" + (var7 + 1) + ")");
            var1.printtab(" ");

            for(var8 = 0; var8 < this.nItems; ++var8) {
                var1.printtab(Fmath.truncate(this.partialCorrelationMatrix.getElement(var8, var7), this.trunc));
            }

            var1.println();
        }

        var1.println();
        var1.println("COVARIANCE MATRIX");
        var1.println("Original item indices in parenthesis");
        var1.println();
        var1.printtab(" ");
        var1.printtab("item");

        for(var7 = 0; var7 < this.nItems; ++var7) {
            var1.printtab(this.eigenValueIndices[var7] + 1 + " (" + (var7 + 1) + ")");
        }

        var1.println();
        var1.println("item");

        for(var7 = 0; var7 < this.nItems; ++var7) {
            var1.printtab(this.eigenValueIndices[var7] + 1 + " (" + (var7 + 1) + ")");
            var1.printtab(" ");

            for(var8 = 0; var8 < this.nItems; ++var8) {
                var1.printtab(Fmath.truncate(this.covarianceMatrix.getElement(var8, var7), this.trunc));
            }

            var1.println();
        }

        var1.println();
        var1.println("EIGENVECTORS");
        var1.println("Original component indices in parenthesis");
        var1.println("Vector corresponding to an ordered eigenvalues in each row");
        var1.println();
        var1.printtab(" ");
        var1.printtab("component");

        for(var7 = 0; var7 < this.nItems; ++var7) {
            var1.printtab(this.eigenValueIndices[var7] + 1 + " (" + (var7 + 1) + ")");
        }

        var1.println();
        var1.println("component");

        for(var7 = 0; var7 < this.nItems; ++var7) {
            var1.printtab(var7 + 1 + " (" + (this.eigenValueIndices[var7] + 1) + ")");
            var1.printtab(" ");

            for(var8 = 0; var8 < this.nItems; ++var8) {
                var1.printtab(Fmath.truncate(this.orderedEigenVectorsAsRows[var7][var8], this.trunc));
            }

            var1.println();
        }

        var1.println();
        var1.println("LOADING FACTORS");
        var1.println("Original  indices in parenthesis");
        var1.println("Loading factors corresponding to an ordered eigenvalues in each row");
        var1.println();
        var1.printtab(" ");
        var1.printtab("component");

        for(var7 = 0; var7 < this.nItems; ++var7) {
            var1.printtab(this.eigenValueIndices[var7] + 1 + " (" + (var7 + 1) + ")");
        }

        var1.printtab(" ");
        var1.printtab("Eigenvalue");
        var1.printtab("% Proportion");
        var1.println("Cumulative %");
        var1.println("factor");

        for(var7 = 0; var7 < this.nItems; ++var7) {
            var1.printtab(var7 + 1 + " (" + (this.eigenValueIndices[var7] + 1) + ")");
            var1.printtab(" ");

            for(var8 = 0; var8 < this.nItems; ++var8) {
                var1.printtab(Fmath.truncate(this.loadingFactorsAsRows[var7][var8], this.trunc));
            }

            var1.printtab(" ");
            var1.printtab(Fmath.truncate(this.orderedEigenValues[var7], this.trunc));
            var1.printtab(Fmath.truncate(this.proportionPercentage[var7], this.trunc));
            var1.println(Fmath.truncate(this.cumulativePercentage[var7], this.trunc));
        }

        var1.println();
        var1.println("ROTATED LOADING FACTORS");
        if (this.varimaxOption) {
            var1.println("NORMAL VARIMAX");
        } else {
            var1.println("RAW VARIMAX");
        }

        String var16 = "The ordered eigenvalues with Monte Carlo means and percentiles in parenthesis";
        var16 = var16 + "\n (Total number of eigenvalues = " + this.nItems + ")";
        var8 = this.nItems;
        Dimension var9 = Toolkit.getDefaultToolkit().getScreenSize();
        int var10 = var9.height;
        int var11 = 20 * var10 / 800;
        if (var8 > var8) {
            var8 = var11;
        }

        int var12;
        for(var12 = 0; var12 < var8; ++var12) {
            var16 = var16 + "\n " + Fmath.truncate(this.orderedEigenValues[var12], 4) + " (" + Fmath.truncate(this.randomEigenValuesMeans[var12], 4) + "  " + Fmath.truncate(this.randomEigenValuesPercentiles[var12], 4) + ")";
        }

        if (var8 < this.nItems) {
            var16 = var16 + "\n . . . ";
        }

        var16 = var16 + "\nEnter number of eigenvalues to be extracted";
        var12 = this.greaterThanOneLimit;
        var12 = Db.readInt(var16, var12);
        this.varimaxRotation(var12);
        var1.println("Varimax rotation for " + var12 + " extracted factors");
        var1.println("Rotated loading factors and eigenvalues scaled to ensure total 'rotated variance' matches unrotated variance for the extracted factors");
        var1.println("Original  indices in parenthesis");
        var1.println();
        var1.printtab(" ");
        var1.printtab("component");

        int var13;
        for(var13 = 0; var13 < this.nItems; ++var13) {
            var1.printtab(this.rotatedIndices[var13] + 1 + " (" + (var13 + 1) + ")");
        }

        var1.printtab(" ");
        var1.printtab("Eigenvalue");
        var1.printtab("% Proportion");
        var1.println("Cumulative %");
        var1.println("factor");

        int var14;
        for(var13 = 0; var13 < var12; ++var13) {
            var1.printtab(var13 + 1 + " (" + (this.rotatedIndices[var13] + 1) + ")");
            var1.printtab(" ");

            for(var14 = 0; var14 < this.nItems; ++var14) {
                var1.printtab(Fmath.truncate(this.rotatedLoadingFactorsAsRows[var13][var14], this.trunc));
            }

            var1.printtab(" ");
            var1.printtab(Fmath.truncate(this.rotatedEigenValues[var13], this.trunc));
            var1.printtab(Fmath.truncate(this.rotatedProportionPercentage[var13], this.trunc));
            var1.println(Fmath.truncate(this.rotatedCumulativePercentage[var13], this.trunc));
        }

        var1.println();
        var1.println("Kaiser-Meyer-Olkin (KMO) statistic");
        var1.println("   Overall KMO statistic =   " + Fmath.truncate(this.kmo, this.trunc));
        var1.println("   KMO values for each item");
        var1.printtab("   ");

        for(var13 = 0; var13 < this.nItems; ++var13) {
            var1.printtab(var13 + 1);
        }

        var1.println();
        var1.printtab("   ");

        for(var13 = 0; var13 < this.nItems; ++var13) {
            var1.printtab(Fmath.truncate(this.itemKMOs[var13], this.trunc));
        }

        var1.println();
        var1.println();
        var1.println("Bartlett Sphericity Test");
        var1.println("   Chi-Square =         " + Fmath.truncate(this.chiSquareBartlett, this.trunc));
        var1.println("   Probability value =  " + Fmath.truncate(this.probBartlett, this.trunc));
        var1.println("   Degrees of freedom = " + this.dfBartlett);
        var1.println("   Chi-Square value at the 5% significance level  (p = 0.05) = " + Fmath.truncate(this.sign05Bartlett, this.trunc));
        var1.println("   Chi-Square value at the 10% significance level (p = 0.10) = " + Fmath.truncate(this.sign10Bartlett, this.trunc));
        var1.println();
        var1.println("DATA USED");
        var1.println("Number of items = " + this.nItems);
        var1.println("Number of persons = " + this.nPersons);
        if (this.originalDataType == 0) {
            var1.printtab("Item");

            for(var13 = 0; var13 < this.nPersons; ++var13) {
                var1.printtab(var13 + 1);
            }

            var1.println();

            for(var13 = 0; var13 < this.nItems; ++var13) {
                var1.printtab(this.itemNames[var13]);

                for(var14 = 0; var14 < this.nPersons; ++var14) {
                    var1.printtab(Fmath.truncate(this.scores0[var13][var14], this.trunc));
                }

                var1.println();
            }
        } else {
            var1.printtab("Person");

            for(var13 = 0; var13 < this.nItems; ++var13) {
                var1.printtab(this.itemNames[var13]);
            }

            var1.println();

            for(var13 = 0; var13 < this.nPersons; ++var13) {
                var1.printtab(var13 + 1);

                for(var14 = 0; var14 < this.nItems; ++var14) {
                    var1.printtab(Fmath.truncate(this.scores1[var13][var14], this.trunc));
                }

                var1.println();
            }
        }

        var1.close();
    }
}
