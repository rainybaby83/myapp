
package com.flanagan.analysis;

import com.flanagan.io.Db;
import com.flanagan.io.FileChooser;
import com.flanagan.io.FileInput;
import com.flanagan.io.FileOutput;
import com.flanagan.math.Conv;
import com.flanagan.math.Fmath;
import com.flanagan.math.Matrix;
import java.math.BigDecimal;
import java.math.BigInteger;

public class ANOVA {
    private String[] title = new String[3];
    private int nTitle = 0;
    private boolean titleCheck = false;
    private String inputFilename = null;
    private String outputFilename = null;
    private int fileOption = 1;
    private boolean fileOptionSet = false;
    private int trunc = 6;
    private int fieldD = 13;
    private boolean truncAll = false;
    private int originalDataType = -1;
    private Object originalData = null;
    private double[][] responses0 = (double[][])null;
    private BigDecimal[][] responsesBD = (BigDecimal[][])null;
    private boolean dataEntered = false;
    private boolean bigDecimal = false;
    private int nGroups = 0;
    private String[] groupNames = null;
    private boolean groupNamesSet = false;
    private int nTotalResponses = 0;
    private int[] nResponsesPerGroup = null;
    private int[] typePerGroup = null;
    private String[] dichotomousS = new String[]{"yes", "no", "y", "n", "true", "false"};
    private char[] dichotomousC = new char[]{'y', 'n'};
    private double dichotTrue = 1.0D;
    private double dichotFalse = -1.0D;
    private double[] dichotomousDoubleS;
    private double[] dichotomousDoubleC;
    private int nDichotomousS;
    private int nDichotomousC;
    private boolean nFactorOption;
    private double[] groupMeans;
    private BigDecimal[] groupMeansBD;
    private double[] groupSD;
    private double[] groupSE;
    private double[] groupClb;
    private double[] groupCub;
    private double[] groupMinimum;
    private BigDecimal[] groupMinimumBD;
    private double[] groupMaximum;
    private BigDecimal[] groupMaximumBD;
    private double[] groupSS;
    private BigDecimal[] groupSSBD;
    private double[] groupMedians;
    private BigDecimal[] groupMediansBD;
    private double[] groupMomentSkewness;
    private double[] groupMedianSkewness;
    private double[] groupQuartileSkewness;
    private BigDecimal[] groupQuartileSkewnessBD;
    private double[] groupKurtosis;
    private BigDecimal[] groupKurtosisBD;
    private double[] groupExcessKurtosis;
    private BigDecimal[] groupExcessKurtosisBD;
    private double[] groupProbPlotR;
    private double[] groupProbPlotGradient;
    private double[] groupProbPlotIntercept;
    private double[] groupProbPlotMu;
    private double[] groupProbPlotSigma;
    private boolean groupStatsDone;
    private double totalMean;
    private BigDecimal totalMeanBD;
    private double totalSD;
    private double totalSE;
    private double totalClb;
    private double totalCub;
    private double totalMinimum;
    private BigDecimal totalMinimumBD;
    private double totalMaximum;
    private BigDecimal totalMaximumBD;
    private double totalSS;
    private BigDecimal totalSSBD;
    private double totalMedian;
    private BigDecimal totalMedianBD;
    private double totalMomentSkewness;
    private double totalMedianSkewness;
    private double totalQuartileSkewness;
    private BigDecimal totalQuartileSkewnessBD;
    private double totalKurtosis;
    private BigDecimal totalKurtosisBD;
    private double totalExcessKurtosis;
    private BigDecimal totalExcessKurtosisBD;
    private double totalProbPlotR;
    private double totalProbPlotGradient;
    private double totalProbPlotIntercept;
    private double totalProbPlotMu;
    private double totalProbPlotSigma;
    private int dofTotal;
    private int dofWithinGroups;
    private int dofBetweenGroups;
    private double ssTotal;
    private double ssWithin;
    private double ssBetween;
    private BigDecimal ssTotalBD;
    private BigDecimal ssWithinBD;
    private BigDecimal ssBetweenBD;
    private double meanSquareTotal;
    private double meanSquareWithin;
    private double meanSquareBetween;
    private BigDecimal meanSquareTotalBD;
    private BigDecimal meanSquareWithinBD;
    private BigDecimal meanSquareBetweenBD;
    private double fRatio;
    private BigDecimal fRatioBD;
    private double fRatioP;
    private boolean oneWayDone;
    private double criticalSignificance;
    private String criticalSignificanceS;
    private String criticalSignificanceF;
    private double criticalFratio;
    private boolean[] compGroups;
    private boolean comparison;
    private int nComparisons;
    private int[][] pairIndices;
    private double[] meanSquareTotalG;
    private double[] meanSquareWithinG;
    private double[] meanSquareBetweenG;
    private BigDecimal[] meanSquareTotalBDG;
    private BigDecimal[] meanSquareWithinBDG;
    private BigDecimal[] meanSquareBetweenBDG;
    private double[] fRatioG;
    private BigDecimal[] fRatioBDG;
    private double[] fRatioPG;
    private double[] criticalFratioG;
    private double[] ssTotalG;
    private double[] ssWithinG;
    private double[] ssBetweenG;
    private BigDecimal[] ssTotalBDG;
    private BigDecimal[] ssWithinBDG;
    private BigDecimal[] ssBetweenBDG;
    private int[] dofTotalG;
    private int[] dofBetweenG;
    private int[] dofWithinG;
    private boolean allCalcn;

    public ANOVA() {
        this.dichotomousDoubleS = new double[]{this.dichotTrue, this.dichotFalse, this.dichotTrue, this.dichotFalse, this.dichotTrue, this.dichotFalse};
        this.dichotomousDoubleC = new double[]{this.dichotTrue, this.dichotFalse};
        this.nDichotomousS = 6;
        this.nDichotomousC = 2;
        this.nFactorOption = false;
        this.groupMeans = null;
        this.groupMeansBD = null;
        this.groupSD = null;
        this.groupSE = null;
        this.groupClb = null;
        this.groupCub = null;
        this.groupMinimum = null;
        this.groupMinimumBD = null;
        this.groupMaximum = null;
        this.groupMaximumBD = null;
        this.groupSS = null;
        this.groupSSBD = null;
        this.groupMedians = null;
        this.groupMediansBD = null;
        this.groupMomentSkewness = null;
        this.groupMedianSkewness = null;
        this.groupQuartileSkewness = null;
        this.groupQuartileSkewnessBD = null;
        this.groupKurtosis = null;
        this.groupKurtosisBD = null;
        this.groupExcessKurtosis = null;
        this.groupExcessKurtosisBD = null;
        this.groupProbPlotR = null;
        this.groupProbPlotGradient = null;
        this.groupProbPlotIntercept = null;
        this.groupProbPlotMu = null;
        this.groupProbPlotSigma = null;
        this.groupStatsDone = false;
        this.totalMean = 0.0D;
        this.totalMeanBD = new BigDecimal(0.0D);
        this.totalSD = 0.0D;
        this.totalSE = 0.0D;
        this.totalClb = 0.0D;
        this.totalCub = 0.0D;
        this.totalMinimum = 0.0D;
        this.totalMinimumBD = new BigDecimal("0.0");
        this.totalMaximum = 0.0D;
        this.totalMaximumBD = new BigDecimal("0.0");
        this.totalSS = 0.0D;
        this.totalSSBD = new BigDecimal("0.0");
        this.totalMedian = 0.0D;
        this.totalMedianBD = new BigDecimal("0.0");
        this.totalMomentSkewness = 0.0D;
        this.totalMedianSkewness = 0.0D;
        this.totalQuartileSkewness = 0.0D;
        this.totalQuartileSkewnessBD = new BigDecimal("0.0");
        this.totalKurtosis = 0.0D;
        this.totalKurtosisBD = new BigDecimal("0.0");
        this.totalExcessKurtosis = 0.0D;
        this.totalExcessKurtosisBD = new BigDecimal("0.0");
        this.totalProbPlotR = 0.0D;
        this.totalProbPlotGradient = 0.0D;
        this.totalProbPlotIntercept = 0.0D;
        this.totalProbPlotMu = 0.0D;
        this.totalProbPlotSigma = 0.0D;
        this.dofTotal = 0;
        this.dofWithinGroups = 0;
        this.dofBetweenGroups = 0;
        this.ssTotal = 0.0D;
        this.ssWithin = 0.0D;
        this.ssBetween = 0.0D;
        this.ssTotalBD = new BigDecimal("0.0");
        this.ssWithinBD = new BigDecimal("0.0");
        this.ssBetweenBD = new BigDecimal("0.0");
        this.meanSquareTotal = 0.0D;
        this.meanSquareWithin = 0.0D;
        this.meanSquareBetween = 0.0D;
        this.meanSquareTotalBD = new BigDecimal("0.0");
        this.meanSquareWithinBD = new BigDecimal("0.0");
        this.meanSquareBetweenBD = new BigDecimal("0.0");
        this.fRatio = 0.0D;
        this.fRatioBD = new BigDecimal("0.0");
        this.fRatioP = 0.0D;
        this.oneWayDone = false;
        this.criticalSignificance = 0.95D;
        this.criticalSignificanceS = "95%";
        this.criticalSignificanceF = "5%";
        this.criticalFratio = 0.0D;
        this.compGroups = null;
        this.comparison = false;
        this.nComparisons = 0;
        this.pairIndices = (int[][])null;
        this.meanSquareTotalG = null;
        this.meanSquareWithinG = null;
        this.meanSquareBetweenG = null;
        this.meanSquareTotalBDG = null;
        this.meanSquareWithinBDG = null;
        this.meanSquareBetweenBDG = null;
        this.fRatioG = null;
        this.fRatioBDG = null;
        this.fRatioPG = null;
        this.criticalFratioG = null;
        this.ssTotalG = null;
        this.ssWithinG = null;
        this.ssBetweenG = null;
        this.ssTotalBDG = null;
        this.ssWithinBDG = null;
        this.ssBetweenBDG = null;
        this.dofTotalG = null;
        this.dofBetweenG = null;
        this.dofWithinG = null;
        this.allCalcn = true;
        this.setTitle("Untitled fillData", false);
    }

    public ANOVA(String var1) {
        this.dichotomousDoubleS = new double[]{this.dichotTrue, this.dichotFalse, this.dichotTrue, this.dichotFalse, this.dichotTrue, this.dichotFalse};
        this.dichotomousDoubleC = new double[]{this.dichotTrue, this.dichotFalse};
        this.nDichotomousS = 6;
        this.nDichotomousC = 2;
        this.nFactorOption = false;
        this.groupMeans = null;
        this.groupMeansBD = null;
        this.groupSD = null;
        this.groupSE = null;
        this.groupClb = null;
        this.groupCub = null;
        this.groupMinimum = null;
        this.groupMinimumBD = null;
        this.groupMaximum = null;
        this.groupMaximumBD = null;
        this.groupSS = null;
        this.groupSSBD = null;
        this.groupMedians = null;
        this.groupMediansBD = null;
        this.groupMomentSkewness = null;
        this.groupMedianSkewness = null;
        this.groupQuartileSkewness = null;
        this.groupQuartileSkewnessBD = null;
        this.groupKurtosis = null;
        this.groupKurtosisBD = null;
        this.groupExcessKurtosis = null;
        this.groupExcessKurtosisBD = null;
        this.groupProbPlotR = null;
        this.groupProbPlotGradient = null;
        this.groupProbPlotIntercept = null;
        this.groupProbPlotMu = null;
        this.groupProbPlotSigma = null;
        this.groupStatsDone = false;
        this.totalMean = 0.0D;
        this.totalMeanBD = new BigDecimal(0.0D);
        this.totalSD = 0.0D;
        this.totalSE = 0.0D;
        this.totalClb = 0.0D;
        this.totalCub = 0.0D;
        this.totalMinimum = 0.0D;
        this.totalMinimumBD = new BigDecimal("0.0");
        this.totalMaximum = 0.0D;
        this.totalMaximumBD = new BigDecimal("0.0");
        this.totalSS = 0.0D;
        this.totalSSBD = new BigDecimal("0.0");
        this.totalMedian = 0.0D;
        this.totalMedianBD = new BigDecimal("0.0");
        this.totalMomentSkewness = 0.0D;
        this.totalMedianSkewness = 0.0D;
        this.totalQuartileSkewness = 0.0D;
        this.totalQuartileSkewnessBD = new BigDecimal("0.0");
        this.totalKurtosis = 0.0D;
        this.totalKurtosisBD = new BigDecimal("0.0");
        this.totalExcessKurtosis = 0.0D;
        this.totalExcessKurtosisBD = new BigDecimal("0.0");
        this.totalProbPlotR = 0.0D;
        this.totalProbPlotGradient = 0.0D;
        this.totalProbPlotIntercept = 0.0D;
        this.totalProbPlotMu = 0.0D;
        this.totalProbPlotSigma = 0.0D;
        this.dofTotal = 0;
        this.dofWithinGroups = 0;
        this.dofBetweenGroups = 0;
        this.ssTotal = 0.0D;
        this.ssWithin = 0.0D;
        this.ssBetween = 0.0D;
        this.ssTotalBD = new BigDecimal("0.0");
        this.ssWithinBD = new BigDecimal("0.0");
        this.ssBetweenBD = new BigDecimal("0.0");
        this.meanSquareTotal = 0.0D;
        this.meanSquareWithin = 0.0D;
        this.meanSquareBetween = 0.0D;
        this.meanSquareTotalBD = new BigDecimal("0.0");
        this.meanSquareWithinBD = new BigDecimal("0.0");
        this.meanSquareBetweenBD = new BigDecimal("0.0");
        this.fRatio = 0.0D;
        this.fRatioBD = new BigDecimal("0.0");
        this.fRatioP = 0.0D;
        this.oneWayDone = false;
        this.criticalSignificance = 0.95D;
        this.criticalSignificanceS = "95%";
        this.criticalSignificanceF = "5%";
        this.criticalFratio = 0.0D;
        this.compGroups = null;
        this.comparison = false;
        this.nComparisons = 0;
        this.pairIndices = (int[][])null;
        this.meanSquareTotalG = null;
        this.meanSquareWithinG = null;
        this.meanSquareBetweenG = null;
        this.meanSquareTotalBDG = null;
        this.meanSquareWithinBDG = null;
        this.meanSquareBetweenBDG = null;
        this.fRatioG = null;
        this.fRatioBDG = null;
        this.fRatioPG = null;
        this.criticalFratioG = null;
        this.ssTotalG = null;
        this.ssWithinG = null;
        this.ssBetweenG = null;
        this.ssTotalBDG = null;
        this.ssWithinBDG = null;
        this.ssBetweenBDG = null;
        this.dofTotalG = null;
        this.dofBetweenG = null;
        this.dofWithinG = null;
        this.allCalcn = true;
        this.setTitle(var1, true);
    }

    public void enterTitle(String var1) {
        this.setTitle(var1, true);
    }

    private void setTitle(String var1, boolean var2) {
        if (!var2) {
            this.title[0] = var1;
            ++this.nTitle;
            this.titleCheck = var2;
        } else if (!this.titleCheck) {
            this.title[0] = var1;
            ++this.nTitle;
            this.titleCheck = var2;
        } else {
            this.titleCheck = var2;
            this.title[this.nTitle] = var1;
            ++this.nTitle;
        }

    }

    public void setCriticalSignificance(double var1) {
        this.criticalSignificance = 1.0D - var1;
        this.criticalSignificanceF = Double.toString(100.0D * var1) + "%";
        this.criticalSignificanceS = Double.toString(100.0D * (1.0D - var1)) + "%";
    }

    public double getCriticalSignificance() {
        return 1.0D - this.criticalSignificance;
    }

    public void addDichotomousPair(String var1, String var2) {
        String[] var3 = (String[])((String[])this.dichotomousS.clone());
        double[] var4 = (double[])((double[])this.dichotomousDoubleS.clone());
        this.dichotomousS = new String[this.nDichotomousS + 2];
        this.dichotomousDoubleS = new double[this.nDichotomousS + 2];

        for(int var5 = 0; var5 < this.nDichotomousS; ++var5) {
            this.dichotomousS[var5] = var3[var5];
            this.dichotomousDoubleS[var5] = var4[var5];
        }

        this.dichotomousS[this.nDichotomousS] = var1;
        this.dichotomousDoubleS[this.nDichotomousS] = this.dichotTrue;
        ++this.nDichotomousS;
        this.dichotomousS[this.nDichotomousS] = var2;
        this.dichotomousDoubleS[this.nDichotomousS] = this.dichotFalse;
        ++this.nDichotomousS;
        if (var1.length() == 1 && var2.length() == 1) {
            char[] var7 = (char[])((char[])this.dichotomousC.clone());
            var4 = (double[])((double[])this.dichotomousDoubleC.clone());
            this.dichotomousC = new char[this.nDichotomousC + 2];
            this.dichotomousDoubleC = new double[this.nDichotomousC + 2];

            for(int var6 = 0; var6 < this.nDichotomousC; ++var6) {
                this.dichotomousC[var6] = var7[var6];
                this.dichotomousDoubleC[var6] = var4[var6];
            }

            this.dichotomousC[this.nDichotomousC] = var1.charAt(0);
            this.dichotomousDoubleC[this.nDichotomousC] = this.dichotTrue;
            ++this.nDichotomousC;
            this.dichotomousC[this.nDichotomousC] = var2.charAt(0);
            this.dichotomousDoubleC[this.nDichotomousC] = this.dichotFalse;
            ++this.nDichotomousC;
        }

    }

    public void addDichotomousPair(char var1, char var2) {
        char[] var3 = (char[])((char[])this.dichotomousC.clone());
        double[] var4 = (double[])((double[])this.dichotomousDoubleC.clone());
        this.dichotomousC = new char[this.nDichotomousC + 2];
        this.dichotomousDoubleC = new double[this.nDichotomousC + 2];

        for(int var5 = 0; var5 < this.nDichotomousC; ++var5) {
            this.dichotomousC[var5] = var3[var5];
            this.dichotomousDoubleC[var5] = var4[var5];
        }

        this.dichotomousC[this.nDichotomousC] = var1;
        this.dichotomousDoubleC[this.nDichotomousC] = this.dichotTrue;
        ++this.nDichotomousC;
        this.dichotomousC[this.nDichotomousC] = var2;
        this.dichotomousDoubleC[this.nDichotomousC] = this.dichotFalse;
        ++this.nDichotomousC;
        String[] var7 = (String[])((String[])this.dichotomousS.clone());
        var4 = (double[])((double[])this.dichotomousDoubleS.clone());
        this.dichotomousS = new String[this.nDichotomousS + 2];
        this.dichotomousDoubleS = new double[this.nDichotomousS + 2];

        for(int var6 = 0; var6 < this.nDichotomousS; ++var6) {
            this.dichotomousS[var6] = var7[var6];
            this.dichotomousDoubleS[var6] = var4[var6];
        }

        this.dichotomousS[this.nDichotomousS] = String.valueOf(var1);
        this.dichotomousDoubleS[this.nDichotomousS] = this.dichotTrue;
        ++this.nDichotomousS;
        this.dichotomousS[this.nDichotomousS] = String.valueOf(var2);
        this.dichotomousDoubleS[this.nDichotomousS] = this.dichotFalse;
        ++this.nDichotomousS;
    }

    public void setDichotomousPairValues(double var1, double var3) {
        this.dichotTrue = var1;
        this.dichotFalse = var3;
    }

    public double[] getDichotomousPairValues() {
        double[] var1 = new double[]{this.dichotTrue, this.dichotFalse};
        return var1;
    }

    private void setAllCalcn(boolean var1) {
        this.allCalcn = var1;
    }

    public void readResponseData() {
        FileChooser var1 = new FileChooser();
        String var2 = var1.selectFile();
        var1.close();
        this.readResponseData(var2);
    }

    public void readResponseData(String var1) {
        this.inputFilename = var1;
        FileInput var2 = new FileInput(var1);
        String var3 = var2.readLine();
        this.setTitle(var3, true);
        this.nGroups = var2.readInt();
        this.groupNames = new String[this.nGroups + 1];

        int var4;
        for(var4 = 0; var4 < this.nGroups; ++var4) {
            this.groupNames[var4] = var2.readWord();
        }

        this.groupNames[this.nGroups] = "total";
        this.groupNamesSet = true;
        this.nResponsesPerGroup = new int[this.nGroups];

        for(var4 = 0; var4 < this.nGroups; ++var4) {
            this.nResponsesPerGroup[var4] = var2.readInt();
        }

        String[][] var8 = new String[this.nGroups][];
        String[] var5 = null;

        for(int var6 = 0; var6 < this.nGroups; ++var6) {
            var5 = new String[this.nResponsesPerGroup[var6]];

            for(int var7 = 0; var7 < this.nResponsesPerGroup[var6]; ++var7) {
                var5[var7] = var2.readWord();
            }

            var8[var6] = Conv.copy(var5);
        }

        var2.close();
        this.originalDataType = 1;
        this.setVariables(var8, this.originalDataType);
    }

    public void enterResponseData(String[][] var1) {
        this.nGroups = var1.length;
        this.nResponsesPerGroup = new int[this.nGroups];

        for(int var2 = 0; var2 < this.nGroups; ++var2) {
            this.nResponsesPerGroup[var2] = var1[var2].length;
        }

        this.originalDataType = 1;
        this.setVariables(var1, this.originalDataType);
    }

    public void enterResponseData(double[][] var1) {
        this.nGroups = var1.length;
        this.nResponsesPerGroup = new int[this.nGroups];

        for(int var2 = 0; var2 < this.nGroups; ++var2) {
            this.nResponsesPerGroup[var2] = var1[var2].length;
        }

        this.originalDataType = 2;
        this.setVariables(var1, this.originalDataType);
    }

    public void enterResponseData(Matrix var1) {
        double[][] var2 = var1.getArrayCopy();
        this.nGroups = var2.length;
        this.nResponsesPerGroup = new int[this.nGroups];

        for(int var3 = 0; var3 < this.nGroups; ++var3) {
            this.nResponsesPerGroup[var3] = var2[var3].length;
        }

        this.originalDataType = 3;
        this.setVariables(var1, this.originalDataType);
    }

    public void enterResponseData(BigDecimal[][] var1) {
        this.nGroups = var1.length;
        this.nResponsesPerGroup = new int[this.nGroups];

        for(int var2 = 0; var2 < this.nGroups; ++var2) {
            this.nResponsesPerGroup[var2] = var1[var2].length;
        }

        this.originalDataType = 9;
        this.setVariables(var1, this.originalDataType);
    }

    public void enterResponseData(float[][] var1) {
        this.nGroups = var1.length;
        this.nResponsesPerGroup = new int[this.nGroups];

        for(int var2 = 0; var2 < this.nGroups; ++var2) {
            this.nResponsesPerGroup[var2] = var1[var2].length;
        }

        this.originalDataType = 4;
        this.setVariables(var1, this.originalDataType);
    }

    public void enterResponseData(long[][] var1) {
        this.nGroups = var1.length;
        this.nResponsesPerGroup = new int[this.nGroups];

        for(int var2 = 0; var2 < this.nGroups; ++var2) {
            this.nResponsesPerGroup[var2] = var1[var2].length;
        }

        this.originalDataType = 8;
        this.setVariables(var1, this.originalDataType);
    }

    public void enterResponseData(int[][] var1) {
        this.nGroups = var1.length;
        this.nResponsesPerGroup = new int[this.nGroups];

        for(int var2 = 0; var2 < this.nGroups; ++var2) {
            this.nResponsesPerGroup[var2] = var1[var2].length;
        }

        this.originalDataType = 5;
        this.setVariables(var1, this.originalDataType);
    }

    public void enterResponseData(char[][] var1) {
        this.nGroups = var1.length;
        this.nResponsesPerGroup = new int[this.nGroups];

        for(int var2 = 0; var2 < this.nGroups; ++var2) {
            this.nResponsesPerGroup[var2] = var1[var2].length;
        }

        this.originalDataType = 6;
        this.setVariables(var1, this.originalDataType);
    }

    public void enterResponseData(boolean[][] var1) {
        this.nGroups = var1.length;
        this.nResponsesPerGroup = new int[this.nGroups];

        for(int var2 = 0; var2 < this.nGroups; ++var2) {
            this.nResponsesPerGroup[var2] = var1[var2].length;
        }

        this.originalDataType = 7;
        this.setVariables(var1, this.originalDataType);
    }

    private void setVariables(Object var1, int var2) {
        if (!this.groupNamesSet) {
            this.groupNames = new String[this.nGroups];

            for(int var3 = 0; var3 < this.nGroups; ++var3) {
                this.groupNames[var3] = "group " + (var3 + 1);
            }
        }

        this.originalData = Conv.copy(var1);
        this.originalDataType = var2;
        this.dataEntered = true;
        this.convertDataType(var2);
    }

    private void convertDataType(int var1) {
        this.bigDecimal = false;
        int var21;
        int var22;
        int var25;
        int var26;
        switch(var1) {
            case 1:
                this.responses0 = new double[this.nGroups][];
                String[][] var2 = (String[][])((String[][])this.originalData);
                var2 = this.trimResponses(var2);

                for(int var15 = 0; var15 < this.nGroups; ++var15) {
                    boolean var17 = this.testIfDichotomous(var2[var15]);
                    if (var17) {
                        this.responses0[var15] = this.dichotStringToDouble(var2[var15]);
                    } else {
                        boolean var19 = this.testIfAlphabetic(var2[var15]);
                        if (var19) {
                            this.responses0[var15] = this.alphabeticToDouble(var2[var15]);
                        } else {
                            this.responses0[var15] = this.stringToDouble(var2[var15]);
                        }
                    }
                }

                this.doubleToBD();
                break;
            case 2:
                this.responses0 = new double[this.nGroups][];
                double[][] var3 = (double[][])((double[][])this.originalData);

                for(int var16 = 0; var16 < this.nGroups; ++var16) {
                    this.responses0[var16] = (double[])((double[])var3[var16].clone());
                }

                this.doubleToBD();
                break;
            case 3:
                this.responses0 = new double[this.nGroups][];
                double[][] var4 = ((Matrix)this.originalData).getArrayCopy();

                for(int var18 = 0; var18 < this.nGroups; ++var18) {
                    this.responses0[var18] = (double[])((double[])var4[var18].clone());
                }

                this.doubleToBD();
                break;
            case 4:
                this.responses0 = new double[this.nGroups][];
                float[][] var5 = (float[][])((float[][])this.originalData);

                for(int var20 = 0; var20 < this.nGroups; ++var20) {
                    this.responses0[var20] = new double[this.nResponsesPerGroup[var20]];

                    for(var21 = 0; var21 < this.nResponsesPerGroup[var20]; ++var21) {
                        this.responses0[var20][var21] = (double)var5[var20][var21];
                    }
                }

                this.doubleToBD();
                break;
            case 5:
                this.responses0 = new double[this.nGroups][];
                int[][] var6 = (int[][])((int[][])this.originalData);

                for(var21 = 0; var21 < this.nGroups; ++var21) {
                    this.responses0[var21] = new double[this.nResponsesPerGroup[var21]];

                    for(var22 = 0; var22 < this.nResponsesPerGroup[var21]; ++var22) {
                        this.responses0[var21][var22] = (double)var6[var21][var22];
                    }
                }

                this.doubleToBD();
                break;
            case 6:
                this.responses0 = new double[this.nGroups][];
                char[][] var7 = (char[][])((char[][])this.originalData);

                for(var22 = 0; var22 < this.nGroups; ++var22) {
                    boolean var24 = this.testIfDichotomous(var7[var22]);
                    if (var24) {
                        this.responses0[var22] = this.dichotCharToDouble(var7[var22]);
                    } else {
                        boolean var27 = this.testIfAlphabetic(var7[var22]);
                        if (var27) {
                            this.responses0[var22] = this.alphabeticToDouble(var7[var22]);
                        } else {
                            this.responses0[var22] = this.charToDouble(var7[var22]);
                        }
                    }
                }

                this.doubleToBD();
                break;
            case 7:
                this.responses0 = new double[this.nGroups][];
                boolean[][] var8 = (boolean[][])((boolean[][])this.originalData);

                for(int var23 = 0; var23 < this.nGroups; ++var23) {
                    this.responses0[var23] = new double[this.nResponsesPerGroup[var23]];

                    for(var25 = 0; var25 < this.nResponsesPerGroup[var23]; ++var25) {
                        if (var8[var23][var25]) {
                            this.responses0[var23][var25] = 1.0D;
                        } else {
                            this.responses0[var23][var25] = -1.0D;
                        }
                    }
                }

                this.doubleToBD();
                break;
            case 8:
                this.responses0 = new double[this.nGroups][];
                long[][] var9 = (long[][])((long[][])this.originalData);

                for(var25 = 0; var25 < this.nGroups; ++var25) {
                    this.responses0[var25] = new double[this.nResponsesPerGroup[var25]];

                    for(var26 = 0; var26 < this.nResponsesPerGroup[var25]; ++var26) {
                        this.responses0[var25][var26] = (double)var9[var25][var26];
                    }
                }

                this.doubleToBD();
                break;
            case 9:
                this.responsesBD = new BigDecimal[this.nGroups][];
                BigDecimal[][] var10 = (BigDecimal[][])((BigDecimal[][])this.originalData);

                for(var26 = 0; var26 < this.nGroups; ++var26) {
                    this.responsesBD[var26] = (BigDecimal[])((BigDecimal[])var10[var26].clone());
                }

                this.bDtodouble();
                this.bigDecimal = true;
                break;
            case 10:
                this.responsesBD = new BigDecimal[this.nGroups][];
                BigInteger[][] var11 = (BigInteger[][])((BigInteger[][])this.originalData);

                for(int var12 = 0; var12 < this.nGroups; ++var12) {
                    this.responsesBD[var12] = new BigDecimal[this.nResponsesPerGroup[var12]];

                    for(int var13 = 0; var13 < this.nResponsesPerGroup[var12]; ++var13) {
                        this.responsesBD[var12][var13] = new BigDecimal(var11[var12][var13]);
                    }
                }

                this.bDtodouble();
                this.bigDecimal = true;
        }

        this.nTotalResponses = 0;

        for(int var14 = 0; var14 < this.nGroups; ++var14) {
            this.nTotalResponses += this.nResponsesPerGroup[var14];
        }

    }

    private void doubleToBD() {
        this.responsesBD = new BigDecimal[this.nGroups][];

        for(int var1 = 0; var1 < this.nGroups; ++var1) {
            this.responsesBD[var1] = new BigDecimal[this.nResponsesPerGroup[var1]];

            for(int var2 = 0; var2 < this.nResponsesPerGroup[var1]; ++var2) {
                this.responsesBD[var1][var2] = new BigDecimal(this.responses0[var1][var2]);
            }
        }

    }

    private void bDtodouble() {
        this.responses0 = new double[this.nGroups][];

        for(int var1 = 0; var1 < this.nGroups; ++var1) {
            this.responses0[var1] = new double[this.nResponsesPerGroup[var1]];

            for(int var2 = 0; var2 < this.nResponsesPerGroup[var1]; ++var2) {
                this.responses0[var1][var2] = this.responsesBD[var1][var2].doubleValue();
            }
        }

    }

    private boolean testIfDichotomous(String[] var1) {
        boolean var2 = false;
        int var3 = var1.length;
        int var4 = 0;

        for(int var5 = 0; var5 < var3; ++var5) {
            boolean var6 = false;

            for(int var7 = 0; var7 < this.nDichotomousS; ++var7) {
                if (var1[var5].equalsIgnoreCase(this.dichotomousS[var7])) {
                    var6 = true;
                }
            }

            if (var6) {
                ++var4;
            }
        }

        if (var4 == var3) {
            var2 = true;
        }

        return var2;
    }

    private boolean testIfDichotomous(char[] var1) {
        boolean var2 = false;
        int var3 = var1.length;
        int var4 = 0;

        for(int var5 = 0; var5 < var3; ++var5) {
            boolean var6 = false;

            for(int var7 = 0; var7 < this.nDichotomousC; ++var7) {
                if (var1[var5] == this.dichotomousC[var7]) {
                    var6 = true;
                }
            }

            if (var6) {
                ++var4;
            }
        }

        if (var4 == var3) {
            var2 = true;
        }

        return var2;
    }

    private boolean testIfAlphabetic(String[] var1) {
        boolean var2 = false;
        int var3 = var1.length;
        int var4 = 0;

        for(int var5 = 0; var5 < var3; ++var5) {
            boolean var6 = false;
            if (var1[var5].length() == 1) {
                char var7 = var1[var5].charAt(0);
                if (var7 > '@' && var7 < '[') {
                    var6 = true;
                } else if (var7 > '`' && var7 < '{') {
                    var6 = true;
                }
            }

            if (var6) {
                ++var4;
            }
        }

        if (var4 == var3) {
            var2 = true;
        }

        return var2;
    }

    private boolean testIfAlphabetic(char[] var1) {
        boolean var2 = false;
        int var3 = var1.length;
        int var4 = 0;

        for(int var5 = 0; var5 < var3; ++var5) {
            boolean var6 = false;
            char var7 = var1[var5];
            if (var7 > '@' && var7 < '[') {
                var6 = true;
            } else if (var7 > '`' && var7 < '{') {
                var6 = true;
            }

            if (var6) {
                ++var4;
            }
        }

        if (var4 == var3) {
            var2 = true;
        }

        return var2;
    }

    private double[] dichotStringToDouble(String[] var1) {
        boolean var2 = false;
        int var3 = var1.length;
        double[] var4 = new double[var3];

        for(int var5 = 0; var5 < var3; ++var5) {
            boolean var6 = false;

            for(int var7 = 0; var7 < this.nDichotomousS; var7 += 2) {
                if (var1[var5].equalsIgnoreCase(this.dichotomousS[var7])) {
                    var6 = true;
                }
            }

            if (var6) {
                var4[var5] = 1.0D;
            } else {
                var4[var5] = -1.0D;
            }
        }

        return var4;
    }

    private double[] dichotCharToDouble(char[] var1) {
        boolean var2 = false;
        int var3 = var1.length;
        double[] var4 = new double[var3];

        for(int var5 = 0; var5 < var3; ++var5) {
            boolean var6 = false;

            for(int var7 = 0; var7 < this.nDichotomousC; var7 += 2) {
                if (var1[var5] == this.dichotomousC[var7]) {
                    var6 = true;
                }
            }

            if (var6) {
                var4[var5] = 1.0D;
            } else {
                var4[var5] = -1.0D;
            }
        }

        return var4;
    }

    private double[] alphabeticToDouble(String[] var1) {
        int var2 = var1.length;
        double[] var3 = new double[var2];

        for(int var4 = 0; var4 < var2; ++var4) {
            double var5 = (double)var1[var4].charAt(0);
            if (var5 > 96.0D) {
                var3[var4] = var5 - 96.0D;
            } else {
                var3[var4] = var5 - 64.0D;
            }
        }

        return var3;
    }

    private double[] alphabeticToDouble(char[] var1) {
        int var2 = var1.length;
        double[] var3 = new double[var2];

        for(int var4 = 0; var4 < var2; ++var4) {
            double var5 = (double)var1[var4];
            if (var5 > 96.0D) {
                var3[var4] = var5 - 96.0D;
            } else {
                var3[var4] = var5 - 64.0D;
            }
        }

        return var3;
    }

    private double[] stringToDouble(String[] var1) {
        int var2 = var1.length;
        double[] var3 = new double[var2];

        for(int var4 = 0; var4 < var2; ++var4) {
            var3[var4] = Double.parseDouble(var1[var4]);
        }

        return var3;
    }

    private double[] charToDouble(char[] var1) {
        int var2 = var1.length;
        double[] var3 = new double[var2];

        for(int var4 = 0; var4 < var2; ++var4) {
            double var5 = (double)var1[var4];
            if (var5 > 96.0D) {
                var3[var4] = var5 - 96.0D;
            } else {
                var3[var4] = var5 - 64.0D;
            }
        }

        return var3;
    }

    private String[][] trimResponses(String[][] var1) {
        String[][] var2 = (String[][])((String[][])var1.clone());
        int var3 = var1.length;
        int var4 = var1[0].length;

        for(int var5 = 0; var5 < var3; ++var5) {
            for(int var6 = 0; var6 < var4; ++var6) {
                var2[var5][var6].trim();
            }
        }

        return var2;
    }

    public Object getResponsesAsEntered() {
        if (!this.dataEntered) {
            throw new IllegalArgumentException("No fillData has been entered");
        } else {
            return Conv.copy(this.originalData);
        }
    }

    public double[][] getResponses() {
        if (!this.dataEntered) {
            throw new IllegalArgumentException("No fillData has been entered");
        } else {
            return Conv.copy(this.responses0);
        }
    }

    public double[][] getResponsesAsdouble() {
        if (!this.dataEntered) {
            throw new IllegalArgumentException("No fillData has been entered");
        } else {
            return Conv.copy(this.responses0);
        }
    }

    public BigDecimal[][] getResponsesAsBigDecimal() {
        if (!this.dataEntered) {
            throw new IllegalArgumentException("No fillData has been entered");
        } else {
            return Conv.copy(this.responsesBD);
        }
    }

    public String[] getTitle() {
        String[] var1 = new String[this.nTitle];

        for(int var2 = 0; var2 < this.nTitle; ++var2) {
            var1[var2] = this.title[var2];
        }

        return var1;
    }

    public String getInputFileName() {
        return this.inputFilename;
    }

    public void enterGroupNames(String[] var1) {
        int var2 = var1.length;
        this.groupNames = new String[var2 + 1];

        for(int var3 = 0; var3 < var2; ++var3) {
            this.groupNames[var3] = var1[var3];
        }

        this.groupNames[var2] = "total";
        this.groupNamesSet = true;
    }

    public int getNumberOfGroups() {
        if (!this.dataEntered) {
            throw new IllegalArgumentException("No fillData has been entered");
        } else {
            return this.nGroups;
        }
    }

    public String[] getGroupNames() {
        if (!this.dataEntered) {
            throw new IllegalArgumentException("no fillData has been entered");
        } else {
            String[] var1 = new String[this.nGroups];

            for(int var2 = 0; var2 < this.nGroups; ++var2) {
                var1[var2] = this.groupNames[var2];
            }

            return var1;
        }
    }

    public String getGroupName(int var1) {
        if (!this.dataEntered) {
            throw new IllegalArgumentException("no fillData has been entered");
        } else {
            return this.groupNames[var1 - 1];
        }
    }

    private void groupStatistics() {
        if (!this.dataEntered) {
            throw new IllegalArgumentException("no fillData has been entered");
        } else {
            this.groupMeansBD = new BigDecimal[this.nGroups];
            this.groupMeans = new double[this.nGroups];
            this.groupSD = new double[this.nGroups];
            this.groupSE = new double[this.nGroups];
            this.groupClb = new double[this.nGroups];
            this.groupCub = new double[this.nGroups];
            this.groupMinimumBD = new BigDecimal[this.nGroups];
            this.groupMinimum = new double[this.nGroups];
            this.groupMaximumBD = new BigDecimal[this.nGroups];
            this.groupMaximum = new double[this.nGroups];
            this.groupSSBD = new BigDecimal[this.nGroups];
            this.groupSS = new double[this.nGroups];
            this.groupMedians = new double[this.nGroups];
            this.groupMediansBD = new BigDecimal[this.nGroups];
            this.groupMomentSkewness = new double[this.nGroups];
            this.groupMedianSkewness = new double[this.nGroups];
            this.groupQuartileSkewness = new double[this.nGroups];
            this.groupQuartileSkewnessBD = new BigDecimal[this.nGroups];
            this.groupKurtosis = new double[this.nGroups];
            this.groupKurtosisBD = new BigDecimal[this.nGroups];
            this.groupExcessKurtosis = new double[this.nGroups];
            this.groupExcessKurtosisBD = new BigDecimal[this.nGroups];
            this.groupProbPlotR = new double[this.nGroups];
            this.groupProbPlotGradient = new double[this.nGroups];
            this.groupProbPlotIntercept = new double[this.nGroups];
            this.groupProbPlotMu = new double[this.nGroups];
            this.groupProbPlotSigma = new double[this.nGroups];
            Stat var1 = null;
            ProbabilityPlot var2 = null;

            for(int var3 = 0; var3 < this.nGroups; ++var3) {
                var1 = new Stat(this.responses0[var3]);
                this.groupMeans[var3] = var1.mean();
                this.groupMeansBD[var3] = new BigDecimal(this.groupMeans[var3]);
                this.groupSD[var3] = var1.standardDeviation();
                this.groupSE[var3] = var1.standardError();
                double[] var4 = var1.meanConfidenceLimits(this.criticalSignificance);
                this.groupClb[var3] = var4[0];
                this.groupCub[var3] = var4[1];
                this.groupMinimum[var3] = var1.minimum();
                this.groupMinimumBD[var3] = new BigDecimal(this.groupMinimum[var3]);
                this.groupMaximum[var3] = var1.maximum();
                this.groupMaximumBD[var3] = new BigDecimal(this.groupMaximum[var3]);
                double var5 = 0.0D;

                for(int var7 = 0; var7 < this.nResponsesPerGroup[var3]; ++var7) {
                    double var8 = this.responses0[var3][var7] - this.groupMeans[var3];
                    var5 += var8 * var8;
                }

                this.groupSS[var3] = var5;
                this.groupSSBD[var3] = new BigDecimal(var5);
                this.groupMedians[var3] = var1.median_as_double();
                this.groupMediansBD[var3] = new BigDecimal(this.groupMedians[var3]);
                this.groupMomentSkewness[var3] = var1.momentSkewness_as_double();
                this.groupMedianSkewness[var3] = var1.medianSkewness_as_double();
                this.groupQuartileSkewness[var3] = var1.quartileSkewness_as_double();
                this.groupQuartileSkewnessBD[var3] = new BigDecimal(this.groupQuartileSkewness[var3]);
                this.groupKurtosis[var3] = var1.kurtosis_as_double();
                this.groupKurtosisBD[var3] = new BigDecimal(this.groupKurtosis[var3]);
                this.groupExcessKurtosis[var3] = var1.excessKurtosis_as_double();
                this.groupExcessKurtosisBD[var3] = new BigDecimal(this.groupExcessKurtosis[var3]);
                var2 = new ProbabilityPlot(this.responses0[var3]);
                var2.suppressDisplay();
                var2.gaussianProbabilityPlot();
                this.groupProbPlotR[var3] = var2.gaussianCorrelationCoefficient();
                this.groupProbPlotGradient[var3] = var2.gaussianGradient();
                this.groupProbPlotIntercept[var3] = var2.gaussianIntercept();
                this.groupProbPlotMu[var3] = var2.gaussianMu();
                this.groupProbPlotSigma[var3] = var2.gaussianSigma();
            }

            double[] var12 = new double[this.nTotalResponses];
            int var13 = 0;

            for(int var14 = 0; var14 < this.nGroups; ++var14) {
                for(int var6 = 0; var6 < this.nResponsesPerGroup[var14]; ++var6) {
                    var12[var13++] = this.responses0[var14][var6];
                }
            }

            Stat var15 = new Stat(var12);
            this.totalMean = var15.mean();
            this.totalMeanBD = new BigDecimal(this.totalMean);
            this.totalSD = var15.standardDeviation();
            this.totalSE = var15.standardError();
            double[] var16 = var15.meanConfidenceLimits(this.criticalSignificance);
            this.totalClb = var16[0];
            this.totalCub = var16[1];
            this.totalMinimum = var15.minimum();
            this.totalMinimumBD = new BigDecimal(this.totalMinimum);
            this.totalMaximum = var15.maximum();
            this.totalMaximumBD = new BigDecimal(this.totalMaximum);
            double var17 = 0.0D;

            for(int var9 = 0; var9 < this.nTotalResponses; ++var9) {
                double var10 = var12[var9] - this.totalMean;
                var17 += var10 * var10;
            }

            this.totalSS = var17;
            this.totalSSBD = new BigDecimal(var17);
            this.totalMedian = var15.median_as_double();
            this.totalMedianBD = new BigDecimal(this.totalMedian);
            this.totalMomentSkewness = var15.momentSkewness_as_double();
            this.totalMedianSkewness = var15.medianSkewness_as_double();
            this.totalQuartileSkewness = var15.quartileSkewness_as_double();
            this.totalQuartileSkewnessBD = new BigDecimal(this.totalQuartileSkewness);
            this.totalKurtosis = var15.kurtosis_as_double();
            this.totalKurtosisBD = new BigDecimal(this.totalKurtosis);
            this.totalExcessKurtosis = var15.excessKurtosis_as_double();
            this.totalExcessKurtosisBD = new BigDecimal(this.totalExcessKurtosis);
            ProbabilityPlot var18 = new ProbabilityPlot(var12);
            var18.suppressDisplay();
            var18.gaussianProbabilityPlot();
            this.totalProbPlotR = var18.gaussianCorrelationCoefficient();
            this.totalProbPlotGradient = var18.gaussianGradient();
            this.totalProbPlotIntercept = var18.gaussianIntercept();
            this.totalProbPlotMu = var18.gaussianMu();
            this.totalProbPlotSigma = var18.gaussianSigma();
            this.groupStatsDone = true;
        }
    }

    private void groupStatisticsBD() {
        if (!this.dataEntered) {
            throw new IllegalArgumentException("no fillData has been entered");
        } else {
            this.groupMeansBD = new BigDecimal[this.nGroups];
            this.groupMeans = new double[this.nGroups];
            this.groupSD = new double[this.nGroups];
            this.groupSE = new double[this.nGroups];
            this.groupClb = new double[this.nGroups];
            this.groupCub = new double[this.nGroups];
            this.groupMinimumBD = new BigDecimal[this.nGroups];
            this.groupMinimum = new double[this.nGroups];
            this.groupMaximumBD = new BigDecimal[this.nGroups];
            this.groupMaximum = new double[this.nGroups];
            this.groupSSBD = new BigDecimal[this.nGroups];
            this.groupSS = new double[this.nGroups];
            this.groupMedians = new double[this.nGroups];
            this.groupMediansBD = new BigDecimal[this.nGroups];
            this.groupMomentSkewness = new double[this.nGroups];
            this.groupMedianSkewness = new double[this.nGroups];
            this.groupQuartileSkewness = new double[this.nGroups];
            this.groupQuartileSkewnessBD = new BigDecimal[this.nGroups];
            this.groupKurtosis = new double[this.nGroups];
            this.groupKurtosisBD = new BigDecimal[this.nGroups];
            this.groupExcessKurtosis = new double[this.nGroups];
            this.groupExcessKurtosisBD = new BigDecimal[this.nGroups];
            this.groupProbPlotR = new double[this.nGroups];
            this.groupProbPlotGradient = new double[this.nGroups];
            this.groupProbPlotIntercept = new double[this.nGroups];
            this.groupProbPlotMu = new double[this.nGroups];
            this.groupProbPlotSigma = new double[this.nGroups];
            Stat var1 = null;
            ProbabilityPlot var2 = null;

            int var6;
            BigDecimal var7;
            for(int var3 = 0; var3 < this.nGroups; ++var3) {
                var1 = new Stat(this.responsesBD[var3]);
                this.groupMeansBD[var3] = var1.mean_as_BigDecimal();
                this.groupMeans[var3] = this.groupMeansBD[var3].doubleValue();
                this.groupSD[var3] = var1.standardDeviation();
                this.groupSE[var3] = var1.standardError();
                double[] var4 = var1.meanConfidenceLimits(this.criticalSignificance);
                this.groupClb[var3] = var4[0];
                this.groupCub[var3] = var4[1];
                this.groupMinimumBD[var3] = var1.minimum_as_BigDecimal();
                this.groupMinimum[var3] = var1.minimum_as_double();
                this.groupMaximumBD[var3] = var1.maximum_as_BigDecimal();
                this.groupMaximum[var3] = var1.maximum_as_double();
                BigDecimal var5 = new BigDecimal("0.0");

                for(var6 = 0; var6 < this.nResponsesPerGroup[var3]; ++var6) {
                    var7 = this.responsesBD[var3][var6].subtract(this.groupMeansBD[var3]);
                    var5 = var5.add(var7.multiply(var7));
                }

                this.groupSSBD[var3] = var5;
                this.groupSS[var3] = var5.doubleValue();
                this.groupMediansBD[var3] = var1.median_as_BigDecimal();
                this.groupMedians[var3] = this.groupMediansBD[var3].doubleValue();
                this.groupMomentSkewness[var3] = var1.momentSkewness_as_double();
                this.groupMedianSkewness[var3] = var1.medianSkewness_as_double();
                this.groupQuartileSkewnessBD[var3] = var1.quartileSkewness_as_BigDecimal();
                this.groupQuartileSkewness[var3] = this.groupQuartileSkewnessBD[var3].doubleValue();
                this.groupKurtosis[var3] = var1.kurtosis_as_double();
                this.groupKurtosisBD[var3] = new BigDecimal(this.groupKurtosis[var3]);
                this.groupExcessKurtosisBD[var3] = var1.excessKurtosis_as_BigDecimal();
                this.groupExcessKurtosis[var3] = this.groupExcessKurtosisBD[var3].doubleValue();
                var2 = new ProbabilityPlot(this.responses0[var3]);
                var2.suppressDisplay();
                var2.gaussianProbabilityPlot();
                this.groupProbPlotR[var3] = var2.gaussianCorrelationCoefficient();
                this.groupProbPlotGradient[var3] = var2.gaussianGradient();
                this.groupProbPlotIntercept[var3] = var2.gaussianIntercept();
                this.groupProbPlotMu[var3] = var2.gaussianMu();
                this.groupProbPlotSigma[var3] = var2.gaussianSigma();
            }

            BigDecimal[] var10 = new BigDecimal[this.nTotalResponses];
            int var11 = 0;

            for(int var12 = 0; var12 < this.nGroups; ++var12) {
                for(var6 = 0; var6 < this.nResponsesPerGroup[var12]; ++var6) {
                    var10[var11++] = this.responsesBD[var12][var6];
                }
            }

            Stat var13 = new Stat(var10);
            this.totalMeanBD = var13.mean_as_BigDecimal();
            this.totalMean = this.totalMeanBD.doubleValue();
            this.totalSD = var13.standardDeviation();
            this.totalSE = var13.standardError();
            double[] var14 = var13.meanConfidenceLimits(this.criticalSignificance);
            this.totalClb = var14[0];
            this.totalCub = var14[1];
            this.totalMinimumBD = var13.minimum_as_BigDecimal();
            this.totalMinimum = this.totalMinimumBD.doubleValue();
            this.totalMaximumBD = var13.maximum_as_BigDecimal();
            this.totalMaximum = this.totalMaximumBD.doubleValue();
            var7 = new BigDecimal("0.0");

            for(int var8 = 0; var8 < this.nTotalResponses; ++var8) {
                BigDecimal var9 = var10[var8].subtract(this.totalMeanBD);
                var7 = var7.add(var9.multiply(var9));
            }

            this.totalSSBD = var7;
            this.totalSS = this.totalSSBD.doubleValue();
            this.totalMedianBD = var13.median_as_BigDecimal();
            this.totalMedian = this.totalMedianBD.doubleValue();
            this.totalMomentSkewness = var13.momentSkewness_as_double();
            this.totalMedianSkewness = var13.medianSkewness_as_double();
            this.totalQuartileSkewnessBD = var13.quartileSkewness_as_BigDecimal();
            this.totalQuartileSkewness = this.totalQuartileSkewnessBD.doubleValue();
            this.totalKurtosis = var13.kurtosis_as_double();
            this.totalKurtosisBD = new BigDecimal(this.totalKurtosis);
            this.totalExcessKurtosisBD = var13.excessKurtosis_as_BigDecimal();
            this.totalExcessKurtosis = this.totalExcessKurtosisBD.doubleValue();
            ProbabilityPlot var15 = new ProbabilityPlot(var10);
            var15.suppressDisplay();
            var15.gaussianProbabilityPlot();
            this.totalProbPlotR = var15.gaussianCorrelationCoefficient();
            this.totalProbPlotGradient = var15.gaussianGradient();
            this.totalProbPlotIntercept = var15.gaussianIntercept();
            this.totalProbPlotMu = var15.gaussianMu();
            this.totalProbPlotSigma = var15.gaussianSigma();
            this.groupStatsDone = true;
        }
    }

    public int[] numberOfResponsesPerGroup() {
        if (!this.dataEntered) {
            throw new IllegalArgumentException("No fillData has been entered");
        } else {
            return this.nResponsesPerGroup;
        }
    }

    public double[] groupMeans() {
        if (!this.groupStatsDone) {
            this.groupStatistics();
        }

        return Conv.copy(this.groupMeans);
    }

    public BigDecimal[] groupMeans_as_BD() {
        if (!this.groupStatsDone) {
            this.groupStatisticsBD();
        }

        return Conv.copy(this.groupMeansBD);
    }

    public double[] groupStandardDeviations() {
        if (!this.groupStatsDone) {
            this.groupStatistics();
        }

        return Conv.copy(this.groupSD);
    }

    public double[] groupStandardErrors() {
        if (!this.groupStatsDone) {
            this.groupStatistics();
        }

        return Conv.copy(this.groupSE);
    }

    public double[] groupMeanLowerConfidenceLimits() {
        if (!this.groupStatsDone) {
            this.groupStatistics();
        }

        return Conv.copy(this.groupClb);
    }

    public double[] groupMeanUpperConfidenceLimits() {
        if (!this.groupStatsDone) {
            this.groupStatistics();
        }

        return Conv.copy(this.groupCub);
    }

    public double[] groupMaxima() {
        if (!this.groupStatsDone) {
            this.groupStatistics();
        }

        return Conv.copy(this.groupMaximum);
    }

    public BigDecimal[] groupMaxima_as_BD() {
        if (!this.groupStatsDone) {
            this.groupStatisticsBD();
        }

        return Conv.copy(this.groupMaximumBD);
    }

    public double[] groupMinima() {
        if (!this.groupStatsDone) {
            this.groupStatistics();
        }

        return Conv.copy(this.groupMinimum);
    }

    public BigDecimal[] groupMinima_as_BD() {
        if (!this.groupStatsDone) {
            this.groupStatisticsBD();
        }

        return Conv.copy(this.groupMinimumBD);
    }

    public double[] groupMedians() {
        if (!this.groupStatsDone) {
            this.groupStatistics();
        }

        return Conv.copy(this.groupMedians);
    }

    public BigDecimal[] groupMedians_as_BD() {
        if (!this.groupStatsDone) {
            this.groupStatisticsBD();
        }

        return Conv.copy(this.groupMediansBD);
    }

    public double[] groupMomentSkewnesses() {
        if (!this.groupStatsDone) {
            this.groupStatistics();
        }

        return Conv.copy(this.groupMomentSkewness);
    }

    public double[] groupMedianSkewnesses() {
        if (!this.groupStatsDone) {
            this.groupStatistics();
        }

        return Conv.copy(this.groupMedianSkewness);
    }

    public double[] groupQuartileSkewnesses() {
        if (!this.groupStatsDone) {
            this.groupStatistics();
        }

        return Conv.copy(this.groupQuartileSkewness);
    }

    public BigDecimal[] groupQuartileSkewnesses_as_BD() {
        if (!this.groupStatsDone) {
            this.groupStatisticsBD();
        }

        return Conv.copy(this.groupQuartileSkewnessBD);
    }

    public double[] groupKurtoses() {
        if (!this.groupStatsDone) {
            this.groupStatistics();
        }

        return Conv.copy(this.groupKurtosis);
    }

    public BigDecimal[] groupKurtoses_as_BD() {
        if (!this.groupStatsDone) {
            this.groupStatisticsBD();
        }

        return Conv.copy(this.groupKurtosisBD);
    }

    public double[] groupExcessKurtoses() {
        if (!this.groupStatsDone) {
            this.groupStatistics();
        }

        return Conv.copy(this.groupExcessKurtosis);
    }

    public BigDecimal[] groupExcessKurtoses_as_BD() {
        if (!this.groupStatsDone) {
            this.groupStatisticsBD();
        }

        return Conv.copy(this.groupExcessKurtosisBD);
    }

    public double[] groupGPPcorrelationCoefficient() {
        if (!this.groupStatsDone) {
            this.groupStatistics();
        }

        return Conv.copy(this.groupProbPlotR);
    }

    public double[] groupGPPgradient() {
        if (!this.groupStatsDone) {
            this.groupStatistics();
        }

        return Conv.copy(this.groupProbPlotGradient);
    }

    public double[] groupGPPintercept() {
        if (!this.groupStatsDone) {
            this.groupStatistics();
        }

        return Conv.copy(this.groupProbPlotIntercept);
    }

    public double[] groupGPPmu() {
        if (!this.groupStatsDone) {
            this.groupStatistics();
        }

        return Conv.copy(this.groupProbPlotMu);
    }

    public double[] groupGPPsigma() {
        if (!this.groupStatsDone) {
            this.groupStatistics();
        }

        return Conv.copy(this.groupProbPlotSigma);
    }

    public double[] groupSumOfSquares() {
        if (!this.groupStatsDone) {
            this.groupStatistics();
        }

        return Conv.copy(this.groupSS);
    }

    public BigDecimal[] groupSumOfSquares_as_BD() {
        if (!this.groupStatsDone) {
            this.groupStatisticsBD();
        }

        return Conv.copy(this.groupSSBD);
    }

    public double totalMean() {
        if (!this.groupStatsDone) {
            this.groupStatistics();
        }

        return this.totalMean;
    }

    public BigDecimal totalMean_as_BD() {
        if (!this.groupStatsDone) {
            this.groupStatisticsBD();
        }

        return this.totalMeanBD;
    }

    public double totalStandardDeviation() {
        if (!this.groupStatsDone) {
            this.groupStatistics();
        }

        return this.totalSD;
    }

    public double totalStandardError() {
        if (!this.groupStatsDone) {
            this.groupStatistics();
        }

        return this.totalSE;
    }

    public double totalMeanLowerConfidenceLimit() {
        if (!this.groupStatsDone) {
            this.groupStatistics();
        }

        return this.totalClb;
    }

    public double totalMeanUpperConfidenceLimit() {
        if (!this.groupStatsDone) {
            this.groupStatistics();
        }

        return this.totalCub;
    }

    public double totalMaximum() {
        if (!this.groupStatsDone) {
            this.groupStatistics();
        }

        return this.totalMaximum;
    }

    public BigDecimal totalMaxima_as_BD() {
        if (!this.groupStatsDone) {
            this.groupStatisticsBD();
        }

        return this.totalMaximumBD;
    }

    public double totalMinimum() {
        if (!this.groupStatsDone) {
            this.groupStatistics();
        }

        return this.totalMinimum;
    }

    public BigDecimal totalMinimum_as_BD() {
        if (!this.groupStatsDone) {
            this.groupStatisticsBD();
        }

        return this.totalMinimumBD;
    }

    public double totalMedian() {
        if (!this.groupStatsDone) {
            this.groupStatistics();
        }

        return this.totalMedian;
    }

    public BigDecimal totalMedian_as_BD() {
        if (!this.groupStatsDone) {
            this.groupStatisticsBD();
        }

        return this.totalMedianBD;
    }

    public double totalMomentSkewness() {
        if (!this.groupStatsDone) {
            this.groupStatistics();
        }

        return this.totalMomentSkewness;
    }

    public double totalMedianSkewness() {
        if (!this.groupStatsDone) {
            this.groupStatistics();
        }

        return this.totalMedianSkewness;
    }

    public double totalQuartileSkewness() {
        if (!this.groupStatsDone) {
            this.groupStatistics();
        }

        return this.totalQuartileSkewness;
    }

    public BigDecimal totalQuartileSkewness_as_BD() {
        if (!this.groupStatsDone) {
            this.groupStatisticsBD();
        }

        return this.totalQuartileSkewnessBD;
    }

    public double totalKurtosis() {
        if (!this.groupStatsDone) {
            this.groupStatistics();
        }

        return this.totalKurtosis;
    }

    public BigDecimal totalKurtosis_as_BD() {
        if (!this.groupStatsDone) {
            this.groupStatisticsBD();
        }

        return this.totalKurtosisBD;
    }

    public double totalExcessKurtosis() {
        if (!this.groupStatsDone) {
            this.groupStatistics();
        }

        return this.totalExcessKurtosis;
    }

    public BigDecimal totalExcessKurtosis_as_BD() {
        if (!this.groupStatsDone) {
            this.groupStatisticsBD();
        }

        return this.totalExcessKurtosisBD;
    }

    public double totalGPPcorrelationCoefficient() {
        if (!this.groupStatsDone) {
            this.groupStatistics();
        }

        return this.totalProbPlotR;
    }

    public double totalGPPgradient() {
        if (!this.groupStatsDone) {
            this.groupStatistics();
        }

        return this.totalProbPlotGradient;
    }

    public double totalGPPintercept() {
        if (!this.groupStatsDone) {
            this.groupStatistics();
        }

        return this.totalProbPlotIntercept;
    }

    public double totalGPPmu() {
        if (!this.groupStatsDone) {
            this.groupStatistics();
        }

        return this.totalProbPlotMu;
    }

    public double totalGPPsigma() {
        if (!this.groupStatsDone) {
            this.groupStatistics();
        }

        return this.totalProbPlotSigma;
    }

    public double totalSumOfSquares() {
        if (!this.groupStatsDone) {
            this.groupStatistics();
        }

        return this.totalSS;
    }

    public BigDecimal totalSumOfSquares_as_BD() {
        if (!this.groupStatsDone) {
            this.groupStatisticsBD();
        }

        return this.totalSSBD;
    }

    public void oneWayAnalysis() {
        if (!this.bigDecimal) {
            this.oneWayAnalysis_d();
        } else {
            this.oneWayAnalysis_BD();
        }

    }

    public void oneWayAnalysisWithPairComparison() {
        this.comparison = true;
        this.nComparisons = this.nGroups * (this.nGroups - 1) / 2;
        this.compGroups = new boolean[this.nGroups];

        for(int var1 = 0; var1 < this.nGroups; ++var1) {
            this.compGroups[var1] = true;
        }

        if (!this.bigDecimal) {
            this.oneWayAnalysis_d();
        } else {
            this.oneWayAnalysis_BD();
        }

    }

    public void oneWayAnalysisWithPairComparison(int var1, int var2) {
        if (var1 == var2) {
            throw new IllegalArgumentException("the two groups must be different");
        } else if (var1 >= 1 && var1 <= this.nGroups) {
            if (var2 >= 1 && var2 <= this.nGroups) {
                this.comparison = true;
                this.compGroups = new boolean[this.nGroups];
                this.nComparisons = 1;
                --var1;
                --var2;

                for(int var3 = 0; var3 < this.nGroups; ++var3) {
                    this.compGroups[var3] = false;
                    if (var3 == var1) {
                        this.compGroups[var3] = true;
                    }

                    if (var3 == var2) {
                        this.compGroups[var3] = true;
                    }
                }

                if (!this.bigDecimal) {
                    this.oneWayAnalysis_d();
                } else {
                    this.oneWayAnalysis_BD();
                }

            } else {
                throw new IllegalArgumentException("The group index, " + var2 + ", must be greater than 0 and less than " + (this.nGroups + 1));
            }
        } else {
            throw new IllegalArgumentException("The group index, " + var1 + ", must be greater than 0 and less than " + (this.nGroups + 1));
        }
    }

    private void oneWayAnalysis_d() {
        this.oneWayANOVA_d();
        this.outputAnalysis();
    }

    private void oneWayAnalysis_BD() {
        this.oneWayANOVA_d();
        this.outputAnalysis();
    }

    public void oneWayAnalysis(String var1) {
        if (!this.bigDecimal) {
            this.oneWayAnalysis_d(var1);
        } else {
            this.oneWayAnalysis_BD(var1);
        }

    }

    public void oneWayAnalysisWithPairComparison(String var1) {
        this.comparison = true;
        this.nComparisons = this.nGroups * (this.nGroups - 1) / 2;
        this.compGroups = new boolean[this.nGroups];

        for(int var2 = 0; var2 < this.nGroups; ++var2) {
            this.compGroups[var2] = true;
        }

        if (!this.bigDecimal) {
            this.oneWayAnalysis_d(var1);
        } else {
            this.oneWayAnalysis_BD(var1);
        }

    }

    public void oneWayAnalysisWithPairComparison(int var1, int var2, String var3) {
        if (var1 == var2) {
            throw new IllegalArgumentException("the two groups must be different");
        } else if (var1 >= 1 && var1 <= this.nGroups) {
            if (var2 >= 1 && var2 <= this.nGroups) {
                this.comparison = true;
                this.compGroups = new boolean[this.nGroups];
                this.nComparisons = 1;
                --var1;
                --var2;

                for(int var4 = 0; var4 < this.nGroups; ++var4) {
                    this.compGroups[var4] = false;
                    if (var4 == var1) {
                        this.compGroups[var4] = true;
                    }

                    if (var4 == var2) {
                        this.compGroups[var4] = true;
                    }
                }

                if (!this.bigDecimal) {
                    this.oneWayAnalysis_d(var3);
                } else {
                    this.oneWayAnalysis_BD(var3);
                }

            } else {
                throw new IllegalArgumentException("The group index, " + var2 + ", must be greater than 0 and less than " + (this.nGroups + 1));
            }
        } else {
            throw new IllegalArgumentException("The group index, " + var1 + ", must be greater than 0 and less than " + (this.nGroups + 1));
        }
    }

    private void oneWayAnalysis_d(String var1) {
        this.oneWayANOVA_d();
        this.outputAnalysis(var1);
    }

    private void oneWayAnalysis_BD(String var1) {
        this.oneWayANOVA_BD();
        this.outputAnalysis(var1);
    }

    public void oneWayANOVA() {
        if (!this.bigDecimal) {
            this.oneWayANOVA_d();
        } else {
            this.oneWayANOVA_BD();
        }

    }

    public void oneWayANOVAwithPairComparison() {
        this.comparison = true;
        this.nComparisons = this.nGroups * (this.nGroups - 1) / 2;
        this.compGroups = new boolean[this.nGroups];

        for(int var1 = 0; var1 < this.nGroups; ++var1) {
            this.compGroups[var1] = true;
        }

        if (!this.bigDecimal) {
            this.oneWayANOVA_d();
        } else {
            this.oneWayANOVA_BD();
        }

    }

    public void oneWayANOVAwithPairComparison(int var1, int var2) {
        if (var1 == var2) {
            throw new IllegalArgumentException("the two groups must be different");
        } else if (var1 >= 1 && var1 <= this.nGroups) {
            if (var2 >= 1 && var2 <= this.nGroups) {
                this.comparison = true;
                this.compGroups = new boolean[this.nGroups];
                this.nComparisons = 1;
                --var1;
                --var2;

                for(int var3 = 0; var3 < this.nGroups; ++var3) {
                    this.compGroups[var3] = false;
                    if (var3 == var1) {
                        this.compGroups[var3] = true;
                    }

                    if (var3 == var2) {
                        this.compGroups[var3] = true;
                    }
                }

                if (!this.bigDecimal) {
                    this.oneWayANOVA_d();
                } else {
                    this.oneWayANOVA_BD();
                }

            } else {
                throw new IllegalArgumentException("The group index, " + var2 + ", must be greater than 0 and less than " + (this.nGroups + 1));
            }
        } else {
            throw new IllegalArgumentException("The group index, " + var1 + ", must be greater than 0 and less than " + (this.nGroups + 1));
        }
    }

    private void oneWayANOVA_d() {
        if (!this.groupStatsDone) {
            if (this.allCalcn) {
                this.groupStatistics();
            } else {
                this.totalMean = 0.0D;

                int var1;
                int var2;
                for(var1 = 0; var1 < this.nGroups; ++var1) {
                    for(var2 = 0; var2 < this.nResponsesPerGroup[var1]; ++var2) {
                        this.totalMean += this.responses0[var1][var2];
                    }
                }

                this.totalMean /= (double)this.nTotalResponses;
                this.totalMeanBD = new BigDecimal(this.totalMean);
                this.groupMeans = new double[this.nGroups];
                this.groupMeansBD = new BigDecimal[this.nGroups];

                for(var1 = 0; var1 < this.nGroups; ++var1) {
                    this.groupMeans[var1] = 0.0D;

                    for(var2 = 0; var2 < this.nResponsesPerGroup[var1]; ++var2) {
                        this.groupMeans[var1] += this.responses0[var1][var2];
                    }

                    this.groupMeans[var1] /= (double)this.nResponsesPerGroup[var1];
                    this.groupMeansBD[var1] = new BigDecimal(this.groupMeans[var1]);
                }
            }
        }

        this.dofTotal = this.nTotalResponses - 1;
        this.dofBetweenGroups = this.nGroups - 1;
        this.dofWithinGroups = this.nTotalResponses - this.nGroups;
        double var14 = 0.0D;
        double var3 = 0.0D;
        double var5 = 0.0D;
        double var7 = 0.0D;

        int var10;
        for(int var9 = 0; var9 < this.nGroups; ++var9) {
            for(var10 = 0; var10 < this.nResponsesPerGroup[var9]; ++var10) {
                var7 = this.responses0[var9][var10] - this.totalMean;
                var14 += var7 * var7;
                var7 = this.groupMeans[var9] - this.totalMean;
                var3 += var7 * var7;
                var7 = this.responses0[var9][var10] - this.groupMeans[var9];
                var5 += var7 * var7;
            }
        }

        this.ssTotal = var14;
        this.ssTotalBD = new BigDecimal(var14);
        this.meanSquareTotal = var14 / (double)this.dofTotal;
        this.meanSquareTotalBD = new BigDecimal(this.meanSquareTotal);
        this.ssBetween = var3;
        this.ssBetweenBD = new BigDecimal(var3);
        this.meanSquareBetween = var3 / (double)this.dofBetweenGroups;
        this.meanSquareBetweenBD = new BigDecimal(this.meanSquareBetween);
        this.ssWithin = var5;
        this.ssWithinBD = new BigDecimal(var5);
        this.meanSquareWithin = var5 / (double)this.dofWithinGroups;
        this.meanSquareWithinBD = new BigDecimal(this.meanSquareWithin);
        this.fRatio = this.meanSquareBetween / this.meanSquareWithin;
        this.fRatioBD = new BigDecimal(this.fRatio);
        this.fRatioP = Stat.fCompCDF(this.fRatio, this.dofBetweenGroups, this.dofWithinGroups);
        this.criticalFratio = Stat.fDistributionInverseCDF(this.dofBetweenGroups, this.dofWithinGroups, this.criticalSignificance);
        if (this.nGroups > 2 && this.comparison) {
            this.meanSquareTotalG = new double[this.nComparisons];
            this.meanSquareWithinG = new double[this.nComparisons];
            this.meanSquareBetweenG = new double[this.nComparisons];
            this.meanSquareTotalBDG = new BigDecimal[this.nComparisons];
            this.meanSquareWithinBDG = new BigDecimal[this.nComparisons];
            this.meanSquareBetweenBDG = new BigDecimal[this.nComparisons];
            this.fRatioG = new double[this.nComparisons];
            this.fRatioBDG = new BigDecimal[this.nComparisons];
            this.fRatioPG = new double[this.nComparisons];
            this.criticalFratioG = new double[this.nComparisons];
            this.ssTotalG = new double[this.nComparisons];
            this.ssWithinG = new double[this.nComparisons];
            this.ssBetweenG = new double[this.nComparisons];
            this.ssTotalBDG = new BigDecimal[this.nComparisons];
            this.ssWithinBDG = new BigDecimal[this.nComparisons];
            this.ssBetweenBDG = new BigDecimal[this.nComparisons];
            this.dofTotalG = new int[this.nComparisons];
            this.dofBetweenG = new int[this.nComparisons];
            this.dofWithinG = new int[this.nComparisons];
            this.pairIndices = new int[this.nComparisons][2];
            double[][] var15 = new double[2][];
            var10 = 0;

            for(int var11 = 0; var11 < this.nGroups - 1; ++var11) {
                for(int var12 = var11 + 1; var12 < this.nGroups; ++var12) {
                    if (this.compGroups[var11] && this.compGroups[var12]) {
                        var15[0] = this.responses0[var11];
                        var15[1] = this.responses0[var12];
                        this.pairIndices[var10][0] = var11;
                        this.pairIndices[var10][1] = var12;
                        ANOVA var13 = new ANOVA();
                        var13.setAllCalcn(false);
                        var13.enterResponseData(var15);
                        var13.oneWayANOVA();
                        this.ssTotalG[var10] = var13.totalSumOfSquares();
                        this.ssWithinG[var10] = var13.withinGroupsSumOfSquares();
                        this.ssBetweenG[var10] = var13.betweenGroupsSumOfSquares();
                        this.ssTotalBDG[var10] = new BigDecimal(this.ssTotalG[var10]);
                        this.ssWithinBDG[var10] = new BigDecimal(this.ssWithinG[var10]);
                        this.ssBetweenBDG[var10] = new BigDecimal(this.ssBetweenG[var10]);
                        this.meanSquareTotalG[var10] = var13.totalMeanSquare();
                        this.meanSquareWithinG[var10] = var13.withinGroupsMeanSquare();
                        this.meanSquareBetweenG[var10] = var13.betweenGroupsMeanSquare();
                        this.meanSquareTotalBDG[var10] = new BigDecimal(this.meanSquareTotalG[var10]);
                        this.meanSquareWithinBDG[var10] = new BigDecimal(this.meanSquareWithinG[var10]);
                        this.meanSquareBetweenBDG[var10] = new BigDecimal(this.meanSquareBetweenG[var10]);
                        this.fRatioG[var10] = var13.oneWayFratio();
                        this.fRatioBDG[var10] = new BigDecimal(this.fRatioG[var10]);
                        this.fRatioPG[var10] = var13.oneWaySignificance();
                        this.criticalFratioG[var10] = var13.criticalFratio();
                        this.dofTotalG[var10] = var13.totalDoF();
                        this.dofBetweenG[var10] = var13.betweenGroupsDoF();
                        this.dofWithinG[var10] = var13.withinGroupsDoF();
                        ++var10;
                    }
                }
            }
        }

        this.oneWayDone = true;
    }

    private void oneWayANOVA_BD() {
        if (!this.groupStatsDone) {
            if (this.allCalcn) {
                this.groupStatisticsBD();
            } else {
                this.totalMeanBD = new BigDecimal("0.0");

                int var1;
                int var2;
                for(var1 = 0; var1 < this.nGroups; ++var1) {
                    for(var2 = 0; var2 < this.nResponsesPerGroup[var1]; ++var2) {
                        this.totalMeanBD = this.totalMeanBD.add(this.responsesBD[var1][var2]);
                    }
                }

                this.totalMeanBD = this.totalMeanBD.divide(new BigDecimal(this.nTotalResponses), 4);
                this.totalMean = this.totalMeanBD.doubleValue();
                this.groupMeans = new double[this.nGroups];
                this.groupMeansBD = new BigDecimal[this.nGroups];

                for(var1 = 0; var1 < this.nGroups; ++var1) {
                    this.groupMeansBD[var1] = new BigDecimal("0.0");

                    for(var2 = 0; var2 < this.nResponsesPerGroup[var1]; ++var2) {
                        this.groupMeansBD[var1] = this.groupMeansBD[var1].add(this.responsesBD[var1][var2]);
                    }

                    this.groupMeansBD[var1] = this.groupMeansBD[var1].divide(new BigDecimal(this.nResponsesPerGroup[var1]), 4);
                    this.groupMeans[var1] = this.groupMeansBD[var1].doubleValue();
                }
            }
        }

        this.dofTotal = this.nTotalResponses - 1;
        this.dofBetweenGroups = this.nGroups - 1;
        this.dofWithinGroups = this.nTotalResponses - this.nGroups;
        BigDecimal var10 = new BigDecimal("0.0");
        BigDecimal var11 = new BigDecimal("0.0");
        BigDecimal var3 = new BigDecimal("0.0");
        new BigDecimal("0.0");

        int var6;
        for(int var5 = 0; var5 < this.nGroups; ++var5) {
            for(var6 = 0; var6 < this.nResponsesPerGroup[var5]; ++var6) {
                BigDecimal var4 = this.responsesBD[var5][var6].subtract(this.totalMeanBD);
                var10 = var10.add(var4.multiply(var4));
                var4 = this.groupMeansBD[var5].subtract(this.totalMeanBD);
                var11 = var11.add(var4.multiply(var4));
                var4 = this.responsesBD[var5][var6].subtract(this.groupMeansBD[var5]);
                var3 = var3.add(var4.multiply(var4));
            }
        }

        this.ssTotalBD = var10;
        this.ssTotal = var10.doubleValue();
        this.meanSquareTotalBD = var10.divide(new BigDecimal((double)this.dofTotal), 4);
        this.meanSquareTotal = this.meanSquareTotalBD.doubleValue();
        this.totalSD = Math.sqrt(this.meanSquareTotalBD.doubleValue());
        this.ssBetweenBD = var11;
        this.ssBetween = var11.doubleValue();
        this.meanSquareBetweenBD = var11.divide(new BigDecimal((double)this.dofBetweenGroups), 4);
        this.meanSquareBetween = this.meanSquareBetweenBD.doubleValue();
        this.ssWithinBD = var3;
        this.ssWithin = var3.doubleValue();
        this.meanSquareWithinBD = var3.divide(new BigDecimal((double)this.dofWithinGroups), 4);
        this.meanSquareWithin = this.meanSquareWithinBD.doubleValue();
        this.fRatioBD = this.meanSquareBetweenBD.divide(this.meanSquareWithinBD, 4);
        this.fRatio = this.fRatioBD.doubleValue();
        this.fRatioP = Stat.fCompCDF(this.fRatio, this.dofBetweenGroups, this.dofWithinGroups);
        this.criticalFratio = Stat.fDistributionInverseCDF(this.dofBetweenGroups, this.dofWithinGroups, this.criticalSignificance);
        if (this.nGroups > 2 && this.comparison) {
            this.meanSquareTotalG = new double[this.nComparisons];
            this.meanSquareWithinG = new double[this.nComparisons];
            this.meanSquareBetweenG = new double[this.nComparisons];
            this.meanSquareTotalBDG = new BigDecimal[this.nComparisons];
            this.meanSquareWithinBDG = new BigDecimal[this.nComparisons];
            this.meanSquareBetweenBDG = new BigDecimal[this.nComparisons];
            this.fRatioG = new double[this.nComparisons];
            this.fRatioBDG = new BigDecimal[this.nComparisons];
            this.fRatioPG = new double[this.nComparisons];
            this.criticalFratioG = new double[this.nComparisons];
            this.ssTotalG = new double[this.nComparisons];
            this.ssWithinG = new double[this.nComparisons];
            this.ssBetweenG = new double[this.nComparisons];
            this.ssTotalBDG = new BigDecimal[this.nComparisons];
            this.ssWithinBDG = new BigDecimal[this.nComparisons];
            this.ssBetweenBDG = new BigDecimal[this.nComparisons];
            this.dofTotalG = new int[this.nComparisons];
            this.dofBetweenG = new int[this.nComparisons];
            this.dofWithinG = new int[this.nComparisons];
            this.pairIndices = new int[this.nComparisons][2];
            BigDecimal[][] var12 = new BigDecimal[2][];
            var6 = 0;

            for(int var7 = 0; var7 < this.nGroups - 1; ++var7) {
                for(int var8 = var7 + 1; var8 < this.nGroups; ++var8) {
                    if (this.compGroups[var7] && this.compGroups[var8]) {
                        var12[0] = this.responsesBD[var7];
                        var12[1] = this.responsesBD[var8];
                        this.pairIndices[var6][0] = var7;
                        this.pairIndices[var6][1] = var8;
                        ANOVA var9 = new ANOVA();
                        var9.setAllCalcn(false);
                        var9.enterResponseData(var12);
                        var9.oneWayANOVA();
                        this.ssTotalBDG[var6] = var9.totalSumOfSquares_as_BD();
                        this.ssWithinBDG[var6] = var9.withinGroupsSumOfSquares_as_BD();
                        this.ssBetweenBDG[var6] = var9.betweenGroupsSumOfSquares_as_BD();
                        this.ssTotalG[var6] = this.ssTotalBDG[var6].doubleValue();
                        this.ssWithinG[var6] = this.ssWithinBDG[var6].doubleValue();
                        this.ssBetweenG[var6] = this.ssBetweenBDG[var6].doubleValue();
                        this.meanSquareTotalBDG[var6] = var9.totalMeanSquare_as_BD();
                        this.meanSquareWithinBDG[var6] = var9.withinGroupsMeanSquare_as_BD();
                        this.meanSquareBetweenBDG[var6] = var9.betweenGroupsMeanSquare_as_BD();
                        this.meanSquareTotalG[var6] = this.meanSquareTotalBDG[var6].doubleValue();
                        this.meanSquareWithinG[var6] = this.meanSquareWithinBDG[var6].doubleValue();
                        this.meanSquareBetweenG[var6] = this.meanSquareBetweenBDG[var6].doubleValue();
                        this.fRatioBDG[var6] = var9.oneWayFratio_as_BD();
                        this.fRatioG[var6] = this.fRatioBDG[var6].doubleValue();
                        this.fRatioPG[var6] = var9.oneWaySignificance();
                        this.criticalFratioG[var6] = var9.criticalFratio();
                        this.dofTotalG[var6] = var9.totalDoF();
                        this.dofBetweenG[var6] = var9.betweenGroupsDoF();
                        this.dofWithinG[var6] = var9.withinGroupsDoF();
                        ++var6;
                    }
                }
            }
        }

        this.oneWayDone = true;
    }

    public double oneWayFratio() {
        if (!this.oneWayDone) {
            this.oneWayANOVA();
        }

        return this.fRatio;
    }

    public double oneWayFratio(int var1, int var2) {
        int var3 = var1 - 1;
        int var4 = var2 - 1;
        boolean var5 = false;
        double var6 = 0.0D;
        if (this.comparison) {
            if (this.compGroups[var3] && this.compGroups[var4]) {
                int var9 = this.pairIndex(var3, var4);
                var6 = this.fRatioG[var9];
            } else {
                ANOVA var8 = new ANOVA();
                var8.enterResponseData(this.responses0);
                var8.oneWayANOVAwithPairComparison(var1, var2);
                var6 = var8.fRatioG[0];
            }
        } else {
            this.oneWayANOVAwithPairComparison(var1, var2);
            var6 = this.fRatioG[0];
        }

        return var6;
    }

    public BigDecimal oneWayFratio_as_BD() {
        if (!this.oneWayDone) {
            this.oneWayANOVA_BD();
        }

        return this.fRatioBD;
    }

    public BigDecimal oneWayFratio_as_BD(int var1, int var2) {
        int var3 = var1 - 1;
        int var4 = var2 - 1;
        boolean var5 = false;
        new BigDecimal("0.0");
        BigDecimal var6;
        if (this.comparison) {
            if (this.compGroups[var3] && this.compGroups[var4]) {
                int var8 = this.pairIndex(var3, var4);
                var6 = this.fRatioBDG[var8];
            } else {
                ANOVA var7 = new ANOVA();
                var7.enterResponseData(this.responses0);
                var7.oneWayANOVAwithPairComparison(var1, var2);
                var6 = var7.fRatioBDG[0];
            }
        } else {
            this.oneWayANOVAwithPairComparison(var1, var2);
            var6 = this.fRatioBDG[0];
        }

        return var6;
    }

    public double oneWaySignificance() {
        if (!this.oneWayDone) {
            this.oneWayANOVA();
        }

        return this.fRatioP;
    }

    public double oneWaySignificance(int var1, int var2) {
        int var3 = var1 - 1;
        int var4 = var2 - 1;
        boolean var5 = false;
        double var6 = 0.0D;
        if (this.comparison) {
            if (this.compGroups[var3] && this.compGroups[var4]) {
                int var9 = this.pairIndex(var3, var4);
                var6 = this.fRatioPG[var9];
            } else {
                ANOVA var8 = new ANOVA();
                var8.enterResponseData(this.responses0);
                var8.oneWayANOVAwithPairComparison(var1, var2);
                var6 = var8.fRatioPG[0];
            }
        } else {
            this.oneWayANOVAwithPairComparison(var1, var2);
            var6 = this.fRatioPG[0];
        }

        return var6;
    }

    public double criticalFratio() {
        if (!this.oneWayDone) {
            this.oneWayANOVA();
        }

        return this.criticalFratio;
    }

    public double criticalFratio(int var1, int var2) {
        int var3 = var1 - 1;
        int var4 = var2 - 1;
        boolean var5 = false;
        double var6 = 0.0D;
        if (this.comparison) {
            if (this.compGroups[var3] && this.compGroups[var4]) {
                int var9 = this.pairIndex(var3, var4);
                var6 = this.criticalFratioG[var9];
            } else {
                ANOVA var8 = new ANOVA();
                var8.enterResponseData(this.responses0);
                var8.oneWayANOVAwithPairComparison(var1, var2);
                var6 = var8.criticalFratioG[0];
            }
        } else {
            this.oneWayANOVAwithPairComparison(var1, var2);
            var6 = this.criticalFratioG[0];
        }

        return var6;
    }

    public double totalMeanSquare() {
        if (!this.oneWayDone) {
            this.oneWayANOVA();
        }

        return this.meanSquareTotal;
    }

    public double totalMeanSquare(int var1, int var2) {
        int var3 = var1 - 1;
        int var4 = var2 - 1;
        boolean var5 = false;
        double var6 = 0.0D;
        if (this.comparison) {
            if (this.compGroups[var3] && this.compGroups[var4]) {
                int var9 = this.pairIndex(var3, var4);
                var6 = this.meanSquareTotalG[var9];
            } else {
                ANOVA var8 = new ANOVA();
                var8.enterResponseData(this.responses0);
                var8.oneWayANOVAwithPairComparison(var1, var2);
                var6 = var8.meanSquareTotalG[0];
            }
        } else {
            this.oneWayANOVAwithPairComparison(var1, var2);
            var6 = this.meanSquareTotalG[0];
        }

        return var6;
    }

    public BigDecimal totalMeanSquare_as_BD() {
        if (!this.oneWayDone) {
            this.oneWayANOVA_BD();
        }

        return this.meanSquareTotalBD;
    }

    public BigDecimal totalMeanSquare_as_BD(int var1, int var2) {
        int var3 = var1 - 1;
        int var4 = var2 - 1;
        boolean var5 = false;
        new BigDecimal("0.0");
        BigDecimal var6;
        if (this.comparison) {
            if (this.compGroups[var3] && this.compGroups[var4]) {
                int var8 = this.pairIndex(var3, var4);
                var6 = this.meanSquareTotalBDG[var8];
            } else {
                ANOVA var7 = new ANOVA();
                var7.enterResponseData(this.responses0);
                var7.oneWayANOVAwithPairComparison(var1, var2);
                var6 = var7.meanSquareTotalBDG[0];
            }
        } else {
            this.oneWayANOVAwithPairComparison(var1, var2);
            var6 = this.meanSquareTotalBDG[0];
        }

        return var6;
    }

    public double betweenGroupsMeanSquare() {
        if (!this.oneWayDone) {
            this.oneWayANOVA();
        }

        return this.meanSquareBetween;
    }

    public double betweenGroupsMeanSquare(int var1, int var2) {
        int var3 = var1 - 1;
        int var4 = var2 - 1;
        boolean var5 = false;
        double var6 = 0.0D;
        if (this.comparison) {
            if (this.compGroups[var3] && this.compGroups[var4]) {
                int var9 = this.pairIndex(var3, var4);
                var6 = this.meanSquareBetweenG[var9];
            } else {
                ANOVA var8 = new ANOVA();
                var8.enterResponseData(this.responses0);
                var8.oneWayANOVAwithPairComparison(var1, var2);
                var6 = var8.meanSquareBetweenG[0];
            }
        } else {
            this.oneWayANOVAwithPairComparison(var1, var2);
            var6 = this.meanSquareBetweenG[0];
        }

        return var6;
    }

    public BigDecimal betweenGroupsMeanSquare_as_BD() {
        if (!this.oneWayDone) {
            this.oneWayANOVA_BD();
        }

        return this.meanSquareBetweenBD;
    }

    public BigDecimal betweenGroupsMeanSquare_as_BD(int var1, int var2) {
        int var3 = var1 - 1;
        int var4 = var2 - 1;
        boolean var5 = false;
        new BigDecimal("0.0");
        BigDecimal var6;
        if (this.comparison) {
            if (this.compGroups[var3] && this.compGroups[var4]) {
                int var8 = this.pairIndex(var3, var4);
                var6 = this.meanSquareBetweenBDG[var8];
            } else {
                ANOVA var7 = new ANOVA();
                var7.enterResponseData(this.responses0);
                var7.oneWayANOVAwithPairComparison(var1, var2);
                var6 = var7.meanSquareBetweenBDG[0];
            }
        } else {
            this.oneWayANOVAwithPairComparison(var1, var2);
            var6 = this.meanSquareBetweenBDG[0];
        }

        return var6;
    }

    public double withinGroupsMeanSquare() {
        if (!this.oneWayDone) {
            this.oneWayANOVA();
        }

        return this.meanSquareWithin;
    }

    public double withinGroupsMeanSquare(int var1, int var2) {
        int var3 = var1 - 1;
        int var4 = var2 - 1;
        boolean var5 = false;
        double var6 = 0.0D;
        if (this.comparison) {
            if (this.compGroups[var3] && this.compGroups[var4]) {
                int var9 = this.pairIndex(var3, var4);
                var6 = this.meanSquareWithinG[var9];
            } else {
                ANOVA var8 = new ANOVA();
                var8.enterResponseData(this.responses0);
                var8.oneWayANOVAwithPairComparison(var1, var2);
                var6 = var8.meanSquareWithinG[0];
            }
        } else {
            this.oneWayANOVAwithPairComparison(var1, var2);
            var6 = this.meanSquareWithinG[0];
        }

        return var6;
    }

    public BigDecimal withinGroupsMeanSquare_as_BD() {
        if (!this.oneWayDone) {
            this.oneWayANOVA_BD();
        }

        return this.meanSquareWithinBD;
    }

    public BigDecimal withinGroupsMeanSquare_as_BD(int var1, int var2) {
        int var3 = var1 - 1;
        int var4 = var2 - 1;
        boolean var5 = false;
        new BigDecimal("0.0");
        BigDecimal var6;
        if (this.comparison) {
            if (this.compGroups[var3] && this.compGroups[var4]) {
                int var8 = this.pairIndex(var3, var4);
                var6 = this.meanSquareWithinBDG[var8];
            } else {
                ANOVA var7 = new ANOVA();
                var7.enterResponseData(this.responses0);
                var7.oneWayANOVAwithPairComparison(var1, var2);
                var6 = var7.meanSquareWithinBDG[0];
            }
        } else {
            this.oneWayANOVAwithPairComparison(var1, var2);
            var6 = this.meanSquareWithinBDG[0];
        }

        return var6;
    }

    public double betweenGroupsSumOfSquares() {
        if (!this.oneWayDone) {
            this.oneWayANOVA();
        }

        return this.ssBetween;
    }

    public double betweenGroupsSumOfSquares(int var1, int var2) {
        int var3 = var1 - 1;
        int var4 = var2 - 1;
        boolean var5 = false;
        double var6 = 0.0D;
        if (this.comparison) {
            if (this.compGroups[var3] && this.compGroups[var4]) {
                int var9 = this.pairIndex(var3, var4);
                var6 = this.ssBetweenG[var9];
            } else {
                ANOVA var8 = new ANOVA();
                var8.enterResponseData(this.responses0);
                var8.oneWayANOVAwithPairComparison(var1, var2);
                var6 = var8.ssBetweenG[0];
            }
        } else {
            this.oneWayANOVAwithPairComparison(var1, var2);
            var6 = this.ssBetweenG[0];
        }

        return var6;
    }

    public BigDecimal betweenGroupsSumOfSquares_as_BD() {
        if (!this.oneWayDone) {
            this.oneWayANOVA_BD();
        }

        return this.ssBetweenBD;
    }

    public BigDecimal betweenGroupsSumOfSquares_as_BD(int var1, int var2) {
        int var3 = var1 - 1;
        int var4 = var2 - 1;
        boolean var5 = false;
        new BigDecimal("0.0");
        BigDecimal var6;
        if (this.comparison) {
            if (this.compGroups[var3] && this.compGroups[var4]) {
                int var8 = this.pairIndex(var3, var4);
                var6 = this.ssBetweenBDG[var8];
            } else {
                ANOVA var7 = new ANOVA();
                var7.enterResponseData(this.responses0);
                var7.oneWayANOVAwithPairComparison(var1, var2);
                var6 = var7.ssBetweenBDG[0];
            }
        } else {
            this.oneWayANOVAwithPairComparison(var1, var2);
            var6 = this.ssBetweenBDG[0];
        }

        return var6;
    }

    public double withinGroupsSumOfSquares() {
        if (!this.oneWayDone) {
            this.oneWayANOVA();
        }

        return this.ssWithin;
    }

    public double withinGroupsSumOfSquares(int var1, int var2) {
        int var3 = var1 - 1;
        int var4 = var2 - 1;
        boolean var5 = false;
        double var6 = 0.0D;
        if (this.comparison) {
            if (this.compGroups[var3] && this.compGroups[var4]) {
                int var9 = this.pairIndex(var3, var4);
                var6 = this.ssWithinG[var9];
            } else {
                ANOVA var8 = new ANOVA();
                var8.enterResponseData(this.responses0);
                var8.oneWayANOVAwithPairComparison(var1, var2);
                var6 = var8.ssWithinG[0];
            }
        } else {
            this.oneWayANOVAwithPairComparison(var1, var2);
            var6 = this.ssWithinG[0];
        }

        return var6;
    }

    public BigDecimal withinGroupsSumOfSquares_as_BD() {
        if (!this.oneWayDone) {
            this.oneWayANOVA_BD();
        }

        return this.ssWithinBD;
    }

    public BigDecimal withinGroupsSumOfSquares_as_BD(int var1, int var2) {
        int var3 = var1 - 1;
        int var4 = var2 - 1;
        boolean var5 = false;
        new BigDecimal("0.0");
        BigDecimal var6;
        if (this.comparison) {
            if (this.compGroups[var3] && this.compGroups[var4]) {
                int var8 = this.pairIndex(var3, var4);
                var6 = this.ssWithinBDG[var8];
            } else {
                ANOVA var7 = new ANOVA();
                var7.enterResponseData(this.responses0);
                var7.oneWayANOVAwithPairComparison(var1, var2);
                var6 = var7.ssWithinBDG[0];
            }
        } else {
            this.oneWayANOVAwithPairComparison(var1, var2);
            var6 = this.ssWithinBDG[0];
        }

        return var6;
    }

    public double totalSumOfSquares(int var1, int var2) {
        int var3 = var1 - 1;
        int var4 = var2 - 1;
        boolean var5 = false;
        double var6 = 0.0D;
        if (this.comparison) {
            if (this.compGroups[var3] && this.compGroups[var4]) {
                int var9 = this.pairIndex(var3, var4);
                var6 = this.ssTotalG[var9];
            } else {
                ANOVA var8 = new ANOVA();
                var8.enterResponseData(this.responses0);
                var8.oneWayANOVAwithPairComparison(var1, var2);
                var6 = var8.ssTotalG[0];
            }
        } else {
            this.oneWayANOVAwithPairComparison(var1, var2);
            var6 = this.ssTotalG[0];
        }

        return var6;
    }

    public BigDecimal totalSumOfSquares_as_BD(int var1, int var2) {
        int var3 = var1 - 1;
        int var4 = var2 - 1;
        boolean var5 = false;
        new BigDecimal("0.0");
        BigDecimal var6;
        if (this.comparison) {
            if (this.compGroups[var3] && this.compGroups[var4]) {
                int var8 = this.pairIndex(var3, var4);
                var6 = this.ssTotalBDG[var8];
            } else {
                ANOVA var7 = new ANOVA();
                var7.enterResponseData(this.responses0);
                var7.oneWayANOVAwithPairComparison(var1, var2);
                var6 = var7.ssTotalBDG[0];
            }
        } else {
            this.oneWayANOVAwithPairComparison(var1, var2);
            var6 = this.ssTotalBDG[0];
        }

        return var6;
    }

    public int totalDoF() {
        if (!this.oneWayDone) {
            this.oneWayANOVA();
        }

        return this.dofTotal;
    }

    public int totalDoF(int var1, int var2) {
        int var3 = var1 - 1;
        int var4 = var2 - 1;
        boolean var5 = false;
        boolean var6 = false;
        int var9;
        if (this.comparison) {
            if (this.compGroups[var3] && this.compGroups[var4]) {
                int var8 = this.pairIndex(var3, var4);
                var9 = this.dofTotalG[var8];
            } else {
                ANOVA var7 = new ANOVA();
                var7.enterResponseData(this.responses0);
                var7.oneWayANOVAwithPairComparison(var1, var2);
                var9 = var7.dofTotalG[0];
            }
        } else {
            this.oneWayANOVAwithPairComparison(var1, var2);
            var9 = this.dofTotalG[0];
        }

        return var9;
    }

    public int withinGroupsDoF() {
        if (!this.oneWayDone) {
            this.oneWayANOVA();
        }

        return this.dofWithinGroups;
    }

    public int withinGroupsDoF(int var1, int var2) {
        int var3 = var1 - 1;
        int var4 = var2 - 1;
        boolean var5 = false;
        boolean var6 = false;
        int var9;
        if (this.comparison) {
            if (this.compGroups[var3] && this.compGroups[var4]) {
                int var8 = this.pairIndex(var3, var4);
                var9 = this.dofWithinG[var8];
            } else {
                ANOVA var7 = new ANOVA();
                var7.enterResponseData(this.responses0);
                var7.oneWayANOVAwithPairComparison(var1, var2);
                var9 = var7.dofWithinG[0];
            }
        } else {
            this.oneWayANOVAwithPairComparison(var1, var2);
            var9 = this.dofWithinG[0];
        }

        return var9;
    }

    public int betweenGroupsDoF() {
        if (!this.oneWayDone) {
            this.oneWayANOVA();
        }

        return this.dofBetweenGroups;
    }

    public int betweenGroupsDoF(int var1, int var2) {
        int var3 = var1 - 1;
        int var4 = var2 - 1;
        boolean var5 = false;
        boolean var6 = false;
        int var9;
        if (this.comparison) {
            if (this.compGroups[var3] && this.compGroups[var4]) {
                int var8 = this.pairIndex(var3, var4);
                var9 = this.dofBetweenG[var8];
            } else {
                ANOVA var7 = new ANOVA();
                var7.enterResponseData(this.responses0);
                var7.oneWayANOVAwithPairComparison(var1, var2);
                var9 = var7.dofBetweenG[0];
            }
        } else {
            this.oneWayANOVAwithPairComparison(var1, var2);
            var9 = this.dofBetweenG[0];
        }

        return var9;
    }

    private int pairIndex(int var1, int var2) {
        int var3 = -1;
        int var4 = 0;
        boolean var5 = true;

        while(true) {
            while(var5) {
                if (this.pairIndices[var4][0] == var1 && this.pairIndices[var4][1] == var2) {
                    var3 = var4;
                    var5 = false;
                } else if (this.pairIndices[var4][0] == var2 && this.pairIndices[var4][1] == var1) {
                    var3 = var4;
                    var5 = false;
                } else {
                    ++var4;
                    if (var4 >= this.nComparisons) {
                        throw new IllegalArgumentException("No index found " + var1 + " and " + var2);
                    }
                }
            }

            return var3;
        }
    }

    public void outputAnalysis() {
        if (!this.oneWayDone) {
            System.out.println("Method outputAnalysis: As no ANOVA peforming method had been called a ANOVA of all groups, withour pair comparison, has now been performed");
            this.oneWayANOVA();
        }

        this.outputFilename = "OneWayANOVAoutput";
        switch(this.fileOption) {
            case 1:
                this.outputFilename = this.outputFilename + ".txt";
                break;
            case 2:
                this.outputFilename = this.outputFilename + ".xls";
        }

        String var1 = "Output file name for the one way ANOVA:";
        String var2 = "\nEnter the required name (as a single word) and click OK ";
        String var3 = "\nor simply click OK for default value";
        String var4 = var1 + var2 + var3;
        String var5 = this.outputFilename;
        this.outputFilename = Db.readLine(var4, var5);
        this.outputAnalysis(this.outputFilename);
    }

    public void outputAnalysis(String var1) {
        if (!this.oneWayDone) {
            System.out.println("Method outputAnalysis: As no ANOVA peforming method had been called a ANOVA of all groups, withour pair comparison, has now been performed");
            this.oneWayANOVA();
        }

        this.outputFilename = var1;
        String var2 = null;
        String var3 = null;
        int var4 = var1.indexOf(46);
        if (var4 == -1) {
            switch(this.fileOption) {
                case 1:
                    this.outputFilename = this.outputFilename + ".txt";
                    break;
                case 2:
                    this.outputFilename = this.outputFilename + ".xls";
            }
        } else {
            var3 = var1.substring(var4).trim();
            var2 = var1.substring(0, var4).trim();
            if (var3.equalsIgnoreCase(".xlsx")) {
                var3 = ".xls";
                this.outputFilename = var2 + var3;
            }

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
                    var5 = "Your entered output file type is " + var3;
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

        if (!this.truncAll) {
            this.setOutputPrecision();
        }

        if (this.fileOption == 1) {
            this.outputText();
        } else {
            this.outputExcel();
        }

        System.out.println("The ANOVA summary has been written to the file " + this.outputFilename);
    }

    private void setOutputPrecision() {
        int var1 = Fmath.checkPrecision(this.responses0[0][0]);

        for(int var3 = 0; var3 < this.nGroups; ++var3) {
            for(int var4 = 0; var4 < this.nResponsesPerGroup[var3]; ++var4) {
                int var2 = Fmath.checkPrecision(this.responses0[var3][var4]);
                if (var2 > var1) {
                    var1 = var2;
                }
            }
        }

        if (!this.truncAll && var1 > this.trunc) {
            this.trunc = var1;
        }

    }

    private void outputText() {
        FileOutput var1 = new FileOutput(this.outputFilename);
        var1.println("PROGRAM ANOVA");

        int var2;
        for(var2 = 0; var2 < this.nTitle; ++var2) {
            var1.println(this.title[var2]);
        }

        if (this.inputFilename != null) {
            var1.println("Input file name: " + this.inputFilename);
        }

        var1.println("This output file name: " + this.outputFilename);
        var1.println();
        var1.println("ALL GROUPS: One-Way Analysis of Variance");
        var1.print(" ", 16);
        var1.print(" ", this.fieldD);
        var1.print("  ", this.fieldD);
        var1.print(" ", this.fieldD);
        var1.print(" ", this.fieldD);
        var1.print("F-ratio at", this.fieldD);
        var1.println(" ");
        var1.print(" ", 16);
        var1.print("Sum of", this.fieldD);
        var1.print("Degrees of ", this.fieldD);
        var1.print("Mean ", this.fieldD);
        var1.print(" ", this.fieldD);
        var1.print(this.criticalSignificanceF + " critical", this.fieldD);
        var1.println(" ");
        var1.print(" ", 16);
        var1.print("squares", this.fieldD);
        var1.print("freedom ", this.fieldD);
        var1.print("square ", this.fieldD);
        var1.print("F-ratio ", this.fieldD);
        var1.print("level", this.fieldD);
        var1.println("Significance");
        var1.print("Between groups", 16);
        var1.print(Fmath.truncate(this.ssBetween, this.trunc), this.fieldD);
        var1.print(this.dofBetweenGroups, this.fieldD);
        var1.print(Fmath.truncate(this.meanSquareBetween, this.trunc), this.fieldD);
        var1.print(Fmath.truncate(this.fRatio, this.trunc), this.fieldD);
        var1.print(Fmath.truncate(this.criticalFratio, this.trunc), this.fieldD);
        var1.println(Fmath.truncate(this.fRatioP, this.trunc));
        var1.print("Within groups", 16);
        var1.print(Fmath.truncate(this.ssWithin, this.trunc), this.fieldD);
        var1.print(this.dofWithinGroups, this.fieldD);
        var1.println(Fmath.truncate(this.meanSquareWithin, this.trunc));
        var1.print("Total", 16);
        var1.print(Fmath.truncate(this.ssTotal, this.trunc), this.fieldD);
        var1.print(this.dofTotal, this.fieldD);
        var1.println(Fmath.truncate(this.meanSquareTotal, this.trunc));
        var1.println();
        var1.println();
        var1.println("ALL GROUPS: Individual Group Statistics");
        var1.print(" ", this.fieldD);
        var1.print(" ", this.fieldD);
        var1.print(" ", this.fieldD);
        var1.print(" ", this.fieldD);
        var1.print(" ", this.fieldD);
        var1.println(this.criticalSignificanceS + " confidence interval");
        var1.print(" ", this.fieldD);
        var1.print(" ", this.fieldD);
        var1.print(" ", this.fieldD);
        var1.print(" ", this.fieldD);
        var1.print(" ", this.fieldD);
        var1.println("of the mean");
        var1.print(" ", this.fieldD);
        var1.print("Number of", this.fieldD);
        var1.print(" ", this.fieldD);
        var1.print("Standard", this.fieldD);
        var1.print("Standard", this.fieldD);
        var1.print("lower", this.fieldD);
        var1.println("upper");
        var1.print(" ", this.fieldD);
        var1.print("responses", this.fieldD);
        var1.print("Mean", this.fieldD);
        var1.print("deviation", this.fieldD);
        var1.print("error", this.fieldD);
        var1.print("bound ", this.fieldD);
        var1.println("bound ");

        for(var2 = 0; var2 < this.nGroups; ++var2) {
            var1.print(this.groupNames[var2], this.fieldD);
            var1.print(this.nResponsesPerGroup[var2], this.fieldD);
            var1.print(Fmath.truncate(this.groupMeans[var2], this.trunc), this.fieldD);
            var1.print(Fmath.truncate(this.groupSD[var2], this.trunc), this.fieldD);
            var1.print(Fmath.truncate(this.groupSE[var2], this.trunc), this.fieldD);
            var1.print(Fmath.truncate(this.groupClb[var2], this.trunc), this.fieldD);
            var1.println(Fmath.truncate(this.groupCub[var2], this.trunc));
        }

        var1.println(" ");
        var1.print("Total", this.fieldD);
        var1.print(this.nTotalResponses, this.fieldD);
        var1.print(Fmath.truncate(this.totalMean, this.trunc), this.fieldD);
        var1.print(Fmath.truncate(this.totalSD, this.trunc), this.fieldD);
        var1.print(Fmath.truncate(this.totalSE, this.trunc), this.fieldD);
        var1.print(Fmath.truncate(this.totalClb, this.trunc), this.fieldD);
        var1.println(Fmath.truncate(this.totalCub, this.trunc));
        var1.println();
        var1.print("       ", this.fieldD);
        var1.print("minimum", this.fieldD);
        var1.print("median ", this.fieldD);
        var1.print("maximum ", this.fieldD);
        var1.print("moment ", this.fieldD);
        var1.print("median ", this.fieldD);
        var1.print("quartile ", this.fieldD);
        var1.print("kurtosis ", this.fieldD);
        var1.println("excess ");
        var1.print("       ", this.fieldD);
        var1.print("       ", this.fieldD);
        var1.print("       ", this.fieldD);
        var1.print("       ", this.fieldD);
        var1.print("skewness", this.fieldD);
        var1.print("skewness", this.fieldD);
        var1.print("skewness", this.fieldD);
        var1.print("        ", this.fieldD);
        var1.println("kurtosis");

        for(var2 = 0; var2 < this.nGroups; ++var2) {
            var1.print(this.groupNames[var2], this.fieldD);
            var1.print(Fmath.truncate(this.groupMinimum[var2], this.trunc), this.fieldD);
            var1.print(Fmath.truncate(this.groupMedians[var2], this.trunc), this.fieldD);
            var1.print(Fmath.truncate(this.groupMaximum[var2], this.trunc), this.fieldD);
            var1.print(Fmath.truncate(this.groupMomentSkewness[var2], this.trunc), this.fieldD);
            var1.print(Fmath.truncate(this.groupMedianSkewness[var2], this.trunc), this.fieldD);
            var1.print(Fmath.truncate(this.groupQuartileSkewness[var2], this.trunc), this.fieldD);
            var1.print(Fmath.truncate(this.groupKurtosis[var2], this.trunc), this.fieldD);
            var1.println(Fmath.truncate(this.groupExcessKurtosis[var2], this.trunc));
        }

        var1.println(" ");
        var1.print("Total", this.fieldD);
        var1.print(Fmath.truncate(this.totalMinimum, this.trunc), this.fieldD);
        var1.print(Fmath.truncate(this.totalMedian, this.trunc), this.fieldD);
        var1.print(Fmath.truncate(this.totalMaximum, this.trunc), this.fieldD);
        var1.print(Fmath.truncate(this.totalMomentSkewness, this.trunc), this.fieldD);
        var1.print(Fmath.truncate(this.totalMedianSkewness, this.trunc), this.fieldD);
        var1.print(Fmath.truncate(this.totalQuartileSkewness, this.trunc), this.fieldD);
        var1.print(Fmath.truncate(this.totalKurtosis, this.trunc), this.fieldD);
        var1.print(Fmath.truncate(this.totalExcessKurtosis, this.trunc), this.fieldD);
        var1.println(Fmath.truncate(this.totalProbPlotR, this.trunc));
        var1.println();
        var1.println("Gaussian Probability Plot");
        var1.print("       ", this.fieldD);
        var1.print("Correlation", this.fieldD);
        var1.print("Gradient", this.fieldD);
        var1.print("Intercept", this.fieldD);
        var1.print("mu", this.fieldD);
        var1.println("sigma");
        var1.print("       ", this.fieldD);
        var1.print("coefficient", this.fieldD);
        var1.print("       ", this.fieldD);
        var1.print("       ", this.fieldD);
        var1.print("       ", this.fieldD);
        var1.println("     ");

        for(var2 = 0; var2 < this.nGroups; ++var2) {
            var1.print(this.groupNames[var2], this.fieldD);
            var1.print(Fmath.truncate(this.groupProbPlotR[var2], this.trunc), this.fieldD);
            var1.print(Fmath.truncate(this.groupProbPlotGradient[var2], this.trunc), this.fieldD);
            var1.print(Fmath.truncate(this.groupProbPlotIntercept[var2], this.trunc), this.fieldD);
            var1.print(Fmath.truncate(this.groupProbPlotMu[var2], this.trunc), this.fieldD);
            var1.println(Fmath.truncate(this.groupProbPlotSigma[var2], this.trunc));
        }

        var1.println(" ");
        var1.print("total", this.fieldD);
        var1.print(Fmath.truncate(this.totalProbPlotR, this.trunc), this.fieldD);
        var1.print(Fmath.truncate(this.totalProbPlotGradient, this.trunc), this.fieldD);
        var1.print(Fmath.truncate(this.totalProbPlotIntercept, this.trunc), this.fieldD);
        var1.print(Fmath.truncate(this.totalProbPlotMu, this.trunc), this.fieldD);
        var1.println(Fmath.truncate(this.totalProbPlotSigma, this.trunc));
        if (this.nGroups > 2 && this.comparison) {
            var1.println(" ");
            var1.println(" ");
            var1.println("COMPARISON OF PAIRS OF GROUPS: One-Way Analysis of Variance");
            var2 = 0;

            for(int var3 = 0; var3 < this.nGroups - 1; ++var3) {
                for(int var4 = var3 + 1; var4 < this.nGroups; ++var4) {
                    if (this.compGroups[var3] && this.compGroups[var4]) {
                        var1.println("Group" + (var3 + 1) + " and Group" + (var4 + 1));
                        var1.print(" ", 16);
                        var1.print(" ", this.fieldD);
                        var1.print("  ", this.fieldD);
                        var1.print(" ", this.fieldD);
                        var1.print(" ", this.fieldD);
                        var1.print("F-ratio at", this.fieldD);
                        var1.println(" ");
                        var1.print(" ", 16);
                        var1.print("Sum of", this.fieldD);
                        var1.print("Degrees of ", this.fieldD);
                        var1.print("Mean ", this.fieldD);
                        var1.print(" ", this.fieldD);
                        var1.print(this.criticalSignificanceF + " critical", this.fieldD);
                        var1.println(" ");
                        var1.print(" ", 16);
                        var1.print("squares", this.fieldD);
                        var1.print("freedom ", this.fieldD);
                        var1.print("square ", this.fieldD);
                        var1.print("F-ratio ", this.fieldD);
                        var1.print("level", this.fieldD);
                        var1.println("Significance");
                        var1.print("Between groups", 16);
                        var1.print(Fmath.truncate(this.ssBetweenG[var2], this.trunc), this.fieldD);
                        var1.print(this.dofBetweenG[var2], this.fieldD);
                        var1.print(Fmath.truncate(this.meanSquareBetweenG[var2], this.trunc), this.fieldD);
                        var1.print(Fmath.truncate(this.fRatioG[var2], this.trunc), this.fieldD);
                        var1.print(Fmath.truncate(this.criticalFratioG[var2], this.trunc), this.fieldD);
                        var1.println(Fmath.truncate(this.fRatioPG[var2], this.trunc));
                        var1.print("Within groups", 16);
                        var1.print(Fmath.truncate(this.ssWithinG[var2], this.trunc), this.fieldD);
                        var1.print(this.dofWithinG[var2], this.fieldD);
                        var1.println(Fmath.truncate(this.meanSquareWithinG[var2], this.trunc));
                        var1.print("Total", 16);
                        var1.print(Fmath.truncate(this.ssTotalG[var2], this.trunc), this.fieldD);
                        var1.print(this.dofTotalG[var2], this.fieldD);
                        var1.println(Fmath.truncate(this.meanSquareTotalG[var2], this.trunc));
                        ++var2;
                        var1.println();
                        var1.println();
                    }
                }
            }
        }

        var1.close();
    }

    private void outputExcel() {
        FileOutput var1 = new FileOutput(this.outputFilename);
        var1.println("PROGRAM ANOVA");

        int var2;
        for(var2 = 0; var2 < this.nTitle; ++var2) {
            var1.println(this.title[var2]);
        }

        if (this.inputFilename != null) {
            var1.println("Input file name: " + this.inputFilename);
        }

        var1.println("This output file name: " + this.outputFilename);
        var1.println();
        var1.println("ALL GROUPS: One-Way Analysis of Variance");
        var1.printtab(" ");
        var1.printtab(" ");
        var1.printtab("  ");
        var1.printtab(" ");
        var1.printtab(" ");
        var1.printtab("F-ratio at");
        var1.println(" ");
        var1.printtab(" ");
        var1.printtab("Sum of");
        var1.printtab("Degrees of ");
        var1.printtab("Mean ");
        var1.printtab(" ");
        var1.printtab(this.criticalSignificanceF + " critical");
        var1.println(" ");
        var1.printtab(" ");
        var1.printtab("squares");
        var1.printtab("freedom ");
        var1.printtab("square ");
        var1.printtab("F-ratio ");
        var1.printtab("level");
        var1.println("Significance");
        var1.printtab("Between groups");
        var1.printtab(Fmath.truncate(this.ssBetween, this.trunc));
        var1.printtab(this.dofBetweenGroups);
        var1.printtab(Fmath.truncate(this.meanSquareBetween, this.trunc));
        var1.printtab(Fmath.truncate(this.fRatio, this.trunc));
        var1.printtab(Fmath.truncate(this.criticalFratio, this.trunc));
        var1.println(Fmath.truncate(this.fRatioP, this.trunc));
        var1.printtab("Within groups");
        var1.printtab(Fmath.truncate(this.ssWithin, this.trunc));
        var1.printtab(this.dofWithinGroups);
        var1.println(Fmath.truncate(this.meanSquareWithin, this.trunc));
        var1.printtab("Total");
        var1.printtab(Fmath.truncate(this.ssTotal, this.trunc));
        var1.printtab(this.dofTotal);
        var1.println(Fmath.truncate(this.meanSquareTotal, this.trunc));
        var1.println();
        var1.println();
        var1.println("ALL GROUPS: Individual Group Statistics");
        var1.printtab(" ");
        var1.printtab(" ");
        var1.printtab(" ");
        var1.printtab(" ");
        var1.printtab(" ");
        var1.println(this.criticalSignificanceS + " confidence interval");
        var1.printtab(" ");
        var1.printtab(" ");
        var1.printtab(" ");
        var1.printtab(" ");
        var1.printtab(" ");
        var1.println("of the mean");
        var1.printtab(" ");
        var1.printtab("Number of");
        var1.printtab(" ");
        var1.printtab("Standard");
        var1.printtab("Standard");
        var1.printtab("lower");
        var1.println("upper");
        var1.printtab(" ");
        var1.printtab("responses");
        var1.printtab("Mean");
        var1.printtab("deviation");
        var1.printtab("error");
        var1.printtab("bound ");
        var1.println("bound ");

        for(var2 = 0; var2 < this.nGroups; ++var2) {
            var1.printtab(this.groupNames[var2]);
            var1.printtab(this.nResponsesPerGroup[var2]);
            var1.printtab(Fmath.truncate(this.groupMeans[var2], this.trunc));
            var1.printtab(Fmath.truncate(this.groupSD[var2], this.trunc));
            var1.printtab(Fmath.truncate(this.groupSE[var2], this.trunc));
            var1.printtab(Fmath.truncate(this.groupClb[var2], this.trunc));
            var1.println(Fmath.truncate(this.groupCub[var2], this.trunc));
        }

        var1.println(" ");
        var1.printtab("Total");
        var1.printtab(this.nTotalResponses);
        var1.printtab(Fmath.truncate(this.totalMean, this.trunc));
        var1.printtab(Fmath.truncate(this.totalSD, this.trunc));
        var1.printtab(Fmath.truncate(this.totalSE, this.trunc));
        var1.printtab(Fmath.truncate(this.totalClb, this.trunc));
        var1.println(Fmath.truncate(this.totalCub, this.trunc));
        var1.println();
        var1.printtab("       ");
        var1.printtab("minimum");
        var1.printtab("median ");
        var1.printtab("maximum ");
        var1.printtab("moment ");
        var1.printtab("median ");
        var1.printtab("quartile ");
        var1.printtab("kurtosis ");
        var1.println("excess ");
        var1.printtab("       ");
        var1.printtab("       ");
        var1.printtab("       ");
        var1.printtab("       ");
        var1.printtab("skewness");
        var1.printtab("skewness");
        var1.printtab("skewness");
        var1.printtab("        ");
        var1.println("kurtosis");

        for(var2 = 0; var2 < this.nGroups; ++var2) {
            var1.printtab(this.groupNames[var2]);
            var1.printtab(Fmath.truncate(this.groupMinimum[var2], this.trunc));
            var1.printtab(Fmath.truncate(this.groupMedians[var2], this.trunc));
            var1.printtab(Fmath.truncate(this.groupMaximum[var2], this.trunc));
            var1.printtab(Fmath.truncate(this.groupMomentSkewness[var2], this.trunc));
            var1.printtab(Fmath.truncate(this.groupMedianSkewness[var2], this.trunc));
            var1.printtab(Fmath.truncate(this.groupQuartileSkewness[var2], this.trunc));
            var1.printtab(Fmath.truncate(this.groupKurtosis[var2], this.trunc));
            var1.println(Fmath.truncate(this.groupExcessKurtosis[var2], this.trunc));
        }

        var1.println(" ");
        var1.printtab("Total");
        var1.printtab(Fmath.truncate(this.totalMinimum, this.trunc));
        var1.printtab(Fmath.truncate(this.totalMedian, this.trunc));
        var1.printtab(Fmath.truncate(this.totalMaximum, this.trunc));
        var1.printtab(Fmath.truncate(this.totalMomentSkewness, this.trunc));
        var1.printtab(Fmath.truncate(this.totalMedianSkewness, this.trunc));
        var1.printtab(Fmath.truncate(this.totalQuartileSkewness, this.trunc));
        var1.printtab(Fmath.truncate(this.totalKurtosis, this.trunc));
        var1.printtab(Fmath.truncate(this.totalExcessKurtosis, this.trunc));
        var1.println(Fmath.truncate(this.totalProbPlotR, this.trunc));
        var1.println();
        var1.println("Gaussian Probability Plot");
        var1.printtab("       ");
        var1.printtab("Correlation");
        var1.printtab("Gradient");
        var1.printtab("Intercept");
        var1.printtab("mu");
        var1.println("sigma");
        var1.printtab("       ");
        var1.printtab("coefficient");
        var1.printtab("       ");
        var1.printtab("       ");
        var1.printtab("       ");
        var1.println("     ");

        for(var2 = 0; var2 < this.nGroups; ++var2) {
            var1.printtab(this.groupNames[var2]);
            var1.printtab(Fmath.truncate(this.groupProbPlotR[var2], this.trunc));
            var1.printtab(Fmath.truncate(this.groupProbPlotGradient[var2], this.trunc));
            var1.printtab(Fmath.truncate(this.groupProbPlotIntercept[var2], this.trunc));
            var1.printtab(Fmath.truncate(this.groupProbPlotMu[var2], this.trunc));
            var1.println(Fmath.truncate(this.groupProbPlotSigma[var2], this.trunc));
        }

        var1.println(" ");
        var1.printtab("total");
        var1.printtab(Fmath.truncate(this.totalProbPlotR, this.trunc));
        var1.printtab(Fmath.truncate(this.totalProbPlotGradient, this.trunc));
        var1.printtab(Fmath.truncate(this.totalProbPlotIntercept, this.trunc));
        var1.printtab(Fmath.truncate(this.totalProbPlotMu, this.trunc));
        var1.println(Fmath.truncate(this.totalProbPlotSigma, this.trunc));
        if (this.nGroups > 2 && this.comparison) {
            var1.println(" ");
            var1.println(" ");
            var1.println("COMPARISON OF PAIRS OF GROUPS: One-Way Analysis of Variance");
            var2 = 0;

            for(int var3 = 0; var3 < this.nGroups - 1; ++var3) {
                for(int var4 = var3 + 1; var4 < this.nGroups; ++var4) {
                    if (this.compGroups[var3] && this.compGroups[var4]) {
                        var1.println("Group" + (var3 + 1) + " and Group" + (var4 + 1));
                        var1.printtab(" ");
                        var1.printtab(" ");
                        var1.printtab("  ");
                        var1.printtab(" ");
                        var1.printtab(" ");
                        var1.printtab("F-ratio at");
                        var1.println(" ");
                        var1.printtab(" ");
                        var1.printtab("Sum of");
                        var1.printtab("Degrees of ");
                        var1.printtab("Mean ");
                        var1.printtab(" ");
                        var1.printtab(this.criticalSignificanceF + " critical");
                        var1.println(" ");
                        var1.printtab(" ");
                        var1.printtab("squares");
                        var1.printtab("freedom ");
                        var1.printtab("square ");
                        var1.printtab("F-ratio ");
                        var1.printtab("level");
                        var1.println("Significance");
                        var1.printtab("Between groups");
                        var1.printtab(Fmath.truncate(this.ssBetweenG[var2], this.trunc));
                        var1.printtab(this.dofBetweenG[var2]);
                        var1.printtab(Fmath.truncate(this.meanSquareBetweenG[var2], this.trunc));
                        var1.printtab(Fmath.truncate(this.fRatioG[var2], this.trunc));
                        var1.printtab(Fmath.truncate(this.criticalFratioG[var2], this.trunc));
                        var1.println(Fmath.truncate(this.fRatioPG[var2], this.trunc));
                        var1.printtab("Within groups");
                        var1.printtab(Fmath.truncate(this.ssWithinG[var2], this.trunc));
                        var1.printtab(this.dofWithinG[var2]);
                        var1.println(Fmath.truncate(this.meanSquareWithinG[var2], this.trunc));
                        var1.printtab("Total");
                        var1.printtab(Fmath.truncate(this.ssTotalG[var2], this.trunc));
                        var1.printtab(this.dofTotalG[var2], this.fieldD);
                        var1.println(Fmath.truncate(this.meanSquareTotalG[var2], this.trunc));
                        ++var2;
                        var1.println();
                        var1.println();
                    }
                }
            }
        }

        var1.close();
    }

    public void numberOfDecimalPlaces(int var1) {
        this.trunc = var1;
    }

    public void numberOfDecimalPlacesAll(int var1) {
        this.trunc = var1;
        this.truncAll = true;
    }
}
