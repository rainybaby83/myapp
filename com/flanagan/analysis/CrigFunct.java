//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package flanagan.analysis;

import flanagan.integration.IntegralFunction;

class CrigFunct implements IntegralFunction {
    private double a = 0.0D;
    private double b = 0.0D;

    CrigFunct() {
    }

    @Override
    public double function(double var1) {
        double var3 = -var1 + (this.a - 1.0D) * Math.log(var1) - this.b;
        var3 = Math.exp(var3);
        return var3;
    }

    public void setA(double var1) {
        this.a = var1;
    }

    public void setB(double var1) {
        this.b = var1;
    }
}
