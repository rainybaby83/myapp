//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.complex;

import com.flanagan.circuits.Phasor;
import com.flanagan.io.FileOutput;
import com.flanagan.math.ArrayMaths;
import com.flanagan.math.Conv;
import com.flanagan.math.Fmath;
import com.flanagan.math.Polynomial;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

public class ComplexPoly {
    private int deg = 0;
    private int degwz = 0;
    private Complex[] coeff;
    private Complex[] coeffwz;
    private boolean suppressRootsErrorMessages = false;

    public ComplexPoly(int var1) {
        this.deg = var1;
        this.coeff = Complex.oneDarray(var1 + 1);
    }

    public ComplexPoly(Complex[] var1) {
        this.deg = var1.length - 1;
        this.coeff = Complex.oneDarray(this.deg + 1);

        for(int var2 = 0; var2 <= this.deg; ++var2) {
            this.coeff[var2] = Complex.copy(var1[var2]);
        }

    }

    public ComplexPoly(double[] var1) {
        this.deg = var1.length - 1;
        this.coeff = Complex.oneDarray(this.deg + 1);

        for(int var2 = 0; var2 <= this.deg; ++var2) {
            this.coeff[var2].reset(var1[var2], 0.0D);
        }

    }

    public ComplexPoly(float[] var1) {
        this.deg = var1.length - 1;
        this.coeff = Complex.oneDarray(this.deg + 1);

        for(int var2 = 0; var2 <= this.deg; ++var2) {
            this.coeff[var2].reset((double)var1[var2], 0.0D);
        }

    }

    public ComplexPoly(int[] var1) {
        this.deg = var1.length - 1;
        this.coeff = Complex.oneDarray(this.deg + 1);

        for(int var2 = 0; var2 <= this.deg; ++var2) {
            this.coeff[var2].reset((double)var1[var2], 0.0D);
        }

    }

    public ComplexPoly(Polynomial var1) {
        this.deg = var1.getDeg();
        this.coeff = Complex.oneDarray(this.deg + 1);

        for(int var2 = 0; var2 <= this.deg; ++var2) {
            this.coeff[var2].reset(var1.getCoefficient(var2), 0.0D);
        }

    }

    public ComplexPoly(ArrayList<Object> var1) {
        this.deg = var1.size() - 1;
        this.coeff = Complex.oneDarray(this.deg + 1);

        for(int var2 = 0; var2 <= this.deg; ++var2) {
            int var3 = this.getTypeCode(var1.get(var2));
            switch(var3) {
                case 1:
                    this.coeff[var2].reset((double)(Byte)var1.get(var2), 0.0D);
                    break;
                case 2:
                    this.coeff[var2].reset((double)(Short)var1.get(var2), 0.0D);
                    break;
                case 3:
                    this.coeff[var2].reset((double)(Integer)var1.get(var2), 0.0D);
                    break;
                case 4:
                    this.coeff[var2].reset((double)(Long)var1.get(var2), 0.0D);
                    break;
                case 5:
                    this.coeff[var2].reset((double)(Float)var1.get(var2), 0.0D);
                    break;
                case 6:
                    this.coeff[var2].reset((Double)var1.get(var2), 0.0D);
                    break;
                case 7:
                    this.coeff[var2] = (Complex)var1.get(var2);
                    break;
                case 8:
                    this.coeff[var2] = ((Phasor)var1.get(var2)).toComplex();
                    break;
                case 9:
                    this.coeff[var2].reset(((BigInteger)var1.get(var2)).doubleValue(), 0.0D);
                    break;
                case 10:
                    this.coeff[var2].reset(((BigDecimal)var1.get(var2)).doubleValue(), 0.0D);
                    break;
                default:
                    throw new IllegalArgumentException("Type code, " + var3 + ", not recognised");
            }
        }

    }

    private int getTypeCode(Object var1) {
        byte var2 = 0;
        if (var1 instanceof Byte) {
            var2 = 1;
        } else if (var1 instanceof Short) {
            var2 = 2;
        } else if (var1 instanceof Integer) {
            var2 = 3;
        } else if (var1 instanceof Long) {
            var2 = 4;
        } else if (var1 instanceof Float) {
            var2 = 5;
        } else if (var1 instanceof Double) {
            var2 = 6;
        } else if (var1 instanceof Complex) {
            var2 = 7;
        } else if (var1 instanceof Phasor) {
            var2 = 8;
        } else if (var1 instanceof BigInteger) {
            var2 = 9;
        } else if (var1 instanceof BigDecimal) {
            var2 = 10;
        }

        return var2;
    }

    public ComplexPoly(Complex var1) {
        this.deg = 0;
        this.coeff = Complex.oneDarray(1);
        this.coeff[0] = Complex.copy(var1);
    }

    public ComplexPoly(double var1) {
        this.deg = 0;
        this.coeff = Complex.oneDarray(1);
        this.coeff[0].reset(var1, 0.0D);
    }

    public ComplexPoly(Complex var1, Complex var2) {
        this.deg = 1;
        this.coeff = Complex.oneDarray(2);
        this.coeff[0] = Complex.copy(var1);
        this.coeff[1] = Complex.copy(var2);
    }

    public ComplexPoly(double var1, double var3) {
        this.deg = 1;
        this.coeff = Complex.oneDarray(2);
        this.coeff[0].reset(var1, 0.0D);
        this.coeff[1].reset(var3, 0.0D);
    }

    public ComplexPoly(Complex var1, Complex var2, Complex var3) {
        this.deg = 2;
        this.coeff = Complex.oneDarray(3);
        this.coeff[0] = Complex.copy(var1);
        this.coeff[1] = Complex.copy(var2);
        this.coeff[2] = Complex.copy(var3);
    }

    public ComplexPoly(double var1, double var3, double var5) {
        this.deg = 2;
        this.coeff = Complex.oneDarray(3);
        this.coeff[0].reset(var1, 0.0D);
        this.coeff[1].reset(var3, 0.0D);
        this.coeff[2].reset(var5, 0.0D);
    }

    public ComplexPoly(Complex var1, Complex var2, Complex var3, Complex var4) {
        this.deg = 3;
        this.coeff = Complex.oneDarray(4);
        this.coeff[0] = Complex.copy(var1);
        this.coeff[1] = Complex.copy(var2);
        this.coeff[2] = Complex.copy(var3);
        this.coeff[3] = Complex.copy(var4);
    }

    public ComplexPoly(double var1, double var3, double var5, double var7) {
        this.deg = 3;
        this.coeff = Complex.oneDarray(4);
        this.coeff[0].reset(var1, 0.0D);
        this.coeff[1].reset(var3, 0.0D);
        this.coeff[2].reset(var5, 0.0D);
        this.coeff[3].reset(var7, 0.0D);
    }

    public ComplexPoly reducePoly() {
        ComplexPoly var1 = null;
        int var2 = this.deg;
        boolean var3 = true;
        int var4 = this.deg;

        while(var3) {
            if (this.coeff[var4].isZero()) {
                --var4;
                --var2;
                if (var4 < 0) {
                    var3 = false;
                }
            } else {
                var3 = false;
            }
        }

        if (this.deg == var2) {
            var1 = this.copy();
        } else if (var2 >= 0) {
            var1 = new ComplexPoly(var2);

            for(int var5 = 0; var5 <= var2; ++var5) {
                var1.resetCoeff(var5, this.coeff[var5].copy());
            }
        }

        return var1;
    }

    public static ComplexPoly reducePoly(ComplexPoly var0) {
        ComplexPoly var1 = null;
        if (var0 != null) {
            int var2 = var0.getDeg();
            int var3 = var2;
            boolean var4 = true;
            int var5 = var2;

            while(var4) {
                if (var0.coeffCopy(var5).isZero()) {
                    --var5;
                    --var3;
                    if (var5 < 0) {
                        var4 = false;
                    }
                } else {
                    var4 = false;
                }
            }

            if (var3 == var2) {
                var1 = var0.copy();
            } else if (var3 >= 0) {
                var1 = new ComplexPoly(var3);

                for(int var6 = 0; var6 <= var3; ++var6) {
                    var1.resetCoeff(var6, var0.coeffCopy(var6));
                }
            }
        }

        return var1;
    }

    public static ComplexPoly rootsToPoly(Complex[] var0) {
        if (var0 == null) {
            return null;
        } else {
            int var1 = var0.length;
            Complex[] var2 = Complex.oneDarray(2);
            var2[0] = var0[0].times(Complex.minusOne());
            var2[1] = Complex.plusOne();
            ComplexPoly var3 = new ComplexPoly(var2);

            for(int var4 = 1; var4 < var1; ++var4) {
                var2[0] = var0[var4].times(Complex.minusOne());
                ComplexPoly var5 = new ComplexPoly(var2);
                var3 = var3.times(var5);
            }

            return var3;
        }
    }

    public void resetPoly(Complex[] var1) {
        if (this.deg + 1 != var1.length) {
            throw new IllegalArgumentException("array lengths do not match");
        } else {
            for(int var2 = 0; var2 < this.deg; ++var2) {
                this.coeff[var2] = Complex.copy(var1[var2]);
            }

        }
    }

    public void resetPoly(ArrayList<Object> var1) {
        if (this.deg + 1 != var1.size()) {
            throw new IllegalArgumentException("array lengths do not match");
        } else {
            for(int var2 = 0; var2 <= this.deg; ++var2) {
                int var3 = this.getTypeCode(var1.get(var2));
                switch(var3) {
                    case 1:
                        this.coeff[var2].reset((double)(Byte)var1.get(var2), 0.0D);
                        break;
                    case 2:
                        this.coeff[var2].reset((double)(Short)var1.get(var2), 0.0D);
                        break;
                    case 3:
                        this.coeff[var2].reset((double)(Integer)var1.get(var2), 0.0D);
                        break;
                    case 4:
                        this.coeff[var2].reset((double)(Long)var1.get(var2), 0.0D);
                        break;
                    case 5:
                        this.coeff[var2].reset((double)(Float)var1.get(var2), 0.0D);
                        break;
                    case 6:
                        this.coeff[var2].reset((Double)var1.get(var2), 0.0D);
                        break;
                    case 7:
                        this.coeff[var2] = (Complex)var1.get(var2);
                        break;
                    case 8:
                        this.coeff[var2] = ((Phasor)var1.get(var2)).toComplex();
                        break;
                    case 9:
                        this.coeff[var2].reset(((BigInteger)var1.get(var2)).doubleValue(), 0.0D);
                        break;
                    case 10:
                        this.coeff[var2].reset(((BigDecimal)var1.get(var2)).doubleValue(), 0.0D);
                        break;
                    default:
                        throw new IllegalArgumentException("Type code, " + var3 + ", not recognised");
                }
            }

        }
    }

    public void resetCoeff(int var1, Complex var2) {
        this.coeff[var1] = Complex.copy(var2);
    }

    public ComplexPoly copy() {
        if (this == null) {
            return null;
        } else {
            ComplexPoly var1 = new ComplexPoly(this.deg);

            for(int var2 = 0; var2 <= this.deg; ++var2) {
                var1.coeff[var2] = Complex.copy(this.coeff[var2]);
            }

            var1.deg = this.deg;
            var1.degwz = this.degwz;
            var1.coeffwz = Conv.copy(this.coeffwz);
            return var1;
        }
    }

    public static ComplexPoly copy(ComplexPoly var0) {
        if (var0 == null) {
            return null;
        } else {
            ComplexPoly var1 = new ComplexPoly(var0.deg);

            for(int var2 = 0; var2 <= var0.deg; ++var2) {
                var1.coeff[var2] = Complex.copy(var0.coeff[var2]);
            }

            var1.deg = var0.deg;
            var1.degwz = var0.degwz;
            var1.coeffwz = Conv.copy(var0.coeffwz);
            return var1;
        }
    }

    public Object clone() {
        return this.copy();
    }

    public Complex[] polyNomCopy() {
        Complex[] var1 = Complex.oneDarray(this.deg + 1);

        for(int var2 = 0; var2 <= this.deg; ++var2) {
            var1[var2] = Complex.copy(this.coeff[var2]);
        }

        return var1;
    }

    public Complex[] polyNomReference() {
        return this.coeff;
    }

    public Complex[] polyNomPointer() {
        return this.coeff;
    }

    public Complex coeffCopy(int var1) {
        return Complex.copy(this.coeff[var1]);
    }

    public Complex coeffReference(int var1) {
        return this.coeff[var1];
    }

    public Complex coeffPointer(int var1) {
        return this.coeff[var1];
    }

    public int getDeg() {
        return this.deg;
    }

    public void setj() {
        Complex.setj();
    }

    public void seti() {
        Complex.seti();
    }

    public String toString() {
        String var1 = "";
        var1 = var1 + this.coeffCopy(0).toString();
        if (this.deg > 0) {
            var1 = var1 + " + (" + this.coeffCopy(1).toString() + ").x";
        }

        for(int var2 = 2; var2 <= this.deg; ++var2) {
            var1 = var1 + " + (" + this.coeffCopy(var2).toString() + ").x^" + var2;
        }

        return var1;
    }

    public void print() {
        System.out.print(this.toString());
    }

    public void println() {
        System.out.println(this.toString());
    }

    public void printToText(String var1) {
        var1 = var1 + ".txt";
        FileOutput var2 = new FileOutput(var1, 'n');
        var2.println("Output File for a ComplexPoly");
        var2.dateAndTimeln();
        var2.println();
        var2.print("Polynomial degree is ");
        var2.println(this.deg);
        var2.println();
        var2.println("The coefficients are ");

        for(int var3 = 0; var3 <= this.deg; ++var3) {
            var2.println(this.coeff[var3]);
        }

        var2.println();
        var2.println("End of file.");
        var2.close();
    }

    public void printToText() {
        String var1 = "ComplexPolyOut";
        this.printToText(var1);
    }

    public ArrayList<ComplexPoly> sTransform() {
        return sTransform(this.coeff);
    }

    public static ArrayList<ComplexPoly> sTransform(double[] var0) {
        ArrayMaths var1 = new ArrayMaths(var0);
        Complex[] var2 = var1.getArray_as_Complex();
        return sTransform(var2);
    }

    public static ArrayList<ComplexPoly> sTransform(Complex[] var0) {
        int var1 = var0.length;
        ComplexPoly[] var2 = new ComplexPoly[var1];
        ComplexPoly[] var3 = new ComplexPoly[var1];
        ComplexPoly var4 = null;
        new ComplexPoly(Complex.plusOne());

        int var6;
        for(var6 = 0; var6 < var1; ++var6) {
            var2[var6] = new ComplexPoly(var0[var6].times(new Complex((double)Fmath.factorial(var6), 0.0D)));
            var3[var6] = new ComplexPoly(var6 + 1);
            var3[var6].resetCoeff(var6 + 1, Complex.plusOne());
        }

        ComplexPoly var5 = var3[var1 - 1];

        for(var6 = 0; var6 < var1 - 1; ++var6) {
            var2[var6] = var2[var6].times(var3[var1 - var6 - 2]);
        }

        var4 = var2[0];

        for(var6 = 1; var6 < var1; ++var6) {
            var4 = var4.plus(var2[var6]);
        }

        ArrayList var7 = new ArrayList();
        var7.add(var4);
        var7.add(var5);
        return var7;
    }

    public boolean equals(ComplexPoly var1) {
        return this.isEqual(var1);
    }

    public boolean isEqual(ComplexPoly var1) {
        boolean var2 = false;
        int var3 = this.getDeg();
        int var4 = var1.getDeg();
        if (var3 == var4) {
            boolean var5 = true;
            int var6 = 0;

            while(var5) {
                if (!this.coeff[var6].isEqual(var1.coeffReference(var6))) {
                    var5 = false;
                } else {
                    ++var6;
                    if (var6 > var4) {
                        var5 = false;
                        var2 = true;
                    }
                }
            }
        }

        return var2;
    }

    public static boolean isEqual(ComplexPoly var0, ComplexPoly var1) {
        boolean var2 = false;
        int var3 = var0.getDeg();
        int var4 = var1.getDeg();
        if (var3 == var4) {
            boolean var5 = true;
            int var6 = 0;

            while(var5) {
                if (!var0.coeffReference(var6).isEqual(var1.coeffReference(var6))) {
                    var5 = false;
                } else {
                    ++var6;
                    if (var6 > var3) {
                        var5 = false;
                        var2 = true;
                    }
                }
            }
        }

        return var2;
    }

    public ComplexPoly plus(ComplexPoly var1) {
        ComplexPoly var2 = null;
        int var3;
        if (var1.deg <= this.deg) {
            var2 = new ComplexPoly(this.deg);

            for(var3 = var1.deg + 1; var3 <= this.deg; ++var3) {
                var2.coeff[var3] = Complex.copy(this.coeff[var3]);
            }

            for(var3 = 0; var3 <= var1.deg; ++var3) {
                var2.coeff[var3] = this.coeff[var3].plus(var1.coeff[var3]);
            }
        } else {
            var2 = new ComplexPoly(var1.deg);

            for(var3 = this.deg + 1; var3 <= var1.deg; ++var3) {
                var2.coeff[var3] = Complex.copy(var1.coeff[var3]);
            }

            for(var3 = 0; var3 <= this.deg; ++var3) {
                var2.coeff[var3] = this.coeff[var3].plus(var1.coeff[var3]);
            }
        }

        return var2;
    }

    public static ComplexPoly plus(ComplexPoly var0, ComplexPoly var1) {
        ComplexPoly var2 = null;
        int var3;
        if (var1.deg <= var0.deg) {
            var2 = new ComplexPoly(var0.deg);

            for(var3 = var1.deg + 1; var3 <= var0.deg; ++var3) {
                var2.coeff[var3] = Complex.copy(var0.coeff[var3]);
            }

            for(var3 = 0; var3 <= var1.deg; ++var3) {
                var2.coeff[var3] = var0.coeff[var3].plus(var1.coeff[var3]);
            }
        } else {
            var2 = new ComplexPoly(var1.deg);

            for(var3 = var0.deg + 1; var3 <= var1.deg; ++var3) {
                var2.coeff[var3] = Complex.copy(var1.coeff[var3]);
            }

            for(var3 = 0; var3 <= var0.deg; ++var3) {
                var2.coeff[var3] = var0.coeff[var3].plus(var1.coeff[var3]);
            }
        }

        return var2;
    }

    public ComplexPoly plus(Complex var1) {
        ComplexPoly var2 = new ComplexPoly(var1);
        return this.plus(var2);
    }

    public static ComplexPoly plus(ComplexPoly var0, Complex var1) {
        ComplexPoly var2 = new ComplexPoly(var1);
        return plus(var0, var2);
    }

    public ComplexPoly plus(double var1) {
        ComplexPoly var3 = new ComplexPoly(new Complex(var1, 0.0D));
        return this.plus(var3);
    }

    public static ComplexPoly plus(ComplexPoly var0, double var1) {
        ComplexPoly var3 = new ComplexPoly(new Complex(var1, 0.0D));
        return plus(var0, var3);
    }

    public ComplexPoly plus(int var1) {
        ComplexPoly var2 = new ComplexPoly(new Complex((double)var1, 0.0D));
        return this.plus(var2);
    }

    public static ComplexPoly plus(ComplexPoly var0, int var1) {
        ComplexPoly var2 = new ComplexPoly(new Complex((double)var1, 0.0D));
        return plus(var0, var2);
    }

    public ComplexPoly minus(ComplexPoly var1) {
        ComplexPoly var2 = null;
        int var3;
        if (var1.deg <= this.deg) {
            var2 = new ComplexPoly(this.deg);

            for(var3 = var1.deg + 1; var3 <= this.deg; ++var3) {
                var2.coeff[var3] = Complex.copy(this.coeff[var3]);
            }

            for(var3 = 0; var3 <= var1.deg; ++var3) {
                var2.coeff[var3] = this.coeff[var3].minus(var1.coeff[var3]);
            }
        } else {
            var2 = new ComplexPoly(var1.deg);

            for(var3 = this.deg + 1; var3 <= var1.deg; ++var3) {
                var2.coeff[var3] = var1.coeff[var3].times(Complex.minusOne());
            }

            for(var3 = 0; var3 <= this.deg; ++var3) {
                var2.coeff[var3] = this.coeff[var3].minus(var1.coeff[var3]);
            }
        }

        return var2;
    }

    public ComplexPoly minus(Complex var1) {
        ComplexPoly var2 = new ComplexPoly(var1);
        return this.minus(var2);
    }

    public ComplexPoly minus(double var1) {
        ComplexPoly var3 = new ComplexPoly(new Complex(var1, 0.0D));
        return this.minus(var3);
    }

    public ComplexPoly minus(int var1) {
        ComplexPoly var2 = new ComplexPoly(new Complex((double)var1, 0.0D));
        return this.minus(var2);
    }

    public static ComplexPoly minus(ComplexPoly var0, ComplexPoly var1) {
        ComplexPoly var2 = null;
        int var3;
        if (var1.deg <= var0.deg) {
            var2 = new ComplexPoly(var0.deg);

            for(var3 = var1.deg + 1; var3 <= var0.deg; ++var3) {
                var2.coeff[var3] = Complex.copy(var0.coeff[var3]);
            }

            for(var3 = 0; var3 <= var1.deg; ++var3) {
                var2.coeff[var3] = var0.coeff[var3].minus(var1.coeff[var3]);
            }
        } else {
            var2 = new ComplexPoly(var1.deg);

            for(var3 = var0.deg + 1; var3 <= var1.deg; ++var3) {
                var2.coeff[var3] = var1.coeff[var3].times(Complex.minusOne());
            }

            for(var3 = 0; var3 <= var0.deg; ++var3) {
                var2.coeff[var3] = var0.coeff[var3].minus(var1.coeff[var3]);
            }
        }

        return var2;
    }

    public static ComplexPoly minus(ComplexPoly var0, Complex var1) {
        ComplexPoly var2 = new ComplexPoly(var1);
        return minus(var0, var2);
    }

    public static ComplexPoly minus(ComplexPoly var0, double var1) {
        ComplexPoly var3 = new ComplexPoly(new Complex(var1, 0.0D));
        return minus(var0, var3);
    }

    public static ComplexPoly minus(ComplexPoly var0, int var1) {
        ComplexPoly var2 = new ComplexPoly(new Complex((double)var1, 0.0D));
        return minus(var0, var2);
    }

    public ComplexPoly times(ComplexPoly var1) {
        int var2 = this.deg + var1.deg;
        ComplexPoly var3 = new ComplexPoly(var2);

        for(int var4 = 0; var4 <= this.deg; ++var4) {
            for(int var5 = 0; var5 <= var1.deg; ++var5) {
                var3.coeff[var4 + var5].plusEquals(this.coeff[var4].times(var1.coeff[var5]));
            }
        }

        return var3;
    }

    public static ComplexPoly times(ComplexPoly var0, ComplexPoly var1) {
        int var2 = var0.deg + var1.deg;
        ComplexPoly var3 = new ComplexPoly(var2);

        for(int var4 = 0; var4 <= var0.deg; ++var4) {
            for(int var5 = 0; var5 <= var1.deg; ++var5) {
                var3.coeff[var4 + var5].plusEquals(var0.coeff[var4].times(var1.coeff[var5]));
            }
        }

        return var3;
    }

    public ComplexPoly times(Complex var1) {
        ComplexPoly var2 = new ComplexPoly(this.deg);

        for(int var3 = 0; var3 <= this.deg; ++var3) {
            var2.coeff[var3] = this.coeff[var3].times(var1);
        }

        return var2;
    }

    public static ComplexPoly times(ComplexPoly var0, Complex var1) {
        ComplexPoly var2 = new ComplexPoly(var0.deg);

        for(int var3 = 0; var3 <= var0.deg; ++var3) {
            var2.coeff[var3] = var0.coeff[var3].times(var1);
        }

        return var2;
    }

    public ComplexPoly times(double var1) {
        ComplexPoly var3 = new ComplexPoly(this.deg);

        for(int var4 = 0; var4 <= this.deg; ++var4) {
            var3.coeff[var4] = this.coeff[var4].times(new Complex(var1, 0.0D));
        }

        return var3;
    }

    public static ComplexPoly times(ComplexPoly var0, double var1) {
        ComplexPoly var3 = new ComplexPoly(var0.deg);

        for(int var4 = 0; var4 <= var0.deg; ++var4) {
            var3.coeff[var4] = var0.coeff[var4].times(new Complex(var1, 0.0D));
        }

        return var3;
    }

    public ComplexPoly nthDerivative(int var1) {
        ComplexPoly var2;
        if (var1 > this.deg) {
            var2 = new ComplexPoly(0.0D);
        } else {
            new ComplexPoly(this.deg - var1);
            Complex[] var3 = Complex.oneDarray(this.deg - var1 + 1);
            int var4 = this.deg - var1;

            for(int var5 = this.deg; var5 > var1 - 1; --var5) {
                var3[var4] = Complex.copy(this.coeff[var5]);

                for(int var6 = 0; var6 < var1; ++var6) {
                    var3[var4] = Complex.times(var3[var4], (double)(var5 - var6));
                }

                --var4;
            }

            var2 = new ComplexPoly(var3);
        }

        return var2;
    }

    public Complex evaluate(Complex var1) {
        new Complex();
        Complex var2;
        if (this.deg == 0) {
            var2 = Complex.copy(this.coeff[0]);
        } else {
            var2 = Complex.copy(this.coeff[this.deg]);

            for(int var3 = this.deg - 1; var3 >= 0; --var3) {
                var2 = Complex.plus(Complex.times(var2, var1), this.coeff[var3]);
            }
        }

        return var2;
    }

    public Complex evaluate(double var1) {
        Complex var3 = new Complex(var1, 0.0D);
        new Complex();
        Complex var4;
        if (this.deg == 0) {
            var4 = Complex.copy(this.coeff[0]);
        } else {
            var4 = Complex.copy(this.coeff[this.deg]);

            for(int var5 = this.deg - 1; var5 >= 0; --var5) {
                var4 = Complex.plus(Complex.times(var4, var3), this.coeff[var5]);
            }
        }

        return var4;
    }

    public Complex nthDerivEvaluate(int var1, Complex var2) {
        new Complex();
        Complex[] var4 = Complex.oneDarray(this.deg + 1);
        Complex var3;
        if (var1 == 0) {
            var3 = this.evaluate(var2);
            System.out.println("n = 0 in ComplexPoly.nthDerivative");
            System.out.println("polynomial itself evaluated and returned");
        } else {
            ComplexPoly var5 = this.nthDerivative(var1);
            var3 = var5.evaluate(var2);
        }

        return var3;
    }

    public Complex nthDerivEvaluate(int var1, double var2) {
        Complex var4 = new Complex(var2, 0.0D);
        return this.nthDerivEvaluate(var1, var4);
    }

    public Complex[] roots() {
        boolean var1 = true;
        Complex var2 = new Complex(0.0D, 0.0D);
        return this.roots(var1, var2);
    }

    public Complex[] rootsNoMessages() {
        this.suppressRootsErrorMessages = true;
        return this.roots();
    }

    public Complex[] roots(boolean var1) {
        Complex var2 = new Complex(0.0D, 0.0D);
        return this.roots(var1, var2);
    }

    public Complex[] roots(Complex var1) {
        boolean var2 = true;
        return this.roots(var2, var1);
    }

    public Complex[] roots(boolean var1, Complex var2) {
        Complex[] var3 = Complex.oneDarray(this.deg);
        if (this.deg == 0) {
            if (!this.suppressRootsErrorMessages) {
                System.out.println("degree of the polynomial is zero in the method ComplexPoly.roots");
                System.out.println("null returned");
            }

            return null;
        } else {
            int var4 = 0;

            int var5;
            for(var5 = 1; var5 <= this.deg; ++var5) {
                if (this.coeff[var5].isZero()) {
                    ++var4;
                }
            }

            if (var4 == this.deg) {
                if (!this.suppressRootsErrorMessages) {
                    System.out.println("polynomial coefficients above the zeroth are all zero in the method ComplexPoly.roots");
                    System.out.println("null returned");
                }

                return null;
            } else {
                var5 = 0;
                int var6 = 0;

                int var7;
                for(var7 = 0; var7 <= this.deg; ++var7) {
                    if (!this.coeff[var7].isZero()) {
                        ++var5;
                        var6 = var7;
                    }
                }

                if (var5 == 1) {
                    if (!this.suppressRootsErrorMessages) {
                        System.out.println("all polynomial coefficients except a[" + var6 + "] are zero in the method ComplexPoly.roots");
                        System.out.println("all roots returned as zero");
                    }

                    for(var7 = 0; var7 < this.deg; ++var7) {
                        var3[var7] = Complex.zero();
                    }

                    return var3;
                } else {
                    boolean var12 = true;
                    int var8 = 0;
                    int var9 = 0;

                    while(var12) {
                        if (this.coeff[var8].isZero()) {
                            ++var9;
                            ++var8;
                        } else {
                            var12 = false;
                        }
                    }

                    int var10;
                    if (var9 > 0) {
                        this.degwz = this.deg - var9;
                        this.coeffwz = Complex.oneDarray(this.degwz + 1);

                        for(var10 = 0; var10 <= this.degwz; ++var10) {
                            this.coeffwz[var10] = this.coeff[var10 + var9].copy();
                        }
                    } else {
                        this.degwz = this.deg;
                        this.coeffwz = Complex.oneDarray(this.degwz + 1);

                        for(var10 = 0; var10 <= this.degwz; ++var10) {
                            this.coeffwz[var10] = this.coeff[var10].copy();
                        }
                    }

                    Complex[] var13 = Complex.oneDarray(this.degwz);
                    switch(this.degwz) {
                        case 1:
                            var13[0] = Complex.negate(this.coeffwz[0].over(this.coeffwz[1]));
                            break;
                        case 2:
                            var13 = quadratic(this.coeffwz[0], this.coeffwz[1], this.coeffwz[2]);
                            break;
                        case 3:
                            var13 = cubic(this.coeffwz[0], this.coeffwz[1], this.coeffwz[2], this.coeffwz[3]);
                            break;
                        default:
                            var13 = this.laguerreAll(var1, var2);
                    }

                    int var11;
                    for(var11 = 0; var11 < this.degwz; ++var11) {
                        var3[var11] = var13[var11].copy();
                    }

                    if (var9 > 0) {
                        for(var11 = this.degwz; var11 < this.deg; ++var11) {
                            var3[var11] = Complex.zero();
                        }
                    }

                    return var3;
                }
            }
        }
    }

    public static Complex[] quadratic(Complex var0, Complex var1, Complex var2) {
        double var3 = 1.0D;
        new Complex();
        new Complex();
        new Complex();
        Complex[] var8 = Complex.oneDarray(2);
        Complex var7 = var1.conjugate();
        Complex var5 = Complex.sqrt(Complex.square(var1).minus(var2.times(var0).times(4.0D)));
        Complex var6 = var7.times(var5);
        if (var6.getReal() < 0.0D) {
            var3 = -1.0D;
        }

        var5 = var5.over(var3).plus(var1).over(-2.0D);
        var8[0] = Complex.over(var5, var2);
        var8[1] = Complex.over(var0, var5);
        return var8;
    }

    public static Complex[] quadratic(double var0, double var2, double var4) {
        Complex var6 = new Complex(var4, 0.0D);
        Complex var7 = new Complex(var2, 0.0D);
        Complex var8 = new Complex(var0, 0.0D);
        return quadratic(var8, var7, var6);
    }

    public static Complex[] cubic(Complex var0, Complex var1, Complex var2, Complex var3) {
        Complex var4 = var2.over(var3);
        Complex var5 = var1.over(var3);
        Complex var6 = var0.over(var3);
        Complex[] var7 = Complex.oneDarray(3);
        Complex var8 = var4.times(var4).minus(var5.times(3.0D)).over(9.0D);
        Complex var9 = var4.times(var4).times(var4).times(2.0D).minus(var4.times(var5).times(9.0D)).plus(var6.times(27.0D)).over(54.0D);
        Complex var10 = Complex.plusOne();
        Complex var11 = Complex.sqrt(var9.times(var9).minus(var8.times(var8).times(var8)));
        Complex var12 = var9.conjugate();
        if (var12.times(var11).getReal() < 0.0D) {
            var10 = Complex.minusOne();
        }

        Complex var13 = Complex.pow(var9.plus(var10.times(var11)), 0.3333333333333333D).times(Complex.minusOne());
        Complex var14 = null;
        if (var13.isZero()) {
            var14 = Complex.zero();
        } else {
            var14 = var8.over(var13);
        }

        Complex var15 = var13.plus(var14);
        Complex var16 = var13.minus(var14);
        Complex var17 = var15.times(Complex.minusOne());
        Complex var18 = var4.over(3.0D);
        Complex var19 = new Complex(0.0D, Math.sqrt(3.0D) / 2.0D);
        var7[0] = var15.minus(var18);
        var7[1] = var17.over(2.0D).minus(var18).plus(var19.times(var16));
        var7[2] = var17.over(2.0D).minus(var18).minus(var19.times(var16));
        return var7;
    }

    public static Complex[] cubic(double var0, double var2, double var4, double var6) {
        Complex var8 = new Complex(var6, 0.0D);
        Complex var9 = new Complex(var4, 0.0D);
        Complex var10 = new Complex(var2, 0.0D);
        Complex var11 = new Complex(var0, 0.0D);
        return cubic(var11, var10, var9, var8);
    }

    public static Complex laguerre(Complex var0, Complex[] var1, int var2) {
        double var3 = 1.0E-7D;
        byte var5 = 8;
        short var6 = 1000;
        int var7 = var5 * var6;
        boolean var8 = false;
        double[] var9 = new double[]{0.5D, 0.25D, 0.75D, 0.13D, 0.38D, 0.62D, 0.88D, 1.0D};
        new Complex();
        new Complex();
        new Complex();
        new Complex();
        new Complex();
        new Complex();
        new Complex();
        new Complex();
        new Complex();
        new Complex();
        new Complex();
        new Complex();
        Complex var22 = new Complex();
        Complex var23 = new Complex();
        double var24 = 0.0D;
        double var26 = 0.0D;
        double var28 = 0.0D;
        double var30 = 0.0D;

        Complex var10;
        for(int var32 = 1; var32 <= var7; ++var32) {
            Complex var11 = Complex.copy(var1[var2]);
            var28 = Complex.abs(var11);
            Complex var13;
            Complex var12 = var13 = Complex.zero();
            var30 = Complex.abs(var0);

            for(int var33 = var2 - 1; var33 >= 0; --var33) {
                var13 = Complex.plus(Complex.times(var0, var13), var12);
                var12 = Complex.plus(Complex.times(var0, var12), var11);
                var11 = Complex.plus(Complex.times(var0, var11), var1[var33]);
                var28 = Complex.abs(var11) + var30 * var28;
            }

            var28 *= var3;
            if (Complex.abs(var11) <= var28) {
                var10 = Complex.copy(var0);
                return var10;
            }

            Complex var14 = Complex.over(var12, var11);
            Complex var15 = Complex.square(var14);
            Complex var16 = Complex.minus(var15, Complex.times(2.0D, Complex.over(var13, var11)));
            Complex var17 = Complex.sqrt(Complex.times((double)(var2 - 1), Complex.minus(Complex.times((double)var2, var16), var15)));
            Complex var18 = Complex.plus(var14, var17);
            Complex var19 = Complex.minus(var14, var17);
            var24 = Complex.abs(var18);
            var26 = Complex.abs(var19);
            if (var24 < var26) {
                var18 = var19;
            }

            var22.setReal((double)var2);
            var23.setReal(Math.cos((double)var32));
            var23.setImag(Math.sin((double)var32));
            Complex var20 = Math.max(var24, var26) > 0.0D ? Complex.over(var22, var18) : Complex.times(Math.exp(1.0D + var30), var23);
            Complex var21 = Complex.minus(var0, var20);
            if (Complex.isEqual(var0, var21)) {
                var10 = Complex.copy(var0);
                return var10;
            }

            if (var32 % var6 != 0) {
                var0 = Complex.copy(var21);
            } else {
                var0 = Complex.minus(var0, Complex.times(var9[var32 / var6 - 1], var20));
            }
        }

        var10 = Complex.copy(var0);
        System.out.println("Maximum number of iterations exceeded in laguerre");
        System.out.println("root returned at this point");
        return var10;
    }

    public Complex[] laguerreAll() {
        Complex var1 = new Complex(0.0D, 0.0D);
        boolean var2 = true;
        return this.laguerreAll(var2, var1);
    }

    public Complex[] laguerreAll(Complex var1) {
        boolean var2 = true;
        return this.laguerreAll(var2, var1);
    }

    public Complex[] laguerreAll(boolean var1) {
        Complex var2 = new Complex(0.0D, 0.0D);
        return this.laguerreAll(var1, var2);
    }

    public Complex[] laguerreAll(boolean var1, Complex var2) {
        int var3 = this.degwz;
        double var4 = 2.0E-6D;
        new Complex();
        new Complex();
        new Complex();
        Complex[] var9 = new Complex[var3 + 1];
        Complex[] var10 = new Complex[var3 + 1];

        int var11;
        for(var11 = 0; var11 <= var3; ++var11) {
            var9[var11] = Complex.copy(this.coeffwz[var11]);
        }

        Complex var6;
        int var12;
        for(var11 = var3; var11 >= 1; --var11) {
            var6 = Complex.copy(var2);
            var6 = laguerre(var6, var9, var11);
            if (Math.abs(var6.getImag()) <= 2.0D * var4 * Math.abs(var6.getReal())) {
                var6.setImag(0.0D);
            }

            var10[var11] = Complex.copy(var6);
            Complex var7 = Complex.copy(var9[var11]);

            for(var12 = var11 - 1; var12 >= 0; --var12) {
                Complex var8 = Complex.copy(var9[var12]);
                var9[var12] = Complex.copy(var7);
                var7 = var6.times(var7).plus(var8);
            }
        }

        if (var1) {
            for(var11 = 1; var11 <= var3; ++var11) {
                var10[var11] = laguerre(var10[var11], this.coeffwz, var3);
            }
        }

        for(var11 = 2; var11 <= var3; ++var11) {
            var6 = Complex.copy(var10[var11]);
            boolean var13 = false;

            for(var12 = var11 - 1; var12 >= 1 && var10[var12].getReal() > var6.getReal(); --var12) {
                var10[var12 + 1] = Complex.copy(var10[var12]);
            }

            var10[var12 + 1] = Complex.copy(var6);
        }

        for(var11 = 0; var11 < var3; ++var11) {
            var10[var11] = Complex.copy(var10[var11 + 1]);
        }

        return var10;
    }
}
