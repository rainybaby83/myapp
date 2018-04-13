//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package flanagan.analysis;

import flanagan.roots.RealRootFunction;

class ChiSquareFunct implements RealRootFunction {
    public double cfd = 0.0D;
    public int nu = 0;

    ChiSquareFunct() {
    }

    @Override
    public double function(double var1) {
        double var3 = this.cfd - Stat.chiSquareCDF(var1, this.nu);
        return var3;
    }
}
