//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.physchem;

import com.flanagan.integration.IntegralFunction;

class FunctionPatX implements IntegralFunction {
    public int numOfIons = 0;
    public double termOne = 0.0D;
    public double expTerm = 0.0D;
    public double[] bulkConcn = null;
    public double[] charges = null;

    FunctionPatX() {
    }

    public double function(double var1) {
        double var3 = 0.0D;

        for(int var5 = 0; var5 < this.numOfIons; ++var5) {
            var3 += this.bulkConcn[var5] * this.termOne * (Math.exp(-this.expTerm * this.charges[var5] * var1) - 1.0D);
        }

        return 1.0D / Math.sqrt(var3);
    }
}
