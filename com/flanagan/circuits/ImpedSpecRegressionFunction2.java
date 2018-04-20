//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.circuits;

import com.flanagan.analysis.RegressionFunction2;
import com.flanagan.complex.Complex;

public class ImpedSpecRegressionFunction2 implements RegressionFunction2 {
    public int numberOfFrequencies = 0;
    protected ImpedSpecModel isModel = null;

    public ImpedSpecRegressionFunction2() {
    }

    @Override
    public double function(double[] var1, double[] var2, int var3) {
        Complex var4 = this.isModel.modelImpedance(var1, var2[0]);
        return var3 < this.numberOfFrequencies ? var4.getReal() : var4.getImag();
    }
}
