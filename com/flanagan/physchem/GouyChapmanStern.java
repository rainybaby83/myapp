//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package flanagan.physchem;

import flanagan.integration.Integration;
import flanagan.io.Db;
import flanagan.math.Conv;
import flanagan.math.Fmath;
import flanagan.math.Matrix;
import flanagan.math.Minimisation;
import flanagan.physprop.IonicRadii;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class GouyChapmanStern {
    private ArrayList<Object> vec = new ArrayList();
    private boolean unpackArrayList = false;
    private int numOfIons = 0;
    private int numOfAnions = 0;
    private int numOfCations = 0;
    private String[] ionNames = null;
    private double[] initConcnM = null;
    private double[] initConcn = null;
    private double[] siteConcn = null;
    private double[] sternConcn = null;
    private double[] bulkConcn = null;
    private double electrolyteConcn = 0.0D;
    private double ionicStrength = 0.0D;
    private int[] indexK = null;
    private int nonZeroAssocK = 0;
    private double[] radii = null;
    private boolean radiusType = true;
    private double[] charges = null;
    private double tolNeutral = 1.0E-6D;
    private double[] assocConstsM = null;
    private double[] assocConsts = null;
    private double surfaceSiteDensity = 0.0D;
    private double freeSurfaceSiteDensity = 0.0D;
    private boolean surfaceDensitySet = false;
    private double epsilon = 0.0D;
    private double epsilonStern = 0.0D;
    private boolean epsilonSet = false;
    private double temp = 25.0D;
    private double tempK = 298.15D;
    private boolean tempSet = false;
    private double surfacePotential = 0.0D;
    private boolean psi0set = false;
    private double diffPotential = 0.0D;
    private double sternPotential = 0.0D;
    private double surfaceArea = 1.0D;
    private boolean surfaceAreaSet = false;
    private double volume = 1.0D;
    private boolean volumeSet = false;
    private double sternCap = 0.0D;
    private double diffCap = 0.0D;
    private double totalCap = 0.0D;
    private double sternDelta = 0.0D;
    private double chargeValue = 0.0D;
    private boolean chargeSame = true;
    private double averageCharge = 0.0D;
    private double surfaceChargeDensity = 0.0D;
    private double adsorbedChargeDensity = 0.0D;
    private double diffuseChargeDensity = 0.0D;
    private boolean sigmaSet = false;
    private double surfaceCharge = 0.0D;
    private boolean chargeSet = false;
    private double recipKappa = 0.0D;
    private boolean sternOption = true;
    private double expTerm = 0.0D;
    private double expTermOver2 = 0.0D;
    private double twoTerm = 0.0D;
    private double eightTerm = 0.0D;

    public GouyChapmanStern() {
    }

    public void setHydratedRadii() {
        this.radiusType = true;
    }

    public void setBareRadii() {
        this.radiusType = false;
    }

    public void ignoreStern() {
        this.sternOption = false;
    }

    public void includeStern() {
        this.sternOption = true;
    }

    public void setIon(String var1, double var2, double var4, int var6, double var7) {
        this.vec.add(var1);
        this.vec.add(new Double(var2));
        this.vec.add(new Double(var4));
        this.vec.add(new Integer(var6));
        this.vec.add(new Double(var7));
        if (var7 != 0.0D) {
            ++this.nonZeroAssocK;
        }

        ++this.numOfIons;
        this.unpackArrayList = false;
    }

    public void setIon(String var1, double var2, double var4, int var6) {
        this.vec.add(var1);
        this.vec.add(new Double(var2));
        this.vec.add(new Double(var4));
        this.vec.add(new Integer(var6));
        this.vec.add(new Double(0.0D));
        ++this.numOfIons;
        this.unpackArrayList = false;
    }

    public void setIon(String var1, double var2, double var4) {
        IonicRadii var6 = new IonicRadii();
        this.vec.add(var1);
        this.vec.add(new Double(var2));
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
        }

        this.vec.add(new Double(var7));
        boolean var12 = false;
        int var13 = IonicRadii.charge(var1);
        if (var13 == 0) {
            var10 = var1 + " charge is not in the IonicRadii list\n";
            String var11 = "Please enter charge as, e.g +2";
            var13 = Db.readInt(var10 + var11);
        }

        this.vec.add(new Integer(var13));
        this.vec.add(new Double(var4));
        if (var4 != 0.0D) {
            ++this.nonZeroAssocK;
        }

        ++this.numOfIons;
        this.unpackArrayList = false;
    }

    public void setIon(String var1, double var2) {
        IonicRadii var4 = new IonicRadii();
        this.vec.add(var1);
        this.vec.add(new Double(var2));
        double var5 = 0.0D;
        if (this.radiusType) {
            var5 = IonicRadii.hydratedRadius(var1);
        } else {
            var5 = IonicRadii.radius(var1);
        }

        String var8;
        if (var5 == 0.0D) {
            String var7 = var1 + " radius is not in the IonicRadii list\n";
            var8 = "Please enter radius in metres\n";
            var5 = Db.readDouble(var7 + var8);
        }

        this.vec.add(new Double(var5));
        boolean var10 = false;
        int var11 = IonicRadii.charge(var1);
        if (var11 == 0) {
            var8 = var1 + " charge is not in the IonicRadii list\n";
            String var9 = "Please enter charge as, e.g +2";
            var11 = Db.readInt(var8 + var9);
        }

        this.vec.add(new Integer(var11));
        this.vec.add(new Double(0.0D));
        ++this.numOfIons;
        this.unpackArrayList = false;
    }

    public void setSurfaceSiteDensity(double var1) {
        this.surfaceSiteDensity = var1 / 6.0221419947E23D;
        this.surfaceDensitySet = true;
    }

    public void setSurfaceArea(double var1) {
        this.surfaceArea = var1;
        this.surfaceAreaSet = true;
    }

    public void setVolume(double var1) {
        this.volume = var1;
        this.volumeSet = true;
    }

    public void setRelPerm(double var1, double var3) {
        this.epsilon = var1;
        this.epsilonStern = var3;
        this.epsilonSet = true;
    }

    public void setRelPerm(double var1) {
        this.epsilon = var1;
        this.epsilonSet = true;
    }

    public void setTemp(double var1) {
        this.tempK = var1 - -273.15D;
        this.tempSet = true;
    }

    public void setSurfaceChargeDensity(double var1) {
        if (this.psi0set) {
            System.out.println("You have already entered a surface potential");
            System.out.println("This class allows the calculation of a surface charge density for a given surface potential");
            System.out.println("or the calculation of a surface potential for a given surface charge density");
            System.out.println("The previously entered surface potential will now be ignored");
            this.psi0set = false;
        }

        this.surfaceChargeDensity = var1;
        this.sigmaSet = true;
        if (this.surfaceAreaSet) {
            this.surfaceCharge = var1 * this.surfaceArea;
            this.chargeSet = true;
        }

    }

    public void setSurfaceCharge(double var1, double var3) {
        if (this.psi0set) {
            System.out.println("You have already entered a surface potential");
            System.out.println("This class allows the calculation of a surface charge density for a given surface potential");
            System.out.println("or the calculation of a surface potential for a given surface charge density");
            System.out.println("The previously entered surface potential will now be ignored");
            this.psi0set = false;
        }

        this.surfaceCharge = var1;
        this.chargeSet = true;
        this.surfaceArea = var3;
        this.surfaceAreaSet = true;
        this.surfaceChargeDensity = var1 / this.surfaceArea;
        this.sigmaSet = true;
    }

    public void setSurfaceCharge(double var1) {
        if (this.psi0set) {
            System.out.println("You have already entered a surface potential");
            System.out.println("This class allows the calculation of a surface charge density for a given surface potential");
            System.out.println("or the calculation of a surface potential for a given surface charge density");
            System.out.println("The previously entered surface potential will now be ignored");
            this.psi0set = false;
        }

        this.surfaceCharge = var1;
        this.chargeSet = true;
        if (this.surfaceAreaSet) {
            this.surfaceChargeDensity = var1 / this.surfaceArea;
            this.sigmaSet = true;
        }

    }

    public void setSurfacePotential(double var1) {
        if (this.sigmaSet) {
            System.out.println("You have already entered a surface charge density");
            System.out.println("This class allows the calculation of a surface potential for a given surface charge density");
            System.out.println("or the calculation of a surface charge density for a given surface potential");
            System.out.println("The previously entered surface charge density will now be ignored");
            this.sigmaSet = false;
        }

        this.surfacePotential = var1;
        this.psi0set = true;
    }

    public double getDiffuseLayerPotential() {
        if (!this.sternOption) {
            System.out.println("Class: GouyChapmanStern\nMethod: getDiffuseLayerPotential\nThe Stern modification was not included");
            System.out.println("The value of the diffuse layer potential has been set equal to the surface potential");
            return this.getSurfacePotential();
        } else if (this.psi0set && this.sigmaSet) {
            return this.diffPotential;
        } else if (this.sigmaSet) {
            this.getSurfacePotential();
            return this.diffPotential;
        } else if (this.psi0set) {
            this.getSurfaceChargeDensity();
            return this.diffPotential;
        } else {
            System.out.println("Class: GouyChapmanStern\nMethod: getDiffuseLayerPotential\nThe value of the diffuse layer potential has not been calculated\nzero returned");
            System.out.println("Neither a surface potential nor a surface charge density have been entered");
            return 0.0D;
        }
    }

    public double getSternLayerPotential() {
        if (!this.sternOption) {
            System.out.println("Class: GouyChapmanStern\nMethod: getSternLayerPotential\nThe Stern modification has not been included");
            System.out.println("The value of zero has been returned");
            return 0.0D;
        } else if (this.psi0set && this.sigmaSet) {
            return this.sternPotential;
        } else if (this.sigmaSet) {
            this.getSurfacePotential();
            return this.sternPotential;
        } else if (this.psi0set) {
            this.getSurfaceChargeDensity();
            return this.sternPotential;
        } else {
            System.out.println("Class: GouyChapmanStern\nMethod: getSternLayerPotential\nThe value of the Stern layer potential has not been calculated\nzero returned");
            System.out.println("Neither a surface potential nor a surface charge density have been entered");
            return 0.0D;
        }
    }

    public double getSternCapPerSquareMetre() {
        if (!this.sternOption) {
            System.out.println("Class: GouyChapmanStern\nMethod: getSternCapacitance\nThe Stern modification has not been included");
            System.out.println("A value of infinity has been returned");
            return 1.0D / 0.0;
        } else if (this.psi0set && this.sigmaSet) {
            return this.sternCap;
        } else if (this.sigmaSet) {
            this.getSurfacePotential();
            return this.sternCap;
        } else if (this.psi0set) {
            this.getSurfaceChargeDensity();
            return this.sternCap;
        } else {
            System.out.println("Class: GouyChapmanStern\nMethod: getSternCap\nThe value of the Stern capacitance has not been calculated\ninfinity returned");
            System.out.println("Neither a surface potential nor a surface charge density have been entered");
            return 1.0D / 0.0;
        }
    }

    public double getSternCapacitance() {
        if (!this.sternOption) {
            System.out.println("Class: GouyChapmanStern\nMethod: getSternCapacitance\nThe Stern modification has not been included");
            System.out.println("A value of infinity has been returned");
            return 1.0D / 0.0;
        } else if (!this.surfaceAreaSet) {
            System.out.println("Class: GouyChapmanStern\nMethod: getSternCapacitance\nThe surface area has not bee included");
            System.out.println("A value per square metre has been returned");
            return this.sternCap;
        } else if (this.psi0set && this.sigmaSet) {
            return this.sternCap * this.surfaceArea;
        } else if (this.sigmaSet) {
            this.getSurfacePotential();
            return this.sternCap * this.surfaceArea;
        } else if (this.psi0set) {
            this.getSurfaceChargeDensity();
            return this.sternCap * this.surfaceArea;
        } else {
            System.out.println("Class: GouyChapmanStern\nMethod: getSternCapacitance\nThe value of the Stern capacitance has not been calculated\ninfinity returned");
            System.out.println("Neither a surface potential nor a surface charge density have been entered");
            return 1.0D / 0.0;
        }
    }

    public double getDiffuseLayerCapPerSquareMetre() {
        if (this.psi0set && this.sigmaSet) {
            return this.diffCap;
        } else if (this.sigmaSet) {
            this.getSurfacePotential();
            return this.diffCap;
        } else if (this.psi0set) {
            this.getSurfaceChargeDensity();
            return this.diffCap;
        } else {
            System.out.println("Class: GouyChapmanStern\nMethod: getDiffuseLayerCapPerSquareMetre\nThe value of the diffuse layer capacitance has not been calculated\ninfinity returned");
            System.out.println("Neither a surface potential nor a surface charge density have been entered");
            return 1.0D / 0.0;
        }
    }

    public double getDiffuseLayerCapacitance() {
        if (!this.surfaceAreaSet) {
            System.out.println("Class: GouyChapmanStern\nMethod: getDiffuseLayerCapacitance\nThe surface area has not bee included");
            System.out.println("A value per square metre has been returned");
            return this.diffCap;
        } else if (this.psi0set && this.sigmaSet) {
            return this.diffCap * this.surfaceArea;
        } else if (this.sigmaSet) {
            this.getSurfacePotential();
            return this.diffCap * this.surfaceArea;
        } else if (this.psi0set) {
            this.getSurfaceChargeDensity();
            return this.diffCap * this.surfaceArea;
        } else {
            System.out.println("Class: GouyChapmanStern\nMethod: getDiffuseLayerCap\nThe value of the diffuse layer capacitance has not been calculated\ninfinity returned");
            System.out.println("Neither a surface potential nor a surface charge density have been entered");
            return 1.0D / 0.0;
        }
    }

    public double getTotalCapPerSquareMetre() {
        if (this.psi0set && this.sigmaSet) {
            return this.totalCap;
        } else if (this.sigmaSet) {
            this.getSurfacePotential();
            return this.totalCap;
        } else if (this.psi0set) {
            this.getSurfaceChargeDensity();
            return this.totalCap;
        } else {
            System.out.println("Class: GouyChapmanStern\nMethod: getTotalCapPerSquareMetre\nThe value of the total capacitance has not been calculated\ninfinity returned");
            System.out.println("Neither a surface potential nor a surface charge density have been entered");
            return 1.0D / 0.0;
        }
    }

    public double getTotalCapacitance() {
        if (!this.surfaceAreaSet) {
            System.out.println("Class: GouyChapmanStern\nMethod: getTotalCapacitance\nThe surface area has not bee included");
            System.out.println("A value per square metre has been returned");
            return this.diffCap;
        } else if (this.psi0set && this.sigmaSet) {
            return this.totalCap * this.surfaceArea;
        } else if (this.sigmaSet) {
            this.getSurfacePotential();
            return this.totalCap * this.surfaceArea;
        } else if (this.psi0set) {
            this.getSurfaceChargeDensity();
            return this.totalCap * this.surfaceArea;
        } else {
            System.out.println("Class: GouyChapmanStern\nMethod: getTotalCapacitance\nThe value of the total capacitance has not been calculated\ninfinity returned");
            System.out.println("Neither a surface potential nor a surface charge density have been entered");
            return 1.0D / 0.0;
        }
    }

    public double getSternThickness() {
        if (!this.sternOption) {
            System.out.println("Class: GouyChapmanStern\nMethod: getSternThickness");
            System.out.println("The Stern modification has not been included");
            System.out.println("A value of zero has been returned");
            return 0.0D;
        } else if (this.psi0set && this.sigmaSet) {
            return this.sternDelta;
        } else if (this.sigmaSet) {
            this.getSurfacePotential();
            return this.sternDelta;
        } else if (this.psi0set) {
            this.getSurfaceChargeDensity();
            return this.sternDelta;
        } else {
            System.out.println("Class: GouyChapmanStern\nMethod: getSternThickness\nThe value of the Stern thickness has not been calculated\nzero returned");
            System.out.println("Neither a surface potential nor a surface charge density have been entered");
            return 0.0D;
        }
    }

    public double getDebyeLength() {
        if (!this.unpackArrayList) {
            this.unpack();
        }

        return this.calcDebyeLength();
    }

    private double calcDebyeLength() {
        if (!this.epsilonSet) {
            throw new IllegalArgumentException("The relative permittivitie/s have not been entered");
        } else {
            if (!this.tempSet) {
                System.out.println("The temperature has not been entered\na value of 25 degrees Celsius has been used");
            }

            double var1 = 2.0D * Fmath.square(-1.60217646263E-19D) * 6.0221419947E23D / (8.854187817E-12D * this.epsilon * 1.380650324E-23D * this.tempK);
            this.recipKappa = 0.0D;

            for(int var3 = 0; var3 < this.numOfIons; ++var3) {
                this.recipKappa += this.bulkConcn[var3] * this.charges[var3] * this.charges[var3];
            }

            this.recipKappa = 1.0D / Math.sqrt(this.recipKappa * var1);
            return this.recipKappa;
        }
    }

    public double getIonicStrength() {
        if (!this.unpackArrayList) {
            this.unpack();
        }

        return this.ionicStrength;
    }

    private void unpack() {
        if (this.numOfIons == 0) {
            throw new IllegalArgumentException("No ions have been entered");
        } else {
            this.ionNames = new String[this.numOfIons];
            this.siteConcn = new double[this.numOfIons];
            this.sternConcn = new double[this.numOfIons];
            this.initConcnM = new double[this.numOfIons];
            this.initConcn = new double[this.numOfIons];
            this.bulkConcn = new double[this.numOfIons];
            this.radii = new double[this.numOfIons];
            this.charges = new double[this.numOfIons];
            this.assocConsts = new double[this.numOfIons];
            this.assocConstsM = new double[this.numOfIons];
            this.indexK = new int[this.nonZeroAssocK];
            Double var1 = null;
            Integer var2 = null;
            int var3 = 0;
            this.chargeValue = 0.0D;
            this.chargeSame = true;

            for(int var4 = 0; var4 < this.numOfIons; ++var4) {
                this.ionNames[var4] = (String)this.vec.get(0 + var4 * 5);
                var1 = (Double)this.vec.get(1 + var4 * 5);
                this.initConcnM[var4] = var1;
                this.initConcn[var4] = this.initConcnM[var4] * 1000.0D;
                var1 = (Double)this.vec.get(2 + var4 * 5);
                this.radii[var4] = var1;
                var2 = (Integer)this.vec.get(3 + var4 * 5);
                this.charges[var4] = (double)var2;
                var1 = (Double)this.vec.get(4 + var4 * 5);
                this.assocConstsM[var4] = var1;
                this.assocConsts[var4] = this.assocConstsM[var4] * 0.001D;
                if (this.assocConsts[var4] > 0.0D) {
                    this.indexK[var3] = var4;
                    ++var3;
                }

                if (var4 == 0) {
                    this.chargeValue = Math.abs(this.charges[0]);
                } else if (Math.abs(this.charges[var4]) != this.chargeValue) {
                    this.chargeSame = false;
                }

                if (this.charges[var4] < 0.0D) {
                    ++this.numOfAnions;
                } else {
                    ++this.numOfCations;
                }

                if (this.surfaceSiteDensity == 0.0D) {
                    this.nonZeroAssocK = 0;
                }
            }

            this.averageCharge = 0.0D;
            this.ionicStrength = 0.0D;
            double var19 = 0.0D;
            double var6 = 0.0D;
            double var8 = 0.0D;

            for(int var10 = 0; var10 < this.numOfIons; ++var10) {
                if (this.charges[var10] > 0.0D) {
                    var6 += this.initConcn[var10] * this.charges[var10];
                } else {
                    var8 += this.initConcn[var10] * this.charges[var10];
                }

                var19 = var6 + var8;
            }

            if (Math.abs(var19) > var6 * this.tolNeutral) {
                String var20 = "Class: GouyChapmanStern, method: unpack()\n";
                String var11 = "Total charge = " + var19 + " mol/dm, i.e. is not equal to zero\n";
                String var12 = "Positive charge = " + var6 + " mol/dm\n";
                String var13 = "Do you wish to continue?";
                String var14 = var20 + var11 + var12 + var13;
                int var15 = JOptionPane.showConfirmDialog((Component)null, var14, "Neutrality check", 0, 3);
                if (var15 == 1) {
                    System.exit(0);
                }
            }

            double var21 = 0.0D;
            double var22 = 0.0D;
            double var23 = 0.0D;
            double var16 = 0.0D;

            int var18;
            for(var18 = 0; var18 < this.numOfIons; ++var18) {
                this.ionicStrength += this.initConcn[var18] * this.charges[var18] * this.charges[var18];
                if (this.charges[var18] > 0.0D) {
                    var16 += this.initConcn[var18];
                } else {
                    var23 += this.initConcn[var18];
                }

                if (this.initConcn[var18] > 0.0D) {
                    var21 += this.initConcn[var18] * Math.abs(this.charges[var18]);
                    var22 += this.initConcn[var18];
                }
            }

            this.ionicStrength = this.ionicStrength * 0.001D / 2.0D;
            this.averageCharge = var21 / var22;
            this.electrolyteConcn = (var23 + var16) / 2.0D;

            for(var18 = 0; var18 < this.numOfIons; ++var18) {
                this.bulkConcn[var18] = this.initConcn[var18];
                this.siteConcn[var18] = 0.0D;
                this.sternConcn[var18] = 0.0D;
            }

            this.expTerm = 1.60217646263E-19D / (1.380650324E-23D * this.tempK);
            this.expTermOver2 = this.expTerm / 2.0D;
            this.twoTerm = 1.4723579861882691E-10D * this.epsilon * this.tempK;
            this.eightTerm = 4.0D * this.twoTerm;
            this.unpackArrayList = true;
        }
    }

    public double getSurfaceChargeDensity() {
        if (this.sigmaSet && this.psi0set) {
            return this.surfaceChargeDensity;
        } else if (!this.psi0set) {
            throw new IllegalArgumentException("No surface potential has been entered");
        } else {
            return this.getSurfaceChargeDensity(this.surfacePotential);
        }
    }

    public double getSurfaceChargeDensity(double var1) {
        this.surfaceChargeDensity = this.calcSurfaceChargeDensity(var1);
        this.sigmaSet = true;
        if (this.surfaceAreaSet) {
            this.surfaceCharge = this.surfaceChargeDensity * this.surfaceArea;
            this.chargeSet = true;
        }

        return this.surfaceChargeDensity;
    }

    private double calcSurfaceChargeDensity(double var1) {
        double var3 = 0.0D;
        if (!this.epsilonSet) {
            throw new IllegalArgumentException("The relative permittivitie/s have not been entered");
        } else {
            if (!this.tempSet) {
                System.out.println("The temperature has not been entered\na value of 25 degrees Celsius has been used");
            }

            if (!this.unpackArrayList) {
                this.unpack();
            }

            if (this.sternOption) {
                if (!this.surfaceAreaSet) {
                    throw new IllegalArgumentException("The surface area has not been entered");
                }

                if (!this.volumeSet) {
                    throw new IllegalArgumentException("The electrolyte volume has not been entered");
                }

                if (this.nonZeroAssocK == 0) {
                    if (this.chargeSame) {
                        var3 = this.surfaceChargeDensity0(var1);
                    } else {
                        var3 = this.surfaceChargeDensity1(var1);
                    }
                } else if (this.chargeSame) {
                    var3 = this.surfaceChargeDensity2(var1);
                } else {
                    var3 = this.surfaceChargeDensity3(var1);
                }
            } else if (this.chargeSame) {
                var3 = Math.sqrt(this.eightTerm * this.electrolyteConcn) * Fmath.sinh(this.chargeValue * this.expTermOver2 * var1);
            } else {
                double var5 = 0.0D;

                for(int var7 = 0; var7 < this.numOfIons; ++var7) {
                    var5 += this.bulkConcn[var7] * (Math.exp(-this.expTerm * var1 * this.charges[var7]) - 1.0D);
                }

                var3 = Fmath.sign(var1) * Math.sqrt(this.twoTerm * var5);
            }

            this.totalCap = var3 / var1;
            if (!this.sternOption) {
                this.diffPotential = var1;
                this.sternCap = 1.0D / 0.0;
                this.sternPotential = 0.0D;
                this.diffCap = this.totalCap;
            } else {
                this.diffPotential = var1 - var3 / this.sternCap;
                this.sternPotential = var1 - this.diffPotential;
                this.diffCap = (var3 + this.adsorbedChargeDensity) / this.diffPotential;
            }

            return var3;
        }
    }

    public double getSurfaceCharge() {
        return this.getSurfaceCharge(this.surfacePotential);
    }

    public double getSurfaceCharge(double var1) {
        if (!this.surfaceAreaSet) {
            throw new IllegalArgumentException("No surface area has been entered");
        } else {
            if (this.sigmaSet) {
                this.surfaceCharge = this.surfaceChargeDensity * this.surfaceArea;
            } else {
                if (!this.psi0set) {
                    throw new IllegalArgumentException("No surface potential has been entered");
                }

                this.surfaceCharge = this.getSurfaceChargeDensity(var1) * this.surfaceArea;
            }

            return this.surfaceCharge;
        }
    }

    private double surfaceChargeDensity0(double var1) {
        double var3 = 0.0D;
        double var5 = 0.0D;

        for(int var7 = 0; var7 < this.numOfIons; ++var7) {
            if (this.charges[var7] > 0.0D) {
                var5 += this.bulkConcn[var7];
            }
        }

        double var23 = 0.0D;
        double var9 = this.sigmaFunction0(var23, var1);
        double var11 = Math.sqrt(this.eightTerm * var5) * Fmath.sinh(this.chargeValue * this.expTermOver2 * var1);
        double var13 = this.sigmaFunction0(var11, var1);
        if (var13 * var9 > 0.0D) {
            throw new IllegalArgumentException("root not bounded");
        } else {
            double var15 = Math.abs(var11) * 1.0E-6D;
            boolean var17 = true;
            double var18 = 0.0D;
            double var20 = 0.0D;
            int var22 = 0;

            while(var17) {
                var18 = (var23 + var11) / 2.0D;
                var20 = this.sigmaFunction0(var18, var1);
                if (Math.abs(var20) <= var15) {
                    var3 = var18;
                    var17 = false;
                } else {
                    ++var22;
                    if (var22 > 10000) {
                        System.out.println("Class: GouyChapmanStern\nMethod: surfaceChargeDensity0\nnumber of iterations exceeded in bisection\ncurrent value of sigma returned");
                        var3 = var18;
                        var17 = false;
                    } else if (var20 * var9 > 0.0D) {
                        var23 = var18;
                        var9 = var20;
                    } else {
                        var11 = var18;
                    }
                }
            }

            return var3;
        }
    }

    private double sigmaFunction0(double var1, double var3) {
        this.calcSurfacePotential(var1);
        return this.diffPotential - var3 + var1 / this.sternCap;
    }

    private double surfaceChargeDensity1(double var1) {
        double var3 = 0.0D;
        double var5 = 0.0D;
        double var7 = this.sigmaFunction0(var5, var1);
        double var9 = 0.0D;

        for(int var11 = 0; var11 < this.numOfIons; ++var11) {
            var9 += this.bulkConcn[var11] * this.twoTerm * (Math.exp(-this.expTerm * this.charges[var11] * var1) - 1.0D);
        }

        var9 = Fmath.sign(var1) * Math.sqrt(this.twoTerm * var9);
        double var21 = this.sigmaFunction0(var9, var1);
        if (var21 * var7 > 0.0D) {
            throw new IllegalArgumentException("root not bounded");
        } else {
            double var13 = Math.abs(var9) * 1.0E-6D;
            boolean var15 = true;
            double var16 = 0.0D;
            double var18 = 0.0D;
            int var20 = 0;

            while(var15) {
                var16 = (var5 + var9) / 2.0D;
                var18 = this.sigmaFunction0(var16, var1);
                if (Math.abs(var18) <= var13) {
                    var3 = var16;
                    var15 = false;
                } else {
                    ++var20;
                    if (var20 > 10000) {
                        System.out.println("Class: GouyChapmanStern\nMethod: surfaceChargeDensity1\nnumber of iterations exceeded in outer bisection\ncurrent value of sigma returned");
                        var3 = var16;
                        var15 = false;
                    } else if (var7 * var18 > 0.0D) {
                        var5 = var16;
                        var7 = var18;
                    } else {
                        var9 = var16;
                    }
                }
            }

            return var3;
        }
    }

    private double calcDelta(double var1) {
        double var3 = 0.0D;
        double var5 = 0.0D;
        double var7 = 0.0D;

        for(int var9 = 0; var9 < this.numOfIons; ++var9) {
            this.sternConcn[var9] = this.bulkConcn[var9] * Math.exp(-this.charges[var9] * this.expTerm);
            var3 += this.sternConcn[var9] * this.radii[var9];
            var5 += this.sternConcn[var9];
        }

        this.sternDelta = var3 / var5;
        return this.sternDelta;
    }

    private double surfaceChargeDensity2(double var1) {
        double var3 = 0.0D;
        double var5 = 0.0D;
        double var7 = 0.0D;

        for(int var9 = 0; var9 < this.nonZeroAssocK; ++var9) {
            if (this.charges[this.indexK[var9]] > 0.0D) {
                var5 = this.surfaceSiteDensity;
            } else {
                var7 = -this.surfaceSiteDensity;
            }
        }

        double var27 = 0.0D;
        double var11 = 0.0D;
        double var13 = 0.0D;

        for(int var15 = 0; var15 < this.numOfIons; ++var15) {
            var13 += this.bulkConcn[var15];
        }

        var13 /= 2.0D;
        var11 = Math.sqrt(var13 * this.eightTerm) * Fmath.sinh(this.expTermOver2 * var1 * this.chargeValue);
        if (var11 > 0.0D) {
            var11 += var5;
            var27 -= var7;
        } else {
            var11 -= var7;
            var27 += var5;
        }

        double var28 = this.sigmaFunction2(var27, var1);
        double var17 = this.sigmaFunction2(var11, var1);
        if (var17 * var28 > 0.0D) {
            throw new IllegalArgumentException("root not bounded");
        } else {
            double var19 = Math.abs(var11) * 1.0E-6D;
            boolean var21 = true;
            double var22 = 0.0D;
            double var24 = 0.0D;
            int var26 = 0;

            while(var21) {
                var22 = (var27 + var11) / 2.0D;
                var24 = this.sigmaFunction2(var22, var1);
                if (Math.abs(var24) <= var19) {
                    var3 = var22;
                    var21 = false;
                } else {
                    ++var26;
                    if (var26 > 10000) {
                        System.out.println("Class: GouyChapmanStern\nMethod: surfaceChargeDensity2\nnumber of iterations exceeded in outer bisection\ncurrent value of sigma returned");
                        var3 = var22;
                        var21 = false;
                    } else if (var28 * var24 > 0.0D) {
                        var27 = var22;
                        var28 = var24;
                    } else {
                        var11 = var22;
                    }
                }
            }

            return var3;
        }
    }

    private double sigmaFunction2(double var1, double var3) {
        double var5 = -10.0D * var3;
        double var7 = this.psiFunctionQ(var5, var3, var1);
        double var9 = 10.0D * var3;
        double var11 = this.psiFunctionQ(var9, var3, var1);
        if (var11 * var7 > 0.0D) {
            throw new IllegalArgumentException("root not bounded");
        } else {
            double var13 = Math.abs(var3) * 1.0E-6D;
            boolean var15 = true;
            double var16 = 0.0D;
            double var18 = 0.0D;
            int var20 = 0;

            while(var15) {
                var16 = (var5 + var9) / 2.0D;
                var18 = this.psiFunctionQ(var16, var3, var1);
                if (Math.abs(var18) <= var13) {
                    var15 = false;
                } else {
                    ++var20;
                    if (var20 > 10000) {
                        System.out.println("Class: GouyChapmanStern\nMethod: surfaceChargeDensity3\nnumber of iterations exceeded in inner bisection\ncurrent value of sigma returned");
                        var15 = false;
                    }

                    if (var18 * var11 > 0.0D) {
                        var9 = var16;
                        var11 = var18;
                    } else {
                        var5 = var16;
                    }
                }
            }

            double var21 = 0.0D;

            for(int var23 = 0; var23 < this.numOfIons; ++var23) {
                var21 += this.bulkConcn[var23];
            }

            var21 = Math.sqrt(this.eightTerm * var21 / 2.0D) * Fmath.sinh(this.expTermOver2 * var3 * this.chargeValue);
            return var1 + this.adsorbedChargeDensity - var21;
        }
    }

    private double surfaceChargeDensity3(double var1) {
        double var3 = 0.0D;
        double var5 = 0.0D;
        double var7 = 0.0D;

        for(int var9 = 0; var9 < this.nonZeroAssocK; ++var9) {
            if (this.charges[this.indexK[var9]] > 0.0D) {
                var5 = this.surfaceSiteDensity;
            } else {
                var7 = -this.surfaceSiteDensity;
            }
        }

        double var25 = 0.0D;
        double var11 = 0.0D;

        for(int var13 = 0; var13 < this.numOfIons; ++var13) {
            var11 += this.bulkConcn[var13] * this.twoTerm * (Math.exp(-this.expTerm * this.charges[var13] * var1) - 1.0D);
        }

        var11 = Fmath.sign(var1) * Math.sqrt(var11);
        if (var11 > 0.0D) {
            var11 += var5;
            var25 -= var7;
        } else {
            var11 -= var7;
            var25 += var5;
        }

        double var26 = this.sigmaFunction3(var25, var1);
        double var15 = this.sigmaFunction3(var11, var1);
        if (var15 * var26 > 0.0D) {
            throw new IllegalArgumentException("root not bounded");
        } else {
            double var17 = Math.abs(var11) * 1.0E-6D;
            boolean var19 = true;
            double var20 = 0.0D;
            double var22 = 0.0D;
            int var24 = 0;

            while(var19) {
                var20 = (var25 + var11) / 2.0D;
                var22 = this.sigmaFunction3(var20, var1);
                if (Math.abs(var22) <= var17) {
                    var3 = var20;
                    var19 = false;
                } else {
                    ++var24;
                    if (var24 > 10000) {
                        System.out.println("Class: GouyChapmanStern\nMethod: surfaceChargeDensity3\nnumber of iterations exceeded in outer bisection\ncurrent value of sigma returned");
                        var3 = var20;
                        var19 = false;
                    } else if (var26 * var22 > 0.0D) {
                        var25 = var20;
                        var26 = var22;
                    } else {
                        var11 = var20;
                    }
                }
            }

            return var3;
        }
    }

    private double sigmaFunction3(double var1, double var3) {
        double var5 = 0.0D;
        double var7 = this.psiFunctionQ(var5, var3, var1);
        double var9 = var3;
        double var11 = this.psiFunctionQ(var3, var3, var1);
        if (var11 * var7 > 0.0D) {
            throw new IllegalArgumentException("root not bounded");
        } else {
            double var13 = Math.abs(var3) * 1.0E-6D;
            boolean var15 = true;
            double var16 = 0.0D;
            double var18 = 0.0D;
            int var20 = 0;

            while(var15) {
                var16 = (var5 + var9) / 2.0D;
                var18 = this.psiFunctionQ(var16, var3, var1);
                if (Math.abs(var18) <= var13) {
                    var15 = false;
                } else {
                    ++var20;
                    if (var20 > 10000) {
                        System.out.println("Class: GouyChapmanStern\nMethod: sigmaFunction3\nnumber of iterations exceeded in inner bisection\ncurrent value of sigma returned");
                        var15 = false;
                    }

                    if (var18 * var11 > 0.0D) {
                        var9 = var16;
                        var11 = var18;
                    } else {
                        var5 = var16;
                    }
                }
            }

            double var21 = 0.0D;

            for(int var23 = 0; var23 < this.numOfIons; ++var23) {
                var21 += this.bulkConcn[var23] * this.twoTerm * (Math.exp(-this.expTerm * this.charges[var23] * var16) - 1.0D);
            }

            var21 = Fmath.sign(var16) * Math.sqrt(var21);
            return var1 + this.adsorbedChargeDensity - var21;
        }
    }

    private double psiFunctionQ(double var1, double var3, double var5) {
        this.sternDelta = this.calcDeltaQ(var1);
        this.diffPotential = var1;
        this.sternCap = this.epsilonStern * 8.854187817E-12D / this.sternDelta;
        return var1 - var3 + var5 / this.sternCap;
    }

    private double calcDeltaQ(double var1) {
        double var3 = 0.0D;
        double var5 = 0.0D;
        double var7 = this.surfaceArea / this.volume;
        boolean var9 = false;
        double var12;
        double var14;
        double var18;
        double var20;
        double var22;
        int var28;
        int var33;
        if (this.nonZeroAssocK == 1) {
            var28 = this.indexK[0];
            double var10 = this.assocConsts[var28] * Math.exp(-this.charges[var28] * var1 * this.expTerm);
            var12 = var10 * var7;
            var14 = -(1.0D + this.initConcn[var28] * var10 + this.surfaceSiteDensity * var10 * var7);
            double var16 = this.initConcn[var28] * this.surfaceSiteDensity * var10;
            var18 = var14 * var14 - 4.0D * var12 * var16;
            if (var18 < 0.0D) {
                System.out.println("Class: GouyChapmanStern\nMethod: calcDeltaQ\nthe square root term (b2-4ac) of the quadratic = " + var18);
                System.out.println("this term was set to zero as the negative value MAY have arisen from rounding errors");
                var18 = 0.0D;
            }

            var20 = -0.5D * (var14 + Fmath.sign(var14) * Math.sqrt(var18));
            var22 = var20 / var12;
            double var24 = var16 / var20;
            double var26 = this.surfaceSiteDensity * 1.001D;
            if (var22 >= 0.0D && var22 <= var26) {
                if (var24 >= 0.0D && var24 <= var26) {
                    System.out.println("Class: GouyChapmanStern\nMethod: ionConcns");
                    System.out.println("error1: no physically meaningfull root");
                    System.out.println("root1 = " + var22 + " root2 = " + var24 + " limit = " + var26);
                    System.exit(0);
                } else {
                    this.siteConcn[this.indexK[0]] = var22;
                    this.bulkConcn[this.indexK[0]] = this.initConcn[this.indexK[0]] - this.siteConcn[this.indexK[0]] * this.surfaceArea / this.volume;
                }
            } else if (var24 >= 0.0D && var24 <= var26) {
                if (var22 >= 0.0D && var22 <= var26) {
                    System.out.println("Class: GouyChapmanStern\nMethod: ionConcns");
                    System.out.println("error2: no physically meaningfull root");
                    System.out.println("root1 = " + var22 + " root2 = " + var24 + " limit = " + var26);
                    System.exit(0);
                } else {
                    this.siteConcn[this.indexK[0]] = var24;
                    this.bulkConcn[this.indexK[0]] = this.initConcn[this.indexK[0]] - this.siteConcn[this.indexK[0]] * this.surfaceArea / this.volume;
                }
            } else {
                System.out.println("Class: GouyChapmanStern\nMethod: ionConcns");
                System.out.println("error3: no physically meaningfull root");
                System.out.println("root1 = " + var22 + " root2 = " + var24 + " limit = " + var26);
                System.exit(0);
            }
        } else {
            double[] var29 = new double[this.nonZeroAssocK];
            double[][] var11 = new double[this.nonZeroAssocK][this.nonZeroAssocK];
            var12 = -var1 * this.expTerm;

            int var15;
            for(var33 = 0; var33 < this.nonZeroAssocK; ++var33) {
                var28 = this.indexK[var33];
                var29[var33] = this.assocConsts[var28] * this.initConcn[var28] * this.surfaceSiteDensity * Math.exp(this.charges[var28] * var12);

                for(var15 = 0; var15 < this.nonZeroAssocK; ++var15) {
                    var11[var33][var15] = this.assocConsts[var28] * this.initConcn[var28] * Math.exp(this.charges[var28] * var12);
                    if (var33 == var15) {
                        ++var11[var33][var15];
                    }
                }
            }

            Matrix var34 = new Matrix(var11);
            var29 = var34.solveLinearSet(var29);

            for(var15 = 0; var15 < this.nonZeroAssocK; ++var15) {
                this.siteConcn[this.indexK[var15]] = var29[var15];
            }
        }

        Minimisation var30 = new Minimisation();
        GCSminim var31 = new GCSminim();
        var31.psiDelta = var1;
        var31.tempK = this.tempK;
        var31.surfaceSiteDensity = this.surfaceSiteDensity;
        var31.surfaceArea = this.surfaceArea;
        var31.volume = this.volume;
        var31.nonZeroAssocK = this.nonZeroAssocK;
        var31.assocK = this.assocConsts;
        var31.initConcn = this.initConcn;
        var31.charges = this.charges;
        var31.indexK = this.indexK;
        double[] var32 = new double[this.nonZeroAssocK];
        double[] var13 = new double[this.nonZeroAssocK];

        for(var33 = 0; var33 < this.nonZeroAssocK; ++var33) {
            var32[var33] = this.surfaceSiteDensity / (double)this.nonZeroAssocK;
            var13[var33] = var32[var33] * 0.05D;
        }

        var14 = this.surfaceSiteDensity * 1.0E-8D;
        int var35 = 100000;
        var30.nelderMead(var31, var32, var13, var14, var35);
        double[] var17 = var30.getParamValues();

        for(int var36 = 0; var36 < this.nonZeroAssocK; ++var36) {
            var28 = this.indexK[var36];
            this.siteConcn[var28] = var17[var36];
            this.bulkConcn[var28] = this.initConcn[var28] - var17[var36] * this.surfaceArea / this.volume;
        }

        this.adsorbedChargeDensity = 0.0D;
        var18 = 96485.34158524018D;
        var20 = this.surfaceArea / this.volume;

        for(int var37 = 0; var37 < this.numOfIons; ++var37) {
            this.sternConcn[var37] = this.bulkConcn[var37] * Math.exp(-this.charges[var37] * this.expTerm);
            this.adsorbedChargeDensity += this.siteConcn[var37] * this.charges[var37] * var18;
            var3 += (this.sternConcn[var37] + this.siteConcn[var37] * var20) * this.radii[var37];
            var5 += this.sternConcn[var37] + this.siteConcn[var37] * var20;
        }

        var22 = var3 / var5;
        return var22;
    }

    public double getAdsorbedChargeDensity() {
        if (this.sternOption && this.nonZeroAssocK != 0) {
            if (this.psi0set && this.sigmaSet) {
                return this.adsorbedChargeDensity;
            } else if (this.sigmaSet) {
                this.getSurfacePotential();
                return this.sternPotential;
            } else if (this.psi0set) {
                this.getSurfaceChargeDensity();
                return this.adsorbedChargeDensity;
            } else {
                System.out.println("Class: GouyChapmanStern\nMethod: getAdsorbedChargeDensity\nThe value of the adsorbed ion charge density has not been calculated\nzero returned");
                System.out.println("Neither a surface potential nor a surface charge density have been entered");
                return 0.0D;
            }
        } else {
            return 0.0D;
        }
    }

    public double getDiffuseChargeDensity() {
        double var1 = this.getAdsorbedChargeDensity();
        this.diffuseChargeDensity = -(this.surfaceChargeDensity + var1);
        return this.diffuseChargeDensity;
    }

    public double getSurfacePotential(double var1) {
        this.surfacePotential = this.calcSurfacePotential(var1);
        this.psi0set = true;
        return this.surfacePotential;
    }

    private double calcSurfacePotential(double var1) {
        double var3 = 0.0D;
        if (!this.epsilonSet) {
            throw new IllegalArgumentException("The relative permittivitie/s have not been entered");
        } else {
            if (!this.tempSet) {
                System.out.println("The temperature has not been entered\na value of 25 degrees Celsius has been used");
            }

            if (this.psi0set && this.sigmaSet) {
                return this.surfacePotential;
            } else {
                if (!this.unpackArrayList) {
                    this.unpack();
                }

                if (this.sternOption) {
                    if (this.nonZeroAssocK == 0) {
                        if (this.chargeSame) {
                            this.diffPotential = Fmath.asinh(var1 / Math.sqrt(this.eightTerm * this.electrolyteConcn)) / (this.chargeValue * this.expTermOver2);
                        } else {
                            this.diffPotential = this.surfacePotential1(var1);
                        }

                        this.sternCap = 8.854187817E-12D * this.epsilonStern / this.calcDelta(this.diffPotential);
                        var3 = this.diffPotential + var1 / this.sternCap;
                        this.totalCap = var1 / this.surfacePotential;
                        this.diffCap = var1 / this.diffPotential;
                    } else if (this.chargeSame) {
                        var3 = this.surfacePotential2(var1);
                    } else {
                        var3 = this.surfacePotential3(var1);
                    }
                } else {
                    if (this.chargeSame) {
                        double var5 = Math.sqrt(this.eightTerm * this.electrolyteConcn);
                        this.surfacePotential = Fmath.asinh(this.surfaceChargeDensity / var5) / (this.chargeValue * this.expTermOver2);
                    } else {
                        var3 = this.surfacePotential4(var1);
                    }

                    this.diffPotential = var3;
                    this.sternPotential = 0.0D;
                    this.totalCap = var1 / var3;
                    this.diffCap = var1 / this.diffPotential;
                    this.sternCap = 1.0D / 0.0;
                }

                return var3;
            }
        }
    }

    public double getSurfacePotential() {
        if (!this.sigmaSet) {
            throw new IllegalArgumentException("No surface charge density has been entered");
        } else {
            return this.getSurfacePotential(this.surfaceChargeDensity);
        }
    }

    private double surfacePotential4(double var1) {
        double var3 = 0.0D;
        double var5 = 0.0D;
        double var7 = this.psiFunction4(var5, var1);
        double var9 = Math.sqrt(this.eightTerm * this.electrolyteConcn);
        double var11 = 10.0D / (this.averageCharge * this.expTerm) * Fmath.asinh(var1 / var9);
        double var13 = this.psiFunction4(var11, var1);
        if (var13 * var7 > 0.0D) {
            throw new IllegalArgumentException("root not bounded");
        } else {
            double var15 = Math.abs(var11) * 1.0E-6D;
            boolean var17 = true;
            double var18 = 0.0D;
            double var20 = 0.0D;
            int var22 = 0;

            while(var17) {
                var18 = (var5 + var11) / 2.0D;
                var20 = this.psiFunction4(var18, var1);
                if (Math.abs(var20) <= var15) {
                    var3 = var18;
                    var17 = false;
                } else {
                    ++var22;
                    if (var22 > 10000) {
                        System.out.println("Class: GouyChapmanStern\nMethod: getSurfacePotential\nnumber of iterations exceeded in outer bisection\ncurrent value of sigma returned");
                        var3 = var18;
                        var17 = false;
                    } else if (var7 * var20 > 0.0D) {
                        var5 = var18;
                        var7 = var20;
                    } else {
                        var11 = var18;
                    }
                }
            }

            return var3;
        }
    }

    private double psiFunction4(double var1, double var3) {
        double var5 = 0.0D;

        for(int var7 = 0; var7 < this.numOfIons; ++var7) {
            var5 += this.bulkConcn[var7] * this.twoTerm * (Math.exp(-this.expTerm * this.charges[var7] * var1) - 1.0D);
        }

        var5 = Fmath.sign(var3) * Math.sqrt(var5);
        return var3 - var5;
    }

    private double surfacePotential1(double var1) {
        double var3 = 0.0D;
        double var5 = 0.0D;
        double var7 = this.psiFunction1(var5, var1);
        double var9 = 5.0D / (this.expTerm * this.chargeValue) * Fmath.asinh(var1 / Math.sqrt(this.eightTerm * this.electrolyteConcn));
        double var11 = this.psiFunction1(var9, var1);
        if (var11 * var7 > 0.0D) {
            throw new IllegalArgumentException("root not bounded");
        } else {
            double var13 = Math.abs(var9) * 1.0E-6D;
            boolean var15 = true;
            double var16 = 0.0D;
            double var18 = 0.0D;
            int var20 = 0;

            while(var15) {
                var16 = (var5 + var9) / 2.0D;
                var18 = this.psiFunction1(var16, var1);
                if (Math.abs(var18) <= var13) {
                    this.diffPotential = var16;
                    var15 = false;
                } else {
                    ++var20;
                    if (var20 > 10000) {
                        System.out.println("Class: GouyChapmanStern\nMethod: getSurfacePotential\nnumber of iterations exceeded in outer bisection\ncurrent value of sigma returned");
                        this.diffPotential = var16;
                        var15 = false;
                    } else if (var7 * var18 > 0.0D) {
                        var5 = var16;
                        var7 = var18;
                    } else {
                        var9 = var16;
                    }
                }
            }

            return var3;
        }
    }

    private double psiFunction1(double var1, double var3) {
        double var5 = 0.0D;

        for(int var7 = 0; var7 < this.numOfIons; ++var7) {
            var5 += this.twoTerm * this.bulkConcn[var7] * (Math.exp(-this.charges[var7] * var1 * this.expTerm) - 1.0D);
        }

        return var3 - Fmath.sign(var3) * Math.sqrt(var5);
    }

    public double getPotentialAtX(double var1) {
        if (!this.psi0set && !this.sigmaSet) {
            throw new IllegalArgumentException("Neither a surface potential nor a surface charge/density have been entered");
        } else {
            if (this.sigmaSet && !this.psi0set) {
                this.getSurfacePotential();
            }

            if (this.psi0set && !this.sigmaSet) {
                this.getSurfaceChargeDensity();
            }

            double var3 = 0.0D;
            if (var1 == 0.0D) {
                var3 = this.surfacePotential;
            } else if (this.sternOption) {
                if (var1 == this.sternDelta) {
                    var3 = this.diffPotential;
                } else if (var1 < this.sternDelta) {
                    var3 = this.surfacePotential - var1 / this.sternDelta * (this.surfacePotential - this.diffPotential);
                } else {
                    var3 = this.calcPotAtX(this.diffPotential, var1);
                }
            } else {
                var3 = this.calcPotAtX(this.surfacePotential, var1);
            }

            return var3;
        }
    }

    private double calcPotAtX(double var1, double var3) {
        double var5 = 0.0D;
        double var9;
        double var11;
        double var13;
        if (this.chargeSame) {
            double var7 = Math.sqrt(2.0D * Fmath.square(-1.60217646263E-19D * this.chargeValue) * 6.0221419947E23D * this.electrolyteConcn / (8.854187817E-12D * this.epsilon * 1.380650324E-23D * this.tempK));
            var9 = Math.exp(this.expTerm * this.chargeValue * var1 / 2.0D);
            var11 = (var9 - 1.0D) / (var9 + 1.0D);
            var13 = var11 * Math.exp(-var7 * var3);
            var5 = 2.0D * Math.log((1.0D + var13) / (1.0D - var13)) / (this.expTerm * this.chargeValue);
        } else {
            FunctionPatX var25 = new FunctionPatX();
            var25.numOfIons = this.numOfIons;
            var25.termOne = 16.628944592313125D * this.tempK / (8.854187817E-12D * this.epsilon);
            var25.expTerm = this.expTerm;
            var25.bulkConcn = this.bulkConcn;
            var25.charges = this.charges;
            short var8 = 2000;
            var9 = 0.0D;
            var11 = var3 - Integration.trapezium(var25, var9, var1, var8);
            var13 = var1;
            double var15 = var3 - Integration.trapezium(var25, var1, var1, var8);
            if (var15 * var11 > 0.0D) {
                throw new IllegalArgumentException("root not bounded");
            }

            double var17 = Math.abs(var3) * 0.01D;
            boolean var19 = true;
            double var20 = 0.0D;
            double var22 = 0.0D;
            int var24 = 0;

            while(var19) {
                var20 = (var9 + var13) / 2.0D;
                var22 = var3 - Integration.trapezium(var25, var20, var1, var8);
                if (Math.abs(var22) <= var17) {
                    var5 = var20;
                    var19 = false;
                } else {
                    ++var24;
                    if (var24 > 10000) {
                        System.out.println("Class: GouyChapmanStern\nMethod: getPotentialAtX\nnumber of iterations exceeded in outer bisection\ncurrent value of psi(x) returned");
                        var5 = var20;
                        var19 = false;
                    } else if (var11 * var22 > 0.0D) {
                        var9 = var20;
                        var11 = var22;
                    } else {
                        var13 = var20;
                    }
                }
            }
        }

        return var5;
    }

    public double[] getConcnsAtX(double var1) {
        if (!this.psi0set && !this.sigmaSet) {
            throw new IllegalArgumentException("Neither a surface potential nor a surface charge/density have been entered");
        } else {
            if (this.sigmaSet && !this.psi0set) {
                this.getSurfacePotential();
            }

            if (this.psi0set && !this.sigmaSet) {
                this.getSurfaceChargeDensity();
            }

            double[] var3 = new double[this.numOfIons];
            if (this.sternOption && var1 < this.sternDelta) {
                for(int var7 = 0; var7 < this.numOfIons; ++var7) {
                    var3[var7] = 0.0D;
                }
            } else {
                double var4 = this.getPotentialAtX(var1);

                for(int var6 = 0; var6 < this.numOfIons; ++var6) {
                    var3[var6] = this.bulkConcn[var6] * Math.exp(-this.expTerm * this.charges[var6] * var4);
                }
            }

            return var3;
        }
    }

    public double[] getInitConcns() {
        if (!this.psi0set && !this.sigmaSet) {
            this.unpack();
        }

        double[] var1 = Conv.copy(this.initConcn);

        for(int var2 = 0; var2 < this.numOfIons; ++var2) {
            var1[var2] *= 0.001D;
        }

        return var1;
    }

    public double[] getBulkConcns() {
        if (!this.psi0set && !this.sigmaSet) {
            throw new IllegalArgumentException("Neither a surface potential nor a surface charge/density have been entered");
        } else {
            if (this.sigmaSet && !this.psi0set) {
                this.getSurfacePotential();
            }

            if (this.psi0set && !this.sigmaSet) {
                this.getSurfaceChargeDensity();
            }

            double[] var1 = Conv.copy(this.bulkConcn);

            for(int var2 = 0; var2 < this.numOfIons; ++var2) {
                var1[var2] *= 0.001D;
            }

            return var1;
        }
    }

    public double[] getSiteConcns() {
        if (!this.psi0set && !this.sigmaSet) {
            throw new IllegalArgumentException("Neither a surface potential nor a surface charge/density have been entered");
        } else {
            if (this.sigmaSet && !this.psi0set) {
                this.getSurfacePotential();
            }

            if (this.psi0set && !this.sigmaSet) {
                this.getSurfaceChargeDensity();
            }

            return this.siteConcn;
        }
    }

    private double surfacePotential2(double var1) {
        double var3 = 0.0D;
        double var5 = Fmath.asinh(var1 / Math.sqrt(this.eightTerm * this.electrolyteConcn)) / (this.chargeValue * this.expTermOver2);
        double var7 = 0.0D;
        double var9 = 0.0D;
        if (var5 > 0.0D) {
            var9 = 2.0D * var5;
        } else {
            var7 = 2.0D * var5;
        }

        var3 = this.surfacePotentialBisection(var7, var9, var1, 2);
        return var3;
    }

    private double surfacePotentialBisection(double var1, double var3, double var5, int var7) {
        double var8 = 0.0D;
        boolean var10 = true;
        int var11 = 0;
        byte var12 = 10;
        double var13 = var3 - var1;
        this.cFunction(var1, var5);
        double var17 = this.cFunction(var3, var5);

        while(var10) {
            if (var3 * var1 > 0.0D) {
                ++var11;
                if (var11 > var12) {
                    throw new IllegalArgumentException("root not bounded after " + var11 + "expansions");
                }

                var1 -= var13;
                var3 += var13;
                this.cFunction(var1, var5);
                var17 = this.cFunction(var3, var5);
            } else {
                var10 = false;
            }
        }

        double var19 = Math.abs(var5) * 1.0E-6D;
        boolean var21 = true;
        double var22 = 0.0D;
        int var24 = 0;

        while(var21) {
            var8 = (var1 + var3) / 2.0D;
            var22 = this.cFunction(var8, var5);
            if (Math.abs(var22) <= var19) {
                var21 = false;
            } else {
                ++var24;
                if (var24 > 10000) {
                    System.out.println("Class: GouyChapmanStern\nMethod: surfacePotential" + var7 + "\nnumber of iterations exceeded in bisection\ncurrent value of sigma returned");
                    var21 = false;
                }

                if (var22 * var17 > 0.0D) {
                    var3 = var8;
                    var17 = var22;
                } else {
                    var1 = var8;
                }
            }
        }

        return var8;
    }

    private double cFunction(double var1, double var3) {
        double var5 = this.calcSurfaceChargeDensity(var1);
        return var5 - var3;
    }

    private double surfacePotential3(double var1) {
        double var3 = 0.0D;
        double var5 = Fmath.asinh(var1 / Math.sqrt(this.eightTerm * this.electrolyteConcn)) / (this.chargeValue * this.expTermOver2);
        double var7 = 0.0D;
        double var9 = 0.0D;
        if (var5 > 0.0D) {
            var9 = 2.0D * var5;
        } else {
            var7 = 2.0D * var5;
        }

        var3 = this.surfacePotentialBisection(var7, var9, var1, 3);
        return var3;
    }
}
