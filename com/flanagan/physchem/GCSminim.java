//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.physchem;

import com.flanagan.math.MinimisationFunction;

class GCSminim implements MinimisationFunction {
    public double psiDelta = 0.0D;
    public double tempK = 298.15D;
    public double surfaceSiteDensity = 0.0D;
    public double surfaceArea = 0.0D;
    public double volume = 0.0D;
    public int nonZeroAssocK = 0;
    public double[] assocK = null;
    public double[] initConcn = null;
    public double[] charges = null;
    public int[] indexK = null;

    public GCSminim() {
    }

    public double function(double[] var1) {
        double var2 = 0.0D;
        double var4 = 0.0D;
        boolean var6 = false;
        double var7 = this.surfaceArea / this.volume;
        double var9 = this.psiDelta * -1.60217646263E-19D / (1.380650324E-23D * this.tempK);
        double var11 = 0.0D;

        int var13;
        for(var13 = 0; var13 < this.nonZeroAssocK; ++var13) {
            var11 += var1[0];
        }

        var11 = this.surfaceSiteDensity - var11;

        for(var13 = 0; var13 < this.nonZeroAssocK; ++var13) {
            int var14 = this.indexK[var13];
            var4 = this.assocK[var14] * (this.initConcn[var14] - var1[var13] * var7) * Math.exp(var9 * this.charges[var14]) * var11 - var1[0];
            var2 += var4 * var4;
        }

        return var2;
    }
}
