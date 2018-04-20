//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.analysis;

import com.flanagan.roots.RealRootFunction;

class ErlangBfunct implements RealRootFunction {
    public double blockingProbability = 0.0D;
    public double totalResources = 0.0D;

    ErlangBfunct() {
    }

    @Override
    public double function(double var1) {
        return this.blockingProbability - Stat.erlangBprobability(var1, this.totalResources);
    }
}
