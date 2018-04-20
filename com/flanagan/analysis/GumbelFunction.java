//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package flanagan.analysis;

class GumbelFunction implements RegressionFunction {
    private boolean scaleOption = true;
    private double scaleFactor = 1.0D;
    private int typeFlag = 0;

    GumbelFunction() {
    }

    @Override
    public double function(double[] var1, double[] var2) {
        double var3 = 0.0D;
        double var5 = 0.0D;
        double var7 = this.scaleFactor;
        switch(this.typeFlag) {
            case 0:
                var5 = (var2[0] - var1[0]) / var1[1];
                if (this.scaleOption) {
                    var7 = var1[2];
                }

                var3 = var7 / var1[1] * Math.exp(var5) * Math.exp(-Math.exp(var5));
                break;
            case 1:
                var5 = (var1[0] - var2[0]) / var1[1];
                if (this.scaleOption) {
                    var7 = var1[2];
                }

                var3 = var7 / var1[1] * Math.exp(var5) * Math.exp(-Math.exp(var5));
                break;
            case 2:
                var5 = var2[0] / var1[0];
                if (this.scaleOption) {
                    var7 = var1[1];
                }

                var3 = var7 / var1[0] * Math.exp(var5) * Math.exp(-Math.exp(var5));
                break;
            case 3:
                var5 = -var2[0] / var1[0];
                if (this.scaleOption) {
                    var7 = var1[1];
                }

                var3 = var7 / var1[0] * Math.exp(var5) * Math.exp(-Math.exp(var5));
                break;
            case 4:
                if (this.scaleOption) {
                    var7 = var1[0];
                }

                var3 = var7 * Math.exp(var2[0]) * Math.exp(-Math.exp(var2[0]));
                break;
            case 5:
                if (this.scaleOption) {
                    var7 = var1[0];
                }

                var3 = var7 * Math.exp(-var2[0]) * Math.exp(-Math.exp(-var2[0]));
        }

        return var3;
    }

    public void setScaleFactor(double var1) {
        this.scaleFactor = var1;
    }

    public void setScaleOption(boolean var1) {
        this.scaleOption = var1;
    }

    public void setTypeFlag(int var1) {
        this.typeFlag = var1;
    }
}
