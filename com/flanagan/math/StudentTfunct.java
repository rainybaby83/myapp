//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.math;

import com.flanagan.analysis.Stat;
import com.flanagan.roots.RealRootFunction;

class StudentTfunct implements RealRootFunction {
    public int nu = 0;
    public double cfd = 0.0D;

    StudentTfunct() {
    }

    public double function(double var1) {
        double var3 = this.cfd - Stat.studentTcdf(var1, this.nu);
        return var3;
    }
}
