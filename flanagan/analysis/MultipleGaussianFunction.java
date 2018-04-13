//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package flanagan.analysis;

import flanagan.math.Fmath;

class MultipleGaussianFunction implements RegressionFunction {
    private boolean scaleOption = true;
    private double scaleFactor = 1.0D;
    private int nGaussians = 1;

    MultipleGaussianFunction() {
    }

    @Override
    public double function(double[] var1, double[] var2) {
        double var3 = 0.0D;
        int var5 = 0;

        for(int var6 = 0; var6 < this.nGaussians; ++var6) {
            var3 += var1[var5 + 2] / (var1[var5 + 1] * Math.sqrt(6.283185307179586D)) * Math.exp(-0.5D * Fmath.square((var2[0] - var1[var5]) / var1[var5 + 1]));
            var5 += 3;
        }

        return var3;
    }

    public void setScaleFactor(double var1) {
        this.scaleFactor = var1;
    }

    public void setScaleOption(boolean var1) {
        this.scaleOption = var1;
    }

    public void setNgaussians(int var1) {
        this.nGaussians = var1;
    }
}
