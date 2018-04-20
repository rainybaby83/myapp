//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package flanagan.control;

public class DelayLine extends BlackBox {
    public DelayLine(double var1, int var3) {
        super("DelayLine");
        super.setDeadTime(var1, var3);
    }

    public DelayLine(double var1) {
        super("DelayLine");
        super.fixedName = "DelayLine";
        super.setDeadTime(var1);
    }

    private DelayLine() {
        super("DelayLine");
    }

    public void setDelayTime(double var1) {
        super.setDeadTime(var1);
    }

    public void setDelayTime(double var1, int var3) {
        super.setDeadTime(var1, var3);
    }

    public double getDelayTime() {
        return super.deadTime;
    }

    public DelayLine copy() {
        if (this == null) {
            return null;
        } else {
            DelayLine var1 = new DelayLine();
            this.copyBBvariables(var1);
            return var1;
        }
    }

    public Object clone() {
        return this.copy();
    }
}
