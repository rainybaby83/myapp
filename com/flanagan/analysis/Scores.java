//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.analysis;

import com.flanagan.io.Db;
import com.flanagan.io.FileChooser;
import com.flanagan.io.FileInput;
import com.flanagan.io.FileOutput;
import com.flanagan.math.ArrayMaths;
import com.flanagan.math.Conv;
import com.flanagan.math.Fmath;
import com.flanagan.math.Matrix;
import com.flanagan.plot.PlotGraph;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Scores {
    protected String[] title = null;
    protected int titleLines = 0;
    protected String inputFilename = null;
    protected String outputFilename = null;
    protected int fileOption = 1;
    protected boolean fileOptionSet = false;
    protected String[] fileExtensions = new String[]{".txt", ".xls"};
    protected boolean fileNumberingSet = false;
    protected int trunc = 6;
    protected boolean truncAll = false;
    protected int originalDataType = -1;
    protected int originalDataOrder = -1;
    protected Object originalData = null;
    protected double[][] scores0 = (double[][])null;
    protected double[][] originalScores0 = (double[][])null;
    protected double[][] standardizedScores0 = (double[][])null;
    protected boolean[][] missingScores0 = (boolean[][])null;
    protected double[][] scores1 = (double[][])null;
    protected double[][] originalScores1 = (double[][])null;
    protected double[][] standardizedScores1 = (double[][])null;
    protected boolean[][] missingRespones1 = (boolean[][])null;
    protected int readFlag = -1;
    protected boolean dataEntered = false;
    protected boolean dataPreprocessed = false;
    protected int nItems = 0;
    protected int originalNitems = 0;
    protected String[] itemNames = null;
    protected String[] originalItemNames = null;
    protected boolean itemNamesSet = false;
    protected int nPersons = 0;
    protected int originalNpersons = 0;
    protected String[] personNames = null;
    protected String[] originalPersonNames = null;
    protected boolean personNamesSet = false;
    protected int nScores = 0;
    protected int originalNscores = 0;
    protected String otherFalse = null;
    protected String otherTrue = null;
    protected boolean otherDichotomousDataSet = false;
    protected boolean[] dichotomous = null;
    protected double[] dichotomousPercentage = null;
    protected boolean dichotomousOverall = false;
    protected boolean dichotomousCheckDone = false;
    protected double dichotTrue = 1.0D;
    protected double dichotFalse = -1.0D;
    protected boolean dichotSet = false;
    protected boolean letterToNumeralSet = true;
    protected boolean ignoreNoResponseRequests = false;
    protected double itemDeletionPercentage = 100.0D;
    protected boolean itemDeletionPercentageSet = false;
    protected double personDeletionPercentage = 100.0D;
    protected boolean personDeletionPercentageSet = false;
    protected ArrayList<String> almrDeletedPersons = new ArrayList();
    protected int replacementOption = 3;
    protected String[] replacementOptionNames = new String[]{"score replaced by zero", "score replaced by person's mean", "score replaced by item mean", "score replaced by overall mean", "user supplied score for each 'no response'"};
    protected boolean replacementOptionSet = false;
    protected boolean allNoResponseOptionsSet = false;
    protected boolean noResponseHandlingSet = false;
    protected int nNaN = 0;
    protected boolean[] deletedItems = null;
    protected int nDeletedItems = 0;
    protected int[] deletedItemsIndices = null;
    protected int[] itemIndices = null;
    protected ArrayList<String> almrDeletedItems = new ArrayList();
    protected boolean[] deletedPersons = null;
    protected int nDeletedPersons = 0;
    protected int[] deletedPersonsIndices = null;
    protected int[] personIndices = null;
    protected int nReplacements = 0;
    protected String[] replacementIndices = null;
    protected double[] rawItemMeans = null;
    protected double rawItemMeansMean = 0.0D / 0.0;
    protected double rawItemMeansSd = 0.0D / 0.0;
    protected double rawItemMeansVar = 0.0D / 0.0;
    protected double rawItemMeansMin = 0.0D / 0.0;
    protected double rawItemMeansMax = 0.0D / 0.0;
    protected double rawItemMeansRange = 0.0D / 0.0;
    protected double[] rawItemStandardDeviations = null;
    protected double rawItemStandardDeviationsMean = 0.0D / 0.0;
    protected double rawItemStandardDeviationsSd = 0.0D / 0.0;
    protected double rawItemStandardDeviationsVar = 0.0D / 0.0;
    protected double rawItemStandardDeviationsMin = 0.0D / 0.0;
    protected double rawItemStandardDeviationsMax = 0.0D / 0.0;
    protected double rawItemStandardDeviationsRange = 0.0D / 0.0;
    protected double[] rawItemVariances = null;
    protected double rawItemVariancesMean = 0.0D / 0.0;
    protected double rawItemVariancesSd = 0.0D / 0.0;
    protected double rawItemVariancesVar = 0.0D / 0.0;
    protected double rawItemVariancesMin = 0.0D / 0.0;
    protected double rawItemVariancesMax = 0.0D / 0.0;
    protected double rawItemVariancesRange = 0.0D / 0.0;
    protected double[] rawItemMinima = null;
    protected double rawItemMinimaMean = 0.0D / 0.0;
    protected double rawItemMinimaSd = 0.0D / 0.0;
    protected double rawItemMinimaVar = 0.0D / 0.0;
    protected double rawItemMinimaMin = 0.0D / 0.0;
    protected double rawItemMinimaMax = 0.0D / 0.0;
    protected double rawItemMinimaRange = 0.0D / 0.0;
    protected double[] rawItemMaxima = null;
    protected double rawItemMaximaMean = 0.0D / 0.0;
    protected double rawItemMaximaSd = 0.0D / 0.0;
    protected double rawItemMaximaVar = 0.0D / 0.0;
    protected double rawItemMaximaMin = 0.0D / 0.0;
    protected double rawItemMaximaMax = 0.0D / 0.0;
    protected double rawItemMaximaRange = 0.0D / 0.0;
    protected double[] rawItemRanges = null;
    protected double rawItemRangesMean = 0.0D / 0.0;
    protected double rawItemRangesSd = 0.0D / 0.0;
    protected double rawItemRangesVar = 0.0D / 0.0;
    protected double rawItemRangesMin = 0.0D / 0.0;
    protected double rawItemRangesMax = 0.0D / 0.0;
    protected double rawItemRangesRange = 0.0D / 0.0;
    protected double[] rawItemTotals = null;
    protected double rawItemTotalsMean = 0.0D / 0.0;
    protected double rawItemTotalsSd = 0.0D / 0.0;
    protected double rawItemTotalsVar = 0.0D / 0.0;
    protected double rawItemTotalsMin = 0.0D / 0.0;
    protected double rawItemTotalsMax = 0.0D / 0.0;
    protected double rawItemTotalsRange = 0.0D / 0.0;
    protected double[] rawItemMedians = null;
    protected double rawItemMediansMean = 0.0D / 0.0;
    protected double rawItemMediansSd = 0.0D / 0.0;
    protected double rawItemMediansVar = 0.0D / 0.0;
    protected double rawItemMediansMin = 0.0D / 0.0;
    protected double rawItemMediansMax = 0.0D / 0.0;
    protected double rawItemMediansRange = 0.0D / 0.0;
    protected double[] rawItemMomentSkewness = null;
    protected double[] rawItemMedianSkewness = null;
    protected double[] rawItemQuartileSkewness = null;
    protected double[] rawItemKurtosisExcess = null;
    protected double[] rawPersonMeans = null;
    protected double[] rawPersonStandardDeviations = null;
    protected double[] rawPersonVariances = null;
    protected double[] rawPersonMinima = null;
    protected double[] rawPersonMaxima = null;
    protected double[] rawPersonRanges = null;
    protected double[] rawPersonTotals = null;
    protected double rawAllResponsesMean = 0.0D / 0.0;
    protected double rawAllResponsesStandardDeviation = 0.0D / 0.0;
    protected double rawAllResponsesVariance = 0.0D / 0.0;
    protected double rawAllResponsesMinimum = 0.0D / 0.0;
    protected double rawAllResponsesMaximum = 0.0D / 0.0;
    protected double rawAllResponsesRange = 0.0D / 0.0;
    protected double rawAllResponsesTotal = 0.0D / 0.0;
    protected double[][] rawCovariances = (double[][])null;
    protected double[][] rawCorrelationCoefficients = (double[][])null;
    protected double[] rawRhosWithTotal = null;
    protected double rawMeanRhoWithTotals = 0.0D / 0.0;
    protected double rawStandardDeviationRhoWithTotals = 0.0D / 0.0;
    protected double rawMeanRhoWithoutTotals = 0.0D / 0.0;
    protected double rawStandardDeviationRhoWithoutTotals = 0.0D / 0.0;
    protected double[] standardizedItemMeans = null;
    protected double standardizedItemMeansMean = 0.0D / 0.0;
    protected double standardizedItemMeansSd = 0.0D / 0.0;
    protected double standardizedItemMeansVar = 0.0D / 0.0;
    protected double standardizedItemMeansMin = 0.0D / 0.0;
    protected double standardizedItemMeansMax = 0.0D / 0.0;
    protected double standardizedItemMeansRange = 0.0D / 0.0;
    protected double[] standardizedItemStandardDeviations = null;
    protected double standardizedItemStandardDeviationsMean = 0.0D / 0.0;
    protected double standardizedItemStandardDeviationsSd = 0.0D / 0.0;
    protected double standardizedItemStandardDeviationsVar = 0.0D / 0.0;
    protected double standardizedItemStandardDeviationsMin = 0.0D / 0.0;
    protected double standardizedItemStandardDeviationsMax = 0.0D / 0.0;
    protected double standardizedItemStandardDeviationsRange = 0.0D / 0.0;
    protected double[] standardizedItemVariances = null;
    protected double standardizedItemVariancesMean = 0.0D / 0.0;
    protected double standardizedItemVariancesSd = 0.0D / 0.0;
    protected double standardizedItemVariancesVar = 0.0D / 0.0;
    protected double standardizedItemVariancesMin = 0.0D / 0.0;
    protected double standardizedItemVariancesMax = 0.0D / 0.0;
    protected double standardizedItemVariancesRange = 0.0D / 0.0;
    protected double[] standardizedItemMinima = null;
    protected double standardizedItemMinimaMean = 0.0D / 0.0;
    protected double standardizedItemMinimaSd = 0.0D / 0.0;
    protected double standardizedItemMinimaVar = 0.0D / 0.0;
    protected double standardizedItemMinimaMin = 0.0D / 0.0;
    protected double standardizedItemMinimaMax = 0.0D / 0.0;
    protected double standardizedItemMinimaRange = 0.0D / 0.0;
    protected double[] standardizedItemMaxima = null;
    protected double standardizedItemMaximaMean = 0.0D / 0.0;
    protected double standardizedItemMaximaSd = 0.0D / 0.0;
    protected double standardizedItemMaximaVar = 0.0D / 0.0;
    protected double standardizedItemMaximaMin = 0.0D / 0.0;
    protected double standardizedItemMaximaMax = 0.0D / 0.0;
    protected double standardizedItemMaximaRange = 0.0D / 0.0;
    protected double[] standardizedItemRanges = null;
    protected double standardizedItemRangesMean = 0.0D / 0.0;
    protected double standardizedItemRangesSd = 0.0D / 0.0;
    protected double standardizedItemRangesVar = 0.0D / 0.0;
    protected double standardizedItemRangesMin = 0.0D / 0.0;
    protected double standardizedItemRangesMax = 0.0D / 0.0;
    protected double standardizedItemRangesRange = 0.0D / 0.0;
    protected double[] standardizedItemTotals = null;
    protected double standardizedItemTotalsMean = 0.0D / 0.0;
    protected double standardizedItemTotalsSd = 0.0D / 0.0;
    protected double standardizedItemTotalsVar = 0.0D / 0.0;
    protected double standardizedItemTotalsMin = 0.0D / 0.0;
    protected double standardizedItemTotalsMax = 0.0D / 0.0;
    protected double standardizedItemTotalsRange = 0.0D / 0.0;
    protected double[] standardizedItemMedians = null;
    protected double standardizedItemMediansMean = 0.0D / 0.0;
    protected double standardizedItemMediansSd = 0.0D / 0.0;
    protected double standardizedItemMediansVar = 0.0D / 0.0;
    protected double standardizedItemMediansMin = 0.0D / 0.0;
    protected double standardizedItemMediansMax = 0.0D / 0.0;
    protected double standardizedItemMediansRange = 0.0D / 0.0;
    protected double[] standardizedItemMomentSkewness = null;
    protected double[] standardizedItemMedianSkewness = null;
    protected double[] standardizedItemQuartileSkewness = null;
    protected double[] standardizedItemKurtosisExcess = null;
    protected double[] standardizedPersonMeans = null;
    protected double[] standardizedPersonStandardDeviations = null;
    protected double[] standardizedPersonVariances = null;
    protected double[] standardizedPersonMinima = null;
    protected double[] standardizedPersonMaxima = null;
    protected double[] standardizedPersonRanges = null;
    protected double[] standardizedPersonTotals = null;
    protected double standardizedAllResponsesMean = 0.0D / 0.0;
    protected double standardizedAllResponsesStandardDeviation = 0.0D / 0.0;
    protected double standardizedAllResponsesVariance = 0.0D / 0.0;
    protected double standardizedAllResponsesMinimum = 0.0D / 0.0;
    protected double standardizedAllResponsesMaximum = 0.0D / 0.0;
    protected double standardizedAllResponsesRange = 0.0D / 0.0;
    protected double standardizedAllResponsesTotal = 0.0D / 0.0;
    protected double[][] standardizedCovariances = (double[][])null;
    protected double[][] standardizedCorrelationCoefficients = (double[][])null;
    protected double[] standardizedRhosWithTotal = null;
    protected double standardizedMeanRhoWithTotals = 0.0D / 0.0;
    protected double standardizedStandardDeviationRhoWithTotals = 0.0D / 0.0;
    protected double standardizedMeanRhoWithoutTotals = 0.0D / 0.0;
    protected double standardizedStandardDeviationRhoWithoutTotals = 0.0D / 0.0;
    protected boolean variancesCalculated = false;
    protected boolean covariancesCalculated = false;
    protected boolean nFactorOption = false;
    protected int sameCheck = 0;

    public Scores() {
    }

    public void enterTitle(String var1) {
        if (this.title == null) {
            this.title = new String[2];
            this.title[0] = "Title: " + var1;
            Date var2 = new Date();
            String var3 = DateFormat.getDateInstance().format(var2);
            String var4 = DateFormat.getTimeInstance().format(var2);
            this.title[1] = "Program execution initiated at " + var4 + " on " + var3;
        } else {
            this.title[0] = var1;
        }

    }

    public void setPersonDeletionPercentage(double var1) {
        this.personDeletionPercentage = var1;
        this.personDeletionPercentageSet = true;
        if (this.itemDeletionPercentageSet && this.replacementOptionSet) {
            this.allNoResponseOptionsSet = true;
            if (this.dataEntered) {
                this.preprocessData();
            }
        }

    }

    public void setItemDeletionPercentage(double var1) {
        this.itemDeletionPercentage = var1;
        this.itemDeletionPercentageSet = true;
        if (this.personDeletionPercentageSet && this.replacementOptionSet) {
            this.allNoResponseOptionsSet = true;
            if (this.dataEntered) {
                this.preprocessData();
            }
        }

    }

    public void setMissingDataOption(int var1) {
        if (var1 >= 1 && var1 <= 5) {
            this.replacementOption = var1;
            this.replacementOptionSet = true;
            if (this.personDeletionPercentageSet && this.itemDeletionPercentageSet) {
                this.allNoResponseOptionsSet = true;
                if (this.dataEntered) {
                    this.preprocessData();
                }
            }

        } else {
            throw new IllegalArgumentException("The missing response option entered is " + var1 + "; the option must be 1, 2, 3, 4 or 5");
        }
    }

    public void ignoreMissingDataOptionRequests() {
        this.ignoreNoResponseRequests = true;
        this.allNoResponseOptionsSet = true;
        this.itemDeletionPercentageSet = true;
        this.personDeletionPercentageSet = true;
        this.allNoResponseOptionsSet = true;
    }

    protected void noResponseHandling() {
        if (this.nNaN > 0 && !this.noResponseHandlingSet) {
            this.nDeletedPersons = 0;
            this.missingScores0 = new boolean[this.nItems][this.nPersons];
            int var1 = 0;

            while(true) {
                int var2;
                if (var1 >= this.nItems) {
                    int var3;
                    for(var1 = 0; var1 < this.nPersons; ++var1) {
                        var2 = 0;
                        this.deletedPersons[var1] = false;

                        for(var3 = 0; var3 < this.nItems; ++var3) {
                            if (Double.isNaN(this.scores0[var3][var1])) {
                                ++var2;
                            }
                        }

                        if (var2 == this.nItems) {
                            this.deletedPersons[var1] = true;
                        }
                    }

                    double var4;
                    for(var1 = 0; var1 < this.nPersons; ++var1) {
                        if (!this.deletedPersons[var1]) {
                            var2 = 0;

                            for(var3 = 0; var3 < this.nItems; ++var3) {
                                if (Double.isNaN(this.scores0[var3][var1])) {
                                    ++var2;
                                    var4 = (double)var2 * 100.0D / (double)this.nItems;
                                    if (var4 > this.personDeletionPercentage) {
                                        this.deletedPersons[var1] = true;
                                    }
                                }
                            }
                        }
                    }

                    for(var1 = 0; var1 < this.nPersons; ++var1) {
                        if (this.deletedPersons[var1]) {
                            ++this.nDeletedPersons;
                        }
                    }

                    int var5;
                    int var6;
                    boolean[][] var16;
                    String[] var17;
                    double[][] var18;
                    if (this.nDeletedPersons > 0) {
                        var1 = 0;
                        this.deletedPersonsIndices = new int[this.nDeletedPersons];

                        for(var2 = 0; var2 < this.nPersons; ++var2) {
                            if (this.deletedPersons[var2]) {
                                this.deletedPersonsIndices[var1] = var2;
                                ++var1;
                            }
                        }

                        var18 = new double[this.nItems][this.nPersons - this.nDeletedPersons];
                        var16 = new boolean[this.nItems][this.nPersons - this.nDeletedPersons];
                        var17 = new String[this.nPersons - this.nDeletedItems];
                        this.personIndices = new int[this.nPersons - this.nDeletedPersons];
                        var1 = 0;

                        for(var5 = 0; var5 < this.nPersons; ++var5) {
                            var17[var5] = this.personNames[var5];
                            if (!this.deletedPersons[var5]) {
                                this.almrDeletedPersons.add(this.personNames[var5]);

                                for(var6 = 0; var6 < this.nItems; ++var6) {
                                    var18[var6][var1] = this.scores0[var6][var5];
                                    var16[var6][var1] = this.missingScores0[var6][var5];
                                }

                                this.personIndices[var1] = var5;
                                ++var1;
                            }
                        }

                        this.nPersons -= this.nDeletedPersons;
                        this.nScores = this.nPersons * this.nItems;
                        this.scores0 = var18;
                        this.missingScores0 = var16;
                        this.personNames = var17;
                        this.scores1 = this.transpose(this.scores0);
                    }

                    if (this.nDeletedPersons == 0) {
                        this.personIndices = new int[this.nPersons];

                        for(var1 = 0; var1 < this.nPersons; this.personIndices[var1] = var1++) {
                            ;
                        }
                    }

                    this.deletedItems = new boolean[this.nItems];
                    this.nDeletedItems = 0;

                    for(var1 = 0; var1 < this.nItems; ++var1) {
                        var2 = 0;
                        this.deletedItems[var1] = false;

                        for(var3 = 0; var3 < this.nPersons; ++var3) {
                            if (Double.isNaN(this.scores0[var1][var3])) {
                                ++var2;
                            }
                        }

                        if (var2 == this.nPersons) {
                            this.deletedItems[var1] = true;
                        }
                    }

                    for(var1 = 0; var1 < this.nItems; ++var1) {
                        this.deletedItems[var1] = false;
                        var2 = 0;

                        for(var3 = 0; var3 < this.nPersons; ++var3) {
                            if (Double.isNaN(this.scores0[var1][var3])) {
                                ++var2;
                                var4 = (double)var2 * 100.0D / (double)this.nPersons;
                                if (var4 > this.itemDeletionPercentage) {
                                    this.deletedItems[var1] = true;
                                }
                            }
                        }
                    }

                    for(var1 = 0; var1 < this.nItems; ++var1) {
                        if (this.deletedItems[var1]) {
                            ++this.nDeletedItems;
                        }
                    }

                    if (this.nDeletedItems > 0) {
                        var1 = 0;
                        this.deletedItemsIndices = new int[this.nDeletedItems];

                        for(var2 = 0; var2 < this.nItems; ++var2) {
                            if (this.deletedItems[var2]) {
                                this.deletedItemsIndices[var1] = var2;
                                ++var1;
                            }
                        }

                        if (this.nItems - this.nDeletedItems <= 1) {
                            throw new IllegalArgumentException("You have deleted " + this.nDeletedItems + " items leaving " + (this.nItems - this.nDeletedItems) + " items and hence no possibility calculation of alpha");
                        }

                        var18 = new double[this.nItems - this.nDeletedItems][this.nPersons];
                        var16 = new boolean[this.nItems - this.nDeletedItems][this.nPersons];
                        var17 = new String[this.nItems - this.nDeletedItems];
                        this.itemIndices = new int[this.nItems - this.nDeletedItems];
                        var1 = 0;

                        for(var5 = 0; var5 < this.nItems; ++var5) {
                            if (!this.deletedItems[var5]) {
                                this.almrDeletedItems.add(this.itemNames[var5]);
                                var17[var1] = this.itemNames[var5];

                                for(var6 = 0; var6 < this.nPersons; ++var6) {
                                    var18[var1][var6] = this.scores0[var5][var6];
                                    var16[var1][var6] = this.missingScores0[var5][var6];
                                }

                                this.itemIndices[var1] = var5;
                                ++var1;
                            }
                        }

                        this.nItems -= this.nDeletedItems;
                        this.nScores = this.nPersons * this.nItems;
                        this.scores0 = var18;
                        this.missingScores0 = var16;
                        this.scores1 = this.transpose(this.scores0);
                        this.itemNames = var17;
                    }

                    if (this.nDeletedItems == 0) {
                        this.itemIndices = new int[this.nItems];

                        for(var1 = 0; var1 < this.nItems; this.itemIndices[var1] = var1++) {
                            ;
                        }
                    }

                    var1 = 0;

                    for(var2 = 0; var2 < this.nPersons; ++var2) {
                        for(var3 = 0; var3 < this.nItems; ++var3) {
                            if (Double.isNaN(this.scores0[var3][var2])) {
                                this.missingScores0[var3][var2] = true;
                                ++var1;
                            }
                        }
                    }

                    if (var1 <= 0) {
                        break;
                    }

                    double[] var20 = new double[this.nItems];
                    double var19 = 0.0D;
                    var5 = 0;

                    int var7;
                    int var8;
                    for(var6 = 0; var6 < this.nItems; ++var6) {
                        var20[var6] = 0.0D;
                        var7 = 0;

                        for(var8 = 0; var8 < this.nPersons; ++var8) {
                            if (!Double.isNaN(this.scores0[var6][var8])) {
                                var20[var6] += this.scores0[var6][var8];
                                ++var7;
                                var19 += this.scores0[var6][var8];
                                ++var5;
                            }
                        }

                        var20[var6] /= (double)var7;
                        var19 /= (double)var5;
                    }

                    double[] var21 = new double[this.nPersons];

                    int var9;
                    for(var7 = 0; var7 < this.nPersons; ++var7) {
                        var21[var7] = 0.0D;
                        var8 = 0;

                        for(var9 = 0; var9 < this.nItems; ++var9) {
                            if (!Double.isNaN(this.scores0[var9][var7])) {
                                var21[var7] += this.scores0[var9][var7];
                                ++var8;
                            }
                        }

                        var21[var7] /= (double)var8;
                    }

                    this.replacementIndices = new String[var1];
                    var7 = 0;
                    label287:
                    switch(this.replacementOption) {
                        case 1:
                            var8 = 0;

                            while(true) {
                                if (var8 >= this.nItems) {
                                    break label287;
                                }

                                for(var9 = 0; var9 < this.nPersons; ++var9) {
                                    if (Double.isNaN(this.scores0[var8][var9])) {
                                        if (this.dichotSet) {
                                            this.scores0[var8][var9] = this.dichotFalse;
                                        } else {
                                            this.scores0[var8][var9] = 0.0D;
                                        }

                                        this.replacementIndices[var7] = this.itemNames[var8] + ", " + (var9 + 1) + ";";
                                        ++var7;
                                    }
                                }

                                ++var8;
                            }
                        case 2:
                            var8 = 0;

                            while(true) {
                                if (var8 >= this.nItems) {
                                    break label287;
                                }

                                for(var9 = 0; var9 < this.nPersons; ++var9) {
                                    if (Double.isNaN(this.scores0[var8][var9])) {
                                        this.scores0[var8][var9] = var21[var8];
                                        this.replacementIndices[var7] = this.itemNames[var8] + ", " + (var9 + 1) + ";";
                                        ++var7;
                                    }
                                }

                                ++var8;
                            }
                        case 3:
                            var8 = 0;

                            while(true) {
                                if (var8 >= this.nItems) {
                                    break label287;
                                }

                                for(var9 = 0; var9 < this.nPersons; ++var9) {
                                    if (Double.isNaN(this.scores0[var8][var9])) {
                                        this.scores0[var8][var9] = var20[var8];
                                        this.replacementIndices[var7] = this.itemNames[var8] + ", " + (var9 + 1) + ";";
                                        ++var7;
                                    }
                                }

                                ++var8;
                            }
                        case 4:
                            var8 = 0;

                            while(true) {
                                if (var8 >= this.nItems) {
                                    break label287;
                                }

                                for(var9 = 0; var9 < this.nPersons; ++var9) {
                                    if (Double.isNaN(this.scores0[var8][var9])) {
                                        this.scores0[var8][var9] = var19;
                                        this.replacementIndices[var7] = this.itemNames[var8] + ", " + (var9 + 1) + ";";
                                        ++var7;
                                    }
                                }

                                ++var8;
                            }
                        case 5:
                            var8 = 0;

                            while(true) {
                                if (var8 >= this.nItems) {
                                    break label287;
                                }

                                for(var9 = 0; var9 < this.nPersons; ++var9) {
                                    if (Double.isNaN(this.scores0[var8][var9])) {
                                        String var10 = "Missing response:";
                                        String var11 = "\nItem index = " + var8 + ",    item mean = " + Fmath.truncate(var20[var8], 4);
                                        String var12 = "\nPerson index = " + var9 + ",    person's responses mean = " + Fmath.truncate(var21[var9], 4);
                                        String var13 = "\nTotal mean = " + Fmath.truncate(var19, 4);
                                        String var14 = "\nEnter the replacement value";
                                        String var15 = var10 + var11 + var12 + var13 + var14;
                                        this.scores0[var8][var9] = Db.readDouble(var15);
                                        this.replacementIndices[var7] = this.itemNames[var8] + ", " + (var9 + 1) + ";";
                                        ++var7;
                                    }
                                }

                                ++var8;
                            }
                        default:
                            throw new IllegalArgumentException("!! It should not be possible to have an option choice (replacementOption) = " + this.replacementOption);
                    }

                    this.nReplacements = var7--;
                    break;
                }

                for(var2 = 0; var2 < this.nPersons; ++var2) {
                    this.missingScores0[var1][var2] = false;
                }

                ++var1;
            }
        }

        this.scores1 = this.transpose(this.scores0);
        this.noResponseHandlingSet = true;
        if (this.dichotSet) {
            this.resetDichotomousMissingValues();
        }

    }

    public void resetDichotomousMissingValues() {
        if (!this.dataEntered) {
            throw new IllegalArgumentException("No data has been entered");
        } else {
            if (!this.noResponseHandlingSet) {
                this.noResponseHandling();
            }

            if (this.nReplacements > 0) {
                double var4;
                double var6;
                double var8;
                int var12;
                int var14;
                label104:
                switch(this.replacementOption) {
                    case 2:
                        double[][] var1 = this.transpose(this.scores0);
                        boolean[][] var2 = this.transpose(this.missingScores0);

                        for(var12 = 0; var12 < this.nPersons; ++var12) {
                            var4 = Fmath.maximum(var1[var12]);
                            var6 = Fmath.minimum(var1[var12]);
                            var8 = (var4 + var6) / 2.0D;

                            for(var14 = 0; var14 < this.nItems; ++var14) {
                                if (var2[var12][var14]) {
                                    if (var1[var12][var14] >= var8) {
                                        var1[var12][var14] = var4;
                                    } else {
                                        var1[var12][var14] = var6;
                                    }
                                }
                            }
                        }

                        this.scores0 = this.transpose(var1);
                        break;
                    case 3:
                        var12 = 0;

                        while(true) {
                            if (var12 >= this.nItems) {
                                break label104;
                            }

                            var4 = Fmath.maximum(this.scores0[var12]);
                            var6 = Fmath.minimum(this.scores0[var12]);
                            var8 = (var4 + var6) / 2.0D;

                            for(var14 = 0; var14 < this.nPersons; ++var14) {
                                if (this.missingScores0[var12][var14]) {
                                    if (this.scores0[var12][var14] >= var8) {
                                        this.scores0[var12][var14] = var4;
                                    } else {
                                        this.scores0[var12][var14] = var6;
                                    }
                                }
                            }

                            ++var12;
                        }
                    case 4:
                        double var3 = Fmath.maximum(this.scores0[0]);
                        double var5 = Fmath.minimum(this.scores0[0]);

                        for(int var7 = 1; var7 < this.nItems; ++var7) {
                            var8 = Fmath.maximum(this.scores0[var7]);
                            double var10 = Fmath.minimum(this.scores0[var7]);
                            if (var8 > var3) {
                                var3 = var8;
                            }

                            if (var10 < var5) {
                                var5 = var10;
                            }
                        }

                        double var13 = (var3 + var5) / 2.0D;

                        for(int var9 = 0; var9 < this.nItems; ++var9) {
                            for(var14 = 0; var14 < this.nPersons; ++var14) {
                                if (this.missingScores0[var9][var14]) {
                                    if (this.scores0[var9][var14] >= var13) {
                                        this.scores0[var9][var14] = var3;
                                    } else {
                                        this.scores0[var9][var14] = var5;
                                    }
                                }
                            }
                        }
                }
            }

            this.scores1 = this.transpose(this.scores0);
            this.dichotomousOverall = true;
        }
    }

    private void frequencyCount() {
        ArrayList var1 = new ArrayList();
        boolean[][] var2 = new boolean[this.nItems][this.nPersons];

        int var3;
        int var4;
        for(var3 = 0; var3 < this.nItems; ++var3) {
            for(var4 = 0; var4 < this.nPersons; ++var4) {
                if (this.scores0[var3][var4] != this.scores0[var3][var4]) {
                    var2[var3][var4] = false;
                } else {
                    var2[var3][var4] = true;
                }
            }
        }

        boolean var16 = false;

        int var7;
        for(var4 = 0; var4 < this.nItems; ++var4) {
            for(int var5 = 0; var5 < this.nPersons; ++var5) {
                byte var17 = 0;
                if (var2[var4][var5]) {
                    var2[var4][var5] = false;
                    var3 = var17 + 1;

                    for(int var6 = 0; var6 < this.nItems; ++var6) {
                        for(var7 = 0; var7 < this.nPersons; ++var7) {
                            if (var2[var6][var7] && this.scores0[var6][var7] == this.scores0[var4][var5]) {
                                ++var3;
                                var2[var6][var7] = false;
                            }
                        }
                    }

                    var1.add(new Double(this.scores0[var4][var5]));
                    var1.add(new Integer(var3));
                }
            }
        }

        var4 = var1.size() / 2;
        if (var4 > 0) {
            double[] var18 = new double[var4];
            int[] var19 = new int[var4];
            var7 = 0;

            for(int var8 = 0; var8 < var4; ++var8) {
                var18[var8] = (Double)var1.get(var7++);
                var19[var8] = (Integer)var1.get(var7++);
            }

            ArrayMaths var20 = new ArrayMaths(var19);
            var20 = var20.descendingSort();
            int[] var9 = var20.originalIndices();
            double[] var10 = new double[var4];
            int[] var11 = new int[var4];

            for(int var12 = 0; var12 < var4; ++var12) {
                var10[var12] = var18[var9[var12]];
                var11[var12] = var19[var9[var12]];
            }

            if (var10[0] < var10[1]) {
                double var21 = var10[0];
                var10[0] = var10[1];
                var10[1] = var21;
            }

            double[] var22 = new double[var4];
            var22[0] = this.dichotTrue;
            var22[1] = this.dichotFalse;

            int var13;
            for(var13 = 2; var13 < var4; ++var13) {
                var22[var13] = 0.0D / 0.0;
                this.nNaN += var11[var13];
                this.nReplacements += var11[var13];
            }

            for(var13 = 0; var13 < var4; ++var13) {
                for(int var14 = 0; var14 < this.nItems; ++var14) {
                    for(int var15 = 0; var15 < this.nPersons; ++var15) {
                        if (this.scores0[var14][var15] == var10[var13]) {
                            this.scores0[var14][var15] = var22[var13];
                        }
                    }
                }
            }
        }

    }

    protected void noResponseRequests() {
        if (!this.allNoResponseOptionsSet) {
            if (!this.ignoreNoResponseRequests) {
                String var1;
                String var2;
                String var3;
                String var4;
                String var5;
                String var6;
                String var7;
                if (this.personDeletionPercentage != 0.0D && !this.itemDeletionPercentageSet) {
                    var1 = "There are missing responses in this data set";
                    var2 = "\nYou have not set the percentage of no responses at which you will delete an item";
                    var3 = "\n(0% = item deleted if a single 'no response' present in the item)";
                    var4 = "\n(100% = item never deleted)";
                    var5 = "\nEnter the required value and click OK ";
                    var6 = "\nor simply click OK for default value";
                    var7 = var1 + var2 + var3 + var4 + var5 + var6;
                    this.itemDeletionPercentage = Db.readDouble(var7, this.itemDeletionPercentage);
                }

                this.itemDeletionPercentageSet = true;
                if (this.itemDeletionPercentage != 0.0D && !this.personDeletionPercentageSet) {
                    var1 = "There are missing responses in this data set";
                    var2 = "\nYou have not set the percentage of no responses at which you will delete a person";
                    var3 = "\n(0% = person deleted if gives a single 'no response')";
                    var4 = "\n(100% = person never deleted)";
                    var5 = "\nEnter the required value and click OK ";
                    var6 = "\nor simply click OK for default value";
                    var7 = var1 + var2 + var3 + var4 + var5 + var6;
                    this.personDeletionPercentage = Db.readDouble(var7, this.personDeletionPercentage);
                }

                this.personDeletionPercentageSet = true;
                if (this.itemDeletionPercentage != 0.0D && this.personDeletionPercentage != 0.0D && !this.replacementOptionSet) {
                    var1 = "There are missing responses in this data set";
                    var2 = "\nYou have not set the option flag for replacing a missing score";
                    var3 = "\n  option = 1 - score replaced by zero or lower dichotomous pair";
                    var4 = "\n  option = 2 - score replaced by person's mean or rounded mean";
                    var5 = "\n  option = 3 - score replaced by item mean or rounded mean (default option)";
                    var6 = "\n  option = 4 - score replaced by overall mean or rounded mean";
                    var7 = "\n  option = 5 - user supplied score for each 'missing response'";
                    String var8 = "\nEnter the required value and click OK ";
                    String var9 = "\nor simply click OK for default value";
                    String var10 = var1 + var2 + var3 + var4 + var5 + var6 + var7 + var8 + var9;
                    this.replacementOption = Db.readInt(var10, this.replacementOption);
                }

                this.replacementOptionSet = true;
            }

            this.allNoResponseOptionsSet = true;
        }

    }

    public void setDenominatorToN() {
        this.nFactorOption = true;
    }

    public void setDenominatorToNminusOne() {
        this.nFactorOption = false;
    }

    public int[] deletedPersonsIndices() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (this.nDeletedPersons == 0) {
            System.out.println("Method - deletedPersonsIndices: there are no deleted persons; null returned");
            return null;
        } else {
            ArrayMaths var1 = new ArrayMaths(this.deletedPersonsIndices);
            ArrayMaths var2 = var1.plus(1);
            return var2.array_as_int();
        }
    }

    public int numberOfDeletedPersons() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        return this.nDeletedPersons;
    }

    public int[] deletedItemsIndices() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (this.nDeletedItems == 0) {
            System.out.println("Method - deletedItemsIndices: there are no deleted items; null returned");
            return null;
        } else {
            ArrayMaths var1 = new ArrayMaths(this.deletedItemsIndices);
            ArrayMaths var2 = var1.plus(1);
            return var2.array_as_int();
        }
    }

    public String[] deletedItemsNames() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (this.nDeletedItems == 0) {
            System.out.println("Method - deletedItemsIndices: there are no deleted items; null returned");
            return null;
        } else {
            String[] var1 = new String[this.nDeletedItems];

            for(int var2 = 0; var2 < this.nDeletedItems; ++var2) {
                var1[var2] = this.originalItemNames[this.deletedItemsIndices[var2]];
            }

            return var1;
        }
    }

    public int numberOfDeletedItems() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        return this.nDeletedItems;
    }

    public void readScoresAsRowPerItemA() {
        this.readFlag = 2;
        this.readScoresAsRowPerItemCore();
    }

    public void readScoresAsRowPerItemA(String var1) {
        this.readFlag = 3;
        this.inputFilename = var1;
        this.readScoresAsRowPerItemCore();
    }

    public void readScoresAsRowPerItemB() {
        this.readFlag = 4;
        this.readScoresAsRowPerItemCore();
    }

    public void readScoresAsRowPerItemB(String var1) {
        this.readFlag = 5;
        this.inputFilename = var1;
        this.readScoresAsRowPerItemCore();
    }

    public void readScoresAsRowPerItemC() {
        this.readFlag = 6;
        this.readScoresOnlyAsRowPerItemCore();
    }

    public void readScoresAsRowPerItemC(String var1) {
        this.readFlag = 7;
        this.inputFilename = var1;
        this.readScoresOnlyAsRowPerItemCore();
    }

    public void readScoresAsRowPerItemD() {
        this.readFlag = 8;
        this.readScoresOnlyAsRowPerItemCore();
    }

    public void readScoresAsRowPerItemD(String var1) {
        this.readFlag = 9;
        this.inputFilename = var1;
        this.readScoresOnlyAsRowPerItemCore();
    }

    public void readScoresAsRowPerItem() {
        this.readFlag = 0;
        this.readScoresAsRowPerItemCore();
    }

    public void readScoresAsRowPerItem(String var1) {
        this.readFlag = 1;
        this.inputFilename = var1;
        this.readScoresAsRowPerItemCore();
    }

    protected void readScoresAsRowPerItemCore() {
        if (this.readFlag == 0 || this.readFlag == 2 || this.readFlag == 4) {
            FileChooser var1 = new FileChooser();
            this.inputFilename = var1.selectFile();
            var1.close();
        }

        FileInput var7 = new FileInput(this.inputFilename);
        this.setTitle0(var7);
        int var2 = 1;
        this.nItems = var7.readInt();
        if (var7.eol()) {
            ++var2;
        }

        this.itemNames = new String[this.nItems];
        this.nPersons = var7.readInt();
        if (var7.eol()) {
            ++var2;
        }

        this.nScores = this.nItems * this.nPersons;
        int var3;
        if (this.readFlag == 4 || this.readFlag == 5) {
            this.itemNames = new String[this.nItems + 1];

            for(var3 = 0; var3 < this.nItems; ++var3) {
                this.itemNames[var3] = var7.readWord();
                if (var7.eol()) {
                    ++var2;
                }
            }

            this.itemNames[this.nItems] = "total";
            this.originalItemNames = (String[])((String[])this.itemNames.clone());
            this.itemNamesSet = true;
        }

        this.personNames = new String[this.nPersons + 1];

        for(var3 = 0; var3 < this.nPersons; ++var3) {
            this.personNames[var3] = var7.readWord();
            if (var7.eol()) {
                ++var2;
            }
        }

        this.personNames[this.nPersons] = "total";
        this.originalPersonNames = (String[])((String[])this.personNames.clone());
        this.personNamesSet = true;
        String[][] var8 = new String[this.nItems][this.nPersons];

        for(int var4 = 0; var4 < this.nItems; ++var4) {
            int var5 = 1;
            if (this.readFlag == 2 || this.readFlag == 3) {
                this.itemNames[var4] = var7.readWord();
            }

            for(int var6 = 0; var6 < this.nPersons; ++var6) {
                var8[var4][var6] = var7.readWord();
                if (var7.eol()) {
                    if (var5 != this.nPersons) {
                        throw new IllegalArgumentException("Line " + var2 + ": the number of scores in this row, " + var5 + ", does not equal the total number of persons, " + this.nPersons);
                    }

                    ++var2;
                } else {
                    ++var5;
                }
            }
        }

        if (this.readFlag == 2 || this.readFlag == 3) {
            this.itemNamesSet = true;
        }

        var7.close();
        if (this.readFlag == 0 || this.readFlag == 1) {
            this.defaultItemNames();
        }

        this.storeData(var8, 1, 0);
    }

    protected void readScoresOnlyAsRowPerItemCore() {
        if (this.readFlag == 6 || this.readFlag == 8) {
            FileChooser var1 = new FileChooser();
            this.inputFilename = var1.selectFile();
            var1.close();
        }

        FileInput var8 = new FileInput(this.inputFilename);
        this.setTitle1();
        this.nItems = var8.numberOfLines();
        ArrayList var2 = new ArrayList();
        int var3;
        int var6;
        ArrayList var10;
        if (this.readFlag != 6 && this.readFlag != 7) {
            for(var3 = 0; var3 < this.nItems; ++var3) {
                var10 = new ArrayList();
                String var11 = var8.readLine();
                var6 = var11.length();
                if (var3 == 0) {
                    this.nPersons = var6;
                } else if (var6 != this.nPersons) {
                    throw new IllegalArgumentException("Line " + var3 + ": the number of scores in this row, " + var6 + ", does not equal the total number of persons, " + this.nPersons + ", calculated from the first row");
                }

                for(int var7 = 0; var7 < this.nPersons; ++var7) {
                    var10.add(String.valueOf(var11.charAt(var7)));
                }

                var2.add(var10);
            }
        } else {
            for(var3 = 0; var3 < this.nItems; ++var3) {
                boolean var4 = true;
                ArrayList var5 = new ArrayList();

                while(var4) {
                    var5.add(var8.readWord());
                    if (var8.eol()) {
                        var4 = false;
                        var6 = var5.size();
                        if (var3 == 0) {
                            this.nPersons = var6;
                            this.originalData = new String[this.nItems][this.nPersons];
                        } else if (var6 != this.nPersons) {
                            throw new IllegalArgumentException("Line " + var3 + ": the number of scores in this row, " + var6 + ", does not equal the total number of persons, " + this.nPersons + ", calculated from the first row");
                        }

                        var2.add(var5);
                    }
                }
            }
        }

        String[][] var9 = new String[this.nItems][this.nPersons];
        new ArrayList();

        for(int var12 = 0; var12 < this.nItems; ++var12) {
            var10 = (ArrayList)var2.get(var12);

            for(var6 = 0; var6 < this.nPersons; ++var6) {
                var9[var12][var6] = (String)var10.get(var6);
            }
        }

        this.defaultPersonNames();
        this.defaultItemNames();
        this.storeData(var9, 1, 0);
    }

    protected void setTitle0(FileInput var1) {
        this.title = new String[3];
        this.titleLines = 3;
        this.title[0] = var1.readLine();
        this.title[1] = "Data read from file: " + this.inputFilename;
        Date var2 = new Date();
        String var3 = DateFormat.getDateInstance().format(var2);
        String var4 = DateFormat.getTimeInstance().format(var2);
        this.title[2] = "Program execution initiated at " + var4 + " on " + var3;
    }

    protected void setTitle1() {
        this.title = new String[2];
        this.title[0] = "Untitled Scores Analysis";
        Date var1 = new Date();
        String var2 = DateFormat.getDateInstance().format(var1);
        String var3 = DateFormat.getTimeInstance().format(var1);
        this.title[1] = "Program execution initiated at " + var3 + " on " + var2;
    }

    protected void storeData(Object var1, int var2, int var3) {
        this.originalData = var1;
        this.originalDataType = var2;
        this.originalDataOrder = var3;
        this.dataEntered = true;
    }

    public void enterScoresAsRowPerItem(String[][] var1) {
        this.nItems = var1.length;
        this.nPersons = var1[0].length;
        this.nScores = this.nItems * this.nPersons;
        if (this.title == null) {
            this.setTitle1();
        }

        this.defaultPersonNames();
        this.defaultItemNames();
        this.storeData(Conv.copy(var1), 1, 0);
    }

    public void enterScoresAsRowPerItem(double[][] var1) {
        this.nItems = var1.length;
        this.nPersons = var1[0].length;
        this.nScores = this.nItems * this.nPersons;
        if (this.title == null) {
            this.setTitle1();
        }

        this.defaultPersonNames();
        this.defaultItemNames();
        this.storeData(Conv.copy(var1), 2, 0);
    }

    public void enterScoresAsRowPerItem(Matrix var1) {
        double[][] var2 = var1.getArrayCopy();
        this.nItems = var2.length;
        this.nPersons = var2[0].length;
        this.nScores = this.nItems * this.nPersons;
        if (this.title == null) {
            this.setTitle1();
        }

        this.defaultPersonNames();
        this.defaultItemNames();
        this.storeData(Conv.copy(var1), 3, 0);
        this.originalData = var1.copy();
    }

    public void enterScoresAsRowPerItem(float[][] var1) {
        this.nItems = var1.length;
        this.nPersons = var1[0].length;
        this.nScores = this.nItems * this.nPersons;
        if (this.title == null) {
            this.setTitle1();
        }

        this.defaultPersonNames();
        this.defaultItemNames();
        this.storeData(Conv.copy(var1), 4, 0);
    }

    public void enterScoresAsRowPerItem(int[][] var1) {
        this.nItems = var1.length;
        this.nPersons = var1[0].length;
        this.nScores = this.nItems * this.nPersons;
        if (this.title == null) {
            this.setTitle1();
        }

        this.defaultPersonNames();
        this.defaultItemNames();
        this.storeData(Conv.copy(var1), 5, 0);
    }

    public void enterScoresAsRowPerItem(char[][] var1) {
        this.nItems = var1.length;
        this.nPersons = var1[0].length;
        this.nScores = this.nItems * this.nPersons;
        if (this.title == null) {
            this.setTitle1();
        }

        this.defaultPersonNames();
        this.defaultItemNames();
        this.storeData(Conv.copy(var1), 6, 0);
    }

    public void enterScoresAsRowPerItem(boolean[][] var1) {
        this.nItems = var1.length;
        this.nPersons = var1[0].length;
        this.nScores = this.nItems * this.nPersons;
        this.dichotomous = new boolean[this.nItems];
        if (this.title == null) {
            this.setTitle1();
        }

        this.defaultPersonNames();
        this.defaultItemNames();
        this.storeData(Conv.copy(var1), 7, 0);
    }

    public void readScoresAsRowPerPersonA() {
        this.readFlag = 2;
        this.readScoresAsRowPerPersonCore();
    }

    public void readScoresAsRowPerPersonA(String var1) {
        this.readFlag = 3;
        this.inputFilename = var1;
        this.readScoresAsRowPerPersonCore();
    }

    public void readScoresAsRowPerPersonB() {
        this.readFlag = 4;
        this.readScoresAsRowPerPersonCore();
    }

    public void readScoresAsRowPerPersonB(String var1) {
        this.readFlag = 5;
        this.inputFilename = var1;
        this.readScoresAsRowPerPersonCore();
    }

    public void readScoresAsRowPerPersonC() {
        this.readFlag = 6;
        this.readScoresOnlyAsRowPerPersonCore();
    }

    public void readScoresAsRowPerPersonC(String var1) {
        this.readFlag = 7;
        this.inputFilename = var1;
        this.readScoresOnlyAsRowPerPersonCore();
    }

    public void readScoresAsRowPerPersonD() {
        this.readFlag = 8;
        this.readScoresOnlyAsRowPerPersonCore();
    }

    public void readScoresAsRowPerPersonD(String var1) {
        this.readFlag = 9;
        this.inputFilename = var1;
        this.readScoresOnlyAsRowPerPersonCore();
    }

    public void readScoresAsRowPerPerson() {
        this.readFlag = 0;
        this.readScoresAsRowPerPersonCore();
    }

    public void readScoresAsRowPerPerson(String var1) {
        this.readFlag = 1;
        this.inputFilename = var1;
        this.readScoresAsRowPerPersonCore();
    }

    protected void readScoresAsRowPerPersonCore() {
        if (this.readFlag == 0 || this.readFlag == 2 || this.readFlag == 4) {
            FileChooser var1 = new FileChooser();
            this.inputFilename = var1.selectFile();
            var1.close();
        }

        FileInput var7 = new FileInput(this.inputFilename);
        this.setTitle0(var7);
        int var2 = 1;
        this.nItems = var7.readInt();
        if (var7.eol()) {
            ++var2;
        }

        this.nPersons = var7.readInt();
        if (var7.eol()) {
            ++var2;
        }

        this.nScores = this.nItems * this.nPersons;
        this.itemNames = new String[this.nItems + 1];

        int var3;
        for(var3 = 0; var3 < this.nItems; ++var3) {
            this.itemNames[var3] = var7.readWord();
            if (var7.eol()) {
                ++var2;
            }
        }

        this.itemNames[this.nItems] = "total";
        this.originalItemNames = this.itemNames;
        this.itemNamesSet = true;
        if (this.readFlag == 4 || this.readFlag == 5) {
            this.personNames = new String[this.nPersons + 1];

            for(var3 = 0; var3 < this.nPersons; ++var3) {
                this.personNames[var3] = var7.readWord();
                if (var7.eol()) {
                    ++var2;
                }
            }

            this.personNames[this.nPersons] = "total";
            this.originalPersonNames = (String[])((String[])this.personNames.clone());
            this.personNamesSet = true;
        }

        String[][] var8 = new String[this.nPersons][this.nItems];
        this.personNames = new String[this.nPersons];

        for(int var4 = 0; var4 < this.nPersons; ++var4) {
            int var5 = 1;
            if (this.readFlag == 2 || this.readFlag == 3) {
                this.personNames[var4] = var7.readWord();
            }

            for(int var6 = 0; var6 < this.nItems; ++var6) {
                var8[var4][var6] = var7.readWord();
                if (var7.eol()) {
                    if (var5 != this.nItems) {
                        throw new IllegalArgumentException("Line " + var2 + ": the number of scores in this row, " + var5 + ", does not equal the total number of items, " + this.nItems);
                    }

                    ++var2;
                } else {
                    ++var5;
                }
            }
        }

        if (this.readFlag == 2 || this.readFlag == 3) {
            this.personNamesSet = true;
        }

        var7.close();
        if (this.readFlag == 0 || this.readFlag == 1) {
            this.defaultPersonNames();
        }

        this.storeData(var8, 1, 1);
    }

    protected void readScoresOnlyAsRowPerPersonCore() {
        if (this.readFlag == 6 || this.readFlag == 8) {
            FileChooser var1 = new FileChooser();
            this.inputFilename = var1.selectFile();
            var1.close();
        }

        FileInput var8 = new FileInput(this.inputFilename);
        this.setTitle1();
        this.nPersons = var8.numberOfLines();
        ArrayList var2 = new ArrayList();
        int var3;
        int var6;
        ArrayList var10;
        if (this.readFlag != 6 && this.readFlag != 7) {
            for(var3 = 0; var3 < this.nPersons; ++var3) {
                var10 = new ArrayList();
                String var11 = var8.readLine();
                var6 = var11.length();
                if (var3 == 0) {
                    this.nItems = var6;
                } else if (var6 != this.nItems) {
                    throw new IllegalArgumentException("Line " + var3 + ": the number of scores in this row, " + var6 + ", does not equal the total number of persons, " + this.nPersons + ", calculated from the first row");
                }

                for(int var7 = 0; var7 < this.nItems; ++var7) {
                    var10.add(String.valueOf(var11.charAt(var7)));
                }

                var2.add(var10);
            }
        } else {
            for(var3 = 0; var3 < this.nPersons; ++var3) {
                boolean var4 = true;
                ArrayList var5 = new ArrayList();

                while(var4) {
                    var5.add(var8.readWord());
                    if (var8.eol()) {
                        var4 = false;
                        var6 = var5.size();
                        if (var3 == 0) {
                            this.nItems = var6;
                            this.originalData = new String[this.nPersons][this.nItems];
                        } else if (var6 != this.nItems) {
                            throw new IllegalArgumentException("Line " + var3 + ": the number of scores in this row, " + var6 + ", does not equal the total number of persons, " + this.nPersons + ", calculated from the first row (line 0)");
                        }

                        var2.add(var5);
                    }
                }
            }
        }

        String[][] var9 = new String[this.nPersons][this.nItems];
        new ArrayList();

        for(int var12 = 0; var12 < this.nPersons; ++var12) {
            var10 = (ArrayList)var2.get(var12);

            for(var6 = 0; var6 < this.nItems; ++var6) {
                var9[var12][var6] = (String)var10.get(var6);
            }
        }

        this.defaultPersonNames();
        this.defaultItemNames();
        this.storeData(var9, 1, 1);
    }

    public void enterScoresAsRowPerPerson(String[][] var1) {
        this.nPersons = var1.length;
        this.nItems = var1[0].length;
        this.nScores = this.nItems * this.nPersons;
        if (this.title == null) {
            this.setTitle1();
        }

        this.storeData(Conv.copy(var1), 1, 1);
        this.defaultPersonNames();
        this.defaultItemNames();
    }

    public void enterScoresAsRowPerIperson(String[][] var1) {
        this.enterScoresAsRowPerPerson(var1);
    }

    public void enterScoresAsRowPerPerson(double[][] var1) {
        this.nPersons = var1.length;
        this.nItems = var1[0].length;
        this.nScores = this.nItems * this.nPersons;
        if (this.title == null) {
            this.setTitle1();
        }

        this.storeData(Conv.copy(var1), 2, 1);
        this.defaultPersonNames();
        this.defaultItemNames();
    }

    public void enterScoresAsRowPerPerson(Matrix var1) {
        double[][] var2 = var1.getArrayCopy();
        this.nPersons = var2.length;
        this.nItems = var2[0].length;
        this.nScores = this.nItems * this.nPersons;
        if (this.title == null) {
            this.setTitle1();
        }

        this.storeData(Conv.copy(var1), 3, 1);
        this.defaultPersonNames();
        this.defaultItemNames();
    }

    public void enterScoresAsRowPerPerson(float[][] var1) {
        this.nPersons = var1.length;
        this.nItems = var1[0].length;
        this.nScores = this.nItems * this.nPersons;
        if (this.title == null) {
            this.setTitle1();
        }

        this.storeData(Conv.copy(var1), 4, 1);
        this.defaultPersonNames();
        this.defaultItemNames();
    }

    public void enterScoresAsRowPerPerson(int[][] var1) {
        this.nPersons = var1.length;
        this.nItems = var1[0].length;
        this.nScores = this.nItems * this.nPersons;
        if (this.title == null) {
            this.setTitle1();
        }

        this.storeData(Conv.copy(var1), 5, 1);
        this.defaultPersonNames();
        this.defaultItemNames();
    }

    public void enterScoresAsRowPerPerson(char[][] var1) {
        this.nPersons = var1.length;
        this.nItems = var1[0].length;
        this.nScores = this.nItems * this.nPersons;
        if (this.title == null) {
            this.setTitle1();
        }

        this.storeData(Conv.copy(var1), 6, 1);
        this.defaultPersonNames();
        this.defaultItemNames();
    }

    public void enterScoresAsRowPerPerson(boolean[][] var1) {
        this.nPersons = var1.length;
        this.nItems = var1[0].length;
        this.nScores = this.nItems * this.nPersons;
        if (this.title == null) {
            this.setTitle1();
        }

        this.storeData(Conv.copy(var1), 7, 1);
        this.defaultPersonNames();
        this.defaultItemNames();
    }

    public void enterItemNames(String[] var1) {
        int var2 = var1.length;
        this.itemNames = new String[var2 + 1];

        for(int var3 = 0; var3 < var2; ++var3) {
            this.itemNames[var3] = var1[var3];
        }

        this.itemNames[var2] = "total";
        this.originalItemNames = (String[])((String[])this.itemNames.clone());
        this.itemNamesSet = true;
    }

    private void defaultItemNames() {
        if (!this.itemNamesSet) {
            this.itemNames = new String[this.nItems + 1];

            for(int var1 = 0; var1 < this.nItems; ++var1) {
                this.itemNames[var1] = "item" + var1;
            }

            this.itemNames[this.nItems] = "total";
            this.originalItemNames = (String[])((String[])this.itemNames.clone());
        }

    }

    public void enterPersonNames(String[] var1) {
        int var2 = var1.length;
        this.personNames = new String[var2 + 1];

        for(int var3 = 0; var3 < var2; ++var3) {
            this.personNames[var3] = var1[var3];
        }

        this.personNames[var2] = "total";
        this.originalPersonNames = (String[])((String[])this.personNames.clone());
        this.personNamesSet = true;
    }

    private void defaultPersonNames() {
        if (!this.personNamesSet) {
            this.personNames = new String[this.nPersons + 1];

            for(int var1 = 0; var1 < this.nPersons; ++var1) {
                this.personNames[var1] = "person" + var1;
            }

            this.personNames[this.nPersons] = "total";
            this.originalPersonNames = (String[])((String[])this.personNames.clone());
        }

    }

    public void letterToNumeral() {
        this.letterToNumeralSet = true;
    }

    public void suspendLetterToNumeral() {
        this.letterToNumeralSet = false;
    }

    public void declareDataDichotomous() {
        this.dichotSet = true;
    }

    public void additionalDichotomousPairs(String var1, String var2) {
        this.otherFalse = var1;
        this.otherTrue = var2;
        this.otherDichotomousDataSet = true;
    }

    public void otherDichotomousData(String var1, String var2) {
        this.otherFalse = var1;
        this.otherTrue = var2;
        this.otherDichotomousDataSet = true;
    }

    public void resetDichotomousNoFalse(double var1) {
        this.dichotFalse = var1;
    }

    public void resetDichotomousYesTrue(double var1) {
        this.dichotTrue = var1;
    }

    public double getDichotomousNoFalse() {
        return this.dichotFalse;
    }

    public double getDichotomousYesTrue() {
        return this.dichotTrue;
    }

    protected double[] checkWhetherRawItemsDichotomous() {
        if (!this.dichotomousCheckDone) {
            this.dichotomous = new boolean[this.nItems];
            this.dichotomousPercentage = new double[this.nItems];
            int var1 = 0;

            for(int var2 = 0; var2 < this.nItems; ++var2) {
                this.dichotomousPercentage[var2] = this.checkWhetherDichotomous(this.scores0[var2]);
                if (this.dichotomousPercentage[var2] == 100.0D) {
                    this.dichotomous[var2] = true;
                    ++var1;
                }
            }

            if (var1 == this.nItems) {
                this.dichotomousOverall = true;
            }

            this.dichotomousCheckDone = false;
        }

        return this.dichotomousPercentage;
    }

    protected double checkWhetherDichotomous(double[] var1) {
        int var2 = var1.length;
        double[] var3 = new double[var2];
        boolean[] var4 = new boolean[var2];

        int var5;
        for(var5 = 0; var5 < var2; ++var5) {
            var3[var5] = 0.0D;
            var4[var5] = false;
        }

        for(var5 = 0; var5 < var2; ++var5) {
            var3[var5] = 0.0D;

            for(int var6 = 0; var6 < var2; ++var6) {
                if (var1[var5] == var1[var6] && !var4[var6]) {
                    ++var3[var5];
                    var4[var6] = true;
                }
            }
        }

        ArrayMaths var10 = new ArrayMaths(var3);
        ArrayMaths var11 = var10.sort();
        double[] var7 = var11.array();
        double var8 = (var7[var2 - 1] + var7[var2 - 2]) * 100.0D / (double)var2;
        return var8;
    }

    protected double[][] transpose(double[][] var1) {
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

    protected String[][] transpose(String[][] var1) {
        int var2 = var1.length;
        int var3 = var1[0].length;
        String[][] var4 = new String[var3][var2];

        for(int var5 = 0; var5 < var2; ++var5) {
            for(int var6 = 0; var6 < var3; ++var6) {
                var4[var6][var5] = var1[var5][var6];
            }
        }

        return var4;
    }

    protected boolean[][] transpose(boolean[][] var1) {
        int var2 = var1.length;
        int var3 = var1[0].length;
        boolean[][] var4 = new boolean[var3][var2];

        for(int var5 = 0; var5 < var2; ++var5) {
            for(int var6 = 0; var6 < var3; ++var6) {
                var4[var6][var5] = var1[var5][var6];
            }
        }

        return var4;
    }

    protected void checkLengths(String[][] var1) {
        int var2 = var1.length;
        int var3 = var1[0].length;

        for(int var4 = 1; var4 < var2; ++var4) {
            if (var1[var4].length != var3) {
                throw new IllegalArgumentException("The length of each item and of each person's responses must be identical (missing responses must be included - see documentation web page)");
            }
        }

    }

    protected void checkLengths(double[][] var1) {
        int var2 = var1.length;
        int var3 = var1[0].length;

        for(int var4 = 1; var4 < var2; ++var4) {
            if (var1[var4].length != var3) {
                throw new IllegalArgumentException("The length of each item and of each person's responses must be identical (missing responses must be included - see documentation web page)");
            }
        }

    }

    protected void checkLengths(char[][] var1) {
        int var2 = var1.length;
        int var3 = var1[0].length;

        for(int var4 = 1; var4 < var2; ++var4) {
            if (var1[var4].length != var3) {
                throw new IllegalArgumentException("The length of each item and of each person's responses must be identical (missing responses must be included - see documentation web page)");
            }
        }

    }

    protected void checkLengths(float[][] var1) {
        int var2 = var1.length;
        int var3 = var1[0].length;

        for(int var4 = 1; var4 < var2; ++var4) {
            if (var1[var4].length != var3) {
                throw new IllegalArgumentException("The length of each item and of each person's responses must be identical (missing responses must be included - see documentation web page)");
            }
        }

    }

    protected void checkLengths(int[][] var1) {
        int var2 = var1.length;
        int var3 = var1[0].length;

        for(int var4 = 1; var4 < var2; ++var4) {
            if (var1[var4].length != var3) {
                throw new IllegalArgumentException("The length of each item and of each person's responses must be identical (missing responses must be included - see documentation web page)");
            }
        }

    }

    protected void checkLengths(boolean[][] var1) {
        int var2 = var1.length;
        int var3 = var1[0].length;

        for(int var4 = 1; var4 < var2; ++var4) {
            if (var1[var4].length != var3) {
                throw new IllegalArgumentException("The length of each item and of each person's responses must be identical (missing responses must be included - see documentation web page)");
            }
        }

    }

    protected void trimScores(String[][] var1) {
        int var2 = var1.length;
        int var3 = var1[0].length;

        for(int var4 = 0; var4 < var2; ++var4) {
            for(int var5 = 0; var5 < var3; ++var5) {
                var1[var4][var5].trim();
            }
        }

    }

    public void preprocessData() {
        if (!this.dataPreprocessed) {
            if (!this.dataEntered) {
                throw new IllegalArgumentException("No data has been entered");
            }

            this.scores0 = new double[this.nItems][this.nPersons];
            this.originalScores0 = new double[this.nItems][this.nPersons];
            this.scores1 = new double[this.nPersons][this.nItems];
            this.originalScores1 = new double[this.nPersons][this.nItems];
            this.deletedPersons = new boolean[this.nPersons];
            this.deletedItems = new boolean[this.nItems];
            this.personIndices = new int[this.nPersons];

            int var1;
            for(var1 = 0; var1 < this.nPersons; this.personIndices[var1] = var1++) {
                ;
            }

            this.itemIndices = new int[this.nItems];

            for(var1 = 0; var1 < this.nItems; this.itemIndices[var1] = var1++) {
                ;
            }

            this.nNaN = 0;
            this.nDeletedPersons = 0;
            this.nDeletedItems = 0;
            String[][] var13 = (String[][])null;
            double[][] var2 = (double[][])null;
            boolean[][] var3 = (boolean[][])null;
            boolean var4 = false;
            boolean var5 = false;
            int var9;
            int var14;
            int var15;
            int var17;
            int var18;
            switch(this.originalDataType) {
                case 1:
                    var13 = (String[][])((String[][])this.originalData);
                    this.checkLengths(var13);
                    if (this.originalDataOrder == 1) {
                        var13 = this.transpose(var13);
                    }

                    this.trimScores(var13);
                    break;
                case 2:
                    var2 = (double[][])((double[][])this.originalData);
                    this.checkLengths(var2);
                    if (this.originalDataOrder == 1) {
                        var2 = this.transpose(var2);
                    }

                    var13 = this.dataToString(var2);
                    break;
                case 3:
                    var2 = ((Matrix)this.originalData).getArrayCopy();
                    this.checkLengths(var2);
                    if (this.originalDataOrder == 1) {
                        var2 = this.transpose(var2);
                    }

                    var13 = this.dataToString(var2);
                    break;
                case 4:
                    float[][] var6 = (float[][])((float[][])this.originalData);
                    this.checkLengths(var6);
                    var14 = var6.length;
                    var15 = var6[0].length;

                    for(var17 = 0; var17 < var14; ++var17) {
                        for(var18 = 0; var18 < var15; ++var18) {
                            var2[var17][var18] = (new Float(var6[var17][var18])).doubleValue();
                        }
                    }

                    if (this.originalDataOrder == 1) {
                        var2 = this.transpose(var2);
                    }

                    var13 = this.dataToString(var2);
                    break;
                case 5:
                    int[][] var7 = (int[][])((int[][])this.originalData);
                    this.checkLengths(var7);
                    var14 = var7.length;
                    var15 = var7[0].length;

                    for(var18 = 0; var18 < var14; ++var18) {
                        for(var9 = 0; var9 < var15; ++var9) {
                            var2[var18][var9] = (new Integer(var7[var18][var9])).doubleValue();
                        }
                    }

                    if (this.originalDataOrder == 1) {
                        var2 = this.transpose(var2);
                    }

                    var13 = this.dataToString(var2);
                    break;
                case 6:
                    char[][] var8 = (char[][])((char[][])this.originalData);
                    this.checkLengths(var8);
                    var14 = var8.length;
                    var15 = var8[0].length;
                    var13 = new String[var14][var15];

                    for(var9 = 0; var9 < var14; ++var9) {
                        for(int var10 = 0; var10 < var15; ++var10) {
                            var13[var9][var10] = Character.toString(var8[var9][var10]);
                        }
                    }

                    if (this.originalDataOrder == 1) {
                        var13 = this.transpose(var13);
                    }

                    this.trimScores(var13);
                    break;
                case 7:
                    var3 = (boolean[][])((boolean[][])this.originalData);
                    this.checkLengths(var3);
                    if (this.originalDataOrder == 1) {
                        var3 = this.transpose(var3);
                    }

                    var13 = this.dataToString(var3);
                    break;
                default:
                    throw new IllegalArgumentException("Original data type, " + this.originalDataType + ", not recognised");
            }

            int var16;
            if (this.letterToNumeralSet) {
                for(var16 = 0; var16 < this.nItems; ++var16) {
                    var17 = 0;

                    char var19;
                    for(var18 = 0; var18 < this.nPersons; ++var18) {
                        var19 = var13[var16][var18].charAt(0);
                        if ((var19 == 'y' || var19 == 'n' || var19 == 'Y' || var19 == 'N' || var19 == ' ') && var13[var16][var18].length() == 1) {
                            ++var17;
                        }
                    }

                    if (var17 == this.nPersons) {
                        for(var18 = 0; var18 < this.nPersons; ++var18) {
                            var19 = var13[var16][var18].charAt(0);
                            if ((var19 == 'y' || var19 == 'Y') && var13[var16][var18].length() == 1) {
                                var13[var16][var18] = String.valueOf(this.dichotTrue);
                            } else if ((var19 == 'n' || var19 == 'N') && var13[var16][var18].length() == 1) {
                                var13[var16][var18] = String.valueOf(this.dichotFalse);
                            }
                        }
                    }
                }
            }

            if (this.letterToNumeralSet) {
                var16 = 0;

                while(true) {
                    if (var16 >= this.nItems) {
                        this.letterToNumeralSet = false;
                        break;
                    }

                    for(var17 = 0; var17 < this.nPersons; ++var17) {
                        char var20 = var13[var16][var17].charAt(0);
                        if (var20 > '@' && var20 < '[' && var13[var16][var17].length() == 1) {
                            var13[var16][var17] = "" + (var20 - 63);
                        } else if (var20 > '`' && var20 < '{' && var13[var16][var17].length() == 1) {
                            var13[var16][var17] = "" + (var20 - 96);
                        }
                    }

                    ++var16;
                }
            }

            label264:
            switch(this.originalDataType) {
                case 1:
                case 6:
                    for(var16 = 0; var16 < this.nItems; ++var16) {
                        for(var17 = 0; var17 < this.nPersons; ++var17) {
                            boolean var22 = false;
                            if (this.otherDichotomousDataSet) {
                                if (var13[var16][var17].equalsIgnoreCase(this.otherTrue)) {
                                    this.scores0[var16][var17] = this.dichotTrue;
                                    var22 = true;
                                } else if (var13[var16][var17].equalsIgnoreCase(this.otherFalse)) {
                                    this.scores0[var16][var17] = this.dichotFalse;
                                    var22 = true;
                                } else {
                                    this.scores0[var16][var17] = 0.0D / 0.0;
                                    var22 = true;
                                }
                            }

                            if (!var22) {
                                if (!var13[var16][var17].equalsIgnoreCase("yes") && !var13[var16][var17].equalsIgnoreCase("y") && !var13[var16][var17].equalsIgnoreCase("true")) {
                                    if (var13[var16][var17].equalsIgnoreCase("no") || var13[var16][var17].equalsIgnoreCase("n") || var13[var16][var17].equalsIgnoreCase("false")) {
                                        this.scores0[var16][var17] = this.dichotFalse;
                                        var22 = true;
                                    }
                                } else {
                                    this.scores0[var16][var17] = this.dichotTrue;
                                    var22 = true;
                                }
                            }

                            if (!var22) {
                                try {
                                    this.scores0[var16][var17] = Double.valueOf(var13[var16][var17]);
                                } catch (Exception var12) {
                                    this.scores0[var16][var17] = 0.0D / 0.0;
                                    ++this.nNaN;
                                }
                            }
                        }
                    }
                    break;
                case 2:
                case 3:
                case 4:
                case 5:
                    var16 = 0;

                    while(true) {
                        if (var16 >= this.nItems) {
                            break label264;
                        }

                        for(var17 = 0; var17 < this.nPersons; ++var17) {
                            try {
                                this.scores0[var16][var17] = Double.valueOf(var2[var16][var17]);
                            } catch (Exception var11) {
                                this.scores0[var16][var17] = 0.0D / 0.0;
                                ++this.nNaN;
                            }
                        }

                        ++var16;
                    }
                case 7:
                    for(var16 = 0; var16 < this.nItems; ++var16) {
                        for(var17 = 0; var17 < this.nPersons; ++var17) {
                            if (var3[var16][var17]) {
                                this.scores0[var16][var17] = this.dichotTrue;
                            } else {
                                this.scores0[var16][var17] = this.dichotFalse;
                            }
                        }
                    }
            }

            var16 = 0;
            boolean var21 = false;

            for(var18 = 0; var18 < this.nItems; ++var18) {
                for(var9 = 0; var9 < this.nPersons; ++var9) {
                    var17 = Fmath.checkPrecision(this.scores0[var18][var9]);
                    if (var17 > var16) {
                        var16 = var17;
                    }
                }
            }

            if (var16 > this.trunc && !this.truncAll) {
                this.trunc = var16;
            }

            this.originalScores0 = Conv.copy(this.scores0);
            this.originalScores1 = this.transpose(this.scores0);
            this.originalNitems = this.nItems;
            this.originalNpersons = this.nPersons;
            this.originalNscores = this.originalNitems * this.originalNpersons;
            if (this.dichotSet) {
                this.frequencyCount();
            }

            if (this.nNaN > 0) {
                this.noResponseHandling();
                this.scores1 = this.transpose(this.scores0);
            }

            this.scores1 = new double[this.nPersons][this.nItems];

            for(var18 = 0; var18 < this.nItems; ++var18) {
                for(var9 = 0; var9 < this.nPersons; ++var9) {
                    this.scores1[var9][var18] = this.scores0[var18][var9];
                }
            }

            this.checkWhetherRawItemsDichotomous();
            this.standardizedScores0 = new double[this.nItems][this.nPersons];
            this.standardizedScores1 = new double[this.nPersons][this.nItems];

            for(var18 = 0; var18 < this.nItems; ++var18) {
                Stat var23 = new Stat(this.scores0[var18]);
                this.standardizedScores0[var18] = var23.standardize();
            }

            this.standardizedScores1 = this.transpose(this.standardizedScores0);
            this.checkForIdenticalElements();
            this.meansAndVariances();
            this.covariancesAndCorrelationCoefficients();
            this.dataPreprocessed = true;
        }

    }

    private void checkForIdenticalElements() {
        boolean var1 = false;

        int var2;
        int var3;
        double var4;
        int var6;
        for(var2 = 0; var2 < this.nItems; ++var2) {
            var3 = 0;
            var4 = this.scores0[var2][0];

            for(var6 = 0; var6 < this.nPersons; ++var6) {
                if (this.scores0[var2][var6] == var4) {
                    ++var3;
                }
            }

            if (var3 == this.nPersons) {
                this.sameCheck = 1;
                var1 = true;
            }
        }

        for(var2 = 0; var2 < this.nPersons; ++var2) {
            var3 = 0;
            var4 = this.scores0[0][var2];

            for(var6 = 0; var6 < this.nItems; ++var6) {
                if (this.scores0[var6][var2] == var4) {
                    ++var3;
                }
            }

            if (var3 == this.nItems) {
                this.sameCheck = 2;
                if (var1) {
                    this.sameCheck = 3;
                }
            }
        }

    }

    private String[][] dataToString(double[][] var1) {
        int var2 = var1.length;
        int var3 = var1[0].length;
        String[][] var4 = new String[var2][var3];

        for(int var5 = 0; var5 < var2; ++var5) {
            for(int var6 = 0; var6 < var3; ++var6) {
                var4[var5][var6] = (new Double(var1[var5][var6])).toString();
            }
        }

        return var4;
    }

    private String[][] dataToString(boolean[][] var1) {
        int var2 = var1.length;
        int var3 = var1[0].length;
        String[][] var4 = new String[var2][var3];

        for(int var5 = 0; var5 < var2; ++var5) {
            for(int var6 = 0; var6 < var3; ++var6) {
                var4[var5][var6] = (new Boolean(var1[var5][var6])).toString();
            }
        }

        return var4;
    }

    public Object originalResponses() {
        return this.originalScores();
    }

    public Object originalScores() {
        if (!this.dataEntered) {
            throw new IllegalArgumentException("No data has been entered");
        } else {
            return this.originalData;
        }
    }

    public double[][] originalResponsesAsRowPerPerson() {
        return this.originalScoresAsRowPerPerson();
    }

    public double[][] originalScoresAsRowPerPerson() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        return this.originalScores1;
    }

    public double[][] originalResponsesAsRowPerItem() {
        return this.originalScoresAsRowPerItem();
    }

    public double[][] originalScoresAsRowPerItem() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        return this.originalScores0;
    }

    public double[][] usedresponsesAsRowPerPerson() {
        return this.usedScoresAsRowPerPerson();
    }

    public double[][] usedScoresAsRowPerPerson() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        return this.scores1;
    }

    public double[][] usedScoresAsRowPerItem() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        return this.scores0;
    }

    public double[][] standardizedScoresAsRowPerPerson() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        return this.standardizedScores1;
    }

    public double[][] standardisedScoresAsRowPerPerson() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        return this.standardizedScores1;
    }

    public double[][] standardizedScoresAsRowPerItem() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        return this.standardizedScores0;
    }

    public double[][] standardisedScoresAsRowPerItem() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        return this.standardizedScores0;
    }

    public int originalNumberOfItems() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        return this.originalNitems;
    }

    public int originalNumberOfPersons() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        return this.originalNpersons;
    }

    public int usedNumberOfItems() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        return this.nItems;
    }

    public int usedNumberOfPersons() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        return this.nPersons;
    }

    public int originalTotalNumberOfScores() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        return this.originalNscores;
    }

    public int usedTotalNumberOfScores() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        return this.nScores;
    }

    public int numberOfDeletedScores() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        return this.originalNscores - this.nScores;
    }

    public int numberOfReplacedScores() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        return this.nReplacements;
    }

    public String[] indicesOfReplacedScores() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        return this.replacementIndices;
    }

    public String[] personNames() {
        if (!this.dataEntered) {
            throw new IllegalArgumentException("no data has been entered");
        } else {
            String[] var1 = new String[this.nPersons];

            for(int var2 = 0; var2 < this.nPersons; ++var2) {
                var1[var2] = this.personNames[var2];
            }

            return var1;
        }
    }

    public String[] originalPersonNames() {
        if (!this.dataEntered) {
            throw new IllegalArgumentException("no data has been entered");
        } else {
            String[] var1 = new String[this.originalNpersons];

            for(int var2 = 0; var2 < this.originalNpersons; ++var2) {
                var1[var2] = this.originalPersonNames[var2];
            }

            return var1;
        }
    }

    public String[] itemNames() {
        if (!this.dataEntered) {
            throw new IllegalArgumentException("no data has been entered");
        } else {
            String[] var1 = new String[this.nItems];

            for(int var2 = 0; var2 < this.nItems; ++var2) {
                var1[var2] = this.itemNames[var2];
            }

            return var1;
        }
    }

    public String[] originalItemNames() {
        if (!this.dataEntered) {
            throw new IllegalArgumentException("no data has been entered");
        } else {
            String[] var1 = new String[this.originalNitems];

            for(int var2 = 0; var2 < this.originalNitems; ++var2) {
                var1[var2] = this.originalItemNames[var2];
            }

            return var1;
        }
    }

    public int itemIndex(String var1) {
        if (!this.dataEntered) {
            throw new IllegalArgumentException("no data has been entered");
        } else {
            int var2 = -1;
            int var3 = 0;
            boolean var4 = true;

            while(var4) {
                if (var1.trim().equalsIgnoreCase(this.itemNames[var3].trim())) {
                    var2 = var3;
                    var4 = false;
                } else {
                    ++var3;
                    if (var3 > this.nItems) {
                        throw new IllegalArgumentException("Item name, " + var1 + ", is not present in the list of entered item names");
                    }
                }
            }

            return var2 + 1;
        }
    }

    public String itemName(int var1) {
        if (!this.dataEntered) {
            throw new IllegalArgumentException("no data has been entered");
        } else {
            return this.itemNames[var1 - 1];
        }
    }

    protected void meansAndVariances() {
        this.rawItemMeans = new double[this.nItems];
        this.rawItemMedians = new double[this.nItems];
        this.rawItemStandardDeviations = new double[this.nItems];
        this.rawItemVariances = new double[this.nItems];
        this.rawItemMinima = new double[this.nItems];
        this.rawItemMaxima = new double[this.nItems];
        this.rawItemRanges = new double[this.nItems];
        this.rawItemTotals = new double[this.nItems];
        this.rawItemMomentSkewness = new double[this.nItems];
        this.rawItemMedianSkewness = new double[this.nItems];
        this.rawItemQuartileSkewness = new double[this.nItems];
        this.rawItemKurtosisExcess = new double[this.nItems];

        for(int var1 = 0; var1 < this.nItems; ++var1) {
            Stat var2 = new Stat(this.scores0[var1]);
            if (this.nFactorOption) {
                var2.setDenominatorToN();
            } else {
                var2.setDenominatorToNminusOne();
            }

            this.rawItemMeans[var1] = var2.mean_as_double();
            this.rawItemVariances[var1] = var2.variance_as_double();
            this.rawItemStandardDeviations[var1] = Math.sqrt(this.rawItemVariances[var1]);
            this.rawItemMinima[var1] = var2.minimum_as_double();
            this.rawItemMaxima[var1] = var2.maximum_as_double();
            this.rawItemRanges[var1] = this.rawItemMaxima[var1] - this.rawItemMinima[var1];
            this.rawItemTotals[var1] = var2.sum_as_double();
            ArrayMaths var3 = var2.sort();
            Stat var4 = new Stat(var3.array());
            this.rawItemMedians[var1] = var4.median_as_double();
            this.rawItemMomentSkewness[var1] = var2.momentSkewness_as_double();
            this.rawItemMedianSkewness[var1] = var2.medianSkewness_as_double();
            this.rawItemQuartileSkewness[var1] = var2.quartileSkewness_as_double();
            this.rawItemKurtosisExcess[var1] = var2.kurtosisExcess_as_double();
        }

        Stat var8 = new Stat(this.rawItemMeans);
        if (this.nFactorOption) {
            var8.setDenominatorToN();
        } else {
            var8.setDenominatorToNminusOne();
        }

        this.rawItemMeansMean = var8.mean_as_double();
        this.rawItemMeansVar = var8.variance_as_double();
        this.rawItemMeansSd = Math.sqrt(this.rawItemMeansVar);
        this.rawItemMeansMin = var8.minimum_as_double();
        this.rawItemMeansMax = var8.maximum_as_double();
        this.rawItemMeansRange = this.rawItemMeansMax - this.rawItemMeansMin;
        var8 = new Stat(this.rawItemStandardDeviations);
        if (this.nFactorOption) {
            var8.setDenominatorToN();
        } else {
            var8.setDenominatorToNminusOne();
        }

        this.rawItemStandardDeviationsMean = var8.mean_as_double();
        this.rawItemStandardDeviationsVar = var8.variance_as_double();
        this.rawItemStandardDeviationsSd = Math.sqrt(this.rawItemStandardDeviationsVar);
        this.rawItemStandardDeviationsMin = var8.minimum_as_double();
        this.rawItemStandardDeviationsMax = var8.maximum_as_double();
        this.rawItemStandardDeviationsRange = this.rawItemStandardDeviationsMax - this.rawItemStandardDeviationsMin;
        var8 = new Stat(this.rawItemVariances);
        if (this.nFactorOption) {
            var8.setDenominatorToN();
        } else {
            var8.setDenominatorToNminusOne();
        }

        this.rawItemVariancesMean = var8.mean_as_double();
        this.rawItemVariancesVar = var8.variance_as_double();
        this.rawItemVariancesSd = Math.sqrt(this.rawItemVariancesVar);
        this.rawItemVariancesMin = var8.minimum_as_double();
        this.rawItemVariancesMax = var8.maximum_as_double();
        this.rawItemVariancesRange = this.rawItemVariancesMax - this.rawItemVariancesMin;
        var8 = new Stat(this.rawItemMinima);
        if (this.nFactorOption) {
            var8.setDenominatorToN();
        } else {
            var8.setDenominatorToNminusOne();
        }

        this.rawItemMinimaMean = var8.mean_as_double();
        this.rawItemMinimaVar = var8.variance_as_double();
        this.rawItemMinimaSd = Math.sqrt(this.rawItemMinimaVar);
        this.rawItemMinimaMin = var8.minimum_as_double();
        this.rawItemMinimaMax = var8.maximum_as_double();
        this.rawItemMinimaRange = this.rawItemMinimaMax - this.rawItemMinimaMin;
        var8 = new Stat(this.rawItemMaxima);
        if (this.nFactorOption) {
            var8.setDenominatorToN();
        } else {
            var8.setDenominatorToNminusOne();
        }

        this.rawItemMaximaMean = var8.mean_as_double();
        this.rawItemMaximaVar = var8.variance_as_double();
        this.rawItemMaximaSd = Math.sqrt(this.rawItemMaximaVar);
        this.rawItemMaximaMin = var8.minimum_as_double();
        this.rawItemMaximaMax = var8.maximum_as_double();
        this.rawItemMaximaRange = this.rawItemMaximaMax - this.rawItemMaximaMin;
        var8 = new Stat(this.rawItemRanges);
        if (this.nFactorOption) {
            var8.setDenominatorToN();
        } else {
            var8.setDenominatorToNminusOne();
        }

        this.rawItemRangesMean = var8.mean_as_double();
        this.rawItemRangesVar = var8.variance_as_double();
        this.rawItemRangesSd = Math.sqrt(this.rawItemRangesVar);
        this.rawItemRangesMin = var8.minimum_as_double();
        this.rawItemRangesMax = var8.maximum_as_double();
        this.rawItemRangesRange = this.rawItemRangesMax - this.rawItemRangesMin;
        var8 = new Stat(this.rawItemTotals);
        if (this.nFactorOption) {
            var8.setDenominatorToN();
        } else {
            var8.setDenominatorToNminusOne();
        }

        this.rawItemTotalsMean = var8.mean_as_double();
        this.rawItemTotalsVar = var8.variance_as_double();
        this.rawItemTotalsSd = Math.sqrt(this.rawItemTotalsVar);
        this.rawItemTotalsMin = var8.minimum_as_double();
        this.rawItemTotalsMax = var8.maximum_as_double();
        this.rawItemTotalsRange = this.rawItemTotalsMax - this.rawItemTotalsMin;
        var8 = new Stat(this.rawItemMedians);
        if (this.nFactorOption) {
            var8.setDenominatorToN();
        } else {
            var8.setDenominatorToNminusOne();
        }

        this.rawItemMediansMean = var8.mean_as_double();
        this.rawItemMediansVar = var8.variance_as_double();
        this.rawItemMediansSd = Math.sqrt(this.rawItemMediansVar);
        this.rawItemMediansMin = var8.minimum_as_double();
        this.rawItemMediansMax = var8.maximum_as_double();
        this.rawItemMediansRange = this.rawItemMediansMax - this.rawItemMediansMin;
        this.standardizedItemMeans = new double[this.nItems];
        this.standardizedItemMedians = new double[this.nItems];
        this.standardizedItemStandardDeviations = new double[this.nItems];
        this.standardizedItemVariances = new double[this.nItems];
        this.standardizedItemMinima = new double[this.nItems];
        this.standardizedItemMaxima = new double[this.nItems];
        this.standardizedItemRanges = new double[this.nItems];
        this.standardizedItemTotals = new double[this.nItems];
        this.standardizedItemMomentSkewness = new double[this.nItems];
        this.standardizedItemMedianSkewness = new double[this.nItems];
        this.standardizedItemQuartileSkewness = new double[this.nItems];
        this.standardizedItemKurtosisExcess = new double[this.nItems];

        Stat var5;
        ArrayMaths var13;
        for(int var9 = 0; var9 < this.nItems; ++var9) {
            Stat var11 = new Stat(this.standardizedScores0[var9]);
            if (this.nFactorOption) {
                var11.setDenominatorToN();
            } else {
                var11.setDenominatorToNminusOne();
            }

            this.standardizedItemMeans[var9] = 0.0D;
            this.standardizedItemVariances[var9] = 1.0D;
            this.standardizedItemStandardDeviations[var9] = 1.0D;
            this.standardizedItemMinima[var9] = var11.minimum_as_double();
            this.standardizedItemMaxima[var9] = var11.maximum_as_double();
            this.standardizedItemRanges[var9] = this.standardizedItemMaxima[var9] - this.standardizedItemMinima[var9];
            this.standardizedItemTotals[var9] = 0.0D;
            var13 = var11.sort();
            var5 = new Stat(var13.array());
            this.standardizedItemMedians[var9] = var5.median_as_double();
            this.standardizedItemMomentSkewness[var9] = var11.momentSkewness_as_double();
            this.standardizedItemMedianSkewness[var9] = var11.medianSkewness_as_double();
            this.standardizedItemQuartileSkewness[var9] = var11.quartileSkewness_as_double();
            this.standardizedItemKurtosisExcess[var9] = var11.kurtosisExcess_as_double();
        }

        var8 = new Stat(this.standardizedItemMeans);
        if (this.nFactorOption) {
            var8.setDenominatorToN();
        } else {
            var8.setDenominatorToNminusOne();
        }

        this.standardizedItemMeansMean = var8.mean_as_double();
        this.standardizedItemMeansVar = var8.variance_as_double();
        this.standardizedItemMeansSd = Math.sqrt(this.standardizedItemMeansVar);
        this.standardizedItemMeansMin = var8.minimum_as_double();
        this.standardizedItemMeansMax = var8.maximum_as_double();
        this.standardizedItemMeansRange = this.standardizedItemMeansMax - this.standardizedItemMeansMin;
        var8 = new Stat(this.standardizedItemStandardDeviations);
        if (this.nFactorOption) {
            var8.setDenominatorToN();
        } else {
            var8.setDenominatorToNminusOne();
        }

        this.standardizedItemStandardDeviationsMean = var8.mean_as_double();
        this.standardizedItemStandardDeviationsVar = var8.variance_as_double();
        this.standardizedItemStandardDeviationsSd = Math.sqrt(this.standardizedItemStandardDeviationsVar);
        this.standardizedItemStandardDeviationsMin = var8.minimum_as_double();
        this.standardizedItemStandardDeviationsMax = var8.maximum_as_double();
        this.standardizedItemStandardDeviationsRange = this.standardizedItemStandardDeviationsMax - this.standardizedItemStandardDeviationsMin;
        var8 = new Stat(this.standardizedItemVariances);
        if (this.nFactorOption) {
            var8.setDenominatorToN();
        } else {
            var8.setDenominatorToNminusOne();
        }

        this.standardizedItemVariancesMean = var8.mean_as_double();
        this.standardizedItemVariancesVar = var8.variance_as_double();
        this.standardizedItemVariancesSd = Math.sqrt(this.standardizedItemVariancesVar);
        this.standardizedItemVariancesMin = var8.minimum_as_double();
        this.standardizedItemVariancesMax = var8.maximum_as_double();
        this.standardizedItemVariancesRange = this.standardizedItemVariancesMax - this.standardizedItemVariancesMin;
        var8 = new Stat(this.standardizedItemMinima);
        if (this.nFactorOption) {
            var8.setDenominatorToN();
        } else {
            var8.setDenominatorToNminusOne();
        }

        this.standardizedItemMinimaMean = var8.mean_as_double();
        this.standardizedItemMinimaVar = var8.variance_as_double();
        this.standardizedItemMinimaSd = Math.sqrt(this.standardizedItemMinimaVar);
        this.standardizedItemMinimaMin = var8.minimum_as_double();
        this.standardizedItemMinimaMax = var8.maximum_as_double();
        this.standardizedItemMinimaRange = this.standardizedItemMinimaMax - this.standardizedItemMinimaMin;
        var8 = new Stat(this.standardizedItemMaxima);
        if (this.nFactorOption) {
            var8.setDenominatorToN();
        } else {
            var8.setDenominatorToNminusOne();
        }

        this.standardizedItemMaximaMean = var8.mean_as_double();
        this.standardizedItemMaximaVar = var8.variance_as_double();
        this.standardizedItemMaximaSd = Math.sqrt(this.standardizedItemMaximaVar);
        this.standardizedItemMaximaMin = var8.minimum_as_double();
        this.standardizedItemMaximaMax = var8.maximum_as_double();
        this.standardizedItemMaximaRange = this.standardizedItemMaximaMax - this.standardizedItemMaximaMin;
        var8 = new Stat(this.standardizedItemRanges);
        if (this.nFactorOption) {
            var8.setDenominatorToN();
        } else {
            var8.setDenominatorToNminusOne();
        }

        this.standardizedItemRangesMean = var8.mean_as_double();
        this.standardizedItemRangesVar = var8.variance_as_double();
        this.standardizedItemRangesSd = Math.sqrt(this.standardizedItemRangesVar);
        this.standardizedItemRangesMin = var8.minimum_as_double();
        this.standardizedItemRangesMax = var8.maximum_as_double();
        this.standardizedItemRangesRange = this.standardizedItemRangesMax - this.standardizedItemRangesMin;
        this.standardizedItemTotalsMean = 0.0D;
        this.standardizedItemTotalsVar = 0.0D;
        this.standardizedItemTotalsSd = 0.0D;
        this.standardizedItemTotalsMin = 0.0D;
        this.standardizedItemTotalsMax = 0.0D;
        this.standardizedItemTotalsRange = 0.0D;
        var8 = new Stat(this.standardizedItemMedians);
        if (this.nFactorOption) {
            var8.setDenominatorToN();
        } else {
            var8.setDenominatorToNminusOne();
        }

        this.standardizedItemMediansMean = var8.mean_as_double();
        this.standardizedItemMediansVar = var8.variance_as_double();
        this.standardizedItemMediansSd = Math.sqrt(this.standardizedItemMediansVar);
        this.standardizedItemMediansMin = var8.minimum_as_double();
        this.standardizedItemMediansMax = var8.maximum_as_double();
        this.standardizedItemMediansRange = this.standardizedItemMediansMax - this.standardizedItemMediansMin;
        this.rawPersonMeans = new double[this.nPersons];
        this.rawPersonStandardDeviations = new double[this.nPersons];
        this.rawPersonVariances = new double[this.nPersons];
        this.rawPersonMinima = new double[this.nPersons];
        this.rawPersonMaxima = new double[this.nPersons];
        this.rawPersonRanges = new double[this.nPersons];
        this.rawPersonTotals = new double[this.nPersons];
        Stat[] var10 = new Stat[this.nPersons];

        for(int var12 = 0; var12 < this.nPersons; ++var12) {
            var10[var12] = new Stat(this.scores1[var12]);
            if (this.nFactorOption) {
                var10[var12].setDenominatorToN();
            } else {
                var10[var12].setDenominatorToNminusOne();
            }

            this.rawPersonMeans[var12] = var10[var12].mean_as_double();
            this.rawPersonVariances[var12] = var10[var12].variance_as_double();
            this.rawPersonStandardDeviations[var12] = Math.sqrt(this.rawPersonVariances[var12]);
            this.rawPersonMinima[var12] = var10[var12].minimum_as_double();
            this.rawPersonMaxima[var12] = var10[var12].maximum_as_double();
            this.rawPersonRanges[var12] = this.rawPersonMaxima[var12] - this.rawPersonMinima[var12];
            this.rawPersonTotals[var12] = var10[var12].sum_as_double();
        }

        this.standardizedPersonMeans = new double[this.nPersons];
        this.standardizedPersonStandardDeviations = new double[this.nPersons];
        this.standardizedPersonVariances = new double[this.nPersons];
        this.standardizedPersonMinima = new double[this.nPersons];
        this.standardizedPersonMaxima = new double[this.nPersons];
        this.standardizedPersonRanges = new double[this.nPersons];
        this.standardizedPersonTotals = new double[this.nPersons];
        Stat[] var14 = new Stat[this.nPersons];

        for(int var15 = 0; var15 < this.nPersons; ++var15) {
            var14[var15] = new Stat(this.standardizedScores1[var15]);
            if (this.nFactorOption) {
                var14[var15].setDenominatorToN();
            } else {
                var14[var15].setDenominatorToNminusOne();
            }

            this.standardizedPersonMeans[var15] = var14[var15].mean_as_double();
            this.standardizedPersonVariances[var15] = var14[var15].variance_as_double();
            this.standardizedPersonStandardDeviations[var15] = Math.sqrt(this.standardizedPersonVariances[var15]);
            this.standardizedPersonMinima[var15] = var14[var15].minimum_as_double();
            this.standardizedPersonMaxima[var15] = var14[var15].maximum_as_double();
            this.standardizedPersonRanges[var15] = this.standardizedPersonMaxima[var15] - this.standardizedPersonMinima[var15];
            this.standardizedPersonTotals[var15] = var14[var15].sum_as_double();
        }

        var13 = new ArrayMaths(this.scores0[0]);

        for(int var16 = 1; var16 < this.nItems; ++var16) {
            var13 = var13.concatenate(this.scores0[var16]);
        }

        var5 = new Stat(var13.array());
        if (this.nFactorOption) {
            var5.setDenominatorToN();
        } else {
            var5.setDenominatorToNminusOne();
        }

        this.rawAllResponsesMean = var5.mean_as_double();
        this.rawAllResponsesVariance = var5.variance_as_double();
        this.rawAllResponsesStandardDeviation = Math.sqrt(this.rawAllResponsesVariance);
        this.rawAllResponsesMinimum = var5.minimum_as_double();
        this.rawAllResponsesMaximum = var5.maximum_as_double();
        this.rawAllResponsesRange = this.rawAllResponsesMaximum - this.rawAllResponsesMinimum;
        this.rawAllResponsesTotal = var5.sum_as_double();
        ArrayMaths var6 = new ArrayMaths(this.standardizedScores0[0]);

        for(int var7 = 1; var7 < this.nItems; ++var7) {
            var6 = var6.concatenate(this.standardizedScores0[var7]);
        }

        Stat var17 = new Stat(var6.array());
        if (this.nFactorOption) {
            var17.setDenominatorToN();
        } else {
            var17.setDenominatorToNminusOne();
        }

        this.standardizedAllResponsesMean = var17.mean_as_double();
        this.standardizedAllResponsesVariance = var17.variance_as_double();
        this.standardizedAllResponsesStandardDeviation = Math.sqrt(this.standardizedAllResponsesVariance);
        this.standardizedAllResponsesMinimum = var17.minimum_as_double();
        this.standardizedAllResponsesMaximum = var17.maximum_as_double();
        this.standardizedAllResponsesRange = this.standardizedAllResponsesMaximum - this.standardizedAllResponsesMinimum;
        this.standardizedAllResponsesTotal = 0.0D;
        this.variancesCalculated = true;
    }

    public double[] rawItemMeans() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (!this.variancesCalculated) {
            this.meansAndVariances();
        }

        return this.rawItemMeans;
    }

    public double[] standardizedItemMeans() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (!this.variancesCalculated) {
            this.meansAndVariances();
        }

        return this.standardizedItemMeans;
    }

    public double[] standardisedItemMeans() {
        return this.standardizedItemMeans();
    }

    public double rawItemMean(String var1) {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        int var2 = this.itemIndex(var1);
        if (!this.variancesCalculated) {
            this.meansAndVariances();
        }

        return this.rawItemMeans[var2 - 1];
    }

    public double rawItemMean(int var1) {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (var1 >= 1 && var1 <= this.nItems) {
            if (!this.variancesCalculated) {
                this.meansAndVariances();
            }

            return this.rawItemMeans[var1 - 1];
        } else {
            throw new IllegalArgumentException("The item index, " + var1 + ", must lie between 1 and the number of items," + this.nItems + ", inclusive");
        }
    }

    public double rawMeanOfItemMeans() {
        return this.rawItemMeansMean;
    }

    public double rawStandardDeviationOfItemMeans() {
        return this.rawItemMeansSd;
    }

    public double rawVarianceOfItemMeans() {
        return this.rawItemMeansVar;
    }

    public double rawMaximumOfItemMeans() {
        return this.rawItemMeansMax;
    }

    public double rawMinimumOfItemMeans() {
        return this.rawItemMeansMin;
    }

    public double rawRangeOfItemMeans() {
        return this.rawItemMeansRange;
    }

    public double standardizedItemMean(String var1) {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        int var2 = this.itemIndex(var1);
        if (!this.variancesCalculated) {
            this.meansAndVariances();
        }

        return this.standardizedItemMeans[var2 - 1];
    }

    public double standardisedItemMean(String var1) {
        return this.standardizedItemMean(var1);
    }

    public double standardizedItemMean(int var1) {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (var1 >= 1 && var1 <= this.nItems) {
            if (!this.variancesCalculated) {
                this.meansAndVariances();
            }

            return this.standardizedItemMeans[var1 - 1];
        } else {
            throw new IllegalArgumentException("The item index, " + var1 + ", must lie between 1 and the number of items," + this.nItems + ", inclusive");
        }
    }

    public double standardisedItemMean(int var1) {
        return this.standardizedItemMean(var1);
    }

    public double standardizedMeanOfItemMeans() {
        return this.standardizedItemMeansMean;
    }

    public double standardisedMeanOfItemMeans() {
        return this.standardizedItemMeansMean;
    }

    public double standardizedStanadarDeviationOfItemMeans() {
        return this.standardizedItemMeansSd;
    }

    public double standardisedStanadarDeviationOfItemMeans() {
        return this.standardizedItemMeansSd;
    }

    public double standardizedVarianceOfItemMeans() {
        return this.standardizedItemMeansVar;
    }

    public double standardizedMaximumOfItemMeans() {
        return this.standardizedItemMeansMax;
    }

    public double standardisedVarianceOfItemMeans() {
        return this.standardizedItemMeansVar;
    }

    public double standardizedMinimumOfItemMeans() {
        return this.standardizedItemMeansMin;
    }

    public double standardisedMinimumOfItemMeans() {
        return this.standardizedItemMeansMin;
    }

    public double standardizedRangeOfItemMeans() {
        return this.standardizedItemMeansRange;
    }

    public double standardisedRangeOfItemMeans() {
        return this.standardizedItemMeansRange;
    }

    public double[] rawItemStandardDeviations() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (!this.variancesCalculated) {
            this.meansAndVariances();
        }

        return this.rawItemStandardDeviations;
    }

    public double rawItemStandardDeviation(String var1) {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        int var2 = this.itemIndex(var1);
        if (!this.variancesCalculated) {
            this.meansAndVariances();
        }

        return this.rawItemStandardDeviations[var2 - 1];
    }

    public double rawItemStandardDeviation(int var1) {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (var1 >= 1 && var1 <= this.nItems) {
            if (!this.variancesCalculated) {
                this.meansAndVariances();
            }

            return this.rawItemStandardDeviations[var1 - 1];
        } else {
            throw new IllegalArgumentException("The item index, " + var1 + ", must lie between 1 and the number of items," + this.nItems + ", inclusive");
        }
    }

    public double rawMeanOfItemStandardDeviations() {
        return this.rawItemStandardDeviationsMean;
    }

    public double rawStanadarDeviationOfItemStandardDeviations() {
        return this.rawItemStandardDeviationsSd;
    }

    public double rawVarianceOfItemStandardDeviations() {
        return this.rawItemStandardDeviationsVar;
    }

    public double rawMaximumOfItemStandardDeviations() {
        return this.rawItemStandardDeviationsMax;
    }

    public double rawMinimumOfItemStandardDeviations() {
        return this.rawItemStandardDeviationsMin;
    }

    public double rawRangeOfItemStandardDeviations() {
        return this.rawItemStandardDeviationsRange;
    }

    public double[] standardizedItemStandardDeviations() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (!this.variancesCalculated) {
            this.meansAndVariances();
        }

        return this.standardizedItemStandardDeviations;
    }

    public double[] standardisedItemStandardDeviations() {
        return this.standardizedItemStandardDeviations();
    }

    public double standardizedItemStandardDeviation(String var1) {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        int var2 = this.itemIndex(var1);
        if (!this.variancesCalculated) {
            this.meansAndVariances();
        }

        return this.standardizedItemStandardDeviations[var2 - 1];
    }

    public double standardisedItemStandardDeviation(String var1) {
        return this.standardizedItemStandardDeviation(var1);
    }

    public double standardizedItemStandardDeviation(int var1) {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (var1 >= 1 && var1 <= this.nItems) {
            if (!this.variancesCalculated) {
                this.meansAndVariances();
            }

            return this.standardizedItemStandardDeviations[var1 - 1];
        } else {
            throw new IllegalArgumentException("The item index, " + var1 + ", must lie between 1 and the number of items," + this.nItems + ", inclusive");
        }
    }

    public double standardisedItemStandardDeviation(int var1) {
        return this.standardizedItemStandardDeviation(var1);
    }

    public double standardizedMeanOfItemStandardDeviations() {
        return this.standardizedItemStandardDeviationsMean;
    }

    public double standardisedMeanOfItemStandardDeviations() {
        return this.standardizedItemStandardDeviationsMean;
    }

    public double standardizedStanadarDeviationOfItemStandardDeviations() {
        return this.standardizedItemStandardDeviationsSd;
    }

    public double standardisedStanadarDeviationOfItemStandardDeviations() {
        return this.standardizedItemStandardDeviationsSd;
    }

    public double standardizedVarianceOfItemStandardDeviations() {
        return this.standardizedItemStandardDeviationsVar;
    }

    public double standardisedVarianceOfItemStandardDeviations() {
        return this.standardizedItemStandardDeviationsVar;
    }

    public double standardizedMaximumOfItemStandardDeviations() {
        return this.standardizedItemStandardDeviationsMax;
    }

    public double standardisedMaximumOfItemStandardDeviations() {
        return this.standardizedItemStandardDeviationsMax;
    }

    public double standardizedMinimumOfItemStandardDeviations() {
        return this.standardizedItemStandardDeviationsMin;
    }

    public double standardisedMinimumOfItemStandardDeviations() {
        return this.standardizedItemStandardDeviationsMin;
    }

    public double standardizedRangeOfItemStandardDeviations() {
        return this.standardizedItemStandardDeviationsRange;
    }

    public double standardisedRangeOfItemStandardDeviations() {
        return this.standardizedItemStandardDeviationsRange;
    }

    public double[] rawItemVariances() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (!this.variancesCalculated) {
            this.meansAndVariances();
        }

        return this.rawItemVariances;
    }

    public double[] standardizedItemVariances() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (!this.variancesCalculated) {
            this.meansAndVariances();
        }

        return this.standardizedItemVariances;
    }

    public double[] standardisedItemVariances() {
        return this.standardizedItemVariances();
    }

    public double rawItemVariance(String var1) {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        int var2 = this.itemIndex(var1);
        if (!this.variancesCalculated) {
            this.meansAndVariances();
        }

        return this.rawItemVariances[var2 - 1];
    }

    public double rawItemVariance(int var1) {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (var1 >= 1 && var1 <= this.nItems) {
            if (!this.variancesCalculated) {
                this.meansAndVariances();
            }

            return this.rawItemVariances[var1 - 1];
        } else {
            throw new IllegalArgumentException("The item index, " + var1 + ", must lie between 1 and the number of items," + this.nItems + ", inclusive");
        }
    }

    public double standardizedItemVariance(String var1) {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        int var2 = this.itemIndex(var1);
        if (!this.variancesCalculated) {
            this.meansAndVariances();
        }

        return this.standardizedItemVariances[var2 - 1];
    }

    public double standardisedItemVariance(String var1) {
        return this.standardizedItemVariance(var1);
    }

    public double standardizedItemVariance(int var1) {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (var1 >= 1 && var1 <= this.nItems) {
            if (!this.variancesCalculated) {
                this.meansAndVariances();
            }

            return this.standardizedItemVariances[var1 - 1];
        } else {
            throw new IllegalArgumentException("The item index, " + var1 + ", must lie between 1 and the number of items," + this.nItems + ", inclusive");
        }
    }

    public double standardisedItemVariance(int var1) {
        return this.standardizedItemVariance(var1);
    }

    public double rawMeanOfItemVariances() {
        return this.rawItemVariancesMean;
    }

    public double rawStanadarDeviationOfItemVariances() {
        return this.rawItemVariancesSd;
    }

    public double rawVarianceOfItemVariances() {
        return this.rawItemVariancesVar;
    }

    public double rawMaximumOfItemVariances() {
        return this.rawItemVariancesMax;
    }

    public double rawMinimumOfItemVariances() {
        return this.rawItemVariancesMin;
    }

    public double rawRangeOfItemVariances() {
        return this.rawItemVariancesRange;
    }

    public double standardizedMeanOfItemVariances() {
        return this.standardizedItemVariancesMean;
    }

    public double standardisedMeanOfItemVariances() {
        return this.standardizedItemVariancesMean;
    }

    public double standardizedStanadarDeviationOfItemVariances() {
        return this.standardizedItemVariancesSd;
    }

    public double standardisedStanadarDeviationOfItemVariances() {
        return this.standardizedItemVariancesSd;
    }

    public double standardizedVarianceOfItemVariances() {
        return this.standardizedItemVariancesVar;
    }

    public double standardisedVarianceOfItemVariances() {
        return this.standardizedItemVariancesVar;
    }

    public double standardizedMaximumOfItemVariances() {
        return this.standardizedItemVariancesMax;
    }

    public double standardisedMaximumOfItemVariances() {
        return this.standardizedItemVariancesMax;
    }

    public double standardizedMinimumOfItemVariances() {
        return this.standardizedItemVariancesMin;
    }

    public double standardisedMinimumOfItemVariances() {
        return this.standardizedItemVariancesMin;
    }

    public double standardizedRangeOfItemVariances() {
        return this.standardizedItemVariancesRange;
    }

    public double standardisedRangeOfItemVariances() {
        return this.standardizedItemVariancesRange;
    }

    public double[] rawItemMinima() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (!this.variancesCalculated) {
            this.meansAndVariances();
        }

        return this.rawItemMinima;
    }

    public double[] standardizedItemMinima() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (!this.variancesCalculated) {
            this.meansAndVariances();
        }

        return this.standardizedItemMinima;
    }

    public double[] standardisedItemMinima() {
        return this.standardizedItemMinima();
    }

    public double rawItemMinimum(int var1) {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (var1 >= 1 && var1 <= this.nItems) {
            if (!this.variancesCalculated) {
                this.meansAndVariances();
            }

            return this.rawItemMinima[var1 - 1];
        } else {
            throw new IllegalArgumentException("The item index, " + var1 + ", must lie between 1 and the number of items," + this.nItems + ", inclusive");
        }
    }

    public double standardizedItemMinimum(int var1) {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (var1 >= 1 && var1 <= this.nItems) {
            if (!this.variancesCalculated) {
                this.meansAndVariances();
            }

            return this.standardizedItemMinima[var1 - 1];
        } else {
            throw new IllegalArgumentException("The item index, " + var1 + ", must lie between 1 and the number of items," + this.nItems + ", inclusive");
        }
    }

    public double standardisedItemMinimum(int var1) {
        return this.standardizedItemMinimum(var1);
    }

    public double rawMeanOfItemMinima() {
        return this.rawItemMinimaMean;
    }

    public double rawStanadarDeviationOfItemMinima() {
        return this.rawItemMinimaSd;
    }

    public double rawVarianceOfItemMinima() {
        return this.rawItemMinimaVar;
    }

    public double rawMaximumOfItemMinima() {
        return this.rawItemMinimaMax;
    }

    public double rawMinimumOfItemMinima() {
        return this.rawItemMinimaMin;
    }

    public double rawRangeOfItemMinima() {
        return this.rawItemMinimaRange;
    }

    public double standardizedMeanOfItemMinima() {
        return this.standardizedItemMinimaMean;
    }

    public double standardisedMeanOfItemMinima() {
        return this.standardizedItemMinimaMean;
    }

    public double standardizedStanadarDeviationOfItemMinima() {
        return this.standardizedItemMinimaSd;
    }

    public double standardisedStanadarDeviationOfItemMinima() {
        return this.standardizedItemMinimaSd;
    }

    public double standardizedVarianceOfItemMinima() {
        return this.standardizedItemMinimaVar;
    }

    public double standardisedVarianceOfItemMinima() {
        return this.standardizedItemMinimaVar;
    }

    public double standardizedMaximumOfItemMinima() {
        return this.standardizedItemMinimaMax;
    }

    public double standardisedMaximumOfItemMinima() {
        return this.standardizedItemMinimaMax;
    }

    public double standardizedMinimumOfItemMinima() {
        return this.standardizedItemMinimaMin;
    }

    public double standardisedMinimumOfItemMinima() {
        return this.standardizedItemMinimaMin;
    }

    public double standardizedRangeOfItemMinima() {
        return this.standardizedItemMinimaRange;
    }

    public double standardisedRangeOfItemMinima() {
        return this.standardizedItemMinimaRange;
    }

    public double[] rawItemMaxima() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (!this.variancesCalculated) {
            this.meansAndVariances();
        }

        return this.rawItemMaxima;
    }

    public double[] standardizedItemMaxima() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (!this.variancesCalculated) {
            this.meansAndVariances();
        }

        return this.standardizedItemMaxima;
    }

    public double[] standardisedItemMaxima() {
        return this.standardizedItemMaxima();
    }

    public double rawItemMaximum(int var1) {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (var1 >= 1 && var1 <= this.nItems) {
            if (!this.variancesCalculated) {
                this.meansAndVariances();
            }

            return this.rawItemMaxima[var1 - 1];
        } else {
            throw new IllegalArgumentException("The item index, " + var1 + ", must lie between 1 and the number of items," + this.nItems + ", inclusive");
        }
    }

    public double standardizedItemMaximum(int var1) {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (var1 >= 1 && var1 <= this.nItems) {
            if (!this.variancesCalculated) {
                this.meansAndVariances();
            }

            return this.standardizedItemMaxima[var1 - 1];
        } else {
            throw new IllegalArgumentException("The item index, " + var1 + ", must lie between 1 and the number of items," + this.nItems + ", inclusive");
        }
    }

    public double standardisedItemMaximum(int var1) {
        return this.standardizedItemMaximum(var1);
    }

    public double rawMeanOfItemMaxima() {
        return this.rawItemMaximaMean;
    }

    public double rawStanadarDeviationOfItemMaxima() {
        return this.rawItemMaximaSd;
    }

    public double rawVarianceOfItemMaxima() {
        return this.rawItemMaximaVar;
    }

    public double rawMaximumOfItemMaxima() {
        return this.rawItemMaximaMax;
    }

    public double rawMinimumOfItemMaxima() {
        return this.rawItemMaximaMin;
    }

    public double rawRangeOfItemMaxima() {
        return this.rawItemMaximaRange;
    }

    public double standardizedMeanOfItemMaxima() {
        return this.standardizedItemMaximaMean;
    }

    public double standardisedMeanOfItemMaxima() {
        return this.standardizedItemMaximaMean;
    }

    public double standardizedStanadarDeviationOfItemMaxima() {
        return this.standardizedItemMaximaSd;
    }

    public double standardisedStanadarDeviationOfItemMaxima() {
        return this.standardizedItemMaximaSd;
    }

    public double standardizedVarianceOfItemMaxima() {
        return this.standardizedItemMaximaVar;
    }

    public double standardisedVarianceOfItemMaxima() {
        return this.standardizedItemMaximaVar;
    }

    public double standardizedMaximumOfItemMaxima() {
        return this.standardizedItemMaximaMax;
    }

    public double standardisedMaximumOfItemMaxima() {
        return this.standardizedItemMaximaMax;
    }

    public double standardizedMinimumOfItemMaxima() {
        return this.standardizedItemMaximaMin;
    }

    public double standardisedMinimumOfItemMaxima() {
        return this.standardizedItemMaximaMin;
    }

    public double standardizedRangeOfItemMaxima() {
        return this.standardizedItemMaximaRange;
    }

    public double standardisedRangeOfItemMaxima() {
        return this.standardizedItemMaximaRange;
    }

    public double[] rawItemRanges() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (!this.variancesCalculated) {
            this.meansAndVariances();
        }

        return this.rawItemRanges;
    }

    public double[] standardizedItemRanges() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (!this.variancesCalculated) {
            this.meansAndVariances();
        }

        return this.standardizedItemRanges;
    }

    public double[] standardisedItemRanges() {
        return this.standardizedItemRanges();
    }

    public double rawItemRange(int var1) {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (var1 >= 1 && var1 <= this.nItems) {
            if (!this.variancesCalculated) {
                this.meansAndVariances();
            }

            return this.rawItemRanges[var1 - 1];
        } else {
            throw new IllegalArgumentException("The item index, " + var1 + ", must lie between 1 and the number of items," + this.nItems + ", inclusive");
        }
    }

    public double standardizedItemRange(int var1) {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (var1 >= 1 && var1 <= this.nItems) {
            if (!this.variancesCalculated) {
                this.meansAndVariances();
            }

            return this.standardizedItemRanges[var1 - 1];
        } else {
            throw new IllegalArgumentException("The item index, " + var1 + ", must lie between 1 and the number of items," + this.nItems + ", inclusive");
        }
    }

    public double standardisedItemRange(int var1) {
        return this.standardizedItemRange(var1);
    }

    public double rawMeanOfItemRanges() {
        return this.rawItemRangesMean;
    }

    public double rawStanadarDeviationOfItemRanges() {
        return this.rawItemRangesSd;
    }

    public double rawVarianceOfItemRanges() {
        return this.rawItemRangesVar;
    }

    public double rawMaximumOfItemRanges() {
        return this.rawItemRangesMax;
    }

    public double rawMinimumOfItemRanges() {
        return this.rawItemRangesMin;
    }

    public double rawRangeOfItemRanges() {
        return this.rawItemRangesRange;
    }

    public double standardizedMeanOfItemRanges() {
        return this.standardizedItemRangesMean;
    }

    public double standardisedMeanOfItemRanges() {
        return this.standardizedItemRangesMean;
    }

    public double standardizedStanadarDeviationOfItemRanges() {
        return this.standardizedItemRangesSd;
    }

    public double standardisedStanadarDeviationOfItemRanges() {
        return this.standardizedItemRangesSd;
    }

    public double standardizedVarianceOfItemRanges() {
        return this.standardizedItemRangesVar;
    }

    public double standardisedVarianceOfItemRanges() {
        return this.standardizedItemRangesVar;
    }

    public double standardizedMaximumOfItemRanges() {
        return this.standardizedItemRangesMax;
    }

    public double standardisedMaximumOfItemRanges() {
        return this.standardizedItemRangesMax;
    }

    public double standardizedMinimumOfItemRanges() {
        return this.standardizedItemRangesMin;
    }

    public double standardisedMinimumOfItemRanges() {
        return this.standardizedItemRangesMin;
    }

    public double standardizedRangeOfItemRanges() {
        return this.standardizedItemRangesRange;
    }

    public double standardisedRangeOfItemRanges() {
        return this.standardizedItemRangesRange;
    }

    public double[] rawItemTotals() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (!this.variancesCalculated) {
            this.meansAndVariances();
        }

        return this.rawItemTotals;
    }

    public double[] standardizedItemTotals() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (!this.variancesCalculated) {
            this.meansAndVariances();
        }

        return this.standardizedItemTotals;
    }

    public double[] standardisedItemTotals() {
        return this.standardizedItemTotals();
    }

    public double rawItemTotal(String var1) {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        int var2 = this.itemIndex(var1);
        if (!this.variancesCalculated) {
            this.meansAndVariances();
        }

        return this.rawItemTotals[var2 - 1];
    }

    public double rawItemTotal(int var1) {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (var1 >= 1 && var1 <= this.nItems) {
            if (!this.variancesCalculated) {
                this.meansAndVariances();
            }

            return this.rawItemTotals[var1 - 1];
        } else {
            throw new IllegalArgumentException("The item index, " + var1 + ", must lie between 1 and the number of items," + this.nItems + ", inclusive");
        }
    }

    public double standardizedItemTotal(String var1) {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        int var2 = this.itemIndex(var1);
        if (!this.variancesCalculated) {
            this.meansAndVariances();
        }

        return this.standardizedItemTotals[var2 - 1];
    }

    public double standardisedItemTotal(String var1) {
        return this.standardizedItemTotal(var1);
    }

    public double standardizedItemTotal(int var1) {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (var1 >= 1 && var1 <= this.nItems) {
            if (!this.variancesCalculated) {
                this.meansAndVariances();
            }

            return this.standardizedItemTotals[var1 - 1];
        } else {
            throw new IllegalArgumentException("The item index, " + var1 + ", must lie between 1 and the number of items," + this.nItems + ", inclusive");
        }
    }

    public double standardisedItemTotal(int var1) {
        return this.standardizedItemTotal(var1);
    }

    public double rawMeanOfItemTotals() {
        return this.rawItemTotalsMean;
    }

    public double rawStanadarDeviationOfItemTotals() {
        return this.rawItemTotalsSd;
    }

    public double rawVarianceOfItemTotals() {
        return this.rawItemTotalsVar;
    }

    public double rawMaximumOfItemTotals() {
        return this.rawItemTotalsMax;
    }

    public double rawMinimumOfItemTotals() {
        return this.rawItemTotalsMin;
    }

    public double rawRangeOfItemTotals() {
        return this.rawItemTotalsRange;
    }

    public double standardizedMeanOfItemTotals() {
        return this.standardizedItemTotalsMean;
    }

    public double standardisedMeanOfItemTotals() {
        return this.standardizedItemTotalsMean;
    }

    public double standardizedStanadarDeviationOfItemTotals() {
        return this.standardizedItemTotalsSd;
    }

    public double standardisedStanadarDeviationOfItemTotals() {
        return this.standardizedItemTotalsSd;
    }

    public double standardizedVarianceOfItemTotals() {
        return this.standardizedItemTotalsVar;
    }

    public double standardisedVarianceOfItemTotals() {
        return this.standardizedItemTotalsVar;
    }

    public double standardizedMaximumOfItemTotals() {
        return this.standardizedItemTotalsMax;
    }

    public double standardisedMaximumOfItemTotals() {
        return this.standardizedItemTotalsMax;
    }

    public double standardizedMinimumOfItemTotals() {
        return this.standardizedItemTotalsMin;
    }

    public double standardisedMinimumOfItemTotals() {
        return this.standardizedItemTotalsMin;
    }

    public double standardizedRangeOfItemTotals() {
        return this.standardizedItemTotalsRange;
    }

    public double standardisedRangeOfItemTotals() {
        return this.standardizedItemTotalsRange;
    }

    public double[] rawPersonMeans() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (!this.variancesCalculated) {
            this.meansAndVariances();
        }

        return this.rawPersonMeans;
    }

    public double[] standardizedPersonMeans() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (!this.variancesCalculated) {
            this.meansAndVariances();
        }

        return this.standardizedPersonMeans;
    }

    public double[] standardisedPersonMeans() {
        return this.standardizedPersonMeans();
    }

    public double rawPersonMean(int var1) {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (var1 >= 1 && var1 <= this.nPersons) {
            if (!this.variancesCalculated) {
                this.meansAndVariances();
            }

            return this.rawPersonMeans[var1 - 1];
        } else {
            throw new IllegalArgumentException("The person index, " + var1 + ", must lie between 1 and the number of persons," + this.nPersons + ", inclusive");
        }
    }

    public double standardizedPersonMean(int var1) {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (var1 >= 1 && var1 <= this.nPersons) {
            if (!this.variancesCalculated) {
                this.meansAndVariances();
            }

            return this.standardizedPersonMeans[var1 - 1];
        } else {
            throw new IllegalArgumentException("The person index, " + var1 + ", must lie between 1 and the number of persons," + this.nPersons + ", inclusive");
        }
    }

    public double standardisedPersonMean(int var1) {
        return this.standardizedPersonMean(var1);
    }

    public double[] rawPersonStandardDeviations() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (!this.variancesCalculated) {
            this.meansAndVariances();
        }

        return this.rawPersonStandardDeviations;
    }

    public double[] standardizedPersonStandardDeviations() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (!this.variancesCalculated) {
            this.meansAndVariances();
        }

        return this.standardizedPersonStandardDeviations;
    }

    public double[] standardisedPersonStandardDeviations() {
        return this.standardizedPersonStandardDeviations();
    }

    public double rawPersonStandardDeviation(int var1) {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (var1 >= 1 && var1 <= this.nPersons) {
            if (!this.variancesCalculated) {
                this.meansAndVariances();
            }

            return this.rawPersonStandardDeviations[var1 - 1];
        } else {
            throw new IllegalArgumentException("The person index, " + var1 + ", must lie between 1 and the number of persons," + this.nPersons + ", inclusive");
        }
    }

    public double standardizedPersonStandardDeviation(int var1) {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (var1 >= 1 && var1 <= this.nPersons) {
            if (!this.variancesCalculated) {
                this.meansAndVariances();
            }

            return this.standardizedPersonStandardDeviations[var1 - 1];
        } else {
            throw new IllegalArgumentException("The person index, " + var1 + ", must lie between 1 and the number of persons," + this.nPersons + ", inclusive");
        }
    }

    public double standardisedPersonStandardDeviation(int var1) {
        return this.standardizedPersonStandardDeviation(var1);
    }

    public double[] rawPersonVariances() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (!this.variancesCalculated) {
            this.meansAndVariances();
        }

        return this.rawPersonVariances;
    }

    public double[] standardizedPersonVariances() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (!this.variancesCalculated) {
            this.meansAndVariances();
        }

        return this.standardizedPersonVariances;
    }

    public double[] standardisedPersonVariances() {
        return this.standardizedPersonVariances();
    }

    public double rawPersonVariance(int var1) {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (var1 >= 1 && var1 <= this.nPersons) {
            if (!this.variancesCalculated) {
                this.meansAndVariances();
            }

            return this.rawPersonVariances[var1 - 1];
        } else {
            throw new IllegalArgumentException("The person index, " + var1 + ", must lie between 1 and the number of persons," + this.nPersons + ", inclusive");
        }
    }

    public double standardizedPersonVariance(int var1) {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (var1 >= 1 && var1 <= this.nPersons) {
            if (!this.variancesCalculated) {
                this.meansAndVariances();
            }

            return this.standardizedPersonVariances[var1 - 1];
        } else {
            throw new IllegalArgumentException("The person index, " + var1 + ", must lie between 1 and the number of persons," + this.nPersons + ", inclusive");
        }
    }

    public double standardisedPersonVariance(int var1) {
        return this.standardizedPersonVariance(var1);
    }

    public double[] rawPersonMinima() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (!this.variancesCalculated) {
            this.meansAndVariances();
        }

        return this.rawPersonMinima;
    }

    public double[] standardizedPersonMinima() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (!this.variancesCalculated) {
            this.meansAndVariances();
        }

        return this.standardizedPersonMinima;
    }

    public double[] standardisedPersonMinima() {
        return this.standardisedPersonMinima();
    }

    public double rawPersonMinimum(int var1) {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (var1 >= 1 && var1 <= this.nItems) {
            if (!this.variancesCalculated) {
                this.meansAndVariances();
            }

            return this.rawPersonMinima[var1 - 1];
        } else {
            throw new IllegalArgumentException("The person index, " + var1 + ", must lie between 1 and the number of persons," + this.nPersons + ", inclusive");
        }
    }

    public double standardizedPersonMinimum(int var1) {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (var1 >= 1 && var1 <= this.nPersons) {
            if (!this.variancesCalculated) {
                this.meansAndVariances();
            }

            return this.standardizedPersonMinima[var1 - 1];
        } else {
            throw new IllegalArgumentException("The person index, " + var1 + ", must lie between 1 and the number of persons," + this.nPersons + ", inclusive");
        }
    }

    public double standardisedPersonMinimum(int var1) {
        return this.standardizedPersonMinimum(var1);
    }

    public double[] rawPersonMaxima() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (!this.variancesCalculated) {
            this.meansAndVariances();
        }

        return this.rawPersonMaxima;
    }

    public double[] standardizedPersonMaxima() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (!this.variancesCalculated) {
            this.meansAndVariances();
        }

        return this.standardizedPersonMaxima;
    }

    public double[] standardisedPersonMaxima() {
        return this.standardizedPersonMaxima();
    }

    public double rawPersonMaximum(int var1) {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (var1 >= 1 && var1 <= this.nItems) {
            if (!this.variancesCalculated) {
                this.meansAndVariances();
            }

            return this.rawPersonMaxima[var1 - 1];
        } else {
            throw new IllegalArgumentException("The person index, " + var1 + ", must lie between 1 and the number of persons," + this.nPersons + ", inclusive");
        }
    }

    public double standardizedPersonMaximum(int var1) {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (var1 >= 1 && var1 <= this.nPersons) {
            if (!this.variancesCalculated) {
                this.meansAndVariances();
            }

            return this.standardizedPersonMaxima[var1 - 1];
        } else {
            throw new IllegalArgumentException("The person index, " + var1 + ", must lie between 1 and the number of persons," + this.nPersons + ", inclusive");
        }
    }

    public double standardisedPersonMaximum(int var1) {
        return this.standardizedPersonMaximum(var1);
    }

    public double[] rawPersonRanges() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (!this.variancesCalculated) {
            this.meansAndVariances();
        }

        return this.rawPersonRanges;
    }

    public double[] standardizedPersonRanges() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (!this.variancesCalculated) {
            this.meansAndVariances();
        }

        return this.standardizedPersonRanges;
    }

    public double[] standardisedPersonRanges() {
        return this.standardizedPersonRanges();
    }

    public double rawPersonRange(int var1) {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (var1 >= 1 && var1 <= this.nItems) {
            if (!this.variancesCalculated) {
                this.meansAndVariances();
            }

            return this.rawPersonRanges[var1 - 1];
        } else {
            throw new IllegalArgumentException("The person index, " + var1 + ", must lie between 1 and the number of persons," + this.nPersons + ", inclusive");
        }
    }

    public double standardizedPersonRange(int var1) {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (var1 >= 1 && var1 <= this.nPersons) {
            if (!this.variancesCalculated) {
                this.meansAndVariances();
            }

            return this.standardizedPersonRanges[var1 - 1];
        } else {
            throw new IllegalArgumentException("The person index, " + var1 + ", must lie between 1 and the number of persons," + this.nPersons + ", inclusive");
        }
    }

    public double standardisedPersonRange(int var1) {
        return this.standardizedPersonRange(var1);
    }

    public double[] rawItemMedians() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (!this.variancesCalculated) {
            this.meansAndVariances();
        }

        return this.rawItemMedians;
    }

    public double[] standardizedItemMedians() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (!this.variancesCalculated) {
            this.meansAndVariances();
        }

        return this.standardizedItemMedians;
    }

    public double[] standardisedItemMedians() {
        return this.standardizedItemMedians();
    }

    public double rawItemMedian(String var1) {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        int var2 = this.itemIndex(var1);
        if (!this.variancesCalculated) {
            this.meansAndVariances();
        }

        return this.rawItemMedians[var2 - 1];
    }

    public double rawItemMedian(int var1) {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (var1 >= 1 && var1 <= this.nItems) {
            if (!this.variancesCalculated) {
                this.meansAndVariances();
            }

            return this.rawItemMedians[var1 - 1];
        } else {
            throw new IllegalArgumentException("The item index, " + var1 + ", must lie between 1 and the number of items," + this.nItems + ", inclusive");
        }
    }

    public double standardizedItemMedian(String var1) {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        int var2 = this.itemIndex(var1);
        if (!this.variancesCalculated) {
            this.meansAndVariances();
        }

        return this.standardizedItemMedians[var2 - 1];
    }

    public double standardisedItemMedian(String var1) {
        return this.standardizedItemMedian(var1);
    }

    public double standardizedItemMedian(int var1) {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (var1 >= 1 && var1 <= this.nItems) {
            if (!this.variancesCalculated) {
                this.meansAndVariances();
            }

            return this.standardizedItemMedians[var1 - 1];
        } else {
            throw new IllegalArgumentException("The item index, " + var1 + ", must lie between 1 and the number of items," + this.nItems + ", inclusive");
        }
    }

    public double standardisedItemMedian(int var1) {
        return this.standardizedItemMedian(var1);
    }

    public double rawMeanOfItemMedians() {
        return this.rawItemMediansMean;
    }

    public double rawStanadarDeviationOfItemMedians() {
        return this.rawItemMediansSd;
    }

    public double rawVarianceOfItemMedians() {
        return this.rawItemMediansVar;
    }

    public double rawMaximumOfItemMedians() {
        return this.rawItemMediansMax;
    }

    public double rawMinimumOfItemMedians() {
        return this.rawItemMediansMin;
    }

    public double rawRangeOfItemMedians() {
        return this.rawItemMediansRange;
    }

    public double standardizedMeanOfItemMedians() {
        return this.standardizedItemMediansMean;
    }

    public double standardisedMeanOfItemMedians() {
        return this.standardizedItemMediansMean;
    }

    public double standardizedStanadarDeviationOfItemMedians() {
        return this.standardizedItemMediansSd;
    }

    public double standardisedStanadarDeviationOfItemMedians() {
        return this.standardizedItemMediansSd;
    }

    public double standardizedVarianceOfItemMedians() {
        return this.standardizedItemMediansVar;
    }

    public double standardisedVarianceOfItemMedians() {
        return this.standardizedItemMediansVar;
    }

    public double standardizedMaximumOfItemMedians() {
        return this.standardizedItemMediansMax;
    }

    public double standardisedMaximumOfItemMedians() {
        return this.standardizedItemMediansMax;
    }

    public double standardizedMinimumOfItemMedians() {
        return this.standardizedItemMediansMin;
    }

    public double standardisedMinimumOfItemMedians() {
        return this.standardizedItemMediansMin;
    }

    public double standardizedRangeOfItemMedians() {
        return this.standardizedItemMediansRange;
    }

    public double standardisedRangeOfItemMedians() {
        return this.standardizedItemMediansRange;
    }

    public double[] rawPersonTotals() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (!this.variancesCalculated) {
            this.meansAndVariances();
        }

        return this.rawPersonTotals;
    }

    public double[] standardizedPersonTotals() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (!this.variancesCalculated) {
            this.meansAndVariances();
        }

        return this.standardizedPersonTotals;
    }

    public double[] standardisedPersonTotals() {
        return this.standardizedPersonTotals();
    }

    public double rawPersonTotal(int var1) {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (var1 >= 1 && var1 <= this.nItems) {
            if (!this.variancesCalculated) {
                this.meansAndVariances();
            }

            return this.rawPersonTotals[var1 - 1];
        } else {
            throw new IllegalArgumentException("The person index, " + var1 + ", must lie between 1 and the number of persons," + this.nPersons + ", inclusive");
        }
    }

    public double standardizedPersonTotal(int var1) {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (var1 >= 1 && var1 <= this.nPersons) {
            if (!this.variancesCalculated) {
                this.meansAndVariances();
            }

            return this.standardizedPersonTotals[var1 - 1];
        } else {
            throw new IllegalArgumentException("The person index, " + var1 + ", must lie between 1 and the number of persons," + this.nPersons + ", inclusive");
        }
    }

    public double standardisedPersonTotal(int var1) {
        return this.standardizedPersonTotal(var1);
    }

    public double rawAllResponsesMean() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (!this.variancesCalculated) {
            this.meansAndVariances();
        }

        return this.rawAllResponsesMean;
    }

    public double standardizedAllResponsesMean() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (!this.variancesCalculated) {
            this.meansAndVariances();
        }

        return this.standardizedAllResponsesMean;
    }

    public double standardisedTotalMean() {
        return this.standardizedAllResponsesMean();
    }

    public double rawAllResponsesStandardDeviation() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (!this.variancesCalculated) {
            this.meansAndVariances();
        }

        return this.rawAllResponsesStandardDeviation;
    }

    public double standardizedAllResponsesStandardDeviation() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (!this.variancesCalculated) {
            this.meansAndVariances();
        }

        return this.standardizedAllResponsesStandardDeviation;
    }

    public double standardisedTotalStandardDeviation() {
        return this.standardizedAllResponsesStandardDeviation();
    }

    public double rawAllResponsesVariance() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (!this.variancesCalculated) {
            this.meansAndVariances();
        }

        return this.rawAllResponsesVariance;
    }

    public double standardizedAllResponsesVariance() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (!this.variancesCalculated) {
            this.meansAndVariances();
        }

        return this.standardizedAllResponsesVariance;
    }

    public double standardisedTotalVariance() {
        return this.standardizedAllResponsesVariance();
    }

    public double rawAllResponsesMinimum() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (!this.variancesCalculated) {
            this.meansAndVariances();
        }

        return this.rawAllResponsesMinimum;
    }

    public double standardizedAllResponsesMinimum() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (!this.variancesCalculated) {
            this.meansAndVariances();
        }

        return this.standardizedAllResponsesMinimum;
    }

    public double standardisedTotalMinimum() {
        return this.standardizedAllResponsesMinimum();
    }

    public double rawAllResponsesMaximum() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (!this.variancesCalculated) {
            this.meansAndVariances();
        }

        return this.rawAllResponsesMaximum;
    }

    public double standardizedAllResponsesMaximum() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (!this.variancesCalculated) {
            this.meansAndVariances();
        }

        return this.standardizedAllResponsesMaximum;
    }

    public double standardisedTotalMaximum() {
        return this.standardizedAllResponsesMaximum();
    }

    public double rawAllResponsesRange() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (!this.variancesCalculated) {
            this.meansAndVariances();
        }

        return this.rawAllResponsesRange;
    }

    public double standardizedAllResponsesRange() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (!this.variancesCalculated) {
            this.meansAndVariances();
        }

        return this.standardizedAllResponsesRange;
    }

    public double standardisedTotalRange() {
        return this.standardizedAllResponsesRange();
    }

    public double rawAllResponsesTotal() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (!this.variancesCalculated) {
            this.meansAndVariances();
        }

        return this.rawAllResponsesTotal;
    }

    public double standardizedAllResponsesTotal() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (!this.variancesCalculated) {
            this.meansAndVariances();
        }

        return this.standardizedAllResponsesTotal;
    }

    public double standardisedTotalTotal() {
        return this.standardizedAllResponsesTotal();
    }

    protected void covariancesAndCorrelationCoefficients() {
        this.rawCovariances = new double[this.nItems + 1][this.nItems + 1];

        int var1;
        int var2;
        for(var1 = 0; var1 < this.nItems; ++var1) {
            for(var2 = var1; var2 < this.nItems; ++var2) {
                this.rawCovariances[var1][var2] = Stat.covariance(this.scores0[var1], this.scores0[var2]);
                if (var1 != var2) {
                    this.rawCovariances[var2][var1] = this.rawCovariances[var1][var2];
                }
            }
        }

        for(var1 = 0; var1 < this.nItems; ++var1) {
            this.rawCovariances[var1][this.nItems] = Stat.covariance(this.scores0[var1], this.rawPersonTotals);
            this.rawCovariances[this.nItems][var1] = this.rawCovariances[var1][this.nItems];
        }

        this.rawCovariances[this.nItems][this.nItems] = Stat.covariance(this.rawPersonTotals, this.rawPersonTotals);
        this.standardizedCovariances = new double[this.nItems + 1][this.nItems + 1];

        for(var1 = 0; var1 < this.nItems; ++var1) {
            for(var2 = var1; var2 < this.nItems; ++var2) {
                this.standardizedCovariances[var1][var2] = Stat.covariance(this.scores0[var1], this.scores0[var2]);
                if (var1 != var2) {
                    this.standardizedCovariances[var2][var1] = this.standardizedCovariances[var1][var2];
                }
            }
        }

        for(var1 = 0; var1 < this.nItems; ++var1) {
            this.standardizedCovariances[var1][this.nItems] = Stat.covariance(this.scores0[var1], this.standardizedPersonTotals);
            this.standardizedCovariances[this.nItems][var1] = this.standardizedCovariances[var1][this.nItems];
        }

        this.standardizedCovariances[this.nItems][this.nItems] = Stat.covariance(this.standardizedPersonTotals, this.standardizedPersonTotals);
        this.rawCorrelationCoefficients = new double[this.nItems + 1][this.nItems + 1];

        for(var1 = 0; var1 < this.nItems; ++var1) {
            this.rawCorrelationCoefficients[var1][var1] = 1.0D;

            for(var2 = var1 + 1; var2 < this.nItems; ++var2) {
                this.rawCorrelationCoefficients[var1][var2] = this.rawCovariances[var1][var2] / Math.sqrt(this.rawCovariances[var1][var1] * this.rawCovariances[var2][var2]);
                if (Fmath.isNaN(this.rawCorrelationCoefficients[var1][var2])) {
                    this.rawCorrelationCoefficients[var1][var2] = 0.0D;
                }

                this.rawCorrelationCoefficients[var2][var1] = this.rawCorrelationCoefficients[var1][var2];
            }
        }

        for(var1 = 0; var1 < this.nItems; ++var1) {
            this.rawCorrelationCoefficients[var1][this.nItems] = this.rawCovariances[var1][this.nItems] / Math.sqrt(this.rawCovariances[var1][var1] * this.rawCovariances[this.nItems][this.nItems]);
            if (Fmath.isNaN(this.rawCorrelationCoefficients[var1][this.nItems])) {
                this.rawCorrelationCoefficients[var1][this.nItems] = 0.0D;
            }

            this.rawCorrelationCoefficients[this.nItems][var1] = this.rawCorrelationCoefficients[var1][this.nItems];
        }

        this.rawCorrelationCoefficients[this.nItems][this.nItems] = 1.0D;
        double[] var7 = new double[this.nItems * (this.nItems - 1) / 2];
        var2 = 0;

        int var4;
        for(int var3 = 0; var3 < this.nItems; ++var3) {
            for(var4 = var3 + 1; var4 < this.nItems; ++var4) {
                var7[var2] = this.rawCorrelationCoefficients[var3][var4];
                ++var2;
            }
        }

        Stat var6 = new Stat(var7);
        if (this.nFactorOption) {
            var6.setDenominatorToN();
        } else {
            var6.setDenominatorToNminusOne();
        }

        this.rawMeanRhoWithoutTotals = var6.mean_as_double();
        this.rawStandardDeviationRhoWithoutTotals = var6.standardDeviation_as_double();
        var7 = new double[this.nItems * (this.nItems + 1) / 2];
        var2 = 0;

        int var5;
        for(var4 = 0; var4 <= this.nItems; ++var4) {
            for(var5 = var4 + 1; var5 <= this.nItems; ++var5) {
                var7[var2] = this.rawCorrelationCoefficients[var4][var5];
                ++var2;
            }
        }

        var6 = new Stat(var7);
        if (this.nFactorOption) {
            var6.setDenominatorToN();
        } else {
            var6.setDenominatorToNminusOne();
        }

        this.rawMeanRhoWithTotals = var6.mean_as_double();
        this.rawStandardDeviationRhoWithTotals = var6.standardDeviation_as_double();
        this.standardizedCorrelationCoefficients = new double[this.nItems + 1][this.nItems + 1];

        for(var4 = 0; var4 < this.nItems; ++var4) {
            this.standardizedCorrelationCoefficients[var4][var4] = 1.0D;

            for(var5 = var4 + 1; var5 < this.nItems; ++var5) {
                this.standardizedCorrelationCoefficients[var4][var5] = this.standardizedCovariances[var4][var5] / Math.sqrt(this.standardizedCovariances[var4][var4] * this.standardizedCovariances[var5][var5]);
                if (Fmath.isNaN(this.standardizedCorrelationCoefficients[var4][var5])) {
                    this.standardizedCorrelationCoefficients[var4][var5] = 0.0D;
                }

                this.standardizedCorrelationCoefficients[var5][var4] = this.standardizedCorrelationCoefficients[var4][var5];
            }
        }

        for(var4 = 0; var4 < this.nItems; ++var4) {
            this.standardizedCorrelationCoefficients[var4][this.nItems] = this.standardizedCovariances[var4][this.nItems] / Math.sqrt(this.standardizedCovariances[var4][var4] * this.standardizedCovariances[this.nItems][this.nItems]);
            if (Fmath.isNaN(this.standardizedCorrelationCoefficients[var4][this.nItems])) {
                this.standardizedCorrelationCoefficients[var4][this.nItems] = 0.0D;
            }

            this.standardizedCorrelationCoefficients[this.nItems][var4] = this.standardizedCorrelationCoefficients[var4][this.nItems];
        }

        this.standardizedCorrelationCoefficients[this.nItems][this.nItems] = 1.0D;
        var7 = new double[this.nItems * (this.nItems - 1) / 2];
        var2 = 0;

        for(var4 = 0; var4 < this.nItems; ++var4) {
            for(var5 = var4 + 1; var5 < this.nItems; ++var5) {
                var7[var2] = this.standardizedCorrelationCoefficients[var4][var5];
                ++var2;
            }
        }

        var6 = new Stat(var7);
        if (this.nFactorOption) {
            var6.setDenominatorToN();
        } else {
            var6.setDenominatorToNminusOne();
        }

        this.standardizedMeanRhoWithoutTotals = var6.mean_as_double();
        this.standardizedStandardDeviationRhoWithoutTotals = var6.standardDeviation_as_double();
        var7 = new double[this.nItems * (this.nItems + 1) / 2];
        var2 = 0;

        for(var4 = 0; var4 <= this.nItems; ++var4) {
            for(var5 = var4 + 1; var5 <= this.nItems; ++var5) {
                var7[var2] = this.standardizedCorrelationCoefficients[var4][var5];
                ++var2;
            }
        }

        var6 = new Stat(var7);
        if (this.nFactorOption) {
            var6.setDenominatorToN();
        } else {
            var6.setDenominatorToNminusOne();
        }

        this.standardizedMeanRhoWithTotals = var6.mean_as_double();
        this.standardizedStandardDeviationRhoWithTotals = var6.standardDeviation_as_double();
        this.covariancesCalculated = true;
    }

    public double[][] rawCovariances() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (!this.covariancesCalculated) {
            this.covariancesAndCorrelationCoefficients();
        }

        return this.rawCovariances;
    }

    public double[][] standardizedCovariances() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (!this.covariancesCalculated) {
            this.covariancesAndCorrelationCoefficients();
        }

        return this.standardizedCovariances;
    }

    public double[][] standardisedCovariances() {
        return this.standardizedCovariances();
    }

    public double rawCovariance(String var1, String var2) {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (!this.covariancesCalculated) {
            this.covariancesAndCorrelationCoefficients();
        }

        int var3 = this.itemIndex(var1);
        int var4 = this.itemIndex(var2);
        return this.rawCovariances[var3 - 1][var4 - 1];
    }

    public double rawCovariance(int var1, int var2) {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (var1 >= 1 && var1 <= this.nItems) {
            if (var2 >= 1 && var2 <= this.nItems) {
                if (!this.covariancesCalculated) {
                    this.covariancesAndCorrelationCoefficients();
                }

                return this.rawCovariances[var1 - 1][var2 - 1];
            } else {
                throw new IllegalArgumentException("The second item index, " + var2 + ", must lie between 1 and the number of items plus one (for totals)," + (this.nItems + 1) + ", inclusive");
            }
        } else {
            throw new IllegalArgumentException("The first item index, " + var1 + ", must lie between 1 and the number of items plus one (for totals)," + (this.nItems + 1) + ", inclusive");
        }
    }

    public double rawCovariance(String var1) {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (!this.covariancesCalculated) {
            this.covariancesAndCorrelationCoefficients();
        }

        int var2 = this.itemIndex(var1);
        return this.rawCovariances[var2 - 1][this.nItems];
    }

    public double rawCovariance(int var1) {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (var1 >= 1 && var1 <= this.nItems) {
            if (!this.covariancesCalculated) {
                this.covariancesAndCorrelationCoefficients();
            }

            return this.rawCovariances[var1 - 1][this.nItems];
        } else {
            throw new IllegalArgumentException("The first item index, " + var1 + ", must lie between 1 and the number of items plus one (for totals)," + (this.nItems + 1) + ", inclusive");
        }
    }

    public double standardizedCovariance(String var1, String var2) {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (!this.covariancesCalculated) {
            this.covariancesAndCorrelationCoefficients();
        }

        int var3 = this.itemIndex(var1);
        int var4 = this.itemIndex(var2);
        return this.standardizedCovariances[var3 + 1][var4 + 1];
    }

    public double standardisedCovariance(String var1, String var2) {
        return this.standardizedCovariance(var1, var2);
    }

    public double standardizedCovariance(int var1, int var2) {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (var1 >= 1 && var1 <= this.nItems) {
            if (var2 >= 1 && var2 <= this.nItems) {
                if (!this.covariancesCalculated) {
                    this.covariancesAndCorrelationCoefficients();
                }

                return this.standardizedCovariances[var1 + 1][var2 + 1];
            } else {
                throw new IllegalArgumentException("The second item index, " + var2 + ", must lie between 1 and the number of items plus one (for totals)," + (this.nItems + 1) + ", inclusive");
            }
        } else {
            throw new IllegalArgumentException("The first item index, " + var1 + ", must lie between 1 and the number of items plus one (for totals)," + (this.nItems + 1) + ", inclusive");
        }
    }

    public double standardisedCovariance(int var1, int var2) {
        return this.standardizedCovariance(var1, var2);
    }

    public double standardizedCovariance(String var1) {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (!this.covariancesCalculated) {
            this.covariancesAndCorrelationCoefficients();
        }

        int var2 = this.itemIndex(var1);
        return this.standardizedCovariances[var2 + 1][this.nItems];
    }

    public double standardisedCovariance(String var1) {
        return this.standardizedCovariance(var1);
    }

    public double standardizedCovariance(int var1) {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (var1 >= 1 && var1 <= this.nItems) {
            if (!this.covariancesCalculated) {
                this.covariancesAndCorrelationCoefficients();
            }

            return this.standardizedCovariances[var1 + 1][this.nItems];
        } else {
            throw new IllegalArgumentException("The first item index, " + var1 + ", must lie between 1 and the number of items plus one (for totals)," + (this.nItems + 1) + ", inclusive");
        }
    }

    public double standardisedCovariance(int var1) {
        return this.standardizedCovariance(var1);
    }

    public double rawAverageCorrelationCoefficients() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (!this.covariancesCalculated) {
            this.covariancesAndCorrelationCoefficients();
        }

        return this.rawMeanRhoWithoutTotals;
    }

    public double rawStandardDeviationCorrelationCoefficients() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (!this.covariancesCalculated) {
            this.covariancesAndCorrelationCoefficients();
        }

        return this.rawStandardDeviationRhoWithoutTotals;
    }

    public double standardizedAverageCorrelationCoefficients() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (!this.covariancesCalculated) {
            this.covariancesAndCorrelationCoefficients();
        }

        return this.standardizedMeanRhoWithoutTotals;
    }

    public double standardisedAverageCorrelationCoefficients() {
        return this.standardizedAverageCorrelationCoefficients();
    }

    public double standardizedStandardDeviationCorrelationCoefficients() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (!this.covariancesCalculated) {
            this.covariancesAndCorrelationCoefficients();
        }

        return this.standardizedStandardDeviationRhoWithoutTotals;
    }

    public double standardisedStandardDeviationCorrelationCoefficients() {
        return this.standardizedStandardDeviationCorrelationCoefficients();
    }

    public double rawAverageCorrelationCoefficientsWithTotals() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (!this.covariancesCalculated) {
            this.covariancesAndCorrelationCoefficients();
        }

        return this.rawMeanRhoWithTotals;
    }

    public double rawStandardDeviationCorrelationCoefficientsWithTotals() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (!this.covariancesCalculated) {
            this.covariancesAndCorrelationCoefficients();
        }

        return this.rawStandardDeviationRhoWithTotals;
    }

    public double standardizedAverageCorrelationCoefficientsWithTotals() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (!this.covariancesCalculated) {
            this.covariancesAndCorrelationCoefficients();
        }

        return this.standardizedMeanRhoWithTotals;
    }

    public double standardisedAverageCorrelationCoefficientsWithTotals() {
        return this.standardizedAverageCorrelationCoefficientsWithTotals();
    }

    public double standardizedStandardDeviationCorrelationCoefficientsWithTotals() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (!this.covariancesCalculated) {
            this.covariancesAndCorrelationCoefficients();
        }

        return this.standardizedStandardDeviationRhoWithTotals;
    }

    public double standardisedStandardDeviationCorrelationCoefficientsWithTotals() {
        return this.standardizedStandardDeviationCorrelationCoefficientsWithTotals();
    }

    public double[][] rawCorrelationCoefficients() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (!this.covariancesCalculated) {
            this.covariancesAndCorrelationCoefficients();
        }

        return this.rawCorrelationCoefficients;
    }

    public double[][] standardizedCorrelationCoefficients() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (!this.covariancesCalculated) {
            this.covariancesAndCorrelationCoefficients();
        }

        return this.standardizedCorrelationCoefficients;
    }

    public double[][] standardisedCorrelationCoefficients() {
        return this.standardizedCorrelationCoefficients();
    }

    public double rawCorrelationCoefficient(String var1, String var2) {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (!this.covariancesCalculated) {
            this.covariancesAndCorrelationCoefficients();
        }

        int var3 = this.itemIndex(var1);
        int var4 = this.itemIndex(var2);
        return this.rawCorrelationCoefficients[var3 - 1][var4 - 1];
    }

    public double rawCorrelationCoefficient(int var1, int var2) {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (var1 >= 1 && var1 <= this.nItems) {
            if (var2 >= 1 && var2 <= this.nItems) {
                if (!this.covariancesCalculated) {
                    this.covariancesAndCorrelationCoefficients();
                }

                return this.rawCorrelationCoefficients[var1 - 1][var2 - 1];
            } else {
                throw new IllegalArgumentException("The second item index, " + var2 + ", must lie between 1 and the number of items plus one (for totals)," + (this.nItems + 1) + ", inclusive");
            }
        } else {
            throw new IllegalArgumentException("The first item index, " + var1 + ", must lie between 1 and the number of items plus one (for totals)," + (this.nItems + 1) + ", inclusive");
        }
    }

    public double rawCorrelationCoefficient(String var1) {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (!this.covariancesCalculated) {
            this.covariancesAndCorrelationCoefficients();
        }

        int var2 = this.itemIndex(var1);
        return this.rawCorrelationCoefficients[var2 - 1][this.nItems];
    }

    public double rawCorrelationCoefficient(int var1) {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (var1 >= 1 && var1 <= this.nItems) {
            if (!this.covariancesCalculated) {
                this.covariancesAndCorrelationCoefficients();
            }

            return this.rawCorrelationCoefficients[var1 - 1][this.nItems];
        } else {
            throw new IllegalArgumentException("The first item index, " + var1 + ", must lie between 1 and the number of items plus one (for totals)," + (this.nItems + 1) + ", inclusive");
        }
    }

    public double standardizedCorrelationCoefficient(String var1, String var2) {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (!this.covariancesCalculated) {
            this.covariancesAndCorrelationCoefficients();
        }

        int var3 = this.itemIndex(var1);
        int var4 = this.itemIndex(var2);
        return this.standardizedCorrelationCoefficients[var3 + 1][var4 + 1];
    }

    public double standardisedCorrelationCoefficient(String var1, String var2) {
        return this.standardizedCorrelationCoefficient(var1, var2);
    }

    public double standardizedCorrelationCoefficient(int var1, int var2) {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (var1 >= 1 && var1 <= this.nItems) {
            if (var2 >= 1 && var2 <= this.nItems) {
                if (!this.covariancesCalculated) {
                    this.covariancesAndCorrelationCoefficients();
                }

                return this.standardizedCorrelationCoefficients[var1 + 1][var2 + 1];
            } else {
                throw new IllegalArgumentException("The second item index, " + var2 + ", must lie between 1 and the number of items plus one (for totals)," + (this.nItems + 1) + ", inclusive");
            }
        } else {
            throw new IllegalArgumentException("The first item index, " + var1 + ", must lie between 1 and the number of items plus one (for totals)," + (this.nItems + 1) + ", inclusive");
        }
    }

    public double standardisedCorrelationCoefficient(int var1, int var2) {
        return this.standardizedCorrelationCoefficient(var1, var2);
    }

    public double standardizedCorrelationCoefficient(String var1) {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (!this.covariancesCalculated) {
            this.covariancesAndCorrelationCoefficients();
        }

        int var2 = this.itemIndex(var1);
        return this.standardizedCorrelationCoefficients[var2 + 1][this.nItems];
    }

    public double standardisedCorrelationCoefficient(String var1) {
        return this.standardizedCorrelationCoefficient(var1);
    }

    public double standardizedCorrelationCoefficient(int var1) {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        if (var1 >= 1 && var1 <= this.nItems) {
            if (!this.covariancesCalculated) {
                this.covariancesAndCorrelationCoefficients();
            }

            return this.standardizedCorrelationCoefficients[var1 + 1][this.nItems];
        } else {
            throw new IllegalArgumentException("The first item index, " + var1 + ", must lie between 1 and the number of items plus one (for totals)," + (this.nItems + 1) + ", inclusive");
        }
    }

    public double standardisedCorrelationCoefficient(int var1) {
        return this.standardizedCorrelationCoefficient(var1);
    }

    public double[][] deleteItem(String var1) {
        int var2 = this.itemIndex(var1);
        return this.deleteItem(var2);
    }

    public double[][] deleteItem(int var1) {
        --var1;
        int var2 = 0;
        double[][] var3 = new double[this.nItems - 1][this.nPersons];

        for(int var4 = 0; var4 < this.nItems; ++var4) {
            if (var4 != var1) {
                var3[var2] = this.scores0[var4];
                ++var2;
            }
        }

        return this.transpose(var3);
    }

    public void rawItemItemPlot(String var1, String var2) {
        int var3 = this.itemIndex(var1);
        int var4 = this.itemIndex(var2);
        this.rawItemItemPlot(var3, var4);
    }

    public void rawItemItemPlot(int var1, int var2) {
        --var1;
        --var2;
        PlotGraph var3 = new PlotGraph(this.scores0[var1], this.scores0[var2]);
        String var4 = "Scores: plot of responses to the item, " + this.itemNames[var1] + ", against those to the item, " + this.itemNames[var2];
        var3.setGraphTitle(var4);
        var3.setXaxisLegend("Responses to the item, " + this.itemNames[var1]);
        var3.setYaxisLegend("Responses to the item, " + this.itemNames[var2]);
        var3.setLine(0);
        var3.setPoint(4);
        var3.plot();
    }

    public void rawItemMeansPlot(String var1) {
        int var2 = this.itemIndex(var1);
        this.rawItemMeansPlot(var2);
    }

    public void rawItemMeansPlot(int var1) {
        --var1;
        PlotGraph var2 = new PlotGraph(this.rawPersonMeans, this.scores0[var1]);
        String var3 = "Scores: plot of responses to the item, " + this.itemNames[var1] + ", against the means of the responses to all items";
        var2.setGraphTitle(var3);
        var2.setXaxisLegend("Mean of the responses to all the items, ");
        var2.setYaxisLegend("Responses to the item, " + this.itemNames[var1]);
        var2.setLine(0);
        var2.setPoint(4);
        var2.plot();
    }

    public void standardizedItemItemPlot(String var1, String var2) {
        int var3 = this.itemIndex(var1);
        int var4 = this.itemIndex(var2);
        this.standardizedItemItemPlot(var3, var4);
    }

    public void standardisedItemItemPlot(String var1, String var2) {
        this.standardizedItemItemPlot(var1, var2);
    }

    public void standardizedItemItemPlot(int var1, int var2) {
        --var1;
        --var2;
        PlotGraph var3 = new PlotGraph(this.standardizedScores0[var1], this.standardizedScores0[var2]);
        String var4 = "Scores: plot of responses to the item, " + this.itemNames[var1] + ", against those to the item, " + this.itemNames[var2];
        var3.setGraphTitle(var4);
        var3.setXaxisLegend("Responses to the item, " + this.itemNames[var1]);
        var3.setYaxisLegend("Responses to the item, " + this.itemNames[var2]);
        var3.setLine(0);
        var3.setPoint(4);
        var3.plot();
    }

    public void standardisedItemItemPlot(int var1, int var2) {
        this.standardizedItemItemPlot(var1, var2);
    }

    public void standardizedItemMeansPlot(String var1) {
        int var2 = this.itemIndex(var1);
        this.standardizedItemMeansPlot(var2);
    }

    public void standardisedItemMeansPlot(String var1) {
        this.standardizedItemMeansPlot(var1);
    }

    public void standardizedItemMeansPlot(int var1) {
        --var1;
        PlotGraph var2 = new PlotGraph(this.standardizedPersonMeans, this.standardizedScores0[var1]);
        String var3 = "Scores: plot of responses to the item, " + this.itemNames[var1] + ", against the means of the responses to all items";
        var2.setGraphTitle(var3);
        var2.setXaxisLegend("Mean of the responses to all the items, ");
        var2.setYaxisLegend("Responses to the item, " + this.itemNames[var1]);
        var2.setLine(0);
        var2.setPoint(4);
        var2.plot();
    }

    public void standardisedItemMeansPlot(int var1) {
        this.standardizedItemMeansPlot(var1);
    }

    public void numberOfDecimalPlaces(int var1) {
        this.trunc = var1;
    }

    public void numberOfDecimalPlacesAll(int var1) {
        this.trunc = var1;
        this.truncAll = true;
    }

    public void setOutputFileType(int var1) {
        this.fileOption = var1;
        this.fileOptionSet = true;
    }

    public void setFileNumbering() {
        this.fileNumberingSet = true;
    }

    public void removeFileNumbering() {
        this.fileNumberingSet = false;
    }

    public String getTitle() {
        return this.title[0];
    }

    public String getInputFileName() {
        return this.inputFilename;
    }

    public void outputProcessedData() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        this.outputFilename = "ScoresOutput";
        if (this.fileOption == 1) {
            this.outputFilename = this.outputFilename + ".txt";
        } else {
            this.outputFilename = this.outputFilename + ".xls";
        }

        String var1 = "Output file name for the processes scores:";
        String var2 = "\nEnter the required name (as a single word) and click OK ";
        String var3 = "\nor simply click OK for default value";
        String var4 = var1 + var2 + var3;
        String var5 = this.outputFilename;
        this.outputFilename = Db.readLine(var4, var5);
        this.outputProcessedData(this.outputFilename);
    }

    public void outputProcessedData(String var1) {
        this.outputProcessedDataCommon(var1, this.originalDataOrder);
    }

    public void outputProcessedDataAlternate() {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        this.outputFilename = "ScoresOutput";
        if (this.fileOption == 1) {
            this.outputFilename = this.outputFilename + ".txt";
        } else {
            this.outputFilename = this.outputFilename + ".xls";
        }

        String var1 = "Output file name for the processes scores:";
        String var2 = "\nEnter the required name (as a single word) and click OK ";
        String var3 = "\nor simply click OK for default value";
        String var4 = var1 + var2 + var3;
        String var5 = this.outputFilename;
        this.outputFilename = Db.readLine(var4, var5);
        this.outputProcessedDataAlternate(this.outputFilename);
    }

    public void outputProcessedDataAlternate(String var1) {
        byte var2 = 0;
        if (this.originalDataOrder == 0) {
            var2 = 1;
        }

        this.outputProcessedDataCommon(var1, var2);
    }

    private void outputProcessedDataCommon(String var1, int var2) {
        if (!this.dataPreprocessed) {
            this.preprocessData();
        }

        this.outputFilename = var1;
        String var3 = null;
        String var4 = null;
        int var5 = var1.indexOf(46);
        if (var5 == -1) {
            if (this.fileOption == 1) {
                this.outputFilename = this.outputFilename + ".txt";
            } else {
                this.outputFilename = this.outputFilename + ".xls";
            }
        } else {
            var4 = var1.substring(var5).trim();
            var3 = var1.substring(0, var5).trim();
            String var6;
            String var7;
            String var8;
            String var9;
            String[] var10;
            String[] var11;
            byte var12;
            int var13;
            if (var4.equalsIgnoreCase(".xls") && this.fileOption == 1) {
                if (this.fileOptionSet) {
                    var6 = "Your entered output file type is .xls";
                    var7 = "\nbut you have chosen a .txt output";
                    var8 = var6 + var7;
                    var9 = "Your output file name extension";
                    var10 = new String[]{var8, "replace it with .txt [text file]"};
                    var11 = new String[]{"Retain", ".txt"};
                    var12 = 1;
                    var13 = Db.optionBox(var9, var10, var11, var12);
                    if (var13 == 2) {
                        this.outputFilename = var3 + ".txt";
                    }
                } else {
                    this.fileOption = 2;
                }
            }

            if (var4.equalsIgnoreCase(".txt") && this.fileOption == 2) {
                if (this.fileOptionSet) {
                    var6 = "Your entered output file type is .txt";
                    var7 = "\nbut you have chosen a .xls output";
                    var8 = var6 + var7;
                    var9 = "Your output file name extension";
                    var10 = new String[]{var8, "replace it with .xls [Excel file]"};
                    var11 = new String[]{"Retain", ".xls"};
                    var12 = 1;
                    var13 = Db.optionBox(var9, var10, var11, var12);
                    if (var13 == 2) {
                        this.outputFilename = var3 + ".xls";
                    }
                } else {
                    this.fileOption = 1;
                }
            }

            if (!var4.equalsIgnoreCase(".txt") && !var4.equalsIgnoreCase(".xls")) {
                var6 = "Your extension is " + var4;
                var7 = "\n    Do you wish to retain it:";
                var8 = var6 + var7;
                var9 = "Your output file name extension";
                var10 = new String[]{var8, "replace it with .txt [text file]", "replace it with .xls [MS Excel file]"};
                var11 = new String[]{"Retain", ".txt", ".xls"};
                var12 = 1;
                var13 = Db.optionBox(var9, var10, var11, var12);
                switch(var13) {
                    case 1:
                        this.fileOption = 1;
                        break;
                    case 2:
                        this.outputFilename = var3 + ".txt";
                        this.fileOption = 1;
                        break;
                    case 3:
                        this.outputFilename = var3 + ".xls";
                        this.fileOption = 2;
                }
            }
        }

        if (var2 == 0) {
            this.title[0] = this.title[0] + "   (output: row per item)";
        } else {
            this.title[0] = this.title[0] + "   (output: row per person)";
        }

        if (this.fileOption == 1) {
            this.outputText(var2);
        } else {
            this.outputExcel(var2);
        }

    }

    private void outputText(int var1) {
        FileOutput var2 = new FileOutput(this.outputFilename);
        var2.println(this.title[0]);
        var2.println("Number of items =   " + this.nItems);
        var2.println("Number of persons = " + this.nPersons);
        var2.println();
        int var3;
        int var4;
        if (var1 == 0) {
            var2.printtab("     ");

            for(var3 = 0; var3 < this.nPersons; ++var3) {
                var2.printtab(this.personNames[var3]);
            }

            var2.println();

            for(var3 = 0; var3 < this.nItems; ++var3) {
                var2.printtab(this.itemNames[var3]);

                for(var4 = 0; var4 < this.nPersons; ++var4) {
                    var2.printtab(Fmath.truncate(this.scores0[var3][var4], this.trunc));
                }

                var2.println();
            }
        } else {
            var2.printtab("     ");

            for(var3 = 0; var3 < this.nItems; ++var3) {
                var2.printtab(this.itemNames[var3]);
            }

            var2.println();

            for(var3 = 0; var3 < this.nPersons; ++var3) {
                var2.printtab(this.personNames[var3]);

                for(var4 = 0; var4 < this.nItems; ++var4) {
                    var2.printtab(Fmath.truncate(this.scores1[var3][var4], this.trunc));
                }

                var2.println();
            }
        }

        var2.close();
    }

    private void outputExcel(int var1) {
        this.outputText(var1);
    }

    public PCA toPCA() {
        PCA var1 = new PCA();
        var1.title = this.title;
        var1.titleLines = this.titleLines;
        var1.inputFilename = this.inputFilename;
        var1.outputFilename = this.outputFilename;
        var1.fileOption = this.fileOption;
        var1.fileOptionSet = this.fileOptionSet;
        var1.fileExtensions = this.fileExtensions;
        var1.fileNumberingSet = this.fileNumberingSet;
        var1.originalDataType = this.originalDataType;
        var1.originalDataOrder = this.originalDataOrder;
        var1.originalData = this.originalData;
        var1.scores0 = Conv.copy(this.scores0);
        var1.originalScores0 = Conv.copy(this.originalScores0);
        var1.standardizedScores0 = Conv.copy(this.standardizedScores0);
        var1.scores1 = Conv.copy(this.scores1);
        var1.originalScores1 = Conv.copy(this.originalScores1);
        var1.standardizedScores1 = Conv.copy(this.standardizedScores1);
        var1.dataEntered = this.dataEntered;
        var1.nItems = this.nItems;
        var1.originalNitems = this.originalNitems;
        var1.itemNames = Conv.copy(this.itemNames);
        var1.originalItemNames = Conv.copy(this.originalItemNames);
        var1.itemNamesSet = this.itemNamesSet;
        var1.nPersons = this.nPersons;
        var1.originalNpersons = this.originalNpersons;
        var1.nScores = this.nScores;
        var1.originalNscores = this.originalNscores;
        var1.otherFalse = this.otherFalse;
        var1.otherTrue = this.otherTrue;
        var1.otherDichotomousDataSet = this.otherDichotomousDataSet;
        var1.dichotomous = Conv.copy(this.dichotomous);
        var1.dichotomousPercentage = Conv.copy(this.dichotomousPercentage);
        var1.dichotomousOverall = this.dichotomousOverall;
        var1.dichotomousCheckDone = this.dichotomousCheckDone;
        var1.letterToNumeralSet = this.letterToNumeralSet;
        var1.ignoreNoResponseRequests = this.ignoreNoResponseRequests;
        var1.itemDeletionPercentage = this.itemDeletionPercentage;
        var1.itemDeletionPercentageSet = this.itemDeletionPercentageSet;
        var1.personDeletionPercentage = this.personDeletionPercentage;
        var1.personDeletionPercentageSet = this.personDeletionPercentageSet;
        var1.replacementOption = this.replacementOption;
        var1.replacementOptionNames = Conv.copy(this.replacementOptionNames);
        var1.replacementOptionSet = this.replacementOptionSet;
        var1.allNoResponseOptionsSet = this.allNoResponseOptionsSet;
        var1.noResponseHandlingSet = this.noResponseHandlingSet;
        var1.nNaN = this.nNaN;
        var1.deletedItems = Conv.copy(this.deletedItems);
        var1.nDeletedItems = this.nDeletedItems;
        var1.deletedItemsIndices = Conv.copy(this.deletedItemsIndices);
        var1.itemIndices = Conv.copy(this.itemIndices);
        var1.deletedPersons = Conv.copy(this.deletedPersons);
        var1.nDeletedPersons = this.nDeletedPersons;
        var1.deletedPersonsIndices = Conv.copy(this.deletedPersonsIndices);
        var1.personIndices = Conv.copy(this.personIndices);
        var1.nReplacements = this.nReplacements;
        var1.replacementIndices = Conv.copy(this.replacementIndices);
        var1.nFactorOption = this.nFactorOption;
        if (this.dataEntered) {
            var1.dataPreprocessed = false;
            var1.preprocessData();
        }

        return var1;
    }

    public Cronbach toCronbach() {
        Cronbach var1 = new Cronbach();
        var1.title = this.title;
        var1.titleLines = this.titleLines;
        var1.inputFilename = this.inputFilename;
        var1.outputFilename = this.outputFilename;
        var1.fileOption = this.fileOption;
        var1.fileOptionSet = this.fileOptionSet;
        var1.fileExtensions = this.fileExtensions;
        var1.fileNumberingSet = this.fileNumberingSet;
        var1.originalDataType = this.originalDataType;
        var1.originalDataOrder = this.originalDataOrder;
        var1.originalData = this.originalData;
        var1.scores0 = Conv.copy(this.scores0);
        var1.originalScores0 = Conv.copy(this.originalScores0);
        var1.standardizedScores0 = Conv.copy(this.standardizedScores0);
        var1.scores1 = Conv.copy(this.scores1);
        var1.originalScores1 = Conv.copy(this.originalScores1);
        var1.standardizedScores1 = Conv.copy(this.standardizedScores1);
        var1.dataEntered = this.dataEntered;
        var1.nItems = this.nItems;
        var1.originalNitems = this.originalNitems;
        var1.itemNames = Conv.copy(this.itemNames);
        var1.originalItemNames = Conv.copy(this.originalItemNames);
        var1.itemNamesSet = this.itemNamesSet;
        var1.nPersons = this.nPersons;
        var1.originalNpersons = this.originalNpersons;
        var1.nScores = this.nScores;
        var1.originalNscores = this.originalNscores;
        var1.otherFalse = this.otherFalse;
        var1.otherTrue = this.otherTrue;
        var1.otherDichotomousDataSet = this.otherDichotomousDataSet;
        var1.dichotomous = Conv.copy(this.dichotomous);
        var1.dichotomousPercentage = Conv.copy(this.dichotomousPercentage);
        var1.dichotomousOverall = this.dichotomousOverall;
        var1.dichotomousCheckDone = this.dichotomousCheckDone;
        var1.letterToNumeralSet = this.letterToNumeralSet;
        var1.ignoreNoResponseRequests = this.ignoreNoResponseRequests;
        var1.itemDeletionPercentage = this.itemDeletionPercentage;
        var1.itemDeletionPercentageSet = this.itemDeletionPercentageSet;
        var1.personDeletionPercentage = this.personDeletionPercentage;
        var1.personDeletionPercentageSet = this.personDeletionPercentageSet;
        var1.replacementOption = this.replacementOption;
        var1.replacementOptionNames = Conv.copy(this.replacementOptionNames);
        var1.replacementOptionSet = this.replacementOptionSet;
        var1.allNoResponseOptionsSet = this.allNoResponseOptionsSet;
        var1.noResponseHandlingSet = this.noResponseHandlingSet;
        var1.nNaN = this.nNaN;
        var1.deletedItems = Conv.copy(this.deletedItems);
        var1.nDeletedItems = this.nDeletedItems;
        var1.deletedItemsIndices = Conv.copy(this.deletedItemsIndices);
        var1.itemIndices = Conv.copy(this.itemIndices);
        var1.deletedPersons = Conv.copy(this.deletedPersons);
        var1.nDeletedPersons = this.nDeletedPersons;
        var1.deletedPersonsIndices = Conv.copy(this.deletedPersonsIndices);
        var1.personIndices = Conv.copy(this.personIndices);
        var1.nReplacements = this.nReplacements;
        var1.replacementIndices = Conv.copy(this.replacementIndices);
        var1.nFactorOption = this.nFactorOption;
        if (this.dataEntered) {
            var1.dataPreprocessed = false;
            var1.preprocessData();
        }

        return var1;
    }
}
