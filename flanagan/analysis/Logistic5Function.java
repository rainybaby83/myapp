//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package flanagan.analysis;

class Logistic5Function implements RegressionFunction {
    Logistic5Function() {
    }

    @Override
    public double function(double[] var1, double[] var2) {
        double var3 = var1[0] + (var1[1] - var1[0]) / Math.pow(1.0D + Math.pow(var2[0] / var1[2], var1[3]), var1[4]);
        return var3;
    }
}
