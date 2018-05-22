//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.physchem;

import com.flanagan.analysis.Regression;
import com.flanagan.analysis.Stat;
import com.flanagan.interpolation.CubicInterpolation;
import com.flanagan.interpolation.CubicSpline;
import com.flanagan.interpolation.LinearInterpolation;
import com.flanagan.io.Db;
import com.flanagan.io.FileChooser;
import com.flanagan.io.FileInput;
import com.flanagan.io.FileOutput;
import com.flanagan.math.ArrayMaths;
import com.flanagan.math.Conv;
import com.flanagan.math.Fmath;
import com.flanagan.plot.PlotGraph;
import java.awt.Component;
import java.io.File;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.JOptionPane;

public class ImmunoAssay extends Regression {
    private double[] analyteConcns = null;
    private double[] log10AnalyteConcns = null;
    private double[] logeAnalyteConcns = null;
    private boolean analyteEntered = false;
    private int analyteConcnFlag = 0;
    private int nAnalyteConcns = 0;
    private double[] responses = null;
    private double[] log10Responses = null;
    private double[] logeResponses = null;
    private boolean responsesEntered = false;
    private int nResponses = 0;
    private int responsesFlag = 0;
    private boolean responsesPlot = true;
    private double responsesMax = 0.0D;
    private double responsesMin = 0.0D;
    private double responsesRange = 0.0D;
    private int nWeights = 0;
    private double weightsMean = 0.0D;
    private double weightsSD = 0.0D;
    private int weightOption = 0;
    private boolean setDataOneDone = false;
    private double[] interpolationConcns = null;
    private double[] calculatedResponses = null;
    private String[] propagatedErrors = null;
    private int nInterp = 1000;
    private CubicSpline interp = null;
    private LinearInterpolation linterp = null;
    private CubicSpline errorp = null;
    private CubicSpline cubicSpline = null;
    private CubicInterpolation cubicInterpolation = null;
    private LinearInterpolation linearInterpolation = null;
    private String titleZero = null;
    private String titleOne = null;
    private String filename = "ImmunoAssayOutput.txt";
    private String dataFilename = null;
    private boolean dataRead = false;
    private double interpAnalyteStart = 0.0D;
    private double interpAnalyteEnd = 0.0D;
    private double interpResponseStart = 0.0D;
    private double interpResponseEnd = 0.0D;
    private int interpStartIndex = 0;
    private int interpEndIndex = 0;
    private boolean ambigCheck = false;
    private boolean curveDirection = true;
    private double workingResponseMin = 0.0D;
    private double workingResponseMax = 0.0D;
    private double minimumAerror = 0.0D;
    private double maximumAerror = 0.0D;
    private double meanAerror = 0.0D;
    private double sdAerror = 0.0D;
    private int nTurningPoints = 0;
    private int[] turnIndices = null;
    private int nWorking = 0;
    private int polyDegree = 0;
    private int bestPolyDegree = 0;
    private int polyNterms = 0;
    private String compFilename = "ImmunoAssayComparison.txt";
    private int resultFlag = -1;
    private double significance = 0.05D;
    private double sampleResponse = 0.0D;
    private double sampleConcn = 0.0D;
    private double sampleError = 0.0D;
    private int nPlot = 0;
    private int plotOptions = 0;
    private boolean suppressPlot = false;
    private int nMethods = 15;
    private String[] methodNames;
    private int[] methodIndices;
    private int methodUsed;
    private boolean sampleErrorFlag;
    private boolean amershamFlag1;
    private double bottom;
    private double top;
    private boolean compWindow;
    private String[] outliers;
    private double residualsMean;
    private double residualsSD;
    private double confidenceLevel;
    private double[] critSize;
    private double[] critValuesOne;
    private double[] critValuesFive;
    private CubicSpline critValues;
    private double anscombeC;
    private boolean outlierFlag;
    private boolean degSet;
    private boolean nTermsSet;
    private boolean fourBotTopSet;
    private boolean fiveBotTopSet;

    public ImmunoAssay() {
        this.methodNames = new String[this.nMethods];
        this.methodIndices = new int[this.nMethods];
        this.methodUsed = 8;
        this.sampleErrorFlag = true;
        this.amershamFlag1 = true;
        this.bottom = 0.0D;
        this.top = 0.0D;
        this.compWindow = false;
        this.outliers = null;
        this.residualsMean = 0.0D;
        this.residualsSD = 0.0D;
        this.confidenceLevel = 0.95D;
        this.critSize = new double[]{3.0D, 4.0D, 5.0D, 6.0D, 7.0D, 8.0D, 9.0D, 10.0D, 12.0D, 14.0D, 15.0D, 16.0D, 18.0D, 20.0D, 30.0D, 40.0D, 50.0D, 60.0D, 100.0D, 120.0D};
        this.critValuesOne = new double[]{1.15D, 1.49D, 1.75D, 1.94D, 2.1D, 2.22D, 2.32D, 2.41D, 2.55D, 2.66D, 2.71D, 2.75D, 2.82D, 2.88D, 3.1D, 3.24D, 3.34D, 3.41D, 3.6D, 3.66D};
        this.critValuesFive = new double[]{1.15D, 1.46D, 1.67D, 1.82D, 1.94D, 2.03D, 2.11D, 2.18D, 2.29D, 2.37D, 2.41D, 2.44D, 2.5D, 2.56D, 2.74D, 2.87D, 2.96D, 3.03D, 3.21D, 3.27D};
        this.critValues = null;
        this.anscombeC = 0.0D;
        this.outlierFlag = false;
        this.degSet = false;
        this.nTermsSet = false;
        this.fourBotTopSet = false;
        this.fiveBotTopSet = false;
        this.titleZero = "Program ImmunoAssay";
        super.suppressErrorMessages = true;
        super.trueErrors = false;
        this.methodList();
    }

    public ImmunoAssay(String title) {
        this.methodNames = new String[this.nMethods];
        this.methodIndices = new int[this.nMethods];
        this.methodUsed = 8;
        this.sampleErrorFlag = true;
        this.amershamFlag1 = true;
        this.bottom = 0.0D;
        this.top = 0.0D;
        this.compWindow = false;
        this.outliers = null;
        this.residualsMean = 0.0D;
        this.residualsSD = 0.0D;
        this.confidenceLevel = 0.95D;
        this.critSize = new double[]{3.0D, 4.0D, 5.0D, 6.0D, 7.0D, 8.0D, 9.0D, 10.0D, 12.0D, 14.0D, 15.0D, 16.0D, 18.0D, 20.0D, 30.0D, 40.0D, 50.0D, 60.0D, 100.0D, 120.0D};
        this.critValuesOne = new double[]{1.15D, 1.49D, 1.75D, 1.94D, 2.1D, 2.22D, 2.32D, 2.41D, 2.55D, 2.66D, 2.71D, 2.75D, 2.82D, 2.88D, 3.1D, 3.24D, 3.34D, 3.41D, 3.6D, 3.66D};
        this.critValuesFive = new double[]{1.15D, 1.46D, 1.67D, 1.82D, 1.94D, 2.03D, 2.11D, 2.18D, 2.29D, 2.37D, 2.41D, 2.44D, 2.5D, 2.56D, 2.74D, 2.87D, 2.96D, 3.03D, 3.21D, 3.27D};
        this.critValues = null;
        this.anscombeC = 0.0D;
        this.outlierFlag = false;
        this.degSet = false;
        this.nTermsSet = false;
        this.fourBotTopSet = false;
        this.fiveBotTopSet = false;
        this.titleZero = "Program ImmunoAssay: " + title;
        super.suppressErrorMessages = true;
        super.trueErrors = false;
        this.methodList();
    }

    public ImmunoAssay(double[] concentrations, double[] values) {
        this.methodNames = new String[this.nMethods];
        this.methodIndices = new int[this.nMethods];
        this.methodUsed = 8;
        this.sampleErrorFlag = true;
        this.amershamFlag1 = true;
        this.bottom = 0.0D;
        this.top = 0.0D;
        this.compWindow = false;
        this.outliers = null;
        this.residualsMean = 0.0D;
        this.residualsSD = 0.0D;
        this.confidenceLevel = 0.95D;
        this.critSize = new double[]{3.0D, 4.0D, 5.0D, 6.0D, 7.0D, 8.0D, 9.0D, 10.0D, 12.0D, 14.0D, 15.0D, 16.0D, 18.0D, 20.0D, 30.0D, 40.0D, 50.0D, 60.0D, 100.0D, 120.0D};
        this.critValuesOne = new double[]{1.15D, 1.49D, 1.75D, 1.94D, 2.1D, 2.22D, 2.32D, 2.41D, 2.55D, 2.66D, 2.71D, 2.75D, 2.82D, 2.88D, 3.1D, 3.24D, 3.34D, 3.41D, 3.6D, 3.66D};
        this.critValuesFive = new double[]{1.15D, 1.46D, 1.67D, 1.82D, 1.94D, 2.03D, 2.11D, 2.18D, 2.29D, 2.37D, 2.41D, 2.44D, 2.5D, 2.56D, 2.74D, 2.87D, 2.96D, 3.03D, 3.21D, 3.27D};
        this.critValues = null;
        this.anscombeC = 0.0D;
        this.outlierFlag = false;
        this.degSet = false;
        this.nTermsSet = false;
        this.fourBotTopSet = false;
        this.fiveBotTopSet = false;
        this.titleZero = "Program ImmunoAssay";
        this.nAnalyteConcns = concentrations.length;
        this.analyteConcns = Conv.copy(concentrations);
        this.analyteConcnFlag = 0;
        this.analyteEntered = true;
        this.nResponses = values.length;
        this.responses = Conv.copy(values);
        this.responsesFlag = 0;
        this.responsesEntered = true;
        super.xErrorsEntered = false;
        super.yErrorsEntered = false;
        super.suppressErrorMessages = true;
        super.trueErrors = false;
        this.methodList();
    }

    public ImmunoAssay(double[] concentrations, double[] values, double[] weights) {
        this.methodNames = new String[this.nMethods];
        this.methodIndices = new int[this.nMethods];
        this.methodUsed = 8;
        this.sampleErrorFlag = true;
        this.amershamFlag1 = true;
        this.bottom = 0.0D;
        this.top = 0.0D;
        this.compWindow = false;
        this.outliers = null;
        this.residualsMean = 0.0D;
        this.residualsSD = 0.0D;
        this.confidenceLevel = 0.95D;
        this.critSize = new double[]{3.0D, 4.0D, 5.0D, 6.0D, 7.0D, 8.0D, 9.0D, 10.0D, 12.0D, 14.0D, 15.0D, 16.0D, 18.0D, 20.0D, 30.0D, 40.0D, 50.0D, 60.0D, 100.0D, 120.0D};
        this.critValuesOne = new double[]{1.15D, 1.49D, 1.75D, 1.94D, 2.1D, 2.22D, 2.32D, 2.41D, 2.55D, 2.66D, 2.71D, 2.75D, 2.82D, 2.88D, 3.1D, 3.24D, 3.34D, 3.41D, 3.6D, 3.66D};
        this.critValuesFive = new double[]{1.15D, 1.46D, 1.67D, 1.82D, 1.94D, 2.03D, 2.11D, 2.18D, 2.29D, 2.37D, 2.41D, 2.44D, 2.5D, 2.56D, 2.74D, 2.87D, 2.96D, 3.03D, 3.21D, 3.27D};
        this.critValues = null;
        this.anscombeC = 0.0D;
        this.outlierFlag = false;
        this.degSet = false;
        this.nTermsSet = false;
        this.fourBotTopSet = false;
        this.fiveBotTopSet = false;
        this.titleZero = "Program ImmunoAssay";
        this.nAnalyteConcns = concentrations.length;
        this.analyteConcns = Conv.copy(concentrations);
        this.analyteConcnFlag = 0;
        this.analyteEntered = true;
        this.nResponses = values.length;
        this.responses = Conv.copy(values);
        this.responsesFlag = 0;
        this.responsesEntered = true;
        this.nWeights = weights.length;
        super.yErrors = super.checkForZeroWeights(weights);
        if (super.weightOpt) {
            super.weightFlag = 1;
        }

        super.xErrorsEntered = false;
        super.yErrorsEntered = true;
        super.suppressErrorMessages = true;
        super.trueErrors = false;
        this.methodList();
    }

    public ImmunoAssay(double[] concentrations, double[] values, double[] xErrors, double[] yErrors) {
        this.methodNames = new String[this.nMethods];
        this.methodIndices = new int[this.nMethods];
        this.methodUsed = 8;
        this.sampleErrorFlag = true;
        this.amershamFlag1 = true;
        this.bottom = 0.0D;
        this.top = 0.0D;
        this.compWindow = false;
        this.outliers = null;
        this.residualsMean = 0.0D;
        this.residualsSD = 0.0D;
        this.confidenceLevel = 0.95D;
        this.critSize = new double[]{3.0D, 4.0D, 5.0D, 6.0D, 7.0D, 8.0D, 9.0D, 10.0D, 12.0D, 14.0D, 15.0D, 16.0D, 18.0D, 20.0D, 30.0D, 40.0D, 50.0D, 60.0D, 100.0D, 120.0D};
        this.critValuesOne = new double[]{1.15D, 1.49D, 1.75D, 1.94D, 2.1D, 2.22D, 2.32D, 2.41D, 2.55D, 2.66D, 2.71D, 2.75D, 2.82D, 2.88D, 3.1D, 3.24D, 3.34D, 3.41D, 3.6D, 3.66D};
        this.critValuesFive = new double[]{1.15D, 1.46D, 1.67D, 1.82D, 1.94D, 2.03D, 2.11D, 2.18D, 2.29D, 2.37D, 2.41D, 2.44D, 2.5D, 2.56D, 2.74D, 2.87D, 2.96D, 3.03D, 3.21D, 3.27D};
        this.critValues = null;
        this.anscombeC = 0.0D;
        this.outlierFlag = false;
        this.degSet = false;
        this.nTermsSet = false;
        this.fourBotTopSet = false;
        this.fiveBotTopSet = false;
        this.titleZero = "Program ImmunoAssay";
        this.nAnalyteConcns = concentrations.length;
        this.analyteConcns = Conv.copy(concentrations);
        this.analyteConcnFlag = 0;
        this.analyteEntered = true;
        this.nResponses = values.length;
        this.responses = Conv.copy(values);
        this.responsesFlag = 0;
        this.responsesEntered = true;
        this.nWeights = yErrors.length;
        super.yErrors = yErrors;
        double[][] xErrorsArray = new double[1][this.nWeights];
        xErrorsArray[0] = xErrors;
        super.xErrors = xErrorsArray;
        super.jointZeroCheck(xErrorsArray, yErrors);
        if (super.weightOpt) {
            super.weightFlag = 1;
        }

        super.xErrorsEntered = true;
        super.yErrorsEntered = true;
        super.weightOpt = true;
        super.weightFlag = 1;
        super.suppressErrorMessages = true;
        super.trueErrors = true;
        this.methodList();
    }

    public ImmunoAssay(double[] concentrations, double[] values, double[] weights, String title) {
        this.methodNames = new String[this.nMethods];
        this.methodIndices = new int[this.nMethods];
        this.methodUsed = 8;
        this.sampleErrorFlag = true;
        this.amershamFlag1 = true;
        this.bottom = 0.0D;
        this.top = 0.0D;
        this.compWindow = false;
        this.outliers = null;
        this.residualsMean = 0.0D;
        this.residualsSD = 0.0D;
        this.confidenceLevel = 0.95D;
        this.critSize = new double[]{3.0D, 4.0D, 5.0D, 6.0D, 7.0D, 8.0D, 9.0D, 10.0D, 12.0D, 14.0D, 15.0D, 16.0D, 18.0D, 20.0D, 30.0D, 40.0D, 50.0D, 60.0D, 100.0D, 120.0D};
        this.critValuesOne = new double[]{1.15D, 1.49D, 1.75D, 1.94D, 2.1D, 2.22D, 2.32D, 2.41D, 2.55D, 2.66D, 2.71D, 2.75D, 2.82D, 2.88D, 3.1D, 3.24D, 3.34D, 3.41D, 3.6D, 3.66D};
        this.critValuesFive = new double[]{1.15D, 1.46D, 1.67D, 1.82D, 1.94D, 2.03D, 2.11D, 2.18D, 2.29D, 2.37D, 2.41D, 2.44D, 2.5D, 2.56D, 2.74D, 2.87D, 2.96D, 3.03D, 3.21D, 3.27D};
        this.critValues = null;
        this.anscombeC = 0.0D;
        this.outlierFlag = false;
        this.degSet = false;
        this.nTermsSet = false;
        this.fourBotTopSet = false;
        this.fiveBotTopSet = false;
        this.titleZero = "Program ImmunoAssay: " + title;
        this.nAnalyteConcns = concentrations.length;
        this.analyteConcns = Conv.copy(concentrations);
        this.analyteConcnFlag = 0;
        this.analyteEntered = true;
        this.nResponses = values.length;
        this.responses = Conv.copy(values);
        this.responsesFlag = 0;
        this.responsesEntered = true;
        this.nWeights = weights.length;
        super.yErrors = super.checkForZeroWeights(weights);
        if (super.weightOpt) {
            super.weightFlag = 1;
        }

        super.xErrorsEntered = false;
        super.yErrorsEntered = true;
        super.suppressErrorMessages = true;
        super.trueErrors = false;
        this.methodList();
    }

    public ImmunoAssay(double[] concentrations, double[] values, String title) {
        this.methodNames = new String[this.nMethods];
        this.methodIndices = new int[this.nMethods];
        this.methodUsed = 8;
        this.sampleErrorFlag = true;
        this.amershamFlag1 = true;
        this.bottom = 0.0D;
        this.top = 0.0D;
        this.compWindow = false;
        this.outliers = null;
        this.residualsMean = 0.0D;
        this.residualsSD = 0.0D;
        this.confidenceLevel = 0.95D;
        this.critSize = new double[]{3.0D, 4.0D, 5.0D, 6.0D, 7.0D, 8.0D, 9.0D, 10.0D, 12.0D, 14.0D, 15.0D, 16.0D, 18.0D, 20.0D, 30.0D, 40.0D, 50.0D, 60.0D, 100.0D, 120.0D};
        this.critValuesOne = new double[]{1.15D, 1.49D, 1.75D, 1.94D, 2.1D, 2.22D, 2.32D, 2.41D, 2.55D, 2.66D, 2.71D, 2.75D, 2.82D, 2.88D, 3.1D, 3.24D, 3.34D, 3.41D, 3.6D, 3.66D};
        this.critValuesFive = new double[]{1.15D, 1.46D, 1.67D, 1.82D, 1.94D, 2.03D, 2.11D, 2.18D, 2.29D, 2.37D, 2.41D, 2.44D, 2.5D, 2.56D, 2.74D, 2.87D, 2.96D, 3.03D, 3.21D, 3.27D};
        this.critValues = null;
        this.anscombeC = 0.0D;
        this.outlierFlag = false;
        this.degSet = false;
        this.nTermsSet = false;
        this.fourBotTopSet = false;
        this.fiveBotTopSet = false;
        this.titleZero = "Program ImmunoAssay: " + title;
        this.nAnalyteConcns = concentrations.length;
        this.analyteConcns = Conv.copy(concentrations);
        this.analyteConcnFlag = 0;
        this.analyteEntered = true;
        this.nResponses = values.length;
        this.responses = Conv.copy(values);
        this.responsesFlag = 0;
        super.xErrorsEntered = false;
        super.yErrorsEntered = false;
        this.responsesEntered = true;
        super.suppressErrorMessages = true;
        super.trueErrors = false;
        this.methodList();
    }

    public ImmunoAssay(double[] concentrations, double[] values, double[] xErrors, double[] yErrors, String title) {
        this.methodNames = new String[this.nMethods];
        this.methodIndices = new int[this.nMethods];
        this.methodUsed = 8;
        this.sampleErrorFlag = true;
        this.amershamFlag1 = true;
        this.bottom = 0.0D;
        this.top = 0.0D;
        this.compWindow = false;
        this.outliers = null;
        this.residualsMean = 0.0D;
        this.residualsSD = 0.0D;
        this.confidenceLevel = 0.95D;
        this.critSize = new double[]{3.0D, 4.0D, 5.0D, 6.0D, 7.0D, 8.0D, 9.0D, 10.0D, 12.0D, 14.0D, 15.0D, 16.0D, 18.0D, 20.0D, 30.0D, 40.0D, 50.0D, 60.0D, 100.0D, 120.0D};
        this.critValuesOne = new double[]{1.15D, 1.49D, 1.75D, 1.94D, 2.1D, 2.22D, 2.32D, 2.41D, 2.55D, 2.66D, 2.71D, 2.75D, 2.82D, 2.88D, 3.1D, 3.24D, 3.34D, 3.41D, 3.6D, 3.66D};
        this.critValuesFive = new double[]{1.15D, 1.46D, 1.67D, 1.82D, 1.94D, 2.03D, 2.11D, 2.18D, 2.29D, 2.37D, 2.41D, 2.44D, 2.5D, 2.56D, 2.74D, 2.87D, 2.96D, 3.03D, 3.21D, 3.27D};
        this.critValues = null;
        this.anscombeC = 0.0D;
        this.outlierFlag = false;
        this.degSet = false;
        this.nTermsSet = false;
        this.fourBotTopSet = false;
        this.fiveBotTopSet = false;
        this.titleZero = "Program ImmunoAssay: " + title;
        this.nAnalyteConcns = concentrations.length;
        this.analyteConcns = Conv.copy(concentrations);
        this.analyteEntered = true;
        this.analyteConcnFlag = 0;
        this.nResponses = values.length;
        this.responses = Conv.copy(values);
        this.responsesFlag = 0;
        this.responsesEntered = true;
        this.nWeights = yErrors.length;
        super.yErrors = yErrors;
        double[][] var6 = new double[1][this.nWeights];
        var6[0] = xErrors;
        super.xErrors = var6;
        super.jointZeroCheck(var6, yErrors);
        super.xErrorsEntered = true;
        super.yErrorsEntered = true;
        super.weightOpt = true;
        super.weightFlag = 1;
        super.suppressErrorMessages = true;
        super.trueErrors = true;
        this.methodList();
    }

    private void methodList() {
        this.methodNames[0] = "CubicSpline";
        this.methodIndices[0] = 0;
        this.methodNames[1] = "五参数拟合";
        this.methodIndices[1] = 13;
        this.methodNames[2] = "五参数拟合 (top and bottom fixed)";
        this.methodIndices[2] = 6;
        this.methodNames[3] = "四参数拟合";
        this.methodIndices[3] = 5;
        this.methodNames[4] = "四参数拟合 (top and bottom fixed)";
        this.methodIndices[4] = 7;
        this.methodNames[5] = "Best fit polynomial";
        this.methodIndices[5] = 8;
        this.methodNames[6] = "Polynomial of user supplied degree ";
        this.methodIndices[6] = 9;
        this.methodNames[7] = "Non-integer polynomial";
        this.methodIndices[7] = 3;
        this.methodNames[8] = "Sigmoid threshold function";
        this.methodIndices[8] = 1;
        this.methodNames[9] = "Sips sigmoid function";
        this.methodIndices[9] = 10;
        this.methodNames[10] = "Shifted rectangular hyperbola";
        this.methodIndices[10] = 11;
        this.methodNames[11] = "Rectangular hyperbola";
        this.methodIndices[11] = 12;
        this.methodNames[12] = "Amersham equation";
        this.methodIndices[12] = 4;
        this.methodNames[13] = "Cubic interpolation";
        this.methodIndices[13] = 2;
        this.methodNames[14] = "Linear interpolation";
        this.methodIndices[14] = 14;
    }

    public void enterAnalyteConcns(double[] concentrations) {
        this.setDataOneDone = false;
        this.nAnalyteConcns = concentrations.length;
        this.analyteConcns = Conv.copy(concentrations);
        this.analyteConcnFlag = 0;
        this.analyteEntered = true;
    }

    public void enterAnalyteConcnsAsLog10(double[] concentrations) {
        this.setDataOneDone = false;
        this.nAnalyteConcns = concentrations.length;
        this.log10AnalyteConcns = Conv.copy(concentrations);
        this.analyteConcns = this.antiLog10(concentrations);
        this.analyteConcnFlag = 1;
        this.analyteEntered = true;
    }

    public void enterAnalyteConcnsAsLogE(double[] concentrations) {
        this.setDataOneDone = false;
        this.nAnalyteConcns = concentrations.length;
        this.logeAnalyteConcns = Conv.copy(concentrations);
        this.analyteConcns = this.antiLoge(concentrations);
        this.analyteConcnFlag = 2;
        this.analyteEntered = true;
    }

    public void enterResponses(double[] values) {
        this.setDataOneDone = false;
        this.nResponses = values.length;
        this.responses = Conv.copy(values);
        this.responsesFlag = 0;
        this.responsesEntered = true;
    }

    public void enterResponsesAsLog10(double[] values) {
        this.setDataOneDone = false;
        this.nResponses = values.length;
        this.log10Responses = Conv.copy(values);
        this.responses = this.antiLog10(values);
        this.responsesFlag = 1;
        this.responsesEntered = true;
    }

    public void enterResponsesAsLogE(double[] values) {
        this.setDataOneDone = false;
        this.nResponses = values.length;
        this.logeResponses = Conv.copy(values);
        this.responses = this.antiLoge(values);
        this.responsesFlag = 2;
        this.responsesEntered = true;
    }

    public void enterWeights(double[] weights) {
        this.setDataOneDone = false;
        this.nWeights = weights.length;
        super.yErrors = super.checkForZeroWeights(weights);
        super.yErrorsEntered = true;
        if (super.weightOpt) {
            super.weightFlag = 1;
        }

    }

    public void enterWeightsAslog10(double[] weights) {
        this.setDataOneDone = false;
        this.nWeights = weights.length;
        super.yErrors = this.antiLog10(super.checkForZeroWeights(weights));
        if (super.weightOpt) {
            super.weightFlag = 1;
        }

        super.yErrorsEntered = true;
    }

    public void enterWeightsAslogE(double[] weights) {
        this.setDataOneDone = false;
        this.nWeights = weights.length;
        super.yErrors = this.antiLoge(super.checkForZeroWeights(weights));
        if (super.weightOpt) {
            super.weightFlag = 1;
        }

        super.yErrorsEntered = true;
    }

    public void enterMultiplicativeWeights(double[] weights) {
        this.setDataOneDone = false;
        this.nWeights = weights.length;
        super.yErrors = Conv.copy(super.checkForZeroWeights(weights));

        for(int var2 = 0; var2 < this.nWeights; ++var2) {
            super.yErrors[var2] = 1.0D / Math.abs(super.yErrors[var2]);
        }

        if (super.weightOpt) {
            super.weightFlag = 1;
        }

        super.yErrorsEntered = true;
    }

    public void enterMultiplicativeWeightsAsLog10(double[] weights) {
        this.setDataOneDone = false;
        this.nWeights = weights.length;
        super.yErrors = this.antiLog10(super.checkForZeroWeights(Conv.copy(weights)));

        for(int var2 = 0; var2 < this.nWeights; ++var2) {
            super.yErrors[var2] = 1.0D / Math.abs(super.yErrors[var2]);
        }

        if (super.weightOpt) {
            super.weightFlag = 1;
        }

        super.yErrorsEntered = true;
    }

    public void enterMultiplicativeWeightsAsLogE(double[] weights) {
        this.setDataOneDone = false;
        this.nWeights = weights.length;
        super.yErrors = this.antiLoge(super.checkForZeroWeights(Conv.copy(weights)));

        for(int var2 = 0; var2 < this.nWeights; ++var2) {
            super.yErrors[var2] = 1.0D / Math.abs(super.yErrors[var2]);
        }

        if (super.weightOpt) {
            super.weightFlag = 1;
        }

        super.yErrorsEntered = true;
    }

    public void setWeightsAsResponses() {
        super.yErrorsEntered = false;
        this.weightOption = 1;
        if (this.nResponses > 0) {
            super.yErrors = new double[this.nResponses];

            for(int i = 0; i < this.nResponses; ++i) {
                super.yErrors[i] = Math.abs(this.responses[i]);
                super.yErrorsEntered = true;
            }

            super.yErrors = super.checkForZeroWeights(super.yErrors);
            if (super.weightOpt) {
                super.weightFlag = 1;
            }

            super.yErrorsEntered = true;
        }

    }

    public void setWeightsAsSqrtResponses() {
        super.yErrorsEntered = false;
        this.weightOption = 2;
        if (this.nResponses > 0) {
            super.yErrors = new double[this.nResponses];

            for(int i = 0; i < this.nResponses; ++i) {
                super.yErrors[i] = Math.sqrt(Math.abs(this.responses[i]));
            }

            super.yErrors = super.checkForZeroWeights(super.yErrors);
            if (super.weightOpt) {
                super.weightFlag = 1;
            }

            super.yErrorsEntered = true;
        }

    }

    public void enterTitle(String title) {
        this.titleZero = title;
    }

    public void readFromFile() {
        this.setDataOneDone = false;
        FileChooser fileChooser = new FileChooser();
        this.dataFilename = fileChooser.selectFile();
        this.read(fileChooser);
    }

    public void readFromFile(String filename) {
        this.setDataOneDone = false;
        this.dataFilename = filename;
        FileInput var2 = new FileInput(filename);
        this.read(var2);
    }

    private int separatorPosition(String text) {
        text = text.trim();
        // 58 :
        // 59 ;
        // 44 ,
        // 9 水平制表符
        // 32 空格
        int position = text.indexOf(58);
        if (position == -1) {
            position = text.indexOf(59);
        }

        if (position == -1) {
            position = text.indexOf(44);
        }

        if (position == -1) {
            position = text.indexOf(9);
        }

        if (position == -1) {
            position = text.indexOf(32);
        }

        return position;
    }

    private void read(FileInput fileInput) {
        this.nAnalyteConcns = fileInput.numberOfLines() - 1;
        this.titleZero = fileInput.readLine();
        this.nResponses = this.nAnalyteConcns;
        this.analyteConcns = new double[this.nAnalyteConcns];
        this.responses = new double[this.nAnalyteConcns];
        super.yErrors = new double[this.nAnalyteConcns];
        int count = 0;
        String readText = null;
        String subReadText = null;
//        boolean var5 = false;
//        boolean var6 = true;
//        boolean var7 = true;
//        boolean var8 = true;

        for(int i = 0; i < this.nAnalyteConcns; ++i) {
            readText = fileInput.readLine().trim();
            int pos1 = this.separatorPosition(readText);
            if (pos1 == -1) {
                throw new IllegalArgumentException("Input file line " + (i + 1) + ": analyte concentration and response value required for all data points");
            }

            subReadText = readText.substring(0, pos1);
            this.analyteConcns[i] = Double.parseDouble(subReadText);
            readText = readText.substring(pos1 + 1).trim();
            int length = readText.length();
            if (length < 1) {
                throw new IllegalArgumentException("Input file line " + (i + 1) + ": response value required for all data points");
            }

            int pos2 = this.separatorPosition(readText);
            if (pos2 == -1) {
                subReadText = readText;
            } else {
                subReadText = readText.substring(0, pos2);
            }

            this.responses[i] = Double.parseDouble(subReadText);
            if (pos2 != -1) {
                readText = readText.substring(pos2 + 1).trim();
                length = readText.length();
                if (length > 0) {
                    int pos3 = this.separatorPosition(readText);
                    if (pos3 == -1) {
                        subReadText = readText.trim();
                    } else {
                        subReadText = readText.substring(0, pos3).trim();
                    }

                    super.yErrors[i] = Double.parseDouble(subReadText);
                    if (super.yErrors[i] == 1.0D) {
                        ++count;
                    }
                }
            }
        }

        this.analyteConcnFlag = 0;
        this.analyteEntered = true;
        this.responsesEntered = true;
        if (count != this.nAnalyteConcns) {
            this.nWeights = this.nAnalyteConcns;
            super.yErrors = super.checkForZeroWeights(super.yErrors);
            if (super.weightOpt) {
                super.weightFlag = 1;
            }

            super.yErrorsEntered = true;
        }

        this.dataRead = true;
    }

    public double[] getAnalyteConcns() {
        return this.analyteConcns;
    }

    public double[] getResponses() {
        return this.responses;
    }

    public String getTitle() {
        return this.titleZero;
    }

    public void cubicSpline() {
        if (this.nAnalyteConcns < 3) {
            throw new IllegalArgumentException("Method cubicSpline requres at least 3 data points; only " + this.nAnalyteConcns + " were supplied");
        } else {
            this.methodUsed = 0;
            this.sampleErrorFlag = false;
            this.titleOne = "Cubic spline ";
            if (!this.setDataOneDone) {
                this.setDataOne();
            }

            this.cubicSpline = new CubicSpline(this.analyteConcns, this.responses);

            for(int var1 = 0; var1 < this.nInterp; ++var1) {
                this.calculatedResponses[var1] = this.cubicSpline.interpolate(this.interpolationConcns[var1]);
            }

            if (!this.suppressPlot) {
                this.plot();
            }

            this.curveCheck(this.methodIndices[this.methodUsed]);
        }
    }

    public void cubicInterpolation() {
        if (this.nAnalyteConcns < 2) {
            throw new IllegalArgumentException("Method cubicInterpolation requres at least 2 data points; only " + this.nAnalyteConcns + " were supplied");
        } else {
            this.methodUsed = 1;
            this.sampleErrorFlag = false;
            this.titleOne = "Cubic interpolation ";
            if (!this.setDataOneDone) {
                this.setDataOne();
            }

            this.cubicInterpolation = new CubicInterpolation(this.analyteConcns, this.responses, 0);

            for(int var1 = 0; var1 < this.nInterp; ++var1) {
                this.calculatedResponses[var1] = this.cubicInterpolation.interpolate(this.interpolationConcns[var1]);
            }

            if (!this.suppressPlot) {
                this.plot();
            }

            this.curveCheck(this.methodIndices[this.methodUsed]);
        }
    }

    public void linearInterpolation() {
        if (this.nAnalyteConcns < 2) {
            throw new IllegalArgumentException("Method cubicInterpolation requres at least 2 data points; only " + this.nAnalyteConcns + " were supplied");
        } else {
            this.methodUsed = 14;
            this.sampleErrorFlag = false;
            this.titleOne = "Linear interpolation ";
            if (!this.setDataOneDone) {
                this.setDataOne();
            }

            this.linearInterpolation = new LinearInterpolation(this.analyteConcns, this.responses);

            for(int var1 = 0; var1 < this.nInterp; ++var1) {
                this.calculatedResponses[var1] = this.linearInterpolation.interpolate(this.interpolationConcns[var1]);
            }

            if (!this.suppressPlot) {
                this.plot();
            }

            this.curveCheck(this.methodIndices[this.methodUsed]);
        }
    }

    public void polynomialFit(int degree) {
        if (this.nAnalyteConcns < degree + 2) {
            throw new IllegalArgumentException("Method polynomialFit(" + degree + ") requres at least " + (degree + 2) + " data points; only " + this.nAnalyteConcns + " were supplied");
        } else {
            this.methodUsed = 2;
            this.sampleErrorFlag = true;
            this.degSet = true;
            this.polyDegree = degree;
            this.titleOne = "Polynomial fitting: r = c[0] + c[1].a +  c[1].a^2 + ... + c[n].a^n; degree (n) = " + degree;
            if (!this.setDataOneDone) {
                this.setDataOne();
            }

            super.polynomial(degree);

            for(int var2 = 0; var2 < this.nInterp; ++var2) {
                this.calculatedResponses[var2] = 0.0D;

                for(int var3 = 0; var3 <= degree; ++var3) {
                    this.calculatedResponses[var2] += super.best[var3] * Math.pow(this.interpolationConcns[var2], (double)var3);
                }
            }

            if (!this.suppressPlot) {
                this.plot();
            }

            this.curveCheck(this.methodIndices[this.methodUsed]);
        }
    }

    public int bestPolynomialFit() {
        this.methodUsed = 3;
        this.sampleErrorFlag = true;
        this.titleOne = "Best polynomial fitting: r = c[0] + c[1].a +  c[1].a^2 + ... + c[n].a^n; best fit degree (n) = ";
        if (!this.setDataOneDone) {
            this.setDataOne();
        }

        ArrayList var1 = super.bestPolynomial();
        this.bestPolyDegree = (Integer)var1.get(0);
        this.titleOne = this.titleOne + " " + this.bestPolyDegree;

        for(int var2 = 0; var2 < this.nInterp; ++var2) {
            this.calculatedResponses[var2] = 0.0D;

            for(int var3 = 0; var3 <= this.bestPolyDegree; ++var3) {
                this.calculatedResponses[var2] += super.best[var3] * Math.pow(this.interpolationConcns[var2], (double)var3);
            }
        }

        if (!this.suppressPlot) {
            this.plot();
        }

        this.curveCheck(this.methodIndices[this.methodUsed]);
        return super.bestPolynomialDegree;
    }

    public void nonIntegerPolynomialFit(int nTerms) {
        if (this.nAnalyteConcns < nTerms + 1) {
            throw new IllegalArgumentException("Method nonIntegerPolynomial requres at least " + (nTerms + 1) + " data points; only " + this.nAnalyteConcns + " were supplied");
        } else {
            this.methodUsed = 4;
            this.polyNterms = nTerms;
            this.nTermsSet = true;
            this.sampleErrorFlag = true;
            this.titleOne = "Non-integer polynomial fitting: r = c[0] + c[1].a^c[n] + c[2].a^c[n+1] + ... + c[n].a^c[2n-1]";
            if (!this.setDataOneDone) {
                this.setDataOne();
            }

            super.nonIntegerPolynomial(nTerms);

            for(int var2 = 0; var2 < this.nInterp; ++var2) {
                this.calculatedResponses[var2] = super.best[0];

                for(int var3 = 1; var3 < this.polyNterms; ++var3) {
                    this.calculatedResponses[var2] += super.best[var3] * Math.pow(this.interpolationConcns[var2], super.best[nTerms + var3 - 1]);
                }
            }

            if (!this.suppressPlot) {
                this.plot();
            }

            this.curveCheck(this.methodIndices[this.methodUsed]);
        }
    }

    public void nonIntegerPolynomialFit() {
        this.nonIntegerPolynomialFit(3);
    }

    public void sigmoidThresholdFit() {
        if (this.nAnalyteConcns < 4) {
            throw new IllegalArgumentException("Method sigmoidThresholdFit requres at least 4 data points; only " + this.nAnalyteConcns + " were supplied");
        } else {
            this.methodUsed = 5;
            this.sampleErrorFlag = true;
            this.titleOne = "Sigmoid threshold fitting: r = A/(1 + exp(-alpha(a - theta)))";
            if (!this.setDataOneDone) {
                this.setDataOne();
            }

            super.sigmoidThreshold();

            for(int var1 = 0; var1 < this.nInterp; ++var1) {
                this.calculatedResponses[var1] = super.best[2] / (1.0D + Math.exp(-super.best[0] * (this.interpolationConcns[var1] - super.best[1])));
            }

            if (!this.suppressPlot) {
                this.plot();
            }

            this.curveCheck(this.methodIndices[this.methodUsed]);
        }
    }

    public void sipsSigmoidFit() {
        if (this.nAnalyteConcns < 4) {
            throw new IllegalArgumentException("Method sipsSigmoidFit requres at least 4 data points; only " + this.nAnalyteConcns + " were supplied");
        } else {
            this.methodUsed = 6;
            this.sampleErrorFlag = true;
            this.titleOne = "Sips sigmoid fitting: r = Aa^n/(theta^n + a^n)";
            if (!this.setDataOneDone) {
                this.setDataOne();
            }

            super.addConstraint(0, -1, 0.0D);
            super.sigmoidHillSips();
            super.removeConstraints();

            for(int var1 = 0; var1 < this.nInterp; ++var1) {
                this.calculatedResponses[var1] = super.best[2] * Math.pow(this.interpolationConcns[var1], super.best[1]) / (Math.pow(super.best[0], super.best[1]) + Math.pow(this.interpolationConcns[var1], super.best[1]));
            }

            if (!this.suppressPlot) {
                this.plot();
            }

            this.curveCheck(this.methodIndices[this.methodUsed]);
        }
    }

    public void fourParameterLogisticFit() {
        if (this.nAnalyteConcns < 5) {
            throw new IllegalArgumentException("Method fourParameterLogisticFit requres at least 5 data points; only " + this.nAnalyteConcns + " were supplied");
        } else {
            this.methodUsed = 7;
            this.sampleErrorFlag = true;
            this.titleOne = "Four parameter logistic fitting: r = top + (bottom - top)/(1 + (a/C50)^HillSlope)";
            if (!this.setDataOneDone) {
                this.setDataOne();
            }

            super.addConstraint(2, -1, 0.0D);
            super.ec50();
            super.removeConstraints();

            for(int var1 = 0; var1 < this.nInterp; ++var1) {
                this.calculatedResponses[var1] = super.best[0] + (super.best[1] - super.best[0]) / (1.0D + Math.pow(this.interpolationConcns[var1] / super.best[2], super.best[3]));
            }

            if (!this.suppressPlot) {
                this.plot();
            }

//            this.curveCheck(this.methodIndices[this.methodUsed]);
        }
    }

    public void ec50Fit() {
        this.fourParameterLogisticFit();
    }

    public void fourParameterLogisticFit(double bottom, double top) {
        if (this.nAnalyteConcns < 3) {
            throw new IllegalArgumentException("Method fourParameterLogisticFit requres at least 3 data points; only " + this.nAnalyteConcns + " were supplied");
        } else {
            this.bottom = bottom;
            this.top = top;
            this.methodUsed = 13;
            this.fourBotTopSet = true;
            this.sampleErrorFlag = true;
            this.titleOne = "Four parameter logistic fitting: r = top + (bottom - top)/(1 + (a/C50)^HillSlope) [top and bottom fixed]";
            if (!this.setDataOneDone) {
                this.setDataOne();
            }

            super.addConstraint(0, -1, 0.0D);
            super.ec50(bottom, top);
            super.removeConstraints();

            for(int var5 = 0; var5 < this.nInterp; ++var5) {
                this.calculatedResponses[var5] = top + (bottom - top) / (1.0D + Math.pow(this.interpolationConcns[var5] / super.best[0], super.best[1]));
            }

            if (!this.suppressPlot) {
                this.plot();
            }

            this.curveCheck(this.methodIndices[this.methodUsed]);
        }
    }

    public void ec50Fit(double bottom, double top) {
        this.fourParameterLogisticFit(bottom, top);
    }

    public void fiveParameterLogisticFit() {
        if (this.nAnalyteConcns < 6) {
//            throw new IllegalArgumentException("Method fiveParameterLogisticFit requres at least 6 data points; only " + this.nAnalyteConcns + " were supplied");
            throw new IllegalArgumentException("五参数拟合至少需要6个数据点。");

        } else {
            this.methodUsed = 8;
            this.sampleErrorFlag = true;
            this.titleOne = "五参数拟合: r = top + (bottom - top)/((1 + (a/C50)^HillSlope)^asymm)";
            if (!this.setDataOneDone) {
                this.setDataOne();
            }

            super.addConstraint(2, -1, 0.0D);
            super.fiveParameterLogistic();
            super.removeConstraints();

            for(int i = 0; i < this.nInterp; ++i) {
                this.calculatedResponses[i] = super.best[0] + (super.best[1] - super.best[0]) / Math.pow(1.0D + Math.pow(this.interpolationConcns[i] / super.best[2], super.best[3]), super.best[4]);
            }

            if (!this.suppressPlot) {
                this.plot();
            }

//            this.curveCheck(this.methodIndices[this.methodUsed]);
        }
    }

    public void fiveParameterLogisticFit(double bottom, double top) {
        if (this.nAnalyteConcns < 5) {
            throw new IllegalArgumentException("Method fiveParameterLogisticFit requres at least 5 data points; only " + this.nAnalyteConcns + " were supplied");
        } else {
            this.methodUsed = 13;
            this.sampleErrorFlag = true;
            this.bottom = bottom;
            this.top = top;
            this.fiveBotTopSet = true;
            this.titleOne = "Five parameter logistic fitting: r = top + (bottom - top)/((1 + (a/C50)^HillSlope)^asymm) [top and bottom fixed]";
            if (!this.setDataOneDone) {
                this.setDataOne();
            }

            super.addConstraint(0, -1, 0.0D);
            super.fiveParameterLogistic(bottom, top);
            super.removeConstraints();

            for(int var5 = 0; var5 < this.nInterp; ++var5) {
                this.calculatedResponses[var5] = top + (bottom - top) / Math.pow(1.0D + Math.pow(this.interpolationConcns[var5] / super.best[0], super.best[1]), super.best[2]);
            }

            if (!this.suppressPlot) {
                this.plot();
            }

            this.curveCheck(this.methodIndices[this.methodUsed]);
        }
    }

    public void shiftedRectangularHyperbolaFit() {
        if (this.nAnalyteConcns < 4) {
            throw new IllegalArgumentException("Method shiftedRectangularHyperbolaFit requres at least 4 data points; only " + this.nAnalyteConcns + " were supplied");
        } else {
            this.methodUsed = 9;
            this.sampleErrorFlag = true;
            this.titleOne = "Rectangular hyperbola fitting: r = A.a/(theta + a) + alpha)";
            if (!this.setDataOneDone) {
                this.setDataOne();
            }

            super.shiftedRectangularHyperbola();

            for(int var1 = 0; var1 < this.nInterp; ++var1) {
                this.calculatedResponses[var1] = this.best[2] * this.interpolationConcns[var1] / (this.best[0] + this.interpolationConcns[var1]) + this.best[1];
            }

            if (!this.suppressPlot) {
                this.plot();
            }

            this.curveCheck(this.methodIndices[this.methodUsed]);
        }
    }

    public void rectangularHyperbolaFit() {
        if (this.nAnalyteConcns < 3) {
            throw new IllegalArgumentException("Method rectangularHyperbolaFit requres at least 3 data points; only " + this.nAnalyteConcns + " were supplied");
        } else {
            this.methodUsed = 10;
            this.sampleErrorFlag = true;
            this.titleOne = "Rectangular hyperbola fitting: r = A.a/(theta + a))";
            if (!this.setDataOneDone) {
                this.setDataOne();
            }

            super.rectangularHyperbola();

            for(int var1 = 0; var1 < this.nInterp; ++var1) {
                this.calculatedResponses[var1] = this.best[1] * this.interpolationConcns[var1] / (this.best[0] + this.interpolationConcns[var1]);
            }

            if (!this.suppressPlot) {
                this.plot();
            }

            this.curveCheck(this.methodIndices[this.methodUsed]);
        }
    }

    public void amershamFit() {
        this.methodUsed = 11;
        if (!this.setDataOneDone) {
            this.setDataOne();
        }

        this.isCompetitive();
        this.amershamFlag1 = true;
        double[] estimates = new double[]{0.0D, 0.0D, 0.0D, 0.0D, 0.0D};
        this.fitAmersham(estimates);
    }

    public void amershamFit(double[] estimates) {
        this.methodUsed = 11;
        if (!this.setDataOneDone) {
            this.setDataOne();
        }

        this.isCompetitive();
        this.amershamFlag1 = false;
        this.fitAmersham(estimates);
    }

    private void fitAmersham(double[] estimates) {
        if (this.nAnalyteConcns < 6) {
            throw new IllegalArgumentException("Method amershamFit requres at least 6 data points; only " + this.nAnalyteConcns + " were supplied");
        } else {
            this.sampleErrorFlag = true;
            this.titleOne = "Amersham equation fitting: r = S(2.P(1-N/L)/(K+P+L+a+[(K-P+L+a)^2+4KP]^0.5)+N/L)";
            double[] var2 = new double[5];
            double[] var3 = new double[5];
            double var4 = 0.0D;
            double var6 = 0.0D;
            double var8 = 0.0D;
            double var10 = 0.0D;
            double var12 = this.responsesMax;
            int var15;
            int var17;
            if (this.amershamFlag1) {
                boolean var14 = false;

                for(var15 = 0; var15 < this.nAnalyteConcns; ++var15) {
                    if (this.responses[var15] < this.responsesMax / 2.0D) {
                        if (var15 != this.nAnalyteConcns - 1) {
                            var6 = (this.analyteConcns[var15] + this.analyteConcns[var15 + 1]) / 2.0D;
                        } else {
                            var6 = this.analyteConcns[var15];
                        }

                        var14 = true;
                    }

                    if (var14) {
                        break;
                    }
                }

                if (!var14) {
                    var6 = this.analyteConcns[this.nAnalyteConcns - 1];
                }

                var4 = var6 / 100.0D;
                var8 = var6 / 1000.0D;
                var2[0] = var4;
                var2[1] = var6;
                var2[2] = var8;
                var2[3] = var10;
                var2[4] = var12;

                for(var15 = 0; var15 < 5; ++var15) {
                    var3[var15] = 0.1D * var2[var15];
                }

                var3[3] = var8 / 100.0D;
            } else {
                var2[0] = estimates[0];
                var2[1] = estimates[1];
                var2[2] = estimates[2];
                var2[3] = estimates[3];
                var2[4] = estimates[4];

                for(var17 = 0; var17 < 5; ++var17) {
                    var3[var17] = 0.1D * var2[var17];
                }

                if (var3[3] == 0.0D) {
                    var3[3] = var3[2] / 100.0D;
                }
            }

            for(var17 = 0; var17 < 5; ++var17) {
                super.addConstraint(var17, -1, 0.0D);
            }

            double[] var16;
            if (super.xErrorsEntered) {
                AmershamDual var18 = new AmershamDual();
                var18.setYerrors(super.yErrors);
                var18.setXerrors(super.xErrors);
                super.simplex(var18, var2, var3);
                super.removeConstraints();

                for(var15 = 0; var15 < this.nInterp; ++var15) {
                    var16 = new double[]{this.interpolationConcns[var15]};
                    this.calculatedResponses[var15] = var18.function(super.best, var16, -1)[0];
                }
            } else {
                Amersham var19 = new Amersham();
                super.simplex(var19, var2, var3);
                super.removeConstraints();

                for(var15 = 0; var15 < this.nInterp; ++var15) {
                    var16 = new double[]{this.interpolationConcns[var15]};
                    this.calculatedResponses[var15] = var19.function(super.best, var16);
                }
            }

            if (!this.suppressPlot) {
                this.plot();
            }

            this.curveCheck(this.methodIndices[this.methodUsed]);
        }
    }

    private void isCompetitive() {
        int count = 0;

        for(int i = 1; i < this.nAnalyteConcns; ++i) {
            if (this.responses[i - 1] < this.responses[i]) {
                ++count;
            }

            if (count >= this.nAnalyteConcns / 2) {
                if (this.responses[this.nAnalyteConcns - 1] >= this.responses[0]) {
                    throw new IllegalArgumentException("The data appears incompatible with a competitive assay");
                }

                System.out.println("The data has been queried as that of a competitive assay but the fitting has not been aborted");
            }
        }

    }

    public void selectEquation() {
        ArrayList arrayList = this.chooseEquation(0);
        int selectCase = (Integer)arrayList.get(0);
        int var3 = (Integer)arrayList.get(1);
        double bottom = (Double)arrayList.get(2);
        double top = (Double)arrayList.get(3);
        switch(selectCase) {
            case 1:
                this.fiveParameterLogisticFit();
                break;
            case 2:
                this.fiveParameterLogisticFit(bottom, top);
                break;
            case 3:
                this.fourParameterLogisticFit();
                break;
            case 4:
                this.fourParameterLogisticFit(bottom, top);
                break;
            case 5:
                this.bestPolynomialFit();
                break;
            case 6:
                this.polynomialFit(var3);
                break;
            case 7:
                this.nonIntegerPolynomialFit(var3);
                break;
            case 8:
                this.sigmoidThresholdFit();
                break;
            case 9:
                this.sipsSigmoidFit();
                break;
            case 10:
                this.shiftedRectangularHyperbolaFit();
                break;
            case 11:
                this.rectangularHyperbolaFit();
                break;
            case 12:
                this.amershamFit();
                break;
            case 13:
                this.cubicSpline();
                break;
            case 14:
                this.linearInterpolation();
                break;
            default:
        }

    }

    private ArrayList<Object> chooseEquation(int chooseInt) {
        ArrayList arrayList = new ArrayList();
        String var3 = null;
        String[] text = null;
        String[] var5 = null;
        switch(chooseInt) {
            case 0:
                var3 = "Choose a fitting equation";
                text = new String[15];
                break;
            case 1:
                var3 = "Choose the first equation of the comparison";
                text = new String[13];
                break;
            case 2:
                var3 = "Choose the second equation of the comparison";
                text = new String[13];
                break;
            default:

        }

        assert text != null;
        text[0] = "1.  Five paramater logistic equation";
        text[1] = "2.  Five paramater logistic equation (top & bottom fixed)";
        text[2] = "3.  Four paramater logistic equation";
        text[3] = "4.  Four paramater logistic equation (top & bottom fixed)";
        text[4] = "5.  Best fit polynomial";
        text[5] = "6.  Polynomial of user supplied degree";
        text[6] = "7.  Non-integer polynomial";
        text[7] = "8.  Sigmoid threshold function";
        text[8] = "9.  Sips sigmoid function";
        text[9] = "10.  Shifted rectangular hyperbola";
        text[10] = "11.  Rectangular hyperbola";
        text[11] = "12.  Amersham mass action model";
        String[] var6;
        if (chooseInt == 0) {
            text[12] = "13.  Cubic spline";
            text[13] = "14.  Linear interpolation\n\n";
            text[14] = "Click on the appropriate button below";
            var6 = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14"};
            var5 = var6;
        } else {
            text[12] = "\nClick on the appropriate button below";
            var6 = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
            var5 = var6;
        }

        byte var13 = 0;
        int var7 = 1 + JOptionPane.showOptionDialog((Component)null, text, var3, 1, 3, (Icon)null, var5, var5[var13]);
        arrayList.add(new Integer(var7));
        int var8 = 0;
        if (var7 == 6) {
            var8 = Db.readInt("enter polynomial degree");
        }

        if (var7 == 7) {
            var8 = Db.readInt("enter non-integer polynomial number of terms");
        }

        arrayList.add(new Integer(var8));
        double var9 = 0.0D;
        double var11 = 0.0D;
        if (var7 == 2) {
            var9 = Db.readDouble("Enter five parameter logistic fixed bottom value");
            var11 = Db.readDouble("Enter five parameter logistic fixed top value");
        }

        if (var7 == 4) {
            var9 = Db.readDouble("Enter four parameter logistic fixed bottom value");
            var11 = Db.readDouble("Enter four parameter logistic fixed top value");
        }

        arrayList.add(new Double(var9));
        arrayList.add(new Double(var11));
        return arrayList;
    }

    private double[] log10(double[] data) {
        int length = data.length;
        double[] result = new double[length];
        this.nPlot = this.nAnalyteConcns;

        for(int i = 0; i < length; ++i) {
            if (data[i] == 0.0D) {
                result[i] = 0.0D;
                --this.nPlot;
            } else {
                result[i] = Math.log10(data[i]);
            }
        }

        return result;
    }

    private double[] loge(double[] data) {
        int length = data.length;
        double[] result = new double[length];
        this.nPlot = 0;
        this.nPlot = this.nAnalyteConcns;

        for(int i = 0; i < length; ++i) {
            if (data[i] == 0.0D) {
                result[i] = 0.0D / 0.0;
                --this.nPlot;
            } else {
                result[i] = Math.log(data[i]);
            }
        }

        return result;
    }

    private double[] antiLog10(double[] data) {
        int length = data.length;
        double[] result = new double[length];

        for(int i = 0; i < length; ++i) {
            result[i] = Math.pow(10.0D, data[i]);
        }

        return result;
    }

    private double[] antiLoge(double[] data) {
        int length = data.length;
        double[] result = new double[length];

        for(int i = 0; i < length; ++i) {
            result[i] = Math.exp(data[i]);
        }

        return result;
    }

    public void setData() {
        this.setDataOne();
    }

    private void setDataOne() {
        if (!this.analyteEntered) {
            throw new IllegalArgumentException("No analyte concentrations have been entered");
        } else if (!this.responsesEntered) {
            throw new IllegalArgumentException("No standard curve responses have been entered");
        } else if (this.nAnalyteConcns != this.nResponses) {
            throw new IllegalArgumentException("The number of analyte concentrations entered, " + this.nAnalyteConcns + ", must equal the number of standard curve responses entered, " + this.nResponses);
        } else {
            if (super.yErrorsEntered) {
                if (this.nAnalyteConcns != this.nWeights) {
                    throw new IllegalArgumentException("The number of analyte concentrations entered, " + this.nAnalyteConcns + ", must equal the number of standard curve weights entered, " + this.nWeights);
                }
            } else {
                super.weightOpt = false;
                super.weightFlag = 0;
                this.nWeights = this.nResponses;
                super.yErrors = new double[this.nWeights];

                for(int i = 0; i < this.nWeights; ++i) {
                    super.yErrors[i] = 1.0D;
                }
            }

            super.nData = this.nAnalyteConcns;
            super.nData0 = this.nAnalyteConcns;
            super.nXarrays = 1;
            super.nYarrays = 1;
            ArrayMaths arrayMaths = new ArrayMaths(this.analyteConcns);
            arrayMaths = arrayMaths.sort();
            int[] index = arrayMaths.originalIndices();
            double[] tmp = new double[this.nAnalyteConcns];
            tmp = Conv.copy(this.analyteConcns);

            int i;
            for(i = 0; i < this.nAnalyteConcns; ++i) {
                this.analyteConcns[i] = tmp[index[i]];
            }

            tmp = Conv.copy(this.responses);

            for(i = 0; i < this.nAnalyteConcns; ++i) {
                this.responses[i] = tmp[index[i]];
            }

            if (super.yErrorsEntered) {
                tmp = Conv.copy(super.yErrors);

                for(i = 0; i < this.nAnalyteConcns; ++i) {
                    super.yErrors[i] = tmp[index[i]];
                }
            }

            if (super.xErrorsEntered) {
                tmp = Conv.copy(super.xErrors[0]);

                for(i = 0; i < this.nAnalyteConcns; ++i) {
                    super.xErrors[0][i] = tmp[index[i]];
                }
            }

            if (this.analyteConcnFlag == 1) {
                tmp = Conv.copy(this.log10AnalyteConcns);

                for(i = 0; i < this.nAnalyteConcns; ++i) {
                    this.log10AnalyteConcns[i] = tmp[index[i]];
                }
            }

            if (this.analyteConcnFlag == 2) {
                tmp = Conv.copy(this.logeAnalyteConcns);

                for(i = 0; i < this.nAnalyteConcns; ++i) {
                    this.logeAnalyteConcns[i] = tmp[index[i]];
                }
            }

            i = this.nAnalyteConcns;

            int j;
            int count;
            for(j = 0; j < this.nAnalyteConcns - 1; ++j) {
                count = 1;
                int repeatIndex = 0;
//                new ArrayList();

                //从第1个浓度开始，判断后边的浓度是否跟它相同
                for(int jNext = j + 1; jNext < this.nAnalyteConcns; ++jNext) {
                    if (this.analyteConcns[j] == this.analyteConcns[jNext]) {
                        ++count;
                        repeatIndex = j;
                    }
                }

                if (count > 1) {
                    double sumValues = 0.0D;
                    double var11 = 0.0D;
                    double var13 = 0.0D;

                    //处理重复浓度数据，循环起点=重复浓度的index，循环结束=index+重复数量，求该浓度对应的反应值平均值
                    int k;
                    for(k = repeatIndex; k < repeatIndex + count; ++k) {

                        sumValues += this.responses[k];
                        if (super.yErrorsEntered) {
                            var11 += super.yErrors[j] * super.yErrors[j];
                        }

                        if (super.xErrorsEntered) {
                            var13 += super.xErrors[0][j] * super.xErrors[0][j];
                        }
                    }

                    this.responses[repeatIndex] = sumValues / (double)count;
                    if (super.yErrorsEntered) {
                        super.yErrors[repeatIndex] = Math.sqrt(var11) / (double)count;
                    }

                    if (super.xErrorsEntered) {
                        super.xErrors[0][repeatIndex] = Math.sqrt(var13) / (double)count;
                    }


                    //删除重复浓度的数据，并将排序过的数据向前移
                    for(k = repeatIndex + 1; k < this.nAnalyteConcns - count + 1; ++k) {
                        this.analyteConcns[k] = this.analyteConcns[k + count - 1];
                        this.responses[k] = this.responses[k + count - 1];
                        if (super.yErrorsEntered) {
                            super.yErrors[k] = super.yErrors[k + count - 1];
                        }

                        if (super.xErrorsEntered) {
                            super.xErrors[0][k] = super.xErrors[0][k + count - 1];
                        }

                        if (this.analyteConcnFlag == 1) {
                            this.log10AnalyteConcns[k] = this.log10AnalyteConcns[k + count - 1];
                        }

                        if (this.analyteConcnFlag == 2) {
                            this.logeAnalyteConcns[k] = this.logeAnalyteConcns[k + count - 1];
                        }
                    }

                    this.nAnalyteConcns = this.nAnalyteConcns - count + 1;
                }
            }

            if (this.nAnalyteConcns < i) {
                double[] var17 = this.analyteConcns;
                this.analyteConcns = new double[this.nAnalyteConcns];

                for(count = 0; count < this.nAnalyteConcns; ++count) {
                    this.analyteConcns[count] = var17[count];
                }

                var17 = this.responses;
                this.responses = new double[this.nAnalyteConcns];

                for(count = 0; count < this.nAnalyteConcns; ++count) {
                    this.responses[count] = var17[count];
                }

                var17 = super.yErrors;
                super.yErrors = new double[this.nAnalyteConcns];

                for(count = 0; count < this.nAnalyteConcns; ++count) {
                    this.weight[count] = var17[count];
                }

                if (super.xErrorsEntered) {
                    var17 = super.xErrors[0];
                    super.xErrors = new double[1][this.nAnalyteConcns];

                    for(count = 0; count < this.nAnalyteConcns; ++count) {
                        super.xErrors[0][count] = var17[count];
                    }
                }

                if (this.analyteConcnFlag == 1) {
                    var17 = this.log10AnalyteConcns;
                    this.log10AnalyteConcns = new double[this.nAnalyteConcns];

                    for(count = 0; count < this.nAnalyteConcns; ++count) {
                        this.log10AnalyteConcns[count] = var17[count];
                    }
                }

                if (this.analyteConcnFlag == 2) {
                    var17 = this.logeAnalyteConcns;
                    this.logeAnalyteConcns = new double[this.nAnalyteConcns];

                    for(count = 0; count < this.nAnalyteConcns; ++count) {
                        this.logeAnalyteConcns[count] = var17[count];
                    }
                }
            }

            this.nPlot = this.nAnalyteConcns;
            switch(this.analyteConcnFlag) {
                case 0:
                    this.log10AnalyteConcns = this.log10(this.analyteConcns);
                    this.logeAnalyteConcns = this.loge(this.analyteConcns);
                    break;
                case 1:
                    this.logeAnalyteConcns = this.loge(this.analyteConcns);
                    break;
                case 2:
                    this.log10AnalyteConcns = this.log10(this.analyteConcns);
                    break;
                default:
                    break;
            }

            count = 0;
            this.responsesPlot = true;

            for(int ii  = 0; ii < this.nAnalyteConcns; ii++) {
                if (this.responses[ii] <= 0.0D) {
                    count++;
                }
            }

            if (count == 1) {
                if (this.responses[0] <= 0.0D) {
                    --this.nPlot;
                    this.responsesPlot = true;
                } else {
                    this.responsesPlot = false;
                }
            } else if (count > 1) {
                this.responsesPlot = false;
            }

            if (this.responsesPlot) {
                switch(this.responsesFlag) {
                    case 0:
                        this.log10Responses = this.log10(this.responses);
                        this.logeResponses = this.loge(this.analyteConcns);
                        break;
                    case 1:
                        this.logeResponses = this.loge(this.responses);
                        break;
                    case 2:
                        this.log10Responses = this.log10(this.responses);
                        break;
                    default:
                        break;
                }
            }

            CubicSpline.setSuppress(true);
            double[][] var19 = new double[1][this.nResponses];
            var19[0] = this.analyteConcns;
            if (super.xErrorsEntered) {
                super.setDefaultValues(var19, this.responses, super.xErrors, super.yErrors);
            } else {
                super.setDefaultValues(var19, this.responses, super.yErrors);
            }

            this.responsesMax = Fmath.maximum(this.responses);
            this.responsesMin = Fmath.minimum(this.responses);
            this.responsesRange = this.responsesMax - this.responsesMin;
            this.setInterpolationData();
            if (this.nAnalyteConcns <= 3) {
                this.anscombeC = this.critValuesFive[0];
            } else if (this.nAnalyteConcns >= 120) {
                this.anscombeC = this.critValuesFive[19];
            } else {
                if (this.confidenceLevel == 0.95D) {
                    this.critValues = new CubicSpline(this.critSize, this.critValuesFive);
                } else {
                    this.critValues = new CubicSpline(this.critSize, this.critValuesOne);
                }

                this.anscombeC = this.critValues.interpolate((double)this.nAnalyteConcns);
            }

            this.setDataOneDone = true;
        }
    }

    public void setNintyNineLevel() {
        this.confidenceLevel = 0.99D;
    }

    public void setNintyFiveLevel() {
        this.confidenceLevel = 0.95D;
    }

    private void setInterpolationData() {
        this.interpolationConcns = new double[this.nInterp];
        this.calculatedResponses = new double[this.nInterp];
        double var1 = (this.analyteConcns[this.nAnalyteConcns - 1] - this.analyteConcns[0]) / (double)(this.nInterp - 1);
        this.interpolationConcns[0] = this.analyteConcns[0];

        for(int var3 = 1; var3 < this.nInterp - 1; ++var3) {
            this.interpolationConcns[var3] = this.interpolationConcns[var3 - 1] + var1;
        }

        this.interpolationConcns[this.nInterp - 1] = this.analyteConcns[this.nAnalyteConcns - 1];
    }

    public void resetPlotOption(int option) {
        if (option >= 0 && option <= 4) {
            this.plotOptions = option;
        } else {
            throw new IllegalArgumentException("The plot option, " + option + ", must be greater than or equal to 0 and less than 5");
        }
    }

//    private void suppressPlot() {
//        this.suppressPlot = true;
//    }
//
//    private void unsuppressPlot() {
//        this.suppressPlot = false;
//    }

    public void setPlotStatus(boolean status) {
        this.suppressPlot = status;
    }



    private int plot() {
        byte var1 = 1;
        double[][] var2 = new double[4][];
        PlotGraph plotGraph = null;
        int var4;
        int var5;
        int i;
        switch(this.plotOptions) {
            case 0:
                var2[0] = this.analyteConcns;
                var2[1] = this.responses;
                var2[2] = this.interpolationConcns;
                var2[3] = this.calculatedResponses;
                plotGraph = new PlotGraph(var2);
                plotGraph.setXaxisLegend("浓度");
                plotGraph.setYaxisLegend("反应值");
                break;
            case 1:
                var4 = this.nAnalyteConcns;
                var5 = this.nInterp;
                if (var4 != this.nPlot) {
                    var4 = this.nPlot;
                    var5 = this.nInterp - 1;
                }

                var2[0] = new double[var4];
                var2[1] = new double[var4];
                var2[2] = new double[var5];
                var2[3] = new double[var5];
                if (var4 == this.nAnalyteConcns) {
                    var2[0] = this.log10AnalyteConcns;
                    var2[1] = this.responses;
                    var2[3] = this.calculatedResponses;
                    var2[2] = new double[this.nInterp];

                    for(i = 0; i < this.nInterp; ++i) {
                        var2[2][i] = Math.log10(this.interpolationConcns[i]);
                    }
                } else {
                    for(i = 1; i < this.nAnalyteConcns; ++i) {
                        var2[0][i - 1] = this.log10AnalyteConcns[i];
                        var2[1][i - 1] = this.responses[i];
                    }

                    for(i = 1; i < this.nInterp; ++i) {
                        var2[2][i - 1] = Math.log10(this.interpolationConcns[i]);
                        var2[3][i - 1] = this.calculatedResponses[i];
                    }
                }

                plotGraph = new PlotGraph(var2);
                plotGraph.setXaxisLegend("Log10[ Analyte concentration (a) ]");
                plotGraph.setYaxisLegend("Assay response (r) ");
                break;
            case 2:
                var4 = this.nAnalyteConcns;
                var5 = this.nInterp;
                if (var4 != this.nPlot) {
                    var4 = this.nPlot;
                    var5 = this.nInterp - 1;
                }

                var2[0] = new double[var4];
                var2[1] = new double[var4];
                var2[2] = new double[var5];
                var2[3] = new double[var5];
                if (var4 == this.nAnalyteConcns) {
                    var2[0] = this.logeAnalyteConcns;
                    var2[1] = this.responses;
                    var2[3] = this.calculatedResponses;
                    var2[2] = new double[this.nInterp];

                    for(i = 0; i < this.nInterp; ++i) {
                        var2[2][i] = Math.log(this.interpolationConcns[i]);
                    }
                } else {
                    for(i = 1; i < this.nAnalyteConcns; ++i) {
                        var2[0][i - 1] = this.logeAnalyteConcns[i];
                        var2[1][i - 1] = this.responses[i];
                    }

                    for(i = 1; i < this.nInterp; ++i) {
                        var2[2][i - 1] = Math.log(this.interpolationConcns[i]);
                        var2[3][i - 1] = this.calculatedResponses[i];
                    }
                }

                plotGraph = new PlotGraph(var2);
                plotGraph.setXaxisLegend("Loge[ Analyte concentration (a) ]");
                plotGraph.setYaxisLegend("Assay response (r) ");
                break;
            case 3:
                if (!this.responsesPlot) {
                    System.out.println("A log(concentration) v. log(responses) plot is not possible; zero or negative responses");
                    return -1;
                }

                var4 = this.nAnalyteConcns;
                var5 = this.nInterp;
                if (var4 != this.nPlot) {
                    var4 = this.nPlot;
                    var5 = this.nInterp - 1;
                }

                var2[0] = new double[var4];
                var2[1] = new double[var4];
                var2[2] = new double[var5];
                var2[3] = new double[var5];
                if (var4 == this.nAnalyteConcns) {
                    var2[0] = this.log10AnalyteConcns;
                    var2[1] = this.log10Responses;
                    var2[2] = new double[this.nInterp];
                    var2[3] = new double[this.nInterp];

                    for(i = 0; i < this.nInterp; ++i) {
                        var2[2][i] = Math.log10(this.interpolationConcns[i]);
                        var2[3][i] = Math.log10(this.calculatedResponses[i]);
                    }
                } else {
                    for(i = 1; i < this.nAnalyteConcns; ++i) {
                        var2[0][i - 1] = this.log10AnalyteConcns[i];
                        var2[1][i - 1] = this.log10Responses[i];
                    }

                    for(i = 1; i < this.nInterp; ++i) {
                        var2[2][i - 1] = Math.log10(this.interpolationConcns[i]);
                        var2[3][i - 1] = Math.log10(this.calculatedResponses[i]);
                    }
                }

                plotGraph = new PlotGraph(var2);
                plotGraph.setXaxisLegend("Log10[ Analyte concentration (a) ]");
                plotGraph.setYaxisLegend("Log10[ Assay response (r) ]");
                break;
            case 4:
                if (!this.responsesPlot) {
                    System.out.println("A log(concentration) v. log(responses) plot is not possible; zero or negative responses");
                    return -1;
                }

                var4 = this.nAnalyteConcns;
                var5 = this.nInterp;
                if (var4 != this.nPlot) {
                    var4 = this.nPlot;
                    var5 = this.nInterp - 1;
                }

                var2[0] = new double[var4];
                var2[1] = new double[var4];
                var2[2] = new double[var5];
                var2[3] = new double[var5];
                if (var4 == this.nAnalyteConcns) {
                    var2[0] = this.logeAnalyteConcns;
                    var2[1] = this.logeResponses;
                    var2[2] = new double[this.nInterp];
                    var2[3] = new double[this.nInterp];

                    for(i = 0; i < this.nInterp; ++i) {
                        var2[2][i] = Math.log(this.interpolationConcns[i]);
                        var2[3][i] = Math.log(this.calculatedResponses[i]);
                    }
                } else {
                    for(i = 1; i < this.nAnalyteConcns; ++i) {
                        var2[0][i - 1] = this.logeAnalyteConcns[i];
                        var2[1][i - 1] = this.logeResponses[i];
                    }

                    for(i = 1; i < this.nInterp; ++i) {
                        var2[2][i - 1] = Math.log(this.interpolationConcns[i]);
                        var2[3][i - 1] = Math.log(this.calculatedResponses[i]);
                    }
                }

                plotGraph = new PlotGraph(var2);
                plotGraph.setXaxisLegend("Loge[ Analyte concentration (a) ]");
                plotGraph.setYaxisLegend("Loge[ Assay response (r) ]");
                break;
            default:
        }

        int[] var7 = new int[]{1, 0};
        plotGraph.setPoint(var7);
        int[] var8 = new int[]{0, 3};
        plotGraph.setLine(var8);
        plotGraph.setGraphTitle(this.titleZero);
        plotGraph.setGraphTitle2(this.titleOne);
        plotGraph.plot();
        return var1;
    }

    private void curveCheck(int var1) {
        this.interpStartIndex = 0;
        this.interpEndIndex = this.nInterp - 1;
        this.outlierFlag = false;
        this.outliers = new String[this.nAnalyteConcns];
        this.residualsMean = Stat.mean(super.residual);
        this.residualsSD = Stat.standardDeviation(super.residual);
        int var2 = 0;
        double var3 = Math.abs(this.residual[0]);

        int i2;
        for (i2 = 0; i2 < this.nAnalyteConcns; ++i2) {
            if (Math.abs(this.residual[i2]) > var3) {
                var3 = Math.abs(this.residual[i2]);
                var2 = i2;
            }
        }

        for(i2 = 0; i2 < this.nAnalyteConcns; ++i2) {
            this.outliers[i2] = "   ";
            if (Math.abs(this.residual[i2] - this.residualsMean) > this.anscombeC * this.residualsSD) {
                this.outliers[i2] = "possible outlier";
                if (i2 == var2) {
                    this.outliers[i2] = this.outliers[i2] + " (***)";
                }

                this.outlierFlag = true;
            }
        }

        i2 = 1;
        this.nTurningPoints = 0;
        boolean var6 = false;
        ArrayList arraylist = new ArrayList();
        double var8 = this.calculatedResponses[1] - this.calculatedResponses[0];
        if (var8 < 0.0D) {
            i2 = -1;
        }

        if (var8 == 0.0D) {
            var6 = true;
        }

        int i1;
        for(i1 = 2; i1 < this.nInterp; ++i1) {
            if (i2 == 1) {
                if (this.calculatedResponses[i1] <= this.calculatedResponses[i1 - 1]) {
                    var6 = true;
                }
            } else if (this.calculatedResponses[i1] >= this.calculatedResponses[i1 - 1]) {
                var6 = true;
            }

            if (var6) {
                ++this.nTurningPoints;
                arraylist.add(new Integer(i1));
                i2 = -i2;
                this.ambigCheck = true;
                var6 = false;
            }
        }

        if (this.nTurningPoints == 1) {
            this.turnIndices = new int[1];
            this.turnIndices[0] = (Integer) arraylist.get(0);
            if (this.turnIndices[0] <= this.nInterp / 2) {
                this.interpStartIndex = this.turnIndices[0];
            } else {
                this.interpEndIndex = this.turnIndices[0];
            }

            System.out.println(this.methodNames[var1]);
            System.out.println("The fitted or interpolated standard curve is not monotonic");
            System.out.println("The useable analyte concentration range is " + this.interpolationConcns[this.interpStartIndex] + " to " + this.interpolationConcns[this.interpEndIndex]);
        }

        int i5;
        if (this.nTurningPoints > 1) {
            this.turnIndices = new int[this.nTurningPoints];

            for(i1 = 0; i1 < this.nTurningPoints; ++i1) {
                this.turnIndices[i1] = (Integer) arraylist.get(i1);
            }

            int[] var28 = new int[this.nTurningPoints + 1];
            int[] var11 = new int[this.nTurningPoints + 1];
            int[] var12 = new int[this.nTurningPoints + 1];
            var12[0] = this.turnIndices[0];
            var28[0] = 0;
            var11[0] = this.turnIndices[0];

            int i3;
            for(i3 = 1; i3 < this.nTurningPoints; ++i3) {
                var12[i3] = this.turnIndices[i3] - this.turnIndices[i3 - 1];
                var28[i3] = this.turnIndices[i3 - 1];
                var11[i3] = this.turnIndices[i3];
            }

            var12[this.nTurningPoints] = this.nInterp - 1 - this.turnIndices[this.nTurningPoints - 1];
            var28[this.nTurningPoints] = this.turnIndices[this.nTurningPoints - 1];
            var11[this.nTurningPoints] = this.nInterp - 1;
            i3 = 0;

            for(int i4 = 0; i4 <= this.nTurningPoints; ++i4) {
                for(i5 = 0; i5 <= this.nTurningPoints; ++i5) {
                    if (i4 != i5 && var12[i4] >= var12[i5]) {
                        ++i3;
                    }
                }

                if (i3 == this.nTurningPoints) {
                    this.interpStartIndex = var28[i4];
                    this.interpEndIndex = var11[i4];
                } else {
                    i3 = 0;
                }

                if (i3 != 0) {
                    break;
                }
            }

            System.out.println(this.methodNames[var1]);
            System.out.println("The fitted or interpolated standard curve is not monotonic");
            System.out.println("The useable analyte concentration range is " + this.interpolationConcns[this.interpStartIndex] + " to " + this.interpolationConcns[this.interpEndIndex]);
        }

        this.interpAnalyteStart = this.interpolationConcns[this.interpStartIndex];
        this.interpAnalyteEnd = this.interpolationConcns[this.interpEndIndex];
        this.interpResponseStart = this.calculatedResponses[this.interpStartIndex];
        this.interpResponseEnd = this.calculatedResponses[this.interpEndIndex];
        this.nWorking = this.interpEndIndex - this.interpStartIndex + 1;
        double[] var29 = new double[this.nWorking];
        double[] var27 = new double[this.nWorking];

        for(int iWorking = 0; iWorking < this.nWorking; ++iWorking) {
            var29[iWorking] = this.interpolationConcns[this.interpStartIndex + iWorking];
            var27[iWorking] = this.calculatedResponses[this.interpStartIndex + iWorking];
        }

        if (this.methodUsed == 14) {
            this.linterp = new LinearInterpolation(var27, var29);
        } else {
            this.interp = new CubicSpline(var27, var29);
        }

        this.curveDirection = true;
        if (this.interpResponseStart > this.interpResponseEnd) {
            this.curveDirection = false;
        }

        this.workingResponseMin = Fmath.minimum(this.calculatedResponses);
        if (this.curveDirection) {
            if (this.workingResponseMin < this.interpResponseStart) {
                this.workingResponseMin = this.interpResponseStart;
            }
        } else if (this.workingResponseMin < this.interpResponseEnd) {
            this.workingResponseMin = this.interpResponseEnd;
        }

        this.workingResponseMax = Fmath.maximum(this.calculatedResponses);
        if (this.curveDirection) {
            if (this.workingResponseMax > this.interpResponseEnd) {
                this.workingResponseMax = this.interpResponseEnd;
            }
        } else if (this.workingResponseMax > this.interpResponseStart) {
            this.workingResponseMax = this.interpResponseStart;
        }

        double[] var31 = new double[this.nWorking];
        double var32 = Math.sqrt(super.sumOfSquaresError / (double)(this.nAnalyteConcns - super.nParam));
        if (this.sampleErrorFlag) {
            double[] var20;
            int i4;
            double var23;
            double[] var41;
            if (!super.yErrorsEntered) {
                for(i5 = 0; i5 < this.nWorking; ++i5) {
                    var31[i5] = var32;
                }
            } else {
                boolean var33 = true;
                int var16 = 0;
                int var17 = this.nAnalyteConcns - 1;
                boolean var18 = false;
                int var19;
                if (this.ambigCheck) {
                    for(var19 = 0; var19 < this.nAnalyteConcns; ++var19) {
                        if (this.interpAnalyteStart >= this.analyteConcns[var19]) {
                            var16 = var19;
                            var18 = true;
                        }

                        if (var18) {
                            break;
                        }
                    }

                    var18 = false;

                    for(var19 = this.nAnalyteConcns - 1; var19 >= 0; --var19) {
                        if (this.interpAnalyteEnd <= this.analyteConcns[var19]) {
                            var17 = var19;
                            var18 = true;
                        }

                        if (var18) {
                            break;
                        }
                    }
                }

                var19 = var17 - var16 + 1;
                var20 = new double[var19];

                for(i4 = 0; i4 < var19; ++i4) {
                    var20[i4] = super.yErrors[i4 + var16];
                }

                var33 = isMonotonic(var20);
                int var25;
                if (var33) {
                    double var38 = Math.abs(var20[1] - var20[0]);
                    var23 = var38;

                    for(var25 = 2; var25 < var19; ++var25) {
                        var38 = Math.abs(var20[var25] - var20[var25 - 1]);
                        if (var38 > var23) {
                            var23 = var38;
                        }
                    }

                    if (var23 > 0.6D * Math.abs(Fmath.maximum(var20) - Fmath.minimum(var20))) {
                        var33 = false;
                    }
                }

                var41 = new double[this.nAnalyteConcns];
                double var22 = var32 / this.weightsMean;

                for(int var24 = 0; var24 < this.nAnalyteConcns; ++var24) {
                    var41[var24] = super.yErrors[var24] * var22;
                }

                if (super.yErrorsEntered && !var33) {
                    LinearInterpolation var45 = new LinearInterpolation(this.analyteConcns, var41);

                    for(var25 = 0; var25 < this.nWorking; ++var25) {
                        var31[var25] = Math.abs(var45.interpolate(var29[var25]));
                    }
                } else {
                    CubicSpline var44 = new CubicSpline(this.analyteConcns, var41);

                    for(var25 = 0; var25 < this.nWorking; ++var25) {
                        var31[var25] = Math.abs(var44.interpolate(var29[var25]));
                    }
                }
            }

            this.errorp = new CubicSpline(var27, var31);
            this.propagatedErrors = new String[this.nAnalyteConcns];
            double var34 = 0.0D;
            ArrayList arrayList = new ArrayList();

            int i;
            for(i = 0; i < this.nAnalyteConcns; ++i) {
                boolean var37 = false;
                if (this.curveDirection) {
                    if (this.responses[i] < this.interpResponseStart || this.responses[i] > this.interpResponseEnd) {
                        var37 = true;
                    }
                } else if (this.responses[i] > this.interpResponseStart || this.responses[i] < this.interpResponseEnd) {
                    var37 = true;
                }

                if (var37) {
                    this.propagatedErrors[i] = "**";
                } else {
                    arrayList.add(new Double(this.responses[i]));
                    this.getSampleConcn(this.responses[i]);
                    var34 = this.getSampleConcnError();
                    arrayList.add(new Double(var34));
                    var34 = Fmath.truncate(var34, super.prec);
                    this.propagatedErrors[i] = (new Double(var34)).toString();
                }
            }

            i = arrayList.size() / 2;
            double[] var39 = new double[i];
            var20 = new double[i];

            for(i4 = 0; i4 < i; ++i4) {
                var39[i4] = (Double)arrayList.get(2 * i4);
                var20[i4] = (Double)arrayList.get(2 * i4 + 1);
            }

            CubicSpline var42 = new CubicSpline(var39, var20);
            var41 = new double[1001];
            double[] var43 = new double[1001];
            var41[0] = var39[0];
            var41[1000] = var39[i - 1];
            var23 = (var39[i - 1] - var39[0]) / 1000.0D;

            int var26;
            for(var26 = 1; var26 < 1000; ++var26) {
                var41[var26] = var41[var26 - 1] + var23;
            }

            for(var26 = 0; var26 < 1001; ++var26) {
                var43[var26] = var42.interpolate(var41[var26]);
            }

            Stat var47 = new Stat(var43);
            this.minimumAerror = var47.minimum();
            this.maximumAerror = var47.maximum();
            this.meanAerror = var47.mean();
            this.sdAerror = var47.standardDeviation();
        }

    }

    public double getSampleConcn(double values) {
        this.sampleResponse = values;
        double result = 0.0D;
        boolean var5 = false;
        if (this.curveDirection) {
            if (values < this.interpResponseStart || values > this.interpResponseEnd) {
                var5 = true;
            }
        } else if (values > this.interpResponseStart || values < this.interpResponseEnd) {
            var5 = true;
        }

        if (var5) {
            if (this.ambigCheck) {
//                System.out.println("The sample response, " + values + ", is outside the useable part of the standard curve:");
            } else {
//                System.out.println("The sample response, " + values + ", is outside the limits of the standard curve:");
            }
//            System.out.println(this.interpResponseStart + " to " + this.interpResponseEnd);
//            System.out.println("NaN returned");

        } else {
            if (this.methodUsed == 14) {
                result = this.linterp.interpolate(values);
            } else {
                result = this.interp.interpolate(values);
            }

            this.sampleConcn = result;
            this.sampleError = 0.0D;
            if (this.sampleErrorFlag) {
                double var6 = this.errorp.interpolate(values);
                double var8 = values - var6;
                if (var8 < this.workingResponseMin) {
                    var8 = this.workingResponseMin;
                }

                double var10 = (values - var8) / var6;
                double var12 = this.interp.interpolate(var8);
                double var14 = values + var6;
                if (var14 > this.workingResponseMax) {
                    var14 = this.workingResponseMax;
                }

                var10 += (var14 - values) / var6;
                double var16 = this.interp.interpolate(var14);
                this.sampleError = Math.abs(var16 - var12) / var10;
            }

        }
        return result;
    }

    public double getSampleConcnError() {
        if (this.methodUsed == 0) {
            System.out.println("ImmunoAssay method: getSampleConcnError()");
            System.out.println("A sample concentration error is not meaningful in the case of a cubic spline interpolation");
            System.out.println("NaN returned");
        }

        if (this.methodUsed == 1) {
            System.out.println("ImmunoAssay method: getSampleConcnError()");
            System.out.println("A sample concentration error is not meaningful in the case of a cubic interpolation");
            System.out.println("NaN returned");
        }

        if (this.methodUsed == 14) {
            System.out.println("ImmunoAssay method: getSampleConcnError()");
            System.out.println("A sample concentration error is not meaningful in the case of a linear interpolation");
            System.out.println("NaN returned");
        }

        return this.sampleError;
    }

    public void resetNfittedCurve(int var1) {
        this.nInterp = var1;
    }

    public double[] getFittedStandardCurveConcns() {
        return Conv.copy(this.interpolationConcns);
    }

    public double[] getFittedStandardCurveResponses() {
        return Conv.copy(this.calculatedResponses);
    }

    public double[] getWorkingConcentrationRange() {
        return new double[]{this.interpAnalyteStart, this.interpAnalyteEnd};
    }

    public double[] getWorkingResponseRange() {
        return new double[]{this.interpResponseStart, this.interpResponseEnd};
    }

    public double[] getModelParameterValues() {
        return Conv.copy(super.best);
    }

    public double[] getModelParameterErrors() {
        return Conv.copy(super.bestSd);
    }

    public int getNumberOfTurningPoints() {
        return this.nTurningPoints;
    }

    @Override
    public double getSumOfSquares() {
        return super.sumOfSquaresError;
    }

    public double getUnWeightedSumOfSquares() {
        return super.sumOfSquaresError;
    }

    public double getWeightedSumOfSquares() {
        return super.chiSquare;
    }

    public double[] getTurningPointConcns() {
        double[] var1 = null;
        if (this.nTurningPoints > 0) {
            var1 = new double[this.nTurningPoints];

            for(int var2 = 0; var2 < this.nTurningPoints; ++var2) {
                var1[var2] = this.interpolationConcns[this.turnIndices[var2]];
            }
        }

        return var1;
    }

    public double[] getTurningPointResponses() {
        double[] result = null;
        if (this.nTurningPoints > 0) {
            result = new double[this.nTurningPoints];

            for(int i = 0; i < this.nTurningPoints; ++i) {
                result[i] = this.calculatedResponses[this.turnIndices[i]];
            }
        }

        return result;
    }

    @Override
    public void print() {
        this.print(this.filename);
    }

    @Override
    public void print(String filename) {
        boolean result = true;
        switch(this.methodUsed) {
            case 0:
                System.out.println("There is no text file associated with the cubic spline interpolation method");
                result = false;
                break;
            case 1:
                System.out.println("There is no text file associated with the cubic interpolation method");
                result = false;
                break;
            case 14:
                System.out.println("There is no text file associated with the linear interpolation method");
                result = false;
                break;
            default:
        }

        if (result) {
            int pos = filename.indexOf(".");
            if (pos == -1) {
                filename = filename + ".txt";
            }

            this.filename = filename;
            FileOutput fileOutput = new FileOutput(filename);
            fileOutput.println(this.titleZero);
            if (this.dataRead) {
                fileOutput.println("Data input file name:   " + this.dataFilename);
            }

            fileOutput.dateAndTimeln(filename);
            this.commonPrint(fileOutput);
            fileOutput.println();
            fileOutput.println("End of file");
            fileOutput.close();
        }

    }

    public void commonPrint(FileOutput fileOutput) {
        fileOutput.println();
        fileOutput.println(this.titleOne);
        fileOutput.println("r = assay response;  a = analyte concentration");
        boolean result = false;
        String[] var3 = null;
        Object var4 = null;
        int var5;
        switch(this.methodUsed) {
            case 0:
            case 1:
            case 14:
                break;
            case 2:
                var3 = new String[this.polyDegree + 1];

                for(var5 = 0; var5 <= this.polyDegree; ++var5) {
                    var3[var5] = "c[" + var5 + "]";
                }

                result = false;
                break;
            case 3:
                var3 = new String[this.bestPolyDegree + 1];

                for(var5 = 0; var5 <= this.bestPolyDegree; ++var5) {
                    var3[var5] = "c[" + var5 + "]";
                }

                result = false;
                break;
            case 4:
                fileOutput.println("n = " + (this.polyNterms - 1));
                var3 = new String[2 * this.polyNterms - 1];

                for(var5 = 0; var5 < 2 * this.polyNterms - 1; ++var5) {
                    var3[var5] = "c[" + var5 + "]";
                }

                result = true;
                break;
            case 5:
                var3 = new String[]{"alpha", "theta", "A"};
                result = true;
                break;
            case 6:
                var3 = new String[]{"theta", "n", "A"};
                result = true;
                break;
            case 7:
                var3 = new String[]{"top", "bottom", "C50", "Hill Slope"};
                result = true;
                break;
            case 8:
                var3 = new String[]{"top", "bottom", "C50", "HillSlope", "Asymm"};
                result = true;
                break;
            case 9:
                var3 = new String[]{"theta", "alpha", "A"};
                result = true;
                break;
            case 10:
                var3 = new String[]{"theta", "A"};
                result = true;
                break;
            case 11:
                var3 = new String[]{"K", "L", "P", "N", "S"};
                result = true;
                break;
            case 12:
                fileOutput.println("top =    " + this.top);
                fileOutput.println("bottom = " + this.bottom);
                var3 = new String[]{"C50", "Hill Slope"};
                result = true;
                break;
            case 13:
                fileOutput.println("top =    " + this.top);
                fileOutput.println("bottom = " + this.bottom);
                var3 = new String[]{"C50", "HillSlope", "Asymm"};
                result = true;
                break;
            default:
                throw new IllegalArgumentException("methodUsed " + this.methodUsed + " not recognised");
        }

        if (result) {
            fileOutput.println();
            fileOutput.println("Non-linear regression (Nelder and Mead simplex procedure)");
            if (!super.nlrStatus) {
                fileOutput.println("Convergence criterion was not satisfied");
                fileOutput.println("The following results are, or are derived from, the current estimates on exiting the regression method");
                fileOutput.println();
            }

            fileOutput.println("Estimated parameters");
            fileOutput.println("The statistics are obtained assuming that the model behaves as a linear model about the minimum.");
            fileOutput.println("The Hessian matrix is calculated as the numerically derived second derivatives of chi square with respect to all pairs of parameters.");
            if (super.zeroCheck) {
                fileOutput.println("The best estimate/s equal to zero were replaced by the step size in the numerical differentiation!!!");
            }

            fileOutput.println("Consequentlty treat the statistics with great caution.");
            if (!super.posVarFlag) {
                fileOutput.println("Covariance matrix contains at least one negative diagonal element");
                fileOutput.println(" - all variances are dubious");
                fileOutput.println(" - may not be at a minimum or the model may be so non-linear that the linear approximation in calculating the statisics is invalid");
            }

            if (!super.invertFlag) {
                fileOutput.println("Hessian matrix is singular");
                fileOutput.println(" - variances cannot be calculated");
                fileOutput.println(" - may not be at a minimum  or the model may be so non-linear that the linear approximation in calculating the statisics is invalid");
            }
        } else {
            fileOutput.println("Linear regression");
        }

        fileOutput.println();
        fileOutput.printtab(" ", super.field);
        if (super.invertFlag) {
            fileOutput.printtab("Best", super.field);
            fileOutput.printtab("Estimate of", super.field);
            fileOutput.printtab("Coefficient", super.field);
            fileOutput.printtab("t-value", super.field);
            fileOutput.println("p-value");
        } else {
            fileOutput.println("Best");
        }

        if (super.invertFlag) {
            fileOutput.printtab(" ", super.field);
            fileOutput.printtab("estimate", super.field);
            fileOutput.printtab("the error", super.field);
            fileOutput.printtab("of", super.field);
            fileOutput.printtab("t", super.field);
            fileOutput.println("P > |t|");
        } else {
            fileOutput.printtab(" ", super.field);
            fileOutput.println("estimate");
        }

        if (super.invertFlag) {
            fileOutput.printtab(" ", super.field);
            fileOutput.printtab(" ", super.field);
            fileOutput.printtab(" ", super.field);
            fileOutput.println("variation (%)");
        } else {
            fileOutput.println("   ");
        }

        for(var5 = 0; var5 < super.nParam; ++var5) {
            if (super.invertFlag) {
                fileOutput.printtab(var3[var5], super.field);
                fileOutput.printtab(Fmath.truncate(super.best[var5], super.prec), super.field);
                fileOutput.printtab(Fmath.truncate(super.bestSd[var5], super.prec), super.field);
                fileOutput.printtab(Fmath.truncate(Math.abs(super.bestSd[var5] * 100.0D / super.best[var5]), super.prec), super.field);
                fileOutput.printtab(Fmath.truncate(super.tValues[var5], super.prec), super.field);
                fileOutput.println(Fmath.truncate(super.pValues[var5], super.prec));
            } else {
                fileOutput.printtab(var3[var5], super.field);
                fileOutput.println(Fmath.truncate(super.best[var5], super.prec));
            }
        }

        fileOutput.println();
        if (result) {
            fileOutput.printtab(" ", super.field);
            fileOutput.printtab("Best", super.field);
            fileOutput.printtab("Pre-minimum", super.field);
            fileOutput.printtab("Post-minimum", super.field);
            fileOutput.printtab("Initial", super.field);
            fileOutput.println("Fractional");
            fileOutput.printtab(" ", super.field);
            fileOutput.printtab("estimate", super.field);
            fileOutput.printtab("gradient", super.field);
            fileOutput.printtab("gradient", super.field);
            fileOutput.printtab("estimate", super.field);
            fileOutput.println("step");

            for(var5 = 0; var5 < super.nParam; ++var5) {
                fileOutput.printtab(var3[var5], super.field);
                fileOutput.printtab(Fmath.truncate(super.best[var5], super.prec), super.field);
                fileOutput.printtab(Fmath.truncate(super.grad[var5][0], super.prec), super.field);
                fileOutput.printtab(Fmath.truncate(super.grad[var5][1], super.prec), super.field);
                fileOutput.printtab(Fmath.truncate(super.startH[var5], super.prec), super.field);
                fileOutput.println(Fmath.truncate(super.stepH[var5], super.prec));
            }

            fileOutput.println();
        }

        var5 = 0;
        fileOutput.printtab("analyte", super.field);
        fileOutput.printtab("observed", super.field);
        fileOutput.printtab("calculated", super.field);
        fileOutput.printtab("weight", super.field);
        fileOutput.printtab("unweighted", super.field);
        fileOutput.printtab("weighted", super.field);
        fileOutput.printtab("estimated analyte", super.field);
        fileOutput.println("outliers");
        fileOutput.printtab("concn ", super.field);
        fileOutput.printtab("response", super.field);
        fileOutput.printtab("response", super.field);
        fileOutput.printtab("     ", super.field);
        fileOutput.printtab("residual", super.field);
        fileOutput.printtab("residual", super.field);
        fileOutput.printtab("concn error *", super.field);
        fileOutput.println("   ");

        int var6;
        for(var6 = 0; var6 < this.nAnalyteConcns; ++var6) {
            fileOutput.printtab(Fmath.truncate(super.xData[0][var5], super.prec), super.field);
            fileOutput.printtab(Fmath.truncate(super.yData[var5], super.prec), super.field);
            fileOutput.printtab(Fmath.truncate(super.yCalc[var5], super.prec), super.field);
            fileOutput.printtab(Fmath.truncate(super.weight[var5], super.prec), super.field);
            fileOutput.printtab(Fmath.truncate(super.residual[var5], super.prec), super.field);
            fileOutput.printtab(Fmath.truncate(super.residualW[var5], super.prec), super.field);
            fileOutput.printtab(this.propagatedErrors[var5], super.field);
            fileOutput.println(this.outliers[var6]);
            ++var5;
        }

        fileOutput.println();
        fileOutput.println("*  The estimated error in the estimated concentration on entering this response via getSampleConcn(response)");
        fileOutput.println("** Outside the working range");
        fileOutput.println("Mean of the unweighted residuals =               " + Fmath.truncate(this.residualsMean, super.prec));
        fileOutput.println("Standard deviation of the unweighted residuals = " + Fmath.truncate(this.residualsSD, super.prec));
        fileOutput.println("Outlier critical value at the " + this.confidenceLevel * 100.0D + " confidence level = " + Fmath.truncate(this.anscombeC * this.residualsSD + this.residualsMean, super.prec));
        if (this.outlierFlag) {
            fileOutput.println("*** the most extreme possible outlier");
        }

        fileOutput.println();
        fileOutput.println("Minimum estimated interpolated concentration error:                   " + Fmath.truncate(this.minimumAerror, super.prec));
        fileOutput.println("Maximum estimated interpolated concentration error:                   " + Fmath.truncate(this.maximumAerror, super.prec));
        fileOutput.println("Mean estimated interpolated concentration error:                      " + Fmath.truncate(this.meanAerror, super.prec));
        fileOutput.println("Standard deviation of the estimated interpolated concentration error: " + Fmath.truncate(this.sdAerror, super.prec));
        fileOutput.println();
        fileOutput.printtab("Degrees of freedom");
        fileOutput.println(super.degreesOfFreedom);
        fileOutput.printtab("Number of data points");
        fileOutput.println(super.nData);
        fileOutput.printtab("Number of estimated paramaters");
        fileOutput.println(super.nParam);
        fileOutput.printtab("Sum of squares of the unweighted residuals");
        fileOutput.println(Fmath.truncate(super.sumOfSquaresError, super.prec));
        if (super.weightOpt) {
            fileOutput.printtab("Chi Square");
            fileOutput.println(Fmath.truncate(super.chiSquare, super.prec));
            fileOutput.printtab("Reduced Chi Square");
            fileOutput.println(Fmath.truncate(super.reducedChiSquare, super.prec));
        }

        int var7;
        if (result) {
            fileOutput.println("Correlation: analyte concentration and responses");
            fileOutput.printtab(super.weightWord[super.weightFlag] + "Linear Correlation Coefficient (R)");
            fileOutput.println(Fmath.truncate(super.xyR, super.prec));
            if (Math.abs(super.xyR) <= 1.0D) {
                fileOutput.printtab(super.weightWord[super.weightFlag] + "Linear Correlation Coefficient Probability");
                fileOutput.println(Fmath.truncate(Stat.linearCorrCoeffProb(super.xyR, super.nData - 2), super.prec));
            }

            fileOutput.println(" ");
            fileOutput.println("Correlation: observed responses and calculated responses");
            fileOutput.printtab(super.weightWord[super.weightFlag] + "Linear Correlation Coefficient");
            fileOutput.println(Fmath.truncate(super.yyR, super.prec));
            if (Math.abs(super.yyR) <= 1.0D) {
                fileOutput.printtab(super.weightWord[super.weightFlag] + "Linear Correlation Coefficient Probability");
                fileOutput.println(Fmath.truncate(Stat.linearCorrCoeffProb(super.yyR, super.nData - 2), super.prec));
            }

            fileOutput.println();
            fileOutput.printtab("Durbin-Watson d statistic");
            fileOutput.println(Fmath.truncate(super.getDurbinWatsonD(), super.prec));
            fileOutput.println();
            if (super.posVarFlag && super.invertFlag && super.chiSquare != 0.0D) {
                fileOutput.println("Parameter - parameter correlation coefficients");
                fileOutput.printtab(" ", super.field);

                for(var6 = 0; var6 < super.nParam; ++var6) {
                    fileOutput.printtab(var3[var6], super.field);
                }

                fileOutput.println();
                fileOutput.println();

                for(var6 = 0; var6 < super.nParam; ++var6) {
                    fileOutput.printtab(var3[var6], super.field);

                    for(var7 = 0; var7 < super.nParam; ++var7) {
                        fileOutput.printtab(Fmath.truncate(super.corrCoeff[var7][var6], super.prec), super.field);
                    }

                    fileOutput.println();
                }
            }

            fileOutput.println();
            fileOutput.println("Coefficient of determination, R =                   " + Fmath.truncate(super.multR, super.prec));
            fileOutput.println("Adjusted Coefficient of determination, R' =         " + Fmath.truncate(super.adjustedR, super.prec));
            fileOutput.println("Coefficient of determination, F-ratio =             " + Fmath.truncate(super.multipleF, super.prec));
            fileOutput.println("Coefficient of determination, F-ratio probability = " + Fmath.truncate(super.multipleFprob, super.prec));
            fileOutput.println("Total (weighted) sum of squares  =                  " + Fmath.truncate(super.sumOfSquaresTotal, super.prec));
            fileOutput.println("Regression (weighted) sum of squares  =             " + Fmath.truncate(super.sumOfSquaresRegrn, super.prec));
            fileOutput.println("Error (weighted) sum of squares  =                  " + Fmath.truncate(super.chiSquare, super.prec));
            fileOutput.println();
            fileOutput.printtab("Number of iterations taken");
            fileOutput.println(super.nIter);
            fileOutput.printtab("Maximum number of iterations allowed");
            fileOutput.println(super.nMax);
            fileOutput.printtab("Number of restarts taken");
            fileOutput.println(super.kRestart);
            fileOutput.printtab("Maximum number of restarts allowed");
            fileOutput.println(super.konvge);
            fileOutput.printtab("Standard deviation of the simplex at the minimum");
            fileOutput.println(Fmath.truncate(super.simplexSd, super.prec));
            fileOutput.printtab("Convergence tolerance");
            fileOutput.println(super.fTol);
            switch(this.minTest) {
                case 0:
                    fileOutput.println("simplex sd < the tolerance times the mean of the absolute values of the y values");
                    break;
                case 1:
                    fileOutput.println("simplex sd < the tolerance");
                    break;
                case 2:
                    fileOutput.println("simplex sd < the tolerance times the square root(sum of squares/degrees of freedom");
            }

            fileOutput.println("Step used in numerical differentiation to obtain Hessian matrix");
            fileOutput.println("d(parameter) = parameter*" + super.delta);
        } else {
            fileOutput.println(" ");
            fileOutput.println("Correlation: analyte concentrations and responses");
            fileOutput.printtab(super.weightWord[super.weightFlag] + "Linear Correlation Coefficient (R)");
            fileOutput.println(Fmath.truncate(super.xyR, super.prec));
            if (Math.abs(super.xyR) <= 1.0D) {
                fileOutput.printtab(super.weightWord[super.weightFlag] + "Linear Correlation Coefficient Probability");
                fileOutput.println(Fmath.truncate(Stat.linearCorrCoeffProb(super.xyR, super.nData - 2), super.prec));
            }

            fileOutput.println(" ");
            fileOutput.println("Correlation: observed responses and calculated responses");
            fileOutput.printtab(super.weightWord[super.weightFlag] + "Linear Correlation Coefficient");
            fileOutput.println(Fmath.truncate(super.yyR, super.prec));
            if (Math.abs(super.yyR) <= 1.0D) {
                fileOutput.printtab(super.weightWord[super.weightFlag] + "Linear Correlation Coefficient Probability");
                fileOutput.println(Fmath.truncate(Stat.linearCorrCoeffProb(super.yyR, super.nData - 2), super.prec));
            }

            fileOutput.println();
            fileOutput.println();
            if (super.chiSquare != 0.0D) {
                fileOutput.println("Correlation coefficients");
                fileOutput.printtab(" ", super.field);

                for(var6 = 0; var6 < super.nParam; ++var6) {
                    fileOutput.printtab(var3[var6], super.field);
                }

                fileOutput.println();

                for(var6 = 0; var6 < super.nParam; ++var6) {
                    fileOutput.printtab(var3[var6], super.field);

                    for(var7 = 0; var7 < super.nParam; ++var7) {
                        fileOutput.printtab(Fmath.truncate(super.corrCoeff[var7][var6], super.prec), super.field);
                    }

                    fileOutput.println();
                }
            }

            fileOutput.println();
            fileOutput.printtab("Durbin-Watson d statistic");
            fileOutput.println(Fmath.truncate(super.getDurbinWatsonD(), super.prec));
            fileOutput.println();
            if (super.bestPolyFlag) {
                fileOutput.println("Method bestPolynomial search history");
                fileOutput.println("F-probability significance level: " + super.fProbSignificance + " (" + super.fProbSignificance * 100.0D + " %)");
                fileOutput.println("Degree of best fit polynomial " + super.bestPolynomialDegree);
                fileOutput.println(" ");
                fileOutput.print("Polynomial degree", 2 * super.field);
                fileOutput.print("chi square", 2 * super.field);
                fileOutput.print("F-ratio", super.field);
                fileOutput.print("F-probability", super.field + 2);
                fileOutput.println("F-value at the");
                fileOutput.print("comparison", 2 * super.field);
                fileOutput.print("comparison", 2 * super.field);
                fileOutput.print("   ", super.field);
                fileOutput.print("   ", super.field + 2);
                fileOutput.println("significance level");
                var6 = (Integer)super.bestPolyArray.get(1);
                int[] var15 = (int[])((int[])((int[])super.bestPolyArray.get(2)));
                int[] var8 = (int[])((int[])((int[])super.bestPolyArray.get(3)));
                double[] var9 = (double[])((double[])((double[])super.bestPolyArray.get(4)));
                double[] var10 = (double[])((double[])((double[])super.bestPolyArray.get(5)));
                double[] var11 = (double[])((double[])((double[])super.bestPolyArray.get(6)));
                double[] var12 = (double[])((double[])((double[])super.bestPolyArray.get(7)));
                double[] var13 = (double[])((double[])((double[])super.bestPolyArray.get(8)));

                for(int var14 = 0; var14 < var6; ++var14) {
                    fileOutput.print(var15[var14], super.field);
                    fileOutput.print(var8[var14], super.field);
                    fileOutput.print(Fmath.truncate(var9[var14], super.prec), super.field);
                    fileOutput.print(Fmath.truncate(var10[var14], super.prec), super.field);
                    fileOutput.print(Fmath.truncate(var11[var14], super.prec), super.field);
                    fileOutput.print(Fmath.truncate(var12[var14], super.prec), super.field + 2);
                    fileOutput.println(Fmath.truncate(var13[var14], super.prec));
                }
            }

            fileOutput.println();
            fileOutput.println("Coefficient of determination,   =                   " + Fmath.truncate(super.multR, super.prec));
            fileOutput.println("Adjusted Coefficient of determination,    =         " + Fmath.truncate(super.adjustedR, super.prec));
            fileOutput.println("Coefficient of determination, F-ratio =             " + Fmath.truncate(super.multipleF, super.prec));
            fileOutput.println("Coefficient of determination, F-ratio probability = " + Fmath.truncate(super.multipleFprob, super.prec));
            fileOutput.println("Total (weighted) sum of squares  =                  " + Fmath.truncate(super.sumOfSquaresTotal, super.prec));
            fileOutput.println("Regression (weighted) sum of squares  =             " + Fmath.truncate(super.sumOfSquaresRegrn, super.prec));
            fileOutput.println("Error (weighted) sum of squares  =                  " + Fmath.truncate(super.chiSquare, super.prec));
        }

    }

    public void compare() {
        this.selectCompare(this.significance, this.compFilename);
    }

    public void compare(double significance) {
        this.selectCompare(significance, this.compFilename);
    }

    public void compare(String filename) {
        this.selectCompare(this.significance, filename);
    }

    public void compare(double significance, String filename) {
        this.selectCompare(significance, filename);
    }

    private void selectCompare(double significance, String filename) {
        this.compWindow = true;
        ArrayList arrayList = this.chooseEquation(1);
        int element = (Integer)arrayList.get(0);
        int element6 = 0;
        int element7 = 0;
        double var8 = 0.0D;
        double var10 = 0.0D;
        if (element == 6) {
            element6 = (Integer)arrayList.get(1);
            this.degSet = true;
        }

        if (element == 7) {
            element7 = (Integer)arrayList.get(1);
            this.nTermsSet = true;
        }

        if (element == 2) {
            var8 = (Double)arrayList.get(2);
            var10 = (Double)arrayList.get(3);
            this.fiveBotTopSet = true;
        }

        if (element == 4) {
            var8 = (Double)arrayList.get(2);
            var10 = (Double)arrayList.get(3);
            this.fourBotTopSet = true;
        }

        arrayList = this.chooseEquation(2);
        int var12 = (Integer)arrayList.get(0);
        int var13 = 0;
        int var14 = 0;
        double var15 = 0.0D;
        double var17 = 0.0D;
        if (var12 == 6) {
            var13 = (Integer)arrayList.get(1);
            this.degSet = true;
        }

        if (var12 == 7) {
            var14 = (Integer)arrayList.get(1);
            this.nTermsSet = true;
        }

        if (var12 == 2) {
            var15 = (Double)arrayList.get(2);
            var17 = (Double)arrayList.get(3);
            this.fiveBotTopSet = true;
        }

        if (var12 == 4) {
            var15 = (Double)arrayList.get(2);
            var17 = (Double)arrayList.get(3);
            this.fourBotTopSet = true;
        }

        this.compare(element, element6, var8, var10, element7, var12, var13, var15, var17, var14, significance, filename);
    }

    public void compare(int equationIndex1, int equationIndex2) {
        this.compare(equationIndex1, 0, 0.0D, 0.0D, 0, equationIndex2, 0, 0.0D, 0.0D, 0, this.significance, this.compFilename);
    }

    public void compare(int equationIndex1, int equationIndex2, double significance) {
        this.compare(equationIndex1, 0, 0.0D, 0.0D, 0, equationIndex2, 0, 0.0D, 0.0D, 0, significance, this.compFilename);
    }

    public void compare(int var1, int var2, int var3, int var4, double var5) {
        this.degSet = true;
        this.compare(var1, var2, 0.0D, 0.0D, 0, var3, var4, 0.0D, 0.0D, 0, var5, this.compFilename);
    }

    public void compare(int equationIndex1, int equationIndex2, String filename) {
        this.compare(equationIndex1, 0, 0.0D, 0.0D, 0, equationIndex2, 0, 0.0D, 0.0D, 0, this.significance, filename);
    }

    public void compare(int var1, int var2, int var3, int var4, String filename) {
        this.degSet = true;
        this.compare(var1, var2, 0.0D, 0.0D, 0, var3, var4, 0.0D, 0.0D,
                0, this.significance, filename);
    }

    public void compare(int equationIndex1, int equationIndex2, int var3, int var4) {
        this.degSet = true;
        this.compare(equationIndex1, equationIndex2, 0.0D, 0.0D, 0, var3, var4, 0.0D,
                0.0D, 0, this.significance, this.compFilename);
    }

    public void compare(int equationIndex1, int equationIndex2, double var3, double var5, int var7,
                        int var8, int var9, double var10, double var12, int var14, double significance, String filename) {

        if (!this.degSet && (equationIndex2 > 0 || var9 > 0)) {
            this.degSet = true;
        }

        double[] var18 = new double[2];
        int[] var19 = new int[2];
        String[] var20 = new String[2];
        int[] var21 = new int[]{equationIndex1, var8};
        if (!this.compWindow) {
            if (equationIndex1 == 2) {
                var3 = Db.readDouble("Enter five parameter logistic bottom value");
                var5 = Db.readDouble("Enter five parameter logistic top value");
            }

            if (equationIndex1 == 4) {
                var3 = Db.readDouble("Enter four parameter logistic bottom value");
                var5 = Db.readDouble("Enter four parameter logistic top value");
            }

            if (equationIndex1 == 6 && !this.degSet) {
                equationIndex2 = Db.readInt("Enter the polynomial degree");
            }

            if (equationIndex1 == 7) {
                var7 = Db.readInt("Enter the non-integer polynomial number of terms");
            }
        }

        ArrayList arrayList = this.fittingForCompare(equationIndex1, equationIndex2, var3, var5, var7, false);
        var18[0] = (Double) arrayList.get(0);
        var19[0] = (Integer) arrayList.get(1);
        var20[0] = (String) arrayList.get(2);
        if (!this.compWindow) {
            if (equationIndex1 == 2) {
                var10 = Db.readDouble("Enter five parameter logistic bottom value");
                var12 = Db.readDouble("Enter five parameter logistic top value");
            }

            if (equationIndex1 == 4) {
                var10 = Db.readDouble("Enter four parameter logistic bottom value");
                var12 = Db.readDouble("Enter four parameter logistic top value");
            }

            if (equationIndex1 == 6 && !this.degSet) {
                var9 = Db.readInt("Enter the polynomial degree");
            }

            if (equationIndex1 == 7) {
                var14 = Db.readInt("Enter the non-integer polynomial number of terms");
            }
        }

        ArrayList arrayList2 = this.fittingForCompare(var8, var9, var10, var12, var14, true);
        var18[1] = (Double) arrayList2.get(0);
        var19[1] = (Integer) arrayList2.get(1);
        var20[1] = (String) arrayList2.get(2);
        ArrayList arrayList1 = comparisonTest(var21, var18[0], var19[0], this.nAnalyteConcns, var18[1], var19[1], this.nAnalyteConcns, significance);
        int var25 = (Integer) arrayList1.get(0);
        int var26 = (Integer) arrayList1.get(1);
        int var27 = (Integer) arrayList1.get(2);
        double var28 = (Double) arrayList1.get(3);
        double var30 = (Double) arrayList1.get(4);
        double var32 = (Double) arrayList1.get(5);
        int var34 = (Integer) arrayList1.get(6);
        int var35 = (Integer) arrayList1.get(7);
        int pos;
        if (var26 != equationIndex1) {
            pos = var19[0];
            var19[0] = var19[1];
            var19[1] = pos;
            double var37 = var18[0];
            var18[0] = var18[1];
            var18[1] = var37;
            String var39 = var20[0];
            var20[0] = var20[1];
            var20[1] = var39;
        }

        pos = filename.indexOf(".");
        if (pos == -1) {
            filename = filename + ".txt";
        }

        FileOutput fileOutput = new FileOutput(filename);
        fileOutput.println(this.titleZero);
        fileOutput.println("Comparison of two fitting procedures");
        if (this.dataRead) {
            fileOutput.println("Data input file name " + this.dataFilename);
        }

        fileOutput.dateAndTimeln(filename);
        fileOutput.println();
        fileOutput.println("Equations compared:");
        fileOutput.println("   Equation One: " + var20[0]);
        fileOutput.println("   Equation Two: " + var20[1]);
        fileOutput.println();
        fileOutput.print("                    ", super.field);
        fileOutput.print("Eqation", super.field);
        fileOutput.println("Eqation");
        fileOutput.print("                    ", super.field);
        fileOutput.print("One", super.field);
        fileOutput.println("Two");
        fileOutput.print("Sum of squares      ", super.field);
        fileOutput.print(Fmath.truncate(var18[0], super.prec), super.field);
        fileOutput.println(Fmath.truncate(var18[1], super.prec));
        fileOutput.print("Degrees of freedom  ", super.field);
        fileOutput.print(this.nAnalyteConcns - var19[0], super.field);
        fileOutput.println(this.nAnalyteConcns - var19[1]);
        fileOutput.println();
        switch (var25) {
            case 0:
                fileOutput.printtab("Extra sum of squares F-ratio =                 ");
                fileOutput.println(Fmath.truncate(var28, super.prec));
                fileOutput.printtab("F-ratio probabilty =                           ");
                fileOutput.println(Fmath.truncate(var30, super.prec));
                fileOutput.printtab("F value at the " + significance + " significance level = ");
                fileOutput.println(Fmath.truncate(var32, super.prec));
                fileOutput.println();
                if (var30 <= significance) {
                    fileOutput.println("In terms of a best fit Equation Two is the preferred fit.");
                    if (Math.abs(var19[1] - var19[0]) == 1) {
                        fileOutput.println("The additional parameter has, given a " + significance + " significance level, significantly improved the fit.");
                    } else {
                        fileOutput.println("The additional parameters have, given a " + significance + " significance level, significantly improved the fit.");
                    }
                } else {
                    fileOutput.println("In terms of a best fit Equation One is the preferred fit");
                    if (Math.abs(var19[1] - var19[0]) == 1) {
                        fileOutput.println("The additional parameter has not, given a " + significance + " significance level, significantly improved the fit.");
                    } else {
                        fileOutput.println("The additional parameters have not, given a " + significance + " significance level, significantly improved the fit.");
                    }
                }
                break;
            case 1:
                fileOutput.println("The fittings to the two equations cannot be distinguished using an F-test analysis");
                break;
            case 2:
                fileOutput.printtab("Variance F-ratio =                            ");
                fileOutput.println(Fmath.truncate(var28, super.prec));
                fileOutput.printtab("F-ratio probabilty =                          ");
                fileOutput.println(Fmath.truncate(var30, super.prec));
                fileOutput.printtab("F value at the " + significance + " significance level = ");
                fileOutput.println(Fmath.truncate(var32, super.prec));
                fileOutput.println();
                if (var30 <= significance) {
                    fileOutput.println("In terms of a best fit Equation Two is the preferred fit");
                    fileOutput.println("as indicated by the F-ratio analysis and a given significance level of " + significance);
                } else {
                    fileOutput.println("The fittings to the two equations cannot be distinguished using an F-test analysis");
                }
                break;
            default:
        }

        fileOutput.println("However, the choice of the model to be used as a standard curve should include, along with this comparison,");
        fileOutput.println("observation of the displayed graphs and of the detailed analyses listed below");
        fileOutput.println();
        fileOutput.println();
        fileOutput.println("Details of the two compared fitting exercises");
        fileOutput.println();
        FileInput var38 = new FileInput("ImmunoAssayTemp.txt");
        int var43 = var38.numberOfLines();
        String var40 = null;

        for (int var41 = 0; var41 < var43; ++var41) {
            var40 = var38.readLine();
            fileOutput.println(var40);
        }

        var38.close();
        fileOutput.println();
        fileOutput.println("End of file");
        fileOutput.close();
        this.deleteFile("ImmunoAssayTemp.txt");
    }

    private ArrayList<Object> fittingForCompare(int equationIndex1, int equationIndex2, double bottom, double top, int var7, boolean var8) {
        ArrayList arrayList = new ArrayList();
        String methodName = null;
        switch(equationIndex1) {
            case 1:
                this.fiveParameterLogisticFit();
                methodName = this.methodNames[1];
                break;
            case 2:
                this.fiveParameterLogisticFit(bottom, top);
                methodName = this.methodNames[2];
                break;
            case 3:
                this.fourParameterLogisticFit();
                methodName = this.methodNames[3];
                break;
            case 4:
                this.fourParameterLogisticFit(bottom, top);
                methodName = this.methodNames[4];
                break;
            case 5:
                this.bestPolynomialFit();
                methodName = this.methodNames[5] + ": degree = " + this.bestPolyDegree;
                break;
            case 6:
                this.polynomialFit(equationIndex2);
                methodName = this.methodNames[6] + equationIndex2;
                break;
            case 7:
                this.nonIntegerPolynomialFit(var7);
                methodName = this.methodNames[7];
                break;
            case 8:
                this.sigmoidThresholdFit();
                methodName = this.methodNames[8];
                break;
            case 9:
                this.sipsSigmoidFit();
                methodName = this.methodNames[9];
                break;
            case 10:
                this.shiftedRectangularHyperbolaFit();
                methodName = this.methodNames[10];
                break;
            case 11:
                this.rectangularHyperbolaFit();
                methodName = this.methodNames[11];
                break;
            case 12:
                this.amershamFit();
                methodName = this.methodNames[12];
                break;
            default:
                throw new IllegalArgumentException("Method number " + equationIndex1 + " not recognised");
        }

        FileOutput var11 = null;
        String var12 = "ImmunoAssayTemp.txt";
        if (var8) {
            var11 = new FileOutput(var12, 'a');
        } else {
            var11 = new FileOutput(var12);
        }

        this.commonPrint(var11);
        var11.println();
        var11.println();
        var11.close();
        arrayList.add(new Double(super.chiSquare));
        arrayList.add(new Integer(super.nParam));
        arrayList.add(methodName);
        return arrayList;
    }

    public static ArrayList<Object> comparisonTest(int[] var0, double var1, int var3, int var4, double var5, int var7, int var8, double var9) {
        ArrayList arrayList = new ArrayList();
        double var12 = 0.0D;
        double var14 = 0.0D;
        double var16 = 0.0D;
        double[] var18 = new double[]{var1, var5};
        int[] var19 = new int[]{var3, var7};
        int[] var20 = new int[]{var4, var8};
        int[] var21 = new int[]{var4 - var3, var8 - var7};
        double[] var22 = new double[2];
        double var23 = 0.0D;
        boolean var25 = false;
        boolean var26 = false;
        byte var27 = -1;
        double var28;
        int var30;
        if (var3 != var7 && var4 == var8) {
            if (var19[0] > var19[1]) {
                var28 = var18[0];
                var18[0] = var18[1];
                var18[1] = var28;
                var30 = var19[0];
                var19[0] = var19[1];
                var19[1] = var30;
                var30 = var20[0];
                var20[0] = var20[1];
                var20[1] = var30;
                var30 = var21[0];
                var21[0] = var21[1];
                var21[1] = var30;
                var30 = var0[0];
                var0[0] = var0[1];
                var0[1] = var30;
            }

            if (var18[0] > var18[1]) {
                var23 = var18[0] - var18[1];
                int var31 = var21[0] - var21[1];
                var12 = var23 * (double)var21[1] / ((double)var31 * var18[1]);
                var14 = Stat.fCompCDF(var12, var31, var21[1]);
                var16 = Stat.fTestValueGivenFprob(var9, var31, var21[1]);
                var27 = 0;
            }
        }

        if (var27 == -1) {
            if (var18[0] == var18[1] && var21[0] == var21[1]) {
                var27 = 1;
            } else {
                var22[0] = var18[0] / (double)var21[0];
                var22[1] = var18[1] / (double)var21[1];
                if (var22[0] <= var22[1]) {
                    var28 = var22[0];
                    var22[0] = var22[1];
                    var22[1] = var28;
                    var28 = var18[0];
                    var18[0] = var18[1];
                    var18[1] = var28;
                    var30 = var19[0];
                    var19[0] = var19[1];
                    var19[1] = var30;
                    var30 = var20[0];
                    var20[0] = var20[1];
                    var20[1] = var30;
                    var30 = var21[0];
                    var21[0] = var21[1];
                    var21[1] = var30;
                    var30 = var0[0];
                    var0[0] = var0[1];
                    var0[1] = var30;
                }

                var12 = var22[0] / var22[1];
                var14 = Stat.fCompCDF(var12, var21[0], var21[1]);
                var16 = Stat.fTestValueGivenFprob(var9, var21[0], var21[1]);
                var27 = 2;
            }
        }

        arrayList.add(new Integer(var27));
        arrayList.add(new Integer(var0[0]));
        arrayList.add(new Integer(var0[1]));
        arrayList.add(new Double(var12));
        arrayList.add(new Double(var14));
        arrayList.add(new Double(var16));
        arrayList.add(new Integer(var21[0]));
        arrayList.add(new Integer(var21[1]));
        arrayList.add(new Integer(var20[0]));
        arrayList.add(new Integer(var20[1]));
        return arrayList;
    }

    public void deleteFile(String filename) {
        boolean var2 = true;
        File var3 = new File(filename);
        if (!var3.exists()) {
            System.err.println("Method deleteFile: no file or directory of the name " + filename + " found");
            var2 = false;
        }

        if (var2 && !var3.canWrite()) {
            System.err.println("Method deleteFile: " + filename + " is write protected and cannot be deleted");
            var2 = false;
        }

        if (var2 && var3.isDirectory()) {
            String[] var4 = var3.list();
            if (var4.length > 0) {
                System.err.println("Method deleteFile: " + filename + " is a directory which is not empty; no action was taken");
                var2 = false;
            }
        }

        if (var2) {
            var2 = var3.delete();
            if (!var2) {
                System.err.println("Method deleteFile: deletion of the temporary file " + filename + " failed");
            }
        }

    }

    public static boolean isMonotonic(double[] data) {
        boolean var1 = true;
        int var2 = data.length;
        double var3 = data[1] - data[0];
        double var5 = var3;
        if (var3 == 0.0D) {
            var1 = false;
        } else {
            for(int var7 = 2; var7 < var2; ++var7) {
                var3 = data[var7] - data[var7 - 1];
                if (var3 == 0.0D) {
                    var1 = false;
                } else if (Fmath.sign(var3) != Fmath.sign(var5)) {
                    var1 = false;
                }

                if (!var1) {
                    break;
                }
            }
        }

        return var1;
    }

    public static double surfaceNumberConcn(double molWeight, double specVolume) {
        return ImmunoChemistry.surfaceNumberConcn(molWeight, specVolume);
    }

    public static double surfaceNumberConcn(double effectiveRadius) {
        return ImmunoChemistry.surfaceNumberConcn(effectiveRadius);
    }

    public static double surfaceMolarConcn(double var0, double var2) {
        return ImmunoChemistry.surfaceMolarConcn(var0, var2);
    }

    public static double surfaceMolarConcn(double effectiveRadius) {
        return ImmunoChemistry.surfaceMolarConcn(effectiveRadius);
    }

    public static double equivalentVolumeConcn(double molWeight, double area, double volume, double specVolume) {
        return ImmunoChemistry.equivalentVolumeConcn(molWeight, area, volume, specVolume);
    }

    public static double equivalentVolumeConcn(double effectiveRadius, double area, double volume) {
        return ImmunoChemistry.equivalentVolumeConcn(effectiveRadius, area, volume);
    }

    public static double convertSurfaceToVolumeConcn(double surfaceConcn, double area, double volume) {
        return ImmunoChemistry.convertSurfaceToVolumeConcn(surfaceConcn, area, volume);
    }

    public static double molecularRadius(double molWeight, double specVol) {
        return ImmunoChemistry.molecularRadius(molWeight, specVol);
    }

    public static double molecularRadius(double molWeight) {
        return ImmunoChemistry.molecularRadius(molWeight, 7.4E-4D);
    }

    public static double effectiveRadius(double diffusionCoefficient, double viscosity, double temperature) {
        return ImmunoChemistry.effectiveRadius(diffusionCoefficient, viscosity, temperature);
    }

    public static double getMolWeightIgG1() {
        return ImmunoChemistry.getMolWeightIgG1();
    }

    public static double getMolWeightIgG2() {
        return ImmunoChemistry.getMolWeightIgG2();
    }

    public static double getMolWeightIgG3() {
        return ImmunoChemistry.getMolWeightIgG3();
    }

    public static double getMolWeightIgG4() {
        return ImmunoChemistry.getMolWeightIgG4();
    }

    public static double getMolWeightIgM() {
        return ImmunoChemistry.getMolWeightIgM();
    }

    public static double getMolWeightIgA1() {
        return ImmunoChemistry.getMolWeightIgA1();
    }

    public static double getMolWeightIgA2() {
        return ImmunoChemistry.getMolWeightIgA2();
    }

    public static double getMolWeightIgD() {
        return ImmunoChemistry.getMolWeightIgD();
    }

    public static double getMolWeightIgE() {
        return ImmunoChemistry.getMolWeightIgE();
    }
}
