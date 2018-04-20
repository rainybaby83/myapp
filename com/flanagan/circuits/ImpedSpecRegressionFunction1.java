//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.circuits;

import com.flanagan.analysis.RegressionFunction2;
import com.flanagan.complex.Complex;

public class ImpedSpecRegressionFunction1 extends Impedance implements RegressionFunction2 {
    public int numberOfFrequencies = 0;
    public int modelNumber = 0;

    public ImpedSpecRegressionFunction1() {
    }

    public double function(double[] var1, double[] var2, int var3) {
        Complex var4 = Impedance.modelImpedance(var1, var2[0], this.modelNumber);
        return var3 < this.numberOfFrequencies ? var4.getReal() : var4.getImag();
    }
}
