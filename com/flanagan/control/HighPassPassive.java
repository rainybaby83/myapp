//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.control;

import com.flanagan.complex.Complex;
import com.flanagan.complex.ComplexPoly;

public class HighPassPassive extends BlackBox {
    private double resistance = 0.0D;
    private double capacitance = 0.0D;
    private double timeConstant = 0.0D;
    private boolean setR = false;
    private boolean setC = false;

    public HighPassPassive() {
        super("PassiveHighPass");
        super.sZeros = Complex.oneDarray(1);
        super.sPoles = Complex.oneDarray(1);
        super.setSnumer(new ComplexPoly(0.0D, 1.0D));
        super.setSdenom(new ComplexPoly(1.0D, 1.0D));
        super.setZtransformMethod(1);
        super.addDeadTimeExtras();
        this.timeConstant = 1.0D;
    }

    public void setResistance(double var1) {
        this.resistance = var1;
        this.timeConstant = var1 * this.capacitance;
        this.calcPolesZerosS();
        super.sNumer = ComplexPoly.rootsToPoly(this.sZeros);

        for(int var3 = 0; var3 <= super.sNumerDeg; ++var3) {
            super.sNumer.resetCoeff(var3, super.sNumer.coeffCopy(var3).times(Math.pow(this.timeConstant, (double)var3)));
        }

        super.sDenom = ComplexPoly.rootsToPoly(this.sPoles);
        super.addDeadTimeExtras();
        this.setR = true;
    }

    public void setCapacitance(double var1) {
        this.capacitance = var1;
        this.timeConstant = var1 * this.resistance;
        this.calcPolesZerosS();
        super.sNumer = ComplexPoly.rootsToPoly(this.sZeros);

        for(int var3 = 0; var3 <= super.sNumerDeg; ++var3) {
            super.sNumer.resetCoeff(var3, super.sNumer.coeffCopy(var3).times(Math.pow(this.timeConstant, (double)var3)));
        }

        super.sDenom = ComplexPoly.rootsToPoly(this.sPoles);
        super.addDeadTimeExtras();
        this.setC = true;
    }

    public void setTimeConstant(double var1) {
        this.timeConstant = var1;
        this.calcPolesZerosS();
        super.sNumer = ComplexPoly.rootsToPoly(this.sZeros);

        for(int var3 = 0; var3 <= super.sNumerDeg; ++var3) {
            super.sNumer.resetCoeff(var3, super.sNumer.coeffCopy(var3).times(Math.pow(this.timeConstant, (double)var3)));
        }

        super.sDenom = ComplexPoly.rootsToPoly(this.sPoles);
        super.addDeadTimeExtras();
    }

    public double getResistance() {
        if (this.setR) {
            return this.resistance;
        } else {
            System.out.println("Class; HighPassPassive, method: getResistance");
            System.out.println("No resistance has been entered; zero returned");
            return 0.0D;
        }
    }

    public double getCapacitance() {
        if (this.setC) {
            return this.capacitance;
        } else {
            System.out.println("Class; HighPassPassive, method: getCapacitance");
            System.out.println("No capacitance has been entered; zero returned");
            return 0.0D;
        }
    }

    public double getTimeConstant() {
        return this.timeConstant;
    }

    protected void calcPolesZerosS() {
        super.sZeros[0].setReal(0.0D);
        super.sPoles[0].setReal(-this.timeConstant);
        super.sNumerScaleFactor = BlackBox.scaleFactor(super.sNumer, super.sZeros);
        super.sDenomScaleFactor = BlackBox.scaleFactor(super.sDenom, super.sPoles);
    }

    public HighPassPassive copy() {
        if (this == null) {
            return null;
        } else {
            HighPassPassive var1 = new HighPassPassive();
            this.copyBBvariables(var1);
            var1.resistance = this.resistance;
            var1.capacitance = this.capacitance;
            var1.timeConstant = this.timeConstant;
            var1.setR = this.setR;
            var1.setC = this.setC;
            return var1;
        }
    }

    public Object clone() {
        return this.copy();
    }
}
