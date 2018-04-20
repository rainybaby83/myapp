//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.physprop;

public class Pva {
    public Pva() {
    }

    public static double viscosity(double var0, double var2, double var4) {
        double var6 = 4.53E-4D * Math.pow(var2, 0.64D);
        double var8 = var6 * 1.07D;
        double var10 = var8 * Math.pow(1.07D, -(var4 - 20.0D) / 10.0D);
        double var18 = var0 / 10.0D;
        double var12 = var18 * (var10 + 0.201D * var18 * Math.pow(var10, 2.28D));
        double var14 = Water.viscosity(var4);
        double var16 = (var12 + 1.0D) * var14;
        return var16;
    }

    public static double density(double var0, double var2) {
        var0 /= 10.0D;
        double var4 = 1000.0D * (0.99565D + (0.00248D - 1.09D / var2) * var0 + (6.4E-5D - 0.39D / var2) * var0 * var0);
        return var4;
    }

    public static double specificVolume() {
        return 7.65E-4D;
    }

    public static double diffCoeff(double var0, double var2, double var4) {
        double var18 = var4 - -273.15D;
        double var10 = viscosity(var0, var2, var4);
        double var12 = specificVolume();
        double var14 = var2 * var12 / 6.0221419947E26D;
        double var16 = Math.pow(3.0D * var14 / 12.566370614359172D, 0.3333333333333333D);
        double var8 = 18.84955592153876D * var10 * var16;
        double var6 = 1.380650324E-23D * var18 / var8;
        return var6;
    }

    public static double refractIndex(double var0, double var2, double var4) {
        double var12 = -0.998419D;
        double var14 = -1.87778E-17D;
        double var8 = Water.refractIndex(var2, var4);
        double var10 = 1.0D + var12 * (1.0D + var14 / Math.pow(var2, 2.0D));
        double var6 = var8 + var10 * var0 / 10.0D;
        return var6;
    }
}
