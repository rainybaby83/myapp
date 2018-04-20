//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.control;

import com.flanagan.complex.Complex;
import com.flanagan.complex.ComplexPoly;

public class SecondOrder extends BlackBox {
    private double aConst = 1.0D;
    private double bConst = 1.0D;
    private double cConst = 1.0D;
    private double dConst = 1.0D;
    private double omegaN = 1.0D;
    private double zeta = 1.0D;
    private double kConst = 1.0D;
    private double sigma = 1.0D;

    public SecondOrder() {
        super("SecondOrder");
        super.setSnumer(new ComplexPoly(1.0D));
        super.setSdenom(new ComplexPoly(1.0D, 1.0D, 1.0D));
        super.sNumerDeg = 0;
        super.setZtransformMethod(1);
        super.addDeadTimeExtras();
    }

    public SecondOrder(double var1, double var3, double var5, double var7) {
        super("SecondOrder");
        this.aConst = var1;
        this.bConst = var3;
        this.cConst = var5;
        this.dConst = var7;
        if (this.cConst > 0.0D) {
            this.standardForm();
        }

        super.setSnumer(new ComplexPoly(this.dConst));
        super.setSdenom(new ComplexPoly(this.cConst, this.bConst, this.aConst));
        super.setZtransformMethod(1);
        super.addDeadTimeExtras();
    }

    public void setCoeff(double var1, double var3, double var5, double var7) {
        this.aConst = var1;
        this.bConst = var3;
        this.cConst = var5;
        this.dConst = var7;
        if (this.cConst > 0.0D) {
            this.standardForm();
        }

        Complex[] var9 = Complex.oneDarray(1);
        var9[0].reset(this.dConst, 0.0D);
        super.sNumer.resetPoly(var9);
        Complex[] var10 = Complex.oneDarray(3);
        var10[0].reset(this.cConst, 0.0D);
        var10[1].reset(this.bConst, 0.0D);
        var10[2].reset(this.aConst, 0.0D);
        super.sDenom.resetPoly(var10);
        super.fixedName = "Second Order Process";
        this.calcPolesZerosS();
        super.addDeadTimeExtras();
    }

    private void standardForm() {
        this.omegaN = Math.sqrt(this.cConst / this.aConst);
        this.zeta = this.bConst / (2.0D * this.aConst * this.omegaN);
        this.kConst = this.dConst / this.cConst;
        this.sigma = this.zeta * this.omegaN;
    }

    public void setA(double var1) {
        this.aConst = var1;
        Complex var3 = new Complex(this.aConst, 0.0D);
        super.sDenom.resetCoeff(2, var3);
        if (this.cConst > 0.0D) {
            this.standardForm();
        }

        this.calcPolesZerosS();
        super.addDeadTimeExtras();
    }

    public void setB(double var1) {
        this.bConst = var1;
        Complex var3 = new Complex(this.bConst, 0.0D);
        super.sDenom.resetCoeff(1, var3);
        if (this.cConst > 0.0D) {
            this.standardForm();
        }

        this.calcPolesZerosS();
        super.addDeadTimeExtras();
    }

    public void setC(double var1) {
        this.cConst = var1;
        Complex var3 = new Complex(this.cConst, 0.0D);
        super.sDenom.resetCoeff(0, var3);
        if (this.cConst > 0.0D) {
            this.standardForm();
        }

        this.calcPolesZerosS();
        super.addDeadTimeExtras();
    }

    public void setD(double var1) {
        this.dConst = var1;
        Complex var3 = new Complex(this.dConst, 0.0D);
        super.sNumer.resetCoeff(0, var3);
        if (this.cConst > 0.0D) {
            this.standardForm();
        }

        this.calcPolesZerosS();
        super.addDeadTimeExtras();
    }

    public void setStandardForm(double var1, double var3, double var5) {
        if (var3 <= 0.0D) {
            throw new IllegalArgumentException("zero or negative natural frequency");
        } else if (var1 < 0.0D) {
            throw new IllegalArgumentException("negative damping ratio");
        } else {
            this.zeta = var1;
            this.omegaN = var3;
            this.kConst = var5;
            this.sigma = this.omegaN * this.zeta;
            this.reverseStandard();
            this.calcPolesZerosS();
            super.addDeadTimeExtras();
        }
    }

    public void setZeta(double var1) {
        if (var1 < 0.0D) {
            throw new IllegalArgumentException("negative damping ratio");
        } else {
            this.zeta = var1;
            this.sigma = this.omegaN * this.zeta;
            this.reverseStandard();
            this.calcPolesZerosS();
            super.addDeadTimeExtras();
        }
    }

    public void setOmegaN(double var1) {
        if (var1 <= 0.0D) {
            throw new IllegalArgumentException("zero or negative natural frequency");
        } else {
            this.omegaN = var1;
            this.sigma = this.omegaN * this.zeta;
            this.reverseStandard();
            this.calcPolesZerosS();
            super.addDeadTimeExtras();
        }
    }

    public void setK(double var1) {
        this.kConst = var1;
        this.reverseStandard();
        this.calcPolesZerosS();
        super.addDeadTimeExtras();
    }

    private void reverseStandard() {
        this.aConst = this.omegaN * this.omegaN;
        this.bConst = 2.0D * this.zeta * this.omegaN;
        this.cConst = 1.0D;
        this.dConst = this.kConst * this.aConst;
        Complex[] var1 = Complex.oneDarray(1);
        var1[0].reset(this.dConst, 0.0D);
        super.sNumer.resetPoly(var1);
        Complex[] var2 = Complex.oneDarray(3);
        var2[0].reset(this.cConst, 0.0D);
        var2[1].reset(this.bConst, 0.0D);
        var2[2].reset(this.aConst, 0.0D);
        super.sDenom.resetPoly(var2);
    }

    public double getA() {
        return this.aConst;
    }

    public double getB() {
        return this.bConst;
    }

    public double getC() {
        return this.cConst;
    }

    public double getD() {
        return this.dConst;
    }

    public double getOmegaN() {
        return this.omegaN;
    }

    public double getZeta() {
        return this.zeta;
    }

    public double getK() {
        return this.kConst;
    }

    public double getAttenuation() {
        return this.sigma;
    }

    public Complex getOutputS(Complex var1, Complex var2) {
        super.sValue = var1;
        super.inputS = var2;
        return this.getOutputS();
    }

    public Complex getOutputS() {
        Complex var1 = Complex.plusOne();
        var1 = var1.times(this.dConst);
        new Complex();
        Complex var2 = this.sValue.times(this.sValue.times(this.aConst));
        var2 = var2.plus(this.sValue.times(this.aConst));
        var2 = var2.plus(this.cConst);
        new Complex();
        Complex var3 = var1.over(var2);
        super.outputS = var3.times(super.inputS);
        if (super.deadTime != 0.0D) {
            super.outputS = super.outputS.times(Complex.exp(super.sValue.times(-super.deadTime)));
        }

        return super.outputS;
    }

    public void zTransform() {
        if (super.deltaT == 0.0D) {
            System.out.println("z-transform attempted in SecondOrder with a zero sampling period");
        }

        if (this.ztransMethod == 0) {
            this.mapstozAdHoc();
        } else {
            Complex[] var1 = null;
            Complex[] var2 = null;
            double var3 = this.bConst * this.deltaT;
            double var5 = this.deltaT * this.deltaT;
            double var7 = this.cConst * var5;
            double var9 = this.dConst * var5;
            switch(this.integMethod) {
                case 0:
                    var1 = Complex.oneDarray(3);
                    var1[0].reset(var9 / 4.0D, 0.0D);
                    var1[1].reset(var9 / 2.0D, 0.0D);
                    var1[2].reset(var9 / 4.0D, 0.0D);
                    super.zNumer = new ComplexPoly(2);
                    super.zNumer.resetPoly(var1);
                    super.zNumerDeg = 2;
                    var2 = Complex.oneDarray(3);
                    var2[0].reset(this.aConst - var3 + var7 / 4.0D, 0.0D);
                    var2[1].reset(-2.0D * this.aConst + var3 + var7 / 2.0D, 0.0D);
                    var2[2].reset(this.aConst + var7 / 4.0D, 0.0D);
                    super.zDenom = new ComplexPoly(2);
                    super.zDenom.resetPoly(var2);
                    super.zDenomDeg = 2;
                    super.zZeros = this.zNumer.roots();
                    super.zPoles = this.zDenom.roots();
                    break;
                case 1:
                    var1 = Complex.oneDarray(3);
                    var1[0].reset(0.0D, 0.0D);
                    var1[1].reset(0.0D, 0.0D);
                    var1[2].reset(var9, 0.0D);
                    super.zNumer = new ComplexPoly(2);
                    super.zNumer.resetPoly(var1);
                    super.zNumerDeg = 2;
                    var2 = Complex.oneDarray(3);
                    var2[0].reset(this.aConst - var3, 0.0D);
                    var2[1].reset(-2.0D * this.aConst, 0.0D);
                    var2[2].reset(this.aConst + var3 + var7, 0.0D);
                    super.zDenom = new ComplexPoly(2);
                    super.zDenom.resetPoly(var2);
                    super.zDenomDeg = 2;
                    super.zPoles = this.zDenom.roots();
                    super.zZeros = Complex.oneDarray(2);
                    super.zZeros[0].reset(0.0D, 0.0D);
                    super.zZeros[1].reset(0.0D, 0.0D);
                    break;
                case 2:
                    var1 = Complex.oneDarray(3);
                    var1[0].reset(0.0D, 0.0D);
                    var1[1].reset(0.0D, 0.0D);
                    var1[2].reset(var9, 0.0D);
                    super.zNumer = new ComplexPoly(2);
                    super.zNumer.resetPoly(var1);
                    super.zNumerDeg = 2;
                    var2 = Complex.oneDarray(3);
                    var2[0].reset(this.aConst - var3 + var7, 0.0D);
                    var2[1].reset(-2.0D * this.aConst + var3, 0.0D);
                    var2[2].reset(this.aConst, 0.0D);
                    super.zDenom = new ComplexPoly(2);
                    super.zDenom.resetPoly(var2);
                    super.zDenomDeg = 2;
                    super.zPoles = this.zDenom.roots();
                    super.zZeros = Complex.oneDarray(2);
                    super.zZeros[0].reset(0.0D, 0.0D);
                    super.zZeros[1].reset(0.0D, 0.0D);
                    break;
                default:
                    System.out.println("Integration method option in SecondOrder must be 0,1 or 2");
                    System.out.println("It was set at " + this.integMethod);
                    System.out.println("z-transform not performed");
            }
        }

    }

    public void zTransform(double var1) {
        super.setDeltaT(var1);
        super.deadTimeWarning("zTransform");
        this.zTransform();
    }

    public double calcOutputT(double var1, double var3) {
        return super.getCurrentOutputT(var1, var3);
    }

    public double calcOutputT() {
        return super.getCurrentOutputT();
    }

    public Complex[] getSzeros() {
        System.out.println("This standard second order process (class SecondOrder) has no s-domain zeros");
        return null;
    }

    public SecondOrder copy() {
        if (this == null) {
            return null;
        } else {
            SecondOrder var1 = new SecondOrder();
            this.copyBBvariables(var1);
            var1.aConst = this.aConst;
            var1.bConst = this.bConst;
            var1.cConst = this.cConst;
            var1.dConst = this.dConst;
            var1.omegaN = this.omegaN;
            var1.zeta = this.zeta;
            var1.kConst = this.kConst;
            var1.sigma = this.sigma;
            return var1;
        }
    }

    public Object clone() {
        return this.copy();
    }
}
