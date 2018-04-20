//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.roots;

import com.flanagan.complex.Complex;
import com.flanagan.complex.ComplexPoly;
import com.flanagan.math.Fmath;
import java.util.ArrayList;

public class RealRoot {
    private double root = 0.0D / 0.0;
    private double tol = 1.0E-9D;
    private int iterMax = 3000;
    private int iterN = 0;
    private double upperBound = 0.0D;
    private double lowerBound = 0.0D;
    private double estimate = 0.0D;
    private int maximumBoundsExtension = 100;
    private boolean noBoundExtensions = false;
    private boolean noLowerBoundExtensions = false;
    private boolean noUpperBoundExtensions = false;
    private boolean suppressLimitReachedMessage = false;
    private boolean returnNaN = false;
    private boolean suppressNaNmessage = false;
    private static int staticIterMax = 3000;
    private static int maximumStaticBoundsExtension = 100;
    private static boolean noStaticBoundExtensions = false;
    private static boolean noStaticLowerBoundExtensions = false;
    private static boolean noStaticUpperBoundExtensions = false;
    private static boolean staticReturnNaN = false;
    private static double realTol = 1.0E-14D;

    public RealRoot() {
    }

    public void setLowerBound(double var1) {
        this.lowerBound = var1;
    }

    public void setUpperBound(double var1) {
        this.upperBound = var1;
    }

    public void resetNaNexceptionToTrue() {
        this.returnNaN = true;
    }

    public void resetNaNexceptionToFalse() {
        this.returnNaN = false;
    }

    public void suppressNaNmessage() {
        this.suppressNaNmessage = true;
    }

    public void supressNaNmessage() {
        this.suppressNaNmessage = true;
    }

    public void allowNaNmessage() {
        this.suppressNaNmessage = false;
    }

    public void setEstimate(double var1) {
        this.estimate = var1;
    }

    public void setTolerance(double var1) {
        this.tol = var1;
    }

    public double getTolerance() {
        return this.tol;
    }

    public void setIterMax(int var1) {
        this.iterMax = var1;
    }

    public int getIterMax() {
        return this.iterMax;
    }

    public int getIterN() {
        return this.iterN;
    }

    public void setmaximumStaticBoundsExtension(int var1) {
        this.maximumBoundsExtension = var1;
    }

    public void noBoundsExtensions() {
        this.noBoundExtensions = true;
        this.noLowerBoundExtensions = true;
        this.noUpperBoundExtensions = true;
    }

    public void noLowerBoundExtension() {
        this.noLowerBoundExtensions = true;
        if (this.noUpperBoundExtensions) {
            this.noBoundExtensions = true;
        }

    }

    public void noUpperBoundExtension() {
        this.noUpperBoundExtensions = true;
        if (this.noLowerBoundExtensions) {
            this.noBoundExtensions = true;
        }

    }

    public void suppressLimitReachedMessage() {
        this.suppressLimitReachedMessage = true;
    }

    public void supressLimitReachedMessage() {
        this.suppressLimitReachedMessage = true;
    }

    public double brent(RealRootFunction var1) {
        return this.brent(var1, this.lowerBound, this.upperBound);
    }

    public double brent(RealRootFunction var1, double var2, double var4) {
        this.lowerBound = var2;
        this.upperBound = var4;
        if (var4 == var2) {
            throw new IllegalArgumentException("upper cannot equal lower");
        } else {
            boolean var6 = true;
            this.iterN = 0;
            double var7 = 0.0D;
            if (var4 < var2) {
                var7 = var4;
                var4 = var2;
                var2 = var7;
            }

            double var9 = var1.function(var4);
            double var11 = var1.function(var2);
            if (Double.isNaN(var11)) {
                if (this.returnNaN) {
                    if (!this.suppressNaNmessage) {
                        System.out.println("Realroot: brent: lower bound returned NaN as the function value - NaN returned as root");
                    }

                    return 0.0D / 0.0;
                } else {
                    throw new ArithmeticException("lower bound returned NaN as the function value");
                }
            } else if (Double.isNaN(var9)) {
                if (this.returnNaN) {
                    if (!this.suppressNaNmessage) {
                        System.out.println("Realroot: brent: upper bound returned NaN as the function value - NaN returned as root");
                    }

                    return 0.0D / 0.0;
                } else {
                    throw new ArithmeticException("upper bound returned NaN as the function value");
                }
            } else {
                boolean var13 = true;
                int var14 = 0;
                double var15 = (var4 - var2) / 2.0D;

                while(var13) {
                    if (var9 * var11 <= 0.0D) {
                        var13 = false;
                    } else {
                        String var17;
                        if (this.noBoundExtensions) {
                            var17 = "RealRoot.brent: root not bounded and no extension to bounds allowed\n";
                            var17 = var17 + "NaN returned";
                            if (!this.suppressNaNmessage) {
                                System.out.println(var17);
                            }

                            return 0.0D / 0.0;
                        }

                        ++var14;
                        if (var14 > this.maximumBoundsExtension) {
                            var17 = "RealRoot.brent: root not bounded and maximum number of extension to bounds allowed, " + this.maximumBoundsExtension + ", exceeded\n";
                            var17 = var17 + "NaN returned";
                            if (!this.suppressNaNmessage) {
                                System.out.println(var17);
                            }

                            return 0.0D / 0.0;
                        }

                        if (!this.noLowerBoundExtensions) {
                            var2 -= var15;
                            var11 = var1.function(var2);
                        }

                        if (!this.noUpperBoundExtensions) {
                            var4 += var15;
                            var9 = var1.function(var4);
                        }
                    }
                }

                if (var11 == 0.0D) {
                    this.root = var2;
                    var6 = false;
                }

                if (var9 == 0.0D) {
                    this.root = var4;
                    var6 = false;
                }

                double var41 = (var2 + var4) / 2.0D;
                double var19 = var41;
                double var21 = var1.function(var41);
                double var23 = var41 - var2;
                double var25 = var21;
                double var27 = var41;
                boolean var29 = true;
                boolean var30 = true;
                double var31 = 0.0D;
                double var33 = 0.0D;
                double var35 = 0.0D;
                double var37 = 0.0D;
                double var39 = 0.0D;

                while(var6) {
                    if (var21 != 0.0D && Math.abs(var23) >= this.tol) {
                        if (var30) {
                            if (var41 >= var2 && var41 <= var4) {
                                var25 = var21;
                                var19 = var41;
                            } else {
                                var30 = false;
                            }
                        } else {
                            var30 = true;
                        }

                        if (var30) {
                            var11 = var1.function(var2);
                            var21 = var1.function(var41);
                            var9 = var1.function(var4);
                            var31 = var21 / var9;
                            var33 = var21 / var11;
                            var35 = var11 / var9;
                            var37 = var33 * (var35 * (var31 - var35) * (var4 - var41) - (1.0D - var31) * (var41 - var2));
                            var39 = (var35 - 1.0D) * (var31 - 1.0D) * (var33 - 1.0D);
                            var27 = var41;
                            var23 = var37 / var39;
                            var41 += var23;
                        } else {
                            if (var25 * var11 > 0.0D) {
                                var2 = var19;
                                var11 = var25;
                            } else {
                                var4 = var19;
                            }

                            var27 = var19;
                            var41 = (var2 + var4) / 2.0D;
                            var21 = var1.function(var41);
                            var23 = var41 - var19;
                            var25 = var21;
                            var19 = var41;
                        }
                    } else {
                        var6 = false;
                        if (var21 == 0.0D) {
                            this.root = var27;
                        } else if (Math.abs(var23) < this.tol) {
                            this.root = var41;
                        }
                    }

                    ++this.iterN;
                    if (this.iterN > this.iterMax) {
                        if (!this.suppressLimitReachedMessage) {
                            if (!this.suppressNaNmessage) {
                                System.out.println("Class: RealRoot; method: brent; maximum number of iterations exceeded - root at this point, " + Fmath.truncate(var41, 4) + ", returned");
                            }

                            if (!this.suppressNaNmessage) {
                                System.out.println("Last mid-point difference = " + Fmath.truncate(var23, 4) + ", tolerance = " + this.tol);
                            }
                        }

                        this.root = var41;
                        var6 = false;
                    }
                }

                return this.root;
            }
        }
    }

    public double bisect(RealRootFunction var1) {
        return this.bisect(var1, this.lowerBound, this.upperBound);
    }

    public double bisect(RealRootFunction var1, double var2, double var4) {
        this.lowerBound = var2;
        this.upperBound = var4;
        if (var4 == var2) {
            throw new IllegalArgumentException("upper cannot equal lower");
        } else {
            if (var4 < var2) {
                double var6 = var4;
                var4 = var2;
                var2 = var6;
            }

            boolean var23 = true;
            this.iterN = 0;
            double var7 = 1.0E300D;
            double var9 = var1.function(var4);
            double var11 = var1.function(var2);
            if (Double.isNaN(var11)) {
                if (this.returnNaN) {
                    if (!this.suppressNaNmessage) {
                        System.out.println("RealRoot: bisect: lower bound returned NaN as the function value - NaN returned as root");
                    }

                    return 0.0D / 0.0;
                } else {
                    throw new ArithmeticException("lower bound returned NaN as the function value");
                }
            } else if (Double.isNaN(var9)) {
                if (this.returnNaN) {
                    if (!this.suppressNaNmessage) {
                        System.out.println("RealRoot: bisect: upper bound returned NaN as the function value - NaN returned as root");
                    }

                    return 0.0D / 0.0;
                } else {
                    throw new ArithmeticException("upper bound returned NaN as the function value");
                }
            } else {
                boolean var13 = true;
                int var14 = 0;
                double var15 = (var4 - var2) / 2.0D;

                while(var13) {
                    if (var9 * var11 <= 0.0D) {
                        var13 = false;
                    } else {
                        String var17;
                        if (this.noBoundExtensions) {
                            var17 = "RealRoot.bisect: root not bounded and no extension to bounds allowed\n";
                            var17 = var17 + "NaN returned";
                            if (!this.suppressNaNmessage) {
                                System.out.println(var17);
                            }

                            return 0.0D / 0.0;
                        }

                        ++var14;
                        if (var14 > this.maximumBoundsExtension) {
                            var17 = "RealRoot.bisect: root not bounded and maximum number of extension to bounds allowed, " + this.maximumBoundsExtension + ", exceeded\n";
                            var17 = var17 + "NaN returned";
                            if (!this.suppressNaNmessage) {
                                System.out.println(var17);
                            }

                            return 0.0D / 0.0;
                        }

                        if (!this.noLowerBoundExtensions) {
                            var2 -= var15;
                            var11 = var1.function(var2);
                        }

                        if (!this.noUpperBoundExtensions) {
                            var4 += var15;
                            var9 = var1.function(var4);
                        }
                    }
                }

                if (var11 == 0.0D) {
                    this.root = var2;
                    var23 = false;
                }

                if (var9 == 0.0D) {
                    this.root = var4;
                    var23 = false;
                }

                double var24 = (var2 + var4) / 2.0D;
                double var19 = 1.0E300D;
                double var21 = var1.function(var24);

                while(var23) {
                    if (var21 == 0.0D || var7 < this.tol) {
                        var23 = false;
                        this.root = var24;
                    }

                    if (var21 * var11 > 0.0D) {
                        var2 = var24;
                        var11 = var21;
                    } else {
                        var4 = var24;
                    }

                    var19 = var24;
                    var24 = (var2 + var4) / 2.0D;
                    var21 = var1.function(var24);
                    var7 = Math.abs(var24 - var19);
                    ++this.iterN;
                    if (this.iterN > this.iterMax) {
                        if (!this.suppressLimitReachedMessage) {
                            if (!this.suppressNaNmessage) {
                                System.out.println("Class: RealRoot; method: bisect; maximum number of iterations exceeded - root at this point, " + Fmath.truncate(var24, 4) + ", returned");
                            }

                            if (!this.suppressNaNmessage) {
                                System.out.println("Last mid-point difference = " + Fmath.truncate(var7, 4) + ", tolerance = " + this.tol);
                            }
                        }

                        this.root = var24;
                        var23 = false;
                    }
                }

                return this.root;
            }
        }
    }

    public double falsePosition(RealRootFunction var1) {
        return this.falsePosition(var1, this.lowerBound, this.upperBound);
    }

    public double falsePosition(RealRootFunction var1, double var2, double var4) {
        this.lowerBound = var2;
        this.upperBound = var4;
        if (var4 == var2) {
            throw new IllegalArgumentException("upper cannot equal lower");
        } else {
            if (var4 < var2) {
                double var6 = var4;
                var4 = var2;
                var2 = var6;
            }

            boolean var23 = true;
            this.iterN = 0;
            double var7 = 1.0E300D;
            double var9 = var1.function(var4);
            double var11 = var1.function(var2);
            if (Double.isNaN(var11)) {
                if (this.returnNaN) {
                    if (!this.suppressNaNmessage) {
                        System.out.println("RealRoot: fals: ePositionlower bound returned NaN as the function value - NaN returned as root");
                    }

                    return 0.0D / 0.0;
                } else {
                    throw new ArithmeticException("lower bound returned NaN as the function value");
                }
            } else if (Double.isNaN(var9)) {
                if (this.returnNaN) {
                    if (!this.suppressNaNmessage) {
                        System.out.println("RealRoot: falsePosition: upper bound returned NaN as the function value - NaN returned as root");
                    }

                    return 0.0D / 0.0;
                } else {
                    throw new ArithmeticException("upper bound returned NaN as the function value");
                }
            } else {
                boolean var13 = true;
                int var14 = 0;
                double var15 = (var4 - var2) / 2.0D;

                while(var13) {
                    if (var9 * var11 <= 0.0D) {
                        var13 = false;
                    } else {
                        String var17;
                        if (this.noBoundExtensions) {
                            var17 = "RealRoot.falsePosition: root not bounded and no extension to bounds allowed\n";
                            var17 = var17 + "NaN returned";
                            if (!this.suppressNaNmessage) {
                                System.out.println(var17);
                            }

                            return 0.0D / 0.0;
                        }

                        ++var14;
                        if (var14 > this.maximumBoundsExtension) {
                            var17 = "RealRoot.falsePosition: root not bounded and maximum number of extension to bounds allowed, " + this.maximumBoundsExtension + ", exceeded\n";
                            var17 = var17 + "NaN returned";
                            if (!this.suppressNaNmessage) {
                                System.out.println(var17);
                            }

                            return 0.0D / 0.0;
                        }

                        if (!this.noLowerBoundExtensions) {
                            var2 -= var15;
                            var11 = var1.function(var2);
                        }

                        if (!this.noUpperBoundExtensions) {
                            var4 += var15;
                            var9 = var1.function(var4);
                        }
                    }
                }

                if (var11 == 0.0D) {
                    this.root = var2;
                    var23 = false;
                }

                if (var9 == 0.0D) {
                    this.root = var4;
                    var23 = false;
                }

                double var24 = var2 + (var4 - var2) * Math.abs(var11) / (Math.abs(var11) + Math.abs(var9));
                double var19 = 1.0E300D;
                double var21 = var1.function(var24);

                while(var23) {
                    if (var21 == 0.0D || var7 < this.tol) {
                        var23 = false;
                        this.root = var24;
                    }

                    if (var21 * var11 > 0.0D) {
                        var2 = var24;
                        var11 = var21;
                    } else {
                        var4 = var24;
                        var9 = var21;
                    }

                    var19 = var24;
                    var24 = var2 + (var4 - var2) * Math.abs(var11) / (Math.abs(var11) + Math.abs(var9));
                    var21 = var1.function(var24);
                    var7 = Math.abs(var24 - var19);
                    ++this.iterN;
                    if (this.iterN > this.iterMax) {
                        if (!this.suppressLimitReachedMessage) {
                            if (!this.suppressNaNmessage) {
                                System.out.println("Class: RealRoot; method: falsePostion; maximum number of iterations exceeded - root at this point, " + Fmath.truncate(var24, 4) + ", returned");
                            }

                            if (!this.suppressNaNmessage) {
                                System.out.println("Last mid-point difference = " + Fmath.truncate(var7, 4) + ", tolerance = " + this.tol);
                            }
                        }

                        this.root = var24;
                        var23 = false;
                    }
                }

                return this.root;
            }
        }
    }

    public double bisectNewtonRaphson(RealRootDerivFunction var1) {
        return this.bisectNewtonRaphson(var1, this.lowerBound, this.upperBound);
    }

    public double bisectNewtonRaphson(RealRootDerivFunction var1, double var2, double var4) {
        this.lowerBound = var2;
        this.upperBound = var4;
        if (var4 == var2) {
            throw new IllegalArgumentException("upper cannot equal lower");
        } else {
            boolean var6 = true;
            this.iterN = 0;
            double var7 = 0.0D;
            if (var4 < var2) {
                var7 = var4;
                var4 = var2;
                var2 = var7;
            }

            double[] var9 = var1.function(var4);
            double var10 = var9[0];
            var9 = var1.function(var2);
            double var12 = var9[0];
            if (Double.isNaN(var12)) {
                if (this.returnNaN) {
                    if (!this.suppressNaNmessage) {
                        System.out.println("RealRoot: bisectNewtonRaphson: lower bound returned NaN as the function value - NaN returned as root");
                    }

                    return 0.0D / 0.0;
                } else {
                    throw new ArithmeticException("lower bound returned NaN as the function value");
                }
            } else if (Double.isNaN(var10)) {
                if (this.returnNaN) {
                    if (!this.suppressNaNmessage) {
                        System.out.println("RealRoot: bisectNewtonRaphson: upper bound returned NaN as the function value - NaN returned as root");
                    }

                    return 0.0D / 0.0;
                } else {
                    throw new ArithmeticException("upper bound returned NaN as the function value");
                }
            } else {
                boolean var14 = true;
                int var15 = 0;
                double var16 = (var4 - var2) / 2.0D;

                while(var14) {
                    if (var10 * var12 <= 0.0D) {
                        var14 = false;
                    } else {
                        String var18;
                        if (this.noBoundExtensions) {
                            var18 = "RealRoot.bisectNewtonRaphson: root not bounded and no extension to bounds allowed\n";
                            var18 = var18 + "NaN returned";
                            if (!this.suppressNaNmessage) {
                                System.out.println(var18);
                            }

                            return 0.0D / 0.0;
                        }

                        ++var15;
                        if (var15 > this.maximumBoundsExtension) {
                            var18 = "RealRoot.bisectNewtonRaphson: root not bounded and maximum number of extension to bounds allowed, " + this.maximumBoundsExtension + ", exceeded\n";
                            var18 = var18 + "NaN returned";
                            if (!this.suppressNaNmessage) {
                                System.out.println(var18);
                            }

                            return 0.0D / 0.0;
                        }

                        if (!this.noLowerBoundExtensions) {
                            var2 -= var16;
                            var9 = var1.function(var2);
                            var12 = var9[0];
                        }

                        if (!this.noUpperBoundExtensions) {
                            var4 += var16;
                            var9 = var1.function(var4);
                            var10 = var9[0];
                        }
                    }
                }

                if (var12 == 0.0D) {
                    this.root = var2;
                    var6 = false;
                }

                if (var10 == 0.0D) {
                    this.root = var4;
                    var6 = false;
                }

                double var32 = (var2 + var4) / 2.0D;
                double var20 = var32;
                var9 = var1.function(var32);
                double var22 = var9[0] / var9[1];
                double var24 = var9[0];
                double var26 = var24;
                double var28 = var32;
                var32 -= var22;
                boolean var30 = true;
                boolean var31 = true;

                while(var6) {
                    if (var24 != 0.0D && Math.abs(var22) >= this.tol) {
                        if (var31) {
                            if (var32 >= var2 && var32 <= var4) {
                                var26 = var24;
                                var20 = var32;
                            } else {
                                var31 = false;
                            }
                        } else {
                            var31 = true;
                        }

                        if (var31) {
                            var9 = var1.function(var32);
                            var24 = var9[0];
                            var22 = var9[0] / var9[1];
                            var28 = var32;
                            var32 -= var22;
                        } else {
                            if (var26 * var12 > 0.0D) {
                                var2 = var20;
                                var12 = var26;
                            } else {
                                var4 = var20;
                            }

                            var28 = var20;
                            var32 = (var2 + var4) / 2.0D;
                            var9 = var1.function(var32);
                            var24 = var9[0];
                            var22 = var32 - var20;
                            var26 = var24;
                            var20 = var32;
                        }
                    } else {
                        var6 = false;
                        if (var24 == 0.0D) {
                            this.root = var28;
                        } else if (Math.abs(var22) < this.tol) {
                            this.root = var32;
                        }
                    }

                    ++this.iterN;
                    if (this.iterN > this.iterMax) {
                        if (!this.suppressLimitReachedMessage) {
                            if (!this.suppressNaNmessage) {
                                System.out.println("Class: RealRoot; method: bisectNewtonRaphson; maximum number of iterations exceeded - root at this point, " + Fmath.truncate(var32, 4) + ", returned");
                            }

                            if (!this.suppressNaNmessage) {
                                System.out.println("Last mid-point difference = " + Fmath.truncate(var22, 4) + ", tolerance = " + this.tol);
                            }
                        }

                        this.root = var32;
                        var6 = false;
                    }
                }

                return this.root;
            }
        }
    }

    public double newtonRaphson(RealRootDerivFunction var1) {
        return this.newtonRaphson(var1, this.estimate);
    }

    public double newtonRaphson(RealRootDerivFunction var1, double var2) {
        this.estimate = var2;
        boolean var4 = true;
        this.iterN = 0;
        double var5 = 1.0E300D;
        double[] var7 = var1.function(var2);
        if (Double.isNaN(var7[0])) {
            if (this.returnNaN) {
                if (!this.suppressNaNmessage) {
                    System.out.println("RealRoot: newtonRaphson: NaN returned as the function value - NaN returned as root");
                }

                return 0.0D / 0.0;
            } else {
                throw new ArithmeticException("NaN returned as the function value");
            }
        } else if (Double.isNaN(var7[1])) {
            if (this.returnNaN) {
                if (!this.suppressNaNmessage) {
                    System.out.println("RealRoot: newtonRaphson: NaN returned as the derivative function value - NaN returned as root");
                }

                return 0.0D / 0.0;
            } else {
                throw new ArithmeticException("NaN returned as the derivative function value");
            }
        } else {
            while(var4) {
                var5 = var7[0] / var7[1];
                if (var7[0] != 0.0D && Math.abs(var5) >= this.tol) {
                    var2 -= var5;
                    var7 = var1.function(var2);
                    if (Double.isNaN(var7[0])) {
                        throw new ArithmeticException("NaN returned as the function value");
                    }

                    if (Double.isNaN(var7[1])) {
                        throw new ArithmeticException("NaN returned as the derivative function value");
                    }

                    if (Double.isNaN(var7[0])) {
                        if (this.returnNaN) {
                            if (!this.suppressNaNmessage) {
                                System.out.println("RealRoot: bisect: NaN as the function value - NaN returned as root");
                            }

                            return 0.0D / 0.0;
                        }

                        throw new ArithmeticException("NaN as the function value");
                    }

                    if (Double.isNaN(var7[1])) {
                        if (this.returnNaN) {
                            if (!this.suppressNaNmessage) {
                                System.out.println("NaN as the function value - NaN returned as root");
                            }

                            return 0.0D / 0.0;
                        }

                        throw new ArithmeticException("NaN as the function value");
                    }
                } else {
                    this.root = var2;
                    var4 = false;
                }

                ++this.iterN;
                if (this.iterN > this.iterMax) {
                    if (!this.suppressLimitReachedMessage) {
                        if (!this.suppressNaNmessage) {
                            System.out.println("Class: RealRoot; method: newtonRaphson; maximum number of iterations exceeded - root at this point, " + Fmath.truncate(var2, 4) + ", returned");
                        }

                        if (!this.suppressNaNmessage) {
                            System.out.println("Last mid-point difference = " + Fmath.truncate(var5, 4) + ", tolerance = " + this.tol);
                        }
                    }

                    this.root = var2;
                    var4 = false;
                }
            }

            return this.root;
        }
    }

    public void setStaticIterMax(int var1) {
        staticIterMax = var1;
    }

    public int getStaticIterMax() {
        return staticIterMax;
    }

    public void setStaticMaximumStaticBoundsExtension(int var1) {
        maximumStaticBoundsExtension = var1;
    }

    public void noStaticBoundsExtensions() {
        noStaticBoundExtensions = true;
        noStaticLowerBoundExtensions = true;
        noStaticUpperBoundExtensions = true;
    }

    public void noStaticLowerBoundExtension() {
        noStaticLowerBoundExtensions = true;
        if (noStaticUpperBoundExtensions) {
            noStaticBoundExtensions = true;
        }

    }

    public void noStaticUpperBoundExtension() {
        noStaticUpperBoundExtensions = true;
        if (noStaticLowerBoundExtensions) {
            noStaticBoundExtensions = true;
        }

    }

    public void resetStaticNaNexceptionToTrue() {
        staticReturnNaN = true;
    }

    public void resetStaticNaNexceptionToFalse() {
        staticReturnNaN = false;
    }

    public static double brent(RealRootFunction var0, double var1, double var3, double var5) {
        if (var3 == var1) {
            throw new IllegalArgumentException("upper cannot equal lower");
        } else {
            double var7 = 0.0D / 0.0;
            boolean var9 = true;
            int var10 = 0;
            double var11 = 0.0D;
            if (var3 < var1) {
                var11 = var3;
                var3 = var1;
                var1 = var11;
            }

            double var13 = var0.function(var3);
            double var15 = var0.function(var1);
            if (Double.isNaN(var15)) {
                if (staticReturnNaN) {
                    System.out.println("Realroot: brent: lower bound returned NaN as the function value - NaN returned as root");
                    return 0.0D / 0.0;
                } else {
                    throw new ArithmeticException("lower bound returned NaN as the function value");
                }
            } else if (Double.isNaN(var13)) {
                if (staticReturnNaN) {
                    System.out.println("Realroot: brent: upper bound returned NaN as the function value - NaN returned as root");
                    return 0.0D / 0.0;
                } else {
                    throw new ArithmeticException("upper bound returned NaN as the function value");
                }
            } else {
                boolean var17 = true;
                int var18 = 0;
                double var19 = (var3 - var1) / 2.0D;

                while(var17) {
                    if (var13 * var15 <= 0.0D) {
                        var17 = false;
                    } else {
                        String var21;
                        if (noStaticBoundExtensions) {
                            var21 = "RealRoot.brent: root not bounded and no extension to bounds allowed\n";
                            var21 = var21 + "NaN returned";
                            System.out.println(var21);
                            return 0.0D / 0.0;
                        }

                        ++var18;
                        if (var18 > maximumStaticBoundsExtension) {
                            var21 = "RealRoot.brent: root not bounded and maximum number of extension to bounds allowed, " + maximumStaticBoundsExtension + ", exceeded\n";
                            var21 = var21 + "NaN returned";
                            System.out.println(var21);
                            return 0.0D / 0.0;
                        }

                        if (!noStaticLowerBoundExtensions) {
                            var1 -= var19;
                            var15 = var0.function(var1);
                        }

                        if (!noStaticUpperBoundExtensions) {
                            var3 += var19;
                            var13 = var0.function(var3);
                        }
                    }
                }

                if (var15 == 0.0D) {
                    var7 = var1;
                    var9 = false;
                }

                if (var13 == 0.0D) {
                    var7 = var3;
                    var9 = false;
                }

                double var45 = (var1 + var3) / 2.0D;
                double var23 = var45;
                double var25 = var0.function(var45);
                double var27 = var45 - var1;
                double var29 = var25;
                double var31 = var45;
                boolean var33 = true;
                boolean var34 = true;
                double var35 = 0.0D;
                double var37 = 0.0D;
                double var39 = 0.0D;
                double var41 = 0.0D;
                double var43 = 0.0D;

                while(var9) {
                    if (var25 != 0.0D && Math.abs(var27) >= var5) {
                        if (var34) {
                            if (var45 >= var1 && var45 <= var3) {
                                var29 = var25;
                                var23 = var45;
                            } else {
                                var34 = false;
                            }
                        } else {
                            var34 = true;
                        }

                        if (var34) {
                            var15 = var0.function(var1);
                            var25 = var0.function(var45);
                            var13 = var0.function(var3);
                            var35 = var25 / var13;
                            var37 = var25 / var15;
                            var39 = var15 / var13;
                            var41 = var37 * (var39 * (var35 - var39) * (var3 - var45) - (1.0D - var35) * (var45 - var1));
                            var43 = (var39 - 1.0D) * (var35 - 1.0D) * (var37 - 1.0D);
                            var31 = var45;
                            var27 = var41 / var43;
                            var45 += var27;
                        } else {
                            if (var29 * var15 > 0.0D) {
                                var1 = var23;
                                var15 = var29;
                            } else {
                                var3 = var23;
                            }

                            var31 = var23;
                            var45 = (var1 + var3) / 2.0D;
                            var25 = var0.function(var45);
                            var27 = var45 - var23;
                            var29 = var25;
                            var23 = var45;
                        }
                    } else {
                        var9 = false;
                        if (var25 == 0.0D) {
                            var7 = var31;
                        } else if (Math.abs(var27) < var5) {
                            var7 = var45;
                        }
                    }

                    ++var10;
                    if (var10 > staticIterMax) {
                        System.out.println("Class: RealRoot; method: brent; maximum number of iterations exceeded - root at this point, " + Fmath.truncate(var45, 4) + ", returned");
                        System.out.println("Last mid-point difference = " + Fmath.truncate(var27, 4) + ", tolerance = " + var5);
                        var7 = var45;
                        var9 = false;
                    }
                }

                return var7;
            }
        }
    }

    public static double bisect(RealRootFunction var0, double var1, double var3, double var5) {
        if (var3 == var1) {
            throw new IllegalArgumentException("upper cannot equal lower");
        } else {
            double var7;
            if (var3 < var1) {
                var7 = var3;
                var3 = var1;
                var1 = var7;
            }

            var7 = 0.0D / 0.0;
            boolean var9 = true;
            int var10 = 0;
            double var11 = 1.0E300D;
            double var13 = var0.function(var3);
            double var15 = var0.function(var1);
            if (Double.isNaN(var15)) {
                if (staticReturnNaN) {
                    System.out.println("RealRoot: bisect: lower bound returned NaN as the function value - NaN returned as root");
                    return 0.0D / 0.0;
                } else {
                    throw new ArithmeticException("lower bound returned NaN as the function value");
                }
            } else if (Double.isNaN(var13)) {
                if (staticReturnNaN) {
                    System.out.println("RealRoot: bisect: upper bound returned NaN as the function value - NaN returned as root");
                    return 0.0D / 0.0;
                } else {
                    throw new ArithmeticException("upper bound returned NaN as the function value");
                }
            } else {
                boolean var17 = true;
                int var18 = 0;
                double var19 = (var3 - var1) / 2.0D;

                while(var17) {
                    if (var13 * var15 <= 0.0D) {
                        var17 = false;
                    } else {
                        String var21;
                        if (noStaticBoundExtensions) {
                            var21 = "RealRoot.bisect: root not bounded and no extension to bounds allowed\n";
                            var21 = var21 + "NaN returned";
                            System.out.println(var21);
                            return 0.0D / 0.0;
                        }

                        ++var18;
                        if (var18 > maximumStaticBoundsExtension) {
                            var21 = "RealRoot.bisect: root not bounded and maximum number of extension to bounds allowed, " + maximumStaticBoundsExtension + ", exceeded\n";
                            var21 = var21 + "NaN returned";
                            System.out.println(var21);
                            return 0.0D / 0.0;
                        }

                        if (!noStaticLowerBoundExtensions) {
                            var1 -= var19;
                            var15 = var0.function(var1);
                        }

                        if (!noStaticUpperBoundExtensions) {
                            var3 += var19;
                            var13 = var0.function(var3);
                        }
                    }
                }

                if (var15 == 0.0D) {
                    var7 = var1;
                    var9 = false;
                }

                if (var13 == 0.0D) {
                    var7 = var3;
                    var9 = false;
                }

                double var27 = (var1 + var3) / 2.0D;
                double var23 = 1.0E300D;
                double var25 = var0.function(var27);

                while(var9) {
                    if (var25 == 0.0D || var11 < var5) {
                        var9 = false;
                        var7 = var27;
                    }

                    if (var25 * var15 > 0.0D) {
                        var1 = var27;
                        var15 = var25;
                    } else {
                        var3 = var27;
                    }

                    var23 = var27;
                    var27 = (var1 + var3) / 2.0D;
                    var25 = var0.function(var27);
                    var11 = Math.abs(var27 - var23);
                    ++var10;
                    if (var10 > staticIterMax) {
                        System.out.println("Class: RealRoot; method: bisect; maximum number of iterations exceeded - root at this point, " + Fmath.truncate(var27, 4) + ", returned");
                        System.out.println("Last mid-point difference = " + Fmath.truncate(var11, 4) + ", tolerance = " + var5);
                        var7 = var27;
                        var9 = false;
                    }
                }

                return var7;
            }
        }
    }

    public static double falsePosition(RealRootFunction var0, double var1, double var3, double var5) {
        if (var3 == var1) {
            throw new IllegalArgumentException("upper cannot equal lower");
        } else {
            double var7;
            if (var3 < var1) {
                var7 = var3;
                var3 = var1;
                var1 = var7;
            }

            var7 = 0.0D / 0.0;
            boolean var9 = true;
            int var10 = 0;
            double var11 = 1.0E300D;
            double var13 = var0.function(var3);
            double var15 = var0.function(var1);
            if (Double.isNaN(var15)) {
                if (staticReturnNaN) {
                    System.out.println("RealRoot: fals: ePositionlower bound returned NaN as the function value - NaN returned as root");
                    return 0.0D / 0.0;
                } else {
                    throw new ArithmeticException("lower bound returned NaN as the function value");
                }
            } else if (Double.isNaN(var13)) {
                if (staticReturnNaN) {
                    System.out.println("RealRoot: falsePosition: upper bound returned NaN as the function value - NaN returned as root");
                    return 0.0D / 0.0;
                } else {
                    throw new ArithmeticException("upper bound returned NaN as the function value");
                }
            } else {
                boolean var17 = true;
                int var18 = 0;
                double var19 = (var3 - var1) / 2.0D;

                while(var17) {
                    if (var13 * var15 <= 0.0D) {
                        var17 = false;
                    } else {
                        String var21;
                        if (noStaticBoundExtensions) {
                            var21 = "RealRoot.falsePosition: root not bounded and no extension to bounds allowed\n";
                            var21 = var21 + "NaN returned";
                            System.out.println(var21);
                            return 0.0D / 0.0;
                        }

                        ++var18;
                        if (var18 > maximumStaticBoundsExtension) {
                            var21 = "RealRoot.falsePosition: root not bounded and maximum number of extension to bounds allowed, " + maximumStaticBoundsExtension + ", exceeded\n";
                            var21 = var21 + "NaN returned";
                            System.out.println(var21);
                            return 0.0D / 0.0;
                        }

                        if (!noStaticLowerBoundExtensions) {
                            var1 -= var19;
                            var15 = var0.function(var1);
                        }

                        if (!noStaticUpperBoundExtensions) {
                            var3 += var19;
                            var13 = var0.function(var3);
                        }
                    }
                }

                if (var15 == 0.0D) {
                    var7 = var1;
                    var9 = false;
                }

                if (var13 == 0.0D) {
                    var7 = var3;
                    var9 = false;
                }

                double var27 = var1 + (var3 - var1) * Math.abs(var15) / (Math.abs(var15) + Math.abs(var13));
                double var23 = 1.0E300D;
                double var25 = var0.function(var27);

                while(var9) {
                    if (var25 == 0.0D || var11 < var5) {
                        var9 = false;
                        var7 = var27;
                    }

                    if (var25 * var15 > 0.0D) {
                        var1 = var27;
                        var15 = var25;
                    } else {
                        var3 = var27;
                        var13 = var25;
                    }

                    var23 = var27;
                    var27 = var1 + (var3 - var1) * Math.abs(var15) / (Math.abs(var15) + Math.abs(var13));
                    var25 = var0.function(var27);
                    var11 = Math.abs(var27 - var23);
                    ++var10;
                    if (var10 > staticIterMax) {
                        System.out.println("Class: RealRoot; method: falsePostion; maximum number of iterations exceeded - root at this point, " + Fmath.truncate(var27, 4) + ", returned");
                        System.out.println("Last mid-point difference = " + Fmath.truncate(var11, 4) + ", tolerance = " + var5);
                        var7 = var27;
                        var9 = false;
                    }
                }

                return var7;
            }
        }
    }

    public static double bisectNewtonRaphson(RealRootDerivFunction var0, double var1, double var3, double var5) {
        if (var3 == var1) {
            throw new IllegalArgumentException("upper cannot equal lower");
        } else {
            double var7 = 0.0D / 0.0;
            boolean var9 = true;
            int var10 = 0;
            double var11 = 0.0D;
            if (var3 < var1) {
                var11 = var3;
                var3 = var1;
                var1 = var11;
            }

            double[] var13 = var0.function(var3);
            double var14 = var13[0];
            var13 = var0.function(var1);
            double var16 = var13[0];
            if (Double.isNaN(var16)) {
                if (staticReturnNaN) {
                    System.out.println("RealRoot: bisectNewtonRaphson: lower bound returned NaN as the function value - NaN returned as root");
                    return 0.0D / 0.0;
                } else {
                    throw new ArithmeticException("lower bound returned NaN as the function value");
                }
            } else if (Double.isNaN(var14)) {
                if (staticReturnNaN) {
                    System.out.println("RealRoot: bisectNewtonRaphson: upper bound returned NaN as the function value - NaN returned as root");
                    return 0.0D / 0.0;
                } else {
                    throw new ArithmeticException("upper bound returned NaN as the function value");
                }
            } else {
                boolean var18 = true;
                int var19 = 0;
                double var20 = (var3 - var1) / 2.0D;

                while(var18) {
                    if (var14 * var16 <= 0.0D) {
                        var18 = false;
                    } else {
                        String var22;
                        if (noStaticBoundExtensions) {
                            var22 = "RealRoot.bisectNewtonRaphson: root not bounded and no extension to bounds allowed\n";
                            var22 = var22 + "NaN returned";
                            System.out.println(var22);
                            return 0.0D / 0.0;
                        }

                        ++var19;
                        if (var19 > maximumStaticBoundsExtension) {
                            var22 = "RealRoot.bisectNewtonRaphson: root not bounded and maximum number of extension to bounds allowed, " + maximumStaticBoundsExtension + ", exceeded\n";
                            var22 = var22 + "NaN returned";
                            System.out.println(var22);
                            return 0.0D / 0.0;
                        }

                        if (!noStaticLowerBoundExtensions) {
                            var1 -= var20;
                            var13 = var0.function(var1);
                            var16 = var13[0];
                        }

                        if (!noStaticUpperBoundExtensions) {
                            var3 += var20;
                            var13 = var0.function(var3);
                            var14 = var13[0];
                        }
                    }
                }

                if (var16 == 0.0D) {
                    var7 = var1;
                    var9 = false;
                }

                if (var14 == 0.0D) {
                    var7 = var3;
                    var9 = false;
                }

                double var36 = (var1 + var3) / 2.0D;
                double var24 = var36;
                var13 = var0.function(var36);
                double var26 = var13[0] / var13[1];
                double var28 = var13[0];
                double var30 = var28;
                double var32 = var36;
                var36 -= var26;
                boolean var34 = true;
                boolean var35 = true;

                while(var9) {
                    if (var28 != 0.0D && Math.abs(var26) >= var5) {
                        if (var35) {
                            if (var36 >= var1 && var36 <= var3) {
                                var30 = var28;
                                var24 = var36;
                            } else {
                                var35 = false;
                            }
                        } else {
                            var35 = true;
                        }

                        if (var35) {
                            var13 = var0.function(var36);
                            var28 = var13[0];
                            var26 = var13[0] / var13[1];
                            var32 = var36;
                            var36 -= var26;
                        } else {
                            if (var30 * var16 > 0.0D) {
                                var1 = var24;
                                var16 = var30;
                            } else {
                                var3 = var24;
                            }

                            var32 = var24;
                            var36 = (var1 + var3) / 2.0D;
                            var13 = var0.function(var36);
                            var28 = var13[0];
                            var26 = var36 - var24;
                            var30 = var28;
                            var24 = var36;
                        }
                    } else {
                        var9 = false;
                        if (var28 == 0.0D) {
                            var7 = var32;
                        } else if (Math.abs(var26) < var5) {
                            var7 = var36;
                        }
                    }

                    ++var10;
                    if (var10 > staticIterMax) {
                        System.out.println("Class: RealRoot; method: bisectNewtonRaphson; maximum number of iterations exceeded - root at this point, " + Fmath.truncate(var36, 4) + ", returned");
                        System.out.println("Last mid-point difference = " + Fmath.truncate(var26, 4) + ", tolerance = " + var5);
                        var7 = var36;
                        var9 = false;
                    }
                }

                return var7;
            }
        }
    }

    public static double newtonRaphson(RealRootDerivFunction var0, double var1, double var3) {
        double var5 = 0.0D / 0.0;
        boolean var7 = true;
        int var8 = 0;
        double var9 = 1.0E300D;
        double[] var11 = var0.function(var1);
        if (Double.isNaN(var11[0])) {
            if (staticReturnNaN) {
                System.out.println("RealRoot: newtonRaphson: NaN returned as the function value - NaN returned as root");
                return 0.0D / 0.0;
            } else {
                throw new ArithmeticException("NaN returned as the function value");
            }
        } else if (Double.isNaN(var11[1])) {
            if (staticReturnNaN) {
                System.out.println("RealRoot: newtonRaphson: NaN returned as the derivative function value - NaN returned as root");
                return 0.0D / 0.0;
            } else {
                throw new ArithmeticException("NaN returned as the derivative function value");
            }
        } else {
            while(var7) {
                var9 = var11[0] / var11[1];
                if (var11[0] != 0.0D && Math.abs(var9) >= var3) {
                    var1 -= var9;
                    var11 = var0.function(var1);
                    if (Double.isNaN(var11[0])) {
                        throw new ArithmeticException("NaN returned as the function value");
                    }

                    if (Double.isNaN(var11[1])) {
                        throw new ArithmeticException("NaN returned as the derivative function value");
                    }

                    if (Double.isNaN(var11[0])) {
                        if (staticReturnNaN) {
                            System.out.println("RealRoot: NewtonRaphson: NaN as the function value - NaN returned as root");
                            return 0.0D / 0.0;
                        }

                        throw new ArithmeticException("NaN as the function value");
                    }

                    if (Double.isNaN(var11[1])) {
                        if (staticReturnNaN) {
                            System.out.println("NaN as the function value - NaN returned as root");
                            return 0.0D / 0.0;
                        }

                        throw new ArithmeticException("NaN as the function value");
                    }
                } else {
                    var5 = var1;
                    var7 = false;
                }

                ++var8;
                if (var8 > staticIterMax) {
                    System.out.println("Class: RealRoot; method: newtonRaphson; maximum number of iterations exceeded - root at this point, " + Fmath.truncate(var1, 4) + ", returned");
                    System.out.println("Last mid-point difference = " + Fmath.truncate(var9, 4) + ", tolerance = " + var3);
                    var5 = var1;
                    var7 = false;
                }
            }

            return var5;
        }
    }

    public static ArrayList<Object> quadratic(double var0, double var2, double var4) {
        ArrayList var6 = new ArrayList(2);
        double var7 = var2 * var2;
        double var9 = 4.0D * var4 * var0;
        if (var7 < var9) {
            Complex[] var11 = ComplexPoly.quadratic(var0, var2, var4);
            var6.add("complex");
            var6.add(var11);
        } else {
            double[] var16 = new double[2];
            double var12 = Fmath.sign(var2);
            double var14 = Math.sqrt(var7 - var9);
            var14 = -0.5D * (var2 + var12 * var14);
            var16[0] = var14 / var4;
            var16[1] = var0 / var14;
            var6.add("real");
            var6.add(var16);
        }

        return var6;
    }

    public static ArrayList<Object> cubic(double var0, double var2, double var4, double var6) {
        ArrayList var8 = new ArrayList(2);
        double var9 = var4 / var6;
        double var11 = var2 / var6;
        double var13 = var0 / var6;
        double var15 = (var9 * var9 - 3.0D * var11) / 9.0D;
        double var17 = var15 * var15 * var15;
        double var19 = (2.0D * var9 * var9 * var9 - 9.0D * var9 * var11 + 27.0D * var13) / 54.0D;
        double var21 = var19 * var19;
        if (var21 >= var17) {
            Complex[] var23 = ComplexPoly.cubic(var0, var2, var4, var6);
            var8.add("complex");
            var8.add(var23);
        } else {
            double[] var30 = new double[3];
            double var24 = Math.acos(var19 / Math.sqrt(var17));
            double var26 = var9 / 3.0D;
            double var28 = -2.0D * Math.sqrt(var15);
            var30[0] = var28 * Math.cos(var24 / 3.0D) - var26;
            var30[1] = var28 * Math.cos((var24 + 6.283185307179586D) / 3.0D) - var26;
            var30[2] = var28 * Math.cos((var24 - 6.283185307179586D) / 3.0D) - var26;
            var8.add("real");
            var8.add(var30);
        }

        return var8;
    }

    public static ArrayList<Object> polynomial(double[] var0) {
        boolean var1 = true;
        double var2 = 0.0D;
        return polynomial(var0, var1, var2);
    }

    public static ArrayList<Object> polynomial(double[] var0, boolean var1) {
        double var2 = 0.0D;
        return polynomial(var0, var1, var2);
    }

    public static ArrayList<Object> polynomial(double[] var0, double var1) {
        boolean var3 = true;
        return polynomial(var0, var3, var1);
    }

    public static ArrayList<Object> polynomial(double[] var0, boolean var1, double var2) {
        int var4 = var0.length;
        if (var4 < 2) {
            throw new IllegalArgumentException("a minimum of two coefficients is required");
        } else {
            ArrayList var5 = new ArrayList(var4);
            boolean var6 = true;
            int var7 = 0;
            int var8 = 0;
            boolean var9 = true;

            while(var9) {
                if (var0[var8] == 0.0D) {
                    ++var7;
                    ++var8;
                } else {
                    var9 = false;
                }
            }

            int var10 = var4 - var7;
            double[] var11 = new double[var10];
            int var12;
            if (var7 > 0) {
                for(var12 = 0; var12 < var10; ++var12) {
                    var11[var12] = var0[var12 + var7];
                }
            } else {
                for(var12 = 0; var12 < var10; ++var12) {
                    var11[var12] = var0[var12];
                }
            }

            ArrayList var19 = new ArrayList(2);
            Object var13 = null;
            double[] var14;
            switch(var10) {
                case 0:
                case 1:
                    break;
                case 2:
                    var19.add("real");
                    var14 = new double[]{-var11[0] / var11[1]};
                    var19.add(var14);
                    break;
                case 3:
                    var19 = quadratic(var11[0], var11[1], var11[2]);
                    if (((String)var19.get(0)).equals("complex")) {
                        var6 = false;
                    }
                    break;
                case 4:
                    var19 = cubic(var11[0], var11[1], var11[2], var11[3]);
                    if (((String)var19.get(0)).equals("complex")) {
                        var6 = false;
                    }
                    break;
                default:
                    ComplexPoly var15 = new ComplexPoly(var11);
                    Complex[] var16 = var15.roots(var1, new Complex(var2, 0.0D));
                    double[] var20 = new double[var10 - 1];
                    int var17 = 0;

                    for(int var18 = 0; var18 < var10 - 1; ++var18) {
                        if (var16[var18].getImag() / var16[var18].getReal() < realTol) {
                            var20[var18] = var16[var18].getReal();
                            ++var17;
                        }
                    }

                    if (var17 == var10 - 1) {
                        var19.add("real");
                        var19.add(var20);
                    } else {
                        var19.add("complex");
                        var19.add(var16);
                        var6 = false;
                    }
            }

            if (var7 == 0) {
                var5 = var19;
            } else {
                int var24;
                if (var6) {
                    var14 = new double[var4 - 1];
                    double[] var22 = (double[])((double[])var19.get(1));

                    for(var24 = 0; var24 < var10 - 1; ++var24) {
                        var14[var24] = var22[var24];
                    }

                    for(var24 = 0; var24 < var7; ++var24) {
                        var14[var24 + var10 - 1] = 0.0D;
                    }

                    var5.add("real");
                    var5.add(var14);
                } else {
                    Complex[] var21 = Complex.oneDarray(var4 - 1);
                    Complex[] var23 = (Complex[])((Complex[])var19.get(1));

                    for(var24 = 0; var24 < var10 - 1; ++var24) {
                        var21[var24] = var23[var24];
                    }

                    for(var24 = 0; var24 < var7; ++var24) {
                        var21[var24 + var10 - 1] = new Complex(0.0D, 0.0D);
                    }

                    var5.add("complex");
                    var5.add(var21);
                }
            }

            return var5;
        }
    }

    public void resetRealTest(double var1) {
        realTol = var1;
    }
}
