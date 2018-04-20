//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.control;

import com.flanagan.complex.Complex;
import com.flanagan.complex.ComplexPoly;

public class Compensator extends BlackBox {
    private double kConst = 1.0D;
    private double aConst = 1.0D;
    private double bConst = 1.0D;

    public Compensator() {
        super("Compensator");
        super.sZeros = Complex.oneDarray(1);
        super.sPoles = Complex.oneDarray(1);
        super.setSnumer(new ComplexPoly(1.0D, 1.0D));
        super.setSdenom(new ComplexPoly(1.0D, 1.0D));
        super.setZtransformMethod(1);
        super.addDeadTimeExtras();
    }

    public Compensator(double var1, double var3, double var5) {
        super("Compensator");
        this.aConst = var3;
        this.bConst = var5;
        this.kConst = var1;
        super.sZeros = Complex.oneDarray(1);
        super.sPoles = Complex.oneDarray(1);
        super.setSnumer(new ComplexPoly(this.aConst * this.kConst, this.kConst));
        super.setSdenom(new ComplexPoly(this.bConst, 1.0D));
        super.setZtransformMethod(1);
        super.addDeadTimeExtras();
    }

    public void setCoeff(double var1, double var3, double var5) {
        this.aConst = var3;
        this.bConst = var5;
        this.kConst = var1;
        Complex[] var7 = Complex.oneDarray(2);
        var7[0].reset(this.aConst * this.kConst, 0.0D);
        var7[1].reset(this.kConst, 0.0D);
        super.sNumer.resetPoly(var7);
        Complex[] var8 = Complex.oneDarray(2);
        var8[0].reset(this.bConst, 0.0D);
        var8[1].reset(1.0D, 0.0D);
        super.sDenom.resetPoly(var8);
        this.calcPolesZerosS();
        super.addDeadTimeExtras();
    }

    public void setK(double var1) {
        this.kConst = var1;
        if (this.sNumerDeg == 1 && this.sDenomDeg == 1) {
            Complex var5 = new Complex(this.aConst * this.kConst, 0.0D);
            super.sNumer.resetCoeff(0, var5);
            var5 = new Complex(this.kConst, 0.0D);
            super.sNumer.resetCoeff(1, var5);
        } else {
            Complex[] var3 = Complex.oneDarray(2);
            var3[0].reset(this.aConst * this.kConst, 0.0D);
            var3[1].reset(this.kConst, 0.0D);
            super.sNumer.resetPoly(var3);
            Complex[] var4 = Complex.oneDarray(2);
            var4[0].reset(this.bConst, 0.0D);
            var4[1].reset(1.0D, 0.0D);
            super.sDenom.resetPoly(var4);
        }

        this.calcPolesZerosS();
        super.addDeadTimeExtras();
    }

    public void setA(double var1) {
        this.aConst = var1;
        if (this.sNumerDeg == 1 && this.sDenomDeg == 1) {
            Complex var5 = new Complex(this.aConst * this.kConst, 0.0D);
            super.sNumer.resetCoeff(0, var5);
        } else {
            Complex[] var3 = Complex.oneDarray(2);
            var3[0].reset(this.aConst * this.kConst, 0.0D);
            var3[1].reset(this.kConst, 0.0D);
            super.sNumer.resetPoly(var3);
            Complex[] var4 = Complex.oneDarray(2);
            var4[0].reset(this.bConst, 0.0D);
            var4[1].reset(1.0D, 0.0D);
            super.sDenom.resetPoly(var4);
        }

        this.calcPolesZerosS();
        super.addDeadTimeExtras();
    }

    public void setB(double var1) {
        this.bConst = var1;
        if (this.sNumerDeg == 1 && this.sDenomDeg == 1) {
            Complex var5 = new Complex(this.bConst, 0.0D);
            super.sDenom.resetCoeff(0, var5);
        } else {
            Complex[] var3 = Complex.oneDarray(2);
            var3[0].reset(this.aConst * this.kConst, 0.0D);
            var3[1].reset(this.kConst, 0.0D);
            super.sNumer.resetPoly(var3);
            Complex[] var4 = Complex.oneDarray(2);
            var4[0].reset(this.bConst, 0.0D);
            var4[1].reset(1.0D, 0.0D);
            super.sDenom.resetPoly(var4);
        }

        this.calcPolesZerosS();
        super.addDeadTimeExtras();
    }

    public double getA() {
        return this.aConst;
    }

    public double getB() {
        return this.bConst;
    }

    public double getK() {
        return this.kConst;
    }

    @Override
    public void calcPolesZerosS() {
        super.sZeros[0].setReal(-this.aConst);
        super.sPoles[0].setReal(-this.bConst);
        super.sNumerScaleFactor = BlackBox.scaleFactor(super.sNumer, super.sZeros);
        super.sDenomScaleFactor = BlackBox.scaleFactor(super.sDenom, super.sPoles);
    }

    @Override
    public Compensator copy() {
        if (this == null) {
            return null;
        } else {
            Compensator var1 = new Compensator();
            this.copyBBvariables(var1);
            var1.kConst = this.kConst;
            var1.aConst = this.aConst;
            var1.bConst = this.bConst;
            return var1;
        }
    }

    @Override
    public Object clone() {
        return this.copy();
    }
}
