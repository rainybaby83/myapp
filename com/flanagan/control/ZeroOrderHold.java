//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.control;

import com.flanagan.complex.Complex;
import com.flanagan.complex.ComplexPoly;

public class ZeroOrderHold extends BlackBox {
    public ZeroOrderHold(double var1, int var3) {
        super("ZeroOrderHold");
        super.sPoles = Complex.oneDarray(1);
        super.setDeltaT(var1);
        super.setPadeOrder(var3);
        this.setNumDen(var1);
    }

    public ZeroOrderHold(double var1) {
        super("ZeroOrderHold");
        super.sPoles = Complex.oneDarray(1);
        super.setDeltaT(var1);
        this.setNumDen(var1);
    }

    private ZeroOrderHold() {
        super("ZeroOrderHold");
    }

    public void setNumDen(double var1) {
        super.sDenom = new ComplexPoly(0.0D, 1.0D);
        super.sPoles[0].reset(0.0D, 0.0D);
        super.sNumer = new ComplexPoly(1.0D);
        super.deadTime = var1;
        super.pade();
        super.deadTime = 0.0D;
        super.sNumerPade = super.sNumerPade.plus(super.sDenomPade);
        super.sZerosPade = this.sNumerPade.rootsNoMessages();
        super.sNumer = super.sNumerPade;
        super.sDenom = super.sDenomPade;
        super.sPoles = super.sPolesPade;
        super.sZeros = super.sZerosPade;
        super.sNumerDegPade = super.sNumerPade.getDeg();
        super.sDenomDegPade = super.sDenomPade.getDeg();
        super.sNumerDeg = super.sNumerDegPade;
        super.sDenomDeg = super.sDenomDegPade;
        if (super.sNumerDeg == 0) {
            super.sNumerScaleFactor = super.sNumer.coeffCopy(0);
        } else {
            super.sNumerScaleFactor = BlackBox.scaleFactor(super.sNumerPade, super.sZerosPade);
        }

        if (super.sDenomDeg == 0) {
            super.sDenomScaleFactor = super.sDenom.coeffCopy(0);
        } else {
            super.sDenomScaleFactor = BlackBox.scaleFactor(super.sDenomPade, super.sPolesPade);
        }

    }

    public ZeroOrderHold copy() {
        if (this == null) {
            return null;
        } else {
            ZeroOrderHold var1 = new ZeroOrderHold();
            this.copyBBvariables(var1);
            return var1;
        }
    }

    public Object clone() {
        return this.copy();
    }
}
