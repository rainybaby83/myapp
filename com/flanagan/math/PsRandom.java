//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.math;

import com.flanagan.analysis.Stat;
import com.flanagan.roots.RealRoot;
import java.io.Serializable;
import java.util.Random;

public class PsRandom implements Serializable {
    private static final long serialVersionUID = 1L;
    private long seed;
    private long initialSeed;
    private int methodOptionDecimal = 1;
    private int methodOptionInteger = 1;
    private Random rr = null;
    private int methodOptionBinary = 1;
    private long ia = 16807L;
    private long im = 2147483647L;
    private double am;
    private long iq;
    private long ir;
    private int ntab;
    private long ndiv;
    private double eps;
    private double rnmx;
    private long iy;
    private long[] iv;
    private int iset;
    private double gset;
    private long powTwo1;
    private long powTwo2;
    private long powTwo5;
    private long powTwo18;
    private long mask;

    public PsRandom() {
        this.am = 1.0D / (double)this.im;
        this.iq = 127773L;
        this.ir = 2836L;
        this.ntab = 32;
        this.ndiv = 1L + (this.im - 1L) / (long)this.ntab;
        this.eps = 1.2E-7D;
        this.rnmx = 1.0D - this.eps;
        this.iy = 0L;
        this.iv = new long[this.ntab];
        this.iset = 0;
        this.gset = 0.0D;
        this.powTwo1 = 1L;
        this.powTwo2 = 2L;
        this.powTwo5 = 16L;
        this.powTwo18 = 131072L;
        this.mask = this.powTwo1 + this.powTwo2 + this.powTwo5;
        this.seed = System.currentTimeMillis();
        this.initialSeed = this.seed;
        this.rr = new Random(this.seed);
    }

    public PsRandom(long var1) {
        this.am = 1.0D / (double)this.im;
        this.iq = 127773L;
        this.ir = 2836L;
        this.ntab = 32;
        this.ndiv = 1L + (this.im - 1L) / (long)this.ntab;
        this.eps = 1.2E-7D;
        this.rnmx = 1.0D - this.eps;
        this.iy = 0L;
        this.iv = new long[this.ntab];
        this.iset = 0;
        this.gset = 0.0D;
        this.powTwo1 = 1L;
        this.powTwo2 = 2L;
        this.powTwo5 = 16L;
        this.powTwo18 = 131072L;
        this.mask = this.powTwo1 + this.powTwo2 + this.powTwo5;
        this.seed = var1;
        this.initialSeed = var1;
        this.rr = new Random(this.seed);
    }

    public void setSeed(long var1) {
        this.seed = var1;
        if (this.methodOptionDecimal == 1) {
            this.rr = new Random(this.seed);
        }

    }

    public long getInitialSeed() {
        return this.initialSeed;
    }

    public long getSeed() {
        return this.seed;
    }

    public void setMethodDecimal(int var1) {
        if (var1 >= 1 && var1 <= 2) {
            this.methodOptionDecimal = var1;
            if (var1 == 1) {
                this.rr = new Random(this.seed);
            }

        } else {
            throw new IllegalArgumentException("Argument to PsRandom.setMethodDecimal must 1 or 2\nValue transferred was" + var1);
        }
    }

    public int getMethodDecimal() {
        return this.methodOptionDecimal;
    }

    public void setMethodInteger(int var1) {
        if (var1 >= 1 && var1 <= 3) {
            this.methodOptionInteger = var1;
        } else {
            throw new IllegalArgumentException("Argument to PsRandom.setMethodInteger must 1, 2 or 3\nValue transferred was" + var1);
        }
    }

    public int getMethodInteger() {
        return this.methodOptionInteger;
    }

    public void setMethodBinary(int var1) {
        if (var1 >= 1 && var1 <= 2) {
            this.methodOptionBinary = var1;
            if (var1 == 1) {
                this.rr = new Random(this.seed);
            }

        } else {
            throw new IllegalArgumentException("Argument to PsRandom.setMethodBinary must 1 or 2\nValue transferred was" + var1);
        }
    }

    public int getMethodBinary() {
        return this.methodOptionBinary;
    }

    public double nextDouble() {
        return this.methodOptionDecimal == 1 ? this.rr.nextDouble() : this.parkMiller();
    }

    public double nextDouble(double var1) {
        return var1 * this.nextDouble();
    }

    public double nextDouble(double var1, double var3) {
        return (var3 - var1) * this.nextDouble() + var1;
    }

    public double[] doubleArray(int var1) {
        double[] var2 = new double[var1];

        for(int var3 = 0; var3 < var1; ++var3) {
            var2[var3] = this.nextDouble();
        }

        return var2;
    }

    public double[] doubleArray(int var1, double var2) {
        double[] var4 = new double[var1];

        for(int var5 = 0; var5 < var1; ++var5) {
            var4[var5] = var2 * this.nextDouble();
        }

        return var4;
    }

    public double[] doubleArray(int var1, double var2, double var4) {
        double[] var6 = new double[var1];

        for(int var7 = 0; var7 < var1; ++var7) {
            var6[var7] = (var4 - var2) * this.nextDouble() + var2;
        }

        return var6;
    }

    public double parkMiller() {
        boolean var1 = false;
        long var2 = 0L;
        double var4 = 0.0D;
        this.iy = 0L;
        if (this.seed <= 0L || this.iy != 0L) {
            if (-this.seed < 1L) {
                this.seed = 1L;
            } else {
                this.seed = -this.seed;
            }

            for(int var6 = this.ntab + 7; var6 >= 0; --var6) {
                var2 = this.seed / this.iq;
                this.seed = this.ia * (this.seed - var2 * this.iq) - this.ir * var2;
                if (this.seed < 0L) {
                    this.seed += this.im;
                }

                if (var6 < this.ntab) {
                    this.iv[var6] = this.seed;
                }
            }

            this.iy = this.iv[0];
        }

        var2 = this.seed / this.iq;
        this.seed = this.ia * (this.seed - var2 * this.iq) - this.ir * var2;
        if (this.seed < 0L) {
            this.seed += this.im;
        }

        int var7 = (int)(this.iy / this.ndiv);
        this.iy = this.iv[var7];
        this.iv[var7] = this.seed;
        return (var4 = this.am * (double)this.iy) > this.rnmx ? this.rnmx : var4;
    }

    public int nextBit() {
        return this.methodOptionBinary == 1 ? this.nextBitM1() : this.nextBitM2();
    }

    public int[] bitArray(int var1) {
        int[] var2 = new int[var1];

        for(int var3 = 0; var3 < var1; ++var3) {
            var2[var3] = this.nextBit();
        }

        return var2;
    }

    public int nextBitM1() {
        long var1 = (this.seed & this.powTwo18) >> 17 ^ (this.seed & this.powTwo5) >> 4 ^ (this.seed & this.powTwo2) >> 1 ^ this.seed & this.powTwo1;
        this.seed = this.seed << 1 | var1;
        return (int)var1;
    }

    public int nextBitM2() {
        boolean var1 = false;
        byte var2;
        if ((this.seed & this.powTwo18) <= 0L) {
            this.seed = (this.seed ^ this.mask) << 1 | this.powTwo1;
            var2 = 1;
        } else {
            this.seed <<= 1;
            var2 = 0;
        }

        return var2;
    }

    public double nextGaussian(double var1, double var3) {
        double var5 = 0.0D;
        if (this.methodOptionDecimal == 1) {
            var5 = this.rr.nextGaussian();
        } else {
            var5 = this.boxMullerParkMiller();
        }

        var5 = var5 * var3 + var1;
        return var5;
    }

    public double nextGaussian() {
        double var1 = 0.0D;
        if (this.methodOptionDecimal == 1) {
            var1 = this.rr.nextGaussian();
        } else {
            var1 = this.boxMullerParkMiller();
        }

        return var1;
    }

    public double nextNormal(double var1, double var3) {
        return this.nextGaussian(var1, var3);
    }

    public double nextNormal() {
        return this.nextGaussian();
    }

    public double[] gaussianArray(double var1, double var3, int var5) {
        double[] var6 = new double[var5];

        int var7;
        for(var7 = 0; var7 < var5; ++var7) {
            var6[var7] = this.nextGaussian();
        }

        var6 = Stat.standardize(var6);

        for(var7 = 0; var7 < var5; ++var7) {
            var6[var7] = var6[var7] * var3 + var1;
        }

        return var6;
    }

    public double[] normalArray(double var1, double var3, int var5) {
        return this.gaussianArray(var1, var3, var5);
    }

    public double[][] correlatedGaussianArrays(double var1, double var3, double var5, double var7, double var9, int var11) {
        if (Math.abs(var9) > 1.0D) {
            throw new IllegalArgumentException("The correlation coefficient, " + var9 + ", must lie between -1 and 1");
        } else {
            double[][] var12 = new double[2][var11];
            double[] var13 = this.gaussianArray(0.0D, 1.0D, var11);
            double[] var14 = this.gaussianArray(0.0D, 1.0D, var11);
            double var15 = 0.0D;
            double var17 = Math.sqrt(1.0D - var9 * var9);

            for(int var19 = 0; var19 < var11; ++var19) {
                var12[0][var19] = var13[var19] * var5 + var1;
                var12[1][var19] = (var9 * var13[var19] + var17 * var14[var19]) * var7 + var3;
            }

            return var12;
        }
    }

    public double[][] correlatedNormalArrays(double var1, double var3, double var5, double var7, double var9, int var11) {
        return this.correlatedGaussianArrays(var1, var3, var5, var7, var9, var11);
    }

    double boxMullerParkMiller() {
        double var1 = 0.0D;
        double var3 = 0.0D;
        double var5 = 0.0D;
        double var7 = 0.0D;
        if (this.iset != 0) {
            this.iset = 0;
            return this.gset;
        } else {
            do {
                do {
                    var5 = 2.0D * this.parkMiller() - 1.0D;
                    var7 = 2.0D * this.parkMiller() - 1.0D;
                    var3 = var5 * var5 + var7 * var7;
                } while(var3 >= 1.0D);
            } while(var3 == 0.0D);

            var1 = Math.sqrt(-2.0D * Math.log(var3) / var3);
            this.gset = var5 * var1;
            this.iset = 1;
            return var7 * var1;
        }
    }

    public double nextLogNormal(double var1, double var3) {
        double var5 = 0.0D;
        LogNormalTwoParFunct var7 = new LogNormalTwoParFunct();
        var7.mu = var1;
        var7.sigma = var3;
        double var8 = 1.0E-10D;
        double var10 = 0.0D;
        double var12 = var3 * var3;
        double var14 = 5.0D * Math.sqrt((Math.exp(var12) - 1.0D) * Math.exp(2.0D * var1 + var12));
        RealRoot var16 = new RealRoot();
        var16.noLowerBoundExtension();
        var16.setTolerance(var8);
        var16.resetNaNexceptionToTrue();
        var16.supressLimitReachedMessage();
        var16.supressNaNmessage();
        boolean var17 = true;
        int var18 = 0;
        byte var19 = 10;

        while(var17) {
            var7.cfd = this.nextDouble();
            var5 = var16.falsePosition(var7, var10, var14);
            if (!Double.isNaN(var5)) {
                var17 = false;
            } else if (var18 > var19) {
                System.out.println("class: PsRandom,  method: nextLogNormal");
                System.out.println(var19 + " successive attempts at calculating a random log-normal deviate failed for values of mu = " + var1 + ", sigma = " + var3);
                System.out.println("NaN returned");
                var5 = 0.0D / 0.0;
                var17 = false;
            } else {
                ++var18;
            }
        }

        return var5;
    }

    public double nextLogNormalTwoPar(double var1, double var3) {
        return this.nextLogNormal(var1, var3);
    }

    public double[] logNormalArray(double var1, double var3, int var5) {
        double[] var6 = new double[var5];
        LogNormalTwoParFunct var7 = new LogNormalTwoParFunct();
        var7.mu = var1;
        var7.sigma = var3;
        double var8 = 1.0E-10D;
        double var10 = 0.0D;
        double var12 = var3 * var3;
        double var14 = 5.0D * Math.sqrt((Math.exp(var12) - 1.0D) * Math.exp(2.0D * var1 + var12));

        for(int var16 = 0; var16 < var5; ++var16) {
            RealRoot var17 = new RealRoot();
            var17.noLowerBoundExtension();
            var17.setTolerance(var8);
            var17.resetNaNexceptionToTrue();
            var17.supressLimitReachedMessage();
            var17.supressNaNmessage();
            boolean var18 = true;
            int var19 = 0;
            byte var20 = 10;

            while(var18) {
                var7.cfd = this.nextDouble();
                double var21 = var17.bisect(var7, var10, var14);
                if (!Double.isNaN(var21)) {
                    var18 = false;
                    var6[var16] = var21;
                } else if (var19 > var20) {
                    System.out.println("class: PsRandom,  method: logNormalArray");
                    System.out.println(var20 + " successive attempts at calculating a random gamma deviate failed for values of mu = " + var1 + ", sigma = " + var3);
                    System.out.println("NaN returned");
                    var6[var16] = 0.0D / 0.0;
                    var18 = false;
                } else {
                    ++var19;
                }
            }
        }

        return var6;
    }

    public double[] logNormalTwoParArray(double var1, double var3, int var5) {
        return this.logNormalArray(var1, var3, var5);
    }

    public double nextLogNormalThreePar(double var1, double var3, double var5) {
        double var7 = 0.0D / 0.0;
        LogNormalThreeParFunct var9 = new LogNormalThreeParFunct();
        var9.alpha = var1;
        var9.beta = var3;
        var9.gamma = var5;
        double var10 = 1.0E-10D;
        double var12 = var1;
        double var14 = var3 * var3;
        double var16 = 5.0D * Math.sqrt((Math.exp(var14) - 1.0D) * Math.exp(2.0D * Math.log(var5) + var14));
        RealRoot var18 = new RealRoot();
        var18.noLowerBoundExtension();
        var18.setTolerance(var10);
        var18.resetNaNexceptionToTrue();
        var18.supressLimitReachedMessage();
        var18.supressNaNmessage();
        boolean var19 = true;
        int var20 = 0;
        byte var21 = 10;

        while(var19) {
            var9.cfd = this.nextDouble();
            var7 = var18.falsePosition(var9, var12, var16);
            if (!Double.isNaN(var7)) {
                var19 = false;
            } else if (var20 > var21) {
                System.out.println("class: PsRandom,  method: nextLogNormalThreePar");
                System.out.println(var21 + " successive attempts at calculating a random log-normal deviate failed for values of alpha = " + var1 + ", beta = " + var3 + ", gamma = " + var5);
                System.out.println("NaN returned");
                var7 = 0.0D / 0.0;
                var19 = false;
            } else {
                ++var20;
            }
        }

        return var7;
    }

    public double[] logNormalThreeParArray(double var1, double var3, double var5, int var7) {
        double[] var8 = new double[var7];
        LogNormalThreeParFunct var9 = new LogNormalThreeParFunct();
        var9.alpha = var1;
        var9.beta = var3;
        var9.gamma = var5;
        double var10 = 1.0E-10D;
        double var12 = var1;
        double var14 = var3 * var3;
        double var16 = 5.0D * Math.sqrt((Math.exp(var14) - 1.0D) * Math.exp(2.0D * Math.log(var5) + var14));

        for(int var18 = 0; var18 < var7; ++var18) {
            RealRoot var19 = new RealRoot();
            var19.noLowerBoundExtension();
            var19.setTolerance(var10);
            var19.resetNaNexceptionToTrue();
            var19.supressLimitReachedMessage();
            var19.supressNaNmessage();
            boolean var20 = true;
            int var21 = 0;
            byte var22 = 10;

            while(var20) {
                var9.cfd = this.nextDouble();
                double var23 = var19.falsePosition(var9, var12, var16);
                if (!Double.isNaN(var23)) {
                    var20 = false;
                    var8[var18] = var23;
                } else if (var21 > var22) {
                    System.out.println("class: PsRandom,  method: logNormalThreeParArray");
                    System.out.println(var22 + " successive attempts at calculating a log-normal gamma deviate failed for values of alpha = " + var1 + ", beta = " + var3 + ", gamma = " + var5);
                    System.out.println("NaN returned");
                    var8[var18] = 0.0D / 0.0;
                    var20 = false;
                } else {
                    ++var21;
                }
            }
        }

        return var8;
    }

    public double nextLorentzian(double var1, double var3) {
        double var5 = Math.tan((this.nextDouble() - 0.5D) * 3.141592653589793D);
        var5 = var5 * var3 / 2.0D + var1;
        return var5;
    }

    public double[] lorentzianArray(double var1, double var3, int var5) {
        double[] var6 = new double[var5];

        for(int var7 = 0; var7 < var5; ++var7) {
            var6[var7] = Math.tan((this.nextDouble() - 0.5D) * 3.141592653589793D);
            var6[var7] = var6[var7] * var3 / 2.0D + var1;
        }

        return var6;
    }

    public double nextPoissonian(double var1) {
        double var3 = 0.0D;
        double var5 = -1.0D;
        double var7 = 0.0D;
        double var9 = 0.0D;
        double var11 = 0.0D;
        double var13 = 0.0D;
        double var15 = 0.0D;
        double var17 = 0.0D;
        if (var1 < 12.0D) {
            if (var1 != var5) {
                var7 = Math.exp(-var1);
            }

            var9 = -1.0D;
            var11 = 1.0D;

            do {
                ++var9;
                var11 *= this.nextDouble();
            } while(var11 > var7);

            var3 = var9;
        } else {
            if (var1 != var5) {
                var13 = Math.sqrt(2.0D * var1);
                var15 = Math.log(var1);
                var7 = var1 * var15 - Stat.logGamma(var1 + 1.0D);
            }

            do {
                do {
                    var17 = Math.tan(3.141592653589793D * this.nextDouble());
                    var9 = var13 * var17 + var1;
                } while(var9 < 0.0D);

                var9 = Math.floor(var9);
                var11 = 0.9D * (1.0D + var17 * var17) * Math.exp(var9 * var15 - Stat.logGamma(var9 + 1.0D) - var7);
            } while(this.nextDouble() > var11);

            var3 = var9;
        }

        return var3;
    }

    public double[] poissonianArray(double var1, int var3) {
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
                    var11 *= this.nextDouble();
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
                    var7 = var1 * var15 - Stat.logGamma(var1 + 1.0D);
                }

                while(true) {
                    var17 = Math.tan(3.141592653589793D * this.nextDouble());
                    var9 = var13 * var17 + var1;
                    if (var9 >= 0.0D) {
                        var9 = Math.floor(var9);
                        var11 = 0.9D * (1.0D + var17 * var17) * Math.exp(var9 * var15 - Stat.logGamma(var9 + 1.0D) - var7);
                        if (this.nextDouble() <= var11) {
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

    public double nextBinomial(double var1, int var3) {
        if (var1 >= 0.0D && var1 <= 1.0D) {
            double var4 = 0.0D;
            double var6 = 0.0D;
            double var8 = 0.0D;
            double var10 = 0.0D;
            double var12 = 0.0D;
            double var14 = -1.0D;
            double var16 = -1.0D;
            double var18 = -1.0D;
            byte var20 = -1;
            double var21 = 0.0D;
            double var23 = 0.0D;
            double var25 = 0.0D;
            double var27 = 0.0D;
            var10 = var1 <= 0.5D ? var1 : 1.0D - var1;
            var6 = (double)var3 * var10;
            int var29;
            if (var3 < 25) {
                var4 = 0.0D;

                for(var29 = 1; var29 <= var3; ++var29) {
                    if (this.nextDouble() < var10) {
                        ++var4;
                    }
                }
            } else {
                double var30;
                if (var6 < 1.0D) {
                    var30 = Math.exp(-var6);
                    var8 = 1.0D;

                    for(var29 = 0; var29 <= var3; ++var29) {
                        var8 *= this.nextDouble();
                        if (var8 < var30) {
                            break;
                        }
                    }

                    var4 = (double)(var29 <= var3 ? var29 : var3);
                } else {
                    if (var3 != var20) {
                        var21 = (double)var3;
                        var23 = Stat.logGamma(var21 + 1.0D);
                    }

                    if (var10 != var14) {
                        var16 = 1.0D - var10;
                        var12 = Math.log(var10);
                        var18 = Math.log(var16);
                    }

                    var30 = Math.sqrt(2.0D * var6 * var16);

                    do {
                        do {
                            do {
                                double var32 = 3.141592653589793D * this.nextDouble();
                                var25 = Math.tan(var32);
                                var27 = var30 * var25 + var6;
                            } while(var27 < 0.0D);
                        } while(var27 >= var21 + 1.0D);

                        var27 = Math.floor(var27);
                        var8 = 1.2D * var30 * (1.0D + var25 * var25) * Math.exp(var23 - Stat.logGamma(var27 + 1.0D) - Stat.logGamma(var21 - var27 + 1.0D) + var27 * var12 + (var21 - var27) * var18);
                    } while(this.nextDouble() > var8);

                    var4 = var27;
                }
            }

            if (var10 != var1) {
                var4 = (double)var3 - var4;
            }

            return var4;
        } else {
            throw new IllegalArgumentException("The probablity provided, " + var1 + ", must lie between 0 and 1)");
        }
    }

    public double[] binomialArray(double var1, int var3, int var4) {
        if (var3 < var4) {
            throw new IllegalArgumentException("Number of deviates requested, " + var4 + ", must be less than the number of trials, " + var3);
        } else if (var1 >= 0.0D && var1 <= 1.0D) {
            double[] var5 = new double[var4];
            double var6 = 0.0D;
            double var8 = 0.0D;
            double var10 = 0.0D;
            double var12 = 0.0D;
            double var14 = 0.0D;
            double var16 = -1.0D;
            double var18 = -1.0D;
            double var20 = -1.0D;
            int var22 = -1;
            double var23 = 0.0D;
            double var25 = 0.0D;
            double var27 = 0.0D;
            double var29 = 0.0D;
            double var32 = var1;

            for(int var34 = 0; var34 < var4; ++var34) {
                var12 = var32 <= 0.5D ? var32 : 1.0D - var32;
                var8 = (double)var3 * var12;
                int var31;
                if (var3 < 25) {
                    var6 = 0.0D;

                    for(var31 = 1; var31 <= var3; ++var31) {
                        if (this.nextDouble() < var12) {
                            ++var6;
                        }
                    }
                } else {
                    double var35;
                    if (var8 < 1.0D) {
                        var35 = Math.exp(-var8);
                        var10 = 1.0D;

                        for(var31 = 0; var31 <= var3; ++var31) {
                            var10 *= this.nextDouble();
                            if (var10 < var35) {
                                break;
                            }
                        }

                        var6 = (double)(var31 <= var3 ? var31 : var3);
                    } else {
                        if (var3 != var22) {
                            var23 = (double)var3;
                            var25 = Stat.logGamma(var23 + 1.0D);
                            var22 = var3;
                        }

                        if (var12 != var16) {
                            var18 = 1.0D - var12;
                            var14 = Math.log(var12);
                            var20 = Math.log(var18);
                            var16 = var12;
                        }

                        var35 = Math.sqrt(2.0D * var8 * var18);

                        while(true) {
                            do {
                                double var37 = 3.141592653589793D * this.nextDouble();
                                var27 = Math.tan(var37);
                                var29 = var35 * var27 + var8;
                            } while(var29 < 0.0D);

                            if (var29 < var23 + 1.0D) {
                                var29 = Math.floor(var29);
                                var10 = 1.2D * var35 * (1.0D + var27 * var27) * Math.exp(var25 - Stat.logGamma(var29 + 1.0D) - Stat.logGamma(var23 - var29 + 1.0D) + var29 * var14 + (var23 - var29) * var20);
                                if (this.nextDouble() <= var10) {
                                    var6 = var29;
                                    break;
                                }
                            }
                        }
                    }
                }

                if (var12 != var32) {
                    var6 = (double)var3 - var6;
                }

                var5[var34] = var6;
            }

            return var5;
        } else {
            throw new IllegalArgumentException("The probablity provided, " + var1 + ", must lie between 0 and 1)");
        }
    }

    public double nextPareto(double var1, double var3) {
        return Math.pow(1.0D - this.nextDouble(), -1.0D / var1) * var3;
    }

    public double[] paretoArray(double var1, double var3, int var5) {
        double[] var6 = new double[var5];

        for(int var7 = 0; var7 < var5; ++var7) {
            var6[var7] = Math.pow(1.0D - this.nextDouble(), -1.0D / var1) * var3;
        }

        return var6;
    }

    public double nextExponential(double var1, double var3) {
        return var1 - Math.log(1.0D - this.nextDouble()) * var3;
    }

    public double[] exponentialArray(double var1, double var3, int var5) {
        double[] var6 = new double[var5];

        for(int var7 = 0; var7 < var5; ++var7) {
            var6[var7] = var1 - Math.log(1.0D - this.nextDouble()) * var3;
        }

        return var6;
    }

    public double nextRayleigh(double var1) {
        return Math.sqrt(-2.0D * Math.log(1.0D - this.nextDouble())) * var1;
    }

    public double[] rayleighArray(double var1, int var3) {
        double[] var4 = new double[var3];

        for(int var5 = 0; var5 < var3; ++var5) {
            var4[var5] = Math.sqrt(-2.0D * Math.log(1.0D - this.nextDouble())) * var1;
        }

        return var4;
    }

    public double nextMinimalGumbel(double var1, double var3) {
        return Math.log(Math.log(1.0D / (1.0D - this.nextDouble()))) * var3 + var1;
    }

    public double[] minimalGumbelArray(double var1, double var3, int var5) {
        double[] var6 = new double[var5];

        for(int var7 = 0; var7 < var5; ++var7) {
            var6[var7] = Math.log(Math.log(1.0D / (1.0D - this.nextDouble()))) * var3 + var1;
        }

        return var6;
    }

    public double nextMaximalGumbel(double var1, double var3) {
        return var1 - Math.log(Math.log(1.0D / (1.0D - this.nextDouble()))) * var3;
    }

    public double[] maximalGumbelArray(double var1, double var3, int var5) {
        double[] var6 = new double[var5];

        for(int var7 = 0; var7 < var5; ++var7) {
            var6[var7] = var1 - Math.log(Math.log(1.0D / (1.0D - this.nextDouble()))) * var3;
        }

        return var6;
    }

    public double nextFrechet(double var1, double var3, double var5) {
        return Math.pow(1.0D / Math.log(1.0D / this.nextDouble()), 1.0D / var5) * var3 + var1;
    }

    public double[] frechetArray(double var1, double var3, double var5, int var7) {
        double[] var8 = new double[var7];

        for(int var9 = 0; var9 < var7; ++var9) {
            var8[var9] = Math.pow(1.0D / Math.log(1.0D / this.nextDouble()), 1.0D / var5) * var3 + var1;
        }

        return var8;
    }

    public double nextWeibull(double var1, double var3, double var5) {
        return Math.pow(-Math.log(1.0D - this.nextDouble()), 1.0D / var5) * var3 + var1;
    }

    public double[] weibullArray(double var1, double var3, double var5, int var7) {
        double[] var8 = new double[var7];

        for(int var9 = 0; var9 < var7; ++var9) {
            var8[var9] = Math.pow(-Math.log(1.0D - this.nextDouble()), 1.0D / var5) * var3 + var1;
        }

        return var8;
    }

    public double nextLogistic(double var1, double var3) {
        return 2.0D * var3 * Fmath.atanh(2.0D * this.nextDouble() - 1.0D) + var1;
    }

    public double[] logisticArray(double var1, double var3, int var5) {
        double[] var6 = new double[var5];

        for(int var7 = 0; var7 < var5; ++var7) {
            var6[var7] = 2.0D * var3 * Fmath.atanh(2.0D * this.nextDouble() - 1.0D) + var1;
        }

        return var6;
    }

    public double nextStudentT(int var1) {
        double var2 = 0.0D;
        StudentTfunct var4 = new StudentTfunct();
        var4.nu = var1;
        double var5 = 0.0D;
        double var7 = 100.0D;
        if (var1 > 2) {
            var7 = (double)(var1 / (var1 - 2));
        }

        double var9 = var5 - 5.0D * var7;
        double var11 = var5 + 5.0D * var7;
        double var13 = 1.0E-10D;
        RealRoot var15 = new RealRoot();
        var15.setTolerance(var13);
        var15.resetNaNexceptionToTrue();
        var15.supressLimitReachedMessage();
        var15.supressNaNmessage();
        boolean var16 = true;
        int var17 = 0;
        byte var18 = 10;

        while(var16) {
            var4.cfd = this.nextDouble();
            var2 = var15.falsePosition(var4, var9, var11);
            if (!Double.isNaN(var2)) {
                var16 = false;
            } else if (var17 > var18) {
                System.out.println("class: PsRandom,  method: studentT");
                System.out.println(var18 + " successive attempts at calculating a random Student's T deviate failed for values of nu = " + var1);
                System.out.println("NaN returned");
                var2 = 0.0D / 0.0;
                var16 = false;
            } else {
                ++var17;
            }
        }

        return var2;
    }

    public double[] studentTarray(int var1, int var2) {
        double[] var3 = new double[var2];
        StudentTfunct var4 = new StudentTfunct();
        var4.nu = var1;
        double var5 = 0.0D;
        double var7 = 100.0D;
        if (var1 > 2) {
            var7 = (double)(var1 / (var1 - 2));
        }

        double var9 = 1.0E-10D;
        double var11 = var5 - 5.0D * var7;
        double var13 = var5 + 5.0D * var7;

        for(int var15 = 0; var15 < var2; ++var15) {
            RealRoot var16 = new RealRoot();
            var16.setTolerance(var9);
            var16.resetNaNexceptionToTrue();
            var16.supressLimitReachedMessage();
            var16.supressNaNmessage();
            boolean var17 = true;
            int var18 = 0;
            byte var19 = 10;

            while(var17) {
                var4.cfd = this.nextDouble();
                double var20 = var16.falsePosition(var4, var11, var13);
                if (!Double.isNaN(var20)) {
                    var17 = false;
                    var3[var15] = var20;
                } else if (var18 > var19) {
                    System.out.println("class: PsRandom,  method: studentTarray");
                    System.out.println(var19 + " successive attempts at calculating a random gamma deviate failed for values of nu = " + var1);
                    System.out.println("NaN returned");
                    var3[var15] = 0.0D / 0.0;
                    var17 = false;
                } else {
                    ++var18;
                }
            }
        }

        return var3;
    }

    public double nextBeta(double var1, double var3) {
        return this.nextBeta(0.0D, 1.0D, var1, var3);
    }

    public double nextBeta(double var1, double var3, double var5, double var7) {
        double var9 = 0.0D;
        BetaFunct var11 = new BetaFunct();
        var11.alpha = var5;
        var11.beta = var7;
        var11.min = var1;
        var11.max = var3;
        double var12 = 1.0E-10D;
        double var14 = var1;
        double var16 = var3;
        RealRoot var18 = new RealRoot();
        var18.noLowerBoundExtension();
        var18.noUpperBoundExtension();
        var18.setTolerance(var12);
        var18.resetNaNexceptionToTrue();
        var18.supressLimitReachedMessage();
        var18.supressNaNmessage();
        boolean var19 = true;
        int var20 = 0;
        byte var21 = 10;

        while(var19) {
            var11.cfd = this.nextDouble();
            var9 = var18.falsePosition(var11, var14, var16);
            if (!Double.isNaN(var9)) {
                var19 = false;
            } else if (var20 > var21) {
                System.out.println("class: PsRandom,  method: nextBeta");
                System.out.println(var21 + " successive attempts at calculating a random beta deviate failed for values of min = " + var1 + ", max = " + var3 + ", alpha = " + var5 + ", beta = " + var7);
                System.out.println("NaN returned");
                var9 = 0.0D / 0.0;
                var19 = false;
            } else {
                ++var20;
            }
        }

        return var9;
    }

    public double[] betaArray(double var1, double var3, int var5) {
        return this.betaArray(0.0D, 1.0D, var1, var3, var5);
    }

    public double[] betaArray(double var1, double var3, double var5, double var7, int var9) {
        double[] var10 = new double[var9];
        BetaFunct var11 = new BetaFunct();
        var11.alpha = var5;
        var11.beta = var7;
        var11.min = var1;
        var11.max = var3;
        double var12 = 1.0E-10D;
        double var14 = var1;
        double var16 = var3;

        for(int var18 = 0; var18 < var9; ++var18) {
            RealRoot var19 = new RealRoot();
            var19.noLowerBoundExtension();
            var19.noUpperBoundExtension();
            var19.setTolerance(var12);
            var19.resetNaNexceptionToTrue();
            var19.supressLimitReachedMessage();
            var19.supressNaNmessage();
            boolean var20 = true;
            int var21 = 0;
            byte var22 = 10;

            while(var20) {
                var11.cfd = this.nextDouble();
                double var23 = var19.bisect(var11, var14, var16);
                if (!Double.isNaN(var23)) {
                    var20 = false;
                    var10[var18] = var23;
                } else if (var21 > var22) {
                    System.out.println("class: PsRandom,  method: betaArray");
                    System.out.println(var22 + " successive attempts at calculating a random beta deviate failed for values of min = " + var1 + ", max = " + var3 + ", alpha = " + var5 + ", beta = " + var7);
                    System.out.println("NaN returned");
                    var10[var18] = 0.0D / 0.0;
                    var20 = false;
                } else {
                    ++var21;
                }
            }
        }

        return var10;
    }

    public double nextGamma(double var1, double var3, double var5) {
        double var7 = 0.0D;
        GammaFunct var9 = new GammaFunct();
        var9.mu = var1;
        var9.beta = var3;
        var9.gamma = var5;
        double var10 = Math.sqrt(var5) * var3;
        double var12 = 1.0E-10D;
        double var14 = var1;
        double var16 = var1 + 5.0D * var10;
        if (var16 <= var1) {
            var16 += var10;
        }

        RealRoot var18 = new RealRoot();
        var18.noLowerBoundExtension();
        var18.setTolerance(var12);
        var18.resetNaNexceptionToTrue();
        var18.supressLimitReachedMessage();
        var18.supressNaNmessage();
        boolean var19 = true;
        int var20 = 0;
        byte var21 = 10;

        while(var19) {
            var9.cfd = this.nextDouble();
            var7 = var18.bisect(var9, var14, var16);
            if (!Double.isNaN(var7)) {
                var19 = false;
            } else if (var20 > var21) {
                System.out.println("class: PsRandom,  method: nextGamma");
                System.out.println(var21 + " successive attempts at calculating a random gamma deviate failed for values of mu = " + var1 + ", beta = " + var3 + ", gamma = " + var5);
                System.out.println("NaN returned");
                var7 = 0.0D / 0.0;
                var19 = false;
            } else {
                ++var20;
            }
        }

        return var7;
    }

    public double[] gammaArray(double var1, double var3, double var5, int var7) {
        double[] var8 = new double[var7];
        GammaFunct var9 = new GammaFunct();
        var9.mu = var1;
        var9.beta = var3;
        var9.gamma = var5;
        double var10 = Math.sqrt(var5) * var3;
        double var12 = 1.0E-10D;
        double var14 = var1;
        double var16 = var1 + 5.0D * var10;
        if (var16 <= var1) {
            var16 += var10;
        }

        for(int var18 = 0; var18 < var7; ++var18) {
            RealRoot var19 = new RealRoot();
            var19.noLowerBoundExtension();
            var19.setTolerance(var12);
            var19.resetNaNexceptionToTrue();
            var19.supressLimitReachedMessage();
            var19.supressNaNmessage();
            boolean var20 = true;
            int var21 = 0;
            byte var22 = 10;

            while(var20) {
                var9.cfd = this.nextDouble();
                double var23 = var19.bisect(var9, var14, var16);
                if (!Double.isNaN(var23)) {
                    var20 = false;
                    var8[var18] = var23;
                } else if (var21 > var22) {
                    System.out.println("class: PsRandom,  method: gammaArray");
                    System.out.println(var22 + " successive attempts at calculating a random gamma deviate failed for values of mu = " + var1 + ", beta = " + var3 + ", gamma = " + var5);
                    System.out.println("NaN returned");
                    var8[var18] = 0.0D / 0.0;
                    var20 = false;
                } else {
                    ++var21;
                }
            }
        }

        return var8;
    }

    public double nextErlang(double var1, int var3) {
        return this.nextGamma(0.0D, 1.0D / var1, (double)var3);
    }

    public double[] erlangArray(double var1, int var3, int var4) {
        return this.gammaArray(0.0D, 1.0D / var1, (double)var3, var4);
    }

    public double[] chiSquareArray(int var1, int var2) {
        if (var1 <= 0) {
            throw new IllegalArgumentException("The degrees of freedom [nu], " + var1 + ", must be greater than zero");
        } else {
            double[] var3 = new double[var2];
            ChiSquareFunct var4 = new ChiSquareFunct();
            var4.nu = var1;
            double var5 = 1.0E-10D;
            double var7 = 0.0D;
            double var9 = 5.0D * Math.sqrt((double)(2 * var1));

            for(int var11 = 0; var11 < var2; ++var11) {
                RealRoot var12 = new RealRoot();
                var12.noLowerBoundExtension();
                var12.setTolerance(var5);
                var12.resetNaNexceptionToTrue();
                var12.supressLimitReachedMessage();
                var12.supressNaNmessage();
                boolean var13 = true;
                int var14 = 0;
                byte var15 = 10;

                while(var13) {
                    var4.cfd = this.nextDouble();
                    double var16 = var12.falsePosition(var4, var7, var9);
                    if (!Double.isNaN(var16)) {
                        var13 = false;
                        var3[var11] = var16;
                    } else if (var14 > var15) {
                        System.out.println("class: PsRandom,  method: chiSquareArray");
                        System.out.println(var15 + " successive attempts at calculating a random chi square deviate failed for values of nu = " + var1);
                        System.out.println("NaN returned");
                        var3[var11] = 0.0D / 0.0;
                        var13 = false;
                    } else {
                        ++var14;
                    }
                }
            }

            return var3;
        }
    }

    public double nextChiSquare(int var1) {
        if (var1 <= 0) {
            throw new IllegalArgumentException("The degrees of freedom [nu], " + var1 + ", must be greater than zero");
        } else {
            double var2 = 0.0D;
            ChiSquareFunct var4 = new ChiSquareFunct();
            var4.nu = var1;
            double var5 = 1.0E-10D;
            double var7 = 0.0D;
            double var9 = 5.0D * Math.sqrt((double)(2 * var1));
            RealRoot var11 = new RealRoot();
            var11.noLowerBoundExtension();
            var11.setTolerance(var5);
            var11.resetNaNexceptionToTrue();
            var11.supressLimitReachedMessage();
            var11.supressNaNmessage();
            boolean var12 = true;
            int var13 = 0;
            byte var14 = 10;

            while(var12) {
                var4.cfd = this.nextDouble();
                var2 = var11.falsePosition(var4, var7, var9);
                if (!Double.isNaN(var2)) {
                    var12 = false;
                } else if (var13 > var14) {
                    System.out.println("class: PsRandom,  method: nextChiSqauare");
                    System.out.println(var14 + " successive attempts at calculating a random Chi Square deviate failed for values of nu = " + var1);
                    System.out.println("NaN returned");
                    var2 = 0.0D / 0.0;
                    var12 = false;
                } else {
                    ++var13;
                }
            }

            return var2;
        }
    }

    public double nextF(int var1, int var2) {
        double var3 = 0.0D / 0.0;
        Ffunct var5 = new Ffunct();
        var5.nu1 = var1;
        var5.nu2 = var2;
        double var6 = 1.0E-10D;
        double var8 = 0.0D;
        double var10 = 10.0D;
        if (var2 > 4) {
            var10 = 5.0D * Math.sqrt((double)(2 * var2 * var2 * (var1 + var2 - 2) / (var1 * (var2 - 2) * (var2 - 2) * (var2 - 4))));
        }

        RealRoot var12 = new RealRoot();
        var12.noLowerBoundExtension();
        var12.setTolerance(var6);
        var12.resetNaNexceptionToTrue();
        var12.supressLimitReachedMessage();
        var12.supressNaNmessage();
        boolean var13 = true;
        int var14 = 0;
        byte var15 = 10;

        while(var13) {
            var5.cfd = this.nextDouble();
            var3 = var12.falsePosition(var5, var8, var10);
            if (!Double.isNaN(var3)) {
                var13 = false;
            } else if (var14 > var15) {
                System.out.println("class: PsRandom,  method: nextF");
                System.out.println(var15 + " successive attempts at calculating a random F-distribution deviate failed for values of nu1 = " + var1 + ", nu2 = " + var2);
                System.out.println("NaN returned");
                var3 = 0.0D / 0.0;
                var13 = false;
            } else {
                ++var14;
            }
        }

        return var3;
    }

    public double[] fArray(int var1, int var2, int var3) {
        double[] var4 = new double[var3];
        Ffunct var5 = new Ffunct();
        var5.nu1 = var1;
        var5.nu2 = var2;
        double var6 = 1.0E-10D;
        double var8 = 0.0D;
        double var10 = 10.0D;
        double var12 = (double)var1;
        double var14 = (double)var2;
        if (var2 > 4) {
            var10 = 5.0D * Math.sqrt(2.0D * var14 * var14 * (var12 + var14 - 2.0D) / (var12 * (var14 - 2.0D) * (var14 - 2.0D) * (var14 - 4.0D)));
        }

        for(int var16 = 0; var16 < var3; ++var16) {
            RealRoot var17 = new RealRoot();
            var17.noLowerBoundExtension();
            var17.setTolerance(var6);
            var17.resetNaNexceptionToTrue();
            var17.supressLimitReachedMessage();
            var17.supressNaNmessage();
            boolean var18 = true;
            int var19 = 0;
            byte var20 = 10;

            while(var18) {
                var5.cfd = this.nextDouble();
                double var21 = var17.falsePosition(var5, var8, var10);
                if (!Double.isNaN(var21)) {
                    var18 = false;
                    var4[var16] = var21;
                } else if (var19 > var20) {
                    System.out.println("class: PsRandom,  method: fArray");
                    System.out.println(var20 + " successive attempts at calculating a random f-distribution deviate failed for values of nu1 = " + var1 + ", nu2 = " + var2);
                    System.out.println("NaN returned");
                    var4[var16] = 0.0D / 0.0;
                    var18 = false;
                } else {
                    ++var19;
                }
            }
        }

        return var4;
    }

    public int nextInteger(int var1, int var2) {
        boolean var3 = false;
        int var4;
        switch(this.methodOptionInteger) {
            case 1:
                ++var2;
                var4 = this.rr.nextInt(var2 - var1) + var1;
                break;
            case 2:
                var4 = (int)Math.round(this.nextDouble() * (double)(var2 - var1)) + var1;
                break;
            case 3:
                double var10000 = this.nextDouble();
                ++var2;
                var4 = (int)Math.floor(var10000 * (double)(var2 - var1)) + var1;
                break;
            default:
                throw new IllegalArgumentException("methodOptionInteger, " + this.methodOptionInteger + " is not recognised");
        }

        return var4;
    }

    public int nextInteger(int var1) {
        return this.nextInteger(0, var1);
    }

    public int[] integerArray(int var1, int var2) {
        int[] var3 = new int[var1];

        for(int var4 = 0; var4 < var1; ++var4) {
            var3[var4] = this.nextInteger(var2);
        }

        return var3;
    }

    public int[] integerArray(int var1, int var2, int var3) {
        int[] var4 = new int[var1];

        for(int var5 = 0; var5 < var1; ++var5) {
            var4[var5] = this.nextInteger(var3 - var2) + var2;
        }

        return var4;
    }

    public int[] noRepeatIntegerArray(int var1, int var2, int var3) {
        int[] var4 = new int[var1];
        boolean var5 = true;
        boolean var6 = true;
        int var7 = 0;
        boolean var8 = true;

        while(var5) {
            int var10 = this.nextInteger(var3 - var2) + var2;
            if (var7 == 0) {
                var4[0] = var10;
                var7 = 1;
            } else {
                var8 = true;

                for(int var9 = 0; var9 < var7; ++var9) {
                    if (var4[var9] == var10) {
                        var8 = false;
                    }
                }

                if (var8) {
                    var4[var7] = var10;
                    ++var7;
                }
            }

            if (var7 == var1) {
                var5 = false;
            }
        }

        return var4;
    }

    public int[] uniqueIntegerArray(int var1, int var2) {
        int var3 = var2 - var1;
        int[] var4 = this.uniqueIntegerArray(var3);

        for(int var5 = 0; var5 < var3 + 1; ++var5) {
            var4[var5] += var1;
        }

        return var4;
    }

    public int[] uniqueIntegerArray(int var1) {
        int var2 = var1 + 1;
        int[] var3 = new int[var2];
        boolean var4 = false;
        int var5 = 0;
        boolean[] var6 = new boolean[var2];

        int var7;
        for(var7 = 0; var7 < var2; ++var7) {
            var6[var7] = false;
        }

        while(!var4) {
            var7 = this.nextInteger(var1);
            if (!var6[var7]) {
                var3[var5] = var7;
                var6[var7] = true;
                ++var5;
                if (var5 == var2) {
                    var4 = true;
                }
            }
        }

        return var3;
    }

    public static long getSerialVersionUID() {
        return 1L;
    }
}
