//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package flanagan.physchem;

import flanagan.analysis.Stat;

public class ImmunoChemistry {
    private static double molecularWeightIgG1 = 146000.0D;
    private static double molecularWeightIgG2 = 146000.0D;
    private static double molecularWeightIgG3 = 170000.0D;
    private static double molecularWeightIgG4 = 146000.0D;
    private static double molecularWeightIgM = 970000.0D;
    private static double molecularWeightIgA1 = 160000.0D;
    private static double molecularWeightIgA2 = 160000.0D;
    private static double molecularWeightIgD = 184000.0D;
    private static double molecularWeightIgE = 188000.0D;

    public ImmunoChemistry() {
    }

    public static double diffusionCoefficient(double var0, double var2, double var4) {
        double var6 = var4 - -273.15D;
        double var8 = 18.84955592153876D * var2 * var0;
        return 1.380650324E-23D * var6 / var8;
    }

    public static double diffusionCoefficient(double var0, double var2, double var4, double var6) {
        double var8 = var6 - -273.15D;
        double var10 = var0 * var2 / 6.0221419947E26D;
        double var12 = Math.pow(3.0D * var10 / 12.566370614359172D, 0.3333333333333333D);
        double var14 = 18.84955592153876D * var4 * var12;
        return 1.380650324E-23D * var8 / var14;
    }

    public static double oneDimensionalDiffusion(double var0, double var2, double var4, double var6) {
        double var8 = var4 / (2.0D * Math.sqrt(var0 * var6));
        return var2 * Stat.erfc(var8);
    }

    public static double diffusionControlledRate(double var0, double var2, double var4, double var6, double var8, double var10) {
        return 7.567646819769641E24D * var8 * var10 * (var0 + var2) * (var4 + var6) * 0.001D;
    }

    public static double molecularRadius(double var0, double var2) {
        double var4 = var0 / 6.0221419947E23D;
        double var6 = var4 * 0.001D * var2;
        return Math.pow(var6 * 3.0D / 12.566370614359172D, 0.3333333333333333D);
    }

    public static double molecularRadius(double var0) {
        return molecularRadius(var0, 7.4E-4D);
    }

    public static double effectiveRadius(double var0, double var2, double var4) {
        double var6 = var4 - -273.15D;
        double var8 = 18.84955592153876D * var2 * var0;
        return 1.380650324E-23D * var6 / var8;
    }

    public static double surfaceNumberConcn(double var0) {
        double var2 = 2.0D * var0 * Math.sqrt(3.0D);
        double var4 = 1.0D / var2;
        return var4;
    }

    public static double surfaceNumberConcn(double var0, double var2) {
        double var4 = var0 / 6.0221419947E23D;
        double var6 = var4 * 0.001D * var2;
        double var8 = Math.pow(var6 * 3.0D / 12.566370614359172D, 0.3333333333333333D);
        double var10 = 2.0D * var8 * Math.sqrt(3.0D);
        double var12 = 1.0D / var10;
        return var12;
    }

    public static double surfaceMolarConcn(double var0) {
        double var2 = surfaceNumberConcn(var0);
        return var2 / 6.0221419947E23D;
    }

    public static double surfaceMolarConcn(double var0, double var2) {
        double var4 = surfaceNumberConcn(var0, var2);
        return var4 / 6.0221419947E23D;
    }

    public static double convertSurfaceToVolumeConcn(double var0, double var2, double var4) {
        return var0 * var2 * 0.001D / var4;
    }

    public static double equivalentVolumeConcn(double var0, double var2, double var4) {
        double var6 = surfaceMolarConcn(var0);
        return var6 * var2 * 0.001D / var4;
    }

    public static double equivalentVolumeConcn(double var0, double var2, double var4, double var6) {
        double var8 = surfaceMolarConcn(var0, var6);
        return var8 * var2 * 0.001D / var4;
    }

    public static double getMolWeightIgG1() {
        return molecularWeightIgG1;
    }

    public static double getMolWeightIgG2() {
        return molecularWeightIgG2;
    }

    public static double getMolWeightIgG3() {
        return molecularWeightIgG3;
    }

    public static double getMolWeightIgG4() {
        return molecularWeightIgG4;
    }

    public static double getMolWeightIgM() {
        return molecularWeightIgM;
    }

    public static double getMolWeightIgA1() {
        return molecularWeightIgA1;
    }

    public static double getMolWeightIgA2() {
        return molecularWeightIgA2;
    }

    public static double getMolWeightIgD() {
        return molecularWeightIgD;
    }

    public static double getMolWeightIgE() {
        return molecularWeightIgE;
    }
}
