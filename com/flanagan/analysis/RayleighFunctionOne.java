//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.analysis;

class RayleighFunctionOne implements RegressionFunction {
    private boolean scaleOption = true;
    private double scaleFactor = 1.0D;

    RayleighFunctionOne() {
    }

    @Override
    public double function(double[] var1, double[] var2) {
        double var3 = 0.0D;
        boolean var5 = false;
        double var6 = this.scaleFactor;
        if (this.scaleOption) {
            var6 = var1[1];
        }

        if (var2[0] >= 0.0D) {
            double var8 = var2[0] / var1[0];
            var3 = var6 / var1[0] * var8 * Math.exp(-0.5D * Math.pow(var8, 2.0D));
        }

        return var3;
    }

    public void setScaleFactor(double var1) {
        this.scaleFactor = var1;
    }

    public void setScaleOption(boolean var1) {
        this.scaleOption = var1;
    }
}
