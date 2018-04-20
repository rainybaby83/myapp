//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.analysis;

class ShiftedRectangularHyperbolaFunction implements RegressionFunction {
    ShiftedRectangularHyperbolaFunction() {
    }

    @Override
    public double function(double[] var1, double[] var2) {
        double var3 = var1[2] * var2[0] / (var1[0] + var2[0]) + var1[1];
        return var3;
    }
}
