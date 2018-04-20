//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package flanagan.analysis;

class ShiftedRectangularHyperbolaFunctionDual implements RegressionFunction3 {
    private double[][] xErrors = (double[][])null;
    private double[] yErrors = null;

    ShiftedRectangularHyperbolaFunctionDual() {
    }

    @Override
    public double[] function(double[] var1, double[] var2, int var3) {
        double[] var4 = new double[2];
        double var5 = var1[0] + var2[0];
        var4[0] = var1[2] * var2[0] / var5 + var1[1];
        var4[1] = var1[0] * var1[2] / (var5 * var5);
        var4[1] = this.yErrors[var3] * this.yErrors[var3] + var4[1] * var4[1] * this.xErrors[0][var3] * this.xErrors[0][var3];
        return var4;
    }

    public void setXerrors(double[][] var1) {
        this.xErrors = var1;
    }

    public void setYerrors(double[] var1) {
        this.yErrors = var1;
    }
}
