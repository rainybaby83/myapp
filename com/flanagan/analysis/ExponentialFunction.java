//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.analysis;

class ExponentialFunction implements RegressionFunction {
    private boolean scaleOption = true;
    private double scaleFactor = 1.0D;
    private int typeFlag = 0;

    ExponentialFunction() {
    }

    @Override
    public double function(double[] var1, double[] var2) {
        double var3 = 0.0D;
        boolean var5 = false;
        double var6 = this.scaleFactor;
        double var8;
        switch(this.typeFlag) {
            case 0:
                if (var2[0] >= var1[0]) {
                    if (this.scaleOption) {
                        var6 = var1[2];
                    }

                    var8 = (var2[0] - var1[0]) / var1[1];
                    var3 = var6 / var1[1] * Math.exp(-var8);
                }
                break;
            case 1:
                if (var2[0] >= 0.0D) {
                    var8 = var2[0] / var1[0];
                    if (this.scaleOption) {
                        var6 = var1[1];
                    }

                    var3 = var6 / var1[0] * Math.exp(-var8);
                }
                break;
            case 2:
                if (var2[0] >= 0.0D) {
                    var8 = var2[0];
                    if (this.scaleOption) {
                        var6 = var1[0];
                    }

                    var3 = var6 * Math.exp(-var8);
                }
        }

        return var3;
    }

    public void setScaleFactor(double var1) {
        this.scaleFactor = var1;
    }

    public void setScaleOption(boolean var1) {
        this.scaleOption = var1;
    }

    public void setTypeFlag(int var1) {
        this.typeFlag = var1;
    }
}
