//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.complex;

import com.flanagan.math.Fmath;
import com.flanagan.math.Matrix;

public class ComplexMatrix {
    private int nrow = 0;
    private int ncol = 0;
    private Complex[][] matrix = (Complex[][])null;
    private int[] index = null;
    private double dswap = 1.0D;
    private static final double TINY = 1.0E-30D;

    public ComplexMatrix(int var1, int var2) {
        this.nrow = var1;
        this.ncol = var2;
        this.matrix = Complex.twoDarray(var1, var2);
        this.index = new int[var1];

        for(int var3 = 0; var3 < var1; this.index[var3] = var3++) {
            ;
        }

        this.dswap = 1.0D;
    }

    public ComplexMatrix(int var1, int var2, Complex var3) {
        this.nrow = var1;
        this.ncol = var2;
        this.matrix = Complex.twoDarray(var1, var2, var3);
        this.index = new int[var1];

        for(int var4 = 0; var4 < var1; this.index[var4] = var4++) {
            ;
        }

        this.dswap = 1.0D;
    }

    public ComplexMatrix(Complex[][] var1) {
        this.nrow = var1.length;
        this.ncol = var1[0].length;
        this.matrix = Complex.twoDarray(this.nrow, this.ncol);

        int var2;
        for(var2 = 0; var2 < this.nrow; ++var2) {
            if (var1[var2].length != this.ncol) {
                throw new IllegalArgumentException("All rows must have the same length");
            }

            for(int var3 = 0; var3 < this.ncol; ++var3) {
                this.matrix[var2][var3] = Complex.copy(var1[var2][var3]);
            }
        }

        this.index = new int[this.nrow];

        for(var2 = 0; var2 < this.nrow; this.index[var2] = var2++) {
            ;
        }

        this.dswap = 1.0D;
    }

    public ComplexMatrix(double[][] var1) {
        this.nrow = var1.length;
        this.ncol = var1[0].length;

        int var2;
        for(var2 = 0; var2 < this.nrow; ++var2) {
            if (var1[var2].length != this.ncol) {
                throw new IllegalArgumentException("All rows must have the same length");
            }
        }

        this.matrix = Complex.twoDarray(this.nrow, this.ncol);

        for(var2 = 0; var2 < this.nrow; ++var2) {
            for(int var3 = 0; var3 < this.ncol; ++var3) {
                this.matrix[var2][var3] = new Complex(var1[var2][var3], 0.0D);
            }
        }

        this.index = new int[this.nrow];

        for(var2 = 0; var2 < this.nrow; this.index[var2] = var2++) {
            ;
        }

        this.dswap = 1.0D;
    }

    public ComplexMatrix(ComplexMatrix var1) {
        this.nrow = var1.nrow;
        this.ncol = var1.ncol;
        this.matrix = var1.copy().matrix;
        this.index = var1.index;
        this.dswap = var1.dswap;
    }

    public ComplexMatrix(Matrix var1) {
        this.nrow = var1.getNrow();
        this.ncol = var1.getNcol();
        double[][] var2 = var1.getArrayCopy();
        this.matrix = Complex.twoDarray(this.nrow, this.ncol);

        for(int var3 = 0; var3 < this.nrow; ++var3) {
            for(int var4 = 0; var4 < this.ncol; ++var4) {
                this.matrix[var3][var4] = new Complex(var2[var3][var4], 0.0D);
            }
        }

        this.index = var1.getIndexCopy();
        this.dswap = var1.getSwap();
    }

    public void setTwoDarray(Complex[][] var1) {
        if (this.nrow != var1.length) {
            throw new IllegalArgumentException("row length of this ComplexMatrix differs from that of the 2D array argument");
        } else if (this.ncol != var1[0].length) {
            throw new IllegalArgumentException("column length of this ComplexMatrix differs from that of the 2D array argument");
        } else {
            for(int var2 = 0; var2 < this.nrow; ++var2) {
                if (var1[var2].length != this.ncol) {
                    throw new IllegalArgumentException("All rows must have the same length");
                }

                for(int var3 = 0; var3 < this.ncol; ++var3) {
                    this.matrix[var2][var3] = Complex.copy(var1[var2][var3]);
                }
            }

        }
    }

    public void setTwoDarray(double[][] var1) {
        if (this.nrow != var1.length) {
            throw new IllegalArgumentException("row length of this ComplexMatrix differs from that of the 2D array argument");
        } else if (this.ncol != var1[0].length) {
            throw new IllegalArgumentException("column length of this ComplexMatrix differs from that of the 2D array argument");
        } else {
            for(int var2 = 0; var2 < this.nrow; ++var2) {
                if (var1[var2].length != this.ncol) {
                    throw new IllegalArgumentException("All rows must have the same length");
                }

                for(int var3 = 0; var3 < this.ncol; ++var3) {
                    this.matrix[var2][var3] = new Complex(var1[var2][var3]);
                }
            }

        }
    }

    public void setElement(int var1, int var2, Complex var3) {
        this.matrix[var1][var2] = Complex.copy(var3);
    }

    public void setElement(int var1, int var2, double var3, double var5) {
        this.matrix[var1][var2].reset(var3, var5);
    }

    public void setSubMatrix(int var1, int var2, int var3, int var4, Complex[][] var5) {
        this.setSubMatrix(var1, var2, var5);
    }

    public void setSubMatrix(int var1, int var2, Complex[][] var3) {
        int var4 = var3.length;
        int var5 = var3[0].length;
        if (var1 + var4 - 1 >= this.nrow) {
            throw new IllegalArgumentException("Sub-matrix position is outside the row bounds of this Matrix");
        } else if (var2 + var5 - 1 >= this.ncol) {
            throw new IllegalArgumentException("Sub-matrix position is outside the column bounds of this Matrix");
        } else {
            int var6 = 0;
            boolean var7 = false;

            for(int var8 = 0; var8 < var4; ++var8) {
                int var10 = 0;

                for(int var9 = 0; var9 < var5; ++var9) {
                    this.matrix[var1 + var8][var2 + var9] = Complex.copy(var3[var6][var10]);
                    ++var10;
                }

                ++var6;
            }

        }
    }

    public void setSubMatrix(int[] var1, int[] var2, Complex[][] var3) {
        int var4 = var1.length;
        int var5 = var2.length;

        for(int var6 = 0; var6 < var4; ++var6) {
            for(int var7 = 0; var7 < var5; ++var7) {
                this.matrix[var1[var6]][var2[var7]] = Complex.copy(var3[var6][var7]);
            }
        }

    }

    public static ComplexMatrix identityMatrix(int var0) {
        ComplexMatrix var1 = new ComplexMatrix(var0, var0);

        for(int var2 = 0; var2 < var0; ++var2) {
            var1.matrix[var2][var2] = Complex.plusOne();
        }

        return var1;
    }

    public static ComplexMatrix scalarMatrix(int var0, Complex var1) {
        ComplexMatrix var2 = new ComplexMatrix(var0, var0);
        Complex[][] var3 = var2.getArrayReference();

        for(int var4 = 0; var4 < var0; ++var4) {
            for(int var5 = var4; var5 < var0; ++var5) {
                if (var4 == var5) {
                    var3[var4][var5] = Complex.copy(var1);
                }
            }
        }

        return var2;
    }

    public static ComplexMatrix diagonalMatrix(int var0, Complex[] var1) {
        if (var1.length != var0) {
            throw new IllegalArgumentException("matrix dimension differs from diagonal array length");
        } else {
            ComplexMatrix var2 = new ComplexMatrix(var0, var0);
            Complex[][] var3 = var2.getArrayReference();

            for(int var4 = 0; var4 < var0; ++var4) {
                for(int var5 = var4; var5 < var0; ++var5) {
                    if (var4 == var5) {
                        var3[var4][var5] = Complex.copy(var1[var4]);
                    }
                }
            }

            return var2;
        }
    }

    public static ComplexMatrix columnMatrix(Complex[] var0) {
        int var1 = var0.length;
        ComplexMatrix var2 = new ComplexMatrix(var1, 1);

        for(int var3 = 0; var3 < var1; ++var3) {
            var2.matrix[var3][0] = var0[var3];
        }

        return var2;
    }

    public static ComplexMatrix rowMatrix(Complex[] var0) {
        int var1 = var0.length;
        ComplexMatrix var2 = new ComplexMatrix(1, var1);

        for(int var3 = 0; var3 < var1; ++var3) {
            var2.matrix[0][var3] = var0[var3];
        }

        return var2;
    }

    public static ComplexMatrix toComplexColumnMatrix(Complex[] var0) {
        int var1 = var0.length;
        ComplexMatrix var2 = new ComplexMatrix(var1, 1);

        for(int var3 = 0; var3 < var1; ++var3) {
            var2.matrix[var3][0] = var0[var3].copy();
        }

        return var2;
    }

    public static ComplexMatrix toComplexColumnMatrix(double[] var0) {
        int var1 = var0.length;
        ComplexMatrix var2 = new ComplexMatrix(var1, 1);

        for(int var3 = 0; var3 < var1; ++var3) {
            var2.matrix[var3][0].reset(var0[var3], 0.0D);
        }

        return var2;
    }

    public static ComplexMatrix toComplexRowMatrix(Complex[] var0) {
        int var1 = var0.length;
        ComplexMatrix var2 = new ComplexMatrix(1, var1);

        for(int var3 = 0; var3 < var1; ++var3) {
            var2.matrix[0][var3] = var0[var3].copy();
        }

        return var2;
    }

    public static ComplexMatrix toComplexRowMatrix(double[] var0) {
        int var1 = var0.length;
        ComplexMatrix var2 = new ComplexMatrix(1, var1);

        for(int var3 = 0; var3 < var1; ++var3) {
            var2.matrix[0][var3].reset(var0[var3], 0.0D);
        }

        return var2;
    }

    public static ComplexMatrix toComplexMatrix(Matrix var0) {
        int var1 = var0.getNrow();
        int var2 = var0.getNcol();
        ComplexMatrix var3 = new ComplexMatrix(var1, var2);

        for(int var4 = 0; var4 < var1; ++var4) {
            for(int var5 = 0; var5 < var2; ++var5) {
                var3.matrix[var4][var5].reset(var0.getElementCopy(var4, var5), 0.0D);
            }
        }

        return var3;
    }

    public static ComplexMatrix toComplexMatrix(double[][] var0) {
        int var1 = var0.length;
        int var2 = var0[0].length;

        for(int var3 = 1; var3 < var1; ++var3) {
            if (var0[var3].length != var2) {
                throw new IllegalArgumentException("All rows must have the same length");
            }
        }

        ComplexMatrix var6 = new ComplexMatrix(var1, var2);

        for(int var4 = 0; var4 < var6.nrow; ++var4) {
            for(int var5 = 0; var5 < var6.ncol; ++var5) {
                var6.matrix[var4][var5].reset(var0[var4][var5], 0.0D);
            }
        }

        return var6;
    }

    public int getNrow() {
        return this.nrow;
    }

    public int getNcol() {
        return this.ncol;
    }

    public Complex[][] getArrayReference() {
        return this.matrix;
    }

    public Complex[][] getArray() {
        return this.matrix;
    }

    public Complex[][] getArrayPointer() {
        return this.matrix;
    }

    public Complex[][] getArrayCopy() {
        Complex[][] var1 = new Complex[this.nrow][this.ncol];

        for(int var2 = 0; var2 < this.nrow; ++var2) {
            for(int var3 = 0; var3 < this.ncol; ++var3) {
                var1[var2][var3] = Complex.copy(this.matrix[var2][var3]);
            }
        }

        return var1;
    }

    public Complex getElementReference(int var1, int var2) {
        return this.matrix[var1][var2];
    }

    public Complex getElementPointer(int var1, int var2) {
        return this.matrix[var1][var2];
    }

    public Complex getElementCopy(int var1, int var2) {
        return Complex.copy(this.matrix[var1][var2]);
    }

    public ComplexMatrix getSubMatrix(int var1, int var2, int var3, int var4) {
        if (var1 > var3) {
            throw new IllegalArgumentException("row indices inverted");
        } else if (var2 > var4) {
            throw new IllegalArgumentException("column indices inverted");
        } else if (var3 >= this.nrow) {
            throw new IllegalArgumentException("Sub-matrix position is outside the row bounds of this Matrix");
        } else if (var4 >= this.ncol) {
            throw new IllegalArgumentException("Sub-matrix position is outside the column bounds of this Matrix" + var1 + " " + var4);
        } else {
            int var5 = var3 - var1 + 1;
            int var6 = var4 - var2 + 1;
            ComplexMatrix var7 = new ComplexMatrix(var5, var6);
            Complex[][] var8 = var7.getArrayReference();

            for(int var9 = 0; var9 < var5; ++var9) {
                for(int var10 = 0; var10 < var6; ++var10) {
                    var8[var9][var10] = Complex.copy(this.matrix[var1 + var9][var2 + var10]);
                }
            }

            return var7;
        }
    }

    public ComplexMatrix getSubMatrix(int[] var1, int[] var2) {
        int var3 = var1.length;
        int var4 = var2.length;
        ComplexMatrix var5 = new ComplexMatrix(var3, var4);
        Complex[][] var6 = var5.getArrayReference();

        for(int var7 = 0; var7 < var3; ++var7) {
            for(int var8 = 0; var8 < var4; ++var8) {
                var6[var7][var8] = Complex.copy(this.matrix[var1[var7]][var2[var8]]);
            }
        }

        return var5;
    }

    public int[] getIndexReference() {
        return this.index;
    }

    public int[] getIndexPointer() {
        return this.index;
    }

    public int[] getIndexCopy() {
        int[] var1 = new int[this.nrow];

        for(int var2 = 0; var2 < this.nrow; ++var2) {
            var1[var2] = this.index[var2];
        }

        return var1;
    }

    public double getSwap() {
        return this.dswap;
    }

    public static ComplexMatrix copy(ComplexMatrix var0) {
        if (var0 == null) {
            return null;
        } else {
            int var1 = var0.getNrow();
            int var2 = var0.getNcol();
            Complex[][] var3 = var0.getArrayReference();
            ComplexMatrix var4 = new ComplexMatrix(var1, var2);
            var4.nrow = var1;
            var4.ncol = var2;
            Complex[][] var5 = var4.getArrayReference();

            int var6;
            for(var6 = 0; var6 < var1; ++var6) {
                for(int var7 = 0; var7 < var2; ++var7) {
                    var5[var6][var7] = Complex.copy(var3[var6][var7]);
                }
            }

            for(var6 = 0; var6 < var1; ++var6) {
                var4.index[var6] = var0.index[var6];
            }

            return var4;
        }
    }

    public ComplexMatrix copy() {
        if (this == null) {
            return null;
        } else {
            int var1 = this.nrow;
            int var2 = this.ncol;
            ComplexMatrix var3 = new ComplexMatrix(var1, var2);
            Complex[][] var4 = var3.getArrayReference();
            var3.nrow = var1;
            var3.ncol = var2;

            int var5;
            for(var5 = 0; var5 < var1; ++var5) {
                for(int var6 = 0; var6 < var2; ++var6) {
                    var4[var5][var6] = Complex.copy(this.matrix[var5][var6]);
                }
            }

            for(var5 = 0; var5 < var1; ++var5) {
                var3.index[var5] = this.index[var5];
            }

            return var3;
        }
    }

    public Object clone() {
        if (this == null) {
            return null;
        } else {
            int var1 = this.nrow;
            int var2 = this.ncol;
            ComplexMatrix var3 = new ComplexMatrix(var1, var2);
            Complex[][] var4 = var3.getArrayReference();
            var3.nrow = var1;
            var3.ncol = var2;

            int var5;
            for(var5 = 0; var5 < var1; ++var5) {
                for(int var6 = 0; var6 < var2; ++var6) {
                    var4[var5][var6] = Complex.copy(this.matrix[var5][var6]);
                }
            }

            for(var5 = 0; var5 < var1; ++var5) {
                var3.index[var5] = this.index[var5];
            }

            return var3;
        }
    }

    public ComplexMatrix plus(ComplexMatrix var1) {
        if (this.nrow == var1.nrow && this.ncol == var1.ncol) {
            int var2 = var1.nrow;
            int var3 = var1.ncol;
            ComplexMatrix var4 = new ComplexMatrix(var2, var3);
            Complex[][] var5 = var4.getArrayReference();

            for(int var6 = 0; var6 < var2; ++var6) {
                for(int var7 = 0; var7 < var3; ++var7) {
                    var5[var6][var7] = this.matrix[var6][var7].plus(var1.matrix[var6][var7]);
                }
            }

            return var4;
        } else {
            throw new IllegalArgumentException("Array dimensions do not agree");
        }
    }

    public ComplexMatrix plus(Complex[][] var1) {
        int var2 = var1.length;
        int var3 = var1[0].length;
        if (this.nrow == var2 && this.ncol == var3) {
            ComplexMatrix var4 = new ComplexMatrix(var2, var3);
            Complex[][] var5 = var4.getArrayReference();

            for(int var6 = 0; var6 < var2; ++var6) {
                for(int var7 = 0; var7 < var3; ++var7) {
                    var5[var6][var7] = this.matrix[var6][var7].plus(var1[var6][var7]);
                }
            }

            return var4;
        } else {
            throw new IllegalArgumentException("Array dimensions do not agree");
        }
    }

    public ComplexMatrix plus(Matrix var1) {
        int var2 = var1.getNrow();
        int var3 = var1.getNcol();
        if (this.nrow == var2 && this.ncol == var3) {
            ComplexMatrix var4 = new ComplexMatrix(var2, var3);
            Complex[][] var5 = var4.getArrayReference();

            for(int var6 = 0; var6 < var2; ++var6) {
                for(int var7 = 0; var7 < var3; ++var7) {
                    var5[var6][var7] = this.matrix[var6][var7].plus(var1.getElement(var6, var7));
                }
            }

            return var4;
        } else {
            throw new IllegalArgumentException("Array dimensions do not agree");
        }
    }

    public ComplexMatrix plus(double[][] var1) {
        int var2 = var1.length;
        int var3 = var1[0].length;
        if (this.nrow == var2 && this.ncol == var3) {
            ComplexMatrix var4 = new ComplexMatrix(var2, var3);
            Complex[][] var5 = var4.getArrayReference();

            for(int var6 = 0; var6 < var2; ++var6) {
                for(int var7 = 0; var7 < var3; ++var7) {
                    var5[var6][var7] = this.matrix[var6][var7].plus(var1[var6][var7]);
                }
            }

            return var4;
        } else {
            throw new IllegalArgumentException("Array dimensions do not agree");
        }
    }

    public static ComplexMatrix plus(ComplexMatrix var0, ComplexMatrix var1) {
        if (var0.nrow == var1.nrow && var0.ncol == var1.ncol) {
            int var2 = var0.nrow;
            int var3 = var0.ncol;
            ComplexMatrix var4 = new ComplexMatrix(var2, var3);
            Complex[][] var5 = var4.getArrayReference();

            for(int var6 = 0; var6 < var2; ++var6) {
                for(int var7 = 0; var7 < var3; ++var7) {
                    var5[var6][var7] = var0.matrix[var6][var7].plus(var1.matrix[var6][var7]);
                }
            }

            return var4;
        } else {
            throw new IllegalArgumentException("Array dimensions do not agree");
        }
    }

    public void plusEquals(ComplexMatrix var1) {
        if (this.nrow == var1.nrow && this.ncol == var1.ncol) {
            int var2 = var1.nrow;
            int var3 = var1.ncol;

            for(int var4 = 0; var4 < var2; ++var4) {
                for(int var5 = 0; var5 < var3; ++var5) {
                    this.matrix[var4][var5].plusEquals(var1.matrix[var4][var5]);
                }
            }

        } else {
            throw new IllegalArgumentException("Array dimensions do not agree");
        }
    }

    public ComplexMatrix minus(ComplexMatrix var1) {
        if (this.nrow == var1.nrow && this.ncol == var1.ncol) {
            int var2 = this.nrow;
            int var3 = this.ncol;
            ComplexMatrix var4 = new ComplexMatrix(var2, var3);
            Complex[][] var5 = var4.getArrayReference();

            for(int var6 = 0; var6 < var2; ++var6) {
                for(int var7 = 0; var7 < var3; ++var7) {
                    var5[var6][var7] = this.matrix[var6][var7].minus(var1.matrix[var6][var7]);
                }
            }

            return var4;
        } else {
            throw new IllegalArgumentException("Array dimensions do not agree");
        }
    }

    public ComplexMatrix minus(Complex[][] var1) {
        int var2 = var1.length;
        int var3 = var1[0].length;
        if (this.nrow == var2 && this.ncol == var3) {
            ComplexMatrix var4 = new ComplexMatrix(var2, var3);
            Complex[][] var5 = var4.getArrayReference();

            for(int var6 = 0; var6 < var2; ++var6) {
                for(int var7 = 0; var7 < var3; ++var7) {
                    var5[var6][var7] = this.matrix[var6][var7].minus(var1[var6][var7]);
                }
            }

            return var4;
        } else {
            throw new IllegalArgumentException("Array dimensions do not agree");
        }
    }

    public ComplexMatrix minus(Matrix var1) {
        int var2 = var1.getNrow();
        int var3 = var1.getNcol();
        if (this.nrow == var2 && this.ncol == var3) {
            ComplexMatrix var4 = new ComplexMatrix(var2, var3);
            Complex[][] var5 = var4.getArrayReference();

            for(int var6 = 0; var6 < var2; ++var6) {
                for(int var7 = 0; var7 < var3; ++var7) {
                    var5[var6][var7] = this.matrix[var6][var7].minus(var1.getElement(var6, var7));
                }
            }

            return var4;
        } else {
            throw new IllegalArgumentException("Array dimensions do not agree");
        }
    }

    public ComplexMatrix minus(double[][] var1) {
        int var2 = var1.length;
        int var3 = var1[0].length;
        if (this.nrow == var2 && this.ncol == var3) {
            ComplexMatrix var4 = new ComplexMatrix(var2, var3);
            Complex[][] var5 = var4.getArrayReference();

            for(int var6 = 0; var6 < var2; ++var6) {
                for(int var7 = 0; var7 < var3; ++var7) {
                    var5[var6][var7] = this.matrix[var6][var7].minus(var1[var6][var7]);
                }
            }

            return var4;
        } else {
            throw new IllegalArgumentException("Array dimensions do not agree");
        }
    }

    public static ComplexMatrix minus(ComplexMatrix var0, ComplexMatrix var1) {
        if (var0.nrow == var1.nrow && var0.ncol == var1.ncol) {
            int var2 = var0.nrow;
            int var3 = var0.ncol;
            ComplexMatrix var4 = new ComplexMatrix(var2, var3);
            Complex[][] var5 = var4.getArrayReference();

            for(int var6 = 0; var6 < var2; ++var6) {
                for(int var7 = 0; var7 < var3; ++var7) {
                    var5[var6][var7] = var0.matrix[var6][var7].minus(var1.matrix[var6][var7]);
                }
            }

            return var4;
        } else {
            throw new IllegalArgumentException("Array dimensions do not agree");
        }
    }

    public void minusEquals(ComplexMatrix var1) {
        if (this.nrow == var1.nrow && this.ncol == var1.ncol) {
            int var2 = var1.nrow;
            int var3 = var1.ncol;

            for(int var4 = 0; var4 < var2; ++var4) {
                for(int var5 = 0; var5 < var3; ++var5) {
                    this.matrix[var4][var5].minusEquals(var1.matrix[var4][var5]);
                }
            }

        } else {
            throw new IllegalArgumentException("Array dimensions do not agree");
        }
    }

    public ComplexMatrix times(ComplexMatrix var1) {
        if (this.ncol != var1.nrow) {
            throw new IllegalArgumentException("Nonconformable matrices");
        } else {
            ComplexMatrix var2 = new ComplexMatrix(this.nrow, var1.ncol);
            Complex[][] var3 = var2.getArrayReference();
            new Complex();

            for(int var5 = 0; var5 < this.nrow; ++var5) {
                for(int var6 = 0; var6 < var1.ncol; ++var6) {
                    Complex var4 = Complex.zero();

                    for(int var7 = 0; var7 < this.ncol; ++var7) {
                        var4.plusEquals(this.matrix[var5][var7].times(var1.matrix[var7][var6]));
                    }

                    var3[var5][var6] = Complex.copy(var4);
                }
            }

            return var2;
        }
    }

    public ComplexMatrix times(Complex[][] var1) {
        int var2 = var1.length;
        int var3 = var1[0].length;
        if (this.ncol != var2) {
            throw new IllegalArgumentException("Nonconformable matrices");
        } else {
            ComplexMatrix var4 = new ComplexMatrix(this.nrow, var3);
            Complex[][] var5 = var4.getArrayReference();
            new Complex();

            for(int var7 = 0; var7 < this.nrow; ++var7) {
                for(int var8 = 0; var8 < var3; ++var8) {
                    Complex var6 = Complex.zero();

                    for(int var9 = 0; var9 < this.ncol; ++var9) {
                        var6.plusEquals(this.matrix[var7][var9].times(var1[var9][var8]));
                    }

                    var5[var7][var8] = Complex.copy(var6);
                }
            }

            return var4;
        }
    }

    public ComplexMatrix times(Matrix var1) {
        int var2 = var1.getNrow();
        int var3 = var1.getNcol();
        if (this.ncol != var2) {
            throw new IllegalArgumentException("Nonconformable matrices");
        } else {
            ComplexMatrix var4 = new ComplexMatrix(this.nrow, var3);
            Complex[][] var5 = var4.getArrayReference();
            new Complex();

            for(int var7 = 0; var7 < this.nrow; ++var7) {
                for(int var8 = 0; var8 < var3; ++var8) {
                    Complex var6 = Complex.zero();

                    for(int var9 = 0; var9 < this.ncol; ++var9) {
                        var6.plusEquals(this.matrix[var7][var9].times(var1.getElement(var9, var8)));
                    }

                    var5[var7][var8] = Complex.copy(var6);
                }
            }

            return var4;
        }
    }

    public ComplexMatrix times(double[][] var1) {
        int var2 = var1.length;
        int var3 = var1[0].length;
        if (this.ncol != var2) {
            throw new IllegalArgumentException("Nonconformable matrices");
        } else {
            ComplexMatrix var4 = new ComplexMatrix(this.nrow, var3);
            Complex[][] var5 = var4.getArrayReference();
            new Complex();

            for(int var7 = 0; var7 < this.nrow; ++var7) {
                for(int var8 = 0; var8 < var3; ++var8) {
                    Complex var6 = Complex.zero();

                    for(int var9 = 0; var9 < this.ncol; ++var9) {
                        var6.plusEquals(this.matrix[var7][var9].times(var1[var9][var8]));
                    }

                    var5[var7][var8] = Complex.copy(var6);
                }
            }

            return var4;
        }
    }

    public ComplexMatrix times(Complex var1) {
        ComplexMatrix var2 = new ComplexMatrix(this.nrow, this.ncol);
        Complex[][] var3 = var2.getArrayReference();

        for(int var4 = 0; var4 < this.nrow; ++var4) {
            for(int var5 = 0; var5 < this.ncol; ++var5) {
                var3[var4][var5] = this.matrix[var4][var5].times(var1);
            }
        }

        return var2;
    }

    public ComplexMatrix times(double var1) {
        ComplexMatrix var3 = new ComplexMatrix(this.nrow, this.ncol);
        Complex[][] var4 = var3.getArrayReference();
        Complex var5 = new Complex(var1, 0.0D);

        for(int var6 = 0; var6 < this.nrow; ++var6) {
            for(int var7 = 0; var7 < this.ncol; ++var7) {
                var4[var6][var7] = this.matrix[var6][var7].times(var5);
            }
        }

        return var3;
    }

    public static ComplexMatrix times(ComplexMatrix var0, ComplexMatrix var1) {
        if (var0.ncol != var1.nrow) {
            throw new IllegalArgumentException("Nonconformable matrices");
        } else {
            ComplexMatrix var2 = new ComplexMatrix(var0.nrow, var1.ncol);
            Complex[][] var3 = var2.getArrayReference();
            new Complex();

            for(int var5 = 0; var5 < var0.nrow; ++var5) {
                for(int var6 = 0; var6 < var1.ncol; ++var6) {
                    Complex var4 = Complex.zero();

                    for(int var7 = 0; var7 < var0.ncol; ++var7) {
                        var4.plusEquals(var0.matrix[var5][var7].times(var1.matrix[var7][var6]));
                    }

                    var3[var5][var6] = Complex.copy(var4);
                }
            }

            return var2;
        }
    }

    public static ComplexMatrix times(ComplexMatrix var0, Complex var1) {
        ComplexMatrix var2 = new ComplexMatrix(var0.nrow, var0.ncol);
        Complex[][] var3 = var2.getArrayReference();

        for(int var4 = 0; var4 < var0.nrow; ++var4) {
            for(int var5 = 0; var5 < var0.ncol; ++var5) {
                var3[var4][var5] = var0.matrix[var4][var5].times(var1);
            }
        }

        return var2;
    }

    public static ComplexMatrix times(ComplexMatrix var0, double var1) {
        ComplexMatrix var3 = new ComplexMatrix(var0.nrow, var0.ncol);
        Complex[][] var4 = var3.getArrayReference();
        Complex var5 = new Complex(var1, 0.0D);

        for(int var6 = 0; var6 < var0.nrow; ++var6) {
            for(int var7 = 0; var7 < var0.ncol; ++var7) {
                var4[var6][var7] = var0.matrix[var6][var7].times(var5);
            }
        }

        return var3;
    }

    public void timesEquals(ComplexMatrix var1) {
        if (this.ncol != var1.nrow) {
            throw new IllegalArgumentException("Nonconformable matrices");
        } else {
            ComplexMatrix var2 = new ComplexMatrix(this.nrow, var1.ncol);
            Complex[][] var3 = var2.getArrayReference();
            new Complex();

            int var5;
            int var6;
            for(var5 = 0; var5 < this.nrow; ++var5) {
                for(var6 = 0; var6 < var1.ncol; ++var6) {
                    Complex var4 = Complex.zero();

                    for(int var7 = 0; var7 < this.ncol; ++var7) {
                        var4.plusEquals(this.matrix[var5][var7].times(var1.matrix[var7][var6]));
                    }

                    var3[var5][var6] = Complex.copy(var4);
                }
            }

            this.nrow = var2.nrow;
            this.ncol = var2.ncol;

            for(var5 = 0; var5 < this.nrow; ++var5) {
                for(var6 = 0; var6 < this.ncol; ++var6) {
                    this.matrix[var5][var6] = var2.matrix[var5][var6];
                }
            }

        }
    }

    public void timesEquals(Complex var1) {
        for(int var2 = 0; var2 < this.nrow; ++var2) {
            for(int var3 = 0; var3 < this.ncol; ++var3) {
                this.matrix[var2][var3].timesEquals(var1);
            }
        }

    }

    public void timesEquals(double var1) {
        Complex var3 = new Complex(var1, 0.0D);

        for(int var4 = 0; var4 < this.nrow; ++var4) {
            for(int var5 = 0; var5 < this.ncol; ++var5) {
                this.matrix[var4][var5].timesEquals(var3);
            }
        }

    }

    public ComplexMatrix over(ComplexMatrix var1) {
        if (this.nrow == var1.nrow && this.ncol == var1.ncol) {
            return this.times(var1.inverse());
        } else {
            throw new IllegalArgumentException("Array dimensions do not agree");
        }
    }

    public ComplexMatrix over(Complex[][] var1) {
        int var2 = var1.length;
        int var3 = var1[0].length;
        if (this.nrow == var2 && this.ncol == var3) {
            ComplexMatrix var4 = new ComplexMatrix(var1);
            return this.times(var4.inverse());
        } else {
            throw new IllegalArgumentException("Array dimensions do not agree");
        }
    }

    public ComplexMatrix over(Matrix var1) {
        ComplexMatrix var2 = toComplexMatrix(var1);
        return this.over(var2);
    }

    public ComplexMatrix over(double[][] var1) {
        ComplexMatrix var2 = toComplexMatrix(var1);
        return this.over(var2);
    }

    public ComplexMatrix over(ComplexMatrix var1, ComplexMatrix var2) {
        if (var1.nrow == var2.nrow && var1.ncol == var2.ncol) {
            return var1.times(var2.inverse());
        } else {
            throw new IllegalArgumentException("Array dimensions do not agree");
        }
    }

    public void overEquals(ComplexMatrix var1) {
        if (this.nrow == var1.nrow && this.ncol == var1.ncol) {
            ComplexMatrix var2 = new ComplexMatrix(var1);
            this.timesEquals(var2.inverse());
        } else {
            throw new IllegalArgumentException("Array dimensions do not agree");
        }
    }

    public ComplexMatrix inverse() {
        int var1 = this.nrow;
        if (var1 != this.ncol) {
            throw new IllegalArgumentException("Matrix is not square");
        } else {
            ComplexMatrix var2 = new ComplexMatrix(var1, var1);
            Complex[][] var3;
            if (var1 == 1) {
                var3 = this.getArrayCopy();
                if (var3[0][0].isZero()) {
                    throw new IllegalArgumentException("Matrix is singular");
                }

                var3[0][0] = Complex.plusOne().over(var3[0][0]);
                var2 = new ComplexMatrix(var3);
            } else {
                Complex[][] var5;
                if (var1 == 2) {
                    var3 = this.getArrayCopy();
                    Complex var4 = var3[0][0].times(var3[1][1]).minus(var3[0][1].times(var3[1][0]));
                    if (var4.isZero()) {
                        throw new IllegalArgumentException("Matrix is singular");
                    }

                    var5 = Complex.twoDarray(2, 2);
                    var5[0][0] = var3[1][1].over(var4);
                    var5[1][1] = var3[0][0].over(var4);
                    var5[1][0] = var3[1][0].negate().over(var4);
                    var5[0][1] = var3[0][1].negate().over(var4);
                    var2 = new ComplexMatrix(var5);
                } else {
                    Complex[] var9 = new Complex[var1];
                    Complex[] var10 = new Complex[var1];
                    var5 = var2.getArrayReference();
                    ComplexMatrix var6 = this.luDecomp();

                    for(int var7 = 0; var7 < var1; ++var7) {
                        int var8;
                        for(var8 = 0; var8 < var1; ++var8) {
                            var9[var8] = Complex.zero();
                        }

                        var9[var7] = Complex.plusOne();
                        var10 = var6.luBackSub(var9);

                        for(var8 = 0; var8 < var1; ++var8) {
                            var5[var8][var7] = Complex.copy(var10[var8]);
                        }
                    }
                }
            }

            return var2;
        }
    }

    public static ComplexMatrix inverse(ComplexMatrix var0) {
        int var1 = var0.nrow;
        if (var1 != var0.ncol) {
            throw new IllegalArgumentException("Matrix is not square");
        } else {
            ComplexMatrix var2 = new ComplexMatrix(var1, var1);
            Complex[][] var3;
            if (var1 == 1) {
                var3 = var0.getArrayCopy();
                if (var3[0][0].isZero()) {
                    throw new IllegalArgumentException("Matrix is singular");
                }

                var3[0][0] = Complex.plusOne().over(var3[0][0]);
                var2 = new ComplexMatrix(var3);
            } else {
                Complex[][] var5;
                if (var1 == 2) {
                    var3 = var0.getArrayCopy();
                    Complex var4 = var3[0][0].times(var3[1][1]).minus(var3[0][1].times(var3[1][0]));
                    if (var4.isZero()) {
                        throw new IllegalArgumentException("Matrix is singular");
                    }

                    var5 = Complex.twoDarray(2, 2);
                    var5[0][0] = var3[1][1].over(var4);
                    var5[1][1] = var3[0][0].over(var4);
                    var5[1][0] = var3[1][0].negate().over(var4);
                    var5[0][1] = var3[0][1].negate().over(var4);
                    var2 = new ComplexMatrix(var5);
                } else {
                    Complex[] var9 = new Complex[var1];
                    Complex[] var10 = new Complex[var1];
                    var5 = var2.getArrayReference();
                    ComplexMatrix var6 = var0.luDecomp();

                    for(int var7 = 0; var7 < var1; ++var7) {
                        int var8;
                        for(var8 = 0; var8 < var1; ++var8) {
                            var9[var8] = Complex.zero();
                        }

                        var9[var7] = Complex.plusOne();
                        var10 = var6.luBackSub(var9);

                        for(var8 = 0; var8 < var1; ++var8) {
                            var5[var8][var7] = Complex.copy(var10[var8]);
                        }
                    }
                }
            }

            return var2;
        }
    }

    public ComplexMatrix transpose() {
        ComplexMatrix var1 = new ComplexMatrix(this.ncol, this.nrow);
        Complex[][] var2 = var1.getArrayReference();

        for(int var3 = 0; var3 < this.ncol; ++var3) {
            for(int var4 = 0; var4 < this.nrow; ++var4) {
                var2[var3][var4] = Complex.copy(this.matrix[var4][var3]);
            }
        }

        return var1;
    }

    public static ComplexMatrix transpose(ComplexMatrix var0) {
        ComplexMatrix var1 = new ComplexMatrix(var0.ncol, var0.nrow);
        Complex[][] var2 = var1.getArrayReference();

        for(int var3 = 0; var3 < var0.ncol; ++var3) {
            for(int var4 = 0; var4 < var0.nrow; ++var4) {
                var2[var3][var4] = Complex.copy(var0.matrix[var4][var3]);
            }
        }

        return var1;
    }

    public ComplexMatrix conjugate() {
        ComplexMatrix var1 = copy(this);

        for(int var2 = 0; var2 < this.nrow; ++var2) {
            for(int var3 = 0; var3 < this.ncol; ++var3) {
                var1.matrix[var2][var3] = this.matrix[var2][var3].conjugate();
            }
        }

        return var1;
    }

    public static ComplexMatrix conjugate(ComplexMatrix var0) {
        ComplexMatrix var1 = copy(var0);

        for(int var2 = 0; var2 < var0.nrow; ++var2) {
            for(int var3 = 0; var3 < var0.ncol; ++var3) {
                var1.matrix[var2][var3] = var0.matrix[var2][var3].conjugate();
            }
        }

        return var1;
    }

    public ComplexMatrix adjoin() {
        ComplexMatrix var1 = copy(this);
        var1 = var1.transpose();
        var1 = var1.conjugate();
        return var1;
    }

    public ComplexMatrix adjoin(ComplexMatrix var1) {
        ComplexMatrix var2 = copy(var1);
        var2 = var2.transpose();
        var2 = var2.conjugate();
        return var2;
    }

    public ComplexMatrix opposite() {
        ComplexMatrix var1 = copy(this);

        for(int var2 = 0; var2 < this.nrow; ++var2) {
            for(int var3 = 0; var3 < this.ncol; ++var3) {
                var1.matrix[var2][var3] = this.matrix[var2][var3].times(Complex.minusOne());
            }
        }

        return var1;
    }

    public static ComplexMatrix opposite(ComplexMatrix var0) {
        ComplexMatrix var1 = copy(var0);

        for(int var2 = 0; var2 < var0.nrow; ++var2) {
            for(int var3 = 0; var3 < var0.ncol; ++var3) {
                var1.matrix[var2][var3] = var0.matrix[var2][var3].times(Complex.minusOne());
            }
        }

        return var1;
    }

    public Complex trace() {
        Complex var1 = new Complex(0.0D, 0.0D);

        for(int var2 = 0; var2 < Math.min(this.ncol, this.ncol); ++var2) {
            var1.plusEquals(this.matrix[var2][var2]);
        }

        return var1;
    }

    public static Complex trace(ComplexMatrix var0) {
        Complex var1 = new Complex(0.0D, 0.0D);

        for(int var2 = 0; var2 < Math.min(var0.ncol, var0.ncol); ++var2) {
            var1.plusEquals(var0.matrix[var2][var2]);
        }

        return var1;
    }

    public Complex determinant() {
        int var1 = this.nrow;
        if (var1 != this.ncol) {
            throw new IllegalArgumentException("Matrix is not square");
        } else {
            Complex var2 = new Complex();
            ComplexMatrix var3 = this.luDecomp();
            var2.reset(var3.dswap, 0.0D);

            for(int var4 = 0; var4 < var1; ++var4) {
                var2.timesEquals(var3.matrix[var4][var4]);
            }

            return var2;
        }
    }

    public static Complex determinant(ComplexMatrix var0) {
        int var1 = var0.nrow;
        if (var1 != var0.ncol) {
            throw new IllegalArgumentException("Matrix is not square");
        } else {
            Complex var2 = new Complex();
            ComplexMatrix var3 = var0.luDecomp();
            var2.reset(var3.dswap, 0.0D);

            for(int var4 = 0; var4 < var1; ++var4) {
                var2.timesEquals(var3.matrix[var4][var4]);
            }

            return var2;
        }
    }

    public Complex logDeterminant() {
        int var1 = this.nrow;
        if (var1 != this.ncol) {
            throw new IllegalArgumentException("Matrix is not square");
        } else {
            Complex var2 = new Complex();
            ComplexMatrix var3 = this.luDecomp();
            var2.reset(var3.dswap, 0.0D);
            var2 = Complex.log(var2);

            for(int var4 = 0; var4 < var1; ++var4) {
                var2.plusEquals(Complex.log(var3.matrix[var4][var4]));
            }

            return var2;
        }
    }

    public static Complex logDeterminant(ComplexMatrix var0) {
        int var1 = var0.nrow;
        if (var1 != var0.ncol) {
            throw new IllegalArgumentException("Matrix is not square");
        } else {
            Complex var2 = new Complex();
            ComplexMatrix var3 = var0.luDecomp();
            var2.reset(var3.dswap, 0.0D);
            var2 = Complex.log(var2);

            for(int var4 = 0; var4 < var1; ++var4) {
                var2.plusEquals(Complex.log(var3.matrix[var4][var4]));
            }

            return var2;
        }
    }

    public ComplexMatrix cofactor() {
        Complex[][] var1 = Complex.twoDarray(this.nrow, this.ncol);

        for(int var2 = 0; var2 < this.nrow; ++var2) {
            for(int var3 = 0; var3 < this.ncol; ++var3) {
                var1[var2][var3] = this.cofactor(var2, var3);
            }
        }

        return new ComplexMatrix(var1);
    }

    public Complex cofactor(int var1, int var2) {
        if (var1 >= 0 && var1 < this.nrow) {
            if (var2 >= 0 && var2 < this.ncol) {
                int[] var3 = new int[this.nrow - 1];
                int[] var4 = new int[this.ncol - 1];
                int var5 = 0;

                int var6;
                for(var6 = 0; var6 < this.nrow; ++var6) {
                    if (var6 != var1) {
                        var3[var5] = var6;
                        ++var5;
                    }
                }

                var5 = 0;

                for(var6 = 0; var6 < this.ncol; ++var6) {
                    if (var6 != var2) {
                        var4[var5] = var6;
                        ++var5;
                    }
                }

                ComplexMatrix var8 = this.getSubMatrix(var3, var4);
                Complex var7 = var8.determinant();
                return var7.times(Math.pow(-1.0D, (double)(var1 + var2)));
            } else {
                throw new IllegalArgumentException("The entered column index, " + var2 + " must lie between 0 and " + (this.ncol - 1) + " inclusive");
            }
        } else {
            throw new IllegalArgumentException("The entered row index, " + var1 + " must lie between 0 and " + (this.nrow - 1) + " inclusive");
        }
    }

    public ComplexMatrix reducedRowEchelonForm() {
        Complex[][] var1 = Complex.twoDarray(this.nrow, this.ncol);

        int var2;
        int var3;
        for(var2 = 0; var2 < this.nrow; ++var2) {
            for(var3 = 0; var3 < this.ncol; ++var3) {
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
                    for(var5 = 0; var5 < this.nrow; ++var5) {
                        for(int var11 = 0; var11 < this.ncol; ++var11) {
                            if (var1[var5][var11].getReal() == -0.0D) {
                                var1[var5][var11].reset(0.0D, var1[var5][var11].getImag());
                            }

                            if (var1[var5][var11].getImag() == -0.0D) {
                                var1[var5][var11].reset(var1[var5][var11].getReal(), 0.0D);
                            }
                        }
                    }

                    return new ComplexMatrix(var1);
                }

                var5 = var3;
                var6 = true;

                while(var6 && var1[var5][var2].equals(Complex.zero())) {
                    ++var5;
                    if (var5 == this.nrow) {
                        var5 = var3;
                        ++var2;
                        if (var2 == this.ncol) {
                            var6 = false;
                        }
                    }
                }

                if (var6) {
                    Complex[] var7 = var1[var3];
                    var1[var3] = var1[var5];
                    var1[var5] = var7;
                    Complex var8 = var1[var3][var2];

                    int var9;
                    for(var9 = 0; var9 < this.ncol; ++var9) {
                        var1[var3][var9] = var1[var3][var9].over(var8);
                    }

                    var9 = 0;

                    while(true) {
                        if (var9 >= this.nrow) {
                            ++var2;
                            if (var2 >= this.ncol) {
                                var4 = false;
                            }
                            break;
                        }

                        if (var9 != var3) {
                            var8 = var1[var9][var2];

                            for(int var10 = 0; var10 < this.ncol; ++var10) {
                                var1[var9][var10] = var1[var9][var10].minus(var8.times(var1[var3][var10]));
                            }
                        }

                        ++var9;
                    }
                }

                ++var3;
            } while(var3 < this.nrow && var6);

            var4 = false;
        }
    }

    public double frobeniusNorm() {
        double var1 = 0.0D;

        for(int var3 = 0; var3 < this.nrow; ++var3) {
            for(int var4 = 0; var4 < this.ncol; ++var4) {
                var1 = Fmath.hypot(var1, Complex.abs(this.matrix[var3][var4]));
            }
        }

        return var1;
    }

    public double oneNorm() {
        double var1 = 0.0D;
        double var3 = 0.0D;

        for(int var5 = 0; var5 < this.nrow; ++var5) {
            var3 = 0.0D;

            for(int var6 = 0; var6 < this.ncol; ++var6) {
                var3 += Complex.abs(this.matrix[var5][var6]);
            }

            var1 = Math.max(var1, var3);
        }

        return var1;
    }

    public double infinityNorm() {
        double var1 = 0.0D;
        double var3 = 0.0D;

        for(int var5 = 0; var5 < this.nrow; ++var5) {
            var3 = 0.0D;

            for(int var6 = 0; var6 < this.ncol; ++var6) {
                var3 += Complex.abs(this.matrix[var5][var6]);
            }

            var1 = Math.max(var1, var3);
        }

        return var1;
    }

    public ComplexMatrix luDecomp() {
        if (this.nrow != this.ncol) {
            throw new IllegalArgumentException("A matrix is not square");
        } else {
            int var1 = this.nrow;
            int var2 = 0;
            double var3 = 0.0D;
            double var5 = 0.0D;
            double var7 = 0.0D;
            double[] var9 = new double[var1];
            new Complex();
            new Complex();
            ComplexMatrix var12 = copy(this);
            Complex[][] var13 = var12.getArrayReference();
            var12.dswap = 1.0D;

            int var14;
            int var15;
            for(var14 = 0; var14 < var1; ++var14) {
                var7 = 0.0D;

                for(var15 = 0; var15 < var1; ++var15) {
                    if ((var5 = Complex.abs(var13[var14][var15])) > var7) {
                        var7 = var5;
                    }
                }

                if (var7 == 0.0D) {
                    throw new ArithmeticException("Singular matrix");
                }

                var9[var14] = 1.0D / var7;
            }

            for(var14 = 0; var14 < var1; ++var14) {
                Complex var10;
                int var16;
                for(var15 = 0; var15 < var14; ++var15) {
                    var10 = Complex.copy(var13[var15][var14]);

                    for(var16 = 0; var16 < var15; ++var16) {
                        var10.minusEquals(var13[var15][var16].times(var13[var16][var14]));
                    }

                    var13[var15][var14] = Complex.copy(var10);
                }

                var7 = 0.0D;

                for(var15 = var14; var15 < var1; ++var15) {
                    var10 = Complex.copy(var13[var15][var14]);

                    for(var16 = 0; var16 < var14; ++var16) {
                        var10.minusEquals(var13[var15][var16].times(var13[var16][var14]));
                    }

                    var13[var15][var14] = Complex.copy(var10);
                    if ((var3 = var9[var15] * Complex.abs(var10)) >= var7) {
                        var7 = var3;
                        var2 = var15;
                    }
                }

                Complex var11;
                if (var14 != var2) {
                    for(var15 = 0; var15 < var1; ++var15) {
                        var11 = Complex.copy(var13[var2][var15]);
                        var13[var2][var15] = Complex.copy(var13[var14][var15]);
                        var13[var14][var15] = Complex.copy(var11);
                    }

                    var12.dswap = -var12.dswap;
                    var9[var2] = var9[var14];
                }

                var12.index[var14] = var2;
                if (var13[var14][var14].isZero()) {
                    var13[var14][var14].reset(1.0E-30D, 1.0E-30D);
                }

                if (var14 != var1 - 1) {
                    var11 = Complex.over(1.0D, var13[var14][var14]);

                    for(var15 = var14 + 1; var15 < var1; ++var15) {
                        var13[var15][var14].timesEquals(var11);
                    }
                }
            }

            return var12;
        }
    }

    public Complex[] luBackSub(Complex[] var1) {
        int var2 = 0;
        boolean var3 = false;
        int var4 = var1.length;
        if (var4 != this.ncol) {
            throw new IllegalArgumentException("vector length is not equal to matrix dimension");
        } else if (this.ncol != this.nrow) {
            throw new IllegalArgumentException("matrix is not square");
        } else {
            new Complex();
            Complex[] var6 = new Complex[var4];

            int var7;
            for(var7 = 0; var7 < var4; ++var7) {
                var6[var7] = Complex.copy(var1[var7]);
            }

            Complex var5;
            int var8;
            for(var7 = 0; var7 < var4; ++var7) {
                int var9 = this.index[var7];
                var5 = Complex.copy(var6[var9]);
                var6[var9] = Complex.copy(var6[var7]);
                if (var2 == 0) {
                    for(var8 = var2; var8 <= var7 - 1; ++var8) {
                        var5.minusEquals(this.matrix[var7][var8].times(var6[var8]));
                    }
                } else if (var5.isZero()) {
                    var2 = var7;
                }

                var6[var7] = Complex.copy(var5);
            }

            for(var7 = var4 - 1; var7 >= 0; --var7) {
                var5 = Complex.copy(var6[var7]);

                for(var8 = var7 + 1; var8 < var4; ++var8) {
                    var5.minusEquals(this.matrix[var7][var8].times(var6[var8]));
                }

                var6[var7] = var5.over(this.matrix[var7][var7]);
            }

            return var6;
        }
    }

    public Complex[] solveLinearSet(Complex[] var1) {
        ComplexMatrix var2 = this.luDecomp();
        return var2.luBackSub(var1);
    }
}
