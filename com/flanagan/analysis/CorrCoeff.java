//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package flanagan.analysis;

import flanagan.integration.IntegralFunction;

class CorrCoeff implements IntegralFunction {
    public double a;

    CorrCoeff() {
    }

    @Override
    public double function(double var1) {
        double var3 = Math.pow(1.0D - var1 * var1, this.a);
        return var3;
    }
}
