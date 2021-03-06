//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.analysis;

class Logistic5FixedFunctionDual implements RegressionFunction3 {
    private double bottom = 0.0D;
    private double top = 0.0D;
    private double[][] xErrors = (double[][])null;
    private double[] yErrors = null;

    Logistic5FixedFunctionDual() {
    }

    @Override
    public double[] function(double[] var1, double[] var2, int var3) {
        double[] var4 = new double[]{this.top + (this.bottom - this.top) / Math.pow(1.0D + Math.pow(var2[0] / var1[0], var1[1]), var1[2]), 0.0D};
        if ((double)var3 >= 0.0D) {
            var4[1] = (this.top - this.bottom) * var1[2] * var1[1] * Math.pow(var2[0] / var1[0], var1[1] - 1.0D) / (var1[0] * Math.pow(1.0D + Math.pow(var2[0] / var1[0], var1[1]), var1[2] + 1.0D));
            var4[1] = this.yErrors[var3] * this.yErrors[var3] + var4[1] * var4[1] * this.xErrors[0][var3] * this.xErrors[0][var3];
        }

        return var4;
    }

    public void setBottom(double var1) {
        this.bottom = var1;
    }

    public void setTop(double var1) {
        this.top = var1;
    }

    public void setXerrors(double[][] var1) {
        this.xErrors = var1;
    }

    public void setYerrors(double[] var1) {
        this.yErrors = var1;
    }
}
