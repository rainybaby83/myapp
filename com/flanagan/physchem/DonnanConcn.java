//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.physchem;

import com.flanagan.math.Fmath;
import com.flanagan.math.Matrix;
import com.flanagan.math.MinimisationFunction;

class DonnanConcn implements MinimisationFunction {
    public int numOfIons = 0;
    public double[] concnA = null;
    public double[] concnB = null;
    public double[] molesT = null;
    public double[] complex = null;
    public double[] excessConcnA = null;
    public double[] excessConcnB = null;
    public double[] excessComplex = null;
    public double[] assocConsts = null;
    public double[] partCoeffPot = null;
    public int[] indexK = null;
    public int nonZeroAssocK = 0;
    public double[] radii = null;
    public double[] charges = null;
    public double ionophoreConcn = 0.0D;
    public double ionophoreRad = 0.0D;
    public double volumeA = 0.0D;
    public double volumeB = 0.0D;
    public double interfacialArea = 0.0D;
    public double epsilonA = 0.0D;
    public double epsilonB = 0.0D;
    public double epsilonSternA = 0.0D;
    public double epsilonSternB = 0.0D;
    public double temp = 298.15D;
    public double diffPotentialA = 0.0D;
    public double diffPotentialB = 0.0D;
    public double sternPotential = 0.0D;
    public double sternCap = 0.0D;
    public double sternDeltaA = 0.0D;
    public double sternDeltaB = 0.0D;
    public double chargeValue = 0.0D;
    public boolean chargeSame = true;
    public double interfacialChargeDensity = 0.0D;
    public double interfacialCharge = 0.0D;
    public boolean includeIc = true;
    public double potential = 0.0D;
    private double penalty = 1.0E50D;

    DonnanConcn() {
    }

    public double function(double[] var1) {
        double var2 = 0.0D;
        int var6;
        if (this.nonZeroAssocK > 0 && this.ionophoreConcn > 0.0D) {
            if (this.nonZeroAssocK == 1) {
                this.complex[this.indexK[0]] = this.assocConsts[this.indexK[0]] * var1[this.indexK[0]] * this.ionophoreConcn / (1.0D + this.assocConsts[this.indexK[0]] * var1[this.indexK[0]]);
            } else {
                double[] var4 = new double[this.nonZeroAssocK];
                double[][] var5 = new double[this.nonZeroAssocK][this.nonZeroAssocK];

                int var7;
                for(var6 = 0; var6 < this.nonZeroAssocK; ++var6) {
                    var4[var6] = this.assocConsts[this.indexK[var6]] * var1[this.indexK[var6]] * this.ionophoreConcn;

                    for(var7 = 0; var7 < this.nonZeroAssocK; ++var7) {
                        var5[var6][var7] = this.assocConsts[this.indexK[var6]] * var1[this.indexK[var6]];
                        if (var6 == var7) {
                            ++var5[var6][var7];
                        }
                    }
                }

                Matrix var12 = new Matrix(var5);
                var4 = var12.solveLinearSet(var4);

                for(var7 = 0; var7 < this.nonZeroAssocK; ++var7) {
                    this.complex[this.indexK[var7]] = var4[var7];
                }
            }
        }

        if (this.includeIc) {
            double var9 = Math.abs(this.interfaceCharge(var1, this.potential));
            this.excessConcentrations(var1, var9, this.potential);

            for(var6 = 0; var6 < this.numOfIons; ++var6) {
                double var13 = var1[var6] * (this.volumeB + this.partCoeffPot[var6] * this.volumeA) + this.excessConcnA[var6] * this.volumeA + (this.excessConcnB[var6] + this.complex[var6] + this.excessComplex[var6]) * this.volumeB - this.molesT[var6];
                var2 += var13 * var13;
                if (var1[var6] < 0.0D) {
                    var2 += var1[var6] * var1[var6] * this.penalty;
                }
            }
        } else {
            for(int var10 = 0; var10 < this.numOfIons; ++var10) {
                double var11 = var1[var10] * (this.volumeB + this.partCoeffPot[var10] * this.volumeA) + this.complex[var10] * this.volumeB - this.molesT[var10];
                var2 += var11 * var11;
                if (var1[var10] < 0.0D) {
                    var2 += var1[var10] * var1[var10] * this.penalty;
                }
            }
        }

        return var2;
    }

    public void excessConcentrations(double[] var1, double var2, double var4) {
        if (var4 == 0.0D) {
            for(int var6 = 0; var6 < this.numOfIons; ++var6) {
                this.excessConcnA[var6] = 0.0D;
                this.excessConcnB[var6] = 0.0D;
                this.excessComplex[var6] = 0.0D;
            }
        } else {
            double var17 = 0.0D;
            double var8 = 0.0D;
            double var10 = 0.0D;

            for(int var12 = 0; var12 < this.numOfIons; ++var12) {
                if (var4 > 0.0D) {
                    if (this.charges[var12] > 0.0D) {
                        var8 += var1[var12] * Math.abs(this.charges[var12]);
                        var10 += this.complex[var12] * Math.abs(this.charges[var12]);
                    } else {
                        var17 += var1[var12] * this.partCoeffPot[var12] * Math.abs(this.charges[var12]);
                    }
                } else if (this.charges[var12] < 0.0D) {
                    var8 += var1[var12] * Math.abs(this.charges[var12]);
                    var10 += this.complex[var12] * Math.abs(this.charges[var12]);
                } else {
                    var17 += var1[var12] * this.partCoeffPot[var12] * Math.abs(this.charges[var12]);
                }
            }

            double var18 = var2 / (var17 * this.volumeA);
            double var14 = var2 / ((var8 + var10) * this.volumeB);

            for(int var16 = 0; var16 < this.numOfIons; ++var16) {
                if (var4 > 0.0D) {
                    if (this.charges[var16] > 0.0D) {
                        this.excessConcnB[var16] = Math.abs(this.concnB[var16] * var14);
                        this.excessComplex[var16] = Math.abs(this.complex[var16] * var14);
                    } else {
                        this.excessConcnA[var16] = Math.abs(this.concnA[var16] * var18);
                    }
                } else if (this.charges[var16] < 0.0D) {
                    this.excessConcnB[var16] = Math.abs(this.concnB[var16] * var14);
                    this.excessComplex[var16] = Math.abs(this.complex[var16] * var14);
                } else {
                    this.excessConcnA[var16] = Math.abs(this.concnA[var16] * var18);
                }
            }
        }

    }

    public double interfaceCharge(double[] var1, double var2) {
        if (var2 == 0.0D) {
            this.interfacialCharge = 0.0D;
            this.interfacialChargeDensity = 0.0D;
            this.diffPotentialA = 0.0D;
            this.diffPotentialB = 0.0D;
            this.sternPotential = 0.0D;
        } else {
            double var4 = 0.0D;
            double var6 = 0.0D;
            double var8 = 0.0D;
            double var10 = 0.0D;
            double var12 = 0.0D;
            double var14 = 0.0D;
            double var16 = 0.0D;

            for(int var18 = 0; var18 < this.numOfIons; ++var18) {
                var14 += Math.abs(var1[var18] * this.charges[var18]);
                var12 += Math.abs(var1[var18] * this.charges[var18] * this.partCoeffPot[var18]);
                var16 += Math.abs(this.charges[var18]);
            }

            var16 /= (double)this.numOfIons;
            var14 /= 2.0D * var16;
            double var10000 = var12 / (2.0D * var16);
            double var32 = 1.2D * Math.sqrt(4.81771359576E24D * var14 * 1.380650324E-23D * this.temp * 8.854187817E-12D * this.epsilonB) * Fmath.sinh(-var16 * -1.60217646263E-19D * Math.abs(var2) / (2.761300648E-23D * this.temp));
            double var20 = var32;
            double var22 = 0.0D;
            double var24 = Math.abs(var2) * 1.0E-8D;
            short var26 = 10000;
            boolean var27 = true;
            int var28 = 0;
            int var29 = 0;
            double var30 = 0.0D;

            while(var27) {
                var10 = this.icFunct(var8, var2, var1);
                var22 = this.icFunct(var20, var2, var1);
                if (var22 * var10 > 0.0D) {
                    ++var28;
                    if (var28 > 10) {
                        throw new IllegalArgumentException("iExpandQ has reached its limit");
                    }

                    var30 = var20 - var8;
                    var20 += var30;
                } else {
                    var27 = false;
                }
            }

            if (Math.abs(var10) <= var24) {
                var4 = var8;
            } else if (Math.abs(var22) <= var24) {
                var4 = var20;
            } else {
                var27 = true;

                while(var27) {
                    var4 = (var8 + var20) / 2.0D;
                    var6 = this.icFunct(var4, var2, var1);
                    if (Math.abs(var6) <= var24) {
                        var27 = false;
                    } else if (var10 * var6 > 0.0D) {
                        var10 = var6;
                        var8 = var4;
                    } else {
                        var20 = var4;
                    }

                    ++var29;
                    if (var29 > var26) {
                        System.out.println("Class: DonnanConcn\nMethod: interfaceCharge");
                        System.out.println("Maximum iterations in bisection procedure exceeded\nCurrent value of interface charge returned");
                        var27 = false;
                    }
                }
            }

            this.interfacialCharge = var4;
            this.interfacialChargeDensity = var4 / this.interfacialArea;
        }

        return this.interfacialCharge / 96485.34158524018D;
    }

    public double icFunct(double var1, double var3, double[] var5) {
        double var6 = Math.abs(var1);
        double var8 = Fmath.sign(var3);
        double var10;
        double var12;
        double var40;
        if (this.chargeSame) {
            var10 = 0.0D;
            var12 = 0.0D;

            for(int var14 = 0; var14 < this.numOfIons; ++var14) {
                var10 += this.concnA[var14];
                var12 += this.concnB[var14] + this.complex[var14];
            }

            var10 /= 2.0D;
            var12 /= 2.0D;
            var40 = 2.761300648E-23D * this.temp / (-this.chargeValue * -1.60217646263E-19D);
            this.diffPotentialA = var8 * var40 * Fmath.asinh(var6 / Math.sqrt(4.81771359576E24D * var10 * 1.380650324E-23D * this.temp * 8.854187817E-12D * this.epsilonA));
            this.diffPotentialB = var8 * var40 * Fmath.asinh(var6 / Math.sqrt(4.81771359576E24D * var12 * 1.380650324E-23D * this.temp * 8.854187817E-12D * this.epsilonB));
        } else {
            var10 = 0.0D;
            var12 = 0.0D;
            var40 = 0.0D;
            double var16 = 0.0D;
            double var18 = 1.1D * var3;
            double var20 = var18;
            double var22 = 0.0D;
            double var24 = Math.abs(var1) * 0.1D;
            short var26 = 1000;
            boolean var27 = true;
            int var28 = 0;
            int var29 = 0;
            double var30 = 0.0D;

            while(var27) {
                var16 = this.phiAfunct(var1, var40, var5);
                var22 = this.phiAfunct(var1, var20, var5);
                if (var22 * var16 > 0.0D) {
                    ++var28;
                    if (var28 > 10) {
                        throw new IllegalArgumentException("iExpandP (partition A) has reached its limit");
                    }

                    var30 = var20 - var40;
                    var20 += var30;
                } else {
                    var27 = false;
                }
            }

            var27 = true;

            while(var27) {
                var10 = (var40 + var20) / 2.0D;
                var12 = this.phiAfunct(var1, var10, var5);
                if (Math.abs(var12) <= var24) {
                    this.diffPotentialA = var8 * var10;
                    var27 = false;
                } else if (var16 * var12 > 0.0D) {
                    var16 = var12;
                    var40 = var10;
                } else {
                    var22 = var12;
                    var20 = var10;
                }

                ++var29;
                if (var29 > var26) {
                    System.out.println("phiA = " + var10 + " sigma = " + var1 + " funcM = " + var12 + " tol = " + var24);
                    this.diffPotentialA = var8 * var10;
                    var27 = false;
                }
            }

            double var32 = 0.0D;
            double var34 = 0.0D;
            double var36 = -1.1D * var3;
            double var38 = var36;
            var24 = Math.abs(var3) * 1.0E-5D;
            if (var24 == 0.0D) {
                var24 = 1.0E-6D;
            }

            int var41 = 100000;
            var27 = true;
            var28 = 0;
            var29 = 0;
            var30 = 0.0D;

            while(var27) {
                var16 = this.phiAfunct(var1, var34, var5);
                var22 = this.phiAfunct(var1, var38, var5);
                if (var22 * var16 > 0.0D) {
                    ++var28;
                    if (var28 > 10) {
                        throw new IllegalArgumentException("iExpandP (partition B) has reached its limit");
                    }

                    var30 = var38 - var34;
                    var38 += var30;
                } else {
                    var27 = false;
                }
            }

            if (Math.abs(var22) <= var24) {
                var32 = var38;
            } else {
                var27 = true;

                while(var27) {
                    var32 = (var34 + var38) / 2.0D;
                    var12 = this.phiAfunct(var1, var32, var5);
                    if (Math.abs(var12) <= var24) {
                        var27 = false;
                    } else if (var16 * var12 > 0.0D) {
                        var16 = var12;
                        var34 = var32;
                    } else {
                        var38 = var32;
                    }

                    ++var29;
                    if (var29 > var41) {
                        System.out.println("Class: DonnanConcn\nMethod: icFunct");
                        System.out.println("Maximum iterations in bisection B procedure exceeded\nCurrent value of interface charge returned");
                        System.out.println("phiB = " + var32 + " maxPhiB = " + var36 + " funcM = " + var12 + " tol = " + var24);
                        var27 = false;
                    }
                }
            }

            this.diffPotentialB = var8 * var32;
        }

        this.sternCapacitance(var5, var1, this.diffPotentialA, -this.diffPotentialB);
        this.sternPotential = var8 * var6 / this.sternCap;
        return var3 - (this.diffPotentialA + this.diffPotentialB + this.sternPotential);
    }

    public double phiAfunct(double var1, double var3, double[] var5) {
        double var6 = 0.0D;
        double var8 = Fmath.sign(var1);
        double var10 = 1.7708375634E-11D * this.epsilonA * 1.380650324E-23D * this.temp * 6.0221419947E23D;
        double var12 = var3 * -1.60217646263E-19D / (1.380650324E-23D * this.temp);

        for(int var14 = 0; var14 < this.numOfIons; ++var14) {
            var6 += var10 * var5[var14] * this.partCoeffPot[var14] * (Math.exp(this.charges[var14] * var12) - 1.0D);
        }

        if (var6 < 0.0D) {
            var8 = -var8;
            var6 = -var6;
        }

        double var16 = var1 - var8 * Math.sqrt(var6);
        return var16;
    }

    public double phiBfunct(double var1, double var3, double[] var5) {
        double var6 = 0.0D;
        double var8 = Fmath.sign(var1);
        double var10 = 1.7708375634E-11D * this.epsilonB * 1.380650324E-23D * this.temp * 6.0221419947E23D;
        double var12 = var3 * -1.60217646263E-19D / (1.380650324E-23D * this.temp);

        for(int var14 = 0; var14 < this.numOfIons; ++var14) {
            var6 += var10 * (var5[var14] + this.complex[var14]) * (Math.exp(this.charges[var14] * var12) - 1.0D);
        }

        if (var6 < 0.0D) {
            var8 = -var8;
            var6 = -var6;
        }

        double var16 = var1 - var8 * Math.sqrt(var6);
        return var16;
    }

    public void sternCapacitance(double[] var1, double var2, double var4, double var6) {
        double var8 = 0.0D;
        double var10 = 0.0D;
        this.sternDeltaA = 0.0D;
        this.sternDeltaB = 0.0D;
        double var12 = 1.60217646263E-19D / (1.380650324E-23D * this.temp);

        for(int var14 = 0; var14 < this.numOfIons; ++var14) {
            this.sternDeltaA += this.radii[var14] * var1[var14] * this.partCoeffPot[var14] * Math.exp(var12 * var4 * this.charges[var14]);
            this.sternDeltaB += (this.radii[var14] * var1[var14] + this.ionophoreRad * this.complex[var14]) * Math.exp(-var12 * var6 * this.charges[var14]);
            var8 += var1[var14] * this.partCoeffPot[var14] * Math.exp(var12 * var4 * this.charges[var14]);
            var10 += (var1[var14] + this.complex[var14]) * Math.exp(-var12 * var6 * this.charges[var14]);
        }

        this.sternDeltaA /= var8;
        this.sternDeltaB /= var10;
        this.sternCap = 8.854187817E-12D * this.epsilonSternA * this.epsilonSternB / (this.sternDeltaA * this.epsilonSternB + this.sternDeltaB * this.epsilonSternA);
    }
}
