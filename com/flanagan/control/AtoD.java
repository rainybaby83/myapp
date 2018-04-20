//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.control;

import com.flanagan.complex.Complex;
import com.flanagan.complex.ComplexPoly;
import com.flanagan.math.Conv;
import com.flanagan.math.Fmath;

public class AtoD extends BlackBox {
    private int nBits = 0;
    private long maximumDecimal = 0L;
    private double vRef = 0.0D;
    private int[] vBinary = null;
    private boolean trueAtoD = true;
    private boolean range = true;
    private double voltageOutput = 0.0D;
    private String binaryOutput = "";
    private long decimalOutput = 0L;
    private double sqnr = 0.0D;
    private double input = 0.0D;
    private double inputC = 0.0D;
    private double shift = 0.0D;
    private long decimalShift = 0L;
    private boolean decCalcDone = false;
    private boolean binCalcDone = false;
    private boolean inputSet = false;
    private boolean firstCopy = true;

    public AtoD(int var1, double var2) {
        super("AtoD");
        if (var1 > 63) {
            throw new IllegalArgumentException("This program cannot accomadate an ADC simulation with a number of bits greater than 63");
        } else {
            this.nBits = var1;
            this.maximumDecimal = (long)Math.pow(2.0D, (double)this.nBits) - 1L;
            this.vRef = var2;
            this.vBinary = new int[var1 + 1];
            this.trueAtoD = true;
            super.setSnumer(new ComplexPoly(1.0D));
            super.setSdenom(new ComplexPoly(1.0D));
            super.setZtransformMethod(1);
        }
    }

    public AtoD() {
        super("AtoD");
        super.fixedName = "AtoD";
        super.sNumerDeg = 0;
        super.sDenomDeg = 0;
        super.setSnumer(new ComplexPoly(1.0D));
        super.setSdenom(new ComplexPoly(1.0D));
        super.ztransMethod = 1;
        super.setZtransformMethod(1);
    }

    public void setRangeOption(int var1) {
        if (var1 >= 0 && var1 <= 2) {
            if (var1 == 0) {
                this.range = true;
            }

            if (var1 == 1) {
                this.range = false;
                this.shift = this.vRef / 2.0D;
                this.decimalShift = this.maximumDecimal / 2L;
            }

            if (this.inputSet) {
                this.checkInput();
            }

            this.decCalcDone = false;
        } else {
            throw new IllegalArgumentException("argument must be either 0 or 1");
        }
    }

    public String getRange() {
        String var1 = null;
        if (this.trueAtoD) {
            if (this.range) {
                var1 = "0 to " + this.vRef;
            } else {
                var1 = "-" + this.vRef / 2.0D + " to " + this.vRef / 2.0D;
            }
        } else {
            System.out.println("Class AtoD; method getRange()");
            System.out.println("No range option set - this instance of AtoD is an 'ADC marker' only");
            System.out.println("getRangeOption has returned 'ADC marker only'");
            var1 = "ADC marker only";
        }

        return var1;
    }

    public boolean getTrueAtoDoption() {
        if (this.trueAtoD) {
            System.out.println("This instance of AtoD is a true simulation of an ADC");
            System.out.println("getTrueAtoDoption has returned 'true'");
        } else {
            System.out.println("This instance of AtoD is not a true simulation of an ADC");
            System.out.println("It is simple an 'A to D marker'");
            System.out.println("getTrueAtoDoption has returned 'false'");
        }

        return this.trueAtoD;
    }

    public double getVref() {
        if (!this.trueAtoD) {
            System.out.println("No reference voltage set - this instance of AtoD is an 'ADC marker' only");
            System.out.println("getVref has returned 0.0 V");
        }

        return this.vRef;
    }

    public void setInput(double var1) {
        this.input = var1;
        this.checkInput();
        this.inputSet = true;
    }

    public void checkInput() {
        this.inputC = this.input;
        if (this.trueAtoD) {
            if (this.range) {
                if (this.input < 0.0D) {
                    System.out.println("lower limit of the ADC range exceeded");
                    System.out.println("input voltage set to zero");
                    this.inputC = 0.0D;
                }

                if (this.input > this.vRef) {
                    System.out.println("upper limit of the ADC range exceeded");
                    System.out.println("input voltage set to " + this.vRef);
                    this.inputC = this.vRef;
                }
            } else {
                if (this.input < -this.vRef) {
                    System.out.println("lower limit of the ADC range exceeded");
                    System.out.println("input voltage set to " + -this.vRef / 2.0D);
                    this.inputC = -this.vRef / 2.0D;
                }

                if (this.input > this.vRef) {
                    System.out.println("upper limit of the ADC range exceeded");
                    System.out.println("input voltage set to " + this.vRef / 2.0D);
                    this.inputC = this.vRef / 2.0D;
                }
            }
        }

        this.inputC += this.shift;
        this.decCalcDone = false;
        this.binCalcDone = false;
    }

    public long getMaximumDecimal() {
        if (!this.trueAtoD) {
            System.out.println("This instance of AtoD is not a true simulation of an ADC");
            System.out.println("It is simple an 'A to D marker'");
            System.out.println("getTrueAtoDoption has returned 0");
        }

        return this.maximumDecimal;
    }

    public double maximumQuantizationError() {
        double var1 = 0.0D;
        if (this.trueAtoD) {
            var1 = this.vRef / (double)this.maximumDecimal;
        } else {
            System.out.println("This instance of AtoD is not a true simulation of an ADC");
            System.out.println("It is simple an 'A to D marker'");
            System.out.println("getMaxQuantizationError returns zero");
        }

        return var1;
    }

    public void calcOutput() {
        if (this.trueAtoD) {
            this.decimalOutput = (long)Math.floor(this.inputC / this.vRef * (double)this.maximumDecimal) - this.decimalShift;
            this.voltageOutput = this.vRef * (double)this.decimalOutput / (double)this.maximumDecimal;
            this.sqnr = 20.0D * Fmath.log10(Math.abs((this.inputC - this.shift) / (this.inputC - this.shift - this.voltageOutput)));
        } else {
            this.voltageOutput = this.input;
            this.sqnr = 1.0D / 0.0;
        }

        super.sNumer.resetCoeff(0, new Complex(this.voltageOutput / this.input, 0.0D));
        this.decCalcDone = true;
    }

    public double getSQNR() {
        if (!this.decCalcDone) {
            this.calcOutput();
        }

        if (!this.trueAtoD) {
            System.out.println("This instance of AtoD is not a true simulation of an ADC");
            System.out.println("It is simple an 'A to D marker'");
            System.out.println("getSQNR returned INFINITY");
        }

        return this.sqnr;
    }

    public double voltageOutput() {
        if (!this.decCalcDone) {
            this.calcOutput();
        }

        return this.voltageOutput;
    }

    public long decimalOutput() {
        if (!this.decCalcDone) {
            this.calcOutput();
        }

        if (!this.trueAtoD) {
            System.out.println("No formal A to D conversion performed - this instance of AtoD is an 'ADC marker' only");
            System.out.println("decimalOutput has returned 0");
        }

        return this.decimalOutput;
    }

    public static int[] decimalToBinary(long var0, int var2) {
        long var3 = 1L;
        if (var0 < 0L) {
            var3 = -1L;
            var0 *= var3;
        }

        long var5 = (long)Math.ceil(Math.log((double)var0) / Math.log(2.0D));
        if ((long)var2 < var5) {
            boolean var7 = true;
            byte var8 = 2;

            while(var7) {
                if (Math.pow(2.0D, (double)var8) > (double)var5) {
                    var2 = var8;
                    var7 = false;
                }
            }
        }

        int[] var10 = new int[var2];

        for(int var11 = 0; var11 < var2; ++var11) {
            var10[var11] = 0;
        }

        boolean var12 = true;
        int var9 = 0;

        while(var12) {
            var10[var9] = (int)(var0 % 2L);
            var0 /= 2L;
            ++var9;
            if (var0 == 0L) {
                var12 = false;
            }
        }

        if (var3 == -1L) {
            var10 = negateBinary(var10);
        }

        return var10;
    }

    public static int[] negateBinary(int[] var0) {
        int var1 = var0.length;
        int var2 = var1;
        if (var0[var1 - 1] == 1) {
            var2 = var1 + var1;
        }

        int[] var3 = new int[var2];
        int[] var4 = new int[var2];

        int var5;
        for(var5 = 0; var5 < var2; ++var5) {
            var4[var5] = 0;
            var3[var5] = 1;
        }

        var4[0] = 1;

        for(var5 = 0; var5 < var1; ++var5) {
            if (var0[var5] == 1) {
                var3[var5] = 0;
            }
        }

        var3 = addBinary(var3, var4);
        return var3;
    }

    public static int[] addBinary(int[] var0, int[] var1) {
        int var2 = var0.length;
        int var3 = var1.length;
        int var4 = var2;
        int var5 = var3;
        if (var3 > var2) {
            var4 = var3;
            var5 = var2;
        }

        int[] var6 = new int[var4];
        byte var7 = 0;
        boolean var8 = false;

        for(int var9 = 0; var9 < var5; ++var9) {
            int var10 = var0[var9] + var1[var9] + var7;
            switch(var10) {
                case 0:
                    var6[var9] = 0;
                    var7 = 0;
                    break;
                case 1:
                    var6[var9] = 1;
                    var7 = 0;
                    break;
                case 2:
                    var6[var9] = 0;
                    var7 = 1;
                    break;
                case 3:
                    var6[var9] = 1;
                    var7 = 1;
            }
        }

        return var6;
    }

    public String binaryOutput() {
        if (!this.decCalcDone) {
            this.calcOutput();
        }

        if (this.trueAtoD) {
            int var1 = this.nBits + 1;
            long var2 = this.decimalOutput + this.decimalShift;
            this.vBinary = decimalToBinary(var2, var1);
            if (this.shift > 0.0D) {
                int[] var4 = decimalToBinary(this.decimalShift, var1);
                var4 = negateBinary(var4);
                this.vBinary = addBinary(this.vBinary, var4);
            }

            this.binaryOutput = "";

            for(int var5 = var1 - 1; var5 >= 0; --var5) {
                this.binaryOutput = this.binaryOutput + this.vBinary[var5];
            }
        } else {
            System.out.println("No formal A to D conversion performed - this instance of AtoD is an 'ADC marker' only");
            System.out.println("binaryOutput has returned 'null'");
        }

        this.binCalcDone = true;
        return this.binaryOutput;
    }

    public int[] binaryArray() {
        if (this.trueAtoD) {
            if (!this.binCalcDone) {
                this.binaryOutput();
            }
        } else {
            System.out.println("No formal A to D conversion performed - this instance of AtoD is an 'ADC marker' only");
            System.out.println("binaryOutput has returned 'null'");
        }

        return this.vBinary;
    }

    public double quantizationError() {
        if (!this.decCalcDone) {
            this.calcOutput();
        }

        double var1 = 0.0D;
        if (this.trueAtoD) {
            var1 = this.inputC - this.voltageOutput;
        } else {
            System.out.println("This instance of AtoD is not a true simulation of an ADC");
            System.out.println("It is simple an 'A to D marker'");
            System.out.println("getQuantizationError returns zero");
        }

        return var1;
    }

    public double clippingError() {
        return this.inputC - this.input;
    }

    @Override
    public AtoD copy() {
        if (this == null) {
            return null;
        } else {
            AtoD var1 = new AtoD();
            this.copyBBvariables(var1);
            var1.nBits = this.nBits;
            var1.maximumDecimal = this.maximumDecimal;
            var1.vRef = this.vRef;
            var1.vBinary = Conv.copy(this.vBinary);
            var1.trueAtoD = this.trueAtoD;
            var1.range = this.range;
            var1.voltageOutput = this.voltageOutput;
            var1.binaryOutput = this.binaryOutput;
            var1.decimalOutput = this.decimalOutput;
            var1.sqnr = this.sqnr;
            var1.input = this.input;
            var1.inputC = this.inputC;
            var1.shift = this.shift;
            var1.decimalShift = this.decimalShift;
            var1.decCalcDone = this.decCalcDone;
            var1.binCalcDone = this.binCalcDone;
            var1.inputSet = this.inputSet;
            return var1;
        }
    }

    @Override
    public Object clone() {
        return this.copy();
    }
}
