//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package flanagan.analysis;

class OneMinusExponentialFunction implements RegressionFunction {
    private boolean scaleOption = true;
    private double scaleFactor = 1.0D;

    OneMinusExponentialFunction() {
    }

    @Override
    public double function(double[] var1, double[] var2) {
        double var3 = this.scaleFactor;
        if (this.scaleOption) {
            var3 = var1[0];
        }

        double var5 = var3 * (1.0D - Math.exp(var1[1] * var2[0]));
        return var5;
    }

    public void setScaleFactor(double var1) {
        this.scaleFactor = var1;
    }

    public void setScaleOption(boolean var1) {
        this.scaleOption = var1;
    }
}
