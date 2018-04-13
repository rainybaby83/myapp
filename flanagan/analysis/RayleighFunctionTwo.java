//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package flanagan.analysis;

class RayleighFunctionTwo implements RegressionFunction {
    RayleighFunctionTwo() {
    }

    @Override
    public double function(double[] var1, double[] var2) {
        double var3 = 0.5D * Math.pow(var2[0] / var1[0], 2.0D);
        return var3;
    }
}
