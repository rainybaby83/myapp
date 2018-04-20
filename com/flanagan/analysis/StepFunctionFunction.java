//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package flanagan.analysis;

class StepFunctionFunction implements RegressionFunction {
    private boolean scaleOption = true;
    private double scaleFactor = 1.0D;

    StepFunctionFunction() {
    }

    @Override
    public double function(double[] var1, double[] var2) {
        double var3 = this.scaleFactor;
        if (this.scaleOption) {
            var3 = var1[1];
        }

        double var5 = 0.0D;
        if (var2[0] > var1[0]) {
            var5 = var3;
        }

        return var5;
    }

    public void setScaleFactor(double var1) {
        this.scaleFactor = var1;
    }

    public void setScaleOption(boolean var1) {
        this.scaleOption = var1;
    }
}
