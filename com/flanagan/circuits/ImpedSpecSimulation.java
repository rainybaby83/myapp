//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.circuits;

import com.flanagan.complex.Complex;
import com.flanagan.io.Db;
import com.flanagan.io.FileOutput;
import com.flanagan.math.Conv;
import com.flanagan.math.Fmath;
import com.flanagan.plot.PlotGraph;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

public class ImpedSpecSimulation {
    private double lowestFrequency = 0.0D;
    private double lowestOmega = 0.0D;
    private boolean lowestSet = false;
    private double highestFrequency = 0.0D;
    private double highestOmega = 0.0D;
    private boolean highestSet = false;
    private boolean logOrLinear = true;
    private double increment = 0.0D;
    private double[] frequencies = null;
    private double[] omegas = null;
    private double[] log10frequencies = null;
    private double[] log10omegas = null;
    private int numberOfFrequencies = 800;
    private boolean numberSet = true;
    private boolean frequenciesSet = false;
    private int modelNumber = 0;
    private double[] parameters = null;
    private int numberOfParameters = 0;
    private String[] modelParameterSymbols = null;
    private boolean parametersSet = false;
    private boolean modelSet = false;
    private Complex[] impedances = null;
    private double[] magnitudesZ = null;
    private double[] phasesRadZ = null;
    private double[] phasesDegZ = null;
    private double[] realZ = null;
    private double[] imagZ = null;
    private boolean impedancesSet = false;
    private Complex[] voltages = null;
    private double[] magnitudesV = null;
    private double[] phasesRadV = null;
    private double[] phasesDegV = null;
    private double[] realV = null;
    private double[] imagV = null;
    private ImpedSpecModel userModel = null;
    private String simulationTitle = null;
    private boolean fileType = false;
    private Complex appliedVoltage = null;
    private boolean voltageSet = false;
    private Complex referenceImpedance = null;
    private boolean referenceSet = false;

    public ImpedSpecSimulation() {
        this.simulationTitle = "  ";
    }

    public ImpedSpecSimulation(String var1) {
        this.simulationTitle = var1;
    }

    public void setScanRangeHz(double var1, double var3) {
        this.lowestFrequency = var1;
        this.lowestOmega = 6.283185307179586D * var1;
        this.highestFrequency = var3;
        this.highestOmega = 6.283185307179586D * var3;
        this.calculateFrequencies();
    }

    public void setScanRangeRadians(double var1, double var3) {
        this.lowestFrequency = var1 / 6.283185307179586D;
        this.lowestOmega = var1;
        this.highestFrequency = var3 / 6.283185307179586D;
        this.highestOmega = var3;
        this.calculateFrequencies();
    }

    public void setLowFrequency(double var1) {
        this.lowestFrequency = var1;
        this.lowestOmega = 6.283185307179586D * var1;
        this.lowestSet = true;
        if (this.highestSet && this.numberSet) {
            this.calculateFrequencies();
        }

    }

    public void setLowRadialFrequency(double var1) {
        this.lowestOmega = var1;
        this.lowestFrequency = var1 / 6.283185307179586D;
        this.lowestSet = true;
        if (this.highestSet && this.numberSet) {
            this.calculateFrequencies();
        }

    }

    public void setHighFrequency(double var1) {
        this.highestFrequency = var1;
        this.highestOmega = 6.283185307179586D * var1;
        this.highestSet = true;
        if (this.lowestSet && this.numberSet) {
            this.calculateFrequencies();
        }

    }

    public void setHighRadialFrequency(double var1) {
        this.highestOmega = var1;
        this.highestFrequency = var1 / 6.283185307179586D;
        this.highestSet = true;
        if (this.lowestSet && this.numberSet) {
            this.calculateFrequencies();
        }

    }

    private void calculateFrequencies() {
        if (this.logOrLinear) {
            double var1 = Fmath.log10(this.lowestFrequency);
            double var3 = Fmath.log10(this.highestFrequency);
            this.increment = (var3 - var1) / (double)(this.numberOfFrequencies - 1);
            this.frequencies = new double[this.numberOfFrequencies];
            this.log10frequencies = new double[this.numberOfFrequencies];
            this.omegas = new double[this.numberOfFrequencies];
            this.log10omegas = new double[this.numberOfFrequencies];
            this.log10frequencies[0] = var1;
            this.log10frequencies[this.numberOfFrequencies - 1] = var3;

            int var5;
            for(var5 = 1; var5 < this.numberOfFrequencies - 1; ++var5) {
                this.log10frequencies[var5] = this.log10frequencies[var5 - 1] + this.increment;
            }

            for(var5 = 0; var5 < this.numberOfFrequencies; ++var5) {
                this.frequencies[var5] = Math.pow(10.0D, this.log10frequencies[var5]);
                this.omegas[var5] = this.frequencies[var5] * 2.0D * 3.141592653589793D;
                this.log10omegas[var5] = Fmath.log10(this.omegas[var5]);
            }
        } else {
            this.increment = (this.highestFrequency - this.lowestFrequency) / (double)(this.numberOfFrequencies - 1);
            this.frequencies = new double[this.numberOfFrequencies];
            this.frequencies[0] = this.lowestFrequency;
            this.log10frequencies = new double[this.numberOfFrequencies];
            this.omegas = new double[this.numberOfFrequencies];
            this.log10omegas = new double[this.numberOfFrequencies];
            this.frequencies[this.numberOfFrequencies - 1] = this.highestFrequency;

            int var6;
            for(var6 = 1; var6 < this.numberOfFrequencies - 1; ++var6) {
                this.frequencies[var6] = this.frequencies[var6 - 1] + this.increment;
            }

            for(var6 = 0; var6 < this.numberOfFrequencies; ++var6) {
                this.log10frequencies[var6] = Fmath.log10(this.frequencies[var6]);
                this.omegas[var6] = this.frequencies[var6] * 2.0D * 3.141592653589793D;
                this.log10omegas[var6] = Fmath.log10(this.omegas[var6]);
            }
        }

        this.frequenciesSet = true;
    }

    public void setLinearPlot() {
        this.logOrLinear = false;
        if (this.lowestSet && this.highestSet && this.numberSet) {
            this.calculateFrequencies();
        }

    }

    public void setLog10Plot() {
        this.logOrLinear = true;
        if (this.lowestSet && this.highestSet && this.numberSet) {
            this.calculateFrequencies();
        }

    }

    public void setAppliedVoltage(double var1) {
        this.appliedVoltage = new Complex(var1, 0.0D);
        this.voltageSet = true;
    }

    public void setReferenceImpedance(double var1) {
        this.referenceImpedance = new Complex(var1, 0.0D);
        this.referenceSet = true;
    }

    public void setReferenceImpedance(double var1, double var3) {
        this.referenceImpedance = new Complex(var1, var3);
        this.referenceSet = true;
    }

    public void setReferenceImpedance(Complex var1) {
        this.referenceImpedance = var1;
        this.referenceSet = true;
    }

    public void setModel(int var1, double[] var2) {
        if (var1 != 0 && var1 <= Impedance.numberOfModels) {
            this.modelNumber = var1;
            this.parameters = var2;
            this.modelParameterSymbols = Impedance.modelComponents(var1);
            this.numberOfParameters = this.modelParameterSymbols.length;
            if (this.numberOfParameters != this.parameters.length) {
                throw new IllegalArgumentException("The number of model parametes passed, " + var2.length + ", does not match the number required, " + this.numberOfParameters + ", by model number " + var1);
            } else {
                this.parametersSet = true;
                this.modelSet = true;
            }
        } else {
            throw new IllegalArgumentException("The model number, " + var1 + ", must lie between 1 and " + Impedance.numberOfModels + " inclusive");
        }
    }

    public void setModel(int var1, double[] var2, String[] var3) {
        if (var1 != 0 && var1 <= Impedance.numberOfModels) {
            this.modelNumber = var1;
            this.parameters = var2;
            this.modelParameterSymbols = Impedance.modelComponents(var1);
            this.numberOfParameters = this.modelParameterSymbols.length;
            if (this.numberOfParameters != this.parameters.length) {
                throw new IllegalArgumentException("The number of model parametes passed, " + var2.length + ", does not match the numbber required, " + this.numberOfParameters + ", by model number " + var1);
            } else if (this.numberOfParameters != var3.length) {
                throw new IllegalArgumentException("The number of model symbols passed, " + var3.length + ", does not match the number required, " + this.numberOfParameters + ", by model number " + var1);
            } else {
                this.modelParameterSymbols = var3;
                this.parametersSet = true;
                this.modelSet = true;
            }
        } else {
            throw new IllegalArgumentException("The model number, " + var1 + ", must lie between 1 and " + Impedance.numberOfModels + " inclusive");
        }
    }

    public void setModel(int var1) {
        if (var1 != 0 && var1 <= Impedance.numberOfModels) {
            this.modelNumber = var1;
            this.modelSet = true;
            this.modelParameterSymbols = Impedance.modelComponents(var1);
            this.numberOfParameters = this.modelParameterSymbols.length;
            this.parameters = new double[this.numberOfParameters];
            int var2 = 0;
            String var3 = null;

            while(var2 < this.numberOfParameters) {
                var3 = this.modelParameterSymbols[var2];
                if (var3.trim().charAt(0) == 'R') {
                    this.parameters[var2] = Db.readDouble("Enter resistance " + var3.trim() + " [ohms]");
                    ++var2;
                } else if (var3.trim().charAt(0) == 'C') {
                    this.parameters[var2] = Db.readDouble("Enter capacitance " + var3.trim() + " [farads]");
                    ++var2;
                } else if (var3.trim().charAt(0) == 'L') {
                    this.parameters[var2] = Db.readDouble("Enter inductance " + var3.trim() + " [henries]");
                    ++var2;
                } else if (var3.trim().charAt(0) == 'W') {
                    this.parameters[var2] = Db.readDouble("Enter 'infinite' Warburg constant, sigma, " + var3.trim() + " [ohms*sqrt(radians)]");
                    ++var2;
                } else if (var3.trim().charAt(0) == 'F') {
                    this.parameters[var2] = Db.readDouble("Enter 'finite' Warburg constant, sigma, " + var3.trim() + " [SI units]");
                    ++var2;
                    this.parameters[var2] = Db.readDouble("Enter 'finite' Warburg power, alpha, " + var3.trim());
                    ++var2;
                } else if (var3.trim().charAt(0) == 'Q') {
                    this.parameters[var2] = Db.readDouble("Enter CPE constant, sigma, " + var3.trim() + " [SI units]");
                    ++var2;
                    this.parameters[var2] = Db.readDouble("Enter CPE power, alpha, " + var3.trim());
                    ++var2;
                }
            }

            this.parametersSet = true;
        } else {
            throw new IllegalArgumentException("The model number, " + var1 + ", must lie between 1 and " + Impedance.numberOfModels + " inclusive");
        }
    }

    public void setModel(ImpedSpecModel var1, double[] var2) {
        this.userModel = var1;
        this.parameters = var2;
        this.numberOfParameters = var2.length;
        this.modelParameterSymbols = new String[this.numberOfParameters];

        for(int var3 = 0; var3 < this.numberOfParameters; ++var3) {
            this.modelParameterSymbols[var3] = "P" + (var3 + 1);
        }

        this.parametersSet = true;
    }

    public void setModel(ImpedSpecModel var1, double[] var2, String[] var3) {
        this.userModel = var1;
        this.parameters = var2;
        this.modelParameterSymbols = var3;
        this.numberOfParameters = var2.length;
        this.parametersSet = true;
    }

    public Complex[] calculateImpedances() {
        if (!this.parametersSet) {
            throw new IllegalArgumentException("model parameters values have not been entered");
        } else if (!this.frequenciesSet) {
            throw new IllegalArgumentException("frequency values have not been entered");
        } else {
            this.impedances = Complex.oneDarray(this.numberOfFrequencies);
            int var1;
            if (this.modelSet) {
                for(var1 = 0; var1 < this.numberOfFrequencies; ++var1) {
                    this.impedances[var1] = Impedance.modelImpedance(this.parameters, this.omegas[var1], this.modelNumber);
                }
            } else {
                for(var1 = 0; var1 < this.numberOfFrequencies; ++var1) {
                    this.impedances[var1] = this.userModel.modelImpedance(this.parameters, this.omegas[var1]);
                }
            }

            this.magnitudesZ = new double[this.numberOfFrequencies];
            this.phasesRadZ = new double[this.numberOfFrequencies];
            this.phasesDegZ = new double[this.numberOfFrequencies];
            this.realZ = new double[this.numberOfFrequencies];
            this.imagZ = new double[this.numberOfFrequencies];
            this.magnitudesV = new double[this.numberOfFrequencies];
            this.phasesRadV = new double[this.numberOfFrequencies];
            this.phasesDegV = new double[this.numberOfFrequencies];
            this.realV = new double[this.numberOfFrequencies];
            this.imagV = new double[this.numberOfFrequencies];
            this.voltages = Complex.oneDarray(this.numberOfFrequencies);

            for(var1 = 0; var1 < this.numberOfFrequencies; ++var1) {
                this.magnitudesZ[var1] = Complex.abs(this.impedances[var1]);
                this.phasesRadZ[var1] = Complex.arg(this.impedances[var1]);
                this.phasesDegZ[var1] = Math.toDegrees(this.phasesRadZ[var1]);
                this.realZ[var1] = this.impedances[var1].getReal();
                this.imagZ[var1] = this.impedances[var1].getImag();
                if (this.voltageSet && this.referenceSet) {
                    this.voltages[var1] = this.appliedVoltage.times(this.impedances[var1].over(this.impedances[var1].plus(this.referenceImpedance)));
                    this.magnitudesV[var1] = Complex.abs(this.voltages[var1]);
                    this.phasesRadV[var1] = Complex.arg(this.voltages[var1]);
                    this.phasesDegV[var1] = Math.toDegrees(this.phasesRadV[var1]);
                    this.realV[var1] = this.voltages[var1].getReal();
                    this.imagV[var1] = this.voltages[var1].getImag();
                }
            }

            this.impedancesSet = true;
            return this.impedances;
        }
    }

    public ArrayList<Object> getSimulationResultsAsArrayList(int var1) {
        if (!this.impedancesSet) {
            this.calculateImpedances();
        }

        if (var1 > this.numberOfFrequencies) {
            var1 = this.numberOfFrequencies;
        }

        int var2 = (int)Math.round((double)this.numberOfFrequencies / (double)var1);
        int[] var3 = new int[var1];
        var3[0] = 0;

        for(int var4 = 1; var4 < var1; ++var4) {
            var3[var4] = var3[var4 - 1] + var2;
        }

        if (var3[var1 - 1] != this.numberOfFrequencies - 1) {
            var3[var1 - 1] = this.numberOfFrequencies - 1;
        }

        ArrayList var8 = new ArrayList();
        Complex[] var5 = Complex.oneDarray(var1);

        for(int var6 = 0; var6 < var1; ++var6) {
            var5[var6] = this.impedances[var3[var6]];
        }

        var8.add(Complex.copy(var5));
        double[] var9 = new double[var1];

        int var7;
        for(var7 = 0; var7 < var1; ++var7) {
            var9[var7] = this.realZ[var3[var7]];
        }

        var8.add(Conv.copy(var9));

        for(var7 = 0; var7 < var1; ++var7) {
            var9[var7] = this.imagZ[var3[var7]];
        }

        var8.add(Conv.copy(var9));

        for(var7 = 0; var7 < var1; ++var7) {
            var9[var7] = this.magnitudesZ[var3[var7]];
        }

        var8.add(Conv.copy(var9));

        for(var7 = 0; var7 < var1; ++var7) {
            var9[var7] = this.phasesDegZ[var3[var7]];
        }

        var8.add(Conv.copy(var9));

        for(var7 = 0; var7 < var1; ++var7) {
            var9[var7] = this.phasesRadZ[var3[var7]];
        }

        var8.add(Conv.copy(var9));

        for(var7 = 0; var7 < var1; ++var7) {
            var9[var7] = this.frequencies[var3[var7]];
        }

        var8.add(Conv.copy(var9));

        for(var7 = 0; var7 < var1; ++var7) {
            var9[var7] = this.log10frequencies[var3[var7]];
        }

        var8.add(Conv.copy(var9));

        for(var7 = 0; var7 < var1; ++var7) {
            var9[var7] = this.omegas[var3[var7]];
        }

        var8.add(Conv.copy(var9));
        if (this.voltageSet && this.referenceSet) {
            var8.add(new Double(this.appliedVoltage.getReal()));
            var8.add(this.referenceImpedance);

            for(var7 = 0; var7 < var1; ++var7) {
                var5[var7] = this.voltages[var3[var7]];
            }

            var8.add(Complex.copy(var5));

            for(var7 = 0; var7 < var1; ++var7) {
                var9[var7] = this.realV[var3[var7]];
            }

            var8.add(Conv.copy(var9));

            for(var7 = 0; var7 < var1; ++var7) {
                var9[var7] = this.imagV[var3[var7]];
            }

            var8.add(Conv.copy(var9));

            for(var7 = 0; var7 < var1; ++var7) {
                var9[var7] = this.magnitudesV[var3[var7]];
            }

            var8.add(Conv.copy(var9));

            for(var7 = 0; var7 < var1; ++var7) {
                var9[var7] = this.phasesDegV[var3[var7]];
            }

            var8.add(Conv.copy(var9));

            for(var7 = 0; var7 < var1; ++var7) {
                var9[var7] = this.phasesRadV[var3[var7]];
            }

            var8.add(Conv.copy(var9));
        } else {
            for(var7 = 0; var7 < 8; ++var7) {
                var8.add((Object)null);
            }
        }

        return var8;
    }

    public Vector<Object> getSimulationResultsAsVector(int var1) {
        if (!this.impedancesSet) {
            this.calculateImpedances();
        }

        if (var1 > this.numberOfFrequencies) {
            var1 = this.numberOfFrequencies;
        }

        int var2 = (int)Math.round((double)this.numberOfFrequencies / (double)var1);
        int[] var3 = new int[var1];
        var3[0] = 0;

        for(int var4 = 1; var4 < var1; ++var4) {
            var3[var4] = var3[var4 - 1] + var2;
        }

        if (var3[var1 - 1] != this.numberOfFrequencies - 1) {
            var3[var1 - 1] = this.numberOfFrequencies - 1;
        }

        Vector var8 = new Vector();
        Complex[] var5 = Complex.oneDarray(var1);

        for(int var6 = 0; var6 < var1; ++var6) {
            var5[var6] = this.impedances[var3[var6]];
        }

        var8.addElement(Complex.copy(var5));
        double[] var9 = new double[var1];

        int var7;
        for(var7 = 0; var7 < var1; ++var7) {
            var9[var7] = this.realZ[var3[var7]];
        }

        var8.addElement(Conv.copy(var9));

        for(var7 = 0; var7 < var1; ++var7) {
            var9[var7] = this.imagZ[var3[var7]];
        }

        var8.addElement(Conv.copy(var9));

        for(var7 = 0; var7 < var1; ++var7) {
            var9[var7] = this.magnitudesZ[var3[var7]];
        }

        var8.addElement(Conv.copy(var9));

        for(var7 = 0; var7 < var1; ++var7) {
            var9[var7] = this.phasesDegZ[var3[var7]];
        }

        var8.addElement(Conv.copy(var9));

        for(var7 = 0; var7 < var1; ++var7) {
            var9[var7] = this.phasesRadZ[var3[var7]];
        }

        var8.addElement(Conv.copy(var9));

        for(var7 = 0; var7 < var1; ++var7) {
            var9[var7] = this.frequencies[var3[var7]];
        }

        var8.addElement(Conv.copy(var9));

        for(var7 = 0; var7 < var1; ++var7) {
            var9[var7] = this.log10frequencies[var3[var7]];
        }

        var8.addElement(Conv.copy(var9));

        for(var7 = 0; var7 < var1; ++var7) {
            var9[var7] = this.omegas[var3[var7]];
        }

        var8.addElement(Conv.copy(var9));
        if (this.voltageSet && this.referenceSet) {
            var8.addElement(new Double(this.appliedVoltage.getReal()));
            var8.addElement(this.referenceImpedance);

            for(var7 = 0; var7 < var1; ++var7) {
                var5[var7] = this.voltages[var3[var7]];
            }

            var8.addElement(Complex.copy(var5));

            for(var7 = 0; var7 < var1; ++var7) {
                var9[var7] = this.realV[var3[var7]];
            }

            var8.addElement(Conv.copy(var9));

            for(var7 = 0; var7 < var1; ++var7) {
                var9[var7] = this.imagV[var3[var7]];
            }

            var8.addElement(Conv.copy(var9));

            for(var7 = 0; var7 < var1; ++var7) {
                var9[var7] = this.magnitudesV[var3[var7]];
            }

            var8.addElement(Conv.copy(var9));

            for(var7 = 0; var7 < var1; ++var7) {
                var9[var7] = this.phasesDegV[var3[var7]];
            }

            var8.addElement(Conv.copy(var9));

            for(var7 = 0; var7 < var1; ++var7) {
                var9[var7] = this.phasesRadV[var3[var7]];
            }

            var8.addElement(Conv.copy(var9));
        } else {
            for(var7 = 0; var7 < 8; ++var7) {
                var8.addElement((Object)null);
            }
        }

        return var8;
    }

    public Vector<Object> getSimulationResults(int var1) {
        return this.getSimulationResultsAsVector(var1);
    }

    public void plotImpedanceMagnitudes() {
        this.plotImpedanceMagnitudeVersusFrequency();
    }

    public void plotImpedanceMagnitudeVersusFrequency() {
        String[] var1 = this.dateAndTime();
        String var2 = "ImpedSpecSimulation program:  Impedance Magnitude versus Frequency   [" + var1[0] + "    " + var1[1] + "]";
        String var3 = this.simulationTitle;
        if (this.logOrLinear) {
            this.impedanceMagnitudeVersusLogFrequencyPlot(var2, var3);
        } else {
            this.impedanceMagnitudeVersusFrequencyPlot(var2, var3);
        }

    }

    public void plotMagnitudeVersusFrequency() {
        this.plotImpedanceMagnitudeVersusFrequency();
    }

    private void impedanceMagnitudeVersusLogFrequencyPlot(String var1, String var2) {
        if (!this.impedancesSet) {
            this.calculateImpedances();
        }

        double[][] var3 = new double[2][this.numberOfFrequencies];
        var3[0] = this.log10frequencies;
        var3[1] = this.magnitudesZ;
        PlotGraph var4 = new PlotGraph(var3);
        var4.setLine(3);
        var4.setPoint(0);
        var4.setGraphTitle(var1);
        var4.setGraphTitle2(var2);
        var4.setXaxisLegend("Log10[Frequency / Hz]");
        var4.setYaxisLegend("Impedance Magnitude");
        var4.plot();
    }

    private void impedanceMagnitudeVersusFrequencyPlot(String var1, String var2) {
        if (!this.impedancesSet) {
            this.calculateImpedances();
        }

        double[][] var3 = new double[2][this.numberOfFrequencies];
        var3[0] = this.frequencies;
        var3[1] = this.magnitudesZ;
        PlotGraph var4 = new PlotGraph(var3);
        var4.setLine(3);
        var4.setPoint(0);
        var4.setGraphTitle(var1);
        var4.setGraphTitle2(var2);
        var4.setXaxisLegend("Frequency");
        var4.setXaxisUnitsName("Hz");
        var4.setYaxisLegend("Impedance Magnitude");
        var4.plot();
    }

    public void plotImpedancePhases() {
        this.plotImpedancePhaseVersusFrequency();
    }

    public void plotImpedancePhaseVersusFrequency() {
        String[] var1 = this.dateAndTime();
        String var2 = "ImpedSpecSimulation program:  Impedance Phase versus Frequency   [" + var1[0] + "    " + var1[1] + "]";
        String var3 = this.simulationTitle;
        if (this.logOrLinear) {
            this.impedancePhaseVersusLogFrequencyPlot(var2, var3);
        } else {
            this.impedancePhaseVersusFrequencyPlot(var2, var3);
        }

    }

    public void plotPhaseVersusFrequency() {
        this.plotImpedancePhaseVersusFrequency();
    }

    private void impedancePhaseVersusLogFrequencyPlot(String var1, String var2) {
        if (!this.impedancesSet) {
            this.calculateImpedances();
        }

        double[][] var3 = new double[2][this.numberOfFrequencies];
        var3[0] = this.log10frequencies;
        var3[1] = this.phasesDegZ;
        PlotGraph var4 = new PlotGraph(var3);
        var4.setLine(3);
        var4.setPoint(0);
        var4.setGraphTitle(var1);
        var4.setGraphTitle2(var2);
        var4.setXaxisLegend("Log10[Frequency / Hz]");
        var4.setYaxisLegend("Impedance Phase");
        var4.setYaxisUnitsName("degrees");
        var4.plot();
    }

    private void impedancePhaseVersusFrequencyPlot(String var1, String var2) {
        if (!this.impedancesSet) {
            this.calculateImpedances();
        }

        double[][] var3 = new double[2][this.numberOfFrequencies];
        var3[0] = this.frequencies;
        var3[1] = this.phasesDegZ;
        PlotGraph var4 = new PlotGraph(var3);
        var4.setLine(3);
        var4.setPoint(0);
        var4.setGraphTitle(var1);
        var4.setGraphTitle2(var2);
        var4.setXaxisLegend("Frequency");
        var4.setXaxisUnitsName("Hz");
        var4.setYaxisLegend("Impedance Phase");
        var4.setYaxisUnitsName("degrees");
        var4.plot();
    }

    public void plotColeCole() {
        String[] var1 = this.dateAndTime();
        String var2 = "ImpedSpecSimulation program:  Cole - Cole plot   [" + var1[0] + "    " + var1[1] + "]";
        String var3 = this.simulationTitle;
        this.coleColePlot(var2, var3);
    }

    private void coleColePlot(String var1, String var2) {
        if (!this.impedancesSet) {
            this.calculateImpedances();
        }

        double[][] var3 = new double[2][this.numberOfFrequencies];

        for(int var4 = 0; var4 < this.numberOfFrequencies; ++var4) {
            var3[0][var4] = this.realZ[this.numberOfFrequencies - var4 - 1];
            var3[1][var4] = -this.imagZ[this.numberOfFrequencies - var4 - 1];
        }

        PlotGraph var5 = new PlotGraph(var3);
        var5.setLine(3);
        var5.setPoint(0);
        var5.setGraphTitle(var1);
        var5.setGraphTitle2(var2);
        var5.setXaxisLegend("Real[Impedance / ohms]");
        var5.setYaxisLegend("-Imag[Impedance / ohms]");
        var5.plot();
    }

    public void plotVoltageMagnitudes() {
        this.plotVoltageMagnitudeVersusFrequency();
    }

    public void plotVoltageMagnitudeVersusFrequency() {
        if (this.voltageSet && this.referenceSet) {
            String[] var1 = this.dateAndTime();
            String var2 = "ImpedSpecSimulation program:  Voltage Magnitude versus Frequency   [" + var1[0] + "    " + var1[1] + "]";
            String var3 = this.simulationTitle;
            if (this.logOrLinear) {
                this.voltageMagnitudeVersusLogFrequencyPlot(var2, var3);
            } else {
                this.voltageMagnitudeVersusFrequencyPlot(var2, var3);
            }
        } else {
            System.out.println("A Voltage phase plot cannot be displayed, either no applied");
            System.out.println("voltage and/or reference impedance has been entered");
        }

    }

    private void voltageMagnitudeVersusLogFrequencyPlot(String var1, String var2) {
        if (!this.impedancesSet) {
            this.calculateImpedances();
        }

        double[][] var3 = new double[2][this.numberOfFrequencies];
        var3[0] = this.log10frequencies;
        var3[1] = this.magnitudesV;
        PlotGraph var4 = new PlotGraph(var3);
        var4.setLine(3);
        var4.setPoint(0);
        var4.setGraphTitle(var1);
        var4.setGraphTitle2(var2);
        var4.setXaxisLegend("Log10[Frequency / Hz]");
        var4.setYaxisLegend("Voltage Magnitude");
        var4.plot();
    }

    private void voltageMagnitudeVersusFrequencyPlot(String var1, String var2) {
        if (!this.impedancesSet) {
            this.calculateImpedances();
        }

        double[][] var3 = new double[2][this.numberOfFrequencies];
        var3[0] = this.frequencies;
        var3[1] = this.magnitudesV;
        PlotGraph var4 = new PlotGraph(var3);
        var4.setLine(3);
        var4.setPoint(0);
        var4.setGraphTitle(var1);
        var4.setGraphTitle2(var2);
        var4.setXaxisLegend("Frequency");
        var4.setXaxisUnitsName("Hz");
        var4.setYaxisLegend("Voltage Magnitude");
        var4.plot();
    }

    public void plotVoltagePhases() {
        this.plotVoltagePhaseVersusFrequency();
    }

    public void plotVoltagePhaseVersusFrequency() {
        if (this.voltageSet && this.referenceSet) {
            String[] var1 = this.dateAndTime();
            String var2 = "ImpedSpecSimulation program:  Voltage Phase versus Frequency   [" + var1[0] + "    " + var1[1] + "]";
            String var3 = this.simulationTitle;
            if (this.logOrLinear) {
                this.voltagePhaseVersusLogFrequencyPlot(var2, var3);
            } else {
                this.voltagePhaseVersusFrequencyPlot(var2, var3);
            }
        } else {
            System.out.println("A Voltage phase plot cannot be displayed, either no applied");
            System.out.println("voltage and/or reference impedance has been entered");
        }

    }

    private void voltagePhaseVersusLogFrequencyPlot(String var1, String var2) {
        if (!this.impedancesSet) {
            this.calculateImpedances();
        }

        double[][] var3 = new double[2][this.numberOfFrequencies];
        var3[0] = this.log10frequencies;
        var3[1] = this.phasesDegV;
        PlotGraph var4 = new PlotGraph(var3);
        var4.setLine(3);
        var4.setPoint(0);
        var4.setGraphTitle(var1);
        var4.setGraphTitle2(var2);
        var4.setXaxisLegend("Log10[Frequency / Hz]");
        var4.setYaxisLegend("Voltage Phase");
        var4.setYaxisUnitsName("degrees");
        var4.plot();
    }

    private void voltagePhaseVersusFrequencyPlot(String var1, String var2) {
        if (!this.impedancesSet) {
            this.calculateImpedances();
        }

        double[][] var3 = new double[2][this.numberOfFrequencies];
        var3[0] = this.frequencies;
        var3[1] = this.phasesDegV;
        PlotGraph var4 = new PlotGraph(var3);
        var4.setLine(3);
        var4.setPoint(0);
        var4.setGraphTitle(var1);
        var4.setGraphTitle2(var2);
        var4.setXaxisLegend("Frequency");
        var4.setXaxisUnitsName("Hz");
        var4.setYaxisLegend("Voltage Phase");
        var4.setYaxisUnitsName("degrees");
        var4.plot();
    }

    public String[] dateAndTime() {
        Date var1 = new Date();
        String[] var2 = new String[]{DateFormat.getDateInstance().format(var1), DateFormat.getTimeInstance().format(var1)};
        return var2;
    }

    public void printToTextFile(int var1) {
        String var2 = "ImpedSpecSimulationOutput.txt";
        this.fileType = true;
        this.printToTextFile(var2, var1);
    }

    public void print(int var1) {
        String var2 = "ImpedSpecSimulationOutput.txt";
        this.fileType = true;
        this.printToTextFile(var2, var1);
    }

    public void print(String var1, int var2) {
        this.printToTextFile(var1, var2);
    }

    public void printToTextFile(String var1, int var2) {
        if (!this.impedancesSet) {
            this.calculateImpedances();
        }

        byte var3 = 10;
        byte var4 = 4;
        var1 = var1.trim();
        int var5 = var1.indexOf(46);
        if (var5 == -1) {
            var1 = var1 + ".txt";
        }

        if (var2 > this.numberOfFrequencies) {
            var2 = this.numberOfFrequencies;
        }

        int var6 = (int)Math.round((double)this.numberOfFrequencies / (double)var2);
        int[] var7 = new int[var2];
        var7[0] = 0;

        for(int var8 = 1; var8 < var2; ++var8) {
            var7[var8] = var7[var8 - 1] + var6;
        }

        if (var7[var2 - 1] != this.numberOfFrequencies - 1) {
            var7[var2 - 1] = this.numberOfFrequencies - 1;
        }

        FileOutput var10 = null;
        if (this.fileType) {
            var10 = new FileOutput(var1, 'n');
        } else {
            var10 = new FileOutput(var1);
        }

        var10.println("ImpedSpecSimulation Program Output File:  " + this.simulationTitle);
        var10.dateAndTimeln(var1);
        var10.println();
        if (this.modelSet) {
            var10.println("Circuit - model number " + this.modelNumber);
        } else {
            var10.println("Circuit supplied by the user");
        }

        var10.println();
        var10.println("Circuit Parameters");
        var10.printtab("Parameters");
        var10.println("Value (SI unit)");

        int var9;
        for(var9 = 0; var9 < this.numberOfParameters; ++var9) {
            var10.printtab(this.modelParameterSymbols[var9], var3);
            var10.println(this.parameters[var9]);
        }

        var10.println();
        var3 = 14;
        var10.println("Frequecy - Impedance fillData");
        var10.print("Frequency", var3);
        var10.print("Magnitude", var3);
        var10.print("Phase", var3);
        var10.print("Phase", var3);
        var10.print("Real[Z]", var3);
        var10.print("Imag[Z]", var3);
        var10.print("Log10(freq)", var3);
        var10.println("Radial frequency");
        var10.print("/Hz [freq]", var3);
        var10.print("  ", var3);
        var10.print("/degrees", var3);
        var10.print("/radians", var3);
        var10.print("/ohms", var3);
        var10.print("/ohms", var3);
        var10.print("  ", var3);
        var10.println("/radians");

        for(var9 = 0; var9 < var2; ++var9) {
            var10.print(Fmath.truncate(this.frequencies[var7[var9]], var4), var3);
            var10.print(Fmath.truncate(this.magnitudesZ[var7[var9]], var4), var3);
            var10.print(Fmath.truncate(this.phasesDegZ[var7[var9]], var4), var3);
            var10.print(Fmath.truncate(this.phasesRadZ[var7[var9]], var4), var3);
            var10.print(Fmath.truncate(this.realZ[var7[var9]], var4), var3);
            var10.print(Fmath.truncate(this.imagZ[var7[var9]], var4), var3);
            var10.print(Fmath.truncate(this.log10frequencies[var7[var9]], var4), var3);
            var10.println(Fmath.truncate(this.omegas[var7[var9]], var4));
        }

        var10.println();
        if (this.voltageSet && this.referenceSet) {
            var10.println("Aplied voltage: " + this.appliedVoltage.getReal() + " volts");
            var10.println();
            var10.println("Reference impedance: " + this.referenceImpedance + " ohms");
            var10.println();
            var3 = 14;
            var10.println("Frequecy - Voltage fillData");
            var10.print("Frequency", var3);
            var10.print("Magnitude", var3);
            var10.print("Phase", var3);
            var10.print("Phase", var3);
            var10.print("Real[V]", var3);
            var10.print("Imag[V]", var3);
            var10.print("Log10(freq)", var3);
            var10.println("Radial frequency");
            var10.print("/Hz [freq]", var3);
            var10.print("  ", var3);
            var10.print("/degrees", var3);
            var10.print("/radians", var3);
            var10.print("/volts", var3);
            var10.print("/volts", var3);
            var10.print("  ", var3);
            var10.println("/radians");

            for(var9 = 0; var9 < var2; ++var9) {
                var10.print(Fmath.truncate(this.frequencies[var7[var9]], var4), var3);
                var10.print(Fmath.truncate(this.magnitudesV[var7[var9]], var4), var3);
                var10.print(Fmath.truncate(this.phasesDegV[var7[var9]], var4), var3);
                var10.print(Fmath.truncate(this.phasesRadV[var7[var9]], var4), var3);
                var10.print(Fmath.truncate(this.realV[var7[var9]], var4), var3);
                var10.print(Fmath.truncate(this.imagV[var7[var9]], var4), var3);
                var10.print(Fmath.truncate(this.log10frequencies[var7[var9]], var4), var3);
                var10.println(Fmath.truncate(this.omegas[var7[var9]], var4));
            }
        }

        var10.close();
    }

    public void printToExcelFile(int var1) {
        String var2 = "ImpedSpecSimulationOutput.xls";
        this.fileType = true;
        this.printToExcelFile(var2, var1);
    }

    public void printForExcel(int var1) {
        String var2 = "ImpedSpecSimulationOutput.xls";
        this.fileType = true;
        this.printToExcelFile(var2, var1);
    }

    public void printForExcel(String var1, int var2) {
        this.printToExcelFile(var1, var2);
    }

    public void printToExcelFile(String var1, int var2) {
        if (!this.impedancesSet) {
            this.calculateImpedances();
        }

        byte var3 = 10;
        byte var4 = 4;
        var1 = var1.trim();
        int var5 = var1.indexOf(46);
        if (var5 == -1) {
            var1 = var1 + ".xls";
        } else {
            var1 = var1.substring(0, var5) + ".xls";
        }

        if (var2 > this.numberOfFrequencies) {
            var2 = this.numberOfFrequencies;
        }

        int var6 = (int)Math.round((double)this.numberOfFrequencies / (double)var2);
        int[] var7 = new int[var2];
        var7[0] = 0;

        for(int var8 = 1; var8 < var2; ++var8) {
            var7[var8] = var7[var8 - 1] + var6;
        }

        if (var7[var2 - 1] != this.numberOfFrequencies - 1) {
            var7[var2 - 1] = this.numberOfFrequencies - 1;
        }

        FileOutput var10 = null;
        if (this.fileType) {
            var10 = new FileOutput(var1, 'n');
        } else {
            var10 = new FileOutput(var1);
        }

        var10.println("ImpedSpecSimulation Program Output File:  " + this.simulationTitle);
        var10.dateAndTimeln(var1);
        var10.println();
        if (this.modelSet) {
            var10.println("Circuit - model number " + this.modelNumber);
        } else {
            var10.println("Circuit supplied by the user");
        }

        var10.println();
        var10.println("Circuit Parameters");
        var10.printtab("Parameters");
        var10.println("Value (SI unit)");

        int var9;
        for(var9 = 0; var9 < this.numberOfParameters; ++var9) {
            var10.printtab(this.modelParameterSymbols[var9], var3);
            var10.println(this.parameters[var9]);
        }

        var10.println();
        var3 = 10;
        var10.println("Frequecy - Impedance fillData");
        var10.printtab("Frequency", var3);
        var10.printtab("Magnitude", var3);
        var10.printtab("Phase", var3);
        var10.printtab("Phase", var3);
        var10.printtab("Real[Z]", var3);
        var10.printtab("Imag[Z]", var3);
        var10.printtab("Log10(freq)", var3);
        var10.println("Radial frequency");
        var10.printtab("/Hz [freq]", var3);
        var10.printtab("  ", var3);
        var10.printtab("/degrees", var3);
        var10.printtab("/radians", var3);
        var10.printtab("/ohms", var3);
        var10.printtab("/ohms", var3);
        var10.printtab("  ", var3);
        var10.println("/radians");

        for(var9 = 0; var9 < var2; ++var9) {
            var10.printtab(Fmath.truncate(this.frequencies[var7[var9]], var4), var3);
            var10.printtab(Fmath.truncate(this.magnitudesZ[var7[var9]], var4), var3);
            var10.printtab(Fmath.truncate(this.phasesDegZ[var7[var9]], var4), var3);
            var10.printtab(Fmath.truncate(this.phasesRadZ[var7[var9]], var4), var3);
            var10.printtab(Fmath.truncate(this.realZ[var7[var9]], var4), var3);
            var10.printtab(Fmath.truncate(this.imagZ[var7[var9]], var4), var3);
            var10.printtab(Fmath.truncate(this.log10frequencies[var7[var9]], var4), var3);
            var10.println(Fmath.truncate(this.omegas[var7[var9]], var4));
        }

        var10.println();
        if (this.voltageSet && this.referenceSet) {
            var10.println("Aplied voltage: " + this.appliedVoltage.getReal() + " volts");
            var10.println();
            var10.println("Reference impedance: " + this.referenceImpedance + " ohms");
            var10.println();
            var3 = 14;
            var10.println("Frequecy - Voltage fillData");
            var10.printtab("Frequency", var3);
            var10.printtab("Magnitude", var3);
            var10.printtab("Phase", var3);
            var10.printtab("Phase", var3);
            var10.printtab("Real[V]", var3);
            var10.printtab("Imag[V]", var3);
            var10.printtab("Log10(freq)", var3);
            var10.println("Radial frequency");
            var10.printtab("/Hz [freq]", var3);
            var10.printtab("  ", var3);
            var10.printtab("/degrees", var3);
            var10.printtab("/radians", var3);
            var10.printtab("/volts", var3);
            var10.printtab("/volts", var3);
            var10.printtab("  ", var3);
            var10.println("/radians");

            for(var9 = 0; var9 < var2; ++var9) {
                var10.printtab(Fmath.truncate(this.frequencies[var7[var9]], var4), var3);
                var10.printtab(Fmath.truncate(this.magnitudesV[var7[var9]], var4), var3);
                var10.printtab(Fmath.truncate(this.phasesDegV[var7[var9]], var4), var3);
                var10.printtab(Fmath.truncate(this.phasesRadV[var7[var9]], var4), var3);
                var10.printtab(Fmath.truncate(this.realV[var7[var9]], var4), var3);
                var10.printtab(Fmath.truncate(this.imagV[var7[var9]], var4), var3);
                var10.printtab(Fmath.truncate(this.log10frequencies[var7[var9]], var4), var3);
                var10.println(Fmath.truncate(this.omegas[var7[var9]], var4));
            }
        }

        var10.close();
    }
}
