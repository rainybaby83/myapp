//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.control;

import com.flanagan.complex.ComplexPoly;
import java.util.ArrayList;
import java.util.Vector;

public class ClosedLoop extends BlackBox {
    private OpenLoop forwardPath = new OpenLoop();
    private OpenLoop closedPath = new OpenLoop();
    private ArrayList<BlackBox> feedbackPath = new ArrayList();
    private int nFeedbackBoxes = 0;
    private boolean checkNoMix = true;
    private boolean checkConsolidate = false;
    private double deadTimeSum = 0.0D;

    public ClosedLoop() {
        super("ClosedLoop");
    }

    public void addBoxToForwardPath(BlackBox var1) {
        this.forwardPath.addBoxToPath(var1);
    }

    public void addBoxToFeedbackPath(BlackBox var1) {
        this.feedbackPath.add(var1);
        ++this.nFeedbackBoxes;
    }

    public void consolidate() {
        this.closedPath = this.forwardPath.copy();

        for(int var1 = 0; var1 < this.nFeedbackBoxes; ++var1) {
            this.closedPath.addBoxToPath((BlackBox)this.feedbackPath.get(var1));
        }

        this.forwardPath.consolidate();
        if (!this.forwardPath.getCheckNoMix()) {
            this.checkNoMix = false;
        }

        this.closedPath.consolidate();
        if (!this.closedPath.getCheckNoMix()) {
            this.checkNoMix = false;
        }

        ComplexPoly var5 = this.forwardPath.getSnumer();
        ComplexPoly var2 = this.forwardPath.getSdenom();
        ComplexPoly var3 = this.closedPath.getSnumer();
        ComplexPoly var4 = this.closedPath.getSdenom();
        if (var2.isEqual(var4)) {
            super.setSnumer(var5.copy());
            super.setSdenom(var3.plus(var2).copy());
        } else {
            super.setSnumer(var5.times(var4));
            super.setSdenom(var3.times(var2).plus(var4.times(var2)));
        }

        this.checkConsolidate = true;
        this.deadTimeSum = this.closedPath.getDeadTime();
        super.deadTime = 0.0D;
        this.checkConsolidate = true;
    }

    public int getNumberOfBoxesInForwardPath() {
        if (!this.checkConsolidate) {
            this.consolidate();
        }

        return this.forwardPath.getNumberOfBoxes();
    }

    public int getNumberOfBoxesInClosedLoop() {
        if (!this.checkConsolidate) {
            this.consolidate();
        }

        return this.closedPath.getNumberOfBoxes();
    }

    public ArrayList<Object> getForwardPathSegmentsArrayList() {
        if (!this.checkConsolidate) {
            this.consolidate();
        }

        return this.forwardPath.getSegmentsArrayList();
    }

    public Vector<Object> getForwardPathSegmentsVector() {
        if (!this.checkConsolidate) {
            this.consolidate();
        }

        return this.forwardPath.getSegmentsVector();
    }

    public ArrayList<Object> getClosedLoopSegmentsArrayList() {
        if (!this.checkConsolidate) {
            this.consolidate();
        }

        return this.closedPath.getSegmentsArrayList();
    }

    public Vector<Object> getClosedLoopSegmentsVector() {
        if (!this.checkConsolidate) {
            this.consolidate();
        }

        return this.closedPath.getSegmentsVector();
    }

    public int getNumberOfSegmentsInForwardPath() {
        if (!this.checkConsolidate) {
            this.consolidate();
        }

        return this.forwardPath.getNumberOfSegments();
    }

    public int getNumberOfSegmentsInClosedLoop() {
        if (!this.checkConsolidate) {
            this.consolidate();
        }

        return this.closedPath.getNumberOfSegments();
    }

    public String getNamesOfBoxesInForwardPath() {
        if (!this.checkConsolidate) {
            this.consolidate();
        }

        return this.forwardPath.getNamesOfBoxes();
    }

    public String getNamesOfBoxesInClosedLoop() {
        if (!this.checkConsolidate) {
            this.consolidate();
        }

        return this.closedPath.getNamesOfBoxes();
    }

    public void removeAllBoxes() {
        this.forwardPath.removeAllBoxes();
        this.closedPath.removeAllBoxes();
        this.feedbackPath.clear();
        this.checkNoMix = true;
        this.checkConsolidate = false;
        this.nFeedbackBoxes = 0;
    }

    @Override
    public double getDeadTime() {
        return this.deadTimeSum;
    }

    @Override
    public ClosedLoop copy() {
        if (this == null) {
            return null;
        } else {
            ClosedLoop var1 = new ClosedLoop();
            this.copyBBvariables(var1);
            var1.nFeedbackBoxes = this.nFeedbackBoxes;
            var1.checkNoMix = this.checkNoMix;
            var1.checkConsolidate = this.checkConsolidate;
            var1.forwardPath = this.forwardPath.copy();
            var1.closedPath = this.closedPath.copy();
            var1.feedbackPath = new ArrayList();
            if (this.feedbackPath.size() != 0) {
                for(int var2 = 0; var2 < this.feedbackPath.size(); ++var2) {
                    var1.feedbackPath.add(((BlackBox)this.feedbackPath.get(var2)).copy());
                }
            }

            return var1;
        }
    }


    @Override
    public Object clone() {
        return this.copy();
    }
}
