//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.physprop;

import com.flanagan.interpolation.CubicSpline;

public class Water {
    public static final double MOLWEIGHT = 18.02D;

    public Water() {
    }

    public static double viscosity(double var0) {
        double[] var2 = new double[]{0.0D, 1.0D, 2.0D, 3.0D, 4.0D, 5.0D, 6.0D, 7.0D, 8.0D, 9.0D, 10.0D, 11.0D, 12.0D, 13.0D, 14.0D, 15.0D, 16.0D, 17.0D, 18.0D, 19.0D, 20.0D, 21.0D, 22.0D, 23.0D, 24.0D, 25.0D, 26.0D, 27.0D, 28.0D, 29.0D, 30.0D, 31.0D, 32.0D, 33.0D, 34.0D, 35.0D, 36.0D, 37.0D, 38.0D, 39.0D, 40.0D, 41.0D, 42.0D, 43.0D, 44.0D, 45.0D, 46.0D, 47.0D, 48.0D, 49.0D, 50.0D, 51.0D, 52.0D, 53.0D, 54.0D, 55.0D, 56.0D, 57.0D, 58.0D, 59.0D, 60.0D, 61.0D, 62.0D, 63.0D, 64.0D, 65.0D, 66.0D, 67.0D, 68.0D, 69.0D, 70.0D, 71.0D, 72.0D, 73.0D, 74.0D, 75.0D, 76.0D, 77.0D, 78.0D, 79.0D, 80.0D, 81.0D, 82.0D, 83.0D, 84.0D, 85.0D, 86.0D, 87.0D, 88.0D, 89.0D, 90.0D, 91.0D, 92.0D, 93.0D, 94.0D, 95.0D, 96.0D, 97.0D, 98.0D, 99.0D, 100.0D};
        double[] var3 = new double[]{0.0017921D, 0.0017313D, 0.0016728D, 0.0016191D, 0.0015674D, 0.0015188D, 0.0014728D, 0.0014284D, 0.001386D, 0.0013462D, 0.0013077D, 0.0012713D, 0.0012363D, 0.0012028D, 0.0011709D, 0.0011404D, 0.0011111D, 0.0010828D, 0.0010559D, 0.0010299D, 0.001005D, 9.81E-4D, 9.579E-4D, 9.358E-4D, 9.142E-4D, 8.937E-4D, 8.737E-4D, 8.545E-4D, 8.36E-4D, 8.18E-4D, 8.007E-4D, 7.84E-4D, 7.679E-4D, 7.523E-4D, 7.371E-4D, 7.225E-4D, 7.085E-4D, 6.947E-4D, 6.814E-4D, 6.685E-4D, 6.56E-4D, 6.439E-4D, 6.321E-4D, 6.207E-4D, 6.097E-4D, 5.988E-4D, 5.883E-4D, 5.782E-4D, 5.683E-4D, 5.588E-4D, 5.494E-4D, 5.404E-4D, 5.315E-4D, 5.229E-4D, 5.146E-4D, 5.064E-4D, 4.985E-4D, 4.907E-4D, 4.832E-4D, 4.759E-4D, 4.688E-4D, 4.618E-4D, 4.55E-4D, 4.483E-4D, 4.418E-4D, 4.355E-4D, 4.293E-4D, 4.233E-4D, 4.174E-4D, 4.117E-4D, 4.061E-4D, 4.006E-4D, 3.952E-4D, 3.9E-4D, 3.849E-4D, 3.799E-4D, 3.75E-4D, 3.702E-4D, 3.655E-4D, 3.61E-4D, 3.565E-4D, 3.521E-4D, 3.478E-4D, 3.436E-4D, 3.395E-4D, 3.355E-4D, 3.315E-4D, 3.276E-4D, 3.239E-4D, 3.202E-4D, 3.165E-4D, 3.13E-4D, 3.095E-4D, 3.06E-4D, 3.027E-4D, 2.994E-4D, 2.962E-4D, 2.93E-4D, 2.899E-4D, 2.868E-4D, 2.838E-4D};
        double[] var4 = new double[]{0.0D, 1.78373E-6D, 6.66507E-6D, 3.55981E-7D, 3.911E-6D, 2.60001E-6D, 1.28896E-6D, 1.84413E-6D, 3.3345E-6D, 4.1785E-7D, 2.7941E-6D, 1.00576E-6D, 1.58285E-6D, 1.66282E-6D, 1.36586E-6D, 1.27374E-6D, 7.39187E-7D, 1.76951E-6D, 5.82755E-7D, 1.29947E-6D, 8.1938E-7D, 8.23013E-7D, 1.28857E-6D, 2.27218E-8D, 1.62055E-6D, 9.50918E-8D, 9.99086E-7D, 7.08563E-7D, 3.66662E-7D, 8.24789E-7D, 5.34182E-7D, 6.38482E-7D, 5.1189E-7D, 3.13959E-7D, 6.32274E-7D, 7.56944E-7D, -6.00505E-8D, 6.83258E-7D, 3.27018E-7D, 4.08669E-7D, 4.38304E-7D, 2.38113E-7D, 4.09244E-7D, 5.24909E-7D, -1.08882E-7D, 5.10619E-7D, 4.66404E-7D, 2.3763E-8D, 6.38544E-7D, -1.77938E-7D, 6.73209E-7D, -1.14896E-7D, 3.86377E-7D, 3.69389E-7D, -6.39346E-8D, 4.86349E-7D, -8.14616E-8D, 4.39497E-7D, 1.23473E-7D, 2.66612E-7D, 1.00779E-8D, 2.93076E-7D, 1.76187E-8D, 2.36449E-7D, 2.36584E-7D, 1.72156E-8D, 2.94554E-7D, 4.56925E-9D, 2.87169E-7D, 4.67538E-8D, 1.25815E-7D, 4.99845E-8D, 2.74247E-7D, 5.30292E-8D, 1.13637E-7D, 9.24242E-8D, 1.16667E-7D, 4.09095E-8D, 3.19695E-7D, -1.19691E-7D, 1.59069E-7D, 8.34135E-8D, 1.07277E-7D, 8.74801E-8D, 1.42803E-7D, -5.86918E-8D, 9.19641E-8D, 2.90835E-7D, -5.53049E-8D, -6.96157E-8D, 3.33768E-7D, -6.54552E-8D, -7.19471E-8D, 3.53244E-7D, -1.41027E-7D, 2.10865E-7D, -1.02433E-7D, 1.98866E-7D, -9.30309E-8D, 1.73258E-7D, 0.0D};
        int var7 = var2.length;
        if (var0 >= var2[0] && var0 <= var2[var7 - 1]) {
            double var5 = CubicSpline.interpolate(var0, var2, var3, var4);
            return var5;
        } else {
            throw new IllegalArgumentException("Temperature outside the experimental data limits");
        }
    }

    public static double density(double var0) {
        double[] var2 = new double[]{0.0D, 1.0D, 2.0D, 3.0D, 4.0D, 5.0D, 6.0D, 7.0D, 8.0D, 9.0D, 10.0D, 11.0D, 12.0D, 13.0D, 14.0D, 15.0D, 16.0D, 17.0D, 18.0D, 19.0D, 20.0D, 21.0D, 22.0D, 23.0D, 24.0D, 25.0D, 26.0D, 27.0D, 28.0D, 29.0D, 30.0D, 31.0D, 32.0D, 33.0D, 34.0D, 35.0D, 36.0D, 37.0D, 38.0D, 39.0D, 40.0D, 41.0D, 42.0D, 43.0D, 44.0D, 45.0D, 46.0D, 47.0D, 48.0D, 49.0D, 50.0D, 51.0D, 52.0D, 53.0D, 54.0D, 55.0D, 60.0D, 65.0D, 70.0D, 75.0D, 80.0D, 85.0D, 90.0D, 95.0D, 100.0D};
        double[] var3 = new double[]{999.87D, 999.93D, 999.97D, 999.99D, 1000.0D, 999.99D, 999.97D, 999.93D, 999.88D, 999.81D, 999.73D, 999.63D, 999.52D, 999.4D, 999.27D, 999.13D, 998.97D, 998.8D, 998.62D, 998.43D, 998.23D, 998.02D, 997.8D, 997.56D, 997.32D, 997.07D, 996.81D, 996.54D, 996.26D, 995.07D, 995.67D, 995.37D, 995.05D, 994.73D, 994.4D, 994.06D, 993.71D, 993.36D, 992.99D, 992.62D, 992.24D, 991.86D, 991.47D, 991.07D, 990.66D, 990.25D, 989.82D, 989.4D, 988.96D, 988.52D, 988.07D, 987.62D, 987.15D, 986.69D, 986.21D, 985.73D, 983.24D, 980.59D, 977.81D, 974.89D, 971.83D, 968.65D, 965.34D, 961.92D, 958.38D};
        double[] var4 = new double[]{0.0D, -0.0241154D, -0.0235383D, -0.00173154D, -0.0295356D, -1.26193E-4D, -0.0299597D, -3.51591E-5D, -0.0298997D, -3.66034E-4D, -0.0286362D, -0.00508932D, -0.0110066D, -0.0108844D, -0.00545578D, -0.0272925D, -0.00537436D, -0.0112101D, -0.00978527D, -0.00964883D, -0.0116194D, -0.00387347D, -0.0328867D, 0.0154203D, -0.0287945D, 0.0397577D, -0.190236D, 0.661187D, -2.51451D, 3.93686D, -2.49294D, 0.634886D, -0.166608D, 0.0315471D, -0.0195801D, -0.0132268D, 0.0124871D, -0.0367217D, 0.0143998D, -0.0208776D, 0.00911065D, -0.015565D, -0.0068508D, -0.0170318D, 0.0149782D, -0.0428809D, 0.0365453D, -0.0433004D, 0.0166564D, -0.0233251D, 0.0166439D, -0.0432504D, 0.0363578D, -0.0421807D, 0.0123651D, -0.00727961D, -0.00660195D, -0.0047126D, -0.00574764D, -0.00589685D, -0.00426496D, -0.00584331D, -0.00356178D, -0.00630955D, 0.0D};
        int var7 = var2.length;
        if (var0 >= var2[0] && var0 <= var2[var7 - 1]) {
            double var5 = CubicSpline.interpolate(var0, var2, var3, var4);
            return var5;
        } else {
            throw new IllegalArgumentException("Temperature outside the experimental data limits");
        }
    }

    public static double elecPerm(double var0) {
        double var2 = var0 - 25.0D;
        return 78.54D * (1.0D - 0.004579D * var2 + 1.19E-5D * var2 * var2 - 2.8E-8D * var2 * var2 * var2);
    }

    public static double refractIndex(double var0, double var2) {
        return RefrIndex.water(var0, var2);
    }
}