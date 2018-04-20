//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package flanagan.analysis;

import flanagan.roots.RealRootFunction;

class InverseGammaFunct implements RealRootFunction {
    public double gamma = 0.0D;

    InverseGammaFunct() {
    }

    @Override
    public double function(double var1) {
        double var3 = this.gamma - Stat.gamma(var1);
        return var3;
    }
}
