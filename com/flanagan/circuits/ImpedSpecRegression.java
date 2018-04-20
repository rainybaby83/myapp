//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.circuits;

import com.flanagan.analysis.ErrorProp;
import com.flanagan.analysis.Regression;
import com.flanagan.analysis.RegressionFunction2;
import com.flanagan.analysis.Stat;
import com.flanagan.complex.Complex;
import com.flanagan.complex.ComplexErrorProp;
import com.flanagan.io.FileOutput;
import com.flanagan.math.Conv;
import com.flanagan.math.Fmath;
import com.flanagan.plot.PlotGraph;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

public class ImpedSpecRegression extends Regression {
    private String regressionTitle = null;
    private boolean fileType = false;
    private Complex appliedVoltage = null;
    private boolean appliedVoltageSet = false;
    private Complex appliedVoltageError = null;
    private boolean voltageErrorSet = false;
    private Complex referenceImpedance = null;
    private boolean referenceSet = false;
    private double[] frequencies = null;
    private double[] omegas = null;
    private double[] log10frequencies = null;
    private double[] log10omegas = null;
    private int numberOfFrequencies = 0;
    private boolean frequenciesSet = false;
    private Complex[] voltages = null;
    private Complex[] voltageWeights = null;
    private double[] voltageMagnitudes = null;
    private double[] voltageMagnitudeWeights = null;
    private double[] voltagePhasesRad = null;
    private double[] voltagePhaseWeightsRad = null;
    private double[] voltagePhasesDeg = null;
    private double[] voltagePhaseWeightsDeg = null;
    private double[] realV = null;
    private double[] realVweights = null;
    private double[] imagV = null;
    private double[] imagVweights = null;
    private boolean weightsSet = true;
    private int dataEnteredTypePointer = -1;
    private String[] dataEnteredType = new String[]{"Complex voltage (as real and imaginary parts)", "Complex voltage (as Complex)", "Voltage Magnitude and phase (in radians)", "Voltage Magnitude and phase (in degrees)", "Complex impedance (as real and imaginary parts)", "Complex impedance (as Complex)", "Magnitude and phase (in radians)", "Magnitude and phase (in degrees)"};
    private boolean voltageOrImpedance = true;
    private Complex[] impedances = null;
    private Complex[] impedanceWeights = null;
    private double[] impedanceMagnitudes = null;
    private double[] impedanceMagnitudeWeights = null;
    private double[] impedancePhasesRad = null;
    private double[] impedancePhaseWeightsRad = null;
    private double[] impedancePhasesDeg = null;
    private double[] impedancePhaseWeightsDeg = null;
    private double[] realZ = null;
    private double[] realZweights = null;
    private double[] imagZ = null;
    private double[] imagZweights = null;
    private boolean impedancesSet = false;
    private double[] xRegression = null;
    private double[][] yRegression = (double[][])null;
    private double[][] wRegression = (double[][])null;
    private int modelNumber = 0;
    private int numberOfParameters = 0;
    private String[] parameterSymbols = null;
    private boolean modelSet = false;
    private boolean estimatesNeeded = false;
    private boolean supressDefaultConstraints = false;
    private boolean supressAddedConstraints = false;
    private boolean supressAllConstraints = false;
    private ArrayList<Object> constraints = null;
    private int numberOfAddedConstraints = -1;
    private boolean constraintsAdded = false;
    private double[] initialEstimates = null;
    private double[] initialSteps = null;
    private double[] bestEstimates = null;
    private double[] standardDeviations = null;
    private double[] coefficientsOfVariation = null;
    private double[][] correlationCoefficients = (double[][])null;
    private double[] preMinimumGradients = null;
    private double[] postMinimumGradients = null;
    private int degreesOfFreedom = 0;
    private double sumOfSquares = 0.0D;
    private double reducedSumOfSquares = 0.0D;
    private double chiSquare = 0.0D / 0.0;
    private double reducedChiSquare = 0.0D / 0.0;
    private double[] realZresiduals = null;
    private double[] imagZresiduals = null;
    private double[] calculatedRealZ = null;
    private double[] calculatedImagZ = null;
    private Complex[] calculatedImpedances = null;
    private double[] calculatedImpedanceMagnitudes = null;
    private double[] calculatedImpedancePhasesRad = null;
    private double[] calculatedImpedancePhasesDeg = null;
    private double[] calculatedRealV = null;
    private double[] calculatedImagV = null;
    private Complex[] calculatedVoltages = null;
    private double[] calculatedVoltageMagnitudes = null;
    private double[] calculatedVoltagePhasesRad = null;
    private double[] calculatedVoltagePhasesDeg = null;
    ArrayList<Object> results = null;
    private boolean estimatesSet = false;
    private ImpedSpecModel userModel = null;
    private boolean userModelSet = false;
    private RegressionFunction2 regressionFunction = null;
    private double tolerance = 1.0E-9D;
    private int maximumIterations = 10000;
    private int numberOfIterations1 = -1;
    private int numberOfIterations2 = -1;
    private boolean regressionDone = false;
    private int numberOfLineFrequencies = 8000;
    private boolean logOrLinear = true;
    private double[] lineFrequencies = null;
    private double[] log10lineFrequencies = null;

    public ImpedSpecRegression() {
        this.regressionTitle = "  ";
    }

    public ImpedSpecRegression(String var1) {
        this.regressionTitle = var1;
    }

    public void setAppliedVoltage(double var1) {
        this.appliedVoltage = new Complex(var1, 0.0D);
        this.appliedVoltageError = new Complex(0.0D, 0.0D);
        this.appliedVoltageSet = true;
        if (this.referenceSet && this.frequenciesSet) {
            this.calculateExperimentalImpedances();
        }

    }

    public void appliedVoltage(double var1, double var3) {
        this.appliedVoltage = new Complex(var1, 0.0D);
        this.appliedVoltageSet = true;
        this.appliedVoltage = new Complex(var3, 0.0D);
        this.voltageErrorSet = true;
        if (this.referenceSet && this.frequenciesSet) {
            this.calculateExperimentalImpedances();
        }

    }

    public void setReferenceImpedance(double var1) {
        this.referenceImpedance = new Complex(var1, 0.0D);
        this.referenceSet = true;
        if (this.appliedVoltageSet && this.frequenciesSet) {
            this.calculateExperimentalImpedances();
        }

    }

    public void setReferenceImpedance(double var1, double var3) {
        this.referenceImpedance = new Complex(var1, var3);
        this.referenceSet = true;
        if (this.appliedVoltageSet && this.frequenciesSet) {
            this.calculateExperimentalImpedances();
        }

    }

    public void setReferenceImpedance(Complex var1) {
        this.referenceImpedance = var1;
        this.referenceSet = true;
        if (this.appliedVoltageSet && this.frequenciesSet) {
            this.calculateExperimentalImpedances();
        }

    }

    public void voltageDataAsComplex(double[] var1, double[] var2, double[] var3) {
        double[] var4 = new double[var1.length];
        double[] var5 = new double[var1.length];
        this.weightsSet = false;
        this.voltageDataAsComplex(var1, var2, var3, var4, var5);
    }

    public void voltageDataAsComplex(double[] var1, double[] var2, double[] var3, double[] var4, double[] var5) {
        this.numberOfFrequencies = var1.length;
        if (this.numberOfFrequencies != var2.length) {
            throw new IllegalArgumentException("The number of frequencies, " + this.numberOfFrequencies + ", does not equal the number of Real[voltages], " + var2.length);
        } else if (this.numberOfFrequencies != var3.length) {
            throw new IllegalArgumentException("The number of frequencies, " + this.numberOfFrequencies + ", does not equal the number of Imag[voltages], " + var3.length);
        } else if (this.numberOfFrequencies != var4.length) {
            throw new IllegalArgumentException("The number of frequencies, " + this.numberOfFrequencies + ", does not equal the number of real weights, " + var4.length);
        } else if (this.numberOfFrequencies != var5.length) {
            throw new IllegalArgumentException("The number of frequencies, " + this.numberOfFrequencies + ", does not equal the number of imag weights, " + var5.length);
        } else {
            this.frequencies = Conv.copy(var1);
            this.setAllFrequencyArrays();
            this.setCalculatedArrayLengths();
            this.realV = Conv.copy(var2);
            this.imagV = Conv.copy(var3);
            this.realVweights = Conv.copy(var4);
            this.imagVweights = Conv.copy(var5);
            this.voltageMagnitudes = new double[this.numberOfFrequencies];
            this.voltagePhasesDeg = new double[this.numberOfFrequencies];
            this.voltagePhasesRad = new double[this.numberOfFrequencies];
            this.voltages = Complex.oneDarray(this.numberOfFrequencies);

            for(int var6 = 0; var6 < this.numberOfFrequencies; ++var6) {
                this.voltages[var6] = new Complex(this.realV[var6], this.imagV[var6]);
                this.voltageMagnitudes[var6] = this.voltages[var6].abs();
                this.voltagePhasesRad[var6] = this.voltages[var6].arg();
                this.voltagePhasesDeg[var6] = Math.toDegrees(this.voltagePhasesRad[var6]);
            }

            this.frequenciesSet = true;
            this.setImpedanceArrayLengths();
            this.calculateExperimentalImpedances();
            this.dataEnteredTypePointer = 4;
            this.voltageOrImpedance = true;
            if (this.estimatesNeeded) {
                this.setInitialEstimates();
            }

        }
    }

    public void voltageDataAsComplex(double[] var1, Complex[] var2) {
        Complex[] var3 = Complex.oneDarray(var2.length, 0.0D, 0.0D);
        this.weightsSet = false;
        this.voltageDataAsComplex(var1, var2, var3);
    }

    public void voltageDataAsComplex(double[] var1, Complex[] var2, Complex[] var3) {
        this.numberOfFrequencies = var1.length;
        if (this.numberOfFrequencies != var2.length) {
            throw new IllegalArgumentException("The number of frequencies, " + this.numberOfFrequencies + ", does not equal the number of voltages, " + var2.length);
        } else if (this.numberOfFrequencies != var3.length) {
            throw new IllegalArgumentException("The number of frequencies, " + this.numberOfFrequencies + ", does not equal the number of weights, " + var3.length);
        } else {
            this.frequencies = Conv.copy(var1);
            this.setAllFrequencyArrays();
            this.setCalculatedArrayLengths();
            this.voltages = Complex.copy(var2);
            this.voltageWeights = Complex.copy(var3);
            this.voltageMagnitudes = new double[this.numberOfFrequencies];
            this.voltagePhasesDeg = new double[this.numberOfFrequencies];
            this.voltagePhasesRad = new double[this.numberOfFrequencies];
            this.realV = new double[this.numberOfFrequencies];
            this.imagV = new double[this.numberOfFrequencies];
            this.realVweights = new double[this.numberOfFrequencies];
            this.imagVweights = new double[this.numberOfFrequencies];

            for(int var4 = 0; var4 < this.numberOfFrequencies; ++var4) {
                this.realV[var4] = this.voltages[var4].getReal();
                this.imagV[var4] = this.voltages[var4].getImag();
                this.realVweights[var4] = var3[var4].getReal();
                this.imagVweights[var4] = var3[var4].getImag();
                this.voltageMagnitudes[var4] = this.voltages[var4].abs();
                this.voltagePhasesRad[var4] = this.voltages[var4].arg();
                this.voltagePhasesDeg[var4] = Math.toDegrees(this.voltagePhasesRad[var4]);
            }

            this.frequenciesSet = true;
            this.setImpedanceArrayLengths();
            this.calculateExperimentalImpedances();
            this.voltageOrImpedance = true;
            this.dataEnteredTypePointer = 1;
            if (this.estimatesNeeded) {
                this.setInitialEstimates();
            }

        }
    }

    public void voltageDataAsPhasorRad(double[] var1, double[] var2, double[] var3) {
        double[] var4 = new double[var1.length];
        double[] var5 = new double[var1.length];
        this.weightsSet = false;
        this.voltageDataAsPhasorRad(var1, var2, var3, var4, var5);
    }

    public void voltageDataAsPhasorRad(double[] var1, double[] var2, double[] var3, double[] var4, double[] var5) {
        this.numberOfFrequencies = var1.length;
        if (this.numberOfFrequencies != var2.length) {
            throw new IllegalArgumentException("The number of frequencies, " + this.numberOfFrequencies + ", does not equal the number of magnitudes, " + var2.length);
        } else if (this.numberOfFrequencies != var3.length) {
            throw new IllegalArgumentException("The number of frequencies, " + this.numberOfFrequencies + ", does not equal the number of phases, " + var3.length);
        } else if (this.numberOfFrequencies != var4.length) {
            throw new IllegalArgumentException("The number of frequencies, " + this.numberOfFrequencies + ", does not equal the number of magnitude weights, " + var4.length);
        } else if (this.numberOfFrequencies != var5.length) {
            throw new IllegalArgumentException("The number of frequencies, " + this.numberOfFrequencies + ", does not equal the number of phase weights, " + var5.length);
        } else {
            this.frequencies = Conv.copy(var1);
            this.setAllFrequencyArrays();
            this.setCalculatedArrayLengths();
            this.voltageMagnitudes = Conv.copy(var2);
            this.voltageMagnitudeWeights = Conv.copy(var4);
            this.voltagePhaseWeightsRad = Conv.copy(var5);
            this.voltages = Complex.oneDarray(this.numberOfFrequencies);
            this.voltagePhasesDeg = new double[this.numberOfFrequencies];
            this.realV = new double[this.numberOfFrequencies];
            this.imagV = new double[this.numberOfFrequencies];
            this.realVweights = new double[this.numberOfFrequencies];
            this.imagVweights = new double[this.numberOfFrequencies];

            for(int var6 = 0; var6 < this.numberOfFrequencies; ++var6) {
                this.voltagePhasesDeg[var6] = Math.toDegrees(this.voltagePhasesRad[var6]);
                this.voltages[var6].polar(var2[var6], var3[var6]);
                this.realV[var6] = this.voltages[var6].getReal();
                this.imagV[var6] = this.voltages[var6].getImag();
                ErrorProp var7 = new ErrorProp(var2[var6], this.voltageMagnitudeWeights[var6]);
                ErrorProp var8 = new ErrorProp(var3[var6], var5[var6]);
                ComplexErrorProp var9 = new ComplexErrorProp();
                var9.polar(var7, var8);
                this.realVweights[var6] = var9.getRealError();
                this.imagVweights[var6] = var9.getImagError();
            }

            this.frequenciesSet = true;
            this.setImpedanceArrayLengths();
            this.calculateExperimentalImpedances();
            this.voltageOrImpedance = true;
            this.dataEnteredTypePointer = 2;
            if (this.estimatesNeeded) {
                this.setInitialEstimates();
            }

        }
    }

    public void voltageDataAsPhasorDeg(double[] var1, double[] var2, double[] var3) {
        double[] var4 = new double[var1.length];
        double[] var5 = new double[var1.length];
        this.weightsSet = false;
        this.voltageDataAsPhasorDeg(var1, var2, var3, var4, var5);
    }

    public void voltageDataAsPhasorDeg(double[] var1, double[] var2, double[] var3, double[] var4, double[] var5) {
        this.numberOfFrequencies = var1.length;
        if (this.numberOfFrequencies != var2.length) {
            throw new IllegalArgumentException("The number of frequencies, " + this.numberOfFrequencies + ", does not equal the number of magnitudes, " + var2.length);
        } else if (this.numberOfFrequencies != var3.length) {
            throw new IllegalArgumentException("The number of frequencies, " + this.numberOfFrequencies + ", does not equal the number of phases, " + var3.length);
        } else if (this.numberOfFrequencies != var4.length) {
            throw new IllegalArgumentException("The number of frequencies, " + this.numberOfFrequencies + ", does not equal the number of magnitude weights, " + var4.length);
        } else if (this.numberOfFrequencies != var5.length) {
            throw new IllegalArgumentException("The number of frequencies, " + this.numberOfFrequencies + ", does not equal the number of phase weights, " + var5.length);
        } else {
            this.frequencies = Conv.copy(var1);
            this.setAllFrequencyArrays();
            this.setCalculatedArrayLengths();
            this.voltageMagnitudes = Conv.copy(var2);
            this.voltagePhasesDeg = Conv.copy(var3);
            this.voltages = Complex.oneDarray(this.numberOfFrequencies);
            this.voltagePhasesRad = new double[this.numberOfFrequencies];
            this.voltagePhaseWeightsRad = new double[this.numberOfFrequencies];
            this.voltageMagnitudeWeights = Conv.copy(var4);
            this.voltagePhaseWeightsDeg = Conv.copy(var5);
            this.realV = new double[this.numberOfFrequencies];
            this.imagV = new double[this.numberOfFrequencies];
            this.realVweights = new double[this.numberOfFrequencies];
            this.imagVweights = new double[this.numberOfFrequencies];

            for(int var6 = 0; var6 < this.numberOfFrequencies; ++var6) {
                this.voltagePhasesRad[var6] = Math.toRadians(this.voltagePhasesDeg[var6]);
                this.voltagePhaseWeightsRad[var6] = Math.toRadians(var5[var6]);
                this.voltages[var6].polar(var2[var6], this.voltagePhasesRad[var6]);
                this.realV[var6] = this.voltages[var6].getReal();
                this.imagV[var6] = this.voltages[var6].getImag();
                ErrorProp var7 = new ErrorProp(var2[var6], this.voltageMagnitudeWeights[var6]);
                ErrorProp var8 = new ErrorProp(this.voltagePhasesRad[var6], this.voltagePhaseWeightsRad[var6]);
                ComplexErrorProp var9 = new ComplexErrorProp();
                var9.polar(var7, var8);
                this.realVweights[var6] = var9.getRealError();
                this.imagVweights[var6] = var9.getImagError();
            }

            this.frequenciesSet = true;
            this.setImpedanceArrayLengths();
            this.calculateExperimentalImpedances();
            this.voltageOrImpedance = true;
            this.dataEnteredTypePointer = 3;
            if (this.estimatesNeeded) {
                this.setInitialEstimates();
            }

        }
    }

    public void impedanceDataAsComplex(double[] var1, double[] var2, double[] var3) {
        double[] var4 = new double[var1.length];
        double[] var5 = new double[var1.length];
        this.weightsSet = false;
        this.impedanceDataAsComplex(var1, var2, var3, var4, var5);
    }

    public void impedanceDataAsComplex(double[] var1, double[] var2, double[] var3, double[] var4, double[] var5) {
        this.numberOfFrequencies = var1.length;
        if (this.numberOfFrequencies != var2.length) {
            throw new IllegalArgumentException("The number of frequencies, " + this.numberOfFrequencies + ", does not equal the number of Real[impedances], " + var2.length);
        } else if (this.numberOfFrequencies != var3.length) {
            throw new IllegalArgumentException("The number of frequencies, " + this.numberOfFrequencies + ", does not equal the number of Imag[impedances], " + var3.length);
        } else if (this.numberOfFrequencies != var4.length) {
            throw new IllegalArgumentException("The number of frequencies, " + this.numberOfFrequencies + ", does not equal the number of real weights, " + var4.length);
        } else if (this.numberOfFrequencies != var5.length) {
            throw new IllegalArgumentException("The number of frequencies, " + this.numberOfFrequencies + ", does not equal the number of imag weights, " + var5.length);
        } else {
            this.frequencies = Conv.copy(var1);
            this.setAllFrequencyArrays();
            this.setCalculatedArrayLengths();
            this.realZ = Conv.copy(var2);
            this.imagZ = Conv.copy(var3);
            this.realZweights = Conv.copy(var4);
            this.imagZweights = Conv.copy(var5);
            this.impedanceMagnitudes = new double[this.numberOfFrequencies];
            this.impedancePhasesDeg = new double[this.numberOfFrequencies];
            this.impedancePhasesRad = new double[this.numberOfFrequencies];
            this.impedances = Complex.oneDarray(this.numberOfFrequencies);

            for(int var6 = 0; var6 < this.numberOfFrequencies; ++var6) {
                this.impedances[var6] = new Complex(this.realZ[var6], this.imagZ[var6]);
                this.impedanceMagnitudes[var6] = this.impedances[var6].abs();
                this.impedancePhasesRad[var6] = this.impedances[var6].arg();
                this.impedancePhasesDeg[var6] = Math.toDegrees(this.impedancePhasesRad[var6]);
            }

            this.frequenciesSet = true;
            this.impedancesSet = true;
            this.dataEnteredTypePointer = 4;
            this.voltageOrImpedance = false;
            if (this.estimatesNeeded) {
                this.setInitialEstimates();
            }

        }
    }

    public void impedanceDataAsComplex(double[] var1, Complex[] var2) {
        Complex[] var3 = Complex.oneDarray(var2.length, 0.0D, 0.0D);
        this.weightsSet = false;
        this.impedanceDataAsComplex(var1, var2, var3);
    }

    public void impedanceDataAsComplex(double[] var1, Complex[] var2, Complex[] var3) {
        this.numberOfFrequencies = var1.length;
        if (this.numberOfFrequencies != var2.length) {
            throw new IllegalArgumentException("The number of frequencies, " + this.numberOfFrequencies + ", does not equal the number of impedances, " + var2.length);
        } else if (this.numberOfFrequencies != var3.length) {
            throw new IllegalArgumentException("The number of frequencies, " + this.numberOfFrequencies + ", does not equal the number of weights, " + var3.length);
        } else {
            this.frequencies = Conv.copy(var1);
            this.setAllFrequencyArrays();
            this.setCalculatedArrayLengths();
            this.impedances = Complex.copy(var2);
            this.impedanceWeights = Complex.copy(var3);
            this.impedanceMagnitudes = new double[this.numberOfFrequencies];
            this.impedancePhasesDeg = new double[this.numberOfFrequencies];
            this.impedancePhasesRad = new double[this.numberOfFrequencies];
            this.realZ = new double[this.numberOfFrequencies];
            this.imagZ = new double[this.numberOfFrequencies];
            this.realZweights = new double[this.numberOfFrequencies];
            this.imagZweights = new double[this.numberOfFrequencies];

            for(int var4 = 0; var4 < this.numberOfFrequencies; ++var4) {
                this.realZ[var4] = this.impedances[var4].getReal();
                this.imagZ[var4] = this.impedances[var4].getImag();
                this.realZweights[var4] = var3[var4].getReal();
                this.imagZweights[var4] = var3[var4].getImag();
                this.impedanceMagnitudes[var4] = this.impedances[var4].abs();
                this.impedancePhasesRad[var4] = this.impedances[var4].arg();
                this.impedancePhasesDeg[var4] = Math.toDegrees(this.impedancePhasesRad[var4]);
            }

            this.frequenciesSet = true;
            this.impedancesSet = true;
            this.voltageOrImpedance = false;
            this.dataEnteredTypePointer = 5;
            if (this.estimatesNeeded) {
                this.setInitialEstimates();
            }

        }
    }

    public void impedanceDataAsPhasorRad(double[] var1, double[] var2, double[] var3) {
        double[] var4 = new double[var1.length];
        double[] var5 = new double[var1.length];
        this.weightsSet = false;
        this.impedanceDataAsPhasorRad(var1, var2, var3, var4, var5);
    }

    public void impedanceDataAsPhasorRad(double[] var1, double[] var2, double[] var3, double[] var4, double[] var5) {
        this.numberOfFrequencies = var1.length;
        if (this.numberOfFrequencies != var2.length) {
            throw new IllegalArgumentException("The number of frequencies, " + this.numberOfFrequencies + ", does not equal the number of magnitudes, " + var2.length);
        } else if (this.numberOfFrequencies != var3.length) {
            throw new IllegalArgumentException("The number of frequencies, " + this.numberOfFrequencies + ", does not equal the number of phases, " + var3.length);
        } else if (this.numberOfFrequencies != var4.length) {
            throw new IllegalArgumentException("The number of frequencies, " + this.numberOfFrequencies + ", does not equal the number of magnitude weights, " + var4.length);
        } else if (this.numberOfFrequencies != var5.length) {
            throw new IllegalArgumentException("The number of frequencies, " + this.numberOfFrequencies + ", does not equal the number of phase weights, " + var5.length);
        } else {
            this.frequencies = Conv.copy(var1);
            this.setAllFrequencyArrays();
            this.setCalculatedArrayLengths();
            this.impedanceMagnitudes = Conv.copy(var2);
            this.impedanceMagnitudeWeights = Conv.copy(var4);
            this.impedancePhaseWeightsRad = Conv.copy(var5);
            this.impedances = Complex.oneDarray(this.numberOfFrequencies);
            this.impedancePhasesDeg = new double[this.numberOfFrequencies];
            this.realZ = new double[this.numberOfFrequencies];
            this.imagZ = new double[this.numberOfFrequencies];
            this.realZweights = new double[this.numberOfFrequencies];
            this.imagZweights = new double[this.numberOfFrequencies];

            for(int var6 = 0; var6 < this.numberOfFrequencies; ++var6) {
                this.impedancePhasesDeg[var6] = Math.toDegrees(this.impedancePhasesRad[var6]);
                this.impedances[var6].polar(var2[var6], var3[var6]);
                this.realZ[var6] = this.impedances[var6].getReal();
                this.imagZ[var6] = this.impedances[var6].getImag();
                ErrorProp var7 = new ErrorProp(var2[var6], this.impedanceMagnitudeWeights[var6]);
                ErrorProp var8 = new ErrorProp(var3[var6], var5[var6]);
                ComplexErrorProp var9 = new ComplexErrorProp();
                var9.polar(var7, var8);
                this.realZweights[var6] = var9.getRealError();
                this.imagZweights[var6] = var9.getImagError();
            }

            this.frequenciesSet = true;
            this.impedancesSet = true;
            this.voltageOrImpedance = false;
            this.dataEnteredTypePointer = 6;
            if (this.estimatesNeeded) {
                this.setInitialEstimates();
            }

        }
    }

    public void impedanceDataAsPhasorDeg(double[] var1, double[] var2, double[] var3) {
        double[] var4 = new double[var1.length];
        double[] var5 = new double[var1.length];
        this.weightsSet = false;
        this.impedanceDataAsPhasorDeg(var1, var2, var3, var4, var5);
    }

    public void impedanceDataAsPhasorDeg(double[] var1, double[] var2, double[] var3, double[] var4, double[] var5) {
        this.numberOfFrequencies = var1.length;
        if (this.numberOfFrequencies != var2.length) {
            throw new IllegalArgumentException("The number of frequencies, " + this.numberOfFrequencies + ", does not equal the number of magnitudes, " + var2.length);
        } else if (this.numberOfFrequencies != var3.length) {
            throw new IllegalArgumentException("The number of frequencies, " + this.numberOfFrequencies + ", does not equal the number of phases, " + var3.length);
        } else if (this.numberOfFrequencies != var4.length) {
            throw new IllegalArgumentException("The number of frequencies, " + this.numberOfFrequencies + ", does not equal the number of magnitude weights, " + var4.length);
        } else if (this.numberOfFrequencies != var5.length) {
            throw new IllegalArgumentException("The number of frequencies, " + this.numberOfFrequencies + ", does not equal the number of phase weights, " + var5.length);
        } else {
            this.frequencies = Conv.copy(var1);
            this.setAllFrequencyArrays();
            this.setCalculatedArrayLengths();
            this.impedanceMagnitudes = Conv.copy(var2);
            this.impedancePhasesDeg = Conv.copy(var3);
            this.impedances = Complex.oneDarray(this.numberOfFrequencies);
            this.impedancePhasesRad = new double[this.numberOfFrequencies];
            this.impedancePhaseWeightsRad = new double[this.numberOfFrequencies];
            this.impedanceMagnitudeWeights = Conv.copy(var4);
            this.impedancePhaseWeightsDeg = Conv.copy(var5);
            this.realZ = new double[this.numberOfFrequencies];
            this.imagZ = new double[this.numberOfFrequencies];
            this.realZweights = new double[this.numberOfFrequencies];
            this.imagZweights = new double[this.numberOfFrequencies];

            for(int var6 = 0; var6 < this.numberOfFrequencies; ++var6) {
                this.impedancePhasesRad[var6] = Math.toRadians(this.impedancePhasesDeg[var6]);
                this.impedancePhaseWeightsRad[var6] = Math.toRadians(var5[var6]);
                this.impedances[var6].polar(var2[var6], this.impedancePhasesRad[var6]);
                this.realZ[var6] = this.impedances[var6].getReal();
                this.imagZ[var6] = this.impedances[var6].getImag();
                ErrorProp var7 = new ErrorProp(var2[var6], this.impedanceMagnitudeWeights[var6]);
                ErrorProp var8 = new ErrorProp(this.impedancePhasesRad[var6], this.impedancePhaseWeightsRad[var6]);
                ComplexErrorProp var9 = new ComplexErrorProp();
                var9.polar(var7, var8);
                this.realZweights[var6] = var9.getRealError();
                this.imagZweights[var6] = var9.getImagError();
            }

            this.frequenciesSet = true;
            this.impedancesSet = true;
            this.voltageOrImpedance = false;
            this.dataEnteredTypePointer = 7;
            if (this.estimatesNeeded) {
                this.setInitialEstimates();
            }

        }
    }

    private void setAllFrequencyArrays() {
        this.log10frequencies = new double[this.numberOfFrequencies];
        this.omegas = new double[this.numberOfFrequencies];
        this.log10omegas = new double[this.numberOfFrequencies];

        for(int var1 = 0; var1 < this.numberOfFrequencies; ++var1) {
            this.log10frequencies[var1] = Math.log10(this.frequencies[var1]);
            this.omegas[var1] = 6.283185307179586D * this.frequencies[var1];
            this.log10omegas[var1] = Math.log10(this.omegas[var1]);
        }

        this.frequenciesSet = true;
    }

    private void setCalculatedArrayLengths() {
        this.realZresiduals = new double[this.numberOfFrequencies];
        this.imagZresiduals = new double[this.numberOfFrequencies];
        this.calculatedRealZ = new double[this.numberOfFrequencies];
        this.calculatedImagZ = new double[this.numberOfFrequencies];
        this.calculatedImpedances = Complex.oneDarray(this.numberOfFrequencies);
        this.calculatedImpedanceMagnitudes = new double[this.numberOfFrequencies];
        this.calculatedImpedancePhasesRad = new double[this.numberOfFrequencies];
        this.calculatedImpedancePhasesDeg = new double[this.numberOfFrequencies];
        if (this.appliedVoltageSet && this.referenceSet) {
            this.calculatedRealV = new double[this.numberOfFrequencies];
            this.calculatedImagV = new double[this.numberOfFrequencies];
            this.calculatedVoltages = Complex.oneDarray(this.numberOfFrequencies);
            this.calculatedVoltageMagnitudes = new double[this.numberOfFrequencies];
            this.calculatedVoltagePhasesRad = new double[this.numberOfFrequencies];
            this.calculatedVoltagePhasesDeg = new double[this.numberOfFrequencies];
        }

    }

    private void setImpedanceArrayLengths() {
        this.realZ = new double[this.numberOfFrequencies];
        this.imagZ = new double[this.numberOfFrequencies];
        this.realZweights = new double[this.numberOfFrequencies];
        this.imagZweights = new double[this.numberOfFrequencies];
        this.impedances = Complex.oneDarray(this.numberOfFrequencies);
        this.impedanceMagnitudes = new double[this.numberOfFrequencies];
        this.impedancePhasesRad = new double[this.numberOfFrequencies];
        this.impedancePhasesDeg = new double[this.numberOfFrequencies];
    }

    private void calculateExperimentalImpedances() {
        if (this.referenceSet && this.appliedVoltageSet) {
            for(int var1 = 0; var1 < this.numberOfFrequencies; ++var1) {
                this.impedances[var1] = this.referenceImpedance.times(this.voltages[var1]).over(this.appliedVoltage.minus(this.voltages[var1]));
                this.realZ[var1] = this.impedances[var1].getReal();
                this.imagZ[var1] = this.impedances[var1].getImag();
                this.impedanceMagnitudes[var1] = this.impedances[var1].abs();
                this.impedancePhasesRad[var1] = this.impedances[var1].arg();
                this.impedancePhasesDeg[var1] = Math.toDegrees(this.impedancePhasesRad[var1]);
                if (this.weightsSet && this.voltageErrorSet) {
                    ComplexErrorProp var2 = new ComplexErrorProp(this.appliedVoltage.getReal(), this.appliedVoltageError.getReal(), this.appliedVoltage.getImag(), this.appliedVoltageError.getImag());
                    ComplexErrorProp var3 = new ComplexErrorProp(this.realV[var1], this.realVweights[var1], this.imagV[var1], this.imagVweights[var1]);
                    ComplexErrorProp var4 = new ComplexErrorProp(this.referenceImpedance.getReal(), 0.0D, this.referenceImpedance.getImag(), 0.0D);
                    ComplexErrorProp var5 = var3.over(var2).times(var4);
                    this.realZweights[var1] = var5.getRealError();
                    this.imagZweights[var1] = var5.getImagError();
                }

                this.impedancesSet = true;
            }
        }

    }

    public void setModel(ImpedSpecModel var1, String[] var2, double[] var3, double[] var4) {
        this.userModel = var1;
        this.parameterSymbols = var2;
        this.numberOfParameters = var2.length;
        if (this.numberOfParameters != var3.length) {
            throw new IllegalArgumentException("The number of parameter symbols, " + this.numberOfParameters + ", does not equal the number of initial estimates, " + var3.length);
        } else if (this.numberOfParameters != var4.length) {
            throw new IllegalArgumentException("The number of parameter symbols, " + this.numberOfParameters + ", does not equal the number of initial steps, " + var4.length);
        } else {
            this.initialEstimates = var3;
            this.initialSteps = var4;
            this.setEstimateArrayDimensions();
            this.estimatesSet = true;
            this.userModelSet = true;
        }
    }

    public void setModel(ImpedSpecModel var1, String[] var2, double[] var3) {
        this.userModel = var1;
        this.parameterSymbols = var2;
        this.numberOfParameters = var2.length;
        if (this.numberOfParameters != var3.length) {
            throw new IllegalArgumentException("The number of parameter symbols, " + this.numberOfParameters + ", does not equal the number of initial estimates, " + var3.length);
        } else {
            this.initialEstimates = var3;
            this.initialSteps = new double[this.numberOfParameters];

            for(int var4 = 0; var4 < this.numberOfParameters; ++var4) {
                this.initialSteps[var4] = Math.abs(this.initialEstimates[var4]) * 0.1D;
            }

            this.setEstimateArrayDimensions();
            this.estimatesSet = true;
            this.userModelSet = true;
        }
    }

    public void setModel(int var1, double[] var2, double[] var3) {
        this.numberOfParameters = var2.length;
        if (this.numberOfParameters != Impedance.modelComponents(var1).length) {
            throw new IllegalArgumentException("The number of parameter estimates, " + this.numberOfParameters + ", does not equal the number of parameters, " + Impedance.modelComponents(var1).length + ", in model number " + var1);
        } else if (this.numberOfParameters != var3.length) {
            throw new IllegalArgumentException("The number of parameter estimates, " + this.numberOfParameters + ", does not equal the number of parameter steps, " + var3.length);
        } else {
            this.modelNumber = var1;
            this.initialEstimates = var2;
            this.initialSteps = var3;
            this.parameterSymbols = Impedance.modelComponents(var1);
            this.setEstimateArrayDimensions();
            this.estimatesSet = true;
            this.modelSet = true;
        }
    }

    public void setModel(int var1, double[] var2) {
        this.numberOfParameters = var2.length;
        if (this.numberOfParameters != Impedance.modelComponents(var1).length) {
            throw new IllegalArgumentException("The number of parameter estimates, " + this.numberOfParameters + ", does not equal the number of parameters, " + Impedance.modelComponents(var1).length + ", in model number " + var1);
        } else {
            this.modelNumber = var1;
            this.initialEstimates = var2;
            this.parameterSymbols = Impedance.modelComponents(var1);
            this.initialSteps = new double[this.numberOfParameters];

            for(int var3 = 0; var3 < this.numberOfParameters; ++var3) {
                this.initialSteps[var3] = Math.abs(this.initialEstimates[var3]) * 0.1D;
            }

            this.setEstimateArrayDimensions();
            this.estimatesSet = true;
            this.modelSet = true;
        }
    }

    public void setModel(int var1) {
        this.modelNumber = var1;
        this.parameterSymbols = Impedance.modelComponents(var1);
        this.numberOfParameters = this.parameterSymbols.length;
        this.setEstimateArrayDimensions();
        this.setInitialEstimates();
        this.estimatesSet = true;
        this.modelSet = true;
    }

    private void setEstimateArrayDimensions() {
        this.bestEstimates = new double[this.numberOfParameters];
        this.standardDeviations = new double[this.numberOfParameters];
        this.coefficientsOfVariation = new double[this.numberOfParameters];
        this.preMinimumGradients = new double[this.numberOfParameters];
        this.postMinimumGradients = new double[this.numberOfParameters];
        this.correlationCoefficients = new double[this.numberOfParameters][this.numberOfParameters];
    }

    private void setInitialEstimates() {
        if (this.impedancesSet && this.frequenciesSet) {
            this.degreesOfFreedom = this.numberOfFrequencies - this.numberOfParameters;
            if (this.degreesOfFreedom <= 0) {
                throw new IllegalArgumentException("Degrees of freedom, " + this.degreesOfFreedom + ", are less than 1");
            }

            double var1 = Stat.mean(this.realZ);
            double var3 = Fmath.minimum(this.realZ);
            Fmath.indexOf(this.realZ, var3);
            double var6 = Fmath.maximum(this.realZ);
            Fmath.indexOf(this.realZ, var6);
            double var9 = Stat.mean(this.imagZ);
            double var11 = Fmath.minimum(this.imagZ);
            int var13 = Fmath.indexOf(this.imagZ, var11);
            double var14 = Fmath.maximum(this.imagZ);
            int var16 = Fmath.indexOf(this.imagZ, var14);
            double var17 = Math.max(Math.abs(var11), Math.abs(var14));
            int var19 = Fmath.indexOf(this.imagZ, var17);
            if (var19 == -1) {
                var19 = Fmath.indexOf(this.imagZ, -var17);
            }

            if (var19 == -1) {
                var19 = this.numberOfFrequencies / 2;
            }

            double var20 = Stat.geometricMean(this.log10frequencies);
            double var78;
            double var80;
            switch(this.modelNumber) {
                case 1:
                    this.initialEstimates = new double[this.numberOfParameters];
                    this.initialEstimates[0] = var1;
                    break;
                case 2:
                    this.initialEstimates = new double[this.numberOfParameters];
                    double var22 = 0.0D;

                    for(int var88 = 0; var88 < this.numberOfFrequencies; ++var88) {
                        var22 += 1.0D / Math.abs(this.imagZ[var88] * this.omegas[var88]);
                    }

                    this.initialEstimates[0] = var22 / (double)this.numberOfFrequencies;
                    break;
                case 3:
                    this.initialEstimates = new double[this.numberOfParameters];
                    double var24 = 0.0D;

                    for(int var89 = 0; var89 < this.numberOfFrequencies; ++var89) {
                        var24 += Math.abs(this.imagZ[var89] / this.omegas[var89]);
                    }

                    this.initialEstimates[0] = var24 / (double)this.numberOfFrequencies;
                    break;
                case 4:
                    this.initialEstimates = new double[this.numberOfParameters];
                    double var26 = 0.0D;

                    for(int var90 = 0; var90 < this.numberOfFrequencies; ++var90) {
                        var26 += Math.abs(this.realZ[var90] * Math.sqrt(this.omegas[var90]));
                        var26 += Math.abs(this.imagZ[var90] * Math.sqrt(this.omegas[var90]));
                    }

                    this.initialEstimates[0] = var26 / (2.0D * (double)this.numberOfFrequencies);
                    break;
                case 5:
                    this.initialEstimates = new double[this.numberOfParameters];
                    double var28 = 0.0D;

                    for(int var91 = 0; var91 < this.numberOfFrequencies; ++var91) {
                        var28 += Math.abs(this.realZ[var91] * Math.sqrt(this.omegas[var91]));
                        var28 += Math.abs(this.imagZ[var91] * Math.sqrt(this.omegas[var91]));
                    }

                    this.initialEstimates[0] = var28 / (2.0D * (double)this.numberOfFrequencies);
                    this.initialEstimates[1] = Math.abs(var1 / this.initialEstimates[0]);
                    break;
                case 6:
                    this.initialEstimates = new double[this.numberOfParameters];
                    double var30 = 0.0D;

                    for(int var32 = 0; var32 < this.numberOfFrequencies; ++var32) {
                        var30 += this.imagZ[var32] / this.realZ[var32];
                    }

                    var30 /= (double)this.numberOfFrequencies;
                    double var92 = Math.abs(Math.atan(var30));
                    double var34 = Math.cos(var92);
                    double var36 = Math.sin(var92);
                    this.initialEstimates[1] = var92 / 1.5707963267948966D;
                    double var38 = 0.0D;

                    for(int var93 = 0; var93 < this.numberOfFrequencies; ++var93) {
                        var38 += Math.abs(this.realZ[var93] / (var34 * Math.pow(this.omegas[var93], this.initialEstimates[1])));
                        var38 += Math.abs(this.imagZ[var93] / (var36 * Math.pow(this.omegas[var93], this.initialEstimates[1])));
                    }

                    this.initialEstimates[0] = var38 / (2.0D * (double)this.numberOfFrequencies);
                    break;
                case 7:
                    this.initialEstimates = new double[this.numberOfParameters];
                    this.initialEstimates[0] = var1;
                    double var40 = 0.0D;

                    for(int var94 = 0; var94 < this.numberOfFrequencies; ++var94) {
                        var40 += 1.0D / Math.abs(this.imagZ[var94] * this.omegas[var94]);
                    }

                    this.initialEstimates[1] = var40 / (double)this.numberOfFrequencies;
                    break;
                case 8:
                    this.initialEstimates = new double[this.numberOfParameters];
                    this.initialEstimates[0] = var1;
                    double var42 = 0.0D;

                    for(int var95 = 0; var95 < this.numberOfFrequencies; ++var95) {
                        var42 += Math.abs(this.imagZ[var95] / this.omegas[var95]);
                    }

                    this.initialEstimates[1] = var42 / (double)this.numberOfFrequencies;
                    break;
                case 9:
                    this.initialEstimates = new double[this.numberOfParameters];
                    double var44 = 0.0D;
                    double var46 = 0.0D;

                    for(int var96 = 1; var96 < this.numberOfFrequencies; ++var96) {
                        double var49 = (this.frequencies[var96] - this.frequencies[var96 - 1]) / this.frequencies[var96] / (this.imagZ[var96] * this.frequencies[var96 - 1] - this.imagZ[var96 - 1] * this.frequencies[var96]);
                        double var51 = (this.imagZ[var96] + 1.0D / (var49 * this.frequencies[var96])) / this.frequencies[var96];
                        var44 += var51;
                        var46 += var49;
                    }

                    this.initialEstimates[0] = var44 / (double)(this.numberOfFrequencies - 1);
                    this.initialEstimates[1] = var46 / (double)(this.numberOfFrequencies - 1);
                    break;
                case 10:
                    this.initialEstimates = new double[this.numberOfParameters];
                    this.initialEstimates[0] = var6;
                    this.initialEstimates[1] = 1.0D / (var6 * this.frequencies[var13]);
                    break;
                case 11:
                    this.initialEstimates = new double[this.numberOfParameters];
                    this.initialEstimates[0] = var6;
                    this.initialEstimates[1] = var6 / this.frequencies[var16];
                    break;
                case 12:
                    this.initialEstimates = new double[this.numberOfParameters];
                    double var48 = 1.0D / this.frequencies[var13];
                    double var50 = 0.0D;
                    double var52 = 0.0D;

                    for(int var97 = 1; var97 < this.numberOfFrequencies; ++var97) {
                        double var55 = this.imagZ[var97] * (this.frequencies[var97] * var48 - 1.0D / this.frequencies[var97]);
                        var50 += var55;
                        var52 += var48 / var55;
                    }

                    this.initialEstimates[0] = var50 / (double)this.numberOfFrequencies;
                    this.initialEstimates[1] = var52 / (double)this.numberOfFrequencies;
                    break;
                case 13:
                    this.initialEstimates = new double[this.numberOfParameters];
                    this.initialEstimates[2] = var3;
                    this.initialEstimates[0] = var6 - var3;
                    this.initialEstimates[1] = 1.0D / (this.initialEstimates[0] * this.frequencies[var13]);
                    break;
                case 14:
                    this.initialEstimates = new double[this.numberOfParameters];
                    this.initialEstimates[2] = var3;
                    this.initialEstimates[0] = var6 - var3;
                    double var54 = 0.0D;
                    double var56 = 0.0D;

                    for(int var98 = 1; var98 < this.numberOfFrequencies; ++var98) {
                        double var59 = (this.frequencies[var98] - this.frequencies[var98 - 1]) / this.frequencies[var98] / (this.imagZ[var98] * this.frequencies[var98 - 1] - this.imagZ[var98 - 1] * this.frequencies[var98]);
                        double var61 = (this.imagZ[var98] + 1.0D / (var59 * this.frequencies[var98])) / this.frequencies[var98];
                        var54 += var61;
                        var56 += var59;
                    }

                    this.initialEstimates[3] = var54 / (double)(this.numberOfFrequencies - 1);
                    this.initialEstimates[1] = var56 / (double)(this.numberOfFrequencies - 1);
                    break;
                case 15:
                    this.initialEstimates = new double[this.numberOfParameters];
                    this.initialEstimates[2] = var3;
                    this.initialEstimates[0] = var6 - var3;
                    double var58 = 1.0D / this.frequencies[var13];
                    double var60 = 0.0D;
                    double var62 = 0.0D;

                    for(int var99 = 1; var99 < this.numberOfFrequencies; ++var99) {
                        double var65 = this.imagZ[var99] * (this.frequencies[var99] * var58 - 1.0D / this.frequencies[var99]);
                        var60 += var65;
                        var62 += var58 / var65;
                    }

                    this.initialEstimates[3] = var60 / (double)this.numberOfFrequencies;
                    this.initialEstimates[1] = var62 / (double)this.numberOfFrequencies;
                    break;
                case 16:
                    this.initialEstimates = new double[this.numberOfParameters];
                    this.initialEstimates[0] = var6;
                    double var64 = 0.0D;

                    for(int var100 = 0; var100 < this.numberOfFrequencies; ++var100) {
                        var64 += 1.0D / Math.abs(this.imagZ[var100] * this.omegas[var100]);
                    }

                    this.initialEstimates[1] = 2.0D * var64 / (double)this.numberOfFrequencies;
                    this.initialEstimates[2] = this.initialEstimates[1];
                    break;
                case 17:
                    this.initialEstimates = new double[this.numberOfParameters];
                    this.initialEstimates[0] = var6;
                    double var66 = 0.0D;

                    for(int var68 = 0; var68 < this.numberOfFrequencies; ++var68) {
                        var66 += 1.0D / Math.abs(this.imagZ[var68] * this.omegas[var68]);
                    }

                    this.initialEstimates[1] = var66 / (2.0D * (double)this.numberOfFrequencies);
                    this.initialEstimates[2] = this.initialEstimates[1];
                case 18:
                    this.initialEstimates = new double[this.numberOfParameters];
                    this.initialEstimates[0] = var3;
                    this.initialEstimates[2] = var6 - var3;
                    double var101 = 0.0D;

                    for(int var102 = 0; var102 < this.numberOfFrequencies; ++var102) {
                        var101 += 1.0D / Math.abs(this.imagZ[var102] * this.omegas[var102]);
                    }

                    this.initialEstimates[1] = 2.0D * var101 / (double)this.numberOfFrequencies;
                    this.initialEstimates[3] = this.initialEstimates[1];
                    break;
                case 19:
                    this.initialEstimates = new double[this.numberOfParameters];
                    this.initialEstimates[0] = var6 / 2.0D;
                    this.initialEstimates[2] = this.initialEstimates[0];
                    this.initialEstimates[1] = 2.0D / (this.initialEstimates[0] * this.frequencies[var13]);
                    this.initialEstimates[3] = this.initialEstimates[1];
                    break;
                case 20:
                    this.initialEstimates = new double[this.numberOfParameters];
                    this.initialEstimates[4] = var3;
                    this.initialEstimates[0] = (var6 - var3) / 2.0D;
                    this.initialEstimates[2] = this.initialEstimates[0];
                    this.initialEstimates[1] = 2.0D / (this.initialEstimates[0] * this.frequencies[var13]);
                    this.initialEstimates[3] = this.initialEstimates[1];
                    break;
                case 21:
                    this.initialEstimates = new double[this.numberOfParameters];
                    this.initialEstimates[4] = var3;
                    this.initialEstimates[0] = (var6 - var3) / 2.0D;
                    this.initialEstimates[2] = this.initialEstimates[0];
                    double var70 = 0.0D;

                    for(int var103 = 0; var103 < this.numberOfFrequencies; ++var103) {
                        var70 += 1.0D / Math.abs(this.imagZ[var103] * this.omegas[var103]);
                    }

                    this.initialEstimates[1] = var70 / (2.0D * (double)this.numberOfFrequencies);
                    this.initialEstimates[3] = this.initialEstimates[1];
                    break;
                case 22:
                    this.initialEstimates = new double[this.numberOfParameters];
                    this.initialEstimates[0] = var6 / 3.0D;
                    this.initialEstimates[2] = this.initialEstimates[0];
                    this.initialEstimates[4] = this.initialEstimates[0];
                    this.initialEstimates[1] = 3.0D / (this.initialEstimates[0] * this.frequencies[var13]);
                    this.initialEstimates[3] = this.initialEstimates[1];
                    this.initialEstimates[5] = this.initialEstimates[1];
                    break;
                case 23:
                    this.initialEstimates = new double[this.numberOfParameters];
                    this.initialEstimates[6] = var3;
                    this.initialEstimates[0] = (var6 - var3) / 3.0D;
                    this.initialEstimates[2] = this.initialEstimates[0];
                    this.initialEstimates[4] = this.initialEstimates[0];
                    this.initialEstimates[1] = 3.0D / (this.initialEstimates[0] * this.frequencies[var13]);
                    this.initialEstimates[3] = this.initialEstimates[1];
                    this.initialEstimates[5] = this.initialEstimates[1];
                    break;
                case 24:
                    this.initialEstimates = new double[this.numberOfParameters];
                    this.initialEstimates[3] = var3;
                    this.initialEstimates[0] = var6 - var3;
                    double var72 = 0.0D;
                    int var104;
                    if (var13 < this.numberOfFrequencies - 3) {
                        this.initialEstimates[1] = 1.0D / (this.initialEstimates[0] * this.frequencies[var13]);

                        for(var104 = var13; var104 < this.numberOfFrequencies; ++var104) {
                            var72 += Math.abs(this.realZ[var104] * Math.sqrt(this.omegas[var104]));
                            var72 += Math.abs(this.imagZ[var104] * Math.sqrt(this.omegas[var104]));
                        }

                        this.initialEstimates[2] = var72 / (2.0D * (double)(this.numberOfFrequencies - var13));
                    } else {
                        this.initialEstimates[1] = 1.0D / (this.initialEstimates[0] * var20);

                        for(var104 = 0; var104 < this.numberOfFrequencies; ++var104) {
                            var72 += Math.abs(this.realZ[var104] * Math.sqrt(this.omegas[var104]));
                            var72 += Math.abs(this.imagZ[var104] * Math.sqrt(this.omegas[var104]));
                        }

                        this.initialEstimates[2] = var72 / (2.0D * (double)this.numberOfFrequencies);
                    }
                    break;
                case 25:
                    this.initialEstimates = new double[this.numberOfParameters];
                    this.initialEstimates[4] = var3;
                    this.initialEstimates[0] = var6 - var3;
                    double var74 = 0.0D;
                    int var105;
                    if (var13 < this.numberOfFrequencies - 3) {
                        this.initialEstimates[1] = 1.0D / (this.initialEstimates[0] * this.frequencies[var13]);

                        for(var105 = var13; var105 < this.numberOfFrequencies; ++var105) {
                            var74 += Math.abs(this.realZ[var105] * Math.sqrt(this.omegas[var105]));
                            var74 += Math.abs(this.imagZ[var105] * Math.sqrt(this.omegas[var105]));
                        }

                        this.initialEstimates[2] = var74 / (2.0D * (double)(this.numberOfFrequencies - var13));
                        this.initialEstimates[3] = Math.abs(var1 / this.initialEstimates[2]);
                    } else {
                        this.initialEstimates[1] = 1.0D / (this.initialEstimates[0] * var20);

                        for(var105 = 0; var105 < this.numberOfFrequencies; ++var105) {
                            var74 += Math.abs(this.realZ[var105] * Math.sqrt(this.omegas[var105]));
                            var74 += Math.abs(this.imagZ[var105] * Math.sqrt(this.omegas[var105]));
                        }

                        this.initialEstimates[2] = var74 / (2.0D * (double)this.numberOfFrequencies);
                        this.initialEstimates[3] = Math.abs(var1 / this.initialEstimates[2]);
                    }
                    break;
                case 26:
                    this.initialEstimates = new double[this.numberOfParameters];
                    this.initialEstimates[4] = var3;
                    this.initialEstimates[0] = var6 - var3;
                    double var76 = 0.0D;
                    double var84;
                    int var86;
                    int var106;
                    double var108;
                    if (var13 < this.numberOfFrequencies - 3) {
                        this.initialEstimates[1] = 1.0D / (this.initialEstimates[0] * this.frequencies[var13]);

                        for(var106 = var13; var106 < this.numberOfFrequencies; ++var106) {
                            var76 += this.imagZ[var106] / this.realZ[var106];
                        }

                        var76 /= (double)(this.numberOfFrequencies - var13);
                        var78 = Math.abs(Math.atan(var76));
                        var80 = Math.cos(var78);
                        var108 = Math.sin(var78);
                        this.initialEstimates[3] = var78 / 1.5707963267948966D;
                        var84 = 0.0D;

                        for(var86 = var13; var86 < this.numberOfFrequencies; ++var86) {
                            var84 += Math.abs(this.realZ[var86] / (var80 * Math.pow(this.omegas[var86], this.initialEstimates[1])));
                            var84 += Math.abs(this.imagZ[var86] / (var108 * Math.pow(this.omegas[var86], this.initialEstimates[1])));
                        }

                        this.initialEstimates[2] = var84 / (2.0D * (double)(this.numberOfFrequencies - var13));
                    } else {
                        this.initialEstimates[1] = 1.0D / (this.initialEstimates[0] * var20);

                        for(var106 = 0; var106 < this.numberOfFrequencies; ++var106) {
                            var76 += this.imagZ[var106] / this.realZ[var106];
                        }

                        var76 /= (double)this.numberOfFrequencies;
                        var78 = Math.abs(Math.atan(var76));
                        var80 = Math.cos(var78);
                        var108 = Math.sin(var78);
                        this.initialEstimates[3] = var78 / 1.5707963267948966D;
                        var84 = 0.0D;

                        for(var86 = 0; var86 < this.numberOfFrequencies; ++var86) {
                            var84 += Math.abs(this.realZ[var86] / (var80 * Math.pow(this.omegas[var86], this.initialEstimates[1])));
                            var84 += Math.abs(this.imagZ[var86] / (var108 * Math.pow(this.omegas[var86], this.initialEstimates[1])));
                        }

                        this.initialEstimates[2] = var84 / (2.0D * (double)this.numberOfFrequencies);
                    }
                    break;
                case 27:
                    this.initialEstimates = new double[this.numberOfParameters];
                    this.initialEstimates[0] = var6 / 2.0D;
                    this.initialEstimates[2] = this.initialEstimates[0];
                    var78 = 0.0D;
                    int var107;
                    if (var13 < this.numberOfFrequencies - 3) {
                        this.initialEstimates[1] = 2.0D / (this.initialEstimates[0] * this.frequencies[var13]);
                        this.initialEstimates[3] = this.initialEstimates[1];

                        for(var107 = var13; var107 < this.numberOfFrequencies; ++var107) {
                            var78 += Math.abs(this.realZ[var107] * Math.sqrt(this.omegas[var107]));
                            var78 += Math.abs(this.imagZ[var107] * Math.sqrt(this.omegas[var107]));
                        }

                        this.initialEstimates[4] = var78 / (2.0D * (double)(this.numberOfFrequencies - var13));
                    } else {
                        this.initialEstimates[1] = 2.0D / (this.initialEstimates[0] * var20);
                        this.initialEstimates[3] = this.initialEstimates[1];

                        for(var107 = var13; var107 < this.numberOfFrequencies; ++var107) {
                            var78 += Math.abs(this.realZ[var107] * Math.sqrt(this.omegas[var107]));
                            var78 += Math.abs(this.imagZ[var107] * Math.sqrt(this.omegas[var107]));
                        }

                        this.initialEstimates[4] = var78 / (2.0D * (double)this.numberOfFrequencies);
                    }
                    break;
                case 28:
                    this.initialEstimates = new double[this.numberOfParameters];
                    this.initialEstimates[6] = var3;
                    this.initialEstimates[0] = (var6 - var3) / 2.0D;
                    this.initialEstimates[2] = this.initialEstimates[0];
                    var80 = 0.0D;
                    int var82;
                    if (var13 < this.numberOfFrequencies - 3) {
                        this.initialEstimates[1] = 3.0D / (this.initialEstimates[0] * this.frequencies[var13]);
                        this.initialEstimates[3] = this.initialEstimates[1];
                        this.initialEstimates[5] = this.initialEstimates[1];

                        for(var82 = var13; var82 < this.numberOfFrequencies; ++var82) {
                            var80 += Math.abs(this.realZ[var82] * Math.sqrt(this.omegas[var82]));
                            var80 += Math.abs(this.imagZ[var82] * Math.sqrt(this.omegas[var82]));
                        }

                        this.initialEstimates[4] = var80 / (2.0D * (double)(this.numberOfFrequencies - var13));
                    } else {
                        this.initialEstimates[1] = 3.0D / (this.initialEstimates[0] * var20);
                        this.initialEstimates[3] = this.initialEstimates[1];
                        this.initialEstimates[5] = this.initialEstimates[1];

                        for(var82 = var13; var82 < this.numberOfFrequencies; ++var82) {
                            var80 += Math.abs(this.realZ[var82] * Math.sqrt(this.omegas[var82]));
                            var80 += Math.abs(this.imagZ[var82] * Math.sqrt(this.omegas[var82]));
                        }

                        this.initialEstimates[4] = var80 / (2.0D * (double)this.numberOfFrequencies);
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Automatically calculated initial estimates are only presntly available for models 1 to 28");
            }

            this.initialSteps = new double[this.numberOfParameters];

            int var87;
            for(var87 = 0; var87 < this.numberOfParameters; ++var87) {
                this.initialSteps[var87] = Math.abs(this.initialEstimates[var87]) * 0.1D;
            }

            for(var87 = 0; var87 < this.numberOfParameters; ++var87) {
                if (this.initialSteps[var87] == 0.0D) {
                    if (this.parameterSymbols[var87].trim().substring(0, 1).equals("R")) {
                        this.initialSteps[var87] = var6 * 0.01D;
                    }

                    if (this.parameterSymbols[var87].trim().substring(0, 1).equals("C")) {
                        this.initialSteps[var87] = 0.01D / (var17 * this.frequencies[var19]);
                    }

                    if (this.parameterSymbols[var87].trim().substring(0, 1).equals("L")) {
                        this.initialSteps[var87] = var17 * 0.01D / this.frequencies[var19];
                    }

                    if (this.parameterSymbols[var87].trim().substring(0, 1).equals("W")) {
                        this.initialSteps[var87] = 0.01D / (var17 * Math.sqrt(this.frequencies[var19]));
                    }

                    if (this.parameterSymbols[var87].trim().substring(0, 2).equals("Fs")) {
                        this.initialSteps[var87] = 0.01D / (var17 * Math.sqrt(this.frequencies[var19]));
                    }

                    if (this.parameterSymbols[var87].trim().substring(0, 2).equals("Fd")) {
                        this.initialSteps[var87] = 0.05D;
                    }

                    if (this.parameterSymbols[var87].trim().substring(0, 2).equals("Qs")) {
                        this.initialSteps[var87] = 0.01D / (var17 * Math.sqrt(this.frequencies[var19]));
                    }

                    if (this.parameterSymbols[var87].trim().substring(0, 2).equals("Qa")) {
                        this.initialSteps[var87] = 0.005D;
                    }
                }
            }

            this.estimatesSet = true;
        } else {
            this.estimatesNeeded = true;
        }

    }

    public double[] getInitialEstimates() {
        if (!this.estimatesSet) {
            throw new IllegalArgumentException("No initial estimates have been entered or calculated");
        } else {
            return this.initialEstimates;
        }
    }

    public String[] getCircuitComponents() {
        return this.parameterSymbols;
    }

    public void addNewConstraint(int var1, int var2, double var3) {
        this.constraints.add(new Integer(var1));
        this.constraints.add(new Integer(var2));
        this.constraints.add(new Double(var3));
        ++this.numberOfAddedConstraints;
        this.constraintsAdded = true;
    }

    public void addNewConstraint(String var1, int var2, double var3) {
        if (this.numberOfParameters == 0) {
            throw new IllegalArgumentException("No model number or model parameters entered");
        } else {
            int var5 = -1;

            for(int var6 = 0; var6 < this.numberOfParameters; ++var6) {
                if (this.parameterSymbols[var6].trim().equals(var1.trim())) {
                    var5 = var6;
                }
            }

            if (var5 == -1) {
                throw new IllegalArgumentException("Parameter symbol, " + var1 + ", not found");
            } else {
                this.constraints.add(new Integer(var5));
                this.constraints.add(new Integer(var2));
                this.constraints.add(new Double(var3));
                ++this.numberOfAddedConstraints;
                this.constraintsAdded = true;
            }
        }
    }

    public void removeDefaultConstraints() {
        this.supressDefaultConstraints = true;
    }

    public void restoreDefaultConstraints() {
        this.supressDefaultConstraints = false;
    }

    public void removeAddedConstraints() {
        this.supressAddedConstraints = true;
        this.constraintsAdded = false;
    }

    public void removeAllConstraints() {
        this.supressDefaultConstraints = true;
        this.supressAddedConstraints = true;
        this.constraintsAdded = false;
    }

    public void resetMaximumNumberOfIterations(int var1) {
        this.maximumIterations = var1;
    }

    public void resetTolerance(double var1) {
        this.tolerance = var1;
    }

    public ArrayList<Object> getRegressionResultsAsArrayList() {
        if (!this.regressionDone) {
            this.regression();
        }

        return this.results;
    }

    public Vector<Object> getRegressionResultsAsVector() {
        if (!this.regressionDone) {
            this.regression();
        }

        int var1 = this.results.size();
        Vector var2 = new Vector(var1);

        for(int var3 = 0; var3 < var1; ++var3) {
            var2.add(this.results.get(var3));
        }

        return var2;
    }

    public Vector<Object> getRegressionResults() {
        return this.getRegressionResults();
    }

    private void setRegressionArrays() {
        this.xRegression = new double[this.numberOfFrequencies];
        this.yRegression = new double[2][this.numberOfFrequencies];
        if (this.weightsSet) {
            this.wRegression = new double[2][this.numberOfFrequencies];
        }

        int var1;
        for(var1 = 0; var1 < this.numberOfFrequencies; ++var1) {
            this.xRegression[var1] = this.omegas[var1];
            this.yRegression[0][var1] = this.realZ[var1];
            this.yRegression[1][var1] = this.imagZ[var1];
        }

        if (this.weightsSet) {
            for(var1 = 0; var1 < this.numberOfFrequencies; ++var1) {
                this.wRegression[0][var1] = this.realZweights[var1];
                this.wRegression[1][var1] = this.imagZweights[var1];
            }
        }

    }

    public ArrayList<Object> regression() {
        this.degreesOfFreedom = this.numberOfFrequencies - this.numberOfParameters;
        if (this.degreesOfFreedom <= 0) {
            throw new IllegalArgumentException("Degrees of freedom, " + this.degreesOfFreedom + ", are less than 1");
        } else if (!this.impedancesSet) {
            throw new IllegalArgumentException("No impedances or voltages have been entered");
        } else {
            if (!this.estimatesSet && !this.userModelSet) {
                this.setInitialEstimates();
            }

            this.setRegressionArrays();
            this.results = new ArrayList();
            this.results.add(new Integer(this.numberOfFrequencies));
            this.results.add(new Integer(this.numberOfParameters));
            this.results.add(new Integer(this.degreesOfFreedom));
            this.results.add(this.parameterSymbols);
            this.results.add(Conv.copy(this.initialEstimates));
            this.results.add(Conv.copy(this.initialSteps));
            if (this.weightsSet) {
                this.enterData(this.xRegression, this.yRegression, this.wRegression);
            } else {
                this.enterData(this.xRegression, this.yRegression);
            }

            if (this.userModelSet) {
                ImpedSpecRegressionFunction2 var1 = new ImpedSpecRegressionFunction2();
                var1.numberOfFrequencies = this.numberOfFrequencies;
                var1.isModel = this.userModel;
                this.regressionFunction = var1;
            } else {
                ImpedSpecRegressionFunction1 var10 = new ImpedSpecRegressionFunction1();
                var10.numberOfFrequencies = this.numberOfFrequencies;
                var10.modelNumber = this.modelNumber;
                this.regressionFunction = var10;
            }

            int[] var11 = null;
            int[] var2 = null;
            double[] var3 = null;
            int var4;
            int var6;
            int var7;
            if (this.constraintsAdded) {
                var11 = new int[this.numberOfAddedConstraints];
                var2 = new int[this.numberOfAddedConstraints];
                var3 = new double[this.numberOfAddedConstraints];
                var4 = 0;

                for(int var5 = 0; var5 < this.numberOfAddedConstraints; ++var5) {
                    var6 = (Integer)this.constraints.get(var4);
                    var11[var5] = var6;
                    ++var4;
                    var7 = (Integer)this.constraints.get(var4);
                    var2[var5] = var7;
                    ++var4;
                    double var8 = (Double)this.constraints.get(var4);
                    var3[var5] = var8;
                    ++var4;
                    this.addConstraint(var6, var7, var8);
                }
            }

            int var9;
            if (!this.supressDefaultConstraints) {
                for(var4 = 0; var4 < this.numberOfParameters; ++var4) {
                    double var12 = 0.0D;
                    double var16 = 1.0D;
                    if (this.constraintsAdded) {
                        for(var9 = 0; var9 < this.numberOfAddedConstraints; ++var9) {
                            if (var11[var9] == var4) {
                                if (var2[var9] == 1) {
                                    var16 = var3[var9];
                                } else {
                                    var12 = var3[var9];
                                }
                            }
                        }
                    }

                    this.addConstraint(var4, -1, var12);
                    if (this.parameterSymbols[var4].trim().substring(0, 1).equals("Qa")) {
                        this.addConstraint(var4, 1, var16);
                    }
                }
            }

            this.simplex2(this.regressionFunction, Conv.copy(this.initialEstimates), Conv.copy(this.initialSteps), this.tolerance, this.maximumIterations);
            this.numberOfIterations1 = this.getNiter();
            double[] var13 = this.getCoeff();
            double[] var14 = new double[this.numberOfParameters];

            for(var6 = 0; var6 < this.numberOfParameters; ++var6) {
                var14[var6] = Math.abs(var13[var6]) * 0.1D;
            }

            this.simplex2(this.regressionFunction, var13, var14, this.tolerance, this.maximumIterations);
            this.bestEstimates = this.getCoeff();
            this.results.add(this.bestEstimates);
            this.standardDeviations = this.getCoeffSd();
            this.results.add(this.standardDeviations);
            this.coefficientsOfVariation = this.getCoeffVar();
            this.results.add(this.coefficientsOfVariation);
            this.correlationCoefficients = this.getCorrCoeffMatrix();
            this.results.add(this.correlationCoefficients);
            double[][] var15 = new double[this.numberOfParameters][2];
            if (this.getGrad() == null) {
                for(var7 = 0; var7 < this.numberOfParameters; ++var7) {
                    this.preMinimumGradients[var7] = 0.0D / 0.0;
                    this.postMinimumGradients[var7] = 0.0D / 0.0;
                }
            } else {
                var15 = this.getGrad();

                for(var7 = 0; var7 < this.numberOfParameters; ++var7) {
                    this.preMinimumGradients[var7] = var15[var7][0];
                    this.postMinimumGradients[var7] = var15[var7][1];
                }
            }

            this.results.add(this.preMinimumGradients);
            this.results.add(this.postMinimumGradients);
            this.sumOfSquares = this.getSumOfSquares();
            this.results.add(new Double(this.sumOfSquares));
            this.reducedSumOfSquares = this.sumOfSquares / (double)this.degreesOfFreedom;
            this.results.add(new Double(this.reducedSumOfSquares));
            if (this.weightsSet) {
                this.chiSquare = this.getChiSquare();
                this.results.add(new Double(this.chiSquare));
                this.reducedChiSquare = this.getReducedChiSquare();
                this.results.add(new Double(this.reducedChiSquare));
            } else {
                this.results.add((Object)null);
                this.results.add((Object)null);
            }

            this.numberOfIterations2 = this.getNiter();
            this.results.add(new Integer(this.numberOfIterations1));
            this.results.add(new Integer(this.numberOfIterations2));
            this.results.add(new Integer(this.maximumIterations));
            this.results.add(this.dataEnteredType[this.dataEnteredTypePointer]);
            this.results.add(this.frequencies);
            this.results.add(this.log10frequencies);
            this.results.add(this.omegas);
            this.results.add(this.log10omegas);
            this.results.add(this.impedanceMagnitudes);
            this.results.add(this.impedancePhasesRad);
            this.results.add(this.impedancePhasesDeg);
            this.results.add(this.impedances);
            this.results.add(this.realZ);
            this.results.add(this.imagZ);
            double[] var19 = this.getYcalc();

            for(int var17 = 0; var17 < this.numberOfFrequencies; ++var17) {
                this.calculatedRealZ[var17] = var19[var17];
                this.calculatedImagZ[var17] = var19[var17 + this.numberOfFrequencies];
            }

            this.results.add(this.calculatedRealZ);
            this.results.add(this.calculatedImagZ);
            double[] var18 = this.getResiduals();

            for(var9 = 0; var9 < this.numberOfFrequencies; ++var9) {
                this.realZresiduals[var9] = var18[var9];
                this.imagZresiduals[var9] = var18[var9 + this.numberOfFrequencies];
            }

            this.results.add(this.realZresiduals);
            this.results.add(this.imagZresiduals);
            if (this.weightsSet) {
                switch(this.dataEnteredTypePointer) {
                    case 0:
                        this.results.add(this.realVweights);
                        this.results.add(this.imagVweights);
                        break;
                    case 1:
                        this.results.add(this.voltageWeights);
                        this.results.add((Object)null);
                        break;
                    case 2:
                        this.results.add(this.voltageMagnitudeWeights);
                        this.results.add(this.voltagePhaseWeightsRad);
                        break;
                    case 3:
                        this.results.add(this.voltageMagnitudeWeights);
                        this.results.add(this.voltagePhaseWeightsDeg);
                        break;
                    case 4:
                        this.results.add(this.realZweights);
                        this.results.add(this.imagZweights);
                        break;
                    case 5:
                        this.results.add(this.impedanceWeights);
                        this.results.add((Object)null);
                        break;
                    case 6:
                        this.results.add(this.impedanceMagnitudeWeights);
                        this.results.add(this.impedancePhaseWeightsRad);
                        break;
                    case 7:
                        this.results.add(this.impedanceMagnitudeWeights);
                        this.results.add(this.impedancePhaseWeightsDeg);
                        break;
                    default:
                        this.results.add((Object)null);
                        this.results.add((Object)null);
                }

                this.results.add(this.realZweights);
                this.results.add(this.imagZweights);
            } else {
                for(var9 = 0; var9 < 4; ++var9) {
                    this.results.add((Object)null);
                }
            }

            for(var9 = 0; var9 < this.numberOfFrequencies; ++var9) {
                this.calculatedImpedances[var9] = new Complex(this.calculatedRealZ[var9], this.calculatedImagZ[var9]);
                this.calculatedImpedanceMagnitudes[var9] = this.calculatedImpedances[var9].abs();
                this.calculatedImpedancePhasesRad[var9] = this.calculatedImpedances[var9].arg();
                this.calculatedImpedancePhasesDeg[var9] = Math.toDegrees(this.calculatedImpedancePhasesRad[var9]);
            }

            this.results.add(this.calculatedImpedances);
            this.results.add(this.calculatedImpedanceMagnitudes);
            this.results.add(this.calculatedImpedancePhasesRad);
            this.results.add(this.calculatedImpedancePhasesDeg);
            if (this.appliedVoltageSet && this.referenceSet) {
                for(var9 = 0; var9 < this.numberOfFrequencies; ++var9) {
                    this.calculatedVoltages[var9] = this.appliedVoltage.times(this.calculatedImpedances[var9]).over(this.calculatedImpedances[var9].plus(this.referenceImpedance));
                    this.calculatedRealV[var9] = this.calculatedVoltages[var9].getReal();
                    this.calculatedImagV[var9] = this.calculatedVoltages[var9].getImag();
                    this.calculatedVoltageMagnitudes[var9] = this.calculatedVoltages[var9].abs();
                    this.calculatedVoltagePhasesRad[var9] = this.calculatedVoltages[var9].arg();
                    this.calculatedVoltagePhasesDeg[var9] = Math.toDegrees(this.calculatedVoltagePhasesRad[var9]);
                }

                this.results.add(this.calculatedVoltages);
                this.results.add(this.calculatedRealV);
                this.results.add(this.calculatedImagV);
                this.results.add(this.calculatedVoltageMagnitudes);
                this.results.add(this.calculatedVoltagePhasesRad);
                this.results.add(this.calculatedVoltagePhasesDeg);
            } else {
                for(var9 = 0; var9 < 6; ++var9) {
                    this.results.add((Object)null);
                }
            }

            this.regressionDone = true;
            return this.results;
        }
    }

    public double[] getBestEstimates() {
        if (!this.regressionDone) {
            this.regression();
        }

        return this.bestEstimates;
    }

    public double[] getStandardDeviations() {
        if (!this.regressionDone) {
            this.regression();
        }

        return this.standardDeviations;
    }

    public int getFirstNumberOfIterations() {
        return this.numberOfIterations1;
    }

    public int getSecondNumberOfIterations() {
        return this.numberOfIterations2;
    }

    public double getTolerance() {
        return this.tolerance;
    }

    public void setLinearPlot() {
        this.logOrLinear = false;
    }

    public void setLog10Plot() {
        this.logOrLinear = true;
    }

    private void calculateLineFrequencies() {
        double var1 = Fmath.minimum(this.frequencies);
        double var3 = Fmath.maximum(this.frequencies);
        double var5;
        if (this.logOrLinear) {
            var5 = Fmath.log10(var1);
            double var7 = Fmath.log10(var3);
            double var9 = (var7 - var5) / (double)(this.numberOfLineFrequencies - 1);
            this.lineFrequencies = new double[this.numberOfLineFrequencies];
            this.log10lineFrequencies = new double[this.numberOfLineFrequencies];
            this.log10lineFrequencies[0] = var5;
            this.log10lineFrequencies[this.numberOfLineFrequencies - 1] = var7;

            int var11;
            for(var11 = 1; var11 < this.numberOfLineFrequencies - 1; ++var11) {
                this.log10lineFrequencies[var11] = this.log10lineFrequencies[var11 - 1] + var9;
            }

            for(var11 = 0; var11 < this.numberOfLineFrequencies; ++var11) {
                this.lineFrequencies[var11] = Math.pow(10.0D, this.log10lineFrequencies[var11]);
            }
        } else {
            var5 = (var3 - var1) / (double)(this.numberOfLineFrequencies - 1);
            this.lineFrequencies = new double[this.numberOfLineFrequencies];
            this.lineFrequencies[0] = var1;
            this.log10lineFrequencies = new double[this.numberOfLineFrequencies];
            this.lineFrequencies[this.numberOfLineFrequencies - 1] = var3;

            int var12;
            for(var12 = 1; var12 < this.numberOfLineFrequencies - 1; ++var12) {
                this.lineFrequencies[var12] = this.lineFrequencies[var12 - 1] + var5;
            }

            for(var12 = 0; var12 < this.numberOfLineFrequencies; ++var12) {
                this.log10lineFrequencies[var12] = Fmath.log10(this.lineFrequencies[var12]);
            }
        }

    }

    private String[] dateAndTime() {
        Date var1 = new Date();
        String[] var2 = new String[]{DateFormat.getDateInstance().format(var1), DateFormat.getTimeInstance().format(var1)};
        return var2;
    }

    public ArrayList<Object> plotColeCole() {
        String[] var1 = this.dateAndTime();
        String var2 = "ImpedSpecRegression program:  Cole - Cole plot   [" + var1[0] + "    " + var1[1] + "]";
        String var3 = this.regressionTitle;
        if (!this.regressionDone) {
            this.regression();
        }

        this.calculateLineFrequencies();
        double[][] var4 = PlotGraph.data(2, this.numberOfLineFrequencies);

        int var5;
        for(var5 = 0; var5 < this.numberOfFrequencies; ++var5) {
            var4[0][var5] = this.realZ[this.numberOfFrequencies - var5 - 1];
            var4[1][var5] = -this.imagZ[this.numberOfFrequencies - var5 - 1];
        }

        if (this.userModelSet) {
            for(var5 = 0; var5 < this.numberOfLineFrequencies; ++var5) {
                var4[2][var5] = this.userModel.modelImpedance(this.bestEstimates, this.lineFrequencies[this.numberOfLineFrequencies - var5 - 1] * 2.0D * 3.141592653589793D).getReal();
                var4[3][var5] = -this.userModel.modelImpedance(this.bestEstimates, this.lineFrequencies[this.numberOfLineFrequencies - var5 - 1] * 2.0D * 3.141592653589793D).getImag();
            }
        } else {
            for(var5 = 0; var5 < this.numberOfLineFrequencies; ++var5) {
                var4[2][var5] = Impedance.modelImpedance(this.bestEstimates, this.lineFrequencies[this.numberOfLineFrequencies - var5 - 1] * 2.0D * 3.141592653589793D, this.modelNumber).getReal();
                var4[3][var5] = -Impedance.modelImpedance(this.bestEstimates, this.lineFrequencies[this.numberOfLineFrequencies - var5 - 1] * 2.0D * 3.141592653589793D, this.modelNumber).getImag();
            }
        }

        PlotGraph var8 = new PlotGraph(var4);
        int[] var6 = new int[]{0, 3};
        var8.setLine(var6);
        int[] var7 = new int[]{1, 0};
        var8.setPoint(var7);
        var8.setGraphTitle(var2);
        var8.setGraphTitle2(var3);
        var8.setXaxisLegend("Real[Impedance / ohms]");
        var8.setYaxisLegend("-Imag[Impedance / ohms]");
        var8.plot();
        return this.results;
    }

    public ArrayList<Object> plotImpedanceMagnitudes() {
        String[] var1 = this.dateAndTime();
        String var2 = "ImpedSpecRegression program:  Impedance magnitude versus frequency plot   [" + var1[0] + "    " + var1[1] + "]";
        String var3 = this.regressionTitle;
        if (!this.regressionDone) {
            this.regression();
        }

        this.calculateLineFrequencies();
        double[][] var4 = PlotGraph.data(2, this.numberOfLineFrequencies);
        int var5;
        if (this.logOrLinear) {
            for(var5 = 0; var5 < this.numberOfFrequencies; ++var5) {
                var4[0][var5] = this.log10frequencies[var5];
                var4[1][var5] = this.impedanceMagnitudes[var5];
            }
        } else {
            for(var5 = 0; var5 < this.numberOfFrequencies; ++var5) {
                var4[0][var5] = this.frequencies[var5];
                var4[1][var5] = this.impedanceMagnitudes[var5];
            }
        }

        Complex var6;
        if (this.logOrLinear) {
            if (this.userModelSet) {
                for(var5 = 0; var5 < this.numberOfLineFrequencies; ++var5) {
                    var4[2][var5] = this.log10lineFrequencies[var5];
                    var6 = this.userModel.modelImpedance(this.bestEstimates, this.lineFrequencies[var5] * 2.0D * 3.141592653589793D);
                    var4[3][var5] = var6.abs();
                }
            } else {
                for(var5 = 0; var5 < this.numberOfLineFrequencies; ++var5) {
                    var4[2][var5] = this.log10lineFrequencies[var5];
                    var6 = Impedance.modelImpedance(this.bestEstimates, this.lineFrequencies[var5] * 2.0D * 3.141592653589793D, this.modelNumber);
                    var4[3][var5] = var6.abs();
                }
            }
        } else if (this.userModelSet) {
            for(var5 = 0; var5 < this.numberOfLineFrequencies; ++var5) {
                var4[2][var5] = this.lineFrequencies[var5];
                var6 = this.userModel.modelImpedance(this.bestEstimates, this.lineFrequencies[var5] * 2.0D * 3.141592653589793D);
                var4[3][var5] = var6.abs();
            }
        } else {
            for(var5 = 0; var5 < this.numberOfLineFrequencies; ++var5) {
                var4[2][var5] = this.lineFrequencies[var5];
                var6 = Impedance.modelImpedance(this.bestEstimates, this.lineFrequencies[var5] * 2.0D * 3.141592653589793D, this.modelNumber);
                var4[3][var5] = var6.abs();
            }
        }

        PlotGraph var9 = new PlotGraph(var4);
        int[] var8 = new int[]{0, 3};
        var9.setLine(var8);
        int[] var7 = new int[]{1, 0};
        var9.setPoint(var7);
        var9.setGraphTitle(var2);
        var9.setGraphTitle2(var3);
        if (this.logOrLinear) {
            var9.setXaxisLegend("Log10[Frequency / Hz]");
        } else {
            var9.setXaxisLegend("Frequency / Hz");
        }

        var9.setYaxisLegend("Impedance Magnitude");
        var9.plot();
        return this.results;
    }

    public ArrayList<Object> plotImpedancePhases() {
        String[] var1 = this.dateAndTime();
        String var2 = "ImpedSpecRegression program:  Impedance phase versus frequency plot   [" + var1[0] + "    " + var1[1] + "]";
        String var3 = this.regressionTitle;
        if (!this.regressionDone) {
            this.regression();
        }

        this.calculateLineFrequencies();
        double[][] var4 = PlotGraph.data(2, this.numberOfLineFrequencies);
        int var5;
        if (this.logOrLinear) {
            for(var5 = 0; var5 < this.numberOfFrequencies; ++var5) {
                var4[0][var5] = this.log10frequencies[var5];
                var4[1][var5] = this.impedancePhasesDeg[var5];
            }
        } else {
            for(var5 = 0; var5 < this.numberOfFrequencies; ++var5) {
                var4[0][var5] = this.frequencies[var5];
                var4[1][var5] = this.impedancePhasesDeg[var5];
            }
        }

        Complex var6;
        if (this.logOrLinear) {
            if (this.userModelSet) {
                for(var5 = 0; var5 < this.numberOfLineFrequencies; ++var5) {
                    var4[2][var5] = this.log10lineFrequencies[var5];
                    var6 = this.userModel.modelImpedance(this.bestEstimates, this.lineFrequencies[var5] * 2.0D * 3.141592653589793D);
                    var4[3][var5] = Math.toDegrees(var6.arg());
                }
            } else {
                for(var5 = 0; var5 < this.numberOfLineFrequencies; ++var5) {
                    var4[2][var5] = this.log10lineFrequencies[var5];
                    var6 = Impedance.modelImpedance(this.bestEstimates, this.lineFrequencies[var5] * 2.0D * 3.141592653589793D, this.modelNumber);
                    var4[3][var5] = Math.toDegrees(var6.arg());
                }
            }
        } else if (this.userModelSet) {
            for(var5 = 0; var5 < this.numberOfLineFrequencies; ++var5) {
                var4[2][var5] = this.lineFrequencies[var5];
                var6 = this.userModel.modelImpedance(this.bestEstimates, this.lineFrequencies[var5] * 2.0D * 3.141592653589793D);
                var4[3][var5] = Math.toDegrees(var6.arg());
            }
        } else {
            for(var5 = 0; var5 < this.numberOfLineFrequencies; ++var5) {
                var4[2][var5] = this.lineFrequencies[var5];
                var6 = Impedance.modelImpedance(this.bestEstimates, this.lineFrequencies[var5] * 2.0D * 3.141592653589793D, this.modelNumber);
                var4[3][var5] = Math.toDegrees(var6.arg());
            }
        }

        PlotGraph var9 = new PlotGraph(var4);
        int[] var8 = new int[]{0, 3};
        var9.setLine(var8);
        int[] var7 = new int[]{1, 0};
        var9.setPoint(var7);
        var9.setGraphTitle(var2);
        var9.setGraphTitle2(var3);
        if (this.logOrLinear) {
            var9.setXaxisLegend("Log10[Frequency / Hz]");
        } else {
            var9.setXaxisLegend("Frequency / Hz");
        }

        var9.setYaxisLegend("Impedance Phase / degrees");
        var9.plot();
        return this.results;
    }

    public ArrayList<Object> plotVoltageMagnitudes() {
        if (!this.regressionDone) {
            this.regression();
        }

        if (this.referenceSet && this.appliedVoltageSet) {
            String[] var1 = this.dateAndTime();
            String var2 = "ImpedSpecRegression program:  Voltage magnitude versus frequency plot   [" + var1[0] + "    " + var1[1] + "]";
            String var3 = this.regressionTitle;
            this.calculateLineFrequencies();
            double[][] var4 = PlotGraph.data(2, this.numberOfLineFrequencies);
            int var5;
            if (this.logOrLinear) {
                for(var5 = 0; var5 < this.numberOfFrequencies; ++var5) {
                    var4[0][var5] = this.log10frequencies[var5];
                    var4[1][var5] = this.voltageMagnitudes[var5];
                }
            } else {
                for(var5 = 0; var5 < this.numberOfFrequencies; ++var5) {
                    var4[0][var5] = this.frequencies[var5];
                    var4[1][var5] = this.voltageMagnitudes[var5];
                }
            }

            Complex var6;
            Complex var7;
            if (this.logOrLinear) {
                if (this.userModelSet) {
                    for(var5 = 0; var5 < this.numberOfLineFrequencies; ++var5) {
                        var4[2][var5] = this.log10lineFrequencies[var5];
                        var6 = this.userModel.modelImpedance(this.bestEstimates, this.lineFrequencies[var5] * 2.0D * 3.141592653589793D);
                        var7 = var6.times(this.appliedVoltage).over(this.referenceImpedance.plus(var6));
                        var4[3][var5] = var7.abs();
                    }
                } else {
                    for(var5 = 0; var5 < this.numberOfLineFrequencies; ++var5) {
                        var4[2][var5] = this.log10lineFrequencies[var5];
                        var6 = Impedance.modelImpedance(this.bestEstimates, this.lineFrequencies[var5] * 2.0D * 3.141592653589793D, this.modelNumber);
                        var7 = var6.times(this.appliedVoltage).over(this.referenceImpedance.plus(var6));
                        var4[3][var5] = var7.abs();
                    }
                }
            } else if (this.userModelSet) {
                for(var5 = 0; var5 < this.numberOfLineFrequencies; ++var5) {
                    var4[2][var5] = this.lineFrequencies[var5];
                    var6 = this.userModel.modelImpedance(this.bestEstimates, this.lineFrequencies[var5] * 2.0D * 3.141592653589793D);
                    var7 = var6.times(this.appliedVoltage).over(this.referenceImpedance.plus(var6));
                    var4[3][var5] = var7.abs();
                }
            } else {
                for(var5 = 0; var5 < this.numberOfLineFrequencies; ++var5) {
                    var4[2][var5] = this.lineFrequencies[var5];
                    var6 = Impedance.modelImpedance(this.bestEstimates, this.lineFrequencies[var5] * 2.0D * 3.141592653589793D, this.modelNumber);
                    var7 = var6.times(this.appliedVoltage).over(this.referenceImpedance.plus(var6));
                    var4[3][var5] = var7.abs();
                }
            }

            PlotGraph var10 = new PlotGraph(var4);
            int[] var8 = new int[]{0, 3};
            var10.setLine(var8);
            int[] var9 = new int[]{1, 0};
            var10.setPoint(var9);
            var10.setGraphTitle(var2);
            var10.setGraphTitle2(var3);
            if (this.logOrLinear) {
                var10.setXaxisLegend("Log10[Frequency / Hz]");
            } else {
                var10.setXaxisLegend("Frequency / Hz");
            }

            var10.setYaxisLegend("Voltage Magnitude");
            var10.plot();
        } else {
            System.out.println("The voltage magnitudes cannot be plotted as no reference impedance or applied voltage has been entered");
        }

        return this.results;
    }

    public ArrayList<Object> plotVoltagePhases() {
        if (!this.regressionDone) {
            this.regression();
        }

        if (this.referenceSet && this.appliedVoltageSet) {
            String[] var1 = this.dateAndTime();
            String var2 = "ImpedSpecRegression program:  Voltage phase versus frequency plot   [" + var1[0] + "    " + var1[1] + "]";
            String var3 = this.regressionTitle;
            this.calculateLineFrequencies();
            double[][] var4 = PlotGraph.data(2, this.numberOfLineFrequencies);
            int var5;
            if (this.logOrLinear) {
                for(var5 = 0; var5 < this.numberOfFrequencies; ++var5) {
                    var4[0][var5] = this.log10frequencies[var5];
                    var4[1][var5] = this.voltagePhasesDeg[var5];
                }
            } else {
                for(var5 = 0; var5 < this.numberOfFrequencies; ++var5) {
                    var4[0][var5] = this.frequencies[var5];
                    var4[1][var5] = this.voltagePhasesDeg[var5];
                }
            }

            Complex var6;
            Complex var7;
            if (this.logOrLinear) {
                if (this.userModelSet) {
                    for(var5 = 0; var5 < this.numberOfLineFrequencies; ++var5) {
                        var4[2][var5] = this.log10lineFrequencies[var5];
                        var6 = this.userModel.modelImpedance(this.bestEstimates, this.lineFrequencies[var5] * 2.0D * 3.141592653589793D);
                        var7 = var6.times(this.appliedVoltage).over(this.referenceImpedance.plus(var6));
                        var4[3][var5] = Math.toDegrees(var7.arg());
                    }
                } else {
                    for(var5 = 0; var5 < this.numberOfLineFrequencies; ++var5) {
                        var4[2][var5] = this.log10lineFrequencies[var5];
                        var6 = Impedance.modelImpedance(this.bestEstimates, this.lineFrequencies[var5] * 2.0D * 3.141592653589793D, this.modelNumber);
                        var7 = var6.times(this.appliedVoltage).over(this.referenceImpedance.plus(var6));
                        var4[3][var5] = Math.toDegrees(var7.arg());
                    }
                }
            } else if (this.userModelSet) {
                for(var5 = 0; var5 < this.numberOfLineFrequencies; ++var5) {
                    var4[2][var5] = this.lineFrequencies[var5];
                    var6 = this.userModel.modelImpedance(this.bestEstimates, this.lineFrequencies[var5] * 2.0D * 3.141592653589793D);
                    var7 = var6.times(this.appliedVoltage).over(this.referenceImpedance.plus(var6));
                    var4[3][var5] = Math.toDegrees(var7.arg());
                }
            } else {
                for(var5 = 0; var5 < this.numberOfLineFrequencies; ++var5) {
                    var4[2][var5] = this.lineFrequencies[var5];
                    var6 = Impedance.modelImpedance(this.bestEstimates, this.lineFrequencies[var5] * 2.0D * 3.141592653589793D, this.modelNumber);
                    var7 = var6.times(this.appliedVoltage).over(this.referenceImpedance.plus(var6));
                    var4[3][var5] = Math.toDegrees(var7.arg());
                }
            }

            PlotGraph var10 = new PlotGraph(var4);
            int[] var8 = new int[]{0, 3};
            var10.setLine(var8);
            int[] var9 = new int[]{1, 0};
            var10.setPoint(var9);
            var10.setGraphTitle(var2);
            var10.setGraphTitle2(var3);
            if (this.logOrLinear) {
                var10.setXaxisLegend("Log10[Frequency / Hz]");
            } else {
                var10.setXaxisLegend("Frequency / Hz");
            }

            var10.setYaxisLegend("Voltage Phases / degrees");
            var10.plot();
        } else {
            System.out.println("The voltage magnitudes cannot be plotted as no reference impedance or applied voltage has been entered");
        }

        return this.results;
    }

    public ArrayList<Object> printToTextFile() {
        String var1 = "ImpedSpecRegressionOutput.txt";
        this.fileType = true;
        return this.printToTextFile(var1);
    }

    public ArrayList<Object> printToTextFile(String var1) {
        if (!this.regressionDone) {
            this.regression();
        }

        byte var2 = 11;
        byte var3 = 4;
        var1 = var1.trim();
        int var4 = var1.indexOf(46);
        if (var4 == -1) {
            var1 = var1 + ".txt";
        }

        FileOutput var5 = null;
        if (this.fileType) {
            var5 = new FileOutput(var1, 'n');
        } else {
            var5 = new FileOutput(var1);
        }

        var5.println("ImpedSpecRegression Program Output File:  " + this.regressionTitle);
        var5.dateAndTimeln(var1);
        var5.println();
        if (this.modelSet) {
            var5.println("Circuit - model number " + this.modelNumber);
        } else {
            var5.println("Circuit supplied by the user");
        }

        var5.println();
        var5.println("Circuit Parameters");
        var5.println("Best Estimates");
        var5.print("Parameter", var2);
        var5.print("Best", var2);
        var5.print("Standard", var2);
        var5.print("Coeff. of", var2);
        var5.print("Pre-", var2);
        var5.println("Post-");
        var5.print("   ", var2);
        var5.print("estimate", var2);
        var5.print("deviation", var2);
        var5.print("variation", var2);
        var5.print("gradient", var2);
        var5.println("gradient");

        int var6;
        for(var6 = 0; var6 < this.numberOfParameters; ++var6) {
            var5.print(this.parameterSymbols[var6], var2);
            var5.print(Fmath.truncate(this.bestEstimates[var6], var3), var2);
            var5.print(Fmath.truncate(this.standardDeviations[var6], var3), var2);
            var5.print(Fmath.truncate(this.coefficientsOfVariation[var6], var3), var2);
            var5.print(Fmath.truncate(this.preMinimumGradients[var6], var3), var2);
            var5.println(Fmath.truncate(this.postMinimumGradients[var6], var3));
        }

        var5.println();
        var5.println("Initial Estimates");
        var5.print("Parameter", var2);
        var5.print("Initial", var2);
        var5.println("initial");
        var5.print("   ", var2);
        var5.print("estimate", var2);
        var5.println("step size");

        for(var6 = 0; var6 < this.numberOfParameters; ++var6) {
            var5.print(this.parameterSymbols[var6], var2);
            var5.print(Fmath.truncate(this.initialEstimates[var6], var3), var2);
            var5.println(Fmath.truncate(this.initialSteps[var6], var3));
        }

        var5.println();
        var5.println("Sum of squares of the Real[Z] and Imag[Z] residuals:         " + Fmath.truncate(this.sumOfSquares, var3));
        var5.println("Reduced sum of squares of the Real[Z] and Imag[Z] residuals: " + Fmath.truncate(this.sumOfSquares / (double)this.degreesOfFreedom, var3));
        var5.println("Degrees of freedom: " + this.degreesOfFreedom);
        if (this.weightsSet) {
            var5.println("Chi square:         " + Fmath.truncate(this.chiSquare, var3));
            var5.println("Reduced chi square: " + Fmath.truncate(this.reducedChiSquare, var3));
        }

        var5.println("Number of iterations taken in the first regression:      " + this.numberOfIterations1);
        var5.println("Number of iterations taken in the second regression:     " + this.numberOfIterations2);
        var5.println("Maximum number of iterations allowed in each regression: " + this.maximumIterations);
        var5.println();
        if (this.appliedVoltageSet) {
            var5.println("Applied voltage: " + this.appliedVoltage.getReal());
        }

        if (this.referenceSet) {
            var5.println("Reference impedance: " + this.referenceImpedance);
        }

        var5.println();
        var2 = 14;
        var5.println("Fitted and entered data [frequencies, calculated impedances, data as entered]");
        var5.print("Entered data type:  ");
        var5.println(this.dataEnteredType[this.dataEnteredTypePointer]);
        var5.println();
        var5.print("Frequency", var2);
        var5.print("Experimental", var2);
        var5.print("Calculated", var2);
        var5.print("Experimental", var2);
        var5.print("Calculated", var2);
        switch(this.dataEnteredTypePointer) {
            case 0:
                var5.print("Real", var2);
                var5.print("Imag", var2);
                break;
            case 1:
                var5.print("Complex", var2);
                break;
            case 2:
                var5.print("Magnitude", var2);
                var5.print("Phase (rad)", var2);
                break;
            case 3:
                var5.print("Magnitude", var2);
                var5.print("Phase (deg)", var2);
                break;
            case 4:
                var5.print("Real", var2);
                var5.print("Imag", var2);
                break;
            case 5:
                var5.print("Complex", var2);
                break;
            case 6:
                var5.print("Magnitude", var2);
                var5.print("Phase (rad)", var2);
                break;
            case 7:
                var5.print("Magnitude", var2);
                var5.print("Phase (deg)", var2);
        }

        var5.println();
        var5.print("Frequency", var2);
        var5.print("Real[Z]", var2);
        var5.print("Real[Z]", var2);
        var5.print("Imag[Z]", var2);
        var5.print("Imag[Z]", var2);
        switch(this.dataEnteredTypePointer) {
            case 0:
                var5.print("[voltage]", var2);
                var5.print("[voltage]", var2);
                break;
            case 1:
                var5.print("voltage", var2);
                break;
            case 2:
                var5.print("[voltage]", var2);
                var5.print("[voltage]", var2);
                break;
            case 3:
                var5.print("[voltage]", var2);
                var5.print("[voltage]", var2);
                break;
            case 4:
                var5.print("[impedance]", var2);
                var5.print("[impedance]", var2);
                break;
            case 5:
                var5.print("impedance", var2);
                break;
            case 6:
                var5.print("[impedance]", var2);
                var5.print("[impedance]", var2);
                break;
            case 7:
                var5.print("[impedance]", var2);
                var5.print("[impedance]", var2);
        }

        var5.println();

        for(var6 = 0; var6 < this.numberOfFrequencies; ++var6) {
            var5.print(Fmath.truncate(this.frequencies[var6], var3), var2);
            var5.print(Fmath.truncate(this.realZ[var6], var3), var2);
            var5.print(Fmath.truncate(this.calculatedRealZ[var6], var3), var2);
            var5.print(Fmath.truncate(this.imagZ[var6], var3), var2);
            var5.print(Fmath.truncate(this.calculatedImagZ[var6], var3), var2);
            switch(this.dataEnteredTypePointer) {
                case 0:
                    var5.print(Fmath.truncate(this.realV[var6], var3), var2);
                    var5.print(Fmath.truncate(this.imagV[var6], var3), var2);
                    break;
                case 1:
                    var5.print(Complex.truncate(this.voltages[var6], var3), var2);
                    break;
                case 2:
                    var5.print(Fmath.truncate(this.voltageMagnitudes[var6], var3), var2);
                    var5.print(Fmath.truncate(this.voltagePhasesRad[var6], var3), var2);
                    break;
                case 3:
                    var5.print(Fmath.truncate(this.voltageMagnitudes[var6], var3), var2);
                    var5.print(Fmath.truncate(this.voltagePhasesDeg[var6], var3), var2);
                    break;
                case 4:
                    var5.print(Fmath.truncate(this.realZ[var6], var3), var2);
                    var5.print(Fmath.truncate(this.imagZ[var6], var3), var2);
                    break;
                case 5:
                    var5.print(Complex.truncate(this.impedances[var6], var3), var2);
                    break;
                case 6:
                    var5.print(Fmath.truncate(this.impedanceMagnitudes[var6], var3), var2);
                    var5.print(Fmath.truncate(this.impedancePhasesRad[var6], var3), var2);
                    break;
                case 7:
                    var5.print(Fmath.truncate(this.impedanceMagnitudes[var6], var3), var2);
                    var5.print(Fmath.truncate(this.impedancePhasesDeg[var6], var3), var2);
            }

            var5.println();
        }

        var5.close();
        return this.results;
    }

    public ArrayList<Object> printToExcelFile() {
        String var1 = "ImpedSpecRegressionOutput.txt";
        this.fileType = true;
        return this.printToExcelFile(var1);
    }

    public ArrayList<Object> printToExcelFile(String var1) {
        if (!this.regressionDone) {
            this.regression();
        }

        byte var2 = 11;
        byte var3 = 4;
        var1 = var1.trim();
        int var4 = var1.indexOf(46);
        if (var4 == -1) {
            var1 = var1 + ".xls";
        } else {
            var1 = var1.substring(0, var4) + ".xls";
        }

        FileOutput var5 = null;
        if (this.fileType) {
            var5 = new FileOutput(var1, 'n');
        } else {
            var5 = new FileOutput(var1);
        }

        var5.println("ImpedSpecRegression Program Output File:  " + this.regressionTitle);
        var5.dateAndTimeln(var1);
        var5.println();
        if (this.modelSet) {
            var5.println("Circuit - model number " + this.modelNumber);
        } else {
            var5.println("Circuit supplied by the user");
        }

        var5.println();
        var5.println("Circuit Parameters");
        var5.println("Best Estimates");
        var5.printtab("Parameter", var2);
        var5.printtab("Best", var2);
        var5.printtab("Standard", var2);
        var5.printtab("Coeff. of", var2);
        var5.printtab("Pre-", var2);
        var5.println("Post-");
        var5.printtab("   ", var2);
        var5.printtab("estimate", var2);
        var5.printtab("deviation", var2);
        var5.printtab("variation", var2);
        var5.printtab("gradient", var2);
        var5.println("gradient");

        int var6;
        for(var6 = 0; var6 < this.numberOfParameters; ++var6) {
            var5.printtab(this.parameterSymbols[var6], var2);
            var5.printtab(Fmath.truncate(this.bestEstimates[var6], var3), var2);
            var5.printtab(Fmath.truncate(this.standardDeviations[var6], var3), var2);
            var5.printtab(Fmath.truncate(this.coefficientsOfVariation[var6], var3), var2);
            var5.printtab(Fmath.truncate(this.preMinimumGradients[var6], var3), var2);
            var5.println(Fmath.truncate(this.postMinimumGradients[var6], var3));
        }

        var5.println();
        var5.println("Initial Estimates");
        var5.printtab("Parameter", var2);
        var5.printtab("Initial", var2);
        var5.println("initial");
        var5.printtab("   ", var2);
        var5.printtab("estimate", var2);
        var5.println("step size");

        for(var6 = 0; var6 < this.numberOfParameters; ++var6) {
            var5.printtab(this.parameterSymbols[var6], var2);
            var5.printtab(Fmath.truncate(this.initialEstimates[var6], var3), var2);
            var5.println(Fmath.truncate(this.initialSteps[var6], var3));
        }

        var5.println();
        var5.println("Sum of squares of the Real[Z] and Imag[z] residuals:         " + Fmath.truncate(this.sumOfSquares, var3));
        var5.println("Reduced sum of squares of the Real[Z] and Imag[z] residuals: " + Fmath.truncate(this.sumOfSquares / (double)this.degreesOfFreedom, var3));
        var5.println("Degrees of freedom: " + this.degreesOfFreedom);
        if (this.weightsSet) {
            var5.println("Chi square:         " + Fmath.truncate(this.chiSquare, var3));
            var5.println("Reduced chi square: " + Fmath.truncate(this.reducedChiSquare, var3));
        }

        var5.println("Number of iterations taken in the first regression:      " + this.numberOfIterations1);
        var5.println("Number of iterations taken in the second regression:     " + this.numberOfIterations2);
        var5.println("Maximum number of iterations allowed in each regression: " + this.maximumIterations);
        var5.println();
        var2 = 14;
        var5.println("Fitted and entered data [frequencies, calculated impedances, data as entered]");
        var5.print("Entered data type:  ");
        var5.println(this.dataEnteredType[this.dataEnteredTypePointer]);
        var5.println();
        var5.printtab("Frequency", var2);
        var5.printtab("Experimental", var2);
        var5.printtab("Calculated", var2);
        var5.printtab("Experimental", var2);
        var5.printtab("Calculated", var2);
        switch(this.dataEnteredTypePointer) {
            case 0:
                var5.printtab("Real", var2);
                var5.printtab("Imag", var2);
                break;
            case 1:
                var5.printtab("Complex", var2);
                break;
            case 2:
                var5.printtab("Magnitude", var2);
                var5.printtab("Phase (rad)", var2);
                break;
            case 3:
                var5.printtab("Magnitude", var2);
                var5.printtab("Phase (deg)", var2);
                break;
            case 4:
                var5.printtab("Real", var2);
                var5.printtab("Imag", var2);
                break;
            case 5:
                var5.printtab("Complex", var2);
                break;
            case 6:
                var5.printtab("Magnitude", var2);
                var5.printtab("Phase (rad)", var2);
                break;
            case 7:
                var5.printtab("Magnitude", var2);
                var5.printtab("Phase (deg)", var2);
        }

        var5.println();
        var5.printtab("Frequency", var2);
        var5.printtab("Real[Z]", var2);
        var5.printtab("Real[Z]", var2);
        var5.printtab("Imag[Z]", var2);
        var5.printtab("Imag[Z]", var2);
        switch(this.dataEnteredTypePointer) {
            case 0:
                var5.printtab("[voltage]", var2);
                var5.printtab("[voltage]", var2);
                break;
            case 1:
                var5.printtab("voltage", var2);
                break;
            case 2:
                var5.printtab("[voltage]", var2);
                var5.printtab("[voltage]", var2);
                break;
            case 3:
                var5.printtab("[voltage]", var2);
                var5.printtab("[voltage]", var2);
                break;
            case 4:
                var5.printtab("[impedance]", var2);
                var5.printtab("[impedance]", var2);
                break;
            case 5:
                var5.printtab("impedance", var2);
                break;
            case 6:
                var5.printtab("[impedance]", var2);
                var5.printtab("[impedance]", var2);
                break;
            case 7:
                var5.printtab("[impedance]", var2);
                var5.printtab("[impedance]", var2);
        }

        var5.println();

        for(var6 = 0; var6 < this.numberOfFrequencies; ++var6) {
            var5.printtab(Fmath.truncate(this.frequencies[var6], var3), var2);
            var5.printtab(Fmath.truncate(this.realZ[var6], var3), var2);
            var5.printtab(Fmath.truncate(this.calculatedRealZ[var6], var3), var2);
            var5.printtab(Fmath.truncate(this.imagZ[var6], var3), var2);
            var5.printtab(Fmath.truncate(this.calculatedImagZ[var6], var3), var2);
            switch(this.dataEnteredTypePointer) {
                case 0:
                    var5.printtab(Fmath.truncate(this.realV[var6], var3), var2);
                    var5.printtab(Fmath.truncate(this.imagV[var6], var3), var2);
                    break;
                case 1:
                    var5.printtab(Complex.truncate(this.voltages[var6], var3), var2);
                    break;
                case 2:
                    var5.printtab(Fmath.truncate(this.voltageMagnitudes[var6], var3), var2);
                    var5.printtab(Fmath.truncate(this.voltagePhasesRad[var6], var3), var2);
                    break;
                case 3:
                    var5.printtab(Fmath.truncate(this.voltageMagnitudes[var6], var3), var2);
                    var5.printtab(Fmath.truncate(this.voltagePhasesDeg[var6], var3), var2);
                    break;
                case 4:
                    var5.printtab(Fmath.truncate(this.realZ[var6], var3), var2);
                    var5.printtab(Fmath.truncate(this.imagZ[var6], var3), var2);
                    break;
                case 5:
                    var5.printtab(Complex.truncate(this.impedances[var6], var3), var2);
                    break;
                case 6:
                    var5.printtab(Fmath.truncate(this.impedanceMagnitudes[var6], var3), var2);
                    var5.printtab(Fmath.truncate(this.impedancePhasesRad[var6], var3), var2);
                    break;
                case 7:
                    var5.printtab(Fmath.truncate(this.impedanceMagnitudes[var6], var3), var2);
                    var5.printtab(Fmath.truncate(this.impedancePhasesDeg[var6], var3), var2);
            }

            var5.println();
        }

        var5.close();
        return this.results;
    }
}
