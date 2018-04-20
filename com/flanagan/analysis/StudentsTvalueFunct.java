//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package flanagan.analysis;

import flanagan.roots.RealRootFunction;

class StudentsTvalueFunct implements RealRootFunction {
    private int dof = 0;
    private double cfd = 0.0D;

    StudentsTvalueFunct() {
    }

    @Override
    public double function(double var1) {
        double var3 = this.cfd - Stat.studentstCDF(var1, this.dof);
        return var3;
    }

    public void setDof(int var1) {
        this.dof = var1;
    }

    public void setCfd(double var1) {
        this.cfd = var1;
    }
}
