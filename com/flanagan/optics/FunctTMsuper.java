//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.optics;

import com.flanagan.roots.RealRootFunction;

class FunctTMsuper implements RealRootFunction {
    public double substrateRefractiveIndex2 = 0.0D;
    public double effectiveRefractiveIndex2 = 0.0D;
    public double coreFilmRefractiveIndex2 = 0.0D;
    public double ko = 0.0D;
    public double thickness = 0.0D;
    public double modeNumber = 0.0D;

    FunctTMsuper() {
    }

    public double function(double var1) {
        double var3 = 0.0D;
        double var5 = var1 * var1;
        double var7 = Math.sqrt(this.effectiveRefractiveIndex2 - this.substrateRefractiveIndex2);
        double var9 = Math.sqrt(this.effectiveRefractiveIndex2 - var5);
        double var11 = Math.sqrt(this.coreFilmRefractiveIndex2 - this.effectiveRefractiveIndex2);
        var3 = 3.141592653589793D * this.modeNumber - this.thickness * this.ko * var11;
        var3 += Math.atan2(this.coreFilmRefractiveIndex2 * var9, var5 * var11) + Math.atan2(this.coreFilmRefractiveIndex2 * var7, this.substrateRefractiveIndex2 * var11);
        return var3;
    }
}
