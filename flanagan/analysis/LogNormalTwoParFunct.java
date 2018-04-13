//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package flanagan.analysis;

import flanagan.roots.RealRootFunction;

class LogNormalTwoParFunct implements RealRootFunction {
    public double cfd = 0.0D;
    public double mu = 0.0D;
    public double sigma = 0.0D;

    LogNormalTwoParFunct() {
    }

    @Override
    public double function(double var1) {
        double var3 = this.cfd - Stat.logNormalCDF(this.mu, this.sigma, var1);
        return var3;
    }
}
