//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.integration;

import com.flanagan.math.Fmath;
import java.util.ArrayList;

public class Integration {
    private IntegralFunction integralFunc = null;
    private boolean setFunction = false;
    private double lowerLimit = 0.0D / 0.0;
    private double upperLimit = 0.0D / 0.0;
    private boolean setLimits = false;
    private int glPoints = 0;
    private boolean setGLpoints = false;
    private int nIntervals = 0;
    private boolean setIntervals = false;
    private double integralSum = 0.0D;
    private double integralMean = 0.0D;
    private double integralSD = 0.0D;
    private boolean integrationDone = false;
    private boolean integrationStatsDone = false;
    private ArrayList<Integer> gaussQuadIndex = new ArrayList();
    private ArrayList<double[]> gaussQuadDistArrayList = new ArrayList();
    private double[] gaussQuadDist = null;
    private ArrayList<double[]> gaussQuadWeightArrayList = new ArrayList();
    private double[] gaussQuadWeight = null;
    private double glPrec = 3.0E-11D;
    private double requiredAccuracy = 0.0D;
    private double trapeziumAccuracy = 0.0D;
    private static double trapAccuracy = 0.0D;
    private int maxIntervals = 0;
    private int trapeziumIntervals = 1;
    private static int trapIntervals = 1;

    public Integration() {
    }

    public Integration(IntegralFunction var1) {
        this.integralFunc = var1;
        this.setFunction = true;
    }

    public Integration(IntegralFunction var1, double var2, double var4) {
        this.integralFunc = var1;
        this.setFunction = true;
        this.lowerLimit = var2;
        this.upperLimit = var4;
        this.setLimits = true;
    }

    public void setIntegrationFunction(IntegralFunction var1) {
        this.integralFunc = var1;
        this.setFunction = true;
    }

    public void setLimits(double var1, double var3) {
        this.lowerLimit = var1;
        this.upperLimit = var3;
        this.setLimits = true;
    }

    public void setLowerLimit(double var1) {
        this.lowerLimit = var1;
        if (!Fmath.isNaN(this.upperLimit)) {
            this.setLimits = true;
        }

    }

    public void setlowerLimit(double var1) {
        this.lowerLimit = var1;
        if (!Fmath.isNaN(this.upperLimit)) {
            this.setLimits = true;
        }

    }

    public void setUpperLimit(double var1) {
        this.upperLimit = var1;
        if (!Fmath.isNaN(this.lowerLimit)) {
            this.setLimits = true;
        }

    }

    public void setupperLimit(double var1) {
        this.upperLimit = var1;
        if (!Fmath.isNaN(this.lowerLimit)) {
            this.setLimits = true;
        }

    }

    public void setGLpoints(int var1) {
        this.glPoints = var1;
        this.setGLpoints = true;
    }

    public void setNintervals(int var1) {
        this.nIntervals = var1;
        this.setIntervals = true;
    }

    public double getIntegralSum() {
        if (!this.integrationDone) {
            throw new IllegalArgumentException("No integration has been performed");
        } else {
            return this.integralSum;
        }
    }

    public double getIntegralMean() {
        if (!this.integrationStatsDone) {
            throw new IllegalArgumentException("No relevant integration has been performed");
        } else {
            return this.integralMean;
        }
    }

    public double getIntegralStandardDeviation() {
        if (!this.integrationStatsDone) {
            throw new IllegalArgumentException("No relevant integration has been performed");
        } else {
            return this.integralSD;
        }
    }

    public double[] getGauassQuadDistances() {
        return this.gaussQuadDist;
    }

    public double[] getGauassQuadWeights() {
        return this.gaussQuadWeight;
    }

    public double gaussQuad() {
        if (!this.setGLpoints) {
            throw new IllegalArgumentException("Number of points not set");
        } else if (!this.setLimits) {
            throw new IllegalArgumentException("One limit or both limits not set");
        } else if (!this.setFunction) {
            throw new IllegalArgumentException("No integral function has been set");
        } else {
            this.gaussQuadDist = new double[this.glPoints];
            this.gaussQuadWeight = new double[this.glPoints];
            boolean var1 = true;
            int var2 = -1;
            if (!this.gaussQuadIndex.isEmpty()) {
                for(int var3 = 0; var3 < this.gaussQuadIndex.size(); ++var3) {
                    Integer var4 = (Integer)this.gaussQuadIndex.get(var3);
                    if (var4 == this.glPoints) {
                        var1 = false;
                        var2 = var3;
                    }
                }
            }

            if (var1) {
                this.gaussQuadCoeff(this.glPoints);
                this.gaussQuadIndex.add(new Integer(this.glPoints));
                this.gaussQuadDistArrayList.add(this.gaussQuadDist);
                this.gaussQuadWeightArrayList.add(this.gaussQuadWeight);
            } else {
                this.gaussQuadDist = (double[])this.gaussQuadDistArrayList.get(var2);
                this.gaussQuadWeight = (double[])this.gaussQuadWeightArrayList.get(var2);
            }

            double var12 = 0.0D;
            double var5 = 0.5D * (this.upperLimit + this.lowerLimit);
            double var7 = 0.5D * (this.upperLimit - this.lowerLimit);
            double var9 = 0.0D;

            for(int var11 = 0; var11 < this.glPoints; ++var11) {
                var9 = var7 * this.gaussQuadDist[var11];
                var12 += this.gaussQuadWeight[var11] * this.integralFunc.function(var5 + var9);
            }

            this.integralSum = var12 * var7;
            this.integrationDone = true;
            return this.integralSum;
        }
    }

    public double gaussQuad(int var1) {
        this.glPoints = var1;
        this.setGLpoints = true;
        return this.gaussQuad();
    }

    public static double gaussQuad(IntegralFunction var0, double var1, double var3, int var5) {
        Integration var6 = new Integration(var0, var1, var3);
        return var6.gaussQuad(var5);
    }

    public ArrayList<Double> gaussQuadPlusMeanAndSD() {
        if (!this.setGLpoints) {
            throw new IllegalArgumentException("Number of points not set");
        } else if (!this.setLimits) {
            throw new IllegalArgumentException("One limit or both limits not set");
        } else if (!this.setFunction) {
            throw new IllegalArgumentException("No integral function has been set");
        } else {
            this.gaussQuadDist = new double[this.glPoints];
            this.gaussQuadWeight = new double[this.glPoints];
            double var1 = 0.5D * (this.upperLimit + this.lowerLimit);
            double var3 = 0.5D * (this.upperLimit - this.lowerLimit);
            boolean var5 = true;
            int var6 = -1;
            if (!this.gaussQuadIndex.isEmpty()) {
                for(int var7 = 0; var7 < this.gaussQuadIndex.size(); ++var7) {
                    Integer var8 = (Integer)this.gaussQuadIndex.get(var7);
                    if (var8 == this.glPoints) {
                        var5 = false;
                        var6 = var7;
                    }
                }
            }

            if (var5) {
                this.gaussQuadCoeff(this.glPoints);
                this.gaussQuadIndex.add(new Integer(this.glPoints));
                this.gaussQuadDistArrayList.add(this.gaussQuadDist);
                this.gaussQuadWeightArrayList.add(this.gaussQuadWeight);
            } else {
                this.gaussQuadDist = (double[])this.gaussQuadDistArrayList.get(var6);
                this.gaussQuadWeight = (double[])this.gaussQuadWeightArrayList.get(var6);
            }

            double var20 = 0.0D;
            double var9 = 0.0D;
            double var11 = 0.0D;
            double var13 = 0.0D;
            double var15 = 0.0D;

            for(int var17 = 0; var17 < this.glPoints; ++var17) {
                var15 = var3 * this.gaussQuadDist[var17];
                var11 = var1 + var15;
                var9 = this.gaussQuadWeight[var17] * this.integralFunc.function(var11);
                var20 += var9;
                var13 += var9 * var11;
            }

            this.integralSum = var20 * var3;
            this.integralMean = var13 / var20;
            double var21 = 0.0D;

            for(int var19 = 0; var19 < this.glPoints; ++var19) {
                var15 = var3 * this.gaussQuadDist[var19];
                var11 = var1 + var15;
                var9 = this.gaussQuadWeight[var19] * this.integralFunc.function(var11);
                var21 += Fmath.square(var11 - this.integralMean) * var9;
            }

            this.integralSD = Math.sqrt(var21 / var20);
            ArrayList var22 = new ArrayList();
            var22.add(new Double(this.integralSum));
            var22.add(new Double(this.integralMean));
            var22.add(new Double(this.integralSD));
            this.integrationStatsDone = true;
            this.integrationDone = true;
            return var22;
        }
    }

    public ArrayList<Double> gaussQuadPlusMeanAndSD(int var1) {
        this.glPoints = var1;
        this.setGLpoints = true;
        return this.gaussQuadPlusMeanAndSD();
    }

    public static ArrayList<Double> gaussQuadPlusMeanAndSD(IntegralFunction var0, double var1, double var3, int var5) {
        Integration var6 = new Integration(var0, var1, var3);
        return var6.gaussQuadPlusMeanAndSD(var5);
    }

    public void gaussQuadCoeff(int var1) {
        double var2 = 0.0D;
        double var4 = 0.0D;
        double var6 = 0.0D;
        double var8 = 0.0D;
        double var10 = 0.0D;
        double var12 = 0.0D;
        double var14 = -1.0D;
        double var16 = 1.0D;
        int var18 = (var1 + 1) / 2;
        double var19 = 0.5D * (var16 + var14);
        double var21 = 0.5D * (var16 - var14);

        for(int var23 = 1; var23 <= var18; ++var23) {
            var2 = Math.cos(3.141592653589793D * ((double)var23 - 0.25D) / ((double)var1 + 0.5D));

            do {
                var6 = 1.0D;
                var8 = 0.0D;

                for(int var24 = 1; var24 <= var1; ++var24) {
                    var10 = var8;
                    var8 = var6;
                    var6 = ((2.0D * (double)var24 - 1.0D) * var2 * var6 - ((double)var24 - 1.0D) * var10) / (double)var24;
                }

                var12 = (double)var1 * (var2 * var6 - var8) / (var2 * var2 - 1.0D);
                var4 = var2;
                var2 -= var6 / var12;
            } while(Math.abs(var2 - var4) > this.glPrec);

            this.gaussQuadDist[var23 - 1] = var19 - var21 * var2;
            this.gaussQuadDist[var1 - var23] = var19 + var21 * var2;
            this.gaussQuadWeight[var23 - 1] = 2.0D * var21 / ((1.0D - var2 * var2) * var12 * var12);
            this.gaussQuadWeight[var1 - var23] = this.gaussQuadWeight[var23 - 1];
        }

    }

    public static void gaussQuadCoeff(double[] var0, double[] var1, int var2) {
        Integration var3 = new Integration();
        var3.gaussQuadCoeff(var2);
        var0 = var3.getGauassQuadDistances();
        var1 = var3.getGauassQuadWeights();
    }

    public double trapezium() {
        if (!this.setIntervals) {
            throw new IllegalArgumentException("Number of intervals not set");
        } else if (!this.setLimits) {
            throw new IllegalArgumentException("One limit or both limits not set");
        } else if (!this.setFunction) {
            throw new IllegalArgumentException("No integral function has been set");
        } else {
            double var1 = 0.0D;
            double var3 = (this.upperLimit - this.lowerLimit) / (double)this.nIntervals;
            double var5 = this.lowerLimit;
            double var7 = this.lowerLimit + var3;
            double var9 = this.integralFunc.function(var5);
            this.integralSum = 0.0D;

            for(int var11 = 0; var11 < this.nIntervals; ++var11) {
                if (var7 > this.upperLimit) {
                    var7 = this.upperLimit;
                    var3 -= var7 - this.upperLimit;
                }

                var1 = this.integralFunc.function(var7);
                this.integralSum += 0.5D * (var9 + var1) * var3;
                var9 = var1;
                var7 += var3;
            }

            this.integrationDone = true;
            return this.integralSum;
        }
    }

    public double trapezium(int var1) {
        this.nIntervals = var1;
        this.setIntervals = true;
        return this.trapezium();
    }

    public static double trapezium(IntegralFunction var0, double var1, double var3, int var5) {
        Integration var6 = new Integration(var0, var1, var3);
        return var6.trapezium(var5);
    }

    public double trapezium(double var1, int var3) {
        this.requiredAccuracy = var1;
        this.maxIntervals = var3;
        this.trapeziumIntervals = 1;
        double var4 = trapezium(this.integralFunc, this.lowerLimit, this.upperLimit, 1);
        double var6 = var4;
        boolean var8 = true;

        int var9;
        for(var9 = 2; var9 <= this.maxIntervals; ++var9) {
            var4 = trapezium(this.integralFunc, this.lowerLimit, this.upperLimit, var9);
            this.trapeziumAccuracy = Math.abs((var4 - var6) / var6);
            if (this.trapeziumAccuracy <= this.requiredAccuracy) {
                break;
            }

            var6 = var4;
        }

        if (var9 > this.maxIntervals) {
            System.out.println("accuracy criterion was not met in this.trapezium - current sum was returned as result.");
            this.trapeziumIntervals = this.maxIntervals;
        } else {
            this.trapeziumIntervals = var9;
        }

        trapIntervals = this.trapeziumIntervals;
        trapAccuracy = this.trapeziumAccuracy;
        return var4;
    }

    public static double trapezium(IntegralFunction var0, double var1, double var3, double var5, int var7) {
        Integration var8 = new Integration(var0, var1, var3);
        return var8.trapezium(var5, var7);
    }

    public int getTrapeziumIntervals() {
        return this.trapeziumIntervals;
    }

    public static int getTrapIntervals() {
        return trapIntervals;
    }

    public double getTrapeziumAccuracy() {
        return this.trapeziumAccuracy;
    }

    public static double getTrapAccuracy() {
        return trapAccuracy;
    }

    public double backward() {
        if (!this.setIntervals) {
            throw new IllegalArgumentException("Number of intervals not set");
        } else if (!this.setLimits) {
            throw new IllegalArgumentException("One limit or both limits not set");
        } else if (!this.setFunction) {
            throw new IllegalArgumentException("No integral function has been set");
        } else {
            double var1 = (this.upperLimit - this.lowerLimit) / (double)this.nIntervals;
            double var3 = this.lowerLimit + var1;
            this.integralFunc.function(var3);
            this.integralSum = 0.0D;

            for(int var7 = 0; var7 < this.nIntervals; ++var7) {
                if (var3 > this.upperLimit) {
                    var3 = this.upperLimit;
                    var1 -= var3 - this.upperLimit;
                }

                double var5 = this.integralFunc.function(var3);
                this.integralSum += var5 * var1;
                var3 += var1;
            }

            this.integrationDone = true;
            return this.integralSum;
        }
    }

    public double backward(int var1) {
        this.nIntervals = var1;
        this.setIntervals = true;
        return this.backward();
    }

    public static double backward(IntegralFunction var0, double var1, double var3, int var5) {
        Integration var6 = new Integration(var0, var1, var3);
        return var6.backward(var5);
    }

    public double forward() {
        double var1 = (this.upperLimit - this.lowerLimit) / (double)this.nIntervals;
        double var3 = this.lowerLimit;
        this.integralFunc.function(var3);
        this.integralSum = 0.0D;

        for(int var7 = 0; var7 < this.nIntervals; ++var7) {
            if (var3 > this.upperLimit) {
                var3 = this.upperLimit;
                var1 -= var3 - this.upperLimit;
            }

            double var5 = this.integralFunc.function(var3);
            this.integralSum += var5 * var1;
            var3 += var1;
        }

        this.integrationDone = true;
        return this.integralSum;
    }

    public double forward(int var1) {
        this.nIntervals = var1;
        this.setIntervals = true;
        return this.forward();
    }

    public static double forward(IntegralFunction var0, double var1, double var3, int var5) {
        Integration var6 = new Integration(var0, var1, var3);
        return var6.forward(var5);
    }

    public static double foreward(IntegralFunction var0, double var1, double var3, int var5) {
        Integration var6 = new Integration(var0, var1, var3);
        return var6.forward(var5);
    }
}
