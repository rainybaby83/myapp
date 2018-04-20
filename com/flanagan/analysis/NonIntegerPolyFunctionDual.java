//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.analysis;

class NonIntegerPolyFunctionDual implements RegressionFunction3 {
    private double[][] xErrors = (double[][])null;
    private double[] yErrors = null;
    private int nTerms = 0;

    NonIntegerPolyFunctionDual() {
    }

    @Override
    public double[] function(double[] var1, double[] var2, int var3) {
        double[] var4 = new double[]{var1[0], 0.0D};

        int var5;
        for(var5 = 1; var5 < this.nTerms; ++var5) {
            var4[0] += var1[var5] * Math.pow(var2[0], var1[this.nTerms + var5 - 1]);
        }

        if (var3 >= 0) {
            for(var5 = 1; var5 < this.nTerms; ++var5) {
                var4[1] += var1[var5] * var1[this.nTerms + var5 - 1] * Math.pow(var2[0], var1[this.nTerms + var5 - 2]);
            }

            var4[1] = var4[1] * var4[1] * this.xErrors[0][var3] * this.xErrors[0][var3] + this.yErrors[var3] * this.yErrors[var3];
        }

        return var4;
    }

    public void setNterms(int var1) {
        this.nTerms = var1;
    }

    public void setXerrors(double[][] var1) {
        this.xErrors = var1;
    }

    public void setYerrors(double[] var1) {
        this.yErrors = var1;
    }
}
