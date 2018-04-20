//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.math;

import com.flanagan.analysis.Stat;
import com.flanagan.roots.RealRootFunction;

class BetaFunct implements RealRootFunction {
    public double alpha = 0.0D;
    public double beta = 0.0D;
    public double min = 0.0D;
    public double max = 0.0D;
    public double cfd = 0.0D;

    BetaFunct() {
    }

    public double function(double var1) {
        double var3 = this.cfd - Stat.betaCDF(this.min, this.max, this.alpha, this.beta, var1);
        return var3;
    }
}
