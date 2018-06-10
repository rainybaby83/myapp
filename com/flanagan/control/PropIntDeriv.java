//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.control;

import com.flanagan.complex.Complex;
import com.flanagan.complex.ComplexPoly;
import com.flanagan.plot.PlotGraph;

public class PropIntDeriv extends BlackBox {
    private double kp = 1.0D;
    private double ti = 1.0D / 0.0;
    private double ki = 0.0D;
    private double td = 0.0D;
    private double kd = 0.0D;

    public PropIntDeriv() {
        super("PropIntDeriv");
        super.setSnumer(new ComplexPoly(0.0D, 1.0D, 0.0D));
        super.setSdenom(new ComplexPoly(0.0D, 1.0D));
        super.setZtransformMethod(1);
        super.addDeadTimeExtras();
    }

    public PropIntDeriv(double var1, double var3, double var5) {
        super("PropIntDeriv");
        this.kp = var1;
        this.ki = var3;
        this.ti = this.kp / var3;
        this.kd = var5;
        this.td = this.kp / var5;
        super.setSnumer(new ComplexPoly(var1, var3, var5));
        super.setSdenom(new ComplexPoly(0.0D, 1.0D));
        super.setZtransformMethod(1);
        super.addDeadTimeExtras();
    }

    public void setKp(double var1) {
        this.kp = var1;
        if (super.sNumerDeg != 2) {
            super.setSnumer(new ComplexPoly(this.ki, this.kp, this.kd));
            super.setSdenom(new ComplexPoly(0.0D, 1.0D));
        } else {
            super.sNumer.resetCoeff(1, new Complex(var1, 0.0D));
            this.calcPolesZerosS();
        }

        super.addDeadTimeExtras();
    }

    public void setKi(double var1) {
        this.ki = var1;
        this.ti = this.kp / var1;
        if (super.sNumerDeg == 2 && this.sDenomDeg == 1) {
            super.sNumer.resetCoeff(0, new Complex(var1, 0.0D));
            this.calcPolesZerosS();
        } else {
            super.setSnumer(new ComplexPoly(this.ki, this.kp, this.kd));
            super.setSdenom(new ComplexPoly(0.0D, 1.0D));
        }

        super.addDeadTimeExtras();
    }

    public void setTi(double var1) {
        this.ti = var1;
        this.ki = this.kp / var1;
        if (super.sNumerDeg == 2 && this.sDenomDeg == 1) {
            super.sNumer.resetCoeff(0, new Complex(this.ki, 0.0D));
            this.calcPolesZerosS();
        } else {
            super.setSnumer(new ComplexPoly(this.ki, this.kp, this.kd));
            super.setSdenom(new ComplexPoly(0.0D, 1.0D));
        }

        super.addDeadTimeExtras();
    }

    public void setKd(double var1) {
        this.kd = var1;
        this.td = var1 / this.kp;
        if (super.sNumerDeg == 2 && this.sDenomDeg == 1) {
            super.sNumer.resetCoeff(2, new Complex(var1, 0.0D));
            this.calcPolesZerosS();
        } else {
            super.setSnumer(new ComplexPoly(this.ki, this.kp, this.kd));
            super.setSdenom(new ComplexPoly(0.0D, 1.0D));
        }

        super.addDeadTimeExtras();
    }

    public void setTd(double var1) {
        this.td = var1;
        this.kd = this.kp * var1;
        if (super.sNumerDeg == 2 && this.sDenomDeg == 1) {
            super.sNumer.resetCoeff(2, new Complex(this.kd, 0.0D));
            this.calcPolesZerosS();
        } else {
            super.setSnumer(new ComplexPoly(this.ki, this.kp, this.kd));
            super.setSdenom(new ComplexPoly(0.0D, 1.0D));
        }

        super.addDeadTimeExtras();
    }

    public double getKp() {
        return this.kp;
    }

    public double getKi() {
        return this.ki;
    }

    public double getTi() {
        return this.ti;
    }

    public double getKd() {
        return this.kd;
    }

    public double getTd() {
        return this.td;
    }

    public void zTransform() {
        if (super.deltaT == 0.0D) {
            System.out.println("z-transform attempted in PropIntDeriv with a zero sampling period");
        }

        super.deadTimeWarning("zTransform");
        if (super.ztransMethod == 0) {
            this.mapstozAdHoc();
        } else {
            double var1 = this.ki * super.deltaT;
            double var3 = this.kd / super.deltaT;
            Complex[] var5 = Complex.oneDarray(3);
            var5[0].reset(0.0D, 0.0D);
            var5[1].reset(-1.0D, 0.0D);
            var5[2].reset(1.0D, 0.0D);
            super.zDenom.resetPoly(var5);
            switch(this.integMethod) {
                case 0:
                    var5[0].reset(var3, 0.0D);
                    var5[1].reset(var1 / 2.0D - 2.0D * var3 - this.kp, 0.0D);
                    var5[2].reset(this.kp + var1 / 2.0D + var3, 0.0D);
                    super.zNumer.resetPoly(var5);
                    break;
                case 1:
                    var5[0].reset(var3, 0.0D);
                    var5[1].reset(-2.0D * var3 - this.kp, 0.0D);
                    var5[2].reset(this.kp + var1 + var3, 0.0D);
                    super.zNumer.resetPoly(var5);
                    break;
                case 2:
                    var5[0].reset(var3, 0.0D);
                    var5[1].reset(var1 - 2.0D * var3 - this.kp, 0.0D);
                    var5[2].reset(this.kp + var3, 0.0D);
                    super.zNumer.resetPoly(var5);
                    break;
                default:
                    System.out.println("Integration method option in PropIntDeriv must be 0,1 or 2");
                    System.out.println("It was set at " + this.integMethod);
                    System.out.println("z-transform not performed");
            }
        }

        super.zZeros = super.zNumer.roots();
        super.zPoles = super.zDenom.roots();
    }

    public void zTransform(double var1) {
        super.setDeltaT(var1);
        this.zTransform();
    }

    public void calcPolesZerosZ() {
        if (super.deltaT == 0.0D) {
            System.out.println("z-pole and z-zero calculation attempted in PropIntDeriv.calcPolesZerosZ( with a zero sampling period");
        }

        this.zTransform();
        super.zPoles[0].reset(0.0D, 0.0D);
        super.zPoles[1].reset(1.0D, 0.0D);
        super.zZeros = super.zNumer.roots();
    }

    public void calcPolesZerosZ(double var1) {
        this.deltaT = var1;
        this.calcPolesZerosZ();
    }

    public void stepInput(double var1, double var3) {
        byte var5 = 50;
        double var6 = var3 / (double)(var5 - 1);
        double[][] var8 = new double[2][var5];
        double var9 = 0.0D;
        var8[0][0] = 0.0D;

        for(int var11 = 1; var11 < var5; ++var11) {
            var8[0][var11] = var8[0][var11 - 1] + var6;
        }

        double var14 = this.kp * var1;

        int var13;
        for(var13 = 0; var13 < var5; ++var13) {
            var9 += this.ki * var6 * var1;
            var8[1][var13] = var14 + var9;
        }

        if (super.deadTime != 0.0D) {
            for(var13 = 0; var13 < var5; ++var13) {
                var8[0][var13] += super.deadTime;
            }
        }

        PlotGraph var15 = new PlotGraph(var8);
        var15.setGraphTitle("Step Input Transient:   Step magnitude = " + var1);
        var15.setGraphTitle2(this.getName());
        var15.setXaxisLegend("Time");
        var15.setXaxisUnitsName("s");
        var15.setYaxisLegend("Output");
        var15.setPoint(0);
        var15.setFrame();
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
                var10 += this.ki * var1 * (Math.pow(var9[0][var12], (double)(var3 + 1)) - Math.pow(var9[0][var12 - 1], (double)(var3 + 1))) / (double)(var3 + 1);
                var9[1][var12] = this.kp * var1 * Math.pow(var9[0][var12], (double)var3) + var10;
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
            var13.setFrame();
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
        Complex var3 = Complex.plusOne();
        Complex var4 = Complex.plusOne();
        Complex var5 = Complex.plusOne();
        var3 = var3.times(this.kp);
        var4 = var4.times(this.ki);
        var4 = var4.over(this.sValue);
        var5 = var5.times(this.kd);
        var5 = var5.times(super.sValue);
        Complex var6 = var3.plus(var4.plus(var5));
        super.outputS = var6.times(super.inputS);
        if (super.deadTime != 0.0D) {
            super.outputS = super.outputS.times(Complex.exp(super.sValue.times(-super.deadTime)));
        }

        return super.outputS;
    }

    public Complex getOutputS() {
        Complex var1 = Complex.plusOne();
        Complex var2 = Complex.plusOne();
        Complex var3 = Complex.plusOne();
        var1 = var1.times(this.kp);
        var2 = var2.times(this.ki);
        var2 = var2.over(this.sValue);
        var3 = var3.times(this.kd);
        var3 = var3.times(super.sValue);
        Complex var4 = var1.plus(var2.plus(var3));
        super.outputS = var4.times(super.inputS);
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
        super.deadTimeWarning("zTransform");
        super.outputT[super.sampLen - 1] = this.kp * super.inputT[super.sampLen - 1];
        if (super.forgetFactor == 1.0D) {
            switch(super.integMethod) {
                case 0:
                    super.integrationSum += (super.inputT[super.sampLen - 1] + super.inputT[super.sampLen - 2]) * super.deltaT / 2.0D;
                    break;
                case 1:
                    super.integrationSum += super.inputT[super.sampLen - 1] * super.deltaT;
                    break;
                case 2:
                    super.integrationSum += super.inputT[super.sampLen - 2] * super.deltaT;
                    break;
                default:
                    System.out.println("Integration method option in PropInt must be 0,1 or 2");
                    System.out.println("It was set at " + super.integMethod);
                    System.out.println("getOutput not performed");
            }
        } else {
            int var1;
            label41:
            switch(super.integMethod) {
                case 0:
                    super.integrationSum = 0.0D;
                    var1 = 1;

                    while(true) {
                        if (var1 >= super.sampLen) {
                            break label41;
                        }

                        super.integrationSum += Math.pow(super.forgetFactor, (double)(super.sampLen - 1 - var1)) * (super.inputT[var1 - 1] + super.inputT[var1]) * super.deltaT / 2.0D;
                        ++var1;
                    }
                case 1:
                    super.integrationSum = 0.0D;
                    var1 = 1;

                    while(true) {
                        if (var1 >= this.sampLen) {
                            break label41;
                        }

                        super.integrationSum += Math.pow(super.forgetFactor, (double)(super.sampLen - 1 - var1)) * super.inputT[var1] * super.deltaT;
                        ++var1;
                    }
                case 2:
                    super.integrationSum = 0.0D;
                    var1 = 1;

                    while(true) {
                        if (var1 >= super.sampLen) {
                            break label41;
                        }

                        super.integrationSum += Math.pow(super.forgetFactor, (double)(super.sampLen - 1 - var1)) * super.inputT[var1 - 1] * super.deltaT;
                        ++var1;
                    }
                default:
                    System.out.println("Integration method option in PropInt must be 0,1 or 2");
                    System.out.println("It was set at " + super.integMethod);
                    System.out.println("getOutput not performed");
            }
        }

        super.outputT[super.sampLen - 1] += this.ki * super.integrationSum;
        super.outputT[this.sampLen - 1] += this.kd * (super.inputT[this.sampLen - 1] - super.inputT[this.sampLen - 2]) / super.deltaT;
    }

    public PropIntDeriv copy() {
        if (this == null) {
            return null;
        } else {
            PropIntDeriv var1 = new PropIntDeriv();
            this.copyBBvariables(var1);
            var1.kp = this.kp;
            var1.ti = this.ti;
            var1.td = this.td;
            var1.kd = this.kd;
            return var1;
        }
    }

    public Object clone() {
        return this.copy();
    }
}
