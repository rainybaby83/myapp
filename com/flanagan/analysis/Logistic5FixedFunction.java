//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.analysis;

class Logistic5FixedFunction implements RegressionFunction {
    private double bottom = 0.0D;
    private double top = 0.0D;

    Logistic5FixedFunction() {
    }

    @Override
    public double function(double[] var1, double[] var2) {
        double var3 = this.top + (this.bottom - this.top) / Math.pow(1.0D + Math.pow(var2[0] / var1[0], var1[1]), var1[2]);
        return var3;
    }

    public void setBottom(double var1) {
        this.bottom = var1;
    }

    public void setTop(double var1) {
        this.top = var1;
    }
}
