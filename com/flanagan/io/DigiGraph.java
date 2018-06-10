//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.io;

import com.flanagan.interpolation.CubicSpline;
import com.flanagan.math.Fmath;
import com.flanagan.plot.PlotGraph;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;
import java.awt.image.PixelGrabber;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import javax.swing.JFrame;

public class DigiGraph extends Canvas implements MouseListener {
    private Image pic = null;
    private String imagePath = null;
    private String imageName = null;
    private String extension = null;
    private String outputFile = null;
    private FileOutput fout = null;
    private int trunc = 16;
    private String path = "C:";
    private int windowWidth = 0;
    private int windowHeight = 0;
    private int closeChoice = 1;
    private int xPos = 0;
    private int yPos = 0;
    private int button = 0;
    private int sumX = 0;
    private int sumY = 0;
    private int iSum = 0;
    private boolean mouseEntered = false;
    private double lowYvalue = 0.0D;
    private double lowYaxisXpixel = 0.0D;
    private double lowYaxisYpixel = 0.0D;
    private double highYvalue = 0.0D;
    private double highYaxisXpixel = 0.0D;
    private double highYaxisYpixel = 0.0D;
    private double lowXvalue = 0.0D;
    private double lowXaxisXpixel = 0.0D;
    private double lowXaxisYpixel = 0.0D;
    private double highXvalue = 0.0D;
    private double highXaxisXpixel = 0.0D;
    private double highXaxisYpixel = 0.0D;
    private ArrayList<Integer> xAndYvalues = new ArrayList();
    private int iCounter = 0;
    private double angleXaxis = 0.0D;
    private double angleYaxis = 0.0D;
    private double angleMean = 0.0D;
    private double angleTolerance = 0.0D;
    private boolean rotationDone = false;
    private double[] xPosPixel = null;
    private double[] yPosPixel = null;
    private double[] xPositions = null;
    private double[] yPositions = null;
    private int nData = 0;
    private int nInterpPoints = 0;
    private boolean interpOpt = false;
    private double[] xInterp = null;
    private double[] yInterp = null;
    private boolean plotOpt = true;
    private boolean noIdentical = true;
    private int imageFormat = 0;
    private boolean digitizationDone = false;
    private boolean noYlow = true;
    private boolean noXlow = true;
    private boolean noYhigh = true;
    private boolean noXhigh = true;
    private boolean resize = false;
    private JFrame window = new JFrame("Michael T Flanagan's digitizing program - DigiGraph");

    public DigiGraph() {
        this.setWindowSize();
        this.selectImage();
        this.setImage();
        this.outputFileChoice();
        this.addMouseListener(this);
    }

    public DigiGraph(String var1) {
        this.setWindowSize();
        this.path = var1;
        this.selectImage();
        this.setImage();
        this.outputFileChoice();
        this.addMouseListener(this);
    }

    private void setWindowSize() {
        Dimension var1 = Toolkit.getDefaultToolkit().getScreenSize();
        this.windowWidth = var1.width - 30;
        this.windowHeight = var1.height - 40;
    }

    private void selectImage() {
        String var1 = null;

        try {
            InetAddress var2 = InetAddress.getLocalHost();
            var1 = var2.getHostName();
        } catch (UnknownHostException var4) {
            System.err.println("Cannot detect local host : " + var4);
        }

        if (var1.equals("name")) {
            this.path = "C:\\DigiGraphDirectory";
        }

        FileChooser var5 = new FileChooser(this.path);
        this.imageName = var5.selectFile();
        if (!var5.fileFound()) {
            System.out.println("Class DigiGraph: No successful selection of an image file occurred");
            System.exit(0);
        }

        this.imagePath = var5.getPathName();
        int var3 = this.imagePath.lastIndexOf(46);
        this.extension = this.imagePath.substring(var3 + 1);
        if (this.extension.equalsIgnoreCase("gif")) {
            this.imageFormat = 1;
        }

        if (this.extension.equalsIgnoreCase("jpg")) {
            this.imageFormat = 2;
        }

        if (this.extension.equalsIgnoreCase("jpeg")) {
            this.imageFormat = 2;
        }

        if (this.extension.equalsIgnoreCase("jpe")) {
            this.imageFormat = 2;
        }

        if (this.extension.equalsIgnoreCase("jfif")) {
            this.imageFormat = 2;
        }

        if (this.extension.equalsIgnoreCase("png")) {
            this.imageFormat = 3;
        }

    }

    private void setImage() {
        this.pic = Toolkit.getDefaultToolkit().getImage(this.imagePath);
    }

    private void outputFileChoice() {
        int var1 = this.imagePath.lastIndexOf(46);
        this.outputFile = this.imagePath.substring(0, var1) + "_digitized.txt";
        this.outputFile = Db.readLine("Enter output file name ", this.outputFile);
        this.fout = new FileOutput(this.outputFile);
        this.trunc = Db.readInt("Enter number of decimal places required in output data ", this.trunc);
    }

    public void setTruncation(int var1) {
        this.trunc = var1;
    }

    public void setRotationTolerance(double var1) {
        this.angleTolerance = var1;
    }

    public void noPlot() {
        this.plotOpt = false;
    }

    public void setPath(String var1) {
        this.path = var1;
    }

    public void setWindowHeight(int var1) {
        this.windowHeight = var1;
    }

    public void setWindowWidth(int var1) {
        this.windowWidth = var1;
    }

    public void setCloseChoice(int var1) {
        this.closeChoice = var1;
    }

    public void keepIdenticalPoints() {
        this.noIdentical = false;
    }

    public void paint(Graphics var1) {
        this.graph(var1);
    }

    public void digitize() {
        this.window.setSize(this.windowWidth, this.windowHeight);
        this.window.getContentPane().setBackground(Color.white);
        if (this.closeChoice == 1) {
            this.window.setDefaultCloseOperation(3);
        } else {
            this.window.setDefaultCloseOperation(1);
        }

        this.window.getContentPane().add("Center", this);
        this.window.pack();
        this.window.setResizable(true);
        this.window.toFront();
        this.window.setVisible(true);
    }

    public void digitise() {
        this.digitize();
    }

    private void graph(Graphics var1) {
        var1.drawImage(this.pic, 10, 30, this);
        if (!this.resize) {
            var1.drawString("RIGHT click anywhere on the screen", 5, 10);
            int var2 = this.pic.getWidth((ImageObserver)null);
            int var3 = this.pic.getHeight((ImageObserver)null);
            System.out.println(var2 + " xxx " + var3);
            var1.drawString("  ", 5, 10);
            double var4 = (double)(this.windowHeight - 30) / (double)var3;
            if ((int)((double)var2 * var4) > this.windowWidth - 10) {
                var4 = (double)(this.windowWidth - 10) / (double)var2;
            }

            var3 = (int)((double)(var3 - 30) * var4 * 0.95D);
            var2 = (int)((double)(var2 - 10) * var4 + 0.95D);
            this.pic = this.pic.getScaledInstance(var2, var3, 1);
            var1.drawImage(this.pic, 10, 30, this);
            this.resize = true;
        }

        boolean var6 = true;
        if (this.xPos == 0 && this.yPos == 0) {
            var6 = false;
        }

        if (var6) {
            this.cursorDoneSign(var1, this.xPos, this.yPos);
        }

        if (!this.digitizationDone) {
            switch(this.iCounter) {
                case 0:
                    var1.drawString("RIGHT click on lower Y-axis calibration point", 5, 10);
                    break;
                case 1:
                    if (this.noYlow) {
                        this.lowYvalue = Db.readDouble("Enter lower Y-axis calibration value");
                        this.noYlow = false;
                    }

                    var1.drawString("RIGHT click on higher Y-axis calibration point", 5, 10);
                    break;
                case 2:
                    if (this.noYhigh) {
                        this.highYvalue = Db.readDouble("Enter higher Y-axis calibration value");
                        this.noYhigh = false;
                    }

                    var1.drawString("RIGHT click on lower X-axis calibration point", 5, 10);
                    break;
                case 3:
                    if (this.noXlow) {
                        this.lowXvalue = Db.readDouble("Enter lower X-axis calibration value");
                        this.noXlow = false;
                    }

                    var1.drawString("RIGHT click on higher X-axis calibration point", 5, 10);
                    break;
                case 4:
                    if (this.noXhigh) {
                        this.highXvalue = Db.readDouble("Enter higher X-axis calibration value");
                        this.noXhigh = false;
                    }

                    var1.drawString("LEFT click on points to be digitized [right click when finished digitizing]", 5, 10);
                    break;
                default:
                    var1.drawString("LEFT click on points to be digitized [right click when finished digitizing]", 5, 10);
            }
        } else {
            var1.drawString("You may now close this window", 5, 10);
        }

    }

    private void cursorDoneSign(Graphics var1, int var2, int var3) {
        var1.drawLine(var2 - 5, var3, var2 + 5, var3);
        var1.drawLine(var2, var3 - 5, var2, var3 + 5);
        var1.fillOval(var2 - 3, var3 - 3, 7, 7);
    }

    public void mouseClicked(MouseEvent var1) {
        if (!this.digitizationDone) {
            switch(this.iCounter) {
                case 0:
                    this.xPos = var1.getX();
                    this.yPos = var1.getY();
                    this.button = var1.getButton();
                    if (this.button == 1) {
                        this.sumX += this.xPos;
                        this.sumY += this.yPos;
                        ++this.iSum;
                    } else if (this.button == 3) {
                        this.sumX += this.xPos;
                        this.sumY += this.yPos;
                        ++this.iSum;
                        this.lowYaxisXpixel = (double)this.sumX / (double)this.iSum;
                        this.lowYaxisYpixel = (double)this.windowHeight - (double)this.sumY / (double)this.iSum;
                        ++this.iCounter;
                        this.sumX = 0;
                        this.sumY = 0;
                        this.iSum = 0;
                    }
                    break;
                case 1:
                    this.xPos = var1.getX();
                    this.yPos = var1.getY();
                    this.button = var1.getButton();
                    if (this.button == 1) {
                        this.sumX += this.xPos;
                        this.sumY += this.yPos;
                        ++this.iSum;
                    } else if (this.button == 3) {
                        this.sumX += this.xPos;
                        this.sumY += this.yPos;
                        ++this.iSum;
                        this.highYaxisXpixel = (double)this.sumX / (double)this.iSum;
                        this.highYaxisYpixel = (double)this.windowHeight - (double)this.sumY / (double)this.iSum;
                        ++this.iCounter;
                        this.sumX = 0;
                        this.sumY = 0;
                        this.iSum = 0;
                    }
                    break;
                case 2:
                    this.xPos = var1.getX();
                    this.yPos = var1.getY();
                    this.button = var1.getButton();
                    if (this.button == 1) {
                        this.sumX += this.xPos;
                        this.sumY += this.yPos;
                        ++this.iSum;
                    } else if (this.button == 3) {
                        this.sumX += this.xPos;
                        this.sumY += this.yPos;
                        ++this.iSum;
                        this.lowXaxisXpixel = (double)this.sumX / (double)this.iSum;
                        this.lowXaxisYpixel = (double)this.windowHeight - (double)this.sumY / (double)this.iSum;
                        ++this.iCounter;
                        this.sumX = 0;
                        this.sumY = 0;
                        this.iSum = 0;
                    }
                    break;
                case 3:
                    this.xPos = var1.getX();
                    this.yPos = var1.getY();
                    this.button = var1.getButton();
                    new PixelGrabber(this.pic, this.xPos, this.yPos, 1, 1, false);
                    if (this.button == 1) {
                        this.sumX += this.xPos;
                        this.sumY += this.yPos;
                        ++this.iSum;
                    } else if (this.button == 3) {
                        this.sumX += this.xPos;
                        this.sumY += this.yPos;
                        ++this.iSum;
                        this.highXaxisXpixel = (double)this.sumX / (double)this.iSum;
                        this.highXaxisYpixel = (double)this.windowHeight - (double)this.sumY / (double)this.iSum;
                        ++this.iCounter;
                        this.sumX = 0;
                        this.sumY = 0;
                        this.iSum = 0;
                    }
                    break;
                default:
                    this.xPos = var1.getX();
                    this.yPos = var1.getY();
                    this.button = var1.getButton();
                    if (this.button == 1) {
                        this.xAndYvalues.add(new Integer(this.xPos));
                        this.xAndYvalues.add(new Integer(this.yPos));
                    }

                    if (this.button == 3 && this.xAndYvalues.size() / 2 != 0) {
                        this.outputData();
                        this.digitizationDone = true;
                    }
            }
        }

        this.repaint();
    }

    private void outputData() {
        this.nData = this.xAndYvalues.size() / 2;
        System.out.println("nData " + this.nData);
        this.xPositions = new double[this.nData];
        this.yPositions = new double[this.nData];
        this.xPosPixel = new double[this.nData];
        this.yPosPixel = new double[this.nData];
        int var1 = 0;

        int var2;
        int var4;
        for(var2 = 0; var2 < this.nData; ++var2) {
            int var3 = (Integer)this.xAndYvalues.get(var1);
            ++var1;
            var4 = (Integer)this.xAndYvalues.get(var1);
            ++var1;
            this.xPosPixel[var2] = (double)var3;
            this.yPosPixel[var2] = (double)this.windowHeight - (double)var4;
        }

        this.checkForRotation();

        for(var2 = 0; var2 < this.nData; ++var2) {
            this.xPositions[var2] = this.lowXvalue + (this.xPosPixel[var2] - this.lowXaxisXpixel) * (this.highXvalue - this.lowXvalue) / (this.highXaxisXpixel - this.lowXaxisXpixel);
            this.yPositions[var2] = this.lowYvalue + (this.yPosPixel[var2] - this.lowYaxisYpixel) * (this.highYvalue - this.lowYvalue) / (this.highYaxisYpixel - this.lowYaxisYpixel);
        }

        if (this.noIdentical) {
            this.checkForIdenticalPoints();
        }

        String var5 = "Do you wish to increase number of data points\n";
        var5 = var5 + "using cubic spline interpolation?";
        boolean var6 = Db.noYes(var5);
        if (var6) {
            this.nInterpPoints = Db.readInt("Enter number of interpolation points", 200);
            this.interpolation();
            this.interpOpt = true;
        } else if (this.plotOpt) {
            this.plotDigitisedPoints();
        }

        this.fout.println("Digitization output for DigiGraph class (M. T. Flanagan Java Library)");
        this.fout.println();
        this.fout.dateAndTimeln();
        this.fout.println();
        this.fout.println("Image used in the digitization:                 " + this.imageName);
        this.fout.println("Location of the image used in the digitization: " + this.imagePath);
        this.fout.println();
        this.fout.println("X-axis skew angle    " + Fmath.truncate(this.angleXaxis, 4) + " degrees");
        this.fout.println("Y-axis skew angle    " + Fmath.truncate(this.angleYaxis, 4) + " degrees");
        this.fout.println("Axes mean skew angle " + Fmath.truncate(this.angleMean, 4) + " degrees");
        if (this.rotationDone) {
            this.fout.println("Axes and all points rotated to bring axes to normal position");
        } else {
            this.fout.println("No rotation of axes or points performed");
        }

        this.fout.println();
        this.fout.println("Number of digitized points: " + this.nData);
        this.fout.println();
        this.fout.printtab("X-value");
        this.fout.println("Y-value");

        for(var4 = 0; var4 < this.nData; ++var4) {
            this.fout.printtab(Fmath.truncate(this.xPositions[var4], this.trunc));
            this.fout.println(Fmath.truncate(this.yPositions[var4], this.trunc));
        }

        this.fout.println();
        if (this.interpOpt) {
            this.fout.println();
            this.fout.println("Interpolated data (cubic spline)");
            this.fout.println();
            this.fout.println("Number of interpolated points: " + this.nInterpPoints);
            this.fout.println();
            this.fout.printtab("X-value");
            this.fout.println("Y-value");

            for(var4 = 0; var4 < this.nInterpPoints; ++var4) {
                this.fout.printtab(Fmath.truncate(this.xInterp[var4], this.trunc));
                this.fout.println(Fmath.truncate(this.yInterp[var4], this.trunc));
            }
        }

        this.fout.close();
    }

    private void checkForRotation() {
        double var1 = (this.highYaxisXpixel - this.lowYaxisXpixel) / (this.highYaxisYpixel - this.lowYaxisYpixel);
        this.angleYaxis = Math.toDegrees(Math.atan(var1));
        var1 = (this.lowXaxisYpixel - this.highXaxisYpixel) / (this.highXaxisXpixel - this.lowXaxisXpixel);
        this.angleXaxis = Math.toDegrees(Math.atan(var1));
        this.angleMean = (this.angleXaxis + this.angleYaxis) / 2.0D;
        double var3 = Math.abs(this.angleMean);
        if (var3 != 0.0D && var3 > this.angleTolerance) {
            this.performRotation();
        }

    }

    private void performRotation() {
        double var1 = (this.highXaxisYpixel - this.lowXaxisYpixel) / (this.highXaxisXpixel - this.lowXaxisXpixel);
        double var3 = this.highXaxisYpixel - var1 * this.highXaxisXpixel;
        double var5 = (this.highYaxisYpixel - this.lowYaxisYpixel) / (this.highYaxisXpixel - this.lowYaxisXpixel);
        double var7 = this.highYaxisYpixel - var5 * this.highYaxisXpixel;
        double var9 = (var3 - var7) / (var5 - var1);
        double var11 = var5 * var9 + var7;
        double var13 = Math.toRadians(this.angleMean);
        double var15 = Math.cos(-var13);
        double var17 = Math.sin(-var13);
        double var19 = (this.highXaxisXpixel - var9) * var15 + (this.highXaxisYpixel - var11) * var17 + var9;
        double var21 = -(this.highXaxisXpixel - var9) * var17 + (this.highXaxisYpixel - var11) * var15 + var11;
        double var23 = (this.lowXaxisXpixel - var9) * var15 + (this.lowXaxisYpixel - var11) * var17 + var9;
        double var25 = -(this.lowXaxisXpixel - var9) * var17 + (this.lowXaxisYpixel - var11) * var15 + var11;
        double var27 = (this.highYaxisXpixel - var9) * var15 + (this.highYaxisYpixel - var11) * var17 + var9;
        double var29 = -(this.highYaxisXpixel - var9) * var17 + (this.highYaxisYpixel - var11) * var15 + var11;
        double var31 = -(this.lowYaxisXpixel - var9) * var15 + (this.lowYaxisYpixel - var11) * var17 + var9;
        double var33 = (this.lowYaxisXpixel - var9) * var17 + (this.lowYaxisYpixel - var11) * var15 + var11;
        this.highXaxisXpixel = var19;
        this.highXaxisYpixel = var21;
        this.lowXaxisXpixel = var23;
        this.lowXaxisYpixel = var25;
        this.highYaxisXpixel = var27;
        this.highYaxisYpixel = var29;
        this.lowYaxisXpixel = var31;
        this.lowYaxisYpixel = var33;

        for(int var35 = 0; var35 < this.nData; ++var35) {
            double var36 = (this.xPosPixel[var35] - var9) * var15 + (this.yPosPixel[var35] - var11) * var17 + var9;
            double var38 = -(this.xPosPixel[var35] - var9) * var17 + (this.yPosPixel[var35] - var11) * var15 + var11;
            this.xPosPixel[var35] = var36;
            this.yPosPixel[var35] = var38;
        }

        this.rotationDone = true;
    }

    public void mousePressed(MouseEvent var1) {
    }

    public void mouseReleased(MouseEvent var1) {
    }

    public void mouseEntered(MouseEvent var1) {
        this.mouseEntered = true;
        this.repaint();
    }

    public void mouseExited(MouseEvent var1) {
        this.mouseEntered = false;
        this.repaint();
    }

    private void interpolation() {
        this.xInterp = new double[this.nInterpPoints];
        this.yInterp = new double[this.nInterpPoints];
        double var1 = (this.xPositions[this.nData - 1] - this.xPositions[0]) / (double)(this.nInterpPoints - 1);
        this.xInterp[0] = this.xPositions[0];

        for(int var3 = 1; var3 < this.nInterpPoints - 1; ++var3) {
            this.xInterp[var3] = this.xInterp[var3 - 1] + var1;
        }

        this.xInterp[this.nInterpPoints - 1] = this.xPositions[this.nData - 1];
        CubicSpline var9 = new CubicSpline(this.xPositions, this.yPositions);

        int var4;
        for(var4 = 0; var4 < this.nInterpPoints; ++var4) {
            this.yInterp[var4] = var9.interpolate(this.xInterp[var4]);
        }

        if (this.plotOpt) {
            var4 = Math.max(this.nInterpPoints, this.nData);
            double[][] var5 = PlotGraph.fillData(2, var4);
            var5[0] = this.xPositions;
            var5[1] = this.yPositions;
            var5[2] = this.xInterp;
            var5[3] = this.yInterp;
            PlotGraph var6 = new PlotGraph(var5);
            var6.setGraphTitle("Cubic Spline Interpolation of Digitised Points");
            var6.setGraphTitle2(this.imagePath);
            var6.setXaxisLegend("x");
            var6.setYaxisLegend("y");
            int[] var7 = new int[]{0, 3};
            var6.setLine(var7);
            int[] var8 = new int[]{4, 0};
            var6.setPoint(var8);
            var6.setFrame();
        }

    }

    public void checkForIdenticalPoints() {
        int var1 = this.nData;
        boolean var2 = true;
        int var3 = 0;

        int var6;
        while(var2) {
            boolean var4 = true;
            int var5 = var3 + 1;

            while(true) {
                while(var4) {
                    System.out.println("ii " + var3 + "  jj  " + var5);
                    if (this.xPositions[var3] == this.xPositions[var5] && this.yPositions[var3] == this.yPositions[var5]) {
                        System.out.print("Class DigiGraph: two identical points, " + this.xPositions[var3] + ", " + this.yPositions[var3]);
                        System.out.println(", in data array at indices " + var3 + " and " + var5 + ", one point removed");

                        for(var6 = var5; var6 < var1; ++var6) {
                            this.xPositions[var6 - 1] = this.xPositions[var6];
                            this.yPositions[var6 - 1] = this.yPositions[var6];
                        }

                        --var1;
                        if (var1 - 1 == var3) {
                            var4 = false;
                        }
                    } else {
                        ++var5;
                        if (var5 >= var1) {
                            var4 = false;
                        }
                    }
                }

                ++var3;
                if (var3 >= var1 - 1) {
                    var2 = false;
                }
                break;
            }
        }

        if (var1 != this.nData) {
            double[] var7 = new double[var1];
            double[] var8 = new double[var1];

            for(var6 = 0; var6 < var1; ++var6) {
                var7[var6] = this.xPositions[var6];
                var8[var6] = this.yPositions[var6];
            }

            this.xPositions = var7;
            this.yPositions = var8;
            this.nData = var1;
        }

    }

    private void plotDigitisedPoints() {
        double[][] var1 = PlotGraph.fillData(1, this.nData);
        var1[0] = this.xPositions;
        var1[1] = this.yPositions;
        PlotGraph var2 = new PlotGraph(var1);
        var2.setGraphTitle("Plot of the Digitised Points");
        var2.setGraphTitle2(this.imagePath);
        var2.setXaxisLegend("x");
        var2.setYaxisLegend("y");
        int[] var3 = new int[]{0};
        var2.setLine(var3);
        int[] var4 = new int[]{4};
        var2.setPoint(var4);
        var2.setFrame();
    }
}
