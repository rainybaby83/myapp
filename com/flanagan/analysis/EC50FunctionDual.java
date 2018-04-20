//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.analysis;

class EC50FunctionDual implements RegressionFunction3 {
    private double[][] xErrors = (double[][])null;
    private double[] yErrors = null;

    EC50FunctionDual() {
    }

    @Override
    public double[] function(double[] var1, double[] var2, int var3) {
        double[] var4 = new double[]{var1[0] + (var1[1] - var1[0]) / (1.0D + Math.pow(var2[0] / var1[2], var1[3])), 0.0D};
        if (var3 >= 0) {
            var4[1] = (var1[0] - var1[1]) * Math.pow(var2[0] / var1[2], var1[3] - 1.0D) / (var1[2] * Math.pow(1.0D + Math.pow(var2[0] / var1[2], var1[3]), 2.0D));
            var4[1] = this.yErrors[var3] * this.yErrors[var3] + var4[1] * var4[1] * this.xErrors[0][var3] * this.xErrors[0][var3];
        }

        return var4;
    }

    public void setXerrors(double[][] var1) {
        this.xErrors = var1;
    }

    public void setYerrors(double[] var1) {
        this.yErrors = var1;
    }
}
