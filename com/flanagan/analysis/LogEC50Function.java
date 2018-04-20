//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package flanagan.analysis;

class LogEC50Function implements RegressionFunction {
    LogEC50Function() {
    }

    @Override
    public double function(double[] var1, double[] var2) {
        double var3 = var1[0] + (var1[1] - var1[0]) / (1.0D + Math.pow(10.0D, (var1[2] - var2[0]) * var1[3]));
        return var3;
    }
}
