//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package flanagan.analysis;

class ExponentialMultipleFunction implements RegressionFunction {
    private int nExps = 0;

    ExponentialMultipleFunction() {
    }

    @Override
    public double function(double[] var1, double[] var2) {
        double var3 = 0.0D;

        for(int var5 = 0; var5 < this.nExps; var5 += 2) {
            var3 += var1[var5] * Math.exp(var1[var5 + 1] * var2[0]);
        }

        return var3;
    }

    public void setNexps(int var1) {
        this.nExps = var1;
    }
}
