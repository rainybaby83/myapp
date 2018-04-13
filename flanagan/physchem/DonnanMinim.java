//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package flanagan.physchem;

import flanagan.math.Fmath;
import flanagan.math.Minimisation;
import flanagan.math.MinimisationFunction;

class DonnanMinim implements MinimisationFunction {
    public int numOfIons = 0;
    public double[] concnA = null;
    public double[] concnB = null;
    public double[] molesT = null;
    public double[] complex = null;
    public double[] excessConcnA = null;
    public double[] excessConcnB = null;
    public double[] excessComplex = null;
    public double[] assocConsts = null;
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
    public double[] partCoeff = null;
    public double[] partCoeffPot = null;
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
    private double[] start = null;
    private double[] step = null;
    private double[] param = null;

    public DonnanMinim(int var1) {
        this.numOfIons = var1;
        this.start = new double[this.numOfIons];
        this.step = new double[this.numOfIons];
        this.param = new double[this.numOfIons];
    }

    public double function(double[] var1) {
        double var2 = 0.0D;
        this.ionConcns(var1[0]);

        for(int var4 = 0; var4 < this.numOfIons; ++var4) {
            var2 += (this.concnB[var4] + this.complex[var4]) * this.charges[var4];
        }

        return var2 * var2;
    }

    public void ionConcns(double var1) {
        for(int var3 = 0; var3 < this.numOfIons; ++var3) {
            this.partCoeffPot[var3] = this.partCoeff[var3] * Math.exp(-var1 * this.charges[var3] * -1.60217646263E-19D / (1.380650324E-23D * this.temp));
        }

        if (!this.includeIc) {
            if (this.nonZeroAssocK < 2) {
                this.calcConcnsSingleK(var1);
            } else {
                this.calcConcnsMultiK(var1);
            }
        } else {
            this.calcConcnsMultiK(var1);
        }

    }

    public void calcConcnsSingleK(double var1) {
        for(int var3 = 0; var3 < this.numOfIons; ++var3) {
            if (this.assocConsts[var3] != 0.0D && this.ionophoreConcn != 0.0D) {
                double var4 = this.assocConsts[var3] * (this.volumeB + this.volumeA * this.partCoeffPot[var3]);
                double var6 = this.volumeB + this.volumeA * this.partCoeffPot[var3] + this.volumeB * this.assocConsts[var3] * this.ionophoreConcn - this.assocConsts[var3] * this.molesT[var3];
                double var8 = -this.molesT[var3];
                double var10 = var6 * var6 - 4.0D * var4 * var8;
                if (var10 < 0.0D) {
                    System.out.println("Class: DonnanMinim\nMethod: ionConcns\nthe square root term (b2-4ac) of the quadratic = " + var10);
                    System.out.println("this term was set to zero as the negative value MAY have arisen from rounding errors");
                    var10 = 0.0D;
                }

                double var12 = -0.5D * (var6 + Fmath.sign(var6) * Math.sqrt(var10));
                double var14 = var12 / var4;
                double var16 = var8 / var12;
                double var18 = this.molesT[var3] / (this.volumeA * this.partCoeffPot[var3] + this.volumeB);
                if (var14 >= 0.0D && var14 <= var18) {
                    if (var16 >= 0.0D && var16 <= var18) {
                        System.out.println("Class: DonnanMinim\nMethod: ionConcns");
                        System.out.println("error1: no physically meaningfull root");
                        System.out.println("root1 = " + var14 + " root2 = " + var16 + " limit = " + var18);
                        System.exit(0);
                    } else {
                        this.concnB[var3] = var14;
                        this.concnA[var3] = this.concnB[var3] * this.partCoeffPot[var3];
                        this.complex[var3] = this.assocConsts[var3] * this.ionophoreConcn * this.concnB[var3] / (1.0D + this.assocConsts[var3] * this.concnB[var3]);
                    }
                } else if (var16 >= 0.0D && var16 <= var18) {
                    if (var14 >= 0.0D && var14 <= var18) {
                        System.out.println("Class: DonnanMinim\nMethod: ionConcns");
                        System.out.println("error2: no physically meaningfull root");
                        System.out.println("root1 = " + var14 + " root2 = " + var16 + " limit = " + var18);
                        System.exit(0);
                    } else {
                        this.concnB[var3] = var16;
                        this.concnA[var3] = this.concnB[var3] * this.partCoeffPot[var3];
                        this.complex[var3] = this.assocConsts[var3] * this.ionophoreConcn * this.concnB[var3] / (1.0D + this.assocConsts[var3] * this.concnB[var3]);
                    }
                } else {
                    System.out.println("Class: DonnanMinim\nMethod: ionConcns");
                    System.out.println("error3: no physically meaningfull root");
                    System.out.println("root1 = " + var14 + " root2 = " + var16 + " limit = " + var18);
                    System.exit(0);
                }
            } else if (this.molesT[var3] == 0.0D) {
                this.concnB[var3] = 0.0D;
                this.concnA[var3] = 0.0D;
                this.complex[var3] = 0.0D;
            } else {
                this.concnB[var3] = this.molesT[var3] / (this.volumeA * this.partCoeffPot[var3] + this.volumeB);
                this.concnA[var3] = this.concnB[var3] * this.partCoeffPot[var3];
                this.complex[var3] = 0.0D;
            }
        }

    }

    public void calcConcnsMultiK(double var1) {
        for(int var3 = 0; var3 < this.numOfIons; ++var3) {
            if (this.molesT[var3] == 0.0D) {
                this.concnB[var3] = 0.0D;
                this.concnA[var3] = 0.0D;
                this.complex[var3] = 0.0D;
                this.excessConcnA[var3] = 0.0D;
                this.excessConcnB[var3] = 0.0D;
                this.excessComplex[var3] = 0.0D;
            } else {
                this.concnB[var3] = this.molesT[var3] / (this.volumeA * this.partCoeffPot[var3] + this.volumeB);
                this.concnA[var3] = this.concnB[var3] * this.partCoeffPot[var3];
                this.complex[var3] = 0.0D;
                this.excessConcnA[var3] = 0.0D;
                this.excessConcnB[var3] = 0.0D;
                this.excessComplex[var3] = 0.0D;
            }

            this.start[var3] = this.concnB[var3];
            this.step[var3] = 0.05D * this.start[var3];
        }

        Minimisation var6 = new Minimisation();
        DonnanConcn var4 = new DonnanConcn();
        var4.numOfIons = this.numOfIons;
        var4.concnA = this.concnA;
        var4.concnB = this.concnB;
        var4.molesT = this.molesT;
        var4.complex = this.complex;
        var4.excessConcnA = this.excessConcnA;
        var4.excessConcnB = this.excessConcnB;
        var4.excessComplex = this.excessComplex;
        var4.assocConsts = this.assocConsts;
        var4.indexK = this.indexK;
        var4.nonZeroAssocK = this.nonZeroAssocK;
        var4.radii = this.radii;
        var4.charges = this.charges;
        var4.ionophoreConcn = this.ionophoreConcn;
        var4.ionophoreRad = this.ionophoreRad;
        var4.volumeA = this.volumeA;
        var4.volumeB = this.volumeB;
        var4.interfacialArea = this.interfacialArea;
        var4.epsilonA = this.epsilonA;
        var4.epsilonB = this.epsilonB;
        var4.epsilonSternA = this.epsilonSternA;
        var4.epsilonSternB = this.epsilonSternB;
        var4.temp = this.temp;
        var4.partCoeffPot = this.partCoeffPot;
        var4.sternCap = this.sternCap;
        var4.sternDeltaA = this.sternDeltaA;
        var4.sternDeltaB = this.sternDeltaB;
        var4.chargeValue = this.chargeValue;
        var4.chargeSame = this.chargeSame;
        var4.interfacialCharge = this.interfacialCharge;
        var4.interfacialChargeDensity = this.interfacialChargeDensity;
        var4.potential = var1;
        var4.includeIc = this.includeIc;
        var6.nelderMead(var4, this.start, this.step, 1.0E-20D, 10000);
        this.param = var6.getParamValues();

        for(int var5 = 0; var5 < this.numOfIons; ++var5) {
            this.concnB[var5] = this.param[var5];
            this.concnA[var5] = this.concnB[var5] * this.partCoeffPot[var5];
        }

        this.interfacialCharge = var4.interfacialCharge;
        this.interfacialChargeDensity = var4.interfacialChargeDensity;
    }
}
