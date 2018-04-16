//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package flanagan.analysis;

import flanagan.roots.RealRootFunction;

class FFfunct implements RealRootFunction {
    public double cfd = 0.0D;
    public int nu1 = 0;
    public int nu2 = 0;

    FFfunct() {
    }

    @Override
    public double function(double var1) {
        double var3 = this.cfd - (1.0D - Stat.fCompCDF(var1, this.nu1, this.nu2));
        return var3;
    }
}