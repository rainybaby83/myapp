//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.analysis;

class EC50FixedFunction implements RegressionFunction {
    private double bottom = 0.0D;
    private double top = 0.0D;

    EC50FixedFunction() {
    }

    @Override
    public double function(double[] var1, double[] var2) {
        double var3 = this.top + (this.bottom - this.top) / (1.0D + Math.pow(var2[0] / var1[0], var1[1]));
        return var3;
    }

    public void setBottom(double var1) {
        this.bottom = var1;
    }

    public void setTop(double var1) {
        this.top = var1;
    }
}
