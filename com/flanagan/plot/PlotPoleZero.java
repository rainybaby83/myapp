//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.plot;

import com.flanagan.complex.Complex;
import com.flanagan.complex.ComplexPoly;
import com.flanagan.io.FileOutput;
import com.flanagan.math.Fmath;

public class PlotPoleZero {
    private ComplexPoly numerPoly = null;
    private ComplexPoly denomPoly = null;
    private Complex[] numerRoots = null;
    private Complex[] denomRoots = null;
    private double[][] data = (double[][])null;
    private int nDeg = 0;
    private int dDeg = 0;
    private int mDeg = 0;
    private int sORz = 0;
    private boolean zerosSet = false;
    private boolean polesSet = false;
    private boolean zCircle = false;
    private boolean noImag = true;
    private boolean noReal = true;
    private boolean noZeros = true;
    private boolean noPoles = true;
    private boolean setUnitAxes = false;
    private boolean setEqualAxes = false;
    private double scaleFactor = 1.0D;

    public PlotPoleZero() {
    }

    public PlotPoleZero(ComplexPoly var1, ComplexPoly var2) {
        if (var1 != null) {
            this.nDeg = var1.getDeg();
            if (this.nDeg > 0) {
                this.numerPoly = ComplexPoly.copy(var1);
                this.numerRoots = Complex.oneDarray(this.nDeg);
                this.mDeg = this.nDeg;
                this.noZeros = false;
            }
        }

        if (var2 != null) {
            this.dDeg = var2.getDeg();
            if (this.dDeg > 0) {
                this.denomPoly = ComplexPoly.copy(var2);
                this.denomRoots = Complex.oneDarray(this.dDeg);
                if (!this.noZeros) {
                    this.mDeg = Math.max(this.nDeg, this.dDeg);
                } else {
                    this.mDeg = this.dDeg;
                }

                this.noPoles = false;
            }
        }

        if (this.noZeros && this.noPoles) {
            throw new IllegalArgumentException("No poles or zeros entered");
        }
    }

    public PlotPoleZero(Complex[] var1, Complex[] var2) {
        if (var1 != null) {
            this.nDeg = var1.length - 1;
            if (this.nDeg > 0) {
                this.numerPoly = new ComplexPoly(var1);
                this.numerRoots = Complex.oneDarray(this.nDeg);
                this.mDeg = this.nDeg;
                this.noZeros = false;
            }
        }

        if (var2 != null) {
            this.dDeg = var2.length - 1;
            if (this.dDeg > 0) {
                this.denomPoly = new ComplexPoly(var2);
                this.denomRoots = Complex.oneDarray(this.dDeg);
                if (!this.noZeros) {
                    this.mDeg = Math.max(this.nDeg, this.dDeg);
                } else {
                    this.mDeg = this.dDeg;
                }

                this.noPoles = false;
            }

            if (this.noZeros && this.noPoles) {
                throw new IllegalArgumentException("No poles or zeros entered");
            }
        }

    }

    public PlotPoleZero(double[] var1, double[] var2) {
        if (var1 != null) {
            this.nDeg = var1.length - 1;
            if (this.nDeg > 0) {
                this.numerPoly = new ComplexPoly(var1);
                this.numerRoots = Complex.oneDarray(this.nDeg);
                this.mDeg = this.nDeg;
                this.noZeros = false;
            }
        }

        if (var2 != null) {
            this.dDeg = var2.length - 1;
            if (this.dDeg > 0) {
                this.denomPoly = new ComplexPoly(var2);
                this.denomRoots = Complex.oneDarray(this.dDeg);
                if (!this.noZeros) {
                    this.mDeg = Math.max(this.nDeg, this.dDeg);
                } else {
                    this.mDeg = this.dDeg;
                }

                this.noPoles = false;
            }

            if (this.noZeros && this.noPoles) {
                throw new IllegalArgumentException("No poles or zeros entered");
            }
        }

    }

    public void setNumerator(ComplexPoly var1) {
        if (var1 != null) {
            this.nDeg = var1.getDeg();
            if (this.nDeg > 0) {
                this.numerPoly = ComplexPoly.copy(var1);
                this.numerRoots = Complex.oneDarray(this.nDeg);
                if (!this.noPoles) {
                    this.mDeg = Math.max(this.nDeg, this.dDeg);
                } else {
                    this.mDeg = this.nDeg;
                }

                this.noZeros = false;
            }
        } else {
            this.noZeros = true;
        }

    }

    public void setNumerator(Complex[] var1) {
        if (var1 != null) {
            this.nDeg = var1.length - 1;
            if (this.nDeg > 0) {
                this.numerPoly = new ComplexPoly(var1);
                this.numerRoots = Complex.oneDarray(this.nDeg);
                if (!this.noPoles) {
                    this.mDeg = Math.max(this.nDeg, this.dDeg);
                } else {
                    this.mDeg = this.nDeg;
                }

                this.noZeros = false;
            }
        } else {
            this.noZeros = true;
        }

    }

    public void setNumerator(double[] var1) {
        if (var1 != null) {
            this.nDeg = var1.length - 1;
            if (this.nDeg > 0) {
                this.numerPoly = new ComplexPoly(var1);
                this.numerRoots = Complex.oneDarray(this.nDeg);
                if (!this.noPoles) {
                    this.mDeg = Math.max(this.nDeg, this.dDeg);
                } else {
                    this.mDeg = this.nDeg;
                }

                this.noZeros = false;
            }
        } else {
            this.noZeros = true;
        }

    }

    public void setZeros(Complex[] var1) {
        if (var1 != null) {
            this.nDeg = var1.length;
            if (this.nDeg > 0) {
                this.numerRoots = var1;
                this.numerPoly = ComplexPoly.rootsToPoly(var1);
                if (!this.noPoles) {
                    this.mDeg = Math.max(this.nDeg, this.dDeg);
                } else {
                    this.mDeg = this.nDeg;
                }

                this.noZeros = false;
            }

            this.zerosSet = true;
        } else {
            this.noZeros = true;
        }

    }

    public void setZeros(double[] var1) {
        int var2 = var1.length;
        Complex[] var3 = Complex.oneDarray(var2);

        for(int var4 = 0; var4 < var2; ++var4) {
            var3[var4] = new Complex(var1[var4], 0.0D);
        }

        this.setZeros(var3);
    }

    public void setDenominator(ComplexPoly var1) {
        if (var1 != null) {
            this.dDeg = var1.getDeg();
            if (this.dDeg > 0) {
                this.denomPoly = ComplexPoly.copy(var1);
                this.denomRoots = Complex.oneDarray(this.dDeg);
                if (!this.noZeros) {
                    this.mDeg = Math.max(this.nDeg, this.dDeg);
                } else {
                    this.mDeg = this.dDeg;
                }

                this.noPoles = false;
            }
        } else {
            this.noPoles = true;
        }

    }

    public void setDenominator(Complex[] var1) {
        if (var1 != null) {
            this.dDeg = var1.length - 1;
            if (this.dDeg > 0) {
                this.denomPoly = new ComplexPoly(var1);
                this.denomRoots = Complex.oneDarray(this.dDeg);
                if (!this.noZeros) {
                    this.mDeg = Math.max(this.nDeg, this.dDeg);
                } else {
                    this.mDeg = this.dDeg;
                }

                this.noPoles = false;
            }
        } else {
            this.noPoles = true;
        }

    }

    public void setDenominator(double[] var1) {
        if (var1 != null) {
            this.dDeg = var1.length - 1;
            if (this.dDeg > 0) {
                this.denomPoly = new ComplexPoly(var1);
                this.denomRoots = Complex.oneDarray(this.dDeg);
                if (!this.noZeros) {
                    this.mDeg = Math.max(this.nDeg, this.dDeg);
                } else {
                    this.mDeg = this.dDeg;
                }

                this.noPoles = false;
            }
        } else {
            this.noPoles = true;
        }

    }

    public void setPoles(Complex[] var1) {
        if (var1 != null) {
            this.dDeg = var1.length;
            if (this.dDeg > 0) {
                this.denomRoots = var1;
                this.denomPoly = ComplexPoly.rootsToPoly(var1);
                if (!this.noZeros) {
                    this.mDeg = Math.max(this.nDeg, this.dDeg);
                } else {
                    this.mDeg = this.dDeg;
                }

                this.noPoles = false;
            }

            this.polesSet = true;
        } else {
            this.noPoles = true;
        }

    }

    public void setPoles(double[] var1) {
        int var2 = var1.length;
        Complex[] var3 = Complex.oneDarray(var2);

        for(int var4 = 0; var4 < var2; ++var4) {
            var3[var4] = new Complex(var1[var4], 0.0D);
        }

        this.setPoles(var3);
    }

    public void setScaleFactor(double var1) {
        this.scaleFactor = var1;
    }

    public void setS() {
        this.sORz = 1;
    }

    public void setZ() {
        this.sORz = 2;
        this.zCircle = true;
    }

    public void setUnitAxes() {
        this.setUnitAxes = true;
        this.setEqualAxes = false;
    }

    public void setEqualAxes() {
        this.setEqualAxes = true;
        this.setUnitAxes = false;
    }

    public void setCircle() {
        this.zCircle = true;
        if (this.sORz != 2) {
            this.sORz = 2;
        }

    }

    public void unsetCircle() {
        this.zCircle = false;
    }

    public Complex[][] pzPlot(String var1) {
        if (this.noPoles && this.noZeros) {
            throw new IllegalArgumentException("No poles or zeros have been entered");
        } else {
            double var2 = 0.0D;
            double var4 = 0.0D;
            double var6 = 1.0E-5D;
            double var8 = 0.0D;
            double var10 = 0.0D;
            short var12 = 600;
            double var13 = 2.0D / (double)(var12 - 1);
            int var15 = 0;
            double[] var16 = null;
            double[] var17 = null;
            double[] var18 = null;
            double[] var19 = null;
            double[] var20 = null;
            double[] var21 = null;
            double[] var22 = null;
            double[] var23 = null;
            double[] var24 = new double[var12];
            double[] var25 = new double[var12];
            double[] var26 = new double[var12];
            double[] var27 = new double[var12];
            Complex[][] var28 = new Complex[][]{null, null};
            int var29 = 0;
            int var30;
            if (this.nDeg > 0) {
                ++var29;
                var16 = new double[this.nDeg];
                var17 = new double[this.nDeg];
                if (!this.zerosSet) {
                    this.numerRoots = this.numerPoly.roots();
                }

                var28[0] = this.numerRoots;

                for(var30 = 0; var30 < this.nDeg; ++var30) {
                    var16[var30] = this.numerRoots[var30].getReal();
                    var17[var30] = this.numerRoots[var30].getImag();
                    if (!this.numerRoots[var30].isZero()) {
                        var2 = Math.abs(var16[var30]);
                        var4 = Math.abs(var17[var30]);
                        if (var2 > var4) {
                            if (var4 < var6 * var2) {
                                var17[var30] = 0.0D;
                            }
                        } else if (var2 < var6 * var4) {
                            var16[var30] = 0.0D;
                        }
                    }

                    if (var16[var30] != 0.0D) {
                        this.noReal = false;
                    }

                    if (var17[var30] != 0.0D) {
                        this.noImag = false;
                    }
                }

                var15 = this.nDeg;
            }

            if (this.dDeg > 0) {
                ++var29;
                var18 = new double[this.dDeg];
                var19 = new double[this.dDeg];
                if (!this.polesSet) {
                    this.denomRoots = this.denomPoly.roots();
                }

                var28[1] = this.denomRoots;

                for(var30 = 0; var30 < this.dDeg; ++var30) {
                    var18[var30] = this.denomRoots[var30].getReal();
                    var19[var30] = this.denomRoots[var30].getImag();
                    if (!this.denomRoots[var30].isZero()) {
                        var2 = Math.abs(var18[var30]);
                        var4 = Math.abs(var19[var30]);
                        if (var2 > var4) {
                            if (var4 < var6 * var2) {
                                var19[var30] = 0.0D;
                            }
                        } else if (var2 < var6 * var4) {
                            var18[var30] = 0.0D;
                        }
                    }

                    if (var18[var30] != 0.0D) {
                        this.noReal = false;
                    }

                    if (var19[var30] != 0.0D) {
                        this.noImag = false;
                    }
                }

                if (this.dDeg > var15) {
                    var15 = this.dDeg;
                }
            }

            if (this.noReal) {
                ++var29;
                var20 = new double[]{1.0D, -1.0D};
                var21 = new double[]{0.0D, 0.0D};
                if (2 > var15) {
                    var15 = 2;
                }
            }

            if (this.noImag) {
                ++var29;
                var22 = new double[]{0.0D, 0.0D};
                var23 = new double[]{1.0D, -1.0D};
                if (2 > var15) {
                    var15 = 2;
                }
            }

            if (this.zCircle) {
                var29 += 2;
                var24[0] = -1.0D;
                var25[0] = 0.0D;
                var26[0] = -1.0D;
                var27[0] = 0.0D;

                for(var30 = 1; var30 < var12; ++var30) {
                    var24[var30] = var24[var30 - 1] + var13;
                    var25[var30] = Math.sqrt(1.0D - var24[var30] * var24[var30]);
                    var26[var30] = var26[var30 - 1] + var13;
                    var27[var30] = -var25[var30];
                }

                if (var12 > var15) {
                    var15 = var12;
                }
            }

            if (this.setEqualAxes) {
                ++var29;
                double var54 = Fmath.maximum(var18);
                double var32 = Fmath.maximum(var16);
                double var34 = Math.max(var54, var32);
                double var36 = Fmath.maximum(var19);
                double var38 = Fmath.maximum(var17);
                double var40 = Math.max(var36, var38);
                var10 = Math.max(var34, var40);
                double var42 = Fmath.minimum(var18);
                double var44 = Fmath.minimum(var16);
                double var46 = Math.min(var42, var44);
                double var48 = Fmath.minimum(var19);
                double var50 = Fmath.minimum(var17);
                double var52 = Math.min(var48, var50);
                var8 = Math.min(var46, var52);
            }

            boolean var55 = false;
            double[][] var31 = PlotGraph.fillData(var29, var15);
            boolean[] var56 = new boolean[var29];
            boolean[] var33 = new boolean[var29];
            int[] var57 = new int[var29];
            int[] var35 = new int[var29];
            var30 = 0;
            int var58;
            if (this.nDeg > 0) {
                var57[var30] = 0;
                var35[var30] = 1;
                var56[var30] = false;
                var33[var30] = true;

                for(var58 = 0; var58 < this.nDeg; ++var58) {
                    var31[2 * var30][var58] = var16[var58];
                    var31[2 * var30 + 1][var58] = var17[var58];
                }

                ++var30;
            }

            if (this.dDeg > 0) {
                var57[var30] = 0;
                var35[var30] = 7;
                var56[var30] = false;
                var33[var30] = true;

                for(var58 = 0; var58 < this.dDeg; ++var58) {
                    var31[2 * var30][var58] = var18[var58];
                    var31[2 * var30 + 1][var58] = var19[var58];
                }

                ++var30;
            }

            if (this.zCircle) {
                var57[var30] = 3;
                var35[var30] = 0;
                var56[var30] = true;
                var33[var30] = false;
                if (this.setUnitAxes) {
                    var33[var30] = true;
                }

                for(var58 = 0; var58 < var12; ++var58) {
                    var31[2 * var30][var58] = var24[var58];
                    var31[2 * var30 + 1][var58] = var25[var58];
                }

                ++var30;
                var57[var30] = 3;
                var35[var30] = 0;
                var56[var30] = true;
                var33[var30] = false;
                if (this.setUnitAxes) {
                    var33[var30] = true;
                }

                for(var58 = 0; var58 < var12; ++var58) {
                    var31[2 * var30][var58] = var26[var58];
                    var31[2 * var30 + 1][var58] = var27[var58];
                }

                ++var30;
            }

            if (this.noReal) {
                var57[var30] = 0;
                var35[var30] = 0;
                var56[var30] = false;
                var33[var30] = true;

                for(var58 = 0; var58 < 2; ++var58) {
                    var31[2 * var30][var58] = var20[var58];
                    var31[2 * var30 + 1][var58] = var21[var58];
                }

                ++var30;
            }

            if (this.noImag) {
                var57[var30] = 0;
                var35[var30] = 0;
                var56[var30] = false;
                var33[var30] = true;

                for(var58 = 0; var58 < 2; ++var58) {
                    var31[2 * var30][var58] = var22[var58];
                    var31[2 * var30 + 1][var58] = var23[var58];
                }

                ++var30;
            }

            if (this.setEqualAxes) {
                var57[var30] = 0;
                var35[var30] = 0;
                var56[var30] = false;
                var33[var30] = true;
                var31[2 * var30][0] = var8;
                var31[2 * var30 + 1][0] = var8;
                var31[2 * var30][1] = var10;
                var31[2 * var30 + 1][1] = var10;
                ++var30;
            }

            PlotGraph var60 = new PlotGraph(var31);
            var60.setLine(var57);
            var60.setPoint(var35);
            var60.setTrimOpt(var56);
            var60.setMinMaxOpt(var33);
            var60.setXlowFac(0.0D);
            var60.setYlowFac(0.0D);
            var60.setGraphWidth((int)(this.scaleFactor * 760.0D));
            var60.setGraphHeight((int)(this.scaleFactor * 700.0D));
            var60.setXaxisLen((int)(this.scaleFactor * 560.0D));
            var60.setYaxisLen((int)(this.scaleFactor * 560.0D));
            var60.setYhigh((int)(this.scaleFactor * 80.0D));
            var60.setNoOffset(true);
            switch(this.sORz) {
                case 0:
                    var60.setGraphTitle("Pole Zero Plot: " + var1);
                    var60.setXaxisLegend("Real part of s or z");
                    var60.setYaxisLegend("Imaginary part of s or z");
                    break;
                case 1:
                    var60.setGraphTitle("Pole Zero Plot (s-plane): " + var1);
                    var60.setXaxisLegend("Real part of s");
                    var60.setYaxisLegend("Imaginary part of s");
                    break;
                case 2:
                    var60.setGraphTitle("Pole Zero Plot (z-plane): " + var1);
                    var60.setXaxisLegend("Real part of z");
                    var60.setYaxisLegend("Imaginary part of z");
            }

            var60.plot();
            Complex[] var37 = null;
            Complex[] var59 = null;
            FileOutput var39 = new FileOutput("PoleZeroOutput.txt");
            var39.println("Output File for Program PlotPoleZero");
            if (this.sORz == 1) {
                var39.println("An s-plane plot");
            }

            if (this.sORz == 2) {
                var39.println("A z-plane plot");
            }

            var39.dateAndTimeln(var1);
            var39.println();
            int var61;
            if (!this.noZeros) {
                var37 = this.numerPoly.polyNomCopy();
                var39.println("Numerator polynomial coefficients");

                for(var61 = 0; var61 <= this.nDeg; ++var61) {
                    var39.print(var37[var61].toString());
                    if (var61 < this.nDeg) {
                        var39.printcomma();
                        var39.printsp();
                    }
                }

                var39.println();
                var39.println();
            }

            if (!this.noPoles) {
                var59 = this.denomPoly.polyNomCopy();
                var39.println("Denominator polynomial coefficients");

                for(var61 = 0; var61 <= this.dDeg; ++var61) {
                    var39.print(var59[var61].toString());
                    if (var61 < this.dDeg) {
                        var39.printcomma();
                        var39.printsp();
                    }
                }

                var39.println();
                var39.println();
            }

            var39.println("Numerator roots (zeros)");
            if (this.nDeg < 1) {
                var39.println("No zeros");
            } else {
                for(var61 = 0; var61 < this.nDeg; ++var61) {
                    var39.print(Complex.truncate(this.numerRoots[var61], 6));
                    if (var61 < this.nDeg - 1) {
                        var39.printcomma();
                        var39.printsp();
                    }
                }

                var39.println();
                var39.println();
            }

            var39.println("Denominator roots (poles)");
            if (this.dDeg < 1) {
                var39.println("No poles");
            } else {
                for(var61 = 0; var61 < this.dDeg; ++var61) {
                    var39.print(Complex.truncate(this.denomRoots[var61], 6));
                    if (var61 < this.dDeg - 1) {
                        var39.printcomma();
                        var39.printsp();
                    }
                }

                var39.println();
                var39.println();
            }

            if (this.sORz == 2) {
                var39.println("Denominator pole radial distances on the z-plane");
                if (this.dDeg < 1) {
                    var39.println("No poles");
                } else {
                    for(var61 = 0; var61 < this.dDeg; ++var61) {
                        var39.print(Fmath.truncate(this.denomRoots[var61].abs(), 6));
                        if (var61 < this.dDeg - 1) {
                            var39.printcomma();
                            var39.printsp();
                        }
                    }
                }

                var39.println();
                var39.println();
            }

            boolean var62 = true;
            int var41;
            if (this.sORz == 1) {
                for(var41 = 0; var41 < this.dDeg; ++var41) {
                    if (this.denomRoots[var41].getReal() > 0.0D) {
                        var62 = false;
                    }
                }

                if (var62) {
                    var39.println("All pole real parts are less than or equal to zero - stable system");
                } else {
                    var39.println("At least one pole real part is greater than zero - unstable system");
                }
            }

            if (this.sORz == 2) {
                for(var41 = 0; var41 < this.dDeg; ++var41) {
                    if (Fmath.truncate(this.denomRoots[var41].abs(), 6) > 1.0D) {
                        var62 = false;
                    }
                }

                if (var62) {
                    var39.println("All pole distances from the z-plane zero are less than or equal to one - stable system");
                } else {
                    var39.println("At least one pole distance from the z-plane zero is greater than one - unstable system");
                }
            }

            var39.println();
            var39.println("End of file");
            var39.close();
            return var28;
        }
    }

    public Complex[][] pzPlot() {
        String var1 = "no file title provided";
        return this.pzPlot(var1);
    }
}
