//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.analysis;

class PolyXYDEfunction implements RegressionFunction3 {
    private double[][] xErrors = (double[][])null;
    private double[] yErrors = null;
    private int deg = 0;

    PolyXYDEfunction() {
    }

    @Override
    public double[] function(double[] var1, double[] var2, int var3) {
        double[] var4 = new double[]{var1[0], this.yErrors[var3] * this.yErrors[var3]};
        double var5 = 0.0D;
        double var7 = this.xErrors[0][var3] * this.xErrors[0][var3];

        for(int var9 = 1; var9 <= this.deg; ++var9) {
            var4[0] += var1[var9] * Math.pow(var2[0], (double)var9);
            var5 += (double)var9 * var1[var9] * Math.pow(var2[0], (double)(var9 - 1));
        }

        var4[1] += var5 * var5 * var7;
        return var4;
    }

    public void setDeg(int var1) {
        this.deg = var1;
    }

    public void setXerrors(double[][] var1) {
        this.xErrors = var1;
    }

    public void setYerrors(double[] var1) {
        this.yErrors = var1;
    }
}
