//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package flanagan.analysis;

class EC50Function implements RegressionFunction {
    EC50Function() {
    }

    @Override
    public double function(double[] var1, double[] var2) {
        double var3 = var1[0] + (var1[1] - var1[0]) / (1.0D + Math.pow(var2[0] / var1[2], var1[3]));
        return var3;
    }
}
