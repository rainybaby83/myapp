//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package flanagan.analysis;

class FrechetTwoParProbPlotFunc implements RegressionFunction {
    private int nPoints = 0;
    private int index = 0;
    private double[] medians = null;

    FrechetTwoParProbPlotFunc() {
    }

    public double function(double[] var1, double[] var2) {
        if (this.index == 0) {
            this.medians = Stat.frechetOrderStatisticMedians(var1[0], var1[1], this.nPoints);
        }

        double var3 = this.medians[this.index];
        ++this.index;
        if (this.index == this.nPoints) {
            this.index = 0;
        }

        return var3;
    }

    public void setDataArray(int var1) {
        this.nPoints = var1;
    }
}
