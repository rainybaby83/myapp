//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.optics;

import com.flanagan.analysis.Regression;
import com.flanagan.complex.Complex;
import com.flanagan.complex.ComplexMatrix;
import com.flanagan.math.Conv;
import com.flanagan.math.Fmath;
import com.flanagan.plot.PlotGraph;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Reflectivity {
    private int numberOfLayers = 0;
    private int numberOfInterfaces = 0;
    private Complex[][] refractiveIndices = (Complex[][])null;
    private Complex[] meanRefractiveIndices = null;
    private boolean refractSet = false;
    private boolean[] refractLayerSet = null;
    private boolean meanRefractUsed = false;
    private Complex[][] relativeMagneticPermeabilities = (Complex[][])null;
    private Complex[] meanRelativeMagneticPermeabilities = null;
    private boolean magneticSet = false;
    private boolean meanMagneticUsed = false;
    private double[][] absorptionCoefficients = (double[][])null;
    private boolean absorbSet = false;
    private double[] thicknesses = null;
    private double[] distances = null;
    private boolean thickSet = false;
    private boolean[] thickLayerSet = null;
    private int numberOfWavelengths = 0;
    private double[] wavelengths = null;
    private double[] frequencies = null;
    private double[] omega = null;
    private int[] origWavelIndices = null;
    private boolean wavelSet = false;
    private boolean freqSet = false;
    private boolean wavelNumberSet = false;
    private double[] incidentAngleDeg = null;
    private double[] incidentAngleRad = null;
    private int[] incidentAngleIndices = null;
    private int numberOfIncidentAngles = 0;
    private boolean incidentAngleSet = false;
    private String mode = null;
    private double eVectorAngleDeg = 0.0D;
    private double eVectorAngleRad = 0.0D;
    private double teFraction = 0.0D;
    private double tmFraction = 0.0D;
    private boolean modeSet = false;
    private Complex[][][] koVector = (Complex[][][])null;
    private Complex[][][] kVector = (Complex[][][])null;
    private Complex[][][] kxVector = (Complex[][][])null;
    private Complex[][][] kzVector = (Complex[][][])null;
    private double[][] reflectivities = (double[][])null;
    private double[][] transmissivities = (double[][])null;
    private double[][] powerLosses = (double[][])null;
    private Complex[][] reflectCoeffTE = (Complex[][])null;
    private Complex[][] reflectCoeffTM = (Complex[][])null;
    private Complex[][] transmitCoeffTE = (Complex[][])null;
    private Complex[][] transmitCoeffTM = (Complex[][])null;
    private double[][] reflectPhaseShiftRadTE = (double[][])null;
    private double[][] reflectPhaseShiftRadTM = (double[][])null;
    private double[][] transmitPhaseShiftRadTE = (double[][])null;
    private double[][] transmitPhaseShiftRadTM = (double[][])null;
    private double[][] reflectPhaseShiftDegTE = (double[][])null;
    private double[][] reflectPhaseShiftDegTM = (double[][])null;
    private double[][] transmitPhaseShiftDegTE = (double[][])null;
    private double[][] transmitPhaseShiftDegTM = (double[][])null;
    private double[][] evanescentFields = (double[][])null;
    private double fieldDistance = 1.0D / 0.0;
    private boolean fieldIntensityCalc = false;
    private double[][] penetrationDepths = (double[][])null;
    private double[][] transmitAnglesRad = (double[][])null;
    private double[][] transmitAnglesDeg = (double[][])null;
    private boolean singleReflectCalculated = false;
    private boolean angularReflectCalculated = false;
    private boolean wavelengthReflectCalculated = false;
    private boolean wavelengthAndAngularReflectCalculated = false;
    private double mu0overEps0 = 141925.72909094833D;
    private double impedance;
    private int wavelengthAxisOption;
    private double[] experimentalData;
    private double[] experimentalWeights;
    private double[] calculatedData;
    private int numberOfDataPoints;
    private boolean experimentalDataSet;
    private boolean weightingOption;
    private int numberOfEstimatedParameters;
    private int[] thicknessEstimateIndices;
    private int[] refractIndexRealEstimateIndices;
    private int[] refractIndexImagEstimateIndices;
    private int[] absorptionCoeffEstimateIndices;
    private int[] magneticPermRealEstimateIndices;
    private int[] magneticPermImagEstimateIndices;
    private boolean refractIndexImagEstimateSet;
    private boolean absorptionCoeffEstimateSet;
    private int thicknessEstimateNumber;
    private int refractIndexRealEstimateNumber;
    private int refractIndexImagEstimateNumber;
    private int absorptionCoeffEstimateNumber;
    private int magneticPermRealEstimateNumber;
    private int magneticPermImagEstimateNumber;
    private double fieldScalingFactor;
    public int regressionOption;
    public int degreesOfFreedom;

    public Reflectivity(int var1) {
        this.impedance = Math.sqrt(this.mu0overEps0);
        this.wavelengthAxisOption = 1;
        this.experimentalData = null;
        this.experimentalWeights = null;
        this.calculatedData = null;
        this.numberOfDataPoints = 0;
        this.experimentalDataSet = false;
        this.weightingOption = false;
        this.numberOfEstimatedParameters = 0;
        this.thicknessEstimateIndices = null;
        this.refractIndexRealEstimateIndices = null;
        this.refractIndexImagEstimateIndices = null;
        this.absorptionCoeffEstimateIndices = null;
        this.magneticPermRealEstimateIndices = null;
        this.magneticPermImagEstimateIndices = null;
        this.refractIndexImagEstimateSet = false;
        this.absorptionCoeffEstimateSet = false;
        this.thicknessEstimateNumber = 0;
        this.refractIndexRealEstimateNumber = 0;
        this.refractIndexImagEstimateNumber = 0;
        this.absorptionCoeffEstimateNumber = 0;
        this.magneticPermRealEstimateNumber = 0;
        this.magneticPermImagEstimateNumber = 0;
        this.fieldScalingFactor = 0.0D;
        this.regressionOption = 0;
        this.degreesOfFreedom = 0;
        this.numberOfLayers = var1;
        this.numberOfInterfaces = var1 - 1;
        if (var1 < 2) {
            throw new IllegalArgumentException("There must be at least two layers, i.e. at least one interface");
        } else {
            this.meanRelativeMagneticPermeabilities = Complex.oneDarray(this.numberOfLayers, 1.0D, 0.0D);
            this.meanRefractiveIndices = Complex.oneDarray(this.numberOfLayers);
            this.refractLayerSet = new boolean[this.numberOfLayers];

            int var2;
            for(var2 = 0; var2 < this.numberOfLayers; ++var2) {
                this.refractLayerSet[var2] = false;
            }

            this.thicknesses = new double[this.numberOfLayers];
            this.thicknesses[0] = -1.0D / 0.0;
            this.thicknesses[this.numberOfLayers - 1] = 1.0D / 0.0;
            this.thickLayerSet = new boolean[this.numberOfLayers];
            this.thickLayerSet[0] = true;

            for(var2 = 1; var2 < this.numberOfLayers - 2; ++var2) {
                this.thickLayerSet[var2] = false;
            }

            this.thickLayerSet[this.numberOfLayers - 1] = true;
            this.distances = new double[this.numberOfInterfaces];
        }
    }

    public void setMode(String var1) {
        if (!var1.equalsIgnoreCase("TE") && !var1.equalsIgnoreCase("transverse electric")) {
            if (!var1.equalsIgnoreCase("TM") && !var1.equalsIgnoreCase("transverse magnetic")) {
                if (!var1.equalsIgnoreCase("unpolarised") && !var1.equalsIgnoreCase("unpolarized") && !var1.equalsIgnoreCase("none")) {
                    throw new IllegalArgumentException("mode must be TE, TM or unpolarised; it cannot be " + var1);
                }

                this.mode = "unpolarised";
                this.teFraction = 0.5D;
                this.tmFraction = 0.5D;
                this.eVectorAngleDeg = 45.0D;
                this.eVectorAngleRad = 0.7853981633974483D;
            } else {
                this.mode = "TM";
                this.teFraction = 0.0D;
                this.tmFraction = 1.0D;
                this.eVectorAngleDeg = 90.0D;
                this.eVectorAngleRad = 1.5707963267948966D;
            }
        } else {
            this.mode = "TE";
            this.teFraction = 1.0D;
            this.tmFraction = 0.0D;
            this.eVectorAngleDeg = 0.0D;
            this.eVectorAngleRad = 0.0D;
        }

        this.modeSet = true;
    }

    public void setMode(double var1) {
        this.mode = "mixed";
        this.eVectorAngleDeg = var1;
        this.eVectorAngleRad = Math.toRadians(var1);
        this.teFraction = Math.sin(this.eVectorAngleRad);
        this.teFraction *= this.teFraction;
        this.tmFraction = 1.0D - this.teFraction;
        this.modeSet = true;
    }

    public double fractionInTEmode() {
        return this.teFraction;
    }

    public double fractionInTMmode() {
        return this.tmFraction;
    }

    public void setIncidentAngle(double var1) {
        double[] var3 = new double[]{var1};
        this.setIncidentAngle(var3);
    }

    public void setIncidentAngle(double[] var1) {
        this.numberOfIncidentAngles = var1.length;
        this.incidentAngleIndices = new int[this.numberOfIncidentAngles];
        this.incidentAngleDeg = new double[this.numberOfIncidentAngles];
        Fmath.selectionSort(var1, this.incidentAngleDeg, this.incidentAngleIndices);
        if (this.experimentalDataSet) {
            if (this.numberOfDataPoints != this.numberOfIncidentAngles) {
                throw new IllegalArgumentException("Number of experimental reflectivities " + this.numberOfDataPoints + " does not equal the number of incident angles " + this.numberOfIncidentAngles);
            }

            double[] var2 = Conv.copy(this.experimentalData);

            for(int var3 = 0; var3 < this.numberOfIncidentAngles; ++var3) {
                this.experimentalData[var3] = var2[this.incidentAngleIndices[var3]];
            }
        }

        this.incidentAngleRad = new double[this.numberOfIncidentAngles];

        for(int var4 = 0; var4 < this.numberOfIncidentAngles; ++var4) {
            this.incidentAngleRad[var4] = Math.toRadians(this.incidentAngleDeg[var4]);
        }

        this.incidentAngleSet = true;
    }

    public void setIncidentAngle(double var1, double var3, int var5) {
        this.numberOfIncidentAngles = var5;
        double var6 = (var3 - var1) / (double)(var5 - 1);
        double[] var8 = new double[var5];
        var8[0] = var1;

        for(int var9 = 1; var9 < var5 - 1; ++var9) {
            var8[var9] = var8[var9 - 1] + var6;
        }

        var8[var5 - 1] = var3;
        this.setIncidentAngle(var8);
    }

    public double[] getIncidentAngles() {
        return this.incidentAngleDeg;
    }

    public void setThicknesses(double[] var1) {
        int var2 = var1.length;
        if (var2 != this.numberOfLayers - 2) {
            throw new IllegalArgumentException("Number of thicknesses, " + var2 + ", does not match the number of layers minus the outer two semi-finite layers, " + (this.numberOfLayers - 2));
        } else {
            int var3;
            for(var3 = 1; var3 < this.numberOfLayers - 1; ++var3) {
                this.thicknesses[var3] = var1[var3 - 1];
            }

            this.distances[0] = 0.0D;

            for(var3 = 1; var3 < this.numberOfInterfaces; ++var3) {
                this.distances[var3] = this.distances[var3 - 1] + this.thicknesses[var3];
            }

            for(var3 = 1; var3 < this.numberOfLayers - 2; ++var3) {
                this.thickLayerSet[var3] = true;
            }

            this.thickSet = true;
        }
    }

    public void setThicknesses(double var1, int var3) {
        if (var3 >= 1 && var3 <= this.numberOfLayers) {
            this.thicknesses[var3 - 1] = var1;
            this.distances[0] = 0.0D;

            int var4;
            for(var4 = 1; var4 < this.numberOfInterfaces; ++var4) {
                this.distances[var4] = this.distances[var4 - 1] + this.thicknesses[var4];
            }

            this.thickLayerSet[var3 - 1] = true;
            var4 = 0;

            for(int var5 = 0; var5 < this.numberOfLayers - var5; ++var5) {
                if (this.thickLayerSet[var5]) {
                    ++var4;
                }
            }

            if (var4 == this.numberOfLayers) {
                this.thickSet = true;
            }

        } else {
            throw new IllegalArgumentException("Layer number, " + var3 + ", must be in the range 1 to " + this.numberOfLayers);
        }
    }

    public double[] getThicknesses() {
        return this.thicknesses;
    }

    public void setWavelength(double[] var1) {
        int var2 = var1.length;
        if (this.wavelNumberSet && var2 != this.numberOfWavelengths) {
            throw new IllegalArgumentException("The number of wavelengths entered, " + var2 + ", does not equal that previously set," + this.numberOfWavelengths);
        } else {
            this.numberOfWavelengths = var2;
            this.wavelengths = var1;
            this.wavelSet = true;
            if (!this.refractSet) {
                this.refractiveIndices = Complex.twoDarray(this.numberOfWavelengths, this.numberOfLayers);
            }

            int var3;
            if (!this.wavelNumberSet) {
                int var4;
                if (this.meanRefractUsed) {
                    var3 = 0;

                    while(true) {
                        if (var3 >= this.numberOfLayers) {
                            for(var3 = 0; var3 < this.numberOfLayers; ++var3) {
                                this.refractLayerSet[var3] = true;
                            }

                            this.refractSet = true;
                            break;
                        }

                        for(var4 = 0; var4 < this.numberOfWavelengths; ++var4) {
                            this.refractiveIndices[var4][var3] = this.meanRefractiveIndices[var3];
                        }

                        ++var3;
                    }
                }

                if (this.absorptionCoefficients == null) {
                    this.absorptionCoefficients = new double[this.numberOfWavelengths][this.numberOfLayers];
                } else {
                    for(var3 = 0; var3 < this.numberOfLayers; ++var3) {
                        for(var4 = 0; var4 < this.numberOfWavelengths; ++var4) {
                            if (this.refractiveIndices[var3][var4].getImag() == 0.0D) {
                                this.refractiveIndices[var4][var3].setImag(this.absorptionCoefficients[var4][var3] * this.wavelengths[var4] / 12.566370614359172D);
                            }
                        }
                    }
                }

                this.relativeMagneticPermeabilities = Complex.twoDarray(this.numberOfWavelengths, this.numberOfLayers);
                if (this.meanMagneticUsed) {
                    for(var3 = 0; var3 < this.numberOfLayers; ++var3) {
                        for(var4 = 0; var4 < this.numberOfWavelengths; ++var4) {
                            this.relativeMagneticPermeabilities[var4][var3] = this.meanRelativeMagneticPermeabilities[var3];
                        }
                    }

                    this.magneticSet = true;
                } else {
                    for(var3 = 0; var3 < this.numberOfLayers; ++var3) {
                        for(var4 = 0; var4 < this.numberOfWavelengths; ++var4) {
                            this.relativeMagneticPermeabilities[var4][var3] = Complex.plusOne();
                        }
                    }
                }
            }

            if (!this.freqSet) {
                this.frequencies = new double[this.numberOfWavelengths];

                for(var3 = 0; var3 < this.numberOfWavelengths; ++var3) {
                    this.frequencies[this.numberOfWavelengths - 1 - var3] = 2.99792458E8D / var1[var3];
                }
            }

            this.omega = new double[this.numberOfWavelengths];

            for(var3 = 0; var3 < this.numberOfWavelengths; ++var3) {
                this.omega[var3] = 6.283185307179586D * this.frequencies[var3];
            }

            this.wavelNumberSet = true;
        }
    }

    public void setFrequency(double[] var1) {
        int var2 = var1.length;
        if (this.wavelNumberSet && var2 != this.numberOfWavelengths) {
            throw new IllegalArgumentException("The number of frequencies entered, " + var2 + ", does not equal that previously set," + this.numberOfWavelengths);
        } else {
            this.frequencies = var1;
            this.freqSet = true;
            this.wavelengthAxisOption = 2;
            double[] var3 = new double[var2];

            for(int var4 = 0; var4 < var2; ++var4) {
                var3[var4] = 2.99792458E8D / this.frequencies[var2 - 1 - var4];
            }

            this.setWavelength(var3);
        }
    }

    public void setWavelength(double var1, double var3, int var5) {
        double var6 = (var3 - var1) / (double)(var5 - 1);
        double[] var8 = new double[var5];
        var8[0] = var1;

        for(int var9 = 1; var9 < var5 - 1; ++var9) {
            var8[var9] = var8[var9 - 1] + var6;
        }

        var8[var5 - 1] = var3;
        this.setWavelength(var8);
    }

    public void setFrequency(double var1, double var3, int var5) {
        double var6 = (var3 - var1) / (double)(var5 - 1);
        double[] var8 = new double[var5];
        var8[0] = var1;

        for(int var9 = 1; var9 < var5 - 1; ++var9) {
            var8[var9] = var8[var9 - 1] + var6;
        }

        var8[var5 - 1] = var3;
        this.setFrequency(var8);
    }

    public void setWavelength(double var1) {
        double[] var3 = new double[]{var1};
        this.setWavelength(var3);
    }

    public void setFrequency(double var1) {
        double[] var3 = new double[]{var1};
        this.setFrequency(var3);
    }

    public double[] getWavelengths() {
        return this.wavelengths;
    }

    public double[] getRadialFrequencies() {
        return this.omega;
    }

    private void sortWavelengths() {
        this.origWavelIndices = new int[this.numberOfWavelengths];

        for(int var1 = 0; var1 < this.numberOfWavelengths; this.origWavelIndices[var1] = var1++) {
            ;
        }

        if (this.numberOfWavelengths > 1) {
            boolean var9 = true;
            boolean var2 = false;
            int var3 = 1;

            while(var9) {
                if (this.wavelengths[var3] < this.wavelengths[var3 - 1]) {
                    var9 = false;
                    var2 = true;
                } else {
                    ++var3;
                    if (var3 >= this.numberOfWavelengths) {
                        var9 = false;
                    }
                }
            }

            if (var2) {
                ArrayList var4 = Fmath.selectSortArrayList(this.wavelengths);
                this.wavelengths = (double[])((double[])var4.get(1));
                this.origWavelIndices = (int[])((int[])var4.get(2));
                Complex[][] var5 = new Complex[this.numberOfWavelengths][this.numberOfLayers];

                int var6;
                int var7;
                for(var6 = 0; var6 < this.numberOfWavelengths; ++var6) {
                    for(var7 = 0; var7 < this.numberOfLayers; ++var7) {
                        var5[var6][var7] = this.refractiveIndices[this.origWavelIndices[var6]][var7];
                    }
                }

                this.refractiveIndices = Complex.copy(var5);

                for(var6 = 0; var6 < this.numberOfWavelengths; ++var6) {
                    for(var7 = 0; var7 < this.numberOfLayers; ++var7) {
                        var5[var6][var7] = this.relativeMagneticPermeabilities[this.origWavelIndices[var6]][var7];
                    }
                }

                this.relativeMagneticPermeabilities = Complex.copy(var5);
                double[][] var10 = new double[this.numberOfWavelengths][this.numberOfLayers];

                for(var7 = 0; var7 < this.numberOfWavelengths; ++var7) {
                    for(int var8 = 0; var8 < this.numberOfLayers; ++var8) {
                        var10[var7][var8] = this.absorptionCoefficients[this.origWavelIndices[var7]][var8];
                    }
                }

                this.absorptionCoefficients = var10;
            }
        }

    }

    public void setRefractiveIndices(Complex[][] var1) {
        int var2 = var1[0].length;
        if (var2 != this.numberOfLayers) {
            throw new IllegalArgumentException("Number of refractive indices layers, " + var2 + ", does not match the number of layers, " + this.numberOfLayers);
        } else {
            int var3 = var1.length;
            if (this.wavelSet && var3 != this.numberOfWavelengths) {
                throw new IllegalArgumentException("Number of refractive indices wavelength sets, " + var3 + ", does not match the number of wavelengths already set, " + this.numberOfWavelengths);
            } else {
                this.refractiveIndices = var1;

                int var4;
                for(var4 = 0; var4 < this.numberOfLayers; ++var4) {
                    this.refractLayerSet[var4] = true;
                }

                this.refractSet = true;
                this.wavelNumberSet = true;

                for(var4 = 0; var4 < this.numberOfLayers; ++var4) {
                    Complex var5 = Complex.zero();

                    for(int var6 = 0; var6 < this.numberOfWavelengths; ++var6) {
                        var5.plusEquals(this.refractiveIndices[var6][var4]);
                    }

                    this.meanRefractiveIndices[var4] = var5.over((double)this.numberOfWavelengths);
                }

                int var7;
                if (this.wavelSet && this.absorptionCoefficients != null) {
                    for(var4 = 0; var4 < this.numberOfLayers; ++var4) {
                        for(var7 = 0; var7 < this.numberOfWavelengths; ++var7) {
                            if (this.refractiveIndices[var7][var4].getImag() == 0.0D) {
                                this.refractiveIndices[var7][var4].setImag(this.absorptionCoefficients[var7][var4] * this.wavelengths[var4] / 12.566370614359172D);
                            }
                        }
                    }
                }

                if (!this.absorbSet) {
                    this.absorptionCoefficients = new double[this.numberOfWavelengths][this.numberOfLayers];
                }

                if (!this.magneticSet) {
                    if (this.meanMagneticUsed) {
                        for(var4 = 0; var4 < this.numberOfLayers; ++var4) {
                            for(var7 = 0; var7 < this.numberOfWavelengths; ++var7) {
                                this.relativeMagneticPermeabilities[var7][var4] = this.meanRelativeMagneticPermeabilities[var4];
                            }
                        }

                        this.magneticSet = true;
                    } else {
                        this.relativeMagneticPermeabilities = Complex.twoDarray(this.numberOfWavelengths, this.numberOfLayers, 1.0D, 0.0D);
                    }
                }

            }
        }
    }

    public void setRefractiveIndices(double[][] var1) {
        int var2 = var1[0].length;
        if (var2 != this.numberOfLayers) {
            throw new IllegalArgumentException("Number of refractive indices layers, " + var2 + ", does not match the number of layers, " + this.numberOfLayers);
        } else {
            int var3 = var1.length;
            if (this.wavelSet && var3 != this.numberOfWavelengths) {
                throw new IllegalArgumentException("Number of refractive indices wavelength sets, " + var3 + ", does not match the number of wavelengths already set, " + this.numberOfWavelengths);
            } else {
                Complex[][] var4 = Complex.twoDarray(var3, var2);

                for(int var5 = 0; var5 < var3; ++var5) {
                    for(int var6 = 0; var6 < var2; ++var6) {
                        var4[var5][var6].setReal(var1[var5][var6]);
                    }
                }

                this.setRefractiveIndices(var4);
            }
        }
    }

    public void setRefractiveIndices(Complex[] var1) {
        int var2 = var1.length;
        if (var2 != this.numberOfLayers) {
            throw new IllegalArgumentException("Number of refrative indices layers, " + var2 + ", does not match the number of layers, " + this.numberOfLayers);
        } else {
            this.meanRefractiveIndices = var1;
            this.meanRefractUsed = true;
            int var3;
            int var4;
            if (this.wavelNumberSet) {
                for(var3 = 0; var3 < this.numberOfLayers; ++var3) {
                    for(var4 = 0; var4 < this.numberOfWavelengths; ++var4) {
                        this.refractiveIndices[var4][var3] = this.meanRefractiveIndices[var3];
                    }
                }

                for(var3 = 0; var3 < this.numberOfLayers; ++var3) {
                    this.refractLayerSet[var3] = true;
                }

                this.refractSet = true;
            }

            if (this.absorptionCoefficients != null && this.wavelSet) {
                for(var3 = 0; var3 < this.numberOfLayers; ++var3) {
                    for(var4 = 0; var4 < this.numberOfWavelengths; ++var4) {
                        if (this.refractiveIndices[var4][var3].getImag() == 0.0D) {
                            this.refractiveIndices[var4][var3].setImag(this.absorptionCoefficients[var4][var3] * this.wavelengths[var4] / 12.566370614359172D);
                        }
                    }
                }
            }

            if (this.absorptionCoefficients == null) {
                this.absorptionCoefficients = new double[this.numberOfWavelengths][this.numberOfLayers];
            }

            if (!this.magneticSet) {
                if (!this.meanMagneticUsed) {
                    if (this.wavelNumberSet) {
                        this.relativeMagneticPermeabilities = Complex.twoDarray(this.numberOfWavelengths, this.numberOfLayers, 1.0D, 0.0D);
                    }
                } else {
                    this.relativeMagneticPermeabilities = Complex.twoDarray(this.numberOfWavelengths, this.numberOfLayers);

                    for(var3 = 0; var3 < this.numberOfLayers; ++var3) {
                        for(var4 = 0; var4 < this.numberOfWavelengths; ++var4) {
                            this.relativeMagneticPermeabilities[var4][var3] = this.meanRelativeMagneticPermeabilities[var3];
                        }
                    }

                    this.magneticSet = true;
                }
            }

        }
    }

    public void setRefractiveIndices(double[] var1) {
        int var2 = var1.length;
        if (var2 != this.numberOfLayers) {
            throw new IllegalArgumentException("Number of refrative indices, " + var2 + ", does not match the number of layers, " + this.numberOfLayers);
        } else {
            Complex[] var3 = Complex.oneDarray(var2);

            for(int var4 = 0; var4 < var2; ++var4) {
                var3[var4].setReal(var1[var4]);
            }

            this.setRefractiveIndices(var3);
        }
    }

    public void setRefractiveIndices(Complex[] var1, int var2) {
        if (var2 >= 0 && var2 <= this.numberOfLayers) {
            int var3 = var1.length;
            int var4;
            int var5;
            if (this.wavelNumberSet) {
                if (var3 != this.numberOfWavelengths) {
                    throw new IllegalArgumentException("The number of refractive index wavelength values, " + var3 + ", does not match the number of wavelengths already entered, " + this.numberOfWavelengths);
                }
            } else {
                this.numberOfWavelengths = var3;
                this.wavelNumberSet = true;
                this.refractiveIndices = Complex.twoDarray(this.numberOfLayers, this.numberOfWavelengths);
                if (this.meanRefractUsed) {
                    var4 = 0;

                    while(true) {
                        if (var4 >= this.numberOfLayers) {
                            for(var4 = 0; var4 < this.numberOfLayers; ++var4) {
                                this.refractLayerSet[var4] = true;
                            }

                            this.refractSet = true;
                            break;
                        }

                        for(var5 = 0; var5 < this.numberOfWavelengths; ++var5) {
                            this.refractiveIndices[var5][var4] = this.meanRefractiveIndices[var4];
                        }

                        ++var4;
                    }
                }

                this.relativeMagneticPermeabilities = Complex.twoDarray(this.numberOfWavelengths, this.numberOfLayers, 1.0D, 0.0D);
                if (this.meanMagneticUsed) {
                    for(var4 = 0; var4 < this.numberOfLayers; ++var4) {
                        for(var5 = 0; var5 < this.numberOfWavelengths; ++var5) {
                            this.relativeMagneticPermeabilities[var5][var4] = this.meanRelativeMagneticPermeabilities[var4];
                        }
                    }

                    this.magneticSet = true;
                }
            }

            --var2;
            this.refractiveIndices[var2] = var1;
            this.refractLayerSet[var2] = true;
            var4 = 0;

            for(var5 = 0; var5 < this.numberOfLayers; ++var5) {
                if (this.refractLayerSet[var5]) {
                    ++var4;
                }
            }

            if (var4 == this.numberOfLayers) {
                this.refractSet = true;
            }

            if (this.absorptionCoefficients != null) {
                for(var5 = 0; var5 < this.numberOfLayers; ++var5) {
                    for(int var6 = 0; var6 < this.numberOfWavelengths; ++var6) {
                        if (this.refractiveIndices[var6][var5].getImag() == 0.0D) {
                            this.refractiveIndices[var6][var5].setImag(this.absorptionCoefficients[var6][var5] * this.wavelengths[var6] / 12.566370614359172D);
                        }
                    }
                }
            }

            if (this.absorptionCoefficients == null) {
                this.absorptionCoefficients = new double[this.numberOfWavelengths][this.numberOfLayers];
            }

        } else {
            throw new IllegalArgumentException("Layer number, " + var2 + ", must be in the range 1 to " + this.numberOfLayers);
        }
    }

    public void setRefractiveIndices(double[] var1, int var2) {
        if (var2 >= 0 && var2 <= this.numberOfLayers) {
            int var3 = var1.length;
            if (this.wavelNumberSet && var3 != this.numberOfWavelengths) {
                throw new IllegalArgumentException("The number of refractive index wavelength values, " + var3 + ", does not match the number of wavelengths already entered, " + this.numberOfWavelengths);
            } else {
                Complex[] var4 = Complex.oneDarray(var3);

                for(int var5 = 0; var5 < var3; ++var5) {
                    var4[var5].setReal(var1[var5]);
                }

                this.setRefractiveIndices(var4, var2);
            }
        } else {
            throw new IllegalArgumentException("Layer number, " + var2 + ", must be in the range 1 to " + this.numberOfLayers);
        }
    }

    public void setRefractiveIndices(Complex var1, int var2) {
        if (this.wavelNumberSet) {
            Complex[] var3 = Complex.oneDarray(this.numberOfWavelengths);

            for(int var4 = 0; var4 < this.numberOfWavelengths; ++var4) {
                var3[var4] = var1;
            }

            this.setRefractiveIndices(var3, var2);
        } else {
            this.meanRefractiveIndices[var2 - 1] = var1;
            this.meanRefractUsed = true;
        }

    }

    public void setRefractiveIndices(double var1, int var3) {
        Complex var4 = new Complex(var1, 0.0D);
        this.setRefractiveIndices(var4, var3);
    }

    public Object getRefractiveIndices() {
        if (this.numberOfWavelengths == 1) {
            Complex[] var1 = this.refractiveIndices[0];
            return var1;
        } else {
            return this.refractiveIndices;
        }
    }

    public void setAbsorptionCoefficients(double[] var1) {
        int var2 = var1.length;
        if (var2 != this.numberOfLayers) {
            throw new IllegalArgumentException("Number of absorption coefficients sets, " + var2 + ", does not match the number of layers, " + this.numberOfLayers);
        } else {
            this.absorptionCoefficients = new double[1][var2];
            this.absorptionCoefficients[0] = var1;
            this.absorbSet = true;
            if (this.refractSet) {
                for(int var3 = 0; var3 < this.numberOfLayers; ++var3) {
                    if (this.refractiveIndices[0][var3].getImag() == 0.0D) {
                        this.refractiveIndices[0][var3].setImag(this.absorptionCoefficients[0][var3] * this.wavelengths[0] / 12.566370614359172D);
                    }
                }
            }

        }
    }

    public void setAbsorptionCoefficients(double[][] var1) {
        int var2 = var1[0].length;
        if (var2 != this.numberOfLayers) {
            throw new IllegalArgumentException("Number of absorption coefficients sets, " + var2 + ", does not match the number of layers, " + this.numberOfLayers);
        } else {
            int var3 = var1.length;
            if (this.wavelNumberSet && var3 != this.numberOfWavelengths) {
                throw new IllegalArgumentException("Number of absorption coefficients wavelengths, " + var3 + ", does not match the number of wavelengths already entered, " + this.numberOfWavelengths);
            } else {
                this.absorptionCoefficients = var1;
                this.absorbSet = true;
                if (this.refractSet && this.wavelSet) {
                    for(int var4 = 0; var4 < this.numberOfLayers; ++var4) {
                        for(int var5 = 0; var5 < this.numberOfWavelengths; ++var5) {
                            if (this.refractiveIndices[var5][var4].getImag() == 0.0D) {
                                this.refractiveIndices[var5][var4].setImag(var1[var5][var4] * this.wavelengths[var5] / 12.566370614359172D);
                            }
                        }
                    }
                }

            }
        }
    }

    public void setAbsorptionCoefficients(double[] var1, int var2) {
        int var3 = var1.length;
        if (this.wavelNumberSet) {
            if (var3 != this.numberOfWavelengths) {
                throw new IllegalArgumentException("Layer " + var2 + ": number of absorption coefficients wavelengths, " + var3 + ", does not match the number of wavelengths already entered, " + this.numberOfWavelengths);
            }
        } else {
            this.numberOfWavelengths = var3;
            this.refractiveIndices = Complex.twoDarray(this.numberOfWavelengths, this.numberOfLayers);
            this.absorptionCoefficients = new double[this.numberOfWavelengths][this.numberOfLayers];
        }

        --var2;
        this.absorptionCoefficients[var2] = var1;

        for(int var4 = 0; var4 < this.numberOfWavelengths; ++var4) {
            if (this.refractiveIndices[var4][var2].getImag() == 0.0D) {
                this.refractiveIndices[var4][var2].setImag(var1[var4] * this.wavelengths[var4] / 12.566370614359172D);
            }
        }

        this.absorbSet = true;
    }

    public void setAbsorptionCoefficients(double var1, int var3) {
        if (this.wavelNumberSet) {
            if (this.numberOfWavelengths != 1) {
                throw new IllegalArgumentException("Layer " + var3 + ": number of absorption coefficients wavelengths, " + 1 + ", does not match the number of wavelengths already entered, " + this.numberOfWavelengths);
            }
        } else {
            this.numberOfWavelengths = 1;
            this.refractiveIndices = Complex.twoDarray(this.numberOfWavelengths, this.numberOfLayers);
            this.absorptionCoefficients = new double[this.numberOfWavelengths][this.numberOfLayers];
        }

        --var3;
        this.absorptionCoefficients[0][var3] = var1;
        if (this.refractiveIndices[0][var3].getImag() == 0.0D) {
            this.refractiveIndices[0][var3].setImag(var1 * this.wavelengths[0] / 12.566370614359172D);
        }

        this.absorbSet = true;
    }

    public Object getAbsorptionCoefficients() {
        double[][] var1 = this.absorptionCoefficients;

        for(int var2 = 0; var2 < this.numberOfLayers; ++var2) {
            for(int var3 = 0; var3 < this.numberOfWavelengths; ++var3) {
                var1[var2][var3] = 12.566370614359172D * this.wavelengths[var3] * this.refractiveIndices[var2][var3].getImag();
            }
        }

        if (this.numberOfWavelengths == 1) {
            double[] var4 = var1[0];
            return var4;
        } else {
            return var1;
        }
    }

    public void setRelativeMagneticPermeabilities(Complex[][] var1) {
        int var2 = var1[0].length;
        if (var2 != this.numberOfLayers) {
            throw new IllegalArgumentException("Number of relative magnetic permeabilities, " + var2 + ", does not match the number of layers, " + this.numberOfLayers);
        } else {
            int var3 = var1.length;
            if (this.wavelNumberSet && var3 != this.numberOfWavelengths) {
                throw new IllegalArgumentException("Number of relative magnetic permeabilities associated wavelengths, " + var3 + ", does not match the number of wavelengths already entered, " + this.numberOfWavelengths);
            } else {
                this.relativeMagneticPermeabilities = var1;
                this.magneticSet = true;

                for(int var4 = 0; var4 < this.numberOfLayers; ++var4) {
                    Complex var5 = Complex.zero();

                    for(int var6 = 0; var6 < this.numberOfWavelengths; ++var6) {
                        var5.plusEquals(this.relativeMagneticPermeabilities[var6][var4]);
                    }

                    this.meanRelativeMagneticPermeabilities[var4] = var5.over((double)this.numberOfWavelengths);
                }

            }
        }
    }

    public void relativeMagneticPermeabilities(double[][] var1) {
        int var2 = var1[0].length;
        if (var2 != this.numberOfLayers) {
            throw new IllegalArgumentException("Number of relative magnetic permeabilities, " + var2 + ", does not match the number of layers, " + this.numberOfLayers);
        } else {
            int var3 = var1.length;
            if (this.wavelNumberSet && var3 != this.numberOfWavelengths) {
                throw new IllegalArgumentException("Number of relative magnetic permeabilities associated wavelengths, " + var3 + ", does not match the number of wavelengths already entered, " + this.numberOfWavelengths);
            } else {
                this.relativeMagneticPermeabilities = Complex.twoDarray(var3, var2);

                int var4;
                for(var4 = 0; var4 < this.numberOfLayers; ++var4) {
                    for(int var5 = 0; var5 < this.numberOfWavelengths; ++var5) {
                        this.relativeMagneticPermeabilities[var5][var4].setReal(var1[var5][var4]);
                    }
                }

                this.magneticSet = true;

                for(var4 = 0; var4 < this.numberOfLayers; ++var4) {
                    Complex var7 = Complex.zero();

                    for(int var6 = 0; var6 < this.numberOfWavelengths; ++var6) {
                        var7.plusEquals(this.relativeMagneticPermeabilities[var6][var4]);
                    }

                    this.meanRelativeMagneticPermeabilities[var4] = var7.over((double)this.numberOfWavelengths);
                }

            }
        }
    }

    public void setRelativeMagneticPermeabilities(Complex[] var1) {
        int var2 = var1.length;
        if (var2 != this.numberOfLayers) {
            throw new IllegalArgumentException("Number of relative magnetic permeabilities, " + var2 + ", does not match the number of layers, " + this.numberOfLayers);
        } else {
            this.meanRelativeMagneticPermeabilities = var1;
            this.meanMagneticUsed = true;
            if (this.wavelNumberSet) {
                for(int var3 = 0; var3 < this.numberOfWavelengths; ++var3) {
                    this.relativeMagneticPermeabilities[var3] = Complex.copy(var1);
                }
            }

        }
    }

    public void setRelativeMagneticPermeabilities(double[] var1) {
        int var2 = var1.length;
        if (var2 != this.numberOfLayers) {
            throw new IllegalArgumentException("Number of relative magnetic permeabilities, " + var2 + ", does not match the number of layers, " + this.numberOfLayers);
        } else {
            int var3;
            for(var3 = 0; var3 < var2; ++var3) {
                this.meanRelativeMagneticPermeabilities[var3].setReal(var1[var3]);
            }

            this.meanMagneticUsed = true;
            if (this.wavelNumberSet) {
                for(var3 = 0; var3 < this.numberOfWavelengths; ++var3) {
                    this.relativeMagneticPermeabilities[var3] = Complex.copy(this.meanRelativeMagneticPermeabilities);
                }
            }

        }
    }

    public void setRelativeMagneticPermeabilities(Complex[] var1, int var2) {
        int var3 = var1.length;
        if (this.wavelNumberSet && var3 != this.numberOfWavelengths) {
            throw new IllegalArgumentException("Layer " + var2 + ": number of relative magnetic permeabilities associated wavelengths, " + var3 + ", does not match the number of wavelengths already entered, " + this.numberOfWavelengths);
        } else {
            if (this.relativeMagneticPermeabilities == null) {
                this.relativeMagneticPermeabilities = Complex.twoDarray(var3, this.numberOfLayers);
            }

            this.relativeMagneticPermeabilities[var2 - 1] = var1;
            Complex var4 = Complex.zero();

            for(int var5 = 0; var5 < var3; ++var5) {
                var4.plusEquals(this.relativeMagneticPermeabilities[var5][var2 - 1]);
            }

            this.meanRelativeMagneticPermeabilities[var2 - 1] = var4.over((double)var3);
        }
    }

    public void setRelativeMagneticPermeabilities(double[] var1, int var2) {
        int var3 = var1.length;
        if (this.wavelNumberSet && var3 != this.numberOfWavelengths) {
            throw new IllegalArgumentException("Layer " + var2 + ": number of relative magnetic permeabilities associated wavelengths, " + var3 + ", does not match the number of wavelengths already entered, " + this.numberOfWavelengths);
        } else {
            if (this.relativeMagneticPermeabilities == null) {
                this.relativeMagneticPermeabilities = Complex.twoDarray(var3, this.numberOfLayers);
            }

            for(int var4 = 0; var4 < var3; ++var4) {
                this.relativeMagneticPermeabilities[var4][var2 - 1].setReal(var1[var4]);
            }

            Complex var6 = Complex.zero();

            for(int var5 = 0; var5 < var3; ++var5) {
                var6.plusEquals(this.relativeMagneticPermeabilities[var5][var2 - 1]);
            }

            this.meanRelativeMagneticPermeabilities[var2 - 1] = var6.over((double)var3);
        }
    }

    public void setRelativeMagneticPermeabilities(Complex var1, int var2) {
        this.meanRelativeMagneticPermeabilities[var2 - 1] = var1;
        this.meanMagneticUsed = true;
        if (this.relativeMagneticPermeabilities != null) {
            int var3 = this.relativeMagneticPermeabilities[0].length;

            for(int var4 = 0; var4 < var3; ++var4) {
                this.relativeMagneticPermeabilities[var4][var2 - 1] = var1;
            }
        }

    }

    public void setRelativeMagneticPermeabilities(double var1, int var3) {
        this.meanRelativeMagneticPermeabilities[var3 - 1].setReal(var1);
        this.meanMagneticUsed = true;
        if (this.relativeMagneticPermeabilities != null) {
            int var4 = this.relativeMagneticPermeabilities[0].length;

            for(int var5 = 0; var5 < var4; ++var5) {
                this.relativeMagneticPermeabilities[var5][var3 - 1] = this.meanRelativeMagneticPermeabilities[var3 - 1];
            }
        }

    }

    public Object getRelativeMagneticPermeabilities() {
        if (this.numberOfWavelengths == 1) {
            Complex[] var1 = this.relativeMagneticPermeabilities[0];
            return var1;
        } else {
            return this.relativeMagneticPermeabilities;
        }
    }

    public Object getReflectivities() {
        this.checkWhichCalculation();
        if (this.singleReflectCalculated) {
            return this.reflectivities[0];
        } else if (this.angularReflectCalculated) {
            return this.reflectivities[0];
        } else if (!this.wavelengthReflectCalculated) {
            return this.wavelengthAndAngularReflectCalculated ? this.reflectivities : null;
        } else {
            double[] var1 = new double[this.numberOfWavelengths];

            for(int var2 = 0; var2 < this.numberOfWavelengths; ++var2) {
                var1[var2] = this.reflectivities[var2][0];
            }

            return var1;
        }
    }

    public Object getTEreflectionCoefficients() {
        this.checkWhichCalculation();
        if (this.singleReflectCalculated) {
            return this.reflectCoeffTE[0];
        } else if (this.angularReflectCalculated) {
            return this.reflectCoeffTE[0];
        } else if (!this.wavelengthReflectCalculated) {
            return this.wavelengthAndAngularReflectCalculated ? this.reflectCoeffTE : null;
        } else {
            Complex[] var1 = Complex.oneDarray(this.numberOfWavelengths);

            for(int var2 = 0; var2 < this.numberOfWavelengths; ++var2) {
                var1[var2] = this.reflectCoeffTE[var2][0];
            }

            return var1;
        }
    }

    public Object getTMreflectionCoefficients() {
        this.checkWhichCalculation();
        if (this.singleReflectCalculated) {
            return this.reflectCoeffTM[0];
        } else if (this.angularReflectCalculated) {
            return this.reflectCoeffTM[0];
        } else if (!this.wavelengthReflectCalculated) {
            return this.wavelengthAndAngularReflectCalculated ? this.reflectCoeffTM : null;
        } else {
            Complex[] var1 = Complex.oneDarray(this.numberOfWavelengths);

            for(int var2 = 0; var2 < this.numberOfWavelengths; ++var2) {
                var1[var2] = this.reflectCoeffTM[var2][0];
            }

            return var1;
        }
    }

    public Object getTransmissivities() {
        this.checkWhichCalculation();
        if (this.singleReflectCalculated) {
            return this.transmissivities[0];
        } else if (this.angularReflectCalculated) {
            return this.transmissivities[0];
        } else if (!this.wavelengthReflectCalculated) {
            return this.wavelengthAndAngularReflectCalculated ? this.transmissivities : null;
        } else {
            double[] var1 = new double[this.numberOfWavelengths];

            for(int var2 = 0; var2 < this.numberOfWavelengths; ++var2) {
                var1[var2] = this.transmissivities[var2][0];
            }

            return var1;
        }
    }

    public Object getPowerLoss() {
        this.checkWhichCalculation();
        if (this.singleReflectCalculated) {
            return this.powerLosses[0];
        } else if (this.angularReflectCalculated) {
            return this.powerLosses[0];
        } else if (!this.wavelengthReflectCalculated) {
            return this.wavelengthAndAngularReflectCalculated ? this.powerLosses : null;
        } else {
            double[] var1 = new double[this.numberOfWavelengths];

            for(int var2 = 0; var2 < this.numberOfWavelengths; ++var2) {
                var1[var2] = this.powerLosses[var2][0];
            }

            return var1;
        }
    }

    public Object getTransmissionAnglesInRadians() {
        this.checkWhichCalculation();
        if (this.singleReflectCalculated) {
            return this.transmitAnglesRad[0];
        } else if (this.angularReflectCalculated) {
            return this.transmitAnglesRad[0];
        } else if (!this.wavelengthReflectCalculated) {
            return this.wavelengthAndAngularReflectCalculated ? this.transmitAnglesRad : null;
        } else {
            double[] var1 = new double[this.numberOfWavelengths];

            for(int var2 = 0; var2 < this.numberOfWavelengths; ++var2) {
                var1[var2] = this.transmitAnglesRad[var2][0];
            }

            return var1;
        }
    }

    public Object getTransmissionAnglesInDegrees() {
        this.checkWhichCalculation();
        if (this.singleReflectCalculated) {
            return this.transmitAnglesDeg[0];
        } else if (this.angularReflectCalculated) {
            return this.transmitAnglesDeg[0];
        } else if (!this.wavelengthReflectCalculated) {
            return this.wavelengthAndAngularReflectCalculated ? this.transmitAnglesDeg : null;
        } else {
            double[] var1 = new double[this.numberOfWavelengths];

            for(int var2 = 0; var2 < this.numberOfWavelengths; ++var2) {
                var1[var2] = this.transmitAnglesDeg[var2][0];
            }

            return var1;
        }
    }

    public Object getTEtransmissionCoefficients() {
        this.checkWhichCalculation();
        if (this.singleReflectCalculated) {
            return this.transmitCoeffTE[0];
        } else if (this.angularReflectCalculated) {
            return this.transmitCoeffTE[0];
        } else if (!this.wavelengthReflectCalculated) {
            return this.wavelengthAndAngularReflectCalculated ? this.transmitCoeffTE : null;
        } else {
            Complex[] var1 = Complex.oneDarray(this.numberOfWavelengths);

            for(int var2 = 0; var2 < this.numberOfWavelengths; ++var2) {
                var1[var2] = this.transmitCoeffTE[var2][0];
            }

            return var1;
        }
    }

    public Object getTMtransmissionCoefficients() {
        this.checkWhichCalculation();
        if (this.singleReflectCalculated) {
            return this.transmitCoeffTM[0];
        } else if (this.angularReflectCalculated) {
            return this.transmitCoeffTM[0];
        } else if (!this.wavelengthReflectCalculated) {
            return this.wavelengthAndAngularReflectCalculated ? this.transmitCoeffTM : null;
        } else {
            Complex[] var1 = Complex.oneDarray(this.numberOfWavelengths);

            for(int var2 = 0; var2 < this.numberOfWavelengths; ++var2) {
                var1[var2] = this.transmitCoeffTM[var2][0];
            }

            return var1;
        }
    }

    public Object getTEreflectionPhaseShiftDeg() {
        this.checkWhichCalculation();
        if (this.singleReflectCalculated) {
            return this.reflectPhaseShiftDegTE[0];
        } else if (this.angularReflectCalculated) {
            return this.reflectPhaseShiftDegTE[0];
        } else if (!this.wavelengthReflectCalculated) {
            return this.wavelengthAndAngularReflectCalculated ? this.reflectPhaseShiftDegTE : null;
        } else {
            double[] var1 = new double[this.numberOfWavelengths];

            for(int var2 = 0; var2 < this.numberOfWavelengths; ++var2) {
                var1[var2] = this.reflectPhaseShiftDegTE[var2][0];
            }

            return var1;
        }
    }

    public Object getTEreflectionPhaseShiftRad() {
        this.checkWhichCalculation();
        if (this.singleReflectCalculated) {
            return this.reflectPhaseShiftRadTE[0];
        } else if (this.angularReflectCalculated) {
            return this.reflectPhaseShiftRadTE[0];
        } else if (!this.wavelengthReflectCalculated) {
            return this.wavelengthAndAngularReflectCalculated ? this.reflectPhaseShiftRadTE : null;
        } else {
            double[] var1 = new double[this.numberOfWavelengths];

            for(int var2 = 0; var2 < this.numberOfWavelengths; ++var2) {
                var1[var2] = this.reflectPhaseShiftRadTE[var2][0];
            }

            return var1;
        }
    }

    public Object getTMreflectionPhaseShiftDeg() {
        this.checkWhichCalculation();
        if (this.singleReflectCalculated) {
            return this.reflectPhaseShiftDegTM[0];
        } else if (this.angularReflectCalculated) {
            return this.reflectPhaseShiftDegTM[0];
        } else if (!this.wavelengthReflectCalculated) {
            return this.wavelengthAndAngularReflectCalculated ? this.reflectPhaseShiftDegTM : null;
        } else {
            double[] var1 = new double[this.numberOfWavelengths];

            for(int var2 = 0; var2 < this.numberOfWavelengths; ++var2) {
                var1[var2] = this.reflectPhaseShiftDegTM[var2][0];
            }

            return var1;
        }
    }

    public Object getTMreflectionPhaseShiftRad() {
        this.checkWhichCalculation();
        if (this.singleReflectCalculated) {
            return this.reflectPhaseShiftRadTM[0];
        } else if (this.angularReflectCalculated) {
            return this.reflectPhaseShiftRadTM[0];
        } else if (!this.wavelengthReflectCalculated) {
            return this.wavelengthAndAngularReflectCalculated ? this.reflectPhaseShiftRadTM : null;
        } else {
            double[] var1 = new double[this.numberOfWavelengths];

            for(int var2 = 0; var2 < this.numberOfWavelengths; ++var2) {
                var1[var2] = this.reflectPhaseShiftRadTM[var2][0];
            }

            return var1;
        }
    }

    public Object getTEtransmissionPhaseShiftDeg() {
        this.checkWhichCalculation();
        if (this.singleReflectCalculated) {
            return this.transmitPhaseShiftDegTE[0];
        } else if (this.angularReflectCalculated) {
            return this.transmitPhaseShiftDegTE[0];
        } else if (!this.wavelengthReflectCalculated) {
            return this.wavelengthAndAngularReflectCalculated ? this.transmitPhaseShiftDegTE : null;
        } else {
            double[] var1 = new double[this.numberOfWavelengths];

            for(int var2 = 0; var2 < this.numberOfWavelengths; ++var2) {
                var1[var2] = this.transmitPhaseShiftDegTE[var2][0];
            }

            return var1;
        }
    }

    public Object getTEtransmissionPhaseShiftRad() {
        this.checkWhichCalculation();
        if (this.singleReflectCalculated) {
            return this.transmitPhaseShiftRadTE[0];
        } else if (this.angularReflectCalculated) {
            return this.transmitPhaseShiftRadTE[0];
        } else if (!this.wavelengthReflectCalculated) {
            return this.wavelengthAndAngularReflectCalculated ? this.transmitPhaseShiftRadTE : null;
        } else {
            double[] var1 = new double[this.numberOfWavelengths];

            for(int var2 = 0; var2 < this.numberOfWavelengths; ++var2) {
                var1[var2] = this.transmitPhaseShiftRadTE[var2][0];
            }

            return var1;
        }
    }

    public Object getTMtransmissionPhaseShiftDeg() {
        this.checkWhichCalculation();
        if (this.singleReflectCalculated) {
            return this.transmitPhaseShiftDegTM[0];
        } else if (this.angularReflectCalculated) {
            return this.transmitPhaseShiftDegTM[0];
        } else if (!this.wavelengthReflectCalculated) {
            return this.wavelengthAndAngularReflectCalculated ? this.transmitPhaseShiftDegTM : null;
        } else {
            double[] var1 = new double[this.numberOfWavelengths];

            for(int var2 = 0; var2 < this.numberOfWavelengths; ++var2) {
                var1[var2] = this.transmitPhaseShiftDegTM[var2][0];
            }

            return var1;
        }
    }

    public Object getTMtransmissionPhaseShiftRad() {
        this.checkWhichCalculation();
        if (this.singleReflectCalculated) {
            return this.transmitPhaseShiftRadTM[0];
        } else if (this.angularReflectCalculated) {
            return this.transmitPhaseShiftRadTM[0];
        } else if (!this.wavelengthReflectCalculated) {
            return this.wavelengthAndAngularReflectCalculated ? this.transmitPhaseShiftRadTM : null;
        } else {
            double[] var1 = new double[this.numberOfWavelengths];

            for(int var2 = 0; var2 < this.numberOfWavelengths; ++var2) {
                var1[var2] = this.transmitPhaseShiftRadTM[var2][0];
            }

            return var1;
        }
    }

    public Object getEvanescentFields(double var1) {
        this.fieldDistance = var1;
        return this.getEvanescentFields();
    }

    public Object getEvanescentFields() {
        this.checkWhichCalculation();
        if (this.singleReflectCalculated) {
            return this.evanescentFields[0];
        } else if (this.angularReflectCalculated) {
            return this.evanescentFields[0];
        } else if (!this.wavelengthReflectCalculated) {
            return this.wavelengthAndAngularReflectCalculated ? this.evanescentFields : null;
        } else {
            double[] var1 = new double[this.numberOfWavelengths];

            for(int var2 = 0; var2 < this.numberOfWavelengths; ++var2) {
                var1[var2] = this.evanescentFields[var2][0];
            }

            return var1;
        }
    }

    public Object getPenetrationDepths() {
        this.checkWhichCalculation();
        if (this.singleReflectCalculated) {
            return this.penetrationDepths[0];
        } else if (this.angularReflectCalculated) {
            return this.penetrationDepths[0];
        } else if (!this.wavelengthReflectCalculated) {
            return this.wavelengthAndAngularReflectCalculated ? this.penetrationDepths : null;
        } else {
            double[] var1 = new double[this.numberOfWavelengths];

            for(int var2 = 0; var2 < this.numberOfWavelengths; ++var2) {
                var1[var2] = this.penetrationDepths[var2][0];
            }

            return var1;
        }
    }

    public Object getKoVectors() {
        this.checkWhichCalculation();
        if (this.singleReflectCalculated) {
            return this.koVector[0][0][0];
        } else if (this.angularReflectCalculated) {
            return this.koVector[0][0][0];
        } else {
            Complex[] var1;
            int var2;
            if (this.wavelengthReflectCalculated) {
                var1 = Complex.oneDarray(this.numberOfWavelengths);

                for(var2 = 0; var2 < this.numberOfWavelengths; ++var2) {
                    var1[var2] = this.koVector[var2][0][0];
                }

                return var1;
            } else if (!this.wavelengthAndAngularReflectCalculated) {
                return null;
            } else {
                var1 = Complex.oneDarray(this.numberOfWavelengths);

                for(var2 = 0; var2 < this.numberOfWavelengths; ++var2) {
                    var1[var2] = this.koVector[var2][0][0];
                }

                return var1;
            }
        }
    }

    public Object getKzVectors() {
        this.checkWhichCalculation();
        if (this.singleReflectCalculated) {
            return this.kzVector[0][0][0];
        } else {
            int var2;
            Complex[] var4;
            if (this.angularReflectCalculated) {
                var4 = Complex.oneDarray(this.numberOfIncidentAngles);

                for(var2 = 0; var2 < this.numberOfIncidentAngles; ++var2) {
                    var4[var2] = this.kzVector[0][var2][0];
                }

                return var4;
            } else if (this.wavelengthReflectCalculated) {
                var4 = Complex.oneDarray(this.numberOfWavelengths);

                for(var2 = 0; var2 < this.numberOfWavelengths; ++var2) {
                    var4[var2] = this.kzVector[var2][0][0];
                }

                return var4;
            } else if (!this.wavelengthAndAngularReflectCalculated) {
                return null;
            } else {
                Complex[][] var1 = Complex.twoDarray(this.numberOfWavelengths, this.numberOfIncidentAngles);

                for(var2 = 0; var2 < this.numberOfWavelengths; ++var2) {
                    for(int var3 = 0; var3 < this.numberOfIncidentAngles; ++var3) {
                        var1[var2][var3] = this.kzVector[var2][var3][0];
                    }
                }

                return var1;
            }
        }
    }

    public Object getKvectors() {
        this.checkWhichCalculation();
        if (this.singleReflectCalculated) {
            return this.kVector[0][0];
        } else if (this.angularReflectCalculated) {
            return this.kVector[0];
        } else {
            Complex[][] var1;
            int var2;
            byte var3;
            if (this.wavelengthReflectCalculated) {
                var1 = Complex.twoDarray(this.numberOfWavelengths, this.numberOfLayers);

                for(var2 = 0; var2 < this.numberOfWavelengths; ++var2) {
                    for(var3 = 0; var2 < this.numberOfLayers; ++var2) {
                        var1[var2][var3] = this.kVector[var2][0][var3];
                    }
                }

                return var1;
            } else if (!this.wavelengthAndAngularReflectCalculated) {
                return null;
            } else {
                var1 = Complex.twoDarray(this.numberOfWavelengths, this.numberOfLayers);

                for(var2 = 0; var2 < this.numberOfWavelengths; ++var2) {
                    for(var3 = 0; var2 < this.numberOfLayers; ++var2) {
                        var1[var2][var3] = this.kVector[var2][0][var3];
                    }
                }

                return var1;
            }
        }
    }

    public Object getKxVectors() {
        this.checkWhichCalculation();
        if (this.singleReflectCalculated) {
            return this.kxVector[0][0];
        } else if (this.angularReflectCalculated) {
            return this.kxVector[0];
        } else if (!this.wavelengthReflectCalculated) {
            return this.wavelengthAndAngularReflectCalculated ? this.kxVector : null;
        } else {
            Complex[][] var1 = Complex.twoDarray(this.numberOfWavelengths, this.numberOfLayers);

            for(int var2 = 0; var2 < this.numberOfWavelengths; ++var2) {
                for(byte var3 = 0; var2 < this.numberOfLayers; ++var2) {
                    var1[var2][var3] = this.kxVector[var2][0][var3];
                }
            }

            return var1;
        }
    }

    public void resetPlotAxisAsFrequency() {
        this.wavelengthAxisOption = 2;
    }

    public void resetPlotAxisAsRadians() {
        this.wavelengthAxisOption = 3;
    }

    public void resetPlotAxisAsWavelength() {
        this.wavelengthAxisOption = 1;
    }

    public void plotReflectivities() {
        String var1 = "Polarisation mode: " + this.mode;
        this.plotReflectivities(var1);
    }

    public void plotReflectivities(String var1) {
        this.checkWhichCalculation();
        if (this.singleReflectCalculated) {
            throw new IllegalArgumentException("Plot methods require more than one data point");
        } else {
            String var2 = " Reflectivities";
            String var3 = "Reflectivity";
            String var4 = " ";
            this.plotSimulation(var1, var2, var3, var4, this.reflectivities);
        }
    }

    public void plotTransmissivities() {
        String var1 = "Polarisation mode: " + this.mode;
        this.plotTransmissivities(var1);
    }

    public void plotTransmissivities(String var1) {
        this.checkWhichCalculation();
        if (this.singleReflectCalculated) {
            throw new IllegalArgumentException("Plot methods require more than one data point");
        } else {
            String var2 = " Transmissivities";
            String var3 = "Transmissivity";
            String var4 = " ";
            this.plotSimulation(var1, var2, var3, var4, this.transmissivities);
        }
    }

    public void plotPowerLosses() {
        String var1 = "Polarisation mode: " + this.mode;
        this.plotPowerLosses(var1);
    }

    public void plotPowerLosses(String var1) {
        this.checkWhichCalculation();
        if (this.singleReflectCalculated) {
            throw new IllegalArgumentException("Plot methods require more than one data point");
        } else {
            String var2 = " Power Losses in decibels relative to an incident power of 1 mW";
            String var3 = "Power Losses";
            String var4 = "dBm";
            this.plotSimulation(var1, var2, var3, var4, this.powerLosses);
        }
    }

    public void plotTransmissionAngles() {
        String var1 = "Polarisation mode: " + this.mode;
        this.plotTransmissionAngles(var1);
    }

    public void plotTransmissionAngles(String var1) {
        this.checkWhichCalculation();
        if (this.singleReflectCalculated) {
            throw new IllegalArgumentException("Plot methods require more than one data point");
        } else {
            String var2 = " Transmission angles (degrees)";
            String var3 = "Transmission angle";
            String var4 = "degrees";
            this.plotSimulation(var1, var2, var3, var4, this.transmitAnglesDeg);
        }
    }

    public void plotAbsTEreflectionCoefficients() {
        String var1 = "Polarisation mode: " + this.mode;
        this.plotAbsTEreflectionCoefficients(var1);
    }

    public void plotAbsTEreflectionCoefficients(String var1) {
        this.checkWhichCalculation();
        if (this.singleReflectCalculated) {
            throw new IllegalArgumentException("Plot methods require more than one data point");
        } else {
            if (this.teFraction == 0.0D) {
                System.out.println("No TE transmission coefficient plot displayed as no light in the TE mode");
            } else {
                double[][] var2 = new double[this.numberOfWavelengths][this.numberOfIncidentAngles];

                for(int var3 = 0; var3 < this.numberOfWavelengths; ++var3) {
                    for(int var4 = 0; var4 < this.numberOfIncidentAngles; ++var4) {
                        var2[var3][var4] = this.reflectCoeffTE[var3][var4].abs();
                    }
                }

                String var6 = " Absolute values of the TE reflection coefficients";
                String var7 = "|TE Reflection Coefficient|";
                String var5 = " ";
                this.plotSimulation(var1, var6, var7, var5, var2);
            }

        }
    }

    public void plotAbsTMreflectionCoefficients() {
        String var1 = "Polarisation mode: " + this.mode;
        this.plotAbsTMreflectionCoefficients(var1);
    }

    public void plotAbsTMreflectionCoefficients(String var1) {
        this.checkWhichCalculation();
        if (this.singleReflectCalculated) {
            throw new IllegalArgumentException("Plot methods require more than one data point");
        } else {
            if (this.tmFraction == 0.0D) {
                System.out.println("No TM transmission coefficient plot displayed as no light in the TM mode");
            } else {
                double[][] var2 = new double[this.numberOfWavelengths][this.numberOfIncidentAngles];

                for(int var3 = 0; var3 < this.numberOfWavelengths; ++var3) {
                    for(int var4 = 0; var4 < this.numberOfIncidentAngles; ++var4) {
                        var2[var3][var4] = this.reflectCoeffTM[var3][var4].abs();
                    }
                }

                String var6 = " Absolute values of the TM reflection coefficients";
                String var7 = "|TM Reflection Coefficient|";
                String var5 = " ";
                this.plotSimulation(var1, var6, var7, var5, var2);
            }

        }
    }

    public void plotAbsTEtransmissionCoefficients() {
        String var1 = "Polarisation mode: " + this.mode;
        this.plotAbsTEtransmissionCoefficients(var1);
    }

    public void plotAbsTEtransmissionCoefficients(String var1) {
        this.checkWhichCalculation();
        if (this.singleReflectCalculated) {
            throw new IllegalArgumentException("Plot methods require more than one data point");
        } else {
            if (this.teFraction == 0.0D) {
                System.out.println("No TE transmission coefficient plot displayed as no light in the TE mode");
            } else {
                double[][] var2 = new double[this.numberOfWavelengths][this.numberOfIncidentAngles];

                for(int var3 = 0; var3 < this.numberOfWavelengths; ++var3) {
                    for(int var4 = 0; var4 < this.numberOfIncidentAngles; ++var4) {
                        var2[var3][var4] = this.transmitCoeffTE[var3][var4].abs();
                    }
                }

                String var6 = " Absolute values of the TE transmission coefficients";
                String var7 = "|TE Transmission Coefficient|";
                String var5 = " ";
                this.plotSimulation(var1, var6, var7, var5, var2);
            }

        }
    }

    public void plotAbsTMtransmissionCoefficients() {
        String var1 = "Polarisation mode: " + this.mode;
        this.plotAbsTMtransmissionCoefficients(var1);
    }

    public void plotAbsTMtransmissionCoefficients(String var1) {
        this.checkWhichCalculation();
        if (this.singleReflectCalculated) {
            throw new IllegalArgumentException("Plot methods require more than one data point");
        } else {
            if (this.tmFraction == 0.0D) {
                System.out.println("No TM transmission coefficient plot displayed as no light in the TM mode");
            } else {
                double[][] var2 = new double[this.numberOfWavelengths][this.numberOfIncidentAngles];

                for(int var3 = 0; var3 < this.numberOfWavelengths; ++var3) {
                    for(int var4 = 0; var4 < this.numberOfIncidentAngles; ++var4) {
                        var2[var3][var4] = this.transmitCoeffTM[var3][var4].abs();
                    }
                }

                String var6 = " Absolute values of the TM transmission coefficients";
                String var7 = "|TM Transmission Coefficient|";
                String var5 = " ";
                this.plotSimulation(var1, var6, var7, var5, var2);
            }

        }
    }

    public void plotEvanescentFields() {
        String var1 = "Polarisation mode: " + this.mode;
        this.plotEvanescentFields(var1);
    }

    public void plotEvanescentFields(double var1) {
        this.fieldDistance = this.fieldDistance;
        String var3 = "Polarisation mode: " + this.mode;
        this.plotEvanescentFields(var3);
    }

    public void plotEvanescentFields(double var1, String var3) {
        this.fieldDistance = var1;
        this.plotEvanescentFields(var3);
    }

    public void plotEvanescentFields(String var1) {
        this.checkWhichCalculation();
        if (this.singleReflectCalculated) {
            throw new IllegalArgumentException("Plot methods require more than one data point");
        } else {
            String var2 = " Integrated Evanescent Field Intensities to a depth of " + this.fieldDistance + " metres";
            String var3 = "Evanescent Field intensity";
            String var4 = " ";
            this.plotSimulation(var1, var2, var3, var4, this.evanescentFields);
        }
    }

    public void plotPenetrationDepths() {
        String var1 = "Polarisation mode: " + this.mode;
        this.plotPenetrationDepths(var1);
    }

    public void plotPenetrationDepths(String var1) {
        this.checkWhichCalculation();
        if (this.singleReflectCalculated) {
            throw new IllegalArgumentException("Plot methods require more than one data point");
        } else {
            String var2 = " Evanescent Field Penetration Depths";
            String var3 = "Penetration Depth";
            String var4 = "metres";
            this.plotSimulation(var1, var2, var3, var4, this.penetrationDepths);
        }
    }

    public void plotTEreflectionPhaseShiftDeg() {
        String var1 = "Polarisation mode: " + this.mode;
        this.plotTEreflectionPhaseShiftDeg(var1);
    }

    public void plotTEreflectionPhaseShiftDeg(String var1) {
        this.checkWhichCalculation();
        if (this.singleReflectCalculated) {
            throw new IllegalArgumentException("Plot methods require more than one data point");
        } else {
            if (this.teFraction == 0.0D) {
                System.out.println("No TE phase shift plot displayed as no light in the TE mode");
            } else {
                String var2 = " Phase Shift on Reflection (TE mode)";
                String var3 = "Phase shift";
                String var4 = "degrees ";
                this.plotSimulation(var1, var2, var3, var4, this.reflectPhaseShiftDegTE);
            }

        }
    }

    public void plotTMreflectionPhaseShiftDeg() {
        String var1 = "Polarisation mode: " + this.mode;
        this.plotTMreflectionPhaseShiftDeg(var1);
    }

    public void plotTMreflectionPhaseShiftDeg(String var1) {
        this.checkWhichCalculation();
        if (this.singleReflectCalculated) {
            throw new IllegalArgumentException("Plot methods require more than one data point");
        } else {
            if (this.tmFraction == 0.0D) {
                System.out.println("No TM phase shift plot displayed as no light in the TM mode");
            } else {
                String var2 = " Phase Shift on Reflection (TM mode)";
                String var3 = "Phase shift";
                String var4 = "degrees ";
                this.plotSimulation(var1, var2, var3, var4, this.reflectPhaseShiftDegTM);
            }

        }
    }

    public void plotTEreflectionPhaseShiftRad() {
        String var1 = "Polarisation mode: " + this.mode;
        this.plotTEreflectionPhaseShiftRad(var1);
    }

    public void plotTEreflectionPhaseShiftRad(String var1) {
        this.checkWhichCalculation();
        if (this.singleReflectCalculated) {
            throw new IllegalArgumentException("Plot methods require more than one data point");
        } else {
            if (this.teFraction == 0.0D) {
                System.out.println("No TE phase shift plot displayed as no light in the TE mode");
            } else {
                String var2 = " Phase Shift on Reflection (TE mode)";
                String var3 = "Phase shift";
                String var4 = "radians ";
                this.plotSimulation(var1, var2, var3, var4, this.reflectPhaseShiftRadTE);
            }

        }
    }

    public void plotTMreflectionPhaseShiftRad() {
        String var1 = "Polarisation mode: " + this.mode;
        this.plotTMreflectionPhaseShiftRad(var1);
    }

    public void plotTMreflectionPhaseShiftRad(String var1) {
        this.checkWhichCalculation();
        if (this.singleReflectCalculated) {
            throw new IllegalArgumentException("Plot methods require more than one data point");
        } else {
            if (this.tmFraction == 0.0D) {
                System.out.println("No TM phase shift plot displayed as no light in the TM mode");
            } else {
                String var2 = " Phase Shift on Reflection (TM mode)";
                String var3 = "Phase shift";
                String var4 = "radians ";
                this.plotSimulation(var1, var2, var3, var4, this.reflectPhaseShiftRadTM);
            }

        }
    }

    public void plotTEtransmissionPhaseShiftDeg() {
        String var1 = "Polarisation mode: " + this.mode;
        this.plotTEtransmissionPhaseShiftDeg(var1);
    }

    public void plotTEtransmissionPhaseShiftDeg(String var1) {
        this.checkWhichCalculation();
        if (this.singleReflectCalculated) {
            throw new IllegalArgumentException("Plot methods require more than one data point");
        } else {
            if (this.teFraction == 0.0D) {
                System.out.println("No TE phase shift plot displayed as no light in the TE mode");
            } else {
                String var2 = " Phase Shift on Transmission (TE mode)";
                String var3 = "Phase shift";
                String var4 = "degrees ";
                this.plotSimulation(var1, var2, var3, var4, this.transmitPhaseShiftDegTE);
            }

        }
    }

    public void plotTMtransmissionPhaseShiftDeg() {
        String var1 = "Polarisation mode: " + this.mode;
        this.plotTMtransmissionPhaseShiftDeg(var1);
    }

    public void plotTMtransmissionPhaseShiftDeg(String var1) {
        this.checkWhichCalculation();
        if (this.singleReflectCalculated) {
            throw new IllegalArgumentException("Plot methods require more than one data point");
        } else {
            if (this.tmFraction == 0.0D) {
                System.out.println("No TM phase shift plot displayed as no light in the TM mode");
            } else {
                String var2 = " Phase Shift on Transmission (TM mode)";
                String var3 = "Phase shift";
                String var4 = "degrees ";
                this.plotSimulation(var1, var2, var3, var4, this.transmitPhaseShiftDegTM);
            }

        }
    }

    public void plotTEtransmissionPhaseShiftRad() {
        String var1 = "Polarisation mode: " + this.mode;
        this.plotTEtransmissionPhaseShiftRad(var1);
    }

    public void plotTEtransmissionPhaseShiftRad(String var1) {
        this.checkWhichCalculation();
        if (this.singleReflectCalculated) {
            throw new IllegalArgumentException("Plot methods require more than one data point");
        } else {
            if (this.teFraction == 0.0D) {
                System.out.println("No TE phase shift plot displayed as no light in the TE mode");
            } else {
                String var2 = " Phase Shift on Transmission (TE mode)";
                String var3 = "Phase shift";
                String var4 = "radians ";
                this.plotSimulation(var1, var2, var3, var4, this.transmitPhaseShiftRadTE);
            }

        }
    }

    public void plotTMtransmissionPhaseShiftRad() {
        String var1 = "Polarisation mode: " + this.mode;
        this.plotTMtransmissionPhaseShiftRad(var1);
    }

    public void plotTMtransmissionPhaseShiftRad(String var1) {
        this.checkWhichCalculation();
        if (this.singleReflectCalculated) {
            throw new IllegalArgumentException("Plot methods require more than one data point");
        } else {
            if (this.tmFraction == 0.0D) {
                System.out.println("No TM phase shift plot displayed as no light in the TM mode");
            } else {
                String var2 = " Phase Shift on Transmission (TM mode)";
                String var3 = "Phase shift";
                String var4 = "radians ";
                this.plotSimulation(var1, var2, var3, var4, this.transmitPhaseShiftRadTM);
            }

        }
    }

    public void plotSimulation(String var1, String var2, String var3, String var4, Object var5) {
        Object var6 = var5;

        int var7;
        for(var7 = 1; !((var6 = Array.get(var6, 0)) instanceof Double); ++var7) {
            ;
        }

        double[][] var8 = new double[var7][];
        if (var7 == 1) {
            double[] var9 = (double[])((double[])var5);
            var8[0] = var9;
        } else {
            var8 = (double[][])((double[][])var5);
        }

        int var16 = var8.length;
        int[] var10 = null;
        double[][] var11 = (double[][])null;
        String var12 = null;
        String var13 = null;
        if (this.angularReflectCalculated) {
            var10 = new int[]{1};
            var11 = new double[2][var16];
            var11[0] = this.incidentAngleDeg;
            var11[1] = var8[0];
            var12 = "Incident Angle";
            var13 = "degrees";
        }

        if (this.wavelengthReflectCalculated) {
            var10 = new int[]{1};
            var11 = new double[2][var16];
            var11[0] = this.wavelengths;
            double[] var14 = new double[this.numberOfWavelengths];

            int var15;
            for(var15 = 0; var15 < this.numberOfWavelengths; ++var15) {
                var14[var15] = var8[var15][0];
            }

            switch(this.wavelengthAxisOption) {
                case 1:
                    var11[0] = this.wavelengths;
                    var11[1] = var14;
                    var12 = "Wavelength";
                    var13 = "metres";
                    break;
                case 2:
                    var11[0] = this.frequencies;

                    for(var15 = 0; var15 < this.numberOfWavelengths; ++var15) {
                        var11[1][this.numberOfWavelengths - 1 - var15] = var14[var15];
                    }

                    var12 = "Frequency";
                    var13 = "Hz";
                    break;
                case 3:
                    var11[0] = this.omega;

                    for(var15 = 0; var15 < this.numberOfWavelengths; ++var15) {
                        var11[1][this.numberOfWavelengths - 1 - var15] = var14[var15];
                    }

                    var12 = "Radial Frequency";
                    var13 = "radians";
            }
        }

        if (this.wavelengthAndAngularReflectCalculated) {
            var10 = new int[var7];
            var11 = new double[2 * var7][var16];

            for(int var17 = 0; var17 < var7; ++var17) {
                var10[var17] = var17 + 1;
                var11[2 * var17] = this.incidentAngleDeg;
                var11[2 * var17 + 1] = var8[var17];
            }

            var12 = "Incident Angle";
            var13 = "degrees";
        }

        PlotGraph var18 = new PlotGraph(var11);
        var18.setGraphTitle("Class Reflectivity: Simulation Plot - " + var2);
        var18.setGraphTitle2(var1);
        var18.setXaxisLegend(var12);
        var18.setYaxisLegend(var3);
        var18.setXaxisUnitsName(var13);
        if (!var4.equals(" ")) {
            var18.setYaxisUnitsName(var4);
        }

        var18.setLine(3);
        var18.setPoint(var10);
        var18.setFrame();
    }

    public void checkWhichCalculation() {
        boolean var1 = false;
        if (this.singleReflectCalculated) {
            var1 = true;
        }

        if (this.angularReflectCalculated) {
            var1 = true;
        }

        if (this.wavelengthReflectCalculated) {
            var1 = true;
        }

        if (this.wavelengthAndAngularReflectCalculated) {
            var1 = true;
        }

        int var2;
        if (var1) {
            if (this.fieldDistance != 1.0D / 0.0 && !this.fieldIntensityCalc) {
                var2 = this.numberOfLayers - 1;
                double var3 = 0.0D;

                for(int var5 = 0; var5 < this.numberOfWavelengths; ++var5) {
                    for(int var6 = 0; var6 < this.numberOfIncidentAngles; ++var6) {
                        if (this.kxVector[var5][var6][var2].getReal() == 0.0D) {
                            double var7 = 1.0D / this.kxVector[var5][var6][var2].getImag();
                            var3 += this.teFraction * Fmath.square(this.transmitCoeffTE[var5][var6].abs()) * (1.0D - Math.exp(-2.0D * this.fieldDistance / var7)) * var7 / 2.0D;
                            double var9 = this.refractiveIndices[var5][0].getReal() / this.refractiveIndices[var5][var6].getReal();
                            double var11 = Math.sqrt(this.relativeMagneticPermeabilities[var5][var2].getReal() / this.relativeMagneticPermeabilities[var5][0].getReal());
                            var3 += this.teFraction * Fmath.square(this.transmitCoeffTM[var5][var6].abs()) * var11 * var9 * (1.0D - Math.exp(-2.0D * this.fieldDistance / var7)) * var7 / 2.0D;
                        }
                    }
                }

                this.fieldIntensityCalc = true;
            }
        } else {
            if (this.numberOfIncidentAngles == 0) {
                throw new IllegalArgumentException("No incident angle/s has/have been entered");
            }

            if (this.numberOfWavelengths == 0) {
                throw new IllegalArgumentException("No wavelength/s has/have been entered");
            }

            if (this.numberOfWavelengths > 1) {
                this.sortWavelengths();
            }

            this.koVector = Complex.threeDarray(this.numberOfWavelengths, this.numberOfIncidentAngles, this.numberOfLayers);
            this.kzVector = Complex.threeDarray(this.numberOfWavelengths, this.numberOfIncidentAngles, this.numberOfLayers);
            this.kVector = Complex.threeDarray(this.numberOfWavelengths, this.numberOfIncidentAngles, this.numberOfLayers);
            this.kxVector = Complex.threeDarray(this.numberOfWavelengths, this.numberOfIncidentAngles, this.numberOfLayers);

            for(var2 = 0; var2 < this.numberOfWavelengths; ++var2) {
                for(int var13 = 0; var13 < this.numberOfIncidentAngles; ++var13) {
                    for(int var4 = 0; var4 < this.numberOfLayers; ++var4) {
                        this.koVector[var2][var13][var4].reset(6.283185307179586D / this.wavelengths[var2], 0.0D);
                        this.kVector[var2][var13][var4] = this.koVector[var2][var13][var4].times(this.refractiveIndices[var2][var4]).times(Complex.sqrt(this.relativeMagneticPermeabilities[var2][var4]));
                        this.kzVector[var2][var13][var4] = this.koVector[var2][var13][var4].times(this.refractiveIndices[var2][0]).times(Complex.sqrt(this.relativeMagneticPermeabilities[var2][0]));
                        this.kzVector[var2][var13][var4] = this.kzVector[var2][var13][var4].times(Math.sin(this.incidentAngleRad[var13]));
                        this.kxVector[var2][var13][var4] = Complex.square(this.kVector[var2][var13][var4]).minus(Complex.square(this.kzVector[var2][var13][var4]));
                        this.kxVector[var2][var13][var4] = Complex.sqrt(this.kxVector[var2][var13][var4]);
                    }
                }
            }

            this.reflectivities = new double[this.numberOfWavelengths][this.numberOfIncidentAngles];
            this.transmissivities = new double[this.numberOfWavelengths][this.numberOfIncidentAngles];
            this.powerLosses = new double[this.numberOfWavelengths][this.numberOfIncidentAngles];
            this.reflectCoeffTE = Complex.twoDarray(this.numberOfWavelengths, this.numberOfIncidentAngles);
            this.reflectCoeffTM = Complex.twoDarray(this.numberOfWavelengths, this.numberOfIncidentAngles);
            this.transmitCoeffTE = Complex.twoDarray(this.numberOfWavelengths, this.numberOfIncidentAngles);
            this.transmitCoeffTM = Complex.twoDarray(this.numberOfWavelengths, this.numberOfIncidentAngles);
            this.evanescentFields = new double[this.numberOfWavelengths][this.numberOfIncidentAngles];
            this.penetrationDepths = new double[this.numberOfWavelengths][this.numberOfIncidentAngles];
            this.transmitAnglesRad = new double[this.numberOfWavelengths][this.numberOfIncidentAngles];
            this.transmitAnglesDeg = new double[this.numberOfWavelengths][this.numberOfIncidentAngles];
            this.reflectPhaseShiftRadTE = new double[this.numberOfWavelengths][this.numberOfIncidentAngles];
            this.reflectPhaseShiftRadTM = new double[this.numberOfWavelengths][this.numberOfIncidentAngles];
            this.reflectPhaseShiftDegTE = new double[this.numberOfWavelengths][this.numberOfIncidentAngles];
            this.reflectPhaseShiftDegTM = new double[this.numberOfWavelengths][this.numberOfIncidentAngles];
            this.transmitPhaseShiftRadTE = new double[this.numberOfWavelengths][this.numberOfIncidentAngles];
            this.transmitPhaseShiftRadTM = new double[this.numberOfWavelengths][this.numberOfIncidentAngles];
            this.transmitPhaseShiftDegTE = new double[this.numberOfWavelengths][this.numberOfIncidentAngles];
            this.transmitPhaseShiftDegTM = new double[this.numberOfWavelengths][this.numberOfIncidentAngles];
            this.scan();
        }

    }

    public void scan() {
        if (!this.wavelSet) {
            throw new IllegalArgumentException("No wavelength has been entered");
        } else if (!this.refractSet) {
            throw new IllegalArgumentException("No, or not all, refractive indices have been entered");
        } else if (!this.thickSet) {
            throw new IllegalArgumentException("No, or not all, layer thicknesses have been entered");
        } else if (!this.incidentAngleSet) {
            throw new IllegalArgumentException("No incident angle has been entered");
        } else if (!this.modeSet) {
            throw new IllegalArgumentException("No polaristaion mode (TE, TM, unpolarised or mixed[angle to be entered]) has been entered");
        } else {
            this.singleReflectCalculated = false;
            this.angularReflectCalculated = false;
            this.wavelengthReflectCalculated = false;
            this.wavelengthAndAngularReflectCalculated = false;

            for(int var1 = 0; var1 < this.numberOfWavelengths; ++var1) {
                for(int var2 = 0; var2 < this.numberOfIncidentAngles; ++var2) {
                    this.calcReflectivity(var1, var2);
                }
            }

            if (this.numberOfWavelengths == 1) {
                if (this.numberOfIncidentAngles == 1) {
                    this.singleReflectCalculated = true;
                } else {
                    this.angularReflectCalculated = true;
                }
            } else if (this.numberOfIncidentAngles == 1) {
                this.wavelengthReflectCalculated = true;
            } else {
                this.wavelengthAndAngularReflectCalculated = true;
            }

        }
    }

    public void calcReflectivity(int var1, int var2) {
        double[] var3 = new double[6];
        if (this.teFraction > 0.0D) {
            var3 = this.calcTEreflectivity(var1, var2);
        }

        if (this.tmFraction > 0.0D) {
            double[] var4 = this.calcTMreflectivity(var1, var2);
            var3[0] = this.teFraction * var3[0] + this.tmFraction * var4[0];
            var3[1] = this.teFraction * var3[1] + this.tmFraction * var4[1];
            var3[2] = this.teFraction * var3[2] + this.tmFraction * var4[2];
            var3[3] = this.teFraction * var3[3] + this.tmFraction * var4[3];
            var3[4] = this.teFraction * var3[4] + this.tmFraction * var4[4];
            var3[5] = this.teFraction * var3[5] + this.tmFraction * var4[5];
        }

        this.reflectivities[var1][var2] = var3[0];
        this.transmissivities[var1][var2] = var3[1];
        this.transmitAnglesRad[var1][var2] = var3[2];
        this.transmitAnglesDeg[var1][var2] = Math.toDegrees(var3[2]);
        this.evanescentFields[var1][var2] = var3[3];
        this.penetrationDepths[var1][var2] = var3[4];
        this.powerLosses[var1][var2] = var3[5];
    }

    public double[] calcTEreflectivity(int var1, int var2) {
        Complex var3 = Complex.zero();
        Complex var4 = Complex.zero();
        Complex var5 = Complex.zero();
        Complex var6 = Complex.zero();
        double var7 = 0.0D;
        Complex var27;
        if (this.numberOfLayers == 2) {
            var3 = this.relativeMagneticPermeabilities[var1][1].times(this.kxVector[var1][var2][0]);
            var4 = this.relativeMagneticPermeabilities[var1][0].times(this.kxVector[var1][var2][1]);
            var5 = var3.minus(var4);
            var6 = var3.plus(var4);
            this.reflectCoeffTE[var1][var2] = var5.over(var6);
            var5 = var3.times(2.0D);
            this.transmitCoeffTE[var1][var2] = var5.over(var6);
        } else {
            ComplexMatrix var9 = new ComplexMatrix(2, 2);
            Complex[][] var10 = Complex.twoDarray(2, 2);
            Complex var11 = this.kxVector[var1][var2][1].over(this.kVector[var1][var2][1]);
            Complex var12 = this.refractiveIndices[var1][1].over(this.impedance).over(Complex.sqrt(this.relativeMagneticPermeabilities[var1][1]));
            var12 = var12.times(var11);
            Complex var13 = this.kxVector[var1][var2][1].times(this.thicknesses[1]);
            var10[0][0] = Complex.cos(var13);
            var10[1][1] = var10[0][0];
            var3 = Complex.sin(var13);
            var3 = var3.times(Complex.minusJay());
            var10[0][1] = var3.over(var12);
            var10[1][0] = var3.times(var12);
            if (this.numberOfLayers > 3) {
                ComplexMatrix var14 = new ComplexMatrix(Complex.copy(var10));

                for(int var15 = 2; var15 < this.numberOfLayers - 1; ++var15) {
                    var11 = this.kxVector[var1][var2][var15].over(this.kVector[var1][var2][var15]);
                    var12 = this.refractiveIndices[var1][var15].over(this.impedance).over(Complex.sqrt(this.relativeMagneticPermeabilities[var1][var15]));
                    var12 = var12.times(var11);
                    var13 = this.kxVector[var1][var2][var15].times(this.thicknesses[var15]);
                    var10[0][0] = Complex.cos(var13);
                    var10[1][1] = var10[0][0];
                    var3 = Complex.sin(var13);
                    var3 = var3.times(Complex.minusJay());
                    var10[0][1] = var3.over(var12);
                    var10[1][0] = var3.times(var12);
                    var9.setTwoDarray(Complex.copy(var10));
                    var14 = var14.times(var9);
                    var10 = var14.getArrayCopy();
                }
            }

            var11 = this.kxVector[var1][var2][0].over(this.kVector[var1][var2][0]);
            var27 = this.refractiveIndices[var1][0].over(this.impedance).over(Complex.sqrt(this.relativeMagneticPermeabilities[var1][0]));
            var27 = var27.times(var11);
            var11 = this.kxVector[var1][var2][this.numberOfLayers - 1].over(this.kVector[var1][var2][this.numberOfLayers - 1]);
            Complex var28 = this.refractiveIndices[var1][this.numberOfLayers - 1].over(this.impedance).over(Complex.sqrt(this.relativeMagneticPermeabilities[var1][this.numberOfLayers - 1]));
            var28 = var28.times(var11);
            var3 = var10[0][0].plus(var10[0][1].times(var28));
            var3 = var3.times(var27);
            var4 = var10[1][0].plus(var10[1][1].times(var28));
            var5 = var3.minus(var4);
            var6 = var3.plus(var4);
            this.reflectCoeffTE[var1][var2] = var5.over(var6);
            this.reflectPhaseShiftRadTE[var1][var2] = this.reflectCoeffTE[var1][var2].arg();
            this.reflectPhaseShiftDegTE[var1][var2] = Math.toDegrees(this.reflectPhaseShiftRadTE[var1][var2]);
            var5 = var27.times(2.0D);
            this.transmitCoeffTE[var1][var2] = var5.over(var6);
            this.transmitPhaseShiftRadTE[var1][var2] = this.transmitCoeffTE[var1][var2].arg();
            this.transmitPhaseShiftDegTE[var1][var2] = Math.toDegrees(this.transmitPhaseShiftRadTE[var1][var2]);
        }

        double var24 = Fmath.square(this.reflectCoeffTE[var1][var2].getReal()) + Fmath.square(this.reflectCoeffTE[var1][var2].getImag());
        int var25 = this.numberOfLayers - 1;
        double var26 = Fmath.square(this.transmitCoeffTE[var1][var2].getReal()) + Fmath.square(this.transmitCoeffTE[var1][var2].getImag());
        var4 = this.relativeMagneticPermeabilities[var1][0].over(this.relativeMagneticPermeabilities[var1][var25]).times(var26);
        var5 = this.kxVector[var1][var2][var25].conjugate().over(this.kxVector[var1][var2][0]);
        var27 = var4.times(var5);
        double var29 = 0.0D;
        double var17 = 1.5707963267948966D;
        double var19 = 0.0D;
        if (this.kxVector[var1][var2][var25].getReal() == 0.0D) {
            var7 = 1.0D / this.kxVector[var1][var2][var25].getImag();
            var19 = Fmath.square(this.transmitCoeffTE[var1][var2].abs()) * (1.0D - Math.exp(-2.0D * this.fieldDistance / var7)) * var7 / 2.0D;
            if (this.fieldDistance != 1.0D / 0.0) {
                this.fieldIntensityCalc = true;
            }
        } else {
            var29 = var27.getReal();
            var17 = Math.atan2(this.kzVector[var1][var2][var25].getReal(), this.kxVector[var1][var2][var25].getReal());
        }

        double var21 = 10.0D * Fmath.log10((1.0D - var29) * 0.001D);
        double[] var23 = new double[]{var24, var29, var17, var19, var7, var21};
        return var23;
    }

    public double[] calcTMreflectivity(int var1, int var2) {
        Complex var3 = Complex.zero();
        Complex var4 = Complex.zero();
        Complex var5 = Complex.zero();
        Complex var6 = Complex.zero();
        double var7 = 0.0D;
        Complex var28;
        if (this.numberOfLayers == 2) {
            var3 = Complex.square(this.refractiveIndices[var1][1]).times(this.kxVector[var1][var2][0]);
            var4 = Complex.square(this.refractiveIndices[var1][0]).times(this.kxVector[var1][var2][1]);
            var5 = var3.minus(var4);
            var6 = var3.plus(var4);
            this.reflectCoeffTM[var1][var2] = var5.over(var6);
            var5 = var3.times(2.0D);
            this.transmitCoeffTM[var1][var2] = var5.over(var6);
        } else {
            ComplexMatrix var9 = new ComplexMatrix(2, 2);
            Complex[][] var10 = Complex.twoDarray(2, 2);
            Complex var11 = this.kxVector[var1][var2][1].over(this.kVector[var1][var2][1]);
            Complex var12 = this.refractiveIndices[var1][1].over(this.impedance).over(Complex.sqrt(this.relativeMagneticPermeabilities[var1][1]));
            var12 = var12.over(var11);
            Complex var13 = this.kxVector[var1][var2][1].times(this.thicknesses[1]);
            var10[0][0] = Complex.cos(var13);
            var10[1][1] = var10[0][0];
            var3 = Complex.sin(var13);
            var3 = var3.times(Complex.minusJay());
            var10[0][1] = var3.over(var12);
            var10[1][0] = var3.times(var12);
            if (this.numberOfLayers > 3) {
                ComplexMatrix var14 = new ComplexMatrix(Complex.copy(var10));

                for(int var15 = 2; var15 < this.numberOfLayers - 1; ++var15) {
                    var11 = this.kxVector[var1][var2][var15].over(this.kVector[var1][var2][var15]);
                    var12 = this.refractiveIndices[var1][var15].over(this.impedance).over(Complex.sqrt(this.relativeMagneticPermeabilities[var1][var15]));
                    var12 = var12.over(var11);
                    var13 = this.kxVector[var1][var2][var15].times(this.thicknesses[var15]);
                    var10[0][0] = Complex.cos(var13);
                    var10[1][1] = var10[0][0];
                    var3 = Complex.sin(var13);
                    var3 = var3.times(Complex.minusJay());
                    var10[0][1] = var3.over(var12);
                    var10[1][0] = var3.times(var12);
                    var9.setTwoDarray(Complex.copy(var10));
                    var14 = var14.times(var9);
                    var10 = var14.getArrayReference();
                }
            }

            var11 = this.kxVector[var1][var2][0].over(this.kVector[var1][var2][0]);
            var28 = this.refractiveIndices[var1][0].over(this.impedance).over(Complex.sqrt(this.relativeMagneticPermeabilities[var1][0]));
            var28 = var28.over(var11);
            var11 = this.kxVector[var1][var2][this.numberOfLayers - 1].over(this.kVector[var1][var2][this.numberOfLayers - 1]);
            Complex var29 = this.refractiveIndices[var1][this.numberOfLayers - 1].over(this.impedance).over(Complex.sqrt(this.relativeMagneticPermeabilities[var1][this.numberOfLayers - 1]));
            var29 = var29.over(var11);
            var3 = var10[0][0].plus(var10[0][1].times(var29));
            var3 = var3.times(var28);
            var4 = var10[1][0].plus(var10[1][1].times(var29));
            var5 = var3.minus(var4);
            var6 = var3.plus(var4);
            this.reflectCoeffTM[var1][var2] = var5.over(var6);
            this.reflectPhaseShiftRadTM[var1][var2] = this.reflectCoeffTM[var1][var2].arg();
            this.reflectPhaseShiftDegTM[var1][var2] = Math.toDegrees(this.reflectPhaseShiftRadTM[var1][var2]);
            var5 = var28.times(2.0D);
            this.transmitCoeffTM[var1][var2] = var5.over(var6);
            this.transmitPhaseShiftRadTM[var1][var2] = this.transmitCoeffTM[var1][var2].arg();
            this.transmitPhaseShiftDegTM[var1][var2] = Math.toDegrees(this.transmitPhaseShiftRadTM[var1][var2]);
        }

        double var25 = Fmath.square(this.reflectCoeffTM[var1][var2].getReal()) + Fmath.square(this.reflectCoeffTM[var1][var2].getImag());
        int var26 = this.numberOfLayers - 1;
        double var27 = Fmath.square(this.transmitCoeffTM[var1][var2].getReal()) + Fmath.square(this.transmitCoeffTM[var1][var2].getImag());
        var4 = Complex.square(this.refractiveIndices[var1][0].over(this.refractiveIndices[var1][var26])).times(var27);
        var5 = this.kxVector[var1][var2][var26].conjugate().over(this.kxVector[var1][var2][0]);
        var28 = var4.times(var5);
        double var30 = 0.0D;
        double var17 = 1.5707963267948966D;
        double var19 = 0.0D;
        double var21;
        if (this.kxVector[var1][var2][var26].getReal() == 0.0D) {
            var7 = 1.0D / this.kxVector[var1][var2][var26].getImag();
            var21 = this.refractiveIndices[var1][0].getReal() / this.refractiveIndices[var1][var26].getReal();
            double var23 = Math.sqrt(this.relativeMagneticPermeabilities[var1][var26].getReal() / this.relativeMagneticPermeabilities[var1][0].getReal());
            var19 = Fmath.square(this.transmitCoeffTM[var1][var2].abs()) * var23 * var21 * (1.0D - Math.exp(-2.0D * this.fieldDistance / var7)) * var7 / 2.0D;
            if (this.fieldDistance != 1.0D / 0.0) {
                this.fieldIntensityCalc = true;
            }
        } else {
            var30 = var28.getReal();
            var17 = Math.atan2(this.kzVector[var1][var2][var26].getReal(), this.kxVector[var1][var2][var26].getReal());
        }

        var21 = 10.0D * Fmath.log10((1.0D - var30) * 0.001D);
        double[] var31 = new double[]{var25, var30, var17, var19, var7, var21};
        return var31;
    }

    public void setThicknessEstimatesIndices(int[] var1) {
        this.thicknessEstimateIndices = var1;
        this.thicknessEstimateNumber = var1.length;
    }

    public void setRealRefractIndexEstimateIndices(int[] var1) {
        this.refractIndexRealEstimateIndices = var1;
        this.refractIndexRealEstimateNumber = var1.length;
    }

    public void setImagRefractIndexEstimateIndices(int[] var1) {
        this.refractIndexImagEstimateIndices = var1;
        this.refractIndexImagEstimateNumber = var1.length;
        this.refractIndexImagEstimateSet = true;
        if (this.absorptionCoeffEstimateSet) {
            int[] var2 = new int[this.absorptionCoeffEstimateNumber];
            int var3 = 0;

            int var4;
            int var6;
            for(var4 = 0; var4 < this.numberOfLayers; ++var4) {
                boolean var5 = false;

                for(var6 = 0; var6 < this.refractIndexImagEstimateNumber; ++var6) {
                    if (var4 == this.refractIndexImagEstimateIndices[var6]) {
                        var5 = true;
                    }
                }

                boolean var9 = false;

                for(int var7 = 0; var7 < this.absorptionCoeffEstimateNumber; ++var7) {
                    if (var4 == this.absorptionCoeffEstimateIndices[var7]) {
                        var9 = true;
                    }
                }

                if (!var5 && var9) {
                    var2[var3] = var4;
                    ++var3;
                }
            }

            var4 = this.refractIndexImagEstimateNumber + var3;
            int[] var8 = new int[var4];

            for(var6 = 0; var6 < this.refractIndexImagEstimateNumber; ++var6) {
                var8[var6] = this.refractIndexImagEstimateIndices[var6];
            }

            for(var6 = 0; var6 < this.absorptionCoeffEstimateNumber; ++var6) {
                var8[this.refractIndexImagEstimateNumber + var6] = this.absorptionCoeffEstimateIndices[var6];
            }

            this.refractIndexImagEstimateIndices = Fmath.selectionSort(var8);
        }

    }

    public void setAbsorptionCoefficientEstimateIndices(int[] var1) {
        this.absorptionCoeffEstimateIndices = var1;
        this.absorptionCoeffEstimateNumber = var1.length;
        this.absorptionCoeffEstimateSet = true;
        if (this.refractIndexImagEstimateSet) {
            int[] var2 = new int[this.absorptionCoeffEstimateNumber];
            int var3 = 0;

            int var4;
            int var6;
            for(var4 = 0; var4 < this.numberOfLayers; ++var4) {
                boolean var5 = false;

                for(var6 = 0; var6 < this.refractIndexImagEstimateNumber; ++var6) {
                    if (var4 == this.refractIndexImagEstimateIndices[var6]) {
                        var5 = true;
                    }
                }

                boolean var9 = false;

                for(int var7 = 0; var7 < this.absorptionCoeffEstimateNumber; ++var7) {
                    if (var4 == this.absorptionCoeffEstimateIndices[var7]) {
                        var9 = true;
                    }
                }

                if (!var5 && var9) {
                    var2[var3] = var4;
                    ++var3;
                }
            }

            var4 = this.refractIndexImagEstimateNumber + var3;
            int[] var8 = new int[var4];

            for(var6 = 0; var6 < this.refractIndexImagEstimateNumber; ++var6) {
                var8[var6] = this.refractIndexImagEstimateIndices[var6];
            }

            for(var6 = 0; var6 < this.absorptionCoeffEstimateNumber; ++var6) {
                var8[this.refractIndexImagEstimateNumber + var6] = this.absorptionCoeffEstimateIndices[var6];
            }

            this.refractIndexImagEstimateIndices = Fmath.selectionSort(var8);
        } else {
            this.refractIndexImagEstimateIndices = this.absorptionCoeffEstimateIndices;
            this.refractIndexImagEstimateNumber = this.absorptionCoeffEstimateNumber;
        }

    }

    public void setRealRelativeMagneticPermeabilityEstimateIndices(int[] var1) {
        this.magneticPermRealEstimateIndices = var1;
        this.magneticPermRealEstimateNumber = var1.length;
    }

    public void setImagRelativeMagneticPermeabilityEstimateIndices(int[] var1) {
        this.magneticPermImagEstimateIndices = var1;
        this.magneticPermImagEstimateNumber = var1.length;
    }

    public void fitReflectivities(double[] var1) {
        int var2 = var1.length;
        double[] var3 = new double[var2];

        for(int var4 = 0; var4 < var2; ++var4) {
            var3[var4] = 1.0D;
        }

        this.fitReflectivities(var1, var3);
    }

    public void fitReflectivities(double[] var1, double[] var2) {
        this.numberOfDataPoints = var1.length;
        if (this.numberOfDataPoints != var2.length) {
            throw new IllegalArgumentException("Number of data points, " + this.numberOfDataPoints + " is not equal to the number of errors (weights), " + var2.length + ".");
        } else {
            if (this.incidentAngleSet) {
                if (this.numberOfDataPoints != this.numberOfIncidentAngles) {
                    throw new IllegalArgumentException("Number of experimental reflectivities " + this.numberOfDataPoints + " does not equal the number of incident angles " + this.numberOfIncidentAngles);
                }

                double[] var3 = Conv.copy(var1);
                double[] var4 = Conv.copy(var2);

                for(int var5 = 0; var5 < this.numberOfIncidentAngles; ++var5) {
                    this.experimentalData[var5] = var3[this.incidentAngleIndices[var5]];
                    this.experimentalWeights[var5] = var4[this.incidentAngleIndices[var5]];
                }
            }

            this.regressionOption = 1;
            this.experimentalDataSet = true;
            this.nonLinearRegression();
        }
    }

    public void fitAndPlotReflectivities(double[] var1) {
        this.fitReflectivities(var1);
        String var2 = " ";
        this.plotFit(var2);
    }

    public void fitAndPlotReflectivities(double[] var1, String var2) {
        this.fitReflectivities(var1);
        this.plotFit(var2);
    }

    public void fitAndPlotReflectivities(double[] var1, double[] var2) {
        this.fitReflectivities(var1, var2);
        String var3 = " ";
        this.plotFit(var3);
    }

    public void fitAndPlotReflectivities(double[] var1, double[] var2, String var3) {
        this.fitReflectivities(var1, var2);
        this.plotFit(var3);
    }

    public void fitTransmissivities(double[] var1) {
        int var2 = var1.length;
        double[] var3 = new double[var2];

        for(int var4 = 0; var4 < var2; ++var4) {
            var3[var4] = 1.0D;
        }

        this.fitTransmissivities(var1, var3);
    }

    public void fitTransmissivities(double[] var1, double[] var2) {
        this.numberOfDataPoints = var1.length;
        if (this.numberOfDataPoints != var2.length) {
            throw new IllegalArgumentException("Number of data points, " + this.numberOfDataPoints + " is not equal to the number of errors (weights), " + var2.length + ".");
        } else {
            if (this.incidentAngleSet) {
                if (this.numberOfDataPoints != this.numberOfIncidentAngles) {
                    throw new IllegalArgumentException("Number of experimental transmissivities " + this.numberOfDataPoints + " does not equal the number of incident angles " + this.numberOfIncidentAngles);
                }

                double[] var3 = Conv.copy(var1);
                double[] var4 = Conv.copy(var2);

                for(int var5 = 0; var5 < this.numberOfIncidentAngles; ++var5) {
                    this.experimentalData[var5] = var3[this.incidentAngleIndices[var5]];
                    this.experimentalWeights[var5] = var4[this.incidentAngleIndices[var5]];
                }
            }

            this.regressionOption = 1;
            this.experimentalDataSet = true;
            this.nonLinearRegression();
        }
    }

    public void fitAndPlotTransmissivities(double[] var1) {
        this.fitTransmissivities(var1);
        String var2 = " ";
        this.plotFit(var2);
    }

    public void fitAndPlotTransmissivities(double[] var1, String var2) {
        this.fitTransmissivities(var1);
        this.plotFit(var2);
    }

    public void fitAndPlotTransmissivities(double[] var1, double[] var2) {
        this.fitTransmissivities(var1, var2);
        String var3 = " ";
        this.plotFit(var3);
    }

    public void fitAndPlotTransmissivities(double[] var1, double[] var2, String var3) {
        this.fitTransmissivities(var1, var2);
        this.plotFit(var3);
    }

    public void fitEvanescentField(double[] var1) {
        int var2 = var1.length;
        double[] var3 = new double[var2];

        for(int var4 = 0; var4 < var2; ++var4) {
            var3[var4] = 1.0D;
        }

        double var6 = 1.0D / 0.0;
        this.fitEvanescentField(var1, var3, var6);
    }

    public void fitEvanescentField(double[] var1, double[] var2) {
        double var3 = 1.0D / 0.0;
        this.fitEvanescentField(var1, var2, var3);
    }

    public void fitEvanescentField(double[] var1, double var2) {
        int var4 = var1.length;
        double[] var5 = new double[var4];

        for(int var6 = 0; var6 < var4; ++var6) {
            var5[var6] = 1.0D;
        }

        this.fitEvanescentField(var1, var5, var2);
    }

    public void fitEvanescentField(double[] var1, double[] var2, double var3) {
        this.numberOfDataPoints = var1.length;
        if (this.numberOfDataPoints != var2.length) {
            throw new IllegalArgumentException("Number of data points, " + this.numberOfDataPoints + " is not equal to the number of errors (weights), " + var2.length + ".");
        } else {
            if (this.incidentAngleSet) {
                if (this.numberOfDataPoints != this.numberOfIncidentAngles) {
                    throw new IllegalArgumentException("Number of experimental transmissivities " + this.numberOfDataPoints + " does not equal the number of incident angles " + this.numberOfIncidentAngles);
                }

                double[] var5 = Conv.copy(var1);
                double[] var6 = Conv.copy(var2);

                for(int var7 = 0; var7 < this.numberOfIncidentAngles; ++var7) {
                    this.experimentalData[var7] = var5[this.incidentAngleIndices[var7]];
                    this.experimentalWeights[var7] = var6[this.incidentAngleIndices[var7]];
                }
            }

            this.regressionOption = 3;
            this.fieldDistance = var3;
            this.experimentalDataSet = true;
            this.nonLinearRegression();
        }
    }

    public void nonLinearRegression() {
        int var1 = 0;
        boolean var2 = true;

        while(var2) {
            if (this.experimentalWeights[var1] != 1.0D) {
                this.weightingOption = true;
                var2 = false;
            } else {
                ++var1;
                if (var1 >= this.numberOfDataPoints) {
                    var2 = false;
                }
            }
        }

        Regression var3 = null;
        if (this.weightingOption) {
            var3 = new Regression(this.incidentAngleDeg, this.experimentalData, this.experimentalWeights);
        } else {
            var3 = new Regression(this.incidentAngleDeg, this.experimentalData);
        }

        RegressFunct var4 = new RegressFunct();
        var4.numberOfLayers = this.numberOfLayers;
        var4.mode = this.mode;
        var4.eVectorAngleDeg = this.eVectorAngleDeg;
        var4.thicknesses = this.thicknesses;
        var4.refractiveIndices = this.refractiveIndices;
        var4.relativeMagneticPermeabilities = this.relativeMagneticPermeabilities;
        var4.regressionOption = this.regressionOption;
        var4.thicknessEstimateIndices = this.thicknessEstimateIndices;
        var4.refractIndexRealEstimateIndices = this.refractIndexRealEstimateIndices;
        var4.refractIndexImagEstimateIndices = this.refractIndexImagEstimateIndices;
        var4.magneticPermRealEstimateIndices = this.magneticPermRealEstimateIndices;
        var4.magneticPermImagEstimateIndices = this.magneticPermImagEstimateIndices;
        this.numberOfEstimatedParameters = this.thicknessEstimateNumber;
        this.numberOfEstimatedParameters += this.refractIndexRealEstimateNumber;
        this.numberOfEstimatedParameters += this.refractIndexImagEstimateNumber;
        this.numberOfEstimatedParameters += this.magneticPermRealEstimateNumber;
        this.numberOfEstimatedParameters += this.magneticPermImagEstimateNumber;
        if (this.regressionOption == 3) {
            ++this.numberOfEstimatedParameters;
        }

        this.degreesOfFreedom = this.numberOfDataPoints - this.numberOfEstimatedParameters;
        if (this.degreesOfFreedom < 1) {
            throw new IllegalArgumentException("Number of parameters to be estimated, " + this.numberOfEstimatedParameters + ", is greater than or equal to the number of data points, " + this.numberOfDataPoints + ".");
        } else {
            double[] var5 = new double[this.numberOfEstimatedParameters];
            double[] var6 = new double[this.numberOfEstimatedParameters];
            double[] var7 = new double[this.numberOfEstimatedParameters];
            int var8 = 0;

            int var9;
            for(var9 = 0; var9 < this.thicknessEstimateNumber; ++var9) {
                var6[var8] = this.thicknesses[this.thicknessEstimateIndices[var8]];
                var5[var8] = var6[var8];
                var7[var8] = var6[var8] * 0.1D;
                if (var7[var8] == 0.0D) {
                    var7[var8] = 1.0E-9D;
                }

                ++var8;
            }

            for(var9 = 0; var9 < this.refractIndexRealEstimateNumber; ++var9) {
                var6[var8] = this.refractiveIndices[0][this.refractIndexRealEstimateIndices[var8]].getReal();
                var5[var8] = var6[var8];
                var7[var8] = var6[var8] * 0.1D;
                if (var7[var8] == 0.0D) {
                    var7[var8] = 0.1D;
                }

                ++var8;
            }

            for(var9 = 0; var9 < this.refractIndexImagEstimateNumber; ++var9) {
                var6[var8] = this.refractiveIndices[0][this.refractIndexImagEstimateIndices[var8]].getImag();
                var5[var8] = var6[var8];
                var7[var8] = var6[var8] * 0.1D;
                if (var7[var8] == 0.0D) {
                    var7[var8] = 0.1D;
                }

                ++var8;
            }

            for(var9 = 0; var9 < this.magneticPermRealEstimateNumber; ++var9) {
                var6[var8] = this.relativeMagneticPermeabilities[0][this.magneticPermRealEstimateIndices[var8]].getReal();
                var5[var8] = var6[var8];
                var7[var8] = var6[var8] * 0.1D;
                if (var7[var8] == 0.0D) {
                    var7[var8] = 0.1D;
                }

                ++var8;
            }

            for(var9 = 0; var9 < this.magneticPermImagEstimateNumber; ++var9) {
                var6[var8] = this.relativeMagneticPermeabilities[0][this.magneticPermImagEstimateIndices[var8]].getImag();
                var5[var8] = var6[var8];
                var7[var8] = var6[var8] * 0.1D;
                if (var7[var8] == 0.0D) {
                    var7[var8] = 0.1D;
                }

                ++var8;
            }

            if (this.regressionOption == 3) {
                double[] var15 = (double[])((double[])this.getEvanescentFields(this.fieldDistance));
                double var10 = 0.0D;
                double var12 = 0.0D;

                for(int var14 = 0; var14 < this.numberOfDataPoints; ++var14) {
                    if (var15[var14] != 0.0D) {
                        var10 += var15[var14];
                        var12 += this.experimentalData[var14];
                    }
                }

                if (var12 == 0.0D) {
                    throw new IllegalArgumentException("All entered field values are zero or sum to zero");
                }

                if (var10 == 0.0D) {
                    throw new IllegalArgumentException("All calculated field values are zero or sum to zero");
                }

                var6[var8] = var12 / var10;
                var5[var8] = var6[var8];
                var7[var8] = var6[var8] * 0.1D;
                if (var7[var8] == 0.0D) {
                    var7[var8] = 0.1D;
                }

                ++var8;
            }

            double var16 = 1.0E-6D;
            short var11 = 1000;
            var3.simplex(var4, var5, var7, var16, var11);
            double[] var17 = var3.getCoeff();
            var8 = 0;

            int var13;
            for(var13 = 0; var13 < this.thicknessEstimateNumber; ++var13) {
                this.thicknesses[this.thicknessEstimateIndices[var8]] = var17[var8];
                ++var8;
            }

            for(var13 = 0; var13 < this.refractIndexRealEstimateNumber; ++var13) {
                this.refractiveIndices[0][this.refractIndexRealEstimateIndices[var8]].setReal(var17[var8]);
                ++var8;
            }

            for(var13 = 0; var13 < this.refractIndexImagEstimateNumber; ++var13) {
                this.refractiveIndices[0][this.refractIndexImagEstimateIndices[var8]].setImag(var17[var8]);
                ++var8;
            }

            for(var13 = 0; var13 < this.magneticPermRealEstimateNumber; ++var13) {
                this.relativeMagneticPermeabilities[0][this.magneticPermRealEstimateIndices[var8]].setReal(var17[var8]);
                ++var8;
            }

            for(var13 = 0; var13 < this.magneticPermImagEstimateNumber; ++var13) {
                this.relativeMagneticPermeabilities[0][this.magneticPermImagEstimateIndices[var8]].setImag(var17[var8]);
                ++var8;
            }

            if (this.regressionOption == 3) {
                this.fieldScalingFactor = var17[var8];
            }

            switch(this.regressionOption) {
                case 1:
                    this.calculatedData = (double[])((double[])this.getReflectivities());
                    break;
                case 2:
                    this.calculatedData = (double[])((double[])this.getTransmissivities());
                    break;
                case 3:
                    this.calculatedData = (double[])((double[])this.getEvanescentFields());

                    for(var13 = 0; var13 < this.numberOfDataPoints; ++var13) {
                        this.calculatedData[var13] *= this.fieldScalingFactor;
                    }

                    return;
                default:
                    throw new IllegalArgumentException("Regresion option " + this.regressionOption + " does not exist");
            }

        }
    }

    public double[] getCalculatedData() {
        return this.calculatedData;
    }

    public void plotFit(String var1) {
        short var2 = 200;
        double[][] var3 = PlotGraph.fillData(var2, 2);

        for(int var4 = 0; var4 < this.numberOfDataPoints; ++var4) {
            var3[0][var4] = this.incidentAngleDeg[var4];
            var3[1][var4] = this.experimentalData[var4];
        }

        double var12 = (this.incidentAngleDeg[this.numberOfIncidentAngles - 1] - this.incidentAngleDeg[0]) / (double)(var2 - 1);
        var3[2][0] = this.incidentAngleDeg[0];

        for(int var6 = 1; var6 < var2 - 1; ++var6) {
            var3[2][var6] = var3[2][var6 - 1] + var12;
        }

        var3[2][var2 - 1] = this.incidentAngleDeg[this.numberOfIncidentAngles - 1];
        Reflectivity var13 = new Reflectivity(this.numberOfLayers);
        if (this.mode.equals("mixed")) {
            var13.setMode(this.eVectorAngleDeg);
        } else {
            var13.setMode(this.mode);
        }

        var13.setThicknesses(this.thicknesses);
        var13.setRefractiveIndices(this.refractiveIndices);
        var13.setRelativeMagneticPermeabilities(this.relativeMagneticPermeabilities);
        var13.setIncidentAngle(var3[2]);
        String var7 = null;
        String var8 = null;
        switch(this.regressionOption) {
            case 1:
                var3[3] = (double[])((double[])var13.getReflectivities());
                var7 = "Plot of reflectivities versus incident angle";
                var8 = "Reflectivity";
                break;
            case 2:
                var3[3] = (double[])((double[])var13.getTransmissivities());
                var7 = "Plot of transmissivities versus incident angle";
                var8 = "Transmissivity";
                break;
            case 3:
                var3[3] = (double[])((double[])var13.getEvanescentFields());

                for(int var9 = 0; var9 < var2; ++var9) {
                    var3[3][var9] *= this.fieldScalingFactor;
                }

                var7 = "Plot of evanescent fields versus incident angle";
                var8 = "Evanescent Field";
                break;
            default:
                throw new IllegalArgumentException("Regresion option " + this.regressionOption + " does not exist");
        }

        PlotGraph var14 = new PlotGraph(var3);
        var14.setGraphTitle("Reflectivity class: " + var7);
        var14.setGraphTitle2(var1);
        var14.setXaxisLegend("Incident angle");
        var14.setXaxisUnitsName("degrees");
        var14.setYaxisLegend(var8);
        int[] var10 = new int[]{1, 0};
        var14.setPoint(var10);
        int[] var11 = new int[]{0, 3};
        var14.setLine(var11);
        var14.setFrame();
    }
}
