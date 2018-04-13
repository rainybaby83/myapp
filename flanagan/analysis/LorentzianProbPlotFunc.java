//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package flanagan.analysis;

import flanagan.math.Fmath;
import flanagan.math.MinimizationFunction;

class LorentzianProbPlotFunc implements MinimizationFunction {
    private double[] dataArray = null;
    private double[] medians = null;
    private double[] weights = null;
    private boolean weighted = false;

    LorentzianProbPlotFunc() {
    }

    @Override
    public double function(double[] var1) {
        this.medians = Stat.lorentzianOrderStatisticMedians(var1[0], var1[1], this.dataArray.length);
        double var2 = 0.0D;

        for(int var4 = 0; var4 < this.dataArray.length; ++var4) {
            var2 += Fmath.square((this.medians[var4] - this.dataArray[var4]) / this.weights[var4]);
        }

        return var2;
    }

    public void setDataArray(double[] var1, double[] var2, boolean var3) {
        this.dataArray = var1;
        this.weights = var2;
        this.weighted = var3;
    }
}
