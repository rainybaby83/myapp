//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package flanagan.math;

import flanagan.complex.Complex;
import flanagan.complex.ComplexPoly;
import flanagan.io.FileOutput;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

public class Polynomial {
    private int deg = 0;
    private int degwz = 0;
    private double[] coeff;
    private double[] coeffwz;
    private boolean suppressRootsErrorMessages = false;

    public Polynomial(int var1) {
        this.deg = var1;
        this.coeff = new double[var1 + 1];
    }

    public Polynomial(double[] var1) {
        this.deg = var1.length - 1;
        this.coeff = new double[this.deg + 1];

        for(int var2 = 0; var2 <= this.deg; ++var2) {
            this.coeff[var2] = var1[var2];
        }

    }

    public Polynomial(float[] var1) {
        this.deg = var1.length - 1;
        this.coeff = new double[this.deg + 1];

        for(int var2 = 0; var2 <= this.deg; ++var2) {
            this.coeff[var2] = (double)var1[var2];
        }

    }

    public Polynomial(long[] var1) {
        this.deg = var1.length - 1;
        this.coeff = new double[this.deg + 1];

        for(int var2 = 0; var2 <= this.deg; ++var2) {
            this.coeff[var2] = (double)var1[var2];
        }

    }

    public Polynomial(int[] var1) {
        this.deg = var1.length - 1;
        this.coeff = new double[this.deg + 1];

        for(int var2 = 0; var2 <= this.deg; ++var2) {
            this.coeff[var2] = (double)var1[var2];
        }

    }

    public Polynomial(ArrayList<Object> var1) {
        this.deg = var1.size() - 1;
        this.coeff = new double[this.deg + 1];

        for(int var2 = 0; var2 <= this.deg; ++var2) {
            int var3 = this.getTypeCode(var1.get(var2));
            switch(var3) {
                case 1:
                    this.coeff[var2] = (double)(Byte)var1.get(var2);
                    break;
                case 2:
                    this.coeff[var2] = (double)(Short)var1.get(var2);
                    break;
                case 3:
                    this.coeff[var2] = (double)(Integer)var1.get(var2);
                    break;
                case 4:
                    this.coeff[var2] = (double)(Long)var1.get(var2);
                    break;
                case 5:
                    this.coeff[var2] = (double)(Float)var1.get(var2);
                    break;
                case 6:
                    this.coeff[var2] = (Double)var1.get(var2);
                    break;
                case 7:
                    this.coeff[var2] = ((BigInteger)var1.get(var2)).doubleValue();
                    break;
                case 8:
                    this.coeff[var2] = ((BigDecimal)var1.get(var2)).doubleValue();
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
        } else if (var1 instanceof BigInteger) {
            var2 = 7;
        } else if (var1 instanceof BigDecimal) {
            var2 = 8;
        }

        return var2;
    }

    public Polynomial(double var1) {
        this.deg = 0;
        this.coeff = new double[1];
        this.coeff[0] = var1;
    }

    public Polynomial(double var1, double var3) {
        this.deg = 1;
        this.coeff = new double[2];
        this.coeff[0] = var1;
        this.coeff[1] = var3;
    }

    public Polynomial(double var1, double var3, double var5) {
        this.deg = 2;
        this.coeff = new double[3];
        this.coeff[0] = var1;
        this.coeff[1] = var3;
        this.coeff[2] = var5;
    }

    public Polynomial(double var1, double var3, double var5, double var7) {
        this.deg = 3;
        this.coeff = new double[4];
        this.coeff[0] = var1;
        this.coeff[1] = var3;
        this.coeff[2] = var5;
        this.coeff[3] = var7;
    }

    public Polynomial reducePoly() {
        Polynomial var1 = null;
        int var2 = this.deg;
        boolean var3 = true;
        int var4 = this.deg;

        while(var3) {
            if (this.coeff[var4] == 0.0D) {
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
            var1 = new Polynomial(var2);

            for(int var5 = 0; var5 <= var2; ++var5) {
                var1.resetCoeff(var5, this.coeff[var5]);
            }
        }

        return var1;
    }

    public static Polynomial reducePoly(Polynomial var0) {
        Polynomial var1 = null;
        if (var1 != null) {
            int var2 = var0.getDeg();
            int var3 = var2;
            boolean var4 = true;
            int var5 = var2;

            while(var4) {
                if (var0.coeff[var5] == 0.0D) {
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
                var1 = new Polynomial(var3);

                for(int var6 = 0; var6 <= var3; ++var6) {
                    var1.resetCoeff(var6, var0.coeff[var6]);
                }
            }
        }

        return var1;
    }

    public static Polynomial rootsToPoly(double[] var0) {
        if (var0 == null) {
            return null;
        } else {
            int var1 = var0.length;
            double[] var2 = new double[]{-var0[0], 1.0D};
            Polynomial var3 = new Polynomial(var2);

            for(int var4 = 1; var4 < var1; ++var4) {
                var2[0] = -var0[var4];
                Polynomial var5 = new Polynomial(var2);
                var3 = var3.times(var5);
            }

            return var3;
        }
    }

    public void resetPoly(double[] var1) {
        if (this.deg + 1 != var1.length) {
            throw new IllegalArgumentException("array lengths do not match");
        } else {
            for(int var2 = 0; var2 < this.deg; ++var2) {
                this.coeff[var2] = var1[var2];
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
                        this.coeff[var2] = (double)(Byte)var1.get(var2);
                        break;
                    case 2:
                        this.coeff[var2] = (double)(Short)var1.get(var2);
                        break;
                    case 3:
                        this.coeff[var2] = (double)(Integer)var1.get(var2);
                        break;
                    case 4:
                        this.coeff[var2] = (double)(Long)var1.get(var2);
                        break;
                    case 5:
                        this.coeff[var2] = (double)(Float)var1.get(var2);
                        break;
                    case 6:
                        this.coeff[var2] = (Double)var1.get(var2);
                        break;
                    case 7:
                        this.coeff[var2] = ((BigInteger)var1.get(var2)).doubleValue();
                        break;
                    case 8:
                        this.coeff[var2] = ((BigDecimal)var1.get(var2)).doubleValue();
                        break;
                    default:
                        throw new IllegalArgumentException("Type code, " + var3 + ", not recognised");
                }
            }

        }
    }

    public void resetCoeff(int var1, double var2) {
        this.coeff[var1] = var2;
    }

    public Polynomial copy() {
        if (this == null) {
            return null;
        } else {
            Polynomial var1 = new Polynomial(this.deg);

            for(int var2 = 0; var2 <= this.deg; ++var2) {
                var1.coeff[var2] = this.coeff[var2];
            }

            var1.deg = this.deg;
            var1.degwz = this.degwz;
            var1.coeffwz = Conv.copy(this.coeffwz);
            return var1;
        }
    }

    public static Polynomial copy(Polynomial var0) {
        if (var0 == null) {
            return null;
        } else {
            Polynomial var1 = new Polynomial(var0.deg);

            for(int var2 = 0; var2 <= var0.deg; ++var2) {
                var1.coeff[var2] = var0.coeff[var2];
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

    public double[] coefficientsCopy() {
        double[] var1 = new double[this.deg + 1];

        for(int var2 = 0; var2 <= this.deg; ++var2) {
            var1[var2] = this.coeff[var2];
        }

        return var1;
    }

    public double[] coefficientsReference() {
        return this.coeff;
    }

    public double getCoefficient(int var1) {
        return this.coeff[var1];
    }

    public int getDeg() {
        return this.deg;
    }

    public String toString() {
        String var1 = "";
        var1 = var1 + this.coeff[0];
        if (this.deg > 0) {
            var1 = var1 + " + (" + this.coeff[1] + ").x";
        }

        for(int var2 = 2; var2 <= this.deg; ++var2) {
            var1 = var1 + " + (" + this.coeff[var2] + ").x^" + var2;
        }

        return var1;
    }

    public ComplexPoly toComplexPoly() {
        ComplexPoly var1 = new ComplexPoly(this);
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
        var2.println("Output File for a Polynomial");
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
        String var1 = "PolynomialOut";
        this.printToText(var1);
    }

    public ArrayList<ComplexPoly> sTransform() {
        return sTransform(this.coeff);
    }

    public static ArrayList<ComplexPoly> sTransform(Polynomial var0) {
        return sTransform(var0.coefficientsCopy());
    }

    public static ArrayList<ComplexPoly> sTransform(double[] var0) {
        int var1 = var0.length;
        ComplexPoly[] var2 = new ComplexPoly[var1];
        ComplexPoly[] var3 = new ComplexPoly[var1];
        ComplexPoly var4 = null;
        new ComplexPoly(Complex.plusOne());

        int var6;
        for(var6 = 0; var6 < var1; ++var6) {
            var2[var6] = new ComplexPoly(new Complex(var0[var6] * (double)Fmath.factorial(var6), 0.0D));
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

    public boolean equals(Polynomial var1) {
        return this.isEqual(var1);
    }

    public boolean isEqual(Polynomial var1) {
        boolean var2 = false;
        int var3 = this.getDeg();
        int var4 = var1.getDeg();
        if (var3 == var4) {
            boolean var5 = true;
            int var6 = 0;

            while(var5) {
                if (this.coeff[var6] != var1.getCoefficient(var6)) {
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

    public static boolean isEqual(Polynomial var0, Polynomial var1) {
        boolean var2 = false;
        int var3 = var0.getDeg();
        int var4 = var1.getDeg();
        if (var3 == var4) {
            boolean var5 = true;
            int var6 = 0;

            while(var5) {
                if (var0.getCoefficient(var6) != var1.getCoefficient(var6)) {
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

    public Polynomial plus(Polynomial var1) {
        Polynomial var2 = null;
        int var3;
        if (var1.deg <= this.deg) {
            var2 = new Polynomial(this.deg);

            for(var3 = var1.deg + 1; var3 <= this.deg; ++var3) {
                var2.coeff[var3] = this.coeff[var3];
            }

            for(var3 = 0; var3 <= var1.deg; ++var3) {
                var2.coeff[var3] = this.coeff[var3] + var1.coeff[var3];
            }
        } else {
            var2 = new Polynomial(var1.deg);

            for(var3 = this.deg + 1; var3 <= var1.deg; ++var3) {
                var2.coeff[var3] = var1.coeff[var3];
            }

            for(var3 = 0; var3 <= this.deg; ++var3) {
                var2.coeff[var3] = this.coeff[var3] + var1.coeff[var3];
            }
        }

        return var2;
    }

    public static Polynomial plus(Polynomial var0, Polynomial var1) {
        Polynomial var2 = null;
        int var3;
        if (var1.deg <= var0.deg) {
            var2 = new Polynomial(var0.deg);

            for(var3 = var1.deg + 1; var3 <= var0.deg; ++var3) {
                var2.coeff[var3] = var0.coeff[var3];
            }

            for(var3 = 0; var3 <= var1.deg; ++var3) {
                var2.coeff[var3] = var0.coeff[var3] + var1.coeff[var3];
            }
        } else {
            var2 = new Polynomial(var1.deg);

            for(var3 = var0.deg + 1; var3 <= var1.deg; ++var3) {
                var2.coeff[var3] = var1.coeff[var3];
            }

            for(var3 = 0; var3 <= var0.deg; ++var3) {
                var2.coeff[var3] = var0.coeff[var3] + var1.coeff[var3];
            }
        }

        return var2;
    }

    public Polynomial plus(double var1) {
        Polynomial var3 = new Polynomial(var1);
        return this.plus(var3);
    }

    public static Polynomial plus(Polynomial var0, double var1) {
        Polynomial var3 = new Polynomial(var1);
        return plus(var0, var3);
    }

    public Polynomial plus(int var1) {
        Polynomial var2 = new Polynomial((double)var1);
        return this.plus(var2);
    }

    public static Polynomial plus(Polynomial var0, int var1) {
        Polynomial var2 = new Polynomial((double)var1);
        return plus(var0, var2);
    }

    public Polynomial minus(Polynomial var1) {
        Polynomial var2 = null;
        int var3;
        if (var1.deg <= this.deg) {
            var2 = new Polynomial(this.deg);

            for(var3 = var1.deg + 1; var3 <= this.deg; ++var3) {
                var2.coeff[var3] = this.coeff[var3];
            }

            for(var3 = 0; var3 <= var1.deg; ++var3) {
                var2.coeff[var3] = this.coeff[var3] - var1.coeff[var3];
            }
        } else {
            var2 = new Polynomial(var1.deg);

            for(var3 = this.deg + 1; var3 <= var1.deg; ++var3) {
                var2.coeff[var3] = -var1.coeff[var3];
            }

            for(var3 = 0; var3 <= this.deg; ++var3) {
                var2.coeff[var3] = this.coeff[var3] - var1.coeff[var3];
            }
        }

        return var2;
    }

    public Polynomial minus(double var1) {
        Polynomial var3 = new Polynomial(var1);
        return this.minus(var3);
    }

    public Polynomial minus(int var1) {
        Polynomial var2 = new Polynomial((double)var1);
        return this.minus(var2);
    }

    public static Polynomial minus(Polynomial var0, Polynomial var1) {
        Polynomial var2 = null;
        int var3;
        if (var1.deg <= var0.deg) {
            var2 = new Polynomial(var0.deg);

            for(var3 = var1.deg + 1; var3 <= var0.deg; ++var3) {
                var2.coeff[var3] = var0.coeff[var3];
            }

            for(var3 = 0; var3 <= var1.deg; ++var3) {
                var2.coeff[var3] = var0.coeff[var3] - var1.coeff[var3];
            }
        } else {
            var2 = new Polynomial(var1.deg);

            for(var3 = var0.deg + 1; var3 <= var1.deg; ++var3) {
                var2.coeff[var3] = -var1.coeff[var3];
            }

            for(var3 = 0; var3 <= var0.deg; ++var3) {
                var2.coeff[var3] = var0.coeff[var3] - var1.coeff[var3];
            }
        }

        return var2;
    }

    public static Polynomial minus(Polynomial var0, double var1) {
        Polynomial var3 = new Polynomial(var1);
        return minus(var0, var3);
    }

    public static Polynomial minus(Polynomial var0, int var1) {
        Polynomial var2 = new Polynomial((double)var1);
        return minus(var0, var2);
    }

    public Polynomial times(Polynomial var1) {
        int var2 = this.deg + var1.deg;
        Polynomial var3 = new Polynomial(var2);

        for(int var4 = 0; var4 <= this.deg; ++var4) {
            for(int var5 = 0; var5 <= var1.deg; ++var5) {
                var3.coeff[var4 + var5] += this.coeff[var4] * var1.coeff[var5];
            }
        }

        return var3;
    }

    public static Polynomial times(Polynomial var0, Polynomial var1) {
        int var2 = var0.deg + var1.deg;
        Polynomial var3 = new Polynomial(var2);

        for(int var4 = 0; var4 <= var0.deg; ++var4) {
            for(int var5 = 0; var5 <= var1.deg; ++var5) {
                var3.coeff[var4 + var5] += var0.coeff[var4] * var1.coeff[var5];
            }
        }

        return var3;
    }

    public Polynomial times(double var1) {
        Polynomial var3 = new Polynomial(this.deg);

        for(int var4 = 0; var4 <= this.deg; ++var4) {
            var3.coeff[var4] = this.coeff[var4] * var1;
        }

        return var3;
    }

    public static Polynomial times(Polynomial var0, double var1) {
        Polynomial var3 = new Polynomial(var0.deg);

        for(int var4 = 0; var4 <= var0.deg; ++var4) {
            var3.coeff[var4] = var0.coeff[var4] * var1;
        }

        return var3;
    }

    public Polynomial times(int var1) {
        Polynomial var2 = new Polynomial(this.deg);

        for(int var3 = 0; var3 <= this.deg; ++var3) {
            var2.coeff[var3] = this.coeff[var3] * (double)var1;
        }

        return var2;
    }

    public static Polynomial times(Polynomial var0, int var1) {
        Polynomial var2 = new Polynomial(var0.deg);

        for(int var3 = 0; var3 <= var0.deg; ++var3) {
            var2.coeff[var3] = var0.coeff[var3] * (double)var1;
        }

        return var2;
    }

    public Polynomial nthDerivative(int var1) {
        Polynomial var2;
        if (var1 > this.deg) {
            var2 = new Polynomial(0.0D);
        } else {
            new Polynomial(this.deg - var1);
            double[] var3 = new double[this.deg - var1 + 1];
            int var4 = this.deg - var1;

            for(int var5 = this.deg; var5 > var1 - 1; --var5) {
                var3[var4] = this.coeff[var5];

                for(int var6 = 0; var6 < var1; ++var6) {
                    var3[var4] *= (double)(var5 - var6);
                }

                --var4;
            }

            var2 = new Polynomial(var3);
        }

        return var2;
    }

    public double evaluate(double var1) {
        double var3 = 0.0D;
        if (this.deg == 0) {
            var3 = this.coeff[0];
        } else {
            var3 = this.coeff[this.deg];

            for(int var5 = this.deg - 1; var5 >= 0; --var5) {
                var3 = var3 * var1 + this.coeff[var5];
            }
        }

        return var3;
    }

    public double nthDerivEvaluate(int var1, double var2) {
        double var4 = 0.0D;
        double[] var6 = new double[this.deg + 1];
        if (var1 == 0) {
            var4 = this.evaluate(var2);
            System.out.println("n = 0 in Polynomial.nthDerivative");
            System.out.println("polynomial itself evaluated and returned");
        } else {
            Polynomial var7 = this.nthDerivative(var1);
            var4 = var7.evaluate(var2);
        }

        return var4;
    }

    public Complex[] roots() {
        ComplexPoly var1 = new ComplexPoly(this);
        return var1.roots();
    }

    public Complex[] rootsNoMessages() {
        ComplexPoly var1 = new ComplexPoly(this);
        return var1.rootsNoMessages();
    }

    public Complex[] roots(boolean var1) {
        ComplexPoly var2 = new ComplexPoly(this);
        return var2.roots(var1);
    }

    public Complex[] roots(double var1) {
        ComplexPoly var3 = new ComplexPoly(this);
        return var3.roots(new Complex(var1, 0.0D));
    }

    public Complex[] roots(Complex var1) {
        ComplexPoly var2 = new ComplexPoly(this);
        return var2.roots(var1);
    }

    public Complex[] roots(boolean var1, double var2) {
        ComplexPoly var4 = new ComplexPoly(this);
        return var4.roots(new Complex(var2, 0.0D));
    }

    public static Complex[] quadratic(double var0, double var2, double var4) {
        return ComplexPoly.quadratic(new Complex(var0, 0.0D), new Complex(var2, 0.0D), new Complex(var4, 0.0D));
    }

    public static Complex[] cubic(double var0, double var2, double var4, double var6) {
        return ComplexPoly.cubic(new Complex(var0, 0.0D), new Complex(var2, 0.0D), new Complex(var4, 0.0D), new Complex(var6, 0.0D));
    }

    public static Complex laguerre(double var0, double[] var2, int var3) {
        ArrayMaths var4 = new ArrayMaths(var2);
        return ComplexPoly.laguerre(new Complex(var0, 0.0D), var4.array_as_Complex(), var3);
    }

    public static Complex laguerre(Complex var0, double[] var1, int var2) {
        ArrayMaths var3 = new ArrayMaths(var1);
        return ComplexPoly.laguerre(var0, var3.array_as_Complex(), var2);
    }

    public Complex[] laguerreAll() {
        ComplexPoly var1 = new ComplexPoly(this);
        return var1.laguerreAll();
    }

    public Complex[] laguerreAll(double var1) {
        ComplexPoly var3 = new ComplexPoly(this);
        return var3.laguerreAll(new Complex(var1, 0.0D));
    }

    public Complex[] laguerreAll(Complex var1) {
        ComplexPoly var2 = new ComplexPoly(this);
        return var2.laguerreAll(var1);
    }

    public Complex[] laguerreAll(boolean var1) {
        ComplexPoly var2 = new ComplexPoly(this);
        return var2.laguerreAll(var1);
    }

    public Complex[] laguerreAll(boolean var1, double var2) {
        ComplexPoly var4 = new ComplexPoly(this);
        return var4.laguerreAll(var1, new Complex(var2, 0.0D));
    }

    public Complex[] laguerreAll(boolean var1, Complex var2) {
        ComplexPoly var3 = new ComplexPoly(this);
        return var3.laguerreAll(var1, var2);
    }
}
