//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package flanagan.analysis;

class WeibullFunctionTwo implements RegressionFunction {
    private int typeFlag = 0;

    WeibullFunctionTwo() {
    }

    @Override
    public double function(double[] var1, double[] var2) {
        double var3 = 0.0D;
        switch(this.typeFlag) {
            case 0:
                var3 = var1[2] * Math.log(Math.abs(var2[0] - var1[0]) / var1[1]);
                break;
            case 1:
                var3 = var1[1] * Math.log(Math.abs(var2[0]) / var1[0]);
                break;
            case 2:
                var3 = var1[0] * Math.log(Math.abs(var2[0]));
        }

        return var3;
    }

    public void setTypeFlag(int var1) {
        this.typeFlag = var1;
    }
}
