//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package flanagan.analysis;

import flanagan.interpolation.CubicSpline;
import flanagan.io.Db;
import flanagan.io.FileOutput;
import flanagan.math.ArrayMaths;
import flanagan.math.Conv;
import flanagan.math.Fmath;
import flanagan.math.Matrix;
import flanagan.plot.PlotGraph;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Regression {
    protected int nData0 = 0;
    protected int nData = 0;
    protected double nEffective = 0.0D;
    protected int nXarrays = 1;
    protected int nYarrays = 1;
    protected int nParam = 0;
    protected int degreesOfFreedom = 0;
    protected double[][] xData = (double[][])null;
    protected double[][] xErrors = (double[][])null;
    protected boolean xErrorsEntered = false;
    protected double[] yData = null;
    protected double[] yErrors = null;
    protected boolean yErrorsEntered = false;
    protected boolean dualErrorsRequired = false;
    protected boolean trueErrors = true;
    protected double[] weight = null;
    protected double[] yCalc = null;
    protected double[] residual = null;
    protected double[] residualW = null;
    protected boolean weightOpt = false;
    protected int weightFlag = 0;
    protected String[] weightWord = new String[]{"", "Weighted "};
    protected double[] best = null;
    protected double[] bestSd = null;
    protected double[] pseudoSd = null;
    protected double[] tValues = null;
    protected double[] pValues = null;
    protected double fixedInterceptL = 0.0D;
    protected double fixedInterceptP = 0.0D;
    protected double yMean = 0.0D / 0.0;
    protected double yWeightedMean = 0.0D / 0.0;
    protected double chiSquare = 0.0D / 0.0;
    protected double reducedChiSquare = 0.0D / 0.0;
    protected double sumOfSquaresError = 0.0D / 0.0;
    protected double sumOfSquaresTotal = 0.0D / 0.0;
    protected double sumOfSquaresRegrn = 0.0D / 0.0;
    protected double lastSSnoConstraint = 0.0D;
    protected double[][] covar = (double[][])null;
    protected double[][] corrCoeff = (double[][])null;
    protected double xyR = 0.0D / 0.0;
    protected double yyR = 0.0D / 0.0;
    protected double multR = 0.0D / 0.0;
    protected double adjustedR = 0.0D / 0.0;
    protected double multipleF = 0.0D / 0.0;
    protected double multipleFprob = 0.0D / 0.0;
    protected String[] paraName = null;
    protected int prec = 4;
    protected int field = 13;
    protected int simplexFlag = 1;
    protected int derivFlag = 1;
    protected boolean nonLinStatsNeeded = true;
    protected int lastMethod = -1;
    protected int[] dualMethods = new int[]{19, 20, 21, 25, 26, 28, 39, 40, 41, 42, 43, 44, 45, 50, 51, 52};
    protected int nSpecDual;
    protected boolean bestPolyFlag;
    protected int bestPolynomialDegree;
    protected double fProbSignificance;
    protected ArrayList<Object> bestPolyArray;
    protected boolean bestPolyTooFewN;
    protected boolean userSupplied;
    protected double kayValue;
    protected boolean frechetWeibull;
    protected boolean linNonLin;
    protected boolean trueFreq;
    protected String xLegend;
    protected String yLegend;
    protected String graphTitle;
    protected String graphTitle2;
    protected boolean legendCheck;
    protected boolean suppressPrint;
    protected boolean suppressYYplot;
    protected boolean suppressErrorMessages;
    protected boolean nlrStatus;
    protected int scaleOpt;
    protected double[] scale;
    protected boolean zeroCheck;
    protected boolean penalty;
    protected boolean sumPenalty;
    protected int nConstraints;
    protected int nSumConstraints;
    protected int maxConstraintIndex;
    protected double constraintTolerance;
    protected ArrayList<Object> penalties;
    protected ArrayList<Object> sumPenalties;
    protected int[] penaltyCheck;
    protected int[] sumPenaltyCheck;
    protected double penaltyWeight;
    protected int[] penaltyParam;
    protected int[][] sumPenaltyParam;
    protected double[][] sumPlusOrMinus;
    protected int[] sumPenaltyNumber;
    protected double[] constraints;
    protected double[] sumConstraints;
    protected int constraintMethod;
    protected ArrayList<Object> constrainedSingle;
    protected ArrayList<Object> constrainedMultiple;
    String[] constraintString;
    protected boolean scaleFlag;
    protected double yScaleFactor;
    protected int nMax;
    protected int minIter;
    protected int nIter;
    protected int konvge;
    protected int kRestart;
    protected double fMin;
    protected double fTol;
    protected double rCoeff;
    protected double eCoeff;
    protected double cCoeff;
    protected double[] startH;
    protected double[] stepH;
    protected double[] startSH;
    protected double[] stepSH;
    protected double dStep;
    protected double[][] grad;
    protected double delta;
    protected boolean invertFlag;
    protected boolean posVarFlag;
    protected int minTest;
    protected double simplexSd;
    protected boolean statFlag;
    protected boolean plotOpt;
    protected boolean multipleY;
    protected boolean ignoreDofFcheck;
    protected double[] values;
    protected boolean[] fixed;
    protected int nGaussians;
    protected double[] multGaussFract;
    protected double[] multGaussFractErrors;
    protected double[] multGaussCoeffVar;
    protected double[] multGaussTvalue;
    protected double[] multGaussPvalue;
    protected double multGaussScale;
    protected double multGaussScaleError;
    protected double multGaussScaleCoeffVar;
    protected double multGaussScaleTvalue;
    protected double multGaussScalePvalue;
    protected boolean plotWindowCloseChoice;
    protected double minimumY;
    protected double minimumYindex;
    protected double maximumY;
    protected double maximumYindex;
    protected double bottom;
    protected double top;
    protected double bottomS;
    protected double bottomSindex;
    protected double topS;
    protected double topSindex;
    protected int midPointLowerIndex;
    protected int midPointUpperIndex;
    protected double midPointXvalue;
    protected double midPointYvalue;
    protected int directionFlag;
    protected double dDurbinWatson;
    protected boolean dDurbinWatsonDone;
    protected double[][] firstDerivs;
    protected boolean analyticalDerivative;
    protected double obsnVariance;
    protected static double histTol = 1.0001D;

    public Regression() {
        this.nSpecDual = this.dualMethods.length;
        this.bestPolyFlag = false;
        this.bestPolynomialDegree = 0;
        this.fProbSignificance = 0.05D;
        this.bestPolyArray = new ArrayList();
        this.bestPolyTooFewN = false;
        this.userSupplied = true;
        this.kayValue = 0.0D;
        this.frechetWeibull = true;
        this.linNonLin = true;
        this.trueFreq = false;
        this.xLegend = "x axis values";
        this.yLegend = "y axis values";
        this.graphTitle = " ";
        this.graphTitle2 = " ";
        this.legendCheck = false;
        this.suppressPrint = false;
        this.suppressYYplot = false;
        this.suppressErrorMessages = false;
        this.nlrStatus = true;
        this.scaleOpt = 0;
        this.scale = null;
        this.zeroCheck = false;
        this.penalty = false;
        this.sumPenalty = false;
        this.nConstraints = 0;
        this.nSumConstraints = 0;
        this.maxConstraintIndex = -1;
        this.constraintTolerance = 1.0E-4D;
        this.penalties = new ArrayList();
        this.sumPenalties = new ArrayList();
        this.penaltyCheck = null;
        this.sumPenaltyCheck = null;
        this.penaltyWeight = 1.0E30D;
        this.penaltyParam = null;
        this.sumPenaltyParam = (int[][])null;
        this.sumPlusOrMinus = (double[][])null;
        this.sumPenaltyNumber = null;
        this.constraints = null;
        this.sumConstraints = null;
        this.constraintMethod = 0;
        this.constrainedSingle = new ArrayList();
        this.constrainedMultiple = new ArrayList();
        this.constraintString = null;
        this.scaleFlag = true;
        this.yScaleFactor = 1.0D;
        this.nMax = 3000;
        this.minIter = 300;
        this.nIter = 0;
        this.konvge = 3;
        this.kRestart = 0;
        this.fMin = -1.0D;
        this.fTol = 1.0E-9D;
        this.rCoeff = 1.0D;
        this.eCoeff = 2.0D;
        this.cCoeff = 0.5D;
        this.startH = null;
        this.stepH = null;
        this.startSH = null;
        this.stepSH = null;
        this.dStep = 0.1D;
        this.grad = (double[][])null;
        this.delta = 1.0E-4D;
        this.invertFlag = true;
        this.posVarFlag = true;
        this.minTest = 0;
        this.simplexSd = 0.0D;
        this.statFlag = true;
        this.plotOpt = true;
        this.multipleY = false;
        this.ignoreDofFcheck = false;
        this.values = null;
        this.fixed = null;
        this.nGaussians = 0;
        this.multGaussFract = null;
        this.multGaussFractErrors = null;
        this.multGaussCoeffVar = null;
        this.multGaussTvalue = null;
        this.multGaussPvalue = null;
        this.multGaussScale = 1.0D;
        this.multGaussScaleError = 0.0D;
        this.multGaussScaleCoeffVar = 0.0D;
        this.multGaussScaleTvalue = 0.0D;
        this.multGaussScalePvalue = 0.0D;
        this.plotWindowCloseChoice = false;
        this.minimumY = 0.0D;
        this.minimumYindex = 0.0D;
        this.maximumY = 0.0D;
        this.maximumYindex = 0.0D;
        this.bottom = 0.0D;
        this.top = 0.0D;
        this.bottomS = 0.0D;
        this.bottomSindex = 0.0D;
        this.topS = 0.0D;
        this.topSindex = 0.0D;
        this.midPointLowerIndex = 0;
        this.midPointUpperIndex = 0;
        this.midPointXvalue = 0.0D;
        this.midPointYvalue = 0.0D;
        this.directionFlag = 0;
        this.dDurbinWatson = 0.0D / 0.0;
        this.dDurbinWatsonDone = false;
        this.firstDerivs = (double[][])null;
        this.analyticalDerivative = false;
        this.obsnVariance = 0.0D;
    }

    public Regression(double[][] var1, double[] var2, double[] var3) {
        this.nSpecDual = this.dualMethods.length;
        this.bestPolyFlag = false;
        this.bestPolynomialDegree = 0;
        this.fProbSignificance = 0.05D;
        this.bestPolyArray = new ArrayList();
        this.bestPolyTooFewN = false;
        this.userSupplied = true;
        this.kayValue = 0.0D;
        this.frechetWeibull = true;
        this.linNonLin = true;
        this.trueFreq = false;
        this.xLegend = "x axis values";
        this.yLegend = "y axis values";
        this.graphTitle = " ";
        this.graphTitle2 = " ";
        this.legendCheck = false;
        this.suppressPrint = false;
        this.suppressYYplot = false;
        this.suppressErrorMessages = false;
        this.nlrStatus = true;
        this.scaleOpt = 0;
        this.scale = null;
        this.zeroCheck = false;
        this.penalty = false;
        this.sumPenalty = false;
        this.nConstraints = 0;
        this.nSumConstraints = 0;
        this.maxConstraintIndex = -1;
        this.constraintTolerance = 1.0E-4D;
        this.penalties = new ArrayList();
        this.sumPenalties = new ArrayList();
        this.penaltyCheck = null;
        this.sumPenaltyCheck = null;
        this.penaltyWeight = 1.0E30D;
        this.penaltyParam = null;
        this.sumPenaltyParam = (int[][])null;
        this.sumPlusOrMinus = (double[][])null;
        this.sumPenaltyNumber = null;
        this.constraints = null;
        this.sumConstraints = null;
        this.constraintMethod = 0;
        this.constrainedSingle = new ArrayList();
        this.constrainedMultiple = new ArrayList();
        this.constraintString = null;
        this.scaleFlag = true;
        this.yScaleFactor = 1.0D;
        this.nMax = 3000;
        this.minIter = 300;
        this.nIter = 0;
        this.konvge = 3;
        this.kRestart = 0;
        this.fMin = -1.0D;
        this.fTol = 1.0E-9D;
        this.rCoeff = 1.0D;
        this.eCoeff = 2.0D;
        this.cCoeff = 0.5D;
        this.startH = null;
        this.stepH = null;
        this.startSH = null;
        this.stepSH = null;
        this.dStep = 0.1D;
        this.grad = (double[][])null;
        this.delta = 1.0E-4D;
        this.invertFlag = true;
        this.posVarFlag = true;
        this.minTest = 0;
        this.simplexSd = 0.0D;
        this.statFlag = true;
        this.plotOpt = true;
        this.multipleY = false;
        this.ignoreDofFcheck = false;
        this.values = null;
        this.fixed = null;
        this.nGaussians = 0;
        this.multGaussFract = null;
        this.multGaussFractErrors = null;
        this.multGaussCoeffVar = null;
        this.multGaussTvalue = null;
        this.multGaussPvalue = null;
        this.multGaussScale = 1.0D;
        this.multGaussScaleError = 0.0D;
        this.multGaussScaleCoeffVar = 0.0D;
        this.multGaussScaleTvalue = 0.0D;
        this.multGaussScalePvalue = 0.0D;
        this.plotWindowCloseChoice = false;
        this.minimumY = 0.0D;
        this.minimumYindex = 0.0D;
        this.maximumY = 0.0D;
        this.maximumYindex = 0.0D;
        this.bottom = 0.0D;
        this.top = 0.0D;
        this.bottomS = 0.0D;
        this.bottomSindex = 0.0D;
        this.topS = 0.0D;
        this.topSindex = 0.0D;
        this.midPointLowerIndex = 0;
        this.midPointUpperIndex = 0;
        this.midPointXvalue = 0.0D;
        this.midPointYvalue = 0.0D;
        this.directionFlag = 0;
        this.dDurbinWatson = 0.0D / 0.0;
        this.dDurbinWatsonDone = false;
        this.firstDerivs = (double[][])null;
        this.analyticalDerivative = false;
        this.obsnVariance = 0.0D;
        this.simplexFlag = 1;
        this.nData0 = var2.length;
        var3 = this.checkForZeroWeights(var3);
        if (this.weightOpt) {
            this.weightFlag = 1;
        }

        this.setDefaultValues(Conv.copy(var1), Conv.copy(var2), Conv.copy(var3));
    }

    public Regression(double[][] var1, double[] var2, double[][] var3, double[] var4) {
        this.nSpecDual = this.dualMethods.length;
        this.bestPolyFlag = false;
        this.bestPolynomialDegree = 0;
        this.fProbSignificance = 0.05D;
        this.bestPolyArray = new ArrayList();
        this.bestPolyTooFewN = false;
        this.userSupplied = true;
        this.kayValue = 0.0D;
        this.frechetWeibull = true;
        this.linNonLin = true;
        this.trueFreq = false;
        this.xLegend = "x axis values";
        this.yLegend = "y axis values";
        this.graphTitle = " ";
        this.graphTitle2 = " ";
        this.legendCheck = false;
        this.suppressPrint = false;
        this.suppressYYplot = false;
        this.suppressErrorMessages = false;
        this.nlrStatus = true;
        this.scaleOpt = 0;
        this.scale = null;
        this.zeroCheck = false;
        this.penalty = false;
        this.sumPenalty = false;
        this.nConstraints = 0;
        this.nSumConstraints = 0;
        this.maxConstraintIndex = -1;
        this.constraintTolerance = 1.0E-4D;
        this.penalties = new ArrayList();
        this.sumPenalties = new ArrayList();
        this.penaltyCheck = null;
        this.sumPenaltyCheck = null;
        this.penaltyWeight = 1.0E30D;
        this.penaltyParam = null;
        this.sumPenaltyParam = (int[][])null;
        this.sumPlusOrMinus = (double[][])null;
        this.sumPenaltyNumber = null;
        this.constraints = null;
        this.sumConstraints = null;
        this.constraintMethod = 0;
        this.constrainedSingle = new ArrayList();
        this.constrainedMultiple = new ArrayList();
        this.constraintString = null;
        this.scaleFlag = true;
        this.yScaleFactor = 1.0D;
        this.nMax = 3000;
        this.minIter = 300;
        this.nIter = 0;
        this.konvge = 3;
        this.kRestart = 0;
        this.fMin = -1.0D;
        this.fTol = 1.0E-9D;
        this.rCoeff = 1.0D;
        this.eCoeff = 2.0D;
        this.cCoeff = 0.5D;
        this.startH = null;
        this.stepH = null;
        this.startSH = null;
        this.stepSH = null;
        this.dStep = 0.1D;
        this.grad = (double[][])null;
        this.delta = 1.0E-4D;
        this.invertFlag = true;
        this.posVarFlag = true;
        this.minTest = 0;
        this.simplexSd = 0.0D;
        this.statFlag = true;
        this.plotOpt = true;
        this.multipleY = false;
        this.ignoreDofFcheck = false;
        this.values = null;
        this.fixed = null;
        this.nGaussians = 0;
        this.multGaussFract = null;
        this.multGaussFractErrors = null;
        this.multGaussCoeffVar = null;
        this.multGaussTvalue = null;
        this.multGaussPvalue = null;
        this.multGaussScale = 1.0D;
        this.multGaussScaleError = 0.0D;
        this.multGaussScaleCoeffVar = 0.0D;
        this.multGaussScaleTvalue = 0.0D;
        this.multGaussScalePvalue = 0.0D;
        this.plotWindowCloseChoice = false;
        this.minimumY = 0.0D;
        this.minimumYindex = 0.0D;
        this.maximumY = 0.0D;
        this.maximumYindex = 0.0D;
        this.bottom = 0.0D;
        this.top = 0.0D;
        this.bottomS = 0.0D;
        this.bottomSindex = 0.0D;
        this.topS = 0.0D;
        this.topSindex = 0.0D;
        this.midPointLowerIndex = 0;
        this.midPointUpperIndex = 0;
        this.midPointXvalue = 0.0D;
        this.midPointYvalue = 0.0D;
        this.directionFlag = 0;
        this.dDurbinWatson = 0.0D / 0.0;
        this.dDurbinWatsonDone = false;
        this.firstDerivs = (double[][])null;
        this.analyticalDerivative = false;
        this.obsnVariance = 0.0D;
        this.simplexFlag = 3;
        this.nData0 = var2.length;
        this.jointZeroCheck(var3, var4);
        this.yErrorsEntered = true;
        this.xErrorsEntered = true;
        this.weightOpt = true;
        this.weightFlag = 1;
        this.setDefaultValues(Conv.copy(var1), Conv.copy(var2), Conv.copy(var3), Conv.copy(var4));
    }

    public Regression(double[][] var1, double[][] var2, double[][] var3) {
        this.nSpecDual = this.dualMethods.length;
        this.bestPolyFlag = false;
        this.bestPolynomialDegree = 0;
        this.fProbSignificance = 0.05D;
        this.bestPolyArray = new ArrayList();
        this.bestPolyTooFewN = false;
        this.userSupplied = true;
        this.kayValue = 0.0D;
        this.frechetWeibull = true;
        this.linNonLin = true;
        this.trueFreq = false;
        this.xLegend = "x axis values";
        this.yLegend = "y axis values";
        this.graphTitle = " ";
        this.graphTitle2 = " ";
        this.legendCheck = false;
        this.suppressPrint = false;
        this.suppressYYplot = false;
        this.suppressErrorMessages = false;
        this.nlrStatus = true;
        this.scaleOpt = 0;
        this.scale = null;
        this.zeroCheck = false;
        this.penalty = false;
        this.sumPenalty = false;
        this.nConstraints = 0;
        this.nSumConstraints = 0;
        this.maxConstraintIndex = -1;
        this.constraintTolerance = 1.0E-4D;
        this.penalties = new ArrayList();
        this.sumPenalties = new ArrayList();
        this.penaltyCheck = null;
        this.sumPenaltyCheck = null;
        this.penaltyWeight = 1.0E30D;
        this.penaltyParam = null;
        this.sumPenaltyParam = (int[][])null;
        this.sumPlusOrMinus = (double[][])null;
        this.sumPenaltyNumber = null;
        this.constraints = null;
        this.sumConstraints = null;
        this.constraintMethod = 0;
        this.constrainedSingle = new ArrayList();
        this.constrainedMultiple = new ArrayList();
        this.constraintString = null;
        this.scaleFlag = true;
        this.yScaleFactor = 1.0D;
        this.nMax = 3000;
        this.minIter = 300;
        this.nIter = 0;
        this.konvge = 3;
        this.kRestart = 0;
        this.fMin = -1.0D;
        this.fTol = 1.0E-9D;
        this.rCoeff = 1.0D;
        this.eCoeff = 2.0D;
        this.cCoeff = 0.5D;
        this.startH = null;
        this.stepH = null;
        this.startSH = null;
        this.stepSH = null;
        this.dStep = 0.1D;
        this.grad = (double[][])null;
        this.delta = 1.0E-4D;
        this.invertFlag = true;
        this.posVarFlag = true;
        this.minTest = 0;
        this.simplexSd = 0.0D;
        this.statFlag = true;
        this.plotOpt = true;
        this.multipleY = false;
        this.ignoreDofFcheck = false;
        this.values = null;
        this.fixed = null;
        this.nGaussians = 0;
        this.multGaussFract = null;
        this.multGaussFractErrors = null;
        this.multGaussCoeffVar = null;
        this.multGaussTvalue = null;
        this.multGaussPvalue = null;
        this.multGaussScale = 1.0D;
        this.multGaussScaleError = 0.0D;
        this.multGaussScaleCoeffVar = 0.0D;
        this.multGaussScaleTvalue = 0.0D;
        this.multGaussScalePvalue = 0.0D;
        this.plotWindowCloseChoice = false;
        this.minimumY = 0.0D;
        this.minimumYindex = 0.0D;
        this.maximumY = 0.0D;
        this.maximumYindex = 0.0D;
        this.bottom = 0.0D;
        this.top = 0.0D;
        this.bottomS = 0.0D;
        this.bottomSindex = 0.0D;
        this.topS = 0.0D;
        this.topSindex = 0.0D;
        this.midPointLowerIndex = 0;
        this.midPointUpperIndex = 0;
        this.midPointXvalue = 0.0D;
        this.midPointYvalue = 0.0D;
        this.directionFlag = 0;
        this.dDurbinWatson = 0.0D / 0.0;
        this.dDurbinWatsonDone = false;
        this.firstDerivs = (double[][])null;
        this.analyticalDerivative = false;
        this.obsnVariance = 0.0D;
        this.simplexFlag = 2;
        this.multipleY = true;
        int var4 = var2.length;
        this.nYarrays = var4;
        int var5 = var2[0].length;
        this.nData0 = var5;
        int var6 = var1.length;
        int var7 = var1[0].length;
        double[] var8 = new double[var4 * var5];
        double[] var9 = new double[var4 * var5];
        double[][] var10 = new double[var4 * var5][var6];
        int var11 = 0;

        for(int var12 = 0; var12 < var4; ++var12) {
            int var13 = var2[var12].length;
            if (var13 != var5) {
                throw new IllegalArgumentException("multiple y arrays must be of the same length");
            }

            int var14 = var1[var12].length;
            if (var13 != var14) {
                throw new IllegalArgumentException("multiple y arrays must be of the same length as the x array length");
            }

            for(int var15 = 0; var15 < var5; ++var15) {
                var8[var11] = var2[var12][var15];
                var10[var11][var12] = var1[var12][var15];
                var9[var11] = var3[var12][var15];
                ++var11;
            }
        }

        var9 = this.checkForZeroWeights(var9);
        if (this.weightOpt) {
            this.weightFlag = 1;
        }

        this.setDefaultValues(var10, var8, var9);
    }

    public Regression(double[][] var1, double[][] var2, double[][] var3, double[][] var4) {
        this.nSpecDual = this.dualMethods.length;
        this.bestPolyFlag = false;
        this.bestPolynomialDegree = 0;
        this.fProbSignificance = 0.05D;
        this.bestPolyArray = new ArrayList();
        this.bestPolyTooFewN = false;
        this.userSupplied = true;
        this.kayValue = 0.0D;
        this.frechetWeibull = true;
        this.linNonLin = true;
        this.trueFreq = false;
        this.xLegend = "x axis values";
        this.yLegend = "y axis values";
        this.graphTitle = " ";
        this.graphTitle2 = " ";
        this.legendCheck = false;
        this.suppressPrint = false;
        this.suppressYYplot = false;
        this.suppressErrorMessages = false;
        this.nlrStatus = true;
        this.scaleOpt = 0;
        this.scale = null;
        this.zeroCheck = false;
        this.penalty = false;
        this.sumPenalty = false;
        this.nConstraints = 0;
        this.nSumConstraints = 0;
        this.maxConstraintIndex = -1;
        this.constraintTolerance = 1.0E-4D;
        this.penalties = new ArrayList();
        this.sumPenalties = new ArrayList();
        this.penaltyCheck = null;
        this.sumPenaltyCheck = null;
        this.penaltyWeight = 1.0E30D;
        this.penaltyParam = null;
        this.sumPenaltyParam = (int[][])null;
        this.sumPlusOrMinus = (double[][])null;
        this.sumPenaltyNumber = null;
        this.constraints = null;
        this.sumConstraints = null;
        this.constraintMethod = 0;
        this.constrainedSingle = new ArrayList();
        this.constrainedMultiple = new ArrayList();
        this.constraintString = null;
        this.scaleFlag = true;
        this.yScaleFactor = 1.0D;
        this.nMax = 3000;
        this.minIter = 300;
        this.nIter = 0;
        this.konvge = 3;
        this.kRestart = 0;
        this.fMin = -1.0D;
        this.fTol = 1.0E-9D;
        this.rCoeff = 1.0D;
        this.eCoeff = 2.0D;
        this.cCoeff = 0.5D;
        this.startH = null;
        this.stepH = null;
        this.startSH = null;
        this.stepSH = null;
        this.dStep = 0.1D;
        this.grad = (double[][])null;
        this.delta = 1.0E-4D;
        this.invertFlag = true;
        this.posVarFlag = true;
        this.minTest = 0;
        this.simplexSd = 0.0D;
        this.statFlag = true;
        this.plotOpt = true;
        this.multipleY = false;
        this.ignoreDofFcheck = false;
        this.values = null;
        this.fixed = null;
        this.nGaussians = 0;
        this.multGaussFract = null;
        this.multGaussFractErrors = null;
        this.multGaussCoeffVar = null;
        this.multGaussTvalue = null;
        this.multGaussPvalue = null;
        this.multGaussScale = 1.0D;
        this.multGaussScaleError = 0.0D;
        this.multGaussScaleCoeffVar = 0.0D;
        this.multGaussScaleTvalue = 0.0D;
        this.multGaussScalePvalue = 0.0D;
        this.plotWindowCloseChoice = false;
        this.minimumY = 0.0D;
        this.minimumYindex = 0.0D;
        this.maximumY = 0.0D;
        this.maximumYindex = 0.0D;
        this.bottom = 0.0D;
        this.top = 0.0D;
        this.bottomS = 0.0D;
        this.bottomSindex = 0.0D;
        this.topS = 0.0D;
        this.topSindex = 0.0D;
        this.midPointLowerIndex = 0;
        this.midPointUpperIndex = 0;
        this.midPointXvalue = 0.0D;
        this.midPointYvalue = 0.0D;
        this.directionFlag = 0;
        this.dDurbinWatson = 0.0D / 0.0;
        this.dDurbinWatsonDone = false;
        this.firstDerivs = (double[][])null;
        this.analyticalDerivative = false;
        this.obsnVariance = 0.0D;
        this.simplexFlag = 4;
        this.multipleY = true;
        int var5 = var2.length;
        this.nYarrays = var5;
        int var6 = var2[0].length;
        this.nData0 = var6;
        int var7 = var1.length;
        int var8 = var1[0].length;
        double[] var9 = new double[var5 * var6];
        double[] var10 = new double[var5 * var6];
        double[][] var11 = new double[var5 * var6][var7];
        double[][] var12 = new double[var5 * var6][var7];
        int var13 = 0;

        for(int var14 = 0; var14 < var5; ++var14) {
            int var15 = var2[var14].length;
            if (var15 != var6) {
                throw new IllegalArgumentException("multiple y arrays must be of the same length");
            }

            int var16 = var1[var14].length;
            if (var15 != var16) {
                throw new IllegalArgumentException("multiple y arrays must be of the same length as the x array length");
            }

            for(int var17 = 0; var17 < var6; ++var17) {
                var9[var13] = var2[var14][var17];
                var11[var13][var14] = var1[var14][var17];
                var10[var13] = var4[var14][var17];
                var12[var13][var14] = var3[var14][var17];
                ++var13;
            }
        }

        this.jointZeroCheck(var12, var10);
        this.yErrorsEntered = true;
        this.xErrorsEntered = true;
        this.weightOpt = true;
        this.weightFlag = 1;
        this.setDefaultValues(Conv.copy(var11), Conv.copy(var9), Conv.copy(var12), Conv.copy(var10));
        this.setDefaultValues(var11, var9, var10);
    }

    public Regression(double[] var1, double[] var2, double[] var3) {
        this.nSpecDual = this.dualMethods.length;
        this.bestPolyFlag = false;
        this.bestPolynomialDegree = 0;
        this.fProbSignificance = 0.05D;
        this.bestPolyArray = new ArrayList();
        this.bestPolyTooFewN = false;
        this.userSupplied = true;
        this.kayValue = 0.0D;
        this.frechetWeibull = true;
        this.linNonLin = true;
        this.trueFreq = false;
        this.xLegend = "x axis values";
        this.yLegend = "y axis values";
        this.graphTitle = " ";
        this.graphTitle2 = " ";
        this.legendCheck = false;
        this.suppressPrint = false;
        this.suppressYYplot = false;
        this.suppressErrorMessages = false;
        this.nlrStatus = true;
        this.scaleOpt = 0;
        this.scale = null;
        this.zeroCheck = false;
        this.penalty = false;
        this.sumPenalty = false;
        this.nConstraints = 0;
        this.nSumConstraints = 0;
        this.maxConstraintIndex = -1;
        this.constraintTolerance = 1.0E-4D;
        this.penalties = new ArrayList();
        this.sumPenalties = new ArrayList();
        this.penaltyCheck = null;
        this.sumPenaltyCheck = null;
        this.penaltyWeight = 1.0E30D;
        this.penaltyParam = null;
        this.sumPenaltyParam = (int[][])null;
        this.sumPlusOrMinus = (double[][])null;
        this.sumPenaltyNumber = null;
        this.constraints = null;
        this.sumConstraints = null;
        this.constraintMethod = 0;
        this.constrainedSingle = new ArrayList();
        this.constrainedMultiple = new ArrayList();
        this.constraintString = null;
        this.scaleFlag = true;
        this.yScaleFactor = 1.0D;
        this.nMax = 3000;
        this.minIter = 300;
        this.nIter = 0;
        this.konvge = 3;
        this.kRestart = 0;
        this.fMin = -1.0D;
        this.fTol = 1.0E-9D;
        this.rCoeff = 1.0D;
        this.eCoeff = 2.0D;
        this.cCoeff = 0.5D;
        this.startH = null;
        this.stepH = null;
        this.startSH = null;
        this.stepSH = null;
        this.dStep = 0.1D;
        this.grad = (double[][])null;
        this.delta = 1.0E-4D;
        this.invertFlag = true;
        this.posVarFlag = true;
        this.minTest = 0;
        this.simplexSd = 0.0D;
        this.statFlag = true;
        this.plotOpt = true;
        this.multipleY = false;
        this.ignoreDofFcheck = false;
        this.values = null;
        this.fixed = null;
        this.nGaussians = 0;
        this.multGaussFract = null;
        this.multGaussFractErrors = null;
        this.multGaussCoeffVar = null;
        this.multGaussTvalue = null;
        this.multGaussPvalue = null;
        this.multGaussScale = 1.0D;
        this.multGaussScaleError = 0.0D;
        this.multGaussScaleCoeffVar = 0.0D;
        this.multGaussScaleTvalue = 0.0D;
        this.multGaussScalePvalue = 0.0D;
        this.plotWindowCloseChoice = false;
        this.minimumY = 0.0D;
        this.minimumYindex = 0.0D;
        this.maximumY = 0.0D;
        this.maximumYindex = 0.0D;
        this.bottom = 0.0D;
        this.top = 0.0D;
        this.bottomS = 0.0D;
        this.bottomSindex = 0.0D;
        this.topS = 0.0D;
        this.topSindex = 0.0D;
        this.midPointLowerIndex = 0;
        this.midPointUpperIndex = 0;
        this.midPointXvalue = 0.0D;
        this.midPointYvalue = 0.0D;
        this.directionFlag = 0;
        this.dDurbinWatson = 0.0D / 0.0;
        this.dDurbinWatsonDone = false;
        this.firstDerivs = (double[][])null;
        this.analyticalDerivative = false;
        this.obsnVariance = 0.0D;
        this.simplexFlag = 1;
        this.nData0 = var2.length;
        int var4 = var1.length;
        int var5 = var3.length;
        double[][] var6 = new double[1][var4];

        for(int var7 = 0; var7 < var4; ++var7) {
            var6[0][var7] = var1[var7];
        }

        var3 = this.checkForZeroWeights(var3);
        if (this.weightOpt) {
            this.weightFlag = 1;
        }

        this.setDefaultValues(Conv.copy(var6), Conv.copy(var2), Conv.copy(var3));
    }

    public Regression(double[] var1, double[] var2, double[] var3, double[] var4) {
        this.nSpecDual = this.dualMethods.length;
        this.bestPolyFlag = false;
        this.bestPolynomialDegree = 0;
        this.fProbSignificance = 0.05D;
        this.bestPolyArray = new ArrayList();
        this.bestPolyTooFewN = false;
        this.userSupplied = true;
        this.kayValue = 0.0D;
        this.frechetWeibull = true;
        this.linNonLin = true;
        this.trueFreq = false;
        this.xLegend = "x axis values";
        this.yLegend = "y axis values";
        this.graphTitle = " ";
        this.graphTitle2 = " ";
        this.legendCheck = false;
        this.suppressPrint = false;
        this.suppressYYplot = false;
        this.suppressErrorMessages = false;
        this.nlrStatus = true;
        this.scaleOpt = 0;
        this.scale = null;
        this.zeroCheck = false;
        this.penalty = false;
        this.sumPenalty = false;
        this.nConstraints = 0;
        this.nSumConstraints = 0;
        this.maxConstraintIndex = -1;
        this.constraintTolerance = 1.0E-4D;
        this.penalties = new ArrayList();
        this.sumPenalties = new ArrayList();
        this.penaltyCheck = null;
        this.sumPenaltyCheck = null;
        this.penaltyWeight = 1.0E30D;
        this.penaltyParam = null;
        this.sumPenaltyParam = (int[][])null;
        this.sumPlusOrMinus = (double[][])null;
        this.sumPenaltyNumber = null;
        this.constraints = null;
        this.sumConstraints = null;
        this.constraintMethod = 0;
        this.constrainedSingle = new ArrayList();
        this.constrainedMultiple = new ArrayList();
        this.constraintString = null;
        this.scaleFlag = true;
        this.yScaleFactor = 1.0D;
        this.nMax = 3000;
        this.minIter = 300;
        this.nIter = 0;
        this.konvge = 3;
        this.kRestart = 0;
        this.fMin = -1.0D;
        this.fTol = 1.0E-9D;
        this.rCoeff = 1.0D;
        this.eCoeff = 2.0D;
        this.cCoeff = 0.5D;
        this.startH = null;
        this.stepH = null;
        this.startSH = null;
        this.stepSH = null;
        this.dStep = 0.1D;
        this.grad = (double[][])null;
        this.delta = 1.0E-4D;
        this.invertFlag = true;
        this.posVarFlag = true;
        this.minTest = 0;
        this.simplexSd = 0.0D;
        this.statFlag = true;
        this.plotOpt = true;
        this.multipleY = false;
        this.ignoreDofFcheck = false;
        this.values = null;
        this.fixed = null;
        this.nGaussians = 0;
        this.multGaussFract = null;
        this.multGaussFractErrors = null;
        this.multGaussCoeffVar = null;
        this.multGaussTvalue = null;
        this.multGaussPvalue = null;
        this.multGaussScale = 1.0D;
        this.multGaussScaleError = 0.0D;
        this.multGaussScaleCoeffVar = 0.0D;
        this.multGaussScaleTvalue = 0.0D;
        this.multGaussScalePvalue = 0.0D;
        this.plotWindowCloseChoice = false;
        this.minimumY = 0.0D;
        this.minimumYindex = 0.0D;
        this.maximumY = 0.0D;
        this.maximumYindex = 0.0D;
        this.bottom = 0.0D;
        this.top = 0.0D;
        this.bottomS = 0.0D;
        this.bottomSindex = 0.0D;
        this.topS = 0.0D;
        this.topSindex = 0.0D;
        this.midPointLowerIndex = 0;
        this.midPointUpperIndex = 0;
        this.midPointXvalue = 0.0D;
        this.midPointYvalue = 0.0D;
        this.directionFlag = 0;
        this.dDurbinWatson = 0.0D / 0.0;
        this.dDurbinWatsonDone = false;
        this.firstDerivs = (double[][])null;
        this.analyticalDerivative = false;
        this.obsnVariance = 0.0D;
        this.simplexFlag = 3;
        this.nData0 = var2.length;
        int var5 = var1.length;
        int var6 = var4.length;
        double[][] var7 = new double[1][var5];
        double[][] var8 = new double[1][var5];

        for(int var9 = 0; var9 < var5; ++var9) {
            var7[0][var9] = var1[var9];
            var8[0][var9] = var3[var9];
        }

        this.jointZeroCheck(var8, var4);
        this.xErrorsEntered = true;
        this.yErrorsEntered = true;
        this.weightOpt = true;
        this.weightFlag = 1;
        this.setDefaultValues(Conv.copy(var7), Conv.copy(var2), Conv.copy(var8), Conv.copy(var4));
    }

    public Regression(double[] var1, double[][] var2, double[][] var3) {
        this.nSpecDual = this.dualMethods.length;
        this.bestPolyFlag = false;
        this.bestPolynomialDegree = 0;
        this.fProbSignificance = 0.05D;
        this.bestPolyArray = new ArrayList();
        this.bestPolyTooFewN = false;
        this.userSupplied = true;
        this.kayValue = 0.0D;
        this.frechetWeibull = true;
        this.linNonLin = true;
        this.trueFreq = false;
        this.xLegend = "x axis values";
        this.yLegend = "y axis values";
        this.graphTitle = " ";
        this.graphTitle2 = " ";
        this.legendCheck = false;
        this.suppressPrint = false;
        this.suppressYYplot = false;
        this.suppressErrorMessages = false;
        this.nlrStatus = true;
        this.scaleOpt = 0;
        this.scale = null;
        this.zeroCheck = false;
        this.penalty = false;
        this.sumPenalty = false;
        this.nConstraints = 0;
        this.nSumConstraints = 0;
        this.maxConstraintIndex = -1;
        this.constraintTolerance = 1.0E-4D;
        this.penalties = new ArrayList();
        this.sumPenalties = new ArrayList();
        this.penaltyCheck = null;
        this.sumPenaltyCheck = null;
        this.penaltyWeight = 1.0E30D;
        this.penaltyParam = null;
        this.sumPenaltyParam = (int[][])null;
        this.sumPlusOrMinus = (double[][])null;
        this.sumPenaltyNumber = null;
        this.constraints = null;
        this.sumConstraints = null;
        this.constraintMethod = 0;
        this.constrainedSingle = new ArrayList();
        this.constrainedMultiple = new ArrayList();
        this.constraintString = null;
        this.scaleFlag = true;
        this.yScaleFactor = 1.0D;
        this.nMax = 3000;
        this.minIter = 300;
        this.nIter = 0;
        this.konvge = 3;
        this.kRestart = 0;
        this.fMin = -1.0D;
        this.fTol = 1.0E-9D;
        this.rCoeff = 1.0D;
        this.eCoeff = 2.0D;
        this.cCoeff = 0.5D;
        this.startH = null;
        this.stepH = null;
        this.startSH = null;
        this.stepSH = null;
        this.dStep = 0.1D;
        this.grad = (double[][])null;
        this.delta = 1.0E-4D;
        this.invertFlag = true;
        this.posVarFlag = true;
        this.minTest = 0;
        this.simplexSd = 0.0D;
        this.statFlag = true;
        this.plotOpt = true;
        this.multipleY = false;
        this.ignoreDofFcheck = false;
        this.values = null;
        this.fixed = null;
        this.nGaussians = 0;
        this.multGaussFract = null;
        this.multGaussFractErrors = null;
        this.multGaussCoeffVar = null;
        this.multGaussTvalue = null;
        this.multGaussPvalue = null;
        this.multGaussScale = 1.0D;
        this.multGaussScaleError = 0.0D;
        this.multGaussScaleCoeffVar = 0.0D;
        this.multGaussScaleTvalue = 0.0D;
        this.multGaussScalePvalue = 0.0D;
        this.plotWindowCloseChoice = false;
        this.minimumY = 0.0D;
        this.minimumYindex = 0.0D;
        this.maximumY = 0.0D;
        this.maximumYindex = 0.0D;
        this.bottom = 0.0D;
        this.top = 0.0D;
        this.bottomS = 0.0D;
        this.bottomSindex = 0.0D;
        this.topS = 0.0D;
        this.topSindex = 0.0D;
        this.midPointLowerIndex = 0;
        this.midPointUpperIndex = 0;
        this.midPointXvalue = 0.0D;
        this.midPointYvalue = 0.0D;
        this.directionFlag = 0;
        this.dDurbinWatson = 0.0D / 0.0;
        this.dDurbinWatsonDone = false;
        this.firstDerivs = (double[][])null;
        this.analyticalDerivative = false;
        this.obsnVariance = 0.0D;
        this.simplexFlag = 1;
        this.multipleY = true;
        int var4 = var2.length;
        this.nYarrays = var4;
        int var5 = var2[0].length;
        this.nData0 = var5;
        double[] var6 = new double[var4 * var5];
        double[] var7 = new double[var4 * var5];
        int var8 = 0;

        int var9;
        int var11;
        for(var9 = 0; var9 < var4; ++var9) {
            int var10 = var2[var9].length;
            if (var10 != var5) {
                throw new IllegalArgumentException("multiple y arrays must be of the same length");
            }

            for(var11 = 0; var11 < var5; ++var11) {
                var6[var8] = var2[var9][var11];
                var7[var8] = var3[var9][var11];
                ++var8;
            }
        }

        var9 = var1.length;
        if (var9 != var5) {
            throw new IllegalArgumentException("x and y data lengths must be the same");
        } else {
            double[][] var13 = new double[1][var4 * var9];
            var8 = 0;

            for(var11 = 0; var11 < var4; ++var11) {
                for(int var12 = 0; var12 < var9; ++var12) {
                    var13[0][var8] = var1[var12];
                    ++var8;
                }
            }

            var7 = this.checkForZeroWeights(var7);
            if (this.weightOpt) {
                this.weightFlag = 1;
            }

            this.setDefaultValues(var13, var6, var7);
        }
    }

    public Regression(double[] var1, double[][] var2, double[] var3, double[][] var4) {
        this.nSpecDual = this.dualMethods.length;
        this.bestPolyFlag = false;
        this.bestPolynomialDegree = 0;
        this.fProbSignificance = 0.05D;
        this.bestPolyArray = new ArrayList();
        this.bestPolyTooFewN = false;
        this.userSupplied = true;
        this.kayValue = 0.0D;
        this.frechetWeibull = true;
        this.linNonLin = true;
        this.trueFreq = false;
        this.xLegend = "x axis values";
        this.yLegend = "y axis values";
        this.graphTitle = " ";
        this.graphTitle2 = " ";
        this.legendCheck = false;
        this.suppressPrint = false;
        this.suppressYYplot = false;
        this.suppressErrorMessages = false;
        this.nlrStatus = true;
        this.scaleOpt = 0;
        this.scale = null;
        this.zeroCheck = false;
        this.penalty = false;
        this.sumPenalty = false;
        this.nConstraints = 0;
        this.nSumConstraints = 0;
        this.maxConstraintIndex = -1;
        this.constraintTolerance = 1.0E-4D;
        this.penalties = new ArrayList();
        this.sumPenalties = new ArrayList();
        this.penaltyCheck = null;
        this.sumPenaltyCheck = null;
        this.penaltyWeight = 1.0E30D;
        this.penaltyParam = null;
        this.sumPenaltyParam = (int[][])null;
        this.sumPlusOrMinus = (double[][])null;
        this.sumPenaltyNumber = null;
        this.constraints = null;
        this.sumConstraints = null;
        this.constraintMethod = 0;
        this.constrainedSingle = new ArrayList();
        this.constrainedMultiple = new ArrayList();
        this.constraintString = null;
        this.scaleFlag = true;
        this.yScaleFactor = 1.0D;
        this.nMax = 3000;
        this.minIter = 300;
        this.nIter = 0;
        this.konvge = 3;
        this.kRestart = 0;
        this.fMin = -1.0D;
        this.fTol = 1.0E-9D;
        this.rCoeff = 1.0D;
        this.eCoeff = 2.0D;
        this.cCoeff = 0.5D;
        this.startH = null;
        this.stepH = null;
        this.startSH = null;
        this.stepSH = null;
        this.dStep = 0.1D;
        this.grad = (double[][])null;
        this.delta = 1.0E-4D;
        this.invertFlag = true;
        this.posVarFlag = true;
        this.minTest = 0;
        this.simplexSd = 0.0D;
        this.statFlag = true;
        this.plotOpt = true;
        this.multipleY = false;
        this.ignoreDofFcheck = false;
        this.values = null;
        this.fixed = null;
        this.nGaussians = 0;
        this.multGaussFract = null;
        this.multGaussFractErrors = null;
        this.multGaussCoeffVar = null;
        this.multGaussTvalue = null;
        this.multGaussPvalue = null;
        this.multGaussScale = 1.0D;
        this.multGaussScaleError = 0.0D;
        this.multGaussScaleCoeffVar = 0.0D;
        this.multGaussScaleTvalue = 0.0D;
        this.multGaussScalePvalue = 0.0D;
        this.plotWindowCloseChoice = false;
        this.minimumY = 0.0D;
        this.minimumYindex = 0.0D;
        this.maximumY = 0.0D;
        this.maximumYindex = 0.0D;
        this.bottom = 0.0D;
        this.top = 0.0D;
        this.bottomS = 0.0D;
        this.bottomSindex = 0.0D;
        this.topS = 0.0D;
        this.topSindex = 0.0D;
        this.midPointLowerIndex = 0;
        this.midPointUpperIndex = 0;
        this.midPointXvalue = 0.0D;
        this.midPointYvalue = 0.0D;
        this.directionFlag = 0;
        this.dDurbinWatson = 0.0D / 0.0;
        this.dDurbinWatsonDone = false;
        this.firstDerivs = (double[][])null;
        this.analyticalDerivative = false;
        this.obsnVariance = 0.0D;
        this.simplexFlag = 4;
        this.multipleY = true;
        int var5 = var2.length;
        this.nYarrays = var5;
        int var6 = var2[0].length;
        this.nData0 = var6;
        double[] var7 = new double[var5 * var6];
        double[] var8 = new double[var5 * var6];
        int var9 = 0;

        int var10;
        for(var10 = 0; var10 < var5; ++var10) {
            int var11 = var2[var10].length;
            if (var11 != var6) {
                throw new IllegalArgumentException("multiple y arrays must be of the same length");
            }

            for(int var12 = 0; var12 < var6; ++var12) {
                var7[var9] = var2[var10][var12];
                var8[var9] = var4[var10][var12];
                ++var9;
            }
        }

        var10 = var1.length;
        if (var10 != var6) {
            throw new IllegalArgumentException("x and y data lengths must be the same");
        } else {
            double[][] var15 = new double[1][var5 * var10];
            double[][] var16 = new double[1][var5 * var10];
            var9 = 0;

            for(int var13 = 0; var13 < var5; ++var13) {
                for(int var14 = 0; var14 < var10; ++var14) {
                    var15[0][var9] = var1[var14];
                    var16[0][var9] = var3[var14];
                    ++var9;
                }
            }

            this.jointZeroCheck(var16, var8);
            this.xErrorsEntered = true;
            this.yErrorsEntered = true;
            this.weightOpt = true;
            this.weightFlag = 1;
            this.setDefaultValues(Conv.copy(var15), Conv.copy(var7), Conv.copy(var16), Conv.copy(var8));
        }
    }

    public Regression(double[][] var1, double[] var2) {
        this.nSpecDual = this.dualMethods.length;
        this.bestPolyFlag = false;
        this.bestPolynomialDegree = 0;
        this.fProbSignificance = 0.05D;
        this.bestPolyArray = new ArrayList();
        this.bestPolyTooFewN = false;
        this.userSupplied = true;
        this.kayValue = 0.0D;
        this.frechetWeibull = true;
        this.linNonLin = true;
        this.trueFreq = false;
        this.xLegend = "x axis values";
        this.yLegend = "y axis values";
        this.graphTitle = " ";
        this.graphTitle2 = " ";
        this.legendCheck = false;
        this.suppressPrint = false;
        this.suppressYYplot = false;
        this.suppressErrorMessages = false;
        this.nlrStatus = true;
        this.scaleOpt = 0;
        this.scale = null;
        this.zeroCheck = false;
        this.penalty = false;
        this.sumPenalty = false;
        this.nConstraints = 0;
        this.nSumConstraints = 0;
        this.maxConstraintIndex = -1;
        this.constraintTolerance = 1.0E-4D;
        this.penalties = new ArrayList();
        this.sumPenalties = new ArrayList();
        this.penaltyCheck = null;
        this.sumPenaltyCheck = null;
        this.penaltyWeight = 1.0E30D;
        this.penaltyParam = null;
        this.sumPenaltyParam = (int[][])null;
        this.sumPlusOrMinus = (double[][])null;
        this.sumPenaltyNumber = null;
        this.constraints = null;
        this.sumConstraints = null;
        this.constraintMethod = 0;
        this.constrainedSingle = new ArrayList();
        this.constrainedMultiple = new ArrayList();
        this.constraintString = null;
        this.scaleFlag = true;
        this.yScaleFactor = 1.0D;
        this.nMax = 3000;
        this.minIter = 300;
        this.nIter = 0;
        this.konvge = 3;
        this.kRestart = 0;
        this.fMin = -1.0D;
        this.fTol = 1.0E-9D;
        this.rCoeff = 1.0D;
        this.eCoeff = 2.0D;
        this.cCoeff = 0.5D;
        this.startH = null;
        this.stepH = null;
        this.startSH = null;
        this.stepSH = null;
        this.dStep = 0.1D;
        this.grad = (double[][])null;
        this.delta = 1.0E-4D;
        this.invertFlag = true;
        this.posVarFlag = true;
        this.minTest = 0;
        this.simplexSd = 0.0D;
        this.statFlag = true;
        this.plotOpt = true;
        this.multipleY = false;
        this.ignoreDofFcheck = false;
        this.values = null;
        this.fixed = null;
        this.nGaussians = 0;
        this.multGaussFract = null;
        this.multGaussFractErrors = null;
        this.multGaussCoeffVar = null;
        this.multGaussTvalue = null;
        this.multGaussPvalue = null;
        this.multGaussScale = 1.0D;
        this.multGaussScaleError = 0.0D;
        this.multGaussScaleCoeffVar = 0.0D;
        this.multGaussScaleTvalue = 0.0D;
        this.multGaussScalePvalue = 0.0D;
        this.plotWindowCloseChoice = false;
        this.minimumY = 0.0D;
        this.minimumYindex = 0.0D;
        this.maximumY = 0.0D;
        this.maximumYindex = 0.0D;
        this.bottom = 0.0D;
        this.top = 0.0D;
        this.bottomS = 0.0D;
        this.bottomSindex = 0.0D;
        this.topS = 0.0D;
        this.topSindex = 0.0D;
        this.midPointLowerIndex = 0;
        this.midPointUpperIndex = 0;
        this.midPointXvalue = 0.0D;
        this.midPointYvalue = 0.0D;
        this.directionFlag = 0;
        this.dDurbinWatson = 0.0D / 0.0;
        this.dDurbinWatsonDone = false;
        this.firstDerivs = (double[][])null;
        this.analyticalDerivative = false;
        this.obsnVariance = 0.0D;
        this.nData0 = var2.length;
        int var3 = var2.length;
        double[] var4 = new double[var3];
        this.weightOpt = false;
        this.weightFlag = 0;

        for(int var5 = 0; var5 < var3; ++var5) {
            var4[var5] = 1.0D;
        }

        this.setDefaultValues(Conv.copy(var1), Conv.copy(var2), var4);
    }

    public Regression(double[][] var1, double[][] var2) {
        this.nSpecDual = this.dualMethods.length;
        this.bestPolyFlag = false;
        this.bestPolynomialDegree = 0;
        this.fProbSignificance = 0.05D;
        this.bestPolyArray = new ArrayList();
        this.bestPolyTooFewN = false;
        this.userSupplied = true;
        this.kayValue = 0.0D;
        this.frechetWeibull = true;
        this.linNonLin = true;
        this.trueFreq = false;
        this.xLegend = "x axis values";
        this.yLegend = "y axis values";
        this.graphTitle = " ";
        this.graphTitle2 = " ";
        this.legendCheck = false;
        this.suppressPrint = false;
        this.suppressYYplot = false;
        this.suppressErrorMessages = false;
        this.nlrStatus = true;
        this.scaleOpt = 0;
        this.scale = null;
        this.zeroCheck = false;
        this.penalty = false;
        this.sumPenalty = false;
        this.nConstraints = 0;
        this.nSumConstraints = 0;
        this.maxConstraintIndex = -1;
        this.constraintTolerance = 1.0E-4D;
        this.penalties = new ArrayList();
        this.sumPenalties = new ArrayList();
        this.penaltyCheck = null;
        this.sumPenaltyCheck = null;
        this.penaltyWeight = 1.0E30D;
        this.penaltyParam = null;
        this.sumPenaltyParam = (int[][])null;
        this.sumPlusOrMinus = (double[][])null;
        this.sumPenaltyNumber = null;
        this.constraints = null;
        this.sumConstraints = null;
        this.constraintMethod = 0;
        this.constrainedSingle = new ArrayList();
        this.constrainedMultiple = new ArrayList();
        this.constraintString = null;
        this.scaleFlag = true;
        this.yScaleFactor = 1.0D;
        this.nMax = 3000;
        this.minIter = 300;
        this.nIter = 0;
        this.konvge = 3;
        this.kRestart = 0;
        this.fMin = -1.0D;
        this.fTol = 1.0E-9D;
        this.rCoeff = 1.0D;
        this.eCoeff = 2.0D;
        this.cCoeff = 0.5D;
        this.startH = null;
        this.stepH = null;
        this.startSH = null;
        this.stepSH = null;
        this.dStep = 0.1D;
        this.grad = (double[][])null;
        this.delta = 1.0E-4D;
        this.invertFlag = true;
        this.posVarFlag = true;
        this.minTest = 0;
        this.simplexSd = 0.0D;
        this.statFlag = true;
        this.plotOpt = true;
        this.multipleY = false;
        this.ignoreDofFcheck = false;
        this.values = null;
        this.fixed = null;
        this.nGaussians = 0;
        this.multGaussFract = null;
        this.multGaussFractErrors = null;
        this.multGaussCoeffVar = null;
        this.multGaussTvalue = null;
        this.multGaussPvalue = null;
        this.multGaussScale = 1.0D;
        this.multGaussScaleError = 0.0D;
        this.multGaussScaleCoeffVar = 0.0D;
        this.multGaussScaleTvalue = 0.0D;
        this.multGaussScalePvalue = 0.0D;
        this.plotWindowCloseChoice = false;
        this.minimumY = 0.0D;
        this.minimumYindex = 0.0D;
        this.maximumY = 0.0D;
        this.maximumYindex = 0.0D;
        this.bottom = 0.0D;
        this.top = 0.0D;
        this.bottomS = 0.0D;
        this.bottomSindex = 0.0D;
        this.topS = 0.0D;
        this.topSindex = 0.0D;
        this.midPointLowerIndex = 0;
        this.midPointUpperIndex = 0;
        this.midPointXvalue = 0.0D;
        this.midPointYvalue = 0.0D;
        this.directionFlag = 0;
        this.dDurbinWatson = 0.0D / 0.0;
        this.dDurbinWatsonDone = false;
        this.firstDerivs = (double[][])null;
        this.analyticalDerivative = false;
        this.obsnVariance = 0.0D;
        this.multipleY = true;
        this.simplexFlag = 2;
        int var3 = var2.length;
        this.nYarrays = var3;
        int var4 = var2[0].length;
        this.nData0 = var4;
        int var5 = var1.length;
        double[] var6 = new double[var3 * var4];
        if (var3 != var5) {
            throw new IllegalArgumentException("Multiple xData and yData arrays of different overall dimensions not supported");
        } else {
            double[][] var7 = new double[1][var3 * var4];
            int var8 = 0;

            int var9;
            int var11;
            for(var9 = 0; var9 < var3; ++var9) {
                int var10 = var2[var9].length;
                if (var10 != var4) {
                    throw new IllegalArgumentException("multiple y arrays must be of the same length");
                }

                var11 = var1[var9].length;
                if (var10 != var11) {
                    throw new IllegalArgumentException("multiple y arrays must be of the same length as the x array length");
                }

                for(int var12 = 0; var12 < var4; ++var12) {
                    var6[var8] = var2[var9][var12];
                    var7[0][var8] = var1[var9][var12];
                    ++var8;
                }
            }

            var9 = var6.length;
            double[] var13 = new double[var9];
            this.weightOpt = false;

            for(var11 = 0; var11 < var9; ++var11) {
                var13[var11] = 1.0D;
            }

            this.weightFlag = 0;
            this.setDefaultValues(var7, var6, var13);
        }
    }

    public Regression(double[] var1, double[] var2) {
        this.nSpecDual = this.dualMethods.length;
        this.bestPolyFlag = false;
        this.bestPolynomialDegree = 0;
        this.fProbSignificance = 0.05D;
        this.bestPolyArray = new ArrayList();
        this.bestPolyTooFewN = false;
        this.userSupplied = true;
        this.kayValue = 0.0D;
        this.frechetWeibull = true;
        this.linNonLin = true;
        this.trueFreq = false;
        this.xLegend = "x axis values";
        this.yLegend = "y axis values";
        this.graphTitle = " ";
        this.graphTitle2 = " ";
        this.legendCheck = false;
        this.suppressPrint = false;
        this.suppressYYplot = false;
        this.suppressErrorMessages = false;
        this.nlrStatus = true;
        this.scaleOpt = 0;
        this.scale = null;
        this.zeroCheck = false;
        this.penalty = false;
        this.sumPenalty = false;
        this.nConstraints = 0;
        this.nSumConstraints = 0;
        this.maxConstraintIndex = -1;
        this.constraintTolerance = 1.0E-4D;
        this.penalties = new ArrayList();
        this.sumPenalties = new ArrayList();
        this.penaltyCheck = null;
        this.sumPenaltyCheck = null;
        this.penaltyWeight = 1.0E30D;
        this.penaltyParam = null;
        this.sumPenaltyParam = (int[][])null;
        this.sumPlusOrMinus = (double[][])null;
        this.sumPenaltyNumber = null;
        this.constraints = null;
        this.sumConstraints = null;
        this.constraintMethod = 0;
        this.constrainedSingle = new ArrayList();
        this.constrainedMultiple = new ArrayList();
        this.constraintString = null;
        this.scaleFlag = true;
        this.yScaleFactor = 1.0D;
        this.nMax = 3000;
        this.minIter = 300;
        this.nIter = 0;
        this.konvge = 3;
        this.kRestart = 0;
        this.fMin = -1.0D;
        this.fTol = 1.0E-9D;
        this.rCoeff = 1.0D;
        this.eCoeff = 2.0D;
        this.cCoeff = 0.5D;
        this.startH = null;
        this.stepH = null;
        this.startSH = null;
        this.stepSH = null;
        this.dStep = 0.1D;
        this.grad = (double[][])null;
        this.delta = 1.0E-4D;
        this.invertFlag = true;
        this.posVarFlag = true;
        this.minTest = 0;
        this.simplexSd = 0.0D;
        this.statFlag = true;
        this.plotOpt = true;
        this.multipleY = false;
        this.ignoreDofFcheck = false;
        this.values = null;
        this.fixed = null;
        this.nGaussians = 0;
        this.multGaussFract = null;
        this.multGaussFractErrors = null;
        this.multGaussCoeffVar = null;
        this.multGaussTvalue = null;
        this.multGaussPvalue = null;
        this.multGaussScale = 1.0D;
        this.multGaussScaleError = 0.0D;
        this.multGaussScaleCoeffVar = 0.0D;
        this.multGaussScaleTvalue = 0.0D;
        this.multGaussScalePvalue = 0.0D;
        this.plotWindowCloseChoice = false;
        this.minimumY = 0.0D;
        this.minimumYindex = 0.0D;
        this.maximumY = 0.0D;
        this.maximumYindex = 0.0D;
        this.bottom = 0.0D;
        this.top = 0.0D;
        this.bottomS = 0.0D;
        this.bottomSindex = 0.0D;
        this.topS = 0.0D;
        this.topSindex = 0.0D;
        this.midPointLowerIndex = 0;
        this.midPointUpperIndex = 0;
        this.midPointXvalue = 0.0D;
        this.midPointYvalue = 0.0D;
        this.directionFlag = 0;
        this.dDurbinWatson = 0.0D / 0.0;
        this.dDurbinWatsonDone = false;
        this.firstDerivs = (double[][])null;
        this.analyticalDerivative = false;
        this.obsnVariance = 0.0D;
        this.simplexFlag = 1;
        this.nData0 = var2.length;
        int var3 = var1.length;
        double[][] var4 = new double[1][var3];
        double[] var5 = new double[var3];

        int var6;
        for(var6 = 0; var6 < var3; ++var6) {
            var4[0][var6] = var1[var6];
        }

        this.weightOpt = false;
        this.weightFlag = 0;

        for(var6 = 0; var6 < var3; ++var6) {
            var5[var6] = 1.0D;
        }

        this.setDefaultValues(var4, Conv.copy(var2), var5);
    }

    public Regression(double[] var1, double[][] var2) {
        this.nSpecDual = this.dualMethods.length;
        this.bestPolyFlag = false;
        this.bestPolynomialDegree = 0;
        this.fProbSignificance = 0.05D;
        this.bestPolyArray = new ArrayList();
        this.bestPolyTooFewN = false;
        this.userSupplied = true;
        this.kayValue = 0.0D;
        this.frechetWeibull = true;
        this.linNonLin = true;
        this.trueFreq = false;
        this.xLegend = "x axis values";
        this.yLegend = "y axis values";
        this.graphTitle = " ";
        this.graphTitle2 = " ";
        this.legendCheck = false;
        this.suppressPrint = false;
        this.suppressYYplot = false;
        this.suppressErrorMessages = false;
        this.nlrStatus = true;
        this.scaleOpt = 0;
        this.scale = null;
        this.zeroCheck = false;
        this.penalty = false;
        this.sumPenalty = false;
        this.nConstraints = 0;
        this.nSumConstraints = 0;
        this.maxConstraintIndex = -1;
        this.constraintTolerance = 1.0E-4D;
        this.penalties = new ArrayList();
        this.sumPenalties = new ArrayList();
        this.penaltyCheck = null;
        this.sumPenaltyCheck = null;
        this.penaltyWeight = 1.0E30D;
        this.penaltyParam = null;
        this.sumPenaltyParam = (int[][])null;
        this.sumPlusOrMinus = (double[][])null;
        this.sumPenaltyNumber = null;
        this.constraints = null;
        this.sumConstraints = null;
        this.constraintMethod = 0;
        this.constrainedSingle = new ArrayList();
        this.constrainedMultiple = new ArrayList();
        this.constraintString = null;
        this.scaleFlag = true;
        this.yScaleFactor = 1.0D;
        this.nMax = 3000;
        this.minIter = 300;
        this.nIter = 0;
        this.konvge = 3;
        this.kRestart = 0;
        this.fMin = -1.0D;
        this.fTol = 1.0E-9D;
        this.rCoeff = 1.0D;
        this.eCoeff = 2.0D;
        this.cCoeff = 0.5D;
        this.startH = null;
        this.stepH = null;
        this.startSH = null;
        this.stepSH = null;
        this.dStep = 0.1D;
        this.grad = (double[][])null;
        this.delta = 1.0E-4D;
        this.invertFlag = true;
        this.posVarFlag = true;
        this.minTest = 0;
        this.simplexSd = 0.0D;
        this.statFlag = true;
        this.plotOpt = true;
        this.multipleY = false;
        this.ignoreDofFcheck = false;
        this.values = null;
        this.fixed = null;
        this.nGaussians = 0;
        this.multGaussFract = null;
        this.multGaussFractErrors = null;
        this.multGaussCoeffVar = null;
        this.multGaussTvalue = null;
        this.multGaussPvalue = null;
        this.multGaussScale = 1.0D;
        this.multGaussScaleError = 0.0D;
        this.multGaussScaleCoeffVar = 0.0D;
        this.multGaussScaleTvalue = 0.0D;
        this.multGaussScalePvalue = 0.0D;
        this.plotWindowCloseChoice = false;
        this.minimumY = 0.0D;
        this.minimumYindex = 0.0D;
        this.maximumY = 0.0D;
        this.maximumYindex = 0.0D;
        this.bottom = 0.0D;
        this.top = 0.0D;
        this.bottomS = 0.0D;
        this.bottomSindex = 0.0D;
        this.topS = 0.0D;
        this.topSindex = 0.0D;
        this.midPointLowerIndex = 0;
        this.midPointUpperIndex = 0;
        this.midPointXvalue = 0.0D;
        this.midPointYvalue = 0.0D;
        this.directionFlag = 0;
        this.dDurbinWatson = 0.0D / 0.0;
        this.dDurbinWatsonDone = false;
        this.firstDerivs = (double[][])null;
        this.analyticalDerivative = false;
        this.obsnVariance = 0.0D;
        this.multipleY = true;
        this.simplexFlag = 2;
        int var3 = var2.length;
        this.nYarrays = var3;
        int var4 = var2[0].length;
        this.nData0 = var4;
        double[] var5 = new double[var3 * var4];
        int var6 = 0;

        int var9;
        for(int var7 = 0; var7 < var3; ++var7) {
            int var8 = var2[var7].length;
            if (var8 != var4) {
                throw new IllegalArgumentException("multiple y arrays must be of the same length");
            }

            for(var9 = 0; var9 < var4; ++var9) {
                var5[var6] = var2[var7][var9];
                ++var6;
            }
        }

        double[][] var12 = new double[1][var3 * var4];
        double[] var13 = new double[var3 * var4];
        var6 = 0;
        var9 = var1.length;

        for(int var10 = 0; var10 < var3; ++var10) {
            for(int var11 = 0; var11 < var9; ++var11) {
                var12[0][var6] = var1[var11];
                var13[var6] = 1.0D;
                ++var6;
            }
        }

        this.weightOpt = false;
        this.weightFlag = 0;
        this.setDefaultValues(var12, var5, var13);
    }

    public Regression(double[] var1, double var2, double var4) {
        this.nSpecDual = this.dualMethods.length;
        this.bestPolyFlag = false;
        this.bestPolynomialDegree = 0;
        this.fProbSignificance = 0.05D;
        this.bestPolyArray = new ArrayList();
        this.bestPolyTooFewN = false;
        this.userSupplied = true;
        this.kayValue = 0.0D;
        this.frechetWeibull = true;
        this.linNonLin = true;
        this.trueFreq = false;
        this.xLegend = "x axis values";
        this.yLegend = "y axis values";
        this.graphTitle = " ";
        this.graphTitle2 = " ";
        this.legendCheck = false;
        this.suppressPrint = false;
        this.suppressYYplot = false;
        this.suppressErrorMessages = false;
        this.nlrStatus = true;
        this.scaleOpt = 0;
        this.scale = null;
        this.zeroCheck = false;
        this.penalty = false;
        this.sumPenalty = false;
        this.nConstraints = 0;
        this.nSumConstraints = 0;
        this.maxConstraintIndex = -1;
        this.constraintTolerance = 1.0E-4D;
        this.penalties = new ArrayList();
        this.sumPenalties = new ArrayList();
        this.penaltyCheck = null;
        this.sumPenaltyCheck = null;
        this.penaltyWeight = 1.0E30D;
        this.penaltyParam = null;
        this.sumPenaltyParam = (int[][])null;
        this.sumPlusOrMinus = (double[][])null;
        this.sumPenaltyNumber = null;
        this.constraints = null;
        this.sumConstraints = null;
        this.constraintMethod = 0;
        this.constrainedSingle = new ArrayList();
        this.constrainedMultiple = new ArrayList();
        this.constraintString = null;
        this.scaleFlag = true;
        this.yScaleFactor = 1.0D;
        this.nMax = 3000;
        this.minIter = 300;
        this.nIter = 0;
        this.konvge = 3;
        this.kRestart = 0;
        this.fMin = -1.0D;
        this.fTol = 1.0E-9D;
        this.rCoeff = 1.0D;
        this.eCoeff = 2.0D;
        this.cCoeff = 0.5D;
        this.startH = null;
        this.stepH = null;
        this.startSH = null;
        this.stepSH = null;
        this.dStep = 0.1D;
        this.grad = (double[][])null;
        this.delta = 1.0E-4D;
        this.invertFlag = true;
        this.posVarFlag = true;
        this.minTest = 0;
        this.simplexSd = 0.0D;
        this.statFlag = true;
        this.plotOpt = true;
        this.multipleY = false;
        this.ignoreDofFcheck = false;
        this.values = null;
        this.fixed = null;
        this.nGaussians = 0;
        this.multGaussFract = null;
        this.multGaussFractErrors = null;
        this.multGaussCoeffVar = null;
        this.multGaussTvalue = null;
        this.multGaussPvalue = null;
        this.multGaussScale = 1.0D;
        this.multGaussScaleError = 0.0D;
        this.multGaussScaleCoeffVar = 0.0D;
        this.multGaussScaleTvalue = 0.0D;
        this.multGaussScalePvalue = 0.0D;
        this.plotWindowCloseChoice = false;
        this.minimumY = 0.0D;
        this.minimumYindex = 0.0D;
        this.maximumY = 0.0D;
        this.maximumYindex = 0.0D;
        this.bottom = 0.0D;
        this.top = 0.0D;
        this.bottomS = 0.0D;
        this.bottomSindex = 0.0D;
        this.topS = 0.0D;
        this.topSindex = 0.0D;
        this.midPointLowerIndex = 0;
        this.midPointUpperIndex = 0;
        this.midPointXvalue = 0.0D;
        this.midPointYvalue = 0.0D;
        this.directionFlag = 0;
        this.dDurbinWatson = 0.0D / 0.0;
        this.dDurbinWatsonDone = false;
        this.firstDerivs = (double[][])null;
        this.analyticalDerivative = false;
        this.obsnVariance = 0.0D;
        this.simplexFlag = 1;
        double[][] var6 = histogramBins(Conv.copy(var1), var2, var4);
        int var7 = var6[0].length;
        this.nData0 = var7;
        double[][] var8 = new double[1][var7];
        double[] var9 = new double[var7];
        double[] var10 = new double[var7];

        for(int var11 = 0; var11 < var7; ++var11) {
            var8[0][var11] = var6[0][var11];
            var9[var11] = var6[1][var11];
        }

        boolean var12 = setTrueFreqWeights(var9, var10);
        if (var12) {
            this.trueFreq = true;
            this.weightOpt = true;
            this.weightFlag = 1;
        } else {
            this.trueFreq = false;
            this.weightOpt = false;
            this.weightFlag = 0;
        }

        this.setDefaultValues(var8, var9, var10);
    }

    public Regression(double[] var1, double var2) {
        this.nSpecDual = this.dualMethods.length;
        this.bestPolyFlag = false;
        this.bestPolynomialDegree = 0;
        this.fProbSignificance = 0.05D;
        this.bestPolyArray = new ArrayList();
        this.bestPolyTooFewN = false;
        this.userSupplied = true;
        this.kayValue = 0.0D;
        this.frechetWeibull = true;
        this.linNonLin = true;
        this.trueFreq = false;
        this.xLegend = "x axis values";
        this.yLegend = "y axis values";
        this.graphTitle = " ";
        this.graphTitle2 = " ";
        this.legendCheck = false;
        this.suppressPrint = false;
        this.suppressYYplot = false;
        this.suppressErrorMessages = false;
        this.nlrStatus = true;
        this.scaleOpt = 0;
        this.scale = null;
        this.zeroCheck = false;
        this.penalty = false;
        this.sumPenalty = false;
        this.nConstraints = 0;
        this.nSumConstraints = 0;
        this.maxConstraintIndex = -1;
        this.constraintTolerance = 1.0E-4D;
        this.penalties = new ArrayList();
        this.sumPenalties = new ArrayList();
        this.penaltyCheck = null;
        this.sumPenaltyCheck = null;
        this.penaltyWeight = 1.0E30D;
        this.penaltyParam = null;
        this.sumPenaltyParam = (int[][])null;
        this.sumPlusOrMinus = (double[][])null;
        this.sumPenaltyNumber = null;
        this.constraints = null;
        this.sumConstraints = null;
        this.constraintMethod = 0;
        this.constrainedSingle = new ArrayList();
        this.constrainedMultiple = new ArrayList();
        this.constraintString = null;
        this.scaleFlag = true;
        this.yScaleFactor = 1.0D;
        this.nMax = 3000;
        this.minIter = 300;
        this.nIter = 0;
        this.konvge = 3;
        this.kRestart = 0;
        this.fMin = -1.0D;
        this.fTol = 1.0E-9D;
        this.rCoeff = 1.0D;
        this.eCoeff = 2.0D;
        this.cCoeff = 0.5D;
        this.startH = null;
        this.stepH = null;
        this.startSH = null;
        this.stepSH = null;
        this.dStep = 0.1D;
        this.grad = (double[][])null;
        this.delta = 1.0E-4D;
        this.invertFlag = true;
        this.posVarFlag = true;
        this.minTest = 0;
        this.simplexSd = 0.0D;
        this.statFlag = true;
        this.plotOpt = true;
        this.multipleY = false;
        this.ignoreDofFcheck = false;
        this.values = null;
        this.fixed = null;
        this.nGaussians = 0;
        this.multGaussFract = null;
        this.multGaussFractErrors = null;
        this.multGaussCoeffVar = null;
        this.multGaussTvalue = null;
        this.multGaussPvalue = null;
        this.multGaussScale = 1.0D;
        this.multGaussScaleError = 0.0D;
        this.multGaussScaleCoeffVar = 0.0D;
        this.multGaussScaleTvalue = 0.0D;
        this.multGaussScalePvalue = 0.0D;
        this.plotWindowCloseChoice = false;
        this.minimumY = 0.0D;
        this.minimumYindex = 0.0D;
        this.maximumY = 0.0D;
        this.maximumYindex = 0.0D;
        this.bottom = 0.0D;
        this.top = 0.0D;
        this.bottomS = 0.0D;
        this.bottomSindex = 0.0D;
        this.topS = 0.0D;
        this.topSindex = 0.0D;
        this.midPointLowerIndex = 0;
        this.midPointUpperIndex = 0;
        this.midPointXvalue = 0.0D;
        this.midPointYvalue = 0.0D;
        this.directionFlag = 0;
        this.dDurbinWatson = 0.0D / 0.0;
        this.dDurbinWatsonDone = false;
        this.firstDerivs = (double[][])null;
        this.analyticalDerivative = false;
        this.obsnVariance = 0.0D;
        this.simplexFlag = 1;
        double[][] var4 = histogramBins(Conv.copy(var1), var2);
        int var5 = var4[0].length;
        this.nData0 = var5;
        double[][] var6 = new double[1][var5];
        double[] var7 = new double[var5];
        double[] var8 = new double[var5];

        for(int var9 = 0; var9 < var5; ++var9) {
            var6[0][var9] = var4[0][var9];
            var7[var9] = var4[1][var9];
        }

        boolean var10 = setTrueFreqWeights(var7, var8);
        if (var10) {
            this.trueFreq = true;
            this.weightOpt = true;
            this.weightFlag = 1;
        } else {
            this.trueFreq = false;
            this.weightOpt = false;
            this.weightFlag = 0;
        }

        this.setDefaultValues(var6, var7, var8);
    }

    public void enterData(double[][] var1, double[] var2, double[] var3) {
        this.simplexFlag = 1;
        this.nData0 = var2.length;
        var3 = this.checkForZeroWeights(var3);
        if (this.weightOpt) {
            this.weightFlag = 1;
        }

        this.setDefaultValues(Conv.copy(var1), Conv.copy(var2), Conv.copy(var3));
    }

    public void enterData(double[][] var1, double[] var2, double[][] var3, double[] var4) {
        this.simplexFlag = 3;
        this.nData0 = var2.length;
        this.jointZeroCheck(var3, var4);
        this.yErrorsEntered = true;
        this.xErrorsEntered = true;
        this.weightOpt = true;
        this.weightFlag = 1;
        this.setDefaultValues(Conv.copy(var1), Conv.copy(var2), Conv.copy(var3), Conv.copy(var4));
    }

    public void enterData(double[][] var1, double[][] var2, double[][] var3) {
        this.simplexFlag = 2;
        this.multipleY = true;
        int var4 = var2.length;
        this.nYarrays = var4;
        int var5 = var2[0].length;
        this.nData0 = var5;
        int var6 = var1.length;
        int var7 = var1[0].length;
        double[] var8 = new double[var4 * var5];
        double[] var9 = new double[var4 * var5];
        double[][] var10 = new double[var4 * var5][var6];
        int var11 = 0;

        for(int var12 = 0; var12 < var4; ++var12) {
            int var13 = var2[var12].length;
            if (var13 != var5) {
                throw new IllegalArgumentException("multiple y arrays must be of the same length");
            }

            int var14 = var1[var12].length;
            if (var13 != var14) {
                throw new IllegalArgumentException("multiple y arrays must be of the same length as the x array length");
            }

            for(int var15 = 0; var15 < var5; ++var15) {
                var8[var11] = var2[var12][var15];
                var10[var11][var12] = var1[var12][var15];
                var9[var11] = var3[var12][var15];
                ++var11;
            }
        }

        var9 = this.checkForZeroWeights(var9);
        if (this.weightOpt) {
            this.weightFlag = 1;
        }

        this.setDefaultValues(var10, var8, var9);
    }

    public void enterData(double[][] var1, double[][] var2, double[][] var3, double[][] var4) {
        this.simplexFlag = 4;
        this.multipleY = true;
        int var5 = var2.length;
        this.nYarrays = var5;
        int var6 = var2[0].length;
        this.nData0 = var6;
        int var7 = var1.length;
        int var8 = var1[0].length;
        double[] var9 = new double[var5 * var6];
        double[] var10 = new double[var5 * var6];
        double[][] var11 = new double[var5 * var6][var7];
        double[][] var12 = new double[var5 * var6][var7];
        int var13 = 0;

        for(int var14 = 0; var14 < var5; ++var14) {
            int var15 = var2[var14].length;
            if (var15 != var6) {
                throw new IllegalArgumentException("multiple y arrays must be of the same length");
            }

            int var16 = var1[var14].length;
            if (var15 != var16) {
                throw new IllegalArgumentException("multiple y arrays must be of the same length as the x array length");
            }

            for(int var17 = 0; var17 < var6; ++var17) {
                var9[var13] = var2[var14][var17];
                var11[var13][var14] = var1[var14][var17];
                var10[var13] = var4[var14][var17];
                var12[var13][var14] = var3[var14][var17];
                ++var13;
            }
        }

        this.jointZeroCheck(var12, var10);
        this.yErrorsEntered = true;
        this.xErrorsEntered = true;
        this.weightOpt = true;
        this.weightFlag = 1;
        this.setDefaultValues(Conv.copy(var11), Conv.copy(var9), Conv.copy(var12), Conv.copy(var10));
        this.setDefaultValues(var11, var9, var10);
    }

    public void enterData(double[] var1, double[] var2, double[] var3) {
        this.simplexFlag = 1;
        this.nData0 = var2.length;
        int var4 = var1.length;
        int var5 = var3.length;
        double[][] var6 = new double[1][var4];

        for(int var7 = 0; var7 < var4; ++var7) {
            var6[0][var7] = var1[var7];
        }

        var3 = this.checkForZeroWeights(var3);
        if (this.weightOpt) {
            this.weightFlag = 1;
        }

        this.setDefaultValues(Conv.copy(var6), Conv.copy(var2), Conv.copy(var3));
    }

    public void enterData(double[] var1, double[] var2, double[] var3, double[] var4) {
        this.simplexFlag = 3;
        this.nData0 = var2.length;
        int var5 = var1.length;
        int var6 = var4.length;
        double[][] var7 = new double[1][var5];
        double[][] var8 = new double[1][var5];

        for(int var9 = 0; var9 < var5; ++var9) {
            var7[0][var9] = var1[var9];
            var8[0][var9] = var3[var9];
        }

        this.jointZeroCheck(var8, var4);
        this.xErrorsEntered = true;
        this.yErrorsEntered = true;
        this.weightOpt = true;
        this.weightFlag = 1;
        this.setDefaultValues(Conv.copy(var7), Conv.copy(var2), Conv.copy(var8), Conv.copy(var4));
    }

    public void enterData(double[] var1, double[][] var2, double[][] var3) {
        this.simplexFlag = 1;
        this.multipleY = true;
        int var4 = var2.length;
        this.nYarrays = var4;
        int var5 = var2[0].length;
        this.nData0 = var5;
        double[] var6 = new double[var4 * var5];
        double[] var7 = new double[var4 * var5];
        int var8 = 0;

        int var9;
        int var11;
        for(var9 = 0; var9 < var4; ++var9) {
            int var10 = var2[var9].length;
            if (var10 != var5) {
                throw new IllegalArgumentException("multiple y arrays must be of the same length");
            }

            for(var11 = 0; var11 < var5; ++var11) {
                var6[var8] = var2[var9][var11];
                var7[var8] = var3[var9][var11];
                ++var8;
            }
        }

        var9 = var1.length;
        if (var9 != var5) {
            throw new IllegalArgumentException("x and y data lengths must be the same");
        } else {
            double[][] var13 = new double[1][var4 * var9];
            var8 = 0;

            for(var11 = 0; var11 < var4; ++var11) {
                for(int var12 = 0; var12 < var9; ++var12) {
                    var13[0][var8] = var1[var12];
                    ++var8;
                }
            }

            var7 = this.checkForZeroWeights(var7);
            if (this.weightOpt) {
                this.weightFlag = 1;
            }

            this.setDefaultValues(var13, var6, var7);
        }
    }

    public void enterData(double[] var1, double[][] var2, double[] var3, double[][] var4) {
        this.simplexFlag = 4;
        this.multipleY = true;
        int var5 = var2.length;
        this.nYarrays = var5;
        int var6 = var2[0].length;
        this.nData0 = var6;
        double[] var7 = new double[var5 * var6];
        double[] var8 = new double[var5 * var6];
        int var9 = 0;

        int var10;
        for(var10 = 0; var10 < var5; ++var10) {
            int var11 = var2[var10].length;
            if (var11 != var6) {
                throw new IllegalArgumentException("multiple y arrays must be of the same length");
            }

            for(int var12 = 0; var12 < var6; ++var12) {
                var7[var9] = var2[var10][var12];
                var8[var9] = var4[var10][var12];
                ++var9;
            }
        }

        var10 = var1.length;
        if (var10 != var6) {
            throw new IllegalArgumentException("x and y data lengths must be the same");
        } else {
            double[][] var15 = new double[1][var5 * var10];
            double[][] var16 = new double[1][var5 * var10];
            var9 = 0;

            for(int var13 = 0; var13 < var5; ++var13) {
                for(int var14 = 0; var14 < var10; ++var14) {
                    var15[0][var9] = var1[var14];
                    var16[0][var9] = var3[var14];
                    ++var9;
                }
            }

            this.jointZeroCheck(var16, var8);
            this.xErrorsEntered = true;
            this.yErrorsEntered = true;
            this.weightOpt = true;
            this.weightFlag = 1;
            this.setDefaultValues(Conv.copy(var15), Conv.copy(var7), Conv.copy(var16), Conv.copy(var8));
        }
    }

    public void enterData(double[][] var1, double[] var2) {
        this.nData0 = var2.length;
        int var3 = var2.length;
        double[] var4 = new double[var3];
        this.weightOpt = false;
        this.weightFlag = 0;

        for(int var5 = 0; var5 < var3; ++var5) {
            var4[var5] = 1.0D;
        }

        this.setDefaultValues(Conv.copy(var1), Conv.copy(var2), var4);
    }

    public void enterData(double[][] var1, double[][] var2) {
        this.multipleY = true;
        this.simplexFlag = 2;
        int var3 = var2.length;
        this.nYarrays = var3;
        int var4 = var2[0].length;
        this.nData0 = var4;
        int var5 = var1.length;
        double[] var6 = new double[var3 * var4];
        if (var3 != var5) {
            throw new IllegalArgumentException("Multiple xData and yData arrays of different overall dimensions not supported");
        } else {
            double[][] var7 = new double[1][var3 * var4];
            int var8 = 0;

            int var9;
            int var11;
            for(var9 = 0; var9 < var3; ++var9) {
                int var10 = var2[var9].length;
                if (var10 != var4) {
                    throw new IllegalArgumentException("multiple y arrays must be of the same length");
                }

                var11 = var1[var9].length;
                if (var10 != var11) {
                    throw new IllegalArgumentException("multiple y arrays must be of the same length as the x array length");
                }

                for(int var12 = 0; var12 < var4; ++var12) {
                    var6[var8] = var2[var9][var12];
                    var7[0][var8] = var1[var9][var12];
                    ++var8;
                }
            }

            var9 = var6.length;
            double[] var13 = new double[var9];
            this.weightOpt = false;

            for(var11 = 0; var11 < var9; ++var11) {
                var13[var11] = 1.0D;
            }

            this.weightFlag = 0;
            this.setDefaultValues(var7, var6, var13);
        }
    }

    public void enterData(double[] var1, double[] var2) {
        this.simplexFlag = 1;
        this.nData0 = var2.length;
        int var3 = var1.length;
        double[][] var4 = new double[1][var3];
        double[] var5 = new double[var3];

        int var6;
        for(var6 = 0; var6 < var3; ++var6) {
            var4[0][var6] = var1[var6];
        }

        this.weightOpt = false;
        this.weightFlag = 0;

        for(var6 = 0; var6 < var3; ++var6) {
            var5[var6] = 1.0D;
        }

        this.setDefaultValues(var4, Conv.copy(var2), var5);
    }

    public void enterData(double[] var1, double[][] var2) {
        this.multipleY = true;
        this.simplexFlag = 2;
        int var3 = var2.length;
        this.nYarrays = var3;
        int var4 = var2[0].length;
        this.nData0 = var4;
        double[] var5 = new double[var3 * var4];
        int var6 = 0;

        int var9;
        for(int var7 = 0; var7 < var3; ++var7) {
            int var8 = var2[var7].length;
            if (var8 != var4) {
                throw new IllegalArgumentException("multiple y arrays must be of the same length");
            }

            for(var9 = 0; var9 < var4; ++var9) {
                var5[var6] = var2[var7][var9];
                ++var6;
            }
        }

        double[][] var12 = new double[1][var3 * var4];
        double[] var13 = new double[var3 * var4];
        var6 = 0;
        var9 = var1.length;

        for(int var10 = 0; var10 < var3; ++var10) {
            for(int var11 = 0; var11 < var9; ++var11) {
                var12[0][var6] = var1[var11];
                var13[var6] = 1.0D;
                ++var6;
            }
        }

        this.weightOpt = false;
        this.weightFlag = 0;
        this.setDefaultValues(var12, var5, var13);
    }

    public void enterData(double[] var1, double var2, double var4) {
        this.simplexFlag = 1;
        double[][] var6 = histogramBins(Conv.copy(var1), var2, var4);
        int var7 = var6[0].length;
        this.nData0 = var7;
        double[][] var8 = new double[1][var7];
        double[] var9 = new double[var7];
        double[] var10 = new double[var7];

        for(int var11 = 0; var11 < var7; ++var11) {
            var8[0][var11] = var6[0][var11];
            var9[var11] = var6[1][var11];
        }

        boolean var12 = setTrueFreqWeights(var9, var10);
        if (var12) {
            this.trueFreq = true;
            this.weightOpt = true;
            this.weightFlag = 1;
        } else {
            this.trueFreq = false;
            this.weightOpt = false;
            this.weightFlag = 0;
        }

        this.setDefaultValues(var8, var9, var10);
    }

    public void enterData(double[] var1, double var2) {
        this.simplexFlag = 1;
        double[][] var4 = histogramBins(Conv.copy(var1), var2);
        int var5 = var4[0].length;
        this.nData0 = var5;
        double[][] var6 = new double[1][var5];
        double[] var7 = new double[var5];
        double[] var8 = new double[var5];

        for(int var9 = 0; var9 < var5; ++var9) {
            var6[0][var9] = var4[0][var9];
            var7[var9] = var4[1][var9];
        }

        boolean var10 = setTrueFreqWeights(var7, var8);
        if (var10) {
            this.trueFreq = true;
            this.weightOpt = true;
            this.weightFlag = 1;
        } else {
            this.trueFreq = false;
            this.weightOpt = false;
            this.weightFlag = 0;
        }

        this.setDefaultValues(var6, var7, var8);
    }

    protected double[] checkForZeroWeights(double[] var1) {
        byte var2 = 1;
        boolean var3 = this.weightOpt;
        this.weightOpt = false;
        var1 = this.checkForZeroWeightsCommon(var1, var1.length, 0, var2);
        if (this.weightOpt) {
            this.yErrorsEntered = true;
        }

        if (var3) {
            this.weightOpt = true;
        }

        return var1;
    }

    protected double[] checkForZeroWeightsCommon(double[] var1, int var2, int var3, int var4) {
        this.weightOpt = true;
        int var5 = 0;
        int var6 = var1.length;

        for(int var7 = 0; var7 < var6; ++var7) {
            if (var1[var7] <= 0.0D) {
                ++var5;
            }
        }

        double var18 = 100.0D * (double)var5 / (double)var6;
        int var9;
        if (var18 > 40.0D) {
            System.out.println(var18 + "% of the weighting errors are zero or less; all weighting errors set to 1.0");

            for(var9 = 0; var9 < var6; ++var9) {
                var1[var9] = 1.0D;
            }

            this.weightOpt = false;
        } else if (var18 > 0.0D) {
            for(var9 = 0; var9 < var6; ++var9) {
                if (var1[var9] <= 0.0D) {
                    int var10;
                    boolean var11;
                    double var12;
                    if (var9 == 0) {
                        var10 = 1;
                        var11 = true;

                        while(var11) {
                            if (var1[var10] > 0.0D) {
                                var12 = var1[0];
                                var1[0] = var1[var10];
                                if (var4 == 1) {
                                    System.out.println("y error at point " + var9 + ", " + var12 + ", replaced by " + var1[var9]);
                                } else {
                                    System.out.println("x error at point " + var9 / var3 + "," + var9 % var3 + " " + var12 + " replaced by " + var1[var9]);
                                }

                                var11 = false;
                            } else {
                                ++var10;
                            }
                        }
                    }

                    if (var9 == var6 - 1) {
                        var10 = var6 - 2;
                        var11 = true;

                        while(var11) {
                            if (var1[var10] > 0.0D) {
                                var12 = var1[var9];
                                var1[var9] = var1[var10];
                                if (var4 == 1) {
                                    System.out.println("y error at point " + var9 + ", " + var12 + ", replaced by " + var1[var9]);
                                } else {
                                    System.out.println("x error at point " + var9 / var3 + "," + var9 % var3 + " " + var12 + " replaced by " + var1[var9]);
                                }

                                var11 = false;
                            } else {
                                --var10;
                            }
                        }
                    }

                    if (var9 > 0 && var9 < var6 - 2) {
                        double var19 = 0.0D;
                        var12 = 0.0D;
                        int var14 = var9 - 1;
                        boolean var15 = true;

                        while(var15) {
                            if (var1[var14] > 0.0D) {
                                var19 = var1[var14];
                                var15 = false;
                            } else {
                                --var14;
                                if (var14 == 0) {
                                    var15 = false;
                                }
                            }
                        }

                        var14 = var9 + 1;
                        var15 = true;

                        while(var15) {
                            if (var1[var14] > 0.0D) {
                                var12 = var1[var14];
                                var15 = false;
                            } else {
                                ++var14;
                                if (var14 == var6 - 1) {
                                    var15 = false;
                                }
                            }
                        }

                        double var16 = var1[var9];
                        if (var19 == 0.0D) {
                            var1[var9] = var12;
                        } else if (var12 == 0.0D) {
                            var1[var9] = var19;
                        } else {
                            var1[var9] = (var19 + var12) / 2.0D;
                        }

                        if (var4 == 1) {
                            System.out.println("y error at point " + var9 + ", " + var16 + ", replaced by " + var1[var9]);
                        } else {
                            System.out.println("x error at point " + var9 / var3 + "," + var9 % var3 + " " + var16 + " replaced by " + var1[var9]);
                        }
                    }
                }
            }
        }

        return var1;
    }

    protected void jointZeroCheck(double[][] var1, double[] var2) {
        int var3 = var1[0].length;
        int var4 = var2.length;
        int var5 = var1.length;
        boolean var6 = false;
        if (var4 != var3) {
            throw new IllegalArgumentException("The number of x weightimg errors, " + var3 + ", does not match the number of y weighting errors. " + var4);
        } else {
            for(int var7 = 0; var7 < var3; ++var7) {
                int var9 = 0;
                if (var2[var7] == 0.0D) {
                    ++var9;
                }

                for(int var8 = 0; var8 < var5; ++var8) {
                    if (var1[var8][var7] == 0.0D) {
                        ++var9;
                    }
                }

                if (var9 == var5 + 1) {
                    throw new IllegalArgumentException("Data point, index, " + var7 + ", has zero x and zero y weighting error values");
                }
            }

        }
    }

    protected static boolean setTrueFreqWeights(double[] var0, double[] var1) {
        int var2 = var0.length;
        boolean var3 = true;
        boolean var4 = false;

        int var5;
        for(var5 = 0; var5 < var2; ++var5) {
            var1[var5] = Math.sqrt(Math.abs(var0[var5]));
        }

        for(var5 = 0; var5 < var2; ++var5) {
            double var6 = 0.0D;
            double var8 = 0.0D;
            if (var1[var5] == 0.0D) {
                boolean var10 = true;
                int var11 = var5 - 1;

                while(var10) {
                    if (var11 < 0) {
                        var10 = false;
                    } else if (var1[var11] == 0.0D) {
                        --var11;
                    } else {
                        var6 = var1[var11];
                        var10 = false;
                    }
                }

                boolean var12 = true;
                int var13 = var5 + 1;

                while(var12) {
                    if (var13 >= var2) {
                        var12 = false;
                    } else if (var1[var13] == 0.0D) {
                        ++var13;
                    } else {
                        var8 = var1[var13];
                        var12 = false;
                    }
                }

                var1[var5] = (var6 + var8) / 2.0D;
            }
        }

        return var3;
    }

    protected void setDefaultValues(double[][] var1, double[] var2, double[] var3) {
        this.setDefaultValues(var1, var2, (double[][])null, var3);
    }

    protected void setDefaultValues(double[][] var1, double[] var2, double[][] var3, double[] var4) {
        boolean var5 = true;
        if (var3 == null) {
            var5 = false;
        }

        this.nData = var2.length;
        this.nXarrays = var1.length;
        this.nParam = this.nXarrays;
        this.yData = new double[this.nData];
        this.yCalc = new double[this.nData];
        this.yErrors = new double[this.nData];
        this.weight = new double[this.nData];
        this.residual = new double[this.nData];
        this.residualW = new double[this.nData];
        this.xData = new double[this.nXarrays][this.nData];
        if (var5) {
            this.xErrors = new double[this.nXarrays][this.nData];
        }

        int var6 = var4.length;
        if (var6 != this.nData) {
            throw new IllegalArgumentException("The y error and the y data lengths do not agree");
        } else {
            int var7;
            for(var7 = 0; var7 < this.nData; ++var7) {
                this.yData[var7] = var2[var7];
                this.yErrors[var7] = var4[var7];
                this.weight[var7] = this.yErrors[var7];
            }

            for(var7 = 0; var7 < this.nXarrays; ++var7) {
                var6 = var1[var7].length;
                if (var6 != this.nData) {
                    throw new IllegalArgumentException("An x [" + var7 + "] length " + var6 + " and the y data length, " + this.nData + ", do not agree");
                }

                int var8;
                if (var5) {
                    var8 = var3[var7].length;
                    if (var8 != this.nData) {
                        throw new IllegalArgumentException("An x error [" + var7 + "] length " + var6 + " and the y data length, " + this.nData + ", do not agree");
                    }
                }

                for(var8 = 0; var8 < this.nData; ++var8) {
                    this.xData[var7][var8] = var1[var7][var8];
                }

                if (var5) {
                    for(var8 = 0; var8 < this.nData; ++var8) {
                        this.xErrors[var7][var8] = var3[var7][var8];
                    }
                }
            }

            this.minimumY = this.yData[0];
            this.minimumYindex = 0.0D;
            this.maximumY = this.yData[0];
            this.maximumYindex = 0.0D;

            for(var7 = 0; var7 < this.nData; ++var7) {
                if (this.yData[var7] < this.minimumY) {
                    this.minimumY = this.yData[var7];
                    this.minimumYindex = (double)var7;
                }

                if (this.yData[var7] > this.maximumY) {
                    this.maximumY = this.yData[var7];
                    this.maximumYindex = (double)var7;
                }
            }

            this.effectiveNumber();
        }
    }

    public void setErrorsAsSD() {
        this.trueErrors = true;
    }

    public void setErrorsAsScaled() {
        this.trueErrors = false;
    }

    protected void effectiveNumber() {
        double var1 = 0.0D;
        double var3 = 0.0D;
        double var5 = 0.0D;

        for(int var7 = 0; var7 < this.nData; ++var7) {
            var5 = 1.0D / (this.weight[var7] * this.weight[var7]);
            var1 += var5;
            var3 += var5 * var5;
        }

        this.nEffective = var1 * var1 / var3;
    }

    private void reassessFtol() {
        double var1 = Math.pow(10.0D, Math.floor(Math.log10((this.minimumY + this.maximumY) / 2.0D)));
        double var3 = var1 * this.fTol;
        if (var3 < this.fTol) {
            this.fTol = var3;
        }

    }

    public static void setDenominatorToN() {
        Stat.setStaticDenominatorToN();
    }

    public static void setDenominatorToNminusOne() {
        Stat.setStaticDenominatorToNminusOne();
    }

    public static void resetCFmaxIter(int var0) {
        Stat.resetCFmaxIter(var0);
    }

    public static int getCFmaxIter() {
        return Stat.getCFmaxIter();
    }

    public static void resetCFtolerance(double var0) {
        Stat.resetCFtolerance(var0);
    }

    public static double getCFtolerance() {
        return Stat.getCFtolerance();
    }

    public void suppressPrint() {
        this.suppressPrint = true;
    }

    public void supressPrint() {
        this.suppressPrint = true;
    }

    public void suppressYYplot() {
        this.suppressYYplot = true;
    }

    public void supressYYplot() {
        this.suppressYYplot = true;
    }

    public void suppressErrorMessages() {
        this.suppressErrorMessages = true;
    }

    public void supressErrorMessages() {
        this.suppressErrorMessages = true;
    }

    public void ignoreDofFcheck() {
        this.ignoreDofFcheck = true;
    }

    public void suppressStats() {
        this.statFlag = false;
    }

    public void supressStats() {
        this.statFlag = false;
    }

    public void reinstateStats() {
        this.statFlag = true;
    }

    public void setCloseChoice(int var1) {
        switch(var1) {
            case 1:
                this.plotWindowCloseChoice = false;
                break;
            case 2:
                this.plotWindowCloseChoice = true;
                break;
            default:
                throw new IllegalArgumentException("Option " + var1 + " not recognised");
        }

    }

    public void setYscaleOption(boolean var1) {
        this.scaleFlag = var1;
        if (!var1) {
            this.yScaleFactor = 1.0D;
        }

    }

    public void setYscale(boolean var1) {
        this.scaleFlag = var1;
        if (!var1) {
            this.yScaleFactor = 1.0D;
        }

    }

    public void setYscaleFactor(double var1) {
        this.scaleFlag = false;
        this.yScaleFactor = var1;
    }

    public boolean getYscaleOption() {
        return this.scaleFlag;
    }

    public boolean getYscale() {
        return this.scaleFlag;
    }

    public void setTrueFreq(boolean var1) {
        boolean var2 = this.trueFreq;
        this.trueFreq = var1;
        if (var1) {
            boolean var3 = setTrueFreqWeights(this.yData, this.weight);
            if (var3) {
                this.trueFreq = true;
                this.weightOpt = true;
            } else {
                this.trueFreq = false;
                this.weightOpt = false;
            }
        } else if (var2) {
            for(int var4 = 0; var4 < this.weight.length; ++var4) {
                this.weight[var4] = 1.0D;
            }

            this.weightOpt = false;
        }

    }

    public boolean getTrueFreq() {
        return this.trueFreq;
    }

    public void setXlegend(String var1) {
        this.xLegend = var1;
        this.legendCheck = true;
    }

    public void setYlegend(String var1) {
        this.yLegend = var1;
        this.legendCheck = true;
    }

    public void setTitle(String var1) {
        this.graphTitle = var1;
    }

    public void constant() {
        if (this.multipleY) {
            throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
        } else {
            this.lastMethod = 46;
            this.linNonLin = true;
            this.nParam = 1;
            this.degreesOfFreedom = this.nData - this.nParam;
            if (this.degreesOfFreedom < 1 && !this.ignoreDofFcheck) {
                throw new IllegalArgumentException("Degrees of freedom must be greater than 0");
            } else {
                this.best = new double[this.nParam];
                this.bestSd = new double[this.nParam];
                this.tValues = new double[this.nParam];
                this.pValues = new double[this.nParam];
                this.best[0] = Stat.mean(this.yData, this.weight);
                this.bestSd[0] = Stat.standardDeviation(this.yData, this.weight);
                this.tValues[0] = this.best[0] / this.bestSd[0];
                double var1 = Math.abs(this.tValues[0]);
                if (var1 != var1) {
                    this.pValues[0] = 0.0D / 0.0;
                } else {
                    this.pValues[0] = 1.0D - Stat.studentTcdf(-var1, var1, this.degreesOfFreedom);
                }

                this.sumOfSquaresError = 0.0D;
                this.chiSquare = 0.0D;

                for(int var3 = 0; var3 < this.nData; ++var3) {
                    this.yCalc[var3] = this.best[0];
                    this.residual[var3] = this.yCalc[var3] - this.yData[var3];
                    this.residualW[var3] = this.residual[var3] / this.weight[var3];
                    this.sumOfSquaresError += this.residual[var3] * this.residual[var3];
                    this.chiSquare += this.residualW[var3] * this.residualW[var3];
                }

                this.reducedChiSquare = this.chiSquare / (double)this.degreesOfFreedom;
                this.calcDurbinWatson();
            }
        }
    }

    public void constantPlot(String var1, String var2) {
        this.xLegend = var1;
        this.yLegend = var2;
        this.legendCheck = true;
        this.constant();
        if (!this.suppressPrint) {
            this.print();
        }

        int var3 = 0;
        if (this.xData.length < 2) {
            var3 = this.plotXY();
        }

        if (var3 != -2 && !this.suppressYYplot) {
            this.plotYY();
        }

    }

    public void constantPlot() {
        this.constant();
        if (!this.suppressPrint) {
            this.print();
        }

        boolean var1 = false;
        if (this.xData.length < 2) {
            int var2 = this.plotXY();
        }

    }

    public void linear() {
        if (this.xErrorsEntered) {
            this.linearDual();
        } else {
            this.linearMono();
        }

    }

    public void linearMono() {
        if (this.multipleY) {
            throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
        } else {
            this.lastMethod = 0;
            this.linNonLin = true;
            this.dualErrorsRequired = false;
            this.nParam = this.nXarrays + 1;
            this.degreesOfFreedom = this.nData - this.nParam;
            if (this.degreesOfFreedom < 1 && !this.ignoreDofFcheck) {
                throw new IllegalArgumentException("Degrees of freedom must be greater than 0");
            } else {
                double[][] var1 = new double[this.nParam][this.nData];

                int var2;
                for(var2 = 0; var2 < this.nData; ++var2) {
                    var1[0][var2] = 1.0D;
                }

                for(var2 = 1; var2 < this.nParam; ++var2) {
                    for(int var3 = 0; var3 < this.nData; ++var3) {
                        var1[var2][var3] = this.xData[var2 - 1][var3];
                    }
                }

                this.best = new double[this.nParam];
                this.bestSd = new double[this.nParam];
                this.tValues = new double[this.nParam];
                this.pValues = new double[this.nParam];
                this.generalLinear(var1);
                this.generalLinearStats(var1);
            }
        }
    }

    protected void linearDual() {
        this.simplexFlag = 3;
        this.lastMethod = 0;
        this.userSupplied = false;
        this.zeroCheck = false;
        this.nParam = this.nXarrays + 1;
        this.degreesOfFreedom = this.nData - this.nParam;
        if (this.degreesOfFreedom < 1 && !this.ignoreDofFcheck) {
            throw new IllegalArgumentException("Degrees of freedom must be greater than 0");
        } else {
            this.dualErrorsRequired = true;
            this.nonLinStatsNeeded = false;
            Regression var1 = new Regression(this.xData, this.yData);
            var1.linear();
            double[] var2 = var1.getBestEstimates();
            LinearXYDEfunction var3 = new LinearXYDEfunction();
            var3.setNterms(this.nParam);
            var3.setYerrors(this.yErrors);
            var3.setXerrors(this.xErrors);
            this.linNonLin = false;
            double[] var4 = new double[this.nParam];

            for(int var5 = 0; var5 < this.nParam; ++var5) {
                var4[var5] = var2[var5] * 0.1D;
                if (var4[var5] == 0.0D) {
                    var4[var5] = Stat.mean(var2) * 0.1D;
                }
            }

            this.nelderMead(var3, (Object)null, var2, var4, this.fTol, this.nMax);
            double[][] var8 = new double[this.nParam][this.nData];
            this.linNonLin = true;

            int var6;
            for(var6 = 0; var6 < this.nData; ++var6) {
                var8[0][var6] = 1.0D;
            }

            for(var6 = 1; var6 < this.nParam; ++var6) {
                for(int var7 = 0; var7 < this.nData; ++var7) {
                    var8[var6][var7] = this.xData[var6 - 1][var7];
                }
            }

            this.tValues = new double[this.nParam];
            this.pValues = new double[this.nParam];
            this.generalLinearStats(var8);
            this.dualErrorsRequired = false;
            this.nonLinStatsNeeded = true;
        }
    }

    public void linearPlot(String var1, String var2) {
        if (this.xErrorsEntered) {
            this.linearPlotDual(var1, var2);
        } else {
            this.linearPlotMono(var1, var2);
        }

    }

    public void linearPlotMono(String var1, String var2) {
        this.xLegend = var1;
        this.yLegend = var2;
        this.legendCheck = true;
        this.dualErrorsRequired = false;
        this.nonLinStatsNeeded = false;
        this.linearMono();
        if (!this.suppressPrint) {
            this.print();
        }

        int var3 = 0;
        if (this.xData.length < 2) {
            var3 = this.plotXY();
        }

        if (var3 != -2 && !this.suppressYYplot) {
            this.plotYY();
        }

    }

    public void linearPlotDual(String var1, String var2) {
        this.xLegend = var1;
        this.yLegend = var2;
        this.legendCheck = true;
        this.linearDual();
        if (!this.suppressPrint) {
            this.print();
        }

        int var3 = 0;
        if (this.xData.length < 2) {
            var3 = this.plotXY();
        }

        if (var3 != -2 && !this.suppressYYplot) {
            this.plotYY();
        }

    }

    public void linearPlot() {
        if (this.xErrorsEntered) {
            this.linearPlotDual();
        } else {
            this.linearPlotMono();
        }

    }

    public void linearPlotMono() {
        this.linearMono();
        if (!this.suppressPrint) {
            this.print();
        }

        int var1 = 0;
        if (this.xData.length < 2) {
            var1 = this.plotXY();
        }

        if (var1 != -2 && !this.suppressYYplot) {
            this.plotYY();
        }

    }

    public void linearPlotDual() {
        this.linearDual();
        if (!this.suppressPrint) {
            this.print();
        }

        int var1 = 0;
        this.xErrorsEntered = false;
        this.simplexFlag = 1;
        if (this.xData.length < 2) {
            var1 = this.plotXY();
        }

        if (var1 != -2 && !this.suppressYYplot) {
            this.plotYY();
        }

        this.xErrorsEntered = true;
    }

    public void linear(double var1) {
        if (this.xErrorsEntered) {
            this.linearDual(var1);
        } else {
            this.linearMono(var1);
        }

    }

    public void linearMono(double var1) {
        if (this.multipleY) {
            throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
        } else {
            this.lastMethod = 47;
            this.fixedInterceptL = var1;
            this.linNonLin = true;
            this.dualErrorsRequired = false;
            this.nParam = this.nXarrays;
            this.degreesOfFreedom = this.nData - this.nParam;
            if (this.degreesOfFreedom < 1 && !this.ignoreDofFcheck) {
                throw new IllegalArgumentException("Degrees of freedom must be greater than 0");
            } else {
                double[][] var3 = new double[this.nParam][this.nData];

                int var4;
                for(var4 = 0; var4 < this.nData; ++var4) {
                    this.yData[var4] -= var1;
                }

                for(var4 = 0; var4 < this.nParam; ++var4) {
                    for(int var5 = 0; var5 < this.nData; ++var5) {
                        var3[var4][var5] = this.xData[var4][var5];
                    }
                }

                this.best = new double[this.nParam];
                this.bestSd = new double[this.nParam];
                this.tValues = new double[this.nParam];
                this.pValues = new double[this.nParam];
                this.generalLinear(var3);
                this.generalLinearStats(var3);

                for(var4 = 0; var4 < this.nData; ++var4) {
                    this.yData[var4] += var1;
                    this.yCalc[var4] += var1;
                }

            }
        }
    }

    public void linearDual(double var1) {
        if (this.multipleY) {
            throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
        } else {
            this.lastMethod = 47;
            this.simplexFlag = 3;
            this.fixedInterceptL = var1;
            this.dualErrorsRequired = true;
            this.nonLinStatsNeeded = false;
            this.nParam = this.nXarrays;
            this.userSupplied = false;
            this.zeroCheck = false;
            this.degreesOfFreedom = this.nData - this.nParam;
            if (this.degreesOfFreedom < 1 && !this.ignoreDofFcheck) {
                throw new IllegalArgumentException("Degrees of freedom must be greater than 0");
            } else {
                Regression var3 = new Regression(this.xData, this.yData);
                var3.linear(var1);
                double[] var4 = var3.getBestEstimates();
                LinearIXYDEfunction var5 = new LinearIXYDEfunction();
                var5.setNparam(this.nParam);
                var5.setYerrors(this.yErrors);
                var5.setXerrors(this.xErrors);
                var5.setIntercept(var1);
                this.linNonLin = false;
                double[] var6 = new double[this.nParam];

                for(int var7 = 0; var7 < this.nParam; ++var7) {
                    var6[var7] = var4[var7] * 0.1D;
                    if (var6[var7] == 0.0D) {
                        var6[var7] = Stat.mean(var4) * 0.1D;
                    }
                }

                this.nelderMead(var5, (Object)null, var4, var6, this.fTol, this.nMax);
                this.linNonLin = true;
                double[][] var10 = new double[this.nParam][this.nData];

                int var8;
                for(var8 = 0; var8 < this.nData; ++var8) {
                    this.yData[var8] -= var1;
                }

                for(var8 = 0; var8 < this.nParam; ++var8) {
                    for(int var9 = 0; var9 < this.nData; ++var9) {
                        var10[var8][var9] = this.xData[var8][var9];
                    }
                }

                this.bestSd = new double[this.nParam];
                this.tValues = new double[this.nParam];
                this.pValues = new double[this.nParam];
                this.generalLinearStats(var10);

                for(var8 = 0; var8 < this.nData; ++var8) {
                    this.yData[var8] += var1;
                    this.yCalc[var8] += var1;
                }

                this.dualErrorsRequired = false;
                this.nonLinStatsNeeded = true;
            }
        }
    }

    public void linearPlot(double var1, String var3, String var4) {
        if (this.xErrorsEntered) {
            this.linearPlotDual(var1, var3, var4);
        } else {
            this.linearPlotMono(var1, var3, var4);
        }

    }

    public void linearPlotMono(double var1, String var3, String var4) {
        this.xLegend = var3;
        this.yLegend = var4;
        this.legendCheck = true;
        this.linearMono(var1);
        if (!this.suppressPrint) {
            this.print();
        }

        int var5 = 0;
        if (this.xData.length < 2) {
            var5 = this.plotXY();
        }

        if (var5 != -2 && !this.suppressYYplot) {
            this.plotYY();
        }

    }

    public void linearPlotDual(double var1, String var3, String var4) {
        this.xLegend = var3;
        this.yLegend = var4;
        this.legendCheck = true;
        this.linearDual(var1);
        this.xErrorsEntered = false;
        if (!this.suppressPrint) {
            this.print();
        }

        int var5 = 0;
        if (this.xData.length < 2) {
            var5 = this.plotXY();
        }

        if (var5 != -2 && !this.suppressYYplot) {
            this.plotYY();
        }

        this.xErrorsEntered = true;
    }

    public void linearPlot(double var1) {
        if (this.xErrorsEntered) {
            this.linearPlotDual(var1);
        } else {
            this.linearPlotMono(var1);
        }

    }

    public void linearPlotMono(double var1) {
        this.linearMono(var1);
        if (!this.suppressPrint) {
            this.print();
        }

        int var3 = 0;
        if (this.xData.length < 2) {
            var3 = this.plotXY();
        }

        if (var3 != -2 && !this.suppressYYplot) {
            this.plotYY();
        }

    }

    public void linearPlotDual(double var1) {
        this.xErrorsEntered = true;
        this.linearDual(var1);
        this.linNonLin = true;
        if (!this.suppressPrint) {
            this.print();
        }

        int var3 = 0;
        this.xErrorsEntered = false;
        if (this.xData.length < 2) {
            var3 = this.plotXY();
        }

        if (var3 != -2 && !this.suppressYYplot) {
            this.plotYY();
        }

        this.xErrorsEntered = true;
    }

    public void polynomial(int var1) {
        if (this.xErrorsEntered) {
            this.polynomialDual(var1);
        } else {
            this.polynomialMono(var1);
        }

    }

    protected void polynomialMono(int var1) {
        if (this.multipleY) {
            throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
        } else if (this.nXarrays > 1) {
            throw new IllegalArgumentException("This class will only perform a polynomial regression on a single x array");
        } else if (var1 < 1) {
            throw new IllegalArgumentException("Polynomial degree must be greater than zero");
        } else {
            this.lastMethod = 1;
            this.linNonLin = true;
            this.dualErrorsRequired = false;
            this.nParam = var1 + 1;
            this.degreesOfFreedom = this.nData - this.nParam;
            if (this.degreesOfFreedom < 1 && !this.ignoreDofFcheck) {
                throw new IllegalArgumentException("Degrees of freedom must be greater than 0");
            } else {
                double[][] var2 = new double[this.nParam][this.nData];

                int var3;
                for(var3 = 0; var3 < this.nData; ++var3) {
                    var2[0][var3] = 1.0D;
                }

                for(var3 = 0; var3 < this.nData; ++var3) {
                    var2[1][var3] = this.xData[0][var3];
                }

                for(var3 = 2; var3 < this.nParam; ++var3) {
                    for(int var4 = 0; var4 < this.nData; ++var4) {
                        var2[var3][var4] = Math.pow(this.xData[0][var4], (double)var3);
                    }
                }

                this.best = new double[this.nParam];
                this.bestSd = new double[this.nParam];
                this.tValues = new double[this.nParam];
                this.pValues = new double[this.nParam];
                this.generalLinear(var2);
                this.generalLinearStats(var2);
            }
        }
    }

    protected void polynomialDual(int var1) {
        if (this.multipleY) {
            throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
        } else if (this.nXarrays > 1) {
            throw new IllegalArgumentException("This class will only perform a polynomial regression on a single x array");
        } else if (var1 < 1) {
            throw new IllegalArgumentException("Polynomial degree must be greater than zero");
        } else {
            this.lastMethod = 1;
            this.linNonLin = true;
            this.dualErrorsRequired = true;
            this.nonLinStatsNeeded = false;
            this.nParam = var1 + 1;
            this.degreesOfFreedom = this.nData - this.nParam;
            if (this.degreesOfFreedom < 1 && !this.ignoreDofFcheck) {
                throw new IllegalArgumentException("Degrees of freedom must be greater than 0");
            } else {
                Regression var2 = new Regression(this.xData, this.yData);
                var2.polynomial(var1);
                double[] var3 = var2.getBestEstimates();
                PolyXYDEfunction var4 = new PolyXYDEfunction();
                var4.setDeg(var1);
                var4.setYerrors(this.yErrors);
                var4.setXerrors(this.xErrors);
                this.linNonLin = false;
                double[] var5 = new double[this.nParam];

                for(int var6 = 0; var6 < this.nParam; ++var6) {
                    var5[var6] = var3[var6] * 0.1D;
                    if (var5[var6] == 0.0D) {
                        var5[var6] = Stat.mean(var3) * 0.1D;
                    }
                }

                this.nelderMead(var4, (Object)null, var3, var5, this.fTol, this.nMax);
                this.linNonLin = true;
                double[][] var9 = new double[this.nParam][this.nData];

                int var7;
                for(var7 = 0; var7 < this.nData; ++var7) {
                    var9[0][var7] = 1.0D;
                }

                for(var7 = 0; var7 < this.nData; ++var7) {
                    var9[1][var7] = this.xData[0][var7];
                }

                for(var7 = 2; var7 < this.nParam; ++var7) {
                    for(int var8 = 0; var8 < this.nData; ++var8) {
                        var9[var7][var8] = Math.pow(this.xData[0][var8], (double)var7);
                    }
                }

                this.bestSd = new double[this.nParam];
                this.tValues = new double[this.nParam];
                this.pValues = new double[this.nParam];
                this.generalLinearStats(var9);
                this.dualErrorsRequired = false;
                this.nonLinStatsNeeded = true;
            }
        }
    }

    public void polynomialPlot(int var1, String var2, String var3) {
        this.xLegend = var2;
        this.yLegend = var3;
        this.legendCheck = true;
        this.polynomial(var1);
        if (!this.suppressPrint) {
            this.print();
        }

        int var4 = this.plotXY();
        if (var4 != -2 && !this.suppressYYplot) {
            this.plotYY();
        }

    }

    public void polynomialPlot(int var1) {
        this.polynomial(var1);
        if (!this.suppressPrint) {
            this.print();
        }

        int var2 = this.plotXY();
        if (var2 != -2 && !this.suppressYYplot) {
            this.plotYY();
        }

    }

    public void polynomial(int var1, double var2) {
        if (this.xErrorsEntered) {
            this.polynomialDual(var1, var2);
        } else {
            this.polynomialMono(var1, var2);
        }

    }

    protected void polynomialMono(int var1, double var2) {
        if (this.multipleY) {
            throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
        } else if (this.nXarrays > 1) {
            throw new IllegalArgumentException("This class will only perform a polynomial regression on a single x array");
        } else if (var1 < 1) {
            throw new IllegalArgumentException("Polynomial degree must be greater than zero");
        } else {
            this.lastMethod = 48;
            this.fixedInterceptP = var2;
            this.linNonLin = true;
            this.dualErrorsRequired = false;
            this.nParam = var1;
            this.degreesOfFreedom = this.nData - this.nParam;
            if (this.degreesOfFreedom < 1 && !this.ignoreDofFcheck) {
                throw new IllegalArgumentException("Degrees of freedom must be greater than 0");
            } else {
                double[][] var4 = new double[this.nParam][this.nData];

                int var5;
                for(var5 = 0; var5 < this.nData; ++var5) {
                    this.yData[var5] -= var2;
                }

                for(var5 = 0; var5 < this.nData; ++var5) {
                    var4[0][var5] = this.xData[0][var5];
                }

                for(var5 = 1; var5 < this.nParam; ++var5) {
                    for(int var6 = 0; var6 < this.nData; ++var6) {
                        var4[var5][var6] = Math.pow(this.xData[0][var6], (double)(var5 + 1));
                    }
                }

                this.best = new double[this.nParam];
                this.bestSd = new double[this.nParam];
                this.tValues = new double[this.nParam];
                this.pValues = new double[this.nParam];
                this.generalLinear(var4);
                this.generalLinearStats(var4);

                for(var5 = 0; var5 < this.nData; ++var5) {
                    this.yData[var5] += var2;
                    this.yCalc[var5] += var2;
                }

            }
        }
    }

    protected void polynomialDual(int var1, double var2) {
        if (this.multipleY) {
            throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
        } else if (this.nXarrays > 1) {
            throw new IllegalArgumentException("This class will only perform a polynomial regression on a single x array");
        } else if (var1 < 1) {
            throw new IllegalArgumentException("Polynomial degree must be greater than zero");
        } else {
            this.lastMethod = 48;
            this.fixedInterceptP = var2;
            this.linNonLin = true;
            this.dualErrorsRequired = true;
            this.nonLinStatsNeeded = false;
            this.nParam = var1;
            this.degreesOfFreedom = this.nData - this.nParam;
            if (this.degreesOfFreedom < 1 && !this.ignoreDofFcheck) {
                throw new IllegalArgumentException("Degrees of freedom must be greater than 0");
            } else {
                Regression var4 = new Regression(this.xData, this.yData);
                var4.polynomial(var1, var2);
                double[] var5 = var4.getBestEstimates();
                PolyIXYDEfunction var6 = new PolyIXYDEfunction();
                var6.setDeg(var1);
                var6.setIntercept(var2);
                var6.setYerrors(this.yErrors);
                var6.setXerrors(this.xErrors);
                this.linNonLin = false;
                double[] var7 = new double[this.nParam];

                for(int var8 = 0; var8 < this.nParam; ++var8) {
                    var7[var8] = var5[var8] * 0.1D;
                    if (var7[var8] == 0.0D) {
                        var7[var8] = Stat.mean(var5) * 0.1D;
                    }
                }

                this.nelderMead(var6, (Object)null, var5, var7, this.fTol, this.nMax);
                this.linNonLin = true;
                double[][] var11 = new double[this.nParam][this.nData];

                int var9;
                for(var9 = 0; var9 < this.nData; ++var9) {
                    this.yData[var9] -= var2;
                }

                for(var9 = 0; var9 < this.nData; ++var9) {
                    var11[0][var9] = this.xData[0][var9];
                }

                for(var9 = 1; var9 < this.nParam; ++var9) {
                    for(int var10 = 0; var10 < this.nData; ++var10) {
                        var11[var9][var10] = Math.pow(this.xData[0][var10], (double)(var9 + 1));
                    }
                }

                this.bestSd = new double[this.nParam];
                this.tValues = new double[this.nParam];
                this.pValues = new double[this.nParam];
                this.generalLinearStats(var11);

                for(var9 = 0; var9 < this.nData; ++var9) {
                    this.yData[var9] += var2;
                    this.yCalc[var9] += var2;
                }

                this.dualErrorsRequired = false;
                this.nonLinStatsNeeded = true;
            }
        }
    }

    public void polynomialPlot(int var1, double var2, String var4, String var5) {
        this.xLegend = var4;
        this.yLegend = var5;
        this.legendCheck = true;
        this.polynomial(var1, var2);
        if (!this.suppressPrint) {
            this.print();
        }

        int var6 = this.plotXY();
        if (var6 != -2 && !this.suppressYYplot) {
            this.plotYY();
        }

    }

    public void polynomialPlot(int var1, double var2) {
        this.polynomial(var1, var2);
        if (!this.suppressPrint) {
            this.print();
        }

        int var4 = this.plotXY();
        if (var4 != -2 && !this.suppressYYplot) {
            this.plotYY();
        }

    }

    public ArrayList<Object> bestPolynomial() {
        return this.polynomialBest(0, 0.0D / 0.0);
    }

    public ArrayList<Object> bestPolynomial(double var1) {
        return this.polynomialBest(2, var1);
    }

    public ArrayList<Object> bestPolynomialPlot() {
        return this.polynomialBest(1, 0.0D / 0.0);
    }

    public ArrayList<Object> bestPolynomialPlot(String var1, String var2) {
        this.xLegend = var1;
        this.yLegend = var2;
        this.legendCheck = true;
        return this.polynomialBest(1, 0.0D / 0.0);
    }

    public ArrayList<Object> bestPolynomialPlot(double var1) {
        return this.polynomialBest(3, var1);
    }

    public ArrayList<Object> bestPolynomialPlot(double var1, String var3, String var4) {
        this.xLegend = var3;
        this.yLegend = var4;
        this.legendCheck = true;
        return this.polynomialBest(3, var1);
    }

    public ArrayList<Object> polynomialBest(int var1, double var2) {
        if (this.multipleY) {
            throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
        } else if (this.nXarrays > 1) {
            throw new IllegalArgumentException("This class will only perform a polynomial regression on a single x array");
        } else {
            this.bestPolyFlag = true;
            this.linNonLin = true;
            this.dualErrorsRequired = false;
            this.ignoreDofFcheck = true;
            this.suppressErrorMessages = true;
            ArrayList var4 = null;
            new ArrayList();
            int var6 = this.nData - 2;
            int var7 = 0;
            Regression var8 = null;
            if (this.xErrorsEntered) {
                var8 = new Regression(this.xData, this.yData, this.xErrors, this.yErrors);
            } else if (this.weightOpt) {
                var8 = new Regression(this.xData, this.yData, this.yErrors);
            } else {
                var8 = new Regression(this.xData, this.yData);
            }

            var8.ignoreDofFcheck();
            var8.suppressErrorMessages();
            double[] var9 = new double[var6 + 1];
            double[] var10 = new double[var6 + 1];
            double[] var11 = new double[var6 + 1];
            double[] var12 = new double[var6 + 1];
            double var13 = 0.0D;
            if (var1 != 2 && var1 != 3) {
                if (this.weightOpt) {
                    var13 = Stat.mean(this.yData, this.yErrors);
                } else {
                    var13 = Stat.mean(this.yData);
                }
            } else {
                var13 = var2;
            }

            double var15 = 0.0D;
            int var17;
            double var18;
            if (this.weightOpt) {
                for(var17 = 0; var17 < this.nData; ++var17) {
                    var18 = (this.yData[var17] - var13) / this.yErrors[var17];
                    var15 += var18 * var18;
                }
            } else {
                for(var17 = 0; var17 < this.nData; ++var17) {
                    var18 = this.yData[var17] - var13;
                    var15 += var18 * var18;
                }
            }

            var9[0] = var15;

            for(var17 = 1; var17 <= var6; ++var17) {
                if (var1 != 2 && var1 != 3) {
                    var8.polynomial(var17);
                } else {
                    var8.polynomial(var17, var2);
                }

                var9[var17] = var8.getChiSquare();
                var4 = testOfAdditionalTerms_ArrayList(var9[var17 - 1], var17 - 1, var9[var17], var17, this.nData, this.fProbSignificance);
                var10[var17] = (Double)var4.get(0);
                var11[var17] = (Double)var4.get(1);
                var12[var17] = (Double)var4.get(8);
                ++var7;
            }

            var17 = this.indexOfMaximum(var10);
            int var30 = this.indexOfMinimum(var11);
            this.bestPolynomialDegree = var17;
            if (var17 != var30) {
                double var19 = var12[var17] - var10[var17];
                double var21 = var12[var30] - var10[var30];
                if (var19 < var21) {
                    this.bestPolynomialDegree = var30;
                }

                if (this.bestPolynomialDegree == this.nData - 2) {
                    this.bestPolynomialDegree = var17;
                }

                System.out.println("The best fit as indicated by the F-ratio (degree = " + var17 + ") does not agree with that indicated by the F-ratio probability (degree = " + var30 + ")");
                System.out.println("Examine the contents of the returned ArrayList or the contents of the BestPolynomialOutput text file if a plot method is being used.");
            }

            if (this.bestPolynomialDegree == this.nData - 2) {
                this.bestPolyTooFewN = true;
                String var31 = "WARNING!!\n\nYou probably do not have enough data points to determine the best fit polynomial.\nThe returned fit of degree " + var17 + " is the maximum degree your data allow to be examined.\nMore data may suggest a polynomial of higher degree is a better fit.\n";
                System.out.println(var31);
                String var20 = var31 + "\nClick on OK to continue.";
                Db.show(var20);
            }

            this.bestPolyArray.add(this.bestPolynomialDegree);
            this.bestPolyArray.add(var7);
            int[] var32 = new int[var7];
            int[] var33 = new int[var7];
            double[] var34 = new double[var7];
            double[] var22 = new double[var7];
            double[] var23 = new double[var7];
            double[] var24 = new double[var7];
            double[] var25 = new double[var7];

            for(int var26 = 0; var26 < var7; ++var26) {
                var32[var26] = var26;
                var33[var26] = var26 + 1;
                var34[var26] = var9[var26];
                var22[var26] = var9[var26 + 1];
                var23[var26] = var10[var26 + 1];
                var24[var26] = var11[var26 + 1];
                var25[var26] = var12[var26 + 1];
            }

            this.bestPolyArray.add(var32);
            this.bestPolyArray.add(var33);
            this.bestPolyArray.add(var34);
            this.bestPolyArray.add(var22);
            this.bestPolyArray.add(var23);
            this.bestPolyArray.add(var24);
            this.bestPolyArray.add(var25);
            this.bestPolyArray.add(this.fProbSignificance);
            label86:
            switch(var1) {
                case 0:
                    switch(this.bestPolynomialDegree) {
                        case 0:
                            this.constant();
                            break label86;
                        default:
                            this.polynomial(this.bestPolynomialDegree);
                            break label86;
                    }
                case 1:
                    switch(this.bestPolynomialDegree) {
                        case 0:
                            this.constantPlot();
                            break label86;
                        default:
                            this.polynomialPlot(this.bestPolynomialDegree);
                            break label86;
                    }
                case 2:
                    switch(this.bestPolynomialDegree) {
                        case 0:
                            break label86;
                        default:
                            this.polynomial(this.bestPolynomialDegree, var2);
                            break label86;
                    }
                case 3:
                    switch(this.bestPolynomialDegree) {
                        case 0:
                            double[][] var35 = new double[][]{this.xData[0], this.yData, this.xData[0], new double[this.nData]};

                            for(int var27 = 0; var27 < this.nData; ++var27) {
                                var35[3][var27] = var13;
                            }

                            PlotGraph var36 = new PlotGraph(var35);
                            int[] var28 = new int[]{0, 1};
                            var36.setLine(var28);
                            int[] var10000 = new int[]{1, 0};
                            var36.setPoint(var28);
                            var36.setXaxisLegend("x-axis values");
                            var36.setYaxisLegend("y-axis values");
                            var36.setGraphTitle("Polynomial fit with degree = 0 and a fixed intercept");
                            var36.setGraphTitle2("points - experimental values; line - fixed intercept value");
                            var36.plot();
                            break;
                        default:
                            this.polynomialPlot(this.bestPolynomialDegree, var2);
                    }
            }

            this.suppressErrorMessages = false;
            this.ignoreDofFcheck = false;
            return this.bestPolyArray;
        }
    }

    public int indexOfMaximum(double[] var1) {
        int var2 = 2;
        double var3 = var1[2];
        int var5 = var1.length;

        for(int var6 = 3; var6 < var5; ++var6) {
            if (var1[var6] > var3) {
                var2 = var6;
                var3 = var1[var6];
            }
        }

        return var2;
    }

    public int indexOfMinimum(double[] var1) {
        int var2 = 2;
        double var3 = var1[2];
        int var5 = var1.length;

        for(int var6 = 3; var6 < var5; ++var6) {
            if (var1[var6] < var3) {
                var2 = var6;
                var3 = var1[var6];
            }
        }

        return var2;
    }

    public void setFtestSignificance(double var1) {
        this.fProbSignificance = var1;
    }

    public double getFtestSignificance(double var1) {
        return this.fProbSignificance;
    }

    public void nonIntegerPolynomial(int var1) {
        this.fitNonIntegerPolynomial(var1, 0);
    }

    public void nonIntegerPolynomial() {
        this.fitNonIntegerPolynomial(3, 0);
    }

    public void nonIntegerPolynomialPlot(int var1) {
        this.fitNonIntegerPolynomial(var1, 1);
    }

    public void nonIntegerPolynomialPlot() {
        this.fitNonIntegerPolynomial(3, 1);
    }

    public void nonIntegerPolynomialPlot(int var1, String var2, String var3) {
        this.xLegend = var2;
        this.yLegend = var3;
        this.legendCheck = true;
        this.fitNonIntegerPolynomial(var1, 1);
    }

    public void nonIntegerPolynomialPlot(String var1, String var2) {
        this.xLegend = var1;
        this.xLegend = var1;
        this.yLegend = var2;
        this.legendCheck = true;
        this.fitNonIntegerPolynomial(3, 1);
    }

    protected void fitNonIntegerPolynomial(int var1, int var2) {
        if (this.xErrorsEntered) {
            this.fitNonIntegerPolynomialDual(var1, var2);
        } else {
            this.fitNonIntegerPolynomialMono(var1, var2);
        }

    }

    protected void fitNonIntegerPolynomialMono(int var1, int var2) {
        if (this.multipleY) {
            throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
        } else {
            this.lastMethod = 50;
            this.userSupplied = false;
            this.linNonLin = false;
            this.dualErrorsRequired = false;
            this.simplexFlag = 1;
            this.nonLinStatsNeeded = true;
            this.zeroCheck = false;
            int var3 = var1 - 1;
            this.nParam = 2 * var1 - 1;
            this.degreesOfFreedom = this.nData - this.nParam;
            if (this.degreesOfFreedom < 1 && !this.ignoreDofFcheck) {
                throw new IllegalArgumentException("Degrees of freedom must be greater than 0");
            } else {
                sort(this.xData[0], this.yData, this.weight);
                Regression var4 = new Regression(this.xData[0], this.yData, this.weight);
                var4.polynomial(var3);
                double[] var5 = new double[this.nParam];
                double[] var6 = new double[this.nParam];
                double[] var7 = var4.getBestEstimates();
                double var8 = 0.0D;

                int var10;
                for(var10 = 0; var10 < var1; ++var10) {
                    var5[var10] = var7[var10];
                    var8 += var5[var10];
                }

                var8 /= (double)var1;

                for(var10 = 0; var10 < var1; ++var10) {
                    var6[var10] = var5[var10] * 0.1D;
                    if (var6[var10] == 0.0D) {
                        var6[var10] = 0.1D * var8;
                    }
                }

                double var15 = 1.0D;

                for(int var12 = var1; var12 < this.nParam; ++var12) {
                    var5[var12] = var15;
                    var6[var12] = var5[var12] * 0.1D;
                    ++var15;
                }

                NonIntegerPolyFunction var16 = new NonIntegerPolyFunction();
                var16.setNterms(var1);
                this.simplexFlag = 1;
                this.nonLinStatsNeeded = true;
                this.dualErrorsRequired = false;
                this.nelderMead(var16, (Object)null, var5, var6, this.fTol, this.nMax);
                if (var2 == 1) {
                    if (!this.suppressPrint) {
                        this.print();
                    }

                    int var14 = this.plotXY((RegressionFunction)var16);
                    if (var14 != -2 && !this.suppressYYplot) {
                        this.plotYY();
                    }
                }

            }
        }
    }

    protected void fitNonIntegerPolynomialDual(int var1, int var2) {
        if (this.multipleY) {
            throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
        } else {
            this.lastMethod = 50;
            this.userSupplied = false;
            this.simplexFlag = 3;
            this.linNonLin = false;
            this.dualErrorsRequired = true;
            this.nonLinStatsNeeded = true;
            this.zeroCheck = false;
            int var3 = var1 - 1;
            this.nParam = 2 * var1 - 1;
            this.degreesOfFreedom = this.nData - this.nParam;
            if (this.degreesOfFreedom < 1 && !this.ignoreDofFcheck) {
                throw new IllegalArgumentException("Degrees of freedom must be greater than 0");
            } else {
                sort(this.xData[0], this.yData, this.weight);
                Regression var4 = new Regression(this.xData[0], this.yData, this.weight);
                var4.polynomial(var3);
                double[] var5 = new double[this.nParam];
                double[] var6 = new double[this.nParam];
                double[] var7 = var4.getBestEstimates();
                double var8 = 0.0D;

                int var10;
                for(var10 = 0; var10 < var1; ++var10) {
                    var5[var10] = var7[var10];
                    var8 += var5[var10];
                }

                var8 /= (double)var1;

                for(var10 = 0; var10 < var1; ++var10) {
                    var6[var10] = var5[var10] * 0.1D;
                    if (var6[var10] == 0.0D) {
                        var6[var10] = 0.1D * var8;
                    }
                }

                double var15 = 1.0D;

                for(int var12 = var1; var12 < this.nParam; ++var12) {
                    var5[var12] = var15;
                    var6[var12] = var5[var12] * 0.1D;
                    ++var15;
                }

                NonIntegerPolyFunctionDual var16 = new NonIntegerPolyFunctionDual();
                var16.setNterms(var1);
                var16.setYerrors(this.yErrors);
                var16.setXerrors(this.xErrors);
                this.nelderMead(var16, (Object)null, var5, var6, this.fTol, this.nMax);
                if (var2 == 1) {
                    if (!this.suppressPrint) {
                        this.print();
                    }

                    int var14 = this.plotXY((RegressionFunction3)var16);
                    if (var14 != -2 && !this.suppressYYplot) {
                        this.plotYY();
                    }
                }

                this.dualErrorsRequired = false;
            }
        }
    }

    public void linearGeneral() {
        if (this.xErrorsEntered) {
            this.linearGeneralDual();
        } else {
            this.linearGeneralMono();
        }

    }

    public void linearGeneralMono() {
        if (this.multipleY) {
            throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
        } else {
            this.lastMethod = 2;
            this.linNonLin = true;
            this.nParam = this.nXarrays;
            this.degreesOfFreedom = this.nData - this.nParam;
            if (this.degreesOfFreedom < 1 && !this.ignoreDofFcheck) {
                throw new IllegalArgumentException("Degrees of freedom must be greater than 0");
            } else {
                this.best = new double[this.nParam];
                this.bestSd = new double[this.nParam];
                this.tValues = new double[this.nParam];
                this.pValues = new double[this.nParam];
                this.generalLinear(this.xData);
                this.generalLinearStats(this.xData);
            }
        }
    }

    public void linearGeneralDual() {
        if (this.multipleY) {
            throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
        } else {
            this.lastMethod = 2;
            this.simplexFlag = 3;
            this.linNonLin = true;
            this.userSupplied = false;
            this.zeroCheck = false;
            this.nParam = this.nXarrays;
            this.degreesOfFreedom = this.nData - this.nParam;
            if (this.degreesOfFreedom < 1 && !this.ignoreDofFcheck) {
                throw new IllegalArgumentException("Degrees of freedom must be greater than 0");
            } else {
                this.best = new double[this.nParam];
                this.dualErrorsRequired = true;
                this.nonLinStatsNeeded = false;
                Regression var1 = new Regression(this.xData, this.yData);
                var1.linearGeneral();
                double[] var2 = var1.getBestEstimates();
                LinearGXYDEfunction var3 = new LinearGXYDEfunction();
                var3.setNterms(this.nParam);
                var3.setYerrors(this.yErrors);
                var3.setXerrors(this.xErrors);
                this.linNonLin = false;
                double[] var4 = new double[this.nParam];

                for(int var5 = 0; var5 < this.nParam; ++var5) {
                    var4[var5] = var2[var5] * 0.1D;
                    if (var4[var5] == 0.0D) {
                        var4[var5] = Stat.mean(var2) * 0.1D;
                    }
                }

                this.nelderMead(var3, (Object)null, var2, var4, this.fTol, this.nMax);
                this.linNonLin = true;
                this.bestSd = new double[this.nParam];
                this.tValues = new double[this.nParam];
                this.pValues = new double[this.nParam];
                this.generalLinearStats(this.xData);
                this.dualErrorsRequired = false;
                this.nonLinStatsNeeded = true;
            }
        }
    }

    public void linearGeneralPlot(String var1, String var2) {
        if (this.xErrorsEntered) {
            this.linearGeneralPlotDual(var1, var2);
        } else {
            this.linearGeneralPlotMono(var1, var2);
        }

    }

    public void linearGeneralPlotMono(String var1, String var2) {
        this.xLegend = var1;
        this.yLegend = var2;
        this.legendCheck = true;
        this.linearGeneral();
        if (!this.suppressPrint) {
            this.print();
        }

        if (!this.suppressYYplot) {
            this.plotYY();
        }

    }

    public void linearGeneralPlotDual(String var1, String var2) {
        this.xLegend = var1;
        this.yLegend = var2;
        this.legendCheck = true;
        this.linearGeneralDual();
        this.linNonLin = true;
        if (!this.suppressPrint) {
            this.print();
        }

        this.xErrorsEntered = false;
        if (!this.suppressYYplot) {
            this.plotYY();
        }

        this.xErrorsEntered = true;
    }

    public void linearGeneralPlot() {
        if (this.xErrorsEntered) {
            this.linearGeneralPlotDual();
        } else {
            this.linearGeneralPlotMono();
        }

    }

    public void linearGeneralPlotMono() {
        this.linearGeneral();
        if (!this.suppressPrint) {
            this.print();
        }

        if (!this.suppressYYplot) {
            this.plotYY();
        }

    }

    public void linearGeneralPlotDual() {
        this.linearGeneralDual();
        this.linNonLin = true;
        if (!this.suppressPrint) {
            this.print();
        }

        this.xErrorsEntered = false;
        if (!this.suppressYYplot) {
            this.plotYY();
        }

        this.xErrorsEntered = true;
    }

    protected void generalLinear(double[][] var1) {
        if (this.nData <= this.nParam && !this.ignoreDofFcheck) {
            throw new IllegalArgumentException("Number of unknown parameters is greater than or equal to the number of data points");
        } else {
            double var2 = 0.0D;
            double var4 = 0.0D;
            double var6 = 0.0D;
            double[][] var8 = new double[this.nParam][this.nParam];
            double[][] var10000 = new double[this.nParam][this.nParam];
            double[] var10 = new double[this.nParam];
            double[] var11 = new double[this.nParam];
            int var12;
            int var13;
            if (this.ignoreDofFcheck) {
                this.bestSd = new double[this.nParam];
                this.pseudoSd = new double[this.nParam];
                this.tValues = new double[this.nParam];
                this.pValues = new double[this.nParam];
                this.covar = new double[this.nParam][this.nParam];
                this.corrCoeff = new double[this.nParam][this.nParam];

                for(var12 = 0; var12 < this.nParam; ++var12) {
                    this.bestSd[var12] = 0.0D / 0.0;
                    this.pseudoSd[var12] = 0.0D / 0.0;

                    for(var13 = 0; var13 < this.nParam; ++var13) {
                        this.covar[var12][var13] = 0.0D / 0.0;
                        this.corrCoeff[var12][var13] = 0.0D / 0.0;
                    }
                }
            }

            for(var12 = 0; var12 < this.nParam; ++var12) {
                var4 = 0.0D;

                for(var13 = 0; var13 < this.nData; ++var13) {
                    var4 += this.yData[var13] * var1[var12][var13] / Fmath.square(this.weight[var13]);
                }

                var10[var12] = var4;
            }

            for(var12 = 0; var12 < this.nParam; ++var12) {
                for(var13 = 0; var13 < this.nParam; ++var13) {
                    var4 = 0.0D;

                    for(int var14 = 0; var14 < this.nData; ++var14) {
                        var4 += var1[var12][var14] * var1[var13][var14] / Fmath.square(this.weight[var14]);
                    }

                    var8[var13][var12] = var4;
                }
            }

            Matrix var15 = new Matrix(var8);
            if (this.suppressErrorMessages) {
                var15.suppressErrorMessage();
            }

            var11 = var15.solveLinearSet(var10);

            for(var13 = 0; var13 < this.nParam; ++var13) {
                this.best[var13] = var11[var13];
            }

        }
    }

    protected void nelderMead(Object var1, Object var2, double[] var3, double[] var4, double var5, int var7) {
        if (this.xErrorsEntered && !this.dualErrorsRequired) {
            throw new IllegalArgumentException("The data fitting method called does not support independent variable errors - use a constructor that does not include x errors in its argument list");
        } else {
            int var8 = var3.length;
            if (this.maxConstraintIndex >= var8) {
                throw new IllegalArgumentException("You have entered more constrained parameters (" + this.maxConstraintIndex + ") than minimisation parameters (" + var8 + ")");
            } else {
                this.nlrStatus = true;
                this.nParam = var8;
                int var9 = var8 + 1;
                this.lastSSnoConstraint = 0.0D;
                if (this.scaleOpt < 2) {
                    this.scale = new double[var8];
                }

                if (this.scaleOpt == 2 && this.scale.length != var3.length) {
                    throw new IllegalArgumentException("scale array and initial estimate array are of different lengths");
                } else if (var4.length != var3.length) {
                    throw new IllegalArgumentException("step array length " + var4.length + " and initial estimate array length " + var3.length + " are of different");
                } else {
                    int var10;
                    for(var10 = 0; var10 < var8; ++var10) {
                        if (var4[var10] == 0.0D) {
                            throw new IllegalArgumentException("step " + var10 + " size is zero");
                        }
                    }

                    if (this.minIter > this.nMax) {
                        this.nMax = this.minIter;
                    }

                    if (this.ignoreDofFcheck) {
                        this.bestSd = new double[this.nParam];
                        this.pseudoSd = new double[this.nParam];
                        this.tValues = new double[this.nParam];
                        this.pValues = new double[this.nParam];
                        this.covar = new double[this.nParam][this.nParam];
                        this.corrCoeff = new double[this.nParam][this.nParam];

                        for(var10 = 0; var10 < this.nParam; ++var10) {
                            this.bestSd[var10] = 0.0D / 0.0;
                            this.pseudoSd[var10] = 0.0D / 0.0;

                            for(int var11 = 0; var11 < this.nParam; ++var11) {
                                this.covar[var10][var11] = 0.0D / 0.0;
                                this.corrCoeff[var10][var11] = 0.0D / 0.0;
                            }
                        }
                    }

                    this.startH = new double[var8];
                    this.stepH = new double[var8];
                    this.startSH = new double[var8];
                    this.stepSH = new double[var8];
                    double[] var47 = new double[var8];
                    this.best = new double[var8];
                    this.bestSd = new double[var8];
                    this.tValues = new double[var8];
                    this.pValues = new double[var8];
                    double[][] var48 = new double[var9][var9];
                    double[] var12 = new double[var9];
                    double[] var13 = new double[var9];
                    double[] var14 = new double[var9];
                    double[] var15 = new double[var9];
                    double var16 = 0.0D;

                    int var18;
                    for(var18 = 0; var18 < this.nData; ++var18) {
                        var16 += Math.abs(this.yData[var18]);
                    }

                    var16 /= (double)this.nData;
                    Double var19;
                    int var20;
                    int var21;
                    Integer var49;
                    if (this.penalty) {
                        var49 = (Integer)this.penalties.get(1);
                        this.nConstraints = var49;
                        this.penaltyParam = new int[this.nConstraints];
                        this.penaltyCheck = new int[this.nConstraints];
                        this.constraints = new double[this.nConstraints];
                        var19 = null;
                        var20 = 2;

                        for(var21 = 0; var21 < this.nConstraints; ++var21) {
                            var49 = (Integer)this.penalties.get(var20);
                            this.penaltyParam[var21] = var49;
                            ++var20;
                            var49 = (Integer)this.penalties.get(var20);
                            this.penaltyCheck[var21] = var49;
                            ++var20;
                            var19 = (Double)this.penalties.get(var20);
                            this.constraints[var21] = var19;
                            ++var20;
                        }
                    }

                    int var22;
                    if (this.sumPenalty) {
                        var49 = (Integer)this.sumPenalties.get(1);
                        this.nSumConstraints = var49;
                        this.sumPenaltyParam = new int[this.nSumConstraints][];
                        this.sumPlusOrMinus = new double[this.nSumConstraints][];
                        this.sumPenaltyCheck = new int[this.nSumConstraints];
                        this.sumPenaltyNumber = new int[this.nSumConstraints];
                        this.sumConstraints = new double[this.nSumConstraints];
                        var19 = null;
                        Object var52 = null;
                        Double var54 = null;
                        var22 = 2;

                        for(int var23 = 0; var23 < this.nSumConstraints; ++var23) {
                            var49 = (Integer)this.sumPenalties.get(var22);
                            this.sumPenaltyNumber[var23] = var49;
                            ++var22;
                            int[] var50 = (int[])((int[])this.sumPenalties.get(var22));
                            this.sumPenaltyParam[var23] = var50;
                            ++var22;
                            double[] var55 = (double[])((double[])this.sumPenalties.get(var22));
                            this.sumPlusOrMinus[var23] = var55;
                            ++var22;
                            var49 = (Integer)this.sumPenalties.get(var22);
                            this.sumPenaltyCheck[var23] = var49;
                            ++var22;
                            var54 = (Double)this.sumPenalties.get(var22);
                            this.sumConstraints[var23] = var54;
                            ++var22;
                        }
                    }

                    for(var18 = 0; var18 < var8; ++var18) {
                        var4[var18] = Math.abs(var4[var18]);
                        this.startH[var18] = var3[var18];
                        this.stepH[var18] = var4[var18];
                    }

                    if (this.scaleOpt > 0) {
                        boolean var53 = false;

                        for(int var51 = 0; var51 < var8; ++var51) {
                            if (var3[var51] == 0.0D) {
                                var53 = true;
                            }
                        }

                        if (var53) {
                            System.out.println("Neler and Mead Simplex: a start value of zero precludes scaling");
                            System.out.println("Regression performed without scaling");
                            this.scaleOpt = 0;
                        }
                    }

                    label453:
                    switch(this.scaleOpt) {
                        case 0:
                            var18 = 0;

                            while(true) {
                                if (var18 >= var8) {
                                    break label453;
                                }

                                this.scale[var18] = 1.0D;
                                ++var18;
                            }
                        case 1:
                            var18 = 0;

                            while(true) {
                                if (var18 >= var8) {
                                    break label453;
                                }

                                this.scale[var18] = 1.0D / var3[var18];
                                var4[var18] /= var3[var18];
                                var3[var18] = 1.0D;
                                ++var18;
                            }
                        case 2:
                            var18 = 0;

                            while(true) {
                                if (var18 >= var8) {
                                    break label453;
                                }

                                var4[var18] *= this.scale[var18];
                                var3[var18] *= this.scale[var18];
                                ++var18;
                            }
                        default:
                            throw new IllegalArgumentException("Scaling factor option " + this.scaleOpt + " not recognised");
                    }

                    this.fTol = var5;
                    this.nMax = var7;
                    this.nIter = 0;

                    for(var18 = 0; var18 < var8; ++var18) {
                        this.startSH[var18] = var3[var18];
                        this.stepSH[var18] = var4[var18];
                        this.scale[var18] = this.scale[var18];
                    }

                    double var58 = 0.0D;

                    for(var20 = 0; var20 < var8; ++var20) {
                        var58 = var3[var20];
                        var14[var20] = var58;
                        var15[var20] = var58;
                        var47[var20] = var58;
                    }

                    var20 = this.konvge;

                    for(var21 = 0; var21 < var8; ++var21) {
                        var48[var21][var9 - 1] = var3[var21];
                    }

                    var12[var9 - 1] = this.sumSquares(var1, var3);

                    for(var21 = 0; var21 < var8; ++var21) {
                        var3[var21] += var4[var21];

                        for(var22 = 0; var22 < var8; ++var22) {
                            var48[var22][var21] = var3[var22];
                        }

                        var12[var21] = this.sumSquares(var1, var3);
                        var3[var21] -= var4[var21];
                    }

                    double var56 = 0.0D;
                    double var57 = 0.0D;
                    double var25 = 0.0D;
                    double var27 = 0.0D;
                    int var31 = 0;
                    boolean var32 = false;
                    boolean var33 = false;
                    boolean var34 = true;
                    double var35 = 0.0D;
                    double var37 = 0.0D;
                    double var39 = 0.0D;
                    double var41 = 0.0D;

                    while(true) {
                        int var43;
                        do {
                            do {
                                if (!var34) {
                                    for(var43 = 0; var43 < var8; ++var43) {
                                        var47[var43] = var48[var43][var31];
                                        this.best[var43] = var47[var43] / this.scale[var43];
                                        this.scale[var43] = 1.0D;
                                    }

                                    this.fMin = var56;
                                    this.kRestart = this.konvge - var20;
                                    if (this.xErrorsEntered) {
                                        double[] var62 = new double[this.nXarrays];
                                        double[] var61 = new double[2];

                                        for(int var45 = 0; var45 < this.nData; ++var45) {
                                            for(int var46 = 0; var46 < this.nXarrays; ++var46) {
                                                var62[var46] = this.xData[var46][var45];
                                            }

                                            var61 = ((RegressionFunction3)var1).function(this.best, var62, var45);
                                            this.yCalc[var45] = var61[0];
                                            this.weight[var45] = Math.sqrt(var61[1]);
                                        }

                                        this.effectiveNumber();
                                    }

                                    if (this.nonLinStatsNeeded) {
                                        if (this.statFlag) {
                                            if (!this.ignoreDofFcheck) {
                                                if (this.analyticalDerivative) {
                                                    this.pseudoLinearStats(var1, var2);
                                                } else {
                                                    this.pseudoLinearStats(var1);
                                                }
                                            }
                                        } else {
                                            for(var43 = 0; var43 < var8; ++var43) {
                                                this.bestSd[var43] = 0.0D / 0.0;
                                            }
                                        }
                                    }

                                    return;
                                }

                                var27 = var12[0];
                                var56 = var27;
                                var31 = 0;
                                int var59 = 0;

                                for(var43 = 1; var43 < var9; ++var43) {
                                    if (var12[var43] < var27) {
                                        var27 = var12[var43];
                                        var31 = var43;
                                    }

                                    if (var12[var43] > var56) {
                                        var56 = var12[var43];
                                        var59 = var43;
                                    }
                                }

                                int var44;
                                for(var43 = 0; var43 < var8; ++var43) {
                                    var39 = 0.0D;

                                    for(var44 = 0; var44 < var9; ++var44) {
                                        var39 += var48[var43][var44];
                                    }

                                    var39 -= var48[var43][var59];
                                    var13[var43] = var39 / (double)var8;
                                }

                                for(var43 = 0; var43 < var8; ++var43) {
                                    var14[var43] = (1.0D + this.rCoeff) * var13[var43] - this.rCoeff * var48[var43][var59];
                                }

                                var57 = this.sumSquares(var1, var14);
                                ++this.nIter;
                                if (var57 < var27) {
                                    for(var43 = 0; var43 < var8; ++var43) {
                                        var15[var43] = var14[var43] * (1.0D + this.eCoeff) - this.eCoeff * var13[var43];
                                    }

                                    var25 = this.sumSquares(var1, var15);
                                    ++this.nIter;
                                    if (var25 < var27) {
                                        for(var43 = 0; var43 < var8; ++var43) {
                                            var48[var43][var59] = var15[var43];
                                        }

                                        var12[var59] = var25;
                                    } else {
                                        for(var43 = 0; var43 < var8; ++var43) {
                                            var48[var43][var59] = var14[var43];
                                        }

                                        var12[var59] = var57;
                                    }
                                } else {
                                    int var60 = 0;

                                    for(var43 = 0; var43 < var9; ++var43) {
                                        if (var43 != var59 && var57 > var12[var43]) {
                                            ++var60;
                                        }
                                    }

                                    if (var60 == var8) {
                                        if (var57 <= var12[var59]) {
                                            for(var43 = 0; var43 < var8; ++var43) {
                                                var48[var43][var59] = var14[var43];
                                            }

                                            var12[var59] = var57;
                                        }

                                        for(var43 = 0; var43 < var8; ++var43) {
                                            var15[var43] = this.cCoeff * var48[var43][var59] + (1.0D - this.cCoeff) * var13[var43];
                                        }

                                        var25 = this.sumSquares(var1, var15);
                                        ++this.nIter;
                                        if (var25 > var12[var59]) {
                                            var43 = 0;

                                            while(true) {
                                                if (var43 >= var9) {
                                                    this.nIter += var9;
                                                    break;
                                                }

                                                for(var44 = 0; var44 < var8; ++var44) {
                                                    var48[var44][var43] = 0.5D * (var48[var44][var43] + var48[var44][var31]);
                                                    var47[var44] = var48[var44][var43];
                                                }

                                                var12[var43] = this.sumSquares(var1, var47);
                                                ++var43;
                                            }
                                        } else {
                                            for(var43 = 0; var43 < var8; ++var43) {
                                                var48[var43][var59] = var15[var43];
                                            }

                                            var12[var59] = var25;
                                        }
                                    } else {
                                        for(var43 = 0; var43 < var8; ++var43) {
                                            var48[var43][var59] = var14[var43];
                                        }

                                        var12[var59] = var57;
                                    }
                                }

                                var37 = 0.0D;
                                var56 = var12[0];
                                var31 = 0;

                                for(var43 = 0; var43 < var9; ++var43) {
                                    var37 += var12[var43];
                                    if (var56 > var12[var43]) {
                                        var56 = var12[var43];
                                        var31 = var43;
                                    }
                                }

                                var37 /= (double)var9;
                                var41 = 0.0D;

                                for(var43 = 0; var43 < var9; ++var43) {
                                    var39 = var12[var43] - var37;
                                    var41 += var39 * var39;
                                }

                                var35 = Math.sqrt(var41 / (double)var8);
                                switch(this.minTest) {
                                    case 0:
                                        if (var35 < var5 && this.nIter > this.minIter) {
                                            var34 = false;
                                        }
                                        break;
                                    case 1:
                                        if (Math.sqrt(var56 / (double)this.degreesOfFreedom) < var16 * var5 && this.nIter > this.minIter) {
                                            var34 = false;
                                        }
                                        break;
                                    default:
                                        throw new IllegalArgumentException("Simplex standard deviation test option " + this.minTest + " not recognised");
                                }

                                this.sumOfSquaresError = var56;
                                if (!var34) {
                                    for(var43 = 0; var43 < var8; ++var43) {
                                        var47[var43] = var48[var43][var31];
                                    }

                                    var12[var9 - 1] = var56;
                                    this.simplexSd = var35;
                                    --var20;
                                    if (var20 > 0) {
                                        var34 = true;

                                        for(var43 = 0; var43 < var8; ++var43) {
                                            var47[var43] += var4[var43];

                                            for(var44 = 0; var44 < var8; ++var44) {
                                                var48[var44][var43] = var47[var44];
                                            }

                                            var12[var43] = this.sumSquares(var1, var47);
                                            var47[var43] -= var4[var43];
                                        }
                                    }
                                }
                            } while(!var34);
                        } while(this.nIter <= this.nMax);

                        if (!this.suppressErrorMessages) {
                            System.out.println("Maximum iteration number reached, in Regression.simplex(...)");
                            System.out.println("without the convergence criterion being satisfied");
                            System.out.println("Current parameter estimates and sum of squares values returned");
                        }

                        this.nlrStatus = false;

                        for(var43 = 0; var43 < var8; ++var43) {
                            var47[var43] = var48[var43][var31];
                        }

                        var12[var9 - 1] = var56;
                        var34 = false;
                    }
                }
            }
        }
    }

    protected double sumSquares(Object var1, double[] var2) {
        RegressionFunction var3 = null;
        RegressionFunction2 var4 = null;
        RegressionFunction3 var5 = null;
        switch(this.simplexFlag) {
            case 1:
                var3 = (RegressionFunction)var1;
                break;
            case 2:
                var4 = (RegressionFunction2)var1;
                break;
            case 3:
            case 4:
                var5 = (RegressionFunction3)var1;
        }

        double var6 = -3.0D;
        double[] var8 = new double[this.nParam];
        double[] var9 = new double[this.nXarrays];

        for(int var10 = 0; var10 < this.nParam; ++var10) {
            var8[var10] = var2[var10] / this.scale[var10];
        }

        double var20 = this.lastSSnoConstraint;
        boolean var12 = true;
        boolean var13;
        int var21;
        if (this.penalty) {
            var13 = false;

            for(int var14 = 0; var14 < this.nConstraints; ++var14) {
                var21 = this.penaltyParam[var14];
                switch(this.penaltyCheck[var14]) {
                    case -1:
                        if (var8[var21] < this.constraints[var14]) {
                            var6 = var20 + this.penaltyWeight * Fmath.square(this.constraints[var14] - var8[var21]);
                            var12 = false;
                        }
                        break;
                    case 0:
                        if (var8[var21] < this.constraints[var14] * (1.0D - this.constraintTolerance)) {
                            var6 = var20 + this.penaltyWeight * Fmath.square(this.constraints[var14] * (1.0D - this.constraintTolerance) - var8[var21]);
                            var12 = false;
                        }

                        if (var8[var21] > this.constraints[var14] * (1.0D + this.constraintTolerance)) {
                            var6 = var20 + this.penaltyWeight * Fmath.square(var8[var21] - this.constraints[var14] * (1.0D + this.constraintTolerance));
                            var12 = false;
                        }
                        break;
                    case 1:
                        if (var8[var21] > this.constraints[var14]) {
                            var6 = var20 + this.penaltyWeight * Fmath.square(var8[var21] - this.constraints[var14]);
                            var12 = false;
                        }
                        break;
                    default:
                        throw new IllegalArgumentException("The " + var14 + "th penalty check " + this.penaltyCheck[var14] + " not recognised");
                }
            }
        }

        int var16;
        if (this.sumPenalty) {
            var13 = false;
            double var23 = 0.0D;

            for(var16 = 0; var16 < this.nSumConstraints; ++var16) {
                double var17 = 0.0D;

                for(int var19 = 0; var19 < this.sumPenaltyNumber[var16]; ++var19) {
                    var21 = this.sumPenaltyParam[var16][var19];
                    var23 = this.sumPlusOrMinus[var16][var19];
                    var17 += var8[var21] * var23;
                }

                switch(this.sumPenaltyCheck[var16]) {
                    case -1:
                        if (var17 < this.sumConstraints[var16]) {
                            var6 = var20 + this.penaltyWeight * Fmath.square(this.sumConstraints[var16] - var17);
                            var12 = false;
                        }
                        break;
                    case 0:
                        if (var17 < this.sumConstraints[var16] * (1.0D - this.constraintTolerance)) {
                            var6 = var20 + this.penaltyWeight * Fmath.square(this.sumConstraints[var16] * (1.0D - this.constraintTolerance) - var17);
                            var12 = false;
                        }

                        if (var17 > this.sumConstraints[var16] * (1.0D + this.constraintTolerance)) {
                            var6 = var20 + this.penaltyWeight * Fmath.square(var17 - this.sumConstraints[var16] * (1.0D + this.constraintTolerance));
                            var12 = false;
                        }
                        break;
                    case 1:
                        if (var17 > this.sumConstraints[var16]) {
                            var6 = var20 + this.penaltyWeight * Fmath.square(var17 - this.sumConstraints[var16]);
                            var12 = false;
                        }
                        break;
                    default:
                        throw new IllegalArgumentException("The " + var16 + "th summation penalty check " + this.sumPenaltyCheck[var16] + " not recognised");
                }
            }
        }

        if (var12) {
            var6 = 0.0D;
            double var22 = 0.0D;
            Object var15 = null;

            for(var16 = 0; var16 < this.nData; ++var16) {
                for(int var25 = 0; var25 < this.nXarrays; ++var25) {
                    var9[var25] = this.xData[var25][var16];
                }

                switch(this.simplexFlag) {
                    case 1:
                        var6 += Fmath.square((this.yData[var16] - var3.function(var8, var9)) / this.weight[var16]);
                        break;
                    case 2:
                        var6 += Fmath.square((this.yData[var16] - var4.function(var8, var9, var16)) / this.weight[var16]);
                        break;
                    case 3:
                    case 4:
                        double[] var24 = var5.function(var8, var9, var16);
                        var22 = Fmath.square(this.yData[var16] - var24[0]);
                        this.weight[var16] = Math.sqrt(var24[1]);
                        var6 += var22 / var24[1];
                }
            }

            this.lastSSnoConstraint = var6;
        }

        return var6;
    }

    public void addConstraint(int var1, int var2, double var3) {
        this.penalty = true;
        if (this.penalties.isEmpty()) {
            this.penalties.add(new Integer(this.constraintMethod));
        }

        if (this.penalties.size() == 1) {
            this.penalties.add(new Integer(1));
        } else {
            int var5 = (Integer)this.penalties.get(1);
            ++var5;
            this.penalties.set(1, new Integer(var5));
        }

        this.penalties.add(new Integer(var1));
        this.constrainedSingle.add(new Integer(var1));
        this.penalties.add(new Integer(var2));
        String var6 = "";
        switch(var2) {
            case -1:
                var6 = "must be >= " + var3;
                break;
            case 0:
                var6 = "must = " + var3;
                break;
            case 1:
                var6 = "must be <= " + var3;
                break;
            default:
                throw new IllegalArgumentException("Constraint direction " + var2 + " not recognised");
        }

        this.constrainedSingle.add(var6);
        this.penalties.add(new Double(var3));
        if (var1 > this.maxConstraintIndex) {
            this.maxConstraintIndex = var1;
        }

    }

    public void addConstraint(int[] var1, int[] var2, int var3, double var4) {
        ArrayMaths var6 = new ArrayMaths(var2);
        double[] var7 = var6.getArray_as_double();
        this.addConstraint(var1, var7, var3, var4);
    }

    public void addConstraint(int[] var1, double[] var2, int var3, double var4) {
        int var6 = var1.length;
        int var7 = var2.length;
        if (var6 != var7) {
            throw new IllegalArgumentException("num of parameters, " + var6 + ", does not equal number of parameter signs, " + var7);
        } else {
            this.sumPenalty = true;
            if (this.sumPenalties.isEmpty()) {
                this.sumPenalties.add(new Integer(this.constraintMethod));
            }

            if (this.sumPenalties.size() == 1) {
                this.sumPenalties.add(new Integer(1));
            } else {
                int var8 = (Integer)this.sumPenalties.get(1);
                ++var8;
                this.sumPenalties.set(1, new Integer(var8));
            }

            this.sumPenalties.add(new Integer(var6));
            this.sumPenalties.add(var1);
            this.sumPenalties.add(var2);
            this.sumPenalties.add(new Integer(var3));
            this.sumPenalties.add(new Double(var4));
            ArrayMaths var13 = new ArrayMaths(var1);
            int var9 = var13.getMaximum_as_int();
            if (var9 > this.maxConstraintIndex) {
                this.maxConstraintIndex = var9;
            }

            String var10 = "";

            int var11;
            for(var11 = 0; var11 < var1.length; ++var11) {
                int var12 = var1[var11];
                if (var2[var11] >= 0.0D) {
                    if (var11 > 0) {
                        var10 = var10 + " + ";
                    }
                } else if (var11 > 0) {
                    var10 = var10 + " - ";
                } else {
                    var10 = var10 + "-";
                }

                var10 = var10 + "p[" + var12 + "]." + Math.abs(var2[var11]);
            }

            switch(var3) {
                case -1:
                    var10 = var10 + " >= " + var4;
                    break;
                case 0:
                    var10 = var10 + " = " + var4;
                    break;
                case 1:
                    var10 = var10 + " <= " + var4;
                    break;
                default:
                    throw new IllegalArgumentException("Constraint direction " + var3 + " not recognised");
            }

            for(var11 = 0; var11 < var1.length; ++var11) {
                this.constrainedMultiple.add(new Integer(var1[var11]));
                this.constrainedMultiple.add(var10);
            }

        }
    }

    public void removeConstraints() {
        int var1;
        int var2;
        if (!this.penalties.isEmpty()) {
            var1 = this.penalties.size();

            for(var2 = var1 - 1; var2 >= 0; --var2) {
                this.penalties.remove(var2);
            }
        }

        this.penalty = false;
        this.nConstraints = 0;
        this.constrainedSingle.clear();
        if (!this.sumPenalties.isEmpty()) {
            var1 = this.sumPenalties.size();

            for(var2 = var1 - 1; var2 >= 0; --var2) {
                this.sumPenalties.remove(var2);
            }
        }

        this.sumPenalty = false;
        this.nSumConstraints = 0;
        this.maxConstraintIndex = -1;
        this.constrainedMultiple.clear();
    }

    public void setConstraintTolerance(double var1) {
        this.constraintTolerance = var1;
    }

    protected void generalLinearStats(double[][] var1) {
        double var2 = 0.0D;
        double var4 = 0.0D;
        double var6 = 0.0D;
        double[][] var9 = new double[this.nParam][this.nParam];
        this.covar = new double[this.nParam][this.nParam];
        this.corrCoeff = new double[this.nParam][this.nParam];
        double[] var12 = new double[this.nParam];
        double[] var13 = new double[this.nParam];

        int var14;
        for(var14 = 0; var14 < this.nParam; ++var14) {
            var13[var14] = this.best[var14];
        }

        this.chiSquare = 0.0D;
        this.sumOfSquaresError = 0.0D;

        for(var14 = 0; var14 < this.nData; ++var14) {
            var6 = 0.0D;

            for(int var15 = 0; var15 < this.nParam; ++var15) {
                var6 += var13[var15] * var1[var15][var14];
            }

            this.yCalc[var14] = var6;
            var6 -= this.yData[var14];
            this.residual[var14] = var6;
            this.sumOfSquaresError += Fmath.square(var6);
        }

        double var23 = this.sumOfSquaresError / (double)this.degreesOfFreedom;
        double var16 = Math.sqrt(var23);
        int var20;
        if (this.weightOpt && !this.trueErrors) {
            double var18 = 0.0D;

            for(var20 = 0; var20 < this.nData; ++var20) {
                var18 = this.weight[var20] * this.weight[var20];
            }

            var18 /= (double)this.degreesOfFreedom;
            double var25 = Math.sqrt(var23 / var18);

            for(int var22 = 0; var22 < this.nData; ++var22) {
                this.weight[var22] *= var25;
            }
        }

        int var24;
        for(var24 = 0; var24 < this.nData; ++var24) {
            this.residualW[var24] = this.residual[var24] / this.weight[var24];
            this.chiSquare += Fmath.square(this.residual[var24] / this.weight[var24]);
        }

        this.reducedChiSquare = this.chiSquare / (double)this.degreesOfFreedom;
        int var19;
        if (this.sumOfSquaresError == 0.0D) {
            for(var24 = 0; var24 < this.nParam; ++var24) {
                var12[var24] = 0.0D;

                for(var19 = 0; var19 < this.nParam; ++var19) {
                    this.covar[var24][var19] = 0.0D;
                    if (var24 == var19) {
                        this.corrCoeff[var24][var19] = 1.0D;
                    } else {
                        this.corrCoeff[var24][var19] = 0.0D;
                    }
                }
            }
        } else {
            for(var24 = 0; var24 < this.nParam; ++var24) {
                for(var19 = 0; var19 < this.nParam; ++var19) {
                    var4 = 0.0D;

                    for(var20 = 0; var20 < this.nData; ++var20) {
                        if (this.weightOpt) {
                            var2 = this.weight[var20];
                        } else {
                            var2 = var16;
                        }

                        var4 += var1[var24][var20] * var1[var19][var20] / Fmath.square(var2);
                    }

                    var9[var19][var24] = var4;
                }
            }

            Matrix var26 = new Matrix(var9);
            if (this.suppressErrorMessages) {
                var26.suppressErrorMessage();
            }

            var26 = var26.inverse();
            double[][] var10 = var26.getArrayCopy();

            for(var19 = 0; var19 < this.nParam; ++var19) {
                var12[var19] = Math.sqrt(var10[var19][var19]);
            }

            for(var19 = 0; var19 < this.nParam; ++var19) {
                for(var20 = 0; var20 < this.nParam; ++var20) {
                    this.covar[var19][var20] = var10[var19][var20];
                }
            }

            for(var19 = 0; var19 < this.nParam; ++var19) {
                for(var20 = 0; var20 < this.nParam; ++var20) {
                    if (var19 == var20) {
                        this.corrCoeff[var19][var20] = 1.0D;
                    } else {
                        this.corrCoeff[var19][var20] = this.covar[var19][var20] / (var12[var19] * var12[var20]);
                    }
                }
            }
        }

        for(var24 = 0; var24 < this.nParam; ++var24) {
            this.bestSd[var24] = var12[var24];
            this.tValues[var24] = this.best[var24] / this.bestSd[var24];
            double var27 = Math.abs(this.tValues[var24]);
            if (var27 != var27) {
                this.pValues[var24] = 0.0D / 0.0;
            } else {
                this.pValues[var24] = 1.0D - Stat.studentTcdf(-var27, var27, this.degreesOfFreedom);
            }
        }

        if (this.nXarrays == 1 && this.nYarrays == 1) {
            this.xyR = Stat.corrCoeff(this.xData[0], this.yData, this.weight);
        }

        this.yyR = Stat.corrCoeff(this.yCalc, this.yData, this.weight);
        this.yMean = Stat.mean(this.yData);
        this.yWeightedMean = Stat.mean(this.yData, this.weight);
        this.sumOfSquaresTotal = 0.0D;

        for(var24 = 0; var24 < this.nData; ++var24) {
            this.sumOfSquaresTotal += Fmath.square((this.yData[var24] - this.yWeightedMean) / this.weight[var24]);
        }

        this.sumOfSquaresRegrn = this.sumOfSquaresTotal - this.chiSquare;
        if (this.sumOfSquaresRegrn < 0.0D) {
            this.sumOfSquaresRegrn = 0.0D;
        }

        this.multR = this.sumOfSquaresRegrn / this.sumOfSquaresTotal;
        this.adjustedR = 0.0D / 0.0;
        this.multipleF = 0.0D / 0.0;
        if (this.nData - this.nParam - 1 > 0) {
            this.adjustedR = 1.0D - (1.0D - this.multR) * (double)(this.nData - 1) / (double)(this.nData - this.nParam - 1);
        }

        this.multipleF = this.multR * ((double)(this.nData - this.nParam) - 1.0D) / ((1.0D - this.multR) * (double)this.nParam);
        if (this.multipleF >= 0.0D) {
            this.multipleFprob = Stat.fTestProb(this.multipleF, this.nXarrays, this.nData - this.nParam - 1);
        }

        this.calcDurbinWatson();
        this.varianceOfObservations();
    }

    protected int pseudoLinearStats(Object var1, Object var2) {
        double var3 = 0.0D;
        double var5 = 0.0D;
        int var7 = 0;
        int var8 = this.nParam;
        double[] var9 = new double[var8];
        double[] var10 = new double[var8];
        double[] var11 = new double[var8];
        double[] var12 = new double[this.nXarrays];
        double[][] var13 = new double[var8][var8];
        this.pseudoSd = new double[var8];
        Object var14 = null;
        this.grad = new double[var8][2];
        this.covar = new double[var8][var8];
        this.corrCoeff = new double[var8][var8];
        var10 = Conv.copy(this.best);
        double var15 = 0.0D;

        for(int var17 = 0; var17 < this.nData; ++var17) {
            for(int var18 = 0; var18 < this.nXarrays; ++var18) {
                var12[var18] = this.xData[var18][var17];
            }

            switch(this.simplexFlag) {
                case 1:
                    this.yCalc[var17] = ((RegressionFunction)var1).function(this.best, var12);
                    break;
                case 2:
                    this.yCalc[var17] = ((RegressionFunction2)var1).function(this.best, var12, var17);
                    break;
                case 3:
                case 4:
                    double[] var33 = ((RegressionFunction3)var1).function(this.best, var12, var17);
                    this.yCalc[var17] = var33[0];
                    this.weight[var17] = Math.sqrt(var33[1]);
            }

            this.residual[var17] = this.yCalc[var17] - this.yData[var17];
            var15 += Fmath.square(this.residual[var17]);
        }

        this.sumOfSquaresError = var15;
        double var32 = var15 / (double)(this.nData - var8);
        double var19 = Math.sqrt(var32);
        double var21;
        int var23;
        double var34;
        if (this.weightOpt && !this.trueErrors) {
            var21 = 0.0D;

            for(var23 = 0; var23 < this.nData; ++var23) {
                var21 = this.weight[var23] * this.weight[var23];
            }

            var21 /= (double)this.degreesOfFreedom;
            var34 = Math.sqrt(var32 / var21);

            for(int var25 = 0; var25 < this.nData; ++var25) {
                this.weight[var25] *= var34;
            }
        }

        var21 = 0.0D;

        for(var23 = 0; var23 < this.nData; ++var23) {
            this.residualW[var23] = this.residual[var23] / this.weight[var23];
            var21 += Fmath.square(this.residualW[var23]);
        }

        this.chiSquare = var21;
        this.reducedChiSquare = var21 / (double)(this.nData - var8);
        var34 = 1.0D;
        if (!this.weightOpt && !this.trueFreq) {
            var34 = this.sumOfSquaresError / (double)(this.nData - var8);
        }

        double var35 = 1.0D;

        int var27;
        int var28;
        for(var27 = 0; var27 < var8; ++var27) {
            for(var28 = 0; var28 < var8; ++var28) {
                var9[var28] = var10[var28];
            }

            var35 = var10[var27];
            if (var35 == 0.0D) {
                var35 = this.stepH[var27];
                this.zeroCheck = true;
            }

            var9[var27] = var35 * (1.0D - this.delta);
            this.lastSSnoConstraint = this.sumOfSquaresError;
            var3 = this.sumSquares(var1, var9);
            var9[var27] = var35 * (1.0D + this.delta);
            this.lastSSnoConstraint = this.sumOfSquaresError;
            var5 = this.sumSquares(var1, var9);
            this.grad[var27][0] = (this.fMin - var3) / Math.abs(this.delta * var35);
            this.grad[var27][1] = (var5 - this.fMin) / Math.abs(this.delta * var35);
        }

        this.lastSSnoConstraint = this.sumOfSquaresError;

        for(var27 = 0; var27 < var8; ++var27) {
            for(var28 = 0; var28 < var8; ++var28) {
                var13[var27][var28] = this.secondDerivative(var1, var2, var10, var27, var28);
            }
        }

        for(var27 = 0; var27 < var8; ++var27) {
            this.pseudoSd[var27] = 2.0D * this.delta * var34 * Math.abs(var10[var27]) / (this.grad[var27][1] - this.grad[var27][0]);
            if (this.pseudoSd[var27] >= 0.0D) {
                this.pseudoSd[var27] = Math.sqrt(this.pseudoSd[var27]);
            } else {
                this.pseudoSd[var27] = 0.0D / 0.0;
            }
        }

        double var37;
        if (var8 == 1) {
            var13[0][0] = 1.0D / var13[0][0];
            this.covar[0][0] = var13[0][0] * var34;
            if (this.covar[0][0] >= 0.0D) {
                var11[0] = Math.sqrt(this.covar[0][0]);
                this.corrCoeff[0][0] = 1.0D;
            } else {
                var11[0] = 0.0D / 0.0;
                this.corrCoeff[0][0] = 0.0D / 0.0;
                this.posVarFlag = false;
            }
        } else {
            Matrix var36 = new Matrix(var13);
            if (this.suppressErrorMessages) {
                var36.suppressErrorMessage();
            }

            var37 = var36.determinant();
            if (var37 == 0.0D) {
                this.invertFlag = false;
            } else {
                var36 = var36.inverse();
                this.invertFlag = var36.getMatrixCheck();
            }

            if (!this.invertFlag) {
                --var7;
            }

            var13 = var36.getArrayCopy();
            this.posVarFlag = true;
            int var30;
            int var31;
            if (this.invertFlag) {
                for(var30 = 0; var30 < var8; ++var30) {
                    for(var31 = var30; var31 < var8; ++var31) {
                        this.covar[var30][var31] = 2.0D * var13[var30][var31] * var34;
                        this.covar[var31][var30] = this.covar[var30][var31];
                    }

                    if (this.covar[var30][var30] >= 0.0D) {
                        var11[var30] = Math.sqrt(this.covar[var30][var30]);
                    } else {
                        var11[var30] = 0.0D / 0.0;
                        this.posVarFlag = false;
                    }
                }

                for(var30 = 0; var30 < var8; ++var30) {
                    for(var31 = 0; var31 < var8; ++var31) {
                        if (var11[var30] != 0.0D / 0.0 && var11[var31] != 0.0D / 0.0) {
                            this.corrCoeff[var30][var31] = this.covar[var30][var31] / (var11[var30] * var11[var31]);
                        } else {
                            this.corrCoeff[var30][var31] = 0.0D / 0.0;
                        }
                    }
                }
            } else {
                for(var30 = 0; var30 < var8; ++var30) {
                    for(var31 = 0; var31 < var8; ++var31) {
                        this.covar[var30][var31] = 0.0D / 0.0;
                        this.corrCoeff[var30][var31] = 0.0D / 0.0;
                    }

                    var11[var30] = 0.0D / 0.0;
                }
            }
        }

        if (!this.posVarFlag) {
            --var7;
        }

        for(var27 = 0; var27 < this.nParam; ++var27) {
            this.bestSd[var27] = var11[var27];
            this.tValues[var27] = this.best[var27] / this.bestSd[var27];
            var37 = Math.abs(this.tValues[var27]);
            if (var37 != var37) {
                this.pValues[var27] = 0.0D / 0.0;
            } else {
                this.pValues[var27] = 1.0D - Stat.studentTcdf(-var37, var37, this.degreesOfFreedom);
            }
        }

        if (this.nXarrays == 1 && this.nYarrays == 1) {
            this.xyR = Stat.corrCoeff(this.xData[0], this.yData, this.weight);
        }

        this.yyR = Stat.corrCoeff(this.yCalc, this.yData, this.weight);
        this.yMean = Stat.mean(this.yData);
        this.yWeightedMean = Stat.mean(this.yData, this.weight);
        this.sumOfSquaresTotal = 0.0D;

        for(var27 = 0; var27 < this.nData; ++var27) {
            this.sumOfSquaresTotal += Fmath.square((this.yData[var27] - this.yWeightedMean) / this.weight[var27]);
        }

        this.sumOfSquaresRegrn = this.sumOfSquaresTotal - this.chiSquare;
        if (this.sumOfSquaresRegrn < 0.0D) {
            this.sumOfSquaresRegrn = 0.0D;
        }

        this.multR = this.sumOfSquaresRegrn / this.sumOfSquaresTotal;
        this.adjustedR = 0.0D / 0.0;
        this.multipleF = 0.0D / 0.0;
        if (this.nData - this.nXarrays - 1 > 0) {
            this.adjustedR = 1.0D - (1.0D - this.multR) * (double)(this.nData - 1) / (double)(this.nData - this.nXarrays - 1);
        }

        this.multipleF = this.multR * ((double)(this.nData - this.nXarrays) - 1.0D) / ((1.0D - this.multR) * (double)this.nXarrays);
        if (this.multipleF >= 0.0D) {
            this.multipleFprob = Stat.fTestProb(this.multipleF, this.nXarrays, this.nData - this.nXarrays - 1);
        }

        this.calcDurbinWatson();
        this.varianceOfObservations();
        return var7;
    }

    protected int pseudoLinearStats(Object var1) {
        double var2 = 0.0D;
        double var4 = 0.0D;
        double var6 = 0.0D;
        double var8 = 0.0D;
        int var10 = 0;
        int var11 = this.nParam;
        double[] var12 = new double[var11];
        double[] var13 = new double[var11];
        double[] var14 = new double[var11];
        double[] var15 = new double[this.nXarrays];
        double[][] var16 = new double[var11][var11];
        this.pseudoSd = new double[var11];
        Object var17 = null;
        this.grad = new double[var11][2];
        this.covar = new double[var11][var11];
        this.corrCoeff = new double[var11][var11];
        var13 = Conv.copy(this.best);
        double var18 = 1.0D;
        double var20 = 1.0D;
        this.firstDerivs = new double[this.nParam][this.nData];

        int var22;
        int var23;
        int var24;
        for(var22 = 0; var22 < var11; ++var22) {
            for(var23 = 0; var23 < var11; ++var23) {
                var12[var23] = var13[var23];
            }

            var18 = var13[var22];
            if (var18 == 0.0D) {
                var18 = this.stepH[var22];
                this.zeroCheck = true;
            }

            var12[var22] = var18 * (1.0D - this.delta);
            this.lastSSnoConstraint = this.sumOfSquaresError;
            var2 = this.sumSquares(var1, var12);

            for(var23 = 0; var23 < this.nData; ++var23) {
                for(var24 = 0; var24 < this.nXarrays; ++var24) {
                    var15[var24] = this.xData[var24][var23];
                }

                switch(this.simplexFlag) {
                    case 1:
                        this.firstDerivs[var22][var23] = -((RegressionFunction)var1).function(var12, var15) / Math.abs(this.delta * 2.0D * var18);
                        break;
                    case 2:
                        this.firstDerivs[var22][var23] = -((RegressionFunction2)var1).function(var12, var15, var23) / Math.abs(this.delta * 2.0D * var18);
                        break;
                    case 3:
                    case 4:
                        this.firstDerivs[var22][var23] = -((RegressionFunction3)var1).function(var12, var15, var23)[0] / Math.abs(this.delta * 2.0D * var18);
                }
            }

            var12[var22] = var18 * (1.0D + this.delta);
            this.lastSSnoConstraint = this.sumOfSquaresError;
            var4 = this.sumSquares(var1, var12);

            for(var23 = 0; var23 < this.nData; ++var23) {
                for(var24 = 0; var24 < this.nXarrays; ++var24) {
                    var15[var24] = this.xData[var24][var23];
                }

                switch(this.simplexFlag) {
                    case 1:
                        this.firstDerivs[var22][var23] = -((RegressionFunction)var1).function(var12, var15) / Math.abs(this.delta * 2.0D * var18);
                        break;
                    case 2:
                        this.firstDerivs[var22][var23] = -((RegressionFunction2)var1).function(var12, var15, var23) / Math.abs(this.delta * 2.0D * var18);
                        break;
                    case 3:
                    case 4:
                        this.firstDerivs[var22][var23] = -((RegressionFunction3)var1).function(var12, var15, var23)[0] / Math.abs(this.delta * 2.0D * var18);
                }
            }

            this.grad[var22][0] = (this.fMin - var2) / Math.abs(this.delta * var18);
            this.grad[var22][1] = (var4 - this.fMin) / Math.abs(this.delta * var18);
        }

        this.lastSSnoConstraint = this.sumOfSquaresError;

        for(var22 = 0; var22 < var11; ++var22) {
            for(var23 = 0; var23 < var11; ++var23) {
                for(var24 = 0; var24 < var11; ++var24) {
                    var12[var24] = var13[var24];
                }

                var18 = var12[var22];
                if (var18 == 0.0D) {
                    var18 = this.stepH[var22];
                    this.zeroCheck = true;
                }

                var12[var22] = var18 * (1.0D + this.delta / 2.0D);
                var18 = var12[var23];
                if (var18 == 0.0D) {
                    var18 = this.stepH[var23];
                    this.zeroCheck = true;
                }

                var12[var23] = var18 * (1.0D + this.delta / 2.0D);
                this.lastSSnoConstraint = this.sumOfSquaresError;
                var2 = this.sumSquares(var1, var12);
                var12[var22] = var13[var22];
                var12[var23] = var13[var23];
                var18 = var12[var22];
                if (var18 == 0.0D) {
                    var18 = this.stepH[var22];
                    this.zeroCheck = true;
                }

                var12[var22] = var18 * (1.0D - this.delta / 2.0D);
                var18 = var12[var23];
                if (var18 == 0.0D) {
                    var18 = this.stepH[var23];
                    this.zeroCheck = true;
                }

                var12[var23] = var18 * (1.0D + this.delta / 2.0D);
                this.lastSSnoConstraint = this.sumOfSquaresError;
                var4 = this.sumSquares(var1, var12);
                var12[var22] = var13[var22];
                var12[var23] = var13[var23];
                var18 = var12[var22];
                if (var18 == 0.0D) {
                    var18 = this.stepH[var22];
                    this.zeroCheck = true;
                }

                var12[var22] = var18 * (1.0D + this.delta / 2.0D);
                var18 = var12[var23];
                if (var18 == 0.0D) {
                    var18 = this.stepH[var23];
                    this.zeroCheck = true;
                }

                var12[var23] = var18 * (1.0D - this.delta / 2.0D);
                this.lastSSnoConstraint = this.sumOfSquaresError;
                var6 = this.sumSquares(var1, var12);
                var12[var22] = var13[var22];
                var12[var23] = var13[var23];
                var18 = var12[var22];
                if (var18 == 0.0D) {
                    var18 = this.stepH[var22];
                    this.zeroCheck = true;
                }

                var12[var22] = var18 * (1.0D - this.delta / 2.0D);
                var18 = var12[var23];
                if (var18 == 0.0D) {
                    var18 = this.stepH[var23];
                    this.zeroCheck = true;
                }

                var12[var23] = var18 * (1.0D - this.delta / 2.0D);
                this.lastSSnoConstraint = this.sumOfSquaresError;
                var8 = this.sumSquares(var1, var12);
                var16[var22][var23] = (var2 - var4 - var6 + var8) / (this.delta * this.delta);
            }
        }

        double var37 = 0.0D;
        double var38 = 0.0D;

        for(int var26 = 0; var26 < this.nData; ++var26) {
            for(int var27 = 0; var27 < this.nXarrays; ++var27) {
                var15[var27] = this.xData[var27][var26];
            }

            switch(this.simplexFlag) {
                case 1:
                    this.yCalc[var26] = ((RegressionFunction)var1).function(var13, var15);
                    break;
                case 2:
                    this.yCalc[var26] = ((RegressionFunction2)var1).function(var13, var15, var26);
                    break;
                case 3:
                case 4:
                    double[] var40 = ((RegressionFunction3)var1).function(var13, var15, var26);
                    this.yCalc[var26] = var40[0];
                    this.weight[var26] = Math.sqrt(var40[1]);
            }

            this.residual[var26] = this.yCalc[var26] - this.yData[var26];
            var37 += Fmath.square(this.residual[var26]);
            this.residualW[var26] = this.residual[var26] / this.weight[var26];
            var38 += Fmath.square(this.residualW[var26]);
        }

        this.sumOfSquaresError = var37;
        double var39 = var37 / (double)(this.nData - var11);
        double var28 = Math.sqrt(var39);
        this.chiSquare = var38;
        this.reducedChiSquare = var38 / (double)(this.nData - var11);
        double var30 = 1.0D;
        if (!this.weightOpt && !this.trueFreq) {
            var30 = this.sumOfSquaresError / (double)(this.nData - var11);
        }

        int var32;
        for(var32 = 0; var32 < var11; ++var32) {
            this.pseudoSd[var32] = 2.0D * this.delta * var30 * Math.abs(var13[var32]) / (this.grad[var32][1] - this.grad[var32][0]);
            if (this.pseudoSd[var32] >= 0.0D) {
                this.pseudoSd[var32] = Math.sqrt(this.pseudoSd[var32]);
            } else {
                this.pseudoSd[var32] = 0.0D / 0.0;
            }
        }

        double var33;
        if (var11 == 1) {
            var18 = var13[0];
            if (var18 == 0.0D) {
                var18 = this.stepH[0];
            }

            var16[0][0] = 1.0D / var16[0][0];
            this.covar[0][0] = var16[0][0] * var30 * var18 * var18;
            if (this.covar[0][0] >= 0.0D) {
                var14[0] = Math.sqrt(this.covar[0][0]);
                this.corrCoeff[0][0] = 1.0D;
            } else {
                var14[0] = 0.0D / 0.0;
                this.corrCoeff[0][0] = 0.0D / 0.0;
                this.posVarFlag = false;
            }
        } else {
            Matrix var41 = new Matrix(var16);
            if (this.suppressErrorMessages) {
                var41.suppressErrorMessage();
            }

            var33 = var41.determinant();
            if (var33 == 0.0D) {
                this.invertFlag = false;
            } else {
                var41 = var41.inverse();
                this.invertFlag = var41.getMatrixCheck();
            }

            if (!this.invertFlag) {
                --var10;
            }

            var16 = var41.getArrayCopy();
            this.posVarFlag = true;
            int var35;
            int var36;
            if (this.invertFlag) {
                for(var35 = 0; var35 < var11; ++var35) {
                    var18 = var13[var35];
                    if (var18 == 0.0D) {
                        var18 = this.stepH[var35];
                    }

                    for(var36 = var35; var36 < var11; ++var36) {
                        var20 = var13[var36];
                        if (var20 == 0.0D) {
                            var20 = this.stepH[var36];
                        }

                        this.covar[var35][var36] = 2.0D * var16[var35][var36] * var30 * var18 * var20;
                        this.covar[var36][var35] = this.covar[var35][var36];
                    }

                    if (this.covar[var35][var35] >= 0.0D) {
                        var14[var35] = Math.sqrt(this.covar[var35][var35]);
                    } else {
                        var14[var35] = 0.0D / 0.0;
                        this.posVarFlag = false;
                    }
                }

                for(var35 = 0; var35 < var11; ++var35) {
                    for(var36 = 0; var36 < var11; ++var36) {
                        if (var14[var35] != 0.0D / 0.0 && var14[var36] != 0.0D / 0.0) {
                            this.corrCoeff[var35][var36] = this.covar[var35][var36] / (var14[var35] * var14[var36]);
                        } else {
                            this.corrCoeff[var35][var36] = 0.0D / 0.0;
                        }
                    }
                }
            } else {
                for(var35 = 0; var35 < var11; ++var35) {
                    for(var36 = 0; var36 < var11; ++var36) {
                        this.covar[var35][var36] = 0.0D / 0.0;
                        this.corrCoeff[var35][var36] = 0.0D / 0.0;
                    }

                    var14[var35] = 0.0D / 0.0;
                }
            }
        }

        if (!this.posVarFlag) {
            --var10;
        }

        for(var32 = 0; var32 < this.nParam; ++var32) {
            this.bestSd[var32] = var14[var32];
            this.tValues[var32] = this.best[var32] / this.bestSd[var32];
            var33 = Math.abs(this.tValues[var32]);
            if (var33 != var33) {
                this.pValues[var32] = 0.0D / 0.0;
            } else {
                this.pValues[var32] = 1.0D - Stat.studentTcdf(-var33, var33, this.degreesOfFreedom);
            }
        }

        if (this.nXarrays == 1 && this.nYarrays == 1) {
            this.xyR = Stat.corrCoeff(this.xData[0], this.yData, this.weight);
        }

        this.yyR = Stat.corrCoeff(this.yCalc, this.yData, this.weight);
        this.yMean = Stat.mean(this.yData);
        this.yWeightedMean = Stat.mean(this.yData, this.weight);
        this.sumOfSquaresTotal = 0.0D;

        for(var32 = 0; var32 < this.nData; ++var32) {
            this.sumOfSquaresTotal += Fmath.square((this.yData[var32] - this.yWeightedMean) / this.weight[var32]);
        }

        this.sumOfSquaresRegrn = this.sumOfSquaresTotal - this.chiSquare;
        if (this.sumOfSquaresRegrn < 0.0D) {
            this.sumOfSquaresRegrn = 0.0D;
        }

        this.multR = this.sumOfSquaresRegrn / this.sumOfSquaresTotal;
        this.adjustedR = 0.0D / 0.0;
        this.multipleF = 0.0D / 0.0;
        if (this.nData - this.nXarrays - 1 > 0) {
            this.adjustedR = 1.0D - (1.0D - this.multR) * (double)(this.nData - 1) / (double)(this.nData - this.nXarrays - 1);
        }

        this.multipleF = this.multR * ((double)(this.nData - this.nXarrays) - 1.0D) / ((1.0D - this.multR) * (double)this.nXarrays);
        if (this.multipleF >= 0.0D) {
            this.multipleFprob = Stat.fTestProb(this.multipleF, this.nXarrays, this.nData - this.nXarrays - 1);
        }

        this.calcDurbinWatson();
        this.varianceOfObservations();
        return var10;
    }

    private double secondDerivative(Object var1, Object var2, double[] var3, int var4, int var5) {
        if (var4 == 0 && var5 == 0) {
            this.firstDerivs = new double[this.nParam][this.nData];
        }

        Object var6 = null;
        double[] var7 = new double[this.nXarrays];
        double var8 = 0.0D;
        double[] var18;
        switch(this.simplexFlag) {
            case 1:
                RegressionDerivativeFunction var10 = (RegressionDerivativeFunction)var2;
                RegressionFunction var11 = (RegressionFunction)var1;

                for(int var19 = 0; var19 < this.nData; ++var19) {
                    for(int var20 = 0; var20 < this.nXarrays; ++var20) {
                        var7[var20] = this.xData[var20][var19];
                    }

                    var18 = var10.function(var3, var7, var4, var5);
                    var8 += (2.0D * var18[0] * var18[1] + 2.0D * var18[2] * (var11.function(var3, var7) - this.yData[var19])) / (this.weight[var19] * this.weight[var19]);
                    if (var4 == 0) {
                        this.firstDerivs[var5][var19] = var18[1];
                    }
                }

                return var8;
            case 2:
                RegressionDerivativeFunction2 var12 = (RegressionDerivativeFunction2)var2;
                RegressionFunction2 var13 = (RegressionFunction2)var1;

                for(int var22 = 0; var22 < this.nData; ++var22) {
                    for(int var23 = 0; var23 < this.nXarrays; ++var23) {
                        var7[var23] = this.xData[var23][var22];
                    }

                    var18 = var12.function(var3, var7, var4, var5, var22);
                    var8 += (2.0D * var18[0] * var18[1] + 2.0D * var18[2] * (var13.function(var3, var7, var22) - this.yData[var22])) / (this.weight[var22] * this.weight[var22]);
                    if (var4 == 0) {
                        this.firstDerivs[var5][var22] = var18[1];
                    }
                }

                return var8;
            case 3:
            case 4:
                RegressionFunction3 var15;
                int var16;
                int var17;
                if (this.derivFlag == 1) {
                    RegressionDerivativeFunction var14 = (RegressionDerivativeFunction)var2;
                    var15 = (RegressionFunction3)var1;

                    for(var16 = 0; var16 < this.nData; ++var16) {
                        for(var17 = 0; var17 < this.nXarrays; ++var17) {
                            var7[var17] = this.xData[var17][var16];
                        }

                        var18 = var14.function(var3, var7, var4, var5);
                        var8 += (2.0D * var18[0] * var18[1] + 2.0D * var18[2] * (var15.function(var3, var7, var16)[0] - this.yData[var16])) / (this.weight[var16] * this.weight[var16]);
                        if (var4 == 0) {
                            this.firstDerivs[var5][var16] = var18[1];
                        }
                    }
                } else {
                    RegressionDerivativeFunction2 var21 = (RegressionDerivativeFunction2)var2;
                    var15 = (RegressionFunction3)var1;

                    for(var16 = 0; var16 < this.nData; ++var16) {
                        for(var17 = 0; var17 < this.nXarrays; ++var17) {
                            var7[var17] = this.xData[var17][var16];
                        }

                        var18 = var21.function(var3, var7, var4, var5, var16);
                        var8 += (2.0D * var18[0] * var18[1] + 2.0D * var18[2] * (var15.function(var3, var7, var16)[0] - this.yData[var16])) / (this.weight[var16] * this.weight[var16]);
                        if (var4 == 0) {
                            this.firstDerivs[var5][var16] = var18[1];
                        }
                    }
                }
        }

        return var8;
    }

    protected void calcDurbinWatson() {
        double var1 = 0.0D;
        double var3 = 0.0D;

        for(int var5 = 1; var5 < this.nData; ++var5) {
            var3 = this.residual[var5] - this.residual[var5 - 1];
            var1 += var3 * var3;
        }

        double var8 = 0.0D;

        for(int var7 = 0; var7 < this.nData; ++var7) {
            var8 += this.residual[var7] * this.residual[var7];
        }

        this.dDurbinWatson = var1 / var8;
        this.dDurbinWatsonDone = true;
    }

    protected double getDurbinWatsonD() {
        if (!this.dDurbinWatsonDone) {
            this.calcDurbinWatson();
        }

        return this.dDurbinWatson;
    }

    protected void checkResidualNormality() {
        this.checkResidualNormality(this.residual);
    }

    protected void checkResidualNormality(double[] var1) {
        Normality var2 = new Normality(var1);
        var2.fullAnalysis();
    }

    protected void checkWeightedResidualNormality() {
        this.checkResidualNormality(this.residualW);
    }

    protected void varianceOfObservations() {
        double var1 = 0.0D;
        double var3 = 0.0D;
        double var5 = 0.0D;
        double var7 = 0.0D;

        for(int var9 = 0; var9 < this.nData; ++var9) {
            var1 = 1.0D / this.weight[var9];
            var1 *= var1;
            var3 += this.residual[var9] * this.residual[var9] * var1;
            var5 += var1;
            var7 += var1 * var1;
        }

        this.obsnVariance = var3 * var5 / (var5 * var5 - (double)this.nParam * var7);
    }

    public void simplex(RegressionFunction var1, double[] var2, double[] var3, double var4, int var6) {
        this.simplexFlag = 1;
        this.dualErrorsRequired = false;
        this.simplex((Object)var1, (double[])var2, var3, var4, var6);
    }

    public void simplex(RegressionFunction2 var1, double[] var2, double[] var3, double var4, int var6) {
        this.simplexFlag = 2;
        this.dualErrorsRequired = false;
        this.simplex((Object)var1, (double[])var2, var3, var4, var6);
    }

    public void simplex(RegressionFunction3 var1, double[] var2, double[] var3, double var4, int var6) {
        this.simplexFlag = 3;
        if (this.multipleY) {
            this.simplexFlag = 4;
        }

        this.dualErrorsRequired = true;
        this.simplex((Object)var1, (double[])var2, var3, var4, var6);
    }

    public void simplex(Object var1, double[] var2, double[] var3, double var4, int var6) {
        this.analyticalDerivative = false;
        this.lastMethod = 3;
        this.userSupplied = true;
        this.linNonLin = false;
        this.zeroCheck = false;
        this.degreesOfFreedom = this.nData - var2.length;
        this.nelderMead(var1, (Object)null, var2, var3, var4, var6);
    }

    public void simplex(RegressionFunction var1, RegressionDerivativeFunction var2, double[] var3, double[] var4, double var5, int var7) {
        this.simplexFlag = 1;
        this.dualErrorsRequired = false;
        this.derivFlag = 1;
        this.simplex((Object)var1, (Object)var2, var3, var4, var5, var7);
    }

    public void simplex(RegressionFunction2 var1, RegressionDerivativeFunction2 var2, double[] var3, double[] var4, double var5, int var7) {
        this.simplexFlag = 2;
        this.dualErrorsRequired = false;
        this.derivFlag = 2;
        this.simplex((Object)var1, (Object)var2, var3, var4, var5, var7);
    }

    public void simplex(RegressionFunction3 var1, RegressionDerivativeFunction var2, double[] var3, double[] var4, double var5, int var7) {
        this.simplexFlag = 3;
        this.dualErrorsRequired = false;
        this.derivFlag = 1;
        if (this.multipleY) {
            this.simplexFlag = 4;
        }

        this.simplex((Object)var1, (Object)var2, var3, var4, var5, var7);
    }

    public void simplex(RegressionFunction3 var1, RegressionDerivativeFunction2 var2, double[] var3, double[] var4, double var5, int var7) {
        this.simplexFlag = 3;
        this.derivFlag = 2;
        if (this.multipleY) {
            this.simplexFlag = 4;
        }

        this.dualErrorsRequired = true;
        this.simplex((Object)var1, (Object)var2, var3, var4, var5, var7);
    }

    public void simplex(Object var1, Object var2, double[] var3, double[] var4, double var5, int var7) {
        this.analyticalDerivative = true;
        this.lastMethod = 3;
        this.userSupplied = true;
        this.linNonLin = false;
        this.zeroCheck = false;
        this.degreesOfFreedom = this.nData - var3.length;
        this.nelderMead(var1, var2, var3, var4, var5, var7);
    }

    public void simplexPlot(RegressionFunction var1, double[] var2, double[] var3, double var4, int var6) {
        this.simplexFlag = 1;
        this.dualErrorsRequired = false;
        this.simplexPlot((Object)var1, (double[])var2, var3, var4, var6);
    }

    public void simplexPlot(RegressionFunction2 var1, double[] var2, double[] var3, double var4, int var6) {
        this.simplexFlag = 2;
        this.dualErrorsRequired = false;
        this.simplexPlot((Object)var1, (double[])var2, var3, var4, var6);
    }

    public void simplexPlot(RegressionFunction3 var1, double[] var2, double[] var3, double var4, int var6) {
        this.simplexFlag = 3;
        this.dualErrorsRequired = true;
        if (this.multipleY) {
            this.simplexFlag = 4;
        }

        this.simplexPlot((Object)var1, (double[])var2, var3, var4, var6);
    }

    public void simplexPlot(Object var1, double[] var2, double[] var3, double var4, int var6) {
        this.analyticalDerivative = false;
        this.lastMethod = 3;
        this.userSupplied = true;
        this.linNonLin = false;
        this.zeroCheck = false;
        this.degreesOfFreedom = this.nData - var2.length;
        this.dualErrorsRequired = true;
        this.nelderMead(var1, (Object)null, var2, var3, var4, var6);
        if (!this.suppressPrint) {
            this.print();
        }

        int var7 = 0;
        if (this.xData.length < 2 && !this.multipleY) {
            var7 = this.plotXY(var1);
        }

        if (var7 != -2 && !this.suppressYYplot) {
            this.plotYY();
        }

    }

    public void simplexPlot(RegressionFunction var1, RegressionDerivativeFunction var2, double[] var3, double[] var4, double var5, int var7) {
        this.simplexFlag = 1;
        this.dualErrorsRequired = false;
        this.derivFlag = 1;
        this.simplexPlot((Object)var1, (Object)var2, var3, var4, var5, var7);
    }

    public void simplexPlot(RegressionFunction2 var1, RegressionDerivativeFunction2 var2, double[] var3, double[] var4, double var5, int var7) {
        this.simplexFlag = 2;
        this.derivFlag = 2;
        this.dualErrorsRequired = false;
        this.simplexPlot((Object)var1, (Object)var2, var3, var4, var5, var7);
    }

    public void simplexPlot(RegressionFunction3 var1, RegressionDerivativeFunction var2, double[] var3, double[] var4, double var5, int var7) {
        this.simplexFlag = 3;
        this.derivFlag = 1;
        this.dualErrorsRequired = true;
        if (this.multipleY) {
            this.simplexFlag = 4;
        }

        this.simplexPlot((Object)var1, (Object)var2, var3, var4, var5, var7);
    }

    public void simplexPlot(RegressionFunction3 var1, RegressionDerivativeFunction2 var2, double[] var3, double[] var4, double var5, int var7) {
        this.simplexFlag = 3;
        this.derivFlag = 2;
        if (this.multipleY) {
            this.simplexFlag = 4;
        }

        this.dualErrorsRequired = true;
        this.simplexPlot((Object)var1, (Object)var2, var3, var4, var5, var7);
    }

    public void simplexPlot(Object var1, Object var2, double[] var3, double[] var4, double var5, int var7) {
        this.analyticalDerivative = true;
        this.lastMethod = 3;
        this.userSupplied = true;
        this.linNonLin = false;
        this.zeroCheck = false;
        this.degreesOfFreedom = this.nData - var3.length;
        this.nelderMead(var1, var2, var3, var4, var5, var7);
        if (!this.suppressPrint) {
            this.print();
        }

        int var8 = 0;
        if (this.xData.length < 2 && !this.multipleY) {
            var8 = this.plotXY(var1);
        }

        if (var8 != -2 && !this.suppressYYplot) {
            this.plotYY();
        }

    }

    public void simplex(RegressionFunction var1, double[] var2, double[] var3, double var4) {
        this.simplexFlag = 1;
        this.dualErrorsRequired = false;
        this.simplex((Object)var1, (double[])var2, var3, var4);
    }

    public void simplex(RegressionFunction2 var1, double[] var2, double[] var3, double var4) {
        this.simplexFlag = 2;
        this.dualErrorsRequired = false;
        this.simplex((Object)var1, (double[])var2, var3, var4);
    }

    public void simplex(RegressionFunction3 var1, double[] var2, double[] var3, double var4) {
        this.simplexFlag = 3;
        this.dualErrorsRequired = true;
        if (this.multipleY) {
            this.simplexFlag = 4;
        }

        this.simplex((Object)var1, (double[])var2, var3, var4);
    }

    public void simplex(Object var1, double[] var2, double[] var3, double var4) {
        this.simplex(var1, var2, var3, var4, this.nMax);
    }

    public void simplex(RegressionFunction var1, RegressionDerivativeFunction var2, double[] var3, double[] var4, double var5) {
        this.simplexFlag = 1;
        this.derivFlag = 1;
        this.dualErrorsRequired = false;
        this.simplex((Object)var1, (Object)var2, var3, var4, var5);
    }

    public void simplex(RegressionFunction2 var1, RegressionDerivativeFunction2 var2, double[] var3, double[] var4, double var5) {
        this.simplexFlag = 2;
        this.derivFlag = 2;
        this.dualErrorsRequired = false;
        this.simplex((Object)var1, (Object)var2, var3, var4, var5);
    }

    public void simplex(RegressionFunction3 var1, RegressionDerivativeFunction var2, double[] var3, double[] var4, double var5) {
        this.simplexFlag = 3;
        this.derivFlag = 1;
        this.dualErrorsRequired = true;
        if (this.multipleY) {
            this.simplexFlag = 4;
        }

        this.simplex((Object)var1, (Object)var2, var3, var4, var5);
    }

    public void simplex(RegressionFunction3 var1, RegressionDerivativeFunction2 var2, double[] var3, double[] var4, double var5) {
        this.simplexFlag = 3;
        this.derivFlag = 2;
        if (this.multipleY) {
            this.simplexFlag = 4;
        }

        this.dualErrorsRequired = true;
        this.simplex((Object)var1, (Object)var2, var3, var4, var5);
    }

    public void simplex(Object var1, Object var2, double[] var3, double[] var4, double var5) {
        this.simplex(var1, var2, var3, var4, var5, this.nMax);
    }

    public void simplexPlot(RegressionFunction var1, double[] var2, double[] var3, double var4) {
        this.simplexFlag = 1;
        this.dualErrorsRequired = false;
        this.simplexPlot((Object)var1, (double[])var2, var3, var4);
    }

    public void simplexPlot(RegressionFunction2 var1, double[] var2, double[] var3, double var4) {
        this.simplexFlag = 2;
        this.dualErrorsRequired = false;
        this.simplexPlot((Object)var1, (double[])var2, var3, var4);
    }

    public void simplexPlot(RegressionFunction3 var1, double[] var2, double[] var3, double var4) {
        this.simplexFlag = 3;
        if (this.multipleY) {
            this.simplexFlag = 4;
        }

        this.dualErrorsRequired = true;
        this.simplexPlot((Object)var1, (double[])var2, var3, var4);
    }

    public void simplexPlot(Object var1, double[] var2, double[] var3, double var4) {
        this.simplexPlot(var1, var2, var3, var4, this.nMax);
    }

    public void simplexPlot(RegressionFunction var1, RegressionDerivativeFunction var2, double[] var3, double[] var4, double var5) {
        this.simplexFlag = 1;
        this.derivFlag = 1;
        this.dualErrorsRequired = false;
        this.simplexPlot((Object)var1, (Object)var2, var3, var4, var5);
    }

    public void simplexPlot(RegressionFunction2 var1, RegressionDerivativeFunction2 var2, double[] var3, double[] var4, double var5) {
        this.simplexFlag = 2;
        this.derivFlag = 2;
        this.dualErrorsRequired = false;
        this.simplexPlot((Object)var1, (Object)var2, var3, var4, var5);
    }

    public void simplexPlot(RegressionFunction3 var1, RegressionDerivativeFunction var2, double[] var3, double[] var4, double var5) {
        this.simplexFlag = 3;
        this.derivFlag = 1;
        if (this.multipleY) {
            this.simplexFlag = 4;
        }

        this.dualErrorsRequired = true;
        this.simplexPlot((Object)var1, (Object)var2, var3, var4, var5);
    }

    public void simplexPlot(RegressionFunction3 var1, RegressionDerivativeFunction2 var2, double[] var3, double[] var4, double var5) {
        this.simplexFlag = 3;
        this.derivFlag = 2;
        if (this.multipleY) {
            this.simplexFlag = 4;
        }

        this.dualErrorsRequired = true;
        this.simplexPlot((Object)var1, (Object)var2, var3, var4, var5);
    }

    public void simplexPlot(Object var1, Object var2, double[] var3, double[] var4, double var5) {
        this.simplexPlot(var1, var2, var3, var4, var5, this.nMax);
    }

    public void simplex(RegressionFunction var1, double[] var2, double[] var3, int var4) {
        this.simplexFlag = 1;
        this.dualErrorsRequired = false;
        this.simplex((Object)var1, (double[])var2, var3, var4);
    }

    public void simplex(RegressionFunction2 var1, double[] var2, double[] var3, int var4) {
        this.simplexFlag = 2;
        this.dualErrorsRequired = false;
        this.simplex((Object)var1, (double[])var2, var3, var4);
    }

    public void simplex(RegressionFunction3 var1, double[] var2, double[] var3, int var4) {
        this.simplexFlag = 3;
        if (this.multipleY) {
            this.simplexFlag = 4;
        }

        this.dualErrorsRequired = true;
        this.simplex((Object)var1, (double[])var2, var3, var4);
    }

    public void simplex(Object var1, double[] var2, double[] var3, int var4) {
        this.simplex(var1, var2, var3, this.fTol, var4);
    }

    public void simplex(RegressionFunction var1, RegressionDerivativeFunction var2, double[] var3, double[] var4, int var5) {
        this.simplexFlag = 1;
        this.derivFlag = 1;
        this.dualErrorsRequired = false;
        this.simplex((Object)var1, (Object)var2, var3, var4, var5);
    }

    public void simplex(RegressionFunction2 var1, RegressionDerivativeFunction2 var2, double[] var3, double[] var4, int var5) {
        this.simplexFlag = 2;
        this.derivFlag = 2;
        this.dualErrorsRequired = false;
        this.simplex((Object)var1, (Object)var2, var3, var4, var5);
    }

    public void simplex(RegressionFunction3 var1, RegressionDerivativeFunction var2, double[] var3, double[] var4, int var5) {
        this.simplexFlag = 3;
        this.derivFlag = 1;
        if (this.multipleY) {
            this.simplexFlag = 4;
        }

        this.dualErrorsRequired = true;
        this.simplex((Object)var1, (Object)var2, var3, var4, var5);
    }

    public void simplex(RegressionFunction3 var1, RegressionDerivativeFunction2 var2, double[] var3, double[] var4, int var5) {
        this.simplexFlag = 3;
        this.derivFlag = 2;
        if (this.multipleY) {
            this.simplexFlag = 4;
        }

        this.dualErrorsRequired = true;
        this.simplex((Object)var1, (Object)var2, var3, var4, var5);
    }

    public void simplex(Object var1, Object var2, double[] var3, double[] var4, int var5) {
        this.simplex(var1, var2, var3, var4, this.fTol, var5);
    }

    public void simplexPlot(RegressionFunction var1, double[] var2, double[] var3, int var4) {
        this.simplexFlag = 1;
        this.dualErrorsRequired = false;
        this.simplexPlot((Object)var1, (double[])var2, var3, var4);
    }

    public void simplexPlot(RegressionFunction2 var1, double[] var2, double[] var3, int var4) {
        this.simplexFlag = 2;
        this.dualErrorsRequired = false;
        this.simplexPlot((Object)var1, (double[])var2, var3, var4);
    }

    public void simplexPlot(RegressionFunction3 var1, double[] var2, double[] var3, int var4) {
        this.simplexFlag = 3;
        this.dualErrorsRequired = true;
        if (this.multipleY) {
            this.simplexFlag = 4;
        }

        this.simplexPlot((Object)var1, (double[])var2, var3, var4);
    }

    public void simplexPlot(Object var1, double[] var2, double[] var3, int var4) {
        this.simplexPlot(var1, var2, var3, this.fTol, var4);
    }

    public void simplexPlot(RegressionFunction var1, RegressionDerivativeFunction var2, double[] var3, double[] var4, int var5) {
        this.simplexFlag = 1;
        this.derivFlag = 1;
        this.dualErrorsRequired = false;
        this.simplexPlot((Object)var1, (Object)var2, var3, var4, var5);
    }

    public void simplexPlot(RegressionFunction2 var1, RegressionDerivativeFunction2 var2, double[] var3, double[] var4, int var5) {
        this.simplexFlag = 2;
        this.derivFlag = 2;
        this.dualErrorsRequired = false;
        this.simplexPlot((Object)var1, (Object)var2, var3, var4, var5);
    }

    public void simplexPlot(RegressionFunction3 var1, RegressionDerivativeFunction var2, double[] var3, double[] var4, int var5) {
        this.simplexFlag = 3;
        this.derivFlag = 1;
        this.dualErrorsRequired = true;
        if (this.multipleY) {
            this.simplexFlag = 4;
        }

        this.simplexPlot((Object)var1, (Object)var2, var3, var4, var5);
    }

    public void simplexPlot(RegressionFunction3 var1, RegressionDerivativeFunction2 var2, double[] var3, double[] var4, int var5) {
        this.simplexFlag = 3;
        this.derivFlag = 2;
        if (this.multipleY) {
            this.simplexFlag = 4;
        }

        this.dualErrorsRequired = true;
        this.simplexPlot((Object)var1, (Object)var2, var3, var4, var5);
    }

    public void simplexPlot(Object var1, Object var2, double[] var3, double[] var4, int var5) {
        this.simplexPlot(var1, var2, var3, var4, this.fTol, var5);
    }

    public void simplex(RegressionFunction var1, double[] var2, double[] var3) {
        this.simplexFlag = 1;
        this.dualErrorsRequired = false;
        this.simplex((Object)var1, (double[])var2, var3);
    }

    public void simplex(RegressionFunction2 var1, double[] var2, double[] var3) {
        this.simplexFlag = 2;
        this.dualErrorsRequired = false;
        this.simplex((Object)var1, (double[])var2, var3);
    }

    public void simplex(RegressionFunction3 var1, double[] var2, double[] var3) {
        this.simplexFlag = 3;
        this.dualErrorsRequired = true;
        if (this.multipleY) {
            this.simplexFlag = 4;
        }

        this.simplex((Object)var1, (double[])var2, var3);
    }

    public void simplex(Object var1, double[] var2, double[] var3) {
        this.simplex(var1, var2, var3, this.fTol, this.nMax);
    }

    public void simplex(RegressionFunction var1, RegressionDerivativeFunction var2, double[] var3, double[] var4) {
        this.simplexFlag = 1;
        this.derivFlag = 1;
        this.dualErrorsRequired = false;
        this.simplex((Object)var1, (Object)var2, var3, var4);
    }

    public void simplex(RegressionFunction2 var1, RegressionDerivativeFunction2 var2, double[] var3, double[] var4) {
        this.simplexFlag = 2;
        this.derivFlag = 2;
        this.dualErrorsRequired = false;
        this.simplex((Object)var1, (Object)var2, var3, var4);
    }

    public void simplex(RegressionFunction3 var1, RegressionDerivativeFunction var2, double[] var3, double[] var4) {
        this.simplexFlag = 3;
        this.derivFlag = 1;
        if (this.multipleY) {
            this.simplexFlag = 4;
        }

        this.dualErrorsRequired = true;
        this.simplex((Object)var1, (Object)var2, var3, var4);
    }

    public void simplex(RegressionFunction3 var1, RegressionDerivativeFunction2 var2, double[] var3, double[] var4) {
        this.simplexFlag = 3;
        this.derivFlag = 2;
        if (this.multipleY) {
            this.simplexFlag = 4;
        }

        this.dualErrorsRequired = true;
        this.simplex((Object)var1, (Object)var2, var3, var4);
    }

    public void simplex(Object var1, Object var2, double[] var3, double[] var4) {
        this.simplex(var1, var2, var3, var4, this.fTol, this.nMax);
    }

    public void simplexPlot(RegressionFunction var1, double[] var2, double[] var3) {
        this.simplexFlag = 1;
        this.dualErrorsRequired = false;
        this.simplexPlot((Object)var1, (double[])var2, var3);
    }

    public void simplexPlot(RegressionFunction2 var1, double[] var2, double[] var3) {
        this.simplexFlag = 2;
        this.dualErrorsRequired = false;
        this.simplexPlot((Object)var1, (double[])var2, var3);
    }

    public void simplexPlot(RegressionFunction3 var1, double[] var2, double[] var3) {
        this.simplexFlag = 3;
        if (this.multipleY) {
            this.simplexFlag = 4;
        }

        this.dualErrorsRequired = true;
        this.simplexPlot((Object)var1, (double[])var2, var3);
    }

    public void simplexPlot(Object var1, double[] var2, double[] var3) {
        this.simplexPlot(var1, var2, var3, this.fTol, this.nMax);
    }

    public void simplexPlot(RegressionFunction var1, RegressionDerivativeFunction var2, double[] var3, double[] var4) {
        this.simplexFlag = 1;
        this.derivFlag = 1;
        this.dualErrorsRequired = false;
        this.simplexPlot((Object)var1, (Object)var2, var3, var4);
    }

    public void simplexPlot(RegressionFunction2 var1, RegressionDerivativeFunction2 var2, double[] var3, double[] var4) {
        this.simplexFlag = 2;
        this.derivFlag = 2;
        this.dualErrorsRequired = false;
        this.simplexPlot((Object)var1, (Object)var2, var3, var4);
    }

    public void simplexPlot(RegressionFunction3 var1, RegressionDerivativeFunction var2, double[] var3, double[] var4) {
        this.simplexFlag = 3;
        this.derivFlag = 1;
        if (this.multipleY) {
            this.simplexFlag = 4;
        }

        this.dualErrorsRequired = true;
        this.simplexPlot((Object)var1, (Object)var2, var3, var4);
    }

    public void simplexPlot(RegressionFunction3 var1, RegressionDerivativeFunction2 var2, double[] var3, double[] var4) {
        this.simplexFlag = 3;
        this.derivFlag = 2;
        if (this.multipleY) {
            this.simplexFlag = 4;
        }

        this.dualErrorsRequired = true;
        this.simplexPlot((Object)var1, (Object)var2, var3, var4);
    }

    public void simplexPlot(Object var1, Object var2, double[] var3, double[] var4) {
        this.simplexPlot(var1, var2, var3, var4, this.fTol, this.nMax);
    }

    public void simplex(RegressionFunction var1, double[] var2, double var3, int var5) {
        this.simplexFlag = 1;
        this.dualErrorsRequired = false;
        this.simplex((Object)var1, var2, var3, var5);
    }

    public void simplex(RegressionFunction2 var1, double[] var2, double var3, int var5) {
        this.simplexFlag = 2;
        this.dualErrorsRequired = false;
        this.simplex((Object)var1, var2, var3, var5);
    }

    public void simplex(RegressionFunction3 var1, double[] var2, double var3, int var5) {
        this.simplexFlag = 3;
        if (this.multipleY) {
            this.simplexFlag = 4;
        }

        this.dualErrorsRequired = true;
        this.simplex((Object)var1, var2, var3, var5);
    }

    public void simplex(Object var1, double[] var2, double var3, int var5) {
        int var6 = var2.length;
        double[] var7 = new double[var6];

        for(int var8 = 0; var8 < var6; ++var8) {
            var7[var8] = this.dStep * var2[var8];
        }

        this.simplex(var1, var2, var7, var3, var5);
    }

    public void simplex(RegressionFunction var1, RegressionDerivativeFunction var2, double[] var3, double var4, int var6) {
        this.simplexFlag = 1;
        this.derivFlag = 1;
        this.dualErrorsRequired = false;
        this.simplex((Object)var1, (Object)var2, var3, var4, var6);
    }

    public void simplex(RegressionFunction2 var1, RegressionDerivativeFunction2 var2, double[] var3, double var4, int var6) {
        this.simplexFlag = 2;
        this.derivFlag = 2;
        this.dualErrorsRequired = false;
        this.simplex((Object)var1, (Object)var2, var3, var4, var6);
    }

    public void simplex(RegressionFunction3 var1, RegressionDerivativeFunction var2, double[] var3, double var4, int var6) {
        this.simplexFlag = 3;
        this.derivFlag = 1;
        if (this.multipleY) {
            this.simplexFlag = 4;
        }

        this.dualErrorsRequired = true;
        this.simplex((Object)var1, (Object)var2, var3, var4, var6);
    }

    public void simplex(RegressionFunction3 var1, RegressionDerivativeFunction2 var2, double[] var3, double var4, int var6) {
        this.simplexFlag = 3;
        this.derivFlag = 2;
        if (this.multipleY) {
            this.simplexFlag = 4;
        }

        this.dualErrorsRequired = true;
        this.simplex((Object)var1, (Object)var2, var3, var4, var6);
    }

    public void simplex(Object var1, Object var2, double[] var3, double var4, int var6) {
        int var7 = var3.length;
        double[] var8 = new double[var7];

        for(int var9 = 0; var9 < var7; ++var9) {
            var8[var9] = this.dStep * var3[var9];
        }

        this.simplex(var1, var2, var3, var8, var4, var6);
    }

    public void simplexPlot(RegressionFunction var1, double[] var2, double var3, int var5) {
        this.simplexFlag = 1;
        this.dualErrorsRequired = false;
        this.simplexPlot((Object)var1, var2, var3, var5);
    }

    public void simplexPlot(RegressionFunction2 var1, double[] var2, double var3, int var5) {
        this.simplexFlag = 2;
        this.dualErrorsRequired = false;
        this.simplexPlot((Object)var1, var2, var3, var5);
    }

    public void simplexPlot(RegressionFunction3 var1, double[] var2, double var3, int var5) {
        this.simplexFlag = 3;
        this.dualErrorsRequired = true;
        if (this.multipleY) {
            this.simplexFlag = 4;
        }

        this.simplexPlot((Object)var1, var2, var3, var5);
    }

    public void simplexPlot(Object var1, double[] var2, double var3, int var5) {
        int var6 = var2.length;
        double[] var7 = new double[var6];

        for(int var8 = 0; var8 < var6; ++var8) {
            var7[var8] = this.dStep * var2[var8];
        }

        this.simplexPlot(var1, var2, var7, var3, var5);
    }

    public void simplexPlot(RegressionFunction var1, RegressionDerivativeFunction var2, double[] var3, double var4, int var6) {
        this.simplexFlag = 1;
        this.derivFlag = 1;
        this.dualErrorsRequired = false;
        this.simplexPlot((Object)var1, (Object)var2, var3, var4, var6);
    }

    public void simplexPlot(RegressionFunction2 var1, RegressionDerivativeFunction2 var2, double[] var3, double var4, int var6) {
        this.simplexFlag = 2;
        this.derivFlag = 2;
        this.dualErrorsRequired = false;
        this.simplexPlot((Object)var1, (Object)var2, var3, var4, var6);
    }

    public void simplexPlot(RegressionFunction3 var1, RegressionDerivativeFunction var2, double[] var3, double var4, int var6) {
        this.simplexFlag = 3;
        this.derivFlag = 1;
        if (this.multipleY) {
            this.simplexFlag = 4;
        }

        this.dualErrorsRequired = true;
        this.simplexPlot((Object)var1, (Object)var2, var3, var4, var6);
    }

    public void simplexPlot(RegressionFunction3 var1, RegressionDerivativeFunction2 var2, double[] var3, double var4, int var6) {
        this.simplexFlag = 3;
        this.derivFlag = 2;
        if (this.multipleY) {
            this.simplexFlag = 4;
        }

        this.dualErrorsRequired = true;
        this.simplexPlot((Object)var1, (Object)var2, var3, var4, var6);
    }

    public void simplexPlot(Object var1, Object var2, double[] var3, double var4, int var6) {
        int var7 = var3.length;
        double[] var8 = new double[var7];

        for(int var9 = 0; var9 < var7; ++var9) {
            var8[var9] = this.dStep * var3[var9];
        }

        this.simplexPlot(var1, var2, var3, var8, var4, var6);
    }

    public void simplex(RegressionFunction var1, double[] var2, double var3) {
        this.simplexFlag = 1;
        this.dualErrorsRequired = false;
        this.simplex((Object)var1, var2, var3);
    }

    public void simplex(RegressionFunction2 var1, double[] var2, double var3) {
        this.simplexFlag = 2;
        this.dualErrorsRequired = false;
        this.simplex((Object)var1, var2, var3);
    }

    public void simplex(RegressionFunction3 var1, double[] var2, double var3) {
        this.simplexFlag = 3;
        if (this.multipleY) {
            this.simplexFlag = 4;
        }

        this.dualErrorsRequired = true;
        this.simplex((Object)var1, var2, var3);
    }

    public void simplex(Object var1, double[] var2, double var3) {
        int var5 = var2.length;
        double[] var6 = new double[var5];

        for(int var7 = 0; var7 < var5; ++var7) {
            var6[var7] = this.dStep * var2[var7];
        }

        this.simplex(var1, var2, var6, var3, this.nMax);
    }

    public void simplex(RegressionFunction var1, RegressionDerivativeFunction var2, double[] var3, double var4) {
        this.simplexFlag = 1;
        this.derivFlag = 1;
        this.dualErrorsRequired = false;
        this.simplex((Object)var1, (Object)var2, var3, var4);
    }

    public void simplex(RegressionFunction2 var1, RegressionDerivativeFunction2 var2, double[] var3, double var4) {
        this.simplexFlag = 2;
        this.derivFlag = 2;
        this.dualErrorsRequired = false;
        this.simplex((Object)var1, (Object)var2, var3, var4);
    }

    public void simplex(RegressionFunction3 var1, RegressionDerivativeFunction var2, double[] var3, double var4) {
        this.simplexFlag = 3;
        this.derivFlag = 1;
        if (this.multipleY) {
            this.simplexFlag = 4;
        }

        this.dualErrorsRequired = true;
        this.simplex((Object)var1, (Object)var2, var3, var4);
    }

    public void simplex(RegressionFunction3 var1, RegressionDerivativeFunction2 var2, double[] var3, double var4) {
        this.simplexFlag = 3;
        this.derivFlag = 2;
        if (this.multipleY) {
            this.simplexFlag = 4;
        }

        this.dualErrorsRequired = true;
        this.simplex((Object)var1, (Object)var2, var3, var4);
    }

    public void simplex(Object var1, Object var2, double[] var3, double var4) {
        int var6 = var3.length;
        double[] var7 = new double[var6];

        for(int var8 = 0; var8 < var6; ++var8) {
            var7[var8] = this.dStep * var3[var8];
        }

        this.simplex(var1, var2, var3, var7, var4, this.nMax);
    }

    public void simplexPlot(RegressionFunction var1, double[] var2, double var3) {
        this.simplexFlag = 1;
        this.dualErrorsRequired = false;
        this.simplexPlot((Object)var1, var2, var3);
    }

    public void simplexPlot(RegressionFunction2 var1, double[] var2, double var3) {
        this.simplexFlag = 2;
        this.dualErrorsRequired = false;
        this.simplexPlot((Object)var1, var2, var3);
    }

    public void simplexPlot(RegressionFunction3 var1, double[] var2, double var3) {
        this.simplexFlag = 3;
        if (this.multipleY) {
            this.simplexFlag = 4;
        }

        this.dualErrorsRequired = true;
        this.simplexPlot((Object)var1, var2, var3);
    }

    public void simplexPlot(Object var1, double[] var2, double var3) {
        int var5 = var2.length;
        double[] var6 = new double[var5];

        for(int var7 = 0; var7 < var5; ++var7) {
            var6[var7] = this.dStep * var2[var7];
        }

        this.simplexPlot(var1, var2, var6, var3, this.nMax);
    }

    public void simplexPlot(RegressionFunction var1, RegressionDerivativeFunction var2, double[] var3, double var4) {
        this.simplexFlag = 1;
        this.derivFlag = 1;
        this.dualErrorsRequired = false;
        this.simplexPlot((Object)var1, (Object)var2, var3, var4);
    }

    public void simplexPlot(RegressionFunction2 var1, RegressionDerivativeFunction2 var2, double[] var3, double var4) {
        this.simplexFlag = 2;
        this.derivFlag = 2;
        this.dualErrorsRequired = false;
        this.simplexPlot((Object)var1, (Object)var2, var3, var4);
    }

    public void simplexPlot(RegressionFunction3 var1, RegressionDerivativeFunction var2, double[] var3, double var4) {
        this.simplexFlag = 3;
        this.derivFlag = 1;
        if (this.multipleY) {
            this.simplexFlag = 4;
        }

        this.dualErrorsRequired = true;
        this.simplexPlot((Object)var1, (Object)var2, var3, var4);
    }

    public void simplexPlot(RegressionFunction3 var1, RegressionDerivativeFunction2 var2, double[] var3, double var4) {
        this.simplexFlag = 3;
        this.derivFlag = 2;
        if (this.multipleY) {
            this.simplexFlag = 4;
        }

        this.dualErrorsRequired = true;
        this.simplexPlot((Object)var1, (Object)var2, var3, var4);
    }

    public void simplexPlot(Object var1, Object var2, double[] var3, double var4) {
        int var6 = var3.length;
        double[] var7 = new double[var6];

        for(int var8 = 0; var8 < var6; ++var8) {
            var7[var8] = this.dStep * var3[var8];
        }

        this.simplexPlot(var1, var2, var3, var7, var4, this.nMax);
    }

    public void simplex(RegressionFunction var1, double[] var2, int var3) {
        this.simplexFlag = 1;
        this.dualErrorsRequired = false;
        this.simplex((Object)var1, var2, var3);
    }

    public void simplex(RegressionFunction2 var1, double[] var2, int var3) {
        this.simplexFlag = 2;
        this.dualErrorsRequired = false;
        this.simplex((Object)var1, var2, var3);
    }

    public void simplex(RegressionFunction3 var1, double[] var2, int var3) {
        this.simplexFlag = 3;
        if (this.multipleY) {
            this.simplexFlag = 4;
        }

        this.dualErrorsRequired = true;
        this.simplex((Object)var1, var2, var3);
    }

    public void simplex(Object var1, double[] var2, int var3) {
        int var4 = var2.length;
        double[] var5 = new double[var4];

        for(int var6 = 0; var6 < var4; ++var6) {
            var5[var6] = this.dStep * var2[var6];
        }

        this.simplex(var1, var2, var5, this.fTol, var3);
    }

    public void simplex(RegressionFunction var1, RegressionDerivativeFunction var2, double[] var3, int var4) {
        this.simplexFlag = 1;
        this.derivFlag = 1;
        this.dualErrorsRequired = false;
        this.simplex((Object)var1, (Object)var2, var3, var4);
    }

    public void simplex(RegressionFunction2 var1, RegressionDerivativeFunction2 var2, double[] var3, int var4) {
        this.simplexFlag = 2;
        this.derivFlag = 2;
        this.dualErrorsRequired = false;
        this.simplex((Object)var1, (Object)var2, var3, var4);
    }

    public void simplex(RegressionFunction3 var1, RegressionDerivativeFunction var2, double[] var3, int var4) {
        this.simplexFlag = 3;
        this.derivFlag = 1;
        if (this.multipleY) {
            this.simplexFlag = 4;
        }

        this.dualErrorsRequired = true;
        this.simplex((Object)var1, (Object)var2, var3, var4);
    }

    public void simplex(RegressionFunction3 var1, RegressionDerivativeFunction2 var2, double[] var3, int var4) {
        this.simplexFlag = 3;
        this.derivFlag = 2;
        if (this.multipleY) {
            this.simplexFlag = 4;
        }

        this.dualErrorsRequired = true;
        this.simplex((Object)var1, (Object)var2, var3, var4);
    }

    public void simplex(Object var1, Object var2, double[] var3, int var4) {
        int var5 = var3.length;
        double[] var6 = new double[var5];

        for(int var7 = 0; var7 < var5; ++var7) {
            var6[var7] = this.dStep * var3[var7];
        }

        this.simplex(var1, var2, var3, var6, this.fTol, var4);
    }

    public void simplexPlot(RegressionFunction var1, double[] var2, int var3) {
        this.simplexFlag = 1;
        this.dualErrorsRequired = false;
        this.simplexPlot((Object)var1, var2, var3);
    }

    public void simplexPlot(RegressionFunction2 var1, double[] var2, int var3) {
        this.simplexFlag = 2;
        this.dualErrorsRequired = false;
        this.simplexPlot((Object)var1, var2, var3);
    }

    public void simplexPlot(RegressionFunction3 var1, double[] var2, int var3) {
        this.simplexFlag = 3;
        this.dualErrorsRequired = true;
        if (this.multipleY) {
            this.simplexFlag = 4;
        }

        this.simplexPlot((Object)var1, var2, var3);
    }

    public void simplexPlot(Object var1, double[] var2, int var3) {
        int var4 = var2.length;
        double[] var5 = new double[var4];

        for(int var6 = 0; var6 < var4; ++var6) {
            var5[var6] = this.dStep * var2[var6];
        }

        this.simplexPlot(var1, var2, var5, this.fTol, var3);
    }

    public void simplexPlot(RegressionFunction var1, RegressionDerivativeFunction var2, double[] var3, int var4) {
        this.simplexFlag = 1;
        this.derivFlag = 1;
        this.dualErrorsRequired = false;
        this.simplexPlot((Object)var1, (Object)var2, var3, var4);
    }

    public void simplexPlot(RegressionFunction2 var1, RegressionDerivativeFunction2 var2, double[] var3, int var4) {
        this.simplexFlag = 2;
        this.derivFlag = 2;
        this.dualErrorsRequired = false;
        this.simplexPlot((Object)var1, (Object)var2, var3, var4);
    }

    public void simplexPlot(RegressionFunction3 var1, RegressionDerivativeFunction var2, double[] var3, int var4) {
        this.simplexFlag = 3;
        this.derivFlag = 1;
        if (this.multipleY) {
            this.simplexFlag = 4;
        }

        this.dualErrorsRequired = true;
        this.simplexPlot((Object)var1, (Object)var2, var3, var4);
    }

    public void simplexPlot(RegressionFunction3 var1, RegressionDerivativeFunction2 var2, double[] var3, int var4) {
        this.simplexFlag = 3;
        this.derivFlag = 2;
        if (this.multipleY) {
            this.simplexFlag = 4;
        }

        this.dualErrorsRequired = true;
        this.simplexPlot((Object)var1, (Object)var2, var3, var4);
    }

    public void simplexPlot(Object var1, Object var2, double[] var3, int var4) {
        int var5 = var3.length;
        double[] var6 = new double[var5];

        for(int var7 = 0; var7 < var5; ++var7) {
            var6[var7] = this.dStep * var3[var7];
        }

        this.simplexPlot(var1, var2, var3, var6, this.fTol, var4);
    }

    public void simplex(RegressionFunction var1, double[] var2) {
        this.simplexFlag = 1;
        this.dualErrorsRequired = false;
        this.simplex((Object)var1, var2);
    }

    public void simplex(RegressionFunction2 var1, double[] var2) {
        this.simplexFlag = 2;
        this.dualErrorsRequired = false;
        this.simplex((Object)var1, var2);
    }

    public void simplex(RegressionFunction3 var1, double[] var2) {
        this.simplexFlag = 3;
        if (this.multipleY) {
            this.simplexFlag = 4;
        }

        this.dualErrorsRequired = true;
        this.simplex((Object)var1, var2);
    }

    public void simplex(Object var1, double[] var2) {
        int var3 = var2.length;
        double[] var4 = new double[var3];

        for(int var5 = 0; var5 < var3; ++var5) {
            var4[var5] = this.dStep * var2[var5];
        }

        this.simplex(var1, var2, var4, this.fTol, this.nMax);
    }

    public void simplex(RegressionFunction var1, RegressionDerivativeFunction var2, double[] var3) {
        this.simplexFlag = 1;
        this.derivFlag = 1;
        this.dualErrorsRequired = false;
        this.simplex((Object)var1, (Object)var2, var3);
    }

    public void simplex(RegressionFunction2 var1, RegressionDerivativeFunction2 var2, double[] var3) {
        this.simplexFlag = 2;
        this.derivFlag = 2;
        this.dualErrorsRequired = false;
        this.simplex((Object)var1, (Object)var2, var3);
    }

    public void simplex(RegressionFunction3 var1, RegressionDerivativeFunction var2, double[] var3) {
        this.simplexFlag = 3;
        this.derivFlag = 1;
        if (this.multipleY) {
            this.simplexFlag = 4;
        }

        this.dualErrorsRequired = true;
        this.simplex((Object)var1, (Object)var2, var3);
    }

    public void simplex(RegressionFunction3 var1, RegressionDerivativeFunction2 var2, double[] var3) {
        this.simplexFlag = 3;
        this.derivFlag = 2;
        if (this.multipleY) {
            this.simplexFlag = 4;
        }

        this.dualErrorsRequired = true;
        this.simplex((Object)var1, (Object)var2, var3);
    }

    public void simplex(Object var1, Object var2, double[] var3) {
        int var4 = var3.length;
        double[] var5 = new double[var4];

        for(int var6 = 0; var6 < var4; ++var6) {
            var5[var6] = this.dStep * var3[var6];
        }

        this.simplex(var1, var2, var3, var5, this.fTol, this.nMax);
    }

    public void simplexPlot(RegressionFunction var1, double[] var2) {
        this.simplexFlag = 1;
        this.dualErrorsRequired = false;
        this.simplexPlot((Object)var1, var2);
    }

    public void simplexPlot(RegressionFunction2 var1, double[] var2) {
        this.simplexFlag = 2;
        this.dualErrorsRequired = false;
        this.simplexPlot((Object)var1, var2);
    }

    public void simplexPlot(RegressionFunction3 var1, double[] var2) {
        this.simplexFlag = 3;
        if (this.multipleY) {
            this.simplexFlag = 4;
        }

        this.dualErrorsRequired = true;
        this.simplexPlot((Object)var1, var2);
    }

    public void simplexPlot(Object var1, double[] var2) {
        int var3 = var2.length;
        double[] var4 = new double[var3];

        for(int var5 = 0; var5 < var3; ++var5) {
            var4[var5] = this.dStep * var2[var5];
        }

        this.simplexPlot(var1, var2, var4, this.fTol, this.nMax);
    }

    public void simplexPlot(RegressionFunction var1, RegressionDerivativeFunction var2, double[] var3) {
        this.simplexFlag = 1;
        this.derivFlag = 1;
        this.dualErrorsRequired = false;
        this.simplexPlot((Object)var1, (Object)var2, var3);
    }

    public void simplexPlot(RegressionFunction2 var1, RegressionDerivativeFunction2 var2, double[] var3) {
        this.simplexFlag = 2;
        this.derivFlag = 2;
        this.dualErrorsRequired = false;
        this.simplexPlot((Object)var1, (Object)var2, var3);
    }

    public void simplexPlot(RegressionFunction3 var1, RegressionDerivativeFunction var2, double[] var3) {
        this.simplexFlag = 3;
        this.derivFlag = 1;
        if (this.multipleY) {
            this.simplexFlag = 4;
        }

        this.dualErrorsRequired = true;
        this.simplexPlot((Object)var1, (Object)var2, var3);
    }

    public void simplexPlot(RegressionFunction3 var1, RegressionDerivativeFunction2 var2, double[] var3) {
        this.simplexFlag = 3;
        this.derivFlag = 2;
        if (this.multipleY) {
            this.simplexFlag = 4;
        }

        this.dualErrorsRequired = true;
        this.simplexPlot((Object)var1, (Object)var2, var3);
    }

    public void simplexPlot(Object var1, Object var2, double[] var3) {
        int var4 = var3.length;
        double[] var5 = new double[var4];

        for(int var6 = 0; var6 < var4; ++var6) {
            var5[var6] = this.dStep * var3[var6];
        }

        this.simplexPlot(var1, var2, var3, var5, this.fTol, this.nMax);
    }

    public void print(String var1, int var2) {
        this.prec = var2;
        this.print(var1);
    }

    public void print(int var1) {
        this.prec = var1;
        String var2 = null;
        if (this.bestPolyFlag) {
            var2 = "BestPolynomialOutput.txt";
        } else {
            var2 = "RegressionOutput.txt";
        }

        this.print(var2);
    }

    public void print(String var1) {
        if (var1.indexOf(46) == -1) {
            var1 = var1 + ".txt";
        }

        FileOutput var2 = new FileOutput(var1, 'n');
        var2.dateAndTimeln(var1);
        var2.println(this.graphTitle);
        this.paraName = new String[this.nParam];
        this.constraintString = new String[this.nParam];

        int var3;
        for(var3 = 0; var3 < this.nParam; ++var3) {
            this.constraintString[var3] = "none";
        }

        var3 = this.constrainedSingle.size() / 2;
        int var4 = 0;

        int var5;
        int var6;
        for(var5 = 0; var5 < var3; ++var5) {
            var6 = (Integer)this.constrainedSingle.get(var4++);
            this.constraintString[var6] = (String)this.constrainedSingle.get(var4++);
        }

        var3 = this.constrainedMultiple.size() / 2;
        var4 = 0;

        for(var5 = 0; var5 < var3; ++var5) {
            var6 = (Integer)this.constrainedMultiple.get(var4++);
            if (this.constraintString[var6].equals("none")) {
                this.constraintString[var6] = (String)this.constrainedMultiple.get(var4++);
            } else {
                this.constraintString[var6] = this.constraintString[var6] + "; " + (String)this.constrainedMultiple.get(var4++);
            }
        }

        if (this.lastMethod == 38) {
            this.paraName = new String[3];
        }

        if (this.bestPolyFlag) {
            if (this.bestPolyTooFewN) {
                var2.println("WARNING!!");
                var2.println("This is the best fit polynomial returned by the bestPolynomial method");
                var2.println("However the degree is two less than the number of points, i.e. the highest statistically valid polynomial that the data allows.");
                var2.println("It may not be the best fit polynomial.  More data points may reveal a higher degree polynomial to be a better fit.");
                var2.println(" ");
            } else {
                var2.println("This is the best fit found by the method bestPolynomial");
            }
        }

        if (this.weightOpt) {
            var2.println("Weighted Least Squares Minimisation");
        } else {
            var2.println("Unweighted Least Squares Minimisation");
        }

        switch(this.lastMethod) {
            case 0:
                var2.println("Linear Regression with intercept");
                var2.println("y = c[0] + c[1]*x1 + c[2]*x2 +c[3]*x3 + . . .");

                for(var5 = 0; var5 < this.nParam; ++var5) {
                    this.paraName[var5] = "c[" + var5 + "]";
                }

                this.linearPrint(var2);
                break;
            case 1:
                var2.println("Polynomial (with degree = " + (this.nParam - 1) + "), Fitting: Linear Regression");
                var2.println("y = c[0] + c[1]*x + c[2]*x^2 +c[3]*x^3 + . . .");

                for(var5 = 0; var5 < this.nParam; ++var5) {
                    this.paraName[var5] = "c[" + var5 + "]";
                }

                this.linearPrint(var2);
                break;
            case 2:
                var2.println("Generalised linear regression");
                var2.println("y = c[0]*f1(x) + c[1]*f2(x) + c[2]*f3(x) + . . .");

                for(var5 = 0; var5 < this.nParam; ++var5) {
                    this.paraName[var5] = "c[" + var5 + "]";
                }

                this.linearPrint(var2);
                break;
            case 3:
                var2.println("Nelder and Mead Simplex Non-linear Regression");
                var2.println("y = f(x1, x2, x3 . . ., c[0], c[1], c[2] . . .");
                var2.println("y is non-linear with respect to the c[i]");

                for(var5 = 0; var5 < this.nParam; ++var5) {
                    this.paraName[var5] = "c[" + var5 + "]";
                }

                this.nonLinearPrint(var2);
                break;
            case 4:
                var2.println("Fitting to a Normal (Gaussian) distribution");
                var2.println("y = (yscale/(sd.sqrt(2.pi)).exp(0.5.square((x-mean)/sd))");
                var2.println("Nelder and Mead Simplex used to fit the data");
                this.paraName[0] = "mean";
                this.paraName[1] = "sd";
                if (this.scaleFlag) {
                    this.paraName[2] = "y scale";
                }

                this.nonLinearPrint(var2);
                break;
            case 5:
                var2.println("Fitting to a Lorentzian distribution");
                var2.println("y = (yscale/pi).(gamma/2)/((x-mean)^2+(gamma/2)^2)");
                var2.println("Nelder and Mead Simplex used to fit the data");
                this.paraName[0] = "mean";
                this.paraName[1] = "gamma";
                if (this.scaleFlag) {
                    this.paraName[2] = "y scale";
                }

                this.nonLinearPrint(var2);
                break;
            case 6:
                var2.println("Fitting to a Poisson distribution");
                var2.println("y = yscale.mu^k.exp(-mu)/mu!");
                var2.println("Nelder and Mead Simplex used to fit the data");
                this.paraName[0] = "mean";
                if (this.scaleFlag) {
                    this.paraName[1] = "y scale";
                }

                this.nonLinearPrint(var2);
                break;
            case 7:
                var2.println("Fitting to a Two Parameter Minimum Order Statistic Gumbel [Type 1 Extreme Value] Distribution");
                var2.println("y = (yscale/sigma)*exp((x - mu)/sigma))*exp(-exp((x-mu)/sigma))");
                var2.println("Nelder and Mead Simplex used to fit the data");
                this.paraName[0] = "mu";
                this.paraName[1] = "sigma";
                if (this.scaleFlag) {
                    this.paraName[2] = "y scale";
                }

                this.nonLinearPrint(var2);
                break;
            case 8:
                var2.println("Fitting to a Two Parameter Maximum Order Statistic Gumbel [Type 1 Extreme Value] Distribution");
                var2.println("y = (yscale/sigma)*exp(-(x - mu)/sigma))*exp(-exp(-(x-mu)/sigma))");
                var2.println("Nelder and Mead Simplex used to fit the data");
                this.paraName[0] = "mu";
                this.paraName[1] = "sigma";
                if (this.scaleFlag) {
                    this.paraName[2] = "y scale";
                }

                this.nonLinearPrint(var2);
                break;
            case 9:
                var2.println("Fitting to a One Parameter Minimum Order Statistic Gumbel [Type 1 Extreme Value] Distribution");
                var2.println("y = (yscale)*exp(x/sigma))*exp(-exp(x/sigma))");
                var2.println("Nelder and Mead Simplex used to fit the data");
                this.paraName[0] = "sigma";
                if (this.scaleFlag) {
                    this.paraName[1] = "y scale";
                }

                this.nonLinearPrint(var2);
                break;
            case 10:
                var2.println("Fitting to a One Parameter Maximum Order Statistic Gumbel [Type 1 Extreme Value] Distribution");
                var2.println("y = (yscale)*exp(-x/sigma))*exp(-exp(-x/sigma))");
                var2.println("Nelder and Mead Simplex used to fit the data");
                this.paraName[0] = "sigma";
                if (this.scaleFlag) {
                    this.paraName[1] = "y scale";
                }

                this.nonLinearPrint(var2);
                break;
            case 11:
                var2.println("Fitting to a Standard Minimum Order Statistic Gumbel [Type 1 Extreme Value] Distribution");
                var2.println("y = (yscale)*exp(x))*exp(-exp(x))");
                var2.println("Linear regression used to fit y = yscale*z where z = exp(x))*exp(-exp(x)))");
                if (this.scaleFlag) {
                    this.paraName[0] = "y scale";
                }

                this.linearPrint(var2);
                break;
            case 12:
                var2.println("Fitting to a Standard Maximum Order Statistic Gumbel [Type 1 Extreme Value] Distribution");
                var2.println("y = (yscale)*exp(-x))*exp(-exp(-x))");
                var2.println("Linear regression used to fit y = yscale*z where z = exp(-x))*exp(-exp(-x)))");
                if (this.scaleFlag) {
                    this.paraName[0] = "y scale";
                }

                this.linearPrint(var2);
                break;
            case 13:
                var2.println("Fitting to a Three Parameter Frechet [Type 2 Extreme Value] Distribution");
                var2.println("y = yscale.(gamma/sigma)*((x - mu)/sigma)^(-gamma-1)*exp(-((x-mu)/sigma)^-gamma");
                var2.println("Nelder and Mead Simplex used to fit the data");
                this.paraName[0] = "mu";
                this.paraName[1] = "sigma";
                this.paraName[2] = "gamma";
                if (this.scaleFlag) {
                    this.paraName[3] = "y scale";
                }

                this.nonLinearPrint(var2);
                break;
            case 14:
                var2.println("Fitting to a Two parameter Frechet [Type2  Extreme Value] Distribution");
                var2.println("y = yscale.(gamma/sigma)*(x/sigma)^(-gamma-1)*exp(-(x/sigma)^-gamma");
                var2.println("Nelder and Mead Simplex used to fit the data");
                this.paraName[0] = "sigma";
                this.paraName[1] = "gamma";
                if (this.scaleFlag) {
                    this.paraName[2] = "y scale";
                }

                this.nonLinearPrint(var2);
                break;
            case 15:
                var2.println("Fitting to a Standard Frechet [Type 2 Extreme Value] Distribution");
                var2.println("y = yscale.gamma*(x)^(-gamma-1)*exp(-(x)^-gamma");
                var2.println("Nelder and Mead Simplex used to fit the data");
                this.paraName[0] = "gamma";
                if (this.scaleFlag) {
                    this.paraName[1] = "y scale";
                }

                this.nonLinearPrint(var2);
                break;
            case 16:
                var2.println("Fitting to a Three parameter Weibull [Type 3 Extreme Value] Distribution");
                var2.println("y = yscale.(gamma/sigma)*((x - mu)/sigma)^(gamma-1)*exp(-((x-mu)/sigma)^gamma");
                var2.println("Nelder and Mead Simplex used to fit the data");
                this.paraName[0] = "mu";
                this.paraName[1] = "sigma";
                this.paraName[2] = "gamma";
                if (this.scaleFlag) {
                    this.paraName[3] = "y scale";
                }

                this.nonLinearPrint(var2);
                break;
            case 17:
                var2.println("Fitting to a Two parameter Weibull [Type 3 Extreme Value] Distribution");
                var2.println("y = yscale.(gamma/sigma)*(x/sigma)^(gamma-1)*exp(-(x/sigma)^gamma");
                var2.println("Nelder and Mead Simplex used to fit the data");
                this.paraName[0] = "sigma";
                this.paraName[1] = "gamma";
                if (this.scaleFlag) {
                    this.paraName[2] = "y scale";
                }

                this.nonLinearPrint(var2);
                break;
            case 18:
                var2.println("Fitting to a Standard Weibull [Type 3 Extreme Value] Distribution");
                var2.println("y = yscale.gamma*(x)^(gamma-1)*exp(-(x)^gamma");
                var2.println("Nelder and Mead Simplex used to fit the data");
                this.paraName[0] = "gamma";
                if (this.scaleFlag) {
                    this.paraName[1] = "y scale";
                }

                this.nonLinearPrint(var2);
                break;
            case 19:
                var2.println("Fitting to a Two parameter Exponential Distribution");
                var2.println("y = (yscale/sigma)*exp(-(x-mu)/sigma)");
                var2.println("Nelder and Mead Simplex used to fit the data");
                this.paraName[0] = "mu";
                this.paraName[1] = "sigma";
                if (this.scaleFlag) {
                    this.paraName[2] = "y scale";
                }

                this.nonLinearPrint(var2);
                break;
            case 20:
                var2.println("Fitting to a One parameter Exponential Distribution");
                var2.println("y = (yscale/sigma)*exp(-x/sigma)");
                var2.println("Nelder and Mead Simplex used to fit the data");
                this.paraName[0] = "sigma";
                if (this.scaleFlag) {
                    this.paraName[1] = "y scale";
                }

                this.nonLinearPrint(var2);
                break;
            case 21:
                var2.println("Fitting to a Standard Exponential Distribution");
                var2.println("y = yscale*exp(-x)");
                var2.println("Nelder and Mead Simplex used to fit the data");
                if (this.scaleFlag) {
                    this.paraName[0] = "y scale";
                }

                this.nonLinearPrint(var2);
                break;
            case 22:
                var2.println("Fitting to a Rayleigh Distribution");
                var2.println("y = (yscale/sigma)*(x/sigma)*exp(-0.5*(x/sigma)^2)");
                var2.println("Nelder and Mead Simplex used to fit the data");
                this.paraName[0] = "sigma";
                if (this.scaleFlag) {
                    this.paraName[1] = "y scale";
                }

                this.nonLinearPrint(var2);
                break;
            case 23:
                var2.println("Fitting to a Two Parameter Pareto Distribution");
                var2.println("y = yscale*(alpha*beta^alpha)/(x^(alpha+1))");
                var2.println("Nelder and Mead Simplex used to fit the data");
                this.paraName[0] = "alpha";
                this.paraName[1] = "beta";
                if (this.scaleFlag) {
                    this.paraName[2] = "y scale";
                }

                this.nonLinearPrint(var2);
                break;
            case 24:
                var2.println("Fitting to a One Parameter Pareto Distribution");
                var2.println("y = yscale*(alpha)/(x^(alpha+1))");
                var2.println("Nelder and Mead Simplex used to fit the data");
                this.paraName[0] = "alpha";
                if (this.scaleFlag) {
                    this.paraName[1] = "y scale";
                }

                this.nonLinearPrint(var2);
                break;
            case 25:
                var2.println("Fitting to a Sigmoidal Threshold Function");
                var2.println("y = yscale/(1 + exp(-slopeTerm(x - theta)))");
                var2.println("Nelder and Mead Simplex used to fit the data");
                this.paraName[0] = "slope term";
                this.paraName[1] = "theta";
                if (this.scaleFlag) {
                    this.paraName[2] = "y scale";
                }

                this.nonLinearPrint(var2);
                break;
            case 26:
                var2.println("Fitting to a Rectangular Hyperbola");
                var2.println("y = yscale.x/(theta + x)");
                var2.println("Nelder and Mead Simplex used to fit the data");
                this.paraName[0] = "theta";
                if (this.scaleFlag) {
                    this.paraName[1] = "y scale";
                }

                this.nonLinearPrint(var2);
                break;
            case 27:
                var2.println("Fitting to a Scaled Heaviside Step Function");
                var2.println("y = yscale.H(x - theta)");
                var2.println("Nelder and Mead Simplex used to fit the data");
                this.paraName[0] = "theta";
                if (this.scaleFlag) {
                    this.paraName[1] = "y scale";
                }

                this.nonLinearPrint(var2);
                break;
            case 28:
                var2.println("Fitting to a Hill/Sips Sigmoid");
                var2.println("y = yscale.x^n/(theta^n + x^n)");
                var2.println("Nelder and Mead Simplex used to fit the data");
                this.paraName[0] = "theta";
                this.paraName[1] = "n";
                if (this.scaleFlag) {
                    this.paraName[2] = "y scale";
                }

                this.nonLinearPrint(var2);
                break;
            case 29:
                var2.println("Fitting to a Shifted Pareto Distribution");
                var2.println("y = yscale*(alpha*beta^alpha)/((x-theta)^(alpha+1))");
                var2.println("Nelder and Mead Simplex used to fit the data");
                this.paraName[0] = "alpha";
                this.paraName[1] = "beta";
                this.paraName[2] = "theta";
                if (this.scaleFlag) {
                    this.paraName[3] = "y scale";
                }

                this.nonLinearPrint(var2);
                break;
            case 30:
                var2.println("Fitting to a Logistic distribution");
                var2.println("y = yscale*exp(-(x-mu)/beta)/(beta*(1 + exp(-(x-mu)/beta))^2");
                var2.println("Nelder and Mead Simplex used to fit the data");
                this.paraName[0] = "mu";
                this.paraName[1] = "beta";
                if (this.scaleFlag) {
                    this.paraName[2] = "y scale";
                }

                this.nonLinearPrint(var2);
                break;
            case 31:
                var2.println("Fitting to a Beta distribution - [0, 1] interval");
                var2.println("y = yscale*x^(alpha-1)*(1-x)^(beta-1)/B(alpha, beta)");
                var2.println("Nelder and Mead Simplex used to fit the data");
                this.paraName[0] = "alpha";
                this.paraName[1] = "beta";
                if (this.scaleFlag) {
                    this.paraName[2] = "y scale";
                }

                this.nonLinearPrint(var2);
                break;
            case 32:
                var2.println("Fitting to a Beta distribution - [min, max] interval");
                var2.println("y = yscale*(x-min)^(alpha-1)*(max-x)^(beta-1)/(B(alpha, beta)*(max-min)^(alpha+beta-1)");
                var2.println("Nelder and Mead Simplex used to fit the data");
                this.paraName[0] = "alpha";
                this.paraName[1] = "beta";
                this.paraName[2] = "min";
                this.paraName[3] = "max";
                if (this.scaleFlag) {
                    this.paraName[4] = "y scale";
                }

                this.nonLinearPrint(var2);
                break;
            case 33:
                var2.println("Fitting to a Three Parameter Gamma distribution");
                var2.println("y = yscale*((x-mu)/beta)^(gamma-1)*exp(-(x-mu)/beta)/(beta*Gamma(gamma))");
                var2.println("Nelder and Mead Simplex used to fit the data");
                this.paraName[0] = "mu";
                this.paraName[1] = "beta";
                this.paraName[2] = "gamma";
                if (this.scaleFlag) {
                    this.paraName[3] = "y scale";
                }

                this.nonLinearPrint(var2);
                break;
            case 34:
                var2.println("Fitting to a Standard Gamma distribution");
                var2.println("y = yscale*x^(gamma-1)*exp(-x)/Gamma(gamma)");
                var2.println("Nelder and Mead Simplex used to fit the data");
                this.paraName[0] = "gamma";
                if (this.scaleFlag) {
                    this.paraName[1] = "y scale";
                }

                this.nonLinearPrint(var2);
                break;
            case 35:
                var2.println("Fitting to an Erang distribution");
                var2.println("y = yscale*lambda^k*x^(k-1)*exp(-x*lambda)/(k-1)!");
                var2.println("Nelder and Mead Simplex used to fit the data");
                this.paraName[0] = "lambda";
                if (this.scaleFlag) {
                    this.paraName[1] = "y scale";
                }

                this.nonLinearPrint(var2);
                break;
            case 36:
                var2.println("Fitting to a two parameter log-normal distribution");
                var2.println("y = (yscale/(x.sigma.sqrt(2.pi)).exp(0.5.square((log(x)-muu)/sigma))");
                var2.println("Nelder and Mead Simplex used to fit the data");
                this.paraName[0] = "mu";
                this.paraName[1] = "sigma";
                if (this.scaleFlag) {
                    this.paraName[2] = "y scale";
                }

                this.nonLinearPrint(var2);
                break;
            case 37:
                var2.println("Fitting to a three parameter log-normal distribution");
                var2.println("y = (yscale/((x-alpha).beta.sqrt(2.pi)).exp(0.5.square((log(x-alpha)/gamma)/beta))");
                var2.println("Nelder and Mead Simplex used to fit the data");
                this.paraName[0] = "alpha";
                this.paraName[1] = "beta";
                this.paraName[2] = "gamma";
                if (this.scaleFlag) {
                    this.paraName[3] = "y scale";
                }

                this.nonLinearPrint(var2);
                break;
            case 38:
                var2.println("Fitting to a Normal (Gaussian) distribution with fixed parameters");
                var2.println("y = (yscale/(sd.sqrt(2.pi)).exp(0.5.square((x-mean)/sd))");
                var2.println("Nelder and Mead Simplex used to fit the data");
                this.paraName[0] = "mean";
                this.paraName[1] = "sd";
                this.paraName[2] = "y scale";
                this.nonLinearPrint(var2);
                break;
            case 39:
                var2.println("Fitting to a EC50 dose response curve (four parameter logistic)");
                var2.println("y = top + (bottom - top)/(1 + (x/EC50)^HillSlope)");
                var2.println("Nelder and Mead Simplex used to fit the data");
                this.paraName[0] = "top";
                this.paraName[1] = "bottom";
                this.paraName[2] = "EC50";
                this.paraName[3] = "Hill Slope";
                this.nonLinearPrint(var2);
                break;
            case 40:
                var2.println("Fitting to a EC50 dose response curve (four parameter logistic)");
                var2.println("y = top + (bottom - top)/(1 + (x/EC50)^HillSlope) [top and bottom fixed]");
                var2.println("bottom = " + this.bottom);
                var2.println("top =    " + this.top);
                var2.println();
                var2.println("Nelder and Mead Simplex used to fit the data");
                this.paraName[0] = "EC50";
                this.paraName[1] = "Hill Slope";
                this.nonLinearPrint(var2);
                break;
            case 41:
                var2.println("Fitting to a EC50 dose response curve - bottom constrained to be zero or positive");
                var2.println("y = top + (bottom - top)/(1 + (x/EC50)^HillSlope)");
                var2.println("Nelder and Mead Simplex used to fit the data");
                this.paraName[0] = "top";
                this.paraName[1] = "bottom";
                this.paraName[2] = "EC50";
                this.paraName[3] = "Hill Slope";
                this.nonLinearPrint(var2);
                break;
            case 42:
                var2.println("Fitting to a five parameter logistic");
                var2.println("y = top + (bottom - top)/(1 + (x/C50)^HillSlope)^asymm [top and bottom fixed]");
                var2.println("bottom = " + this.bottom);
                var2.println("top =    " + this.top);
                var2.println();
                var2.println("Nelder and Mead Simplex used to fit the data");
                this.paraName[0] = "C50";
                this.paraName[1] = "HillSlope";
                this.paraName[2] = "asymm";
                this.nonLinearPrint(var2);
                break;
            case 43:
                var2.println("Fitting to an exponential");
                var2.println("y = yscale.exp(A.x)");
                var2.println("Nelder and Mead Simplex used to fit the data");
                this.paraName[0] = "A";
                if (this.scaleFlag) {
                    this.paraName[1] = "y scale";
                }

                this.nonLinearPrint(var2);
                break;
            case 44:
                var2.println("Fitting to multiple exponentials");
                var2.println("y = Sum[Ai.exp(Bi.x)], i=1 to n");
                var2.println("Nelder and Mead Simplex used to fit the data");
                var5 = 1;

                for(var6 = 0; var6 < this.nParam; var6 += 2) {
                    this.paraName[var6] = "A[" + var5 + "]";
                    this.paraName[var6 + 1] = "B[" + var5++ + "]";
                }

                this.nonLinearPrint(var2);
                break;
            case 45:
                var2.println("Fitting to one minus an exponential");
                var2.println("y = A(1 - exp(B.x)");
                var2.println("Nelder and Mead Simplex used to fit the data");
                this.paraName[0] = "A";
                this.paraName[1] = "B";
                this.nonLinearPrint(var2);
                break;
            case 46:
                var2.println("Fitting to a constant");
                var2.println("y = a");
                var2.println("Stat weighted mean used to fit the data");
                this.paraName[0] = "a";
                this.linearPrint(var2);
                break;
            case 47:
                var2.println("Linear Regression with fixed intercept");
                var2.println("y = fixed intercept + c[0]*x1 + c[1]*x2 +c[2]*x3 + . . .     ");

                for(var6 = 0; var6 < this.nParam; ++var6) {
                    this.paraName[var6] = "c[" + var6 + "]";
                }

                this.linearPrint(var2);
                break;
            case 48:
                var2.println("Polynomial (with degree = " + this.nParam + ") and fixed intercept, Fitting: Linear Regression");
                var2.println("y = fixed intercept + c[0]*x + c[1]*x^2 +c[2]*x^3 + . . .");

                for(var6 = 0; var6 < this.nParam; ++var6) {
                    this.paraName[var6] = "c[" + var6 + "]";
                }

                this.linearPrint(var2);
                break;
            case 49:
                var2.println("Fitting multiple Gaussian distributions");
                var2.println("y = Sum(A[i]/(sd[i].sqrt(2.pi)).exp(0.5.square((x-mean[i])/sd[i])) = yscale.Sum(f[i]/(sd[i].sqrt(2.pi)).exp(0.5.square((x-mean[i])/sd[i]))");
                var2.println("Nelder and Mead Simplex used to fit the data");

                for(var6 = 0; var6 < this.nGaussians; ++var6) {
                    this.paraName[3 * var6] = "mean[" + var6 + "]";
                    this.paraName[3 * var6 + 1] = "sd[" + var6 + "]";
                    this.paraName[3 * var6 + 2] = "A[" + var6 + "]";
                }

                if (this.scaleFlag) {
                    this.paraName[3 * this.nGaussians] = "y scale";
                }

                this.nonLinearPrint(var2);
                break;
            case 50:
                var2.println("Fitting to a non-integer polynomial");
                var2.println("y = c[0] + c[1]*x + c[2]*x^c[3]");

                for(var6 = 0; var6 < this.nParam; ++var6) {
                    this.paraName[var6] = "c[" + var6 + "]";
                }

                this.nonLinearPrint(var2);
                break;
            case 51:
                var2.println("Five parameter logistic function");
                var2.println("y = top + (bottom - top)/((1 + (x/y50)^HillSlope)^asymm");
                var2.println("Nelder and Mead Simplex used to fit the data");
                this.paraName[0] = "top";
                this.paraName[1] = "bottom";
                this.paraName[2] = "y50";
                this.paraName[3] = "HillSlope";
                this.paraName[4] = "asymm";
                this.nonLinearPrint(var2);
                break;
            case 52:
                var2.println("Fitting to a Shifted Rectangular Hyperbola");
                var2.println("y = A.x/(theta + x) + alpha");
                var2.println("Nelder and Mead Simplex used to fit the data");
                this.paraName[0] = "theta";
                this.paraName[1] = "alpha";
                this.paraName[2] = "A";
                this.nonLinearPrint(var2);
                break;
            default:
                throw new IllegalArgumentException("Method number (this.lastMethod) not found");
        }

        var2.close();
    }

    public void print() {
        String var1 = null;
        if (this.bestPolyFlag) {
            var1 = "BestPolynomialOutput.txt";
        } else {
            var1 = "RegressOutput.txt";
        }

        this.print(var1);
    }

    protected void linearPrint(FileOutput var1) {
        if (this.legendCheck) {
            var1.println();
            var1.println("x1 = " + this.xLegend);
            var1.println("y  = " + this.yLegend);
        }

        var1.println();
        if (this.lastMethod == 47) {
            var1.println("Fixed Intercept = " + this.fixedInterceptL);
        }

        if (this.lastMethod == 48) {
            var1.println("Fixed Intercept = " + this.fixedInterceptP);
        }

        var1.printtab(" ", this.field);
        var1.printtab("Best", this.field);
        var1.printtab("Error", this.field);
        var1.printtab("Coefficient of", this.field);
        var1.printtab("t-value  ", this.field);
        var1.println("p-value");
        var1.printtab(" ", this.field);
        var1.printtab("Estimate", this.field);
        var1.printtab("        ", this.field);
        var1.printtab("variation (%)", this.field);
        var1.printtab("t ", this.field);
        var1.println("P > |t|");

        for(int var2 = 0; var2 < this.nParam; ++var2) {
            var1.printtab(this.paraName[var2], this.field);
            var1.printtab(Fmath.truncate(this.best[var2], this.prec), this.field);
            var1.printtab(Fmath.truncate(this.bestSd[var2], this.prec), this.field);
            var1.printtab(Fmath.truncate(Math.abs(this.bestSd[var2] * 100.0D / this.best[var2]), this.prec), this.field);
            var1.printtab(Fmath.truncate(this.tValues[var2], this.prec), this.field);
            var1.println(Fmath.truncate(this.pValues[var2], this.prec));
        }

        var1.println();
        byte var12 = 0;
        if (this.lastMethod < 2) {
            var12 = 1;
        }

        int var3;
        for(var3 = 0; var3 < this.nXarrays; ++var3) {
            var1.printtab("x" + String.valueOf(var3 + var12), this.field);
        }

        var1.printtab("y(expl)", this.field);
        var1.printtab("y(calc)", this.field);
        if (this.xErrorsEntered) {
            for(var3 = 0; var3 < this.nXarrays; ++var3) {
                var1.printtab("x error", this.field);
            }

            var1.printtab("y error", this.field);
        }

        var1.printtab("weight", this.field);
        var1.printtab("residual", this.field);
        var1.println("residual");

        for(var3 = 0; var3 < this.nXarrays; ++var3) {
            var1.printtab(" ", this.field);
        }

        var1.printtab(" ", this.field);
        var1.printtab(" ", this.field);
        if (this.xErrorsEntered) {
            for(var3 = 0; var3 < this.nXarrays; ++var3) {
                var1.printtab("x" + String.valueOf(var3 + var12), this.field);
            }

            var1.printtab(" ", this.field);
        }

        var1.printtab(" ", this.field);
        var1.printtab("(unweighted)", this.field);
        var1.println("(weighted)");

        int var4;
        for(var3 = 0; var3 < this.nData; ++var3) {
            for(var4 = 0; var4 < this.nXarrays; ++var4) {
                var1.printtab(Fmath.truncate(this.xData[var4][var3], this.prec), this.field);
            }

            var1.printtab(Fmath.truncate(this.yData[var3], this.prec), this.field);
            var1.printtab(Fmath.truncate(this.yCalc[var3], this.prec), this.field);
            if (this.xErrorsEntered) {
                for(var4 = 0; var4 < this.nXarrays; ++var4) {
                    var1.printtab(Fmath.truncate(this.xErrors[var4][var3], this.prec), this.field);
                }

                var1.printtab(Fmath.truncate(this.yErrors[var3], this.prec), this.field);
            }

            var1.printtab(Fmath.truncate(this.weight[var3], this.prec), this.field);
            var1.printtab(Fmath.truncate(this.residual[var3], this.prec), this.field);
            var1.println(Fmath.truncate(this.residualW[var3], this.prec));
        }

        var1.println();
        var1.println("Sum of squares " + Fmath.truncate(this.sumOfSquaresError, this.prec));
        if (this.trueFreq) {
            var1.printtab("Chi Square (Poissonian bins)");
            var1.println(Fmath.truncate(this.chiSquare, this.prec));
            var1.printtab("Reduced Chi Square (Poissonian bins)");
            var1.println(Fmath.truncate(this.reducedChiSquare, this.prec));
            var1.printtab("Chi Square (Poissonian bins) Probability");
            var1.println(Fmath.truncate(1.0D - Stat.chiSquareProb(this.chiSquare, this.nData - this.nXarrays), this.prec));
        } else if (this.weightOpt) {
            var1.printtab("Chi Square");
            var1.println(Fmath.truncate(this.chiSquare, this.prec));
            var1.printtab("Reduced Chi Square");
            var1.println(Fmath.truncate(this.reducedChiSquare, this.prec));
        }

        var1.println(" ");
        if (this.lastMethod != 46) {
            if (this.nXarrays == 1 && this.nYarrays == 1 && this.lastMethod != 47 && this.lastMethod != 48) {
                var1.println("Correlation: x - y data");
                var1.printtab(this.weightWord[this.weightFlag] + "Linear Correlation Coefficient (R)");
                var1.println(Fmath.truncate(this.xyR, this.prec));
                if (Math.abs(this.xyR) <= 1.0D) {
                    var1.printtab(this.weightWord[this.weightFlag] + "Linear Correlation Coefficient Probability");
                    var1.println(Fmath.truncate(Stat.linearCorrCoeffProb(this.xyR, this.nData - 2), this.prec));
                }
            }

            var1.println(" ");
            var1.println("Correlation: y(experimental) - y(calculated)");
            var1.printtab(this.weightWord[this.weightFlag] + "Linear Correlation Coefficient");
            var1.println(Fmath.truncate(this.yyR, this.prec));
            if (Math.abs(this.yyR) <= 1.0D) {
                var1.printtab(this.weightWord[this.weightFlag] + "Linear Correlation Coefficient Probability");
                var1.println(Fmath.truncate(Stat.linearCorrCoeffProb(this.yyR, this.nData - 2), this.prec));
            }

            var1.println();
            if (this.chiSquare != 0.0D) {
                var1.println("Correlation coefficients");
                var1.printtab(" ", this.field);

                for(var3 = 0; var3 < this.nParam; ++var3) {
                    var1.printtab(this.paraName[var3], this.field);
                }

                var1.println();

                for(var3 = 0; var3 < this.nParam; ++var3) {
                    var1.printtab(this.paraName[var3], this.field);

                    for(var4 = 0; var4 < this.nParam; ++var4) {
                        var1.printtab(Fmath.truncate(this.corrCoeff[var4][var3], this.prec), this.field);
                    }

                    var1.println();
                }
            }
        }

        var1.println(" ");
        var1.printtab("Degrees of freedom");
        var1.println(this.nData - this.nParam);
        var1.printtab("Number of data points");
        var1.println(this.nData);
        var1.printtab("Number of estimated paramaters");
        var1.println(this.nParam);
        var1.println();
        var1.printtab("Durbin-Watson d statistic");
        var1.println(Fmath.truncate(this.getDurbinWatsonD(), this.prec));
        var1.println();
        if (this.bestPolyFlag) {
            var1.println("Method bestPolynomial search history");
            var1.println("F-probability significance level: " + this.fProbSignificance + " (" + this.fProbSignificance * 100.0D + " %)");
            var1.println("Degree of best fit polynomial " + this.bestPolynomialDegree);
            var1.println(" ");
            var1.print("Polynomial degree", 2 * this.field);
            var1.print("chi square", 2 * this.field);
            var1.print("F-ratio", this.field);
            var1.print("F-probability", this.field + 2);
            var1.println("F-value at the");
            var1.print("comparison", 2 * this.field);
            var1.print("comparison", 2 * this.field);
            var1.print("   ", this.field);
            var1.print("   ", this.field + 2);
            var1.println("significance level");
            var3 = (Integer)this.bestPolyArray.get(1);
            int[] var13 = (int[])((int[])this.bestPolyArray.get(2));
            int[] var5 = (int[])((int[])this.bestPolyArray.get(3));
            double[] var6 = (double[])((double[])this.bestPolyArray.get(4));
            double[] var7 = (double[])((double[])this.bestPolyArray.get(5));
            double[] var8 = (double[])((double[])this.bestPolyArray.get(6));
            double[] var9 = (double[])((double[])this.bestPolyArray.get(7));
            double[] var10 = (double[])((double[])this.bestPolyArray.get(8));

            for(int var11 = 0; var11 < var3; ++var11) {
                var1.print(var13[var11], this.field);
                var1.print(var5[var11], this.field);
                var1.print(Fmath.truncate(var6[var11], this.prec), this.field);
                var1.print(Fmath.truncate(var7[var11], this.prec), this.field);
                var1.print(Fmath.truncate(var8[var11], this.prec), this.field);
                var1.print(Fmath.truncate(var9[var11], this.prec), this.field + 2);
                var1.println(Fmath.truncate(var10[var11], this.prec));
            }
        }

        var1.println();
        var1.println("Coefficient of determination,   =                   " + Fmath.truncate(this.multR, this.prec));
        var1.println("Adjusted Coefficient of determination,    =         " + Fmath.truncate(this.adjustedR, this.prec));
        var1.println("Coefficient of determination, F-ratio =             " + Fmath.truncate(this.multipleF, this.prec));
        var1.println("Coefficient of determination, F-ratio probability = " + Fmath.truncate(this.multipleFprob, this.prec));
        var1.println("Total (weighted) sum of squares  =                  " + Fmath.truncate(this.sumOfSquaresTotal, this.prec));
        var1.println("Regression (weighted) sum of squares  =             " + Fmath.truncate(this.sumOfSquaresRegrn, this.prec));
        var1.println("Error (weighted) sum of squares  =                  " + Fmath.truncate(this.chiSquare, this.prec));
        var1.println();
        var1.println("End of file");
        var1.close();
    }

    protected void nonLinearPrint(FileOutput var1) {
        int var2;
        int var6;
        int var7;
        for(var2 = 0; var2 < this.nParam; ++var2) {
            if (!this.constraintString[var2].equals("none")) {
                boolean var3 = true;
                String var4 = this.constraintString[var2];
                String var5 = "";

                while(var3) {
                    var6 = var4.indexOf("p[");
                    if (var6 == -1) {
                        var5 = var5 + var4;
                        var3 = false;
                    } else {
                        var7 = var4.indexOf("]");
                        int var8 = Integer.parseInt(var4.substring(var6 + 2, var7));
                        var5 = var5 + var4.substring(0, var6) + this.paraName[var8];
                        var4 = var4.substring(var7 + 1);
                    }
                }

                this.constraintString[var2] = var5;
            }
        }

        if (this.userSupplied) {
            var1.println();
            var1.println("Initial estimates were supplied by the user");
        } else {
            var1.println("Initial estimates were calculated internally");
        }

        switch(this.scaleOpt) {
            case 1:
                var1.println();
                var1.println("Initial estimates were scaled to unity within the regression");
                break;
            case 2:
                var1.println();
                var1.println("Initial estimates were scaled with user supplied scaling factors within the regression");
        }

        if (this.legendCheck) {
            var1.println();
            var1.println("x1 = " + this.xLegend);
            var1.println("y  = " + this.yLegend);
        }

        var1.println();
        if (!this.nlrStatus) {
            var1.println("Convergence criterion was not satisfied");
            var1.println("The following results are, or are derived from, the current estimates on exiting the regression method");
            var1.println();
        }

        var1.println("Estimated parameters");
        var1.println("The statistics are obtained assuming that the model behaves as a linear model about the minimum.");
        if (!this.analyticalDerivative) {
            var1.println("The Hessian matrix is calculated as the numerically derived second derivatives of chi square with respect to all pairs of parameters.");
        }

        if (this.zeroCheck) {
            var1.println("The best estimate/s equal to zero were replaced by the step size in the numerical differentiation!!!");
        }

        var1.println("Consequentlty treat the statistics with great caution");
        if (!this.posVarFlag) {
            var1.println("Covariance matrix contains at least one negative diagonal element");
            var1.println(" - all variances are dubious");
            var1.println(" - may not be at a minimum or the model may be so non-linear that the linear approximation in calculating the statisics is invalid");
        }

        if (!this.invertFlag) {
            var1.println("Hessian matrix is singular");
            var1.println(" - variances cannot be calculated");
            var1.println(" - may not be at a minimum  or the model may be so non-linear that the linear approximation in calculating the statisics is invalid");
        }

        var1.println(" ");
        if (!this.scaleFlag) {
            var1.println("The ordinate scaling factor [yscale, Ao] has been set equal to " + this.yScaleFactor);
            var1.println(" ");
        }

        if (this.lastMethod == 35) {
            var1.println("The integer rate parameter, k, was varied in unit steps to obtain a minimum sum of squares");
            var1.println("This value of k was " + this.kayValue);
            var1.println(" ");
        }

        var1.printtab(" ", this.field);
        if (this.invertFlag) {
            var1.printtab("Best", this.field);
            var1.printtab("Estimate of", this.field);
            var1.printtab("Coefficient", this.field);
            var1.printtab("t-value", this.field);
            var1.println("p-value");
        } else {
            var1.println("Best");
        }

        if (this.invertFlag) {
            var1.printtab(" ", this.field);
            var1.printtab("estimate", this.field);
            var1.printtab("the error", this.field);
            var1.printtab("of", this.field);
            var1.printtab("t", this.field);
            var1.println("P > |t|");
        } else {
            var1.printtab(" ", this.field);
            var1.println("estimate");
        }

        if (this.invertFlag) {
            var1.printtab(" ", this.field);
            var1.printtab(" ", this.field);
            var1.printtab(" ", this.field);
            var1.println("variation (%)");
        } else {
            var1.println("   ");
        }

        byte var9;
        int var10;
        int var11;
        if (this.lastMethod == 38) {
            var9 = 3;
            var10 = 0;

            for(var11 = 0; var11 < var9; ++var11) {
                var1.printtab(this.paraName[var11], this.field);
                if (this.fixed[var11]) {
                    var1.printtab(this.values[var11]);
                    var1.println(" fixed parameter");
                } else {
                    if (this.invertFlag) {
                        var1.printtab(Fmath.truncate(this.best[var10], this.prec), this.field);
                        var1.printtab(Fmath.truncate(this.bestSd[var10], this.prec), this.field);
                        var1.printtab(Fmath.truncate(Math.abs(this.bestSd[var10] * 100.0D / this.best[var10]), this.prec), this.field);
                        var1.printtab(Fmath.truncate(this.tValues[var10], this.prec), this.field);
                        var1.println(Fmath.truncate(this.pValues[var10], this.prec));
                    } else {
                        var1.println(Fmath.truncate(this.best[var10], this.prec));
                    }

                    ++var10;
                }
            }
        } else {
            for(var2 = 0; var2 < this.nParam; ++var2) {
                if (this.invertFlag) {
                    var1.printtab(this.paraName[var2], this.field);
                    var1.printtab(Fmath.truncate(this.best[var2], this.prec), this.field);
                    var1.printtab(Fmath.truncate(this.bestSd[var2], this.prec), this.field);
                    var1.printtab(Fmath.truncate(Math.abs(this.bestSd[var2] * 100.0D / this.best[var2]), this.prec), this.field);
                    var1.printtab(Fmath.truncate(this.tValues[var2], this.prec), this.field);
                    var1.println(Fmath.truncate(this.pValues[var2], this.prec));
                } else {
                    var1.printtab(this.paraName[var2], this.field);
                    var1.println(Fmath.truncate(this.best[var2], this.prec));
                }
            }
        }

        var1.println();
        var1.printtab(" ", this.field);
        var1.printtab("Best", this.field);
        var1.printtab("Pre-min", this.field);
        var1.printtab("Post-min", this.field);
        var1.printtab("Initial", this.field);
        var1.printtab("Fractional", this.field);
        var1.printtab("Scaling", this.field);
        var1.println("Constraint");
        var1.printtab(" ", this.field);
        var1.printtab("estimate", this.field);
        var1.printtab("gradient", this.field);
        var1.printtab("gradient", this.field);
        var1.printtab("estimate", this.field);
        var1.printtab("step", this.field);
        var1.println("factor");
        if (this.lastMethod == 38) {
            var9 = 3;
            var10 = 0;

            for(var11 = 0; var11 < var9; ++var11) {
                var1.printtab(this.paraName[var11], this.field);
                if (this.fixed[var11]) {
                    var1.printtab(this.values[var11]);
                    var1.println(" fixed parameter");
                } else {
                    var1.printtab(Fmath.truncate(this.best[var10], this.prec), this.field);
                    var1.printtab(Fmath.truncate(this.grad[var10][0], this.prec), this.field);
                    var1.printtab(Fmath.truncate(this.grad[var10][1], this.prec), this.field);
                    var1.printtab(Fmath.truncate(this.startH[var10], this.prec), this.field);
                    var1.printtab(Fmath.truncate(this.stepH[var10], this.prec), this.field);
                    var1.printtab(Fmath.truncate(this.scale[var10], this.prec), this.field);
                    var1.println(this.constraintString[var10]);
                    ++var10;
                }
            }
        } else {
            for(var2 = 0; var2 < this.nParam; ++var2) {
                var1.printtab(this.paraName[var2], this.field);
                var1.printtab(Fmath.truncate(this.best[var2], this.prec), this.field);
                var1.printtab(Fmath.truncate(this.grad[var2][0], this.prec), this.field);
                var1.printtab(Fmath.truncate(this.grad[var2][1], this.prec), this.field);
                var1.printtab(Fmath.truncate(this.startH[var2], this.prec), this.field);
                var1.printtab(Fmath.truncate(this.stepH[var2], this.prec), this.field);
                var1.printtab(Fmath.truncate(this.scale[var2], this.prec), this.field);
                var1.println(this.constraintString[var2]);
            }
        }

        var1.println();
        ErrorProp var12 = null;
        ErrorProp var13 = null;
        if (this.scaleFlag) {
            switch(this.lastMethod) {
                case 4:
                    ErrorProp var14 = new ErrorProp(this.best[1], this.bestSd[1]);
                    var13 = new ErrorProp(this.best[2] / Math.sqrt(6.283185307179586D), this.bestSd[2] / Math.sqrt(6.283185307179586D));
                    var12 = var13.over(var14);
                    var1.printsp("Calculated estimate of the peak value = ");
                    var1.println(ErrorProp.truncate(var12, this.prec));
                    break;
                case 5:
                    ErrorProp var15 = new ErrorProp(this.best[1], this.bestSd[1]);
                    var13 = new ErrorProp(2.0D * this.best[2] / 3.141592653589793D, 2.0D * this.bestSd[2] / 3.141592653589793D);
                    var12 = var13.over(var15);
                    var1.printsp("Calculated estimate of the peak value = ");
                    var1.println(ErrorProp.truncate(var12, this.prec));
            }
        }

        if (this.lastMethod == 25) {
            var1.printsp("Calculated estimate of the maximum gradient = ");
            if (this.scaleFlag) {
                var1.println(Fmath.truncate(this.best[0] * this.best[2] / 4.0D, this.prec));
            } else {
                var1.println(Fmath.truncate(this.best[0] * this.yScaleFactor / 4.0D, this.prec));
            }
        }

        if (this.lastMethod == 28) {
            var1.printsp("Calculated estimate of the maximum gradient = ");
            if (this.scaleFlag) {
                var1.println(Fmath.truncate(this.best[1] * this.best[2] / (4.0D * this.best[0]), this.prec));
            } else {
                var1.println(Fmath.truncate(this.best[1] * this.yScaleFactor / (4.0D * this.best[0]), this.prec));
            }

            var1.printsp("Calculated estimate of the Ka, i.e. theta raised to the power n = ");
            var1.println(Fmath.truncate(Math.pow(this.best[0], this.best[1]), this.prec));
        }

        var1.println();
        if (this.lastMethod == 49) {
            var1.println("A[i] values converted to fractional contributions, f[i], and a scaling factor, yscale");
            var1.printtab(" ", this.field);
            if (this.invertFlag) {
                var1.printtab("Best", this.field);
                var1.printtab("Estimate of", this.field);
                var1.printtab("Coefficient", this.field);
                var1.printtab("t-value", this.field);
                var1.println("p-value");
            } else {
                var1.println("Best");
            }

            if (this.invertFlag) {
                var1.printtab(" ", this.field);
                var1.printtab("estimate", this.field);
                var1.printtab("the error", this.field);
                var1.printtab("of", this.field);
                var1.printtab("t", this.field);
                var1.println("P > |t|");
            } else {
                var1.printtab(" ", this.field);
                var1.println("estimate");
            }

            if (this.invertFlag) {
                var1.printtab(" ", this.field);
                var1.printtab(" ", this.field);
                var1.printtab(" ", this.field);
                var1.println("variation (%)");
            } else {
                var1.println("   ");
            }

            for(var11 = 0; var11 < this.nGaussians; ++var11) {
                if (this.invertFlag) {
                    var1.printtab("f[" + var11 + "]", this.field);
                    var1.printtab(Fmath.truncate(this.multGaussFract[var11], this.prec), this.field);
                    var1.printtab(Fmath.truncate(this.multGaussFractErrors[var11], this.prec), this.field);
                    var1.printtab(Fmath.truncate(this.multGaussCoeffVar[var11], this.prec), this.field);
                    var1.printtab(Fmath.truncate(this.multGaussTvalue[var11], this.prec), this.field);
                    var1.println(Fmath.truncate(this.multGaussPvalue[var11], this.prec));
                } else {
                    var1.printtab("f[" + var11 + "]", this.field);
                    var1.println(Fmath.truncate(this.multGaussFract[var11], this.prec));
                }
            }

            if (this.invertFlag) {
                var1.printtab("yscale", this.field);
                var1.printtab(Fmath.truncate(this.multGaussScale, this.prec), this.field);
                var1.printtab(Fmath.truncate(this.multGaussScaleError, this.prec), this.field);
                var1.printtab(Fmath.truncate(this.multGaussScaleCoeffVar, this.prec), this.field);
                var1.printtab(Fmath.truncate(this.multGaussScaleTvalue, this.prec), this.field);
                var1.println(Fmath.truncate(this.multGaussScalePvalue, this.prec));
            } else {
                var1.printtab("yscale", this.field);
                var1.println(Fmath.truncate(this.multGaussScale, this.prec));
            }
        }

        var1.println();
        var11 = 0;

        int var16;
        for(var16 = 0; var16 < this.nYarrays; ++var16) {
            if (this.multipleY) {
                var1.println("Y array " + var16);
            }

            for(var6 = 0; var6 < this.nXarrays; ++var6) {
                var1.printtab("x" + String.valueOf(var6), this.field);
            }

            var1.printtab("y(expl)", this.field);
            var1.printtab("y(calc)", this.field);
            if (this.xErrorsEntered) {
                for(var6 = 0; var6 < this.nXarrays; ++var6) {
                    var1.printtab("x errors", this.field);
                }

                var1.printtab("y errors", this.field);
            }

            var1.printtab("weight", this.field);
            var1.printtab("residual", this.field);
            var1.println("residual");

            for(var6 = 0; var6 < this.nXarrays; ++var6) {
                var1.printtab(" ", this.field);
            }

            var1.printtab(" ", this.field);
            var1.printtab(" ", this.field);
            if (this.xErrorsEntered) {
                for(var6 = 0; var6 < this.nXarrays; ++var6) {
                    var1.printtab("x" + String.valueOf(var6), this.field);
                }

                var1.printtab("  ", this.field);
            }

            var1.printtab(" ", this.field);
            var1.printtab("(unweighted)", this.field);
            var1.println("(weighted)");

            for(var6 = 0; var6 < this.nData0; ++var6) {
                for(var7 = 0; var7 < this.nXarrays; ++var7) {
                    var1.printtab(Fmath.truncate(this.xData[var7][var11], this.prec), this.field);
                }

                var1.printtab(Fmath.truncate(this.yData[var11], this.prec), this.field);
                var1.printtab(Fmath.truncate(this.yCalc[var11], this.prec), this.field);
                if (this.xErrorsEntered) {
                    for(var7 = 0; var7 < this.nXarrays; ++var7) {
                        var1.printtab(Fmath.truncate(this.xErrors[var7][var11], this.prec), this.field);
                    }

                    var1.printtab(Fmath.truncate(this.yErrors[var11], this.prec), this.field);
                }

                var1.printtab(Fmath.truncate(this.weight[var11], this.prec), this.field);
                var1.printtab(Fmath.truncate(this.residual[var11], this.prec), this.field);
                var1.println(Fmath.truncate(this.residualW[var11], this.prec));
                ++var11;
            }

            var1.println();
        }

        var1.printtab("Sum of squares of the unweighted residuals");
        var1.println(Fmath.truncate(this.sumOfSquaresError, this.prec));
        if (this.trueFreq) {
            var1.printtab("Chi Square (Poissonian bins)");
            var1.println(Fmath.truncate(this.chiSquare, this.prec));
            var1.printtab("Reduced Chi Square (Poissonian bins)");
            var1.println(Fmath.truncate(this.reducedChiSquare, this.prec));
            var1.printtab("Chi Square (Poissonian bins) Probability");
            var1.println(Fmath.truncate(1.0D - Stat.chiSquareProb(this.reducedChiSquare, this.degreesOfFreedom), this.prec));
        } else if (this.weightOpt) {
            var1.printtab("Chi Square");
            var1.println(Fmath.truncate(this.chiSquare, this.prec));
            var1.printtab("Reduced Chi Square");
            var1.println(Fmath.truncate(this.reducedChiSquare, this.prec));
        }

        var1.println(" ");
        if (this.nXarrays == 1 && this.nYarrays == 1) {
            var1.println("Correlation: x - y data");
            var1.printtab(this.weightWord[this.weightFlag] + "Linear Correlation Coefficient (R)");
            var1.println(Fmath.truncate(this.xyR, this.prec));
            if (Math.abs(this.xyR) <= 1.0D) {
                var1.printtab(this.weightWord[this.weightFlag] + "Linear Correlation Coefficient Probability");
                var1.println(Fmath.truncate(Stat.linearCorrCoeffProb(this.xyR, this.nData - 2), this.prec));
            }
        }

        var1.println(" ");
        var1.println("Correlation: y(experimental) - y(calculated)");
        var1.printtab(this.weightWord[this.weightFlag] + "Linear Correlation Coefficient");
        var1.println(Fmath.truncate(this.yyR, this.prec));
        if (Math.abs(this.yyR) <= 1.0D) {
            var1.printtab(this.weightWord[this.weightFlag] + "Linear Correlation Coefficient Probability");
            var1.println(Fmath.truncate(Stat.linearCorrCoeffProb(this.yyR, this.nData - 2), this.prec));
        }

        var1.println(" ");
        var1.printtab("Degrees of freedom");
        var1.println(this.degreesOfFreedom);
        var1.printtab("Number of data points");
        var1.println(this.nData);
        var1.printtab("Number of estimated paramaters");
        var1.println(this.nParam);
        var1.println();
        if (this.posVarFlag && this.invertFlag && this.chiSquare != 0.0D) {
            var1.println("Parameter - parameter correlation coefficients");
            var1.printtab(" ", this.field);

            for(var16 = 0; var16 < this.nParam; ++var16) {
                var1.printtab(this.paraName[var16], this.field);
            }

            var1.println();

            for(var16 = 0; var16 < this.nParam; ++var16) {
                var1.printtab(this.paraName[var16], this.field);

                for(var6 = 0; var6 < this.nParam; ++var6) {
                    var1.printtab(Fmath.truncate(this.corrCoeff[var6][var16], this.prec), this.field);
                }

                var1.println();
            }

            var1.println();
        }

        var1.println();
        var1.println("Coefficient of determination, R =                   " + Fmath.truncate(this.multR, this.prec));
        var1.println("Adjusted Coefficient of determination, R' =         " + Fmath.truncate(this.adjustedR, this.prec));
        var1.println("Coefficient of determination, F-ratio =             " + Fmath.truncate(this.multipleF, this.prec));
        var1.println("Coefficient of determination, F-ratio probability = " + Fmath.truncate(this.multipleFprob, this.prec));
        var1.println("Total (weighted) sum of squares  =                  " + Fmath.truncate(this.sumOfSquaresTotal, this.prec));
        var1.println("Regression (weighted) sum of squares  =             " + Fmath.truncate(this.sumOfSquaresRegrn, this.prec));
        var1.println("Error (weighted) sum of squares  =                  " + Fmath.truncate(this.chiSquare, this.prec));
        var1.println();
        var1.println();
        var1.printtab("Durbin-Watson d statistic");
        var1.println(Fmath.truncate(this.getDurbinWatsonD(), this.prec));
        var1.println();
        var1.printtab("Number of iterations taken");
        var1.println(this.nIter);
        var1.printtab("Maximum number of iterations allowed");
        var1.println(this.nMax);
        var1.printtab("Number of restarts taken");
        var1.println(this.kRestart);
        var1.printtab("Maximum number of restarts allowed");
        var1.println(this.konvge);
        var1.printtab("Standard deviation of the simplex at the minimum");
        var1.println(Fmath.truncate(this.simplexSd, this.prec));
        var1.printtab("Convergence tolerance");
        var1.println(this.fTol);
        switch(this.minTest) {
            case 0:
                var1.println("simplex sd < the tolerance times the mean of the absolute values of the y values");
                break;
            case 1:
                var1.println("simplex sd < the tolerance");
                break;
            case 2:
                var1.println("simplex sd < the tolerance times the square root(sum of squares/degrees of freedom");
        }

        if (!this.analyticalDerivative) {
            var1.println("Step used in numerical differentiation to obtain Hessian matrix");
            var1.println("d(parameter) = parameter*" + this.delta);
        }

        var1.println();
        var1.println("End of file");
        var1.close();
    }

    public void plotYY(String var1) {
        this.graphTitle = var1;
        byte var2 = 2;
        int var3 = this.nData0;
        double[][] var4 = PlotGraph.data(var2, var3);
        int var5 = 0;

        for(int var6 = 0; var6 < this.nYarrays; ++var6) {
            for(int var7 = 0; var7 < this.nData0; ++var7) {
                var4[0][var7] = this.yData[var5];
                var4[1][var7] = this.yCalc[var5];
                ++var5;
            }

            String var14 = this.setGandPtitle(this.graphTitle);
            if (this.multipleY) {
                var14 = var14 + "y array " + var6;
            }

            String var8 = "Calculated versus experimental y values";
            Regression var9 = new Regression(this.yData, this.yCalc, this.weight);
            var9.linear();
            double[] var10 = var9.getCoeff();
            var4[2][0] = Fmath.minimum(this.yData);
            var4[3][0] = var10[0] + var10[1] * var4[2][0];
            var4[2][1] = Fmath.maximum(this.yData);
            var4[3][1] = var10[0] + var10[1] * var4[2][1];
            PlotGraph var11 = new PlotGraph(var4);
            if (this.plotWindowCloseChoice) {
                var11.setCloseChoice(2);
            } else {
                var11.setCloseChoice(1);
            }

            var11.setGraphTitle(var14);
            var11.setGraphTitle2(var8);
            var11.setXaxisLegend("Experimental y value");
            var11.setYaxisLegend("Calculated y value");
            int[] var12 = new int[]{1, 0};
            var11.setPoint(var12);
            int[] var13 = new int[]{0, 3};
            var11.setLine(var13);
            var11.plot();
        }

    }

    protected String setGandPtitle(String var1) {
        String var2 = "";
        switch(this.lastMethod) {
            case 0:
                var2 = "Linear regression (with intercept): " + var1;
                break;
            case 1:
                var2 = "Linear(polynomial with degree = " + (this.nParam - 1) + ") regression: " + var1;
                break;
            case 2:
                var2 = "General linear regression: " + var1;
                break;
            case 3:
                var2 = "Non-linear (simplex) regression: " + var1;
                break;
            case 4:
                var2 = "Fit to a Gaussian distribution: " + var1;
                break;
            case 5:
                var2 = "Fit to a Lorentzian distribution: " + var1;
                break;
            case 6:
                var2 = "Fit to a Poisson distribution: " + var1;
                break;
            case 7:
                var2 = "Fit to a Two Parameter Minimum Order Statistic Gumbel distribution: " + var1;
                break;
            case 8:
                var2 = "Fit to a two Parameter Maximum Order Statistic Gumbel distribution: " + var1;
                break;
            case 9:
                var2 = "Fit to a One Parameter Minimum Order Statistic Gumbel distribution: " + var1;
                break;
            case 10:
                var2 = "Fit to a One Parameter Maximum Order Statistic Gumbel distribution: " + var1;
                break;
            case 11:
                var2 = "Fit to a Standard Minimum Order Statistic Gumbel distribution: " + var1;
                break;
            case 12:
                var2 = "Fit to a Standard Maximum Order Statistic Gumbel distribution: " + var1;
                break;
            case 13:
                var2 = "Fit to a Three Parameter Frechet distribution: " + var1;
                break;
            case 14:
                var2 = "Fit to a Two Parameter Frechet distribution: " + var1;
                break;
            case 15:
                var2 = "Fit to a Standard Frechet distribution: " + var1;
                break;
            case 16:
                var2 = "Fit to a Three Parameter Weibull distribution: " + var1;
                break;
            case 17:
                var2 = "Fit to a Two Parameter Weibull distribution: " + var1;
                break;
            case 18:
                var2 = "Fit to a Standard Weibull distribution: " + var1;
                break;
            case 19:
                var2 = "Fit to a Two Parameter Exponential distribution: " + var1;
                break;
            case 20:
                var2 = "Fit to a One Parameter Exponential distribution: " + var1;
                break;
            case 21:
                var2 = "Fit to a Standard exponential distribution: " + var1;
                break;
            case 22:
                var2 = "Fit to a Rayleigh distribution: " + var1;
                break;
            case 23:
                var2 = "Fit to a Two Parameter Pareto distribution: " + var1;
                break;
            case 24:
                var2 = "Fit to a One Parameter Pareto distribution: " + var1;
                break;
            case 25:
                var2 = "Fit to a Sigmoid Threshold Function: " + var1;
                break;
            case 26:
                var2 = "Fit to a Rectangular Hyperbola: " + var1;
                break;
            case 27:
                var2 = "Fit to a Scaled Heaviside Step Function: " + var1;
                break;
            case 28:
                var2 = "Fit to a Hill/Sips Sigmoid: " + var1;
                break;
            case 29:
                var2 = "Fit to a Shifted Pareto distribution: " + var1;
                break;
            case 30:
                var2 = "Fit to a Logistic distribution: " + var1;
                break;
            case 31:
                var2 = "Fit to a Beta distribution - interval [0, 1]: " + var1;
                break;
            case 32:
                var2 = "Fit to a Beta distribution - interval [min, max]: " + var1;
                break;
            case 33:
                var2 = "Fit to a Three Parameter Gamma distribution]: " + var1;
                break;
            case 34:
                var2 = "Fit to a Standard Gamma distribution]: " + var1;
                break;
            case 35:
                var2 = "Fit to an Erlang distribution]: " + var1;
                break;
            case 36:
                var2 = "Fit to an two parameter log-normal distribution]: " + var1;
                break;
            case 37:
                var2 = "Fit to an three parameter log-normal distribution]: " + var1;
                break;
            case 38:
                var2 = "Fit to a Gaussian distribution with fixed parameters: " + var1;
                break;
            case 39:
                var2 = "Fit to a EC50 dose response curve (four parameter logistic): " + var1;
                break;
            case 40:
                var2 = "Fit to a EC50 dose response curve (top and bottom fixed): " + var1;
                break;
            case 41:
                var2 = "Fit to a EC50 dose response curve - bottom constrained [>= 0]: " + var1;
                break;
            case 42:
                var2 = "Fitting to a five parameter logistic function (top and bottom fixed)";
                break;
            case 43:
                var2 = "Fit to an exponential yscale.exp(A.x): " + var1;
                break;
            case 44:
                var2 = "Fit to multiple exponentials sum[Ai.exp(Bi.x)]: " + var1;
                break;
            case 45:
                var2 = "Fit to an exponential A.(1 - exp(B.x): " + var1;
                break;
            case 46:
                var2 = "Fit to a constant a: " + var1;
                break;
            case 47:
                var2 = "Linear regression (with fixed intercept): " + var1;
                break;
            case 48:
                var2 = "Linear(polynomial with degree = " + (this.nParam - 1) + " and fixed intercept) regression: " + var1;
                break;
            case 49:
                var2 = "Fitting multiple Gaussian distributions";
                break;
            case 50:
                var2 = "Fitting to a non-integer polynomial";
                break;
            case 51:
                var2 = "Fitting to a five parameter logistic function";
                break;
            case 52:
                var2 = "Fit to a shifted Rectangular Hyperbola: " + var1;
                break;
            default:
                var2 = " " + var1;
        }

        return var2;
    }

    public void plotYY() {
        this.plotYY(this.graphTitle);
    }

    protected int plotXY(String var1) {
        this.graphTitle = var1;
        boolean var2 = false;
        if (!this.linNonLin && this.nParam > 0) {
            System.out.println("You attempted to use Regression.plotXY() for a non-linear regression without providing the function reference in the plotXY argument list");
            System.out.println("No plot attempted");
            byte var4 = -1;
            return var4;
        } else {
            int var3 = this.plotXYlinear(var1);
            return var3;
        }
    }

    public int plotXY() {
        int var1 = -2;
        if (this.multipleY) {
            System.out.println("You attempted to use Regression.plotXY() for a multiply dimensioned y array");
            System.out.println("No plot attempted");
        } else {
            var1 = this.plotXY(this.graphTitle);
        }

        return var1;
    }

    protected int plotXY(Object var1, String var2) {
        int var3 = -2;
        if (this.multipleY) {
            System.out.println("You attempted to use Regression.plotXY() for a multiply dimensioned y array");
            System.out.println("No plot attempted");
        } else {
            var3 = this.plotXYnonlinear(var1, var2);
        }

        return var3;
    }

    protected int plotXY(RegressionFunction var1, String var2) {
        return this.plotXY((Object)var1, var2);
    }

    protected int plotXY(RegressionFunction2 var1, String var2) {
        return this.plotXY((Object)var1, var2);
    }

    protected int plotXY(RegressionFunction3 var1, String var2) {
        return this.plotXY((Object)var1, var2);
    }

    protected int plotXY(Object var1) {
        int var2 = -2;
        if (this.multipleY) {
            System.out.println("You attempted to use Regression.plotXY() for a multiply dimensioned y array");
            System.out.println("No plot attempted");
        } else {
            var2 = this.plotXYnonlinear(var1, this.graphTitle);
        }

        return var2;
    }

    protected int plotXY(RegressionFunction var1) {
        return this.plotXY((Object)var1);
    }

    protected int plotXY(RegressionFunction2 var1) {
        return this.plotXY((Object)var1);
    }

    protected int plotXY(RegressionFunction3 var1) {
        return this.plotXY((Object)var1);
    }

    public void addLegends() {
        int var1 = JOptionPane.showConfirmDialog((Component)null, "Do you wish to add your own legends to the x and y axes", "Axis Legends", 0, 3);
        if (var1 == 0) {
            this.xLegend = JOptionPane.showInputDialog("Type the legend for the abscissae (x-axis) [first data set]");
            this.yLegend = JOptionPane.showInputDialog("Type the legend for the ordinates (y-axis) [second data set]");
            this.legendCheck = true;
        }

    }

    protected int plotXYlinear(String var1) {
        this.graphTitle = var1;
        byte var2 = 0;
        if (this.nXarrays > 1) {
            System.out.println("You attempted to use Regression.plotXY() for a multiple regression");
            System.out.println("No plot attempted");
            var2 = -2;
            return var2;
        } else {
            byte var3 = 2;
            int var4 = 200;
            if (var4 < this.nData0) {
                var4 = this.nData0;
            }

            if (this.lastMethod == 11 || this.lastMethod == 12 || this.lastMethod == 21) {
                var4 = this.nData0;
            }

            double[][] var5 = PlotGraph.data(var3, var4);
            double var6 = Fmath.minimum(this.xData[0]);
            double var8 = Fmath.maximum(this.xData[0]);
            double var10 = (var8 - var6) / (double)(var4 - 1);
            String var12 = " ";
            String var13 = " ";

            int var14;
            for(var14 = 0; var14 < this.nData0; ++var14) {
                var5[0][var14] = this.xData[0][var14];
                var5[1][var14] = this.yData[var14];
            }

            var5[2][0] = var6;

            for(var14 = 1; var14 < var4; ++var14) {
                var5[2][var14] = var5[2][var14 - 1] + var10;
            }

            if (this.nParam == 0) {
                label198:
                switch(this.lastMethod) {
                    case 11:
                        var12 = "No regression: Minimum Order Statistic Standard Gumbel (y = exp(x)exp(-exp(x))): " + this.graphTitle;
                        var13 = " points - experimental values;   line - theoretical curve;   no parameters to be estimated";
                        if (this.weightOpt) {
                            var13 = var13 + ";   error bars - weighting factors";
                        }

                        var14 = 0;

                        while(true) {
                            if (var14 >= var4) {
                                break label198;
                            }

                            var5[3][var14] = this.yCalc[var14];
                            ++var14;
                        }
                    case 12:
                        var12 = "No regression:  Maximum Order Statistic Standard Gumbel (y = exp(-x)exp(-exp(-x))): " + this.graphTitle;
                        var13 = " points - experimental values;   line - theoretical curve;   no parameters to be estimated";
                        if (this.weightOpt) {
                            var13 = var13 + ";   error bars - weighting factors";
                        }

                        var14 = 0;

                        while(true) {
                            if (var14 >= var4) {
                                break label198;
                            }

                            var5[3][var14] = this.yCalc[var14];
                            ++var14;
                        }
                    case 21:
                        var12 = "No regression:  Standard Exponential (y = exp(-x)): " + this.graphTitle;
                        var13 = " points - experimental values;   line - theoretical curve;   no parameters to be estimated";
                        if (this.weightOpt) {
                            var13 = var13 + ";   error bars - weighting factors";
                        }

                        for(var14 = 0; var14 < var4; ++var14) {
                            var5[3][var14] = this.yCalc[var14];
                        }
                }
            } else {
                double var15;
                int var17;
                label182:
                switch(this.lastMethod) {
                    case 0:
                        var12 = "Linear regression  (y = a + b.x): " + this.graphTitle;
                        var13 = " points - experimental values;   line - best fit curve";
                        if (this.weightOpt) {
                            var13 = var13 + ";   error bars - weighting factors";
                        }

                        var14 = 0;

                        while(true) {
                            if (var14 >= var4) {
                                break label182;
                            }

                            var5[3][var14] = this.best[0] + this.best[1] * var5[2][var14];
                            ++var14;
                        }
                    case 1:
                        var12 = "Linear (polynomial with degree = " + (this.nParam - 1) + ") regression: " + this.graphTitle;
                        var13 = " points - experimental values;   line - best fit curve";
                        if (this.weightOpt) {
                            var13 = var13 + ";   error bars - weighting factors";
                        }

                        var14 = 0;

                        while(true) {
                            if (var14 >= var4) {
                                break label182;
                            }

                            var15 = this.best[0];

                            for(var17 = 1; var17 < this.nParam; ++var17) {
                                var15 += this.best[var17] * Math.pow(var5[2][var14], (double)var17);
                            }

                            var5[3][var14] = var15;
                            ++var14;
                        }
                    case 2:
                        var12 = "Linear regression  (y = a.x): " + this.graphTitle;
                        var13 = " points - experimental values;   line - best fit curve";
                        if (this.nXarrays == 1) {
                            if (this.weightOpt) {
                                var13 = var13 + ";   error bars - weighting factors";
                            }

                            var14 = 0;

                            while(true) {
                                if (var14 >= var4) {
                                    break label182;
                                }

                                var5[3][var14] = this.best[0] * var5[2][var14];
                                ++var14;
                            }
                        } else {
                            System.out.println("Regression.plotXY(linear): lastMethod, " + this.lastMethod + ",cannot be plotted in two dimensions");
                            System.out.println("No plot attempted");
                            var2 = -1;
                            break;
                        }
                    case 11:
                        var12 = "Linear regression: Minimum Order Statistic Standard Gumbel (y = a.z where z = exp(x)exp(-exp(x))): " + this.graphTitle;
                        var13 = " points - experimental values;   line - best fit curve";
                        if (this.weightOpt) {
                            var13 = var13 + ";   error bars - weighting factors";
                        }

                        var14 = 0;

                        while(true) {
                            if (var14 >= var4) {
                                break label182;
                            }

                            var5[3][var14] = this.best[0] * Math.exp(var5[2][var14]) * Math.exp(-Math.exp(var5[2][var14]));
                            ++var14;
                        }
                    case 12:
                        var12 = "Linear regression:  Maximum Order Statistic Standard Gumbel (y = a.z where z=exp(-x)exp(-exp(-x))): " + this.graphTitle;
                        var13 = " points - experimental values;   line - best fit curve";
                        if (this.weightOpt) {
                            var13 = var13 + ";   error bars - weighting factors";
                        }

                        var14 = 0;

                        while(true) {
                            if (var14 >= var4) {
                                break label182;
                            }

                            var5[3][var14] = this.best[0] * Math.exp(-var5[2][var14]) * Math.exp(-Math.exp(-var5[2][var14]));
                            ++var14;
                        }
                    case 46:
                        var12 = "Linear regression:  Fit to a constant (y = a): " + this.graphTitle;
                        var13 = " points - experimental values;   line - best fit curve";
                        if (this.weightOpt) {
                            var13 = var13 + ";   error bars - weighting factors";
                        }

                        var14 = 0;

                        while(true) {
                            if (var14 >= var4) {
                                break label182;
                            }

                            var5[3][var14] = this.best[0];
                            ++var14;
                        }
                    case 47:
                        var12 = "Linear regression  (y = fixed intercept + b.x): " + this.graphTitle;
                        var13 = " points - experimental values;   line - best fit curve";
                        if (this.weightOpt) {
                            var13 = var13 + ";   error bars - weighting factors";
                        }

                        var14 = 0;

                        while(true) {
                            if (var14 >= var4) {
                                break label182;
                            }

                            var5[3][var14] = this.fixedInterceptL + this.best[0] * var5[2][var14];
                            ++var14;
                        }
                    case 48:
                        var12 = "Linear (polynomial with degree = " + this.nParam + ") regression: " + this.graphTitle;
                        var13 = "Fixed intercept;   points - experimental values;   line - best fit curve";
                        if (this.weightOpt) {
                            var13 = var13 + ";   error bars - weighting factors";
                        }

                        var14 = 0;

                        while(true) {
                            if (var14 >= var4) {
                                break label182;
                            }

                            var15 = this.fixedInterceptP;

                            for(var17 = 0; var17 < this.nParam; ++var17) {
                                var15 += this.best[var17] * Math.pow(var5[2][var14], (double)(var17 + 1));
                            }

                            var5[3][var14] = var15;
                            ++var14;
                        }
                    default:
                        System.out.println("Regression.plotXY(linear): lastMethod, " + this.lastMethod + ", either not recognised or cannot be plotted in two dimensions");
                        System.out.println("No plot attempted");
                        var2 = -1;
                        return var2;
                }
            }

            PlotGraph var19 = new PlotGraph(var5);
            if (this.plotWindowCloseChoice) {
                var19.setCloseChoice(2);
            } else {
                var19.setCloseChoice(1);
            }

            var19.setGraphTitle(var12);
            var19.setGraphTitle2(var13);
            var19.setXaxisLegend(this.xLegend);
            var19.setYaxisLegend(this.yLegend);
            int[] var18 = new int[]{1, 0};
            var19.setPoint(var18);
            int[] var16 = new int[]{0, 3};
            var19.setLine(var16);
            if (this.weightOpt) {
                var19.setErrorBars(0, this.weight);
            }

            var19.plot();
            return var2;
        }
    }

    public int plotXYnonlinear(Object var1, String var2) {
        this.graphTitle = var2;
        RegressionFunction var3 = null;
        RegressionFunction2 var4 = null;
        RegressionFunction3 var5 = null;
        switch(this.simplexFlag) {
            case 1:
                var3 = (RegressionFunction)var1;
                break;
            case 2:
                var4 = (RegressionFunction2)var1;
                break;
            case 3:
            case 4:
                var5 = (RegressionFunction3)var1;
        }

        byte var6 = 0;
        if (this.lastMethod < 3) {
            System.out.println("Regression.plotXY(non-linear): lastMethod, " + this.lastMethod + ", either not recognised or cannot be plotted in two dimensions");
            System.out.println("No plot attempted");
            var6 = -1;
            return var6;
        } else {
            if (this.nXarrays > 1) {
                System.out.println("Multiple Linear Regression with more than one independent variable cannot be plotted in two dimensions");
                System.out.println("plotYY() called instead of plotXY()");
                this.plotYY(var2);
                var6 = -2;
            } else {
                byte var7 = 2;
                int var8 = 200;
                if (var8 < this.nData0) {
                    var8 = this.nData0;
                }

                if (this.lastMethod == 6) {
                    var8 = this.nData0;
                }

                double[][] var11 = PlotGraph.data(var7, var8);

                for(int var12 = 0; var12 < this.nData0; ++var12) {
                    var11[0][var12] = this.xData[0][var12];
                    var11[1][var12] = this.yData[var12];
                }

                if (this.lastMethod == 6) {
                    double[] var24 = new double[this.nXarrays];

                    for(int var13 = 0; var13 < var8; ++var13) {
                        var11[2][var13] = var11[0][var13];
                        var24[0] = var11[2][var13];
                        var11[3][var13] = var3.function(this.best, var24);
                    }
                } else {
                    double var23 = Fmath.minimum(this.xData[0]);
                    double var14 = Fmath.maximum(this.xData[0]);
                    double var16 = (var14 - var23) / (double)(var8 - 1);
                    var11[2][0] = var23;

                    for(int var18 = 1; var18 < var8; ++var18) {
                        var11[2][var18] = var11[2][var18 - 1] + var16;
                    }

                    var11[2][var8 - 1] = var14;
                    double[] var28 = new double[this.nXarrays];
                    label98:
                    switch(this.simplexFlag) {
                        case 1:
                            int var29 = 0;

                            while(true) {
                                if (var29 >= var8) {
                                    break label98;
                                }

                                var28[0] = var11[2][var29];
                                var11[3][var29] = var3.function(this.best, var28);
                                ++var29;
                            }
                        case 2:
                            CubicSpline var19 = new CubicSpline(this.xData[0], this.yCalc);
                            int var30 = 0;

                            while(true) {
                                if (var30 >= var8) {
                                    break label98;
                                }

                                var11[3][var30] = var19.interpolate(var11[2][var30]);
                                ++var30;
                            }
                        case 3:
                        case 4:
                            boolean var20 = false;
                            int var21 = 0;

                            for(; var21 < this.nSpecDual; ++var21) {
                                if (this.lastMethod == this.dualMethods[var21]) {
                                    var20 = true;
                                }
                            }

                            if (var20) {
                                for(var21 = 0; var21 < var8; ++var21) {
                                    var28[0] = var11[2][var21];
                                    var11[3][var21] = var5.function(this.best, var28, -1)[0];
                                }
                            } else {
                                CubicSpline var31 = new CubicSpline(this.xData[0], this.yCalc);

                                for(int var22 = 0; var22 < var8; ++var22) {
                                    var11[3][var22] = var31.interpolate(var11[2][var22]);
                                }
                            }
                    }
                }

                String var9 = this.setGandPtitle(var2);
                String var10 = " points - experimental values;   line - best fit curve";
                if (this.weightOpt) {
                    var10 = var10 + ";   error bars - weighting factors";
                }

                PlotGraph var25 = new PlotGraph(var11);
                if (this.plotWindowCloseChoice) {
                    var25.setCloseChoice(2);
                } else {
                    var25.setCloseChoice(1);
                }

                var25.setGraphTitle(var9);
                var25.setGraphTitle2(var10);
                var25.setXaxisLegend(this.xLegend);
                var25.setYaxisLegend(this.yLegend);
                int[] var26 = new int[]{1, 0};
                var25.setPoint(var26);
                int[] var27 = new int[]{0, 3};
                var25.setLine(var27);
                if (this.weightOpt) {
                    var25.setErrorBars(0, this.weight);
                }

                var25.plot();
            }

            return var6;
        }
    }

    public int plotXYfixed(Object var1, String var2) {
        byte var3 = 0;
        if (this.multipleY) {
            System.out.println("You attempted to use Regression.plotXY() for a multiply dimensioned y array");
            System.out.println("No plot attempted");
            var3 = -1;
        } else {
            RegressionFunction var4 = null;
            RegressionFunction2 var5 = null;
            RegressionFunction3 var6 = null;
            switch(this.simplexFlag) {
                case 1:
                    var4 = (RegressionFunction)var1;
                    break;
                case 2:
                    var5 = (RegressionFunction2)var1;
                    break;
                case 3:
                case 4:
                    var6 = (RegressionFunction3)var1;
            }

            if (this.lastMethod < 3) {
                System.out.println("Regression.plotXY(non-linear): lastMethod, " + this.lastMethod + ", either not recognised or cannot be plotted in two dimensions");
                System.out.println("No plot attempted");
                var3 = -1;
                return var3;
            }

            if (this.nXarrays > 1) {
                System.out.println("Multiple Linear Regression with more than one independent variable cannot be plotted in two dimensions");
                System.out.println("plotYY() called instead of plotXY()");
                this.plotYY(var2);
                var3 = -2;
            } else {
                byte var7 = 2;
                int var8 = 200;
                if (var8 < this.nData0) {
                    var8 = this.nData0;
                }

                if (this.lastMethod == 6) {
                    var8 = this.nData0;
                }

                double[][] var11 = PlotGraph.data(var7, var8);

                for(int var12 = 0; var12 < this.nData0; ++var12) {
                    var11[0][var12] = this.xData[0][var12];
                    var11[1][var12] = this.yData[var12];
                }

                if (this.lastMethod == 6) {
                    double[] var24 = new double[this.nXarrays];

                    for(int var13 = 0; var13 < var8; ++var13) {
                        var11[2][var13] = var11[0][var13];
                        var24[0] = var11[2][var13];
                        var11[3][var13] = var4.function(this.values, var24);
                    }
                } else {
                    double var23 = Fmath.minimum(this.xData[0]);
                    double var14 = Fmath.maximum(this.xData[0]);
                    double var16 = (var14 - var23) / (double)(var8 - 1);
                    var11[2][0] = var23;

                    for(int var18 = 1; var18 < var8; ++var18) {
                        var11[2][var18] = var11[2][var18 - 1] + var16;
                    }

                    double[] var28 = new double[this.nXarrays];
                    label100:
                    switch(this.simplexFlag) {
                        case 1:
                            int var29 = 0;

                            while(true) {
                                if (var29 >= var8) {
                                    break label100;
                                }

                                var28[0] = var11[2][var29];
                                var11[3][var29] = var4.function(this.best, var28);
                                ++var29;
                            }
                        case 2:
                            CubicSpline var19 = new CubicSpline(this.xData[0], this.yCalc);
                            int var30 = 0;

                            while(true) {
                                if (var30 >= var8) {
                                    break label100;
                                }

                                var11[3][var30] = var19.interpolate(var11[2][var30]);
                                ++var30;
                            }
                        case 3:
                        case 4:
                            boolean var20 = false;
                            int var21 = 0;

                            for(; var21 < this.nSpecDual; ++var21) {
                                if (this.lastMethod == this.dualMethods[var21]) {
                                    var20 = true;
                                }
                            }

                            if (var20) {
                                for(var21 = 0; var21 < var8; ++var21) {
                                    var28[0] = var11[2][var21];
                                    var11[3][var21] = var6.function(this.best, var28, -1)[0];
                                }
                            } else {
                                CubicSpline var31 = new CubicSpline(this.xData[0], this.yCalc);

                                for(int var22 = 0; var22 < var8; ++var22) {
                                    var11[3][var22] = var31.interpolate(var11[2][var22]);
                                }
                            }
                    }
                }

                String var9 = this.setGandPtitle(var2);
                String var10 = " points - experimental values;   line - best fit curve";
                if (this.weightOpt) {
                    var10 = var10 + ";   error bars - weighting factors";
                }

                PlotGraph var25 = new PlotGraph(var11);
                if (this.plotWindowCloseChoice) {
                    var25.setCloseChoice(2);
                } else {
                    var25.setCloseChoice(1);
                }

                var25.setGraphTitle(var9);
                var25.setGraphTitle2(var10);
                var25.setXaxisLegend(this.xLegend);
                var25.setYaxisLegend(this.yLegend);
                int[] var26 = new int[]{1, 0};
                var25.setPoint(var26);
                int[] var27 = new int[]{0, 3};
                var25.setLine(var27);
                if (this.weightOpt) {
                    var25.setErrorBars(0, this.weight);
                }

                var25.plot();
            }
        }

        return var3;
    }

    public double[] getWeights() {
        return this.weight;
    }

    public int getNparameters() {
        return this.nParam;
    }

    public int getNdata() {
        return this.nData;
    }

    public boolean getNlrStatus() {
        return this.nlrStatus;
    }

    public void setScale(int var1) {
        if (var1 >= 0 && var1 <= 1) {
            this.scaleOpt = var1;
        } else {
            throw new IllegalArgumentException("The argument must be 0 (no scaling) 1(initial estimates all scaled to unity) or the array of scaling factors");
        }
    }

    public void setScale(double[] var1) {
        this.scale = var1;
        this.scaleOpt = 2;
    }

    public double[] getScale() {
        return Conv.copy(this.scale);
    }

    public void setMinTest(int var1) {
        if (var1 >= 0 && var1 <= 1) {
            this.minTest = var1;
        } else {
            throw new IllegalArgumentException("minTest must be 0 or 1");
        }
    }

    public int getMinTest() {
        return this.minTest;
    }

    public double getSimplexSd() {
        return this.simplexSd;
    }

    public double getVarianceOfObserations() {
        return this.obsnVariance;
    }

    public double[] getBestEstimates() {
        return Conv.copy(this.best);
    }

    public double[] getCoeff() {
        return Conv.copy(this.best);
    }

    public double[] getbestestimatesStandardDeviations() {
        return Conv.copy(this.bestSd);
    }

    public double[] getBestEstimatesStandardDeviations() {
        return Conv.copy(this.bestSd);
    }

    public double[] getCoeffSd() {
        return Conv.copy(this.bestSd);
    }

    public double[] getBestEstimatesErrors() {
        return Conv.copy(this.bestSd);
    }

    public double[] getInitialEstimates() {
        return Conv.copy(this.startH);
    }

    public double[] getScaledInitialEstimates() {
        return Conv.copy(this.startSH);
    }

    public double[] getInitialSteps() {
        return Conv.copy(this.stepH);
    }

    public double[] getScaledInitialSteps() {
        return Conv.copy(this.stepSH);
    }

    public double[] getCoeffVar() {
        double[] var1 = new double[this.nParam];

        for(int var2 = 0; var2 < this.nParam; ++var2) {
            var1[var2] = this.bestSd[var2] * 100.0D / this.best[var2];
        }

        return var1;
    }

    public double[] getPseudoSd() {
        return Conv.copy(this.pseudoSd);
    }

    public double[] getPseudoErrors() {
        return Conv.copy(this.pseudoSd);
    }

    public double[] getTvalues() {
        return Conv.copy(this.tValues);
    }

    public double[] getPvalues() {
        return Conv.copy(this.pValues);
    }

    public double[][] getXdata() {
        return Conv.copy(this.xData);
    }

    public double[] getYdata() {
        return Conv.copy(this.yData);
    }

    public double[] getYcalc() {
        double[] var1 = new double[this.nData];

        for(int var2 = 0; var2 < this.nData; ++var2) {
            var1[var2] = this.yCalc[var2];
        }

        return var1;
    }

    public double[] getResiduals() {
        double[] var1 = new double[this.nData];

        for(int var2 = 0; var2 < this.nData; ++var2) {
            var1[var2] = this.yData[var2] - this.yCalc[var2];
        }

        return var1;
    }

    public double[] getWeightedResiduals() {
        double[] var1 = new double[this.nData];

        for(int var2 = 0; var2 < this.nData; ++var2) {
            var1[var2] = (this.yData[var2] - this.yCalc[var2]) / this.weight[var2];
        }

        return var1;
    }

    public double getSumOfSquares() {
        return this.sumOfSquaresError;
    }

    public double getSumOfUnweightedResidualSquares() {
        return this.sumOfSquaresError;
    }

    public double getSumOfWeightedResidualSquares() {
        return this.chiSquare;
    }

    public double getChiSquare() {
        return this.chiSquare;
    }

    public double getReducedChiSquare() {
        return this.reducedChiSquare;
    }

    public double getTotalSumOfWeightedSquares() {
        return this.sumOfSquaresTotal;
    }

    public double getRegressionSumOfWeightedSquares() {
        return this.sumOfSquaresRegrn;
    }

    public double getCoefficientOfDetermination() {
        return this.multR;
    }

    public double getSampleR() {
        return this.multR;
    }

    public double getAdjustedCoefficientOfDetermination() {
        return this.adjustedR;
    }

    public double getCoeffDeterminationFratio() {
        return this.multipleF;
    }

    public double getCoeffDeterminationFratioProb() {
        return this.multipleFprob;
    }

    public double[][] getCovMatrix() {
        return Conv.copy(this.covar);
    }

    public double[][] getCorrCoeffMatrix() {
        return Conv.copy(this.corrCoeff);
    }

    public double[][] getFirstDerivatives() {
        return Conv.copy(this.firstDerivs);
    }

    public int getNiter() {
        return this.nIter;
    }

    public void setNmax(int var1) {
        this.nMax = var1;
    }

    public int getNmax() {
        return this.nMax;
    }

    public void setNmin(int var1) {
        this.minIter = var1;
    }

    public int getNmin() {
        return this.minIter;
    }

    public int getNrestarts() {
        return this.kRestart;
    }

    public void setNrestartsMax(int var1) {
        this.konvge = var1;
    }

    public int getNrestartsMax() {
        return this.konvge;
    }

    public double getDegFree() {
        return (double)this.degreesOfFreedom;
    }

    public void setNMreflect(double var1) {
        this.rCoeff = var1;
    }

    public double getNMreflect() {
        return this.rCoeff;
    }

    public void setNMextend(double var1) {
        this.eCoeff = var1;
    }

    public double getNMextend() {
        return this.eCoeff;
    }

    public void setNMcontract(double var1) {
        this.cCoeff = var1;
    }

    public double getNMcontract() {
        return this.cCoeff;
    }

    public void setTolerance(double var1) {
        this.fTol = var1;
    }

    public double getTolerance() {
        return this.fTol;
    }

    public double[][] getGrad() {
        return this.grad;
    }

    public void setDelta(double var1) {
        this.delta = var1;
    }

    public double getDelta() {
        return this.delta;
    }

    public boolean getInversionCheck() {
        return this.invertFlag;
    }

    public boolean getPosVarCheck() {
        return this.posVarFlag;
    }

    public static ArrayList<Object> testOfAdditionalTerms_ArrayList(double var0, int var2, double var3, int var5, int var6) {
        return testOfAdditionalTerms_ArrayList(var0, var2, var3, var5, var6, 0.05D);
    }

    public static ArrayList<Object> testOfAdditionalTerms_ArrayList(double var0, int var2, double var3, int var5, int var6, double var7) {
        int var9 = var6 - var2;
        int var10 = var6 - var5;
        boolean var11 = false;
        if (var9 < var10) {
            var11 = true;
            double var12 = var0;
            var0 = var3;
            var3 = var12;
            int var14 = var2;
            var2 = var5;
            var5 = var14;
            var9 = var6 - var2;
            var10 = var6 - var14;
            System.out.println("package com.flanagan.analysis; class Regression; method testAdditionalTerms");
            System.out.println("the order of the chi-squares has been reversed to give a second chi- square with the lowest degrees of freedom");
        }

        int var22 = var9 - var10;
        double var13 = (var0 - var3) / (double)var22;
        double var15 = var3 / (double)var10;
        double var17 = var13 / var15;
        double var19 = 1.0D;
        if (var0 > var3) {
            var19 = Stat.fTestProb(var17, var22, var10);
        }

        ArrayList var21 = new ArrayList();
        var21.add(new Double(var17));
        var21.add(new Double(var19));
        var21.add(new Boolean(var11));
        var21.add(new Double(var0));
        var21.add(new Integer(var2));
        var21.add(new Double(var3));
        var21.add(new Integer(var5));
        var21.add(new Integer(var6));
        var21.add(new Double(Stat.fTestValueGivenFprob(var7, var22, var10)));
        return var21;
    }

    public static ArrayList<Object> testOfAdditionalTerms(double var0, int var2, double var3, int var5, int var6, double var7) {
        return testOfAdditionalTerms_ArrayList(var0, var2, var3, var5, var6, var7);
    }

    public static ArrayList<Object> testOfAdditionalTerms(double var0, int var2, double var3, int var5, int var6) {
        return testOfAdditionalTerms_ArrayList(var0, var2, var3, var5, var6, 0.05D);
    }

    public static double testOfAdditionalTermsFratio(double var0, int var2, double var3, int var5, int var6) {
        return testOfAdditionalTermsFratio(var0, var2, var3, var5, var6, 0.05D);
    }

    public static double testOfAdditionalTermsFratio(double var0, int var2, double var3, int var5, int var6, double var7) {
        int var9 = var6 - var2;
        int var10 = var6 - var5;
        boolean var11 = false;
        if (var9 < var10) {
            var11 = true;
            double var12 = var0;
            var0 = var3;
            var3 = var12;
            var9 = var6 - var5;
            var10 = var6 - var2;
            System.out.println("package com.flanagan.analysis; class Regression; method testAdditionalTermsFratio");
            System.out.println("the order of the chi-squares has been reversed to give a second chi- square with the lowest degrees of freedom");
        }

        int var19 = var9 - var10;
        double var13 = (var0 - var3) / (double)var19;
        double var15 = var3 / (double)var10;
        double var17 = var13 / var15;
        return var17;
    }

    public static double testOfAdditionalTermsFprobability(double var0, int var2, double var3, int var5, int var6) {
        return testOfAdditionalTermsFprobability(var0, var2, var3, var5, var6, 0.05D);
    }

    public static double testOfAdditionalTermsFprobability(double var0, int var2, double var3, int var5, int var6, double var7) {
        int var9 = var6 - var2;
        int var10 = var6 - var5;
        boolean var11 = false;
        if (var9 < var10) {
            var11 = true;
            double var12 = var0;
            var0 = var3;
            var3 = var12;
            var9 = var6 - var5;
            var10 = var6 - var2;
            System.out.println("package com.flanagan.analysis; class Regression; method testAdditionalTermsFprobability");
            System.out.println("the order of the chi-squares has been reversed to give a second chi- square with the lowest degrees of freedom");
        }

        int var21 = var9 - var10;
        double var13 = (var0 - var3) / (double)var21;
        double var15 = var3 / (double)var10;
        double var17 = var13 / var15;
        double var19 = 1.0D;
        if (var0 > var3) {
            var19 = Stat.fTestProb(var17, var21, var10);
        }

        return var19;
    }

    public static double testOfAdditionalTermsFprobabilty(double var0, int var2, double var3, int var5, int var6) {
        return testOfAdditionalTermsFprobability(var0, var2, var3, var5, var6, 0.05D);
    }

    public void poisson() {
        this.userSupplied = false;
        this.fitPoisson(0);
    }

    public void poissonPlot() {
        this.userSupplied = false;
        this.fitPoisson(1);
    }

    protected void fitPoisson(int var1) {
        if (this.multipleY) {
            throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
        } else {
            this.lastMethod = 6;
            this.linNonLin = false;
            this.zeroCheck = false;
            this.nParam = 2;
            if (!this.scaleFlag) {
                this.nParam = 2;
            }

            this.degreesOfFreedom = this.nData - this.nParam;
            if (this.degreesOfFreedom < 1) {
                throw new IllegalArgumentException("Degrees of freedom must be greater than 0");
            } else {
                for(int var2 = 0; var2 < this.nData; ++var2) {
                    if (this.xData[0][var2] - Math.floor(this.xData[0][var2]) != 0.0D) {
                        throw new IllegalArgumentException("all abscissae must be, mathematically, integer values");
                    }
                }

                ArrayList var16 = dataSign(this.yData);
                Double var3 = null;
                Integer var4 = null;
                var4 = (Integer)var16.get(5);
                int var5 = var4;
                double var6 = this.xData[0][var5];
                var3 = (Double)var16.get(4);
                double var8 = var3;
                double[] var10 = new double[this.nParam];
                double[] var11 = new double[this.nParam];
                var10[0] = var6;
                if (this.scaleFlag) {
                    var10[1] = var8 / (Math.exp(var6 * Math.log(var6) - Stat.logFactorial(var6)) * Math.exp(-var6));
                }

                var11[0] = 0.1D * var10[0];
                if (var11[0] == 0.0D) {
                    ArrayList var12 = dataSign(this.xData[0]);
                    Double var13 = null;
                    var13 = (Double)var12.get(2);
                    double var14 = var13;
                    if (var14 == 0.0D) {
                        var13 = (Double)var12.get(0);
                        var14 = var13;
                    }

                    var11[0] = var14 * 0.1D;
                }

                if (this.scaleFlag) {
                    var11[1] = 0.1D * var10[1];
                }

                PoissonFunction var17 = new PoissonFunction();
                this.addConstraint(1, -1, 0.0D);
                var17.setScaleOption(this.scaleFlag);
                var17.setScaleFactor(this.yScaleFactor);
                this.simplexFlag = 1;
                this.nonLinStatsNeeded = true;
                this.dualErrorsRequired = false;
                this.nelderMead(var17, (Object)null, var10, var11, this.fTol, this.nMax);
                if (var1 == 1) {
                    if (!this.suppressPrint) {
                        this.print();
                    }

                    this.plotOpt = false;
                    int var18 = this.plotXY((RegressionFunction)var17);
                    if (var18 != -2 && !this.suppressYYplot) {
                        this.plotYY();
                    }
                }

            }
        }
    }

    public void gaussian() {
        this.userSupplied = false;
        this.fitGaussian(0);
    }

    public void normal() {
        this.userSupplied = false;
        this.fitGaussian(0);
    }

    public void gaussianPlot() {
        this.userSupplied = false;
        this.fitGaussian(1);
    }

    public void normalPlot() {
        this.userSupplied = false;
        this.fitGaussian(1);
    }

    protected void fitGaussian(int var1) {
        if (this.multipleY) {
            throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
        } else {
            this.lastMethod = 4;
            this.linNonLin = false;
            this.zeroCheck = false;
            this.nParam = 3;
            if (!this.scaleFlag) {
                this.nParam = 2;
            }

            this.degreesOfFreedom = this.nData - this.nParam;
            if (this.degreesOfFreedom < 1 && !this.ignoreDofFcheck) {
                throw new IllegalArgumentException("Degrees of freedom must be greater than 0");
            } else {
                sort(this.xData[0], this.yData, this.weight);
                Double var2 = null;
                ArrayList var3 = dataSign(this.yData);
                var2 = (Double)var3.get(4);
                double var4 = var2;
                boolean var6 = false;
                if (var4 < 0.0D) {
                    System.out.println("Regression.fitGaussian(): This implementation of the Gaussian distribution takes only positive y values\n(noise taking low values below zero are allowed)");
                    System.out.println("All y values have been multiplied by -1 before fitting");

                    for(int var7 = 0; var7 < this.nData; ++var7) {
                        this.yData[var7] = -this.yData[var7];
                    }

                    var3 = dataSign(this.yData);
                    var6 = true;
                }

                ArrayList var22 = dataSign(this.yData);
                Integer var8 = null;
                var8 = (Integer)var22.get(5);
                int var9 = var8;
                double var10 = this.xData[0][var9];
                double var12 = Math.sqrt(2.0D) * halfWidth(this.xData[0], this.yData);
                var2 = (Double)var22.get(4);
                double var14 = var2;
                var14 = var14 * var12 * Math.sqrt(6.283185307179586D);
                double[] var16 = new double[this.nParam];
                double[] var17 = new double[this.nParam];
                var16[0] = var10;
                var16[1] = var12;
                if (this.scaleFlag) {
                    var16[2] = var14;
                }

                var17[0] = 0.1D * var12;
                var17[1] = 0.1D * var16[1];
                if (var17[1] == 0.0D) {
                    ArrayList var18 = dataSign(this.xData[0]);
                    Double var19 = null;
                    var19 = (Double)var18.get(2);
                    double var20 = var19;
                    if (var20 == 0.0D) {
                        var19 = (Double)var18.get(0);
                        var20 = var19;
                    }

                    var17[1] = var20 * 0.1D;
                }

                if (this.scaleFlag) {
                    var17[2] = 0.1D * var16[2];
                }

                GaussianFunction var23 = new GaussianFunction();
                this.addConstraint(1, -1, 0.0D);
                var23.setScaleOption(this.scaleFlag);
                var23.setScaleFactor(this.yScaleFactor);
                this.simplexFlag = 1;
                this.nonLinStatsNeeded = true;
                this.dualErrorsRequired = false;
                this.nelderMead(var23, (Object)null, var16, var17, this.fTol, this.nMax);
                int var24;
                if (var1 == 1) {
                    if (!this.suppressPrint) {
                        this.print();
                    }

                    var24 = this.plotXY((RegressionFunction)var23);
                    if (var24 != -2 && !this.suppressYYplot) {
                        this.plotYY();
                    }
                }

                if (var6) {
                    for(var24 = 0; var24 < this.nData - 1; ++var24) {
                        this.yData[var24] = -this.yData[var24];
                    }
                }

            }
        }
    }

    public void gaussian(double[] var1, boolean[] var2) {
        this.userSupplied = true;
        this.fitGaussianFixed(var1, var2, 0);
    }

    public void normal(double[] var1, boolean[] var2) {
        this.userSupplied = true;
        this.fitGaussianFixed(var1, var2, 0);
    }

    public void gaussianPlot(double[] var1, boolean[] var2) {
        this.userSupplied = true;
        this.fitGaussianFixed(var1, var2, 1);
    }

    public void normalPlot(double[] var1, boolean[] var2) {
        this.userSupplied = true;
        this.fitGaussianFixed(var1, var2, 1);
    }

    protected void fitGaussianFixed(double[] var1, boolean[] var2, int var3) {
        if (this.multipleY) {
            throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
        } else {
            this.lastMethod = 38;
            this.values = var1;
            this.fixed = var2;
            this.scaleFlag = true;
            this.linNonLin = false;
            this.zeroCheck = false;
            this.nParam = 3;
            this.degreesOfFreedom = this.nData - this.nParam;
            if (this.degreesOfFreedom < 1 && !this.ignoreDofFcheck) {
                throw new IllegalArgumentException("Degrees of freedom must be greater than 0");
            } else {
                sort(this.xData[0], this.yData, this.weight);
                Double var4 = null;
                ArrayList var5 = dataSign(this.yData);
                var4 = (Double)var5.get(4);
                double var6 = var4;
                boolean var8 = false;
                if (var6 < 0.0D) {
                    System.out.println("Regression.fitGaussian(): This implementation of the Gaussian distribution takes only positive y values\n(noise taking low values below zero are allowed)");
                    System.out.println("All y values have been multiplied by -1 before fitting");

                    for(int var9 = 0; var9 < this.nData; ++var9) {
                        this.yData[var9] = -this.yData[var9];
                    }

                    var5 = dataSign(this.yData);
                    var8 = true;
                }

                GaussianFunctionFixed var23 = new GaussianFunctionFixed();
                var23.setFixed(var2);
                var23.setParam(var1);
                int var10 = this.nParam;

                for(int var11 = 0; var11 < this.nParam; ++var11) {
                    if (var2[var11]) {
                        --var10;
                    }
                }

                if (var10 == 0) {
                    if (var3 == 0) {
                        throw new IllegalArgumentException("At least one parameter must be available for variation by the Regression procedure or GauasianPlot should have been called and not Gaussian");
                    }

                    var3 = 3;
                }

                double[] var24 = new double[var10];
                double[] var12 = new double[var10];
                boolean[] var13 = new boolean[var10];
                double var14 = Fmath.minimum(this.xData[0]);
                double var16 = Fmath.maximum(this.xData[0]);
                double var18 = Fmath.maximum(this.yData);
                if (var1[2] == 0.0D) {
                    if (var2[2]) {
                        throw new IllegalArgumentException("Scale factor has been fixed at zero");
                    }

                    var1[2] = var18;
                }

                int var20 = 0;

                int var21;
                for(var21 = 0; var21 < this.nParam; ++var21) {
                    if (!var2[var21]) {
                        var24[var20] = var1[var21];
                        var12[var20] = var24[var20] * 0.1D;
                        if (var12[var20] == 0.0D) {
                            var12[var20] = (var16 - var14) * 0.1D;
                        }

                        var13[var20] = false;
                        if (var21 == 1) {
                            var13[var20] = true;
                        }

                        ++var20;
                    }
                }

                this.nParam = var10;

                for(var21 = 0; var21 < this.nParam; ++var21) {
                    if (var13[var21]) {
                        this.addConstraint(var21, -1, 0.0D);
                    }
                }

                this.simplexFlag = 1;
                this.nonLinStatsNeeded = true;
                this.dualErrorsRequired = false;
                if (var3 != 3) {
                    this.nelderMead(var23, (Object)null, var24, var12, this.fTol, this.nMax);
                }

                int var22;
                if (var3 == 1) {
                    if (!this.suppressPrint) {
                        this.print();
                    }

                    var22 = this.plotXY((RegressionFunction)var23);
                    if (var22 != -2 && !this.suppressYYplot) {
                        this.plotYY();
                    }
                }

                if (var3 == 3) {
                    this.plotXYfixed(var23, "Gaussian distribution - all parameters fixed");
                }

                if (var8) {
                    for(var22 = 0; var22 < this.nData - 1; ++var22) {
                        this.yData[var22] = -this.yData[var22];
                    }
                }

            }
        }
    }

    public void multipleGaussiansPlot(int var1, double[] var2, double[] var3, double[] var4) {
        if (var2.length != var1) {
            throw new IllegalArgumentException("length of initial means array, " + var2.length + ", does not equal the number of Gaussians, " + var1);
        } else if (var3.length != var1) {
            throw new IllegalArgumentException("length of initial standard deviations array, " + var3.length + ", does not equal the number of Gaussians, " + var1);
        } else if (var4.length != var1) {
            throw new IllegalArgumentException("length of initial fractional weight array, " + var4.length + ", does not equal the number of Gaussians, " + var1);
        } else {
            double var5 = 0.0D;

            for(int var7 = 0; var7 < var1; ++var7) {
                var5 += var4[var7];
            }

            if (var5 != 1.0D) {
                System.out.println("Regression method multipleGaussiansPlot: the sum of the initial estimates of the fractional weight, " + var5 + ", does not equal 1.0");
                System.out.println("Program continued using the supplied fractional weight");
            }

            this.fitMultipleGaussians(var1, var2, var3, var4, 1);
        }
    }

    protected void fitMultipleGaussians(int var1, double[] var2, double[] var3, double[] var4, int var5) {
        this.nGaussians = var1;
        if (this.multipleY) {
            throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
        } else {
            this.lastMethod = 49;
            this.linNonLin = false;
            this.zeroCheck = false;
            this.nParam = 3 * this.nGaussians;
            boolean var6 = this.scaleFlag;
            this.scaleFlag = false;
            this.degreesOfFreedom = this.nData - this.nParam;
            if (this.degreesOfFreedom < 1 && !this.ignoreDofFcheck) {
                throw new IllegalArgumentException("Degrees of freedom must be greater than 0");
            } else {
                int var7 = this.nMax;
                if (this.nMax < 10000) {
                    this.nMax = 10000;
                }

                sort(this.xData[0], this.yData, this.weight);
                Double var8 = null;
                ArrayList var9 = dataSign(this.yData);
                var8 = (Double)var9.get(4);
                double var10 = var8;
                boolean var12 = false;
                if (var10 < 0.0D) {
                    System.out.println("Regression.fitGaussian(): This implementation of the Gaussian distribution takes only positive y values\n(noise taking low values below zero are allowed)");
                    System.out.println("All y values have been multiplied by -1 before fitting");

                    for(int var13 = 0; var13 < this.nData; ++var13) {
                        this.yData[var13] = -this.yData[var13];
                    }

                    var9 = dataSign(this.yData);
                    var12 = true;
                }

                ArrayList var36 = dataSign(this.yData);
                Integer var14 = null;
                var14 = (Integer)var36.get(5);
                int var15 = var14;
                double var16 = this.xData[0][var15];
                double var10000 = Math.sqrt(2.0D) * halfWidth(this.xData[0], this.yData);
                var8 = (Double)var36.get(4);
                double var20 = var8;
                double[] var22 = new double[this.nParam];
                double[] var23 = new double[this.nParam];
                int var24 = 0;

                for(int var25 = 0; var25 < var1; ++var25) {
                    var22[var24] = var2[var25];
                    var23[var24] = Math.abs(0.1D * var22[var24]);
                    var22[var24 + 1] = var3[var25];
                    var23[var24 + 1] = Math.abs(0.1D * var22[var24 + 1]);
                    if (var23[var24 + 1] == 0.0D) {
                        ArrayList var26 = dataSign(this.xData[0]);
                        Double var27 = null;
                        var27 = (Double)var26.get(2);
                        double var28 = var27;
                        if (var28 == 0.0D) {
                            var27 = (Double)var26.get(0);
                            var28 = var27;
                        }

                        var23[var24 + 1] = Math.abs(var28 * 0.1D);
                    }

                    var22[var24 + 2] = var4[var25] * Math.sqrt(6.283185307179586D) * var22[var24 + 1] * var20;
                    var23[var24 + 2] = Math.abs(0.1D * var22[var24 + 2]);
                    var24 += 3;
                }

                MultipleGaussianFunction var37 = new MultipleGaussianFunction();
                var37.setScaleOption(this.scaleFlag);
                double var38 = this.yScaleFactor;
                if (!this.scaleFlag) {
                    var38 = 1.0D;
                }

                var37.setScaleFactor(var38);
                var37.setNgaussians(this.nGaussians);

                for(int var39 = 0; var39 < this.nGaussians; ++var39) {
                    this.addConstraint(3 * var39 + 1, -1, 0.0D);
                    this.addConstraint(3 * var39 + 2, -1, 0.0D);
                }

                this.simplexFlag = 1;
                this.nonLinStatsNeeded = true;
                this.dualErrorsRequired = false;
                this.nelderMead(var37, (Object)null, var22, var23, this.fTol, this.nMax);
                this.multGaussFract = new double[this.nGaussians];
                this.multGaussFractErrors = new double[this.nGaussians];
                this.multGaussCoeffVar = new double[this.nGaussians];
                this.multGaussTvalue = new double[this.nGaussians];
                this.multGaussPvalue = new double[this.nGaussians];

                int var29;
                for(var29 = 0; var29 < var1; ++var29) {
                    this.multGaussFractErrors[var29] = 0.0D / 0.0;
                    this.multGaussCoeffVar[var29] = 0.0D / 0.0;
                    this.multGaussTvalue[var29] = 0.0D / 0.0;
                    this.multGaussPvalue[var29] = 0.0D / 0.0;
                }

                this.multGaussScaleError = 0.0D / 0.0;
                this.multGaussScaleCoeffVar = 0.0D / 0.0;
                this.multGaussScaleTvalue = 0.0D / 0.0;
                this.multGaussScalePvalue = 0.0D / 0.0;
                this.multGaussScaleTvalue = 0.0D / 0.0;
                this.multGaussScalePvalue = 0.0D / 0.0;
                int var31;
                if (this.invertFlag) {
                    ErrorProp[] var40 = new ErrorProp[this.nGaussians];
                    ErrorProp var30 = new ErrorProp(0.0D, 0.0D);

                    for(var31 = 0; var31 < var1; ++var31) {
                        var40[var31] = new ErrorProp(this.best[3 * var31 + 2], this.bestSd[3 * var31 + 2]);
                        var30 = var30.plus(var40[var31]);
                    }

                    ErrorProp var42 = new ErrorProp(0.0D, 0.0D);

                    for(int var32 = 0; var32 < var1; ++var32) {
                        ErrorProp var33 = var40[var32].over(var30);
                        this.multGaussFract[var32] = var33.getValue();
                        this.multGaussFractErrors[var32] = var33.getError();
                        var42 = var42.plus(var40[var32].over(var33));
                        this.multGaussCoeffVar[var32] = 100.0D * this.multGaussFractErrors[var32] / this.multGaussFract[var32];
                        this.multGaussTvalue[var32] = this.multGaussFract[var32] / this.multGaussFractErrors[var32];
                        double var34 = Math.abs(this.multGaussTvalue[var32]);
                        if (var34 != var34) {
                            this.multGaussPvalue[var32] = 0.0D / 0.0;
                        } else {
                            this.multGaussPvalue[var32] = 1.0D - Stat.studentTcdf(-var34, var34, this.degreesOfFreedom);
                        }
                    }

                    var42 = var42.over((double)this.nGaussians);
                    this.multGaussScale = var42.getValue();
                    this.multGaussScaleError = var42.getError();
                    this.multGaussScaleCoeffVar = 100.0D * this.multGaussScaleError / this.multGaussScale;
                    this.multGaussScaleTvalue = this.multGaussScale / this.multGaussScaleError;
                    double var43 = Math.abs(this.multGaussScaleTvalue);
                    if (var43 != var43) {
                        this.multGaussScalePvalue = 0.0D / 0.0;
                    } else {
                        this.multGaussScalePvalue = 1.0D - Stat.studentTcdf(-var43, var43, this.degreesOfFreedom);
                    }
                } else {
                    double var41 = 0.0D;

                    for(var31 = 0; var31 < var1; ++var31) {
                        var41 += this.best[3 * var31 + 2];
                    }

                    this.multGaussScale = 0.0D;

                    for(var31 = 0; var31 < var1; ++var31) {
                        this.multGaussFract[var31] = this.best[3 * var31 + 2] / var41;
                        this.multGaussScale += this.multGaussFract[var31];
                    }

                    this.multGaussScale /= (double)this.nGaussians;
                }

                if (var5 == 1) {
                    if (!this.suppressPrint) {
                        this.print();
                    }

                    var29 = this.plotXY((RegressionFunction)var37);
                    if (var29 != -2 && !this.suppressYYplot) {
                        this.plotYY();
                    }
                }

                if (var12) {
                    for(var29 = 0; var29 < this.nData - 1; ++var29) {
                        this.yData[var29] = -this.yData[var29];
                    }
                }

                this.nMax = var7;
                this.scaleFlag = var6;
            }
        }
    }

    public void logNormal() {
        this.fitLogNormalTwoPar(0);
    }

    public void logNormalTwoPar() {
        this.fitLogNormalTwoPar(0);
    }

    public void logNormalPlot() {
        this.fitLogNormalTwoPar(1);
    }

    public void logNormalTwoParPlot() {
        this.fitLogNormalTwoPar(1);
    }

    protected void fitLogNormalTwoPar(int var1) {
        if (this.multipleY) {
            throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
        } else {
            this.lastMethod = 36;
            this.userSupplied = false;
            this.linNonLin = false;
            this.zeroCheck = false;
            this.nParam = 3;
            if (!this.scaleFlag) {
                this.nParam = 2;
            }

            this.degreesOfFreedom = this.nData - this.nParam;
            if (this.degreesOfFreedom < 1 && !this.ignoreDofFcheck) {
                throw new IllegalArgumentException("Degrees of freedom must be greater than 0");
            } else {
                sort(this.xData[0], this.yData, this.weight);
                Double var2 = null;
                ArrayList var3 = dataSign(this.yData);
                var2 = (Double)var3.get(4);
                double var4 = var2;
                boolean var6 = false;
                if (var4 < 0.0D) {
                    System.out.println("Regression.fitLogNormalTwoPar(): This implementation of the two parameter log-nprmal distribution takes only positive y values\n(noise taking low values below zero are allowed)");
                    System.out.println("All y values have been multiplied by -1 before fitting");

                    for(int var7 = 0; var7 < this.nData; ++var7) {
                        this.yData[var7] = -this.yData[var7];
                    }

                    var3 = dataSign(this.yData);
                    var6 = true;
                }

                ArrayList var24 = dataSign(this.yData);
                Integer var8 = null;
                var8 = (Integer)var24.get(5);
                int var9 = var8;
                double var10 = this.xData[0][var9];
                double var12 = 0.0D;

                for(int var14 = 0; var14 < this.nData; ++var14) {
                    var12 += Math.log(this.xData[0][var14]);
                }

                var12 /= (double)this.nData;
                double var25 = 0.0D;

                for(int var16 = 0; var16 < this.nData; ++var16) {
                    var25 += Fmath.square(Math.log(this.xData[0][var16]) - var12);
                }

                var25 = Math.sqrt(var25 / (double)this.nData);
                var2 = (Double)var24.get(4);
                double var26 = var2;
                var26 *= Math.exp(var12 - var25 * var25 / 2.0D);
                double[] var18 = new double[this.nParam];
                double[] var19 = new double[this.nParam];
                var18[0] = var12;
                var18[1] = var25;
                if (this.scaleFlag) {
                    var18[2] = var26;
                }

                var19[0] = 0.1D * var18[0];
                var19[1] = 0.1D * var18[1];
                ArrayList var20;
                Double var21;
                double var22;
                if (var19[0] == 0.0D) {
                    var20 = dataSign(this.xData[0]);
                    var21 = null;
                    var21 = (Double)var20.get(2);
                    var22 = var21;
                    if (var22 == 0.0D) {
                        var21 = (Double)var20.get(0);
                        var22 = var21;
                    }

                    var19[0] = var22 * 0.1D;
                }

                if (var19[0] == 0.0D) {
                    var20 = dataSign(this.xData[0]);
                    var21 = null;
                    var21 = (Double)var20.get(2);
                    var22 = var21;
                    if (var22 == 0.0D) {
                        var21 = (Double)var20.get(0);
                        var22 = var21;
                    }

                    var19[1] = var22 * 0.1D;
                }

                if (this.scaleFlag) {
                    var19[2] = 0.1D * var18[2];
                }

                LogNormalTwoParFunction var27 = new LogNormalTwoParFunction();
                this.addConstraint(1, -1, 0.0D);
                var27.setScaleOption(this.scaleFlag);
                var27.setScaleFactor(this.yScaleFactor);
                this.simplexFlag = 1;
                this.nonLinStatsNeeded = true;
                this.dualErrorsRequired = false;
                this.nelderMead(var27, (Object)null, var18, var19, this.fTol, this.nMax);
                int var28;
                if (var1 == 1) {
                    if (!this.suppressPrint) {
                        this.print();
                    }

                    var28 = this.plotXY((RegressionFunction)var27);
                    if (var28 != -2 && !this.suppressYYplot) {
                        this.plotYY();
                    }
                }

                if (var6) {
                    for(var28 = 0; var28 < this.nData - 1; ++var28) {
                        this.yData[var28] = -this.yData[var28];
                    }
                }

            }
        }
    }

    public void logNormalThreePar() {
        this.fitLogNormalThreePar(0);
    }

    public void logNormalThreeParPlot() {
        this.fitLogNormalThreePar(1);
    }

    protected void fitLogNormalThreePar(int var1) {
        if (this.multipleY) {
            throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
        } else {
            this.lastMethod = 37;
            this.userSupplied = false;
            this.linNonLin = false;
            this.zeroCheck = false;
            this.nParam = 4;
            if (!this.scaleFlag) {
                this.nParam = 3;
            }

            this.degreesOfFreedom = this.nData - this.nParam;
            if (this.degreesOfFreedom < 1 && !this.ignoreDofFcheck) {
                throw new IllegalArgumentException("Degrees of freedom must be greater than 0");
            } else {
                sort(this.xData[0], this.yData, this.weight);
                Double var2 = null;
                ArrayList var3 = dataSign(this.yData);
                var2 = (Double)var3.get(4);
                double var4 = var2;
                boolean var6 = false;
                if (var4 < 0.0D) {
                    System.out.println("Regression.fitLogNormalThreePar(): This implementation of the three parameter log-normal distribution takes only positive y values\n(noise taking low values below zero are allowed)");
                    System.out.println("All y values have been multiplied by -1 before fitting");

                    for(int var7 = 0; var7 < this.nData; ++var7) {
                        this.yData[var7] = -this.yData[var7];
                    }

                    var3 = dataSign(this.yData);
                    var6 = true;
                }

                ArrayList var31 = dataSign(this.yData);
                Integer var8 = null;
                var8 = (Integer)var31.get(5);
                int var9 = var8;
                double var10 = this.xData[0][var9];
                double var12 = 0.0D;

                for(int var14 = 0; var14 < this.nData; ++var14) {
                    var12 += this.xData[0][var14];
                }

                var12 /= (double)this.nData;
                double var32 = 0.0D;

                for(int var16 = 0; var16 < this.nData; ++var16) {
                    var32 += Fmath.square(Math.log(this.xData[0][var16]) - Math.log(var12));
                }

                var32 = Math.sqrt(var32 / (double)this.nData);
                ArrayList var33 = dataSign(this.xData[0]);
                Double var17 = null;
                var17 = (Double)var33.get(0);
                double var18 = var17;
                var17 = (Double)var33.get(2);
                double var20 = var17;
                double var22 = var18 - (var20 - var18) / 100.0D;
                if (var18 == 0.0D) {
                    var22 -= (var20 - var18) / 100.0D;
                }

                var2 = (Double)var31.get(4);
                double var24 = var2;
                var24 = var24 * (var12 + var22) * Math.exp(-var32 * var32 / 2.0D);
                double[] var26 = new double[this.nParam];
                double[] var27 = new double[this.nParam];
                var26[0] = var22;
                var26[1] = var32;
                var26[2] = var12;
                if (this.scaleFlag) {
                    var26[3] = var24;
                }

                var27[0] = 0.1D * var26[0];
                var27[1] = 0.1D * var26[1];
                var27[2] = 0.1D * var26[2];

                for(int var28 = 0; var28 < 3; ++var28) {
                    if (var27[var28] == 0.0D) {
                        var27[var28] = var20 * 0.1D;
                    }
                }

                if (this.scaleFlag) {
                    var27[3] = 0.1D * var26[3];
                }

                LogNormalThreeParFunction var34 = new LogNormalThreeParFunction();
                this.addConstraint(0, 1, var18);
                this.addConstraint(1, -1, 0.0D);
                this.addConstraint(2, -1, 0.0D);
                var34.setScaleOption(this.scaleFlag);
                var34.setScaleFactor(this.yScaleFactor);
                this.simplexFlag = 1;
                this.nonLinStatsNeeded = true;
                this.dualErrorsRequired = false;
                this.nelderMead(var34, (Object)null, var26, var27, this.fTol, this.nMax);
                int var30;
                if (var1 == 1) {
                    if (!this.suppressPrint) {
                        this.print();
                    }

                    var30 = this.plotXY((RegressionFunction)var34);
                    if (var30 != -2 && !this.suppressYYplot) {
                        this.plotYY();
                    }
                }

                if (var6) {
                    for(var30 = 0; var30 < this.nData - 1; ++var30) {
                        this.yData[var30] = -this.yData[var30];
                    }
                }

            }
        }
    }

    public void lorentzian() {
        this.fitLorentzian(0);
    }

    public void lorentzianPlot() {
        this.fitLorentzian(1);
    }

    protected void fitLorentzian(int var1) {
        if (this.multipleY) {
            throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
        } else {
            this.lastMethod = 5;
            this.userSupplied = false;
            this.linNonLin = false;
            this.zeroCheck = false;
            this.nParam = 3;
            if (!this.scaleFlag) {
                this.nParam = 2;
            }

            this.degreesOfFreedom = this.nData - this.nParam;
            if (this.degreesOfFreedom < 1 && !this.ignoreDofFcheck) {
                throw new IllegalArgumentException("Degrees of freedom must be greater than 0");
            } else {
                sort(this.xData[0], this.yData, this.weight);
                Double var2 = null;
                ArrayList var3 = dataSign(this.yData);
                var2 = (Double)var3.get(4);
                double var4 = var2;
                boolean var6 = false;
                if (var4 < 0.0D) {
                    System.out.println("Regression.fitLorentzian(): This implementation of the Lorentzian distribution takes only positive y values\n(noise taking low values below zero are allowed)");
                    System.out.println("All y values have been multiplied by -1 before fitting");

                    for(int var7 = 0; var7 < this.nData; ++var7) {
                        this.yData[var7] = -this.yData[var7];
                    }

                    var3 = dataSign(this.yData);
                    var6 = true;
                }

                ArrayList var22 = dataSign(this.yData);
                Integer var8 = null;
                var8 = (Integer)var22.get(5);
                int var9 = var8;
                double var10 = this.xData[0][var9];
                double var12 = halfWidth(this.xData[0], this.yData);
                var2 = (Double)var22.get(4);
                double var14 = var2;
                var14 = var14 * var12 * 3.141592653589793D / 2.0D;
                double[] var16 = new double[this.nParam];
                double[] var17 = new double[this.nParam];
                var16[0] = var10;
                var16[1] = var12 * 0.9D;
                if (this.scaleFlag) {
                    var16[2] = var14;
                }

                var17[0] = 0.2D * var12;
                if (var17[0] == 0.0D) {
                    ArrayList var18 = dataSign(this.xData[0]);
                    Double var19 = null;
                    var19 = (Double)var18.get(2);
                    double var20 = var19;
                    if (var20 == 0.0D) {
                        var19 = (Double)var18.get(0);
                        var20 = var19;
                    }

                    var17[0] = var20 * 0.1D;
                }

                var17[1] = 0.2D * var16[1];
                if (this.scaleFlag) {
                    var17[2] = 0.2D * var16[2];
                }

                LorentzianFunction var23 = new LorentzianFunction();
                this.addConstraint(1, -1, 0.0D);
                var23.setScaleOption(this.scaleFlag);
                var23.setScaleFactor(this.yScaleFactor);
                this.simplexFlag = 1;
                this.nonLinStatsNeeded = true;
                this.dualErrorsRequired = false;
                this.nelderMead(var23, (Object)null, var16, var17, this.fTol, this.nMax);
                int var24;
                if (var1 == 1) {
                    if (!this.suppressPrint) {
                        this.print();
                    }

                    var24 = this.plotXY((RegressionFunction)var23);
                    if (var24 != -2 && !this.suppressYYplot) {
                        this.plotYY();
                    }
                }

                if (var6) {
                    for(var24 = 0; var24 < this.nData - 1; ++var24) {
                        this.yData[var24] = -this.yData[var24];
                    }
                }

            }
        }
    }

    public static void fitOneOrSeveralDistributions(double[] var0) {
        int var1 = var0.length;
        double var2 = Fmath.maximum(var0);
        double var4 = Fmath.minimum(var0);
        double var6 = var2 - var4;
        int var8 = (int)Math.ceil(Math.sqrt((double)var1));
        double var9 = var6 / (double)var8;
        double var11 = (double)var1 / (double)var8;
        String var13 = "Maximum value:  " + var2 + "\n";
        var13 = var13 + "Minimum value:  " + var4 + "\n";
        var13 = var13 + "Suggested bin width:  " + var9 + "\n";
        var13 = var13 + "Giving an average points per bin:  " + var11 + "\n";
        var13 = var13 + "If you wish to change the bin width enter the new value below \n";
        var13 = var13 + "and click on OK\n";
        var13 = var13 + "If you do NOT wish to change the bin width simply click on OK";
        var9 = Db.readDouble(var13, var9);
        var13 = "Input the name of the output text file\n";
        var13 = var13 + "[Do not forget the extension, e.g.   .txt]";
        String var14 = Db.readLine(var13, "fitOneOrSeveralDistributionsOutput.txt");
        FileOutput var15 = new FileOutput(var14, 'n');
        var15.println("Fitting a set of data to one or more distributions");
        var15.println("Class Regression/Stat: method fitAllDistributions");
        var15.dateAndTimeln();
        var15.println();
        var15.printtab("Number of points: ");
        var15.println(var1);
        var15.printtab("Minimum value: ");
        var15.println(var4);
        var15.printtab("Maximum value: ");
        var15.println(var2);
        var15.printtab("Number of bins: ");
        var15.println(var8);
        var15.printtab("Bin width: ");
        var15.println(var9);
        var15.printtab("Average number of points per bin: ");
        var15.println(var11);
        var15.println();
        String[] var16 = new String[]{"Gaussian Distribution", "Two parameter Log-normal Distribution", "Three parameter Log-normal Distribution", "Logistic Distribution", "Lorentzian Distribution", "Type 1 Extreme Distribution - Gumbel minimum order statistic", "Type 1 Extreme Distribution - Gumbel maximum order statistic", "Type 2 Extreme Distribution - Frechet", "Type 3 Extreme Distribution - Weibull", "Type 3 Extreme Distribution - Exponential Distribution", "Type 3 Extreme Distribution - Rayleigh Distribution", "Pareto Distribution", "Beta Distribution", "Gamma Distribution", "Erlang Distribution", "exit"};
        String[] var17 = new String[]{" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "exit"};
        String var18 = "Choose next distribution to be fitted by clicking on box number";
        byte var19 = 1;
        boolean var20 = true;
        Regression var21 = null;
        Object var22 = null;

        while(var20) {
            int var23 = Db.optionBox(var18, var16, var17, var19);
            double[] var24;
            switch(var23) {
                case 1:
                    var21 = new Regression(var0, var9);
                    var21.suppressPrint();
                    var21.gaussianPlot();
                    var24 = var21.getCoeff();
                    var15.println("NORMAL (GAUSSIAN) DISTRIBUTION");
                    var15.println("Best Estimates:");
                    var15.printtab("Mean [mu] ");
                    var15.println(var24[0]);
                    var15.printtab("Standard deviation [sigma] ");
                    var15.println(var24[1]);
                    var15.printtab("Scaling factor [Ao] ");
                    var15.println(var24[2]);
                    regressionDetails(var15, var21);
                    break;
                case 2:
                    var21 = new Regression(var0, var9);
                    var21.suppressPrint();
                    var21.logNormalTwoParPlot();
                    var24 = var21.getCoeff();
                    var15.println("LOG-NORMAL DISTRIBUTION (two parameter statistic)");
                    var15.println("Best Estimates:");
                    var15.printtab("Location parameter [mu] ");
                    var15.println(var24[0]);
                    var15.printtab("Shape parameter [sigma] ");
                    var15.println(var24[1]);
                    var15.printtab("Scaling factor [Ao] ");
                    var15.println(var24[2]);
                    regressionDetails(var15, var21);
                    break;
                case 3:
                    var21 = new Regression(var0, var9);
                    var21.suppressPrint();
                    var21.logNormalThreeParPlot();
                    var24 = var21.getCoeff();
                    var15.println("LOG-NORMAL DISTRIBUTION (three parameter statistic)");
                    var15.println("Best Estimates:");
                    var15.printtab("Location parameter [alpha] ");
                    var15.println(var24[0]);
                    var15.printtab("Shape parameter [beta] ");
                    var15.println(var24[1]);
                    var15.printtab("Scale parameter [gamma] ");
                    var15.println(var24[2]);
                    var15.printtab("Scaling factor [Ao] ");
                    var15.println(var24[3]);
                    regressionDetails(var15, var21);
                    break;
                case 4:
                    var21 = new Regression(var0, var9);
                    var21.suppressPrint();
                    var21.logisticPlot();
                    var24 = var21.getCoeff();
                    var15.println("LOGISTIC DISTRIBUTION");
                    var15.println("Best Estimates:");
                    var15.printtab("Location parameter [mu] ");
                    var15.println(var24[0]);
                    var15.printtab("Scale parameter [beta] ");
                    var15.println(var24[1]);
                    var15.printtab("Scaling factor [Ao] ");
                    var15.println(var24[2]);
                    regressionDetails(var15, var21);
                    break;
                case 5:
                    var21 = new Regression(var0, var9);
                    var21.suppressPrint();
                    var21.lorentzianPlot();
                    var24 = var21.getCoeff();
                    var15.println("LORENTZIAN DISTRIBUTION");
                    var15.println("Best Estimates:");
                    var15.printtab("Mean [mu] ");
                    var15.println(var24[0]);
                    var15.printtab("Half-height parameter [Gamma] ");
                    var15.println(var24[1]);
                    var15.printtab("Scaling factor [Ao] ");
                    var15.println(var24[2]);
                    regressionDetails(var15, var21);
                    break;
                case 6:
                    var21 = new Regression(var0, var9);
                    var21.suppressPrint();
                    var21.gumbelMinPlot();
                    var24 = var21.getCoeff();
                    var15.println("TYPE 1 (GUMBEL) EXTREME DISTRIBUTION [MINIMUM ORDER STATISTIC]");
                    var15.println("Best Estimates:");
                    var15.printtab("Location parameter [mu] ");
                    var15.println(var24[0]);
                    var15.printtab("Scale parameter [sigma] ");
                    var15.println(var24[1]);
                    var15.printtab("Scaling factor [Ao] ");
                    var15.println(var24[2]);
                    regressionDetails(var15, var21);
                    break;
                case 7:
                    var21 = new Regression(var0, var9);
                    var21.suppressPrint();
                    var21.gumbelMaxPlot();
                    var24 = var21.getCoeff();
                    var15.println("TYPE 1 (GUMBEL) EXTREME DISTRIBUTION [MAXIMUM ORDER STATISTIC]");
                    var15.println("Best Estimates:");
                    var15.printtab("Location parameter [mu] ");
                    var15.println(var24[0]);
                    var15.printtab("Scale parameter [sigma] ");
                    var15.println(var24[1]);
                    var15.printtab("Scaling factor [Ao] ");
                    var15.println(var24[2]);
                    regressionDetails(var15, var21);
                    break;
                case 8:
                    var21 = new Regression(var0, var9);
                    var21.suppressPrint();
                    var21.frechetPlot();
                    var24 = var21.getCoeff();
                    var15.println("TYPE 2 (FRECHET) EXTREME DISTRIBUTION");
                    var15.println("Best Estimates:");
                    var15.printtab("Location parameter [mu] ");
                    var15.println(var24[0]);
                    var15.printtab("Scale parameter [sigma] ");
                    var15.println(var24[1]);
                    var15.printtab("Shape parameter [gamma] ");
                    var15.println(var24[2]);
                    var15.printtab("Scaling factor [Ao] ");
                    var15.println(var24[3]);
                    regressionDetails(var15, var21);
                    break;
                case 9:
                    var21 = new Regression(var0, var9);
                    var21.suppressPrint();
                    var21.weibullPlot();
                    var24 = var21.getCoeff();
                    var15.println("TYPE 3 (WEIBULL) EXTREME DISTRIBUTION");
                    var15.println("Best Estimates:");
                    var15.printtab("Location parameter [mu] ");
                    var15.println(var24[0]);
                    var15.printtab("Scale parameter [sigma] ");
                    var15.println(var24[1]);
                    var15.printtab("Shape parameter [gamma] ");
                    var15.println(var24[2]);
                    var15.printtab("Scaling factor [Ao] ");
                    var15.println(var24[3]);
                    regressionDetails(var15, var21);
                    break;
                case 10:
                    var21 = new Regression(var0, var9);
                    var21.suppressPrint();
                    var21.exponentialPlot();
                    var24 = var21.getCoeff();
                    var15.println("EXPONENTIAL DISTRIBUTION");
                    var15.println("Best Estimates:");
                    var15.printtab("Location parameter [mu] ");
                    var15.println(var24[0]);
                    var15.printtab("Scale parameter [sigma] ");
                    var15.println(var24[1]);
                    var15.printtab("Scaling factor [Ao] ");
                    var15.println(var24[2]);
                    regressionDetails(var15, var21);
                    break;
                case 11:
                    var21 = new Regression(var0, var9);
                    var21.suppressPrint();
                    var21.rayleighPlot();
                    var24 = var21.getCoeff();
                    var15.println("RAYLEIGH DISTRIBUTION");
                    var15.println("Best Estimates:");
                    var15.printtab("Scale parameter [beta] ");
                    var15.println(var24[0]);
                    var15.printtab("Scaling factor [Ao] ");
                    var15.println(var24[1]);
                    regressionDetails(var15, var21);
                    break;
                case 12:
                    var21 = new Regression(var0, var9);
                    var21.suppressPrint();
                    var21.paretoThreeParPlot();
                    var24 = var21.getCoeff();
                    var15.println("PARETO DISTRIBUTION");
                    var15.println("Best Estimates:");
                    var15.printtab("Shape parameter [alpha] ");
                    var15.println(var24[0]);
                    var15.printtab("Scale parameter [beta] ");
                    var15.println(var24[1]);
                    var15.printtab("Threshold parameter [theta] ");
                    var15.println(var24[2]);
                    var15.printtab("Scaling factor [Ao] ");
                    var15.println(var24[3]);
                    regressionDetails(var15, var21);
                    break;
                case 13:
                    var21 = new Regression(var0, var9);
                    var21.suppressPrint();
                    var21.betaMinMaxPlot();
                    var24 = var21.getCoeff();
                    var15.println("BETA DISTRIBUTION");
                    var15.println("Best Estimates:");
                    var15.printtab("Shape parameter [alpha] ");
                    var15.println(var24[0]);
                    var15.printtab("Shape parameter [beta] ");
                    var15.println(var24[1]);
                    var15.printtab("minimum limit [min] ");
                    var15.println(var24[2]);
                    var15.printtab("maximum limit [max] ");
                    var15.println(var24[3]);
                    var15.printtab("Scaling factor [Ao] ");
                    var15.println(var24[4]);
                    regressionDetails(var15, var21);
                    break;
                case 14:
                    var21 = new Regression(var0, var9);
                    var21.suppressPrint();
                    var21.gammaPlot();
                    var24 = var21.getCoeff();
                    var15.println("GAMMA DISTRIBUTION");
                    var15.println("Best Estimates:");
                    var15.printtab("Location parameter [mu] ");
                    var15.println(var24[0]);
                    var15.printtab("Scale parameter [beta] ");
                    var15.println(var24[1]);
                    var15.printtab("Shape parameter [gamma] ");
                    var15.println(var24[2]);
                    var15.printtab("Scaling factor [Ao] ");
                    var15.println(var24[3]);
                    regressionDetails(var15, var21);
                    break;
                case 15:
                    var21 = new Regression(var0, var9);
                    var21.suppressPrint();
                    var21.erlangPlot();
                    var24 = var21.getCoeff();
                    var15.println("ERLANG DISTRIBUTION");
                    var15.println("Best Estimates:");
                    var15.printtab("Shape parameter [lambda] ");
                    var15.println(var24[0]);
                    var15.printtab("Rate parameter [k] ");
                    var15.println(var21.getKayValue());
                    var15.printtab("Scaling factor [Ao] ");
                    var15.println(var24[1]);
                    regressionDetails(var15, var21);
                    break;
                case 16:
                default:
                    var15.close();
                    var20 = false;
            }
        }

    }

    protected static void regressionDetails(FileOutput var0, Regression var1) {
        var0.println();
        var0.println("Regression details:");
        var0.printtab("Chi squared: ");
        var0.println(var1.getChiSquare());
        var0.printtab("Reduced chi squared: ");
        var0.println(var1.getReducedChiSquare());
        var0.printtab("Sum of squares: ");
        var0.println(var1.getSumOfSquares());
        var0.printtab("Degrees of freedom: ");
        var0.println(var1.getDegFree());
        var0.printtab("Number of iterations: ");
        var0.println(var1.getNiter());
        var0.printtab("maximum number of iterations allowed: ");
        var0.println(var1.getNmax());
        var0.println();
        var0.println();
    }

    public double getXYcorrCoeff() {
        return this.xyR;
    }

    public double getYYcorrCoeff() {
        return this.yyR;
    }

    protected static ArrayList<Object> dataSign(double[] var0) {
        ArrayList var1 = new ArrayList();
        int var2 = var0.length;
        double var3 = var0[0];
        int var5 = 0;
        double var6 = var0[0];
        int var8 = 0;
        double var9 = 0.0D;
        boolean var11 = true;
        boolean var12 = true;
        double var13 = 0.0D;
        double var15 = 0.0D;
        int var17 = 0;
        int var18 = 0;
        int var19 = 0;

        for(int var20 = 0; var20 < var2; ++var20) {
            var15 = var0[var20];
            if (var0[var20] > var3) {
                var3 = var0[var20];
                var5 = var20;
            }

            if (var0[var20] < var6) {
                var6 = var0[var20];
                var8 = var20;
            }

            if (var0[var20] == 0.0D) {
                ++var17;
            }

            if (var0[var20] > 0.0D) {
                ++var19;
            }

            if (var0[var20] < 0.0D) {
                ++var18;
            }
        }

        var15 /= (double)var2;
        int var21;
        byte var22;
        if (var17 + var19 == var2) {
            var9 = var3;
            var21 = var5;
            var22 = 0;
        } else if (var17 + var18 == var2) {
            var9 = var6;
            var21 = var8;
            var22 = 1;
        } else {
            var9 = var3;
            var21 = var5;
            if (-var6 > var3) {
                var9 = (double)var8;
            }

            var22 = 2;
            var13 = -var6;
        }

        var1.add(new Double(var6));
        var1.add(new Integer(var8));
        var1.add(new Double(var3));
        var1.add(new Integer(var5));
        var1.add(new Double(var9));
        var1.add(new Integer(var21));
        var1.add(new Integer(var22));
        var1.add(new Double(var13));
        var1.add(new Double(var15));
        var1.add(new Integer(var17));
        var1.add(new Integer(var19));
        var1.add(new Integer(var18));
        return var1;
    }

    public void frechet() {
        this.fitFrechet(0, 0);
    }

    public void frechetPlot() {
        this.fitFrechet(1, 0);
    }

    public void frechetTwoPar() {
        this.fitFrechet(0, 1);
    }

    public void frechetTwoParPlot() {
        this.fitFrechet(1, 1);
    }

    public void frechetStandard() {
        this.fitFrechet(0, 2);
    }

    public void frechetStandardPlot() {
        this.fitFrechet(1, 2);
    }

    protected void fitFrechet(int var1, int var2) {
        if (this.multipleY) {
            throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
        } else {
            this.userSupplied = false;
            switch(var2) {
                case 0:
                    this.lastMethod = 13;
                    this.nParam = 4;
                    break;
                case 1:
                    this.lastMethod = 14;
                    this.nParam = 3;
                    break;
                case 2:
                    this.lastMethod = 15;
                    this.nParam = 2;
            }

            if (!this.scaleFlag) {
                --this.nParam;
            }

            this.frechetWeibull = true;
            this.fitFrechetWeibull(var1, var2);
        }
    }

    protected void fitFrechetWeibull(int var1, int var2) {
        if (this.multipleY) {
            throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
        } else {
            this.linNonLin = false;
            this.zeroCheck = false;
            this.degreesOfFreedom = this.nData - this.nParam;
            if (this.degreesOfFreedom < 1 && !this.ignoreDofFcheck) {
                throw new IllegalArgumentException("Degrees of freedom must be greater than 0");
            } else {
                sort(this.xData[0], this.yData, this.weight);
                Double var3 = null;
                ArrayList var4 = dataSign(this.yData);
                var3 = (Double)var4.get(4);
                double var5 = var3;
                Integer var7 = null;
                var7 = (Integer)var4.get(5);
                int var8 = var7;
                var3 = (Double)var4.get(8);
                double var9 = var3;
                boolean var11 = true;
                double var12 = (double)this.degreesOfFreedom;

                while(var11) {
                    if (this.infinityCheck(var5, var8)) {
                        --var12;
                        if (var12 < 1.0D && !this.ignoreDofFcheck) {
                            throw new IllegalArgumentException("The effective degrees of freedom have been reduced to zero");
                        }

                        var4 = dataSign(this.yData);
                        var3 = (Double)var4.get(4);
                        var5 = var3;
                        var7 = (Integer)var4.get(5);
                        var8 = var7;
                        var3 = (Double)var4.get(8);
                        var9 = var3;
                    } else {
                        var11 = false;
                    }
                }

                String var14 = "Weibull";
                if (this.frechetWeibull) {
                    var14 = "Frechet";
                }

                boolean var15 = false;
                if (var5 < 0.0D) {
                    this.reverseYsign(var14);
                    var4 = dataSign(this.yData);
                    var5 = -var5;
                    var15 = true;
                }

                boolean var16 = false;
                double var17 = this.checkYallSmall(var5, var14);
                if (var17 != 1.0D) {
                    var16 = true;
                    var5 = 1.0D;
                }

                ArrayList var19 = dataSign(this.xData[0]);
                var3 = (Double)var19.get(0);
                double var20 = var3;
                var3 = (Double)var19.get(2);
                double var22 = var3;
                double var24 = this.xData[0][var8];
                double var26 = Math.log(2.0D) * halfWidth(this.xData[0], this.yData);
                double[] var28 = new double[this.nData];
                double[] var29 = new double[this.nData];
                double[] var30 = new double[this.nData];

                for(int var31 = 0; var31 < this.nData; ++var31) {
                    var28[var31] = this.xData[0][var31];
                    var29[var31] = this.yData[var31];
                    var30[var31] = this.weight[var31];
                }

                double[] var49 = new double[this.nData];
                double[] var32 = new double[this.nData];
                double[] var33 = new double[this.nData];
                ErrorProp[] var34 = ErrorProp.oneDarray(this.nData);
                double var35 = this.calculateCumulativeValues(var49, var32, var33, var34, var8, var5, var24, var14);
                int var37;
                if (this.frechetWeibull) {
                    for(var37 = 0; var37 < this.nData; ++var37) {
                        var34[var37] = ErrorProp.over(1.0D, var34[var37]);
                        var34[var37] = ErrorProp.log(var34[var37]);
                        var34[var37] = ErrorProp.log(var34[var37]);
                        var32[var37] = var34[var37].getValue();
                        var33[var37] = var34[var37].getError();
                    }
                } else {
                    for(var37 = 0; var37 < this.nData; ++var37) {
                        var34[var37] = ErrorProp.minus(1.0D, var34[var37]);
                        var34[var37] = ErrorProp.over(1.0D, var34[var37]);
                        var34[var37] = ErrorProp.log(var34[var37]);
                        var34[var37] = ErrorProp.log(var34[var37]);
                        var32[var37] = var34[var37].getValue();
                        var33[var37] = var34[var37].getError();
                    }
                }

                for(var37 = 0; var37 < this.nData; ++var37) {
                    this.xData[0][var37] = var49[var37];
                    this.yData[var37] = var32[var37];
                    this.weight[var37] = var33[var37];
                }

                boolean var50 = this.weightOpt;
                this.weightOpt = true;
                boolean var38 = this.statFlag;
                this.statFlag = false;
                double[] var39 = new double[this.nParam];
                double[] var40 = new double[this.nParam];

                for(int var41 = 0; var41 < this.nParam; ++var41) {
                    var39[var41] = 1.0D;
                    var40[var41] = 0.2D;
                }

                Object var51 = null;
                double var42 = 0.0D;
                switch(var2) {
                    case 0:
                        var39[0] = var20 - Math.abs(0.1D * var20);
                        var39[1] = var26;
                        var39[2] = 4.0D;
                        var40[0] = 0.2D * var39[0];
                        if (var40[0] == 0.0D) {
                            ArrayList var44 = dataSign(this.xData[0]);
                            Double var45 = null;
                            var45 = (Double)var44.get(2);
                            double var46 = var45;
                            if (var46 == 0.0D) {
                                var45 = (Double)var44.get(0);
                                var46 = var45;
                            }

                            var40[0] = var46 * 0.1D;
                        }

                        var40[1] = 0.2D * var39[1];
                        var40[2] = 0.5D * var39[2];
                        this.addConstraint(0, 1, var20);
                        this.addConstraint(1, -1, 0.0D);
                        this.addConstraint(2, -1, 0.0D);
                        break;
                    case 1:
                        var39[0] = var26;
                        var39[1] = 4.0D;
                        var40[0] = 0.2D * var39[0];
                        var40[1] = 0.5D * var39[1];
                        this.addConstraint(0, -1, 0.0D);
                        this.addConstraint(1, -1, 0.0D);
                        break;
                    case 2:
                        var39[0] = 4.0D;
                        var40[0] = 0.5D * var39[0];
                        this.addConstraint(0, -1, 0.0D);
                }

                if (this.frechetWeibull) {
                    FrechetFunctionTwo var52 = new FrechetFunctionTwo();
                    var52.setTypeFlag(var2);
                    this.simplexFlag = 1;
                    this.nonLinStatsNeeded = true;
                    this.dualErrorsRequired = false;
                    this.nelderMead(var52, (Object)null, var39, var40, this.fTol, this.nMax);
                } else {
                    WeibullFunctionTwo var53 = new WeibullFunctionTwo();
                    var53.setTypeFlag(var2);
                    this.simplexFlag = 1;
                    this.nonLinStatsNeeded = true;
                    this.dualErrorsRequired = false;
                    this.nelderMead(var53, (Object)null, var39, var40, this.fTol, this.nMax);
                }

                double[] var54 = Conv.copy(this.best);
                this.statFlag = var38;
                this.weightOpt = var50;

                int var55;
                for(var55 = 0; var55 < this.nData; ++var55) {
                    this.xData[0][var55] = var28[var55];
                    this.yData[var55] = var29[var55];
                    this.weight[var55] = var30[var55];
                }

                switch(var2) {
                    case 0:
                        var39[0] = var54[0];
                        var39[1] = var54[1];
                        var39[2] = var54[2];
                        if (this.scaleFlag) {
                            var39[3] = 1.0D / var35;
                        }

                        var40[0] = 0.1D * var39[0];
                        if (var40[0] == 0.0D) {
                            ArrayList var56 = dataSign(this.xData[0]);
                            Double var58 = null;
                            var58 = (Double)var56.get(2);
                            double var47 = var58;
                            if (var47 == 0.0D) {
                                var58 = (Double)var56.get(0);
                                var47 = var58;
                            }

                            var40[0] = var47 * 0.1D;
                        }

                        var40[1] = 0.1D * var39[1];
                        var40[2] = 0.1D * var39[2];
                        if (this.scaleFlag) {
                            var40[3] = 0.1D * var39[3];
                        }
                        break;
                    case 1:
                        var39[0] = var54[0];
                        var39[1] = var54[1];
                        if (this.scaleFlag) {
                            var39[2] = 1.0D / var35;
                        }

                        var40[0] = 0.1D * var39[0];
                        var40[1] = 0.1D * var39[1];
                        if (this.scaleFlag) {
                            var40[2] = 0.1D * var39[2];
                        }
                        break;
                    case 2:
                        var39[0] = var54[0];
                        if (this.scaleFlag) {
                            var39[1] = 1.0D / var35;
                        }

                        var40[0] = 0.1D * var39[0];
                        if (this.scaleFlag) {
                            var40[1] = 0.1D * var39[1];
                        }
                }

                int var60;
                if (this.frechetWeibull) {
                    FrechetFunctionOne var57 = new FrechetFunctionOne();
                    var57.setScaleOption(this.scaleFlag);
                    var57.setScaleFactor(this.yScaleFactor);
                    var57.setTypeFlag(var2);
                    this.simplexFlag = 1;
                    this.nonLinStatsNeeded = true;
                    this.dualErrorsRequired = false;
                    this.nelderMead(var57, (Object)null, var39, var40, this.fTol, this.nMax);
                    if (var1 == 1) {
                        if (!this.suppressPrint) {
                            this.print();
                        }

                        var60 = this.plotXY((RegressionFunction)var57);
                        if (var60 != -2 && !this.suppressYYplot) {
                            this.plotYY();
                        }
                    }
                } else {
                    WeibullFunctionOne var59 = new WeibullFunctionOne();
                    var59.setScaleOption(this.scaleFlag);
                    var59.setScaleFactor(this.yScaleFactor);
                    var59.setTypeFlag(var2);
                    this.simplexFlag = 1;
                    this.nonLinStatsNeeded = true;
                    this.dualErrorsRequired = false;
                    this.nelderMead(var59, (Object)null, var39, var40, this.fTol, this.nMax);
                    if (var1 == 1) {
                        if (!this.suppressPrint) {
                            this.print();
                        }

                        var60 = this.plotXY((RegressionFunction)var59);
                        if (var60 != -2 && !this.suppressYYplot) {
                            this.plotYY();
                        }
                    }
                }

                this.weightOpt = var50;
                if (var16) {
                    for(var55 = 0; var55 < this.nData; ++var55) {
                        this.yData[var55] = var29[var55] / var17;
                        if (this.weightOpt) {
                            this.weight[var55] = var30[var55] / var17;
                        }
                    }
                }

                if (var15) {
                    for(var55 = 0; var55 < this.nData; ++var55) {
                        this.yData[var55] = -this.yData[var55];
                    }
                }

            }
        }
    }

    public boolean infinityCheck(double var1, int var3) {
        boolean var4 = false;
        if (var1 == 1.0D / 0.0 || var1 == -1.0D / 0.0) {
            int var5 = var3 + 1;
            if (var3 == this.nData - 1) {
                var5 = var3 - 1;
            }

            this.xData[0][var3] = this.xData[0][var5];
            this.yData[var3] = this.yData[var5];
            this.weight[var3] = this.weight[var5];
            System.out.println("An infinty has been removed at point " + var3);
            var4 = true;
        }

        return var4;
    }

    public void reverseYsign(String var1) {
        System.out.println("This implementation of the " + var1 + " distributions takes only positive y values\n(noise taking low values below zero are allowed)");
        System.out.println("All y values have been multiplied by -1 before fitting");

        for(int var2 = 0; var2 < this.nData; ++var2) {
            this.yData[var2] = -this.yData[var2];
        }

    }

    public double checkYallSmall(double var1, String var3) {
        double var4 = 1.0D;
        double var6 = Fmath.truncate(1.0D / var1, 4);
        if (var1 < 1.0E-4D) {
            System.out.println(var3 + " fitting: The ordinate axis (y axis) has been rescaled by " + var6 + " to reduce rounding errors");

            for(int var8 = 0; var8 < this.nData; ++var8) {
                this.yData[var8] *= var6;
                if (this.weightOpt) {
                    this.weight[var8] *= var6;
                }
            }

            var4 = var6;
        }

        return var4;
    }

    public double calculateCumulativeValues(double[] var1, double[] var2, double[] var3, ErrorProp[] var4, int var5, double var6, double var8, String var10) {
        var1[0] = this.xData[0][0];

        for(int var11 = 1; var11 < this.nData; ++var11) {
            var1[var11] = this.xData[0][var11];
        }

        ErrorProp[] var18 = ErrorProp.oneDarray(this.nData);

        int var12;
        for(var12 = 0; var12 < this.nData; ++var12) {
            var18[var12].reset(this.yData[var12], this.weight[var12]);
        }

        ErrorProp var19;
        if (var5 != 0) {
            if (var5 == this.nData - 1) {
                System.out.println("The data does not cover a wide enough range of x values to fit to a " + var10 + " distribution with any accuracy");
                System.out.println("The regression will be attempted but you should treat any result with great caution");
            }

            if (this.yData[0] < this.yData[1] * 0.5D && this.yData[0] > var8 * 0.02D) {
                new ErrorProp(0.0D, 0.0D);
                var19 = var18[0].times(this.xData[0][1] - this.xData[0][0]);
                var19 = var19.over(var18[1].minus(var18[0]));
                var19 = ErrorProp.minus(this.xData[0][0], var19);
                if (this.yData[0] >= 0.9D * var6) {
                    var19 = var19.plus(this.xData[0][0]).over(2.0D);
                }

                if (var19.getValue() < 0.0D) {
                    var19.reset(0.0D, 0.0D);
                }

                var4[0] = var18[0].over(2.0D);
                var4[0] = var4[0].times(ErrorProp.minus(this.xData[0][0], var19));
            } else {
                var4[0].reset(0.0D, this.weight[0]);
            }
        } else {
            var4[0].reset(0.0D, this.weight[0]);
        }

        for(var12 = 1; var12 < this.nData; ++var12) {
            var4[var12] = var18[var12].plus(var18[var12 - 1]);
            var4[var12] = var4[var12].over(2.0D);
            var4[var12] = var4[var12].times(this.xData[0][var12] - this.xData[0][var12 - 1]);
            var4[var12] = var4[var12].plus(var4[var12 - 1]);
        }

        var19 = var4[this.nData - 1].copy();
        if (var5 == this.nData - 1) {
            var19 = var19.times(2.0D);
        } else if (this.yData[this.nData - 1] < this.yData[this.nData - 2] * 0.5D && this.yData[this.nData - 1] > var8 * 0.02D) {
            new ErrorProp();
            ErrorProp var13 = var18[this.nData - 1].times(this.xData[0][this.nData - 2] - this.xData[0][this.nData - 1]);
            var13 = var13.over(var18[this.nData - 2].minus(var18[this.nData - 1]));
            var13 = ErrorProp.minus(this.xData[0][this.nData - 1], var13);
            if (this.yData[0] >= 0.9D * var6) {
                var13 = var13.plus(this.xData[0][this.nData - 1]).over(2.0D);
            }

            var19 = var19.plus(ErrorProp.times(0.5D, var18[this.nData - 1].times(var13.minus(this.xData[0][this.nData - 1]))));
        }

        for(int var20 = 0; var20 < this.nData; ++var20) {
            var2[var20] = var4[var20].getValue();
            var3[var20] = var4[var20].getError();
        }

        double var21 = 1.0D / var19.getValue();

        int var15;
        for(var15 = 0; var15 < this.nData; ++var15) {
            var4[var15] = var4[var15].over(var19);
        }

        var15 = 0;
        boolean var16 = true;

        int var17;
        for(var17 = 0; var17 < this.nData; ++var17) {
            if (var4[var17].getValue() <= 0.0D) {
                if (var17 <= var15) {
                    var16 = true;
                    var15 = var17;

                    while(var16) {
                        ++var15;
                        if (var15 >= this.nData) {
                            throw new ArithmeticException("all zero cumulative data!!");
                        }

                        if (var4[var15].getValue() > 0.0D) {
                            var4[var17] = var4[var15].copy();
                            var1[var17] = var1[var15];
                            var16 = false;
                        }
                    }
                } else if (var17 == this.nData - 1) {
                    var4[var17] = var4[var17 - 1].copy();
                    var1[var17] = var1[var17 - 1];
                } else {
                    var4[var17] = var4[var17 - 1].plus(var4[var17 + 1]);
                    var4[var17] = var4[var17].over(2.0D);
                    var1[var17] = (var1[var17 - 1] + var1[var17 + 1]) / 2.0D;
                }
            }
        }

        var15 = this.nData - 1;

        for(var17 = this.nData - 1; var17 >= 0; --var17) {
            if (var4[var17].getValue() >= 1.0D) {
                if (var17 >= var15) {
                    var16 = true;
                    var15 = this.nData - 1;

                    while(var16) {
                        --var15;
                        if (var15 < 0) {
                            throw new ArithmeticException("all unity cumulative data!!");
                        }

                        if (var4[var15].getValue() < 1.0D) {
                            var4[var17] = var4[var15].copy();
                            var1[var17] = var1[var15];
                            var16 = false;
                        }
                    }
                } else if (var17 == 0) {
                    var4[var17] = var4[var17 + 1].copy();
                    var1[var17] = var1[var17 + 1];
                } else {
                    var4[var17] = var4[var17 - 1].plus(var4[var17 + 1]);
                    var4[var17] = var4[var17].over(2.0D);
                    var1[var17] = (var1[var17 - 1] + var1[var17 + 1]) / 2.0D;
                }
            }
        }

        return var21;
    }

    public void weibull() {
        this.fitWeibull(0, 0);
    }

    public void weibullPlot() {
        this.fitWeibull(1, 0);
    }

    public void weibullTwoPar() {
        this.fitWeibull(0, 1);
    }

    public void weibullTwoParPlot() {
        this.fitWeibull(1, 1);
    }

    public void weibullStandard() {
        this.fitWeibull(0, 2);
    }

    public void weibullStandardPlot() {
        this.fitWeibull(1, 2);
    }

    protected void fitWeibull(int var1, int var2) {
        if (this.multipleY) {
            throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
        } else {
            this.userSupplied = false;
            switch(var2) {
                case 0:
                    this.lastMethod = 16;
                    this.nParam = 4;
                    break;
                case 1:
                    this.lastMethod = 17;
                    this.nParam = 3;
                    break;
                case 2:
                    this.lastMethod = 18;
                    this.nParam = 2;
            }

            if (!this.scaleFlag) {
                --this.nParam;
            }

            this.frechetWeibull = false;
            this.fitFrechetWeibull(var1, var2);
        }
    }

    public void gumbelMin() {
        this.fitGumbel(0, 0);
    }

    public void gumbelMinPlot() {
        this.fitGumbel(1, 0);
    }

    public void gumbelMax() {
        this.fitGumbel(0, 1);
    }

    public void gumbelMaxPlot() {
        this.fitGumbel(1, 1);
    }

    public void gumbelMinOnePar() {
        this.fitGumbel(0, 2);
    }

    public void gumbelMinOneParPlot() {
        this.fitGumbel(1, 2);
    }

    public void gumbelMaxOnePar() {
        this.fitGumbel(0, 3);
    }

    public void gumbelMaxOneParPlot() {
        this.fitGumbel(1, 3);
    }

    public void gumbelMinStandard() {
        this.fitGumbel(0, 4);
    }

    public void gumbelMinStandardPlot() {
        this.fitGumbel(1, 4);
    }

    public void gumbelMaxStandard() {
        this.fitGumbel(0, 5);
    }

    public void gumbelMaxStandardPlot() {
        this.fitGumbel(1, 5);
    }

    protected void noParameters(String var1) {
        FileOutput var4;
        System.out.println(var1 + " Regression");
        System.out.println("No parameters set for estimation");
        System.out.println("Theoretical curve obtained");
        String var2 = "RegressOutput.txt";
        String var3 = "RegressOutputN.txt";
        var4 = new FileOutput(var2, 'n');
        System.out.println("Results printed to the file " + var3);
        var4.dateAndTimeln(var2);
        var4.println("No parameters set for estimation");
        int var5;
        label66:
        switch(this.lastMethod) {
            case 11:
                var4.println("Minimal Standard Gumbel p(x) = exp(x)exp(-exp(x))");
                var5 = 0;

                while(true) {
                    if (var5 >= this.nData) {
                        break label66;
                    }

                    this.yCalc[var5] = Math.exp(this.xData[0][var5]) * Math.exp(-Math.exp(this.xData[0][var5]));
                    ++var5;
                }
            case 12:
                var4.println("Maximal Standard Gumbel p(x) = exp(-x)exp(-exp(-x))");
                var5 = 0;

                while(true) {
                    if (var5 >= this.nData) {
                        break label66;
                    }

                    this.yCalc[var5] = Math.exp(-this.xData[0][var5]) * Math.exp(-Math.exp(-this.xData[0][var5]));
                    ++var5;
                }
            case 21:
                var4.println("Standard Exponential p(x) = exp(-x)");

                for(var5 = 0; var5 < this.nData; ++var5) {
                    this.yCalc[var5] = Math.exp(-this.xData[0][var5]);
                }
        }

        this.sumOfSquaresError = 0.0D;
        this.chiSquare = 0.0D;
        double var10 = 0.0D;

        for(int var7 = 0; var7 < this.nData; ++var7) {
            var10 = Fmath.square(this.yData[var7] - this.yCalc[var7]);
            this.sumOfSquaresError += var10;
            this.chiSquare += var10 / Fmath.square(this.weight[var7]);
        }

        double var11 = Stat.corrCoeff(this.yData, this.yCalc);
        var4.printtab("Correlation Coefficient");
        var4.println(Fmath.truncate(var11, this.prec));
        if (Math.abs(var11) <= 1.0D) {
            var4.printtab("Correlation Coefficient Probability");
            var4.println(Fmath.truncate(1.0D - Stat.linearCorrCoeffProb(var11, this.degreesOfFreedom - 1), this.prec));
        }

        var4.printtab("Sum of Squares");
        var4.println(Fmath.truncate(this.sumOfSquaresError, this.prec));
        if (this.weightOpt || this.trueFreq) {
            var4.printtab("Chi Square");
            var4.println(Fmath.truncate(this.chiSquare, this.prec));
            var4.printtab("chi square probability");
            var4.println(Fmath.truncate(Stat.chiSquareProb(this.chiSquare, this.degreesOfFreedom - 1), this.prec));
        }

        var4.println(" ");
        var4.printtab("x", this.field);
        var4.printtab("p(x) [expl]", this.field);
        var4.printtab("p(x) [calc]", this.field);
        var4.println("residual");

        for(int var9 = 0; var9 < this.nData; ++var9) {
            var4.printtab(Fmath.truncate(this.xData[0][var9], this.prec), this.field);
            var4.printtab(Fmath.truncate(this.yData[var9], this.prec), this.field);
            var4.printtab(Fmath.truncate(this.yCalc[var9], this.prec), this.field);
            var4.println(Fmath.truncate(this.yData[var9] - this.yCalc[var9], this.prec));
        }

        var4.close();
        this.plotXY();
        if (!this.suppressYYplot) {
            this.plotYY();
        }

    }

    protected void fitGumbel(int var1, int var2) {
        if (this.multipleY) {
            throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
        } else {
            this.userSupplied = false;
            switch(var2) {
                case 0:
                    this.lastMethod = 7;
                    this.nParam = 3;
                    break;
                case 1:
                    this.lastMethod = 8;
                    this.nParam = 3;
                    break;
                case 2:
                    this.lastMethod = 9;
                    this.nParam = 2;
                    break;
                case 3:
                    this.lastMethod = 10;
                    this.nParam = 2;
                    break;
                case 4:
                    this.lastMethod = 11;
                    this.nParam = 1;
                    break;
                case 5:
                    this.lastMethod = 12;
                    this.nParam = 1;
            }

            if (!this.scaleFlag) {
                --this.nParam;
            }

            this.zeroCheck = false;
            this.degreesOfFreedom = this.nData - this.nParam;
            if (this.degreesOfFreedom < 1 && !this.ignoreDofFcheck) {
                throw new IllegalArgumentException("Degrees of freedom must be greater than 0");
            } else {
                if (this.nParam == 0) {
                    this.noParameters("Gumbel");
                } else {
                    sort(this.xData[0], this.yData, this.weight);
                    Double var3 = null;
                    ArrayList var4 = dataSign(this.yData);
                    var3 = (Double)var4.get(4);
                    double var5 = var3;
                    boolean var7 = false;
                    if (var5 < 0.0D) {
                        System.out.println("Regression.fitGumbel(): This implementation of the Gumbel distribution takes only positive y values\n(noise taking low values below zero are allowed)");
                        System.out.println("All y values have been multiplied by -1 before fitting");

                        for(int var8 = 0; var8 < this.nData; ++var8) {
                            this.yData[var8] = -this.yData[var8];
                        }

                        var4 = dataSign(this.yData);
                        var7 = true;
                    }

                    ArrayList var22 = dataSign(this.xData[0]);
                    Integer var9 = null;
                    var9 = (Integer)var4.get(5);
                    int var10 = var9;
                    double var11 = this.xData[0][var10];
                    double var13 = halfWidth(this.xData[0], this.yData);
                    double[] var15 = new double[this.nParam];
                    double[] var16 = new double[this.nParam];
                    double var19;
                    switch(var2) {
                        case 0:
                        case 1:
                            var15[0] = var11;
                            var15[1] = var13 * Math.sqrt(6.0D) / 3.141592653589793D;
                            if (this.scaleFlag) {
                                var15[2] = var5 * var15[1] * Math.exp(1.0D);
                            }

                            var16[0] = 0.1D * var15[0];
                            if (var16[0] == 0.0D) {
                                ArrayList var17 = dataSign(this.xData[0]);
                                Double var18 = null;
                                var18 = (Double)var17.get(2);
                                var19 = var18;
                                if (var19 == 0.0D) {
                                    var18 = (Double)var17.get(0);
                                    var19 = var18;
                                }

                                var16[0] = var19 * 0.1D;
                            }

                            var16[1] = 0.1D * var15[1];
                            if (this.scaleFlag) {
                                var16[2] = 0.1D * var15[2];
                            }

                            this.addConstraint(1, -1, 0.0D);
                            break;
                        case 2:
                        case 3:
                            var15[0] = var13 * Math.sqrt(6.0D) / 3.141592653589793D;
                            if (this.scaleFlag) {
                                var15[1] = var5 * var15[0] * Math.exp(1.0D);
                            }

                            var16[0] = 0.1D * var15[0];
                            if (this.scaleFlag) {
                                var16[1] = 0.1D * var15[1];
                            }

                            this.addConstraint(0, -1, 0.0D);
                            break;
                        case 4:
                        case 5:
                            if (this.scaleFlag) {
                                var15[0] = var5 * Math.exp(1.0D);
                                var16[0] = 0.1D * var15[0];
                            }
                    }

                    GumbelFunction var23 = new GumbelFunction();
                    var23.setTypeFlag(var2);
                    var23.setScaleOption(this.scaleFlag);
                    var23.setScaleFactor(this.yScaleFactor);
                    if (var2 < 4) {
                        this.simplexFlag = 1;
                        this.nonLinStatsNeeded = true;
                        this.dualErrorsRequired = false;
                        this.nelderMead(var23, (Object)null, var15, var16, this.fTol, this.nMax);
                        if (var1 == 1) {
                            if (!this.suppressPrint) {
                                this.print();
                            }

                            int var26 = this.plotXY((RegressionFunction)var23);
                            if (var26 != -2 && !this.suppressYYplot) {
                                this.plotYY();
                            }
                        }
                    } else {
                        double[][] var24 = new double[1][this.nData];
                        var19 = 1.0D;
                        if (var2 == 5) {
                            var19 = -1.0D;
                        }

                        for(int var21 = 0; var21 < this.nData; ++var21) {
                            var24[0][var21] = Math.exp(var19 * this.xData[0][var21]) * Math.exp(-Math.exp(var19 * this.xData[0][var21]));
                        }

                        this.linNonLin = true;
                        this.generalLinear(var24);
                        if (!this.suppressPrint) {
                            this.print();
                        }

                        if (!this.suppressYYplot) {
                            this.plotYY();
                        }

                        this.plotXY();
                        this.linNonLin = false;
                    }

                    if (var7) {
                        for(int var25 = 0; var25 < this.nData - 1; ++var25) {
                            this.yData[var25] = -this.yData[var25];
                        }
                    }
                }

            }
        }
    }

    protected static void sort(double[] var0, double[] var1, double[] var2) {
        boolean var3 = false;
        int var4 = -1;
        int var5 = var0.length;
        double var6 = 0.0D;
        double var8 = 0.0D;

        for(double var10 = 0.0D; var4 < var5 - 1; var2[var4] = var10) {
            int var13 = var4 + 1;

            for(int var12 = var4 + 2; var12 < var5; ++var12) {
                if (var0[var12] < var0[var13]) {
                    var13 = var12;
                }
            }

            ++var4;
            var6 = var0[var13];
            var0[var13] = var0[var4];
            var0[var4] = var6;
            var8 = var1[var13];
            var1[var13] = var1[var4];
            var1[var4] = var8;
            var10 = var2[var13];
            var2[var13] = var2[var4];
        }

    }

    protected static double halfWidth(double[] var0, double[] var1) {
        double var2 = var1[0];
        int var4 = 0;
        int var5 = var0.length;

        for(int var6 = 1; var6 < var5; ++var6) {
            if (var1[var6] > var2) {
                var2 = var1[var6];
                var4 = var6;
            }
        }

        var2 /= 2.0D;
        double var20 = -1.0D;
        double var8 = -1.0D;
        double var10 = -1.0D;
        int var12 = -1;
        if (var4 > 0) {
            var12 = var4 - 1;
            var8 = Math.abs(var2 - var1[var12]);

            for(int var13 = var4 - 2; var13 >= 0; --var13) {
                var10 = Math.abs(var2 - var1[var13]);
                if (var10 < var8) {
                    var8 = var10;
                    var12 = var13;
                }
            }

            var20 = Math.abs(var0[var12] - var0[var4]);
        }

        double var21 = -1.0D;
        double var15 = -1.0D;
        var10 = -1.0D;
        int var17 = -1;
        if (var4 < var5 - 1) {
            var17 = var4 + 1;
            var15 = Math.abs(var2 - var1[var17]);

            for(int var18 = var4 + 2; var18 < var5; ++var18) {
                var10 = Math.abs(var2 - var1[var18]);
                if (var10 < var15) {
                    var15 = var10;
                    var17 = var18;
                }
            }

            var21 = Math.abs(var0[var17] - var0[var4]);
        }

        double var22 = 0.0D;
        if (var12 != -1) {
            var22 += var20;
        }

        if (var17 != -1) {
            var22 += var21;
        }

        return var22;
    }

    public void exponentialSimple() {
        this.fitsexponentialSimple(0);
    }

    public void exponentialSimplePlot() {
        this.fitsexponentialSimple(1);
    }

    protected void fitsexponentialSimple(int var1) {
        if (this.multipleY) {
            throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
        } else {
            this.lastMethod = 43;
            this.userSupplied = false;
            this.linNonLin = false;
            this.zeroCheck = false;
            this.nParam = 2;
            if (!this.scaleFlag) {
                this.nParam = 1;
            }

            this.degreesOfFreedom = this.nData - this.nParam;
            if (this.degreesOfFreedom < 1 && !this.ignoreDofFcheck) {
                throw new IllegalArgumentException("Degrees of freedom must be greater than 0");
            } else {
                sort(this.xData[0], this.yData, this.weight);
                int var2 = this.yData.length;
                int var3 = var2;
                boolean[] var4 = new boolean[var2];

                for(int var5 = 0; var5 < var2; ++var5) {
                    var4[var5] = true;
                    if (this.xData[0][var5] <= 0.0D || this.yData[var5] <= 0.0D) {
                        var4[var5] = false;
                        --var3;
                    }
                }

                double[] var15 = new double[var3];
                double[] var6 = new double[var3];
                double[] var7 = new double[var3];
                int var8 = 0;

                for(int var9 = 0; var9 < var2; ++var9) {
                    if (var4[var9]) {
                        var15[var8] = Math.log(this.xData[0][var9]);
                        var6[var8] = Math.log(this.yData[var9]);
                        var7[var8] = Math.abs(this.weight[var9] / this.yData[var9]);
                        ++var8;
                    }
                }

                Regression var16 = new Regression(var15, var6, var7);
                double[] var10 = new double[this.nParam];
                double[] var11 = new double[this.nParam];
                double[] var12;
                double[] var13;
                if (this.scaleFlag) {
                    var16.linear();
                    var12 = var16.getBestEstimates();
                    var13 = var16.getBestEstimatesErrors();
                    var10[0] = var12[1];
                    var10[1] = Math.exp(var12[0]);
                    var11[0] = var13[1] / 2.0D;
                    var11[1] = var13[0] * var10[0] / 2.0D;
                    if (var11[0] <= 0.0D || Fmath.isNaN(var11[0])) {
                        var11[0] = Math.abs(var10[0] * 0.1D);
                    }

                    if (var11[1] <= 0.0D || Fmath.isNaN(var11[1])) {
                        var11[1] = Math.abs(var10[1] * 0.1D);
                    }
                } else {
                    var16.linearGeneral();
                    var12 = var16.getBestEstimates();
                    var13 = var16.getBestEstimatesErrors();
                    var10[0] = var12[1];
                    var11[0] = var13[1] / 2.0D;
                    if (var11[0] <= 0.0D || Fmath.isNaN(var11[0])) {
                        var11[0] = Math.abs(var10[0] * 0.1D);
                    }
                }

                ExponentialSimpleFunction var17 = new ExponentialSimpleFunction();
                var17.setScaleOption(this.scaleFlag);
                var17.setScaleFactor(this.yScaleFactor);
                this.simplexFlag = 1;
                this.nonLinStatsNeeded = true;
                this.dualErrorsRequired = false;
                this.nelderMead(var17, (Object)null, var10, var11, this.fTol, this.nMax);
                if (var1 == 1) {
                    if (!this.suppressPrint) {
                        this.print();
                    }

                    int var14 = this.plotXY((RegressionFunction)var17);
                    if (var14 != -2 && !this.suppressYYplot) {
                        this.plotYY();
                    }
                }

            }
        }
    }

    public void exponentialMultiple(int var1) {
        this.userSupplied = false;
        this.fitsexponentialMultiple(var1, 0);
    }

    public void exponentialMultiplePlot(int var1) {
        this.userSupplied = false;
        this.fitsexponentialMultiple(var1, 1);
    }

    public void exponentialMultiple(int var1, double[] var2) {
        this.userSupplied = true;
        this.fitsexponentialMultiple(var1, 0, var2);
    }

    public void exponentialMultiplePlot(int var1, double[] var2) {
        this.userSupplied = true;
        this.fitsexponentialMultiple(var1, 1, var2);
    }

    protected void fitsexponentialMultiple(int var1, int var2) {
        if (this.multipleY) {
            throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
        } else {
            this.lastMethod = 44;
            this.linNonLin = false;
            this.zeroCheck = false;
            this.nParam = 2 * var1;
            this.degreesOfFreedom = this.nData - this.nParam;
            if (this.degreesOfFreedom < 1 && !this.ignoreDofFcheck) {
                throw new IllegalArgumentException("Degrees of freedom must be greater than 0");
            } else {
                sort(this.xData[0], this.yData, this.weight);
                int var3 = this.yData.length;
                int var4 = var3;
                boolean[] var5 = new boolean[var3];

                for(int var6 = 0; var6 < var3; ++var6) {
                    var5[var6] = true;
                    if (this.xData[0][var6] <= 0.0D || this.yData[var6] <= 0.0D) {
                        var5[var6] = false;
                        --var4;
                    }
                }

                double[] var18 = new double[var4];
                double[] var7 = new double[var4];
                double[] var8 = new double[var4];
                int var9 = 0;

                for(int var10 = 0; var10 < var3; ++var10) {
                    if (var5[var10]) {
                        var18[var9] = Math.log(this.xData[0][var10]);
                        var7[var9] = Math.log(this.yData[var10]);
                        var8[var9] = Math.abs(this.weight[var10] / this.yData[var10]);
                        ++var9;
                    }
                }

                Regression var19 = new Regression(var18, var7, var8);
                double[] var11 = new double[this.nParam];
                double[] var12 = new double[this.nParam];
                var19.linear();
                double[] var13 = var19.getBestEstimates();
                double[] var14 = var19.getBestEstimatesErrors();

                for(int var15 = 0; var15 < this.nParam; var15 += 2) {
                    var11[var15] = Math.exp(var13[0]) / (double)this.nParam;
                    var11[var15 + 1] = var13[1];
                    var12[var15] = var14[0] * var11[var15] / 2.0D;
                    var12[var15 + 1] = var14[1] / 2.0D;
                    if (var12[var15] <= 0.0D || Fmath.isNaN(var12[var15])) {
                        var12[var15] = Math.abs(var11[var15] * 0.1D);
                    }

                    if (var12[var15 + 1] <= 0.0D || Fmath.isNaN(var12[var15 + 1])) {
                        var12[var15 + 1] = Math.abs(var11[var15 + 1] * 0.1D);
                    }
                }

                ExponentialMultipleFunction var20 = new ExponentialMultipleFunction();
                var20.setNexps(this.nParam);
                this.simplexFlag = 1;
                this.nonLinStatsNeeded = true;
                this.dualErrorsRequired = false;
                this.nelderMead(var20, (Object)null, var11, var12, this.fTol, this.nMax);
                if (var2 == 1) {
                    if (!this.suppressPrint) {
                        this.print();
                    }

                    int var17 = this.plotXY((RegressionFunction)var20);
                    if (var17 != -2 && !this.suppressYYplot) {
                        this.plotYY();
                    }
                }

            }
        }
    }

    protected void fitsexponentialMultiple(int var1, int var2, double[] var3) {
        if (this.multipleY) {
            throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
        } else {
            this.lastMethod = 44;
            this.linNonLin = false;
            this.zeroCheck = false;
            this.nParam = 2 * var1;
            this.degreesOfFreedom = this.nData - this.nParam;
            if (this.degreesOfFreedom < 1 && !this.ignoreDofFcheck) {
                throw new IllegalArgumentException("Degrees of freedom must be greater than 0");
            } else if (var3.length != this.nParam) {
                throw new IllegalArgumentException(" Number of A and Bs, " + var3.length + ", does not match the number of exponentials, " + var1);
            } else {
                sort(this.xData[0], this.yData, this.weight);
                double[] var4 = new double[this.nParam];
                double[] var5 = new double[this.nParam];

                for(int var6 = 0; var6 < this.nParam; ++var6) {
                    var4[var6] = var3[var6];
                    var5[var6] = Math.abs(var4[var6] * 0.1D);
                }

                ExponentialMultipleFunction var9 = new ExponentialMultipleFunction();
                var9.setNexps(this.nParam);
                this.simplexFlag = 1;
                this.nonLinStatsNeeded = true;
                this.dualErrorsRequired = false;
                this.nelderMead(var9, (Object)null, var4, var5, this.fTol, this.nMax);
                if (var2 == 1) {
                    if (!this.suppressPrint) {
                        this.print();
                    }

                    int var8 = this.plotXY((RegressionFunction)var9);
                    if (var8 != -2 && !this.suppressYYplot) {
                        this.plotYY();
                    }
                }

            }
        }
    }

    public void oneMinusExponential() {
        this.fitsoneMinusExponential(0);
    }

    public void oneMinusExponentialPlot() {
        this.fitsoneMinusExponential(1);
    }

    protected void fitsoneMinusExponential(int var1) {
        if (this.multipleY) {
            throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
        } else {
            this.lastMethod = 45;
            this.userSupplied = false;
            this.linNonLin = false;
            this.zeroCheck = false;
            this.nParam = 2;
            this.degreesOfFreedom = this.nData - this.nParam;
            if (this.degreesOfFreedom < 1 && !this.ignoreDofFcheck) {
                throw new IllegalArgumentException("Degrees of freedom must be greater than 0");
            } else {
                sort(this.xData[0], this.yData, this.weight);
                ArrayMaths var2 = new ArrayMaths(this.yData);
                double var3 = var2.maximum();
                double var5 = var2.minimum();
                double var7 = 1.0D;
                double var9 = var3 / 2.0D;
                if (Math.abs(var5) > Math.abs(var3)) {
                    var7 = -1.0D;
                    var3 = var5;
                    var9 = var5 / 2.0D;
                }

                double var11 = 0.0D / 0.0;
                boolean var13 = true;
                int var14 = 0;

                while(true) {
                    while(var13) {
                        if (this.yData[var14] == var9) {
                            var11 = this.xData[0][var14] - this.xData[0][0];
                            var13 = false;
                        } else if (this.yData[var14] < var9 && this.yData[var14 + 1] > var9) {
                            var11 = (this.xData[0][var14] + this.xData[0][var14 + 1]) / 2.0D - this.xData[0][0];
                            var13 = false;
                        } else if (this.yData[var14] > var9 && this.yData[var14 + 1] < var9) {
                            var11 = (this.xData[0][var14] + this.xData[0][var14 + 1]) / 2.0D - this.xData[0][0];
                            var13 = false;
                        } else {
                            ++var14;
                            if (var14 >= this.nData - 1) {
                                var13 = false;
                            }
                        }
                    }

                    if (var11 != var11) {
                        var11 = var2.maximumDifference();
                    }

                    double var15 = -var7 / var11;
                    double[] var17 = new double[]{var3, var15};
                    double[] var18 = new double[]{Math.abs(var17[0] / 5.0D), Math.abs(var17[1] / 5.0D)};
                    OneMinusExponentialFunction var19 = new OneMinusExponentialFunction();
                    var19.setScaleOption(this.scaleFlag);
                    var19.setScaleFactor(this.yScaleFactor);
                    this.simplexFlag = 1;
                    this.nonLinStatsNeeded = true;
                    this.dualErrorsRequired = false;
                    this.nelderMead(var19, (Object)null, var17, var18, this.fTol, this.nMax);
                    double var21 = this.sumOfSquaresError;
                    double[] var23 = this.best;
                    var17[0] = -var3;
                    var17[1] = -var15;
                    var18[0] = Math.abs(var17[0] / 5.0D);
                    var18[1] = Math.abs(var17[1] / 5.0D);
                    this.simplexFlag = 1;
                    this.nonLinStatsNeeded = true;
                    this.dualErrorsRequired = false;
                    this.nelderMead(var19, (Object)null, var17, var18, this.fTol, this.nMax);
                    if (this.sumOfSquaresError > var21) {
                        var17[0] = var23[0];
                        var17[1] = var23[1];
                        var18[0] = Math.abs(var17[0] / 20.0D);
                        var18[1] = Math.abs(var17[1] / 20.0D);
                        this.simplexFlag = 1;
                        this.nonLinStatsNeeded = true;
                        this.dualErrorsRequired = false;
                        this.nelderMead(var19, (Object)null, var17, var18, this.fTol, this.nMax);
                    }

                    if (var1 == 1) {
                        if (!this.suppressPrint) {
                            this.print();
                        }

                        int var24 = this.plotXY((RegressionFunction)var19);
                        if (var24 != -2 && !this.suppressYYplot) {
                            this.plotYY();
                        }
                    }

                    return;
                }
            }
        }
    }

    public void exponential() {
        this.fitExponential(0, 0);
    }

    public void exponentialPlot() {
        this.fitExponential(1, 0);
    }

    public void exponentialOnePar() {
        this.fitExponential(0, 1);
    }

    public void exponentialOneParPlot() {
        this.fitExponential(1, 1);
    }

    public void exponentialStandard() {
        this.fitExponential(0, 2);
    }

    public void exponentialStandardPlot() {
        this.fitExponential(1, 2);
    }

    protected void fitExponential(int var1, int var2) {
        if (this.multipleY) {
            throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
        } else {
            this.userSupplied = false;
            switch(var2) {
                case 0:
                    this.lastMethod = 19;
                    this.nParam = 3;
                    break;
                case 1:
                    this.lastMethod = 20;
                    this.nParam = 2;
                    break;
                case 2:
                    this.lastMethod = 21;
                    this.nParam = 1;
            }

            if (!this.scaleFlag) {
                --this.nParam;
            }

            this.linNonLin = false;
            this.zeroCheck = false;
            this.degreesOfFreedom = this.nData - this.nParam;
            if (this.degreesOfFreedom < 1 && !this.ignoreDofFcheck) {
                throw new IllegalArgumentException("Degrees of freedom must be greater than 0");
            } else {
                if (this.nParam == 0) {
                    this.noParameters("Exponential");
                } else {
                    double[] var3 = new double[this.nData];
                    double[] var4 = new double[this.nData];
                    double[] var5 = new double[this.nData];

                    for(int var6 = 0; var6 < this.nData; ++var6) {
                        var3[var6] = this.xData[0][var6];
                        var4[var6] = this.yData[var6];
                        var5[var6] = this.weight[var6];
                    }

                    sort(this.xData[0], this.yData, this.weight);
                    Double var35 = null;
                    ArrayList var7 = dataSign(this.yData);
                    var35 = (Double)var7.get(4);
                    double var8 = var35;
                    Integer var10 = null;
                    var10 = (Integer)var7.get(5);
                    int var11 = var10;
                    String var12 = "Exponential";
                    boolean var13 = false;
                    if (var8 < 0.0D) {
                        this.reverseYsign(var12);
                        var7 = dataSign(this.yData);
                        var8 = -var8;
                        var13 = true;
                    }

                    boolean var14 = false;
                    double var15 = this.checkYallSmall(var8, var12);
                    if (var15 != 1.0D) {
                        var14 = true;
                        var8 = 1.0D;
                    }

                    ArrayList var17 = dataSign(this.xData[0]);
                    var35 = (Double)var17.get(0);
                    double var18 = var35;
                    double var20 = var8 / Math.exp(1.0D);
                    if (this.yData[0] < var8) {
                        var20 = (var8 + this.yData[0]) / (2.0D * Math.exp(1.0D));
                    }

                    double var22 = Math.abs(this.yData[0] - var20);
                    double var24 = 0.0D;
                    int var26 = 0;

                    for(int var27 = 1; var27 < this.nData; ++var27) {
                        var24 = Math.abs(this.yData[var27] - var20);
                        if (var24 < var22) {
                            var22 = var24;
                            var26 = var27;
                        }
                    }

                    double var36 = this.xData[0][var26] - this.xData[0][0];
                    double[] var29 = new double[this.nParam];
                    double[] var30 = new double[this.nParam];
                    switch(var2) {
                        case 0:
                            var29[0] = var18 * 0.9D;
                            var29[1] = var36;
                            if (this.scaleFlag) {
                                var29[2] = var8 * var36;
                            }

                            var30[0] = 0.1D * var29[0];
                            if (var30[0] == 0.0D) {
                                ArrayList var31 = dataSign(this.xData[0]);
                                Double var32 = null;
                                var32 = (Double)var31.get(2);
                                double var33 = var32;
                                if (var33 == 0.0D) {
                                    var32 = (Double)var31.get(0);
                                    var33 = var32;
                                }

                                var30[0] = var33 * 0.1D;
                            }

                            var30[1] = 0.1D * var29[1];
                            if (this.scaleFlag) {
                                var30[2] = 0.1D * var29[2];
                            }
                            break;
                        case 1:
                            var29[0] = var36;
                            if (this.scaleFlag) {
                                var29[1] = var8 * var36;
                            }

                            var30[0] = 0.1D * var29[0];
                            if (this.scaleFlag) {
                                var30[1] = 0.1D * var29[1];
                            }
                            break;
                        case 2:
                            if (this.scaleFlag) {
                                var29[0] = var8;
                                var30[0] = 0.1D * var29[0];
                            }
                    }

                    ExponentialFunction var37 = new ExponentialFunction();
                    var37.setScaleOption(this.scaleFlag);
                    var37.setScaleFactor(this.yScaleFactor);
                    var37.setTypeFlag(var2);
                    this.simplexFlag = 1;
                    this.nonLinStatsNeeded = true;
                    this.dualErrorsRequired = false;
                    this.nelderMead(var37, (Object)null, var29, var30, this.fTol, this.nMax);
                    int var38;
                    if (var1 == 1) {
                        if (!this.suppressPrint) {
                            this.print();
                        }

                        var38 = this.plotXY((RegressionFunction)var37);
                        if (var38 != -2 && !this.suppressYYplot) {
                            this.plotYY();
                        }
                    }

                    if (var14) {
                        for(var38 = 0; var38 < this.nData; ++var38) {
                            this.yData[var38] = var4[var38] / var15;
                            if (this.weightOpt) {
                                this.weight[var38] = var5[var38] / var15;
                            }
                        }
                    }

                    if (var13) {
                        for(var38 = 0; var38 < this.nData; ++var38) {
                            this.yData[var38] = -this.yData[var38];
                        }
                    }
                }

            }
        }
    }

    public void checkZeroNeg(double[] var1, double[] var2, double[] var3) {
        int var4 = 0;
        boolean var5 = true;

        for(int var6 = 0; var6 < this.nData; ++var6) {
            if (var2[var6] <= 0.0D) {
                if (var6 <= var4) {
                    var5 = true;
                    var4 = var6;

                    while(var5) {
                        ++var4;
                        if (var4 >= this.nData) {
                            throw new ArithmeticException("all zero cumulative data!!");
                        }

                        if (var2[var4] > 0.0D) {
                            var2[var6] = var2[var4];
                            var1[var6] = var1[var4];
                            var3[var6] = var3[var4];
                            var5 = false;
                        }
                    }
                } else if (var6 == this.nData - 1) {
                    var2[var6] = var2[var6 - 1];
                    var1[var6] = var1[var6 - 1];
                    var3[var6] = var3[var6 - 1];
                } else {
                    var2[var6] = (var2[var6 - 1] + var2[var6 + 1]) / 2.0D;
                    var1[var6] = (var1[var6 - 1] + var1[var6 + 1]) / 2.0D;
                    var3[var6] = (var3[var6 - 1] + var3[var6 + 1]) / 2.0D;
                }
            }
        }

    }

    public void rayleigh() {
        this.fitRayleigh(0, 0);
    }

    public void rayleighPlot() {
        this.fitRayleigh(1, 0);
    }

    protected void fitRayleigh(int var1, int var2) {
        if (this.multipleY) {
            throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
        } else {
            this.lastMethod = 22;
            this.userSupplied = false;
            this.nParam = 2;
            if (!this.scaleFlag) {
                --this.nParam;
            }

            this.linNonLin = false;
            this.zeroCheck = false;
            this.degreesOfFreedom = this.nData - this.nParam;
            if (this.degreesOfFreedom < 1 && !this.ignoreDofFcheck) {
                throw new IllegalArgumentException("Degrees of freedom must be greater than 0");
            } else {
                sort(this.xData[0], this.yData, this.weight);
                Double var3 = null;
                ArrayList var4 = dataSign(this.yData);
                var3 = (Double)var4.get(4);
                double var5 = var3;
                Integer var7 = null;
                var7 = (Integer)var4.get(5);
                int var8 = var7;
                String var9 = "Rayleigh";
                boolean var10 = false;
                if (var5 < 0.0D) {
                    this.reverseYsign(var9);
                    var4 = dataSign(this.yData);
                    var5 = -var5;
                    var10 = true;
                }

                boolean var11 = false;
                double var12 = this.checkYallSmall(var5, var9);
                if (var12 != 1.0D) {
                    var11 = true;
                    var5 = 1.0D;
                }

                double[] var14 = new double[this.nData];
                double[] var15 = new double[this.nData];
                double[] var16 = new double[this.nData];

                for(int var17 = 0; var17 < this.nData; ++var17) {
                    var14[var17] = this.xData[0][var17];
                    var15[var17] = this.yData[var17];
                    var16[var17] = this.weight[var17];
                }

                ArrayList var41 = dataSign(this.xData[0]);
                var3 = (Double)var41.get(0);
                double var18 = var3;
                var3 = (Double)var41.get(2);
                double var20 = var3;
                double var22 = this.xData[0][var8];
                double var24 = Math.log(2.0D) * halfWidth(this.xData[0], this.yData);
                double[] var26 = new double[this.nData];
                double[] var27 = new double[this.nData];
                double[] var28 = new double[this.nData];
                ErrorProp[] var29 = ErrorProp.oneDarray(this.nData);
                double var30 = this.calculateCumulativeValues(var26, var27, var28, var29, var8, var5, var22, var9);

                int var32;
                for(var32 = 0; var32 < this.nData; ++var32) {
                    var29[var32] = ErrorProp.minus(1.0D, var29[var32]);
                    var29[var32] = ErrorProp.over(1.0D, var29[var32]);
                    var29[var32] = ErrorProp.log(var29[var32]);
                    var27[var32] = var29[var32].getValue();
                    var28[var32] = var29[var32].getError();
                }

                for(var32 = 0; var32 < this.nData; ++var32) {
                    this.xData[0][var32] = var26[var32];
                    this.yData[var32] = var27[var32];
                    this.weight[var32] = var28[var32];
                }

                boolean var42 = this.weightOpt;
                this.weightOpt = true;
                this.statFlag = false;
                double[] var33 = new double[this.nParam];
                double[] var34 = new double[this.nParam];

                for(int var35 = 0; var35 < this.nParam; ++var35) {
                    var33[var35] = 1.0D;
                    var34[var35] = 0.2D;
                }

                var33[0] = var24;
                var34[0] = 0.2D;
                this.addConstraint(0, -1, 0.0D);
                RayleighFunctionTwo var43 = new RayleighFunctionTwo();
                this.simplexFlag = 1;
                this.nonLinStatsNeeded = true;
                this.dualErrorsRequired = false;
                this.nelderMead(var43, (Object)null, var33, var34, this.fTol, this.nMax);
                double[] var37 = Conv.copy(this.best);
                this.statFlag = true;
                this.weightOpt = var42;

                for(int var38 = 0; var38 < this.nData; ++var38) {
                    this.xData[0][var38] = var14[var38];
                    this.yData[var38] = var15[var38];
                    this.weight[var38] = var16[var38];
                }

                var33[0] = var37[0];
                if (this.scaleFlag) {
                    var33[1] = 1.0D / var30;
                }

                var34[0] = 0.1D * var33[0];
                if (this.scaleFlag) {
                    var34[1] = 0.1D * var33[1];
                }

                RayleighFunctionOne var44 = new RayleighFunctionOne();
                var44.setScaleOption(this.scaleFlag);
                var44.setScaleFactor(this.yScaleFactor);
                this.simplexFlag = 1;
                this.nonLinStatsNeeded = true;
                this.dualErrorsRequired = false;
                this.nelderMead(var44, (Object)null, var33, var34, this.fTol, this.nMax);
                int var40;
                if (var1 == 1) {
                    if (!this.suppressPrint) {
                        this.print();
                    }

                    var40 = this.plotXY((RegressionFunction)var44);
                    if (var40 != -2 && !this.suppressYYplot) {
                        this.plotYY();
                    }
                }

                if (var11) {
                    for(var40 = 0; var40 < this.nData; ++var40) {
                        this.yData[var40] = var15[var40] / var12;
                        if (this.weightOpt) {
                            this.weight[var40] = var16[var40] / var12;
                        }
                    }
                }

                if (var10) {
                    for(var40 = 0; var40 < this.nData; ++var40) {
                        this.yData[var40] = -this.yData[var40];
                    }
                }

            }
        }
    }

    public void paretoShifted() {
        this.fitPareto(0, 3);
    }

    public void paretoThreePar() {
        this.fitPareto(0, 3);
    }

    public void paretoShiftedPlot() {
        this.fitPareto(1, 3);
    }

    public void paretoThreeParPlot() {
        this.fitPareto(1, 3);
    }

    public void paretoTwoPar() {
        this.fitPareto(0, 2);
    }

    public void paretoTwoParPlot() {
        this.fitPareto(1, 2);
    }

    public void paretoOnePar() {
        this.fitPareto(0, 1);
    }

    public void paretoOneParPlot() {
        this.fitPareto(1, 1);
    }

    protected void fitPareto(int var1, int var2) {
        if (this.multipleY) {
            throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
        } else {
            this.userSupplied = false;
            switch(var2) {
                case 1:
                    this.lastMethod = 24;
                    this.nParam = 2;
                    break;
                case 2:
                    this.lastMethod = 23;
                    this.nParam = 3;
                    break;
                case 3:
                    this.lastMethod = 29;
                    this.nParam = 4;
            }

            if (!this.scaleFlag) {
                --this.nParam;
            }

            this.linNonLin = false;
            this.zeroCheck = false;
            this.degreesOfFreedom = this.nData - this.nParam;
            if (this.degreesOfFreedom < 1 && !this.ignoreDofFcheck) {
                throw new IllegalArgumentException("Degrees of freedom must be greater than 0");
            } else {
                String var3 = "Pareto";
                sort(this.xData[0], this.yData, this.weight);
                Double var4 = null;
                ArrayList var5 = dataSign(this.yData);
                var4 = (Double)var5.get(4);
                double var6 = var4;
                Integer var8 = null;
                var8 = (Integer)var5.get(5);
                int var9 = var8;
                if (this.infinityCheck(var6, var9)) {
                    var5 = dataSign(this.yData);
                    var4 = (Double)var5.get(4);
                    var6 = var4;
                    var8 = null;
                    var8 = (Integer)var5.get(5);
                    var9 = var8;
                }

                boolean var10 = false;
                if (var6 < 0.0D) {
                    this.reverseYsign(var3);
                    var5 = dataSign(this.yData);
                    var6 = -var6;
                    var10 = true;
                }

                boolean var11 = false;
                double var12 = this.checkYallSmall(var6, var3);
                if (var12 != 1.0D) {
                    var11 = true;
                    var6 = 1.0D;
                }

                ArrayList var14 = dataSign(this.xData[0]);
                var4 = (Double)var14.get(0);
                double var15 = var4;
                var4 = (Double)var14.get(2);
                double var17 = var4;
                double var19 = this.xData[0][var9];
                double var10000 = Math.log(2.0D) * halfWidth(this.xData[0], this.yData);
                double[] var23 = new double[this.nData];
                double[] var24 = new double[this.nData];
                double[] var25 = new double[this.nData];

                for(int var26 = 0; var26 < this.nData; ++var26) {
                    var23[var26] = this.xData[0][var26];
                    var24[var26] = this.yData[var26];
                    var25[var26] = this.weight[var26];
                }

                double[] var41 = new double[this.nData];
                double[] var27 = new double[this.nData];
                double[] var28 = new double[this.nData];
                ErrorProp[] var29 = ErrorProp.oneDarray(this.nData);
                double var30 = this.calculateCumulativeValues(var41, var27, var28, var29, var9, var6, var19, var3);

                int var32;
                for(var32 = 0; var32 < this.nData; ++var32) {
                    var29[var32] = ErrorProp.minus(1.0D, var29[var32]);
                    var27[var32] = var29[var32].getValue();
                    var28[var32] = var29[var32].getError();
                }

                for(var32 = 0; var32 < this.nData; ++var32) {
                    this.xData[0][var32] = var41[var32];
                    this.yData[var32] = var27[var32];
                    this.weight[var32] = var28[var32];
                }

                boolean var42 = this.weightOpt;
                this.weightOpt = true;
                this.statFlag = false;
                double[] var33 = new double[this.nParam];
                double[] var34 = new double[this.nParam];

                for(int var35 = 0; var35 < this.nParam; ++var35) {
                    var33[var35] = 1.0D;
                    var34[var35] = 0.2D;
                }

                double var43;
                switch(var2) {
                    case 1:
                        if (var15 < 0.0D) {
                            System.out.println("Method: FitParetoOnePar/FitParetoOneParPlot\nNegative data values present\nFitParetoShifted/FitParetoShiftedPlot would have been more appropriate");
                        }

                        var33[0] = 2.0D;
                        var34[0] = 0.2D * var33[0];
                        this.addConstraint(0, -1, 0.0D);
                        this.addConstraint(1, -1, 0.0D);
                        break;
                    case 2:
                        if (var15 < 0.0D) {
                            System.out.println("Method: FitParetoTwoPar/FitParetoTwoParPlot\nNegative data values present\nFitParetoShifted/FitParetoShiftedPlot would have been more appropriate");
                        }

                        var33[0] = 2.0D;
                        var33[1] = var15 * 0.9D;
                        if (var33[1] < 0.0D) {
                            var33[1] = 0.0D;
                        }

                        var34[0] = 0.2D * var33[0];
                        var34[1] = 0.2D * var33[1];
                        if (var34[1] == 0.0D) {
                            var43 = var17;
                            if (var17 == 0.0D) {
                                var43 = var15;
                            }

                            var34[1] = var43 * 0.1D;
                        }

                        this.addConstraint(0, -1, 0.0D);
                        this.addConstraint(1, -1, 0.0D);
                        break;
                    case 3:
                        var33[0] = 2.0D;
                        var33[1] = var15 * 0.9D;
                        if (var15 < 0.0D) {
                            var33[2] = -var15 * 1.1D;
                        } else {
                            var33[2] = var15 * 0.01D;
                        }

                        if (var33[1] < 0.0D) {
                            var33[1] = 0.0D;
                        }

                        var34[0] = 0.2D * var33[0];
                        var34[1] = 0.2D * var33[1];
                        if (var34[1] == 0.0D) {
                            var43 = var17;
                            if (var17 == 0.0D) {
                                var43 = var15;
                            }

                            var34[1] = var43 * 0.1D;
                        }

                        this.addConstraint(0, -1, 0.0D);
                        this.addConstraint(1, -1, 0.0D);
                        this.addConstraint(1, 1, var15);
                }

                ParetoFunctionTwo var44 = new ParetoFunctionTwo();
                var44.setTypeFlag(var2);
                this.simplexFlag = 1;
                this.nonLinStatsNeeded = true;
                this.dualErrorsRequired = false;
                this.nelderMead(var44, (Object)null, var33, var34, this.fTol, this.nMax);
                double[] var37 = Conv.copy(this.best);
                this.statFlag = true;
                this.weightOpt = var42;

                for(int var38 = 0; var38 < this.nData; ++var38) {
                    this.xData[0][var38] = var23[var38];
                    this.yData[var38] = var24[var38];
                    this.weight[var38] = var25[var38];
                }

                double var45;
                switch(var2) {
                    case 1:
                        var33[0] = var37[0];
                        if (var33[0] <= 0.0D) {
                            if (var33[0] == 0.0D) {
                                var33[0] = 1.0D;
                            } else {
                                var33[0] = Math.min(1.0D, -var33[0]);
                            }
                        }

                        if (this.scaleFlag) {
                            var33[1] = 1.0D / var30;
                        }

                        var34[0] = 0.1D * var33[0];
                        if (this.scaleFlag) {
                            var34[1] = 0.1D * var33[1];
                        }
                        break;
                    case 2:
                        var33[0] = var37[0];
                        if (var33[0] <= 0.0D) {
                            if (var33[0] == 0.0D) {
                                var33[0] = 1.0D;
                            } else {
                                var33[0] = Math.min(1.0D, -var33[0]);
                            }
                        }

                        var33[1] = var37[1];
                        if (var33[1] <= 0.0D) {
                            if (var33[1] == 0.0D) {
                                var33[1] = 1.0D;
                            } else {
                                var33[1] = Math.min(1.0D, -var33[1]);
                            }
                        }

                        if (this.scaleFlag) {
                            var33[2] = 1.0D / var30;
                        }

                        var34[0] = 0.1D * var33[0];
                        var34[1] = 0.1D * var33[1];
                        if (var34[1] == 0.0D) {
                            var45 = var17;
                            if (var17 == 0.0D) {
                                var45 = var15;
                            }

                            var34[1] = var45 * 0.1D;
                        }

                        if (this.scaleFlag) {
                            var34[2] = 0.1D * var33[2];
                        }
                        break;
                    case 3:
                        var33[0] = var37[0];
                        if (var33[0] <= 0.0D) {
                            if (var33[0] == 0.0D) {
                                var33[0] = 1.0D;
                            } else {
                                var33[0] = Math.min(1.0D, -var33[0]);
                            }
                        }

                        var33[1] = var37[1];
                        if (var33[1] <= 0.0D) {
                            if (var33[1] == 0.0D) {
                                var33[1] = 1.0D;
                            } else {
                                var33[1] = Math.min(1.0D, -var33[1]);
                            }
                        }

                        var33[2] = var37[2];
                        if (this.scaleFlag) {
                            var33[3] = 1.0D / var30;
                        }

                        var34[0] = 0.1D * var33[0];
                        var34[1] = 0.1D * var33[1];
                        if (var34[1] == 0.0D) {
                            var45 = var17;
                            if (var17 == 0.0D) {
                                var45 = var15;
                            }

                            var34[1] = var45 * 0.1D;
                        }

                        if (this.scaleFlag) {
                            var34[2] = 0.1D * var33[2];
                        }
                }

                ParetoFunctionOne var46 = new ParetoFunctionOne();
                var46.setScaleOption(this.scaleFlag);
                var46.setScaleFactor(this.yScaleFactor);
                var46.setTypeFlag(var2);
                this.simplexFlag = 1;
                this.nonLinStatsNeeded = true;
                this.dualErrorsRequired = false;
                this.nelderMead(var46, (Object)null, var33, var34, this.fTol, this.nMax);
                int var40;
                if (var1 == 1) {
                    if (!this.suppressPrint) {
                        this.print();
                    }

                    var40 = this.plotXY((RegressionFunction)var46);
                    if (var40 != -2 && !this.suppressYYplot) {
                        this.plotYY();
                    }
                }

                this.weightOpt = var42;
                if (var11) {
                    for(var40 = 0; var40 < this.nData; ++var40) {
                        this.yData[var40] = var24[var40] / var12;
                        if (this.weightOpt) {
                            this.weight[var40] = var25[var40] / var12;
                        }
                    }
                }

                if (var10) {
                    for(var40 = 0; var40 < this.nData; ++var40) {
                        this.yData[var40] = -this.yData[var40];
                    }
                }

            }
        }
    }

    public void sigmoidThreshold() {
        this.fitSigmoidThreshold(0);
    }

    public void sigmoidThresholdPlot() {
        this.fitSigmoidThreshold(1);
    }

    protected void fitSigmoidThreshold(int var1) {
        if (this.multipleY) {
            throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
        } else {
            this.lastMethod = 25;
            this.userSupplied = false;
            this.linNonLin = false;
            this.zeroCheck = false;
            this.nParam = 3;
            if (!this.scaleFlag) {
                this.nParam = 2;
            }

            this.degreesOfFreedom = this.nData - this.nParam;
            if (this.degreesOfFreedom < 1 && !this.ignoreDofFcheck) {
                throw new IllegalArgumentException("Degrees of freedom must be greater than 0");
            } else {
                sort(this.xData[0], this.yData, this.weight);
                double var2 = Fmath.minimum(this.yData);
                double var4 = Fmath.maximum(this.yData);
                byte var6 = 1;
                if (var2 < 0.0D) {
                    var6 = -1;
                }

                double var7 = (var4 - var2) / 2.0D;
                double var9 = this.xData[0][0];
                int var11 = 1;
                int var12 = this.yData.length;
                boolean var13 = true;

                while(var13) {
                    if (this.yData[var11] >= (double)var6 * var7) {
                        var9 = this.xData[0][var11];
                        var13 = false;
                    } else {
                        ++var11;
                        if (var11 >= var12) {
                            var9 = Stat.mean(this.xData[0]);
                            var11 = var12 - 1;
                            var13 = false;
                        }
                    }
                }

                double var14 = this.xData[0][var12 - 1];
                int var16 = var12 - 1;
                var13 = true;

                while(var13) {
                    if (this.yData[var16] <= (double)var6 * var7) {
                        var14 = this.xData[0][var16];
                        var13 = false;
                    } else {
                        --var16;
                        if (var16 < 0) {
                            var14 = Stat.mean(this.xData[0]);
                            var16 = 1;
                            var13 = false;
                        }
                    }
                }

                int var17 = (var11 + var16) / 2;
                double var18 = this.xData[0][var17];
                double var20 = 2.0D * (this.yData[var12 - 1] - var18) / (this.xData[0][var12 - 1] - this.xData[0][var17]);
                double var22 = 2.0D * var18 / (this.xData[0][var17] - this.xData[0][var12 - 1]);
                double var24 = Math.max(var20, var22);
                double[] var26 = new double[this.nParam];
                var26[0] = 4.0D * var24;
                if (var6 == 1) {
                    var26[0] /= var4;
                } else {
                    var26[0] /= var2;
                }

                var26[1] = var18;
                if (this.scaleFlag) {
                    if (var6 == 1) {
                        var26[2] = var4;
                    } else {
                        var26[2] = var2;
                    }
                }

                double[] var27 = new double[this.nParam];

                for(int var28 = 0; var28 < this.nParam; ++var28) {
                    var27[var28] = 0.1D * var26[var28];
                }

                if (var27[0] == 0.0D) {
                    var27[0] = 0.1D * (this.xData[0][var12 - 1] - this.xData[0][0]) / (this.yData[var12 - 1] - this.yData[0]);
                }

                if (var27[1] == 0.0D) {
                    var27[1] = (this.xData[0][var12 - 1] - this.xData[0][0]) / 20.0D;
                }

                if (this.scaleFlag && var27[2] == 0.0D) {
                    var27[2] = 0.1D * (this.yData[var12 - 1] - this.yData[0]);
                }

                int var30;
                if (this.xErrorsEntered) {
                    this.dualErrorsRequired = true;
                    this.nonLinStatsNeeded = true;
                    this.simplexFlag = 3;
                    SigmoidThresholdFunctionDual var31 = new SigmoidThresholdFunctionDual();
                    var31.setScaleOption(this.scaleFlag);
                    var31.setScaleFactor(this.yScaleFactor);
                    var31.setXerrors(this.xErrors);
                    var31.setYerrors(this.yErrors);
                    this.nelderMead(var31, (Object)null, var26, var27, this.fTol, this.nMax);
                    if (var1 == 1) {
                        if (!this.suppressPrint) {
                            this.print();
                        }

                        var30 = this.plotXY((RegressionFunction3)var31);
                        if (var30 != -2 && !this.suppressYYplot) {
                            this.plotYY();
                        }
                    }
                } else {
                    this.dualErrorsRequired = false;
                    this.nonLinStatsNeeded = true;
                    this.simplexFlag = 1;
                    SigmoidThresholdFunction var32 = new SigmoidThresholdFunction();
                    var32.setScaleOption(this.scaleFlag);
                    var32.setScaleFactor(this.yScaleFactor);
                    this.nelderMead(var32, (Object)null, var26, var27, this.fTol, this.nMax);
                    if (var1 == 1) {
                        if (!this.suppressPrint) {
                            this.print();
                        }

                        var30 = this.plotXY((RegressionFunction)var32);
                        if (var30 != -2 && !this.suppressYYplot) {
                            this.plotYY();
                        }
                    }
                }

                this.dualErrorsRequired = false;
                this.nonLinStatsNeeded = true;
            }
        }
    }

    public void sigmoidHillSips() {
        this.fitsigmoidHillSips(0);
    }

    public void sigmoidHillSipsPlot() {
        this.fitsigmoidHillSips(1);
    }

    protected void fitsigmoidHillSips(int var1) {
        if (this.multipleY) {
            throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
        } else {
            this.lastMethod = 28;
            this.userSupplied = false;
            this.linNonLin = false;
            this.zeroCheck = false;
            this.nParam = 3;
            if (!this.scaleFlag) {
                this.nParam = 2;
            }

            this.degreesOfFreedom = this.nData - this.nParam;
            if (this.degreesOfFreedom < 1 && !this.ignoreDofFcheck) {
                throw new IllegalArgumentException("Degrees of freedom must be greater than 0");
            } else {
                sort(this.xData[0], this.yData, this.weight);
                double[] var2 = Conv.copy(this.xData[0]);
                double[] var3 = Conv.copy(this.yData);
                byte var4 = 1;
                if (this.yData[0] > this.yData[this.nData - 1]) {
                    var4 = -1;

                    for(int var5 = 0; var5 < this.nData; ++var5) {
                        var3[var5] = -var3[var5];
                    }
                }

                double var29 = Fmath.minimum(var3);
                double var7 = Fmath.maximum(var3);
                double var9 = 0.0D;
                double var11 = var7 - var29;
                if (var29 <= 0.0D) {
                    var9 = var29 - 0.01D * var11;

                    for(int var13 = 0; var13 < this.nData; ++var13) {
                        var3[var13] -= var9;
                    }
                }

                double var30 = Fmath.minimum(var2);
                double var15 = Fmath.maximum(var2);
                double var17 = 0.0D;
                double var19 = var15 - var30;
                int var21;
                if (var30 <= 0.0D) {
                    var17 = var30 - 0.01D * var19;

                    for(var21 = 0; var21 < this.nData; ++var21) {
                        var2[var21] -= var17;
                    }
                }

                for(var21 = 0; var21 < this.nData; ++var21) {
                    var3[var21] = Math.log(var11 + 0.01D * var11 - var3[var21]) - Math.log(var3[var21]);
                    var2[var21] = Math.log(var2[var21]);
                }

                Regression var31 = new Regression(var2, var3);
                var31.linear();
                double[] var22 = var31.getBestEstimates();
                double[] var23 = new double[this.nParam];
                var23[1] = -var22[1] * (double)var4;
                var23[0] = Math.exp(var22[0] / var23[1]) + var17;
                if (this.scaleFlag) {
                    var23[2] = var11 * (double)var4;
                }

                int var24 = this.nData / 2;
                double[] var25 = new double[this.nParam];

                for(int var26 = 0; var26 < this.nParam; ++var26) {
                    var25[var26] = 0.1D * var23[var26];
                }

                if (var25[0] == 0.0D) {
                    var25[0] = 0.1D * (this.xData[0][var24 - 1] - this.xData[0][0]) / (this.yData[var24 - 1] - this.yData[0]);
                }

                if (var25[1] == 0.0D) {
                    var25[1] = (this.xData[0][var24 - 1] - this.xData[0][0]) / 20.0D;
                }

                if (this.scaleFlag && var25[2] == 0.0D) {
                    var25[2] = 0.1D * (this.yData[var24 - 1] - this.yData[0]);
                }

                int var28;
                if (this.xErrorsEntered) {
                    this.dualErrorsRequired = true;
                    this.nonLinStatsNeeded = true;
                    this.simplexFlag = 3;
                    SigmoidHillSipsFunctionDual var32 = new SigmoidHillSipsFunctionDual();
                    var32.setScaleOption(this.scaleFlag);
                    var32.setScaleFactor(this.yScaleFactor);
                    var32.setXerrors(this.xErrors);
                    var32.setYerrors(this.yErrors);
                    this.nelderMead(var32, (Object)null, var23, var25, this.fTol, this.nMax);
                    if (var1 == 1) {
                        if (!this.suppressPrint) {
                            this.print();
                        }

                        var28 = this.plotXY((RegressionFunction3)var32);
                        if (var28 != -2 && !this.suppressYYplot) {
                            this.plotYY();
                        }
                    }
                } else {
                    this.dualErrorsRequired = false;
                    this.nonLinStatsNeeded = true;
                    this.simplexFlag = 1;
                    SigmoidHillSipsFunction var33 = new SigmoidHillSipsFunction();
                    var33.setScaleOption(this.scaleFlag);
                    var33.setScaleFactor(this.yScaleFactor);
                    this.nelderMead(var33, (Object)null, var23, var25, this.fTol, this.nMax);
                    if (var1 == 1) {
                        if (!this.suppressPrint) {
                            this.print();
                        }

                        var28 = this.plotXY((RegressionFunction)var33);
                        if (var28 != -2 && !this.suppressYYplot) {
                            this.plotYY();
                        }
                    }
                }

                this.dualErrorsRequired = false;
                this.nonLinStatsNeeded = true;
            }
        }
    }

    public void ec50() {
        this.fitEC50(0);
    }

    public void ec50Plot() {
        this.fitEC50(1);
    }

    public void ec50constrained() {
        this.fitEC50(2);
    }

    public void ec50constrainedPlot() {
        this.fitEC50(3);
    }

    public void fourParameterLogistic() {
        this.fitEC50(0);
    }

    public void fourParameterLogisticPlot() {
        this.fitEC50(1);
    }

    public void fourParameterLogisticConstrained() {
        this.fitEC50(2);
    }

    public void fourParameterLogisticConstrainedPlot() {
        this.fitEC50(3);
    }

    protected void fitEC50(int var1) {
        if (this.multipleY) {
            throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
        } else {
            boolean var2 = false;
            boolean var3 = false;
            this.userSupplied = false;
            switch(var1) {
                case 0:
                    this.lastMethod = 39;
                    var2 = false;
                    break;
                case 1:
                    this.lastMethod = 39;
                    var2 = true;
                    break;
                case 2:
                    this.lastMethod = 41;
                    var2 = false;
                    var3 = true;
                    break;
                case 3:
                    this.lastMethod = 41;
                    var2 = true;
                    var3 = true;
            }

            this.linNonLin = false;
            this.zeroCheck = false;
            this.nParam = 4;
            this.scaleFlag = false;
            this.degreesOfFreedom = this.nData - this.nParam;
            if (this.degreesOfFreedom < 1 && !this.ignoreDofFcheck) {
                throw new IllegalArgumentException("Degrees of freedom must be greater than 0");
            } else {
                sort(this.xData[0], this.yData, this.weight);
                int var4 = this.yData.length;
                this.midPoint();
                double var5 = 1.0D;
                if (this.directionFlag == -1) {
                    var5 = -1.0D;
                }

                double[] var7 = new double[this.nParam];
                var7[0] = this.topS;
                var7[1] = this.bottomS;
                var7[2] = this.midPointXvalue;
                var7[3] = var5;
                double[] var8 = new double[this.nParam];

                for(int var9 = 0; var9 < this.nParam; ++var9) {
                    var8[var9] = 0.1D * Math.abs(var7[var9]);
                }

                if (var8[0] == 0.0D) {
                    var8[0] = 0.1D * (this.yData[var4 - 1] - this.yData[0]);
                }

                if (var8[1] == 0.0D) {
                    var8[1] = 0.1D * (this.yData[var4 - 1] - this.yData[0]);
                }

                if (var8[2] == 0.0D) {
                    var8[2] = 0.05D * (this.xData[0][var4 - 1] - this.xData[0][0]);
                }

                if (var8[3] == 0.0D) {
                    var8[3] = 0.1D * (this.xData[0][var4 - 1] - this.xData[0][0]) / (this.yData[var4 - 1] - this.yData[0]);
                }

                if (var3) {
                    this.addConstraint(0, -1, 0.0D);
                }

                int var11;
                if (this.xErrorsEntered) {
                    EC50FunctionDual var12 = new EC50FunctionDual();
                    var12.setXerrors(this.xErrors);
                    var12.setYerrors(this.yErrors);
                    this.simplexFlag = 3;
                    this.nonLinStatsNeeded = true;
                    this.dualErrorsRequired = true;
                    this.nelderMead(var12, (Object)null, var7, var8, this.fTol, this.nMax);
                    if (var2) {
                        if (!this.suppressPrint) {
                            this.print();
                        }

                        var11 = this.plotXY((RegressionFunction3)var12);
                        if (var11 != -2 && !this.suppressYYplot) {
                            this.plotYY();
                        }
                    }
                } else {
                    EC50Function var13 = new EC50Function();
                    this.simplexFlag = 1;
                    this.nonLinStatsNeeded = true;
                    this.dualErrorsRequired = false;
                    this.nelderMead(var13, (Object)null, var7, var8, this.fTol, this.nMax);
                    if (var2) {
                        if (!this.suppressPrint) {
                            this.print();
                        }

                        var11 = this.plotXY((RegressionFunction)var13);
                        if (var11 != -2 && !this.suppressYYplot) {
                            this.plotYY();
                        }
                    }
                }

            }
        }
    }

    protected void ec50(double var1, double var3) {
        this.lastMethod = 40;
        this.fitEC50(var1, var3, 0);
    }

    protected void fourParameterLogistic(double var1, double var3) {
        this.lastMethod = 40;
        this.fitEC50(var1, var3, 0);
    }

    protected void ec50Plot(double var1, double var3) {
        this.lastMethod = 40;
        this.fitEC50(var1, var3, 1);
    }

    protected void fourParameterLogisticPlot(double var1, double var3) {
        this.lastMethod = 40;
        this.fitEC50(var1, var3, 1);
    }

    private void fitEC50(double var1, double var3, int var5) {
        if (this.multipleY) {
            throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
        } else {
            this.bottom = var1;
            this.top = var3;
            this.linNonLin = false;
            this.zeroCheck = false;
            this.nParam = 2;
            this.scaleFlag = false;
            this.degreesOfFreedom = this.nData - this.nParam;
            if (this.degreesOfFreedom < 1 && !this.ignoreDofFcheck) {
                throw new IllegalArgumentException("Degrees of freedom must be greater than 0");
            } else {
                sort(this.xData[0], this.yData, this.weight);
                int var6 = this.yData.length;
                this.midPoint();
                double var7 = 1.0D;
                if (this.directionFlag == -1) {
                    var7 = -1.0D;
                }

                double[] var9 = new double[this.nParam];
                var9[0] = this.midPointXvalue;
                var9[1] = var7;
                double[] var10 = new double[this.nParam];

                for(int var11 = 0; var11 < this.nParam; ++var11) {
                    var10[var11] = 0.1D * Math.abs(var9[var11]);
                }

                if (var10[0] == 0.0D) {
                    var10[0] = 0.05D * (this.xData[0][var6 - 1] - this.xData[0][0]);
                }

                if (var10[1] == 0.0D) {
                    var10[1] = 0.1D * (this.xData[0][var6 - 1] - this.xData[0][0]) / (this.yData[var6 - 1] - this.yData[0]);
                }

                int var13;
                if (this.xErrorsEntered) {
                    EC50FixedFunctionDual var14 = new EC50FixedFunctionDual();
                    var14.setBottom(this.bottom);
                    var14.setTop(this.top);
                    var14.setXerrors(this.xErrors);
                    var14.setYerrors(this.yErrors);
                    this.simplexFlag = 3;
                    this.nonLinStatsNeeded = true;
                    this.dualErrorsRequired = true;
                    this.nelderMead(var14, (Object)null, var9, var10, this.fTol, this.nMax);
                    if (var5 == 1) {
                        if (!this.suppressPrint) {
                            this.print();
                        }

                        var13 = this.plotXY((RegressionFunction3)var14);
                        if (var13 != -2 && !this.suppressYYplot) {
                            this.plotYY();
                        }
                    }
                } else {
                    EC50FixedFunction var15 = new EC50FixedFunction();
                    var15.setBottom(this.bottom);
                    var15.setTop(this.top);
                    this.simplexFlag = 1;
                    this.nonLinStatsNeeded = true;
                    this.dualErrorsRequired = false;
                    this.nelderMead(var15, (Object)null, var9, var10, this.fTol, this.nMax);
                    if (var5 == 1) {
                        if (!this.suppressPrint) {
                            this.print();
                        }

                        var13 = this.plotXY((RegressionFunction)var15);
                        if (var13 != -2 && !this.suppressYYplot) {
                            this.plotYY();
                        }
                    }
                }

            }
        }
    }

    private void midPoint() {
        this.bottomS = Fmath.minimum(this.yData);
        this.topS = Fmath.maximum(this.yData);
        this.bottomSindex = 0.0D;
        this.topSindex = 0.0D;
        int var1 = this.yData.length;
        int var2 = 0;
        boolean var3 = true;

        while(var3) {
            if (this.bottomS == this.yData[var2]) {
                this.bottomSindex = (double)var2;
                var3 = false;
            } else {
                ++var2;
                if (var2 >= var1) {
                    throw new IllegalArgumentException("This should not be possible - check coding");
                }
            }
        }

        var3 = true;
        var2 = 0;

        while(var3) {
            if (this.topS == this.yData[var2]) {
                this.topSindex = (double)var2;
                var3 = false;
            } else {
                ++var2;
                if (var2 >= var1) {
                    throw new IllegalArgumentException("This should not be possible - check coding");
                }
            }
        }

        this.directionFlag = 1;
        if (this.topSindex < this.bottomSindex) {
            this.directionFlag = -1;
        }

        double var4 = this.topS - (this.topS - this.bottomS) / 2.0D;
        this.midPointYvalue = var4;
        double var6 = this.xData[0][0];
        var2 = 0;
        double var8;
        int var10;
        int var11;
        if (this.directionFlag == 1) {
            var3 = true;

            while(var3) {
                if (this.yData[var2] >= var4) {
                    var6 = this.xData[0][var2];
                    var3 = false;
                } else {
                    ++var2;
                    if (var2 >= var1) {
                        var6 = Stat.mean(this.xData[0]);
                        var2 = var1 - 1;
                        var3 = false;
                    }
                }
            }

            var8 = this.xData[0][var1 - 1];
            var10 = var1 - 1;
            var3 = true;

            while(var3) {
                if (this.yData[var10] <= var4) {
                    var8 = this.xData[0][var10];
                    var3 = false;
                } else {
                    --var10;
                    if (var10 < 0) {
                        var8 = Stat.mean(this.xData[0]);
                        var10 = 1;
                        var3 = false;
                    }
                }
            }

            if (var2 < var10) {
                var11 = var10;
                var10 = var2;
                var2 = var11;
            }

            this.midPointLowerIndex = var10;
            this.midPointUpperIndex = var2;
            this.midPointXvalue = (this.xData[0][var2] + this.xData[0][var10]) / 2.0D;
        } else {
            var2 = 0;
            var3 = true;

            while(var3) {
                if (this.yData[var2] <= var4) {
                    var6 = this.xData[0][var2];
                    var3 = false;
                } else {
                    ++var2;
                    if (var2 >= var1) {
                        var6 = Stat.mean(this.xData[0]);
                        var2 = var1 - 1;
                        var3 = false;
                    }
                }
            }

            var8 = this.xData[0][var1 - 1];
            var10 = var1 - 1;
            var3 = true;

            while(var3) {
                if (this.yData[var10] >= var4) {
                    var8 = this.xData[0][var10];
                    var3 = false;
                } else {
                    --var10;
                    if (var10 < 0) {
                        var8 = Stat.mean(this.xData[0]);
                        var10 = 1;
                        var3 = false;
                    }
                }
            }

            if (var2 > var10) {
                var11 = var10;
                var10 = var2;
                var2 = var11;
            }

            if (var2 < var10) {
                var11 = var10;
                var10 = var2;
                var2 = var11;
            }

            this.midPointLowerIndex = var10;
            this.midPointUpperIndex = var2;
            this.midPointXvalue = (this.xData[0][var2] + this.xData[0][var10]) / 2.0D;
        }

    }

    public void fiveParameterLogistic() {
        this.fitfiveParameterLogistic(0);
    }

    public void fiveParameterLogisticPlot() {
        this.fitfiveParameterLogistic(1);
    }

    protected void fitfiveParameterLogistic(int var1) {
        if (this.multipleY) {
            throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
        } else {
            boolean var2 = false;
            this.userSupplied = false;
            this.lastMethod = 51;
            this.linNonLin = false;
            this.zeroCheck = false;
            this.nParam = 5;
            this.scaleFlag = false;
            this.degreesOfFreedom = this.nData - this.nParam;
            if (this.degreesOfFreedom < 1 && !this.ignoreDofFcheck) {
                throw new IllegalArgumentException("Degrees of freedom must be greater than 0");
            } else {
                sort(this.xData[0], this.yData, this.weight);
                int var3 = this.yData.length;
                this.midPoint();
                double var4 = 1.0D;
                if (this.directionFlag == -1 && var4 >= 0.0D) {
                    var4 = -1.0D;
                }

                double[] var6 = new double[this.nParam];
                var6[0] = this.topS;
                var6[1] = this.bottomS;
                var6[2] = this.midPointXvalue;
                var6[3] = var4;
                var6[4] = 1.0D;
                double[] var7 = new double[this.nParam];

                for(int var8 = 0; var8 < this.nParam; ++var8) {
                    var7[var8] = 0.1D * Math.abs(var6[var8]);
                }

                if (var7[0] == 0.0D) {
                    var7[0] = 0.1D * (this.yData[var3 - 1] - this.yData[0]);
                }

                if (var7[1] == 0.0D) {
                    var7[1] = 0.1D * (this.yData[var3 - 1] - this.yData[0]);
                }

                if (var7[2] == 0.0D) {
                    var7[2] = 0.05D * (this.xData[0][var3 - 1] - this.xData[0][0]);
                }

                if (var7[3] == 0.0D) {
                    var7[3] = 0.1D * (this.xData[0][var3 - 1] - this.xData[0][0]) / (this.yData[var3 - 1] - this.yData[0]);
                }

                int var10;
                if (this.xErrorsEntered) {
                    Logistic5FunctionDual var11 = new Logistic5FunctionDual();
                    var11.setXerrors(this.xErrors);
                    var11.setYerrors(this.yErrors);
                    this.simplexFlag = 3;
                    this.nonLinStatsNeeded = true;
                    this.dualErrorsRequired = true;
                    this.nelderMead(var11, (Object)null, var6, var7, this.fTol, this.nMax);
                    if (var1 == 1) {
                        if (!this.suppressPrint) {
                            this.print();
                        }

                        var10 = this.plotXY((RegressionFunction3)var11);
                        if (var10 != -2 && !this.suppressYYplot) {
                            this.plotYY();
                        }
                    }
                } else {
                    Logistic5Function var12 = new Logistic5Function();
                    this.simplexFlag = 1;
                    this.nonLinStatsNeeded = true;
                    this.dualErrorsRequired = false;
                    this.nelderMead(var12, (Object)null, var6, var7, this.fTol, this.nMax);
                    if (var1 == 1) {
                        if (!this.suppressPrint) {
                            this.print();
                        }

                        var10 = this.plotXY((RegressionFunction)var12);
                        if (var10 != -2 && !this.suppressYYplot) {
                            this.plotYY();
                        }
                    }
                }

            }
        }
    }

    public void fiveParameterLogistic(double var1, double var3) {
        this.lastMethod = 42;
        this.fitFiveParameterLogistic(var1, var3, 0);
    }

    public void fiveParameterLogisticPlot(double var1, double var3) {
        this.lastMethod = 42;
        this.fitFiveParameterLogistic(var1, var3, 1);
    }

    protected void fitFiveParameterLogistic(double var1, double var3, int var5) {
        if (this.multipleY) {
            throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
        } else {
            this.linNonLin = false;
            this.zeroCheck = false;
            this.nParam = 3;
            this.scaleFlag = false;
            this.degreesOfFreedom = this.nData - this.nParam;
            if (this.degreesOfFreedom < 1 && !this.ignoreDofFcheck) {
                throw new IllegalArgumentException("Degrees of freedom must be greater than 0");
            } else {
                sort(this.xData[0], this.yData, this.weight);
                int var6 = this.yData.length;
                this.midPoint();
                double var7 = 1.0D;
                if (this.directionFlag == -1) {
                    var7 = -1.0D;
                }

                double[] var9 = new double[this.nParam];
                var9[0] = this.midPointXvalue;
                var9[1] = var7;
                var9[2] = 1.0D;
                double[] var10 = new double[this.nParam];

                for(int var11 = 0; var11 < this.nParam; ++var11) {
                    var10[var11] = 0.1D * Math.abs(var9[var11]);
                }

                if (var10[0] == 0.0D) {
                    var10[0] = 0.05D * (this.xData[0][var6 - 1] - this.xData[0][0]);
                }

                if (var10[1] == 0.0D) {
                    var10[1] = 0.1D * (this.xData[0][var6 - 1] - this.xData[0][0]) / (this.yData[var6 - 1] - this.yData[0]);
                }

                if (var10[2] == 0.0D) {
                    var10[2] = 0.1D;
                }

                int var13;
                if (this.xErrorsEntered) {
                    Logistic5FixedFunctionDual var14 = new Logistic5FixedFunctionDual();
                    var14.setBottom(var1);
                    var14.setTop(var3);
                    var14.setXerrors(this.xErrors);
                    var14.setYerrors(this.yErrors);
                    this.simplexFlag = 3;
                    this.nonLinStatsNeeded = true;
                    this.dualErrorsRequired = true;
                    this.nelderMead(var14, (Object)null, var9, var10, this.fTol, this.nMax);
                    if (var5 == 1) {
                        if (!this.suppressPrint) {
                            this.print();
                        }

                        var13 = this.plotXY((RegressionFunction3)var14);
                        if (var13 != -2 && !this.suppressYYplot) {
                            this.plotYY();
                        }
                    }
                } else {
                    Logistic5FixedFunction var15 = new Logistic5FixedFunction();
                    var15.setBottom(var1);
                    var15.setTop(var3);
                    this.simplexFlag = 1;
                    this.nonLinStatsNeeded = true;
                    this.dualErrorsRequired = false;
                    this.nelderMead(var15, (Object)null, var9, var10, this.fTol, this.nMax);
                    if (var5 == 1) {
                        if (!this.suppressPrint) {
                            this.print();
                        }

                        var13 = this.plotXY((RegressionFunction)var15);
                        if (var13 != -2 && !this.suppressYYplot) {
                            this.plotYY();
                        }
                    }
                }

            }
        }
    }

    public void rectangularHyperbola() {
        this.fitRectangularHyperbola(0);
    }

    public void rectangularHyperbolaPlot() {
        this.fitRectangularHyperbola(1);
    }

    protected void fitRectangularHyperbola(int var1) {
        if (this.multipleY) {
            throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
        } else {
            this.lastMethod = 26;
            this.userSupplied = false;
            this.linNonLin = false;
            this.zeroCheck = false;
            this.nParam = 2;
            if (!this.scaleFlag) {
                this.nParam = 1;
            }

            this.degreesOfFreedom = this.nData - this.nParam;
            if (this.degreesOfFreedom < 1 && !this.ignoreDofFcheck) {
                throw new IllegalArgumentException("Degrees of freedom must be greater than 0");
            } else {
                sort(this.xData[0], this.yData, this.weight);
                double var2 = Fmath.minimum(this.yData);
                double var4 = Fmath.maximum(this.yData);
                byte var6 = 1;
                if (var2 < 0.0D) {
                    var6 = -1;
                }

                double var7 = (var4 - var2) / 2.0D;
                double var9 = this.xData[0][0];
                int var11 = 1;
                int var12 = this.yData.length;
                boolean var13 = true;

                while(var13) {
                    if (this.yData[var11] >= (double)var6 * var7) {
                        var9 = this.xData[0][var11];
                        var13 = false;
                    } else {
                        ++var11;
                        if (var11 >= var12) {
                            var9 = Stat.mean(this.xData[0]);
                            var11 = var12 - 1;
                            var13 = false;
                        }
                    }
                }

                double var14 = this.xData[0][var12 - 1];
                int var16 = var12 - 1;
                var13 = true;

                while(var13) {
                    if (this.yData[var16] <= (double)var6 * var7) {
                        var14 = this.xData[0][var16];
                        var13 = false;
                    } else {
                        --var16;
                        if (var16 < 0) {
                            var14 = Stat.mean(this.xData[0]);
                            var16 = 1;
                            var13 = false;
                        }
                    }
                }

                int var17 = (var11 + var16) / 2;
                double var18 = this.xData[0][var17];
                double[] var20 = new double[this.nParam];
                var20[0] = var18;
                if (this.scaleFlag) {
                    if (var6 == 1) {
                        var20[1] = var4;
                    } else {
                        var20[1] = var2;
                    }
                }

                double[] var21 = new double[this.nParam];

                for(int var22 = 0; var22 < this.nParam; ++var22) {
                    var21[var22] = 0.1D * var20[var22];
                }

                if (var21[0] == 0.0D) {
                    var21[0] = (this.xData[0][var12 - 1] - this.xData[0][0]) / 20.0D;
                }

                if (this.scaleFlag && var21[1] == 0.0D) {
                    var21[1] = 0.1D * (this.yData[var12 - 1] - this.yData[0]);
                }

                int var24;
                if (this.xErrorsEntered) {
                    RectangularHyperbolaFunctionDual var25 = new RectangularHyperbolaFunctionDual();
                    var25.setScaleOption(this.scaleFlag);
                    var25.setScaleFactor(this.yScaleFactor);
                    var25.setXerrors(this.xErrors);
                    var25.setYerrors(this.yErrors);
                    this.simplexFlag = 3;
                    this.nonLinStatsNeeded = true;
                    this.dualErrorsRequired = true;
                    this.nelderMead(var25, (Object)null, var20, var21, this.fTol, this.nMax);
                    if (var1 == 1) {
                        if (!this.suppressPrint) {
                            this.print();
                        }

                        var24 = this.plotXY((RegressionFunction3)var25);
                        if (var24 != -2 && !this.suppressYYplot) {
                            this.plotYY();
                        }
                    }
                } else {
                    RectangularHyperbolaFunction var26 = new RectangularHyperbolaFunction();
                    var26.setScaleOption(this.scaleFlag);
                    var26.setScaleFactor(this.yScaleFactor);
                    this.simplexFlag = 1;
                    this.nonLinStatsNeeded = true;
                    this.dualErrorsRequired = false;
                    this.nelderMead(var26, (Object)null, var20, var21, this.fTol, this.nMax);
                    if (var1 == 1) {
                        if (!this.suppressPrint) {
                            this.print();
                        }

                        var24 = this.plotXY((RegressionFunction)var26);
                        if (var24 != -2 && !this.suppressYYplot) {
                            this.plotYY();
                        }
                    }
                }

            }
        }
    }

    public void shiftedRectangularHyperbola() {
        this.fitShiftedRectangularHyperbola(0);
    }

    public void shiftedRectangularHyperbolaPlot() {
        this.fitShiftedRectangularHyperbola(1);
    }

    protected void fitShiftedRectangularHyperbola(int var1) {
        if (this.multipleY) {
            throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
        } else {
            this.lastMethod = 52;
            this.userSupplied = false;
            this.linNonLin = false;
            this.zeroCheck = false;
            this.nParam = 3;
            boolean var2 = this.scaleFlag;
            this.scaleFlag = false;
            this.degreesOfFreedom = this.nData - this.nParam;
            if (this.degreesOfFreedom < 1 && !this.ignoreDofFcheck) {
                throw new IllegalArgumentException("Degrees of freedom must be greater than 0");
            } else {
                sort(this.xData[0], this.yData, this.weight);
                double var3 = Fmath.minimum(this.yData);
                double var5 = Fmath.maximum(this.yData);
                byte var7 = 1;
                if (var3 < 0.0D) {
                    var7 = -1;
                }

                double var8 = (var5 - var3) / 2.0D;
                double var10 = this.xData[0][0];
                int var12 = 1;
                int var13 = this.yData.length;
                boolean var14 = true;

                while(var14) {
                    if (this.yData[var12] >= (double)var7 * var8) {
                        var10 = this.xData[0][var12];
                        var14 = false;
                    } else {
                        ++var12;
                        if (var12 >= var13) {
                            var10 = Stat.mean(this.xData[0]);
                            var12 = var13 - 1;
                            var14 = false;
                        }
                    }
                }

                double var15 = this.xData[0][var13 - 1];
                int var17 = var13 - 1;
                var14 = true;

                while(var14) {
                    if (this.yData[var17] <= (double)var7 * var8) {
                        var15 = this.xData[0][var17];
                        var14 = false;
                    } else {
                        --var17;
                        if (var17 < 0) {
                            var15 = Stat.mean(this.xData[0]);
                            var17 = 1;
                            var14 = false;
                        }
                    }
                }

                int var18 = (var12 + var17) / 2;
                double var19 = this.xData[0][var18];
                double[] var21 = new double[this.nParam];
                var21[0] = var19;
                var21[1] = this.yData[0];
                if (var7 == 1) {
                    var21[2] = var5;
                } else {
                    var21[2] = var3;
                }

                double[] var22 = new double[this.nParam];

                for(int var23 = 0; var23 < this.nParam; ++var23) {
                    var22[var23] = 0.1D * var21[var23];
                }

                if (var22[0] == 0.0D) {
                    var22[0] = (this.xData[0][var13 - 1] - this.xData[0][0]) / 20.0D;
                }

                if (var22[1] == 0.0D) {
                    var22[1] = Math.abs(this.yData[var13 - 1] - this.yData[0]) / 20.0D;
                }

                if (var22[2] == 0.0D) {
                    var22[1] = 0.1D * (this.yData[var13 - 1] - this.yData[0]);
                }

                int var25;
                if (this.xErrorsEntered) {
                    ShiftedRectangularHyperbolaFunctionDual var26 = new ShiftedRectangularHyperbolaFunctionDual();
                    var26.setYerrors(this.yErrors);
                    var26.setXerrors(this.xErrors);
                    this.simplexFlag = 3;
                    this.nonLinStatsNeeded = true;
                    this.dualErrorsRequired = true;
                    this.nelderMead(var26, (Object)null, var21, var22, this.fTol, this.nMax);
                    if (var1 == 1) {
                        if (!this.suppressPrint) {
                            this.print();
                        }

                        var25 = this.plotXY((RegressionFunction3)var26);
                        if (var25 != -2 && !this.suppressYYplot) {
                            this.plotYY();
                        }
                    }
                } else {
                    ShiftedRectangularHyperbolaFunction var27 = new ShiftedRectangularHyperbolaFunction();
                    this.simplexFlag = 1;
                    this.nonLinStatsNeeded = true;
                    this.dualErrorsRequired = false;
                    this.nelderMead(var27, (Object)null, var21, var22, this.fTol, this.nMax);
                    if (var1 == 1) {
                        if (!this.suppressPrint) {
                            this.print();
                        }

                        var25 = this.plotXY((RegressionFunction)var27);
                        if (var25 != -2 && !this.suppressYYplot) {
                            this.plotYY();
                        }
                    }
                }

                this.scaleFlag = var2;
            }
        }
    }

    public void stepFunction() {
        this.fitStepFunction(0);
    }

    public void stepFunctionPlot() {
        this.fitStepFunction(1);
    }

    protected void fitStepFunction(int var1) {
        if (this.multipleY) {
            throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
        } else {
            this.lastMethod = 27;
            this.userSupplied = false;
            this.linNonLin = false;
            this.zeroCheck = false;
            this.nParam = 2;
            if (!this.scaleFlag) {
                this.nParam = 1;
            }

            this.degreesOfFreedom = this.nData - this.nParam;
            if (this.degreesOfFreedom < 1 && !this.ignoreDofFcheck) {
                throw new IllegalArgumentException("Degrees of freedom must be greater than 0");
            } else {
                sort(this.xData[0], this.yData, this.weight);
                double var2 = Fmath.minimum(this.yData);
                double var4 = Fmath.maximum(this.yData);
                byte var6 = 1;
                if (var2 < 0.0D) {
                    var6 = -1;
                }

                double var7 = (var4 - var2) / 2.0D;
                double var9 = this.xData[0][0];
                int var11 = 1;
                int var12 = this.yData.length;
                boolean var13 = true;

                while(var13) {
                    if (this.yData[var11] >= (double)var6 * var7) {
                        var9 = this.xData[0][var11];
                        var13 = false;
                    } else {
                        ++var11;
                        if (var11 >= var12) {
                            var9 = Stat.mean(this.xData[0]);
                            var11 = var12 - 1;
                            var13 = false;
                        }
                    }
                }

                double var14 = this.xData[0][var12 - 1];
                int var16 = var12 - 1;
                var13 = true;

                while(var13) {
                    if (this.yData[var16] <= (double)var6 * var7) {
                        var14 = this.xData[0][var16];
                        var13 = false;
                    } else {
                        --var16;
                        if (var16 < 0) {
                            var14 = Stat.mean(this.xData[0]);
                            var16 = 1;
                            var13 = false;
                        }
                    }
                }

                int var17 = (var11 + var16) / 2;
                double var18 = this.xData[0][var17];
                double[] var20 = new double[this.nParam];
                var20[0] = var18;
                if (this.scaleFlag) {
                    if (var6 == 1) {
                        var20[1] = var4;
                    } else {
                        var20[1] = var2;
                    }
                }

                double[] var21 = new double[this.nParam];

                for(int var22 = 0; var22 < this.nParam; ++var22) {
                    var21[var22] = 0.1D * var20[var22];
                }

                if (var21[0] == 0.0D) {
                    var21[0] = (this.xData[0][var12 - 1] - this.xData[0][0]) / 20.0D;
                }

                if (this.scaleFlag && var21[1] == 0.0D) {
                    var21[1] = 0.1D * (this.yData[var12 - 1] - this.yData[0]);
                }

                StepFunctionFunction var25 = new StepFunctionFunction();
                var25.setScaleOption(this.scaleFlag);
                var25.setScaleFactor(this.yScaleFactor);
                this.simplexFlag = 1;
                this.nonLinStatsNeeded = true;
                this.dualErrorsRequired = false;
                this.nelderMead(var25, (Object)null, var20, var21, this.fTol, this.nMax);
                if (var1 == 1) {
                    if (!this.suppressPrint) {
                        this.print();
                    }

                    int var24 = this.plotXY((RegressionFunction)var25);
                    if (var24 != -2 && !this.suppressYYplot) {
                        this.plotYY();
                    }
                }

            }
        }
    }

    public void logistic() {
        this.fitLogistic(0);
    }

    public void logisticPlot() {
        this.fitLogistic(1);
    }

    protected void fitLogistic(int var1) {
        if (this.multipleY) {
            throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
        } else {
            this.lastMethod = 30;
            this.userSupplied = false;
            this.linNonLin = false;
            this.zeroCheck = false;
            this.nParam = 3;
            if (!this.scaleFlag) {
                this.nParam = 2;
            }

            this.degreesOfFreedom = this.nData - this.nParam;
            if (this.degreesOfFreedom < 1 && !this.ignoreDofFcheck) {
                throw new IllegalArgumentException("Degrees of freedom must be greater than 0");
            } else {
                sort(this.xData[0], this.yData, this.weight);
                Double var2 = null;
                ArrayList var3 = dataSign(this.yData);
                var2 = (Double)var3.get(4);
                double var4 = var2;
                boolean var6 = false;
                if (var4 < 0.0D) {
                    System.out.println("Regression.fitLogistic(): This implementation of the Logistic distribution takes only positive y values\n(noise taking low values below zero are allowed)");
                    System.out.println("All y values have been multiplied by -1 before fitting");

                    for(int var7 = 0; var7 < this.nData; ++var7) {
                        this.yData[var7] = -this.yData[var7];
                    }

                    var3 = dataSign(this.yData);
                    var6 = true;
                }

                ArrayList var22 = dataSign(this.yData);
                Integer var8 = null;
                var8 = (Integer)var22.get(5);
                int var9 = var8;
                double var10 = this.xData[0][var9];
                double var12 = Math.sqrt(6.0D) * halfWidth(this.xData[0], this.yData) / 3.141592653589793D;
                var2 = (Double)var22.get(4);
                double var14 = var2;
                var14 = var14 * var12 * Math.sqrt(6.283185307179586D);
                double[] var16 = new double[this.nParam];
                double[] var17 = new double[this.nParam];
                var16[0] = var10;
                var16[1] = var12;
                if (this.scaleFlag) {
                    var16[2] = var14;
                }

                var17[0] = 0.1D * var12;
                var17[1] = 0.1D * var16[1];
                if (var17[1] == 0.0D) {
                    ArrayList var18 = dataSign(this.xData[0]);
                    Double var19 = null;
                    var19 = (Double)var18.get(2);
                    double var20 = var19;
                    if (var20 == 0.0D) {
                        var19 = (Double)var18.get(0);
                        var20 = var19;
                    }

                    var17[0] = var20 * 0.1D;
                }

                if (this.scaleFlag) {
                    var17[2] = 0.1D * var16[2];
                }

                LogisticFunction var23 = new LogisticFunction();
                this.addConstraint(1, -1, 0.0D);
                var23.setScaleOption(this.scaleFlag);
                var23.setScaleFactor(this.yScaleFactor);
                this.simplexFlag = 1;
                this.nonLinStatsNeeded = true;
                this.dualErrorsRequired = false;
                this.nelderMead(var23, (Object)null, var16, var17, this.fTol, this.nMax);
                int var24;
                if (var1 == 1) {
                    if (!this.suppressPrint) {
                        this.print();
                    }

                    var24 = this.plotXY((RegressionFunction)var23);
                    if (var24 != -2 && !this.suppressYYplot) {
                        this.plotYY();
                    }
                }

                if (var6) {
                    for(var24 = 0; var24 < this.nData - 1; ++var24) {
                        this.yData[var24] = -this.yData[var24];
                    }
                }

            }
        }
    }

    public void beta() {
        this.fitBeta(0, 0);
    }

    public void betaPlot() {
        this.fitBeta(1, 0);
    }

    public void betaMinMax() {
        this.fitBeta(0, 1);
    }

    public void betaMinMaxPlot() {
        this.fitBeta(1, 1);
    }

    protected void fitBeta(int var1, int var2) {
        if (this.multipleY) {
            throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
        } else {
            this.userSupplied = false;
            switch(var2) {
                case 0:
                    this.lastMethod = 31;
                    this.nParam = 3;
                    break;
                case 1:
                    this.lastMethod = 32;
                    this.nParam = 5;
            }

            if (!this.scaleFlag) {
                --this.nParam;
            }

            this.zeroCheck = false;
            this.degreesOfFreedom = this.nData - this.nParam;
            if (this.degreesOfFreedom < 1 && !this.ignoreDofFcheck) {
                throw new IllegalArgumentException("Degrees of freedom must be greater than 0");
            } else {
                sort(this.xData[0], this.yData, this.weight);
                Double var3 = null;
                ArrayList var4 = dataSign(this.yData);
                var3 = (Double)var4.get(4);
                double var5 = var3;
                boolean var7 = false;
                if (var5 < 0.0D) {
                    System.out.println("Regression.fitBeta(): This implementation of the Beta distribution takes only positive y values\n(noise taking low values below zero are allowed)");
                    System.out.println("All y values have been multiplied by -1 before fitting");

                    for(int var8 = 0; var8 < this.nData; ++var8) {
                        this.yData[var8] = -this.yData[var8];
                    }

                    var4 = dataSign(this.yData);
                    var7 = true;
                }

                ArrayList var34 = dataSign(this.xData[0]);
                Integer var9 = null;
                var9 = (Integer)var4.get(5);
                int var10 = var9;
                double var11 = this.xData[0][var10];
                var3 = (Double)var34.get(0);
                double var13 = var3;
                var3 = (Double)var34.get(2);
                double var15 = var3;
                var3 = (Double)var34.get(8);
                double var17 = var3;
                if (var2 == 0) {
                    if (var13 < 0.0D) {
                        System.out.println("Regression: beta: data points must be greater than or equal to 0");
                        System.out.println("method betaMinMax used in place of method beta");
                        var2 = 1;
                        this.lastMethod = 32;
                        this.nParam = 5;
                    }

                    if (var15 > 1.0D) {
                        System.out.println("Regression: beta: data points must be less than or equal to 1");
                        System.out.println("method betaMinMax used in place of method beta");
                        var2 = 1;
                        this.lastMethod = 32;
                        this.nParam = 5;
                    }
                }

                double var19 = var11;
                double var21 = var17;
                if (var2 == 1) {
                    var19 = (var11 - var13 * 0.9D) / (var15 * 1.2D - var13 * 0.9D);
                    var21 = (var17 - var13 * 0.9D) / (var15 * 1.2D - var13 * 0.9D);
                }

                double var23 = 2.0D * var19 * var21 / (var19 - var21);
                if (var23 < 1.3D) {
                    var23 = 1.6D;
                }

                double var25 = var23 * (1.0D - var21) / var21;
                if (var25 <= 1.3D) {
                    var25 = 1.6D;
                }

                double var27 = 0.0D;
                if (var2 == 0) {
                    var27 = var5 / Stat.betaPDF(var23, var25, var11);
                } else {
                    var27 = var5 / Stat.betaPDF(var13, var15, var23, var25, var11);
                }

                if (var27 < 0.0D) {
                    var27 = 1.0D;
                }

                double[] var29 = new double[this.nParam];
                double[] var30 = new double[this.nParam];
                switch(var2) {
                    case 0:
                        var29[0] = var23;
                        var29[1] = var25;
                        if (this.scaleFlag) {
                            var29[2] = var27;
                        }

                        var30[0] = 0.1D * var29[0];
                        var30[1] = 0.1D * var29[1];
                        if (this.scaleFlag) {
                            var30[2] = 0.1D * var29[2];
                        }

                        this.addConstraint(0, -1, 1.0D);
                        this.addConstraint(1, -1, 1.0D);
                        break;
                    case 1:
                        var29[0] = var23;
                        var29[1] = var25;
                        var29[2] = 0.9D * var13;
                        var29[3] = 1.1D * var15;
                        if (this.scaleFlag) {
                            var29[4] = var27;
                        }

                        var30[0] = 0.1D * var29[0];
                        var30[1] = 0.1D * var29[1];
                        var30[2] = 0.1D * var29[2];
                        var30[3] = 0.1D * var29[3];
                        if (this.scaleFlag) {
                            var30[4] = 0.1D * var29[4];
                        }

                        this.addConstraint(0, -1, 1.0D);
                        this.addConstraint(1, -1, 1.0D);
                        this.addConstraint(2, 1, var13);
                        this.addConstraint(3, -1, var15);
                }

                BetaFunction var31 = new BetaFunction();
                var31.setTypeFlag(var2);
                var31.setScaleOption(this.scaleFlag);
                var31.setScaleFactor(this.yScaleFactor);
                this.simplexFlag = 1;
                this.nonLinStatsNeeded = true;
                this.dualErrorsRequired = false;
                this.simplexFlag = 1;
                this.nonLinStatsNeeded = true;
                this.dualErrorsRequired = false;
                this.nelderMead(var31, (Object)null, var29, var30, this.fTol, this.nMax);
                int var33;
                if (var1 == 1) {
                    if (!this.suppressPrint) {
                        this.print();
                    }

                    var33 = this.plotXY((RegressionFunction)var31);
                    if (var33 != -2 && !this.suppressYYplot) {
                        this.plotYY();
                    }
                }

                if (var7) {
                    for(var33 = 0; var33 < this.nData - 1; ++var33) {
                        this.yData[var33] = -this.yData[var33];
                    }
                }

            }
        }
    }

    public void gamma() {
        this.fitGamma(0, 0);
    }

    public void gammaPlot() {
        this.fitGamma(1, 0);
    }

    public void gammaStandard() {
        this.fitGamma(0, 1);
    }

    public void gammaStandardPlot() {
        this.fitGamma(1, 1);
    }

    protected void fitGamma(int var1, int var2) {
        if (this.multipleY) {
            throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
        } else {
            this.userSupplied = false;
            switch(var2) {
                case 0:
                    this.lastMethod = 33;
                    this.nParam = 4;
                    break;
                case 1:
                    this.lastMethod = 34;
                    this.nParam = 2;
            }

            if (!this.scaleFlag) {
                --this.nParam;
            }

            this.zeroCheck = false;
            this.degreesOfFreedom = this.nData - this.nParam;
            if (this.degreesOfFreedom < 1 && !this.ignoreDofFcheck) {
                throw new IllegalArgumentException("Degrees of freedom must be greater than 0");
            } else {
                sort(this.xData[0], this.yData, this.weight);
                Double var3 = null;
                ArrayList var4 = dataSign(this.yData);
                var3 = (Double)var4.get(4);
                double var5 = var3;
                boolean var7 = false;
                if (var5 < 0.0D) {
                    System.out.println("Regression.fitGamma(): This implementation of the Gamma distribution takes only positive y values\n(noise taking low values below zero are allowed)");
                    System.out.println("All y values have been multiplied by -1 before fitting");

                    for(int var8 = 0; var8 < this.nData; ++var8) {
                        this.yData[var8] = -this.yData[var8];
                    }

                    var4 = dataSign(this.yData);
                    var7 = true;
                }

                ArrayList var32 = dataSign(this.xData[0]);
                Integer var9 = null;
                var9 = (Integer)var4.get(5);
                int var10 = var9;
                double var11 = this.xData[0][var10];
                var3 = (Double)var32.get(0);
                double var13 = var3;
                var3 = (Double)var32.get(2);
                double var15 = var3;
                var3 = (Double)var32.get(8);
                double var17 = var3;
                if (var2 == 1 && var13 < 0.0D) {
                    System.out.println("Regression: gammaStandard: data points must be greater than or equal to 0");
                    System.out.println("method gamma used in place of method gammaStandard");
                    var2 = 0;
                    this.lastMethod = 33;
                    this.nParam = 2;
                }

                double var19 = 0.8D * var13;
                if (var19 == 0.0D) {
                    var19 = -0.1D;
                }

                double var21 = var17 - var11;
                if (var21 <= 0.0D) {
                    var21 = 1.0D;
                }

                double var23 = (var17 + var19) / var21;
                if (var2 == 1) {
                    var23 = var17;
                }

                if (var23 <= 0.0D) {
                    var23 = 1.0D;
                }

                double var25 = 0.0D;
                if (var2 == 0) {
                    var25 = var5 / Stat.gammaPDF(var19, var21, var23, var11);
                } else {
                    var25 = var5 / Stat.gammaPDF(var23, var11);
                }

                if (var25 < 0.0D) {
                    var25 = 1.0D;
                }

                double[] var27 = new double[this.nParam];
                double[] var28 = new double[this.nParam];
                switch(var2) {
                    case 0:
                        var27[0] = var19;
                        var27[1] = var21;
                        var27[2] = var23;
                        if (this.scaleFlag) {
                            var27[3] = var25;
                        }

                        var28[0] = 0.1D * var27[0];
                        var28[1] = 0.1D * var27[1];
                        var28[2] = 0.1D * var27[2];
                        if (this.scaleFlag) {
                            var28[3] = 0.1D * var27[3];
                        }

                        this.addConstraint(1, -1, 0.0D);
                        this.addConstraint(2, -1, 0.0D);
                        break;
                    case 1:
                        var27[0] = var23;
                        if (this.scaleFlag) {
                            var27[1] = var25;
                        }

                        var28[0] = 0.1D * var27[0];
                        if (this.scaleFlag) {
                            var28[1] = 0.1D * var27[1];
                        }

                        this.addConstraint(0, -1, 0.0D);
                }

                GammaFunction var29 = new GammaFunction();
                var29.setTypeFlag(var2);
                var29.setScaleOption(this.scaleFlag);
                var29.setScaleFactor(this.yScaleFactor);
                this.simplexFlag = 1;
                this.nonLinStatsNeeded = true;
                this.dualErrorsRequired = false;
                this.nelderMead(var29, (Object)null, var27, var28, this.fTol, this.nMax);
                int var31;
                if (var1 == 1) {
                    if (!this.suppressPrint) {
                        this.print();
                    }

                    var31 = this.plotXY((RegressionFunction)var29);
                    if (var31 != -2 && !this.suppressYYplot) {
                        this.plotYY();
                    }
                }

                if (var7) {
                    for(var31 = 0; var31 < this.nData - 1; ++var31) {
                        this.yData[var31] = -this.yData[var31];
                    }
                }

            }
        }
    }

    public void erlang() {
        this.fitErlang(0, 0);
    }

    public void erlangPlot() {
        this.fitErlang(1, 0);
    }

    protected void fitErlang(int var1, int var2) {
        if (this.multipleY) {
            throw new IllegalArgumentException("This method cannot handle multiply dimensioned y arrays");
        } else {
            this.lastMethod = 35;
            this.userSupplied = false;
            byte var3 = 2;
            byte var4 = 4;
            this.nParam = var4;
            if (!this.scaleFlag) {
                --this.nParam;
            }

            this.zeroCheck = false;
            this.degreesOfFreedom = this.nData - this.nParam;
            if (this.degreesOfFreedom < 1 && !this.ignoreDofFcheck) {
                throw new IllegalArgumentException("Degrees of freedom must be greater than 0");
            } else {
                sort(this.xData[0], this.yData, this.weight);
                Double var5 = null;
                ArrayList var6 = dataSign(this.yData);
                var5 = (Double)var6.get(4);
                double var7 = var5;
                boolean var9 = false;
                if (var7 < 0.0D) {
                    System.out.println("Regression.fitGamma(): This implementation of the Erlang distribution takes only positive y values\n(noise taking low values below zero are allowed)");
                    System.out.println("All y values have been multiplied by -1 before fitting");

                    for(int var10 = 0; var10 < this.nData; ++var10) {
                        this.yData[var10] = -this.yData[var10];
                    }

                    var6 = dataSign(this.yData);
                    var9 = true;
                }

                ArrayList var57 = dataSign(this.xData[0]);
                Integer var11 = null;
                var11 = (Integer)var6.get(5);
                int var12 = var11;
                double var13 = this.xData[0][var12];
                var5 = (Double)var57.get(0);
                double var15 = var5;
                var5 = (Double)var57.get(2);
                double var17 = var5;
                var5 = (Double)var57.get(8);
                double var19 = var5;
                if (var15 < 0.0D) {
                    throw new IllegalArgumentException("data points must be greater than or equal to 0");
                } else {
                    double var21 = 0.8D * var15;
                    if (var21 == 0.0D) {
                        var21 = -0.1D;
                    }

                    double var23 = var19 - var13;
                    if (var23 <= 0.0D) {
                        var23 = 1.0D;
                    }

                    double var25 = (var19 + var21) / var23;
                    if (var2 == 1) {
                        var25 = var19;
                    }

                    if (var25 <= 0.0D) {
                        var25 = 1.0D;
                    }

                    double var27 = 0.0D;
                    var27 = var7 / Stat.gammaPDF(var21, var23, var25, var13);
                    if (var27 < 0.0D) {
                        var27 = 1.0D;
                    }

                    double[] var29 = new double[this.nParam];
                    double[] var30 = new double[this.nParam];
                    var29[0] = var21;
                    var29[1] = var23;
                    var29[2] = var25;
                    if (this.scaleFlag) {
                        var29[3] = var27;
                    }

                    var30[0] = 0.1D * var29[0];
                    var30[1] = 0.1D * var29[1];
                    var30[2] = 0.1D * var29[2];
                    if (this.scaleFlag) {
                        var30[3] = 0.1D * var29[3];
                    }

                    this.addConstraint(1, -1, 0.0D);
                    this.addConstraint(2, -1, 0.0D);
                    GammaFunction var31 = new GammaFunction();
                    var31.setTypeFlag(var2);
                    var31.setScaleOption(this.scaleFlag);
                    var31.setScaleFactor(this.yScaleFactor);
                    this.simplexFlag = 1;
                    this.nonLinStatsNeeded = true;
                    this.dualErrorsRequired = false;
                    this.nelderMead(var31, (Object)null, var29, var30, this.fTol, this.nMax);
                    this.removeConstraints();
                    double[] var33 = this.getCoeff();
                    this.nParam = var3;
                    var29 = new double[this.nParam];
                    var30 = new double[this.nParam];
                    if (var33[3] < 0.0D) {
                        var33[3] *= -1.0D;
                    }

                    var29[0] = 1.0D / var33[1];
                    if (this.scaleFlag) {
                        var29[1] = var33[3];
                    }

                    var30[0] = 0.1D * var29[0];
                    if (this.scaleFlag) {
                        var30[1] = 0.1D * var29[1];
                    }

                    this.addConstraint(0, -1, 0.0D);
                    double var34 = (double)Math.round(var33[2]);
                    double var36 = var34;
                    ErlangFunction var38 = new ErlangFunction();
                    var38.setScaleOption(this.scaleFlag);
                    var38.setScaleFactor(this.yScaleFactor);
                    var38.setKay(var34);
                    boolean var39 = true;
                    double var40 = 0.0D / 0.0;
                    double var42 = 0.0D / 0.0;
                    double var44 = 0.0D / 0.0;
                    double var46 = 0.0D / 0.0;
                    byte var48 = 1;
                    int var49 = 0;

                    while(var39) {
                        this.simplexFlag = 1;
                        this.nonLinStatsNeeded = true;
                        this.dualErrorsRequired = false;
                        this.nelderMead(var38, (Object)null, var29, var30, this.fTol, this.nMax);
                        double var51 = this.getSumOfSquares();
                        if (var48 == 1) {
                            var48 = 2;
                            var40 = var51;
                            ++var36;
                            var29[0] = 1.0D / var33[1];
                            if (this.scaleFlag) {
                                var29[1] = var33[3];
                            }

                            var30[0] = 0.1D * var29[0];
                            if (this.scaleFlag) {
                                var30[1] = 0.1D * var29[1];
                            }

                            this.addConstraint(0, -1, 0.0D);
                            var38.setKay(var36);
                        } else if (var51 <= var40) {
                            if (var51 == var40) {
                                ++var49;
                                if (var49 == 10) {
                                    var42 = var40;
                                    var44 = var36 - 5.0D;
                                    var39 = false;
                                }
                            }

                            var40 = var51;
                            ++var36;
                            var29[0] = 1.0D / var33[1];
                            if (this.scaleFlag) {
                                var29[1] = var33[3];
                            }

                            var30[0] = 0.1D * var29[0];
                            if (this.scaleFlag) {
                                var30[1] = 0.1D * var29[1];
                            }

                            this.addConstraint(0, -1, 0.0D);
                            var38.setKay(var36);
                        } else {
                            var42 = var40;
                            var44 = var36 - 1.0D;
                            var39 = false;
                        }
                    }

                    if (var34 == 1.0D) {
                        var46 = var44;
                    } else {
                        var48 = 1;
                        var39 = true;
                        var40 = 0.0D / 0.0;
                        double var50 = 0.0D / 0.0;
                        double var52 = 0.0D / 0.0;
                        var29[0] = 1.0D / var33[1];
                        if (this.scaleFlag) {
                            var29[1] = var33[3];
                        }

                        var30[0] = 0.1D * var29[0];
                        if (this.scaleFlag) {
                            var30[1] = 0.1D * var29[1];
                        }

                        this.addConstraint(0, -1, 0.0D);
                        var36 = var34;
                        var38.setKay(var34);

                        while(var39) {
                            this.simplexFlag = 1;
                            this.nonLinStatsNeeded = true;
                            this.dualErrorsRequired = false;
                            this.nelderMead(var38, (Object)null, var29, var30, this.fTol, this.nMax);
                            double var55 = this.getSumOfSquares();
                            if (var48 == 1) {
                                var48 = 2;
                                var40 = var55;
                                --var36;
                                if (Math.rint(var36) < 1.0D) {
                                    var50 = var55;
                                    var52 = var36 + 1.0D;
                                    var39 = false;
                                } else {
                                    var29[0] = 1.0D / var33[1];
                                    if (this.scaleFlag) {
                                        var29[1] = var33[3];
                                    }

                                    var30[0] = 0.1D * var29[0];
                                    if (this.scaleFlag) {
                                        var30[1] = 0.1D * var29[1];
                                    }

                                    this.addConstraint(0, -1, 0.0D);
                                    var38.setKay(var36);
                                }
                            } else if (var55 <= var40) {
                                var40 = var55;
                                --var36;
                                if (Math.rint(var36) < 1.0D) {
                                    var50 = var55;
                                    var52 = var36 + 1.0D;
                                    var39 = false;
                                } else {
                                    var29[0] = 1.0D / var33[1];
                                    if (this.scaleFlag) {
                                        var29[1] = var33[3];
                                    }

                                    var30[0] = 0.1D * var29[0];
                                    if (this.scaleFlag) {
                                        var30[1] = 0.1D * var29[1];
                                    }

                                    this.addConstraint(0, -1, 0.0D);
                                    var38.setKay(var36);
                                }
                            } else {
                                var50 = var40;
                                var52 = var36 + 1.0D;
                                var39 = false;
                            }
                        }

                        if (var50 < var42) {
                            var46 = var52;
                        } else {
                            var46 = var44;
                        }
                    }

                    var29[0] = 1.0D / var33[1];
                    if (this.scaleFlag) {
                        var29[1] = var33[3];
                    }

                    var30[0] = 0.1D * var29[0];
                    if (this.scaleFlag) {
                        var30[1] = 0.1D * var29[1];
                    }

                    this.addConstraint(0, -1, 0.0D);
                    var38.setScaleOption(this.scaleFlag);
                    var38.setScaleFactor(this.yScaleFactor);
                    var38.setKay((double)Math.round(var46));
                    this.kayValue = (double)Math.round(var46);
                    this.simplexFlag = 1;
                    this.nonLinStatsNeeded = true;
                    this.dualErrorsRequired = false;
                    this.nelderMead(var38, (Object)null, var29, var30, this.fTol, this.nMax);
                    double[] var58 = this.getCoeff();
                    var29[0] = var58[0];
                    if (this.scaleFlag) {
                        var29[1] = var58[1];
                    }

                    var30[0] = 0.1D * var29[0];
                    if (this.scaleFlag) {
                        var30[1] = 0.1D * var29[1];
                    }

                    this.addConstraint(0, -1, 0.0D);
                    var38.setScaleOption(this.scaleFlag);
                    var38.setScaleFactor(this.yScaleFactor);
                    var38.setKay((double)Math.round(var46));
                    this.kayValue = (double)Math.round(var46);
                    this.simplexFlag = 1;
                    this.nonLinStatsNeeded = true;
                    this.dualErrorsRequired = false;
                    this.nelderMead(var38, (Object)null, var29, var30, this.fTol, this.nMax);
                    int var53;
                    if (var1 == 1) {
                        if (!this.suppressPrint) {
                            this.print();
                        }

                        var53 = this.plotXY((RegressionFunction)var38);
                        if (var53 != -2 && !this.suppressYYplot) {
                            this.plotYY();
                        }
                    }

                    if (var9) {
                        for(var53 = 0; var53 < this.nData - 1; ++var53) {
                            this.yData[var53] = -this.yData[var53];
                        }
                    }

                }
            }
        }
    }

    public double getKayValue() {
        return this.kayValue;
    }

    public static double[][] histogramBins(double[] var0, double var1, double var3, double var5) {
        int var7 = 0;
        int var8 = var0.length;

        for(int var9 = 0; var9 < var8; ++var9) {
            if (var0[var9] <= var5) {
                ++var7;
            }
        }

        if (var7 != var8) {
            double[] var12 = new double[var7];
            int var10 = 0;

            for(int var11 = 0; var11 < var8; ++var11) {
                if (var0[var11] <= var5) {
                    var12[var10] = var0[var11];
                    ++var10;
                }
            }

            System.out.println(var8 - var7 + " data points, above histogram upper limit, excluded in histogramBins");
            return histogramBins(var12, var1, var3);
        } else {
            return histogramBins(var0, var1, var3);
        }
    }

    public static double[][] histogramBins(double[] var0, double var1, double var3) {
        double var5 = Fmath.maximum(var0);
        int var7 = (int)Math.ceil((var5 - var3) / var1);
        if (var3 + (double)var7 * var1 > var5) {
            ++var7;
        }

        int var8 = var0.length;
        int[] var9 = new int[var8];

        for(int var10 = 0; var10 < var8; ++var10) {
            var9[var10] = 0;
        }

        double[] var15 = new double[var7 + 1];
        var15[0] = var3;

        for(int var11 = 1; var11 <= var7; ++var11) {
            var15[var11] = var15[var11 - 1] + var1;
        }

        double[][] var16 = new double[2][var7];

        for(int var12 = 0; var12 < var7; ++var12) {
            var16[0][var12] = (var15[var12] + var15[var12 + 1]) / 2.0D;
            var16[1][var12] = 0.0D;
        }

        boolean var17 = true;

        int var13;
        int var14;
        for(var13 = 0; var13 < var8; ++var13) {
            var17 = true;
            var14 = 0;

            while(var17) {
                if (var14 == var7 - 1) {
                    if (var0[var13] >= var15[var14] && var0[var13] <= var15[var14 + 1] * (1.0D + histTol)) {
                        ++var16[1][var14];
                        var9[var13] = 1;
                        var17 = false;
                    }
                } else if (var0[var13] >= var15[var14] && var0[var13] < var15[var14 + 1]) {
                    ++var16[1][var14];
                    var9[var13] = 1;
                    var17 = false;
                }

                if (var17) {
                    if (var14 == var7 - 1) {
                        var17 = false;
                    } else {
                        ++var14;
                    }
                }
            }
        }

        var13 = 0;

        for(var14 = 0; var14 < var8; ++var14) {
            if (var9[var14] == 0) {
                ++var13;
                System.out.println("p " + var14 + " " + var0[var14] + " " + var15[0] + " " + var15[var7]);
            }
        }

        if (var13 > 0) {
            System.out.println(var13 + " data points, outside histogram limits, excluded in histogramBins");
        }

        return var16;
    }

    public static double[][] histogramBins(double[] var0, double var1) {
        double var3 = Fmath.minimum(var0);
        double var5 = Fmath.maximum(var0);
        double var7 = var5 - var3;
        double var9 = var3;
        int var11 = (int)Math.ceil(var7 / var1);
        double var12 = (double)var11 * var1;
        double var14 = var12 - var7;
        if (var14 >= 0.0D) {
            var9 = var3 - var14 / 2.0D;
        } else if (Math.abs(var14) / var7 > histTol) {
            boolean var16 = true;
            double var17 = histTol / (double)var11;
            int var19 = 0;

            while(var16) {
                var1 += var17;
                var12 = (double)var11 * var1;
                var14 = var12 - var7;
                if (var14 < 0.0D) {
                    ++var19;
                    if (var19 > 1000) {
                        var16 = false;
                        System.out.println("histogram method could not encompass all data within histogram\nContact Michael thomas Flanagan");
                    }
                } else {
                    var16 = false;
                }
            }
        }

        return histogramBins(var0, var1, var9);
    }

    public void simplex2(RegressionFunction2 var1, double[] var2, double[] var3, double var4, int var6) {
        this.simplex(var1, var2, var3, var4, var6);
    }

    public void simplex2(RegressionFunction2 var1, RegressionDerivativeFunction2 var2, double[] var3, double[] var4, double var5, int var7) {
        this.simplex(var1, var2, var3, var4, var5, var7);
    }

    public void simplexPlot2(RegressionFunction2 var1, double[] var2, double[] var3, double var4, int var6) {
        this.simplexPlot(var1, var2, var3, var4, var6);
    }

    public void simplexPlot2(RegressionFunction2 var1, RegressionDerivativeFunction2 var2, double[] var3, double[] var4, double var5, int var7) {
        this.simplexPlot(var1, var2, var3, var4, var5, var7);
    }

    public void simplex2(RegressionFunction2 var1, double[] var2, double[] var3, double var4) {
        this.simplex(var1, var2, var3, var4);
    }

    public void simplex2(RegressionFunction2 var1, RegressionDerivativeFunction2 var2, double[] var3, double[] var4, double var5) {
        this.simplex(var1, var2, var3, var4, var5);
    }

    public void simplexPlot2(RegressionFunction2 var1, double[] var2, double[] var3, double var4) {
        this.simplexPlot(var1, var2, var3, var4);
    }

    public void simplexPlot2(RegressionFunction2 var1, RegressionDerivativeFunction2 var2, double[] var3, double[] var4, double var5) {
        this.simplexPlot(var1, var2, var3, var4, var5);
    }

    public void simplex2(RegressionFunction2 var1, double[] var2, double[] var3, int var4) {
        this.simplex(var1, var2, var3, var4);
    }

    public void simplex2(RegressionFunction2 var1, RegressionDerivativeFunction2 var2, double[] var3, double[] var4, int var5) {
        this.simplex(var1, var2, var3, var4, var5);
    }

    public void simplexPlot2(RegressionFunction2 var1, double[] var2, double[] var3, int var4) {
        this.simplexPlot(var1, var2, var3, var4);
    }

    public void simplexPlot2(RegressionFunction2 var1, RegressionDerivativeFunction2 var2, double[] var3, double[] var4, int var5) {
        this.simplexPlot(var1, var2, var3, var4, var5);
    }

    public void simplex2(RegressionFunction2 var1, double[] var2, double[] var3) {
        this.simplex(var1, var2, var3);
    }

    public void simplex2(RegressionFunction2 var1, RegressionDerivativeFunction2 var2, double[] var3, double[] var4) {
        this.simplex(var1, var2, var3, var4);
    }

    public void simplexPlot2(RegressionFunction2 var1, double[] var2, double[] var3) {
        this.simplexPlot(var1, var2, var3);
    }

    public void simplexPlot2(RegressionFunction2 var1, RegressionDerivativeFunction2 var2, double[] var3, double[] var4) {
        this.simplexPlot(var1, var2, var3, var4);
    }

    public void simplex2(RegressionFunction2 var1, double[] var2, double var3, int var5) {
        this.simplex(var1, var2, var3, var5);
    }

    public void simplex2(RegressionFunction2 var1, RegressionDerivativeFunction2 var2, double[] var3, double var4, int var6) {
        this.simplex(var1, var2, var3, var4, var6);
    }

    public void simplexPlot2(RegressionFunction2 var1, double[] var2, double var3, int var5) {
        this.simplexPlot(var1, var2, var3, var5);
    }

    public void simplexPlot2(RegressionFunction2 var1, RegressionDerivativeFunction2 var2, double[] var3, double var4, int var6) {
        this.simplexPlot(var1, var2, var3, var4, var6);
    }

    public void simplex2(RegressionFunction2 var1, double[] var2, double var3) {
        this.simplex(var1, var2, var3);
    }

    public void simplex2(RegressionFunction2 var1, RegressionDerivativeFunction2 var2, double[] var3, double var4) {
        this.simplex(var1, var2, var3, var4);
    }

    public void simplexPlot2(RegressionFunction2 var1, double[] var2, double var3) {
        this.simplexPlot(var1, var2, var3);
    }

    public void simplexPlot2(RegressionFunction2 var1, RegressionDerivativeFunction2 var2, double[] var3, double var4) {
        this.simplexPlot(var1, var2, var3, var4);
    }

    public void simplex2(RegressionFunction2 var1, double[] var2, int var3) {
        this.simplex(var1, var2, var3);
    }

    public void simplex2(RegressionFunction2 var1, RegressionDerivativeFunction2 var2, double[] var3, int var4) {
        this.simplex(var1, var2, var3, var4);
    }

    public void simplexPlot2(RegressionFunction2 var1, double[] var2, int var3) {
        this.simplexPlot(var1, var2, var3);
    }

    public void simplexPlot2(RegressionFunction2 var1, RegressionDerivativeFunction2 var2, double[] var3, int var4) {
        this.simplexPlot(var1, var2, var3, var4);
    }

    public void simplex2(RegressionFunction2 var1, double[] var2) {
        this.simplex(var1, var2);
    }

    public void simplex2(RegressionFunction2 var1, RegressionDerivativeFunction2 var2, double[] var3) {
        this.simplex(var1, var2, var3);
    }

    public void simplexPlot2(RegressionFunction2 var1, double[] var2) {
        this.simplexPlot(var1, var2);
    }

    public void simplexPlot2(RegressionFunction2 var1, RegressionDerivativeFunction2 var2, double[] var3) {
        this.simplexPlot(var1, var2, var3);
    }

    protected int plotXY2(RegressionFunction2 var1, String var2) {
        return this.plotXY(var1, var2);
    }

    protected int plotXY2(RegressionFunction2 var1) {
        return this.plotXY(var1);
    }

    public void pareto() {
        this.fitPareto(0, 2);
    }

    public void paretoPlot() {
        this.fitPareto(1, 2);
    }
}
