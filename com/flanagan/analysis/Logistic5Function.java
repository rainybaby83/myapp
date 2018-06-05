//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.analysis;

class Logistic5Function implements RegressionFunction {
    Logistic5Function() {
    }

    @Override
    public double function(double[] paraArray, double[] xArray) {
        // y = top + (bottom - top) /  ( (1+x/c)^d) )^e
        double top = paraArray[0];
        double bottom = paraArray[1];
        double c = paraArray[2];
        double d = paraArray[3];
        double e = paraArray[4];
        double x = xArray[0];
        double y = top + (bottom - top) / Math.pow(1.0D + Math.pow(x / c, d), e);
        return y;
    }
}
