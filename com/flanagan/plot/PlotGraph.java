//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.plot;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.io.Serializable;
import javax.swing.*;

public class PlotGraph extends Plot implements Serializable {
    protected static final long serialVersionUID = 1L;
    protected int graphWidth = 600;
    protected int graphHeight = 400;
    protected int closeChoice = 1;
    protected JFrame window = new JFrame("");



    public PlotGraph(double[][] datas) {
        super(datas);
    }

    public PlotGraph(double[] var1, double[] var2) {
        super(var1, var2);
    }

    public void rescaleY(double var1) {
        this.graphHeight = (int)Math.round((double)this.graphHeight * var1);
        super.yLength = (int)Math.round((double)super.yLength * var1);
        super.yTop = (int)Math.round((double)super.yTop * var1);
        super.yBottom = super.yTop + super.yLength;
    }

    public void rescaleX(double var1) {
        this.graphWidth = (int)Math.round((double)this.graphWidth * var1);
        super.xLength = (int)Math.round((double)super.xLength * var1);
        super.xBottom = (int)Math.round((double)super.xBottom * var1);
        super.xTop = super.xBottom + super.xLength;
    }

    public int getGraphWidth() {
        return this.graphWidth;
    }

    public int getGraphHeight() {
        return this.graphHeight;
    }

    public void setGraphHeight(int var1) {
        this.graphHeight = var1;
    }

    public void setGraphWidth(int var1) {
        this.graphWidth = var1;
    }

    public int getCloseChoice() {
        return this.closeChoice;
    }

    public void setCloseChoice(int var1) {
        this.closeChoice = var1;
    }

    @Override
    public void paint(Graphics var1) {
        double var2 = (double)this.getSize().width;
        double var4 = (double)this.getSize().height;
        double var6 = var2 / (double)this.graphWidth;
        double var8 = var4 / (double)this.graphHeight;
        this.rescaleX(var6);
        this.rescaleY(var8);
        this.graph(var1);
    }



    public void setFrame() {
        this.setSize(this.graphWidth, this.graphHeight);
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

    public void endProgram() {
        int var1 = JOptionPane.showConfirmDialog((Component)null, "Do you wish to end the program\nThis will also close the graph window or windows", "End Program", 0, 3);
        if (var1 == 0) {
            System.exit(0);
        } else {
            String var2 = "Now you must press the appropriate escape key/s, e.g. Ctrl C, to exit this program\n";
            if (this.closeChoice == 1) {
                var2 = var2 + "or close a graph window";
            }

            JOptionPane.showMessageDialog((Component)null, var2);
        }

    }

    public static long getSerialVersionUID() {
        return 1L;
    }
}
