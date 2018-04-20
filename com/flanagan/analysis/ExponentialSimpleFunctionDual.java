//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.analysis;

class ExponentialSimpleFunctionDual implements RegressionFunction3 {
    private boolean scaleOption = true;
    private double scaleFactor = 1.0D;
    private double[][] xErrors = (double[][])null;
    private double[] yErrors = null;

    ExponentialSimpleFunctionDual() {
    }

    @Override
    public double[] function(double[] var1, double[] var2, int var3) {
        double var4 = this.scaleFactor;
        if (this.scaleOption) {
            var4 = var1[1];
        }

        double[] var6 = new double[]{var4 * Math.exp(var1[0] * var2[0]), 0.0D};
        if (var3 >= 0) {
            var6[1] = var4 * var1[0] * Math.exp(var1[0] * var2[0]);
            var6[1] = this.yErrors[var3] * this.yErrors[var3] + this.xErrors[0][var3] * this.xErrors[0][var3] * var6[1] * var6[1];
        }

        return var6;
    }

    public void setScaleFactor(double var1) {
        this.scaleFactor = var1;
    }

    public void setScaleOption(boolean var1) {
        this.scaleOption = var1;
    }

    public void setXerrors(double[][] var1) {
        this.xErrors = var1;
    }

    public void setYerrors(double[] var1) {
        this.yErrors = var1;
    }
}
