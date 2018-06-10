//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.math;

import com.flanagan.complex.Complex;
import com.flanagan.io.FileInput;
import com.flanagan.io.FileOutput;
import com.flanagan.plot.PlotGraph;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
import javax.swing.JFrame;

public class FourierTransform extends Canvas implements Serializable {
    private static final long serialVersionUID = 1L;
    private Complex[] complexData = null;
    private Complex[] complexCorr = null;
    private boolean complexDataSet = false;
    private int originalDataLength = 0;
    private int fftDataLength = 0;
    private boolean dataAltered = false;
    private double[] fftData = null;
    private double[] fftCorr = null;
    private double[] fftResp = null;
    private boolean fftDataSet = false;
    private double[] fftDataWindow = null;
    private double[] fftCorrWindow = null;
    private int windowOption = 0;
    private String[] windowNames = new String[]{"no windowing applied", "Rectangular (square, box-car)", "Bartlett (triangular)", "Welch", "Hann (Hanning)", "Hamming", "Kaiser", "Gaussian"};
    private String windowName;
    private double kaiserAlpha;
    private double gaussianAlpha;
    private double[] weights;
    private boolean windowSet;
    private boolean windowApplied;
    private double sumOfSquaredWeights;
    private Complex[] transformedDataComplex;
    private double[] transformedDataFft;
    private boolean fftDone;
    private double[][] powerSpectrumEstimate;
    private boolean powSpecDone;
    private int psdNumberOfPoints;
    private int segmentNumber;
    private int segmentLength;
    private boolean overlap;
    private boolean segNumSet;
    private boolean segLenSet;
    private double deltaT;
    private boolean deltaTset;
    private double[][] correlationArray;
    private boolean correlateDone;
    private int numberOfWarnings;
    private boolean[] warning;
    private int plotLineOption;
    private int plotPointOption;
    private double[][] timeFrequency;
    private boolean shortTimeDone;
    private int numShortFreq;
    private int numShortTimes;
    private String shortTitle;

    public FourierTransform() {
        this.windowName = this.windowNames[0];
        this.kaiserAlpha = 2.0D;
        this.gaussianAlpha = 2.5D;
        this.weights = null;
        this.windowSet = false;
        this.windowApplied = false;
        this.sumOfSquaredWeights = 0.0D;
        this.transformedDataComplex = null;
        this.transformedDataFft = null;
        this.fftDone = false;
        this.powerSpectrumEstimate = (double[][])null;
        this.powSpecDone = false;
        this.psdNumberOfPoints = 0;
        this.segmentNumber = 1;
        this.segmentLength = 0;
        this.overlap = false;
        this.segNumSet = false;
        this.segLenSet = false;
        this.deltaT = 1.0D;
        this.deltaTset = false;
        this.correlationArray = (double[][])null;
        this.correlateDone = false;
        this.numberOfWarnings = 9;
        this.warning = new boolean[this.numberOfWarnings];
        this.plotLineOption = 0;
        this.plotPointOption = 0;
        this.timeFrequency = (double[][])null;
        this.shortTimeDone = false;
        this.numShortFreq = 0;
        this.numShortTimes = 0;
        this.shortTitle = " ";

        for(int var1 = 0; var1 < this.numberOfWarnings; ++var1) {
            this.warning[var1] = false;
        }

    }

    public FourierTransform(double[] var1) {
        this.windowName = this.windowNames[0];
        this.kaiserAlpha = 2.0D;
        this.gaussianAlpha = 2.5D;
        this.weights = null;
        this.windowSet = false;
        this.windowApplied = false;
        this.sumOfSquaredWeights = 0.0D;
        this.transformedDataComplex = null;
        this.transformedDataFft = null;
        this.fftDone = false;
        this.powerSpectrumEstimate = (double[][])null;
        this.powSpecDone = false;
        this.psdNumberOfPoints = 0;
        this.segmentNumber = 1;
        this.segmentLength = 0;
        this.overlap = false;
        this.segNumSet = false;
        this.segLenSet = false;
        this.deltaT = 1.0D;
        this.deltaTset = false;
        this.correlationArray = (double[][])null;
        this.correlateDone = false;
        this.numberOfWarnings = 9;
        this.warning = new boolean[this.numberOfWarnings];
        this.plotLineOption = 0;
        this.plotPointOption = 0;
        this.timeFrequency = (double[][])null;
        this.shortTimeDone = false;
        this.numShortFreq = 0;
        this.numShortTimes = 0;
        this.shortTitle = " ";
        this.originalDataLength = var1.length;
        this.fftDataLength = nextPowerOfTwo(this.originalDataLength);
        this.complexData = Complex.oneDarray(this.fftDataLength);

        int var2;
        for(var2 = 0; var2 < this.originalDataLength; ++var2) {
            this.complexData[var2].setReal(var1[var2]);
            this.complexData[var2].setImag(0.0D);
        }

        for(var2 = this.originalDataLength; var2 < this.fftDataLength; ++var2) {
            this.complexData[var2].reset(0.0D, 0.0D);
        }

        this.complexDataSet = true;
        this.fftData = new double[2 * this.fftDataLength];
        var2 = 0;

        int var3;
        for(var3 = 0; var3 < this.fftDataLength; ++var3) {
            this.fftData[var2] = this.complexData[var3].getReal();
            ++var2;
            this.fftData[var2] = 0.0D;
            ++var2;
        }

        this.fftDataSet = true;
        this.fftDataWindow = new double[2 * this.fftDataLength];
        this.weights = new double[this.fftDataLength];
        this.sumOfSquaredWeights = this.windowData(this.fftData, this.fftDataWindow, this.weights);
        this.transformedDataFft = new double[2 * this.fftDataLength];
        this.transformedDataComplex = Complex.oneDarray(this.fftDataLength);
        this.segmentLength = this.fftDataLength;

        for(var3 = 0; var3 < this.numberOfWarnings; ++var3) {
            this.warning[var3] = false;
        }

    }

    public FourierTransform(Complex[] var1) {
        this.windowName = this.windowNames[0];
        this.kaiserAlpha = 2.0D;
        this.gaussianAlpha = 2.5D;
        this.weights = null;
        this.windowSet = false;
        this.windowApplied = false;
        this.sumOfSquaredWeights = 0.0D;
        this.transformedDataComplex = null;
        this.transformedDataFft = null;
        this.fftDone = false;
        this.powerSpectrumEstimate = (double[][])null;
        this.powSpecDone = false;
        this.psdNumberOfPoints = 0;
        this.segmentNumber = 1;
        this.segmentLength = 0;
        this.overlap = false;
        this.segNumSet = false;
        this.segLenSet = false;
        this.deltaT = 1.0D;
        this.deltaTset = false;
        this.correlationArray = (double[][])null;
        this.correlateDone = false;
        this.numberOfWarnings = 9;
        this.warning = new boolean[this.numberOfWarnings];
        this.plotLineOption = 0;
        this.plotPointOption = 0;
        this.timeFrequency = (double[][])null;
        this.shortTimeDone = false;
        this.numShortFreq = 0;
        this.numShortTimes = 0;
        this.shortTitle = " ";
        this.originalDataLength = var1.length;
        this.fftDataLength = nextPowerOfTwo(this.originalDataLength);
        this.complexData = Complex.oneDarray(this.fftDataLength);

        int var2;
        for(var2 = 0; var2 < this.originalDataLength; ++var2) {
            this.complexData[var2] = var1[var2].copy();
        }

        for(var2 = this.originalDataLength; var2 < this.fftDataLength; ++var2) {
            this.complexData[var2].reset(0.0D, 0.0D);
        }

        this.complexDataSet = true;
        this.fftData = new double[2 * this.fftDataLength];
        var2 = 0;

        int var3;
        for(var3 = 0; var3 < this.fftDataLength; ++var3) {
            this.fftData[var2] = this.complexData[var3].getReal();
            ++var2;
            this.fftData[var2] = this.complexData[var3].getImag();
            ++var2;
        }

        this.fftDataSet = true;
        this.fftDataWindow = new double[2 * this.fftDataLength];
        this.weights = new double[this.fftDataLength];
        this.sumOfSquaredWeights = this.windowData(this.fftData, this.fftDataWindow, this.weights);
        this.transformedDataFft = new double[2 * this.fftDataLength];
        this.transformedDataComplex = Complex.oneDarray(this.fftDataLength);
        this.segmentLength = this.fftDataLength;

        for(var3 = 0; var3 < this.numberOfWarnings; ++var3) {
            this.warning[var3] = false;
        }

    }

    public void setData(double[] var1) {
        this.originalDataLength = var1.length;
        this.fftDataLength = nextPowerOfTwo(this.originalDataLength);
        this.complexData = Complex.oneDarray(this.fftDataLength);

        int var2;
        for(var2 = 0; var2 < this.originalDataLength; ++var2) {
            this.complexData[var2].setReal(var1[var2]);
            this.complexData[var2].setImag(0.0D);
        }

        for(var2 = this.originalDataLength; var2 < this.fftDataLength; ++var2) {
            this.complexData[var2].reset(0.0D, 0.0D);
        }

        this.complexDataSet = true;
        this.fftData = new double[2 * this.fftDataLength];
        var2 = 0;

        for(int var3 = 0; var3 < this.fftDataLength; ++var3) {
            this.fftData[var2] = this.complexData[var3].getReal();
            ++var2;
            this.fftData[var2] = 0.0D;
            ++var2;
        }

        this.fftDataSet = true;
        this.fftDataWindow = new double[2 * this.fftDataLength];
        this.weights = new double[this.fftDataLength];
        this.sumOfSquaredWeights = this.windowData(this.fftData, this.fftDataWindow, this.weights);
        this.transformedDataFft = new double[2 * this.fftDataLength];
        this.transformedDataComplex = Complex.oneDarray(this.fftDataLength);
        if (this.segNumSet) {
            this.setSegmentNumber(this.segmentNumber);
        } else if (this.segLenSet) {
            this.setSegmentLength(this.segmentLength);
        } else {
            this.segmentLength = this.fftDataLength;
        }

    }

    public void setData(Complex[] var1) {
        this.originalDataLength = var1.length;
        this.fftDataLength = nextPowerOfTwo(this.originalDataLength);
        this.complexData = Complex.oneDarray(this.fftDataLength);

        int var2;
        for(var2 = 0; var2 < this.originalDataLength; ++var2) {
            this.complexData[var2] = var1[var2].copy();
        }

        for(var2 = this.originalDataLength; var2 < this.fftDataLength; ++var2) {
            this.complexData[var2].reset(0.0D, 0.0D);
        }

        this.complexDataSet = true;
        this.fftData = new double[2 * this.fftDataLength];
        var2 = 0;

        for(int var3 = 0; var3 < this.fftDataLength; ++var3) {
            this.fftData[var2] = this.complexData[var3].getReal();
            ++var2;
            this.fftData[var2] = this.complexData[var3].getImag();
            ++var2;
        }

        this.fftDataSet = true;
        this.fftDataWindow = new double[2 * this.fftDataLength];
        this.weights = new double[this.fftDataLength];
        this.sumOfSquaredWeights = this.windowData(this.fftData, this.fftDataWindow, this.weights);
        this.transformedDataFft = new double[2 * this.fftDataLength];
        this.transformedDataComplex = Complex.oneDarray(this.fftDataLength);
        if (this.segNumSet) {
            this.setSegmentNumber(this.segmentNumber);
        } else if (this.segLenSet) {
            this.setSegmentLength(this.segmentLength);
        } else {
            this.segmentLength = this.fftDataLength;
        }

    }

    public void setFftData(double[] var1) {
        if (var1.length % 2 != 0) {
            throw new IllegalArgumentException("data length must be an even number");
        } else {
            this.originalDataLength = var1.length / 2;
            this.fftDataLength = nextPowerOfTwo(this.originalDataLength);
            this.fftData = new double[2 * this.fftDataLength];

            int var2;
            for(var2 = 0; var2 < 2 * this.originalDataLength; ++var2) {
                this.fftData[var2] = var1[var2];
            }

            for(var2 = 2 * this.originalDataLength; var2 < 2 * this.fftDataLength; ++var2) {
                this.fftData[var2] = 0.0D;
            }

            this.fftDataSet = true;
            this.complexData = Complex.oneDarray(this.fftDataLength);
            var2 = -1;

            for(int var3 = 0; var3 < this.fftDataLength; ++var3) {
                Complex var10000 = this.complexData[var3];
                ++var2;
                var10000.setReal(this.fftData[var2]);
                var10000 = this.complexData[var3];
                ++var2;
                var10000.setImag(this.fftData[var2]);
            }

            this.complexDataSet = true;
            this.fftDataWindow = new double[2 * this.fftDataLength];
            this.weights = new double[this.fftDataLength];
            this.sumOfSquaredWeights = this.windowData(this.fftData, this.fftDataWindow, this.weights);
            this.transformedDataFft = new double[2 * this.fftDataLength];
            this.transformedDataComplex = Complex.oneDarray(this.fftDataLength);
            if (this.segNumSet) {
                this.setSegmentNumber(this.segmentNumber);
            } else if (this.segLenSet) {
                this.setSegmentLength(this.segmentLength);
            } else {
                this.segmentLength = this.fftDataLength;
            }

        }
    }

    public Complex[] getComplexInputData() {
        if (!this.complexDataSet) {
            System.out.println("complex data set not entered or calculated - null returned");
        }

        return this.complexData;
    }

    public double[] getAlternateInputData() {
        if (!this.fftDataSet) {
            System.out.println("fft data set not entered or calculted - null returned");
        }

        return this.fftData;
    }

    public double[] getAlternateWindowedInputData() {
        if (!this.fftDataSet) {
            System.out.println("fft data set not entered or calculted - null returned");
        }

        if (!this.fftDataSet) {
            System.out.println("fft data set not entered or calculted - null returned");
        }

        if (!this.windowApplied) {
            System.out.println("fft data set has not been multiplied by windowing weights");
        }

        return this.fftDataWindow;
    }

    public int getOriginalDataLength() {
        return this.originalDataLength;
    }

    public int getUsedDataLength() {
        return this.fftDataLength;
    }

    public void setDeltaT(double var1) {
        this.deltaT = var1;
        this.deltaTset = true;
    }

    public double getDeltaT() {
        double var1 = 0.0D;
        if (this.deltaTset) {
            var1 = this.deltaT;
        } else {
            System.out.println("detaT has not been set - zero returned");
        }

        return var1;
    }

    public void setRectangular() {
        this.windowOption = 1;
        this.windowSet = true;
        if (this.fftDataSet) {
            this.sumOfSquaredWeights = this.windowData(this.fftData, this.fftDataWindow, this.weights);
            this.windowApplied = true;
        }

    }

    public void setBartlett() {
        this.windowOption = 2;
        this.windowSet = true;
        if (this.fftDataSet) {
            this.sumOfSquaredWeights = this.windowData(this.fftData, this.fftDataWindow, this.weights);
            this.windowApplied = true;
        }

    }

    public void setWelch() {
        this.windowOption = 3;
        this.windowSet = true;
        if (this.fftDataSet) {
            this.sumOfSquaredWeights = this.windowData(this.fftData, this.fftDataWindow, this.weights);
            this.windowApplied = true;
        }

    }

    public void setHann() {
        this.windowOption = 4;
        this.windowSet = true;
        if (this.fftDataSet) {
            this.sumOfSquaredWeights = this.windowData(this.fftData, this.fftDataWindow, this.weights);
            this.windowApplied = true;
        }

    }

    public void setHamming() {
        this.windowOption = 5;
        this.windowSet = true;
        if (this.fftDataSet) {
            this.sumOfSquaredWeights = this.windowData(this.fftData, this.fftDataWindow, this.weights);
            this.windowApplied = true;
        }

    }

    public void setKaiser(double var1) {
        this.kaiserAlpha = var1;
        this.windowOption = 6;
        this.windowSet = true;
        if (this.fftDataSet) {
            this.sumOfSquaredWeights = this.windowData(this.fftData, this.fftDataWindow, this.weights);
            this.windowApplied = true;
        }

    }

    public void setKaiser() {
        this.windowOption = 6;
        this.windowSet = true;
        if (this.fftDataSet) {
            this.sumOfSquaredWeights = this.windowData(this.fftData, this.fftDataWindow, this.weights);
            this.windowApplied = true;
        }

    }

    public void setGaussian(double var1) {
        if (var1 < 2.0D) {
            var1 = 2.0D;
            System.out.println("setGaussian; alpha must be greater than or equal to 2 - alpha has been reset to 2");
        }

        this.gaussianAlpha = var1;
        this.windowOption = 7;
        this.windowSet = true;
        if (this.fftDataSet) {
            this.sumOfSquaredWeights = this.windowData(this.fftData, this.fftDataWindow, this.weights);
            this.windowApplied = true;
        }

    }

    public void setGaussian() {
        this.windowOption = 7;
        this.windowSet = true;
        if (this.fftDataSet) {
            this.sumOfSquaredWeights = this.windowData(this.fftData, this.fftDataWindow, this.weights);
            this.windowApplied = true;
        }

    }

    public void removeWindow() {
        this.windowOption = 0;
        this.windowSet = false;
        if (this.fftDataSet) {
            this.sumOfSquaredWeights = this.windowData(this.fftData, this.fftDataWindow, this.weights);
            this.windowApplied = false;
        }

    }

    private double windowData(double[] var1, double[] var2, double[] var3) {
        int var4 = var1.length;
        int var5 = var4 / 2 - 1;
        int var6 = 0;
        double var7 = 0.0D;
        int var13;
        int var14;
        switch(this.windowOption) {
            case 0:
            case 1:
                for(var14 = 0; var14 <= var5; ++var14) {
                    var3[var14] = 1.0D;
                    var2[var6] = var1[var6++];
                    var2[var6] = var1[var6++];
                }

                var7 = (double)(var5 + 1);
                break;
            case 2:
                for(var14 = 0; var14 <= var5; ++var14) {
                    var3[var14] = 1.0D - (double)Math.abs((var14 - var5 / 2) / var5 / 2);
                    var7 += var3[var14] * var3[var14];
                    var2[var6] = var1[var6++] * var3[var14];
                    var2[var6] = var1[var6++] * var3[var14];
                }

                return var7;
            case 3:
                for(var14 = 0; var14 <= var5; ++var14) {
                    var3[var14] = 1.0D - (double)Fmath.square((var14 - var5 / 2) / var5 / 2);
                    var7 += var3[var14] * var3[var14];
                    var2[var6] = var1[var6++] * var3[var14];
                    var2[var6] = var1[var6++] * var3[var14];
                }

                return var7;
            case 4:
                for(var14 = 0; var14 <= var5; ++var14) {
                    var3[var14] = (1.0D - Math.cos(2.0D * (double)var14 * 3.141592653589793D / (double)var5)) / 2.0D;
                    var7 += var3[var14] * var3[var14];
                    var2[var6] = var1[var6++] * var3[var14];
                    var2[var6] = var1[var6++] * var3[var14];
                }

                return var7;
            case 5:
                for(var14 = 0; var14 <= var5; ++var14) {
                    var3[var14] = 0.54D + 0.46D * Math.cos(2.0D * (double)var14 * 3.141592653589793D / (double)var5);
                    var7 += var3[var14] * var3[var14];
                    var2[var6] = var1[var6++] * var3[var14];
                    var2[var6] = var1[var6++] * var3[var14];
                }

                return var7;
            case 6:
                double var9 = modBesselIo(3.141592653589793D * this.kaiserAlpha);
                double var11 = 0.0D;

                for(var13 = 0; var13 <= var5; ++var13) {
                    var11 = modBesselIo(3.141592653589793D * this.kaiserAlpha * Math.sqrt(1.0D - Fmath.square(2.0D * (double)var13 / (double)var5 - 1.0D)));
                    var3[var13] = var11 / var9;
                    var7 += var3[var13] * var3[var13];
                    var2[var6] = var1[var6++] * var3[var13];
                    var2[var6] = var1[var6++] * var3[var13];
                }

                return var7;
            case 7:
                for(var13 = 0; var13 <= var5; ++var13) {
                    var3[var13] = Math.exp(-0.5D * Fmath.square(this.gaussianAlpha * (double)(2 * var13 - var5) / (double)var5));
                    var7 += var3[var13] * var3[var13];
                    var2[var6] = var1[var6++] * var3[var13];
                    var2[var6] = var1[var6++] * var3[var13];
                }
        }

        return var7;
    }

    public static double modBesselIo(double var0) {
        double var2 = 0.0D;
        double var4 = 0.0D;
        double var6 = 0.0D;
        if ((var2 = Math.abs(var0)) < 3.75D) {
            var4 = var0 / 3.75D;
            var4 *= var4;
            var6 = 1.0D + var4 * (3.5156229D + var4 * (3.08989424D + var4 * (1.2067492D + var4 * (0.2659732D + var4 * (0.0360768D + var4 * 0.0045813D)))));
        } else {
            var6 = Math.exp(var2) / Math.sqrt(var2) * (0.39894228D + var4 * (0.01328592D + var4 * (0.00225319D + var4 * (-0.00157565D + var4 * (0.00916281D + var4 * (-0.02057706D + var4 * (0.02635537D + var4 * (-0.01647633D + var4 * 0.00392377D))))))));
        }

        return var6;
    }

    public String getWindowOption() {
        String var1 = " ";
        switch(this.windowOption) {
            case 0:
                var1 = "No windowing applied";
                break;
            case 1:
                var1 = "Rectangular";
                break;
            case 2:
                var1 = "Bartlett";
                break;
            case 3:
                var1 = "Welch";
                break;
            case 4:
                var1 = "Hann";
                break;
            case 5:
                var1 = "Hamming";
                break;
            case 6:
                var1 = "Kaiser";
                break;
            case 7:
                var1 = "Gaussian";
        }

        return var1;
    }

    public double[] getWeights() {
        return this.weights;
    }

    public void setSegmentNumber(int var1) {
        this.segmentNumber = var1;
        this.segNumSet = true;
        if (this.segLenSet) {
            this.segLenSet = false;
        }

    }

    public void setSegmentLength(int var1) {
        this.segmentLength = var1;
        this.segLenSet = true;
        if (this.segNumSet) {
            this.segNumSet = false;
        }

    }

    private void checkSegmentDetails() {
        if (!this.fftDataSet) {
            throw new IllegalArgumentException("No fft data has been entered or calculated");
        } else if (this.fftDataLength < 2) {
            throw new IllegalArgumentException("More than one point, MANY MORE, are needed");
        } else {
            if (this.fftDataLength % 2 != 0) {
                System.out.println("Number of data points must be an even number");
                System.out.println("last point deleted");
                --this.fftDataLength;
                this.dataAltered = true;
                this.warning[0] = true;
            }

            int var1;
            if (this.segNumSet && !this.overlap) {
                if (this.fftDataLength % this.segmentNumber == 0) {
                    var1 = this.fftDataLength / this.segmentNumber;
                    if (checkPowerOfTwo(var1)) {
                        this.segmentLength = var1;
                        this.segLenSet = true;
                    } else {
                        System.out.println("segment length is not an integer power of two");
                        System.out.println("segment length reset to total data length, i.e. no segmentation");
                        this.warning[1] = true;
                        this.segmentNumber = 1;
                        this.segmentLength = this.fftDataLength;
                        this.segLenSet = true;
                    }
                } else {
                    System.out.println("total data length divided by the number of segments is not an integer");
                    System.out.println("segment length reset to total data length, i.e. no segmentation");
                    this.warning[2] = true;
                    this.segmentNumber = 1;
                    this.segmentLength = this.fftDataLength;
                    this.segLenSet = true;
                }
            }

            if (this.segLenSet && !this.overlap) {
                if (this.fftDataLength % this.segmentLength == 0) {
                    if (checkPowerOfTwo(this.segmentLength)) {
                        this.segmentNumber = this.fftDataLength / this.segmentLength;
                        this.segNumSet = true;
                    } else {
                        System.out.println("segment length is not an integer power of two");
                        System.out.println("segment length reset to total data length, i.e. no segmentation");
                        this.warning[1] = true;
                        this.segmentNumber = 1;
                        this.segmentLength = this.fftDataLength;
                        this.segNumSet = true;
                    }
                } else {
                    System.out.println("total data length divided by the segment length is not an integer");
                    System.out.println("segment length reset to total data length, i.e. no segmentation");
                    this.warning[3] = true;
                    this.segmentNumber = 1;
                    this.segmentLength = this.fftDataLength;
                    this.segNumSet = true;
                }
            }

            if (this.segNumSet && this.overlap) {
                if (this.fftDataLength % (this.segmentNumber + 1) == 0) {
                    var1 = 2 * this.fftDataLength / (this.segmentNumber + 1);
                    if (checkPowerOfTwo(var1)) {
                        this.segmentLength = var1;
                        this.segLenSet = true;
                    } else {
                        System.out.println("segment length is not an integer power of two");
                        System.out.println("segment length reset to total data length, i.e. no segmentation");
                        this.warning[1] = true;
                        this.segmentNumber = 1;
                        this.segmentLength = this.fftDataLength;
                        this.segLenSet = true;
                        this.overlap = false;
                    }
                } else {
                    System.out.println("total data length divided by the number of segments plus one is not an integer");
                    System.out.println("segment length reset to total data length, i.e. no segmentation");
                    this.warning[4] = true;
                    this.segmentNumber = 1;
                    this.segmentLength = this.fftDataLength;
                    this.segLenSet = true;
                    this.overlap = false;
                }
            }

            if (this.segLenSet && this.overlap) {
                if (2 * this.fftDataLength % this.segmentLength == 0) {
                    if (checkPowerOfTwo(this.segmentLength)) {
                        this.segmentNumber = 2 * this.fftDataLength / this.segmentLength - 1;
                        this.segNumSet = true;
                    } else {
                        System.out.println("segment length is not an integer power of two");
                        System.out.println("segment length reset to total data length, i.e. no segmentation");
                        this.warning[1] = true;
                        this.segmentNumber = 1;
                        this.segmentLength = this.fftDataLength;
                        this.segNumSet = true;
                        this.overlap = false;
                    }
                } else {
                    System.out.println("twice the total data length divided by the segment length is not an integer");
                    System.out.println("segment length reset to total data length, i.e. no segmentation");
                    this.warning[5] = true;
                    this.segmentNumber = 1;
                    this.segmentLength = this.fftDataLength;
                    this.segNumSet = true;
                    this.overlap = false;
                }
            }

            if (!this.segNumSet && !this.segLenSet) {
                this.segmentNumber = 1;
                this.segNumSet = true;
                this.overlap = false;
            }

            if (this.overlap && this.segmentNumber < 2) {
                System.out.println("Overlap is not possible with less than two segments.");
                System.out.println("Overlap option has been reset to 'no overlap' i.e. to false.");
                this.overlap = false;
                this.segmentNumber = 1;
                this.segNumSet = true;
                this.warning[6] = true;
            }

            var1 = 0;
            int var2 = 0;
            int var3 = 0;
            int var4 = 0;
            if (this.segmentNumber == 1 && !checkPowerOfTwo(this.fftDataLength)) {
                boolean var5 = true;
                boolean var6 = true;
                boolean var7 = true;
                boolean var8 = false;
                int var9 = 2;

                while(true) {
                    int var14;
                    while(var5) {
                        var14 = this.fftDataLength / var9;
                        if (checkPowerOfTwo(var14) && this.fftDataLength % var9 == 0) {
                            var5 = false;
                            var1 = var14;
                            var2 = var9;
                        } else if (var14 < 2) {
                            var6 = false;
                            var5 = false;
                        } else {
                            ++var9;
                        }
                    }

                    var5 = true;
                    var9 = 2;

                    while(true) {
                        while(var5) {
                            var14 = 2 * (this.fftDataLength / (var9 + 1));
                            if (checkPowerOfTwo(var14) && this.fftDataLength % (var9 + 1) == 0) {
                                var5 = false;
                                var3 = var14;
                                var4 = var9;
                            } else if (var14 < 2) {
                                var7 = false;
                                var5 = false;
                            } else {
                                ++var9;
                            }
                        }

                        boolean var10 = true;
                        int var11 = 0;
                        int var12 = 0;
                        boolean var13 = false;
                        if (var6) {
                            if (var7) {
                                if (var3 > var1) {
                                    var11 = var3;
                                    var12 = var4;
                                    var13 = true;
                                } else {
                                    var11 = var1;
                                    var12 = var2;
                                    var13 = false;
                                }
                            } else {
                                var11 = var1;
                                var12 = var2;
                                var13 = false;
                            }
                        } else if (var7) {
                            var11 = var3;
                            var12 = var4;
                            var13 = true;
                        } else {
                            var10 = false;
                        }

                        if (var10 && this.originalDataLength - var11 <= this.fftDataLength - this.originalDataLength) {
                            System.out.println("Data length is not an integer power of two");
                            System.out.println("Data cannot be transformed as a single segment");
                            System.out.print("The data has been split into " + var12 + " segments of length " + var11);
                            if (var13) {
                                System.out.println(" with 50% overlap");
                            } else {
                                System.out.println(" with no overlap");
                            }

                            this.segmentLength = var11;
                            this.segmentNumber = var12;
                            this.overlap = var13;
                            this.warning[7] = true;
                        } else {
                            System.out.println("Data length is not an integer power of two");
                            if (this.dataAltered) {
                                System.out.println("Deleted point has been restored and the data has been padded with zeros to give a power of two length");
                                this.warning[0] = false;
                            } else {
                                System.out.println("Data has been padded with zeros to give a power of two length");
                            }

                            this.fftDataLength = this.fftDataLength;
                            this.warning[8] = true;
                        }

                        return;
                    }
                }
            }
        }
    }

    private void printWarnings(FileOutput var1) {
        if (this.warning[0]) {
            var1.println("WARNING!");
            var1.println("Number of data points must be an even number");
            var1.println("The last point was deleted");
            var1.println();
        }

        if (this.warning[1]) {
            var1.println("WARNING!");
            var1.println("Segment length was not an integer power of two");
            var1.println("Segment length was reset to total data length, i.e. no segmentation");
            var1.println();
        }

        if (this.warning[2]) {
            var1.println("WARNING!");
            var1.println("Total data length divided by the number of segments was not an integer");
            var1.println("Segment length was reset to total data length, i.e. no segmentation");
            var1.println();
        }

        if (this.warning[3]) {
            var1.println("WARNING!");
            var1.println("Total data length divided by the segment length was not an integer");
            var1.println("Segment length was reset to total data length, i.e. no segmentation");
            var1.println();
        }

        if (this.warning[4]) {
            var1.println("WARNING!");
            var1.println("Total data length divided by the number of segments plus one was not an integer");
            var1.println("Segment length was reset to total data length, i.e. no segmentation");
            var1.println();
        }

        if (this.warning[5]) {
            var1.println("WARNING!");
            var1.println("Twice the total data length divided by the segment length was not an integer");
            var1.println("Segment length was reset to total data length, i.e. no segmentation");
            var1.println();
        }

        if (this.warning[6]) {
            var1.println("WARNING!");
            var1.println("Overlap is not possible with less than two segments");
            var1.println("Overlap option has been reset to 'no overlap' i.e. to false");
            var1.println();
        }

        if (this.warning[7]) {
            var1.println("WARNING!");
            var1.println("Data length was not an integer power of two");
            var1.println("The data could not be transformed as a single segment");
            var1.print("The data has been split into " + this.segmentNumber + " segment/s of length " + this.segmentLength);
            if (this.overlap) {
                var1.println(" with 50% overlap");
            } else {
                var1.println(" with no overlap");
            }

            var1.println();
        }

        if (this.warning[8]) {
            var1.println("WARNING!");
            var1.println("Data length was not an integer power of two");
            var1.println("Data has been padded with " + (this.fftDataLength - this.originalDataLength) + " zeros to give an integer power of two length");
            var1.println();
        }

    }

    public int getSegmentNumber() {
        return this.segmentNumber;
    }

    public int getSegmentLength() {
        return this.segmentLength;
    }

    public void setOverlapOption(boolean var1) {
        boolean var2 = this.overlap;
        this.overlap = var1;
        if (var2 != this.overlap && this.fftDataSet) {
            this.setSegmentNumber(this.segmentNumber);
        }

    }

    public boolean getOverlapOption() {
        return this.overlap;
    }

    public static int calcDataLength(boolean var0, int var1, int var2) {
        return var0 ? (var2 + 1) * var1 / 2 : var2 * var1;
    }

    public void transform() {
        byte var1 = 1;
        if (!this.fftDataSet) {
            throw new IllegalArgumentException("No data has been entered for the Fast Fourier Transform");
        } else {
            if (this.originalDataLength != this.fftDataLength) {
                System.out.println("Fast Fourier Transform data length ," + this.originalDataLength + ", is not an integer power of two");
                System.out.println("WARNING!!! Data has been padded with zeros to fill to nearest integer power of two length " + this.fftDataLength);
            }

            double[] var2 = new double[this.fftDataLength * 2];

            int var3;
            for(var3 = 0; var3 < this.fftDataLength * 2; ++var3) {
                var2[var3] = this.fftDataWindow[var3];
            }

            this.basicFft(var2, this.fftDataLength, var1);

            for(var3 = 0; var3 < this.fftDataLength * 2; ++var3) {
                this.transformedDataFft[var3] = var2[var3];
            }

            for(var3 = 0; var3 < this.fftDataLength; ++var3) {
                this.transformedDataComplex[var3].reset(this.transformedDataFft[2 * var3], this.transformedDataFft[2 * var3 + 1]);
            }

        }
    }

    public void inverse() {
        byte var1 = -1;
        if (!this.fftDataSet) {
            throw new IllegalArgumentException("No data has been entered for the inverse Fast Fourier Transform");
        } else {
            if (this.originalDataLength != this.fftDataLength) {
                System.out.println("Fast Fourier Transform data length ," + this.originalDataLength + ", is not an integer power of two");
                System.out.println("WARNING!!! Data has been padded with zeros to fill to nearest integer power of two length " + this.fftDataLength);
            }

            double[] var2 = new double[this.fftDataLength * 2];

            int var3;
            for(var3 = 0; var3 < this.fftDataLength * 2; ++var3) {
                var2[var3] = this.fftDataWindow[var3];
            }

            this.basicFft(var2, this.fftDataLength, var1);

            for(var3 = 0; var3 < this.fftDataLength * 2; ++var3) {
                this.transformedDataFft[var3] = var2[var3] / (double)this.fftDataLength;
            }

            for(var3 = 0; var3 < this.fftDataLength; ++var3) {
                this.transformedDataComplex[var3].reset(this.transformedDataFft[2 * var3], this.transformedDataFft[2 * var3 + 1]);
            }

        }
    }

    public void basicFft(double[] var1, int var2, int var3) {
        double var4 = 0.0D;
        double var6 = 0.0D;
        double var8 = 0.0D;
        double var10 = 0.0D;
        double var12 = 0.0D;
        double var14 = 0.0D;
        double var16 = 0.0D;
        double var18 = 0.0D;
        double var20 = 0.0D;
        boolean var22 = false;
        boolean var23 = false;
        boolean var24 = false;
        int var25 = var2 << 1;
        int var26 = 1;
        boolean var27 = false;

        int var33;
        int var28;
        int var29;
        int var31;
        for(var28 = 1; var28 < var25; var28 += 2) {
            var33 = var26 - 1;
            if (var26 > var28) {
                var29 = var28 - 1;
                var4 = var1[var33];
                var1[var33] = var1[var29];
                var1[var29] = var4;
                var4 = var1[var33 + 1];
                var1[var33 + 1] = var1[var29 + 1];
                var1[var29 + 1] = var4;
            }

            for(var31 = var25 >> 1; var31 >= 2 && var26 > var31; var31 >>= 1) {
                var26 -= var31;
            }

            var26 += var31;
        }

        int var30;
        for(int var32 = 2; var25 > var32; var32 = var30) {
            var30 = var32 << 1;
            var12 = (double)var3 * (6.28318530717959D / (double)var32);
            var6 = Math.sin(0.5D * var12);
            var16 = -2.0D * var6 * var6;
            var18 = Math.sin(var12);
            var14 = 1.0D;
            var20 = 0.0D;

            for(var31 = 1; var31 < var32; var31 = (int)((long)var31 + 2L)) {
                for(var28 = var31; var28 <= var25; var28 += var30) {
                    var29 = var28 - 1;
                    var33 = var29 + var32;
                    var8 = var14 * var1[var33] - var20 * var1[var33 + 1];
                    var10 = var14 * var1[var33 + 1] + var20 * var1[var33];
                    var1[var33] = var1[var29] - var8;
                    var1[var33 + 1] = var1[var29 + 1] - var10;
                    var1[var29] += var8;
                    var1[var29 + 1] += var10;
                }

                var6 = var14;
                var14 += var14 * var16 - var20 * var18;
                var20 += var20 * var16 + var6 * var18;
            }
        }

    }

    public Complex[] getTransformedDataAsComplex() {
        return this.transformedDataComplex;
    }

    public double[] getTransformedDataAsAlternate() {
        return this.transformedDataFft;
    }

    public double[][] powerSpectrum() {
        this.checkSegmentDetails();
        this.psdNumberOfPoints = this.segmentLength / 2;
        this.powerSpectrumEstimate = new double[2][this.psdNumberOfPoints];
        if (!this.overlap && this.segmentNumber < 2) {
            byte var1 = 1;
            if (!this.fftDataSet) {
                throw new IllegalArgumentException("No data has been entered for the Fast Fourier Transform");
            }

            if (!checkPowerOfTwo(this.fftDataLength)) {
                throw new IllegalArgumentException("Fast Fourier Transform data length ," + this.fftDataLength + ", is not an integer power of two");
            }

            double[] var2 = new double[this.fftDataLength * 2];

            int var3;
            for(var3 = 0; var3 < this.fftDataLength * 2; ++var3) {
                var2[var3] = this.fftDataWindow[var3];
            }

            this.basicFft(var2, this.fftDataLength, var1);

            for(var3 = 0; var3 < this.fftDataLength * 2; ++var3) {
                this.transformedDataFft[var3] = var2[var3];
            }

            for(var3 = 0; var3 < this.fftDataLength; ++var3) {
                this.transformedDataComplex[var3].reset(this.transformedDataFft[2 * var3], this.transformedDataFft[2 * var3 + 1]);
            }

            this.powerSpectrumEstimate[1][0] = Fmath.square(var2[0]) + Fmath.square(var2[1]);

            for(var3 = 1; var3 < this.psdNumberOfPoints; ++var3) {
                this.powerSpectrumEstimate[1][var3] = Fmath.square(var2[2 * var3]) + Fmath.square(var2[2 * var3 + 1]) + Fmath.square(var2[2 * this.segmentLength - 2 * var3]) + Fmath.square(var2[2 * this.segmentLength - 2 * var3 + 1]);
            }

            for(var3 = 0; var3 < this.psdNumberOfPoints; ++var3) {
                this.powerSpectrumEstimate[1][var3] = 2.0D * this.powerSpectrumEstimate[1][var3] / ((double)this.fftDataLength * this.sumOfSquaredWeights);
            }

            for(var3 = 0; var3 < this.psdNumberOfPoints; ++var3) {
                this.powerSpectrumEstimate[0][var3] = (double)var3 / ((double)this.segmentLength * this.deltaT);
            }
        } else {
            this.powerSpectrumEstimate = this.powerSpectrumSeg();
        }

        this.powSpecDone = true;
        return this.powerSpectrumEstimate;
    }

    public double[][] powerSpectrum(String var1) {
        if (!checkPowerOfTwo(this.segmentLength)) {
            throw new IllegalArgumentException("Fast Fourier Transform segment length ," + this.segmentLength + ", is not an integer power of two");
        } else {
            FileInput var2 = new FileInput(var1);
            this.psdNumberOfPoints = this.segmentLength / 2;
            this.powerSpectrumEstimate = new double[2][this.psdNumberOfPoints];
            this.fftDataLength = calcDataLength(this.overlap, this.segmentLength, this.segmentNumber);
            if (!this.overlap && this.segmentNumber < 2) {
                this.fftData = new double[2 * this.fftDataLength];
                int var3 = -1;

                int var4;
                for(var4 = 0; var4 < this.segmentLength; ++var4) {
                    ++var3;
                    this.fftData[var3] = var2.readDouble();
                    ++var3;
                    this.fftData[var3] = var2.readDouble();
                }

                this.complexData = Complex.oneDarray(this.fftDataLength);
                var3 = -1;

                for(var4 = 0; var4 < this.fftDataLength; ++var4) {
                    Complex var10000 = this.complexData[var4];
                    ++var3;
                    var10000.setReal(this.fftData[var3]);
                    var10000 = this.complexData[var4];
                    ++var3;
                    var10000.setImag(this.fftData[var3]);
                }

                this.fftDataWindow = new double[2 * this.fftDataLength];
                this.sumOfSquaredWeights = this.windowData(this.fftData, this.fftDataWindow, this.weights);
                byte var7 = 1;
                double[] var5 = new double[this.fftDataLength * 2];

                int var6;
                for(var6 = 0; var6 < this.fftDataLength * 2; ++var6) {
                    var5[var6] = this.fftDataWindow[var6];
                }

                this.basicFft(var5, this.fftDataLength, var7);

                for(var6 = 0; var6 < this.fftDataLength * 2; ++var6) {
                    this.transformedDataFft[var6] = var5[var6];
                }

                for(var6 = 0; var6 < this.fftDataLength; ++var6) {
                    this.transformedDataComplex[var6].reset(this.transformedDataFft[2 * var6], this.transformedDataFft[2 * var6 + 1]);
                }

                this.powerSpectrumEstimate[1][0] = Fmath.square(var5[0]) + Fmath.square(var5[1]);

                for(var6 = 1; var6 < this.psdNumberOfPoints; ++var6) {
                    this.powerSpectrumEstimate[1][var6] = Fmath.square(var5[2 * var6]) + Fmath.square(var5[2 * var6 + 1]) + Fmath.square(var5[2 * this.segmentLength - 2 * var6]) + Fmath.square(var5[2 * this.segmentLength - 2 * var6 + 1]);
                }

                for(var6 = 0; var6 < this.psdNumberOfPoints; ++var6) {
                    this.powerSpectrumEstimate[1][var6] = 2.0D * this.powerSpectrumEstimate[1][var6] / ((double)this.fftDataLength * this.sumOfSquaredWeights);
                }

                for(var6 = 0; var6 < this.psdNumberOfPoints; ++var6) {
                    this.powerSpectrumEstimate[0][var6] = (double)var6 / ((double)this.segmentLength * this.deltaT);
                }
            } else {
                this.powerSpectrumEstimate = this.powerSpectrumSeg(var2);
            }

            this.powSpecDone = true;
            return this.powerSpectrumEstimate;
        }
    }

    private double[][] powerSpectrumSeg() {
        int var1 = 0;
        int var2 = this.segmentLength;
        if (this.overlap) {
            var2 /= 2;
        }

        double[] var3 = new double[2 * this.segmentLength];
        this.psdNumberOfPoints = this.segmentLength / 2;
        double[] var4 = new double[this.psdNumberOfPoints];
        double[][] var5 = new double[2][this.psdNumberOfPoints];

        for(int var6 = 0; var6 < this.psdNumberOfPoints; ++var6) {
            var5[1][var6] = 0.0D;
        }

        byte var10 = 1;

        int var7;
        for(var7 = 1; var7 <= this.segmentNumber; ++var7) {
            int var8;
            for(var8 = 0; var8 < 2 * this.segmentLength; ++var8) {
                var3[var8] = this.fftData[var1 + var8];
            }

            if (var7 == 1) {
                this.sumOfSquaredWeights = this.windowData(var3, var3, this.weights);
            } else {
                var8 = 0;

                for(int var9 = 0; var9 < this.segmentLength; ++var9) {
                    var3[var8] *= this.weights[var9];
                    ++var8;
                    var3[var8] *= this.weights[var9];
                    ++var8;
                }
            }

            this.basicFft(var3, this.segmentLength, var10);
            var4[0] = Fmath.square(var3[0]) + Fmath.square(var3[1]);

            for(var8 = 1; var8 < this.psdNumberOfPoints; ++var8) {
                var4[var8] = Fmath.square(var3[2 * var8]) + Fmath.square(var3[2 * var8 + 1]) + Fmath.square(var3[2 * this.segmentLength - 2 * var8]) + Fmath.square(var3[2 * this.segmentLength - 2 * var8 + 1]);
            }

            for(var8 = 0; var8 < this.psdNumberOfPoints; ++var8) {
                var4[var8] = 2.0D * var4[var8] / ((double)this.segmentLength * this.sumOfSquaredWeights);
            }

            for(var8 = 0; var8 < this.psdNumberOfPoints; ++var8) {
                var5[1][var8] += var4[var8];
            }

            var1 += var2;
        }

        for(var7 = 0; var7 < this.psdNumberOfPoints; ++var7) {
            var5[1][var7] /= (double)this.segmentNumber;
        }

        for(var7 = 0; var7 < this.psdNumberOfPoints; ++var7) {
            var5[0][var7] = (double)var7 / ((double)this.segmentLength * this.deltaT);
        }

        return var5;
    }

    private double[][] powerSpectrumSeg(FileInput var1) {
        double[] var2 = new double[2 * this.segmentLength];
        this.weights = new double[this.segmentLength];
        double[] var3 = new double[2 * this.segmentLength];
        this.psdNumberOfPoints = this.segmentLength / 2;
        double[] var4 = new double[this.psdNumberOfPoints];
        double[][] var5 = new double[2][this.psdNumberOfPoints];

        for(int var6 = 0; var6 < this.psdNumberOfPoints; ++var6) {
            var5[1][var6] = 0.0D;
        }

        byte var10 = 1;
        this.sumOfSquaredWeights = this.windowData(var3, var3, this.weights);
        int var7;
        int var8;
        int var9;
        if (this.overlap) {
            for(var7 = 0; var7 < this.segmentLength; ++var7) {
                var2[var7] = var1.readDouble();
            }

            for(var7 = 1; var7 <= this.segmentNumber; ++var7) {
                for(var8 = 0; var8 < this.segmentLength; ++var8) {
                    var2[var8 + this.segmentLength] = var1.readDouble();
                }

                var8 = -1;

                for(var9 = 0; var9 < this.segmentLength; ++var9) {
                    ++var8;
                    var2[var8] *= this.weights[var9];
                    ++var8;
                    var2[var8] *= this.weights[var9];
                }

                this.basicFft(var2, this.segmentLength, var10);
                var4[0] = Fmath.square(var2[0]) + Fmath.square(var2[1]);

                for(var9 = 1; var9 < this.psdNumberOfPoints; ++var9) {
                    var4[var9] = Fmath.square(var2[2 * var9]) + Fmath.square(var2[2 * var9 + 1]) + Fmath.square(var2[2 * this.segmentLength - 2 * var9]) + Fmath.square(var2[2 * this.segmentLength - 2 * var9 + 1]);
                }

                for(var9 = 0; var9 < this.psdNumberOfPoints; ++var9) {
                    var4[var9] = 2.0D * var4[var9] / ((double)this.segmentLength * this.sumOfSquaredWeights);
                }

                for(var9 = 0; var9 < this.psdNumberOfPoints; ++var9) {
                    var5[1][var9] += var4[var9];
                }

                for(var9 = 0; var9 < this.segmentLength; ++var9) {
                    var2[var9] = var2[var9 + this.segmentLength];
                }
            }
        } else {
            for(var7 = 1; var7 <= this.segmentNumber; ++var7) {
                for(var8 = 0; var8 < 2 * this.segmentLength; ++var8) {
                    var2[var8] = var1.readDouble();
                }

                var8 = -1;

                for(var9 = 0; var9 < this.segmentLength; ++var9) {
                    ++var8;
                    var2[var8] *= this.weights[var9];
                    ++var8;
                    var2[var8] *= this.weights[var9];
                }

                this.basicFft(var2, this.segmentLength, var10);
                var4[0] = Fmath.square(var2[0]) + Fmath.square(var2[1]);

                for(var9 = 1; var9 < this.psdNumberOfPoints; ++var9) {
                    var4[var9] = Fmath.square(var2[2 * var9]) + Fmath.square(var2[2 * var9 + 1]) + Fmath.square(var2[2 * this.segmentLength - 2 * var9]) + Fmath.square(var2[2 * this.segmentLength - 2 * var9 + 1]);
                }

                for(var9 = 1; var9 < this.psdNumberOfPoints; ++var9) {
                    var4[var9] = 2.0D * var4[var9] / ((double)this.segmentLength * this.sumOfSquaredWeights);
                }

                for(var9 = 0; var9 < this.psdNumberOfPoints; ++var9) {
                    var5[1][var9] += var4[var9];
                }
            }
        }

        for(var7 = 0; var7 < this.psdNumberOfPoints; ++var7) {
            var5[1][var7] /= (double)this.segmentNumber;
        }

        for(var7 = 0; var7 < this.psdNumberOfPoints; ++var7) {
            var5[0][var7] = (double)var7 / ((double)this.segmentLength * this.deltaT);
        }

        return var5;
    }

    public double[][] getpowerSpectrumEstimate() {
        if (!this.powSpecDone) {
            System.out.println("getpowerSpectrumEstimate - powerSpectrum has not been called - null returned");
        }

        return this.powerSpectrumEstimate;
    }

    public int getNumberOfPsdPoints() {
        return this.psdNumberOfPoints;
    }

    public void printPowerSpectrum() {
        String var1 = "FourierTransformPSD.txt";
        this.printPowerSpectrum(var1);
    }

    public void printPowerSpectrum(String var1) {
        if (!this.powSpecDone) {
            this.powerSpectrum();
        }

        FileOutput var2 = new FileOutput(var1);
        var2.println("Power Spectrum Density Estimate Output File from FourierTransform");
        var2.dateAndTimeln(var1);
        String var3 = "Window: " + this.windowNames[this.windowOption];
        if (this.windowOption == 6) {
            var3 = var3 + ", alpha = " + this.kaiserAlpha;
        }

        if (this.windowOption == 7) {
            var3 = var3 + ", alpha = " + this.gaussianAlpha;
        }

        var2.println(var3);
        var2.printtab("Number of segments = ");
        var2.println(this.segmentNumber);
        var2.printtab("Segment length = ");
        var2.println(this.segmentLength);
        if (this.segmentNumber > 1) {
            if (this.overlap) {
                var2.printtab("Segments overlap by 50%");
            } else {
                var2.printtab("Segments do not overlap");
            }
        }

        var2.println();
        this.printWarnings(var2);
        var2.printtab("Frequency");
        var2.println("Mean Square");
        var2.printtab("(cycles per");
        var2.println("Amplitude");
        if (this.deltaTset) {
            var2.printtab("unit time)");
        } else {
            var2.printtab("gridpoint)");
        }

        var2.println(" ");
        int var4 = this.powerSpectrumEstimate[0].length;

        for(int var5 = 0; var5 < var4; ++var5) {
            var2.printtab(Fmath.truncate(this.powerSpectrumEstimate[0][var5], 4));
            var2.println(Fmath.truncate(this.powerSpectrumEstimate[1][var5], 4));
        }

        var2.close();
    }

    public void plotPowerSpectrum(int var1) {
        String var2 = "Estimation of Power Spectrum Density";
        this.plotPowerSpectrum(var1, this.powerSpectrumEstimate[0].length - 1, var2);
    }

    public void plotPowerSpectrum(int var1, String var2) {
        this.plotPowerSpectrum(var1, this.powerSpectrumEstimate[0].length - 1, var2);
    }

    public void plotPowerSpectrum(int var1, int var2) {
        String var3 = "Estimation of Power Spectrum Density";
        this.plotPowerSpectrum(var1, var2, var3);
    }

    public void plotPowerSpectrum(int var1, int var2, String var3) {
        if (!this.powSpecDone) {
            System.out.println("plotPowerSpectrum - powerSpectrum has not been called - no plot displayed");
        } else {
            int var4 = this.powerSpectrumEstimate[0].length - 1;
            if (var1 < 0 || var1 >= var4) {
                var1 = 0;
            }

            if (var2 < 0 || var2 > var4) {
                var2 = var4;
            }

            this.plotPowerSpectrumLinear(var1, var2, var3);
        }

    }

    public void plotPowerSpectrum(double var1) {
        String var3 = "Estimation of Power Spectrum Density";
        this.plotPowerSpectrum(var1, var3);
    }

    public void plotPowerSpectrum(double var1, String var3) {
        if (!this.powSpecDone) {
            this.powerSpectrum();
        }

        double var4 = this.powerSpectrumEstimate[1][this.powerSpectrumEstimate[0].length - 1];
        this.plotPowerSpectrum(var1, var4, var3);
    }

    public void plotPowerSpectrum(double var1, double var3) {
        if (!this.powSpecDone) {
            System.out.println("plotPowerSpectrum - powerSpectrum has not been called - no plot displayed");
        } else {
            String var5 = "Estimation of Power Spectrum Density";
            this.plotPowerSpectrum(var1, var3, var5);
        }

    }

    public void plotPowerSpectrum(double var1, double var3, String var5) {
        if (!this.powSpecDone) {
            System.out.println("plotPowerSpectrum - powerSpectrum has not been called - no plot displayed");
        } else {
            int var6 = 0;
            int var7 = 0;
            if (!this.deltaTset) {
                System.out.println("plotPowerSpectrum - deltaT has not been set");
                System.out.println("full spectrum plotted");
            } else {
                int var8 = 0;
                int var9 = this.powerSpectrumEstimate[0].length - 1;
                boolean var10 = true;
                if (var1 == -1.0D) {
                    var6 = 1;
                } else {
                    while(var10) {
                        if (this.powerSpectrumEstimate[0][var8] > var1) {
                            var6 = var8 - 1;
                            if (var6 < 0) {
                                var6 = 0;
                            }

                            var10 = false;
                        } else {
                            ++var8;
                            if (var8 >= var9) {
                                var6 = 0;
                                System.out.println("plotPowerSpectrum - lowFreq out of range -  reset to zero");
                                var10 = false;
                            }
                        }
                    }
                }

                var10 = true;
                var8 = 0;

                while(var10) {
                    if (this.powerSpectrumEstimate[0][var8] > var3) {
                        var7 = var8 - 1;
                        if (var7 < 0) {
                            System.out.println("plotPowerSpectrum - highFreq out of range -  reset to highest value");
                            var7 = var9;
                        }

                        var10 = false;
                    } else {
                        ++var8;
                        if (var8 >= var9) {
                            var7 = var9;
                            System.out.println("plotPowerSpectrum - highFreq out of range -  reset to highest value");
                            var10 = false;
                        }
                    }
                }

                this.plotPowerSpectrumLinear(var6, var7, var5);
            }
        }

    }

    public void plotPowerSpectrum() {
        if (!this.powSpecDone) {
            this.powerSpectrum();
        }

        String var1 = "Estimation of Power Spectrum Density";
        this.plotPowerSpectrumLinear(0, this.powerSpectrumEstimate[0].length - 1, var1);
    }

    public void plotPowerSpectrum(String var1) {
        if (!this.powSpecDone) {
            this.powerSpectrum();
        }

        this.plotPowerSpectrumLinear(0, this.powerSpectrumEstimate[0].length - 1, var1);
    }

    private void plotPowerSpectrumLinear(int var1, int var2, String var3) {
        int var4 = this.powerSpectrumEstimate[0].length;
        int var5 = var2 - var1 + 1;
        double[][] var6 = new double[2][var5];

        for(int var7 = 0; var7 < var5; ++var7) {
            var6[0][var7] = this.powerSpectrumEstimate[0][var7 + var1];
            var6[1][var7] = this.powerSpectrumEstimate[1][var7 + var1];
        }

        String var8 = "Mean Square Amplitude";
        this.plotPowerDisplay(var6, var1, var2, var3, var8);
    }

    public void plotPowerLog(int var1) {
        String var2 = "Estimation of Power Spectrum Density";
        this.plotPowerLog(var1, this.powerSpectrumEstimate[0].length - 1, var2);
    }

    public void plotPowerLog(int var1, String var2) {
        this.plotPowerLog(var1, this.powerSpectrumEstimate[0].length - 1, var2);
    }

    public void plotPowerLog(int var1, int var2) {
        String var3 = "Estimation of Power Spectrum Density";
        this.plotPowerLog(var1, var2, var3);
    }

    public void plotPowerLog(int var1, int var2, String var3) {
        if (!this.powSpecDone) {
            this.powerSpectrum();
        }

        int var4 = this.powerSpectrumEstimate[0].length - 1;
        if (var1 < 0 || var1 >= var4) {
            var1 = 0;
        }

        if (var2 < 0 || var2 > var4) {
            var2 = var4;
        }

        this.plotPowerSpectrumLog(var1, var2, var3);
    }

    public void plotPowerLog(double var1) {
        String var3 = "Estimation of Power Spectrum Density";
        this.plotPowerLog(var1, var3);
    }

    public void plotPowerLog(double var1, String var3) {
        if (!this.powSpecDone) {
            this.powerSpectrum();
        }

        double var4 = this.powerSpectrumEstimate[1][this.powerSpectrumEstimate[0].length - 1];
        this.plotPowerLog(var1, var4, var3);
    }

    public void plotPowerLog(double var1, double var3) {
        if (!this.powSpecDone) {
            this.powerSpectrum();
        }

        String var5 = "Estimation of Power Spectrum Density";
        this.plotPowerLog(var1, var3, var5);
    }

    public void plotPowerLog(double var1, double var3, String var5) {
        if (!this.powSpecDone) {
            this.powerSpectrum();
        }

        int var6 = 0;
        int var7 = 0;
        if (!this.deltaTset) {
            System.out.println("plotPowerLog - deltaT has not been set");
            System.out.println("full spectrum plotted");
        } else {
            int var8 = 0;
            int var9 = this.powerSpectrumEstimate[0].length - 1;
            boolean var10 = true;
            if (var1 == -1.0D) {
                var6 = 1;
            } else {
                while(var10) {
                    if (this.powerSpectrumEstimate[0][var8] > var1) {
                        var6 = var8 - 1;
                        if (var6 < 0) {
                            var6 = 0;
                        }

                        var10 = false;
                    } else {
                        ++var8;
                        if (var8 >= var9) {
                            var6 = 0;
                            System.out.println("plotPowerLog - lowFreq out of range -  reset to zero");
                            var10 = false;
                        }
                    }
                }
            }

            var10 = true;
            var8 = 0;

            while(var10) {
                if (this.powerSpectrumEstimate[0][var8] > var3) {
                    var7 = var8 - 1;
                    if (var7 < 0) {
                        System.out.println("plotPowerLog - highFreq out of range -  reset to highest value");
                        var7 = var9;
                    }

                    var10 = false;
                } else {
                    ++var8;
                    if (var8 >= var9) {
                        var7 = var9;
                        System.out.println("plotPowerSpectrum - highFreq out of range -  reset to highest value");
                        var10 = false;
                    }
                }
            }

            this.plotPowerSpectrumLog(var6, var7, var5);
        }

    }

    public void plotPowerLog() {
        if (!this.powSpecDone) {
            this.powerSpectrum();
        }

        String var1 = "Estimation of Power Spectrum Density";
        this.plotPowerSpectrumLog(0, this.powerSpectrumEstimate[0].length - 1, var1);
    }

    public void plotPowerLog(String var1) {
        if (!this.powSpecDone) {
            this.powerSpectrum();
        }

        this.plotPowerSpectrumLog(0, this.powerSpectrumEstimate[0].length - 1, var1);
    }

    private void plotPowerSpectrumLog(int var1, int var2, String var3) {
        int var4 = this.powerSpectrumEstimate[0].length;
        int var5 = var2 - var1 + 1;
        double[][] var6 = new double[2][var5];

        for(int var7 = 0; var7 < var5; ++var7) {
            var6[0][var7] = this.powerSpectrumEstimate[0][var7 + var1];
            var6[1][var7] = this.powerSpectrumEstimate[1][var7 + var1];
        }

        boolean var12 = true;
        int var8 = 0;
        double var9 = 0.0D;

        while(var12) {
            if (var6[1][var8] > 0.0D) {
                var9 = var6[1][var8];
                var12 = false;
            } else {
                ++var8;
                if (var8 >= var5) {
                    var12 = false;
                    System.out.println("plotPowerSpectrumLog:  no non-zero amplitudes");
                    System.exit(0);
                }
            }
        }

        int var11;
        for(var11 = var8 + 1; var11 < var5; ++var11) {
            if (var6[1][var11] < var9) {
                var9 = var6[1][var11];
            }
        }

        for(var11 = 0; var11 < var5; ++var11) {
            if (var6[1][var11] <= 0.0D) {
                var6[1][var11] = var9;
            }
        }

        for(var11 = 0; var11 < var5; ++var11) {
            var6[1][var11] = Fmath.log10(var6[1][var11]);
        }

        String var13 = "Log10(Mean Square Amplitude)";
        this.plotPowerDisplay(var6, var1, var2, var3, var13);
    }

    private void plotPowerDisplay(double[][] var1, int var2, int var3, String var4, String var5) {
        PlotGraph var6 = new PlotGraph(var1);
        var4 = var4 + "  [plot between points " + var2 + " and " + var3 + "]";
        var6.setGraphTitle(var4);
        String var7 = "Window: " + this.windowNames[this.windowOption];
        if (this.windowOption == 6) {
            var7 = var7 + " - alpha = " + this.kaiserAlpha;
        }

        if (this.windowOption == 7) {
            var7 = var7 + " - alpha = " + this.gaussianAlpha;
        }

        var7 = var7 + ", " + this.segmentNumber + " segment/s of length " + this.segmentLength;
        if (this.segmentNumber > 1) {
            if (this.overlap) {
                var7 = var7 + ", segments overlap by 50%";
            } else {
                var7 = var7 + ", segments do not overlap";
            }
        }

        var6.setGraphTitle2(var7);
        var6.setXaxisLegend("Frequency");
        if (this.deltaTset) {
            var6.setXaxisUnitsName("cycles per unit time");
        } else {
            var6.setXaxisUnitsName("cycles per grid point");
        }

        var6.setYaxisLegend(var5);
        switch(this.plotLineOption) {
            case 0:
                var6.setLine(3);
                break;
            case 1:
                var6.setLine(1);
                break;
            case 2:
                var6.setLine(2);
                break;
            default:
                var6.setLine(3);
        }

        switch(this.plotPointOption) {
            case 0:
                var6.setPoint(0);
                break;
            case 1:
                var6.setPoint(4);
                break;
            default:
                var6.setPoint(0);
        }

        var6.setFrame();
    }

    public void setPlotLineOption(int var1) {
        this.plotLineOption = var1;
    }

    public int getPlotLineOption() {
        return this.plotLineOption;
    }

    public void setPlotPointOption(int var1) {
        this.plotPointOption = var1;
    }

    public int getPlotPointOption() {
        return this.plotPointOption;
    }

    public double[][] correlate(double[] var1) {
        int var2 = var1.length;
        if (!this.fftDataSet) {
            throw new IllegalArgumentException("No data has been previously entered");
        } else if (var2 != this.originalDataLength) {
            throw new IllegalArgumentException("The two data sets to be correlated are of different length");
        } else if (!checkPowerOfTwo(var2)) {
            throw new IllegalArgumentException("The length of the correlation data sets is not equal to an integer power of two");
        } else {
            this.complexCorr = Complex.oneDarray(var2);

            int var3;
            for(var3 = 0; var3 < var2; ++var3) {
                this.complexCorr[var3].setReal(var1[var3]);
                this.complexCorr[var3].setImag(0.0D);
            }

            this.fftCorr = new double[2 * var2];
            var3 = -1;

            for(int var4 = 0; var4 < var2; ++var4) {
                ++var3;
                this.fftCorr[var3] = var1[var4];
                ++var3;
                this.fftCorr[var3] = 0.0D;
            }

            return this.correlation(var2);
        }
    }

    public double[][] correlate(double[] var1, double[] var2) {
        int var3 = var1.length;
        int var4 = var2.length;
        if (var3 != var4) {
            throw new IllegalArgumentException("The two data sets to be correlated are of different length");
        } else if (!checkPowerOfTwo(var3)) {
            throw new IllegalArgumentException("The length of the correlation data sets is not equal to an integer power of two");
        } else {
            this.fftDataLength = var3;
            this.complexData = Complex.oneDarray(this.fftDataLength);

            int var5;
            for(var5 = 0; var5 < this.fftDataLength; ++var5) {
                this.complexData[var5].setReal(var1[var5]);
                this.complexData[var5].setImag(0.0D);
            }

            this.fftData = new double[2 * this.fftDataLength];
            var5 = 0;

            int var6;
            for(var6 = 0; var6 < this.fftDataLength; ++var6) {
                this.fftData[var5] = var1[var6];
                ++var5;
                this.fftData[var5] = 0.0D;
                ++var5;
            }

            this.fftDataSet = true;
            this.fftDataWindow = new double[2 * this.fftDataLength];
            this.weights = new double[this.fftDataLength];
            this.sumOfSquaredWeights = this.windowData(this.fftData, this.fftDataWindow, this.weights);
            this.transformedDataFft = new double[2 * this.fftDataLength];
            this.transformedDataComplex = Complex.oneDarray(this.fftDataLength);
            this.complexCorr = Complex.oneDarray(var3);

            for(var6 = 0; var6 < var3; ++var6) {
                this.complexCorr[var6].setReal(var2[var6]);
                this.complexCorr[var6].setImag(0.0D);
            }

            this.fftCorr = new double[2 * var3];
            var5 = -1;

            for(var6 = 0; var6 < var3; ++var6) {
                ++var5;
                this.fftCorr[var5] = var2[var6];
                ++var5;
                this.fftCorr[var5] = 0.0D;
            }

            return this.correlation(var3);
        }
    }

    private double[][] correlation(int var1) {
        this.fftDataWindow = new double[2 * var1];
        this.fftCorrWindow = new double[2 * var1];
        this.weights = new double[var1];
        this.sumOfSquaredWeights = this.windowData(this.fftData, this.fftDataWindow, this.weights);
        this.windowData(this.fftCorr, this.fftCorrWindow, this.weights);
        byte var2 = 1;
        double[] var3 = new double[2 * var1];

        for(int var4 = 0; var4 < var1 * 2; ++var4) {
            var3[var4] = this.fftDataWindow[var4];
        }

        this.basicFft(var3, var1, var2);
        var2 = 1;
        double[] var10 = new double[2 * var1];

        for(int var5 = 0; var5 < var1 * 2; ++var5) {
            var10[var5] = this.fftCorrWindow[var5];
        }

        this.basicFft(var10, var1, var2);
        double[] var11 = new double[2 * var1];
        int var6 = 0;

        int var7;
        for(var7 = 0; var7 < var1; ++var7) {
            var11[var6] = (var3[var6] * var10[var6] + var3[var6 + 1] * var10[var6 + 1]) / (double)var1;
            var11[var6 + 1] = (-var3[var6] * var10[var6 + 1] + var3[var6 + 1] * var10[var6]) / (double)var1;
            var6 += 2;
        }

        byte var9 = -1;
        this.basicFft(var11, var1, var9);

        for(var7 = 0; var7 < 2 * var1; ++var7) {
            this.transformedDataFft[var7] = var11[var7];
        }

        this.correlationArray = new double[2][var1];
        var6 = 0;
        var7 = var1;

        int var8;
        for(var8 = var1 / 2 + 1; var8 < var1; ++var8) {
            this.correlationArray[1][var6] = var11[var7] / (double)var1;
            ++var6;
            var7 += 2;
        }

        var7 = 0;

        for(var8 = 0; var8 < var1 / 2; ++var8) {
            this.correlationArray[1][var6] = var11[var7] / (double)var1;
            ++var6;
            var7 += 2;
        }

        this.correlationArray[0][0] = -((double)(var1 / 2)) * this.deltaT;

        for(var8 = 1; var8 < var1; ++var8) {
            this.correlationArray[0][var8] = this.correlationArray[0][var8 - 1] + this.deltaT;
        }

        this.correlateDone = true;
        return this.correlationArray;
    }

    public double[][] getCorrelation() {
        if (!this.correlateDone) {
            System.out.println("getCorrelation - correlation has not been called - no correlation returned");
        }

        return this.correlationArray;
    }

    public void printCorrelation() {
        String var1 = "Correlation.txt";
        this.printCorrelation(var1);
    }

    public void printCorrelation(String var1) {
        if (!this.correlateDone) {
            System.out.println("printCorrelation - correlate has not been called - no file printed");
        } else {
            FileOutput var2 = new FileOutput(var1);
            var2.println("Correlation Output File from FourierTransform");
            var2.dateAndTimeln(var1);
            String var3 = "Window: " + this.windowNames[this.windowOption];
            if (this.windowOption == 6) {
                var3 = var3 + ", alpha = " + this.kaiserAlpha;
            }

            if (this.windowOption == 7) {
                var3 = var3 + ", alpha = " + this.gaussianAlpha;
            }

            var2.println(var3);
            var2.printtab("Data length = ");
            var2.println(this.fftDataLength);
            var2.println();
            var2.printtab("Time lag");
            var2.println("Correlation");
            if (this.deltaTset) {
                var2.printtab("/unit time");
            } else {
                var2.printtab("/grid interval)");
            }

            var2.println("Coefficient");
            int var4 = this.correlationArray[0].length;

            for(int var5 = 0; var5 < var4; ++var5) {
                var2.printtab(Fmath.truncate(this.correlationArray[0][var5], 4));
                var2.println(Fmath.truncate(this.correlationArray[1][var5], 4));
            }

            var2.close();
        }

    }

    public void plotCorrelation() {
        if (!this.correlateDone) {
            System.out.println("plotCorrelation - correlation has not been called - no plot displayed");
        } else {
            String var1 = "Correlation Plot";
            this.plotCorrelation(var1);
        }

    }

    public void plotCorrelation(String var1) {
        if (!this.correlateDone) {
            System.out.println("plotCorrelation - correlate has not been called - no plot displayed");
        } else {
            PlotGraph var2 = new PlotGraph(this.correlationArray);
            var2.setGraphTitle(var1);
            String var3 = "Window: " + this.windowNames[this.windowOption];
            if (this.windowOption == 6) {
                var3 = var3 + " - alpha = " + this.kaiserAlpha;
            }

            if (this.windowOption == 7) {
                var3 = var3 + " - alpha = " + this.gaussianAlpha;
            }

            var2.setGraphTitle2(var3);
            var2.setXaxisLegend("Correlation Lag");
            if (this.deltaTset) {
                var2.setXaxisUnitsName("unit time");
            } else {
                var2.setXaxisUnitsName("grid interval");
            }

            var2.setYaxisLegend("Correlation coefficient");
            switch(this.plotLineOption) {
                case 0:
                    var2.setLine(3);
                    break;
                case 1:
                    var2.setLine(1);
                    break;
                case 2:
                    var2.setLine(2);
                    break;
                default:
                    var2.setLine(3);
            }

            switch(this.plotPointOption) {
                case 0:
                    var2.setPoint(0);
                    break;
                case 1:
                    var2.setPoint(4);
                    break;
                default:
                    var2.setPoint(0);
            }

            var2.setFrame();
        }

    }

    public double[][] shortTime(double var1) {
        int var3 = (int)Math.round(var1 / this.deltaT);
        if (!checkPowerOfTwo(var3)) {
            int var4 = lastPowerOfTwo(var3);
            int var5 = nextPowerOfTwo(var3);
            if (var3 - var4 <= var5 - var3) {
                var3 = var4;
                if (var4 == 0) {
                    var3 = var5;
                }
            } else {
                var3 = var5;
            }

            System.out.println("Method - shortTime");
            System.out.println("Window length, provided as time, " + var1 + ", did not convert to an integer power of two data points");
            System.out.println("A value of " + (double)(var3 - 1) * this.deltaT + " was substituted");
        }

        return this.shortTime(var3);
    }

    public double[][] shortTime(int var1) {
        if (!checkPowerOfTwo(var1)) {
            throw new IllegalArgumentException("Moving window data length ," + var1 + ", is not an integer power of two");
        } else if (!this.fftDataSet) {
            throw new IllegalArgumentException("No data has been entered for the Fast Fourier Transform");
        } else if (var1 > this.originalDataLength) {
            throw new IllegalArgumentException("The window length, " + var1 + ", is greater than the data length, " + this.originalDataLength + ".");
        } else {
            if (this.windowOption == 0) {
                this.setGaussian();
            }

            this.numShortTimes = this.originalDataLength - var1 + 1;
            this.numShortFreq = var1 / 2;
            this.timeFrequency = new double[this.numShortFreq + 1][this.numShortTimes + 1];
            this.timeFrequency[0][0] = 0.0D;
            this.timeFrequency[0][1] = (double)(var1 - 1) * this.deltaT / 2.0D;

            int var2;
            for(var2 = 2; var2 <= this.numShortTimes; ++var2) {
                this.timeFrequency[0][var2] = this.timeFrequency[0][var2 - 1] + this.deltaT;
            }

            for(var2 = 0; var2 < this.numShortFreq; ++var2) {
                this.timeFrequency[var2 + 1][0] = (double)var2 / ((double)var1 * this.deltaT);
            }

            this.segmentLength = var1;
            var2 = 0;
            double[] var3 = new double[2 * var1];
            double[] var4 = new double[this.numShortFreq];
            byte var5 = 1;

            for(int var6 = 1; var6 <= this.numShortTimes; ++var6) {
                int var7;
                for(var7 = 0; var7 < 2 * var1; ++var7) {
                    var3[var7] = this.fftData[var2 + var7];
                }

                if (var6 == 1) {
                    this.sumOfSquaredWeights = this.windowData(var3, var3, this.weights);
                } else {
                    var7 = 0;

                    for(int var8 = 0; var8 < this.segmentLength; ++var8) {
                        var3[var7] *= this.weights[var8];
                        ++var7;
                        var3[var7] *= this.weights[var8];
                        ++var7;
                    }
                }

                this.basicFft(var3, var1, var5);
                var4[0] = Fmath.square(var3[0]) + Fmath.square(var3[1]);

                for(var7 = 1; var7 < this.numShortFreq; ++var7) {
                    var4[var7] = Fmath.square(var3[2 * var7]) + Fmath.square(var3[2 * var7 + 1]) + Fmath.square(var3[2 * var1 - 2 * var7]) + Fmath.square(var3[2 * var1 - 2 * var7 + 1]);
                }

                for(var7 = 0; var7 < this.numShortFreq; ++var7) {
                    this.timeFrequency[var7 + 1][var6] = 2.0D * var4[var7] / ((double)var1 * this.sumOfSquaredWeights);
                }

                var2 += 2;
            }

            this.shortTimeDone = true;
            return this.timeFrequency;
        }
    }

    public double[][] getTimeFrequencyMatrix() {
        if (!this.shortTimeDone) {
            throw new IllegalArgumentException("No short time Fourier transform has been performed");
        } else {
            return this.timeFrequency;
        }
    }

    public int getShortTimeNumberOfTimes() {
        if (!this.shortTimeDone) {
            throw new IllegalArgumentException("No short time Fourier transform has been performed");
        } else {
            return this.numShortTimes;
        }
    }

    public int getShortTimeNumberOfFrequencies() {
        if (!this.shortTimeDone) {
            throw new IllegalArgumentException("No short time Fourier transform has been performed");
        } else {
            return this.numShortFreq;
        }
    }

    public int getShortTimeWindowLength() {
        if (!this.shortTimeDone) {
            throw new IllegalArgumentException("No short time Fourier transform has been performed");
        } else {
            return this.segmentLength;
        }
    }

    public void printShortTime() {
        String var1 = "ShortTime.txt";
        this.printShortTime(var1);
    }

    public void printShortTime(String var1) {
        if (!this.shortTimeDone) {
            System.out.println("printShortTime- shortTime has not been called - no file printed");
        } else {
            FileOutput var2 = new FileOutput(var1);
            var2.println("Short Time Fourier Transform Output File from FourierTransform");
            var2.dateAndTimeln(var1);
            String var3 = "Window: " + this.windowNames[this.windowOption];
            if (this.windowOption == 6) {
                var3 = var3 + ", alpha = " + this.kaiserAlpha;
            }

            if (this.windowOption == 7) {
                var3 = var3 + ", alpha = " + this.gaussianAlpha;
            }

            var2.println(var3);
            var2.printtab("Data length = ");
            var2.println(this.originalDataLength);
            var2.printtab("Delta T = ");
            var2.println(this.deltaT);
            var2.printtab("Window length (points) = ");
            var2.println(this.segmentLength);
            var2.printtab("Window length (time units) = ");
            var2.println((double)(this.segmentLength - 1) * this.deltaT);
            var2.printtab("Number of frequency points = ");
            var2.println(this.numShortFreq);
            var2.printtab("Number of time points = ");
            var2.println(this.numShortTimes);
            boolean var4 = false;
            int var5 = this.numShortTimes;
            byte var6 = 100;
            int var7 = this.numShortTimes / var6;
            int var8 = this.numShortTimes % var6;
            if (this.numShortTimes > 127) {
                var4 = true;
                if (var8 > 0) {
                    ++var7;
                    var5 = var6;
                    var8 = this.numShortTimes - var7 * (var6 - 1);
                } else {
                    var5 = var6;
                    var8 = var7;
                }

                if (var8 != var7) {
                    var2.println("In the output below, each of the first " + (var5 - 2) + " magnitude points, along the time axis, is the average of " + var7 + " calculated points");
                    var2.println("The last point is the average of " + var8 + " calculated points");
                } else {
                    var2.println("In the output below, each magnitude point is the average of " + var7 + " calculated points");
                }

                var2.println("The data, without averaging, may be accessed using the method getTimeFrequencyMatrix()");
            }

            var2.println();
            var2.println("first row = times");
            var2.println("first column = frequencies");
            var2.println("all other cells = mean square amplitudes at the corresponding time and frequency");
            if (var4) {
                double var16 = 0.0D;
                boolean var11 = true;

                for(int var13 = 0; var13 <= this.numShortFreq; ++var13) {
                    var2.printtab(Fmath.truncate(this.timeFrequency[var13][0], 4));
                    int var17 = 1;

                    for(int var14 = 1; var14 <= var5; ++var14) {
                        int var12 = var7;
                        if (var14 == var5) {
                            var12 = var8;
                        }

                        var16 = 0.0D;

                        for(int var15 = var17; var15 <= var17 + var12 - 1; ++var15) {
                            var16 += this.timeFrequency[var13][var15];
                        }

                        var16 /= (double)var12;
                        var2.printtab(Fmath.truncate(var16, 4));
                        var17 += var12;
                    }

                    var2.println();
                }
            } else {
                for(int var9 = 0; var9 <= this.numShortFreq; ++var9) {
                    for(int var10 = 0; var10 <= var5; ++var10) {
                        var2.printtab(Fmath.truncate(this.timeFrequency[var9][var10], 4));
                    }

                    var2.println();
                }
            }

            var2.close();
        }

    }

    public void paint(Graphics var1) {
        this.graph(var1);
    }

    public void plotShortTime(String var1) {
        this.shortTitle = var1;
        this.plotShortTime();
    }

    public void plotShortTime() {
        JFrame var1 = new JFrame("Michael T Flanagan's plotting program - FourierTransform.plotShortTime");
        this.setSize(800, 600);
        var1.getContentPane().setBackground(Color.white);
        var1.setDefaultCloseOperation(3);
        var1.getContentPane().add("Center", this);
        var1.pack();
        var1.setResizable(true);
        var1.toFront();
        var1.setVisible(true);
    }

    public void graph(Graphics var1) {
        short var2 = 512;
        short var3 = 256;
        byte var4 = 100;
        byte var5 = 100;
        byte var6 = 18;
        Color[] var7 = new Color[var6 + 1];
        var7[18] = Color.black;
        var7[17] = Color.darkGray;
        var7[16] = Color.gray;
        var7[15] = Color.lightGray;
        var7[14] = Color.red.darker();
        var7[13] = Color.red;
        var7[12] = Color.magenta.darker();
        var7[11] = Color.magenta;
        var7[10] = Color.pink;
        var7[9] = Color.pink.darker();
        var7[8] = Color.orange.darker();
        var7[7] = Color.orange;
        var7[6] = Color.yellow;
        var7[5] = Color.green;
        var7[4] = Color.green.darker();
        var7[3] = Color.cyan;
        var7[2] = Color.cyan.darker();
        var7[1] = Color.blue;
        var7[0] = Color.blue.darker();
        boolean var8 = false;
        boolean var9 = false;
        int var10 = 0;
        int var11 = 0;
        boolean var12 = true;
        int var67;
        int var69;
        int var70;
        if (this.numShortTimes <= var2) {
            var69 = var2 / this.numShortTimes;
            var67 = var69 * this.numShortTimes;
            var70 = this.numShortTimes;
        } else {
            var12 = false;
            var69 = 1;
            var70 = this.numShortTimes;
            var10 = this.numShortTimes / var2;
            var11 = this.numShortTimes % var2;
            if (var11 > 0) {
                ++var10;
                var70 = this.numShortTimes / var10 + 1;
                var11 = this.numShortTimes - var10 * (var70 - 1);
            } else {
                var70 = this.numShortTimes / var10;
                var11 = var10;
            }

            var67 = var70;
        }

        boolean var13 = false;
        boolean var14 = false;
        int var15 = 0;
        int var16 = 0;
        boolean var17 = true;
        int var68;
        int var71;
        int var72;
        if (this.numShortFreq <= var3) {
            var71 = var3 / this.numShortFreq;
            var68 = var71 * this.numShortFreq;
            var72 = this.numShortFreq;
        } else {
            var17 = false;
            var71 = 1;
            var72 = this.numShortFreq;
            var15 = this.numShortFreq / var3;
            var16 = this.numShortFreq % var3;
            if (var16 > 0) {
                ++var15;
                var72 = this.numShortFreq / var15 + 1;
                var16 = this.numShortFreq - var15 * (var72 - 1);
            } else {
                var72 = this.numShortFreq / var15;
                var16 = var15;
            }

            var68 = var72;
        }

        int var18 = var4 + var68;
        int var19 = var5 + var67;
        double[][] var20 = new double[var72][var70];
        int[][] var21 = new int[var72][var70];
        double[] var22 = new double[var70];
        int[] var23 = new int[var70];
        double[] var24 = new double[var72];
        int[] var25 = new int[var72];
        double[][] var26 = new double[this.numShortFreq][var70];
        int var27;
        int var28;
        int var31;
        int var32;
        int var33;
        double var73;
        if (var12) {
            for(var27 = 0; var27 <= this.numShortFreq; ++var27) {
                for(var28 = 1; var28 <= this.numShortTimes; ++var28) {
                    if (var27 == 0) {
                        var22[var28 - 1] = this.timeFrequency[0][var28];
                    } else {
                        var26[var27 - 1][var28 - 1] = this.timeFrequency[var27][var28];
                    }
                }
            }
        } else {
            var73 = 0.0D;
            boolean var29 = true;

            for(var31 = 0; var31 <= this.numShortFreq; ++var31) {
                int var74 = 1;

                for(var32 = 1; var32 <= var70; ++var32) {
                    int var30 = var10;
                    if (var32 == var70) {
                        var30 = var11;
                    }

                    var73 = 0.0D;

                    for(var33 = var74; var33 <= var74 + var30 - 1; ++var33) {
                        var73 += this.timeFrequency[var31][var33];
                    }

                    if (var31 == 0) {
                        var22[var32 - 1] = var73 / (double)var30;
                    } else {
                        var26[var31 - 1][var32 - 1] = var73 / (double)var30;
                    }

                    var74 += var30;
                }
            }
        }

        double var75;
        if (var17) {
            for(var27 = 0; var27 < this.numShortFreq; ++var27) {
                var24[var27] = this.timeFrequency[var27 + 1][0];

                for(var28 = 0; var28 < var70; ++var28) {
                    var20[var27][var28] = var26[var27][var28];
                }
            }
        } else {
            var73 = 0.0D;
            var75 = 0.0D;
            boolean var76 = false;

            for(var33 = 0; var33 < var70; ++var33) {
                var31 = 0;

                for(int var34 = 0; var34 < var72; ++var34) {
                    var32 = var15;
                    if (var34 == var72 - 1) {
                        var32 = var16;
                    }

                    var73 = 0.0D;
                    var75 = 0.0D;

                    for(int var35 = var31; var35 <= var31 + var32 - 1; ++var35) {
                        var73 += var26[var35][var33];
                        var75 += this.timeFrequency[var35 + 1][0];
                    }

                    var20[var34][var33] = var73;
                    var24[var34] = var75 / (double)var32;
                    var31 += var32;
                }
            }
        }

        var73 = var20[0][0];
        var75 = var73;

        for(var31 = 0; var31 < var72; ++var31) {
            for(var32 = 0; var32 < var70; ++var32) {
                if (var20[var31][var32] > var73) {
                    var73 = var20[var31][var32];
                }

                if (var20[var31][var32] < var75) {
                    var75 = var20[var31][var32];
                }
            }
        }

        double var77 = 0.0D;
        if (var75 > 0.1D * var73) {
            var77 = 0.99D * var75;
        }

        double var78 = (1.01D * var73 - 0.99D * var75) / (double)var6;
        double[] var79 = new double[var6];
        var79[0] = var77 + var78;

        for(int var36 = 1; var36 < var6; ++var36) {
            var79[var36] = var79[var36 - 1] + var78;
        }

        boolean var80 = true;

        int var37;
        int var38;
        int var39;
        for(var37 = 0; var37 < var72; ++var37) {
            for(var38 = 0; var38 < var70; ++var38) {
                var80 = true;
                var39 = 0;

                while(var80) {
                    if (var20[var37][var38] <= var79[var39]) {
                        var21[var37][var38] = var39;
                        var80 = false;
                    } else {
                        ++var39;
                    }
                }
            }
        }

        var37 = 0;
        var38 = 0;
        var39 = 0;
        int var40 = 0;

        int var41;
        int var42;
        int var43;
        int var44;
        for(var43 = 0; var43 < var72; ++var43) {
            for(var44 = 0; var44 < var70; ++var44) {
                var39 = 0;

                for(int var45 = 0; var45 < var71; ++var45) {
                    var40 = 0;

                    for(int var46 = 0; var46 < var69; ++var46) {
                        var1.setColor(var7[var21[var43][var44]]);
                        var41 = var5 + var38 + var40;
                        var42 = var18 - (var37 + var39);
                        var1.drawLine(var41, var42, var41, var42);
                        ++var40;
                    }

                    ++var39;
                }

                var38 += var40;
            }

            var37 += var39;
            var38 = 0;
        }

        var1.setColor(var7[var6]);
        var1.drawLine(var5, var18, var5, var4);
        var1.drawLine(var19, var18, var19, var4);
        var1.drawLine(var5, var18, var19, var18);
        var1.drawLine(var5, var4, var19, var4);
        var43 = var68 / 4;
        var44 = this.numShortFreq / 4;
        double var82 = (double)var43 * (var24[1] - var24[0]) / (double)(var71 * var44);
        String[] var47 = new String[5];
        int var48 = 0;
        var47[0] = "0  ";

        int var49;
        for(var49 = 1; var49 < 5; ++var49) {
            var48 += var44;
            var47[var49] = var48 + "  ";
        }

        byte var81 = var5;
        var42 = var18;
        var49 = 6 * (var47[4].length() + 1);

        int var50;
        for(var50 = 0; var50 < 5; ++var50) {
            var1.drawLine(var81 - 5, var42, var81, var42);
            var1.drawString(var47[var50], var81 - var49, var42 + 4);
            var42 -= var43;
        }

        var50 = var67 / 8;
        int var51 = this.numShortTimes / 8;
        double var52 = (double)var50 * (var22[1] - var22[0]) / (double)(var69 * var51);
        String[] var54 = new String[9];
        int var55 = 0;
        var54[0] = "0 ";

        int var56;
        for(var56 = 1; var56 < 9; ++var56) {
            var55 += var51;
            var54[var56] = var55 + " ";
        }

        var41 = var5;
        var42 = var18;

        for(var56 = 0; var56 < 9; ++var56) {
            var1.drawLine(var41, var42, var41, var42 + 5);
            var1.drawString(var54[var56], var41 - 4, var42 + 20);
            var41 += var50;
        }

        var1.drawString("Short Time Fourier Transfer Time-Frequency Plot", var5 - 80, var4 - 80);
        var1.drawString(this.shortTitle, var5 - 80, var4 - 60);
        String var83 = "Frequency / (" + Fmath.truncate(var82, 3) + " cycles per time unit)";
        var1.drawString(var83, var5 - 60, var4 - 20);
        String var57 = "Time / (" + Fmath.truncate(var52, 3) + " time units)";
        var1.drawString(var57, var5, var18 + 40);
        String var58 = "Total time = " + Fmath.truncate((double)var67 * (var22[1] - var22[0]) / (double)var69, 3) + " time units";
        var1.drawString(var58, var5, var18 + 80);
        String var59 = "Frequecy range = 0 to " + Fmath.truncate((double)var68 * (var24[1] - var24[0]) / (double)var71, 3) + " cycles per time unit";
        var1.drawString(var59, var5, var18 + 100);
        var1.drawString("Widow length = " + Fmath.truncate((double)(this.segmentLength - 1) * this.deltaT, 3) + " time units", var5, var18 + 120);
        String var60 = "Window filter = " + this.windowNames[this.windowOption];
        if (this.windowOption == 6) {
            var60 = var60 + ", alpha = " + this.kaiserAlpha;
        }

        if (this.windowOption == 7) {
            var60 = var60 + ", alpha = " + this.gaussianAlpha;
        }

        var1.drawString(var60, var5, var18 + 140);
        var42 = var18 + 100;
        var41 = var19 + 40;
        double var61 = Fmath.truncate(var77, 3);

        for(int var63 = 0; var63 < var6; ++var63) {
            double var64 = Fmath.truncate(var79[var63], 3);
            var1.setColor(var7[var6]);
            var1.drawString(var61 + " - " + var64, var41 + 25, var42);
            var61 = var64;
            var1.setColor(var7[var63]);

            for(int var66 = 0; var66 < 20; ++var66) {
                --var42;
                var1.drawLine(var41, var42, var41 + 20, var42);
            }
        }

        var1.setColor(Color.black);
        var1.drawString("Mean square", var41 + 25, var42 - 25);
        var1.drawString("amplitudes ", var41 + 25, var42 - 10);
    }

    public static int lastPowerOfTwo(int var0) {
        boolean var1 = true;

        while(var1) {
            if (checkPowerOfTwo(var0)) {
                var1 = false;
            } else {
                --var0;
            }
        }

        return var0;
    }

    public static int nextPowerOfTwo(int var0) {
        boolean var1 = true;

        while(var1) {
            if (checkPowerOfTwo(var0)) {
                var1 = false;
            } else {
                ++var0;
            }
        }

        return var0;
    }

    public static boolean checkPowerOfTwo(int var0) {
        boolean var1 = true;
        int var2 = var0;

        while(var1 && var2 > 1) {
            if (var2 % 2 != 0) {
                var1 = false;
            } else {
                var2 /= 2;
            }
        }

        return var1;
    }

    public static int checkIntegerTimesPowerOfTwo(int var0) {
        boolean var1 = true;
        boolean var2 = true;
        boolean var3 = true;
        boolean var4 = true;
        int var5 = var0;
        int var6 = 1;
        int var7 = 0;

        while(true) {
            while(var1) {
                var2 = checkPowerOfTwo(var5);
                if (var2) {
                    var4 = true;
                    var1 = false;
                } else {
                    var3 = true;

                    while(var3) {
                        ++var6;
                        var5 /= var6;
                        if (var5 < 1) {
                            var3 = false;
                            var2 = false;
                            var1 = false;
                            var4 = false;
                        } else if (var5 % 2 == 0) {
                            var3 = false;
                        }
                    }
                }
            }

            if (var4) {
                var7 = var6;
            }

            return var7;
        }
    }

    public static long getSerialVersionUID() {
        return 1L;
    }
}
