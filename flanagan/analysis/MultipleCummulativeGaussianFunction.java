//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package flanagan.analysis;

class MultipleCummulativeGaussianFunction implements RegressionFunction {
    public int nGaussians = 1;

    MultipleCummulativeGaussianFunction() {
    }

    @Override
    public double function(double[] var1, double[] var2) {
        double var3 = 0.0D;
        int var5 = 0;

        for(int var6 = 0; var6 < this.nGaussians; ++var6) {
            var3 += var1[var5 + 2] * Stat.gaussianCDF(var1[var5], var1[var5 + 1], var2[0]);
            var5 += 3;
        }

        return var3 + var1[var5];
    }
}
