//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.physprop;

import com.flanagan.analysis.Stat;

public class Diffusion {
    public Diffusion() {
    }

    public static double diffusionCoefficient(double var0, double var2, double var4, double var6, double var8) {
        double var10 = var8 - -273.15D;
        double var12 = var0 * var2 / 6.0221419947E26D;
        double var14 = Math.pow(3.0D * var12 / 12.566370614359172D, 0.3333333333333333D);
        double var16 = 18.84955592153876D * var4 * var14;
        return 1.380650324E-23D * var10 / var16;
    }

    public static double planarHexagonalNumberPerSquareMetre(double var0, double var2) {
        double var4 = var0 * var2 / 6.0221419947E26D;
        double var6 = Math.pow(3.0D * var4 / 12.566370614359172D, 0.3333333333333333D);
        return 2.0D / (3.0D * Math.sqrt(3.0D) * var6 * var6);
    }

    public static double planarHexagonalMolesPerSquareMetre(double var0, double var2) {
        double var4 = var0 * var2 / 6.0221419947E26D;
        double var6 = Math.pow(3.0D * var4 / 12.566370614359172D, 0.3333333333333333D);
        return 2.0D / (3.0D * Math.sqrt(3.0D) * var6 * var6 * 6.0221419947E23D);
    }

    public static double oneDimensionalDiffusion(double var0, double var2, double var4, double var6) {
        double var8 = var4 / (2.0D * Math.sqrt(var0 * var6));
        return var2 * Stat.erfc(var8);
    }
}
