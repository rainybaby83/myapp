//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.analysis;

class ParetoFunctionTwo implements RegressionFunction {
    private int typeFlag = 0;

    ParetoFunctionTwo() {
    }

    @Override
    public double function(double[] var1, double[] var2) {
        double var3 = 0.0D;
        switch(this.typeFlag) {
            case 1:
                if (var2[0] >= 1.0D) {
                    var3 = 1.0D - Math.pow(1.0D / var2[0], var1[0]);
                }
                break;
            case 2:
                if (var2[0] >= var1[1]) {
                    var3 = 1.0D - Math.pow(var1[1] / var2[0], var1[0]);
                }
                break;
            case 3:
                if (var2[0] >= var1[1] + var1[2]) {
                    var3 = 1.0D - Math.pow(var1[1] / (var2[0] - var1[2]), var1[0]);
                }
        }

        return var3;
    }

    public void setTypeFlag(int var1) {
        this.typeFlag = var1;
    }
}
