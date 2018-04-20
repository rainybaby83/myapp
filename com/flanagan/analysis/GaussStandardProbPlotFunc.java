//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.analysis;

import com.flanagan.math.Fmath;
import com.flanagan.math.MinimizationFunction;

class GaussStandardProbPlotFunc implements MinimizationFunction {
    private double[] dataArray = null;
    private double[] medians = null;
    private double[] weights = null;
    private boolean weighted = false;

    GaussStandardProbPlotFunc() {
    }

    @Override
    public double function(double[] var1) {
        int var2 = this.dataArray.length;
        if (this.weighted) {
            for(int var3 = 0; var3 < var2; ++var3) {
                this.weights[var3] = 1.0D / Math.sqrt(Stat.gaussianPDF(var1[0], var1[1], this.dataArray[var3]));
            }
        }

        this.medians = Stat.gaussianOrderStatisticMedians(var2);
        double var6 = 0.0D;

        for(int var5 = 0; var5 < var2; ++var5) {
            var6 += Fmath.square((this.medians[var5] - this.dataArray[var5]) / this.weights[var5]);
        }

        return var6;
    }

    public void setDataArray(double[] var1, double[] var2, boolean var3) {
        this.dataArray = var1;
        this.weights = var2;
        this.weighted = var3;
    }
}
