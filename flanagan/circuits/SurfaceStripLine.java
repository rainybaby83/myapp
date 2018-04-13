//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package flanagan.circuits;

import flanagan.complex.Complex;
import flanagan.math.Fmath;

public class SurfaceStripLine extends TransmissionLine {
    private double trackWidth = -1.0D;
    private double effectiveTrackWidth = -1.0D;
    private double trackThickness = -1.0D;
    private double plateSeparation = -1.0D;
    private boolean widthSet = false;
    private boolean separationSet = false;
    private boolean thicknessSet = false;
    private double relativePermittivity = 1.0D;
    private double effectivePermittivity = 1.0D;
    private boolean permittivitySet = false;
    private double relativePermeability = 1.0D;
    private int formulaOption = 0;
    private boolean z0calculated = false;

    public SurfaceStripLine() {
        super.title = "Surface Strip Line";
    }

    public SurfaceStripLine(String var1) {
        super.title = var1;
    }

    public void setFormulae(int var1) {
        if (var1 >= 1 && var1 <= 3) {
            this.formulaOption = var1 - 1;
            if (this.permittivitySet && this.separationSet && this.widthSet) {
                if (this.formulaOption == 0) {
                    this.calcIdealCharacteristicImpedance();
                } else if (this.thicknessSet) {
                    this.calcIdealCharacteristicImpedance();
                }
            }

        } else {
            throw new IllegalArgumentException("The option value, " + var1 + ", must be 1,2 or 3");
        }
    }

    public void useEdwardsHoffmannFormulae() {
        this.formulaOption = 0;
        if (this.permittivitySet && this.separationSet && this.widthSet) {
            this.calcIdealCharacteristicImpedance();
        }

    }

    public void useWadellWheelerSchneiderFormulae() {
        this.formulaOption = 1;
        if (this.permittivitySet && this.separationSet && this.widthSet && this.thicknessSet) {
            this.calcIdealCharacteristicImpedance();
        }

    }

    public void useIPCD317AFormulae() {
        this.formulaOption = 2;
        if (this.permittivitySet && this.separationSet && this.widthSet && this.thicknessSet) {
            this.calcIdealCharacteristicImpedance();
        }

    }

    public void setSurfaceTrackWidth(double var1) {
        if (var1 <= 0.0D) {
            throw new IllegalArgumentException("The plate width, " + var1 + ", must be greater than zero");
        } else {
            this.trackWidth = var1;
            this.effectiveTrackWidth = var1;
            this.widthSet = true;
            if (this.permittivitySet && this.separationSet) {
                if (this.formulaOption == 0) {
                    this.calcIdealCharacteristicImpedance();
                } else if (this.thicknessSet) {
                    this.calcIdealCharacteristicImpedance();
                }
            }

        }
    }

    public double getEffectiveTrackWidth() {
        if (!this.z0calculated) {
            this.calcIdealCharacteristicImpedance();
        }

        if (this.formulaOption == 2) {
            this.effectiveTrackWidth = this.trackWidth;
            System.out.println("Method: getEffectiveTrackWidth()");
            System.out.println("The effective track width is not calculated explicitely for the IPC-D-317A formula.");
            System.out.println("The unadjusted track width has been returned.");
        }

        return this.effectiveTrackWidth;
    }

    public void setSurfaceTrackThickness(double var1) {
        if (var1 <= 0.0D) {
            throw new IllegalArgumentException("The plate thickness, " + var1 + ", must be greater than zero");
        } else {
            this.trackThickness = var1;
            this.thicknessSet = true;
            if (this.permittivitySet && this.separationSet && this.widthSet) {
                this.calcIdealCharacteristicImpedance();
            }

        }
    }

    public void setPlateSeparation(double var1) {
        if (var1 <= 0.0D) {
            throw new IllegalArgumentException("The plate separation, " + var1 + ", must be greater than zero");
        } else {
            this.plateSeparation = var1;
            this.separationSet = true;
            if (this.permittivitySet && this.widthSet) {
                if (this.formulaOption == 0) {
                    this.calcIdealCharacteristicImpedance();
                } else if (this.thicknessSet) {
                    this.calcIdealCharacteristicImpedance();
                }
            }

        }
    }

    public void setRelativePermittivity(double var1) {
        this.relativePermittivity = var1;
        this.permittivitySet = true;
        if (this.widthSet && this.separationSet) {
            if (this.formulaOption == 0) {
                this.calcIdealCharacteristicImpedance();
            } else if (this.thicknessSet) {
                this.calcIdealCharacteristicImpedance();
            }
        }

    }

    public double getEffectiveRelativePermittivity() {
        if (!this.permittivitySet) {
            throw new IllegalArgumentException("The relative permittivity has not been entered");
        } else {
            if (!this.z0calculated) {
                this.calcIdealCharacteristicImpedance();
            }

            if (this.formulaOption == 2) {
                this.effectivePermittivity = this.relativePermittivity;
                System.out.println("Method: getEffectiveRelativePermittivity()");
                System.out.println("The effective permittivity is not calculated explicitely for the IPC-D-317A formula.");
                System.out.println("The unadjusted relative permittivity has been returned.");
            }

            return this.effectivePermittivity;
        }
    }

    public void setRelativePermeability(double var1) {
        this.relativePermeability = var1;
        if (this.permittivitySet && this.widthSet && this.separationSet) {
            if (this.formulaOption == 0) {
                this.calcIdealCharacteristicImpedance();
            } else if (this.thicknessSet) {
                this.calcIdealCharacteristicImpedance();
            }
        }

    }

    public void setFrequency(double var1) {
        super.frequency = var1;
        super.omega = this.frequency * 2.0D * 3.141592653589793D;
        if (this.permittivitySet && this.widthSet && this.separationSet) {
            if (this.formulaOption == 0) {
                this.calcIdealCharacteristicImpedance();
            } else if (this.thicknessSet) {
                this.calcIdealCharacteristicImpedance();
            }
        }

    }

    private Complex calcIdealCharacteristicImpedance() {
        if (!this.separationSet) {
            throw new IllegalArgumentException("The strip line plate separation has not been entered");
        } else if (!this.widthSet) {
            throw new IllegalArgumentException("The strip line track width has not been entered");
        } else if (!this.permittivitySet) {
            throw new IllegalArgumentException("The relative permittivity has not been entered");
        } else {
            double var1 = this.trackWidth / this.plateSeparation;
            double var3 = 1.0D / var1;
            double var5 = this.trackThickness / this.plateSeparation;
            double var7 = this.relativePermittivity + 1.0D;
            double var9 = this.relativePermittivity - 1.0D;
            double var11 = this.plateSeparation * super.frequency * 1.0E-6D;
            switch(this.formulaOption) {
                case 0:
                    if (!this.separationSet) {
                        throw new IllegalArgumentException("The strip line plate separation has not been entered");
                    }

                    if (!this.widthSet) {
                        throw new IllegalArgumentException("The strip line track width has not been entered");
                    }

                    if (!this.permittivitySet) {
                        throw new IllegalArgumentException("The relative permittivity has not been entered");
                    }

                    double var23 = 0.0D;
                    double var25;
                    if (var1 < 3.3D) {
                        var23 = 376.73031346177066D / (3.141592653589793D * Math.sqrt(2.0D * var7));
                        var25 = Math.log(4.0D * var3 + Math.sqrt(16.0D * var3 * var3 + 2.0D));
                        var25 -= 0.5D * (var9 / var7) * (Math.log(1.5707963267948966D) + Math.log(1.2732395447351628D) / this.relativePermittivity);
                        var23 *= var25;
                    } else {
                        var23 = 376.73031346177066D / (2.0D * Math.sqrt(this.relativePermittivity));
                        var25 = var1 / 2.0D + Math.log(4.0D) / 3.141592653589793D + Math.log(1.6767728935975386D) / 6.283185307179586D * var9 / (this.relativePermittivity * this.relativePermittivity);
                        var25 += var7 / (6.283185307179586D * this.relativePermittivity) * (Math.log(4.269867111336783D) + Math.log(var1 / 2.0D + 0.94D));
                        var23 /= var25;
                    }

                    var25 = 0.0D;
                    double var27 = var23 * Math.sqrt(2.0D * var7) * 3.141592653589793D / 376.73031346177066D;
                    var27 += 0.5D * (var9 / var7) * (Math.log(1.5707963267948966D) + Math.log(1.2732395447351628D) / this.relativePermittivity);
                    if (var1 < 1.3D) {
                        var25 = var7 / 2.0D * Math.pow(1.0D - 0.5D * var27 * (var9 / var7) * (Math.log(1.5707963267948966D) + Math.log(1.2732395447351628D) / this.relativePermittivity), -2.0D);
                    } else {
                        var25 = var7 / 2.0D + var9 / 2.0D * Math.pow(1.0D + 10.0D * var3, -0.555D);
                    }

                    double var29 = this.plateSeparation * 376.73031346177066D / (var23 * Math.sqrt(var25));
                    if (super.frequency == 0.0D) {
                        super.idealRealCharacteristicImpedance = var23;
                        this.effectivePermittivity = var25;
                        this.effectiveTrackWidth = var29;
                    } else {
                        System.out.println("QQQ");
                        double[] var31 = new double[]{0.27488D + (0.6315D + 0.525D / Math.pow(1.0D + 0.157D * var11, 20.0D)) * var1 - 0.065683D * Math.exp(-8.7513D * var1), 0.33622D * (1.0D - Math.exp(-0.03442D * this.relativePermittivity)), 0.0363D * Math.exp(-4.6D * var1) * (1.0D - Math.exp(-Math.pow(var11 / 3.87D, 4.97D))), 1.0D + 2.751D * (1.0D - Math.exp(-Math.pow(this.relativePermittivity / 15.916D, 8.0D)))};
                        double var32 = var31[0] * var31[1] * Math.pow((0.1844D + var31[2] * var31[3]) * 10.0D * var11, 1.5763D);
                        this.effectivePermittivity = this.relativePermittivity - (this.relativePermittivity - var25) / (1.0D + var32);
                        double[] var34 = new double[12];
                        var34[0] = 4.766D * Math.exp(-3.228D * Math.pow(var1, 0.641D));
                        var34[1] = 0.016D + Math.pow(0.0514D * this.relativePermittivity, 4.524D);
                        var34[2] = 1.206D - 0.3144D * Math.exp(-0.0389D * Math.pow(this.relativePermittivity, 1.4D)) * (1.0D - Math.exp(-0.267D * Math.pow(var1, 7.0D)));
                        var34[3] = 1.0D + 1.275D * (1.0D - Math.exp(-0.00463D * var34[0] * Math.pow(this.relativePermittivity, 1.674D) * Math.pow(var11 / 18.37D, 2.745D)));
                        var34[4] = 5.086D * var31[1] * Math.pow(var11 / 28.84D, 12.0D) / (0.384D + 0.386D * var34[1]);
                        var34[4] *= Math.exp(-22.2D * Math.pow(var1, 1.92D)) / (1.0D + 1.3D * Math.pow(var11 / 28.84D, 12.0D));
                        var34[4] *= Math.pow(var9, 6.0D) / (1.0D + 10.0D * Math.pow(var9, 6.0D));
                        var34[5] = 1.0D / (0.0962D + Math.pow(19.47D / var11, 6.0D));
                        var34[6] = 1.0D / (1.0D + 0.00245D * Math.pow(var1, 2.0D));
                        var34[7] = 0.9408D * Math.pow(this.effectivePermittivity, var34[3]) - 0.9603D;
                        var34[8] = (0.9408D - var34[4]) * Math.pow(var25, var34[3]) - 0.9603D;
                        var34[9] = 0.707D * (4.4E-4D * Math.pow(this.relativePermittivity, 2.136D) + 0.0184D) * Math.pow(var11 / 12.3D, 1.097D);
                        var34[10] = 1.0D + 0.0503D * Math.pow(this.relativePermittivity, 2.0D) * var34[5] * (1.0D - Math.exp(-Math.pow(var1 / 15.0D, 6.0D)));
                        var34[11] = var34[2] * (1.0D - 1.1241D * (var34[6] / var34[10]) * Math.exp(-0.026D * Math.pow(var11, 1.1566D) - var34[9]));
                        this.idealRealCharacteristicImpedance = var23 * Math.pow(var34[7] / var34[8], var34[11]);
                        System.out.println(var34[7] + " " + var34[8] + " " + var34[11]);
                        this.effectiveTrackWidth = this.plateSeparation * 376.73031346177066D / (this.idealRealCharacteristicImpedance * Math.sqrt(this.effectivePermittivity));
                    }
                    break;
                case 1:
                    if (!this.thicknessSet) {
                        throw new IllegalArgumentException("The strip line track thickness has not been entered");
                    }

                    double var13 = 1.0D / Math.sqrt(1.0D + 12.0D * var3);
                    if (var1 < 1.0D) {
                        var13 += 0.04D * Fmath.square(1.0D - var1);
                    }

                    this.effectivePermittivity = (var7 + var9 * var13) / 2.0D;
                    double var15 = var5 * var5;
                    var15 += Fmath.square(0.3183098861837907D / (this.trackWidth / this.trackThickness + 1.1D));
                    double var17 = this.trackThickness / 3.141592653589793D * Math.log(10.87312731383618D / Math.sqrt(var15));
                    double var19 = var17 * ((1.0D + 1.0D / this.effectivePermittivity) / 2.0D);
                    this.effectiveTrackWidth = this.trackWidth + var19;
                    super.idealRealCharacteristicImpedance = 376.73031346177066D / (6.283185307179586D * Math.sqrt(2.0D * var7));
                    double var21 = (1.0D + 1.0D / this.effectivePermittivity) * 3.141592653589793D * 3.141592653589793D / 2.0D;
                    var21 += Fmath.square(4.0D * this.plateSeparation / this.effectiveTrackWidth);
                    var21 += Fmath.square((14.0D + 8.0D / this.effectivePermittivity) / 11.0D);
                    var21 = Math.sqrt(var21);
                    var21 += (14.0D + 8.0D / this.effectivePermittivity) / 11.0D * (4.0D * this.plateSeparation / this.effectiveTrackWidth);
                    var21 *= 4.0D * this.plateSeparation / this.effectiveTrackWidth;
                    ++var21;
                    var21 = Math.log(var21);
                    super.idealRealCharacteristicImpedance *= var21;
                    break;
                case 2:
                    if (!this.thicknessSet) {
                        throw new IllegalArgumentException("The strip line track thickness has not been entered");
                    }

                    if (var1 < 0.1D || var1 > 3.0D) {
                        System.out.println("WARNING!");
                        System.out.println("IPC-D-317A FORMULA RESTRICTION");
                        System.out.println("For this model the width over separation ratio, " + var1 + ", must lie between 0.1 and 3.0 inclusive");
                        System.out.println("The results are likely to be inaccurate");
                    }

                    if (var5 > 0.25D) {
                        System.out.println("WARNING!");
                        System.out.println("IPC-D-317A FORMULA RESTRICTION");
                        System.out.println("For this model the thickness over separation ratio, " + var5 + ", must be less than 0.25");
                        System.out.println("The results are likely to be inaccurate");
                    }

                    super.idealRealCharacteristicImpedance = 87.0D / Math.sqrt(this.relativePermittivity + 1.41D);
                    super.idealRealCharacteristicImpedance *= Math.log(5.98D * this.plateSeparation / (0.8D * this.trackWidth + this.trackThickness));
                    break;
                default:
                    throw new IllegalArgumentException("Formulae option, " + this.formulaOption + ", must lie between 0 and 2 inclusive");
            }

            super.idealRealCharacteristicImpedance *= Math.sqrt(this.relativePermeability);
            super.idealCharacteristicImpedance = new Complex(super.idealRealCharacteristicImpedance, 0.0D);
            this.z0calculated = true;
            this.calculateDistributedCapacitanceAndInductance();
            return super.idealCharacteristicImpedance;
        }
    }

    public Complex getIdealCharacteristicImpedance() {
        if (!this.z0calculated) {
            this.calcIdealCharacteristicImpedance();
        }

        return super.idealCharacteristicImpedance;
    }

    public double getIdealCharacteristicImpedanceAsReal() {
        if (!this.z0calculated) {
            this.calcIdealCharacteristicImpedance();
        }

        return super.idealRealCharacteristicImpedance;
    }

    private void calculateDistributedCapacitanceAndInductance() {
        if (!this.z0calculated) {
            this.calcIdealCharacteristicImpedance();
        }

        switch(this.formulaOption) {
            case 0:
            case 1:
                super.distributedCapacitance = this.effectiveTrackWidth * this.effectivePermittivity * 8.854187817E-12D / this.plateSeparation;
                break;
            case 2:
                double var1 = Math.log(5.98D * this.plateSeparation / (0.8D * this.trackWidth + this.trackThickness));
                super.distributedCapacitance = 1.7018000000000003E-14D * (this.relativePermittivity + 1.41D) / var1;
                break;
            default:
                throw new IllegalArgumentException("Formulae option, " + this.formulaOption + ", must lie between 0 and 2 inclusive");
        }

        super.distributedInductance = super.distributedCapacitance * this.idealRealCharacteristicImpedance * this.idealRealCharacteristicImpedance;
    }

    public SurfaceStripLine copy() {
        if (this == null) {
            return null;
        } else {
            SurfaceStripLine var1 = new SurfaceStripLine();
            var1.formulaOption = this.formulaOption;
            var1.trackWidth = this.trackWidth;
            var1.effectiveTrackWidth = this.effectiveTrackWidth;
            var1.trackThickness = this.trackThickness;
            var1.plateSeparation = this.plateSeparation;
            var1.separationSet = this.separationSet;
            var1.thicknessSet = this.thicknessSet;
            var1.widthSet = this.widthSet;
            var1.z0calculated = this.z0calculated;
            var1.relativePermittivity = this.relativePermittivity;
            var1.effectivePermittivity = this.effectivePermittivity;
            var1.relativePermeability = this.relativePermeability;
            var1.permittivitySet = this.permittivitySet;
            var1.title = this.title;
            var1.distributedResistance = this.distributedResistance;
            var1.distributedConductance = this.distributedConductance;
            var1.distributedCapacitance = this.distributedCapacitance;
            var1.distributedInductance = this.distributedInductance;
            var1.distributedImpedance = this.distributedImpedance.copy();
            var1.distributedAdmittance = this.distributedAdmittance.copy();
            var1.loadImpedance = this.loadImpedance.copy();
            var1.lineLength = this.lineLength;
            var1.segmentLength = this.segmentLength;
            var1.frequency = this.frequency;
            var1.segmentLength = this.segmentLength;
            var1.omega = this.omega;
            var1.inputVoltage = this.inputVoltage.copy();
            var1.inputCurrent = this.inputCurrent.copy();
            var1.outputVoltage = this.outputVoltage.copy();
            var1.outputCurrent = this.outputCurrent.copy();
            var1.idealWavelength = this.idealWavelength;
            var1.generalWavelength = this.generalWavelength;
            var1.lowLossWavelength = this.lowLossWavelength;
            var1.idealPhaseVelocity = this.idealPhaseVelocity;
            var1.generalPhaseVelocity = this.generalPhaseVelocity;
            var1.lowLossPhaseVelocity = this.lowLossPhaseVelocity;
            var1.idealGroupVelocity = this.idealGroupVelocity;
            var1.generalGroupVelocity = this.generalGroupVelocity;
            var1.lowLossGroupVelocity = this.lowLossGroupVelocity;
            var1.delta = this.delta;
            var1.idealAttenuationConstant = this.idealAttenuationConstant;
            var1.generalAttenuationConstant = this.generalAttenuationConstant;
            var1.lowLossAttenuationConstant = this.lowLossAttenuationConstant;
            var1.idealPhaseConstant = this.idealPhaseConstant;
            var1.generalPhaseConstant = this.generalPhaseConstant;
            var1.lowLossPhaseConstant = this.lowLossPhaseConstant;
            var1.idealPropagationConstant = this.idealPropagationConstant.copy();
            var1.loadImpedance = this.loadImpedance.copy();
            var1.loadImpedance = this.loadImpedance.copy();
            var1.loadImpedance = this.loadImpedance.copy();
            var1.generalPropagationConstant = this.generalPropagationConstant.copy();
            var1.lowLossPropagationConstant = this.lowLossPropagationConstant.copy();
            var1.idealCharacteristicImpedance = this.idealCharacteristicImpedance.copy();
            var1.idealRealCharacteristicImpedance = this.idealRealCharacteristicImpedance;
            var1.generalCharacteristicImpedance = this.generalCharacteristicImpedance.copy();
            var1.lowLossCharacteristicImpedance = this.lowLossCharacteristicImpedance.copy();
            var1.idealInputImpedance = this.idealInputImpedance.copy();
            var1.generalInputImpedance = this.generalInputImpedance.copy();
            var1.lowLossInputImpedance = this.lowLossInputImpedance.copy();
            var1.idealShortedLineImpedance = this.idealShortedLineImpedance.copy();
            var1.generalShortedLineImpedance = this.generalShortedLineImpedance.copy();
            var1.lowLossShortedLineImpedance = this.lowLossShortedLineImpedance.copy();
            var1.idealOpenLineImpedance = this.idealOpenLineImpedance.copy();
            var1.generalOpenLineImpedance = this.generalOpenLineImpedance.copy();
            var1.lowLossOpenLineImpedance = this.lowLossOpenLineImpedance.copy();
            var1.idealQuarterWaveLineImpedance = this.idealQuarterWaveLineImpedance.copy();
            var1.generalQuarterWaveLineImpedance = this.generalQuarterWaveLineImpedance.copy();
            var1.lowLossQuarterWaveLineImpedance = this.lowLossQuarterWaveLineImpedance.copy();
            var1.idealHalfWaveLineImpedance = this.idealHalfWaveLineImpedance.copy();
            var1.generalHalfWaveLineImpedance = this.generalHalfWaveLineImpedance.copy();
            var1.lowLossHalfWaveLineImpedance = this.lowLossHalfWaveLineImpedance.copy();
            var1.idealRefectionCoefficient = this.idealRefectionCoefficient.copy();
            var1.generalRefectionCoefficient = this.generalRefectionCoefficient.copy();
            var1.lowLossRefectionCoefficient = this.lowLossRefectionCoefficient.copy();
            var1.idealStandingWaveRatio = this.idealStandingWaveRatio;
            var1.generalStandingWaveRatio = this.generalStandingWaveRatio;
            var1.lowLossStandingWaveRatio = this.lowLossStandingWaveRatio;
            var1.idealABCDmatrix = this.idealABCDmatrix.copy();
            var1.generalABCDmatrix = this.generalABCDmatrix.copy();
            var1.lowLossABCDmatrix = this.lowLossABCDmatrix.copy();
            var1.numberOfPoints = this.numberOfPoints;
            return var1;
        }
    }

    public Object clone() {
        SurfaceStripLine var1 = null;
        if (this != null) {
            SurfaceStripLine var2 = new SurfaceStripLine();
            var2.formulaOption = this.formulaOption;
            var2.trackWidth = this.trackWidth;
            var2.effectiveTrackWidth = this.effectiveTrackWidth;
            var2.trackThickness = this.trackThickness;
            var2.plateSeparation = this.plateSeparation;
            var2.separationSet = this.separationSet;
            var2.thicknessSet = this.thicknessSet;
            var2.widthSet = this.widthSet;
            var2.z0calculated = this.z0calculated;
            var2.relativePermittivity = this.relativePermittivity;
            var2.effectivePermittivity = this.effectivePermittivity;
            var2.relativePermeability = this.relativePermeability;
            var2.permittivitySet = this.permittivitySet;
            var2.title = this.title;
            var2.distributedResistance = this.distributedResistance;
            var2.distributedConductance = this.distributedConductance;
            var2.distributedCapacitance = this.distributedCapacitance;
            var2.distributedInductance = this.distributedInductance;
            var2.distributedImpedance = this.distributedImpedance.copy();
            var2.distributedAdmittance = this.distributedAdmittance.copy();
            var2.loadImpedance = this.loadImpedance.copy();
            var2.lineLength = this.lineLength;
            var2.segmentLength = this.segmentLength;
            var2.frequency = this.frequency;
            var2.segmentLength = this.segmentLength;
            var2.omega = this.omega;
            var2.inputVoltage = this.inputVoltage.copy();
            var2.inputCurrent = this.inputCurrent.copy();
            var2.outputVoltage = this.outputVoltage.copy();
            var2.outputCurrent = this.outputCurrent.copy();
            var2.idealWavelength = this.idealWavelength;
            var2.generalWavelength = this.generalWavelength;
            var2.lowLossWavelength = this.lowLossWavelength;
            var2.idealPhaseVelocity = this.idealPhaseVelocity;
            var2.generalPhaseVelocity = this.generalPhaseVelocity;
            var2.lowLossPhaseVelocity = this.lowLossPhaseVelocity;
            var2.idealGroupVelocity = this.idealGroupVelocity;
            var2.generalGroupVelocity = this.generalGroupVelocity;
            var2.lowLossGroupVelocity = this.lowLossGroupVelocity;
            var2.delta = this.delta;
            var2.idealAttenuationConstant = this.idealAttenuationConstant;
            var2.generalAttenuationConstant = this.generalAttenuationConstant;
            var2.lowLossAttenuationConstant = this.lowLossAttenuationConstant;
            var2.idealPhaseConstant = this.idealPhaseConstant;
            var2.generalPhaseConstant = this.generalPhaseConstant;
            var2.lowLossPhaseConstant = this.lowLossPhaseConstant;
            var2.idealPropagationConstant = this.idealPropagationConstant.copy();
            var2.loadImpedance = this.loadImpedance.copy();
            var2.loadImpedance = this.loadImpedance.copy();
            var2.loadImpedance = this.loadImpedance.copy();
            var2.generalPropagationConstant = this.generalPropagationConstant.copy();
            var2.lowLossPropagationConstant = this.lowLossPropagationConstant.copy();
            var2.idealCharacteristicImpedance = this.idealCharacteristicImpedance.copy();
            var2.idealRealCharacteristicImpedance = this.idealRealCharacteristicImpedance;
            var2.generalCharacteristicImpedance = this.generalCharacteristicImpedance.copy();
            var2.lowLossCharacteristicImpedance = this.lowLossCharacteristicImpedance.copy();
            var2.idealInputImpedance = this.idealInputImpedance.copy();
            var2.generalInputImpedance = this.generalInputImpedance.copy();
            var2.lowLossInputImpedance = this.lowLossInputImpedance.copy();
            var2.idealShortedLineImpedance = this.idealShortedLineImpedance.copy();
            var2.generalShortedLineImpedance = this.generalShortedLineImpedance.copy();
            var2.lowLossShortedLineImpedance = this.lowLossShortedLineImpedance.copy();
            var2.idealOpenLineImpedance = this.idealOpenLineImpedance.copy();
            var2.generalOpenLineImpedance = this.generalOpenLineImpedance.copy();
            var2.lowLossOpenLineImpedance = this.lowLossOpenLineImpedance.copy();
            var2.idealQuarterWaveLineImpedance = this.idealQuarterWaveLineImpedance.copy();
            var2.generalQuarterWaveLineImpedance = this.generalQuarterWaveLineImpedance.copy();
            var2.lowLossQuarterWaveLineImpedance = this.lowLossQuarterWaveLineImpedance.copy();
            var2.idealHalfWaveLineImpedance = this.idealHalfWaveLineImpedance.copy();
            var2.generalHalfWaveLineImpedance = this.generalHalfWaveLineImpedance.copy();
            var2.lowLossHalfWaveLineImpedance = this.lowLossHalfWaveLineImpedance.copy();
            var2.idealRefectionCoefficient = this.idealRefectionCoefficient.copy();
            var2.generalRefectionCoefficient = this.generalRefectionCoefficient.copy();
            var2.lowLossRefectionCoefficient = this.lowLossRefectionCoefficient.copy();
            var2.idealStandingWaveRatio = this.idealStandingWaveRatio;
            var2.generalStandingWaveRatio = this.generalStandingWaveRatio;
            var2.lowLossStandingWaveRatio = this.lowLossStandingWaveRatio;
            var2.idealABCDmatrix = this.idealABCDmatrix.copy();
            var2.generalABCDmatrix = this.generalABCDmatrix.copy();
            var2.lowLossABCDmatrix = this.lowLossABCDmatrix.copy();
            var2.numberOfPoints = this.numberOfPoints;
            var1 = var2;
        }

        return var1;
    }
}
