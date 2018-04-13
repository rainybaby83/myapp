//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package flanagan.analysis;

import flanagan.roots.RealRootFunction;

class ErlangCfunct implements RealRootFunction {
    public double nonZeroDelayProbability = 0.0D;
    public double totalResources = 0.0D;

    ErlangCfunct() {
    }

    @Override
    public double function(double var1) {
        return this.nonZeroDelayProbability - Stat.erlangCprobability(var1, this.totalResources);
    }
}
