//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.control;

import com.flanagan.complex.Complex;
import com.flanagan.complex.ComplexPoly;
import com.flanagan.math.Conv;

public class DtoA extends BlackBox {
    private int nBits = 0;
    private long maximumDecimal = 0L;
    private double vRef = 0.0D;
    private int[] vBinary = null;
    private boolean trueDtoA = true;
    private double outputVoltage = 0.0D;
    private double voltageInput = 0.0D;
    private String binaryInput = "";
    private long decimalInput = 0L;
    private boolean inputSet = false;

    public DtoA(int var1, double var2) {
        super("DtoA");
        super.setSnumer(new ComplexPoly(1.0D));
        super.setSdenom(new ComplexPoly(1.0D));
        super.setZtransformMethod(1);
        this.nBits = var1;
        this.vBinary = new int[var1 + 1];
        this.maximumDecimal = (long)Math.pow(2.0D, (double)this.nBits) - 1L;
        this.vRef = var2;
        this.trueDtoA = true;
    }

    public DtoA() {
        super("DtoA");
        this.trueDtoA = false;
        super.sNumerDeg = 0;
        super.sDenomDeg = 0;
        super.setSnumer(new ComplexPoly(1.0D));
        super.setSdenom(new ComplexPoly(1.0D));
        super.setZtransformMethod(1);
    }

    public boolean getTrueDtoAoption() {
        if (this.trueDtoA) {
            System.out.println("This instance of DtoA is a true simulation of an ADC");
            System.out.println("getTrueDtoAoption has returned 'true'");
        } else {
            System.out.println("This instance of DtoA is not a true simulation of an ADC");
            System.out.println("It is simple an 'D to A marker'");
            System.out.println("getTrueDtoAoption has returned 'false'");
        }

        return this.trueDtoA;
    }

    public void setInput(String var1) {
        this.binaryInput = var1.trim();
        int var2 = this.binaryInput.length();
        if (var2 > this.nBits + 1) {
            throw new IllegalArgumentException("length of input String is greater than the DAC bit number plus one");
        } else {
            if (var2 < this.nBits + 1) {
                System.out.println("Class - DtoA;  method - setInput(String)");
                System.out.println("The input String is less than DAC number of bits plus one");
                System.out.println("String assumed to represent a postive unsigned binary number");
                System.out.println("unfilled bits assigned zeros");

                for(int var3 = var2; var3 < this.nBits + 1; ++var3) {
                    this.binaryInput = '0' + this.binaryInput;
                }

                var2 = this.nBits + 1;
            }

            boolean var9 = false;
            boolean var4 = false;
            boolean var5 = true;

            for(int var6 = var2 - 1; var6 >= 0; --var6) {
                char var12 = this.binaryInput.charAt(var6);
                byte var10;
                if (var12 == '1') {
                    var10 = 1;
                } else {
                    if (var12 != '0') {
                        throw new IllegalArgumentException("String input must be '0's or '1's");
                    }

                    var10 = 0;
                }

                int var11 = var2 - var6 - 1;
                this.vBinary[var11] = var10;
            }

            long var13 = 1L;
            int[] var8 = Conv.copy(this.vBinary);
            if (this.vBinary[var2 - 1] == 1) {
                var13 = -1L;
                var8 = negateNegativeBinary(var8);
            }

            this.decimalInput = binaryToDecimal(var8);
            if (var13 == -1L) {
                this.decimalInput = -this.decimalInput;
            }

            this.outputVoltage = (double)this.decimalInput * this.vRef / (double)(this.maximumDecimal + 1L);
            this.inputSet = true;
        }
    }

    public void setInput(int[] var1) {
        int var2 = var1.length;
        if (var2 > this.nBits + 1) {
            throw new IllegalArgumentException("length of input array is greater than the DAC bit number plus  one");
        } else {
            int var3;
            for(var3 = 0; var3 < var2; ++var3) {
                this.vBinary[var3] = var1[var3];
            }

            if (var2 < this.nBits + 1) {
                System.out.println("Class - DtoA;  method - setInput(String)");
                System.out.println("The input array is less than DAC number of bits plus one");
                System.out.println("Array assumed to represent a postive unsigned binary number");
                System.out.println("unfilled bits assigned zeros");

                for(var3 = var2; var3 < this.nBits + 1; ++var3) {
                    this.vBinary[var3] = 0;
                }

                var2 = this.nBits + 1;
            }

            this.binaryInput = "";

            for(var3 = this.nBits; var3 >= 0; --var3) {
                this.binaryInput = this.binaryInput + this.vBinary[var3];
            }

            long var6 = 1L;
            int[] var5 = Conv.copy(this.vBinary);
            if (this.vBinary[var2 - 1] == 1) {
                var6 = -1L;
                var5 = negateNegativeBinary(this.vBinary);
            }

            this.decimalInput = binaryToDecimal(var5);
            if (var6 == -1L) {
                this.decimalInput = -this.decimalInput;
            }

            this.outputVoltage = (double)this.decimalInput * this.vRef / (double)(this.maximumDecimal + 1L);
            this.inputSet = true;
        }
    }

    public void setInput(long var1) {
        if (Math.abs(var1) > this.maximumDecimal) {
            throw new IllegalArgumentException("abs(input), " + var1 + ", is greater than the maximum decimal representation, " + this.maximumDecimal + ", allowed by the set number of bits, " + this.nBits);
        } else {
            this.decimalInput = var1;
            this.outputVoltage = (double)var1 * this.vRef / (double)(this.maximumDecimal + 1L);
            long var3 = this.decimalInput;
            byte var5 = 1;
            if (var3 < 0L) {
                var5 = -1;
                var3 = -var3;
            }

            for(int var6 = 0; var6 < this.nBits + 1; ++var6) {
                this.vBinary[var6] = 0;
            }

            boolean var9 = true;
            int var7 = 0;

            while(var9) {
                this.vBinary[var7] = (int)(var3 % 2L);
                var3 /= 2L;
                ++var7;
                if (var3 == 0L) {
                    var9 = false;
                }
            }

            if ((long)var5 == -1L) {
                this.vBinary = AtoD.negateBinary(this.vBinary);
            }

            this.binaryInput = "";

            for(int var8 = this.nBits; var8 >= 0; --var8) {
                this.binaryInput = this.binaryInput + this.vBinary[var8];
            }

            this.inputSet = true;
        }
    }

    public void setInput(double var1) {
        if (this.trueDtoA) {
            if (Math.abs(var1) > this.vRef) {
                throw new IllegalArgumentException("The input voltage in this simulation of a DAC must be less than nor equal to the reference voltage\nIf you choose the constructor without an argument list, i.e. an instance of DtoA that is simply a DAC marker\nyou may imput any voltage and the output will be made equal to that voltage");
            }

            this.voltageInput = var1;
            AtoD var3 = new AtoD(this.nBits, this.vRef);
            var3.setInput(var1);
            this.decimalInput = var3.decimalOutput();
            this.binaryInput = var3.binaryOutput();
            this.vBinary = var3.binaryArray();
        } else {
            this.outputVoltage = var1;
        }

        super.sNumer.resetCoeff(0, new Complex(this.outputVoltage / this.voltageInput, 0.0D));
        this.inputSet = true;
    }

    private static long binaryToDecimal(int[] var0) {
        long var1 = 0L;

        for(int var3 = 0; var3 < var0.length; ++var3) {
            var1 += (long)(Math.pow(2.0D, (double)var3) * (double)var0[var3]);
        }

        return var1;
    }

    private static int[] negateNegativeBinary(int[] var0) {
        int var1 = var0.length;
        int[] var2 = new int[var1];
        int[] var3 = new int[var1];

        int var4;
        for(var4 = 0; var4 < var1; ++var4) {
            var3[var4] = 1;
            var2[var4] = 0;
        }

        var2 = addBinary(var2, var3);

        for(var4 = 0; var4 < var1; ++var4) {
            if (var0[var4] == 0) {
                var2[var4] = 1;
            }
        }

        return var2;
    }

    private static int[] addBinary(int[] var0, int[] var1) {
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

    public double getOutput() {
        if (!this.inputSet) {
            throw new IllegalArgumentException("No input has been entered");
        } else {
            return this.outputVoltage;
        }
    }

    public long getDecimalInput() {
        if (!this.inputSet) {
            throw new IllegalArgumentException("No input has been entered");
        } else {
            if (!this.trueDtoA) {
                System.out.println("Class - DtoA;  method - getDecimalInput");
                System.out.println("This instance of DtoA is not a true simulation of an DAC");
                System.out.println("It is simple an 'D to A marker'");
                System.out.println("getDecimalInput has returned 0L");
                this.decimalInput = 0L;
            }

            return this.decimalInput;
        }
    }

    public String getBinaryInput() {
        if (!this.inputSet) {
            throw new IllegalArgumentException("No input has been entered");
        } else {
            if (!this.trueDtoA) {
                System.out.println("Class - DtoA;  method - getBinaryInput");
                System.out.println("This instance of DtoA is not a true simulation of an DAC");
                System.out.println("It is simple an 'D to A marker'");
                System.out.println("getBinaryInput has returned null");
                this.binaryInput = null;
            }

            return this.binaryInput;
        }
    }

    public int[] getBinaryArray() {
        if (!this.inputSet) {
            throw new IllegalArgumentException("No input has been entered");
        } else {
            if (!this.trueDtoA) {
                System.out.println("Class - DtoA;  method - getBinaryInput");
                System.out.println("This instance of DtoA is not a true simulation of an DAC");
                System.out.println("It is simple an 'D to A marker'");
                System.out.println("getBinaryArray has returned null");
                this.vBinary = null;
            }

            return this.vBinary;
        }
    }

    public DtoA copy() {
        if (this == null) {
            return null;
        } else {
            DtoA var1 = new DtoA();
            this.copyBBvariables(var1);
            var1.nBits = this.nBits;
            var1.maximumDecimal = this.maximumDecimal;
            var1.vRef = this.vRef;
            var1.vBinary = Conv.copy(this.vBinary);
            var1.trueDtoA = this.trueDtoA;
            var1.outputVoltage = this.outputVoltage;
            var1.voltageInput = this.voltageInput;
            var1.binaryInput = this.binaryInput;
            var1.decimalInput = this.decimalInput;
            var1.inputSet = this.inputSet;
            return var1;
        }
    }

    @Override
    public Object clone() {
        return this.copy();
    }
}
