//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.optics;

import com.flanagan.analysis.Stat;
import com.flanagan.math.Conv;
import com.flanagan.math.Fmath;
import com.flanagan.plot.PlotGraph;
import com.flanagan.roots.RealRoot;
import java.util.ArrayList;

public class PlanarWaveguide {
    protected double[][] measurementsTE = (double[][])null;
    protected int numberOfTEmeasurements = 0;
    protected double[] thicknessesUsedTE = null;
    protected double[] calcEffectRefrIndicesTE = null;
    protected boolean setMeasurementsTE = false;
    protected boolean setErrorsTE = false;
    protected double maximumTEmodeEffectiveRefractiveIndex = 0.0D;
    protected double minimumTEmodeEffectiveRefractiveIndex = 0.0D;
    protected double[][] measurementsTM = (double[][])null;
    protected int numberOfTMmeasurements = 0;
    protected double[] thicknessesUsedTM = null;
    protected double[] calcEffectRefrIndicesTM = null;
    protected boolean setMeasurementsTM = false;
    protected boolean setErrorsTM = false;
    protected double maximumTMmodeEffectiveRefractiveIndex = 0.0D;
    protected double minimumTMmodeEffectiveRefractiveIndex = 0.0D;
    protected double maximumEffectiveRefractiveIndex = 0.0D;
    protected double minimumEffectiveRefractiveIndex = 0.0D;
    protected int numberOfMeasurements = 0;
    protected boolean setMeasurements = false;
    protected boolean setWeights = false;
    protected boolean[] eliminatedTE = null;
    protected boolean[] eliminatedTM = null;
    protected double wavelength = 0.0D;
    protected boolean setWavelength = false;
    protected double ko = 0.0D;
    protected double superstrateRefractiveIndex = 0.0D;
    protected double superstrateRefractiveIndex2 = 0.0D;
    protected double[] calcSuperstrateTEmodeRI = null;
    protected double[] calcSuperstrateTMmodeRI = null;
    protected double meanTEmodeSuperstrateRefractiveIndex = 0.0D / 0.0;
    protected double meanTMmodeSuperstrateRefractiveIndex = 0.0D / 0.0;
    protected double sdTEmodeSuperstrateRefractiveIndex = 0.0D / 0.0;
    protected double sdTMmodeSuperstrateRefractiveIndex = 0.0D / 0.0;
    protected double sdSuperstrateRefractiveIndex = 0.0D / 0.0;
    protected boolean setSuperstrate = false;
    protected boolean superCalculationDone = false;
    protected double substrateRefractiveIndex = 0.0D;
    protected double substrateRefractiveIndex2 = 0.0D;
    protected boolean setSubstrate = false;
    protected double coreFilmRefractiveIndex = 0.0D;
    protected double coreFilmRefractiveIndex2 = 0.0D;
    protected boolean setCore = false;
    protected double[] coreFilmTEmodeRefractiveIndices = null;
    protected double[] coreFilmTMmodeRefractiveIndices = null;
    protected double meanTEmodeCoreFilmRefractiveIndex = 0.0D / 0.0;
    protected double meanTMmodeCoreFilmRefractiveIndex = 0.0D / 0.0;
    protected double meanCoreFilmRefractiveIndex = 0.0D / 0.0;
    protected double meanCoreFilmRefractiveIndex2 = 0.0D / 0.0;
    protected double sdTEmodeCoreFilmRefractiveIndex = 0.0D / 0.0;
    protected double sdTMmodeCoreFilmRefractiveIndex = 0.0D / 0.0;
    protected double sdCoreFilmRefractiveIndex = 0.0D / 0.0;
    protected double lowerBound = 0.0D;
    protected double upperBound = 0.0D;
    protected double tolerance = 1.0E-9D;
    protected boolean calculationDone = false;
    protected double prismToWaveguideGap = 1.0D / 0.0;
    protected boolean setPrismToWaveguideGap = false;
    protected boolean fixedPrismToWaveguideGap = true;
    protected double prismRefractiveIndex = 0.0D;
    protected double prismRefractiveIndex2 = 0.0D;

    public PlanarWaveguide() {
    }

    public void enterTEmodeData(double var1, double var3, double var5) {
        if (this.setMeasurementsTE) {
            if (this.setErrorsTE) {
                throw new IllegalArgumentException("All Entered data must either all have associated errors entered or all have no associated errors entered");
            }

            int var7 = this.numberOfTEmeasurements + 1;
            double[][] var8 = new double[var7][4];

            for(int var9 = 0; var9 < this.numberOfTEmeasurements; ++var9) {
                for(int var10 = 0; var10 < 4; ++var10) {
                    var8[var9][var10] = this.measurementsTE[var9][var10];
                }
            }

            var8[this.numberOfTEmeasurements][0] = var1;
            var8[this.numberOfTEmeasurements][1] = var3;
            var8[this.numberOfTEmeasurements][2] = 1.0D;
            var8[this.numberOfTEmeasurements][3] = var5;
            this.measurementsTE = var8;
            this.numberOfTEmeasurements = var7;
        } else {
            this.measurementsTE = new double[1][4];
            this.measurementsTE[0][0] = var1;
            this.measurementsTE[0][1] = var3;
            this.measurementsTE[0][2] = 1.0D;
            this.measurementsTE[0][3] = var5;
            this.numberOfTEmeasurements = 1;
        }

        this.numberOfMeasurements = this.numberOfTEmeasurements + this.numberOfTMmeasurements;
        this.setMeasurementsTE = true;
        this.setMeasurements = true;
    }

    public void enterTEmodeData(double var1, double var3, double var5, double var7) {
        if (this.setMeasurementsTE) {
            if (!this.setErrorsTE) {
                throw new IllegalArgumentException("All Entered data must either all have associated errors entered or all have no associated errors entered");
            }

            int var9 = this.numberOfTEmeasurements + 1;
            double[][] var10 = new double[var9][4];

            for(int var11 = 0; var11 < this.numberOfTEmeasurements; ++var11) {
                for(int var12 = 0; var12 < 4; ++var12) {
                    var10[var11][var12] = this.measurementsTE[var11][var12];
                }
            }

            var10[this.numberOfTEmeasurements][0] = var1;
            var10[this.numberOfTEmeasurements][1] = var3;
            var10[this.numberOfTEmeasurements][2] = var5;
            var10[this.numberOfTEmeasurements][3] = var7;
            this.measurementsTE = var10;
            this.numberOfTEmeasurements = var9;
        } else {
            this.measurementsTE = new double[1][4];
            this.measurementsTE[0][0] = var1;
            this.measurementsTE[0][1] = var3;
            this.measurementsTE[0][2] = var5;
            this.measurementsTE[0][3] = var7;
            this.numberOfTEmeasurements = 1;
        }

        this.numberOfMeasurements = this.numberOfTEmeasurements + this.numberOfTMmeasurements;
        this.setMeasurementsTE = true;
        this.setMeasurements = true;
        this.setErrorsTE = true;
    }

    public void enterTEmodeData(double[] var1, double[] var2, double[] var3) {
        int var4 = var1.length;
        int var5 = var2.length;
        if (var5 != var4) {
            throw new IllegalArgumentException("number of thicknesses, " + var4 + ", does not equal the number of effective refractive indices, " + var5);
        } else {
            int var6 = var3.length;
            if (var6 != var4) {
                throw new IllegalArgumentException("number of thicknesses, " + var4 + ", does not equal the number of mode numbers, " + var6);
            } else {
                int var7;
                if (this.setMeasurementsTE) {
                    if (this.setErrorsTE) {
                        throw new IllegalArgumentException("All Entered data must either all have associated errors entered or all have no associated errors entered");
                    }

                    var7 = this.numberOfTEmeasurements + var4;
                    double[][] var8 = new double[var7][4];

                    int var9;
                    for(var9 = 0; var9 < this.numberOfTEmeasurements; ++var9) {
                        for(int var10 = 0; var10 < 4; ++var10) {
                            var8[var9][var10] = this.measurementsTE[var9][var10];
                        }
                    }

                    for(var9 = 0; var9 < var4; ++var9) {
                        var8[this.numberOfTEmeasurements + var9][0] = var1[var9];
                        var8[this.numberOfTEmeasurements + var9][1] = var2[var9];
                        var8[this.numberOfTEmeasurements + var9][2] = 1.0D;
                        var8[this.numberOfTEmeasurements + var9][3] = var3[var9];
                    }

                    this.measurementsTE = var8;
                    this.numberOfTEmeasurements = var7;
                } else {
                    this.numberOfTEmeasurements = var4;
                    this.measurementsTE = new double[this.numberOfTEmeasurements][4];

                    for(var7 = 0; var7 < this.numberOfTEmeasurements; ++var7) {
                        this.measurementsTE[var7][0] = var1[var7];
                        this.measurementsTE[var7][1] = var2[var7];
                        this.measurementsTE[var7][2] = 1.0D;
                        this.measurementsTE[var7][3] = var3[var7];
                    }
                }

                this.numberOfMeasurements = this.numberOfTEmeasurements + this.numberOfTMmeasurements;
                this.setMeasurementsTE = true;
                this.setMeasurements = true;
            }
        }
    }

    public void enterTEmodeData(double[] var1, double[] var2, double[] var3, double[] var4) {
        int var5 = var1.length;
        int var6 = var2.length;
        if (var6 != var5) {
            throw new IllegalArgumentException("number of thicknesses, " + var5 + ", does not equal the number of effective refractive indices, " + var6);
        } else {
            int var7 = var4.length;
            if (var7 != var5) {
                throw new IllegalArgumentException("number of thicknesses, " + var5 + ", does not equal the number of mode numbers, " + var7);
            } else {
                int var8;
                if (this.setMeasurementsTE) {
                    if (!this.setErrorsTE) {
                        throw new IllegalArgumentException("All Entered data must either all have associated errors entered or all have no associated errors entered");
                    }

                    var8 = this.numberOfTEmeasurements + var5;
                    double[][] var9 = new double[var8][4];

                    int var10;
                    for(var10 = 0; var10 < this.numberOfTEmeasurements; ++var10) {
                        for(int var11 = 0; var11 < 4; ++var11) {
                            var9[var10][var11] = this.measurementsTE[var10][var11];
                        }
                    }

                    for(var10 = 0; var10 < var5; ++var10) {
                        var9[this.numberOfTEmeasurements + var10][0] = var1[var10];
                        var9[this.numberOfTEmeasurements + var10][1] = var2[var10];
                        var9[this.numberOfTEmeasurements + var10][2] = var3[var10];
                        var9[this.numberOfTEmeasurements + var10][3] = var4[var10];
                    }

                    this.measurementsTE = var9;
                    this.numberOfTEmeasurements = var8;
                } else {
                    this.numberOfTEmeasurements = var5;
                    this.measurementsTE = new double[this.numberOfTEmeasurements][4];

                    for(var8 = 0; var8 < this.numberOfTEmeasurements; ++var8) {
                        this.measurementsTE[var8][0] = var1[var8];
                        this.measurementsTE[var8][1] = var2[var8];
                        this.measurementsTE[var8][2] = var3[var8];
                        this.measurementsTE[var8][3] = var4[var8];
                    }
                }

                this.numberOfMeasurements = this.numberOfTEmeasurements + this.numberOfTMmeasurements;
                this.setMeasurementsTE = true;
                this.setMeasurements = true;
                this.setErrorsTE = true;
            }
        }
    }

    public void enterTMmodeData(double var1, double var3, double var5) {
        if (this.setMeasurementsTM) {
            if (this.setErrorsTM) {
                throw new IllegalArgumentException("All Entered data must either all have associated errors entered or all have no associated errors entered");
            }

            int var7 = this.numberOfTMmeasurements + 1;
            double[][] var8 = new double[var7][4];

            for(int var9 = 0; var9 < this.numberOfTMmeasurements; ++var9) {
                for(int var10 = 0; var10 < 4; ++var10) {
                    var8[var9][var10] = this.measurementsTM[var9][var10];
                }
            }

            var8[this.numberOfTMmeasurements][0] = var1;
            var8[this.numberOfTMmeasurements][1] = var3;
            var8[this.numberOfTMmeasurements][2] = 1.0D;
            var8[this.numberOfTMmeasurements][3] = var5;
            this.measurementsTM = var8;
            this.numberOfTMmeasurements = var7;
        } else {
            this.measurementsTM = new double[1][4];
            this.measurementsTM[0][0] = var1;
            this.measurementsTM[0][1] = var3;
            this.measurementsTM[0][2] = 1.0D;
            this.measurementsTM[0][3] = var5;
            this.numberOfTMmeasurements = 1;
        }

        this.numberOfMeasurements = this.numberOfTEmeasurements + this.numberOfTMmeasurements;
        this.setMeasurementsTM = true;
        this.setMeasurements = true;
    }

    public void enterTMmodeData(double var1, double var3, double var5, double var7) {
        if (this.setMeasurementsTM) {
            if (!this.setErrorsTM) {
                throw new IllegalArgumentException("All Entered data must either all have associated errors entered or all have no associated errors entered");
            }

            int var9 = this.numberOfTMmeasurements + 1;
            double[][] var10 = new double[var9][4];

            for(int var11 = 0; var11 < this.numberOfTMmeasurements; ++var11) {
                for(int var12 = 0; var12 < 4; ++var12) {
                    var10[var11][var12] = this.measurementsTM[var11][var12];
                }
            }

            var10[this.numberOfTMmeasurements][0] = var1;
            var10[this.numberOfTMmeasurements][1] = var3;
            var10[this.numberOfTMmeasurements][2] = var5;
            var10[this.numberOfTMmeasurements][3] = var7;
            this.measurementsTM = var10;
            this.numberOfTMmeasurements = var9;
        } else {
            this.measurementsTM = new double[1][4];
            this.measurementsTM[0][0] = var1;
            this.measurementsTM[0][1] = var3;
            this.measurementsTM[0][2] = var5;
            this.measurementsTM[0][3] = var7;
            this.numberOfTMmeasurements = 1;
        }

        this.numberOfMeasurements = this.numberOfTMmeasurements + this.numberOfTMmeasurements;
        this.setMeasurementsTM = true;
        this.setMeasurements = true;
        this.setErrorsTM = true;
    }

    public void enterTMmodeData(double[] var1, double[] var2, double[] var3) {
        int var4 = var1.length;
        int var5 = var2.length;
        if (var5 != var4) {
            throw new IllegalArgumentException("number of thicknesses, " + var4 + ", does not equal the number of effective refractive indices, " + var5);
        } else {
            int var6 = var3.length;
            if (var6 != var4) {
                throw new IllegalArgumentException("number of thicknesses, " + var4 + ", does not equal the number of mode numbers, " + var6);
            } else {
                int var7;
                if (this.setMeasurementsTM) {
                    if (this.setErrorsTM) {
                        throw new IllegalArgumentException("All Entered data must either all have associated errors entered or all have no associated errors entered");
                    }

                    var7 = this.numberOfTMmeasurements + var4;
                    double[][] var8 = new double[var7][4];

                    int var9;
                    for(var9 = 0; var9 < this.numberOfTMmeasurements; ++var9) {
                        for(int var10 = 0; var10 < 4; ++var10) {
                            var8[var9][var10] = this.measurementsTM[var9][var10];
                        }
                    }

                    for(var9 = 0; var9 < var4; ++var9) {
                        var8[this.numberOfTMmeasurements + var9][0] = var1[var9];
                        var8[this.numberOfTMmeasurements + var9][1] = var2[var9];
                        var8[this.numberOfTMmeasurements + var9][2] = 1.0D;
                        var8[this.numberOfTMmeasurements + var9][3] = var3[var9];
                    }

                    this.measurementsTM = var8;
                    this.numberOfTMmeasurements = var7;
                } else {
                    this.numberOfTMmeasurements = var4;
                    this.measurementsTM = new double[this.numberOfTMmeasurements][4];

                    for(var7 = 0; var7 < this.numberOfTMmeasurements; ++var7) {
                        this.measurementsTM[var7][0] = var1[var7];
                        this.measurementsTM[var7][1] = var2[var7];
                        this.measurementsTM[var7][2] = 1.0D;
                        this.measurementsTM[var7][3] = var3[var7];
                    }
                }

                this.numberOfMeasurements = this.numberOfTMmeasurements + this.numberOfTMmeasurements;
                this.setMeasurementsTM = true;
                this.setMeasurements = true;
            }
        }
    }

    public void enterTMmodeData(double[] var1, double[] var2, double[] var3, double[] var4) {
        int var5 = var1.length;
        int var6 = var2.length;
        if (var6 != var5) {
            throw new IllegalArgumentException("number of thicknesses, " + var5 + ", does not equal the number of effective refractive indices, " + var6);
        } else {
            int var7 = var4.length;
            if (var7 != var5) {
                throw new IllegalArgumentException("number of thicknesses, " + var5 + ", does not equal the number of mode numbers, " + var7);
            } else {
                int var8;
                if (this.setMeasurementsTM) {
                    if (!this.setErrorsTM) {
                        throw new IllegalArgumentException("All Entered data must either all have associated errors entered or all have no associated errors entered");
                    }

                    var8 = this.numberOfTMmeasurements + var5;
                    double[][] var9 = new double[var8][4];

                    int var10;
                    for(var10 = 0; var10 < this.numberOfTMmeasurements; ++var10) {
                        for(int var11 = 0; var11 < 4; ++var11) {
                            var9[var10][var11] = this.measurementsTM[var10][var11];
                        }
                    }

                    for(var10 = 0; var10 < var5; ++var10) {
                        var9[this.numberOfTMmeasurements + var10][0] = var1[var10];
                        var9[this.numberOfTMmeasurements + var10][1] = var2[var10];
                        var9[this.numberOfTMmeasurements + var10][2] = var3[var10];
                        var9[this.numberOfTMmeasurements + var10][3] = var4[var10];
                    }

                    this.measurementsTM = var9;
                    this.numberOfTMmeasurements = var8;
                } else {
                    this.numberOfTMmeasurements = var5;
                    this.measurementsTM = new double[this.numberOfTMmeasurements][4];

                    for(var8 = 0; var8 < this.numberOfTMmeasurements; ++var8) {
                        this.measurementsTM[var8][0] = var1[var8];
                        this.measurementsTM[var8][1] = var2[var8];
                        this.measurementsTM[var8][2] = var3[var8];
                        this.measurementsTM[var8][3] = var4[var8];
                    }
                }

                this.numberOfMeasurements = this.numberOfTMmeasurements + this.numberOfTMmeasurements;
                this.setMeasurementsTM = true;
                this.setMeasurements = true;
                this.setErrorsTM = true;
            }
        }
    }

    public void clearData() {
        this.numberOfMeasurements = 0;
        this.setMeasurements = false;
        this.setWeights = false;
        this.numberOfTEmeasurements = 0;
        this.setMeasurementsTE = false;
        this.setErrorsTE = false;
        this.numberOfTMmeasurements = 0;
        this.setMeasurementsTM = false;
        this.setErrorsTM = false;
    }

    public void setWavelength(double var1) {
        this.wavelength = var1;
        this.setWavelength = true;
        this.ko = 6.283185307179586D / this.wavelength;
        if (!this.setSuperstrate) {
            this.superstrateRefractiveIndex = RefractiveIndex.air(this.wavelength);
        }

    }

    public void setSubstrateRefractiveIndex(double var1) {
        this.substrateRefractiveIndex = var1;
        this.substrateRefractiveIndex2 = var1 * var1;
        this.setSubstrate = true;
    }

    public void setSuperstrateRefractiveIndex(double var1) {
        this.superstrateRefractiveIndex = var1;
        this.superstrateRefractiveIndex2 = var1 * var1;
        this.setSuperstrate = true;
    }

    public double getSuperstrateRefractiveIndex() {
        if (!this.superCalculationDone && this.setCore) {
            this.calcSuperstrateRefractiveIndex();
        }

        return this.superstrateRefractiveIndex;
    }

    public double getStandardDeviationSuperstrateRefractiveIndex() {
        if (!this.superCalculationDone && this.setCore) {
            this.calcSuperstrateRefractiveIndex();
        }

        if (this.setCore) {
            if (this.numberOfTMmeasurements + this.numberOfTEmeasurements == 1) {
                System.out.println("Method: getStandardDeviationSuperstrateRefractiveIndex - Only one measurement entered - NO standard deviation returned");
            }
        } else {
            System.out.println("Method: getStandardDeviationSuperstrateRefractiveIndex - Superstrate refractive index was entered and NOT calculated - NO standard deviation returned");
        }

        return this.sdCoreFilmRefractiveIndex;
    }

    public void setCoreLayerRefractiveIndex(double var1) {
        this.coreFilmRefractiveIndex = var1;
        this.coreFilmRefractiveIndex2 = var1 * var1;
        this.setCore = true;
    }

    public double[] getTEmodeCoreFilmRefractiveIndices() {
        if (!this.calculationDone) {
            this.calcCoreFilmRefractiveIndices();
        }

        if (this.numberOfTEmeasurements == 0) {
            System.out.println("Method: getTEmodeCoreFilmRefractiveIndices - NO TE mode data entered - NO refractive indices returned");
        }

        return this.coreFilmTEmodeRefractiveIndices;
    }

    public double[] getTMmodeCoreFilmRefractiveIndices() {
        if (!this.calculationDone) {
            this.calcCoreFilmRefractiveIndices();
        }

        if (this.numberOfTMmeasurements == 0) {
            System.out.println("Method: getTMmodeCoreFilmRefractiveIndices - NO TM mode data entered - NO refractive indices returned");
        }

        return this.coreFilmTMmodeRefractiveIndices;
    }

    public double getMeanTEmodeCoreFilmRefractiveIndex() {
        if (!this.calculationDone) {
            this.calcCoreFilmRefractiveIndices();
        }

        if (this.numberOfTEmeasurements == 0) {
            System.out.println("Method: getMeanTEmodeCoreFilmRefractiveIndices - NO TE mode data entered - NO refractive index returned");
        }

        return this.meanTEmodeCoreFilmRefractiveIndex;
    }

    public double getMeanTMmodeCoreFilmRefractiveIndex() {
        if (!this.calculationDone) {
            this.calcCoreFilmRefractiveIndices();
        }

        if (this.numberOfTMmeasurements == 0) {
            System.out.println("Method: getMeanTMmodeCoreFilmRefractiveIndices - NO TM mode data entered - NO refractive index returned");
        }

        return this.meanTMmodeCoreFilmRefractiveIndex;
    }

    public double getMeanCoreFilmRefractiveIndex() {
        if (!this.calculationDone) {
            this.calcCoreFilmRefractiveIndices();
        }

        return this.meanCoreFilmRefractiveIndex;
    }

    public double getCoreFilmRefractiveIndex() {
        if (!this.calculationDone && !this.setCore) {
            this.calcCoreFilmRefractiveIndices();
        }

        return this.coreFilmRefractiveIndex;
    }

    public double getStandardDeviationTEmodeCoreFilmRefractiveIndex() {
        if (!this.calculationDone) {
            this.calcCoreFilmRefractiveIndices();
        }

        if (this.numberOfTEmeasurements == 0) {
            System.out.println("Method: getStandardDeviationTEmodeCoreFilmRefractiveIndex - NO TE mode data entered - NO standard deviation returned");
        }

        if (this.numberOfTEmeasurements == 1) {
            System.out.println("Method: getStandardDeviationTEmodeCoreFilmRefractiveIndex - Only one measurement entered - NO standard deviation returned");
        }

        return this.sdTEmodeCoreFilmRefractiveIndex;
    }

    public double getStandardDeviationTMmodeCoreFilmRefractiveIndex() {
        if (!this.calculationDone) {
            this.calcCoreFilmRefractiveIndices();
        }

        if (this.numberOfTMmeasurements == 0) {
            System.out.println("Method: getStandardDeviationTMmodeCoreFilmRefractiveIndex - NO TM mode data entered - NO standard deviation returned");
        }

        if (this.numberOfTMmeasurements == 1) {
            System.out.println("Method: getStandardDeviationTMmodeCoreFilmRefractiveIndex - Only one measurement entered - NO standard deviation returned");
        }

        return this.sdTMmodeCoreFilmRefractiveIndex;
    }

    public double getStandardDeviationCoreFilmRefractiveIndex() {
        if (!this.calculationDone) {
            this.calcCoreFilmRefractiveIndices();
        }

        if (this.numberOfTMmeasurements + this.numberOfTEmeasurements == 1) {
            System.out.println("Method: getStandardDeviationCoreFilmRefractiveIndex - Only one measurement entered - NO standard deviation returned");
        }

        return this.sdCoreFilmRefractiveIndex;
    }

    public double[][] getTEmodeExperimentalEffectiveRefractiveIndices() {
        double[][] var1 = (double[][])null;
        if (this.numberOfTEmeasurements == 0) {
            System.out.println("Method: getTEmodeExperimentalEffectiveRefractiveIndices - NO TE mode data entered - NO effective refractive indices returned");
        } else {
            var1 = new double[2][this.numberOfTEmeasurements];
            var1[0] = this.thicknessesUsedTE;

            for(int var2 = 0; var2 < this.numberOfTEmeasurements; ++var2) {
                var1[1][var2] = this.measurementsTE[var2][1];
            }
        }

        return var1;
    }

    public double[][] getTEmodeEffectiveRefractiveIndicesErrors() {
        double[][] var1 = (double[][])null;
        if (this.numberOfTEmeasurements == 0) {
            System.out.println("Method: getTEmodeExperimentalEffectiveRefractiveIndices - NO TE mode data entered - NO errors returned");
        } else if (!this.setErrorsTE) {
            System.out.println("Method: getTEmodeExperimentalEffectiveRefractiveIndices - NO TE mode errors entered - NO errors returned");
        } else {
            var1 = new double[2][this.numberOfTEmeasurements];
            var1[0] = this.thicknessesUsedTE;

            for(int var2 = 0; var2 < this.numberOfTEmeasurements; ++var2) {
                var1[1][var2] = this.measurementsTE[var2][2];
            }
        }

        return var1;
    }

    public double[][] getTMmodeExperimentalEffectiveRefractiveIndices() {
        double[][] var1 = (double[][])null;
        if (this.numberOfTMmeasurements == 0) {
            System.out.println("Method: getTMmodeExperimentalEffectiveRefractiveIndices - NO TM mode data entered - NO effective refractive indices returned");
        } else {
            var1 = new double[2][this.numberOfTMmeasurements];
            var1[0] = this.thicknessesUsedTM;

            for(int var2 = 0; var2 < this.numberOfTMmeasurements; ++var2) {
                var1[1][var2] = this.measurementsTM[var2][1];
            }
        }

        return var1;
    }

    public double[][] getTMmodeEffectiveRefractiveIndicesErrors() {
        double[][] var1 = (double[][])null;
        if (this.numberOfTMmeasurements == 0) {
            System.out.println("Method: getTMmodeExperimentalEffectiveRefractiveIndices - NO TM mode data entered - NO errors returned");
        } else if (!this.setErrorsTM) {
            System.out.println("Method: getTMmodeExperimentalEffectiveRefractiveIndices - NO TM mode errors entered - NO errors returned");
        } else {
            var1 = new double[2][this.numberOfTMmeasurements];
            var1[0] = this.thicknessesUsedTM;

            for(int var2 = 0; var2 < this.numberOfTMmeasurements; ++var2) {
                var1[1][var2] = this.measurementsTM[var2][2];
            }
        }

        return var1;
    }

    public double[][] getTEmodeCalculatedEffectiveRefractiveIndices() {
        if (!this.calculationDone) {
            this.calcCoreFilmRefractiveIndices();
        }

        if (this.numberOfTEmeasurements == 0) {
            System.out.println("Method: getStandardDeviationTEmodeCoreFilmRefractiveIndices - NO TE mode data entered - NO effective refractive indices returned");
        }

        double[][] var1 = new double[2][this.numberOfTEmeasurements];
        FunctTEplot var2 = new FunctTEplot();
        var2.substrateRefractiveIndex2 = this.substrateRefractiveIndex2;
        var2.superstrateRefractiveIndex2 = this.superstrateRefractiveIndex2;
        var2.coreFilmRefractiveIndex2 = this.coreFilmRefractiveIndex2;
        var2.prismRefractiveIndex2 = this.prismRefractiveIndex2;
        var2.prismToWaveguideGap = this.prismToWaveguideGap;
        var2.setPrismToWaveguideGap = this.setPrismToWaveguideGap;
        var2.ko = this.ko;
        this.lowerBound = Math.max(this.substrateRefractiveIndex, this.superstrateRefractiveIndex);
        this.upperBound = Math.min(this.coreFilmRefractiveIndex, this.prismRefractiveIndex);

        for(int var3 = 0; var3 < this.numberOfTEmeasurements; ++var3) {
            var2.thickness = this.measurementsTE[var3][0];
            var2.modeNumber = this.measurementsTE[var3][3];
            RealRoot var4 = new RealRoot();
            var4.noBoundsExtensions();
            var4.setTolerance(this.tolerance);
            this.calcEffectRefrIndicesTE[var3] = var4.bisect(var2, this.lowerBound, this.upperBound);
        }

        var1[0] = this.thicknessesUsedTE;
        var1[1] = this.calcEffectRefrIndicesTE;
        return var1;
    }

    public double[][] getTMmodeCalculatedEffectiveRefractiveIndices() {
        if (!this.calculationDone) {
            this.calcCoreFilmRefractiveIndices();
        }

        if (this.numberOfTMmeasurements == 0) {
            System.out.println("Method: getStandardDeviationTMmodeCoreFilmRefractiveIndices - NO TM mode data entered - NO effective refractive indices returned");
        }

        double[][] var1 = new double[2][this.numberOfTMmeasurements];
        FunctTMplot var2 = new FunctTMplot();
        var2.substrateRefractiveIndex2 = this.substrateRefractiveIndex2;
        var2.superstrateRefractiveIndex2 = this.superstrateRefractiveIndex2;
        var2.coreFilmRefractiveIndex2 = this.coreFilmRefractiveIndex2;
        var2.prismRefractiveIndex2 = this.prismRefractiveIndex2;
        var2.prismToWaveguideGap = this.prismToWaveguideGap;
        var2.setPrismToWaveguideGap = this.setPrismToWaveguideGap;
        var2.ko = this.ko;
        this.lowerBound = Math.max(this.substrateRefractiveIndex, this.superstrateRefractiveIndex);
        this.upperBound = Math.min(this.coreFilmRefractiveIndex, this.prismRefractiveIndex);

        for(int var3 = 0; var3 < this.numberOfTMmeasurements; ++var3) {
            var2.thickness = this.measurementsTM[var3][0];
            var2.modeNumber = this.measurementsTM[var3][3];
            RealRoot var4 = new RealRoot();
            var4.noBoundsExtensions();
            var4.setTolerance(this.tolerance);
            this.calcEffectRefrIndicesTM[var3] = var4.bisect(var2, this.lowerBound, this.upperBound);
        }

        var1[0] = this.thicknessesUsedTM;
        var1[1] = this.calcEffectRefrIndicesTM;
        return var1;
    }

    public void calcCoreFilmRefractiveIndices() {
        if (!this.setMeasurements) {
            throw new IllegalArgumentException("Either no thickness, angle/effective refractive index, mode number data has been entered or a key subclass variable, e.g. coupling prism corner angle has not been entered");
        } else if (!this.setWavelength) {
            throw new IllegalArgumentException("No wavelength has been entered");
        } else if (!this.setSubstrate) {
            throw new IllegalArgumentException("No substrate refractive index has been entered");
        } else {
            this.lowerBound = Math.max(this.substrateRefractiveIndex, this.superstrateRefractiveIndex);
            this.upperBound = 0.0D;
            if (this.numberOfTEmeasurements > 0) {
                this.eliminatedTE = new boolean[this.numberOfTEmeasurements];
            }

            int var1 = 0;

            int var2;
            for(var2 = 0; var2 < this.numberOfTEmeasurements; ++var2) {
                this.eliminatedTE[var2] = false;
                if (this.measurementsTE[var2][1] < this.lowerBound) {
                    System.out.println("TE mode measurement point, " + var2 + ", eliminated as the effective refractive index, " + this.measurementsTE[var2][1] + ", lies below the physical limit, " + this.lowerBound);
                    this.eliminatedTE[var2] = true;
                    ++var1;
                } else if (this.upperBound < this.measurementsTE[var2][1]) {
                    this.upperBound = this.measurementsTE[var2][1];
                }
            }

            int var5;
            if (var1 > 0) {
                var2 = this.numberOfTEmeasurements - var1;
                if (var2 == 0) {
                    this.numberOfTEmeasurements = 0;
                } else {
                    double[][] var3 = new double[var2][3];
                    int var4 = 0;

                    for(var5 = 0; var5 < this.numberOfTEmeasurements; ++var5) {
                        if (!this.eliminatedTE[var5]) {
                            var3[var4][0] = this.measurementsTE[var5][0];
                            var3[var4][1] = this.measurementsTE[var5][1];
                            var3[var4][2] = this.measurementsTE[var5][2];
                            var3[var4][3] = this.measurementsTE[var5][3];
                            ++var4;
                        }
                    }

                    this.measurementsTE = var3;
                    this.numberOfTEmeasurements = var2;
                    this.numberOfMeasurements = this.numberOfTEmeasurements + this.numberOfTMmeasurements;
                }
            }

            this.thicknessesUsedTE = new double[this.numberOfTEmeasurements];
            this.calcEffectRefrIndicesTE = new double[this.numberOfTEmeasurements];

            for(var2 = 0; var2 < this.numberOfTEmeasurements; ++var2) {
                this.thicknessesUsedTE[var2] = this.measurementsTE[var2][0];
            }

            this.maximumTEmodeEffectiveRefractiveIndex = this.upperBound;
            this.upperBound = 0.0D;
            if (this.numberOfTMmeasurements > 0) {
                this.eliminatedTM = new boolean[this.numberOfTMmeasurements];
            }

            var2 = 0;

            int var7;
            for(var7 = 0; var7 < this.numberOfTMmeasurements; ++var7) {
                this.eliminatedTM[var7] = false;
                if (this.measurementsTM[var7][1] < this.lowerBound) {
                    System.out.println("TM mode measurement point, " + var7 + ", eliminated as the effective refractive index, " + this.measurementsTM[var7][1] + ", lies below the physical limit, " + this.lowerBound);
                    this.eliminatedTM[var7] = true;
                    ++var2;
                } else if (this.upperBound < this.measurementsTM[var7][1]) {
                    this.upperBound = this.measurementsTM[var7][1];
                }
            }

            if (var2 > 0) {
                var7 = this.numberOfTMmeasurements - var2;
                if (var7 == 0) {
                    this.numberOfTMmeasurements = 0;
                } else {
                    double[][] var8 = new double[var7][3];
                    var5 = 0;

                    for(int var6 = 0; var6 < this.numberOfTMmeasurements; ++var6) {
                        if (!this.eliminatedTM[var6]) {
                            var8[var5][0] = this.measurementsTM[var6][0];
                            var8[var5][1] = this.measurementsTM[var6][1];
                            var8[var5][2] = this.measurementsTM[var6][2];
                            var8[var5][3] = this.measurementsTM[var6][3];
                            ++var5;
                        }
                    }

                    this.measurementsTM = var8;
                    this.numberOfTMmeasurements = var7;
                    this.numberOfMeasurements = this.numberOfTEmeasurements + this.numberOfTMmeasurements;
                }
            }

            this.thicknessesUsedTM = new double[this.numberOfTMmeasurements];
            this.calcEffectRefrIndicesTM = new double[this.numberOfTMmeasurements];

            for(var7 = 0; var7 < this.numberOfTMmeasurements; ++var7) {
                this.thicknessesUsedTM[var7] = this.measurementsTM[var7][0];
            }

            this.maximumTMmodeEffectiveRefractiveIndex = this.upperBound;
            if (this.numberOfMeasurements == 0) {
                throw new IllegalArgumentException("All data points rejected as lying outside the physically meaningful bounds");
            } else {
                if (this.fixedPrismToWaveguideGap) {
                    this.calcCoreFilmRefractiveIndicesFixedGap();
                } else {
                    this.calcCoreFilmRefractiveIndicesEstimatedGap();
                }

            }
        }
    }

    public void calcCoreFilmRefractiveIndicesEstimatedGap() {
        ArrayList var1 = new ArrayList();
        this.prismToWaveguideGap = 10.0D;
        this.fixedPrismToWaveguideGap = true;
        double[] var2 = new double[this.numberOfMeasurements];
        double[] var3 = new double[this.numberOfMeasurements];

        int var4;
        for(var4 = 0; var4 < this.numberOfTEmeasurements; ++var4) {
            var2[var4] = this.measurementsTE[var4][1];
        }

        for(var4 = 0; var4 < this.numberOfTMmeasurements; ++var4) {
            var2[var4 + this.numberOfTEmeasurements] = this.measurementsTM[var4][1];
        }

        double var15 = 0.0D;
        double var6 = 1.0D / 0.0;
        int var8 = 0;
        boolean var9 = true;

        while(true) {
            while(var9) {
                this.setCore = false;
                this.calculationDone = false;
                this.fixedPrismToWaveguideGap = true;
                this.setPrismToWaveguideGap = true;
                double var10 = this.getMeanCoreFilmRefractiveIndex();
                if (var10 != var10) {
                    System.out.println("NaN");
                    var9 = false;
                } else {
                    double[][] var12 = this.getTEmodeCalculatedEffectiveRefractiveIndices();

                    for(int var13 = 0; var13 < this.numberOfTEmeasurements; ++var13) {
                        var3[var13] = var12[1][var13];
                    }

                    double[][] var16 = this.getTMmodeCalculatedEffectiveRefractiveIndices();

                    int var14;
                    for(var14 = 0; var14 < this.numberOfTMmeasurements; ++var14) {
                        var3[var14 + this.numberOfTEmeasurements] = var16[1][var14];
                    }

                    var15 = 0.0D;

                    for(var14 = 0; var14 < this.numberOfMeasurements; ++var14) {
                        var15 += Fmath.square(var2[var14] - var3[var14]);
                    }

                    System.out.println(this.prismToWaveguideGap + " " + var10 + " " + var15);
                    var1.add(new Double(var10));
                    var1.add(new Double(var15));
                    ++var8;
                    this.prismToWaveguideGap /= 2.0D;
                    if (this.prismToWaveguideGap < 1.0E-10D) {
                        var9 = false;
                    }
                }
            }

            return;
        }
    }

    public void calcCoreFilmRefractiveIndicesFixedGap() {
        if (this.numberOfTEmeasurements > 0) {
            this.calcTEmodeCoreFilmRefractiveIndices();
        }

        if (this.numberOfTMmeasurements > 0) {
            this.calcTMmodeCoreFilmRefractiveIndices();
        }

        if (this.numberOfTEmeasurements > 0 && this.numberOfTMmeasurements == 0) {
            this.meanCoreFilmRefractiveIndex = this.meanTEmodeCoreFilmRefractiveIndex;
            this.coreFilmRefractiveIndex = this.meanCoreFilmRefractiveIndex;
            this.sdCoreFilmRefractiveIndex = this.sdTEmodeCoreFilmRefractiveIndex;
        } else if (this.numberOfTMmeasurements > 0 && this.numberOfTEmeasurements == 0) {
            this.meanCoreFilmRefractiveIndex = this.meanTMmodeCoreFilmRefractiveIndex;
            this.coreFilmRefractiveIndex = this.meanCoreFilmRefractiveIndex;
            this.sdCoreFilmRefractiveIndex = this.sdTMmodeCoreFilmRefractiveIndex;
        } else {
            double[] var1 = new double[this.numberOfMeasurements];
            double[] var2 = new double[this.numberOfMeasurements];

            int var3;
            for(var3 = 0; var3 < this.numberOfTEmeasurements; ++var3) {
                var1[var3] = this.coreFilmTEmodeRefractiveIndices[var3];
                var2[var3] = this.measurementsTE[var3][2];
            }

            for(var3 = 0; var3 < this.numberOfTMmeasurements; ++var3) {
                var1[var3 + this.numberOfTEmeasurements] = this.coreFilmTMmodeRefractiveIndices[var3];
                var2[var3 + this.numberOfTEmeasurements] = this.measurementsTM[var3][2];
            }

            this.meanCoreFilmRefractiveIndex = Stat.mean(var1, var2);
            this.sdCoreFilmRefractiveIndex = Stat.standardDeviation(var1, var2);
            this.coreFilmRefractiveIndex = this.meanCoreFilmRefractiveIndex;
        }

        this.meanCoreFilmRefractiveIndex2 = this.meanCoreFilmRefractiveIndex * this.meanCoreFilmRefractiveIndex;
        this.coreFilmRefractiveIndex2 = this.meanCoreFilmRefractiveIndex2;
        this.maximumEffectiveRefractiveIndex = Math.max(this.maximumTEmodeEffectiveRefractiveIndex, this.maximumTMmodeEffectiveRefractiveIndex);
        this.setCore = true;
        this.calculationDone = true;
    }

    public void calcTEmodeCoreFilmRefractiveIndices() {
        this.coreFilmTEmodeRefractiveIndices = new double[this.numberOfTEmeasurements];
        FunctTE var1 = new FunctTE();
        var1.substrateRefractiveIndex2 = this.substrateRefractiveIndex2;
        var1.superstrateRefractiveIndex2 = this.superstrateRefractiveIndex2;
        var1.prismRefractiveIndex2 = this.prismRefractiveIndex2;
        var1.prismToWaveguideGap = this.prismToWaveguideGap;
        var1.setPrismToWaveguideGap = this.setPrismToWaveguideGap;
        var1.ko = this.ko;
        double[] var2 = new double[this.numberOfTEmeasurements];
        this.lowerBound = this.maximumTEmodeEffectiveRefractiveIndex;
        this.upperBound = 2.0D * this.lowerBound;

        for(int var3 = 0; var3 < this.numberOfTEmeasurements; ++var3) {
            var2[var3] = this.measurementsTE[var3][2];
            var1.thickness = this.measurementsTE[var3][0];
            var1.effectiveRefractiveIndex2 = this.measurementsTE[var3][1] * this.measurementsTE[var3][1];
            var1.modeNumber = this.measurementsTE[var3][3];
            RealRoot var4 = new RealRoot();
            var4.noLowerBoundExtension();
            var4.setTolerance(this.tolerance);
            this.coreFilmTEmodeRefractiveIndices[var3] = var4.bisect(var1, this.lowerBound, this.upperBound);
        }

        if (this.numberOfTEmeasurements > 1) {
            this.meanTEmodeCoreFilmRefractiveIndex = Stat.mean(this.coreFilmTEmodeRefractiveIndices, var2);
            this.sdTEmodeCoreFilmRefractiveIndex = Stat.standardDeviation(this.coreFilmTEmodeRefractiveIndices, var2);
        } else {
            this.meanTEmodeCoreFilmRefractiveIndex = this.coreFilmTEmodeRefractiveIndices[0];
        }

    }

    public void calcTMmodeCoreFilmRefractiveIndices() {
        this.coreFilmTMmodeRefractiveIndices = new double[this.numberOfTMmeasurements];
        FunctTM var1 = new FunctTM();
        var1.substrateRefractiveIndex2 = this.substrateRefractiveIndex2;
        var1.superstrateRefractiveIndex2 = this.superstrateRefractiveIndex2;
        var1.prismRefractiveIndex2 = this.prismRefractiveIndex2;
        var1.prismToWaveguideGap = this.prismToWaveguideGap;
        var1.setPrismToWaveguideGap = this.setPrismToWaveguideGap;
        var1.ko = this.ko;
        double[] var2 = new double[this.numberOfTMmeasurements];
        this.lowerBound = this.maximumTMmodeEffectiveRefractiveIndex;
        this.upperBound = 2.0D * this.lowerBound;

        for(int var3 = 0; var3 < this.numberOfTMmeasurements; ++var3) {
            var2[var3] = this.measurementsTM[var3][2];
            var1.thickness = this.measurementsTM[var3][0];
            var1.effectiveRefractiveIndex2 = this.measurementsTM[var3][1] * this.measurementsTM[var3][1];
            var1.modeNumber = this.measurementsTM[var3][3];
            RealRoot var4 = new RealRoot();
            var4.noLowerBoundExtension();
            var4.setTolerance(this.tolerance);
            this.coreFilmTMmodeRefractiveIndices[var3] = var4.bisect(var1, this.lowerBound, this.upperBound);
        }

        if (this.numberOfTMmeasurements > 1) {
            this.meanTMmodeCoreFilmRefractiveIndex = Stat.mean(this.coreFilmTMmodeRefractiveIndices, var2);
            this.sdTMmodeCoreFilmRefractiveIndex = Stat.standardDeviation(this.coreFilmTMmodeRefractiveIndices, var2);
        } else {
            this.meanTMmodeCoreFilmRefractiveIndex = this.coreFilmTMmodeRefractiveIndices[0];
        }

    }

    public double[][] dispersionCurveTE(double var1, double var3, int var5, double var6) {
        if (!this.setWavelength) {
            throw new IllegalArgumentException("No wavelength has been entered");
        } else if (!this.setSubstrate) {
            throw new IllegalArgumentException("No substrate refractive index has been entered");
        } else if (!this.setCore) {
            throw new IllegalArgumentException("No core film refractive index has been calculated or entered");
        } else {
            double[] var8 = new double[var5];
            double[] var9 = new double[var5];
            double[][] var10 = new double[2][var5];
            double var11 = (Fmath.log10(var3) - Fmath.log10(var1)) / (double)(var5 - 1);
            var8[0] = Fmath.log10(var1);
            var8[var5 - 1] = Fmath.log10(var3);

            for(int var13 = 1; var13 < var5 - 1; ++var13) {
                var8[var13] = var8[var13 - 1] + var11;
            }

            var10[0] = var8;
            FunctTEplot var16 = new FunctTEplot();
            var16.substrateRefractiveIndex2 = this.substrateRefractiveIndex2;
            var16.superstrateRefractiveIndex2 = this.superstrateRefractiveIndex2;
            var16.coreFilmRefractiveIndex2 = this.coreFilmRefractiveIndex2;
            var16.prismRefractiveIndex2 = this.prismRefractiveIndex2;
            var16.prismToWaveguideGap = this.prismToWaveguideGap;
            var16.setPrismToWaveguideGap = this.setPrismToWaveguideGap;
            var16.ko = this.ko;
            var16.modeNumber = var6;
            this.lowerBound = Math.max(this.substrateRefractiveIndex, this.superstrateRefractiveIndex);
            this.upperBound = Math.min(this.coreFilmRefractiveIndex, this.prismRefractiveIndex);

            for(int var14 = 0; var14 < var5; ++var14) {
                var16.thickness = Math.pow(10.0D, var8[var14]);
                RealRoot var15 = new RealRoot();
                var15.noBoundsExtensions();
                var15.setTolerance(this.tolerance);
                var9[var14] = var15.bisect(var16, this.lowerBound, this.upperBound);
            }

            var10[1] = var9;
            return var10;
        }
    }

    public double[][] dispersionCurveTM(double var1, double var3, int var5, double var6) {
        if (!this.setWavelength) {
            throw new IllegalArgumentException("No wavelength has been entered");
        } else if (!this.setSubstrate) {
            throw new IllegalArgumentException("No substrate refractive index has been entered");
        } else if (!this.setCore) {
            throw new IllegalArgumentException("No core film refractive index has been calculated or entered");
        } else {
            double[] var8 = new double[var5];
            double[] var9 = new double[var5];
            double[][] var10 = new double[2][var5];
            double var11 = (Fmath.log10(var3) - Fmath.log10(var1)) / (double)(var5 - 1);
            var8[0] = Fmath.log10(var1);
            var8[var5 - 1] = Fmath.log10(var3);

            for(int var13 = 1; var13 < var5 - 1; ++var13) {
                var8[var13] = var8[var13 - 1] + var11;
            }

            var10[0] = var8;
            FunctTMplot var16 = new FunctTMplot();
            var16.substrateRefractiveIndex2 = this.substrateRefractiveIndex2;
            var16.superstrateRefractiveIndex2 = this.superstrateRefractiveIndex2;
            var16.coreFilmRefractiveIndex2 = this.coreFilmRefractiveIndex2;
            var16.prismRefractiveIndex2 = this.prismRefractiveIndex2;
            var16.prismToWaveguideGap = this.prismToWaveguideGap;
            var16.setPrismToWaveguideGap = this.setPrismToWaveguideGap;
            var16.ko = this.ko;
            var16.modeNumber = var6;
            this.lowerBound = Math.max(this.substrateRefractiveIndex, this.superstrateRefractiveIndex);
            this.upperBound = Math.min(this.coreFilmRefractiveIndex, this.prismRefractiveIndex);

            for(int var14 = 0; var14 < var5; ++var14) {
                var16.thickness = Math.pow(10.0D, var8[var14]);
                RealRoot var15 = new RealRoot();
                var15.noBoundsExtensions();
                var15.setTolerance(this.tolerance);
                var9[var14] = var15.bisect(var16, this.lowerBound, this.upperBound);
            }

            var10[1] = var9;
            return var10;
        }
    }

    public double[][] plotDispersionCurveTE(double var1, double var3, int var5, double var6) {
        String var8 = " ";
        return this.plotDispersionCurveTE(var1, var3, var5, var6, var8);
    }

    public double[][] plotDispersionCurveTE(double var1, double var3, int var5, double var6, String var8) {
        double[][] var9 = this.dispersionCurveTE(var1, var3, var5, var6);
        PlotGraph var10 = new PlotGraph(var9);
        byte var11 = 3;
        if (var5 < 100) {
            var11 = 1;
        }

        var10.setLine(var11);
        var10.setPoint(0);
        String var12 = "Dispersion curve: TE mode  -  mode number " + (int)var6;
        var10.setGraphTitle(var12);
        var10.setGraphTitle2(var8);
        var10.setXaxisLegend("Log10( Core Film Thickness / metres )");
        var10.setYaxisLegend("Effective Refractive Index (kz/ko)");
        var10.setFrame();
        return var9;
    }

    public double[][] plotDispersionCurveTM(double var1, double var3, int var5, double var6) {
        String var8 = " ";
        return this.plotDispersionCurveTM(var1, var3, var5, var6, var8);
    }

    public double[][] plotDispersionCurveTM(double var1, double var3, int var5, double var6, String var8) {
        double[][] var9 = this.dispersionCurveTM(var1, var3, var5, var6);
        PlotGraph var10 = new PlotGraph(var9);
        byte var11 = 3;
        if (var5 < 100) {
            var11 = 1;
        }

        var10.setLine(var11);
        var10.setPoint(0);
        String var12 = "Dispersion curve: TM mode  -  mode number " + (int)var6;
        var10.setGraphTitle(var12);
        var10.setGraphTitle2(var8);
        var10.setXaxisLegend("Log10( Core Film Thickness / metres )");
        var10.setYaxisLegend("Effective Refractive Index (kz/ko)");
        var10.setFrame();
        return var9;
    }

    public void plotFittedDispersionCurves() {
        String var1 = "PlanarWaveguide.plotDispersion - Dispersion Plot";
        this.plotFittedDispersionCurve(var1);
    }

    public void plotFittedDispersionCurve(String var1) {
        if (!this.calculationDone) {
            this.calcCoreFilmRefractiveIndices();
        }

        ArrayList var2 = null;
        int var3 = 0;
        int var4 = 0;
        int var5 = 0;
        int var8;
        int var9;
        if (this.numberOfTEmeasurements > 0) {
            var2 = new ArrayList();
            boolean var6 = true;
            int var7 = 0;
            var8 = 0;

            while(var6) {
                var9 = 0;

                for(int var10 = 0; var10 < this.numberOfTEmeasurements; ++var10) {
                    if (this.measurementsTE[var10][3] == (double)var7) {
                        ++var9;
                        ++var8;
                        var2.add(new Double(this.measurementsTE[var10][0]));
                        var2.add(new Double(this.measurementsTE[var10][1]));
                    }
                }

                var2.add(2 * var7, new Integer(var7));
                var2.add(2 * var7 + 1, new Integer(var9));
                if (var9 > 0) {
                    ++var3;
                }

                if (var9 > var5) {
                    var5 = var9;
                }

                if (var8 == this.numberOfTEmeasurements) {
                    var6 = false;
                } else {
                    ++var7;
                }
            }

            var4 = var7;
        }

        ArrayList var27 = null;
        var8 = 0;
        var9 = 0;
        int var13;
        int var14;
        if (this.numberOfTMmeasurements > 0) {
            var27 = new ArrayList();
            boolean var28 = true;
            int var11 = 0;
            int var12 = 0;

            while(var28) {
                var13 = 0;

                for(var14 = 0; var14 < this.numberOfTMmeasurements; ++var14) {
                    if (this.measurementsTM[var14][3] == (double)var11) {
                        ++var13;
                        ++var12;
                        var27.add(new Double(this.measurementsTM[var14][0]));
                        var27.add(new Double(this.measurementsTM[var14][1]));
                    }
                }

                var27.add(2 * var11, new Integer(var11));
                var27.add(2 * var11 + 1, new Integer(var13));
                if (var13 > 0) {
                    ++var8;
                }

                if (var13 > var5) {
                    var5 = var13;
                }

                if (var12 == this.numberOfTMmeasurements) {
                    var28 = false;
                } else {
                    ++var11;
                }
            }

            var9 = var11;
        }

        int var26 = var3 + var8;
        var26 *= 2;
        if (var5 < 200) {
            var5 = 200;
        }

        double[][] var29 = PlotGraph.fillData(var26, var5);
        double[] var30 = new double[var26];
        String[] var31 = new String[var26];
        var13 = 0;
        var14 = 0;
        int var15 = 2 * (var4 + 1);
        byte var16 = 1;
        double var17 = 0.0D;
        boolean var19 = false;
        int var20;
        int var21;
        double[] var22;
        double[] var23;
        int var24;
        int var25;
        int var32;
        double[] var38;
        double[][] var39;
        if (this.numberOfTEmeasurements > 0) {
            var20 = 0;

            for(var21 = var2.size(); var20 < var21; var16 = 2) {
                var32 = (Integer)var2.get(var16);
                ++var20;
                if (var32 > 0) {
                    var31[var13] = "TE";
                    var31[var13 + 1] = "TE";
                    var30[var13] = (double)(Integer)var2.get(var16 - 1);
                    var30[var13 + 1] = var30[var13];
                    ++var20;
                    var22 = new double[var32];
                    var23 = new double[var32];

                    for(var24 = 0; var24 < var32; ++var24) {
                        var22[var24] = (Double)var2.get(var15++);
                        var23[var24] = (Double)var2.get(var15++);
                        var20 += 2;
                    }

                    var38 = Conv.copy(var22);

                    for(var25 = 0; var25 < var32; ++var25) {
                        var38[var25] = Fmath.log10(var22[var25]);
                    }

                    var29[var14++] = var38;
                    var29[var14++] = var23;
                    Fmath.selectionSort(var22, var23, var22, var23);
                    var39 = this.dispersionCurveTE(var22[0], var22[var32 - 1], var5, var30[var13]);
                    var29[var14++] = var39[0];
                    var29[var14++] = var39[1];
                    var13 += 2;
                }
            }
        }

        var15 = 2 * (var9 + 1);
        var16 = 1;
        var17 = 0.0D;
        var19 = false;
        if (this.numberOfTMmeasurements > 0) {
            var20 = 0;

            for(var21 = var27.size(); var20 < var21; var16 = 2) {
                var32 = (Integer)var27.get(var16);
                ++var20;
                if (var32 > 0) {
                    var31[var13] = "TM";
                    var31[var13 + 1] = "TM";
                    var30[var13] = (double)(Integer)var27.get(var16 - 1);
                    ++var20;
                    var30[var13 + 1] = var30[var13];
                    var22 = new double[var32];
                    var23 = new double[var32];

                    for(var24 = 0; var24 < var32; ++var24) {
                        var22[var24] = (Double)var27.get(var15++);
                        var23[var24] = (Double)var27.get(var15++);
                        var20 += 2;
                    }

                    var38 = Conv.copy(var22);

                    for(var25 = 0; var25 < var32; ++var25) {
                        var38[var25] = Fmath.log10(var22[var25]);
                    }

                    var29[var14++] = var38;
                    var29[var14++] = var23;
                    Fmath.selectionSort(var22, var23, var22, var23);
                    var39 = this.dispersionCurveTM(var22[0], var22[var32 - 1], var5, var30[var13]);
                    var29[var14++] = var39[0];
                    var29[var14++] = var39[1];
                    var13 += 2;
                }
            }
        }

        PlotGraph var34 = new PlotGraph(var29);
        int[] var33 = new int[var26];

        for(int var35 = 0; var35 < var26; var35 += 2) {
            var33[var35] = 0;
            var33[var35 + 1] = 3;
            if (var5 < 100) {
                var33[var35 + 1] = 1;
            }
        }

        var34.setLine(var33);
        int[] var36 = new int[var26];
        int var37 = 1;

        for(var24 = 0; var24 < var26; var24 += 2) {
            var36[var24] = var37;
            var36[var24 + 1] = 0;
            ++var37;
        }

        var34.setPoint(var36);
        var34.setGraphTitle(var1);
        var34.setXaxisLegend("Log10( Core Film Thickness / metres )");
        var34.setYaxisLegend("Effective Refractive Index (kz/ko)");
        var34.setFrame();
    }

    public void calcSuperstrateRefractiveIndex() {
        if (!this.setMeasurements) {
            throw new IllegalArgumentException("Either no thickness, angle/effective refractive index, mode number data has been entered or a key subclass variable, e.g. coupling prism corner angle has not been entered");
        } else if (!this.setWavelength) {
            throw new IllegalArgumentException("No wavelength has been entered");
        } else if (!this.setSubstrate) {
            throw new IllegalArgumentException("No substrate refractive index has been entered");
        } else if (!this.setCore) {
            throw new IllegalArgumentException("No core layer refractive index has been entered");
        } else {
            this.lowerBound = 1.0D;
            this.upperBound = this.coreFilmRefractiveIndex;
            if (this.numberOfTEmeasurements > 0) {
                this.eliminatedTE = new boolean[this.numberOfTEmeasurements];
            }

            int var1 = 0;

            int var2;
            for(var2 = 0; var2 < this.numberOfTEmeasurements; ++var2) {
                this.eliminatedTE[var2] = false;
                if (this.measurementsTE[var2][1] > this.coreFilmRefractiveIndex) {
                    System.out.println("TE mode measurement point, " + var2 + ", eliminated as the effective refractive index, " + this.measurementsTE[var2][1] + ", lies above the physical limit, " + this.coreFilmRefractiveIndex);
                    this.eliminatedTE[var2] = true;
                    ++var1;
                } else if (this.upperBound > this.measurementsTE[var2][1]) {
                    this.upperBound = this.measurementsTE[var2][1];
                }
            }

            int var5;
            if (var1 > 0) {
                var2 = this.numberOfTEmeasurements - var1;
                if (var2 == 0) {
                    this.numberOfTEmeasurements = 0;
                } else {
                    double[][] var3 = new double[var2][3];
                    int var4 = 0;

                    for(var5 = 0; var5 < this.numberOfTEmeasurements; ++var5) {
                        if (!this.eliminatedTE[var5]) {
                            var3[var4][0] = this.measurementsTE[var5][0];
                            var3[var4][1] = this.measurementsTE[var5][1];
                            var3[var4][2] = this.measurementsTE[var5][2];
                            var3[var4][3] = this.measurementsTE[var5][3];
                            ++var4;
                        }
                    }

                    this.measurementsTE = var3;
                    this.numberOfTEmeasurements = var2;
                    this.numberOfMeasurements = this.numberOfTEmeasurements + this.numberOfTMmeasurements;
                }
            }

            this.thicknessesUsedTE = new double[this.numberOfTEmeasurements];
            this.calcEffectRefrIndicesTE = new double[this.numberOfTEmeasurements];

            for(var2 = 0; var2 < this.numberOfTEmeasurements; ++var2) {
                this.thicknessesUsedTE[var2] = this.measurementsTE[var2][0];
            }

            this.minimumTEmodeEffectiveRefractiveIndex = this.upperBound;
            this.upperBound = 0.0D;
            if (this.numberOfTMmeasurements > 0) {
                this.eliminatedTM = new boolean[this.numberOfTMmeasurements];
            }

            var2 = 0;

            int var7;
            for(var7 = 0; var7 < this.numberOfTMmeasurements; ++var7) {
                this.eliminatedTM[var7] = false;
                if (this.measurementsTM[var7][1] > this.coreFilmRefractiveIndex) {
                    System.out.println("TM mode measurement point, " + var7 + ", eliminated as the effective refractive index, " + this.measurementsTM[var7][1] + ", lies above the physical limit, " + this.coreFilmRefractiveIndex);
                    this.eliminatedTM[var7] = true;
                    ++var2;
                } else if (this.upperBound > this.measurementsTM[var7][1]) {
                    this.upperBound = this.measurementsTM[var7][1];
                }
            }

            if (var2 > 0) {
                var7 = this.numberOfTMmeasurements - var2;
                if (var7 == 0) {
                    this.numberOfTMmeasurements = 0;
                } else {
                    double[][] var8 = new double[var7][3];
                    var5 = 0;

                    for(int var6 = 0; var6 < this.numberOfTMmeasurements; ++var6) {
                        if (!this.eliminatedTM[var6]) {
                            var8[var5][0] = this.measurementsTM[var6][0];
                            var8[var5][1] = this.measurementsTM[var6][1];
                            var8[var5][2] = this.measurementsTM[var6][2];
                            var8[var5][3] = this.measurementsTM[var6][3];
                            ++var5;
                        }
                    }

                    this.measurementsTM = var8;
                    this.numberOfTMmeasurements = var7;
                    this.numberOfMeasurements = this.numberOfTEmeasurements + this.numberOfTMmeasurements;
                }
            }

            this.thicknessesUsedTM = new double[this.numberOfTMmeasurements];
            this.calcEffectRefrIndicesTM = new double[this.numberOfTMmeasurements];

            for(var7 = 0; var7 < this.numberOfTMmeasurements; ++var7) {
                this.thicknessesUsedTM[var7] = this.measurementsTM[var7][0];
            }

            this.minimumTMmodeEffectiveRefractiveIndex = this.upperBound;
            if (this.numberOfMeasurements == 0) {
                throw new IllegalArgumentException("All data points rejected as lying outside the physically meaningful bounds");
            } else {
                if (this.numberOfTEmeasurements > 0) {
                    this.calcTEmodeSuperstrateRefractiveIndices();
                }

                if (this.numberOfTMmeasurements > 0) {
                    this.calcTMmodeSuperstrateRefractiveIndices();
                }

                if (this.numberOfTEmeasurements > 0 && this.numberOfTMmeasurements == 0) {
                    this.superstrateRefractiveIndex = this.meanTEmodeSuperstrateRefractiveIndex;
                    this.sdSuperstrateRefractiveIndex = this.sdTEmodeSuperstrateRefractiveIndex;
                } else if (this.numberOfTMmeasurements > 0 && this.numberOfTEmeasurements == 0) {
                    this.superstrateRefractiveIndex = this.meanTMmodeSuperstrateRefractiveIndex;
                    this.sdSuperstrateRefractiveIndex = this.sdTMmodeSuperstrateRefractiveIndex;
                } else {
                    double[] var9 = new double[this.numberOfMeasurements];
                    double[] var10 = new double[this.numberOfMeasurements];

                    for(var5 = 0; var5 < this.numberOfTEmeasurements; ++var5) {
                        var9[var5] = this.calcSuperstrateTEmodeRI[var5];
                        var10[var5] = this.measurementsTE[var5][2];
                    }

                    for(var5 = 0; var5 < this.numberOfTMmeasurements; ++var5) {
                        var9[var5 + this.numberOfTEmeasurements] = this.calcSuperstrateTMmodeRI[var5];
                        var10[var5 + this.numberOfTEmeasurements] = this.measurementsTM[var5][2];
                    }

                    this.superstrateRefractiveIndex = Stat.mean(var9, var10);
                    this.sdSuperstrateRefractiveIndex = Stat.standardDeviation(var9, var10);
                }

                this.superstrateRefractiveIndex2 = this.superstrateRefractiveIndex * this.superstrateRefractiveIndex;
                this.minimumEffectiveRefractiveIndex = Math.min(this.minimumTEmodeEffectiveRefractiveIndex, this.minimumTMmodeEffectiveRefractiveIndex);
                this.superCalculationDone = true;
            }
        }
    }

    public void calcTEmodeSuperstrateRefractiveIndices() {
        this.calcSuperstrateTEmodeRI = new double[this.numberOfTEmeasurements];
        FunctTEsuper var1 = new FunctTEsuper();
        var1.coreFilmRefractiveIndex2 = this.coreFilmRefractiveIndex2;
        var1.ko = this.ko;
        double[] var2 = new double[this.numberOfTEmeasurements];
        this.lowerBound = 1.0D;
        this.upperBound = this.minimumTEmodeEffectiveRefractiveIndex;

        for(int var3 = 0; var3 < this.numberOfTEmeasurements; ++var3) {
            var2[var3] = this.measurementsTE[var3][2];
            var1.thickness = this.measurementsTE[var3][0];
            var1.effectiveRefractiveIndex2 = this.measurementsTE[var3][1] * this.measurementsTE[var3][1];
            var1.modeNumber = this.measurementsTE[var3][3];
            RealRoot var4 = new RealRoot();
            var4.noBoundsExtensions();
            var4.setTolerance(this.tolerance);
            this.calcSuperstrateTEmodeRI[var3] = var4.bisect(var1, this.lowerBound, this.upperBound);
        }

        if (this.numberOfTEmeasurements > 1) {
            this.meanTEmodeSuperstrateRefractiveIndex = Stat.mean(this.calcSuperstrateTEmodeRI, var2);
            this.sdTEmodeSuperstrateRefractiveIndex = Stat.standardDeviation(this.calcSuperstrateTEmodeRI, var2);
        } else {
            this.meanTEmodeSuperstrateRefractiveIndex = this.calcSuperstrateTEmodeRI[0];
        }

    }

    public void calcTMmodeSuperstrateRefractiveIndices() {
        this.calcSuperstrateTMmodeRI = new double[this.numberOfTMmeasurements];
        FunctTMsuper var1 = new FunctTMsuper();
        var1.coreFilmRefractiveIndex2 = this.coreFilmRefractiveIndex2;
        var1.ko = this.ko;
        double[] var2 = new double[this.numberOfTMmeasurements];
        this.lowerBound = 1.0D;
        this.upperBound = this.minimumTMmodeEffectiveRefractiveIndex;

        for(int var3 = 0; var3 < this.numberOfTMmeasurements; ++var3) {
            var2[var3] = this.measurementsTM[var3][2];
            var1.thickness = this.measurementsTM[var3][0];
            var1.effectiveRefractiveIndex2 = this.measurementsTM[var3][1] * this.measurementsTM[var3][1];
            var1.modeNumber = this.measurementsTM[var3][3];
            RealRoot var4 = new RealRoot();
            var4.noBoundsExtensions();
            var4.setTolerance(this.tolerance);
            this.calcSuperstrateTMmodeRI[var3] = var4.bisect(var1, this.lowerBound, this.upperBound);
        }

        if (this.numberOfTMmeasurements > 1) {
            this.meanTMmodeSuperstrateRefractiveIndex = Stat.mean(this.calcSuperstrateTMmodeRI, var2);
            this.sdTMmodeSuperstrateRefractiveIndex = Stat.standardDeviation(this.calcSuperstrateTMmodeRI, var2);
        } else {
            this.meanTMmodeSuperstrateRefractiveIndex = this.calcSuperstrateTMmodeRI[0];
        }

    }
}
