//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.control;

import com.flanagan.complex.Complex;
import com.flanagan.complex.ComplexPoly;
import com.flanagan.plot.PlotGraph;

public class PropDeriv extends BlackBox {
    private double kp = 1.0D;
    private double td = 0.0D;
    private double kd = 0.0D;

    public PropDeriv() {
        super("PropDeriv");
        super.sNumerDeg = 1;
        super.sDenomDeg = 0;
        super.setSnumer(new ComplexPoly(1.0D, 0.0D));
        super.setSdenom(new ComplexPoly(1.0D));
        super.setZtransformMethod(1);
        super.addDeadTimeExtras();
    }

    public PropDeriv(double var1, double var3) {
        super("PropDeriv");
        super.sNumerDeg = 1;
        super.sDenomDeg = 0;
        this.kp = var1;
        this.kd = var3;
        this.td = var3 / this.kp;
        super.setSnumer(new ComplexPoly(new Complex(this.kp, 0.0D), new Complex(this.kd, 0.0D)));
        super.setSdenom(new ComplexPoly(1.0D));
        super.setZtransformMethod(1);
        super.addDeadTimeExtras();
    }

    public void setKp(double var1) {
        this.kp = var1;
        if (super.sNumerDeg != 1) {
            super.setSnumer(new ComplexPoly(new Complex(this.kp, 0.0D), new Complex(this.kd, 0.0D)));
            super.setSdenom(new ComplexPoly(1.0D));
        } else {
            super.sNumer.resetCoeff(0, new Complex(this.kp, 0.0D));
            if (super.sZeros == null) {
                super.sZeros = Complex.oneDarray(1);
            }

            super.sZeros[0].reset(-this.kp / this.kd, 0.0D);
        }

        super.addDeadTimeExtras();
    }

    public void setKd(double var1) {
        this.kd = var1;
        this.td = var1 / this.kp;
        if (super.sNumerDeg != 1) {
            super.setSnumer(new ComplexPoly(new Complex(this.kp, 0.0D), new Complex(this.kd, 0.0D)));
            super.setSdenom(new ComplexPoly(1.0D));
        } else {
            super.sNumer.resetCoeff(1, new Complex(this.kd, 0.0D));
            if (super.sZeros == null) {
                super.sZeros = Complex.oneDarray(1);
            }

            super.sZeros[0].reset(-this.kp / this.kd, 0.0D);
        }

        super.addDeadTimeExtras();
    }

    public void setTd(double var1) {
        this.td = var1;
        this.kd = this.td * this.kp;
        if (super.sNumerDeg != 1) {
            super.setSnumer(new ComplexPoly(new Complex(this.kp, 0.0D), new Complex(this.kd, 0.0D)));
            super.setSdenom(new ComplexPoly(1.0D));
        } else {
            if (super.sZeros == null) {
                super.sZeros = Complex.oneDarray(1);
            }

            super.sNumer.resetCoeff(1, new Complex(this.kd, 0.0D));
            super.sZeros[0].reset(-this.kp / this.kd, 0.0D);
        }

        super.addDeadTimeExtras();
    }

    public double getKp() {
        return this.kp;
    }

    public double getKd() {
        return this.kd;
    }

    public double getTd() {
        return this.td;
    }

    public void zTransform() {
        if (super.deltaT == 0.0D) {
            System.out.println("z-transform attempted in PropDeriv with a zero sampling period");
        }

        super.deadTimeWarning("zTransform");
        if (this.ztransMethod == 0) {
            this.mapstozAdHoc();
        } else {
            super.zNumerDeg = 1;
            super.zDenomDeg = 1;
            super.zNumer = new ComplexPoly(-this.kd, this.kp * super.deltaT + this.kd);
            super.zDenom = new ComplexPoly(0.0D, super.deltaT);
            super.zZeros = Complex.oneDarray(1);
            super.zZeros[0].reset(this.kd / (this.kp * super.deltaT + this.kd), 0.0D);
            super.zPoles = Complex.oneDarray(1);
            super.zPoles[0].reset(0.0D, 0.0D);
        }

    }

    public void zTransform(double var1) {
        super.setDeltaT(var1);
        super.deadTimeWarning("zTransform");
        this.zTransform();
    }

    public void stepInput(double var1, double var3) {
        byte var5 = 51;
        double var6 = var3 / (double)(var5 - 2);
        double[][] var8 = new double[2][var5];
        var8[0][0] = 0.0D;
        var8[1][0] = 0.0D;

        for(int var9 = 2; var9 < var5; ++var9) {
            var8[0][var9] = var8[0][var9 - 1] + var6;
        }

        double var12 = this.kp * var1;
        var8[1][0] = 0.0D;

        int var11;
        for(var11 = 1; var11 < var5; ++var11) {
            var8[1][var11] = var12;
        }

        if (super.deadTime != 0.0D) {
            for(var11 = 0; var11 < var5; ++var11) {
                var8[0][var11] += super.deadTime;
            }
        }

        PlotGraph var13 = new PlotGraph(var8);
        var13.setGraphTitle("Step Input Transient:   Step magnitude = " + var1);
        var13.setGraphTitle2(this.getName());
        var13.setXaxisLegend("Time");
        var13.setXaxisUnitsName("s");
        var13.setYaxisLegend("Output");
        var13.setPoint(0);
        var13.setLine(3);
        var13.plot();
    }

    public void stepInput(double var1) {
        this.stepInput(1.0D, var1);
    }

    public void rampInput(double var1, int var3, double var4) {
        if (var3 == 0) {
            this.stepInput(var1, var4);
        } else {
            byte var6 = 50;
            double var7 = var4 / (double)(var6 - 1);
            double[][] var9 = new double[2][var6];
            double var10 = 0.0D;
            var9[0][0] = 0.0D;
            var9[1][0] = 0.0D;

            int var12;
            for(var12 = 1; var12 < var6; ++var12) {
                var9[0][var12] = var9[0][var12 - 1] + var7;
                var9[1][var12] = var1 * Math.pow(var9[0][var12], (double)(var3 - 1)) * (this.kp * var9[0][var12] + this.kd);
            }

            if (super.deadTime != 0.0D) {
                for(var12 = 0; var12 < var6; ++var12) {
                    var9[0][var12] += super.deadTime;
                }
            }

            PlotGraph var13 = new PlotGraph(var9);
            var13.setGraphTitle("Ramp (a.t^n) Input Transient:   ramp gradient (a) = " + var1 + " ramp order (n) = " + var3);
            var13.setGraphTitle2(this.getName());
            var13.setXaxisLegend("Time");
            var13.setXaxisUnitsName("s");
            var13.setYaxisLegend("Output");
            var13.setPoint(0);
            var13.plot();
        }

    }

    public void rampInput(int var1, double var2) {
        double var4 = 1.0D;
        this.rampInput(var4, var1, var2);
    }

    public void rampInput(double var1, double var3) {
        byte var5 = 1;
        this.rampInput(var1, var5, var3);
    }

    public void rampInput(double var1) {
        double var3 = 1.0D;
        byte var5 = 1;
        this.rampInput(var3, var5, var1);
    }

    public Complex getOutputS(Complex var1, Complex var2) {
        super.sValue = var1;
        super.inputS = var2;
        Complex var3 = this.sValue.times(this.kd);
        var3 = var3.plus(this.kp);
        super.outputS = var3.times(super.inputS);
        if (super.deadTime != 0.0D) {
            super.outputS = super.outputS.times(Complex.exp(super.sValue.times(-super.deadTime)));
        }

        return super.outputS;
    }

    public Complex getOutputS() {
        Complex var1 = this.sValue.times(this.kd);
        var1 = var1.plus(this.kp);
        super.outputS = var1.times(super.inputS);
        if (super.deadTime != 0.0D) {
            super.outputS = super.outputS.times(Complex.exp(super.sValue.times(-super.deadTime)));
        }

        return super.outputS;
    }

    public void calcOutputT(double var1, double var3) {
        super.setInputT(var1, var3);
        this.calcOutputT();
    }

    public void calcOutputT() {
        super.outputT[super.sampLen - 1] = this.kp * super.inputT[this.sampLen - 1];
        super.outputT[super.sampLen - 1] += this.kd * (super.inputT[super.sampLen - 1] - super.inputT[super.sampLen - 2]) / super.deltaT;
    }

    public Complex[] getSpoles() {
        System.out.println("PD controller has no s-domain poles");
        return null;
    }

    public PropDeriv copy() {
        if (this == null) {
            return null;
        } else {
            PropDeriv var1 = new PropDeriv();
            this.copyBBvariables(var1);
            var1.kp = this.kp;
            var1.td = this.td;
            var1.kd = this.kd;
            return var1;
        }
    }

    public Object clone() {
        return this.copy();
    }
}
