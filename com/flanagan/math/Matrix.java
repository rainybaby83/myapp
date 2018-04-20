//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.math;

import com.flanagan.analysis.Regression;
import com.flanagan.analysis.Stat;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Vector;

public class Matrix {
    private int numberOfRows = 0;
    private int numberOfColumns = 0;
    private double[][] matrix = (double[][])null;
    private String[][] matrixS = (String[][])null;
    private int entryType = -1;
    private boolean numericalCheck = true;
    private double[][] hessenberg = (double[][])null;
    private boolean hessenbergDone = false;
    private int[] permutationIndex = null;
    private double rowSwapIndex = 1.0D;
    private double[] eigenValues = null;
    private double[][] eigenVector = (double[][])null;
    private double[] sortedEigenValues = null;
    private double[][] sortedEigenVector = (double[][])null;
    private int numberOfRotations = 0;
    private int[] eigenIndices = null;
    private int maximumJacobiIterations = 100;
    private boolean eigenDone = false;
    private boolean matrixCheck = true;
    private boolean suppressErrorMessage = false;
    private double tiny = 1.0E-100D;

    public Matrix(int var1, int var2) {
        this.numberOfRows = var1;
        this.numberOfColumns = var2;
        this.matrix = new double[this.numberOfRows][this.numberOfColumns];
        this.matrixS = new String[this.numberOfRows][this.numberOfColumns];
        this.permutationIndex = new int[this.numberOfRows];
        this.entryType = 0;

        for(int var3 = 0; var3 < this.numberOfRows; ++var3) {
            this.permutationIndex[var3] = var3;

            for(int var4 = 0; var4 < this.numberOfColumns; ++var4) {
                this.matrixS[var3][var4] = "0.0";
            }
        }

        this.eigenDone = false;
        this.hessenbergDone = false;
    }

    public Matrix(int var1, int var2, double var3) {
        this.numberOfRows = var1;
        this.numberOfColumns = var2;
        this.matrix = new double[this.numberOfRows][this.numberOfColumns];
        this.matrixS = new String[this.numberOfRows][this.numberOfColumns];
        this.permutationIndex = new int[this.numberOfRows];
        this.entryType = 0;

        for(int var5 = 0; var5 < this.numberOfRows; ++var5) {
            this.permutationIndex[var5] = var5;

            for(int var6 = 0; var6 < this.numberOfColumns; ++var6) {
                this.matrix[var5][var6] = var3;
                this.matrixS[var5][var6] = Conv.convert_double_to_String(var3);
            }
        }

        this.eigenDone = false;
        this.hessenbergDone = false;
    }

    public Matrix(double[][] var1) {
        this.numberOfRows = var1.length;
        this.numberOfColumns = var1[0].length;
        this.matrix = new double[this.numberOfRows][this.numberOfColumns];
        this.matrixS = new String[this.numberOfRows][this.numberOfColumns];
        this.permutationIndex = new int[this.numberOfRows];
        this.entryType = 0;

        for(int var2 = 0; var2 < this.numberOfRows; ++var2) {
            this.permutationIndex[var2] = var2;

            for(int var3 = 0; var3 < this.numberOfColumns; ++var3) {
                this.matrix[var2][var3] = var1[var2][var3];
                this.matrixS[var2][var3] = Conv.convert_double_to_String(var1[var2][var3]);
            }
        }

        this.eigenDone = false;
        this.hessenbergDone = false;
    }

    public Matrix(float[][] var1) {
        this.numberOfRows = var1.length;
        this.numberOfColumns = var1[0].length;

        int var2;
        for(var2 = 1; var2 < this.numberOfRows; ++var2) {
            if (var1[var2].length != this.numberOfColumns) {
                throw new IllegalArgumentException("All rows must have the same length");
            }
        }

        this.matrix = new double[this.numberOfRows][this.numberOfColumns];
        this.matrixS = new String[this.numberOfRows][this.numberOfColumns];
        this.permutationIndex = new int[this.numberOfRows];
        this.entryType = 1;

        for(var2 = 0; var2 < this.numberOfRows; ++var2) {
            this.permutationIndex[var2] = var2;

            for(int var3 = 0; var3 < this.numberOfColumns; ++var3) {
                this.matrix[var2][var3] = (double)var1[var2][var3];
                this.matrixS[var2][var3] = Conv.convert_float_to_String(var1[var2][var3]);
            }
        }

        this.eigenDone = false;
        this.hessenbergDone = false;
    }

    public Matrix(long[][] var1) {
        this.numberOfRows = var1.length;
        this.numberOfColumns = var1[0].length;

        int var2;
        for(var2 = 1; var2 < this.numberOfRows; ++var2) {
            if (var1[var2].length != this.numberOfColumns) {
                throw new IllegalArgumentException("All rows must have the same length");
            }
        }

        this.matrix = new double[this.numberOfRows][this.numberOfColumns];
        this.matrixS = new String[this.numberOfRows][this.numberOfColumns];
        this.permutationIndex = new int[this.numberOfRows];
        this.entryType = 2;

        for(var2 = 0; var2 < this.numberOfRows; ++var2) {
            this.permutationIndex[var2] = var2;

            for(int var3 = 0; var3 < this.numberOfColumns; ++var3) {
                this.matrix[var2][var3] = (double)var1[var2][var3];
                this.matrixS[var2][var3] = Conv.convert_long_to_String(var1[var2][var3]);
            }
        }

        this.eigenDone = false;
        this.hessenbergDone = false;
    }

    public Matrix(int[][] var1) {
        this.numberOfRows = var1.length;
        this.numberOfColumns = var1[0].length;

        int var2;
        for(var2 = 1; var2 < this.numberOfRows; ++var2) {
            if (var1[var2].length != this.numberOfColumns) {
                throw new IllegalArgumentException("All rows must have the same length");
            }
        }

        this.matrix = new double[this.numberOfRows][this.numberOfColumns];
        this.matrixS = new String[this.numberOfRows][this.numberOfColumns];
        this.permutationIndex = new int[this.numberOfRows];
        this.entryType = 3;

        for(var2 = 0; var2 < this.numberOfRows; ++var2) {
            this.permutationIndex[var2] = var2;

            for(int var3 = 0; var3 < this.numberOfColumns; ++var3) {
                this.matrix[var2][var3] = (double)var1[var2][var3];
                this.matrixS[var2][var3] = Conv.convert_long_to_String((long)var1[var2][var3]);
            }
        }

        this.eigenDone = false;
        this.hessenbergDone = false;
    }

    public Matrix(char[][] var1) {
        this.numberOfRows = var1.length;
        this.numberOfColumns = var1[0].length;

        int var2;
        for(var2 = 1; var2 < this.numberOfRows; ++var2) {
            if (var1[var2].length != this.numberOfColumns) {
                throw new IllegalArgumentException("All rows must have the same length");
            }
        }

        this.matrix = new double[this.numberOfRows][this.numberOfColumns];
        this.matrixS = new String[this.numberOfRows][this.numberOfColumns];
        this.permutationIndex = new int[this.numberOfRows];
        this.entryType = 3;

        for(var2 = 0; var2 < this.numberOfRows; ++var2) {
            this.permutationIndex[var2] = var2;

            for(int var3 = 0; var3 < this.numberOfColumns; ++var3) {
                this.matrix[var2][var3] = Conv.convert_char_to_double(var1[var2][var3]);
                this.matrixS[var2][var3] = Conv.convert_char_to_String(var1[var2][var3]);
            }
        }

        this.eigenDone = false;
        this.hessenbergDone = false;
    }

    public Matrix(String[][] var1) {
        this.numberOfRows = var1.length;
        this.numberOfColumns = var1[0].length;

        int var2;
        for(var2 = 1; var2 < this.numberOfRows; ++var2) {
            if (var1[var2].length != this.numberOfColumns) {
                throw new IllegalArgumentException("All rows must have the same length");
            }
        }

        this.matrix = new double[this.numberOfRows][this.numberOfColumns];
        this.matrixS = new String[this.numberOfRows][this.numberOfColumns];
        this.permutationIndex = new int[this.numberOfRows];
        this.entryType = 3;
        this.numericalCheck = false;

        int var3;
        for(var2 = 0; var2 < this.numberOfRows; ++var2) {
            this.permutationIndex[var2] = var2;

            for(var3 = 0; var3 < this.numberOfColumns; ++var3) {
                this.matrixS[var2][var3] = var1[var2][var3];
            }
        }

        for(var2 = 0; var2 < this.numberOfRows; ++var2) {
            for(var3 = 0; var3 < this.numberOfColumns; ++var3) {
                try {
                    this.matrix[var2][var3] = Conv.convert_String_to_double(var1[var2][var3]);
                } catch (NumberFormatException var5) {
                    System.out.println(var5);
                    System.out.println("The entered String array does not contain numerical values - none of the numerical methods in this class are available.");
                    this.numericalCheck = false;
                    break;
                }
            }

            if (!this.numericalCheck) {
                break;
            }
        }

        this.eigenDone = false;
        this.hessenbergDone = false;
    }

    public Matrix(ArrayMaths[] var1) {
        this.numberOfRows = var1.length;
        this.numberOfColumns = var1[0].length();
        this.matrix = new double[this.numberOfRows][this.numberOfColumns];
        this.matrixS = new String[this.numberOfRows][this.numberOfColumns];

        int var2;
        for(var2 = 0; var2 < this.numberOfRows; ++var2) {
            double[] var3 = var1[var2].copy().array();
            if (var3.length != this.numberOfColumns) {
                throw new IllegalArgumentException("All rows must have the same length");
            }

            String[] var4 = var1[var2].copy().array_as_String();
            this.matrix[var2] = var3;
            this.matrixS[var2] = var4;
        }

        this.permutationIndex = new int[this.numberOfRows];

        for(var2 = 0; var2 < this.numberOfRows; this.permutationIndex[var2] = var2++) {
            ;
        }

        this.eigenDone = false;
        this.hessenbergDone = false;
    }

    public Matrix(ArrayList<Object>[] var1) {
        this.numberOfRows = var1.length;
        ArrayMaths[] var2 = new ArrayMaths[this.numberOfRows];

        int var3;
        for(var3 = 0; var3 < this.numberOfRows; ++var3) {
            var2[var3] = new ArrayMaths(var1[var3]);
        }

        this.numberOfColumns = var2[0].length();
        this.matrix = new double[this.numberOfRows][this.numberOfColumns];
        this.matrixS = new String[this.numberOfRows][this.numberOfColumns];

        for(var3 = 0; var3 < this.numberOfRows; ++var3) {
            double[] var4 = var2[var3].copy().array();
            if (var4.length != this.numberOfColumns) {
                throw new IllegalArgumentException("All rows must have the same length");
            }

            this.matrix[var3] = var4;
        }

        this.permutationIndex = new int[this.numberOfRows];

        for(var3 = 0; var3 < this.numberOfRows; this.permutationIndex[var3] = var3++) {
            ;
        }

        this.eigenDone = false;
        this.hessenbergDone = false;
    }

    public Matrix(Vector<Object>[] var1) {
        this.numberOfRows = var1.length;
        ArrayMaths[] var2 = new ArrayMaths[this.numberOfRows];

        int var3;
        for(var3 = 0; var3 < this.numberOfRows; ++var3) {
            var2[var3] = new ArrayMaths(var1[var3]);
        }

        this.numberOfColumns = var2[0].length();
        this.matrix = new double[this.numberOfRows][this.numberOfColumns];
        this.matrixS = new String[this.numberOfRows][this.numberOfColumns];

        for(var3 = 0; var3 < this.numberOfRows; ++var3) {
            double[] var4 = var2[var3].copy().array();
            if (var4.length != this.numberOfColumns) {
                throw new IllegalArgumentException("All rows must have the same length");
            }

            this.matrix[var3] = var4;
        }

        this.permutationIndex = new int[this.numberOfRows];

        for(var3 = 0; var3 < this.numberOfRows; this.permutationIndex[var3] = var3++) {
            ;
        }

        this.eigenDone = false;
        this.hessenbergDone = false;
    }

    public Matrix(BigDecimal[][] var1) {
        this.numberOfRows = var1.length;
        this.numberOfColumns = var1[0].length;

        int var2;
        for(var2 = 1; var2 < this.numberOfRows; ++var2) {
            if (var1[var2].length != this.numberOfColumns) {
                throw new IllegalArgumentException("All rows must have the same length");
            }
        }

        this.matrix = new double[this.numberOfRows][this.numberOfColumns];
        this.matrixS = new String[this.numberOfRows][this.numberOfColumns];
        this.permutationIndex = new int[this.numberOfRows];
        this.entryType = 4;

        for(var2 = 0; var2 < this.numberOfRows; ++var2) {
            this.permutationIndex[var2] = var2;

            for(int var3 = 0; var3 < this.numberOfColumns; ++var3) {
                this.matrix[var2][var3] = var1[var2][var3].doubleValue();
                this.matrixS[var2][var3] = Conv.convert_BigDecimal_to_String(var1[var2][var3]);
            }
        }

        this.eigenDone = false;
        this.hessenbergDone = false;
    }

    public Matrix(BigInteger[][] var1) {
        this.numberOfRows = var1.length;
        this.numberOfColumns = var1[0].length;

        int var2;
        for(var2 = 1; var2 < this.numberOfRows; ++var2) {
            if (var1[var2].length != this.numberOfColumns) {
                throw new IllegalArgumentException("All rows must have the same length");
            }
        }

        this.matrix = new double[this.numberOfRows][this.numberOfColumns];
        this.matrixS = new String[this.numberOfRows][this.numberOfColumns];
        this.entryType = 5;
        this.permutationIndex = new int[this.numberOfRows];

        for(var2 = 0; var2 < this.numberOfRows; ++var2) {
            this.permutationIndex[var2] = var2;

            for(int var3 = 0; var3 < this.numberOfColumns; ++var3) {
                this.matrix[var2][var3] = var1[var2][var3].doubleValue();
                this.matrixS[var2][var3] = Conv.convert_BigInteger_to_String(var1[var2][var3]);
            }
        }

        this.eigenDone = false;
        this.hessenbergDone = false;
    }

    public Matrix(Matrix var1) {
        this.numberOfRows = var1.numberOfRows;
        this.numberOfColumns = var1.numberOfColumns;
        this.matrix = new double[this.numberOfRows][this.numberOfColumns];
        this.matrixS = new String[this.numberOfRows][this.numberOfColumns];
        this.entryType = var1.getEntryType();

        for(int var2 = 0; var2 < this.numberOfRows; ++var2) {
            for(int var3 = 0; var3 < this.numberOfColumns; ++var3) {
                this.matrix[var2][var3] = var1.matrix[var2][var3];
                this.matrixS[var2][var3] = var1.matrixS[var2][var3];
            }
        }

        this.permutationIndex = Conv.copy(var1.permutationIndex);
        this.rowSwapIndex = var1.rowSwapIndex;
        this.eigenDone = false;
        this.hessenbergDone = false;
    }

    private int getEntryType() {
        return this.entryType;
    }

    public void resetLUzero(double var1) {
        this.tiny = var1;
    }

    public void setTwoDarray(double[][] var1) {
        if (this.numberOfRows != var1.length) {
            throw new IllegalArgumentException("row length of this Matrix differs from that of the 2D array argument");
        } else if (this.numberOfColumns != var1[0].length) {
            throw new IllegalArgumentException("column length of this Matrix differs from that of the 2D array argument");
        } else {
            for(int var2 = 0; var2 < this.numberOfRows; ++var2) {
                if (var1[var2].length != this.numberOfColumns) {
                    throw new IllegalArgumentException("All rows must have the same length");
                }

                for(int var3 = 0; var3 < this.numberOfColumns; ++var3) {
                    this.matrix[var2][var3] = var1[var2][var3];
                }
            }

            this.eigenDone = false;
            this.hessenbergDone = false;
        }
    }

    public void setElement(int var1, int var2, double var3) {
        this.matrix[var1][var2] = var3;
        this.eigenDone = false;
        this.hessenbergDone = false;
    }

    public void setSubMatrix(int var1, int var2, double[][] var3) {
        int var4 = var3.length;
        int var5 = var3[0].length;
        if (var1 + var4 - 1 >= this.numberOfRows) {
            throw new IllegalArgumentException("Sub-matrix position is outside the row bounds of this Matrix");
        } else if (var2 + var5 - 1 >= this.numberOfColumns) {
            throw new IllegalArgumentException("Sub-matrix position is outside the column bounds of this Matrix");
        } else {
            int var6 = 0;
            boolean var7 = false;

            for(int var8 = 0; var8 < var4; ++var8) {
                int var10 = 0;

                for(int var9 = 0; var9 < var5; ++var9) {
                    this.matrix[var1 + var8][var2 + var9] = var3[var6][var10];
                    ++var10;
                }

                ++var6;
            }

            this.eigenDone = false;
            this.hessenbergDone = false;
        }
    }

    public void setSubMatrix(int var1, int var2, int var3, int var4, double[][] var5) {
        this.setSubMatrix(var1, var2, var5);
        this.eigenDone = false;
        this.hessenbergDone = false;
    }

    public void setSubMatrix(int[] var1, int[] var2, double[][] var3) {
        int var4 = var1.length;
        int var5 = var2.length;

        for(int var6 = 0; var6 < var4; ++var6) {
            for(int var7 = 0; var7 < var5; ++var7) {
                this.matrix[var1[var6]][var2[var7]] = var3[var6][var7];
            }
        }

        this.eigenDone = false;
        this.hessenbergDone = false;
    }

    public boolean getMatrixCheck() {
        return this.matrixCheck;
    }

    public static Matrix identityMatrix(int var0) {
        Matrix var1 = new Matrix(var0, var0);

        for(int var2 = 0; var2 < var0; ++var2) {
            var1.matrix[var2][var2] = 1.0D;
        }

        return var1;
    }

    public static Matrix unitMatrix(int var0) {
        Matrix var1 = new Matrix(var0, var0);

        for(int var2 = 0; var2 < var0; ++var2) {
            for(int var3 = 0; var3 < var0; ++var3) {
                var1.matrix[var2][var3] = 1.0D;
            }
        }

        return var1;
    }

    public static Matrix unitMatrix(int var0, int var1) {
        Matrix var2 = new Matrix(var0, var1);

        for(int var3 = 0; var3 < var0; ++var3) {
            for(int var4 = 0; var4 < var1; ++var4) {
                var2.matrix[var3][var4] = 1.0D;
            }
        }

        return var2;
    }

    public static Matrix scalarMatrix(int var0, double var1) {
        Matrix var3 = new Matrix(var0, var0);
        double[][] var4 = var3.getArrayReference();

        for(int var5 = 0; var5 < var0; ++var5) {
            for(int var6 = var5; var6 < var0; ++var6) {
                if (var5 == var6) {
                    var4[var5][var6] = var1;
                }
            }
        }

        return var3;
    }

    public static Matrix scalarMatrix(int var0, int var1, double var2) {
        Matrix var4 = new Matrix(var0, var1);
        double[][] var5 = var4.getArrayReference();

        for(int var6 = 0; var6 < var0; ++var6) {
            for(int var7 = var6; var7 < var1; ++var7) {
                if (var6 == var7) {
                    var5[var6][var7] = var2;
                }
            }
        }

        return var4;
    }

    public static Matrix diagonalMatrix(int var0, double[] var1) {
        if (var1.length != var0) {
            throw new IllegalArgumentException("matrix dimension differs from diagonal array length");
        } else {
            Matrix var2 = new Matrix(var0, var0);
            double[][] var3 = var2.getArrayReference();

            for(int var4 = 0; var4 < var0; ++var4) {
                var3[var4][var4] = var1[var4];
            }

            return var2;
        }
    }

    public static Matrix diagonalMatrix(int var0, int var1, double[] var2) {
        if (var2.length != var0) {
            throw new IllegalArgumentException("matrix dimension differs from diagonal array length");
        } else {
            Matrix var3 = new Matrix(var0, var1);
            double[][] var4 = var3.getArrayReference();

            for(int var5 = 0; var5 < var0; ++var5) {
                for(int var6 = var5; var6 < var1; ++var6) {
                    if (var5 == var6) {
                        var4[var5][var6] = var2[var5];
                    }
                }
            }

            return var3;
        }
    }

    public int getNumberOfRows() {
        return this.numberOfRows;
    }

    public int getNrow() {
        return this.numberOfRows;
    }

    public int getNumberOfColumns() {
        return this.numberOfColumns;
    }

    public int getNcol() {
        return this.numberOfColumns;
    }

    public double[][] getArrayReference() {
        return this.matrix;
    }

    public double[][] getArrayPointer() {
        return this.matrix;
    }

    public double[][] getArrayCopy() {
        double[][] var1 = new double[this.numberOfRows][this.numberOfColumns];

        for(int var2 = 0; var2 < this.numberOfRows; ++var2) {
            for(int var3 = 0; var3 < this.numberOfColumns; ++var3) {
                var1[var2][var3] = this.matrix[var2][var3];
            }
        }

        return var1;
    }

    public double[] getRowCopy(int var1) {
        if (var1 >= this.numberOfRows) {
            throw new IllegalArgumentException("Row index, " + var1 + ", must be less than the number of rows, " + this.numberOfRows);
        } else if (var1 < 0) {
            throw new IllegalArgumentException("Row index, " + var1 + ", must be zero or positive");
        } else {
            return Conv.copy(this.matrix[var1]);
        }
    }

    public double[] getColumnCopy(int var1) {
        if (var1 >= this.numberOfColumns) {
            throw new IllegalArgumentException("Column index, " + var1 + ", must be less than the number of columns, " + this.numberOfColumns);
        } else if (var1 < 0) {
            throw new IllegalArgumentException("column index, " + var1 + ", must be zero or positive");
        } else {
            double[] var2 = new double[this.numberOfRows];

            for(int var3 = 0; var3 < this.numberOfRows; ++var3) {
                var2[var3] = this.matrix[var3][var1];
            }

            return var2;
        }
    }

    public double getElement(int var1, int var2) {
        return this.matrix[var1][var2];
    }

    public double getElementCopy(int var1, int var2) {
        return this.matrix[var1][var2];
    }

    public double getElementPointer(int var1, int var2) {
        return this.matrix[var1][var2];
    }

    public Matrix getSubMatrix(int var1, int var2, int var3, int var4) {
        if (var1 > var3) {
            throw new IllegalArgumentException("row indices inverted");
        } else if (var2 > var4) {
            throw new IllegalArgumentException("column indices inverted");
        } else if (var3 >= this.numberOfRows) {
            throw new IllegalArgumentException("Sub-matrix position is outside the row bounds of this Matrix");
        } else if (var4 >= this.numberOfColumns) {
            throw new IllegalArgumentException("Sub-matrix position is outside the column bounds of this Matrix" + var1 + " " + var4);
        } else {
            int var5 = var3 - var1 + 1;
            int var6 = var4 - var2 + 1;
            Matrix var7 = new Matrix(var5, var6);
            double[][] var8 = var7.getArrayReference();

            for(int var9 = 0; var9 < var5; ++var9) {
                for(int var10 = 0; var10 < var6; ++var10) {
                    var8[var9][var10] = this.matrix[var1 + var9][var2 + var10];
                }
            }

            return var7;
        }
    }

    public Matrix getSubMatrix(int[] var1, int[] var2) {
        int var3 = var1.length;
        int var4 = var2.length;
        Matrix var5 = new Matrix(var3, var4);
        double[][] var6 = var5.getArrayReference();

        for(int var7 = 0; var7 < var3; ++var7) {
            for(int var8 = 0; var8 < var4; ++var8) {
                var6[var7][var8] = this.matrix[var1[var7]][var2[var8]];
            }
        }

        return var5;
    }

    public int[] getIndexReference() {
        return this.permutationIndex;
    }

    public int[] getIndexPointer() {
        return this.permutationIndex;
    }

    public int[] getIndexCopy() {
        int[] var1 = new int[this.numberOfRows];

        for(int var2 = 0; var2 < this.numberOfRows; ++var2) {
            var1[var2] = this.permutationIndex[var2];
        }

        return var1;
    }

    public double getSwap() {
        return this.rowSwapIndex;
    }

    public static Matrix copy(Matrix var0) {
        if (var0 == null) {
            return null;
        } else {
            int var1 = var0.getNumberOfRows();
            int var2 = var0.getNumberOfColumns();
            double[][] var3 = var0.getArrayReference();
            Matrix var4 = new Matrix(var1, var2);
            var4.numberOfRows = var1;
            var4.numberOfColumns = var2;
            double[][] var5 = var4.getArrayReference();

            int var6;
            for(var6 = 0; var6 < var1; ++var6) {
                for(int var7 = 0; var7 < var2; ++var7) {
                    var5[var6][var7] = var3[var6][var7];
                }
            }

            for(var6 = 0; var6 < var1; ++var6) {
                var4.permutationIndex[var6] = var0.permutationIndex[var6];
            }

            return var4;
        }
    }

    public Matrix copy() {
        if (this == null) {
            return null;
        } else {
            int var1 = this.numberOfRows;
            int var2 = this.numberOfColumns;
            Matrix var3 = new Matrix(var1, var2);
            double[][] var4 = var3.getArrayReference();
            var3.numberOfRows = var1;
            var3.numberOfColumns = var2;

            int var5;
            for(var5 = 0; var5 < var1; ++var5) {
                for(int var6 = 0; var6 < var2; ++var6) {
                    var4[var5][var6] = this.matrix[var5][var6];
                }
            }

            for(var5 = 0; var5 < var1; ++var5) {
                var3.permutationIndex[var5] = this.permutationIndex[var5];
            }

            return var3;
        }
    }

    public Object clone() {
        if (this == null) {
            return null;
        } else {
            int var1 = this.numberOfRows;
            int var2 = this.numberOfColumns;
            Matrix var3 = new Matrix(var1, var2);
            double[][] var4 = var3.getArrayReference();
            var3.numberOfRows = var1;
            var3.numberOfColumns = var2;

            int var5;
            for(var5 = 0; var5 < var1; ++var5) {
                for(int var6 = 0; var6 < var2; ++var6) {
                    var4[var5][var6] = this.matrix[var5][var6];
                }
            }

            for(var5 = 0; var5 < var1; ++var5) {
                var3.permutationIndex[var5] = this.permutationIndex[var5];
            }

            return var3;
        }
    }

    public static Matrix columnMatrix(double[] var0) {
        int var1 = var0.length;
        Matrix var2 = new Matrix(var1, 1);

        for(int var3 = 0; var3 < var1; ++var3) {
            var2.matrix[var3][0] = var0[var3];
        }

        return var2;
    }

    public static Matrix rowMatrix(double[] var0) {
        int var1 = var0.length;
        Matrix var2 = new Matrix(1, var1);

        for(int var3 = 0; var3 < var1; ++var3) {
            var2.matrix[0][var3] = var0[var3];
        }

        return var2;
    }

    public Matrix plus(Matrix var1) {
        if (this.numberOfRows == var1.numberOfRows && this.numberOfColumns == var1.numberOfColumns) {
            int var2 = var1.numberOfRows;
            int var3 = var1.numberOfColumns;
            Matrix var4 = new Matrix(var2, var3);
            double[][] var5 = var4.getArrayReference();

            for(int var6 = 0; var6 < var2; ++var6) {
                for(int var7 = 0; var7 < var3; ++var7) {
                    var5[var6][var7] = this.matrix[var6][var7] + var1.matrix[var6][var7];
                }
            }

            return var4;
        } else {
            throw new IllegalArgumentException("Array dimensions do not agree");
        }
    }

    public Matrix plus(double[][] var1) {
        int var2 = var1.length;
        int var3 = var1[0].length;
        if (this.numberOfRows == var2 && this.numberOfColumns == var3) {
            Matrix var4 = new Matrix(var2, var3);
            double[][] var5 = var4.getArrayReference();

            for(int var6 = 0; var6 < var2; ++var6) {
                for(int var7 = 0; var7 < var3; ++var7) {
                    var5[var6][var7] = this.matrix[var6][var7] + var1[var6][var7];
                }
            }

            return var4;
        } else {
            throw new IllegalArgumentException("Array dimensions do not agree");
        }
    }

    public static Matrix plus(Matrix var0, Matrix var1) {
        if (var0.numberOfRows == var1.numberOfRows && var0.numberOfColumns == var1.numberOfColumns) {
            int var2 = var0.numberOfRows;
            int var3 = var0.numberOfColumns;
            Matrix var4 = new Matrix(var2, var3);
            double[][] var5 = var4.getArrayReference();

            for(int var6 = 0; var6 < var2; ++var6) {
                for(int var7 = 0; var7 < var3; ++var7) {
                    var5[var6][var7] = var0.matrix[var6][var7] + var1.matrix[var6][var7];
                }
            }

            return var4;
        } else {
            throw new IllegalArgumentException("Array dimensions do not agree");
        }
    }

    public void plusEquals(Matrix var1) {
        if (this.numberOfRows == var1.numberOfRows && this.numberOfColumns == var1.numberOfColumns) {
            int var2 = var1.numberOfRows;
            int var3 = var1.numberOfColumns;

            for(int var4 = 0; var4 < var2; ++var4) {
                for(int var5 = 0; var5 < var3; ++var5) {
                    this.matrix[var4][var5] += var1.matrix[var4][var5];
                }
            }

        } else {
            throw new IllegalArgumentException("Array dimensions do not agree");
        }
    }

    public Matrix minus(Matrix var1) {
        if (this.numberOfRows == var1.numberOfRows && this.numberOfColumns == var1.numberOfColumns) {
            int var2 = this.numberOfRows;
            int var3 = this.numberOfColumns;
            Matrix var4 = new Matrix(var2, var3);
            double[][] var5 = var4.getArrayReference();

            for(int var6 = 0; var6 < var2; ++var6) {
                for(int var7 = 0; var7 < var3; ++var7) {
                    var5[var6][var7] = this.matrix[var6][var7] - var1.matrix[var6][var7];
                }
            }

            return var4;
        } else {
            throw new IllegalArgumentException("Array dimensions do not agree");
        }
    }

    public Matrix minus(double[][] var1) {
        int var2 = var1.length;
        int var3 = var1[0].length;
        if (this.numberOfRows == var2 && this.numberOfColumns == var3) {
            Matrix var4 = new Matrix(var2, var3);
            double[][] var5 = var4.getArrayReference();

            for(int var6 = 0; var6 < var2; ++var6) {
                for(int var7 = 0; var7 < var3; ++var7) {
                    var5[var6][var7] = this.matrix[var6][var7] - var1[var6][var7];
                }
            }

            return var4;
        } else {
            throw new IllegalArgumentException("Array dimensions do not agree");
        }
    }

    public static Matrix minus(Matrix var0, Matrix var1) {
        if (var0.numberOfRows == var1.numberOfRows && var0.numberOfColumns == var1.numberOfColumns) {
            int var2 = var0.numberOfRows;
            int var3 = var0.numberOfColumns;
            Matrix var4 = new Matrix(var2, var3);
            double[][] var5 = var4.getArrayReference();

            for(int var6 = 0; var6 < var2; ++var6) {
                for(int var7 = 0; var7 < var3; ++var7) {
                    var5[var6][var7] = var0.matrix[var6][var7] - var1.matrix[var6][var7];
                }
            }

            return var4;
        } else {
            throw new IllegalArgumentException("Array dimensions do not agree");
        }
    }

    public void minusEquals(Matrix var1) {
        if (this.numberOfRows == var1.numberOfRows && this.numberOfColumns == var1.numberOfColumns) {
            int var2 = var1.numberOfRows;
            int var3 = var1.numberOfColumns;

            for(int var4 = 0; var4 < var2; ++var4) {
                for(int var5 = 0; var5 < var3; ++var5) {
                    this.matrix[var4][var5] -= var1.matrix[var4][var5];
                }
            }

        } else {
            throw new IllegalArgumentException("Array dimensions do not agree");
        }
    }

    public Matrix times(Matrix var1) {
        if (this.numberOfColumns != var1.numberOfRows) {
            throw new IllegalArgumentException("Nonconformable matrices");
        } else {
            Matrix var2 = new Matrix(this.numberOfRows, var1.numberOfColumns);
            double[][] var3 = var2.getArrayReference();
            double var4 = 0.0D;

            for(int var6 = 0; var6 < this.numberOfRows; ++var6) {
                for(int var7 = 0; var7 < var1.numberOfColumns; ++var7) {
                    var4 = 0.0D;

                    for(int var8 = 0; var8 < this.numberOfColumns; ++var8) {
                        var4 += this.matrix[var6][var8] * var1.matrix[var8][var7];
                    }

                    var3[var6][var7] = var4;
                }
            }

            return var2;
        }
    }

    public Matrix times(double[][] var1) {
        int var2 = var1.length;
        int var3 = var1[0].length;
        if (this.numberOfColumns != var2) {
            throw new IllegalArgumentException("Nonconformable matrices");
        } else {
            Matrix var4 = new Matrix(this.numberOfRows, var3);
            double[][] var5 = var4.getArrayReference();
            double var6 = 0.0D;

            for(int var8 = 0; var8 < this.numberOfRows; ++var8) {
                for(int var9 = 0; var9 < var3; ++var9) {
                    var6 = 0.0D;

                    for(int var10 = 0; var10 < this.numberOfColumns; ++var10) {
                        var6 += this.matrix[var8][var10] * var1[var10][var9];
                    }

                    var5[var8][var9] = var6;
                }
            }

            return var4;
        }
    }

    public Matrix times(double var1) {
        Matrix var3 = new Matrix(this.numberOfRows, this.numberOfColumns);
        double[][] var4 = var3.getArrayReference();

        for(int var5 = 0; var5 < this.numberOfRows; ++var5) {
            for(int var6 = 0; var6 < this.numberOfColumns; ++var6) {
                var4[var5][var6] = this.matrix[var5][var6] * var1;
            }
        }

        return var3;
    }

    public static Matrix times(Matrix var0, Matrix var1) {
        if (var0.numberOfColumns != var1.numberOfRows) {
            throw new IllegalArgumentException("Nonconformable matrices");
        } else {
            Matrix var2 = new Matrix(var0.numberOfRows, var1.numberOfColumns);
            double[][] var3 = var2.getArrayReference();
            double var4 = 0.0D;

            for(int var6 = 0; var6 < var0.numberOfRows; ++var6) {
                for(int var7 = 0; var7 < var1.numberOfColumns; ++var7) {
                    var4 = 0.0D;

                    for(int var8 = 0; var8 < var0.numberOfColumns; ++var8) {
                        var4 += var0.matrix[var6][var8] * var1.matrix[var8][var7];
                    }

                    var3[var6][var7] = var4;
                }
            }

            return var2;
        }
    }

    public static Matrix times(Matrix var0, double[][] var1) {
        if (var0.numberOfColumns != var1.length) {
            throw new IllegalArgumentException("Nonconformable matrices");
        } else {
            Matrix var2 = new Matrix(var0.numberOfRows, var1[0].length);
            Matrix var3 = new Matrix(var1);
            double[][] var4 = var2.getArrayReference();
            double var5 = 0.0D;

            for(int var7 = 0; var7 < var0.numberOfRows; ++var7) {
                for(int var8 = 0; var8 < var3.numberOfColumns; ++var8) {
                    var5 = 0.0D;

                    for(int var9 = 0; var9 < var0.numberOfColumns; ++var9) {
                        var5 += var0.matrix[var7][var9] * var3.matrix[var9][var8];
                    }

                    var4[var7][var8] = var5;
                }
            }

            return var2;
        }
    }

    public static Matrix times(Matrix var0, double var1) {
        Matrix var3 = new Matrix(var0.numberOfRows, var0.numberOfColumns);
        double[][] var4 = var3.getArrayReference();

        for(int var5 = 0; var5 < var0.numberOfRows; ++var5) {
            for(int var6 = 0; var6 < var0.numberOfColumns; ++var6) {
                var4[var5][var6] = var0.matrix[var5][var6] * var1;
            }
        }

        return var3;
    }

    public void timesEquals(Matrix var1) {
        if (this.numberOfColumns != var1.numberOfRows) {
            throw new IllegalArgumentException("Nonconformable matrices");
        } else {
            Matrix var2 = new Matrix(this.numberOfRows, var1.numberOfColumns);
            double[][] var3 = var2.getArrayReference();
            double var4 = 0.0D;

            int var6;
            int var7;
            for(var6 = 0; var6 < this.numberOfRows; ++var6) {
                for(var7 = 0; var7 < var1.numberOfColumns; ++var7) {
                    var4 = 0.0D;

                    for(int var8 = 0; var8 < this.numberOfColumns; ++var8) {
                        var4 += this.matrix[var6][var8] * var1.matrix[var8][var7];
                    }

                    var3[var6][var7] = var4;
                }
            }

            this.numberOfRows = var2.numberOfRows;
            this.numberOfColumns = var2.numberOfColumns;

            for(var6 = 0; var6 < this.numberOfRows; ++var6) {
                for(var7 = 0; var7 < this.numberOfColumns; ++var7) {
                    this.matrix[var6][var7] = var2.matrix[var6][var7];
                }
            }

        }
    }

    public void timesEquals(double var1) {
        for(int var3 = 0; var3 < this.numberOfRows; ++var3) {
            for(int var4 = 0; var4 < this.numberOfColumns; ++var4) {
                this.matrix[var3][var4] *= var1;
            }
        }

    }

    public Matrix over(Matrix var1) {
        if (this.numberOfRows == var1.numberOfRows && this.numberOfColumns == var1.numberOfColumns) {
            return this.times(var1.inverse());
        } else {
            throw new IllegalArgumentException("Array dimensions do not agree");
        }
    }

    public Matrix over(Matrix var1, Matrix var2) {
        if (var1.numberOfRows == var2.numberOfRows && var1.numberOfColumns == var2.numberOfColumns) {
            return var1.times(var2.inverse());
        } else {
            throw new IllegalArgumentException("Array dimensions do not agree");
        }
    }

    public Matrix over(double[][] var1) {
        int var2 = var1.length;
        int var3 = var1[0].length;
        if (this.numberOfRows == var2 && this.numberOfColumns == var3) {
            Matrix var4 = new Matrix(var1);
            return this.times(var4.inverse());
        } else {
            throw new IllegalArgumentException("Array dimensions do not agree");
        }
    }

    public Matrix over(Matrix var1, double[][] var2) {
        int var3 = var2.length;
        int var4 = var2[0].length;
        if (var1.numberOfRows == var3 && var1.numberOfColumns == var4) {
            Matrix var5 = new Matrix(var2);
            return var1.times(var5.inverse());
        } else {
            throw new IllegalArgumentException("Array dimensions do not agree");
        }
    }

    public Matrix over(double[][] var1, Matrix var2) {
        int var3 = var1.length;
        int var4 = var1[0].length;
        if (var2.numberOfRows == var3 && var2.numberOfColumns == var4) {
            Matrix var5 = new Matrix(var1);
            return var5.times(var2.inverse());
        } else {
            throw new IllegalArgumentException("Array dimensions do not agree");
        }
    }

    public Matrix over(double[][] var1, double[][] var2) {
        int var3 = var1.length;
        int var4 = var1[0].length;
        if (var2.length == var3 && var2[0].length == var4) {
            Matrix var5 = new Matrix(var1);
            Matrix var6 = new Matrix(var2);
            return var5.times(var6.inverse());
        } else {
            throw new IllegalArgumentException("Array dimensions do not agree");
        }
    }

    public void overEquals(Matrix var1) {
        if (this.numberOfRows == var1.numberOfRows && this.numberOfColumns == var1.numberOfColumns) {
            Matrix var2 = new Matrix(var1);
            this.timesEquals(var2.inverse());
        } else {
            throw new IllegalArgumentException("Array dimensions do not agree");
        }
    }

    public void overEquals(double[][] var1) {
        Matrix var2 = new Matrix(var1);
        this.overEquals(var2);
    }

    public Matrix inverse() {
        int var1 = this.numberOfRows;
        if (var1 != this.numberOfColumns) {
            throw new IllegalArgumentException("Matrix is not square");
        } else {
            Matrix var2 = new Matrix(var1, var1);
            double[][] var3;
            if (var1 == 1) {
                var3 = this.getArrayCopy();
                if (var3[0][0] == 0.0D) {
                    throw new IllegalArgumentException("Matrix is singular");
                }

                var3[0][0] = 1.0D / var3[0][0];
                var2 = new Matrix(var3);
            } else if (var1 == 2) {
                var3 = this.getArrayCopy();
                double var4 = var3[0][0] * var3[1][1] - var3[0][1] * var3[1][0];
                if (var4 == 0.0D) {
                    throw new IllegalArgumentException("Matrix is singular");
                }

                double[][] var6 = new double[2][2];
                var6[0][0] = var3[1][1] / var4;
                var6[1][1] = var3[0][0] / var4;
                var6[1][0] = -var3[1][0] / var4;
                var6[0][1] = -var3[0][1] / var4;
                var2 = new Matrix(var6);
            } else {
                double[] var9 = new double[var1];
                double[] var10 = new double[var1];
                double[][] var5 = var2.getArrayReference();
                Matrix var11 = this.luDecomp();

                for(int var7 = 0; var7 < var1; ++var7) {
                    int var8;
                    for(var8 = 0; var8 < var1; ++var8) {
                        var9[var8] = 0.0D;
                    }

                    var9[var7] = 1.0D;
                    var10 = var11.luBackSub(var9);

                    for(var8 = 0; var8 < var1; ++var8) {
                        var5[var8][var7] = var10[var8];
                    }
                }
            }

            return var2;
        }
    }

    public static Matrix inverse(Matrix var0) {
        int var1 = var0.numberOfRows;
        if (var1 != var0.numberOfColumns) {
            throw new IllegalArgumentException("Matrix is not square");
        } else {
            Matrix var2 = new Matrix(var1, var1);
            double[][] var3;
            if (var1 == 1) {
                var3 = var0.getArrayCopy();
                if (var3[0][0] == 0.0D) {
                    throw new IllegalArgumentException("Matrix is singular");
                }

                var3[0][0] = 1.0D / var3[0][0];
                var2 = new Matrix(var3);
            } else if (var1 == 2) {
                var3 = var0.getArrayCopy();
                double var4 = var3[0][0] * var3[1][1] - var3[0][1] * var3[1][0];
                if (var4 == 0.0D) {
                    throw new IllegalArgumentException("Matrix is singular");
                }

                double[][] var6 = new double[2][2];
                var6[0][0] = var3[1][1] / var4;
                var6[1][1] = var3[0][0] / var4;
                var6[1][0] = -var3[1][0] / var4;
                var6[0][1] = -var3[0][1] / var4;
                var2 = new Matrix(var6);
            } else {
                double[] var9 = new double[var1];
                double[] var10 = new double[var1];
                double[][] var5 = var2.getArrayReference();
                Matrix var11 = var0.luDecomp();

                for(int var7 = 0; var7 < var1; ++var7) {
                    int var8;
                    for(var8 = 0; var8 < var1; ++var8) {
                        var9[var8] = 0.0D;
                    }

                    var9[var7] = 1.0D;
                    var10 = var11.luBackSub(var9);

                    for(var8 = 0; var8 < var1; ++var8) {
                        var5[var8][var7] = var10[var8];
                    }
                }
            }

            return var2;
        }
    }

    public Matrix transpose() {
        Matrix var1 = new Matrix(this.numberOfColumns, this.numberOfRows);
        double[][] var2 = var1.getArrayReference();

        for(int var3 = 0; var3 < this.numberOfColumns; ++var3) {
            for(int var4 = 0; var4 < this.numberOfRows; ++var4) {
                var2[var3][var4] = this.matrix[var4][var3];
            }
        }

        return var1;
    }

    public static Matrix transpose(Matrix var0) {
        Matrix var1 = new Matrix(var0.numberOfColumns, var0.numberOfRows);
        double[][] var2 = var1.getArrayReference();

        for(int var3 = 0; var3 < var0.numberOfColumns; ++var3) {
            for(int var4 = 0; var4 < var0.numberOfRows; ++var4) {
                var2[var3][var4] = var0.matrix[var4][var3];
            }
        }

        return var1;
    }

    public Matrix opposite() {
        Matrix var1 = copy(this);

        for(int var2 = 0; var2 < this.numberOfRows; ++var2) {
            for(int var3 = 0; var3 < this.numberOfColumns; ++var3) {
                var1.matrix[var2][var3] = -this.matrix[var2][var3];
            }
        }

        return var1;
    }

    public static Matrix opposite(Matrix var0) {
        Matrix var1 = copy(var0);

        for(int var2 = 0; var2 < var0.numberOfRows; ++var2) {
            for(int var3 = 0; var3 < var0.numberOfColumns; ++var3) {
                var1.matrix[var2][var3] = -var0.matrix[var2][var3];
            }
        }

        return var1;
    }

    public double trace() {
        double var1 = 0.0D;

        for(int var3 = 0; var3 < Math.min(this.numberOfColumns, this.numberOfColumns); ++var3) {
            var1 += this.matrix[var3][var3];
        }

        return var1;
    }

    public static double trace(Matrix var0) {
        double var1 = 0.0D;

        for(int var3 = 0; var3 < Math.min(var0.numberOfColumns, var0.numberOfColumns); ++var3) {
            var1 += var0.matrix[var3][var3];
        }

        return var1;
    }

    public double determinant() {
        int var1 = this.numberOfRows;
        if (var1 != this.numberOfColumns) {
            throw new IllegalArgumentException("Matrix is not square");
        } else {
            double var2 = 0.0D;
            if (var1 == 2) {
                var2 = this.matrix[0][0] * this.matrix[1][1] - this.matrix[0][1] * this.matrix[1][0];
            } else {
                Matrix var4 = this.luDecomp();
                var2 = var4.rowSwapIndex;

                for(int var5 = 0; var5 < var1; ++var5) {
                    var2 *= var4.matrix[var5][var5];
                }
            }

            return var2;
        }
    }

    public static double determinant(Matrix var0) {
        int var1 = var0.numberOfRows;
        if (var1 != var0.numberOfColumns) {
            throw new IllegalArgumentException("Matrix is not square");
        } else {
            double var2 = 0.0D;
            if (var1 == 2) {
                double[][] var4 = var0.getArrayCopy();
                var2 = var4[0][0] * var4[1][1] - var4[0][1] * var4[1][0];
            } else {
                Matrix var6 = var0.luDecomp();
                var2 = var6.rowSwapIndex;

                for(int var5 = 0; var5 < var1; ++var5) {
                    var2 *= var6.matrix[var5][var5];
                }
            }

            return var2;
        }
    }

    public static double determinant(double[][] var0) {
        int var1 = var0.length;

        for(int var2 = 0; var2 < var1; ++var2) {
            if (var1 != var0[var2].length) {
                throw new IllegalArgumentException("Matrix is not square");
            }
        }

        double var7 = 0.0D;
        if (var1 == 2) {
            var7 = var0[0][0] * var0[1][1] - var0[0][1] * var0[1][0];
        } else {
            Matrix var4 = new Matrix(var0);
            Matrix var5 = var4.luDecomp();
            var7 = var5.rowSwapIndex;

            for(int var6 = 0; var6 < var1; ++var6) {
                var7 *= var5.matrix[var6][var6];
            }
        }

        return var7;
    }

    public double logDeterminant() {
        int var1 = this.numberOfRows;
        if (var1 != this.numberOfColumns) {
            throw new IllegalArgumentException("Matrix is not square");
        } else {
            double var2 = 0.0D;
            Matrix var4 = this.luDecomp();
            var2 = var4.rowSwapIndex;
            var2 = Math.log(var2);

            for(int var5 = 0; var5 < var1; ++var5) {
                var2 += Math.log(var4.matrix[var5][var5]);
            }

            return var2;
        }
    }

    public static double logDeterminant(Matrix var0) {
        int var1 = var0.numberOfRows;
        if (var1 != var0.numberOfColumns) {
            throw new IllegalArgumentException("Matrix is not square");
        } else {
            double var2 = 0.0D;
            Matrix var4 = var0.luDecomp();
            var2 = var4.rowSwapIndex;
            var2 = Math.log(var2);

            for(int var5 = 0; var5 < var1; ++var5) {
                var2 += Math.log(var4.matrix[var5][var5]);
            }

            return var2;
        }
    }

    public static double logDeterminant(double[][] var0) {
        int var1 = var0.length;

        for(int var2 = 0; var2 < var1; ++var2) {
            if (var1 != var0[var2].length) {
                throw new IllegalArgumentException("Matrix is not square");
            }
        }

        Matrix var3 = new Matrix(var0);
        return var3.logDeterminant();
    }

    public Matrix cofactor() {
        double[][] var1 = new double[this.numberOfRows][this.numberOfColumns];

        for(int var2 = 0; var2 < this.numberOfRows; ++var2) {
            for(int var3 = 0; var3 < this.numberOfColumns; ++var3) {
                var1[var2][var3] = this.cofactor(var2, var3);
            }
        }

        return new Matrix(var1);
    }

    public double cofactor(int var1, int var2) {
        if (var1 >= 0 && var1 < this.numberOfRows) {
            if (var2 >= 0 && var2 < this.numberOfColumns) {
                int[] var3 = new int[this.numberOfRows - 1];
                int[] var4 = new int[this.numberOfColumns - 1];
                int var5 = 0;

                int var6;
                for(var6 = 0; var6 < this.numberOfRows; ++var6) {
                    if (var6 != var1) {
                        var3[var5] = var6;
                        ++var5;
                    }
                }

                var5 = 0;

                for(var6 = 0; var6 < this.numberOfColumns; ++var6) {
                    if (var6 != var2) {
                        var4[var5] = var6;
                        ++var5;
                    }
                }

                Matrix var9 = this.getSubMatrix(var3, var4);
                double var7 = var9.determinant();
                return var7 * Math.pow(-1.0D, (double)(var1 + var2));
            } else {
                throw new IllegalArgumentException("The entered column index, " + var2 + " must lie between 0 and " + (this.numberOfColumns - 1) + " inclusive");
            }
        } else {
            throw new IllegalArgumentException("The entered row index, " + var1 + " must lie between 0 and " + (this.numberOfRows - 1) + " inclusive");
        }
    }

    public Matrix reducedRowEchelonForm() {
        double[][] var1 = new double[this.numberOfRows][this.numberOfColumns];

        int var2;
        int var3;
        for(var2 = 0; var2 < this.numberOfRows; ++var2) {
            for(var3 = 0; var3 < this.numberOfColumns; ++var3) {
                var1[var2][var3] = this.matrix[var2][var3];
            }
        }

        var2 = 0;
        var3 = 0;
        boolean var4 = true;

        while(true) {
            boolean var6;
            do {
                int var5;
                if (!var4) {
                    for(var5 = 0; var5 < this.numberOfRows; ++var5) {
                        for(int var12 = 0; var12 < this.numberOfColumns; ++var12) {
                            if (var1[var5][var12] == -0.0D) {
                                var1[var5][var12] = 0.0D;
                            }
                        }
                    }

                    return new Matrix(var1);
                }

                var5 = var3;
                var6 = true;

                while(var6 && var1[var5][var2] == 0.0D) {
                    ++var5;
                    if (var5 == this.numberOfRows) {
                        var5 = var3;
                        ++var2;
                        if (var2 == this.numberOfColumns) {
                            var6 = false;
                        }
                    }
                }

                if (var6) {
                    double[] var7 = var1[var3];
                    var1[var3] = var1[var5];
                    var1[var5] = var7;
                    double var8 = var1[var3][var2];

                    int var10;
                    for(var10 = 0; var10 < this.numberOfColumns; ++var10) {
                        var1[var3][var10] /= var8;
                    }

                    var10 = 0;

                    while(true) {
                        if (var10 >= this.numberOfRows) {
                            ++var2;
                            if (var2 >= this.numberOfColumns) {
                                var4 = false;
                            }
                            break;
                        }

                        if (var10 != var3) {
                            var8 = var1[var10][var2];

                            for(int var11 = 0; var11 < this.numberOfColumns; ++var11) {
                                var1[var10][var11] -= var8 * var1[var3][var11];
                            }
                        }

                        ++var10;
                    }
                }

                ++var3;
            } while(var3 < this.numberOfRows && var6);

            var4 = false;
        }
    }

    public double frobeniusNorm() {
        double var1 = 0.0D;

        for(int var3 = 0; var3 < this.numberOfRows; ++var3) {
            for(int var4 = 0; var4 < this.numberOfColumns; ++var4) {
                var1 = hypot(var1, Math.abs(this.matrix[var3][var4]));
            }
        }

        return var1;
    }

    public double oneNorm() {
        double var1 = 0.0D;
        double var3 = 0.0D;

        for(int var5 = 0; var5 < this.numberOfRows; ++var5) {
            var3 = 0.0D;

            for(int var6 = 0; var6 < this.numberOfColumns; ++var6) {
                var3 += Math.abs(this.matrix[var5][var6]);
            }

            var1 = Math.max(var1, var3);
        }

        return var1;
    }

    public double infinityNorm() {
        double var1 = 0.0D;
        double var3 = 0.0D;

        for(int var5 = 0; var5 < this.numberOfRows; ++var5) {
            var3 = 0.0D;

            for(int var6 = 0; var6 < this.numberOfColumns; ++var6) {
                var3 += Math.abs(this.matrix[var5][var6]);
            }

            var1 = Math.max(var1, var3);
        }

        return var1;
    }

    public double sum() {
        double var1 = 0.0D;

        for(int var3 = 0; var3 < this.numberOfRows; ++var3) {
            for(int var4 = 0; var4 < this.numberOfColumns; ++var4) {
                var1 += this.matrix[var3][var4];
            }
        }

        return var1;
    }

    public double[] rowSums() {
        double[] var1 = new double[this.numberOfRows];

        for(int var2 = 0; var2 < this.numberOfRows; ++var2) {
            var1[var2] = 0.0D;

            for(int var3 = 0; var3 < this.numberOfColumns; ++var3) {
                var1[var2] += this.matrix[var2][var3];
            }
        }

        return var1;
    }

    public double[] columnSums() {
        double[] var1 = new double[this.numberOfColumns];

        for(int var2 = 0; var2 < this.numberOfColumns; ++var2) {
            var1[var2] = 0.0D;

            for(int var3 = 0; var3 < this.numberOfRows; ++var3) {
                var1[var2] += this.matrix[var3][var2];
            }
        }

        return var1;
    }

    public double mean() {
        double var1 = 0.0D;

        for(int var3 = 0; var3 < this.numberOfRows; ++var3) {
            for(int var4 = 0; var4 < this.numberOfColumns; ++var4) {
                var1 += this.matrix[var3][var4];
            }
        }

        var1 /= (double)(this.numberOfRows * this.numberOfColumns);
        return var1;
    }

    public double[] rowMeans() {
        double[] var1 = new double[this.numberOfRows];

        for(int var2 = 0; var2 < this.numberOfRows; ++var2) {
            var1[var2] = 0.0D;

            for(int var3 = 0; var3 < this.numberOfColumns; ++var3) {
                var1[var2] += this.matrix[var2][var3];
            }

            var1[var2] /= (double)this.numberOfColumns;
        }

        return var1;
    }

    public double[] columnMeans() {
        double[] var1 = new double[this.numberOfColumns];

        for(int var2 = 0; var2 < this.numberOfColumns; ++var2) {
            var1[var2] = 0.0D;

            for(int var3 = 0; var3 < this.numberOfRows; ++var3) {
                var1[var2] += this.matrix[var3][var2];
            }

            var1[var2] /= (double)this.numberOfRows;
        }

        return var1;
    }

    public Matrix subtractMean() {
        Matrix var1 = new Matrix(this.numberOfRows, this.numberOfColumns);
        double var2 = 0.0D;

        int var4;
        int var5;
        for(var4 = 0; var4 < this.numberOfRows; ++var4) {
            for(var5 = 0; var5 < this.numberOfColumns; ++var5) {
                var2 += this.matrix[var4][var5];
            }
        }

        var2 /= (double)(this.numberOfRows * this.numberOfColumns);

        for(var4 = 0; var4 < this.numberOfRows; ++var4) {
            for(var5 = 0; var5 < this.numberOfColumns; ++var5) {
                var1.matrix[var4][var5] = this.matrix[var4][var5] - var2;
            }
        }

        return var1;
    }

    public Matrix subtractRowMeans() {
        Matrix var1 = new Matrix(this.numberOfRows, this.numberOfColumns);

        for(int var2 = 0; var2 < this.numberOfRows; ++var2) {
            double var3 = 0.0D;

            int var5;
            for(var5 = 0; var5 < this.numberOfColumns; ++var5) {
                var3 += this.matrix[var2][var5];
            }

            var3 /= (double)this.numberOfColumns;

            for(var5 = 0; var5 < this.numberOfColumns; ++var5) {
                var1.matrix[var2][var5] = this.matrix[var2][var5] - var3;
            }
        }

        return var1;
    }

    public Matrix subtractColumnMeans() {
        Matrix var1 = new Matrix(this.numberOfRows, this.numberOfColumns);

        for(int var2 = 0; var2 < this.numberOfColumns; ++var2) {
            double var3 = 0.0D;

            int var5;
            for(var5 = 0; var5 < this.numberOfRows; ++var5) {
                var3 += this.matrix[var5][var2];
            }

            var3 /= (double)this.numberOfRows;

            for(var5 = 0; var5 < this.numberOfRows; ++var5) {
                var1.matrix[var5][var2] = this.matrix[var5][var2] - var3;
            }
        }

        return var1;
    }

    public double median() {
        Stat var1 = new Stat(this.matrix[0]);

        for(int var2 = 1; var2 < this.numberOfRows; ++var2) {
            var1.concatenate(this.matrix[var2]);
        }

        return var1.median();
    }

    public double[] rowMedians() {
        double[] var1 = new double[this.numberOfRows];

        for(int var2 = 0; var2 < this.numberOfRows; ++var2) {
            Stat var3 = new Stat(this.matrix[var2]);
            var1[var2] = var3.median();
        }

        return var1;
    }

    public double[] columnMedians() {
        double[] var1 = new double[this.numberOfRows];

        for(int var2 = 0; var2 < this.numberOfColumns; ++var2) {
            double[] var3 = new double[this.numberOfRows];

            for(int var4 = 0; var4 < this.numberOfRows; ++var4) {
                var3[var2] = this.matrix[var4][var2];
            }

            Stat var5 = new Stat(var3);
            var1[var2] = var5.median();
        }

        return var1;
    }

    public void setDenominatorToN() {
        Stat.setStaticDenominatorToN();
    }

    public double variance() {
        Stat var1 = new Stat(this.matrix[0]);

        for(int var2 = 1; var2 < this.numberOfRows; ++var2) {
            var1.concatenate(this.matrix[var2]);
        }

        return var1.variance();
    }

    public double[] rowVariances() {
        double[] var1 = new double[this.numberOfRows];

        for(int var2 = 0; var2 < this.numberOfRows; ++var2) {
            Stat var3 = new Stat(this.matrix[var2]);
            var1[var2] = var3.variance();
        }

        return var1;
    }

    public double[] columnVariances() {
        double[] var1 = new double[this.numberOfColumns];

        for(int var2 = 0; var2 < this.numberOfColumns; ++var2) {
            double[] var3 = new double[this.numberOfRows];

            for(int var4 = 0; var4 < this.numberOfRows; ++var4) {
                var3[var2] = this.matrix[var4][var2];
            }

            Stat var5 = new Stat(var3);
            var1[var2] = var5.variance();
        }

        return var1;
    }

    public double standardDeviation() {
        Stat var1 = new Stat(this.matrix[0]);

        for(int var2 = 1; var2 < this.numberOfRows; ++var2) {
            var1.concatenate(this.matrix[var2]);
        }

        return var1.standardDeviation();
    }

    public double[] rowStandardDeviations() {
        double[] var1 = new double[this.numberOfRows];

        for(int var2 = 0; var2 < this.numberOfRows; ++var2) {
            Stat var3 = new Stat(this.matrix[var2]);
            var1[var2] = var3.standardDeviation();
        }

        return var1;
    }

    public double[] columnStandardDeviations() {
        double[] var1 = new double[this.numberOfColumns];

        for(int var2 = 0; var2 < this.numberOfColumns; ++var2) {
            double[] var3 = new double[this.numberOfRows];

            for(int var4 = 0; var4 < this.numberOfRows; ++var4) {
                var3[var2] = this.matrix[var4][var2];
            }

            Stat var5 = new Stat(var3);
            var1[var2] = var5.standardDeviation();
        }

        return var1;
    }

    public double stanadardError() {
        Stat var1 = new Stat(this.matrix[0]);

        for(int var2 = 1; var2 < this.numberOfRows; ++var2) {
            var1.concatenate(this.matrix[var2]);
        }

        return var1.standardError();
    }

    public double[] rowStandardErrors() {
        double[] var1 = new double[this.numberOfRows];

        for(int var2 = 0; var2 < this.numberOfRows; ++var2) {
            Stat var3 = new Stat(this.matrix[var2]);
            var1[var2] = var3.standardError();
        }

        return var1;
    }

    public double[] columnStandardErrors() {
        double[] var1 = new double[this.numberOfRows];

        for(int var2 = 0; var2 < this.numberOfColumns; ++var2) {
            double[] var3 = new double[this.numberOfRows];

            for(int var4 = 0; var4 < this.numberOfRows; ++var4) {
                var3[var2] = this.matrix[var4][var2];
            }

            Stat var5 = new Stat(var3);
            var1[var2] = var5.standardError();
        }

        return var1;
    }

    public double[] maximumElement() {
        double[] var1 = new double[3];
        double[] var2 = new double[this.numberOfRows];
        ArrayMaths var3 = null;
        int[] var4 = new int[this.numberOfRows];

        int var5;
        for(var5 = 0; var5 < this.numberOfRows; ++var5) {
            var3 = new ArrayMaths(this.matrix[var5]);
            var2[var5] = var3.maximum();
            var4[var5] = var3.maximumIndex();
        }

        var3 = new ArrayMaths(var2);
        var1[0] = var3.maximum();
        var5 = var3.maximumIndex();
        var1[1] = (double)var5;
        var1[2] = (double)var4[var5];
        return var1;
    }

    public double[] rowMaxima() {
        double[] var1 = new double[this.numberOfRows];

        for(int var2 = 0; var2 < this.numberOfRows; ++var2) {
            Stat var3 = new Stat(this.matrix[var2]);
            var1[var2] = var3.maximum();
        }

        return var1;
    }

    public double[] columnMaxima() {
        double[] var1 = new double[this.numberOfRows];

        for(int var2 = 0; var2 < this.numberOfColumns; ++var2) {
            double[] var3 = new double[this.numberOfRows];

            for(int var4 = 0; var4 < this.numberOfRows; ++var4) {
                var3[var2] = this.matrix[var4][var2];
            }

            Stat var5 = new Stat(var3);
            var1[var2] = var5.maximum();
        }

        return var1;
    }

    public double[] minimumElement() {
        double[] var1 = new double[3];
        double[] var2 = new double[this.numberOfRows];
        ArrayMaths var3 = null;
        int[] var4 = new int[this.numberOfRows];

        int var5;
        for(var5 = 0; var5 < this.numberOfRows; ++var5) {
            var3 = new ArrayMaths(this.matrix[var5]);
            var2[var5] = var3.minimum();
            var4[var5] = var3.minimumIndex();
        }

        var3 = new ArrayMaths(var2);
        var1[0] = var3.minimum();
        var5 = var3.minimumIndex();
        var1[1] = (double)var5;
        var1[2] = (double)var4[var5];
        return var1;
    }

    public double[] rowMinima() {
        double[] var1 = new double[this.numberOfRows];

        for(int var2 = 0; var2 < this.numberOfRows; ++var2) {
            Stat var3 = new Stat(this.matrix[var2]);
            var1[var2] = var3.minimum();
        }

        return var1;
    }

    public double[] columnMinima() {
        double[] var1 = new double[this.numberOfRows];

        for(int var2 = 0; var2 < this.numberOfColumns; ++var2) {
            double[] var3 = new double[this.numberOfRows];

            for(int var4 = 0; var4 < this.numberOfRows; ++var4) {
                var3[var2] = this.matrix[var4][var2];
            }

            Stat var5 = new Stat(var3);
            var1[var2] = var5.minimum();
        }

        return var1;
    }

    public double range() {
        return this.maximumElement()[0] - this.minimumElement()[0];
    }

    public double[] rowRanges() {
        double[] var1 = new double[this.numberOfRows];

        for(int var2 = 0; var2 < this.numberOfRows; ++var2) {
            Stat var3 = new Stat(this.matrix[var2]);
            var1[var2] = var3.maximum() - var3.minimum();
        }

        return var1;
    }

    public double[] columnRanges() {
        double[] var1 = new double[this.numberOfRows];

        for(int var2 = 0; var2 < this.numberOfColumns; ++var2) {
            double[] var3 = new double[this.numberOfRows];

            for(int var4 = 0; var4 < this.numberOfRows; ++var4) {
                var3[var2] = this.matrix[var4][var2];
            }

            Stat var5 = new Stat(var3);
            var1[var2] = var5.maximum() - var5.minimum();
        }

        return var1;
    }

    public int[] pivot() {
        double[] var1 = this.maximumElement();
        int var2 = (int)var1[1];
        int var3 = (int)var1[2];
        double[] var4 = this.minimumElement();
        int var5 = (int)var4[1];
        int var6 = (int)var4[2];
        if (Math.abs(var4[0]) > Math.abs(var1[0])) {
            var2 = var5;
            var3 = var6;
        }

        int[] var7 = new int[]{var2, var3};
        double[] var8 = this.matrix[0];
        this.matrix[0] = this.matrix[var2];
        this.matrix[var2] = var8;
        double var9 = 0.0D;

        for(int var11 = 0; var11 < this.numberOfRows; ++var11) {
            var9 = this.matrix[var11][0];
            this.matrix[var11][0] = this.matrix[var11][var3];
            this.matrix[var11][var3] = var9;
        }

        return var7;
    }

    public boolean isSquare() {
        boolean var1 = false;
        if (this.numberOfRows == this.numberOfColumns) {
            var1 = true;
        }

        return var1;
    }

    public boolean isSymmetric() {
        boolean var1 = true;
        if (this.numberOfRows == this.numberOfColumns) {
            for(int var2 = 0; var2 < this.numberOfRows; ++var2) {
                for(int var3 = var2 + 1; var3 < this.numberOfColumns; ++var3) {
                    if (this.matrix[var2][var3] != this.matrix[var3][var2]) {
                        var1 = false;
                    }
                }
            }
        } else {
            var1 = false;
        }

        return var1;
    }

    public boolean isZero() {
        boolean var1 = true;

        for(int var2 = 0; var2 < this.numberOfRows; ++var2) {
            for(int var3 = 0; var3 < this.numberOfColumns; ++var3) {
                if (this.matrix[var2][var3] != 0.0D) {
                    var1 = false;
                }
            }
        }

        return var1;
    }

    public boolean isUnit() {
        boolean var1 = true;

        for(int var2 = 0; var2 < this.numberOfRows; ++var2) {
            for(int var3 = 0; var3 < this.numberOfColumns; ++var3) {
                if (this.matrix[var2][var3] != 1.0D) {
                    var1 = false;
                }
            }
        }

        return var1;
    }

    public boolean isDiagonal() {
        boolean var1 = true;

        for(int var2 = 0; var2 < this.numberOfRows; ++var2) {
            for(int var3 = 0; var3 < this.numberOfColumns; ++var3) {
                if (var2 != var3 && this.matrix[var2][var3] != 0.0D) {
                    var1 = false;
                }
            }
        }

        return var1;
    }

    public boolean isUpperTriagonal() {
        boolean var1 = true;

        for(int var2 = 0; var2 < this.numberOfRows; ++var2) {
            for(int var3 = 0; var3 < this.numberOfColumns; ++var3) {
                if (var3 < var2 && this.matrix[var2][var3] != 0.0D) {
                    var1 = false;
                }
            }
        }

        return var1;
    }

    public boolean isLowerTriagonal() {
        boolean var1 = true;

        for(int var2 = 0; var2 < this.numberOfRows; ++var2) {
            for(int var3 = 0; var3 < this.numberOfColumns; ++var3) {
                if (var2 > var3 && this.matrix[var2][var3] != 0.0D) {
                    var1 = false;
                }
            }
        }

        return var1;
    }

    public boolean isTridiagonal() {
        boolean var1 = true;

        for(int var2 = 0; var2 < this.numberOfRows; ++var2) {
            for(int var3 = 0; var3 < this.numberOfColumns; ++var3) {
                if (var2 < var3 + 1 && this.matrix[var2][var3] != 0.0D) {
                    var1 = false;
                }

                if (var3 > var2 + 1 && this.matrix[var2][var3] != 0.0D) {
                    var1 = false;
                }
            }
        }

        return var1;
    }

    public boolean isUpperHessenberg() {
        boolean var1 = true;

        for(int var2 = 0; var2 < this.numberOfRows; ++var2) {
            for(int var3 = 0; var3 < this.numberOfColumns; ++var3) {
                if (var3 < var2 + 1 && this.matrix[var2][var3] != 0.0D) {
                    var1 = false;
                }
            }
        }

        return var1;
    }

    public boolean isLowerHessenberg() {
        boolean var1 = true;

        for(int var2 = 0; var2 < this.numberOfRows; ++var2) {
            for(int var3 = 0; var3 < this.numberOfColumns; ++var3) {
                if (var2 > var3 + 1 && this.matrix[var2][var3] != 0.0D) {
                    var1 = false;
                }
            }
        }

        return var1;
    }

    public boolean isIdentity() {
        boolean var1 = true;
        if (this.numberOfRows == this.numberOfColumns) {
            for(int var2 = 0; var2 < this.numberOfRows; ++var2) {
                if (this.matrix[var2][var2] != 1.0D) {
                    var1 = false;
                }

                for(int var3 = var2 + 1; var3 < this.numberOfColumns; ++var3) {
                    if (this.matrix[var2][var3] != 0.0D) {
                        var1 = false;
                    }

                    if (this.matrix[var3][var2] != 0.0D) {
                        var1 = false;
                    }
                }
            }
        } else {
            var1 = false;
        }

        return var1;
    }

    public boolean isNearlySymmetric(double var1) {
        boolean var3 = true;
        if (this.numberOfRows == this.numberOfColumns) {
            for(int var4 = 0; var4 < this.numberOfRows; ++var4) {
                for(int var5 = var4 + 1; var5 < this.numberOfColumns; ++var5) {
                    if (Math.abs(this.matrix[var4][var5] - this.matrix[var5][var4]) > Math.abs(var1)) {
                        var3 = false;
                    }
                }
            }
        } else {
            var3 = false;
        }

        return var3;
    }

    public boolean isNearlyZero(double var1) {
        boolean var3 = true;

        for(int var4 = 0; var4 < this.numberOfRows; ++var4) {
            for(int var5 = 0; var5 < this.numberOfColumns; ++var5) {
                if (Math.abs(this.matrix[var4][var5]) > Math.abs(var1)) {
                    var3 = false;
                }
            }
        }

        return var3;
    }

    public boolean isNearlyUnit(double var1) {
        boolean var3 = true;

        for(int var4 = 0; var4 < this.numberOfRows; ++var4) {
            for(int var5 = 0; var5 < this.numberOfColumns; ++var5) {
                if (Math.abs(this.matrix[var4][var5] - 1.0D) > Math.abs(var1)) {
                    var3 = false;
                }
            }
        }

        return var3;
    }

    public boolean isNearlyUpperTriagonal(double var1) {
        boolean var3 = true;

        for(int var4 = 0; var4 < this.numberOfRows; ++var4) {
            for(int var5 = 0; var5 < this.numberOfColumns; ++var5) {
                if (var5 < var4 && Math.abs(this.matrix[var4][var5]) > Math.abs(var1)) {
                    var3 = false;
                }
            }
        }

        return var3;
    }

    public boolean isNearlyLowerTriagonal(double var1) {
        boolean var3 = true;

        for(int var4 = 0; var4 < this.numberOfRows; ++var4) {
            for(int var5 = 0; var5 < this.numberOfColumns; ++var5) {
                if (var4 > var5 && Math.abs(this.matrix[var4][var5]) > Math.abs(var1)) {
                    var3 = false;
                }
            }
        }

        return var3;
    }

    public boolean isNearlyIdenty(double var1) {
        boolean var3 = true;
        if (this.numberOfRows == this.numberOfColumns) {
            for(int var4 = 0; var4 < this.numberOfRows; ++var4) {
                if (Math.abs(this.matrix[var4][var4] - 1.0D) > Math.abs(var1)) {
                    var3 = false;
                }

                for(int var5 = var4 + 1; var5 < this.numberOfColumns; ++var5) {
                    if (Math.abs(this.matrix[var4][var5]) > Math.abs(var1)) {
                        var3 = false;
                    }

                    if (Math.abs(this.matrix[var5][var4]) > Math.abs(var1)) {
                        var3 = false;
                    }
                }
            }
        } else {
            var3 = false;
        }

        return var3;
    }

    public boolean isTridiagonal(double var1) {
        boolean var3 = true;

        for(int var4 = 0; var4 < this.numberOfRows; ++var4) {
            for(int var5 = 0; var5 < this.numberOfColumns; ++var5) {
                if (var4 < var5 + 1 && Math.abs(this.matrix[var4][var5]) > Math.abs(var1)) {
                    var3 = false;
                }

                if (var5 > var4 + 1 && Math.abs(this.matrix[var4][var5]) > Math.abs(var1)) {
                    var3 = false;
                }
            }
        }

        return var3;
    }

    public boolean isNearlyTridiagonal(double var1) {
        boolean var3 = true;

        for(int var4 = 0; var4 < this.numberOfRows; ++var4) {
            for(int var5 = 0; var5 < this.numberOfColumns; ++var5) {
                if (var4 < var5 + 1 && Math.abs(this.matrix[var4][var5]) > Math.abs(var1)) {
                    var3 = false;
                }

                if (var5 > var4 + 1 && Math.abs(this.matrix[var4][var5]) > Math.abs(var1)) {
                    var3 = false;
                }
            }
        }

        return var3;
    }

    public boolean isNearlyUpperHessenberg(double var1) {
        boolean var3 = true;

        for(int var4 = 0; var4 < this.numberOfRows; ++var4) {
            for(int var5 = 0; var5 < this.numberOfColumns; ++var5) {
                if (var5 < var4 + 1 && Math.abs(this.matrix[var4][var5]) > Math.abs(var1)) {
                    var3 = false;
                }
            }
        }

        return var3;
    }

    public boolean isNearlyLowerHessenberg(double var1) {
        boolean var3 = true;

        for(int var4 = 0; var4 < this.numberOfRows; ++var4) {
            for(int var5 = 0; var5 < this.numberOfColumns; ++var5) {
                if (var4 > var5 + 1 && Math.abs(this.matrix[var4][var5]) > Math.abs(var1)) {
                    var3 = false;
                }
            }
        }

        return var3;
    }

    public boolean isSingular() {
        boolean var1 = false;
        double var2 = this.determinant();
        if (var2 == 0.0D) {
            var1 = true;
        }

        return var1;
    }

    public boolean isNearlySingular(double var1) {
        boolean var3 = false;
        double var4 = this.determinant();
        if (Math.abs(var4) <= Math.abs(var1)) {
            var3 = true;
        }

        return var3;
    }

    public ArrayList<Integer> identicalRows() {
        ArrayList var1 = new ArrayList();
        int var2 = 0;

        for(int var3 = 0; var3 < this.numberOfRows - 1; ++var3) {
            for(int var4 = var3 + 1; var4 < this.numberOfRows; ++var4) {
                int var5 = 0;

                for(int var6 = 0; var6 < this.numberOfColumns; ++var6) {
                    if (this.matrix[var3][var6] == this.matrix[var4][var6]) {
                        ++var5;
                    }
                }

                if (var5 == this.numberOfColumns) {
                    ++var2;
                    var1.add(new Integer(var3));
                    var1.add(new Integer(var4));
                }
            }
        }

        var1.add(0, new Integer(var2));
        return var1;
    }

    public ArrayList<Integer> identicalColumns() {
        ArrayList var1 = new ArrayList();
        int var2 = 0;

        for(int var3 = 0; var3 < this.numberOfColumns; ++var3) {
            for(int var4 = var3 + 1; var4 < this.numberOfColumns - 1; ++var4) {
                int var5 = 0;

                for(int var6 = 0; var6 < this.numberOfRows; ++var6) {
                    if (this.matrix[var6][var3] == this.matrix[var6][var4]) {
                        ++var5;
                    }
                }

                if (var5 == this.numberOfRows) {
                    ++var2;
                    var1.add(new Integer(var3));
                    var1.add(new Integer(var4));
                }
            }
        }

        var1.add(0, new Integer(var2));
        return var1;
    }

    public ArrayList<Integer> zeroRows() {
        ArrayList var1 = new ArrayList();
        int var2 = 0;

        for(int var3 = 0; var3 < this.numberOfRows; ++var3) {
            int var4 = 0;

            for(int var5 = 0; var5 < this.numberOfColumns; ++var5) {
                if (this.matrix[var3][var5] == 0.0D) {
                    ++var4;
                }
            }

            if (var4 == this.numberOfColumns) {
                ++var2;
                var1.add(new Integer(var3));
            }
        }

        var1.add(0, new Integer(var2));
        return var1;
    }

    public ArrayList<Integer> zeroColumns() {
        ArrayList var1 = new ArrayList();
        int var2 = 0;

        for(int var3 = 0; var3 < this.numberOfColumns; ++var3) {
            int var4 = 0;

            for(int var5 = 0; var5 < this.numberOfRows; ++var5) {
                if (this.matrix[var5][var3] == 0.0D) {
                    ++var4;
                }
            }

            if (var4 == this.numberOfRows) {
                ++var2;
                var1.add(new Integer(var3));
            }
        }

        var1.add(0, new Integer(var2));
        return var1;
    }

    public Matrix luDecomp() {
        if (this.numberOfRows != this.numberOfColumns) {
            throw new IllegalArgumentException("A matrix is not square");
        } else {
            int var1 = this.numberOfRows;
            int var2 = 0;
            double var3 = 0.0D;
            double var5 = 0.0D;
            double var7 = 0.0D;
            double[] var9 = new double[var1];
            double var10 = 0.0D;
            double var12 = 0.0D;
            this.matrixCheck = true;
            Matrix var14 = copy(this);
            double[][] var15 = var14.getArrayReference();
            var14.rowSwapIndex = 1.0D;

            int var16;
            int var17;
            int var18;
            for(var16 = 0; var16 < var1; ++var16) {
                var7 = 0.0D;

                for(var17 = 0; var17 < var1; ++var17) {
                    if ((var5 = Math.abs(var15[var16][var17])) > var7) {
                        var7 = var5;
                    }
                }

                if (var7 == 0.0D) {
                    if (!this.suppressErrorMessage) {
                        System.out.println("Attempted LU Decomposition of a singular matrix in Matrix.luDecomp()");
                        System.out.println("NaN matrix returned and matrixCheck set to false");
                    }

                    this.matrixCheck = false;

                    for(var17 = 0; var17 < var1; ++var17) {
                        for(var18 = 0; var18 < var1; ++var18) {
                            var15[var17][var18] = 0.0D / 0.0;
                        }
                    }

                    return var14;
                }

                var9[var16] = 1.0D / var7;
            }

            for(var16 = 0; var16 < var1; ++var16) {
                for(var17 = 0; var17 < var16; ++var17) {
                    var10 = var15[var17][var16];

                    for(var18 = 0; var18 < var17; ++var18) {
                        var10 -= var15[var17][var18] * var15[var18][var16];
                    }

                    var15[var17][var16] = var10;
                }

                var7 = 0.0D;

                for(var17 = var16; var17 < var1; ++var17) {
                    var10 = var15[var17][var16];

                    for(var18 = 0; var18 < var16; ++var18) {
                        var10 -= var15[var17][var18] * var15[var18][var16];
                    }

                    var15[var17][var16] = var10;
                    if ((var3 = var9[var17] * Math.abs(var10)) >= var7) {
                        var7 = var3;
                        var2 = var17;
                    }
                }

                if (var16 != var2) {
                    for(var17 = 0; var17 < var1; ++var17) {
                        var12 = var15[var2][var17];
                        var15[var2][var17] = var15[var16][var17];
                        var15[var16][var17] = var12;
                    }

                    var14.rowSwapIndex = -var14.rowSwapIndex;
                    var9[var2] = var9[var16];
                }

                var14.permutationIndex[var16] = var2;
                if (var15[var16][var16] == 0.0D) {
                    var15[var16][var16] = this.tiny;
                }

                if (var16 != var1 - 1) {
                    var12 = 1.0D / var15[var16][var16];

                    for(var17 = var16 + 1; var17 < var1; ++var17) {
                        var15[var17][var16] *= var12;
                    }
                }
            }

            return var14;
        }
    }

    public double[] luBackSub(double[] var1) {
        int var2 = 0;
        boolean var3 = false;
        int var4 = var1.length;
        if (var4 != this.numberOfColumns) {
            throw new IllegalArgumentException("vector length is not equal to matrix dimension");
        } else if (this.numberOfColumns != this.numberOfRows) {
            throw new IllegalArgumentException("matrix is not square");
        } else {
            double var5 = 0.0D;
            double[] var7 = new double[var4];

            int var8;
            for(var8 = 0; var8 < var4; ++var8) {
                var7[var8] = var1[var8];
            }

            int var9;
            for(var8 = 0; var8 < var4; ++var8) {
                int var10 = this.permutationIndex[var8];
                var5 = var7[var10];
                var7[var10] = var7[var8];
                if (var2 == 0) {
                    for(var9 = var2; var9 <= var8 - 1; ++var9) {
                        var5 -= this.matrix[var8][var9] * var7[var9];
                    }
                } else if (var5 == 0.0D) {
                    var2 = var8;
                }

                var7[var8] = var5;
            }

            for(var8 = var4 - 1; var8 >= 0; --var8) {
                var5 = var7[var8];

                for(var9 = var8 + 1; var9 < var4; ++var9) {
                    var5 -= this.matrix[var8][var9] * var7[var9];
                }

                var7[var8] = var5 / this.matrix[var8][var8];
            }

            return var7;
        }
    }

    public double[] solveLinearSet(double[] var1) {
        Object var2 = null;
        double[] var7;
        if (this.numberOfRows == this.numberOfColumns) {
            Matrix var3 = this.luDecomp();
            var7 = var3.luBackSub(var1);
        } else {
            if (this.numberOfRows <= this.numberOfColumns) {
                throw new IllegalArgumentException("This class does not handle underdetermined equations");
            }

            int var8 = var1.length;
            if (this.numberOfRows != var8) {
                throw new IllegalArgumentException("Overdetermined equation solution - vector length is not equal to matrix column length");
            }

            Matrix var4 = this.transpose();
            double[][] var5 = var4.getArrayCopy();
            Regression var6 = new Regression(var5, var1);
            var6.linearGeneral();
            var7 = var6.getCoeff();
        }

        return var7;
    }

    public void suppressErrorMessage() {
        this.suppressErrorMessage = true;
    }

    public void supressErrorMessage() {
        this.suppressErrorMessage = true;
    }

    public void hessenbergMatrix() {
        this.hessenberg = this.getArrayCopy();
        double var1 = 0.0D;
        boolean var3 = false;
        double var4 = 0.0D;

        int var6;
        int var7;
        for(var6 = 1; var6 < this.numberOfRows - 1; ++var6) {
            var1 = 0.0D;
            int var9 = var6;

            for(var7 = var6; var7 < this.numberOfRows; ++var7) {
                if (Math.abs(this.hessenberg[var7][var6 - 1]) > Math.abs(var1)) {
                    var1 = this.hessenberg[var7][var6 - 1];
                    var9 = var7;
                }
            }

            if (var9 != var6) {
                for(var7 = var6 - 1; var7 < this.numberOfRows; ++var7) {
                    var4 = this.hessenberg[var9][var7];
                    this.hessenberg[var9][var7] = this.hessenberg[var6][var7];
                    this.hessenberg[var6][var7] = var4;
                }

                for(var7 = 0; var7 < this.numberOfRows; ++var7) {
                    var4 = this.hessenberg[var7][var9];
                    this.hessenberg[var7][var9] = this.hessenberg[var7][var6];
                    this.hessenberg[var7][var6] = var4;
                }

                if (var1 != 0.0D) {
                    for(var7 = var6 + 1; var7 < this.numberOfRows; ++var7) {
                        var4 = this.hessenberg[var7][var6 - 1];
                        if (var4 != 0.0D) {
                            var4 /= var1;
                            this.hessenberg[var7][var6 - 1] = var4;

                            int var8;
                            for(var8 = var6; var8 < this.numberOfRows; ++var8) {
                                this.hessenberg[var7][var8] -= var4 * this.hessenberg[var6][var8];
                            }

                            for(var8 = 0; var8 < this.numberOfRows; ++var8) {
                                this.hessenberg[var8][var6] += var4 * this.hessenberg[var8][var7];
                            }
                        }
                    }
                }
            }
        }

        for(var6 = 2; var6 < this.numberOfRows; ++var6) {
            for(var7 = 0; var7 < var6 - 1; ++var7) {
                this.hessenberg[var6][var7] = 0.0D;
            }
        }

        this.hessenbergDone = true;
    }

    public double[][] getHessenbergMatrix() {
        if (!this.hessenbergDone) {
            this.hessenbergMatrix();
        }

        return this.hessenberg;
    }

    public double[] getEigenValues() {
        if (!this.eigenDone) {
            this.symmetricEigen();
        }

        return this.eigenValues;
    }

    public double[] getSortedEigenValues() {
        if (!this.eigenDone) {
            this.symmetricEigen();
        }

        return this.sortedEigenValues;
    }

    public double[][] getEigenVectorsAsColumns() {
        if (!this.eigenDone) {
            this.symmetricEigen();
        }

        return this.eigenVector;
    }

    public double[][] getEigenVector() {
        if (!this.eigenDone) {
            this.symmetricEigen();
        }

        return this.eigenVector;
    }

    public double[][] getEigenVectorsAsRows() {
        if (!this.eigenDone) {
            this.symmetricEigen();
        }

        double[][] var1 = new double[this.numberOfRows][this.numberOfRows];

        for(int var2 = 0; var2 < this.numberOfRows; ++var2) {
            for(int var3 = 0; var3 < this.numberOfRows; ++var3) {
                var1[var2][var3] = this.eigenVector[var3][var2];
            }
        }

        return var1;
    }

    public double[][] getSortedEigenVectorsAsColumns() {
        if (!this.eigenDone) {
            this.symmetricEigen();
        }

        return this.sortedEigenVector;
    }

    public double[][] getSortedEigenVector() {
        if (!this.eigenDone) {
            this.symmetricEigen();
        }

        return this.sortedEigenVector;
    }

    public double[][] getSortedEigenVectorsAsRows() {
        if (!this.eigenDone) {
            this.symmetricEigen();
        }

        double[][] var1 = new double[this.numberOfRows][this.numberOfRows];

        for(int var2 = 0; var2 < this.numberOfRows; ++var2) {
            for(int var3 = 0; var3 < this.numberOfRows; ++var3) {
                var1[var2][var3] = this.sortedEigenVector[var3][var2];
            }
        }

        return var1;
    }

    public int getNumberOfJacobiRotations() {
        return this.numberOfRotations;
    }

    private void symmetricEigen() {
        if (!this.isSymmetric()) {
            throw new IllegalArgumentException("matrix is not symmetric");
        } else {
            double[][] var1 = this.getArrayCopy();
            this.eigenVector = new double[this.numberOfRows][this.numberOfRows];
            this.eigenValues = new double[this.numberOfRows];
            double var2 = 0.0D;
            double var4 = 0.0D;
            double var6 = 0.0D;
            double var8 = 0.0D;
            double var10 = 0.0D;
            double var12 = 0.0D;
            double var14 = 0.0D;
            double var16 = 0.0D;
            double var18 = 0.0D;
            double[] var20 = new double[this.numberOfRows];
            double[] var21 = new double[this.numberOfRows];

            int var22;
            int var23;
            for(var22 = 0; var22 < this.numberOfRows; ++var22) {
                for(var23 = 0; var23 < this.numberOfRows; ++var23) {
                    this.eigenVector[var22][var23] = 0.0D;
                }

                this.eigenVector[var22][var22] = 1.0D;
            }

            for(var22 = 0; var22 < this.numberOfRows; ++var22) {
                var20[var22] = var1[var22][var22];
                this.eigenValues[var22] = var1[var22][var22];
                var21[var22] = 0.0D;
            }

            this.numberOfRotations = 0;

            for(var22 = 1; var22 <= this.maximumJacobiIterations; ++var22) {
                var8 = 0.0D;

                int var24;
                for(var23 = 0; var23 < this.numberOfRows - 1; ++var23) {
                    for(var24 = var23 + 1; var24 < this.numberOfRows; ++var24) {
                        var8 += Math.abs(var1[var23][var24]);
                    }
                }

                if (var8 == 0.0D) {
                    this.eigenDone = true;
                    this.eigenSort();
                    return;
                }

                if (var22 < 4) {
                    var2 = 0.2D * var8 / (double)(this.numberOfRows * this.numberOfRows);
                } else {
                    var2 = 0.0D;
                }

                for(var23 = 0; var23 < this.numberOfRows - 1; ++var23) {
                    for(var24 = var23 + 1; var24 < this.numberOfRows; ++var24) {
                        var10 = 100.0D * Math.abs(var1[var23][var24]);
                        if (var22 > 4 && Math.abs(this.eigenValues[var23]) + var10 == Math.abs(this.eigenValues[var23]) && Math.abs(this.eigenValues[var24]) + var10 == Math.abs(this.eigenValues[var24])) {
                            var1[var23][var24] = 0.0D;
                        } else if (Math.abs(var1[var23][var24]) > var2) {
                            var18 = this.eigenValues[var24] - this.eigenValues[var23];
                            if (Math.abs(var18) + var10 == Math.abs(var18)) {
                                var16 = var1[var23][var24] / var18;
                            } else {
                                var4 = 0.5D * var18 / var1[var23][var24];
                                var16 = 1.0D / (Math.abs(var4) + Math.sqrt(1.0D + var4 * var4));
                                if (var4 < 0.0D) {
                                    var16 = -var16;
                                }
                            }

                            var14 = 1.0D / Math.sqrt(1.0D + var16 * var16);
                            var12 = var16 * var14;
                            var6 = var12 / (1.0D + var14);
                            var18 = var16 * var1[var23][var24];
                            var21[var23] -= var18;
                            var21[var24] += var18;
                            this.eigenValues[var23] -= var18;
                            this.eigenValues[var24] += var18;
                            var1[var23][var24] = 0.0D;

                            int var25;
                            for(var25 = 0; var25 <= var23 - 1; ++var25) {
                                this.rotation(var1, var6, var12, var25, var23, var25, var24);
                            }

                            for(var25 = var23 + 1; var25 <= var24 - 1; ++var25) {
                                this.rotation(var1, var6, var12, var23, var25, var25, var24);
                            }

                            for(var25 = var24 + 1; var25 < this.numberOfRows; ++var25) {
                                this.rotation(var1, var6, var12, var23, var25, var24, var25);
                            }

                            for(var25 = 0; var25 < this.numberOfRows; ++var25) {
                                this.rotation(this.eigenVector, var6, var12, var25, var23, var25, var24);
                            }

                            ++this.numberOfRotations;
                        }
                    }
                }

                for(var23 = 0; var23 < this.numberOfRows; ++var23) {
                    var20[var23] += var21[var23];
                    this.eigenValues[var23] = var20[var23];
                    var21[var23] = 0.0D;
                }
            }

            System.out.println("Maximum iterations, " + this.maximumJacobiIterations + ", reached - values at this point returned");
            this.eigenDone = true;
            this.eigenSort();
        }
    }

    private void rotation(double[][] var1, double var2, double var4, int var6, int var7, int var8, int var9) {
        double var10 = var1[var6][var7];
        double var12 = var1[var8][var9];
        var1[var6][var7] = var10 - var4 * (var12 + var10 * var2);
        var1[var8][var9] = var12 + var4 * (var10 - var12 * var2);
    }

    private void eigenSort() {
        boolean var1 = false;
        this.sortedEigenValues = Conv.copy(this.eigenValues);
        this.sortedEigenVector = Conv.copy(this.eigenVector);
        this.eigenIndices = new int[this.numberOfRows];

        int var4;
        for(var4 = 0; var4 < this.numberOfRows - 1; ++var4) {
            double var2 = this.sortedEigenValues[var4];
            int var7 = var4;

            int var5;
            for(var5 = var4 + 1; var5 < this.numberOfRows; ++var5) {
                if (this.sortedEigenValues[var5] >= var2) {
                    var2 = this.sortedEigenValues[var5];
                    var7 = var5;
                }
            }

            if (var7 != var4) {
                this.sortedEigenValues[var7] = this.sortedEigenValues[var4];
                this.sortedEigenValues[var4] = var2;

                for(var5 = 0; var5 < this.numberOfRows; ++var5) {
                    var2 = this.sortedEigenVector[var5][var4];
                    this.sortedEigenVector[var5][var4] = this.sortedEigenVector[var5][var7];
                    this.sortedEigenVector[var5][var7] = var2;
                }
            }
        }

        this.eigenIndices = new int[this.numberOfRows];

        for(var4 = 0; var4 < this.numberOfRows; ++var4) {
            boolean var8 = true;
            int var6 = 0;

            while(var8) {
                if (this.sortedEigenValues[var4] == this.eigenValues[var6]) {
                    this.eigenIndices[var4] = var6;
                    var8 = false;
                } else {
                    ++var6;
                }
            }
        }

    }

    public int[] eigenValueIndices() {
        if (!this.eigenDone) {
            this.symmetricEigen();
        }

        return this.eigenIndices;
    }

    private static double hypot(double var0, double var2) {
        double var4 = 0.0D;
        double var6 = 0.0D;
        double var8 = Math.abs(var0);
        double var10 = Math.abs(var2);
        if (var8 == 0.0D) {
            var4 = var10;
        } else if (var10 == 0.0D) {
            var4 = var8;
        } else if (var8 <= var10) {
            var6 = var8 / var10;
            var4 = var10 * Math.sqrt(1.0D + var6 * var6);
        } else {
            var6 = var10 / var8;
            var4 = var8 * Math.sqrt(1.0D + var6 * var6);
        }

        return var4;
    }
}
