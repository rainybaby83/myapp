//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.math;

import com.flanagan.analysis.ErrorProp;
import com.flanagan.circuits.Phasor;
import com.flanagan.complex.Complex;
import com.flanagan.complex.ComplexErrorProp;
import com.flanagan.complex.ComplexMatrix;
import com.flanagan.complex.ComplexPoly;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;

public class DeepCopy {
    public DeepCopy() {
    }

    public static double[] copy(double[] var0) {
        if (var0 == null) {
            return null;
        } else {
            int var1 = var0.length;
            double[] var2 = new double[var1];

            for(int var3 = 0; var3 < var1; ++var3) {
                var2[var3] = var0[var3];
            }

            return var2;
        }
    }

    public static float[] copy(float[] var0) {
        if (var0 == null) {
            return null;
        } else {
            int var1 = var0.length;
            float[] var2 = new float[var1];

            for(int var3 = 0; var3 < var1; ++var3) {
                var2[var3] = var0[var3];
            }

            return var2;
        }
    }

    public static int[] copy(int[] var0) {
        if (var0 == null) {
            return null;
        } else {
            int var1 = var0.length;
            int[] var2 = new int[var1];

            for(int var3 = 0; var3 < var1; ++var3) {
                var2[var3] = var0[var3];
            }

            return var2;
        }
    }

    public static long[] copy(long[] var0) {
        if (var0 == null) {
            return null;
        } else {
            int var1 = var0.length;
            long[] var2 = new long[var1];

            for(int var3 = 0; var3 < var1; ++var3) {
                var2[var3] = var0[var3];
            }

            return var2;
        }
    }

    public static double[][] copy(double[][] var0) {
        if (var0 == null) {
            return (double[][])null;
        } else {
            int var1 = var0.length;
            double[][] var2 = new double[var1][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new double[var4];

                for(int var5 = 0; var5 < var4; ++var5) {
                    var2[var3][var5] = var0[var3][var5];
                }
            }

            return var2;
        }
    }

    public static float[][] copy(float[][] var0) {
        if (var0 == null) {
            return (float[][])null;
        } else {
            int var1 = var0.length;
            float[][] var2 = new float[var1][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new float[var4];

                for(int var5 = 0; var5 < var4; ++var5) {
                    var2[var3][var5] = var0[var3][var5];
                }
            }

            return var2;
        }
    }

    public static int[][] copy(int[][] var0) {
        if (var0 == null) {
            return (int[][])null;
        } else {
            int var1 = var0.length;
            int[][] var2 = new int[var1][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new int[var4];

                for(int var5 = 0; var5 < var4; ++var5) {
                    var2[var3][var5] = var0[var3][var5];
                }
            }

            return var2;
        }
    }

    public static long[][] copy(long[][] var0) {
        if (var0 == null) {
            return (long[][])null;
        } else {
            int var1 = var0.length;
            long[][] var2 = new long[var1][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new long[var4];

                for(int var5 = 0; var5 < var4; ++var5) {
                    var2[var3][var5] = var0[var3][var5];
                }
            }

            return var2;
        }
    }

    public static double[][][] copy(double[][][] var0) {
        if (var0 == null) {
            return (double[][][])null;
        } else {
            int var1 = var0.length;
            double[][][] var2 = new double[var1][][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new double[var4][];

                for(int var5 = 0; var5 < var4; ++var5) {
                    int var6 = var0[var3][var5].length;
                    var2[var3][var5] = new double[var6];

                    for(int var7 = 0; var7 < var6; ++var7) {
                        var2[var3][var5][var7] = var0[var3][var5][var7];
                    }
                }
            }

            return var2;
        }
    }

    public static float[][][] copy(float[][][] var0) {
        if (var0 == null) {
            return (float[][][])null;
        } else {
            int var1 = var0.length;
            float[][][] var2 = new float[var1][][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new float[var4][];

                for(int var5 = 0; var5 < var4; ++var5) {
                    int var6 = var0[var3][var5].length;
                    var2[var3][var5] = new float[var6];

                    for(int var7 = 0; var7 < var6; ++var7) {
                        var2[var3][var5][var7] = var0[var3][var5][var7];
                    }
                }
            }

            return var2;
        }
    }

    public static int[][][] copy(int[][][] var0) {
        if (var0 == null) {
            return (int[][][])null;
        } else {
            int var1 = var0.length;
            int[][][] var2 = new int[var1][][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new int[var4][];

                for(int var5 = 0; var5 < var4; ++var5) {
                    int var6 = var0[var3][var5].length;
                    var2[var3][var5] = new int[var6];

                    for(int var7 = 0; var7 < var6; ++var7) {
                        var2[var3][var5][var7] = var0[var3][var5][var7];
                    }
                }
            }

            return var2;
        }
    }

    public static long[][][] copy(long[][][] var0) {
        if (var0 == null) {
            return (long[][][])null;
        } else {
            int var1 = var0.length;
            long[][][] var2 = new long[var1][][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new long[var4][];

                for(int var5 = 0; var5 < var4; ++var5) {
                    int var6 = var0[var3][var5].length;
                    var2[var3][var5] = new long[var6];

                    for(int var7 = 0; var7 < var6; ++var7) {
                        var2[var3][var5][var7] = var0[var3][var5][var7];
                    }
                }
            }

            return var2;
        }
    }

    public static double[][][][] copy(double[][][][] var0) {
        if (var0 == null) {
            return (double[][][][])null;
        } else {
            int var1 = var0.length;
            double[][][][] var2 = new double[var1][][][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new double[var4][][];

                for(int var5 = 0; var5 < var4; ++var5) {
                    int var6 = var0[var3][var5].length;
                    var2[var3][var5] = new double[var6][];

                    for(int var7 = 0; var7 < var6; ++var7) {
                        int var8 = var0[var3][var5][var7].length;
                        var2[var3][var5][var7] = new double[var8];

                        for(int var9 = 0; var9 < var8; ++var9) {
                            var2[var3][var5][var7][var9] = var0[var3][var5][var7][var9];
                        }
                    }
                }
            }

            return var2;
        }
    }

    public static float[][][][] copy(float[][][][] var0) {
        if (var0 == null) {
            return (float[][][][])null;
        } else {
            int var1 = var0.length;
            float[][][][] var2 = new float[var1][][][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new float[var4][][];

                for(int var5 = 0; var5 < var4; ++var5) {
                    int var6 = var0[var3][var5].length;
                    var2[var3][var5] = new float[var6][];

                    for(int var7 = 0; var7 < var6; ++var7) {
                        int var8 = var0[var3][var5][var7].length;
                        var2[var3][var5][var7] = new float[var8];

                        for(int var9 = 0; var9 < var8; ++var9) {
                            var2[var3][var5][var7][var9] = var0[var3][var5][var7][var9];
                        }
                    }
                }
            }

            return var2;
        }
    }

    public static int[][][][] copy(int[][][][] var0) {
        if (var0 == null) {
            return (int[][][][])null;
        } else {
            int var1 = var0.length;
            int[][][][] var2 = new int[var1][][][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new int[var4][][];

                for(int var5 = 0; var5 < var4; ++var5) {
                    int var6 = var0[var3][var5].length;
                    var2[var3][var5] = new int[var6][];

                    for(int var7 = 0; var7 < var6; ++var7) {
                        int var8 = var0[var3][var5][var7].length;
                        var2[var3][var5][var7] = new int[var8];

                        for(int var9 = 0; var9 < var8; ++var9) {
                            var2[var3][var5][var7][var9] = var0[var3][var5][var7][var9];
                        }
                    }
                }
            }

            return var2;
        }
    }

    public static long[][][][] copy(long[][][][] var0) {
        if (var0 == null) {
            return (long[][][][])null;
        } else {
            int var1 = var0.length;
            long[][][][] var2 = new long[var1][][][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new long[var4][][];

                for(int var5 = 0; var5 < var4; ++var5) {
                    int var6 = var0[var3][var5].length;
                    var2[var3][var5] = new long[var6][];

                    for(int var7 = 0; var7 < var6; ++var7) {
                        int var8 = var0[var3][var5][var7].length;
                        var2[var3][var5][var7] = new long[var8];

                        for(int var9 = 0; var9 < var8; ++var9) {
                            var2[var3][var5][var7][var9] = var0[var3][var5][var7][var9];
                        }
                    }
                }
            }

            return var2;
        }
    }

    public static String[] copy(String[] var0) {
        if (var0 == null) {
            return null;
        } else {
            int var1 = var0.length;
            String[] var2 = new String[var1];

            for(int var3 = 0; var3 < var1; ++var3) {
                var2[var3] = var0[var3];
            }

            return var2;
        }
    }

    public static String[][] copy(String[][] var0) {
        if (var0 == null) {
            return (String[][])null;
        } else {
            int var1 = var0.length;
            String[][] var2 = new String[var1][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new String[var4];

                for(int var5 = 0; var5 < var4; ++var5) {
                    var2[var3][var5] = var0[var3][var5];
                }
            }

            return var2;
        }
    }

    public static String[][][] copy(String[][][] var0) {
        if (var0 == null) {
            return (String[][][])null;
        } else {
            int var1 = var0.length;
            String[][][] var2 = new String[var1][][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new String[var4][];

                for(int var5 = 0; var5 < var4; ++var5) {
                    int var6 = var0[var3][var5].length;
                    var2[var3][var5] = new String[var6];

                    for(int var7 = 0; var7 < var6; ++var7) {
                        var2[var3][var5][var7] = var0[var3][var5][var7];
                    }
                }
            }

            return var2;
        }
    }

    public static String[][][][] copy(String[][][][] var0) {
        if (var0 == null) {
            return (String[][][][])null;
        } else {
            int var1 = var0.length;
            String[][][][] var2 = new String[var1][][][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new String[var4][][];

                for(int var5 = 0; var5 < var4; ++var5) {
                    int var6 = var0[var3][var5].length;
                    var2[var3][var5] = new String[var6][];

                    for(int var7 = 0; var7 < var6; ++var7) {
                        int var8 = var0[var3][var5][var7].length;
                        var2[var3][var5][var7] = new String[var8];

                        for(int var9 = 0; var9 < var8; ++var9) {
                            var2[var3][var5][var7][var9] = var0[var3][var5][var7][var9];
                        }
                    }
                }
            }

            return var2;
        }
    }

    public static boolean[] copy(boolean[] var0) {
        if (var0 == null) {
            return null;
        } else {
            int var1 = var0.length;
            boolean[] var2 = new boolean[var1];

            for(int var3 = 0; var3 < var1; ++var3) {
                var2[var3] = var0[var3];
            }

            return var2;
        }
    }

    public static boolean[][] copy(boolean[][] var0) {
        if (var0 == null) {
            return (boolean[][])null;
        } else {
            int var1 = var0.length;
            boolean[][] var2 = new boolean[var1][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new boolean[var4];

                for(int var5 = 0; var5 < var4; ++var5) {
                    var2[var3][var5] = var0[var3][var5];
                }
            }

            return var2;
        }
    }

    public static boolean[][][] copy(boolean[][][] var0) {
        if (var0 == null) {
            return (boolean[][][])null;
        } else {
            int var1 = var0.length;
            boolean[][][] var2 = new boolean[var1][][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new boolean[var4][];

                for(int var5 = 0; var5 < var4; ++var5) {
                    int var6 = var0[var3][var5].length;
                    var2[var3][var5] = new boolean[var6];

                    for(int var7 = 0; var7 < var6; ++var7) {
                        var2[var3][var5][var7] = var0[var3][var5][var7];
                    }
                }
            }

            return var2;
        }
    }

    public static boolean[][][][] copy(boolean[][][][] var0) {
        if (var0 == null) {
            return (boolean[][][][])null;
        } else {
            int var1 = var0.length;
            boolean[][][][] var2 = new boolean[var1][][][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new boolean[var4][][];

                for(int var5 = 0; var5 < var4; ++var5) {
                    int var6 = var0[var3][var5].length;
                    var2[var3][var5] = new boolean[var6][];

                    for(int var7 = 0; var7 < var6; ++var7) {
                        int var8 = var0[var3][var5][var7].length;
                        var2[var3][var5][var7] = new boolean[var8];

                        for(int var9 = 0; var9 < var8; ++var9) {
                            var2[var3][var5][var7][var9] = var0[var3][var5][var7][var9];
                        }
                    }
                }
            }

            return var2;
        }
    }

    public static char[] copy(char[] var0) {
        if (var0 == null) {
            return null;
        } else {
            int var1 = var0.length;
            char[] var2 = new char[var1];

            for(int var3 = 0; var3 < var1; ++var3) {
                var2[var3] = var0[var3];
            }

            return var2;
        }
    }

    public static char[][] copy(char[][] var0) {
        if (var0 == null) {
            return (char[][])null;
        } else {
            int var1 = var0.length;
            char[][] var2 = new char[var1][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new char[var4];

                for(int var5 = 0; var5 < var4; ++var5) {
                    var2[var3][var5] = var0[var3][var5];
                }
            }

            return var2;
        }
    }

    public static char[][][] copy(char[][][] var0) {
        if (var0 == null) {
            return (char[][][])null;
        } else {
            int var1 = var0.length;
            char[][][] var2 = new char[var1][][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new char[var4][];

                for(int var5 = 0; var5 < var4; ++var5) {
                    int var6 = var0[var3][var5].length;
                    var2[var3][var5] = new char[var6];

                    for(int var7 = 0; var7 < var6; ++var7) {
                        var2[var3][var5][var7] = var0[var3][var5][var7];
                    }
                }
            }

            return var2;
        }
    }

    public static char[][][][] copy(char[][][][] var0) {
        if (var0 == null) {
            return (char[][][][])null;
        } else {
            int var1 = var0.length;
            char[][][][] var2 = new char[var1][][][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new char[var4][][];

                for(int var5 = 0; var5 < var4; ++var5) {
                    int var6 = var0[var3][var5].length;
                    var2[var3][var5] = new char[var6][];

                    for(int var7 = 0; var7 < var6; ++var7) {
                        int var8 = var0[var3][var5][var7].length;
                        var2[var3][var5][var7] = new char[var8];

                        for(int var9 = 0; var9 < var8; ++var9) {
                            var2[var3][var5][var7][var9] = var0[var3][var5][var7][var9];
                        }
                    }
                }
            }

            return var2;
        }
    }

    public static Character[] copy(Character[] var0) {
        if (var0 == null) {
            return null;
        } else {
            int var1 = var0.length;
            Character[] var2 = new Character[var1];

            for(int var3 = 0; var3 < var1; ++var3) {
                var2[var3] = var0[var3];
            }

            return var2;
        }
    }

    public static Character[][] copy(Character[][] var0) {
        if (var0 == null) {
            return (Character[][])null;
        } else {
            int var1 = var0.length;
            Character[][] var2 = new Character[var1][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new Character[var4];

                for(int var5 = 0; var5 < var4; ++var5) {
                    var2[var3][var5] = var0[var3][var5];
                }
            }

            return var2;
        }
    }

    public static Character[][][] copy(Character[][][] var0) {
        if (var0 == null) {
            return (Character[][][])null;
        } else {
            int var1 = var0.length;
            Character[][][] var2 = new Character[var1][][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new Character[var4][];

                for(int var5 = 0; var5 < var4; ++var5) {
                    int var6 = var0[var3][var5].length;
                    var2[var3][var5] = new Character[var6];

                    for(int var7 = 0; var7 < var6; ++var7) {
                        var2[var3][var5][var7] = var0[var3][var5][var7];
                    }
                }
            }

            return var2;
        }
    }

    public static Character[][][][] copy(Character[][][][] var0) {
        if (var0 == null) {
            return (Character[][][][])null;
        } else {
            int var1 = var0.length;
            Character[][][][] var2 = new Character[var1][][][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new Character[var4][][];

                for(int var5 = 0; var5 < var4; ++var5) {
                    int var6 = var0[var3][var5].length;
                    var2[var3][var5] = new Character[var6][];

                    for(int var7 = 0; var7 < var6; ++var7) {
                        int var8 = var0[var3][var5][var7].length;
                        var2[var3][var5][var7] = new Character[var8];

                        for(int var9 = 0; var9 < var8; ++var9) {
                            var2[var3][var5][var7][var9] = var0[var3][var5][var7][var9];
                        }
                    }
                }
            }

            return var2;
        }
    }

    public static Complex[] copy(Complex[] var0) {
        if (var0 == null) {
            return null;
        } else {
            int var1 = var0.length;
            Complex[] var2 = new Complex[var1];

            for(int var3 = 0; var3 < var1; ++var3) {
                var2[var3] = var0[var3].copy();
            }

            return var2;
        }
    }

    public static Complex[][] copy(Complex[][] var0) {
        if (var0 == null) {
            return (Complex[][])null;
        } else {
            int var1 = var0.length;
            Complex[][] var2 = new Complex[var1][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new Complex[var4];

                for(int var5 = 0; var5 < var4; ++var5) {
                    var2[var3][var5] = var0[var3][var5].copy();
                }
            }

            return var2;
        }
    }

    public static Complex[][][] copy(Complex[][][] var0) {
        if (var0 == null) {
            return (Complex[][][])null;
        } else {
            int var1 = var0.length;
            Complex[][][] var2 = new Complex[var1][][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new Complex[var4][];

                for(int var5 = 0; var5 < var4; ++var5) {
                    int var6 = var0[var3][var5].length;
                    var2[var3][var5] = new Complex[var6];

                    for(int var7 = 0; var7 < var6; ++var7) {
                        var2[var3][var5][var7] = var0[var3][var5][var7].copy();
                    }
                }
            }

            return var2;
        }
    }

    public static Complex[][][][] copy(Complex[][][][] var0) {
        if (var0 == null) {
            return (Complex[][][][])null;
        } else {
            int var1 = var0.length;
            Complex[][][][] var2 = new Complex[var1][][][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new Complex[var4][][];

                for(int var5 = 0; var5 < var4; ++var5) {
                    int var6 = var0[var3][var5].length;
                    var2[var3][var5] = new Complex[var6][];

                    for(int var7 = 0; var7 < var6; ++var7) {
                        int var8 = var0[var3][var5][var7].length;
                        var2[var3][var5][var7] = new Complex[var8];

                        for(int var9 = 0; var9 < var8; ++var9) {
                            var2[var3][var5][var7][var9] = var0[var3][var5][var7][var9].copy();
                        }
                    }
                }
            }

            return var2;
        }
    }

    public static ComplexPoly[] copy(ComplexPoly[] var0) {
        if (var0 == null) {
            return null;
        } else {
            int var1 = var0.length;
            ComplexPoly[] var2 = new ComplexPoly[var1];

            for(int var3 = 0; var3 < var1; ++var3) {
                var2[var3] = var0[var3].copy();
            }

            return var2;
        }
    }

    public static ComplexPoly[][] copy(ComplexPoly[][] var0) {
        if (var0 == null) {
            return (ComplexPoly[][])null;
        } else {
            int var1 = var0.length;
            ComplexPoly[][] var2 = new ComplexPoly[var1][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new ComplexPoly[var4];

                for(int var5 = 0; var5 < var4; ++var5) {
                    var2[var3][var5] = var0[var3][var5].copy();
                }
            }

            return var2;
        }
    }

    public static ComplexPoly[][][] copy(ComplexPoly[][][] var0) {
        if (var0 == null) {
            return (ComplexPoly[][][])null;
        } else {
            int var1 = var0.length;
            ComplexPoly[][][] var2 = new ComplexPoly[var1][][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new ComplexPoly[var4][];

                for(int var5 = 0; var5 < var4; ++var5) {
                    int var6 = var0[var3][var5].length;
                    var2[var3][var5] = new ComplexPoly[var6];

                    for(int var7 = 0; var7 < var6; ++var7) {
                        var2[var3][var5][var7] = var0[var3][var5][var7].copy();
                    }
                }
            }

            return var2;
        }
    }

    public static ComplexPoly[][][][] copy(ComplexPoly[][][][] var0) {
        if (var0 == null) {
            return (ComplexPoly[][][][])null;
        } else {
            int var1 = var0.length;
            ComplexPoly[][][][] var2 = new ComplexPoly[var1][][][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new ComplexPoly[var4][][];

                for(int var5 = 0; var5 < var4; ++var5) {
                    int var6 = var0[var3][var5].length;
                    var2[var3][var5] = new ComplexPoly[var6][];

                    for(int var7 = 0; var7 < var6; ++var7) {
                        int var8 = var0[var3][var5][var7].length;
                        var2[var3][var5][var7] = new ComplexPoly[var8];

                        for(int var9 = 0; var9 < var8; ++var9) {
                            var2[var3][var5][var7][var9] = var0[var3][var5][var7][var9].copy();
                        }
                    }
                }
            }

            return var2;
        }
    }

    public static Polynomial[] copy(Polynomial[] var0) {
        if (var0 == null) {
            return null;
        } else {
            int var1 = var0.length;
            Polynomial[] var2 = new Polynomial[var1];

            for(int var3 = 0; var3 < var1; ++var3) {
                var2[var3] = var0[var3].copy();
            }

            return var2;
        }
    }

    public static Polynomial[][] copy(Polynomial[][] var0) {
        if (var0 == null) {
            return (Polynomial[][])null;
        } else {
            int var1 = var0.length;
            Polynomial[][] var2 = new Polynomial[var1][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new Polynomial[var4];

                for(int var5 = 0; var5 < var4; ++var5) {
                    var2[var3][var5] = var0[var3][var5].copy();
                }
            }

            return var2;
        }
    }

    public static Polynomial[][][] copy(Polynomial[][][] var0) {
        if (var0 == null) {
            return (Polynomial[][][])null;
        } else {
            int var1 = var0.length;
            Polynomial[][][] var2 = new Polynomial[var1][][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new Polynomial[var4][];

                for(int var5 = 0; var5 < var4; ++var5) {
                    int var6 = var0[var3][var5].length;
                    var2[var3][var5] = new Polynomial[var6];

                    for(int var7 = 0; var7 < var6; ++var7) {
                        var2[var3][var5][var7] = var0[var3][var5][var7].copy();
                    }
                }
            }

            return var2;
        }
    }

    public static Polynomial[][][][] copy(Polynomial[][][][] var0) {
        if (var0 == null) {
            return (Polynomial[][][][])null;
        } else {
            int var1 = var0.length;
            Polynomial[][][][] var2 = new Polynomial[var1][][][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new Polynomial[var4][][];

                for(int var5 = 0; var5 < var4; ++var5) {
                    int var6 = var0[var3][var5].length;
                    var2[var3][var5] = new Polynomial[var6][];

                    for(int var7 = 0; var7 < var6; ++var7) {
                        int var8 = var0[var3][var5][var7].length;
                        var2[var3][var5][var7] = new Polynomial[var8];

                        for(int var9 = 0; var9 < var8; ++var9) {
                            var2[var3][var5][var7][var9] = var0[var3][var5][var7][var9].copy();
                        }
                    }
                }
            }

            return var2;
        }
    }

    public static BigDecimal[] copy(BigDecimal[] var0) {
        if (var0 == null) {
            return null;
        } else {
            int var1 = var0.length;
            BigDecimal[] var2 = new BigDecimal[var1];

            for(int var3 = 0; var3 < var1; ++var3) {
                var2[var3] = var0[var3];
            }

            return var2;
        }
    }

    public static BigDecimal[][] copy(BigDecimal[][] var0) {
        if (var0 == null) {
            return (BigDecimal[][])null;
        } else {
            int var1 = var0.length;
            BigDecimal[][] var2 = new BigDecimal[var1][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new BigDecimal[var4];

                for(int var5 = 0; var5 < var4; ++var5) {
                    var2[var3][var5] = var0[var3][var5];
                }
            }

            return var2;
        }
    }

    public static BigDecimal[][][] copy(BigDecimal[][][] var0) {
        if (var0 == null) {
            return (BigDecimal[][][])null;
        } else {
            int var1 = var0.length;
            BigDecimal[][][] var2 = new BigDecimal[var1][][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new BigDecimal[var4][];

                for(int var5 = 0; var5 < var4; ++var5) {
                    int var6 = var0[var3][var5].length;
                    var2[var3][var5] = new BigDecimal[var6];

                    for(int var7 = 0; var7 < var6; ++var7) {
                        var2[var3][var5][var7] = var0[var3][var5][var7];
                    }
                }
            }

            return var2;
        }
    }

    public static BigDecimal[][][][] copy(BigDecimal[][][][] var0) {
        if (var0 == null) {
            return (BigDecimal[][][][])null;
        } else {
            int var1 = var0.length;
            BigDecimal[][][][] var2 = new BigDecimal[var1][][][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new BigDecimal[var4][][];

                for(int var5 = 0; var5 < var4; ++var5) {
                    int var6 = var0[var3][var5].length;
                    var2[var3][var5] = new BigDecimal[var6][];

                    for(int var7 = 0; var7 < var6; ++var7) {
                        int var8 = var0[var3][var5][var7].length;
                        var2[var3][var5][var7] = new BigDecimal[var8];

                        for(int var9 = 0; var9 < var8; ++var9) {
                            var2[var3][var5][var7][var9] = var0[var3][var5][var7][var9];
                        }
                    }
                }
            }

            return var2;
        }
    }

    public static BigInteger[] copy(BigInteger[] var0) {
        if (var0 == null) {
            return null;
        } else {
            int var1 = var0.length;
            BigInteger[] var2 = new BigInteger[var1];

            for(int var3 = 0; var3 < var1; ++var3) {
                var2[var3] = var0[var3];
            }

            return var2;
        }
    }

    public static BigInteger[][] copy(BigInteger[][] var0) {
        if (var0 == null) {
            return (BigInteger[][])null;
        } else {
            int var1 = var0.length;
            BigInteger[][] var2 = new BigInteger[var1][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new BigInteger[var4];

                for(int var5 = 0; var5 < var4; ++var5) {
                    var2[var3][var5] = var0[var3][var5];
                }
            }

            return var2;
        }
    }

    public static BigInteger[][][] copy(BigInteger[][][] var0) {
        if (var0 == null) {
            return (BigInteger[][][])null;
        } else {
            int var1 = var0.length;
            BigInteger[][][] var2 = new BigInteger[var1][][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new BigInteger[var4][];

                for(int var5 = 0; var5 < var4; ++var5) {
                    int var6 = var0[var3][var5].length;
                    var2[var3][var5] = new BigInteger[var6];

                    for(int var7 = 0; var7 < var6; ++var7) {
                        var2[var3][var5][var7] = var0[var3][var5][var7];
                    }
                }
            }

            return var2;
        }
    }

    public static BigInteger[][][][] copy(BigInteger[][][][] var0) {
        if (var0 == null) {
            return (BigInteger[][][][])null;
        } else {
            int var1 = var0.length;
            BigInteger[][][][] var2 = new BigInteger[var1][][][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new BigInteger[var4][][];

                for(int var5 = 0; var5 < var4; ++var5) {
                    int var6 = var0[var3][var5].length;
                    var2[var3][var5] = new BigInteger[var6][];

                    for(int var7 = 0; var7 < var6; ++var7) {
                        int var8 = var0[var3][var5][var7].length;
                        var2[var3][var5][var7] = new BigInteger[var8];

                        for(int var9 = 0; var9 < var8; ++var9) {
                            var2[var3][var5][var7][var9] = var0[var3][var5][var7][var9];
                        }
                    }
                }
            }

            return var2;
        }
    }

    public static ErrorProp[] copy(ErrorProp[] var0) {
        if (var0 == null) {
            return null;
        } else {
            int var1 = var0.length;
            ErrorProp[] var2 = new ErrorProp[var1];

            for(int var3 = 0; var3 < var1; ++var3) {
                var2[var3] = var0[var3].copy();
            }

            return var2;
        }
    }

    public static ErrorProp[][] copy(ErrorProp[][] var0) {
        if (var0 == null) {
            return (ErrorProp[][])null;
        } else {
            int var1 = var0.length;
            ErrorProp[][] var2 = new ErrorProp[var1][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new ErrorProp[var4];

                for(int var5 = 0; var5 < var4; ++var5) {
                    var2[var3][var5] = var0[var3][var5].copy();
                }
            }

            return var2;
        }
    }

    public static ErrorProp[][][] copy(ErrorProp[][][] var0) {
        if (var0 == null) {
            return (ErrorProp[][][])null;
        } else {
            int var1 = var0.length;
            ErrorProp[][][] var2 = new ErrorProp[var1][][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new ErrorProp[var4][];

                for(int var5 = 0; var5 < var4; ++var5) {
                    int var6 = var0[var3][var5].length;
                    var2[var3][var5] = new ErrorProp[var6];

                    for(int var7 = 0; var7 < var6; ++var7) {
                        var2[var3][var5][var7] = var0[var3][var5][var7].copy();
                    }
                }
            }

            return var2;
        }
    }

    public static ErrorProp[][][][] copy(ErrorProp[][][][] var0) {
        if (var0 == null) {
            return (ErrorProp[][][][])null;
        } else {
            int var1 = var0.length;
            ErrorProp[][][][] var2 = new ErrorProp[var1][][][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new ErrorProp[var4][][];

                for(int var5 = 0; var5 < var4; ++var5) {
                    int var6 = var0[var3][var5].length;
                    var2[var3][var5] = new ErrorProp[var6][];

                    for(int var7 = 0; var7 < var6; ++var7) {
                        int var8 = var0[var3][var5][var7].length;
                        var2[var3][var5][var7] = new ErrorProp[var8];

                        for(int var9 = 0; var9 < var8; ++var9) {
                            var2[var3][var5][var7][var9] = var0[var3][var5][var7][var9].copy();
                        }
                    }
                }
            }

            return var2;
        }
    }

    public static ComplexErrorProp[] copy(ComplexErrorProp[] var0) {
        if (var0 == null) {
            return null;
        } else {
            int var1 = var0.length;
            ComplexErrorProp[] var2 = new ComplexErrorProp[var1];

            for(int var3 = 0; var3 < var1; ++var3) {
                var2[var3] = var0[var3].copy();
            }

            return var2;
        }
    }

    public static ComplexErrorProp[][] copy(ComplexErrorProp[][] var0) {
        if (var0 == null) {
            return (ComplexErrorProp[][])null;
        } else {
            int var1 = var0.length;
            ComplexErrorProp[][] var2 = new ComplexErrorProp[var1][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new ComplexErrorProp[var4];

                for(int var5 = 0; var5 < var4; ++var5) {
                    var2[var3][var5] = var0[var3][var5].copy();
                }
            }

            return var2;
        }
    }

    public static ComplexErrorProp[][][] copy(ComplexErrorProp[][][] var0) {
        if (var0 == null) {
            return (ComplexErrorProp[][][])null;
        } else {
            int var1 = var0.length;
            ComplexErrorProp[][][] var2 = new ComplexErrorProp[var1][][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new ComplexErrorProp[var4][];

                for(int var5 = 0; var5 < var4; ++var5) {
                    int var6 = var0[var3][var5].length;
                    var2[var3][var5] = new ComplexErrorProp[var6];

                    for(int var7 = 0; var7 < var6; ++var7) {
                        var2[var3][var5][var7] = var0[var3][var5][var7].copy();
                    }
                }
            }

            return var2;
        }
    }

    public static ComplexErrorProp[][][][] copy(ComplexErrorProp[][][][] var0) {
        if (var0 == null) {
            return (ComplexErrorProp[][][][])null;
        } else {
            int var1 = var0.length;
            ComplexErrorProp[][][][] var2 = new ComplexErrorProp[var1][][][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new ComplexErrorProp[var4][][];

                for(int var5 = 0; var5 < var4; ++var5) {
                    int var6 = var0[var3][var5].length;
                    var2[var3][var5] = new ComplexErrorProp[var6][];

                    for(int var7 = 0; var7 < var6; ++var7) {
                        int var8 = var0[var3][var5][var7].length;
                        var2[var3][var5][var7] = new ComplexErrorProp[var8];

                        for(int var9 = 0; var9 < var8; ++var9) {
                            var2[var3][var5][var7][var9] = var0[var3][var5][var7][var9].copy();
                        }
                    }
                }
            }

            return var2;
        }
    }

    public static Phasor[] copy(Phasor[] var0) {
        if (var0 == null) {
            return null;
        } else {
            int var1 = var0.length;
            Phasor[] var2 = new Phasor[var1];

            for(int var3 = 0; var3 < var1; ++var3) {
                var2[var3] = var0[var3].copy();
            }

            return var2;
        }
    }

    public static Phasor[][] copy(Phasor[][] var0) {
        if (var0 == null) {
            return (Phasor[][])null;
        } else {
            int var1 = var0.length;
            Phasor[][] var2 = new Phasor[var1][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new Phasor[var4];

                for(int var5 = 0; var5 < var4; ++var5) {
                    var2[var3][var5] = var0[var3][var5].copy();
                }
            }

            return var2;
        }
    }

    public static Phasor[][][] copy(Phasor[][][] var0) {
        if (var0 == null) {
            return (Phasor[][][])null;
        } else {
            int var1 = var0.length;
            Phasor[][][] var2 = new Phasor[var1][][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new Phasor[var4][];

                for(int var5 = 0; var5 < var4; ++var5) {
                    int var6 = var0[var3][var5].length;
                    var2[var3][var5] = new Phasor[var6];

                    for(int var7 = 0; var7 < var6; ++var7) {
                        var2[var3][var5][var7] = var0[var3][var5][var7].copy();
                    }
                }
            }

            return var2;
        }
    }

    public static Phasor[][][][] copy(Phasor[][][][] var0) {
        if (var0 == null) {
            return (Phasor[][][][])null;
        } else {
            int var1 = var0.length;
            Phasor[][][][] var2 = new Phasor[var1][][][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new Phasor[var4][][];

                for(int var5 = 0; var5 < var4; ++var5) {
                    int var6 = var0[var3][var5].length;
                    var2[var3][var5] = new Phasor[var6][];

                    for(int var7 = 0; var7 < var6; ++var7) {
                        int var8 = var0[var3][var5][var7].length;
                        var2[var3][var5][var7] = new Phasor[var8];

                        for(int var9 = 0; var9 < var8; ++var9) {
                            var2[var3][var5][var7][var9] = var0[var3][var5][var7][var9].copy();
                        }
                    }
                }
            }

            return var2;
        }
    }

    public static short[] copy(short[] var0) {
        if (var0 == null) {
            return null;
        } else {
            int var1 = var0.length;
            short[] var2 = new short[var1];

            for(int var3 = 0; var3 < var1; ++var3) {
                var2[var3] = var0[var3];
            }

            return var2;
        }
    }

    public static short[][] copy(short[][] var0) {
        if (var0 == null) {
            return (short[][])null;
        } else {
            int var1 = var0.length;
            short[][] var2 = new short[var1][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new short[var4];

                for(int var5 = 0; var5 < var4; ++var5) {
                    var2[var3][var5] = var0[var3][var5];
                }
            }

            return var2;
        }
    }

    public static short[][][] copy(short[][][] var0) {
        if (var0 == null) {
            return (short[][][])null;
        } else {
            int var1 = var0.length;
            short[][][] var2 = new short[var1][][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new short[var4][];

                for(int var5 = 0; var5 < var4; ++var5) {
                    int var6 = var0[var3][var5].length;
                    var2[var3][var5] = new short[var6];

                    for(int var7 = 0; var7 < var6; ++var7) {
                        var2[var3][var5][var7] = var0[var3][var5][var7];
                    }
                }
            }

            return var2;
        }
    }

    public static short[][][][] copy(short[][][][] var0) {
        if (var0 == null) {
            return (short[][][][])null;
        } else {
            int var1 = var0.length;
            short[][][][] var2 = new short[var1][][][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new short[var4][][];

                for(int var5 = 0; var5 < var4; ++var5) {
                    int var6 = var0[var3][var5].length;
                    var2[var3][var5] = new short[var6][];

                    for(int var7 = 0; var7 < var6; ++var7) {
                        int var8 = var0[var3][var5][var7].length;
                        var2[var3][var5][var7] = new short[var8];

                        for(int var9 = 0; var9 < var8; ++var9) {
                            var2[var3][var5][var7][var9] = var0[var3][var5][var7][var9];
                        }
                    }
                }
            }

            return var2;
        }
    }

    public static byte[] copy(byte[] var0) {
        if (var0 == null) {
            return null;
        } else {
            int var1 = var0.length;
            byte[] var2 = new byte[var1];

            for(int var3 = 0; var3 < var1; ++var3) {
                var2[var3] = var0[var3];
            }

            return var2;
        }
    }

    public static byte[][] copy(byte[][] var0) {
        if (var0 == null) {
            return (byte[][])null;
        } else {
            int var1 = var0.length;
            byte[][] var2 = new byte[var1][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new byte[var4];

                for(int var5 = 0; var5 < var4; ++var5) {
                    var2[var3][var5] = var0[var3][var5];
                }
            }

            return var2;
        }
    }

    public static byte[][][] copy(byte[][][] var0) {
        if (var0 == null) {
            return (byte[][][])null;
        } else {
            int var1 = var0.length;
            byte[][][] var2 = new byte[var1][][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new byte[var4][];

                for(int var5 = 0; var5 < var4; ++var5) {
                    int var6 = var0[var3][var5].length;
                    var2[var3][var5] = new byte[var6];

                    for(int var7 = 0; var7 < var6; ++var7) {
                        var2[var3][var5][var7] = var0[var3][var5][var7];
                    }
                }
            }

            return var2;
        }
    }

    public static byte[][][][] copy(byte[][][][] var0) {
        if (var0 == null) {
            return (byte[][][][])null;
        } else {
            int var1 = var0.length;
            byte[][][][] var2 = new byte[var1][][][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new byte[var4][][];

                for(int var5 = 0; var5 < var4; ++var5) {
                    int var6 = var0[var3][var5].length;
                    var2[var3][var5] = new byte[var6][];

                    for(int var7 = 0; var7 < var6; ++var7) {
                        int var8 = var0[var3][var5][var7].length;
                        var2[var3][var5][var7] = new byte[var8];

                        for(int var9 = 0; var9 < var8; ++var9) {
                            var2[var3][var5][var7][var9] = var0[var3][var5][var7][var9];
                        }
                    }
                }
            }

            return var2;
        }
    }

    public static Double[] copy(Double[] var0) {
        if (var0 == null) {
            return null;
        } else {
            int var1 = var0.length;
            Double[] var2 = new Double[var1];

            for(int var3 = 0; var3 < var1; ++var3) {
                var2[var3] = var0[var3];
            }

            return var2;
        }
    }

    public static Double[][] copy(Double[][] var0) {
        if (var0 == null) {
            return (Double[][])null;
        } else {
            int var1 = var0.length;
            Double[][] var2 = new Double[var1][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new Double[var4];

                for(int var5 = 0; var5 < var4; ++var5) {
                    var2[var3][var5] = var0[var3][var5];
                }
            }

            return var2;
        }
    }

    public static Double[][][] copy(Double[][][] var0) {
        if (var0 == null) {
            return (Double[][][])null;
        } else {
            int var1 = var0.length;
            Double[][][] var2 = new Double[var1][][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new Double[var4][];

                for(int var5 = 0; var5 < var4; ++var5) {
                    int var6 = var0[var3][var5].length;
                    var2[var3][var5] = new Double[var6];

                    for(int var7 = 0; var7 < var6; ++var7) {
                        var2[var3][var5][var7] = var0[var3][var5][var7];
                    }
                }
            }

            return var2;
        }
    }

    public static Double[][][][] copy(Double[][][][] var0) {
        if (var0 == null) {
            return (Double[][][][])null;
        } else {
            int var1 = var0.length;
            Double[][][][] var2 = new Double[var1][][][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new Double[var4][][];

                for(int var5 = 0; var5 < var4; ++var5) {
                    int var6 = var0[var3][var5].length;
                    var2[var3][var5] = new Double[var6][];

                    for(int var7 = 0; var7 < var6; ++var7) {
                        int var8 = var0[var3][var5][var7].length;
                        var2[var3][var5][var7] = new Double[var8];

                        for(int var9 = 0; var9 < var8; ++var9) {
                            var2[var3][var5][var7][var9] = var0[var3][var5][var7][var9];
                        }
                    }
                }
            }

            return var2;
        }
    }

    public static Float[] copy(Float[] var0) {
        if (var0 == null) {
            return null;
        } else {
            int var1 = var0.length;
            Float[] var2 = new Float[var1];

            for(int var3 = 0; var3 < var1; ++var3) {
                var2[var3] = var0[var3];
            }

            return var2;
        }
    }

    public static Float[][] copy(Float[][] var0) {
        if (var0 == null) {
            return (Float[][])null;
        } else {
            int var1 = var0.length;
            Float[][] var2 = new Float[var1][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new Float[var4];

                for(int var5 = 0; var5 < var4; ++var5) {
                    var2[var3][var5] = var0[var3][var5];
                }
            }

            return var2;
        }
    }

    public static Float[][][] copy(Float[][][] var0) {
        if (var0 == null) {
            return (Float[][][])null;
        } else {
            int var1 = var0.length;
            Float[][][] var2 = new Float[var1][][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new Float[var4][];

                for(int var5 = 0; var5 < var4; ++var5) {
                    int var6 = var0[var3][var5].length;
                    var2[var3][var5] = new Float[var6];

                    for(int var7 = 0; var7 < var6; ++var7) {
                        var2[var3][var5][var7] = var0[var3][var5][var7];
                    }
                }
            }

            return var2;
        }
    }

    public static Float[][][][] copy(Float[][][][] var0) {
        if (var0 == null) {
            return (Float[][][][])null;
        } else {
            int var1 = var0.length;
            Float[][][][] var2 = new Float[var1][][][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new Float[var4][][];

                for(int var5 = 0; var5 < var4; ++var5) {
                    int var6 = var0[var3][var5].length;
                    var2[var3][var5] = new Float[var6][];

                    for(int var7 = 0; var7 < var6; ++var7) {
                        int var8 = var0[var3][var5][var7].length;
                        var2[var3][var5][var7] = new Float[var8];

                        for(int var9 = 0; var9 < var8; ++var9) {
                            var2[var3][var5][var7][var9] = var0[var3][var5][var7][var9];
                        }
                    }
                }
            }

            return var2;
        }
    }

    public static Long[] copy(Long[] var0) {
        if (var0 == null) {
            return null;
        } else {
            int var1 = var0.length;
            Long[] var2 = new Long[var1];

            for(int var3 = 0; var3 < var1; ++var3) {
                var2[var3] = var0[var3];
            }

            return var2;
        }
    }

    public static Long[][] copy(Long[][] var0) {
        if (var0 == null) {
            return (Long[][])null;
        } else {
            int var1 = var0.length;
            Long[][] var2 = new Long[var1][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new Long[var4];

                for(int var5 = 0; var5 < var4; ++var5) {
                    var2[var3][var5] = var0[var3][var5];
                }
            }

            return var2;
        }
    }

    public static Long[][][] copy(Long[][][] var0) {
        if (var0 == null) {
            return (Long[][][])null;
        } else {
            int var1 = var0.length;
            Long[][][] var2 = new Long[var1][][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new Long[var4][];

                for(int var5 = 0; var5 < var4; ++var5) {
                    int var6 = var0[var3][var5].length;
                    var2[var3][var5] = new Long[var6];

                    for(int var7 = 0; var7 < var6; ++var7) {
                        var2[var3][var5][var7] = var0[var3][var5][var7];
                    }
                }
            }

            return var2;
        }
    }

    public static Long[][][][] copy(Long[][][][] var0) {
        if (var0 == null) {
            return (Long[][][][])null;
        } else {
            int var1 = var0.length;
            Long[][][][] var2 = new Long[var1][][][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new Long[var4][][];

                for(int var5 = 0; var5 < var4; ++var5) {
                    int var6 = var0[var3][var5].length;
                    var2[var3][var5] = new Long[var6][];

                    for(int var7 = 0; var7 < var6; ++var7) {
                        int var8 = var0[var3][var5][var7].length;
                        var2[var3][var5][var7] = new Long[var8];

                        for(int var9 = 0; var9 < var8; ++var9) {
                            var2[var3][var5][var7][var9] = var0[var3][var5][var7][var9];
                        }
                    }
                }
            }

            return var2;
        }
    }

    public static Integer[] copy(Integer[] var0) {
        if (var0 == null) {
            return null;
        } else {
            int var1 = var0.length;
            Integer[] var2 = new Integer[var1];

            for(int var3 = 0; var3 < var1; ++var3) {
                var2[var3] = var0[var3];
            }

            return var2;
        }
    }

    public static Integer[][] copy(Integer[][] var0) {
        if (var0 == null) {
            return (Integer[][])null;
        } else {
            int var1 = var0.length;
            Integer[][] var2 = new Integer[var1][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new Integer[var4];

                for(int var5 = 0; var5 < var4; ++var5) {
                    var2[var3][var5] = var0[var3][var5];
                }
            }

            return var2;
        }
    }

    public static Integer[][][] copy(Integer[][][] var0) {
        if (var0 == null) {
            return (Integer[][][])null;
        } else {
            int var1 = var0.length;
            Integer[][][] var2 = new Integer[var1][][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new Integer[var4][];

                for(int var5 = 0; var5 < var4; ++var5) {
                    int var6 = var0[var3][var5].length;
                    var2[var3][var5] = new Integer[var6];

                    for(int var7 = 0; var7 < var6; ++var7) {
                        var2[var3][var5][var7] = var0[var3][var5][var7];
                    }
                }
            }

            return var2;
        }
    }

    public static Integer[][][][] copy(Integer[][][][] var0) {
        if (var0 == null) {
            return (Integer[][][][])null;
        } else {
            int var1 = var0.length;
            Integer[][][][] var2 = new Integer[var1][][][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new Integer[var4][][];

                for(int var5 = 0; var5 < var4; ++var5) {
                    int var6 = var0[var3][var5].length;
                    var2[var3][var5] = new Integer[var6][];

                    for(int var7 = 0; var7 < var6; ++var7) {
                        int var8 = var0[var3][var5][var7].length;
                        var2[var3][var5][var7] = new Integer[var8];

                        for(int var9 = 0; var9 < var8; ++var9) {
                            var2[var3][var5][var7][var9] = var0[var3][var5][var7][var9];
                        }
                    }
                }
            }

            return var2;
        }
    }

    public static Short[] copy(Short[] var0) {
        if (var0 == null) {
            return null;
        } else {
            int var1 = var0.length;
            Short[] var2 = new Short[var1];

            for(int var3 = 0; var3 < var1; ++var3) {
                var2[var3] = var0[var3];
            }

            return var2;
        }
    }

    public static Short[][] copy(Short[][] var0) {
        if (var0 == null) {
            return (Short[][])null;
        } else {
            int var1 = var0.length;
            Short[][] var2 = new Short[var1][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new Short[var4];

                for(int var5 = 0; var5 < var4; ++var5) {
                    var2[var3][var5] = var0[var3][var5];
                }
            }

            return var2;
        }
    }

    public static Short[][][] copy(Short[][][] var0) {
        if (var0 == null) {
            return (Short[][][])null;
        } else {
            int var1 = var0.length;
            Short[][][] var2 = new Short[var1][][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new Short[var4][];

                for(int var5 = 0; var5 < var4; ++var5) {
                    int var6 = var0[var3][var5].length;
                    var2[var3][var5] = new Short[var6];

                    for(int var7 = 0; var7 < var6; ++var7) {
                        var2[var3][var5][var7] = var0[var3][var5][var7];
                    }
                }
            }

            return var2;
        }
    }

    public static Short[][][][] copy(Short[][][][] var0) {
        if (var0 == null) {
            return (Short[][][][])null;
        } else {
            int var1 = var0.length;
            Short[][][][] var2 = new Short[var1][][][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new Short[var4][][];

                for(int var5 = 0; var5 < var4; ++var5) {
                    int var6 = var0[var3][var5].length;
                    var2[var3][var5] = new Short[var6][];

                    for(int var7 = 0; var7 < var6; ++var7) {
                        int var8 = var0[var3][var5][var7].length;
                        var2[var3][var5][var7] = new Short[var8];

                        for(int var9 = 0; var9 < var8; ++var9) {
                            var2[var3][var5][var7][var9] = var0[var3][var5][var7][var9];
                        }
                    }
                }
            }

            return var2;
        }
    }

    public static Byte[] copy(Byte[] var0) {
        if (var0 == null) {
            return null;
        } else {
            int var1 = var0.length;
            Byte[] var2 = new Byte[var1];

            for(int var3 = 0; var3 < var1; ++var3) {
                var2[var3] = var0[var3];
            }

            return var2;
        }
    }

    public static Byte[][] copy(Byte[][] var0) {
        if (var0 == null) {
            return (Byte[][])null;
        } else {
            int var1 = var0.length;
            Byte[][] var2 = new Byte[var1][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new Byte[var4];

                for(int var5 = 0; var5 < var4; ++var5) {
                    var2[var3][var5] = var0[var3][var5];
                }
            }

            return var2;
        }
    }

    public static Byte[][][] copy(Byte[][][] var0) {
        if (var0 == null) {
            return (Byte[][][])null;
        } else {
            int var1 = var0.length;
            Byte[][][] var2 = new Byte[var1][][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new Byte[var4][];

                for(int var5 = 0; var5 < var4; ++var5) {
                    int var6 = var0[var3][var5].length;
                    var2[var3][var5] = new Byte[var6];

                    for(int var7 = 0; var7 < var6; ++var7) {
                        var2[var3][var5][var7] = var0[var3][var5][var7];
                    }
                }
            }

            return var2;
        }
    }

    public static Byte[][][][] copy(Byte[][][][] var0) {
        if (var0 == null) {
            return (Byte[][][][])null;
        } else {
            int var1 = var0.length;
            Byte[][][][] var2 = new Byte[var1][][][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new Byte[var4][][];

                for(int var5 = 0; var5 < var4; ++var5) {
                    int var6 = var0[var3][var5].length;
                    var2[var3][var5] = new Byte[var6][];

                    for(int var7 = 0; var7 < var6; ++var7) {
                        int var8 = var0[var3][var5][var7].length;
                        var2[var3][var5][var7] = new Byte[var8];

                        for(int var9 = 0; var9 < var8; ++var9) {
                            var2[var3][var5][var7][var9] = var0[var3][var5][var7][var9];
                        }
                    }
                }
            }

            return var2;
        }
    }

    public static ArrayMaths[] copy(ArrayMaths[] var0) {
        if (var0 == null) {
            return null;
        } else {
            int var1 = var0.length;
            ArrayMaths[] var2 = new ArrayMaths[var1];

            for(int var3 = 0; var3 < var1; ++var3) {
                var2[var3] = var0[var3].copy();
            }

            return var2;
        }
    }

    public static ArrayMaths[][] copy(ArrayMaths[][] var0) {
        if (var0 == null) {
            return (ArrayMaths[][])null;
        } else {
            int var1 = var0.length;
            ArrayMaths[][] var2 = new ArrayMaths[var1][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new ArrayMaths[var4];

                for(int var5 = 0; var5 < var4; ++var5) {
                    var2[var3][var5] = var0[var3][var5].copy();
                }
            }

            return var2;
        }
    }

    public static ArrayMaths[][][] copy(ArrayMaths[][][] var0) {
        if (var0 == null) {
            return (ArrayMaths[][][])null;
        } else {
            int var1 = var0.length;
            ArrayMaths[][][] var2 = new ArrayMaths[var1][][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new ArrayMaths[var4][];

                for(int var5 = 0; var5 < var4; ++var5) {
                    int var6 = var0[var3][var5].length;
                    var2[var3][var5] = new ArrayMaths[var6];

                    for(int var7 = 0; var7 < var6; ++var7) {
                        var2[var3][var5][var7] = var0[var3][var5][var7].copy();
                    }
                }
            }

            return var2;
        }
    }

    public static ArrayMaths[][][][] copy(ArrayMaths[][][][] var0) {
        if (var0 == null) {
            return (ArrayMaths[][][][])null;
        } else {
            int var1 = var0.length;
            ArrayMaths[][][][] var2 = new ArrayMaths[var1][][][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new ArrayMaths[var4][][];

                for(int var5 = 0; var5 < var4; ++var5) {
                    int var6 = var0[var3][var5].length;
                    var2[var3][var5] = new ArrayMaths[var6][];

                    for(int var7 = 0; var7 < var6; ++var7) {
                        int var8 = var0[var3][var5][var7].length;
                        var2[var3][var5][var7] = new ArrayMaths[var8];

                        for(int var9 = 0; var9 < var8; ++var9) {
                            var2[var3][var5][var7][var9] = var0[var3][var5][var7][var9].copy();
                        }
                    }
                }
            }

            return var2;
        }
    }

    public static VectorMaths[] copy(VectorMaths[] var0) {
        if (var0 == null) {
            return null;
        } else {
            int var1 = var0.length;
            VectorMaths[] var2 = new VectorMaths[var1];

            for(int var3 = 0; var3 < var1; ++var3) {
                var2[var3] = var0[var3].copy();
            }

            return var2;
        }
    }

    public static VectorMaths[][] copy(VectorMaths[][] var0) {
        if (var0 == null) {
            return (VectorMaths[][])null;
        } else {
            int var1 = var0.length;
            VectorMaths[][] var2 = new VectorMaths[var1][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new VectorMaths[var4];

                for(int var5 = 0; var5 < var4; ++var5) {
                    var2[var3][var5] = var0[var3][var5].copy();
                }
            }

            return var2;
        }
    }

    public static VectorMaths[][][] copy(VectorMaths[][][] var0) {
        if (var0 == null) {
            return (VectorMaths[][][])null;
        } else {
            int var1 = var0.length;
            VectorMaths[][][] var2 = new VectorMaths[var1][][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new VectorMaths[var4][];

                for(int var5 = 0; var5 < var4; ++var5) {
                    int var6 = var0[var3][var5].length;
                    var2[var3][var5] = new VectorMaths[var6];

                    for(int var7 = 0; var7 < var6; ++var7) {
                        var2[var3][var5][var7] = var0[var3][var5][var7].copy();
                    }
                }
            }

            return var2;
        }
    }

    public static VectorMaths[][][][] copy(VectorMaths[][][][] var0) {
        if (var0 == null) {
            return (VectorMaths[][][][])null;
        } else {
            int var1 = var0.length;
            VectorMaths[][][][] var2 = new VectorMaths[var1][][][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new VectorMaths[var4][][];

                for(int var5 = 0; var5 < var4; ++var5) {
                    int var6 = var0[var3][var5].length;
                    var2[var3][var5] = new VectorMaths[var6][];

                    for(int var7 = 0; var7 < var6; ++var7) {
                        int var8 = var0[var3][var5][var7].length;
                        var2[var3][var5][var7] = new VectorMaths[var8];

                        for(int var9 = 0; var9 < var8; ++var9) {
                            var2[var3][var5][var7][var9] = var0[var3][var5][var7][var9].copy();
                        }
                    }
                }
            }

            return var2;
        }
    }

    public static Point[] copy(Point[] var0) {
        if (var0 == null) {
            return null;
        } else {
            int var1 = var0.length;
            Point[] var2 = new Point[var1];

            for(int var3 = 0; var3 < var1; ++var3) {
                var2[var3] = var0[var3].copy();
            }

            return var2;
        }
    }

    public static Point[][] copy(Point[][] var0) {
        if (var0 == null) {
            return (Point[][])null;
        } else {
            int var1 = var0.length;
            Point[][] var2 = new Point[var1][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new Point[var4];

                for(int var5 = 0; var5 < var4; ++var5) {
                    var2[var3][var5] = var0[var3][var5].copy();
                }
            }

            return var2;
        }
    }

    public static Point[][][] copy(Point[][][] var0) {
        if (var0 == null) {
            return (Point[][][])null;
        } else {
            int var1 = var0.length;
            Point[][][] var2 = new Point[var1][][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new Point[var4][];

                for(int var5 = 0; var5 < var4; ++var5) {
                    int var6 = var0[var3][var5].length;
                    var2[var3][var5] = new Point[var6];

                    for(int var7 = 0; var7 < var6; ++var7) {
                        var2[var3][var5][var7] = var0[var3][var5][var7].copy();
                    }
                }
            }

            return var2;
        }
    }

    public static Point[][][][] copy(Point[][][][] var0) {
        if (var0 == null) {
            return (Point[][][][])null;
        } else {
            int var1 = var0.length;
            Point[][][][] var2 = new Point[var1][][][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new Point[var4][][];

                for(int var5 = 0; var5 < var4; ++var5) {
                    int var6 = var0[var3][var5].length;
                    var2[var3][var5] = new Point[var6][];

                    for(int var7 = 0; var7 < var6; ++var7) {
                        int var8 = var0[var3][var5][var7].length;
                        var2[var3][var5][var7] = new Point[var8];

                        for(int var9 = 0; var9 < var8; ++var9) {
                            var2[var3][var5][var7][var9] = var0[var3][var5][var7][var9].copy();
                        }
                    }
                }
            }

            return var2;
        }
    }

    public static Matrix[] copy(Matrix[] var0) {
        if (var0 == null) {
            return null;
        } else {
            int var1 = var0.length;
            Matrix[] var2 = new Matrix[var1];

            for(int var3 = 0; var3 < var1; ++var3) {
                var2[var3] = var0[var3].copy();
            }

            return var2;
        }
    }

    public static Matrix[][] copy(Matrix[][] var0) {
        if (var0 == null) {
            return (Matrix[][])null;
        } else {
            int var1 = var0.length;
            Matrix[][] var2 = new Matrix[var1][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new Matrix[var4];

                for(int var5 = 0; var5 < var4; ++var5) {
                    var2[var3][var5] = var0[var3][var5].copy();
                }
            }

            return var2;
        }
    }

    public static Matrix[][][] copy(Matrix[][][] var0) {
        if (var0 == null) {
            return (Matrix[][][])null;
        } else {
            int var1 = var0.length;
            Matrix[][][] var2 = new Matrix[var1][][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new Matrix[var4][];

                for(int var5 = 0; var5 < var4; ++var5) {
                    int var6 = var0[var3][var5].length;
                    var2[var3][var5] = new Matrix[var6];

                    for(int var7 = 0; var7 < var6; ++var7) {
                        var2[var3][var5][var7] = var0[var3][var5][var7].copy();
                    }
                }
            }

            return var2;
        }
    }

    public static Matrix[][][][] copy(Matrix[][][][] var0) {
        if (var0 == null) {
            return (Matrix[][][][])null;
        } else {
            int var1 = var0.length;
            Matrix[][][][] var2 = new Matrix[var1][][][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new Matrix[var4][][];

                for(int var5 = 0; var5 < var4; ++var5) {
                    int var6 = var0[var3][var5].length;
                    var2[var3][var5] = new Matrix[var6][];

                    for(int var7 = 0; var7 < var6; ++var7) {
                        int var8 = var0[var3][var5][var7].length;
                        var2[var3][var5][var7] = new Matrix[var8];

                        for(int var9 = 0; var9 < var8; ++var9) {
                            var2[var3][var5][var7][var9] = var0[var3][var5][var7][var9].copy();
                        }
                    }
                }
            }

            return var2;
        }
    }

    public static ComplexMatrix[] copy(ComplexMatrix[] var0) {
        if (var0 == null) {
            return null;
        } else {
            int var1 = var0.length;
            ComplexMatrix[] var2 = new ComplexMatrix[var1];

            for(int var3 = 0; var3 < var1; ++var3) {
                var2[var3] = var0[var3].copy();
            }

            return var2;
        }
    }

    public static ComplexMatrix[][] copy(ComplexMatrix[][] var0) {
        if (var0 == null) {
            return (ComplexMatrix[][])null;
        } else {
            int var1 = var0.length;
            ComplexMatrix[][] var2 = new ComplexMatrix[var1][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new ComplexMatrix[var4];

                for(int var5 = 0; var5 < var4; ++var5) {
                    var2[var3][var5] = var0[var3][var5].copy();
                }
            }

            return var2;
        }
    }

    public static ComplexMatrix[][][] copy(ComplexMatrix[][][] var0) {
        if (var0 == null) {
            return (ComplexMatrix[][][])null;
        } else {
            int var1 = var0.length;
            ComplexMatrix[][][] var2 = new ComplexMatrix[var1][][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new ComplexMatrix[var4][];

                for(int var5 = 0; var5 < var4; ++var5) {
                    int var6 = var0[var3][var5].length;
                    var2[var3][var5] = new ComplexMatrix[var6];

                    for(int var7 = 0; var7 < var6; ++var7) {
                        var2[var3][var5][var7] = var0[var3][var5][var7].copy();
                    }
                }
            }

            return var2;
        }
    }

    public static ComplexMatrix[][][][] copy(ComplexMatrix[][][][] var0) {
        if (var0 == null) {
            return (ComplexMatrix[][][][])null;
        } else {
            int var1 = var0.length;
            ComplexMatrix[][][][] var2 = new ComplexMatrix[var1][][][];

            for(int var3 = 0; var3 < var1; ++var3) {
                int var4 = var0[var3].length;
                var2[var3] = new ComplexMatrix[var4][][];

                for(int var5 = 0; var5 < var4; ++var5) {
                    int var6 = var0[var3][var5].length;
                    var2[var3][var5] = new ComplexMatrix[var6][];

                    for(int var7 = 0; var7 < var6; ++var7) {
                        int var8 = var0[var3][var5][var7].length;
                        var2[var3][var5][var7] = new ComplexMatrix[var8];

                        for(int var9 = 0; var9 < var8; ++var9) {
                            var2[var3][var5][var7][var9] = var0[var3][var5][var7][var9].copy();
                        }
                    }
                }
            }

            return var2;
        }
    }

    public static Object copy(Object var0) {
        return var0 == null ? null : copyObject(var0);
    }

    public static Object copyObject(Object var0) {
        if (var0 == null) {
            return null;
        } else {
            Object var1 = null;

            try {
                ByteArrayOutputStream var2 = new ByteArrayOutputStream();
                ObjectOutputStream var3 = new ObjectOutputStream(var2);
                var3.writeObject(var0);
                var3.flush();
                var3.close();
                ObjectInputStream var4 = new ObjectInputStream(new ByteArrayInputStream(var2.toByteArray()));
                var1 = var4.readObject();
            } catch (IOException var5) {
                var5.printStackTrace();
            } catch (ClassNotFoundException var6) {
                var6.printStackTrace();
            }

            return var1;
        }
    }
}
