//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package flanagan.complex;

import flanagan.math.Fmath;
import java.io.IOException;

public class Complex {
    private double real = 0.0D;
    private double imag = 0.0D;
    private static char jori = 'j';
    private static boolean infOption = true;

    public Complex() {
        this.real = 0.0D;
        this.imag = 0.0D;
    }

    public Complex(double var1, double var3) {
        this.real = var1;
        this.imag = var3;
    }

    public Complex(double var1) {
        this.real = var1;
        this.imag = 0.0D;
    }

    public Complex(Complex var1) {
        this.real = var1.real;
        this.imag = var1.imag;
    }

    public void setReal(double var1) {
        this.real = var1;
    }

    public void setImag(double var1) {
        this.imag = var1;
    }

    public void reset(double var1, double var3) {
        this.real = var1;
        this.imag = var3;
    }

    public void polarRad(double var1, double var3) {
        this.real = var1 * Math.cos(var3);
        this.imag = var1 * Math.sin(var3);
    }

    public void polar(double var1, double var3) {
        this.real = var1 * Math.cos(var3);
        this.imag = var1 * Math.sin(var3);
    }

    public void polarDeg(double var1, double var3) {
        var3 = Math.toRadians(var3);
        this.real = var1 * Math.cos(var3);
        this.imag = var1 * Math.sin(var3);
    }

    public double getReal() {
        return this.real;
    }

    public double getImag() {
        return this.imag;
    }

    public static final synchronized Complex readComplex(String var0) {
        boolean var1 = true;
        String var2 = "";
        boolean var3 = false;
        System.out.print(var0 + " ");
        System.out.flush();

        while(!var3) {
            try {
                int var6 = System.in.read();
                if (var6 >= 0 && (char)var6 != '\n') {
                    var2 = var2 + (char)var6;
                } else {
                    var3 = true;
                }
            } catch (IOException var5) {
                var3 = true;
            }
        }

        return parseComplex(var2);
    }

    public static final synchronized Complex readComplex(String var0, String var1) {
        boolean var2 = true;
        String var3 = "";
        boolean var4 = false;
        System.out.print(var0 + " [default value = " + var1 + "]  ");
        System.out.flush();
        int var5 = 0;

        while(!var4) {
            try {
                int var8 = System.in.read();
                if (var8 >= 0 && (char)var8 != '\n' && (char)var8 != '\r') {
                    var3 = var3 + (char)var8;
                    ++var5;
                } else {
                    if (var5 == 0) {
                        var3 = var1;
                        if ((char)var8 == '\r') {
                            var8 = System.in.read();
                        }
                    }

                    var4 = true;
                }
            } catch (IOException var7) {
                var4 = true;
            }
        }

        return parseComplex(var3);
    }

    public static final synchronized Complex readComplex(String var0, Complex var1) {
        boolean var2 = true;
        String var3 = "";
        boolean var4 = false;
        System.out.print(var0 + " [default value = " + var1 + "]  ");
        System.out.flush();
        int var5 = 0;

        while(!var4) {
            try {
                int var8 = System.in.read();
                if (var8 >= 0 && (char)var8 != '\n' && (char)var8 != '\r') {
                    var3 = var3 + (char)var8;
                    ++var5;
                } else {
                    if (var5 == 0) {
                        if ((char)var8 == '\r') {
                            var8 = System.in.read();
                        }

                        return var1;
                    }

                    var4 = true;
                }
            } catch (IOException var7) {
                var4 = true;
            }
        }

        return parseComplex(var3);
    }

    public static final synchronized Complex readComplex() {
        boolean var0 = true;
        String var1 = "";
        boolean var2 = false;
        System.out.print(" ");
        System.out.flush();

        while(!var2) {
            try {
                int var5 = System.in.read();
                if (var5 >= 0 && (char)var5 != '\n') {
                    var1 = var1 + (char)var5;
                } else {
                    var2 = true;
                }
            } catch (IOException var4) {
                var2 = true;
            }
        }

        return parseComplex(var1);
    }

    public void println(String var1) {
        System.out.println(var1 + " " + this.toString());
    }

    public void println() {
        System.out.println(" " + this.toString());
    }

    public void print(String var1) {
        System.out.print(var1 + " " + this.toString());
    }

    public void print() {
        System.out.print(" " + this.toString());
    }

    public static void println(String var0, Complex[] var1) {
        System.out.println(var0);

        for(int var2 = 0; var2 < var1.length; ++var2) {
            System.out.println(var1[var2].toString() + "  ");
        }

    }

    public static void println(Complex[] var0) {
        for(int var1 = 0; var1 < var0.length; ++var1) {
            System.out.println(var0[var1].toString() + "  ");
        }

    }

    public static void print(String var0, Complex[] var1) {
        System.out.print(var0 + " ");

        for(int var2 = 0; var2 < var1.length; ++var2) {
            System.out.print(var1[var2].toString() + "   ");
        }

        System.out.println();
    }

    public static void print(Complex[] var0) {
        for(int var1 = 0; var1 < var0.length; ++var1) {
            System.out.print(var0[var1].toString() + "  ");
        }

        System.out.println();
    }

    public static Complex truncate(Complex var0, int var1) {
        if (var1 < 0) {
            return var0;
        } else {
            double var2 = var0.getReal();
            double var4 = var0.getImag();
            Complex var6 = new Complex();
            var2 = Fmath.truncate(var2, var1);
            var4 = Fmath.truncate(var4, var1);
            var6.reset(var2, var4);
            return var6;
        }
    }

    public Complex truncate(int var1) {
        if (var1 < 0) {
            return this;
        } else {
            double var2 = this.getReal();
            double var4 = this.getImag();
            Complex var6 = new Complex();
            var2 = Fmath.truncate(var2, var1);
            var4 = Fmath.truncate(var4, var1);
            var6.reset(var2, var4);
            return var6;
        }
    }

    public String toString() {
        char var1 = '+';
        if (this.imag < 0.0D) {
            var1 = '-';
        }

        return this.real + " " + var1 + " " + jori + Math.abs(this.imag);
    }

    public static String toString(Complex var0) {
        char var1 = '+';
        if (var0.imag < 0.0D) {
            var1 = '-';
        }

        return var0.real + " " + var1 + jori + Math.abs(var0.imag);
    }

    public static void setj() {
        jori = 'j';
    }

    public static void seti() {
        jori = 'i';
    }

    public static char getjori() {
        return jori;
    }

    public static Complex parseComplex(String var0) {
        Complex var1 = new Complex();
        var0 = var0.trim();
        double var2 = 1.0D;
        if (var0.charAt(0) == '-') {
            var2 = -1.0D;
            var0 = var0.substring(1);
        }

        int var4 = var0.indexOf(106);
        if (var4 == -1) {
            var4 = var0.indexOf(105);
        }

        if (var4 == -1) {
            throw new NumberFormatException("no i or jScrollPane found");
        } else {
            byte var5 = 1;
            int var6 = var0.indexOf(43);
            if (var6 == -1) {
                var6 = var0.indexOf(45);
                if (var6 > -1) {
                    var5 = -1;
                }
            }

            if (var6 == -1) {
                throw new NumberFormatException("no + or - found");
            } else {
                byte var7 = 0;
                int var9 = var4 + 1;
                int var10 = var0.length();
                String var11 = var0.substring(var7, var6);
                String var12 = var0.substring(var9, var10);
                var1.real = var2 * Double.parseDouble(var11);
                var1.imag = (double)var5 * Double.parseDouble(var12);
                return var1;
            }
        }
    }

    public static Complex valueOf(String var0) {
        return parseComplex(var0);
    }

    public int hashCode() {
        long var1 = Double.doubleToLongBits(this.real);
        long var3 = Double.doubleToLongBits(this.imag);
        int var5 = (int)(var1 ^ var1 >>> 32);
        int var6 = (int)(var3 ^ var3 >>> 32);
        return 7 * (var5 / 10) + 3 * (var6 / 10);
    }

    public static void swap(Complex var0, Complex var1) {
        double var2 = var0.real;
        double var4 = var0.imag;
        var0.reset(var1.real, var1.imag);
        var1.reset(var2, var4);
    }

    public static Complex[] oneDarray(int var0) {
        Complex[] var1 = new Complex[var0];

        for(int var2 = 0; var2 < var0; ++var2) {
            var1[var2] = zero();
        }

        return var1;
    }

    public static Complex[] oneDarray(int var0, double var1, double var3) {
        Complex[] var5 = new Complex[var0];

        for(int var6 = 0; var6 < var0; ++var6) {
            var5[var6] = zero();
            var5[var6].reset(var1, var3);
        }

        return var5;
    }

    public static Complex mean(Complex[] var0) {
        int var1 = var0.length;
        Complex var2 = new Complex(0.0D, 0.0D);

        for(int var3 = 0; var3 < var1; ++var3) {
            var2 = var2.plus(var0[var3]);
        }

        return var2.over((double)var1);
    }

    public static Complex[] oneDarray(int var0, Complex var1) {
        Complex[] var2 = new Complex[var0];

        for(int var3 = 0; var3 < var0; ++var3) {
            var2[var3] = copy(var1);
        }

        return var2;
    }

    public static Complex[][] twoDarray(int var0, int var1) {
        Complex[][] var2 = new Complex[var0][var1];

        for(int var3 = 0; var3 < var0; ++var3) {
            for(int var4 = 0; var4 < var1; ++var4) {
                var2[var3][var4] = zero();
            }
        }

        return var2;
    }

    public static Complex[][] twoDarray(int var0, int var1, double var2, double var4) {
        Complex[][] var6 = new Complex[var0][var1];

        for(int var7 = 0; var7 < var0; ++var7) {
            for(int var8 = 0; var8 < var1; ++var8) {
                var6[var7][var8] = zero();
                var6[var7][var8].reset(var2, var4);
            }
        }

        return var6;
    }

    public static Complex[][] twoDarray(int var0, int var1, Complex var2) {
        Complex[][] var3 = new Complex[var0][var1];

        for(int var4 = 0; var4 < var0; ++var4) {
            for(int var5 = 0; var5 < var1; ++var5) {
                var3[var4][var5] = copy(var2);
            }
        }

        return var3;
    }

    public static Complex[][][] threeDarray(int var0, int var1, int var2) {
        Complex[][][] var3 = new Complex[var0][var1][var2];

        for(int var4 = 0; var4 < var0; ++var4) {
            for(int var5 = 0; var5 < var1; ++var5) {
                for(int var6 = 0; var6 < var2; ++var6) {
                    var3[var4][var5][var6] = zero();
                }
            }
        }

        return var3;
    }

    public static Complex[][][] threeDarray(int var0, int var1, int var2, double var3, double var5) {
        Complex[][][] var7 = new Complex[var0][var1][var2];

        for(int var8 = 0; var8 < var0; ++var8) {
            for(int var9 = 0; var9 < var1; ++var9) {
                for(int var10 = 0; var10 < var2; ++var10) {
                    var7[var8][var9][var10] = zero();
                    var7[var8][var9][var10].reset(var3, var5);
                }
            }
        }

        return var7;
    }

    public static Complex[][][] threeDarray(int var0, int var1, int var2, Complex var3) {
        Complex[][][] var4 = new Complex[var0][var1][var2];

        for(int var5 = 0; var5 < var0; ++var5) {
            for(int var6 = 0; var6 < var1; ++var6) {
                for(int var7 = 0; var7 < var2; ++var7) {
                    var4[var5][var6][var7] = copy(var3);
                }
            }
        }

        return var4;
    }

    public static Complex copy(Complex var0) {
        if (var0 == null) {
            return null;
        } else {
            Complex var1 = new Complex();
            var1.real = var0.real;
            var1.imag = var0.imag;
            return var1;
        }
    }

    public Complex copy() {
        if (this == null) {
            return null;
        } else {
            Complex var1 = new Complex();
            var1.real = this.real;
            var1.imag = this.imag;
            return var1;
        }
    }

    public static Complex[] copy(Complex[] var0) {
        if (var0 == null) {
            return null;
        } else {
            int var1 = var0.length;
            Complex[] var2 = oneDarray(var1);

            for(int var3 = 0; var3 < var1; ++var3) {
                var2[var3] = copy(var0[var3]);
            }

            return var2;
        }
    }

    public static Complex[][] copy(Complex[][] var0) {
        if (var0 == null) {
            return (Complex[][])null;
        } else {
            int var1 = var0.length;
            int var2 = var0[0].length;
            Complex[][] var3 = twoDarray(var1, var2);

            for(int var4 = 0; var4 < var1; ++var4) {
                for(int var5 = 0; var5 < var2; ++var5) {
                    var3[var4][var5] = copy(var0[var4][var5]);
                }
            }

            return var3;
        }
    }

    public static Complex[][][] copy(Complex[][][] var0) {
        if (var0 == null) {
            return (Complex[][][])null;
        } else {
            int var1 = var0.length;
            int var2 = var0[0].length;
            int var3 = var0[0][0].length;
            Complex[][][] var4 = threeDarray(var1, var2, var3);

            for(int var5 = 0; var5 < var1; ++var5) {
                for(int var6 = 0; var6 < var2; ++var6) {
                    for(int var7 = 0; var7 < var3; ++var7) {
                        var4[var5][var6][var7] = copy(var0[var5][var6][var7]);
                    }
                }
            }

            return var4;
        }
    }

    public Object clone() {
        Complex var1 = null;
        if (this != null) {
            Complex var2 = new Complex();
            var2.real = this.real;
            var2.imag = this.imag;
            var1 = var2;
        }

        return var1;
    }

    public static Complex plus(Complex var0, Complex var1) {
        Complex var2 = new Complex();
        var2.real = var0.real + var1.real;
        var2.imag = var0.imag + var1.imag;
        return var2;
    }

    public static Complex plus(Complex var0, double var1) {
        Complex var3 = new Complex();
        var3.real = var0.real + var1;
        var3.imag = var0.imag;
        return var3;
    }

    public static Complex plus(double var0, Complex var2) {
        Complex var3 = new Complex();
        var3.real = var0 + var2.real;
        var3.imag = var2.imag;
        return var3;
    }

    public static Complex plus(double var0, double var2) {
        Complex var4 = new Complex();
        var4.real = var0 + var2;
        var4.imag = 0.0D;
        return var4;
    }

    public Complex plus(Complex var1) {
        Complex var2 = new Complex();
        var2.real = this.real + var1.real;
        var2.imag = this.imag + var1.imag;
        return var2;
    }

    public Complex plus(double var1) {
        Complex var3 = new Complex();
        var3.real = this.real + var1;
        var3.imag = this.imag;
        return var3;
    }

    public void plusEquals(Complex var1) {
        this.real += var1.real;
        this.imag += var1.imag;
    }

    public void plusEquals(double var1) {
        this.real += var1;
        this.imag = this.imag;
    }

    public static Complex minus(Complex var0, Complex var1) {
        Complex var2 = new Complex();
        var2.real = var0.real - var1.real;
        var2.imag = var0.imag - var1.imag;
        return var2;
    }

    public static Complex minus(Complex var0, double var1) {
        Complex var3 = new Complex();
        var3.real = var0.real - var1;
        var3.imag = var0.imag;
        return var3;
    }

    public static Complex minus(double var0, Complex var2) {
        Complex var3 = new Complex();
        var3.real = var0 - var2.real;
        var3.imag = -var2.imag;
        return var3;
    }

    public static Complex minus(double var0, double var2) {
        Complex var4 = new Complex();
        var4.real = var0 - var2;
        var4.imag = 0.0D;
        return var4;
    }

    public Complex minus(Complex var1) {
        Complex var2 = new Complex();
        var2.real = this.real - var1.real;
        var2.imag = this.imag - var1.imag;
        return var2;
    }

    public Complex minus(double var1) {
        Complex var3 = new Complex();
        var3.real = this.real - var1;
        var3.imag = this.imag;
        return var3;
    }

    public Complex transposedMinus(double var1) {
        Complex var3 = new Complex();
        var3.real = var1 - this.real;
        var3.imag = this.imag;
        return var3;
    }

    public void minusEquals(Complex var1) {
        this.real -= var1.real;
        this.imag -= var1.imag;
    }

    public void minusEquals(double var1) {
        this.real -= var1;
        this.imag = this.imag;
    }

    public static void setInfOption(boolean var0) {
        infOption = var0;
    }

    public static void setInfOption(int var0) {
        if (var0 >= 0 && var0 <= 1) {
            infOption = true;
            if (var0 == 1) {
                infOption = false;
            }

        } else {
            throw new IllegalArgumentException("opt must be 0 or 1");
        }
    }

    public static boolean getInfOption() {
        return infOption;
    }

    public static Complex times(Complex var0, Complex var1) {
        Complex var2 = new Complex(0.0D, 0.0D);
        if (infOption) {
            if (var0.isInfinite() && !var1.isZero()) {
                var2.reset(1.0D / 0.0, 1.0D / 0.0);
                return var2;
            }

            if (var1.isInfinite() && !var0.isZero()) {
                var2.reset(1.0D / 0.0, 1.0D / 0.0);
                return var2;
            }
        }

        var2.real = var0.real * var1.real - var0.imag * var1.imag;
        var2.imag = var0.real * var1.imag + var0.imag * var1.real;
        return var2;
    }

    public static Complex times(Complex var0, double var1) {
        Complex var3 = new Complex();
        if (infOption) {
            if (var0.isInfinite() && var1 != 0.0D) {
                var3.reset(1.0D / 0.0, 1.0D / 0.0);
                return var3;
            }

            if (Fmath.isInfinity(var1) && !var0.isZero()) {
                var3.reset(1.0D / 0.0, 1.0D / 0.0);
                return var3;
            }
        }

        var3.real = var0.real * var1;
        var3.imag = var0.imag * var1;
        return var3;
    }

    public static Complex times(double var0, Complex var2) {
        Complex var3 = new Complex();
        if (infOption) {
            if (var2.isInfinite() && var0 != 0.0D) {
                var3.reset(1.0D / 0.0, 1.0D / 0.0);
                return var3;
            }

            if (Fmath.isInfinity(var0) && !var2.isZero()) {
                var3.reset(1.0D / 0.0, 1.0D / 0.0);
                return var3;
            }
        }

        var3.real = var0 * var2.real;
        var3.imag = var0 * var2.imag;
        return var3;
    }

    public static Complex times(double var0, double var2) {
        Complex var4 = new Complex();
        var4.real = var0 * var2;
        var4.imag = 0.0D;
        return var4;
    }

    public Complex times(Complex var1) {
        Complex var2 = new Complex();
        if (infOption) {
            if (this.isInfinite() && !var1.isZero()) {
                var2.reset(1.0D / 0.0, 1.0D / 0.0);
                return var2;
            }

            if (var1.isInfinite() && !this.isZero()) {
                var2.reset(1.0D / 0.0, 1.0D / 0.0);
                return var2;
            }
        }

        var2.real = this.real * var1.real - this.imag * var1.imag;
        var2.imag = this.real * var1.imag + this.imag * var1.real;
        return var2;
    }

    public Complex times(double var1) {
        Complex var3 = new Complex();
        if (infOption) {
            if (this.isInfinite() && var1 != 0.0D) {
                var3.reset(1.0D / 0.0, 1.0D / 0.0);
                return var3;
            }

            if (Fmath.isInfinity(var1) && !this.isZero()) {
                var3.reset(1.0D / 0.0, 1.0D / 0.0);
                return var3;
            }
        }

        var3.real = this.real * var1;
        var3.imag = this.imag * var1;
        return var3;
    }

    public void timesEquals(Complex var1) {
        Complex var2 = new Complex();
        boolean var3 = true;
        if (infOption && (this.isInfinite() && !var1.isZero() || var1.isInfinite() && !this.isZero())) {
            this.real = 1.0D / 0.0;
            this.imag = 1.0D / 0.0;
            var3 = false;
        }

        if (var3) {
            var2.real = var1.real * this.real - var1.imag * this.imag;
            var2.imag = var1.real * this.imag + var1.imag * this.real;
            this.real = var2.real;
            this.imag = var2.imag;
        }

    }

    public void timesEquals(double var1) {
        boolean var3 = true;
        if (infOption && (this.isInfinite() && var1 != 0.0D || Fmath.isInfinity(var1) && !this.isZero())) {
            this.real = 1.0D / 0.0;
            this.imag = 1.0D / 0.0;
            var3 = false;
        }

        if (var3) {
            this.real *= var1;
            this.imag *= var1;
        }

    }

    public static Complex over(Complex var0, Complex var1) {
        Complex var2 = new Complex(0.0D, 0.0D);
        if (infOption && !var0.isInfinite() && var1.isInfinite()) {
            return var2;
        } else {
            double var3 = 0.0D;
            double var5 = 0.0D;
            if (var0.isZero()) {
                if (var1.isZero()) {
                    var2.real = 0.0D / 0.0;
                    var2.imag = 0.0D / 0.0;
                } else {
                    var2.real = 0.0D;
                    var2.imag = 0.0D;
                }
            } else if (Math.abs(var1.real) >= Math.abs(var1.imag)) {
                var5 = var1.imag / var1.real;
                var3 = var1.real + var1.imag * var5;
                var2.real = (var0.real + var0.imag * var5) / var3;
                var2.imag = (var0.imag - var0.real * var5) / var3;
            } else {
                var5 = var1.real / var1.imag;
                var3 = var1.real * var5 + var1.imag;
                var2.real = (var0.real * var5 + var0.imag) / var3;
                var2.imag = (var0.imag * var5 - var0.real) / var3;
            }

            return var2;
        }
    }

    public static Complex over(Complex var0, double var1) {
        Complex var3 = new Complex(0.0D, 0.0D);
        if (infOption && Fmath.isInfinity(var1)) {
            return var3;
        } else {
            var3.real = var0.real / var1;
            var3.imag = var0.imag / var1;
            return var3;
        }
    }

    public static Complex over(double var0, Complex var2) {
        Complex var3 = new Complex();
        if (infOption && !Fmath.isInfinity(var0) && var2.isInfinite()) {
            return var3;
        } else {
            if (var0 == 0.0D) {
                if (var2.isZero()) {
                    var3.real = 0.0D / 0.0;
                    var3.imag = 0.0D / 0.0;
                } else {
                    var3.real = 0.0D;
                    var3.imag = 0.0D;
                }
            } else {
                double var4;
                double var6;
                if (Math.abs(var2.real) >= Math.abs(var2.imag)) {
                    var6 = var2.imag / var2.real;
                    var4 = var2.real + var2.imag * var6;
                    var3.real = var0 / var4;
                    var3.imag = -var0 * var6 / var4;
                } else {
                    var6 = var2.real / var2.imag;
                    var4 = var2.real * var6 + var2.imag;
                    var3.real = var0 * var6 / var4;
                    var3.imag = -var0 / var4;
                }
            }

            return var3;
        }
    }

    public static Complex over(double var0, double var2) {
        Complex var4 = new Complex();
        var4.real = var0 / var2;
        var4.imag = 0.0D;
        return var4;
    }

    public Complex over(Complex var1) {
        Complex var2 = new Complex(0.0D, 0.0D);
        if (infOption && !this.isInfinite() && var1.isInfinite()) {
            return var2;
        } else {
            double var3 = 0.0D;
            double var5 = 0.0D;
            if (Math.abs(var1.real) >= Math.abs(var1.imag)) {
                var5 = var1.imag / var1.real;
                var3 = var1.real + var1.imag * var5;
                var2.real = (this.real + this.imag * var5) / var3;
                var2.imag = (this.imag - this.real * var5) / var3;
            } else {
                var5 = var1.real / var1.imag;
                var3 = var1.real * var5 + var1.imag;
                var2.real = (this.real * var5 + this.imag) / var3;
                var2.imag = (this.imag * var5 - this.real) / var3;
            }

            return var2;
        }
    }

    public Complex over(double var1) {
        Complex var3 = new Complex(0.0D, 0.0D);
        var3.real = this.real / var1;
        var3.imag = this.imag / var1;
        return var3;
    }

    public Complex transposedOver(double var1) {
        Complex var3 = new Complex(0.0D, 0.0D);
        if (infOption && !Fmath.isInfinity(var1) && this.isInfinite()) {
            return var3;
        } else {
            double var4 = 0.0D;
            double var6 = 0.0D;
            if (Math.abs(this.real) >= Math.abs(this.imag)) {
                var6 = this.imag / this.real;
                var4 = this.real + this.imag * var6;
                var3.real = var1 / var4;
                var3.imag = -var1 * var6 / var4;
            } else {
                var6 = this.real / this.imag;
                var4 = this.real * var6 + this.imag;
                var3.real = var1 * var6 / var4;
                var3.imag = -var1 / var4;
            }

            return var3;
        }
    }

    public void overEquals(Complex var1) {
        Complex var2 = new Complex(0.0D, 0.0D);
        boolean var3 = true;
        if (infOption && !this.isInfinite() && var1.isInfinite()) {
            this.real = 0.0D;
            this.imag = 0.0D;
            var3 = false;
        }

        if (var3) {
            double var4 = 0.0D;
            double var6 = 0.0D;
            if (Math.abs(var1.real) >= Math.abs(var1.imag)) {
                var6 = var1.imag / var1.real;
                var4 = var1.real + var1.imag * var6;
                var2.real = (this.real + this.imag * var6) / var4;
                var2.imag = (this.imag - this.real * var6) / var4;
            } else {
                var6 = var1.real / var1.imag;
                var4 = var1.real * var6 + var1.imag;
                var2.real = (this.real * var6 + this.imag) / var4;
                var2.imag = (this.imag * var6 - this.real) / var4;
            }

            this.real = var2.real;
            this.imag = var2.imag;
        }

    }

    public void overEquals(double var1) {
        this.real /= var1;
        this.imag /= var1;
    }

    public static Complex inverse(Complex var0) {
        Complex var1 = new Complex(0.0D, 0.0D);
        if (infOption && var0.isInfinite()) {
            return var1;
        } else {
            var1 = over(1.0D, var0);
            return var1;
        }
    }

    public Complex inverse() {
        new Complex(0.0D, 0.0D);
        Complex var1 = over(1.0D, this);
        return var1;
    }

    public static Complex negate(Complex var0) {
        Complex var1 = new Complex();
        var1.real = -var0.real;
        var1.imag = -var0.imag;
        return var1;
    }

    public Complex negate() {
        Complex var1 = new Complex();
        var1.real = -this.real;
        var1.imag = -this.imag;
        return var1;
    }

    public static double abs(Complex var0) {
        double var1 = Math.abs(var0.real);
        double var3 = Math.abs(var0.imag);
        double var5 = 0.0D;
        double var7 = 0.0D;
        if (var1 == 0.0D) {
            var7 = var3;
        } else {
            if (var3 == 0.0D) {
                ;
            }

            if (var1 >= var3) {
                var5 = var0.imag / var0.real;
                var7 = var1 * Math.sqrt(1.0D + var5 * var5);
            } else {
                var5 = var0.real / var0.imag;
                var7 = var3 * Math.sqrt(1.0D + var5 * var5);
            }
        }

        return var7;
    }

    public double abs() {
        double var1 = Math.abs(this.real);
        double var3 = Math.abs(this.imag);
        double var5 = 0.0D;
        double var7 = 0.0D;
        if (var1 == 0.0D) {
            var7 = var3;
        } else {
            if (var3 == 0.0D) {
                ;
            }

            if (var1 >= var3) {
                var5 = this.imag / this.real;
                var7 = var1 * Math.sqrt(1.0D + var5 * var5);
            } else {
                var5 = this.real / this.imag;
                var7 = var3 * Math.sqrt(1.0D + var5 * var5);
            }
        }

        return var7;
    }

    public static double squareAbs(Complex var0) {
        return var0.real * var0.real + var0.imag * var0.imag;
    }

    public double squareAbs() {
        return this.real * this.real + this.imag * this.imag;
    }

    public static double arg(Complex var0) {
        return Math.atan2(var0.imag, var0.real);
    }

    public double arg() {
        return Math.atan2(this.imag, this.real);
    }

    public static double argRad(Complex var0) {
        return Math.atan2(var0.imag, var0.real);
    }

    public double argRad() {
        return Math.atan2(this.imag, this.real);
    }

    public static double argDeg(Complex var0) {
        return Math.toDegrees(Math.atan2(var0.imag, var0.real));
    }

    public double argDeg() {
        return Math.toDegrees(Math.atan2(this.imag, this.real));
    }

    public static Complex conjugate(Complex var0) {
        Complex var1 = new Complex();
        var1.real = var0.real;
        var1.imag = -var0.imag;
        return var1;
    }

    public Complex conjugate() {
        Complex var1 = new Complex();
        var1.real = this.real;
        var1.imag = -this.imag;
        return var1;
    }

    public static double hypot(Complex var0, Complex var1) {
        double var2 = abs(var0);
        double var4 = abs(var1);
        double var6 = 0.0D;
        double var8 = 0.0D;
        if (var2 == 0.0D) {
            var6 = var4;
        } else if (var4 == 0.0D) {
            var6 = var2;
        } else if (var2 >= var4) {
            var8 = var4 / var2;
            var6 = var2 * Math.sqrt(1.0D + var8 * var8);
        } else {
            var8 = var2 / var4;
            var6 = var4 * Math.sqrt(1.0D + var8 * var8);
        }

        return var6;
    }

    public Complex exp() {
        return exp(this);
    }

    public static Complex exp(Complex var0) {
        Complex var1 = new Complex();
        double var2 = var0.real;
        double var4 = var0.imag;
        if (var4 == 0.0D) {
            var1.real = Math.exp(var2);
            var1.imag = 0.0D;
        } else if (var2 == 0.0D) {
            var1.real = Math.cos(var4);
            var1.imag = Math.sin(var4);
        } else {
            double var6 = Math.exp(var2);
            var1.real = var6 * Math.cos(var4);
            var1.imag = var6 * Math.sin(var4);
        }

        return var1;
    }

    public static Complex exp(double var0) {
        Complex var2 = new Complex(var0, 0.0D);
        return exp(var2);
    }

    public static Complex expPlusJayArg(double var0) {
        Complex var2 = new Complex(0.0D, var0);
        return exp(var2);
    }

    public static Complex expMinusJayArg(double var0) {
        Complex var2 = new Complex(0.0D, -var0);
        return exp(var2);
    }

    public Complex log() {
        double var1 = this.real;
        double var3 = this.imag;
        Complex var5 = new Complex();
        var5.real = Math.log(abs(this));
        var5.imag = Math.atan2(var3, var1);
        return var5;
    }

    public static Complex log(Complex var0) {
        double var1 = var0.real;
        double var3 = var0.imag;
        Complex var5 = new Complex();
        var5.real = Math.log(abs(var0));
        var5.imag = Math.atan2(var3, var1);
        return var5;
    }

    public Complex sqrt() {
        return sqrt(this);
    }

    public static Complex sqrt(Complex var0) {
        double var1 = var0.real;
        double var3 = var0.imag;
        Complex var5 = new Complex();
        if (var3 == 0.0D) {
            if (var1 >= 0.0D) {
                var5.real = Math.sqrt(var1);
                var5.imag = 0.0D;
            } else {
                var5.real = 0.0D;
                var5.imag = Math.sqrt(-var1);
            }
        } else {
            double var10 = Math.abs(var1);
            double var12 = Math.abs(var3);
            double var6;
            double var8;
            if (var10 >= var12) {
                var8 = var3 / var1;
                var6 = Math.sqrt(var10) * Math.sqrt(0.5D * (1.0D + Math.sqrt(1.0D + var8 * var8)));
            } else {
                var8 = var1 / var3;
                var6 = Math.sqrt(var12) * Math.sqrt(0.5D * (Math.abs(var8) + Math.sqrt(1.0D + var8 * var8)));
            }

            if (var1 >= 0.0D) {
                var5.real = var6;
                var5.imag = var3 / (2.0D * var6);
            } else if (var3 >= 0.0D) {
                var5.imag = var6;
                var5.real = var3 / (2.0D * var5.imag);
            } else {
                var5.imag = -var6;
                var5.real = var3 / (2.0D * var5.imag);
            }
        }

        return var5;
    }

    public Complex nthRoot(int var1) {
        return nthRoot(this, var1);
    }

    public static Complex nthRoot(Complex var0, int var1) {
        new Complex();
        Complex var2;
        if (var1 == 0) {
            var2 = new Complex(1.0D / 0.0, 0.0D);
        } else if (var1 == 1) {
            var2 = var0;
        } else {
            var2 = exp(log(var0).over((double)var1));
        }

        return var2;
    }

    public static Complex square(Complex var0) {
        Complex var1 = new Complex();
        var1.real = var0.real * var0.real - var0.imag * var0.imag;
        var1.imag = 2.0D * var0.real * var0.imag;
        return var1;
    }

    public Complex square() {
        return this.times(this);
    }

    public Complex pow(Complex var1) {
        Complex var2 = new Complex();
        if (this.isZero()) {
            if (var1.imag == 0.0D) {
                if (var1.real == 0.0D) {
                    var2 = new Complex(1.0D, 0.0D);
                } else if (var1.real > 0.0D) {
                    var2 = new Complex(0.0D, 0.0D);
                } else if (var1.real < 0.0D) {
                    var2 = new Complex(1.0D / 0.0, 0.0D);
                }
            } else {
                var2 = exp(var1.times(log(this)));
            }
        } else {
            var2 = exp(var1.times(log(this)));
        }

        return var2;
    }

    public static Complex pow(Complex var0, Complex var1) {
        Complex var2 = new Complex();
        if (var0.isZero()) {
            if (var1.imag == 0.0D) {
                if (var1.real == 0.0D) {
                    var2 = new Complex(1.0D, 0.0D);
                } else if (var0.real > 0.0D) {
                    var2 = new Complex(0.0D, 0.0D);
                } else if (var0.real < 0.0D) {
                    var2 = new Complex(1.0D / 0.0, 0.0D);
                }
            } else {
                var2 = exp(var1.times(log(var0)));
            }
        } else {
            var2 = exp(var1.times(log(var0)));
        }

        return var2;
    }

    public Complex pow(double var1) {
        return powDouble(this, var1);
    }

    public static Complex pow(Complex var0, double var1) {
        return powDouble(var0, var1);
    }

    public Complex pow(int var1) {
        double var2 = (double)var1;
        return powDouble(this, var2);
    }

    public static Complex pow(Complex var0, int var1) {
        double var2 = (double)var1;
        return powDouble(var0, var2);
    }

    public static Complex pow(double var0, Complex var2) {
        Complex var3 = new Complex();
        double var4;
        if (var0 == 0.0D) {
            if (var2.imag == 0.0D) {
                if (var2.real == 0.0D) {
                    var3 = new Complex(1.0D, 0.0D);
                } else if (var2.real > 0.0D) {
                    var3 = new Complex(0.0D, 0.0D);
                } else if (var2.real < 0.0D) {
                    var3 = new Complex(1.0D / 0.0, 0.0D);
                }
            } else {
                var4 = Math.pow(var0, var2.real);
                var3 = exp(times(plusJay(), var2.imag * Math.log(var0)));
                var3 = times(var4, var3);
            }
        } else {
            var4 = Math.pow(var0, var2.real);
            var3 = exp(times(plusJay(), var2.imag * Math.log(var0)));
            var3 = times(var4, var3);
        }

        return var3;
    }

    public Complex sin() {
        return sin(this);
    }

    public static Complex sin(Complex var0) {
        Complex var1 = new Complex();
        double var2 = var0.real;
        double var4 = var0.imag;
        var1.real = Math.sin(var2) * Fmath.cosh(var4);
        var1.imag = Math.cos(var2) * Fmath.sinh(var4);
        return var1;
    }

    public Complex cos() {
        return cos(this);
    }

    public static Complex cos(Complex var0) {
        Complex var1 = new Complex();
        double var2 = var0.real;
        double var4 = var0.imag;
        var1.real = Math.cos(var2) * Fmath.cosh(var4);
        var1.imag = -Math.sin(var2) * Fmath.sinh(var4);
        return var1;
    }

    public Complex sec() {
        return sec(this);
    }

    public static Complex sec(Complex var0) {
        Complex var1 = new Complex();
        double var2 = var0.real;
        double var4 = var0.imag;
        var1.real = Math.cos(var2) * Fmath.cosh(var4);
        var1.imag = -Math.sin(var2) * Fmath.sinh(var4);
        return var1.inverse();
    }

    public Complex csc() {
        return csc(this);
    }

    public static Complex csc(Complex var0) {
        Complex var1 = new Complex();
        double var2 = var0.real;
        double var4 = var0.imag;
        var1.real = Math.sin(var2) * Fmath.cosh(var4);
        var1.imag = Math.cos(var2) * Fmath.sinh(var4);
        return var1.inverse();
    }

    public Complex tan() {
        return tan(this);
    }

    public static Complex tan(Complex var0) {
        new Complex();
        double var2 = 0.0D;
        double var4 = var0.real;
        double var6 = var0.imag;
        Complex var8 = new Complex(Math.sin(var4) * Fmath.cosh(var6), Math.cos(var4) * Fmath.sinh(var6));
        Complex var9 = new Complex(Math.cos(var4) * Fmath.cosh(var6), -Math.sin(var4) * Fmath.sinh(var6));
        Complex var1 = over(var8, var9);
        return var1;
    }

    public Complex cot() {
        return cot(this);
    }

    public static Complex cot(Complex var0) {
        new Complex();
        double var2 = 0.0D;
        double var4 = var0.real;
        double var6 = var0.imag;
        Complex var8 = new Complex(Math.sin(var4) * Fmath.cosh(var6), Math.cos(var4) * Fmath.sinh(var6));
        Complex var9 = new Complex(Math.cos(var4) * Fmath.cosh(var6), -Math.sin(var4) * Fmath.sinh(var6));
        Complex var1 = over(var9, var8);
        return var1;
    }

    public Complex exsec() {
        return exsec(this);
    }

    public static Complex exsec(Complex var0) {
        return sec(var0).minus(1.0D);
    }

    public Complex vers() {
        return vers(this);
    }

    public static Complex vers(Complex var0) {
        return plusOne().minus(cos(var0));
    }

    public Complex covers() {
        return covers(this);
    }

    public static Complex covers(Complex var0) {
        return plusOne().minus(sin(var0));
    }

    public Complex hav() {
        return hav(this);
    }

    public static Complex hav(Complex var0) {
        return vers(var0).over(2.0D);
    }

    public Complex sinh() {
        return sinh(this);
    }

    public static Complex sinh(Complex var0) {
        new Complex();
        Complex var1 = var0.times(plusJay());
        var1 = minusJay().times(sin(var1));
        return var1;
    }

    public Complex cosh() {
        return cosh(this);
    }

    public static Complex cosh(Complex var0) {
        new Complex();
        Complex var1 = var0.times(plusJay());
        var1 = cos(var1);
        return var1;
    }

    public Complex tanh() {
        return tanh(this);
    }

    public static Complex tanh(Complex var0) {
        new Complex();
        Complex var1 = sinh(var0).over(cosh(var0));
        return var1;
    }

    public Complex coth() {
        return coth(this);
    }

    public static Complex coth(Complex var0) {
        new Complex();
        Complex var1 = cosh(var0).over(sinh(var0));
        return var1;
    }

    public Complex sech() {
        return sech(this);
    }

    public static Complex sech(Complex var0) {
        new Complex();
        Complex var1 = cosh(var0).inverse();
        return var1;
    }

    public Complex csch() {
        return csch(this);
    }

    public static Complex csch(Complex var0) {
        new Complex();
        Complex var1 = sinh(var0).inverse();
        return var1;
    }

    public Complex asin() {
        return asin(this);
    }

    public static Complex asin(Complex var0) {
        new Complex();
        Complex var1 = sqrt(minus(1.0D, square(var0)));
        var1 = plusJay().times(var0).plus(var1);
        var1 = minusJay().times(log(var1));
        return var1;
    }

    public Complex acos() {
        return acos(this);
    }

    public static Complex acos(Complex var0) {
        new Complex();
        Complex var1 = sqrt(minus(square(var0), 1.0D));
        var1 = var0.plus(var1);
        var1 = minusJay().times(log(var1));
        return var1;
    }

    public Complex atan() {
        return atan(this);
    }

    public static Complex atan(Complex var0) {
        new Complex();
        new Complex();
        Complex var1 = plusJay().plus(var0);
        Complex var2 = plusJay().minus(var0);
        var1 = var1.over(var2);
        var1 = log(var1);
        var1 = plusJay().times(var1);
        var1 = var1.over(2.0D);
        return var1;
    }

    public Complex acot() {
        return acot(this);
    }

    public static Complex acot(Complex var0) {
        return atan(var0.inverse());
    }

    public Complex asec() {
        return asec(this);
    }

    public static Complex asec(Complex var0) {
        return acos(var0.inverse());
    }

    public Complex acsc() {
        return acsc(this);
    }

    public static Complex acsc(Complex var0) {
        return asin(var0.inverse());
    }

    public Complex aexsec() {
        return aexsec(this);
    }

    public static Complex aexsec(Complex var0) {
        Complex var1 = var0.plus(1.0D);
        return asin(var1.inverse());
    }

    public Complex avers() {
        return avers(this);
    }

    public static Complex avers(Complex var0) {
        Complex var1 = plusOne().plus(var0);
        return acos(var1);
    }

    public Complex acovers() {
        return acovers(this);
    }

    public static Complex acovers(Complex var0) {
        Complex var1 = plusOne().plus(var0);
        return asin(var1);
    }

    public Complex ahav() {
        return ahav(this);
    }

    public static Complex ahav(Complex var0) {
        Complex var1 = plusOne().minus(var0.times(2.0D));
        return acos(var1);
    }

    public Complex asinh() {
        return asinh(this);
    }

    public static Complex asinh(Complex var0) {
        new Complex(0.0D, 0.0D);
        Complex var1 = sqrt(square(var0).plus(1.0D));
        var1 = var0.plus(var1);
        var1 = log(var1);
        return var1;
    }

    public Complex acosh() {
        return acosh(this);
    }

    public static Complex acosh(Complex var0) {
        new Complex();
        Complex var1 = sqrt(square(var0).minus(1.0D));
        var1 = var0.plus(var1);
        var1 = log(var1);
        return var1;
    }

    public Complex atanh() {
        return atanh(this);
    }

    public static Complex atanh(Complex var0) {
        new Complex();
        new Complex();
        Complex var1 = plusOne().plus(var0);
        Complex var2 = plusOne().minus(var0);
        var1 = var1.over(var2);
        var1 = log(var1);
        var1 = var1.over(2.0D);
        return var1;
    }

    public Complex acoth() {
        return acoth(this);
    }

    public static Complex acoth(Complex var0) {
        new Complex();
        new Complex();
        Complex var1 = plusOne().plus(var0);
        Complex var2 = var0.plus(1.0D);
        var1 = var1.over(var2);
        var1 = log(var1);
        var1 = var1.over(2.0D);
        return var1;
    }

    public Complex asech() {
        return asech(this);
    }

    public static Complex asech(Complex var0) {
        Complex var1 = var0.inverse();
        Complex var2 = square(var0).minus(1.0D);
        return log(var1.plus(sqrt(var2)));
    }

    public Complex acsch() {
        return acsch(this);
    }

    public static Complex acsch(Complex var0) {
        Complex var1 = var0.inverse();
        Complex var2 = square(var0).plus(1.0D);
        return log(var1.plus(sqrt(var2)));
    }

    public static boolean isReal(Complex var0) {
        boolean var1 = false;
        if (Math.abs(var0.imag) == 0.0D) {
            var1 = true;
        }

        return var1;
    }

    public static boolean isReal(Complex[] var0) {
        boolean var1 = true;
        int var2 = var0.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            if (Math.abs(var0[var3].imag) != 0.0D) {
                var1 = false;
            }
        }

        return var1;
    }

    public boolean isReal() {
        boolean var1 = false;
        if (Math.abs(this.imag) == 0.0D) {
            var1 = true;
        }

        return var1;
    }

    public static boolean isReal(Complex var0, double var1) {
        boolean var3 = false;
        if (Math.abs(var0.imag) <= Math.abs(var1)) {
            var3 = true;
        }

        return var3;
    }

    public static boolean isReal(Complex[] var0, double var1) {
        boolean var3 = true;
        int var4 = var0.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            if (Math.abs(var0[var5].imag) > Math.abs(var1)) {
                var3 = false;
            }
        }

        return var3;
    }

    public boolean isReal(double var1) {
        boolean var3 = false;
        if (Math.abs(this.imag) <= Math.abs(var1)) {
            var3 = true;
        }

        return var3;
    }

    public static boolean isRealPerCent(Complex var0, double var1) {
        boolean var3 = false;
        if (Math.abs(var0.imag * 100.0D / var0.real) <= Math.abs(var1)) {
            var3 = true;
        }

        return var3;
    }

    public static boolean isRealPerCent(Complex[] var0, double var1) {
        boolean var3 = true;
        int var4 = var0.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            if (Math.abs(var0[var5].imag * 100.0D / var0[var5].real) > Math.abs(var1)) {
                var3 = false;
            }
        }

        return var3;
    }

    public boolean isRealperCent(double var1) {
        boolean var3 = false;
        if (Math.abs(this.imag * 100.0D / this.real) <= Math.abs(var1)) {
            var3 = true;
        }

        return var3;
    }

    public static boolean isZero(Complex var0) {
        boolean var1 = false;
        if (Math.abs(var0.real) == 0.0D && Math.abs(var0.imag) == 0.0D) {
            var1 = true;
        }

        return var1;
    }

    public boolean isZero() {
        boolean var1 = false;
        if (Math.abs(this.real) == 0.0D && Math.abs(this.imag) == 0.0D) {
            var1 = true;
        }

        return var1;
    }

    public boolean isPlusInfinity() {
        boolean var1 = false;
        if (this.real == 1.0D / 0.0 || this.imag == 1.0D / 0.0) {
            var1 = true;
        }

        return var1;
    }

    public static boolean isPlusInfinity(Complex var0) {
        boolean var1 = false;
        if (var0.real == 1.0D / 0.0 || var0.imag == 1.0D / 0.0) {
            var1 = true;
        }

        return var1;
    }

    public boolean isMinusInfinity() {
        boolean var1 = false;
        if (this.real == -1.0D / 0.0 || this.imag == -1.0D / 0.0) {
            var1 = true;
        }

        return var1;
    }

    public static boolean isMinusInfinity(Complex var0) {
        boolean var1 = false;
        if (var0.real == -1.0D / 0.0 || var0.imag == -1.0D / 0.0) {
            var1 = true;
        }

        return var1;
    }

    public static boolean isInfinite(Complex var0) {
        boolean var1 = false;
        if (var0.real == 1.0D / 0.0 || var0.imag == 1.0D / 0.0) {
            var1 = true;
        }

        if (var0.real == -1.0D / 0.0 || var0.imag == -1.0D / 0.0) {
            var1 = true;
        }

        return var1;
    }

    public boolean isInfinite() {
        boolean var1 = false;
        if (this.real == 1.0D / 0.0 || this.imag == 1.0D / 0.0) {
            var1 = true;
        }

        if (this.real == -1.0D / 0.0 || this.imag == -1.0D / 0.0) {
            var1 = true;
        }

        return var1;
    }

    public static boolean isNaN(Complex var0) {
        boolean var1 = false;
        if (var0.real != var0.real || var0.imag != var0.imag) {
            var1 = true;
        }

        return var1;
    }

    public boolean isNaN() {
        boolean var1 = false;
        if (this.real != this.real || this.imag != this.imag) {
            var1 = true;
        }

        return var1;
    }

    public boolean equals(Complex var1) {
        boolean var2 = false;
        if (this.isNaN() && var1.isNaN()) {
            var2 = true;
        } else if (this.real == var1.real && this.imag == var1.imag) {
            var2 = true;
        }

        return var2;
    }

    public boolean isEqual(Complex var1) {
        boolean var2 = false;
        if (this.isNaN() && var1.isNaN()) {
            var2 = true;
        } else if (this.real == var1.real && this.imag == var1.imag) {
            var2 = true;
        }

        return var2;
    }

    public static boolean isEqual(Complex var0, Complex var1) {
        boolean var2 = false;
        if (isNaN(var0) && isNaN(var1)) {
            var2 = true;
        } else if (var0.real == var1.real && var0.imag == var1.imag) {
            var2 = true;
        }

        return var2;
    }

    public boolean equalsWithinLimits(Complex var1, double var2) {
        return this.isEqualWithinLimits(var1, var2);
    }

    public boolean isEqualWithinLimits(Complex var1, double var2) {
        boolean var4 = false;
        double var5 = this.getReal();
        double var7 = var1.getReal();
        double var9 = this.getImag();
        double var11 = var1.getImag();
        double var13 = 0.0D;
        double var15 = 0.0D;
        double var17 = 0.0D;
        double var19 = 0.0D;
        if (var5 == 0.0D && var9 == 0.0D && var7 == 0.0D && var11 == 0.0D) {
            var4 = true;
        }

        if (!var4) {
            var13 = Math.abs(var5);
            if (Math.abs(var7) > var13) {
                var13 = Math.abs(var7);
            }

            if (var13 == 0.0D) {
                var17 = 0.0D;
            } else {
                var17 = Math.abs(var7 - var5) / var13;
            }

            var15 = Math.abs(var9);
            if (Math.abs(var11) > var15) {
                var15 = Math.abs(var11);
            }

            if (var15 == 0.0D) {
                var19 = 0.0D;
            } else {
                var19 = Math.abs(var11 - var9) / var15;
            }

            if (var17 < var2 && var19 < var2) {
                var4 = true;
            }
        }

        return var4;
    }

    public static boolean isEqualWithinLimits(Complex var0, Complex var1, double var2) {
        boolean var4 = false;
        double var5 = var1.getReal();
        double var7 = var0.getReal();
        double var9 = var1.getImag();
        double var11 = var0.getImag();
        double var13 = 0.0D;
        double var15 = 0.0D;
        if (var7 == 0.0D && var11 == 0.0D && var5 == 0.0D && var9 == 0.0D) {
            var4 = true;
        }

        if (!var4) {
            var13 = Math.abs(var5);
            if (Math.abs(var7) > var13) {
                var13 = Math.abs(var7);
            }

            var15 = Math.abs(var9);
            if (Math.abs(var11) > var15) {
                var15 = Math.abs(var11);
            }

            if (Math.abs(var7 - var5) / var13 < var2 && Math.abs(var11 - var11) / var15 < var2) {
                var4 = true;
            }
        }

        return var4;
    }

    public static Complex zero() {
        Complex var0 = new Complex();
        var0.real = 0.0D;
        var0.imag = 0.0D;
        return var0;
    }

    public static Complex plusOne() {
        Complex var0 = new Complex();
        var0.real = 1.0D;
        var0.imag = 0.0D;
        return var0;
    }

    public static Complex minusOne() {
        Complex var0 = new Complex();
        var0.real = -1.0D;
        var0.imag = 0.0D;
        return var0;
    }

    public static Complex plusJay() {
        Complex var0 = new Complex();
        var0.real = 0.0D;
        var0.imag = 1.0D;
        return var0;
    }

    public static Complex minusJay() {
        Complex var0 = new Complex();
        var0.real = 0.0D;
        var0.imag = -1.0D;
        return var0;
    }

    public static Complex pi() {
        Complex var0 = new Complex();
        var0.real = 3.141592653589793D;
        var0.imag = 0.0D;
        return var0;
    }

    public static Complex twoPiJay() {
        Complex var0 = new Complex();
        var0.real = 0.0D;
        var0.imag = 6.283185307179586D;
        return var0;
    }

    public static Complex plusInfinity() {
        Complex var0 = new Complex();
        var0.real = 1.0D / 0.0;
        var0.imag = 1.0D / 0.0;
        return var0;
    }

    public static Complex minusInfinity() {
        Complex var0 = new Complex();
        var0.real = -1.0D / 0.0;
        var0.imag = -1.0D / 0.0;
        return var0;
    }

    private static Complex powDouble(Complex var0, double var1) {
        Complex var3 = new Complex();
        double var4 = var0.real;
        double var6 = var0.imag;
        if (var0.isZero()) {
            if (var1 == 0.0D) {
                var3 = new Complex(1.0D, 0.0D);
            } else if (var1 > 0.0D) {
                var3 = new Complex(0.0D, 0.0D);
            } else if (var1 < 0.0D) {
                var3 = new Complex(1.0D / 0.0, 0.0D);
            }
        } else if (var6 == 0.0D && var4 > 0.0D) {
            var3.real = Math.pow(var4, var1);
            var3.imag = 0.0D;
        } else if (var4 == 0.0D) {
            var3 = exp(times(var1, log(var0)));
        } else {
            double var8 = Math.pow(var4 * var4 + var6 * var6, var1 / 2.0D);
            double var10 = Math.atan2(var6, var4);
            var3.real = var8 * Math.cos(var1 * var10);
            var3.imag = var8 * Math.sin(var1 * var10);
        }

        return var3;
    }
}
