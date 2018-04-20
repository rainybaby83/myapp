//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.analysis;

import com.flanagan.math.ArrayMaths;
import com.flanagan.math.MaximizationFunction;

class BoxCoxFunction implements MaximizationFunction {
    public double[] shiftedData = null;
    public int nData = 0;
    public double[] yTransform = null;
    public double[] gaussianOrderMedians = null;
    public Regression reg = null;
    public ArrayMaths am = null;
    public Stat st = null;

    BoxCoxFunction() {
    }

    @Override
    public double function(double[] var1) {
        int var2;
        if (var1[0] == 0.0D) {
            for(var2 = 0; var2 < this.nData; ++var2) {
                this.yTransform[var2] = Math.log(this.shiftedData[var2]);
            }
        } else {
            for(var2 = 0; var2 < this.nData; ++var2) {
                this.yTransform[var2] = (Math.pow(this.shiftedData[var2], var1[0]) - 1.0D) / var1[0];
            }
        }

        this.am = new ArrayMaths(this.yTransform);
        this.am = this.am.sort();
        double[] var3 = this.am.array();
        this.st = new Stat(var3);
        this.yTransform = this.st.standardize();
        this.reg = new Regression(this.gaussianOrderMedians, this.yTransform);
        this.reg.linear();
        return this.reg.getSampleR();
    }
}
