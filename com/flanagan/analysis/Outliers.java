//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.analysis;

import com.flanagan.io.FileChooser;
import com.flanagan.io.FileOutput;
import com.flanagan.math.ArrayMaths;
import com.flanagan.math.DeepCopy;
import com.flanagan.math.Fmath;
import com.flanagan.math.TimeAndDate;
import com.flanagan.util.Strings;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

public class Outliers {
    private double[] data = null;
    private double[] orderedData = null;
    private int[] originalIndices = null;
    private double[] orderStatisticMedians = null;
    private double[] strippedData = null;
    private double[] strippedOrderStatisticMedians = null;
    private int nPoints = 0;
    private double meanAll = 0.0D / 0.0;
    private double sdAll = 0.0D / 0.0;
    private double significance = 0.05D;
    private int nTietjenMooreSimulations = 10000;
    private static int nTietjenMooreSimulationsStatic = 10000;
    private int nDixonSimulations = 10000;
    private static int nDixonSimulationsStatic = 10000;
    private int bluOption = -1;
    private int luOption = -1;
    private boolean pointsIgnored = false;
    private int nPointsIgnored = 0;
    private double[] ignoredPoints = null;
    private double[] esdLambdas = null;
    private double[] esdRvalues = null;
    private double[] esdMaxValues = null;
    private boolean[] esdTests = null;
    private boolean suppressPrint = false;
    private boolean suppressDisplay = false;
    private String filenameout = "OutlierDetectionResults.txt";
    private String filenamein = null;
    private TimeAndDate tad = new TimeAndDate();
    private String time_date;
    private int trunc = 4;
    private int field0 = 30;
    private int field = 15;

    public Outliers(double[] var1) {
        this.data = var1;
        this.initialise();
    }

    public Outliers(float[] var1) {
        ArrayMaths var2 = new ArrayMaths(var1);
        this.data = var2.array_as_double();
        this.initialise();
    }

    public Outliers(long[] var1) {
        ArrayMaths var2 = new ArrayMaths(var1);
        this.data = var2.array_as_double();
        this.initialise();
    }

    public Outliers(int[] var1) {
        ArrayMaths var2 = new ArrayMaths(var1);
        this.data = var2.array_as_double();
        this.initialise();
    }

    public Outliers(BigDecimal[] var1) {
        ArrayMaths var2 = new ArrayMaths(var1);
        this.data = var2.array_as_double();
        this.initialise();
    }

    public Outliers(BigInteger[] var1) {
        ArrayMaths var2 = new ArrayMaths(var1);
        this.data = var2.array_as_double();
        this.initialise();
    }

    public Outliers() {
        this.data = null;
        this.initialise();
    }

    private void initialise() {
        if (this.data != null) {
            this.nPoints = this.data.length;
            this.meanAll = Stat.mean(this.data);
            this.sdAll = Stat.standardDeviation(this.data);
        }

        this.time_date = this.tad.getShortTime24() + ", ";
        this.time_date = this.time_date + this.tad.getDate();
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

        this.meanAll = Stat.mean(this.data);
        this.sdAll = Stat.standardDeviation(this.data);
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

    public void resetSignificanceLevel(double var1) {
        this.significance = var1;
    }

    public double getSignificanceLevel() {
        return this.significance;
    }

    private void setNpoints(int var1) {
        this.nPoints = var1;
    }

    public int getNpoints() {
        return this.nPoints;
    }

    public void suppressPrint() {
        this.suppressPrint = true;
    }

    public void restorePrint() {
        this.suppressPrint = false;
    }

    public void resetTextFileName(String var1) {
        this.filenameout = var1;
        int var2 = var1.indexOf(".");
        if (var2 == -1) {
            var1 = var1 + ".txt";
        }

    }

    public void suppressDisplay() {
        this.suppressDisplay = true;
    }

    public void restoreDisplay() {
        this.suppressDisplay = false;
    }

    private ArrayList<Object> orderData(double[] var1, int var2) {
        ArrayList var3 = null;
        switch(var2) {
            case 0:
                var3 = this.orderAbsoluteResiduals(var1);
                break;
            case 1:
                var3 = this.orderDescending(var1);
                break;
            case 2:
                var3 = this.orderAscending(var1);
        }

        return var3;
    }

    public ArrayList<Object> orderAbsoluteResiduals(double[] var1) {
        int var2 = var1.length;
        double[] var3 = DeepCopy.copy(var1);
        double var4 = Stat.mean(var3);
        ArrayMaths var6 = new ArrayMaths(var3);
        var6 = var6.minus(var4);
        var6 = var6.abs();
        var6 = var6.sort();
        int[] var7 = var6.originalIndices();
        double[] var8 = new double[var2];

        for(int var9 = 0; var9 < var2; ++var9) {
            var8[var9] = var1[var7[var9]];
        }

        ArrayList var10 = new ArrayList();
        var10.add(var8);
        var10.add(var7);
        return var10;
    }

    public ArrayList<Object> orderDescending(double[] var1) {
        ArrayMaths var2 = new ArrayMaths(var1);
        var2 = var2.sort();
        double[] var3 = var2.array();
        int[] var4 = var2.originalIndices();
        double[] var5 = DeepCopy.copy(var3);
        int[] var6 = DeepCopy.copy(var4);
        int var7 = var1.length;

        for(int var8 = 0; var8 < var7; ++var8) {
            var3[var8] = var5[var7 - 1 - var8];
            var4[var8] = var6[var7 - 1 - var8];
        }

        ArrayList var9 = new ArrayList();
        var9.add(var3);
        var9.add(var4);
        return var9;
    }

    public ArrayList<Object> orderAscending(double[] var1) {
        ArrayMaths var2 = new ArrayMaths(var1);
        var2 = var2.sort();
        double[] var3 = var2.array();
        int[] var4 = var2.originalIndices();
        ArrayList var5 = new ArrayList();
        var5.add(var3);
        var5.add(var4);
        return var5;
    }

    public double[] getOriginalData() {
        return this.data;
    }

    public double[] getOrderedOriginalData() {
        ArrayMaths var1 = new ArrayMaths(this.data);
        var1.sort();
        return var1.array();
    }

    public double[] getDataOrderStatisticMedians() {
        return this.orderStatisticMedians;
    }

    public double[] getStrippedData() {
        return this.strippedData;
    }

    public double[] getOrderedStrippedData() {
        ArrayMaths var1 = new ArrayMaths(this.strippedData);
        var1.sort();
        return var1.array();
    }

    public double[] getStrippedDataOrderStatisticMedians() {
        return this.strippedOrderStatisticMedians;
    }

    public static double getGrubbsOneTailedCriticalT(double var0, int var2) {
        return getGrubbsCriticalT(var0, var2, 1);
    }

    public static double getGrubbsOneSidedCriticalT(double var0, int var2) {
        return getGrubbsCriticalT(var0, var2, 1);
    }

    public double getGrubbsOneTailedCriticalT() {
        return getGrubbsCriticalT(this.significance, this.nPoints, 1);
    }

    public double getGrubbsOneSidedCriticalT() {
        return getGrubbsCriticalT(this.significance, this.nPoints, 1);
    }

    public static double getGrubbsTwoTailedCriticalT(double var0, int var2) {
        return getGrubbsCriticalT(var0, var2, 2);
    }

    public static double getGrubbsTwoSidedCriticalT(double var0, int var2) {
        return getGrubbsCriticalT(var0, var2, 2);
    }

    public double getGrubbsTwoTailedCriticalT() {
        return getGrubbsCriticalT(this.significance, this.nPoints, 2);
    }

    public double getGrubbsTwoSidedCriticalT() {
        return getGrubbsCriticalT(this.significance, this.nPoints, 2);
    }

    private static double getGrubbsCriticalT(double var0, int var2, int var3) {
        double var4 = Stat.studentstValue(var0 / (double)(var2 * var3), var2 - 2);
        double var6 = var4 * var4;
        return (double)(var2 - 1) * Math.sqrt(var6 / ((double)(var2 - 2) + var6) / (double)var2);
    }

    public ArrayList<Object> outlierGrubbs(double[] var1) {
        Outliers var2 = new Outliers(var1);
        return var2.outlierGrubbs();
    }

    public ArrayList<Object> outlierGrubbs() {
        Stat.denominatorSwap();
        this.bluOption = 0;
        ArrayMaths var1 = new ArrayMaths(this.data);
        var1 = var1.minus(this.meanAll);
        var1 = var1.abs();
        var1 = var1.sort();
        int[] var2 = var1.originalIndices();
        this.orderedData = var1.getArray_as_double();
        double var3 = this.data[var2[this.nPoints - 1]];
        double var5 = getGrubbsTwoTailedCriticalT(this.significance, this.nPoints);
        double var7 = this.orderedData[this.nPoints - 1] / this.sdAll;
        Stat.denominatorUnswap();
        return this.outlierGrubbsCore(var3, var5, var7);
    }

    public static ArrayList<Object> upperOutlierGrubbs(double[] var0) {
        Outliers var1 = new Outliers(var0);
        return var1.upperOutlierGrubbs();
    }

    public ArrayList<Object> upperOutlierGrubbs() {
        Stat.denominatorSwap();
        this.bluOption = 2;
        ArrayMaths var1 = new ArrayMaths(this.data);
        var1 = var1.sort();
        this.orderedData = var1.getArray_as_double();
        double var2 = this.orderedData[this.nPoints - 1];
        double var4 = getGrubbsOneTailedCriticalT(this.significance, this.nPoints);
        double var6 = (var2 - this.meanAll) / this.sdAll;
        Stat.denominatorUnswap();
        return this.outlierGrubbsCore(var2, var4, var6);
    }

    public static ArrayList<Object> lowerOutlierGrubbs(double[] var0) {
        Outliers var1 = new Outliers(var0);
        return var1.lowerOutlierGrubbs();
    }

    public ArrayList<Object> lowerOutlierGrubbs() {
        Stat.denominatorSwap();
        this.bluOption = 1;
        ArrayMaths var1 = new ArrayMaths(this.data);
        var1 = var1.sort();
        this.orderedData = var1.getArray_as_double();
        double var2 = this.orderedData[0];
        double var4 = getGrubbsOneTailedCriticalT(this.significance, this.nPoints);
        double var6 = (this.meanAll - var2) / this.sdAll;
        Stat.denominatorUnswap();
        return this.outlierGrubbsCore(var2, var4, var6);
    }

    private ArrayList<Object> outlierGrubbsCore(double var1, double var3, double var5) {
        ArrayList var7 = new ArrayList();
        double var8 = 0.0D / 0.0;
        int var10 = -1;
        boolean var11 = false;
        ArrayList var12;
        if (var5 > var3) {
            var11 = true;
            var8 = var1;
            var12 = new ArrayList();

            int var13;
            for(var13 = 0; var13 < this.nPoints; ++var13) {
                if (this.data[var13] == var1) {
                    var10 = var13;
                } else {
                    var12.add(this.data[var13]);
                }
            }

            var13 = var12.size();
            this.strippedData = new double[var13];

            for(int var14 = 0; var14 < var13; ++var14) {
                this.strippedData[var14] = (Double)var12.get(var14);
            }

            var7.add(var11);
            var7.add(var1);
            var7.add(var10);
            var7.add(this.strippedData);
        } else {
            var7.add(var11);
            var7.add(var8);
            var7.add(var10);
            var7.add(this.data);
        }

        var7.add(var5);
        var7.add(var3);
        var7.add(this.significance);
        var7.add(this.nPoints);
        var12 = this.probabilityPlots(var11, this.data, this.strippedData);
        var7.add(var12.get(0));
        var7.add(var12.get(1));
        var7.add(var12.get(2));
        var7.add(var12.get(3));
        if (var11) {
            var7.add(var12.get(4));
            var7.add(var12.get(5));
            var7.add(var12.get(6));
            var7.add(var12.get(7));
        } else {
            var7.add(0.0D / 0.0);
            var7.add(0.0D / 0.0);
            var7.add(0.0D / 0.0);
            var7.add(0.0D / 0.0);
        }

        ArrayList var40 = this.shapiroWilkTest(this.data);
        double var41 = (Double)var40.get(0);
        double var16 = (Double)var40.get(1);
        double var18 = (Double)var40.get(2);
        String var20 = (String)var40.get(3);
        double var21 = 0.0D / 0.0;
        double var23 = 0.0D / 0.0;
        double var25 = 0.0D / 0.0;
        String var27 = null;
        if (var11) {
            ArrayList var28 = this.shapiroWilkTest(this.strippedData);
            var21 = (Double)var28.get(0);
            var23 = (Double)var28.get(1);
            var25 = (Double)var28.get(2);
            var27 = (String)var28.get(3);
        }

        var7.add(var41);
        var7.add(var16);
        var7.add(var18);
        if (var11) {
            var7.add(var21);
            var7.add(var23);
            var7.add(var25);
        } else {
            var7.add(0.0D / 0.0);
            var7.add(0.0D / 0.0);
            var7.add(0.0D / 0.0);
        }

        if (!this.suppressPrint) {
            FileOutput var42 = new FileOutput(this.filenameout);
            var42.println("Outlier Detection Results");
            var42.println("Outlier Detection Method: Grubbs' Test");
            switch(this.bluOption) {
                case 0:
                    var42.println("Lower or upper outlier");
                    break;
                case 1:
                    var42.println("Lower outlier");
                    break;
                case 2:
                    var42.println("Upper outlier");
            }

            if (this.filenamein != null) {
                var42.println("Input file: " + this.filenamein);
            }

            var42.println();
            var42.println("File name: " + this.filenameout);
            var42.println("Run date: " + this.time_date);
            var42.println();
            if (var11) {
                var42.println("Extreme outlier indicated");
            } else {
                var42.println("No outlier indicated");
            }

            var42.println();
            var42.println("Significance level: " + this.significance + " [" + this.significance * 100.0D + "%]");
            var42.println("Test Statistic, G:  " + Fmath.truncate(var5, this.trunc));
            var42.println("Critical value, T:  " + Fmath.truncate(var3, this.trunc));
            var42.println();
            if (var11) {
                var42.print("Outlier: ");
                var42.println(Fmath.truncate(var8, this.trunc));
                var42.print("Outlier index in the original inputted data [indices start at 0]: ");
                var42.println(var10);
                var42.println();
            }

            var42.print(" ", this.field0);
            var42.print("Original data ", this.field);
            if (var11) {
                var42.println("Data with outlier removed");
            } else {
                var42.println();
            }

            var42.print("Number of data points: ", this.field0);
            var42.print(this.nPoints, this.field);
            if (var11) {
                var42.println(this.nPoints - 1);
            } else {
                var42.println();
            }

            var42.print("Sample mean: ", this.field0);
            var42.print(Fmath.truncate(Stat.mean(this.data), this.trunc), this.field);
            if (var11) {
                var42.println(Fmath.truncate(Stat.mean(this.strippedData), this.trunc));
            } else {
                var42.println();
            }

            var42.print("Sample standard deviation: ", this.field0);
            var42.print(Fmath.truncate(Stat.standardDeviation(this.data), this.trunc), this.field);
            if (var11) {
                var42.println(Fmath.truncate(Stat.standardDeviation(this.strippedData), this.trunc));
            } else {
                var42.println();
            }

            var42.print("Maximum: ", this.field0);
            var42.print(Fmath.truncate(Fmath.maximum(this.data), this.trunc), this.field);
            if (var11) {
                var42.println(Fmath.truncate(Fmath.maximum(this.strippedData), this.trunc));
            } else {
                var42.println();
            }

            var42.print("Minimum: ", this.field0);
            var42.print(Fmath.truncate(Fmath.minimum(this.data), this.trunc), this.field);
            if (var11) {
                var42.println(Fmath.truncate(Fmath.minimum(this.strippedData), this.trunc));
            } else {
                var42.println();
            }

            var42.println();
            var42.println("Shapiro-Wilk Test:");
            var42.print(" Shapiro-Wilk W value: ", this.field0);
            var42.print(Fmath.truncate(var41, this.trunc), this.field);
            if (var11) {
                var42.println(Fmath.truncate(var21, this.trunc));
            } else {
                var42.println();
            }

            var42.println(" Critical value for W: ");
            var42.print("  (" + this.significance * 100.0D + "% Significance level)", this.field0);
            var42.print(Fmath.truncate(var16, this.trunc), this.field);
            if (var11) {
                var42.println(Fmath.truncate(var23, this.trunc));
            } else {
                var42.println();
            }

            var42.print(" Shapiro-Wilk P value: ", this.field0);
            var42.print(Fmath.truncate(var18, this.trunc), this.field);
            if (var11) {
                var42.println(Fmath.truncate(var25, this.trunc));
            } else {
                var42.println();
            }

            var42.print(" Data possibly Gaussian: ", this.field0);
            var42.print(var20, this.field);
            if (var11) {
                var42.println(var27);
            } else {
                var42.println();
            }

            var42.println();
            var42.println("Probability plot:");
            var42.print(" Correlation coefficient, r: ", this.field0);
            double var29 = (Double)var12.get(2);
            double var31 = 0.0D / 0.0;
            var42.print(Fmath.truncate(var29, this.trunc), this.field);
            if (var11) {
                var31 = (Double)var12.get(6);
                var42.println(Fmath.truncate(var31, this.trunc));
            } else {
                var42.println();
            }

            var42.println(" Critical value for r: ");
            var42.print("  (" + this.significance * 100.0D + "% Significance level)", this.field0);
            double var33 = (Double)var12.get(3);
            double var35 = 0.0D / 0.0;
            var42.print(Fmath.truncate(var33, this.trunc), this.field);
            if (var11) {
                var35 = (Double)var12.get(7);
                var42.println(Fmath.truncate(var35, this.trunc));
            } else {
                var42.println();
            }

            var42.print(" Data possibly Gaussian: ", this.field0);
            String var37 = "Rejected";
            if (var29 >= var33) {
                var37 = "Accepted";
            }

            var42.print(var37, this.field);
            if (var11) {
                var37 = "Rejected";
                if (var31 >= var35) {
                    var37 = "Accepted";
                }

                var42.println(var37);
            } else {
                var42.println();
            }

            var42.print(" Gradient: ", this.field0);
            var42.print(Fmath.truncate((Double)var12.get(0), this.trunc), this.field);
            if (var11) {
                var42.println(Fmath.truncate((Double)var12.get(4), this.trunc));
            } else {
                var42.println();
            }

            var42.print(" Intercept: ", this.field0);
            var42.print(Fmath.truncate((Double)var12.get(1), this.trunc), this.field);
            if (var11) {
                var42.println(Fmath.truncate((Double)var12.get(5), this.trunc));
            } else {
                var42.println();
            }

            var42.println();
            var42.println("Input data: ");
            int var38 = 0;

            for(int var39 = 0; var39 < this.nPoints; ++var39) {
                var42.print(this.data[var39] + "  ");
                ++var38;
                if (var38 == 10) {
                    var42.println();
                    var38 = 0;
                }
            }

            var42.close();
        }

        return var7;
    }

    public void resetNumberTietjenMooreSimulations(int var1) {
        this.nTietjenMooreSimulations = var1;
    }

    public int getNumberTietjenMooreSimulations() {
        return this.nTietjenMooreSimulations;
    }

    public ArrayList<Object> outliersTietjenMoore(int var1) {
        return this.outliersTietjenMooreCore(var1, 0);
    }

    public static ArrayList<Object> outliersTietjenMoore(double[] var0, int var1) {
        Outliers var2 = new Outliers(var0);
        return var2.outliersTietjenMoore(var1);
    }

    public ArrayList<Object> upperOutliersTietjenMoore(int var1) {
        return this.outliersTietjenMooreCore(var1, 2);
    }

    public static ArrayList<Object> upperOutliersTietjenMoore(double[] var0, int var1) {
        Outliers var2 = new Outliers(var0);
        return var2.upperOutliersTietjenMoore(var1);
    }

    public ArrayList<Object> lowerOutliersTietjenMoore(int var1) {
        return this.outliersTietjenMooreCore(var1, 1);
    }

    public static ArrayList<Object> lowerOutliersTietjenMoore(double[] var0, int var1) {
        Outliers var2 = new Outliers(var0);
        return var2.lowerOutliersTietjenMoore(var1);
    }

    private ArrayList<Object> outliersTietjenMooreCore(int var1, int var2) {
        ArrayList var3 = new ArrayList();
        Stat.denominatorSwap();
        ArrayList var4 = this.orderData(this.data, var2);
        this.orderedData = (double[])((double[])var4.get(0));
        this.originalIndices = (int[])((int[])var4.get(1));
        double[] var5 = new double[var1];
        int[] var6 = new int[var1];

        for(int var7 = 0; var7 < var1; ++var7) {
            var5[var7] = this.orderedData[this.nPoints - var1 + var7];
            var6[var7] = this.originalIndices[this.nPoints - var1 + var7];
        }

        double var50 = this.getTietjenMooreLvalue(this.orderedData, this.nPoints, var1);
        double[] var9 = new double[this.nTietjenMooreSimulations];
        Object var10 = null;
        double var11 = 0.0D;

        for(int var13 = 0; var13 < this.nTietjenMooreSimulations; ++var13) {
            double[] var14 = Stat.gaussianRand(0.0D, 1.0D, this.nPoints);
            ArrayList var15 = this.orderData(var14, 0);
            double[] var51 = DeepCopy.copy((double[])((double[])var15.get(0)));
            var9[var13] = this.getTietjenMooreLvalue(var51, this.nPoints, var1);
            if (var9[var13] >= var50) {
                ++var11;
            }
        }

        double var52 = var11 / (double)this.nTietjenMooreSimulations;
        double var53 = 1.0D - var52;
        ArrayMaths var17 = new ArrayMaths(var9);
        var17 = var17.sort();
        var9 = var17.array();
        int var18 = (int)(this.significance * (double)this.nTietjenMooreSimulations);
        double var19 = var9[var18];
        boolean var21 = false;
        if (var50 < var19) {
            var21 = true;
        }

        var3.add(var21);
        var3.add(var5);
        var3.add(var6);
        if (var21) {
            this.strippedData = new double[this.nPoints - var1];
            int var22 = 0;

            for(int var23 = 0; var23 < this.nPoints; ++var23) {
                boolean var24 = true;

                for(int var25 = 0; var25 < var1; ++var25) {
                    if (var23 == var6[var25]) {
                        var24 = false;
                    }
                }

                if (var24) {
                    this.strippedData[var22] = this.data[var23];
                    ++var22;
                }
            }

            var3.add(this.strippedData);
        } else {
            var3.add(this.data);
        }

        var3.add(var50);
        var3.add(var19);
        var3.add(this.significance);
        var3.add(var53);
        var3.add(this.nPoints);
        var3.add(var1);
        ArrayList var54 = this.probabilityPlots(var21, this.data, this.strippedData);
        var3.add(var54.get(0));
        var3.add(var54.get(1));
        var3.add(var54.get(2));
        var3.add(var54.get(3));
        if (var21) {
            var3.add(var54.get(4));
            var3.add(var54.get(5));
            var3.add(var54.get(6));
            var3.add(var54.get(7));
        } else {
            var3.add(0.0D / 0.0);
            var3.add(0.0D / 0.0);
            var3.add(0.0D / 0.0);
            var3.add(0.0D / 0.0);
        }

        ArrayList var55 = this.shapiroWilkTest(this.data);
        double var56 = (Double)var55.get(0);
        double var26 = (Double)var55.get(1);
        double var28 = (Double)var55.get(2);
        String var30 = (String)var55.get(3);
        double var31 = 0.0D / 0.0;
        double var33 = 0.0D / 0.0;
        double var35 = 0.0D / 0.0;
        String var37 = null;
        if (var21) {
            ArrayList var38 = this.shapiroWilkTest(this.strippedData);
            var31 = (Double)var38.get(0);
            var33 = (Double)var38.get(1);
            var35 = (Double)var38.get(2);
            var37 = (String)var38.get(3);
        }

        var3.add(var56);
        var3.add(var26);
        var3.add(var28);
        if (var21) {
            var3.add(var31);
            var3.add(var33);
            var3.add(var35);
        } else {
            var3.add(0.0D / 0.0);
            var3.add(0.0D / 0.0);
            var3.add(0.0D / 0.0);
        }

        if (!this.suppressPrint) {
            FileOutput var57 = new FileOutput(this.filenameout);
            var57.println("Outlier Detection Results");
            var57.println("Outlier Detection Method: Tietjen-Moore Test");
            switch(var2) {
                case 0:
                    var57.println("Lower and upper outliers");
                    break;
                case 1:
                    var57.println("Lower outliers");
                    break;
                case 2:
                    var57.println("Upper outliers");
            }

            if (this.filenamein != null) {
                var57.println("Input file: " + this.filenamein);
            }

            var57.println();
            var57.println("File name: " + this.filenameout);
            var57.println("Run date: " + this.time_date);
            var57.println();
            var57.println("Number of outliers checked: " + var1);
            if (var21) {
                var57.println(var1 + " outliers indicated");
            } else {
                var57.println("No outliers indicated");
            }

            var57.println();
            var57.println("Significance level:    " + this.significance + " [" + this.significance * 100.0D + "%]");
            var57.println("Test Statistic, Lk:    " + Fmath.truncate(var50, this.trunc));
            var57.println("Critical value, Lcrit: " + Fmath.truncate(var19, this.trunc));
            var57.println("Significance level at which Lk = Lcrit:  " + Fmath.truncate(var53, this.trunc) + " [" + Fmath.truncate(var53 * 100.0D, 8) + "%]");
            var57.println();
            if (var21) {
                var57.println("Outliers: ");

                int var39;
                for(var39 = 0; var39 < var1; ++var39) {
                    var57.print(Fmath.truncate(var5[var39], this.trunc) + "  ");
                }

                var57.println();
                var57.println("Outlier indices in the original inputted data [indices start at 0]: ");

                for(var39 = 0; var39 < var1; ++var39) {
                    var57.print(var6[var39] + "  ");
                }

                var57.println();
            }

            var57.print(" ", this.field0);
            var57.print("Original data ", this.field);
            if (var21) {
                var57.println("Data with outlier/s removed");
            } else {
                var57.println();
            }

            var57.print("Number of data points: ", this.field0);
            var57.print(this.nPoints, this.field);
            if (var21) {
                var57.println(this.nPoints - var1);
            } else {
                var57.println();
            }

            var57.print("Sample mean: ", this.field0);
            var57.print(Fmath.truncate(Stat.mean(this.data), this.trunc), this.field);
            if (var21) {
                var57.println(Fmath.truncate(Stat.mean(this.strippedData), this.trunc));
            } else {
                var57.println();
            }

            var57.print("Sample standard deviation: ", this.field0);
            var57.print(Fmath.truncate(Stat.standardDeviation(this.data), this.trunc), this.field);
            if (var21) {
                var57.println(Fmath.truncate(Stat.standardDeviation(this.strippedData), this.trunc));
            } else {
                var57.println();
            }

            var57.print("Maximum: ", this.field0);
            var57.print(Fmath.truncate(Fmath.maximum(this.data), this.trunc), this.field);
            if (var21) {
                var57.println(Fmath.truncate(Fmath.maximum(this.strippedData), this.trunc));
            } else {
                var57.println();
            }

            var57.print("Minimum: ", this.field0);
            var57.print(Fmath.truncate(Fmath.minimum(this.data), this.trunc), this.field);
            if (var21) {
                var57.println(Fmath.truncate(Fmath.minimum(this.strippedData), this.trunc));
            } else {
                var57.println();
            }

            var57.println();
            var57.println("Shapiro-Wilk Test:");
            var57.print(" Shapiro-Wilk W value: ", this.field0);
            var57.print(Fmath.truncate(var56, this.trunc), this.field);
            if (var21) {
                var57.println(Fmath.truncate(var31, this.trunc));
            } else {
                var57.println();
            }

            var57.println(" Critical value for W: ");
            var57.print("  (" + this.significance * 100.0D + "% Significance level)", this.field0);
            var57.print(Fmath.truncate(var26, this.trunc), this.field);
            if (var21) {
                var57.println(Fmath.truncate(var33, this.trunc));
            } else {
                var57.println();
            }

            var57.print(" Shapiro-Wilk P value: ", this.field0);
            var57.print(Fmath.truncate(var28, this.trunc), this.field);
            if (var21) {
                var57.println(Fmath.truncate(var35, this.trunc));
            } else {
                var57.println();
            }

            var57.print(" Data possibly Gaussian: ", this.field0);
            var57.print(var30, this.field);
            if (var21) {
                var57.println(var37);
            } else {
                var57.println();
            }

            var57.println();
            var57.println("Probability plot:");
            var57.print(" Correlation coefficient, r: ", this.field0);
            double var58 = (Double)var54.get(2);
            double var41 = 0.0D / 0.0;
            var57.print(Fmath.truncate(var58, this.trunc), this.field);
            if (var21) {
                var41 = (Double)var54.get(6);
                var57.println(Fmath.truncate(var41, this.trunc));
            } else {
                var57.println();
            }

            var57.println(" Critical value for r: ");
            var57.print("  (" + this.significance * 100.0D + "% Significance level)", this.field0);
            double var43 = (Double)var54.get(3);
            double var45 = 0.0D / 0.0;
            var57.print(Fmath.truncate(var43, this.trunc), this.field);
            if (var21) {
                var45 = (Double)var54.get(7);
                var57.println(Fmath.truncate(var45, this.trunc));
            } else {
                var57.println();
            }

            var57.print(" Data possibly Gaussian: ", this.field0);
            String var47 = "Rejected";
            if (var58 >= var43) {
                var47 = "Accepted";
            }

            var57.print(var47, this.field);
            if (var21) {
                var47 = "Rejected";
                if (var41 >= var45) {
                    var47 = "Accepted";
                }

                var57.println(var47);
            } else {
                var57.println();
            }

            var57.print(" Gradient: ", this.field0);
            var57.print(Fmath.truncate((Double)var54.get(0), this.trunc), this.field);
            if (var21) {
                var57.println(Fmath.truncate((Double)var54.get(4), this.trunc));
            } else {
                var57.println();
            }

            var57.print(" Intercept: ", this.field0);
            var57.print(Fmath.truncate((Double)var54.get(1), this.trunc), this.field);
            if (var21) {
                var57.println(Fmath.truncate((Double)var54.get(5), this.trunc));
            } else {
                var57.println();
            }

            var57.println();
            var57.println("Input data: ");
            int var48 = 0;

            for(int var49 = 0; var49 < this.nPoints; ++var49) {
                var57.print(this.data[var49] + "  ");
                ++var48;
                if (var48 == 10) {
                    var57.println();
                    var48 = 0;
                }
            }

            var57.close();
        }

        Stat.denominatorUnswap();
        return var3;
    }

    public double getTietjenMooreCriticalL(int var1) {
        return this.getTietjenMooreCriticalLcore(var1, 0);
    }

    public static double getTietjenMooreCriticalL(int var0, int var1, int var2, double var3) {
        Outliers var5 = new Outliers();
        var5.setNpoints(var1);
        var5.resetNumberTietjenMooreSimulations(var2);
        var5.resetSignificanceLevel(var3);
        return var5.getTietjenMooreCriticalL(var0);
    }

    public static double getTietjenMooreCriticalL(int var0, int var1, double var2) {
        Outliers var4 = new Outliers();
        var4.setNpoints(var1);
        var4.resetSignificanceLevel(var2);
        return var4.getTietjenMooreCriticalL(var0);
    }

    public double getTietjenMooreLowerCriticalL(int var1) {
        return this.getTietjenMooreCriticalLcore(var1, 1);
    }

    public static double getTietjenMooreLowerCriticalL(int var0, int var1, int var2, double var3) {
        Outliers var5 = new Outliers();
        var5.setNpoints(var1);
        var5.resetNumberTietjenMooreSimulations(var2);
        var5.resetSignificanceLevel(var3);
        return var5.getTietjenMooreLowerCriticalL(var0);
    }

    public static double getTietjenMooreLowerCriticalL(int var0, int var1, double var2) {
        Outliers var4 = new Outliers();
        var4.setNpoints(var1);
        var4.resetSignificanceLevel(var2);
        return var4.getTietjenMooreLowerCriticalL(var0);
    }

    public double getTietjenMooreUpperCriticalL(int var1) {
        return this.getTietjenMooreCriticalLcore(var1, 2);
    }

    public static double getTietjenMooreUpperCriticalL(int var0, int var1, int var2, double var3) {
        Outliers var5 = new Outliers();
        var5.setNpoints(var1);
        var5.resetNumberTietjenMooreSimulations(var2);
        var5.resetSignificanceLevel(var3);
        return var5.getTietjenMooreUpperCriticalL(var0);
    }

    public static double getTietjenMooreUpperCriticalL(int var0, int var1, double var2) {
        Outliers var4 = new Outliers();
        var4.setNpoints(var1);
        var4.resetSignificanceLevel(var2);
        return var4.getTietjenMooreUpperCriticalL(var0);
    }

    private double getTietjenMooreCriticalLcore(int var1, int var2) {
        double var3 = 0.0D / 0.0;
        Stat.denominatorSwap();
        double[] var5 = new double[this.nTietjenMooreSimulations];
        Object var6 = null;

        for(int var7 = 0; var7 < this.nTietjenMooreSimulations; ++var7) {
            double[] var8 = Stat.gaussianRand(0.0D, 1.0D, this.nPoints);
            ArrayList var9 = this.orderData(var8, var2);
            double[] var10 = (double[])((double[])var9.get(0));
            var5[var7] = this.getTietjenMooreLvalue(var10, this.nPoints, var1);
        }

        ArrayMaths var11 = new ArrayMaths(var5);
        var11 = var11.sort();
        var5 = var11.array();
        int var12 = (int)(this.significance * (double)this.nTietjenMooreSimulations);
        var3 = var5[var12];
        Stat.denominatorUnswap();
        return var3;
    }

    private double getTietjenMooreLvalue(double[] var1, int var2, int var3) {
        double var4 = 0.0D / 0.0;
        int var6 = var2 - var3;
        double[] var7 = new double[var6];

        for(int var8 = 0; var8 < var6; ++var8) {
            var7[var8] = var1[var8];
        }

        double var17 = Stat.mean(var7);
        double var10 = 0.0D;
        double var12 = 0.0D;
        double var14 = Stat.mean(var1);

        int var16;
        for(var16 = 0; var16 < var6; ++var16) {
            var10 += Fmath.square(var1[var16] - var17);
        }

        for(var16 = 0; var16 < this.nPoints; ++var16) {
            var12 += Fmath.square(var1[var16] - var14);
        }

        var4 = var10 / var12;
        return var4;
    }

    public ArrayList<Object> outliersTeitjenMoore(int var1) {
        return this.outliersTietjenMoore(var1);
    }

    public ArrayList<Object> lowerOutliersTeitjenMoore(int var1) {
        return this.lowerOutliersTietjenMoore(var1);
    }

    public ArrayList<Object> upperOutliersTeitjenMoore(int var1) {
        return this.upperOutliersTietjenMoore(var1);
    }

    public double getTeitjenMooreCriticalL(int var1) {
        return this.getTietjenMooreCriticalL(var1);
    }

    public double getTeitjenMooreLowerCriticalL(int var1) {
        return this.getTietjenMooreLowerCriticalL(var1);
    }

    public double getTeitjenMooreUpperCriticalL(int var1) {
        return this.getTietjenMooreUpperCriticalL(var1);
    }

    public void resetNumberTeitjenMooreSimulations(int var1) {
        this.resetNumberTietjenMooreSimulations(var1);
    }

    public int getNumberTeitjenMooreSimulations() {
        return this.getNumberTietjenMooreSimulations();
    }

    public static ArrayList<Object> outliersTeitjenMoore(double[] var0, int var1) {
        return outliersTietjenMoore(var0, var1);
    }

    public static ArrayList<Object> lowerOutliersTeitjenMoore(double[] var0, int var1) {
        return lowerOutliersTietjenMoore(var0, var1);
    }

    public static ArrayList<Object> upperOutliersTeitjenMoore(double[] var0, int var1) {
        return upperOutliersTietjenMoore(var0, var1);
    }

    public static double getTeitjenMooreCriticalL(int var0, int var1, int var2, double var3) {
        return getTietjenMooreCriticalL(var0, var1, var2, var3);
    }

    public static double getTeitjenMooreCriticalL(int var0, int var1, double var2) {
        return getTietjenMooreCriticalL(var0, var1, var2);
    }

    public static double getTeitjenMooreLowerCriticalL(int var0, int var1, int var2, double var3) {
        return getTietjenMooreLowerCriticalL(var0, var1, var2, var3);
    }

    public static double getTeitjenMooreLowerCriticalL(int var0, int var1, double var2) {
        return getTietjenMooreLowerCriticalL(var0, var1, var2);
    }

    public static double getTeitjenMooreUpperCriticalL(int var0, int var1, int var2, double var3) {
        return getTietjenMooreUpperCriticalL(var0, var1, var2, var3);
    }

    public static double getTeitjenMooreUpperCriticalL(int var0, int var1, double var2) {
        return getTietjenMooreUpperCriticalL(var0, var1, var2);
    }

    public ArrayList<Object> outliersESD(int var1) {
        return this.outliersESDcore(var1, 0);
    }

    public ArrayList<Object> outliersESDcore(int var1, int var2) {
        if (this.nPoints - var1 <= 1) {
            System.out.println("Number of suggested outliers, " + var1 + ", must be less than the number of points minus two, " + (this.nPoints - 2));
            --var1;
            System.out.println("The number of outliers has been reduced to " + var1);
        }

        this.esdLambdas = this.getESDlambdas(var1);
        this.esdRvalues = new double[var1];
        this.esdTests = new boolean[var1];
        this.esdMaxValues = new double[var1];
        double[] var3 = (double[])((double[])this.orderData(this.data, var2).get(0));
        boolean var4 = false;
        int var5 = 0;

        for(int var6 = 0; var6 < var1; ++var6) {
            this.esdRvalues[var6] = Math.abs(var3[this.nPoints - var6 - 1] - Stat.mean(var3)) / Stat.standardDeviation(var3);
            this.esdTests[var6] = false;
            if (this.esdRvalues[var6] > this.esdLambdas[var6]) {
                this.esdTests[var6] = true;
                var4 = true;
                var5 = var6 + 1;
            }

            double[] var7 = DeepCopy.copy(var3);
            this.esdMaxValues[var6] = var3[this.nPoints - 1 - var6];
            var3 = new double[this.nPoints - var6 - 1];

            for(int var8 = 0; var8 < this.nPoints - var6 - 1; ++var8) {
                var3[var8] = var7[var8];
            }

            var3 = (double[])((double[])this.orderData(var3, var2).get(0));
        }

        double[] var40 = null;
        int[] var41 = null;
        Object var42 = null;
        if (var4) {
            var40 = new double[var5];
            var41 = new int[var5];
            boolean[] var43 = new boolean[this.nPoints];

            int var9;
            for(var9 = 0; var9 < this.nPoints; ++var9) {
                var43[var9] = true;
            }

            int var10;
            for(var9 = 0; var9 < var5; ++var9) {
                var40[var9] = this.esdMaxValues[var9];

                for(var10 = 0; var10 < this.nPoints; ++var10) {
                    if (var43[var10] && var40[var9] == this.data[var10]) {
                        var41[var9] = var10;
                        var43[var10] = false;
                        break;
                    }
                }
            }

            var9 = this.nPoints - var5;
            this.strippedData = new double[var9];
            var10 = 0;

            for(int var11 = 0; var11 < this.nPoints; ++var11) {
                if (var43[var11]) {
                    this.strippedData[var10] = this.data[var11];
                    ++var10;
                }
            }
        }

        ArrayList var44 = new ArrayList();
        var44.add(var4);
        var44.add(var40);
        var44.add(var41);
        var44.add(this.strippedData);
        var44.add(this.esdRvalues);
        var44.add(this.esdLambdas);
        var44.add(this.esdTests);
        var44.add(this.esdMaxValues);
        var44.add(this.significance);
        var44.add(this.nPoints);
        var44.add(var1);
        ArrayList var45 = this.probabilityPlots(var4, this.data, this.strippedData);
        var44.add(var45.get(0));
        var44.add(var45.get(1));
        var44.add(var45.get(2));
        var44.add(var45.get(3));
        if (var4) {
            var44.add(var45.get(4));
            var44.add(var45.get(5));
            var44.add(var45.get(6));
            var44.add(var45.get(7));
        } else {
            var44.add(0.0D / 0.0);
            var44.add(0.0D / 0.0);
            var44.add(0.0D / 0.0);
            var44.add(0.0D / 0.0);
        }

        ArrayList var46 = this.shapiroWilkTest(this.data);
        double var12 = (Double)var46.get(0);
        double var14 = (Double)var46.get(1);
        double var16 = (Double)var46.get(2);
        String var18 = (String)var46.get(3);
        double var19 = 0.0D / 0.0;
        double var21 = 0.0D / 0.0;
        double var23 = 0.0D / 0.0;
        String var25 = null;
        if (var4) {
            ArrayList var26 = this.shapiroWilkTest(this.strippedData);
            var19 = (Double)var26.get(0);
            var21 = (Double)var26.get(1);
            var23 = (Double)var26.get(2);
            var25 = (String)var26.get(3);
        }

        var44.add(var12);
        var44.add(var14);
        var44.add(var16);
        if (var4) {
            var44.add(var19);
            var44.add(var21);
            var44.add(var23);
        } else {
            var44.add(0.0D / 0.0);
            var44.add(0.0D / 0.0);
            var44.add(0.0D / 0.0);
        }

        if (!this.suppressPrint) {
            FileOutput var47 = new FileOutput(this.filenameout);
            var47.println("Outlier Detection Results");
            var47.println("Outlier Detection Method: Generalised ESD (Extreme Studentised Deviate) Test");
            var47.println("Lower and upper outliers");
            if (this.filenamein != null) {
                var47.println("Input file: " + this.filenamein);
            }

            var47.println();
            var47.println("File name: " + this.filenameout);
            var47.println("Run date: " + this.time_date);
            var47.println();
            if (var4) {
                var47.println(var5 + " outliers indicated");
            } else {
                var47.println("No outliers indicated");
            }

            var47.println();
            var47.println("Significance level: " + this.significance + " [" + this.significance * 100.0D + "%]");
            var47.println();
            byte var27 = 12;
            byte var28 = 4;
            var47.println("Potential   Test        Critical    R>lambda    Maximum");
            var47.println("number of   Statistic,  Value,                  deviate");
            var47.println("outliers    R           lambda                  data value");

            int var29;
            for(var29 = 0; var29 < var1; ++var29) {
                var47.print(var29 + 1, var27);
                var47.print(Fmath.truncate(this.esdRvalues[var29], var28), var27);
                var47.print(Fmath.truncate(this.esdLambdas[var29], var28), var27);
                var47.print(this.esdTests[var29], var27);
                var47.print(Fmath.truncate(this.esdMaxValues[var29], var28), var27);
                var47.println();
            }

            var47.println();
            if (var4) {
                var47.println("Outliers: ");

                for(var29 = 0; var29 < var5; ++var29) {
                    var47.print(Fmath.truncate(var40[var29], var28) + "  ");
                }

                var47.println();
                var47.println("Outlier indices in the original inputted data [indices start at 0]: ");

                for(var29 = 0; var29 < var5; ++var29) {
                    var47.print(var41[var29] + "  ");
                }

                var47.println();
                var47.println();
            }

            var47.print(" ", this.field0);
            var47.print("Original data ", this.field);
            if (var4) {
                var47.println("Data with outlier/s removed");
            } else {
                var47.println();
            }

            var47.print("Number of data points: ", this.field0);
            var47.print(this.nPoints, this.field);
            if (var4) {
                var47.println(this.nPoints - var5);
            } else {
                var47.println();
            }

            var47.print("Sample mean: ", this.field0);
            var47.print(Fmath.truncate(Stat.mean(this.data), var28), this.field);
            if (var4) {
                var47.println(Fmath.truncate(Stat.mean(this.strippedData), var28));
            } else {
                var47.println();
            }

            var47.print("Sample standard deviation: ", this.field0);
            var47.print(Fmath.truncate(Stat.standardDeviation(this.data), var28), this.field);
            if (var4) {
                var47.println(Fmath.truncate(Stat.standardDeviation(this.strippedData), var28));
            } else {
                var47.println();
            }

            var47.print("Maximum: ", this.field0);
            var47.print(Fmath.truncate(Fmath.maximum(this.data), var28), this.field);
            if (var4) {
                var47.println(Fmath.truncate(Fmath.maximum(this.strippedData), var28));
            } else {
                var47.println();
            }

            var47.print("Minimum: ", this.field0);
            var47.print(Fmath.truncate(Fmath.minimum(this.data), var28), this.field);
            if (var4) {
                var47.println(Fmath.truncate(Fmath.minimum(this.strippedData), var28));
            } else {
                var47.println();
            }

            var47.println();
            var47.println("Shapiro-Wilk Test:");
            var47.print(" Shapiro-Wilk W value: ", this.field0);
            var47.print(Fmath.truncate(var12, var28), this.field);
            if (var4) {
                var47.println(Fmath.truncate(var19, var28));
            } else {
                var47.println();
            }

            var47.println(" Critical value for W: ");
            var47.print("  (" + this.significance * 100.0D + "% Significance level)", this.field0);
            var47.print(Fmath.truncate(var14, var28), this.field);
            if (var4) {
                var47.println(Fmath.truncate(var21, var28));
            } else {
                var47.println();
            }

            var47.print(" Shapiro-Wilk P value: ", this.field0);
            var47.print(Fmath.truncate(var16, var28), this.field);
            if (var4) {
                var47.println(Fmath.truncate(var23, var28));
            } else {
                var47.println();
            }

            var47.print(" Data possibly Gaussian: ", this.field0);
            var47.print(var18, this.field);
            if (var4) {
                var47.println(var25);
            } else {
                var47.println();
            }

            var47.println();
            var47.println("Probability plot:");
            var47.print(" Correlation coefficient, r: ", this.field0);
            double var48 = (Double)var45.get(2);
            double var31 = 0.0D / 0.0;
            var47.print(Fmath.truncate(var48, var28), this.field);
            if (var4) {
                var31 = (Double)var45.get(6);
                var47.println(Fmath.truncate(var31, var28));
            } else {
                var47.println();
            }

            var47.println(" Critical value for r: ");
            var47.print("  (" + this.significance * 100.0D + "% Significance level)", this.field0);
            double var33 = (Double)var45.get(3);
            double var35 = 0.0D / 0.0;
            var47.print(Fmath.truncate(var33, var28), this.field);
            if (var4) {
                var35 = (Double)var45.get(7);
                var47.println(Fmath.truncate(var35, var28));
            } else {
                var47.println();
            }

            var47.print(" Data possibly Gaussian: ", this.field0);
            String var37 = "Rejected";
            if (var48 >= var33) {
                var37 = "Accepted";
            }

            var47.print(var37, this.field);
            if (var4) {
                var37 = "Rejected";
                if (var31 >= var35) {
                    var37 = "Accepted";
                }

                var47.println(var37);
            } else {
                var47.println();
            }

            var47.print(" Gradient: ", this.field0);
            var47.print(Fmath.truncate((Double)var45.get(0), var28), this.field);
            if (var4) {
                var47.println(Fmath.truncate((Double)var45.get(4), var28));
            } else {
                var47.println();
            }

            var47.print(" Intercept: ", this.field0);
            var47.print(Fmath.truncate((Double)var45.get(1), var28), this.field);
            if (var4) {
                var47.println(Fmath.truncate((Double)var45.get(5), var28));
            } else {
                var47.println();
            }

            var47.println();
            var47.println("Input data: ");
            int var38 = 0;

            for(int var39 = 0; var39 < this.nPoints; ++var39) {
                var47.print(this.data[var39] + "  ");
                ++var38;
                if (var38 == 10) {
                    var47.println();
                    var38 = 0;
                }
            }

            var47.close();
        }

        return var44;
    }

    public static ArrayList<Object> outliersESD(double[] var0, int var1) {
        Outliers var2 = new Outliers(var0);
        return var2.outliersESD(var1);
    }

    public double[] getESDlambdas(int var1) {
        this.esdLambdas = new double[var1];

        for(int var2 = 1; var2 <= var1; ++var2) {
            this.esdLambdas[var2 - 1] = this.getESDlambda(var2);
        }

        return this.esdLambdas;
    }

    private double getESDlambda(int var1) {
        return this.getESDlambdaCore(var1, 2);
    }

    private double getESDlambdaCore(int var1, int var2) {
        double var3 = 0.0D / 0.0;
        int var5 = this.nPoints - var1;
        double var6 = 1.0D - this.significance / (double)(var5 + 1) / (double)var2;
        double var8 = Stat.studentstValue(var6, var5 - 1);
        var3 = (double)var5 * var8 / Math.sqrt(((double)(var5 - 1) + var8 * var8) * (double)(var5 + 1));
        return var3;
    }

    public static double[] getESDlambdas(int var0, int var1, double var2) {
        Outliers var4 = new Outliers();
        var4.resetSignificanceLevel(var2);
        var4.setNpoints(var1);
        return var4.getESDlambdas(var0);
    }

    public double[] calcIgnoredPoints(int var1, int var2, int[] var3, int var4) {
        ArrayList var5 = new ArrayList();
        int var6;
        if (var4 == 0) {
            for(var6 = 1; var6 < var1; ++var6) {
                var5.add(this.data[var3[var6]]);
            }

            for(var6 = this.nPoints - var2; var6 < this.nPoints; ++var6) {
                var5.add(this.data[var3[var6]]);
            }
        } else {
            for(var6 = 0; var6 < var2; ++var6) {
                var5.add(this.data[var3[var6]]);
            }

            for(var6 = this.nPoints - var1; var6 < this.nPoints - 1; ++var6) {
                var5.add(this.data[var3[var6]]);
            }
        }

        this.pointsIgnored = false;
        this.ignoredPoints = null;
        this.nPointsIgnored = var5.size();
        if (this.nPointsIgnored > 0) {
            this.ignoredPoints = new double[this.nPointsIgnored];
            this.pointsIgnored = true;

            for(var6 = 0; var6 < this.nPointsIgnored; ++var6) {
                this.ignoredPoints[var6] = (Double)var5.get(var6);
            }
        }

        return this.ignoredPoints;
    }

    public static int[] ignoredIndicesLowerTest(int var0, int var1, int var2) {
        int[] var3 = null;
        ArrayList var4 = new ArrayList();

        int var5;
        for(var5 = 1; var5 < var0; ++var5) {
            var4.add(var5 + 1);
        }

        for(var5 = var2 - var1; var5 < var2; ++var5) {
            var4.add(var5 + 1);
        }

        var5 = var4.size();
        if (var5 > 0) {
            var3 = new int[var5];

            for(int var6 = 0; var6 < var5; ++var6) {
                var3[var6] = (Integer)var4.get(var6);
            }
        }

        return var3;
    }

    public static int[] ignoredIndicesUpperTest(int var0, int var1, int var2) {
        int[] var3 = null;
        ArrayList var4 = new ArrayList();

        int var5;
        for(var5 = 0; var5 < var1; ++var5) {
            var4.add(var5 + 1);
        }

        for(var5 = var2 - var0; var5 < var2 - 1; ++var5) {
            var4.add(var5 + 1);
        }

        var5 = var4.size();
        if (var5 > 0) {
            var3 = new int[var5];

            for(int var6 = 0; var6 < var5; ++var6) {
                var3[var6] = (Integer)var4.get(var6);
            }
        }

        return var3;
    }

    public void resetNumberDixonSimulations(int var1) {
        this.nDixonSimulations = var1;
    }

    public int getNumberDixonSimulations() {
        return this.nDixonSimulations;
    }

    private int[] getSubscripts(int var1, int var2, int var3) {
        int[] var4 = new int[4];
        if (var3 == 0) {
            var4[0] = var1;
            var4[1] = 0;
            var4[2] = this.nPoints - var2 - 1;
            var4[3] = 0;
        } else {
            var4[0] = this.nPoints - 1;
            var4[1] = this.nPoints - var1 - 1;
            var4[2] = this.nPoints - 1;
            var4[3] = var2;
        }

        return var4;
    }

    private static int[] getSubscripts(int var0, int var1, int var2, int var3) {
        int[] var4 = new int[4];
        if (var2 == 0) {
            var4[0] = var0;
            var4[1] = 0;
            var4[2] = var3 - var1 - 1;
            var4[3] = 0;
        } else {
            var4[0] = var3 - 1;
            var4[1] = var3 - var0 - 1;
            var4[2] = var3 - 1;
            var4[3] = var1;
        }

        return var4;
    }

    public ArrayList<Object> outlierDixon(int var1, int var2) {
        this.bluOption = 0;
        new ArrayList();
        double[] var4 = (double[])((double[])this.orderData(DeepCopy.copy(this.data), 2).get(0));
        int[] var5 = (int[])((int[])this.orderData(var4, 2).get(1));
        byte var6 = 0;
        int[] var7 = this.getSubscripts(var1, var2, var6);
        double var8 = var4[var7[0]] - var4[var7[1]];
        double var10 = var4[var7[2]] - var4[var7[3]];
        double var12 = var8 / var10;
        var6 = 1;
        int[] var14 = this.getSubscripts(var1, var2, var6);
        var8 = var4[var14[0]] - var4[var14[1]];
        var10 = var4[var14[2]] - var4[var14[3]];
        double var15 = var8 / var10;
        var6 = 0;
        if (var15 > var12) {
            var6 = 1;
        }

        boolean var17 = true;
        double var18 = 0.0D / 0.0;
        double var20 = 0.0D / 0.0;
        boolean var22 = false;
        int var28;
        if (var6 == 0) {
            var20 = var12;
            var28 = var5[0];
            var18 = getDixonCritical(var7, this.nPoints, this.nDixonSimulations, this.significance, 2);
            if (var12 >= var18) {
                var22 = true;
            }
        } else {
            var20 = var15;
            var28 = var5[this.nPoints - 1];
            var18 = getDixonCritical(var14, this.nPoints, this.nDixonSimulations, this.significance, 2);
            if (var15 >= var18) {
                var22 = true;
            }
        }

        double var23 = this.data[var28];
        this.calcIgnoredPoints(var1, var2, var5, var6);
        this.strippedData(var22, var28);
        ArrayList var25 = this.probabilityPlots(var22, this.data, this.strippedData);
        ArrayList var26 = this.shapiroWilkTest(this.data);
        ArrayList var27 = null;
        if (var22) {
            var27 = this.shapiroWilkTest(this.strippedData);
        }

        if (!this.suppressPrint) {
            this.dixonTextFile(var22, var1, var2, var20, var18, var28, var23, var25, var26, var27);
        }

        return this.dixonRetArray(var22, var20, var18, var28, var1, var2, var25, var26, var27);
    }

    public static ArrayList<Object> outlierDixon(double[] var0, int var1, int var2) {
        Outliers var3 = new Outliers(var0);
        return var3.outlierDixon(var1, var2);
    }

    public ArrayList<Object> outlierDixon() {
        return this.outlierDixon(1, 0);
    }

    public static ArrayList<Object> outlierDixon(double[] var0) {
        Outliers var1 = new Outliers(var0);
        return var1.outlierDixon(1, 0);
    }

    public ArrayList<Object> lowerOutlierDixon(int var1, int var2) {
        this.bluOption = 1;
        byte var3 = 0;
        int[] var4 = this.getSubscripts(var1, var2, var3);
        new ArrayList();
        double[] var6 = (double[])((double[])this.orderData(DeepCopy.copy(this.data), 2).get(0));
        int[] var7 = (int[])((int[])this.orderData(var6, 2).get(1));
        double var8 = var6[var4[0]] - var6[var4[1]];
        double var10 = var6[var4[2]] - var6[var4[3]];
        double var12 = var8 / var10;
        int var14 = var7[0];
        double var15 = getDixonCritical(var4, this.nPoints, this.nDixonSimulations, this.significance, 1);
        boolean var17 = false;
        if (var12 >= var15) {
            var17 = true;
        }

        double var18 = this.data[var14];
        this.calcIgnoredPoints(var1, var2, var7, var3);
        this.strippedData(var17, var14);
        ArrayList var20 = this.probabilityPlots(var17, this.data, this.strippedData);
        ArrayList var21 = this.shapiroWilkTest(this.data);
        ArrayList var22 = null;
        if (var17) {
            var22 = this.shapiroWilkTest(this.strippedData);
        }

        if (!this.suppressPrint) {
            this.dixonTextFile(var17, var1, var2, var12, var15, var14, var18, var20, var21, var22);
        }

        return this.dixonRetArray(var17, var12, var15, var14, var1, var2, var20, var21, var22);
    }

    private ArrayList<Object> dixonRetArray(boolean var1, double var2, double var4, int var6, int var7, int var8, ArrayList<Double> var9, ArrayList<Object> var10, ArrayList<Object> var11) {
        ArrayList var12 = new ArrayList();
        double var13 = (Double)var10.get(0);
        double var15 = (Double)var10.get(1);
        double var17 = (Double)var10.get(2);
        String var19 = (String)var10.get(3);
        double var20 = 0.0D / 0.0;
        double var22 = 0.0D / 0.0;
        double var24 = 0.0D / 0.0;
        String var26 = null;
        if (var1) {
            var20 = (Double)var11.get(0);
            var22 = (Double)var11.get(1);
            var24 = (Double)var11.get(2);
            var26 = (String)var11.get(3);
        }

        double var27 = 0.0D / 0.0;
        Object var29 = null;
        int var30 = -1;
        double[] var33;
        if (var1) {
            var27 = this.data[var6];
            var33 = new double[this.nPoints - 1];
            int var31 = 0;

            for(int var32 = 0; var32 < this.nPoints - 2; ++var32) {
                if (var32 != var6) {
                    var33[var31] = this.data[var32];
                }

                ++var31;
            }

            var30 = var6;
        } else {
            var33 = DeepCopy.copy(this.data);
        }

        var12.add(var1);
        var12.add(var27);
        var12.add(var30);
        var12.add(var33);
        var12.add(var2);
        var12.add(var4);
        var12.add(this.significance);
        var12.add(this.nPoints);
        var12.add(var7);
        var12.add(var8);
        var12.add(this.nPointsIgnored);
        var12.add(this.ignoredPoints);
        var12.add(var9.get(0));
        var12.add(var9.get(1));
        var12.add(var9.get(2));
        var12.add(var9.get(3));
        if (var1) {
            var12.add(var9.get(4));
            var12.add(var9.get(5));
            var12.add(var9.get(6));
            var12.add(var9.get(7));
        } else {
            var12.add(0.0D / 0.0);
            var12.add(0.0D / 0.0);
            var12.add(0.0D / 0.0);
            var12.add(0.0D / 0.0);
        }

        var12.add(var13);
        var12.add(var15);
        var12.add(var17);
        if (var1) {
            var12.add(var20);
            var12.add(var22);
            var12.add(var24);
        } else {
            var12.add(0.0D / 0.0);
            var12.add(0.0D / 0.0);
            var12.add(0.0D / 0.0);
        }

        return var12;
    }

    public static ArrayList<Object> lowerOutlierDixon(double[] var0, int var1, int var2) {
        Outliers var3 = new Outliers(var0);
        return var3.lowerOutlierDixon(var1, var2);
    }

    public ArrayList<Object> lower0outlierDixon() {
        return this.lowerOutlierDixon(1, 0);
    }

    public static ArrayList<Object> lowerOutlierDixon(double[] var0) {
        Outliers var1 = new Outliers(var0);
        return var1.lowerOutlierDixon(1, 0);
    }

    public ArrayList<Object> upperOutlierDixon(int var1, int var2) {
        this.bluOption = 2;
        byte var3 = 1;
        int[] var4 = this.getSubscripts(var1, var2, var3);
        new ArrayList();
        double[] var6 = (double[])((double[])this.orderData(DeepCopy.copy(this.data), 2).get(0));
        int[] var7 = (int[])((int[])this.orderData(var6, 2).get(1));
        double var8 = var6[var4[0]] - var6[var4[1]];
        double var10 = var6[var4[2]] - var6[var4[3]];
        double var12 = var8 / var10;
        int var14 = var7[this.nPoints - 1];
        double var15 = getDixonCritical(var4, this.nPoints, this.nDixonSimulations, this.significance, 1);
        boolean var17 = false;
        if (var12 >= var15) {
            var17 = true;
        }

        double var18 = this.data[var14];
        this.calcIgnoredPoints(var1, var2, var7, var3);
        this.strippedData(var17, var14);
        ArrayList var20 = this.probabilityPlots(var17, this.data, this.strippedData);
        ArrayList var21 = this.shapiroWilkTest(this.data);
        ArrayList var22 = null;
        if (var17) {
            var22 = this.shapiroWilkTest(this.strippedData);
        }

        if (!this.suppressPrint) {
            this.dixonTextFile(var17, var1, var2, var12, var15, var14, var18, var20, var21, var22);
        }

        return this.dixonRetArray(var17, var12, var15, var14, var1, var2, var20, var21, var22);
    }

    public static ArrayList<Object> upperOutlierDixon(double[] var0, int var1, int var2) {
        Outliers var3 = new Outliers(var0);
        return var3.upperOutlierDixon(var1, var2);
    }

    public ArrayList<Object> upperOutlierDixon() {
        return this.upperOutlierDixon(1, 0);
    }

    public static ArrayList<Object> upperOutlierDixon(double[] var0) {
        Outliers var1 = new Outliers(var0);
        return var1.upperOutlierDixon(1, 0);
    }

    public static double getDixonOneTailedCriticalQ(int var0, int var1, int var2, int var3, double var4) {
        int[] var6 = getSubscripts(var0, var1, 0, var2);
        return getDixonCritical(var6, var2, var3, var4, 1);
    }

    public static double getDixonOneTailedCriticalQ(int var0, int var1, int var2, double var3) {
        int[] var5 = getSubscripts(var0, var1, 0, var2);
        return getDixonCritical(var5, var2, nDixonSimulationsStatic, var3, 1);
    }

    public static double getDixonOneTailedCriticalQ(int var0, int var1, double var2) {
        int[] var4 = getSubscripts(1, 0, 0, var0);
        return getDixonCritical(var4, var0, var1, var2, 1);
    }

    public static double getDixonOneTailedCriticalQ(int var0, double var1) {
        int[] var3 = getSubscripts(1, 0, 0, var0);
        return getDixonCritical(var3, var0, nDixonSimulationsStatic, var1, 1);
    }

    public static double getDixonTwoTailedCriticalQ(int var0, int var1, int var2, int var3, double var4) {
        int[] var6 = getSubscripts(var0, var1, 0, var2);
        return getDixonCritical(var6, var2, var3, var4, 2);
    }

    public static double getDixonTwoTailedCriticalQ(int var0, int var1, int var2, double var3) {
        int[] var5 = getSubscripts(var0, var1, 0, var2);
        return getDixonCritical(var5, var2, nDixonSimulationsStatic, var3, 2);
    }

    public static double getDixonTwoTailedCriticalQ(int var0, int var1, double var2) {
        int[] var4 = getSubscripts(1, 0, 0, var0);
        return getDixonCritical(var4, var0, var1, var2, 2);
    }

    public static double getDixonTwoTailedCriticalQ(int var0, double var1) {
        int[] var3 = getSubscripts(1, 0, 0, var0);
        return getDixonCritical(var3, var0, nDixonSimulationsStatic, var1, 2);
    }

    private static double getDixonCritical(int var0, int var1, int var2, int var3, double var4, int var6) {
        int[] var7 = getSubscripts(var0, var1, 0, var2);
        return getDixonCritical(var7, var2, var3, var4, var6);
    }

    private static double getDixonCritical(int var0, int var1, int var2, int var3, int var4, double var5, int var7) {
        int[] var8 = getSubscripts(var0, var1, var2, var3);
        return getDixonCritical(var8, var3, var4, var5, var7);
    }

    private static double getDixonCritical(int[] var0, int var1, int var2, double var3, int var5) {
        double[] var6 = new double[var2];

        for(int var7 = 0; var7 < var2; ++var7) {
            double[] var8 = Stat.gaussianRand(0.0D, 1.0D, var1);
            ArrayMaths var9 = new ArrayMaths(var8);
            var8 = var9.sort().array();
            double var10 = var8[var0[0]] - var8[var0[1]];
            double var12 = var8[var0[2]] - var8[var0[3]];
            var6[var7] = var10 / var12;
        }

        ArrayMaths var14 = new ArrayMaths(var6);
        var6 = var14.sort().array();
        int var15 = (int)Math.round((double)var2 * (1.0D - var3 / (double)var5));
        double var16 = var6[var15];
        return var16;
    }

    private void dixonTextFile(boolean var1, int var2, int var3, double var4, double var6, int var8, double var9, ArrayList<Double> var11, ArrayList<Object> var12, ArrayList<Object> var13) {
        String var14 = "r" + var2 + var3;
        double var15 = (Double)var12.get(0);
        double var17 = (Double)var12.get(1);
        double var19 = (Double)var12.get(2);
        String var21 = (String)var12.get(3);
        double var22 = 0.0D / 0.0;
        double var24 = 0.0D / 0.0;
        double var26 = 0.0D / 0.0;
        String var28 = null;
        if (var1) {
            var22 = (Double)var13.get(0);
            var24 = (Double)var13.get(1);
            var26 = (Double)var13.get(2);
            var28 = (String)var13.get(3);
        }

        FileOutput var29 = new FileOutput(this.filenameout);
        var29.println("Outlier Detection Results");
        var29.println("Outlier Detection Method: Dixon's Q Test with " + var14);
        String var30 = "";
        switch(this.bluOption) {
            case 0:
                var29.println("Lower or upper outlier");
                break;
            case 1:
                var29.println("Lower outlier");
                var30 = " lower ";
                break;
            case 2:
                var29.println("Upper outlier");
                var30 = " upper ";
        }

        if (this.filenamein != null) {
            var29.println("Input file: " + this.filenamein);
        }

        var29.println();
        var29.println("File name: " + this.filenameout);
        var29.println("Run date: " + this.time_date);
        var29.println();
        if (this.pointsIgnored) {
            if (this.nPointsIgnored == 1) {
                var29.print("1 point ignored: ");
            } else {
                var29.print(this.nPointsIgnored + " points ignored: ");
            }

            for(int var31 = 0; var31 < this.nPointsIgnored; ++var31) {
                var29.print(this.ignoredPoints[var31] + "   ");
            }

            var29.println();
            var29.println();
        } else {
            var29.println("No points ignored");
            var29.println();
        }

        if (var1) {
            var29.println("Extreme outlier indicated");
        } else {
            var29.println("No" + var30 + "outlier indicated");
        }

        var29.println();
        var29.println("Significance level:    " + this.significance + " [" + this.significance * 100.0D + "%]");
        var29.println("Test Statistic, " + var14 + ":   " + Fmath.truncate(var4, this.trunc));
        var29.println("Critical value, Qcrit: " + Fmath.truncate(var6, this.trunc));
        var29.println();
        if (var1) {
            var29.print("Outlier: ");
            var29.println(Fmath.truncate(var9, this.trunc));
            var29.print("Outlier index in the original inputted data [indices start at 0]: ");
            var29.println(var8);
            var29.println();
        }

        var29.print(" ", this.field0);
        var29.print("Original data ", this.field);
        if (var1) {
            var29.println("Data with outlier removed");
        } else {
            var29.println();
        }

        var29.print("Number of data points: ", this.field0);
        var29.print(this.nPoints, this.field);
        if (var1) {
            var29.println(this.nPoints - 1);
        } else {
            var29.println();
        }

        var29.print("Sample mean: ", this.field0);
        var29.print(Fmath.truncate(Stat.mean(this.data), this.trunc), this.field);
        if (var1) {
            var29.println(Fmath.truncate(Stat.mean(this.strippedData), this.trunc));
        } else {
            var29.println();
        }

        var29.print("Sample standard deviation: ", this.field0);
        var29.print(Fmath.truncate(Stat.standardDeviation(this.data), this.trunc), this.field);
        if (var1) {
            var29.println(Fmath.truncate(Stat.standardDeviation(this.strippedData), this.trunc));
        } else {
            var29.println();
        }

        var29.print("Maximum: ", this.field0);
        var29.print(Fmath.truncate(Fmath.maximum(this.data), this.trunc), this.field);
        if (var1) {
            var29.println(Fmath.truncate(Fmath.maximum(this.strippedData), this.trunc));
        } else {
            var29.println();
        }

        var29.print("Minimum: ", this.field0);
        var29.print(Fmath.truncate(Fmath.minimum(this.data), this.trunc), this.field);
        if (var1) {
            var29.println(Fmath.truncate(Fmath.minimum(this.strippedData), this.trunc));
        } else {
            var29.println();
        }

        var29.println();
        var29.println("Shapiro-Wilk Test:");
        var29.print(" Shapiro-Wilk W value: ", this.field0);
        var29.print(Fmath.truncate(var15, this.trunc), this.field);
        if (var1) {
            var29.println(Fmath.truncate(var22, this.trunc));
        } else {
            var29.println();
        }

        var29.println(" Critical value for W: ");
        var29.print("  (" + this.significance * 100.0D + "% Significance level)", this.field0);
        var29.print(Fmath.truncate(var17, this.trunc), this.field);
        if (var1) {
            var29.println(Fmath.truncate(var24, this.trunc));
        } else {
            var29.println();
        }

        var29.print(" Shapiro-Wilk P value: ", this.field0);
        var29.print(Fmath.truncate(var19, this.trunc), this.field);
        if (var1) {
            var29.println(Fmath.truncate(var26, this.trunc));
        } else {
            var29.println();
        }

        var29.print(" Data possibly Gaussian: ", this.field0);
        var29.print(var21, this.field);
        if (var1) {
            var29.println(var28);
        } else {
            var29.println();
        }

        var29.println();
        var29.println("Probability plot:");
        var29.print(" Correlation coefficient, r: ", this.field0);
        double var42 = (Double)var11.get(2);
        double var33 = 0.0D / 0.0;
        var29.print(Fmath.truncate(var42, this.trunc), this.field);
        if (var1) {
            var33 = (Double)var11.get(6);
            var29.println(Fmath.truncate(var33, this.trunc));
        } else {
            var29.println();
        }

        var29.println(" Critical value for r: ");
        var29.print("  (" + this.significance * 100.0D + "% Significance level)", this.field0);
        double var35 = (Double)var11.get(3);
        double var37 = 0.0D / 0.0;
        var29.print(Fmath.truncate(var35, this.trunc), this.field);
        if (var1) {
            var37 = (Double)var11.get(7);
            var29.println(Fmath.truncate(var37, this.trunc));
        } else {
            var29.println();
        }

        var29.print(" Data possibly Gaussian: ", this.field0);
        String var39 = "Rejected";
        if (var42 >= var35) {
            var39 = "Accepted";
        }

        var29.print(var39, this.field);
        if (var1) {
            var39 = "Rejected";
            if (var33 >= var37) {
                var39 = "Accepted";
            }

            var29.println(var39);
        } else {
            var29.println();
        }

        var29.print(" Gradient: ", this.field0);
        var29.print(Fmath.truncate((Double)var11.get(0), this.trunc), this.field);
        if (var1) {
            var29.println(Fmath.truncate((Double)var11.get(4), this.trunc));
        } else {
            var29.println();
        }

        var29.print(" Intercept: ", this.field0);
        var29.print(Fmath.truncate((Double)var11.get(1), this.trunc), this.field);
        if (var1) {
            var29.println(Fmath.truncate((Double)var11.get(5), this.trunc));
        } else {
            var29.println();
        }

        var29.println();
        var29.println("Input data: ");
        int var40 = 0;

        for(int var41 = 0; var41 < this.nPoints; ++var41) {
            var29.print(this.data[var41] + "  ");
            ++var40;
            if (var40 == 10) {
                var29.println();
                var40 = 0;
            }
        }

        var29.close();
    }

    public double[] strippedData(boolean var1, int var2) {
        int[] var3 = new int[]{var2};
        return this.strippedData(var1, 1, var3);
    }

    public double[] strippedData(boolean var1, int var2, int[] var3) {
        this.strippedData = null;
        if (var1) {
            this.strippedData = new double[this.nPoints - var2];
            int var4 = 0;

            for(int var5 = 0; var5 < this.nPoints; ++var5) {
                boolean var6 = true;

                for(int var7 = 0; var7 < var2; ++var7) {
                    if (var5 == var3[var7]) {
                        var6 = false;
                    }
                }

                if (var6) {
                    this.strippedData[var4] = this.data[var5];
                    ++var4;
                }
            }
        } else {
            this.strippedData = DeepCopy.copy(this.data);
        }

        return this.strippedData;
    }

    public ArrayList<Double> probabilityPlots(boolean var1, double[] var2, double[] var3) {
        ArrayList var4 = new ArrayList();
        ProbabilityPlot var5 = new ProbabilityPlot(var2);
        var5.setStartOfGraphTitle("Original data: ");
        var5.resetSignificance(this.significance);
        var5.suppressFileOutput();
        var5.suppressErrorMessages();
        if (this.suppressDisplay) {
            var5.suppressDisplay();
        }

        var5.gaussianProbabilityPlot();
        var4.add(var5.gaussianGradient());
        var4.add(var5.gaussianIntercept());
        var4.add(var5.gaussianCorrelationCoefficient());
        var4.add(var5.correlationCoefficientCriticalValue());
        this.orderStatisticMedians = var5.gaussianOrderStatisticMedians();
        this.strippedOrderStatisticMedians = this.orderStatisticMedians;
        if (var1) {
            var5 = new ProbabilityPlot(var3);
            var5.setStartOfGraphTitle("Stripped data: ");
            var5.resetSignificance(this.significance);
            var5.suppressFileOutput();
            var5.suppressErrorMessages();
            if (this.suppressDisplay) {
                var5.suppressDisplay();
            }

            var5.gaussianProbabilityPlot();
            var4.add(var5.gaussianGradient());
            var4.add(var5.gaussianIntercept());
            var4.add(var5.gaussianCorrelationCoefficient());
            var4.add(var5.correlationCoefficientCriticalValue());
            this.strippedOrderStatisticMedians = var5.gaussianOrderStatisticMedians();
        }

        return var4;
    }

    public ArrayList<Object> shapiroWilkTest(double[] var1) {
        ArrayList var2 = new ArrayList();
        Normality var3 = new Normality(var1);
        double var4 = var3.shapiroWilkWvalue();
        var2.add(var4);
        double var6 = var3.shapiroWilkCriticalW();
        var2.add(var6);
        double var8 = var3.shapiroWilkPvalue();
        var2.add(var8);
        String var10 = "Rejected";
        if (var4 >= var6) {
            var10 = "Accepted";
        }

        var2.add(var10);
        return var2;
    }
}
