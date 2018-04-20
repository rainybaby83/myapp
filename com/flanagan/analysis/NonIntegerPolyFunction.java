//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.analysis;

class NonIntegerPolyFunction implements RegressionFunction {
    private int nTerms = 0;

    NonIntegerPolyFunction() {
    }

    @Override
    public double function(double[] var1, double[] var2) {
        double var3 = var1[0];

        for(int var5 = 1; var5 < this.nTerms; ++var5) {
            var3 += var1[var5] * Math.pow(var2[0], var1[this.nTerms + var5 - 1]);
        }

        return var3;
    }

    public void setNterms(int var1) {
        this.nTerms = var1;
    }
}
