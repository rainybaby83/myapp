//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package flanagan.analysis;

import flanagan.roots.RealRootFunction;

class GammaFunct implements RealRootFunction {
    public double mu = 0.0D;
    public double beta = 0.0D;
    public double gamma = 0.0D;
    public double cfd = 0.0D;

    GammaFunct() {
    }

    @Override
    public double function(double var1) {
        double var3 = this.cfd - Stat.gammaCDF(this.mu, this.beta, this.gamma, var1);
        return var3;
    }
}
