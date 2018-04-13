//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package flanagan.analysis;

import flanagan.math.Fmath;

class GaussianFunctionFixed implements RegressionFunction {
    private double[] param = new double[3];
    private boolean[] fixed = new boolean[3];

    GaussianFunctionFixed() {
    }

    @Override
    public double function(double[] var1, double[] var2) {
        int var3 = 0;

        for(int var4 = 0; var4 < 3; ++var4) {
            if (!this.fixed[var4]) {
                this.param[var4] = var1[var3];
                ++var3;
            }
        }

        double var6 = this.param[2] / (this.param[1] * Math.sqrt(6.283185307179586D)) * Math.exp(-0.5D * Fmath.square((var2[0] - this.param[0]) / this.param[1]));
        return var6;
    }

    public void setParam(double[] var1) {
        this.param = var1;
    }

    public void setFixed(boolean[] var1) {
        this.fixed = var1;
    }
}
