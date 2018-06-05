//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.analysis;

import com.flanagan.io.Db;
import com.flanagan.io.FileOutput;
import com.flanagan.math.ArrayMaths;
import com.flanagan.math.Fmath;
import java.text.DateFormat;
import java.util.Date;

public class Cronbach extends Scores {
    private double rawAlpha = 0.0D / 0.0;
    private boolean rawAlphaCalculated = false;
    private double standardizedAlpha = 0.0D / 0.0;
    private boolean standardizedAlphaCalculated = false;
    private int deletedItemIndex = -1;
    private String deletedFilename = null;

    public Cronbach() {
    }

    public double rawAlpha() {
        if (!this.rawAlphaCalculated) {
            if (this.nItems == 1) {
                System.out.println("Method rawAlpha: only one item - alpha cannot be calculated - NaN returned");
                this.rawAlpha = 0.0D / 0.0;
            } else {
                if (!this.dataPreprocessed) {
                    this.preprocessData();
                }

                double var1 = this.rawAllResponsesTotal * this.rawAllResponsesTotal;
                double var3 = 0.0D;

                for(int var5 = 0; var5 < this.nItems; ++var5) {
                    for(int var6 = 0; var6 < this.nPersons; ++var6) {
                        var3 += this.scores1[var6][var5] * this.scores1[var6][var5];
                    }
                }

                double var20 = 0.0D;

                for(int var7 = 0; var7 < this.nItems; ++var7) {
                    var20 += this.rawItemTotals[var7] * this.rawItemTotals[var7] / (double)this.nPersons;
                }

                double var21 = 0.0D;

                for(int var9 = 0; var9 < this.nPersons; ++var9) {
                    var21 += this.rawPersonTotals[var9] * this.rawPersonTotals[var9] / (double)this.nItems;
                }

                double var22 = var21 - var1 / (double)this.nScores;
                double var11 = var3 - var20 - var21 + var1 / (double)this.nScores;
                int var13 = this.nItems - 1;
                int var14 = this.nPersons - 1;
                int var15 = var13 * var14;
                double var16 = var22 / (double)var14;
                double var18 = var11 / (double)var15;
                this.rawAlpha = (var16 - var18) / var16;
                this.rawAlphaCalculated = true;
            }
        }

        return this.rawAlpha;
    }

    public double standardizedAlpha() {
        if (!this.standardizedAlphaCalculated) {
            if (this.nItems == 1) {
                System.out.println("Method standardizedAlpha: only one item - alpha cannot be calculated - NaN returned");
                this.rawAlpha = 0.0D / 0.0;
            } else {
                if (!this.dataPreprocessed) {
                    this.preprocessData();
                }

                if (!this.covariancesCalculated) {
                    this.covariancesAndCorrelationCoefficients();
                }

                this.standardizedAlpha = (double)this.nItems * this.rawMeanRhoWithoutTotals / (1.0D + (double)(this.nItems - 1) * this.rawMeanRhoWithoutTotals);
                this.standardizedAlphaCalculated = true;
            }
        }

        return this.standardizedAlpha;
    }

    public double standardisedAlpha() {
        return this.standardizedAlpha();
    }

    public void analysis() {
        this.outputFilename = "CronbachOutput";
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

    }

    private void analysisExcel() {
        FileOutput var1 = null;
        if (this.fileNumberingSet) {
            var1 = new FileOutput(this.outputFilename, 'n');
        } else {
            var1 = new FileOutput(this.outputFilename);
        }

        if (!this.rawAlphaCalculated) {
            this.rawAlpha();
        }

        if (!this.standardizedAlphaCalculated) {
            this.standardizedAlpha();
        }

        var1.println("CRONBACH'S ALPHA RELIABILITY ESTIMATOR");
        var1.println("Program: Cronbach - Analysis Output");

        for(int var2 = 0; var2 < this.titleLines; ++var2) {
            var1.println(this.title[var2]);
        }

        Date var32 = new Date();
        String var3 = DateFormat.getDateInstance().format(var32);
        String var4 = DateFormat.getTimeInstance().format(var32);
        var1.println("Program executed at " + var4 + " on " + var3);
        var1.println();
        var1.println("RELIABILITY ESTIMATORS");
        var1.println("Cronbach's coefficient alpha");
        var1.printtab("Raw fillData                  ");
        var1.println(Fmath.truncate(this.rawAlpha, this.trunc));
        var1.printtab("Standardized fillData           ");
        var1.println(Fmath.truncate(this.standardizedAlpha, this.trunc));
        var1.println();
        var1.println("Average of the inter-item correlation coefficients, excluding item totals");
        var1.printtab("Raw fillData                  ");
        var1.println(Fmath.truncate(this.rawMeanRhoWithoutTotals, this.trunc));
        var1.printtab("Standardized fillData           ");
        var1.println(Fmath.truncate(this.standardizedMeanRhoWithoutTotals, this.trunc));
        var1.println();
        var1.println("Average of the inter-item correlation coefficients, including item totals");
        var1.printtab("Raw fillData                  ");
        var1.println(Fmath.truncate(this.rawMeanRhoWithTotals, this.trunc));
        var1.printtab("Standardized fillData           ");
        var1.println(Fmath.truncate(this.standardizedMeanRhoWithTotals, this.trunc));
        var1.println();
        var1.println("'NO RESPONSE' DELETIONS AND REPLACEMENTS");
        boolean var5 = false;
        int var6;
        if (this.nDeletedPersons != 0) {
            var5 = true;
            var1.printtab("Number of persons deleted ");
            var1.println(this.nDeletedPersons);
            var1.printtab("Indices of deleted persons: ");

            for(var6 = 0; var6 < this.nDeletedPersons; ++var6) {
                var1.printtab(this.deletedPersonsIndices[var6] + 1);
            }

            var1.println();
        } else {
            var1.println("No persons were deleted ");
        }

        if (this.nDeletedItems != 0) {
            var5 = true;
            var1.printtab("Number of items deleted ");
            var1.println(this.nDeletedItems);
            var1.printtab("Names of deleted items: ");

            for(var6 = 0; var6 < this.nDeletedItems; ++var6) {
                var1.printtab(this.originalItemNames[this.deletedItemsIndices[var6]]);
            }

            var1.println();
        } else {
            var1.println("No items were deleted ");
        }

        if (this.nReplacements != 0) {
            var1.printtab("Number of 'no responses' replaced ");
            var1.println(this.nReplacements);
            var1.printtab("Item name and person index of replacements: ");

            for(var6 = 0; var6 < this.nReplacements; ++var6) {
                var1.printtab(this.replacementIndices[var6] + " ");
            }

            var1.println();
            var1.printtab("Replacement option: ");
            var1.println(this.replacementOptionNames[this.replacementOption - 1]);
            var1.println();
        } else if (var5) {
            var1.println("No 'no response' replacements, other than any above deletions, were made ");
        } else {
            var1.println("No 'no response' replacements were made ");
        }

        var1.println();
        var1.printtab("Number of items used         ");
        var1.println(this.nItems);
        var1.printtab("Number of persons used   ");
        var1.println(this.nPersons);
        var1.println();
        var1.println("CORRELATION COEFFICIENTS");
        var1.println("Correlation coefficients between items  -  raw fillData");
        var1.printtab("    ");

        for(var6 = 0; var6 <= this.nItems; ++var6) {
            var1.printtab(this.itemNames[var6]);
        }

        var1.println();

        int var7;
        for(var6 = 0; var6 <= this.nItems; ++var6) {
            var1.printtab(this.itemNames[var6]);

            for(var7 = 0; var7 <= this.nItems; ++var7) {
                var1.printtab(Fmath.truncate(this.rawCorrelationCoefficients[var6][var7], this.trunc));
            }

            var1.println();
        }

        var1.println();
        var1.print("Average inter-item correlation coefficient (excluding total)                    ");
        var1.println(Fmath.truncate(this.rawMeanRhoWithoutTotals, this.trunc));
        var1.print("Standard deviation of the inter-item correlation coefficient (excluding total)  ");
        var1.println(Fmath.truncate(this.rawStandardDeviationRhoWithoutTotals, this.trunc));
        var1.print("Average inter-item correlation coefficient (including total)                    ");
        var1.println(Fmath.truncate(this.rawMeanRhoWithTotals, this.trunc));
        var1.print("Standard deviation of the inter-item correlation coefficient (including total)  ");
        var1.println(Fmath.truncate(this.rawStandardDeviationRhoWithTotals, this.trunc));
        var1.println();
        var1.println("Correlation coefficients between items  -  standardized fillData");
        var1.printtab("    ");

        for(var6 = 0; var6 <= this.nItems; ++var6) {
            var1.printtab(this.itemNames[var6]);
        }

        var1.println();

        for(var6 = 0; var6 <= this.nItems; ++var6) {
            var1.printtab(this.itemNames[var6]);

            for(var7 = 0; var7 <= this.nItems; ++var7) {
                var1.printtab(Fmath.truncate(this.standardizedCorrelationCoefficients[var6][var7], this.trunc));
            }

            var1.println();
        }

        var1.println();
        var1.print("Average inter-item correlation coefficient (excluding total)                    ");
        var1.println(Fmath.truncate(this.standardizedMeanRhoWithoutTotals, this.trunc));
        var1.print("Standard deviation of the inter-item correlation coefficient (excluding total)  ");
        var1.println(Fmath.truncate(this.standardizedStandardDeviationRhoWithoutTotals, this.trunc));
        var1.print("Average inter-item correlation coefficient (including total)                    ");
        var1.println(Fmath.truncate(this.standardizedMeanRhoWithTotals, this.trunc));
        var1.print("Standard deviation of the inter-item correlation coefficient (including total)  ");
        var1.println(Fmath.truncate(this.standardizedStandardDeviationRhoWithTotals, this.trunc));
        var1.println();
        var1.println("ITEMS: MEANS, STANDARD DEVIATIONS, SKEWNESS AND KURTOSIS");
        var1.println("Raw fillData");
        var1.printtab("item ");
        var1.printtab("mean");
        var1.printtab("standard");
        var1.printtab("moment");
        var1.printtab("median");
        var1.printtab("quartile");
        var1.printtab("kurtosis");
        var1.println("dichotomous");
        var1.printtab("    ");
        var1.printtab("    ");
        var1.printtab("deviation");
        var1.printtab("skewness");
        var1.printtab("skewness");
        var1.printtab("skewness");
        var1.printtab("excess  ");
        var1.println("percentage");

        for(var6 = 0; var6 < this.nItems; ++var6) {
            var1.printtab(this.itemNames[var6]);
            var1.printtab(Fmath.truncate(this.rawItemMeans[var6], this.trunc));
            var1.printtab(Fmath.truncate(this.rawItemStandardDeviations[var6], this.trunc));
            var1.printtab(Fmath.truncate(this.rawItemMomentSkewness[var6], this.trunc));
            var1.printtab(Fmath.truncate(this.rawItemMedianSkewness[var6], this.trunc));
            var1.printtab(Fmath.truncate(this.rawItemQuartileSkewness[var6], this.trunc));
            var1.printtab(Fmath.truncate(this.rawItemKurtosisExcess[var6], this.trunc));
            var1.println(Fmath.truncate(this.dichotomousPercentage[var6], 1));
        }

        var1.println();
        var1.println("ITEMS: MINIMA, MAXIMA, MEDIANS, RANGES AND TOTALS");
        var1.println("raw fillData");
        var1.printtab("item ");
        var1.printtab("minimum");
        var1.printtab("maximum");
        var1.printtab("median");
        var1.printtab("range");
        var1.printtab("total");
        var1.println("dichotomous");
        var1.printtab("    ");
        var1.printtab("    ");
        var1.printtab("     ");
        var1.printtab("     ");
        var1.printtab("     ");
        var1.printtab("     ");
        var1.println("percentage");

        for(var6 = 0; var6 < this.nItems; ++var6) {
            var1.printtab(this.itemNames[var6]);
            var1.printtab(Fmath.truncate(this.rawItemMinima[var6], this.trunc));
            var1.printtab(Fmath.truncate(this.rawItemMaxima[var6], this.trunc));
            var1.printtab(Fmath.truncate(this.rawItemMedians[var6], this.trunc));
            var1.printtab(Fmath.truncate(this.rawItemRanges[var6], this.trunc));
            var1.printtab(Fmath.truncate(this.rawItemTotals[var6], this.trunc));
            var1.println(Fmath.truncate(this.dichotomousPercentage[var6], 1));
        }

        var1.println();
        var1.printtab("item");
        var1.printtab("mean");
        var1.printtab("standard");
        var1.printtab("variance");
        var1.printtab("minimum");
        var1.printtab("maximum");
        var1.println("range");
        var1.printtab("statistic    ");
        var1.printtab("    ");
        var1.printtab("deviation");
        var1.printtab("     ");
        var1.printtab("     ");
        var1.printtab("     ");
        var1.printtab("     ");
        var1.println("     ");
        var1.printtab("item means");
        var1.printtab(Fmath.truncate(this.rawItemMeansMean, this.trunc));
        var1.printtab(Fmath.truncate(this.rawItemMeansSd, this.trunc));
        var1.printtab(Fmath.truncate(this.rawItemMeansVar, this.trunc));
        var1.printtab(Fmath.truncate(this.rawItemMeansMin, this.trunc));
        var1.printtab(Fmath.truncate(this.rawItemMeansMax, this.trunc));
        var1.println(Fmath.truncate(this.rawItemMeansRange, this.trunc));
        var1.printtab("item standard deviations");
        var1.printtab(Fmath.truncate(this.rawItemStandardDeviationsMean, this.trunc));
        var1.printtab(Fmath.truncate(this.rawItemStandardDeviationsSd, this.trunc));
        var1.printtab(Fmath.truncate(this.rawItemStandardDeviationsVar, this.trunc));
        var1.printtab(Fmath.truncate(this.rawItemStandardDeviationsMin, this.trunc));
        var1.printtab(Fmath.truncate(this.rawItemStandardDeviationsMax, this.trunc));
        var1.println(Fmath.truncate(this.rawItemStandardDeviationsRange, this.trunc));
        var1.printtab("item variances");
        var1.printtab(Fmath.truncate(this.rawItemVariancesMean, this.trunc));
        var1.printtab(Fmath.truncate(this.rawItemVariancesSd, this.trunc));
        var1.printtab(Fmath.truncate(this.rawItemVariancesVar, this.trunc));
        var1.printtab(Fmath.truncate(this.rawItemVariancesMin, this.trunc));
        var1.printtab(Fmath.truncate(this.rawItemVariancesMax, this.trunc));
        var1.println(Fmath.truncate(this.rawItemVariancesRange, this.trunc));
        var1.printtab("item mimima");
        var1.printtab(Fmath.truncate(this.rawItemMinimaMean, this.trunc));
        var1.printtab(Fmath.truncate(this.rawItemMinimaSd, this.trunc));
        var1.printtab(Fmath.truncate(this.rawItemMinimaVar, this.trunc));
        var1.printtab(Fmath.truncate(this.rawItemMinimaMin, this.trunc));
        var1.printtab(Fmath.truncate(this.rawItemMinimaMax, this.trunc));
        var1.println(Fmath.truncate(this.rawItemMinimaRange, this.trunc));
        var1.printtab("item maxima");
        var1.printtab(Fmath.truncate(this.rawItemMaximaMean, this.trunc));
        var1.printtab(Fmath.truncate(this.rawItemMaximaSd, this.trunc));
        var1.printtab(Fmath.truncate(this.rawItemMaximaVar, this.trunc));
        var1.printtab(Fmath.truncate(this.rawItemMaximaMin, this.trunc));
        var1.printtab(Fmath.truncate(this.rawItemMaximaMax, this.trunc));
        var1.println(Fmath.truncate(this.rawItemMaximaRange, this.trunc));
        var1.printtab("item medians");
        var1.printtab(Fmath.truncate(this.rawItemMediansMean, this.trunc));
        var1.printtab(Fmath.truncate(this.rawItemMediansSd, this.trunc));
        var1.printtab(Fmath.truncate(this.rawItemMediansVar, this.trunc));
        var1.printtab(Fmath.truncate(this.rawItemMediansMin, this.trunc));
        var1.printtab(Fmath.truncate(this.rawItemMediansMax, this.trunc));
        var1.println(Fmath.truncate(this.rawItemMediansRange, this.trunc));
        var1.printtab("item ranges");
        var1.printtab(Fmath.truncate(this.rawItemRangesMean, this.trunc));
        var1.printtab(Fmath.truncate(this.rawItemRangesSd, this.trunc));
        var1.printtab(Fmath.truncate(this.rawItemRangesVar, this.trunc));
        var1.printtab(Fmath.truncate(this.rawItemRangesMin, this.trunc));
        var1.printtab(Fmath.truncate(this.rawItemRangesMax, this.trunc));
        var1.println(Fmath.truncate(this.rawItemRangesRange, this.trunc));
        var1.printtab("item totals");
        var1.printtab(Fmath.truncate(this.rawItemTotalsMean, this.trunc));
        var1.printtab(Fmath.truncate(this.rawItemTotalsSd, this.trunc));
        var1.printtab(Fmath.truncate(this.rawItemTotalsVar, this.trunc));
        var1.printtab(Fmath.truncate(this.rawItemTotalsMin, this.trunc));
        var1.printtab(Fmath.truncate(this.rawItemTotalsMax, this.trunc));
        var1.println(Fmath.truncate(this.rawItemTotalsRange, this.trunc));
        var1.println();
        var1.println("Standardized fillData");
        var1.println("ITEMS: MEANS, STANDARD DEVIATIONS, SKEWNESS AND KURTOSIS");
        var1.printtab("item ");
        var1.printtab("mean");
        var1.printtab("standard");
        var1.printtab("moment");
        var1.printtab("median");
        var1.printtab("quartile");
        var1.println("kurtosis");
        var1.printtab("    ");
        var1.printtab("    ");
        var1.printtab("deviation");
        var1.printtab("skewness");
        var1.printtab("skewness");
        var1.printtab("skewness");
        var1.println("excess  ");

        for(var6 = 0; var6 < this.nItems; ++var6) {
            var1.printtab(this.itemNames[var6]);
            var1.printtab(Fmath.truncate(this.standardizedItemMeans[var6], this.trunc));
            var1.printtab(Fmath.truncate(this.standardizedItemStandardDeviations[var6], this.trunc));
            var1.printtab(Fmath.truncate(this.standardizedItemMomentSkewness[var6], this.trunc));
            var1.printtab(Fmath.truncate(this.standardizedItemMedianSkewness[var6], this.trunc));
            var1.printtab(Fmath.truncate(this.standardizedItemQuartileSkewness[var6], this.trunc));
            var1.println(Fmath.truncate(this.standardizedItemKurtosisExcess[var6], this.trunc));
        }

        var1.println();
        var1.println("ITEMS: MINIMA, MAXIMA, MEDIANS, RANGES AND TOTALS");
        var1.println("Standardized fillData");
        var1.printtab("item ");
        var1.printtab("minimum");
        var1.printtab("maximum");
        var1.printtab("median");
        var1.printtab("range");
        var1.println("total");
        var1.printtab("    ");
        var1.printtab("    ");
        var1.printtab("     ");
        var1.printtab("     ");
        var1.printtab("     ");
        var1.println("     ");

        for(var6 = 0; var6 < this.nItems; ++var6) {
            var1.printtab(this.itemNames[var6]);
            var1.printtab(Fmath.truncate(this.standardizedItemMinima[var6], this.trunc));
            var1.printtab(Fmath.truncate(this.standardizedItemMaxima[var6], this.trunc));
            var1.printtab(Fmath.truncate(this.standardizedItemMedians[var6], this.trunc));
            var1.printtab(Fmath.truncate(this.standardizedItemRanges[var6], this.trunc));
            var1.println(Fmath.truncate(this.standardizedItemTotals[var6], this.trunc));
        }

        var1.println();
        var1.printtab("item");
        var1.printtab("mean");
        var1.printtab("standard");
        var1.printtab("variance");
        var1.printtab("minimum");
        var1.printtab("maximum");
        var1.println("range");
        var1.printtab("statistic    ");
        var1.printtab("    ");
        var1.printtab("deviation");
        var1.printtab("     ");
        var1.printtab("     ");
        var1.printtab("     ");
        var1.printtab("     ");
        var1.println("     ");
        var1.printtab("item means");
        var1.printtab(Fmath.truncate(this.standardizedItemMeansMean, this.trunc));
        var1.printtab(Fmath.truncate(this.standardizedItemMeansSd, this.trunc));
        var1.printtab(Fmath.truncate(this.standardizedItemMeansVar, this.trunc));
        var1.printtab(Fmath.truncate(this.standardizedItemMeansMin, this.trunc));
        var1.printtab(Fmath.truncate(this.standardizedItemMeansMax, this.trunc));
        var1.println(Fmath.truncate(this.standardizedItemMeansRange, this.trunc));
        var1.printtab("item standard deviations");
        var1.printtab(Fmath.truncate(this.standardizedItemStandardDeviationsMean, this.trunc));
        var1.printtab(Fmath.truncate(this.standardizedItemStandardDeviationsSd, this.trunc));
        var1.printtab(Fmath.truncate(this.standardizedItemStandardDeviationsVar, this.trunc));
        var1.printtab(Fmath.truncate(this.standardizedItemStandardDeviationsMin, this.trunc));
        var1.printtab(Fmath.truncate(this.standardizedItemStandardDeviationsMax, this.trunc));
        var1.println(Fmath.truncate(this.standardizedItemStandardDeviationsRange, this.trunc));
        var1.printtab("item variances");
        var1.printtab(Fmath.truncate(this.standardizedItemVariancesMean, this.trunc));
        var1.printtab(Fmath.truncate(this.standardizedItemVariancesSd, this.trunc));
        var1.printtab(Fmath.truncate(this.standardizedItemVariancesVar, this.trunc));
        var1.printtab(Fmath.truncate(this.standardizedItemVariancesMin, this.trunc));
        var1.printtab(Fmath.truncate(this.standardizedItemVariancesMax, this.trunc));
        var1.println(Fmath.truncate(this.standardizedItemVariancesRange, this.trunc));
        var1.printtab("item mimima");
        var1.printtab(Fmath.truncate(this.standardizedItemMinimaMean, this.trunc));
        var1.printtab(Fmath.truncate(this.standardizedItemMinimaSd, this.trunc));
        var1.printtab(Fmath.truncate(this.standardizedItemMinimaVar, this.trunc));
        var1.printtab(Fmath.truncate(this.standardizedItemMinimaMin, this.trunc));
        var1.printtab(Fmath.truncate(this.standardizedItemMinimaMax, this.trunc));
        var1.println(Fmath.truncate(this.standardizedItemMinimaRange, this.trunc));
        var1.printtab("item maxima");
        var1.printtab(Fmath.truncate(this.standardizedItemMaximaMean, this.trunc));
        var1.printtab(Fmath.truncate(this.standardizedItemMaximaSd, this.trunc));
        var1.printtab(Fmath.truncate(this.standardizedItemMaximaVar, this.trunc));
        var1.printtab(Fmath.truncate(this.standardizedItemMaximaMin, this.trunc));
        var1.printtab(Fmath.truncate(this.standardizedItemMaximaMax, this.trunc));
        var1.println(Fmath.truncate(this.standardizedItemMaximaRange, this.trunc));
        var1.print("item medians");
        var1.print(Fmath.truncate(this.rawItemMediansMean, this.trunc));
        var1.print(Fmath.truncate(this.rawItemMediansSd, this.trunc));
        var1.print(Fmath.truncate(this.rawItemMediansVar, this.trunc));
        var1.print(Fmath.truncate(this.rawItemMediansMin, this.trunc));
        var1.print(Fmath.truncate(this.rawItemMediansMax, this.trunc));
        var1.println(Fmath.truncate(this.rawItemMediansRange, this.trunc));
        var1.printtab("item ranges");
        var1.printtab(Fmath.truncate(this.standardizedItemRangesMean, this.trunc));
        var1.printtab(Fmath.truncate(this.standardizedItemRangesSd, this.trunc));
        var1.printtab(Fmath.truncate(this.standardizedItemRangesVar, this.trunc));
        var1.printtab(Fmath.truncate(this.standardizedItemRangesMin, this.trunc));
        var1.printtab(Fmath.truncate(this.standardizedItemRangesMax, this.trunc));
        var1.println(Fmath.truncate(this.standardizedItemRangesRange, this.trunc));
        var1.printtab("item totals");
        var1.printtab(Fmath.truncate(this.standardizedItemTotalsMean, this.trunc));
        var1.printtab(Fmath.truncate(this.standardizedItemTotalsSd, this.trunc));
        var1.printtab(Fmath.truncate(this.standardizedItemTotalsVar, this.trunc));
        var1.printtab(Fmath.truncate(this.standardizedItemTotalsMin, this.trunc));
        var1.printtab(Fmath.truncate(this.standardizedItemTotalsMax, this.trunc));
        var1.println(Fmath.truncate(this.standardizedItemTotalsRange, this.trunc));
        var1.println();
        var1.println("DELETION OF ITEMS");
        var1.printtab("                ");
        var1.printtab("Raw fillData        ");
        var1.printtab("                ");
        var1.printtab("                ");
        var1.printtab("                ");
        var1.println("Standardized fillData");
        var1.printtab("Deleted item");
        var1.printtab("Alpha       ");
        var1.printtab("Correlation ");
        var1.printtab("Average     ");
        var1.printtab("Average     ");
        var1.printtab("Alpha       ");
        var1.printtab("Correlation ");
        var1.printtab("Average     ");
        var1.println("Average     ");
        var1.printtab("           ");
        var1.printtab("           ");
        var1.printtab("coefficient");
        var1.printtab("inter-item ");
        var1.printtab("inter-item ");
        var1.printtab("           ");
        var1.printtab("coefficient ");
        var1.printtab("inter-item ");
        var1.println("inter-item ");
        var1.printtab("           ");
        var1.printtab("           ");
        var1.printtab("with total ");
        var1.printtab("correlation");
        var1.printtab("correlation");
        var1.printtab("           ");
        var1.printtab("with total ");
        var1.printtab("correlation");
        var1.println("correlation");
        var1.printtab("           ");
        var1.printtab("           ");
        var1.printtab("           ");
        var1.printtab("coefficient");
        var1.printtab("coefficient");
        var1.printtab("           ");
        var1.printtab("           ");
        var1.printtab("coefficient");
        var1.println("coefficient");
        var1.printtab("              ");
        var1.printtab("              ");
        var1.printtab("              ");
        var1.printtab("without totals");
        var1.printtab("with totals   ");
        var1.printtab("              ");
        var1.printtab("              ");
        var1.printtab("without totals");
        var1.println("with totals   ");
        double[] var34 = new double[this.nItems];
        double[] var33 = new double[this.nItems];
        double[] var8 = new double[this.nItems];
        double[] var9 = new double[this.nItems];

        int var10;
        int var11;
        for(var10 = 0; var10 < this.nItems; ++var10) {
            var11 = var10 + 1;
            double[][] var12 = this.deleteItem(var11);
            Cronbach var13 = new Cronbach();
            var13.enterScoresAsRowPerPerson(var12);
            double var14 = var13.rawAlpha();
            var34[var10] = var14;
            double var16 = var13.rawAverageCorrelationCoefficientsWithTotals();
            double var18 = var13.rawAverageCorrelationCoefficients();
            double[] var20 = var13.rawPersonTotals();
            double var21 = Stat.corrCoeff(var20, this.scores0[var10]);
            var8[var10] = var21;
            double var23 = var13.standardizedAlpha();
            var33[var10] = var23;
            double var25 = var13.standardizedAverageCorrelationCoefficientsWithTotals();
            double var27 = var13.standardizedAverageCorrelationCoefficients();
            double[] var29 = var13.standardizedPersonTotals();
            double var30 = Stat.corrCoeff(var29, this.scores0[var10]);
            var9[var10] = var30;
            var1.printtab(this.itemNames[var10]);
            var1.printtab(Fmath.truncate(var14, this.trunc));
            var1.printtab(Fmath.truncate(var21, this.trunc));
            var1.printtab(Fmath.truncate(var18, this.trunc));
            var1.printtab(Fmath.truncate(var16, this.trunc));
            var1.printtab(Fmath.truncate(var23, this.trunc));
            var1.printtab(Fmath.truncate(var30, this.trunc));
            var1.printtab(Fmath.truncate(var27, this.trunc));
            var1.println(Fmath.truncate(var25, this.trunc));
        }

        var1.println();
        var1.printtab("No item deleted");
        var1.printtab(Fmath.truncate(this.rawAlpha, this.trunc));
        var1.printtab("   ");
        var1.printtab(Fmath.truncate(this.rawMeanRhoWithoutTotals, this.trunc));
        var1.printtab(Fmath.truncate(this.rawMeanRhoWithTotals, this.trunc));
        var1.printtab(Fmath.truncate(this.standardizedAlpha, this.trunc));
        var1.printtab("   ");
        var1.printtab(Fmath.truncate(this.standardizedMeanRhoWithoutTotals, this.trunc));
        var1.println(Fmath.truncate(this.standardizedMeanRhoWithTotals, this.trunc));
        var1.println();
        this.deletedItemDataFile(var34, var8, var33, var9);
        var1.println("INDIVIDUALS - raw fillData");
        var1.printtab("person ");
        var1.printtab("mean");
        var1.printtab("standard");
        var1.printtab("minimum");
        var1.printtab("maximum");
        var1.printtab("range");
        var1.printtab("total");
        var1.println("scores:");
        var1.printtab("    ");
        var1.printtab("    ");
        var1.printtab("deviation");
        var1.printtab("     ");
        var1.printtab("     ");
        var1.printtab("     ");
        var1.printtab("     ");

        for(var10 = 0; var10 < this.nItems; ++var10) {
            var1.printtab(this.itemNames[var10]);
        }

        var1.println();

        for(var10 = 0; var10 < this.nPersons; ++var10) {
            var1.printtab(this.personIndices[var10] + 1);
            var1.printtab(Fmath.truncate(this.rawPersonMeans[var10], this.trunc));
            var1.printtab(Fmath.truncate(this.rawPersonStandardDeviations[var10], this.trunc));
            var1.printtab(Fmath.truncate(this.rawPersonMinima[var10], this.trunc));
            var1.printtab(Fmath.truncate(this.rawPersonMaxima[var10], this.trunc));
            var1.printtab(Fmath.truncate(this.rawPersonRanges[var10], this.trunc));
            var1.printtab(Fmath.truncate(this.rawPersonTotals[var10], this.trunc));

            for(var11 = 0; var11 < this.nItems; ++var11) {
                var1.printtab(Fmath.truncate(this.scores1[var10][var11], this.trunc));
            }

            var1.println();
        }

        var1.println();
        var1.println("INDIVIDUALS - standardized fillData");
        var1.printtab("person ");
        var1.printtab("mean");
        var1.printtab("standard");
        var1.printtab("minimum");
        var1.printtab("maximum");
        var1.printtab("range");
        var1.printtab("total");
        var1.println("scores:");
        var1.printtab("    ");
        var1.printtab("    ");
        var1.printtab("deviation");
        var1.printtab("     ");
        var1.printtab("     ");
        var1.printtab("     ");
        var1.printtab("     ");

        for(var10 = 0; var10 < this.nItems; ++var10) {
            var1.printtab(this.itemNames[var10]);
        }

        var1.println();

        for(var10 = 0; var10 < this.nPersons; ++var10) {
            var1.printtab(this.personIndices[var10] + 1);
            var1.printtab(Fmath.truncate(this.standardizedPersonMeans[var10], this.trunc));
            var1.printtab(Fmath.truncate(this.standardizedPersonStandardDeviations[var10], this.trunc));
            var1.printtab(Fmath.truncate(this.standardizedPersonMinima[var10], this.trunc));
            var1.printtab(Fmath.truncate(this.standardizedPersonMaxima[var10], this.trunc));
            var1.printtab(Fmath.truncate(this.standardizedPersonRanges[var10], this.trunc));
            var1.printtab(Fmath.truncate(this.standardizedPersonTotals[var10], this.trunc));

            for(var11 = 0; var11 < this.nItems; ++var11) {
                var1.printtab(Fmath.truncate(this.standardizedScores1[var10][var11], this.trunc));
            }

            var1.println();
        }

        var1.println();
        var1.println("ALL SCORES - raw fillData");
        var1.printtab("mean");
        var1.printtab("standard");
        var1.printtab("minimum");
        var1.printtab("maximum");
        var1.printtab("range");
        var1.println("overall");
        var1.printtab("    ");
        var1.printtab("deviation");
        var1.printtab("     ");
        var1.printtab("     ");
        var1.printtab("     ");
        var1.println("total");
        var1.printtab(Fmath.truncate(this.rawAllResponsesMean, this.trunc));
        var1.printtab(Fmath.truncate(this.rawAllResponsesStandardDeviation, this.trunc));
        var1.printtab(Fmath.truncate(this.rawAllResponsesMinimum, this.trunc));
        var1.printtab(Fmath.truncate(this.rawAllResponsesMaximum, this.trunc));
        var1.printtab(Fmath.truncate(this.rawAllResponsesRange, this.trunc));
        var1.println(Fmath.truncate(this.rawAllResponsesTotal, this.trunc));
        var1.println();
        var1.println("ALL SCORES - standardized fillData");
        var1.printtab("mean");
        var1.printtab("standard");
        var1.printtab("minimum");
        var1.printtab("maximum");
        var1.printtab("range");
        var1.println("overall");
        var1.printtab("    ");
        var1.printtab("deviation");
        var1.printtab("     ");
        var1.printtab("     ");
        var1.printtab("     ");
        var1.println("total");
        var1.printtab(Fmath.truncate(this.standardizedAllResponsesMean, this.trunc));
        var1.printtab(Fmath.truncate(this.standardizedAllResponsesStandardDeviation, this.trunc));
        var1.printtab(Fmath.truncate(this.standardizedAllResponsesMinimum, this.trunc));
        var1.printtab(Fmath.truncate(this.standardizedAllResponsesMaximum, this.trunc));
        var1.printtab(Fmath.truncate(this.standardizedAllResponsesRange, this.trunc));
        var1.println(Fmath.truncate(this.standardizedAllResponsesTotal, this.trunc));
        var1.println();
        var1.close();
    }

    private void analysisText() {
        FileOutput var1 = null;
        if (this.fileNumberingSet) {
            var1 = new FileOutput(this.outputFilename, 'n');
        } else {
            var1 = new FileOutput(this.outputFilename);
        }

        if (!this.rawAlphaCalculated) {
            this.rawAlpha();
        }

        if (!this.standardizedAlphaCalculated) {
            this.standardizedAlpha();
        }

        var1.println("CRONBACH'S ALPHA RELIABILITY ESTIMATOR");
        var1.println("Program: Cronbach - Analysis Output");

        for(int var2 = 0; var2 < this.titleLines; ++var2) {
            var1.println(this.title[var2]);
        }

        Date var39 = new Date();
        String var3 = DateFormat.getDateInstance().format(var39);
        String var4 = DateFormat.getTimeInstance().format(var39);
        var1.println("Program executed at " + var4 + " on " + var3);
        var1.println();
        byte var5 = 36;
        var1.println("RELIABILITY ESTIMATORS");
        var1.println("Cronbach's coefficient alpha");
        var1.print("Raw fillData ", var5);
        var1.println(Fmath.truncate(this.rawAlpha, this.trunc));
        var1.print("Standardized fillData ", var5);
        var1.println(Fmath.truncate(this.standardizedAlpha, this.trunc));
        var1.println();
        var1.println("Average of the inter-item correlation coefficients, excluding item totals");
        var1.print("Raw fillData ", var5);
        var1.println(Fmath.truncate(this.rawMeanRhoWithoutTotals, this.trunc));
        var1.print("Standardized fillData ", var5);
        var1.println(Fmath.truncate(this.standardizedMeanRhoWithoutTotals, this.trunc));
        var1.println();
        var1.println("Average of the inter-item correlation coefficients, including item totals");
        var1.print("Raw fillData ", var5);
        var1.println(Fmath.truncate(this.rawMeanRhoWithTotals, this.trunc));
        var1.print("Standardized fillData ", var5);
        var1.println(Fmath.truncate(this.standardizedMeanRhoWithTotals, this.trunc));
        var1.println();
        var1.println("'NO RESPONSE' DELETIONS AND REPLACEMENTS");
        var5 = 34;
        byte var6 = 6;
        boolean var7 = false;
        int var8;
        if (this.nDeletedPersons != 0) {
            var7 = true;
            var1.print("Number of persons deleted ", var5);
            var1.println(this.nDeletedPersons);
            var1.print("Indices of deleted persons: ", var5);

            for(var8 = 0; var8 < this.nDeletedPersons; ++var8) {
                var1.print(this.deletedPersonsIndices[var8] + 1, var6);
            }

            var1.println();
        } else {
            var1.println("No persons were deleted ");
        }

        if (this.nDeletedItems != 0) {
            var7 = true;
            var1.print("Number of items deleted ", var5);
            var1.println(this.nDeletedItems);
            var1.print("Names of deleted items: ", var5);

            for(var8 = 0; var8 < this.nDeletedItems; ++var8) {
                var1.print(this.originalItemNames[this.deletedItemsIndices[var8]], var6);
            }

            var1.println();
        } else {
            var1.println("No items were deleted ");
        }

        if (this.nReplacements != 0) {
            var1.printtab("Number of 'no responses' replaced ");
            var1.println(this.nReplacements);
            var1.print("Item name and person index of replacements: ", 50);

            for(var8 = 0; var8 < this.nReplacements; ++var8) {
                var1.print(this.replacementIndices[var8] + " ", var6);
            }

            var1.println();
            var1.print("Replacement option: ", var5);
            var1.println(this.replacementOptionNames[this.replacementOption - 1]);
            var1.println();
        } else if (var7) {
            var1.println("No 'no response' replacements, other than any above deletions, were made ");
        } else {
            var1.println("No 'no response' replacements were made ");
        }

        var1.println();
        var1.print("Number of items used", 35);
        var1.println(this.nItems);
        var1.print("Number of persons used", 35);
        var1.println(this.nPersons);
        var1.println();
        var8 = this.trunc + 8;
        int var9 = 0;

        int var10;
        for(var10 = 0; var10 <= this.nItems; ++var10) {
            if (this.itemNames[var10].length() > var9) {
                var9 = this.itemNames[var10].length();
            }
        }

        var10 = var9;
        if (var8 > var9) {
            var10 = var8;
        }

        ++var9;
        ++var10;
        var1.println("CORRELATION COEFFICIENTS");
        var1.println("Correlation coefficients between items  -  raw fillData");
        var1.print("    ", var9);

        int var11;
        for(var11 = 0; var11 <= this.nItems; ++var11) {
            var1.print(this.itemNames[var11], var10);
        }

        var1.println();

        int var12;
        for(var11 = 0; var11 <= this.nItems; ++var11) {
            var1.print(this.itemNames[var11], var9);

            for(var12 = 0; var12 <= this.nItems; ++var12) {
                var1.print(Fmath.truncate(this.rawCorrelationCoefficients[var11][var12], this.trunc), var10);
            }

            var1.println();
        }

        var1.println();
        var1.print("Average inter-item correlation coefficient (excluding total) ", 80);
        var1.println(Fmath.truncate(this.rawMeanRhoWithoutTotals, this.trunc));
        var1.print("Standard deviation of the inter-item correlation coefficient (excluding total) ", 80);
        var1.println(Fmath.truncate(this.rawStandardDeviationRhoWithoutTotals, this.trunc));
        var1.print("Average inter-item correlation coefficient (including total) ", 80);
        var1.println(Fmath.truncate(this.rawMeanRhoWithTotals, this.trunc));
        var1.print("Standard deviation of the inter-item correlation coefficient (including total) ", 80);
        var1.println(Fmath.truncate(this.rawStandardDeviationRhoWithTotals, this.trunc));
        var1.println();
        var1.println("Correlation coefficients between items  -  standardized fillData");
        var1.print("    ", var9);

        for(var11 = 0; var11 <= this.nItems; ++var11) {
            var1.print(this.itemNames[var11], var10);
        }

        var1.println();

        for(var11 = 0; var11 <= this.nItems; ++var11) {
            var1.print(this.itemNames[var11], var9);

            for(var12 = 0; var12 <= this.nItems; ++var12) {
                var1.print(Fmath.truncate(this.standardizedCorrelationCoefficients[var11][var12], this.trunc), var10);
            }

            var1.println();
        }

        var1.println();
        var1.print("Average inter-item correlation coefficient (excluding total) ", 80);
        var1.println(Fmath.truncate(this.standardizedMeanRhoWithoutTotals, this.trunc));
        var1.print("Standard deviation of the inter-item correlation coefficient (excluding total) ", 80);
        var1.println(Fmath.truncate(this.standardizedStandardDeviationRhoWithoutTotals, this.trunc));
        var1.print("Average inter-item correlation coefficient (including total) ", 80);
        var1.println(Fmath.truncate(this.standardizedMeanRhoWithTotals, this.trunc));
        var1.print("Standard deviation of the inter-item correlation coefficient (including total) ", 80);
        var1.println(Fmath.truncate(this.standardizedStandardDeviationRhoWithTotals, this.trunc));
        var1.println();
        if (var10 < 12) {
            var10 = 12;
        }

        var1.println("ITEMS: MEANS, STANDARD DEVIATIONS, SKEWNESS AND KURTOSIS");
        var1.println("Raw fillData");
        var1.print("item ", var9);
        var1.print("mean", var10);
        var1.print("standard", var10);
        var1.print("moment", var10);
        var1.print("median", var10);
        var1.print("quartile", var10);
        var1.print("kurtosis", var10);
        var1.println("dichotomous");
        var1.print("    ", var9);
        var1.print("    ", var10);
        var1.print("deviation", var10);
        var1.print("skewness", var10);
        var1.print("skewness", var10);
        var1.print("skewness", var10);
        var1.print("excess  ", var10);
        var1.println("percentage");

        for(var11 = 0; var11 < this.nItems; ++var11) {
            var1.print(this.itemNames[var11], var9);
            var1.print(Fmath.truncate(this.rawItemMeans[var11], this.trunc), var10);
            var1.print(Fmath.truncate(this.rawItemStandardDeviations[var11], this.trunc), var10);
            var1.print(Fmath.truncate(this.rawItemMomentSkewness[var11], this.trunc), var10);
            var1.print(Fmath.truncate(this.rawItemMedianSkewness[var11], this.trunc), var10);
            var1.print(Fmath.truncate(this.rawItemQuartileSkewness[var11], this.trunc), var10);
            var1.print(Fmath.truncate(this.rawItemKurtosisExcess[var11], this.trunc), var10);
            var1.println(Fmath.truncate(this.dichotomousPercentage[var11], 1));
        }

        var1.println();
        var1.println("ITEMS: MINIMA, MAXIMA, MEDIANS, RANGES AND TOTALS");
        var1.println("Raw fillData");
        var1.print("item ", var9);
        var1.print("minimum", var10);
        var1.print("maximum", var10);
        var1.print("median", var10);
        var1.print("range", var10);
        var1.print("total", var10);
        var1.println("dichotomous");
        var1.print("    ", var9);
        var1.print("    ", var10);
        var1.print("     ", var10);
        var1.print("     ", var10);
        var1.print("     ", var10);
        var1.print("     ", var10);
        var1.println("percentage");

        for(var11 = 0; var11 < this.nItems; ++var11) {
            var1.print(this.itemNames[var11], var9);
            var1.print(Fmath.truncate(this.rawItemMinima[var11], this.trunc), var10);
            var1.print(Fmath.truncate(this.rawItemMaxima[var11], this.trunc), var10);
            var1.print(Fmath.truncate(this.rawItemMedians[var11], this.trunc), var10);
            var1.print(Fmath.truncate(this.rawItemRanges[var11], this.trunc), var10);
            var1.print(Fmath.truncate(this.rawItemTotals[var11], this.trunc), var10);
            var1.println(Fmath.truncate(this.dichotomousPercentage[var11], 1));
        }

        var1.println();
        byte var40 = 25;
        var1.print("item", var40);
        var1.print("mean", var10);
        var1.print("standard", var10);
        var1.print("variance", var10);
        var1.print("minimum", var10);
        var1.print("maximum", var10);
        var1.println("range");
        var1.print("statistic    ", var40);
        var1.print("    ", var10);
        var1.print("deviation", var10);
        var1.print("     ", var10);
        var1.print("     ", var10);
        var1.print("     ", var10);
        var1.print("     ", var10);
        var1.println("     ");
        var1.print("item means", var40);
        var1.print(Fmath.truncate(this.rawItemMeansMean, this.trunc), var10);
        var1.print(Fmath.truncate(this.rawItemMeansSd, this.trunc), var10);
        var1.print(Fmath.truncate(this.rawItemMeansVar, this.trunc), var10);
        var1.print(Fmath.truncate(this.rawItemMeansMin, this.trunc), var10);
        var1.print(Fmath.truncate(this.rawItemMeansMax, this.trunc), var10);
        var1.println(Fmath.truncate(this.rawItemMeansRange, this.trunc));
        var1.print("item standard deviations", var40);
        var1.print(Fmath.truncate(this.rawItemStandardDeviationsMean, this.trunc), var10);
        var1.print(Fmath.truncate(this.rawItemStandardDeviationsSd, this.trunc), var10);
        var1.print(Fmath.truncate(this.rawItemStandardDeviationsVar, this.trunc), var10);
        var1.print(Fmath.truncate(this.rawItemStandardDeviationsMin, this.trunc), var10);
        var1.print(Fmath.truncate(this.rawItemStandardDeviationsMax, this.trunc), var10);
        var1.println(Fmath.truncate(this.rawItemStandardDeviationsRange, this.trunc));
        var1.print("item variances", var40);
        var1.print(Fmath.truncate(this.rawItemVariancesMean, this.trunc), var10);
        var1.print(Fmath.truncate(this.rawItemVariancesSd, this.trunc), var10);
        var1.print(Fmath.truncate(this.rawItemVariancesVar, this.trunc), var10);
        var1.print(Fmath.truncate(this.rawItemVariancesMin, this.trunc), var10);
        var1.print(Fmath.truncate(this.rawItemVariancesMax, this.trunc), var10);
        var1.println(Fmath.truncate(this.rawItemVariancesRange, this.trunc));
        var1.print("item mimima", var40);
        var1.print(Fmath.truncate(this.rawItemMinimaMean, this.trunc), var10);
        var1.print(Fmath.truncate(this.rawItemMinimaSd, this.trunc), var10);
        var1.print(Fmath.truncate(this.rawItemMinimaVar, this.trunc), var10);
        var1.print(Fmath.truncate(this.rawItemMinimaMin, this.trunc), var10);
        var1.print(Fmath.truncate(this.rawItemMinimaMax, this.trunc), var10);
        var1.println(Fmath.truncate(this.rawItemMinimaRange, this.trunc));
        var1.print("item maxima", var40);
        var1.print(Fmath.truncate(this.rawItemMaximaMean, this.trunc), var10);
        var1.print(Fmath.truncate(this.rawItemMaximaSd, this.trunc), var10);
        var1.print(Fmath.truncate(this.rawItemMaximaVar, this.trunc), var10);
        var1.print(Fmath.truncate(this.rawItemMaximaMin, this.trunc), var10);
        var1.print(Fmath.truncate(this.rawItemMaximaMax, this.trunc), var10);
        var1.println(Fmath.truncate(this.rawItemMaximaRange, this.trunc));
        var1.print("item medians", var40);
        var1.print(Fmath.truncate(this.rawItemMediansMean, this.trunc), var10);
        var1.print(Fmath.truncate(this.rawItemMediansSd, this.trunc), var10);
        var1.print(Fmath.truncate(this.rawItemMediansVar, this.trunc), var10);
        var1.print(Fmath.truncate(this.rawItemMediansMin, this.trunc), var10);
        var1.print(Fmath.truncate(this.rawItemMediansMax, this.trunc), var10);
        var1.println(Fmath.truncate(this.rawItemMediansRange, this.trunc));
        var1.print("item ranges", var40);
        var1.print(Fmath.truncate(this.rawItemRangesMean, this.trunc), var10);
        var1.print(Fmath.truncate(this.rawItemRangesSd, this.trunc), var10);
        var1.print(Fmath.truncate(this.rawItemRangesVar, this.trunc), var10);
        var1.print(Fmath.truncate(this.rawItemRangesMin, this.trunc), var10);
        var1.print(Fmath.truncate(this.rawItemRangesMax, this.trunc), var10);
        var1.println(Fmath.truncate(this.rawItemRangesRange, this.trunc));
        var1.print("item totals", var40);
        var1.print(Fmath.truncate(this.rawItemTotalsMean, this.trunc), var10);
        var1.print(Fmath.truncate(this.rawItemTotalsSd, this.trunc), var10);
        var1.print(Fmath.truncate(this.rawItemTotalsVar, this.trunc), var10);
        var1.print(Fmath.truncate(this.rawItemTotalsMin, this.trunc), var10);
        var1.print(Fmath.truncate(this.rawItemTotalsMax, this.trunc), var10);
        var1.println(Fmath.truncate(this.rawItemTotalsRange, this.trunc));
        var1.println();
        var1.println("standardized fillData");
        var1.print("item ", var9);
        var1.print("mean", var10);
        var1.print("standard", var10);
        var1.print("moment", var10);
        var1.print("median", var10);
        var1.print("quartile", var10);
        var1.println("kurtosis");
        var1.print("    ", var9);
        var1.print("    ", var10);
        var1.print("deviation", var10);
        var1.print("skewness", var10);
        var1.print("skewness", var10);
        var1.print("skewness", var10);
        var1.println("excess  ");

        for(var12 = 0; var12 < this.nItems; ++var12) {
            var1.print(this.itemNames[var12], var9);
            var1.print(Fmath.truncate(this.standardizedItemMeans[var12], this.trunc), var10);
            var1.print(Fmath.truncate(this.standardizedItemStandardDeviations[var12], this.trunc), var10);
            var1.print(Fmath.truncate(this.standardizedItemMomentSkewness[var12], this.trunc), var10);
            var1.print(Fmath.truncate(this.standardizedItemMedianSkewness[var12], this.trunc), var10);
            var1.print(Fmath.truncate(this.standardizedItemQuartileSkewness[var12], this.trunc), var10);
            var1.println(Fmath.truncate(this.standardizedItemKurtosisExcess[var12], this.trunc));
        }

        var1.println();
        var1.print("item ", var9);
        var1.print("minimum", var10);
        var1.print("maximum", var10);
        var1.print("median", var10);
        var1.print("range", var10);
        var1.println("total");
        var1.print("    ", var9);
        var1.print("    ", var10);
        var1.print("     ", var10);
        var1.print("     ", var10);
        var1.print("     ", var10);
        var1.println("     ");

        for(var12 = 0; var12 < this.nItems; ++var12) {
            var1.print(this.itemNames[var12], var9);
            var1.print(Fmath.truncate(this.standardizedItemMinima[var12], this.trunc), var10);
            var1.print(Fmath.truncate(this.standardizedItemMaxima[var12], this.trunc), var10);
            var1.print(Fmath.truncate(this.standardizedItemMedians[var12], this.trunc), var10);
            var1.print(Fmath.truncate(this.standardizedItemRanges[var12], this.trunc), var10);
            var1.println(Fmath.truncate(this.standardizedItemTotals[var12], this.trunc));
        }

        var1.println();
        var1.print("item", var40);
        var1.print("mean", var10);
        var1.print("standard", var10);
        var1.print("variance", var10);
        var1.print("minimum", var10);
        var1.print("maximum", var10);
        var1.println("range");
        var1.print("statistic    ", var40);
        var1.print("    ", var10);
        var1.print("deviation", var10);
        var1.print("     ", var10);
        var1.print("     ", var10);
        var1.print("     ", var10);
        var1.print("     ", var10);
        var1.println("     ");
        var1.print("item means", var40);
        var1.print(Fmath.truncate(this.standardizedItemMeansMean, this.trunc), var10);
        var1.print(Fmath.truncate(this.standardizedItemMeansSd, this.trunc), var10);
        var1.print(Fmath.truncate(this.standardizedItemMeansVar, this.trunc), var10);
        var1.print(Fmath.truncate(this.standardizedItemMeansMin, this.trunc), var10);
        var1.print(Fmath.truncate(this.standardizedItemMeansMax, this.trunc), var10);
        var1.println(Fmath.truncate(this.standardizedItemMeansRange, this.trunc));
        var1.print("item standard deviations", var40);
        var1.print(Fmath.truncate(this.standardizedItemStandardDeviationsMean, this.trunc), var10);
        var1.print(Fmath.truncate(this.standardizedItemStandardDeviationsSd, this.trunc), var10);
        var1.print(Fmath.truncate(this.standardizedItemStandardDeviationsVar, this.trunc), var10);
        var1.print(Fmath.truncate(this.standardizedItemStandardDeviationsMin, this.trunc), var10);
        var1.print(Fmath.truncate(this.standardizedItemStandardDeviationsMax, this.trunc), var10);
        var1.println(Fmath.truncate(this.standardizedItemStandardDeviationsRange, this.trunc));
        var1.print("item variances", var40);
        var1.print(Fmath.truncate(this.standardizedItemVariancesMean, this.trunc), var10);
        var1.print(Fmath.truncate(this.standardizedItemVariancesSd, this.trunc), var10);
        var1.print(Fmath.truncate(this.standardizedItemVariancesVar, this.trunc), var10);
        var1.print(Fmath.truncate(this.standardizedItemVariancesMin, this.trunc), var10);
        var1.print(Fmath.truncate(this.standardizedItemVariancesMax, this.trunc), var10);
        var1.println(Fmath.truncate(this.standardizedItemVariancesRange, this.trunc));
        var1.print("item mimima", var40);
        var1.print(Fmath.truncate(this.standardizedItemMinimaMean, this.trunc), var10);
        var1.print(Fmath.truncate(this.standardizedItemMinimaSd, this.trunc), var10);
        var1.print(Fmath.truncate(this.standardizedItemMinimaVar, this.trunc), var10);
        var1.print(Fmath.truncate(this.standardizedItemMinimaMin, this.trunc), var10);
        var1.print(Fmath.truncate(this.standardizedItemMinimaMax, this.trunc), var10);
        var1.println(Fmath.truncate(this.standardizedItemMinimaRange, this.trunc));
        var1.print("item maxima", var40);
        var1.print(Fmath.truncate(this.standardizedItemMaximaMean, this.trunc), var10);
        var1.print(Fmath.truncate(this.standardizedItemMaximaSd, this.trunc), var10);
        var1.print(Fmath.truncate(this.standardizedItemMaximaVar, this.trunc), var10);
        var1.print(Fmath.truncate(this.standardizedItemMaximaMin, this.trunc), var10);
        var1.print(Fmath.truncate(this.standardizedItemMaximaMax, this.trunc), var10);
        var1.println(Fmath.truncate(this.standardizedItemMaximaRange, this.trunc));
        var1.print("item medians", var40);
        var1.print(Fmath.truncate(this.standardizedItemMediansMean, this.trunc), var10);
        var1.print(Fmath.truncate(this.standardizedItemMediansSd, this.trunc), var10);
        var1.print(Fmath.truncate(this.standardizedItemMediansVar, this.trunc), var10);
        var1.print(Fmath.truncate(this.standardizedItemMediansMin, this.trunc), var10);
        var1.print(Fmath.truncate(this.standardizedItemMediansMax, this.trunc), var10);
        var1.println(Fmath.truncate(this.standardizedItemMediansRange, this.trunc));
        var1.print("item ranges", var40);
        var1.print(Fmath.truncate(this.standardizedItemRangesMean, this.trunc), var10);
        var1.print(Fmath.truncate(this.standardizedItemRangesSd, this.trunc), var10);
        var1.print(Fmath.truncate(this.standardizedItemRangesVar, this.trunc), var10);
        var1.print(Fmath.truncate(this.standardizedItemRangesMin, this.trunc), var10);
        var1.print(Fmath.truncate(this.standardizedItemRangesMax, this.trunc), var10);
        var1.println(Fmath.truncate(this.standardizedItemRangesRange, this.trunc));
        var1.print("item totals", var40);
        var1.print(Fmath.truncate(this.standardizedItemTotalsMean, this.trunc), var10);
        var1.print(Fmath.truncate(this.standardizedItemTotalsSd, this.trunc), var10);
        var1.print(Fmath.truncate(this.standardizedItemTotalsVar, this.trunc), var10);
        var1.print(Fmath.truncate(this.standardizedItemTotalsMin, this.trunc), var10);
        var1.print(Fmath.truncate(this.standardizedItemTotalsMax, this.trunc), var10);
        var1.println(Fmath.truncate(this.standardizedItemTotalsRange, this.trunc));
        var1.println();
        var1.println("DELETION OF ITEMS");
        var12 = 16;
        if (var9 > var12) {
            var12 = var9;
        }

        var1.print("   ", var12);
        var1.print("Raw fillData", var10);
        var1.print("   ", var10);
        var1.print("   ", var10);
        var1.print("   ", var10);
        var1.println("Standardized fillData");
        var1.print("Deleted item", var12);
        var1.print("Alpha", var10);
        var1.print("Correlation", var10);
        var1.print("Average", var10);
        var1.print("Average", var10);
        var1.print("Alpha", var10);
        var1.print("Correlation", var10);
        var1.print("Average", var10);
        var1.println("Average");
        var1.print("       ", var12);
        var1.print("       ", var10);
        var1.print("coefficient", var10);
        var1.print("inter-item", var10);
        var1.print("inter-item", var10);
        var1.print("      ", var10);
        var1.print("coefficient", var10);
        var1.print("inter-item", var10);
        var1.println("inter-item");
        var1.print("       ", var12);
        var1.print("       ", var10);
        var1.print("with total", var10);
        var1.print("correlation", var10);
        var1.print("correlation", var10);
        var1.print("      ", var10);
        var1.print("with total", var10);
        var1.print("correlation", var10);
        var1.println("correlation");
        var1.print("       ", var12);
        var1.print("       ", var10);
        var1.print("       ", var10);
        var1.print("coefficient", var10);
        var1.print("coefficient", var10);
        var1.print("        ", var10);
        var1.print("        ", var10);
        var1.print("coefficient", var10);
        var1.println("coefficient");
        var1.print("       ", var12);
        var1.print("       ", var10);
        var1.print("       ", var10);
        var1.print("without totals", var10);
        var1.print("with totals", var10);
        var1.print("        ", var10);
        var1.print("        ", var10);
        var1.print("without totals", var10);
        var1.println("with totals");
        double[] var13 = new double[this.nItems];
        double[] var14 = new double[this.nItems];
        double[] var15 = new double[this.nItems];
        double[] var16 = new double[this.nItems];

        int var18;
        for(int var17 = 0; var17 < this.nItems; ++var17) {
            var18 = var17 + 1;
            double[][] var19 = this.deleteItem(var18);
            Cronbach var20 = new Cronbach();
            var20.enterScoresAsRowPerPerson(var19);
            double var21 = var20.rawAlpha();
            var13[var17] = var21;
            double var23 = var20.rawAverageCorrelationCoefficientsWithTotals();
            double[] var25 = var20.rawPersonTotals();
            double var26 = Stat.corrCoeff(var25, this.scores0[var17]);
            double var28 = var20.rawAverageCorrelationCoefficients();
            var15[var17] = var26;
            double var30 = var20.standardizedAlpha();
            var14[var17] = var30;
            double var32 = var20.standardizedAverageCorrelationCoefficients();
            double[] var34 = var20.standardizedPersonTotals();
            double var35 = Stat.corrCoeff(var34, this.scores0[var17]);
            double var37 = var20.standardizedAverageCorrelationCoefficients();
            var16[var17] = var35;
            var1.print(this.itemNames[var17], var12);
            var1.print(Fmath.truncate(var21, this.trunc), var10);
            var1.print(Fmath.truncate(var26, this.trunc), var10);
            var1.print(Fmath.truncate(var28, this.trunc), var10);
            var1.print(Fmath.truncate(var23, this.trunc), var10);
            var1.print(Fmath.truncate(var30, this.trunc), var10);
            var1.print(Fmath.truncate(var35, this.trunc), var10);
            var1.print(Fmath.truncate(var37, this.trunc), var10);
            var1.print(Fmath.truncate(var32, this.trunc), var10);
            var1.println();
        }

        var1.println();
        var1.print("No item deleted", var12);
        var1.print(Fmath.truncate(this.rawAlpha, this.trunc), var10);
        var1.print("   ", var10);
        var1.print(Fmath.truncate(this.rawMeanRhoWithoutTotals, this.trunc), var10);
        var1.print(Fmath.truncate(this.rawMeanRhoWithTotals, this.trunc), var10);
        var1.print(Fmath.truncate(this.standardizedAlpha, this.trunc), var10);
        var1.print("   ", var10);
        var1.print(Fmath.truncate(this.standardizedMeanRhoWithoutTotals, this.trunc), var10);
        var1.println(Fmath.truncate(this.standardizedMeanRhoWithTotals, this.trunc));
        var1.println();
        this.deletedItemDataFile(var13, var15, var14, var16);
        byte var41 = 12;
        var1.println("INDIVIDUALS - raw fillData");
        var1.print("person", var41);
        var1.print("mean", var10);
        var1.print("standard", var10);
        var1.print("minimum", var10);
        var1.print("maximum", var10);
        var1.print("range", var10);
        var1.println("total");
        var1.print("    ", var41);
        var1.print("    ", var10);
        var1.print("deviation", var10);
        var1.print("     ", var10);
        var1.print("     ", var10);
        var1.print("     ", var10);
        var1.println("     ");
        var18 = 0;

        int var42;
        int var43;
        for(var42 = 0; var42 < this.nItems; ++var42) {
            for(var43 = 0; var43 < this.nPersons; ++var43) {
                int var44 = Double.toString(this.scores0[var42][var43]).length();
                if (var44 > var18) {
                    var18 = var44;
                }
            }
        }

        ++var18;
        if (var18 < var9) {
            ;
        }

        for(var42 = 0; var42 < this.nPersons; ++var42) {
            var1.print(this.personIndices[var42] + 1, var41);
            var1.print(Fmath.truncate(this.rawPersonMeans[var42], this.trunc), var10);
            var1.print(Fmath.truncate(this.rawPersonStandardDeviations[var42], this.trunc), var10);
            var1.print(Fmath.truncate(this.rawPersonMinima[var42], this.trunc), var10);
            var1.print(Fmath.truncate(this.rawPersonMaxima[var42], this.trunc), var10);
            var1.print(Fmath.truncate(this.rawPersonRanges[var42], this.trunc), var10);
            var1.println(Fmath.truncate(this.rawPersonTotals[var42], this.trunc));
        }

        var1.println();
        var1.println("scores:");
        var1.print("person ", var41);

        for(var42 = 0; var42 < this.nItems; ++var42) {
            var1.print(this.itemNames[var42], var10);
        }

        var1.println();

        for(var42 = 0; var42 < this.nPersons; ++var42) {
            var1.print(this.personIndices[var42] + 1, var41);

            for(var43 = 0; var43 < this.nItems; ++var43) {
                var1.print(Fmath.truncate(this.scores1[var42][var43], this.trunc), var10);
            }

            var1.println();
        }

        var1.println();
        var1.println("INDIVIDUALS - standardized fillData");
        var1.print("person ", var41);
        var1.print("mean", var10);
        var1.print("standard", var10);
        var1.print("minimum", var10);
        var1.print("maximum", var10);
        var1.print("range", var10);
        var1.println("total");
        var1.print("    ", var41);
        var1.print("    ", var10);
        var1.print("deviation", var10);
        var1.print("     ", var10);
        var1.print("     ", var10);
        var1.print("     ", var10);
        var1.println("     ");

        for(var42 = 0; var42 < this.nPersons; ++var42) {
            var1.print(this.personIndices[var42] + 1, var41);
            var1.print(Fmath.truncate(this.standardizedPersonMeans[var42], this.trunc), var10);
            var1.print(Fmath.truncate(this.standardizedPersonStandardDeviations[var42], this.trunc), var10);
            var1.print(Fmath.truncate(this.standardizedPersonMinima[var42], this.trunc), var10);
            var1.print(Fmath.truncate(this.standardizedPersonMaxima[var42], this.trunc), var10);
            var1.print(Fmath.truncate(this.standardizedPersonRanges[var42], this.trunc), var10);
            var1.println(Fmath.truncate(this.standardizedPersonTotals[var42], this.trunc));
        }

        var1.println();
        var1.println("scores:");
        var1.print("person ", var41);

        for(var42 = 0; var42 < this.nItems; ++var42) {
            var1.print(this.itemNames[var42], var10);
        }

        var1.println();

        for(var42 = 0; var42 < this.nPersons; ++var42) {
            var1.print(this.personIndices[var42] + 1, var41);

            for(var43 = 0; var43 < this.nItems; ++var43) {
                var1.print(Fmath.truncate(this.standardizedScores1[var42][var43], this.trunc), var10);
            }

            var1.println();
        }

        var1.println();
        var1.println("ALL SCORES - raw fillData");
        var1.print("mean", var10);
        var1.print("standard", var10);
        var1.print("minimum", var10);
        var1.print("maximum", var10);
        var1.print("range", var10);
        var1.println("overall");
        var1.print("    ", var10);
        var1.print("deviation", var10);
        var1.print("     ", var10);
        var1.print("     ", var10);
        var1.print("     ", var10);
        var1.println("total");
        var1.print(Fmath.truncate(this.rawAllResponsesMean, this.trunc), var10);
        var1.print(Fmath.truncate(this.rawAllResponsesStandardDeviation, this.trunc), var10);
        var1.print(Fmath.truncate(this.rawAllResponsesMinimum, this.trunc), var10);
        var1.print(Fmath.truncate(this.rawAllResponsesMaximum, this.trunc), var10);
        var1.print(Fmath.truncate(this.rawAllResponsesRange, this.trunc), var10);
        var1.println(Fmath.truncate(this.rawAllResponsesTotal, this.trunc));
        var1.println();
        var1.println("ALL SCORES - standardized fillData");
        var1.print("mean", var10);
        var1.print("standard", var10);
        var1.print("minimum", var10);
        var1.print("maximum", var10);
        var1.print("range", var10);
        var1.println("overall");
        var1.print("    ", var10);
        var1.print("deviation", var10);
        var1.print("     ", var10);
        var1.print("     ", var10);
        var1.print("     ", var10);
        var1.println("total");
        var1.print(Fmath.truncate(this.standardizedAllResponsesMean, this.trunc), var10);
        var1.print(Fmath.truncate(this.standardizedAllResponsesStandardDeviation, this.trunc), var10);
        var1.print(Fmath.truncate(this.standardizedAllResponsesMinimum, this.trunc), var10);
        var1.print(Fmath.truncate(this.standardizedAllResponsesMaximum, this.trunc), var10);
        var1.print(Fmath.truncate(this.standardizedAllResponsesRange, this.trunc), var10);
        var1.println(Fmath.truncate(this.standardizedAllResponsesTotal, this.trunc));
        var1.println();
        var1.close();
    }

    private void deletedItemDataFile(double[] var1, double[] var2, double[] var3, double[] var4) {
        ArrayMaths var5 = new ArrayMaths(var1);
        int var6 = var5.maximumIndex();
        var5 = new ArrayMaths(var3);
        int var7 = var5.maximumIndex();
        var5 = new ArrayMaths(var2);
        int var8 = var5.minimumIndex();
        var5 = new ArrayMaths(var4);
        int var9 = var5.minimumIndex();
        this.deletedItemIndex = var8;
        if (var6 == var7 && var6 == var8 && var6 == var9) {
            this.deletedItemIndex = var6;
        } else if (var6 == var7 && (var6 == var8 || var6 == var9)) {
            this.deletedItemIndex = var6;
        } else if (var9 == var8 && (var9 == var6 || var9 == var7)) {
            this.deletedItemIndex = var9;
        } else if (var6 == var7 && var8 == var9) {
            this.deletedItemIndex = var8;
        } else if (var6 == var8 && var7 == var9) {
            this.deletedItemIndex = var6;
        } else if (var6 != var7 && var7 != var8 && var8 != var9) {
            this.deletedItemIndex = var8;
        }

        this.deletedFilename = null;
        if (this.inputFilename != null) {
            this.deletedFilename = this.inputFilename;
            int var10 = this.deletedFilename.indexOf(".");
            if (var10 != -1) {
                this.deletedFilename = this.deletedFilename.substring(0, var10);
            }

            this.deletedFilename = this.deletedFilename + "_" + this.itemNames[this.deletedItemIndex] + "_deleted";
            this.deletedFilename = this.deletedFilename + ".txt";
        } else {
            this.deletedFilename = "DeletedItemFile.txt";
        }

        FileOutput var14 = new FileOutput(this.deletedFilename);
        String var11 = this.title[0] + " - Item " + this.itemNames[this.deletedItemIndex] + " deleted";
        var14.println(var11);
        var14.println(this.nItems - 1);
        var14.println(this.nPersons);

        int var12;
        for(var12 = 0; var12 < this.nItems; ++var12) {
            if (var12 != this.deletedItemIndex) {
                var14.printtab(this.itemNames[var12]);
            }
        }

        var14.println();
        int var13;
        if (this.originalDataType == 0) {
            for(var12 = 0; var12 < this.nItems; ++var12) {
                if (var12 != this.deletedItemIndex) {
                    for(var13 = 0; var13 < this.nPersons; ++var13) {
                        var14.printtab(this.scores0[var12][var13]);
                    }

                    var14.println();
                }
            }
        } else {
            for(var12 = 0; var12 < this.nPersons; ++var12) {
                for(var13 = 0; var13 < this.nItems; ++var13) {
                    if (var13 != this.deletedItemIndex) {
                        var14.printtab(this.scores1[var12][var13]);
                    }
                }

                var14.println();
            }
        }

        var14.close();
    }

    public String getDeletionFileName() {
        return this.deletedFilename;
    }

    public String getLeastConsistentItemName() {
        return this.itemNames[this.deletedItemIndex];
    }
}
