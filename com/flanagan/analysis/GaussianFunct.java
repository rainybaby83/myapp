//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.analysis;

import com.flanagan.roots.RealRootFunction;

class GaussianFunct implements RealRootFunction {
    public double cfd = 0.0D;
    public double mean = 0.0D;
    public double sd = 0.0D;

    GaussianFunct() {
    }

    @Override
    public double function(double var1) {
        double var3 = this.cfd - Stat.gaussianCDF(this.mean, this.sd, var1);
        return var3;
    }
}
