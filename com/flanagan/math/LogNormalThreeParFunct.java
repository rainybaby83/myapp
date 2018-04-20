//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.math;

import com.flanagan.analysis.Stat;
import com.flanagan.roots.RealRootFunction;

class LogNormalThreeParFunct implements RealRootFunction {
    public double cfd = 0.0D;
    public double alpha = 0.0D;
    public double beta = 0.0D;
    public double gamma = 0.0D;

    LogNormalThreeParFunct() {
    }

    public double function(double var1) {
        double var3 = this.cfd - Stat.logNormalThreeParCDF(this.alpha, this.beta, this.gamma, var1);
        return var3;
    }
}
