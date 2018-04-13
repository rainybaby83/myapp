//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package flanagan.optics;

import flanagan.roots.RealRootFunction;

class FunctTMplot implements RealRootFunction {
    public double substrateRefractiveIndex2 = 0.0D;
    public double superstrateRefractiveIndex2 = 0.0D;
    public double coreFilmRefractiveIndex2 = 0.0D;
    public double prismRefractiveIndex2 = 0.0D;
    public double ko = 0.0D;
    public double prismToWaveguideGap = 0.0D;
    public boolean setPrismToWaveguideGap = false;
    public double thickness = 0.0D;
    public double modeNumber = 0.0D;

    FunctTMplot() {
    }

    @Override
    public double function(double var1) {
        double var3 = 0.0D;
        double var5 = var1 * var1;
        double var7 = Math.sqrt(var5 - this.substrateRefractiveIndex2);
        double var9 = Math.sqrt(var5 - this.superstrateRefractiveIndex2);
        double var11 = Math.sqrt(this.coreFilmRefractiveIndex2 - var5);
        double var13 = Math.sqrt(this.prismRefractiveIndex2 - var5);
        double var15 = Math.atan2(this.coreFilmRefractiveIndex2 * var9, this.superstrateRefractiveIndex2 * var11);
        var3 = 3.141592653589793D * this.modeNumber - this.thickness * this.ko * var11;
        var3 += var15 + Math.atan2(this.coreFilmRefractiveIndex2 * var7, this.substrateRefractiveIndex2 * var11);
        if (this.setPrismToWaveguideGap) {
            var3 += Math.sin(var15) * Math.cos(Math.atan2(var9 * this.prismRefractiveIndex2, var13 * this.superstrateRefractiveIndex2)) * Math.exp(-2.0D * this.prismToWaveguideGap * var9);
        }

        return var3;
    }
}
