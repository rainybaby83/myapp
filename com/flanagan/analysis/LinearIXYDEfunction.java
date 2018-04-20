//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package flanagan.analysis;

class LinearIXYDEfunction implements RegressionFunction3 {
    private double[][] xErrors = (double[][])null;
    private double[] yErrors = null;
    private int nParam = 0;
    private double intercept = 0.0D;

    LinearIXYDEfunction() {
    }

    @Override
    public double[] function(double[] var1, double[] var2, int var3) {
        double[] var4 = new double[]{this.intercept, this.yErrors[var3] * this.yErrors[var3]};

        for(int var5 = 0; var5 < this.nParam; ++var5) {
            var4[0] += var1[var5] * var2[var5];
            var4[1] += var1[var5] * var1[var5] * this.xErrors[var5][var3] * this.xErrors[var5][var3];
        }

        return var4;
    }

    public void setNparam(int var1) {
        this.nParam = var1;
    }

    public void setXerrors(double[][] var1) {
        this.xErrors = var1;
    }

    public void setYerrors(double[] var1) {
        this.yErrors = var1;
    }

    public void setIntercept(double var1) {
        this.intercept = var1;
    }
}
