//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package flanagan.analysis;

import flanagan.math.Fmath;

class ErlangFunction implements RegressionFunction {
    private boolean scaleOption = true;
    private double scaleFactor = 1.0D;
    private double kay = 1.0D;

    ErlangFunction() {
    }

    @Override
    public double function(double[] var1, double[] var2) {
        boolean var3 = false;
        double var4 = this.scaleFactor;
        if (this.scaleOption) {
            var4 = var1[1];
        }

        double var6 = this.kay * Math.log(var1[0]) + (this.kay - 1.0D) * Math.log(var2[0]) - var2[0] * var1[0] - Fmath.logFactorial(this.kay - 1.0D);
        var6 = var4 * Math.exp(var6);
        return var6;
    }

    public void setScaleFactor(double var1) {
        this.scaleFactor = var1;
    }

    public void setScaleOption(boolean var1) {
        this.scaleOption = var1;
    }

    public void setKay(double var1) {
        this.kay = var1;
    }
}
