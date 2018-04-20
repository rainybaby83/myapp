//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.analysis;

import com.flanagan.roots.RealRootFunction;

class EngsetLoad implements RealRootFunction {
    public double blockingProbability = 0.0D;
    public double totalResources = 0.0D;
    public double numberOfSources = 0.0D;

    EngsetLoad() {
    }

    @Override
    public double function(double var1) {
        return this.blockingProbability - Stat.engsetProbability(var1, this.totalResources, this.numberOfSources);
    }
}
