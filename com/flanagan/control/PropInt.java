//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package flanagan.control;

import flanagan.complex.Complex;
import flanagan.complex.ComplexPoly;
import flanagan.plot.PlotGraph;

public class PropInt extends BlackBox {
    private double kp = 1.0D;
    private double ti = 1.0D / 0.0;
    private double ki = 0.0D;

    public PropInt() {
        super("PropInt");
        super.setSnumer(new ComplexPoly(0.0D, 1.0D));
        super.setSdenom(new ComplexPoly(0.0D, 1.0D));
        super.setZtransformMethod(1);
        super.addDeadTimeExtras();
    }

    public PropInt(double var1, double var3) {
        super("PropInt");
        this.kp = var1;
        this.ki = var3;
        this.ti = this.kp / var3;
        super.setSnumer(new ComplexPoly(new Complex(var3, 0.0D), new Complex(var1, 0.0D)));
        super.setSdenom(new ComplexPoly(0.0D, 1.0D));
        super.setZtransformMethod(1);
        super.addDeadTimeExtras();
    }

    public void setKp(double var1) {
        this.kp = var1;
        if (super.sNumerDeg != 1) {
            super.setSnumer(new ComplexPoly(new Complex(this.ki, 0.0D), new Complex(var1, 0.0D)));
            super.setSdenom(new ComplexPoly(0.0D, 1.0D));
        } else {
            super.sNumer.resetCoeff(1, new Complex(var1, 0.0D));
            super.calcPolesZerosS();
        }

        super.addDeadTimeExtras();
    }

    public void setKi(double var1) {
        this.ki = var1;
        this.ti = this.kp / var1;
        if (super.sNumerDeg != 1) {
            super.setSnumer(new ComplexPoly(new Complex(var1, 0.0D), new Complex(this.kp, 0.0D)));
            super.setSdenom(new ComplexPoly(0.0D, 1.0D));
        } else {
            super.sNumer.resetCoeff(0, new Complex(var1, 0.0D));
            super.calcPolesZerosS();
        }

        super.addDeadTimeExtras();
    }

    public void setTi(double var1) {
        this.ti = var1;
        this.ki = this.kp / var1;
        if (super.sNumerDeg != 1) {
            super.setSnumer(new ComplexPoly(new Complex(this.ki, 0.0D), new Complex(this.kp, 0.0D)));
            super.setSdenom(new ComplexPoly(0.0D, 1.0D));
        } else {
            super.sNumer.resetCoeff(0, new Complex(this.ki, 0.0D));
            super.calcPolesZerosS();
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

    public void zTransform() {
        super.deadTimeWarning("zTransform");
        if (super.deltaT == 0.0D) {
            System.out.println("z-transform attempted in PropInt with a zero sampling period");
        }

        if (super.ztransMethod == 0) {
            this.mapstozAdHoc();
        } else {
            super.zDenom = new ComplexPoly(1);
            Complex[] var1 = Complex.oneDarray(2);
            var1[0].reset(-1.0D, 0.0D);
            var1[1].reset(1.0D, 0.0D);
            super.zDenom.resetPoly(var1);
            Complex[] var2 = Complex.oneDarray(1);
            var2[0].reset(1.0D, 0.0D);
            super.zNumer = new ComplexPoly(1);
            Complex[] var3 = Complex.oneDarray(1);
            double var4 = this.ki * super.deltaT;
            switch(this.integMethod) {
                case 0:
                    var1[0].reset(var4 / 2.0D - this.kp, 0.0D);
                    var1[1].reset(var4 / 2.0D + this.kp, 0.0D);
                    super.zNumer.resetPoly(var1);
                    var3[0].reset((this.kp - var4 / 2.0D) / (this.kp + var4 / 2.0D), 0.0D);
                    break;
                case 1:
                    var1[0].reset(-this.kp, 0.0D);
                    var1[1].reset(var4 + this.kp, 0.0D);
                    super.zNumer.resetPoly(var1);
                    var3[0].reset(this.kp / (this.kp + var4), 0.0D);
                    break;
                case 2:
                    var1[0].reset(this.kp - var4, 0.0D);
                    var1[1].reset(this.kp, 0.0D);
                    super.zNumer.resetPoly(var1);
                    var3[0].reset((this.kp - var4) / this.kp, 0.0D);
                    break;
                default:
                    System.out.println("Integration method option in PropInt must be 0,1 or 2");
                    System.out.println("It was set at " + this.integMethod);
                    System.out.println("z-transform not performed");
            }
        }

    }

    public void zTransform(double var1) {
        super.setDeltaT(var1);
        this.zTransform();
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

        for(int var13 = 0; var13 < var5; ++var13) {
            var9 += this.ki * var6 * var1;
            var8[1][var13] = var14 + var9;
            var8[0][var13] += super.deadTime;
        }

        PlotGraph var15 = new PlotGraph(var8);
        var15.setGraphTitle("Step Input Transient:   Step magnitude = " + var1);
        var15.setGraphTitle2(this.getName());
        var15.setXaxisLegend("Time");
        var15.setXaxisUnitsName("s");
        var15.setYaxisLegend("Output");
        var15.setPoint(0);
        var15.plot();
    }

    public void stepInput(double var1) {
        this.stepInput(1.0D, var1);
    }

    public void rampInput(double var1, int var3, double var4) {
        byte var6 = 50;
        double var7 = var4 / (double)(var6 - 1);
        double[][] var9 = new double[2][var6];
        double var10 = 0.0D;
        var9[0][0] = 0.0D;
        var9[1][0] = 0.0D;

        int var12;
        for(var12 = 1; var12 < var6; ++var12) {
            var9[0][var12] = var9[0][var12 - 1] + var7;
            var10 += var1 * (Math.pow(var9[0][var12], (double)(var3 + 1)) - Math.pow(var9[0][var12 - 1], (double)(var3 + 1))) / (double)(var3 + 1);
            var9[1][var12] = this.kp * var1 * Math.pow(var9[0][var12], (double)var3) + var10;
        }

        for(var12 = 0; var12 < var6; ++var12) {
            var9[0][var12] += super.deadTime;
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
        Complex var3 = super.sValue.times(this.kp);
        var3 = var3.plus(this.ki);
        var3 = var3.over(super.sValue);
        super.outputS = var3.times(super.inputS);
        if (super.deadTime != 0.0D) {
            super.outputS = super.outputS.times(Complex.exp(super.sValue.times(-super.deadTime)));
        }

        return super.outputS;
    }

    public Complex getOutputS() {
        Complex var1 = super.sValue.times(this.kp);
        var1 = var1.plus(this.ki);
        var1 = var1.over(super.sValue);
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
        super.deadTimeWarning("calcOutputT()");
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
    }

    public PropInt copy() {
        if (this == null) {
            return null;
        } else {
            PropInt var1 = new PropInt();
            this.copyBBvariables(var1);
            var1.kp = this.kp;
            var1.ti = this.ti;
            var1.ki = this.ki;
            return var1;
        }
    }

    public Object clone() {
        return this.copy();
    }
}
