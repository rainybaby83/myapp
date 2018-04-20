//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package flanagan.optics;

import flanagan.analysis.RegressionFunction;
import flanagan.complex.Complex;

class RegressFunct implements RegressionFunction {
    public int numberOfLayers = 0;
    public String mode = null;
    public double eVectorAngleDeg = 0.0D;
    public double[] thicknesses = null;
    public double[] incidentAnglesDeg = null;
    public Complex[][] refractiveIndices = (Complex[][])null;
    public Complex[][] relativeMagneticPermeabilities = (Complex[][])null;
    public int regressionOption = 0;
    public int[] thicknessEstimateIndices = null;
    public int[] refractIndexRealEstimateIndices = null;
    public int[] refractIndexImagEstimateIndices = null;
    public int[] magneticPermRealEstimateIndices = null;
    public int[] magneticPermImagEstimateIndices = null;

    RegressFunct() {
    }

    public double function(double[] var1, double[] var2) {
        Reflectivity var3 = new Reflectivity(this.numberOfLayers);
        if (this.mode.equals("mixed")) {
            var3.setMode(this.eVectorAngleDeg);
        } else {
            var3.setMode(this.mode);
        }

        int var4 = 0;
        int var5 = this.thicknessEstimateIndices.length;

        int var6;
        for(var6 = 0; var6 < var5; ++var6) {
            this.thicknesses[this.thicknessEstimateIndices[var6]] = var1[var4];
            ++var4;
        }

        var3.setThicknesses(this.thicknesses);
        var5 = this.refractIndexRealEstimateIndices.length;

        for(var6 = 0; var6 < var5; ++var6) {
            this.refractiveIndices[0][this.refractIndexRealEstimateIndices[var6]].setReal(var1[var4]);
            ++var4;
        }

        var5 = this.refractIndexImagEstimateIndices.length;

        for(var6 = 0; var6 < var5; ++var6) {
            this.refractiveIndices[0][this.refractIndexImagEstimateIndices[var6]].setImag(var1[var4]);
            ++var4;
        }

        var3.setRefractiveIndices(this.refractiveIndices);
        var5 = this.magneticPermRealEstimateIndices.length;

        for(var6 = 0; var6 < var5; ++var6) {
            this.relativeMagneticPermeabilities[0][this.magneticPermRealEstimateIndices[var6]].setReal(var1[var4]);
            ++var4;
        }

        var5 = this.magneticPermImagEstimateIndices.length;

        for(var6 = 0; var6 < var5; ++var6) {
            this.relativeMagneticPermeabilities[0][this.magneticPermImagEstimateIndices[var6]].setImag(var1[var4]);
            ++var4;
        }

        var3.setRelativeMagneticPermeabilities(this.relativeMagneticPermeabilities);
        var3.setIncidentAngle(var2[0]);
        double var8 = 0.0D;
        switch(this.regressionOption) {
            case 1:
                var8 = ((double[])((double[])var3.getReflectivities()))[0];
                break;
            case 2:
                var8 = ((double[])((double[])var3.getTransmissivities()))[0];
                break;
            case 3:
                var8 = var1[var4] * ((double[])((double[])var3.getEvanescentFields()))[0];
                break;
            default:
                throw new IllegalArgumentException("Regresion option " + this.regressionOption + " does not exist");
        }

        return var8;
    }
}
