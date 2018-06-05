//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.analysis;

import com.flanagan.interpolation.CubicSpline;
import com.flanagan.io.FileOutput;
import com.flanagan.math.ArrayMaths;
import com.flanagan.math.Conv;
import com.flanagan.math.DeepCopy;
import com.flanagan.math.Fmath;
import com.flanagan.math.TimeAndDate;
import com.flanagan.plot.PlotGraph;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Vector;

public class ProbabilityPlot {
    private String title = "";
    private double[] array = null;
    private Stat arrayAsStat = null;
    private double[] sortedData = null;
    private int[] indices = null;
    private double[] weights = null;
    private Stat weightsAsStat = null;
    private double[] sortedWeights = null;
    private boolean weighted = false;
    private int weightingOption = 0;
    private double mean = 0.0D / 0.0;
    private double standardDeviation = 0.0D / 0.0;
    private double minimum = 0.0D / 0.0;
    private double maximum = 0.0D / 0.0;
    private double range = 0.0D / 0.0;
    private double halfWidth = 0.0D / 0.0;
    private double peakPoint = 0.0D / 0.0;
    private int numberOfDataPoints = 0;
    private double dataOffset = 0.0D;
    private boolean dataShifted = false;
    private double[] initialEstimates = null;
    private int lastMethod = -1;
    private int nMethods = 16;
    private String[] methodNames = new String[]{"Gaussian", "Three Parameter Weibull", "Exponential", "Rayleigh", "Pareto", "Gumbel (minimum order statistic)", "Gumbel (maximum order statistic)", "Three Parameter Frechet", "Logistic", "Lorentzian", "Three Parameter Log-Normal", "Two Parameter Log-Normal", "Two Parameter Weibull", "One Parameter (Standard) Weibull", "Standard Gaussian", "F-distribution", "Two Parameter Frechet", "One Parameter (Standard) Frechet"};
    private String[][] methodParameters = new String[][]{{"mu", "sigma"}, {"mu", "sigma", "gamma"}, {"mu", "sigma"}, {"beta"}, {"alpha", "beta"}, {"mu", "sigma"}, {"mu", "sigma"}, {"mu", "sigma", "gamma"}, {"mu", "beta"}, {"mu", "gamma"}, {"alpha", "beta", "gamma"}, {"mu", "sigma"}, {"sigma", "gamma"}, {"gamma"}, {" "}, {" "}, {"sigma", "gamma"}, {"gamma"}};
    private int fDistributionNu1 = 0;
    private int fDistributionNu2 = 0;
    private boolean suppressPlot = false;
    private boolean suppressFile = false;
    private boolean suppressErrorMessages = false;
    private double significance = 0.05D;
    private int gaussianNumberOfParameters = 2;
    private double[] gaussianOrderMedians = null;
    private double[] gaussianParam = null;
    private double[] gaussianParamErrors = null;
    private double gaussianSumOfSquares = 0.0D / 0.0;
    private double gaussianWeightedSumOfSquares = 0.0D / 0.0;
    private double[] gaussianLine = null;
    private double[] gaussianLineErrors = null;
    private double gaussianCorrCoeff = 0.0D / 0.0;
    private boolean gaussianDone = false;
    private int gaussianStandardNumberOfParameters = 0;
    private double[] gaussianStandardOrderMedians = null;
    private double gaussianStandardSumOfSquares = 0.0D / 0.0;
    private double gaussianStandardWeightedSumOfSquares = 0.0D / 0.0;
    private double[] gaussianStandardLine = null;
    private double[] gaussianStandardLineErrors = null;
    private double gaussianStandardCorrCoeff = 0.0D / 0.0;
    private boolean gaussianStandardDone = false;
    private int exponentialNumberOfParameters = 2;
    private double[] exponentialOrderMedians = null;
    private double[] exponentialParam = null;
    private double[] exponentialParamErrors = null;
    private double exponentialSumOfSquares = 0.0D / 0.0;
    private double exponentialWeightedSumOfSquares = 0.0D / 0.0;
    private double[] exponentialLine = null;
    private double[] exponentialLineErrors = null;
    private double exponentialCorrCoeff = 0.0D / 0.0;
    private boolean exponentialDone = false;
    private int fDistributionNumberOfParameters = 0;
    private double[] fDistributionOrderMedians = null;
    private double fDistributionSumOfSquares = 0.0D / 0.0;
    private double fDistributionWeightedSumOfSquares = 0.0D / 0.0;
    private double[] fDistributionLine = null;
    private double[] fDistributionLineErrors = null;
    private double fDistributionCorrCoeff = 0.0D / 0.0;
    private boolean fDistributionDone = false;
    private int frechetNumberOfParameters = 3;
    private double[] frechetOrderMedians = null;
    private double[] frechetParam = null;
    private double[] frechetParamErrors = null;
    private double frechetSumOfSquares = 0.0D / 0.0;
    private double frechetWeightedSumOfSquares = 0.0D / 0.0;
    private double[] frechetLine = null;
    private double[] frechetLineErrors = null;
    private double frechetCorrCoeff = 0.0D / 0.0;
    private boolean frechetDone = false;
    private int frechetTwoParNumberOfParameters = 2;
    private double[] frechetTwoParOrderMedians = null;
    private double[] frechetTwoParParam = null;
    private double[] frechetTwoParParamErrors = null;
    private double frechetTwoParSumOfSquares = 0.0D / 0.0;
    private double frechetTwoParWeightedSumOfSquares = 0.0D / 0.0;
    private double[] frechetTwoParLine = null;
    private double[] frechetTwoParLineErrors = null;
    private double frechetTwoParCorrCoeff = 0.0D / 0.0;
    private boolean frechetTwoParDone = false;
    private int frechetStandardNumberOfParameters = 1;
    private double[] frechetStandardOrderMedians = null;
    private double[] frechetStandardParam = null;
    private double[] frechetStandardParamErrors = null;
    private double frechetStandardSumOfSquares = 0.0D / 0.0;
    private double frechetStandardWeightedSumOfSquares = 0.0D / 0.0;
    private double[] frechetStandardLine = null;
    private double[] frechetStandardLineErrors = null;
    private double frechetStandardCorrCoeff = 0.0D / 0.0;
    private boolean frechetStandardDone = false;
    private int gumbelMinNumberOfParameters = 3;
    private double[] gumbelMinOrderMedians = null;
    private double[] gumbelMinParam = null;
    private double[] gumbelMinParamErrors = null;
    private double gumbelMinSumOfSquares = 0.0D / 0.0;
    private double gumbelMinWeightedSumOfSquares = 0.0D / 0.0;
    private double[] gumbelMinLine = null;
    private double[] gumbelMinLineErrors = null;
    private double gumbelMinCorrCoeff = 0.0D / 0.0;
    private boolean gumbelMinDone = false;
    private int gumbelMaxNumberOfParameters = 3;
    private double[] gumbelMaxOrderMedians = null;
    private double[] gumbelMaxParam = null;
    private double[] gumbelMaxParamErrors = null;
    private double gumbelMaxSumOfSquares = 0.0D / 0.0;
    private double gumbelMaxWeightedSumOfSquares = 0.0D / 0.0;
    private double[] gumbelMaxLine = null;
    private double[] gumbelMaxLineErrors = null;
    private double gumbelMaxCorrCoeff = 0.0D / 0.0;
    private boolean gumbelMaxDone = false;
    private int logisticNumberOfParameters = 3;
    private double[] logisticOrderMedians = null;
    private double[] logisticParam = null;
    private double[] logisticParamErrors = null;
    private double logisticSumOfSquares = 0.0D / 0.0;
    private double logisticWeightedSumOfSquares = 0.0D / 0.0;
    private double[] logisticLine = null;
    private double[] logisticLineErrors = null;
    private double logisticCorrCoeff = 0.0D / 0.0;
    private boolean logisticDone = false;
    private int paretoNumberOfParameters = 2;
    private double[] paretoOrderMedians = null;
    private double[] paretoParam = null;
    private double[] paretoParamErrors = null;
    private double paretoSumOfSquares = 0.0D / 0.0;
    private double paretoWeightedSumOfSquares = 0.0D / 0.0;
    private double[] paretoLine = null;
    private double[] paretoLineErrors = null;
    private double paretoCorrCoeff = 0.0D / 0.0;
    private boolean paretoDone = false;
    private int rayleighNumberOfParameters = 2;
    private double[] rayleighOrderMedians = null;
    private double[] rayleighParam = null;
    private double[] rayleighParamErrors = null;
    private double rayleighSumOfSquares = 0.0D / 0.0;
    private double rayleighWeightedSumOfSquares = 0.0D / 0.0;
    private double[] rayleighLine = null;
    private double[] rayleighLineErrors = null;
    private double rayleighCorrCoeff = 0.0D / 0.0;
    private boolean rayleighDone = false;
    private int weibullNumberOfParameters = 3;
    private double[] weibullOrderMedians = null;
    private double[] weibullParam = null;
    private double[] weibullParamErrors = null;
    private double weibullSumOfSquares = 0.0D / 0.0;
    private double weibullWeightedSumOfSquares = 0.0D / 0.0;
    private double[] weibullLine = null;
    private double[] weibullLineErrors = null;
    private double weibullCorrCoeff = 0.0D / 0.0;
    private boolean weibullDone = false;
    private int weibullTwoParNumberOfParameters = 2;
    private double[] weibullTwoParOrderMedians = null;
    private double[] weibullTwoParParam = null;
    private double[] weibullTwoParParamErrors = null;
    private double weibullTwoParSumOfSquares = 0.0D / 0.0;
    private double weibullTwoParWeightedSumOfSquares = 0.0D / 0.0;
    private double[] weibullTwoParLine = null;
    private double[] weibullTwoParLineErrors = null;
    private double weibullTwoParCorrCoeff = 0.0D / 0.0;
    private boolean weibullTwoParDone = false;
    private int weibullStandardNumberOfParameters = 1;
    private double[] weibullStandardOrderMedians = null;
    private double[] weibullStandardParam = null;
    private double[] weibullStandardParamErrors = null;
    private double weibullStandardSumOfSquares = 0.0D / 0.0;
    private double weibullStandardWeightedSumOfSquares = 0.0D / 0.0;
    private double[] weibullStandardLine = null;
    private double[] weibullStandardLineErrors = null;
    private double weibullStandardCorrCoeff = 0.0D / 0.0;
    private boolean weibullStandardDone = false;
    private boolean probPlotDone = false;
    private double delta = 0.01D;
    private double stepFactor = 0.2D;
    private double tolerance = 1.0E-15D;
    private boolean nFactorOptionI = false;
    private boolean nFactorReset = false;
    private boolean[] userSuppliedEstimates = null;
    private double[][] userInitialEstimates = (double[][])null;
    private TimeAndDate tad = new TimeAndDate();
    private int trunc = 4;
    private String titleStart = "";

    public ProbabilityPlot(double[] var1) {
        this.arrayAsStat = new Stat(var1);
        this.array = this.arrayAsStat.array();
        this.weighted = false;
        this.weightingOption = 0;
        this.initialise();
    }

    public ProbabilityPlot(double[] var1, double[] var2) {
        this.arrayAsStat = new Stat(var1);
        this.array = this.arrayAsStat.array();
        this.weightsAsStat = new Stat(var2);
        this.weights = this.weightsAsStat.array();
        this.weighted = true;
        this.weightingOption = 1;
        this.initialise();
    }

    public ProbabilityPlot(Double[] var1) {
        this.arrayAsStat = new Stat(var1);
        this.array = this.arrayAsStat.array();
        this.weighted = false;
        this.weightingOption = 0;
        this.initialise();
    }

    public ProbabilityPlot(Double[] var1, Double[] var2) {
        this.arrayAsStat = new Stat(var1);
        this.array = this.arrayAsStat.array();
        this.weightsAsStat = new Stat(var2);
        this.weights = this.weightsAsStat.array();
        this.weighted = true;
        this.weightingOption = 1;
        this.initialise();
    }

    public ProbabilityPlot(float[] var1) {
        this.arrayAsStat = new Stat(var1);
        this.array = this.arrayAsStat.array();
        this.weighted = false;
        this.weightingOption = 0;
        this.initialise();
    }

    public ProbabilityPlot(float[] var1, float[] var2) {
        this.arrayAsStat = new Stat(var1);
        this.array = this.arrayAsStat.array();
        this.weightsAsStat = new Stat(var2);
        this.weights = this.weightsAsStat.array();
        this.weighted = true;
        this.weightingOption = 1;
        this.initialise();
    }

    public ProbabilityPlot(Float[] var1) {
        this.arrayAsStat = new Stat(var1);
        this.array = this.arrayAsStat.array();
        this.weighted = false;
        this.weightingOption = 0;
        this.initialise();
    }

    public ProbabilityPlot(Float[] var1, Float[] var2) {
        this.arrayAsStat = new Stat(var1);
        this.array = this.arrayAsStat.array();
        this.weightsAsStat = new Stat(var2);
        this.weights = this.weightsAsStat.array();
        this.weighted = true;
        this.weightingOption = 1;
        this.initialise();
    }

    public ProbabilityPlot(long[] var1) {
        this.arrayAsStat = new Stat(var1);
        this.array = this.arrayAsStat.array();
        this.weighted = false;
        this.weightingOption = 0;
        this.initialise();
    }

    public ProbabilityPlot(long[] var1, long[] var2) {
        this.arrayAsStat = new Stat(var1);
        this.array = this.arrayAsStat.array();
        this.weightsAsStat = new Stat(var2);
        this.weights = this.weightsAsStat.array();
        this.weighted = true;
        this.weightingOption = 1;
        this.initialise();
    }

    public ProbabilityPlot(Long[] var1) {
        this.arrayAsStat = new Stat(var1);
        this.array = this.arrayAsStat.array();
        this.weighted = false;
        this.weightingOption = 0;
        this.initialise();
    }

    public ProbabilityPlot(Long[] var1, Long[] var2) {
        this.arrayAsStat = new Stat(var1);
        this.array = this.arrayAsStat.array();
        this.weightsAsStat = new Stat(var2);
        this.weights = this.weightsAsStat.array();
        this.weighted = true;
        this.weightingOption = 1;
        this.initialise();
    }

    public ProbabilityPlot(int[] var1) {
        this.arrayAsStat = new Stat(var1);
        this.array = this.arrayAsStat.array();
        this.weighted = false;
        this.weightingOption = 0;
        this.initialise();
    }

    public ProbabilityPlot(int[] var1, int[] var2) {
        this.arrayAsStat = new Stat(var1);
        this.array = this.arrayAsStat.array();
        this.weightsAsStat = new Stat(var2);
        this.weights = this.weightsAsStat.array();
        this.weighted = true;
        this.weightingOption = 1;
        this.initialise();
    }

    public ProbabilityPlot(Integer[] var1) {
        this.arrayAsStat = new Stat(var1);
        this.array = this.arrayAsStat.array();
        this.weighted = false;
        this.weightingOption = 0;
        this.initialise();
    }

    public ProbabilityPlot(Integer[] var1, Integer[] var2) {
        this.arrayAsStat = new Stat(var1);
        this.array = this.arrayAsStat.array();
        this.weightsAsStat = new Stat(var2);
        this.weights = this.weightsAsStat.array();
        this.weighted = true;
        this.weightingOption = 1;
        this.initialise();
    }

    public ProbabilityPlot(short[] var1) {
        this.arrayAsStat = new Stat(var1);
        this.array = this.arrayAsStat.array();
        this.weighted = false;
        this.weightingOption = 0;
        this.initialise();
    }

    public ProbabilityPlot(short[] var1, short[] var2) {
        this.arrayAsStat = new Stat(var1);
        this.array = this.arrayAsStat.array();
        this.weightsAsStat = new Stat(var2);
        this.weights = this.weightsAsStat.array();
        this.weighted = true;
        this.weightingOption = 1;
        this.initialise();
    }

    public ProbabilityPlot(Short[] var1) {
        this.arrayAsStat = new Stat(var1);
        this.array = this.arrayAsStat.array();
        this.weightingOption = 0;
        this.initialise();
    }

    public ProbabilityPlot(Short[] var1, Short[] var2) {
        this.arrayAsStat = new Stat(var1);
        this.array = this.arrayAsStat.array();
        this.weightsAsStat = new Stat(var2);
        this.weights = this.weightsAsStat.array();
        this.weighted = true;
        this.weightingOption = 1;
        this.initialise();
    }

    public ProbabilityPlot(byte[] var1) {
        this.arrayAsStat = new Stat(var1);
        this.array = this.arrayAsStat.array();
        this.weightingOption = 0;
        this.weighted = false;
        this.initialise();
    }

    public ProbabilityPlot(byte[] var1, byte[] var2) {
        this.arrayAsStat = new Stat(var1);
        this.array = this.arrayAsStat.array();
        this.weightsAsStat = new Stat(var2);
        this.weights = this.weightsAsStat.array();
        this.weighted = true;
        this.weightingOption = 1;
        this.initialise();
    }

    public ProbabilityPlot(Byte[] var1) {
        this.arrayAsStat = new Stat(var1);
        this.array = this.arrayAsStat.array();
        this.weighted = false;
        this.weightingOption = 0;
        this.initialise();
    }

    public ProbabilityPlot(Byte[] var1, Byte[] var2) {
        this.arrayAsStat = new Stat(var1);
        this.array = this.arrayAsStat.array();
        this.weightsAsStat = new Stat(var2);
        this.weights = this.weightsAsStat.array();
        this.weighted = true;
        this.weightingOption = 1;
        this.initialise();
    }

    public ProbabilityPlot(BigDecimal[] var1) {
        this.arrayAsStat = new Stat(var1);
        this.weighted = false;
        this.weightingOption = 0;
        this.initialise();
    }

    public ProbabilityPlot(BigDecimal[] var1, BigDecimal[] var2) {
        this.arrayAsStat = new Stat(var1);
        this.array = this.arrayAsStat.array();
        this.weightsAsStat = new Stat(var2);
        this.weights = this.weightsAsStat.array();
        this.weighted = true;
        this.weightingOption = 1;
        this.initialise();
    }

    public ProbabilityPlot(BigInteger[] var1) {
        this.arrayAsStat = new Stat(var1);
        this.array = this.arrayAsStat.array();
        this.weighted = false;
        this.weightingOption = 0;
        this.initialise();
    }

    public ProbabilityPlot(BigInteger[] var1, BigInteger[] var2) {
        this.arrayAsStat = new Stat(var1);
        this.array = this.arrayAsStat.array();
        this.weightsAsStat = new Stat(var2);
        this.weights = this.weightsAsStat.array();
        this.weighted = true;
        this.weightingOption = 1;
        this.initialise();
    }

    public ProbabilityPlot(Object[] var1) {
        this.arrayAsStat = new Stat(var1);
        this.array = this.arrayAsStat.array();
        this.weighted = false;
        this.weightingOption = 0;
        this.initialise();
    }

    public ProbabilityPlot(Object[] var1, Object[] var2) {
        this.arrayAsStat = new Stat(var1);
        this.array = this.arrayAsStat.array();
        this.weightsAsStat = new Stat(var2);
        this.weights = this.weightsAsStat.array();
        this.weighted = true;
        this.weightingOption = 1;
        this.initialise();
    }

    public ProbabilityPlot(Vector<Object> var1) {
        this.arrayAsStat = new Stat(var1);
        this.array = this.arrayAsStat.array();
        this.weighted = false;
        this.weightingOption = 0;
        this.initialise();
    }

    public ProbabilityPlot(Vector<Object> var1, Vector<Object> var2) {
        this.arrayAsStat = new Stat(var1);
        this.array = this.arrayAsStat.array();
        this.weightsAsStat = new Stat(var2);
        this.weights = this.weightsAsStat.array();
        this.weighted = true;
        this.weightingOption = 1;
        this.initialise();
    }

    public ProbabilityPlot(ArrayList<Object> var1) {
        this.arrayAsStat = new Stat(var1);
        this.array = this.arrayAsStat.array();
        this.weighted = false;
        this.weightingOption = 0;
        this.initialise();
    }

    public ProbabilityPlot(ArrayList<Object> var1, ArrayList<Object> var2) {
        this.arrayAsStat = new Stat(var1);
        this.array = this.arrayAsStat.array();
        this.weightsAsStat = new Stat(var2);
        this.weights = this.weightsAsStat.array();
        this.weighted = true;
        this.weightingOption = 1;
        this.initialise();
    }

    public ProbabilityPlot(ArrayMaths var1) {
        double[] var2 = var1.array();
        this.arrayAsStat = new Stat(var2);
        this.array = this.arrayAsStat.array();
        this.weighted = false;
        this.weightingOption = 0;
        this.initialise();
    }

    public ProbabilityPlot(ArrayMaths var1, ArrayMaths var2) {
        double[] var3 = var1.array();
        this.arrayAsStat = new Stat(var3);
        this.array = this.arrayAsStat.array();
        double[] var4 = var2.array();
        this.weightsAsStat = new Stat(var4);
        this.weights = this.weightsAsStat.array();
        this.weighted = true;
        this.weightingOption = 1;
        this.initialise();
    }

    public ProbabilityPlot(Stat var1) {
        this.arrayAsStat = var1;
        this.array = this.arrayAsStat.array();
        this.weighted = false;
        this.weightingOption = 0;
        this.initialise();
    }

    public ProbabilityPlot(Stat var1, Stat var2) {
        this.arrayAsStat = var1;
        this.array = this.arrayAsStat.array();
        this.weightsAsStat = var2;
        this.weights = this.weightsAsStat.array();
        this.weighted = true;
        this.weightingOption = 1;
        this.initialise();
    }

    private void initialise() {
        this.numberOfDataPoints = this.array.length;
        ArrayMaths var1 = this.arrayAsStat.sort();
        this.indices = var1.originalIndices();
        this.sortedData = var1.array();
        this.mean = this.arrayAsStat.mean();
        this.standardDeviation = this.arrayAsStat.standardDeviation();
        this.minimum = this.arrayAsStat.minimum();
        this.maximum = this.arrayAsStat.maximum();
        this.range = this.maximum - this.minimum;
        this.sortedWeights = new double[this.numberOfDataPoints];
        int var2;
        if (this.weighted) {
            for(var2 = 0; var2 < this.numberOfDataPoints; ++var2) {
                this.sortedWeights[var2] = this.weights[this.indices[var2]];
            }
        } else {
            this.weights = new double[this.numberOfDataPoints];

            for(var2 = 0; var2 < this.numberOfDataPoints; ++var2) {
                this.weights[var2] = 1.0D;
                this.sortedWeights[var2] = 1.0D;
            }
        }

        this.userSuppliedEstimates = new boolean[this.nMethods];

        for(var2 = 0; var2 < this.nMethods; ++var2) {
            this.userSuppliedEstimates[var2] = false;
        }

        this.userSuppliedEstimates = new boolean[this.nMethods];
        this.userInitialEstimates = new double[this.nMethods][];
    }

    public void resetSignificance(double var1) {
        if (var1 >= 0.0D && var1 <= 1.0D) {
            this.significance = var1;
        } else {
            throw new IllegalArgumentException("The argument signif, " + var1 + ", must lie between 0 and 1 inclusive");
        }
    }

    public double getSignificance() {
        return this.significance;
    }

    public void suppressDisplay() {
        this.suppressPlot = true;
    }

    public void restoreDisplay() {
        this.suppressPlot = false;
    }

    public void suppressFileOutput() {
        this.suppressFile = true;
    }

    public void restoreFileOutput() {
        this.suppressFile = false;
    }

    public void suppressErrorMessages() {
        this.suppressErrorMessages = true;
    }

    public void restoreErrorMessages() {
        this.suppressErrorMessages = false;
    }

    public void setWeightingOption(int var1) {
        if (var1 >= 1 && var1 <= 4) {
            if (this.weighted && this.weightingOption == 1) {
                throw new IllegalArgumentException("Weights already entered via a constructor");
            } else {
                int var2;
                label49:
                switch(var1) {
                    case 1:
                        var2 = 0;

                        while(true) {
                            if (var2 >= this.numberOfDataPoints) {
                                break label49;
                            }

                            this.sortedWeights[var2] = Math.abs(this.sortedData[var2]);
                            ++var2;
                        }
                    case 2:
                        var2 = 0;

                        while(true) {
                            if (var2 >= this.numberOfDataPoints) {
                                break label49;
                            }

                            this.sortedWeights[var2] = Math.sqrt(Math.abs(this.sortedData[var2]));
                            ++var2;
                        }
                    case 3:
                        var2 = 0;

                        while(true) {
                            if (var2 >= this.numberOfDataPoints) {
                                break label49;
                            }

                            this.sortedWeights[var2] = 1.0D / Math.abs(this.sortedData[var2]);
                            ++var2;
                        }
                    case 4:
                        for(var2 = 0; var2 < this.numberOfDataPoints; ++var2) {
                            this.sortedWeights[var2] = 1.0D / Math.sqrt(Math.abs(this.sortedData[var2]));
                        }
                }

                this.weightingOption = var1 + 1;
            }
        } else {
            throw new IllegalArgumentException("The option argument, " + var1 + ", should be 1, 2, 3 or 4");
        }
    }

    public String getWeightingOption() {
        String var1 = null;
        switch(this.weightingOption) {
            case 0:
                var1 = "Unweighted regression";
                break;
            case 1:
                var1 = "Weighted regression: user supplied weights";
                break;
            case 2:
                var1 = "Weighted regression: weight = response value";
                break;
            case 3:
                var1 = "Weighted regression: weight = square root of the response value";
                break;
            case 4:
                var1 = "Weighted regression: weight = reciprocal of the response value";
                break;
            case 5:
                var1 = "Weighted regression: weight = reciprocal of the square root of the response value";
        }

        return var1;
    }

    public void removeWeights() {
        this.weighted = false;

        for(int var1 = 0; var1 < this.numberOfDataPoints; ++var1) {
            this.sortedWeights[var1] = 1.0D;
        }

    }

    public void restoreWeights() {
        this.weighted = true;

        for(int var1 = 0; var1 < this.numberOfDataPoints; ++var1) {
            this.sortedWeights[var1] = this.weights[this.indices[var1]];
        }

    }

    private void negativeAndNonZeroDataShift() {
        this.dataShifted = false;
        if (this.minimum <= 0.0D) {
            this.dataOffset = this.range * 0.01D - this.minimum;
            this.dataShift();
        }

    }

    private void negativeDataShift() {
        this.dataShifted = false;
        if (this.minimum < 0.0D) {
            this.dataOffset = -this.minimum;
            this.dataShift();
        }

    }

    private void dataShift() {
        for(int var1 = 0; var1 < this.numberOfDataPoints; ++var1) {
            this.sortedData[var1] += this.dataOffset;
        }

        this.minimum += this.dataOffset;
        this.maximum += this.dataOffset;
        this.mean += this.dataOffset;
        this.dataShifted = true;
    }

    public double getdataOffset() {
        return this.dataOffset;
    }

    private double peakWidth() {
        this.halfWidth = 0.0D;
        Object var1 = null;
        int var2 = 10000;
        double[] var38;
        if (this.numberOfDataPoints >= 1000) {
            var38 = this.sortedData;
            var2 = this.numberOfDataPoints;
        } else {
            double[] var3 = new double[this.numberOfDataPoints];

            for(int var4 = 0; var4 < this.numberOfDataPoints; ++var4) {
                var3[var4] = (double)var4;
            }

            double var40 = (double)(this.numberOfDataPoints - 1) / (double)(var2 - 1);
            var38 = new double[var2];
            CubicSpline var6 = new CubicSpline(var3, this.sortedData);
            double var7 = 0.0D;

            for(int var9 = 0; var9 < var2 - 1; ++var9) {
                var38[var9] = var6.interpolate(var7);
                var7 += var40;
            }

            var38[var2 - 1] = (double)(this.numberOfDataPoints - 1);
        }

        byte var39 = 100;
        double[] var41 = new double[var39];
        double[] var5 = new double[var39];
        double var42 = this.range / (double)var39;
        double var8 = this.minimum;
        double var10 = var8 + var42;
        int var12 = 0;

        for(int var13 = 0; var13 < var39; ++var13) {
            var5[var13] = (var10 + var8) / 2.0D;
            var41[var13] = 0.0D;
            boolean var14 = true;
            if (var12 >= var2) {
                var14 = false;
            }

            while(var14) {
                if (var38[var12] < var10) {
                    ++var41[var13];
                } else {
                    var14 = false;
                }

                ++var12;
                if (var12 >= var2) {
                    var14 = false;
                }
            }

            var8 = var10;
            var10 += var42;
        }

        if (var12 < var2) {
            var41[var39 - 1] += (double)(var2 - var12);
        }

        ArrayMaths var43 = new ArrayMaths(var41);
        double var44 = var43.maximum();
        int var16 = var43.maximumIndex();
        this.peakPoint = var5[var16];
        double var17 = var44 / 2.0D;
        double var19 = 0.0D;
        boolean var21 = false;
        double var22 = 0.0D;
        boolean var24 = false;
        double[] var28;
        if (var41[0] == var17) {
            var19 = var5[0];
            var21 = true;
        } else if (var41[0] < var17) {
            if (var16 >= 2) {
                double[] var25 = new double[var16 + 1];
                double[] var26 = new double[var16 + 1];

                for(int var27 = 0; var27 <= var16; ++var27) {
                    var25[var27] = var41[var27];
                    var26[var27] = var5[var27];
                }

                CubicSpline var47 = new CubicSpline(var26, var25);
                var28 = new double[100];
                double[] var29 = new double[100];
                double var30 = (var26[var16] - var26[0]) / 99.0D;
                double var32 = var26[0];

                for(int var34 = 0; var34 < 99; ++var34) {
                    var28[var34] = var32;
                    var29[var34] = var47.interpolate(var32);
                    var32 += var30;
                }

                var29[99] = var25[var16];
                var28[99] = var26[var16];
                boolean var53 = true;
                int var35 = 0;

                while(var53) {
                    if (var17 <= var29[var35]) {
                        if (var35 == 0) {
                            var19 = var28[0];
                            var53 = false;
                            var21 = true;
                        } else if (var35 == 99) {
                            var19 = var28[99];
                            var53 = false;
                            var21 = true;
                        } else {
                            var19 = (var28[var35] + var28[var35 - 1]) / 2.0D;
                            var53 = false;
                            var21 = true;
                        }
                    }

                    ++var35;
                    if (var35 >= 100) {
                        var53 = false;
                    }
                }
            } else if (var16 == 2) {
                if (var41[1] >= var17) {
                    var19 = var5[0] + (var5[1] - var5[0]) * (var17 - var41[0]) / (var41[1] - var41[0]);
                    var21 = true;
                } else {
                    var19 = var5[1] + (var5[2] - var5[1]) * (var17 - var41[1]) / (var41[2] - var41[1]);
                    var21 = true;
                }
            } else {
                var19 = var5[0] + (var5[1] - var5[0]) * (var17 - var41[0]) / (var41[1] - var41[0]);
                var21 = true;
            }
        } else if (var16 > 2 && var41[var16] - var41[0] > var17 * 0.5D) {
            var19 = var5[0] + (var5[1] - var5[0]) * (var17 - var41[0]) / (var41[1] - var41[0]);
            var21 = true;
        }

        int var45 = var39 - 1;
        int var46 = var39 - var16;
        if (var41[var45] == var17) {
            var22 = var5[var45];
            var24 = true;
        } else if (var41[var45] < var17) {
            if (var46 >= 3) {
                double[] var48 = new double[var46];
                var28 = new double[var46];
                int var49 = 0;

                for(int var50 = var16; var50 < var39; ++var50) {
                    var48[var49] = var41[var50];
                    var28[var49] = var5[var50];
                    ++var49;
                }

                CubicSpline var51 = new CubicSpline(var28, var48);
                double[] var31 = new double[100];
                double[] var52 = new double[100];
                double var33 = (var28[var46 - 1] - var28[0]) / 99.0D;
                double var54 = var28[0];

                for(int var37 = 0; var37 < 99; ++var37) {
                    var31[var37] = var54;
                    var52[var37] = var51.interpolate(var54);
                    var54 += var33;
                }

                var52[99] = var48[var46 - 1];
                var31[99] = var28[var46 - 1];
                boolean var55 = true;
                var49 = 0;

                while(var55) {
                    if (var17 <= var52[var49]) {
                        if (var49 == 0) {
                            var22 = var31[0];
                            var55 = false;
                            var24 = true;
                        } else if (var49 == 99) {
                            var22 = var31[99];
                            var55 = false;
                            var24 = true;
                        } else {
                            var22 = (var31[var49] + var31[var49 - 1]) / 2.0D;
                            var55 = false;
                            var24 = true;
                        }
                    }

                    ++var49;
                    if (var49 >= 100) {
                        var55 = false;
                    }
                }
            } else if (var46 == 2) {
                if (var41[var45 - 1] >= var17) {
                    var22 = var5[var45 - 1] + (var5[var45] - var5[var45 - 1]) * (var17 - var41[var45 - 1]) / (var41[var45] - var41[var45 - 1]);
                    var24 = true;
                } else {
                    var22 = var5[var45 - 2] + (var5[var45 - 1] - var5[var45 - 2]) * (var17 - var41[var45 - 2]) / (var41[var45 - 1] - var41[var45 - 2]);
                    var24 = true;
                }
            } else {
                var22 = var5[var45 - 1] + (var5[var45] - var5[var45 - 1]) * (var17 - var41[var45 - 1]) / (var41[var45] - var41[var45 - 1]);
                var24 = true;
            }
        } else if (var46 > 2 && var41[var16] - var41[var45] > var17 * 0.5D) {
            var22 = var5[var45 - 1] + (var5[var45] - var5[var45 - 1]) * (var17 - var41[var45 - 1]) / (var41[var45] - var41[var45 - 1]);
            var24 = true;
        }

        if (var21) {
            if (var24) {
                this.halfWidth = var22 - var19;
            } else {
                this.halfWidth = (this.peakPoint - var19) * 1.3D;
            }
        } else if (var24) {
            this.halfWidth = (var22 - this.peakPoint) * 1.3D;
        } else {
            System.out.println("Half height width could not be calculated - half range returned");
            this.halfWidth = this.range / 2.0D;
        }

        return this.halfWidth;
    }

    public double correlationCoefficientCriticalValue() {
        return this.correlationCoefficientCriticalValue(this.significance);
    }

    public double correlationCoefficientCriticalValue(double var1) {
        double var3 = 0.0D / 0.0;
        var3 = Stat.corrCoeffInverseCDFoneTailed(var1, this.numberOfDataPoints - 2);
        return var3;
    }

    public void gaussianUserSuppliedInitialEstimates(double var1, double var3) {
        this.userSuppliedEstimates[0] = true;
        this.userInitialEstimates[0] = new double[2];
        this.userInitialEstimates[0][0] = var1;
        this.userInitialEstimates[0][1] = var3;
    }

    public void normalUserSuppliedInitialEstimates(double var1, double var3) {
        this.userSuppliedEstimates[0] = true;
        this.userInitialEstimates[0] = new double[2];
        this.userInitialEstimates[0][0] = var1;
        this.userInitialEstimates[0][1] = var3;
    }

    public void removeGaussianUserSuppliedInitialEstimates() {
        this.userSuppliedEstimates[0] = false;
    }

    public void removeNormalUserSuppliedInitialEstimates() {
        this.userSuppliedEstimates[0] = false;
    }

    public void gaussianProbabilityPlot() {
        this.gaussianProbabilityPlot("");
    }

    public void gaussianProbabilityPlot(String var1) {
        this.title = var1;
        this.lastMethod = 0;
        this.gaussianNumberOfParameters = 2;
        if (this.numberOfDataPoints < 3) {
            throw new IllegalArgumentException("There must be at least three fillData points - preferably considerably more");
        } else {
            Regression var2 = new Regression(this.sortedData, this.sortedData, this.sortedWeights);
            if (this.suppressErrorMessages) {
                var2.suppressErrorMessages();
            }

            double[] var3 = new double[2];
            if (this.userSuppliedEstimates[0]) {
                var3[0] = this.userInitialEstimates[0][0];
                var3[1] = this.userInitialEstimates[0][1];
                if (var3[1] <= 0.0D) {
                    var3[1] = this.range * 0.01D;
                }
            } else {
                var3[0] = this.mean;
                var3[1] = this.standardDeviation;
            }

            this.initialEstimates = var3;
            double[] var4 = new double[]{this.stepFactor * Math.abs(var3[0]), this.stepFactor * Math.abs(var3[1])};
            if (var4[0] == 0.0D) {
                var4[0] = this.stepFactor * this.standardDeviation;
            }

            if (var4[1] == 0.0D) {
                var4[1] = this.stepFactor * this.standardDeviation;
            }

            var2.addConstraint(1, -1, 0.0D);
            GaussProbPlotFunc var5 = new GaussProbPlotFunc();
            var5.setDataArray(this.numberOfDataPoints);
            var2.simplex(var5, DeepCopy.copy(var3), var4, this.tolerance);
            double[] var6 = var2.getBestEstimates();
            double[] var7 = var2.getBestEstimatesErrors();
            double var8 = var2.getSumOfSquares();
            double var10 = var8;
            double var12 = var8;
            if (this.weighted) {
                var10 = var2.getSumOfWeightedResidualSquares();
                var12 = var10;
            }

            double[] var14 = new double[this.gaussianNumberOfParameters];
            var14[0] = 2.0D * var6[0] - var3[0];
            var4[0] = Math.abs(var14[0] * this.stepFactor);
            if (var4[0] == 0.0D) {
                var4[0] = this.range * 0.01D;
            }

            var14[1] = 2.0D * var6[1] - var3[1];
            if (var14[1] <= 0.0D) {
                var14[1] = this.range * 0.01D;
            }

            var4[1] = Math.abs(var14[1] * this.stepFactor);
            var2.simplex(var5, DeepCopy.copy(var14), var4, this.tolerance);
            this.gaussianParam = var2.getBestEstimates();
            this.gaussianParamErrors = var2.getBestEstimatesErrors();
            this.gaussianSumOfSquares = var2.getSumOfSquares();
            this.gaussianWeightedSumOfSquares = this.gaussianSumOfSquares;
            double var15 = this.gaussianSumOfSquares;
            if (this.weighted) {
                this.gaussianWeightedSumOfSquares = var2.getChiSquare();
                var15 = this.gaussianWeightedSumOfSquares;
            }

            if (var12 < var15) {
                this.gaussianParam = var6;
                this.gaussianParamErrors = var7;
                this.gaussianSumOfSquares = var8;
                if (this.weighted) {
                    this.gaussianWeightedSumOfSquares = var10;
                }
            }

            this.gaussianOrderMedians = Stat.gaussianOrderStatisticMedians(this.gaussianParam[0], this.gaussianParam[1], this.numberOfDataPoints);
            Regression var17 = new Regression(this.gaussianOrderMedians, this.sortedData, this.sortedWeights);
            if (this.suppressErrorMessages) {
                var17.suppressErrorMessages();
            }

            var17.linear();
            this.gaussianLine = var17.getBestEstimates();
            this.gaussianLineErrors = var17.getBestEstimatesErrors();
            this.gaussianCorrCoeff = var17.getSampleR();
            this.gaussianSumOfSquares = var2.getSumOfSquares();
            if (!this.suppressFile) {
                this.outputFile(this.lastMethod, var2, var17, this.gaussianNumberOfParameters);
            }

            if (!this.suppressPlot) {
                double[][] var18 = PlotGraph.fillData(2, this.numberOfDataPoints);
                var18[0] = this.gaussianOrderMedians;
                var18[1] = this.sortedData;
                var18[2] = this.gaussianOrderMedians;

                for(int var19 = 0; var19 < this.numberOfDataPoints; ++var19) {
                    var18[3][var19] = this.gaussianLine[0] + this.gaussianLine[1] * this.gaussianOrderMedians[var19];
                }

                PlotGraph var22 = new PlotGraph(var18);
                int[] var20 = new int[]{4, 0};
                var22.setPoint(var20);
                int[] var21 = new int[]{0, 3};
                var22.setLine(var21);
                var22.setXaxisLegend("Gaussian Order Statistic Medians");
                var22.setYaxisLegend("Ordered Data Values");
                var22.setGraphTitle(this.titleStart + "Gaussian probability plot, " + this.title + ":   gradient = " + Fmath.truncate(this.gaussianLine[1], 4) + ", intercept = " + Fmath.truncate(this.gaussianLine[0], 4) + ",  R = " + Fmath.truncate(this.gaussianCorrCoeff, 4));
                var22.setGraphTitle2("  mu = " + Fmath.truncate(this.gaussianParam[0], 4) + ", sigma = " + Fmath.truncate(this.gaussianParam[1], 4));
                var22.plot();
                this.probPlotDone = true;
            }

            this.gaussianDone = true;
        }
    }

    public void normalProbabilityPlot(String var1) {
        this.gaussianProbabilityPlot(var1);
    }

    public void normalProbabilityPlot() {
        this.gaussianProbabilityPlot();
    }

    public double gaussianMu() {
        if (!this.gaussianDone) {
            this.gaussianProbabilityPlot(this.title);
        }

        return this.gaussianParam[0];
    }

    public double gaussianMuError() {
        if (!this.gaussianDone) {
            this.gaussianProbabilityPlot(this.title);
        }

        return this.gaussianParamErrors[0];
    }

    public double gaussianSigma() {
        if (!this.gaussianDone) {
            this.gaussianProbabilityPlot(this.title);
        }

        return this.gaussianParam[1];
    }

    public double gaussianSigmaError() {
        if (!this.gaussianDone) {
            this.gaussianProbabilityPlot(this.title);
        }

        return this.gaussianParamErrors[1];
    }

    public double gaussianGradient() {
        if (!this.gaussianDone) {
            this.gaussianProbabilityPlot(this.title);
        }

        return this.gaussianLine[1];
    }

    public double gaussianGradientError() {
        if (!this.gaussianDone) {
            this.gaussianProbabilityPlot(this.title);
        }

        return this.gaussianLineErrors[1];
    }

    public double gaussianIntercept() {
        if (!this.gaussianDone) {
            this.gaussianProbabilityPlot(this.title);
        }

        return this.gaussianLine[0];
    }

    public double gaussianInterceptError() {
        if (!this.gaussianDone) {
            this.gaussianProbabilityPlot(this.title);
        }

        return this.gaussianLineErrors[0];
    }

    public double gaussianCorrelationCoefficient() {
        if (!this.gaussianDone) {
            this.gaussianProbabilityPlot(this.title);
        }

        return this.gaussianCorrCoeff;
    }

    public double gaussianSumOfSquares() {
        if (!this.gaussianDone) {
            this.gaussianProbabilityPlot(this.title);
        }

        return this.gaussianSumOfSquares;
    }

    public double gaussianWeightedSumOfSquares() {
        if (!this.gaussianDone) {
            this.gaussianProbabilityPlot(this.title);
        }

        return this.gaussianWeightedSumOfSquares;
    }

    public double[] gaussianOrderStatisticMedians() {
        if (!this.gaussianDone) {
            this.gaussianProbabilityPlot(this.title);
        }

        return this.gaussianOrderMedians;
    }

    public double normalMu() {
        if (!this.gaussianDone) {
            this.gaussianProbabilityPlot(this.title);
        }

        return this.gaussianParam[0];
    }

    public double normalMuError() {
        if (!this.gaussianDone) {
            this.gaussianProbabilityPlot(this.title);
        }

        return this.gaussianParamErrors[0];
    }

    public double normalSigma() {
        if (!this.gaussianDone) {
            this.gaussianProbabilityPlot(this.title);
        }

        return this.gaussianParam[1];
    }

    public double normalSigmaError() {
        if (!this.gaussianDone) {
            this.gaussianProbabilityPlot(this.title);
        }

        return this.gaussianParamErrors[1];
    }

    public double normalGradient() {
        if (!this.gaussianDone) {
            this.gaussianProbabilityPlot(this.title);
        }

        return this.gaussianLine[1];
    }

    public double normalGradientError() {
        if (!this.gaussianDone) {
            this.gaussianProbabilityPlot(this.title);
        }

        return this.gaussianLineErrors[1];
    }

    public double normalIntercept() {
        if (!this.gaussianDone) {
            this.gaussianProbabilityPlot(this.title);
        }

        return this.gaussianLine[0];
    }

    public double normalInterceptError() {
        if (!this.gaussianDone) {
            this.gaussianProbabilityPlot(this.title);
        }

        return this.gaussianLineErrors[0];
    }

    public double normalCorrelationCoefficient() {
        if (!this.gaussianDone) {
            this.gaussianProbabilityPlot(this.title);
        }

        return this.gaussianCorrCoeff;
    }

    public double normalSumOfSquares() {
        if (!this.gaussianDone) {
            this.gaussianProbabilityPlot(this.title);
        }

        return this.gaussianSumOfSquares;
    }

    public double normalWeightedSumOfSquares() {
        if (!this.gaussianDone) {
            this.gaussianProbabilityPlot(this.title);
        }

        return this.gaussianWeightedSumOfSquares;
    }

    public double[] normalOrderStatisticMedians() {
        if (!this.gaussianDone) {
            this.gaussianProbabilityPlot(this.title);
        }

        return this.gaussianOrderMedians;
    }

    public void gaussianStandardProbabilityPlot() {
        this.gaussianStandardProbabilityPlot("");
    }

    public void gaussianStandardProbabilityPlot(String var1) {
        this.title = var1;
        this.lastMethod = 14;
        this.gaussianStandardNumberOfParameters = 2;
        if (this.numberOfDataPoints < 3) {
            throw new IllegalArgumentException("There must be at least three fillData points - preferably considerably more");
        } else {
            this.gaussianStandardOrderMedians = Stat.gaussianOrderStatisticMedians(this.numberOfDataPoints);
            Regression var2 = new Regression(this.gaussianStandardOrderMedians, this.sortedData, this.sortedWeights);
            if (this.suppressErrorMessages) {
                var2.suppressErrorMessages();
            }

            var2.linear();
            this.gaussianStandardLine = var2.getBestEstimates();
            this.gaussianStandardLineErrors = var2.getBestEstimatesErrors();
            this.gaussianStandardCorrCoeff = var2.getSampleR();
            this.gaussianStandardSumOfSquares = var2.getSumOfSquares();
            this.gaussianStandardWeightedSumOfSquares = var2.getSumOfSquares();
            if (this.weighted) {
                this.gaussianStandardWeightedSumOfSquares = var2.getChiSquare();
            }

            if (!this.suppressFile) {
                this.outputFile(this.lastMethod, (Regression)null, var2, 0);
            }

            if (!this.suppressPlot) {
                double[][] var3 = PlotGraph.fillData(2, this.numberOfDataPoints);
                var3[0] = this.gaussianStandardOrderMedians;
                var3[1] = this.sortedData;
                var3[2] = this.gaussianStandardOrderMedians;

                for(int var4 = 0; var4 < this.numberOfDataPoints; ++var4) {
                    var3[3][var4] = this.gaussianStandardLine[0] + this.gaussianStandardLine[1] * this.gaussianStandardOrderMedians[var4];
                }

                PlotGraph var7 = new PlotGraph(var3);
                int[] var5 = new int[]{4, 0};
                var7.setPoint(var5);
                int[] var6 = new int[]{0, 3};
                var7.setLine(var6);
                var7.setXaxisLegend("Standard Gaussian Order Statistic Medians");
                var7.setYaxisLegend("Ordered Data Values");
                var7.setGraphTitle("Standard Gaussian probability plot, " + this.title + ":   gradient = " + Fmath.truncate(this.gaussianStandardLine[1], 4) + ", intercept = " + Fmath.truncate(this.gaussianStandardLine[0], 4) + ",  R = " + Fmath.truncate(this.gaussianStandardCorrCoeff, 4));
                var7.plot();
                this.probPlotDone = true;
            }

            this.gaussianStandardDone = true;
        }
    }

    public void normalStandardProbabilityPlot() {
        this.gaussianStandardProbabilityPlot();
    }

    public void normalStandardProbabilityPlot(String var1) {
        this.gaussianStandardProbabilityPlot(var1);
    }

    public double gaussianStandardGradient() {
        if (!this.gaussianStandardDone) {
            this.gaussianStandardProbabilityPlot(this.title);
        }

        return this.gaussianStandardLine[1];
    }

    public double gaussianStandardGradientError() {
        if (!this.gaussianStandardDone) {
            this.gaussianStandardProbabilityPlot(this.title);
        }

        return this.gaussianStandardLineErrors[1];
    }

    public double gaussianStandardIntercept() {
        if (!this.gaussianStandardDone) {
            this.gaussianStandardProbabilityPlot(this.title);
        }

        return this.gaussianStandardLine[0];
    }

    public double gaussianStandardInterceptError() {
        if (!this.gaussianStandardDone) {
            this.gaussianStandardProbabilityPlot(this.title);
        }

        return this.gaussianStandardLineErrors[0];
    }

    public double gaussianStandardCorrelationCoefficient() {
        if (!this.gaussianStandardDone) {
            this.gaussianStandardProbabilityPlot(this.title);
        }

        return this.gaussianStandardCorrCoeff;
    }

    public double gaussianStandardSumOfSquares() {
        if (!this.gaussianStandardDone) {
            this.gaussianStandardProbabilityPlot(this.title);
        }

        return this.gaussianStandardSumOfSquares;
    }

    public double[] gaussianStandardOrderStatisticMedians() {
        if (!this.gaussianStandardDone) {
            this.gaussianStandardProbabilityPlot(this.title);
        }

        return this.gaussianStandardOrderMedians;
    }

    public double normalStandardGradient() {
        if (!this.gaussianStandardDone) {
            this.gaussianStandardProbabilityPlot(this.title);
        }

        return this.gaussianStandardLine[1];
    }

    public double normalstandardGradientError() {
        if (!this.gaussianStandardDone) {
            this.gaussianStandardProbabilityPlot(this.title);
        }

        return this.gaussianStandardLineErrors[1];
    }

    public double normalStandardInterceptError() {
        if (!this.gaussianStandardDone) {
            this.gaussianStandardProbabilityPlot(this.title);
        }

        return this.gaussianStandardLineErrors[0];
    }

    public double normalStandardCorrelationCoefficient() {
        if (!this.gaussianStandardDone) {
            this.gaussianStandardProbabilityPlot(this.title);
        }

        return this.gaussianStandardCorrCoeff;
    }

    public double normalStandardSumOfSquares() {
        if (!this.gaussianStandardDone) {
            this.gaussianStandardProbabilityPlot(this.title);
        }

        return this.gaussianStandardSumOfSquares;
    }

    public double normalStandardWeightedSumOfSquares() {
        if (!this.gaussianDone) {
            this.gaussianProbabilityPlot(this.title);
        }

        return this.gaussianStandardWeightedSumOfSquares;
    }

    public double[] normalStandardOrderStatisticMedians() {
        if (!this.gaussianDone) {
            this.gaussianStandardProbabilityPlot(this.title);
        }

        return this.gaussianStandardOrderMedians;
    }

    public void logisticUserSuppliedInitialEstimates(double var1, double var3) {
        this.userSuppliedEstimates[8] = true;
        this.userInitialEstimates[8] = new double[2];
        this.userInitialEstimates[8][0] = var1;
        this.userInitialEstimates[8][1] = var3;
    }

    public void removeLogisticUserSuppliedInitialEstimates() {
        this.userSuppliedEstimates[8] = false;
    }

    public void logisticProbabilityPlot() {
        this.lastMethod = 8;
        this.logisticNumberOfParameters = 2;
        if (this.numberOfDataPoints < 3) {
            throw new IllegalArgumentException("There must be at least three fillData points - preferably considerably more");
        } else {
            Regression var1 = new Regression(this.sortedData, this.sortedData, this.sortedWeights);
            if (this.suppressErrorMessages) {
                var1.suppressErrorMessages();
            }

            double[] var2 = new double[2];
            if (this.userSuppliedEstimates[8]) {
                var2[0] = this.userInitialEstimates[8][0];
                var2[1] = this.userInitialEstimates[8][1];
                if (var2[1] <= 0.0D) {
                    var2[0] = this.standardDeviation / 3.0D;
                }
            } else {
                var2[0] = this.mean;
                var2[1] = this.standardDeviation;
            }

            this.initialEstimates = var2;
            double[] var3 = new double[]{this.stepFactor * Math.abs(var2[0]), this.stepFactor * Math.abs(var2[1])};
            if (var3[0] <= 0.0D) {
                var3[0] = this.standardDeviation / 10.0D;
            }

            if (var3[1] <= 0.0D) {
                var3[1] = this.standardDeviation / 10.0D;
            }

            var1.addConstraint(1, -1, 0.0D);
            LogisticProbPlotFunc var4 = new LogisticProbPlotFunc();
            var4.setDataArray(this.numberOfDataPoints);
            var1.simplex(var4, var2, var3, this.tolerance);
            this.logisticParam = var1.getBestEstimates();
            this.logisticParamErrors = var1.getBestEstimatesErrors();
            this.logisticSumOfSquares = var1.getSumOfSquares();
            this.logisticWeightedSumOfSquares = this.logisticSumOfSquares;
            if (this.weighted) {
                this.logisticWeightedSumOfSquares = var1.getChiSquare();
            }

            this.logisticOrderMedians = Stat.logisticOrderStatisticMedians(this.logisticParam[0], this.logisticParam[1], this.numberOfDataPoints);
            Regression var5 = new Regression(this.logisticOrderMedians, this.sortedData, this.sortedWeights);
            if (this.suppressErrorMessages) {
                var5.suppressErrorMessages();
            }

            var5.linear();
            this.logisticLine = var5.getBestEstimates();
            this.logisticLineErrors = var5.getBestEstimatesErrors();
            this.logisticCorrCoeff = var5.getSampleR();
            this.logisticSumOfSquares = var1.getSumOfSquares();
            if (!this.suppressFile) {
                this.outputFile(this.lastMethod, var1, var5, this.logisticNumberOfParameters);
            }

            if (!this.suppressPlot) {
                double[][] var6 = PlotGraph.fillData(2, this.numberOfDataPoints);
                var6[0] = this.logisticOrderMedians;
                var6[1] = this.sortedData;
                var6[2] = this.logisticOrderMedians;

                for(int var7 = 0; var7 < this.numberOfDataPoints; ++var7) {
                    var6[3][var7] = this.logisticLine[0] + this.logisticLine[1] * this.logisticOrderMedians[var7];
                }

                PlotGraph var10 = new PlotGraph(var6);
                int[] var8 = new int[]{4, 0};
                var10.setPoint(var8);
                int[] var9 = new int[]{0, 3};
                var10.setLine(var9);
                var10.setXaxisLegend("Logistic Order Statistic Medians");
                var10.setYaxisLegend("Ordered Data Values");
                var10.setGraphTitle("Logistic probability plot, " + this.title + ":   gradient = " + Fmath.truncate(this.logisticLine[1], 4) + ", intercept = " + Fmath.truncate(this.logisticLine[0], 4) + ",  R = " + Fmath.truncate(this.logisticCorrCoeff, 4));
                var10.setGraphTitle2("  mu = " + Fmath.truncate(this.logisticParam[0], 4) + ", beta = " + Fmath.truncate(this.logisticParam[1], 4));
                var10.plot();
                this.probPlotDone = true;
            }

            this.logisticDone = true;
        }
    }

    public double logisticMu() {
        if (!this.logisticDone) {
            this.logisticProbabilityPlot();
        }

        return this.logisticParam[0];
    }

    public double logisticMuError() {
        if (!this.logisticDone) {
            this.logisticProbabilityPlot();
        }

        return this.logisticParamErrors[0];
    }

    public double logisticBeta() {
        if (!this.logisticDone) {
            this.logisticProbabilityPlot();
        }

        return this.logisticParam[1];
    }

    public double logisticBetaError() {
        if (!this.logisticDone) {
            this.logisticProbabilityPlot();
        }

        return this.logisticParamErrors[1];
    }

    public double[] logisticOrderStatisticMedians() {
        if (!this.logisticDone) {
            this.logisticProbabilityPlot();
        }

        return this.logisticOrderMedians;
    }

    public double logisticGradient() {
        if (!this.logisticDone) {
            this.logisticProbabilityPlot();
        }

        return this.logisticLine[1];
    }

    public double logisticGradientError() {
        if (!this.logisticDone) {
            this.logisticProbabilityPlot();
        }

        return this.logisticLineErrors[1];
    }

    public double logisticIntercept() {
        if (!this.logisticDone) {
            this.logisticProbabilityPlot();
        }

        return this.logisticLine[0];
    }

    public double logisticInterceptError() {
        if (!this.logisticDone) {
            this.logisticProbabilityPlot();
        }

        return this.logisticLineErrors[0];
    }

    public double logisticCorrelationCoefficient() {
        if (!this.logisticDone) {
            this.logisticProbabilityPlot();
        }

        return this.logisticCorrCoeff;
    }

    public double logisticSumOfSquares() {
        if (!this.logisticDone) {
            this.logisticProbabilityPlot();
        }

        return this.logisticSumOfSquares;
    }

    public double logisticWeightedSumOfSquares() {
        if (!this.logisticDone) {
            this.logisticProbabilityPlot();
        }

        return this.logisticWeightedSumOfSquares;
    }

    public void weibullUserSuppliedInitialEstimates(double var1, double var3, double var5) {
        this.userSuppliedEstimates[1] = true;
        this.userInitialEstimates[1] = new double[3];
        this.userInitialEstimates[1][0] = var1;
        this.userInitialEstimates[1][1] = var3;
        this.userInitialEstimates[1][2] = var5;
    }

    public void removeWeibullUserSuppliedInitialEstimates() {
        this.userSuppliedEstimates[1] = false;
    }

    public void weibullProbabilityPlot() {
        this.lastMethod = 1;
        this.weibullNumberOfParameters = 3;
        if (this.numberOfDataPoints < 4) {
            throw new IllegalArgumentException("There must be at least four fillData points - preferably considerably more");
        } else {
            Regression var1 = new Regression(this.sortedData, this.sortedData, this.sortedWeights);
            if (this.suppressErrorMessages) {
                var1.suppressErrorMessages();
            }

            double[] var2 = new double[3];
            if (this.userSuppliedEstimates[1]) {
                var2[0] = this.userInitialEstimates[1][0];
                if (var2[0] > this.minimum) {
                    var2[0] = this.minimum - 0.1D * Math.abs(this.minimum);
                }

                var2[1] = this.userInitialEstimates[1][1];
                if (var2[1] <= 0.0D) {
                    var2[1] = this.range * 0.1D;
                }

                var2[2] = this.userInitialEstimates[1][2];
                if (var2[2] <= 0.0D) {
                    var2[2] = 0.1D;
                }
            } else {
                var2[0] = this.minimum - 0.1D * Math.abs(this.minimum);
                var2[1] = this.peakWidth();
                if (var2[1] <= 0.0D) {
                    var2[1] = this.range * 0.1D;
                }

                var2[2] = 4.0D;
            }

            this.initialEstimates = var2;
            double[] var3 = new double[]{Math.abs(this.stepFactor * var2[0]), Math.abs(this.stepFactor * var2[1]), Math.abs(this.stepFactor * var2[2])};
            if (var3[0] <= 0.0D) {
                var3[0] = this.range * 0.01D;
            }

            if (var3[1] <= 0.0D) {
                var3[1] = this.range * 0.01D;
            }

            if (var3[2] <= 0.0D) {
                var3[2] = 0.01D;
            }

            var1.addConstraint(0, 1, this.minimum);
            var1.addConstraint(1, -1, 0.0D);
            var1.addConstraint(2, -1, 0.0D);
            WeibullProbPlotFunc var4 = new WeibullProbPlotFunc();
            var4.setDataArray(this.numberOfDataPoints);
            var1.simplex(var4, Conv.copy(var2), var3, this.tolerance);
            double[] var5 = var1.getBestEstimates();
            double[] var6 = var1.getBestEstimatesErrors();
            double var7 = var1.getSumOfSquares();
            double var9 = var7;
            double var11 = var7;
            if (this.weighted) {
                var9 = var1.getSumOfWeightedResidualSquares();
                var11 = var9;
            }

            double[] var13 = new double[this.weibullNumberOfParameters];
            var13[0] = 2.0D * var5[0] - var2[0];
            if (var13[0] > this.minimum) {
                var13[0] = this.minimum * (1.0D - Math.abs(this.minimum) * 0.05D);
            }

            var3[0] = Math.abs(var13[0] * 0.1D);
            if (var3[0] == 0.0D) {
                var3[0] = this.range * 0.01D;
            }

            var13[1] = 2.0D * var5[1] - var2[1];
            if (var13[1] <= 0.0D) {
                var13[1] = Math.abs(2.0D * var5[1] - 0.98D * var2[1]);
            }

            var3[1] = Math.abs(var13[1] * 0.1D);
            var13[2] = 2.0D * var5[2] - var2[2];
            if (var13[1] <= 0.0D) {
                var13[2] = Math.abs(2.0D * var5[2] - 0.98D * var2[2]);
            }

            var3[2] = Math.abs(var13[2] * 0.1D);
            var1.simplex(var4, Conv.copy(var13), var3, this.tolerance);
            this.weibullParam = var1.getBestEstimates();
            this.weibullParamErrors = var1.getBestEstimatesErrors();
            this.weibullSumOfSquares = var1.getSumOfSquares();
            this.weibullWeightedSumOfSquares = this.weibullSumOfSquares;
            double var14 = this.weibullSumOfSquares;
            if (this.weighted) {
                this.weibullWeightedSumOfSquares = var1.getChiSquare();
                var14 = this.weibullWeightedSumOfSquares;
            }

            if (var11 < var14) {
                this.weibullParam = var5;
                this.weibullParamErrors = var6;
                this.weibullSumOfSquares = var7;
                if (this.weighted) {
                    this.weibullWeightedSumOfSquares = var9;
                }
            }

            this.weibullOrderMedians = Stat.weibullOrderStatisticMedians(this.weibullParam[0], this.weibullParam[1], this.weibullParam[2], this.numberOfDataPoints);
            Regression var16 = new Regression(this.weibullOrderMedians, this.sortedData, this.sortedWeights);
            if (this.suppressErrorMessages) {
                var16.suppressErrorMessages();
            }

            var16.linear();
            this.weibullLine = var16.getBestEstimates();
            this.weibullLineErrors = var16.getBestEstimatesErrors();
            this.weibullCorrCoeff = var16.getSampleR();
            if (!this.suppressFile) {
                this.outputFile(this.lastMethod, var1, var16, this.weibullNumberOfParameters);
            }

            if (!this.suppressPlot) {
                double[][] var17 = PlotGraph.fillData(2, this.numberOfDataPoints);
                var17[0] = this.weibullOrderMedians;
                var17[1] = this.sortedData;
                var17[2] = this.weibullOrderMedians;

                for(int var18 = 0; var18 < this.numberOfDataPoints; ++var18) {
                    var17[3][var18] = this.weibullLine[0] + this.weibullLine[1] * this.weibullOrderMedians[var18];
                }

                PlotGraph var21 = new PlotGraph(var17);
                int[] var19 = new int[]{4, 0};
                var21.setPoint(var19);
                int[] var20 = new int[]{0, 3};
                var21.setLine(var20);
                var21.setXaxisLegend("Weibull Order Statistic Medians");
                var21.setYaxisLegend("Ordered Data Values");
                var21.setGraphTitle("Weibull probability plot, " + this.title + ":   gradient = " + Fmath.truncate(this.weibullLine[1], 4) + ", intercept = " + Fmath.truncate(this.weibullLine[0], 4) + ",  R = " + Fmath.truncate(this.weibullCorrCoeff, 4));
                var21.setGraphTitle2("  mu = " + Fmath.truncate(this.weibullParam[0], 4) + ", sigma = " + Fmath.truncate(this.weibullParam[1], 4) + ", gamma = " + Fmath.truncate(this.weibullParam[2], 4));
                var21.plot();
                this.probPlotDone = true;
            }

            this.weibullDone = true;
        }
    }

    public void weibullThreeParProbabilityPlot() {
        this.weibullProbabilityPlot();
    }

    public double weibullMu() {
        if (!this.weibullDone) {
            this.weibullProbabilityPlot();
        }

        return this.weibullParam[0];
    }

    public double weibullMuError() {
        if (!this.weibullDone) {
            this.weibullProbabilityPlot();
        }

        return this.weibullParamErrors[0];
    }

    public double weibullSigma() {
        if (!this.weibullDone) {
            this.weibullProbabilityPlot();
        }

        return this.weibullParam[1];
    }

    public double weibullSigmaError() {
        if (!this.weibullDone) {
            this.weibullProbabilityPlot();
        }

        return this.weibullParamErrors[1];
    }

    public double weibullGamma() {
        if (!this.weibullDone) {
            this.weibullProbabilityPlot();
        }

        return this.weibullParam[2];
    }

    public double weibullGammaError() {
        if (!this.weibullDone) {
            this.weibullProbabilityPlot();
        }

        return this.weibullParamErrors[2];
    }

    public double[] weibullOrderStatisticMedians() {
        if (!this.weibullDone) {
            this.weibullProbabilityPlot();
        }

        return this.weibullOrderMedians;
    }

    public double weibullGradient() {
        if (!this.weibullDone) {
            this.weibullProbabilityPlot();
        }

        return this.weibullLine[1];
    }

    public double weibullGradientError() {
        if (!this.weibullDone) {
            this.weibullProbabilityPlot();
        }

        return this.weibullLineErrors[1];
    }

    public double weibullIntercept() {
        if (!this.weibullDone) {
            this.weibullProbabilityPlot();
        }

        return this.weibullLine[0];
    }

    public double weibullInterceptError() {
        if (!this.weibullDone) {
            this.weibullProbabilityPlot();
        }

        return this.weibullLineErrors[0];
    }

    public double weibullCorrelationCoefficient() {
        if (!this.weibullDone) {
            this.weibullProbabilityPlot();
        }

        return this.weibullCorrCoeff;
    }

    public double weibullSumOfSquares() {
        if (!this.weibullDone) {
            this.weibullProbabilityPlot();
        }

        return this.weibullSumOfSquares;
    }

    public double weibullWeightedSumOfSquares() {
        if (!this.weibullDone) {
            this.weibullProbabilityPlot();
        }

        return this.weibullWeightedSumOfSquares;
    }

    public void weibullTwoParUserSuppliedInitialEstimates(double var1, double var3) {
        this.userSuppliedEstimates[12] = true;
        this.userInitialEstimates[12] = new double[2];
        this.userInitialEstimates[12][0] = var1;
        this.userInitialEstimates[1][1] = var3;
    }

    public void removeWeibullTwoParUserSuppliedInitialEstimates() {
        this.userSuppliedEstimates[12] = false;
    }

    public void weibullTwoParProbabilityPlot() {
        this.lastMethod = 12;
        if (this.sortedData[0] < 0.0D) {
            System.out.println("Method weibullTwoParProbabilityPlot: negative x value found - weibullThreeParProbabilityPlot called");
            this.weibullThreeParProbabilityPlot();
        }

        this.weibullTwoParNumberOfParameters = 2;
        if (this.numberOfDataPoints < 3) {
            throw new IllegalArgumentException("There must be at least three fillData points - preferably considerably more");
        } else {
            Regression var1 = new Regression(this.sortedData, this.sortedData, this.sortedWeights);
            if (this.suppressErrorMessages) {
                var1.suppressErrorMessages();
            }

            double[] var2 = new double[2];
            if (this.userSuppliedEstimates[12]) {
                var2[0] = this.userInitialEstimates[12][0];
                if (var2[0] <= 0.0D) {
                    var2[0] = this.range * 0.01D;
                }

                var2[1] = this.userInitialEstimates[12][1];
                if (var2[1] <= 0.0D) {
                    var2[1] = 1.0D;
                }
            } else {
                var2[0] = this.peakWidth();
                var2[1] = 4.0D;
            }

            this.initialEstimates = var2;
            double[] var3 = new double[]{Math.abs(this.stepFactor * var2[0]), Math.abs(this.stepFactor * var2[1])};
            if (var3[0] <= 0.0D) {
                var3[0] = this.range * 0.01D;
            }

            var1.addConstraint(0, -1, 0.0D);
            var1.addConstraint(1, -1, 0.0D);
            WeibullTwoParProbPlotFunc var4 = new WeibullTwoParProbPlotFunc();
            var4.setDataArray(this.numberOfDataPoints);
            var1.simplex(var4, Conv.copy(var2), var3, this.tolerance);
            double[] var5 = var1.getBestEstimates();
            double[] var6 = var1.getBestEstimatesErrors();
            double var7 = var1.getSumOfSquares();
            double var9 = var7;
            double var11 = var7;
            if (this.weighted) {
                var9 = var1.getSumOfWeightedResidualSquares();
                var11 = var9;
            }

            double[] var13 = new double[this.weibullTwoParNumberOfParameters];
            var13[0] = 2.0D * var5[0] - var2[0];
            if (var13[0] <= 0.0D) {
                var13[0] = Math.abs(2.0D * var5[0] - 0.98D * var2[0]);
            }

            var3[0] = Math.abs(var13[0] * 0.1D);
            if (var3[0] <= 0.0D) {
                var3[0] = this.range * 0.01D;
            }

            var13[1] = 2.0D * var5[1] - var2[1];
            if (var13[1] <= 0.0D) {
                var13[1] = Math.abs(2.0D * var5[1] - 0.98D * var2[1]);
            }

            var3[1] = Math.abs(var13[1] * 0.1D);
            var1.simplex(var4, Conv.copy(var13), var3, this.tolerance);
            this.weibullTwoParParam = var1.getBestEstimates();
            this.weibullTwoParParamErrors = var1.getBestEstimatesErrors();
            this.weibullTwoParSumOfSquares = var1.getSumOfSquares();
            this.weibullTwoParWeightedSumOfSquares = this.weibullTwoParSumOfSquares;
            double var14 = this.weibullTwoParSumOfSquares;
            if (this.weighted) {
                this.weibullTwoParWeightedSumOfSquares = var1.getChiSquare();
                var14 = this.weibullWeightedSumOfSquares;
            }

            if (var11 < var14) {
                this.weibullTwoParParam = var5;
                this.weibullTwoParParamErrors = var6;
                this.weibullTwoParSumOfSquares = var7;
                if (this.weighted) {
                    this.weibullTwoParWeightedSumOfSquares = var9;
                }
            }

            this.weibullTwoParOrderMedians = Stat.weibullOrderStatisticMedians(this.weibullTwoParParam[0], this.weibullTwoParParam[1], this.numberOfDataPoints);
            Regression var16 = new Regression(this.weibullTwoParOrderMedians, this.sortedData, this.sortedWeights);
            if (this.suppressErrorMessages) {
                var16.suppressErrorMessages();
            }

            var16.linear();
            this.weibullTwoParLine = var16.getBestEstimates();
            this.weibullTwoParLineErrors = var16.getBestEstimatesErrors();
            this.weibullTwoParCorrCoeff = var16.getSampleR();
            if (!this.suppressFile) {
                this.outputFile(this.lastMethod, var1, var16, this.weibullTwoParNumberOfParameters);
            }

            if (!this.suppressPlot) {
                double[][] var17 = PlotGraph.fillData(2, this.numberOfDataPoints);
                var17[0] = this.weibullTwoParOrderMedians;
                var17[1] = this.sortedData;
                var17[2] = this.weibullTwoParOrderMedians;

                for(int var18 = 0; var18 < this.numberOfDataPoints; ++var18) {
                    var17[3][var18] = this.weibullTwoParLine[0] + this.weibullTwoParLine[1] * this.weibullTwoParOrderMedians[var18];
                }

                PlotGraph var21 = new PlotGraph(var17);
                int[] var19 = new int[]{4, 0};
                var21.setPoint(var19);
                int[] var20 = new int[]{0, 3};
                var21.setLine(var20);
                var21.setXaxisLegend("Weibull Order Statistic Medians");
                var21.setYaxisLegend("Ordered Data Values");
                var21.setGraphTitle("Two Parameter Weibull probability plot, " + this.title + ":   gradient = " + Fmath.truncate(this.weibullTwoParLine[1], 4) + ", intercept = " + Fmath.truncate(this.weibullTwoParLine[0], 4) + ",  R = " + Fmath.truncate(this.weibullTwoParCorrCoeff, 4));
                var21.setGraphTitle2("  mu = 0, sigma = " + Fmath.truncate(this.weibullTwoParParam[0], 4) + ", gamma = " + Fmath.truncate(this.weibullTwoParParam[1], 4));
                var21.plot();
                this.probPlotDone = true;
            }

            this.weibullTwoParDone = true;
        }
    }

    public double weibullTwoParSigma() {
        if (!this.weibullTwoParDone) {
            this.weibullTwoParProbabilityPlot();
        }

        return this.weibullTwoParParam[0];
    }

    public double weibullTwoParSigmaError() {
        if (!this.weibullTwoParDone) {
            this.weibullTwoParProbabilityPlot();
        }

        return this.weibullTwoParParamErrors[0];
    }

    public double weibullTwoParGamma() {
        if (!this.weibullTwoParDone) {
            this.weibullTwoParProbabilityPlot();
        }

        return this.weibullTwoParParam[1];
    }

    public double weibullTwoParGammaError() {
        if (!this.weibullTwoParDone) {
            this.weibullTwoParProbabilityPlot();
        }

        return this.weibullTwoParParamErrors[1];
    }

    public double[] weibullTwoParOrderStatisticMedians() {
        if (!this.weibullTwoParDone) {
            this.weibullTwoParProbabilityPlot();
        }

        return this.weibullTwoParOrderMedians;
    }

    public double weibullTwoParGradient() {
        if (!this.weibullTwoParDone) {
            this.weibullTwoParProbabilityPlot();
        }

        return this.weibullTwoParLine[1];
    }

    public double weibullTwoParGradientError() {
        if (!this.weibullTwoParDone) {
            this.weibullTwoParProbabilityPlot();
        }

        return this.weibullTwoParLineErrors[1];
    }

    public double weibullTwoParIntercept() {
        if (!this.weibullTwoParDone) {
            this.weibullTwoParProbabilityPlot();
        }

        return this.weibullTwoParLine[0];
    }

    public double weibullTwoParInterceptError() {
        if (!this.weibullTwoParDone) {
            this.weibullTwoParProbabilityPlot();
        }

        return this.weibullTwoParLineErrors[0];
    }

    public double weibullTwoParCorrelationCoefficient() {
        if (!this.weibullTwoParDone) {
            this.weibullTwoParProbabilityPlot();
        }

        return this.weibullTwoParCorrCoeff;
    }

    public double weibullTwoParSumOfSquares() {
        if (!this.weibullTwoParDone) {
            this.weibullTwoParProbabilityPlot();
        }

        return this.weibullTwoParSumOfSquares;
    }

    public double weibullTwoParWeightedSumOfSquares() {
        if (!this.weibullTwoParDone) {
            this.weibullTwoParProbabilityPlot();
        }

        return this.weibullTwoParWeightedSumOfSquares;
    }

    public void weibullStandardUserSuppliedInitialEstimates(double var1) {
        this.userSuppliedEstimates[13] = true;
        this.userInitialEstimates[13] = new double[1];
        this.userInitialEstimates[13][0] = var1;
    }

    public void removeWeibullStandardUserSuppliedInitialEstimates() {
        this.userSuppliedEstimates[13] = false;
    }

    public void weibullStandardProbabilityPlot() {
        this.lastMethod = 13;
        if (this.sortedData[0] < 0.0D) {
            System.out.println("Method weibullStandardProbabilityPlot: negative x value found - weibullThreeParProbabilityPlot called");
            this.weibullThreeParProbabilityPlot();
        }

        this.weibullStandardNumberOfParameters = 1;
        if (this.numberOfDataPoints < 3) {
            throw new IllegalArgumentException("There must be at least three fillData points - preferably considerably more");
        } else {
            Regression var1 = new Regression(this.sortedData, this.sortedData, this.sortedWeights);
            if (this.suppressErrorMessages) {
                var1.suppressErrorMessages();
            }

            double[] var2 = new double[1];
            if (this.userSuppliedEstimates[13]) {
                var2[0] = this.userInitialEstimates[13][0];
                if (var2[0] <= 0.0D) {
                    var2[0] = 1.0D;
                }
            } else {
                var2[0] = 4.0D;
            }

            double[] var3 = new double[]{Math.abs(this.stepFactor * var2[0])};
            this.initialEstimates = var2;
            var1.addConstraint(0, -1, 0.0D);
            WeibullStandardProbPlotFunc var4 = new WeibullStandardProbPlotFunc();
            var4.setDataArray(this.numberOfDataPoints);
            var1.simplex(var4, Conv.copy(var2), var3, this.tolerance);
            double[] var5 = var1.getBestEstimates();
            double[] var6 = var1.getBestEstimatesErrors();
            double var7 = var1.getSumOfSquares();
            double var9 = var7;
            double var11 = var7;
            if (this.weighted) {
                var9 = var1.getSumOfWeightedResidualSquares();
                var11 = var9;
            }

            double[] var13 = new double[this.weibullStandardNumberOfParameters];
            var13[0] = 2.0D * var5[0] - var2[0];
            if (var13[0] <= 0.0D) {
                var13[0] = Math.abs(2.0D * var5[0] - 0.98D * var2[0]);
            }

            var3[0] = Math.abs(var13[0] * 0.1D);
            if (var3[0] == 0.0D) {
                var3[0] = this.range * 0.01D;
            }

            var1.simplex(var4, Conv.copy(var13), var3, this.tolerance);
            this.weibullStandardParam = var1.getBestEstimates();
            this.weibullStandardParamErrors = var1.getBestEstimatesErrors();
            this.weibullStandardSumOfSquares = var1.getSumOfSquares();
            this.weibullStandardWeightedSumOfSquares = this.weibullStandardSumOfSquares;
            double var14 = this.weibullStandardSumOfSquares;
            if (this.weighted) {
                this.weibullStandardWeightedSumOfSquares = var1.getChiSquare();
                var14 = this.weibullStandardWeightedSumOfSquares;
            }

            if (var11 < var14) {
                this.weibullStandardParam = var5;
                this.weibullStandardParamErrors = var6;
                this.weibullStandardSumOfSquares = var7;
                if (this.weighted) {
                    this.weibullStandardWeightedSumOfSquares = var9;
                }
            }

            this.weibullStandardOrderMedians = Stat.weibullOrderStatisticMedians(this.weibullStandardParam[0], this.numberOfDataPoints);
            Regression var16 = new Regression(this.weibullStandardOrderMedians, this.sortedData, this.sortedWeights);
            if (this.suppressErrorMessages) {
                var16.suppressErrorMessages();
            }

            var16.linear();
            this.weibullStandardLine = var16.getBestEstimates();
            this.weibullStandardLineErrors = var16.getBestEstimatesErrors();
            this.weibullStandardCorrCoeff = var16.getSampleR();
            if (!this.suppressFile) {
                this.outputFile(this.lastMethod, var1, var16, this.weibullStandardNumberOfParameters);
            }

            if (!this.suppressPlot) {
                double[][] var17 = PlotGraph.fillData(2, this.numberOfDataPoints);
                var17[0] = this.weibullStandardOrderMedians;
                var17[1] = this.sortedData;
                var17[2] = this.weibullStandardOrderMedians;

                for(int var18 = 0; var18 < this.numberOfDataPoints; ++var18) {
                    var17[3][var18] = this.weibullStandardLine[0] + this.weibullStandardLine[1] * this.weibullStandardOrderMedians[var18];
                }

                PlotGraph var21 = new PlotGraph(var17);
                int[] var19 = new int[]{4, 0};
                var21.setPoint(var19);
                int[] var20 = new int[]{0, 3};
                var21.setLine(var20);
                var21.setXaxisLegend("Weibull Order Statistic Medians");
                var21.setYaxisLegend("Ordered Data Values");
                var21.setGraphTitle("Standard Weibull probability plot, " + this.title + ":   gradient = " + Fmath.truncate(this.weibullStandardLine[1], 4) + ", intercept = " + Fmath.truncate(this.weibullStandardLine[0], 4) + ",  R = " + Fmath.truncate(this.weibullStandardCorrCoeff, 4));
                var21.setGraphTitle2("  mu = 0, sigma = 1, gamma = " + Fmath.truncate(this.weibullStandardParam[0], 4));
                var21.plot();
                this.probPlotDone = true;
            }

            this.weibullStandardDone = true;
        }
    }

    public void weibullOneParProbabilityPlot() {
        this.weibullStandardProbabilityPlot();
    }

    public double weibullStandardGamma() {
        if (!this.weibullStandardDone) {
            this.weibullStandardProbabilityPlot();
        }

        return this.weibullStandardParam[0];
    }

    public double weibullStandardGammaError() {
        if (!this.weibullStandardDone) {
            this.weibullStandardProbabilityPlot();
        }

        return this.weibullStandardParamErrors[0];
    }

    public double[] weibullStandardOrderStatisticMedians() {
        if (!this.weibullStandardDone) {
            this.weibullStandardProbabilityPlot();
        }

        return this.weibullStandardOrderMedians;
    }

    public double weibullStandardGradient() {
        if (!this.weibullStandardDone) {
            this.weibullStandardProbabilityPlot();
        }

        return this.weibullStandardLine[1];
    }

    public double weibullStandardGradientError() {
        if (!this.weibullStandardDone) {
            this.weibullStandardProbabilityPlot();
        }

        return this.weibullStandardLineErrors[1];
    }

    public double weibullStandardIntercept() {
        if (!this.weibullStandardDone) {
            this.weibullStandardProbabilityPlot();
        }

        return this.weibullStandardLine[0];
    }

    public double weibullStandardInterceptError() {
        if (!this.weibullStandardDone) {
            this.weibullStandardProbabilityPlot();
        }

        return this.weibullStandardLineErrors[0];
    }

    public double weibullStandardCorrelationCoefficient() {
        if (!this.weibullStandardDone) {
            this.weibullStandardProbabilityPlot();
        }

        return this.weibullStandardCorrCoeff;
    }

    public double weibullStandardSumOfSquares() {
        if (!this.weibullStandardDone) {
            this.weibullStandardProbabilityPlot();
        }

        return this.weibullStandardSumOfSquares;
    }

    public double weibullStandardWeightedSumOfSquares() {
        if (!this.weibullStandardDone) {
            this.weibullStandardProbabilityPlot();
        }

        return this.weibullStandardWeightedSumOfSquares;
    }

    public void exponentialUserSuppliedInitialEstimates(double var1, double var3) {
        this.userSuppliedEstimates[2] = true;
        this.userInitialEstimates[2] = new double[2];
        this.userInitialEstimates[2][0] = var1;
        this.userInitialEstimates[2][1] = var3;
    }

    public void exponentialUserSuppliedInitialEstimates() {
        this.userSuppliedEstimates[2] = false;
    }

    public void exponentialProbabilityPlot() {
        this.lastMethod = 2;
        this.exponentialNumberOfParameters = 2;
        if (this.numberOfDataPoints < 3) {
            throw new IllegalArgumentException("There must be at least three fillData points - preferably considerably more");
        } else {
            Regression var1 = new Regression(this.sortedData, this.sortedData, this.sortedWeights);
            if (this.suppressErrorMessages) {
                var1.suppressErrorMessages();
            }

            double[] var2 = new double[2];
            if (this.userSuppliedEstimates[2]) {
                var2[0] = this.userInitialEstimates[2][0];
                var2[1] = this.userInitialEstimates[2][1];
            } else {
                var2[0] = this.minimum;
                if (var2[0] == 0.0D) {
                    var2[0] = this.standardDeviation / 3.0D;
                }

                var2[1] = this.standardDeviation;
            }

            this.initialEstimates = var2;
            double[] var3 = new double[]{this.stepFactor * var2[0], this.stepFactor * var2[1]};
            if (var3[0] <= 0.0D) {
                var3[0] = this.standardDeviation / 30.0D;
            }

            var1.addConstraint(1, -1, 0.0D);
            ExponentialProbPlotFunc var4 = new ExponentialProbPlotFunc();
            var4.setDataArray(this.numberOfDataPoints);
            var1.simplex(var4, var2, var3, this.tolerance);
            this.exponentialParam = var1.getBestEstimates();
            this.exponentialParamErrors = var1.getBestEstimatesErrors();
            this.exponentialSumOfSquares = var1.getSumOfSquares();
            this.exponentialWeightedSumOfSquares = this.exponentialSumOfSquares;
            if (this.weighted) {
                this.exponentialWeightedSumOfSquares = var1.getChiSquare();
            }

            this.exponentialOrderMedians = Stat.weibullOrderStatisticMedians(this.exponentialParam[0], this.exponentialParam[1], 1.0D, this.numberOfDataPoints);
            Regression var5 = new Regression(this.exponentialOrderMedians, this.sortedData, this.sortedWeights);
            if (this.suppressErrorMessages) {
                var5.suppressErrorMessages();
            }

            var5.linear();
            this.exponentialLine = var5.getBestEstimates();
            this.exponentialLineErrors = var5.getBestEstimatesErrors();
            this.exponentialCorrCoeff = var5.getSampleR();
            if (!this.suppressFile) {
                this.outputFile(this.lastMethod, var1, var5, this.exponentialNumberOfParameters);
            }

            if (!this.suppressPlot) {
                double[][] var6 = PlotGraph.fillData(2, this.numberOfDataPoints);
                var6[0] = this.exponentialOrderMedians;
                var6[1] = this.sortedData;
                var6[2] = this.exponentialOrderMedians;

                for(int var7 = 0; var7 < this.numberOfDataPoints; ++var7) {
                    var6[3][var7] = this.exponentialLine[0] + this.exponentialLine[1] * this.exponentialOrderMedians[var7];
                }

                PlotGraph var10 = new PlotGraph(var6);
                int[] var8 = new int[]{4, 0};
                var10.setPoint(var8);
                int[] var9 = new int[]{0, 3};
                var10.setLine(var9);
                var10.setXaxisLegend("Exponential Order Statistic Medians");
                var10.setYaxisLegend("Ordered Data Values");
                var10.setGraphTitle("Exponential probability plot, " + this.title + ":   gradient = " + Fmath.truncate(this.exponentialLine[1], 4) + ", intercept = " + Fmath.truncate(this.exponentialLine[0], 4) + ",  R = " + Fmath.truncate(this.exponentialCorrCoeff, 4));
                var10.setGraphTitle2("  mu = " + Fmath.truncate(this.exponentialParam[0], 4) + ", sigma = " + Fmath.truncate(this.exponentialParam[1], 4));
                var10.plot();
                this.probPlotDone = true;
            }

            this.exponentialDone = true;
        }
    }

    public double exponentialMu() {
        if (!this.exponentialDone) {
            this.exponentialProbabilityPlot();
        }

        return this.exponentialParam[0];
    }

    public double exponentialMuError() {
        if (!this.exponentialDone) {
            this.exponentialProbabilityPlot();
        }

        return this.exponentialParamErrors[0];
    }

    public double exponentialSigma() {
        if (!this.exponentialDone) {
            this.exponentialProbabilityPlot();
        }

        return this.exponentialParam[1];
    }

    public double exponentialSigmaError() {
        if (!this.exponentialDone) {
            this.exponentialProbabilityPlot();
        }

        return this.exponentialParamErrors[1];
    }

    public double[] exponentialOrderStatisticMedians() {
        if (!this.exponentialDone) {
            this.exponentialProbabilityPlot();
        }

        return this.exponentialOrderMedians;
    }

    public double exponentialGradient() {
        if (!this.exponentialDone) {
            this.exponentialProbabilityPlot();
        }

        return this.exponentialLine[1];
    }

    public double exponentialGradientError() {
        if (!this.exponentialDone) {
            this.exponentialProbabilityPlot();
        }

        return this.exponentialLineErrors[1];
    }

    public double exponentialIntercept() {
        if (!this.exponentialDone) {
            this.exponentialProbabilityPlot();
        }

        return this.exponentialLine[0];
    }

    public double exponentialInterceptError() {
        if (!this.exponentialDone) {
            this.exponentialProbabilityPlot();
        }

        return this.exponentialLineErrors[0];
    }

    public double exponentialCorrelationCoefficient() {
        if (!this.exponentialDone) {
            this.exponentialProbabilityPlot();
        }

        return this.exponentialCorrCoeff;
    }

    public double exponentialSumOfSquaresExponential() {
        if (!this.exponentialDone) {
            this.exponentialProbabilityPlot();
        }

        return this.exponentialSumOfSquares;
    }

    public double exponentialWeightedSumOfSquares() {
        if (!this.exponentialDone) {
            this.exponentialProbabilityPlot();
        }

        return this.exponentialWeightedSumOfSquares;
    }

    public void frechetUserSuppliedInitialEstimates(double var1, double var3, double var5) {
        this.userSuppliedEstimates[7] = true;
        this.userInitialEstimates[7] = new double[3];
        this.userInitialEstimates[7][0] = var1;
        this.userInitialEstimates[7][1] = var3;
        this.userInitialEstimates[7][2] = var5;
    }

    public void removeFrechetUserSuppliedInitialEstimates() {
        this.userSuppliedEstimates[7] = false;
    }

    public void frechetProbabilityPlot() {
        this.lastMethod = 7;
        this.frechetNumberOfParameters = 3;
        if (this.numberOfDataPoints < 4) {
            throw new IllegalArgumentException("There must be at least four fillData points - preferably considerably more");
        } else {
            Regression var1 = new Regression(this.sortedData, this.sortedData, this.sortedWeights);
            if (this.suppressErrorMessages) {
                var1.suppressErrorMessages();
            }

            double[] var2 = new double[3];
            if (this.userSuppliedEstimates[7]) {
                var2[0] = this.userInitialEstimates[7][0];
                if (var2[0] > this.minimum) {
                    var2[0] = this.minimum - 0.1D * Math.abs(this.minimum);
                }

                var2[1] = this.userInitialEstimates[7][1];
                if (var2[1] <= 0.0D) {
                    var2[1] = 0.001D * this.range;
                }

                var2[2] = this.userInitialEstimates[7][2];
                if (var2[2] < 0.0D) {
                    var2[2] = 1.0E-4D;
                }
            } else {
                var2[0] = this.minimum - 0.1D * Math.abs(this.minimum);
                var2[1] = this.peakWidth();
                if (var2[1] <= 0.0D) {
                    var2[1] = 0.001D * this.range;
                }

                var2[2] = 4.0D;
            }

            this.initialEstimates = var2;
            double[] var3 = new double[]{Math.abs(this.stepFactor * var2[0]), Math.abs(this.stepFactor * var2[1]), Math.abs(this.stepFactor * var2[2])};
            if (var3[0] == 0.0D) {
                var3[0] = this.range * 0.01D;
            }

            var1.addConstraint(0, 1, this.minimum);
            var1.addConstraint(1, -1, 0.0D);
            var1.addConstraint(2, -1, 0.0D);
            FrechetProbPlotFunc var4 = new FrechetProbPlotFunc();
            var4.setDataArray(this.numberOfDataPoints);
            var1.simplex(var4, Conv.copy(var2), var3, this.tolerance);
            double[] var5 = var1.getBestEstimates();
            double[] var6 = var1.getBestEstimatesErrors();
            double var7 = var1.getSumOfSquares();
            double var9 = var7;
            double var11 = var7;
            if (this.weighted) {
                var9 = var1.getSumOfWeightedResidualSquares();
                var11 = var9;
            }

            double[] var13 = new double[this.frechetNumberOfParameters];
            var13[0] = 2.0D * var5[0] - var2[0];
            if (var13[0] > this.minimum) {
                var13[0] = this.minimum * (1.0D - Math.abs(this.minimum) * 0.05D);
            }

            var3[0] = Math.abs(var13[0] * 0.1D);
            if (var3[0] == 0.0D) {
                var3[0] = this.range * 0.01D;
            }

            var13[1] = 2.0D * var5[1] - var2[1];
            if (var13[1] <= 0.0D) {
                var13[1] = Math.abs(2.0D * var5[1] - 0.98D * var2[1]);
            }

            var3[1] = Math.abs(var13[1] * 0.1D);
            var13[2] = 2.0D * var5[2] - var2[2];
            if (var13[2] <= 0.0D) {
                var13[2] = Math.abs(2.0D * var5[2] - 0.98D * var2[2]);
            }

            var3[2] = Math.abs(var13[2] * 0.1D);
            var1.simplex(var4, Conv.copy(var13), var3, this.tolerance);
            this.frechetParam = var1.getBestEstimates();
            this.frechetParamErrors = var1.getBestEstimatesErrors();
            this.frechetSumOfSquares = var1.getSumOfSquares();
            this.frechetWeightedSumOfSquares = this.frechetSumOfSquares;
            double var14 = this.frechetSumOfSquares;
            if (this.weighted) {
                this.frechetWeightedSumOfSquares = var1.getChiSquare();
                var14 = this.frechetWeightedSumOfSquares;
            }

            if (var11 < var14) {
                this.frechetParam = var5;
                this.frechetParamErrors = var6;
                this.frechetSumOfSquares = var7;
                if (this.weighted) {
                    this.frechetWeightedSumOfSquares = var9;
                }
            }

            this.frechetOrderMedians = Stat.frechetOrderStatisticMedians(this.frechetParam[0], this.frechetParam[1], this.frechetParam[2], this.numberOfDataPoints);
            Regression var16 = new Regression(this.frechetOrderMedians, this.sortedData, this.sortedWeights);
            if (this.suppressErrorMessages) {
                var16.suppressErrorMessages();
            }

            var16.linear();
            this.frechetLine = var16.getBestEstimates();
            this.frechetLineErrors = var16.getBestEstimatesErrors();
            this.frechetCorrCoeff = var16.getSampleR();
            this.gaussianSumOfSquares = var1.getSumOfSquares();
            if (!this.suppressFile) {
                this.outputFile(this.lastMethod, var1, var16, this.frechetNumberOfParameters);
            }

            if (!this.suppressPlot) {
                double[][] var17 = PlotGraph.fillData(2, this.numberOfDataPoints);
                var17[0] = this.frechetOrderMedians;
                var17[1] = this.sortedData;
                var17[2] = this.frechetOrderMedians;

                for(int var18 = 0; var18 < this.numberOfDataPoints; ++var18) {
                    var17[3][var18] = this.frechetLine[0] + this.frechetLine[1] * this.frechetOrderMedians[var18];
                }

                PlotGraph var21 = new PlotGraph(var17);
                int[] var19 = new int[]{4, 0};
                var21.setPoint(var19);
                int[] var20 = new int[]{0, 3};
                var21.setLine(var20);
                var21.setXaxisLegend("Frechet Order Statistic Medians");
                var21.setYaxisLegend("Ordered Data Values");
                var21.setGraphTitle("Frechet probability plot, " + this.title + ":   gradient = " + Fmath.truncate(this.frechetLine[1], 4) + ", intercept = " + Fmath.truncate(this.frechetLine[0], 4) + ",  R = " + Fmath.truncate(this.frechetCorrCoeff, 4));
                var21.setGraphTitle2("  mu = " + Fmath.truncate(this.frechetParam[0], 4) + ", sigma = " + Fmath.truncate(this.frechetParam[1], 4) + ", gamma = " + Fmath.truncate(this.frechetParam[2], 4));
                var21.plot();
                this.probPlotDone = true;
            }

            this.frechetDone = true;
        }
    }

    public double frechetMu() {
        if (!this.frechetDone) {
            this.frechetProbabilityPlot();
        }

        return this.frechetParam[0];
    }

    public double frechetMuError() {
        if (!this.frechetDone) {
            this.frechetProbabilityPlot();
        }

        return this.frechetParamErrors[0];
    }

    public double frechetSigma() {
        if (!this.frechetDone) {
            this.frechetProbabilityPlot();
        }

        return this.frechetParam[1];
    }

    public double frechetSigmaError() {
        if (!this.frechetDone) {
            this.frechetProbabilityPlot();
        }

        return this.frechetParamErrors[1];
    }

    public double frechetGamma() {
        if (!this.frechetDone) {
            this.frechetProbabilityPlot();
        }

        return this.frechetParam[2];
    }

    public double frechetGammaError() {
        if (!this.frechetDone) {
            this.frechetProbabilityPlot();
        }

        return this.frechetParamErrors[2];
    }

    public double[] frechetOrderStatisticMedians() {
        if (!this.frechetDone) {
            this.frechetProbabilityPlot();
        }

        return this.frechetOrderMedians;
    }

    public double frechetGradient() {
        if (!this.frechetDone) {
            this.frechetProbabilityPlot();
        }

        return this.frechetLine[1];
    }

    public double frechetGradientError() {
        if (!this.frechetDone) {
            this.frechetProbabilityPlot();
        }

        return this.frechetLineErrors[1];
    }

    public double frechetIntercept() {
        if (!this.frechetDone) {
            this.frechetProbabilityPlot();
        }

        return this.frechetLine[0];
    }

    public double frechetInterceptError() {
        if (!this.frechetDone) {
            this.frechetProbabilityPlot();
        }

        return this.frechetLineErrors[0];
    }

    public double frechetCorrelationCoefficient() {
        if (!this.frechetDone) {
            this.frechetProbabilityPlot();
        }

        return this.frechetCorrCoeff;
    }

    public double frechetSumOfSquares() {
        if (!this.frechetDone) {
            this.frechetProbabilityPlot();
        }

        return this.frechetSumOfSquares;
    }

    public double frechetWeightedSumOfSquares() {
        if (!this.frechetDone) {
            this.frechetProbabilityPlot();
        }

        return this.frechetWeightedSumOfSquares;
    }

    public void frechetThreeParProbabilityPlot() {
        this.frechetProbabilityPlot();
    }

    public void frechetTwoParUserSuppliedInitialEstimates(double var1, double var3) {
        this.userSuppliedEstimates[16] = true;
        this.userInitialEstimates[16] = new double[2];
        this.userInitialEstimates[16][0] = var1;
        this.userInitialEstimates[16][1] = var3;
    }

    public void removeFrechetTwoParUserSuppliedInitialEstimates() {
        this.userSuppliedEstimates[16] = false;
    }

    public void frechetTwoParProbabilityPlot() {
        this.lastMethod = 16;
        this.frechetTwoParNumberOfParameters = 2;
        if (this.numberOfDataPoints < 3) {
            throw new IllegalArgumentException("There must be at least three fillData points - preferably considerably more");
        } else {
            Regression var1 = new Regression(this.sortedData, this.sortedData, this.sortedWeights);
            if (this.suppressErrorMessages) {
                var1.suppressErrorMessages();
            }

            double[] var2 = new double[2];
            if (this.userSuppliedEstimates[16]) {
                var2[0] = this.userInitialEstimates[16][0];
                var2[1] = this.userInitialEstimates[16][1];
            } else {
                var2[0] = this.peakWidth();
                var2[1] = 4.0D;
            }

            if (var2[0] <= 0.0D) {
                var2[0] = this.range / 100.0D;
            }

            if (var2[1] <= 0.0D) {
                var2[1] = 1.0D;
            }

            this.initialEstimates = var2;
            double[] var3 = new double[]{Math.abs(this.stepFactor * var2[0]), Math.abs(this.stepFactor * var2[1])};
            var1.addConstraint(0, -1, 0.0D);
            var1.addConstraint(1, -1, 0.0D);
            FrechetTwoParProbPlotFunc var4 = new FrechetTwoParProbPlotFunc();
            var4.setDataArray(this.numberOfDataPoints);
            var1.simplex(var4, Conv.copy(var2), var3, this.tolerance);
            double[] var5 = var1.getBestEstimates();
            double[] var6 = var1.getBestEstimatesErrors();
            double var7 = var1.getSumOfSquares();
            double var9 = var7;
            double var11 = var7;
            if (this.weighted) {
                var9 = var1.getSumOfWeightedResidualSquares();
                var11 = var9;
            }

            double[] var13 = new double[this.frechetTwoParNumberOfParameters];
            var13[0] = 2.0D * var5[0] - var2[0];
            if (var13[0] <= 0.0D) {
                var13[0] = Math.abs(2.0D * var5[0] - 0.98D * var2[0]);
            }

            var3[0] = Math.abs(var13[0] * 0.1D);
            var13[1] = 2.0D * var5[1] - var2[1];
            if (var13[1] <= 0.0D) {
                var13[1] = Math.abs(2.0D * var5[1] - 0.98D * var2[1]);
            }

            var3[1] = Math.abs(var13[1] * 0.1D);
            var1.simplex(var4, Conv.copy(var13), var3, this.tolerance);
            this.frechetTwoParParam = var1.getBestEstimates();
            this.frechetTwoParParamErrors = var1.getBestEstimatesErrors();
            this.frechetTwoParSumOfSquares = var1.getSumOfSquares();
            this.frechetTwoParWeightedSumOfSquares = this.frechetTwoParSumOfSquares;
            double var14 = this.frechetTwoParSumOfSquares;
            if (this.weighted) {
                this.frechetTwoParWeightedSumOfSquares = var1.getChiSquare();
                var14 = this.frechetTwoParWeightedSumOfSquares;
            }

            if (var11 < var14) {
                this.frechetTwoParParam = var5;
                this.frechetTwoParParamErrors = var6;
                this.frechetTwoParSumOfSquares = var7;
                if (this.weighted) {
                    this.frechetTwoParWeightedSumOfSquares = var9;
                }
            }

            this.frechetTwoParOrderMedians = Stat.frechetOrderStatisticMedians(this.frechetTwoParParam[0], this.frechetTwoParParam[1], this.numberOfDataPoints);
            Regression var16 = new Regression(this.frechetTwoParOrderMedians, this.sortedData, this.sortedWeights);
            if (this.suppressErrorMessages) {
                var16.suppressErrorMessages();
            }

            var16.linear();
            this.frechetTwoParLine = var16.getBestEstimates();
            this.frechetTwoParLineErrors = var16.getBestEstimatesErrors();
            this.frechetTwoParCorrCoeff = var16.getSampleR();
            if (!this.suppressFile) {
                this.outputFile(this.lastMethod, var1, var16, this.frechetTwoParNumberOfParameters);
            }

            if (!this.suppressPlot) {
                double[][] var17 = PlotGraph.fillData(2, this.numberOfDataPoints);
                var17[0] = this.frechetTwoParOrderMedians;
                var17[1] = this.sortedData;
                var17[2] = this.frechetTwoParOrderMedians;

                for(int var18 = 0; var18 < this.numberOfDataPoints; ++var18) {
                    var17[3][var18] = this.frechetTwoParLine[0] + this.frechetTwoParLine[1] * this.frechetTwoParOrderMedians[var18];
                }

                PlotGraph var21 = new PlotGraph(var17);
                int[] var19 = new int[]{4, 0};
                var21.setPoint(var19);
                int[] var20 = new int[]{0, 3};
                var21.setLine(var20);
                var21.setXaxisLegend("Two Parameter Frechet Order Statistic Medians");
                var21.setYaxisLegend("Ordered Data Values");
                var21.setGraphTitle("Two parameter Frechet probability plot, " + this.title + ":   gradient = " + Fmath.truncate(this.frechetTwoParLine[1], 4) + ", intercept = " + Fmath.truncate(this.frechetTwoParLine[0], 4) + ",  R = " + Fmath.truncate(this.frechetTwoParCorrCoeff, 4));
                var21.setGraphTitle2("  sigma = " + Fmath.truncate(this.frechetTwoParParam[1], 4) + ", gamma = " + Fmath.truncate(this.frechetTwoParParam[2], 4));
                var21.plot();
                this.probPlotDone = true;
            }

            this.frechetTwoParDone = true;
        }
    }

    public double frechetTwoParMu() {
        if (!this.frechetTwoParDone) {
            this.frechetTwoParProbabilityPlot();
        }

        return this.frechetTwoParParam[0];
    }

    public double frechetTwoParMuError() {
        if (!this.frechetTwoParDone) {
            this.frechetTwoParProbabilityPlot();
        }

        return this.frechetTwoParParamErrors[0];
    }

    public double frechetTwoParSigma() {
        if (!this.frechetTwoParDone) {
            this.frechetTwoParProbabilityPlot();
        }

        return this.frechetTwoParParam[1];
    }

    public double frechetTwoParSigmaError() {
        if (!this.frechetTwoParDone) {
            this.frechetTwoParProbabilityPlot();
        }

        return this.frechetTwoParParamErrors[1];
    }

    public double frechetTwoParGamma() {
        if (!this.frechetTwoParDone) {
            this.frechetTwoParProbabilityPlot();
        }

        return this.frechetTwoParParam[2];
    }

    public double frechetTwoParGammaError() {
        if (!this.frechetTwoParDone) {
            this.frechetTwoParProbabilityPlot();
        }

        return this.frechetTwoParParamErrors[2];
    }

    public double[] frechetTwoParOrderStatisticMedians() {
        if (!this.frechetTwoParDone) {
            this.frechetTwoParProbabilityPlot();
        }

        return this.frechetTwoParOrderMedians;
    }

    public double frechetTwoParGradient() {
        if (!this.frechetTwoParDone) {
            this.frechetTwoParProbabilityPlot();
        }

        return this.frechetTwoParLine[1];
    }

    public double frechetTwoParGradientError() {
        if (!this.frechetTwoParDone) {
            this.frechetTwoParProbabilityPlot();
        }

        return this.frechetTwoParLineErrors[1];
    }

    public double frechetTwoParIntercept() {
        if (!this.frechetTwoParDone) {
            this.frechetTwoParProbabilityPlot();
        }

        return this.frechetTwoParLine[0];
    }

    public double frechetTwoParInterceptError() {
        if (!this.frechetTwoParDone) {
            this.frechetTwoParProbabilityPlot();
        }

        return this.frechetTwoParLineErrors[0];
    }

    public double frechetTwoParCorrelationCoefficient() {
        if (!this.frechetTwoParDone) {
            this.frechetTwoParProbabilityPlot();
        }

        return this.frechetTwoParCorrCoeff;
    }

    public double frechetTwoParSumOfSquares() {
        if (!this.frechetTwoParDone) {
            this.frechetTwoParProbabilityPlot();
        }

        return this.frechetTwoParSumOfSquares;
    }

    public double frechetTwoParWeightedSumOfSquares() {
        if (!this.frechetTwoParDone) {
            this.frechetTwoParProbabilityPlot();
        }

        return this.frechetTwoParWeightedSumOfSquares;
    }

    public void frechetStandardUserSuppliedInitialEstimates(double var1) {
        this.userSuppliedEstimates[17] = true;
        this.userInitialEstimates[17] = new double[1];
        this.userInitialEstimates[17][0] = var1;
    }

    public void removeFrechetStandardUserSuppliedInitialEstimates() {
        this.userSuppliedEstimates[17] = false;
    }

    public void frechetStandardProbabilityPlot() {
        this.lastMethod = 17;
        this.frechetStandardNumberOfParameters = 1;
        if (this.numberOfDataPoints < 2) {
            throw new IllegalArgumentException("There must be at least two fillData points - preferably considerably more");
        } else {
            Regression var1 = new Regression(this.sortedData, this.sortedData, this.sortedWeights);
            if (this.suppressErrorMessages) {
                var1.suppressErrorMessages();
            }

            double[] var2 = new double[1];
            if (this.userSuppliedEstimates[17]) {
                var2[0] = this.userInitialEstimates[17][0];
                if (var2[0] <= 0.0D) {
                    var2[0] = 0.1D;
                }
            } else {
                var2[0] = 4.0D;
            }

            this.initialEstimates = var2;
            double[] var3 = new double[]{Math.abs(this.stepFactor * var2[0])};
            var1.addConstraint(0, -1, 0.0D);
            FrechetStandardProbPlotFunc var4 = new FrechetStandardProbPlotFunc();
            var4.setDataArray(this.numberOfDataPoints);
            var1.simplex(var4, Conv.copy(var2), var3, this.tolerance);
            double[] var5 = var1.getBestEstimates();
            double[] var6 = var1.getBestEstimatesErrors();
            double var7 = var1.getSumOfSquares();
            double var9 = var7;
            double var11 = var7;
            if (this.weighted) {
                var9 = var1.getSumOfWeightedResidualSquares();
                var11 = var9;
            }

            double[] var13 = new double[this.frechetStandardNumberOfParameters];
            var13[0] = 2.0D * var5[0] - var2[0];
            if (var13[0] <= 0.0D) {
                var13[0] = 2.0D * var5[0] - 0.98D * var2[0];
            }

            var1.simplex(var4, Conv.copy(var13), var3, this.tolerance);
            this.frechetStandardParam = var1.getBestEstimates();
            this.frechetStandardParamErrors = var1.getBestEstimatesErrors();
            this.frechetStandardSumOfSquares = var1.getSumOfSquares();
            this.frechetStandardWeightedSumOfSquares = this.frechetStandardSumOfSquares;
            double var14 = this.frechetStandardSumOfSquares;
            if (this.weighted) {
                this.frechetStandardWeightedSumOfSquares = var1.getChiSquare();
                var14 = this.frechetStandardWeightedSumOfSquares;
            }

            if (var11 < var14) {
                this.frechetStandardParam = var5;
                this.frechetStandardParamErrors = var6;
                this.frechetStandardSumOfSquares = var7;
                if (this.weighted) {
                    this.frechetStandardWeightedSumOfSquares = var9;
                }
            }

            this.frechetStandardOrderMedians = Stat.frechetOrderStatisticMedians(this.frechetStandardParam[0], this.numberOfDataPoints);
            Regression var16 = new Regression(this.frechetStandardOrderMedians, this.sortedData, this.sortedWeights);
            if (this.suppressErrorMessages) {
                var16.suppressErrorMessages();
            }

            var16.linear();
            this.frechetStandardLine = var16.getBestEstimates();
            this.frechetStandardLineErrors = var16.getBestEstimatesErrors();
            this.frechetStandardCorrCoeff = var16.getSampleR();
            if (!this.suppressFile) {
                this.outputFile(this.lastMethod, var1, var16, this.frechetStandardNumberOfParameters);
            }

            if (!this.suppressPlot) {
                double[][] var17 = PlotGraph.fillData(2, this.numberOfDataPoints);
                var17[0] = this.frechetStandardOrderMedians;
                var17[1] = this.sortedData;
                var17[2] = this.frechetStandardOrderMedians;

                for(int var18 = 0; var18 < this.numberOfDataPoints; ++var18) {
                    var17[3][var18] = this.frechetStandardLine[0] + this.frechetStandardLine[1] * this.frechetStandardOrderMedians[var18];
                }

                PlotGraph var21 = new PlotGraph(var17);
                int[] var19 = new int[]{4, 0};
                var21.setPoint(var19);
                int[] var20 = new int[]{0, 3};
                var21.setLine(var20);
                var21.setXaxisLegend("Frechet StandardOrder Statistic Medians");
                var21.setYaxisLegend("Ordered Data Values");
                var21.setGraphTitle("Standard Frechet probability plot, " + this.title + ":   gradient = " + Fmath.truncate(this.frechetStandardLine[1], 4) + ", intercept = " + Fmath.truncate(this.frechetStandardLine[0], 4) + ",  R = " + Fmath.truncate(this.frechetStandardCorrCoeff, 4));
                var21.setGraphTitle2("  gamma = " + Fmath.truncate(this.frechetStandardParam[0], 4));
                var21.plot();
                this.probPlotDone = true;
            }

            this.frechetStandardDone = true;
        }
    }

    public double frechetStandardMu() {
        if (!this.frechetStandardDone) {
            this.frechetStandardProbabilityPlot();
        }

        return this.frechetStandardParam[0];
    }

    public double frechetStandardMuError() {
        if (!this.frechetStandardDone) {
            this.frechetStandardProbabilityPlot();
        }

        return this.frechetStandardParamErrors[0];
    }

    public double frechetStandardSigma() {
        if (!this.frechetStandardDone) {
            this.frechetStandardProbabilityPlot();
        }

        return this.frechetStandardParam[1];
    }

    public double frechetStandardSigmaError() {
        if (!this.frechetStandardDone) {
            this.frechetStandardProbabilityPlot();
        }

        return this.frechetStandardParamErrors[1];
    }

    public double frechetStandardGamma() {
        if (!this.frechetStandardDone) {
            this.frechetStandardProbabilityPlot();
        }

        return this.frechetStandardParam[2];
    }

    public double frechetStandardGammaError() {
        if (!this.frechetStandardDone) {
            this.frechetStandardProbabilityPlot();
        }

        return this.frechetStandardParamErrors[2];
    }

    public double[] frechetStandardOrderStatisticMedians() {
        if (!this.frechetStandardDone) {
            this.frechetStandardProbabilityPlot();
        }

        return this.frechetStandardOrderMedians;
    }

    public double frechetStandardGradient() {
        if (!this.frechetStandardDone) {
            this.frechetStandardProbabilityPlot();
        }

        return this.frechetStandardLine[1];
    }

    public double frechetStandardGradientError() {
        if (!this.frechetStandardDone) {
            this.frechetStandardProbabilityPlot();
        }

        return this.frechetStandardLineErrors[1];
    }

    public double frechetStandardIntercept() {
        if (!this.frechetStandardDone) {
            this.frechetStandardProbabilityPlot();
        }

        return this.frechetStandardLine[0];
    }

    public double frechetStandardInterceptError() {
        if (!this.frechetStandardDone) {
            this.frechetStandardProbabilityPlot();
        }

        return this.frechetStandardLineErrors[0];
    }

    public double frechetStandardCorrelationCoefficient() {
        if (!this.frechetStandardDone) {
            this.frechetStandardProbabilityPlot();
        }

        return this.frechetStandardCorrCoeff;
    }

    public double frechetStandardSumOfSquares() {
        if (!this.frechetStandardDone) {
            this.frechetStandardProbabilityPlot();
        }

        return this.frechetStandardSumOfSquares;
    }

    public double frechetStandardWeightedSumOfSquares() {
        if (!this.frechetStandardDone) {
            this.frechetStandardProbabilityPlot();
        }

        return this.frechetStandardWeightedSumOfSquares;
    }

    public void gumbelMinUserSuppliedInitialEstimates(double var1, double var3) {
        this.userSuppliedEstimates[5] = true;
        this.userInitialEstimates[5] = new double[2];
        this.userInitialEstimates[5][0] = var1;
        this.userInitialEstimates[5][1] = var3;
    }

    public void removeGumbelMinUserSuppliedInitialEstimates() {
        this.userSuppliedEstimates[5] = false;
    }

    public void gumbelMinProbabilityPlot() {
        this.lastMethod = 5;
        this.gumbelMinNumberOfParameters = 2;
        if (this.numberOfDataPoints < 3) {
            throw new IllegalArgumentException("There must be at least three fillData points - preferably considerably more");
        } else {
            Regression var1 = new Regression(this.sortedData, this.sortedData, this.sortedWeights);
            if (this.suppressErrorMessages) {
                var1.suppressErrorMessages();
            }

            double[] var2 = new double[2];
            if (this.userSuppliedEstimates[5]) {
                var2[0] = this.userInitialEstimates[5][0];
                var2[1] = this.userInitialEstimates[5][1];
                if (var2[1] < 0.0D) {
                    var2[1] = this.standardDeviation / 3.0D;
                }
            } else {
                var2[0] = this.mean;
                var2[1] = this.standardDeviation;
            }

            double[] var3 = new double[]{this.stepFactor * var2[0], this.stepFactor * var2[1]};
            if (var3[0] == 0.0D) {
                var3[0] = this.standardDeviation / 30.0D;
            }

            this.initialEstimates = var2;
            var1.addConstraint(1, -1, 0.0D);
            GumbelMinProbPlotFunc var4 = new GumbelMinProbPlotFunc();
            var4.setDataArray(this.numberOfDataPoints);
            var1.simplex(var4, var2, var3, this.tolerance);
            this.gumbelMinParam = var1.getBestEstimates();
            this.gumbelMinParamErrors = var1.getBestEstimatesErrors();
            this.gumbelMinSumOfSquares = var1.getSumOfSquares();
            this.gumbelMinWeightedSumOfSquares = this.gumbelMinSumOfSquares;
            if (this.weighted) {
                this.gumbelMinWeightedSumOfSquares = var1.getChiSquare();
            }

            this.gumbelMinOrderMedians = Stat.gumbelMinOrderStatisticMedians(this.gumbelMinParam[0], this.gumbelMinParam[1], this.numberOfDataPoints);
            Regression var5 = new Regression(this.gumbelMinOrderMedians, this.sortedData, this.sortedWeights);
            if (this.suppressErrorMessages) {
                var5.suppressErrorMessages();
            }

            var5.linear();
            this.gumbelMinLine = var5.getBestEstimates();
            this.gumbelMinLineErrors = var5.getBestEstimatesErrors();
            this.gumbelMinCorrCoeff = var5.getSampleR();
            if (!this.suppressFile) {
                this.outputFile(this.lastMethod, var1, var5, this.gumbelMinNumberOfParameters);
            }

            if (!this.suppressPlot) {
                double[][] var6 = PlotGraph.fillData(2, this.numberOfDataPoints);
                var6[0] = this.gumbelMinOrderMedians;
                var6[1] = this.sortedData;
                var6[2] = this.gumbelMinOrderMedians;

                for(int var7 = 0; var7 < this.numberOfDataPoints; ++var7) {
                    var6[3][var7] = this.gumbelMinLine[0] + this.gumbelMinLine[1] * this.gumbelMinOrderMedians[var7];
                }

                PlotGraph var10 = new PlotGraph(var6);
                int[] var8 = new int[]{4, 0};
                var10.setPoint(var8);
                int[] var9 = new int[]{0, 3};
                var10.setLine(var9);
                var10.setXaxisLegend("Gumbel (minimum order statistic) Order Statistic Medians");
                var10.setYaxisLegend("Ordered Data Values");
                var10.setGraphTitle("Gumbel (minimum order statistic) probability plot, " + this.title + ":   gradient = " + Fmath.truncate(this.gumbelMinLine[1], 4) + ", intercept = " + Fmath.truncate(this.gumbelMinLine[0], 4) + ",  R = " + Fmath.truncate(this.gumbelMinCorrCoeff, 4));
                var10.setGraphTitle2("  mu = " + Fmath.truncate(this.gumbelMinParam[0], 4) + ", sigma = " + Fmath.truncate(this.gumbelMinParam[1], 4));
                var10.plot();
                this.probPlotDone = true;
            }

            this.gumbelMinDone = true;
        }
    }

    public double gumbelMinMu() {
        if (!this.gumbelMinDone) {
            this.gumbelMinProbabilityPlot();
        }

        return this.gumbelMinParam[0];
    }

    public double gumbelMinMuError() {
        if (!this.gumbelMinDone) {
            this.gumbelMinProbabilityPlot();
        }

        return this.gumbelMinParamErrors[0];
    }

    public double gumbelMinSigma() {
        if (!this.gumbelMinDone) {
            this.gumbelMinProbabilityPlot();
        }

        return this.gumbelMinParam[1];
    }

    public double gumbelMinSigmaError() {
        if (!this.gumbelMinDone) {
            this.gumbelMinProbabilityPlot();
        }

        return this.gumbelMinParamErrors[1];
    }

    public double[] gumbelMinOrderStatisticMedians() {
        if (!this.gumbelMinDone) {
            this.gumbelMinProbabilityPlot();
        }

        return this.gumbelMinOrderMedians;
    }

    public double gumbelMinGradient() {
        if (!this.gumbelMinDone) {
            this.gumbelMinProbabilityPlot();
        }

        return this.gumbelMinLine[1];
    }

    public double gumbelMinGradientError() {
        if (!this.gumbelMinDone) {
            this.gumbelMinProbabilityPlot();
        }

        return this.gumbelMinLineErrors[1];
    }

    public double gumbelMinIntercept() {
        if (!this.gumbelMinDone) {
            this.gumbelMinProbabilityPlot();
        }

        return this.gumbelMinLine[0];
    }

    public double gumbelMinInterceptError() {
        if (!this.gumbelMinDone) {
            this.gumbelMinProbabilityPlot();
        }

        return this.gumbelMinLineErrors[0];
    }

    public double gumbelMinCorrelationCoefficient() {
        if (!this.gumbelMinDone) {
            this.gumbelMinProbabilityPlot();
        }

        return this.gumbelMinCorrCoeff;
    }

    public double gumbelMinSumOfSquares() {
        if (!this.gumbelMinDone) {
            this.gumbelMinProbabilityPlot();
        }

        return this.gumbelMinSumOfSquares;
    }

    public double gumbelMinWeightedSumOfSquares() {
        if (!this.gumbelMinDone) {
            this.gumbelMinProbabilityPlot();
        }

        return this.gumbelMinWeightedSumOfSquares;
    }

    public void gumbelMaxUserSuppliedInitialEstimates(double var1, double var3) {
        this.userSuppliedEstimates[6] = true;
        this.userInitialEstimates[6] = new double[2];
        this.userInitialEstimates[6][0] = var1;
        this.userInitialEstimates[6][1] = var3;
    }

    public void removeGumbelMaxUserSuppliedInitialEstimates() {
        this.userSuppliedEstimates[6] = false;
    }

    public void gumbelMaxProbabilityPlot() {
        this.lastMethod = 6;
        this.gumbelMaxNumberOfParameters = 2;
        if (this.numberOfDataPoints < 3) {
            throw new IllegalArgumentException("There must be at least three fillData points - preferably considerably more");
        } else {
            Regression var1 = new Regression(this.sortedData, this.sortedData, this.sortedWeights);
            if (this.suppressErrorMessages) {
                var1.suppressErrorMessages();
            }

            double[] var2 = new double[2];
            if (this.userSuppliedEstimates[6]) {
                var2[0] = this.userInitialEstimates[6][0];
                var2[1] = this.userInitialEstimates[6][1];
                if (var2[1] < 0.0D) {
                    var2[1] = this.standardDeviation / 3.0D;
                }
            } else {
                var2[0] = this.mean;
                var2[1] = this.standardDeviation;
            }

            double[] var3 = new double[]{this.stepFactor * var2[0], this.stepFactor * var2[1]};
            if (var3[0] == 0.0D) {
                var3[0] = this.standardDeviation / 30.0D;
            }

            this.initialEstimates = var2;
            var1.addConstraint(1, -1, 0.0D);
            GumbelMaxProbPlotFunc var4 = new GumbelMaxProbPlotFunc();
            var4.setDataArray(this.numberOfDataPoints);
            var1.simplex(var4, var2, var3, this.tolerance);
            this.gumbelMaxParam = var1.getBestEstimates();
            this.gumbelMaxParamErrors = var1.getBestEstimatesErrors();
            this.gumbelMaxSumOfSquares = var1.getSumOfSquares();
            this.gumbelMaxWeightedSumOfSquares = this.gumbelMaxSumOfSquares;
            if (this.weighted) {
                this.gumbelMaxWeightedSumOfSquares = var1.getChiSquare();
            }

            this.gumbelMaxOrderMedians = Stat.gumbelMaxOrderStatisticMedians(this.gumbelMaxParam[0], this.gumbelMaxParam[1], this.numberOfDataPoints);
            Regression var5 = new Regression(this.gumbelMaxOrderMedians, this.sortedData, this.sortedWeights);
            if (this.suppressErrorMessages) {
                var5.suppressErrorMessages();
            }

            var5.linear();
            this.gumbelMaxLine = var5.getBestEstimates();
            this.gumbelMaxCorrCoeff = var5.getSampleR();
            if (!this.suppressFile) {
                this.outputFile(this.lastMethod, var1, var5, this.gumbelMaxNumberOfParameters);
            }

            if (!this.suppressPlot) {
                double[][] var6 = PlotGraph.fillData(2, this.numberOfDataPoints);
                var6[0] = this.gumbelMaxOrderMedians;
                var6[1] = this.sortedData;
                var6[2] = this.gumbelMaxOrderMedians;

                for(int var7 = 0; var7 < this.numberOfDataPoints; ++var7) {
                    var6[3][var7] = this.gumbelMaxLine[0] + this.gumbelMaxLine[1] * this.gumbelMaxOrderMedians[var7];
                }

                PlotGraph var10 = new PlotGraph(var6);
                int[] var8 = new int[]{4, 0};
                var10.setPoint(var8);
                int[] var9 = new int[]{0, 3};
                var10.setLine(var9);
                var10.setXaxisLegend("Gumbel (maximum order statistic) Order Statistic Medians");
                var10.setYaxisLegend("Ordered Data Values");
                var10.setGraphTitle("Gumbel (maximum order statistic) probability plot, " + this.title + ":   gradient = " + Fmath.truncate(this.gumbelMaxLine[1], 4) + ", intercept = " + Fmath.truncate(this.gumbelMaxLine[0], 4) + ",  R = " + Fmath.truncate(this.gumbelMaxCorrCoeff, 4));
                var10.setGraphTitle2("  mu = " + Fmath.truncate(this.gumbelMaxParam[0], 4) + ", sigma = " + Fmath.truncate(this.gumbelMaxParam[1], 4));
                var10.plot();
                this.probPlotDone = true;
            }

            this.gumbelMaxDone = true;
        }
    }

    public double gumbelMaxMu() {
        if (!this.gumbelMaxDone) {
            this.gumbelMaxProbabilityPlot();
        }

        return this.gumbelMaxParam[0];
    }

    public double gumbelMaxMuError() {
        if (!this.gumbelMaxDone) {
            this.gumbelMaxProbabilityPlot();
        }

        return this.gumbelMaxParamErrors[0];
    }

    public double gumbelMaxSigma() {
        if (!this.gumbelMaxDone) {
            this.gumbelMaxProbabilityPlot();
        }

        return this.gumbelMaxParam[1];
    }

    public double gumbelMaxSigmaError() {
        if (!this.gumbelMaxDone) {
            this.gumbelMaxProbabilityPlot();
        }

        return this.gumbelMaxParamErrors[1];
    }

    public double[] gumbelMaxOrderStatisticMedians() {
        if (!this.gumbelMaxDone) {
            this.gumbelMaxProbabilityPlot();
        }

        return this.gumbelMaxOrderMedians;
    }

    public double gumbelMaxGradient() {
        if (!this.gumbelMaxDone) {
            this.gumbelMaxProbabilityPlot();
        }

        return this.gumbelMaxLine[1];
    }

    public double gumbelMaxGradientError() {
        if (!this.gumbelMaxDone) {
            this.gumbelMaxProbabilityPlot();
        }

        return this.gumbelMaxLineErrors[1];
    }

    public double gumbelMaxIntercept() {
        if (!this.gumbelMaxDone) {
            this.gumbelMaxProbabilityPlot();
        }

        return this.gumbelMaxLine[0];
    }

    public double gumbelMaxInterceptError() {
        if (!this.gumbelMaxDone) {
            this.gumbelMaxProbabilityPlot();
        }

        return this.gumbelMaxLineErrors[0];
    }

    public double gumbelMaxCorrelationCoefficient() {
        if (!this.gumbelMaxDone) {
            this.gumbelMaxProbabilityPlot();
        }

        return this.gumbelMaxCorrCoeff;
    }

    public double gumbelMaxSumOfSquares() {
        if (!this.gumbelMaxDone) {
            this.gumbelMaxProbabilityPlot();
        }

        return this.gumbelMaxSumOfSquares;
    }

    public double gumbelMaxWeightedSumOfSquares() {
        if (!this.gumbelMaxDone) {
            this.gumbelMaxProbabilityPlot();
        }

        return this.gumbelMaxWeightedSumOfSquares;
    }

    public void rayleighUserSuppliedInitialEstimates(double var1) {
        this.userSuppliedEstimates[3] = true;
        this.userInitialEstimates[3] = new double[1];
        this.userInitialEstimates[3][0] = var1;
    }

    public void removeRayleighUserSuppliedInitialEstimates() {
        this.userSuppliedEstimates[3] = false;
    }

    public void rayleighProbabilityPlot() {
        this.lastMethod = 3;
        this.rayleighNumberOfParameters = 1;
        if (this.numberOfDataPoints < 3) {
            throw new IllegalArgumentException("There must be at least three fillData points - preferably considerably more");
        } else {
            Regression var1 = new Regression(this.sortedData, this.sortedData, this.sortedWeights);
            if (this.suppressErrorMessages) {
                var1.suppressErrorMessages();
            }

            double[] var2 = new double[1];
            if (this.userSuppliedEstimates[3]) {
                var2[0] = this.userInitialEstimates[3][0];
                if (var2[0] < 0.0D) {
                    var2[0] = this.standardDeviation;
                }
            } else {
                var2[0] = this.standardDeviation;
            }

            double[] var3 = new double[]{this.stepFactor * var2[0]};
            if (var3[0] == 0.0D) {
                var3[0] = this.standardDeviation / 30.0D;
            }

            this.initialEstimates = var2;
            var1.addConstraint(0, -1, 0.0D);
            RayleighProbPlotFunc var4 = new RayleighProbPlotFunc();
            var4.setDataArray(this.numberOfDataPoints);
            var1.simplex(var4, var2, var3, this.tolerance);
            this.rayleighParam = var1.getBestEstimates();
            this.rayleighParamErrors = var1.getBestEstimatesErrors();
            this.rayleighSumOfSquares = var1.getSumOfSquares();
            this.rayleighWeightedSumOfSquares = this.rayleighSumOfSquares;
            if (this.weighted) {
                this.rayleighWeightedSumOfSquares = var1.getChiSquare();
            }

            this.rayleighOrderMedians = Stat.weibullOrderStatisticMedians(0.0D, this.rayleighParam[0] * Math.sqrt(2.0D), 2.0D, this.numberOfDataPoints);
            Regression var5 = new Regression(this.rayleighOrderMedians, this.sortedData, this.sortedWeights);
            if (this.suppressErrorMessages) {
                var5.suppressErrorMessages();
            }

            var5.linear();
            this.rayleighLine = var5.getBestEstimates();
            this.rayleighLineErrors = var5.getBestEstimatesErrors();
            this.rayleighCorrCoeff = var5.getSampleR();
            if (!this.suppressFile) {
                this.outputFile(this.lastMethod, var1, var5, this.rayleighNumberOfParameters);
            }

            if (!this.suppressPlot) {
                double[][] var6 = PlotGraph.fillData(2, this.numberOfDataPoints);
                var6[0] = this.rayleighOrderMedians;
                var6[1] = this.sortedData;
                var6[2] = this.rayleighOrderMedians;

                for(int var7 = 0; var7 < this.numberOfDataPoints; ++var7) {
                    var6[3][var7] = this.rayleighLine[0] + this.rayleighLine[1] * this.rayleighOrderMedians[var7];
                }

                PlotGraph var10 = new PlotGraph(var6);
                int[] var8 = new int[]{4, 0};
                var10.setPoint(var8);
                int[] var9 = new int[]{0, 3};
                var10.setLine(var9);
                var10.setXaxisLegend("Rayleigh Order Statistic Medians");
                var10.setYaxisLegend("Ordered Data Values");
                var10.setGraphTitle("Rayleigh probability plot, " + this.title + ":   gradient = " + Fmath.truncate(this.rayleighLine[1], 4) + ", intercept = " + Fmath.truncate(this.rayleighLine[0], 4) + ",  R = " + Fmath.truncate(this.rayleighCorrCoeff, 4));
                var10.setGraphTitle2("  beta = " + Fmath.truncate(this.rayleighParam[0], 4));
                var10.plot();
                this.probPlotDone = true;
            }

            this.rayleighDone = true;
        }
    }

    public double rayleighBeta() {
        if (!this.rayleighDone) {
            this.rayleighProbabilityPlot();
        }

        return this.rayleighParam[0];
    }

    public double rayleighBetaError() {
        if (!this.rayleighDone) {
            this.rayleighProbabilityPlot();
        }

        return this.rayleighParamErrors[0];
    }

    public double[] rayleighOrderStatisticMedians() {
        if (!this.rayleighDone) {
            this.rayleighProbabilityPlot();
        }

        return this.rayleighOrderMedians;
    }

    public double rayleighGradient() {
        if (!this.rayleighDone) {
            this.rayleighProbabilityPlot();
        }

        return this.rayleighLine[1];
    }

    public double rayleighGradientError() {
        if (!this.rayleighDone) {
            this.rayleighProbabilityPlot();
        }

        return this.rayleighLineErrors[1];
    }

    public double rayleighIntercept() {
        if (!this.rayleighDone) {
            this.rayleighProbabilityPlot();
        }

        return this.rayleighLine[0];
    }

    public double rayleighInterceptError() {
        if (!this.rayleighDone) {
            this.rayleighProbabilityPlot();
        }

        return this.rayleighLineErrors[0];
    }

    public double rayleighCorrelationCoefficient() {
        if (!this.rayleighDone) {
            this.rayleighProbabilityPlot();
        }

        return this.rayleighCorrCoeff;
    }

    public double rayleighSumOfSquares() {
        if (!this.rayleighDone) {
            this.rayleighProbabilityPlot();
        }

        return this.rayleighSumOfSquares;
    }

    public double rayleighMinWeightedSumOfSquares() {
        if (!this.rayleighDone) {
            this.rayleighProbabilityPlot();
        }

        return this.rayleighWeightedSumOfSquares;
    }

    public void paretoUserSuppliedInitialEstimates(double var1, double var3) {
        this.userSuppliedEstimates[4] = true;
        this.userInitialEstimates[4] = new double[2];
        this.userInitialEstimates[4][0] = var1;
        this.userInitialEstimates[4][1] = var3;
    }

    public void removeParetoUserSuppliedInitialEstimates() {
        this.userSuppliedEstimates[4] = false;
    }

    public void paretoProbabilityPlot() {
        this.lastMethod = 4;
        this.paretoNumberOfParameters = 2;
        if (this.numberOfDataPoints < 3) {
            throw new IllegalArgumentException("There must be at least three fillData points - preferably considerably more");
        } else {
            Regression var1 = new Regression(this.sortedData, this.sortedData, this.sortedWeights);
            if (this.suppressErrorMessages) {
                var1.suppressErrorMessages();
            }

            double[] var2 = new double[2];
            if (this.userSuppliedEstimates[4]) {
                var2[0] = this.userInitialEstimates[4][0];
                var2[1] = this.userInitialEstimates[4][1];
                if (var2[1] < this.minimum) {
                    var2[1] = this.minimum + this.standardDeviation / 30.0D;
                }
            } else {
                var2[0] = this.mean / (this.mean - this.minimum);
                var2[1] = this.minimum + this.standardDeviation / 30.0D;
            }

            double[] var3 = new double[]{Math.abs(this.stepFactor * var2[0]), Math.abs(this.stepFactor * var2[1])};
            if (var3[0] == 0.0D) {
                var3[0] = this.standardDeviation / 30.0D;
            }

            if (var3[1] == 0.0D) {
                var3[1] = this.standardDeviation / 30.0D;
            }

            this.initialEstimates = var2;
            ParetoProbPlotFunc var4 = new ParetoProbPlotFunc();
            var4.setDataArray(this.numberOfDataPoints);
            var1.simplex(var4, var2, var3, this.tolerance);
            this.paretoParam = var1.getBestEstimates();
            this.paretoParamErrors = var1.getBestEstimatesErrors();
            this.paretoSumOfSquares = var1.getSumOfSquares();
            this.paretoWeightedSumOfSquares = this.paretoSumOfSquares;
            if (this.weighted) {
                this.paretoWeightedSumOfSquares = var1.getChiSquare();
            }

            this.paretoOrderMedians = Stat.paretoOrderStatisticMedians(this.paretoParam[0], this.paretoParam[1], this.numberOfDataPoints);
            Regression var5 = new Regression(this.paretoOrderMedians, this.sortedData, this.sortedWeights);
            if (this.suppressErrorMessages) {
                var5.suppressErrorMessages();
            }

            var5.linear();
            this.paretoLine = var5.getBestEstimates();
            this.paretoLineErrors = var5.getBestEstimatesErrors();
            this.paretoCorrCoeff = var5.getSampleR();
            if (!this.suppressFile) {
                this.outputFile(this.lastMethod, var1, var5, this.paretoNumberOfParameters);
            }

            if (!this.suppressPlot) {
                double[][] var6 = PlotGraph.fillData(2, this.numberOfDataPoints);
                var6[0] = this.paretoOrderMedians;
                var6[1] = this.sortedData;
                var6[2] = this.paretoOrderMedians;

                for(int var7 = 0; var7 < this.numberOfDataPoints; ++var7) {
                    var6[3][var7] = this.paretoLine[0] + this.paretoLine[1] * this.paretoOrderMedians[var7];
                }

                PlotGraph var10 = new PlotGraph(var6);
                int[] var8 = new int[]{4, 0};
                var10.setPoint(var8);
                int[] var9 = new int[]{0, 3};
                var10.setLine(var9);
                var10.setXaxisLegend("Pareto Order Statistic Medians");
                var10.setYaxisLegend("Ordered Data Values");
                var10.setGraphTitle("Pareto probability plot, " + this.title + ":   gradient = " + Fmath.truncate(this.paretoLine[1], 4) + ", intercept = " + Fmath.truncate(this.paretoLine[0], 4) + ",  R = " + Fmath.truncate(this.paretoCorrCoeff, 4));
                var10.setGraphTitle2("  alpha = " + Fmath.truncate(this.paretoParam[0], 4) + ", beta = " + Fmath.truncate(this.paretoParam[1], 4));
                var10.plot();
                this.probPlotDone = true;
            }

            this.paretoDone = true;
        }
    }

    public double paretoAlpha() {
        if (!this.paretoDone) {
            this.paretoProbabilityPlot();
        }

        return this.paretoParam[0];
    }

    public double paretoAlphaError() {
        if (!this.paretoDone) {
            this.paretoProbabilityPlot();
        }

        return this.paretoParamErrors[0];
    }

    public double paretoBeta() {
        if (!this.paretoDone) {
            this.paretoProbabilityPlot();
        }

        return this.paretoParam[1];
    }

    public double paretoBetaError() {
        if (!this.paretoDone) {
            this.paretoProbabilityPlot();
        }

        return this.paretoParamErrors[1];
    }

    public double[] paretoOrderStatisticMedians() {
        if (!this.paretoDone) {
            this.paretoProbabilityPlot();
        }

        return this.paretoOrderMedians;
    }

    public double paretoGradient() {
        if (!this.paretoDone) {
            this.paretoProbabilityPlot();
        }

        return this.paretoLine[1];
    }

    public double paretoGradientError() {
        if (!this.paretoDone) {
            this.paretoProbabilityPlot();
        }

        return this.paretoLineErrors[1];
    }

    public double paretoIntercept() {
        if (!this.paretoDone) {
            this.paretoProbabilityPlot();
        }

        return this.paretoLine[0];
    }

    public double paretoInterceptError() {
        if (!this.paretoDone) {
            this.paretoProbabilityPlot();
        }

        return this.paretoLineErrors[0];
    }

    public double paretoCorrelationCoefficient() {
        if (!this.paretoDone) {
            this.paretoProbabilityPlot();
        }

        return this.paretoCorrCoeff;
    }

    public double paretoSumOfSquares() {
        if (!this.paretoDone) {
            this.paretoProbabilityPlot();
        }

        return this.paretoSumOfSquares;
    }

    public double paretoWeightedSumOfSquares() {
        if (!this.paretoDone) {
            this.paretoProbabilityPlot();
        }

        return this.paretoWeightedSumOfSquares;
    }

    public void fDistributionProbabilityPlot(int var1, int var2) {
        this.lastMethod = 15;
        this.fDistributionNu1 = var1;
        this.fDistributionNu2 = var2;
        this.fDistributionNumberOfParameters = 0;
        if (this.numberOfDataPoints < 3) {
            throw new IllegalArgumentException("There must be at least three fillData points - preferably considerably more");
        } else {
            this.fDistributionOrderMedians = Stat.fDistributionOrderStatisticMedians(var1, var2, this.numberOfDataPoints);
            Regression var3 = new Regression(this.fDistributionOrderMedians, this.sortedData, this.sortedWeights);
            if (this.suppressErrorMessages) {
                var3.suppressErrorMessages();
            }

            var3.linear();
            this.fDistributionLine = var3.getBestEstimates();
            this.fDistributionLineErrors = var3.getBestEstimatesErrors();
            this.fDistributionCorrCoeff = var3.getSampleR();
            this.fDistributionSumOfSquares = var3.getSumOfSquares();
            this.fDistributionSumOfSquares = var3.getSumOfSquares();
            this.fDistributionWeightedSumOfSquares = this.paretoSumOfSquares;
            if (this.weighted) {
                this.fDistributionWeightedSumOfSquares = var3.getChiSquare();
            }

            if (!this.suppressFile) {
                this.outputFile(this.lastMethod, (Regression)null, var3, 0);
            }

            if (!this.suppressPlot) {
                double[][] var4 = PlotGraph.fillData(2, this.numberOfDataPoints);
                var4[0] = this.fDistributionOrderMedians;
                var4[1] = this.sortedData;
                var4[2] = this.fDistributionOrderMedians;

                for(int var5 = 0; var5 < this.numberOfDataPoints; ++var5) {
                    var4[3][var5] = this.fDistributionLine[0] + this.fDistributionLine[1] * this.fDistributionOrderMedians[var5];
                }

                PlotGraph var8 = new PlotGraph(var4);
                int[] var6 = new int[]{4, 0};
                var8.setPoint(var6);
                int[] var7 = new int[]{0, 3};
                var8.setLine(var7);
                var8.setXaxisLegend("F-distribution Order Statistic Medians");
                var8.setYaxisLegend("Ordered Data Values");
                var8.setGraphTitle("F-distribution probability plot, " + this.title + ":   gradient = " + Fmath.truncate(this.fDistributionLine[1], 4) + ", intercept = " + Fmath.truncate(this.fDistributionLine[0], 4) + ",  R = " + Fmath.truncate(this.fDistributionCorrCoeff, 4));
                var8.setGraphTitle2("  nu1 = " + var1 + ", nu2 = " + var2);
                var8.plot();
                this.probPlotDone = true;
            }

            this.fDistributionDone = true;
        }
    }

    public double[] fDistributionOrderStatisticMedians() {
        if (!this.fDistributionDone) {
            throw new IllegalArgumentException("F-distribution Probability Plot method has not been called");
        } else {
            return this.fDistributionOrderMedians;
        }
    }

    public double fDistributionGradient() {
        if (!this.fDistributionDone) {
            throw new IllegalArgumentException("F-distribution Probability Plot method has not been called");
        } else {
            return this.fDistributionLine[1];
        }
    }

    public double fDistributionGradientError() {
        if (!this.fDistributionDone) {
            throw new IllegalArgumentException("F-distribution Probability Plot method has not been called");
        } else {
            return this.fDistributionLineErrors[1];
        }
    }

    public double fDistributionIntercept() {
        if (!this.fDistributionDone) {
            throw new IllegalArgumentException("F-distribution Probability Plot method has not been called");
        } else {
            return this.fDistributionLine[0];
        }
    }

    public double fDistributionInterceptError() {
        if (!this.fDistributionDone) {
            throw new IllegalArgumentException("F-distribution Probability Plot method has not been called");
        } else {
            return this.fDistributionLineErrors[0];
        }
    }

    public double fDistributionCorrelationCoefficient() {
        if (!this.fDistributionDone) {
            throw new IllegalArgumentException("F-distribution Probability Plot method has not been called");
        } else {
            return this.fDistributionCorrCoeff;
        }
    }

    public double fDistributionSumOfSquares() {
        if (!this.fDistributionDone) {
            throw new IllegalArgumentException("F-distribution Probability Plot method has not been called");
        } else {
            return this.fDistributionSumOfSquares;
        }
    }

    public double fDistributionWeightedSumOfSquares() {
        if (!this.fDistributionDone) {
            throw new IllegalArgumentException("F-distribution Probability Plot method has not been called");
        } else {
            return this.fDistributionWeightedSumOfSquares;
        }
    }

    public double[] orderedData() {
        return this.sortedData;
    }

    public int numberOfDataPoints() {
        return this.numberOfDataPoints;
    }

    public double mean() {
        return this.mean;
    }

    public double standardDeviation() {
        if (!this.probPlotDone) {
            throw new IllegalArgumentException("no probability plot method has been called");
        } else {
            return this.standardDeviation;
        }
    }

    public double minimum() {
        return this.minimum;
    }

    public double maximum() {
        return this.maximum;
    }

    public double delta() {
        return this.delta;
    }

    public void resetDelta(double var1) {
        this.delta = var1;
    }

    public double stepFactor() {
        return this.stepFactor;
    }

    public void resetStepFactor(double var1) {
        this.stepFactor = var1;
    }

    public void setDenominatorToN() {
        this.nFactorOptionI = true;
        this.arrayAsStat.setDenominatorToN();
        this.standardDeviation = this.arrayAsStat.standardDeviation();
        this.nFactorReset = true;
    }

    public void setDenominatorToNminusOne() {
        this.nFactorOptionI = false;
        this.arrayAsStat.setDenominatorToNminusOne();
        this.standardDeviation = this.arrayAsStat.standardDeviation();
        this.nFactorReset = true;
    }

    public double[] getInitialEstimates() {
        if (this.lastMethod == -1) {
            throw new IllegalArgumentException("No probability plot method has been called");
        } else {
            return this.initialEstimates;
        }
    }

    public void outputFile(int var1, Regression var2, Regression var3, int var4) {
        String var5 = this.methodNames[this.lastMethod];
        String var6 = var5 + "ProbabilityPlotOutput" + this.title + ".txt";
        var6 = this.removeSpacesEtc(var6);
        double[] var7 = var3.getBestEstimates();
        double[] var8 = var3.getBestEstimatesErrors();
        double[] var9 = null;
        double[] var10 = null;
        double[][] var11 = (double[][])null;
        if (var4 > 0) {
            var9 = var2.getBestEstimates();
            var10 = var2.getBestEstimatesErrors();
            var11 = var2.getCorrCoeffMatrix();
        }

        FileOutput var12 = new FileOutput(var6);
        var12.println(var5 + " Probability Plot");
        var12.println();
        var12.println(this.tad.getFullDate() + ", " + this.tad.getShortTime24());
        var12.println();
        switch(this.weightingOption) {
            case 0:
                var12.println("Unweighted constrained simplex regression");
                break;
            case 1:
                var12.println("Weighted constrained simplex regression - user supplied weights");
                break;
            case 2:
                var12.println("Weighted constrained simplex regression - weights = responses");
                break;
            case 3:
                var12.println("Weighted constrained simplex regression - weights = square root of the responses");
        }

        var12.println();
        var12.print("Gradient:  " + Fmath.truncate(var7[1], this.trunc), 25);
        var12.println("s.e. " + Fmath.truncate(var8[1], this.trunc));
        var12.print("Intercept: " + Fmath.truncate(var7[0], this.trunc), 25);
        var12.println("s.e. " + Fmath.truncate(var8[0], this.trunc));
        var12.println();
        var12.println("Correlation Coefficient, r: " + Fmath.truncate(var3.getSampleR(), this.trunc));
        var12.println("Critical Value for r:       " + Fmath.truncate(this.correlationCoefficientCriticalValue(), this.trunc));
        var12.println("Significance level used:    " + this.significance * 100.0D + "%");
        var12.println("Sum of squares:             " + Fmath.truncate(var3.getSumOfSquares(), this.trunc));
        var12.println();
        if (var4 > 0) {
            var12.println("Best Estimates of the Parameters");

            int var13;
            for(var13 = 0; var13 < var4; ++var13) {
                var12.print(this.methodParameters[var1][var13] + ": ", 8);
                var12.print(Fmath.truncate(var9[var13], this.trunc), 12);
                var12.println("'pseudo-linear' s.e. " + Fmath.truncate(var10[var13], this.trunc));
            }

            var12.println();
            var12.println("The 'pseudo-linear' s.e.s are a lower limit to the s.e. and may significantly underestimate the s.e.");
            var12.println("See documentation, http://www.ee.ucl.ac.uk/~mflanaga/java/ProbabilityPlot.html, for details");
            var12.println();
            var12.println("'Pseudo-linear' Parameter-Parameter Correlation Coefficients");
            var12.print(" ", 8);

            for(var13 = 0; var13 < var4; ++var13) {
                var12.print(this.methodParameters[var1][var13], 12);
            }

            var12.println();

            for(var13 = 0; var13 < var4; ++var13) {
                var12.print(this.methodParameters[var1][var13], 8);

                for(int var14 = 0; var14 < var4; ++var14) {
                    var12.print(Fmath.truncate(var11[var13][var14], this.trunc), 12);
                }

                var12.println();
            }
        } else if (var1 == 15) {
            var12.println("Degrees of freedom, nu1: " + this.fDistributionNu1);
            var12.println("Degrees of freedom, nu2: " + this.fDistributionNu2);
        }

        var12.close();
    }

    public String removeSpacesEtc(String var1) {
        int var2 = var1.length();
        String var3 = "";

        for(int var4 = 0; var4 < var2; ++var4) {
            char var5 = var1.charAt(var4);
            if (var5 != ' ' && var5 != '(' && var5 != ')' && var5 != '-') {
                var3 = var3 + var5;
            }
        }

        return var3;
    }

    public void setStartOfGraphTitle(String var1) {
        this.titleStart = var1;
    }
}
