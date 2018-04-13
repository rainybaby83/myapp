//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package flanagan.control;

import flanagan.complex.Complex;
import flanagan.complex.ComplexPoly;
import flanagan.plot.PlotGraph;

public class Transducer extends BlackBox {
    private double tGain = 1.0D;
    private double tConst = 0.0D;
    private double aConst = 0.0D;
    private double bConst = 1.0D;
    private double cConst = 1.0D;

    public Transducer() {
        super("Transducer");
        super.sPoles = Complex.oneDarray(1);
        super.setSnumer(new ComplexPoly(1.0D));
        super.setSdenom(new ComplexPoly(1.0D, 0.0D));
        super.setZtransformMethod(1);
        super.addDeadTimeExtras();
    }

    public Transducer(double var1, double var3) {
        super("Transducer");
        this.tGain = var1;
        this.tConst = var3;
        this.aConst = var3;
        this.bConst = 1.0D;
        this.cConst = var1;
        super.sPoles = Complex.oneDarray(1);
        super.setSnumer(new ComplexPoly(this.cConst));
        super.setSdenom(new ComplexPoly(this.bConst, this.aConst));
        super.setZtransformMethod(1);
        super.addDeadTimeExtras();
    }

    public Transducer(double var1) {
        super("Transducer");
        this.tGain = var1;
        this.tConst = 0.0D;
        this.aConst = 0.0D;
        this.bConst = 1.0D;
        this.cConst = var1;
        super.sPoles = Complex.oneDarray(1);
        super.setSnumer(new ComplexPoly(this.cConst));
        super.setSdenom(new ComplexPoly(this.bConst, this.aConst));
        super.setZtransformMethod(1);
        super.addDeadTimeExtras();
    }

    public void setCoeff(double var1, double var3) {
        if (this.aConst == 0.0D) {
            if (this.bConst == 0.0D) {
                this.aConst = var3;
                this.bConst = 1.0D;
            } else {
                this.aConst = var3 * this.bConst;
            }
        } else if (this.bConst == 0.0D) {
            this.aConst = var3;
            this.bConst = 1.0D;
        } else {
            this.aConst = this.aConst * var3 / this.tConst;
        }

        this.tConst = var3;
        if (this.cConst == 0.0D) {
            if (this.bConst == 0.0D) {
                this.cConst = var1;
                this.bConst = 1.0D;
            } else {
                this.cConst = var1 * this.bConst;
            }
        } else if (this.bConst == 0.0D) {
            this.cConst = var1;
            this.bConst = 1.0D;
        } else {
            this.cConst = this.cConst * var1 / this.tConst;
        }

        this.tGain = var1;
        super.sPoles = Complex.oneDarray(1);
        super.setSnumer(new ComplexPoly(this.cConst));
        super.setSdenom(new ComplexPoly(this.bConst, this.aConst));
        this.calcPolesZerosS();
        super.addDeadTimeExtras();
    }

    public void setTimeConstant(double var1) {
        if (this.aConst == 0.0D) {
            if (this.bConst == 0.0D) {
                this.aConst = var1;
                this.bConst = 1.0D;
            } else {
                this.aConst = var1 * this.bConst;
            }
        } else if (this.bConst == 0.0D) {
            this.aConst = var1;
            this.bConst = 1.0D;
        } else {
            this.aConst = this.aConst * var1 / this.tConst;
        }

        this.tConst = var1;
        super.sPoles = Complex.oneDarray(1);
        super.setSnumer(new ComplexPoly(this.cConst));
        super.setSdenom(new ComplexPoly(this.bConst, this.aConst));
        this.calcPolesZerosS();
        super.addDeadTimeExtras();
    }

    public void setGain(double var1) {
        if (this.cConst == 0.0D) {
            if (this.bConst == 0.0D) {
                this.cConst = var1;
                this.bConst = 1.0D;
            } else {
                this.cConst = var1 * this.bConst;
            }
        } else if (this.bConst == 0.0D) {
            this.cConst = var1;
            this.bConst = 1.0D;
        } else {
            this.cConst = this.cConst * var1 / this.tGain;
        }

        this.tGain = var1;
        super.sPoles = Complex.oneDarray(1);
        super.setSnumer(new ComplexPoly(this.cConst));
        super.setSdenom(new ComplexPoly(this.bConst, this.aConst));
        this.calcPolesZerosS();
        super.addDeadTimeExtras();
    }

    public double getGain() {
        return this.tGain;
    }

    public double getTimeConstant() {
        return this.tConst;
    }

    protected void calcPolesZerosS() {
        super.sPoles = Complex.oneDarray(1);
        super.sPoles[0].setReal(-this.bConst / this.aConst);
        super.sNumerScaleFactor = super.sNumer.coeffCopy(0);
        super.sNumerScaleFactor = BlackBox.scaleFactor(super.sNumer, super.sPoles);
    }

    public void stepInput(double var1, double var3) {
        if (this.tConst == 0.0D) {
            byte var5 = 51;
            double var6 = var3 / (double)(var5 - 2);
            double[][] var8 = new double[2][var5];
            var8[0][0] = 0.0D;
            var8[0][1] = 0.0D;

            for(int var9 = 2; var9 < var5; ++var9) {
                var8[0][var9] = var8[0][var9 - 1] + var6;
            }

            double var12 = this.tGain * var1;
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
        } else {
            super.stepInput(var1, var3);
        }

    }

    public void zTransform() {
        if (super.deltaT == 0.0D) {
            System.out.println("z-transform attempted in Transducer with a zero sampling period");
        }

        super.deadTimeWarning("zTransform");
        if (this.ztransMethod == 0) {
            this.mapstozAdHoc();
        } else {
            Complex[] var1 = null;
            Complex[] var2 = null;
            switch(this.integMethod) {
                case 0:
                    var1 = Complex.oneDarray(2);
                    var1[0].reset(this.deltaT * this.cConst, 0.0D);
                    var1[1].reset(this.deltaT * this.cConst, 0.0D);
                    super.zNumer = new ComplexPoly(1);
                    super.zNumer.resetPoly(var1);
                    super.zNumerDeg = 1;
                    var2 = Complex.oneDarray(2);
                    var2[0].reset(this.bConst * this.deltaT - 2.0D * this.aConst, 0.0D);
                    var2[1].reset(this.bConst * this.deltaT + 2.0D * this.aConst, 0.0D);
                    super.zDenom = new ComplexPoly(1);
                    super.zDenom.resetPoly(var2);
                    super.zDenomDeg = 1;
                    super.zZeros = Complex.oneDarray(1);
                    super.zZeros[0].reset(-1.0D, 0.0D);
                    super.zPoles = Complex.oneDarray(1);
                    super.zPoles[0].reset((2.0D * this.aConst - super.deltaT * this.bConst) / (2.0D * this.aConst + super.deltaT * this.bConst), 0.0D);
                    break;
                case 1:
                    var1 = Complex.oneDarray(2);
                    var1[0].reset(0.0D, 0.0D);
                    var1[1].reset(this.cConst * this.deltaT, 0.0D);
                    super.zNumer = new ComplexPoly(1);
                    super.zNumer.resetPoly(var1);
                    super.zNumerDeg = 1;
                    var2 = Complex.oneDarray(2);
                    var2[0].reset(this.bConst * this.deltaT + this.aConst, 0.0D);
                    var2[1].reset(this.aConst, 0.0D);
                    super.zDenom = new ComplexPoly(1);
                    super.zDenom.resetPoly(var2);
                    super.zDenomDeg = 1;
                    super.zZeros = Complex.oneDarray(1);
                    super.zZeros[0].reset(0.0D, 0.0D);
                    super.zPoles = Complex.oneDarray(1);
                    super.zPoles[0].reset(this.aConst / (super.deltaT * this.bConst + this.aConst), 0.0D);
                    break;
                case 2:
                    var1 = Complex.oneDarray(1);
                    var1[0].reset(this.cConst * this.deltaT, 0.0D);
                    super.zNumer = new ComplexPoly(0);
                    super.zNumer.resetPoly(var1);
                    super.zNumerDeg = 0;
                    var2 = Complex.oneDarray(2);
                    var2[0].reset(-this.aConst, 0.0D);
                    var2[1].reset(this.bConst * this.deltaT - this.aConst, 0.0D);
                    super.zDenom = new ComplexPoly(1);
                    super.zDenom.resetPoly(var2);
                    super.zDenomDeg = 1;
                    super.zPoles = Complex.oneDarray(1);
                    super.zPoles[0].reset(this.aConst / (super.deltaT * this.bConst - this.aConst), 0.0D);
                    break;
                default:
                    System.out.println("Integration method option in Transducer must be 0,1 or 2");
                    System.out.println("It was set at " + this.integMethod);
                    System.out.println("z-transform not performed");
            }
        }

    }

    public void zTransform(double var1) {
        super.setDeltaT(var1);
        this.zTransform();
    }

    public Complex getOutputS(Complex var1, Complex var2) {
        super.sValue = var1;
        super.inputS = var2;
        return this.getOutputS();
    }

    public Complex getOutputS() {
        Complex var1 = Complex.plusOne();
        var1 = var1.times(this.cConst);
        new Complex();
        Complex var2 = this.sValue.times(this.aConst);
        var2 = var2.plus(this.bConst);
        new Complex();
        Complex var3 = var1.over(var2);
        super.outputS = var3.times(super.inputS);
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
        super.outputT[this.sampLen - 1] = (this.bConst * super.inputT[this.sampLen - 1] + this.aConst * (super.inputT[this.sampLen - 1] - super.inputT[this.sampLen - 2]) / super.deltaT) / this.cConst;
    }

    public Complex[] getSzeros() {
        System.out.println("This standard first order process (class Transducer) has no s-domain zeros");
        return null;
    }

    public Transducer copy() {
        if (this == null) {
            return null;
        } else {
            Transducer var1 = new Transducer();
            this.copyBBvariables(var1);
            var1.aConst = this.aConst;
            var1.bConst = this.bConst;
            var1.cConst = this.cConst;
            return var1;
        }
    }

    public Object clone() {
        return this.copy();
    }
}
