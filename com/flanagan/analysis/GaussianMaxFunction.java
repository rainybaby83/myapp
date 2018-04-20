//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.analysis;

import com.flanagan.math.MaximizationFunction;

class GaussianMaxFunction implements MaximizationFunction {
    public double[] sortedData = null;
    public int nData = 0;
    public double[] gaussianOrderMedians = null;
    public Regression reg = null;

    GaussianMaxFunction() {
    }

    @Override
    public double function(double[] var1) {
        this.gaussianOrderMedians = Stat.gaussianOrderStatisticMedians(var1[0], var1[1], this.nData);
        this.reg = new Regression(this.sortedData, this.gaussianOrderMedians);
        this.reg.linear();
        System.out.println(this.reg.getSampleR() + " " + var1[0] + " " + var1[1]);
        return this.reg.getSampleR();
    }
}
