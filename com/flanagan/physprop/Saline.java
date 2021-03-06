//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.physprop;

import com.flanagan.interpolation.CubicSpline;

public class Saline {
    public static final double MOLWEIGHT = 58.45D;

    public Saline() {
    }

    public static double viscosity(double var0, double var2) {
        double[] var4 = new double[]{0.0D, 0.017D, 0.034D, 0.051D, 0.069D, 0.086D, 0.103D, 0.12D, 0.137D, 0.155D, 0.172D, 0.189D, 0.207D, 0.224D, 0.241D, 0.259D, 0.276D, 0.294D, 0.311D, 0.329D, 0.346D, 0.364D, 0.382D, 0.399D, 0.418D, 0.435D, 0.452D, 0.47D, 0.488D, 0.505D, 0.523D, 0.541D, 0.559D, 0.577D, 0.595D, 0.653D, 0.631D, 0.649D, 0.667D, 0.685D, 0.703D, 0.721D, 0.739D, 0.757D, 0.775D, 0.794D, 0.812D, 0.83D, 0.848D, 0.866D, 0.885D, 0.921D, 0.958D, 0.995D, 1.032D, 1.069D, 1.106D, 1.144D, 1.181D, 1.218D, 1.256D, 1.294D, 1.331D, 1.369D, 1.407D, 1.445D, 1.484D, 1.522D, 1.56D, 1.599D, 1.637D, 1.676D, 1.715D, 1.754D, 1.193D, 1.832D, 1.93D, 2.029D, 2.129D, 2.229D, 2.33D, 2.432D, 2.534D, 2.637D, 2.741D, 2.845D, 3.056D, 3.27D, 3.486D, 3.706D, 3.928D, 4.153D, 4.382D, 4.613D, 4.848D, 5.085D, 5.326D};
        double[] var5 = new double[]{0.00100219D, 0.00100389D, 0.0010057D, 0.0010074D, 0.00100911D, 0.00101082D, 0.00101353D, 0.00101524D, 0.00101695D, 0.00101866D, 0.00102038D, 0.0010221D, 0.00102392D, 0.00102463D, 0.00102635D, 0.00102807D, 0.00102979D, 0.00103152D, 0.00103324D, 0.00103396D, 0.00103579D, 0.00103752D, 0.00103925D, 0.00104098D, 0.00104271D, 0.00104445D, 0.00104527D, 0.00104701D, 0.00104875D, 0.00105049D, 0.00105223D, 0.00105397D, 0.0010548D, 0.00105654D, 0.00105829D, 0.00106004D, 0.00106178D, 0.00106251D, 0.00106437D, 0.00106653D, 0.00106787D, 0.00106963D, 0.00107138D, 0.00107325D, 0.00107501D, 0.00107677D, 0.00107853D, 0.00107926D, 0.00108113D, 0.0010829D, 0.00108467D, 0.00108935D, 0.00109289D, 0.00109655D, 0.00110011D, 0.00110378D, 0.00110735D, 0.00111207D, 0.00111566D, 0.00111935D, 0.0011241D, 0.0011277D, 0.00113247D, 0.00113619D, 0.00114086D, 0.0011446D, 0.00114939D, 0.00115409D, 0.00115891D, 0.00116373D, 0.00116857D, 0.0011733D, 0.00117815D, 0.00118301D, 0.00118788D, 0.00119383D, 0.00120655D, 0.00122041D, 0.00123553D, 0.00125073D, 0.00126588D, 0.00128232D, 0.00129885D, 0.00131656D, 0.00133337D, 0.00135126D, 0.00138744D, 0.00142411D, 0.00146355D, 0.00150682D, 0.00155756D, 0.0016135D, 0.00167732D, 0.00174526D, 0.00182018D, 0.00190192D, 0.00198975D};
        double[] var6 = new double[]{0.0D, 7.68695E-4D, -7.91042E-4D, 1.11737E-4D, -0.00135411D, 0.00742974D, -0.0076036D, 0.0022234D, -0.00129002D, 0.00105411D, -7.94614E-4D, 0.00212435D, -0.00753267D, 0.00782177D, -0.00278555D, 0.00157181D, -0.00153892D, 0.00281175D, -0.00816056D, 0.00868783D, -0.0032574D, 6.17161E-4D, 7.88751E-4D, -0.00190588D, 0.00313379D, -0.00715407D, 0.00638216D, -0.00191908D, 0.00129416D, -0.00129001D, 0.00189902D, -0.00630606D, 0.00647338D, -0.0027356D, 0.0046542D, -0.0182844D, -0.0177707D, 0.00963645D, 1.50791E-4D, -0.00468406D, 0.00340026D, -0.0011392D, 9.71365E-4D, -5.24035E-4D, -9.12263E-4D, 0.00242436D, -0.00728848D, 0.00765548D, -0.00222235D, -6.17948E-4D, 0.00287778D, -0.00232672D, 8.15016E-4D, -4.07408E-4D, 3.76341E-4D, -6.15852E-4D, 0.00164879D, -0.00153126D, 1.06328E-4D, 0.00154422D, -0.00220911D, 0.00251382D, -0.00237932D, 0.00204582D, -0.00185658D, 0.00151625D, -4.2451E-4D, 3.00603E-4D, -2.79286E-4D, 3.09564E-4D, -3.7128E-4D, 2.28089E-4D, -6.77046E-5D, 8.21768E-5D, 0.00126799D, -6.84692E-5D, 1.38594E-4D, 1.34632E-4D, -1.04198E-6D, -8.24638E-5D, 2.10442E-4D, -9.85469E-5D, 2.35649E-4D, -2.64725E-4D, 2.25783E-4D, -3.92966E-5D, -9.59633E-6D, 7.36642E-5D, 2.83662E-5D, 1.99494E-4D, 3.90432E-5D, 1.83066E-4D, 2.35719E-5D, 1.25227E-4D, 1.10721E-4D, 9.52208E-5D, 0.0D};
        int var13 = var4.length;
        if (var0 >= var4[0] && var0 <= var4[var13 - 1]) {
            double var11 = CubicSpline.interpolate(var0, var4, var5, var6);
            if (var2 != 20.0D) {
                double var9 = Water.viscosity(20.0D);
                double var7 = Water.viscosity(var2);
                var11 = var11 * var7 / var9;
            }

            return var11;
        } else {
            throw new IllegalArgumentException("concentration outside the experimental data limits");
        }
    }

    public static double density(double var0) {
        double[] var2 = new double[]{0.0D, 0.017D, 0.034D, 0.051D, 0.069D, 0.086D, 0.103D, 0.12D, 0.137D, 0.155D, 0.172D, 0.189D, 0.207D, 0.224D, 0.241D, 0.259D, 0.276D, 0.294D, 0.311D, 0.329D, 0.346D, 0.364D, 0.382D, 0.399D, 0.418D, 0.435D, 0.452D, 0.47D, 0.488D, 0.505D, 0.523D, 0.541D, 0.559D, 0.577D, 0.595D, 0.653D, 0.631D, 0.649D, 0.667D, 0.685D, 0.703D, 0.721D, 0.739D, 0.757D, 0.775D, 0.794D, 0.812D, 0.83D, 0.848D, 0.866D, 0.885D, 0.921D, 0.958D, 0.995D, 1.032D, 1.069D, 1.106D, 1.144D, 1.181D, 1.218D, 1.256D, 1.294D, 1.331D, 1.369D, 1.407D, 1.445D, 1.484D, 1.522D, 1.56D, 1.599D, 1.637D, 1.676D, 1.715D, 1.754D, 1.193D, 1.832D, 1.93D, 2.029D, 2.129D, 2.229D, 2.33D, 2.432D, 2.534D, 2.637D, 2.741D, 2.845D, 3.056D, 3.27D, 3.486D, 3.706D, 3.928D, 4.153D, 4.382D, 4.613D, 4.848D, 5.085D, 5.326D};
        double[] var3 = new double[]{998.2D, 998.9D, 999.7D, 1000.4D, 1001.1D, 1001.8D, 1002.5D, 1003.2D, 1003.9D, 1004.6D, 1005.3D, 1006.0D, 1006.8D, 1007.5D, 1008.2D, 1008.9D, 1009.6D, 1010.3D, 1011.0D, 1011.7D, 1012.5D, 1013.2D, 1013.9D, 1014.6D, 1015.3D, 1016.0D, 1016.8D, 1017.5D, 1018.2D, 1018.9D, 1019.6D, 1020.3D, 1021.1D, 1021.8D, 1022.5D, 1023.2D, 1023.9D, 1024.6D, 1025.4D, 1026.5D, 1026.8D, 1027.5D, 1028.2D, 1029.0D, 1029.7D, 1030.4D, 1031.1D, 1031.8D, 1032.6D, 1033.3D, 1034.0D, 1035.5D, 1036.9D, 1038.4D, 1039.8D, 1041.3D, 1042.7D, 1044.2D, 1045.6D, 1047.1D, 1048.6D, 1050.0D, 1051.5D, 1053.0D, 1054.4D, 1055.9D, 1057.4D, 1058.8D, 1060.3D, 1061.8D, 1063.3D, 1064.7D, 1066.2D, 1067.7D, 1069.2D, 1070.7D, 1074.4D, 1078.1D, 1081.9D, 1085.7D, 1089.4D, 1093.2D, 1097.0D, 1100.8D, 1104.7D, 1108.5D, 1116.2D, 1124.0D, 1131.9D, 1139.8D, 1147.8D, 1155.8D, 1164.0D, 1172.1D, 1180.4D, 1188.7D, 1197.2D};
        double[] var4 = new double[]{0.0D, 685.75D, -666.876D, -94.3722D, 234.303D, -57.4711D, -4.41831D, 75.1444D, -296.159D, 318.233D, -189.411D, 439.409D, -440.601D, 195.58D, -341.719D, 381.664D, -402.356D, 441.731D, -585.487D, 1097.17D, -1014.34D, 185.143D, 273.774D, -515.957D, 341.504D, 660.063D, -905.631D, 175.193D, 204.858D, -221.651D, -94.0302D, 597.771D, -445.203D, -668.811D, 3120.45D, -10744.6D, -14968.4D, 3784.07D, 1683.98D, -4964.43D, 3358.94D, -1063.92D, 896.753D, -671.238D, -63.6541D, 237.473D, -226.825D, 669.828D, -600.633D, -119.146D, 386.711D, -314.64D, 244.402D, -224.691D, 216.087D, -201.381D, 151.163D, -142.321D, 156.457D, -45.2313D, -142.246D, 198.704D, -59.7198D, -126.19D, 148.967D, -54.1662D, -86.975D, 152.369D, -106.99D, 118.297D, -209.797D, 162.973D, -47.6169D, 27.4948D, 385.473D, -22.8199D, -2.39438D, 9.00548D, 4.10437D, -25.4229D, 15.9561D, -1.79451D, -8.77808D, 15.6492D, -18.5946D, 3.25592D, -1.85219D, 2.90469D, -6.24447D, 3.76304D, -5.36519D, 4.79201D, -7.11856D, 4.3037D, -3.58034D, 2.44792D, 0.0D};
        int var7 = var2.length;
        if (var0 >= var2[0] && var0 <= var2[var7 - 1]) {
            double var5 = CubicSpline.interpolate(var0, var2, var3, var4);
            return var5;
        } else {
            throw new IllegalArgumentException("concentration outside the experimental data limits");
        }
    }

    public static double refractIndex(double var0, double var2, double var4) {
        return RefrIndex.saline(var0, var2, var4);
    }

    public static double moleFraction(double var0) {
        double var2 = var0 * 1000.0D;
        double var4 = density(var0) * 1000.0D;
        double var6 = (var4 - var2 * 58.45D) / 18.02D;
        return var2 / (var6 + var2);
    }

    public static double molarToGperl(double var0) {
        return var0 * 58.45D;
    }

    public static double gperlToMolar(double var0) {
        return var0 / 58.45D;
    }
}
