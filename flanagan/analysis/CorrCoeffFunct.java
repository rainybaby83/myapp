//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package flanagan.analysis;

import flanagan.roots.RealRootFunction;

class CorrCoeffFunct implements RealRootFunction {
    public double cfd = 0.0D;
    public int nu = 0;

    CorrCoeffFunct() {
    }

    @Override
    public double function(double var1) {
        double var3 = this.cfd - Stat.corrCoeffCDF(Math.abs(var1), this.nu);
        return var3;
    }
}
