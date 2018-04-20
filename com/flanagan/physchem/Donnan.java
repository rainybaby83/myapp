//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.physchem;

import com.flanagan.io.Db;
import com.flanagan.io.FileOutput;
import com.flanagan.math.Conv;
import com.flanagan.math.Fmath;
import com.flanagan.math.Minimisation;
import com.flanagan.physprop.IonicRadii;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Donnan {
    private ArrayList<Object> arrayl = new ArrayList();
    private int numOfIons = 0;
    private int numOfAnions = 0;
    private int numOfCations = 0;
    private String[] ionNames = null;
    private double[] concnA = null;
    private double[] concnB = null;
    private double[] molesT = null;
    private double[] complex = null;
    private double[] excessConcnA = null;
    private double[] excessConcnB = null;
    private double[] excessComplex = null;
    private int[] indexC = null;
    private int nonZeroConcns = 0;
    private double[] assocConsts = null;
    private int[] indexK = null;
    private int nonZeroAssocK = 0;
    private double[] radii = null;
    private boolean radiusType = true;
    private double[] charges = null;
    private double tol = 1.0E-6D;
    private String ionophore = "ionophore";
    private double ionophoreConcn = 0.0D;
    private double freeIonophoreConcn = 0.0D;
    private double ionophoreRad = 0.0D;
    private boolean ionophoreSet = false;
    private double volumeA = 0.0D;
    private double volumeB = 0.0D;
    private double interfacialArea = 0.0D;
    private boolean volumeSet = false;
    private double epsilonA = 0.0D;
    private double epsilonB = 0.0D;
    private double epsilonSternA = 0.0D;
    private double epsilonSternB = 0.0D;
    private boolean epsilonSet = false;
    private double temp = 298.15D;
    private boolean tempSet = false;
    private double[] deltaMu0 = null;
    private double[] partCoeff = null;
    private double[] partCoeffPot = null;
    private boolean[] indexPC = null;
    private double donnanPotential = 0.0D;
    private double diffPotentialA = 0.0D;
    private double diffPotentialB = 0.0D;
    private double sternPotential = 0.0D;
    private double estimate = 0.0D;
    private double step = 0.0D;
    private double tolerance = 1.0E-20D;
    private int nMaxIter = 10000;
    private int numIterations = 0;
    private double minimum = 1.0E300D;
    private double sternCap = 0.0D;
    private double diffCapA = 0.0D;
    private double diffCapB = 0.0D;
    private double donnanCap = 0.0D;
    private double sternDeltaA = 0.0D;
    private double sternDeltaB = 0.0D;
    private double chargeValue = 0.0D;
    private boolean chargeSame = true;
    private double interfacialChargeDensity = 0.0D;
    private double interfacialCharge = 0.0D;
    private boolean includeIc = true;
    private double[] ratioA = null;
    private double[] ratioB = null;
    private double[] ratioC = null;
    private double recipKappaA = 0.0D;
    private double recipKappaB = 0.0D;

    public Donnan() {
    }

    public void setHydratedRadii() {
        this.radiusType = true;
    }

    public void setBareRadii() {
        this.radiusType = false;
    }

    public void ignoreInterfaceCharge() {
        this.includeIc = false;
    }

    public void includeInterfaceCharge() {
        this.includeIc = true;
    }

    public void setIon(String var1, double var2, double var4, double var6, double var8, int var10) {
        this.arrayl.add(var1);
        this.arrayl.add(new Double(var2));
        this.arrayl.add(new Double(var4));
        if (var2 > 0.0D || var4 > 0.0D) {
            ++this.nonZeroConcns;
        }

        this.arrayl.add(new Double(var6));
        if (var6 != 0.0D) {
            ++this.nonZeroAssocK;
        }

        this.arrayl.add(new Double(var8));
        this.arrayl.add(new Integer(var10));
        this.arrayl.add(new Double(-1.0D));
        ++this.numOfIons;
    }

    public void setIon(double var1, String var3, double var4, double var6, double var8, double var10, int var12) {
        this.arrayl.add(var3);
        this.arrayl.add(new Double(var4));
        this.arrayl.add(new Double(var6));
        if (var4 > 0.0D || var6 > 0.0D) {
            ++this.nonZeroConcns;
        }

        this.arrayl.add(new Double(var8));
        if (var8 != 0.0D) {
            ++this.nonZeroAssocK;
        }

        this.arrayl.add(new Double(var10));
        this.arrayl.add(new Integer(var12));
        this.arrayl.add(new Double(var1));
        ++this.numOfIons;
    }

    public void setIon(String var1, double var2, double var4, double var6, int var8) {
        this.arrayl.add(var1);
        this.arrayl.add(new Double(var2));
        this.arrayl.add(new Double(var4));
        if (var2 > 0.0D || var4 > 0.0D) {
            ++this.nonZeroConcns;
        }

        this.arrayl.add(new Double(0.0D));
        this.arrayl.add(new Double(var6));
        this.arrayl.add(new Integer(var8));
        this.arrayl.add(new Double(-1.0D));
        ++this.numOfIons;
    }

    public void setIon(double var1, String var3, double var4, double var6, double var8, int var10) {
        this.arrayl.add(var3);
        this.arrayl.add(new Double(var4));
        this.arrayl.add(new Double(var6));
        if (var4 > 0.0D || var6 > 0.0D) {
            ++this.nonZeroConcns;
        }

        this.arrayl.add(new Double(0.0D));
        this.arrayl.add(new Double(var8));
        this.arrayl.add(new Integer(var10));
        this.arrayl.add(new Double(var1));
        ++this.numOfIons;
    }

    public void setIon(String var1, double var2, double var4, double var6) {
        IonicRadii var8 = new IonicRadii();
        this.arrayl.add(var1);
        this.arrayl.add(new Double(var2));
        this.arrayl.add(new Double(var4));
        if (var2 > 0.0D || var4 > 0.0D) {
            ++this.nonZeroConcns;
        }

        this.arrayl.add(new Double(var6));
        if (var6 != 0.0D) {
            ++this.nonZeroAssocK;
        }

        double var9 = 0.0D;
        if (this.radiusType) {
            var9 = IonicRadii.hydratedRadius(var1);
        } else {
            var9 = IonicRadii.radius(var1);
        }

        String var12;
        if (var9 == 0.0D) {
            String var11 = var1 + " radius is not in the IonicRadii list\n";
            var12 = "Please enter radius in metres\n";
            var9 = Db.readDouble(var11 + var12);
        }

        this.arrayl.add(new Double(var9));
        boolean var14 = false;
        int var15 = IonicRadii.charge(var1);
        if (var15 == 0) {
            var12 = var1 + " charge is not in the IonicRadii list\n";
            String var13 = "Please enter charge, e.g +2";
            var15 = Db.readInt(var12 + var13);
        }

        this.arrayl.add(new Integer(var15));
        this.arrayl.add(new Double(-1.0D));
        ++this.numOfIons;
    }

    public void setIon(double var1, String var3, double var4, double var6, double var8) {
        IonicRadii var10 = new IonicRadii();
        this.arrayl.add(var3);
        this.arrayl.add(new Double(var4));
        this.arrayl.add(new Double(var6));
        if (var4 > 0.0D || var6 > 0.0D) {
            ++this.nonZeroConcns;
        }

        this.arrayl.add(new Double(var8));
        if (var8 != 0.0D) {
            ++this.nonZeroAssocK;
        }

        double var11 = 0.0D;
        String var14;
        String var15;
        if (this.includeIc) {
            if (this.radiusType) {
                var11 = IonicRadii.hydratedRadius(var3);
            } else {
                var11 = IonicRadii.radius(var3);
            }

            if (var11 == 0.0D) {
                String var13 = var3 + " radius is not in the IonicRadii list\n";
                var14 = "Please enter radius in metres\n";
                var15 = "Enter 0.0 if you wish interfacial charge to be neglected";
                var11 = Db.readDouble(var13 + var14 + var15);
                if (var11 == 0.0D) {
                    this.includeIc = false;
                }
            }
        }

        this.arrayl.add(new Double(var11));
        boolean var16 = false;
        int var17 = IonicRadii.charge(var3);
        if (var17 == 0) {
            var14 = var3 + " charge is not in the IonicRadii list\n";
            var15 = "Please enter charge, e.g +2";
            var17 = Db.readInt(var14 + var15);
        }

        this.arrayl.add(new Integer(var17));
        this.arrayl.add(new Double(var1));
        ++this.numOfIons;
    }

    public void setIon(String var1, double var2, double var4) {
        IonicRadii var6 = new IonicRadii();
        this.arrayl.add(var1);
        this.arrayl.add(new Double(var2));
        this.arrayl.add(new Double(var4));
        if (var2 > 0.0D || var4 > 0.0D) {
            ++this.nonZeroConcns;
        }

        this.arrayl.add(new Double(0.0D));
        double var7 = 0.0D;
        if (this.radiusType) {
            var7 = IonicRadii.hydratedRadius(var1);
        } else {
            var7 = IonicRadii.radius(var1);
        }

        String var10;
        if (var7 == 0.0D) {
            String var9 = var1 + " radius is not in the IonicRadii list\n";
            var10 = "Please enter radius in metres\n";
            var7 = Db.readDouble(var9 + var10);
            if (var7 == 0.0D) {
                this.includeIc = false;
            }
        }

        this.arrayl.add(new Double(var7));
        boolean var12 = false;
        int var13 = IonicRadii.charge(var1);
        if (var13 == 0) {
            var10 = var1 + " charge is not in the IonicRadii list\n";
            String var11 = "Please enter charge, e.g +2";
            var13 = Db.readInt(var10 + var11);
        }

        this.arrayl.add(new Integer(var13));
        this.arrayl.add(new Double(-1.0D));
        ++this.numOfIons;
    }

    public void setIon(double var1, String var3, double var4, double var6) {
        IonicRadii var8 = new IonicRadii();
        this.arrayl.add(var3);
        this.arrayl.add(new Double(var4));
        this.arrayl.add(new Double(var6));
        if (var4 > 0.0D || var6 > 0.0D) {
            ++this.nonZeroConcns;
        }

        this.arrayl.add(new Double(0.0D));
        double var9 = 0.0D;
        String var12;
        String var13;
        if (this.includeIc) {
            if (this.radiusType) {
                var9 = IonicRadii.hydratedRadius(var3);
            } else {
                var9 = IonicRadii.radius(var3);
            }

            if (var9 == 0.0D) {
                String var11 = var3 + " radius is not in the IonicRadii list\n";
                var12 = "Please enter radius in metres\n";
                var13 = "Enter 0.0 if you wish interfacial charge to be neglected";
                var9 = Db.readDouble(var11 + var12 + var13);
                if (var9 == 0.0D) {
                    this.includeIc = false;
                }
            }
        }

        this.arrayl.add(new Double(var9));
        boolean var14 = false;
        int var15 = IonicRadii.charge(var3);
        if (var15 == 0) {
            var12 = var3 + " charge is not in the IonicRadii list\n";
            var13 = "Please enter charge, e.g +2";
            var15 = Db.readInt(var12 + var13);
        }

        this.arrayl.add(new Integer(var15));
        this.arrayl.add(new Double(var1));
        ++this.numOfIons;
    }

    public void setIonophore(double var1, double var3) {
        this.ionophoreConcn = var1 * 1000.0D;
        this.ionophoreRad = var3;
        this.ionophoreSet = true;
    }

    public void setIonophore(String var1, double var2, double var4) {
        this.ionophore = var1;
        this.ionophoreConcn = var2 * 1000.0D;
        this.ionophoreRad = var4;
        this.ionophoreSet = true;
    }

    public void setIonophore(String var1, double var2) {
        this.ionophore = var1;
        this.ionophoreConcn = var2 * 1000.0D;
        this.includeIc = false;
        this.ionophoreSet = true;
    }

    public void setIonophore(double var1) {
        this.ionophoreConcn = var1 * 1000.0D;
        this.includeIc = false;
        this.ionophoreSet = true;
    }

    public void setVolumes(double var1, double var3, double var5) {
        this.volumeA = var1;
        this.volumeB = var3;
        this.interfacialArea = var5;
        this.volumeSet = true;
    }

    public void setVolumes(double var1, double var3) {
        this.volumeA = var1;
        this.volumeB = var3;
        this.includeIc = false;
        this.volumeSet = true;
    }

    public void setRelPerm(double var1, double var3, double var5, double var7) {
        this.epsilonA = var1;
        this.epsilonB = var3;
        this.epsilonSternA = var5;
        this.epsilonSternB = var7;
        this.epsilonSet = true;
    }

    public void setRelPerm(double var1, double var3) {
        this.epsilonA = var1;
        this.epsilonB = var3;
        this.includeIc = false;
        this.epsilonSet = true;
    }

    public void setTemp(double var1) {
        this.temp = var1 - -273.15D;
        this.tempSet = true;
    }

    public void setEstimate(double var1) {
        this.estimate = var1;
    }

    public void setStep(double var1) {
        this.step = var1;
    }

    public void setTolerance(double var1) {
        this.tolerance = var1;
    }

    public void setMaxIterations(int var1) {
        this.nMaxIter = var1;
    }

    public double getDonnanPotential() {
        return this.donnanPotential;
    }

    public double getDiffuseLayerPotentialA() {
        return this.diffPotentialA;
    }

    public double getDiffuseLayerPotentialB() {
        return this.diffPotentialB;
    }

    public double getSternLayerPotential() {
        return this.sternPotential;
    }

    public double[] getConcnA() {
        double[] var1 = Conv.copy(this.concnA);

        for(int var2 = 0; var2 < this.numOfIons; ++var2) {
            var1[var2] *= 0.001D;
        }

        return var1;
    }

    public double[] getConcnB() {
        double[] var1 = Conv.copy(this.concnB);

        for(int var2 = 0; var2 < this.numOfIons; ++var2) {
            var1[var2] *= 0.001D;
        }

        return var1;
    }

    public double[] getComplex() {
        double[] var1 = Conv.copy(this.complex);

        for(int var2 = 0; var2 < this.numOfIons; ++var2) {
            var1[var2] *= 0.001D;
        }

        return var1;
    }

    public double[] getExcessConcnA() {
        if (!this.includeIc) {
            System.out.println("Class: Donnan\nMethod: getExcessConcnA\nThe values of the excess concentrations have not been calculated\nzeros returned");
        }

        double[] var1 = Conv.copy(this.excessConcnA);

        for(int var2 = 0; var2 < this.numOfIons; ++var2) {
            var1[var2] *= 0.001D;
        }

        return var1;
    }

    public double[] getExcessConcnB() {
        if (!this.includeIc) {
            System.out.println("Class: Donnan\nMethod: getExcessConcnA\nThe values of the excess concentrations have not been calculated\nzeros returned");
        }

        double[] var1 = Conv.copy(this.excessConcnB);

        for(int var2 = 0; var2 < this.numOfIons; ++var2) {
            var1[var2] *= 0.001D;
        }

        return var1;
    }

    public double[] getExcessComplex() {
        if (!this.includeIc) {
            System.out.println("Class: Donnan\nMethod: getExcessConcnA\nThe values of the excess concentrations have not been calculated\nzeros returned");
        }

        double[] var1 = Conv.copy(this.excessComplex);

        for(int var2 = 0; var2 < this.numOfIons; ++var2) {
            var1[var2] *= 0.001D;
        }

        return var1;
    }

    public double[] getRatioA() {
        if (!this.includeIc) {
            System.out.println("Class: Donnan\nMethod: getRatioA\nThe values of the excess to bulk concentrations have not been calculated\nzeros returned");
        }

        return this.ratioA;
    }

    public double[] getRatioB() {
        if (!this.includeIc) {
            System.out.println("Class: Donnan\nMethod: getRatioB\nThe values of the excess to bulk concentrations have not been calculated\nzeros returned");
        }

        return this.ratioB;
    }

    public double[] getRatioComplex() {
        if (!this.includeIc) {
            System.out.println("Class: Donnan\nMethod: getRatioComplex\nThe values of the excess to bulk concentrations have not been calculated\nzeros returned");
        }

        return this.ratioC;
    }

    public double[] getPartitionCoefficients() {
        return this.partCoeffPot;
    }

    public double[] getPartitionCoefficientsZero() {
        return this.partCoeff;
    }

    public double[] getDeltaMu0() {
        return this.deltaMu0;
    }

    public double getInterfaceCharge() {
        if (!this.includeIc) {
            System.out.println("Class: Donnan\nMethod: getInterfaceCharge\nThe value of the interface charge has not been calculated\nzero returned");
        }

        return this.interfacialCharge;
    }

    public double getInterfaceChargeDensity() {
        if (!this.includeIc) {
            System.out.println("Class: Donnan\nMethod: getInterfaceChargeDensity\nThe value of the interface charge density has not been calculated\nzero returned");
        }

        return this.interfacialCharge;
    }

    public double getSternCapacitance() {
        if (!this.includeIc) {
            System.out.println("Class: Donnan\nMethod: getSternCapacitance\nThe value of the Stern capacitance has not been calculated\nzero returned");
        }

        return this.sternCap * this.interfacialArea;
    }

    public double getDiffuseLayerCapacitanceA() {
        if (!this.includeIc) {
            System.out.println("Class: Donnan\nMethod: getDiffuseLayerCapacitanceA\nThe values of the diffuse layer capacitances have not been calculated\nzero returned");
        }

        return this.diffCapA * this.interfacialArea;
    }

    public double getDiffuseLayerCapacitanceB() {
        if (!this.includeIc) {
            System.out.println("Class: Donnan\nMethod: getDiffuseLayerCapacitanceB\nThe values of the diffuse layer capacitances have not been calculated\nzero returned");
        }

        return this.diffCapB * this.interfacialArea;
    }

    public double getDonnanCapacitanceB() {
        if (!this.includeIc) {
            System.out.println("Class: Donnan\nMethod: getDonnanCapacitance\nThe value of the Donnan capacitance has not been calculated\nzero returned");
        }

        return this.donnanCap * this.interfacialArea;
    }

    public double getSternThicknessA() {
        if (!this.includeIc) {
            System.out.println("Class: Donnan\nMethod: getSternThicknessA\nThe values of the Stern layer thicknesses have not been calculated\nzero returned");
        }

        return this.sternDeltaA;
    }

    public double getSternThicknessB() {
        if (!this.includeIc) {
            System.out.println("Class: Donnan\nMethod: getSternThicknessB\nThe values of the Stern layer thicknesses have not been calculated\nzero returned");
        }

        return this.sternDeltaB;
    }

    public double getDebyeLengthA() {
        if (!this.includeIc) {
            System.out.println("Class: Donnan\nMethod: getDebyeLengthA\nThe values of the Debye lengths have not been calculated\nzero returned");
        }

        return this.recipKappaA;
    }

    public double getDebyeLengthB() {
        if (!this.includeIc) {
            System.out.println("Class: Donnan\nMethod: getDebyeLengthB\nThe values of the Debye lengths have not been calculated\nzero returned");
        }

        return this.recipKappaB;
    }

    public double getMinimum() {
        return this.minimum;
    }

    public double calcPotential() {
        this.unpack();
        double var1 = (double)this.numOfIons;
        double[] var3 = null;
        double[] var4 = null;
        double[] var5 = null;
        double[] var6 = null;
        double[] var7 = null;
        boolean var8;
        if (this.nonZeroConcns < this.numOfIons) {
            var3 = Conv.copy(this.assocConsts);
            var4 = Conv.copy(this.radii);
            var5 = Conv.copy(this.charges);
            var6 = Conv.copy(this.deltaMu0);
            var7 = Conv.copy(this.partCoeff);
            var8 = true;
            int var9 = 0;

            while(var8) {
                if (this.indexC[var9] != 0) {
                    ++var9;
                } else {
                    for(int var10 = var9 + 1; var10 < this.numOfIons; ++var10) {
                        this.concnA[var10 - 1] = this.concnA[var10];
                        this.concnB[var10 - 1] = this.concnB[var10];
                        this.complex[var10 - 1] = this.complex[var10];
                        this.molesT[var10 - 1] = this.molesT[var10];
                        this.assocConsts[var10 - 1] = this.assocConsts[var10];
                        this.radii[var10 - 1] = this.radii[var10];
                        this.charges[var10 - 1] = this.charges[var10];
                        this.deltaMu0[var10 - 1] = this.deltaMu0[var10];
                        this.partCoeff[var10 - 1] = this.partCoeff[var10];
                    }

                    --this.numOfIons;
                }

                if (this.numOfIons == this.nonZeroConcns) {
                    var8 = false;
                }
            }
        }

        var8 = false;
        if (this.includeIc) {
            var8 = true;
            this.includeIc = false;
        }

        Minimisation var21 = new Minimisation();
        DonnanMinim var22 = new DonnanMinim(this.numOfIons);
        var22.numOfIons = this.numOfIons;
        var22.concnA = this.concnA;
        var22.concnB = this.concnB;
        var22.molesT = this.molesT;
        var22.complex = this.complex;
        var22.excessConcnA = this.excessConcnA;
        var22.excessConcnB = this.excessConcnB;
        var22.excessComplex = this.excessComplex;
        var22.assocConsts = this.assocConsts;
        var22.indexK = this.indexK;
        var22.nonZeroAssocK = this.nonZeroAssocK;
        var22.radii = this.radii;
        var22.charges = this.charges;
        var22.ionophoreConcn = this.ionophoreConcn;
        var22.ionophoreRad = this.ionophoreRad;
        var22.volumeA = this.volumeA;
        var22.volumeB = this.volumeB;
        var22.interfacialArea = this.interfacialArea;
        var22.epsilonA = this.epsilonA;
        var22.epsilonB = this.epsilonB;
        var22.epsilonSternA = this.epsilonSternA;
        var22.epsilonSternB = this.epsilonSternB;
        var22.temp = this.temp;
        var22.partCoeff = this.partCoeff;
        var22.partCoeffPot = this.partCoeffPot;
        var22.sternCap = this.sternCap;
        var22.sternDeltaA = this.sternDeltaA;
        var22.sternDeltaB = this.sternDeltaB;
        var22.chargeValue = this.chargeValue;
        var22.chargeSame = this.chargeSame;
        var22.interfacialCharge = this.interfacialCharge;
        var22.interfacialChargeDensity = this.interfacialChargeDensity;
        var22.includeIc = this.includeIc;
        double[] var11 = new double[]{this.estimate};
        double[] var12 = new double[]{this.step};
        var21.nelderMead(var22, var11, var12, this.tolerance, this.nMaxIter);
        double[] var13 = var21.getParamValues();
        this.donnanPotential = var13[0];
        if (var8) {
            this.includeIc = true;
            var11[0] = this.donnanPotential;
            var12[0] = this.step;
            var21.nelderMead(var22, var11, var12, this.tolerance, this.nMaxIter);
            var13 = var21.getParamValues();
            this.donnanPotential = var13[0];
        }

        this.ionConcns(this.donnanPotential);
        if ((double)this.nonZeroConcns != var1) {
            boolean var14 = true;
            int var15 = 0;

            while(var14) {
                if (this.indexC[var15] != 0) {
                    ++var15;
                } else {
                    for(int var16 = var15; var16 < this.numOfIons; ++var16) {
                        this.concnA[var16 + 1] = this.concnA[var16];
                        this.concnB[var16 + 1] = this.concnB[var16];
                        this.complex[var16 + 1] = this.complex[var16];
                        this.excessConcnA[var16 + 1] = this.excessConcnA[var16];
                        this.excessConcnB[var16 + 1] = this.excessConcnB[var16];
                        this.excessComplex[var16 + 1] = this.excessComplex[var16];
                        this.molesT[var16 + 1] = this.molesT[var16];
                        this.assocConsts[var16 + 1] = this.assocConsts[var16];
                        this.radii[var16 + 1] = this.radii[var16];
                        this.charges[var16 + 1] = this.charges[var16];
                        this.deltaMu0[var16 + 1] = this.deltaMu0[var16];
                        this.partCoeff[var16 + 1] = this.partCoeff[var16];
                        this.partCoeffPot[var16 + 1] = this.partCoeffPot[var16];
                    }

                    ++this.numOfIons;
                    this.concnA[var15] = 0.0D;
                    this.concnB[var15] = 0.0D;
                    this.complex[var15] = 0.0D;
                    this.excessConcnA[var15] = 0.0D;
                    this.excessConcnB[var15] = 0.0D;
                    this.excessComplex[var15] = 0.0D;
                    this.molesT[var15] = 0.0D;
                    this.assocConsts[var15] = var3[var15];
                    this.radii[var15] = var4[var15];
                    this.charges[var15] = var5[var15];
                    this.deltaMu0[var15] = var6[var15];
                    this.partCoeff[var15] = var7[var15];
                }

                if (this.numOfIons == this.nonZeroConcns) {
                    var14 = false;
                }
            }
        }

        this.minimum = var21.getMinimum();
        this.numIterations = var21.getNiter();
        if (this.includeIc) {
            for(int var23 = 0; var23 < this.numOfIons; ++var23) {
                this.ratioA[var23] = this.excessConcnA[var23] / this.concnA[var23];
                this.ratioB[var23] = this.excessConcnB[var23] / this.concnB[var23];
                this.ratioC[var23] = this.excessComplex[var23] / this.complex[var23];
            }

            this.diffCapA = Math.abs(this.interfacialCharge / this.diffPotentialA);
            this.diffCapB = Math.abs(this.interfacialCharge / this.diffPotentialB);
            this.donnanCap = Math.abs(this.interfacialCharge / this.donnanPotential);
            double var24 = 2.0D * Fmath.square(-1.60217646263E-19D) * 6.0221419947E23D / (1.2224537278297904E-34D * this.temp);
            double var25 = var24 / this.epsilonA;
            double var18 = var24 / this.epsilonB;
            this.recipKappaA = 0.0D;
            this.recipKappaB = 0.0D;

            int var20;
            for(var20 = 0; var20 < this.numOfIons; ++var20) {
                this.recipKappaA += this.concnA[var20] * this.charges[var20] * this.charges[var20];
                this.recipKappaB += (this.concnB[var20] + this.complex[var20]) * this.charges[var20] * this.charges[var20];
            }

            this.recipKappaA = 1.0D / Math.sqrt(this.recipKappaA * var25);
            this.recipKappaB = 1.0D / Math.sqrt(this.recipKappaB * var18);

            for(var20 = 0; var20 < this.numOfIons; ++var20) {
                if (this.indexPC[var20]) {
                    this.deltaMu0[var20] = Math.log(this.partCoeff[var20]) * 8.314472296156563D * this.temp;
                } else {
                    this.deltaMu0[var20] *= 6.0221419947E23D;
                }
            }

            for(var20 = 0; var20 < this.numOfIons; ++var20) {
                this.partCoeffPot[var20] = this.partCoeff[var20] * Math.exp(-this.donnanPotential * this.charges[var20] * -1.60217646263E-19D / (1.380650324E-23D * this.temp));
            }
        }

        return this.donnanPotential;
    }

    private void unpack() {
        if (!this.volumeSet) {
            throw new IllegalArgumentException("The volumes of the partitions have not been set");
        } else if (this.numOfIons == 0) {
            throw new IllegalArgumentException("No ions have been entered");
        } else if (this.nonZeroConcns == 0) {
            throw new IllegalArgumentException("No non-zero ionic concentrations have been entered");
        } else if (!this.epsilonSet) {
            throw new IllegalArgumentException("The relative permittivities of the partitions have not been set");
        } else {
            if (!this.tempSet) {
                System.out.println("The temperature has not been entered\na value of 25 degrees Celsius has been used");
            }

            if (!this.ionophoreSet) {
                System.out.println("The ionophore has not been entered\na concentration value of zero has been used");
            }

            this.ionNames = new String[this.numOfIons];
            this.concnA = new double[this.numOfIons];
            this.concnB = new double[this.numOfIons];
            this.molesT = new double[this.numOfIons];
            this.complex = new double[this.numOfIons];
            this.excessConcnA = new double[this.numOfIons];
            this.excessConcnB = new double[this.numOfIons];
            this.excessComplex = new double[this.numOfIons];
            this.ratioA = new double[this.numOfIons];
            this.ratioB = new double[this.numOfIons];
            this.ratioC = new double[this.numOfIons];
            this.assocConsts = new double[this.numOfIons];
            this.radii = new double[this.numOfIons];
            this.charges = new double[this.numOfIons];
            this.deltaMu0 = new double[this.numOfIons];
            this.partCoeff = new double[this.numOfIons];
            this.partCoeffPot = new double[this.numOfIons];
            this.indexK = new int[this.nonZeroAssocK];
            this.indexC = new int[this.numOfIons];
            this.indexPC = new boolean[this.numOfIons];
            Double var1 = null;
            Integer var2 = null;
            int var3 = 0;
            this.chargeValue = 0.0D;
            this.chargeSame = true;

            for(int var4 = 0; var4 < this.numOfIons; ++var4) {
                this.ionNames[var4] = (String)this.arrayl.get(0 + var4 * 7);
                var1 = (Double)this.arrayl.get(1 + var4 * 7);
                this.concnA[var4] = var1 * 1000.0D;
                var1 = (Double)this.arrayl.get(2 + var4 * 7);
                this.concnB[var4] = var1 * 1000.0D;
                this.molesT[var4] = this.concnA[var4] * this.volumeA + this.concnB[var4] * this.volumeB;
                if (this.molesT[var4] > 0.0D) {
                    this.indexC[var4] = 1;
                } else {
                    this.indexC[var4] = 0;
                }

                var1 = (Double)this.arrayl.get(3 + var4 * 7);
                this.assocConsts[var4] = var1 * 0.001D;
                if (this.assocConsts[var4] > 0.0D) {
                    this.indexK[var3] = var4;
                    ++var3;
                }

                var1 = (Double)this.arrayl.get(4 + var4 * 7);
                this.radii[var4] = var1;
                var2 = (Integer)this.arrayl.get(5 + var4 * 7);
                this.charges[var4] = (double)var2;
                if (var4 == 0) {
                    this.chargeValue = Math.abs(this.charges[0]);
                } else if (Math.abs(this.charges[var4]) != this.chargeValue) {
                    this.chargeSame = false;
                }

                var1 = (Double)this.arrayl.get(6 + var4 * 7);
                this.partCoeff[var4] = var1;
                this.indexPC[var4] = true;
                if (this.partCoeff[var4] == -1.0D) {
                    this.indexPC[var4] = false;
                    this.deltaMu0[var4] = this.BornChargingEnergy(this.radii[var4], this.charges[var4], this.epsilonB) - this.BornChargingEnergy(this.radii[var4], this.charges[var4], this.epsilonA);
                    this.partCoeff[var4] = Math.exp(this.deltaMu0[var4] / (1.380650324E-23D * this.temp));
                }

                if (this.charges[var4] < 0.0D) {
                    ++this.numOfAnions;
                } else {
                    ++this.numOfCations;
                }

                if (this.ionophoreConcn == 0.0D) {
                    this.nonZeroAssocK = 0;
                }
            }

            double var16 = 0.0D;
            double var6 = 0.0D;
            double var8 = 0.0D;

            for(int var10 = 0; var10 < this.numOfIons; ++var10) {
                if (this.charges[var10] > 0.0D) {
                    var6 += this.molesT[var10] * this.charges[var10];
                } else {
                    var8 += this.molesT[var10] * this.charges[var10];
                }

                var16 = var6 + var8;
            }

            if (Math.abs(var16) > var6 * this.tol) {
                String var17 = "Class: Donnan, method: unpack()\n";
                String var11 = "Total charge = " + var16 + " mol/dm, i.e. is not equal to zero\n";
                String var12 = "Positive charge = " + var6 + " mol/dm\n";
                String var13 = "Do you wish to continue?";
                String var14 = var17 + var11 + var12 + var13;
                int var15 = JOptionPane.showConfirmDialog((Component)null, var14, "Neutrality check", 0, 3);
                if (var15 == 1) {
                    System.exit(0);
                }
            }

        }
    }

    public double BornChargingEnergy(double var1, double var3, double var5) {
        return Fmath.square(-1.60217646263E-19D * var3) / (25.132741228718345D * var1 * 8.854187817E-12D * var5);
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
        double[] var3 = new double[this.numOfIons];
        double[] var4 = new double[this.numOfIons];

        for(int var5 = 0; var5 < this.numOfIons; ++var5) {
            if (this.molesT[var5] == 0.0D) {
                this.concnB[var5] = 0.0D;
                this.concnA[var5] = 0.0D;
                this.complex[var5] = 0.0D;
                this.excessConcnA[var5] = 0.0D;
                this.excessConcnB[var5] = 0.0D;
                this.excessComplex[var5] = 0.0D;
            } else {
                this.concnB[var5] = this.molesT[var5] / (this.volumeA * this.partCoeffPot[var5] + this.volumeB);
                this.concnA[var5] = this.concnB[var5] * this.partCoeffPot[var5];
                this.complex[var5] = 0.0D;
                this.excessConcnA[var5] = 0.0D;
                this.excessConcnB[var5] = 0.0D;
                this.excessComplex[var5] = 0.0D;
            }

            var3[var5] = this.concnB[var5];
            var4[var5] = 0.05D * var3[var5];
        }

        Minimisation var9 = new Minimisation();
        DonnanConcn var6 = new DonnanConcn();
        var6.numOfIons = this.numOfIons;
        var6.concnA = this.concnA;
        var6.concnB = this.concnB;
        var6.molesT = this.molesT;
        var6.complex = this.complex;
        var6.excessConcnA = this.excessConcnA;
        var6.excessConcnB = this.excessConcnB;
        var6.excessComplex = this.excessComplex;
        var6.assocConsts = this.assocConsts;
        var6.indexK = this.indexK;
        var6.nonZeroAssocK = this.nonZeroAssocK;
        var6.radii = this.radii;
        var6.charges = this.charges;
        var6.ionophoreConcn = this.ionophoreConcn;
        var6.ionophoreRad = this.ionophoreRad;
        var6.volumeA = this.volumeA;
        var6.volumeB = this.volumeB;
        var6.interfacialArea = this.interfacialArea;
        var6.epsilonA = this.epsilonA;
        var6.epsilonB = this.epsilonB;
        var6.epsilonSternA = this.epsilonSternA;
        var6.epsilonSternB = this.epsilonSternB;
        var6.temp = this.temp;
        var6.partCoeffPot = this.partCoeffPot;
        var6.sternCap = this.sternCap;
        var6.sternDeltaA = this.sternDeltaA;
        var6.sternDeltaB = this.sternDeltaB;
        var6.chargeValue = this.chargeValue;
        var6.chargeSame = this.chargeSame;
        var6.interfacialCharge = this.interfacialCharge;
        var6.interfacialChargeDensity = this.interfacialChargeDensity;
        var6.potential = var1;
        var6.includeIc = this.includeIc;
        var9.nelderMead(var6, var3, var4, 1.0E-20D, 10000);
        double[] var7 = var9.getParamValues();
        this.freeIonophoreConcn = this.ionophoreConcn;

        for(int var8 = 0; var8 < this.numOfIons; ++var8) {
            this.concnB[var8] = var7[var8];
            this.concnA[var8] = this.concnB[var8] * this.partCoeffPot[var8];
            this.freeIonophoreConcn -= this.complex[var8];
        }

        this.interfacialCharge = var6.interfacialCharge;
        this.interfacialChargeDensity = var6.interfacialChargeDensity;
        this.sternCap = var6.sternCap;
        this.sternDeltaA = var6.sternDeltaA;
        this.sternDeltaB = var6.sternDeltaB;
        this.sternPotential = var6.sternPotential;
        this.diffPotentialA = var6.diffPotentialA;
        this.diffPotentialB = var6.diffPotentialB;
    }

    public void printToFile(String var1) {
        FileOutput var2 = new FileOutput(var1);
        var2.dateAndTimeln(var1);
        var2.println();
        var2.print("Donnan potential = ");
        var2.printsp(Fmath.truncate(this.donnanPotential, 7));
        var2.println("volts");
        if (this.includeIc) {
            var2.print("Compartment A double layer potential difference = ");
            var2.printsp(Fmath.truncate(this.diffPotentialA, 7));
            var2.println("volts");
            var2.print("Compartment B double layer potential difference = ");
            var2.printsp(Fmath.truncate(this.diffPotentialB, 7));
            var2.println("volts");
            var2.print("Stern potential difference = ");
            var2.printsp(Fmath.truncate(this.sternPotential, 7));
            var2.println("volts");
        }

        var2.println();
        var2.println("Ionic concentrations expressed as mol per cubic decimetre (M)");
        var2.println("Total = equivalent concentration with all ions in compartment A");
        int var3;
        if (this.includeIc) {
            var2.printtab("Ion");
            var2.println("Bulk concentrations / M                         Excess concentrations / M                       total / M");
            var2.printtab(" ");
            var2.println("A               B               complex         A               B               complex         ");

            for(var3 = 0; var3 < this.numOfIons; ++var3) {
                var2.printtab(this.ionNames[var3]);
                var2.printtab(Fmath.truncate(this.concnA[var3] * 0.001D, 7));
                var2.printtab(Fmath.truncate(this.concnB[var3] * 0.001D, 7));
                var2.printtab(Fmath.truncate(this.complex[var3] * 0.001D, 7));
                var2.printtab(Fmath.truncate(this.excessConcnA[var3] * 0.001D, 7));
                var2.printtab(Fmath.truncate(this.excessConcnB[var3] * 0.001D, 7));
                var2.printtab(Fmath.truncate(this.excessComplex[var3] * 0.001D, 7));
                var2.println(Fmath.truncate(this.molesT[var3] * 0.001D / this.volumeA, 7));
            }
        } else {
            var2.printtab("Ion");
            var2.println("A               B               complex         total");

            for(var3 = 0; var3 < this.numOfIons; ++var3) {
                var2.printtab(this.ionNames[var3]);
                var2.printtab(Fmath.truncate(this.concnA[var3] * 0.001D, 7));
                var2.printtab(Fmath.truncate(this.concnB[var3] * 0.001D, 7));
                var2.printtab(Fmath.truncate(this.complex[var3] * 0.001D, 7));
                var2.println(Fmath.truncate(this.molesT[var3] * 0.001D / this.volumeA, 7));
            }
        }

        var2.println();
        var2.println("mols of each ionic species");
        if (this.includeIc) {
            var2.printtab("Ion");
            var2.println("Bulk mols                                       Excess mols                                 total mols");
            var2.printtab(" ");
            var2.println("A               B               complex         A               B               complex         ");

            for(var3 = 0; var3 < this.numOfIons; ++var3) {
                var2.printtab(this.ionNames[var3]);
                var2.printtab(Fmath.truncate(this.concnA[var3] * this.volumeA, 7));
                var2.printtab(Fmath.truncate(this.concnB[var3] * this.volumeB, 7));
                var2.printtab(Fmath.truncate(this.complex[var3] * this.volumeB, 7));
                var2.printtab(Fmath.truncate(this.excessConcnA[var3] * this.volumeA, 7));
                var2.printtab(Fmath.truncate(this.excessConcnB[var3] * this.volumeB, 7));
                var2.printtab(Fmath.truncate(this.excessComplex[var3] * this.volumeB, 7));
                var2.println(Fmath.truncate(this.molesT[var3], 7));
            }
        } else {
            var2.printtab("Ion");
            var2.println("A               B               complex         total mols");

            for(var3 = 0; var3 < this.numOfIons; ++var3) {
                var2.printtab(this.ionNames[var3]);
                var2.printtab(Fmath.truncate(this.concnA[var3] * this.volumeA, 7));
                var2.printtab(Fmath.truncate(this.concnB[var3] * this.volumeB, 7));
                var2.printtab(Fmath.truncate(this.complex[var3] * this.volumeB, 7));
                var2.println(Fmath.truncate(this.molesT[var3], 7));
            }
        }

        var2.println();
        if (this.includeIc) {
            var2.println("Ratios of excess concentration over bulk concentration");
            var2.printtab("Ion");
            var2.println("A               B               complex");

            for(var3 = 0; var3 < this.numOfIons; ++var3) {
                var2.printtab(this.ionNames[var3]);
                var2.printtab(Fmath.truncate(this.ratioA[var3], 7));
                var2.printtab(Fmath.truncate(this.ratioB[var3], 7));
                var2.println(Fmath.truncate(this.ratioC[var3], 7));
            }

            var2.println();
        }

        var2.print("Total ionophore concentration = ");
        var2.printsp(Fmath.truncate(this.ionophoreConcn * 0.001D, 7));
        var2.println("M");
        var2.print("Free ionophore concentration = ");
        var2.printsp(Fmath.truncate(this.freeIonophoreConcn * 0.001D, 7));
        var2.println("M");
        var2.print("Total ionophore moles = ");
        var2.printsp(Fmath.truncate(this.ionophoreConcn * this.volumeB, 7));
        var2.println("mol");
        var2.print("Ionophore radius = ");
        var2.printsp(Fmath.truncate(this.ionophoreRad, 7));
        var2.println("m");
        var2.println();
        if (this.includeIc) {
            var2.print("Interface charge density = ");
            var2.printsp(Fmath.truncate(this.interfacialChargeDensity, 7));
            var2.printsp("C per square metre   ->   ");
            var2.printsp(Fmath.truncate(this.interfacialChargeDensity / Math.abs(-1.60217646263E-19D), 7));
            var2.println("unit charges per square metre ");
            var2.print("Total interface charge = ");
            var2.printsp(Fmath.truncate(this.interfacialCharge, 7));
            var2.printsp("unit charges   ->   ");
            var2.printsp(Fmath.truncate(this.interfacialCharge / Math.abs(-1.60217646263E-19D), 7));
            var2.println("unit charges ");
            var2.print("Overall interfacial capacitance = ");
            var2.printsp(Fmath.truncate(this.donnanCap * this.interfacialArea, 7));
            var2.printsp("F ");
            var2.print("  ->  ");
            var2.printsp(Fmath.truncate(this.donnanCap, 7));
            var2.println("Farads per square metre");
            var2.print("Diffuse double layer capacitance (Compartment A) = ");
            var2.printsp(Fmath.truncate(this.diffCapA * this.interfacialArea, 7));
            var2.printsp("F ");
            var2.print("  ->  ");
            var2.printsp(Fmath.truncate(this.diffCapA, 7));
            var2.println("Farads per square metre");
            var2.print("Diffuse double layer capacitance (Compartment B) = ");
            var2.printsp(Fmath.truncate(this.diffCapB * this.interfacialArea, 7));
            var2.printsp("F ");
            var2.print("  ->  ");
            var2.printsp(Fmath.truncate(this.diffCapB, 7));
            var2.println("Farads per square metre");
            var2.print("Stern capacitance = ");
            var2.printsp(Fmath.truncate(this.sternCap * this.interfacialArea, 7));
            var2.printsp("F ");
            var2.print("  ->  ");
            var2.printsp(Fmath.truncate(this.sternCap, 7));
            var2.println("Farads per square metre");
            var2.print("Stern thickness (Compartment A) = ");
            var2.printsp(Fmath.truncate(this.sternDeltaA, 7));
            var2.println("m");
            var2.print("Stern thickness (Compartment B) = ");
            var2.printsp(Fmath.truncate(this.sternDeltaB, 7));
            var2.println("m");
            var2.print("Debye length (Compartment A) = ");
            var2.printsp(Fmath.truncate(this.recipKappaA, 7));
            var2.println("m");
            var2.print("Debye length (Compartment B) = ");
            var2.printsp(Fmath.truncate(this.recipKappaB, 7));
            var2.println("m");
            var2.println("Compartment thicknesses assuming cubes with one side equal to the interfacial area");
            var2.print("Compartment A thickness = ");
            var2.printsp(Fmath.truncate(this.volumeA / this.interfacialArea, 7));
            var2.println("m");
            var2.print("Compartment B thickness = ");
            var2.printsp(Fmath.truncate(this.volumeB / this.interfacialArea, 7));
            var2.println("m");
            var2.println();
        }

        var2.print("Volume of compartment A = ");
        var2.printsp(this.volumeA);
        var2.println("cubic metres");
        var2.print("Volume of compartment B = ");
        var2.printsp(this.volumeB);
        var2.println("cubic metres");
        var2.print("Interfacial area = ");
        var2.printsp(this.interfacialArea);
        var2.println("square metres");
        var2.print("Relative electrical permittivity of compartment A = ");
        var2.println(this.epsilonA);
        var2.print("Relative electrical permittivity of compartment B = ");
        var2.println(this.epsilonB);
        var2.print("Relative electrical permittivity of compartment A Stern layer= ");
        var2.println(this.epsilonSternA);
        var2.print("Relative electrical permittivity of compartment B Stern layer= ");
        var2.println(this.epsilonSternB);
        var2.print("Temperature= ");
        var2.printsp(this.temp + -273.15D);
        var2.println("degrees Celsius");
        var2.println();
        var2.printtab("Ion");
        var2.printtab("Radius   ");
        var2.printtab("Charge");
        var2.printtab("Partition");
        var2.printtab("Partition");
        var2.printtab("Delta(mu0)");
        var2.println("Ion-Ionophore ");
        var2.printtab("  ");
        var2.printtab(" m       ");
        var2.printtab(" ");
        var2.printtab("Coefficient ");
        var2.printtab("Coefficient ");
        var2.printtab("/ J per mol");
        var2.println("associaion ");
        var2.printtab("  ");
        var2.printtab("         ");
        var2.printtab(" ");
        var2.printtab("at      ");
        var2.printtab("at zero ");
        var2.printtab("       ");
        var2.println("constant");
        var2.printtab("  ");
        var2.printtab("          ");
        var2.printtab(" ");
        var2.printtab("equilibrium ");
        var2.printtab("potential ");
        var2.printtab("          ");
        var2.println("mol per cubic dm");

        for(var3 = 0; var3 < this.numOfIons; ++var3) {
            var2.printtab(this.ionNames[var3]);
            var2.printtab(Fmath.truncate(this.radii[var3], 4));
            var2.printtab(this.charges[var3]);
            var2.printtab(Fmath.truncate(this.partCoeffPot[var3], 4));
            var2.printtab(Fmath.truncate(this.partCoeff[var3], 4));
            var2.printtab(Fmath.truncate(this.deltaMu0[var3], 4));
            var2.println(Fmath.truncate(this.assocConsts[var3] * 1000.0D, 4));
        }

        var2.close();
    }

    public void printToFile() {
        String var1 = "DonnanOutputFile.txt";
        this.printToFile(var1);
    }
}
