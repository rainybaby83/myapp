//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.analysis;

import com.flanagan.roots.RealRootFunction;

class EngsetProb implements RealRootFunction {
    public double offeredTraffic = 0.0D;
    public double totalResources = 0.0D;
    public double numberOfSources = 0.0D;

    EngsetProb() {
    }

    @Override
    public double function(double var1) {
        double var3 = this.offeredTraffic / (this.numberOfSources - this.offeredTraffic * (1.0D - var1));
        double var5 = Stat.logFactorial(this.numberOfSources - 1.0D) - Stat.logFactorial(this.totalResources) - Stat.logFactorial(this.numberOfSources - 1.0D - this.totalResources);
        double var7 = 0.0D;
        double var9 = 0.0D;
        double var11 = 0.0D;

        double var13;
        for(var13 = 0.0D; var11 <= this.totalResources; ++var11) {
            var9 = Stat.logFactorial(this.numberOfSources - 1.0D) - Stat.logFactorial(var11) - Stat.logFactorial(this.numberOfSources - 1.0D - var11);
            var9 += (var11 - this.totalResources) * Math.log(var3);
            var7 += Math.exp(var9);
        }

        var13 = Math.exp(var5 - Math.log(var7));
        return var1 - var13;
    }
}
