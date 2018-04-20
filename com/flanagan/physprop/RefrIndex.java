//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.physprop;

import com.flanagan.complex.Complex;
import com.flanagan.optics.RefractiveIndex;

public class RefrIndex {
    public RefrIndex() {
    }

    public static Complex absToComplex(double var0, double var2, double var4, double var6) {
        return RefractiveIndex.absToComplex(var0, var2, var4, var6);
    }

    public static double imagToAbs(double var0, double var2) {
        return RefractiveIndex.imagToAbs(var0, var2);
    }

    public static Complex gold(double var0) {
        return RefractiveIndex.gold(var0);
    }

    public static Complex silver(double var0) {
        return RefractiveIndex.silver(var0);
    }

    public static double quartz(double var0) {
        return RefractiveIndex.quartz(var0);
    }

    public static double crownGlass(double var0) {
        return RefractiveIndex.crownGlass(var0);
    }

    public static double floatGlass(double var0) {
        return RefractiveIndex.floatGlass(var0);
    }

    public static double microSlideGlass(double var0) {
        return RefractiveIndex.microscopeSlideGlass(var0);
    }

    public static double polymethacrylate(double var0) {
        return RefractiveIndex.polymethacrylate(var0);
    }

    public static double air(double var0) {
        return RefractiveIndex.air(var0);
    }

    public static double water(double var0, double var2) {
        return RefractiveIndex.water(var0, var2);
    }

    public static double pva(double var0, double var2, double var4) {
        return RefractiveIndex.pva(var0, var2, var4);
    }

    public static double saline(double var0, double var2, double var4) {
        return RefractiveIndex.saline(var0, var2, var4);
    }

    public static double sucrose(double var0, double var2) {
        return RefractiveIndex.water(var0, var2);
    }

    public static double lorenzLorentz(double var0, double var2, double var4, double var6, double var8, double var10, double var12, double var14) {
        return RefractiveIndex.lorenzLorentz(var0, var2, var4, var6, var8, var10, var12, var14);
    }

    public static double lorenzLorentz(double[] var0, double[] var1, double[] var2, double[] var3, double var4) {
        return RefractiveIndex.lorenzLorentz(var0, var1, var2, var3, var4);
    }
}
