//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.circuits;

import com.flanagan.complex.Complex;
import com.flanagan.complex.ComplexMatrix;
import com.flanagan.math.Fmath;
import com.flanagan.math.Matrix;

public class PhasorMatrix {
    private int nrow = 0;
    private int ncol = 0;
    private Phasor[][] matrix = (Phasor[][])null;
    private int[] index = null;
    private double dswap = 1.0D;
    private static final double TINY = 1.0E-30D;

    public PhasorMatrix(int var1, int var2) {
        this.nrow = var1;
        this.ncol = var2;
        this.matrix = Phasor.twoDarray(var1, var2);
        this.index = new int[var1];

        for(int var3 = 0; var3 < var1; this.index[var3] = var3++) {
            ;
        }

        this.dswap = 1.0D;
    }

    public PhasorMatrix(int var1, int var2, Phasor var3) {
        this.nrow = var1;
        this.ncol = var2;
        this.matrix = Phasor.twoDarray(var1, var2, var3);
        this.index = new int[var1];

        for(int var4 = 0; var4 < var1; this.index[var4] = var4++) {
            ;
        }

        this.dswap = 1.0D;
    }

    public PhasorMatrix(Phasor[][] var1) {
        this.nrow = var1.length;
        this.ncol = var1[0].length;

        int var2;
        for(var2 = 0; var2 < this.nrow; ++var2) {
            if (var1[var2].length != this.ncol) {
                throw new IllegalArgumentException("All rows must have the same length");
            }
        }

        this.matrix = var1;
        this.index = new int[this.nrow];

        for(var2 = 0; var2 < this.nrow; this.index[var2] = var2++) {
            ;
        }

        this.dswap = 1.0D;
    }

    public PhasorMatrix(PhasorMatrix var1) {
        this.nrow = var1.nrow;
        this.ncol = var1.ncol;
        this.matrix = var1.matrix;
        this.index = var1.index;
        this.dswap = var1.dswap;
    }

    public void setTwoDarray(Phasor[][] var1) {
        if (this.nrow != var1.length) {
            throw new IllegalArgumentException("row length of this PhasorMatrix differs from that of the 2D array argument");
        } else if (this.ncol != var1[0].length) {
            throw new IllegalArgumentException("column length of this PhasorMatrix differs from that of the 2D array argument");
        } else {
            for(int var2 = 0; var2 < this.nrow; ++var2) {
                if (var1[var2].length != this.ncol) {
                    throw new IllegalArgumentException("All rows must have the same length");
                }

                for(int var3 = 0; var3 < this.ncol; ++var3) {
                    this.matrix[var2][var3] = Phasor.copy(var1[var2][var3]);
                }
            }

        }
    }

    public void setElement(int var1, int var2, Phasor var3) {
        this.matrix[var1][var2] = Phasor.copy(var3);
    }

    public void setElement(int var1, int var2, double var3, double var5) {
        this.matrix[var1][var2].reset(var3, var5);
    }

    public void setSubMatrix(int var1, int var2, Phasor[][] var3) {
        int var4 = var3.length;
        int var5 = var3[0].length;
        if (var1 > var4) {
            throw new IllegalArgumentException("row indices inverted");
        } else if (var2 > var5) {
            throw new IllegalArgumentException("column indices inverted");
        } else {
            int var6 = var4 - var1 + 1;
            int var7 = var5 - var2 + 1;

            for(int var8 = 0; var8 < var6; ++var8) {
                for(int var9 = 0; var9 < var7; ++var9) {
                    this.matrix[var1 + var8][var2 + var9] = Phasor.copy(var3[var8][var9]);
                }
            }

        }
    }

    public void setSubMatrix(int var1, int var2, int var3, int var4, Phasor[][] var5) {
        if (var1 + var3 - 1 >= this.nrow) {
            throw new IllegalArgumentException("Sub-matrix position is outside the row bounds of this Matrix");
        } else if (var2 + var4 - 1 >= this.ncol) {
            throw new IllegalArgumentException("Sub-matrix position is outside the column bounds of this Matrix");
        } else {
            int var6 = var3 - var1 + 1;
            int var7 = var4 - var2 + 1;

            for(int var8 = 0; var8 < var6; ++var8) {
                for(int var9 = 0; var9 < var7; ++var9) {
                    this.matrix[var1 + var8][var2 + var9] = Phasor.copy(var5[var8][var9]);
                }
            }

        }
    }

    public void setSubMatrix(int[] var1, int[] var2, Phasor[][] var3) {
        int var4 = var1.length;
        int var5 = var2.length;

        for(int var6 = 0; var6 < var4; ++var6) {
            for(int var7 = 0; var7 < var5; ++var7) {
                this.matrix[var1[var6]][var2[var7]] = Phasor.copy(var3[var6][var7]);
            }
        }

    }

    public static PhasorMatrix identityMatrix(int var0) {
        PhasorMatrix var1 = new PhasorMatrix(var0, var0);

        for(int var2 = 0; var2 < var0; ++var2) {
            var1.matrix[var2][var2] = Phasor.plusOne();
        }

        return var1;
    }

    public static PhasorMatrix scalarMatrix(int var0, Phasor var1) {
        PhasorMatrix var2 = new PhasorMatrix(var0, var0);
        Phasor[][] var3 = var2.getArrayReference();

        for(int var4 = 0; var4 < var0; ++var4) {
            for(int var5 = var4; var5 < var0; ++var5) {
                if (var4 == var5) {
                    var3[var4][var5] = Phasor.copy(var1);
                }
            }
        }

        return var2;
    }

    public static PhasorMatrix diagonalMatrix(int var0, Phasor[] var1) {
        if (var1.length != var0) {
            throw new IllegalArgumentException("matrix dimension differs from diagonal array length");
        } else {
            PhasorMatrix var2 = new PhasorMatrix(var0, var0);
            Phasor[][] var3 = var2.getArrayReference();

            for(int var4 = 0; var4 < var0; ++var4) {
                for(int var5 = var4; var5 < var0; ++var5) {
                    if (var4 == var5) {
                        var3[var4][var5] = Phasor.copy(var1[var4]);
                    }
                }
            }

            return var2;
        }
    }

    public static PhasorMatrix columnMatrix(Phasor[] var0) {
        int var1 = var0.length;
        PhasorMatrix var2 = new PhasorMatrix(var1, 1);

        for(int var3 = 0; var3 < var1; ++var3) {
            var2.matrix[var3][0] = var0[var3];
        }

        return var2;
    }

    public static PhasorMatrix rowMatrix(Phasor[] var0) {
        int var1 = var0.length;
        PhasorMatrix var2 = new PhasorMatrix(1, var1);

        for(int var3 = 0; var3 < var1; ++var3) {
            var2.matrix[0][var3] = var0[var3];
        }

        return var2;
    }

    public int getNrow() {
        return this.nrow;
    }

    public int getNcol() {
        return this.ncol;
    }

    public Phasor[][] getArrayReference() {
        return this.matrix;
    }

    public Phasor[][] getArray() {
        return this.matrix;
    }

    public Phasor[][] getArrayPointer() {
        return this.matrix;
    }

    public Phasor[][] getArrayCopy() {
        Phasor[][] var1 = new Phasor[this.nrow][this.ncol];

        for(int var2 = 0; var2 < this.nrow; ++var2) {
            for(int var3 = 0; var3 < this.ncol; ++var3) {
                var1[var2][var3] = Phasor.copy(this.matrix[var2][var3]);
            }
        }

        return var1;
    }

    public Phasor getElementReference(int var1, int var2) {
        return this.matrix[var1][var2];
    }

    public Phasor getElementPointer(int var1, int var2) {
        return this.matrix[var1][var2];
    }

    public Phasor getElementCopy(int var1, int var2) {
        return Phasor.copy(this.matrix[var1][var2]);
    }

    public PhasorMatrix getSubMatrix(int var1, int var2, int var3, int var4) {
        if (var1 + var3 - 1 >= this.nrow) {
            throw new IllegalArgumentException("Sub-matrix position is outside the row bounds of this Matrix");
        } else if (var2 + var4 - 1 >= this.ncol) {
            throw new IllegalArgumentException("Sub-matrix position is outside the column bounds of this Matrix");
        } else {
            int var5 = var3 - var1 + 1;
            int var6 = var4 - var2 + 1;
            PhasorMatrix var7 = new PhasorMatrix(var5, var6);
            Phasor[][] var8 = var7.getArrayReference();

            for(int var9 = 0; var9 < var5; ++var9) {
                for(int var10 = 0; var10 < var6; ++var10) {
                    var8[var9][var10] = Phasor.copy(this.matrix[var1 + var9][var2 + var10]);
                }
            }

            return var7;
        }
    }

    public PhasorMatrix getSubMatrix(int[] var1, int[] var2) {
        int var3 = var1.length;
        int var4 = var2.length;
        PhasorMatrix var5 = new PhasorMatrix(var3, var4);
        Phasor[][] var6 = var5.getArrayReference();

        for(int var7 = 0; var7 < var3; ++var7) {
            for(int var8 = 0; var8 < var4; ++var8) {
                var6[var7][var8] = Phasor.copy(this.matrix[var1[var7]][var2[var8]]);
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

    public static PhasorMatrix copy(PhasorMatrix var0) {
        if (var0 == null) {
            return null;
        } else {
            int var1 = var0.getNrow();
            int var2 = var0.getNcol();
            Phasor[][] var3 = var0.getArrayReference();
            PhasorMatrix var4 = new PhasorMatrix(var1, var2);
            var4.nrow = var1;
            var4.ncol = var2;
            Phasor[][] var5 = var4.getArrayReference();

            int var6;
            for(var6 = 0; var6 < var1; ++var6) {
                for(int var7 = 0; var7 < var2; ++var7) {
                    var5[var6][var7] = Phasor.copy(var3[var6][var7]);
                }
            }

            for(var6 = 0; var6 < var1; ++var6) {
                var4.index[var6] = var0.index[var6];
            }

            return var4;
        }
    }

    public PhasorMatrix copy() {
        if (this == null) {
            return null;
        } else {
            int var1 = this.nrow;
            int var2 = this.ncol;
            PhasorMatrix var3 = new PhasorMatrix(var1, var2);
            Phasor[][] var4 = var3.getArrayReference();
            var3.nrow = var1;
            var3.ncol = var2;

            int var5;
            for(var5 = 0; var5 < var1; ++var5) {
                for(int var6 = 0; var6 < var2; ++var6) {
                    var4[var5][var6] = Phasor.copy(this.matrix[var5][var6]);
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
            PhasorMatrix var3 = new PhasorMatrix(var1, var2);
            Phasor[][] var4 = var3.getArrayReference();
            var3.nrow = var1;
            var3.ncol = var2;

            int var5;
            for(var5 = 0; var5 < var1; ++var5) {
                for(int var6 = 0; var6 < var2; ++var6) {
                    var4[var5][var6] = Phasor.copy(this.matrix[var5][var6]);
                }
            }

            for(var5 = 0; var5 < var1; ++var5) {
                var3.index[var5] = this.index[var5];
            }

            return var3;
        }
    }

    public static PhasorMatrix toPhasorRowMatrix(Phasor[] var0) {
        int var1 = var0.length;
        PhasorMatrix var2 = new PhasorMatrix(1, var1);

        for(int var3 = 0; var3 < var1; ++var3) {
            var2.matrix[0][var3] = var0[var3].copy();
        }

        return var2;
    }

    public static PhasorMatrix toPhasorRowMatrix(Complex[] var0) {
        int var1 = var0.length;
        PhasorMatrix var2 = new PhasorMatrix(1, var1);

        for(int var3 = 0; var3 < var1; ++var3) {
            var2.matrix[0][var3] = Phasor.toPhasor(var0[var3]).copy();
        }

        return var2;
    }

    public static PhasorMatrix toPhasorRowMatrix(double[] var0) {
        int var1 = var0.length;
        PhasorMatrix var2 = new PhasorMatrix(1, var1);

        for(int var3 = 0; var3 < var1; ++var3) {
            var2.matrix[0][var3] = new Phasor(var0[var3], 0.0D);
        }

        return var2;
    }

    public static PhasorMatrix toPhasorColumnMatrix(Phasor[] var0) {
        int var1 = var0.length;
        PhasorMatrix var2 = new PhasorMatrix(var1, 1);

        for(int var3 = 0; var3 < var1; ++var3) {
            var2.matrix[var3][0] = var0[var3].copy();
        }

        return var2;
    }

    public static PhasorMatrix toPhasorColumnMatrix(Complex[] var0) {
        int var1 = var0.length;
        PhasorMatrix var2 = new PhasorMatrix(var1, 1);

        for(int var3 = 0; var3 < var1; ++var3) {
            var2.matrix[var3][0] = Phasor.toPhasor(var0[var3]).copy();
        }

        return var2;
    }

    public static PhasorMatrix toPhasorColumnMatrix(double[] var0) {
        int var1 = var0.length;
        PhasorMatrix var2 = new PhasorMatrix(var1, 1);

        for(int var3 = 0; var3 < var1; ++var3) {
            var2.matrix[var3][0] = new Phasor(var0[var3], 0.0D);
        }

        return var2;
    }

    public static PhasorMatrix toPhasorMatrix(ComplexMatrix var0) {
        PhasorMatrix var1 = new PhasorMatrix(var0.getNrow(), var0.getNcol());
        var1.index = var0.getIndexCopy();
        var1.dswap = var0.getSwap();

        for(int var2 = 0; var2 < var1.nrow; ++var2) {
            for(byte var3 = 0; var3 < var1.ncol; ++var2) {
                var1.matrix[var2][var3] = Phasor.toPhasor(var0.getElementCopy(var2, var3));
            }
        }

        return var1;
    }

    public static PhasorMatrix toPhasorMatrix(Complex[][] var0) {
        ComplexMatrix var1 = new ComplexMatrix(var0);
        PhasorMatrix var2 = new PhasorMatrix(var1.getNrow(), var1.getNcol());

        for(int var3 = 0; var3 < var2.nrow; ++var3) {
            for(byte var4 = 0; var4 < var2.ncol; ++var3) {
                var2.matrix[var3][var4] = Phasor.toPhasor(var1.getElementCopy(var3, var4));
            }
        }

        return var2;
    }

    public static PhasorMatrix toPhasorMatrix(Matrix var0) {
        int var1 = var0.getNrow();
        int var2 = var0.getNcol();
        PhasorMatrix var3 = new PhasorMatrix(var1, var2);

        for(int var4 = 0; var4 < var1; ++var4) {
            for(int var5 = 0; var5 < var2; ++var5) {
                var3.matrix[var4][var5].reset(var0.getElementCopy(var4, var5), 0.0D);
            }
        }

        return var3;
    }

    public static PhasorMatrix toPhasorMatrix(double[][] var0) {
        int var1 = var0.length;
        int var2 = var0[0].length;

        for(int var3 = 1; var3 < var1; ++var3) {
            if (var0[var3].length != var2) {
                throw new IllegalArgumentException("All rows must have the same length");
            }
        }

        PhasorMatrix var6 = new PhasorMatrix(var1, var2);

        for(int var4 = 0; var4 < var6.nrow; ++var4) {
            for(int var5 = 0; var5 < var6.ncol; ++var5) {
                var6.matrix[var4][var5].reset(var0[var4][var5], 0.0D);
            }
        }

        return var6;
    }

    public ComplexMatrix toComplexMatrix() {
        int var1 = this.getNrow();
        int var2 = this.getNcol();
        ComplexMatrix var3 = new ComplexMatrix(var1, var2);

        for(int var4 = 0; var4 < var1; ++var4) {
            for(byte var5 = 0; var5 < var2; ++var4) {
                var3.setElement(var4, var5, this.matrix[var4][var5].toRectangular());
            }
        }

        return var3;
    }

    public static ComplexMatrix toComplexMatrix(PhasorMatrix var0) {
        int var1 = var0.getNrow();
        int var2 = var0.getNcol();
        ComplexMatrix var3 = new ComplexMatrix(var1, var2);

        for(int var4 = 0; var4 < var1; ++var4) {
            for(byte var5 = 0; var5 < var2; ++var4) {
                var3.setElement(var4, var5, var0.matrix[var4][var5].toRectangular());
            }
        }

        return var3;
    }

    public PhasorMatrix plus(PhasorMatrix var1) {
        if (this.nrow == var1.nrow && this.ncol == var1.ncol) {
            int var2 = var1.nrow;
            int var3 = var1.ncol;
            PhasorMatrix var4 = new PhasorMatrix(var2, var3);
            Phasor[][] var5 = var4.getArrayReference();

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

    public PhasorMatrix plus(Phasor[][] var1) {
        int var2 = var1.length;
        int var3 = var1[0].length;
        if (this.nrow == var2 && this.ncol == var3) {
            PhasorMatrix var4 = new PhasorMatrix(var2, var3);
            Phasor[][] var5 = var4.getArrayReference();

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

    public PhasorMatrix plus(ComplexMatrix var1) {
        PhasorMatrix var2 = toPhasorMatrix(var1);
        return this.plus(var2);
    }

    public PhasorMatrix plus(Complex[][] var1) {
        PhasorMatrix var2 = toPhasorMatrix(var1);
        return this.plus(var2);
    }

    public PhasorMatrix plus(Matrix var1) {
        PhasorMatrix var2 = toPhasorMatrix(var1);
        return this.plus(var2);
    }

    public PhasorMatrix plus(double[][] var1) {
        PhasorMatrix var2 = toPhasorMatrix(var1);
        return this.plus(var2);
    }

    public void plusEquals(PhasorMatrix var1) {
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

    public void plusEquals(Phasor[][] var1) {
        PhasorMatrix var2 = new PhasorMatrix(var1);
        this.plusEquals(var2);
    }

    public void plusEquals(ComplexMatrix var1) {
        PhasorMatrix var2 = toPhasorMatrix(var1);
        this.plusEquals(var2);
    }

    public void plusEquals(Complex[][] var1) {
        PhasorMatrix var2 = toPhasorMatrix(var1);
        this.plusEquals(var2);
    }

    public void plusEquals(Matrix var1) {
        PhasorMatrix var2 = toPhasorMatrix(var1);
        this.plusEquals(var2);
    }

    public void plusEquals(double[][] var1) {
        PhasorMatrix var2 = toPhasorMatrix(var1);
        this.plusEquals(var2);
    }

    public PhasorMatrix minus(PhasorMatrix var1) {
        if (this.nrow == var1.nrow && this.ncol == var1.ncol) {
            int var2 = this.nrow;
            int var3 = this.ncol;
            PhasorMatrix var4 = new PhasorMatrix(var2, var3);
            Phasor[][] var5 = var4.getArrayReference();

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

    public PhasorMatrix minus(Phasor[][] var1) {
        int var2 = var1.length;
        int var3 = var1[0].length;
        if (this.nrow == var2 && this.ncol == var3) {
            PhasorMatrix var4 = new PhasorMatrix(var2, var3);
            Phasor[][] var5 = var4.getArrayReference();

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

    public PhasorMatrix minus(ComplexMatrix var1) {
        PhasorMatrix var2 = toPhasorMatrix(var1);
        return this.minus(var2);
    }

    public PhasorMatrix minus(Complex[][] var1) {
        PhasorMatrix var2 = toPhasorMatrix(var1);
        return this.minus(var2);
    }

    public PhasorMatrix minus(Matrix var1) {
        PhasorMatrix var2 = toPhasorMatrix(var1);
        return this.minus(var2);
    }

    public PhasorMatrix minus(double[][] var1) {
        PhasorMatrix var2 = toPhasorMatrix(var1);
        return this.minus(var2);
    }

    public void minusEquals(PhasorMatrix var1) {
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

    public void minusEquals(Phasor[][] var1) {
        PhasorMatrix var2 = new PhasorMatrix(var1);
        this.minusEquals(var2);
    }

    public void minusEquals(ComplexMatrix var1) {
        PhasorMatrix var2 = toPhasorMatrix(var1);
        this.minusEquals(var2);
    }

    public void minusEquals(Complex[][] var1) {
        PhasorMatrix var2 = toPhasorMatrix(var1);
        this.minusEquals(var2);
    }

    public void minusEquals(Matrix var1) {
        PhasorMatrix var2 = toPhasorMatrix(var1);
        this.minusEquals(var2);
    }

    public void minusEquals(double[][] var1) {
        PhasorMatrix var2 = toPhasorMatrix(var1);
        this.minusEquals(var2);
    }

    public PhasorMatrix times(PhasorMatrix var1) {
        if (this.ncol != var1.nrow) {
            throw new IllegalArgumentException("Nonconformable matrices");
        } else {
            PhasorMatrix var2 = new PhasorMatrix(this.nrow, var1.ncol);
            Phasor[][] var3 = var2.getArrayReference();
            new Phasor();

            for(int var5 = 0; var5 < this.nrow; ++var5) {
                for(int var6 = 0; var6 < var1.ncol; ++var6) {
                    Phasor var4 = Phasor.zero();

                    for(int var7 = 0; var7 < this.ncol; ++var7) {
                        var4.plusEquals(this.matrix[var5][var7].times(var1.matrix[var7][var6]));
                    }

                    var3[var5][var6] = Phasor.copy(var4);
                }
            }

            return var2;
        }
    }

    public PhasorMatrix times(Phasor[][] var1) {
        int var2 = var1.length;
        int var3 = var1[0].length;
        if (this.ncol != var2) {
            throw new IllegalArgumentException("Nonconformable matrices");
        } else {
            PhasorMatrix var4 = new PhasorMatrix(this.nrow, var3);
            Phasor[][] var5 = var4.getArrayReference();
            new Phasor();

            for(int var7 = 0; var7 < this.nrow; ++var7) {
                for(int var8 = 0; var8 < var3; ++var8) {
                    Phasor var6 = Phasor.zero();

                    for(int var9 = 0; var9 < this.ncol; ++var9) {
                        var6.plusEquals(this.matrix[var7][var9].times(var1[var9][var8]));
                    }

                    var5[var7][var8] = Phasor.copy(var6);
                }
            }

            return var4;
        }
    }

    public PhasorMatrix times(ComplexMatrix var1) {
        PhasorMatrix var2 = toPhasorMatrix(var1);
        return this.times(var2);
    }

    public PhasorMatrix times(Complex[][] var1) {
        PhasorMatrix var2 = toPhasorMatrix(var1);
        return this.times(var2);
    }

    public PhasorMatrix times(Matrix var1) {
        PhasorMatrix var2 = toPhasorMatrix(var1);
        return this.times(var2);
    }

    public PhasorMatrix times(double[][] var1) {
        PhasorMatrix var2 = toPhasorMatrix(var1);
        return this.times(var2);
    }

    public PhasorMatrix times(Phasor var1) {
        PhasorMatrix var2 = new PhasorMatrix(this.nrow, this.ncol);
        Phasor[][] var3 = var2.getArrayReference();

        for(int var4 = 0; var4 < this.nrow; ++var4) {
            for(int var5 = 0; var5 < this.ncol; ++var5) {
                var3[var4][var5] = this.matrix[var4][var5].times(var1);
            }
        }

        return var2;
    }

    public PhasorMatrix times(double var1) {
        PhasorMatrix var3 = new PhasorMatrix(this.nrow, this.ncol);
        Phasor[][] var4 = var3.getArrayReference();
        Phasor var5 = new Phasor(var1, 0.0D);

        for(int var6 = 0; var6 < this.nrow; ++var6) {
            for(int var7 = 0; var7 < this.ncol; ++var7) {
                var4[var6][var7] = this.matrix[var6][var7].times(var5);
            }
        }

        return var3;
    }

    public void timesEquals(PhasorMatrix var1) {
        if (this.ncol != var1.nrow) {
            throw new IllegalArgumentException("Nonconformable matrices");
        } else {
            new Phasor();

            for(int var3 = 0; var3 < this.nrow; ++var3) {
                for(int var4 = 0; var4 < var1.ncol; ++var4) {
                    Phasor var2 = Phasor.zero();

                    for(int var5 = 0; var5 < this.ncol; ++var5) {
                        var2.plusEquals(this.matrix[var3][var5].times(var1.matrix[var5][var4]));
                    }

                    this.matrix[var3][var4] = Phasor.copy(var2);
                }
            }

        }
    }

    public void timesEquals(Phasor[][] var1) {
        PhasorMatrix var2 = new PhasorMatrix(var1);
        this.timesEquals(var2);
    }

    public void timesEquals(ComplexMatrix var1) {
        PhasorMatrix var2 = toPhasorMatrix(var1);
        this.timesEquals(var2);
    }

    public void timesEquals(Complex[][] var1) {
        PhasorMatrix var2 = toPhasorMatrix(var1);
        this.timesEquals(var2);
    }

    public void timesEquals(Matrix var1) {
        PhasorMatrix var2 = toPhasorMatrix(var1);
        this.timesEquals(var2);
    }

    public void timesEquals(double[][] var1) {
        PhasorMatrix var2 = toPhasorMatrix(var1);
        this.timesEquals(var2);
    }

    public void timesEquals(Phasor var1) {
        for(int var2 = 0; var2 < this.nrow; ++var2) {
            for(int var3 = 0; var3 < this.ncol; ++var3) {
                this.matrix[var2][var3].timesEquals(var1);
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
        Phasor var3 = new Phasor(var1, 0.0D);

        for(int var4 = 0; var4 < this.nrow; ++var4) {
            for(int var5 = 0; var5 < this.ncol; ++var5) {
                this.matrix[var4][var5].timesEquals(var3);
            }
        }

    }

    public void timesEquals(int var1) {
        Phasor var2 = new Phasor((double)var1, 0.0D);

        for(int var3 = 0; var3 < this.nrow; ++var3) {
            for(int var4 = 0; var4 < this.ncol; ++var4) {
                this.matrix[var3][var4].timesEquals(var2);
            }
        }

    }

    public PhasorMatrix over(PhasorMatrix var1) {
        if (this.nrow == var1.nrow && this.ncol == var1.ncol) {
            return this.times(var1.inverse());
        } else {
            throw new IllegalArgumentException("Array dimensions do not agree");
        }
    }

    public PhasorMatrix over(Phasor[][] var1) {
        int var2 = var1.length;
        int var3 = var1[0].length;
        if (this.nrow == var2 && this.ncol == var3) {
            PhasorMatrix var4 = new PhasorMatrix(var1);
            return this.times(var4.inverse());
        } else {
            throw new IllegalArgumentException("Array dimensions do not agree");
        }
    }

    public PhasorMatrix over(ComplexMatrix var1) {
        PhasorMatrix var2 = toPhasorMatrix(var1);
        return this.over(var2);
    }

    public PhasorMatrix over(Complex[][] var1) {
        PhasorMatrix var2 = toPhasorMatrix(var1);
        return this.over(var2);
    }

    public PhasorMatrix over(Matrix var1) {
        PhasorMatrix var2 = toPhasorMatrix(var1);
        return this.over(var2);
    }

    public PhasorMatrix over(double[][] var1) {
        PhasorMatrix var2 = toPhasorMatrix(var1);
        return this.over(var2);
    }

    public void overEquals(PhasorMatrix var1) {
        if (this.nrow == var1.nrow && this.ncol == var1.ncol) {
            PhasorMatrix var2 = new PhasorMatrix(var1);
            this.timesEquals(var2.inverse());
        } else {
            throw new IllegalArgumentException("Array dimensions do not agree");
        }
    }

    public void overEquals(Phasor[][] var1) {
        PhasorMatrix var2 = new PhasorMatrix(var1);
        this.overEquals(var2);
    }

    public void overEquals(ComplexMatrix var1) {
        PhasorMatrix var2 = toPhasorMatrix(var1);
        this.overEquals(var2);
    }

    public void overEquals(Complex[][] var1) {
        PhasorMatrix var2 = toPhasorMatrix(var1);
        this.overEquals(var2);
    }

    public void overEquals(Matrix var1) {
        PhasorMatrix var2 = toPhasorMatrix(var1);
        this.overEquals(var2);
    }

    public void overEquals(double[][] var1) {
        PhasorMatrix var2 = toPhasorMatrix(var1);
        this.overEquals(var2);
    }

    public PhasorMatrix inverse() {
        int var1 = this.nrow;
        if (var1 != this.ncol) {
            throw new IllegalArgumentException("Matrix is not square");
        } else {
            Phasor[] var2 = new Phasor[var1];
            Phasor[] var3 = new Phasor[var1];
            PhasorMatrix var4 = new PhasorMatrix(var1, var1);
            Phasor[][] var5 = var4.getArrayReference();
            PhasorMatrix var6 = this.luDecomp();

            for(int var7 = 0; var7 < var1; ++var7) {
                int var8;
                for(var8 = 0; var8 < var1; ++var8) {
                    var2[var8] = Phasor.zero();
                }

                var2[var7] = Phasor.plusOne();
                var3 = var6.luBackSub(var2);

                for(var8 = 0; var8 < var1; ++var8) {
                    var5[var8][var7] = Phasor.copy(var3[var8]);
                }
            }

            return var4;
        }
    }

    public PhasorMatrix transpose() {
        PhasorMatrix var1 = new PhasorMatrix(this.ncol, this.nrow);
        Phasor[][] var2 = var1.getArrayReference();

        for(int var3 = 0; var3 < this.ncol; ++var3) {
            for(int var4 = 0; var4 < this.nrow; ++var4) {
                var2[var3][var4] = Phasor.copy(this.matrix[var4][var3]);
            }
        }

        return var1;
    }

    public PhasorMatrix conjugate() {
        PhasorMatrix var1 = copy(this);

        for(int var2 = 0; var2 < this.nrow; ++var2) {
            for(int var3 = 0; var3 < this.ncol; ++var3) {
                var1.matrix[var2][var3] = this.matrix[var2][var3].conjugate();
            }
        }

        return var1;
    }

    public PhasorMatrix adjoin() {
        PhasorMatrix var1 = copy(this);
        var1 = var1.transpose();
        var1 = var1.conjugate();
        return var1;
    }

    public PhasorMatrix opposite() {
        PhasorMatrix var1 = copy(this);

        for(int var2 = 0; var2 < this.nrow; ++var2) {
            for(int var3 = 0; var3 < this.ncol; ++var3) {
                var1.matrix[var2][var3] = this.matrix[var2][var3].times(Phasor.minusOne());
            }
        }

        return var1;
    }

    public Phasor trace() {
        Phasor var1 = new Phasor(0.0D, 0.0D);

        for(int var2 = 0; var2 < Math.min(this.ncol, this.ncol); ++var2) {
            var1.plusEquals(this.matrix[var2][var2]);
        }

        return var1;
    }

    public Phasor determinant() {
        int var1 = this.nrow;
        if (var1 != this.ncol) {
            throw new IllegalArgumentException("Matrix is not square");
        } else {
            Phasor var2 = new Phasor();
            PhasorMatrix var3 = this.luDecomp();
            var2.reset(var3.dswap, 0.0D);

            for(int var4 = 0; var4 < var1; ++var4) {
                var2.timesEquals(var3.matrix[var4][var4]);
            }

            return var2;
        }
    }

    public Phasor logDeterminant() {
        int var1 = this.nrow;
        if (var1 != this.ncol) {
            throw new IllegalArgumentException("Matrix is not square");
        } else {
            Phasor var2 = new Phasor();
            PhasorMatrix var3 = this.luDecomp();
            var2.reset(var3.dswap, 0.0D);
            var2 = Phasor.log(var2);

            for(int var4 = 0; var4 < var1; ++var4) {
                var2.plusEquals(Phasor.log(var3.matrix[var4][var4]));
            }

            return var2;
        }
    }

    public double frobeniusNorm() {
        double var1 = 0.0D;

        for(int var3 = 0; var3 < this.nrow; ++var3) {
            for(int var4 = 0; var4 < this.ncol; ++var4) {
                var1 = Fmath.hypot(var1, this.matrix[var3][var4].abs());
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
                var3 += this.matrix[var5][var6].abs();
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
                var3 += this.matrix[var5][var6].abs();
            }

            var1 = Math.max(var1, var3);
        }

        return var1;
    }

    public PhasorMatrix luDecomp() {
        if (this.nrow != this.ncol) {
            throw new IllegalArgumentException("A matrix is not square");
        } else {
            int var1 = this.nrow;
            int var2 = 0;
            double var3 = 0.0D;
            double var5 = 0.0D;
            double var7 = 0.0D;
            double[] var9 = new double[var1];
            new Phasor();
            new Phasor();
            PhasorMatrix var12 = copy(this);
            Phasor[][] var13 = var12.getArrayReference();
            var12.dswap = 1.0D;

            int var14;
            int var15;
            for(var14 = 0; var14 < var1; ++var14) {
                var7 = 0.0D;

                for(var15 = 0; var15 < var1; ++var15) {
                    if ((var5 = var13[var14][var15].abs()) > var7) {
                        var7 = var5;
                    }
                }

                if (var7 == 0.0D) {
                    throw new ArithmeticException("Singular matrix");
                }

                var9[var14] = 1.0D / var7;
            }

            for(var14 = 0; var14 < var1; ++var14) {
                Phasor var10;
                int var16;
                for(var15 = 0; var15 < var14; ++var15) {
                    var10 = Phasor.copy(var13[var15][var14]);

                    for(var16 = 0; var16 < var15; ++var16) {
                        var10.minusEquals(var13[var15][var16].times(var13[var16][var14]));
                    }

                    var13[var15][var14] = Phasor.copy(var10);
                }

                var7 = 0.0D;

                for(var15 = var14; var15 < var1; ++var15) {
                    var10 = Phasor.copy(var13[var15][var14]);

                    for(var16 = 0; var16 < var14; ++var16) {
                        var10.minusEquals(var13[var15][var16].times(var13[var16][var14]));
                    }

                    var13[var15][var14] = Phasor.copy(var10);
                    if ((var3 = var9[var15] * var10.abs()) >= var7) {
                        var7 = var3;
                        var2 = var15;
                    }
                }

                Phasor var11;
                if (var14 != var2) {
                    for(var15 = 0; var15 < var1; ++var15) {
                        var11 = Phasor.copy(var13[var2][var15]);
                        var13[var2][var15] = Phasor.copy(var13[var14][var15]);
                        var13[var14][var15] = Phasor.copy(var11);
                    }

                    var12.dswap = -var12.dswap;
                    var9[var2] = var9[var14];
                }

                var12.index[var14] = var2;
                if (var13[var14][var14].isZero()) {
                    var13[var14][var14].reset(1.0E-30D, 0.0D);
                }

                if (var14 != var1 - 1) {
                    var11 = var13[var14][var14].inverse();

                    for(var15 = var14 + 1; var15 < var1; ++var15) {
                        var13[var15][var14].timesEquals(var11);
                    }
                }
            }

            return var12;
        }
    }

    public Phasor[] luBackSub(Phasor[] var1) {
        int var2 = 0;
        boolean var3 = false;
        int var4 = var1.length;
        if (var4 != this.ncol) {
            throw new IllegalArgumentException("vector length is not equal to matrix dimension");
        } else if (this.ncol != this.nrow) {
            throw new IllegalArgumentException("matrix is not square");
        } else {
            new Phasor();
            Phasor[] var6 = new Phasor[var4];

            int var7;
            for(var7 = 0; var7 < var4; ++var7) {
                var6[var7] = Phasor.copy(var1[var7]);
            }

            Phasor var5;
            int var8;
            for(var7 = 0; var7 < var4; ++var7) {
                int var9 = this.index[var7];
                var5 = Phasor.copy(var6[var9]);
                var6[var9] = Phasor.copy(var6[var7]);
                if (var2 == 0) {
                    for(var8 = var2; var8 <= var7 - 1; ++var8) {
                        var5.minusEquals(this.matrix[var7][var8].times(var6[var8]));
                    }
                } else if (var5.isZero()) {
                    var2 = var7;
                }

                var6[var7] = Phasor.copy(var5);
            }

            for(var7 = var4 - 1; var7 >= 0; --var7) {
                var5 = Phasor.copy(var6[var7]);

                for(var8 = var7 + 1; var8 < var4; ++var8) {
                    var5.minusEquals(this.matrix[var7][var8].times(var6[var8]));
                }

                var6[var7] = var5.over(this.matrix[var7][var7]);
            }

            return var6;
        }
    }

    public Phasor[] solveLinearSet(Phasor[] var1) {
        PhasorMatrix var2 = this.luDecomp();
        return var2.luBackSub(var1);
    }
}
