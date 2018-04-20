//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.analysis;

import com.flanagan.roots.RealRootFunction;

class CorrCoeffFunctST implements RealRootFunction {
    public double cfd = 0.0D;
    public int nu = 0;

    CorrCoeffFunctST() {
    }

    @Override
    public double function(double var1) {
        double var3 = this.cfd - Stat.corrCoeffProb(Math.abs(var1), this.nu);
        return var3;
    }
}
