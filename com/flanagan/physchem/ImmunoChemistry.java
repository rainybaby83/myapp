//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.physchem;

import com.flanagan.analysis.Stat;

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

    public static double molecularRadius(double molWeight, double specVol) {
        double var4 = molWeight / 6.0221419947E23D;
        double var6 = var4 * 0.001D * specVol;
        return Math.pow(var6 * 3.0D / 12.566370614359172D, 0.3333333333333333D);
    }

    public static double molecularRadius(double molWeight) {
        return molecularRadius(molWeight, 7.4E-4D);
    }

    public static double effectiveRadius(double diffusionCoefficient, double viscosity, double temperature) {
        double var6 = temperature - -273.15D;
        double var8 = 18.84955592153876D * viscosity * diffusionCoefficient;
        return 1.380650324E-23D * var6 / var8;
    }

    public static double surfaceNumberConcn(double effectiveRadius) {
        double var2 = 2.0D * effectiveRadius * Math.sqrt(3.0D);
        double var4 = 1.0D / var2;
        return var4;
    }

    public static double surfaceNumberConcn(double molWeight, double specVolume) {
        double var4 = molWeight / 6.0221419947E23D;
        double var6 = var4 * 0.001D * specVolume;
        double var8 = Math.pow(var6 * 3.0D / 12.566370614359172D, 0.3333333333333333D);
        double var10 = 2.0D * var8 * Math.sqrt(3.0D);
        double var12 = 1.0D / var10;
        return var12;
    }

    public static double surfaceMolarConcn(double effectiveRadius) {
        double var2 = surfaceNumberConcn(effectiveRadius);
        return var2 / 6.0221419947E23D;
    }

    public static double surfaceMolarConcn(double molWeight, double specVolume) {
        double var4 = surfaceNumberConcn(molWeight, specVolume);
        return var4 / 6.0221419947E23D;
    }

    public static double convertSurfaceToVolumeConcn(double surfaceConcn, double area, double volume) {
        return surfaceConcn * area * 0.001D / volume;
    }

    public static double equivalentVolumeConcn(double effectiveRadius, double area, double volume) {
        double var6 = surfaceMolarConcn(effectiveRadius);
        return var6 * area * 0.001D / volume;
    }

    public static double equivalentVolumeConcn(double molWeight, double area, double volume, double specVolume) {
        double var8 = surfaceMolarConcn(molWeight, specVolume);
        return var8 * area * 0.001D / volume;
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
