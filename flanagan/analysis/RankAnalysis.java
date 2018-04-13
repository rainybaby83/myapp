//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package flanagan.analysis;

import flanagan.io.FileOutput;
import flanagan.math.ArrayMaths;
import flanagan.math.Conv;
import flanagan.math.Fmath;
import flanagan.math.Matrix;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

public class RankAnalysis {
    private double[][] values = (double[][])null;
    private double[][] errors = (double[][])null;
    private double[] valuesDiagonal = null;
    private double[] errorsDiagonal = null;
    private double[][] reducedValues = (double[][])null;
    private double[][] reducedErrors = (double[][])null;
    private double[] reducedValuesDiagonal = null;
    private double[] reducedErrorsDiagonal = null;
    private double[] reducedValueOverError = null;
    private double[] probabilityValues = null;
    private double[] mcMullen = null;
    private int numberOfRows = 0;
    private int numberOfColumns = 0;
    private int diagonalLength = 0;
    private int errorType = 3;
    private double[] errorRowMeans = null;
    private double[] errorColumnMeans = null;
    private int numberOfMissingErrors = 0;
    private boolean rowOption = true;
    private boolean rankAnalysisDone = false;

    public RankAnalysis(double[][] var1, double[][] var2) {
        this.values = Conv.copy(var1);
        this.errors = Conv.copy(var2);
        this.errorType = 0;
        this.preprocessDataOne();
    }

    public RankAnalysis(float[][] var1, float[][] var2) {
        Matrix var3 = new Matrix(var1);
        this.values = var3.getArrayCopy();
        Matrix var4 = new Matrix(var2);
        this.errors = var4.getArrayCopy();
        this.errorType = 0;
        this.preprocessDataOne();
    }

    public RankAnalysis(long[][] var1, long[][] var2) {
        Matrix var3 = new Matrix(var1);
        this.values = var3.getArrayCopy();
        Matrix var4 = new Matrix(var2);
        this.errors = var4.getArrayCopy();
        this.errorType = 0;
        this.preprocessDataOne();
    }

    public RankAnalysis(int[][] var1, int[][] var2) {
        Matrix var3 = new Matrix(var1);
        this.values = var3.getArrayCopy();
        Matrix var4 = new Matrix(var2);
        this.errors = var4.getArrayCopy();
        this.errorType = 0;
        this.preprocessDataOne();
    }

    public RankAnalysis(BigDecimal[][] var1, BigDecimal[][] var2) {
        Matrix var3 = new Matrix(var1);
        this.values = var3.getArrayCopy();
        Matrix var4 = new Matrix(var2);
        this.errors = var4.getArrayCopy();
        this.errorType = 0;
        this.preprocessDataOne();
    }

    public RankAnalysis(BigInteger[][] var1, BigInteger[][] var2) {
        Matrix var3 = new Matrix(var1);
        this.values = var3.getArrayCopy();
        Matrix var4 = new Matrix(var2);
        this.errors = var4.getArrayCopy();
        this.errorType = 0;
        this.preprocessDataOne();
    }

    public RankAnalysis(ArrayMaths[] var1, ArrayMaths[] var2) {
        Matrix var3 = new Matrix(var1);
        this.values = var3.getArrayCopy();
        Matrix var4 = new Matrix(var2);
        this.errors = var4.getArrayCopy();
        this.errorType = 0;
        this.preprocessDataOne();
    }

    public RankAnalysis(ArrayList<Object>[] var1, ArrayList<Object>[] var2) {
        Matrix var3 = new Matrix(var1);
        this.values = var3.getArrayCopy();
        Matrix var4 = new Matrix(var2);
        this.errors = var4.getArrayCopy();
        this.errorType = 0;
        this.preprocessDataOne();
    }

    public RankAnalysis(Vector<Object>[] var1, Vector<Object>[] var2) {
        Matrix var3 = new Matrix(var1);
        this.values = var3.getArrayCopy();
        Matrix var4 = new Matrix(var2);
        this.errors = var4.getArrayCopy();
        this.errorType = 0;
        this.preprocessDataOne();
    }

    public RankAnalysis(Matrix var1, Matrix var2) {
        this.values = var1.getArrayCopy();
        this.errors = var2.getArrayCopy();
        this.errorType = 0;
        this.preprocessDataOne();
    }

    public RankAnalysis(double[][] var1, double[] var2) {
        this.values = Conv.copy(var1);
        this.errors = this.oneToTwo(Conv.copy(var2), this.values[0].length);
        this.errorType = 1;
        this.preprocessDataOne();
    }

    public RankAnalysis(float[][] var1, float[] var2) {
        Matrix var3 = new Matrix(var1);
        this.values = var3.getArrayCopy();
        ArrayMaths var4 = new ArrayMaths(var2);
        this.errors = this.oneToTwo(var4.array(), this.values[0].length);
        this.errorType = 1;
        this.preprocessDataOne();
    }

    public RankAnalysis(long[][] var1, long[] var2) {
        Matrix var3 = new Matrix(var1);
        this.values = var3.getArrayCopy();
        ArrayMaths var4 = new ArrayMaths(var2);
        this.errors = this.oneToTwo(var4.array(), this.values[0].length);
        this.errorType = 1;
        this.preprocessDataOne();
    }

    public RankAnalysis(int[][] var1, int[] var2) {
        Matrix var3 = new Matrix(var1);
        this.values = var3.getArrayCopy();
        ArrayMaths var4 = new ArrayMaths(var2);
        this.errors = this.oneToTwo(var4.array(), this.values[0].length);
        this.errorType = 1;
        this.preprocessDataOne();
    }

    public RankAnalysis(BigDecimal[][] var1, BigDecimal[] var2) {
        Matrix var3 = new Matrix(var1);
        this.values = var3.getArrayCopy();
        ArrayMaths var4 = new ArrayMaths(var2);
        this.errors = this.oneToTwo(var4.array(), this.values[0].length);
        this.errorType = 1;
        this.preprocessDataOne();
    }

    public RankAnalysis(BigInteger[][] var1, BigInteger[] var2) {
        Matrix var3 = new Matrix(var1);
        this.values = var3.getArrayCopy();
        ArrayMaths var4 = new ArrayMaths(var2);
        this.errors = this.oneToTwo(var4.array(), this.values[0].length);
        this.errorType = 1;
        this.preprocessDataOne();
    }

    public RankAnalysis(ArrayMaths[] var1, ArrayMaths var2) {
        Matrix var3 = new Matrix(var1);
        this.values = var3.getArrayCopy();
        this.errors = this.oneToTwo(var2.array(), this.values[0].length);
        this.errorType = 1;
        this.preprocessDataOne();
    }

    public RankAnalysis(ArrayList<Object>[] var1, ArrayList<Object> var2) {
        Matrix var3 = new Matrix(var1);
        this.values = var3.getArrayCopy();
        ArrayMaths var4 = new ArrayMaths(var2);
        this.errors = this.oneToTwo(var4.array(), this.values[0].length);
        this.errorType = 1;
        this.preprocessDataOne();
    }

    public RankAnalysis(Vector<Object>[] var1, Vector<Object> var2) {
        Matrix var3 = new Matrix(var1);
        this.values = var3.getArrayCopy();
        ArrayMaths var4 = new ArrayMaths(var2);
        this.errors = this.oneToTwo(var4.array(), this.values[0].length);
        this.errorType = 1;
        this.preprocessDataOne();
    }

    public RankAnalysis(Scores var1) {
        this.values = var1.usedScoresAsRowPerItem();
        Matrix var2 = new Matrix(this.values);
        double[] var3 = var2.rowStandardDeviations();
        ArrayMaths var4 = new ArrayMaths(var3);
        this.errors = this.oneToTwo(var4.array(), this.values[0].length);
        this.errorType = 1;
        this.preprocessDataOne();
    }

    public RankAnalysis(Cronbach var1) {
        this.values = var1.usedScoresAsRowPerItem();
        Matrix var2 = new Matrix(this.values);
        double[] var3 = var2.rowStandardDeviations();
        ArrayMaths var4 = new ArrayMaths(var3);
        this.errors = this.oneToTwo(var4.array(), this.values[0].length);
        this.errorType = 1;
        this.preprocessDataOne();
    }

    public RankAnalysis(PCA var1) {
        this.values = var1.usedScoresAsRowPerItem();
        Matrix var2 = new Matrix(this.values);
        double[] var3 = var2.rowStandardDeviations();
        ArrayMaths var4 = new ArrayMaths(var3);
        this.errors = this.oneToTwo(var4.array(), this.values[0].length);
        this.errorType = 1;
        this.preprocessDataOne();
    }

    public RankAnalysis(double[][] var1, double var2) {
        this.values = Conv.copy(var1);
        this.errorType = 2;
        this.preprocessDataTwo(var2);
    }

    public RankAnalysis(float[][] var1, float var2) {
        Matrix var3 = new Matrix(var1);
        this.values = var3.getArrayCopy();
        this.errorType = 2;
        this.preprocessDataTwo((double)var2);
    }

    public RankAnalysis(long[][] var1, long var2) {
        Matrix var4 = new Matrix(var1);
        this.values = var4.getArrayCopy();
        this.errorType = 2;
        this.preprocessDataTwo((double)var2);
    }

    public RankAnalysis(int[][] var1, int var2) {
        Matrix var3 = new Matrix(var1);
        this.values = var3.getArrayCopy();
        this.errorType = 2;
        this.preprocessDataTwo((double)var2);
    }

    public RankAnalysis(BigDecimal[][] var1, BigDecimal var2) {
        Matrix var3 = new Matrix(var1);
        this.values = var3.getArrayCopy();
        this.errorType = 2;
        this.preprocessDataTwo(var2.doubleValue());
    }

    public RankAnalysis(BigInteger[][] var1, BigInteger var2) {
        Matrix var3 = new Matrix(var1);
        this.values = var3.getArrayCopy();
        this.errorType = 2;
        this.preprocessDataTwo(var2.doubleValue());
    }

    public RankAnalysis(ArrayMaths[] var1, double var2) {
        Matrix var4 = new Matrix(var1);
        this.values = var4.getArrayCopy();
        this.errorType = 2;
        this.preprocessDataTwo(var2);
    }

    public RankAnalysis(ArrayList<Object>[] var1, double var2) {
        Matrix var4 = new Matrix(var1);
        this.values = var4.getArrayCopy();
        this.errorType = 2;
        this.preprocessDataTwo(var2);
    }

    public RankAnalysis(Vector<Object>[] var1, double var2) {
        Matrix var4 = new Matrix(var1);
        this.values = var4.getArrayCopy();
        this.errorType = 2;
        this.preprocessDataTwo(var2);
    }

    public RankAnalysis(Matrix var1, double var2) {
        this.values = var1.getArrayCopy();
        this.errorType = 2;
        this.preprocessDataTwo(var2);
    }

    public RankAnalysis(double[][] var1) {
        this.values = Conv.copy(var1);
        this.errorType = 3;
        this.preprocessDataThree();
    }

    public RankAnalysis(float[][] var1) {
        Matrix var2 = new Matrix(var1);
        this.values = var2.getArrayCopy();
        this.errorType = 3;
        this.preprocessDataThree();
    }

    public RankAnalysis(long[][] var1) {
        Matrix var2 = new Matrix(var1);
        this.values = var2.getArrayCopy();
        this.errorType = 3;
        this.preprocessDataThree();
    }

    public RankAnalysis(int[][] var1) {
        Matrix var2 = new Matrix(var1);
        this.values = var2.getArrayCopy();
        this.errorType = 3;
        this.preprocessDataThree();
    }

    public RankAnalysis(BigDecimal[][] var1) {
        Matrix var2 = new Matrix(var1);
        this.values = var2.getArrayCopy();
        this.errorType = 3;
        this.preprocessDataThree();
    }

    public RankAnalysis(BigInteger[][] var1) {
        Matrix var2 = new Matrix(var1);
        this.values = var2.getArrayCopy();
        this.errorType = 3;
        this.preprocessDataThree();
    }

    public RankAnalysis(ArrayMaths[] var1) {
        Matrix var2 = new Matrix(var1);
        this.values = var2.getArrayCopy();
        this.errorType = 3;
        this.preprocessDataThree();
    }

    public RankAnalysis(ArrayList<Object>[] var1) {
        Matrix var2 = new Matrix(var1);
        this.values = var2.getArrayCopy();
        this.errorType = 3;
        this.preprocessDataThree();
    }

    public RankAnalysis(Vector<Object>[] var1) {
        Matrix var2 = new Matrix(var1);
        this.values = var2.getArrayCopy();
        this.errorType = 3;
        this.preprocessDataThree();
    }

    public RankAnalysis(Matrix var1) {
        this.values = var1.getArrayCopy();
        this.errorType = 3;
        this.preprocessDataThree();
    }

    private double[][] oneToTwo(double[] var1, int var2) {
        int var3 = var1.length;
        double[][] var4 = new double[var3][var2];

        for(int var5 = 0; var5 < var3; ++var5) {
            for(int var6 = 0; var6 < var2; ++var6) {
                var4[var5][var6] = var1[var5];
            }
        }

        return var4;
    }

    private void preprocessDataOne() {
        this.numberOfRows = this.values.length;
        this.numberOfColumns = this.values[0].length;

        int var1;
        for(var1 = 1; var1 < this.numberOfRows; ++var1) {
            if (this.values[var1].length != this.numberOfColumns) {
                throw new IllegalArgumentException("All rows of the value matrix must be of the same length");
            }
        }

        for(var1 = 0; var1 < this.numberOfRows; ++var1) {
            if (this.errors[var1].length != this.numberOfColumns) {
                throw new IllegalArgumentException("All rows of the error matrix must be of the same length as those of the value matrix");
            }
        }

        this.diagonalLength = this.numberOfRows;
        if (this.numberOfRows > this.numberOfColumns) {
            this.diagonalLength = this.numberOfColumns;
        }

        for(var1 = 0; var1 < this.numberOfRows; ++var1) {
            for(int var2 = 0; var2 < this.numberOfColumns; ++var2) {
                this.errors[var1][var2] *= this.errors[var1][var2];
            }
        }

    }

    private void preprocessDataTwo(double var1) {
        this.numberOfRows = this.values.length;
        this.numberOfColumns = this.values[0].length;

        int var3;
        for(var3 = 1; var3 < this.numberOfRows; ++var3) {
            if (this.values[var3].length != this.numberOfColumns) {
                throw new IllegalArgumentException("All rows of the value matrix must be of the same length");
            }
        }

        this.diagonalLength = this.numberOfRows;
        if (this.numberOfRows > this.numberOfColumns) {
            this.diagonalLength = this.numberOfColumns;
        }

        this.errors = new double[this.numberOfRows][this.numberOfColumns];

        for(var3 = 0; var3 < this.numberOfRows; ++var3) {
            for(int var4 = 0; var4 < this.numberOfColumns; ++var4) {
                this.errors[var3][var4] = var1 * var1;
            }
        }

    }

    private void preprocessDataThree() {
        this.numberOfRows = this.values.length;
        this.numberOfColumns = this.values[0].length;

        for(int var1 = 1; var1 < this.numberOfRows; ++var1) {
            if (this.values[var1].length != this.numberOfColumns) {
                throw new IllegalArgumentException("All rows of the value matrix must be of the same length");
            }
        }

        this.diagonalLength = this.numberOfRows;
        if (this.numberOfRows > this.numberOfColumns) {
            this.diagonalLength = this.numberOfColumns;
        }

        this.errors = new double[this.numberOfRows][this.numberOfColumns];
        double var5 = 0.0D;

        for(int var3 = 0; var3 < this.numberOfRows; ++var3) {
            for(int var4 = 0; var4 < this.numberOfColumns; ++var4) {
                var5 = Math.pow(10.0D, Math.floor(Math.log10(Math.abs(this.values[var3][var4])))) * 5.0E-16D;
                this.errors[var3][var4] = var5 * var5;
            }
        }

    }

    public void useErrorRowMean() {
        this.rowOption = true;
    }

    public void useErrorColumnMean() {
        this.rowOption = false;
    }

    public int nMissingErrors() {
        return this.numberOfMissingErrors;
    }

    private void rankAnalysis() {
        this.errorRowMeans = new double[this.numberOfRows];
        this.errorColumnMeans = new double[this.numberOfColumns];
        this.numberOfMissingErrors = 0;

        int var1;
        int var2;
        int var3;
        for(var1 = 0; var1 < this.numberOfRows; ++var1) {
            var2 = 0;

            for(var3 = 0; var3 < this.numberOfColumns; ++var3) {
                if (!Double.isNaN(this.errors[var1][var3])) {
                    if (this.errors[var1][var3] < 0.0D) {
                        this.errors[var1][var3] *= -1.0D;
                    }

                    this.errorRowMeans[var1] += this.errors[var1][var3];
                    ++var2;
                } else {
                    ++this.numberOfMissingErrors;
                }
            }

            this.errorRowMeans[var1] /= (double)var2;
        }

        for(var1 = 0; var1 < this.numberOfColumns; ++var1) {
            var2 = 0;

            for(var3 = 0; var3 < this.numberOfRows; ++var3) {
                if (!Double.isNaN(this.errors[var3][var1])) {
                    this.errorColumnMeans[var1] += this.errors[var3][var1];
                    ++var2;
                }
            }

            this.errorColumnMeans[var1] /= (double)var2;
        }

        if (this.numberOfMissingErrors > 0) {
            for(var1 = 0; var1 < this.numberOfRows; ++var1) {
                for(var2 = 0; var2 < this.numberOfColumns; ++var2) {
                    if (Double.isNaN(this.errors[var1][var2])) {
                        if (this.rowOption) {
                            this.errors[var1][var2] = this.errorRowMeans[var1];
                        } else {
                            this.errors[var1][var2] = this.errorColumnMeans[var1];
                        }
                    }
                }
            }
        }

        this.reducedValues = this.values;
        this.reducedErrors = this.errors;
        new Matrix(this.reducedValues);
        new Matrix(this.reducedErrors);
        var3 = this.diagonalLength - 1;

        int var4;
        for(var4 = 0; var4 < var3; ++var4) {
            Matrix var30 = new Matrix(this.reducedValues);
            int var10000 = this.numberOfRows - var4;
            var10000 = this.numberOfColumns - var4;
            Matrix var7 = var30.getSubMatrix(var4, var4, this.numberOfRows - 1, this.numberOfColumns - 1);
            double[][] var8 = var7.getArrayCopy();
            int[] var9 = var7.pivot();
            int var10 = var9[0] + var4;
            int var11 = var9[1] + var4;
            double[] var12 = this.reducedValues[var4];
            double[] var13 = this.reducedErrors[var4];
            this.reducedValues[var4] = this.reducedValues[var10];
            this.reducedErrors[var4] = this.reducedErrors[var10];
            this.reducedValues[var10] = var12;
            this.reducedErrors[var10] = var13;
            double var14 = 0.0D;
            double var16 = 0.0D;

            for(int var18 = 0; var18 < this.numberOfRows; ++var18) {
                var14 = this.reducedValues[var18][var4];
                var16 = this.reducedErrors[var18][var4];
                this.reducedValues[var18][var4] = this.reducedValues[var18][var11];
                this.reducedErrors[var18][var4] = this.reducedErrors[var18][var11];
                this.reducedValues[var18][var11] = var14;
                this.reducedErrors[var18][var11] = var16;
            }

            Matrix var33 = new Matrix(this.reducedValues);
            Matrix var19 = new Matrix(this.reducedErrors);
            double[][] var20 = var33.getArrayCopy();
            double[][] var21 = var19.getArrayCopy();

            for(int var22 = var4 + 1; var22 < this.numberOfRows; ++var22) {
                for(int var23 = var4; var23 < this.numberOfColumns; ++var23) {
                    double var24 = 1.0D;
                    if (this.reducedValues[var22][var4] != this.reducedValues[var4][var4]) {
                        var24 = this.reducedValues[var22][var4] / this.reducedValues[var4][var4];
                    }

                    var20[var22][var23] = this.reducedValues[var22][var23] - var24 * this.reducedValues[var4][var23];
                    double var26 = this.reducedErrors[var22][var23] + this.reducedErrors[var4][var23] * var24 * var24;
                    double var28 = 1.0D;
                    if (this.reducedValues[var4][var23] != this.reducedValues[var4][var4]) {
                        var28 = this.reducedValues[var4][var23] / this.reducedValues[var4][var4];
                    }

                    var26 += this.reducedErrors[var22][var4] * var28 * var28;
                    var21[var22][var23] = var26 + this.reducedErrors[var4][var4] * var24 * var24 * var28 * var28;
                }
            }

            var33 = new Matrix(var20);
            var19 = new Matrix(var21);
            this.reducedValues = var33.getArrayCopy();
            this.reducedErrors = var19.getArrayCopy();
        }

        int var5;
        for(var4 = 0; var4 < this.numberOfRows; ++var4) {
            for(var5 = 0; var5 < this.numberOfColumns; ++var5) {
                this.reducedErrors[var4][var5] = Math.sqrt(this.reducedErrors[var4][var5]);
            }
        }

        for(var4 = 1; var4 < this.diagonalLength; ++var4) {
            for(var5 = 0; var5 < var4; ++var5) {
                this.reducedValues[var4][var5] = 0.0D;
                this.reducedErrors[var4][var5] = 0.0D;
            }
        }

        if (this.diagonalLength < this.numberOfRows) {
            for(var4 = this.diagonalLength; var4 < this.numberOfRows; ++var4) {
                for(var5 = 0; var5 < this.numberOfColumns; ++var5) {
                    this.reducedValues[var4][var5] = 0.0D;
                    this.reducedErrors[var4][var5] = 0.0D;
                }
            }
        }

        this.reducedValuesDiagonal = new double[this.diagonalLength];
        this.reducedErrorsDiagonal = new double[this.diagonalLength];
        this.reducedValueOverError = new double[this.diagonalLength];
        this.probabilityValues = new double[this.diagonalLength];
        this.mcMullen = new double[this.numberOfRows];

        for(var4 = 0; var4 < this.diagonalLength; ++var4) {
            this.reducedValuesDiagonal[var4] = this.reducedValues[var4][var4];
            this.reducedErrorsDiagonal[var4] = this.reducedErrors[var4][var4];
            this.reducedValueOverError[var4] = Math.abs(this.reducedValuesDiagonal[var4] / this.reducedErrorsDiagonal[var4]);
            this.probabilityValues[var4] = 1.0D - Stat.gaussianCDF(0.0D, 1.0D, -this.reducedValueOverError[var4], this.reducedValueOverError[var4]);
        }

        for(var4 = 0; var4 < this.numberOfRows; ++var4) {
            double var31 = 0.0D;

            for(int var32 = var4; var32 < this.numberOfColumns; ++var32) {
                var31 += this.reducedValues[var4][var32] * this.reducedValues[var4][var32];
            }

            this.mcMullen[var4] = Math.sqrt(var31) / (double)(this.numberOfColumns - var4);
        }

        this.rankAnalysisDone = true;
    }

    public void analysis() {
        this.analysis("RankAnalysisOutput.txt");
    }

    public void analysis(String var1) {
        if (!this.rankAnalysisDone) {
            this.rankAnalysis();
        }

        int var2 = var1.indexOf(".");
        if (var2 == -1) {
            var1 = var1 + ".txt";
        }

        FileOutput var3 = new FileOutput(var1);
        var3.println("Rank Analysis");
        var3.println("File name:   " + var1);
        Date var4 = new Date();
        String var5 = DateFormat.getDateInstance().format(var4);
        String var6 = DateFormat.getTimeInstance().format(var4);
        var3.println("Program executed at " + var6 + " on " + var5);
        var3.println();
        var3.println("Number of rows    " + this.numberOfRows);
        var3.println("Number of columns " + this.numberOfColumns);
        if (this.numberOfMissingErrors > 0) {
            var3.println("Number of substituted missing errors" + this.numberOfMissingErrors);
            if (this.rowOption) {
                var3.println("Row means used as the substituted value/s");
            } else {
                var3.println("Column means used as the substituted value/s");
            }
        }

        var3.println();
        switch(this.errorType) {
            case 0:
                var3.println("Matrix of individual errors supplied");
                break;
            case 1:
                var3.println("Common error for all elements in each each row supplied");
                break;
            case 2:
                var3.println("Single common error for all elements in the matrix supplied");
                break;
            case 3:
                var3.println("No errors supplied - estimate of the rounding errors used");
        }

        var3.println();
        boolean var7 = true;
        byte var8 = 15;
        byte var9 = 4;
        if (this.errorType != 3) {
            var3.print("Reduced", var8);
            var3.print("Reduced", var8);
            var3.print("V/E Ratio", var8);
            var3.print("P-value", var8);
            var3.println("McMullen");
            var3.print("Value", var8);
            var3.print("Error", var8);
            var3.print("    ", var8);
            var3.print("    ", var8);
            var3.println("rms");
            var3.print("Diagonal (V)", var8);
            var3.print("Diagonal (E)", var8);
            var3.print("   ", var8);
            var3.print("   ", var8);
            var3.println("   ");
        } else {
            var3.print("Reduced", var8);
            var3.print("Reduced", var8);
            var3.print("V/E Ratio", var8);
            var3.print("P-value", var8);
            var3.println("McMullen");
            var3.print("Value", var8);
            var3.print("Estimated", var8);
            var3.print("    ", var8);
            var3.print("    ", var8);
            var3.println("rms");
            var3.print("Diagonal (V)", var8);
            var3.print("Rounding", var8);
            var3.print("   ", var8);
            var3.print("   ", var8);
            var3.println("   ");
            var3.print("   ", var8);
            var3.print("Error (E)", var8);
            var3.print("   ", var8);
            var3.print("   ", var8);
            var3.println("   ");
        }

        for(int var10 = 0; var10 < this.diagonalLength; ++var10) {
            var3.print(Fmath.truncate(this.reducedValuesDiagonal[var10], var9), var8);
            var3.print(Fmath.truncate(this.reducedErrorsDiagonal[var10], var9), var8);
            var3.print(Fmath.truncate(this.reducedValueOverError[var10], var9), var8);
            var3.print(Fmath.truncate(this.probabilityValues[var10], var9), var8);
            var3.println(Fmath.truncate(this.mcMullen[var10], var9));
        }

        System.out.println("Analysis written to text file " + var1);
        var3.close();
    }

    public double[][] originalValues() {
        if (!this.rankAnalysisDone) {
            this.rankAnalysis();
        }

        return this.values;
    }

    public double[][] originalErrors() {
        if (!this.rankAnalysisDone) {
            this.rankAnalysis();
        }

        return this.errors;
    }

    public double[][] reducedValues() {
        if (!this.rankAnalysisDone) {
            this.rankAnalysis();
        }

        return this.reducedValues;
    }

    public double[][] reducedErrors() {
        if (!this.rankAnalysisDone) {
            this.rankAnalysis();
        }

        return this.reducedErrors;
    }

    public double[] reducedValuesDiagonal() {
        if (!this.rankAnalysisDone) {
            this.rankAnalysis();
        }

        return this.reducedValuesDiagonal;
    }

    public double[] reducedErrorsDiagonal() {
        if (!this.rankAnalysisDone) {
            this.rankAnalysis();
        }

        return this.reducedErrorsDiagonal;
    }

    public double[] reducedRatiosDiagonal() {
        if (!this.rankAnalysisDone) {
            this.rankAnalysis();
        }

        return this.reducedValueOverError;
    }

    public double[] probabilityValues() {
        if (!this.rankAnalysisDone) {
            this.rankAnalysis();
        }

        return this.probabilityValues;
    }

    public double[] mcMullenValues() {
        if (!this.rankAnalysisDone) {
            this.rankAnalysis();
        }

        return this.mcMullen;
    }

    public int nRows() {
        if (!this.rankAnalysisDone) {
            this.rankAnalysis();
        }

        return this.numberOfRows;
    }

    public int nColumns() {
        if (!this.rankAnalysisDone) {
            this.rankAnalysis();
        }

        return this.numberOfColumns;
    }

    public int nDiagonalElements() {
        if (!this.rankAnalysisDone) {
            this.rankAnalysis();
        }

        return this.diagonalLength;
    }
}
