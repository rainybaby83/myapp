//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.optics;

import com.flanagan.analysis.ErrorProp;

public class PrismCoupler extends PlanarWaveguide {
    private double[] thicknessesTE = null;
    private double[] anglesDegTE = null;
    private double[] anglesRadTE = null;
    private double[] errorsDegTE = null;
    private double[] errorsRadTE = null;
    private double[] modeNumbersTE = null;
    private double[] effectiveRefractiveIndicesTE = null;
    private double[] effectiveErrorsTE = null;
    private int numberOfTEmeasurementsPrism = 0;
    private boolean setMeasurementsTEprism = false;
    private boolean setTEerrors = false;
    private double[] thicknessesTM = null;
    private double[] anglesDegTM = null;
    private double[] anglesRadTM = null;
    private double[] errorsDegTM = null;
    private double[] errorsRadTM = null;
    private double[] modeNumbersTM = null;
    private double[] effectiveRefractiveIndicesTM = null;
    private double[] effectiveErrorsTM = null;
    private int numberOfTMmeasurementsPrism = 0;
    private boolean setMeasurementsTMprism = false;
    private boolean setTMerrors = false;
    private int numberOfMeasurementsPrism = 0;
    private boolean setMeasurementsPrism = false;
    private boolean setPrismRI = false;
    private double prismAngleAlphaDeg = 0.0D;
    private double prismAngleAlphaRad = 0.0D;
    private boolean setPrismAlpha = false;

    public PrismCoupler() {
    }

    public void setPrismRefractiveIndex(double var1) {
        super.prismRefractiveIndex = var1;
        super.prismRefractiveIndex2 = var1 * var1;
        this.setPrismRI = true;
        if (this.setMeasurementsPrism && this.setPrismAlpha) {
            this.calcEffectiveRefractiveIndices();
        }

    }

    public void setPrismAngleAlpha(double var1) {
        this.prismAngleAlphaDeg = var1;
        this.prismAngleAlphaRad = Math.toRadians(var1);
        this.setPrismAlpha = true;
        if (this.setMeasurementsPrism && this.setPrismRI) {
            this.calcEffectiveRefractiveIndices();
        }

    }

    public void setPrismToWaveguideGap(double var1) {
        super.prismToWaveguideGap = var1;
        super.setPrismToWaveguideGap = true;
    }

    public void enterTEmodeData(double var1, double var3, double var5) {
        if (this.setMeasurementsTEprism) {
            if (this.setErrorsTE) {
                throw new IllegalArgumentException("All Entered data must either all have associated errors entered or all have no associated errors entered");
            }

            int var7 = this.numberOfTEmeasurementsPrism + 1;
            double[] var8 = new double[var7];

            int var9;
            for(var9 = 0; var9 < this.numberOfTEmeasurementsPrism; ++var9) {
                var8[var9] = this.thicknessesTE[var9];
            }

            var8[this.numberOfTEmeasurementsPrism] = var1;
            this.thicknessesTE = var8;

            for(var9 = 0; var9 < this.numberOfTEmeasurementsPrism; ++var9) {
                var8[var9] = this.anglesDegTE[var9];
            }

            var8[this.numberOfTEmeasurementsPrism] = var3;
            this.anglesDegTE = var8;
            this.anglesRadTE = var8;
            this.errorsDegTE = var8;
            this.errorsRadTE = var8;

            for(var9 = 0; var9 < var7; ++var9) {
                this.anglesRadTE[var9] = Math.toRadians(this.anglesDegTE[var9]);
                this.errorsDegTE[var9] = 0.0D;
                this.errorsRadTE[var9] = 0.0D;
            }

            for(var9 = 0; var9 < this.numberOfTEmeasurementsPrism; ++var9) {
                var8[var9] = this.modeNumbersTE[var9];
            }

            var8[this.numberOfTEmeasurementsPrism] = var5;
            this.numberOfTEmeasurementsPrism = var7;
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
            this.numberOfTEmeasurementsPrism = 1;
        }

        this.numberOfMeasurementsPrism = this.numberOfTEmeasurementsPrism + this.numberOfTMmeasurementsPrism;
        this.setMeasurementsTEprism = true;
        this.setMeasurementsPrism = true;
        if (this.setPrismAlpha && this.setPrismAlpha) {
            this.calcTEmodeEffectiveRefractiveIndices();
        }

    }

    public void enterTMmodeData(double var1, double var3, double var5) {
        if (this.setMeasurementsTMprism) {
            if (this.setTMerrors) {
                throw new IllegalArgumentException("All Entered data must either all have associated errors entered or all have no associated errors entered");
            }

            int var7 = this.numberOfTMmeasurementsPrism + 1;
            double[] var8 = new double[var7];

            int var9;
            for(var9 = 0; var9 < this.numberOfTMmeasurementsPrism; ++var9) {
                var8[var9] = this.thicknessesTM[var9];
            }

            var8[this.numberOfTMmeasurementsPrism] = var1;
            this.thicknessesTM = var8;

            for(var9 = 0; var9 < this.numberOfTMmeasurementsPrism; ++var9) {
                var8[var9] = this.anglesDegTM[var9];
            }

            var8[this.numberOfTMmeasurementsPrism] = var3;
            this.anglesDegTM = var8;
            this.anglesRadTM = var8;
            this.errorsDegTM = var8;
            this.errorsRadTM = var8;

            for(var9 = 0; var9 < var7; ++var9) {
                this.anglesRadTM[var9] = Math.toRadians(this.anglesDegTM[var9]);
                this.errorsDegTM[var9] = 0.0D;
                this.errorsRadTM[var9] = 0.0D;
            }

            for(var9 = 0; var9 < this.numberOfTMmeasurementsPrism; ++var9) {
                var8[var9] = this.modeNumbersTM[var9];
            }

            var8[this.numberOfTMmeasurementsPrism] = var5;
            this.numberOfTMmeasurementsPrism = var7;
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
            this.numberOfTMmeasurementsPrism = 1;
        }

        this.numberOfMeasurementsPrism = this.numberOfTEmeasurementsPrism + this.numberOfTMmeasurementsPrism;
        this.setMeasurementsTMprism = true;
        this.setMeasurementsPrism = true;
        if (this.setPrismAlpha && this.setPrismRI) {
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
                if (this.setMeasurementsTEprism) {
                    if (this.setTEerrors) {
                        throw new IllegalArgumentException("All Entered data must either all have associated errors entered or all have no associated errors entered");
                    }

                    var7 = this.numberOfTEmeasurementsPrism + var4;
                    double[] var8 = new double[var7];

                    int var9;
                    for(var9 = 0; var9 < this.numberOfTEmeasurementsPrism; ++var9) {
                        var8[var9] = this.thicknessesTE[var9];
                    }

                    for(var9 = 0; var9 < var4; ++var9) {
                        var8[this.numberOfTEmeasurementsPrism + var9] = var1[var9];
                    }

                    this.thicknessesTE = var8;

                    for(var9 = 0; var9 < this.numberOfTEmeasurementsPrism; ++var9) {
                        var8[var9] = this.anglesDegTE[var9];
                    }

                    for(var9 = 0; var9 < var4; ++var9) {
                        var8[this.numberOfTEmeasurementsPrism + var9] = var2[var9];
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

                    for(var9 = 0; var9 < this.numberOfTEmeasurementsPrism; ++var9) {
                        var8[var9] = this.modeNumbersTE[var9];
                    }

                    for(var9 = 0; var9 < var4; ++var9) {
                        var8[this.numberOfTEmeasurementsPrism + var9] = var3[var9];
                    }

                    this.numberOfTEmeasurementsPrism = var7;
                } else {
                    this.numberOfTEmeasurementsPrism = var4;
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

                this.numberOfMeasurementsPrism = this.numberOfTEmeasurementsPrism + this.numberOfTMmeasurementsPrism;
                this.setMeasurementsTEprism = true;
                this.setMeasurementsPrism = true;
                if (this.setPrismAlpha && this.setPrismRI) {
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
                if (this.setMeasurementsTMprism) {
                    if (this.setTMerrors) {
                        throw new IllegalArgumentException("All Entered data must either all have associated errors entered or all have no associated errors entered");
                    }

                    var7 = this.numberOfTMmeasurementsPrism + var4;
                    double[] var8 = new double[var7];

                    int var9;
                    for(var9 = 0; var9 < this.numberOfTMmeasurementsPrism; ++var9) {
                        var8[var9] = this.thicknessesTM[var9];
                    }

                    for(var9 = 0; var9 < var4; ++var9) {
                        var8[this.numberOfTMmeasurementsPrism + var9] = var1[var9];
                    }

                    this.thicknessesTM = var8;

                    for(var9 = 0; var9 < this.numberOfTMmeasurementsPrism; ++var9) {
                        var8[var9] = this.anglesDegTM[var9];
                    }

                    for(var9 = 0; var9 < var4; ++var9) {
                        var8[this.numberOfTMmeasurementsPrism + var9] = var2[var9];
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

                    for(var9 = 0; var9 < this.numberOfTMmeasurementsPrism; ++var9) {
                        var8[var9] = this.modeNumbersTM[var9];
                    }

                    for(var9 = 0; var9 < var4; ++var9) {
                        var8[this.numberOfTMmeasurementsPrism + var9] = var3[var9];
                    }

                    this.numberOfTMmeasurementsPrism = var7;
                } else {
                    this.numberOfTMmeasurementsPrism = var4;
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

                this.numberOfMeasurementsPrism = this.numberOfTEmeasurementsPrism + this.numberOfTMmeasurementsPrism;
                this.setMeasurementsTMprism = true;
                this.setMeasurementsPrism = true;
                if (this.setPrismAlpha && this.setPrismRI) {
                    this.calcTMmodeEffectiveRefractiveIndices();
                }

            }
        }
    }

    public void enterTEmodeData(double var1, double var3, double var5, double var7) {
        if (this.setMeasurementsTEprism) {
            if (!this.setTEerrors) {
                throw new IllegalArgumentException("All Entered data must either all have associated errors entered or all have no associated errors entered");
            }

            int var9 = this.numberOfTEmeasurementsPrism + 1;
            double[] var10 = new double[var9];

            int var11;
            for(var11 = 0; var11 < this.numberOfTEmeasurementsPrism; ++var11) {
                var10[var11] = this.thicknessesTE[var11];
            }

            var10[this.numberOfTEmeasurementsPrism] = var1;
            this.thicknessesTE = var10;

            for(var11 = 0; var11 < this.numberOfTEmeasurementsPrism; ++var11) {
                var10[var11] = this.anglesDegTE[var11];
            }

            var10[this.numberOfTEmeasurementsPrism] = var3;
            this.anglesDegTE = var10;

            for(var11 = 0; var11 < this.numberOfTEmeasurementsPrism; ++var11) {
                var10[var11] = this.errorsDegTE[var11];
            }

            var10[this.numberOfTEmeasurementsPrism] = var5;
            this.errorsDegTE = var10;
            this.anglesRadTE = var10;
            this.errorsRadTE = var10;

            for(var11 = 0; var11 < var9; ++var11) {
                this.anglesRadTE[var11] = Math.toRadians(this.anglesDegTE[var11]);
                this.errorsRadTE[var11] = Math.toRadians(this.errorsDegTE[var11]);
            }

            for(var11 = 0; var11 < this.numberOfTEmeasurementsPrism; ++var11) {
                var10[var11] = this.modeNumbersTE[var11];
            }

            var10[this.numberOfTEmeasurementsPrism] = var7;
            this.numberOfTEmeasurementsPrism = var9;
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
            this.numberOfTEmeasurementsPrism = 1;
        }

        this.numberOfMeasurementsPrism = this.numberOfTEmeasurementsPrism + this.numberOfTMmeasurementsPrism;
        this.setMeasurementsTEprism = true;
        this.setTEerrors = true;
        this.setMeasurementsPrism = true;
        if (this.setPrismAlpha && this.setPrismRI) {
            this.calcTEmodeEffectiveRefractiveIndices();
        }

    }

    public void enterTMmodeData(double var1, double var3, double var5, double var7) {
        if (this.setMeasurementsTMprism) {
            if (!this.setTMerrors) {
                throw new IllegalArgumentException("All Entered data must either all have associated errors entered or all have no associated errors entered");
            }

            int var9 = this.numberOfTMmeasurementsPrism + 1;
            double[] var10 = new double[var9];

            int var11;
            for(var11 = 0; var11 < this.numberOfTMmeasurementsPrism; ++var11) {
                var10[var11] = this.thicknessesTM[var11];
            }

            var10[this.numberOfTMmeasurementsPrism] = var1;
            this.thicknessesTM = var10;

            for(var11 = 0; var11 < this.numberOfTMmeasurementsPrism; ++var11) {
                var10[var11] = this.anglesDegTM[var11];
            }

            var10[this.numberOfTMmeasurementsPrism] = var3;
            this.anglesDegTM = var10;

            for(var11 = 0; var11 < this.numberOfTMmeasurementsPrism; ++var11) {
                var10[var11] = this.errorsDegTM[var11];
            }

            var10[this.numberOfTMmeasurementsPrism] = var5;
            this.errorsDegTM = var10;
            this.anglesRadTM = var10;
            this.errorsRadTM = var10;

            for(var11 = 0; var11 < var9; ++var11) {
                this.anglesRadTM[var11] = Math.toRadians(this.anglesDegTM[var11]);
                this.errorsRadTM[var11] = Math.toRadians(this.errorsDegTM[var11]);
            }

            for(var11 = 0; var11 < this.numberOfTMmeasurementsPrism; ++var11) {
                var10[var11] = this.modeNumbersTM[var11];
            }

            var10[this.numberOfTMmeasurementsPrism] = var7;
            this.numberOfTMmeasurementsPrism = var9;
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
            this.numberOfTMmeasurementsPrism = 1;
        }

        this.numberOfMeasurementsPrism = this.numberOfTEmeasurementsPrism + this.numberOfTMmeasurementsPrism;
        this.setMeasurementsTMprism = true;
        this.setTMerrors = true;
        this.setMeasurementsPrism = true;
        if (this.setPrismAlpha && this.setPrismRI) {
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
                if (this.setMeasurementsTEprism) {
                    if (!this.setTEerrors) {
                        throw new IllegalArgumentException("All Entered data must either all have associated errors entered or all have no associated errors entered");
                    }

                    var8 = this.numberOfTEmeasurementsPrism + var5;
                    double[] var9 = new double[var8];

                    int var10;
                    for(var10 = 0; var10 < this.numberOfTEmeasurementsPrism; ++var10) {
                        var9[var10] = this.thicknessesTE[var10];
                    }

                    for(var10 = 0; var10 < var5; ++var10) {
                        var9[this.numberOfTEmeasurementsPrism + var10] = var1[var10];
                    }

                    this.thicknessesTE = var9;

                    for(var10 = 0; var10 < this.numberOfTEmeasurementsPrism; ++var10) {
                        var9[var10] = this.anglesDegTE[var10];
                    }

                    for(var10 = 0; var10 < var5; ++var10) {
                        var9[this.numberOfTEmeasurementsPrism + var10] = var2[var10];
                    }

                    this.anglesDegTE = var9;

                    for(var10 = 0; var10 < this.numberOfTEmeasurementsPrism; ++var10) {
                        var9[var10] = this.errorsDegTE[var10];
                    }

                    for(var10 = 0; var10 < var5; ++var10) {
                        var9[this.numberOfTEmeasurementsPrism + var10] = var3[var10];
                    }

                    this.errorsDegTE = var9;
                    this.anglesRadTE = var9;
                    this.errorsRadTE = var9;

                    for(var10 = 0; var10 < var8; ++var10) {
                        this.anglesRadTE[var10] = Math.toRadians(this.anglesDegTE[var10]);
                        this.errorsRadTE[var10] = Math.toRadians(this.errorsDegTE[var10]);
                    }

                    for(var10 = 0; var10 < this.numberOfTEmeasurementsPrism; ++var10) {
                        var9[var10] = this.modeNumbersTE[var10];
                    }

                    for(var10 = 0; var10 < var5; ++var10) {
                        var9[this.numberOfTEmeasurementsPrism + var10] = var4[var10];
                    }

                    this.numberOfTEmeasurementsPrism = var8;
                } else {
                    this.numberOfTEmeasurementsPrism = var5;
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

                this.numberOfMeasurementsPrism = this.numberOfTEmeasurementsPrism + this.numberOfTMmeasurementsPrism;
                this.setMeasurementsTEprism = true;
                this.setTEerrors = true;
                this.setMeasurementsPrism = true;
                if (this.setPrismAlpha && this.setPrismRI) {
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
                if (this.setMeasurementsTMprism) {
                    if (!this.setTMerrors) {
                        throw new IllegalArgumentException("All Entered data must either all have associated errors entered or all have no associated errors entered");
                    }

                    var8 = this.numberOfTMmeasurementsPrism + var5;
                    double[] var9 = new double[var8];

                    int var10;
                    for(var10 = 0; var10 < this.numberOfTMmeasurementsPrism; ++var10) {
                        var9[var10] = this.thicknessesTM[var10];
                    }

                    for(var10 = 0; var10 < var5; ++var10) {
                        var9[this.numberOfTMmeasurementsPrism + var10] = var1[var10];
                    }

                    this.thicknessesTM = var9;

                    for(var10 = 0; var10 < this.numberOfTMmeasurementsPrism; ++var10) {
                        var9[var10] = this.anglesDegTM[var10];
                    }

                    for(var10 = 0; var10 < var5; ++var10) {
                        var9[this.numberOfTMmeasurementsPrism + var10] = var2[var10];
                    }

                    this.anglesDegTM = var9;

                    for(var10 = 0; var10 < this.numberOfTMmeasurementsPrism; ++var10) {
                        var9[var10] = this.errorsDegTM[var10];
                    }

                    for(var10 = 0; var10 < var5; ++var10) {
                        var9[this.numberOfTMmeasurementsPrism + var10] = var3[var10];
                    }

                    this.errorsDegTM = var9;
                    this.anglesRadTM = var9;
                    this.errorsRadTM = var9;

                    for(var10 = 0; var10 < var8; ++var10) {
                        this.anglesRadTM[var10] = Math.toRadians(this.anglesDegTM[var10]);
                        this.errorsRadTM[var10] = Math.toRadians(this.errorsDegTM[var10]);
                    }

                    for(var10 = 0; var10 < this.numberOfTMmeasurementsPrism; ++var10) {
                        var9[var10] = this.modeNumbersTM[var10];
                    }

                    for(var10 = 0; var10 < var5; ++var10) {
                        var9[this.numberOfTMmeasurementsPrism + var10] = var4[var10];
                    }

                    this.numberOfTMmeasurementsPrism = var8;
                } else {
                    this.numberOfTMmeasurementsPrism = var5;
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

                this.numberOfMeasurementsPrism = this.numberOfTEmeasurementsPrism + this.numberOfTMmeasurementsPrism;
                this.setMeasurementsTMprism = true;
                this.setTMerrors = true;
                this.setMeasurementsPrism = true;
                if (this.setPrismAlpha && this.setPrismRI) {
                    this.calcTMmodeEffectiveRefractiveIndices();
                }

            }
        }
    }

    public void clearData() {
        this.numberOfTEmeasurementsPrism = 0;
        this.setMeasurementsTEprism = false;
        this.numberOfTMmeasurementsPrism = 0;
        this.setMeasurementsTMprism = false;
        super.numberOfMeasurements = 0;
        super.setMeasurements = false;
        super.setWeights = false;
        super.numberOfTEmeasurements = 0;
        super.setMeasurementsTE = false;
        super.numberOfTMmeasurements = 0;
        super.setMeasurementsTM = false;
    }

    public void calcEffectiveRefractiveIndices() {
        if (this.setMeasurementsTEprism) {
            this.calcTEmodeEffectiveRefractiveIndices();
        }

        if (this.setMeasurementsTMprism) {
            this.calcTMmodeEffectiveRefractiveIndices();
        }

    }

    public void calcTEmodeEffectiveRefractiveIndices() {
        this.effectiveRefractiveIndicesTE = new double[this.numberOfTEmeasurementsPrism];
        this.effectiveErrorsTE = new double[this.numberOfTEmeasurementsPrism];
        if (this.setTEerrors) {
            ErrorProp var1 = new ErrorProp(this.prismAngleAlphaRad, 0.0D);
            ErrorProp var2 = new ErrorProp(super.prismRefractiveIndex, 0.0D);
            ErrorProp var3 = new ErrorProp(RefractiveIndex.air(super.wavelength), 0.0D);
            new ErrorProp();
            ErrorProp var5 = new ErrorProp();

            for(int var6 = 0; var6 < this.numberOfTEmeasurementsPrism; ++var6) {
                var5.reset(this.anglesRadTE[var6], this.errorsRadTE[var6]);
                ErrorProp var4 = var5.over(var2).times(var3);
                var4 = ErrorProp.asin(var4);
                var4 = var1.plus(var4);
                var4 = var2.times(ErrorProp.sin(var4));
                this.effectiveRefractiveIndicesTE[var6] = var4.getValue();
                this.effectiveErrorsTE[var6] = var4.getError();
            }

            super.enterTEmodeData(this.thicknessesTE, this.effectiveRefractiveIndicesTE, this.effectiveErrorsTE, this.modeNumbersTE);
        } else {
            for(int var7 = 0; var7 < this.numberOfTEmeasurementsPrism; ++var7) {
                double var8 = this.prismAngleAlphaRad + Math.asin(RefractiveIndex.air(super.wavelength) * this.anglesRadTE[var7] / super.prismRefractiveIndex);
                this.effectiveRefractiveIndicesTE[var7] = super.prismRefractiveIndex * Math.sin(var8);
            }

            super.enterTEmodeData(this.thicknessesTE, this.effectiveRefractiveIndicesTE, this.modeNumbersTE);
        }

    }

    public void calcTMmodeEffectiveRefractiveIndices() {
        this.effectiveRefractiveIndicesTM = new double[this.numberOfTMmeasurementsPrism];
        this.effectiveErrorsTM = new double[this.numberOfTMmeasurementsPrism];
        if (this.setTMerrors) {
            ErrorProp var1 = new ErrorProp(this.prismAngleAlphaRad, 0.0D);
            ErrorProp var2 = new ErrorProp(super.prismRefractiveIndex, 0.0D);
            ErrorProp var3 = new ErrorProp(RefractiveIndex.air(super.wavelength), 0.0D);
            new ErrorProp();
            ErrorProp var5 = new ErrorProp();

            for(int var6 = 0; var6 < this.numberOfTMmeasurementsPrism; ++var6) {
                var5.reset(this.anglesRadTM[var6], this.errorsRadTM[var6]);
                ErrorProp var4 = var5.over(var2).times(var3);
                var4 = ErrorProp.asin(var4);
                var4 = var1.plus(var4);
                var4 = var2.times(ErrorProp.sin(var4));
                this.effectiveRefractiveIndicesTM[var6] = var4.getValue();
                this.effectiveErrorsTM[var6] = var4.getError();
            }

            super.enterTMmodeData(this.thicknessesTM, this.effectiveRefractiveIndicesTM, this.effectiveErrorsTM, this.modeNumbersTM);
        } else {
            for(int var7 = 0; var7 < this.numberOfTMmeasurementsPrism; ++var7) {
                double var8 = this.prismAngleAlphaRad + Math.asin(RefractiveIndex.air(super.wavelength) * this.anglesRadTM[var7] / super.prismRefractiveIndex);
                this.effectiveRefractiveIndicesTM[var7] = super.prismRefractiveIndex * Math.sin(var8);
            }

            super.enterTMmodeData(this.thicknessesTM, this.effectiveRefractiveIndicesTM, this.modeNumbersTM);
        }

    }
}
