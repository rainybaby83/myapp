//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.circuits;

public class TwoWireLine extends TransmissionLine {
    private double wireRadius = -1.0D;
    private double wireSeparation = -1.0D;
    private boolean distancesSet = false;
    private double relativePermittivity = 1.0D;
    private double relativePermeability = 1.0D;

    public TwoWireLine() {
    }

    public void setWireRadius(double var1) {
        if (var1 <= 0.0D) {
            throw new IllegalArgumentException("The wire radius, " + var1 + ", must be greater than zero");
        } else if (this.wireSeparation != -1.0D && this.wireSeparation <= 2.0D * var1) {
            throw new IllegalArgumentException("The wire separation distance, " + this.wireSeparation + ", must be greater than the sum of the two wire radii, " + 2.0D * var1);
        } else {
            this.wireRadius = var1;
            if (this.wireSeparation != -1.0D) {
                this.distancesSet = true;
            }

            if (this.distancesSet) {
                this.calculateDistributedCapacitanceAndInductance();
            }

        }
    }

    public void setWireSeparation(double var1) {
        if (var1 <= 0.0D) {
            throw new IllegalArgumentException("The wire separation, " + var1 + ", must be greater than zero");
        } else if (this.wireRadius != -1.0D && var1 <= 2.0D * this.wireRadius) {
            throw new IllegalArgumentException("The wire separation distance, " + var1 + ", must be greater than the sum of the two wire radii, " + 2.0D * this.wireRadius);
        } else {
            this.wireSeparation = var1;
            if (this.wireRadius != -1.0D) {
                this.distancesSet = true;
            }

            if (this.distancesSet) {
                this.calculateDistributedCapacitanceAndInductance();
            }

        }
    }

    public void setRelativePermittivity(double var1) {
        this.relativePermittivity = var1;
        if (this.distancesSet) {
            this.calculateDistributedCapacitanceAndInductance();
        }

    }

    public void setRelativePermeability(double var1) {
        this.relativePermeability = var1;
        if (this.distancesSet) {
            this.calculateDistributedCapacitanceAndInductance();
        }

    }

    private void calculateDistributedCapacitanceAndInductance() {
        super.distributedCapacitance = Impedance.parallelWiresCapacitance(1.0D, this.wireRadius, this.wireSeparation, this.relativePermittivity);
        super.distributedInductance = Impedance.parallelWiresInductance(1.0D, this.wireRadius, this.wireSeparation, this.relativePermeability);
    }

    public TwoWireLine copy() {
        if (this == null) {
            return null;
        } else {
            TwoWireLine var1 = new TwoWireLine();
            var1.wireRadius = this.wireRadius;
            var1.wireSeparation = this.wireSeparation;
            var1.distancesSet = this.distancesSet;
            var1.relativePermittivity = this.relativePermittivity;
            var1.relativePermeability = this.relativePermeability;
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
        TwoWireLine var1 = null;
        if (this != null) {
            TwoWireLine var2 = new TwoWireLine();
            var2.wireRadius = this.wireRadius;
            var2.wireSeparation = this.wireSeparation;
            var2.distancesSet = this.distancesSet;
            var2.relativePermittivity = this.relativePermittivity;
            var2.relativePermeability = this.relativePermeability;
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
