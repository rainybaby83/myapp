//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.control;

import com.flanagan.complex.Complex;
import com.flanagan.complex.ComplexMatrix;
import com.flanagan.complex.ComplexPoly;
import com.flanagan.interpolation.CubicSpline;
import com.flanagan.io.Db;
import com.flanagan.math.Conv;
import com.flanagan.math.Fmath;
import com.flanagan.plot.PlotGraph;
import com.flanagan.plot.PlotPoleZero;

public class BlackBox {
    protected int sampLen = 0;
    protected double[] inputT = null;
    protected double[] outputT = null;
    protected double[] time = null;
    protected double forgetFactor = 1.0D;
    protected double deltaT = 0.0D;
    protected double sampFreq = 0.0D;
    protected Complex inputS = new Complex();
    protected Complex outputS = new Complex();
    protected Complex sValue = new Complex();
    protected Complex zValue = new Complex();
    protected ComplexPoly sNumer = new ComplexPoly(1.0D);
    protected ComplexPoly sDenom = new ComplexPoly(1.0D);
    protected ComplexPoly zNumer = new ComplexPoly(1.0D);
    protected ComplexPoly zDenom = new ComplexPoly(1.0D);
    protected boolean sNumerSet = false;
    protected boolean sDenomSet = false;
    protected Complex sNumerScaleFactor = Complex.plusOne();
    protected Complex sDenomScaleFactor = Complex.plusOne();
    protected Complex sNumerWorkingFactor = Complex.plusOne();
    protected Complex sDenomWorkingFactor = Complex.plusOne();
    protected Complex[] sPoles = null;
    protected Complex[] sZeros = null;
    protected Complex[] zPoles = null;
    protected Complex[] zZeros = null;
    protected int sNumerDeg = 0;
    protected int sDenomDeg = 0;
    protected int zNumerDeg = 0;
    protected int zDenomDeg = 0;
    protected double deadTime = 0.0D;
    protected int orderPade = 2;
    protected ComplexPoly sNumerPade = new ComplexPoly(1.0D);
    protected ComplexPoly sDenomPade = new ComplexPoly(1.0D);
    protected Complex[] sPolesPade = null;
    protected Complex[] sZerosPade = null;
    protected int sNumerDegPade = 0;
    protected int sDenomDegPade = 0;
    protected boolean maptozero = true;
    protected boolean padeAdded = false;
    protected double integrationSum = 0.0D;
    protected int integMethod = 1;
    protected int ztransMethod = 0;
    protected String name = "BlackBox";
    protected String fixedName = "BlackBox";
    protected int nPlotPoints = 400;
    protected String[] subclassName = new String[]{"BlackBox", "OpenLoop", "ClosedLoop", "Prop", "PropDeriv", "PropInt", "PropIntDeriv", "FirstOrder", "SecondOrder", "Compensator", "LowPassPassive", "HighPassPassive", "Transducer", "DelayLine", "ZeroOrderHold", "AtoD", "DtoA"};
    protected int nSubclasses;
    protected int subclassIndex;

    public BlackBox() {
        this.nSubclasses = this.subclassName.length;
        this.subclassIndex = 0;
    }

    public BlackBox(String var1) {
        this.nSubclasses = this.subclassName.length;
        this.subclassIndex = 0;
        this.name = var1;
        this.fixedName = var1;
        this.setSubclassIndex();
    }

    protected void setSubclassIndex() {
        boolean var1 = true;
        int var2 = 0;

        while(var1) {
            if (this.fixedName.equals(this.subclassName[var2])) {
                this.subclassIndex = var2;
                var1 = false;
            } else {
                ++var2;
                if (var2 >= this.nSubclasses) {
                    System.out.println("Subclass name, " + this.fixedName + ", not recognised as a recorded subclass within this library");
                    System.out.println("Subclass, " + this.fixedName + ", handled as BlackBox");
                    this.subclassIndex = var2;
                    var1 = false;
                }
            }
        }

    }

    public void setSnumer(double[] var1) {
        this.sNumerDeg = var1.length - 1;
        this.sNumer = new ComplexPoly(var1);
        this.sNumerSet = true;
        this.calcPolesZerosS();
        this.addDeadTimeExtras();
    }

    protected void addDeadTimeExtras() {
        this.sNumerDegPade = this.sNumerDeg;
        this.sNumerPade = this.sNumer.copy();
        this.sDenomDegPade = this.sDenomDeg;
        this.sDenomPade = this.sDenom.copy();
        if (this.deadTime == 0.0D) {
            this.transferPolesZeros();
        } else {
            this.pade();
        }

    }

    public void setSnumer(Complex[] var1) {
        this.sNumerDeg = var1.length - 1;
        this.sNumer = new ComplexPoly(var1);
        this.sNumerSet = true;
        this.calcPolesZerosS();
        this.addDeadTimeExtras();
    }

    public void setSnumer(ComplexPoly var1) {
        this.sNumerDeg = var1.getDeg();
        this.sNumer = ComplexPoly.copy(var1);
        this.sNumerSet = true;
        this.calcPolesZerosS();
        this.addDeadTimeExtras();
    }

    public void setSdenom(double[] var1) {
        this.sDenomDeg = var1.length - 1;
        this.sDenom = new ComplexPoly(var1);
        this.sDenomSet = true;
        this.calcPolesZerosS();
        this.addDeadTimeExtras();
    }

    public void setSdenom(Complex[] var1) {
        this.sDenomDeg = var1.length - 1;
        this.sDenom = new ComplexPoly(var1);
        this.sDenomSet = true;
        this.calcPolesZerosS();
        this.addDeadTimeExtras();
    }

    public void setSdenom(ComplexPoly var1) {
        this.sDenomDeg = var1.getDeg();
        this.sDenom = var1.copy();
        this.sDenomSet = true;
        this.calcPolesZerosS();
        this.addDeadTimeExtras();
    }

    public static Complex scaleFactor(ComplexPoly var0, Complex[] var1) {
        int var2 = var1.length;
        Complex var3 = new Complex(0.0D, 0.0D);

        for(int var4 = 0; var4 < var2; ++var4) {
            var3 = var3.plus(var1[var4]);
        }

        var3 = var3.over((double)var2);
        boolean var8 = true;
        int var5 = 0;

        while(true) {
            while(var8) {
                if (var3.isEqual(var1[var5])) {
                    if (!var3.isEqual(Complex.zero())) {
                        var3 = var3.times(1.5D);
                    } else {
                        for(int var6 = 0; var6 < var2; ++var6) {
                            var3 = var3.plus(var1[var6].abs());
                        }

                        if (var3.isEqual(Complex.zero())) {
                            var3 = Complex.plusOne();
                        }
                    }

                    var5 = 0;
                } else {
                    ++var5;
                    if (var5 > var2 - 1) {
                        var8 = false;
                    }
                }
            }

            Complex var9 = new Complex(1.0D, 0.0D);

            for(int var7 = 0; var7 < var2; ++var7) {
                var9 = var9.times(var3.minus(var1[var7]));
            }

            Complex var10 = var0.evaluate(var3);
            return var10.over(var9);
        }
    }

    public Complex getSnumerScaleFactor() {
        if (this.sNumerScaleFactor == null) {
            this.calcPolesZerosS();
        }

        return this.sNumerScaleFactor;
    }

    public Complex getSdenomScaleFactor() {
        if (this.sDenomScaleFactor == null) {
            this.calcPolesZerosS();
        }

        return this.sDenomScaleFactor;
    }

    public void setDeadTime(double var1) {
        this.deadTime = var1;
        this.pade();
    }

    public void setDeadTime(double var1, int var3) {
        this.deadTime = var1;
        if (var3 > 5) {
            var3 = 4;
            System.out.println("BlackBox does not support Pade approximations above an order of 4");
            System.out.println("The order has been set to 4");
        }

        if (var3 < 1) {
            var3 = 1;
            System.out.println("Pade approximation order was less than 1");
            System.out.println("The order has been set to 1");
        }

        this.orderPade = var3;
        this.pade();
    }

    public void setPadeOrder(int var1) {
        if (var1 > 5) {
            var1 = 4;
            System.out.println("BlackBox does not support Pade approximations above an order of 4");
            System.out.println("The order has been set to 4");
        }

        if (var1 < 1) {
            var1 = 2;
            System.out.println("Pade approximation order was less than 1");
            System.out.println("The order has been set to 2");
        }

        this.orderPade = var1;
        this.pade();
    }

    public double getDeadTime() {
        return this.deadTime;
    }

    public int getPadeOrder() {
        return this.orderPade;
    }

    protected void pade() {
        ComplexPoly var1 = null;
        ComplexPoly var2 = null;
        Complex[] var3 = null;
        Complex[] var4 = null;
        switch(this.orderPade) {
            case 1:
                this.sNumerDegPade = this.sNumerDeg + 1;
                this.sDenomDegPade = this.sDenomDeg + 1;
                this.sNumerPade = new ComplexPoly(this.sNumerDegPade);
                this.sDenomPade = new ComplexPoly(this.sDenomDegPade);
                var1 = new ComplexPoly(1.0D, -this.deadTime / 2.0D);
                var2 = new ComplexPoly(1.0D, this.deadTime / 2.0D);
                this.sNumerPade = this.sNumer.times(var1);
                this.sDenomPade = this.sDenom.times(var2);
                var3 = Complex.oneDarray(1);
                var3[0].reset(2.0D / this.deadTime, 0.0D);
                var4 = Complex.oneDarray(1);
                var4[0].reset(-2.0D / this.deadTime, 0.0D);
                break;
            case 2:
                this.sNumerDegPade = this.sNumerDeg + 2;
                this.sDenomDegPade = this.sDenomDeg + 2;
                this.sNumerPade = new ComplexPoly(this.sNumerDegPade);
                this.sDenomPade = new ComplexPoly(this.sDenomDegPade);
                var1 = new ComplexPoly(1.0D, -this.deadTime / 2.0D, Math.pow(this.deadTime, 2.0D) / 12.0D);
                var2 = new ComplexPoly(1.0D, this.deadTime / 2.0D, Math.pow(this.deadTime, 2.0D) / 12.0D);
                this.sNumerPade = this.sNumer.times(var1);
                this.sDenomPade = this.sDenom.times(var2);
                var3 = var1.rootsNoMessages();
                var4 = var2.rootsNoMessages();
                break;
            case 3:
                this.sNumerDegPade = this.sNumerDeg + 3;
                this.sDenomDegPade = this.sDenomDeg + 3;
                this.sNumerPade = new ComplexPoly(this.sNumerDegPade);
                this.sDenomPade = new ComplexPoly(this.sDenomDegPade);
                double[] var5 = new double[]{1.0D, -this.deadTime / 2.0D, Math.pow(this.deadTime, 2.0D) / 10.0D, -Math.pow(this.deadTime, 3.0D) / 120.0D};
                var1 = new ComplexPoly(var5);
                this.sNumerPade = this.sNumer.times(var1);
                var3 = var1.rootsNoMessages();
                double[] var6 = new double[]{1.0D, this.deadTime / 2.0D, Math.pow(this.deadTime, 2.0D) / 10.0D, Math.pow(this.deadTime, 3.0D) / 120.0D};
                var2 = new ComplexPoly(var6);
                this.sDenomPade = this.sDenom.times(var2);
                var4 = var2.rootsNoMessages();
                break;
            case 4:
                this.sNumerDegPade = this.sNumerDeg + 4;
                this.sDenomDegPade = this.sDenomDeg + 4;
                this.sNumerPade = new ComplexPoly(this.sNumerDegPade);
                this.sDenomPade = new ComplexPoly(this.sDenomDegPade);
                double[] var7 = new double[]{1.0D, -this.deadTime / 2.0D, 3.0D * Math.pow(this.deadTime, 2.0D) / 28.0D, -Math.pow(this.deadTime, 3.0D) / 84.0D, Math.pow(this.deadTime, 4.0D) / 1680.0D};
                var1 = new ComplexPoly(var7);
                this.sNumerPade = this.sNumer.times(var1);
                var3 = var1.rootsNoMessages();
                double[] var8 = new double[]{1.0D, this.deadTime / 2.0D, 3.0D * Math.pow(this.deadTime, 2.0D) / 28.0D, Math.pow(this.deadTime, 3.0D) / 84.0D, Math.pow(this.deadTime, 4.0D) / 1680.0D};
                var2 = new ComplexPoly(var8);
                this.sDenomPade = this.sDenom.times(var2);
                var4 = var2.rootsNoMessages();
                break;
            default:
                this.orderPade = 2;
                this.sNumerDegPade = this.sNumerDeg + 2;
                this.sDenomDegPade = this.sDenomDeg + 2;
                this.sNumerPade = new ComplexPoly(this.sNumerDegPade);
                this.sDenomPade = new ComplexPoly(this.sDenomDegPade);
                var1 = new ComplexPoly(1.0D, -this.deadTime / 2.0D, Math.pow(this.deadTime, 2.0D) / 12.0D);
                var2 = new ComplexPoly(1.0D, this.deadTime / 2.0D, Math.pow(this.deadTime, 2.0D) / 12.0D);
                this.sNumerPade = this.sNumer.times(var1);
                this.sDenomPade = this.sDenom.times(var2);
                var3 = var1.rootsNoMessages();
                var4 = var2.rootsNoMessages();
        }

        int var9;
        if (this.sNumerPade != null && this.sNumerDegPade > 0) {
            this.sZerosPade = Complex.oneDarray(this.sNumerDegPade);

            for(var9 = 0; var9 < this.sNumerDeg; ++var9) {
                this.sZerosPade[var9] = this.sZeros[var9].copy();
            }

            for(var9 = 0; var9 < this.orderPade; ++var9) {
                this.sZerosPade[var9 + this.sNumerDeg] = var3[var9].copy();
            }
        }

        if (this.sDenomPade != null && this.sDenomDegPade > 0) {
            this.sPolesPade = Complex.oneDarray(this.sDenomDegPade);

            for(var9 = 0; var9 < this.sDenomDeg; ++var9) {
                this.sPolesPade[var9] = this.sPoles[var9].copy();
            }

            for(var9 = 0; var9 < this.orderPade; ++var9) {
                this.sPolesPade[var9 + this.sDenomDeg] = var4[var9].copy();
            }
        }

        this.zeroPoleCancellation();
        this.padeAdded = true;
    }

    protected void transferPolesZeros() {
        this.sNumerDegPade = this.sNumerDeg;
        this.sNumerPade = this.sNumer.copy();
        int var1;
        if (this.sNumerDeg > 0 && this.sZeros != null) {
            this.sZerosPade = Complex.oneDarray(this.sNumerDeg);

            for(var1 = 0; var1 < this.sNumerDeg; ++var1) {
                this.sZerosPade[var1] = this.sZeros[var1].copy();
            }
        }

        this.sDenomDegPade = this.sDenomDeg;
        this.sDenomPade = this.sDenom.copy();
        if (this.sDenomDeg > 0 && this.sPoles != null) {
            this.sPolesPade = Complex.oneDarray(this.sDenomDeg);

            for(var1 = 0; var1 < this.sDenomDeg; ++var1) {
                this.sPolesPade[var1] = this.sPoles[var1].copy();
            }
        }

        this.zeroPoleCancellation();
        this.padeAdded = true;
    }

    public int orderPade() {
        return this.orderPade;
    }

    protected boolean deadTimeWarning(String var1) {
        boolean var2 = false;
        if (this.deadTime > this.deltaT) {
            System.out.println(this.name + "." + var1 + ": The dead time is greater than the sampling period");
            System.out.println("Dead time:       " + this.deadTime);
            System.out.println("Sampling period: " + this.deltaT);
            System.out.println("!!! The results of this program may not be physically meaningful !!!");
            var2 = true;
        }

        return var2;
    }

    public void zTransform(double var1) {
        this.mapstozAdHoc(var1);
    }

    public void zTransform() {
        this.mapstozAdHoc();
    }

    public void mapstozAdHoc(double var1) {
        this.deltaT = var1;
        this.mapstozAdHoc();
    }

    public void mapstozAdHoc() {
        this.deadTimeWarning("mapstozAdHoc");
        if (!this.padeAdded) {
            this.transferPolesZeros();
        }

        this.zDenomDeg = this.sDenomDegPade;
        new ComplexPoly(1);
        this.zDenom = new ComplexPoly(this.zDenomDeg);
        int var2;
        if (this.zDenomDeg > 0) {
            this.zPoles = Complex.oneDarray(this.zDenomDeg);

            for(var2 = 0; var2 < this.zDenomDeg; ++var2) {
                this.zPoles[var2] = Complex.exp(this.sPolesPade[var2].times(this.deltaT));
            }

            this.zDenom = ComplexPoly.rootsToPoly(this.zPoles);
        }

        var2 = this.sDenomDegPade;
        if (var2 + this.sNumerDegPade > this.sDenomDegPade) {
            var2 = this.sDenomDegPade - this.sNumerDegPade;
        }

        this.zNumerDeg = this.sNumerDegPade + var2;
        this.zNumer = new ComplexPoly(this.zNumerDeg);
        this.zZeros = Complex.oneDarray(this.zNumerDeg);
        if (this.zNumerDeg > 0) {
            int var3;
            for(var3 = 0; var3 < this.sNumerDegPade; ++var3) {
                this.zZeros[var3] = Complex.exp(this.sZerosPade[var3].times(this.deltaT));
            }

            if (var2 > 0) {
                if (this.maptozero) {
                    for(var3 = this.sNumerDegPade; var3 < this.zNumerDeg; ++var3) {
                        this.zZeros[var3] = Complex.zero();
                    }
                } else {
                    for(var3 = this.sNumerDegPade; var3 < this.zNumerDeg; ++var3) {
                        this.zZeros[var3] = Complex.minusOne();
                    }
                }
            }

            this.zNumer = ComplexPoly.rootsToPoly(this.zZeros);
        }

        this.sValue = Complex.zero();
        this.zValue = Complex.plusOne();
        boolean var8 = true;

        while(var8) {
            var8 = false;
            int var4;
            if (this.sDenomDegPade > 0) {
                for(var4 = 0; var4 < this.sDenomDegPade; ++var4) {
                    if (this.sPolesPade[var4].truncate(3).equals(this.sValue.truncate(3))) {
                        var8 = true;
                    }
                }
            }

            if (!var8 && this.sNumerDegPade > 0) {
                for(var4 = 0; var4 < this.sDenomDegPade; ++var4) {
                    if (this.sZerosPade[var4].truncate(3).equals(this.sValue.truncate(3))) {
                        var8 = true;
                    }
                }
            }

            if (!var8 && this.zDenomDeg > 0) {
                for(var4 = 0; var4 < this.zDenomDeg; ++var4) {
                    if (this.zPoles[var4].truncate(3).equals(this.zValue.truncate(3))) {
                        var8 = true;
                    }
                }
            }

            if (!var8 && this.zNumerDeg > 0) {
                for(var4 = 0; var4 < this.zDenomDeg; ++var4) {
                    if (this.zZeros[var4].truncate(3).equals(this.zValue.truncate(3))) {
                        var8 = true;
                    }
                }
            }

            if (var8) {
                this.sValue = this.sValue.plus(Complex.plusJay()).truncate(3);
                this.zValue = Complex.exp(this.sValue.times(this.deltaT).truncate(3));
            }
        }

        Complex var9 = this.evalTransFunctS(this.sValue);
        Complex var5 = this.evalTransFunctZ(this.zValue);
        Complex var6 = var9.over(var5);
        ComplexPoly var7 = new ComplexPoly(var6);
        this.zNumer = this.zNumer.times(var7);
    }

    public void setMaptozero(boolean var1) {
        this.maptozero = var1;
    }

    public void setZnumer(double[] var1) {
        this.zNumerDeg = var1.length - 1;
        this.zNumer = new ComplexPoly(var1);
        this.zZeros = this.zNumer.rootsNoMessages();
    }

    public void setZnumer(Complex[] var1) {
        this.zNumerDeg = var1.length - 1;
        this.zNumer = new ComplexPoly(var1);
        this.zZeros = this.zNumer.rootsNoMessages();
    }

    public void setZnumer(ComplexPoly var1) {
        this.zNumerDeg = var1.getDeg();
        this.zNumer = ComplexPoly.copy(var1);
        this.zZeros = this.zNumer.rootsNoMessages();
    }

    public void setZdenom(double[] var1) {
        this.zDenomDeg = var1.length - 1;
        this.zDenom = new ComplexPoly(var1);
        this.zPoles = this.zDenom.rootsNoMessages();
    }

    public void setZdenom(Complex[] var1) {
        this.zDenomDeg = var1.length - 1;
        this.zDenom = new ComplexPoly(var1);
        this.zPoles = this.zDenom.rootsNoMessages();
    }

    public void setZdenom(ComplexPoly var1) {
        this.zDenomDeg = var1.getDeg();
        this.zDenom = ComplexPoly.copy(var1);
        this.zPoles = this.zDenom.rootsNoMessages();
    }

    public void setDeltaT(double var1) {
        if (this.deltaT == 0.0D) {
            this.deltaT = var1;
            this.sampFreq = 1.0D / this.deltaT;
            this.deadTimeWarning("setDeltaT");
        } else {
            String var3 = "BlackBox setDeltaT: Do you wish to replace the deltaT value, " + this.deltaT + " with " + var1;
            if (Db.yesNo(var3)) {
                this.deltaT = var1;
                this.sampFreq = 1.0D / this.deltaT;
                this.deadTimeWarning("setDeltaT");
                if (this.time != null) {
                    int var4 = this.sampLen;
                    this.sampLen = (int)Math.round(this.time[this.sampLen - 1] / this.deltaT);
                    double[] var5 = Conv.copy(this.time);
                    double[] var6 = Conv.copy(this.inputT);
                    this.time = new double[this.sampLen];
                    this.inputT = new double[this.sampLen];
                    CubicSpline var7 = new CubicSpline(var5, var6);
                    this.time[0] = var5[0];
                    this.inputT[0] = var6[0];

                    for(int var8 = 1; var8 < this.sampLen - 1; ++var8) {
                        this.time[var8] = this.time[var8 - 1] = this.deltaT;
                        this.inputT[var8] = var7.interpolate(this.time[var8]);
                    }

                    this.time[this.sampLen - 1] = var5[var4];
                    this.inputT[this.sampLen - 1] = var6[var4];
                }
            }
        }

    }

    public void setForgetFactor(double var1) {
        this.forgetFactor = var1;
    }

    public void setSampFreq(double var1) {
        this.sampFreq = var1;
        this.setDeltaT(1.0D / var1);
    }

    public void setS(Complex var1) {
        this.sValue = Complex.copy(var1);
    }

    public void setS(double var1, double var3) {
        this.sValue.reset(var1, var3);
    }

    public void setS(double var1) {
        this.sValue.reset(0.0D, var1);
    }

    public void setZ(Complex var1) {
        this.zValue = Complex.copy(var1);
    }

    public void setZ(double var1, double var3) {
        this.zValue.reset(var1, var3);
    }

    public void setZtransformMethod(int var1) {
        if (var1 >= 0 && var1 <= 1) {
            this.ztransMethod = var1;
        } else {
            System.out.println("z transform method option number " + var1 + " not recognised");
            System.out.println("z tr methodansform option number set in BlackBox to the default value of 0 (s -> z ad hoc mapping)");
            this.integMethod = 0;
        }

    }

    public void setIntegrateOption(int var1) {
        if (var1 >= 0 && var1 <= 2) {
            this.integMethod = var1;
        } else {
            System.out.println("integration method option number " + var1 + " not recognised");
            System.out.println("integration method option number set in BlackBox to the default value of 0 (trapezium rule)");
            this.integMethod = 0;
        }

    }

    public void setIntegrateOption(String var1) {
        if (!var1.equals("trapezium") && !var1.equals("Trapezium") && !var1.equals("tutin") && !var1.equals("Tutin")) {
            if (!var1.equals("backward") && !var1.equals("Backward") && !var1.equals("back") && !var1.equals("Back")) {
                if (!var1.equals("foreward") && !var1.equals("Foreward") && !var1.equals("fore") && !var1.equals("Fore")) {
                    System.out.println("integration method option  " + var1 + " not recognised");
                    System.out.println("integration method option number set in PID to the default value of 0 (trapezium rule)");
                    this.integMethod = 0;
                } else {
                    this.integMethod = 2;
                }
            } else {
                this.integMethod = 1;
            }
        } else {
            this.integMethod = 0;
        }

    }

    public void setSampleLength(int var1) {
        if (var1 == 0) {
            throw new IllegalArgumentException("Entered sample length must be greater than zero");
        } else {
            if (var1 == 1) {
                var1 = 2;
            }

            if (this.sampLen == 0) {
                this.sampLen = var1;
                this.time = new double[var1];
                this.inputT = new double[var1];
                this.outputT = new double[var1];
            } else {
                String var2 = "BlackBox setSampleLength: Do you wish to replace the sample length, " + this.sampLen + " with " + var1;
                if (Db.yesNo(var2)) {
                    int var3 = this.sampLen;
                    this.sampLen = var1;
                    if (this.time != null) {
                        this.deltaT = this.time[var3 - 1] / (double)(var1 - 1);
                        double[] var4 = Conv.copy(this.time);
                        double[] var5 = Conv.copy(this.inputT);
                        this.time = new double[this.sampLen];
                        this.inputT = new double[this.sampLen];
                        CubicSpline var6 = new CubicSpline(var4, var5);
                        this.time[0] = var4[0];
                        this.inputT[0] = var5[0];

                        for(int var7 = 1; var7 < this.sampLen - 1; ++var7) {
                            this.time[var7] = this.time[var7 - 1] = this.deltaT;
                            this.inputT[var7] = var6.interpolate(this.time[var7]);
                        }

                        this.time[this.sampLen - 1] = var4[var3];
                        this.inputT[this.sampLen - 1] = var5[var3];
                    }
                }
            }

        }
    }

    public void setName(String var1) {
        this.name = var1;
    }

    public void setInputT(double var1, double var3) {
        if (this.deltaT == 0.0D) {
            this.time = new double[2];
            this.time[0] = 0.0D;
            this.time[1] = var1;
            this.inputT = new double[2];
            this.inputT[0] = var3;
            this.inputT[1] = var3;
            this.outputT = new double[2];
            this.sampLen = 2;
        } else {
            double var5 = this.deltaT;
            this.sampLen = (int)Math.round(var1 / var5);
            this.deltaT = var1 / (double)this.sampLen;
            if (!Fmath.isEqualWithinLimits(this.deltaT, var5, var5 * 0.001D)) {
                System.out.println("BlackBox setInputT method; deltaT has been reset from " + var5 + " to " + this.deltaT);
            }

            this.sampFreq = 1.0D / this.deltaT;
            this.time = new double[this.sampLen];
            this.time[this.sampLen - 1] = var1;
            this.inputT = new double[this.sampLen];
            this.inputT[this.sampLen - 1] = var3;
            this.outputT = new double[this.sampLen];

            for(int var7 = this.sampLen - 2; var7 > 0; --var7) {
                this.time[var7] = this.time[var7 + 1] - this.deltaT;
                this.inputT[var7] = var3;
            }

            this.time[0] = 0.0D;
            this.inputT[0] = var3;
        }

    }

    public void setInputT(double[] var1, double[] var2) {
        int var3 = var1.length;
        if (var3 != var2.length) {
            throw new IllegalArgumentException("time and input arrays are of different lengths: " + var3 + ", " + var2.length);
        } else {
            if (var3 == 1) {
                this.setInputT(var1[0], var2[0]);
            } else {
                this.sampLen = var3;
                this.time = var1;
                this.inputT = var2;
                this.outputT = new double[this.sampLen];
                this.deltaT = var1[this.sampLen] / (double)(this.sampLen - 1);
                this.sampFreq = 1.0D / this.deltaT;
            }

        }
    }

    public void setInputS(Complex var1) {
        this.inputS = var1;
    }

    public void resetZero() {
        for(int var1 = 0; var1 < this.sampLen - 1; ++var1) {
            this.outputT[var1] = 0.0D;
            this.inputT[var1] = 0.0D;
            this.time[var1] = 0.0D;
        }

        this.outputS = Complex.zero();
        this.inputS = Complex.zero();
        this.deltaT = 0.0D;
        this.sampLen = 0;
    }

    protected void calcPolesZerosS() {
        this.sNumer = ComplexPoly.reducePoly(this.sNumer);
        this.sNumerDeg = this.sNumer.getDeg();
        this.sNumerPade = ComplexPoly.reducePoly(this.sNumerPade);
        this.sNumerDegPade = this.sNumerPade.getDeg();
        this.sDenom = ComplexPoly.reducePoly(this.sDenom);
        this.sDenomDeg = this.sDenom.getDeg();
        this.sDenomPade = ComplexPoly.reducePoly(this.sDenomPade);
        this.sDenomDegPade = this.sDenomPade.getDeg();
        if (this.sNumer != null) {
            if (this.sNumer.getDeg() > 0) {
                this.sZeros = this.sNumer.rootsNoMessages();
            }

            if (this.sZeros != null) {
                this.sNumerScaleFactor = scaleFactor(this.sNumer, this.sZeros);
            } else {
                this.sNumerScaleFactor = this.sNumer.coeffCopy(0);
            }
        }

        if (this.sDenom != null) {
            if (this.sDenom.getDeg() > 0) {
                this.sPoles = this.sDenom.rootsNoMessages();
            }

            if (this.sPoles != null) {
                this.sDenomScaleFactor = scaleFactor(this.sDenom, this.sPoles);
            } else {
                this.sDenomScaleFactor = this.sDenom.coeffCopy(0);
            }
        }

        if (this.sNumerPade != null && this.sNumerPade.getDeg() > 0) {
            this.sZerosPade = this.sNumerPade.rootsNoMessages();
        }

        if (this.sDenomPade != null && this.sDenomPade.getDeg() > 0) {
            this.sPolesPade = this.sDenomPade.rootsNoMessages();
        }

    }

    protected void zeroPoleCancellation() {
        boolean var1 = false;
        boolean var2 = true;
        boolean var3 = false;
        if (this.sNumerDegPade == 0 || this.sDenomDegPade == 0) {
            var2 = false;
        }

        if (this.sZerosPade == null || this.sPolesPade == null) {
            var2 = false;
        }

        int var4;
        int var5;
        int var6;
        int var7;
        int var8;
        if (var2) {
            var4 = this.sPolesPade.length;
            var5 = this.sZerosPade.length;

            for(var6 = var5 - 1; var6 >= 0; --var6) {
                var3 = false;

                for(var7 = var4 - 1; var7 >= 0; --var7) {
                    if (this.sZerosPade[var6].isEqual(this.sPolesPade[var7])) {
                        if (var7 < this.sDenomDegPade - 2) {
                            for(var8 = var7; var8 < this.sDenomDegPade; ++var8) {
                                this.sPolesPade[var8] = this.sPolesPade[var8 + 1].copy();
                            }
                        }

                        --this.sDenomDegPade;
                        if (var6 < this.sNumerDegPade - 2) {
                            for(var8 = var6; var8 < this.sNumerDegPade; ++var8) {
                                this.sZerosPade[var8] = this.sZerosPade[var8 + 1].copy();
                            }
                        }

                        --this.sNumerDegPade;
                        --var6;
                        var1 = true;
                        var3 = true;
                    }

                    if (var3) {
                        break;
                    }
                }
            }
        }

        Complex[] var9;
        if (var1) {
            if (this.sNumerDegPade == 0) {
                this.sNumerPade = new ComplexPoly(1.0D);
                this.sZerosPade = null;
            } else {
                var9 = Complex.oneDarray(this.sNumerDegPade);

                for(var5 = 0; var5 < this.sNumerDegPade; ++var5) {
                    var9[var5] = this.sZerosPade[var5].copy();
                }

                this.sZerosPade = var9;
                this.sNumerPade = ComplexPoly.rootsToPoly(this.sZerosPade);
            }

            if (this.sDenomDegPade == 0) {
                this.sDenomPade = new ComplexPoly(1.0D);
                this.sPolesPade = null;
            } else {
                var9 = Complex.oneDarray(this.sDenomDegPade);

                for(var5 = 0; var5 < this.sDenomDegPade; ++var5) {
                    var9[var5] = this.sPolesPade[var5].copy();
                }

                this.sPolesPade = var9;
                this.sDenomPade = ComplexPoly.rootsToPoly(this.sPolesPade);
            }
        }

        var1 = false;
        var2 = true;
        var3 = true;
        if (this.sNumerDeg == 0 || this.sDenomDeg == 0) {
            var2 = false;
        }

        if (this.sZeros == null || this.sPoles == null) {
            var2 = false;
        }

        if (var2) {
            var4 = this.sPoles.length;
            var5 = this.sZeros.length;

            for(var6 = var5 - 1; var6 >= 0; --var6) {
                var3 = false;

                for(var7 = var4 - 1; var7 >= 0; --var7) {
                    if (this.sZeros[var6].isEqual(this.sPoles[var7])) {
                        if (var7 < this.sDenomDegPade - 2) {
                            for(var8 = var7 + 1; var8 < this.sDenomDeg; ++var8) {
                                this.sPoles[var8 - 1] = this.sPoles[var8].copy();
                            }
                        }

                        --this.sDenomDeg;
                        if (var7 < this.sNumerDegPade - 2) {
                            for(var8 = var6 + 1; var8 < this.sNumerDeg; ++var8) {
                                this.sZeros[var8 - 1] = this.sZeros[var8].copy();
                            }
                        }

                        --this.sNumerDeg;
                        var1 = true;
                        var3 = true;
                        --var6;
                    }

                    if (var3) {
                        break;
                    }
                }
            }
        }

        if (var1) {
            if (this.sNumerDeg == 0) {
                this.sNumer = new ComplexPoly(1.0D);
                this.sZeros = null;
            } else {
                var9 = Complex.oneDarray(this.sNumerDeg);

                for(var5 = 0; var5 < this.sNumerDeg; ++var5) {
                    var9[var5] = this.sZeros[var5].copy();
                }

                this.sZeros = var9;
                this.sNumer = ComplexPoly.rootsToPoly(this.sZeros);
            }

            if (this.sDenomDeg == 0) {
                this.sDenom = new ComplexPoly(1.0D);
                this.sPoles = null;
            } else {
                var9 = Complex.oneDarray(this.sDenomDeg);

                for(var5 = 0; var5 < this.sDenomDeg; ++var5) {
                    var9[var5] = this.sPoles[var5].copy();
                }

                this.sPoles = var9;
                this.sDenom = ComplexPoly.rootsToPoly(this.sPoles);
            }
        }

    }

    public double getSeadyStateValue() {
        Complex var1 = this.sNumer.evaluate(Complex.zero());
        Complex var2 = this.sDenom.evaluate(Complex.zero());
        Complex var3 = var1.over(var2);
        double var4 = var3.getReal();
        double var6 = var3.getImag();
        if (Math.abs(var6) > Math.abs(var4) * 0.01D) {
            System.out.println("method getSteadyStateValue: The imaginary part, " + var6 + ", is greater than 1 per cent of the the real part, " + var4);
            System.out.println("Magnitude has  been returned");
        }

        return var3.abs();
    }

    public double getSeadyStateValue(double var1) {
        Complex var3 = this.sNumer.evaluate(Complex.zero());
        Complex var4 = this.sDenom.evaluate(Complex.zero());
        Complex var5 = var3.over(var4);
        double var6 = var5.getReal();
        double var8 = var5.getImag();
        if (Math.abs(var8) > Math.abs(var6) * 0.01D) {
            System.out.println("method getSteadyStateValue: The imaginary part, " + var8 + ", is greater than 1 per cent of the the real part, " + var6);
            System.out.println("Magnitude has  been returned");
        }

        return var1 * var5.abs();
    }

    public Complex evalTransFunctS() {
        if (!this.padeAdded) {
            this.transferPolesZeros();
        }

        Complex var1 = this.sNumer.evaluate(this.sValue);
        Complex var2 = this.sDenom.evaluate(this.sValue);
        Complex var3 = Complex.plusOne();
        if (this.deadTime != 0.0D) {
            var3 = Complex.exp(this.sValue.times(-this.deadTime));
        }

        return var1.over(var2).times(var3);
    }

    public Complex evalTransFunctS(Complex var1) {
        if (!this.padeAdded) {
            this.transferPolesZeros();
        }

        this.sValue = Complex.copy(var1);
        Complex var2 = this.sNumer.evaluate(var1);
        Complex var3 = this.sDenom.evaluate(var1);
        Complex var4 = Complex.plusOne();
        if (this.deadTime != 0.0D) {
            var4 = Complex.exp(this.sValue.times(-this.deadTime));
        }

        return var2.over(var3).times(var4);
    }

    public Complex evalTransFunctS(double var1) {
        if (!this.padeAdded) {
            this.transferPolesZeros();
        }

        this.sValue.reset(0.0D, 6.283185307179586D * var1);
        Complex var3 = this.sNumer.evaluate(this.sValue);
        Complex var4 = this.sDenom.evaluate(this.sValue);
        Complex var5 = Complex.plusOne();
        if (this.deadTime != 0.0D) {
            var5 = Complex.exp(this.sValue.times(-this.deadTime));
        }

        return var3.over(var4).times(var5);
    }

    public double evalMagTransFunctS() {
        if (!this.padeAdded) {
            this.transferPolesZeros();
        }

        Complex var1 = this.sNumer.evaluate(this.sValue);
        Complex var2 = this.sDenom.evaluate(this.sValue);
        Complex var3 = Complex.plusOne();
        if (this.deadTime != 0.0D) {
            var3 = Complex.exp(this.sValue.times(-this.deadTime));
        }

        return var1.over(var2).times(var3).abs();
    }

    public double evalMagTransFunctS(Complex var1) {
        if (!this.padeAdded) {
            this.transferPolesZeros();
        }

        this.sValue = Complex.copy(var1);
        Complex var2 = this.sNumer.evaluate(var1);
        Complex var3 = this.sDenom.evaluate(var1);
        Complex var4 = Complex.plusOne();
        if (this.deadTime != 0.0D) {
            var4 = Complex.exp(this.sValue.times(-this.deadTime));
        }

        return var2.over(var3).times(var4).abs();
    }

    public double evalMagTransFunctS(double var1) {
        if (!this.padeAdded) {
            this.transferPolesZeros();
        }

        this.sValue.reset(0.0D, 6.283185307179586D * var1);
        Complex var3 = this.sNumer.evaluate(this.sValue);
        Complex var4 = this.sDenom.evaluate(this.sValue);
        Complex var5 = Complex.plusOne();
        if (this.deadTime != 0.0D) {
            var5 = Complex.exp(this.sValue.times(-this.deadTime));
        }

        return var3.over(var4).times(var5).abs();
    }

    public double evalPhaseTransFunctS() {
        if (!this.padeAdded) {
            this.transferPolesZeros();
        }

        Complex var1 = this.sNumer.evaluate(this.sValue);
        Complex var2 = this.sDenom.evaluate(this.sValue);
        Complex var3 = Complex.plusOne();
        if (this.deadTime != 0.0D) {
            var3 = Complex.exp(this.sValue.times(-this.deadTime));
        }

        return var1.over(var2).times(var3).arg();
    }

    public double evalPhaseTransFunctS(Complex var1) {
        if (!this.padeAdded) {
            this.transferPolesZeros();
        }

        this.sValue = Complex.copy(var1);
        Complex var2 = this.sNumer.evaluate(var1);
        Complex var3 = this.sDenom.evaluate(var1);
        Complex var4 = Complex.plusOne();
        if (this.deadTime != 0.0D) {
            var4 = Complex.exp(this.sValue.times(-this.deadTime));
        }

        return var2.over(var3).times(var4).arg();
    }

    public double evalPhaseTransFunctS(double var1) {
        if (!this.padeAdded) {
            this.transferPolesZeros();
        }

        this.sValue.reset(0.0D, 6.283185307179586D * var1);
        Complex var3 = this.sNumer.evaluate(this.sValue);
        Complex var4 = this.sDenom.evaluate(this.sValue);
        Complex var5 = Complex.plusOne();
        if (this.deadTime != 0.0D) {
            var5 = Complex.exp(this.sValue.times(-this.deadTime));
        }

        return var3.over(var4).times(var5).arg();
    }

    public Complex evalTransFunctZ() {
        Complex var1 = this.zNumer.evaluate(this.zValue);
        Complex var2 = this.zDenom.evaluate(this.zValue);
        return var1.over(var2);
    }

    public Complex evalTransFunctZ(Complex var1) {
        this.zValue = Complex.copy(var1);
        Complex var2 = this.zNumer.evaluate(var1);
        Complex var3 = this.zDenom.evaluate(var1);
        return var2.over(var3);
    }

    public double evalMagTransFunctZ() {
        Complex var1 = this.zNumer.evaluate(this.zValue);
        Complex var2 = this.zDenom.evaluate(this.zValue);
        return var1.over(var2).abs();
    }

    public double evalMagTransFunctZ(Complex var1) {
        this.zValue = Complex.copy(var1);
        Complex var2 = this.zNumer.evaluate(var1);
        Complex var3 = this.zDenom.evaluate(var1);
        return var2.over(var3).abs();
    }

    public double evalPhaseTransFunctZ() {
        Complex var1 = this.zNumer.evaluate(this.zValue);
        Complex var2 = this.zDenom.evaluate(this.zValue);
        return var1.over(var2).arg();
    }

    public double evalPhaseTransFunctZ(Complex var1) {
        this.zValue = Complex.copy(var1);
        Complex var2 = this.zNumer.evaluate(var1);
        Complex var3 = this.zDenom.evaluate(var1);
        return var2.over(var3).arg();
    }

    public int getIntegMethod() {
        return this.integMethod;
    }

    public int getZtransformMethod() {
        return this.ztransMethod;
    }

    public int getSampleLength() {
        return this.sampLen;
    }

    public double getForgetFactor() {
        return this.forgetFactor;
    }

    public double getCurrentTime() {
        return this.time[this.sampLen - 1];
    }

    public double[] getTime() {
        return this.time;
    }

    public double getCurrentInputT() {
        return this.inputT[this.sampLen - 1];
    }

    public double[] getInputT() {
        return this.inputT;
    }

    public Complex getInputS() {
        return this.inputS;
    }

    public double getDeltaT() {
        return this.deltaT;
    }

    public double getSampFreq() {
        return this.sampFreq;
    }

    public Complex getS() {
        return this.sValue;
    }

    public Complex getZ() {
        return this.zValue;
    }

    public int getSnumerDeg() {
        return this.sNumerDeg;
    }

    public int getSnumerPadeDeg() {
        return this.sNumerDegPade;
    }

    public int getSdenomDeg() {
        return this.sDenomDeg;
    }

    public int getSdenomPadeDeg() {
        return this.sDenomDegPade;
    }

    public ComplexPoly getSnumer() {
        return this.sNumer.times(this.sNumerWorkingFactor);
    }

    public ComplexPoly getSnumerPade() {
        return this.sNumerPade.times(this.sNumerWorkingFactor);
    }

    public ComplexPoly getSdenom() {
        return this.sDenom.times(this.sDenomWorkingFactor);
    }

    public ComplexPoly getSdenomPade() {
        return this.sDenomPade.times(this.sDenomWorkingFactor);
    }

    public int getZnumerDeg() {
        return this.zNumerDeg;
    }

    public int getZdenomDeg() {
        return this.zDenomDeg;
    }

    public ComplexPoly getZnumer() {
        return this.zNumer;
    }

    public ComplexPoly getZdenom() {
        return this.zDenom;
    }

    public Complex[] getZerosS() {
        if (this.sZeros == null) {
            this.calcPolesZerosS();
        }

        if (this.sZeros == null) {
            System.out.println("Method BlackBox.getZerosS:");
            System.out.println("There are either no s-domain zeros for this transfer function");
            System.out.println("or the s-domain numerator polynomial has not been set");
            System.out.println("null returned");
            return null;
        } else {
            return this.sZeros;
        }
    }

    public Complex[] getZerosPadeS() {
        if (this.sZeros == null) {
            this.calcPolesZerosS();
        }

        if (!this.padeAdded) {
            this.transferPolesZeros();
        }

        if (this.sZerosPade == null) {
            System.out.println("Method BlackBox.getZerosPadeS:");
            System.out.println("There are either no s-domain zeros for this transfer function");
            System.out.println("or the s-domain numerator polynomial has not been set");
            System.out.println("null returned");
            return null;
        } else {
            return this.sZerosPade;
        }
    }

    public Complex[] getPolesS() {
        if (this.sPoles == null) {
            this.calcPolesZerosS();
        }

        if (this.sPoles == null) {
            System.out.println("Method BlackBox.getPolesS:");
            System.out.println("There are either no s-domain poles for this transfer function");
            System.out.println("or the s-domain denominator polynomial has not been set");
            System.out.println("null returned");
            return null;
        } else {
            return this.sPoles;
        }
    }

    public Complex[] getPolesPadeS() {
        if (this.sPoles == null) {
            this.calcPolesZerosS();
        }

        if (!this.padeAdded) {
            this.transferPolesZeros();
        }

        if (this.sPolesPade == null) {
            System.out.println("Method BlackBox.getPolesPadeS:");
            System.out.println("There are either no s-domain poles for this transfer function");
            System.out.println("or the s-domain denominator polynomial has not been set");
            System.out.println("null returned");
            return null;
        } else {
            return this.sPolesPade;
        }
    }

    public Complex[] getZerosZ() {
        if (this.zZeros == null) {
            System.out.println("Method BlackBox.getZerosZ:");
            System.out.println("There are either no z-domain zeros for this transfer function");
            System.out.println("or the z-domain numerator polynomial has not been set");
            System.out.println("null returned");
            return null;
        } else {
            return this.zZeros;
        }
    }

    public Complex[] getPolesZ() {
        if (this.zPoles == null) {
            System.out.println("Method BlackBox.getPolesZ:");
            System.out.println("There are either no z-domain poles for this transfer function");
            System.out.println("or the z-domain denominator polynomial has not been set");
            System.out.println("null returned");
            return null;
        } else {
            return this.zPoles;
        }
    }

    public boolean getMaptozero() {
        return this.maptozero;
    }

    public String getName() {
        return this.name;
    }

    public void plotPoleZeroS() {
        if (this.sNumer == null) {
            throw new IllegalArgumentException("s domain numerator has not been set");
        } else if (this.sDenom == null) {
            throw new IllegalArgumentException("s domain denominator has not been set");
        } else {
            PlotPoleZero var1 = new PlotPoleZero(this.sNumer, this.sDenom);
            var1.setS();
            var1.pzPlot(this.name);
        }
    }

    public void plotPoleZeroPadeS() {
        if (!this.padeAdded) {
            this.transferPolesZeros();
        }

        if (this.sNumerPade == null) {
            throw new IllegalArgumentException("s domain numerator has not been set");
        } else if (this.sDenomPade == null) {
            throw new IllegalArgumentException("s domain denominator has not been set");
        } else {
            PlotPoleZero var1 = new PlotPoleZero(this.sNumerPade, this.sDenomPade);
            var1.setS();
            var1.pzPlot(this.name);
        }
    }

    public void plotPoleZeroZ() {
        PlotPoleZero var1 = new PlotPoleZero(this.zNumer, this.zDenom);
        if (this.zNumer == null) {
            throw new IllegalArgumentException("z domain numerator has not been set");
        } else if (this.zDenom == null) {
            throw new IllegalArgumentException("z domain denominator has not been set");
        } else {
            var1.setZ();
            var1.pzPlot(this.name);
        }
    }

    public void plotBode(double var1, double var3) {
        if (!this.padeAdded) {
            this.transferPolesZeros();
        }

        byte var5 = 100;
        double[][] var6 = new double[2][var5];
        double[] var7 = new double[var5 + 1];
        double var8 = Fmath.log10(6.283185307179586D * var1);
        double var10 = Fmath.log10(6.283185307179586D * var3);
        double var12 = (var10 - var8) / ((double)var5 - 1.0D);
        var7[0] = var8;

        double var14;
        for(int var16 = 0; var16 < var5; ++var16) {
            var14 = Math.pow(10.0D, var7[var16]);
            var6[0][var16] = var7[var16];
            var6[1][var16] = 20.0D * Fmath.log10(this.evalMagTransFunctS(var14 / 6.283185307179586D));
            var7[var16 + 1] = var7[var16] + var12;
        }

        PlotGraph var18 = new PlotGraph(var6);
        var18.setGraphTitle("Bode Plot = magnitude versus log10[radial frequency]");
        var18.setGraphTitle2(this.name);
        var18.setXaxisLegend("Log10[radial frequency]");
        var18.setYaxisLegend("Magnitude[Transfer Function]");
        var18.setYaxisUnitsName("dB");
        var18.setPoint(0);
        var18.setLine(3);
        var18.setFrame();

        for(int var17 = 0; var17 < var5; ++var17) {
            var14 = Math.pow(10.0D, var7[var17]);
            var6[0][var17] = var7[var17];
            var6[1][var17] = this.evalPhaseTransFunctS(var14) * 180.0D / 3.141592653589793D;
        }

        PlotGraph var19 = new PlotGraph(var6);
        var19.setGraphTitle("Bode Plot = phase versus log10[radial frequency]");
        var19.setGraphTitle2(this.name);
        var19.setXaxisLegend("Log10[radial frequency]");
        var19.setYaxisLegend("Phase[Transfer Function]");
        var19.setYaxisUnitsName("degrees");
        var19.setPoint(0);
        var18.setLine(3);
        var19.setFrame();
    }

    public double getCurrentOutputT(double var1, double var3) {
        this.setInputT(var1, var3);
        return this.getCurrentOutputT();
    }

    public double getCurrentOutputT() {
        if (!this.padeAdded) {
            this.transferPolesZeros();
        }

        ComplexPoly var1 = this.sNumerPade.times(new Complex(this.inputT[this.sampLen - 1], 0.0D));
        Complex[] var2 = new Complex[]{Complex.zero(), Complex.plusOne()};
        ComplexPoly var3 = new ComplexPoly(var2);
        ComplexPoly var4 = this.sDenomPade.times(var3);
        Complex[][] var5 = inverseTransform(var1, var4, this.sNumerWorkingFactor, this.sDenomScaleFactor);
        Complex var6 = Complex.zero();

        for(int var7 = 0; var7 < var5[0].length; ++var7) {
            var6.plusEquals(timeTerm(this.time[this.sampLen - 1], var5[0][var7], var5[1][var7], var5[2][var7]));
        }

        double var14 = var6.getReal();
        double var9 = var6.getImag();
        boolean var13 = true;
        if (var9 == 0.0D) {
            var13 = false;
        }

        if (var13) {
            double var11 = Math.max(Math.abs(var14), Math.abs(var9));
            if (Math.abs((var14 - var9) / var11) > 1.0E-5D) {
                var13 = false;
            } else {
                System.out.println("output in Blackbox.getCurrentOutputT() has a significant imaginary part");
                System.out.println("time = " + this.time[this.sampLen - 1] + "    real = " + var14 + "   imag = " + var9);
                System.out.println("Output equated to the real part");
            }
        }

        this.outputT[this.sampLen - 1] = var14;
        return this.outputT[this.sampLen - 1];
    }

    public double[] getOutputT() {
        return this.outputT;
    }

    public Complex getOutputS() {
        if (!this.padeAdded) {
            this.transferPolesZeros();
        }

        Complex var1 = this.sNumer.evaluate(this.sValue);
        Complex var2 = this.sDenom.evaluate(this.sValue);
        this.outputS = var1.over(var2).times(this.inputS);
        if (this.deadTime != 0.0D) {
            this.outputS = this.outputS.times(Complex.exp(this.sValue.times(-this.deadTime)));
        }

        return this.outputS;
    }

    public Complex getOutputS(Complex var1, Complex var2) {
        if (!this.padeAdded) {
            this.transferPolesZeros();
        }

        this.inputS = var2;
        this.sValue = var1;
        Complex var3 = this.sNumer.evaluate(this.sValue);
        Complex var4 = this.sDenom.evaluate(this.sValue);
        this.outputS = var3.over(var4).times(this.inputS);
        if (this.deadTime != 0.0D) {
            this.outputS = this.outputS.times(Complex.exp(this.sValue.times(-this.deadTime)));
        }

        return this.outputS;
    }

    public void setNplotPoints(int var1) {
        this.nPlotPoints = var1;
    }

    public int getNplotPoints() {
        return this.nPlotPoints;
    }

    public void impulseInput(double var1, double var3) {
        if (!this.padeAdded) {
            this.transferPolesZeros();
        }

        ComplexPoly var5 = new ComplexPoly(0);
        var5.resetCoeff(0, Complex.plusOne().times(var1));
        ComplexPoly var6 = this.sNumerPade.times(var5);
        ComplexPoly var7 = this.sDenomPade.copy();
        String var8 = "Impulse Input Transient:   Impulse magnitude = " + var1;
        String var9 = this.getName();
        transientResponse(this.nPlotPoints, var3, this.deadTime, var6, var7, var8, var9, this.sNumerWorkingFactor, this.sDenomScaleFactor);
    }

    public void impulseInput(double var1) {
        this.impulseInput(1.0D, var1);
    }

    public void stepInput(double var1, double var3) {
        Complex var5 = this.sNumerPade.coeffCopy(0);
        Complex var6 = this.sDenomPade.coeffCopy(0);
        boolean var7 = false;
        if (Complex.isReal(var5) && Complex.isReal(var6)) {
            var7 = true;
        }

        if (this.sNumerDeg == 0 && this.sDenomDeg == 0 && var7) {
            byte var15 = 51;
            double var16 = var3 / (double)(var15 - 2);
            double[][] var17 = new double[2][var15];
            var17[0][0] = 0.0D;
            var17[0][1] = 0.0D;

            for(int var18 = 2; var18 < var15; ++var18) {
                var17[0][var18] = var17[0][var18 - 1] + var16;
            }

            double var19 = var5.getReal() * var1 / var6.getReal();
            var17[1][0] = 0.0D;

            int var14;
            for(var14 = 1; var14 < var15; ++var14) {
                var17[1][var14] = var19;
            }

            if (this.deadTime != 0.0D) {
                for(var14 = 0; var14 < var15; ++var14) {
                    var17[0][var14] += this.deadTime;
                }
            }

            PlotGraph var20 = new PlotGraph(var17);
            var20.setGraphTitle("Step Input Transient:   Step magnitude = " + var1);
            var20.setGraphTitle2(this.getName());
            var20.setXaxisLegend("Time");
            var20.setXaxisUnitsName("s");
            var20.setYaxisLegend("Output");
            var20.setPoint(0);
            var20.setLine(3);
            var20.setFrame();
        } else {
            if (!this.padeAdded) {
                this.transferPolesZeros();
            }

            ComplexPoly var8 = this.sNumer.times(var1);
            Complex[] var9 = new Complex[]{Complex.zero(), Complex.plusOne()};
            ComplexPoly var10 = new ComplexPoly(var9);
            ComplexPoly var11 = this.sDenom.times(var10);
            String var12 = "Step Input Transient:   Step magnitude = " + var1;
            String var13 = this.getName();
            transientResponse(this.nPlotPoints, var3, this.deadTime, var8, var11, var12, var13, this.sNumerWorkingFactor, this.sDenomScaleFactor);
        }

    }

    public void stepInput(double var1) {
        this.stepInput(1.0D, var1);
    }

    public void rampInput(double var1, int var3, double var4) {
        if (!this.padeAdded) {
            this.transferPolesZeros();
        }

        ComplexPoly var6 = this.sNumer.times(var1 * (double)Fmath.factorial(var3));
        Complex[] var7 = Complex.oneDarray(var3 + 1);

        for(int var8 = 0; var8 < var3; ++var8) {
            var7[var8] = Complex.zero();
        }

        var7[var3] = Complex.plusOne();
        ComplexPoly var12 = new ComplexPoly(var7);
        ComplexPoly var9 = this.sDenom.times(var12);
        String var10 = "";
        if (var1 != 1.0D) {
            if (var3 != 1) {
                var10 = var10 + "nth order ramp (at^n) input transient:   a = " + var1 + "    n = " + var3;
            } else {
                var10 = var10 + "First order ramp (at) input transient:   a = " + var1;
            }
        } else if (var3 != 1) {
            var10 = var10 + "Unit ramp (t) input transient";
        } else {
            var10 = var10 + "nth order ramp (t^n) input transient:   n = " + var3;
        }

        String var11 = this.getName();
        transientResponse(this.nPlotPoints, var4, this.deadTime, var6, var9, var10, var11, this.sNumerWorkingFactor, this.sDenomScaleFactor);
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

    public static void transientResponse(int var0, double var1, double var3, ComplexPoly var5, ComplexPoly var6, String var7, String var8) {
        Complex[] var9 = var6.rootsNoMessages();
        Complex var10 = scaleFactor(var6, var9);
        Complex var11 = Complex.plusOne();
        transientResponse(var0, var1, var3, var5, var6, var7, var8, var11, var10);
    }

    public static void transientResponse(int var0, double var1, double var3, ComplexPoly var5, ComplexPoly var6, String var7, String var8, Complex var9, Complex var10) {
        Complex[][] var11 = inverseTransform(var5, var6, var9, var10);
        int var12 = var6.getDeg();
        double var13 = var1 / (double)(var0 - 1);
        double[][] var15 = new double[2][var0];
        double var16 = 0.0D;
        new Complex();
        double var19 = 0.0D;
        double var21 = 0.0D;
        boolean var23 = true;
        var15[0][0] = 0.0D;

        int var24;
        for(var24 = 1; var24 < var0; ++var24) {
            var15[0][var24] = var15[0][var24 - 1] + var13;
        }

        for(var24 = 0; var24 < var0; ++var24) {
            var23 = true;
            Complex var18 = Complex.zero();

            for(int var25 = 0; var25 < var12; ++var25) {
                var18.plusEquals(timeTerm(var15[0][var24], var11[0][var25], var11[1][var25], var11[2][var25]));
            }

            var19 = var18.getReal();
            var21 = var18.getImag();
            if (var21 == 0.0D) {
                var23 = false;
            }

            if (var23) {
                var16 = Math.max(Math.abs(var19), Math.abs(var21));
                if (Math.abs((var19 - var21) / var16) > 1.0E-5D) {
                    var23 = false;
                } else {
                    System.out.println("output in Blackbox.stepInput has a significant imaginary part");
                    System.out.println("time = " + var15[0][var24] + "    real = " + var19 + "   imag = " + var21);
                    System.out.println("Output equated to the real part");
                }
            }

            var15[1][var24] = var19;
            var15[0][var24] += var3;
        }

        PlotGraph var26 = new PlotGraph(var15);
        var26.setGraphTitle(var7);
        var26.setGraphTitle2(var8);
        var26.setXaxisLegend("Time");
        var26.setXaxisUnitsName("s");
        var26.setYaxisLegend("Output");
        var26.setPoint(0);
        var26.setLine(3);
        var26.setNoYoffset(true);
        if (var3 < var15[0][var0 - 1] - var15[0][0]) {
            var26.setNoXoffset(true);
        }

        var26.setXlowFac(0.0D);
        var26.setYlowFac(0.0D);
        var26.setFrame();
    }

    public static Complex timeTerm(double var0, Complex var2, Complex var3, Complex var4) {
        new Complex();
        int var6 = (int)var4.getReal() - 1;
        Complex var5 = var2.times(Math.pow(var0, (double)var6));
        var5 = var5.over((double)Fmath.factorial(var6));
        var5 = var5.times(Complex.exp(var3.times(var0)));
        return var5;
    }

    public static double timeTerm(double var0, double var2, double var4, int var6) {
        int var7 = var6 - 1;
        double var8 = var2 * Math.pow(var0, (double)var7);
        var8 /= (double)Fmath.factorial(var7);
        var8 *= Math.exp(var4 * var0);
        return var8;
    }

    public static double timeTerm(double var0, double var2, double var4, double var6) {
        double var8 = var6 - 1.0D;
        double var10 = var2 * Math.pow(var0, var8);
        var10 /= Fmath.factorial(var8);
        var10 *= Math.exp(var4 * var0);
        return var10;
    }

    public static double[][] inverseTransformToReal(ComplexPoly var0, ComplexPoly var1) {
        Complex[][] var2 = inverseTransform(var0, var1);
        int var3 = var2[0].length;
        double[][] var4 = new double[3][var3];

        for(int var5 = 0; var5 < var3; ++var5) {
            var4[0][var5] = var2[0][var5].getReal();
            if (Math.abs((var4[0][var5] - var2[0][var5].getImag()) / var4[0][var5]) > 1.0E-5D) {
                System.out.println("BlackBox inverseTransformToReal coefficient A[" + var5 + "] has a significant imaginary part: " + var2[0][var5]);
                System.out.println("A equated to the real part");
                System.out.println("inverseTransform method may be more appropriate");
            }

            var4[1][var5] = var2[1][var5].getReal();
            if (Math.abs((var4[1][var5] - var2[1][var5].getImag()) / var4[1][var5]) > 1.0E-5D) {
                System.out.println("BlackBox inverseTransformToReal coefficient a[" + var5 + "] has a significant imaginary part: " + var2[1][var5]);
                System.out.println("a equated to the real part");
                System.out.println("inverseTransform method may be more appropriate");
            }

            var4[2][var5] = var2[2][var5].getReal();
        }

        return var4;
    }

    public static Complex[][] inverseTransform(ComplexPoly var0, ComplexPoly var1) {
        Complex[] var2 = var1.rootsNoMessages();
        Complex var3 = scaleFactor(var1, var2);
        Complex var4 = Complex.plusOne();
        return inverseTransform(var0, var1, var4, var3);
    }

    public static Complex[][] inverseTransform(ComplexPoly var0, ComplexPoly var1, Complex var2, Complex var3) {
        int var4 = var1.getDeg();
        int var5 = var0.getDeg();
        if (var5 >= var4) {
            throw new IllegalArgumentException("The degree of the numerator is equal to or greater than the degree of the denominator");
        } else {
            Complex[][] var6 = Complex.twoDarray(3, var4);
            if (var4 == 1 && var5 == 0) {
                Complex var39 = var0.coeffCopy(0);
                Complex var40 = var1.coeffCopy(0);
                Complex var41 = var1.coeffCopy(1);
                var6[0][0] = var39.over(var41);
                var6[1][0] = Complex.minusOne().times(var40.over(var41));
                var6[2][0] = new Complex(1.0D, 0.0D);
                return var6;
            } else {
                int var7 = var4;
                int var8 = 0;
                Complex[] var9 = var1.rootsNoMessages();
                int[] var10 = new int[var4];
                boolean[] var11 = new boolean[var4];
                int[] var12 = new int[var4];
                int[] var13 = new int[var4];
                boolean[] var14 = new boolean[var4];
                double var15 = 0.01D;
                int[] var17 = new int[var4];
                int var18 = 0;
                Complex var19 = new Complex();
                int var20 = 0;

                int var21;
                for(var21 = 0; var21 < var4; ++var21) {
                    var11[var21] = false;
                }

                for(var21 = 0; var21 < var4; ++var21) {
                    var14[var21] = true;
                }

                for(var21 = 0; var21 < var4; ++var21) {
                    int var22;
                    if (!var11[var21]) {
                        var18 = 1;
                        var10[var21] = 1;
                        var13[var21] = 1;
                        var12[var21] = var21;
                        var17[var21] = 1;
                        var19 = var9[var21];

                        for(var22 = var21 + 1; var22 < var4; ++var22) {
                            if (!var11[var22]) {
                                if (var9[var21].isEqualWithinLimits(var9[var22], var15)) {
                                    var12[var22] = var21;
                                    ++var18;
                                    var10[var22] = var18;
                                    var11[var22] = true;
                                    var11[var21] = true;
                                    var14[var22] = false;
                                    var14[var21] = false;
                                    var20 = var22;
                                    --var7;
                                    var19 = var19.plus(var9[var22]);
                                } else {
                                    var12[var22] = var22;
                                    var10[var22] = 1;
                                }
                            }
                        }
                    }

                    if (var11[var21]) {
                        --var7;
                        ++var8;
                        var14[var20] = true;
                        var19 = var19.over((double)var18);

                        for(var22 = 0; var22 < var4; ++var22) {
                            if (var11[var22] && var12[var22] == var21) {
                                var9[var22] = var19;
                                var13[var21] = var18;
                                var17[var22] = var18;
                            }
                        }
                    }
                }

                Complex var42 = Complex.zero();
                Complex var43 = Complex.zero();

                for(int var23 = 0; var23 < var4; ++var23) {
                    var42 = var42.plus(var9[var23]);
                    var43 = var43.plus(var9[var23].abs());
                }

                var42 = var42.over((double)var4);
                var43 = var43.over((double)var4);
                Complex var44 = var42;
                if (var42.isZero()) {
                    var44 = var43;
                }

                if (var44.isZero()) {
                    var44 = Complex.plusOne();
                }

                Complex[] var24 = Complex.oneDarray(var4);
                boolean[] var25 = new boolean[var4];

                for(int var26 = 0; var26 < var4; ++var26) {
                    var25[var26] = false;
                }

                Complex[] var45 = null;
                Complex var27 = new Complex(1.7D, 0.0D);

                int var28;
                for(var28 = 0; var28 < var4; ++var28) {
                    var24[var28] = var9[var28].copy();
                }

                boolean var46 = false;
                int var30;
                int var31;
                int var32;
                if (var8 > 0) {
                    for(int var29 = 0; var29 < var4; ++var29) {
                        if (var17[var29] > 1 && !var25[var29]) {
                            var28 = var17[var29];
                            var45 = Complex.oneDarray(var17[var29]);
                            var30 = var17[var29] / 2;
                            if (Fmath.isEven(var17[var29])) {
                                for(var31 = 0; var31 < var30; ++var31) {
                                    var45[var30 + var31] = var27.times((double)(var31 + 1));
                                    var45[var30 - 1 - var31] = var45[var30 + var31].times(-1.0D);
                                }
                            } else {
                                var45[var30] = Complex.zero();

                                for(var31 = 0; var31 < var30; ++var31) {
                                    var45[var30 + 1 + var31] = var27.times((double)(var31 + 1));
                                    var45[var30 - 1 - var31] = var45[var30 + var31].times(-1.0D);
                                }
                            }

                            var31 = 0;

                            for(var32 = 0; var32 < var4; ++var32) {
                                if (!var25[var32] && var17[var32] == var28) {
                                    Complex var33 = var9[var32];
                                    if (var33.isZero()) {
                                        var33 = var44;
                                    }

                                    var24[var32] = var45[var31].times(var33);
                                    var25[var32] = true;
                                    ++var31;
                                }
                            }
                        }
                    }
                }

                boolean var47 = true;
                var30 = 0;
                var31 = 0;

                while(var47) {
                    var32 = var30 + 1;
                    boolean var49 = true;

                    while(var49) {
                        if (var24[var30].isEqualWithinLimits(var24[var32], var15)) {
                            var24[var30] = var24[var30].plus(var44.times((double)var31));
                            ++var31;
                            var30 = 0;
                            var49 = false;
                            if (var31 > 1000000) {
                                throw new IllegalArgumentException("a non repeating set of substitution values could not be foumd");
                            }
                        } else {
                            ++var32;
                        }

                        if (var32 >= var4) {
                            var49 = false;
                        }
                    }

                    ++var30;
                    if (var30 >= var4 - 1) {
                        var47 = false;
                    }
                }

                Complex[][] var48 = Complex.twoDarray(var4, var4);
                Complex[] var50 = Complex.oneDarray(var4);

                int var34;
                for(var34 = 0; var34 < var4; ++var34) {
                    if (var5 > 0) {
                        var50[var34] = var0.evaluate(var24[var34]);
                    } else {
                        var50[var34] = var0.coeffCopy(0);
                    }
                }

                for(var34 = 0; var34 < var4; ++var34) {
                    for(int var35 = 0; var35 < var4; ++var35) {
                        Complex var36 = Complex.plusOne();
                        boolean var37 = false;

                        for(int var38 = 0; var38 < var4; ++var38) {
                            if (var14[var38]) {
                                if (var35 != var38) {
                                    if (var10[var38] == 1) {
                                        var36 = var36.times(var24[var34].minus(var9[var38]));
                                    } else {
                                        var36 = var36.times(Complex.pow(var24[var34].minus(var9[var38]), var10[var38]));
                                    }
                                } else if (var10[var35] < var13[var35]) {
                                    int var53 = var13[var35] - var10[var35];
                                    if (var53 == 1) {
                                        var36 = var36.times(var24[var34].minus(var9[var38]));
                                    } else if (var53 != 0) {
                                        var36 = var36.times(Complex.pow(var24[var34].minus(var9[var38]), var53));
                                    }
                                }
                            }
                        }

                        var48[var34][var35] = var36;
                    }
                }

                ComplexMatrix var51 = new ComplexMatrix(var48);
                Complex[] var52 = var51.solveLinearSet(var50);

                for(int var54 = 0; var54 < var4; ++var54) {
                    var6[0][var54] = var52[var54].times(var2).over(var3);
                    var6[1][var54] = var9[var54];
                    var6[2][var54].reset((double)var10[var54], 0.0D);
                }

                return var6;
            }
        }
    }

    public BlackBox copy() {
        if (this == null) {
            return null;
        } else {
            BlackBox var1 = new BlackBox();
            this.copyBBvariables(var1);
            return var1;
        }
    }

    public void copyBBvariables(BlackBox var1) {
        var1.sampLen = this.sampLen;
        var1.inputT = Conv.copy(this.inputT);
        var1.outputT = Conv.copy(this.outputT);
        var1.time = Conv.copy(this.time);
        var1.forgetFactor = this.forgetFactor;
        var1.deltaT = this.deltaT;
        var1.sampFreq = this.sampFreq;
        var1.inputS = this.inputS.copy();
        var1.outputS = this.outputS.copy();
        var1.sValue = this.sValue.copy();
        var1.zValue = this.zValue.copy();
        var1.sNumer = this.sNumer.copy();
        var1.sDenom = this.sDenom.copy();
        var1.zNumer = this.zNumer.copy();
        var1.zDenom = this.zDenom.copy();
        var1.sNumerSet = this.sNumerSet;
        var1.sDenomSet = this.sDenomSet;
        var1.sNumerScaleFactor = this.sNumerScaleFactor;
        var1.sDenomScaleFactor = this.sDenomScaleFactor;
        var1.sPoles = Complex.copy(this.sPoles);
        var1.sZeros = Complex.copy(this.sZeros);
        var1.zPoles = Complex.copy(this.zPoles);
        var1.zZeros = Complex.copy(this.zZeros);
        var1.sNumerDeg = this.sNumerDeg;
        var1.sDenomDeg = this.sDenomDeg;
        var1.zNumerDeg = this.zNumerDeg;
        var1.zDenomDeg = this.zDenomDeg;
        var1.deadTime = this.deadTime;
        var1.orderPade = this.orderPade;
        var1.sNumerPade = this.sNumerPade.copy();
        var1.sDenomPade = this.sDenomPade.copy();
        var1.sPolesPade = Complex.copy(this.sPolesPade);
        var1.sZerosPade = Complex.copy(this.sZerosPade);
        var1.sNumerDegPade = this.sNumerDegPade;
        var1.sDenomDegPade = this.sDenomDegPade;
        var1.maptozero = this.maptozero;
        var1.padeAdded = this.padeAdded;
        var1.integrationSum = this.integrationSum;
        var1.integMethod = this.integMethod;
        var1.ztransMethod = this.ztransMethod;
        var1.name = this.name;
        var1.fixedName = this.fixedName;
        var1.nPlotPoints = this.nPlotPoints;
    }


    public Object clone() {
        return this.copy();
    }
}
