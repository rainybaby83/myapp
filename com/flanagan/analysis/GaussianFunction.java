//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.analysis;

import com.flanagan.math.Fmath;

class GaussianFunction implements RegressionFunction {
    private boolean scaleOption = true;
    private double scaleFactor = 1.0D;

    GaussianFunction() {
    }

    @Override
    public double function(double[] var1, double[] var2) {
        double var3 = this.scaleFactor;
        if (this.scaleOption) {
            var3 = var1[2];
        }

        double var5 = var3 / (var1[1] * Math.sqrt(6.283185307179586D)) * Math.exp(-0.5D * Fmath.square((var2[0] - var1[0]) / var1[1]));
        return var5;
    }

    public void setScaleFactor(double var1) {
        this.scaleFactor = var1;
    }

    public void setScaleOption(boolean var1) {
        this.scaleOption = var1;
    }
}
