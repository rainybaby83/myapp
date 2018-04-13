//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package flanagan.circuits;

import flanagan.complex.Complex;
import flanagan.complex.ComplexMatrix;
import flanagan.math.Fmath;
import flanagan.plot.PlotGraph;

public class TransmissionLine {
    protected String title = "Transmission Line";
    protected double distributedResistance = 0.0D;
    protected double distributedConductance = 0.0D;
    protected double distributedCapacitance = 0.0D;
    protected double distributedInductance = 0.0D;
    protected Complex distributedImpedance = null;
    protected Complex distributedAdmittance = null;
    protected Complex loadImpedance = Complex.plusInfinity();
    protected double lineLength = -1.0D;
    protected double segmentLength = -1.0D;
    protected double frequency = 0.0D;
    protected double omega = 0.0D;
    protected Complex inputVoltage = null;
    protected Complex inputCurrent = null;
    protected Complex outputVoltage = null;
    protected Complex outputCurrent = null;
    protected double idealWavelength = 0.0D;
    protected double generalWavelength = 0.0D;
    protected double lowLossWavelength = 0.0D;
    protected double idealPhaseVelocity = 0.0D;
    protected double generalPhaseVelocity = 0.0D;
    protected double lowLossPhaseVelocity = 0.0D;
    protected double idealGroupVelocity = 0.0D;
    protected double generalGroupVelocity = 0.0D;
    protected double lowLossGroupVelocity = 0.0D;
    protected double delta = 0.001D;
    protected double idealAttenuationConstant = 0.0D;
    protected double generalAttenuationConstant = 0.0D;
    protected double lowLossAttenuationConstant = 0.0D;
    protected double idealPhaseConstant = 0.0D;
    protected double generalPhaseConstant = 0.0D;
    protected double lowLossPhaseConstant = 0.0D;
    protected Complex idealPropagationConstant = null;
    protected Complex generalPropagationConstant = null;
    protected Complex lowLossPropagationConstant = null;
    protected Complex idealCharacteristicImpedance = null;
    protected double idealRealCharacteristicImpedance = 0.0D;
    protected Complex generalCharacteristicImpedance = null;
    protected Complex lowLossCharacteristicImpedance = null;
    protected Complex idealInputImpedance = null;
    protected Complex generalInputImpedance = null;
    protected Complex lowLossInputImpedance = null;
    protected Complex idealShortedLineImpedance = null;
    protected Complex generalShortedLineImpedance = null;
    protected Complex lowLossShortedLineImpedance = null;
    protected Complex idealOpenLineImpedance = null;
    protected Complex generalOpenLineImpedance = null;
    protected Complex lowLossOpenLineImpedance = null;
    protected Complex idealQuarterWaveLineImpedance = null;
    protected Complex generalQuarterWaveLineImpedance = null;
    protected Complex lowLossQuarterWaveLineImpedance = null;
    protected Complex idealHalfWaveLineImpedance = null;
    protected Complex generalHalfWaveLineImpedance = null;
    protected Complex lowLossHalfWaveLineImpedance = null;
    protected Complex idealRefectionCoefficient = null;
    protected Complex generalRefectionCoefficient = null;
    protected Complex lowLossRefectionCoefficient = null;
    protected double idealStandingWaveRatio = 0.0D;
    protected double generalStandingWaveRatio = 0.0D;
    protected double lowLossStandingWaveRatio = 0.0D;
    protected ComplexMatrix idealABCDmatrix = null;
    protected ComplexMatrix generalABCDmatrix = null;
    protected ComplexMatrix lowLossABCDmatrix = null;
    protected int numberOfPoints = 1000;

    public TransmissionLine() {
    }

    public TransmissionLine(String var1) {
        this.title = var1;
    }

    public void setTitle(String var1) {
        this.title = var1;
    }

    public String getTitle() {
        return this.title;
    }

    public void setFrequency(double var1) {
        this.frequency = var1;
        this.omega = this.frequency * 2.0D * 3.141592653589793D;
    }

    public double getFrequency() {
        return this.frequency;
    }

    public double getRadialFrequency() {
        return this.omega;
    }

    public void setLoadImpedance(double var1) {
        this.loadImpedance = new Complex(var1, 0.0D);
    }

    public void setLoadImpedance(Complex var1) {
        this.loadImpedance = var1;
    }

    public Complex getLoadImpedance() {
        return this.loadImpedance;
    }

    public void setLineLength(double var1) {
        this.lineLength = var1;
    }

    public double getLineLength() {
        return this.lineLength;
    }

    public void setSegmentLength(double var1) {
        this.segmentLength = var1;
    }

    public void setOutputVoltage(Phasor var1) {
        this.outputVoltage = Phasor.toRectangular(var1);
    }

    public void setOutputVoltage(Complex var1) {
        this.outputVoltage = var1;
    }

    public void setOutputVoltage(double var1, double var3) {
        this.outputVoltage = new Complex();
        this.outputVoltage.polar(var1, var3);
    }

    public void setOutputCurrent(Phasor var1) {
        this.outputCurrent = Phasor.toRectangular(var1);
    }

    public void setOutputCurrent(Complex var1) {
        this.outputCurrent = var1;
    }

    public void setOutputCurrent(double var1, double var3) {
        this.outputCurrent = new Complex();
        this.outputCurrent.polar(var1, var3);
    }

    public void setDistributedResistance(double var1) {
        this.distributedResistance = var1;
    }

    public void setDistributedInductance(double var1) {
        this.distributedInductance = var1;
    }

    public void setDistributedCapacitance(double var1) {
        this.distributedCapacitance = var1;
    }

    public void setDistributedConductance(double var1) {
        this.distributedConductance = var1;
    }

    public double getDistributedResistance() {
        return this.distributedResistance;
    }

    public double getDistributedInductance() {
        return this.distributedInductance;
    }

    public double getDistributedCapacitance() {
        return this.distributedCapacitance;
    }

    public double getDistributedConductance() {
        return this.distributedConductance;
    }

    public Complex getDistributedImpedance() {
        this.distributedImpedance = new Complex(this.distributedResistance, this.distributedInductance * this.omega);
        return this.distributedImpedance;
    }

    public Complex getDistributedAdmittance() {
        this.distributedAdmittance = new Complex(this.distributedConductance, this.distributedCapacitance * this.omega);
        return this.distributedAdmittance;
    }

    public double getWavelength() {
        this.generalWavelength = this.getPhaseVelocity() / this.frequency;
        return this.generalWavelength;
    }

    public double getIdealWavelength() {
        this.idealWavelength = this.getIdealPhaseVelocity() / this.frequency;
        return this.idealWavelength;
    }

    public double getLowLossWavelength() {
        this.lowLossWavelength = this.getLowLossPhaseVelocity() / this.frequency;
        return this.lowLossWavelength;
    }

    public double getPhaseVelocity() {
        this.generalPhaseVelocity = this.omega / this.getPhaseConstant();
        return this.generalPhaseVelocity;
    }

    public double getIdealPhaseVelocity() {
        this.idealPhaseVelocity = this.omega / this.getIdealPhaseConstant();
        return this.idealPhaseVelocity;
    }

    public double getLowLossPhaseVelocity() {
        this.lowLossPhaseVelocity = this.omega / this.getLowLossPhaseConstant();
        return this.lowLossPhaseVelocity;
    }

    public double getGroupVelocity() {
        if (this.distributedResistance == 0.0D && this.distributedConductance == 0.0D) {
            this.generalPhaseVelocity = 1.0D / Math.sqrt(this.distributedInductance * this.distributedCapacitance);
        } else {
            double var1 = this.omega;
            this.omega = var1 * (1.0D - this.delta);
            double var3 = this.getPhaseConstant();
            this.omega = var1 * (1.0D + this.delta);
            double var5 = this.getPhaseConstant();
            this.omega = var1;
            this.generalPhaseVelocity = 2.0D * this.omega * this.delta / (var5 - var3);
        }

        return this.generalGroupVelocity;
    }

    public void setDelta(double var1) {
        this.delta = var1;
    }

    public double getIdealGroupVelocity() {
        this.idealGroupVelocity = 1.0D / Math.sqrt(this.distributedInductance * this.distributedCapacitance);
        return this.idealGroupVelocity;
    }

    public double getLowLossGroupVelocity() {
        double var1 = this.omega * this.omega;
        double var3 = Math.sqrt(this.distributedInductance * this.distributedCapacitance);
        double var5 = this.distributedResistance * this.distributedConductance / (4.0D * var1 * this.distributedInductance * this.distributedCapacitance);
        double var7 = this.distributedConductance * this.distributedConductance / (8.0D * var1 * this.distributedCapacitance * this.distributedCapacitance);
        double var9 = this.distributedResistance * this.distributedResistance / (8.0D * var1 * this.distributedInductance * this.distributedInductance);
        this.lowLossPhaseConstant = 1.0D / (var3 * (1.0D + var5 - var7 - var9));
        return this.lowLossGroupVelocity;
    }

    public double getAttenuationConstant() {
        if (this.distributedResistance == 0.0D && this.distributedConductance == 0.0D) {
            this.generalAttenuationConstant = 0.0D;
        } else {
            this.generalAttenuationConstant = Complex.sqrt(this.getDistributedImpedance().times(this.getDistributedAdmittance())).getReal();
        }

        return this.generalAttenuationConstant;
    }

    public double getLowLossAttenuationConstant() {
        double var1 = Math.sqrt(this.distributedInductance / this.distributedCapacitance);
        double var3 = this.distributedResistance / (2.0D * var1);
        double var5 = this.distributedConductance * var1 / 2.0D;
        this.lowLossAttenuationConstant = var3 + var5;
        return this.lowLossAttenuationConstant;
    }

    public double getIdealAttenuationConstant() {
        this.idealAttenuationConstant = 0.0D;
        return this.idealAttenuationConstant;
    }

    public double getPhaseConstant() {
        if (this.distributedResistance == 0.0D && this.distributedConductance == 0.0D) {
            this.generalPhaseConstant = this.omega * Math.sqrt(this.distributedInductance * this.distributedCapacitance);
        } else {
            this.generalPhaseConstant = Complex.sqrt(this.getDistributedImpedance().times(this.getDistributedAdmittance())).getImag();
        }

        return this.generalPhaseConstant;
    }

    public double getLowLossPhaseConstant() {
        double var1 = this.omega * this.omega;
        double var3 = this.omega * Math.sqrt(this.distributedInductance * this.distributedCapacitance);
        double var5 = this.distributedResistance * this.distributedConductance / (4.0D * var1 * this.distributedInductance * this.distributedCapacitance);
        double var7 = this.distributedConductance * this.distributedConductance / (8.0D * var1 * this.distributedCapacitance * this.distributedCapacitance);
        double var9 = this.distributedResistance * this.distributedResistance / (8.0D * var1 * this.distributedInductance * this.distributedInductance);
        this.lowLossPhaseConstant = var3 * (1.0D - var5 + var7 + var9);
        return this.lowLossPhaseConstant;
    }

    public double getIdealPhaseConstant() {
        this.idealPhaseConstant = this.omega * Math.sqrt(this.distributedInductance * this.distributedCapacitance);
        return this.idealPhaseConstant;
    }

    public Complex getPropagationConstant() {
        if (this.distributedResistance == 0.0D && this.distributedConductance == 0.0D) {
            this.generalPropagationConstant = new Complex(0.0D, this.omega * Math.sqrt(this.distributedInductance * this.distributedCapacitance));
        } else {
            this.generalPropagationConstant = Complex.sqrt(this.getDistributedImpedance().times(this.getDistributedAdmittance()));
        }

        return this.generalPropagationConstant;
    }

    public Complex getLowLossPropagationConstant() {
        this.lowLossPropagationConstant = new Complex(this.getLowLossAttenuationConstant(), this.getLowLossPhaseConstant());
        return this.lowLossPropagationConstant;
    }

    public Complex getIdealPropagationConstant() {
        this.idealPropagationConstant = new Complex(0.0D, this.omega * Math.sqrt(this.distributedInductance * this.distributedCapacitance));
        return this.idealPropagationConstant;
    }

    public Complex getCharacteristicImpedance() {
        this.generalCharacteristicImpedance = Complex.sqrt(this.getDistributedImpedance().over(this.getDistributedAdmittance()));
        return this.generalCharacteristicImpedance;
    }

    public Complex getLowLossCharacteristicImpedance() {
        double var1 = this.omega * this.omega;
        double var3 = Math.sqrt(this.distributedInductance / this.distributedCapacitance);
        double var5 = this.distributedResistance * this.distributedResistance / (8.0D * var1 * this.distributedInductance * this.distributedInductance);
        double var7 = this.distributedConductance * this.distributedConductance / (8.0D * var1 * this.distributedCapacitance * this.distributedCapacitance);
        double var9 = this.distributedResistance * this.distributedConductance / (4.0D * var1 * this.distributedInductance * this.distributedCapacitance);
        double var11 = this.distributedConductance / (2.0D * this.omega * this.distributedCapacitance);
        double var13 = this.distributedResistance / (2.0D * this.omega * this.distributedInductance);
        this.lowLossCharacteristicImpedance = new Complex(var3 * (1.0D + var5 - var7 + var9), var3 * (var11 - var13));
        return this.lowLossCharacteristicImpedance;
    }

    public Complex getIdealCharacteristicImpedance() {
        this.idealRealCharacteristicImpedance = Math.sqrt(this.distributedInductance / this.distributedCapacitance);
        this.idealCharacteristicImpedance = new Complex(this.idealRealCharacteristicImpedance, 0.0D);
        return this.idealCharacteristicImpedance;
    }

    public double getIdealCharacteristicImpedanceAsReal() {
        this.idealRealCharacteristicImpedance = Math.sqrt(this.distributedInductance / this.distributedCapacitance);
        this.idealCharacteristicImpedance = new Complex(this.idealRealCharacteristicImpedance, 0.0D);
        return this.idealRealCharacteristicImpedance;
    }

    public Complex getInputImpedance() {
        Complex var1 = this.getPropagationConstant();
        Complex var2 = this.getCharacteristicImpedance();
        Complex var3 = Complex.cosh(var1.times(this.lineLength));
        Complex var4 = Complex.sinh(var1.times(this.lineLength));
        Complex var5 = var3.times(this.loadImpedance);
        Complex var6 = var4.times(var2);
        Complex var7 = var3.times(var2);
        Complex var8 = var4.times(this.loadImpedance);
        Complex var9 = var5.plus(var6).over(var7.plus(var8));
        this.generalInputImpedance = var2.times(var9);
        return this.generalInputImpedance;
    }

    public Complex getLowLossInputImpedance() {
        Complex var1 = this.getLowLossPropagationConstant();
        Complex var2 = this.getLowLossCharacteristicImpedance();
        Complex var3 = Complex.cosh(var1.times(this.lineLength));
        Complex var4 = Complex.sinh(var1.times(this.lineLength));
        Complex var5 = var3.times(this.loadImpedance);
        Complex var6 = var4.times(var2);
        Complex var7 = var3.times(var2);
        Complex var8 = var4.times(this.loadImpedance);
        Complex var9 = var5.plus(var6).over(var7.plus(var8));
        this.lowLossInputImpedance = var2.times(var9);
        return this.lowLossInputImpedance;
    }

    public Complex getIdealInputImpedance() {
        double var1 = this.getIdealPhaseConstant();
        double var3 = this.getIdealCharacteristicImpedanceAsReal();
        double var5 = Math.cos(var1 * this.lineLength);
        double var7 = Math.sin(var1 * this.lineLength);
        Complex var9 = (new Complex(0.0D, var7 * var3)).plus(this.loadImpedance.times(var5));
        Complex var10 = (new Complex(var5 * var3, 0.0D)).plus(Complex.plusJay().times(this.loadImpedance.times(var7)));
        Complex var11 = var9.over(var10);
        this.idealInputImpedance = var11.times(var3);
        return this.idealInputImpedance;
    }

    public Complex getShortedLineImpedance() {
        if (this.lineLength == -1.0D) {
            throw new IllegalArgumentException("No line length as been entered");
        } else {
            this.generalShortedLineImpedance = this.getCharacteristicImpedance().times(Complex.tanh(this.getPropagationConstant().times(this.lineLength)));
            return this.generalShortedLineImpedance;
        }
    }

    public Complex getLowLossShortedLineImpedance() {
        if (this.lineLength == -1.0D) {
            throw new IllegalArgumentException("No line length as been entered");
        } else {
            double var1 = this.getLowLossAttenuationConstant() * this.lineLength;
            double var3 = Math.cos(this.getLowLossPhaseConstant() * this.lineLength);
            double var5 = Math.sin(this.getLowLossPhaseConstant() * this.lineLength);
            Complex var7 = new Complex(var1 * var3, var5);
            Complex var8 = new Complex(var3, var1 * var5);
            this.lowLossShortedLineImpedance = var7.over(var8);
            return this.lowLossShortedLineImpedance;
        }
    }

    public Complex getIdealShortedLineImpedance() {
        if (this.lineLength == -1.0D) {
            throw new IllegalArgumentException("No line length as been entered");
        } else {
            this.idealShortedLineImpedance = new Complex(0.0D, this.getIdealCharacteristicImpedanceAsReal() * Math.tan(this.getIdealPhaseConstant() * this.lineLength));
            return this.idealShortedLineImpedance;
        }
    }

    public Complex getOpenLineImpedance() {
        if (this.lineLength == -1.0D) {
            throw new IllegalArgumentException("No line length as been entered");
        } else {
            this.generalShortedLineImpedance = this.getCharacteristicImpedance().times(Complex.coth(this.getPropagationConstant().times(this.lineLength)));
            return this.generalShortedLineImpedance;
        }
    }

    public Complex getLowLossOpenLineImpedance() {
        if (this.lineLength == -1.0D) {
            throw new IllegalArgumentException("No line length as been entered");
        } else {
            double var1 = this.getLowLossAttenuationConstant() * this.lineLength;
            double var3 = Math.cos(this.getLowLossPhaseConstant() * this.lineLength);
            double var5 = Math.sin(this.getLowLossPhaseConstant() * this.lineLength);
            Complex var7 = new Complex(var3, var1 * var5);
            Complex var8 = new Complex(var1 * var3, var5);
            this.lowLossShortedLineImpedance = var7.over(var8);
            return this.lowLossShortedLineImpedance;
        }
    }

    public Complex getIdealOpenLineImpedance() {
        if (this.lineLength == -1.0D) {
            throw new IllegalArgumentException("No line length as been entered");
        } else {
            this.idealShortedLineImpedance = new Complex(0.0D, -this.getIdealCharacteristicImpedanceAsReal() * Fmath.cot(this.getIdealPhaseConstant() * this.lineLength));
            return this.idealShortedLineImpedance;
        }
    }

    public Complex getQuarterWaveLineImpedance() {
        Complex var1 = new Complex(this.getAttenuationConstant(), 0.0D);
        Complex var2 = this.getCharacteristicImpedance();
        Complex var3 = Complex.sinh(var1.times(this.lineLength));
        Complex var4 = Complex.cosh(var1.times(this.lineLength));
        Complex var5 = var3.times(this.loadImpedance);
        Complex var6 = var4.times(var2);
        Complex var7 = var3.times(var2);
        Complex var8 = var4.times(this.loadImpedance);
        Complex var9 = var5.plus(var6).over(var7.plus(var8));
        this.generalQuarterWaveLineImpedance = var2.times(var9);
        return this.generalQuarterWaveLineImpedance;
    }

    public Complex getLowLossQuarterWaveLineImpedance() {
        Complex var1 = new Complex(this.getLowLossAttenuationConstant(), 0.0D);
        Complex var2 = this.getLowLossCharacteristicImpedance();
        Complex var3 = var1.times(this.lineLength);
        Complex var4 = var2.plus(this.loadImpedance.times(var3));
        Complex var5 = this.loadImpedance.plus(var2.times(var3));
        Complex var6 = var4.over(var5);
        this.lowLossQuarterWaveLineImpedance = var2.times(var6);
        return this.lowLossQuarterWaveLineImpedance;
    }

    public Complex getIdealQuarterWaveLineImpedance() {
        Complex var1 = new Complex(Fmath.square(this.getIdealCharacteristicImpedanceAsReal()), 0.0D);
        this.idealQuarterWaveLineImpedance = var1.over(this.loadImpedance);
        return this.idealQuarterWaveLineImpedance;
    }

    public Complex getHalfWaveLineImpedance() {
        Complex var1 = new Complex(this.getAttenuationConstant(), 0.0D);
        Complex var2 = this.getCharacteristicImpedance();
        Complex var3 = Complex.cosh(var1.times(this.lineLength));
        Complex var4 = Complex.sinh(var1.times(this.lineLength));
        Complex var5 = var3.times(this.loadImpedance);
        Complex var6 = var4.times(var2);
        Complex var7 = var3.times(var2);
        Complex var8 = var4.times(this.loadImpedance);
        Complex var9 = var5.plus(var6).over(var7.plus(var8));
        this.generalHalfWaveLineImpedance = var2.times(var9);
        return this.generalHalfWaveLineImpedance;
    }

    public Complex getLowLossHalfWaveLineImpedance() {
        Complex var1 = new Complex(this.getLowLossAttenuationConstant(), 0.0D);
        Complex var2 = this.getLowLossCharacteristicImpedance();
        Complex var3 = var1.times(this.lineLength);
        Complex var4 = this.loadImpedance.plus(var2.times(var3));
        Complex var5 = var2.plus(this.loadImpedance.times(var3));
        Complex var6 = var4.over(var5);
        this.lowLossHalfWaveLineImpedance = var2.times(var6);
        return this.lowLossHalfWaveLineImpedance;
    }

    public Complex getIdealHalfWaveLineImpedance() {
        this.idealHalfWaveLineImpedance = this.loadImpedance;
        return this.idealHalfWaveLineImpedance;
    }

    public Complex getRefectionCoefficient() {
        Complex var1 = this.loadImpedance.minus(this.getCharacteristicImpedance());
        Complex var2 = this.loadImpedance.plus(this.getCharacteristicImpedance());
        this.generalRefectionCoefficient = var1.over(var2);
        return this.generalRefectionCoefficient;
    }

    public Complex getLowLossRefectionCoefficient() {
        Complex var1 = this.loadImpedance.minus(this.getLowLossCharacteristicImpedance());
        Complex var2 = this.loadImpedance.plus(this.getLowLossCharacteristicImpedance());
        this.lowLossRefectionCoefficient = var1.over(var2);
        return this.lowLossRefectionCoefficient;
    }

    public Complex getIdealRefectionCoefficient() {
        Complex var1 = this.loadImpedance.minus(this.getIdealCharacteristicImpedance());
        Complex var2 = this.loadImpedance.plus(this.getIdealCharacteristicImpedance());
        this.idealRefectionCoefficient = var1.over(var2);
        return this.idealRefectionCoefficient;
    }

    public double getStandingWaveRatio() {
        double var1 = this.getRefectionCoefficient().abs();
        this.generalStandingWaveRatio = (1.0D + var1) / (1.0D - var1);
        return this.generalStandingWaveRatio;
    }

    public double getLowLossStandingWaveRatio() {
        double var1 = this.getLowLossRefectionCoefficient().abs();
        this.lowLossStandingWaveRatio = (1.0D + var1) / (1.0D - var1);
        return this.lowLossStandingWaveRatio;
    }

    public double getIdealStandingWaveRatio() {
        double var1 = this.getIdealRefectionCoefficient().abs();
        this.idealStandingWaveRatio = (1.0D + var1) / (1.0D - var1);
        return this.idealStandingWaveRatio;
    }

    public ComplexMatrix getABCDmatrix() {
        if (this.segmentLength == -1.0D) {
            throw new IllegalArgumentException("No distance along the line as been entered");
        } else {
            if (this.distributedResistance == 0.0D && this.distributedConductance == 0.0D) {
                this.generalABCDmatrix = this.getIdealABCDmatrix();
            } else {
                this.generalABCDmatrix = new ComplexMatrix(2, 2);
                Complex var1 = this.getPropagationConstant().times(this.segmentLength);
                Complex var2 = this.getCharacteristicImpedance();
                this.generalABCDmatrix.setElement(0, 0, Complex.cosh(var1));
                this.generalABCDmatrix.setElement(0, 1, Complex.sinh(var1).times(var2));
                this.generalABCDmatrix.setElement(1, 0, Complex.sinh(var1).over(var2));
                this.generalABCDmatrix.setElement(1, 1, Complex.cosh(var1));
            }

            return this.generalABCDmatrix;
        }
    }

    public ComplexMatrix getIdealABCDmatrix() {
        if (this.segmentLength == -1.0D) {
            throw new IllegalArgumentException("No distance along the line as been entered");
        } else {
            this.idealABCDmatrix = new ComplexMatrix(2, 2);
            double var1 = this.getIdealPhaseConstant() * this.segmentLength;
            double var3 = this.getIdealCharacteristicImpedanceAsReal();
            this.idealABCDmatrix.setElement(0, 0, new Complex(Math.cos(var1), 0.0D));
            this.idealABCDmatrix.setElement(0, 1, new Complex(0.0D, Math.sin(var1) * var3));
            this.idealABCDmatrix.setElement(1, 0, new Complex(0.0D, Math.sin(var1) / var3));
            this.idealABCDmatrix.setElement(1, 1, new Complex(Math.cos(var1), 0.0D));
            return this.idealABCDmatrix;
        }
    }

    public ComplexMatrix getLowLossABCDmatrix() {
        if (this.segmentLength == -1.0D) {
            throw new IllegalArgumentException("No distance along the line as been entered");
        } else {
            this.lowLossABCDmatrix = new ComplexMatrix(2, 2);
            Complex var1 = this.getLowLossPropagationConstant().times(this.segmentLength);
            Complex var2 = this.getLowLossCharacteristicImpedance();
            this.lowLossABCDmatrix.setElement(0, 0, Complex.cosh(var1));
            this.lowLossABCDmatrix.setElement(0, 1, Complex.sinh(var1).times(var2));
            this.lowLossABCDmatrix.setElement(1, 0, Complex.sinh(var1).over(var2));
            this.lowLossABCDmatrix.setElement(1, 1, Complex.cosh(var1));
            return this.lowLossABCDmatrix;
        }
    }

    public Complex[] voltageAndCurrentAsComplex(double var1) {
        this.segmentLength = var1;
        return this.voltageAndCurrentAsComplex();
    }

    public Complex[] voltageAndCurrentAsComplex() {
        Complex[] var1 = new Complex[]{this.outputVoltage, this.outputCurrent};
        ComplexMatrix var2 = this.getABCDmatrix();
        Complex[] var3 = var2.solveLinearSet(var1);
        this.inputVoltage = var3[0];
        this.inputCurrent = var3[1];
        return var3;
    }

    public Phasor[] voltageAndCurrentAsPhasor(double var1) {
        this.segmentLength = var1;
        Complex[] var3 = new Complex[]{this.outputVoltage, this.outputCurrent};
        ComplexMatrix var4 = this.getABCDmatrix();
        Complex[] var5 = var4.solveLinearSet(var3);
        this.inputVoltage = var5[0];
        this.inputCurrent = var5[1];
        Phasor[] var6 = new Phasor[]{Phasor.toPhasor(this.inputVoltage), Phasor.toPhasor(this.inputCurrent)};
        return var6;
    }

    public Phasor[] voltageAndCurrentAsPhasor() {
        Complex[] var1 = new Complex[]{this.outputVoltage, this.outputCurrent};
        ComplexMatrix var2 = this.getABCDmatrix();
        Complex[] var3 = var2.solveLinearSet(var1);
        this.inputVoltage = var3[0];
        this.inputCurrent = var3[1];
        Phasor[] var4 = new Phasor[]{Phasor.toPhasor(this.inputVoltage), Phasor.toPhasor(this.inputCurrent)};
        return var4;
    }

    public double[] voltageAndCurrentAsReal() {
        Complex[] var1 = new Complex[]{this.outputVoltage, this.outputCurrent};
        ComplexMatrix var2 = this.getABCDmatrix();
        Complex[] var3 = var2.solveLinearSet(var1);
        double[] var4 = new double[]{var3[0].abs() * Math.cos(var3[0].arg()), var3[1].abs() * Math.cos(var3[1].arg())};
        return var4;
    }

    public double[] voltageAndCurrentAsReal(double var1) {
        this.segmentLength = var1;
        return this.voltageAndCurrentAsReal();
    }

    public double[] voltageAndCurrentAsMagnitudeAndPhase() {
        Complex[] var1 = new Complex[]{this.outputVoltage, this.outputCurrent};
        ComplexMatrix var2 = this.getABCDmatrix();
        Complex[] var3 = var2.solveLinearSet(var1);
        double[] var4 = new double[]{var3[0].abs(), var3[0].arg(), var3[1].abs(), var3[1].arg()};
        return var4;
    }

    public double[] voltageAndCurrentAsAsMagnitudeAndPhase(double var1) {
        this.segmentLength = var1;
        return this.voltageAndCurrentAsMagnitudeAndPhase();
    }

    public void plotVandI() {
        double[][] var1 = PlotGraph.data(4, this.numberOfPoints);
        double var2 = this.segmentLength / (double)(this.numberOfPoints - 1);
        var1[0][0] = 0.0D;
        var1[2][0] = 0.0D;

        int var4;
        for(var4 = 1; var4 < this.numberOfPoints; ++var4) {
            var1[0][var4] = var1[0][var4 - 1] + var2;
            var1[2][var4] = var1[2][var4 - 1] + var2;
        }

        for(var4 = 0; var4 < this.numberOfPoints; ++var4) {
            double[] var5 = this.voltageAndCurrentAsReal(var1[0][var4]);
            var1[1][var4] = var5[0];
            var1[3][var4] = var5[1];
        }

        var1[4][0] = 0.0D;
        var1[6][0] = 0.0D;
        var1[4][1] = var1[0][this.numberOfPoints / 2];
        var1[6][1] = var1[0][this.numberOfPoints / 2];
        var1[4][2] = var1[0][this.numberOfPoints - 1];
        var1[6][2] = var1[0][this.numberOfPoints - 1];
        var1[5][0] = var1[1][0];
        var1[7][0] = var1[3][0];
        var1[5][1] = var1[1][this.numberOfPoints / 2];
        var1[7][1] = var1[3][this.numberOfPoints / 2];
        var1[5][2] = var1[1][this.numberOfPoints - 1];
        var1[7][2] = var1[3][this.numberOfPoints - 1];
        PlotGraph var7 = new PlotGraph(var1);
        int[] var8 = new int[]{3, 3, 0, 0};
        var7.setLine(var8);
        int[] var6 = new int[]{0, 0, 1, 2};
        var7.setPoint(var6);
        var7.setXaxisLegend("distance / metres");
        var7.setYaxisLegend("Voltage / V and Current / A");
        var7.plot();
    }

    public TransmissionLine copy() {
        if (this == null) {
            return null;
        } else {
            TransmissionLine var1 = new TransmissionLine();
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
        TransmissionLine var1 = null;
        if (this != null) {
            TransmissionLine var2 = new TransmissionLine();
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
