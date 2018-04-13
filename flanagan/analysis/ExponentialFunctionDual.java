//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package flanagan.analysis;

class ExponentialFunctionDual implements RegressionFunction3 {
    private boolean scaleOption = true;
    private double scaleFactor = 1.0D;
    private int typeFlag = 0;
    private double[][] xErrors = (double[][])null;
    private double[] yErrors = null;

    ExponentialFunctionDual() {
    }

    @Override
    public double[] function(double[] var1, double[] var2, int var3) {
        double[] var4 = new double[2];
        boolean var5 = false;
        double var6 = this.scaleFactor;
        double var8;
        switch(this.typeFlag) {
            case 0:
                if (var2[0] >= var1[0]) {
                    if (this.scaleOption) {
                        var6 = var1[2];
                    }

                    var8 = (var2[0] - var1[0]) / var1[1];
                    var4[0] = var6 / var1[1] * Math.exp(-var8);
                    if (var3 >= 0) {
                        var4[1] = -(var6 / var1[1]) * Math.exp(-var8) / var1[1];
                        var4[1] = this.yErrors[var3] * this.yErrors[var3] + this.xErrors[0][var3] * this.xErrors[0][var3] * var4[1] * var4[1];
                    }
                }
                break;
            case 1:
                if (var2[0] >= 0.0D) {
                    var8 = var2[0] / var1[0];
                    if (this.scaleOption) {
                        var6 = var1[1];
                    }

                    var4[0] = var6 / var1[0] * Math.exp(-var8);
                    if (var3 >= 0) {
                        var4[1] = -(var6 / var1[0]) * Math.exp(-var8) / var1[0];
                        var4[1] = this.yErrors[var3] * this.yErrors[var3] + this.xErrors[0][var3] * this.xErrors[0][var3] * var4[1] * var4[1];
                    }
                }
                break;
            case 2:
                if (var2[0] >= 0.0D) {
                    var8 = var2[0];
                    if (this.scaleOption) {
                        var6 = var1[0];
                    }

                    var4[0] = var6 * Math.exp(-var8);
                    if (var3 >= 0) {
                        var4[1] = -var6 * Math.exp(-var8);
                        var4[1] = this.yErrors[var3] * this.yErrors[var3] + this.xErrors[0][var3] * this.xErrors[0][var3] * var4[1] * var4[1];
                    }
                }
        }

        return var4;
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

    public void setXerrors(double[][] var1) {
        this.xErrors = var1;
    }

    public void setYerrors(double[] var1) {
        this.yErrors = var1;
    }
}
