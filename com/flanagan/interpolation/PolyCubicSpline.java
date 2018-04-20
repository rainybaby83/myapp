//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.interpolation;

import com.flanagan.math.Fmath;
import java.lang.reflect.Array;

public class PolyCubicSpline {
    private int nDimensions = 0;
    private Object fOfX = null;
    private Object internalDeriv = null;
    private Object xArrays = null;
    private Object method = null;
    private double[][] xArray = (double[][])null;
    private double[] csArray = null;
    private PolyCubicSpline[] pcs = null;
    private int dimOne = 0;
    private double yValue = 0.0D;
    private double[] xMin = null;
    private double[] xMax = null;
    private boolean calculationDone = false;
    private boolean averageIdenticalAbscissae = false;
    private static double potentialRoundingError = 5.0E-15D;
    private static boolean roundingCheck = true;

    public PolyCubicSpline(Object var1, Object var2) {
        this.fOfX = Fmath.copyObject(var2);
        this.xArrays = Fmath.copyObject(var1);
        Object var3 = Fmath.copyObject(var2);

        for(this.nDimensions = 1; !((var3 = Array.get(var3, 0)) instanceof Double); ++this.nDimensions) {
            ;
        }

        if (this.xArrays instanceof double[] && this.nDimensions == 1) {
            double[][] var4 = new double[][]{(double[])((double[])this.xArrays)};
            this.xArrays = var4;
        } else if (!(this.xArrays instanceof double[][])) {
            throw new IllegalArgumentException("xArrays should be a two dimensional array of doubles");
        }

        this.xArray = (double[][])((double[][])this.xArrays);
        this.limits();
        switch(this.nDimensions) {
            case 0:
                throw new IllegalArgumentException("data array must have at least one dimension");
            case 1:
                CubicSpline var13 = new CubicSpline(this.xArray[0], (double[])((double[])this.fOfX));
                if (this.averageIdenticalAbscissae) {
                    CubicSpline.averageIdenticalAbscissae();
                }

                this.internalDeriv = var13.getDeriv();
                this.method = var13;
                this.calculationDone = true;
                break;
            case 2:
                BiCubicSpline var5 = new BiCubicSpline(this.xArray[0], this.xArray[1], (double[][])((double[][])this.fOfX));
                if (this.averageIdenticalAbscissae) {
                    var5.averageIdenticalAbscissae();
                }

                this.internalDeriv = var5.getDeriv();
                this.method = var5;
                this.calculationDone = true;
                break;
            case 3:
                TriCubicSpline var6 = new TriCubicSpline(this.xArray[0], this.xArray[1], this.xArray[2], (double[][][])((double[][][])this.fOfX));
                if (this.averageIdenticalAbscissae) {
                    var6.averageIdenticalAbscissae();
                }

                this.internalDeriv = var6.getDeriv();
                this.method = var6;
                this.calculationDone = true;
                break;
            case 4:
                QuadriCubicSpline var7 = new QuadriCubicSpline(this.xArray[0], this.xArray[1], this.xArray[2], this.xArray[3], (double[][][][])((double[][][][])this.fOfX));
                if (this.averageIdenticalAbscissae) {
                    var7.averageIdenticalAbscissae();
                }

                this.internalDeriv = var7.getDeriv();
                this.method = var7;
                this.calculationDone = true;
                break;
            default:
                Object var8 = var2;
                this.dimOne = Array.getLength(var2);
                this.csArray = new double[this.dimOne];
                double[][] var9 = new double[this.nDimensions - 1][];

                for(int var10 = 0; var10 < this.nDimensions - 1; ++var10) {
                    var9[var10] = this.xArray[var10 + 1];
                }

                Object[] var14 = new Object[this.dimOne];
                if (this.calculationDone) {
                    var14 = (Object[])((Object[])this.internalDeriv);
                }

                this.pcs = new PolyCubicSpline[this.dimOne];

                for(int var11 = 0; var11 < this.dimOne; ++var11) {
                    Object var12 = Array.get(var8, var11);
                    this.pcs[var11] = new PolyCubicSpline(var9, var12);
                    if (this.averageIdenticalAbscissae) {
                        this.pcs[var11].averageIdenticalAbscissae();
                    }

                    if (this.calculationDone) {
                        this.pcs[var11].setDeriv(var14[var11]);
                    }

                    if (!this.calculationDone) {
                        var14[var11] = this.pcs[var11].getDeriv();
                    }
                }

                this.internalDeriv = var14;
                this.calculationDone = true;
        }

    }

    public static void noRoundingErrorCheck() {
        roundingCheck = false;
        QuadriCubicSpline.noRoundingErrorCheck();
        TriCubicSpline.noRoundingErrorCheck();
        BiCubicSpline.noRoundingErrorCheck();
        CubicSpline.noRoundingErrorCheck();
    }

    public static void potentialRoundingError(double var0) {
        potentialRoundingError = var0;
        QuadriCubicSpline.potentialRoundingError(var0);
        TriCubicSpline.potentialRoundingError(var0);
        BiCubicSpline.potentialRoundingError(var0);
        CubicSpline.potentialRoundingError(var0);
    }

    private void limits() {
        this.xMin = new double[this.nDimensions];
        this.xMax = new double[this.nDimensions];

        for(int var1 = 0; var1 < this.nDimensions; ++var1) {
            this.xMin[var1] = Fmath.minimum(this.xArray[var1]);
            this.xMax[var1] = Fmath.maximum(this.xArray[var1]);
        }

    }

    public double[] getXmin() {
        return this.xMin;
    }

    public double[] getXmax() {
        return this.xMax;
    }

    public int getNumberOfDimensions() {
        return this.nDimensions;
    }

    public double[] getLimits() {
        double[] var1 = new double[2 * this.nDimensions];
        int var2 = 0;

        for(int var3 = 0; var3 < this.nDimensions; ++var3) {
            var1[var2] = this.xMin[var3];
            ++var2;
            var1[var2] = this.xMax[var3];
            ++var2;
        }

        return var1;
    }

    public void displayLimits() {
        System.out.println(" ");

        for(int var1 = 0; var1 < this.nDimensions; ++var1) {
            System.out.println("The limits to the x array " + var1 + " are " + this.xMin[var1] + " and " + this.xMax[var1]);
        }

        System.out.println(" ");
    }

    public void averageIdenticalAbscissae() {
        this.averageIdenticalAbscissae = true;
    }

    public double interpolate(double[] var1) {
        int var2 = var1.length;
        if (var2 != this.nDimensions) {
            throw new IllegalArgumentException("Number of unknown value coordinates, " + var2 + ", does not equal the number of tabulated data dimensions, " + this.nDimensions);
        } else {
            switch(this.nDimensions) {
                case 0:
                    throw new IllegalArgumentException("data array must have at least one dimension");
                case 1:
                    this.yValue = ((CubicSpline)((CubicSpline)this.method)).interpolate(var1[0]);
                    break;
                case 2:
                    this.yValue = ((BiCubicSpline)((BiCubicSpline)this.method)).interpolate(var1[0], var1[1]);
                    break;
                case 3:
                    this.yValue = ((TriCubicSpline)((TriCubicSpline)this.method)).interpolate(var1[0], var1[1], var1[2]);
                    break;
                case 4:
                    this.yValue = ((QuadriCubicSpline)((QuadriCubicSpline)this.method)).interpolate(var1[0], var1[1], var1[2], var1[3]);
                    break;
                default:
                    double[] var3 = new double[this.nDimensions - 1];

                    int var4;
                    for(var4 = 0; var4 < this.nDimensions - 1; ++var4) {
                        var3[var4] = var1[var4 + 1];
                    }

                    for(var4 = 0; var4 < this.dimOne; ++var4) {
                        this.csArray[var4] = this.pcs[var4].interpolate(var3);
                    }

                    CubicSpline var5 = new CubicSpline(this.xArray[0], this.csArray);
                    this.yValue = var5.interpolate(var1[0]);
            }

            return this.yValue;
        }
    }

    public void setDeriv(Object var1) {
        this.internalDeriv = var1;
    }

    public Object getDeriv() {
        return this.internalDeriv;
    }
}
