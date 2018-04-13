//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package flanagan.control;

import flanagan.complex.Complex;
import java.util.ArrayList;
import java.util.Vector;

public class OpenLoop extends BlackBox {
    private ArrayList<BlackBox> openPath = new ArrayList();
    private ArrayList<Object> segments = new ArrayList();
    private int nBoxes = 0;
    private int nSeg = 0;
    private boolean checkPath = false;
    private boolean checkNoMix = true;
    private boolean checkConsolidate = false;
    private boolean[] adcs = null;
    private boolean[] dacs = null;
    private boolean[] zeroHolds = null;

    public OpenLoop() {
        super("OpenLoop");
    }

    public void addBoxToPath(BlackBox var1) {
        this.openPath.add(var1);
        ++this.nBoxes;
    }

    public void consolidate() {
        if (!this.segments.isEmpty()) {
            this.segments.clear();
            this.nBoxes = 0;
            this.nSeg = 0;
            this.checkNoMix = true;
            this.checkPath = false;
        }

        this.segment();
        BlackBox var1 = null;
        if (this.nSeg == 1) {
            if (this.nBoxes == 1) {
                var1 = (BlackBox)this.openPath.get(0);
            } else {
                var1 = (BlackBox)this.segments.get(3);
            }
        } else {
            var1 = this.combineSegment(0, this.nBoxes);
        }

        super.sNumer = var1.sNumer.copy();
        super.sDenom = var1.sDenom.copy();
        super.sNumerPade = var1.sNumerPade.copy();
        super.sDenomPade = var1.sDenomPade.copy();
        super.sNumerDeg = var1.sNumerDeg;
        super.sDenomDeg = var1.sDenomDeg;
        super.sNumerDegPade = var1.sNumerDegPade;
        super.sDenomDegPade = var1.sDenomDegPade;
        super.sNumerSet = true;
        super.sDenomSet = true;
        super.deadTime = var1.deadTime;
        super.sZeros = Complex.copy(var1.sZeros);
        super.sPoles = Complex.copy(var1.sPoles);
        super.sZerosPade = Complex.copy(var1.sZerosPade);
        super.sPolesPade = Complex.copy(var1.sPolesPade);
        super.padeAdded = true;
        if (super.sNumerDeg == 0) {
            super.sNumerScaleFactor = super.sNumer.coeffCopy(0);
        } else {
            super.sNumerScaleFactor = BlackBox.scaleFactor(super.sNumer, super.sZeros);
        }

        if (super.sDenomDeg == 0) {
            super.sDenomScaleFactor = super.sDenom.coeffCopy(0);
        } else {
            super.sDenomScaleFactor = BlackBox.scaleFactor(super.sDenom, super.sPoles);
        }

        this.checkConsolidate = true;
    }

    public void segment() {
        this.adcs = new boolean[this.nBoxes];
        int var1 = 0;
        this.dacs = new boolean[this.nBoxes];
        int var2 = 0;
        this.zeroHolds = new boolean[this.nBoxes];
        int var3 = 0;
        String var4 = null;

        int var5;
        for(var5 = 0; var5 < this.nBoxes; ++var5) {
            this.adcs[var5] = false;
            this.dacs[var5] = false;
            this.zeroHolds[var5] = false;
            BlackBox var6 = (BlackBox)this.openPath.get(var5);
            var4 = var6.fixedName;
            if (var4.equals("ADC")) {
                this.adcs[var5] = true;
                ++var1;
            } else if (var4.equals("DAC")) {
                this.dacs[var5] = true;
                ++var2;
            } else if (var4.equals("ZeroOrderHold")) {
                this.zeroHolds[var5] = true;
                ++var3;
            }
        }

        if (var1 == 0 && var2 == 0) {
            this.nSeg = 1;
            this.checkNoMix = true;
            this.checkPath = true;
            this.segments.add(new Integer(0));
            this.segments.add(new Integer(this.nBoxes - 1));
            this.segments.add("analogue");
            BlackBox var12 = this.combineSegment(0, this.nBoxes - 1);
            this.segments.add(var12);
        } else {
            this.nSeg = 0;
            var5 = 0;
            int var11 = 0;
            boolean var7 = false;
            boolean var8;
            if (var1 > 0 && var2 > 0) {
                var8 = true;
                var5 = 0;

                while(var8) {
                    if (this.adcs[var5]) {
                        var8 = false;
                    } else {
                        ++var5;
                        if (var5 >= this.nBoxes) {
                            var8 = false;
                        }
                    }
                }

                var8 = true;

                while(var8) {
                    if (this.dacs[var11]) {
                        var8 = false;
                    } else {
                        ++var11;
                        if (var11 >= this.nBoxes) {
                            var8 = false;
                        }
                    }
                }

                if (var5 < var11) {
                    var7 = true;
                }
            } else if (var1 > 0) {
                var7 = true;
            }

            var8 = var7;
            ++this.nSeg;
            boolean var9 = false;
            BlackBox var10;
            int var13;
            if (var7) {
                this.segments.add(new Integer(0));
                this.segments.add(new Integer(var5));
                this.segments.add("digital");
                var10 = this.combineSegment(0, var5);
                this.segments.add(var10);
                var13 = var5 + 1;
            } else {
                this.segments.add(new Integer(0));
                this.segments.add(new Integer(var11));
                this.segments.add("analogue");
                var10 = this.combineSegment(0, var11);
                this.segments.add(var10);
                var13 = var11 + 1;
            }

            boolean var14 = true;
            if (var13 >= this.nBoxes) {
                var14 = false;
            }

            while(var14) {
                if (var8) {
                    var13 = this.nextDigitalSegment(var13);
                    var8 = false;
                } else {
                    var13 = this.nextAnalogueSegment(var13);
                    var8 = true;
                }

                if (var13 >= this.nBoxes) {
                    var14 = false;
                }
            }
        }

    }

    private int nextDigitalSegment(int var1) {
        int var2 = this.nBoxes;
        boolean var3 = false;
        boolean var4 = true;
        int var5 = var1;

        while(var4) {
            if (this.adcs[var5]) {
                var2 = var5;
                var4 = false;
            } else {
                ++var5;
                if (var5 >= this.nBoxes) {
                    var4 = false;
                }
            }
        }

        int var6 = this.nBoxes;
        var4 = true;
        var5 = var1;

        while(var4) {
            if (this.dacs[var5]) {
                var6 = var5;
                var4 = false;
            } else {
                ++var5;
                if (var5 >= this.nBoxes) {
                    var4 = false;
                    var3 = true;
                }
            }
        }

        if (var3) {
            var6 = this.nBoxes - 1;
        }

        if (var2 < var6) {
            throw new IllegalArgumentException("Two consecutive ADCs with no intervening DAC");
        } else {
            ++this.nSeg;
            this.segments.add(new Integer(0));
            this.segments.add(new Integer(var6));
            this.segments.add("digital");
            BlackBox var7 = this.combineSegment(0, var6);
            this.segments.add(var7);
            return var6 + 1;
        }
    }

    private int nextAnalogueSegment(int var1) {
        int var2 = this.nBoxes;
        boolean var3 = false;
        boolean var4 = true;
        int var5 = var1;

        while(var4) {
            if (this.adcs[var5]) {
                var2 = var5;
                var4 = false;
            } else {
                ++var5;
                if (var5 >= this.nBoxes) {
                    var4 = false;
                    var3 = true;
                }
            }
        }

        int var6 = this.nBoxes;
        var4 = true;
        var5 = var1;

        while(var4) {
            if (this.dacs[var5]) {
                var6 = var5;
                var4 = false;
            } else {
                ++var5;
                if (var5 >= this.nBoxes) {
                    var4 = false;
                }
            }
        }

        if (var3) {
            var2 = this.nBoxes - 1;
        }

        if (var6 < var2) {
            throw new IllegalArgumentException("Two consecutive DACs with no intervening ADC");
        } else {
            ++this.nSeg;
            this.segments.add(new Integer(0));
            this.segments.add(new Integer(var2));
            this.segments.add("digital");
            BlackBox var7 = this.combineSegment(0, var2);
            this.segments.add(var7);
            return var2 + 1;
        }
    }

    public BlackBox combineSegment(int var1, int var2) {
        ArrayList var3 = new ArrayList();
        ArrayList var4 = new ArrayList();
        ArrayList var5 = new ArrayList();
        ArrayList var6 = new ArrayList();
        BlackBox var7 = new BlackBox();
        int var8 = var2 - var1 + 1;
        BlackBox var9 = (BlackBox)this.openPath.get(var1);
        if (!var9.padeAdded) {
            var9.transferPolesZeros();
        }

        var7.sNumerPade = var9.sNumerPade.copy();
        var7.sDenomPade = var9.sDenomPade.copy();
        var7.sNumer = var9.sNumer.copy();
        var7.sDenom = var9.sDenom.copy();
        var7.sNumerDegPade = var9.sNumerDegPade;
        var7.sDenomDegPade = var9.sDenomDegPade;
        var7.sNumerDeg = var9.sNumerDeg;
        var7.sDenomDeg = var9.sDenomDeg;
        Complex[] var10;
        int var11;
        if (var7.sNumerDegPade > 0) {
            var10 = Complex.copy(var9.sZerosPade);

            for(var11 = 0; var11 < var7.sNumerDegPade; ++var11) {
                var5.add(var10[var11]);
            }
        }

        if (var7.sDenomDegPade > 0) {
            var10 = Complex.copy(var9.sPolesPade);

            for(var11 = 0; var11 < var7.sDenomDegPade; ++var11) {
                var6.add(var10[var11]);
            }
        }

        if (var7.sNumerDeg > 0) {
            var10 = Complex.copy(var9.sZeros);

            for(var11 = 0; var11 < var7.sNumerDeg; ++var11) {
                var3.add(var10[var11]);
            }
        }

        if (var7.sDenomDeg > 0) {
            var10 = Complex.copy(var9.sPoles);

            for(var11 = 0; var11 < var7.sDenomDeg; ++var11) {
                var4.add(var10[var11]);
            }
        }

        var7.deadTime = var9.deadTime;
        var7.sNumerScaleFactor = var9.sNumerScaleFactor.copy();
        var7.sDenomScaleFactor = var9.sDenomScaleFactor.copy();

        int var13;
        for(var13 = 1; var13 < var8; ++var13) {
            var9 = (BlackBox)this.openPath.get(var13 + var1);
            if (!var9.padeAdded) {
                var9.transferPolesZeros();
            }

            if (var7.sNumerPade == null) {
                if (var9.sNumerPade != null) {
                    var7.sNumerPade = var9.sNumerPade.copy();
                }
            } else if (var9.sNumerPade != null) {
                var7.sNumerPade = var7.sNumerPade.times(var9.sNumerPade);
            }

            if (var7.sNumer == null) {
                if (var9.sNumer != null) {
                    var7.sNumer = var9.sNumer.copy();
                }
            } else if (var9.sNumer != null) {
                var7.sNumer = var7.sNumer.times(var9.sNumer);
            }

            if (var7.sDenom == null) {
                if (var9.sDenom != null) {
                    var7.sDenom = var9.sDenom.copy();
                }
            } else if (var9.sDenom != null) {
                var7.sDenom = var7.sDenom.times(var9.sDenom);
            }

            if (var7.sDenomPade == null) {
                if (var9.sDenomPade != null) {
                    var7.sDenomPade = var9.sDenomPade.copy();
                }
            } else if (var9.sDenomPade != null) {
                var7.sDenomPade = var7.sDenomPade.times(var9.sDenomPade);
            }

            var7.sNumerDegPade += var9.sNumerDegPade;
            var7.sDenomDegPade += var9.sDenomDegPade;
            var7.sNumerDeg += var9.sNumerDeg;
            var7.sDenomDeg += var9.sDenomDeg;
            var7.sNumerScaleFactor = var9.sNumerScaleFactor.times(var7.sNumerScaleFactor);
            var7.sDenomScaleFactor = var9.sDenomScaleFactor.times(var7.sDenomScaleFactor);
            var7.deadTime += var9.deadTime;
            int var12;
            Complex[] var14;
            if (var9.sNumerDegPade > 0) {
                var14 = Complex.copy(var9.sZerosPade);

                for(var12 = 0; var12 < var9.sNumerDegPade; ++var12) {
                    var5.add(var14[var12]);
                }
            }

            if (var9.sDenomDegPade > 0) {
                var14 = Complex.copy(var9.sPolesPade);

                for(var12 = 0; var12 < var9.sDenomDegPade; ++var12) {
                    var6.add(var14[var12]);
                }
            }

            if (var9.sNumerDeg > 0) {
                var14 = Complex.copy(var9.sZeros);

                for(var12 = 0; var12 < var9.sNumerDeg; ++var12) {
                    var3.add(var14[var12]);
                }
            }

            if (var9.sDenomDeg > 0) {
                var14 = Complex.copy(var9.sPoles);

                for(var12 = 0; var12 < var9.sDenomDeg; ++var12) {
                    var4.add(var14[var12]);
                }
            }
        }

        if (var7.sNumerDegPade > 0) {
            var7.sZerosPade = Complex.oneDarray(var7.sNumerDegPade);

            for(var13 = 0; var13 < var7.sNumerDegPade; ++var13) {
                var7.sZerosPade[var13] = (Complex)var5.get(var13);
            }
        }

        if (var7.sDenomDegPade > 0) {
            var7.sPolesPade = Complex.oneDarray(var7.sDenomDegPade);

            for(var13 = 0; var13 < var7.sDenomDegPade; ++var13) {
                var7.sPolesPade[var13] = (Complex)var6.get(var13);
            }
        }

        if (var7.sNumerDeg > 0) {
            var7.sZeros = Complex.oneDarray(var7.sNumerDeg);

            for(var13 = 0; var13 < var7.sNumerDeg; ++var13) {
                var7.sZeros[var13] = (Complex)var3.get(var13);
            }
        }

        if (var7.sDenomDeg > 0) {
            var7.sPoles = Complex.oneDarray(var7.sDenomDeg);

            for(var13 = 0; var13 < var7.sDenomDeg; ++var13) {
                var7.sPoles[var13] = (Complex)var4.get(var13);
            }
        }

        return var7;
    }

    public int getNumberOfBoxes() {
        if (!this.checkConsolidate) {
            this.consolidate();
        }

        return this.nBoxes;
    }

    public ArrayList<Object> getSegmentsArrayList() {
        if (!this.checkConsolidate) {
            this.consolidate();
        }

        return this.segments;
    }

    public Vector<Object> getSegmentsVector() {
        if (!this.checkConsolidate) {
            this.consolidate();
        }

        ArrayList var1 = this.segments;
        Vector var2 = null;
        if (var1 != null) {
            int var3 = var1.size();
            var2 = new Vector(var3);

            for(int var4 = 0; var4 < var3; ++var4) {
                var2.addElement(var1.get(var4));
            }
        }

        return var2;
    }

    public int getNumberOfSegments() {
        if (!this.checkConsolidate) {
            this.consolidate();
        }

        return this.nSeg;
    }

    public String getNamesOfBoxes() {
        if (!this.checkConsolidate) {
            this.consolidate();
        }

        String var1 = "";

        for(int var2 = 0; var2 < this.nBoxes; ++var2) {
            BlackBox var3 = (BlackBox)this.openPath.get(var2);
            var1 = var1 + var2 + ": " + var3.getName() + "   ";
        }

        return var1;
    }

    public void removeAllBoxes() {
        if (!this.openPath.isEmpty()) {
            this.openPath.clear();
        }

        if (!this.segments.isEmpty()) {
            this.segments.clear();
        }

        this.nSeg = 0;
        this.checkNoMix = true;
        this.checkPath = false;
        this.nBoxes = 0;
        this.checkConsolidate = false;
        this.adcs = null;
        this.dacs = null;
        this.zeroHolds = null;
    }

    public boolean getCheckNoMix() {
        return this.checkNoMix;
    }

    public OpenLoop copy() {
        if (this == null) {
            return null;
        } else {
            OpenLoop var1 = new OpenLoop();
            this.copyBBvariables(var1);
            var1.nBoxes = this.nBoxes;
            var1.nSeg = this.nSeg;
            var1.checkPath = this.checkPath;
            var1.checkNoMix = this.checkNoMix;
            var1.checkConsolidate = this.checkConsolidate;
            int var2;
            if (this.openPath.size() == 0) {
                var1.openPath = new ArrayList();
            } else {
                for(var2 = 0; var2 < this.openPath.size(); ++var2) {
                    var1.openPath.add(((BlackBox)this.openPath.get(var2)).copy());
                }
            }

            if (this.segments.size() == 0) {
                var1.segments = new ArrayList();
            } else {
                var2 = 0;

                for(int var3 = 0; var3 < this.nSeg; ++var3) {
                    Integer var4 = (Integer)this.segments.get(var2);
                    int var5 = var4;
                    var1.segments.add(new Integer(var5));
                    ++var2;
                    Integer var6 = (Integer)this.segments.get(var2);
                    var5 = var6;
                    var1.segments.add(new Integer(var5));
                    ++var2;
                    String var7 = (String)this.segments.get(var2);
                    var1.segments.add(var7);
                    ++var2;
                    var1.segments.add(((BlackBox)this.segments.get(var2)).copy());
                    ++var2;
                }
            }

            return var1;
        }
    }

    public Object clone() {
        return this.copy();
    }
}
