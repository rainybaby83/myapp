//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.math;

import com.flanagan.io.FileOutput;
import java.util.ArrayList;

public class Minimisation {
    protected boolean iseOption = true;
    protected int nParam = 0;
    protected double[] paramValue = null;
    protected String[] paraName = null;
    protected double functValue = 0.0D;
    protected double lastFunctValNoCnstrnt = 0.0D;
    protected double minimum = 0.0D;
    protected int prec = 4;
    protected int field = 13;
    protected boolean convStatus = false;
    protected boolean suppressNoConvergenceMessage = false;
    protected int scaleOpt = 0;
    protected double[] scale = null;
    protected boolean penalty = false;
    protected boolean sumPenalty = false;
    protected int nConstraints = 0;
    protected int nSumConstraints = 0;
    protected int maxConstraintIndex = -1;
    protected ArrayList<Object> penalties = new ArrayList();
    protected ArrayList<Object> sumPenalties = new ArrayList();
    protected int[] penaltyCheck = null;
    protected int[] sumPenaltyCheck = null;
    protected double penaltyWeight = 1.0E30D;
    protected int[] penaltyParam = null;
    protected int[][] sumPenaltyParam = (int[][])null;
    protected double[][] sumPlusOrMinus = (double[][])null;
    protected int[] sumPenaltyNumber = null;
    protected double[] constraints = null;
    protected double constraintTolerance = 1.0E-4D;
    protected double[] sumConstraints = null;
    protected int constraintMethod = 0;
    protected int nMax = 3000;
    protected int nIter = 0;
    protected int konvge = 3;
    protected int kRestart = 0;
    protected double fTol = 1.0E-13D;
    protected double rCoeff = 1.0D;
    protected double eCoeff = 2.0D;
    protected double cCoeff = 0.5D;
    protected double[] startH = null;
    protected double[] step = null;
    protected double dStep = 0.5D;
    protected int minTest = 0;
    protected double simplexSd = 0.0D;

    public Minimisation() {
        this.iseOption = true;
    }

    public void suppressNoConvergenceMessage() {
        this.suppressNoConvergenceMessage = true;
    }

    public void supressNoConvergenceMessage() {
        this.suppressNoConvergenceMessage = true;
    }

    public void nelderMead(MinimisationFunction var1, double[] var2, double[] var3, double var4, int var6) {
        this.nelderMead((Object)var1, var2, var3, var4, var6);
    }

    public void nelderMead(MinimizationFunction var1, double[] var2, double[] var3, double var4, int var6) {
        this.nelderMead((Object)var1, var2, var3, var4, var6);
    }

    public void nelderMead(Object var1, double[] var2, double[] var3, double var4, int var6) {
        boolean var7 = false;
        int var8 = var2.length;
        if (this.maxConstraintIndex >= var8) {
            throw new IllegalArgumentException("You have entered more constrained parameters (" + this.maxConstraintIndex + ") than minimisation parameters (" + var8 + ")");
        } else {
            this.nParam = var8;
            this.convStatus = true;
            int var9 = var8 + 1;
            this.lastFunctValNoCnstrnt = 0.0D;
            if (this.scaleOpt < 2) {
                this.scale = new double[var8];
            }

            if (this.scaleOpt == 2 && this.scale.length != var2.length) {
                throw new IllegalArgumentException("scale array and initial estimate array are of different lengths");
            } else if (var3.length != var2.length) {
                throw new IllegalArgumentException("step array length " + var3.length + " and initial estimate array length " + var2.length + " are of different");
            } else {
                for(int var10 = 0; var10 < var8; ++var10) {
                    if (var3[var10] == 0.0D) {
                        if (var2[var10] != 0.0D) {
                            var3[var10] = var2[var10] * 0.1D;
                        } else {
                            var3[var10] = 1.0D;
                            System.out.println("As no step size has been entered for an itial estimate of zero an initial step size of unity has been used");
                            System.out.println("You are advised to repeat the minimization using one of the methods allowing the setting of an appropriate non-zero initial step size");
                        }
                    }
                }

                this.paramValue = new double[var8];
                this.startH = new double[var8];
                this.step = new double[var8];
                double[] var43 = new double[var8];
                double[][] var11 = new double[var9][var9];
                double[] var12 = new double[var9];
                double[] var13 = new double[var9];
                double[] var14 = new double[var9];
                double[] var15 = new double[var9];
                Integer var16;
                Double var17;
                int var18;
                int var19;
                if (this.penalty) {
                    var16 = (Integer)this.penalties.get(1);
                    this.nConstraints = var16;
                    this.penaltyParam = new int[this.nConstraints];
                    this.penaltyCheck = new int[this.nConstraints];
                    this.constraints = new double[this.nConstraints];
                    var17 = null;
                    var18 = 2;

                    for(var19 = 0; var19 < this.nConstraints; ++var19) {
                        var16 = (Integer)this.penalties.get(var18);
                        this.penaltyParam[var19] = var16;
                        ++var18;
                        var16 = (Integer)this.penalties.get(var18);
                        this.penaltyCheck[var19] = var16;
                        ++var18;
                        var17 = (Double)this.penalties.get(var18);
                        this.constraints[var19] = var17;
                        ++var18;
                    }
                }

                int var20;
                if (this.sumPenalty) {
                    var16 = (Integer)this.sumPenalties.get(1);
                    this.nSumConstraints = var16;
                    this.sumPenaltyParam = new int[this.nSumConstraints][];
                    this.sumPlusOrMinus = new double[this.nSumConstraints][];
                    this.sumPenaltyCheck = new int[this.nSumConstraints];
                    this.sumPenaltyNumber = new int[this.nSumConstraints];
                    this.sumConstraints = new double[this.nSumConstraints];
                    var17 = null;
                    Object var48 = null;
                    Double var49 = null;
                    var20 = 2;

                    for(int var21 = 0; var21 < this.nSumConstraints; ++var21) {
                        var16 = (Integer)this.sumPenalties.get(var20);
                        this.sumPenaltyNumber[var21] = var16;
                        ++var20;
                        int[] var45 = (int[])((int[])this.sumPenalties.get(var20));
                        this.sumPenaltyParam[var21] = var45;
                        ++var20;
                        double[] var50 = (double[])((double[])this.sumPenalties.get(var20));
                        this.sumPlusOrMinus[var21] = var50;
                        ++var20;
                        var16 = (Integer)this.sumPenalties.get(var20);
                        this.sumPenaltyCheck[var21] = var16;
                        ++var20;
                        var49 = (Double)this.sumPenalties.get(var20);
                        this.sumConstraints[var21] = var49;
                        ++var20;
                    }
                }

                int var44;
                for(var44 = 0; var44 < var8; ++var44) {
                    this.startH[var44] = var2[var44];
                }

                if (this.scaleOpt > 0) {
                    boolean var47 = false;

                    for(int var46 = 0; var46 < var8; ++var46) {
                        if (var2[var46] == 0.0D) {
                            var47 = true;
                        }
                    }

                    if (var47) {
                        System.out.println("Neler and Mead Simplex: a start value of zero precludes scaling");
                        System.out.println("Regression performed without scaling");
                        this.scaleOpt = 0;
                    }
                }

                label388:
                switch(this.scaleOpt) {
                    case 0:
                        var44 = 0;

                        while(true) {
                            if (var44 >= var8) {
                                break label388;
                            }

                            this.scale[var44] = 1.0D;
                            ++var44;
                        }
                    case 1:
                        var44 = 0;

                        while(true) {
                            if (var44 >= var8) {
                                break label388;
                            }

                            this.scale[var44] = 1.0D / var2[var44];
                            var3[var44] /= var2[var44];
                            var2[var44] = 1.0D;
                            ++var44;
                        }
                    case 2:
                        for(var44 = 0; var44 < var8; ++var44) {
                            var3[var44] *= this.scale[var44];
                            var2[var44] *= this.scale[var44];
                        }
                }

                this.fTol = var4;
                this.nMax = var6;
                this.nIter = 0;

                for(var44 = 0; var44 < var8; ++var44) {
                    this.step[var44] = var3[var44];
                    this.scale[var44] = this.scale[var44];
                }

                double var53 = 0.0D;

                for(var18 = 0; var18 < var8; ++var18) {
                    var53 = var2[var18];
                    var14[var18] = var53;
                    var15[var18] = var53;
                    var43[var18] = var53;
                }

                var18 = this.konvge;

                for(var19 = 0; var19 < var8; ++var19) {
                    var11[var19][var9 - 1] = var2[var19];
                }

                var12[var9 - 1] = this.functionValue(var1, var2);

                for(var19 = 0; var19 < var8; ++var19) {
                    var2[var19] += var3[var19];

                    for(var20 = 0; var20 < var8; ++var20) {
                        var11[var20][var19] = var2[var20];
                    }

                    var12[var19] = this.functionValue(var1, var2);
                    var2[var19] -= var3[var19];
                }

                double var51 = 0.0D;
                double var52 = 0.0D;
                double var23 = 0.0D;
                double var25 = 0.0D;
                double var29 = 0.0D;
                double var31 = 0.0D;
                double var33 = 0.0D;
                double var35 = 0.0D;
                int var37 = 0;
                boolean var38 = false;
                boolean var39 = false;
                boolean var40 = true;

                while(true) {
                    int var41;
                    do {
                        do {
                            if (!var40) {
                                for(var41 = 0; var41 < var8; ++var41) {
                                    var43[var41] = var11[var41][var37];
                                    this.paramValue[var41] = var43[var41] / this.scale[var41];
                                }

                                this.minimum = var51;
                                this.kRestart = this.konvge - var18;
                                return;
                            }

                            var25 = var12[0];
                            var51 = var25;
                            var37 = 0;
                            int var54 = 0;

                            for(var41 = 1; var41 < var9; ++var41) {
                                if (var12[var41] < var25) {
                                    var25 = var12[var41];
                                    var37 = var41;
                                }

                                if (var12[var41] > var51) {
                                    var51 = var12[var41];
                                    var54 = var41;
                                }
                            }

                            int var42;
                            for(var41 = 0; var41 < var8; ++var41) {
                                var35 = 0.0D;

                                for(var42 = 0; var42 < var9; ++var42) {
                                    var35 += var11[var41][var42];
                                }

                                var35 -= var11[var41][var54];
                                var13[var41] = var35 / (double)var8;
                            }

                            for(var41 = 0; var41 < var8; ++var41) {
                                var14[var41] = (1.0D + this.rCoeff) * var13[var41] - this.rCoeff * var11[var41][var54];
                            }

                            var52 = this.functionValue(var1, var14);
                            ++this.nIter;
                            if (var52 < var25) {
                                for(var41 = 0; var41 < var8; ++var41) {
                                    var15[var41] = var14[var41] * (1.0D + this.eCoeff) - this.eCoeff * var13[var41];
                                }

                                var23 = this.functionValue(var1, var15);
                                ++this.nIter;
                                if (var23 < var25) {
                                    for(var41 = 0; var41 < var8; ++var41) {
                                        var11[var41][var54] = var15[var41];
                                    }

                                    var12[var54] = var23;
                                } else {
                                    for(var41 = 0; var41 < var8; ++var41) {
                                        var11[var41][var54] = var14[var41];
                                    }

                                    var12[var54] = var52;
                                }
                            } else {
                                int var55 = 0;

                                for(var41 = 0; var41 < var9; ++var41) {
                                    if (var41 != var54 && var52 > var12[var41]) {
                                        ++var55;
                                    }
                                }

                                if (var55 == var8) {
                                    if (var52 <= var12[var54]) {
                                        for(var41 = 0; var41 < var8; ++var41) {
                                            var11[var41][var54] = var14[var41];
                                        }

                                        var12[var54] = var52;
                                    }

                                    for(var41 = 0; var41 < var8; ++var41) {
                                        var15[var41] = this.cCoeff * var11[var41][var54] + (1.0D - this.cCoeff) * var13[var41];
                                    }

                                    var23 = this.functionValue(var1, var15);
                                    ++this.nIter;
                                    if (var23 > var12[var54]) {
                                        var41 = 0;

                                        while(true) {
                                            if (var41 >= var9) {
                                                this.nIter += var9;
                                                break;
                                            }

                                            for(var42 = 0; var42 < var8; ++var42) {
                                                var11[var42][var41] = 0.5D * (var11[var42][var41] + var11[var42][var37]);
                                                var43[var42] = var11[var42][var41];
                                            }

                                            var12[var41] = this.functionValue(var1, var43);
                                            ++var41;
                                        }
                                    } else {
                                        for(var41 = 0; var41 < var8; ++var41) {
                                            var11[var41][var54] = var15[var41];
                                        }

                                        var12[var54] = var23;
                                    }
                                } else {
                                    for(var41 = 0; var41 < var8; ++var41) {
                                        var11[var41][var54] = var14[var41];
                                    }

                                    var12[var54] = var52;
                                }
                            }

                            var31 = 0.0D;
                            var51 = var12[0];
                            var37 = 0;

                            for(var41 = 0; var41 < var9; ++var41) {
                                var31 += var12[var41];
                                if (var51 > var12[var41]) {
                                    var51 = var12[var41];
                                    var37 = var41;
                                }
                            }

                            var31 /= (double)var9;
                            var33 = 0.0D;

                            for(var41 = 0; var41 < var9; ++var41) {
                                var35 = var12[var41] - var31;
                                var33 += var35 * var35;
                            }

                            var29 = Math.sqrt(var33 / (double)var8);
                            switch(this.minTest) {
                                case 0:
                                    if (var29 < var4) {
                                        var40 = false;
                                    }
                            }

                            this.minimum = var51;
                            if (!var40) {
                                for(var41 = 0; var41 < var8; ++var41) {
                                    var43[var41] = var11[var41][var37];
                                }

                                var12[var9 - 1] = var51;
                                this.simplexSd = var29;
                                --var18;
                                if (var18 > 0) {
                                    var40 = true;

                                    for(var41 = 0; var41 < var8; ++var41) {
                                        var43[var41] += var3[var41];

                                        for(var42 = 0; var42 < var8; ++var42) {
                                            var11[var42][var41] = var43[var42];
                                        }

                                        var12[var41] = this.functionValue(var1, var43);
                                        var43[var41] -= var3[var41];
                                    }
                                }
                            }
                        } while(!var40);
                    } while(this.nIter <= this.nMax);

                    if (!this.suppressNoConvergenceMessage) {
                        System.out.println("Maximum iteration number reached, in Minimisation.simplex(...)");
                        System.out.println("without the convergence criterion being satisfied");
                        System.out.println("Current parameter estimates and function value returned");
                    }

                    this.convStatus = false;

                    for(var41 = 0; var41 < var8; ++var41) {
                        var43[var41] = var11[var41][var37];
                    }

                    var12[var9 - 1] = var51;
                    var40 = false;
                }
            }
        }
    }

    public void nelderMead(MinimisationFunction var1, double[] var2, double[] var3, double var4) {
        int var6 = this.nMax;
        this.nelderMead(var1, var2, var3, var4, var6);
    }

    public void nelderMead(MinimizationFunction var1, double[] var2, double[] var3, double var4) {
        int var6 = this.nMax;
        this.nelderMead(var1, var2, var3, var4, var6);
    }

    public void nelderMead(MinimisationFunction var1, double[] var2, double[] var3, int var4) {
        double var5 = this.fTol;
        this.nelderMead(var1, var2, var3, var5, var4);
    }

    public void nelderMead(MinimizationFunction var1, double[] var2, double[] var3, int var4) {
        double var5 = this.fTol;
        this.nelderMead(var1, var2, var3, var5, var4);
    }

    public void nelderMead(MinimisationFunction var1, double[] var2, double[] var3) {
        double var4 = this.fTol;
        int var6 = this.nMax;
        this.nelderMead(var1, var2, var3, var4, var6);
    }

    public void nelderMead(MinimizationFunction var1, double[] var2, double[] var3) {
        double var4 = this.fTol;
        int var6 = this.nMax;
        this.nelderMead(var1, var2, var3, var4, var6);
    }

    public void nelderMead(MinimisationFunction var1, double[] var2, double var3, int var5) {
        int var6 = var2.length;
        double[] var7 = new double[var6];

        for(int var8 = 0; var8 < var6; ++var8) {
            var7[var8] = this.dStep * var2[var8];
        }

        this.nelderMead(var1, var2, var7, var3, var5);
    }

    public void nelderMead(MinimizationFunction var1, double[] var2, double var3, int var5) {
        int var6 = var2.length;
        double[] var7 = new double[var6];

        for(int var8 = 0; var8 < var6; ++var8) {
            var7[var8] = this.dStep * var2[var8];
        }

        this.nelderMead(var1, var2, var7, var3, var5);
    }

    public void nelderMead(MinimisationFunction var1, double[] var2, double var3) {
        int var5 = var2.length;
        int var6 = this.nMax;
        double[] var7 = new double[var5];

        for(int var8 = 0; var8 < var5; ++var8) {
            var7[var8] = this.dStep * var2[var8];
        }

        this.nelderMead(var1, var2, var7, var3, var6);
    }

    public void nelderMead(MinimizationFunction var1, double[] var2, double var3) {
        int var5 = var2.length;
        int var6 = this.nMax;
        double[] var7 = new double[var5];

        for(int var8 = 0; var8 < var5; ++var8) {
            var7[var8] = this.dStep * var2[var8];
        }

        this.nelderMead(var1, var2, var7, var3, var6);
    }

    public void nelderMead(MinimisationFunction var1, double[] var2, int var3) {
        int var4 = var2.length;
        double var5 = this.fTol;
        double[] var7 = new double[var4];

        for(int var8 = 0; var8 < var4; ++var8) {
            var7[var8] = this.dStep * var2[var8];
        }

        this.nelderMead(var1, var2, var7, var5, var3);
    }

    public void nelderMead(MinimizationFunction var1, double[] var2, int var3) {
        int var4 = var2.length;
        double var5 = this.fTol;
        double[] var7 = new double[var4];

        for(int var8 = 0; var8 < var4; ++var8) {
            var7[var8] = this.dStep * var2[var8];
        }

        this.nelderMead(var1, var2, var7, var5, var3);
    }

    public void nelderMead(MinimisationFunction var1, double[] var2) {
        int var3 = var2.length;
        int var4 = this.nMax;
        double var5 = this.fTol;
        double[] var7 = new double[var3];

        for(int var8 = 0; var8 < var3; ++var8) {
            var7[var8] = this.dStep * var2[var8];
        }

        this.nelderMead(var1, var2, var7, var5, var4);
    }

    public void nelderMead(MinimizationFunction var1, double[] var2) {
        int var3 = var2.length;
        int var4 = this.nMax;
        double var5 = this.fTol;
        double[] var7 = new double[var3];

        for(int var8 = 0; var8 < var3; ++var8) {
            var7[var8] = this.dStep * var2[var8];
        }

        this.nelderMead(var1, var2, var7, var5, var4);
    }

    protected double functionValue(Object var1, double[] var2) {
        return this.iseOption ? this.functionValue((MinimisationFunction)var1, var2) : this.functionValue((MinimizationFunction)var1, var2);
    }

    protected double functionValue(MinimisationFunction var1, double[] var2) {
        double[] var3 = new double[this.nParam];

        for(int var4 = 0; var4 < this.nParam; ++var4) {
            var3[var4] = var2[var4] / this.scale[var4];
        }

        boolean var5 = this.functionValueCommon(var2, var3);
        if (var5) {
            this.functValue = var1.function(var3);
            this.lastFunctValNoCnstrnt = this.functValue;
        }

        return this.functValue;
    }

    protected double functionValue(MinimizationFunction var1, double[] var2) {
        double[] var3 = new double[this.nParam];

        for(int var4 = 0; var4 < this.nParam; ++var4) {
            var3[var4] = var2[var4] / this.scale[var4];
        }

        boolean var5 = this.functionValueCommon(var2, var3);
        if (var5) {
            this.functValue = var1.function(var3);
            this.lastFunctValNoCnstrnt = this.functValue;
        }

        return this.functValue;
    }

    protected boolean functionValueCommon(double[] var1, double[] var2) {
        double var3 = this.lastFunctValNoCnstrnt;
        boolean var5 = true;
        boolean var6;
        int var13;
        if (this.penalty) {
            var6 = false;

            for(int var7 = 0; var7 < this.nConstraints; ++var7) {
                var13 = this.penaltyParam[var7];
                switch(this.penaltyCheck[var7]) {
                    case -1:
                        if (var2[var13] < this.constraints[var7]) {
                            this.functValue = var3 + this.penaltyWeight * Fmath.square(this.constraints[var7] - var2[var13]);
                            var5 = false;
                        }
                        break;
                    case 0:
                        if (var2[var13] < this.constraints[var7] * (1.0D - this.constraintTolerance)) {
                            this.functValue = var3 + this.penaltyWeight * Fmath.square(this.constraints[var7] * (1.0D - this.constraintTolerance) - var2[var13]);
                            var5 = false;
                        }

                        if (var2[var13] > this.constraints[var7] * (1.0D + this.constraintTolerance)) {
                            this.functValue = var3 + this.penaltyWeight * Fmath.square(var2[var13] - this.constraints[var7] * (1.0D + this.constraintTolerance));
                            var5 = false;
                        }
                        break;
                    case 1:
                        if (var2[var13] > this.constraints[var7]) {
                            this.functValue = var3 + this.penaltyWeight * Fmath.square(var2[var13] - this.constraints[var7]);
                            var5 = false;
                        }
                }
            }
        }

        if (this.sumPenalty) {
            var6 = false;
            double var14 = 0.0D;

            for(int var9 = 0; var9 < this.nSumConstraints; ++var9) {
                double var10 = 0.0D;

                for(int var12 = 0; var12 < this.sumPenaltyNumber[var9]; ++var12) {
                    var13 = this.sumPenaltyParam[var9][var12];
                    var14 = this.sumPlusOrMinus[var9][var12];
                    var10 += var2[var13] * var14;
                }

                switch(this.sumPenaltyCheck[var9]) {
                    case -1:
                        if (var10 < this.sumConstraints[var9]) {
                            this.functValue = var3 + this.penaltyWeight * Fmath.square(this.sumConstraints[var9] - var10);
                            var5 = false;
                        }
                        break;
                    case 0:
                        if (var10 < this.sumConstraints[var9] * (1.0D - this.constraintTolerance)) {
                            this.functValue = var3 + this.penaltyWeight * Fmath.square(this.sumConstraints[var9] * (1.0D - this.constraintTolerance) - var10);
                            var5 = false;
                        }

                        if (var10 > this.sumConstraints[var9] * (1.0D + this.constraintTolerance)) {
                            this.functValue = var3 + this.penaltyWeight * Fmath.square(var10 - this.sumConstraints[var9] * (1.0D + this.constraintTolerance));
                            var5 = false;
                        }
                        break;
                    case 1:
                        if (var10 > this.sumConstraints[var9]) {
                            this.functValue = var3 + this.penaltyWeight * Fmath.square(var10 - this.sumConstraints[var9]);
                            var5 = false;
                        }
                }
            }
        }

        return var5;
    }

    public void addConstraint(int var1, int var2, double var3) {
        this.penalty = true;
        if (this.penalties.isEmpty()) {
            this.penalties.add(new Integer(this.constraintMethod));
        }

        if (this.penalties.size() == 1) {
            this.penalties.add(new Integer(1));
        } else {
            int var5 = (Integer)this.penalties.get(1);
            ++var5;
            this.penalties.set(1, new Integer(var5));
        }

        this.penalties.add(new Integer(var1));
        this.penalties.add(new Integer(var2));
        this.penalties.add(new Double(var3));
        if (var1 > this.maxConstraintIndex) {
            this.maxConstraintIndex = var1;
        }

    }

    public void addConstraint(int[] var1, int[] var2, int var3, double var4) {
        ArrayMaths var6 = new ArrayMaths(var2);
        double[] var7 = var6.getArray_as_double();
        this.addConstraint(var1, var7, var3, var4);
    }

    public void addConstraint(int[] var1, double[] var2, int var3, double var4) {
        int var6 = var1.length;
        int var7 = var2.length;
        if (var6 != var7) {
            throw new IllegalArgumentException("num of parameters, " + var6 + ", does not equal number of parameter signs, " + var7);
        } else {
            this.sumPenalty = true;
            if (this.sumPenalties.isEmpty()) {
                this.sumPenalties.add(new Integer(this.constraintMethod));
            }

            if (this.sumPenalties.size() == 1) {
                this.sumPenalties.add(new Integer(1));
            } else {
                int var8 = (Integer)this.sumPenalties.get(1);
                ++var8;
                this.sumPenalties.set(1, new Integer(var8));
            }

            this.sumPenalties.add(new Integer(var6));
            this.sumPenalties.add(var1);
            this.sumPenalties.add(var2);
            this.sumPenalties.add(new Integer(var3));
            this.sumPenalties.add(new Double(var4));
            ArrayMaths var10 = new ArrayMaths(var1);
            int var9 = var10.getMaximum_as_int();
            if (var9 > this.maxConstraintIndex) {
                this.maxConstraintIndex = var9;
            }

        }
    }

    public void setConstraintMethod(int var1) {
        this.constraintMethod = var1;
        if (!this.penalties.isEmpty()) {
            this.penalties.set(0, new Integer(this.constraintMethod));
        }

    }

    public void removeConstraints() {
        int var1;
        int var2;
        if (!this.penalties.isEmpty()) {
            var1 = this.penalties.size();

            for(var2 = var1 - 1; var2 >= 0; --var2) {
                this.penalties.remove(var2);
            }
        }

        this.penalty = false;
        this.nConstraints = 0;
        if (!this.sumPenalties.isEmpty()) {
            var1 = this.sumPenalties.size();

            for(var2 = var1 - 1; var2 >= 0; --var2) {
                this.sumPenalties.remove(var2);
            }
        }

        this.sumPenalty = false;
        this.nSumConstraints = 0;
        this.maxConstraintIndex = -1;
    }

    public void setConstraintTolerance(double var1) {
        this.constraintTolerance = var1;
    }

    public void print(String var1, int var2) {
        this.prec = var2;
        this.print(var1);
    }

    public void print(int var1) {
        this.prec = var1;
        String var2 = "MinimisationOutput.txt";
        this.print(var2);
    }

    public void print(String var1) {
        if (var1.indexOf(46) == -1) {
            var1 = var1 + ".txt";
        }

        FileOutput var2 = new FileOutput(var1, 'n');
        var2.dateAndTimeln(var1);
        var2.println(" ");
        var2.println("Simplex minimisation, using the method of Nelder and Mead,");
        var2.println("of the function y = f(c[0], c[1], c[2] . . .)");
        this.paraName = new String[this.nParam];

        int var3;
        for(var3 = 0; var3 < this.nParam; ++var3) {
            this.paraName[var3] = "c[" + var3 + "]";
        }

        var2.println();
        if (!this.convStatus) {
            var2.println("Convergence criterion was not satisfied");
            var2.println("The following results are the current estimates on exiting the minimisation method");
            var2.println();
        }

        var2.println("Value of parameters at the minimum");
        var2.println(" ");
        var2.printtab(" ", this.field);
        var2.printtab("Value at", this.field);
        var2.printtab("Initial", this.field);
        var2.println("Initial");
        var2.printtab(" ", this.field);
        var2.printtab("mimium", this.field);
        var2.printtab("estimate", this.field);
        var2.println("step");

        for(var3 = 0; var3 < this.nParam; ++var3) {
            var2.printtab(this.paraName[var3], this.field);
            var2.printtab(Fmath.truncate(this.paramValue[var3], this.prec), this.field);
            var2.printtab(Fmath.truncate(this.startH[var3], this.prec), this.field);
            var2.println(Fmath.truncate(this.step[var3], this.prec));
        }

        var2.println();
        var2.println(" ");
        var2.printtab("Number of paramaters");
        var2.println(this.nParam);
        var2.printtab("Number of iterations taken");
        var2.println(this.nIter);
        var2.printtab("Maximum number of iterations allowed");
        var2.println(this.nMax);
        var2.printtab("Number of restarts taken");
        var2.println(this.kRestart);
        var2.printtab("Maximum number of restarts allowed");
        var2.println(this.konvge);
        var2.printtab("Standard deviation of the simplex at the minimum");
        var2.println(Fmath.truncate(this.simplexSd, this.prec));
        var2.printtab("Convergence tolerance");
        var2.println(this.fTol);
        switch(this.minTest) {
            case 0:
                if (this.convStatus) {
                    var2.println("simplex sd < the tolerance");
                } else {
                    var2.println("NOTE!!! simplex sd > the tolerance");
                }
            default:
                var2.println();
                var2.println("End of file");
                var2.close();
        }
    }

    public void print() {
        String var1 = "MinimisationOutput.txt";
        this.print(var1);
    }

    public boolean getConvStatus() {
        return this.convStatus;
    }

    public void setScale(int var1) {
        if (var1 >= 0 && var1 <= 1) {
            this.scaleOpt = var1;
        } else {
            throw new IllegalArgumentException("The argument must be 0 (no scaling) 1(initial estimates all scaled to unity) or the array of scaling factors");
        }
    }

    public void setScale(double[] var1) {
        this.scale = var1;
        this.scaleOpt = 2;
    }

    public double[] getScale() {
        return this.scale;
    }

    public void setMinTest(int var1) {
        if (var1 >= 0 && var1 <= 1) {
            this.minTest = var1;
        } else {
            throw new IllegalArgumentException("minTest must be 0 or 1");
        }
    }

    public int getMinTest() {
        return this.minTest;
    }

    public double getSimplexSd() {
        return this.simplexSd;
    }

    public double[] getParamValues() {
        return this.paramValue;
    }

    public double getMinimum() {
        return this.minimum;
    }

    public int getNiter() {
        return this.nIter;
    }

    public void setNmax(int var1) {
        this.nMax = var1;
    }

    public int getNmax() {
        return this.nMax;
    }

    public int getNrestarts() {
        return this.kRestart;
    }

    public void setNrestartsMax(int var1) {
        this.konvge = var1;
    }

    public int getNrestartsMax() {
        return this.konvge;
    }

    public void setNMreflect(double var1) {
        this.rCoeff = var1;
    }

    public double getNMreflect() {
        return this.rCoeff;
    }

    public void setNMextend(double var1) {
        this.eCoeff = var1;
    }

    public double getNMextend() {
        return this.eCoeff;
    }

    public void setNMcontract(double var1) {
        this.cCoeff = var1;
    }

    public double getNMcontract() {
        return this.cCoeff;
    }

    public void setTolerance(double var1) {
        this.fTol = var1;
    }

    public double getTolerance() {
        return this.fTol;
    }
}
