//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package flanagan.physprop;

import java.awt.Component;
import javax.swing.Icon;
import javax.swing.JOptionPane;

public class IonicRadii {
    private static String[] ions1 = new String[]{"Ag+", "Al+++", "Au+", "Au+++", "Ba++", "Be++", "Bi+++", "Ca++", "Cd++", "Ce+++", "Ce++++", "Co++ ls", "Co++ hs", "Co+++ ls", "Co+++ hs", "Cr++ ls", "Cr++ hs", "Cr+++", "Cs+", "Cu+", "Cu++", "Dy+++", "Er+++", "Eu++", "Eu+++", "Fe++ ls", "Fe++ hs", "Fe+++ ls", "Fe+++ hs", "Ga+++", "Gd+++", "Hf++++", "Hg+", "Hg++", "Ho+++", "In+++", "Ir+++", "K+", "La+++", "Li+", "Lu+++", "Mg++", "Mn++ ls", "Mn++ hs", "Mn+++ ls", "Mn+++ hs", "Mo+++", "Na+", "Nb+++", "Nd+++", "Ni++", "Pb++", "Pd++", "Pm+++", "Pr+++", "Pt++", "Rb+", "Rh+++", "Ru+++", "Sb+++", "Sc+++", "Sm+++", "Sr++", "Ta+++", "Tb+++", "Th++++", "Ti++", "Ti+++", "Ti++++", "Tl+", "Tl+++", "Tm+++", "U+++", "U++++", "V++", "V+++", "Y+++", "Yb++", "Yb+++", "Zn++", "Zr++++", "Br-", "Cl-", "F-", "H-", "I-", "O--", "S--", "Se--", "Te--", "OH-"};
    private static String[] ions2 = new String[]{"Ag1+", "Al3+", "Au1+", "Au3+", "Ba2+", "Be2+", "Bi3+", "Ca2+", "Cd2+", "Ce3+", "Ce4+", "Co2+ ls", "Co2+ hs", "Co3+ ls", "Co3+ hs", "Cr2+ ls", "Cr2+ hs", "Cr3+", "Cs1+", "Cu1+", "Cu2+", "Dy3+", "Er3+", "Eu2+", "Eu3+", "Fe2+ ls", "Fe2+ hs", "Fe3+ ls", "Fe3+ hs", "Ga3+", "Gd3+", "Hf4+", "Hg1+", "Hg2+", "Ho3+", "In3+", "Ir3+", "K1+", "La3+", "Li1+", "Lu3+", "Mg2+", "Mn2+ ls", "Mn2+ hs", "Mn3+ ls", "Mn3+ hs", "Mo3+", "Na1+", "Nb3+", "Nd3+", "Ni2+", "Pb2+", "Pd2+", "Pm3+", "Pr3+", "Pt2+", "Rb1+", "Rh3+", "Ru3+", "Sb3+", "Sc3+", "Sm3+", "Sr2+", "Ta3+", "Tb3+", "Th4+", "Ti2+", "Ti3+", "Ti4+", "Tl1+", "Tl3+", "Tm3+", "U3+", "U4+", "V2+", "V3+", "Y3+", "Yb2+", "Yb3+", "Zn2+", "Zr4+", "Br1-", "Cl1-", "F1-", "H1-", "I1-", "O2-", "S2-", "Se2-", "Te2-", "OH1-"};
    private static String[] ions3 = new String[]{"Ag+1", "Al+3", "Au+1", "Au+3", "Ba+2", "Be+2", "Bi+3", "Ca+2", "Cd+2", "Ce+3", "Ce+4", "Co+2 ls", "Co+2 hs", "Co+3 ls", "Co+3 hs", "Cr+2 ls", "Cr+2 hs", "Cr+3", "Cs+1", "Cu+1", "Cu+2", "Dy+3", "Er+3", "Eu+2", "Eu+3", "Fe+2 ls", "Fe+2 hs", "Fe+3 ls", "Fe+3 hs", "Ga+3", "Gd+3", "Hf+4", "Hg+1", "Hg+2", "Ho+3", "In+3", "Ir+3", "K+1", "La+3", "Li+1", "Lu+3", "Mg+2", "Mn+2 ls", "Mn+2 hs", "Mn+3 ls", "Mn+3 hs", "Mo+3", "Na+1", "Nb+3", "Nd+3", "Ni+2", "Pb+2", "Pd+2", "Pm+3", "Pr+3", "Pt+2", "Rb+1", "Rh+3", "Ru+3", "Sb+3", "Sc+3", "Sm+3", "Sr+2", "Ta+3", "Tb+3", "Th+4", "Ti+2", "Ti+3", "Ti+4", "Tl+1", "Tl+3", "Tm+3", "U+3", "U+4", "V+2", "V+3", "Y+3", "Yb+2", "Yb+3", "Zn+2", "Zr+4", "Br-1", "Cl-1", "F-1", "H-1", "I-1", "O-2", "S-2", "Se-2", "Te-2", "OH-1"};
    private static String[] ions4 = new String[]{"Ag(+)", "Al(+++)", "Au(+)", "Au(+++)", "Ba(++)", "Be(++)", "Bi(+++)", "Ca(++)", "Cd(++)", "Ce(+++)", "Ce(++++)", "Co(++) ls", "Co(++) hs", "Co(+++) ls", "Co(+++) hs", "Cr(++) ls", "Cr(++) hs", "Cr(+++)", "Cs(+)", "Cu(+)", "Cu(++)", "Dy(+++)", "Er(+++)", "Eu(++)", "Eu(+++)", "Fe(++) ls", "Fe(++) hs", "Fe(+++) ls", "Fe(+++) hs", "Ga(+++)", "Gd(+++)", "Hf(++++)", "Hg(+)", "Hg(++)", "Ho(+++)", "In(+++)", "Ir(+++)", "K(+)", "La(+++)", "Li(+)", "Lu(+++)", "Mg(++)", "Mn(++) ls", "Mn(++) hs", "Mn(+++) ls", "Mn(+++) hs", "Mo(+++)", "Na(+)", "Nb(+++)", "Nd(+++)", "Ni(++)", "Pb(++)", "Pd(++)", "Pm(+++)", "Pr(+++)", "Pt(++)", "Rb(+)", "Rh(+++)", "Ru(+++)", "Sb(+++)", "Sc(+++)", "Sm(+++)", "Sr(++)", "Ta(+++)", "Tb(+++)", "Th(++++)", "Ti(++)", "Ti(+++)", "Ti(++++)", "Tl(+)", "Tl(+++)", "Tm(+++)", "U(+++)", "U(++++)", "V(++)", "V(+++)", "Y(+++)", "Yb(++)", "Yb(+++)", "Zn(++)", "Zr(++++)", "Br(-)", "Cl(-)", "F(-)", "H(-)", "I(-)", "O(--)", "S(--)", "Se(--)", "Te(--)", "OH(-)"};
    private static String[] ions5 = new String[]{"Ag(1+)", "Al(3+)", "Au(1+)", "Au(3+)", "Ba(2+)", "Be(2+)", "Bi(3+)", "Ca(2+)", "Cd(2+)", "Ce(3+)", "Ce(4+)", "Co(2+) ls", "Co(2+) hs", "Co(3+) ls", "Co(3+) hs", "Cr(2+) ls", "Cr(2+) hs", "Cr(3+)", "Cs(1+)", "Cu(1+)", "Cu(2+)", "Dy(3+)", "Er(3+)", "Eu(2+)", "Eu(3+)", "Fe(2+) ls", "Fe(2+) hs", "Fe(3+) ls", "Fe(3+) hs", "Ga(3+)", "Gd(3+)", "Hf(4+)", "Hg(1+)", "Hg(2+)", "Ho(3+)", "In(3+)", "Ir(3+)", "K(1+)", "La(3+)", "Li(1+)", "Lu(3+)", "Mg(2+)", "Mn(2+) ls", "Mn(2+) hs", "Mn(3+) ls", "Mn(3+) hs", "Mo(3+)", "Na(1+)", "Nb(3+)", "Nd(3+)", "Ni(2+)", "Pb(2+)", "Pd(2+)", "Pm(3+)", "Pr(3+)", "Pt(2+)", "Rb(1+)", "Rh(3+)", "Ru(3+)", "Sb(3+)", "Sc(3+)", "Sm(3+)", "Sr(2+)", "Ta(3+)", "Tb(3+)", "Th(4+)", "Ti(2+)", "Ti(3+)", "Ti(4+)", "Tl(1+)", "Tl(3+)", "Tm(3+)", "U(3+)", "U(4+)", "V(2+)", "V(3+)", "Y(3+)", "Yb(2+)", "Yb(3+)", "Zn(2+)", "Zr(4+)", "Br(1-)", "Cl(1-)", "F(1-)", "H(1-)", "I(1-)", "O(2-)", "S(2-)", "Se(2-)", "Te(2-)", "OH(1-)"};
    private static String[] ions6 = new String[]{"Ag(+1)", "Al(+3)", "Au(+1)", "Au(+3)", "Ba(+2)", "Be(+2)", "Bi(+3)", "Ca(+2)", "Cd(+2)", "Ce(+3)", "Ce(+4)", "Co(+2) ls", "Co(+2) hs", "Co(+3) ls", "Co(+3) hs", "Cr(+2) ls", "Cr(+2) hs", "Cr(+3)", "Cs(+1)", "Cu(+1)", "Cu(+2)", "Dy(+3)", "Er(+3)", "Eu(+2)", "Eu(+3)", "Fe(+2) ls", "Fe(+2) hs", "Fe(+3) ls", "Fe(+3) hs", "Ga(+3)", "Gd(+3)", "Hf(+4)", "Hg(+1)", "Hg(+2)", "Ho(+3)", "In(+3)", "Ir(+3)", "K(+1)", "La(+3)", "Li(+1)", "Lu(+3)", "Mg(+2)", "Mn(+2) ls", "Mn(+2) hs", "Mn(+3) ls", "Mn(+3) hs", "Mo(+3)", "Na(+1)", "Nb(+3)", "Nd(+3)", "Ni(+2)", "Pb(+2)", "Pd(+2)", "Pm(+3)", "Pr(+3)", "Pt(+2)", "Rb(+1)", "Rh(+3)", "Ru(+3)", "Sb(+3)", "Sc(+3)", "Sm(+3)", "Sr(+2)", "Ta(+3)", "Tb(+3)", "Th(+4)", "Ti(+2)", "Ti(+3)", "Ti(+4)", "Tl(+1)", "Tl(+3)", "Tm(+3)", "U(+3)", "U(+4)", "V(+2)", "V(+3)", "Y(+3)", "Yb(+2)", "Yb(+3)", "Zn(+2)", "Zr(+4)", "Br(-1)", "Cl(-1)", "F(-1)", "H(-1)", "I(-1)", "O(-2)", "S(-2)", "Se(-2)", "Te(-2)", "OH(-1)"};
    private static boolean[] spins = new boolean[]{false, false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, true, false, false, false, false, false, false, false, false, true, true, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false};
    private static int[] ionCharge = new int[]{1, 3, 1, 3, 2, 2, 3, 2, 2, 3, 4, 2, 2, 3, 3, 2, 2, 3, 1, 1, 2, 3, 3, 2, 3, 2, 2, 3, 3, 3, 3, 4, 1, 2, 3, 3, 3, 1, 3, 1, 3, 2, 2, 2, 3, 3, 3, 1, 3, 3, 2, 2, 2, 3, 3, 2, 1, 3, 3, 3, 3, 3, 2, 3, 3, 4, 2, 3, 4, 1, 3, 3, 3, 4, 2, 3, 3, 2, 3, 2, 4, -1, -1, -1, -1, -1, -2, -2, -2, -2, -1};
    private static double[] radii = new double[]{129.0D, 67.5D, 151.0D, 99.0D, 149.0D, 59.0D, 117.0D, 114.0D, 109.0D, 115.0D, 101.0D, 79.0D, 88.5D, 68.5D, 75.0D, 87.0D, 94.0D, 75.5D, 181.0D, 91.0D, 87.0D, 105.2D, 103.0D, 131.0D, 108.7D, 75.0D, 92.0D, 69.0D, 78.5D, 76.0D, 107.8D, 85.0D, 133.0D, 116.0D, 104.1D, 94.0D, 82.0D, 152.0D, 117.2D, 90.0D, 100.1D, 86.0D, 81.0D, 97.0D, 72.0D, 78.5D, 83.0D, 116.0D, 86.0D, 112.3D, 83.0D, 133.0D, 100.0D, 111.0D, 113.0D, 94.0D, 166.0D, 80.5D, 82.0D, 90.0D, 88.5D, 109.8D, 132.0D, 86.0D, 106.3D, 108.0D, 100.0D, 81.0D, 74.5D, 164.0D, 102.5D, 102.0D, 116.5D, 103.0D, 93.0D, 78.0D, 104.0D, 116.0D, 100.8D, 88.0D, 86.0D, 167.0D, 182.0D, 119.0D, 139.0D, 206.0D, 126.0D, 170.0D, 184.0D, 207.0D, 120.0D};
    private static String[] hydratedIons1 = new String[]{"Ag+", "Al+++", "Be++", "Ca++", "Cd++", "Cs+", "K+", "Li+", "Mg++", "Na+", "Pb++", "Rb+", "Tl+", "Zn++", "H30+", "NH4+", "Cl-", "Br-", "F-", "I-", "NO3-", "OH-"};
    private static String[] hydratedIons2 = new String[]{"Ag1+", "Al3+", "Be2+", "Ca2+", "Cd2+", "Cs1+", "K1+", "Li1+", "Mg2+", "Na1+", "Pb2+", "Rb1+", "Tl1+", "Zn2+", "H301+", "NH41+", "Cl1-", "Br1-", "F1-", "I1-", "NO31-", "OH1-"};
    private static String[] hydratedIons3 = new String[]{"Ag+1", "Al+3", "Be+2", "Ca+2", "Cd+2", "Cs+1", "K+1", "Li+1", "Mg+2", "Na+1", "Pb+2", "Rb+1", "Tl+1", "Zn+2", "H30+1", "NH4+1", "Cl-1", "Br-1", "F-1", "I-1", "NO3-1", "OH-1"};
    private static String[] hydratedIons4 = new String[]{"Ag(+)", "Al(+++)", "Be(++)", "Ca(++)", "Cd(++)", "Cs(+)", "K(+)", "Li(+)", "Mg(++)", "Na(+)", "Pb(++)", "Rb(+)", "Tl(+)", "Zn(++)", "H30(+)", "NH4(+)", "Cl(-)", "Br(-)", "F(-)", "I(-)", "NO3(-)", "OH(-)"};
    private static String[] hydratedIons5 = new String[]{"Ag(1+)", "Al(3+)", "Be(2+)", "Ca(2+)", "Cd(2+)", "Cs(1+)", "K(1+)", "Li(1+)", "Mg(2+)", "Na(1+)", "Pb(2+)", "Rb(1+)", "Tl(1+)", "Zn(2+)", "H30(1+)", "NH4(1+)", "Cl(1-)", "Br(1-)", "F(1-)", "I(1-)", "NO3(1-)", "OH(1-)"};
    private static String[] hydratedIons6 = new String[]{"Ag(+1)", "Al(+3)", "Be(+2)", "Ca(+2)", "Cd(+2)", "Cs(+1)", "K(+1)", "Li(+1)", "Mg(+2)", "Na(+1)", "Pb(+2)", "Rb(+1)", "Tl(+1)", "Zn(+2)", "H30(+1)", "NH4(+1)", "Cl(-1)", "Br(-1)", "F(-1)", "I(-1)", "NO3(-1)", "OH(-1)"};
    private static double[] hydratedRadii = new double[]{341.0D, 480.0D, 459.0D, 412.0D, 426.0D, 329.0D, 331.0D, 382.0D, 428.0D, 358.0D, 401.0D, 329.0D, 330.0D, 430.0D, 280.0D, 331.0D, 332.0D, 332.0D, 330.0D, 352.0D, 340.0D, 300.0D};
    private static int nIons = 91;
    private static int nHydratedIons = 22;

    public IonicRadii() {
    }

    public static double radius(String var0, String var1) {
        boolean var2 = false;
        var1 = var1.trim();
        if (!var1.equals("ls") && !var1.equals("low") && !var1.equals("low spin") && !var1.equals("LS")) {
            if (!var1.equals("hs") && !var1.equals("high") && !var1.equals("high spin") && !var1.equals("HS")) {
                throw new IllegalArgumentException("spin state must be entered as ls or hs not as " + var1);
            }

            var1 = "hs";
        } else {
            var1 = "ls";
        }

        var2 = true;
        var0 = var0.trim();
        String var3 = var0 + " " + var1;
        return radiusCalc(var3, var2);
    }

    public static double radius(String var0) {
        boolean var1 = false;
        return radiusCalc(var0, var1);
    }

    private static double radiusCalc(String var0, boolean var1) {
        String var2 = var0.trim();
        if (!var1) {
            var0 = var2;
        }

        boolean var3 = true;
        boolean var4 = false;
        int var5 = 0;
        double var6 = 0.0D;

        while(var3) {
            if (compareBare(var2, var5)) {
                var3 = false;
                var4 = true;
                var6 = radii[var5] * 1.0E-12D;
            } else {
                ++var5;
                if (var5 >= nIons) {
                    var3 = false;
                }
            }
        }

        if (!var4 && !var1) {
            var3 = true;
            var5 = 0;

            label73:
            while(true) {
                while(true) {
                    if (!var3) {
                        break label73;
                    }

                    if (compareSubstringBare(var0, var5) && spins[var5]) {
                        var3 = false;
                        var4 = true;
                        boolean var8 = true;
                        String var9 = var0 + " may be low spin or high spin";
                        Object[] var10 = new Object[]{"low spin", "high spin"};

                        while(var8) {
                            int var11 = JOptionPane.showOptionDialog((Component)null, "Click appropriate box", var9, 1, 3, (Icon)null, var10, var10[0]);
                            if (var11 == 0) {
                                var6 = radii[var5] * 1.0E-12D;
                                var8 = false;
                            } else if (var11 == 1) {
                                var6 = radii[var5 + 1] * 1.0E-12D;
                                var8 = false;
                            } else {
                                System.out.println("You must click either low spin or high spin");
                            }
                        }
                    } else {
                        ++var5;
                        if (var5 >= nIons) {
                            var3 = false;
                        }
                    }
                }
            }
        }

        if (!var4 && var1) {
            var3 = true;
            var5 = 0;

            while(var3) {
                if (compareBare(var0, var5)) {
                    var3 = false;
                    var4 = true;
                    var6 = radii[var5] * 1.0E-12D;
                    System.out.println(var0 + " does not have low and high spin states listed");
                    System.out.println("Single availabe listed radius was used");
                } else {
                    ++var5;
                    if (var5 >= nIons) {
                        var3 = false;
                    }
                }
            }
        }

        if (!var4) {
            System.out.println("Class: IonicRadii\nMethod: radius\n" + var2 + " was not found in the lists of non-hydrated radii");
            System.out.println("0.0D returned");
        }

        var1 = false;
        return var6;
    }

    public static boolean compareBare(String var0, int var1) {
        boolean var2 = false;
        if (var0.equals(ions1[var1]) || var0.equals(ions2[var1]) || var0.equals(ions3[var1]) || var0.equals(ions4[var1]) || var0.equals(ions5[var1]) || var0.equals(ions6[var1])) {
            var2 = true;
        }

        return var2;
    }

    public static boolean compareSubstringBare(String var0, int var1) {
        boolean var2 = false;
        if (ions1[var1].indexOf(var0) > -1 || ions2[var1].indexOf(var0) > -1 || ions3[var1].indexOf(var0) > -1 || ions4[var1].indexOf(var0) > -1 || ions5[var1].indexOf(var0) > -1 || ions6[var1].indexOf(var0) > -1) {
            var2 = true;
        }

        return var2;
    }

    public static double hydratedRadius(String var0) {
        var0 = var0.trim();
        boolean var1 = true;
        boolean var2 = false;
        int var3 = 0;
        double var4 = 0.0D;

        while(var1) {
            if (compareHydrated(var0, var3)) {
                var1 = false;
                var2 = true;
                var4 = hydratedRadii[var3] * 1.0E-12D;
            } else {
                ++var3;
                if (var3 >= nHydratedIons) {
                    var1 = false;
                }
            }
        }

        if (!var2) {
            System.out.println("Class: IonicRadii\nMethod: hydratedRadius\n" + var0 + " was not found in the lists of hydrated radii");
            System.out.println("0.0D returned");
        }

        return var4;
    }

    public static boolean compareHydrated(String var0, int var1) {
        boolean var2 = false;
        if (var0.equals(hydratedIons1[var1]) || var0.equals(hydratedIons2[var1]) || var0.equals(hydratedIons3[var1]) || var0.equals(hydratedIons4[var1]) || var0.equals(hydratedIons5[var1]) || var0.equals(hydratedIons6[var1])) {
            var2 = true;
        }

        return var2;
    }

    public static boolean compareSubstringHydrated(String var0, int var1) {
        boolean var2 = false;
        if (hydratedIons1[var1].indexOf(var0) > -1 || hydratedIons2[var1].indexOf(var0) > -1 || hydratedIons3[var1].indexOf(var0) > -1 || hydratedIons4[var1].indexOf(var0) > -1 || hydratedIons5[var1].indexOf(var0) > -1 || hydratedIons6[var1].indexOf(var0) > -1) {
            var2 = true;
        }

        return var2;
    }

    public static int charge(String var0) {
        var0 = var0.trim();
        boolean var1 = true;
        boolean var2 = false;
        int var3 = 0;
        int var4 = 0;

        while(var1) {
            if (compareBare(var0, var3)) {
                var1 = false;
                var2 = true;
                var4 = ionCharge[var3];
            } else {
                ++var3;
                if (var3 >= nIons) {
                    var1 = false;
                }
            }
        }

        if (!var2) {
            System.out.println("Class: IonicRadii\nMethod: charge\n" + var0 + " was not found in the lists of non-hydrated ions");
            System.out.println("0 returned");
        }

        return var4;
    }
}
