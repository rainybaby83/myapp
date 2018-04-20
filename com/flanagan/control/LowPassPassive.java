//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package flanagan.control;

import flanagan.complex.Complex;
import flanagan.complex.ComplexPoly;

public class LowPassPassive extends BlackBox {
    private double resistance = 0.0D;
    private double capacitance = 0.0D;
    private double timeConstant = 0.0D;
    private boolean setR = false;
    private boolean setC = false;

    public LowPassPassive() {
        super("PassiveLowPass");
        super.sPoles = Complex.oneDarray(1);
        super.setSnumer(new ComplexPoly(1.0D));
        super.setSdenom(new ComplexPoly(1.0D, 1.0D));
        super.setZtransformMethod(1);
        super.addDeadTimeExtras();
        this.timeConstant = 1.0D;
    }

    public void setResistance(double var1) {
        this.resistance = var1;
        this.timeConstant = var1 * this.capacitance;
        this.calcPolesZerosS();
        super.sDenom = ComplexPoly.rootsToPoly(this.sPoles);
        super.addDeadTimeExtras();
        this.setR = true;
    }

    public void setCapacitance(double var1) {
        this.capacitance = var1;
        this.timeConstant = var1 * this.resistance;
        this.calcPolesZerosS();
        super.sDenom = ComplexPoly.rootsToPoly(this.sPoles);
        super.addDeadTimeExtras();
        this.setC = true;
    }

    public void setTimeConstant(double var1) {
        this.timeConstant = var1;
        this.calcPolesZerosS();
        super.sDenom = ComplexPoly.rootsToPoly(this.sPoles);
        super.addDeadTimeExtras();
    }

    public double getResistance() {
        if (this.setR) {
            return this.resistance;
        } else {
            System.out.println("Class; LowPassPassive, method: getResistance");
            System.out.println("No resistance has been entered; zero returned");
            return 0.0D;
        }
    }

    public double getCapacitance() {
        if (this.setC) {
            return this.capacitance;
        } else {
            System.out.println("Class; LowPassPassive, method: getCapacitance");
            System.out.println("No capacitance has been entered; zero returned");
            return 0.0D;
        }
    }

    public double getTimeConstant() {
        return this.timeConstant;
    }

    protected void calcPolesZerosS() {
        super.sPoles[0].setReal(-this.timeConstant);
        super.sNumerScaleFactor = super.sNumer.coeffCopy(0);
        super.sDenomScaleFactor = BlackBox.scaleFactor(super.sDenom, super.sPoles);
    }

    public LowPassPassive copy() {
        if (this == null) {
            return null;
        } else {
            LowPassPassive var1 = new LowPassPassive();
            this.copyBBvariables(var1);
            var1.resistance = this.resistance;
            var1.capacitance = this.capacitance;
            var1.timeConstant = this.timeConstant;
            var1.setR = this.setR;
            var1.setC = this.setC;
            return var1;
        }
    }

    public Object clone() {
        return this.copy();
    }
}
