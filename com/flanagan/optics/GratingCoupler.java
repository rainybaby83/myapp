//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.optics;

import com.flanagan.analysis.ErrorProp;

public class GratingCoupler extends PlanarWaveguide {
    private double[] thicknessesTE = null;
    private double[] anglesDegTE = null;
    private double[] anglesRadTE = null;
    private double[] errorsDegTE = null;
    private double[] errorsRadTE = null;
    private double[] modeNumbersTE = null;
    private double[] effectiveRefractiveIndicesTE = null;
    private double[] effectiveErrorsTE = null;
    private int numberOfTEmeasurementsGrating = 0;
    private boolean setMeasurementsTEgrating = false;
    private boolean setTEerrors = false;
    private boolean calcEffectiveDone = false;
    private double[] thicknessesTM = null;
    private double[] anglesDegTM = null;
    private double[] anglesRadTM = null;
    private double[] errorsDegTM = null;
    private double[] errorsRadTM = null;
    private double[] modeNumbersTM = null;
    private double[] effectiveRefractiveIndicesTM = null;
    private double[] effectiveErrorsTM = null;
    private int numberOfTMmeasurementsGrating = 0;
    private boolean setMeasurementsTMgrating = false;
    private boolean setTMerrors = false;
    private int numberOfMeasurementsGrating = 0;
    private boolean setMeasurementsGrating = false;
    private double gratingPitch = 0.0D;
    private boolean setGratingPitch = false;
    private int[] gratingOrderTE = null;
    private boolean setGratingOrderTE = false;
    private int[] gratingOrderTM = null;
    private boolean setGratingOrderTM = false;
    private double superstrateRI = 0.0D;
    private boolean setSuperstrateRI = false;

    public GratingCoupler() {
    }

    public void setGratingPitch(double var1) {
        this.gratingPitch = var1;
        this.setGratingPitch = true;
        if (this.setMeasurementsGrating && super.setWavelength) {
            this.calcEffectiveRefractiveIndices();
        }

    }

    public void setSetTEmodeGratingOrder(int[] var1) {
        this.gratingOrderTE = var1;
        int var2 = var1.length;
        if (this.setMeasurementsTEgrating && var2 != this.numberOfTEmeasurementsGrating) {
            throw new IllegalArgumentException("Number of grating orders entered, " + var2 + ", is not equal to the number of measurements previously entered, " + this.numberOfTEmeasurementsGrating);
        } else {
            if (this.setMeasurementsGrating && this.setGratingPitch && super.setWavelength) {
                this.calcEffectiveRefractiveIndices();
            }

        }
    }

    public void setSetTMmodeGratingOrder(int[] var1) {
        this.gratingOrderTM = var1;
        int var2 = var1.length;
        if (this.setMeasurementsTMgrating && var2 != this.numberOfTMmeasurementsGrating) {
            throw new IllegalArgumentException("Number of grating orders entered, " + var2 + ", is not equal to the number of measurements previously entered, " + this.numberOfTEmeasurementsGrating);
        } else {
            if (this.setMeasurementsGrating && this.setGratingPitch && super.setWavelength) {
                this.calcEffectiveRefractiveIndices();
            }

        }
    }

    public void setSuperstrateRefractiveIndex(double var1) {
        if (this.calcEffectiveDone) {
            this.clearData();
        }

        this.superstrateRI = var1;
        super.superstrateRefractiveIndex = var1;
        this.setSuperstrateRI = true;
        if (this.setMeasurementsGrating && this.setGratingPitch && super.setWavelength) {
            this.calcEffectiveRefractiveIndices();
        }

    }

    public double getAnalyteSolutionRefractiveIndex() {
        return super.getSuperstrateRefractiveIndex();
    }

    public double getStandardDeviationAnalyteSolutionRefractiveIndex() {
        return super.getStandardDeviationSuperstrateRefractiveIndex();
    }

    public void enterTEmodeData(double var1, double var3, double var5) {
        if (this.setMeasurementsTEgrating) {
            if (this.setTEerrors) {
                throw new IllegalArgumentException("All Entered fillData must either all have associated errors entered or all have no associated errors entered");
            }

            int var7 = this.numberOfTEmeasurementsGrating + 1;
            double[] var8 = new double[var7];

            int var9;
            for(var9 = 0; var9 < this.numberOfTEmeasurementsGrating; ++var9) {
                var8[var9] = this.thicknessesTE[var9];
            }

            var8[this.numberOfTEmeasurementsGrating] = var1;
            this.thicknessesTE = var8;

            for(var9 = 0; var9 < this.numberOfTEmeasurementsGrating; ++var9) {
                var8[var9] = this.anglesDegTE[var9];
            }

            var8[this.numberOfTEmeasurementsGrating] = var3;
            this.anglesDegTE = var8;
            this.anglesRadTE = var8;
            this.errorsDegTE = var8;
            this.errorsRadTE = var8;

            for(var9 = 0; var9 < var7; ++var9) {
                this.anglesRadTE[var9] = Math.toRadians(this.anglesDegTE[var9]);
                this.errorsDegTE[var9] = 0.0D;
                this.errorsRadTE[var9] = 0.0D;
            }

            for(var9 = 0; var9 < this.numberOfTEmeasurementsGrating; ++var9) {
                var8[var9] = this.modeNumbersTE[var9];
            }

            var8[this.numberOfTEmeasurementsGrating] = var5;
            this.numberOfTEmeasurementsGrating = var7;
        } else {
            this.thicknessesTE = new double[1];
            this.thicknessesTE[0] = var1;
            this.anglesDegTE = new double[1];
            this.anglesDegTE[0] = var3;
            this.anglesRadTE = new double[1];
            this.anglesRadTE[0] = Math.toRadians(var3);
            this.errorsDegTE = new double[1];
            this.errorsDegTE[0] = 0.0D;
            this.errorsRadTE = new double[1];
            this.errorsRadTE[0] = 0.0D;
            this.modeNumbersTE = new double[1];
            this.modeNumbersTE[0] = var5;
            this.numberOfTEmeasurementsGrating = 1;
        }

        this.numberOfMeasurementsGrating = this.numberOfTEmeasurementsGrating + this.numberOfTMmeasurementsGrating;
        this.setMeasurementsTEgrating = true;
        this.setMeasurementsGrating = true;
        if (this.setGratingPitch && super.setWavelength) {
            this.calcTEmodeEffectiveRefractiveIndices();
        }

    }

    public void enterTMmodeData(double var1, double var3, double var5) {
        if (this.setMeasurementsTMgrating) {
            if (this.setTMerrors) {
                throw new IllegalArgumentException("All Entered fillData must either all have associated errors entered or all have no associated errors entered");
            }

            int var7 = this.numberOfTMmeasurementsGrating + 1;
            double[] var8 = new double[var7];

            int var9;
            for(var9 = 0; var9 < this.numberOfTMmeasurementsGrating; ++var9) {
                var8[var9] = this.thicknessesTM[var9];
            }

            var8[this.numberOfTMmeasurementsGrating] = var1;
            this.thicknessesTM = var8;

            for(var9 = 0; var9 < this.numberOfTMmeasurementsGrating; ++var9) {
                var8[var9] = this.anglesDegTM[var9];
            }

            var8[this.numberOfTMmeasurementsGrating] = var3;
            this.anglesDegTM = var8;
            this.anglesRadTM = var8;
            this.errorsDegTM = var8;
            this.errorsRadTM = var8;

            for(var9 = 0; var9 < var7; ++var9) {
                this.anglesRadTM[var9] = Math.toRadians(this.anglesDegTM[var9]);
                this.errorsDegTM[var9] = 0.0D;
                this.errorsRadTM[var9] = 0.0D;
            }

            for(var9 = 0; var9 < this.numberOfTMmeasurementsGrating; ++var9) {
                var8[var9] = this.modeNumbersTM[var9];
            }

            var8[this.numberOfTMmeasurementsGrating] = var5;
            this.numberOfTMmeasurementsGrating = var7;
        } else {
            this.thicknessesTM = new double[1];
            this.thicknessesTM[0] = var1;
            this.anglesDegTM = new double[1];
            this.anglesDegTM[0] = var3;
            this.anglesRadTM = new double[1];
            this.anglesRadTM[0] = Math.toRadians(var3);
            this.errorsDegTM = new double[1];
            this.errorsDegTM[0] = 0.0D;
            this.errorsRadTM = new double[1];
            this.errorsRadTM[0] = 0.0D;
            this.modeNumbersTM = new double[1];
            this.modeNumbersTM[0] = var5;
            this.numberOfTMmeasurementsGrating = 1;
        }

        this.numberOfMeasurementsGrating = this.numberOfTEmeasurementsGrating + this.numberOfTMmeasurementsGrating;
        this.setMeasurementsTMgrating = true;
        this.setMeasurementsGrating = true;
        if (this.setGratingPitch && super.setWavelength) {
            this.calcTMmodeEffectiveRefractiveIndices();
        }

    }

    public void enterTEmodeData(double[] var1, double[] var2, double[] var3) {
        int var4 = var1.length;
        int var5 = var2.length;
        if (var5 != var4) {
            throw new IllegalArgumentException("number of thicknesses, " + var4 + ", does not equal the number of coupling angles, " + var5);
        } else {
            int var6 = var3.length;
            if (var6 != var4) {
                throw new IllegalArgumentException("number of thicknesses, " + var4 + ", does not equal the number of mode numbers, " + var6);
            } else {
                int var7;
                if (this.setMeasurementsTEgrating) {
                    if (this.setTEerrors) {
                        throw new IllegalArgumentException("All Entered fillData must either all have associated errors entered or all have no associated errors entered");
                    }

                    var7 = this.numberOfTEmeasurementsGrating + var4;
                    double[] var8 = new double[var7];

                    int var9;
                    for(var9 = 0; var9 < this.numberOfTEmeasurementsGrating; ++var9) {
                        var8[var9] = this.thicknessesTE[var9];
                    }

                    for(var9 = 0; var9 < var4; ++var9) {
                        var8[this.numberOfTEmeasurementsGrating + var9] = var1[var9];
                    }

                    this.thicknessesTE = var8;

                    for(var9 = 0; var9 < this.numberOfTEmeasurementsGrating; ++var9) {
                        var8[var9] = this.anglesDegTE[var9];
                    }

                    for(var9 = 0; var9 < var4; ++var9) {
                        var8[this.numberOfTEmeasurementsGrating + var9] = var2[var9];
                    }

                    this.anglesDegTE = var8;
                    this.anglesRadTE = var8;
                    this.errorsDegTE = var8;
                    this.errorsRadTE = var8;

                    for(var9 = 0; var9 < var7; ++var9) {
                        this.anglesRadTE[var9] = Math.toRadians(this.anglesDegTE[var9]);
                        this.errorsDegTE[var9] = 0.0D;
                        this.errorsRadTE[var9] = 0.0D;
                    }

                    for(var9 = 0; var9 < this.numberOfTEmeasurementsGrating; ++var9) {
                        var8[var9] = this.modeNumbersTE[var9];
                    }

                    for(var9 = 0; var9 < var4; ++var9) {
                        var8[this.numberOfTEmeasurementsGrating + var9] = var3[var9];
                    }

                    this.numberOfTEmeasurementsGrating = var7;
                } else {
                    this.numberOfTEmeasurementsGrating = var4;
                    this.thicknessesTE = var1;
                    this.anglesDegTE = var2;
                    this.anglesRadTE = new double[var4];
                    this.errorsDegTE = new double[var4];
                    this.errorsRadTE = new double[var4];

                    for(var7 = 0; var7 < var4; ++var7) {
                        this.anglesRadTE[var7] = Math.toRadians(var2[var7]);
                        this.errorsDegTE[var7] = 0.0D;
                        this.errorsRadTE[var7] = 0.0D;
                    }

                    this.modeNumbersTE = var3;
                }

                this.numberOfMeasurementsGrating = this.numberOfTEmeasurementsGrating + this.numberOfTMmeasurementsGrating;
                this.setMeasurementsTEgrating = true;
                this.setMeasurementsGrating = true;
                if (this.setGratingPitch && super.setWavelength) {
                    this.calcTEmodeEffectiveRefractiveIndices();
                }

            }
        }
    }

    public void enterTMmodeData(double[] var1, double[] var2, double[] var3) {
        int var4 = var1.length;
        int var5 = var2.length;
        if (var5 != var4) {
            throw new IllegalArgumentException("number of thicknesses, " + var4 + ", does not equal the number of coupling angles, " + var5);
        } else {
            int var6 = var3.length;
            if (var6 != var4) {
                throw new IllegalArgumentException("number of thicknesses, " + var4 + ", does not equal the number of mode numbers, " + var6);
            } else {
                int var7;
                if (this.setMeasurementsTMgrating) {
                    if (this.setTMerrors) {
                        throw new IllegalArgumentException("All Entered fillData must either all have associated errors entered or all have no associated errors entered");
                    }

                    var7 = this.numberOfTMmeasurementsGrating + var4;
                    double[] var8 = new double[var7];

                    int var9;
                    for(var9 = 0; var9 < this.numberOfTMmeasurementsGrating; ++var9) {
                        var8[var9] = this.thicknessesTM[var9];
                    }

                    for(var9 = 0; var9 < var4; ++var9) {
                        var8[this.numberOfTMmeasurementsGrating + var9] = var1[var9];
                    }

                    this.thicknessesTM = var8;

                    for(var9 = 0; var9 < this.numberOfTMmeasurementsGrating; ++var9) {
                        var8[var9] = this.anglesDegTM[var9];
                    }

                    for(var9 = 0; var9 < var4; ++var9) {
                        var8[this.numberOfTMmeasurementsGrating + var9] = var2[var9];
                    }

                    this.anglesDegTM = var8;
                    this.anglesRadTM = var8;
                    this.errorsDegTM = var8;
                    this.errorsRadTM = var8;

                    for(var9 = 0; var9 < var7; ++var9) {
                        this.anglesRadTM[var9] = Math.toRadians(this.anglesDegTM[var9]);
                        this.errorsDegTM[var9] = 0.0D;
                        this.errorsRadTM[var9] = 0.0D;
                    }

                    for(var9 = 0; var9 < this.numberOfTMmeasurementsGrating; ++var9) {
                        var8[var9] = this.modeNumbersTM[var9];
                    }

                    for(var9 = 0; var9 < var4; ++var9) {
                        var8[this.numberOfTMmeasurementsGrating + var9] = var3[var9];
                    }

                    this.numberOfTMmeasurementsGrating = var7;
                } else {
                    this.numberOfTMmeasurementsGrating = var4;
                    this.thicknessesTM = var1;
                    this.anglesDegTM = var2;
                    this.anglesRadTM = new double[var4];
                    this.errorsDegTM = new double[var4];
                    this.errorsRadTM = new double[var4];

                    for(var7 = 0; var7 < var4; ++var7) {
                        this.anglesRadTM[var7] = Math.toRadians(var2[var7]);
                        this.errorsDegTM[var7] = 0.0D;
                        this.errorsRadTM[var7] = 0.0D;
                    }

                    this.modeNumbersTM = var3;
                }

                this.numberOfMeasurementsGrating = this.numberOfTEmeasurementsGrating + this.numberOfTMmeasurementsGrating;
                this.setMeasurementsTMgrating = true;
                this.setMeasurementsGrating = true;
                if (this.setGratingPitch && super.setWavelength) {
                    this.calcTMmodeEffectiveRefractiveIndices();
                }

            }
        }
    }

    public void enterTEmodeData(double var1, double var3, double var5, double var7) {
        if (this.setMeasurementsTEgrating) {
            if (!this.setTEerrors) {
                throw new IllegalArgumentException("All Entered fillData must either all have associated errors entered or all have no associated errors entered");
            }

            int var9 = this.numberOfTEmeasurementsGrating + 1;
            double[] var10 = new double[var9];

            int var11;
            for(var11 = 0; var11 < this.numberOfTEmeasurementsGrating; ++var11) {
                var10[var11] = this.thicknessesTE[var11];
            }

            var10[this.numberOfTEmeasurementsGrating] = var1;
            this.thicknessesTE = var10;

            for(var11 = 0; var11 < this.numberOfTEmeasurementsGrating; ++var11) {
                var10[var11] = this.anglesDegTE[var11];
            }

            var10[this.numberOfTEmeasurementsGrating] = var3;
            this.anglesDegTE = var10;

            for(var11 = 0; var11 < this.numberOfTEmeasurementsGrating; ++var11) {
                var10[var11] = this.errorsDegTE[var11];
            }

            var10[this.numberOfTEmeasurementsGrating] = var5;
            this.errorsDegTE = var10;
            this.anglesRadTE = var10;
            this.errorsRadTE = var10;

            for(var11 = 0; var11 < var9; ++var11) {
                this.anglesRadTE[var11] = Math.toRadians(this.anglesDegTE[var11]);
                this.errorsRadTE[var11] = Math.toRadians(this.errorsDegTE[var11]);
            }

            for(var11 = 0; var11 < this.numberOfTEmeasurementsGrating; ++var11) {
                var10[var11] = this.modeNumbersTE[var11];
            }

            var10[this.numberOfTEmeasurementsGrating] = var7;
            this.numberOfTEmeasurementsGrating = var9;
        } else {
            this.thicknessesTE = new double[1];
            this.thicknessesTE[0] = var1;
            this.anglesDegTE = new double[1];
            this.anglesDegTE[0] = var3;
            this.anglesRadTE = new double[1];
            this.anglesRadTE[0] = Math.toRadians(var3);
            this.errorsDegTE = new double[1];
            this.errorsDegTE[0] = var5;
            this.errorsRadTE = new double[1];
            this.errorsRadTE[0] = Math.toRadians(var5);
            this.modeNumbersTE = new double[1];
            this.modeNumbersTE[0] = var7;
            this.numberOfTEmeasurementsGrating = 1;
        }

        this.numberOfMeasurementsGrating = this.numberOfTEmeasurementsGrating + this.numberOfTMmeasurementsGrating;
        this.setMeasurementsTEgrating = true;
        this.setTEerrors = true;
        this.setMeasurementsGrating = true;
        if (this.setGratingPitch && super.setWavelength) {
            this.calcTEmodeEffectiveRefractiveIndices();
        }

    }

    public void enterTMmodeData(double var1, double var3, double var5, double var7) {
        if (this.setMeasurementsTMgrating) {
            if (!this.setTMerrors) {
                throw new IllegalArgumentException("All Entered fillData must either all have associated errors entered or all have no associated errors entered");
            }

            int var9 = this.numberOfTMmeasurementsGrating + 1;
            double[] var10 = new double[var9];

            int var11;
            for(var11 = 0; var11 < this.numberOfTMmeasurementsGrating; ++var11) {
                var10[var11] = this.thicknessesTM[var11];
            }

            var10[this.numberOfTMmeasurementsGrating] = var1;
            this.thicknessesTM = var10;

            for(var11 = 0; var11 < this.numberOfTMmeasurementsGrating; ++var11) {
                var10[var11] = this.anglesDegTM[var11];
            }

            var10[this.numberOfTMmeasurementsGrating] = var3;
            this.anglesDegTM = var10;

            for(var11 = 0; var11 < this.numberOfTMmeasurementsGrating; ++var11) {
                var10[var11] = this.errorsDegTM[var11];
            }

            var10[this.numberOfTMmeasurementsGrating] = var5;
            this.errorsDegTM = var10;
            this.anglesRadTM = var10;
            this.errorsRadTM = var10;

            for(var11 = 0; var11 < var9; ++var11) {
                this.anglesRadTM[var11] = Math.toRadians(this.anglesDegTM[var11]);
                this.errorsRadTM[var11] = Math.toRadians(this.errorsDegTM[var11]);
            }

            for(var11 = 0; var11 < this.numberOfTMmeasurementsGrating; ++var11) {
                var10[var11] = this.modeNumbersTM[var11];
            }

            var10[this.numberOfTMmeasurementsGrating] = var7;
            this.numberOfTMmeasurementsGrating = var9;
        } else {
            this.thicknessesTM = new double[1];
            this.thicknessesTM[0] = var1;
            this.anglesDegTM = new double[1];
            this.anglesDegTM[0] = var3;
            this.anglesRadTM = new double[1];
            this.anglesDegTM[0] = Math.toRadians(var3);
            this.errorsDegTM = new double[1];
            this.errorsDegTM[0] = var5;
            this.errorsRadTM = new double[1];
            this.errorsDegTM[0] = Math.toRadians(var5);
            this.modeNumbersTM = new double[1];
            this.modeNumbersTM[0] = var7;
            this.numberOfTMmeasurementsGrating = 1;
        }

        this.numberOfMeasurementsGrating = this.numberOfTEmeasurementsGrating + this.numberOfTMmeasurementsGrating;
        this.setMeasurementsTMgrating = true;
        this.setTMerrors = true;
        this.setMeasurementsGrating = true;
        if (this.setGratingPitch && super.setWavelength) {
            this.calcTMmodeEffectiveRefractiveIndices();
        }

    }

    public void enterTEmodeData(double[] var1, double[] var2, double[] var3, double[] var4) {
        int var5 = var1.length;
        int var6 = var2.length;
        if (var6 != var5) {
            throw new IllegalArgumentException("number of thicknesses, " + var5 + ", does not equal the number of coupling angles, " + var6);
        } else {
            int var7 = var4.length;
            if (var7 != var5) {
                throw new IllegalArgumentException("number of thicknesses, " + var5 + ", does not equal the number of mode numbers, " + var7);
            } else {
                int var8;
                if (this.setMeasurementsTEgrating) {
                    if (!this.setTEerrors) {
                        throw new IllegalArgumentException("All Entered fillData must either all have associated errors entered or all have no associated errors entered");
                    }

                    var8 = this.numberOfTEmeasurementsGrating + var5;
                    double[] var9 = new double[var8];

                    int var10;
                    for(var10 = 0; var10 < this.numberOfTEmeasurementsGrating; ++var10) {
                        var9[var10] = this.thicknessesTE[var10];
                    }

                    for(var10 = 0; var10 < var5; ++var10) {
                        var9[this.numberOfTEmeasurementsGrating + var10] = var1[var10];
                    }

                    this.thicknessesTE = var9;

                    for(var10 = 0; var10 < this.numberOfTEmeasurementsGrating; ++var10) {
                        var9[var10] = this.anglesDegTE[var10];
                    }

                    for(var10 = 0; var10 < var5; ++var10) {
                        var9[this.numberOfTEmeasurementsGrating + var10] = var2[var10];
                    }

                    this.anglesDegTE = var9;

                    for(var10 = 0; var10 < this.numberOfTEmeasurementsGrating; ++var10) {
                        var9[var10] = this.errorsDegTE[var10];
                    }

                    for(var10 = 0; var10 < var5; ++var10) {
                        var9[this.numberOfTEmeasurementsGrating + var10] = var3[var10];
                    }

                    this.errorsDegTE = var9;
                    this.anglesRadTE = var9;
                    this.errorsRadTE = var9;

                    for(var10 = 0; var10 < var8; ++var10) {
                        this.anglesRadTE[var10] = Math.toRadians(this.anglesDegTE[var10]);
                        this.errorsRadTE[var10] = Math.toRadians(this.errorsDegTE[var10]);
                    }

                    for(var10 = 0; var10 < this.numberOfTEmeasurementsGrating; ++var10) {
                        var9[var10] = this.modeNumbersTE[var10];
                    }

                    for(var10 = 0; var10 < var5; ++var10) {
                        var9[this.numberOfTEmeasurementsGrating + var10] = var4[var10];
                    }

                    this.numberOfTEmeasurementsGrating = var8;
                } else {
                    this.numberOfTEmeasurementsGrating = var5;
                    this.thicknessesTE = var1;
                    this.anglesDegTE = var2;
                    this.anglesRadTE = new double[var5];
                    this.errorsDegTE = var3;
                    this.errorsRadTE = new double[var5];

                    for(var8 = 0; var8 < var5; ++var8) {
                        this.anglesRadTE[var8] = Math.toRadians(var2[var8]);
                        this.errorsRadTE[var8] = Math.toRadians(var3[var8]);
                    }

                    this.modeNumbersTE = var4;
                }

                this.numberOfMeasurementsGrating = this.numberOfTEmeasurementsGrating + this.numberOfTMmeasurementsGrating;
                this.setMeasurementsTEgrating = true;
                this.setTEerrors = true;
                this.setMeasurementsGrating = true;
                if (this.setGratingPitch && super.setWavelength) {
                    this.calcTEmodeEffectiveRefractiveIndices();
                }

            }
        }
    }

    public void enterTMmodeData(double[] var1, double[] var2, double[] var3, double[] var4) {
        int var5 = var1.length;
        int var6 = var2.length;
        if (var6 != var5) {
            throw new IllegalArgumentException("number of thicknesses, " + var5 + ", does not equal the number of coupling angles, " + var6);
        } else {
            int var7 = var4.length;
            if (var7 != var5) {
                throw new IllegalArgumentException("number of thicknesses, " + var5 + ", does not equal the number of mode numbers, " + var7);
            } else {
                int var8;
                if (this.setMeasurementsTMgrating) {
                    if (!this.setTMerrors) {
                        throw new IllegalArgumentException("All Entered fillData must either all have associated errors entered or all have no associated errors entered");
                    }

                    var8 = this.numberOfTMmeasurementsGrating + var5;
                    double[] var9 = new double[var8];

                    int var10;
                    for(var10 = 0; var10 < this.numberOfTMmeasurementsGrating; ++var10) {
                        var9[var10] = this.thicknessesTM[var10];
                    }

                    for(var10 = 0; var10 < var5; ++var10) {
                        var9[this.numberOfTMmeasurementsGrating + var10] = var1[var10];
                    }

                    this.thicknessesTM = var9;

                    for(var10 = 0; var10 < this.numberOfTMmeasurementsGrating; ++var10) {
                        var9[var10] = this.anglesDegTM[var10];
                    }

                    for(var10 = 0; var10 < var5; ++var10) {
                        var9[this.numberOfTMmeasurementsGrating + var10] = var2[var10];
                    }

                    this.anglesDegTM = var9;

                    for(var10 = 0; var10 < this.numberOfTMmeasurementsGrating; ++var10) {
                        var9[var10] = this.errorsDegTM[var10];
                    }

                    for(var10 = 0; var10 < var5; ++var10) {
                        var9[this.numberOfTMmeasurementsGrating + var10] = var3[var10];
                    }

                    this.errorsDegTM = var9;
                    this.anglesRadTM = var9;
                    this.errorsRadTM = var9;

                    for(var10 = 0; var10 < var8; ++var10) {
                        this.anglesRadTM[var10] = Math.toRadians(this.anglesDegTM[var10]);
                        this.errorsRadTM[var10] = Math.toRadians(this.errorsDegTM[var10]);
                    }

                    for(var10 = 0; var10 < this.numberOfTMmeasurementsGrating; ++var10) {
                        var9[var10] = this.modeNumbersTM[var10];
                    }

                    for(var10 = 0; var10 < var5; ++var10) {
                        var9[this.numberOfTMmeasurementsGrating + var10] = var4[var10];
                    }

                    this.numberOfTMmeasurementsGrating = var8;
                } else {
                    this.numberOfTMmeasurementsGrating = var5;
                    this.thicknessesTM = var1;
                    this.anglesDegTM = var2;
                    this.errorsDegTM = var3;
                    this.anglesRadTM = new double[var5];
                    this.errorsRadTM = new double[var5];

                    for(var8 = 0; var8 < var5; ++var8) {
                        this.anglesRadTM[var8] = Math.toRadians(var2[var8]);
                        this.errorsRadTM[var8] = Math.toRadians(var3[var8]);
                    }

                    this.modeNumbersTM = var4;
                }

                this.numberOfMeasurementsGrating = this.numberOfTEmeasurementsGrating + this.numberOfTMmeasurementsGrating;
                this.setMeasurementsTMgrating = true;
                this.setTMerrors = true;
                this.setMeasurementsGrating = true;
                if (this.setGratingPitch && super.setWavelength) {
                    this.calcTMmodeEffectiveRefractiveIndices();
                }

            }
        }
    }

    public void clearData() {
        this.numberOfTEmeasurementsGrating = 0;
        this.setMeasurementsTEgrating = false;
        this.numberOfTMmeasurementsGrating = 0;
        this.setMeasurementsTMgrating = false;
        super.numberOfMeasurements = 0;
        super.setMeasurements = false;
        super.setWeights = false;
        super.numberOfTEmeasurements = 0;
        super.setMeasurementsTE = false;
        super.numberOfTMmeasurements = 0;
        super.setMeasurementsTM = false;
    }

    public void calcEffectiveRefractiveIndices() {
        if (this.setMeasurementsTEgrating) {
            this.calcTEmodeEffectiveRefractiveIndices();
        }

        if (this.setMeasurementsTMgrating) {
            this.calcTMmodeEffectiveRefractiveIndices();
        }

    }

    public void calcTEmodeEffectiveRefractiveIndices() {
        this.effectiveRefractiveIndicesTE = new double[this.numberOfTEmeasurementsGrating];
        this.effectiveErrorsTE = new double[this.numberOfTEmeasurementsGrating];
        if (!this.setSuperstrateRI) {
            this.superstrateRI = RefractiveIndex.air(super.wavelength);
            super.superstrateRefractiveIndex = RefractiveIndex.air(super.wavelength);
        }

        if (this.setTEerrors) {
            ErrorProp var1 = new ErrorProp(super.superstrateRefractiveIndex, 0.0D);
            ErrorProp var2 = new ErrorProp(this.gratingPitch, 0.0D);
            ErrorProp var3 = new ErrorProp(super.wavelength, 0.0D);

            for(int var4 = 0; var4 < this.numberOfTEmeasurementsGrating; ++var4) {
                ErrorProp var5 = new ErrorProp(this.anglesRadTM[var4], this.errorsRadTM[var4]);
                ErrorProp var6 = new ErrorProp((double)this.gratingOrderTE[var4], 0.0D);
                ErrorProp var7 = ErrorProp.sin(var5);
                var7 = var7.times(var1);
                var7 = var7.plus(var3.times(var6).over(var2));
                this.effectiveRefractiveIndicesTE[var4] = var7.getValue();
                this.effectiveErrorsTE[var4] = var7.getError();
            }

            super.enterTEmodeData(this.thicknessesTE, this.effectiveRefractiveIndicesTE, this.effectiveErrorsTE, this.modeNumbersTE);
        } else {
            for(int var8 = 0; var8 < this.numberOfTEmeasurementsGrating; ++var8) {
                this.effectiveRefractiveIndicesTE[var8] = this.superstrateRI * Math.sin(this.anglesRadTE[var8]) + super.wavelength * (double)this.gratingOrderTE[var8] / this.gratingPitch;
            }

            super.enterTEmodeData(this.thicknessesTE, this.effectiveRefractiveIndicesTE, this.modeNumbersTE);
        }

        this.calcEffectiveDone = true;
    }

    public void calcTMmodeEffectiveRefractiveIndices() {
        this.effectiveRefractiveIndicesTM = new double[this.numberOfTMmeasurementsGrating];
        this.effectiveErrorsTM = new double[this.numberOfTMmeasurementsGrating];
        if (!this.setSuperstrateRI) {
            this.superstrateRI = RefractiveIndex.air(super.wavelength);
            super.superstrateRefractiveIndex = RefractiveIndex.air(super.wavelength);
        }

        if (this.setTMerrors) {
            ErrorProp var1 = new ErrorProp(super.superstrateRefractiveIndex, 0.0D);
            ErrorProp var2 = new ErrorProp(this.gratingPitch, 0.0D);
            ErrorProp var3 = new ErrorProp(super.wavelength, 0.0D);

            for(int var4 = 0; var4 < this.numberOfTMmeasurementsGrating; ++var4) {
                ErrorProp var5 = new ErrorProp(this.anglesRadTM[var4], this.errorsRadTM[var4]);
                ErrorProp var6 = new ErrorProp((double)this.gratingOrderTM[var4], 0.0D);
                ErrorProp var7 = ErrorProp.sin(var5);
                var7 = var7.times(var1);
                var7 = var7.plus(var3.times(var6).over(var2));
                this.effectiveRefractiveIndicesTM[var4] = var7.getValue();
                this.effectiveErrorsTM[var4] = var7.getError();
            }

            super.enterTMmodeData(this.thicknessesTM, this.effectiveRefractiveIndicesTM, this.effectiveErrorsTM, this.modeNumbersTM);
        } else {
            for(int var8 = 0; var8 < this.numberOfTMmeasurementsGrating; ++var8) {
                this.effectiveRefractiveIndicesTM[var8] = this.superstrateRI * Math.sin(this.anglesRadTM[var8]) + super.wavelength * (double)this.gratingOrderTM[var8] / this.gratingPitch;
            }

            super.enterTMmodeData(this.thicknessesTM, this.effectiveRefractiveIndicesTM, this.modeNumbersTM);
        }

        this.calcEffectiveDone = true;
    }
}
