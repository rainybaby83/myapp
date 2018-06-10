//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.analysis;

import com.flanagan.circuits.Phasor;
import com.flanagan.complex.Complex;
import com.flanagan.integration.Integration;
import com.flanagan.interpolation.CubicSpline;
import com.flanagan.math.ArrayMaths;
import com.flanagan.math.Conv;
import com.flanagan.math.Fmath;
import com.flanagan.math.PsRandom;
import com.flanagan.plot.PlotGraph;
import com.flanagan.roots.RealRoot;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public class Stat extends ArrayMaths {
    private boolean nFactorOptionI = false;
    private boolean nFactorOptionIstored = false;
    private boolean nFactorReset = false;
    private boolean nEffOptionI = true;
    private boolean nEffReset = false;
    private boolean weightingOptionI = true;
    private boolean weightingReset = false;
    private ArrayMaths amWeights = null;
    private boolean weightsSupplied = false;
    private ArrayList<Object> upperOutlierDetails = new ArrayList();
    private boolean upperDone = false;
    private ArrayList<Object> lowerOutlierDetails = new ArrayList();
    private boolean lowerDone = false;
    private static boolean nFactorOptionS = false;
    private static boolean nFactorOptionSstored = false;
    private static boolean nEffOptionS = true;
    private static boolean weightingOptionS = true;
    private static int cfMaxIter = 500;
    private static double cfTol = 1.0E-8D;
    public static final double FPMIN = 1.0E-300D;
    private static boolean igSupress = false;
    private static int lgfN = 6;
    private static double[] lgfCoeff = new double[]{1.000000000190015D, 76.18009172947146D, -86.50532032941678D, 24.01409824083091D, -1.231739572450155D, 0.001208650973866179D, -5.395239384953E-6D};
    private static double lgfGamma = 5.0D;
    private static int igfiter = 1000;
    private static double igfeps = 1.0E-8D;
    private static double histTol = 1.0001D;

    public Stat() {
    }

    public Stat(double[] var1) {
        super(var1);
        this.convertToHighest();
    }

    public Stat(Double[] var1) {
        super(var1);
        this.convertToHighest();
    }

    public Stat(float[] var1) {
        super(var1);
        this.convertToHighest();
    }

    public Stat(Float[] var1) {
        super(var1);
        this.convertToHighest();
    }

    public Stat(long[] var1) {
        super(var1);
        this.convertToHighest();
    }

    public Stat(Long[] var1) {
        super(var1);
        this.convertToHighest();
    }

    public Stat(int[] var1) {
        super(var1);
        this.convertToHighest();
    }

    public Stat(Integer[] var1) {
        super(var1);
        this.convertToHighest();
    }

    public Stat(short[] var1) {
        super(var1);
        this.convertToHighest();
    }

    public Stat(Short[] var1) {
        super(var1);
        this.convertToHighest();
    }

    public Stat(byte[] var1) {
        super(var1);
        this.convertToHighest();
    }

    public Stat(Byte[] var1) {
        super(var1);
        this.convertToHighest();
    }

    public Stat(BigDecimal[] var1) {
        super(var1);
    }

    public Stat(BigInteger[] var1) {
        super(var1);
        this.convertToHighest();
    }

    public Stat(Complex[] var1) {
        super(var1);
        this.convertToHighest();
    }

    public Stat(Phasor[] var1) {
        super(var1);
        this.convertToHighest();
    }

    public Stat(String[] var1) {
        super(var1);
        this.convertToHighest();
    }

    public Stat(Object[] var1) {
        super(var1);
        this.convertToHighest();
    }

    public Stat(Vector<Object> var1) {
        super(var1);
        this.convertToHighest();
    }

    public Stat(ArrayList<Object> var1) {
        super(var1);
        this.convertToHighest();
    }

    public void convertToHighest() {
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 16:
            case 17:
            case 18:
                Double[] var1 = this.getArray_as_Double();
                this.array.clear();

                for(int var2 = 0; var2 < this.length; ++var2) {
                    this.array.add(var1[var2]);
                }

                double[] var8 = new double[this.length];

                for(int var9 = 0; var9 < this.length; ++var9) {
                    var8[var9] = 1.0D;
                }

                this.amWeights = new ArrayMaths(var8);
                this.type = 1;
                break;
            case 12:
            case 13:
                BigDecimal[] var3 = this.getArray_as_BigDecimal();
                this.array.clear();

                for(int var4 = 0; var4 < this.length; ++var4) {
                    this.array.add(var3[var4]);
                }

                BigDecimal[] var10 = new BigDecimal[this.length];

                for(int var11 = 0; var11 < this.length; ++var11) {
                    var10[var11] = BigDecimal.ONE;
                }

                this.amWeights = new ArrayMaths(var10);
                this.type = 12;
                var3 = null;
                break;
            case 14:
            case 15:
                Complex[] var5 = this.getArray_as_Complex();
                this.array.clear();

                for(int var6 = 0; var6 < this.length; ++var6) {
                    this.array.add(var5[var6]);
                }

                Complex[] var12 = new Complex[this.length];

                for(int var7 = 0; var7 < this.length; ++var7) {
                    var12[var7] = Complex.plusOne();
                }

                this.amWeights = new ArrayMaths(var12);
                this.type = 14;
                break;
            default:
                throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
        }

    }

    public void setWeightsToBigW() {
        this.weightingOptionI = false;
        this.weightingReset = true;
    }

    public void setWeightsToLittleW() {
        this.weightingOptionI = true;
        this.weightingReset = true;
    }

    public void setDenominatorToN() {
        this.nFactorOptionI = true;
        this.nFactorReset = true;
    }

    public void setDenominatorToNminusOne() {
        this.nFactorOptionI = false;
        this.nFactorReset = true;
    }

    public boolean getDenominatorOption() {
        return this.nFactorOptionI;
    }

    public void useEffectiveN() {
        this.nEffOptionI = true;
        this.nEffReset = true;
    }

    public void useTrueN() {
        this.nEffOptionI = false;
        this.nEffReset = true;
    }

    public double effectiveSampleNumber() {
        return this.effectiveSampleNumber_as_double();
    }

    public double effectiveSampleNumber_as_double() {
        boolean var1 = weightingOptionS;
        if (this.weightingReset) {
            if (this.weightingOptionI) {
                weightingOptionS = true;
            } else {
                weightingOptionS = false;
            }
        }

        double var2 = 0.0D;
        switch(this.type) {
            case 1:
                double[] var4 = this.getArray_as_double();
                var2 = effectiveSampleNumber(var4);
                break;
            case 12:
                BigDecimal[] var5 = this.getArray_as_BigDecimal();
                var2 = effectiveSampleNumber(var5).doubleValue();
                var5 = null;
                break;
            case 14:
                throw new IllegalArgumentException("Complex cannot be converted to double");
            default:
                throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
        }

        weightingOptionS = var1;
        return var2;
    }

    public BigDecimal effectiveSampleNumber_as_BigDecimal() {
        boolean var1 = weightingOptionS;
        if (this.weightingReset) {
            if (this.weightingOptionI) {
                weightingOptionS = true;
            } else {
                weightingOptionS = false;
            }
        }

        BigDecimal var2 = BigDecimal.ZERO;
        switch(this.type) {
            case 1:
            case 12:
                BigDecimal[] var3 = this.getArray_as_BigDecimal();
                var2 = effectiveSampleNumber(var3);
                var3 = null;
                weightingOptionS = var1;
                return var2;
            case 14:
                throw new IllegalArgumentException("Complex cannot be converted to BigDecimal");
            default:
                throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
        }
    }

    public Complex effectiveSampleNumber_as_Complex() {
        boolean var1 = weightingOptionS;
        if (this.weightingReset) {
            if (this.weightingOptionI) {
                weightingOptionS = true;
            } else {
                weightingOptionS = false;
            }
        }

        Complex var2 = Complex.zero();
        switch(this.type) {
            case 1:
            case 12:
            case 14:
                Complex[] var3 = this.getArray_as_Complex();
                var2 = effectiveSampleNumber(var3);
                weightingOptionS = var1;
                return var2;
            default:
                throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
        }
    }

    public int trueSampleNumber() {
        return this.length;
    }

    public int trueSampleNumber_as_int() {
        return this.length;
    }

    public double trueSampleNumber_as_double() {
        return (double)this.length;
    }

    public BigDecimal trueSampleNumber_as_BigDecimal() {
        return new BigDecimal((new Integer(this.length)).toString());
    }

    public Complex trueSampleNumber_as_Complex() {
        return new Complex((double)this.length, 0.0D);
    }

    public void convertBigWtoLittleW() {
        if (!this.weightsSupplied) {
            System.out.println("convertBigWtoLittleW: no weights have been supplied - all weights set to unity");
        } else {
            this.amWeights = this.amWeights.oneOverSqrt();
        }

    }

    public void setWeights(double[] var1) {
        if (this.length != var1.length) {
            throw new IllegalArgumentException("Length of weights array, " + var1.length + ", must be the same as the length of the instance internal array, " + this.length);
        } else {
            ArrayMaths var2 = new ArrayMaths(var1);
            this.convertWeights(var2);
            this.weightsSupplied = true;
        }
    }

    public void setWeights(Double[] var1) {
        if (this.length != var1.length) {
            throw new IllegalArgumentException("Length of weights array, " + var1.length + ", must be the same as the length of the instance internal array, " + this.length);
        } else {
            ArrayMaths var2 = new ArrayMaths(var1);
            this.convertWeights(var2);
            this.weightsSupplied = true;
        }
    }

    public void setWeights(float[] var1) {
        if (this.length != var1.length) {
            throw new IllegalArgumentException("Length of weights array, " + var1.length + ", must be the same as the length of the instance internal array, " + this.length);
        } else {
            ArrayMaths var2 = new ArrayMaths(var1);
            this.convertWeights(var2);
            this.weightsSupplied = true;
        }
    }

    public void setWeights(Float[] var1) {
        if (this.length != var1.length) {
            throw new IllegalArgumentException("Length of weights array, " + var1.length + ", must be the same as the length of the instance internal array, " + this.length);
        } else {
            ArrayMaths var2 = new ArrayMaths(var1);
            this.convertWeights(var2);
            this.weightsSupplied = true;
        }
    }

    public void setWeights(long[] var1) {
        if (this.length != var1.length) {
            throw new IllegalArgumentException("Length of weights array, " + var1.length + ", must be the same as the length of the instance internal array, " + this.length);
        } else {
            ArrayMaths var2 = new ArrayMaths(var1);
            this.convertWeights(var2);
            this.weightsSupplied = true;
        }
    }

    public void setWeights(Long[] var1) {
        if (this.length != var1.length) {
            throw new IllegalArgumentException("Length of weights array, " + var1.length + ", must be the same as the length of the instance internal array, " + this.length);
        } else {
            ArrayMaths var2 = new ArrayMaths(var1);
            this.convertWeights(var2);
            this.weightsSupplied = true;
        }
    }

    public void setWeights(int[] var1) {
        if (this.length != var1.length) {
            throw new IllegalArgumentException("Length of weights array, " + var1.length + ", must be the same as the length of the instance internal array, " + this.length);
        } else {
            ArrayMaths var2 = new ArrayMaths(var1);
            this.convertWeights(var2);
            this.weightsSupplied = true;
        }
    }

    public void setWeights(Integer[] var1) {
        if (this.length != var1.length) {
            throw new IllegalArgumentException("Length of weights array, " + var1.length + ", must be the same as the length of the instance internal array, " + this.length);
        } else {
            ArrayMaths var2 = new ArrayMaths(var1);
            this.convertWeights(var2);
            this.weightsSupplied = true;
        }
    }

    public void setWeights(short[] var1) {
        if (this.length != var1.length) {
            throw new IllegalArgumentException("Length of weights array, " + var1.length + ", must be the same as the length of the instance internal array, " + this.length);
        } else {
            ArrayMaths var2 = new ArrayMaths(var1);
            this.convertWeights(var2);
            this.weightsSupplied = true;
        }
    }

    public void setWeights(Short[] var1) {
        if (this.length != var1.length) {
            throw new IllegalArgumentException("Length of weights array, " + var1.length + ", must be the same as the length of the instance internal array, " + this.length);
        } else {
            ArrayMaths var2 = new ArrayMaths(var1);
            this.convertWeights(var2);
            this.weightsSupplied = true;
        }
    }

    public void setWeights(byte[] var1) {
        if (this.length != var1.length) {
            throw new IllegalArgumentException("Length of weights array, " + var1.length + ", must be the same as the length of the instance internal array, " + this.length);
        } else {
            ArrayMaths var2 = new ArrayMaths(var1);
            this.convertWeights(var2);
            this.weightsSupplied = true;
        }
    }

    public void setWeights(Byte[] var1) {
        if (this.length != var1.length) {
            throw new IllegalArgumentException("Length of weights array, " + var1.length + ", must be the same as the length of the instance internal array, " + this.length);
        } else {
            ArrayMaths var2 = new ArrayMaths(var1);
            this.convertWeights(var2);
            this.weightsSupplied = true;
        }
    }

    public void setWeights(BigDecimal[] var1) {
        if (this.length != var1.length) {
            throw new IllegalArgumentException("Length of weights array, " + var1.length + ", must be the same as the length of the instance internal array, " + this.length);
        } else {
            ArrayMaths var2 = new ArrayMaths(var1);
            this.convertWeights(var2);
            this.weightsSupplied = true;
        }
    }

    public void setWeights(BigInteger[] var1) {
        if (this.length != var1.length) {
            throw new IllegalArgumentException("Length of weights array, " + var1.length + ", must be the same as the length of the instance internal array, " + this.length);
        } else {
            ArrayMaths var2 = new ArrayMaths(var1);
            this.convertWeights(var2);
            this.weightsSupplied = true;
        }
    }

    public void setWeights(Complex[] var1) {
        if (this.length != var1.length) {
            throw new IllegalArgumentException("Length of weights array, " + var1.length + ", must be the same as the length of the instance internal array, " + this.length);
        } else {
            ArrayMaths var2 = new ArrayMaths(var1);
            this.convertWeights(var2);
            this.weightsSupplied = true;
        }
    }

    public void setWeights(Phasor[] var1) {
        if (this.length != var1.length) {
            throw new IllegalArgumentException("Length of weights array, " + var1.length + ", must be the same as the length of the instance internal array, " + this.length);
        } else {
            ArrayMaths var2 = new ArrayMaths(var1);
            this.convertWeights(var2);
            this.weightsSupplied = true;
        }
    }

    public void setWeights(Object[] var1) {
        if (this.length != var1.length) {
            throw new IllegalArgumentException("Length of weights array, " + var1.length + ", must be the same as the length of the instance internal array, " + this.length);
        } else {
            ArrayMaths var2 = new ArrayMaths(var1);
            this.convertWeights(var2);
            this.weightsSupplied = true;
        }
    }

    public void setWeights(Vector<Object> var1) {
        if (this.length != var1.size()) {
            throw new IllegalArgumentException("Length of weights array, " + var1.size() + ", must be the same as the length of the instance internal array, " + this.length);
        } else {
            ArrayMaths var2 = new ArrayMaths(var1);
            this.convertWeights(var2);
            this.weightsSupplied = true;
        }
    }

    public void setWeights(ArrayList<Object> var1) {
        if (this.length != var1.size()) {
            throw new IllegalArgumentException("Length of weights array, " + var1.size() + ", must be the same as the length of the instance internal array, " + this.length);
        } else {
            ArrayMaths var2 = new ArrayMaths(var1);
            this.convertWeights(var2);
            this.weightsSupplied = true;
        }
    }

    private void convertWeights(ArrayMaths var1) {
        Complex[] var2;
        Complex[] var4;
        Complex[] var9;
        BigDecimal[] var10;
        Complex[] var13;
        switch(this.type) {
            case 1:
                switch(var1.typeIndex()) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                    case 11:
                        Double[] var8 = var1.getArray_as_Double();
                        this.amWeights = new ArrayMaths(var8);
                        return;
                    case 12:
                    case 13:
                        var10 = this.getArray_as_BigDecimal();

                        for(int var11 = 0; var11 < this.length; ++var11) {
                            this.array.add(var10[var11]);
                        }

                        BigDecimal[] var12 = var1.getArray_as_BigDecimal();
                        this.amWeights = new ArrayMaths(var12);
                        var9 = null;
                        var4 = null;
                        return;
                    case 14:
                    case 15:
                        var13 = this.getArray_as_Complex();

                        for(int var6 = 0; var6 < this.length; ++var6) {
                            this.array.add(var13[var6]);
                        }

                        Complex[] var14 = var1.getArray_as_Complex();
                        this.amWeights = new ArrayMaths(var14);
                        return;
                    default:
                        throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
                }
            case 12:
                switch(var1.typeIndex()) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                    case 11:
                        BigDecimal[] var7 = var1.getArray_as_BigDecimal();
                        this.amWeights = new ArrayMaths(var7);
                        var2 = null;
                        return;
                    case 12:
                    case 13:
                        var10 = var1.getArray_as_BigDecimal();
                        this.amWeights = new ArrayMaths(var10);
                        var9 = null;
                        return;
                    case 14:
                    case 15:
                        var4 = this.getArray_as_Complex();

                        for(int var5 = 0; var5 < this.length; ++var5) {
                            this.array.add(var4[var5]);
                        }

                        var13 = var1.getArray_as_Complex();
                        this.amWeights = new ArrayMaths(var13);
                        return;
                    default:
                        throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
                }
            case 14:
                var2 = this.getArray_as_Complex();

                for(int var3 = 0; var3 < this.length; ++var3) {
                    this.array.add(var2[var3]);
                }

                var9 = var1.getArray_as_Complex();
                this.amWeights = new ArrayMaths(var9);
                return;
            default:
                throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
        }
    }

    public double mean() {
        return this.mean_as_double();
    }

    public double mean_as_double() {
        double var1 = 0.0D;
        switch(this.type) {
            case 1:
                double[] var3 = this.getArray_as_double();

                for(int var7 = 0; var7 < this.length; ++var7) {
                    var1 += var3[var7];
                }

                var1 /= (double)this.length;
                break;
            case 12:
                BigDecimal[] var4 = this.getArray_as_BigDecimal();
                BigDecimal var5 = BigDecimal.ZERO;

                for(int var6 = 0; var6 < this.length; ++var6) {
                    var5 = var5.add(var4[var6]);
                }

                var5 = var5.divide(new BigDecimal((double)this.length), 4);
                var1 = var5.doubleValue();
                var4 = null;
                var5 = null;
                break;
            case 14:
                throw new IllegalArgumentException("Complex cannot be converted to double");
            default:
                throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
        }

        return var1;
    }

    public BigDecimal mean_as_BigDecimal() {
        BigDecimal var1 = BigDecimal.ZERO;
        switch(this.type) {
            case 1:
                double[] var2 = this.getArray_as_double();
                double var3 = 0.0D;

                for(int var7 = 0; var7 < this.length; ++var7) {
                    var3 += var2[var7];
                }

                var3 /= (double)this.length;
                var1 = new BigDecimal(var3);
                break;
            case 12:
                BigDecimal[] var5 = this.getArray_as_BigDecimal();

                for(int var6 = 0; var6 < this.length; ++var6) {
                    var1 = var1.add(var5[var6]);
                }

                var1 = var1.divide(new BigDecimal((double)this.length), 4);
                var5 = null;
                break;
            case 14:
                throw new IllegalArgumentException("Complex cannot be converted to BigDecimal");
            default:
                throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
        }

        return var1;
    }

    public Complex mean_as_Complex() {
        Complex var1 = Complex.zero();
        switch(this.type) {
            case 1:
                double[] var2 = this.getArray_as_double();
                double var3 = 0.0D;

                for(int var9 = 0; var9 < this.length; ++var9) {
                    var3 += var2[var9];
                }

                var3 /= (double)this.length;
                var1 = new Complex(var3);
                break;
            case 12:
                BigDecimal[] var5 = this.getArray_as_BigDecimal();
                BigDecimal var6 = BigDecimal.ZERO;

                for(int var10 = 0; var10 < this.length; ++var10) {
                    var6 = var6.add(var5[var10]);
                }

                var6 = var6.divide(new BigDecimal((double)this.length), 4);
                var1 = new Complex(var6.doubleValue());
                var5 = null;
                var6 = null;
                break;
            case 14:
                Complex[] var7 = this.getArray_as_Complex();

                for(int var8 = 0; var8 < this.length; ++var8) {
                    var1 = var1.plus(var7[var8]);
                }

                var1 = var1.over(new Complex((double)this.length));
                break;
            default:
                throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
        }

        return var1;
    }

    public double weightedMean() {
        return this.weightedMean_as_double();
    }

    public double weightedMean_as_double() {
        if (!this.weightsSupplied) {
            System.out.println("weightedMean_as_double: no weights supplied - unweighted mean returned");
            return this.mean_as_double();
        } else {
            boolean var1 = weightingOptionS;
            if (this.weightingReset) {
                if (this.weightingOptionI) {
                    weightingOptionS = true;
                } else {
                    weightingOptionS = false;
                }
            }

            double var2 = 0.0D;
            switch(this.type) {
                case 1:
                    double[] var4 = this.getArray_as_double();
                    double[] var5 = this.amWeights.getArray_as_double();
                    var2 = mean(var4, var5);
                    break;
                case 12:
                    BigDecimal[] var6 = this.getArray_as_BigDecimal();
                    BigDecimal[] var7 = this.amWeights.getArray_as_BigDecimal();
                    var2 = mean(var6, var7).doubleValue();
                    var6 = null;
                    var7 = null;
                    break;
                case 14:
                    throw new IllegalArgumentException("Complex cannot be converted to double");
                default:
                    throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
            }

            weightingOptionS = var1;
            return var2;
        }
    }

    public BigDecimal weightedMean_as_BigDecimal() {
        if (!this.weightsSupplied) {
            System.out.println("weightedMean_as_BigDecimal: no weights supplied - unweighted mean returned");
            return this.mean_as_BigDecimal();
        } else {
            boolean var1 = weightingOptionS;
            if (this.weightingReset) {
                if (this.weightingOptionI) {
                    weightingOptionS = true;
                } else {
                    weightingOptionS = false;
                }
            }

            BigDecimal var2 = BigDecimal.ZERO;
            switch(this.type) {
                case 1:
                case 12:
                    BigDecimal[] var3 = this.getArray_as_BigDecimal();
                    BigDecimal[] var4 = this.amWeights.getArray_as_BigDecimal();
                    var2 = mean(var3, var4);
                    var3 = null;
                    var4 = null;
                    weightingOptionS = var1;
                    return var2;
                case 14:
                    throw new IllegalArgumentException("Complex cannot be converted to BigDecimal");
                default:
                    throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
            }
        }
    }

    public Complex weightedMean_as_Complex() {
        if (!this.weightsSupplied) {
            System.out.println("weightedMean_as_Complex: no weights supplied - unweighted mean returned");
            return this.mean_as_Complex();
        } else {
            boolean var1 = weightingOptionS;
            if (this.weightingReset) {
                if (this.weightingOptionI) {
                    weightingOptionS = true;
                } else {
                    weightingOptionS = false;
                }
            }

            Complex var2 = Complex.zero();
            switch(this.type) {
                case 1:
                    double[] var3 = this.getArray_as_double();
                    double[] var4 = this.amWeights.getArray_as_double();
                    var2 = new Complex(mean(var3, var4));
                    break;
                case 12:
                    BigDecimal[] var5 = this.getArray_as_BigDecimal();
                    BigDecimal[] var6 = this.amWeights.getArray_as_BigDecimal();
                    var2 = new Complex(mean(var5, var6).doubleValue());
                    var5 = null;
                    var6 = null;
                    break;
                case 14:
                    Complex[] var7 = this.getArray_as_Complex();
                    Complex[] var8 = this.amWeights.getArray_as_Complex();
                    var2 = mean(var7, var8);
                    break;
                default:
                    throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
            }

            weightingOptionS = var1;
            return var2;
        }
    }

    public double[] subtractMean() {
        return this.subtractMean_as_double();
    }

    public double[] subtractMean_as_double() {
        double[] var1 = new double[this.length];
        switch(this.type) {
            case 1:
                double var2 = this.mean_as_double();
                ArrayMaths var4 = this.minus(var2);
                var1 = var4.getArray_as_double();
                break;
            case 12:
                BigDecimal var5 = this.mean_as_BigDecimal();
                ArrayMaths var6 = this.minus(var5);
                var1 = var6.getArray_as_double();
                var5 = null;
                break;
            case 14:
                throw new IllegalArgumentException("Complex cannot be converted to double");
            default:
                throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
        }

        return var1;
    }

    public BigDecimal[] subtractMean_as_BigDecimal() {
        BigDecimal[] var1 = new BigDecimal[this.length];
        switch(this.type) {
            case 1:
            case 12:
                BigDecimal var2 = this.mean_as_BigDecimal();
                ArrayMaths var3 = this.minus(var2);
                var1 = var3.getArray_as_BigDecimal();
                var2 = null;
                return var1;
            case 14:
                throw new IllegalArgumentException("Complex cannot be converted to BigDecimal");
            default:
                throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
        }
    }

    public Complex[] subtractMean_as_Complex() {
        Complex[] var1 = new Complex[this.length];
        switch(this.type) {
            case 1:
                double var2 = this.mean_as_double();
                ArrayMaths var4 = this.minus(var2);
                var1 = var4.getArray_as_Complex();
                break;
            case 12:
                BigDecimal var5 = this.mean_as_BigDecimal();
                ArrayMaths var6 = this.minus(var5);
                var1 = var6.getArray_as_Complex();
                var5 = null;
                break;
            case 14:
                Complex var7 = this.mean_as_Complex();
                ArrayMaths var8 = this.minus(var7);
                var1 = var8.getArray_as_Complex();
                break;
            default:
                throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
        }

        return var1;
    }

    public double[] subtractWeightedMean() {
        return this.subtractWeightedMean_as_double();
    }

    public double[] subtractWeightedMean_as_double() {
        if (!this.weightsSupplied) {
            System.out.println("subtractWeightedMean_as_double: no weights supplied - unweighted values returned");
            return this.subtractMean_as_double();
        } else {
            boolean var1 = weightingOptionS;
            if (this.weightingReset) {
                if (this.weightingOptionI) {
                    weightingOptionS = true;
                } else {
                    weightingOptionS = false;
                }
            }

            double[] var2 = new double[this.length];
            switch(this.type) {
                case 1:
                    double var3 = this.weightedMean_as_double();
                    ArrayMaths var5 = this.minus(var3);
                    var2 = var5.getArray_as_double();
                    break;
                case 12:
                    BigDecimal var6 = this.weightedMean_as_BigDecimal();
                    ArrayMaths var7 = this.minus(var6);
                    var2 = var7.getArray_as_double();
                    var6 = null;
                    break;
                case 14:
                    throw new IllegalArgumentException("Complex cannot be converted to double");
                default:
                    throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
            }

            weightingOptionS = var1;
            return var2;
        }
    }

    public BigDecimal[] subtractWeightedMean_as_BigDecimal() {
        if (!this.weightsSupplied) {
            System.out.println("subtractWeightedMean_as_BigDecimal: no weights supplied - unweighted values returned");
            return this.subtractMean_as_BigDecimal();
        } else {
            boolean var1 = weightingOptionS;
            if (this.weightingReset) {
                if (this.weightingOptionI) {
                    weightingOptionS = true;
                } else {
                    weightingOptionS = false;
                }
            }

            BigDecimal[] var2 = new BigDecimal[this.length];
            switch(this.type) {
                case 1:
                case 12:
                    BigDecimal var3 = this.weightedMean_as_BigDecimal();
                    ArrayMaths var4 = this.minus(var3);
                    var2 = var4.getArray_as_BigDecimal();
                    var3 = null;
                    weightingOptionS = var1;
                    return var2;
                case 14:
                    throw new IllegalArgumentException("Complex cannot be converted to BigDecimal");
                default:
                    throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
            }
        }
    }

    public Complex[] subtractWeightedMean_as_Complex() {
        if (!this.weightsSupplied) {
            System.out.println("subtractWeightedMean_as_Complex: no weights supplied - unweighted values returned");
            return this.subtractMean_as_Complex();
        } else {
            boolean var1 = weightingOptionS;
            if (this.weightingReset) {
                if (this.weightingOptionI) {
                    weightingOptionS = true;
                } else {
                    weightingOptionS = false;
                }
            }

            Complex[] var2 = new Complex[this.length];
            switch(this.type) {
                case 1:
                    double var3 = this.weightedMean_as_double();
                    ArrayMaths var5 = this.minus(var3);
                    var2 = var5.getArray_as_Complex();
                    break;
                case 12:
                    BigDecimal var6 = this.weightedMean_as_BigDecimal();
                    ArrayMaths var7 = this.minus(var6);
                    var2 = var7.getArray_as_Complex();
                    var6 = null;
                    break;
                case 14:
                    Complex var8 = this.weightedMean_as_Complex();
                    ArrayMaths var9 = this.minus(var8);
                    var2 = var9.getArray_as_Complex();
                    break;
                default:
                    throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
            }

            weightingOptionS = var1;
            return var2;
        }
    }

    public double geometricMean() {
        return this.geometricMean_as_double();
    }

    public double geometricMean_as_double() {
        double var1 = 0.0D;
        switch(this.type) {
            case 1:
                double[] var3 = this.getArray_as_double();
                var1 = geometricMean(var3);
                break;
            case 12:
                BigDecimal[] var4 = this.getArray_as_BigDecimal();
                var1 = geometricMean(var4);
                var4 = null;
                break;
            case 14:
                throw new IllegalArgumentException("Complex cannot  be converted to double");
            default:
                throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
        }

        return var1;
    }

    public Complex geometricMean_as_Complex() {
        Complex var1 = Complex.zero();
        switch(this.type) {
            case 1:
            case 12:
            case 14:
                Complex[] var2 = this.getArray_as_Complex();
                var1 = geometricMean(var2);
                return var1;
            default:
                throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
        }
    }

    public double weightedGeometricMean() {
        return this.weightedGeometricMean_as_double();
    }

    public double weightedGeometricMean_as_double() {
        if (!this.weightsSupplied) {
            System.out.println("weightedGeometricMean_as_double: no weights supplied - unweighted value returned");
            return this.geometricMean_as_double();
        } else {
            boolean var1 = weightingOptionS;
            if (this.weightingReset) {
                if (this.weightingOptionI) {
                    weightingOptionS = true;
                } else {
                    weightingOptionS = false;
                }
            }

            double var2 = 0.0D;
            switch(this.type) {
                case 1:
                    double[] var4 = this.getArray_as_double();
                    double[] var5 = this.getArray_as_double();
                    var2 = geometricMean(var4, var5);
                    break;
                case 12:
                    BigDecimal[] var6 = this.getArray_as_BigDecimal();
                    BigDecimal[] var7 = this.getArray_as_BigDecimal();
                    var2 = geometricMean(var6, var7);
                    var6 = null;
                    var7 = null;
                    break;
                case 14:
                    throw new IllegalArgumentException("Complex cannot  be converted to double");
                default:
                    throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
            }

            weightingOptionS = var1;
            return var2;
        }
    }

    public Complex weightedGeometricMean_as_Complex() {
        if (!this.weightsSupplied) {
            System.out.println("weightedGeometricMean_as_Complex: no weights supplied - unweighted value returned");
            return this.geometricMean_as_Complex();
        } else {
            boolean var1 = weightingOptionS;
            if (this.weightingReset) {
                if (this.weightingOptionI) {
                    weightingOptionS = true;
                } else {
                    weightingOptionS = false;
                }
            }

            Complex var2 = Complex.zero();
            switch(this.type) {
                case 1:
                case 12:
                case 14:
                    Complex[] var3 = this.getArray_as_Complex();
                    Complex[] var4 = this.getArray_as_Complex();
                    var2 = geometricMean(var3, var4);
                    weightingOptionS = var1;
                    return var2;
                default:
                    throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
            }
        }
    }

    public double harmonicMean() {
        return this.harmonicMean_as_double();
    }

    public double harmonicMean_as_double() {
        double var1 = 0.0D;
        switch(this.type) {
            case 1:
                double[] var3 = this.getArray_as_double();
                var1 = harmonicMean(var3);
                break;
            case 12:
                BigDecimal[] var4 = this.getArray_as_BigDecimal();
                var1 = harmonicMean(var4).doubleValue();
                var4 = null;
                break;
            case 14:
                throw new IllegalArgumentException("Complex cannot be converted to double");
            default:
                throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
        }

        return var1;
    }

    public BigDecimal harmonicMean_as_BigDecimal() {
        BigDecimal var1 = BigDecimal.ZERO;
        switch(this.type) {
            case 1:
            case 12:
                BigDecimal[] var2 = this.getArray_as_BigDecimal();
                var1 = harmonicMean(var2);
                var2 = null;
                return var1;
            case 14:
                throw new IllegalArgumentException("Complex cannot be converted to BigDecimal");
            default:
                throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
        }
    }

    public Complex harmonicMean_as_Complex() {
        Complex var1 = Complex.zero();
        switch(this.type) {
            case 1:
                double[] var2 = this.getArray_as_double();
                var1 = new Complex(harmonicMean(var2));
                break;
            case 12:
                BigDecimal[] var3 = this.getArray_as_BigDecimal();
                var1 = new Complex(harmonicMean(var3).doubleValue());
                var3 = null;
                break;
            case 14:
                Complex[] var4 = this.getArray_as_Complex();
                var1 = harmonicMean(var4);
                break;
            default:
                throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
        }

        return var1;
    }

    public double weightedHarmonicMean() {
        return this.weightedHarmonicMean_as_double();
    }

    public double weightedHarmonicMean_as_double() {
        if (!this.weightsSupplied) {
            System.out.println("weightedHarmonicMean_as_double: no weights supplied - unweighted mean returned");
            return this.harmonicMean_as_double();
        } else {
            boolean var1 = weightingOptionS;
            if (this.weightingReset) {
                if (this.weightingOptionI) {
                    weightingOptionS = true;
                } else {
                    weightingOptionS = false;
                }
            }

            double var2 = 0.0D;
            switch(this.type) {
                case 1:
                    double[] var4 = this.getArray_as_double();
                    double[] var5 = this.amWeights.getArray_as_double();
                    var2 = harmonicMean(var4, var5);
                    break;
                case 12:
                    BigDecimal[] var6 = this.getArray_as_BigDecimal();
                    BigDecimal[] var7 = this.amWeights.getArray_as_BigDecimal();
                    var2 = harmonicMean(var6, var7).doubleValue();
                    var6 = null;
                    var7 = null;
                    break;
                case 14:
                    throw new IllegalArgumentException("Complex cannot be converted to double");
                default:
                    throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
            }

            weightingOptionS = var1;
            return var2;
        }
    }

    public BigDecimal weightedHarmonicMean_as_BigDecimal() {
        if (!this.weightsSupplied) {
            System.out.println("weightedHarmonicMean_as_BigDecimal: no weights supplied - unweighted mean returned");
            return this.harmonicMean_as_BigDecimal();
        } else {
            boolean var1 = weightingOptionS;
            if (this.weightingReset) {
                if (this.weightingOptionI) {
                    weightingOptionS = true;
                } else {
                    weightingOptionS = false;
                }
            }

            BigDecimal var2 = BigDecimal.ZERO;
            switch(this.type) {
                case 1:
                case 12:
                    BigDecimal[] var3 = this.getArray_as_BigDecimal();
                    BigDecimal[] var4 = this.amWeights.getArray_as_BigDecimal();
                    var2 = harmonicMean(var3, var4);
                    var3 = null;
                    var4 = null;
                    weightingOptionS = var1;
                    return var2;
                case 14:
                    throw new IllegalArgumentException("Complex cannot be converted to BigDecimal");
                default:
                    throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
            }
        }
    }

    public Complex weightedHarmonicMean_as_Complex() {
        if (!this.weightsSupplied) {
            System.out.println("weightedHarmonicMean_as_Complex: no weights supplied - unweighted mean returned");
            return this.harmonicMean_as_Complex();
        } else {
            boolean var1 = weightingOptionS;
            if (this.weightingReset) {
                if (this.weightingOptionI) {
                    weightingOptionS = true;
                } else {
                    weightingOptionS = false;
                }
            }

            Complex var2 = Complex.zero();
            switch(this.type) {
                case 1:
                    double[] var3 = this.getArray_as_double();
                    double[] var4 = this.amWeights.getArray_as_double();
                    var2 = new Complex(harmonicMean(var3, var4));
                    break;
                case 12:
                    BigDecimal[] var5 = this.getArray_as_BigDecimal();
                    BigDecimal[] var6 = this.amWeights.getArray_as_BigDecimal();
                    var2 = new Complex(harmonicMean(var5, var6).doubleValue());
                    var5 = null;
                    var6 = null;
                    break;
                case 14:
                    Complex[] var7 = this.getArray_as_Complex();
                    Complex[] var8 = this.amWeights.getArray_as_Complex();
                    var2 = harmonicMean(var7, var8);
                    break;
                default:
                    throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
            }

            weightingOptionS = var1;
            return var2;
        }
    }

    public double generalizedMean(double var1) {
        return this.generalizedMean_as_double(var1);
    }

    public double generalizedMean_as_double(double var1) {
        double var3 = 0.0D;
        switch(this.type) {
            case 1:
                double[] var5 = this.getArray_as_double();
                var3 = generalizedMean(var5, var1);
                break;
            case 12:
                BigDecimal[] var6 = this.getArray_as_BigDecimal();
                var3 = generalizedMean(var6, var1);
                var6 = null;
                break;
            case 14:
                throw new IllegalArgumentException("Complex cannot be converted to double");
            default:
                throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
        }

        return var3;
    }

    public double generalizedMean(BigDecimal var1) {
        return this.generalizedMean_as_double(var1);
    }

    public double generalizedMean_as_double(BigDecimal var1) {
        double var2 = 0.0D;
        switch(this.type) {
            case 1:
            case 12:
                BigDecimal[] var4 = this.getArray_as_BigDecimal();
                var2 = generalizedMean(var4, var1);
                var4 = null;
                return var2;
            case 14:
                throw new IllegalArgumentException("Complex cannot be converted to BigDecimal");
            default:
                throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
        }
    }

    public Complex generalizedMean_as_Complex(double var1) {
        Complex var3 = Complex.zero();
        switch(this.type) {
            case 1:
                double[] var4 = this.getArray_as_double();
                var3 = new Complex(generalizedMean(var4, var1));
                break;
            case 12:
                BigDecimal[] var5 = this.getArray_as_BigDecimal();
                var3 = new Complex(generalizedMean(var5, var1));
                var5 = null;
                break;
            case 14:
                Complex[] var6 = this.getArray_as_Complex();
                var3 = generalizedMean(var6, var1);
                break;
            default:
                throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
        }

        return var3;
    }

    public Complex generalizedMean_as_Complex(Complex var1) {
        Complex var2 = Complex.zero();
        switch(this.type) {
            case 1:
            case 12:
            case 14:
                Complex[] var3 = this.getArray_as_Complex();
                var2 = generalizedMean(var3, var1);
                return var2;
            default:
                throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
        }
    }

    public double generalisedMean(double var1) {
        return this.generalisedMean_as_double(var1);
    }

    public double generalisedMean_as_double(double var1) {
        double var3 = 0.0D;
        switch(this.type) {
            case 1:
                double[] var5 = this.getArray_as_double();
                var3 = generalisedMean(var5, var1);
                break;
            case 12:
                BigDecimal[] var6 = this.getArray_as_BigDecimal();
                var3 = generalisedMean(var6, var1);
                var6 = null;
                break;
            case 14:
                throw new IllegalArgumentException("Complex cannot be converted to double");
            default:
                throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
        }

        return var3;
    }

    public double generalisedMean(BigDecimal var1) {
        return this.generalisedMean_as_double(var1);
    }

    public double generalisedMean_as_double(BigDecimal var1) {
        double var2 = 0.0D;
        switch(this.type) {
            case 1:
            case 12:
                BigDecimal[] var4 = this.getArray_as_BigDecimal();
                var2 = generalisedMean(var4, var1);
                var4 = null;
                return var2;
            case 14:
                throw new IllegalArgumentException("Complex cannot be converted to BigDecimal");
            default:
                throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
        }
    }

    public Complex generalisedMean_as_Complex(double var1) {
        Complex var3 = Complex.zero();
        switch(this.type) {
            case 1:
                double[] var4 = this.getArray_as_double();
                var3 = new Complex(generalisedMean(var4, var1));
                break;
            case 12:
                BigDecimal[] var5 = this.getArray_as_BigDecimal();
                var3 = new Complex(generalisedMean(var5, var1));
                var5 = null;
                break;
            case 14:
                Complex[] var6 = this.getArray_as_Complex();
                var3 = generalisedMean(var6, var1);
                break;
            default:
                throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
        }

        return var3;
    }

    public Complex generalisedMean_as_Complex(Complex var1) {
        Complex var2 = Complex.zero();
        switch(this.type) {
            case 1:
            case 12:
            case 14:
                Complex[] var3 = this.getArray_as_Complex();
                var2 = generalisedMean(var3, var1);
                return var2;
            default:
                throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
        }
    }

    public double weightedGeneralizedMean(double var1) {
        return this.weightedGeneralizedMean_as_double(var1);
    }

    public double weightedGeneralizedMean_as_double(double var1) {
        if (!this.weightsSupplied) {
            System.out.println("weightedGeneralizedMean_as_double: no weights supplied - unweighted mean returned");
            return this.generalizedMean_as_double(var1);
        } else {
            boolean var3 = weightingOptionS;
            if (this.weightingReset) {
                if (this.weightingOptionI) {
                    weightingOptionS = true;
                } else {
                    weightingOptionS = false;
                }
            }

            double var4 = 0.0D;
            switch(this.type) {
                case 1:
                    double[] var6 = this.getArray_as_double();
                    double[] var7 = this.amWeights.getArray_as_double();
                    var4 = generalisedMean(var6, var7, var1);
                    break;
                case 12:
                    BigDecimal[] var8 = this.getArray_as_BigDecimal();
                    BigDecimal[] var9 = this.amWeights.getArray_as_BigDecimal();
                    var4 = generalisedMean(var8, var9, var1);
                    var8 = null;
                    var9 = null;
                    break;
                case 14:
                    throw new IllegalArgumentException("Complex cannot be converted to double");
                default:
                    throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
            }

            weightingOptionS = var3;
            return var4;
        }
    }

    public double weightedGeneralizedMean(BigDecimal var1) {
        return this.weightedGeneralizedMean_as_double(var1);
    }

    public double weightedGeneralizedMean_as_double(BigDecimal var1) {
        if (!this.weightsSupplied) {
            System.out.println("weightedGeneralizedMean_as_double: no weights supplied - unweighted mean returned");
            return this.generalizedMean_as_double(var1);
        } else {
            boolean var2 = weightingOptionS;
            if (this.weightingReset) {
                if (this.weightingOptionI) {
                    weightingOptionS = true;
                } else {
                    weightingOptionS = false;
                }
            }

            double var3 = 0.0D;
            switch(this.type) {
                case 1:
                case 12:
                    BigDecimal[] var5 = this.getArray_as_BigDecimal();
                    BigDecimal[] var6 = this.amWeights.getArray_as_BigDecimal();
                    var3 = generalisedMean(var5, var6, var1);
                    var5 = null;
                    weightingOptionS = var2;
                    return var3;
                case 14:
                    throw new IllegalArgumentException("Complex cannot be converted to BigDecimal");
                default:
                    throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
            }
        }
    }

    public Complex weightedGeneralizedMean_as_Complex(double var1) {
        if (!this.weightsSupplied) {
            System.out.println("weightedGeneralizedMean_as_Complex: no weights supplied - unweighted mean returned");
            return this.generalizedMean_as_Complex(var1);
        } else {
            boolean var3 = weightingOptionS;
            if (this.weightingReset) {
                if (this.weightingOptionI) {
                    weightingOptionS = true;
                } else {
                    weightingOptionS = false;
                }
            }

            Complex var4 = Complex.zero();
            switch(this.type) {
                case 1:
                    double[] var5 = this.getArray_as_double();
                    double[] var6 = this.amWeights.getArray_as_double();
                    var4 = new Complex(generalisedMean(var5, var6, var1));
                    break;
                case 12:
                    BigDecimal[] var7 = this.getArray_as_BigDecimal();
                    BigDecimal[] var8 = this.amWeights.getArray_as_BigDecimal();
                    var4 = new Complex(generalisedMean(var7, var8, var1));
                    var7 = null;
                    break;
                case 14:
                    Complex[] var9 = this.getArray_as_Complex();
                    Complex[] var10 = this.amWeights.getArray_as_Complex();
                    var4 = generalisedMean(var9, var10, var1);
                    break;
                default:
                    throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
            }

            weightingOptionS = var3;
            return var4;
        }
    }

    public Complex weightedGeneralizedMean_as_Complex(Complex var1) {
        Complex var2 = Complex.zero();
        if (!this.weightsSupplied) {
            System.out.println("weightedGeneralizedMean_as_dComplex: no weights supplied - unweighted mean returned");
            return this.generalizedMean_as_Complex(var1);
        } else {
            boolean var3 = weightingOptionS;
            if (this.weightingReset) {
                if (this.weightingOptionI) {
                    weightingOptionS = true;
                } else {
                    weightingOptionS = false;
                }
            }

            switch(this.type) {
                case 1:
                case 12:
                case 14:
                    Complex[] var4 = this.getArray_as_Complex();
                    Complex[] var5 = this.amWeights.getArray_as_Complex();
                    var2 = generalisedMean(var4, var5, var1);
                    weightingOptionS = var3;
                    return var2;
                default:
                    throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
            }
        }
    }

    public double weightedGeneralisedMean(double var1) {
        return this.weightedGeneralizedMean_as_double(var1);
    }

    public double weightedGeneralisedMean_as_double(double var1) {
        return this.weightedGeneralizedMean_as_double(var1);
    }

    public double weightedGeneralisedMean(BigDecimal var1) {
        return this.weightedGeneralizedMean_as_double(var1);
    }

    public double weightedGeneralisedMean_as_double(BigDecimal var1) {
        return this.weightedGeneralizedMean_as_double(var1);
    }

    public Complex weightedGeneralisedMean_as_Complex(double var1) {
        return this.weightedGeneralizedMean_as_Complex(var1);
    }

    public Complex weightedGeneralisedMean_as_Complex(Complex var1) {
        return this.weightedGeneralizedMean_as_Complex(var1);
    }

    public double interQuartileMean() {
        return this.interQuartileMean_as_double();
    }

    public double interQuartileMean_as_double() {
        double var1 = 0.0D;
        switch(this.type) {
            case 1:
                double[] var3 = this.getArray_as_double();
                var1 = interQuartileMean(var3);
                break;
            case 12:
                BigDecimal[] var4 = this.getArray_as_BigDecimal();
                var1 = interQuartileMean(var4).doubleValue();
                var4 = null;
                break;
            case 14:
                throw new IllegalArgumentException("Complex interquartile mean is not supported");
            default:
                throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
        }

        return var1;
    }

    public BigDecimal interQuartileMean_as_BigDecimal() {
        BigDecimal var1 = BigDecimal.ZERO;
        switch(this.type) {
            case 1:
            case 12:
                BigDecimal[] var2 = this.getArray_as_BigDecimal();
                var1 = interQuartileMean(var2);
                var2 = null;
                return var1;
            case 14:
                throw new IllegalArgumentException("Complex interquartile mean is not supported");
            default:
                throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
        }
    }

    public double median() {
        return this.median_as_double();
    }

    public double median_as_double() {
        double var1 = 0.0D;
        switch(this.type) {
            case 1:
                double[] var3 = this.getArray_as_double();
                var1 = median(var3);
                break;
            case 12:
                BigDecimal[] var4 = this.getArray_as_BigDecimal();
                var1 = median(var4).doubleValue();
                var4 = null;
                break;
            case 14:
                throw new IllegalArgumentException("Complex median value not supported");
            default:
                throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
        }

        return var1;
    }

    public BigDecimal median_as_BigDecimal() {
        BigDecimal var1 = BigDecimal.ZERO;
        switch(this.type) {
            case 1:
            case 12:
                BigDecimal[] var2 = this.getArray_as_BigDecimal();
                var1 = median(var2);
                var2 = null;
                return var1;
            case 14:
                throw new IllegalArgumentException("Complex median value not supported");
            default:
                throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
        }
    }

    public double rms() {
        double var1 = 0.0D;
        switch(this.type) {
            case 1:
                double[] var3 = this.getArray_as_double();
                var1 = rms(var3);
                break;
            case 12:
                BigDecimal[] var4 = this.getArray_as_BigDecimal();
                var1 = rms(var4);
                var4 = null;
                break;
            case 14:
                throw new IllegalArgumentException("Complex root mean square is not supported");
            default:
                throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
        }

        return var1;
    }

    public double weightedRms() {
        if (!this.weightsSupplied) {
            System.out.println("weightedRms: no weights supplied - unweighted rms returned");
            return this.rms();
        } else {
            boolean var1 = weightingOptionS;
            if (this.weightingReset) {
                if (this.weightingOptionI) {
                    weightingOptionS = true;
                } else {
                    weightingOptionS = false;
                }
            }

            double var2 = 0.0D;
            switch(this.type) {
                case 1:
                    double[] var4 = this.getArray_as_double();
                    double[] var5 = this.amWeights.getArray_as_double();
                    var2 = rms(var4, var5);
                    break;
                case 12:
                    BigDecimal[] var6 = this.getArray_as_BigDecimal();
                    BigDecimal[] var7 = this.amWeights.getArray_as_BigDecimal();
                    var2 = rms(var6, var7);
                    var6 = null;
                    var7 = null;
                    break;
                case 14:
                    throw new IllegalArgumentException("Complex root mean square is not supported");
                default:
                    throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
            }

            weightingOptionS = var1;
            return var2;
        }
    }

    public double momentSkewness() {
        boolean var1 = nFactorOptionS;
        if (this.nFactorReset) {
            if (this.nFactorOptionI) {
                nFactorOptionS = true;
            } else {
                nFactorOptionS = false;
            }
        }

        double var2 = 0.0D;
        switch(this.type) {
            case 1:
                double[] var4 = this.getArray_as_double();
                var2 = momentSkewness(var4);
                break;
            case 12:
                BigDecimal[] var5 = this.getArray_as_BigDecimal();
                var2 = momentSkewness(var5);
                var5 = null;
                break;
            case 14:
                throw new IllegalArgumentException("Complex skewness is not supported");
            default:
                throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
        }

        nFactorOptionS = var1;
        return var2;
    }

    public double momentSkewness_as_double() {
        return this.momentSkewness();
    }

    public double medianSkewness() {
        boolean var1 = nFactorOptionS;
        if (this.nFactorReset) {
            if (this.nFactorOptionI) {
                nFactorOptionS = true;
            } else {
                nFactorOptionS = false;
            }
        }

        double var2 = 0.0D;
        switch(this.type) {
            case 1:
                double[] var4 = this.getArray_as_double();
                var2 = medianSkewness(var4);
                break;
            case 12:
                BigDecimal[] var5 = this.getArray_as_BigDecimal();
                var2 = medianSkewness(var5);
                var5 = null;
                break;
            case 14:
                throw new IllegalArgumentException("Complex skewness is not supported");
            default:
                throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
        }

        nFactorOptionS = var1;
        return var2;
    }

    public double medianSkewness_as_double() {
        return this.medianSkewness();
    }

    public double quartileSkewness() {
        boolean var1 = nFactorOptionS;
        if (this.nFactorReset) {
            if (this.nFactorOptionI) {
                nFactorOptionS = true;
            } else {
                nFactorOptionS = false;
            }
        }

        double var2 = 0.0D;
        switch(this.type) {
            case 1:
                double[] var4 = this.getArray_as_double();
                var2 = quartileSkewness(var4);
                break;
            case 12:
                BigDecimal[] var5 = this.getArray_as_BigDecimal();
                var2 = quartileSkewness(var5).doubleValue();
                var5 = null;
                break;
            case 14:
                throw new IllegalArgumentException("Complex skewness is not supported");
            default:
                throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
        }

        nFactorOptionS = var1;
        return var2;
    }

    public double quartileSkewness_as_double() {
        return this.quartileSkewness();
    }

    public BigDecimal quartileSkewness_as_BigDecimal() {
        boolean var1 = nFactorOptionS;
        if (this.nFactorReset) {
            if (this.nFactorOptionI) {
                nFactorOptionS = true;
            } else {
                nFactorOptionS = false;
            }
        }

        BigDecimal var2 = BigDecimal.ZERO;
        switch(this.type) {
            case 1:
            case 12:
                BigDecimal[] var3 = this.getArray_as_BigDecimal();
                var2 = quartileSkewness(var3);
                var3 = null;
                nFactorOptionS = var1;
                return var2;
            case 14:
                throw new IllegalArgumentException("Complex skewness is not supported");
            default:
                throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
        }
    }

    public double kurtosis() {
        return this.kurtosis_as_double();
    }

    public double kurtosis_as_double() {
        boolean var1 = nFactorOptionS;
        if (this.nFactorReset) {
            if (this.nFactorOptionI) {
                nFactorOptionS = true;
            } else {
                nFactorOptionS = false;
            }
        }

        double var2 = 0.0D;
        switch(this.type) {
            case 1:
                double[] var4 = this.getArray_as_double();
                var2 = kurtosis(var4);
                break;
            case 12:
                BigDecimal[] var5 = this.getArray_as_BigDecimal();
                var2 = kurtosis(var5).doubleValue();
                var5 = null;
                break;
            case 14:
                throw new IllegalArgumentException("Complex kurtosis is not supported");
            default:
                throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
        }

        nFactorOptionS = var1;
        return var2;
    }

    public double curtosis() {
        return this.kurtosis_as_double();
    }

    public double curtosis_as_double() {
        return this.kurtosis_as_double();
    }

    public double kurtosisExcess() {
        return this.kurtosisExcess_as_double();
    }

    public double excessKurtosis() {
        return this.kurtosisExcess_as_double();
    }

    public double excessCurtosis() {
        return this.kurtosisExcess_as_double();
    }

    public double kurtosisExcess_as_double() {
        boolean var1 = nFactorOptionS;
        if (this.nFactorReset) {
            if (this.nFactorOptionI) {
                nFactorOptionS = true;
            } else {
                nFactorOptionS = false;
            }
        }

        double var2 = 0.0D;
        switch(this.type) {
            case 1:
                double[] var4 = this.getArray_as_double();
                var2 = kurtosisExcess(var4);
                break;
            case 12:
                BigDecimal[] var5 = this.getArray_as_BigDecimal();
                var2 = kurtosisExcess(var5).doubleValue();
                var5 = null;
                break;
            case 14:
                throw new IllegalArgumentException("Complex kurtosis is not supported");
            default:
                throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
        }

        nFactorOptionS = var1;
        return var2;
    }

    public double excessKurtosis_as_double() {
        return this.kurtosisExcess_as_double();
    }

    public double curtosisExcess() {
        return this.kurtosisExcess_as_double();
    }

    public double curtosisExcess_as_double() {
        return this.kurtosisExcess_as_double();
    }

    public double excessCurtosis_as_double() {
        return this.kurtosisExcess_as_double();
    }

    public BigDecimal kurtosis_as_BigDecimal() {
        boolean var1 = nFactorOptionS;
        if (this.nFactorReset) {
            if (this.nFactorOptionI) {
                nFactorOptionS = true;
            } else {
                nFactorOptionS = false;
            }
        }

        BigDecimal var2 = BigDecimal.ZERO;
        switch(this.type) {
            case 1:
            case 12:
                BigDecimal[] var3 = this.getArray_as_BigDecimal();
                var2 = kurtosis(var3);
                var3 = null;
                nFactorOptionS = var1;
                return var2;
            case 14:
                throw new IllegalArgumentException("Complex kurtosis is not supported");
            default:
                throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
        }
    }

    public BigDecimal curtosis_as_BigDecimal() {
        return this.kurtosis_as_BigDecimal();
    }

    public BigDecimal kurtosisExcess_as_BigDecimal() {
        boolean var1 = nFactorOptionS;
        if (this.nFactorReset) {
            if (this.nFactorOptionI) {
                nFactorOptionS = true;
            } else {
                nFactorOptionS = false;
            }
        }

        BigDecimal var2 = BigDecimal.ZERO;
        switch(this.type) {
            case 1:
            case 12:
                BigDecimal[] var3 = this.getArray_as_BigDecimal();
                var2 = kurtosisExcess(var3);
                var3 = null;
                nFactorOptionS = var1;
                return var2;
            case 14:
                throw new IllegalArgumentException("Complex kurtosis is not supported");
            default:
                throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
        }
    }

    public BigDecimal excessKurtosis_as_BigDecimal() {
        return this.kurtosisExcess_as_BigDecimal();
    }

    public BigDecimal curtosisExcess_as_BigDecimal() {
        return this.kurtosisExcess_as_BigDecimal();
    }

    public BigDecimal excessCurtosis_as_BigDecimal() {
        return this.kurtosisExcess_as_BigDecimal();
    }

    public double variance() {
        return this.variance_as_double();
    }

    public double variance_as_double() {
        boolean var1 = nFactorOptionS;
        if (this.nFactorReset) {
            if (this.nFactorOptionI) {
                nFactorOptionS = true;
            } else {
                nFactorOptionS = false;
            }
        }

        double var2 = 0.0D;
        switch(this.type) {
            case 1:
                double[] var4 = this.getArray_as_double();
                var2 = variance(var4);
                break;
            case 12:
                BigDecimal[] var5 = this.getArray_as_BigDecimal();
                var2 = variance(var5).doubleValue();
                var5 = null;
                break;
            case 14:
                throw new IllegalArgumentException("Complex cannot be converted to double");
            default:
                throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
        }

        nFactorOptionS = var1;
        return var2;
    }

    public BigDecimal variance_as_BigDecimal() {
        boolean var1 = nFactorOptionS;
        if (this.nFactorReset) {
            if (this.nFactorOptionI) {
                nFactorOptionS = true;
            } else {
                nFactorOptionS = false;
            }
        }

        BigDecimal var2 = BigDecimal.ZERO;
        switch(this.type) {
            case 1:
            case 12:
                BigDecimal[] var3 = this.getArray_as_BigDecimal();
                var2 = variance(var3);
                var3 = null;
                nFactorOptionS = var1;
                return var2;
            case 14:
                throw new IllegalArgumentException("Complex cannot be converted to BigDecimal");
            default:
                throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
        }
    }

    public Complex variance_as_Complex() {
        boolean var1 = nFactorOptionS;
        if (this.nFactorReset) {
            if (this.nFactorOptionI) {
                nFactorOptionS = true;
            } else {
                nFactorOptionS = false;
            }
        }

        Complex var2 = Complex.zero();
        Complex[] var3 = this.getArray_as_Complex();
        var2 = variance(var3);
        nFactorOptionS = var1;
        return var2;
    }

    public double variance_as_Complex_ConjugateCalcn() {
        boolean var1 = nFactorOptionS;
        if (this.nFactorReset) {
            if (this.nFactorOptionI) {
                nFactorOptionS = true;
            } else {
                nFactorOptionS = false;
            }
        }

        Complex[] var2 = this.getArray_as_Complex();
        double var3 = varianceConjugateCalcn(var2);
        nFactorOptionS = var1;
        return var3;
    }

    public double variance_of_ComplexModuli() {
        boolean var1 = nFactorOptionS;
        if (this.nFactorReset) {
            if (this.nFactorOptionI) {
                nFactorOptionS = true;
            } else {
                nFactorOptionS = false;
            }
        }

        double[] var2 = this.array_as_modulus_of_Complex();
        double var3 = variance(var2);
        nFactorOptionS = var1;
        return var3;
    }

    public double variance_of_ComplexRealParts() {
        boolean var1 = nFactorOptionS;
        if (this.nFactorReset) {
            if (this.nFactorOptionI) {
                nFactorOptionS = true;
            } else {
                nFactorOptionS = false;
            }
        }

        double[] var2 = this.array_as_real_part_of_Complex();
        double var3 = variance(var2);
        nFactorOptionS = var1;
        return var3;
    }

    public double variance_of_ComplexImaginaryParts() {
        boolean var1 = nFactorOptionS;
        if (this.nFactorReset) {
            if (this.nFactorOptionI) {
                nFactorOptionS = true;
            } else {
                nFactorOptionS = false;
            }
        }

        double[] var2 = this.array_as_imaginary_part_of_Complex();
        double var3 = variance(var2);
        nFactorOptionS = var1;
        return var3;
    }

    public double weightedVariance() {
        return this.weightedVariance_as_double();
    }

    public double weightedVariance_as_double() {
        boolean var1 = nFactorOptionS;
        if (this.nFactorReset) {
            if (this.nFactorOptionI) {
                nFactorOptionS = true;
            } else {
                nFactorOptionS = false;
            }
        }

        boolean var2 = nEffOptionS;
        if (this.nEffReset) {
            if (this.nEffOptionI) {
                nEffOptionS = true;
            } else {
                nEffOptionS = false;
            }
        }

        boolean var3 = weightingOptionS;
        if (this.weightingReset) {
            if (this.weightingOptionI) {
                weightingOptionS = true;
            } else {
                weightingOptionS = false;
            }
        }

        double var4 = 0.0D / 0.0;
        if (!this.weightsSupplied) {
            System.out.println("weightedVariance_as_double: no weights supplied - unweighted value returned");
            var4 = this.variance_as_double();
        } else {
            double var6 = 0.0D;
            switch(this.type) {
                case 1:
                    double[] var8 = this.getArray_as_double();
                    double[] var9 = this.amWeights.getArray_as_double();
                    var6 = variance(var8, var9);
                    break;
                case 12:
                    BigDecimal[] var10 = this.getArray_as_BigDecimal();
                    BigDecimal[] var11 = this.amWeights.getArray_as_BigDecimal();
                    var6 = variance(var10, var11).doubleValue();
                    var10 = null;
                    var11 = null;
                    break;
                case 14:
                    throw new IllegalArgumentException("Complex cannot be converted to double");
                default:
                    throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
            }

            var4 = var6;
        }

        nFactorOptionS = var1;
        nEffOptionS = var2;
        weightingOptionS = var3;
        return var4;
    }

    public BigDecimal weightedVariance_as_BigDecimal() {
        boolean var1 = nFactorOptionS;
        if (this.nFactorReset) {
            if (this.nFactorOptionI) {
                nFactorOptionS = true;
            } else {
                nFactorOptionS = false;
            }
        }

        boolean var2 = nEffOptionS;
        if (this.nEffReset) {
            if (this.nEffOptionI) {
                nEffOptionS = true;
            } else {
                nEffOptionS = false;
            }
        }

        boolean var3 = weightingOptionS;
        if (this.weightingReset) {
            if (this.weightingOptionI) {
                weightingOptionS = true;
            } else {
                weightingOptionS = false;
            }
        }

        BigDecimal var4 = BigDecimal.ZERO;
        if (!this.weightsSupplied) {
            System.out.println("weightedVariance_as_BigDecimal: no weights supplied - unweighted value returned");
            var4 = this.variance_as_BigDecimal();
        } else {
            BigDecimal var5 = BigDecimal.ZERO;
            switch(this.type) {
                case 1:
                case 12:
                    BigDecimal[] var6 = this.getArray_as_BigDecimal();
                    BigDecimal[] var7 = this.amWeights.getArray_as_BigDecimal();
                    var5 = variance(var6, var7);
                    var6 = null;
                    var7 = null;
                    var4 = var5;
                    break;
                case 14:
                    throw new IllegalArgumentException("Complex cannot be converted to BigDecimal");
                default:
                    throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
            }
        }

        nFactorOptionS = var1;
        nEffOptionS = var2;
        weightingOptionS = var3;
        return var4;
    }

    public Complex weightedVariance_as_Complex() {
        boolean var1 = nFactorOptionS;
        if (this.nFactorReset) {
            if (this.nFactorOptionI) {
                nFactorOptionS = true;
            } else {
                nFactorOptionS = false;
            }
        }

        boolean var2 = nEffOptionS;
        if (this.nEffReset) {
            if (this.nEffOptionI) {
                nEffOptionS = true;
            } else {
                nEffOptionS = false;
            }
        }

        boolean var3 = weightingOptionS;
        if (this.weightingReset) {
            if (this.weightingOptionI) {
                weightingOptionS = true;
            } else {
                weightingOptionS = false;
            }
        }

        Complex var4 = Complex.zero();
        if (!this.weightsSupplied) {
            System.out.println("weightedVariance_as_Complex: no weights supplied - unweighted value returned");
            var4 = this.variance_as_Complex();
        } else {
            Complex var5 = Complex.zero();
            Complex[] var6 = this.getArray_as_Complex();
            Complex[] var7 = this.amWeights.getArray_as_Complex();
            var5 = variance(var6, var7);
            var4 = var5;
        }

        nFactorOptionS = var1;
        nEffOptionS = var2;
        weightingOptionS = var3;
        return var4;
    }

    public double weightedVariance_as_Complex_ConjugateCalcn() {
        boolean var1 = nFactorOptionS;
        if (this.nFactorReset) {
            if (this.nFactorOptionI) {
                nFactorOptionS = true;
            } else {
                nFactorOptionS = false;
            }
        }

        boolean var2 = nEffOptionS;
        if (this.nEffReset) {
            if (this.nEffOptionI) {
                nEffOptionS = true;
            } else {
                nEffOptionS = false;
            }
        }

        boolean var3 = weightingOptionS;
        if (this.weightingReset) {
            if (this.weightingOptionI) {
                weightingOptionS = true;
            } else {
                weightingOptionS = false;
            }
        }

        double var4 = 0.0D / 0.0;
        if (!this.weightsSupplied) {
            System.out.println("weightedVariance_as_Complex: no weights supplied - unweighted value returned");
            var4 = this.variance_as_Complex_ConjugateCalcn();
        } else {
            Complex[] var6 = this.getArray_as_Complex();
            Complex[] var7 = this.amWeights.getArray_as_Complex();
            var4 = varianceConjugateCalcn(var6, var7);
        }

        nFactorOptionS = var1;
        nEffOptionS = var2;
        weightingOptionS = var3;
        return var4;
    }

    public double weightedVariance_of_ComplexModuli() {
        boolean var1 = nFactorOptionS;
        if (this.nFactorReset) {
            if (this.nFactorOptionI) {
                nFactorOptionS = true;
            } else {
                nFactorOptionS = false;
            }
        }

        boolean var2 = nEffOptionS;
        if (this.nEffReset) {
            if (this.nEffOptionI) {
                nEffOptionS = true;
            } else {
                nEffOptionS = false;
            }
        }

        boolean var3 = weightingOptionS;
        if (this.weightingReset) {
            if (this.weightingOptionI) {
                weightingOptionS = true;
            } else {
                weightingOptionS = false;
            }
        }

        double var4 = 0.0D / 0.0;
        if (!this.weightsSupplied) {
            System.out.println("weightedVariance_as_Complex: no weights supplied - unweighted value returned");
            var4 = this.variance_of_ComplexModuli();
        } else {
            double[] var6 = this.array_as_modulus_of_Complex();
            double[] var7 = this.amWeights.array_as_modulus_of_Complex();
            var4 = variance(var6, var7);
        }

        nFactorOptionS = var1;
        nEffOptionS = var2;
        weightingOptionS = var3;
        return var4;
    }

    public double weightedVariance_of_ComplexRealParts() {
        boolean var1 = nFactorOptionS;
        if (this.nFactorReset) {
            if (this.nFactorOptionI) {
                nFactorOptionS = true;
            } else {
                nFactorOptionS = false;
            }
        }

        boolean var2 = nEffOptionS;
        if (this.nEffReset) {
            if (this.nEffOptionI) {
                nEffOptionS = true;
            } else {
                nEffOptionS = false;
            }
        }

        boolean var3 = weightingOptionS;
        if (this.weightingReset) {
            if (this.weightingOptionI) {
                weightingOptionS = true;
            } else {
                weightingOptionS = false;
            }
        }

        double var4 = 0.0D / 0.0;
        if (!this.weightsSupplied) {
            System.out.println("weightedVariance_as_Complex: no weights supplied - unweighted value returned");
            var4 = this.variance_of_ComplexRealParts();
        } else {
            double[] var6 = this.array_as_real_part_of_Complex();
            double[] var7 = this.amWeights.array_as_real_part_of_Complex();
            var4 = variance(var6, var7);
        }

        nFactorOptionS = var1;
        nEffOptionS = var2;
        weightingOptionS = var3;
        return var4;
    }

    public double weightedVariance_of_ComplexImaginaryParts() {
        boolean var1 = nFactorOptionS;
        if (this.nFactorReset) {
            if (this.nFactorOptionI) {
                nFactorOptionS = true;
            } else {
                nFactorOptionS = false;
            }
        }

        boolean var2 = nEffOptionS;
        if (this.nEffReset) {
            if (this.nEffOptionI) {
                nEffOptionS = true;
            } else {
                nEffOptionS = false;
            }
        }

        boolean var3 = weightingOptionS;
        if (this.weightingReset) {
            if (this.weightingOptionI) {
                weightingOptionS = true;
            } else {
                weightingOptionS = false;
            }
        }

        double var4 = 0.0D / 0.0;
        if (!this.weightsSupplied) {
            System.out.println("weightedVariance_as_Complex: no weights supplied - unweighted value returned");
            var4 = this.variance_of_ComplexImaginaryParts();
        } else {
            double[] var6 = this.array_as_imaginary_part_of_Complex();
            double[] var7 = this.amWeights.array_as_imaginary_part_of_Complex();
            var4 = variance(var6, var7);
        }

        nFactorOptionS = var1;
        nEffOptionS = var2;
        weightingOptionS = var3;
        return var4;
    }

    public double standardDeviation() {
        return this.standardDeviation_as_double();
    }

    public double standardDeviation_as_double() {
        boolean var1 = nFactorOptionS;
        if (this.nFactorReset) {
            if (this.nFactorOptionI) {
                nFactorOptionS = true;
            } else {
                nFactorOptionS = false;
            }
        }

        double var2 = 0.0D;
        switch(this.type) {
            case 1:
                double[] var4 = this.getArray_as_double();
                var2 = variance(var4);
                break;
            case 12:
                BigDecimal[] var5 = this.getArray_as_BigDecimal();
                var2 = variance(var5).doubleValue();
                var5 = null;
                break;
            case 14:
                throw new IllegalArgumentException("Complex cannot be converted to double");
            default:
                throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
        }

        nFactorOptionS = var1;
        return Math.sqrt(var2);
    }

    public Complex standardDeviation_as_Complex() {
        boolean var1 = nFactorOptionS;
        if (this.nFactorReset) {
            if (this.nFactorOptionI) {
                nFactorOptionS = true;
            } else {
                nFactorOptionS = false;
            }
        }

        Complex var2 = Complex.zero();
        Complex[] var3 = this.getArray_as_Complex();
        var2 = variance(var3);
        nFactorOptionS = var1;
        return Complex.sqrt(var2);
    }

    public double standardDeviation_as_Complex_ConjugateCalcn() {
        boolean var1 = nFactorOptionS;
        if (this.nFactorReset) {
            if (this.nFactorOptionI) {
                nFactorOptionS = true;
            } else {
                nFactorOptionS = false;
            }
        }

        Complex[] var2 = this.getArray_as_Complex();
        double var3 = varianceConjugateCalcn(var2);
        nFactorOptionS = var1;
        return Math.sqrt(var3);
    }

    public double standardDeviation_of_ComplexModuli() {
        boolean var1 = nFactorOptionS;
        if (this.nFactorReset) {
            if (this.nFactorOptionI) {
                nFactorOptionS = true;
            } else {
                nFactorOptionS = false;
            }
        }

        double[] var2 = this.array_as_modulus_of_Complex();
        double var3 = standardDeviation(var2);
        nFactorOptionS = var1;
        return var3;
    }

    public double standardDeviation_of_ComplexRealParts() {
        boolean var1 = nFactorOptionS;
        if (this.nFactorReset) {
            if (this.nFactorOptionI) {
                nFactorOptionS = true;
            } else {
                nFactorOptionS = false;
            }
        }

        double[] var2 = this.array_as_real_part_of_Complex();
        double var3 = standardDeviation(var2);
        nFactorOptionS = var1;
        return var3;
    }

    public double standardDeviation_of_ComplexImaginaryParts() {
        boolean var1 = nFactorOptionS;
        if (this.nFactorReset) {
            if (this.nFactorOptionI) {
                nFactorOptionS = true;
            } else {
                nFactorOptionS = false;
            }
        }

        double[] var2 = this.array_as_imaginary_part_of_Complex();
        double var3 = standardDeviation(var2);
        nFactorOptionS = var1;
        return var3;
    }

    public double weightedStandardDeviation() {
        return this.weightedStandardDeviation_as_double();
    }

    public double weightedStandardDeviation_as_double() {
        boolean var1 = nFactorOptionS;
        if (this.nFactorReset) {
            if (this.nFactorOptionI) {
                nFactorOptionS = true;
            } else {
                nFactorOptionS = false;
            }
        }

        boolean var2 = weightingOptionS;
        if (this.weightingReset) {
            if (this.weightingOptionI) {
                weightingOptionS = true;
            } else {
                weightingOptionS = false;
            }
        }

        double var3 = 0.0D;
        if (!this.weightsSupplied) {
            System.out.println("weightedStandardDeviation_as_double: no weights supplied - unweighted value returned");
            var3 = this.standardDeviation_as_double();
        } else {
            double var5 = 0.0D;
            switch(this.type) {
                case 1:
                    double[] var7 = this.getArray_as_double();
                    double[] var8 = this.amWeights.getArray_as_double();
                    var5 = variance(var7, var8);
                    break;
                case 12:
                    BigDecimal[] var9 = this.getArray_as_BigDecimal();
                    BigDecimal[] var10 = this.amWeights.getArray_as_BigDecimal();
                    var5 = variance(var9, var10).doubleValue();
                    var9 = null;
                    var10 = null;
                    break;
                case 14:
                    throw new IllegalArgumentException("Complex cannot be converted to double");
                default:
                    throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
            }

            var3 = Math.sqrt(var5);
        }

        nFactorOptionS = var1;
        weightingOptionS = var2;
        return var3;
    }

    public Complex weightedStandardDeviation_as_Complex() {
        boolean var1 = nFactorOptionS;
        if (this.nFactorReset) {
            if (this.nFactorOptionI) {
                nFactorOptionS = true;
            } else {
                nFactorOptionS = false;
            }
        }

        boolean var2 = weightingOptionS;
        if (this.weightingReset) {
            if (this.weightingOptionI) {
                weightingOptionS = true;
            } else {
                weightingOptionS = false;
            }
        }

        Complex var3 = Complex.zero();
        if (!this.weightsSupplied) {
            System.out.println("weightedtandardDeviationS_as_Complex: no weights supplied - unweighted value returned");
            var3 = this.standardDeviation_as_Complex();
        } else {
            Complex var4 = Complex.zero();
            Complex[] var5 = this.getArray_as_Complex();
            Complex[] var6 = this.amWeights.getArray_as_Complex();
            var4 = variance(var5, var6);
            var3 = Complex.sqrt(var4);
        }

        nFactorOptionS = var1;
        weightingOptionS = var2;
        return var3;
    }

    public double weightedStandardDeviation_as_Complex_ConjugateCalcn() {
        boolean var1 = nFactorOptionS;
        if (this.nFactorReset) {
            if (this.nFactorOptionI) {
                nFactorOptionS = true;
            } else {
                nFactorOptionS = false;
            }
        }

        boolean var2 = weightingOptionS;
        if (this.weightingReset) {
            if (this.weightingOptionI) {
                weightingOptionS = true;
            } else {
                weightingOptionS = false;
            }
        }

        double var3 = 0.0D / 0.0;
        if (!this.weightsSupplied) {
            System.out.println("weightedtandardDeviationS_as_Complex: no weights supplied - unweighted value returned");
            var3 = this.standardDeviation_as_Complex_ConjugateCalcn();
        } else {
            double var5 = 0.0D / 0.0;
            Complex[] var7 = this.getArray_as_Complex();
            Complex[] var8 = this.amWeights.getArray_as_Complex();
            var5 = varianceConjugateCalcn(var7, var8);
            var3 = Math.sqrt(var5);
        }

        nFactorOptionS = var1;
        weightingOptionS = var2;
        return var3;
    }

    public double weightedStandardDeviation_of_ComplexModuli() {
        boolean var1 = nFactorOptionS;
        if (this.nFactorReset) {
            if (this.nFactorOptionI) {
                nFactorOptionS = true;
            } else {
                nFactorOptionS = false;
            }
        }

        boolean var2 = nEffOptionS;
        if (this.nEffReset) {
            if (this.nEffOptionI) {
                nEffOptionS = true;
            } else {
                nEffOptionS = false;
            }
        }

        boolean var3 = weightingOptionS;
        if (this.weightingReset) {
            if (this.weightingOptionI) {
                weightingOptionS = true;
            } else {
                weightingOptionS = false;
            }
        }

        double var4 = 0.0D / 0.0;
        if (!this.weightsSupplied) {
            System.out.println("weightedStandardDeviation_as_Complex: no weights supplied - unweighted value returned");
            var4 = this.standardDeviation_of_ComplexModuli();
        } else {
            double[] var6 = this.array_as_modulus_of_Complex();
            double[] var7 = this.amWeights.array_as_modulus_of_Complex();
            var4 = standardDeviation(var6, var7);
        }

        nFactorOptionS = var1;
        nEffOptionS = var2;
        weightingOptionS = var3;
        return var4;
    }

    public double weightedStandardDeviation_of_ComplexRealParts() {
        boolean var1 = nFactorOptionS;
        if (this.nFactorReset) {
            if (this.nFactorOptionI) {
                nFactorOptionS = true;
            } else {
                nFactorOptionS = false;
            }
        }

        boolean var2 = nEffOptionS;
        if (this.nEffReset) {
            if (this.nEffOptionI) {
                nEffOptionS = true;
            } else {
                nEffOptionS = false;
            }
        }

        boolean var3 = weightingOptionS;
        if (this.weightingReset) {
            if (this.weightingOptionI) {
                weightingOptionS = true;
            } else {
                weightingOptionS = false;
            }
        }

        double var4 = 0.0D / 0.0;
        if (!this.weightsSupplied) {
            System.out.println("weightedStandardDeviation_as_Complex: no weights supplied - unweighted value returned");
            var4 = this.standardDeviation_of_ComplexRealParts();
        } else {
            double[] var6 = this.array_as_real_part_of_Complex();
            double[] var7 = this.amWeights.array_as_real_part_of_Complex();
            var4 = standardDeviation(var6, var7);
        }

        nFactorOptionS = var1;
        nEffOptionS = var2;
        weightingOptionS = var3;
        return var4;
    }

    public double weightedStandardDeviation_of_ComplexImaginaryParts() {
        boolean var1 = nFactorOptionS;
        if (this.nFactorReset) {
            if (this.nFactorOptionI) {
                nFactorOptionS = true;
            } else {
                nFactorOptionS = false;
            }
        }

        boolean var2 = nEffOptionS;
        if (this.nEffReset) {
            if (this.nEffOptionI) {
                nEffOptionS = true;
            } else {
                nEffOptionS = false;
            }
        }

        boolean var3 = weightingOptionS;
        if (this.weightingReset) {
            if (this.weightingOptionI) {
                weightingOptionS = true;
            } else {
                weightingOptionS = false;
            }
        }

        double var4 = 0.0D / 0.0;
        if (!this.weightsSupplied) {
            System.out.println("weightedStandardDeviation_as_Complex: no weights supplied - unweighted value returned");
            var4 = this.standardDeviation_of_ComplexImaginaryParts();
        } else {
            double[] var6 = this.array_as_imaginary_part_of_Complex();
            double[] var7 = this.amWeights.array_as_imaginary_part_of_Complex();
            var4 = standardDeviation(var6, var7);
        }

        nFactorOptionS = var1;
        nEffOptionS = var2;
        weightingOptionS = var3;
        return var4;
    }

    public double standardError() {
        return this.standardError_as_double();
    }

    public double standardError_as_double() {
        boolean var1 = nFactorOptionS;
        if (this.nFactorReset) {
            if (this.nFactorOptionI) {
                nFactorOptionS = true;
            } else {
                nFactorOptionS = false;
            }
        }

        double var2 = 0.0D;
        switch(this.type) {
            case 1:
                double[] var4 = this.getArray_as_double();
                var2 = standardError(var4);
                break;
            case 12:
                BigDecimal[] var5 = this.getArray_as_BigDecimal();
                var2 = standardError(var5);
                var5 = null;
                break;
            case 14:
                throw new IllegalArgumentException("Complex cannot be converted to double");
            default:
                throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
        }

        nFactorOptionS = var1;
        return var2;
    }

    public Complex standardError_as_Complex() {
        boolean var1 = nFactorOptionS;
        if (this.nFactorReset) {
            if (this.nFactorOptionI) {
                nFactorOptionS = true;
            } else {
                nFactorOptionS = false;
            }
        }

        Complex var2 = Complex.zero();
        Complex[] var3 = this.getArray_as_Complex();
        var2 = standardError(var3);
        nFactorOptionS = var1;
        return var2;
    }

    public double standardError_as_Complex_ConjugateCalcn() {
        boolean var1 = nFactorOptionS;
        if (this.nFactorReset) {
            if (this.nFactorOptionI) {
                nFactorOptionS = true;
            } else {
                nFactorOptionS = false;
            }
        }

        Complex[] var2 = this.getArray_as_Complex();
        double var3 = standardErrorConjugateCalcn(var2);
        nFactorOptionS = var1;
        return var3;
    }

    public double standardError_of_ComplexModuli() {
        boolean var1 = nFactorOptionS;
        if (this.nFactorReset) {
            if (this.nFactorOptionI) {
                nFactorOptionS = true;
            } else {
                nFactorOptionS = false;
            }
        }

        double[] var2 = this.array_as_modulus_of_Complex();
        double var3 = standardError(var2);
        nFactorOptionS = var1;
        return var3;
    }

    public double standardError_of_ComplexRealParts() {
        boolean var1 = nFactorOptionS;
        if (this.nFactorReset) {
            if (this.nFactorOptionI) {
                nFactorOptionS = true;
            } else {
                nFactorOptionS = false;
            }
        }

        double[] var2 = this.array_as_real_part_of_Complex();
        double var3 = standardError(var2);
        nFactorOptionS = var1;
        return var3;
    }

    public double standardError_of_ComplexImaginaryParts() {
        boolean var1 = nFactorOptionS;
        if (this.nFactorReset) {
            if (this.nFactorOptionI) {
                nFactorOptionS = true;
            } else {
                nFactorOptionS = false;
            }
        }

        double[] var2 = this.array_as_imaginary_part_of_Complex();
        double var3 = standardError(var2);
        nFactorOptionS = var1;
        return var3;
    }

    public double weightedStandardError() {
        return this.weightedStandardError_as_double();
    }

    public double weightedStandardError_as_double() {
        boolean var1 = nFactorOptionS;
        if (this.nFactorReset) {
            if (this.nFactorOptionI) {
                nFactorOptionS = true;
            } else {
                nFactorOptionS = false;
            }
        }

        boolean var2 = nEffOptionS;
        if (this.nEffReset) {
            if (this.nEffOptionI) {
                nEffOptionS = true;
            } else {
                nEffOptionS = false;
            }
        }

        boolean var3 = weightingOptionS;
        if (this.weightingReset) {
            if (this.weightingOptionI) {
                weightingOptionS = true;
            } else {
                weightingOptionS = false;
            }
        }

        double var4 = 0.0D;
        if (!this.weightsSupplied) {
            System.out.println("weightedStandardError_as_double: no weights supplied - unweighted value returned");
            var4 = this.standardError_as_double();
        } else {
            switch(this.type) {
                case 1:
                    double[] var6 = this.getArray_as_double();
                    double[] var7 = this.amWeights.getArray_as_double();
                    var4 = standardError(var6, var7);
                    break;
                case 12:
                    BigDecimal[] var8 = this.getArray_as_BigDecimal();
                    BigDecimal[] var9 = this.amWeights.getArray_as_BigDecimal();
                    var4 = standardError(var8, var9);
                    var8 = null;
                    var9 = null;
                    break;
                case 14:
                    throw new IllegalArgumentException("Complex cannot be converted to double");
                default:
                    throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
            }

            var4 = Math.sqrt(var4);
        }

        nFactorOptionS = var1;
        nEffOptionS = var2;
        weightingOptionS = var3;
        return var4;
    }

    public Complex weightedStandarError_as_Complex() {
        boolean var1 = nFactorOptionS;
        if (this.nFactorReset) {
            if (this.nFactorOptionI) {
                nFactorOptionS = true;
            } else {
                nFactorOptionS = false;
            }
        }

        boolean var2 = nEffOptionS;
        if (this.nEffReset) {
            if (this.nEffOptionI) {
                nEffOptionS = true;
            } else {
                nEffOptionS = false;
            }
        }

        boolean var3 = weightingOptionS;
        if (this.weightingReset) {
            if (this.weightingOptionI) {
                weightingOptionS = true;
            } else {
                weightingOptionS = false;
            }
        }

        Complex var4 = Complex.zero();
        if (!this.weightsSupplied) {
            System.out.println("weightedStandardError_as_Complex: no weights supplied - unweighted value returned");
            var4 = this.standardError_as_Complex();
        } else {
            Complex[] var5 = this.getArray_as_Complex();
            Complex[] var6 = this.amWeights.getArray_as_Complex();
            var4 = standardError(var5, var6);
        }

        nFactorOptionS = var1;
        nEffOptionS = var2;
        weightingOptionS = var3;
        return var4;
    }

    public double weightedStandarError_as_Complex_ConjugateCalcn() {
        boolean var1 = nFactorOptionS;
        if (this.nFactorReset) {
            if (this.nFactorOptionI) {
                nFactorOptionS = true;
            } else {
                nFactorOptionS = false;
            }
        }

        boolean var2 = nEffOptionS;
        if (this.nEffReset) {
            if (this.nEffOptionI) {
                nEffOptionS = true;
            } else {
                nEffOptionS = false;
            }
        }

        boolean var3 = weightingOptionS;
        if (this.weightingReset) {
            if (this.weightingOptionI) {
                weightingOptionS = true;
            } else {
                weightingOptionS = false;
            }
        }

        double var4 = 0.0D / 0.0;
        if (!this.weightsSupplied) {
            System.out.println("weightedStandardError_as_Complex: no weights supplied - unweighted value returned");
            var4 = this.standardError_as_Complex_ConjugateCalcn();
        } else {
            Complex[] var6 = this.getArray_as_Complex();
            Complex[] var7 = this.amWeights.getArray_as_Complex();
            var4 = standardErrorConjugateCalcn(var6, var7);
        }

        nFactorOptionS = var1;
        nEffOptionS = var2;
        weightingOptionS = var3;
        return var4;
    }

    public double weightedStandardError_of_ComplexModuli() {
        boolean var1 = nFactorOptionS;
        if (this.nFactorReset) {
            if (this.nFactorOptionI) {
                nFactorOptionS = true;
            } else {
                nFactorOptionS = false;
            }
        }

        boolean var2 = nEffOptionS;
        if (this.nEffReset) {
            if (this.nEffOptionI) {
                nEffOptionS = true;
            } else {
                nEffOptionS = false;
            }
        }

        boolean var3 = weightingOptionS;
        if (this.weightingReset) {
            if (this.weightingOptionI) {
                weightingOptionS = true;
            } else {
                weightingOptionS = false;
            }
        }

        double var4 = 0.0D / 0.0;
        if (!this.weightsSupplied) {
            System.out.println("weightedStandardError_as_Complex: no weights supplied - unweighted value returned");
            var4 = this.standardError_of_ComplexModuli();
        } else {
            double[] var6 = this.array_as_modulus_of_Complex();
            double[] var7 = this.amWeights.array_as_modulus_of_Complex();
            var4 = standardError(var6, var7);
        }

        nFactorOptionS = var1;
        nEffOptionS = var2;
        weightingOptionS = var3;
        return var4;
    }

    public double weightedStandardError_of_ComplexRealParts() {
        boolean var1 = nFactorOptionS;
        if (this.nFactorReset) {
            if (this.nFactorOptionI) {
                nFactorOptionS = true;
            } else {
                nFactorOptionS = false;
            }
        }

        boolean var2 = nEffOptionS;
        if (this.nEffReset) {
            if (this.nEffOptionI) {
                nEffOptionS = true;
            } else {
                nEffOptionS = false;
            }
        }

        boolean var3 = weightingOptionS;
        if (this.weightingReset) {
            if (this.weightingOptionI) {
                weightingOptionS = true;
            } else {
                weightingOptionS = false;
            }
        }

        double var4 = 0.0D / 0.0;
        if (!this.weightsSupplied) {
            System.out.println("weightedStandardError_as_Complex: no weights supplied - unweighted value returned");
            var4 = this.standardError_of_ComplexRealParts();
        } else {
            double[] var6 = this.array_as_real_part_of_Complex();
            double[] var7 = this.amWeights.array_as_real_part_of_Complex();
            var4 = standardError(var6, var7);
        }

        nFactorOptionS = var1;
        nEffOptionS = var2;
        weightingOptionS = var3;
        return var4;
    }

    public double weightedStandardError_of_ComplexImaginaryParts() {
        boolean var1 = nFactorOptionS;
        if (this.nFactorReset) {
            if (this.nFactorOptionI) {
                nFactorOptionS = true;
            } else {
                nFactorOptionS = false;
            }
        }

        boolean var2 = nEffOptionS;
        if (this.nEffReset) {
            if (this.nEffOptionI) {
                nEffOptionS = true;
            } else {
                nEffOptionS = false;
            }
        }

        boolean var3 = weightingOptionS;
        if (this.weightingReset) {
            if (this.weightingOptionI) {
                weightingOptionS = true;
            } else {
                weightingOptionS = false;
            }
        }

        double var4 = 0.0D / 0.0;
        if (!this.weightsSupplied) {
            System.out.println("weightedStandardError_as_Complex: no weights supplied - unweighted value returned");
            var4 = this.standardError_of_ComplexImaginaryParts();
        } else {
            double[] var6 = this.array_as_imaginary_part_of_Complex();
            double[] var7 = this.amWeights.array_as_imaginary_part_of_Complex();
            var4 = standardError(var6, var7);
        }

        nFactorOptionS = var1;
        nEffOptionS = var2;
        weightingOptionS = var3;
        return var4;
    }

    public double[] standardize() {
        Object var1 = null;
        switch(this.type) {
            case 1:
            case 12:
                double[] var2 = this.getArray_as_double();
                double[] var3 = standardize(var2);
                return var3;
            case 14:
                throw new IllegalArgumentException("Standardisation of Complex is not supported");
            default:
                throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
        }
    }

    public double[] standardise() {
        return this.standardize();
    }

    public double[] scale(double var1, double var3) {
        Object var5 = null;
        switch(this.type) {
            case 1:
            case 12:
                double[] var6 = this.getArray_as_double();
                double[] var7 = scale(var6, var1, var3);
                return var7;
            case 14:
                throw new IllegalArgumentException("Scaling of Complex is not supported");
            default:
                throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
        }
    }

    public double volatilityLogChange() {
        double var1 = 0.0D;
        switch(this.type) {
            case 1:
                double[] var3 = this.getArray_as_double();
                var1 = volatilityLogChange(var3);
                break;
            case 12:
                BigDecimal[] var4 = this.getArray_as_BigDecimal();
                var1 = volatilityLogChange(var4);
                var4 = null;
                break;
            case 14:
                throw new IllegalArgumentException("Complex volatilty is not supported");
            default:
                throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
        }

        return var1;
    }

    public double volatilityPerCentChange() {
        double var1 = 0.0D;
        switch(this.type) {
            case 1:
                double[] var3 = this.getArray_as_double();
                var1 = volatilityPerCentChange(var3);
                break;
            case 12:
                BigDecimal[] var4 = this.getArray_as_BigDecimal();
                var1 = volatilityPerCentChange(var4);
                var4 = null;
                break;
            case 14:
                throw new IllegalArgumentException("Complex volatilty is not supported");
            default:
                throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
        }

        return var1;
    }

    public double coefficientOfVariation() {
        boolean var1 = nFactorOptionS;
        if (this.nFactorReset) {
            if (this.nFactorOptionI) {
                nFactorOptionS = true;
            } else {
                nFactorOptionS = false;
            }
        }

        double var2 = 0.0D;
        switch(this.type) {
            case 1:
                double[] var4 = this.getArray_as_double();
                var2 = coefficientOfVariation(var4);
                break;
            case 12:
                BigDecimal[] var5 = this.getArray_as_BigDecimal();
                var2 = coefficientOfVariation(var5);
                var5 = null;
                break;
            case 14:
                throw new IllegalArgumentException("Complex coefficient of variation is not supported");
            default:
                throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
        }

        nFactorOptionS = var1;
        return var2;
    }

    public double weightedCoefficientOfVariation() {
        boolean var1 = nFactorOptionS;
        if (this.nFactorReset) {
            if (this.nFactorOptionI) {
                nFactorOptionS = true;
            } else {
                nFactorOptionS = false;
            }
        }

        boolean var2 = weightingOptionS;
        if (this.weightingReset) {
            if (this.weightingOptionI) {
                weightingOptionS = true;
            } else {
                weightingOptionS = false;
            }
        }

        double var3 = 0.0D;
        switch(this.type) {
            case 1:
                double[] var5 = this.getArray_as_double();
                double[] var6 = this.amWeights.getArray_as_double();
                var3 = coefficientOfVariation(var5, var6);
                break;
            case 12:
                BigDecimal[] var7 = this.getArray_as_BigDecimal();
                BigDecimal[] var8 = this.amWeights.getArray_as_BigDecimal();
                var3 = coefficientOfVariation(var7, var8);
                var7 = null;
                var8 = null;
                break;
            case 14:
                throw new IllegalArgumentException("Complex coefficient of variation is not supported");
            default:
                throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
        }

        nFactorOptionS = var1;
        weightingOptionS = var2;
        return var3;
    }

    public double shannonEntropy() {
        double var1 = 0.0D;
        switch(this.type) {
            case 1:
            case 12:
                double[] var3 = this.getArray_as_double();
                var1 = shannonEntropy(var3);
                return var1;
            case 14:
                throw new IllegalArgumentException("Complex Shannon Entropy is not meaningful");
            default:
                throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
        }
    }

    public double shannonEntropyBit() {
        double var1 = 0.0D;
        switch(this.type) {
            case 1:
            case 12:
                double[] var3 = this.getArray_as_double();
                var1 = shannonEntropy(var3);
                return var1;
            case 14:
                throw new IllegalArgumentException("Complex Shannon Entropy is not meaningful");
            default:
                throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
        }
    }

    public double shannonEntropyNat() {
        double var1 = 0.0D;
        switch(this.type) {
            case 1:
            case 12:
                double[] var3 = this.getArray_as_double();
                var1 = shannonEntropyNat(var3);
                return var1;
            case 14:
                throw new IllegalArgumentException("Complex Shannon Entropy is not meaningful");
            default:
                throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
        }
    }

    public double shannonEntropyDit() {
        double var1 = 0.0D;
        switch(this.type) {
            case 1:
            case 12:
                double[] var3 = this.getArray_as_double();
                var1 = shannonEntropyDit(var3);
                return var1;
            case 14:
                throw new IllegalArgumentException("Complex Shannon Entropy is not meaningful");
            default:
                throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
        }
    }

    public double renyiEntropy(double var1) {
        double var3 = 0.0D;
        switch(this.type) {
            case 1:
            case 12:
                double[] var5 = this.getArray_as_double();
                var3 = renyiEntropy(var5, var1);
                return var3;
            case 14:
                throw new IllegalArgumentException("Complex Renyi Entropy is not meaningful");
            default:
                throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
        }
    }

    public double renyiEntropyBit(double var1) {
        double var3 = 0.0D;
        switch(this.type) {
            case 1:
            case 12:
                double[] var5 = this.getArray_as_double();
                var3 = renyiEntropy(var5, var1);
                return var3;
            case 14:
                throw new IllegalArgumentException("Complex Renyi Entropy is not meaningful");
            default:
                throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
        }
    }

    public double renyiEntropyNat(double var1) {
        double var3 = 0.0D;
        switch(this.type) {
            case 1:
            case 12:
                double[] var5 = this.getArray_as_double();
                var3 = renyiEntropyNat(var5, var1);
                return var3;
            case 14:
                throw new IllegalArgumentException("Complex Renyi Entropy is not meaningful");
            default:
                throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
        }
    }

    public double renyiEntropyDit(double var1) {
        double var3 = 0.0D;
        switch(this.type) {
            case 1:
            case 12:
                double[] var5 = this.getArray_as_double();
                var3 = renyiEntropyDit(var5, var1);
                return var3;
            case 14:
                throw new IllegalArgumentException("Complex Renyi Entropy is not meaningful");
            default:
                throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
        }
    }

    public double tsallisEntropyNat(double var1) {
        double var3 = 0.0D;
        switch(this.type) {
            case 1:
            case 12:
                double[] var5 = this.getArray_as_double();
                var3 = tsallisEntropyNat(var5, var1);
                return var3;
            case 14:
                throw new IllegalArgumentException("Complex Tsallis Entropy is not meaningful");
            default:
                throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
        }
    }

    public double generalizedEntropyOneNat(double var1, double var3) {
        double var5 = 0.0D;
        switch(this.type) {
            case 1:
            case 12:
                double[] var7 = this.getArray_as_double();
                var5 = generalizedEntropyOneNat(var7, var1, var3);
                return var5;
            case 14:
                throw new IllegalArgumentException("Complex Generalized Entropy is not meaningful");
            default:
                throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
        }
    }

    public double generalisedEntropyOneNat(double var1, double var3) {
        return this.generalizedEntropyOneNat(var1, var3);
    }

    public ArrayList<Object> upperOutliersAnscombe(double var1) {
        return this.upperOutliersAnscombe_as_double(var1);
    }

    public ArrayList<Object> upperOutliersAnscombe_as_double(double var1) {
        switch(this.type) {
            case 1:
                double[] var3 = this.getArray_as_double();
                this.upperOutlierDetails = upperOutliersAnscombeAsArrayList(var3, var1);
                break;
            case 12:
                BigDecimal[] var4 = this.getArray_as_BigDecimal();
                new ArrayList();
                ArrayList var5 = upperOutliersAnscombeAsArrayList(var4, new BigDecimal(var1));
                this.upperOutlierDetails.add((Integer)var5.get(0));
                BigDecimal[] var6 = (BigDecimal[])((BigDecimal[])var5.get(1));
                ArrayMaths var7 = new ArrayMaths(var6);
                this.upperOutlierDetails.add(var7.getArray_as_Double());
                this.upperOutlierDetails.add((int[])((int[])var5.get(2)));
                BigDecimal[] var8 = (BigDecimal[])((BigDecimal[])var5.get(3));
                ArrayMaths var9 = new ArrayMaths(var8);
                this.upperOutlierDetails.add(var9.getArray_as_Double());
                break;
            case 14:
                throw new IllegalArgumentException("Outlier detection of Complex is not supported");
            default:
                throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
        }

        this.upperDone = true;
        return this.upperOutlierDetails;
    }

    public ArrayList<Object> upperOutliersAnscombe(BigDecimal var1) {
        return this.upperOutliersAnscombe_as_BigDecimal(var1);
    }

    public ArrayList<Object> upperOutliersAnscombe_as_BigDecimal(BigDecimal var1) {
        switch(this.type) {
            case 1:
                double[] var2 = this.getArray_as_double();
                new ArrayList();
                ArrayList var3 = upperOutliersAnscombeAsArrayList(var2, var1.doubleValue());
                this.upperOutlierDetails.add((Integer)var3.get(0));
                Double[] var4 = (Double[])((Double[])var3.get(1));
                ArrayMaths var5 = new ArrayMaths(var4);
                this.upperOutlierDetails.add(var5.getArray_as_BigDecimal());
                this.upperOutlierDetails.add((int[])((int[])var3.get(2)));
                Double[] var6 = (Double[])((Double[])var3.get(3));
                ArrayMaths var7 = new ArrayMaths(var6);
                this.upperOutlierDetails.add(var7.getArray_as_BigDecimal());
                break;
            case 12:
                BigDecimal[] var8 = this.getArray_as_BigDecimal();
                this.upperOutlierDetails = upperOutliersAnscombeAsArrayList(var8, var1);
                break;
            case 14:
                throw new IllegalArgumentException("Outlier detection of Complex is not supported");
            default:
                throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
        }

        this.upperDone = true;
        return this.upperOutlierDetails;
    }

    public ArrayList<Object> upperOutliersAnscombe(BigInteger var1) {
        return this.upperOutliersAnscombe_as_BigDecimal(new BigDecimal(var1));
    }

    public ArrayList<Object> upperOutliersAnscombe_as_BigDecimal(BigInteger var1) {
        return this.upperOutliersAnscombe_as_BigDecimal(new BigDecimal(var1));
    }

    public ArrayList<Object> lowerOutliersAnscombe(double var1) {
        return this.lowerOutliersAnscombe_as_double(var1);
    }

    public ArrayList<Object> lowerOutliersAnscombe_as_double(double var1) {
        switch(this.type) {
            case 1:
                double[] var3 = this.getArray_as_double();
                this.lowerOutlierDetails = lowerOutliersAnscombeAsArrayList(var3, var1);
                break;
            case 12:
                BigDecimal[] var4 = this.getArray_as_BigDecimal();
                new ArrayList();
                ArrayList var5 = lowerOutliersAnscombeAsArrayList(var4, new BigDecimal(var1));
                this.lowerOutlierDetails.add((Integer)var5.get(0));
                BigDecimal[] var6 = (BigDecimal[])((BigDecimal[])var5.get(1));
                ArrayMaths var7 = new ArrayMaths(var6);
                this.lowerOutlierDetails.add(var7.getArray_as_Double());
                this.lowerOutlierDetails.add((int[])((int[])var5.get(2)));
                BigDecimal[] var8 = (BigDecimal[])((BigDecimal[])var5.get(3));
                ArrayMaths var9 = new ArrayMaths(var8);
                this.lowerOutlierDetails.add(var9.getArray_as_Double());
                break;
            case 14:
                throw new IllegalArgumentException("Outlier detection of Complex is not supported");
            default:
                throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
        }

        this.lowerDone = true;
        return this.lowerOutlierDetails;
    }

    public ArrayList<Object> lowerOutliersAnscombe(BigDecimal var1) {
        return this.lowerOutliersAnscombe_as_BigDecimal(var1);
    }

    public ArrayList<Object> lowerOutliersAnscombe_as_BigDecimal(BigDecimal var1) {
        switch(this.type) {
            case 1:
                double[] var2 = this.getArray_as_double();
                new ArrayList();
                ArrayList var3 = lowerOutliersAnscombeAsArrayList(var2, var1.doubleValue());
                this.lowerOutlierDetails.add((Integer)var3.get(0));
                Double[] var4 = (Double[])((Double[])var3.get(1));
                ArrayMaths var5 = new ArrayMaths(var4);
                this.lowerOutlierDetails.add(var5.getArray_as_BigDecimal());
                this.lowerOutlierDetails.add((int[])((int[])var3.get(2)));
                Double[] var6 = (Double[])((Double[])var3.get(3));
                ArrayMaths var7 = new ArrayMaths(var6);
                this.lowerOutlierDetails.add(var7.getArray_as_BigDecimal());
                break;
            case 12:
                BigDecimal[] var8 = this.getArray_as_BigDecimal();
                this.lowerOutlierDetails = lowerOutliersAnscombeAsArrayList(var8, var1);
                break;
            case 14:
                throw new IllegalArgumentException("Outlier detection of Complex is not supported");
            default:
                throw new IllegalArgumentException("This type number, " + this.type + ", should not be possible here!!!!");
        }

        this.lowerDone = true;
        return this.lowerOutlierDetails;
    }

    public ArrayList<Object> lowerOutliersAnscombe(BigInteger var1) {
        return this.lowerOutliersAnscombe_as_BigDecimal(new BigDecimal(var1));
    }

    public ArrayList<Object> lowerOutliersAnscombe_as_BigDecimal(BigInteger var1) {
        return this.lowerOutliersAnscombe_as_BigDecimal(new BigDecimal(var1));
    }

    public static void setStaticWeightsToBigW() {
        weightingOptionS = false;
    }

    public static void setStaticWeightsToLittleW() {
        weightingOptionS = true;
    }

    public static double[] convertBigWtoLittleW(double[] var0) {
        ArrayMaths var1 = new ArrayMaths(var0);
        ArrayMaths var2 = var1.oneOverSqrt();
        return var2.getArray_as_double();
    }

    public static float[] convertBigWtoLittleW(float[] var0) {
        ArrayMaths var1 = new ArrayMaths(var0);
        ArrayMaths var2 = var1.oneOverSqrt();
        return var2.getArray_as_float();
    }

    public static Complex[] convertBigWtoLittleW(Complex[] var0) {
        ArrayMaths var1 = new ArrayMaths(var0);
        ArrayMaths var2 = var1.oneOverSqrt();
        return var2.getArray_as_Complex();
    }

    public static double[] convertBigWtoLittleW(BigDecimal[] var0) {
        ArrayMaths var1 = new ArrayMaths(var0);
        ArrayMaths var2 = var1.oneOverSqrt();
        return var2.getArray_as_double();
    }

    public static double[] convertBigWtoLittleW(BigInteger[] var0) {
        ArrayMaths var1 = new ArrayMaths(var0);
        ArrayMaths var2 = var1.oneOverSqrt();
        return var2.getArray_as_double();
    }

    private static double[] invertAndSquare(double[] var0) {
        double[] var1 = Conv.copy(var0);
        if (weightingOptionS) {
            ArrayMaths var2 = new ArrayMaths(var0);
            var2 = var2.pow(2);
            var2 = var2.invert();
            var1 = var2.array();
        }

        return var1;
    }

    private static float[] invertAndSquare(float[] var0) {
        float[] var1 = Conv.copy(var0);
        if (weightingOptionS) {
            ArrayMaths var2 = new ArrayMaths(var0);
            var2 = var2.pow(2);
            var2 = var2.invert();
            var1 = var2.array_as_float();
        }

        return var1;
    }

    private static Complex[] invertAndSquare(Complex[] var0) {
        Complex[] var1 = Conv.copy(var0);
        if (weightingOptionS) {
            ArrayMaths var2 = new ArrayMaths(var0);
            var2 = var2.pow(2);
            var2 = var2.invert();
            var1 = var2.array_as_Complex();
        }

        return var1;
    }

    private static BigDecimal[] invertAndSquare(BigDecimal[] var0) {
        BigDecimal[] var1 = Conv.copy(var0);
        if (weightingOptionS) {
            ArrayMaths var2 = new ArrayMaths(var0);
            var2 = var2.pow(2);
            var2 = var2.invert();
            var1 = var2.array_as_BigDecimal();
        }

        return var1;
    }

    private static BigDecimal[] invertAndSquare(BigInteger[] var0) {
        ArrayMaths var1 = new ArrayMaths(var0);
        BigDecimal[] var2 = var1.array_as_BigDecimal();
        if (weightingOptionS) {
            var1 = var1.pow(2);
            var1 = var1.invert();
            var2 = var1.array_as_BigDecimal();
        }

        return var2;
    }

    public static void setStaticDenominatorToN() {
        nFactorOptionS = true;
    }

    public static void setStaticDenominatorToNminusOne() {
        nFactorOptionS = false;
    }

    public static boolean getStaticDenominatorOption() {
        return nFactorOptionS;
    }

    public static void denominatorSwap() {
        nFactorOptionSstored = getStaticDenominatorOption();
        if (nFactorOptionSstored) {
            setStaticDenominatorToNminusOne();
        }

    }

    public static void denominatorUnswap() {
        nFactorOptionS = nFactorOptionSstored;
    }

    public static void useStaticEffectiveN() {
        nEffOptionS = true;
    }

    public static void useStaticTrueN() {
        nEffOptionS = false;
    }

    public static double effectiveSampleNumber(double[] var0) {
        double[] var1 = Conv.copy(var0);
        if (weightingOptionS) {
            ArrayMaths var2 = new ArrayMaths(var0);
            var2 = var2.pow(2);
            var2 = var2.invert();
            var1 = var2.array();
        }

        int var10 = var1.length;
        double var3 = (double)var10;
        if (nEffOptionS) {
            double var5 = 0.0D;
            double var7 = 0.0D;

            for(int var9 = 0; var9 < var10; ++var9) {
                var5 += var1[var9];
                var7 += var1[var9] * var1[var9];
            }

            var5 *= var5;
            var3 = var5 / var7;
        }

        return var3;
    }

    public static float effectiveSampleNumber(float[] var0) {
        float[] var1 = Conv.copy(var0);
        if (weightingOptionS) {
            ArrayMaths var2 = new ArrayMaths(var0);
            var2 = var2.pow(2);
            var2 = var2.invert();
            var1 = var2.array_as_float();
        }

        int var7 = var1.length;
        float var3 = (float)var7;
        if (nEffOptionS) {
            float var4 = 0.0F;
            float var5 = 0.0F;

            for(int var6 = 0; var6 < var7; ++var6) {
                var4 += var1[var6];
                var5 += var1[var6] * var1[var6];
            }

            var4 *= var4;
            var3 = var4 / var5;
        }

        return var3;
    }

    public static Complex effectiveSampleNumber(Complex[] var0) {
        Complex[] var1 = Conv.copy(var0);
        if (weightingOptionS) {
            ArrayMaths var2 = new ArrayMaths(var0);
            var2 = var2.pow(2);
            var2 = var2.invert();
            var1 = var2.array_as_Complex();
        }

        int var7 = var1.length;
        Complex var3 = new Complex((double)var7, 0.0D);
        if (nEffOptionS) {
            Complex var4 = Complex.zero();
            Complex var5 = Complex.zero();

            for(int var6 = 0; var6 < var7; ++var6) {
                var5 = var5.plus(var1[var6]);
                var4 = var4.plus(var1[var6].times(var1[var6]));
            }

            var5 = var5.times(var5);
            var3 = var5.over(var4);
        }

        return var3;
    }

    public static double effectiveSampleNumberConjugateCalcn(Complex[] var0) {
        Complex[] var1 = Conv.copy(var0);
        if (weightingOptionS) {
            ArrayMaths var2 = new ArrayMaths(var0);
            var2 = var2.pow(2);
            var2 = var2.invert();
            var1 = var2.array_as_Complex();
        }

        int var8 = var1.length;
        double var3 = 0.0D / 0.0;
        if (nEffOptionS) {
            Complex var5 = Complex.zero();
            Complex var6 = Complex.zero();

            for(int var7 = 0; var7 < var8; ++var7) {
                var6 = var6.plus(var1[var7]);
                var5 = var5.plus(var1[var7].times(var1[var7].conjugate()));
            }

            var6 = var6.times(var6.conjugate());
            var3 = var6.getReal() / var5.getReal();
        }

        return var3;
    }

    public static BigDecimal effectiveSampleNumber(BigDecimal[] var0) {
        BigDecimal[] var1 = Conv.copy(var0);
        if (weightingOptionS) {
            ArrayMaths var2 = new ArrayMaths(var0);
            var2 = var2.pow(2);
            var2 = var2.invert();
            var1 = var2.array_as_BigDecimal();
        }

        int var7 = var1.length;
        BigDecimal var3 = new BigDecimal((new Integer(var7)).toString());
        if (nEffOptionS) {
            BigDecimal var4 = BigDecimal.ZERO;
            BigDecimal var5 = BigDecimal.ZERO;

            for(int var6 = 0; var6 < var7; ++var6) {
                var5 = var5.add(var1[var6]);
                var4 = var4.add(var1[var6].multiply(var1[var6]));
            }

            var5 = var5.multiply(var5);
            var3 = var5.divide(var4, 4);
            var4 = null;
            var5 = null;
            var1 = null;
        }

        return var3;
    }

    public static BigDecimal effectiveSampleNumber(BigInteger[] var0) {
        ArrayMaths var1 = new ArrayMaths(var0);
        BigDecimal[] var2 = var1.array_as_BigDecimal();
        return effectiveSampleNumber(var2);
    }

    public static double mean(double[] var0) {
        int var1 = var0.length;
        double var2 = 0.0D;

        for(int var4 = 0; var4 < var1; ++var4) {
            var2 += var0[var4];
        }

        return var2 / (double)var1;
    }

    public static float mean(float[] var0) {
        int var1 = var0.length;
        float var2 = 0.0F;

        for(int var3 = 0; var3 < var1; ++var3) {
            var2 += var0[var3];
        }

        return var2 / (float)var1;
    }

    public static double mean(long[] var0) {
        int var1 = var0.length;
        double var2 = 0.0D;

        for(int var4 = 0; var4 < var1; ++var4) {
            var2 += (double)var0[var4];
        }

        return var2 / (double)var1;
    }

    public static double mean(int[] var0) {
        int var1 = var0.length;
        double var2 = 0.0D;

        for(int var4 = 0; var4 < var1; ++var4) {
            var2 += (double)var0[var4];
        }

        return var2 / (double)var1;
    }

    public static double mean(short[] var0) {
        int var1 = var0.length;
        double var2 = 0.0D;

        for(int var4 = 0; var4 < var1; ++var4) {
            var2 += (double)var0[var4];
        }

        return var2 / (double)var1;
    }

    public static double mean(byte[] var0) {
        int var1 = var0.length;
        double var2 = 0.0D;

        for(int var4 = 0; var4 < var1; ++var4) {
            var2 += (double)var0[var4];
        }

        return var2 / (double)var1;
    }

    public static Complex mean(Complex[] var0) {
        int var1 = var0.length;
        Complex var2 = new Complex(0.0D, 0.0D);

        for(int var3 = 0; var3 < var1; ++var3) {
            var2 = var2.plus(var0[var3]);
        }

        return var2.over((double)var1);
    }

    public static BigDecimal mean(BigDecimal[] var0) {
        int var1 = var0.length;
        BigDecimal var2 = BigDecimal.ZERO;

        for(int var3 = 0; var3 < var1; ++var3) {
            var2 = var2.add(var0[var3]);
        }

        return var2.divide(new BigDecimal((double)var1), 4);
    }

    public static BigDecimal mean(BigInteger[] var0) {
        int var1 = var0.length;
        BigDecimal var2 = BigDecimal.ZERO;
        BigDecimal var3 = BigDecimal.ZERO;

        for(int var4 = 0; var4 < var1; ++var4) {
            var3 = new BigDecimal(var0[var4]);
            var2 = var2.add(var3);
        }

        var3 = null;
        return var2.divide(new BigDecimal((double)var1), 4);
    }

    public static double mean(double[] var0, double[] var1) {
        int var2 = var0.length;
        if (var2 != var1.length) {
            throw new IllegalArgumentException("length of variable array, " + var2 + " and length of weight array, " + var1.length + " are different");
        } else {
            double[] var3 = Conv.copy(var1);
            if (weightingOptionS) {
                ArrayMaths var4 = new ArrayMaths(var1);
                var4 = var4.pow(2);
                var4 = var4.invert();
                var3 = var4.array();
            }

            double var9 = 0.0D;
            double var6 = 0.0D;

            for(int var8 = 0; var8 < var2; ++var8) {
                var9 += var0[var8] * var3[var8];
                var6 += var3[var8];
            }

            return var9 / var6;
        }
    }

    public static float mean(float[] var0, float[] var1) {
        int var2 = var0.length;
        if (var2 != var1.length) {
            throw new IllegalArgumentException("length of variable array, " + var2 + " and length of weight array, " + var1.length + " are different");
        } else {
            float[] var3 = Conv.copy(var1);
            if (weightingOptionS) {
                ArrayMaths var4 = new ArrayMaths(var1);
                var4 = var4.pow(2);
                var4 = var4.invert();
                var3 = var4.array_as_float();
            }

            float var7 = 0.0F;
            float var5 = 0.0F;

            for(int var6 = 0; var6 < var2; ++var6) {
                var7 += var0[var6] * var3[var6];
                var5 += var3[var6];
            }

            return var7 / var5;
        }
    }

    public static Complex mean(Complex[] var0, Complex[] var1) {
        int var2 = var0.length;
        if (var2 != var1.length) {
            throw new IllegalArgumentException("length of variable array, " + var2 + " and length of weight array, " + var1.length + " are different");
        } else {
            Complex[] var3 = Conv.copy(var1);
            if (weightingOptionS) {
                ArrayMaths var4 = new ArrayMaths(var1);
                var4 = var4.pow(2);
                var4 = var4.invert();
                var3 = var4.array_as_Complex();
            }

            Complex var7 = Complex.zero();
            Complex var5 = Complex.zero();

            for(int var6 = 0; var6 < var2; ++var6) {
                var7 = var7.plus(var0[var6].times(var3[var6]));
                var5 = var5.plus(var3[var6]);
            }

            return var7.over(var5);
        }
    }

    public static BigDecimal mean(BigDecimal[] var0, BigDecimal[] var1) {
        int var2 = var0.length;
        if (var2 != var1.length) {
            throw new IllegalArgumentException("length of variable array, " + var2 + " and length of weight array, " + var1.length + " are different");
        } else {
            BigDecimal[] var3 = Conv.copy(var1);
            if (weightingOptionS) {
                ArrayMaths var4 = new ArrayMaths(var1);
                var4 = var4.pow(2);
                var4 = var4.invert();
                var3 = var4.array_as_BigDecimal();
            }

            BigDecimal var7 = BigDecimal.ZERO;
            BigDecimal var5 = BigDecimal.ZERO;

            for(int var6 = 0; var6 < var2; ++var6) {
                var7 = var7.add(var0[var6].multiply(var3[var6]));
                var5 = var5.add(var3[var6]);
            }

            var7 = var7.divide(var5, 4);
            var5 = null;
            var3 = null;
            return var7;
        }
    }

    public static BigDecimal mean(BigInteger[] var0, BigInteger[] var1) {
        ArrayMaths var2 = new ArrayMaths(var0);
        ArrayMaths var3 = new ArrayMaths(var1);
        return mean(var2.array_as_BigDecimal(), var3.array_as_BigDecimal());
    }

    public static double[] subtractMean(double[] var0) {
        int var1 = var0.length;
        double var2 = mean(var0);
        double[] var4 = new double[var1];

        for(int var5 = 0; var5 < var1; ++var5) {
            var4[var5] = var0[var5] - var2;
        }

        return var4;
    }

    public static float[] subtractMean(float[] var0) {
        int var1 = var0.length;
        float var2 = mean(var0);
        float[] var3 = new float[var1];

        for(int var4 = 0; var4 < var1; ++var4) {
            var3[var4] = var0[var4] - var2;
        }

        return var3;
    }

    public static BigDecimal[] subtractMean(BigDecimal[] var0) {
        int var1 = var0.length;
        BigDecimal var2 = mean(var0);
        BigDecimal[] var3 = new BigDecimal[var1];

        for(int var4 = 0; var4 < var1; ++var4) {
            var3[var4] = var0[var4].subtract(var2);
        }

        var2 = null;
        return var3;
    }

    public static BigDecimal[] subtractMean(BigInteger[] var0) {
        int var1 = var0.length;
        BigDecimal var2 = mean(var0);
        BigDecimal[] var3 = new BigDecimal[var1];

        for(int var4 = 0; var4 < var1; ++var4) {
            var3[var4] = (new BigDecimal(var0[var4])).subtract(var2);
        }

        var2 = null;
        return var3;
    }

    public static Complex[] subtractMean(Complex[] var0) {
        int var1 = var0.length;
        Complex var2 = mean(var0);
        Complex[] var3 = new Complex[var1];

        for(int var4 = 0; var4 < var1; ++var4) {
            var3[var4] = var0[var4].minus(var2);
        }

        return var3;
    }

    public static double[] subtractMean(double[] var0, double[] var1) {
        int var2 = var0.length;
        double var3 = mean(var0, var1);
        double[] var5 = new double[var2];

        for(int var6 = 0; var6 < var2; ++var6) {
            var5[var6] = var0[var6] - var3;
        }

        return var5;
    }

    public static float[] subtractMean(float[] var0, float[] var1) {
        int var2 = var0.length;
        float var3 = mean(var0, var1);
        float[] var4 = new float[var2];

        for(int var5 = 0; var5 < var2; ++var5) {
            var4[var5] = var0[var5] - var3;
        }

        return var4;
    }

    public static BigDecimal[] subtractMean(BigDecimal[] var0, BigDecimal[] var1) {
        int var2 = var0.length;
        BigDecimal var3 = mean(var0, var1);
        BigDecimal[] var4 = new BigDecimal[var2];

        for(int var5 = 0; var5 < var2; ++var5) {
            var4[var5] = var0[var5].subtract(var3);
        }

        var3 = null;
        return var4;
    }

    public static BigDecimal[] subtractMean(BigInteger[] var0, BigInteger[] var1) {
        int var2 = var0.length;
        BigDecimal var3 = mean(var0, var1);
        BigDecimal[] var4 = new BigDecimal[var2];

        for(int var5 = 0; var5 < var2; ++var5) {
            var4[var5] = (new BigDecimal(var0[var5])).subtract(var3);
        }

        var3 = null;
        return var4;
    }

    public static Complex[] subtractMean(Complex[] var0, Complex[] var1) {
        int var2 = var0.length;
        Complex var3 = mean(var0, var1);
        Complex[] var4 = new Complex[var2];

        for(int var5 = 0; var5 < var2; ++var5) {
            var4[var5] = var0[var5].minus(var3);
        }

        return var4;
    }

    public static double geometricMean(BigDecimal[] var0) {
        int var1 = var0.length;
        double var2 = 0.0D;

        for(int var4 = 0; var4 < var1; ++var4) {
            var2 += Math.log(var0[var4].doubleValue());
        }

        return Math.exp(var2 / (double)var1);
    }

    public static double geometricMean(BigInteger[] var0) {
        int var1 = var0.length;
        double var2 = 0.0D;

        for(int var4 = 0; var4 < var1; ++var4) {
            var2 += Math.log(var0[var4].doubleValue());
        }

        return Math.exp(var2 / (double)var1);
    }

    public static Complex geometricMean(Complex[] var0) {
        int var1 = var0.length;
        Complex var2 = Complex.zero();

        for(int var3 = 0; var3 < var1; ++var3) {
            var2 = var2.plus(Complex.log(var0[var3]));
        }

        return Complex.exp(var2.over((double)var1));
    }

    public static double geometricMean(double[] var0) {
        int var1 = var0.length;
        double var2 = 0.0D;

        for(int var4 = 0; var4 < var1; ++var4) {
            var2 += Math.log(var0[var4]);
        }

        return Math.exp(var2 / (double)var1);
    }

    public static float geometricMean(float[] var0) {
        int var1 = var0.length;
        float var2 = 0.0F;

        for(int var3 = 0; var3 < var1; ++var3) {
            var2 += (float)Math.log((double)var0[var3]);
        }

        return (float)Math.exp((double)(var2 / (float)var1));
    }

    public static Complex geometricMean(Complex[] var0, Complex[] var1) {
        int var2 = var0.length;
        if (var2 != var1.length) {
            throw new IllegalArgumentException("length of variable array, " + var2 + " and length of weight array, " + var1.length + " are different");
        } else {
            Complex var3 = Complex.zero();
            Complex[] var4 = invertAndSquare(var1);

            for(int var5 = 0; var5 < var2; ++var5) {
                var3 = var3.plus(var4[var5]);
            }

            Complex var7 = Complex.zero();

            for(int var6 = 0; var6 < var2; ++var6) {
                var7 = var7.plus(Complex.log(var0[var6]).times(var4[var6]));
            }

            return Complex.exp(var7.over(var3));
        }
    }

    public static double geometricMean(BigDecimal[] var0, BigDecimal[] var1) {
        int var2 = var0.length;
        if (var2 != var1.length) {
            throw new IllegalArgumentException("length of variable array, " + var2 + " and length of weight array, " + var1.length + " are different");
        } else {
            ArrayMaths var3 = new ArrayMaths(invertAndSquare(var1));
            double[] var4 = var3.array();
            double var5 = 0.0D;

            for(int var7 = 0; var7 < var2; ++var7) {
                var5 += var4[var7];
            }

            double var10 = 0.0D;

            for(int var9 = 0; var9 < var2; ++var9) {
                var10 += Math.log(var0[var9].doubleValue()) * var4[var9];
            }

            return Math.exp(var10 / var5);
        }
    }

    public static double geometricMean(BigInteger[] var0, BigInteger[] var1) {
        ArrayMaths var2 = new ArrayMaths(var0);
        ArrayMaths var3 = new ArrayMaths(var1);
        return geometricMean(var2.array_as_BigDecimal(), var3.array_as_BigDecimal());
    }

    public static double geometricMean(double[] var0, double[] var1) {
        int var2 = var0.length;
        if (var2 != var1.length) {
            throw new IllegalArgumentException("length of variable array, " + var2 + " and length of weight array, " + var1.length + " are different");
        } else {
            double var3 = 0.0D;
            double[] var5 = invertAndSquare(var1);

            for(int var6 = 0; var6 < var2; ++var6) {
                var3 += var5[var6];
            }

            double var9 = 0.0D;

            for(int var8 = 0; var8 < var2; ++var8) {
                var9 += Math.log(var0[var8]) * var5[var8];
            }

            return Math.exp(var9 / var3);
        }
    }

    public static float geometricMean(float[] var0, float[] var1) {
        int var2 = var0.length;
        if (var2 != var1.length) {
            throw new IllegalArgumentException("length of variable array, " + var2 + " and length of weight array, " + var1.length + " are different");
        } else {
            float var3 = 0.0F;
            float[] var4 = invertAndSquare(var1);

            for(int var5 = 0; var5 < var2; ++var5) {
                var3 += var4[var5];
            }

            float var7 = 0.0F;

            for(int var6 = 0; var6 < var2; ++var6) {
                var7 += (float)Math.log((double)var0[var6]) * var4[var6];
            }

            return (float)Math.exp((double)(var7 / var3));
        }
    }

    public static BigDecimal harmonicMean(BigDecimal[] var0) {
        int var1 = var0.length;
        BigDecimal var2 = BigDecimal.ZERO;

        for(int var3 = 0; var3 < var1; ++var3) {
            var2 = var2.add(BigDecimal.ONE.divide(var0[var3], 4));
        }

        var2 = (new BigDecimal((double)var1)).divide(var2, 4);
        return var2;
    }

    public static BigDecimal harmonicMean(BigInteger[] var0) {
        int var1 = var0.length;
        ArrayMaths var2 = new ArrayMaths(var0);
        BigDecimal[] var3 = var2.getArray_as_BigDecimal();
        BigDecimal var4 = BigDecimal.ZERO;

        for(int var5 = 0; var5 < var1; ++var5) {
            var4 = var4.add(BigDecimal.ONE.divide(var3[var5], 4));
        }

        var4 = (new BigDecimal((double)var1)).divide(var4, 4);
        var3 = null;
        return var4;
    }

    public static Complex harmonicMean(Complex[] var0) {
        int var1 = var0.length;
        Complex var2 = Complex.zero();

        for(int var3 = 0; var3 < var1; ++var3) {
            var2 = var2.plus(Complex.plusOne().over(var0[var3]));
        }

        var2 = (new Complex((double)var1)).over(var2);
        return var2;
    }

    public static double harmonicMean(double[] var0) {
        int var1 = var0.length;
        double var2 = 0.0D;

        for(int var4 = 0; var4 < var1; ++var4) {
            var2 += 1.0D / var0[var4];
        }

        return (double)var1 / var2;
    }

    public static float harmonicMean(float[] var0) {
        int var1 = var0.length;
        float var2 = 0.0F;

        for(int var3 = 0; var3 < var1; ++var3) {
            var2 += 1.0F / var0[var3];
        }

        return (float)var1 / var2;
    }

    public static BigDecimal harmonicMean(BigDecimal[] var0, BigDecimal[] var1) {
        int var2 = var0.length;
        if (var2 != var1.length) {
            throw new IllegalArgumentException("length of variable array, " + var2 + " and length of weight array, " + var1.length + " are different");
        } else {
            BigDecimal var3 = BigDecimal.ZERO;
            BigDecimal var4 = BigDecimal.ZERO;
            BigDecimal[] var5 = invertAndSquare(var1);

            int var6;
            for(var6 = 0; var6 < var2; ++var6) {
                var4 = var4.add(var5[var6]);
            }

            for(var6 = 0; var6 < var2; ++var6) {
                var3 = var3.add(var5[var6].divide(var0[var6], 4));
            }

            var3 = var4.divide(var3, 4);
            var4 = null;
            var5 = null;
            return var3;
        }
    }

    public static BigDecimal harmonicMean(BigInteger[] var0, BigInteger[] var1) {
        int var2 = var0.length;
        if (var2 != var1.length) {
            throw new IllegalArgumentException("length of variable array, " + var2 + " and length of weight array, " + var1.length + " are different");
        } else {
            ArrayMaths var3 = new ArrayMaths(var0);
            ArrayMaths var4 = new ArrayMaths(var1);
            return harmonicMean(var3.getArray_as_BigDecimal(), var4.getArray_as_BigDecimal());
        }
    }

    public static Complex harmonicMean(Complex[] var0, Complex[] var1) {
        int var2 = var0.length;
        if (var2 != var1.length) {
            throw new IllegalArgumentException("length of variable array, " + var2 + " and length of weight array, " + var1.length + " are different");
        } else {
            Complex var3 = Complex.zero();
            Complex var4 = Complex.zero();
            Complex[] var5 = invertAndSquare(var1);

            int var6;
            for(var6 = 0; var6 < var2; ++var6) {
                var4 = var4.plus(var5[var6]);
            }

            for(var6 = 0; var6 < var2; ++var6) {
                var3 = var3.plus(var5[var6].over(var0[var6]));
            }

            return var4.over(var3);
        }
    }

    public static double harmonicMean(double[] var0, double[] var1) {
        int var2 = var0.length;
        if (var2 != var1.length) {
            throw new IllegalArgumentException("length of variable array, " + var2 + " and length of weight array, " + var1.length + " are different");
        } else {
            double var3 = 0.0D;
            double var5 = 0.0D;
            double[] var7 = invertAndSquare(var1);

            int var8;
            for(var8 = 0; var8 < var2; ++var8) {
                var5 += var7[var8];
            }

            for(var8 = 0; var8 < var2; ++var8) {
                var3 += var7[var8] / var0[var8];
            }

            return var5 / var3;
        }
    }

    public static float harmonicMean(float[] var0, float[] var1) {
        int var2 = var0.length;
        if (var2 != var1.length) {
            throw new IllegalArgumentException("length of variable array, " + var2 + " and length of weight array, " + var1.length + " are different");
        } else {
            float var3 = 0.0F;
            float var4 = 0.0F;
            float[] var5 = invertAndSquare(var1);

            int var6;
            for(var6 = 0; var6 < var2; ++var6) {
                var4 += var5[var6];
            }

            for(var6 = 0; var6 < var2; ++var6) {
                var3 += var5[var6] / var0[var6];
            }

            return var4 / var3;
        }
    }

    public static Complex generalizedMean(Complex[] var0, double var1) {
        int var3 = var0.length;
        Complex var4 = Complex.zero();
        int var5;
        if (var1 == 0.0D) {
            for(var5 = 0; var5 < var3; ++var5) {
                var4 = var4.plus(Complex.log(var0[var5]));
            }

            return Complex.exp(var4);
        } else {
            for(var5 = 0; var5 < var3; ++var5) {
                var4 = var4.plus(Complex.pow(var0[var5], var1));
            }

            return Complex.pow(var4.over((double)var3), 1.0D / var1);
        }
    }

    public static Complex generalizedMean(Complex[] var0, Complex var1) {
        int var2 = var0.length;
        Complex var3 = Complex.zero();
        int var4;
        if (var1.equals(Complex.zero())) {
            for(var4 = 0; var4 < var2; ++var4) {
                var3 = var3.plus(Complex.log(var0[var4]));
            }

            return Complex.exp(var3);
        } else {
            for(var4 = 0; var4 < var2; ++var4) {
                var3 = var3.plus(Complex.pow(var0[var4], var1));
            }

            return Complex.pow(var3.over((double)var2), Complex.plusOne().over(var1));
        }
    }

    public static double generalizedMean(BigDecimal[] var0, double var1) {
        ArrayMaths var3 = new ArrayMaths(var0);
        double[] var4 = var3.getArray_as_double();
        return generalizedMean(var4, var1);
    }

    public static double generalizedMean(BigDecimal[] var0, BigDecimal var1) {
        ArrayMaths var2 = new ArrayMaths(var0);
        double[] var3 = var2.getArray_as_double();
        return generalizedMean(var3, var1.doubleValue());
    }

    public static double generalizedMean(BigInteger[] var0, double var1) {
        ArrayMaths var3 = new ArrayMaths(var0);
        double[] var4 = var3.getArray_as_double();
        return generalizedMean(var4, var1);
    }

    public static double generalizedMean(BigInteger[] var0, BigInteger var1) {
        ArrayMaths var2 = new ArrayMaths(var0);
        double[] var3 = var2.getArray_as_double();
        return generalizedMean(var3, var1.doubleValue());
    }

    public static double generalizedMean(double[] var0, double var1) {
        int var3 = var0.length;
        double var4 = 0.0D;
        int var6;
        if (var1 == 0.0D) {
            for(var6 = 0; var6 < var3; ++var6) {
                var4 += Math.log(var0[var6]);
            }

            return Math.exp(var4);
        } else {
            for(var6 = 0; var6 < var3; ++var6) {
                var4 += Math.pow(var0[var6], var1);
            }

            return Math.pow(var4 / (double)var3, 1.0D / var1);
        }
    }

    public static float generalizedMean(float[] var0, float var1) {
        int var2 = var0.length;
        float var3 = 0.0F;
        int var4;
        if (var1 == 0.0F) {
            for(var4 = 0; var4 < var2; ++var4) {
                var3 += (float)Math.log((double)var0[var4]);
            }

            return (float)Math.exp((double)var3);
        } else {
            for(var4 = 0; var4 < var2; ++var4) {
                var3 = (float)((double)var3 + Math.pow((double)var0[var4], (double)var1));
            }

            return (float)Math.pow((double)(var3 / (float)var2), (double)(1.0F / var1));
        }
    }

    public static Complex generalisedMean(Complex[] var0, double var1) {
        int var3 = var0.length;
        Complex var4 = Complex.zero();
        int var5;
        if (var1 == 0.0D) {
            for(var5 = 0; var5 < var3; ++var5) {
                var4 = var4.plus(Complex.log(var0[var5]));
            }

            return Complex.exp(var4);
        } else {
            for(var5 = 0; var5 < var3; ++var5) {
                var4 = var4.plus(Complex.pow(var0[var5], var1));
            }

            return Complex.pow(var4.over((double)var3), 1.0D / var1);
        }
    }

    public static Complex generalisedMean(Complex[] var0, Complex var1) {
        int var2 = var0.length;
        Complex var3 = Complex.zero();
        int var4;
        if (var1.equals(Complex.zero())) {
            for(var4 = 0; var4 < var2; ++var4) {
                var3 = var3.plus(Complex.log(var0[var4]));
            }

            return Complex.exp(var3);
        } else {
            for(var4 = 0; var4 < var2; ++var4) {
                var3 = var3.plus(Complex.pow(var0[var4], var1));
            }

            return Complex.pow(var3.over((double)var2), Complex.plusOne().over(var1));
        }
    }

    public static double generalisedMean(BigDecimal[] var0, double var1) {
        ArrayMaths var3 = new ArrayMaths(var0);
        double[] var4 = var3.getArray_as_double();
        return generalisedMean(var4, var1);
    }

    public static double generalisedMean(BigDecimal[] var0, BigDecimal var1) {
        ArrayMaths var2 = new ArrayMaths(var0);
        double[] var3 = var2.getArray_as_double();
        return generalisedMean(var3, var1.doubleValue());
    }

    public static double generalisedMean(BigInteger[] var0, double var1) {
        ArrayMaths var3 = new ArrayMaths(var0);
        double[] var4 = var3.getArray_as_double();
        return generalisedMean(var4, var1);
    }

    public static double generalisedMean(BigInteger[] var0, BigInteger var1) {
        ArrayMaths var2 = new ArrayMaths(var0);
        double[] var3 = var2.getArray_as_double();
        return generalisedMean(var3, var1.doubleValue());
    }

    public static double generalisedMean(double[] var0, double var1) {
        int var3 = var0.length;
        double var4 = 0.0D;
        int var6;
        if (var1 == 0.0D) {
            for(var6 = 0; var6 < var3; ++var6) {
                var4 += Math.log(var0[var6]);
            }

            return Math.exp(var4);
        } else {
            for(var6 = 0; var6 < var3; ++var6) {
                var4 += Math.pow(var0[var6], var1);
            }

            return Math.pow(var4 / (double)var3, 1.0D / var1);
        }
    }

    public static float generalisedMean(float[] var0, float var1) {
        int var2 = var0.length;
        float var3 = 0.0F;
        int var4;
        if (var1 == 0.0F) {
            for(var4 = 0; var4 < var2; ++var4) {
                var3 += (float)Math.log((double)var0[var4]);
            }

            return (float)Math.exp((double)var3);
        } else {
            for(var4 = 0; var4 < var2; ++var4) {
                var3 = (float)((double)var3 + Math.pow((double)var0[var4], (double)var1));
            }

            return (float)Math.pow((double)(var3 / (float)var2), (double)(1.0F / var1));
        }
    }

    public static Complex generalisedMean(Complex[] var0, Complex[] var1, double var2) {
        int var4 = var0.length;
        if (var4 != var1.length) {
            throw new IllegalArgumentException("length of variable array, " + var4 + " and length of weight array, " + var1.length + " are different");
        } else {
            Complex var5 = Complex.zero();
            Complex var6 = Complex.zero();
            Complex[] var7 = invertAndSquare(var1);

            int var8;
            for(var8 = 0; var8 < var4; ++var8) {
                var6 = var6.plus(var7[var8]);
            }

            if (var2 == 0.0D) {
                for(var8 = 0; var8 < var4; ++var8) {
                    var5 = var5.plus(Complex.log(var7[var8].times(var0[var8])).over(var6));
                }

                return Complex.exp(var5);
            } else {
                for(var8 = 0; var8 < var4; ++var8) {
                    var5 = var5.plus(var7[var8].times(Complex.pow(var0[var8], var2)));
                }

                return Complex.pow(var5.over(var6), 1.0D / var2);
            }
        }
    }

    public static Complex generalisedMean(Complex[] var0, Complex[] var1, Complex var2) {
        int var3 = var0.length;
        if (var3 != var1.length) {
            throw new IllegalArgumentException("length of variable array, " + var3 + " and length of weight array, " + var1.length + " are different");
        } else {
            Complex var4 = Complex.zero();
            Complex var5 = Complex.zero();
            Complex[] var6 = invertAndSquare(var1);

            int var7;
            for(var7 = 0; var7 < var3; ++var7) {
                var5 = var5.plus(var6[var7]);
            }

            if (var2.equals(Complex.zero())) {
                for(var7 = 0; var7 < var3; ++var7) {
                    var4 = var4.plus(Complex.log(var6[var7].times(var0[var7])).over(var5));
                }

                return Complex.exp(var4);
            } else {
                for(var7 = 0; var7 < var3; ++var7) {
                    var4 = var4.plus(var6[var7].times(Complex.pow(var0[var7], var2)));
                }

                return Complex.pow(var4.over(var5), Complex.plusOne().over(var2));
            }
        }
    }

    public static double generalisedMean(BigDecimal[] var0, BigDecimal[] var1, double var2) {
        int var4 = var0.length;
        if (var4 != var1.length) {
            throw new IllegalArgumentException("length of variable array, " + var4 + " and length of weight array, " + var1.length + " are different");
        } else {
            ArrayMaths var5 = new ArrayMaths(var0);
            double[] var6 = var5.getArray_as_double();
            ArrayMaths var7 = new ArrayMaths(var1);
            double[] var8 = var7.getArray_as_double();
            return generalisedMean(var6, var8, var2);
        }
    }

    public static double generalisedMean(BigDecimal[] var0, BigDecimal[] var1, BigDecimal var2) {
        int var3 = var0.length;
        if (var3 != var1.length) {
            throw new IllegalArgumentException("length of variable array, " + var3 + " and length of weight array, " + var1.length + " are different");
        } else {
            ArrayMaths var4 = new ArrayMaths(var0);
            double[] var5 = var4.getArray_as_double();
            ArrayMaths var6 = new ArrayMaths(var1);
            double[] var7 = var6.getArray_as_double();
            return generalisedMean(var5, var7, var2.doubleValue());
        }
    }

    public static double generalisedMean(BigInteger[] var0, BigInteger[] var1, double var2) {
        int var4 = var0.length;
        if (var4 != var1.length) {
            throw new IllegalArgumentException("length of variable array, " + var4 + " and length of weight array, " + var1.length + " are different");
        } else {
            ArrayMaths var5 = new ArrayMaths(var0);
            double[] var6 = var5.getArray_as_double();
            ArrayMaths var7 = new ArrayMaths(var1);
            double[] var8 = var7.getArray_as_double();
            return generalisedMean(var6, var8, var2);
        }
    }

    public static double generalisedMean(BigInteger[] var0, BigInteger[] var1, BigInteger var2) {
        int var3 = var0.length;
        if (var3 != var1.length) {
            throw new IllegalArgumentException("length of variable array, " + var3 + " and length of weight array, " + var1.length + " are different");
        } else {
            ArrayMaths var4 = new ArrayMaths(var0);
            double[] var5 = var4.getArray_as_double();
            ArrayMaths var6 = new ArrayMaths(var1);
            double[] var7 = var6.getArray_as_double();
            return generalisedMean(var5, var7, var2.doubleValue());
        }
    }

    public static double generalisedMean(double[] var0, double[] var1, double var2) {
        int var4 = var0.length;
        if (var4 != var1.length) {
            throw new IllegalArgumentException("length of variable array, " + var4 + " and length of weight array, " + var1.length + " are different");
        } else {
            double var5 = 0.0D;
            double var7 = 0.0D;
            double[] var9 = invertAndSquare(var1);

            int var10;
            for(var10 = 0; var10 < var4; ++var10) {
                var7 += var9[var10];
            }

            if (var2 == 0.0D) {
                for(var10 = 0; var10 < var4; ++var10) {
                    var5 += Math.log(var0[var10] * var9[var10] / var7);
                }

                return Math.exp(var5);
            } else {
                for(var10 = 0; var10 < var4; ++var10) {
                    var5 += var9[var10] * Math.pow(var0[var10], var2);
                }

                return Math.pow(var5 / var7, 1.0D / var2);
            }
        }
    }

    public static float generalisedMean(float[] var0, float[] var1, float var2) {
        int var3 = var0.length;
        if (var3 != var1.length) {
            throw new IllegalArgumentException("length of variable array, " + var3 + " and length of weight array, " + var1.length + " are different");
        } else {
            float var4 = 0.0F;
            float var5 = 0.0F;
            float[] var6 = invertAndSquare(var1);

            int var7;
            for(var7 = 0; var7 < var3; ++var7) {
                var5 += var6[var7];
            }

            if (var2 == 0.0F) {
                for(var7 = 0; var7 < var3; ++var7) {
                    var4 += (float)Math.log((double)var0[var7]);
                }

                return (float)Math.exp((double)var4);
            } else {
                for(var7 = 0; var7 < var3; ++var7) {
                    var4 = (float)((double)var4 + Math.pow((double)var0[var7], (double)var2));
                }

                return (float)Math.pow((double)(var4 / var5), (double)(1.0F / var2));
            }
        }
    }

    public static Complex weightedGeneralisedMean(Complex[] var0, Complex[] var1, double var2) {
        int var4 = var0.length;
        if (var4 != var1.length) {
            throw new IllegalArgumentException("length of variable array, " + var4 + " and length of weight array, " + var1.length + " are different");
        } else {
            return generalisedMean(var0, var1, var2);
        }
    }

    public static Complex weightedGeneralisedMean(Complex[] var0, Complex[] var1, Complex var2) {
        int var3 = var0.length;
        if (var3 != var1.length) {
            throw new IllegalArgumentException("length of variable array, " + var3 + " and length of weight array, " + var1.length + " are different");
        } else {
            return generalisedMean(var0, var1, var2);
        }
    }

    public static double weightedGeneralisedMean(BigDecimal[] var0, BigDecimal[] var1, double var2) {
        int var4 = var0.length;
        if (var4 != var1.length) {
            throw new IllegalArgumentException("length of variable array, " + var4 + " and length of weight array, " + var1.length + " are different");
        } else {
            return generalisedMean(var0, var1, var2);
        }
    }

    public static double weightedGeneralisedMean(BigDecimal[] var0, BigDecimal[] var1, BigDecimal var2) {
        int var3 = var0.length;
        if (var3 != var1.length) {
            throw new IllegalArgumentException("length of variable array, " + var3 + " and length of weight array, " + var1.length + " are different");
        } else {
            return generalisedMean(var0, var1, var2);
        }
    }

    public static double weightedGeneralisedMean(BigInteger[] var0, BigInteger[] var1, double var2) {
        int var4 = var0.length;
        if (var4 != var1.length) {
            throw new IllegalArgumentException("length of variable array, " + var4 + " and length of weight array, " + var1.length + " are different");
        } else {
            return generalisedMean(var0, var1, var2);
        }
    }

    public static double weightedGeneralisedMean(BigInteger[] var0, BigInteger[] var1, BigInteger var2) {
        int var3 = var0.length;
        if (var3 != var1.length) {
            throw new IllegalArgumentException("length of variable array, " + var3 + " and length of weight array, " + var1.length + " are different");
        } else {
            return generalisedMean(var0, var1, var2);
        }
    }

    public static double weightedGeneralisedMean(double[] var0, double[] var1, double var2) {
        int var4 = var0.length;
        if (var4 != var1.length) {
            throw new IllegalArgumentException("length of variable array, " + var4 + " and length of weight array, " + var1.length + " are different");
        } else {
            return generalisedMean(var0, var1, var2);
        }
    }

    public static float weightedGeneralisedMean(float[] var0, float[] var1, float var2) {
        int var3 = var0.length;
        if (var3 != var1.length) {
            throw new IllegalArgumentException("length of variable array, " + var3 + " and length of weight array, " + var1.length + " are different");
        } else {
            return generalisedMean(var0, var1, var2);
        }
    }

    public static BigDecimal interQuartileMean(BigDecimal[] var0) {
        int var1 = var0.length;
        if (var1 < 4) {
            throw new IllegalArgumentException("At least 4 array elements needed");
        } else {
            ArrayMaths var2 = new ArrayMaths(var0);
            ArrayMaths var3 = var2.sort();
            BigDecimal[] var4 = var3.getArray_as_BigDecimal();
            BigDecimal var5 = BigDecimal.ZERO;

            for(int var6 = var1 / 4; var6 < 3 * var1 / 4; ++var6) {
                var5 = var5.add(var4[var6]);
            }

            var5 = var5.multiply(new BigDecimal(2.0D / (double)var1));
            var4 = null;
            return var5;
        }
    }

    public static BigDecimal interQuartileMean(BigInteger[] var0) {
        int var1 = var0.length;
        if (var1 < 4) {
            throw new IllegalArgumentException("At least 4 array elements needed");
        } else {
            ArrayMaths var2 = new ArrayMaths(var0);
            ArrayMaths var3 = var2.sort();
            BigDecimal[] var4 = var3.getArray_as_BigDecimal();
            BigDecimal var5 = BigDecimal.ZERO;

            for(int var6 = var1 / 4; var6 < 3 * var1 / 4; ++var6) {
                var5 = var5.add(var4[var6]);
            }

            var5 = var5.multiply(new BigDecimal(2.0D / (double)var1));
            var4 = null;
            return var5;
        }
    }

    public static double interQuartileMean(double[] var0) {
        int var1 = var0.length;
        if (var1 < 4) {
            throw new IllegalArgumentException("At least 4 array elements needed");
        } else {
            double[] var2 = Fmath.selectionSort(var0);
            double var3 = 0.0D;

            for(int var5 = var1 / 4; var5 < 3 * var1 / 4; ++var5) {
                var3 += var2[var5];
            }

            return 2.0D * var3 / (double)var1;
        }
    }

    public static float interQuartileMean(float[] var0) {
        int var1 = var0.length;
        if (var1 < 4) {
            throw new IllegalArgumentException("At least 4 array elements needed");
        } else {
            float[] var2 = Fmath.selectionSort(var0);
            float var3 = 0.0F;

            for(int var4 = var1 / 4; var4 < 3 * var1 / 4; ++var4) {
                var3 += var2[var4];
            }

            return 2.0F * var3 / (float)var1;
        }
    }

    public static double rms(double[] var0) {
        int var1 = var0.length;
        double var2 = 0.0D;

        for(int var4 = 0; var4 < var1; ++var4) {
            var2 += var0[var4] * var0[var4];
        }

        return Math.sqrt(var2 / (double)var1);
    }

    public static float rms(float[] var0) {
        int var1 = var0.length;
        float var2 = 0.0F;

        for(int var3 = 0; var3 < var1; ++var3) {
            var2 += var0[var3] * var0[var3];
        }

        var2 /= (float)var1;
        return (float)Math.sqrt((double)var2);
    }

    public static double rms(BigDecimal[] var0) {
        int var1 = var0.length;
        BigDecimal var2 = BigDecimal.ZERO;

        for(int var3 = 0; var3 < var1; ++var3) {
            var2 = var2.add(var0[var3].multiply(var0[var3]));
        }

        var2 = var2.divide(new BigDecimal(var1), 4);
        double var5 = Math.sqrt(var2.doubleValue());
        var2 = null;
        return var5;
    }

    public static double rms(BigInteger[] var0) {
        int var1 = var0.length;
        BigDecimal var2 = BigDecimal.ZERO;
        BigDecimal var3 = BigDecimal.ZERO;

        for(int var4 = 0; var4 < var1; ++var4) {
            var3 = new BigDecimal(var0[var4]);
            var2 = var2.add(var3.multiply(var3));
        }

        var2 = var2.divide(new BigDecimal(var1), 4);
        double var6 = Math.sqrt(var2.doubleValue());
        var3 = null;
        var2 = null;
        return var6;
    }

    public static double rms(double[] var0, double[] var1) {
        int var2 = var0.length;
        if (var2 != var1.length) {
            throw new IllegalArgumentException("length of variable array, " + var2 + " and length of weight array, " + var1.length + " are different");
        } else {
            double var3 = 0.0D;
            double[] var5 = invertAndSquare(var1);

            for(int var6 = 0; var6 < var2; ++var6) {
                var3 += var5[var6];
            }

            double var9 = 0.0D;

            for(int var8 = 0; var8 < var2; ++var8) {
                var9 += var5[var8] * var0[var8] * var0[var8];
            }

            return Math.sqrt(var9 / var3);
        }
    }

    public static float rms(float[] var0, float[] var1) {
        int var2 = var0.length;
        if (var2 != var1.length) {
            throw new IllegalArgumentException("length of variable array, " + var2 + " and length of weight array, " + var1.length + " are different");
        } else {
            double var3 = 0.0D;
            float[] var5 = invertAndSquare(var1);

            for(int var6 = 0; var6 < var2; ++var6) {
                var3 += (double)var5[var6];
            }

            float var8 = 0.0F;

            for(int var7 = 0; var7 < var2; ++var7) {
                var8 += var5[var7] * var0[var7] * var0[var7];
            }

            return (float)Math.sqrt((double)var8 / var3);
        }
    }

    public static double rms(BigDecimal[] var0, BigDecimal[] var1) {
        int var2 = var0.length;
        if (var2 != var1.length) {
            throw new IllegalArgumentException("length of variable array, " + var2 + " and length of weight array, " + var1.length + " are different");
        } else {
            BigDecimal var3 = BigDecimal.ZERO;
            BigDecimal[] var4 = invertAndSquare(var1);

            for(int var5 = 0; var5 < var2; ++var5) {
                var3 = var3.add(var4[var5]);
            }

            BigDecimal var8 = BigDecimal.ZERO;

            for(int var6 = 0; var6 < var2; ++var6) {
                var8 = var8.add(var0[var6].multiply(var0[var6]).multiply(var4[var6]));
            }

            var8 = var8.divide(var3, 4);
            double var9 = Math.sqrt(var8.doubleValue());
            var8 = null;
            var4 = null;
            return var9;
        }
    }

    public static double rms(BigInteger[] var0, BigInteger[] var1) {
        int var2 = var0.length;
        if (var2 != var1.length) {
            throw new IllegalArgumentException("length of variable array, " + var2 + " and length of weight array, " + var1.length + " are different");
        } else {
            ArrayMaths var3 = new ArrayMaths(var0);
            ArrayMaths var4 = new ArrayMaths(var1);
            return rms(var3.array_as_BigDecimal(), var4.array_as_BigDecimal());
        }
    }

    public static BigDecimal median(BigDecimal[] var0) {
        int var1 = var0.length;
        int var2 = var1 / 2;
        BigDecimal var3 = BigDecimal.ZERO;
        ArrayMaths var4 = new ArrayMaths(var0);
        ArrayMaths var5 = var4.sort();
        BigDecimal[] var6 = var4.getArray_as_BigDecimal();
        if (Fmath.isOdd(var1)) {
            var3 = var6[var2];
        } else {
            var3 = var6[var2 - 1].add(var6[var2]).divide(new BigDecimal(2.0D), 4);
        }

        var6 = null;
        return var3;
    }

    public static BigDecimal secondQuartile(BigDecimal[] var0) {
        return median(var0);
    }

    public static BigInteger median(BigInteger[] var0) {
        int var1 = var0.length;
        int var2 = var1 / 2;
        BigInteger var3 = BigInteger.ZERO;
        ArrayMaths var4 = new ArrayMaths(var0);
        ArrayMaths var5 = var4.sort();
        BigInteger[] var6 = var4.getArray_as_BigInteger();
        if (Fmath.isOdd(var1)) {
            var3 = var6[var2];
        } else {
            var3 = var6[var2 - 1].add(var6[var2]).divide(new BigInteger("2"));
        }

        var6 = null;
        return var3;
    }

    public static BigInteger secondQuartile(BigInteger[] var0) {
        return median(var0);
    }

    public static double median(double[] var0) {
        int var1 = var0.length;
        int var2 = var1 / 2;
        double var3 = 0.0D;
        double[] var5 = Fmath.selectionSort(var0);
        if (Fmath.isOdd(var1)) {
            var3 = var5[var2];
        } else {
            var3 = (var5[var2 - 1] + var5[var2]) / 2.0D;
        }

        return var3;
    }

    public static double secondQuartile(double[] var0) {
        return median(var0);
    }

    public static float median(float[] var0) {
        int var1 = var0.length;
        int var2 = var1 / 2;
        float var3 = 0.0F;
        float[] var4 = Fmath.selectionSort(var0);
        if (Fmath.isOdd(var1)) {
            var3 = var4[var2];
        } else {
            var3 = (var4[var2 - 1] + var4[var2]) / 2.0F;
        }

        return var3;
    }

    public static float secondQuartile(float[] var0) {
        return median(var0);
    }

    public static double median(int[] var0) {
        int var1 = var0.length;
        int var2 = var1 / 2;
        double var3 = 0.0D;
        int[] var5 = Fmath.selectionSort(var0);
        if (Fmath.isOdd(var1)) {
            var3 = (double)var5[var2];
        } else {
            var3 = (double)(var5[var2 - 1] + var5[var2]) / 2.0D;
        }

        return var3;
    }

    public static double secondQuartile(int[] var0) {
        return median(var0);
    }

    public static double median(long[] var0) {
        int var1 = var0.length;
        int var2 = var1 / 2;
        double var3 = 0.0D;
        long[] var5 = Fmath.selectionSort(var0);
        if (Fmath.isOdd(var1)) {
            var3 = (double)var5[var2];
        } else {
            var3 = (double)(var5[var2 - 1] + var5[var2]) / 2.0D;
        }

        return var3;
    }

    public static double secondQuartile(long[] var0) {
        return median(var0);
    }

    public static double standardDeviation(BigDecimal[] var0) {
        return Math.sqrt(variance(var0).doubleValue());
    }

    public static double standardDeviation(BigInteger[] var0) {
        return Math.sqrt(variance(var0).doubleValue());
    }

    public static Complex standardDeviation(Complex[] var0) {
        return Complex.sqrt(variance(var0));
    }

    public static double standardDeviationConjugateCalcn(Complex[] var0) {
        return Math.sqrt(varianceConjugateCalcn(var0));
    }

    public static double standardDeviationModuli(Complex[] var0) {
        ArrayMaths var1 = new ArrayMaths(var0);
        double[] var2 = var1.array_as_modulus_of_Complex();
        double var3 = standardDeviation(var2);
        return var3;
    }

    public static double standardDeviationRealParts(Complex[] var0) {
        ArrayMaths var1 = new ArrayMaths(var0);
        double[] var2 = var1.array_as_real_part_of_Complex();
        double var3 = standardDeviation(var2);
        return var3;
    }

    public static double standardDeviationImaginaryParts(Complex[] var0) {
        ArrayMaths var1 = new ArrayMaths(var0);
        double[] var2 = var1.array_as_imaginary_part_of_Complex();
        double var3 = standardDeviation(var2);
        return var3;
    }

    public static double standardDeviation(double[] var0) {
        return Math.sqrt(variance(var0));
    }

    public static float standardDeviation(float[] var0) {
        return (float)Math.sqrt((double)variance(var0));
    }

    public static double standardDeviation(int[] var0) {
        return Math.sqrt(variance(var0));
    }

    public static double standardDeviation(long[] var0) {
        return Math.sqrt(variance(var0));
    }

    public static Complex standardDeviation(Complex[] var0, Complex[] var1) {
        if (var0.length != var1.length) {
            throw new IllegalArgumentException("length of variable array, " + var0.length + " and length of weight array, " + var1.length + " are different");
        } else {
            return Complex.sqrt(variance(var0, var1));
        }
    }

    public static double standardDeviationConjugateCalcn(Complex[] var0, Complex[] var1) {
        if (var0.length != var1.length) {
            throw new IllegalArgumentException("length of variable array, " + var0.length + " and length of weight array, " + var1.length + " are different");
        } else {
            return Math.sqrt(varianceConjugateCalcn(var0, var1));
        }
    }

    public static double standardDeviationModuli(Complex[] var0, Complex[] var1) {
        int var2 = var0.length;
        if (var2 != var1.length) {
            throw new IllegalArgumentException("length of variable array, " + var2 + " and length of weight array, " + var1.length + " are different");
        } else {
            ArrayMaths var3 = new ArrayMaths(var0);
            double[] var4 = var3.array_as_modulus_of_Complex();
            ArrayMaths var5 = new ArrayMaths(var1);
            double[] var6 = var5.array_as_modulus_of_Complex();
            double var7 = standardDeviation(var4, var6);
            return var7;
        }
    }

    public static double standardDeviationRealParts(Complex[] var0, Complex[] var1) {
        int var2 = var0.length;
        if (var2 != var1.length) {
            throw new IllegalArgumentException("length of variable array, " + var2 + " and length of weight array, " + var1.length + " are different");
        } else {
            ArrayMaths var3 = new ArrayMaths(var0);
            double[] var4 = var3.array_as_real_part_of_Complex();
            ArrayMaths var5 = new ArrayMaths(var1);
            double[] var6 = var5.array_as_real_part_of_Complex();
            double var7 = standardDeviation(var4, var6);
            return var7;
        }
    }

    public static double standardDeviationImaginaryParts(Complex[] var0, Complex[] var1) {
        int var2 = var0.length;
        if (var2 != var1.length) {
            throw new IllegalArgumentException("length of variable array, " + var2 + " and length of weight array, " + var1.length + " are different");
        } else {
            ArrayMaths var3 = new ArrayMaths(var0);
            double[] var4 = var3.array_as_imaginary_part_of_Complex();
            ArrayMaths var5 = new ArrayMaths(var1);
            double[] var6 = var5.array_as_imaginary_part_of_Complex();
            double var7 = standardDeviation(var4, var6);
            return var7;
        }
    }

    public static double standardDeviation(BigDecimal[] var0, BigDecimal[] var1) {
        if (var0.length != var1.length) {
            throw new IllegalArgumentException("length of variable array, " + var0.length + " and length of weight array, " + var1.length + " are different");
        } else {
            return Math.sqrt(variance(var0, var1).doubleValue());
        }
    }

    public static double standardDeviation(BigInteger[] var0, BigInteger[] var1) {
        if (var0.length != var1.length) {
            throw new IllegalArgumentException("length of variable array, " + var0.length + " and length of weight array, " + var1.length + " are different");
        } else {
            return Math.sqrt(variance(var0, var1).doubleValue());
        }
    }

    public static double standardDeviation(double[] var0, double[] var1) {
        if (var0.length != var1.length) {
            throw new IllegalArgumentException("length of variable array, " + var0.length + " and length of weight array, " + var1.length + " are different");
        } else {
            return Math.sqrt(variance(var0, var1));
        }
    }

    public static float standardDeviation(float[] var0, float[] var1) {
        if (var0.length != var1.length) {
            throw new IllegalArgumentException("length of variable array, " + var0.length + " and length of weight array, " + var1.length + " are different");
        } else {
            return (float)Math.sqrt((double)variance(var0, var1));
        }
    }

    public static double volatilityLogChange(BigDecimal[] var0) {
        int var1 = var0.length - 1;
        double[] var2 = new double[var1];

        for(int var3 = 0; var3 < var1; ++var3) {
            var2[var3] = Math.log(var0[var3 + 1].divide(var0[var3], 4).doubleValue());
        }

        return standardDeviation(var2);
    }

    public static double volatilityLogChange(BigInteger[] var0) {
        int var1 = var0.length - 1;
        double[] var2 = new double[var1];

        for(int var3 = 0; var3 < var1; ++var3) {
            var2[var3] = Math.log((new BigDecimal(var0[var3 + 1])).divide(new BigDecimal(var0[var3]), 4).doubleValue());
        }

        return standardDeviation(var2);
    }

    public static double volatilityLogChange(double[] var0) {
        int var1 = var0.length - 1;
        double[] var2 = new double[var1];

        for(int var3 = 0; var3 < var1; ++var3) {
            var2[var3] = Math.log(var0[var3 + 1] / var0[var3]);
        }

        return standardDeviation(var2);
    }

    public static float volatilityLogChange(float[] var0) {
        int var1 = var0.length - 1;
        float[] var2 = new float[var1];

        for(int var3 = 0; var3 < var1; ++var3) {
            var2[var3] = (float)Math.log((double)(var0[var3 + 1] / var0[var3]));
        }

        return standardDeviation(var2);
    }

    public static double volatilityPerCentChange(BigDecimal[] var0) {
        int var1 = var0.length - 1;
        double[] var2 = new double[var1];

        for(int var3 = 0; var3 < var1; ++var3) {
            var2[var3] = var0[var3 + 1].add(var0[var3]).multiply((new BigDecimal(100.0D)).divide(var0[var3], 4)).doubleValue();
        }

        return standardDeviation(var2);
    }

    public static double volatilityPerCentChange(BigInteger[] var0) {
        int var1 = var0.length - 1;
        double[] var2 = new double[var1];
        ArrayMaths var3 = new ArrayMaths(var0);
        BigDecimal[] var4 = var3.getArray_as_BigDecimal();

        for(int var5 = 0; var5 < var1; ++var5) {
            var2[var5] = var4[var5 + 1].add(var4[var5]).multiply((new BigDecimal(100.0D)).divide(var4[var5], 4)).doubleValue();
        }

        var4 = null;
        return standardDeviation(var2);
    }

    public static double volatilityPerCentChange(double[] var0) {
        int var1 = var0.length - 1;
        double[] var2 = new double[var1];

        for(int var3 = 0; var3 < var1; ++var3) {
            var2[var3] = (var0[var3 + 1] - var0[var3]) * 100.0D / var0[var3];
        }

        return standardDeviation(var2);
    }

    public static double volatilityPerCentChange(float[] var0) {
        int var1 = var0.length - 1;
        float[] var2 = new float[var1];

        for(int var3 = 0; var3 < var1; ++var3) {
            var2[var3] = (var0[var3 + 1] - var0[var3]) * 100.0F / var0[var3];
        }

        return (double)standardDeviation(var2);
    }

    public static double coefficientOfVariation(BigInteger[] var0) {
        return 100.0D * standardDeviation(var0) / Math.abs(mean(var0).doubleValue());
    }

    public static double coefficientOfVariation(BigDecimal[] var0) {
        return 100.0D * standardDeviation(var0) / Math.abs(mean(var0).doubleValue());
    }

    public static double coefficientOfVariation(double[] var0) {
        return 100.0D * standardDeviation(var0) / Math.abs(mean(var0));
    }

    public static float coefficientOfVariation(float[] var0) {
        return 100.0F * standardDeviation(var0) / Math.abs(mean(var0));
    }

    public static double coefficientOfVariation(BigInteger[] var0, BigInteger[] var1) {
        int var2 = var0.length;
        if (var2 != var1.length) {
            throw new IllegalArgumentException("length of variable array, " + var2 + " and length of weight array, " + var1.length + " are different");
        } else {
            return 100.0D * standardDeviation(var0, var1) / Math.abs(mean(var0, var1).doubleValue());
        }
    }

    public static double coefficientOfVariation(BigDecimal[] var0, BigDecimal[] var1) {
        int var2 = var0.length;
        if (var2 != var1.length) {
            throw new IllegalArgumentException("length of variable array, " + var2 + " and length of weight array, " + var1.length + " are different");
        } else {
            return 100.0D * standardDeviation(var0, var1) / Math.abs(mean(var0, var1).doubleValue());
        }
    }

    public static double coefficientOfVariation(double[] var0, double[] var1) {
        int var2 = var0.length;
        if (var2 != var1.length) {
            throw new IllegalArgumentException("length of variable array, " + var2 + " and length of weight array, " + var1.length + " are different");
        } else {
            return 100.0D * standardDeviation(var0, var1) / Math.abs(mean(var0, var1));
        }
    }

    public static float coefficientOfVariation(float[] var0, float[] var1) {
        int var2 = var0.length;
        if (var2 != var1.length) {
            throw new IllegalArgumentException("length of variable array, " + var2 + " and length of weight array, " + var1.length + " are different");
        } else {
            return 100.0F * standardDeviation(var0, var1) / Math.abs(mean(var0, var1));
        }
    }

    public static double[] standardize(double[] var0) {
        double var1 = mean(var0);
        double var3 = standardDeviation(var0);
        int var5 = var0.length;
        double[] var6 = new double[var5];
        int var7;
        if (var3 == 0.0D) {
            for(var7 = 0; var7 < var5; ++var7) {
                var6[var7] = 1.0D;
            }
        } else {
            for(var7 = 0; var7 < var5; ++var7) {
                var6[var7] = (var0[var7] - var1) / var3;
            }
        }

        return var6;
    }

    public static double[] standardise(double[] var0) {
        return standardize(var0);
    }

    public static float[] standardize(float[] var0) {
        float var1 = mean(var0);
        float var2 = standardDeviation(var0);
        int var3 = var0.length;
        float[] var4 = new float[var3];
        int var5;
        if ((double)var2 == 0.0D) {
            for(var5 = 0; var5 < var3; ++var5) {
                var4[var5] = 1.0F;
            }
        } else {
            for(var5 = 0; var5 < var3; ++var5) {
                var4[var5] = (var0[var5] - var1) / var2;
            }
        }

        return var4;
    }

    public static float[] standardise(float[] var0) {
        return standardize(var0);
    }

    public static double[] standardize(long[] var0) {
        double var1 = mean(var0);
        double var3 = standardDeviation(var0);
        int var5 = var0.length;
        double[] var6 = new double[var5];
        int var7;
        if (var3 == 0.0D) {
            for(var7 = 0; var7 < var5; ++var7) {
                var6[var7] = 1.0D;
            }
        } else {
            for(var7 = 0; var7 < var5; ++var7) {
                var6[var7] = ((double)var0[var7] - var1) / var3;
            }
        }

        return var6;
    }

    public static double[] standardise(long[] var0) {
        return standardize(var0);
    }

    public static double[] standardize(int[] var0) {
        double var1 = mean(var0);
        double var3 = standardDeviation(var0);
        int var5 = var0.length;
        double[] var6 = new double[var5];
        int var7;
        if (var3 == 0.0D) {
            for(var7 = 0; var7 < var5; ++var7) {
                var6[var7] = 1.0D;
            }
        } else {
            for(var7 = 0; var7 < var5; ++var7) {
                var6[var7] = ((double)var0[var7] - var1) / var3;
            }
        }

        return var6;
    }

    public static double[] standardise(int[] var0) {
        return standardize(var0);
    }

    public static double[] standardize(BigDecimal[] var0) {
        double var1 = mean(var0).doubleValue();
        double var3 = standardDeviation(var0);
        int var5 = var0.length;
        double[] var6 = new double[var5];
        int var7;
        if (var3 == 0.0D) {
            for(var7 = 0; var7 < var5; ++var7) {
                var6[var7] = 1.0D;
            }
        } else {
            for(var7 = 0; var7 < var5; ++var7) {
                var6[var7] = (var0[var7].doubleValue() - var1) / var3;
            }
        }

        return var6;
    }

    public static double[] standardise(BigDecimal[] var0) {
        return standardize(var0);
    }

    public static double[] standardize(BigInteger[] var0) {
        ArrayMaths var1 = new ArrayMaths(var0);
        BigDecimal[] var2 = var1.getArray_as_BigDecimal();
        return standardize(var2);
    }

    public static double[] standardise(BigInteger[] var0) {
        return standardize(var0);
    }

    public static double[] scale(double[] var0, double var1, double var3) {
        double[] var5 = standardize(var0);
        int var6 = var0.length;

        for(int var7 = 0; var7 < var6; ++var7) {
            var5[var7] = var5[var7] * var3 + var1;
        }

        return var5;
    }

    public static float[] scale(float[] var0, float var1, float var2) {
        float[] var3 = standardize(var0);
        int var4 = var0.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            var3[var5] = var3[var5] * var2 + var1;
        }

        return var3;
    }

    public static double[] scale(long[] var0, double var1, double var3) {
        double[] var5 = standardize(var0);
        int var6 = var0.length;

        for(int var7 = 0; var7 < var6; ++var7) {
            var5[var7] = var5[var7] * var3 + var1;
        }

        return var5;
    }

    public static double[] scale(int[] var0, double var1, double var3) {
        double[] var5 = standardize(var0);
        int var6 = var0.length;

        for(int var7 = 0; var7 < var6; ++var7) {
            var5[var7] = var5[var7] * var3 + var1;
        }

        return var5;
    }

    public static double[] scale(BigDecimal[] var0, double var1, double var3) {
        double[] var5 = standardize(var0);
        int var6 = var0.length;

        for(int var7 = 0; var7 < var6; ++var7) {
            var5[var7] = var5[var7] * var3 + var1;
        }

        return var5;
    }

    public static double[] scale(BigInteger[] var0, double var1, double var3) {
        ArrayMaths var5 = new ArrayMaths(var0);
        BigDecimal[] var6 = var5.getArray_as_BigDecimal();
        return scale(var6, var1, var3);
    }

    public static double momentSkewness(double[] var0) {
        int var1 = var0.length;
        double var2 = (double)(var1 - 1);
        if (nFactorOptionS) {
            var2 = (double)var1;
        }

        double var4 = 0.0D;
        double var6 = mean(var0);

        for(int var8 = 0; var8 < var1; ++var8) {
            var4 += Math.pow(var0[var8] - var6, 3.0D);
        }

        var4 /= var2;
        return var4 / Math.pow(standardDeviation(var0), 3.0D);
    }

    public static float momentSkewness(float[] var0) {
        int var1 = var0.length;
        float var2 = (float)(var1 - 1);
        if (nFactorOptionS) {
            var2 = (float)var1;
        }

        float var3 = 0.0F;
        float var4 = mean(var0);

        for(int var5 = 0; var5 < var1; ++var5) {
            var3 = (float)((double)var3 + Math.pow((double)(var0[var5] - var4), 3.0D));
        }

        var3 /= var2;
        return var3 / (float)Math.pow((double)standardDeviation(var0), 3.0D);
    }

    public static double momentSkewness(BigDecimal[] var0) {
        int var1 = var0.length;
        double var2 = (double)(var1 - 1);
        if (nFactorOptionS) {
            var2 = (double)var1;
        }

        BigDecimal var4 = BigDecimal.ZERO;
        BigDecimal var5 = mean(var0);
        double var6 = standardDeviation(var0);

        for(int var8 = 0; var8 < var1; ++var8) {
            BigDecimal var9 = var0[var8].subtract(var5);
            var4 = var4.add(var9.multiply(var9.multiply(var9)));
        }

        var4 = var4.multiply(new BigDecimal(1.0D / var2));
        return var4.doubleValue() / Math.pow(var6, 3.0D);
    }

    public static double momentSkewness(long[] var0) {
        int var1 = var0.length;
        double var2 = (double)(var1 - 1);
        if (nFactorOptionS) {
            var2 = (double)var1;
        }

        double var4 = 0.0D;
        double var6 = mean(var0);

        for(int var8 = 0; var8 < var1; ++var8) {
            var4 += Math.pow((double)var0[var8] - var6, 3.0D);
        }

        var4 /= var2;
        return var4 / Math.pow(standardDeviation(var0), 3.0D);
    }

    public static double momentSkewness(int[] var0) {
        int var1 = var0.length;
        double var2 = (double)(var1 - 1);
        if (nFactorOptionS) {
            var2 = (double)var1;
        }

        double var4 = 0.0D;
        double var6 = mean(var0);

        for(int var8 = 0; var8 < var1; ++var8) {
            var4 += Math.pow((double)var0[var8] - var6, 3.0D);
        }

        var4 /= var2;
        return var4 / Math.pow(standardDeviation(var0), 3.0D);
    }

    public static double medianSkewness(double[] var0) {
        double var1 = mean(var0);
        double var3 = median(var0);
        double var5 = standardDeviation(var0);
        return 3.0D * (var1 - var3) / var5;
    }

    public static float medianSkewness(float[] var0) {
        float var1 = mean(var0);
        float var2 = median(var0);
        float var3 = standardDeviation(var0);
        return 3.0F * (var1 - var2) / var3;
    }

    public static double medianSkewness(BigDecimal[] var0) {
        BigDecimal var1 = mean(var0);
        BigDecimal var2 = median(var0);
        double var3 = standardDeviation(var0);
        return 3.0D * var1.subtract(var2).doubleValue() / var3;
    }

    public static double medianSkewness(long[] var0) {
        double var1 = mean(var0);
        double var3 = median(var0);
        double var5 = standardDeviation(var0);
        return 3.0D * (var1 - var3) / var5;
    }

    public static double medianSkewness(int[] var0) {
        double var1 = mean(var0);
        double var3 = median(var0);
        double var5 = standardDeviation(var0);
        return 3.0D * (var1 - var3) / var5;
    }

    public static double quartileSkewness(double[] var0) {
        int var1 = var0.length;
        double var2 = median(var0);
        byte var4 = 0;
        boolean var5 = false;
        int var6 = var1 / 2 - 1;
        int var7 = var1 - 1;
        int var17;
        if (Fmath.isOdd(var1)) {
            var17 = var6 + 2;
        } else {
            var17 = var6 + 1;
        }

        ArrayMaths var8 = new ArrayMaths(var0);
        double[] var9 = var8.subarray_as_double(var4, var6);
        double[] var10 = var8.subarray_as_double(var17, var7);
        double var11 = median(var9);
        double var13 = median(var10);
        double var15 = (var11 - 2.0D * var2 + var13) / (var13 - var11);
        if (Fmath.isNaN(var15)) {
            var15 = 1.0D;
        }

        return var15;
    }

    public static float quartileSkewness(float[] var0) {
        int var1 = var0.length;
        float var2 = median(var0);
        byte var3 = 0;
        boolean var4 = false;
        int var5 = var1 / 2 - 1;
        int var6 = var1 - 1;
        int var13;
        if (Fmath.isOdd(var1)) {
            var13 = var5 + 2;
        } else {
            var13 = var5 + 1;
        }

        ArrayMaths var7 = new ArrayMaths(var0);
        float[] var8 = var7.subarray_as_float(var3, var5);
        float[] var9 = var7.subarray_as_float(var13, var6);
        float var10 = median(var8);
        float var11 = median(var9);
        float var12 = (var10 - 2.0F * var2 + var11) / (var11 - var10);
        if (Fmath.isNaN(var12)) {
            var12 = 1.0F;
        }

        return var12;
    }

    public static BigDecimal quartileSkewness(BigDecimal[] var0) {
        int var1 = var0.length;
        BigDecimal var2 = median(var0);
        byte var3 = 0;
        boolean var4 = false;
        int var5 = var1 / 2 - 1;
        int var6 = var1 - 1;
        int var15;
        if (Fmath.isOdd(var1)) {
            var15 = var5 + 2;
        } else {
            var15 = var5 + 1;
        }

        ArrayMaths var7 = new ArrayMaths(var0);
        BigDecimal[] var8 = var7.subarray_as_BigDecimal(var3, var5);
        BigDecimal[] var9 = var7.subarray_as_BigDecimal(var15, var6);
        BigDecimal var10 = median(var8);
        BigDecimal var11 = median(var9);
        BigDecimal var12 = var10.subtract(var2.multiply(new BigDecimal(2.0D))).add(var11);
        BigDecimal var13 = var11.subtract(var10);
        BigDecimal var14 = var12.divide(var13, 4);
        if (Fmath.isNaN(var14.doubleValue())) {
            var14 = new BigDecimal(1.0D);
        }

        var8 = null;
        var9 = null;
        var10 = null;
        var2 = null;
        var11 = null;
        var12 = null;
        var13 = null;
        return var14;
    }

    public static BigDecimal quartileSkewness(BigInteger[] var0) {
        ArrayMaths var1 = new ArrayMaths(var0);
        BigDecimal[] var2 = var1.array_as_BigDecimal();
        return quartileSkewness(var2);
    }

    public static double quartileSkewness(long[] var0) {
        int var1 = var0.length;
        double var2 = median(var0);
        byte var4 = 0;
        boolean var5 = false;
        int var6 = var1 / 2 - 1;
        int var7 = var1 - 1;
        int var17;
        if (Fmath.isOdd(var1)) {
            var17 = var6 + 2;
        } else {
            var17 = var6 + 1;
        }

        ArrayMaths var8 = new ArrayMaths(var0);
        double[] var9 = var8.subarray_as_double(var4, var6);
        double[] var10 = var8.subarray_as_double(var17, var7);
        double var11 = median(var9);
        double var13 = median(var10);
        double var15 = (var11 - 2.0D * var2 + var13) / (var13 - var11);
        if (Fmath.isNaN(var15)) {
            var15 = 1.0D;
        }

        return var15;
    }

    public static double quartileSkewness(int[] var0) {
        int var1 = var0.length;
        double var2 = median(var0);
        byte var4 = 0;
        boolean var5 = false;
        int var6 = var1 / 2 - 1;
        int var7 = var1 - 1;
        int var17;
        if (Fmath.isOdd(var1)) {
            var17 = var6 + 2;
        } else {
            var17 = var6 + 1;
        }

        ArrayMaths var8 = new ArrayMaths(var0);
        double[] var9 = var8.subarray_as_double(var4, var6);
        double[] var10 = var8.subarray_as_double(var17, var7);
        double var11 = median(var9);
        double var13 = median(var10);
        double var15 = (var11 - 2.0D * var2 + var13) / (var13 - var11);
        if (Fmath.isNaN(var15)) {
            var15 = 1.0D;
        }

        return var15;
    }

    public static double kurtosis(double[] var0) {
        int var1 = var0.length;
        double var2 = (double)(var1 - 1);
        if (nFactorOptionS) {
            var2 = (double)var1;
        }

        double var4 = 0.0D;
        double var6 = mean(var0);

        for(int var8 = 0; var8 < var1; ++var8) {
            var4 += Math.pow(var0[var8] - var6, 4.0D);
        }

        var4 /= var2;
        double var10 = var4 / Fmath.square(variance(var0));
        if (Fmath.isNaN(var10)) {
            var10 = 2.0D / var2;
        }

        return var10;
    }

    public static double curtosis(double[] var0) {
        return kurtosis(var0);
    }

    public static double kurtosisExcess(double[] var0) {
        return kurtosis(var0) - 3.0D;
    }

    public static double curtosisExcess(double[] var0) {
        return kurtosisExcess(var0);
    }

    public static double excessKurtosis(double[] var0) {
        return kurtosisExcess(var0);
    }

    public static double excessCurtosis(double[] var0) {
        return kurtosisExcess(var0);
    }

    public static float kurtosis(float[] var0) {
        int var1 = var0.length;
        float var2 = (float)(var1 - 1);
        if (nFactorOptionS) {
            var2 = (float)var1;
        }

        float var3 = 0.0F;
        float var4 = mean(var0);

        for(int var5 = 0; var5 < var1; ++var5) {
            var3 = (float)((double)var3 + Math.pow((double)(var0[var5] - var4), 4.0D));
        }

        var3 /= var2;
        float var6 = var3 / Fmath.square(variance(var0));
        if (Fmath.isNaN(var6)) {
            var6 = 2.0F / var2;
        }

        return var6;
    }

    public static float curtosis(float[] var0) {
        return kurtosis(var0);
    }

    public static float kurtosisExcess(float[] var0) {
        return kurtosis(var0) - 3.0F;
    }

    public static float curtosisExcess(float[] var0) {
        return kurtosisExcess(var0);
    }

    public static float excessKurtosis(float[] var0) {
        return kurtosisExcess(var0);
    }

    public static float excessCurtosis(float[] var0) {
        return kurtosisExcess(var0);
    }

    public static BigDecimal kurtosis(BigInteger[] var0) {
        ArrayMaths var1 = new ArrayMaths(var0);
        BigDecimal[] var2 = var1.array_as_BigDecimal();
        return kurtosis(var2);
    }

    public static BigDecimal curtosis(BigInteger[] var0) {
        return kurtosis(var0);
    }

    public static BigDecimal kurtosisExcess(BigInteger[] var0) {
        return kurtosis(var0).subtract(new BigDecimal("3.0"));
    }

    public static BigDecimal curtosisExcess(BigInteger[] var0) {
        return kurtosisExcess(var0);
    }

    public static BigDecimal excessKurtosis(BigInteger[] var0) {
        return kurtosisExcess(var0);
    }

    public static BigDecimal excessCurtosis(BigInteger[] var0) {
        return kurtosisExcess(var0);
    }

    public static BigDecimal kurtosis(BigDecimal[] var0) {
        int var1 = var0.length;
        double var2 = (double)(var1 - 1);
        if (nFactorOptionS) {
            var2 = (double)var1;
        }

        BigDecimal var4 = BigDecimal.ZERO;
        BigDecimal var5 = mean(var0);

        for(int var6 = 0; var6 < var1; ++var6) {
            BigDecimal var7 = var0[var6].subtract(var5);
            var4 = var4.add(var7.multiply(var7.multiply(var7.multiply(var7))));
        }

        var4 = var4.divide(new BigDecimal(var2), 4);
        var5 = variance(var0);
        if (var5.doubleValue() == 0.0D) {
            var4 = new BigDecimal(2.0D / var2);
        } else {
            var4 = var4.divide(var5.multiply(var5), 4);
        }

        var5 = null;
        return var4;
    }

    public static BigDecimal curtosis(BigDecimal[] var0) {
        return kurtosis(var0);
    }

    public static BigDecimal kurtosisExcess(BigDecimal[] var0) {
        return kurtosis(var0).subtract(new BigDecimal("3.0"));
    }

    public static BigDecimal curtosisExcess(BigDecimal[] var0) {
        return kurtosisExcess(var0);
    }

    public static BigDecimal excessCurtosis(BigDecimal[] var0) {
        return kurtosisExcess(var0);
    }

    public static BigDecimal excessKurtosis(BigDecimal[] var0) {
        return kurtosisExcess(var0);
    }

    public static double kurtosis(long[] var0) {
        int var1 = var0.length;
        double var2 = (double)(var1 - 1);
        if (nFactorOptionS) {
            var2 = (double)var1;
        }

        double var4 = 0.0D;
        double var6 = mean(var0);

        for(int var8 = 0; var8 < var1; ++var8) {
            var4 += Math.pow((double)var0[var8] - var6, 4.0D);
        }

        var4 /= var2;
        double var10 = var4 / Fmath.square(variance(var0));
        if (Fmath.isNaN(var10)) {
            var10 = 2.0D / var2;
        }

        return var10;
    }

    public static double curtosis(long[] var0) {
        return kurtosis(var0);
    }

    public static double kurtosisExcess(long[] var0) {
        return kurtosis(var0) - 3.0D;
    }

    public static double curtosisExcess(long[] var0) {
        return kurtosisExcess(var0);
    }

    public static double excessCurtosis(long[] var0) {
        return kurtosisExcess(var0);
    }

    public static double excessKurtosis(long[] var0) {
        return kurtosisExcess(var0);
    }

    public static double kurtosis(int[] var0) {
        int var1 = var0.length;
        double var2 = (double)(var1 - 1);
        if (nFactorOptionS) {
            var2 = (double)var1;
        }

        double var4 = 0.0D;
        double var6 = mean(var0);

        for(int var8 = 0; var8 < var1; ++var8) {
            var4 += Math.pow((double)var0[var8] - var6, 4.0D);
        }

        var4 /= var2;
        double var10 = var4 / Fmath.square(variance(var0));
        if (Fmath.isNaN(var10)) {
            var10 = 2.0D / var2;
        }

        return var10;
    }

    public static double curtosis(int[] var0) {
        return kurtosis(var0);
    }

    public static double kurtosisExcess(int[] var0) {
        return kurtosis(var0) - 3.0D;
    }

    public static double curtosisExcess(int[] var0) {
        return kurtosisExcess(var0);
    }

    public static double excessCurtosis(int[] var0) {
        return kurtosisExcess(var0);
    }

    public static double excessKurtosis(int[] var0) {
        return kurtosisExcess(var0);
    }

    public static BigDecimal variance(BigDecimal[] var0) {
        int var1 = var0.length;
        BigDecimal var2 = BigDecimal.ZERO;
        BigDecimal var3 = mean(var0);

        for(int var4 = 0; var4 < var1; ++var4) {
            BigDecimal var5 = var0[var4].subtract(var3);
            var2 = var2.add(var5.multiply(var5));
        }

        BigDecimal var6 = var2.divide(new BigDecimal((double)(var1 - 1)), 4);
        if (nFactorOptionS) {
            var6 = var2.divide(new BigDecimal((double)var1), 4);
        }

        var2 = null;
        var3 = null;
        return var6;
    }

    public static BigDecimal variance(BigInteger[] var0) {
        int var1 = var0.length;
        BigDecimal var2 = BigDecimal.ZERO;
        BigDecimal var3 = BigDecimal.ZERO;

        int var4;
        for(var4 = 0; var4 < var1; ++var4) {
            var2 = var2.add(new BigDecimal(var0[var4]));
        }

        var3 = var2.divide(new BigDecimal((double)var1), 4);
        var2 = BigDecimal.ZERO;

        for(var4 = 0; var4 < var1; ++var4) {
            BigDecimal var5 = (new BigDecimal(var0[var4])).subtract(var3);
            var2 = var2.add(var5.multiply(var5));
        }

        BigDecimal var6 = var2.divide(new BigDecimal((double)(var1 - 1)), 4);
        if (nFactorOptionS) {
            var6 = var2.divide(new BigDecimal((double)var1), 4);
        }

        var2 = null;
        var3 = null;
        return var6;
    }

    public static Complex variance(Complex[] var0) {
        int var1 = var0.length;
        Complex var2 = Complex.zero();
        Complex var3 = mean(var0);

        for(int var4 = 0; var4 < var1; ++var4) {
            Complex var5 = (new Complex(var0[var4])).minus(var3);
            var2 = var2.plus(var5.times(var5));
        }

        Complex var6 = var2.over(new Complex((double)(var1 - 1)));
        if (nFactorOptionS) {
            var6 = var2.over(new Complex((double)var1));
        }

        return var6;
    }

    public static double varianceConjugateCalcn(Complex[] var0) {
        int var1 = var0.length;
        Complex var2 = Complex.zero();
        Complex var3 = mean(var0);

        for(int var4 = 0; var4 < var1; ++var4) {
            Complex var5 = (new Complex(var0[var4])).minus(var3);
            var2 = var2.plus(var5.times(var5.conjugate()));
        }

        double var6 = var2.getReal() / (double)(var1 - 1);
        if (nFactorOptionS) {
            var6 = var2.getReal() / (double)var1;
        }

        return var6;
    }

    public static double varianceModuli(Complex[] var0) {
        ArrayMaths var1 = new ArrayMaths(var0);
        double[] var2 = var1.array_as_modulus_of_Complex();
        double var3 = variance(var2);
        return var3;
    }

    public static double varianceRealParts(Complex[] var0) {
        ArrayMaths var1 = new ArrayMaths(var0);
        double[] var2 = var1.array_as_real_part_of_Complex();
        double var3 = variance(var2);
        return var3;
    }

    public static double varianceImaginaryParts(Complex[] var0) {
        ArrayMaths var1 = new ArrayMaths(var0);
        double[] var2 = var1.array_as_imaginary_part_of_Complex();
        double var3 = variance(var2);
        return var3;
    }

    public static double variance(double[] var0) {
        int var1 = var0.length;
        double var2 = 0.0D;
        double var4 = mean(var0);
        var2 = 0.0D;

        for(int var6 = 0; var6 < var1; ++var6) {
            var2 += Fmath.square(var0[var6] - var4);
        }

        double var8 = var2 / (double)(var1 - 1);
        if (nFactorOptionS) {
            var8 = var2 / (double)var1;
        }

        return var8;
    }

    public static float variance(float[] var0) {
        int var1 = var0.length;
        float var2 = 0.0F;
        float var3 = mean(var0);

        for(int var4 = 0; var4 < var1; ++var4) {
            var2 += Fmath.square(var0[var4] - var3);
        }

        float var5 = var2 / (float)(var1 - 1);
        if (nFactorOptionS) {
            var5 = var2 / (float)var1;
        }

        return var5;
    }

    public static double variance(int[] var0) {
        int var1 = var0.length;
        double var2 = 0.0D;
        double var4 = mean(var0);

        for(int var6 = 0; var6 < var1; ++var6) {
            var2 += Fmath.square((double)var0[var6] - var4);
        }

        double var8 = var2 / (double)(var1 - 1);
        if (nFactorOptionS) {
            var8 = var2 / (double)var1;
        }

        return var8;
    }

    public static double variance(long[] var0) {
        int var1 = var0.length;
        double var2 = 0.0D;
        double var4 = mean(var0);

        for(int var6 = 0; var6 < var1; ++var6) {
            var2 += Fmath.square((double)var0[var6] - var4);
        }

        double var8 = var2 / (double)(var1 - 1);
        if (nFactorOptionS) {
            var8 = var2 / (double)var1;
        }

        return var8;
    }

    public static double variance(double[] var0, double[] var1) {
        int var2 = var0.length;
        if (var2 != var1.length) {
            throw new IllegalArgumentException("length of variable array, " + var2 + " and length of weight array, " + var1.length + " are different");
        } else {
            double var3 = effectiveSampleNumber(var1);
            double var5 = var3 / (var3 - 1.0D);
            if (nFactorOptionS) {
                var5 = 1.0D;
            }

            double var7 = 0.0D;
            double var9 = 0.0D;
            double var11 = 0.0D;
            double[] var13 = invertAndSquare(var1);

            int var14;
            for(var14 = 0; var14 < var2; ++var14) {
                var7 += var0[var14] * var13[var14];
                var9 += var13[var14];
            }

            var11 = var7 / var9;
            var7 = 0.0D;

            for(var14 = 0; var14 < var2; ++var14) {
                var7 += var13[var14] * Fmath.square(var0[var14] - var11);
            }

            return var7 * var5 / var9;
        }
    }

    public static float variance(float[] var0, float[] var1) {
        int var2 = var0.length;
        if (var2 != var1.length) {
            throw new IllegalArgumentException("length of variable array, " + var2 + " and length of weight array, " + var1.length + " are different");
        } else {
            float var3 = effectiveSampleNumber(var1);
            float var4 = var3 / (var3 - 1.0F);
            if (nFactorOptionS) {
                var4 = 1.0F;
            }

            float var5 = 0.0F;
            float var6 = 0.0F;
            float var7 = 0.0F;
            float[] var8 = invertAndSquare(var1);

            int var9;
            for(var9 = 0; var9 < var2; ++var9) {
                var5 += var0[var9] * var8[var9];
                var6 += var8[var9];
            }

            var7 = var5 / var6;
            var5 = 0.0F;

            for(var9 = 0; var9 < var2; ++var9) {
                var5 += var8[var9] * Fmath.square(var0[var9] - var7);
            }

            return var5 * var4 / var6;
        }
    }

    public static Complex variance(Complex[] var0, Complex[] var1) {
        int var2 = var0.length;
        if (var2 != var1.length) {
            throw new IllegalArgumentException("length of variable array, " + var2 + " and length of weight array, " + var1.length + " are different");
        } else {
            Complex var3 = effectiveSampleNumber(var1);
            Complex var4 = var3.over(var3.minus(1.0D));
            if (nFactorOptionS) {
                var4 = Complex.plusOne();
            }

            Complex var5 = Complex.zero();
            Complex var6 = Complex.zero();
            Complex var7 = Complex.zero();
            Complex[] var8 = invertAndSquare(var1);

            int var9;
            for(var9 = 0; var9 < var2; ++var9) {
                var5 = var5.plus(var0[var9].times(var8[var9]));
                var6 = var6.plus(var8[var9]);
            }

            var7 = var5.over(var6);
            var5 = Complex.zero();

            for(var9 = 0; var9 < var2; ++var9) {
                Complex var10 = var0[var9].minus(var7);
                var5 = var5.plus(var8[var9].times(var10).times(var10));
            }

            return var5.times(var4).over(var6);
        }
    }

    public static double varianceConjugateCalcn(Complex[] var0, Complex[] var1) {
        int var2 = var0.length;
        if (var2 != var1.length) {
            throw new IllegalArgumentException("length of variable array, " + var2 + " and length of weight array, " + var1.length + " are different");
        } else {
            double var3 = effectiveSampleNumberConjugateCalcn(var1);
            double var5 = var3 / (var3 - 1.0D);
            if (nFactorOptionS) {
                var5 = 1.0D;
            }

            Complex var7 = Complex.zero();
            Complex var8 = Complex.zero();
            Complex var9 = Complex.zero();
            Complex var10 = Complex.zero();
            ArrayMaths var11 = new ArrayMaths(var1);
            var11 = var11.invert();
            Complex[] var12 = var11.array_as_Complex();

            int var13;
            for(var13 = 0; var13 < var2; ++var13) {
                var7 = var7.plus(var0[var13].times(var12[var13].times(var12[var13])));
                var8 = var8.plus(var12[var13].times(var12[var13]));
                var9 = var9.plus(var12[var13].times(var12[var13].conjugate()));
            }

            var10 = var7.over(var8);
            var7 = Complex.zero();

            for(var13 = 0; var13 < var2; ++var13) {
                Complex var14 = var0[var13].minus(var10);
                var7 = var7.plus(var12[var13].times(var12[var13].conjugate()).times(var14).times(var14.conjugate()));
            }

            return var5 * var7.times(var5).over(var9).getReal();
        }
    }

    public static double varianceModuli(Complex[] var0, Complex[] var1) {
        int var2 = var0.length;
        if (var2 != var1.length) {
            throw new IllegalArgumentException("length of variable array, " + var2 + " and length of weight array, " + var1.length + " are different");
        } else {
            ArrayMaths var3 = new ArrayMaths(var0);
            double[] var4 = var3.array_as_modulus_of_Complex();
            ArrayMaths var5 = new ArrayMaths(var1);
            double[] var6 = var5.array_as_modulus_of_Complex();
            double var7 = variance(var4, var6);
            return var7;
        }
    }

    public static double varianceRealParts(Complex[] var0, Complex[] var1) {
        int var2 = var0.length;
        if (var2 != var1.length) {
            throw new IllegalArgumentException("length of variable array, " + var2 + " and length of weight array, " + var1.length + " are different");
        } else {
            ArrayMaths var3 = new ArrayMaths(var0);
            double[] var4 = var3.array_as_real_part_of_Complex();
            ArrayMaths var5 = new ArrayMaths(var1);
            double[] var6 = var5.array_as_real_part_of_Complex();
            double var7 = variance(var4, var6);
            return var7;
        }
    }

    public static double varianceImaginaryParts(Complex[] var0, Complex[] var1) {
        int var2 = var0.length;
        if (var2 != var1.length) {
            throw new IllegalArgumentException("length of variable array, " + var2 + " and length of weight array, " + var1.length + " are different");
        } else {
            ArrayMaths var3 = new ArrayMaths(var0);
            double[] var4 = var3.array_as_imaginary_part_of_Complex();
            ArrayMaths var5 = new ArrayMaths(var1);
            double[] var6 = var5.array_as_imaginary_part_of_Complex();
            double var7 = variance(var4, var6);
            return var7;
        }
    }

    public static BigDecimal variance(BigDecimal[] var0, BigDecimal[] var1) {
        int var2 = var0.length;
        if (var2 != var1.length) {
            throw new IllegalArgumentException("length of variable array, " + var2 + " and length of weight array, " + var1.length + " are different");
        } else {
            BigDecimal var3 = effectiveSampleNumber(var1);
            BigDecimal var4 = var3.divide(var3.subtract(BigDecimal.ONE), 4);
            if (nFactorOptionS) {
                var4 = BigDecimal.ONE;
            }

            BigDecimal var5 = BigDecimal.ZERO;
            BigDecimal var6 = BigDecimal.ZERO;
            BigDecimal var7 = BigDecimal.ZERO;
            BigDecimal[] var8 = invertAndSquare(var1);

            int var9;
            for(var9 = 0; var9 < var2; ++var9) {
                var5 = var5.add(var0[var9].multiply(var8[var9]));
                var6 = var6.add(var8[var9]);
            }

            var7 = var5.divide(var6, 4);
            var5 = BigDecimal.ZERO;

            for(var9 = 0; var9 < var2; ++var9) {
                var5 = var5.add(var8[var9].multiply(var0[var9].subtract(var7)).multiply(var0[var9].subtract(var7)));
            }

            var5 = var5.multiply(var4).divide(var6, 4);
            var6 = null;
            var7 = null;
            var8 = null;
            var3 = null;
            var4 = null;
            return var5;
        }
    }

    public static BigDecimal variance(BigInteger[] var0, BigInteger[] var1) {
        int var2 = var0.length;
        if (var2 != var1.length) {
            throw new IllegalArgumentException("length of variable array, " + var2 + " and length of weight array, " + var1.length + " are different");
        } else {
            ArrayMaths var3 = new ArrayMaths(var0);
            ArrayMaths var4 = new ArrayMaths(var1);
            return variance(var3.array_as_BigDecimal(), var4.array_as_BigDecimal());
        }
    }

    public static double standardError(BigDecimal[] var0) {
        return Math.sqrt(variance(var0).doubleValue() / (double)var0.length);
    }

    public static double standardError(BigInteger[] var0) {
        return Math.sqrt(variance(var0).doubleValue() / (double)var0.length);
    }

    public static Complex standardError(Complex[] var0) {
        return Complex.sqrt(variance(var0).over((double)var0.length));
    }

    public static double standardErrorConjugateCalcn(Complex[] var0) {
        return Math.sqrt(varianceConjugateCalcn(var0) / (double)var0.length);
    }

    public static double standardErrorModuli(Complex[] var0) {
        ArrayMaths var1 = new ArrayMaths(var0);
        double[] var2 = var1.array_as_modulus_of_Complex();
        return standardError(var2);
    }

    public static double standardErrorRealParts(Complex[] var0) {
        ArrayMaths var1 = new ArrayMaths(var0);
        double[] var2 = var1.array_as_real_part_of_Complex();
        return standardError(var2);
    }

    public static double standardErrorImaginaryParts(Complex[] var0) {
        ArrayMaths var1 = new ArrayMaths(var0);
        double[] var2 = var1.array_as_imaginary_part_of_Complex();
        return standardError(var2);
    }

    public static double standardError(double[] var0) {
        return Math.sqrt(variance(var0) / (double)var0.length);
    }

    public static float standardError(float[] var0) {
        return (float)Math.sqrt((double)(variance(var0) / (float)var0.length));
    }

    public static double standardError(int[] var0) {
        return Math.sqrt(variance(var0) / (double)var0.length);
    }

    public static double standardError(long[] var0) {
        return Math.sqrt(variance(var0) / (double)var0.length);
    }

    public static Complex standardError(Complex[] var0, Complex[] var1) {
        if (var0.length != var1.length) {
            throw new IllegalArgumentException("length of variable array, " + var0.length + " and length of weight array, " + var1.length + " are different");
        } else {
            Complex var2 = effectiveSampleNumber(var1);
            return Complex.sqrt(variance(var0, var1).over(var2));
        }
    }

    public static double standardErrorConjugateCalcn(Complex[] var0, Complex[] var1) {
        if (var0.length != var1.length) {
            throw new IllegalArgumentException("length of variable array, " + var0.length + " and length of weight array, " + var1.length + " are different");
        } else {
            double var2 = effectiveSampleNumberConjugateCalcn(var1);
            return Math.sqrt(varianceConjugateCalcn(var0, var1) / var2);
        }
    }

    public static double standardErrorModuli(Complex[] var0, Complex[] var1) {
        int var2 = var0.length;
        if (var2 != var1.length) {
            throw new IllegalArgumentException("length of variable array, " + var2 + " and length of weight array, " + var1.length + " are different");
        } else {
            ArrayMaths var3 = new ArrayMaths(var0);
            double[] var4 = var3.array_as_modulus_of_Complex();
            ArrayMaths var5 = new ArrayMaths(var1);
            double[] var6 = var5.array_as_modulus_of_Complex();
            return standardError(var4, var6);
        }
    }

    public static double standardErrorRealParts(Complex[] var0, Complex[] var1) {
        int var2 = var0.length;
        if (var2 != var1.length) {
            throw new IllegalArgumentException("length of variable array, " + var2 + " and length of weight array, " + var1.length + " are different");
        } else {
            ArrayMaths var3 = new ArrayMaths(var0);
            double[] var4 = var3.array_as_real_part_of_Complex();
            ArrayMaths var5 = new ArrayMaths(var1);
            double[] var6 = var5.array_as_real_part_of_Complex();
            return standardError(var4, var6);
        }
    }

    public static double standardErrorImaginaryParts(Complex[] var0, Complex[] var1) {
        int var2 = var0.length;
        if (var2 != var1.length) {
            throw new IllegalArgumentException("length of variable array, " + var2 + " and length of weight array, " + var1.length + " are different");
        } else {
            ArrayMaths var3 = new ArrayMaths(var0);
            double[] var4 = var3.array_as_imaginary_part_of_Complex();
            ArrayMaths var5 = new ArrayMaths(var1);
            double[] var6 = var5.array_as_imaginary_part_of_Complex();
            return standardError(var4, var6);
        }
    }

    public static double standardError(BigDecimal[] var0, BigDecimal[] var1) {
        if (var0.length != var1.length) {
            throw new IllegalArgumentException("length of variable array, " + var0.length + " and length of weight array, " + var1.length + " are different");
        } else {
            double var2 = effectiveSampleNumber(var1).doubleValue();
            return Math.sqrt(variance(var0, var1).doubleValue() / var2);
        }
    }

    public static double standardError(BigInteger[] var0, BigInteger[] var1) {
        if (var0.length != var1.length) {
            throw new IllegalArgumentException("length of variable array, " + var0.length + " and length of weight array, " + var1.length + " are different");
        } else {
            double var2 = effectiveSampleNumber(var1).doubleValue();
            return Math.sqrt(variance(var0, var1).doubleValue() / var2);
        }
    }

    public static double standardError(double[] var0, double[] var1) {
        if (var0.length != var1.length) {
            throw new IllegalArgumentException("length of variable array, " + var0.length + " and length of weight array, " + var1.length + " are different");
        } else {
            double var2 = effectiveSampleNumber(var1);
            return Math.sqrt(variance(var0, var1) / var2);
        }
    }

    public static float standardError(float[] var0, float[] var1) {
        float var2 = effectiveSampleNumber(var1);
        return (float)Math.sqrt((double)(variance(var0, var1) / var2));
    }

    public static double covariance(double[] var0, double[] var1) {
        int var2 = var0.length;
        if (var2 != var1.length) {
            throw new IllegalArgumentException("length of x variable array, " + var2 + " and length of y array, " + var1.length + " are different");
        } else {
            double var3 = (double)(var2 - 1);
            if (nFactorOptionS) {
                var3 = (double)var2;
            }

            double var5 = 0.0D;
            double var7 = 0.0D;
            double var9 = 0.0D;
            double var11 = 0.0D;

            for(int var13 = 0; var13 < var2; ++var13) {
                var5 += var0[var13];
                var9 += var1[var13];
            }

            var7 = var5 / (double)var2;
            var11 = var9 / (double)var2;
            double var16 = 0.0D;

            for(int var15 = 0; var15 < var2; ++var15) {
                var16 += (var0[var15] - var7) * (var1[var15] - var11);
            }

            return var16 / var3;
        }
    }

    public static float covariance(float[] var0, float[] var1) {
        int var2 = var0.length;
        if (var2 != var1.length) {
            throw new IllegalArgumentException("length of x variable array, " + var2 + " and length of y array, " + var1.length + " are different");
        } else {
            float var3 = (float)(var2 - 1);
            if (nFactorOptionS) {
                var3 = (float)var2;
            }

            float var4 = 0.0F;
            float var5 = 0.0F;
            float var6 = 0.0F;
            float var7 = 0.0F;

            for(int var8 = 0; var8 < var2; ++var8) {
                var4 += var0[var8];
                var6 += var1[var8];
            }

            var5 = var4 / (float)var2;
            var7 = var6 / (float)var2;
            float var10 = 0.0F;

            for(int var9 = 0; var9 < var2; ++var9) {
                var10 += (var0[var9] - var5) * (var1[var9] - var7);
            }

            return var10 / var3;
        }
    }

    public static double covariance(int[] var0, int[] var1) {
        int var2 = var0.length;
        if (var2 != var1.length) {
            throw new IllegalArgumentException("length of x variable array, " + var2 + " and length of y array, " + var1.length + " are different");
        } else {
            double var3 = (double)(var2 - 1);
            if (nFactorOptionS) {
                var3 = (double)var2;
            }

            double var5 = 0.0D;
            double var7 = 0.0D;
            double var9 = 0.0D;
            double var11 = 0.0D;

            for(int var13 = 0; var13 < var2; ++var13) {
                var5 += (double)var0[var13];
                var9 += (double)var1[var13];
            }

            var7 = var5 / (double)var2;
            var11 = var9 / (double)var2;
            double var16 = 0.0D;

            for(int var15 = 0; var15 < var2; ++var15) {
                var16 += ((double)var0[var15] - var7) * ((double)var1[var15] - var11);
            }

            return var16 / var3;
        }
    }

    public static double covariance(long[] var0, long[] var1) {
        int var2 = var0.length;
        if (var2 != var1.length) {
            throw new IllegalArgumentException("length of x variable array, " + var2 + " and length of y array, " + var1.length + " are different");
        } else {
            double var3 = (double)(var2 - 1);
            if (nFactorOptionS) {
                var3 = (double)var2;
            }

            double var5 = 0.0D;
            double var7 = 0.0D;
            double var9 = 0.0D;
            double var11 = 0.0D;

            for(int var13 = 0; var13 < var2; ++var13) {
                var5 += (double)var0[var13];
                var9 += (double)var1[var13];
            }

            var7 = var5 / (double)var2;
            var11 = var9 / (double)var2;
            double var16 = 0.0D;

            for(int var15 = 0; var15 < var2; ++var15) {
                var16 += ((double)var0[var15] - var7) * ((double)var1[var15] - var11);
            }

            return var16 / var3;
        }
    }

    public static double covariance(double[] var0, double[] var1, double[] var2) {
        int var3 = var0.length;
        if (var3 != var1.length) {
            throw new IllegalArgumentException("length of x variable array, " + var3 + " and length of y array, " + var1.length + " are different");
        } else if (var3 != var2.length) {
            throw new IllegalArgumentException("length of x variable array, " + var3 + " and length of weight array, " + var1.length + " are different");
        } else {
            double var4 = effectiveSampleNumber(var2);
            double var6 = var4 / (var4 - 1.0D);
            if (nFactorOptionS) {
                var6 = 1.0D;
            }

            double var8 = 0.0D;
            double var10 = 0.0D;
            double var12 = 0.0D;
            double var14 = 0.0D;
            double var16 = 0.0D;
            double[] var18 = invertAndSquare(var2);

            for(int var19 = 0; var19 < var3; ++var19) {
                var8 += var0[var19] * var18[var19];
                var10 += var1[var19] * var18[var19];
                var12 += var18[var19];
            }

            var14 = var8 / var12;
            var16 = var10 / var12;
            double var22 = 0.0D;

            for(int var21 = 0; var21 < var3; ++var21) {
                var22 += var18[var21] * (var0[var21] - var14) * (var1[var21] - var16);
            }

            return var22 * var6 / var12;
        }
    }

    public static double corrCoeff(double[] var0, double[] var1) {
        double var2 = 0.0D;
        double var4 = 0.0D;
        int var6 = var0.length;
        if (var1.length != var6) {
            throw new IllegalArgumentException("array lengths must be equal");
        } else {
            int var7 = var6 - 1;
            double var8 = 0.0D;
            double var10 = 0.0D;

            for(int var12 = 0; var12 < var6; ++var12) {
                var8 += var0[var12];
                var10 += var1[var12];
            }

            var8 /= (double)var6;
            var10 /= (double)var6;
            double var20 = 0.0D;
            double var14 = 0.0D;
            double var16 = 0.0D;

            for(int var18 = 0; var18 < var6; ++var18) {
                var20 += Fmath.square(var0[var18] - var8);
                var14 += Fmath.square(var1[var18] - var10);
                var16 += (var0[var18] - var8) * (var1[var18] - var10);
            }

            double var21 = var16 / Math.sqrt(var20 * var14);
            if (var21 > 1.0D && Fmath.isEqualWithinLimits(var21, 1.0D, 0.001D)) {
                var21 = 1.0D;
            }

            if (var21 < -1.0D && Fmath.isEqualWithinLimits(Math.abs(var21), 1.0D, 0.001D)) {
                var21 = -1.0D;
            }

            return var21;
        }
    }

    public static float corrCoeff(float[] var0, float[] var1) {
        int var2 = var0.length;
        if (var1.length != var2) {
            throw new IllegalArgumentException("array lengths must be equal");
        } else {
            int var3 = var0.length;
            double[] var4 = new double[var3];
            double[] var5 = new double[var3];

            for(int var6 = 0; var6 < var3; ++var6) {
                var4[var6] = (double)var0[var6];
                var5[var6] = (double)var1[var6];
            }

            return (float)corrCoeff(var4, var5);
        }
    }

    public static double corrCoeff(int[] var0, int[] var1) {
        int var2 = var0.length;
        if (var1.length != var2) {
            throw new IllegalArgumentException("array lengths must be equal");
        } else {
            double[] var3 = new double[var2];
            double[] var4 = new double[var2];

            for(int var5 = 0; var5 < var2; ++var5) {
                var3[var5] = (double)var0[var5];
                var4[var5] = (double)var1[var5];
            }

            return corrCoeff(var3, var4);
        }
    }

    public static double corrCoeff(double[] var0, double[] var1, double[] var2) {
        int var3 = var0.length;
        if (var1.length != var3) {
            throw new IllegalArgumentException("x and y array lengths must be equal");
        } else if (var2.length != var3) {
            throw new IllegalArgumentException("x and weight array lengths must be equal");
        } else {
            double var4 = covariance(var0, var1, var2);
            double var6 = variance(var0, var2);
            double var8 = variance(var1, var2);
            double var10 = var4 / Math.sqrt(var6 * var8);
            if (var10 > 1.0D && Fmath.isEqualWithinLimits(var10, 1.0D, 0.001D)) {
                var10 = 1.0D;
            }

            if (var10 < -1.0D && Fmath.isEqualWithinLimits(Math.abs(var10), 1.0D, 0.001D)) {
                var10 = -1.0D;
            }

            return var10;
        }
    }

    public static double corrCoeff(int var0, int var1, int var2, int var3) {
        double var4 = (double)(var0 * var3 - var1 * var2) / Math.sqrt((double)((var0 + var1) * (var2 + var3) * (var0 + var2) * (var1 + var3)));
        if (var4 > 1.0D && Fmath.isEqualWithinLimits(var4, 1.0D, 0.001D)) {
            var4 = 1.0D;
        }

        if (var4 < -1.0D && Fmath.isEqualWithinLimits(Math.abs(var4), 1.0D, 0.001D)) {
            var4 = -1.0D;
        }

        return var4;
    }

    public static double corrCoeff(int[][] var0) {
        double var1 = (double)var0[0][0];
        double var3 = (double)var0[0][1];
        double var5 = (double)var0[1][0];
        double var7 = (double)var0[1][1];
        double var9 = (var1 * var7 - var3 * var5) / Math.sqrt((var1 + var3) * (var5 + var7) * (var1 + var5) * (var3 + var7));
        if (var9 > 1.0D && Fmath.isEqualWithinLimits(var9, 1.0D, 0.001D)) {
            var9 = 1.0D;
        }

        if (var9 < -1.0D && Fmath.isEqualWithinLimits(Math.abs(var9), 1.0D, 0.001D)) {
            var9 = -1.0D;
        }

        return var9;
    }

    public static double linearCorrCoeffProb(double var0, int var2) {
        return corrCoeffProb(var0, var2);
    }

    public static double corrCoeffCDF(double var0, int var2) {
        return corrCoeffProb(var0, var2);
    }

    public static double corrCoeffCDFtwoTailed(double var0, int var2) {
        return corrCoeffProb(var0, var2);
    }

    public static double corrCoeffCDFoneTailed(double var0, int var2) {
        return corrCoeffProb(var0, var2) / 2.0D;
    }

    public static double corrCoeffProb(double var0, int var2) {
        if (Math.abs(var0) > 1.0D) {
            throw new IllegalArgumentException("|Correlation coefficient| > 1 :  " + var0);
        } else {
            CorrCoeff var3 = new CorrCoeff();
            var3.a = ((double)var2 - 2.0D) / 2.0D;
            double var4 = Integration.gaussQuad(var3, Math.abs(var0), 1.0D, 128);
            double var6 = 2.0D * Math.exp(logGamma(((double)var2 + 1.0D) / 2.0D) - logGamma((double)var2 / 2.0D)) / Math.sqrt(3.141592653589793D);
            return var6 * var4;
        }
    }

    public static double linearCorrCoeff(double var0, int var2) {
        return corrCoeffPDF(var0, var2);
    }

    public static double corrCoeffPDF(double var0, int var2) {
        if (Math.abs(var0) > 1.0D) {
            throw new IllegalArgumentException("|Correlation coefficient| > 1 :  " + var0);
        } else {
            double var3 = ((double)var2 - 2.0D) / 2.0D;
            double var5 = Math.pow(1.0D - Fmath.square(var0), var3);
            double var7 = Math.exp(logGamma(((double)var2 + 1.0D) / 2.0D) - logGamma((double)var2 / 2.0D)) / Math.sqrt(3.141592653589793D);
            return var7 * var5;
        }
    }

    public static double corrCoeffPdf(double var0, int var2) {
        if (Math.abs(var0) > 1.0D) {
            throw new IllegalArgumentException("|Correlation coefficient| > 1 :  " + var0);
        } else {
            double var3 = ((double)var2 - 2.0D) / 2.0D;
            double var5 = Math.pow(1.0D - Fmath.square(var0), var3);
            double var7 = Math.exp(logGamma(((double)var2 + 1.0D) / 2.0D) - logGamma((double)var2 / 2.0D)) / Math.sqrt(3.141592653589793D);
            return var7 * var5;
        }
    }

    public static double corrCoeffInverseCDFoneTailed(double var0, int var2) {
        return corrCoeffInverseCDF(2.0D * var0, var2);
    }

    public static double corrCoeffInverseCDFtwoTailed(double var0, int var2) {
        return corrCoeffInverseCDF(var0, var2);
    }

    public static double corrCoeffInverseCDF(double var0, int var2) {
        if (var0 >= 0.0D && var0 <= 1.0D) {
            double var3 = 0.0D;
            if (var0 == 0.0D) {
                var3 = 1.0D;
            } else if (var0 == 1.0D) {
                var3 = 0.0D;
            } else {
                CorrCoeffFunct var5 = new CorrCoeffFunct();
                var5.nu = var2;
                double var6 = 1.0E-12D;
                double var8 = 0.0D;
                double var10 = 1.0D;
                RealRoot var12 = new RealRoot();
                var12.noBoundsExtensions();
                var12.setLowerBound(0.0D);
                var12.setUpperBound(1.0D);
                var12.setTolerance(var6);
                var12.resetNaNexceptionToTrue();
                var12.suppressLimitReachedMessage();
                var12.suppressNaNmessage();
                var5.cfd = var0;
                var3 = var12.bisect(var5, var8, var10);
            }

            return var3;
        } else {
            throw new IllegalArgumentException("Entered probability value, " + var0 + ", must lie between 0 and 1 inclusive");
        }
    }

    public static double shannonEntropy(double[] var0) {
        ArrayMaths var1 = new ArrayMaths(var0);
        double var2 = var1.getMaximum_as_double();
        if (var2 > 1.0D) {
            throw new IllegalArgumentException("All probabilites must be less than or equal to 1; the maximum supplied probabilty is " + var2);
        } else {
            double var4 = var1.getMinimum_as_double();
            if (var4 < 0.0D) {
                throw new IllegalArgumentException("All probabilites must be greater than or equal to 0; the minimum supplied probabilty is " + var4);
            } else {
                double var6 = var1.getSum_as_double();
                if (!Fmath.isEqualWithinPerCent(var6, 1.0D, 0.1D)) {
                    throw new IllegalArgumentException("the probabilites must add up to 1 within an error of 0.1%; they add up to " + var6);
                } else {
                    return var1.minusxLog2x().getSum_as_double();
                }
            }
        }
    }

    public static double shannonEntropyBit(double[] var0) {
        return shannonEntropy(var0);
    }

    public static double shannonEntropyNat(double[] var0) {
        ArrayMaths var1 = new ArrayMaths(var0);
        double var2 = var1.getMaximum_as_double();
        if (var2 > 1.0D) {
            throw new IllegalArgumentException("All probabilites must be less than or equal to 1; the maximum supplied probabilty is " + var2);
        } else {
            double var4 = var1.getMinimum_as_double();
            if (var4 < 0.0D) {
                throw new IllegalArgumentException("All probabilites must be greater than or equal to 0; the minimum supplied probabilty is " + var4);
            } else {
                double var6 = var1.getSum_as_double();
                if (!Fmath.isEqualWithinPerCent(var6, 1.0D, 0.1D)) {
                    throw new IllegalArgumentException("the probabilites must add up to 1 within an error of 0.1%; they add up to " + var6);
                } else {
                    return var1.minusxLogEx().getSum_as_double();
                }
            }
        }
    }

    public static double shannonEntropyDit(double[] var0) {
        ArrayMaths var1 = new ArrayMaths(var0);
        double var2 = var1.getMaximum_as_double();
        if (var2 > 1.0D) {
            throw new IllegalArgumentException("All probabilites must be less than or equal to 1; the maximum supplied probabilty is " + var2);
        } else {
            double var4 = var1.getMinimum_as_double();
            if (var4 < 0.0D) {
                throw new IllegalArgumentException("All probabilites must be greater than or equal to 0; the minimum supplied probabilty is " + var4);
            } else {
                double var6 = var1.getSum_as_double();
                if (!Fmath.isEqualWithinPerCent(var6, 1.0D, 0.1D)) {
                    throw new IllegalArgumentException("the probabilites must add up to 1 within an error of 0.1%; they add up to " + var6);
                } else {
                    return var1.minusxLog10x().getSum_as_double();
                }
            }
        }
    }

    public static double binaryShannonEntropy(double var0) {
        if (var0 > 1.0D) {
            throw new IllegalArgumentException("The probabiliy, " + var0 + ",  must be less than or equal to 1");
        } else if (var0 < 0.0D) {
            throw new IllegalArgumentException("The probabiliy, " + var0 + ",  must be greater than or equal to 0");
        } else {
            double var2 = 0.0D;
            if (var0 > 0.0D && var0 < 1.0D) {
                var2 = -var0 * Fmath.log2(var0) - (1.0D - var0) * Fmath.log2(1.0D - var0);
            }

            return var2;
        }
    }

    public static double binaryShannonEntropyBit(double var0) {
        return binaryShannonEntropy(var0);
    }

    public static double binaryShannonEntropyNat(double var0) {
        if (var0 > 1.0D) {
            throw new IllegalArgumentException("The probabiliy, " + var0 + ",  must be less than or equal to 1");
        } else if (var0 < 0.0D) {
            throw new IllegalArgumentException("The probabiliy, " + var0 + ",  must be greater than or equal to 0");
        } else {
            double var2 = 0.0D;
            if (var0 > 0.0D && var0 < 1.0D) {
                var2 = -var0 * Math.log(var0) - (1.0D - var0) * Math.log(1.0D - var0);
            }

            return var2;
        }
    }

    public static double binaryShannonEntropyDit(double var0) {
        if (var0 > 1.0D) {
            throw new IllegalArgumentException("The probabiliy, " + var0 + ",  must be less than or equal to 1");
        } else if (var0 < 0.0D) {
            throw new IllegalArgumentException("The probabiliy, " + var0 + ",  must be greater than or equal to 0");
        } else {
            double var2 = 0.0D;
            if (var0 > 0.0D && var0 < 1.0D) {
                var2 = -var0 * Math.log10(var0) - (1.0D - var0) * Math.log10(1.0D - var0);
            }

            return var2;
        }
    }

    public static double renyiEntropy(double[] var0, double var1) {
        ArrayMaths var3 = new ArrayMaths(var0);
        double var4 = var3.getMaximum_as_double();
        if (var4 > 1.0D) {
            throw new IllegalArgumentException("All probabilites must be less than or equal to 1; the maximum supplied probabilty is " + var4);
        } else {
            double var6 = var3.getMinimum_as_double();
            if (var6 < 0.0D) {
                throw new IllegalArgumentException("All probabilites must be greater than or equal to 0; the minimum supplied probabilty is " + var6);
            } else {
                double var8 = var3.getSum_as_double();
                if (!Fmath.isEqualWithinPerCent(var8, 1.0D, 0.1D)) {
                    throw new IllegalArgumentException("the probabilites must add up to 1 within an error of 0.1%; they add up to " + var8);
                } else if (var1 < 0.0D) {
                    throw new IllegalArgumentException("alpha, " + var1 + ", must be greater than or equal to 0");
                } else {
                    double var10 = 0.0D;
                    if (var1 == 0.0D) {
                        var10 = (double)Fmath.log2((float)var0.length);
                    } else if (var1 == 1.0D) {
                        var10 = shannonEntropy(var0);
                    } else if (Fmath.isPlusInfinity(var1)) {
                        var10 = -Fmath.log2(var4);
                    } else if (var1 <= 3000.0D) {
                        var3 = var3.pow(var1);
                        boolean var12 = false;
                        if (var3.getMaximum_as_double() == 4.9E-324D) {
                            var12 = true;
                        }

                        var10 = Fmath.log2(var3.getSum_as_double()) / (1.0D - var1);
                        if (Fmath.isPlusInfinity(var10) || var12) {
                            var10 = -Fmath.log2(var4);
                            double var13 = var10;
                            System.out.println("Stat: renyiEntropy/renyiEntopyBit: underflow or overflow in calculating the entropy");
                            boolean var15 = true;
                            boolean var16 = true;
                            boolean var17 = true;
                            int var18 = 0;
                            double var19 = var1 / 2.0D;
                            double var21 = 0.0D;

                            double var34;
                            while(var17) {
                                while(var15) {
                                    ArrayMaths var23 = new ArrayMaths(var0);
                                    var23 = var23.pow(var19);
                                    var21 = Fmath.log2(var23.getSum_as_double()) / (1.0D - var19);
                                    if (Fmath.isPlusInfinity(var21)) {
                                        var19 /= 2.0D;
                                        ++var18;
                                        if (var18 == 100000) {
                                            var15 = false;
                                            var16 = false;
                                        }
                                    } else {
                                        var15 = false;
                                    }
                                }

                                var34 = var19 + 40.0D * var1 / 1000.0D;
                                ArrayMaths var25 = new ArrayMaths(var0);
                                var25 = var25.pow(var34);
                                double var26 = Fmath.log2(var25.getSum_as_double()) / (1.0D - var34);
                                if (!Fmath.isPlusInfinity(var26)) {
                                    var17 = false;
                                } else {
                                    var19 /= 2.0D;
                                }
                            }

                            ArrayList var27 = new ArrayList();
                            if (var16) {
                                double var28 = var19 / 1000.0D;
                                var15 = true;

                                while(var15) {
                                    var27.add(new Double(var19));
                                    var27.add(new Double(var21));
                                    var34 = var21;
                                    double var35 = var19;
                                    var19 += var28;
                                    ArrayMaths var30 = new ArrayMaths(var0);
                                    var30 = var30.pow(var19);
                                    var21 = Fmath.log2(var30.getSum_as_double()) / (1.0D - var19);
                                    if (Fmath.isPlusInfinity(var21)) {
                                        var15 = false;
                                        var21 = var34;
                                        var19 = var35;
                                    }
                                }
                            }

                            int var36 = var27.size() / 2 - 20;
                            double[] var29 = new double[var36];
                            double[] var37 = new double[var36];
                            int var31 = -1;

                            for(int var32 = 0; var32 < var36; ++var32) {
                                ++var31;
                                var29[var32] = (Double)var27.get(var31);
                                ++var31;
                                var37[var32] = Math.log((Double)var27.get(var31) - var13);
                            }

                            Regression var38 = new Regression(var29, var37);
                            var38.linear();
                            double[] var33 = var38.getCoeff();
                            var10 = Math.exp(var33[0] + var33[1] * var1) + var13;
                            System.out.println("An interpolated entropy of " + var10 + " returned (see documentation for exponential interpolation)");
                            System.out.println("Lowest calculable value =  " + (Math.exp(var37[var36 - 1]) + var13) + ", alpha = " + var29[var36 - 1]);
                            System.out.println("Minimum entropy value =  " + var13 + ", alpha = infinity");
                        }
                    } else {
                        var10 = -Fmath.log2(var4);
                        System.out.println("Stat: renyiEntropy/renyiEntropyBit: underflow or overflow in calculating the entropy");
                        System.out.println("An interpolated entropy of " + var10 + " returned (see documentation for exponential interpolation)");
                    }

                    return var10;
                }
            }
        }
    }

    public static double renyiEntropyNat(double[] var0, double var1) {
        ArrayMaths var3 = new ArrayMaths(var0);
        double var4 = var3.getMaximum_as_double();
        if (var4 > 1.0D) {
            throw new IllegalArgumentException("All probabilites must be less than or equal to 1; the maximum supplied probabilty is " + var4);
        } else {
            double var6 = var3.getMinimum_as_double();
            if (var6 < 0.0D) {
                throw new IllegalArgumentException("All probabilites must be greater than or equal to 0; the minimum supplied probabilty is " + var6);
            } else {
                double var8 = var3.getSum_as_double();
                if (!Fmath.isEqualWithinPerCent(var8, 1.0D, 0.1D)) {
                    throw new IllegalArgumentException("the probabilites must add up to 1 within an error of 0.1%; they add up to " + var8);
                } else if (var1 < 0.0D) {
                    throw new IllegalArgumentException("alpha, " + var1 + ", must be greater than or equal to 0");
                } else {
                    double var10 = 0.0D;
                    if (var1 == 0.0D) {
                        var10 = Math.log((double)var0.length);
                    } else if (var1 == 1.0D) {
                        var10 = shannonEntropy(var0);
                    } else if (Fmath.isPlusInfinity(var1)) {
                        var10 = -Math.log(var4);
                    } else if (var1 <= 3000.0D) {
                        var3 = var3.pow(var1);
                        boolean var12 = false;
                        if (var3.getMaximum_as_double() == 4.9E-324D) {
                            var12 = true;
                        }

                        var10 = Math.log(var3.getSum_as_double()) / (1.0D - var1);
                        if (Fmath.isPlusInfinity(var10) || var12) {
                            var10 = -Math.log(var4);
                            double var13 = var10;
                            System.out.println("Stat: renyiEntropyNat: underflow or overflow in calculating the entropy");
                            boolean var15 = true;
                            boolean var16 = true;
                            boolean var17 = true;
                            int var18 = 0;
                            double var19 = var1 / 2.0D;
                            double var21 = 0.0D;

                            double var34;
                            while(var17) {
                                while(var15) {
                                    ArrayMaths var23 = new ArrayMaths(var0);
                                    var23 = var23.pow(var19);
                                    var21 = Math.log(var23.getSum_as_double()) / (1.0D - var19);
                                    if (Fmath.isPlusInfinity(var21)) {
                                        var19 /= 2.0D;
                                        ++var18;
                                        if (var18 == 100000) {
                                            var15 = false;
                                            var16 = false;
                                        }
                                    } else {
                                        var15 = false;
                                    }
                                }

                                var34 = var19 + 40.0D * var1 / 1000.0D;
                                ArrayMaths var25 = new ArrayMaths(var0);
                                var25 = var25.pow(var34);
                                double var26 = Math.log(var25.getSum_as_double()) / (1.0D - var34);
                                if (!Fmath.isPlusInfinity(var26)) {
                                    var17 = false;
                                } else {
                                    var19 /= 2.0D;
                                }
                            }

                            ArrayList var27 = new ArrayList();
                            if (var16) {
                                double var28 = var19 / 1000.0D;
                                var15 = true;

                                while(var15) {
                                    var27.add(new Double(var19));
                                    var27.add(new Double(var21));
                                    var34 = var21;
                                    double var35 = var19;
                                    var19 += var28;
                                    ArrayMaths var30 = new ArrayMaths(var0);
                                    var30 = var30.pow(var19);
                                    var21 = Math.log(var30.getSum_as_double()) / (1.0D - var19);
                                    if (Fmath.isPlusInfinity(var21)) {
                                        var15 = false;
                                        var21 = var34;
                                        var19 = var35;
                                    }
                                }
                            }

                            int var36 = var27.size() / 2 - 20;
                            double[] var29 = new double[var36];
                            double[] var37 = new double[var36];
                            int var31 = -1;

                            for(int var32 = 0; var32 < var36; ++var32) {
                                ++var31;
                                var29[var32] = (Double)var27.get(var31);
                                ++var31;
                                var37[var32] = Math.log((Double)var27.get(var31) - var13);
                            }

                            Regression var38 = new Regression(var29, var37);
                            var38.linear();
                            double[] var33 = var38.getCoeff();
                            var10 = Math.exp(var33[0] + var33[1] * var1) + var13;
                            System.out.println("An interpolated entropy of " + var10 + " returned (see documentation for exponential interpolation)");
                            System.out.println("Lowest calculable value =  " + (Math.exp(var37[var36 - 1]) + var13) + ", alpha = " + var29[var36 - 1]);
                            System.out.println("Minimum entropy value =  " + var13 + ", alpha = infinity");
                        }
                    } else {
                        var10 = -Math.log(var4);
                        System.out.println("Stat: renyiEntropyNat: underflow or overflow in calculating the entropy");
                        System.out.println("An interpolated entropy of " + var10 + " returned (see documentation for exponential interpolation)");
                    }

                    return var10;
                }
            }
        }
    }

    public static double renyiEntropyDit(double[] var0, double var1) {
        ArrayMaths var3 = new ArrayMaths(var0);
        double var4 = var3.getMaximum_as_double();
        if (var4 > 1.0D) {
            throw new IllegalArgumentException("All probabilites must be less than or equal to 1; the maximum supplied probabilty is " + var4);
        } else {
            double var6 = var3.getMinimum_as_double();
            if (var6 < 0.0D) {
                throw new IllegalArgumentException("All probabilites must be greater than or equal to 0; the minimum supplied probabilty is " + var6);
            } else {
                double var8 = var3.getSum_as_double();
                if (!Fmath.isEqualWithinPerCent(var8, 1.0D, 0.1D)) {
                    throw new IllegalArgumentException("the probabilites must add up to 1 within an error of 0.1%; they add up to " + var8);
                } else if (var1 < 0.0D) {
                    throw new IllegalArgumentException("alpha, " + var1 + ", must be greater than or equal to 0");
                } else {
                    double var10 = 0.0D;
                    if (var1 == 0.0D) {
                        var10 = Math.log10((double)var0.length);
                    } else if (var1 == 1.0D) {
                        var10 = shannonEntropy(var0);
                    } else if (Fmath.isPlusInfinity(var1)) {
                        var10 = -Math.log10(var4);
                    } else if (var1 <= 3000.0D) {
                        var3 = var3.pow(var1);
                        boolean var12 = false;
                        if (var3.getMaximum_as_double() == 4.9E-324D) {
                            var12 = true;
                        }

                        var10 = Math.log10(var3.getSum_as_double()) / (1.0D - var1);
                        if (Fmath.isPlusInfinity(var10) || var12) {
                            var10 = -Math.log10(var4);
                            double var13 = var10;
                            System.out.println("Stat: renyiEntropyDit: underflow or overflow in calculating the entropy");
                            boolean var15 = true;
                            boolean var16 = true;
                            boolean var17 = true;
                            int var18 = 0;
                            double var19 = var1 / 2.0D;
                            double var21 = 0.0D;

                            double var34;
                            while(var17) {
                                while(var15) {
                                    ArrayMaths var23 = new ArrayMaths(var0);
                                    var23 = var23.pow(var19);
                                    var21 = Math.log10(var23.getSum_as_double()) / (1.0D - var19);
                                    if (Fmath.isPlusInfinity(var21)) {
                                        var19 /= 2.0D;
                                        ++var18;
                                        if (var18 == 100000) {
                                            var15 = false;
                                            var16 = false;
                                        }
                                    } else {
                                        var15 = false;
                                    }
                                }

                                var34 = var19 + 40.0D * var1 / 1000.0D;
                                ArrayMaths var25 = new ArrayMaths(var0);
                                var25 = var25.pow(var34);
                                double var26 = Math.log10(var25.getSum_as_double()) / (1.0D - var34);
                                if (!Fmath.isPlusInfinity(var26)) {
                                    var17 = false;
                                } else {
                                    var19 /= 2.0D;
                                }
                            }

                            ArrayList var27 = new ArrayList();
                            if (var16) {
                                double var28 = var19 / 1000.0D;
                                var15 = true;

                                while(var15) {
                                    var27.add(new Double(var19));
                                    var27.add(new Double(var21));
                                    var34 = var21;
                                    double var35 = var19;
                                    var19 += var28;
                                    ArrayMaths var30 = new ArrayMaths(var0);
                                    var30 = var30.pow(var19);
                                    var21 = Math.log10(var30.getSum_as_double()) / (1.0D - var19);
                                    if (Fmath.isPlusInfinity(var21)) {
                                        var15 = false;
                                        var21 = var34;
                                        var19 = var35;
                                    }
                                }
                            }

                            int var36 = var27.size() / 2 - 20;
                            double[] var29 = new double[var36];
                            double[] var37 = new double[var36];
                            int var31 = -1;

                            for(int var32 = 0; var32 < var36; ++var32) {
                                ++var31;
                                var29[var32] = (Double)var27.get(var31);
                                ++var31;
                                var37[var32] = Math.log10((Double)var27.get(var31) - var13);
                            }

                            Regression var38 = new Regression(var29, var37);
                            var38.linear();
                            double[] var33 = var38.getCoeff();
                            var10 = Math.exp(var33[0] + var33[1] * var1) + var13;
                            System.out.println("An interpolated entropy of " + var10 + " returned (see documentation for exponential interpolation)");
                            System.out.println("Lowest calculable value =  " + (Math.exp(var37[var36 - 1]) + var13) + ", alpha = " + var29[var36 - 1]);
                            System.out.println("Minimum entropy value =  " + var13 + ", alpha = infinity");
                        }
                    } else {
                        var10 = -Math.log10(var4);
                        System.out.println("Stat: renyiEntropyDit: underflow or overflow in calculating the entropy");
                        System.out.println("An interpolated entropy of " + var10 + " returned (see documentation for exponential interpolation)");
                    }

                    return var10;
                }
            }
        }
    }

    public static double renyiEntropyBit(double[] var0, double var1) {
        return renyiEntropy(var0, var1);
    }

    public static double tsallisEntropyNat(double[] var0, double var1) {
        ArrayMaths var3 = new ArrayMaths(var0);
        double var4 = var3.getMaximum_as_double();
        if (var4 > 1.0D) {
            throw new IllegalArgumentException("All probabilites must be less than or equal to 1; the maximum supplied probabilty is " + var4);
        } else {
            double var6 = var3.getMinimum_as_double();
            if (var6 < 0.0D) {
                throw new IllegalArgumentException("All probabilites must be greater than or equal to 0; the minimum supplied probabilty is " + var6);
            } else {
                double var8 = var3.getSum_as_double();
                if (!Fmath.isEqualWithinPerCent(var8, 1.0D, 0.1D)) {
                    throw new IllegalArgumentException("the probabilites must add up to 1 within an error of 0.1%; they add up to " + var8);
                } else if (var1 == 1.0D) {
                    return shannonEntropyNat(var0);
                } else {
                    var3 = var3.pow(var1);
                    return (1.0D - var3.getSum_as_double()) / (var1 - 1.0D);
                }
            }
        }
    }

    public static double generalizedEntropyOneNat(double[] var0, double var1, double var3) {
        ArrayMaths var5 = new ArrayMaths(var0);
        double var6 = var5.getMaximum_as_double();
        if (var6 > 1.0D) {
            throw new IllegalArgumentException("All probabilites must be less than or equal to 1; the maximum supplied probabilty is " + var6);
        } else {
            double var8 = var5.getMinimum_as_double();
            if (var8 < 0.0D) {
                throw new IllegalArgumentException("All probabilites must be greater than or equal to 0; the minimum supplied probabilty is " + var8);
            } else {
                double var10 = var5.getSum_as_double();
                if (!Fmath.isEqualWithinPerCent(var10, 1.0D, 0.1D)) {
                    throw new IllegalArgumentException("the probabilites must add up to 1 within an error of 0.1%; they add up to " + var10);
                } else if (var3 == 0.0D) {
                    return renyiEntropyNat(var0, var1);
                } else if (var3 == 1.0D) {
                    return tsallisEntropyNat(var0, var1);
                } else if (var1 != 1.0D) {
                    var5 = var5.pow(var1);
                    return (1.0D - Math.pow(var5.getSum_as_double(), var3)) / (var3 * (var1 - 1.0D));
                } else {
                    double[] var12 = new double[10];
                    double[] var13 = new double[10];
                    double var14 = 0.995D;

                    int var16;
                    ArrayMaths var17;
                    for(var16 = 0; var16 < 5; ++var16) {
                        var17 = var5.pow(var14);
                        var12[var16] = (1.0D - Math.pow(var17.getSum_as_double(), var3)) / (var3 * (var14 - 1.0D));
                        var13[var16] = var14;
                        var14 += 0.001D;
                    }

                    var14 = 1.001D;

                    for(var16 = 5; var16 < 10; ++var16) {
                        var17 = var5.pow(var14);
                        var12[var16] = (1.0D - Math.pow(var17.getSum_as_double(), var3)) / (var3 * (var14 - 1.0D));
                        var13[var16] = var14;
                        var14 += 0.001D;
                    }

                    Regression var18 = new Regression(var13, var12);
                    var18.polynomial(2);
                    double[] var19 = var18.getCoeff();
                    return var19[0] + var19[1] + var19[2];
                }
            }
        }
    }

    public static double generalisedEntropyOneNat(double[] var0, double var1, double var3) {
        return generalizedEntropyOneNat(var0, var1, var3);
    }

    public static double[][] histogramBins(double[] var0, double var1, double var3, double var5) {
        int var7 = 0;
        int var8 = var0.length;

        for(int var9 = 0; var9 < var8; ++var9) {
            if (var0[var9] <= var5) {
                ++var7;
            }
        }

        if (var7 != var8) {
            double[] var12 = new double[var7];
            int var10 = 0;

            for(int var11 = 0; var11 < var8; ++var11) {
                if (var0[var11] <= var5) {
                    var12[var10] = var0[var11];
                    ++var10;
                }
            }

            System.out.println(var8 - var7 + " data points, above histogram upper limit, excluded in Stat.histogramBins");
            return histogramBins(var12, var1, var3);
        } else {
            return histogramBins(var0, var1, var3);
        }
    }

    public static double[][] histogramBins(double[] var0, double var1, double var3) {
        double var5 = Fmath.maximum(var0);
        int var7 = (int)Math.ceil((var5 - var3) / var1);
        if (var3 + (double)var7 * var1 > var5) {
            ++var7;
        }

        int var8 = var0.length;
        int[] var9 = new int[var8];

        for(int var10 = 0; var10 < var8; ++var10) {
            var9[var10] = 0;
        }

        double[] var15 = new double[var7 + 1];
        var15[0] = var3;

        for(int var11 = 1; var11 <= var7; ++var11) {
            var15[var11] = var15[var11 - 1] + var1;
        }

        double[][] var16 = new double[2][var7];

        for(int var12 = 0; var12 < var7; ++var12) {
            var16[0][var12] = (var15[var12] + var15[var12 + 1]) / 2.0D;
            var16[1][var12] = 0.0D;
        }

        boolean var17 = true;

        int var13;
        int var14;
        for(var13 = 0; var13 < var8; ++var13) {
            var17 = true;
            var14 = 0;

            while(var17) {
                if (var14 == var7 - 1) {
                    if (var0[var13] >= var15[var14] && var0[var13] <= var15[var14 + 1] * (1.0D + histTol)) {
                        ++var16[1][var14];
                        var9[var13] = 1;
                        var17 = false;
                    }
                } else if (var0[var13] >= var15[var14] && var0[var13] < var15[var14 + 1]) {
                    ++var16[1][var14];
                    var9[var13] = 1;
                    var17 = false;
                }

                if (var17) {
                    if (var14 == var7 - 1) {
                        var17 = false;
                    } else {
                        ++var14;
                    }
                }
            }
        }

        var13 = 0;

        for(var14 = 0; var14 < var8; ++var14) {
            if (var9[var14] == 0) {
                ++var13;
                System.out.println("p " + var14 + " " + var0[var14] + " " + var15[0] + " " + var15[var7]);
            }
        }

        if (var13 > 0) {
            System.out.println(var13 + " data points, outside histogram limits, excluded in Stat.histogramBins");
        }

        return var16;
    }

    public static double[][] histogramBins(double[] var0, double var1) {
        double var3 = Fmath.minimum(var0);
        double var5 = Fmath.maximum(var0);
        double var7 = var5 - var3;
        double var9 = var3;
        int var11 = (int)Math.ceil(var7 / var1);
        double var12 = (double)var11 * var1;
        double var14 = var12 - var7;
        if (var14 >= 0.0D) {
            var9 = var3 - var14 / 2.0D;
        } else if (Math.abs(var14) / var7 > histTol) {
            boolean var16 = true;
            double var17 = histTol / (double)var11;
            int var19 = 0;

            while(var16) {
                var1 += var17;
                var12 = (double)var11 * var1;
                var14 = var12 - var7;
                if (var14 < 0.0D) {
                    ++var19;
                    if (var19 > 1000) {
                        var16 = false;
                        System.out.println("histogram method could not encompass all data within histogram\nContact Michael thomas Flanagan");
                    }
                } else {
                    var16 = false;
                }
            }
        }

        return histogramBins(var0, var1, var9);
    }

    public static double[][] histogramBinsPlot(double[] var0, double var1, double var3, double var5) {
        Object var7 = null;
        return histogramBinsPlot(var0, var1, var3, var5, (String)var7);
    }

    public static double[][] histogramBinsPlot(double[] var0, double var1, double var3, double var5, String var7) {
        int var8 = 0;
        int var9 = var0.length;

        for(int var10 = 0; var10 < var9; ++var10) {
            if (var0[var10] <= var5) {
                ++var8;
            }
        }

        if (var8 != var9) {
            double[] var13 = new double[var8];
            int var11 = 0;

            for(int var12 = 0; var12 < var9; ++var12) {
                if (var0[var12] <= var5) {
                    var13[var11] = var0[var12];
                    ++var11;
                }
            }

            System.out.println(var9 - var8 + " data points, above histogram upper limit, excluded in Stat.histogramBins");
            return histogramBinsPlot(var13, var1, var3, var7);
        } else {
            return histogramBinsPlot(var0, var1, var3, var7);
        }
    }

    public static double[][] histogramBinsPlot(double[] var0, double var1, double var3) {
        Object var5 = null;
        return histogramBinsPlot(var0, var1, var3, (String)var5);
    }

    public static double[][] histogramBinsPlot(double[] var0, double var1, double var3, String var5) {
        double[][] var6 = histogramBins(var0, var1, var3);
        int var7 = var6[0].length;
        int var8 = var7 * 3 + 1;
        double[][] var9 = PlotGraph.fillData(1, var8);
        var9[0][0] = var3;
        var9[1][0] = 0.0D;
        int var10 = 1;

        for(int var11 = 0; var11 < var7; ++var11) {
            var9[0][var10] = var9[0][var10 - 1];
            var9[1][var10] = var6[1][var11];
            ++var10;
            var9[0][var10] = var9[0][var10 - 1] + var1;
            var9[1][var10] = var6[1][var11];
            ++var10;
            var9[0][var10] = var9[0][var10 - 1];
            var9[1][var10] = 0.0D;
            ++var10;
        }

        PlotGraph var12 = new PlotGraph(var9);
        var12.setGraphTitle("Histogram:  Bin Width = " + var1);
        var12.setLine(3);
        var12.setPoint(0);
        var12.setYaxisLegend("Frequency");
        if (var5 != null) {
            var12.setXaxisLegend(var5);
        }

        var12.setFrame();
        return var6;
    }

    public static double[][] histogramBinsPlot(double[] var0, double var1) {
        Object var3 = null;
        return histogramBinsPlot(var0, var1, (String)var3);
    }

    public static double[][] histogramBinsPlot(double[] var0, double var1, String var3) {
        double var4 = Fmath.minimum(var0);
        double var6 = Fmath.maximum(var0);
        double var8 = var6 - var4;
        int var10 = (int)Math.ceil(var8 / var1);
        double var11 = (double)var10 * var1 - var8;
        double var13 = var4 - var11 / 2.0D;
        return histogramBinsPlot(var0, var1, var13, var3);
    }

    public static double[] uniformOrderStatisticMedians(int var0) {
        double var1 = (double)var0;
        double[] var3 = new double[var0];
        var3[var0 - 1] = Math.pow(0.5D, 1.0D / var1);
        var3[0] = 1.0D - var3[var0 - 1];

        for(int var4 = 1; var4 < var0 - 1; ++var4) {
            var3[var4] = ((double)(var4 + 1) - 0.3175D) / (var1 + 0.365D);
        }

        return var3;
    }

    public static double gammaCDF(double var0, double var2, double var4, double var6) {
        if (var6 < var0) {
            throw new IllegalArgumentException("The upper limit, " + var6 + "must be equal to or greater than the location parameter, " + var0);
        } else if (var2 <= 0.0D) {
            throw new IllegalArgumentException("The scale parameter, " + var2 + "must be greater than zero");
        } else if (var4 <= 0.0D) {
            throw new IllegalArgumentException("The shape parameter, " + var4 + "must be greater than zero");
        } else {
            double var8 = (var6 - var0) / var2;
            return regularisedGammaFunction(var4, var8);
        }
    }

    public static double gammaCDF(double var0, double var2) {
        if (var2 < 0.0D) {
            throw new IllegalArgumentException("The upper limit, " + var2 + "must be equal to or greater than zero");
        } else if (var0 <= 0.0D) {
            throw new IllegalArgumentException("The shape parameter, " + var0 + "must be greater than zero");
        } else {
            return regularisedGammaFunction(var0, var2);
        }
    }

    public static double gammaPDF(double var0, double var2, double var4, double var6) {
        if (var6 < var0) {
            throw new IllegalArgumentException("The variable, x, " + var6 + "must be equal to or greater than the location parameter, " + var0);
        } else if (var2 <= 0.0D) {
            throw new IllegalArgumentException("The scale parameter, " + var2 + "must be greater than zero");
        } else if (var4 <= 0.0D) {
            throw new IllegalArgumentException("The shape parameter, " + var4 + "must be greater than zero");
        } else {
            double var8 = (var6 - var0) / var2;
            return Math.pow(var8, var4 - 1.0D) * Math.exp(-var8) / (var2 * gammaFunction(var4));
        }
    }

    public static double gammaPDF(double var0, double var2) {
        if (var2 < 0.0D) {
            throw new IllegalArgumentException("The variable, x, " + var2 + "must be equal to or greater than zero");
        } else if (var0 <= 0.0D) {
            throw new IllegalArgumentException("The shape parameter, " + var0 + "must be greater than zero");
        } else {
            return Math.pow(var2, var0 - 1.0D) * Math.exp(-var2) / gammaFunction(var0);
        }
    }

    public static double gammaMean(double var0, double var2, double var4) {
        if (var2 <= 0.0D) {
            throw new IllegalArgumentException("The scale parameter, " + var2 + "must be greater than zero");
        } else if (var4 <= 0.0D) {
            throw new IllegalArgumentException("The shape parameter, " + var4 + "must be greater than zero");
        } else {
            return var4 * var2 - var0;
        }
    }

    public static double gammaMode(double var0, double var2, double var4) {
        if (var2 <= 0.0D) {
            throw new IllegalArgumentException("The scale parameter, " + var2 + "must be greater than zero");
        } else if (var4 <= 0.0D) {
            throw new IllegalArgumentException("The shape parameter, " + var4 + "must be greater than zero");
        } else {
            double var6 = 0.0D / 0.0;
            if (var4 >= 1.0D) {
                var6 = (var4 - 1.0D) * var2 - var0;
            }

            return var6;
        }
    }

    public static double gammaStandardDeviation(double var0, double var2, double var4) {
        return gammaStandDev(var0, var2, var4);
    }

    public static double gammaStandDev(double var0, double var2, double var4) {
        if (var2 <= 0.0D) {
            throw new IllegalArgumentException("The scale parameter, " + var2 + "must be greater than zero");
        } else if (var4 <= 0.0D) {
            throw new IllegalArgumentException("The shape parameter, " + var4 + "must be greater than zero");
        } else {
            return Math.sqrt(var4) * var2;
        }
    }

    public static double[] gammaRand(double var0, double var2, double var4, int var6) {
        if (var2 <= 0.0D) {
            throw new IllegalArgumentException("The scale parameter, " + var2 + "must be greater than zero");
        } else if (var4 <= 0.0D) {
            throw new IllegalArgumentException("The shape parameter, " + var4 + "must be greater than zero");
        } else {
            PsRandom var7 = new PsRandom();
            return var7.gammaArray(var0, var2, var4, var6);
        }
    }

    public static double[] gammaRand(double var0, double var2, double var4, int var6, long var7) {
        if (var2 <= 0.0D) {
            throw new IllegalArgumentException("The scale parameter, " + var2 + "must be greater than zero");
        } else if (var4 <= 0.0D) {
            throw new IllegalArgumentException("The shape parameter, " + var4 + "must be greater than zero");
        } else {
            PsRandom var9 = new PsRandom(var7);
            return var9.gammaArray(var0, var2, var4, var6);
        }
    }

    public static double gammaFunction(double var0) {
        double var2 = var0;
        double var4 = var0 + lgfGamma + 0.5D;
        double var6 = lgfCoeff[0];
        double var8 = 0.0D;
        if (var0 >= 0.0D) {
            var4 = Math.pow(var4, var0 + 0.5D) * Math.exp(-var4);

            for(int var10 = 1; var10 <= lgfN; ++var10) {
                var6 += lgfCoeff[var10] / ++var2;
            }

            var8 = var4 * Math.sqrt(6.283185307179586D) * var6 / var0;
        } else {
            var8 = -3.141592653589793D / (var0 * gamma(-var0) * Math.sin(3.141592653589793D * var0));
        }

        return var8;
    }

    public static double gamma(double var0) {
        double var2 = var0;
        double var4 = var0 + lgfGamma + 0.5D;
        double var6 = lgfCoeff[0];
        double var8 = 0.0D;
        if (var0 >= 0.0D) {
            var4 = Math.pow(var4, var0 + 0.5D) * Math.exp(-var4);

            for(int var10 = 1; var10 <= lgfN; ++var10) {
                var6 += lgfCoeff[var10] / ++var2;
            }

            var8 = var4 * Math.sqrt(6.283185307179586D) * var6 / var0;
        } else {
            var8 = -3.141592653589793D / (var0 * gamma(-var0) * Math.sin(3.141592653589793D * var0));
        }

        return var8;
    }

    public static double getLanczosGamma() {
        return lgfGamma;
    }

    public static int getLanczosN() {
        return lgfN;
    }

    public static double[] getLanczosCoeff() {
        int var0 = getLanczosN() + 1;
        double[] var1 = new double[var0];

        for(int var2 = 0; var2 < var0; ++var2) {
            var1[var2] = lgfCoeff[var2];
        }

        return var1;
    }

    public static double getFpmin() {
        return 1.0E-300D;
    }

    public static double logGammaFunction(double var0) {
        double var2 = var0;
        double var4 = 0.0D;
        double var6 = var0 + lgfGamma + 0.5D;
        double var8 = lgfCoeff[0];
        if (var0 >= 0.0D) {
            var6 -= (var0 + 0.5D) * Math.log(var6);

            for(int var10 = 1; var10 <= lgfN; ++var10) {
                var8 += lgfCoeff[var10] / ++var2;
            }

            var4 = Math.log(Math.sqrt(6.283185307179586D) * var8 / var0) - var6;
        } else {
            var4 = 3.141592653589793D / (gamma(1.0D - var0) * Math.sin(3.141592653589793D * var0));
            if (var4 != 1.0D / 0.0 && var4 != -1.0D / 0.0) {
                if (var4 < 0.0D) {
                    throw new IllegalArgumentException("\nThe gamma function is negative");
                }

                var4 = Math.log(var4);
            }
        }

        return var4;
    }

    public static double logGamma(double var0) {
        double var2 = var0;
        double var4 = 0.0D;
        double var6 = var0 + lgfGamma + 0.5D;
        double var8 = lgfCoeff[0];
        if (var0 >= 0.0D) {
            var6 -= (var0 + 0.5D) * Math.log(var6);

            for(int var10 = 1; var10 <= lgfN; ++var10) {
                var8 += lgfCoeff[var10] / ++var2;
            }

            var4 = Math.log(Math.sqrt(6.283185307179586D) * var8 / var0) - var6;
        } else {
            var4 = 3.141592653589793D / (gamma(1.0D - var0) * Math.sin(3.141592653589793D * var0));
            if (var4 != 1.0D / 0.0 && var4 != -1.0D / 0.0) {
                if (var4 < 0.0D) {
                    throw new IllegalArgumentException("\nThe gamma function is negative");
                }

                var4 = Math.log(var4);
            }
        }

        return var4;
    }

    public static double[] inverseGammaFunction(double var0) {
        double var2 = 0.8856031944108839D;
        double var4 = 1.4616321399961483D;
        if (var0 < var2) {
            throw new IllegalArgumentException("Entered argument (gamma) value, " + var0 + ", must be equal to or greater than 0.8856031944108839 - this method does not handle the negative domain");
        } else {
            double[] var6 = new double[2];
            double var7 = 1.0E-12D;
            InverseGammaFunct var9;
            double var10;
            if (var0 == 1.0D) {
                var6[0] = 1.0D;
            } else if (var0 == var2) {
                var6[0] = var4;
            } else {
                var9 = new InverseGammaFunct();
                var9.gamma = var0;
                var10 = 0.0D;
                RealRoot var14 = new RealRoot();
                var14.noBoundsExtensions();
                var14.setTolerance(var7);
                var14.resetNaNexceptionToTrue();
                var14.suppressLimitReachedMessage();
                var14.suppressNaNmessage();
                var6[0] = var14.bisect(var9, var10, var4);
            }

            if (var0 == 1.0D) {
                var6[1] = 2.0D;
            } else if (var0 == var2) {
                var6[1] = var4;
            } else {
                var9 = new InverseGammaFunct();
                var9.gamma = var0;
                var10 = var4;
                double var12 = 2.0D;
                double var19 = 2.0D;
                double var16 = gamma(var19);
                if (var0 > var16) {
                    boolean var18 = true;

                    while(var18) {
                        ++var19;
                        var16 = gamma(var19);
                        if (var0 <= var16) {
                            var12 = var19;
                            var10 = var19 - 1.0D;
                            var18 = false;
                        }
                    }
                }

                RealRoot var20 = new RealRoot();
                var20.noBoundsExtensions();
                var20.setTolerance(var7);
                var20.resetNaNexceptionToTrue();
                var20.suppressLimitReachedMessage();
                var20.suppressNaNmessage();
                var6[1] = var20.bisect(var9, var10, var12);
            }

            return var6;
        }
    }

    public static double[] gammaFunctionMinimum() {
        double[] var0 = new double[]{0.8856031944108839D, 1.4616321399961483D};
        return var0;
    }

    public static double regularisedGammaFunction(double var0, double var2) {
        if (var0 >= 0.0D && var2 >= 0.0D) {
            boolean var4 = igSupress;
            igSupress = true;
            double var5 = 0.0D;
            if (var2 != 0.0D) {
                if (var2 < var0 + 1.0D) {
                    var5 = incompleteGammaSer(var0, var2);
                } else {
                    var5 = incompleteGammaFract(var0, var2);
                }

                if (var5 != var5) {
                    var5 = 1.0D - crigfGaussQuad(var0, var2);
                }
            }

            if (var5 < 0.0D) {
                var5 = 0.0D;
            }

            igSupress = var4;
            return var5;
        } else {
            throw new IllegalArgumentException("\nFunction defined only for a >= 0 and x>=0");
        }
    }

    public static double regularizedGammaFunction(double var0, double var2) {
        return regularisedGammaFunction(var0, var2);
    }

    public static double regIncompleteGamma(double var0, double var2) {
        return regularisedGammaFunction(var0, var2);
    }

    public static double incompleteGamma(double var0, double var2) {
        return regularisedGammaFunction(var0, var2);
    }

    public static double complementaryRegularisedGammaFunction(double var0, double var2) {
        if (var0 >= 0.0D && var2 >= 0.0D) {
            boolean var4 = igSupress;
            igSupress = true;
            double var5 = 1.0D;
            if (var2 != 0.0D) {
                if (var2 == 1.0D / 0.0) {
                    var5 = 0.0D;
                } else if (var2 < var0 + 1.0D) {
                    var5 = 1.0D - incompleteGammaSer(var0, var2);
                } else {
                    var5 = 1.0D - incompleteGammaFract(var0, var2);
                }
            }

            if (var5 > 1.0D) {
                var5 = 1.0D;
            }

            igSupress = var4;
            return var5;
        } else {
            throw new IllegalArgumentException("\nFunction defined only for a >= 0 and x>=0");
        }
    }

    public static double complementaryRegularizedGammaFunction(double var0, double var2) {
        return complementaryRegularisedGammaFunction(var0, var2);
    }

    public static double incompleteGammaComplementary(double var0, double var2) {
        return complementaryRegularisedGammaFunction(var0, var2);
    }

    public static double regIncompleteGammaComplementary(double var0, double var2) {
        return complementaryRegularisedGammaFunction(var0, var2);
    }

    public static double incompleteGammaSer(double var0, double var2) {
        if (var0 >= 0.0D && var2 >= 0.0D) {
            if (var2 >= var0 + 1.0D) {
                throw new IllegalArgumentException("\nx >= a+1   use Continued Fraction Representation");
            } else {
                double var4 = 0.0D;
                if (var2 != 0.0D) {
                    int var6 = 0;
                    boolean var7 = true;
                    double var8 = var0;
                    double var10 = 1.0D / var0;
                    double var12 = var10;
                    double var14 = logGamma(var0);

                    while(var7) {
                        ++var6;
                        ++var0;
                        var12 *= var2 / var0;
                        var10 += var12;
                        if (Math.abs(var12) < Math.abs(var10) * igfeps) {
                            var4 = var10 * Math.exp(-var2 + var8 * Math.log(var2) - var14);
                            var7 = false;
                        }

                        if (var6 >= igfiter) {
                            var7 = false;
                            var4 = 0.0D / 0.0;
                            if (!igSupress) {
                                System.out.println("\nMaximum number of iterations were exceeded in Stat.incompleteGammaSer().");
                                System.out.println("NaN returned.\nIncrement = " + String.valueOf(var12) + ".");
                                System.out.println("Sum = " + String.valueOf(var10) + ".\nTolerance =  " + igfeps);
                            }
                        }
                    }
                }

                return var4;
            }
        } else {
            throw new IllegalArgumentException("\nFunction defined only for a >= 0 and x>=0");
        }
    }

    public static double incompleteGammaFract(double var0, double var2) {
        if (var0 >= 0.0D && var2 >= 0.0D) {
            if (var2 < var0 + 1.0D) {
                throw new IllegalArgumentException("\nx < a+1   Use Series Representation");
            } else {
                double var4 = 0.0D;
                if (var2 != 0.0D) {
                    int var6 = 0;
                    double var7 = 0.0D;
                    boolean var9 = true;
                    double var10 = logGamma(var0);
                    double var12 = 0.0D;
                    double var14 = 0.0D;
                    double var16 = var2 - var0 + 1.0D;
                    double var18 = 1.0D / var16;
                    double var20 = 9.999999999999999E299D;
                    double var22 = var18;

                    while(var9) {
                        ++var6;
                        var7 = (double)var6;
                        var12 = -var7 * (var7 - var0);
                        var16 += 2.0D;
                        var18 = var12 * var18 + var16;
                        if (Math.abs(var18) < 1.0E-300D) {
                            var18 = 1.0E-300D;
                        }

                        var20 = var16 + var12 / var20;
                        if (Math.abs(var20) < 1.0E-300D) {
                            var20 = 1.0E-300D;
                        }

                        var18 = 1.0D / var18;
                        var14 = var18 * var20;
                        var22 *= var14;
                        if (Math.abs(var14 - 1.0D) < igfeps) {
                            var9 = false;
                        }

                        if (var6 >= igfiter) {
                            var9 = false;
                            var4 = 0.0D / 0.0;
                            if (!igSupress) {
                                System.out.println("\nMaximum number of iterations were exceeded in Stat.incompleteGammaFract().");
                                System.out.println("NaN returned.\nIncrement - 1 = " + String.valueOf(var14 - 1.0D) + ".");
                                System.out.println("Tolerance =  " + String.valueOf(igfeps));
                            }
                        }
                    }

                    var4 = 1.0D - Math.exp(-var2 + var0 * Math.log(var2) - var10) * var22;
                }

                return var4;
            }
        } else {
            throw new IllegalArgumentException("\nFunction defined only for a >= 0 and x>=0");
        }
    }

    private static double crigfGaussQuad(double var0, double var2) {
        double var4 = 0.0D;
        double var6 = 100.0D * var0;
        double var8 = var6 - var2;
        double var10 = 0.0D;
        if (var6 > var2 && var8 > 100.0D) {
            var10 = var8 / 1000.0D;
        } else {
            var6 = var2 + 100.0D;
            var8 = 100.0D;
            var10 = 0.1D;
        }

        int var12 = (int)Math.round(var8 / var10);
        var10 = var8 / (double)var12;
        CrigFunct var13 = new CrigFunct();
        var13.setA(var0);
        var13.setB(logGammaFunction(var0));
        Integration var14 = new Integration(var13);
        double var17 = var2 + var10;
        var14.setLimits(var2, var17);
        var4 = var14.gaussQuad(64);
        boolean var19 = true;

        for(int var20 = 1; var20 < var12; ++var20) {
            double var15 = var17;
            var17 += var10;
            var14.setLimits(var15, var17);
            var4 += var14.gaussQuad(64);
        }

        return var4;
    }

    public static void igSupress() {
        igSupress = true;
    }

    public static void setIncGammaMaxIter(int var0) {
        igfiter = var0;
    }

    public static int getIncGammaMaxIter() {
        return igfiter;
    }

    public static void setIncGammaTol(double var0) {
        igfeps = var0;
    }

    public static double getIncGammaTol() {
        return igfeps;
    }

    public static int factorial(int var0) {
        if (var0 < 0) {
            throw new IllegalArgumentException("n must be a positive integer");
        } else if (var0 > 12) {
            throw new IllegalArgumentException("n must less than 13 to avoid integer overflow\nTry long or double argument");
        } else {
            int var1 = 1;

            for(int var2 = 2; var2 <= var0; ++var2) {
                var1 *= var2;
            }

            return var1;
        }
    }

    public static long factorial(long var0) {
        if (var0 < 0L) {
            throw new IllegalArgumentException("n must be a positive integer");
        } else if (var0 > 20L) {
            throw new IllegalArgumentException("n must less than 21 to avoid long integer overflow\nTry double argument");
        } else {
            long var2 = 1L;

            for(long var4 = 2L; var4 <= var0; ++var4) {
                var2 *= var4;
            }

            return var2;
        }
    }

    public static BigInteger factorial(BigInteger var0) {
        if (var0.compareTo(BigInteger.ZERO) == -1) {
            throw new IllegalArgumentException("\nn must be a positive integer\nIs a Gamma funtion [Fmath.gamma(x)] more appropriate?");
        } else {
            BigInteger var1 = BigInteger.ONE;
            BigInteger var2 = var1;

            BigInteger var3;
            for(var3 = new BigInteger("2"); var3.compareTo(var0) != 1; var3 = var3.add(var1)) {
                var2 = var2.multiply(var3);
            }

            var1 = null;
            var3 = null;
            return var2;
        }
    }

    public static double factorial(double var0) {
        if (var0 >= 0.0D && var0 - Math.floor(var0) == 0.0D) {
            double var2 = 1.0D;

            for(double var4 = 2.0D; var4 <= var0; ++var4) {
                var2 *= var4;
            }

            return var2;
        } else {
            throw new IllegalArgumentException("\nn must be a positive integer\nIs a Gamma funtion [Fmath.gamma(x)] more appropriate?");
        }
    }

    public static BigDecimal factorial(BigDecimal var0) {
        if (var0.compareTo(BigDecimal.ZERO) != -1 && Fmath.isInteger(var0)) {
            BigDecimal var1 = BigDecimal.ONE;
            BigDecimal var2 = var1;

            BigDecimal var3;
            for(var3 = new BigDecimal(2.0D); var3.compareTo(var0) != 1; var3 = var3.add(var1)) {
                var2 = var2.multiply(var3);
            }

            var1 = null;
            var3 = null;
            return var2;
        } else {
            throw new IllegalArgumentException("\nn must be a positive integer\nIs a Gamma funtion [Fmath.gamma(x)] more appropriate?");
        }
    }

    public static double logFactorial(int var0) {
        if (var0 < 0) {
            throw new IllegalArgumentException("\nn, " + var0 + ", must be a positive integer\nIs a Gamma funtion [Fmath.gamma(x)] more appropriate?");
        } else {
            double var1 = 0.0D;

            for(int var3 = 2; var3 <= var0; ++var3) {
                var1 += Math.log((double)var3);
            }

            return var1;
        }
    }

    public static double logFactorial(long var0) {
        if (var0 < 0L) {
            throw new IllegalArgumentException("\nn, " + var0 + ", must be a positive integer\nIs a Gamma funtion [Fmath.gamma(x)] more appropriate?");
        } else {
            double var2 = 0.0D;

            for(long var4 = 2L; var4 <= var0; ++var4) {
                var2 += Math.log((double)var4);
            }

            return var2;
        }
    }

    public static double logFactorial(double var0) {
        if (var0 >= 0.0D && var0 - Math.floor(var0) == 0.0D) {
            double var2 = 0.0D;

            for(double var4 = 2.0D; var4 <= var0; ++var4) {
                var2 += Math.log(var4);
            }

            return var2;
        } else {
            throw new IllegalArgumentException("\nn must be a positive integer\nIs a Gamma funtion [Fmath.gamma(x)] more appropriate?");
        }
    }

    public static double erlangCDF(double var0, int var2, double var3) {
        return gammaCDF(0.0D, 1.0D / var0, (double)var2, var3);
    }

    public static double erlangCDF(double var0, long var2, double var4) {
        return gammaCDF(0.0D, 1.0D / var0, (double)var2, var4);
    }

    public static double erlangCDF(double var0, double var2, double var4) {
        if (var2 - (double)Math.round(var2) != 0.0D) {
            throw new IllegalArgumentException("kay must, mathematically, be an integer even though it may be entered as a double\nTry the Gamma distribution instead of the Erlang distribution");
        } else {
            return gammaCDF(0.0D, 1.0D / var0, var2, var4);
        }
    }

    public static double erlangPDF(double var0, int var2, double var3) {
        return gammaPDF(0.0D, 1.0D / var0, (double)var2, var3);
    }

    public static double erlangPDF(double var0, long var2, double var4) {
        return gammaPDF(0.0D, 1.0D / var0, (double)var2, var4);
    }

    public static double erlangPDF(double var0, double var2, double var4) {
        if (var2 - (double)Math.round(var2) != 0.0D) {
            throw new IllegalArgumentException("kay must, mathematically, be an integer even though it may be entered as a double\nTry the Gamma distribution instead of the Erlang distribution");
        } else {
            return gammaPDF(0.0D, 1.0D / var0, var2, var4);
        }
    }

    public static double erlangMean(double var0, int var2) {
        if (var2 < 1) {
            throw new IllegalArgumentException("The rate parameter, " + var2 + "must be equal to or greater than one");
        } else {
            return (double)var2 / var0;
        }
    }

    public static double erlangMean(double var0, long var2) {
        if (var2 < 1L) {
            throw new IllegalArgumentException("The rate parameter, " + var2 + "must be equal to or greater than one");
        } else {
            return (double)var2 / var0;
        }
    }

    public static double erlangMean(double var0, double var2) {
        if (var2 - (double)Math.round(var2) != 0.0D) {
            throw new IllegalArgumentException("kay must, mathematically, be an integer even though it may be entered as a double\nTry the Gamma distribution instead of the Erlang distribution");
        } else if (var2 < 1.0D) {
            throw new IllegalArgumentException("The rate parameter, " + var2 + "must be equal to or greater than one");
        } else {
            return var2 / var0;
        }
    }

    public static double erlangMode(double var0, int var2) {
        if (var2 < 1) {
            throw new IllegalArgumentException("The rate parameter, " + var2 + "must be equal to or greater than one");
        } else {
            double var3 = 0.0D / 0.0;
            if (var2 >= 1) {
                var3 = ((double)var2 - 1.0D) / var0;
            }

            return var3;
        }
    }

    public static double erlangMode(double var0, long var2) {
        if (var2 < 1L) {
            throw new IllegalArgumentException("The rate parameter, " + var2 + "must be equal to or greater than one");
        } else {
            double var4 = 0.0D / 0.0;
            if (var2 >= 1L) {
                var4 = ((double)var2 - 1.0D) / var0;
            }

            return var4;
        }
    }

    public static double erlangMode(double var0, double var2) {
        if (var2 < 1.0D) {
            throw new IllegalArgumentException("The rate parameter, " + var2 + "must be equal to or greater than one");
        } else if (var2 - (double)Math.round(var2) != 0.0D) {
            throw new IllegalArgumentException("kay must, mathematically, be an integer even though it may be entered as a double\nTry the Gamma distribution instead of the Erlang distribution");
        } else {
            double var4 = 0.0D / 0.0;
            if (var2 >= 1.0D) {
                var4 = (var2 - 1.0D) / var0;
            }

            return var4;
        }
    }

    public static double erlangStandardDeviation(double var0, int var2) {
        return erlangStandDev(var0, var2);
    }

    public static double erlangStandardDeviation(double var0, long var2) {
        return erlangStandDev(var0, var2);
    }

    public static double erlangStandardDeviation(double var0, double var2) {
        return erlangStandDev(var0, var2);
    }

    public static double erlangStandDev(double var0, int var2) {
        if (var2 < 1) {
            throw new IllegalArgumentException("The rate parameter, " + var2 + "must be equal to or greater than one");
        } else {
            return Math.sqrt((double)var2) / var0;
        }
    }

    public static double erlangStandDev(double var0, long var2) {
        if (var2 < 1L) {
            throw new IllegalArgumentException("The rate parameter, " + var2 + "must be equal to or greater than one");
        } else {
            return Math.sqrt((double)var2) / var0;
        }
    }

    public static double erlangStandDev(double var0, double var2) {
        if (var2 < 1.0D) {
            throw new IllegalArgumentException("The rate parameter, " + var2 + "must be equal to or greater than one");
        } else if (var2 - (double)Math.round(var2) != 0.0D) {
            throw new IllegalArgumentException("kay must, mathematically, be an integer even though it may be entered as a double\nTry the Gamma distribution instead of the Erlang distribution");
        } else {
            return Math.sqrt(var2) / var0;
        }
    }

    public static double[] erlangRand(double var0, int var2, int var3) {
        if (var2 < 1) {
            throw new IllegalArgumentException("The rate parameter, " + var2 + "must be equal to or greater than one");
        } else {
            return gammaRand(0.0D, 1.0D / var0, (double)var2, var3);
        }
    }

    public static double[] erlangRand(double var0, long var2, int var4) {
        if (var2 < 1L) {
            throw new IllegalArgumentException("The rate parameter, " + var2 + "must be equal to or greater than one");
        } else {
            return gammaRand(0.0D, 1.0D / var0, (double)var2, var4);
        }
    }

    public static double[] erlangRand(double var0, double var2, int var4) {
        if (var2 < 1.0D) {
            throw new IllegalArgumentException("The rate parameter, " + var2 + "must be equal to or greater than one");
        } else if (var2 - (double)Math.round(var2) != 0.0D) {
            throw new IllegalArgumentException("kay must, mathematically, be an integer even though it may be entered as a double\nTry the Gamma distribution instead of the Erlang distribution");
        } else {
            return gammaRand(0.0D, 1.0D / var0, var2, var4);
        }
    }

    public static double[] erlangRand(double var0, int var2, int var3, long var4) {
        if (var2 < 1) {
            throw new IllegalArgumentException("The rate parameter, " + var2 + "must be equal to or greater than one");
        } else {
            return gammaRand(0.0D, 1.0D / var0, (double)var2, var3, var4);
        }
    }

    public static double[] erlangRand(double var0, long var2, int var4, long var5) {
        if (var2 < 1L) {
            throw new IllegalArgumentException("The rate parameter, " + var2 + "must be equal to or greater than one");
        } else {
            return gammaRand(0.0D, 1.0D / var0, (double)var2, var4, var5);
        }
    }

    public static double[] erlangRand(double var0, double var2, int var4, long var5) {
        if (var2 < 1.0D) {
            throw new IllegalArgumentException("The rate parameter, " + var2 + "must be equal to or greater than one");
        } else if (var2 - (double)Math.round(var2) != 0.0D) {
            throw new IllegalArgumentException("kay must, mathematically, be an integer even though it may be entered as a double\nTry the Gamma distribution instead of the Erlang distribution");
        } else {
            return gammaRand(0.0D, 1.0D / var0, var2, var4, var5);
        }
    }

    public static double erlangMprobability(double var0, double var2, double var4) {
        double var6 = 0.0D;
        if (var0 > 0.0D) {
            double var8 = var2 * Math.log(var4) - Fmath.logFactorial(var4);
            double var10 = 1.0D;
            double var12 = 1.0D;

            for(int var14 = 1; (double)var14 <= var2; ++var14) {
                var12 = var12 * var0 / (double)var14;
                var10 += var12;
            }

            var10 = Math.log(var10);
            var6 = var8 - var10;
            var6 = Math.exp(var6);
        }

        return var6;
    }

    public static double erlangMprobability(double var0, long var2, long var4) {
        return erlangMprobability(var0, (double)var2, (double)var4);
    }

    public static double erlangMprobability(double var0, int var2, int var3) {
        return erlangMprobability(var0, (double)var2, (double)var3);
    }

    public static double erlangBprobability(double var0, double var2) {
        if (var0 < 0.0D) {
            throw new IllegalArgumentException("Total traffic, " + var0 + ", must be greater than or equal to zero");
        } else if (var2 < 0.0D) {
            throw new IllegalArgumentException("Total resources, " + var2 + ", must be greater than or equal to zero");
        } else {
            double var4 = 0.0D;
            if (var2 == 0.0D) {
                var4 = 1.0D;
            } else if (var0 == 0.0D) {
                var4 = 0.0D;
            } else if (Fmath.isInteger(var2)) {
                double var6 = 1.0D;
                var4 = 1.0D;

                for(double var8 = 0.0D; var6 <= var2; ++var6) {
                    var8 = var4 * var0;
                    var4 = var8 / (var6 + var8);
                }
            } else {
                var4 = erlangBprobabilityNIR(var0, var2);
            }

            return var4;
        }
    }

    public static double erlangBprobability(double var0, long var2) {
        return erlangBprobability(var0, (double)var2);
    }

    public static double erlangBprobability(double var0, int var2) {
        return erlangBprobability(var0, (double)var2);
    }

    public static double erlangBprobabilityNIR(double var0, double var2) {
        double var4 = 0.0D;
        double var6 = var2 * Math.log(var0) - var0;
        double var8 = 1.0D + var2;
        double var10 = complementaryRegularisedGammaFunction(var8, var0);
        if (var10 == var10 && var10 != 0.0D) {
            double var18 = Math.log(var10) + logGammaFunction(var8);
            var4 = Math.exp(var6 - var18);
        } else {
            int var12 = (int)Math.floor(var2) - 2;
            if (var12 < 0) {
                var12 = 0;
            }

            byte var13 = 6;
            int[] var14 = new int[var13];
            double[] var15 = new double[var13];
            double[] var16 = new double[var13];

            for(int var17 = 0; var17 < var13; ++var17) {
                var14[var17] = var12 + var17;
                var15[var17] = (double)var14[var17];
                var16[var17] = erlangBprobability(var0, var14[var17]);
            }

            CubicSpline var19 = new CubicSpline(var15, var16);
            var4 = var19.interpolate(var2);
        }

        return var4;
    }

    public static double erlangBprobabilityNonIntRes(double var0, double var2) {
        return erlangBprobability(var0, var2);
    }

    public static double erlangBload(double var0, double var2) {
        ErlangBfunct var4 = new ErlangBfunct();
        var4.blockingProbability = var0;
        var4.totalResources = var2;
        double var5 = 0.0D;
        double var7 = 20.0D;
        double var9 = 1.0E-6D;
        RealRoot var11 = new RealRoot();
        var11.setTolerance(var9);
        var11.noLowerBoundExtension();
        var11.suppressLimitReachedMessage();
        double var12 = var11.bisect(var4, var5, var7);
        return var12;
    }

    public static double erlangBload(double var0, long var2) {
        return erlangBload(var0, (double)var2);
    }

    public static double erlangBload(double var0, int var2) {
        return erlangBload(var0, (double)var2);
    }

    public static double[] erlangBresources(double var0, double var2) {
        double[] var4 = new double[8];
        long var5 = 1L;
        double var7 = 0.0D / 0.0;
        double var9 = 0.0D / 0.0;
        boolean var11 = true;

        while(true) {
            while(var11) {
                var9 = erlangBprobability(var2, var5);
                if (var9 <= var0) {
                    var4[0] = (double)var5;
                    var4[1] = var9;
                    var4[2] = erlangBload(var0, var5);
                    var4[3] = (double)(var5 - 1L);
                    var4[4] = var7;
                    var4[5] = erlangBload(var0, var5 - 1L);
                    var4[6] = var0;
                    var4[7] = var2;
                    var11 = false;
                } else {
                    var7 = var9;
                    ++var5;
                    if (var5 == 2147483647L) {
                        System.out.println("Method erlangBresources: no solution found below 9223372036854775807resources");

                        for(int var12 = 0; var12 < 8; ++var12) {
                            var4[var12] = 0.0D / 0.0;
                        }

                        var11 = false;
                    }
                }
            }

            return var4;
        }
    }

    public static double erlangCprobability(double var0, double var2) {
        double var4 = 0.0D;
        if (var0 > 0.0D) {
            double var6 = erlangBprobability(var0, var2);
            var4 = 1.0D + (1.0D / var6 - 1.0D) * (var2 - var0) / var2;
            var4 = 1.0D / var4;
        }

        return var4;
    }

    public static double erlangCprobability(double var0, long var2) {
        return erlangCprobability(var0, (double)var2);
    }

    public static double erlangCprobability(double var0, int var2) {
        return erlangCprobability(var0, (double)var2);
    }

    public static double erlangCload(double var0, double var2) {
        ErlangCfunct var4 = new ErlangCfunct();
        var4.nonZeroDelayProbability = var0;
        var4.totalResources = var2;
        double var5 = 0.0D;
        double var7 = 10.0D;
        double var9 = 1.0E-6D;
        RealRoot var11 = new RealRoot();
        var11.setTolerance(var9);
        var11.suppressLimitReachedMessage();
        var11.noLowerBoundExtension();
        double var12 = var11.bisect(var4, var5, var7);
        return var12;
    }

    public static double erlangCload(double var0, long var2) {
        return erlangCload(var0, (double)var2);
    }

    public static double erlangCload(double var0, int var2) {
        return erlangCload(var0, (double)var2);
    }

    public static double[] erlangCresources(double var0, double var2) {
        double[] var4 = new double[8];
        long var5 = 1L;
        double var7 = 0.0D / 0.0;
        double var9 = 0.0D / 0.0;
        boolean var11 = true;

        while(true) {
            while(var11) {
                var9 = erlangCprobability(var2, var5);
                if (var9 <= var0) {
                    var4[0] = (double)var5;
                    var4[1] = var9;
                    var4[2] = erlangCload(var0, var5);
                    var4[3] = (double)(var5 - 1L);
                    var4[4] = var7;
                    var4[5] = erlangCload(var0, var5 - 1L);
                    var4[6] = var0;
                    var4[7] = var2;
                    var11 = false;
                } else {
                    var7 = var9;
                    ++var5;
                    if (var5 == 2147483647L) {
                        System.out.println("Method erlangCresources: no solution found below 9223372036854775807resources");

                        for(int var12 = 0; var12 < 8; ++var12) {
                            var4[var12] = 0.0D / 0.0;
                        }

                        var11 = false;
                    }
                }
            }

            return var4;
        }
    }

    public static double engsetProbability(double var0, double var2, double var4) {
        if (var2 < 1.0D) {
            throw new IllegalArgumentException("Total resources, " + var2 + ", must be an integer greater than or equal to 1");
        } else if (!Fmath.isInteger(var2)) {
            throw new IllegalArgumentException("Total resources, " + var2 + ", must be, arithmetically, an integer");
        } else if (var4 < 1.0D) {
            throw new IllegalArgumentException("number of sources, " + var4 + ", must be an integer greater than or equal to 1");
        } else if (!Fmath.isInteger(var4)) {
            throw new IllegalArgumentException("number of sources, " + var4 + ", must be, arithmetically, an integer");
        } else if (var2 > var4 - 1.0D) {
            throw new IllegalArgumentException("total resources, " + var2 + ", must be less than or  equal to the number of sources minus one, " + (var4 - 1.0D));
        } else if (var0 >= var4) {
            throw new IllegalArgumentException("Number of sources, " + var4 + ", must be greater than the offered traffic, " + var0);
        } else {
            double var6 = 0.0D;
            if (var2 == 0.0D) {
                var6 = 1.0D;
            } else if (var0 == 0.0D) {
                var6 = 0.0D;
            } else {
                double var8 = 0.0D;
                double var10 = 1.0D;
                EngsetProb var12 = new EngsetProb();
                var12.offeredTraffic = var0;
                var12.totalResources = var2;
                var12.numberOfSources = var4;
                RealRoot var13 = new RealRoot();
                var13.suppressLimitReachedMessage();
                var6 = var13.bisect(var12, var8, var10);
            }

            return var6;
        }
    }

    public static double engsetProbability(double var0, long var2, long var4) {
        return engsetProbability(var0, (double)var2, (double)var4);
    }

    public static double engsetProbability(double var0, int var2, int var3) {
        return engsetProbability(var0, (double)var2, (double)var3);
    }

    public static double engsetLoad(double var0, double var2, double var4) {
        if (var2 < 1.0D) {
            throw new IllegalArgumentException("Total resources, " + var2 + ", must be an integer greater than or equal to 1");
        } else if (!Fmath.isInteger(var2)) {
            throw new IllegalArgumentException("Total resources, " + var2 + ", must be, arithmetically, an integer");
        } else if (var4 < 1.0D) {
            throw new IllegalArgumentException("number of sources, " + var4 + ", must be an integer greater than or equal to 1");
        } else if (!Fmath.isInteger(var4)) {
            throw new IllegalArgumentException("number of sources, " + var4 + ", must be, arithmetically, an integer");
        } else if (var2 > var4 - 1.0D) {
            throw new IllegalArgumentException("total resources, " + var2 + ", must be less than or  equal to the number of sources minus one, " + (var4 - 1.0D));
        } else {
            EngsetLoad var6 = new EngsetLoad();
            var6.blockingProbability = var0;
            var6.totalResources = var2;
            var6.numberOfSources = var4;
            double var7 = 0.0D;
            double var9 = var4 * 0.999999999D;
            double var11 = 1.0E-6D;
            RealRoot var13 = new RealRoot();
            var13.setTolerance(var11);
            var13.noLowerBoundExtension();
            var13.noUpperBoundExtension();
            var13.suppressLimitReachedMessage();
            double var14 = var13.bisect(var6, var7, var9);
            return var14;
        }
    }

    public static double engsetLoad(double var0, long var2, long var4) {
        return engsetLoad(var0, (double)var2, (double)var4);
    }

    public static double engsetLoad(double var0, int var2, int var3) {
        return engsetLoad(var0, (double)var2, (double)var3);
    }

    public static double[] engsetResources(double var0, double var2, double var4) {
        if (var4 < 1.0D) {
            throw new IllegalArgumentException("number of sources, " + var4 + ", must be an integer greater than or equal to 1");
        } else if (!Fmath.isInteger(var4)) {
            throw new IllegalArgumentException("number of sources, " + var4 + ", must be, arithmetically, an integer");
        } else {
            double[] var6 = new double[9];
            long var7 = 1L;
            double var9 = 0.0D / 0.0;
            double var11 = 0.0D / 0.0;
            boolean var13 = true;

            while(true) {
                while(var13) {
                    var11 = engsetProbability(var2, (double)var7, var4);
                    if (var11 <= var0) {
                        var6[0] = (double)var7;
                        var6[1] = var11;
                        var6[2] = engsetLoad(var0, (double)var7, var4);
                        var6[3] = (double)(var7 - 1L);
                        var6[4] = var9;
                        var6[5] = engsetLoad(var0, (double)(var7 - 1L), var4);
                        var6[6] = var0;
                        var6[7] = var2;
                        var6[8] = var4;
                        var13 = false;
                    } else {
                        var9 = var11;
                        ++var7;
                        if (var7 > (long)var4 - 1L) {
                            System.out.println("Method engsetResources: no solution found below the (sources-1), " + (var4 - 1.0D));

                            for(int var14 = 0; var14 < 8; ++var14) {
                                var6[var14] = 0.0D / 0.0;
                            }

                            var13 = false;
                        }
                    }
                }

                return var6;
            }
        }
    }

    public static double[] engsetResources(double var0, double var2, long var4) {
        return engsetResources(var0, var2, (double)var4);
    }

    public static double[] engsetResources(double var0, double var2, int var4) {
        return engsetResources(var0, var2, (double)var4);
    }

    public static double[] engsetSources(double var0, double var2, double var4) {
        if (var4 < 1.0D) {
            throw new IllegalArgumentException("resources, " + var4 + ", must be an integer greater than or equal to 1");
        } else if (!Fmath.isInteger(var4)) {
            throw new IllegalArgumentException("resources, " + var4 + ", must be, arithmetically, an integer");
        } else {
            double[] var6 = new double[9];
            long var7 = (long)var4 + 1L;
            double var9 = 0.0D / 0.0;
            double var11 = 0.0D / 0.0;
            boolean var13 = true;

            while(true) {
                while(var13) {
                    var11 = engsetProbability(var2, var4, (double)var7);
                    if (var11 >= var0) {
                        var6[0] = (double)var7;
                        var6[1] = var11;
                        var6[2] = engsetLoad(var0, var4, (double)var7);
                        var6[3] = (double)(var7 - 1L);
                        var6[4] = var9;
                        if (var7 - 1L >= (long)(var4 + 1.0D)) {
                            var6[5] = engsetLoad(var0, var4, (double)(var7 - 1L));
                        } else {
                            var6[5] = 0.0D / 0.0;
                        }

                        var6[6] = var0;
                        var6[7] = var2;
                        var6[8] = var4;
                        var13 = false;
                    } else {
                        var9 = var11;
                        ++var7;
                        if (var7 >= 9223372036854775807L) {
                            System.out.println("Method engsetResources: no solution found below 9223372036854775807sources");

                            for(int var14 = 0; var14 < 8; ++var14) {
                                var6[var14] = 0.0D / 0.0;
                            }

                            var13 = false;
                        }
                    }
                }

                return var6;
            }
        }
    }

    public static double[] engsetSources(double var0, double var2, long var4) {
        return engsetSources(var0, var2, (double)var4);
    }

    public static double[] engsetSources(double var0, double var2, int var4) {
        return engsetSources(var0, var2, (double)var4);
    }

    public static double betaCDF(double var0, double var2, double var4) {
        return betaCDF(0.0D, 1.0D, var0, var2, var4);
    }

    public static double betaCDF(double var0, double var2, double var4, double var6, double var8) {
        if (var4 <= 0.0D) {
            throw new IllegalArgumentException("The shape parameter, alpha, " + var4 + "must be greater than zero");
        } else if (var6 <= 0.0D) {
            throw new IllegalArgumentException("The shape parameter, beta, " + var6 + "must be greater than zero");
        } else if (var8 < var0) {
            throw new IllegalArgumentException("limit, " + var8 + ", must be greater than or equal to the minimum value, " + var0);
        } else if (var8 > var2) {
            throw new IllegalArgumentException("limit, " + var8 + ", must be less than or equal to the maximum value, " + var2);
        } else {
            return regularisedBetaFunction(var4, var6, (var8 - var0) / (var2 - var0));
        }
    }

    public static double betaPDF(double var0, double var2, double var4) {
        return betaPDF(0.0D, 1.0D, var0, var2, var4);
    }

    public static double betaPDF(double var0, double var2, double var4, double var6, double var8) {
        if (var4 <= 0.0D) {
            throw new IllegalArgumentException("The shape parameter, alpha, " + var4 + "must be greater than zero");
        } else if (var6 <= 0.0D) {
            throw new IllegalArgumentException("The shape parameter, beta, " + var6 + "must be greater than zero");
        } else if (var8 < var0) {
            throw new IllegalArgumentException("x, " + var8 + ", must be greater than or equal to the minimum value, " + var0);
        } else if (var8 > var2) {
            throw new IllegalArgumentException("x, " + var8 + ", must be less than or equal to the maximum value, " + var2);
        } else {
            double var10 = Math.pow(var8 - var0, var4 - 1.0D) * Math.pow(var2 - var8, var6 - 1.0D) / Math.pow(var2 - var0, var4 + var6 - 1.0D);
            return var10 / betaFunction(var4, var6);
        }
    }

    public static double[] betaRand(double var0, double var2, int var4) {
        if (var0 <= 0.0D) {
            throw new IllegalArgumentException("The shape parameter, alpha, " + var0 + "must be greater than zero");
        } else if (var2 <= 0.0D) {
            throw new IllegalArgumentException("The shape parameter, beta, " + var2 + "must be greater than zero");
        } else {
            PsRandom var5 = new PsRandom();
            return var5.betaArray(var0, var2, var4);
        }
    }

    public static double[] betaRand(double var0, double var2, double var4, double var6, int var8) {
        if (var4 <= 0.0D) {
            throw new IllegalArgumentException("The shape parameter, alpha, " + var4 + "must be greater than zero");
        } else if (var6 <= 0.0D) {
            throw new IllegalArgumentException("The shape parameter, beta, " + var6 + "must be greater than zero");
        } else {
            PsRandom var9 = new PsRandom();
            return var9.betaArray(var0, var2, var4, var6, var8);
        }
    }

    public static double[] betaRand(double var0, double var2, int var4, long var5) {
        if (var0 <= 0.0D) {
            throw new IllegalArgumentException("The shape parameter, alpha, " + var0 + "must be greater than zero");
        } else if (var2 <= 0.0D) {
            throw new IllegalArgumentException("The shape parameter,  beta, " + var2 + "must be greater than zero");
        } else {
            PsRandom var7 = new PsRandom(var5);
            return var7.betaArray(var0, var2, var4);
        }
    }

    public static double[] betaRand(double var0, double var2, double var4, double var6, int var8, long var9) {
        if (var4 <= 0.0D) {
            throw new IllegalArgumentException("The shape parameter, alpha, " + var4 + "must be greater than zero");
        } else if (var6 <= 0.0D) {
            throw new IllegalArgumentException("The shape parameter,  beta, " + var6 + "must be greater than zero");
        } else {
            PsRandom var11 = new PsRandom(var9);
            return var11.betaArray(var0, var2, var4, var6, var8);
        }
    }

    public static double betaMean(double var0, double var2) {
        return betaMean(0.0D, 1.0D, var0, var2);
    }

    public static double betaMean(double var0, double var2, double var4, double var6) {
        if (var4 <= 0.0D) {
            throw new IllegalArgumentException("The shape parameter, alpha, " + var4 + "must be greater than zero");
        } else if (var6 <= 0.0D) {
            throw new IllegalArgumentException("The shape parameter, beta, " + var6 + "must be greater than zero");
        } else {
            return var0 + var4 * (var2 - var0) / (var4 + var6);
        }
    }

    public static double betaMode(double var0, double var2) {
        return betaMode(0.0D, 1.0D, var0, var2);
    }

    public static double betaMode(double var0, double var2, double var4, double var6) {
        if (var4 <= 0.0D) {
            throw new IllegalArgumentException("The shape parameter, alpha, " + var4 + "must be greater than zero");
        } else if (var6 <= 0.0D) {
            throw new IllegalArgumentException("The shape parameter, beta, " + var6 + "must be greater than zero");
        } else {
            double var8 = 0.0D / 0.0;
            if (var4 > 1.0D) {
                if (var6 > 1.0D) {
                    var8 = var0 + (var4 + var6) * (var2 - var0) / (var4 + var6 - 2.0D);
                } else {
                    var8 = var2;
                }
            } else if (var4 == 1.0D) {
                if (var6 > 1.0D) {
                    var8 = var0;
                } else if (var6 == 1.0D) {
                    var8 = 0.0D / 0.0;
                } else {
                    var8 = var2;
                }
            } else if (var6 >= 1.0D) {
                var8 = var0;
            } else {
                System.out.println("Class Stat; method betaMode; distribution is bimodal wirh modes at " + var0 + " and " + var2);
                System.out.println("NaN returned");
            }

            return var8;
        }
    }

    public static double betaStandardDeviation(double var0, double var2) {
        return betaStandDev(var0, var2);
    }

    public static double betaStandDev(double var0, double var2) {
        return betaStandDev(0.0D, 1.0D, var0, var2);
    }

    public static double betaStandardDeviation(double var0, double var2, double var4, double var6) {
        return betaStandDev(var0, var2, var4, var6);
    }

    public static double betaStandDev(double var0, double var2, double var4, double var6) {
        if (var4 <= 0.0D) {
            throw new IllegalArgumentException("The shape parameter, alpha, " + var4 + "must be greater than zero");
        } else if (var6 <= 0.0D) {
            throw new IllegalArgumentException("The shape parameter, beta, " + var6 + "must be greater than zero");
        } else {
            return (var2 - var0) / (var4 + var6) * Math.sqrt(var4 * var6 / (var4 + var6 + 1.0D));
        }
    }

    public static double betaFunction(double var0, double var2) {
        return Math.exp(logGamma(var0) + logGamma(var2) - logGamma(var0 + var2));
    }

    public static double beta(double var0, double var2) {
        return Math.exp(logGamma(var0) + logGamma(var2) - logGamma(var0 + var2));
    }

    public static double regularisedBetaFunction(double var0, double var2, double var4) {
        if (var4 >= 0.0D && var4 <= 1.0D) {
            double var6 = 0.0D;
            if (var4 == 0.0D) {
                var6 = 0.0D;
            } else if (var4 == 1.0D) {
                var6 = 1.0D;
            } else {
                var6 = Math.exp(logGamma(var0 + var2) - logGamma(var0) - logGamma(var2) + var0 * Math.log(var4) + var2 * Math.log(1.0D - var4));
                if (var4 < (var0 + 1.0D) / (var0 + var2 + 2.0D)) {
                    var6 = var6 * contFract(var0, var2, var4) / var0;
                } else {
                    var6 = 1.0D - var6 * contFract(var2, var0, 1.0D - var4) / var2;
                }
            }

            return var6;
        } else {
            throw new IllegalArgumentException("Argument x, " + var4 + ", must be lie between 0 and 1 (inclusive)");
        }
    }

    public static double regularizedBetaFunction(double var0, double var2, double var4) {
        return regularisedBetaFunction(var0, var2, var4);
    }

    public static double incompleteBeta(double var0, double var2, double var4) {
        return regularisedBetaFunction(var0, var2, var4);
    }

    public static double contFract(double var0, double var2, double var4) {
        double var6 = var0 + var2;
        double var8 = var0 + 1.0D;
        double var10 = var0 - 1.0D;
        double var12 = 1.0D;
        double var14 = 1.0D - var6 * var4 / var8;
        if (Math.abs(var14) < 1.0E-300D) {
            var14 = 1.0E-300D;
        }

        var14 = 1.0D / var14;
        double var16 = var14;
        double var18 = 0.0D;
        double var20 = 0.0D;
        int var22 = 1;
        boolean var23 = false;
        boolean var24 = true;

        while(var24) {
            int var25 = 2 * var22;
            var18 = (double)var22 * (var2 - (double)var22) * var4 / ((var10 + (double)var25) * (var0 + (double)var25));
            var14 = 1.0D + var18 * var14;
            if (Math.abs(var14) < 1.0E-300D) {
                var14 = 1.0E-300D;
            }

            var12 = 1.0D + var18 / var12;
            if (Math.abs(var12) < 1.0E-300D) {
                var12 = 1.0E-300D;
            }

            var14 = 1.0D / var14;
            var16 *= var14 * var12;
            var18 = -(var0 + (double)var22) * (var6 + (double)var22) * var4 / ((var0 + (double)var25) * (var8 + (double)var25));
            var14 = 1.0D + var18 * var14;
            if (Math.abs(var14) < 1.0E-300D) {
                var14 = 1.0E-300D;
            }

            var12 = 1.0D + var18 / var12;
            if (Math.abs(var12) < 1.0E-300D) {
                var12 = 1.0E-300D;
            }

            var14 = 1.0D / var14;
            var20 = var14 * var12;
            var16 *= var20;
            ++var22;
            if (Math.abs(var20 - 1.0D) < cfTol) {
                var24 = false;
            }

            if (var22 > cfMaxIter) {
                var24 = false;
                System.out.println("Maximum number of iterations (" + cfMaxIter + ") exceeded in Stat.contFract in Stat.incompleteBeta");
            }
        }

        return var16;
    }

    public static void resetCFmaxIter(int var0) {
        cfMaxIter = var0;
    }

    public static int getCFmaxIter() {
        return cfMaxIter;
    }

    public static void resetCFtolerance(double var0) {
        cfTol = var0;
    }

    public static double getCFtolerance() {
        return cfTol;
    }

    public static double erf(double var0) {
        double var2 = 0.0D;
        if (var0 != 0.0D) {
            if (var0 == 1.0D / 0.0) {
                var2 = 1.0D;
            } else if (var0 >= 0.0D) {
                var2 = incompleteGamma(0.5D, var0 * var0);
            } else {
                var2 = -incompleteGamma(0.5D, var0 * var0);
            }
        }

        return var2;
    }

    public static double erfc(double var0) {
        double var2 = 1.0D;
        if (var0 != 0.0D) {
            if (var0 == 1.0D / 0.0) {
                var2 = 0.0D;
            } else if (var0 >= 0.0D) {
                var2 = 1.0D - incompleteGamma(0.5D, var0 * var0);
            } else {
                var2 = 1.0D + incompleteGamma(0.5D, var0 * var0);
            }
        }

        return var2;
    }

    public static double normalCDF(double var0, double var2, double var4) {
        double var6 = 0.0D / 0.0;
        if (var4 == 1.0D / 0.0) {
            var6 = 1.0D;
        } else if (var4 == -1.0D / 0.0) {
            var6 = 0.0D;
        } else {
            double var8 = (var4 - var0) / (var2 * Math.sqrt(2.0D));
            var6 = (1.0D + erf(var8)) / 2.0D;
        }

        if (Fmath.isNaN(var6)) {
            if (var4 > var0) {
                var6 = 1.0D;
            } else {
                var6 = 0.0D;
            }
        }

        return var6;
    }

    public static double normalProb(double var0, double var2, double var4) {
        if (var4 == 1.0D / 0.0) {
            return 1.0D;
        } else if (var4 == -1.0D / 0.0) {
            return 0.0D;
        } else {
            double var6 = (var4 - var0) / (var2 * Math.sqrt(2.0D));
            return (1.0D + erf(var6)) / 2.0D;
        }
    }

    public static double gaussianCDF(double var0, double var2, double var4) {
        return normalCDF(var0, var2, var4);
    }

    public static double gaussianProb(double var0, double var2, double var4) {
        return normalCDF(var0, var2, var4);
    }

    public static double normalCDF(double var0, double var2, double var4, double var6) {
        return normalCDF(var0, var2, var6) - normalCDF(var0, var2, var4);
    }

    public static double normalProb(double var0, double var2, double var4, double var6) {
        return normalCDF(var0, var2, var6) - normalCDF(var0, var2, var4);
    }

    public static double gaussianCDF(double var0, double var2, double var4, double var6) {
        return normalCDF(var0, var2, var6) - normalCDF(var0, var2, var4);
    }

    public static double gaussianProb(double var0, double var2, double var4, double var6) {
        return normalCDF(var0, var2, var6) - normalCDF(var0, var2, var4);
    }

    public static double gaussianInverseCDF(double var0, double var2, double var4) {
        if (var4 >= 0.0D && var4 <= 1.0D) {
            double var6 = 0.0D;
            if (var4 == 0.0D) {
                var6 = -1.0D / 0.0;
            } else if (var4 == 1.0D) {
                var6 = 1.0D / 0.0;
            } else {
                GaussianFunct var8 = new GaussianFunct();
                var8.mean = var0;
                var8.sd = var2;
                double var9 = 1.0E-12D;
                double var11 = var0 - 10.0D * var2;
                double var13 = var0 + 10.0D * var2;
                RealRoot var15 = new RealRoot();
                var15.setTolerance(var9);
                var15.resetNaNexceptionToTrue();
                var15.suppressLimitReachedMessage();
                var15.suppressNaNmessage();
                var8.cfd = var4;
                var6 = var15.bisect(var8, var11, var13);
            }

            return var6;
        } else {
            throw new IllegalArgumentException("Entered cdf value, " + var4 + ", must lie between 0 and 1 inclusive");
        }
    }

    public static double inverseGaussianCDF(double var0, double var2, double var4) {
        return gaussianInverseCDF(var0, var2, var4);
    }

    public static double normalInverseCDF(double var0, double var2, double var4) {
        return gaussianInverseCDF(var0, var2, var4);
    }

    public static double inverseNormalCDF(double var0, double var2, double var4) {
        return gaussianInverseCDF(var0, var2, var4);
    }

    public static double gaussianInverseCDF(double var0) {
        return gaussianInverseCDF(0.0D, 1.0D, var0);
    }

    public static double inverseGaussianCDF(double var0) {
        return gaussianInverseCDF(0.0D, 1.0D, var0);
    }

    public static double normalInverseCDF(double var0) {
        return gaussianInverseCDF(0.0D, 1.0D, var0);
    }

    public static double inverseNormalCDF(double var0) {
        return gaussianInverseCDF(0.0D, 1.0D, var0);
    }

    public static double[] meanConfidenceLimits(double var0, double var2, double var4) {
        double[] var6 = new double[2];
        double var7 = var4 / 2.0D + 0.5D;
        double var9 = gaussianInverseCDF(var0, var2, var7);
        var6[0] = 2.0D * var0 - var9;
        var6[1] = var9;
        return var6;
    }

    public double[] meanConfidenceLimits(double var1) {
        double[] var3 = new double[2];
        double var4 = var1 / 2.0D + 0.5D;
        double var6 = this.mean();
        double var8 = this.standardDeviation();
        double var10 = gaussianInverseCDF(var6, var8, var4);
        var3[0] = 2.0D * var6 - var10;
        var3[1] = var10;
        return var3;
    }

    public static double[] gaussianOrderStatisticMedians(double var0, double var2, int var4) {
        double var5 = (double)var4;
        double[] var7 = new double[var4];
        double[] var8 = uniformOrderStatisticMedians(var4);

        for(int var9 = 0; var9 < var4; ++var9) {
            var7[var9] = inverseGaussianCDF(var0, var2, var8[var9]);
        }

        var7 = scale(var7, var0, var2);
        return var7;
    }

    public static double[] normalOrderStatisticMedians(double var0, double var2, int var4) {
        return gaussianOrderStatisticMedians(var0, var2, var4);
    }

    public static double[] gaussianOrderStatisticMedians(int var0) {
        return gaussianOrderStatisticMedians(0.0D, 1.0D, var0);
    }

    public static double[] normalOrderStatisticMedians(int var0) {
        return gaussianOrderStatisticMedians(0.0D, 1.0D, var0);
    }

    public static double normalPDF(double var0, double var2, double var4) {
        return Math.exp(-Fmath.square((var4 - var0) / var2) / 2.0D) / (var2 * Math.sqrt(6.283185307179586D));
    }

    public static double normal(double var0, double var2, double var4) {
        return Math.exp(-Fmath.square((var4 - var0) / var2) / 2.0D) / (var2 * Math.sqrt(6.283185307179586D));
    }

    public static double gaussianPDF(double var0, double var2, double var4) {
        return Math.exp(-Fmath.square((var4 - var0) / var2) / 2.0D) / (var2 * Math.sqrt(6.283185307179586D));
    }

    public static double gaussian(double var0, double var2, double var4) {
        return Math.exp(-Fmath.square((var4 - var0) / var2) / 2.0D) / (var2 * Math.sqrt(6.283185307179586D));
    }

    public static double[] normalRand(double var0, double var2, int var4) {
        double[] var5 = new double[var4];
        Random var6 = new Random();

        int var7;
        for(var7 = 0; var7 < var4; ++var7) {
            var5[var7] = var6.nextGaussian();
        }

        var5 = standardize(var5);

        for(var7 = 0; var7 < var4; ++var7) {
            var5[var7] = var5[var7] * var2 + var0;
        }

        return var5;
    }

    public static double[] gaussianRand(double var0, double var2, int var4) {
        return normalRand(var0, var2, var4);
    }

    public static double[] normalRand(double var0, double var2, int var4, long var5) {
        double[] var7 = new double[var4];
        Random var8 = new Random(var5);

        int var9;
        for(var9 = 0; var9 < var4; ++var9) {
            var7[var9] = var8.nextGaussian();
        }

        var7 = standardize(var7);

        for(var9 = 0; var9 < var4; ++var9) {
            var7[var9] = var7[var9] * var2 + var0;
        }

        return var7;
    }

    public static double[] gaussianRand(double var0, double var2, int var4, long var5) {
        return normalRand(var0, var2, var4, var5);
    }

    public static double logNormalCDF(double var0, double var2, double var4) {
        if (var2 < 0.0D) {
            throw new IllegalArgumentException("The parameter sigma, " + var2 + ", must be greater than or equal to zero");
        } else {
            return var4 <= 0.0D ? 0.0D : 0.5D * (1.0D + erf((Math.log(var4) - var0) / (var2 * Math.sqrt(2.0D))));
        }
    }

    public static double logNormalTwoParCDF(double var0, double var2, double var4) {
        return logNormalCDF(var0, var2, var4);
    }

    public static double logNormalCDF(double var0, double var2, double var4, double var6) {
        if (var2 < 0.0D) {
            throw new IllegalArgumentException("The parameter sigma, " + var2 + ", must be greater than or equal to zero");
        } else if (var6 < var4) {
            throw new IllegalArgumentException("The upper limit, " + var6 + ", must be greater than the " + var4);
        } else {
            double var8 = 0.0D;
            double var10 = 0.0D;
            double var12 = 0.0D;
            if (var4 != var6) {
                if (var6 > 0.0D) {
                    var8 = 0.5D * (1.0D + erf((Math.log(var6) - var0) / (var2 * Math.sqrt(2.0D))));
                }

                if (var4 > 0.0D) {
                    var10 = 0.5D * (1.0D + erf((Math.log(var4) - var0) / (var2 * Math.sqrt(2.0D))));
                }

                var12 = var8 - var10;
            }

            return var12;
        }
    }

    public static double logNormalTwoParCDF(double var0, double var2, double var4, double var6) {
        return logNormalCDF(var0, var2, var4, var6);
    }

    public static double logNormalInverseCDF(double var0, double var2, double var4) {
        double var6 = 0.0D;
        double var10 = Math.exp(var0);
        return logNormalInverseCDF(var6, var2, var10, var4);
    }

    public static double logNormaltwoParInverseCDF(double var0, double var2, double var4) {
        double var6 = 0.0D;
        double var10 = Math.exp(var0);
        return logNormalInverseCDF(var6, var2, var10, var4);
    }

    public static double logNormalPDF(double var0, double var2, double var4) {
        if (var2 < 0.0D) {
            throw new IllegalArgumentException("The parameter sigma, " + var2 + ", must be greater than or equal to zero");
        } else {
            return var4 < 0.0D ? 0.0D : Math.exp(-0.5D * Fmath.square((Math.log(var4) - var0) / var2)) / (var4 * var2 * Math.sqrt(6.283185307179586D));
        }
    }

    public static double logNormalTwoParPDF(double var0, double var2, double var4) {
        return logNormalPDF(var0, var2, var4);
    }

    public static double logNormalMean(double var0, double var2) {
        return Math.exp(var0 + var2 * var2 / 2.0D);
    }

    public static double logNormalTwoParMean(double var0, double var2) {
        return Math.exp(var0 + var2 * var2 / 2.0D);
    }

    public static double logNormalStandardDeviation(double var0, double var2) {
        return logNormalStandDev(var0, var2);
    }

    public static double logNormalStandDev(double var0, double var2) {
        double var4 = var2 * var2;
        return Math.sqrt((Math.exp(var4) - 1.0D) * Math.exp(2.0D * var0 + var4));
    }

    public static double logNormalTwoParStandardDeviation(double var0, double var2) {
        return logNormalTwoParStandDev(var0, var2);
    }

    public static double logNormalTwoParStandDev(double var0, double var2) {
        double var4 = var2 * var2;
        return Math.sqrt((Math.exp(var4) - 1.0D) * Math.exp(2.0D * var0 + var4));
    }

    public static double logNormalMode(double var0, double var2) {
        return Math.exp(var0 - var2 * var2);
    }

    public static double logNormalTwoParMode(double var0, double var2) {
        return Math.exp(var0 - var2 * var2);
    }

    public static double logNormalMedian(double var0) {
        return Math.exp(var0);
    }

    public static double logNormalTwoParMedian(double var0) {
        return Math.exp(var0);
    }

    public static double[] logNormalRand(double var0, double var2, int var4) {
        if (var4 <= 0) {
            throw new IllegalArgumentException("The number of random deviates required, " + var4 + ", must be greater than zero");
        } else if (var2 < 0.0D) {
            throw new IllegalArgumentException("The parameter sigma, " + var2 + ", must be greater than or equal to zero");
        } else {
            PsRandom var5 = new PsRandom();
            return var5.logNormalArray(var0, var2, var4);
        }
    }

    public static double[] logNormalTwoParRand(double var0, double var2, int var4) {
        return logNormalRand(var0, var2, var4);
    }

    public static double[] logNormalOrderStatisticMedians(double var0, double var2, int var4) {
        double var5 = 0.0D;
        double var9 = Math.exp(var0);
        return logNormalOrderStatisticMedians(var5, var2, var9, var4);
    }

    public static double[] logNormalTwoParOrderStatisticMedians(double var0, double var2, int var4) {
        return logNormalOrderStatisticMedians(var0, var2, var4);
    }

    public static double[] logNormalRand(double var0, double var2, int var4, long var5) {
        if (var4 <= 0) {
            throw new IllegalArgumentException("The number of random deviates required, " + var4 + ", must be greater than zero");
        } else if (var2 < 0.0D) {
            throw new IllegalArgumentException("The parameter sigma, " + var2 + ", must be greater than or equal to zero");
        } else {
            PsRandom var7 = new PsRandom(var5);
            return var7.logNormalArray(var0, var2, var4);
        }
    }

    public static double[] logNormalTwoParRand(double var0, double var2, int var4, long var5) {
        return logNormalRand(var0, var2, var4, var5);
    }

    public static double logNormalThreeParCDF(double var0, double var2, double var4, double var6) {
        if (var2 < 0.0D) {
            throw new IllegalArgumentException("The parameter beta, " + var2 + ", must be greater than or equal to zero");
        } else {
            return var6 <= var0 ? 0.0D : 0.5D * (1.0D + erf(Math.log((var6 - var0) / var4) / (var2 * Math.sqrt(2.0D))));
        }
    }

    public static double logNormalThreeParCDF(double var0, double var2, double var4, double var6, double var8) {
        if (var2 < 0.0D) {
            throw new IllegalArgumentException("The parameter beta, " + var2 + ", must be greater than or equal to zero");
        } else if (var8 < var6) {
            throw new IllegalArgumentException("The upper limit, " + var8 + ", must be greater than the " + var6);
        } else {
            double var10 = 0.0D;
            double var12 = 0.0D;
            double var14 = 0.0D;
            if (var6 != var8) {
                if (var8 > var0) {
                    var10 = 0.5D * (1.0D + erf(Math.log((var8 - var0) / var4) / (var2 * Math.sqrt(2.0D))));
                }

                if (var6 > var0) {
                    var12 = 0.5D * (1.0D + erf(Math.log((var6 - var0) / var4) / (var2 * Math.sqrt(2.0D))));
                }

                var14 = var10 - var12;
            }

            return var14;
        }
    }

    public static double logNormalInverseCDF(double var0, double var2, double var4, double var6) {
        if (var6 >= 0.0D && var6 <= 1.0D) {
            double var8 = 0.0D;
            if (var6 == 0.0D) {
                var8 = var0;
            } else if (var6 == 1.0D) {
                var8 = 1.0D / 0.0;
            } else {
                LogNormalThreeParFunct var10 = new LogNormalThreeParFunct();
                var10.alpha = var0;
                var10.beta = var2;
                var10.gamma = var4;
                double var11 = 1.0E-12D;
                double var15 = logNormalThreeParMean(var0, var2, var4) + 5.0D * logNormalThreeParStandardDeviation(var0, var2, var4);
                RealRoot var17 = new RealRoot();
                var17.noLowerBoundExtension();
                var17.setTolerance(var11);
                var17.resetNaNexceptionToTrue();
                var17.suppressLimitReachedMessage();
                var17.suppressNaNmessage();
                var10.cfd = var6;
                var8 = var17.bisect(var10, var0, var15);
            }

            return var8;
        } else {
            throw new IllegalArgumentException("Entered cdf value, " + var6 + ", must lie between 0 and 1 inclusive");
        }
    }

    public static double logNormalThreeParInverseCDF(double var0, double var2, double var4, double var6) {
        return logNormalInverseCDF(var0, var2, var4, var6);
    }

    public static double logNormalThreeParPDF(double var0, double var2, double var4, double var6) {
        if (var2 < 0.0D) {
            throw new IllegalArgumentException("The parameter beta, " + var2 + ", must be greater than or equal to zero");
        } else {
            return var6 <= var0 ? 0.0D : Math.exp(-0.5D * Fmath.square(Math.log((var6 - var0) / var4) / var2)) / ((var6 - var4) * var2 * Math.sqrt(6.283185307179586D));
        }
    }

    public static double[] logNormalThreeParRand(double var0, double var2, double var4, int var6) {
        if (var6 <= 0) {
            throw new IllegalArgumentException("The number of random deviates required, " + var6 + ", must be greater than zero");
        } else if (var2 < 0.0D) {
            throw new IllegalArgumentException("The parameter beta, " + var2 + ", must be greater than or equal to zero");
        } else {
            PsRandom var7 = new PsRandom();
            return var7.logNormalThreeParArray(var0, var2, var4, var6);
        }
    }

    public static double[] logNormalThreeParRand(double var0, double var2, double var4, int var6, long var7) {
        if (var6 <= 0) {
            throw new IllegalArgumentException("The number of random deviates required, " + var6 + ", must be greater than zero");
        } else if (var2 < 0.0D) {
            throw new IllegalArgumentException("The parameter beta, " + var2 + ", must be greater than or equal to zero");
        } else {
            PsRandom var9 = new PsRandom(var7);
            return var9.logNormalThreeParArray(var0, var2, var4, var6);
        }
    }

    public static double[] logNormalOrderStatisticMedians(double var0, double var2, double var4, int var6) {
        double var7 = (double)var6;
        double[] var9 = new double[var6];
        double[] var10 = uniformOrderStatisticMedians(var6);

        for(int var11 = 0; var11 < var6; ++var11) {
            var9[var11] = logNormalThreeParInverseCDF(var0, var2, var4, var10[var11]);
        }

        var9 = scale(var9, logNormalThreeParMean(var0, var2, var4), logNormalThreeParStandardDeviation(var0, var2, var4));
        return var9;
    }

    public static double[] logNormalThreeParOrderStatisticMedians(double var0, double var2, double var4, int var6) {
        return logNormalOrderStatisticMedians(var0, var2, var4, var6);
    }

    public static double logNormalThreeParMean(double var0, double var2, double var4) {
        return var4 * Math.exp(var2 * var2 / 2.0D) + var0;
    }

    public static double logNormalThreeParStandardDeviation(double var0, double var2, double var4) {
        return logNormalThreeParStandDev(var0, var2, var4);
    }

    public static double logNormalThreeParStandDev(double var0, double var2, double var4) {
        double var6 = var2 * var2;
        return Math.sqrt((Math.exp(var6) - 1.0D) * Math.exp(2.0D * Math.log(var4) + var6));
    }

    public static double logNormalThreeParMode(double var0, double var2, double var4) {
        return var4 * Math.exp(-var2 * var2) + var0;
    }

    public static double logNormalThreeParMedian(double var0, double var2) {
        return var2 + var0;
    }

    public static double logisticCDF(double var0, double var2, double var4) {
        return 0.5D * (1.0D + Math.tanh((var4 - var0) / (2.0D * var2)));
    }

    public static double logisticTwoParCDF(double var0, double var2, double var4) {
        return 0.5D * (1.0D + Math.tanh((var4 - var0) / (2.0D * var2)));
    }

    public static double logisticProb(double var0, double var2, double var4) {
        return 0.5D * (1.0D + Math.tanh((var4 - var0) / (2.0D * var2)));
    }

    public static double logisticCDF(double var0, double var2, double var4, double var6) {
        double var8 = 0.5D * (1.0D + Math.tanh((var4 - var0) / (2.0D * var2)));
        double var10 = 0.5D * (1.0D + Math.tanh((var6 - var0) / (2.0D * var2)));
        return var10 - var8;
    }

    public static double logisticTwoParCDF(double var0, double var2, double var4, double var6) {
        double var8 = 0.5D * (1.0D + Math.tanh((var4 - var0) / (2.0D * var2)));
        double var10 = 0.5D * (1.0D + Math.tanh((var6 - var0) / (2.0D * var2)));
        return var10 - var8;
    }

    public static double logisticProb(double var0, double var2, double var4, double var6) {
        double var8 = 0.5D * (1.0D + Math.tanh((var4 - var0) / (2.0D * var2)));
        double var10 = 0.5D * (1.0D + Math.tanh((var6 - var0) / (2.0D * var2)));
        return var10 - var8;
    }

    public static double logisticTwoParInverseCDF(double var0, double var2, double var4) {
        return logisticInverseCDF(var0, var2, var4);
    }

    public static double logisticInverseCDF(double var0, double var2, double var4) {
        if (var4 >= 0.0D && var4 <= 1.0D) {
            double var6 = 0.0D;
            if (var4 == 0.0D) {
                var6 = -1.0D / 0.0;
            } else if (var4 == 1.0D) {
                var6 = 1.0D / 0.0;
            } else {
                var6 = var0 - var2 * Math.log(1.0D / var4 - 1.0D);
            }

            return var6;
        } else {
            throw new IllegalArgumentException("Entered cdf value, " + var4 + ", must lie between 0 and 1 inclusive");
        }
    }

    public static double logisticPDF(double var0, double var2, double var4) {
        return Fmath.square(Fmath.sech((var4 - var0) / (2.0D * var2))) / (4.0D * var2);
    }

    public static double logisticTwoParPDF(double var0, double var2, double var4) {
        return Fmath.square(Fmath.sech((var4 - var0) / (2.0D * var2))) / (4.0D * var2);
    }

    public static double logistic(double var0, double var2, double var4) {
        return Fmath.square(Fmath.sech((var4 - var0) / (2.0D * var2))) / (4.0D * var2);
    }

    public static double[] logisticTwoParRand(double var0, double var2, int var4) {
        return logisticRand(var0, var2, var4);
    }

    public static double[] logisticRand(double var0, double var2, int var4) {
        double[] var5 = new double[var4];
        Random var6 = new Random();

        for(int var7 = 0; var7 < var4; ++var7) {
            var5[var7] = 2.0D * var2 * Fmath.atanh(2.0D * var6.nextDouble() - 1.0D) + var0;
        }

        return var5;
    }

    public static double[] logisticTwoParRand(double var0, double var2, int var4, long var5) {
        return logisticRand(var0, var2, var4, var5);
    }

    public static double[] logisticRand(double var0, double var2, int var4, long var5) {
        double[] var7 = new double[var4];
        Random var8 = new Random(var5);

        for(int var9 = 0; var9 < var4; ++var9) {
            var7[var9] = 2.0D * var2 * Fmath.atanh(2.0D * var8.nextDouble() - 1.0D) + var0;
        }

        return var7;
    }

    public static double[] logisticOrderStatisticMedians(double var0, double var2, int var4) {
        double var5 = (double)var4;
        double[] var7 = new double[var4];
        double[] var8 = uniformOrderStatisticMedians(var4);

        for(int var9 = 0; var9 < var4; ++var9) {
            var7[var9] = logisticInverseCDF(var0, var2, var8[var9]);
        }

        return var7;
    }

    public static double[] logisticTwoParOrderStatisticMedians(double var0, double var2, int var4) {
        double var5 = (double)var4;
        double[] var7 = new double[var4];
        double[] var8 = uniformOrderStatisticMedians(var4);

        for(int var9 = 0; var9 < var4; ++var9) {
            var7[var9] = logisticInverseCDF(var0, var2, var8[var9]);
        }

        return var7;
    }

    public static double logisticMean(double var0) {
        return var0;
    }

    public static double logisticTwoParMean(double var0) {
        return var0;
    }

    public static double logisticStandardDeviation(double var0) {
        return logisticStandDev(var0);
    }

    public static double logisticStandDev(double var0) {
        return Math.sqrt(Fmath.square(3.141592653589793D * var0) / 3.0D);
    }

    public static double logisticTwoParStandardDeviation(double var0) {
        return Math.sqrt(Fmath.square(3.141592653589793D * var0) / 3.0D);
    }

    public static double logisticMode(double var0) {
        return var0;
    }

    public static double logisticTwoParMode(double var0) {
        return var0;
    }

    public static double logisticMedian(double var0) {
        return var0;
    }

    public static double logisticTwoParMedian(double var0) {
        return var0;
    }

    public static double lorentzianProb(double var0, double var2, double var4) {
        double var6 = (var4 - var0) / (var2 / 2.0D);
        return 0.3183098861837907D * (Math.atan(var6) + 1.5707963267948966D);
    }

    public static double lorentzianCDF(double var0, double var2, double var4, double var6) {
        double var8 = (var6 - var0) / (var2 / 2.0D);
        double var10 = (var4 - var0) / (var2 / 2.0D);
        return 0.3183098861837907D * (Math.atan(var8) - Math.atan(var10));
    }

    public static double lorentzianProb(double var0, double var2, double var4, double var6) {
        double var8 = (var6 - var0) / (var2 / 2.0D);
        double var10 = (var4 - var0) / (var2 / 2.0D);
        return 0.3183098861837907D * (Math.atan(var8) - Math.atan(var10));
    }

    public static double lorentzianInverseCDF(double var0, double var2, double var4) {
        if (var4 >= 0.0D && var4 <= 1.0D) {
            double var6 = 0.0D;
            if (var4 == 0.0D) {
                var6 = -1.0D / 0.0;
            } else if (var4 == 1.0D) {
                var6 = 1.0D / 0.0;
            } else {
                var6 = var0 + var2 * Math.tan(3.141592653589793D * (var4 - 0.5D)) / 2.0D;
            }

            return var6;
        } else {
            throw new IllegalArgumentException("Entered cdf value, " + var4 + ", must lie between 0 and 1 inclusive");
        }
    }

    public static double lorentzianPDF(double var0, double var2, double var4) {
        double var6 = var2 / 2.0D;
        return 0.3183098861837907D * var6 / (Fmath.square(var0 - var4) + var6 * var6);
    }

    public static double lorentzian(double var0, double var2, double var4) {
        double var6 = var2 / 2.0D;
        return 0.3183098861837907D * var6 / (Fmath.square(var0 - var4) + var6 * var6);
    }

    public static double[] lorentzianRand(double var0, double var2, int var4) {
        double[] var5 = new double[var4];
        Random var6 = new Random();

        for(int var7 = 0; var7 < var4; ++var7) {
            var5[var7] = Math.tan((var6.nextDouble() - 0.5D) * 3.141592653589793D);
            var5[var7] = var5[var7] * var2 / 2.0D + var0;
        }

        return var5;
    }

    public static double[] lorentzianRand(double var0, double var2, int var4, long var5) {
        double[] var7 = new double[var4];
        Random var8 = new Random(var5);

        for(int var9 = 0; var9 < var4; ++var9) {
            var7[var9] = Math.tan((var8.nextDouble() - 0.5D) * 3.141592653589793D);
            var7[var9] = var7[var9] * var2 / 2.0D + var0;
        }

        return var7;
    }

    public static double[] lorentzianOrderStatisticMedians(double var0, double var2, int var4) {
        double var5 = (double)var4;
        double[] var7 = new double[var4];
        double[] var8 = uniformOrderStatisticMedians(var4);

        for(int var9 = 0; var9 < var4; ++var9) {
            var7[var9] = lorentzianInverseCDF(var0, var2, var8[var9]);
        }

        return var7;
    }

    public static double poissonCDF(int var0, double var1) {
        if (var0 < 1) {
            throw new IllegalArgumentException("k must be an integer greater than or equal to 1");
        } else {
            return incompleteGammaComplementary((double)var0, var1);
        }
    }

    public static double poissonProb(int var0, double var1) {
        if (var0 < 1) {
            throw new IllegalArgumentException("k must be an integer greater than or equal to 1");
        } else {
            return incompleteGammaComplementary((double)var0, var1);
        }
    }

    public static double poissonPDF(int var0, double var1) {
        if (var0 < 0) {
            throw new IllegalArgumentException("k must be an integer greater than or equal to 0");
        } else {
            return Math.pow(var1, (double)var0) * Math.exp(-var1) / factorial((double)var0);
        }
    }

    public static double poisson(int var0, double var1) {
        if (var0 < 0) {
            throw new IllegalArgumentException("k must be an integer greater than or equal to 0");
        } else {
            return Math.pow(var1, (double)var0) * Math.exp(-var1) / factorial((double)var0);
        }
    }

    public static double[] poissonRand(double var0, int var2) {
        Random var3 = new Random();
        double[] var4 = poissonRandCalc(var3, var0, var2);
        return var4;
    }

    public static double[] poissonRand(double var0, int var2, long var3) {
        Random var5 = new Random(var3);
        double[] var6 = poissonRandCalc(var5, var0, var2);
        return var6;
    }

    private static double[] poissonRandCalc(Random var0, double var1, int var3) {
        double[] var4 = new double[var3];
        double var5 = -1.0D;
        double var7 = 0.0D;
        double var9 = 0.0D;
        double var11 = 0.0D;
        double var13 = 0.0D;
        double var15 = 0.0D;
        double var17 = 0.0D;
        int var19;
        if (var1 < 12.0D) {
            for(var19 = 0; var19 < var3; ++var19) {
                if (var1 != var5) {
                    var5 = var1;
                    var7 = Math.exp(-var1);
                }

                var9 = -1.0D;
                var11 = 1.0D;

                do {
                    ++var9;
                    var11 *= var0.nextDouble();
                } while(var11 > var7);

                var4[var19] = var9;
            }
        } else {
            var19 = 0;

            while(var19 < var3) {
                if (var1 != var5) {
                    var5 = var1;
                    var13 = Math.sqrt(2.0D * var1);
                    var15 = Math.log(var1);
                    var7 = var15 - logGamma(var1 + 1.0D);
                }

                while(true) {
                    var17 = Math.tan(3.141592653589793D * var0.nextDouble());
                    var9 = var13 * var17 + var1;
                    if (var9 >= 0.0D) {
                        var9 = Math.floor(var9);
                        var11 = 0.9D * (1.0D + var17 * var17) * Math.exp(var9 * var15 - logGamma(var9 + 1.0D) - var7);
                        if (var0.nextDouble() <= var11) {
                            var4[var19] = var9;
                            ++var19;
                            break;
                        }
                    }
                }
            }
        }

        return var4;
    }

    public static double chiSquareCDF(double var0, int var2) {
        if (var2 <= 0) {
            throw new IllegalArgumentException("The degrees of freedom [nu], " + var2 + ", must be greater than zero");
        } else {
            return incompleteGamma((double)var2 / 2.0D, var0 / 2.0D);
        }
    }

    public static double chiSquareProb(double var0, int var2) {
        if (var2 <= 0) {
            throw new IllegalArgumentException("The degrees of freedom [nu], " + var2 + ", must be greater than zero");
        } else {
            return incompleteGamma((double)var2 / 2.0D, var0 / 2.0D);
        }
    }

    public static double chiSquareInverseCDF(int var0, double var1) {
        if (var1 >= 0.0D && var1 <= 1.0D) {
            double var3 = 0.0D;
            if (var1 == 0.0D) {
                var3 = 0.0D;
            } else if (var1 == 1.0D) {
                var3 = 1.0D / 0.0;
            } else {
                ChiSquareFunct var5 = new ChiSquareFunct();
                var5.nu = var0;
                double var6 = 1.0E-12D;
                double var8 = 0.0D;
                double var10 = (double)var0 + 10.0D * Math.sqrt(2.0D * (double)var0);
                RealRoot var12 = new RealRoot();
                var12.noLowerBoundExtension();
                var12.setTolerance(var6);
                var12.resetNaNexceptionToTrue();
                var12.suppressLimitReachedMessage();
                var12.suppressNaNmessage();
                var5.cfd = var1;
                var3 = var12.bisect(var5, var8, var10);
            }

            return var3;
        } else {
            throw new IllegalArgumentException("Entered cdf value, " + var1 + ", must lie between 0 and 1 inclusive");
        }
    }

    public static double inverseChiSquareCDF(int var0, double var1) {
        return chiSquareInverseCDF(var0, var1);
    }

    public static double chiSquarePDF(double var0, int var2) {
        if (var2 <= 0) {
            throw new IllegalArgumentException("The degrees of freedom [nu], " + var2 + ", must be greater than zero");
        } else {
            double var3 = (double)var2;
            return Math.pow(0.5D, var3 / 2.0D) * Math.pow(var0, var3 / 2.0D - 1.0D) * Math.exp(-var0 / 2.0D) / gammaFunction(var3 / 2.0D);
        }
    }

    public static double[] chiSquareRand(int var0, int var1) {
        if (var0 <= 0) {
            throw new IllegalArgumentException("The degrees of freedom [nu], " + var0 + ", must be greater than zero");
        } else {
            PsRandom var2 = new PsRandom();
            return var2.chiSquareArray(var0, var1);
        }
    }

    public static double[] chiSquareRand(int var0, int var1, long var2) {
        if (var0 <= 0) {
            throw new IllegalArgumentException("The degrees of freedom [nu], " + var0 + ", must be greater than zero");
        } else {
            PsRandom var4 = new PsRandom(var2);
            return var4.chiSquareArray(var0, var1);
        }
    }

    public static double chiSquareMean(int var0) {
        if (var0 <= 0) {
            throw new IllegalArgumentException("The degrees of freedom [nu], " + var0 + ", must be greater than zero");
        } else {
            return (double)var0;
        }
    }

    public static double chiSquareMode(int var0) {
        if (var0 <= 0) {
            throw new IllegalArgumentException("The degrees of freedom [nu], " + var0 + ", must be greater than zero");
        } else {
            double var1 = 0.0D;
            if (var0 >= 2) {
                var1 = (double)var0 - 2.0D;
            }

            return var1;
        }
    }

    public static double chiSquareStandardDeviation(int var0) {
        return chiSquareStandDev(var0);
    }

    public static double chiSquareStandDev(int var0) {
        if (var0 <= 0) {
            throw new IllegalArgumentException("The degrees of freedom [nu], " + var0 + ", must be greater than zero");
        } else {
            double var1 = (double)var0;
            return Math.sqrt(2.0D * var1);
        }
    }

    public static double chiSquare(double[] var0, double[] var1, double[] var2) {
        int var3 = var0.length;
        int var4 = var1.length;
        int var5 = var2.length;
        if (var3 != var4) {
            throw new IllegalArgumentException("observed array length does not equal the expected array length");
        } else if (var3 != var5) {
            throw new IllegalArgumentException("observed array length does not equal the variance array length");
        } else {
            double var6 = 0.0D;

            for(int var8 = 0; var8 < var3; ++var8) {
                var6 += Fmath.square(var0[var8] - var1[var8]) / var2[var8];
            }

            return var6;
        }
    }

    public static double chiSquareFreq(double[] var0, double[] var1) {
        int var2 = var0.length;
        int var3 = var1.length;
        if (var2 != var3) {
            throw new IllegalArgumentException("observed array length does not equal the expected array length");
        } else {
            double var4 = 0.0D;

            for(int var6 = 0; var6 < var2; ++var6) {
                var4 += Fmath.square(var0[var6] - var1[var6]) / var1[var6];
            }

            return var4;
        }
    }

    public static double chiSquareFreq(int[] var0, int[] var1) {
        int var2 = var0.length;
        int var3 = var1.length;
        if (var2 != var3) {
            throw new IllegalArgumentException("observed array length does not equal the expected array length");
        } else {
            double[] var4 = new double[var2];
            double[] var5 = new double[var2];

            for(int var6 = 0; var6 < var2; ++var6) {
                var4[var6] = (double)var0[var6];
                var5[var6] = (double)var1[var6];
            }

            return chiSquareFreq(var4, var5);
        }
    }

    public static double wilsonHilferty(double var0, int var2) {
        double var3 = 2.0D / (9.0D * (double)var2);
        return (Math.pow(var0 / (double)var2, 0.3333333333333333D) - (1.0D - var3)) / Math.sqrt(var3);
    }

    public static double wilsonHilferty(double var0, double var2) {
        double var4 = 3.0D / Math.sqrt(var2);
        return (Math.pow(var0, 0.3333333333333333D) - 1.0D) * var4 + 1.0D / var4;
    }

    public static double binomialCDF(double var0, int var2, int var3) {
        if (var0 >= 0.0D && var0 <= 1.0D) {
            if (var3 >= 0 && var2 >= 0) {
                if (var3 > var2) {
                    throw new IllegalArgumentException("\nk is greater than n");
                } else {
                    return regularisedBetaFunction((double)var3, (double)(var2 - var3 + 1), var0);
                }
            } else {
                throw new IllegalArgumentException("\nn and k must be greater than or equal to zero");
            }
        } else {
            throw new IllegalArgumentException("\np must lie between 0 and 1");
        }
    }

    public static double binomialProb(double var0, int var2, int var3) {
        if (var0 >= 0.0D && var0 <= 1.0D) {
            if (var3 >= 0 && var2 >= 0) {
                if (var3 > var2) {
                    throw new IllegalArgumentException("\nk is greater than n");
                } else {
                    return regularisedBetaFunction((double)var3, (double)(var2 - var3 + 1), var0);
                }
            } else {
                throw new IllegalArgumentException("\nn and k must be greater than or equal to zero");
            }
        } else {
            throw new IllegalArgumentException("\np must lie between 0 and 1");
        }
    }

    public static double binomialPDF(double var0, int var2, int var3) {
        if (var3 >= 0 && var2 >= 0) {
            if (var3 > var2) {
                throw new IllegalArgumentException("\nk is greater than n");
            } else {
                return Math.floor(0.5D + Math.exp(logFactorial(var2) - logFactorial(var3) - logFactorial(var2 - var3))) * Math.pow(var0, (double)var3) * Math.pow(1.0D - var0, (double)(var2 - var3));
            }
        } else {
            throw new IllegalArgumentException("\nn and k must be greater than or equal to zero");
        }
    }

    public static double binomial(double var0, int var2, int var3) {
        if (var3 >= 0 && var2 >= 0) {
            if (var3 > var2) {
                throw new IllegalArgumentException("\nk is greater than n");
            } else {
                return Math.floor(0.5D + Math.exp(logFactorial(var2) - logFactorial(var3) - logFactorial(var2 - var3))) * Math.pow(var0, (double)var3) * Math.pow(1.0D - var0, (double)(var2 - var3));
            }
        } else {
            throw new IllegalArgumentException("\nn and k must be greater than or equal to zero");
        }
    }

    public static double binomialCoeff(int var0, int var1) {
        if (var1 >= 0 && var0 >= 0) {
            if (var1 > var0) {
                throw new IllegalArgumentException("\nk is greater than n");
            } else {
                return Math.floor(0.5D + Math.exp(logFactorial(var0) - logFactorial(var1) - logFactorial(var0 - var1)));
            }
        } else {
            throw new IllegalArgumentException("\nn and k must be greater than or equal to zero");
        }
    }

    public double[] binomialRand(double var1, int var3, int var4) {
        if (var3 < var4) {
            throw new IllegalArgumentException("Number of deviates requested, " + var4 + ", must be less than the number of trials, " + var3);
        } else if (var1 >= 0.0D && var1 <= 1.0D) {
            double[] var5 = new double[var4];
            Random var6 = new Random();
            double var7 = 0.0D;
            double var9 = 0.0D;
            double var11 = 0.0D;
            double var13 = 0.0D;
            double var15 = 0.0D;
            double var17 = -1.0D;
            double var19 = -1.0D;
            double var21 = -1.0D;
            int var23 = -1;
            double var24 = 0.0D;
            double var26 = 0.0D;
            double var28 = 0.0D;
            double var30 = 0.0D;
            double var33 = var1;

            for(int var35 = 0; var35 < var4; ++var35) {
                var13 = var33 <= 0.5D ? var33 : 1.0D - var33;
                var9 = (double)var3 * var13;
                int var32;
                if (var3 < 25) {
                    var7 = 0.0D;

                    for(var32 = 1; var32 <= var3; ++var32) {
                        if (var6.nextDouble() < var13) {
                            ++var7;
                        }
                    }
                } else {
                    double var36;
                    if (var9 < 1.0D) {
                        var36 = Math.exp(-var9);
                        var11 = 1.0D;

                        for(var32 = 0; var32 <= var3; ++var32) {
                            var11 *= var6.nextDouble();
                            if (var11 < var36) {
                                break;
                            }
                        }

                        var7 = (double)(var32 <= var3 ? var32 : var3);
                    } else {
                        if (var3 != var23) {
                            var24 = (double)var3;
                            var26 = logGamma(var24 + 1.0D);
                            var23 = var3;
                        }

                        if (var13 != var17) {
                            var19 = 1.0D - var13;
                            var15 = Math.log(var13);
                            var21 = Math.log(var19);
                            var17 = var13;
                        }

                        var36 = Math.sqrt(2.0D * var9 * var19);

                        while(true) {
                            do {
                                double var38 = 3.141592653589793D * var6.nextDouble();
                                var28 = Math.tan(var38);
                                var30 = var36 * var28 + var9;
                            } while(var30 < 0.0D);

                            if (var30 < var24 + 1.0D) {
                                var30 = Math.floor(var30);
                                var11 = 1.2D * var36 * (1.0D + var28 * var28) * Math.exp(var26 - logGamma(var30 + 1.0D) - logGamma(var24 - var30 + 1.0D) + var30 * var15 + (var24 - var30) * var21);
                                if (var6.nextDouble() <= var11) {
                                    var7 = var30;
                                    break;
                                }
                            }
                        }
                    }
                }

                if (var13 != var33) {
                    var7 = (double)var3 - var7;
                }

                var5[var35] = var7;
            }

            return var5;
        } else {
            throw new IllegalArgumentException("The probablity provided, " + var1 + ", must lie between 0 and 1)");
        }
    }

    public double[] binomialRand(double var1, int var3, int var4, long var5) {
        if (var3 < var4) {
            throw new IllegalArgumentException("Number of deviates requested, " + var4 + ", must be less than the number of trials, " + var3);
        } else if (var1 >= 0.0D && var1 <= 1.0D) {
            double[] var7 = new double[var4];
            Random var8 = new Random(var5);
            double var9 = 0.0D;
            double var11 = 0.0D;
            double var13 = 0.0D;
            double var15 = 0.0D;
            double var17 = 0.0D;
            double var19 = -1.0D;
            double var21 = -1.0D;
            double var23 = -1.0D;
            int var25 = -1;
            double var26 = 0.0D;
            double var28 = 0.0D;
            double var30 = 0.0D;
            double var32 = 0.0D;
            double var35 = var1;

            for(int var37 = 0; var37 < var4; ++var37) {
                var15 = var35 <= 0.5D ? var35 : 1.0D - var35;
                var11 = (double)var3 * var15;
                int var34;
                if (var3 < 25) {
                    var9 = 0.0D;

                    for(var34 = 1; var34 <= var3; ++var34) {
                        if (var8.nextDouble() < var15) {
                            ++var9;
                        }
                    }
                } else {
                    double var38;
                    if (var11 < 1.0D) {
                        var38 = Math.exp(-var11);
                        var13 = 1.0D;

                        for(var34 = 0; var34 <= var3; ++var34) {
                            var13 *= var8.nextDouble();
                            if (var13 < var38) {
                                break;
                            }
                        }

                        var9 = (double)(var34 <= var3 ? var34 : var3);
                    } else {
                        if (var3 != var25) {
                            var26 = (double)var3;
                            var28 = logGamma(var26 + 1.0D);
                            var25 = var3;
                        }

                        if (var15 != var19) {
                            var21 = 1.0D - var15;
                            var17 = Math.log(var15);
                            var23 = Math.log(var21);
                            var19 = var15;
                        }

                        var38 = Math.sqrt(2.0D * var11 * var21);

                        while(true) {
                            do {
                                double var40 = 3.141592653589793D * var8.nextDouble();
                                var30 = Math.tan(var40);
                                var32 = var38 * var30 + var11;
                            } while(var32 < 0.0D);

                            if (var32 < var26 + 1.0D) {
                                var32 = Math.floor(var32);
                                var13 = 1.2D * var38 * (1.0D + var30 * var30) * Math.exp(var28 - logGamma(var32 + 1.0D) - logGamma(var26 - var32 + 1.0D) + var32 * var17 + (var26 - var32) * var23);
                                if (var8.nextDouble() <= var13) {
                                    var9 = var32;
                                    break;
                                }
                            }
                        }
                    }
                }

                if (var15 != var35) {
                    var9 = (double)var3 - var9;
                }

                var7[var37] = var9;
            }

            return var7;
        } else {
            throw new IllegalArgumentException("The probablity provided, " + var1 + ", must lie between 0 and 1)");
        }
    }

    public static double fCompCDF(double var0, int var2, int var3) {
        boolean var4 = true;
        if (var2 <= 0) {
            System.out.println("Method: fCompCDF\nThe degrees of freedom, nu1, " + var2 + ", must be greater than zero\nNaN returned");
            var4 = false;
        }

        if (var3 <= 0) {
            System.out.println("Method: fCompCDF\nThe degrees of freedom, nu2, " + var3 + ", must be greater than zero\nNaN returned");
            var4 = false;
        }

        if (var0 < 0.0D) {
            System.out.println("Method: fCompCDF\nThe F-ratio, " + var0 + ", must be greater than or equal to zero\nNaN returned");
            var4 = false;
        }

        if (var4) {
            double var5 = (double)var2;
            double var7 = (double)var3;
            double var9 = var7 / (var7 + var5 * var0);
            return regularisedBetaFunction((double)var3 / 2.0D, (double)var2 / 2.0D, var9);
        } else {
            return 0.0D / 0.0;
        }
    }

    public static double fTestProb(double var0, int var2, int var3) {
        boolean var4 = true;
        if (var2 <= 0) {
            System.out.println("Method: fTestProb\nThe degrees of freedom, nu1, " + var2 + ", must be greater than zero\nNaN returned");
            var4 = false;
        }

        if (var3 <= 0) {
            System.out.println("Method: fTestProb\nThe degrees of freedom, nu2, " + var3 + ", must be greater than zero\nNaN returned");
            var4 = false;
        }

        if (var0 < 0.0D) {
            System.out.println("Method: fTestProb\nThe F-ratio, " + var0 + ", must be greater than or equal to zero\nNaN returned");
            var4 = false;
        }

        if (var4) {
            double var5 = (double)var2;
            double var7 = (double)var3;
            double var9 = var7 / (var7 + var5 * var0);
            return regularisedBetaFunction((double)var3 / 2.0D, (double)var2 / 2.0D, var9);
        } else {
            return 0.0D / 0.0;
        }
    }

    public static double fCompCDF(double var0, int var2, double var3, int var5) {
        boolean var6 = true;
        if (var2 <= 0) {
            System.out.println("Method: fCompCDF\nThe degrees of freedom, nu1, " + var2 + ", must be greater than zero\nNaN returned");
            var6 = false;
        }

        if (var5 <= 0) {
            System.out.println("Method: fCompCDF\nThe degrees of freedom, nu2, " + var5 + ", must be greater than zero\nNaN returned");
            var6 = false;
        }

        if (var0 < 0.0D) {
            System.out.println("Method: fCompCDF\nThe variance, " + var0 + ", must be greater than or equal to zero\nNaN returned");
            var6 = false;
        }

        if (var3 <= 0.0D) {
            System.out.println("Method: fCompCDF\nThe variance, " + var3 + ", must be greater than zero\nNaN returned");
            var6 = false;
        }

        if (var6) {
            double var7 = var0 / var3;
            double var9 = (double)var2;
            double var11 = (double)var5;
            double var13 = var11 / (var11 + var9 * var7);
            return regularisedBetaFunction((double)var5 / 2.0D, (double)var2 / 2.0D, var13);
        } else {
            return 0.0D / 0.0;
        }
    }

    public static double fTestProb(double var0, int var2, double var3, int var5) {
        boolean var6 = true;
        if (var2 <= 0) {
            System.out.println("Method: fCompCDF\nThe degrees of freedom, nu1, " + var2 + ", must be greater than zero\nNaN returned");
            var6 = false;
        }

        if (var5 <= 0) {
            System.out.println("Method: fCompCDF\nThe degrees of freedom, nu2, " + var5 + ", must be greater than zero\nNaN returned");
            var6 = false;
        }

        if (var0 < 0.0D) {
            System.out.println("Method: fCompCDF\nThe variance, " + var0 + ", must be greater than or equal to zero\nNaN returned");
            var6 = false;
        }

        if (var3 <= 0.0D) {
            System.out.println("Method: fCompCDF\nThe variance, " + var3 + ", must be greater than zero\nNaN returned");
            var6 = false;
        }

        if (var6) {
            double var7 = var0 / var3;
            double var9 = (double)var2;
            double var11 = (double)var5;
            double var13 = var11 / (var11 + var9 * var7);
            return regularisedBetaFunction((double)var5 / 2.0D, (double)var2 / 2.0D, var13);
        } else {
            return 0.0D / 0.0;
        }
    }

    public static double fDistributionInverseCDF(int var0, int var1, double var2) {
        if (var2 >= 0.0D && var2 <= 1.0D) {
            double var4 = 0.0D;
            if (var2 == 0.0D) {
                var4 = 0.0D;
            } else if (var2 == 1.0D) {
                var4 = 1.0D / 0.0;
            } else {
                FdistribtionFunct var6 = new FdistribtionFunct();
                var6.nu1 = var0;
                var6.nu2 = var1;
                double var7 = 1.0E-12D;
                double var9 = 0.0D;
                double var11 = 2.0D;
                RealRoot var13 = new RealRoot();
                var13.noLowerBoundExtension();
                var13.setTolerance(var7);
                var13.resetNaNexceptionToTrue();
                var13.suppressLimitReachedMessage();
                var13.suppressNaNmessage();
                var6.cfd = var2;
                var4 = var13.bisect(var6, var9, var11);
            }

            return var4;
        } else {
            throw new IllegalArgumentException("Entered cdf value, " + var2 + ", must lie between 0 and 1 inclusive");
        }
    }

    public static double[] fDistributionOrderStatisticMedians(int var0, int var1, int var2) {
        double var3 = (double)var2;
        double[] var5 = new double[var2];
        double[] var6 = uniformOrderStatisticMedians(var2);

        for(int var7 = 0; var7 < var2; ++var7) {
            var5[var7] = fDistributionInverseCDF(var0, var1, var6[var7]);
        }

        Stat var12 = new Stat(var5);
        double var8 = var12.mean();
        double var10 = var12.standardDeviation();
        var5 = scale(var5, var8, var10);
        return var5;
    }

    public static double fTestValueGivenFprob(double var0, int var2, int var3) {
        byte var4 = 100;
        double[] var5 = new double[var4];
        var5[0] = 1.0E-4D;
        var5[var4 - 1] = 10000.0D;
        double var6 = (Fmath.log10(var5[var4 - 1]) - Fmath.log10(var5[0])) / (double)(var4 - 1);

        for(int var8 = 1; var8 < var4 - 1; ++var8) {
            var5[var8] = Math.pow(10.0D, Fmath.log10(var5[var8 - 1]) + var6);
        }

        double[] var19 = new double[var4];

        for(int var9 = 0; var9 < var4; ++var9) {
            var19[var9] = fTestProb(var5[var9], var2, var3);
        }

        double var20 = 0.0D;
        double var11 = 0.0D;
        double var13 = 0.0D;
        boolean var15 = true;
        boolean var16 = true;
        int var17 = 0;
        byte var18 = 0;

        while(var15) {
            if (var0 == var19[var17]) {
                var20 = var5[var17];
                var15 = false;
                var16 = false;
            } else if (var0 > var19[var17]) {
                var15 = false;
                if (var17 > 0) {
                    var11 = var5[var17 - 1];
                    var13 = var5[var17];
                    var18 = -1;
                } else {
                    var11 = var5[var17] / 10.0D;
                    var13 = var5[var17];
                }
            } else {
                ++var17;
                if (var17 > var4 - 1) {
                    var15 = false;
                    var11 = var5[var17 - 1];
                    var13 = 10.0D * var5[var17 - 1];
                    var18 = 1;
                }
            }
        }

        if (var16) {
            var20 = fTestBisect(var0, var11, var13, var2, var3, var18);
        }

        return var20;
    }

    private static double fTestBisect(double var0, double var2, double var4, int var6, int var7, int var8) {
        double var9 = var0 - fTestProb(var2, var6, var7);
        double var11 = var0 - fTestProb(var4, var6, var7);
        double var13 = 0.0D;
        double var15 = 0.0D;
        int var17 = 0;
        short var18 = 1000;
        double var19 = var0 * 1.0E-6D;
        boolean var21 = true;
        boolean var22 = true;

        while(var21) {
            if (var9 * var11 > 0.0D) {
                if (var8 < 0) {
                    ++var17;
                    if (var17 > 100) {
                        System.out.println("Class: Stats\nMethod: fTestBisect\nProbability higher than range covered\nF-test value is less than " + var2);
                        System.out.println("This value was returned");
                        var13 = var2;
                        var21 = false;
                        var22 = false;
                    }

                    var2 /= 10.0D;
                    var9 = var0 - fTestProb(var2, var6, var7);
                } else {
                    ++var17;
                    if (var17 > 100) {
                        System.out.println("Class: Stats\nMethod: fTestBisect\nProbability lower than range covered\nF-test value is greater than " + var4);
                        System.out.println("This value was returned");
                        var13 = var4;
                        var21 = false;
                        var22 = false;
                    }

                    var4 *= 10.0D;
                    var11 = var0 - fTestProb(var4, var6, var7);
                }
            } else {
                var21 = false;
            }

            int var23 = 0;

            while(var22) {
                var13 = (var2 + var4) / 2.0D;
                var15 = var0 - fTestProb(var13, var6, var7);
                if (Math.abs(var15) < var19) {
                    var22 = false;
                } else {
                    ++var23;
                    if (var23 > var18) {
                        System.out.println("Class: Stats\nMethod: fTestBisect\nmaximum number of iterations exceeded\ncurrent value of F-test value returned");
                        var22 = false;
                    }

                    if (var15 * var11 > 0.0D) {
                        var11 = var15;
                        var4 = var13;
                    } else {
                        var9 = var15;
                        var2 = var13;
                    }
                }
            }
        }

        return var13;
    }

    public double fPDF(double var1, int var3, int var4) {
        double var5 = Math.pow((double)var3 * var1, (double)var3) * Math.pow((double)var4, (double)var4);
        double var7 = (double)var3;
        double var9 = (double)var4;
        var5 /= Math.pow(var7 * var1 + var9, var7 + var9);
        var5 = Math.sqrt(var5);
        double var11 = var1 * betaFunction(var7 / 2.0D, var9 / 2.0D);
        return var5 / var11;
    }

    public double fPDF(double var1, int var3, double var4, int var6) {
        return this.fPDF(var1 / var4, var3, var6);
    }

    public static double[] fRand(int var0, int var1, int var2) {
        if (var0 <= 0) {
            throw new IllegalArgumentException("The degrees of freedom [nu1], " + var0 + ", must be greater than zero");
        } else if (var1 <= 0) {
            throw new IllegalArgumentException("The degrees of freedom [nu2], " + var1 + ", must be greater than zero");
        } else {
            PsRandom var3 = new PsRandom();
            return var3.fArray(var0, var1, var2);
        }
    }

    public static double[] fRand(int var0, int var1, int var2, long var3) {
        if (var0 <= 0) {
            throw new IllegalArgumentException("The degrees of freedom [nu1], " + var0 + ", must be greater than zero");
        } else if (var1 <= 0) {
            throw new IllegalArgumentException("The degrees of freedom [nu2], " + var1 + ", must be greater than zero");
        } else {
            PsRandom var5 = new PsRandom(var3);
            return var5.fArray(var0, var1, var2);
        }
    }

    public static double studentst(double var0, int var2) {
        return studentT(var0, var2);
    }

    public static double studentT(double var0, int var2) {
        if (var0 != var0) {
            throw new IllegalArgumentException("argument tValue is not a number (NaN)");
        } else {
            double var3 = (double)var2;
            double var5 = (var3 + 1.0D) / 2.0D;
            return gamma(var5) / gamma(var3 / 2.0D) / Math.sqrt(var3 * 3.141592653589793D) * Math.pow(1.0D + var0 * var0 / var3, -var5);
        }
    }

    public static double studentstPDF(double var0, int var2) {
        return studentTpdf(var0, var2);
    }

    public static double studentTpdf(double var0, int var2) {
        if (var0 != var0) {
            throw new IllegalArgumentException("argument tValue is not a number (NaN)");
        } else {
            double var3 = (double)var2;
            double var5 = (var3 + 1.0D) / 2.0D;
            return gamma(var5) / gamma(var3 / 2.0D) / Math.sqrt(var3 * 3.141592653589793D) * Math.pow(1.0D + var0 * var0 / var3, -var5);
        }
    }

    public static double studentTPDF(double var0, int var2) {
        if (var0 != var0) {
            throw new IllegalArgumentException("argument tValue is not a number (NaN)");
        } else {
            double var3 = (double)var2;
            double var5 = (var3 + 1.0D) / 2.0D;
            return gamma(var5) / gamma(var3 / 2.0D) / Math.sqrt(var3 * 3.141592653589793D) * Math.pow(1.0D + var0 * var0 / var3, -var5);
        }
    }

    public static double studentstCDF(double var0, int var2) {
        return studentTcdf(var0, var2);
    }

    public static double studentTProb(double var0, int var2) {
        if (var0 != var0) {
            throw new IllegalArgumentException("argument tValue is not a number (NaN)");
        } else if (var0 == 1.0D / 0.0) {
            return 1.0D;
        } else if (var0 == -1.0D / 0.0) {
            return 0.0D;
        } else {
            double var3 = (double)var2;
            double var5 = var3 / (var3 + var0 * var0);
            return 0.5D * (1.0D + (regularisedBetaFunction(var3 / 2.0D, 0.5D, 1.0D) - regularisedBetaFunction(var3 / 2.0D, 0.5D, var5)) * Fmath.sign(var0));
        }
    }

    public static double studentTcdf(double var0, int var2) {
        if (var0 != var0) {
            throw new IllegalArgumentException("argument tValue is not a number (NaN)");
        } else if (var0 == 1.0D / 0.0) {
            return 1.0D;
        } else if (var0 == -1.0D / 0.0) {
            return 0.0D;
        } else {
            double var3 = (double)var2;
            double var5 = var3 / (var3 + var0 * var0);
            return 0.5D * (1.0D + (regularisedBetaFunction(var3 / 2.0D, 0.5D, 1.0D) - regularisedBetaFunction(var3 / 2.0D, 0.5D, var5)) * Fmath.sign(var0));
        }
    }

    public static double studentTCDF(double var0, int var2) {
        if (var0 != var0) {
            throw new IllegalArgumentException("argument tValue is not a number (NaN)");
        } else if (var0 == 1.0D / 0.0) {
            return 1.0D;
        } else if (var0 == -1.0D / 0.0) {
            return 0.0D;
        } else {
            double var3 = (double)var2;
            double var5 = var3 / (var3 + var0 * var0);
            return 0.5D * (1.0D + (regularisedBetaFunction(var3 / 2.0D, 0.5D, 1.0D) - regularisedBetaFunction(var3 / 2.0D, 0.5D, var5)) * Fmath.sign(var0));
        }
    }

    public static double studentTcdf(double var0, double var2, int var4) {
        if (var0 != var0) {
            throw new IllegalArgumentException("argument tLowerValue is not a number (NaN)");
        } else if (var2 != var2) {
            throw new IllegalArgumentException("argument tUpperValue is not a number (NaN)");
        } else if (var2 == 1.0D / 0.0) {
            if (var0 == -1.0D / 0.0) {
                return 1.0D;
            } else {
                return var0 == 1.0D / 0.0 ? 0.0D : 1.0D - studentTcdf(var0, var4);
            }
        } else if (var0 == -1.0D / 0.0) {
            return var2 == -1.0D / 0.0 ? 0.0D : studentTcdf(var2, var4);
        } else {
            return studentTcdf(var2, var4) - studentTcdf(var0, var4);
        }
    }

    public static double pValue(double var0, int var2) {
        if (var0 != var0) {
            throw new IllegalArgumentException("argument tValue is not a number (NaN)");
        } else {
            double var3 = Math.abs(var0);
            return 1.0D - studentTcdf(-var3, var3, var2);
        }
    }

    public static double studentstValue(double var0, int var2) {
        StudentsTvalueFunct var3 = new StudentsTvalueFunct();
        var3.setCfd(var0);
        var3.setDof(var2);
        RealRoot var4 = new RealRoot();
        double var5 = 0.0D;
        double var7 = 1.0D;
        double var9 = 1.0E-10D;
        var4.setTolerance(var9);
        return var4.bisect(var3, var5, var7);
    }

    public static double studentstMean(int var0) {
        return studentTmean(var0);
    }

    public static double studentTmean(int var0) {
        double var1 = 0.0D / 0.0;
        if (var0 > 1) {
            var1 = 0.0D;
        }

        return var1;
    }

    public static double studentstMedian() {
        return 0.0D;
    }

    public static double studentTmedian() {
        return 0.0D;
    }

    public static double studentstMode() {
        return 0.0D;
    }

    public static double studentTmode() {
        return 0.0D;
    }

    public static double studentstStandardDeviation(int var0) {
        return studentTstandDev(var0);
    }

    public static double studentTstandDev(int var0) {
        double var1 = 1.0D / 0.0;
        if (var0 > 2) {
            var1 = Math.sqrt((double)(var0 / (1 - var0)));
        }

        return var1;
    }

    public static double probAtn(double var0, int var2) {
        double var3 = (double)var2;
        double var5 = var3 / (var3 + var0 * var0);
        return 1.0D - regularisedBetaFunction(var3 / 2.0D, 0.5D, var5);
    }

    public static double[] studentstRand(int var0, int var1) {
        return studentTRand(var0, var1);
    }

    public static double[] studentTRand(int var0, int var1) {
        PsRandom var2 = new PsRandom();
        return var2.studentTarray(var0, var1);
    }

    public static double[] studentTrand(int var0, int var1) {
        PsRandom var2 = new PsRandom();
        return var2.studentTarray(var0, var1);
    }

    public static double[] studentstRand(int var0, int var1, long var2) {
        return studentTrand(var0, var1, var2);
    }

    public static double[] studentTrand(int var0, int var1, long var2) {
        PsRandom var4 = new PsRandom(var2);
        return var4.studentTarray(var0, var1);
    }

    public static double[] studentTRand(int var0, int var1, long var2) {
        PsRandom var4 = new PsRandom(var2);
        return var4.studentTarray(var0, var1);
    }

    public static double gumbelMinProbCDF(double var0, double var2, double var4) {
        if (var2 < 0.0D) {
            throw new IllegalArgumentException("sigma must be positive");
        } else {
            double var6 = -(var4 - var0) / var2;
            return Math.exp(-Math.exp(var6));
        }
    }

    public static double gumbelMinProb(double var0, double var2, double var4) {
        if (var2 < 0.0D) {
            throw new IllegalArgumentException("sigma must be positive");
        } else {
            double var6 = -(var4 - var0) / var2;
            return Math.exp(-Math.exp(var6));
        }
    }

    public static double gumbelMaxCDF(double var0, double var2, double var4) {
        if (var2 < 0.0D) {
            throw new IllegalArgumentException("sigma must be positive");
        } else {
            double var6 = -(var4 - var0) / var2;
            return 1.0D - Math.exp(-Math.exp(var6));
        }
    }

    public static double gumbelMaxProb(double var0, double var2, double var4) {
        if (var2 < 0.0D) {
            throw new IllegalArgumentException("sigma must be positive");
        } else {
            double var6 = -(var4 - var0) / var2;
            return 1.0D - Math.exp(-Math.exp(var6));
        }
    }

    public static double gumbelMaxInverseCDF(double var0, double var2, double var4) {
        if (var4 >= 0.0D && var4 <= 1.0D) {
            double var6 = 0.0D;
            if (var4 == 0.0D) {
                var6 = -1.0D / 0.0;
            } else if (var4 == 1.0D) {
                var6 = 1.0D / 0.0;
            } else {
                var6 = var0 - var2 * Math.log(Math.log(1.0D / var4));
            }

            return var6;
        } else {
            throw new IllegalArgumentException("Entered cdf value, " + var4 + ", must lie between 0 and 1 inclusive");
        }
    }

    public static double gumbelMinCDF(double var0, double var2, double var4, double var6) {
        if (var2 < 0.0D) {
            throw new IllegalArgumentException("sigma must be positive");
        } else {
            double var8 = -(var4 - var0) / var2;
            double var10 = -(var6 - var0) / var2;
            double var12 = Math.exp(-Math.exp(var8));
            double var14 = Math.exp(-Math.exp(var10));
            return var14 - var12;
        }
    }

    public static double gumbelMinProb(double var0, double var2, double var4, double var6) {
        if (var2 < 0.0D) {
            throw new IllegalArgumentException("sigma must be positive");
        } else {
            double var8 = -(var4 - var0) / var2;
            double var10 = -(var6 - var0) / var2;
            double var12 = Math.exp(-Math.exp(var8));
            double var14 = Math.exp(-Math.exp(var10));
            return var14 - var12;
        }
    }

    public static double gumbelMaxCDF(double var0, double var2, double var4, double var6) {
        if (var2 < 0.0D) {
            throw new IllegalArgumentException("sigma must be positive");
        } else {
            double var8 = (var4 - var0) / var2;
            double var10 = (var6 - var0) / var2;
            double var12 = -Math.exp(-Math.exp(var8));
            double var14 = -Math.exp(-Math.exp(var10));
            return var14 - var12;
        }
    }

    public static double gumbelMaxProb(double var0, double var2, double var4, double var6) {
        if (var2 < 0.0D) {
            throw new IllegalArgumentException("sigma must be positive");
        } else {
            double var8 = (var4 - var0) / var2;
            double var10 = (var6 - var0) / var2;
            double var12 = -Math.exp(-Math.exp(var8));
            double var14 = -Math.exp(-Math.exp(var10));
            return var14 - var12;
        }
    }

    public static double gumbelMinInverseCDF(double var0, double var2, double var4) {
        if (var4 >= 0.0D && var4 <= 1.0D) {
            double var6 = 0.0D;
            if (var4 == 0.0D) {
                var6 = -1.0D / 0.0;
            } else if (var4 == 1.0D) {
                var6 = 1.0D / 0.0;
            } else {
                var6 = var0 + var2 * Math.log(Math.log(1.0D / (1.0D - var4)));
            }

            return var6;
        } else {
            throw new IllegalArgumentException("Entered cdf value, " + var4 + ", must lie between 0 and 1 inclusive");
        }
    }

    public static double gumbelMinPDF(double var0, double var2, double var4) {
        if (var2 < 0.0D) {
            throw new IllegalArgumentException("sigma must be positive");
        } else {
            double var6 = (var4 - var0) / var2;
            return 1.0D / var2 * Math.exp(var6) * Math.exp(-Math.exp(var6));
        }
    }

    public static double gumbelMin(double var0, double var2, double var4) {
        if (var2 < 0.0D) {
            throw new IllegalArgumentException("sigma must be positive");
        } else {
            double var6 = (var4 - var0) / var2;
            return 1.0D / var2 * Math.exp(var6) * Math.exp(-Math.exp(var6));
        }
    }

    public static double gumbelMaxPDF(double var0, double var2, double var4) {
        if (var2 < 0.0D) {
            throw new IllegalArgumentException("sigma must be positive");
        } else {
            double var6 = -(var4 - var0) / var2;
            return 1.0D / var2 * Math.exp(var6) * Math.exp(-Math.exp(var6));
        }
    }

    public static double gumbelMax(double var0, double var2, double var4) {
        if (var2 < 0.0D) {
            throw new IllegalArgumentException("sigma must be positive");
        } else {
            double var6 = -(var4 - var0) / var2;
            return 1.0D / var2 * Math.exp(var6) * Math.exp(-Math.exp(var6));
        }
    }

    public static double[] gumbelMinRand(double var0, double var2, int var4) {
        double[] var5 = new double[var4];
        Random var6 = new Random();

        for(int var7 = 0; var7 < var4; ++var7) {
            var5[var7] = Math.log(Math.log(1.0D / (1.0D - var6.nextDouble()))) * var2 + var0;
        }

        return var5;
    }

    public static double[] gumbelMinRand(double var0, double var2, int var4, long var5) {
        double[] var7 = new double[var4];
        Random var8 = new Random(var5);

        for(int var9 = 0; var9 < var4; ++var9) {
            var7[var9] = Math.log(Math.log(1.0D / (1.0D - var8.nextDouble()))) * var2 + var0;
        }

        return var7;
    }

    public static double[] gumbelMaxRand(double var0, double var2, int var4) {
        double[] var5 = new double[var4];
        Random var6 = new Random();

        for(int var7 = 0; var7 < var4; ++var7) {
            var5[var7] = var0 - Math.log(Math.log(1.0D / (1.0D - var6.nextDouble()))) * var2;
        }

        return var5;
    }

    public static double[] gumbelMaxRand(double var0, double var2, int var4, long var5) {
        double[] var7 = new double[var4];
        Random var8 = new Random(var5);

        for(int var9 = 0; var9 < var4; ++var9) {
            var7[var9] = var0 - Math.log(Math.log(1.0D / (1.0D - var8.nextDouble()))) * var2;
        }

        return var7;
    }

    public static double[] gumbelMinOrderStatisticMedians(double var0, double var2, int var4) {
        double var5 = (double)var4;
        double[] var7 = new double[var4];
        double[] var8 = uniformOrderStatisticMedians(var4);

        for(int var9 = 0; var9 < var4; ++var9) {
            var7[var9] = gumbelMinInverseCDF(var0, var2, var8[var9]);
        }

        return var7;
    }

    public static double[] gumbelMaxOrderStatisticMedians(double var0, double var2, int var4) {
        double var5 = (double)var4;
        double[] var7 = new double[var4];
        double[] var8 = uniformOrderStatisticMedians(var4);

        for(int var9 = 0; var9 < var4; ++var9) {
            var7[var9] = gumbelMaxInverseCDF(var0, var2, var8[var9]);
        }

        return var7;
    }

    public static double gumbelMinMean(double var0, double var2) {
        return var0 - var2 * 0.5772156649015627D;
    }

    public static double gumbelMaxMean(double var0, double var2) {
        return var0 + var2 * 0.5772156649015627D;
    }

    public static double gumbelMinStandardDeviation(double var0) {
        return var0 * 3.141592653589793D / Math.sqrt(6.0D);
    }

    public static double gumbelMinStandDev(double var0) {
        return var0 * 3.141592653589793D / Math.sqrt(6.0D);
    }

    public static double gumbelMaxStandardDeviation(double var0) {
        return var0 * 3.141592653589793D / Math.sqrt(6.0D);
    }

    public static double gumbelMaxStandDev(double var0) {
        return var0 * 3.141592653589793D / Math.sqrt(6.0D);
    }

    public static double gumbelMinMode(double var0, double var2) {
        return var0;
    }

    public static double gumbelMaxMode(double var0, double var2) {
        return var0;
    }

    public static double gumbelMinMedian(double var0, double var2) {
        return var0 + var2 * Math.log(Math.log(2.0D));
    }

    public static double gumbelMaxMedian(double var0, double var2) {
        return var0 - var2 * Math.log(Math.log(2.0D));
    }

    public static double frechetProb(double var0, double var2, double var4, double var6) {
        double var8 = (var6 - var0) / var2;
        double var10 = 0.0D;
        if (var8 > 0.0D) {
            var10 = Math.exp(-Math.pow(var8, -var4));
        }

        return var10;
    }

    public static double frechetCDF(double var0, double var2, double var4, double var6, double var8) {
        double var10 = (var6 - var0) / var2;
        double var12 = (var8 - var0) / var2;
        double var14 = 0.0D;
        double var16 = 0.0D;
        if (var10 >= 0.0D) {
            var14 = Math.exp(-Math.pow(var10, -var4));
        }

        if (var12 >= 0.0D) {
            var16 = Math.exp(-Math.pow(var12, -var4));
        }

        return var16 - var14;
    }

    public static double frechetProb(double var0, double var2, double var4, double var6, double var8) {
        double var10 = (var6 - var0) / var2;
        double var12 = (var8 - var0) / var2;
        double var14 = 0.0D;
        double var16 = 0.0D;
        if (var10 >= 0.0D) {
            var14 = Math.exp(-Math.pow(var10, -var4));
        }

        if (var12 >= 0.0D) {
            var16 = Math.exp(-Math.pow(var12, -var4));
        }

        return var16 - var14;
    }

    public static double frechetInverseCDF(double var0, double var2, double var4, double var6) {
        if (var6 >= 0.0D && var6 <= 1.0D) {
            double var8 = 0.0D;
            if (var6 == 0.0D) {
                var8 = -1.0D / 0.0;
            } else if (var6 == 1.0D) {
                var8 = 1.0D / 0.0;
            } else {
                var8 = var0 + var2 * Math.pow(Math.log(1.0D / var6), -1.0D / var4);
            }

            return var8;
        } else {
            throw new IllegalArgumentException("Entered cdf value, " + var6 + ", must lie between 0 and 1 inclusive");
        }
    }

    public static double frechetInverseCDF(double var0, double var2, double var4) {
        return frechetInverseCDF(0.0D, var0, var2, var4);
    }

    public static double frechetInverseCDF(double var0, double var2) {
        return frechetInverseCDF(0.0D, 1.0D, var0, var2);
    }

    public static double frechetPDF(double var0, double var2, double var4, double var6) {
        double var8 = (var6 - var0) / var2;
        double var10 = 0.0D;
        if (var8 >= 0.0D) {
            var10 = var4 / var2 * Math.pow(var8, -var4 - 1.0D) * Math.exp(-Math.pow(var8, -var4));
        }

        return var10;
    }

    public static double frechet(double var0, double var2, double var4, double var6) {
        double var8 = (var6 - var0) / var2;
        double var10 = 0.0D;
        if (var8 >= 0.0D) {
            var10 = var4 / var2 * Math.pow(var8, -var4 - 1.0D) * Math.exp(-Math.pow(var8, -var4));
        }

        return var10;
    }

    public static double[] frechetOrderStatisticMedians(double var0, double var2, double var4, int var6) {
        double var7 = (double)var6;
        double[] var9 = new double[var6];
        double[] var10 = uniformOrderStatisticMedians(var6);

        for(int var11 = 0; var11 < var6; ++var11) {
            var9[var11] = frechetInverseCDF(var0, var2, var4, var10[var11]);
        }

        return var9;
    }

    public static double[] frechetOrderStatisticMedians(double var0, double var2, int var4) {
        return frechetOrderStatisticMedians(0.0D, var0, var2, var4);
    }

    public static double[] frechetOrderStatisticMedians(double var0, int var2) {
        return frechetOrderStatisticMedians(0.0D, 1.0D, var0, var2);
    }

    public static double frechetMean(double var0, double var2, double var4) {
        double var6 = 0.0D / 0.0;
        if (var4 > 1.0D) {
            var6 = var0 + var2 * gamma(1.0D - 1.0D / var4);
        }

        return var6;
    }

    public static double frechetStandardDeviation(double var0, double var2) {
        return frechetStandDev(var0, var2);
    }

    public static double frechetStandDev(double var0, double var2) {
        double var4 = 0.0D / 0.0;
        if (var2 > 2.0D) {
            var4 = gamma(1.0D - 2.0D / var2) - Fmath.square(gamma(1.0D - 1.0D / var2));
            var4 = var0 * Math.sqrt(var4);
        }

        return var4;
    }

    public static double frechetMode(double var0, double var2, double var4) {
        return var0 + var2 * Math.pow(var4 / (1.0D + var4), 1.0D / var4);
    }

    public static double[] frechetRand(double var0, double var2, double var4, int var6) {
        double[] var7 = new double[var6];
        Random var8 = new Random();

        for(int var9 = 0; var9 < var6; ++var9) {
            var7[var9] = Math.pow(1.0D / Math.log(1.0D / var8.nextDouble()), 1.0D / var4) * var2 + var0;
        }

        return var7;
    }

    public static double[] frechetRand(double var0, double var2, double var4, int var6, long var7) {
        double[] var9 = new double[var6];
        Random var10 = new Random(var7);

        for(int var11 = 0; var11 < var6; ++var11) {
            var9[var11] = Math.pow(1.0D / Math.log(1.0D / var10.nextDouble()), 1.0D / var4) * var2 + var0;
        }

        return var9;
    }

    public static double weibullCDF(double var0, double var2, double var4, double var6) {
        double var8 = (var6 - var0) / var2;
        double var10 = 0.0D;
        if (var8 > 0.0D) {
            var10 = 1.0D - Math.exp(-Math.pow(var8, var4));
        }

        return var10;
    }

    public static double weibullProb(double var0, double var2, double var4, double var6) {
        double var8 = (var6 - var0) / var2;
        double var10 = 0.0D;
        if (var8 > 0.0D) {
            var10 = 1.0D - Math.exp(-Math.pow(var8, var4));
        }

        return var10;
    }

    public static double weibullCDF(double var0, double var2, double var4, double var6, double var8) {
        double var10 = (var6 - var0) / var2;
        double var12 = (var8 - var0) / var2;
        double var14 = 0.0D;
        double var16 = 0.0D;
        if (var10 >= 0.0D) {
            var14 = -Math.exp(-Math.pow(var10, var4));
        }

        if (var12 >= 0.0D) {
            var16 = -Math.exp(-Math.pow(var12, var4));
        }

        return var16 - var14;
    }

    public static double weibullProb(double var0, double var2, double var4, double var6, double var8) {
        double var10 = (var6 - var0) / var2;
        double var12 = (var8 - var0) / var2;
        double var14 = 0.0D;
        double var16 = 0.0D;
        if (var10 >= 0.0D) {
            var14 = -Math.exp(-Math.pow(var10, var4));
        }

        if (var12 >= 0.0D) {
            var16 = -Math.exp(-Math.pow(var12, var4));
        }

        return var16 - var14;
    }

    public static double weibullInverseCDF(double var0, double var2, double var4, double var6) {
        if (var6 >= 0.0D && var6 <= 1.0D) {
            double var8 = 0.0D;
            if (var6 == 0.0D) {
                var8 = var0;
            } else if (var6 == 1.0D) {
                var8 = 1.0D / 0.0;
            } else {
                var8 = var0 + var2 * Math.pow(-Math.log(1.0D - var6), 1.0D / var4);
            }

            return var8;
        } else {
            throw new IllegalArgumentException("Entered cdf value, " + var6 + ", must lie between 0 and 1 inclusive");
        }
    }

    public static double inverseWeibullCDF(double var0, double var2, double var4, double var6) {
        return weibullInverseCDF(var0, var2, var4, var6);
    }

    public static double weibullInverseCDF(double var0, double var2, double var4) {
        return weibullInverseCDF(0.0D, var0, var2, var4);
    }

    public static double inverseWeibullCDF(double var0, double var2, double var4) {
        return weibullInverseCDF(0.0D, var0, var2, var4);
    }

    public static double weibullInverseCDF(double var0, double var2) {
        return weibullInverseCDF(0.0D, 1.0D, var0, var2);
    }

    public static double inverseWeibullCDF(double var0, double var2) {
        return weibullInverseCDF(0.0D, 1.0D, var0, var2);
    }

    public static double weibullPDF(double var0, double var2, double var4, double var6) {
        double var8 = (var6 - var0) / var2;
        double var10 = 0.0D;
        if (var8 >= 0.0D) {
            var10 = var4 / var2 * Math.pow(var8, var4 - 1.0D) * Math.exp(-Math.pow(var8, var4));
        }

        return var10;
    }

    public static double weibull(double var0, double var2, double var4, double var6) {
        double var8 = (var6 - var0) / var2;
        double var10 = 0.0D;
        if (var8 >= 0.0D) {
            var10 = var4 / var2 * Math.pow(var8, var4 - 1.0D) * Math.exp(-Math.pow(var8, var4));
        }

        return var10;
    }

    public static double weibullMean(double var0, double var2, double var4) {
        return var0 + var2 * gamma(1.0D / var4 + 1.0D);
    }

    public static double weibullStandardDeviation(double var0, double var2) {
        return weibullStandDev(var0, var2);
    }

    public static double weibullStandDev(double var0, double var2) {
        double var4 = gamma(2.0D / var2 + 1.0D) - Fmath.square(gamma(1.0D / var2 + 1.0D));
        return var0 * Math.sqrt(var4);
    }

    public static double weibullMode(double var0, double var2, double var4) {
        double var6 = var0;
        if (var4 > 1.0D) {
            var6 = var0 + var2 * Math.pow((var4 - 1.0D) / var4, 1.0D / var4);
        }

        return var6;
    }

    public static double weibullMedian(double var0, double var2, double var4) {
        return var0 + var2 * Math.pow(Math.log(2.0D), 1.0D / var4);
    }

    public static double[] weibullRand(double var0, double var2, double var4, int var6) {
        double[] var7 = new double[var6];
        Random var8 = new Random();

        for(int var9 = 0; var9 < var6; ++var9) {
            var7[var9] = Math.pow(-Math.log(1.0D - var8.nextDouble()), 1.0D / var4) * var2 + var0;
        }

        return var7;
    }

    public static double[] weibullRand(double var0, double var2, double var4, int var6, long var7) {
        double[] var9 = new double[var6];
        Random var10 = new Random(var7);

        for(int var11 = 0; var11 < var6; ++var11) {
            var9[var11] = Math.pow(-Math.log(1.0D - var10.nextDouble()), 1.0D / var4) * var2 + var0;
        }

        return var9;
    }

    public static double[] weibullOrderStatisticMedians(double var0, double var2, double var4, int var6) {
        double var7 = (double)var6;
        double[] var9 = new double[var6];
        double[] var10 = uniformOrderStatisticMedians(var6);

        for(int var11 = 0; var11 < var6; ++var11) {
            var9[var11] = inverseWeibullCDF(var0, var2, var4, var10[var11]);
        }

        return var9;
    }

    public static double[] weibullOrderStatisticMedians(double var0, double var2, int var4) {
        return weibullOrderStatisticMedians(0.0D, var0, var2, var4);
    }

    public static double[] weibullOrderStatisticMedians(double var0, int var2) {
        return weibullOrderStatisticMedians(0.0D, 1.0D, var0, var2);
    }

    public static double exponentialCDF(double var0, double var2, double var4) {
        double var6 = (var4 - var0) / var2;
        double var8 = 0.0D;
        if (var6 > 0.0D) {
            var8 = 1.0D - Math.exp(-var6);
        }

        return var8;
    }

    public static double exponentialProb(double var0, double var2, double var4) {
        double var6 = (var4 - var0) / var2;
        double var8 = 0.0D;
        if (var6 > 0.0D) {
            var8 = 1.0D - Math.exp(-var6);
        }

        return var8;
    }

    public static double exponentialCDF(double var0, double var2, double var4, double var6) {
        double var8 = (var4 - var0) / var2;
        double var10 = (var6 - var0) / var2;
        double var12 = 0.0D;
        double var14 = 0.0D;
        if (var8 >= 0.0D) {
            var12 = -Math.exp(-var8);
        }

        if (var10 >= 0.0D) {
            var14 = -Math.exp(-var10);
        }

        return var14 - var12;
    }

    public static double exponentialProb(double var0, double var2, double var4, double var6) {
        double var8 = (var4 - var0) / var2;
        double var10 = (var6 - var0) / var2;
        double var12 = 0.0D;
        double var14 = 0.0D;
        if (var8 >= 0.0D) {
            var12 = -Math.exp(-var8);
        }

        if (var10 >= 0.0D) {
            var14 = -Math.exp(-var10);
        }

        return var14 - var12;
    }

    public static double exponentialInverseCDF(double var0, double var2, double var4) {
        if (var4 >= 0.0D && var4 <= 1.0D) {
            double var6 = 0.0D;
            if (var4 == 0.0D) {
                var6 = var0;
            } else if (var4 == 1.0D) {
                var6 = 1.0D / 0.0;
            } else {
                var6 = var0 - var2 * Math.log(1.0D - var4);
            }

            return var6;
        } else {
            throw new IllegalArgumentException("Entered cdf value, " + var4 + ", must lie between 0 and 1 inclusive");
        }
    }

    public static double inverseExponentialCDF(double var0, double var2, double var4) {
        return exponentialInverseCDF(var0, var2, var4);
    }

    public static double exponentialPDF(double var0, double var2, double var4) {
        double var6 = (var4 - var0) / var2;
        double var8 = 0.0D;
        if (var6 >= 0.0D) {
            var8 = Math.exp(-var6) / var2;
        }

        return var8;
    }

    public static double exponential(double var0, double var2, double var4) {
        double var6 = (var4 - var0) / var2;
        double var8 = 0.0D;
        if (var6 >= 0.0D) {
            var8 = Math.exp(-var6) / var2;
        }

        return var8;
    }

    public static double exponentialMean(double var0, double var2) {
        return var0 + var2;
    }

    public static double exponentialStandardDeviation(double var0) {
        return var0;
    }

    public static double exponentialStandDev(double var0) {
        return var0;
    }

    public static double exponentialMode(double var0) {
        return var0;
    }

    public static double exponentialMedian(double var0, double var2) {
        return var0 + var2 * Math.log(2.0D);
    }

    public static double[] exponentialRand(double var0, double var2, int var4) {
        double[] var5 = new double[var4];
        Random var6 = new Random();

        for(int var7 = 0; var7 < var4; ++var7) {
            var5[var7] = var0 - Math.log(1.0D - var6.nextDouble()) * var2;
        }

        return var5;
    }

    public static double[] exponentialRand(double var0, double var2, int var4, long var5) {
        double[] var7 = new double[var4];
        Random var8 = new Random(var5);

        for(int var9 = 0; var9 < var4; ++var9) {
            var7[var9] = var0 - Math.log(1.0D - var8.nextDouble()) * var2;
        }

        return var7;
    }

    public static double[] exponentialOrderStatisticMedians(double var0, double var2, int var4) {
        double var5 = (double)var4;
        double[] var7 = new double[var4];
        double[] var8 = uniformOrderStatisticMedians(var4);

        for(int var9 = 0; var9 < var4; ++var9) {
            var7[var9] = inverseExponentialCDF(var0, var2, var8[var9]);
        }

        return var7;
    }

    public static double rayleighCDF(double var0, double var2) {
        double var4 = var2 / var0;
        double var6 = 0.0D;
        if (var4 > 0.0D) {
            var6 = 1.0D - Math.exp(-var4 * var4 / 2.0D);
        }

        return var6;
    }

    public static double rayleighProb(double var0, double var2) {
        double var4 = var2 / var0;
        double var6 = 0.0D;
        if (var4 > 0.0D) {
            var6 = 1.0D - Math.exp(-var4 * var4 / 2.0D);
        }

        return var6;
    }

    public static double rayleighCDF(double var0, double var2, double var4) {
        double var6 = var2 / var0;
        double var8 = var4 / var0;
        double var10 = 0.0D;
        double var12 = 0.0D;
        if (var6 >= 0.0D) {
            var10 = -Math.exp(-var6 * var6 / 2.0D);
        }

        if (var8 >= 0.0D) {
            var12 = -Math.exp(-var8 * var8 / 2.0D);
        }

        return var12 - var10;
    }

    public static double rayleighProb(double var0, double var2, double var4) {
        double var6 = var2 / var0;
        double var8 = var4 / var0;
        double var10 = 0.0D;
        double var12 = 0.0D;
        if (var6 >= 0.0D) {
            var10 = -Math.exp(-var6 * var6 / 2.0D);
        }

        if (var8 >= 0.0D) {
            var12 = -Math.exp(-var8 * var8 / 2.0D);
        }

        return var12 - var10;
    }

    public static double rayleighInverseCDF(double var0, double var2) {
        if (var2 >= 0.0D && var2 <= 1.0D) {
            double var4 = 0.0D;
            if (var2 == 0.0D) {
                var4 = 0.0D;
            } else if (var2 == 1.0D) {
                var4 = 1.0D / 0.0;
            } else {
                var4 = var0 * Math.sqrt(-Math.log(1.0D - var2));
            }

            return var4;
        } else {
            throw new IllegalArgumentException("Entered cdf value, " + var2 + ", must lie between 0 and 1 inclusive");
        }
    }

    public static double inverseRayleighCDF(double var0, double var2) {
        return rayleighInverseCDF(var0, var2);
    }

    public static double rayleighPDF(double var0, double var2) {
        double var4 = var2 / var0;
        double var6 = 0.0D;
        if (var4 >= 0.0D) {
            var6 = var4 / var0 * Math.exp(-var4 * var4 / 2.0D) / var0;
        }

        return var6;
    }

    public static double rayleigh(double var0, double var2) {
        double var4 = var2 / var0;
        double var6 = 0.0D;
        if (var4 >= 0.0D) {
            var6 = var4 / var0 * Math.exp(-var4 * var4 / 2.0D) / var0;
        }

        return var6;
    }

    public static double rayleighMean(double var0) {
        return var0 * Math.sqrt(1.5707963267948966D);
    }

    public static double rayleighStandardDeviation(double var0) {
        return var0 * Math.sqrt(0.42920367320510344D);
    }

    public static double rayleighStandDev(double var0) {
        return var0 * Math.sqrt(0.42920367320510344D);
    }

    public static double rayleighMode(double var0) {
        return var0;
    }

    public static double rayleighMedian(double var0) {
        return var0 * Math.sqrt(Math.log(2.0D));
    }

    public static double[] rayleighRand(double var0, int var2) {
        double[] var3 = new double[var2];
        Random var4 = new Random();

        for(int var5 = 0; var5 < var2; ++var5) {
            var3[var5] = Math.sqrt(-2.0D * Math.log(1.0D - var4.nextDouble())) * var0;
        }

        return var3;
    }

    public static double[] rayleighRand(double var0, int var2, long var3) {
        double[] var5 = new double[var2];
        Random var6 = new Random(var3);

        for(int var7 = 0; var7 < var2; ++var7) {
            var5[var7] = Math.sqrt(-2.0D * Math.log(1.0D - var6.nextDouble())) * var0;
        }

        return var5;
    }

    public static double[] rayleighOrderStatisticMedians(double var0, int var2) {
        double var3 = (double)var2;
        double[] var5 = new double[var2];
        double[] var6 = uniformOrderStatisticMedians(var2);

        for(int var7 = 0; var7 < var2; ++var7) {
            var5[var7] = inverseRayleighCDF(var0, var6[var7]);
        }

        return var5;
    }

    public static double paretoCDF(double var0, double var2, double var4) {
        double var6 = 0.0D;
        if (var4 >= var2) {
            var6 = 1.0D - Math.pow(var2 / var4, var0);
        }

        return var6;
    }

    public static double paretoProb(double var0, double var2, double var4) {
        double var6 = 0.0D;
        if (var4 >= var2) {
            var6 = 1.0D - Math.pow(var2 / var4, var0);
        }

        return var6;
    }

    public static double paretoCDF(double var0, double var2, double var4, double var6) {
        double var8 = 0.0D;
        double var10 = 0.0D;
        if (var4 >= var2) {
            var8 = -Math.pow(var2 / var4, var0);
        }

        if (var6 >= var2) {
            var10 = -Math.pow(var2 / var6, var0);
        }

        return var10 - var8;
    }

    public static double paretoProb(double var0, double var2, double var4, double var6) {
        double var8 = 0.0D;
        double var10 = 0.0D;
        if (var4 >= var2) {
            var8 = -Math.pow(var2 / var4, var0);
        }

        if (var6 >= var2) {
            var10 = -Math.pow(var2 / var6, var0);
        }

        return var10 - var8;
    }

    public static double paretoInverseCDF(double var0, double var2, double var4) {
        if (var4 >= 0.0D && var4 <= 1.0D) {
            double var6 = 0.0D;
            if (var4 == 0.0D) {
                var6 = var2;
            } else if (var4 == 1.0D) {
                var6 = 1.0D / 0.0;
            } else {
                var6 = var2 / Math.pow(1.0D - var4, 1.0D / var0);
            }

            return var6;
        } else {
            throw new IllegalArgumentException("Entered cdf value, " + var4 + ", must lie between 0 and 1 inclusive");
        }
    }

    public static double inverseParetoCDF(double var0, double var2, double var4) {
        return paretoInverseCDF(var0, var2, var4);
    }

    public static double paretoPDF(double var0, double var2, double var4) {
        double var6 = 0.0D;
        if (var4 >= var2) {
            var6 = var0 * Math.pow(var2, var0) / Math.pow(var4, var0 + 1.0D);
        }

        return var6;
    }

    public static double pareto(double var0, double var2, double var4) {
        double var6 = 0.0D;
        if (var4 >= var2) {
            var6 = var0 * Math.pow(var2, var0) / Math.pow(var4, var0 + 1.0D);
        }

        return var6;
    }

    public static double paretoMean(double var0, double var2) {
        double var4 = 0.0D / 0.0;
        if (var0 > 1.0D) {
            var4 = var0 * var2 / (var0 - 1.0D);
        }

        return var4;
    }

    public static double paretoStandardDeviation(double var0, double var2) {
        double var4 = 0.0D / 0.0;
        if (var0 > 1.0D) {
            var4 = var0 * Fmath.square(var2) / (Fmath.square(var0 - 1.0D) * (var0 - 2.0D));
        }

        return var4;
    }

    public static double paretoStandDev(double var0, double var2) {
        double var4 = 0.0D / 0.0;
        if (var0 > 1.0D) {
            var4 = var0 * Fmath.square(var2) / (Fmath.square(var0 - 1.0D) * (var0 - 2.0D));
        }

        return var4;
    }

    public static double paretoMode(double var0) {
        return var0;
    }

    public static double[] paretoRand(double var0, double var2, int var4) {
        double[] var5 = new double[var4];
        Random var6 = new Random();

        for(int var7 = 0; var7 < var4; ++var7) {
            var5[var7] = Math.pow(1.0D - var6.nextDouble(), -1.0D / var0) * var2;
        }

        return var5;
    }

    public static double[] paretoRand(double var0, double var2, int var4, long var5) {
        double[] var7 = new double[var4];
        Random var8 = new Random(var5);

        for(int var9 = 0; var9 < var4; ++var9) {
            var7[var9] = Math.pow(1.0D - var8.nextDouble(), -1.0D / var0) * var2;
        }

        return var7;
    }

    public static double[] paretoOrderStatisticMedians(double var0, double var2, int var4) {
        double var5 = (double)var4;
        double[] var7 = new double[var4];
        double[] var8 = uniformOrderStatisticMedians(var4);

        for(int var9 = 0; var9 < var4; ++var9) {
            var7[var9] = inverseParetoCDF(var0, var2, var8[var9]);
        }

        return var7;
    }

    public void fitOneOrSeveralDistributions() {
        double[] var1 = this.getArray_as_double();
        Regression.fitOneOrSeveralDistributions(var1);
    }

    public static void fitOneOrSeveralDistributions(double[] var0) {
        Regression.fitOneOrSeveralDistributions(var0);
    }

    public static double getGrubbsOneSidedCriticalT(double var0, int var2) {
        return getGrubbsCriticalT(var0, var2, 1);
    }

    public static double getGrubbsTwoSidedCriticalT(double var0, int var2) {
        return getGrubbsCriticalT(var0, var2, 2);
    }

    public static double getGrubbsCriticalT(double var0, int var2, int var3) {
        double var4 = studentstValue(var0 / (double)(var2 * var3), var2 - 2);
        double var6 = var4 * var4;
        return (double)(var2 - 1) * Math.sqrt(var6 / ((double)(var2 - 2) + var6) / (double)var2);
    }

    public ArrayList<Object> outlierGrubbs(double var1) {
        return outlierGrubbs(super.array_as_double(), var1);
    }

    public static ArrayList<Object> outlierGrubbs(double[] var0, double var1) {
        denominatorSwap();
        int var3 = var0.length;
        double var4 = mean(var0);
        double var6 = standardDeviation(var0);
        ArrayMaths var8 = new ArrayMaths(var0);
        var8 = var8.minus(var4);
        var8 = var8.abs();
        var8 = var8.sort();
        int[] var9 = var8.originalIndices();
        double[] var10 = var8.getArray_as_double();
        double var11 = var0[var9[var3 - 1]];
        double var13 = getGrubbsTwoSidedCriticalT(var1, var3);
        double var15 = var10[var3 - 1] / var6;
        denominatorUnswap();
        return outlierGrubbsCore(var11, var0, var10, var3, var1, var13, var15);
    }

    public ArrayList<Object> upperOutlierGrubbs(double var1) {
        return upperOutlierGrubbs(super.array_as_double(), var1);
    }

    public static ArrayList<Object> upperOutlierGrubbs(double[] var0, double var1) {
        denominatorSwap();
        int var3 = var0.length;
        double var4 = mean(var0);
        double var6 = standardDeviation(var0);
        ArrayMaths var8 = new ArrayMaths(var0);
        var8 = var8.sort();
        double[] var9 = var8.getArray_as_double();
        double var10 = var9[var3 - 1];
        double var12 = getGrubbsOneSidedCriticalT(var1, var3);
        double var14 = (var10 - var4) / var6;
        denominatorUnswap();
        return outlierGrubbsCore(var10, var0, var9, var3, var1, var12, var14);
    }

    public ArrayList<Object> lowerOutlierGrubbs(double var1) {
        return lowerOutlierGrubbs(super.array_as_double(), var1);
    }

    public static ArrayList<Object> lowerOutlierGrubbs(double[] var0, double var1) {
        denominatorSwap();
        int var3 = var0.length;
        double var4 = mean(var0);
        double var6 = standardDeviation(var0);
        ArrayMaths var8 = new ArrayMaths(var0);
        var8 = var8.sort();
        double[] var9 = var8.getArray_as_double();
        double var10 = var9[0];
        double var12 = getGrubbsOneSidedCriticalT(var1, var3);
        double var14 = (var4 - var10) / var6;
        denominatorUnswap();
        return outlierGrubbsCore(var10, var0, var9, var3, var1, var12, var14);
    }

    public static ArrayList<Object> outlierGrubbsCore(double var0, double[] var2, double[] var3, int var4, double var5, double var7, double var9) {
        ArrayList var11 = new ArrayList();
        double var12 = 0.0D / 0.0;
        int var14 = -1;
        if (var9 > var7) {
            ArrayList var15 = new ArrayList();

            int var16;
            for(var16 = 0; var16 < var4; ++var16) {
                if (var2[var16] == var0) {
                    var14 = var16;
                } else {
                    var15.add(var2[var16]);
                }
            }

            var16 = var15.size();
            double[] var17 = new double[var16];

            for(int var18 = 0; var18 < var16; ++var18) {
                var17[var18] = (Double)var15.get(var18);
            }

            var11.add(true);
            var11.add(var0);
            var11.add(var14);
            var11.add(var17);
        } else {
            var11.add(false);
            var11.add(var12);
            var11.add(var14);
            var11.add(var2);
        }

        var11.add(var9);
        var11.add(var7);
        var11.add(var5);
        var11.add(var4);
        return var11;
    }

    public static Vector<Object> upperOutliersAnscombeAsVector(double[] var0, double var1) {
        ArrayList var3 = upperOutliersAnscombeAsArrayList(var0, var1);
        Vector var4 = null;
        if (var3 != null) {
            int var5 = var3.size();
            var4 = new Vector(var5);

            for(int var6 = 0; var6 < var5; ++var6) {
                var4.add(var3.get(var6));
            }
        }

        return var4;
    }

    public static Vector<Object> upperOutliersAnscombe(double[] var0, double var1) {
        return upperOutliersAnscombeAsVector(var0, var1);
    }

    public static ArrayList<Object> upperOutliersAnscombeAsArrayList(double[] var0, double var1) {
        Stat var3 = new Stat(var0);
        double[] var4 = var3.getArray_as_double();
        double[] var5 = var3.getArray_as_double();
        int var6 = var0.length;
        ArrayList var8 = new ArrayList();
        int var9 = 0;
        boolean var10 = true;

        while(true) {
            while(var10) {
                double var11 = var3.mean_as_double();
                double var13 = var3.standardDeviation_as_double();
                double var15 = var3.getMaximum_as_double();
                int var17 = var3.getMaximumIndex();
                double var18 = (var15 - var11) / var13;
                if (var18 > var1) {
                    var8.add(new Double(var15));
                    var8.add(new Integer(var17));
                    ++var9;
                    var5 = new double[var6 - 1];

                    for(int var20 = var17; var20 < var6 - 1; ++var20) {
                        var5[var20] = var4[var20 + 1];
                    }

                    --var6;
                    var3 = new Stat(Conv.copy(var5));
                } else {
                    var10 = false;
                }
            }

            double[] var21 = null;
            int[] var12 = null;
            if (var9 > 0) {
                var21 = new double[var9];
                var12 = new int[var9];

                for(int var22 = 0; var22 < var9; ++var22) {
                    var21[var22] = (Double)var8.get(2 * var22);
                    var12[var22] = (Integer)var8.get(2 * var22 + 1);
                }
            }

            ArrayList var23 = new ArrayList(4);
            var23.add(new Integer(var9));
            var23.add(var21);
            var23.add(var12);
            var23.add(var5);
            return var23;
        }
    }

    public static Vector<Object> upperOutliersAnscombeAsVector(BigDecimal[] var0, BigDecimal var1) {
        ArrayList var2 = upperOutliersAnscombeAsArrayList(var0, var1);
        Vector var3 = null;
        if (var2 != null) {
            int var4 = var2.size();
            var3 = new Vector(var4);

            for(int var5 = 0; var5 < var4; ++var5) {
                var3.add(var2.get(var5));
            }
        }

        return var3;
    }

    public static Vector<Object> upperOutliersAnscombe(BigDecimal[] var0, BigDecimal var1) {
        return upperOutliersAnscombeAsVector(var0, var1);
    }

    public static ArrayList<Object> upperOutliersAnscombeAsArrayList(BigDecimal[] var0, BigDecimal var1) {
        Stat var2 = new Stat(var0);
        BigDecimal[] var3 = var2.getArray_as_BigDecimal();
        BigDecimal[] var4 = var2.getArray_as_BigDecimal();
        int var5 = var0.length;
        ArrayList var7 = new ArrayList();
        int var8 = 0;
        boolean var9 = true;

        while(true) {
            while(var9) {
                BigDecimal var10 = var2.mean_as_BigDecimal();
                BigDecimal var11 = var2.variance_as_BigDecimal();
                BigDecimal var12 = var2.getMaximum_as_BigDecimal();
                int var13 = var2.getMaximumIndex();
                BigDecimal var14 = var12.subtract(var10).divide(var11, 4);
                if (var14.compareTo(var1.multiply(var1)) == 1) {
                    var7.add(var12);
                    var7.add(new Integer(var13));
                    ++var8;
                    var4 = new BigDecimal[var5 - 1];

                    for(int var15 = var13; var15 < var5 - 1; ++var15) {
                        var4[var15] = var3[var15 + 1];
                    }

                    --var5;
                    var2 = new Stat(Conv.copy(var4));
                } else {
                    var10 = null;
                    var11 = null;
                    var14 = null;
                    var3 = null;
                    var9 = false;
                }
            }

            BigDecimal[] var16 = null;
            int[] var17 = null;
            if (var8 > 0) {
                var16 = new BigDecimal[var8];
                var17 = new int[var8];

                for(int var18 = 0; var18 < var8; ++var18) {
                    var16[var18] = (BigDecimal)var7.get(2 * var18);
                    var17[var18] = (Integer)var7.get(2 * var18 + 1);
                }
            }

            ArrayList var19 = new ArrayList(4);
            var19.add(new Integer(var8));
            var19.add(var16);
            var19.add(var17);
            var19.add(var4);
            return var19;
        }
    }

    public static Vector<Object> upperOutliersAnscombeAsVector(BigInteger[] var0, BigInteger var1) {
        ArrayList var2 = upperOutliersAnscombeAsArrayList(var0, var1);
        Vector var3 = null;
        if (var2 != null) {
            int var4 = var2.size();
            var3 = new Vector(var4);

            for(int var5 = 0; var5 < var4; ++var5) {
                var3.add(var2.get(var5));
            }
        }

        return var3;
    }

    public static Vector<Object> upperOutliersAnscombe(BigInteger[] var0, BigInteger var1) {
        return upperOutliersAnscombeAsVector(var0, var1);
    }

    public static ArrayList<Object> upperOutliersAnscombeAsArrayList(BigInteger[] var0, BigInteger var1) {
        ArrayMaths var2 = new ArrayMaths(var0);
        BigDecimal[] var3 = var2.getArray_as_BigDecimal();
        BigDecimal var4 = new BigDecimal(var1);
        return upperOutliersAnscombeAsArrayList(var3, var4);
    }

    public static Vector<Object> lowerOutliersAnscombeAsVector(double[] var0, double var1) {
        ArrayList var3 = lowerOutliersAnscombeAsArrayList(var0, var1);
        Vector var4 = null;
        if (var3 != null) {
            int var5 = var3.size();
            var4 = new Vector(var5);

            for(int var6 = 0; var6 < var5; ++var6) {
                var4.add(var3.get(var6));
            }
        }

        return var4;
    }

    public static Vector<Object> lowerOutliersAnscombe(double[] var0, double var1) {
        return upperOutliersAnscombeAsVector(var0, var1);
    }

    public static ArrayList<Object> lowerOutliersAnscombeAsArrayList(double[] var0, double var1) {
        Stat var3 = new Stat(var0);
        double[] var4 = var3.getArray_as_double();
        double[] var5 = var3.getArray_as_double();
        int var6 = var0.length;
        ArrayList var8 = new ArrayList();
        int var9 = 0;
        boolean var10 = true;

        while(true) {
            while(var10) {
                double var11 = var3.mean_as_double();
                double var13 = var3.standardDeviation_as_double();
                double var15 = var3.getMinimum_as_double();
                int var17 = var3.getMinimumIndex();
                double var18 = (var11 - var15) / var13;
                if (var18 > var1) {
                    var8.add(new Double(var15));
                    var8.add(new Integer(var17));
                    ++var9;
                    var5 = new double[var6 - 1];

                    for(int var20 = var17; var20 < var6 - 1; ++var20) {
                        var5[var20] = var4[var20 + 1];
                    }

                    --var6;
                    var3 = new Stat(Conv.copy(var5));
                } else {
                    var10 = false;
                }
            }

            double[] var21 = null;
            int[] var12 = null;
            if (var9 > 0) {
                var21 = new double[var9];
                var12 = new int[var9];

                for(int var22 = 0; var22 < var9; ++var22) {
                    var21[var22] = (Double)var8.get(2 * var22);
                    var12[var22] = (Integer)var8.get(2 * var22 + 1);
                }
            }

            ArrayList var23 = new ArrayList();
            var23.add(new Integer(var9));
            var23.add(var21);
            var23.add(var12);
            var23.add(var5);
            return var23;
        }
    }

    public static Vector<Object> lowerOutliersAnscombeAsVector(BigDecimal[] var0, BigDecimal var1) {
        ArrayList var2 = lowerOutliersAnscombeAsArrayList(var0, var1);
        Vector var3 = null;
        if (var2 != null) {
            int var4 = var2.size();
            var3 = new Vector(var4);

            for(int var5 = 0; var5 < var4; ++var5) {
                var3.add(var2.get(var5));
            }
        }

        return var3;
    }

    public static Vector<Object> lowerOutliersAnscombe(BigDecimal[] var0, BigDecimal var1) {
        return upperOutliersAnscombeAsVector(var0, var1);
    }

    public static ArrayList<Object> lowerOutliersAnscombeAsArrayList(BigDecimal[] var0, BigDecimal var1) {
        Stat var2 = new Stat(var0);
        BigDecimal[] var3 = var2.getArray_as_BigDecimal();
        BigDecimal[] var4 = var2.getArray_as_BigDecimal();
        int var5 = var0.length;
        ArrayList var7 = new ArrayList();
        int var8 = 0;
        boolean var9 = true;

        while(true) {
            while(var9) {
                BigDecimal var10 = var2.mean_as_BigDecimal();
                BigDecimal var11 = var2.variance_as_BigDecimal();
                BigDecimal var12 = var2.getMinimum_as_BigDecimal();
                int var13 = var2.getMinimumIndex();
                BigDecimal var14 = var10.subtract(var12).divide(var11, 4);
                if (var14.compareTo(var1.multiply(var1)) == 1) {
                    var7.add(var12);
                    var7.add(new Integer(var13));
                    ++var8;
                    var4 = new BigDecimal[var5 - 1];

                    for(int var15 = var13; var15 < var5 - 1; ++var15) {
                        var4[var15] = var3[var15 + 1];
                    }

                    --var5;
                    var2 = new Stat(Conv.copy(var4));
                } else {
                    var10 = null;
                    var11 = null;
                    var14 = null;
                    var3 = null;
                    var9 = false;
                }
            }

            BigDecimal[] var16 = null;
            int[] var17 = null;
            if (var8 > 0) {
                var16 = new BigDecimal[var8];
                var17 = new int[var8];

                for(int var18 = 0; var18 < var8; ++var18) {
                    var16[var18] = (BigDecimal)var7.get(2 * var18);
                    var17[var18] = (Integer)var7.get(2 * var18 + 1);
                }
            }

            ArrayList var19 = new ArrayList();
            var19.add(new Integer(var8));
            var19.add(var16);
            var19.add(var17);
            var19.add(var4);
            return var19;
        }
    }

    public static Vector<Object> lowerOutliersAnscombeAsVector(BigInteger[] var0, BigInteger var1) {
        ArrayList var2 = lowerOutliersAnscombeAsArrayList(var0, var1);
        Vector var3 = null;
        if (var2 != null) {
            int var4 = var2.size();
            var3 = new Vector(var4);

            for(int var5 = 0; var5 < var4; ++var5) {
                var3.add(var2.get(var5));
            }
        }

        return var3;
    }

    public static Vector<Object> lowerOutliersAnscombe(BigInteger[] var0, BigInteger var1) {
        return upperOutliersAnscombeAsVector(var0, var1);
    }

    public static ArrayList<Object> lowerOutliersAnscombeAsArrayList(BigInteger[] var0, BigInteger var1) {
        ArrayMaths var2 = new ArrayMaths(var0);
        BigDecimal[] var3 = var2.getArray_as_BigDecimal();
        BigDecimal var4 = new BigDecimal(var1);
        return lowerOutliersAnscombeAsArrayList(var3, var4);
    }

    public Stat copy() {
        Stat var1 = new Stat();
        if (this.amWeights == null) {
            var1.amWeights = null;
        } else {
            var1.amWeights = this.amWeights;
        }

        var1.weightsSupplied = this.weightsSupplied;
        var1.upperOutlierDetails = new ArrayList();
        Integer var2;
        int[] var3;
        if (this.upperOutlierDetails.size() != 0) {
            var2 = (Integer)this.upperOutlierDetails.get(0);
            var1.upperOutlierDetails.add(var2);
            var1.upperOutlierDetails.add(this.upperOutlierDetails.get(1));
            var3 = (int[])((int[])this.upperOutlierDetails.get(2));
            var1.upperOutlierDetails.add(var3);
            var1.upperOutlierDetails.add(this.upperOutlierDetails.get(3));
        }

        var1.upperDone = this.upperDone;
        var1.lowerOutlierDetails = new ArrayList();
        if (this.lowerOutlierDetails.size() != 0) {
            var2 = (Integer)this.lowerOutlierDetails.get(0);
            var1.lowerOutlierDetails.add(var2);
            var1.lowerOutlierDetails.add(this.lowerOutlierDetails.get(1));
            var3 = (int[])((int[])this.lowerOutlierDetails.get(2));
            var1.lowerOutlierDetails.add(var3);
            var1.lowerOutlierDetails.add(this.lowerOutlierDetails.get(3));
        }

        var1.lowerDone = this.lowerDone;
        var1.length = this.length;
        var1.maxIndex = this.maxIndex;
        var1.minIndex = this.minIndex;
        var1.sumDone = this.sumDone;
        var1.productDone = this.productDone;
        var1.sumlongToDouble = this.sumlongToDouble;
        var1.productlongToDouble = this.productlongToDouble;
        var1.type = this.type;
        if (this.originalTypes == null) {
            var1.originalTypes = null;
        } else {
            var1.originalTypes = Conv.copy(this.originalTypes);
        }

        if (this.sortedIndices == null) {
            var1.sortedIndices = null;
        } else {
            var1.sortedIndices = Conv.copy(this.sortedIndices);
        }

        var1.suppressMessages = this.suppressMessages;
        var1.minmax = new ArrayList();
        long var4;
        int var7;
        int var12;
        double var15;
        if (this.minmax.size() != 0) {
            short var8;
            switch(this.type) {
                case 0:
                case 1:
                    var15 = (Double)this.minmax.get(0);
                    var1.minmax.add(new Double(var15));
                    var15 = (Double)this.minmax.get(1);
                    var1.minmax.add(new Double(var15));
                    break;
                case 2:
                case 3:
                    float var6 = (Float)this.minmax.get(0);
                    var1.minmax.add(new Double((double)var6));
                    var6 = (Float)this.minmax.get(1);
                    var1.minmax.add(new Double((double)var6));
                    break;
                case 4:
                case 5:
                    var4 = (Long)this.minmax.get(0);
                    var1.minmax.add(new Double((double)var4));
                    var4 = (Long)this.minmax.get(1);
                    var1.minmax.add(new Long(var4));
                    break;
                case 6:
                case 7:
                    var7 = (Integer)this.minmax.get(0);
                    var1.minmax.add(new Integer(var7));
                    var7 = ((Double)this.minmax.get(1)).intValue();
                    var1.minmax.add(new Integer(var7));
                    break;
                case 8:
                case 9:
                    var8 = (Short)this.minmax.get(0);
                    var1.minmax.add(new Short(var8));
                    var8 = ((Double)this.minmax.get(1)).shortValue();
                    var1.minmax.add(new Short(var8));
                    break;
                case 10:
                case 11:
                    byte var9 = (Byte)this.minmax.get(0);
                    var1.minmax.add(new Byte(var9));
                    var8 = (short)(Byte)this.minmax.get(1);
                    var1.minmax.add(new Byte(var9));
                    break;
                case 12:
                    BigDecimal var10 = (BigDecimal)this.minmax.get(0);
                    var1.minmax.add(var10);
                    var10 = (BigDecimal)this.minmax.get(1);
                    var1.minmax.add(var10);
                    var10 = null;
                    break;
                case 13:
                    BigInteger var11 = (BigInteger)this.minmax.get(0);
                    var1.minmax.add(var11);
                    var11 = (BigInteger)this.minmax.get(1);
                    var1.minmax.add(var11);
                    var11 = null;
                case 14:
                case 15:
                default:
                    break;
                case 16:
                case 17:
                    var12 = (Integer)this.minmax.get(0);
                    var1.minmax.add(new Integer(var12));
                    var12 = ((Double)this.minmax.get(1)).intValue();
                    var1.minmax.add(new Integer(var12));
            }
        }

        var1.summ = new ArrayList();
        BigInteger var5;
        BigDecimal var19;
        double var20;
        Complex var23;
        Phasor var27;
        if (this.summ.size() != 0) {
            switch(this.type) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 18:
                    var15 = (Double)this.summ.get(0);
                    var1.summ.add(new Double(var15));
                    break;
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 16:
                case 17:
                    if (this.sumlongToDouble) {
                        var20 = (Double)this.summ.get(0);
                        var1.summ.add(new Double(var20));
                    } else {
                        var4 = (Long)this.summ.get(0);
                        var1.summ.add(new Long(var4));
                    }
                    break;
                case 12:
                    var19 = (BigDecimal)this.summ.get(0);
                    var1.summ.add(var19);
                    break;
                case 13:
                    var5 = (BigInteger)this.summ.get(0);
                    var1.summ.add(var5);
                    break;
                case 14:
                    var23 = (Complex)this.summ.get(0);
                    var1.summ.add(var23);
                    break;
                case 15:
                    var27 = (Phasor)this.summ.get(0);
                    var1.summ.add(var27);
                    break;
                default:
                    throw new IllegalArgumentException("Data type not identified by this method");
            }
        }

        var1.productt = new ArrayList();
        if (this.productt.size() != 0) {
            switch(this.type) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 18:
                    var15 = (Double)this.productt.get(0);
                    var1.productt.add(new Double(var15));
                    break;
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 16:
                case 17:
                    if (this.sumlongToDouble) {
                        var20 = (Double)this.productt.get(0);
                        var1.productt.add(new Double(var20));
                    } else {
                        var4 = (Long)this.productt.get(0);
                        var1.productt.add(new Long(var4));
                    }
                    break;
                case 12:
                    var19 = (BigDecimal)this.productt.get(0);
                    var1.productt.add(var19);
                    break;
                case 13:
                    var5 = (BigInteger)this.productt.get(0);
                    var1.productt.add(var5);
                    break;
                case 14:
                    var23 = (Complex)this.productt.get(0);
                    var1.productt.add(var23);
                    break;
                case 15:
                    var27 = (Phasor)this.productt.get(0);
                    var1.productt.add(var27);
                    break;
                default:
                    throw new IllegalArgumentException("Data type not identified by this method");
            }
        }

        switch(this.type) {
            case 0:
            case 1:
                double[] var17 = Conv.copy(this.getArray_as_double());

                for(int var18 = 0; var18 < this.length; ++var18) {
                    var1.array.add(new Double(var17[var18]));
                }

                return var1;
            case 2:
            case 3:
                float[] var16 = Conv.copy(this.getArray_as_float());

                for(int var25 = 0; var25 < this.length; ++var25) {
                    var1.array.add(new Float(var16[var25]));
                }

                return var1;
            case 4:
            case 5:
                long[] var24 = Conv.copy(this.getArray_as_long());

                for(int var22 = 0; var22 < this.length; ++var22) {
                    var1.array.add(new Long(var24[var22]));
                }

                return var1;
            case 6:
            case 7:
                int[] var21 = Conv.copy(this.getArray_as_int());

                for(int var28 = 0; var28 < this.length; ++var28) {
                    var1.array.add(new Integer(var21[var28]));
                }

                return var1;
            case 8:
            case 9:
                short[] var26 = Conv.copy(this.getArray_as_short());

                for(var7 = 0; var7 < this.length; ++var7) {
                    var1.array.add(new Short(var26[var7]));
                }

                return var1;
            case 10:
            case 11:
                byte[] var29 = Conv.copy(this.getArray_as_byte());

                for(int var31 = 0; var31 < this.length; ++var31) {
                    var1.array.add(new Byte(var29[var31]));
                }

                return var1;
            case 12:
                BigDecimal[] var30 = Conv.copy(this.getArray_as_BigDecimal());

                for(int var33 = 0; var33 < this.length; ++var33) {
                    var1.array.add(var30[var33]);
                }

                return var1;
            case 13:
                BigInteger[] var32 = Conv.copy(this.getArray_as_BigInteger());

                for(int var35 = 0; var35 < this.length; ++var35) {
                    var1.array.add(var32[var35]);
                }

                return var1;
            case 14:
                Complex[] var34 = this.getArray_as_Complex();

                for(int var37 = 0; var37 < this.length; ++var37) {
                    var1.array.add(var34[var37].copy());
                }

                return var1;
            case 15:
                Phasor[] var36 = this.getArray_as_Phasor();

                for(var12 = 0; var12 < this.length; ++var12) {
                    var1.array.add(var36[var12].copy());
                }

                return var1;
            case 16:
            case 17:
                char[] var38 = Conv.copy(this.getArray_as_char());

                for(int var39 = 0; var39 < this.length; ++var39) {
                    var1.array.add(new Character(var38[var39]));
                }

                return var1;
            case 18:
                String[] var13 = Conv.copy(this.getArray_as_String());

                for(int var14 = 0; var14 < this.length; ++var14) {
                    var1.array.add(var13[var14]);
                }
        }

        return var1;
    }
}
