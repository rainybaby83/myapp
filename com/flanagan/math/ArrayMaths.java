//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.math;

import com.flanagan.analysis.Stat;
import com.flanagan.circuits.Phasor;
import com.flanagan.circuits.PhasorMatrix;
import com.flanagan.complex.Complex;
import com.flanagan.complex.ComplexMatrix;
import com.flanagan.io.PrintToScreen;
import com.flanagan.plot.PlotGraph;
import com.flanagan.util.Strings;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class ArrayMaths {
    protected ArrayList<Object> array = null;
    protected int length = 0;
    protected int type = -1;
    protected int[] originalTypes = null;
    protected String[] typeName = new String[]{"double", "Double", "float", "Float", "long", "Long", "int", "Integer", "short", "Short", "byte", "Byte", "BigDecimal", "BigInteger", "Complex", "Phasor", "char", "Character", "String"};
    protected ArrayList<Object> summ = new ArrayList(1);
    protected ArrayList<Object> productt = new ArrayList(1);
    protected int[] sortedIndices = null;
    protected ArrayList<Object> minmax = new ArrayList(2);
    protected int maxIndex = -1;
    protected int minIndex = -1;
    protected boolean sumDone = false;
    protected boolean productDone = false;
    protected boolean sumlongToDouble = false;
    protected boolean productlongToDouble = false;
    protected boolean suppressMessages = false;
    public String[] words = null;
    public String[] sortedWords = null;
    protected char[][] wordChar = (char[][])null;
    protected char[][] holdWordChar = (char[][])null;
    protected int[] wordOrder = null;
    protected int[] holdWordOrder = null;
    protected int nWords = 0;
    protected int nLength = 0;
    protected static final Map<Object, Object> integers = new HashMap();

    protected ArrayMaths() {
        this.array = new ArrayList();
    }

    public ArrayMaths(double[] var1) {
        this.length = var1.length;
        this.array = new ArrayList(this.length);
        this.type = 0;

        int var2;
        for(var2 = 0; var2 < this.length; ++var2) {
            this.array.add(new Double(var1[var2]));
        }

        this.originalTypes = new int[this.length];

        for(var2 = 0; var2 < this.length; ++var2) {
            this.originalTypes[var2] = this.type;
        }

        this.originalTypes = new int[this.length];

        for(var2 = 0; var2 < this.length; ++var2) {
            this.originalTypes[var2] = this.type;
        }

        this.sortedIndices = new int[this.length];

        for(var2 = 0; var2 < this.length; this.sortedIndices[var2] = var2++) {
            ;
        }

        this.minmax();
    }

    public ArrayMaths(Double[] var1) {
        this.length = var1.length;
        this.array = new ArrayList(this.length);
        this.type = 1;

        int var2;
        for(var2 = 0; var2 < this.length; ++var2) {
            this.array.add(var1[var2]);
        }

        this.originalTypes = new int[this.length];

        for(var2 = 0; var2 < this.length; ++var2) {
            this.originalTypes[var2] = this.type;
        }

        this.sortedIndices = new int[this.length];

        for(var2 = 0; var2 < this.length; this.sortedIndices[var2] = var2++) {
            ;
        }

        this.minmax();
    }

    public ArrayMaths(long[] var1) {
        this.length = var1.length;
        this.array = new ArrayList(this.length);
        this.type = 4;

        int var2;
        for(var2 = 0; var2 < this.length; ++var2) {
            this.array.add(new Long(var1[var2]));
        }

        this.originalTypes = new int[this.length];

        for(var2 = 0; var2 < this.length; ++var2) {
            this.originalTypes[var2] = this.type;
        }

        this.sortedIndices = new int[this.length];

        for(var2 = 0; var2 < this.length; this.sortedIndices[var2] = var2++) {
            ;
        }

        this.minmax();
    }

    public ArrayMaths(Long[] var1) {
        this.length = var1.length;
        this.array = new ArrayList(this.length);
        this.type = 5;

        int var2;
        for(var2 = 0; var2 < this.length; ++var2) {
            this.array.add(var1[var2]);
        }

        this.originalTypes = new int[this.length];

        for(var2 = 0; var2 < this.length; ++var2) {
            this.originalTypes[var2] = this.type;
        }

        this.sortedIndices = new int[this.length];

        for(var2 = 0; var2 < this.length; this.sortedIndices[var2] = var2++) {
            ;
        }

        this.minmax();
    }

    public ArrayMaths(float[] var1) {
        this.length = var1.length;
        this.array = new ArrayList(this.length);
        this.type = 2;

        int var2;
        for(var2 = 0; var2 < this.length; ++var2) {
            this.array.add(new Float(var1[var2]));
        }

        this.originalTypes = new int[this.length];

        for(var2 = 0; var2 < this.length; ++var2) {
            this.originalTypes[var2] = this.type;
        }

        this.sortedIndices = new int[this.length];

        for(var2 = 0; var2 < this.length; this.sortedIndices[var2] = var2++) {
            ;
        }

        this.minmax();
    }

    public ArrayMaths(Float[] var1) {
        this.length = var1.length;
        this.array = new ArrayList(this.length);
        this.type = 3;

        int var2;
        for(var2 = 0; var2 < this.length; ++var2) {
            this.array.add(var1[var2]);
        }

        this.originalTypes = new int[this.length];

        for(var2 = 0; var2 < this.length; ++var2) {
            this.originalTypes[var2] = this.type;
        }

        this.sortedIndices = new int[this.length];

        for(var2 = 0; var2 < this.length; this.sortedIndices[var2] = var2++) {
            ;
        }

        this.minmax();
    }

    public ArrayMaths(int[] var1) {
        this.length = var1.length;
        this.array = new ArrayList(this.length);
        this.type = 6;

        int var2;
        for(var2 = 0; var2 < this.length; ++var2) {
            this.array.add(new Integer(var1[var2]));
        }

        this.originalTypes = new int[this.length];

        for(var2 = 0; var2 < this.length; ++var2) {
            this.originalTypes[var2] = this.type;
        }

        this.sortedIndices = new int[this.length];

        for(var2 = 0; var2 < this.length; this.sortedIndices[var2] = var2++) {
            ;
        }

        this.minmax();
    }

    public ArrayMaths(Integer[] var1) {
        this.length = var1.length;
        this.array = new ArrayList(this.length);
        this.type = 7;

        int var2;
        for(var2 = 0; var2 < this.length; ++var2) {
            this.array.add(var1[var2]);
        }

        this.originalTypes = new int[this.length];

        for(var2 = 0; var2 < this.length; ++var2) {
            this.originalTypes[var2] = this.type;
        }

        this.sortedIndices = new int[this.length];

        for(var2 = 0; var2 < this.length; this.sortedIndices[var2] = var2++) {
            ;
        }

        this.minmax();
    }

    public ArrayMaths(short[] var1) {
        this.length = var1.length;
        this.array = new ArrayList(this.length);
        this.type = 8;

        int var2;
        for(var2 = 0; var2 < this.length; ++var2) {
            this.array.add(new Short(var1[var2]));
        }

        this.originalTypes = new int[this.length];

        for(var2 = 0; var2 < this.length; ++var2) {
            this.originalTypes[var2] = this.type;
        }

        this.sortedIndices = new int[this.length];

        for(var2 = 0; var2 < this.length; this.sortedIndices[var2] = var2++) {
            ;
        }

        this.minmax();
    }

    public ArrayMaths(Short[] var1) {
        this.length = var1.length;
        this.array = new ArrayList(this.length);
        this.type = 9;

        int var2;
        for(var2 = 0; var2 < this.length; ++var2) {
            this.array.add(var1[var2]);
        }

        this.originalTypes = new int[this.length];

        for(var2 = 0; var2 < this.length; ++var2) {
            this.originalTypes[var2] = this.type;
        }

        this.sortedIndices = new int[this.length];

        for(var2 = 0; var2 < this.length; this.sortedIndices[var2] = var2++) {
            ;
        }

        this.minmax();
    }

    public ArrayMaths(byte[] var1) {
        this.length = var1.length;
        this.array = new ArrayList(this.length);
        this.type = 10;

        int var2;
        for(var2 = 0; var2 < this.length; ++var2) {
            this.array.add(new Byte(var1[var2]));
        }

        this.originalTypes = new int[this.length];

        for(var2 = 0; var2 < this.length; ++var2) {
            this.originalTypes[var2] = this.type;
        }

        this.sortedIndices = new int[this.length];

        for(var2 = 0; var2 < this.length; this.sortedIndices[var2] = var2++) {
            ;
        }

        this.minmax();
    }

    public ArrayMaths(Byte[] var1) {
        this.length = var1.length;
        this.array = new ArrayList(this.length);
        this.type = 11;

        int var2;
        for(var2 = 0; var2 < this.length; ++var2) {
            this.array.add(var1[var2]);
        }

        this.originalTypes = new int[this.length];

        for(var2 = 0; var2 < this.length; ++var2) {
            this.originalTypes[var2] = this.type;
        }

        this.sortedIndices = new int[this.length];

        for(var2 = 0; var2 < this.length; this.sortedIndices[var2] = var2++) {
            ;
        }

        this.minmax();
    }

    public ArrayMaths(BigDecimal[] var1) {
        this.length = var1.length;
        this.array = new ArrayList(this.length);
        this.type = 12;

        int var2;
        for(var2 = 0; var2 < this.length; ++var2) {
            this.array.add(var1[var2]);
        }

        this.originalTypes = new int[this.length];

        for(var2 = 0; var2 < this.length; ++var2) {
            this.originalTypes[var2] = this.type;
        }

        this.sortedIndices = new int[this.length];

        for(var2 = 0; var2 < this.length; this.sortedIndices[var2] = var2++) {
            ;
        }

        this.minmax();
    }

    public ArrayMaths(BigInteger[] var1) {
        this.length = var1.length;
        this.array = new ArrayList(this.length);
        this.type = 13;

        int var2;
        for(var2 = 0; var2 < this.length; ++var2) {
            this.array.add(var1[var2]);
        }

        this.originalTypes = new int[this.length];

        for(var2 = 0; var2 < this.length; ++var2) {
            this.originalTypes[var2] = this.type;
        }

        this.sortedIndices = new int[this.length];

        for(var2 = 0; var2 < this.length; this.sortedIndices[var2] = var2++) {
            ;
        }

        this.minmax();
    }

    public ArrayMaths(Complex[] var1) {
        this.length = var1.length;
        this.array = new ArrayList(this.length);
        this.type = 14;

        int var2;
        for(var2 = 0; var2 < this.length; ++var2) {
            this.array.add(var1[var2]);
        }

        this.originalTypes = new int[this.length];

        for(var2 = 0; var2 < this.length; ++var2) {
            this.originalTypes[var2] = this.type;
        }

        this.sortedIndices = new int[this.length];

        for(var2 = 0; var2 < this.length; this.sortedIndices[var2] = var2++) {
            ;
        }

    }

    public ArrayMaths(Phasor[] var1) {
        this.length = var1.length;
        this.array = new ArrayList(this.length);
        this.type = 15;

        int var2;
        for(var2 = 0; var2 < this.length; ++var2) {
            this.array.add(var1[var2]);
        }

        this.originalTypes = new int[this.length];

        for(var2 = 0; var2 < this.length; ++var2) {
            this.originalTypes[var2] = this.type;
        }

        this.sortedIndices = new int[this.length];

        for(var2 = 0; var2 < this.length; this.sortedIndices[var2] = var2++) {
            ;
        }

    }

    public ArrayMaths(char[] var1) {
        this.length = var1.length;
        this.array = new ArrayList(this.length);
        this.type = 16;

        int var2;
        for(var2 = 0; var2 < this.length; ++var2) {
            this.array.add(new Character(var1[var2]));
        }

        this.originalTypes = new int[this.length];

        for(var2 = 0; var2 < this.length; ++var2) {
            this.originalTypes[var2] = this.type;
        }

        this.sortedIndices = new int[this.length];

        for(var2 = 0; var2 < this.length; this.sortedIndices[var2] = var2++) {
            ;
        }

        this.minmax();
    }

    public ArrayMaths(Character[] var1) {
        this.length = var1.length;
        this.array = new ArrayList(this.length);
        this.type = 17;

        int var2;
        for(var2 = 0; var2 < this.length; ++var2) {
            this.array.add(var1[var2]);
        }

        this.originalTypes = new int[this.length];

        for(var2 = 0; var2 < this.length; ++var2) {
            this.originalTypes[var2] = this.type;
        }

        this.sortedIndices = new int[this.length];

        for(var2 = 0; var2 < this.length; this.sortedIndices[var2] = var2++) {
            ;
        }

        this.minmax();
    }

    public ArrayMaths(String[] var1) {
        this.length = var1.length;
        this.array = new ArrayList(this.length);
        this.type = 18;

        int var2;
        for(var2 = 0; var2 < this.length; ++var2) {
            this.array.add(var1[var2]);
        }

        this.originalTypes = new int[this.length];

        for(var2 = 0; var2 < this.length; ++var2) {
            this.originalTypes[var2] = this.type;
        }

        this.sortedIndices = new int[this.length];

        for(var2 = 0; var2 < this.length; this.sortedIndices[var2] = var2++) {
            ;
        }

    }

    public ArrayMaths(Object[] var1) {
        this.length = var1.length;
        this.originalTypes = new int[this.length];
        ArrayList var2 = new ArrayList(this.length);

        for(int var3 = 0; var3 < this.length; ++var3) {
            var2.add(var1[var3]);
        }

        ArrayMaths var5 = new ArrayMaths(var2);
        this.array = var5.getArray_as_ArrayList();
        this.minmax = var5.minmax;
        this.minIndex = var5.minIndex;
        this.maxIndex = var5.maxIndex;
        this.originalTypes = var5.originalTypes;
        this.sortedIndices = new int[this.length];

        for(int var4 = 0; var4 < this.length; this.sortedIndices[var4] = var4++) {
            ;
        }

    }

    public ArrayMaths(Stat var1) {
        this.array = var1.getArray_as_ArrayList();
        this.length = this.array.size();
        this.type = var1.typeIndex();
        this.originalTypes = new int[this.length];

        int var2;
        for(var2 = 0; var2 < this.length; ++var2) {
            this.originalTypes[var2] = this.type;
        }

        this.sortedIndices = new int[this.length];

        for(var2 = 0; var2 < this.length; this.sortedIndices[var2] = var2++) {
            ;
        }

        this.minmax();
    }

    public ArrayMaths(Vector<Object> var1) {
        this.length = var1.size();
        this.originalTypes = new int[this.length];
        this.array = new ArrayList(this.length);
        this.sortedIndices = new int[this.length];

        int var2;
        for(var2 = 0; var2 < this.length; this.sortedIndices[var2] = var2++) {
            ;
        }

        for(var2 = 0; var2 < this.length; ++var2) {
            this.originalTypes[var2] = -1;
            if (var1.elementAt(var2) instanceof Double) {
                this.originalTypes[var2] = 1;
            }

            if (var1.elementAt(var2) instanceof Float) {
                this.originalTypes[var2] = 3;
            }

            if (var1.elementAt(var2) instanceof Long) {
                this.originalTypes[var2] = 5;
            }

            if (var1.elementAt(var2) instanceof Integer) {
                this.originalTypes[var2] = 7;
            }

            if (var1.elementAt(var2) instanceof Short) {
                this.originalTypes[var2] = 9;
            }

            if (var1.elementAt(var2) instanceof Byte) {
                this.originalTypes[var2] = 11;
            }

            if (var1.elementAt(var2) instanceof BigDecimal) {
                this.originalTypes[var2] = 12;
            }

            if (var1.elementAt(var2) instanceof BigInteger) {
                this.originalTypes[var2] = 13;
            }

            if (var1.elementAt(var2) instanceof Complex) {
                this.originalTypes[var2] = 14;
            }

            if (var1.elementAt(var2) instanceof Phasor) {
                this.originalTypes[var2] = 15;
            }

            if (var1.elementAt(var2) instanceof Character) {
                this.originalTypes[var2] = 17;
            }

            if (var1.elementAt(var2) instanceof String) {
                this.originalTypes[var2] = 18;
            }

            if (this.originalTypes[var2] == -1) {
                throw new IllegalArgumentException("Object at " + var2 + " not recognised as one allowed by this class");
            }
        }

        byte var16 = -1;

        int var3;
        for(var3 = 0; var3 < this.length; ++var3) {
            if (this.originalTypes[var3] == 18) {
                var16 = 0;
            }
        }

        for(var3 = 0; var3 < this.length; ++var3) {
            if (this.originalTypes[var3] == 14) {
                var16 = 1;
            }
        }

        if (var16 == -1) {
            for(var3 = 0; var3 < this.length; ++var3) {
                if (this.originalTypes[var3] == 15) {
                    var16 = 2;
                }
            }
        }

        if (var16 == -1) {
            for(var3 = 0; var3 < this.length; ++var3) {
                if (this.originalTypes[var3] == 12) {
                    var16 = 3;
                }
            }
        }

        if (var16 == -1) {
            for(var3 = 0; var3 < this.length; ++var3) {
                if (this.originalTypes[var3] == 13) {
                    var16 = 4;
                }
            }
        }

        if (var16 == 4) {
            for(var3 = 0; var3 < this.length; ++var3) {
                if (this.originalTypes[var3] <= 3) {
                    var16 = 3;
                }
            }
        }

        if (var16 == -1) {
            for(var3 = 0; var3 < this.length; ++var3) {
                if (this.originalTypes[var3] <= 3) {
                    var16 = 5;
                }
            }
        }

        if (var16 == -1) {
            for(var3 = 0; var3 < this.length; ++var3) {
                if (this.originalTypes[var3] > 3 && this.originalTypes[var3] < 12) {
                    var16 = 6;
                }
            }
        }

        if (var16 == -1) {
            for(var3 = 0; var3 < this.length; ++var3) {
                if (this.originalTypes[var3] == 17) {
                    var16 = 7;
                }
            }
        }

        if (var16 == -1) {
            throw new IllegalArgumentException("It should not be possible to reach this exception - main Object type not identified");
        } else {
            Byte var9;
            BigInteger var11;
            Character var12;
            Phasor var13;
            Character var14;
            String var15;
            Double var18;
            Float var19;
            Long var20;
            Integer var21;
            Short var22;
            BigDecimal var23;
            Complex var24;
            label312:
            switch(var16) {
                case 0:
                    this.type = 18;
                    var3 = 0;

                    while(true) {
                        if (var3 >= this.length) {
                            break label312;
                        }

                        switch(this.originalTypes[var3]) {
                            case 1:
                                var18 = (Double)var1.elementAt(var3);
                                this.array.add(var18.toString());
                                break;
                            case 2:
                            case 4:
                            case 6:
                            case 8:
                            case 10:
                            case 16:
                            default:
                                throw new IllegalArgumentException("Data type not identified by this method");
                            case 3:
                                var19 = (Float)var1.elementAt(var3);
                                this.array.add(var19.toString());
                                break;
                            case 5:
                                var20 = (Long)var1.elementAt(var3);
                                this.array.add(var20.toString());
                                break;
                            case 7:
                                var21 = (Integer)var1.elementAt(var3);
                                this.array.add(var21.toString());
                                break;
                            case 9:
                                var22 = (Short)var1.elementAt(var3);
                                this.array.add(var22.toString());
                                break;
                            case 11:
                                var9 = (Byte)var1.elementAt(var3);
                                this.array.add(var9.toString());
                                break;
                            case 12:
                                var23 = (BigDecimal)var1.elementAt(var3);
                                this.array.add(var23.toString());
                                break;
                            case 13:
                                var11 = (BigInteger)var1.elementAt(var3);
                                this.array.add(var11.toString());
                                break;
                            case 14:
                                var24 = (Complex)var1.elementAt(var3);
                                this.array.add(var24.toString());
                                break;
                            case 15:
                                var13 = (Phasor)var1.elementAt(var3);
                                this.array.add(var13.toString());
                                break;
                            case 17:
                                var14 = (Character)var1.elementAt(var3);
                                this.array.add(var14.toString());
                                break;
                            case 18:
                                var15 = (String)var1.elementAt(var3);
                                this.array.add(var15);
                        }

                        ++var3;
                    }
                case 1:
                    this.type = 14;
                    var3 = 0;

                    while(true) {
                        if (var3 >= this.length) {
                            break label312;
                        }

                        switch(this.originalTypes[var3]) {
                            case 1:
                                var18 = (Double)var1.elementAt(var3);
                                this.array.add(new Complex(var18));
                                break;
                            case 2:
                            case 4:
                            case 6:
                            case 8:
                            case 10:
                            case 16:
                            default:
                                throw new IllegalArgumentException("Data type not identified by this method");
                            case 3:
                                var19 = (Float)var1.elementAt(var3);
                                this.array.add(new Complex(var19.doubleValue()));
                                break;
                            case 5:
                                var20 = (Long)var1.elementAt(var3);
                                this.array.add(new Complex(var20.doubleValue()));
                                break;
                            case 7:
                                var21 = (Integer)var1.elementAt(var3);
                                this.array.add(new Complex(var21.doubleValue()));
                                break;
                            case 9:
                                var22 = (Short)var1.elementAt(var3);
                                this.array.add(new Complex(var22.doubleValue()));
                                break;
                            case 11:
                                var9 = (Byte)var1.elementAt(var3);
                                this.array.add(new Complex(var9.doubleValue()));
                                break;
                            case 12:
                                var23 = (BigDecimal)var1.elementAt(var3);
                                this.array.add(new Complex(var23.doubleValue()));
                                break;
                            case 13:
                                var11 = (BigInteger)var1.elementAt(var3);
                                this.array.add(new Complex(var11.doubleValue()));
                                break;
                            case 14:
                                var24 = (Complex)var1.elementAt(var3);
                                this.array.add(var24);
                                break;
                            case 15:
                                var13 = (Phasor)var1.elementAt(var3);
                                this.array.add(Conv.convert_Phasor_to_Complex(var13));
                                break;
                            case 17:
                                var14 = (Character)var1.elementAt(var3);
                                this.array.add(new Complex((double)var14));
                                break;
                            case 18:
                                var15 = (String)var1.elementAt(var3);
                                this.array.add(new Complex(Double.parseDouble(var15)));
                        }

                        ++var3;
                    }
                case 2:
                    this.type = 15;
                    var3 = 0;

                    while(true) {
                        if (var3 >= this.length) {
                            break label312;
                        }

                        switch(this.originalTypes[var3]) {
                            case 1:
                                var18 = (Double)var1.elementAt(var3);
                                this.array.add(new Phasor(var18));
                                break;
                            case 2:
                            case 4:
                            case 6:
                            case 8:
                            case 10:
                            case 16:
                            default:
                                throw new IllegalArgumentException("Data type not identified by this method");
                            case 3:
                                var19 = (Float)var1.elementAt(var3);
                                this.array.add(new Phasor(var19.doubleValue()));
                                break;
                            case 5:
                                var20 = (Long)var1.elementAt(var3);
                                this.array.add(new Phasor(var20.doubleValue()));
                                break;
                            case 7:
                                var21 = (Integer)var1.elementAt(var3);
                                this.array.add(new Phasor(var21.doubleValue()));
                                break;
                            case 9:
                                var22 = (Short)var1.elementAt(var3);
                                this.array.add(new Phasor(var22.doubleValue()));
                                break;
                            case 11:
                                var9 = (Byte)var1.elementAt(var3);
                                this.array.add(new Phasor(var9.doubleValue()));
                                break;
                            case 12:
                                var23 = (BigDecimal)var1.elementAt(var3);
                                this.array.add(new Phasor(var23.doubleValue()));
                                break;
                            case 13:
                                var11 = (BigInteger)var1.elementAt(var3);
                                this.array.add(new Phasor(var11.doubleValue()));
                                break;
                            case 14:
                                var24 = (Complex)var1.elementAt(var3);
                                this.array.add(Conv.convert_Complex_to_Phasor(var24));
                                break;
                            case 15:
                                var13 = (Phasor)var1.elementAt(var3);
                                this.array.add(var13);
                                break;
                            case 17:
                                var14 = (Character)var1.elementAt(var3);
                                this.array.add(new Phasor((double)var14));
                        }

                        ++var3;
                    }
                case 3:
                    this.type = 12;
                    var3 = 0;

                    while(true) {
                        if (var3 >= this.length) {
                            break label312;
                        }

                        switch(this.originalTypes[var3]) {
                            case 1:
                                var18 = (Double)var1.elementAt(var3);
                                this.array.add(Conv.convert_Double_to_BigDecimal(var18));
                                break;
                            case 2:
                            case 4:
                            case 6:
                            case 8:
                            case 10:
                            case 14:
                            case 15:
                            case 16:
                            default:
                                throw new IllegalArgumentException("Data type not identified by this method");
                            case 3:
                                var19 = (Float)var1.elementAt(var3);
                                this.array.add(Conv.convert_Float_to_BigDecimal(var19));
                                break;
                            case 5:
                                var20 = (Long)var1.elementAt(var3);
                                this.array.add(Conv.convert_Long_to_BigDecimal(var20));
                                break;
                            case 7:
                                var21 = (Integer)var1.elementAt(var3);
                                this.array.add(Conv.convert_Integer_to_BigDecimal(var21));
                                break;
                            case 9:
                                var22 = (Short)var1.elementAt(var3);
                                this.array.add(Conv.convert_Short_to_BigDecimal(var22));
                                break;
                            case 11:
                                var9 = (Byte)var1.elementAt(var3);
                                this.array.add(Conv.convert_Byte_to_BigDecimal(var9));
                                break;
                            case 12:
                                var23 = (BigDecimal)var1.elementAt(var3);
                                this.array.add(var23);
                                break;
                            case 13:
                                var11 = (BigInteger)var1.elementAt(var3);
                                this.array.add(Conv.convert_BigInteger_to_BigDecimal(var11));
                                break;
                            case 17:
                                var12 = (Character)var1.elementAt(var3);
                                this.array.add(new BigDecimal(var12.toString()));
                        }

                        ++var3;
                    }
                case 4:
                    this.type = 13;
                    var3 = 0;

                    while(true) {
                        if (var3 >= this.length) {
                            break label312;
                        }

                        switch(this.originalTypes[var3]) {
                            case 1:
                                var18 = (Double)var1.elementAt(var3);
                                this.array.add(Conv.convert_Double_to_BigInteger(var18));
                                break;
                            case 2:
                            case 4:
                            case 6:
                            case 8:
                            case 10:
                            case 14:
                            case 15:
                            case 16:
                            default:
                                throw new IllegalArgumentException("Data type not identified by this method");
                            case 3:
                                var19 = (Float)var1.elementAt(var3);
                                this.array.add(Conv.convert_Float_to_BigInteger(var19));
                                break;
                            case 5:
                                var20 = (Long)var1.elementAt(var3);
                                this.array.add(Conv.convert_Long_to_BigInteger(var20));
                                break;
                            case 7:
                                var21 = (Integer)var1.elementAt(var3);
                                this.array.add(Conv.convert_Integer_to_BigInteger(var21));
                                break;
                            case 9:
                                var22 = (Short)var1.elementAt(var3);
                                this.array.add(Conv.convert_Short_to_BigInteger(var22));
                                break;
                            case 11:
                                var9 = (Byte)var1.elementAt(var3);
                                this.array.add(Conv.convert_Byte_to_BigInteger(var9));
                                break;
                            case 12:
                                var23 = (BigDecimal)var1.elementAt(var3);
                                this.array.add(Conv.convert_BigDecimal_to_BigInteger(var23));
                                break;
                            case 13:
                                var11 = (BigInteger)var1.elementAt(var3);
                                this.array.add(var11);
                                break;
                            case 17:
                                var12 = (Character)var1.elementAt(var3);
                                this.array.add(new BigInteger(var12.toString()));
                        }

                        ++var3;
                    }
                case 5:
                    this.type = 1;
                    var3 = 0;

                    while(true) {
                        if (var3 >= this.length) {
                            break label312;
                        }

                        switch(this.originalTypes[var3]) {
                            case 1:
                                var18 = (Double)var1.elementAt(var3);
                                this.array.add(var18);
                                break;
                            case 2:
                            case 4:
                            case 6:
                            case 8:
                            case 10:
                            case 12:
                            case 13:
                            case 14:
                            case 15:
                            case 16:
                            default:
                                throw new IllegalArgumentException("Data type not identified by this method");
                            case 3:
                                var19 = (Float)var1.elementAt(var3);
                                this.array.add(Conv.convert_Float_to_Double(var19));
                                break;
                            case 5:
                                var20 = (Long)var1.elementAt(var3);
                                this.array.add(Conv.convert_Long_to_Double(var20));
                                break;
                            case 7:
                                var21 = (Integer)var1.elementAt(var3);
                                this.array.add(Conv.convert_Integer_to_Double(var21));
                                break;
                            case 9:
                                var22 = (Short)var1.elementAt(var3);
                                this.array.add(Conv.convert_Short_to_Double(var22));
                                break;
                            case 11:
                                var9 = (Byte)var1.elementAt(var3);
                                this.array.add(Conv.convert_Byte_to_Double(var9));
                                break;
                            case 17:
                                Character var10 = (Character)var1.elementAt(var3);
                                this.array.add(new Double(Double.parseDouble(var10.toString())));
                        }

                        ++var3;
                    }
                case 6:
                    this.type = 7;
                    var3 = 0;

                    while(true) {
                        if (var3 >= this.length) {
                            break label312;
                        }

                        switch(this.originalTypes[var3]) {
                            case 5:
                                Long var17 = (Long)var1.elementAt(var3);
                                this.array.add(var17);
                                break;
                            case 6:
                            case 8:
                            case 10:
                            case 12:
                            case 13:
                            case 14:
                            case 15:
                            case 16:
                            default:
                                throw new IllegalArgumentException("Data type not identified by this method");
                            case 7:
                                Integer var5 = (Integer)var1.elementAt(var3);
                                this.array.add(Conv.convert_Integer_to_Long(var5));
                                break;
                            case 9:
                                Short var6 = (Short)var1.elementAt(var3);
                                this.array.add(Conv.convert_Short_to_Long(var6));
                                break;
                            case 11:
                                Byte var7 = (Byte)var1.elementAt(var3);
                                this.array.add(Conv.convert_Byte_to_Long(var7));
                                break;
                            case 17:
                                Character var8 = (Character)var1.elementAt(var3);
                                this.array.add(new Long((long)var8));
                        }

                        ++var3;
                    }
                case 7:
                    this.type = 7;
                    var3 = 0;

                    while(true) {
                        if (var3 >= this.length) {
                            break label312;
                        }

                        switch(this.originalTypes[var3]) {
                            case 17:
                                Character var4 = (Character)var1.elementAt(var3);
                                this.array.add(var4);
                                ++var3;
                                break;
                            default:
                                throw new IllegalArgumentException("Data type not identified by this method");
                        }
                    }
                default:
                    throw new IllegalArgumentException("Dominant array data type not identified by this method");
            }

            switch(this.type) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 16:
                    this.minmax();
                case 14:
                case 15:
                default:
            }
        }
    }

    public ArrayMaths(ArrayList<Object> var1) {
        this.length = var1.size();
        this.originalTypes = new int[this.length];
        this.array = new ArrayList(this.length);
        this.sortedIndices = new int[this.length];

        int var2;
        for(var2 = 0; var2 < this.length; this.sortedIndices[var2] = var2++) {
            ;
        }

        for(var2 = 0; var2 < this.length; ++var2) {
            this.originalTypes[var2] = -1;
            if (var1.get(var2) instanceof Double) {
                this.originalTypes[var2] = 1;
            }

            if (var1.get(var2) instanceof Float) {
                this.originalTypes[var2] = 3;
            }

            if (var1.get(var2) instanceof Long) {
                this.originalTypes[var2] = 5;
            }

            if (var1.get(var2) instanceof Integer) {
                this.originalTypes[var2] = 7;
            }

            if (var1.get(var2) instanceof Short) {
                this.originalTypes[var2] = 9;
            }

            if (var1.get(var2) instanceof Byte) {
                this.originalTypes[var2] = 11;
            }

            if (var1.get(var2) instanceof BigDecimal) {
                this.originalTypes[var2] = 12;
            }

            if (var1.get(var2) instanceof BigInteger) {
                this.originalTypes[var2] = 13;
            }

            if (var1.get(var2) instanceof Complex) {
                this.originalTypes[var2] = 14;
            }

            if (var1.get(var2) instanceof Phasor) {
                this.originalTypes[var2] = 15;
            }

            if (var1.get(var2) instanceof Character) {
                this.originalTypes[var2] = 17;
            }

            if (var1.get(var2) instanceof String) {
                this.originalTypes[var2] = 18;
            }

            if (this.originalTypes[var2] == -1) {
                throw new IllegalArgumentException("Object at " + var2 + " not recognised as one allowed by this class");
            }
        }

        byte var16 = -1;

        int var3;
        for(var3 = 0; var3 < this.length; ++var3) {
            if (this.originalTypes[var3] == 18) {
                var16 = 0;
            }
        }

        for(var3 = 0; var3 < this.length; ++var3) {
            if (this.originalTypes[var3] == 14) {
                var16 = 1;
            }
        }

        if (var16 == -1) {
            for(var3 = 0; var3 < this.length; ++var3) {
                if (this.originalTypes[var3] == 15) {
                    var16 = 2;
                }
            }
        }

        if (var16 == -1) {
            for(var3 = 0; var3 < this.length; ++var3) {
                if (this.originalTypes[var3] == 12) {
                    var16 = 3;
                }
            }
        }

        if (var16 == -1) {
            for(var3 = 0; var3 < this.length; ++var3) {
                if (this.originalTypes[var3] == 13) {
                    var16 = 4;
                }
            }
        }

        if (var16 == 4) {
            for(var3 = 0; var3 < this.length; ++var3) {
                if (this.originalTypes[var3] <= 3) {
                    var16 = 3;
                }
            }
        }

        if (var16 == -1) {
            for(var3 = 0; var3 < this.length; ++var3) {
                if (this.originalTypes[var3] <= 3) {
                    var16 = 5;
                }
            }
        }

        if (var16 == -1) {
            for(var3 = 0; var3 < this.length; ++var3) {
                if (this.originalTypes[var3] > 3 && this.originalTypes[var3] < 12) {
                    var16 = 6;
                }
            }
        }

        if (var16 == -1) {
            for(var3 = 0; var3 < this.length; ++var3) {
                if (this.originalTypes[var3] == 17) {
                    var16 = 7;
                }
            }
        }

        if (var16 == -1) {
            throw new IllegalArgumentException("It should not be possible to reach this exception - main Object type not identified");
        } else {
            Byte var9;
            BigInteger var11;
            Character var12;
            Phasor var13;
            Character var14;
            String var15;
            Double var18;
            Float var19;
            Long var20;
            Integer var21;
            Short var22;
            BigDecimal var23;
            Complex var24;
            label312:
            switch(var16) {
                case 0:
                    this.type = 18;
                    var3 = 0;

                    while(true) {
                        if (var3 >= this.length) {
                            break label312;
                        }

                        switch(this.originalTypes[var3]) {
                            case 1:
                                var18 = (Double)var1.get(var3);
                                this.array.add(var18.toString());
                                break;
                            case 2:
                            case 4:
                            case 6:
                            case 8:
                            case 10:
                            case 16:
                            default:
                                throw new IllegalArgumentException("Data type not identified by this method");
                            case 3:
                                var19 = (Float)var1.get(var3);
                                this.array.add(var19.toString());
                                break;
                            case 5:
                                var20 = (Long)var1.get(var3);
                                this.array.add(var20.toString());
                                break;
                            case 7:
                                var21 = (Integer)var1.get(var3);
                                this.array.add(var21.toString());
                                break;
                            case 9:
                                var22 = (Short)var1.get(var3);
                                this.array.add(var22.toString());
                                break;
                            case 11:
                                var9 = (Byte)var1.get(var3);
                                this.array.add(var9.toString());
                                break;
                            case 12:
                                var23 = (BigDecimal)var1.get(var3);
                                this.array.add(var23.toString());
                                break;
                            case 13:
                                var11 = (BigInteger)var1.get(var3);
                                this.array.add(var11.toString());
                                break;
                            case 14:
                                var24 = (Complex)var1.get(var3);
                                this.array.add(var24.toString());
                                break;
                            case 15:
                                var13 = (Phasor)var1.get(var3);
                                this.array.add(var13.toString());
                                break;
                            case 17:
                                var14 = (Character)var1.get(var3);
                                this.array.add(var14.toString());
                                break;
                            case 18:
                                var15 = (String)var1.get(var3);
                                this.array.add(var15);
                        }

                        ++var3;
                    }
                case 1:
                    this.type = 14;
                    var3 = 0;

                    while(true) {
                        if (var3 >= this.length) {
                            break label312;
                        }

                        switch(this.originalTypes[var3]) {
                            case 1:
                                var18 = (Double)var1.get(var3);
                                this.array.add(new Complex(var18));
                                break;
                            case 2:
                            case 4:
                            case 6:
                            case 8:
                            case 10:
                            case 16:
                            default:
                                throw new IllegalArgumentException("Data type not identified by this method");
                            case 3:
                                var19 = (Float)var1.get(var3);
                                this.array.add(new Complex(var19.doubleValue()));
                                break;
                            case 5:
                                var20 = (Long)var1.get(var3);
                                this.array.add(new Complex(var20.doubleValue()));
                                break;
                            case 7:
                                var21 = (Integer)var1.get(var3);
                                this.array.add(new Complex(var21.doubleValue()));
                                break;
                            case 9:
                                var22 = (Short)var1.get(var3);
                                this.array.add(new Complex(var22.doubleValue()));
                                break;
                            case 11:
                                var9 = (Byte)var1.get(var3);
                                this.array.add(new Complex(var9.doubleValue()));
                                break;
                            case 12:
                                var23 = (BigDecimal)var1.get(var3);
                                this.array.add(new Complex(var23.doubleValue()));
                                break;
                            case 13:
                                var11 = (BigInteger)var1.get(var3);
                                this.array.add(new Complex(var11.doubleValue()));
                                break;
                            case 14:
                                var24 = (Complex)var1.get(var3);
                                this.array.add(var24);
                                break;
                            case 15:
                                var13 = (Phasor)var1.get(var3);
                                this.array.add(Conv.convert_Phasor_to_Complex(var13));
                                break;
                            case 17:
                                var14 = (Character)var1.get(var3);
                                this.array.add(new Complex((double)var14));
                                break;
                            case 18:
                                var15 = (String)var1.get(var3);
                                this.array.add(new Complex(Double.parseDouble(var15)));
                        }

                        ++var3;
                    }
                case 2:
                    this.type = 15;
                    var3 = 0;

                    while(true) {
                        if (var3 >= this.length) {
                            break label312;
                        }

                        switch(this.originalTypes[var3]) {
                            case 1:
                                var18 = (Double)var1.get(var3);
                                this.array.add(new Phasor(var18));
                                break;
                            case 2:
                            case 4:
                            case 6:
                            case 8:
                            case 10:
                            case 16:
                            default:
                                throw new IllegalArgumentException("Data type not identified by this method");
                            case 3:
                                var19 = (Float)var1.get(var3);
                                this.array.add(new Phasor(var19.doubleValue()));
                                break;
                            case 5:
                                var20 = (Long)var1.get(var3);
                                this.array.add(new Phasor(var20.doubleValue()));
                                break;
                            case 7:
                                var21 = (Integer)var1.get(var3);
                                this.array.add(new Phasor(var21.doubleValue()));
                                break;
                            case 9:
                                var22 = (Short)var1.get(var3);
                                this.array.add(new Phasor(var22.doubleValue()));
                                break;
                            case 11:
                                var9 = (Byte)var1.get(var3);
                                this.array.add(new Phasor(var9.doubleValue()));
                                break;
                            case 12:
                                var23 = (BigDecimal)var1.get(var3);
                                this.array.add(new Phasor(var23.doubleValue()));
                                break;
                            case 13:
                                var11 = (BigInteger)var1.get(var3);
                                this.array.add(new Phasor(var11.doubleValue()));
                                break;
                            case 14:
                                var24 = (Complex)var1.get(var3);
                                this.array.add(Conv.convert_Complex_to_Phasor(var24));
                                break;
                            case 15:
                                var13 = (Phasor)var1.get(var3);
                                this.array.add(var13);
                                break;
                            case 17:
                                var14 = (Character)var1.get(var3);
                                this.array.add(new Phasor((double)var14));
                        }

                        ++var3;
                    }
                case 3:
                    this.type = 12;
                    var3 = 0;

                    while(true) {
                        if (var3 >= this.length) {
                            break label312;
                        }

                        switch(this.originalTypes[var3]) {
                            case 1:
                                var18 = (Double)var1.get(var3);
                                this.array.add(Conv.convert_Double_to_BigDecimal(var18));
                                break;
                            case 2:
                            case 4:
                            case 6:
                            case 8:
                            case 10:
                            case 14:
                            case 15:
                            case 16:
                            default:
                                throw new IllegalArgumentException("Data type not identified by this method");
                            case 3:
                                var19 = (Float)var1.get(var3);
                                this.array.add(Conv.convert_Float_to_BigDecimal(var19));
                                break;
                            case 5:
                                var20 = (Long)var1.get(var3);
                                this.array.add(Conv.convert_Long_to_BigDecimal(var20));
                                break;
                            case 7:
                                var21 = (Integer)var1.get(var3);
                                this.array.add(Conv.convert_Integer_to_BigDecimal(var21));
                                break;
                            case 9:
                                var22 = (Short)var1.get(var3);
                                this.array.add(Conv.convert_Short_to_BigDecimal(var22));
                                break;
                            case 11:
                                var9 = (Byte)var1.get(var3);
                                this.array.add(Conv.convert_Byte_to_BigDecimal(var9));
                                break;
                            case 12:
                                var23 = (BigDecimal)var1.get(var3);
                                this.array.add(var23);
                                break;
                            case 13:
                                var11 = (BigInteger)var1.get(var3);
                                this.array.add(Conv.convert_BigInteger_to_BigDecimal(var11));
                                break;
                            case 17:
                                var12 = (Character)var1.get(var3);
                                this.array.add(new BigDecimal(var12.toString()));
                        }

                        ++var3;
                    }
                case 4:
                    this.type = 13;
                    var3 = 0;

                    while(true) {
                        if (var3 >= this.length) {
                            break label312;
                        }

                        switch(this.originalTypes[var3]) {
                            case 1:
                                var18 = (Double)var1.get(var3);
                                this.array.add(Conv.convert_Double_to_BigInteger(var18));
                                break;
                            case 2:
                            case 4:
                            case 6:
                            case 8:
                            case 10:
                            case 14:
                            case 15:
                            case 16:
                            default:
                                throw new IllegalArgumentException("Data type not identified by this method");
                            case 3:
                                var19 = (Float)var1.get(var3);
                                this.array.add(Conv.convert_Float_to_BigInteger(var19));
                                break;
                            case 5:
                                var20 = (Long)var1.get(var3);
                                this.array.add(Conv.convert_Long_to_BigInteger(var20));
                                break;
                            case 7:
                                var21 = (Integer)var1.get(var3);
                                this.array.add(Conv.convert_Integer_to_BigInteger(var21));
                                break;
                            case 9:
                                var22 = (Short)var1.get(var3);
                                this.array.add(Conv.convert_Short_to_BigInteger(var22));
                                break;
                            case 11:
                                var9 = (Byte)var1.get(var3);
                                this.array.add(Conv.convert_Byte_to_BigInteger(var9));
                                break;
                            case 12:
                                var23 = (BigDecimal)var1.get(var3);
                                this.array.add(Conv.convert_BigDecimal_to_BigInteger(var23));
                                break;
                            case 13:
                                var11 = (BigInteger)var1.get(var3);
                                this.array.add(var11);
                                break;
                            case 17:
                                var12 = (Character)var1.get(var3);
                                this.array.add(new BigInteger(var12.toString()));
                        }

                        ++var3;
                    }
                case 5:
                    this.type = 1;
                    var3 = 0;

                    while(true) {
                        if (var3 >= this.length) {
                            break label312;
                        }

                        switch(this.originalTypes[var3]) {
                            case 1:
                                var18 = (Double)var1.get(var3);
                                this.array.add(var18);
                                break;
                            case 2:
                            case 4:
                            case 6:
                            case 8:
                            case 10:
                            case 12:
                            case 13:
                            case 14:
                            case 15:
                            case 16:
                            default:
                                throw new IllegalArgumentException("Data type not identified by this method");
                            case 3:
                                var19 = (Float)var1.get(var3);
                                this.array.add(Conv.convert_Float_to_Double(var19));
                                break;
                            case 5:
                                var20 = (Long)var1.get(var3);
                                this.array.add(Conv.convert_Long_to_Double(var20));
                                break;
                            case 7:
                                var21 = (Integer)var1.get(var3);
                                this.array.add(Conv.convert_Integer_to_Double(var21));
                                break;
                            case 9:
                                var22 = (Short)var1.get(var3);
                                this.array.add(Conv.convert_Short_to_Double(var22));
                                break;
                            case 11:
                                var9 = (Byte)var1.get(var3);
                                this.array.add(Conv.convert_Byte_to_Double(var9));
                                break;
                            case 17:
                                Character var10 = (Character)var1.get(var3);
                                this.array.add(new Double(Double.parseDouble(var10.toString())));
                        }

                        ++var3;
                    }
                case 6:
                    this.type = 7;
                    var3 = 0;

                    while(true) {
                        if (var3 >= this.length) {
                            break label312;
                        }

                        switch(this.originalTypes[var3]) {
                            case 5:
                                Long var17 = (Long)var1.get(var3);
                                this.array.add(var17);
                                break;
                            case 6:
                            case 8:
                            case 10:
                            case 12:
                            case 13:
                            case 14:
                            case 15:
                            case 16:
                            default:
                                throw new IllegalArgumentException("Data type not identified by this method");
                            case 7:
                                Integer var5 = (Integer)var1.get(var3);
                                this.array.add(Conv.convert_Integer_to_Long(var5));
                                break;
                            case 9:
                                Short var6 = (Short)var1.get(var3);
                                this.array.add(Conv.convert_Short_to_Long(var6));
                                break;
                            case 11:
                                Byte var7 = (Byte)var1.get(var3);
                                this.array.add(Conv.convert_Byte_to_Long(var7));
                                break;
                            case 17:
                                Character var8 = (Character)var1.get(var3);
                                this.array.add(new Long((long)var8));
                        }

                        ++var3;
                    }
                case 7:
                    this.type = 7;
                    var3 = 0;

                    while(true) {
                        if (var3 >= this.length) {
                            break label312;
                        }

                        switch(this.originalTypes[var3]) {
                            case 17:
                                Character var4 = (Character)var1.get(var3);
                                this.array.add(var4);
                                ++var3;
                                break;
                            default:
                                throw new IllegalArgumentException("Data type not identified by this method");
                        }
                    }
                default:
                    throw new IllegalArgumentException("Dominant array data type not identified by this method");
            }

            switch(this.type) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 16:
                    this.minmax();
                case 14:
                case 15:
                default:
            }
        }
    }

    public int length() {
        return this.length;
    }

    public void resetSortedIndices(int[] var1) {
        int var2 = var1.length;
        if (var2 != this.length) {
            throw new IllegalArgumentException("array mismatch in resetSortedIndices: " + var2 + "   " + this.length);
        } else {
            this.sortedIndices = DeepCopy.copy(var1);
        }
    }

    public int typeIndex() {
        return this.type;
    }

    public String arrayType() {
        return this.typeName[this.type] + "[]";
    }

    public String[] originalArrayTypes() {
        String[] var1 = new String[this.length];

        for(int var2 = 0; var2 < this.length; ++var2) {
            var1[var2] = this.typeName[this.originalTypes[var2]];
        }

        return var1;
    }

    public ArrayMaths copy() {
        ArrayMaths var1 = new ArrayMaths();
        var1.length = this.length;
        var1.maxIndex = this.maxIndex;
        var1.minIndex = this.minIndex;
        var1.sumDone = this.sumDone;
        var1.productDone = this.productDone;
        var1.sumlongToDouble = this.sumlongToDouble;
        var1.productlongToDouble = this.productlongToDouble;
        var1.type = this.type;
        if (this.originalTypes == null) {
            var1.originalTypes = null;
        } else {
            var1.originalTypes = Conv.copy(this.originalTypes);
        }

        if (this.sortedIndices == null) {
            var1.sortedIndices = null;
        } else {
            var1.sortedIndices = Conv.copy(this.sortedIndices);
        }

        var1.suppressMessages = this.suppressMessages;
        var1.minmax = new ArrayList();
        double var2;
        long var4;
        int var7;
        int var12;
        if (this.minmax.size() != 0) {
            short var8;
            switch(this.type) {
                case 0:
                case 1:
                    var2 = (Double)this.minmax.get(0);
                    var1.minmax.add(new Double(var2));
                    var2 = (Double)this.minmax.get(1);
                    var1.minmax.add(new Double(var2));
                    break;
                case 2:
                case 3:
                    float var6 = (Float)this.minmax.get(0);
                    var1.minmax.add(new Double((double)var6));
                    var6 = (Float)this.minmax.get(1);
                    var1.minmax.add(new Double((double)var6));
                    break;
                case 4:
                case 5:
                    var4 = (Long)this.minmax.get(0);
                    var1.minmax.add(new Double((double)var4));
                    var4 = (Long)this.minmax.get(1);
                    var1.minmax.add(new Long(var4));
                    break;
                case 6:
                case 7:
                    var7 = (Integer)this.minmax.get(0);
                    var1.minmax.add(new Integer(var7));
                    var7 = ((Double)this.minmax.get(1)).intValue();
                    var1.minmax.add(new Integer(var7));
                    break;
                case 8:
                case 9:
                    var8 = (Short)this.minmax.get(0);
                    var1.minmax.add(new Short(var8));
                    var8 = ((Double)this.minmax.get(1)).shortValue();
                    var1.minmax.add(new Short(var8));
                    break;
                case 10:
                case 11:
                    byte var9 = (Byte)this.minmax.get(0);
                    var1.minmax.add(new Byte(var9));
                    var8 = (short)(Byte)this.minmax.get(1);
                    var1.minmax.add(new Byte(var9));
                    break;
                case 12:
                    BigDecimal var10 = (BigDecimal)this.minmax.get(0);
                    var1.minmax.add(var10);
                    var10 = (BigDecimal)this.minmax.get(1);
                    var1.minmax.add(var10);
                    var10 = null;
                    break;
                case 13:
                    BigInteger var11 = (BigInteger)this.minmax.get(0);
                    var1.minmax.add(var11);
                    var11 = (BigInteger)this.minmax.get(1);
                    var1.minmax.add(var11);
                    var11 = null;
                case 14:
                case 15:
                default:
                    break;
                case 16:
                case 17:
                    var12 = (Integer)this.minmax.get(0);
                    var1.minmax.add(new Integer(var12));
                    var12 = ((Double)this.minmax.get(1)).intValue();
                    var1.minmax.add(new Integer(var12));
            }
        }

        var1.summ = new ArrayList();
        BigInteger var5;
        BigDecimal var17;
        double var18;
        Complex var21;
        Phasor var25;
        if (this.summ.size() != 0) {
            switch(this.type) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 18:
                    var2 = (Double)this.summ.get(0);
                    var1.summ.add(new Double(var2));
                    break;
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 16:
                case 17:
                    if (this.sumlongToDouble) {
                        var18 = (Double)this.summ.get(0);
                        var1.summ.add(new Double(var18));
                    } else {
                        var4 = (Long)this.summ.get(0);
                        var1.summ.add(new Long(var4));
                    }
                    break;
                case 12:
                    var17 = (BigDecimal)this.summ.get(0);
                    var1.summ.add(var17);
                    break;
                case 13:
                    var5 = (BigInteger)this.summ.get(0);
                    var1.summ.add(var5);
                    break;
                case 14:
                    var21 = (Complex)this.summ.get(0);
                    var1.summ.add(var21);
                    break;
                case 15:
                    var25 = (Phasor)this.summ.get(0);
                    var1.summ.add(var25);
                    break;
                default:
                    throw new IllegalArgumentException("Data type not identified by this method");
            }
        }

        var1.productt = new ArrayList();
        if (this.productt.size() != 0) {
            switch(this.type) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 18:
                    var2 = (Double)this.productt.get(0);
                    var1.productt.add(new Double(var2));
                    break;
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 16:
                case 17:
                    if (this.sumlongToDouble) {
                        var18 = (Double)this.productt.get(0);
                        var1.productt.add(new Double(var18));
                    } else {
                        var4 = (Long)this.productt.get(0);
                        var1.productt.add(new Long(var4));
                    }
                    break;
                case 12:
                    var17 = (BigDecimal)this.productt.get(0);
                    var1.productt.add(var17);
                    break;
                case 13:
                    var5 = (BigInteger)this.productt.get(0);
                    var1.productt.add(var5);
                    break;
                case 14:
                    var21 = (Complex)this.productt.get(0);
                    var1.productt.add(var21);
                    break;
                case 15:
                    var25 = (Phasor)this.productt.get(0);
                    var1.productt.add(var25);
                    break;
                default:
                    throw new IllegalArgumentException("Data type not identified by this method");
            }
        }

        switch(this.type) {
            case 0:
            case 1:
                double[] var15 = Conv.copy(this.getArray_as_double());

                for(int var16 = 0; var16 < this.length; ++var16) {
                    var1.array.add(new Double(var15[var16]));
                }

                return var1;
            case 2:
            case 3:
                float[] var3 = Conv.copy(this.getArray_as_float());

                for(int var23 = 0; var23 < this.length; ++var23) {
                    var1.array.add(new Float(var3[var23]));
                }

                return var1;
            case 4:
            case 5:
                long[] var22 = Conv.copy(this.getArray_as_long());

                for(int var20 = 0; var20 < this.length; ++var20) {
                    var1.array.add(new Long(var22[var20]));
                }

                return var1;
            case 6:
            case 7:
                int[] var19 = Conv.copy(this.getArray_as_int());

                for(int var26 = 0; var26 < this.length; ++var26) {
                    var1.array.add(new Integer(var19[var26]));
                }

                return var1;
            case 8:
            case 9:
                short[] var24 = Conv.copy(this.getArray_as_short());

                for(var7 = 0; var7 < this.length; ++var7) {
                    var1.array.add(new Short(var24[var7]));
                }

                return var1;
            case 10:
            case 11:
                byte[] var27 = Conv.copy(this.getArray_as_byte());

                for(int var29 = 0; var29 < this.length; ++var29) {
                    var1.array.add(new Byte(var27[var29]));
                }

                return var1;
            case 12:
                BigDecimal[] var28 = Conv.copy(this.getArray_as_BigDecimal());

                for(int var31 = 0; var31 < this.length; ++var31) {
                    var1.array.add(var28[var31]);
                }

                return var1;
            case 13:
                BigInteger[] var30 = Conv.copy(this.getArray_as_BigInteger());

                for(int var33 = 0; var33 < this.length; ++var33) {
                    var1.array.add(var30[var33]);
                }

                return var1;
            case 14:
                Complex[] var32 = this.getArray_as_Complex();

                for(int var35 = 0; var35 < this.length; ++var35) {
                    var1.array.add(var32[var35].copy());
                }

                return var1;
            case 15:
                Phasor[] var34 = this.getArray_as_Phasor();

                for(var12 = 0; var12 < this.length; ++var12) {
                    var1.array.add(var34[var12].copy());
                }

                return var1;
            case 16:
            case 17:
                char[] var36 = Conv.copy(this.getArray_as_char());

                for(int var37 = 0; var37 < this.length; ++var37) {
                    var1.array.add(new Character(var36[var37]));
                }

                return var1;
            case 18:
                String[] var13 = Conv.copy(this.getArray_as_String());

                for(int var14 = 0; var14 < this.length; ++var14) {
                    var1.array.add(var13[var14]);
                }
        }

        return var1;
    }

    public void suppressMessages() {
        this.suppressMessages = true;
    }

    public void restoreMessages() {
        this.suppressMessages = false;
    }

    public static void suppressMessagesTotal() {
        Conv.suppressMessagesAM();
    }

    public static void restoreMessagesTotal() {
        Conv.restoreMessagesAM();
    }

    public double[] array() {
        return this.getArray_as_double();
    }

    public double[] array_as_double() {
        return this.getArray_as_double();
    }

    public double[] getArray_as_double() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        double[] var1;
        var1 = new double[this.length];
        int var2;
        label101:
        switch(this.type) {
            case 0:
            case 1:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = (Double)this.array.get(var2);
                    ++var2;
                }
            case 2:
            case 3:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Float_to_double((Float)this.array.get(var2));
                    ++var2;
                }
            case 4:
            case 5:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Long_to_double((Long)this.array.get(var2));
                    ++var2;
                }
            case 6:
            case 7:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Integer_to_double((Integer)this.array.get(var2));
                    ++var2;
                }
            case 8:
            case 9:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Short_to_double((Short)this.array.get(var2));
                    ++var2;
                }
            case 10:
            case 11:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Byte_to_double((Byte)this.array.get(var2));
                    ++var2;
                }
            case 12:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_BigDecimal_to_double((BigDecimal)this.array.get(var2));
                    ++var2;
                }
            case 13:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_BigInteger_to_double((BigInteger)this.array.get(var2));
                    ++var2;
                }
            case 14:
            case 15:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a conversion to double is meaningful/supported");
            case 16:
            case 17:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_int_to_double((Character)this.array.get(var2));
                    ++var2;
                }
            case 18:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Double.valueOf((String)this.array.get(var2));
                    ++var2;
                }
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var1;
    }

    public Double[] array_as_Double() {
        return this.getArray_as_Double();
    }

    public Double[] getArray_as_Double() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        Double[] var1;
        var1 = new Double[this.length];
        int var2;
        label101:
        switch(this.type) {
            case 0:
            case 1:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = (Double)this.array.get(var2);
                    ++var2;
                }
            case 2:
            case 3:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Float_to_Double((Float)this.array.get(var2));
                    ++var2;
                }
            case 4:
            case 5:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Long_to_Double((Long)this.array.get(var2));
                    ++var2;
                }
            case 6:
            case 7:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Integer_to_Double((Integer)this.array.get(var2));
                    ++var2;
                }
            case 8:
            case 9:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Short_to_Double((Short)this.array.get(var2));
                    ++var2;
                }
            case 10:
            case 11:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Byte_to_Double((Byte)this.array.get(var2));
                    ++var2;
                }
            case 12:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_BigDecimal_to_Double((BigDecimal)this.array.get(var2));
                    ++var2;
                }
            case 13:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_BigInteger_to_Double((BigInteger)this.array.get(var2));
                    ++var2;
                }
            case 14:
            case 15:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a conversion to Double is meaningful/supported");
            case 16:
            case 17:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_int_to_Double((Character)this.array.get(var2));
                    ++var2;
                }
            case 18:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = new Double((String)this.array.get(var2));
                    ++var2;
                }
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var1;
    }

    public Float[] array_as_Float() {
        return this.getArray_as_Float();
    }

    public Float[] getArray_as_Float() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        Float[] var1;
        var1 = new Float[this.length];
        int var2;
        label101:
        switch(this.type) {
            case 0:
            case 1:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Double_to_Float((Double)this.array.get(var2));
                    ++var2;
                }
            case 2:
            case 3:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = (Float)this.array.get(var2);
                    ++var2;
                }
            case 4:
            case 5:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Long_to_Float((Long)this.array.get(var2));
                    ++var2;
                }
            case 6:
            case 7:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Integer_to_Float((Integer)this.array.get(var2));
                    ++var2;
                }
            case 8:
            case 9:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Short_to_Float((Short)this.array.get(var2));
                    ++var2;
                }
            case 10:
            case 11:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Byte_to_Float((Byte)this.array.get(var2));
                    ++var2;
                }
            case 12:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_BigDecimal_to_Float((BigDecimal)this.array.get(var2));
                    ++var2;
                }
            case 13:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_BigInteger_to_Float((BigInteger)this.array.get(var2));
                    ++var2;
                }
            case 14:
            case 15:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a conversion to Float is meaningful/supported");
            case 16:
            case 17:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_int_to_Float((Character)this.array.get(var2));
                    ++var2;
                }
            case 18:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = new Float((String)this.array.get(var2));
                    ++var2;
                }
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var1;
    }

    public float[] array_as_float() {
        return this.getArray_as_float();
    }

    public float[] getArray_as_float() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        float[] var1;
        var1 = new float[this.length];
        int var2;
        label101:
        switch(this.type) {
            case 0:
            case 1:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Double_to_float((Double)this.array.get(var2));
                    ++var2;
                }
            case 2:
            case 3:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = (Float)this.array.get(var2);
                    ++var2;
                }
            case 4:
            case 5:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Long_to_float((Long)this.array.get(var2));
                    ++var2;
                }
            case 6:
            case 7:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Integer_to_float((Integer)this.array.get(var2));
                    ++var2;
                }
            case 8:
            case 9:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Short_to_float((Short)this.array.get(var2));
                    ++var2;
                }
            case 10:
            case 11:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Byte_to_float((Byte)this.array.get(var2));
                    ++var2;
                }
            case 12:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_BigDecimal_to_float((BigDecimal)this.array.get(var2));
                    ++var2;
                }
            case 13:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_BigInteger_to_float((BigInteger)this.array.get(var2));
                    ++var2;
                }
            case 14:
            case 15:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a conversion to float is meaningful/supported");
            case 16:
            case 17:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_int_to_float((Character)this.array.get(var2));
                    ++var2;
                }
            case 18:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = new Float((String)this.array.get(var2));
                    ++var2;
                }
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var1;
    }

    public long[] array_as_long() {
        return this.getArray_as_long();
    }

    public long[] getArray_as_long() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        long[] var1;
        var1 = new long[this.length];
        int var2;
        label101:
        switch(this.type) {
            case 0:
            case 1:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Double_to_long((Double)this.array.get(var2));
                    ++var2;
                }
            case 2:
            case 3:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Float_to_long((Float)this.array.get(var2));
                    ++var2;
                }
            case 4:
            case 5:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = (Long)this.array.get(var2);
                    ++var2;
                }
            case 6:
            case 7:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Integer_to_long((Integer)this.array.get(var2));
                    ++var2;
                }
            case 8:
            case 9:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Short_to_long((Short)this.array.get(var2));
                    ++var2;
                }
            case 10:
            case 11:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Byte_to_long((Byte)this.array.get(var2));
                    ++var2;
                }
            case 12:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_BigDecimal_to_long((BigDecimal)this.array.get(var2));
                    ++var2;
                }
            case 13:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_BigInteger_to_long((BigInteger)this.array.get(var2));
                    ++var2;
                }
            case 14:
            case 15:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a conversion to long is meaningful/supported");
            case 16:
            case 17:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_int_to_long((Character)this.array.get(var2));
                    ++var2;
                }
            case 18:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = new Long((String)this.array.get(var2));
                    ++var2;
                }
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var1;
    }

    public Long[] array_as_Long() {
        return this.getArray_as_Long();
    }

    public Long[] getArray_as_Long() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        Long[] var1;
        var1 = new Long[this.length];
        int var2;
        label101:
        switch(this.type) {
            case 0:
            case 1:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Double_to_Long((Double)this.array.get(var2));
                    ++var2;
                }
            case 2:
            case 3:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Float_to_Long((Float)this.array.get(var2));
                    ++var2;
                }
            case 4:
            case 5:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = (Long)this.array.get(var2);
                    ++var2;
                }
            case 6:
            case 7:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Integer_to_Long((Integer)this.array.get(var2));
                    ++var2;
                }
            case 8:
            case 9:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Short_to_Long((Short)this.array.get(var2));
                    ++var2;
                }
            case 10:
            case 11:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Byte_to_Long((Byte)this.array.get(var2));
                    ++var2;
                }
            case 12:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_BigDecimal_to_Long((BigDecimal)this.array.get(var2));
                    ++var2;
                }
            case 13:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_BigInteger_to_Long((BigInteger)this.array.get(var2));
                    ++var2;
                }
            case 14:
            case 15:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a conversion to Long is meaningful/supported");
            case 16:
            case 17:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_int_to_Long((Character)this.array.get(var2));
                    ++var2;
                }
            case 18:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = new Long((String)this.array.get(var2));
                    ++var2;
                }
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var1;
    }

    public Integer[] array_as_Integer() {
        return this.getArray_as_Integer();
    }

    public Integer[] getArray_as_Integer() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        Integer[] var1;
        var1 = new Integer[this.length];
        int var2;
        label101:
        switch(this.type) {
            case 0:
            case 1:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Double_to_Integer((Double)this.array.get(var2));
                    ++var2;
                }
            case 2:
            case 3:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Float_to_Integer((Float)this.array.get(var2));
                    ++var2;
                }
            case 4:
            case 5:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Long_to_Integer((Long)this.array.get(var2));
                    ++var2;
                }
            case 6:
            case 7:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = (Integer)this.array.get(var2);
                    ++var2;
                }
            case 8:
            case 9:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Short_to_Integer((Short)this.array.get(var2));
                    ++var2;
                }
            case 10:
            case 11:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Byte_to_Integer((Byte)this.array.get(var2));
                    ++var2;
                }
            case 12:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_BigDecimal_to_Integer((BigDecimal)this.array.get(var2));
                    ++var2;
                }
            case 13:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_BigInteger_to_Integer((BigInteger)this.array.get(var2));
                    ++var2;
                }
            case 14:
            case 15:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a conversion to Integer is meaningful/supported");
            case 16:
            case 17:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = new Integer((Character)this.array.get(var2));
                    ++var2;
                }
            case 18:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = new Integer((String)this.array.get(var2));
                    ++var2;
                }
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var1;
    }

    public int[] array_as_int() {
        return this.getArray_as_int();
    }

    public int[] getArray_as_int() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        int[] var1;
        var1 = new int[this.length];
        int var2;
        label101:
        switch(this.type) {
            case 0:
            case 1:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Double_to_int((Double)this.array.get(var2));
                    ++var2;
                }
            case 2:
            case 3:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Float_to_int((Float)this.array.get(var2));
                    ++var2;
                }
            case 4:
            case 5:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Long_to_int((Long)this.array.get(var2));
                    ++var2;
                }
            case 6:
            case 7:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = (Integer)this.array.get(var2);
                    ++var2;
                }
            case 8:
            case 9:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Short_to_int((Short)this.array.get(var2));
                    ++var2;
                }
            case 10:
            case 11:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Byte_to_int((Byte)this.array.get(var2));
                    ++var2;
                }
            case 12:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_BigDecimal_to_int((BigDecimal)this.array.get(var2));
                    ++var2;
                }
            case 13:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_BigInteger_to_int((BigInteger)this.array.get(var2));
                    ++var2;
                }
            case 14:
            case 15:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a conversion to int is meaningful/supported");
            case 16:
            case 17:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = (Character)this.array.get(var2);
                    ++var2;
                }
            case 18:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = new Integer((String)this.array.get(var2));
                    ++var2;
                }
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var1;
    }

    public short[] array_as_short() {
        return this.getArray_as_short();
    }

    public short[] getArray_as_short() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        short[] var1;
        var1 = new short[this.length];
        int var2;
        label101:
        switch(this.type) {
            case 0:
            case 1:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Double_to_short((Double)this.array.get(var2));
                    ++var2;
                }
            case 2:
            case 3:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Float_to_short((Float)this.array.get(var2));
                    ++var2;
                }
            case 4:
            case 5:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Long_to_short((Long)this.array.get(var2));
                    ++var2;
                }
            case 6:
            case 7:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Integer_to_short((Integer)this.array.get(var2));
                    ++var2;
                }
            case 8:
            case 9:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = (Short)this.array.get(var2);
                    ++var2;
                }
            case 10:
            case 11:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Byte_to_short((Byte)this.array.get(var2));
                    ++var2;
                }
            case 12:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_BigDecimal_to_short((BigDecimal)this.array.get(var2));
                    ++var2;
                }
            case 13:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_BigInteger_to_short((BigInteger)this.array.get(var2));
                    ++var2;
                }
            case 14:
            case 15:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a conversion to short is meaningful/supported");
            case 16:
            case 17:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_int_to_short((Character)this.array.get(var2));
                    ++var2;
                }
            case 18:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = new Short((String)this.array.get(var2));
                    ++var2;
                }
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var1;
    }

    public Short[] array_as_Short() {
        return this.getArray_as_Short();
    }

    public Short[] getArray_as_Short() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        Short[] var1;
        var1 = new Short[this.length];
        int var2;
        label101:
        switch(this.type) {
            case 0:
            case 1:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Double_to_Short((Double)this.array.get(var2));
                    ++var2;
                }
            case 2:
            case 3:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Float_to_Short((Float)this.array.get(var2));
                    ++var2;
                }
            case 4:
            case 5:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Long_to_Short((Long)this.array.get(var2));
                    ++var2;
                }
            case 6:
            case 7:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Integer_to_Short((Integer)this.array.get(var2));
                    ++var2;
                }
            case 8:
            case 9:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = (Short)this.array.get(var2);
                    ++var2;
                }
            case 10:
            case 11:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Byte_to_Short((Byte)this.array.get(var2));
                    ++var2;
                }
            case 12:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_BigDecimal_to_Short((BigDecimal)this.array.get(var2));
                    ++var2;
                }
            case 13:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_BigInteger_to_Short((BigInteger)this.array.get(var2));
                    ++var2;
                }
            case 14:
            case 15:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a conversion to Short is meaningful/supported");
            case 16:
            case 17:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_int_to_Short((Character)this.array.get(var2));
                    ++var2;
                }
            case 18:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = new Short((String)this.array.get(var2));
                    ++var2;
                }
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var1;
    }

    public byte[] array_as_byte() {
        return this.getArray_as_byte();
    }

    public byte[] getArray_as_byte() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        byte[] var1;
        var1 = new byte[this.length];
        int var2;
        label101:
        switch(this.type) {
            case 0:
            case 1:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Double_to_byte((Double)this.array.get(var2));
                    ++var2;
                }
            case 2:
            case 3:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Float_to_byte((Float)this.array.get(var2));
                    ++var2;
                }
            case 4:
            case 5:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Long_to_byte((Long)this.array.get(var2));
                    ++var2;
                }
            case 6:
            case 7:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Integer_to_byte((Integer)this.array.get(var2));
                    ++var2;
                }
            case 8:
            case 9:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Short_to_byte((Short)this.array.get(var2));
                    ++var2;
                }
            case 10:
            case 11:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = (Byte)this.array.get(var2);
                    ++var2;
                }
            case 12:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_BigDecimal_to_byte((BigDecimal)this.array.get(var2));
                    ++var2;
                }
            case 13:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_BigInteger_to_byte((BigInteger)this.array.get(var2));
                    ++var2;
                }
            case 14:
            case 15:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a conversion to byte is meaningful/supported");
            case 16:
            case 17:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_int_to_byte((Character)this.array.get(var2));
                    ++var2;
                }
            case 18:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = new Byte((String)this.array.get(var2));
                    ++var2;
                }
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var1;
    }

    public Byte[] array_as_Byte() {
        return this.getArray_as_Byte();
    }

    public Byte[] getArray_as_Byte() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        Byte[] var1;
        var1 = new Byte[this.length];
        int var2;
        label101:
        switch(this.type) {
            case 0:
            case 1:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Double_to_Byte((Double)this.array.get(var2));
                    ++var2;
                }
            case 2:
            case 3:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Float_to_Byte((Float)this.array.get(var2));
                    ++var2;
                }
            case 4:
            case 5:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Long_to_Byte((Long)this.array.get(var2));
                    ++var2;
                }
            case 6:
            case 7:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Integer_to_Byte((Integer)this.array.get(var2));
                    ++var2;
                }
            case 8:
            case 9:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Short_to_Byte((Short)this.array.get(var2));
                    ++var2;
                }
            case 10:
            case 11:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = (Byte)this.array.get(var2);
                    ++var2;
                }
            case 12:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_BigDecimal_to_Byte((BigDecimal)this.array.get(var2));
                    ++var2;
                }
            case 13:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_BigInteger_to_Byte((BigInteger)this.array.get(var2));
                    ++var2;
                }
            case 14:
            case 15:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a conversion to Byte is meaningful/supported");
            case 16:
            case 17:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_int_to_Byte((Character)this.array.get(var2));
                    ++var2;
                }
            case 18:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = new Byte((String)this.array.get(var2));
                    ++var2;
                }
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var1;
    }

    public BigDecimal[] array_as_BigDecimal() {
        return this.getArray_as_BigDecimal();
    }

    public BigDecimal[] getArray_as_BigDecimal() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        BigDecimal[] var1;
        var1 = new BigDecimal[this.length];
        int var2;
        label101:
        switch(this.type) {
            case 0:
            case 1:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Double_to_BigDecimal((Double)this.array.get(var2));
                    ++var2;
                }
            case 2:
            case 3:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Float_to_BigDecimal((Float)this.array.get(var2));
                    ++var2;
                }
            case 4:
            case 5:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Long_to_BigDecimal((Long)this.array.get(var2));
                    ++var2;
                }
            case 6:
            case 7:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Integer_to_BigDecimal((Integer)this.array.get(var2));
                    ++var2;
                }
            case 8:
            case 9:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Short_to_BigDecimal((Short)this.array.get(var2));
                    ++var2;
                }
            case 10:
            case 11:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Byte_to_BigDecimal((Byte)this.array.get(var2));
                    ++var2;
                }
            case 12:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = (BigDecimal)this.array.get(var2);
                    ++var2;
                }
            case 13:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_BigInteger_to_BigDecimal((BigInteger)this.array.get(var2));
                    ++var2;
                }
            case 14:
            case 15:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a conversion to BigDecimal is meaningful/supported");
            case 16:
            case 17:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_int_to_BigDecimal((Character)this.array.get(var2));
                    ++var2;
                }
            case 18:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = new BigDecimal((String)this.array.get(var2));
                    ++var2;
                }
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var1;
    }

    public BigInteger[] array_as_BigInteger() {
        return this.getArray_as_BigInteger();
    }

    public BigInteger[] getArray_as_BigInteger() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        BigInteger[] var1;
        var1 = new BigInteger[this.length];
        int var2;
        label101:
        switch(this.type) {
            case 0:
            case 1:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Double_to_BigInteger((Double)this.array.get(var2));
                    ++var2;
                }
            case 2:
            case 3:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Float_to_BigInteger((Float)this.array.get(var2));
                    ++var2;
                }
            case 4:
            case 5:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Long_to_BigInteger((Long)this.array.get(var2));
                    ++var2;
                }
            case 6:
            case 7:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Integer_to_BigInteger((Integer)this.array.get(var2));
                    ++var2;
                }
            case 8:
            case 9:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Short_to_BigInteger((Short)this.array.get(var2));
                    ++var2;
                }
            case 10:
            case 11:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_Byte_to_BigInteger((Byte)this.array.get(var2));
                    ++var2;
                }
            case 12:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_BigDecimal_to_BigInteger((BigDecimal)this.array.get(var2));
                    ++var2;
                }
            case 13:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = (BigInteger)this.array.get(var2);
                    ++var2;
                }
            case 14:
            case 15:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a conversion to BigInteger is meaningful/supported");
            case 16:
            case 17:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = Conv.convert_int_to_BigInteger((Character)this.array.get(var2));
                    ++var2;
                }
            case 18:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label101;
                    }

                    var1[var2] = new BigInteger((String)this.array.get(var2));
                    ++var2;
                }
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var1;
    }

    public Complex[] array_as_Complex() {
        return this.getArray_as_Complex();
    }

    public Complex[] getArray_as_Complex() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        Complex[] var1;
        var1 = Complex.oneDarray(this.length);
        int var2;
        label127:
        switch(this.type) {
            case 0:
            case 1:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label127;
                    }

                    var1[var2] = new Complex((Double)this.array.get(var2));
                    ++var2;
                }
            case 2:
            case 3:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label127;
                    }

                    var1[var2] = new Complex(((Float)this.array.get(var2)).doubleValue());
                    ++var2;
                }
            case 4:
            case 5:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label127;
                    }

                    var1[var2] = new Complex(Conv.convert_Long_to_double((Long)this.array.get(var2)));
                    ++var2;
                }
            case 6:
            case 7:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label127;
                    }

                    var1[var2] = new Complex(Conv.convert_Integer_to_double((Integer)this.array.get(var2)));
                    ++var2;
                }
            case 8:
            case 9:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label127;
                    }

                    var1[var2] = new Complex(Conv.convert_Short_to_double((Short)this.array.get(var2)));
                    ++var2;
                }
            case 10:
            case 11:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label127;
                    }

                    var1[var2] = new Complex(Conv.convert_Byte_to_double((Byte)this.array.get(var2)));
                    ++var2;
                }
            case 12:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label127;
                    }

                    var1[var2] = new Complex(Conv.convert_BigDecimal_to_double((BigDecimal)this.array.get(var2)));
                    ++var2;
                }
            case 13:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label127;
                    }

                    var1[var2] = new Complex(Conv.convert_BigInteger_to_double((BigInteger)this.array.get(var2)));
                    ++var2;
                }
            case 14:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label127;
                    }

                    var1[var2] = (Complex)this.array.get(var2);
                    ++var2;
                }
            case 15:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label127;
                    }

                    var1[var2] = Conv.convert_Phasor_to_Complex((Phasor)this.array.get(var2));
                    ++var2;
                }
            case 16:
            case 17:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label127;
                    }

                    var1[var2] = new Complex(Conv.convert_int_to_double((Character)this.array.get(var2)));
                    ++var2;
                }
            case 18:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label127;
                    }

                    String var3 = (String)this.array.get(var2);
                    if (var3.indexOf(105) == -1 && var3.indexOf(106) == -1) {
                        var1[var2] = new Complex(Double.valueOf(var3));
                    } else {
                        var1[var2] = Complex.valueOf(var3);
                    }

                    ++var2;
                }
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var1;
    }

    public Phasor[] array_as_Phasor() {
        return this.getArray_as_Phasor();
    }

    public Phasor[] getArray_as_Phasor() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        Phasor[] var1;
        var1 = Phasor.oneDarray(this.length);
        int var2;
        label127:
        switch(this.type) {
            case 0:
            case 1:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label127;
                    }

                    var1[var2] = new Phasor((Double)this.array.get(var2));
                    ++var2;
                }
            case 2:
            case 3:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label127;
                    }

                    var1[var2] = new Phasor(((Float)this.array.get(var2)).doubleValue());
                    ++var2;
                }
            case 4:
            case 5:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label127;
                    }

                    var1[var2] = new Phasor(Conv.convert_Long_to_double((Long)this.array.get(var2)));
                    ++var2;
                }
            case 6:
            case 7:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label127;
                    }

                    var1[var2] = new Phasor(Conv.convert_Integer_to_double((Integer)this.array.get(var2)));
                    ++var2;
                }
            case 8:
            case 9:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label127;
                    }

                    var1[var2] = new Phasor(Conv.convert_Short_to_double((Short)this.array.get(var2)));
                    ++var2;
                }
            case 10:
            case 11:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label127;
                    }

                    var1[var2] = new Phasor(Conv.convert_Byte_to_double((Byte)this.array.get(var2)));
                    ++var2;
                }
            case 12:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label127;
                    }

                    var1[var2] = new Phasor(Conv.convert_BigDecimal_to_double((BigDecimal)this.array.get(var2)));
                    ++var2;
                }
            case 13:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label127;
                    }

                    var1[var2] = new Phasor(Conv.convert_BigInteger_to_double((BigInteger)this.array.get(var2)));
                    ++var2;
                }
            case 14:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label127;
                    }

                    var1[var2] = Conv.convert_Complex_to_Phasor((Complex)this.array.get(var2));
                    ++var2;
                }
            case 15:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label127;
                    }

                    var1[var2] = (Phasor)this.array.get(var2);
                    ++var2;
                }
            case 16:
            case 17:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label127;
                    }

                    var1[var2] = new Phasor(Conv.convert_int_to_double((Character)this.array.get(var2)));
                    ++var2;
                }
            case 18:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label127;
                    }

                    String var3 = ((String)this.array.get(var2)).trim();
                    if (var3.indexOf(60) == -1 && var3.indexOf(76) == -1) {
                        var1[var2] = new Phasor(Double.valueOf(var3));
                    } else {
                        var1[var2] = Phasor.valueOf(var3);
                    }

                    ++var2;
                }
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var1;
    }

    public Character[] array_as_Character() {
        return this.getArray_as_Character();
    }

    public Character[] getArray_as_Character() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        Character[] var1;
        var1 = new Character[this.length];
        int var5;
        label55:
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a conversion to char is meaningful/supported");
            case 6:
            case 7:
                var5 = 0;

                while(true) {
                    if (var5 >= this.length) {
                        break label55;
                    }

//                    var1[var5] = new Character((char)(Integer)this.array.get(var5));
                    var1[var5] = (char)this.array.get(var5);
                    ++var5;
                }
            case 16:
            case 17:
                var5 = 0;

                while(true) {
                    if (var5 >= this.length) {
                        break label55;
                    }

                    var1[var5] = (Character)this.array.get(var5);
                    ++var5;
                }
            case 18:
                boolean var2 = true;
                String[] var3 = new String[this.length];

                int var4;
                for(var4 = 0; var4 < this.length; ++var4) {
                    var3[var4] = ((String)this.array.get(var4)).trim();
                    if (var3[var4].length() > 1) {
                        var2 = false;
                        break;
                    }
                }

                if (!var2) {
                    throw new IllegalArgumentException("The String array elements are too long to be converted to Character");
                }

                var4 = 0;

                while(true) {
                    if (var4 >= this.length) {
                        break label55;
                    }

                    var1[var4] = new Character(var3[var4].charAt(0));
                    ++var4;
                }
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var1;
    }

    public char[] array_as_char() {
        return this.getArray_as_char();
    }

    public char[] getArray_as_char() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        char[] var1;
        var1 = new char[this.length];
        int var5;
        label55:
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a conversion to char is meaningful/supported");
            case 6:
            case 7:
                var5 = 0;

                while(true) {
                    if (var5 >= this.length) {
                        break label55;
                    }

//                    var1[var5] = (char)(Integer)this.array.get(var5);
                    var1[var5] = (char)this.array.get(var5);
                    ++var5;
                }
            case 16:
            case 17:
                var5 = 0;

                while(true) {
                    if (var5 >= this.length) {
                        break label55;
                    }

                    var1[var5] = (Character)this.array.get(var5);
                    ++var5;
                }
            case 18:
                boolean var2 = true;
                String[] var3 = new String[this.length];

                int var4;
                for(var4 = 0; var4 < this.length; ++var4) {
                    var3[var4] = ((String)this.array.get(var4)).trim();
                    if (var3[var4].length() > 1) {
                        var2 = false;
                        break;
                    }
                }

                if (!var2) {
                    throw new IllegalArgumentException("The String array elements are too long to be converted to char");
                }

                var4 = 0;

                while(true) {
                    if (var4 >= this.length) {
                        break label55;
                    }

                    var1[var4] = var3[var4].charAt(0);
                    ++var4;
                }
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var1;
    }

    public String[] array_as_String() {
        return this.getArray_as_String();
    }

    public String[] getArray_as_String() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        String[] var1;
        var1 = new String[this.length];
        int var2;
        label118:
        switch(this.type) {
            case 0:
            case 1:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label118;
                    }

                    var1[var2] = ((Double)this.array.get(var2)).toString();
                    ++var2;
                }
            case 2:
            case 3:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label118;
                    }

                    var1[var2] = ((Float)this.array.get(var2)).toString();
                    ++var2;
                }
            case 4:
            case 5:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label118;
                    }

                    var1[var2] = ((Long)this.array.get(var2)).toString();
                    ++var2;
                }
            case 6:
            case 7:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label118;
                    }

                    var1[var2] = ((Integer)this.array.get(var2)).toString();
                    ++var2;
                }
            case 8:
            case 9:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label118;
                    }

                    var1[var2] = ((Short)this.array.get(var2)).toString();
                    ++var2;
                }
            case 10:
            case 11:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label118;
                    }

                    var1[var2] = ((Byte)this.array.get(var2)).toString();
                    ++var2;
                }
            case 12:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label118;
                    }

                    var1[var2] = ((BigDecimal)this.array.get(var2)).toString();
                    ++var2;
                }
            case 13:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label118;
                    }

                    var1[var2] = ((BigInteger)this.array.get(var2)).toString();
                    ++var2;
                }
            case 14:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label118;
                    }

                    var1[var2] = ((Complex)this.array.get(var2)).toString();
                    ++var2;
                }
            case 15:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label118;
                    }

                    var1[var2] = ((Phasor)this.array.get(var2)).toString();
                    ++var2;
                }
            case 16:
            case 17:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label118;
                    }

                    var1[var2] = ((Character)this.array.get(var2)).toString();
                    ++var2;
                }
            case 18:
                var2 = 0;

                while(true) {
                    if (var2 >= this.length) {
                        break label118;
                    }

                    var1[var2] = (String)this.array.get(var2);
                    ++var2;
                }
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var1;
    }

    public Object[] array_as_Object() {
        return this.getArray_as_Object();
    }

    public Object[] getArray_as_Object() {
        Object[] var1 = new Object[this.length];

        for(int var2 = 0; var2 < this.length; ++var2) {
            var1[var2] = this.array.get(var2);
        }

        return var1;
    }

    public Vector array_as_Vector() {
        return this.getArray_as_Vector();
    }

    public Vector<Object> getArray_as_Vector() {
        Vector var1 = new Vector(this.length);

        for(int var2 = 0; var2 < this.length; ++var2) {
            var1.addElement(this.array.get(var2));
        }

        return var1;
    }

    public ArrayList array_as_ArrayList() {
        return this.getArray_as_ArrayList();
    }

    public ArrayList<Object> getArray_as_ArrayList() {
        ArrayList var1 = new ArrayList(this.length);

        for(int var2 = 0; var2 < this.length; ++var2) {
            var1.add(this.array.get(var2));
        }

        return var1;
    }

    public Matrix array_as_Matrix_rowMatrix() {
        return this.getArray_as_Matrix_rowMatrix();
    }

    public Matrix getArray_as_Matrix_rowMatrix() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        Matrix var1 = null;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 16:
            case 17:
            case 18:
                double[] var2 = this.getArray_as_double();
                var1 = Matrix.rowMatrix(var2);
                Conv.restoreMessages();
                return var1;
            case 6:
            case 7:
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
            case 14:
                throw new IllegalArgumentException("Complex array cannot be converted to Matrix.rowMatrix - use method getArray_as_Complex_rowMatrix");
            case 15:
                throw new IllegalArgumentException("Phasor array cannot be converted to Matrix.rowMatrix - use method getArray_as_Phasor_rowMatrix");
        }
    }

    public Matrix array_as_Matrix_columnMatrix() {
        return this.getArray_as_Matrix_columnMatrix();
    }

    public Matrix getArray_as_Matrix_columnMatrix() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        Matrix var1 = null;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 16:
            case 17:
            case 18:
                double[] var2 = this.getArray_as_double();
                var1 = Matrix.columnMatrix(var2);
                Conv.restoreMessages();
                return var1;
            case 6:
            case 7:
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
            case 14:
                throw new IllegalArgumentException("Complex array cannot be converted to Matrix.columnMatrix - use method getArray_as_Complex_columnMatrix");
            case 15:
                throw new IllegalArgumentException("Phasor array cannot be converted to Matrix.columnMatrix - use method getArray_as_Phasor_columnMatrix");
        }
    }

    public ComplexMatrix array_as_Complex_rowMatrix() {
        return this.getArray_as_Complex_rowMatrix();
    }

    public ComplexMatrix getArray_as_Complex_rowMatrix() {
        Complex[] var1 = this.getArray_as_Complex();
        ComplexMatrix var2 = ComplexMatrix.rowMatrix(var1);
        return var2;
    }

    public ComplexMatrix array_as_Complex_columnMatrix() {
        return this.getArray_as_Complex_columnMatrix();
    }

    public ComplexMatrix getArray_as_Complex_columnMatrix() {
        Complex[] var1 = this.getArray_as_Complex();
        ComplexMatrix var2 = ComplexMatrix.columnMatrix(var1);
        return var2;
    }

    public PhasorMatrix array_as_Phasor_rowMatrix() {
        return this.getArray_as_Phasor_rowMatrix();
    }

    public PhasorMatrix getArray_as_Phasor_rowMatrix() {
        Phasor[] var1 = this.getArray_as_Phasor();
        PhasorMatrix var2 = PhasorMatrix.rowMatrix(var1);
        return var2;
    }

    public PhasorMatrix array_as_Phasor_columnMatrix() {
        return this.getArray_as_Phasor_columnMatrix();
    }

    public PhasorMatrix getArray_as_Phasor_columnMatrix() {
        Phasor[] var1 = this.getArray_as_Phasor();
        PhasorMatrix var2 = PhasorMatrix.columnMatrix(var1);
        return var2;
    }

    public double[] array_as_modulus_of_Complex() {
        Complex[] var1 = this.getArray_as_Complex();
        double[] var2 = new double[this.length];

        for(int var3 = 0; var3 < this.length; ++var3) {
            var2[var3] = var1[var3].abs();
        }

        return var2;
    }

    public double[] array_as_real_part_of_Complex() {
        return this.getArray_as_real_part_of_Complex();
    }

    public double[] getArray_as_real_part_of_Complex() {
        Complex[] var1 = this.getArray_as_Complex();
        double[] var2 = new double[this.length];

        for(int var3 = 0; var3 < this.length; ++var3) {
            var2[var3] = var1[var3].getReal();
        }

        return var2;
    }

    public double[] array_as_imaginary_part_of_Complex() {
        return this.getArray_as_imaginay_part_of_Complex();
    }

    public double[] getArray_as_imaginay_part_of_Complex() {
        Complex[] var1 = this.getArray_as_Complex();
        double[] var2 = new double[this.length];

        for(int var3 = 0; var3 < this.length; ++var3) {
            var2[var3] = var1[var3].getImag();
        }

        return var2;
    }

    public double[] array_as_magnitude_of_Phasor() {
        return this.getArray_as_magnitude_of_Phasor();
    }

    public double[] getArray_as_magnitude_of_Phasor() {
        Phasor[] var1 = this.getArray_as_Phasor();
        double[] var2 = new double[this.length];

        for(int var3 = 0; var3 < this.length; ++var3) {
            var2[var3] = var1[var3].getMagnitude();
        }

        return var2;
    }

    public double[] array_as_degrees_phase_of_Phasor() {
        return this.getArray_as_degrees_phase_of_Phasor();
    }

    public double[] getArray_as_degrees_phase_of_Phasor() {
        Phasor[] var1 = this.getArray_as_Phasor();
        double[] var2 = new double[this.length];

        for(int var3 = 0; var3 < this.length; ++var3) {
            var2[var3] = var1[var3].getPhaseInDegrees();
        }

        return var2;
    }

    public double[] array_as_radians_phase_of_Phasor() {
        return this.getArray_as_radians_phase_of_Phasor();
    }

    public double[] getArray_as_radians_phase_of_Phasor() {
        Phasor[] var1 = this.getArray_as_Phasor();
        double[] var2 = new double[this.length];

        for(int var3 = 0; var3 < this.length; ++var3) {
            var2[var3] = var1[var3].getPhaseInRadians();
        }

        return var2;
    }

    public double[] subarray_as_double(int var1, int var2) {
        if (var2 >= this.length) {
            throw new IllegalArgumentException("end, " + var2 + ", is greater than the highest index, " + (this.length - 1));
        } else {
            if (this.suppressMessages) {
                Conv.suppressMessages();
            }

            double[] var3;
            var3 = new double[var2 - var1 + 1];
            int var4;
            label103:
            switch(this.type) {
                case 0:
                case 1:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = (Double)this.array.get(var4);
                        ++var4;
                    }
                case 2:
                case 3:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Float_to_double((Float)this.array.get(var4));
                        ++var4;
                    }
                case 4:
                case 5:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Long_to_double((Long)this.array.get(var4));
                        ++var4;
                    }
                case 6:
                case 7:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Integer_to_double((Integer)this.array.get(var4));
                        ++var4;
                    }
                case 8:
                case 9:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Short_to_double((Short)this.array.get(var4));
                        ++var4;
                    }
                case 10:
                case 11:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Byte_to_double((Byte)this.array.get(var4));
                        ++var4;
                    }
                case 12:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_BigDecimal_to_double((BigDecimal)this.array.get(var4));
                        ++var4;
                    }
                case 13:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_BigInteger_to_double((BigInteger)this.array.get(var4));
                        ++var4;
                    }
                case 14:
                case 15:
                    throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a conversion to double is meaningful/supported");
                case 16:
                case 17:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_int_to_double((Character)this.array.get(var4));
                        ++var4;
                    }
                case 18:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Double.valueOf((String)this.array.get(var4));
                        ++var4;
                    }
                default:
                    throw new IllegalArgumentException("Data type not identified by this method");
            }

            Conv.restoreMessages();
            return var3;
        }
    }

    public Double[] subarray_as_Double(int var1, int var2) {
        if (var2 >= this.length) {
            throw new IllegalArgumentException("end, " + var2 + ", is greater than the highest index, " + (this.length - 1));
        } else {
            if (this.suppressMessages) {
                Conv.suppressMessages();
            }

            Double[] var3;
            var3 = new Double[var2 - var1 + 1];
            int var4;
            label103:
            switch(this.type) {
                case 0:
                case 1:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = (Double)this.array.get(var4);
                        ++var4;
                    }
                case 2:
                case 3:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Float_to_Double((Float)this.array.get(var4));
                        ++var4;
                    }
                case 4:
                case 5:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Long_to_Double((Long)this.array.get(var4));
                        ++var4;
                    }
                case 6:
                case 7:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Integer_to_Double((Integer)this.array.get(var4));
                        ++var4;
                    }
                case 8:
                case 9:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Short_to_Double((Short)this.array.get(var4));
                        ++var4;
                    }
                case 10:
                case 11:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Byte_to_Double((Byte)this.array.get(var4));
                        ++var4;
                    }
                case 12:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_BigDecimal_to_Double((BigDecimal)this.array.get(var4));
                        ++var4;
                    }
                case 13:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_BigInteger_to_Double((BigInteger)this.array.get(var4));
                        ++var4;
                    }
                case 14:
                case 15:
                    throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a conversion to Double is meaningful/supported");
                case 16:
                case 17:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_int_to_Double((Character)this.array.get(var4));
                        ++var4;
                    }
                case 18:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = new Double((String)this.array.get(var4));
                        ++var4;
                    }
                default:
                    throw new IllegalArgumentException("Data type not identified by this method");
            }

            Conv.restoreMessages();
            return var3;
        }
    }

    public Float[] subarray_as_Float(int var1, int var2) {
        if (var2 >= this.length) {
            throw new IllegalArgumentException("end, " + var2 + ", is greater than the highest index, " + (this.length - 1));
        } else {
            if (this.suppressMessages) {
                Conv.suppressMessages();
            }

            Float[] var3;
            var3 = new Float[var2 - var1 + 1];
            int var4;
            label103:
            switch(this.type) {
                case 0:
                case 1:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Double_to_Float((Double)this.array.get(var4));
                        ++var4;
                    }
                case 2:
                case 3:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = (Float)this.array.get(var4);
                        ++var4;
                    }
                case 4:
                case 5:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Long_to_Float((Long)this.array.get(var4));
                        ++var4;
                    }
                case 6:
                case 7:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Integer_to_Float((Integer)this.array.get(var4));
                        ++var4;
                    }
                case 8:
                case 9:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Short_to_Float((Short)this.array.get(var4));
                        ++var4;
                    }
                case 10:
                case 11:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Byte_to_Float((Byte)this.array.get(var4));
                        ++var4;
                    }
                case 12:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_BigDecimal_to_Float((BigDecimal)this.array.get(var4));
                        ++var4;
                    }
                case 13:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_BigInteger_to_Float((BigInteger)this.array.get(var4));
                        ++var4;
                    }
                case 14:
                case 15:
                    throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a conversion to Float is meaningful/supported");
                case 16:
                case 17:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_int_to_Float((Character)this.array.get(var4));
                        ++var4;
                    }
                case 18:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = new Float((String)this.array.get(var4));
                        ++var4;
                    }
                default:
                    throw new IllegalArgumentException("Data type not identified by this method");
            }

            Conv.restoreMessages();
            return var3;
        }
    }

    public float[] subarray_as_float(int var1, int var2) {
        if (var2 >= this.length) {
            throw new IllegalArgumentException("end, " + var2 + ", is greater than the highest index, " + (this.length - 1));
        } else {
            if (this.suppressMessages) {
                Conv.suppressMessages();
            }

            float[] var3;
            var3 = new float[var2 - var1 + 1];
            int var4;
            label103:
            switch(this.type) {
                case 0:
                case 1:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Double_to_float((Double)this.array.get(var4));
                        ++var4;
                    }
                case 2:
                case 3:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = (Float)this.array.get(var4);
                        ++var4;
                    }
                case 4:
                case 5:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Long_to_float((Long)this.array.get(var4));
                        ++var4;
                    }
                case 6:
                case 7:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Integer_to_float((Integer)this.array.get(var4));
                        ++var4;
                    }
                case 8:
                case 9:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Short_to_float((Short)this.array.get(var4));
                        ++var4;
                    }
                case 10:
                case 11:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Byte_to_float((Byte)this.array.get(var4));
                        ++var4;
                    }
                case 12:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_BigDecimal_to_float((BigDecimal)this.array.get(var4));
                        ++var4;
                    }
                case 13:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_BigInteger_to_float((BigInteger)this.array.get(var4));
                        ++var4;
                    }
                case 14:
                case 15:
                    throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a conversion to float is meaningful/supported");
                case 16:
                case 17:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_int_to_float((Character)this.array.get(var4));
                        ++var4;
                    }
                case 18:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = new Float((String)this.array.get(var4));
                        ++var4;
                    }
                default:
                    throw new IllegalArgumentException("Data type not identified by this method");
            }

            Conv.restoreMessages();
            return var3;
        }
    }

    public long[] subarray_as_long(int var1, int var2) {
        if (var2 >= this.length) {
            throw new IllegalArgumentException("end, " + var2 + ", is greater than the highest index, " + (this.length - 1));
        } else {
            if (this.suppressMessages) {
                Conv.suppressMessages();
            }

            long[] var3;
            var3 = new long[var2 - var1 + 1];
            int var4;
            label103:
            switch(this.type) {
                case 0:
                case 1:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Double_to_long((Double)this.array.get(var4));
                        ++var4;
                    }
                case 2:
                case 3:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Float_to_long((Float)this.array.get(var4));
                        ++var4;
                    }
                case 4:
                case 5:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = (Long)this.array.get(var4);
                        ++var4;
                    }
                case 6:
                case 7:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Integer_to_long((Integer)this.array.get(var4));
                        ++var4;
                    }
                case 8:
                case 9:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Short_to_long((Short)this.array.get(var4));
                        ++var4;
                    }
                case 10:
                case 11:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Byte_to_long((Byte)this.array.get(var4));
                        ++var4;
                    }
                case 12:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_BigDecimal_to_long((BigDecimal)this.array.get(var4));
                        ++var4;
                    }
                case 13:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_BigInteger_to_long((BigInteger)this.array.get(var4));
                        ++var4;
                    }
                case 14:
                case 15:
                    throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a conversion to long is meaningful/supported");
                case 16:
                case 17:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_int_to_long((Character)this.array.get(var4));
                        ++var4;
                    }
                case 18:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = new Long((String)this.array.get(var4));
                        ++var4;
                    }
                default:
                    throw new IllegalArgumentException("Data type not identified by this method");
            }

            Conv.restoreMessages();
            return var3;
        }
    }

    public Long[] subarray_as_Long(int var1, int var2) {
        if (var2 >= this.length) {
            throw new IllegalArgumentException("end, " + var2 + ", is greater than the highest index, " + (this.length - 1));
        } else {
            if (this.suppressMessages) {
                Conv.suppressMessages();
            }

            Long[] var3;
            var3 = new Long[var2 - var1 + 1];
            int var4;
            label103:
            switch(this.type) {
                case 0:
                case 1:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Double_to_Long((Double)this.array.get(var4));
                        ++var4;
                    }
                case 2:
                case 3:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Float_to_Long((Float)this.array.get(var4));
                        ++var4;
                    }
                case 4:
                case 5:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = (Long)this.array.get(var4);
                        ++var4;
                    }
                case 6:
                case 7:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Integer_to_Long((Integer)this.array.get(var4));
                        ++var4;
                    }
                case 8:
                case 9:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Short_to_Long((Short)this.array.get(var4));
                        ++var4;
                    }
                case 10:
                case 11:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Byte_to_Long((Byte)this.array.get(var4));
                        ++var4;
                    }
                case 12:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_BigDecimal_to_Long((BigDecimal)this.array.get(var4));
                        ++var4;
                    }
                case 13:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_BigInteger_to_Long((BigInteger)this.array.get(var4));
                        ++var4;
                    }
                case 14:
                case 15:
                    throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a conversion to Long is meaningful/supported");
                case 16:
                case 17:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_int_to_Long((Character)this.array.get(var4));
                        ++var4;
                    }
                case 18:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = new Long((String)this.array.get(var4));
                        ++var4;
                    }
                default:
                    throw new IllegalArgumentException("Data type not identified by this method");
            }

            Conv.restoreMessages();
            return var3;
        }
    }

    public Integer[] subarray_as_Integer(int var1, int var2) {
        if (var2 >= this.length) {
            throw new IllegalArgumentException("end, " + var2 + ", is greater than the highest index, " + (this.length - 1));
        } else {
            if (this.suppressMessages) {
                Conv.suppressMessages();
            }

            Integer[] var3;
            var3 = new Integer[var2 - var1 + 1];
            int var4;
            label103:
            switch(this.type) {
                case 0:
                case 1:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Double_to_Integer((Double)this.array.get(var4));
                        ++var4;
                    }
                case 2:
                case 3:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Float_to_Integer((Float)this.array.get(var4));
                        ++var4;
                    }
                case 4:
                case 5:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Long_to_Integer((Long)this.array.get(var4));
                        ++var4;
                    }
                case 6:
                case 7:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = (Integer)this.array.get(var4);
                        ++var4;
                    }
                case 8:
                case 9:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Short_to_Integer((Short)this.array.get(var4));
                        ++var4;
                    }
                case 10:
                case 11:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Byte_to_Integer((Byte)this.array.get(var4));
                        ++var4;
                    }
                case 12:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_BigDecimal_to_Integer((BigDecimal)this.array.get(var4));
                        ++var4;
                    }
                case 13:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_BigInteger_to_Integer((BigInteger)this.array.get(var4));
                        ++var4;
                    }
                case 14:
                case 15:
                    throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a conversion to Integer is meaningful/supported");
                case 16:
                case 17:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = new Integer((Character)this.array.get(var4));
                        ++var4;
                    }
                case 18:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = new Integer((String)this.array.get(var4));
                        ++var4;
                    }
                default:
                    throw new IllegalArgumentException("Data type not identified by this method");
            }

            Conv.restoreMessages();
            return var3;
        }
    }

    public int[] subarray_as_int(int var1, int var2) {
        if (var2 >= this.length) {
            throw new IllegalArgumentException("end, " + var2 + ", is greater than the highest index, " + (this.length - 1));
        } else {
            if (this.suppressMessages) {
                Conv.suppressMessages();
            }

            int[] var3;
            var3 = new int[var2 - var1 + 1];
            int var4;
            label103:
            switch(this.type) {
                case 0:
                case 1:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Double_to_int((Double)this.array.get(var4));
                        ++var4;
                    }
                case 2:
                case 3:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Float_to_int((Float)this.array.get(var4));
                        ++var4;
                    }
                case 4:
                case 5:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Long_to_int((Long)this.array.get(var4));
                        ++var4;
                    }
                case 6:
                case 7:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = (Integer)this.array.get(var4);
                        ++var4;
                    }
                case 8:
                case 9:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Short_to_int((Short)this.array.get(var4));
                        ++var4;
                    }
                case 10:
                case 11:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Byte_to_int((Byte)this.array.get(var4));
                        ++var4;
                    }
                case 12:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_BigDecimal_to_int((BigDecimal)this.array.get(var4));
                        ++var4;
                    }
                case 13:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_BigInteger_to_int((BigInteger)this.array.get(var4));
                        ++var4;
                    }
                case 14:
                case 15:
                    throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a conversion to int is meaningful/supported");
                case 16:
                case 17:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = (Character)this.array.get(var4);
                        ++var4;
                    }
                case 18:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = new Integer((String)this.array.get(var4));
                        ++var4;
                    }
                default:
                    throw new IllegalArgumentException("Data type not identified by this method");
            }

            Conv.restoreMessages();
            return var3;
        }
    }

    public short[] subarray_as_short(int var1, int var2) {
        if (var2 >= this.length) {
            throw new IllegalArgumentException("end, " + var2 + ", is greater than the highest index, " + (this.length - 1));
        } else {
            if (this.suppressMessages) {
                Conv.suppressMessages();
            }

            short[] var3;
            var3 = new short[var2 - var1 + 1];
            int var4;
            label103:
            switch(this.type) {
                case 0:
                case 1:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Double_to_short((Double)this.array.get(var4));
                        ++var4;
                    }
                case 2:
                case 3:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Float_to_short((Float)this.array.get(var4));
                        ++var4;
                    }
                case 4:
                case 5:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Long_to_short((Long)this.array.get(var4));
                        ++var4;
                    }
                case 6:
                case 7:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Integer_to_short((Integer)this.array.get(var4));
                        ++var4;
                    }
                case 8:
                case 9:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = (Short)this.array.get(var4);
                        ++var4;
                    }
                case 10:
                case 11:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Byte_to_short((Byte)this.array.get(var4));
                        ++var4;
                    }
                case 12:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_BigDecimal_to_short((BigDecimal)this.array.get(var4));
                        ++var4;
                    }
                case 13:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_BigInteger_to_short((BigInteger)this.array.get(var4));
                        ++var4;
                    }
                case 14:
                case 15:
                    throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a conversion to short is meaningful/supported");
                case 16:
                case 17:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_int_to_short((Character)this.array.get(var4));
                        ++var4;
                    }
                case 18:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = new Short((String)this.array.get(var4));
                        ++var4;
                    }
                default:
                    throw new IllegalArgumentException("Data type not identified by this method");
            }

            Conv.restoreMessages();
            return var3;
        }
    }

    public Short[] subarray_as_Short(int var1, int var2) {
        if (var2 >= this.length) {
            throw new IllegalArgumentException("end, " + var2 + ", is greater than the highest index, " + (this.length - 1));
        } else {
            if (this.suppressMessages) {
                Conv.suppressMessages();
            }

            Short[] var3;
            var3 = new Short[var2 - var1 + 1];
            int var4;
            label103:
            switch(this.type) {
                case 0:
                case 1:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Double_to_Short((Double)this.array.get(var4));
                        ++var4;
                    }
                case 2:
                case 3:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Float_to_Short((Float)this.array.get(var4));
                        ++var4;
                    }
                case 4:
                case 5:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Long_to_Short((Long)this.array.get(var4));
                        ++var4;
                    }
                case 6:
                case 7:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Integer_to_Short((Integer)this.array.get(var4));
                        ++var4;
                    }
                case 8:
                case 9:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = (Short)this.array.get(var4);
                        ++var4;
                    }
                case 10:
                case 11:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Byte_to_Short((Byte)this.array.get(var4));
                        ++var4;
                    }
                case 12:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_BigDecimal_to_Short((BigDecimal)this.array.get(var4));
                        ++var4;
                    }
                case 13:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_BigInteger_to_Short((BigInteger)this.array.get(var4));
                        ++var4;
                    }
                case 14:
                case 15:
                    throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a conversion to Short is meaningful/supported");
                case 16:
                case 17:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_int_to_Short((Character)this.array.get(var4));
                        ++var4;
                    }
                case 18:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = new Short((String)this.array.get(var4));
                        ++var4;
                    }
                default:
                    throw new IllegalArgumentException("Data type not identified by this method");
            }

            Conv.restoreMessages();
            return var3;
        }
    }

    public byte[] subarray_as_byte(int var1, int var2) {
        if (var2 >= this.length) {
            throw new IllegalArgumentException("end, " + var2 + ", is greater than the highest index, " + (this.length - 1));
        } else {
            if (this.suppressMessages) {
                Conv.suppressMessages();
            }

            byte[] var3;
            var3 = new byte[var2 - var1 + 1];
            int var4;
            label103:
            switch(this.type) {
                case 0:
                case 1:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Double_to_byte((Double)this.array.get(var4));
                        ++var4;
                    }
                case 2:
                case 3:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Float_to_byte((Float)this.array.get(var4));
                        ++var4;
                    }
                case 4:
                case 5:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Long_to_byte((Long)this.array.get(var4));
                        ++var4;
                    }
                case 6:
                case 7:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Integer_to_byte((Integer)this.array.get(var4));
                        ++var4;
                    }
                case 8:
                case 9:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Short_to_byte((Short)this.array.get(var4));
                        ++var4;
                    }
                case 10:
                case 11:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = (Byte)this.array.get(var4);
                        ++var4;
                    }
                case 12:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_BigDecimal_to_byte((BigDecimal)this.array.get(var4));
                        ++var4;
                    }
                case 13:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_BigInteger_to_byte((BigInteger)this.array.get(var4));
                        ++var4;
                    }
                case 14:
                case 15:
                    throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a conversion to byte is meaningful/supported");
                case 16:
                case 17:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_int_to_byte((Character)this.array.get(var4));
                        ++var4;
                    }
                case 18:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = new Byte((String)this.array.get(var4));
                        ++var4;
                    }
                default:
                    throw new IllegalArgumentException("Data type not identified by this method");
            }

            Conv.restoreMessages();
            return var3;
        }
    }

    public Byte[] subarray_as_Byte(int var1, int var2) {
        if (var2 >= this.length) {
            throw new IllegalArgumentException("end, " + var2 + ", is greater than the highest index, " + (this.length - 1));
        } else {
            if (this.suppressMessages) {
                Conv.suppressMessages();
            }

            Byte[] var3;
            var3 = new Byte[var2 - var1 + 1];
            int var4;
            label103:
            switch(this.type) {
                case 0:
                case 1:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Double_to_Byte((Double)this.array.get(var4));
                        ++var4;
                    }
                case 2:
                case 3:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Float_to_Byte((Float)this.array.get(var4));
                        ++var4;
                    }
                case 4:
                case 5:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Long_to_Byte((Long)this.array.get(var4));
                        ++var4;
                    }
                case 6:
                case 7:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Integer_to_Byte((Integer)this.array.get(var4));
                        ++var4;
                    }
                case 8:
                case 9:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Short_to_Byte((Short)this.array.get(var4));
                        ++var4;
                    }
                case 10:
                case 11:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = (Byte)this.array.get(var4);
                        ++var4;
                    }
                case 12:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_BigDecimal_to_Byte((BigDecimal)this.array.get(var4));
                        ++var4;
                    }
                case 13:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_BigInteger_to_Byte((BigInteger)this.array.get(var4));
                        ++var4;
                    }
                case 14:
                case 15:
                    throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a conversion to Byte is meaningful/supported");
                case 16:
                case 17:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_int_to_Byte((Character)this.array.get(var4));
                        ++var4;
                    }
                case 18:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = new Byte((String)this.array.get(var4));
                        ++var4;
                    }
                default:
                    throw new IllegalArgumentException("Data type not identified by this method");
            }

            Conv.restoreMessages();
            return var3;
        }
    }

    public BigDecimal[] subarray_as_BigDecimal(int var1, int var2) {
        if (var2 >= this.length) {
            throw new IllegalArgumentException("end, " + var2 + ", is greater than the highest index, " + (this.length - 1));
        } else {
            if (this.suppressMessages) {
                Conv.suppressMessages();
            }

            BigDecimal[] var3;
            var3 = new BigDecimal[var2 - var1 + 1];
            int var4;
            label103:
            switch(this.type) {
                case 0:
                case 1:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Double_to_BigDecimal((Double)this.array.get(var4));
                        ++var4;
                    }
                case 2:
                case 3:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Float_to_BigDecimal((Float)this.array.get(var4));
                        ++var4;
                    }
                case 4:
                case 5:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Long_to_BigDecimal((Long)this.array.get(var4));
                        ++var4;
                    }
                case 6:
                case 7:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Integer_to_BigDecimal((Integer)this.array.get(var4));
                        ++var4;
                    }
                case 8:
                case 9:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Short_to_BigDecimal((Short)this.array.get(var4));
                        ++var4;
                    }
                case 10:
                case 11:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Byte_to_BigDecimal((Byte)this.array.get(var4));
                        ++var4;
                    }
                case 12:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = (BigDecimal)this.array.get(var4);
                        ++var4;
                    }
                case 13:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_BigInteger_to_BigDecimal((BigInteger)this.array.get(var4));
                        ++var4;
                    }
                case 14:
                case 15:
                    throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a conversion to BigDecimal is meaningful/supported");
                case 16:
                case 17:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_int_to_BigDecimal((Character)this.array.get(var4));
                        ++var4;
                    }
                case 18:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = new BigDecimal((String)this.array.get(var4));
                        ++var4;
                    }
                default:
                    throw new IllegalArgumentException("Data type not identified by this method");
            }

            Conv.restoreMessages();
            return var3;
        }
    }

    public BigInteger[] subarray_as_BigInteger(int var1, int var2) {
        if (var2 >= this.length) {
            throw new IllegalArgumentException("end, " + var2 + ", is greater than the highest index, " + (this.length - 1));
        } else {
            if (this.suppressMessages) {
                Conv.suppressMessages();
            }

            BigInteger[] var3;
            var3 = new BigInteger[var2 - var1 + 1];
            int var4;
            label103:
            switch(this.type) {
                case 0:
                case 1:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Double_to_BigInteger((Double)this.array.get(var4));
                        ++var4;
                    }
                case 2:
                case 3:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Float_to_BigInteger((Float)this.array.get(var4));
                        ++var4;
                    }
                case 4:
                case 5:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Long_to_BigInteger((Long)this.array.get(var4));
                        ++var4;
                    }
                case 6:
                case 7:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Integer_to_BigInteger((Integer)this.array.get(var4));
                        ++var4;
                    }
                case 8:
                case 9:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Short_to_BigInteger((Short)this.array.get(var4));
                        ++var4;
                    }
                case 10:
                case 11:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_Byte_to_BigInteger((Byte)this.array.get(var4));
                        ++var4;
                    }
                case 12:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_BigDecimal_to_BigInteger((BigDecimal)this.array.get(var4));
                        ++var4;
                    }
                case 13:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = (BigInteger)this.array.get(var4);
                        ++var4;
                    }
                case 14:
                case 15:
                    throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a conversion to BigInteger is meaningful/supported");
                case 16:
                case 17:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = Conv.convert_int_to_BigInteger((Character)this.array.get(var4));
                        ++var4;
                    }
                case 18:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label103;
                        }

                        var3[var4 - var1] = new BigInteger((String)this.array.get(var4));
                        ++var4;
                    }
                default:
                    throw new IllegalArgumentException("Data type not identified by this method");
            }

            Conv.restoreMessages();
            return var3;
        }
    }

    public Complex[] subarray_as_Complex(int var1, int var2) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        Complex[] var3;
        var3 = Complex.oneDarray(this.length);
        int var4;
        label127:
        switch(this.type) {
            case 0:
            case 1:
                var4 = var1;

                while(true) {
                    if (var4 > var2) {
                        break label127;
                    }

                    var3[var4 - var1] = new Complex((Double)this.array.get(var4));
                    ++var4;
                }
            case 2:
            case 3:
                var4 = var1;

                while(true) {
                    if (var4 > var2) {
                        break label127;
                    }

                    var3[var4 - var1] = new Complex(((Float)this.array.get(var4)).doubleValue());
                    ++var4;
                }
            case 4:
            case 5:
                var4 = var1;

                while(true) {
                    if (var4 > var2) {
                        break label127;
                    }

                    var3[var4 - var1] = new Complex(Conv.convert_Long_to_double((Long)this.array.get(var4)));
                    ++var4;
                }
            case 6:
            case 7:
                var4 = var1;

                while(true) {
                    if (var4 > var2) {
                        break label127;
                    }

                    var3[var4 - var1] = new Complex(Conv.convert_Integer_to_double((Integer)this.array.get(var4)));
                    ++var4;
                }
            case 8:
            case 9:
                var4 = var1;

                while(true) {
                    if (var4 > var2) {
                        break label127;
                    }

                    var3[var4 - var1] = new Complex(Conv.convert_Short_to_double((Short)this.array.get(var4)));
                    ++var4;
                }
            case 10:
            case 11:
                var4 = var1;

                while(true) {
                    if (var4 > var2) {
                        break label127;
                    }

                    var3[var4 - var1] = new Complex(Conv.convert_Byte_to_double((Byte)this.array.get(var4)));
                    ++var4;
                }
            case 12:
                var4 = var1;

                while(true) {
                    if (var4 > var2) {
                        break label127;
                    }

                    var3[var4 - var1] = new Complex(Conv.convert_BigDecimal_to_double((BigDecimal)this.array.get(var4)));
                    ++var4;
                }
            case 13:
                var4 = var1;

                while(true) {
                    if (var4 > var2) {
                        break label127;
                    }

                    var3[var4 - var1] = new Complex(Conv.convert_BigInteger_to_double((BigInteger)this.array.get(var4)));
                    ++var4;
                }
            case 14:
                var4 = var1;

                while(true) {
                    if (var4 > var2) {
                        break label127;
                    }

                    var3[var4 - var1] = (Complex)this.array.get(var4);
                    ++var4;
                }
            case 15:
                var4 = var1;

                while(true) {
                    if (var4 > var2) {
                        break label127;
                    }

                    var3[var4 - var1] = Conv.convert_Phasor_to_Complex((Phasor)this.array.get(var4));
                    ++var4;
                }
            case 16:
            case 17:
                var4 = var1;

                while(true) {
                    if (var4 > var2) {
                        break label127;
                    }

                    var3[var4 - var1] = new Complex(Conv.convert_int_to_double((Character)this.array.get(var4)));
                    ++var4;
                }
            case 18:
                var4 = var1;

                while(true) {
                    if (var4 > var2) {
                        break label127;
                    }

                    String var5 = (String)this.array.get(var4);
                    if (var5.indexOf(105) == -1 && var5.indexOf(106) == -1) {
                        var3[var4 - var1] = new Complex(Double.valueOf(var5));
                    } else {
                        var3[var4 - var1] = Complex.valueOf(var5);
                    }

                    ++var4;
                }
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var3;
    }

    public Phasor[] subarray_as_Phasor(int var1, int var2) {
        if (var2 >= this.length) {
            throw new IllegalArgumentException("end, " + var2 + ", is greater than the highest index, " + (this.length - 1));
        } else {
            if (this.suppressMessages) {
                Conv.suppressMessages();
            }

            Phasor[] var3;
            var3 = Phasor.oneDarray(this.length);
            int var4;
            label129:
            switch(this.type) {
                case 0:
                case 1:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label129;
                        }

                        var3[var4 - var1] = new Phasor((Double)this.array.get(var4));
                        ++var4;
                    }
                case 2:
                case 3:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label129;
                        }

                        var3[var4 - var1] = new Phasor(((Float)this.array.get(var4)).doubleValue());
                        ++var4;
                    }
                case 4:
                case 5:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label129;
                        }

                        var3[var4 - var1] = new Phasor(Conv.convert_Long_to_double((Long)this.array.get(var4)));
                        ++var4;
                    }
                case 6:
                case 7:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label129;
                        }

                        var3[var4 - var1] = new Phasor(Conv.convert_Integer_to_double((Integer)this.array.get(var4)));
                        ++var4;
                    }
                case 8:
                case 9:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label129;
                        }

                        var3[var4 - var1] = new Phasor(Conv.convert_Short_to_double((Short)this.array.get(var4)));
                        ++var4;
                    }
                case 10:
                case 11:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label129;
                        }

                        var3[var4 - var1] = new Phasor(Conv.convert_Byte_to_double((Byte)this.array.get(var4)));
                        ++var4;
                    }
                case 12:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label129;
                        }

                        var3[var4 - var1] = new Phasor(Conv.convert_BigDecimal_to_double((BigDecimal)this.array.get(var4)));
                        ++var4;
                    }
                case 13:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label129;
                        }

                        var3[var4 - var1] = new Phasor(Conv.convert_BigInteger_to_double((BigInteger)this.array.get(var4)));
                        ++var4;
                    }
                case 14:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label129;
                        }

                        var3[var4 - var1] = Conv.convert_Complex_to_Phasor((Complex)this.array.get(var4));
                        ++var4;
                    }
                case 15:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label129;
                        }

                        var3[var4 - var1] = (Phasor)this.array.get(var4);
                        ++var4;
                    }
                case 16:
                case 17:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label129;
                        }

                        var3[var4 - var1] = new Phasor(Conv.convert_int_to_double((Character)this.array.get(var4)));
                        ++var4;
                    }
                case 18:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label129;
                        }

                        String var5 = ((String)this.array.get(var4)).trim();
                        if (var5.indexOf(60) == -1 && var5.indexOf(76) == -1) {
                            var3[var4 - var1] = new Phasor(Double.valueOf(var5));
                        } else {
                            var3[var4 - var1] = Phasor.valueOf(var5);
                        }

                        ++var4;
                    }
                default:
                    throw new IllegalArgumentException("Data type not identified by this method");
            }

            Conv.restoreMessages();
            return var3;
        }
    }

    public Character[] subarray_as_Character(int var1, int var2) {
        if (var2 >= this.length) {
            throw new IllegalArgumentException("end, " + var2 + ", is greater than the highest index, " + (this.length - 1));
        } else {
            if (this.suppressMessages) {
                Conv.suppressMessages();
            }

            Character[] var3;
            var3 = new Character[var2 - var1 + 1];
            int var7;
            label57:
            switch(this.type) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                case 15:
                    throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a conversion to char is meaningful/supported");
                case 6:
                case 7:
                    var7 = var1;

                    while(true) {
                        if (var7 > var2) {
                            break label57;
                        }

//                        var3[var7 - var1] = new Character((char)(Integer)this.array.get(var7));
                        var3[var7 - var1] = (char)this.array.get(var7);
                        ++var7;
                    }
                case 16:
                case 17:
                    var7 = var1;

                    while(true) {
                        if (var7 > var2) {
                            break label57;
                        }

                        var3[var7 - var1] = (Character)this.array.get(var7);
                        ++var7;
                    }
                case 18:
                    boolean var4 = true;
                    String[] var5 = new String[var2 - var1 + 1];

                    int var6;
                    for(var6 = var1; var6 <= var2; ++var6) {
                        var5[var6 - var1] = ((String)this.array.get(var6)).trim();
                        if (var5[var6 - var1].length() > 1) {
                            var4 = false;
                            break;
                        }
                    }

                    if (!var4) {
                        throw new IllegalArgumentException("The String array elements are too long to be converted to Character");
                    }

                    var6 = var1;

                    while(true) {
                        if (var6 > var2) {
                            break label57;
                        }

                        var3[var6 - var1] = new Character(var5[var6 - var1].charAt(0));
                        ++var6;
                    }
                default:
                    throw new IllegalArgumentException("Data type not identified by this method");
            }

            Conv.restoreMessages();
            return var3;
        }
    }

    public char[] subarray_as_char(int var1, int var2) {
        if (var2 >= this.length) {
            throw new IllegalArgumentException("end, " + var2 + ", is greater than the highest index, " + (this.length - 1));
        } else {
            if (this.suppressMessages) {
                Conv.suppressMessages();
            }

            char[] var3;
            var3 = new char[var2 - var1 + 1];
            int var7;
            label57:
            switch(this.type) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                case 15:
                    throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a conversion to char is meaningful/supported");
                case 6:
                case 7:
                    var7 = var1;

                    while(true) {
                        if (var7 > var2) {
                            break label57;
                        }

//                        var3[var7 - var1] = (char)(Integer)this.array.get(var7);
                        var3[var7 - var1] = (char)this.array.get(var7);
                        ++var7;
                    }
                case 16:
                case 17:
                    var7 = var1;

                    while(true) {
                        if (var7 > var2) {
                            break label57;
                        }

                        var3[var7 - var1] = (Character)this.array.get(var7);
                        ++var7;
                    }
                case 18:
                    boolean var4 = true;
                    String[] var5 = new String[var2 - var1 + 1];

                    int var6;
                    for(var6 = var1; var6 <= var2; ++var6) {
                        var5[var6 - var1] = ((String)this.array.get(var6)).trim();
                        if (var5[var6 - var1].length() > 1) {
                            var4 = false;
                            break;
                        }
                    }

                    if (!var4) {
                        throw new IllegalArgumentException("The String array elements are too long to be converted to char");
                    }

                    var6 = var1;

                    while(true) {
                        if (var6 > var2) {
                            break label57;
                        }

                        var3[var6 - var1] = var5[var6 - var1].charAt(0);
                        ++var6;
                    }
                default:
                    throw new IllegalArgumentException("Data type not identified by this method");
            }

            Conv.restoreMessages();
            return var3;
        }
    }

    public String[] subarray_as_String(int var1, int var2) {
        if (var2 >= this.length) {
            throw new IllegalArgumentException("end, " + var2 + ", is greater than the highest index, " + (this.length - 1));
        } else {
            if (this.suppressMessages) {
                Conv.suppressMessages();
            }

            String[] var3;
            var3 = new String[var2 - var1 + 1];
            int var4;
            label120:
            switch(this.type) {
                case 0:
                case 1:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label120;
                        }

                        var3[var4 - var1] = ((Double)this.array.get(var4)).toString();
                        ++var4;
                    }
                case 2:
                case 3:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label120;
                        }

                        var3[var4 - var1] = ((Float)this.array.get(var4)).toString();
                        ++var4;
                    }
                case 4:
                case 5:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label120;
                        }

                        var3[var4 - var1] = ((Long)this.array.get(var4)).toString();
                        ++var4;
                    }
                case 6:
                case 7:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label120;
                        }

                        var3[var4 - var1] = ((Integer)this.array.get(var4)).toString();
                        ++var4;
                    }
                case 8:
                case 9:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label120;
                        }

                        var3[var4 - var1] = ((Short)this.array.get(var4)).toString();
                        ++var4;
                    }
                case 10:
                case 11:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label120;
                        }

                        var3[var4 - var1] = ((Byte)this.array.get(var4)).toString();
                        ++var4;
                    }
                case 12:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label120;
                        }

                        var3[var4 - var1] = ((BigDecimal)this.array.get(var4)).toString();
                        ++var4;
                    }
                case 13:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label120;
                        }

                        var3[var4 - var1] = ((BigInteger)this.array.get(var4)).toString();
                        ++var4;
                    }
                case 14:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label120;
                        }

                        var3[var4 - var1] = ((Complex)this.array.get(var4)).toString();
                        ++var4;
                    }
                case 15:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label120;
                        }

                        var3[var4 - var1] = ((Phasor)this.array.get(var4)).toString();
                        ++var4;
                    }
                case 16:
                case 17:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label120;
                        }

                        var3[var4 - var1] = ((Character)this.array.get(var4)).toString();
                        ++var4;
                    }
                case 18:
                    var4 = var1;

                    while(true) {
                        if (var4 > var2) {
                            break label120;
                        }

                        var3[var4 - var1] = (String)this.array.get(var4);
                        ++var4;
                    }
                default:
                    throw new IllegalArgumentException("Data type not identified by this method");
            }

            Conv.restoreMessages();
            return var3;
        }
    }

    public Object[] subarray_as_Object(int var1, int var2) {
        if (var2 >= this.length) {
            throw new IllegalArgumentException("end, " + var2 + ", is greater than the highest index, " + (this.length - 1));
        } else {
            Object[] var3 = new Object[var2 - var1 + 1];

            for(int var4 = var1; var4 <= var2; ++var4) {
                var3[var4 - var1] = this.array.get(var4);
            }

            return var3;
        }
    }

    public Vector<Object> subarray_as_Vector(int var1, int var2) {
        if (var2 >= this.length) {
            throw new IllegalArgumentException("end, " + var2 + ", is greater than the highest index, " + (this.length - 1));
        } else {
            Vector var3 = new Vector(var2 - var1 + 1);

            for(int var4 = var1; var4 <= var2; ++var4) {
                var3.addElement(this.array.get(var4));
            }

            return var3;
        }
    }

    public ArrayList<Object> subarray_as_ArrayList(int var1, int var2) {
        if (var2 >= this.length) {
            throw new IllegalArgumentException("end, " + var2 + ", is greater than the highest index, " + (this.length - 1));
        } else {
            ArrayList var3 = new ArrayList(var2 - var1 + 1);

            for(int var4 = var1; var4 <= var2; ++var4) {
                var3.add(this.array.get(var4));
            }

            return var3;
        }
    }

    public Matrix subarray_as_Matrix_rowMatrix(int var1, int var2) {
        if (var2 >= this.length) {
            throw new IllegalArgumentException("end, " + var2 + ", is greater than the highest index, " + (this.length - 1));
        } else {
            if (this.suppressMessages) {
                Conv.suppressMessages();
            }

            Matrix var3 = null;
            double[] var4 = new double[var2 - var1 + 1];
            switch(this.type) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 16:
                case 17:
                case 18:
                    double[] var5 = this.getArray_as_double();

                    for(int var6 = var1; var6 <= var2; ++var6) {
                        var4[var6 - var1] = var5[var6];
                    }

                    var3 = Matrix.rowMatrix(var4);
                    Conv.restoreMessages();
                    return var3;
                case 6:
                case 7:
                default:
                    throw new IllegalArgumentException("Data type not identified by this method");
                case 14:
                    throw new IllegalArgumentException("Complex array cannot be converted to Matrix.rowMatrix - use method subarray_as_Complex_rowMatrix");
                case 15:
                    throw new IllegalArgumentException("Phasor array cannot be converted to Matrix.rowMatrix - use method subarray_as_Phasor_rowMatrix");
            }
        }
    }

    public Matrix subarray_as_Matrix_columnMatrix(int var1, int var2) {
        if (var2 >= this.length) {
            throw new IllegalArgumentException("end, " + var2 + ", is greater than the highest index, " + (this.length - 1));
        } else {
            if (this.suppressMessages) {
                Conv.suppressMessages();
            }

            Matrix var3 = null;
            double[] var4 = new double[var2 - var1 + 1];
            switch(this.type) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 16:
                case 17:
                case 18:
                    double[] var5 = this.getArray_as_double();

                    for(int var6 = var1; var6 <= var2; ++var6) {
                        var4[var6 - var1] = var5[var6];
                    }

                    var3 = Matrix.columnMatrix(var4);
                    Conv.restoreMessages();
                    return var3;
                case 6:
                case 7:
                default:
                    throw new IllegalArgumentException("Data type not identified by this method");
                case 14:
                    throw new IllegalArgumentException("Complex array cannot be converted to Matrix.columnMatrix - use method subarray_as_Complex_columnMatrix");
                case 15:
                    throw new IllegalArgumentException("Phasor array cannot be converted to Matrix.columnMatrix - use method subarray_as_Phasor_columnMatrix");
            }
        }
    }

    public ComplexMatrix subarray_as_Complex_rowMatrix(int var1, int var2) {
        if (var2 >= this.length) {
            throw new IllegalArgumentException("end, " + var2 + ", is greater than the highest index, " + (this.length - 1));
        } else {
            Complex[] var3 = this.getArray_as_Complex();
            Complex[] var4 = new Complex[var2 - var1 + 1];

            for(int var5 = var1; var5 <= var2; ++var5) {
                var4[var5 - var1] = var3[var5];
            }

            ComplexMatrix var6 = ComplexMatrix.rowMatrix(var4);
            return var6;
        }
    }

    public ComplexMatrix subarray_as_Complex_columnMatrix(int var1, int var2) {
        if (var2 >= this.length) {
            throw new IllegalArgumentException("end, " + var2 + ", is greater than the highest index, " + (this.length - 1));
        } else {
            Complex[] var3 = this.getArray_as_Complex();
            Complex[] var4 = new Complex[var2 - var1 + 1];

            for(int var5 = var1; var5 <= var2; ++var5) {
                var4[var5 - var1] = var3[var5];
            }

            ComplexMatrix var6 = ComplexMatrix.columnMatrix(var4);
            return var6;
        }
    }

    public PhasorMatrix subarray_as_Phasor_rowMatrix(int var1, int var2) {
        if (var2 >= this.length) {
            throw new IllegalArgumentException("end, " + var2 + ", is greater than the highest index, " + (this.length - 1));
        } else {
            Phasor[] var3 = this.getArray_as_Phasor();
            Phasor[] var4 = new Phasor[var2 - var1 + 1];

            for(int var5 = var1; var5 <= var2; ++var5) {
                var4[var5 - var1] = var3[var5];
            }

            PhasorMatrix var6 = PhasorMatrix.rowMatrix(var4);
            return var6;
        }
    }

    public PhasorMatrix subarray_as_Phasor_columnMatrix(int var1, int var2) {
        if (var2 >= this.length) {
            throw new IllegalArgumentException("end, " + var2 + ", is greater than the highest index, " + (this.length - 1));
        } else {
            Phasor[] var3 = this.getArray_as_Phasor();
            Phasor[] var4 = new Phasor[var2 - var1 + 1];

            for(int var5 = var1; var5 <= var2; ++var5) {
                var4[var5 - var1] = var3[var5];
            }

            PhasorMatrix var6 = PhasorMatrix.columnMatrix(var4);
            return var6;
        }
    }

    public double[] subarray_as_modulus_of_Complex(int var1, int var2) {
        if (var2 >= this.length) {
            throw new IllegalArgumentException("end, " + var2 + ", is greater than the highest index, " + (this.length - 1));
        } else {
            Complex[] var3 = this.getArray_as_Complex();
            double[] var4 = new double[var2 - var1 + 1];

            for(int var5 = var1; var5 <= var2; ++var5) {
                var4[var5 - var1] = var3[var5].abs();
            }

            return var4;
        }
    }

    public double[] subarray_as_real_part_of_Complex(int var1, int var2) {
        if (var2 >= this.length) {
            throw new IllegalArgumentException("end, " + var2 + ", is greater than the highest index, " + (this.length - 1));
        } else {
            Complex[] var3 = this.getArray_as_Complex();
            double[] var4 = new double[var2 - var1 + 1];

            for(int var5 = var1; var5 <= var2; ++var5) {
                var4[var5 - var1] = var3[var5].getReal();
            }

            return var4;
        }
    }

    public double[] subarray_as_imaginay_part_of_Complex(int var1, int var2) {
        if (var2 >= this.length) {
            throw new IllegalArgumentException("end, " + var2 + ", is greater than the highest index, " + (this.length - 1));
        } else {
            Complex[] var3 = this.getArray_as_Complex();
            double[] var4 = new double[var2 - var1 + 1];

            for(int var5 = var1; var5 <= var2; ++var5) {
                var4[var5 - var1] = var3[var5].getImag();
            }

            return var4;
        }
    }

    public double[] subarray_as_magnitude_of_Phasor(int var1, int var2) {
        if (var2 >= this.length) {
            throw new IllegalArgumentException("end, " + var2 + ", is greater than the highest index, " + (this.length - 1));
        } else {
            Phasor[] var3 = this.getArray_as_Phasor();
            double[] var4 = new double[var2 - var1 + 1];

            for(int var5 = var1; var5 <= var2; ++var5) {
                var4[var5 - var1] = var3[var5].getMagnitude();
            }

            return var4;
        }
    }

    public double[] subarray_as_degrees_phase_of_Phasor(int var1, int var2) {
        if (var2 >= this.length) {
            throw new IllegalArgumentException("end, " + var2 + ", is greater than the highest index, " + (this.length - 1));
        } else {
            Phasor[] var3 = this.getArray_as_Phasor();
            double[] var4 = new double[var2 - var1 + 1];

            for(int var5 = var1; var5 <= var2; ++var5) {
                var4[var5 - var1] = var3[var5].getPhaseInDegrees();
            }

            return var4;
        }
    }

    public double[] subarray_as_radians_phase_of_Phasor(int var1, int var2) {
        if (var2 >= this.length) {
            throw new IllegalArgumentException("end, " + var2 + ", is greater than the highest index, " + (this.length - 1));
        } else {
            Phasor[] var3 = this.getArray_as_Phasor();
            double[] var4 = new double[var2 - var1 + 1];

            for(int var5 = var1; var5 <= var2; ++var5) {
                var4[var5 - var1] = var3[var5].getPhaseInRadians();
            }

            return var4;
        }
    }

    protected void minmax() {
        int[] var1 = new int[2];
        findMinMax(this.getArray_as_Object(), this.minmax, var1, this.typeName, this.type);
        this.maxIndex = var1[0];
        this.minIndex = var1[1];
    }

    protected static void findMinMax(Object[] var0, ArrayList<Object> var1, int[] var2, String[] var3, int var4) {
        int var5 = 0;
        int var6 = 0;
        int var7 = var0.length;
        switch(var4) {
            case 0:
            case 1:
                double[] var8 = new double[var7];

                for(int var9 = 0; var9 < var7; ++var9) {
                    var8[var9] = (Double)var0[var9];
                }

                double var40 = var8[0];
                double var11 = var8[0];
                var5 = 0;
                var6 = 0;

                for(int var41 = 1; var41 < var7; ++var41) {
                    if (var8[var41] > var40) {
                        var40 = var8[var41];
                        var5 = var41;
                    }

                    if (var8[var41] < var11) {
                        var11 = var8[var41];
                        var6 = var41;
                    }
                }

                var1.add(new Double(var40));
                var1.add(new Double(var11));
                break;
            case 2:
            case 3:
                float[] var43 = new float[var7];

                for(int var19 = 0; var19 < var7; ++var19) {
                    var43[var19] = (Float)var0[var19];
                }

                float var44 = var43[0];
                float var20 = var43[0];
                var5 = 0;
                var6 = 0;

                for(int var45 = 1; var45 < var7; ++var45) {
                    if (var43[var45] > var44) {
                        var44 = var43[var45];
                        var5 = var45;
                    }

                    if (var43[var45] < var20) {
                        var20 = var43[var45];
                        var6 = var45;
                    }
                }

                var1.add(new Float(var44));
                var1.add(new Float(var20));
                break;
            case 4:
            case 5:
                long[] var13 = new long[var7];

                for(int var14 = 0; var14 < var7; ++var14) {
                    var13[var14] = (Long)var0[var14];
                }

                long var42 = var13[0];
                long var16 = var13[0];
                var5 = 0;
                var6 = 0;

                for(int var18 = 1; var18 < var7; ++var18) {
                    if (var13[var18] > var42) {
                        var42 = var13[var18];
                        var5 = var18;
                    }

                    if (var13[var18] < var16) {
                        var16 = var13[var18];
                        var6 = var18;
                    }
                }

                var1.add(new Long(var42));
                var1.add(new Long(var16));
                break;
            case 6:
            case 7:
                int[] var21 = new int[var7];

                int var22;
                for(var22 = 0; var22 < var7; ++var22) {
                    var21[var22] = (Integer)var0[var22];
                }

                var22 = var21[0];
                int var23 = var21[0];
                var5 = 0;
                var6 = 0;

                for(int var46 = 1; var46 < var7; ++var46) {
                    if (var21[var46] > var22) {
                        var22 = var21[var46];
                        var5 = var46;
                    }

                    if (var21[var46] < var23) {
                        var23 = var21[var46];
                        var6 = var46;
                    }
                }

                var1.add(new Integer(var22));
                var1.add(new Integer(var23));
                break;
            case 8:
            case 9:
                short[] var24 = new short[var7];

                for(int var25 = 0; var25 < var7; ++var25) {
                    var24[var25] = (Short)var0[var25];
                }

                short var47 = var24[0];
                short var26 = var24[0];
                var5 = 0;
                var6 = 0;

                for(int var48 = 1; var48 < var7; ++var48) {
                    if (var24[var48] > var47) {
                        var47 = var24[var48];
                        var5 = var48;
                    }

                    if (var24[var48] < var26) {
                        var26 = var24[var48];
                        var6 = var48;
                    }
                }

                var1.add(new Short(var47));
                var1.add(new Short(var26));
                break;
            case 10:
            case 11:
                byte[] var27 = new byte[var7];

                for(int var28 = 0; var28 < var7; ++var28) {
                    var27[var28] = (Byte)var0[var28];
                }

                byte var49 = var27[0];
                byte var29 = var27[0];
                var5 = 0;
                var6 = 0;

                for(int var50 = 1; var50 < var7; ++var50) {
                    if (var27[var50] > var49) {
                        var49 = var27[var50];
                        var5 = var50;
                    }

                    if (var27[var50] < var29) {
                        var29 = var27[var50];
                        var6 = var50;
                    }
                }

                var1.add(new Byte(var49));
                var1.add(new Byte(var29));
                break;
            case 12:
                BigDecimal[] var30 = new BigDecimal[var7];

                for(int var31 = 0; var31 < var7; ++var31) {
                    var30[var31] = (BigDecimal)var0[var31];
                }

                BigDecimal var51 = var30[0];
                BigDecimal var32 = var30[0];
                var5 = 0;
                var6 = 0;

                for(int var52 = 1; var52 < var7; ++var52) {
                    if (var30[var52].compareTo(var51) == 1) {
                        var51 = var30[var52];
                        var5 = var52;
                    }

                    if (var30[var52].compareTo(var32) == -1) {
                        var32 = var30[var52];
                        var6 = var52;
                    }
                }

                var1.add(var51);
                var1.add(var32);
                break;
            case 13:
                BigInteger[] var33 = new BigInteger[var7];

                for(int var34 = 0; var34 < var7; ++var34) {
                    var33[var34] = (BigInteger)var0[var34];
                }

                BigInteger var53 = var33[0];
                BigInteger var35 = var33[0];
                var5 = 0;
                var6 = 0;

                for(int var54 = 1; var54 < var7; ++var54) {
                    if (var33[var54].compareTo(var53) == 1) {
                        var53 = var33[var54];
                        var5 = var54;
                    }

                    if (var33[var54].compareTo(var35) == -1) {
                        var35 = var33[var54];
                        var6 = var54;
                    }
                }

                var1.add(var53);
                var1.add(var35);
                break;
            case 14:
            case 15:
            case 18:
                System.out.println("ArrayMaths:  getMaximum_... or getMinimum_... (findMinMax): the " + var3[var4] + " is not a numerical type for which a maximum or a minimum is meaningful/supported");
                break;
            case 16:
            case 17:
                int[] var36 = new int[var7];

                int var37;
                for(var37 = 0; var37 < var7; ++var37) {
                    var36[var37] = (Character)var0[var37];
                }

                var37 = var36[0];
                int var38 = var36[0];
                var5 = 0;
                var6 = 0;

                for(int var39 = 1; var39 < var7; ++var39) {
                    if (var36[var39] > var37) {
                        var37 = var36[var39];
                        var5 = var39;
                    }

                    if (var36[var39] < var38) {
                        var38 = var36[var39];
                        var6 = var39;
                    }
                }

                var1.add(new Character((char)var37));
                var1.add(new Character((char)var38));
                break;
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        var2[0] = var5;
        var2[1] = var6;
    }

    public double maximum() {
        return this.getMaximum_as_double();
    }

    public double maximum_as_double() {
        return this.getMaximum_as_double();
    }

    public double getMaximum() {
        return this.getMaximum_as_double();
    }

    public double getMaximum_as_double() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        double var1 = 0.0D;
        switch(this.type) {
            case 0:
            case 1:
                var1 = (Double)this.minmax.get(0);
                break;
            case 2:
            case 3:
                var1 = Conv.convert_Float_to_double((Float)this.minmax.get(0));
                break;
            case 4:
            case 5:
                var1 = Conv.convert_Long_to_double((Long)this.minmax.get(0));
                break;
            case 6:
            case 7:
                var1 = Conv.convert_Integer_to_double((Integer)this.minmax.get(0));
                break;
            case 8:
            case 9:
                var1 = Conv.convert_Short_to_double((Short)this.minmax.get(0));
                break;
            case 10:
            case 11:
                var1 = Conv.convert_Byte_to_double((Byte)this.minmax.get(0));
                break;
            case 12:
                var1 = Conv.convert_BigDecimal_to_double((BigDecimal)this.minmax.get(0));
                break;
            case 13:
                var1 = Conv.convert_BigInteger_to_double((BigInteger)this.minmax.get(0));
                break;
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a maximum is meaningful/supported");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var1;
    }

    public Double maximum_as_Double() {
        return this.getMaximum_as_Double();
    }

    public Double getMaximum_as_Double() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        new Double(0.0D);
        Double var1;
        switch(this.type) {
            case 0:
            case 1:
                var1 = (Double)this.minmax.get(0);
                break;
            case 2:
            case 3:
                var1 = Conv.convert_Float_to_Double((Float)this.minmax.get(0));
                break;
            case 4:
            case 5:
                var1 = Conv.convert_Long_to_Double((Long)this.minmax.get(0));
                break;
            case 6:
            case 7:
                var1 = Conv.convert_Integer_to_Double((Integer)this.minmax.get(0));
                break;
            case 8:
            case 9:
                var1 = Conv.convert_Short_to_Double((Short)this.minmax.get(0));
                break;
            case 10:
            case 11:
                var1 = Conv.convert_Byte_to_Double((Byte)this.minmax.get(0));
                break;
            case 12:
                var1 = Conv.convert_BigDecimal_to_Double((BigDecimal)this.minmax.get(0));
                break;
            case 13:
                var1 = Conv.convert_BigInteger_to_Double((BigInteger)this.minmax.get(0));
                break;
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a maximum is meaningful/supported");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var1;
    }

    public Float maximum_as_Float() {
        return this.getMaximum_as_Float();
    }

    public Float getMaximum_as_Float() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        new Float(0.0D);
        Float var1;
        switch(this.type) {
            case 0:
            case 1:
                var1 = Conv.convert_Double_to_Float((Double)this.minmax.get(0));
                break;
            case 2:
            case 3:
                var1 = (Float)this.minmax.get(0);
                break;
            case 4:
            case 5:
                var1 = Conv.convert_Long_to_Float((Long)this.minmax.get(0));
                break;
            case 6:
            case 7:
                var1 = Conv.convert_Integer_to_Float((Integer)this.minmax.get(0));
                break;
            case 8:
            case 9:
                var1 = Conv.convert_Short_to_Float((Short)this.minmax.get(0));
                break;
            case 10:
            case 11:
                var1 = Conv.convert_Byte_to_Float((Byte)this.minmax.get(0));
                break;
            case 12:
                var1 = Conv.convert_BigDecimal_to_Float((BigDecimal)this.minmax.get(0));
                break;
            case 13:
                var1 = Conv.convert_BigInteger_to_Float((BigInteger)this.minmax.get(0));
                break;
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a maximum is meaningful/supported");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var1;
    }

    public float maximum_as_float() {
        return this.getMaximum_as_float();
    }

    public float getMaximum_as_float() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        float var1 = 0.0F;
        switch(this.type) {
            case 0:
            case 1:
                var1 = Conv.convert_Double_to_float((Double)this.minmax.get(0));
                break;
            case 2:
            case 3:
                var1 = (Float)this.minmax.get(0);
                break;
            case 4:
            case 5:
                var1 = Conv.convert_Long_to_float((Long)this.minmax.get(0));
                break;
            case 6:
            case 7:
                var1 = Conv.convert_Integer_to_float((Integer)this.minmax.get(0));
                break;
            case 8:
            case 9:
                var1 = Conv.convert_Short_to_float((Short)this.minmax.get(0));
                break;
            case 10:
            case 11:
                var1 = Conv.convert_Byte_to_float((Byte)this.minmax.get(0));
                break;
            case 12:
                var1 = Conv.convert_BigDecimal_to_float((BigDecimal)this.minmax.get(0));
                break;
            case 13:
                var1 = Conv.convert_BigInteger_to_float((BigInteger)this.minmax.get(0));
                break;
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a maximum is meaningful/supported");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var1;
    }

    public long maximum_as_long() {
        return this.getMaximum_as_long();
    }

    public long getMaximum_as_long() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        long var1 = 0L;
        switch(this.type) {
            case 0:
            case 1:
                var1 = Conv.convert_Double_to_long((Double)this.minmax.get(0));
                break;
            case 2:
            case 3:
                var1 = Conv.convert_Float_to_long((Float)this.minmax.get(0));
                break;
            case 4:
            case 5:
                var1 = (Long)this.minmax.get(0);
                break;
            case 6:
            case 7:
                var1 = Conv.convert_Integer_to_long((Integer)this.minmax.get(0));
                break;
            case 8:
            case 9:
                var1 = Conv.convert_Short_to_long((Short)this.minmax.get(0));
                break;
            case 10:
            case 11:
                var1 = Conv.convert_Byte_to_long((Byte)this.minmax.get(0));
                break;
            case 12:
                var1 = Conv.convert_BigDecimal_to_long((BigDecimal)this.minmax.get(0));
                break;
            case 13:
                var1 = Conv.convert_BigInteger_to_long((BigInteger)this.minmax.get(0));
                break;
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a maximum is meaningful/supported");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var1;
    }

    public Long maximum_as_Long() {
        return this.getMaximum_as_Long();
    }

    public Long getMaximum_as_Long() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        new Long(0L);
        Long var1;
        switch(this.type) {
            case 0:
            case 1:
                var1 = Conv.convert_Double_to_Long((Double)this.minmax.get(0));
                break;
            case 2:
            case 3:
                var1 = Conv.convert_Float_to_Long((Float)this.minmax.get(0));
                break;
            case 4:
            case 5:
                var1 = (Long)this.minmax.get(0);
                break;
            case 6:
            case 7:
                var1 = Conv.convert_Integer_to_Long((Integer)this.minmax.get(0));
                break;
            case 8:
            case 9:
                var1 = Conv.convert_Short_to_Long((Short)this.minmax.get(0));
                break;
            case 10:
            case 11:
                var1 = Conv.convert_Byte_to_Long((Byte)this.minmax.get(0));
                break;
            case 12:
                var1 = Conv.convert_BigDecimal_to_Long((BigDecimal)this.minmax.get(0));
                break;
            case 13:
                var1 = Conv.convert_BigInteger_to_Long((BigInteger)this.minmax.get(0));
                break;
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a maximum is meaningful/supported");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var1;
    }

    public int maximum_as_int() {
        return this.getMaximum_as_int();
    }

    public int getMaximum_as_int() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        boolean var1 = false;
        int var2;
        switch(this.type) {
            case 0:
            case 1:
                var2 = Conv.convert_Double_to_int((Double)this.minmax.get(0));
                break;
            case 2:
            case 3:
                var2 = Conv.convert_Float_to_int((Float)this.minmax.get(0));
                break;
            case 4:
            case 5:
                var2 = Conv.convert_Long_to_int((Long)this.minmax.get(0));
                break;
            case 6:
            case 7:
                var2 = (Integer)this.minmax.get(0);
                break;
            case 8:
            case 9:
                var2 = Conv.convert_Short_to_int((Short)this.minmax.get(0));
                break;
            case 10:
            case 11:
                var2 = Conv.convert_Byte_to_int((Byte)this.minmax.get(0));
                break;
            case 12:
                var2 = Conv.convert_BigDecimal_to_int((BigDecimal)this.minmax.get(0));
                break;
            case 13:
                var2 = Conv.convert_BigInteger_to_int((BigInteger)this.minmax.get(0));
                break;
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a maximum is meaningful/supported");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var2;
    }

    public Integer maximum_as_Integer() {
        return this.getMaximum_as_Integer();
    }

    public Integer getMaximum_as_Integer() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        new Integer(0);
        Integer var1;
        switch(this.type) {
            case 0:
            case 1:
                var1 = Conv.convert_Double_to_Integer((Double)this.minmax.get(0));
                break;
            case 2:
            case 3:
                var1 = Conv.convert_Float_to_Integer((Float)this.minmax.get(0));
                break;
            case 4:
            case 5:
                var1 = Conv.convert_Long_to_Integer((Long)this.minmax.get(0));
                break;
            case 6:
            case 7:
                var1 = (Integer)this.minmax.get(0);
                break;
            case 8:
            case 9:
                var1 = Conv.convert_Short_to_Integer((Short)this.minmax.get(0));
                break;
            case 10:
            case 11:
                var1 = Conv.convert_Byte_to_Integer((Byte)this.minmax.get(0));
                break;
            case 12:
                var1 = Conv.convert_BigDecimal_to_Integer((BigDecimal)this.minmax.get(0));
                break;
            case 13:
                var1 = Conv.convert_BigInteger_to_Integer((BigInteger)this.minmax.get(0));
                break;
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a maximum is meaningful/supported");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var1;
    }

    public short maximum_as_short() {
        return this.getMaximum_as_short();
    }

    public short getMaximum_as_short() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        boolean var1 = false;
        short var2;
        switch(this.type) {
            case 0:
            case 1:
                var2 = Conv.convert_Double_to_short((Double)this.minmax.get(0));
                break;
            case 2:
            case 3:
                var2 = Conv.convert_Float_to_short((Float)this.minmax.get(0));
                break;
            case 4:
            case 5:
                var2 = Conv.convert_Long_to_short((Long)this.minmax.get(0));
                break;
            case 6:
            case 7:
                var2 = Conv.convert_Integer_to_short((Integer)this.minmax.get(0));
                break;
            case 8:
            case 9:
                var2 = (Short)this.minmax.get(0);
                break;
            case 10:
            case 11:
                var2 = Conv.convert_Byte_to_short((Byte)this.minmax.get(0));
                break;
            case 12:
                var2 = Conv.convert_BigDecimal_to_short((BigDecimal)this.minmax.get(0));
                break;
            case 13:
                var2 = Conv.convert_BigInteger_to_short((BigInteger)this.minmax.get(0));
                break;
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a maximum is meaningful/supported");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var2;
    }

    public Short maximum_as_Short() {
        return this.getMaximum_as_Short();
    }

    public Short getMaximum_as_Short() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        new Short((short)0);
        Short var1;
        switch(this.type) {
            case 0:
            case 1:
                var1 = Conv.convert_Double_to_Short((Double)this.minmax.get(0));
                break;
            case 2:
            case 3:
                var1 = Conv.convert_Float_to_Short((Float)this.minmax.get(0));
                break;
            case 4:
            case 5:
                var1 = Conv.convert_Long_to_Short((Long)this.minmax.get(0));
                break;
            case 6:
            case 7:
                var1 = Conv.convert_Integer_to_Short((Integer)this.minmax.get(0));
                break;
            case 8:
            case 9:
                var1 = (Short)this.minmax.get(0);
                break;
            case 10:
            case 11:
                var1 = Conv.convert_Byte_to_Short((Byte)this.minmax.get(0));
                break;
            case 12:
                var1 = Conv.convert_BigDecimal_to_Short((BigDecimal)this.minmax.get(0));
                break;
            case 13:
                var1 = Conv.convert_BigInteger_to_Short((BigInteger)this.minmax.get(0));
                break;
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a maximum is meaningful/supported");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var1;
    }

    public byte maximum_as_byte() {
        return this.getMaximum_as_byte();
    }

    public byte getMaximum_as_byte() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        boolean var1 = false;
        byte var2;
        switch(this.type) {
            case 0:
            case 1:
                var2 = Conv.convert_Double_to_byte((Double)this.minmax.get(0));
                break;
            case 2:
            case 3:
                var2 = Conv.convert_Float_to_byte((Float)this.minmax.get(0));
                break;
            case 4:
            case 5:
                var2 = Conv.convert_Long_to_byte((Long)this.minmax.get(0));
                break;
            case 6:
            case 7:
                var2 = Conv.convert_Integer_to_byte((Integer)this.minmax.get(0));
                break;
            case 8:
            case 9:
                var2 = Conv.convert_Short_to_byte((Short)this.minmax.get(0));
                break;
            case 10:
            case 11:
                var2 = (Byte)this.minmax.get(0);
                break;
            case 12:
                var2 = Conv.convert_BigDecimal_to_byte((BigDecimal)this.minmax.get(0));
                break;
            case 13:
                var2 = Conv.convert_BigInteger_to_byte((BigInteger)this.minmax.get(0));
                break;
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a maximum is meaningful/supported");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var2;
    }

    public Byte maximum_as_Byte() {
        return this.getMaximum_as_Byte();
    }

    public Byte getMaximum_as_Byte() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        new Byte((byte)0);
        Byte var1;
        switch(this.type) {
            case 0:
            case 1:
                var1 = Conv.convert_Double_to_Byte((Double)this.minmax.get(0));
                break;
            case 2:
            case 3:
                var1 = Conv.convert_Float_to_Byte((Float)this.minmax.get(0));
                break;
            case 4:
            case 5:
                var1 = Conv.convert_Long_to_Byte((Long)this.minmax.get(0));
                break;
            case 6:
            case 7:
                var1 = Conv.convert_Integer_to_Byte((Integer)this.minmax.get(0));
                break;
            case 8:
            case 9:
                var1 = Conv.convert_Short_to_Byte((Short)this.minmax.get(0));
                break;
            case 10:
            case 11:
                var1 = (Byte)this.minmax.get(0);
                break;
            case 12:
                var1 = Conv.convert_BigDecimal_to_Byte((BigDecimal)this.minmax.get(0));
                break;
            case 13:
                var1 = Conv.convert_BigInteger_to_Byte((BigInteger)this.minmax.get(0));
                break;
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a maximum is meaningful/supported");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var1;
    }

    public BigDecimal maximum_as_BigDecimal() {
        return this.getMaximum_as_BigDecimal();
    }

    public BigDecimal getMaximum_as_BigDecimal() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        new BigDecimal(0.0D);
        BigDecimal var1;
        switch(this.type) {
            case 0:
            case 1:
                var1 = Conv.convert_Double_to_BigDecimal((Double)this.minmax.get(0));
                break;
            case 2:
            case 3:
                var1 = Conv.convert_Float_to_BigDecimal((Float)this.minmax.get(0));
                break;
            case 4:
            case 5:
                var1 = Conv.convert_Long_to_BigDecimal((Long)this.minmax.get(0));
                break;
            case 6:
            case 7:
                var1 = Conv.convert_Integer_to_BigDecimal((Integer)this.minmax.get(0));
                break;
            case 8:
            case 9:
                var1 = Conv.convert_Short_to_BigDecimal((Short)this.minmax.get(0));
                break;
            case 10:
            case 11:
                var1 = Conv.convert_Byte_to_BigDecimal((Byte)this.minmax.get(0));
                break;
            case 12:
                var1 = (BigDecimal)this.minmax.get(0);
                break;
            case 13:
                var1 = Conv.convert_BigInteger_to_BigDecimal((BigInteger)this.minmax.get(0));
                break;
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a maximum is meaningful/supported");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var1;
    }

    public BigInteger maximum_as_BigInteger() {
        return this.getMaximum_as_BigInteger();
    }

    public BigInteger getMaximum_as_BigInteger() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        new BigInteger("0");
        BigInteger var1;
        switch(this.type) {
            case 0:
            case 1:
                var1 = Conv.convert_Double_to_BigInteger((Double)this.minmax.get(0));
                break;
            case 2:
            case 3:
                var1 = Conv.convert_Float_to_BigInteger((Float)this.minmax.get(0));
                break;
            case 4:
            case 5:
                var1 = Conv.convert_Long_to_BigInteger((Long)this.minmax.get(0));
                break;
            case 6:
            case 7:
                var1 = Conv.convert_Integer_to_BigInteger((Integer)this.minmax.get(0));
                break;
            case 8:
            case 9:
                var1 = Conv.convert_Short_to_BigInteger((Short)this.minmax.get(0));
                break;
            case 10:
            case 11:
                var1 = Conv.convert_Byte_to_BigInteger((Byte)this.minmax.get(0));
                break;
            case 12:
                var1 = Conv.convert_BigDecimal_to_BigInteger((BigDecimal)this.minmax.get(0));
                break;
            case 13:
                var1 = (BigInteger)this.minmax.get(0);
                break;
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a maximum is meaningful/supported");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var1;
    }

    public char maximum_as_char() {
        return this.getMaximum_as_char();
    }

    public char getMaximum_as_char() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        boolean var1 = false;
        char var2;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 18:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a char type maximum is meaningful/supported");
            case 6:
            case 7:
//                var2 = (char)(Integer)this.minmax.get(1);
                var2 = (char)this.minmax.get(1);
                break;
            case 16:
            case 17:
                var2 = (Character)this.minmax.get(1);
                break;
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var2;
    }

    public Character maximum_as_Character() {
        return this.getMaximum_as_Character();
    }

    public Character getMaximum_as_Character() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        new Character('\u0000');
        Character var1;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 18:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a Character type maximum is meaningful/supported");
            case 6:
            case 7:
//                var1 = new Character((char)(Integer)this.minmax.get(1));
                var1 = (char)this.minmax.get(1);
                break;
            case 16:
            case 17:
                var1 = (Character)this.minmax.get(1);
                break;
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var1;
    }

    public double minimum() {
        return this.getMinimum_as_double();
    }

    public double minimum_as_double() {
        return this.getMinimum_as_double();
    }

    public double getMinimum() {
        return this.getMinimum_as_double();
    }

    public double getMinimum_as_double() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        double var1 = 0.0D;
        switch(this.type) {
            case 0:
            case 1:
                var1 = (Double)this.minmax.get(1);
                break;
            case 2:
            case 3:
                var1 = Conv.convert_Float_to_double((Float)this.minmax.get(1));
                break;
            case 4:
            case 5:
                var1 = Conv.convert_Long_to_double((Long)this.minmax.get(1));
                break;
            case 6:
            case 7:
                var1 = Conv.convert_Integer_to_double((Integer)this.minmax.get(1));
                break;
            case 8:
            case 9:
                var1 = Conv.convert_Short_to_double((Short)this.minmax.get(1));
                break;
            case 10:
            case 11:
                var1 = Conv.convert_Byte_to_double((Byte)this.minmax.get(1));
                break;
            case 12:
                var1 = Conv.convert_BigDecimal_to_double((BigDecimal)this.minmax.get(1));
                break;
            case 13:
                var1 = Conv.convert_BigInteger_to_double((BigInteger)this.minmax.get(1));
                break;
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a minimum is meaningful/supported");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var1;
    }

    public Double minimum_as_Double() {
        return this.getMinimum_as_Double();
    }

    public Double getMinimum_as_Double() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        new Double(0.0D);
        Double var1;
        switch(this.type) {
            case 0:
            case 1:
                var1 = (Double)this.minmax.get(1);
                break;
            case 2:
            case 3:
                var1 = Conv.convert_Float_to_Double((Float)this.minmax.get(1));
                break;
            case 4:
            case 5:
                var1 = Conv.convert_Long_to_Double((Long)this.minmax.get(1));
                break;
            case 6:
            case 7:
                var1 = Conv.convert_Integer_to_Double((Integer)this.minmax.get(1));
                break;
            case 8:
            case 9:
                var1 = Conv.convert_Short_to_Double((Short)this.minmax.get(1));
                break;
            case 10:
            case 11:
                var1 = Conv.convert_Byte_to_Double((Byte)this.minmax.get(1));
                break;
            case 12:
                var1 = Conv.convert_BigDecimal_to_Double((BigDecimal)this.minmax.get(1));
                break;
            case 13:
                var1 = Conv.convert_BigInteger_to_Double((BigInteger)this.minmax.get(1));
                break;
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a minimum is meaningful/supported");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var1;
    }

    public Float minimum_as_Float() {
        return this.getMinimum_as_Float();
    }

    public Float getMinimum_as_Float() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        new Float(0.0D);
        Float var1;
        switch(this.type) {
            case 0:
            case 1:
                var1 = Conv.convert_Double_to_Float((Double)this.minmax.get(1));
                break;
            case 2:
            case 3:
                var1 = (Float)this.minmax.get(1);
                break;
            case 4:
            case 5:
                var1 = Conv.convert_Long_to_Float((Long)this.minmax.get(1));
                break;
            case 6:
            case 7:
                var1 = Conv.convert_Integer_to_Float((Integer)this.minmax.get(1));
                break;
            case 8:
            case 9:
                var1 = Conv.convert_Short_to_Float((Short)this.minmax.get(1));
                break;
            case 10:
            case 11:
                var1 = Conv.convert_Byte_to_Float((Byte)this.minmax.get(1));
                break;
            case 12:
                var1 = Conv.convert_BigDecimal_to_Float((BigDecimal)this.minmax.get(1));
                break;
            case 13:
                var1 = Conv.convert_BigInteger_to_Float((BigInteger)this.minmax.get(1));
                break;
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a minimum is meaningful/supported");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var1;
    }

    public float minimum_as_float() {
        return this.getMinimum_as_float();
    }

    public float getMinimum_as_float() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        float var1 = 0.0F;
        switch(this.type) {
            case 0:
            case 1:
                var1 = Conv.convert_Double_to_float((Double)this.minmax.get(1));
                break;
            case 2:
            case 3:
                var1 = (Float)this.minmax.get(1);
                break;
            case 4:
            case 5:
                var1 = Conv.convert_Long_to_float((Long)this.minmax.get(1));
                break;
            case 6:
            case 7:
                var1 = Conv.convert_Integer_to_float((Integer)this.minmax.get(1));
                break;
            case 8:
            case 9:
                var1 = Conv.convert_Short_to_float((Short)this.minmax.get(1));
                break;
            case 10:
            case 11:
                var1 = Conv.convert_Byte_to_float((Byte)this.minmax.get(1));
                break;
            case 12:
                var1 = Conv.convert_BigDecimal_to_float((BigDecimal)this.minmax.get(1));
                break;
            case 13:
                var1 = Conv.convert_BigInteger_to_float((BigInteger)this.minmax.get(1));
                break;
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a minimum is meaningful/supported");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var1;
    }

    public long minimum_as_long() {
        return this.getMinimum_as_long();
    }

    public long getMinimum_as_long() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        long var1 = 0L;
        switch(this.type) {
            case 0:
            case 1:
                var1 = Conv.convert_Double_to_long((Double)this.minmax.get(1));
                break;
            case 2:
            case 3:
                var1 = Conv.convert_Float_to_long((Float)this.minmax.get(1));
                break;
            case 4:
            case 5:
                var1 = (Long)this.minmax.get(1);
                break;
            case 6:
            case 7:
                var1 = Conv.convert_Integer_to_long((Integer)this.minmax.get(1));
                break;
            case 8:
            case 9:
                var1 = Conv.convert_Short_to_long((Short)this.minmax.get(1));
                break;
            case 10:
            case 11:
                var1 = Conv.convert_Byte_to_long((Byte)this.minmax.get(1));
                break;
            case 12:
                var1 = Conv.convert_BigDecimal_to_long((BigDecimal)this.minmax.get(1));
                break;
            case 13:
                var1 = Conv.convert_BigInteger_to_long((BigInteger)this.minmax.get(1));
                break;
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a minimum is meaningful/supported");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var1;
    }

    public Long minimum_as_Long() {
        return this.getMinimum_as_Long();
    }

    public Long getMinimum_as_Long() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        new Long(0L);
        Long var1;
        switch(this.type) {
            case 0:
            case 1:
                var1 = Conv.convert_Double_to_Long((Double)this.minmax.get(1));
                break;
            case 2:
            case 3:
                var1 = Conv.convert_Float_to_Long((Float)this.minmax.get(1));
                break;
            case 4:
            case 5:
                var1 = (Long)this.minmax.get(1);
                break;
            case 6:
            case 7:
                var1 = Conv.convert_Integer_to_Long((Integer)this.minmax.get(1));
                break;
            case 8:
            case 9:
                var1 = Conv.convert_Short_to_Long((Short)this.minmax.get(1));
                break;
            case 10:
            case 11:
                var1 = Conv.convert_Byte_to_Long((Byte)this.minmax.get(1));
                break;
            case 12:
                var1 = Conv.convert_BigDecimal_to_Long((BigDecimal)this.minmax.get(1));
                break;
            case 13:
                var1 = Conv.convert_BigInteger_to_Long((BigInteger)this.minmax.get(1));
                break;
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a minimum is meaningful/supported");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var1;
    }

    public int minimum_as_int() {
        return this.getMinimum_as_int();
    }

    public int getMinimum_as_int() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        boolean var1 = false;
        int var2;
        switch(this.type) {
            case 0:
            case 1:
                var2 = Conv.convert_Double_to_int((Double)this.minmax.get(1));
                break;
            case 2:
            case 3:
                var2 = Conv.convert_Float_to_int((Float)this.minmax.get(1));
                break;
            case 4:
            case 5:
                var2 = Conv.convert_Long_to_int((Long)this.minmax.get(1));
                break;
            case 6:
            case 7:
                var2 = (Integer)this.minmax.get(1);
                break;
            case 8:
            case 9:
                var2 = Conv.convert_Short_to_int((Short)this.minmax.get(1));
                break;
            case 10:
            case 11:
                var2 = Conv.convert_Byte_to_int((Byte)this.minmax.get(1));
                break;
            case 12:
                var2 = Conv.convert_BigDecimal_to_int((BigDecimal)this.minmax.get(1));
                break;
            case 13:
                var2 = Conv.convert_BigInteger_to_int((BigInteger)this.minmax.get(1));
                break;
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a minimum is meaningful/supported");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var2;
    }

    public Integer minimum_as_Integer() {
        return this.getMinimum_as_Integer();
    }

    public Integer getMinimum_as_Integer() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        new Integer(0);
        Integer var1;
        switch(this.type) {
            case 0:
            case 1:
                var1 = Conv.convert_Double_to_Integer((Double)this.minmax.get(1));
                break;
            case 2:
            case 3:
                var1 = Conv.convert_Float_to_Integer((Float)this.minmax.get(1));
                break;
            case 4:
            case 5:
                var1 = Conv.convert_Long_to_Integer((Long)this.minmax.get(1));
                break;
            case 6:
            case 7:
                var1 = (Integer)this.minmax.get(1);
                break;
            case 8:
            case 9:
                var1 = Conv.convert_Short_to_Integer((Short)this.minmax.get(1));
                break;
            case 10:
            case 11:
                var1 = Conv.convert_Byte_to_Integer((Byte)this.minmax.get(1));
                break;
            case 12:
                var1 = Conv.convert_BigDecimal_to_Integer((BigDecimal)this.minmax.get(1));
                break;
            case 13:
                var1 = Conv.convert_BigInteger_to_Integer((BigInteger)this.minmax.get(1));
                break;
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a minimum is meaningful/supported");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var1;
    }

    public short minimum_as_short() {
        return this.getMinimum_as_short();
    }

    public short getMinimum_as_short() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        boolean var1 = false;
        short var2;
        switch(this.type) {
            case 0:
            case 1:
                var2 = Conv.convert_Double_to_short((Double)this.minmax.get(1));
                break;
            case 2:
            case 3:
                var2 = Conv.convert_Float_to_short((Float)this.minmax.get(1));
                break;
            case 4:
            case 5:
                var2 = Conv.convert_Long_to_short((Long)this.minmax.get(1));
                break;
            case 6:
            case 7:
                var2 = Conv.convert_Integer_to_short((Integer)this.minmax.get(1));
                break;
            case 8:
            case 9:
                var2 = (Short)this.minmax.get(1);
                break;
            case 10:
            case 11:
                var2 = Conv.convert_Byte_to_short((Byte)this.minmax.get(1));
                break;
            case 12:
                var2 = Conv.convert_BigDecimal_to_short((BigDecimal)this.minmax.get(1));
                break;
            case 13:
                var2 = Conv.convert_BigInteger_to_short((BigInteger)this.minmax.get(1));
                break;
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a minimum is meaningful/supported");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var2;
    }

    public Short minimum_as_Short() {
        return this.getMinimum_as_Short();
    }

    public Short getMinimum_as_Short() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        new Short((short)0);
        Short var1;
        switch(this.type) {
            case 0:
            case 1:
                var1 = Conv.convert_Double_to_Short((Double)this.minmax.get(1));
                break;
            case 2:
            case 3:
                var1 = Conv.convert_Float_to_Short((Float)this.minmax.get(1));
                break;
            case 4:
            case 5:
                var1 = Conv.convert_Long_to_Short((Long)this.minmax.get(1));
                break;
            case 6:
            case 7:
                var1 = Conv.convert_Integer_to_Short((Integer)this.minmax.get(1));
                break;
            case 8:
            case 9:
                var1 = (Short)this.minmax.get(1);
                break;
            case 10:
            case 11:
                var1 = Conv.convert_Byte_to_Short((Byte)this.minmax.get(1));
                break;
            case 12:
                var1 = Conv.convert_BigDecimal_to_Short((BigDecimal)this.minmax.get(1));
                break;
            case 13:
                var1 = Conv.convert_BigInteger_to_Short((BigInteger)this.minmax.get(1));
                break;
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a minimum is meaningful/supported");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var1;
    }

    public byte minimum_as_byte() {
        return this.getMinimum_as_byte();
    }

    public byte getMinimum_as_byte() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        boolean var1 = false;
        byte var2;
        switch(this.type) {
            case 0:
            case 1:
                var2 = Conv.convert_Double_to_byte((Double)this.minmax.get(1));
                break;
            case 2:
            case 3:
                var2 = Conv.convert_Float_to_byte((Float)this.minmax.get(1));
                break;
            case 4:
            case 5:
                var2 = Conv.convert_Long_to_byte((Long)this.minmax.get(1));
                break;
            case 6:
            case 7:
                var2 = Conv.convert_Integer_to_byte((Integer)this.minmax.get(1));
                break;
            case 8:
            case 9:
                var2 = Conv.convert_Short_to_byte((Short)this.minmax.get(1));
                break;
            case 10:
            case 11:
                var2 = (Byte)this.minmax.get(1);
                break;
            case 12:
                var2 = Conv.convert_BigDecimal_to_byte((BigDecimal)this.minmax.get(1));
                break;
            case 13:
                var2 = Conv.convert_BigInteger_to_byte((BigInteger)this.minmax.get(1));
                break;
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a minimum is meaningful/supported");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var2;
    }

    public Byte minimum_as_Byte() {
        return this.getMinimum_as_Byte();
    }

    public Byte getMinimum_as_Byte() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        new Byte((byte)0);
        Byte var1;
        switch(this.type) {
            case 0:
            case 1:
                var1 = Conv.convert_Double_to_Byte((Double)this.minmax.get(1));
                break;
            case 2:
            case 3:
                var1 = Conv.convert_Float_to_Byte((Float)this.minmax.get(1));
                break;
            case 4:
            case 5:
                var1 = Conv.convert_Long_to_Byte((Long)this.minmax.get(1));
                break;
            case 6:
            case 7:
                var1 = Conv.convert_Integer_to_Byte((Integer)this.minmax.get(1));
                break;
            case 8:
            case 9:
                var1 = Conv.convert_Short_to_Byte((Short)this.minmax.get(1));
                break;
            case 10:
            case 11:
                var1 = (Byte)this.minmax.get(1);
                break;
            case 12:
                var1 = Conv.convert_BigDecimal_to_Byte((BigDecimal)this.minmax.get(1));
                break;
            case 13:
                var1 = Conv.convert_BigInteger_to_Byte((BigInteger)this.minmax.get(1));
                break;
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a minimum is meaningful/supported");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var1;
    }

    public BigDecimal minimum_as_BigDecimal() {
        return this.getMinimum_as_BigDecimal();
    }

    public BigDecimal getMinimum_as_BigDecimal() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        new BigDecimal(0.0D);
        BigDecimal var1;
        switch(this.type) {
            case 0:
            case 1:
                var1 = Conv.convert_Double_to_BigDecimal((Double)this.minmax.get(1));
                break;
            case 2:
            case 3:
                var1 = Conv.convert_Float_to_BigDecimal((Float)this.minmax.get(1));
                break;
            case 4:
            case 5:
                var1 = Conv.convert_Long_to_BigDecimal((Long)this.minmax.get(1));
                break;
            case 6:
            case 7:
                var1 = Conv.convert_Integer_to_BigDecimal((Integer)this.minmax.get(1));
                break;
            case 8:
            case 9:
                var1 = Conv.convert_Short_to_BigDecimal((Short)this.minmax.get(1));
                break;
            case 10:
            case 11:
                var1 = Conv.convert_Byte_to_BigDecimal((Byte)this.minmax.get(1));
                break;
            case 12:
                var1 = (BigDecimal)this.minmax.get(1);
                break;
            case 13:
                var1 = Conv.convert_BigInteger_to_BigDecimal((BigInteger)this.minmax.get(1));
                break;
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a minimum is meaningful/supported");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var1;
    }

    public BigInteger minimum_as_BigInteger() {
        return this.getMinimum_as_BigInteger();
    }

    public BigInteger getMinimum_as_BigInteger() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        new BigInteger("0");
        BigInteger var1;
        switch(this.type) {
            case 0:
            case 1:
                var1 = Conv.convert_Double_to_BigInteger((Double)this.minmax.get(1));
                break;
            case 2:
            case 3:
                var1 = Conv.convert_Float_to_BigInteger((Float)this.minmax.get(1));
                break;
            case 4:
            case 5:
                var1 = Conv.convert_Long_to_BigInteger((Long)this.minmax.get(1));
                break;
            case 6:
            case 7:
                var1 = Conv.convert_Integer_to_BigInteger((Integer)this.minmax.get(1));
                break;
            case 8:
            case 9:
                var1 = Conv.convert_Short_to_BigInteger((Short)this.minmax.get(1));
                break;
            case 10:
            case 11:
                var1 = Conv.convert_Byte_to_BigInteger((Byte)this.minmax.get(1));
                break;
            case 12:
                var1 = Conv.convert_BigDecimal_to_BigInteger((BigDecimal)this.minmax.get(1));
                break;
            case 13:
                var1 = (BigInteger)this.minmax.get(1);
                break;
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a minimum is meaningful/supported");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var1;
    }

    public char minimum_as_char() {
        return this.getMinimum_as_char();
    }

    public char getMinimum_as_char() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        boolean var1 = false;
        char var2;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 18:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a char type minimum is meaningful/supported");
            case 6:
            case 7:
//                var2 = (char)(Integer)this.minmax.get(1);
                var2 = (char)this.minmax.get(1);
                break;
            case 16:
            case 17:
                var2 = (Character)this.minmax.get(1);
                break;
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var2;
    }

    public Character minimum_as_Character() {
        return this.getMinimum_as_Character();
    }

    public Character getMinimum_as_Character() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        new Character('\u0000');
        Character var1;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 18:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a Character type minimum is meaningful/supported");
            case 6:
            case 7:
//                var1 = new Character((char)(Integer)this.minmax.get(1));
                var1 = (char)this.minmax.get(1);
                break;
            case 16:
            case 17:
                var1 = (Character)this.minmax.get(1);
                break;
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var1;
    }

    public int maximumIndex() {
        return this.maxIndex;
    }

    public int getMaximumIndex() {
        return this.maxIndex;
    }

    public int minimumIndex() {
        return this.minIndex;
    }

    public int getMinimumIndex() {
        return this.minIndex;
    }

    public boolean isInteger() {
        boolean var1 = false;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 18:
                double[] var2 = this.getArray_as_double();
                var1 = Fmath.isInteger(var2);
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 13:
            case 16:
            case 17:
                var1 = true;
                break;
            case 12:
                BigDecimal[] var3 = this.getArray_as_BigDecimal();
                var1 = Fmath.isInteger(var3);
                break;
            case 14:
            case 15:
                var1 = false;
                break;
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        return var1;
    }

    public ArrayMaths plus(double var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var3 = new ArrayMaths();
        var3.array = new ArrayList();
        var3.length = this.length;
        String var4 = Double.toString(var1);
        int var6;
        BigInteger var7;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                double[] var5 = this.getArray_as_double();

                for(var6 = 0; var6 < this.length; ++var6) {
                    var3.array.add(new Double(var5[var6] + var1));
                }

                var3.type = 0;
                break;
            case 12:
                for(var6 = 0; var6 < this.length; ++var6) {
                    BigDecimal var10 = (BigDecimal)((BigDecimal)this.array.get(var6));
                    var10 = var10.add(new BigDecimal(var1));
                    var3.array.add(var10);
                    var7 = null;
                }

                var3.type = this.type;
                break;
            case 13:
                for(var6 = 0; var6 < this.length; ++var6) {
                    var7 = (BigInteger)((BigInteger)this.array.get(var6));
                    BigDecimal var8 = (new BigDecimal(var7)).add(new BigDecimal(var4));
                    var3.array.add(var8);
                    var7 = null;
                    var8 = null;
                }

                var3.type = 12;
                break;
            case 14:
                for(var6 = 0; var6 < this.length; ++var6) {
                    var3.array.add(((Complex)this.array.get(var6)).plus(var1));
                }

                var3.type = this.type;
                break;
            case 15:
                for(var6 = 0; var6 < this.length; ++var6) {
                    var3.array.add(((Phasor)this.array.get(var6)).plus(new Complex(var1)));
                }

                var3.type = this.type;
                break;
            case 16:
                throw new IllegalArgumentException("a double or float cannot be added to a char");
            case 17:
                throw new IllegalArgumentException("a double or float cannot be added to a Character");
            case 18:
                for(var6 = 0; var6 < this.length; ++var6) {
                    var3.array.add((String)this.array.get(var6) + var4);
                }

                var3.type = this.type;
                break;
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        int[] var9 = new int[2];
        findMinMax(var3.getArray_as_Object(), var3.minmax, var9, var3.typeName, var3.type);
        var3.maxIndex = var9[0];
        var3.minIndex = var9[1];
        Conv.restoreMessages();
        return var3;
    }

    public ArrayMaths plus(Double var1) {
        return this.plus(var1);
    }

    public ArrayMaths plus(double[] var1) {
        if (this.length != var1.length) {
            throw new IllegalArgumentException("The length of the argument array, " + var1.length + ", and the length of this instance internal array, " + this.length + ", must be equal");
        } else {
            if (this.suppressMessages) {
                Conv.suppressMessages();
            }

            ArrayMaths var2 = new ArrayMaths();
            var2.array = new ArrayList();
            var2.length = this.length;
            int var4;
            BigInteger var5;
            switch(this.type) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                    double[] var3 = this.getArray_as_double();

                    for(var4 = 0; var4 < this.length; ++var4) {
                        var2.array.add(new Double(var3[var4] + var1[var4]));
                    }

                    var2.type = 0;
                    break;
                case 12:
                    for(var4 = 0; var4 < this.length; ++var4) {
                        BigDecimal var8 = (BigDecimal)((BigDecimal)this.array.get(var4));
                        var8 = var8.add(new BigDecimal(var1[var4]));
                        var2.array.add(var8);
                        var5 = null;
                    }

                    var2.type = this.type;
                    break;
                case 13:
                    for(var4 = 0; var4 < this.length; ++var4) {
                        var5 = (BigInteger)((BigInteger)this.array.get(var4));
                        BigDecimal var6 = (new BigDecimal(var5)).add(new BigDecimal(var1[var4]));
                        var2.array.add(var6);
                        var5 = null;
                        var6 = null;
                    }

                    var2.type = 12;
                    break;
                case 14:
                    for(var4 = 0; var4 < this.length; ++var4) {
                        var2.array.add(((Complex)this.array.get(var4)).plus(var1[var4]));
                    }

                    var2.type = this.type;
                    break;
                case 15:
                    for(var4 = 0; var4 < this.length; ++var4) {
                        var2.array.add(((Phasor)this.array.get(var4)).plus(new Phasor(var1[var4])));
                    }

                    var2.type = this.type;
                    break;
                case 16:
                    throw new IllegalArgumentException("a double or float cannot be added to a char");
                case 17:
                    throw new IllegalArgumentException("a double or float cannot be added to a Character");
                case 18:
                    for(var4 = 0; var4 < this.length; ++var4) {
                        var2.array.add((String)this.array.get(var4) + Double.toString(var1[var4]));
                    }

                    var2.type = this.type;
                    break;
                default:
                    throw new IllegalArgumentException("Data type not identified by this method");
            }

            int[] var7 = new int[2];
            findMinMax(var2.getArray_as_Object(), var2.minmax, var7, var2.typeName, var2.type);
            var2.maxIndex = var7[0];
            var2.minIndex = var7[1];
            Conv.restoreMessages();
            return var2;
        }
    }

    public ArrayMaths plus(Double[] var1) {
        int var2 = var1.length;
        if (this.length != var2) {
            throw new IllegalArgumentException("The argument array [length = " + var2 + "], must be of the same length as this instance array [length = " + this.length + "]");
        } else {
            double[] var3 = new double[this.length];

            for(int var4 = 0; var4 < this.length; ++var4) {
                var3[var4] = var1[var4];
            }

            return this.plus(var3);
        }
    }

    public ArrayMaths plus(float var1) {
        double var2 = (double)var1;
        return this.plus(var2);
    }

    public ArrayMaths plus(Float var1) {
        return this.plus(var1);
    }

    public ArrayMaths plus(float[] var1) {
        if (this.length != var1.length) {
            throw new IllegalArgumentException("The length of the argument array, " + var1.length + ", and the length of this instance internal array, " + this.length + ", must be equal");
        } else {
            double[] var2 = new double[this.length];

            for(int var3 = 0; var3 < this.length; ++var3) {
                var2[var3] = (double)var1[var3];
            }

            return this.plus(var2);
        }
    }

    public ArrayMaths plus(Float[] var1) {
        int var2 = var1.length;
        if (this.length != var2) {
            throw new IllegalArgumentException("The argument array [length = " + var2 + "], must be of the same length as this instance array [length = " + this.length + "]");
        } else {
            double[] var3 = new double[this.length];

            for(int var4 = 0; var4 < this.length; ++var4) {
                var3[var4] = var1[var4].doubleValue();
            }

            return this.plus(var3);
        }
    }

    public ArrayMaths plus(long var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var3 = new ArrayMaths();
        var3.array = new ArrayList();
        var3.length = this.length;
        int var8;
        BigInteger var9;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
                double[] var4 = this.getArray_as_double();

                for(int var12 = 0; var12 < this.length; ++var12) {
                    var3.array.add(new Double(var4[var12] + (double)var1));
                }

                var3.type = 0;
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                long var5 = this.getMaximum_as_long();
                long[] var7 = this.getArray_as_long();
                if (9223372036854775807L - var5 >= var1) {
                    for(var8 = 0; var8 < this.length; ++var8) {
                        var3.array.add(new Long(var7[var8] + var1));
                    }

                    var3.type = 4;
                } else {
                    for(var8 = 0; var8 < this.length; ++var8) {
                        var3.array.add(new Double((double)var7[var8] + (double)var1));
                    }

                    var3.type = 0;
                }
                break;
            case 12:
                for(var8 = 0; var8 < this.length; ++var8) {
                    BigDecimal var13 = (BigDecimal)((BigDecimal)this.array.get(var8));
                    var13 = var13.add(new BigDecimal((double)var1));
                    var3.array.add(var13);
                    var9 = null;
                }

                var3.type = 12;
                break;
            case 13:
                for(var8 = 0; var8 < this.length; ++var8) {
                    var9 = (BigInteger)((BigInteger)this.array.get(var8));
                    BigInteger var10 = var9.add(new BigInteger(Long.toString(var1)));
                    var3.array.add(var10);
                    var9 = null;
                    var10 = null;
                }

                var3.type = 13;
                break;
            case 14:
                for(var8 = 0; var8 < this.length; ++var8) {
                    var3.array.add(((Complex)this.array.get(var8)).plus((double)var1));
                }

                var3.type = this.type;
                break;
            case 15:
                for(var8 = 0; var8 < this.length; ++var8) {
                    var3.array.add(((Phasor)this.array.get(var8)).plus(new Phasor((double)var1)));
                }

                var3.type = this.type;
                break;
            case 16:
                throw new IllegalArgumentException("a long cannot be added to a char");
            case 17:
                throw new IllegalArgumentException("a long cannot be added to a Character");
            case 18:
                for(var8 = 0; var8 < this.length; ++var8) {
                    var3.array.add((String)this.array.get(var8) + Long.toString(var1));
                }

                var3.type = this.type;
                break;
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        int[] var11 = new int[2];
        findMinMax(var3.getArray_as_Object(), var3.minmax, var11, var3.typeName, var3.type);
        var3.maxIndex = var11[0];
        var3.minIndex = var11[1];
        Conv.restoreMessages();
        return var3;
    }

    public ArrayMaths plus(Long var1) {
        long var2 = var1;
        return this.plus(var2);
    }

    public ArrayMaths plus(long[] var1) {
        int var2 = var1.length;
        if (this.length != var2) {
            throw new IllegalArgumentException("The argument array [length = " + var2 + "], must be of the same length as this instance array [length = " + this.length + "]");
        } else {
            if (this.suppressMessages) {
                Conv.suppressMessages();
            }

            ArrayMaths var3 = new ArrayMaths();
            var3.array = new ArrayList();
            var3.length = this.length;
            int var11;
            BigInteger var12;
            switch(this.type) {
                case 0:
                case 1:
                case 2:
                case 3:
                    double[] var4 = this.getArray_as_double();

                    for(int var15 = 0; var15 < this.length; ++var15) {
                        var3.array.add(new Double(var4[var15] + (double)var1[var15]));
                    }

                    var3.type = 0;
                    break;
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                    long var5 = this.getMaximum_as_long();
                    ArrayMaths var7 = new ArrayMaths(var1);
                    long var8 = var7.getMaximum_as_long();
                    long[] var10 = this.getArray_as_long();
                    if (9223372036854775807L - var5 >= var8) {
                        for(var11 = 0; var11 < this.length; ++var11) {
                            var3.array.add(new Long(var10[var11] + var1[var11]));
                        }

                        var3.type = 4;
                    } else {
                        for(var11 = 0; var11 < this.length; ++var11) {
                            var3.array.add(new Double((double)var10[var11] + (double)var1[var11]));
                        }

                        var3.type = 0;
                    }
                    break;
                case 12:
                    for(var11 = 0; var11 < this.length; ++var11) {
                        BigDecimal var16 = (BigDecimal)((BigDecimal)this.array.get(var11));
                        var16 = var16.add(new BigDecimal((double)var1[var11]));
                        var3.array.add(var16);
                        var12 = null;
                    }

                    var3.type = 12;
                    break;
                case 13:
                    for(var11 = 0; var11 < this.length; ++var11) {
                        var12 = (BigInteger)((BigInteger)this.array.get(var11));
                        BigInteger var13 = var12.add(new BigInteger(Long.toString(var1[var11])));
                        var3.array.add(var13);
                        var12 = null;
                        var13 = null;
                    }

                    var3.type = 13;
                    break;
                case 14:
                    for(var11 = 0; var11 < this.length; ++var11) {
                        var3.array.add(((Complex)this.array.get(var11)).plus((double)var1[var11]));
                    }

                    var3.type = this.type;
                    break;
                case 15:
                    for(var11 = 0; var11 < this.length; ++var11) {
                        var3.array.add(((Phasor)this.array.get(var11)).plus(new Phasor((double)var1[var11])));
                    }

                    var3.type = this.type;
                    break;
                case 16:
                    throw new IllegalArgumentException("a long cannot be added to a char");
                case 17:
                    throw new IllegalArgumentException("a long cannot be added to a Character");
                case 18:
                    for(var11 = 0; var11 < this.length; ++var11) {
                        var3.array.add((String)this.array.get(var11) + Long.toString(var1[var11]));
                    }

                    var3.type = this.type;
                    break;
                default:
                    throw new IllegalArgumentException("Data type not identified by this method");
            }

            int[] var14 = new int[2];
            findMinMax(var3.getArray_as_Object(), var3.minmax, var14, var3.typeName, var3.type);
            var3.maxIndex = var14[0];
            var3.minIndex = var14[1];
            Conv.restoreMessages();
            return var3;
        }
    }

    public ArrayMaths plus(Long[] var1) {
        int var2 = var1.length;
        if (this.length != var2) {
            throw new IllegalArgumentException("The argument array [length = " + var2 + "], must be of the same length as this instance array [length = " + this.length + "]");
        } else {
            long[] var3 = new long[this.length];

            for(int var4 = 0; var4 < this.length; ++var4) {
                var3[var4] = var1[var4];
            }

            return this.plus(var3);
        }
    }

    public ArrayMaths plus(int var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var2 = new ArrayMaths();
        var2.array = new ArrayList();
        var2.length = this.length;
        int var7;
        int var9;
        BigInteger var10;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
                double[] var3 = this.getArray_as_double();

                for(int var13 = 0; var13 < this.length; ++var13) {
                    var2.array.add(new Double(var3[var13] + (double)var1));
                }

                var2.type = 0;
                break;
            case 4:
                long var4 = this.getMaximum_as_long();
                long[] var6 = this.getArray_as_long();
                if (9223372036854775807L - var4 >= (long)var1) {
                    for(var7 = 0; var7 < this.length; ++var7) {
                        var2.array.add(new Long(var6[var7] + (long)var1));
                    }

                    var2.type = 4;
                } else {
                    for(var7 = 0; var7 < this.length; ++var7) {
                        var2.array.add(new Double((double)var6[var7] + (double)var1));
                    }

                    var2.type = 0;
                }
                break;
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                var7 = this.getMaximum_as_int();
                int[] var8 = this.getArray_as_int();
                if (2147483647 - var7 >= var1) {
                    for(var9 = 0; var9 < this.length; ++var9) {
                        var2.array.add(new Integer(var8[var9] + var1));
                    }

                    var2.type = 6;
                } else {
                    for(var9 = 0; var9 < this.length; ++var9) {
                        var2.array.add(new Double((double)var8[var9] + (double)var1));
                    }

                    var2.type = 0;
                }
                break;
            case 12:
                for(var9 = 0; var9 < this.length; ++var9) {
                    BigDecimal var14 = (BigDecimal)((BigDecimal)this.array.get(var9));
                    var14 = var14.add(new BigDecimal((double)var1));
                    var2.array.add(var14);
                    var10 = null;
                }

                var2.type = 12;
                break;
            case 13:
                for(var9 = 0; var9 < this.length; ++var9) {
                    var10 = (BigInteger)((BigInteger)this.array.get(var9));
                    BigInteger var11 = var10.add(new BigInteger(Integer.toString(var1)));
                    var2.array.add(var11);
                    var10 = null;
                    var11 = null;
                }

                var2.type = 13;
                break;
            case 14:
                for(var9 = 0; var9 < this.length; ++var9) {
                    var2.array.add(((Complex)this.array.get(var9)).plus((double)var1));
                }

                var2.type = this.type;
                break;
            case 15:
                for(var9 = 0; var9 < this.length; ++var9) {
                    var2.array.add(((Phasor)this.array.get(var9)).plus(new Phasor((double)var1)));
                }

                var2.type = this.type;
                break;
            case 16:
                throw new IllegalArgumentException("an int cannot be added to a char");
            case 17:
                throw new IllegalArgumentException("an int cannot be added to a Character");
            case 18:
                for(var9 = 0; var9 < this.length; ++var9) {
                    var2.array.add((String)this.array.get(var9) + Integer.toString(var1));
                }

                var2.type = this.type;
                break;
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        int[] var12 = new int[2];
        findMinMax(var2.getArray_as_Object(), var2.minmax, var12, var2.typeName, var2.type);
        var2.maxIndex = var12[0];
        var2.minIndex = var12[1];
        Conv.restoreMessages();
        return var2;
    }

    public ArrayMaths plus(Integer var1) {
        int var2 = var1;
        return this.plus(var2);
    }

    public ArrayMaths plus(int[] var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var2 = new ArrayMaths();
        var2.array = new ArrayList();
        var2.length = this.length;
        int var10;
        int var14;
        BigInteger var15;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
                double[] var3 = this.getArray_as_double();

                for(int var18 = 0; var18 < this.length; ++var18) {
                    var2.array.add(new Double(var3[var18] + (double)var1[var18]));
                }

                var2.type = 0;
                break;
            case 4:
                long var4 = this.getMaximum_as_long();
                ArrayMaths var6 = new ArrayMaths(var1);
                long var7 = var6.getMaximum_as_long();
                long[] var9 = this.getArray_as_long();
                if (9223372036854775807L - var4 >= var7) {
                    for(var10 = 0; var10 < this.length; ++var10) {
                        var2.array.add(new Long(var9[var10] + (long)var1[var10]));
                    }

                    var2.type = 4;
                } else {
                    for(var10 = 0; var10 < this.length; ++var10) {
                        var2.array.add(new Double((double)var9[var10] + (double)var1[var10]));
                    }

                    var2.type = 0;
                }
                break;
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                var10 = this.getMaximum_as_int();
                ArrayMaths var11 = new ArrayMaths(var1);
                int var12 = var11.getMaximum_as_int();
                int[] var13 = this.getArray_as_int();
                if (2147483647 - var10 >= var12) {
                    for(var14 = 0; var14 < this.length; ++var14) {
                        var2.array.add(new Integer(var13[var14] + var1[var14]));
                    }

                    var2.type = 6;
                } else {
                    for(var14 = 0; var14 < this.length; ++var14) {
                        var2.array.add(new Double((double)var13[var14] + (double)var1[var14]));
                    }

                    var2.type = 0;
                }
                break;
            case 12:
                for(var14 = 0; var14 < this.length; ++var14) {
                    BigDecimal var19 = (BigDecimal)((BigDecimal)this.array.get(var14));
                    var19 = var19.add(new BigDecimal((double)var1[var14]));
                    var2.array.add(var19);
                    var15 = null;
                }

                var2.type = 12;
                break;
            case 13:
                for(var14 = 0; var14 < this.length; ++var14) {
                    var15 = (BigInteger)((BigInteger)this.array.get(var14));
                    BigInteger var16 = var15.add(new BigInteger(Integer.toString(var1[var14])));
                    var2.array.add(var16);
                    var15 = null;
                    var16 = null;
                }

                var2.type = 13;
                break;
            case 14:
                for(var14 = 0; var14 < this.length; ++var14) {
                    var2.array.add(((Complex)this.array.get(var14)).plus((double)var1[var14]));
                }

                var2.type = this.type;
                break;
            case 15:
                for(var14 = 0; var14 < this.length; ++var14) {
                    var2.array.add(((Phasor)this.array.get(var14)).plus(new Phasor((double)var1[var14])));
                }

                var2.type = this.type;
                break;
            case 16:
                throw new IllegalArgumentException("an int cannot be added to a char");
            case 17:
                throw new IllegalArgumentException("an int cannot be added to a Character");
            case 18:
                for(var14 = 0; var14 < this.length; ++var14) {
                    var2.array.add((String)this.array.get(var14) + Integer.toString(var1[var14]));
                }

                var2.type = this.type;
                break;
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        int[] var17 = new int[2];
        findMinMax(var2.getArray_as_Object(), var2.minmax, var17, var2.typeName, var2.type);
        var2.maxIndex = var17[0];
        var2.minIndex = var17[1];
        Conv.restoreMessages();
        return var2;
    }

    public ArrayMaths plus(Integer[] var1) {
        int var2 = var1.length;
        if (this.length != var2) {
            throw new IllegalArgumentException("The argument array [length = " + var2 + "], must be of the same length as this instance array [length = " + this.length + "]");
        } else {
            int[] var3 = new int[this.length];

            for(int var4 = 0; var4 < this.length; ++var4) {
                var3[var4] = var1[var4];
            }

            return this.plus(var3);
        }
    }

    public ArrayMaths plus(short var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var2 = new ArrayMaths();
        var2.array = new ArrayList();
        var2.length = this.length;
        int var9;
        BigInteger var10;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
                double[] var3 = this.getArray_as_double();

                for(int var13 = 0; var13 < this.length; ++var13) {
                    var2.array.add(new Double(var3[var13] + (double)var1));
                }

                var2.type = 0;
                break;
            case 4:
                long var4 = this.getMaximum_as_long();
                long[] var6 = this.getArray_as_long();
                int var14;
                if (9223372036854775807L - var4 >= (long)var1) {
                    for(var14 = 0; var14 < this.length; ++var14) {
                        var2.array.add(new Long(var6[var14] + (long)var1));
                    }

                    var2.type = 4;
                } else {
                    for(var14 = 0; var14 < this.length; ++var14) {
                        var2.array.add(new Double((double)var6[var14] + (double)var1));
                    }

                    var2.type = 0;
                }
                break;
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                short var7 = this.getMaximum_as_short();
                short[] var8 = this.getArray_as_short();
                if (2147483647 - var7 >= var1) {
                    for(var9 = 0; var9 < this.length; ++var9) {
                        var2.array.add(new Integer(var8[var9] + var1));
                    }

                    var2.type = 6;
                } else {
                    for(var9 = 0; var9 < this.length; ++var9) {
                        var2.array.add(new Double((double)var8[var9] + (double)var1));
                    }

                    var2.type = 0;
                }
                break;
            case 12:
                for(var9 = 0; var9 < this.length; ++var9) {
                    BigDecimal var15 = (BigDecimal)((BigDecimal)this.array.get(var9));
                    var15 = var15.add(new BigDecimal((double)var1));
                    var2.array.add(var15);
                    var10 = null;
                }

                var2.type = 12;
                break;
            case 13:
                for(var9 = 0; var9 < this.length; ++var9) {
                    var10 = (BigInteger)((BigInteger)this.array.get(var9));
                    BigInteger var11 = var10.add(new BigInteger(Integer.toString(var1)));
                    var2.array.add(var11);
                    var10 = null;
                    var11 = null;
                }

                var2.type = 13;
                break;
            case 14:
                for(var9 = 0; var9 < this.length; ++var9) {
                    var2.array.add(((Complex)this.array.get(var9)).plus((double)var1));
                }

                var2.type = this.type;
                break;
            case 15:
                for(var9 = 0; var9 < this.length; ++var9) {
                    var2.array.add(((Phasor)this.array.get(var9)).plus(new Phasor((double)var1)));
                }

                var2.type = this.type;
                break;
            case 16:
                throw new IllegalArgumentException("a short cannot be added to a char");
            case 17:
                throw new IllegalArgumentException("a short cannot be added to a Character");
            case 18:
                for(var9 = 0; var9 < this.length; ++var9) {
                    var2.array.add((String)this.array.get(var9) + Integer.toString(var1));
                }

                var2.type = this.type;
                break;
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        int[] var12 = new int[2];
        findMinMax(var2.getArray_as_Object(), var2.minmax, var12, var2.typeName, var2.type);
        var2.maxIndex = var12[0];
        var2.minIndex = var12[1];
        Conv.restoreMessages();
        return var2;
    }

    public ArrayMaths plus(Short var1) {
        short var2 = var1;
        return this.plus(var2);
    }

    public ArrayMaths plus(short[] var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var2 = new ArrayMaths();
        var2.array = new ArrayList();
        var2.length = this.length;
        int var14;
        BigInteger var15;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
                double[] var3 = this.getArray_as_double();

                for(int var18 = 0; var18 < this.length; ++var18) {
                    var2.array.add(new Double(var3[var18] + (double)var1[var18]));
                }

                var2.type = 0;
                break;
            case 4:
                long var4 = this.getMaximum_as_long();
                ArrayMaths var6 = new ArrayMaths(var1);
                long var7 = var6.getMaximum_as_long();
                long[] var9 = this.getArray_as_long();
                int var19;
                if (9223372036854775807L - var4 >= var7) {
                    for(var19 = 0; var19 < this.length; ++var19) {
                        var2.array.add(new Long(var9[var19] + (long)var1[var19]));
                    }

                    var2.type = 4;
                } else {
                    for(var19 = 0; var19 < this.length; ++var19) {
                        var2.array.add(new Double((double)var9[var19] + (double)var1[var19]));
                    }

                    var2.type = 0;
                }
                break;
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                short var10 = this.getMaximum_as_short();
                ArrayMaths var11 = new ArrayMaths(var1);
                short var12 = var11.getMaximum_as_short();
                short[] var13 = this.getArray_as_short();
                if (2147483647 - var10 >= var12) {
                    for(var14 = 0; var14 < this.length; ++var14) {
                        var2.array.add(new Integer(var13[var14] + var1[var14]));
                    }

                    var2.type = 6;
                } else {
                    for(var14 = 0; var14 < this.length; ++var14) {
                        var2.array.add(new Double((double)var13[var14] + (double)var1[var14]));
                    }

                    var2.type = 0;
                }
                break;
            case 12:
                for(var14 = 0; var14 < this.length; ++var14) {
                    BigDecimal var20 = (BigDecimal)((BigDecimal)this.array.get(var14));
                    var20 = var20.add(new BigDecimal((double)var1[var14]));
                    var2.array.add(var20);
                    var15 = null;
                }

                var2.type = 12;
                break;
            case 13:
                for(var14 = 0; var14 < this.length; ++var14) {
                    var15 = (BigInteger)((BigInteger)this.array.get(var14));
                    BigInteger var16 = var15.add(new BigInteger(Integer.toString(var1[var14])));
                    var2.array.add(var16);
                    var15 = null;
                    var16 = null;
                }

                var2.type = 13;
                break;
            case 14:
                for(var14 = 0; var14 < this.length; ++var14) {
                    var2.array.add(((Complex)this.array.get(var14)).plus((double)var1[var14]));
                }

                var2.type = this.type;
                break;
            case 15:
                for(var14 = 0; var14 < this.length; ++var14) {
                    var2.array.add(((Phasor)this.array.get(var14)).plus(new Phasor((double)var1[var14])));
                }

                var2.type = this.type;
                break;
            case 16:
                throw new IllegalArgumentException("a short cannot be added to a char");
            case 17:
                throw new IllegalArgumentException("a short cannot be added to a Character");
            case 18:
                for(var14 = 0; var14 < this.length; ++var14) {
                    var2.array.add((String)this.array.get(var14) + Integer.toString(var1[var14]));
                }

                var2.type = this.type;
                break;
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        int[] var17 = new int[2];
        findMinMax(var2.getArray_as_Object(), var2.minmax, var17, var2.typeName, var2.type);
        var2.maxIndex = var17[0];
        var2.minIndex = var17[1];
        Conv.restoreMessages();
        return var2;
    }

    public ArrayMaths plus(Short[] var1) {
        int var2 = var1.length;
        if (this.length != var2) {
            throw new IllegalArgumentException("The argument array [length = " + var2 + "], must be of the same length as this instance array [length = " + this.length + "]");
        } else {
            short[] var3 = new short[this.length];

            for(int var4 = 0; var4 < this.length; ++var4) {
                var3[var4] = var1[var4];
            }

            return this.plus(var3);
        }
    }

    public ArrayMaths plus(BigDecimal var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var2 = new ArrayMaths();
        var2.array = new ArrayList();
        var2.length = this.length;
        int var6;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
                BigDecimal[] var3 = this.getArray_as_BigDecimal();

                for(int var8 = 0; var8 < this.length; ++var8) {
                    var2.array.add(var3[var8].add(var1));
                }

                var2.type = 12;
                break;
            case 14:
                Complex[] var4 = this.getArray_as_Complex();

                for(int var9 = 0; var9 < this.length; ++var9) {
                    var2.array.add(var4[var9].plus(var1.doubleValue()));
                }

                var2.type = this.type;
                break;
            case 15:
                Phasor[] var5 = this.getArray_as_Phasor();

                for(var6 = 0; var6 < this.length; ++var6) {
                    var2.array.add(var5[var6].plus(new Phasor(var1.doubleValue())));
                }

                var2.type = this.type;
                break;
            case 16:
                throw new IllegalArgumentException("a BigDecimal cannot be added to a char");
            case 17:
                throw new IllegalArgumentException("a BigDecimal cannot be added to a Character");
            case 18:
                for(var6 = 0; var6 < this.length; ++var6) {
                    var2.array.add((String)this.array.get(var6) + var1.toString());
                }

                var2.type = this.type;
                break;
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        int[] var7 = new int[2];
        findMinMax(var2.getArray_as_Object(), var2.minmax, var7, var2.typeName, var2.type);
        var2.maxIndex = var7[0];
        var2.minIndex = var7[1];
        Conv.restoreMessages();
        return var2;
    }

    public ArrayMaths plus(byte var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var2 = new ArrayMaths();
        var2.array = new ArrayList();
        var2.length = this.length;
        int var9;
        BigInteger var10;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
                double[] var3 = this.getArray_as_double();

                for(int var13 = 0; var13 < this.length; ++var13) {
                    var2.array.add(new Double(var3[var13] + (double)var1));
                }

                var2.type = 0;
                break;
            case 4:
                long var4 = this.getMaximum_as_long();
                long[] var6 = this.getArray_as_long();
                int var14;
                if (9223372036854775807L - var4 >= (long)var1) {
                    for(var14 = 0; var14 < this.length; ++var14) {
                        var2.array.add(new Long(var6[var14] + (long)var1));
                    }

                    var2.type = 4;
                } else {
                    for(var14 = 0; var14 < this.length; ++var14) {
                        var2.array.add(new Double((double)var6[var14] + (double)var1));
                    }

                    var2.type = 0;
                }
                break;
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                byte var7 = this.getMaximum_as_byte();
                byte[] var8 = this.getArray_as_byte();
                if (2147483647 - var7 >= var1) {
                    for(var9 = 0; var9 < this.length; ++var9) {
                        var2.array.add(new Integer(var8[var9] + var1));
                    }

                    var2.type = 6;
                } else {
                    for(var9 = 0; var9 < this.length; ++var9) {
                        var2.array.add(new Double((double)var8[var9] + (double)var1));
                    }

                    var2.type = 0;
                }
                break;
            case 12:
                for(var9 = 0; var9 < this.length; ++var9) {
                    BigDecimal var15 = (BigDecimal)((BigDecimal)this.array.get(var9));
                    var15 = var15.add(new BigDecimal((double)var1));
                    var2.array.add(var15);
                    var10 = null;
                }

                var2.type = 12;
                break;
            case 13:
                for(var9 = 0; var9 < this.length; ++var9) {
                    var10 = (BigInteger)((BigInteger)this.array.get(var9));
                    BigInteger var11 = var10.add(new BigInteger(Integer.toString(var1)));
                    var2.array.add(var11);
                    var10 = null;
                    var11 = null;
                }

                var2.type = 13;
                break;
            case 14:
                for(var9 = 0; var9 < this.length; ++var9) {
                    var2.array.add(((Complex)this.array.get(var9)).plus((double)var1));
                }

                var2.type = this.type;
                break;
            case 15:
                for(var9 = 0; var9 < this.length; ++var9) {
                    var2.array.add(((Phasor)this.array.get(var9)).plus(new Phasor((double)var1)));
                }

                var2.type = this.type;
                break;
            case 16:
                throw new IllegalArgumentException("a byte cannot be added to a char");
            case 17:
                throw new IllegalArgumentException("a byte cannot be added to a Character");
            case 18:
                for(var9 = 0; var9 < this.length; ++var9) {
                    var2.array.add((String)this.array.get(var9) + Integer.toString(var1));
                }

                var2.type = this.type;
                break;
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        int[] var12 = new int[2];
        findMinMax(var2.getArray_as_Object(), var2.minmax, var12, var2.typeName, var2.type);
        var2.maxIndex = var12[0];
        var2.minIndex = var12[1];
        Conv.restoreMessages();
        return var2;
    }

    public ArrayMaths plus(Byte var1) {
        byte var2 = var1;
        return this.plus(var2);
    }

    public ArrayMaths plus(byte[] var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var2 = new ArrayMaths();
        var2.array = new ArrayList();
        var2.length = this.length;
        int var14;
        BigInteger var15;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
                double[] var3 = this.getArray_as_double();

                for(int var18 = 0; var18 < this.length; ++var18) {
                    var2.array.add(new Double(var3[var18] + (double)var1[var18]));
                }

                var2.type = 0;
                break;
            case 4:
                long var4 = this.getMaximum_as_long();
                ArrayMaths var6 = new ArrayMaths(var1);
                long var7 = var6.getMaximum_as_long();
                long[] var9 = this.getArray_as_long();
                int var19;
                if (9223372036854775807L - var4 >= var7) {
                    for(var19 = 0; var19 < this.length; ++var19) {
                        var2.array.add(new Long(var9[var19] + (long)var1[var19]));
                    }

                    var2.type = 4;
                } else {
                    for(var19 = 0; var19 < this.length; ++var19) {
                        var2.array.add(new Double((double)var9[var19] + (double)var1[var19]));
                    }

                    var2.type = 0;
                }
                break;
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                byte var10 = this.getMaximum_as_byte();
                ArrayMaths var11 = new ArrayMaths(var1);
                byte var12 = var11.getMaximum_as_byte();
                byte[] var13 = this.getArray_as_byte();
                if (2147483647 - var10 >= var12) {
                    for(var14 = 0; var14 < this.length; ++var14) {
                        var2.array.add(new Integer(var13[var14] + var1[var14]));
                    }

                    var2.type = 6;
                } else {
                    for(var14 = 0; var14 < this.length; ++var14) {
                        var2.array.add(new Double((double)var13[var14] + (double)var1[var14]));
                    }

                    var2.type = 0;
                }
                break;
            case 12:
                for(var14 = 0; var14 < this.length; ++var14) {
                    BigDecimal var20 = (BigDecimal)((BigDecimal)this.array.get(var14));
                    var20 = var20.add(new BigDecimal((double)var1[var14]));
                    var2.array.add(var20);
                    var15 = null;
                }

                var2.type = 12;
                break;
            case 13:
                for(var14 = 0; var14 < this.length; ++var14) {
                    var15 = (BigInteger)((BigInteger)this.array.get(var14));
                    BigInteger var16 = var15.add(new BigInteger(Integer.toString(var1[var14])));
                    var2.array.add(var16);
                    var15 = null;
                    var16 = null;
                }

                var2.type = 13;
                break;
            case 14:
                for(var14 = 0; var14 < this.length; ++var14) {
                    var2.array.add(((Complex)this.array.get(var14)).plus((double)var1[var14]));
                }

                var2.type = this.type;
                break;
            case 15:
                for(var14 = 0; var14 < this.length; ++var14) {
                    var2.array.add(((Phasor)this.array.get(var14)).plus(new Phasor((double)var1[var14])));
                }

                var2.type = this.type;
                break;
            case 16:
                throw new IllegalArgumentException("a byte cannot be added to a char");
            case 17:
                throw new IllegalArgumentException("a byte cannot be added to a Character");
            case 18:
                for(var14 = 0; var14 < this.length; ++var14) {
                    var2.array.add((String)this.array.get(var14) + Integer.toString(var1[var14]));
                }

                var2.type = this.type;
                break;
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        int[] var17 = new int[2];
        findMinMax(var2.getArray_as_Object(), var2.minmax, var17, var2.typeName, var2.type);
        var2.maxIndex = var17[0];
        var2.minIndex = var17[1];
        Conv.restoreMessages();
        return var2;
    }

    public ArrayMaths plus(Byte[] var1) {
        int var2 = var1.length;
        if (this.length != var2) {
            throw new IllegalArgumentException("The argument array [length = " + var2 + "], must be of the same length as this instance array [length = " + this.length + "]");
        } else {
            byte[] var3 = new byte[this.length];

            for(int var4 = 0; var4 < this.length; ++var4) {
                var3[var4] = var1[var4];
            }

            return this.plus(var3);
        }
    }

    public ArrayMaths plus(BigDecimal[] var1) {
        int var2 = var1.length;
        if (this.length != var2) {
            throw new IllegalArgumentException("The argument array [length = " + var2 + "], must be of the same length as this instance array [length = " + this.length + "]");
        } else {
            if (this.suppressMessages) {
                Conv.suppressMessages();
            }

            ArrayMaths var3 = new ArrayMaths();
            var3.array = new ArrayList();
            var3.length = this.length;
            int var7;
            switch(this.type) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                    BigDecimal[] var4 = this.getArray_as_BigDecimal();

                    for(int var9 = 0; var9 < this.length; ++var9) {
                        var3.array.add(var4[var9].add(var1[var9]));
                    }

                    Conv.restoreMessages();
                    var3.type = 12;
                    break;
                case 14:
                    Complex[] var5 = this.getArray_as_Complex();

                    for(int var10 = 0; var10 < this.length; ++var10) {
                        var3.array.add(var5[var10].plus(var1[var10].doubleValue()));
                    }

                    var3.type = this.type;
                    break;
                case 15:
                    Phasor[] var6 = this.getArray_as_Phasor();

                    for(var7 = 0; var7 < this.length; ++var7) {
                        var3.array.add(var6[var7].plus(new Phasor(var1[var7].doubleValue())));
                    }

                    var3.type = this.type;
                    break;
                case 16:
                    throw new IllegalArgumentException("a BigDecimal cannot be added to a char");
                case 17:
                    throw new IllegalArgumentException("a BigDecimal cannot be added to a Character");
                case 18:
                    for(var7 = 0; var7 < this.length; ++var7) {
                        var3.array.add((String)this.array.get(var7) + var1[var7].toString());
                    }

                    var3.type = this.type;
                    break;
                default:
                    throw new IllegalArgumentException("Data type not identified by this method");
            }

            int[] var8 = new int[2];
            findMinMax(var3.getArray_as_Object(), var3.minmax, var8, var3.typeName, var3.type);
            var3.maxIndex = var8[0];
            var3.minIndex = var8[1];
            Conv.restoreMessages();
            return var3;
        }
    }

    public ArrayMaths plus(BigInteger var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var2 = new ArrayMaths();
        var2.array = new ArrayList();
        var2.length = this.length;
        int var8;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 12:
                BigDecimal var3 = Conv.convert_BigInteger_to_BigDecimal(var1);
                BigDecimal[] var4 = this.getArray_as_BigDecimal();

                for(int var10 = 0; var10 < this.length; ++var10) {
                    var2.array.add(var4[var10].add(var3));
                }

                var2.type = 12;
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 13:
                BigInteger[] var5 = this.getArray_as_BigInteger();

                for(int var11 = 0; var11 < this.length; ++var11) {
                    var2.array.add(var5[var11].add(var1));
                }

                var2.type = 13;
                break;
            case 14:
                Complex[] var6 = this.getArray_as_Complex();

                for(int var12 = 0; var12 < this.length; ++var12) {
                    var2.array.add(var6[var12].plus(Conv.convert_BigInteger_to_double(var1)));
                }

                var2.type = this.type;
                break;
            case 15:
                Phasor[] var7 = this.getArray_as_Phasor();

                for(var8 = 0; var8 < this.length; ++var8) {
                    var2.array.add(var7[var8].plus(new Phasor(Conv.convert_BigInteger_to_double(var1))));
                }

                var2.type = this.type;
                break;
            case 16:
                throw new IllegalArgumentException("a BigInteger cannot be added to a char");
            case 17:
                throw new IllegalArgumentException("a BigInteger cannot be added to a Character");
            case 18:
                for(var8 = 0; var8 < this.length; ++var8) {
                    var2.array.add((String)this.array.get(var8) + var1.toString());
                }

                var2.type = this.type;
                break;
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        int[] var9 = new int[2];
        findMinMax(var2.getArray_as_Object(), var2.minmax, var9, var2.typeName, var2.type);
        var2.maxIndex = var9[0];
        var2.minIndex = var9[1];
        Conv.restoreMessages();
        return var2;
    }

    public ArrayMaths plus(BigInteger[] var1) {
        int var2 = var1.length;
        if (this.length != var2) {
            throw new IllegalArgumentException("The argument array [length = " + var2 + "], must be of the same length as this instance array [length = " + this.length + "]");
        } else {
            if (this.suppressMessages) {
                Conv.suppressMessages();
            }

            ArrayMaths var3 = new ArrayMaths();
            var3.array = new ArrayList();
            var3.length = this.length;
            int var8;
            switch(this.type) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 12:
                    BigDecimal[] var4 = this.getArray_as_BigDecimal();

                    for(int var10 = 0; var10 < this.length; ++var10) {
                        var3.array.add(var4[var10].add(Conv.convert_BigInteger_to_BigDecimal(var1[var10])));
                    }

                    var3.type = 12;
                    break;
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 13:
                    BigInteger[] var5 = this.getArray_as_BigInteger();

                    for(int var11 = 0; var11 < this.length; ++var11) {
                        var3.array.add(var5[var11].add(var1[var11]));
                    }

                    var3.type = 13;
                    break;
                case 14:
                    Complex[] var6 = this.getArray_as_Complex();

                    for(int var12 = 0; var12 < this.length; ++var12) {
                        var3.array.add(var6[var12].plus(Conv.convert_BigInteger_to_double(var1[var12])));
                    }

                    var3.type = this.type;
                    break;
                case 15:
                    Phasor[] var7 = this.getArray_as_Phasor();

                    for(var8 = 0; var8 < this.length; ++var8) {
                        var3.array.add(var7[var8].plus(new Phasor(Conv.convert_BigInteger_to_double(var1[var8]))));
                    }

                    var3.type = this.type;
                    break;
                case 16:
                    throw new IllegalArgumentException("a BigInteger cannot be added to a char");
                case 17:
                    throw new IllegalArgumentException("a BigInteger cannot be added to a Character");
                case 18:
                    for(var8 = 0; var8 < this.length; ++var8) {
                        var3.array.add((String)this.array.get(var8) + var1[var8].toString());
                    }

                    var3.type = this.type;
                    break;
                default:
                    throw new IllegalArgumentException("Data type not identified by this method");
            }

            int[] var9 = new int[2];
            findMinMax(var3.getArray_as_Object(), var3.minmax, var9, var3.typeName, var3.type);
            var3.maxIndex = var9[0];
            var3.minIndex = var9[1];
            Conv.restoreMessages();
            return var3;
        }
    }

    public ArrayMaths plus(Complex var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var2 = new ArrayMaths();
        var2.array = new ArrayList();
        var2.length = this.length;
        int var5;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
                Complex[] var3 = this.getArray_as_Complex();

                for(int var6 = 0; var6 < this.length; ++var6) {
                    var2.array.add(var3[var6].plus(var1));
                }

                var2.type = 14;
                break;
            case 15:
                Phasor[] var4 = this.getArray_as_Phasor();

                for(var5 = 0; var5 < this.length; ++var5) {
                    var2.array.add(var4[var5].plus(Conv.convert_Complex_to_Phasor(var1)));
                }

                var2.type = this.type;
                break;
            case 16:
                throw new IllegalArgumentException("a Complex cannot be added to a char");
            case 17:
                throw new IllegalArgumentException("a Complex cannot be added to a Character");
            case 18:
                for(var5 = 0; var5 < this.length; ++var5) {
                    var2.array.add((String)this.array.get(var5) + var1.toString());
                }

                var2.type = this.type;
                break;
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var2;
    }

    public ArrayMaths plus(Complex[] var1) {
        int var2 = var1.length;
        if (this.length != var2) {
            throw new IllegalArgumentException("The argument array [length = " + var2 + "], must be of the same length as this instance array [length = " + this.length + "]");
        } else {
            if (this.suppressMessages) {
                Conv.suppressMessages();
            }

            ArrayMaths var3 = new ArrayMaths();
            var3.array = new ArrayList();
            var3.length = this.length;
            int var6;
            switch(this.type) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                    Complex[] var4 = this.getArray_as_Complex();

                    for(int var7 = 0; var7 < this.length; ++var7) {
                        var3.array.add(var4[var7].plus(var1[var7]));
                    }

                    var3.type = 14;
                    break;
                case 15:
                    Phasor[] var5 = this.getArray_as_Phasor();

                    for(var6 = 0; var6 < this.length; ++var6) {
                        var3.array.add(var5[var6].plus(Conv.convert_Complex_to_Phasor(var1[var6])));
                    }

                    var3.type = this.type;
                    break;
                case 16:
                    throw new IllegalArgumentException("a Complex cannot be added to a char");
                case 17:
                    throw new IllegalArgumentException("a Complex cannot be added to a Character");
                case 18:
                    for(var6 = 0; var6 < this.length; ++var6) {
                        var3.array.add((String)this.array.get(var6) + var1[var6].toString());
                    }

                    var3.type = this.type;
                    break;
                default:
                    throw new IllegalArgumentException("Data type not identified by this method");
            }

            Conv.restoreMessages();
            return var3;
        }
    }

    public ArrayMaths plus(Phasor var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var2 = new ArrayMaths();
        var2.array = new ArrayList();
        var2.length = this.length;
        int var5;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 15:
                Phasor[] var3 = this.getArray_as_Phasor();

                for(int var6 = 0; var6 < this.length; ++var6) {
                    var2.array.add(var3[var6].plus(var1));
                }

                var2.type = 15;
                break;
            case 14:
                Complex[] var4 = this.getArray_as_Complex();

                for(var5 = 0; var5 < this.length; ++var5) {
                    var2.array.add(var4[var5].plus(Conv.convert_Phasor_to_Complex(var1)));
                }

                var2.type = this.type;
                break;
            case 16:
                throw new IllegalArgumentException("a Phasor cannot be added to a char");
            case 17:
                throw new IllegalArgumentException("a Phasor cannot be added to a Character");
            case 18:
                for(var5 = 0; var5 < this.length; ++var5) {
                    var2.array.add((String)this.array.get(var5) + var1.toString());
                }

                var2.type = this.type;
                break;
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var2;
    }

    public ArrayMaths plus(Phasor[] var1) {
        int var2 = var1.length;
        if (this.length != var2) {
            throw new IllegalArgumentException("The argument array [length = " + var2 + "], must be of the same length as this instance array [length = " + this.length + "]");
        } else {
            if (this.suppressMessages) {
                Conv.suppressMessages();
            }

            ArrayMaths var3 = new ArrayMaths();
            var3.array = new ArrayList();
            var3.length = this.length;
            int var6;
            switch(this.type) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 15:
                    Phasor[] var4 = this.getArray_as_Phasor();

                    for(int var7 = 0; var7 < this.length; ++var7) {
                        var3.array.add(var4[var7].plus(var1[var7]));
                    }

                    var3.type = 15;
                    break;
                case 14:
                    Complex[] var5 = this.getArray_as_Complex();

                    for(var6 = 0; var6 < this.length; ++var6) {
                        var3.array.add(var5[var6].plus(Conv.convert_Phasor_to_Complex(var1[var6])));
                    }

                    var3.type = this.type;
                    break;
                case 16:
                    throw new IllegalArgumentException("a Phasor cannot be added to a char");
                case 17:
                    throw new IllegalArgumentException("a Phasor cannot be added to a Character");
                case 18:
                    for(var6 = 0; var6 < this.length; ++var6) {
                        var3.array.add((String)this.array.get(var6) + var1[var6].toString());
                    }

                    var3.type = this.type;
                    break;
                default:
                    throw new IllegalArgumentException("Data type not identified by this method");
            }

            Conv.restoreMessages();
            return var3;
        }
    }

    public ArrayMaths plus(String var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var2 = new ArrayMaths();
        var2.array = new ArrayList();
        var2.length = this.length;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
                String[] var3 = this.getArray_as_String();

                for(int var4 = 0; var4 < this.length; ++var4) {
                    var2.array.add(var3[var4] + var1);
                }

                var2.type = 18;
                Conv.restoreMessages();
                return var2;
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }
    }

    public ArrayMaths plus(String[] var1) {
        int var2 = var1.length;
        if (this.length != var2) {
            throw new IllegalArgumentException("The argument array [length = " + var2 + "], must be of the same length as this instance array [length = " + this.length + "]");
        } else {
            if (this.suppressMessages) {
                Conv.suppressMessages();
            }

            ArrayMaths var3 = new ArrayMaths();
            var3.array = new ArrayList();
            var3.length = this.length;
            switch(this.type) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                case 15:
                case 16:
                case 17:
                case 18:
                    String[] var4 = this.getArray_as_String();

                    for(int var5 = 0; var5 < this.length; ++var5) {
                        var3.array.add(var4[var5] + var1[var5]);
                    }

                    var3.type = 18;
                    Conv.restoreMessages();
                    return var3;
                default:
                    throw new IllegalArgumentException("Data type not identified by this method");
            }
        }
    }

    public ArrayMaths plus(char var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var2 = new ArrayMaths();
        var2.array = new ArrayList();
        var2.length = this.length;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
                String[] var3 = this.getArray_as_String();

                for(int var4 = 0; var4 < this.length; ++var4) {
                    var2.array.add(var3[var4] + var1);
                }

                var2.type = 18;
                int[] var5 = new int[2];
                findMinMax(var2.getArray_as_Object(), var2.minmax, var5, var2.typeName, var2.type);
                var2.maxIndex = var5[0];
                var2.minIndex = var5[1];
                Conv.restoreMessages();
                return var2;
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }
    }

    public ArrayMaths plus(char[] var1) {
        int var2 = var1.length;
        if (this.length != var2) {
            throw new IllegalArgumentException("The argument array [length = " + var2 + "], must be of the same length as this instance array [length = " + this.length + "]");
        } else {
            if (this.suppressMessages) {
                Conv.suppressMessages();
            }

            ArrayMaths var3 = new ArrayMaths();
            var3.array = new ArrayList();
            var3.length = this.length;
            switch(this.type) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                case 15:
                case 16:
                case 17:
                case 18:
                    String[] var4 = this.getArray_as_String();

                    for(int var5 = 0; var5 < this.length; ++var5) {
                        var3.array.add(var4[var5] + var1[var5]);
                    }

                    var3.type = 18;
                    int[] var6 = new int[2];
                    findMinMax(var3.getArray_as_Object(), var3.minmax, var6, var3.typeName, var3.type);
                    var3.maxIndex = var6[0];
                    var3.minIndex = var6[1];
                    Conv.restoreMessages();
                    return var3;
                default:
                    throw new IllegalArgumentException("Data type not identified by this method");
            }
        }
    }

    public ArrayMaths plus(Character var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var2 = new ArrayMaths();
        var2.array = new ArrayList();
        var2.length = this.length;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
                String[] var3 = this.getArray_as_String();

                for(int var4 = 0; var4 < this.length; ++var4) {
                    var2.array.add(var3[var4] + var1);
                }

                var2.type = 18;
                int[] var5 = new int[2];
                findMinMax(var2.getArray_as_Object(), var2.minmax, var5, var2.typeName, var2.type);
                var2.maxIndex = var5[0];
                var2.minIndex = var5[1];
                Conv.restoreMessages();
                return var2;
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }
    }

    public ArrayMaths plus(Character[] var1) {
        int var2 = var1.length;
        if (this.length != var2) {
            throw new IllegalArgumentException("The argument array [length = " + var2 + "], must be of the same length as this instance array [length = " + this.length + "]");
        } else {
            if (this.suppressMessages) {
                Conv.suppressMessages();
            }

            ArrayMaths var3 = new ArrayMaths();
            var3.array = new ArrayList();
            var3.length = this.length;
            switch(this.type) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                case 15:
                case 16:
                case 17:
                case 18:
                    String[] var4 = this.getArray_as_String();

                    for(int var5 = 0; var5 < this.length; ++var5) {
                        var3.array.add(var4[var5] + var1[var5]);
                    }

                    var3.type = 18;
                    Conv.restoreMessages();
                    int[] var6 = new int[2];
                    findMinMax(var3.getArray_as_Object(), var3.minmax, var6, var3.typeName, var3.type);
                    var3.maxIndex = var6[0];
                    var3.minIndex = var6[1];
                    return var3;
                default:
                    throw new IllegalArgumentException("Data type not identified by this method");
            }
        }
    }

    public ArrayMaths plus(Vector<Object> var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        new ArrayMaths();
        ArrayMaths var3 = new ArrayMaths(var1);
        ArrayMaths var2;
        switch(var3.type) {
            case 0:
            case 1:
            case 2:
            case 3:
                double[] var4 = var3.getArray_as_double();
                var2 = this.plus(var4);
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                long[] var5 = var3.getArray_as_long();
                var2 = this.plus(var5);
                break;
            case 12:
                BigDecimal[] var6 = var3.getArray_as_BigDecimal();
                var2 = this.plus(var6);
                break;
            case 13:
                BigInteger[] var7 = var3.getArray_as_BigInteger();
                var2 = this.plus(var7);
                break;
            case 14:
                Complex[] var8 = var3.getArray_as_Complex();
                var2 = this.plus(var8);
                break;
            case 15:
                Phasor[] var9 = var3.getArray_as_Phasor();
                var2 = this.plus(var9);
                break;
            case 16:
            case 17:
                Character[] var10 = var3.getArray_as_Character();
                var2 = this.plus(var10);
                break;
            case 18:
                String[] var11 = var3.getArray_as_String();
                var2 = this.plus(var11);
                break;
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        int[] var12 = new int[2];
        findMinMax(var2.getArray_as_Object(), var2.minmax, var12, var2.typeName, var2.type);
        var2.maxIndex = var12[0];
        var2.minIndex = var12[1];
        Conv.restoreMessages();
        return var2;
    }

    public ArrayMaths plus(ArrayList<Object> var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        new ArrayMaths();
        ArrayMaths var3 = new ArrayMaths(var1);
        ArrayMaths var2;
        switch(var3.type) {
            case 0:
            case 1:
            case 2:
            case 3:
                double[] var4 = var3.getArray_as_double();
                var2 = this.plus(var4);
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                long[] var5 = var3.getArray_as_long();
                var2 = this.plus(var5);
                break;
            case 12:
                BigDecimal[] var6 = var3.getArray_as_BigDecimal();
                var2 = this.plus(var6);
                break;
            case 13:
                BigInteger[] var7 = var3.getArray_as_BigInteger();
                var2 = this.plus(var7);
                break;
            case 14:
                Complex[] var8 = var3.getArray_as_Complex();
                var2 = this.plus(var8);
                break;
            case 15:
                Phasor[] var9 = var3.getArray_as_Phasor();
                var2 = this.plus(var9);
                break;
            case 16:
            case 17:
                Character[] var10 = var3.getArray_as_Character();
                var2 = this.plus(var10);
                break;
            case 18:
                String[] var11 = var3.getArray_as_String();
                var2 = this.plus(var11);
                break;
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        int[] var12 = new int[2];
        findMinMax(var2.getArray_as_Object(), var2.minmax, var12, var2.typeName, var2.type);
        var2.maxIndex = var12[0];
        var2.minIndex = var12[1];
        Conv.restoreMessages();
        return var2;
    }

    public ArrayMaths plus(ArrayMaths var1) {
        ArrayList var2 = var1.getArray_as_ArrayList();
        return this.plus(var2);
    }

    public ArrayMaths plus(Stat var1) {
        ArrayList var2 = var1.getArray_as_ArrayList();
        return this.plus(var2);
    }

    public ArrayMaths minus(double var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var3 = new ArrayMaths();
        var3.array = new ArrayList();
        var3.length = this.length;
        int var5;
        BigInteger var6;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                double[] var4 = this.getArray_as_double();

                for(var5 = 0; var5 < this.length; ++var5) {
                    var3.array.add(new Double(var4[var5] - var1));
                }

                var3.type = 0;
                break;
            case 12:
                for(var5 = 0; var5 < this.length; ++var5) {
                    BigDecimal var9 = (BigDecimal)((BigDecimal)this.array.get(var5));
                    var9 = var9.subtract(new BigDecimal(var1));
                    var3.array.add(var9);
                    var6 = null;
                }

                var3.type = this.type;
                break;
            case 13:
                for(var5 = 0; var5 < this.length; ++var5) {
                    var6 = (BigInteger)((BigInteger)this.array.get(var5));
                    BigDecimal var7 = (new BigDecimal(var6)).subtract(new BigDecimal(var1));
                    var3.array.add(var7);
                    var6 = null;
                    var7 = null;
                }

                var3.type = 12;
                break;
            case 14:
                for(var5 = 0; var5 < this.length; ++var5) {
                    var3.array.add(((Complex)this.array.get(var5)).minus(var1));
                }

                var3.type = this.type;
                break;
            case 15:
                for(var5 = 0; var5 < this.length; ++var5) {
                    var3.array.add(((Phasor)this.array.get(var5)).minus(new Complex(var1)));
                }

                var3.type = this.type;
                break;
            case 16:
                throw new IllegalArgumentException("a double or float cannot be subtracted from a char");
            case 17:
                throw new IllegalArgumentException("a double or float cannot be subtracted from a Character");
            case 18:
                throw new IllegalArgumentException("a double or float cannot be subtracted from a String");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        int[] var8 = new int[2];
        findMinMax(var3.getArray_as_Object(), var3.minmax, var8, var3.typeName, var3.type);
        var3.maxIndex = var8[0];
        var3.minIndex = var8[1];
        Conv.restoreMessages();
        return var3;
    }

    public ArrayMaths minus(Double var1) {
        return this.minus(var1);
    }

    public ArrayMaths minus(double[] var1) {
        if (this.length != var1.length) {
            throw new IllegalArgumentException("The length of the argument array, " + var1.length + ", and the length of this instance internal array, " + this.length + ", must be equal");
        } else {
            if (this.suppressMessages) {
                Conv.suppressMessages();
            }

            ArrayMaths var2 = new ArrayMaths();
            var2.array = new ArrayList();
            var2.length = this.length;
            int var4;
            BigInteger var5;
            switch(this.type) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                    double[] var3 = this.getArray_as_double();

                    for(var4 = 0; var4 < this.length; ++var4) {
                        var2.array.add(new Double(var3[var4] - var1[var4]));
                    }

                    var2.type = 0;
                    break;
                case 12:
                    for(var4 = 0; var4 < this.length; ++var4) {
                        BigDecimal var8 = (BigDecimal)((BigDecimal)this.array.get(var4));
                        var8 = var8.subtract(new BigDecimal(var1[var4]));
                        var2.array.add(var8);
                        var5 = null;
                    }

                    var2.type = this.type;
                    break;
                case 13:
                    for(var4 = 0; var4 < this.length; ++var4) {
                        var5 = (BigInteger)((BigInteger)this.array.get(var4));
                        BigDecimal var6 = (new BigDecimal(var5)).subtract(new BigDecimal(var1[var4]));
                        var2.array.add(var6);
                        var5 = null;
                        var6 = null;
                    }

                    var2.type = 12;
                    break;
                case 14:
                    for(var4 = 0; var4 < this.length; ++var4) {
                        var2.array.add(((Complex)this.array.get(var4)).minus(var1[var4]));
                    }

                    var2.type = this.type;
                    break;
                case 15:
                    for(var4 = 0; var4 < this.length; ++var4) {
                        var2.array.add(((Phasor)this.array.get(var4)).minus(new Phasor(var1[var4])));
                    }

                    var2.type = this.type;
                    break;
                case 16:
                    throw new IllegalArgumentException("a double or float cannot be subtracted from a char");
                case 17:
                    throw new IllegalArgumentException("a double or float cannot be subtracted from a Character");
                case 18:
                    throw new IllegalArgumentException("a double or float cannot be subtracted from a String");
                default:
                    throw new IllegalArgumentException("Data type not identified by this method");
            }

            int[] var7 = new int[2];
            findMinMax(var2.getArray_as_Object(), var2.minmax, var7, var2.typeName, var2.type);
            var2.maxIndex = var7[0];
            var2.minIndex = var7[1];
            Conv.restoreMessages();
            return var2;
        }
    }

    public ArrayMaths minus(Double[] var1) {
        int var2 = var1.length;
        if (this.length != var2) {
            throw new IllegalArgumentException("The argument array [length = " + var2 + "], must be of the same length as this instance array [length = " + this.length + "]");
        } else {
            double[] var3 = new double[this.length];

            for(int var4 = 0; var4 < this.length; ++var4) {
                var3[var4] = var1[var4];
            }

            return this.minus(var3);
        }
    }

    public ArrayMaths minus(float var1) {
        double var2 = (double)var1;
        return this.minus(var2);
    }

    public ArrayMaths minus(Float var1) {
        return this.minus(var1);
    }

    public ArrayMaths minus(float[] var1) {
        if (this.length != var1.length) {
            throw new IllegalArgumentException("The length of the argument array, " + var1.length + ", and the length of this instance internal array, " + this.length + ", must be equal");
        } else {
            double[] var2 = new double[this.length];

            for(int var3 = 0; var3 < this.length; ++var3) {
                var2[var3] = (double)var1[var3];
            }

            return this.minus(var2);
        }
    }

    public ArrayMaths minus(Float[] var1) {
        int var2 = var1.length;
        if (this.length != var2) {
            throw new IllegalArgumentException("The argument array [length = " + var2 + "], must be of the same length as this instance array [length = " + this.length + "]");
        } else {
            double[] var3 = new double[this.length];

            for(int var4 = 0; var4 < this.length; ++var4) {
                var3[var4] = var1[var4].doubleValue();
            }

            return this.minus(var3);
        }
    }

    public ArrayMaths minus(long var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var3 = new ArrayMaths();
        var3.array = new ArrayList();
        var3.length = this.length;
        int var8;
        BigInteger var9;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
                double[] var4 = this.getArray_as_double();

                for(int var12 = 0; var12 < this.length; ++var12) {
                    var3.array.add(new Double(var4[var12] - (double)var1));
                }

                var3.type = 0;
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                long var5 = this.getMaximum_as_long();
                long[] var7 = this.getArray_as_long();
                if (9223372036854775807L - var5 >= var1) {
                    for(var8 = 0; var8 < this.length; ++var8) {
                        var3.array.add(new Long(var7[var8] - var1));
                    }

                    var3.type = 4;
                } else {
                    for(var8 = 0; var8 < this.length; ++var8) {
                        var3.array.add(new Double((double)var7[var8] - (double)var1));
                    }

                    var3.type = 0;
                }
                break;
            case 12:
                for(var8 = 0; var8 < this.length; ++var8) {
                    BigDecimal var13 = (BigDecimal)((BigDecimal)this.array.get(var8));
                    var13 = var13.subtract(new BigDecimal((double)var1));
                    var3.array.add(var13);
                    var9 = null;
                }

                var3.type = 12;
                break;
            case 13:
                for(var8 = 0; var8 < this.length; ++var8) {
                    var9 = (BigInteger)((BigInteger)this.array.get(var8));
                    BigInteger var10 = var9.subtract(new BigInteger(Long.toString(var1)));
                    var3.array.add(var10);
                    var9 = null;
                    var10 = null;
                }

                var3.type = 13;
                break;
            case 14:
                for(var8 = 0; var8 < this.length; ++var8) {
                    var3.array.add(((Complex)this.array.get(var8)).minus((double)var1));
                }

                var3.type = this.type;
                break;
            case 15:
                for(var8 = 0; var8 < this.length; ++var8) {
                    var3.array.add(((Phasor)this.array.get(var8)).minus(new Phasor((double)var1)));
                }

                var3.type = this.type;
                break;
            case 16:
                throw new IllegalArgumentException("a long cannot be subtracted from a char");
            case 17:
                throw new IllegalArgumentException("a long cannot be subtracted from a Character");
            case 18:
                throw new IllegalArgumentException("a long cannot be subtracted from a String");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        int[] var11 = new int[2];
        findMinMax(var3.getArray_as_Object(), var3.minmax, var11, var3.typeName, var3.type);
        var3.maxIndex = var11[0];
        var3.minIndex = var11[1];
        Conv.restoreMessages();
        return var3;
    }

    public ArrayMaths minus(Long var1) {
        long var2 = var1;
        return this.minus(var2);
    }

    public ArrayMaths minus(long[] var1) {
        int var2 = var1.length;
        if (this.length != var2) {
            throw new IllegalArgumentException("The argument array [length = " + var2 + "], must be of the same length as this instance array [length = " + this.length + "]");
        } else {
            if (this.suppressMessages) {
                Conv.suppressMessages();
            }

            ArrayMaths var3 = new ArrayMaths();
            var3.array = new ArrayList();
            var3.length = this.length;
            int var11;
            BigInteger var12;
            switch(this.type) {
                case 0:
                case 1:
                case 2:
                case 3:
                    double[] var4 = this.getArray_as_double();

                    for(int var15 = 0; var15 < this.length; ++var15) {
                        var3.array.add(new Double(var4[var15] - (double)var1[var15]));
                    }

                    var3.type = 0;
                    break;
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                    long var5 = this.getMaximum_as_long();
                    ArrayMaths var7 = new ArrayMaths(var1);
                    long var8 = var7.getMaximum_as_long();
                    long[] var10 = this.getArray_as_long();
                    if (9223372036854775807L - var5 >= var8) {
                        for(var11 = 0; var11 < this.length; ++var11) {
                            var3.array.add(new Long(var10[var11] - var1[var11]));
                        }

                        var3.type = 4;
                    } else {
                        for(var11 = 0; var11 < this.length; ++var11) {
                            var3.array.add(new Double((double)var10[var11] - (double)var1[var11]));
                        }

                        var3.type = 0;
                    }
                    break;
                case 12:
                    for(var11 = 0; var11 < this.length; ++var11) {
                        BigDecimal var16 = (BigDecimal)((BigDecimal)this.array.get(var11));
                        var16 = var16.subtract(new BigDecimal((double)var1[var11]));
                        var3.array.add(var16);
                        var12 = null;
                    }

                    var3.type = 12;
                    break;
                case 13:
                    for(var11 = 0; var11 < this.length; ++var11) {
                        var12 = (BigInteger)((BigInteger)this.array.get(var11));
                        BigInteger var13 = var12.subtract(new BigInteger(Long.toString(var1[var11])));
                        var3.array.add(var13);
                        var12 = null;
                        var13 = null;
                    }

                    var3.type = 13;
                    break;
                case 14:
                    for(var11 = 0; var11 < this.length; ++var11) {
                        var3.array.add(((Complex)this.array.get(var11)).minus((double)var1[var11]));
                    }

                    var3.type = this.type;
                    break;
                case 15:
                    for(var11 = 0; var11 < this.length; ++var11) {
                        var3.array.add(((Phasor)this.array.get(var11)).minus(new Phasor((double)var1[var11])));
                    }

                    var3.type = this.type;
                    break;
                case 16:
                    throw new IllegalArgumentException("a long cannot be subtracted from a char");
                case 17:
                    throw new IllegalArgumentException("a long cannot be subtracted from a Character");
                case 18:
                    throw new IllegalArgumentException("a long cannot be subtracted from a String");
                default:
                    throw new IllegalArgumentException("Data type not identified by this method");
            }

            int[] var14 = new int[2];
            findMinMax(var3.getArray_as_Object(), var3.minmax, var14, var3.typeName, var3.type);
            var3.maxIndex = var14[0];
            var3.minIndex = var14[1];
            Conv.restoreMessages();
            return var3;
        }
    }

    public ArrayMaths minus(Long[] var1) {
        int var2 = var1.length;
        if (this.length != var2) {
            throw new IllegalArgumentException("The argument array [length = " + var2 + "], must be of the same length as this instance array [length = " + this.length + "]");
        } else {
            long[] var3 = new long[this.length];

            for(int var4 = 0; var4 < this.length; ++var4) {
                var3[var4] = var1[var4];
            }

            return this.minus(var3);
        }
    }

    public ArrayMaths minus(int var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var2 = new ArrayMaths();
        var2.array = new ArrayList();
        var2.length = this.length;
        int var7;
        int var9;
        BigInteger var10;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
                double[] var3 = this.getArray_as_double();

                for(int var13 = 0; var13 < this.length; ++var13) {
                    var2.array.add(new Double(var3[var13] - (double)var1));
                }

                var2.type = 0;
                break;
            case 4:
                long var4 = this.getMaximum_as_long();
                long[] var6 = this.getArray_as_long();
                if (9223372036854775807L - var4 >= (long)var1) {
                    for(var7 = 0; var7 < this.length; ++var7) {
                        var2.array.add(new Long(var6[var7] - (long)var1));
                    }

                    var2.type = 4;
                } else {
                    for(var7 = 0; var7 < this.length; ++var7) {
                        var2.array.add(new Double((double)var6[var7] - (double)var1));
                    }

                    var2.type = 0;
                }
                break;
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                var7 = this.getMaximum_as_int();
                int[] var8 = this.getArray_as_int();
                if (2147483647 - var7 >= var1) {
                    for(var9 = 0; var9 < this.length; ++var9) {
                        var2.array.add(new Integer(var8[var9] - var1));
                    }

                    var2.type = 6;
                } else {
                    for(var9 = 0; var9 < this.length; ++var9) {
                        var2.array.add(new Double((double)var8[var9] - (double)var1));
                    }

                    var2.type = 0;
                }
                break;
            case 12:
                for(var9 = 0; var9 < this.length; ++var9) {
                    BigDecimal var14 = (BigDecimal)((BigDecimal)this.array.get(var9));
                    var14 = var14.subtract(new BigDecimal((double)var1));
                    var2.array.add(var14);
                    var10 = null;
                }

                var2.type = 12;
                break;
            case 13:
                for(var9 = 0; var9 < this.length; ++var9) {
                    var10 = (BigInteger)((BigInteger)this.array.get(var9));
                    BigInteger var11 = var10.subtract(new BigInteger(Integer.toString(var1)));
                    var2.array.add(var11);
                    var10 = null;
                    var11 = null;
                }

                var2.type = 13;
                break;
            case 14:
                for(var9 = 0; var9 < this.length; ++var9) {
                    var2.array.add(((Complex)this.array.get(var9)).minus((double)var1));
                }

                var2.type = this.type;
                break;
            case 15:
                for(var9 = 0; var9 < this.length; ++var9) {
                    var2.array.add(((Phasor)this.array.get(var9)).minus(new Phasor((double)var1)));
                }

                var2.type = this.type;
                break;
            case 16:
                throw new IllegalArgumentException("an int cannot be subtracted from a char");
            case 17:
                throw new IllegalArgumentException("an int cannot be subtracted from a Character");
            case 18:
                throw new IllegalArgumentException("an int cannot be subtracted from a String");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        int[] var12 = new int[2];
        findMinMax(var2.getArray_as_Object(), var2.minmax, var12, var2.typeName, var2.type);
        var2.maxIndex = var12[0];
        var2.minIndex = var12[1];
        Conv.restoreMessages();
        return var2;
    }

    public ArrayMaths minus(Integer var1) {
        int var2 = var1;
        return this.minus(var2);
    }

    public ArrayMaths minus(int[] var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var2 = new ArrayMaths();
        var2.array = new ArrayList();
        var2.length = this.length;
        int var10;
        int var14;
        BigInteger var15;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
                double[] var3 = this.getArray_as_double();

                for(int var18 = 0; var18 < this.length; ++var18) {
                    var2.array.add(new Double(var3[var18] - (double)var1[var18]));
                }

                var2.type = 0;
                break;
            case 4:
                long var4 = this.getMaximum_as_long();
                ArrayMaths var6 = new ArrayMaths(var1);
                long var7 = var6.getMaximum_as_long();
                long[] var9 = this.getArray_as_long();
                if (9223372036854775807L - var4 >= var7) {
                    for(var10 = 0; var10 < this.length; ++var10) {
                        var2.array.add(new Long(var9[var10] - (long)var1[var10]));
                    }

                    var2.type = 4;
                } else {
                    for(var10 = 0; var10 < this.length; ++var10) {
                        var2.array.add(new Double((double)var9[var10] - (double)var1[var10]));
                    }

                    var2.type = 0;
                }
                break;
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                var10 = this.getMaximum_as_int();
                ArrayMaths var11 = new ArrayMaths(var1);
                int var12 = var11.getMaximum_as_int();
                int[] var13 = this.getArray_as_int();
                if (2147483647 - var10 >= var12) {
                    for(var14 = 0; var14 < this.length; ++var14) {
                        var2.array.add(new Integer(var13[var14] - var1[var14]));
                    }

                    var2.type = 6;
                } else {
                    for(var14 = 0; var14 < this.length; ++var14) {
                        var2.array.add(new Double((double)var13[var14] - (double)var1[var14]));
                    }

                    var2.type = 0;
                }
                break;
            case 12:
                for(var14 = 0; var14 < this.length; ++var14) {
                    BigDecimal var19 = (BigDecimal)((BigDecimal)this.array.get(var14));
                    var19 = var19.subtract(new BigDecimal((double)var1[var14]));
                    var2.array.add(var19);
                    var15 = null;
                }

                var2.type = 12;
                break;
            case 13:
                for(var14 = 0; var14 < this.length; ++var14) {
                    var15 = (BigInteger)((BigInteger)this.array.get(var14));
                    BigInteger var16 = var15.subtract(new BigInteger(Integer.toString(var1[var14])));
                    var2.array.add(var16);
                    var15 = null;
                    var16 = null;
                }

                var2.type = 13;
                break;
            case 14:
                for(var14 = 0; var14 < this.length; ++var14) {
                    var2.array.add(((Complex)this.array.get(var14)).minus((double)var1[var14]));
                }

                var2.type = this.type;
                break;
            case 15:
                for(var14 = 0; var14 < this.length; ++var14) {
                    var2.array.add(((Phasor)this.array.get(var14)).minus(new Phasor((double)var1[var14])));
                }

                var2.type = this.type;
                break;
            case 16:
                throw new IllegalArgumentException("an int cannot be subtracted from a char");
            case 17:
                throw new IllegalArgumentException("an int cannot be subtracted from a Character");
            case 18:
                throw new IllegalArgumentException("an int cannot be subtracted from a String");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        int[] var17 = new int[2];
        findMinMax(var2.getArray_as_Object(), var2.minmax, var17, var2.typeName, var2.type);
        var2.maxIndex = var17[0];
        var2.minIndex = var17[1];
        Conv.restoreMessages();
        return var2;
    }

    public ArrayMaths minus(Integer[] var1) {
        int var2 = var1.length;
        if (this.length != var2) {
            throw new IllegalArgumentException("The argument array [length = " + var2 + "], must be of the same length as this instance array [length = " + this.length + "]");
        } else {
            int[] var3 = new int[this.length];

            for(int var4 = 0; var4 < this.length; ++var4) {
                var3[var4] = var1[var4];
            }

            return this.minus(var3);
        }
    }

    public ArrayMaths minus(short var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var2 = new ArrayMaths();
        var2.array = new ArrayList();
        var2.length = this.length;
        int var9;
        BigInteger var10;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
                double[] var3 = this.getArray_as_double();

                for(int var13 = 0; var13 < this.length; ++var13) {
                    var2.array.add(new Double(var3[var13] - (double)var1));
                }

                var2.type = 0;
                break;
            case 4:
                long var4 = this.getMaximum_as_long();
                long[] var6 = this.getArray_as_long();
                int var14;
                if (9223372036854775807L - var4 >= (long)var1) {
                    for(var14 = 0; var14 < this.length; ++var14) {
                        var2.array.add(new Long(var6[var14] - (long)var1));
                    }

                    var2.type = 4;
                } else {
                    for(var14 = 0; var14 < this.length; ++var14) {
                        var2.array.add(new Double((double)var6[var14] - (double)var1));
                    }

                    var2.type = 0;
                }
                break;
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                short var7 = this.getMaximum_as_short();
                short[] var8 = this.getArray_as_short();
                if (2147483647 - var7 >= var1) {
                    for(var9 = 0; var9 < this.length; ++var9) {
                        var2.array.add(new Integer(var8[var9] - var1));
                    }

                    var2.type = 6;
                } else {
                    for(var9 = 0; var9 < this.length; ++var9) {
                        var2.array.add(new Double((double)var8[var9] - (double)var1));
                    }

                    var2.type = 0;
                }
                break;
            case 12:
                for(var9 = 0; var9 < this.length; ++var9) {
                    BigDecimal var15 = (BigDecimal)((BigDecimal)this.array.get(var9));
                    var15 = var15.subtract(new BigDecimal((double)var1));
                    var2.array.add(var15);
                    var10 = null;
                }

                var2.type = 12;
                break;
            case 13:
                for(var9 = 0; var9 < this.length; ++var9) {
                    var10 = (BigInteger)((BigInteger)this.array.get(var9));
                    BigInteger var11 = var10.subtract(new BigInteger(Integer.toString(var1)));
                    var2.array.add(var11);
                    var10 = null;
                    var11 = null;
                }

                var2.type = 13;
                break;
            case 14:
                for(var9 = 0; var9 < this.length; ++var9) {
                    var2.array.add(((Complex)this.array.get(var9)).minus((double)var1));
                }

                var2.type = this.type;
                break;
            case 15:
                for(var9 = 0; var9 < this.length; ++var9) {
                    var2.array.add(((Phasor)this.array.get(var9)).minus(new Phasor((double)var1)));
                }

                var2.type = this.type;
                break;
            case 16:
                throw new IllegalArgumentException("a short cannot be subtracted from a char");
            case 17:
                throw new IllegalArgumentException("a short cannot be subtracted from a Character");
            case 18:
                throw new IllegalArgumentException("a short cannot be subtracted from a String");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        int[] var12 = new int[2];
        findMinMax(var2.getArray_as_Object(), var2.minmax, var12, var2.typeName, var2.type);
        var2.maxIndex = var12[0];
        var2.minIndex = var12[1];
        Conv.restoreMessages();
        return var2;
    }

    public ArrayMaths minus(Short var1) {
        short var2 = var1;
        return this.minus(var2);
    }

    public ArrayMaths minus(short[] var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var2 = new ArrayMaths();
        var2.array = new ArrayList();
        var2.length = this.length;
        int var14;
        BigInteger var15;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
                double[] var3 = this.getArray_as_double();

                for(int var18 = 0; var18 < this.length; ++var18) {
                    var2.array.add(new Double(var3[var18] - (double)var1[var18]));
                }

                var2.type = 0;
                break;
            case 4:
                long var4 = this.getMaximum_as_long();
                ArrayMaths var6 = new ArrayMaths(var1);
                long var7 = var6.getMaximum_as_long();
                long[] var9 = this.getArray_as_long();
                int var19;
                if (9223372036854775807L - var4 >= var7) {
                    for(var19 = 0; var19 < this.length; ++var19) {
                        var2.array.add(new Long(var9[var19] - (long)var1[var19]));
                    }

                    var2.type = 4;
                } else {
                    for(var19 = 0; var19 < this.length; ++var19) {
                        var2.array.add(new Double((double)var9[var19] - (double)var1[var19]));
                    }

                    var2.type = 0;
                }
                break;
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                short var10 = this.getMaximum_as_short();
                ArrayMaths var11 = new ArrayMaths(var1);
                short var12 = var11.getMaximum_as_short();
                short[] var13 = this.getArray_as_short();
                if (2147483647 - var10 >= var12) {
                    for(var14 = 0; var14 < this.length; ++var14) {
                        var2.array.add(new Integer(var13[var14] - var1[var14]));
                    }

                    var2.type = 6;
                } else {
                    for(var14 = 0; var14 < this.length; ++var14) {
                        var2.array.add(new Double((double)var13[var14] - (double)var1[var14]));
                    }

                    var2.type = 0;
                }
                break;
            case 12:
                for(var14 = 0; var14 < this.length; ++var14) {
                    BigDecimal var20 = (BigDecimal)((BigDecimal)this.array.get(var14));
                    var20 = var20.subtract(new BigDecimal((double)var1[var14]));
                    var2.array.add(var20);
                    var15 = null;
                }

                var2.type = 12;
                break;
            case 13:
                for(var14 = 0; var14 < this.length; ++var14) {
                    var15 = (BigInteger)((BigInteger)this.array.get(var14));
                    BigInteger var16 = var15.subtract(new BigInteger(Integer.toString(var1[var14])));
                    var2.array.add(var16);
                    var15 = null;
                    var16 = null;
                }

                var2.type = 13;
                break;
            case 14:
                for(var14 = 0; var14 < this.length; ++var14) {
                    var2.array.add(((Complex)this.array.get(var14)).minus((double)var1[var14]));
                }

                var2.type = this.type;
                break;
            case 15:
                for(var14 = 0; var14 < this.length; ++var14) {
                    var2.array.add(((Phasor)this.array.get(var14)).minus(new Phasor((double)var1[var14])));
                }

                var2.type = this.type;
                break;
            case 16:
                throw new IllegalArgumentException("a long cannot be subtracted from a char");
            case 17:
                throw new IllegalArgumentException("a long cannot be subtracted from a Character");
            case 18:
                throw new IllegalArgumentException("a short cannot be subtracted from a String");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        int[] var17 = new int[2];
        findMinMax(var2.getArray_as_Object(), var2.minmax, var17, var2.typeName, var2.type);
        var2.maxIndex = var17[0];
        var2.minIndex = var17[1];
        Conv.restoreMessages();
        return var2;
    }

    public ArrayMaths minus(Short[] var1) {
        int var2 = var1.length;
        if (this.length != var2) {
            throw new IllegalArgumentException("The argument array [length = " + var2 + "], must be of the same length as this instance array [length = " + this.length + "]");
        } else {
            short[] var3 = new short[this.length];

            for(int var4 = 0; var4 < this.length; ++var4) {
                var3[var4] = var1[var4];
            }

            return this.minus(var3);
        }
    }

    public ArrayMaths minus(BigDecimal var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var2 = new ArrayMaths();
        var2.array = new ArrayList();
        var2.length = this.length;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
                BigDecimal[] var3 = this.getArray_as_BigDecimal();

                for(int var8 = 0; var8 < this.length; ++var8) {
                    var2.array.add(var3[var8].subtract(var1));
                }

                var2.type = 12;
                break;
            case 14:
                Complex[] var4 = this.getArray_as_Complex();

                for(int var9 = 0; var9 < this.length; ++var9) {
                    var2.array.add(var4[var9].minus(var1.doubleValue()));
                }

                var2.type = this.type;
                break;
            case 15:
                Phasor[] var5 = this.getArray_as_Phasor();

                for(int var6 = 0; var6 < this.length; ++var6) {
                    var2.array.add(var5[var6].minus(new Phasor(var1.doubleValue())));
                }

                var2.type = this.type;
                break;
            case 16:
                throw new IllegalArgumentException("a BigDecimal cannot be subtracted from a char");
            case 17:
                throw new IllegalArgumentException("a BigDecimal cannot be subtracted from a Character");
            case 18:
                throw new IllegalArgumentException("a BigDecimal cannot be subtracted from a String");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        int[] var7 = new int[2];
        findMinMax(var2.getArray_as_Object(), var2.minmax, var7, var2.typeName, var2.type);
        var2.maxIndex = var7[0];
        var2.minIndex = var7[1];
        Conv.restoreMessages();
        return var2;
    }

    public ArrayMaths minus(byte var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var2 = new ArrayMaths();
        var2.array = new ArrayList();
        var2.length = this.length;
        int var9;
        BigInteger var10;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
                double[] var3 = this.getArray_as_double();

                for(int var13 = 0; var13 < this.length; ++var13) {
                    var2.array.add(new Double(var3[var13] - (double)var1));
                }

                var2.type = 0;
                break;
            case 4:
                long var4 = this.getMaximum_as_long();
                long[] var6 = this.getArray_as_long();
                int var14;
                if (9223372036854775807L - var4 >= (long)var1) {
                    for(var14 = 0; var14 < this.length; ++var14) {
                        var2.array.add(new Long(var6[var14] - (long)var1));
                    }

                    var2.type = 4;
                } else {
                    for(var14 = 0; var14 < this.length; ++var14) {
                        var2.array.add(new Double((double)var6[var14] - (double)var1));
                    }

                    var2.type = 0;
                }
                break;
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                byte var7 = this.getMaximum_as_byte();
                byte[] var8 = this.getArray_as_byte();
                if (2147483647 - var7 >= var1) {
                    for(var9 = 0; var9 < this.length; ++var9) {
                        var2.array.add(new Integer(var8[var9] - var1));
                    }

                    var2.type = 6;
                } else {
                    for(var9 = 0; var9 < this.length; ++var9) {
                        var2.array.add(new Double((double)var8[var9] - (double)var1));
                    }

                    var2.type = 0;
                }
                break;
            case 12:
                for(var9 = 0; var9 < this.length; ++var9) {
                    BigDecimal var15 = (BigDecimal)((BigDecimal)this.array.get(var9));
                    var15 = var15.subtract(new BigDecimal((double)var1));
                    var2.array.add(var15);
                    var10 = null;
                }

                var2.type = 12;
                break;
            case 13:
                for(var9 = 0; var9 < this.length; ++var9) {
                    var10 = (BigInteger)((BigInteger)this.array.get(var9));
                    BigInteger var11 = var10.subtract(new BigInteger(Integer.toString(var1)));
                    var2.array.add(var11);
                    var10 = null;
                    var11 = null;
                }

                var2.type = 13;
                break;
            case 14:
                for(var9 = 0; var9 < this.length; ++var9) {
                    var2.array.add(((Complex)this.array.get(var9)).minus((double)var1));
                }

                var2.type = this.type;
                break;
            case 15:
                for(var9 = 0; var9 < this.length; ++var9) {
                    var2.array.add(((Phasor)this.array.get(var9)).minus(new Phasor((double)var1)));
                }

                var2.type = this.type;
                break;
            case 16:
                throw new IllegalArgumentException("a byte cannot be subtracted from a char");
            case 17:
                throw new IllegalArgumentException("a byte cannot be subtracted from a Character");
            case 18:
                throw new IllegalArgumentException("a byte cannot be subtracted from a String");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        int[] var12 = new int[2];
        findMinMax(var2.getArray_as_Object(), var2.minmax, var12, var2.typeName, var2.type);
        var2.maxIndex = var12[0];
        var2.minIndex = var12[1];
        Conv.restoreMessages();
        return var2;
    }

    public ArrayMaths minus(Byte var1) {
        byte var2 = var1;
        return this.minus(var2);
    }

    public ArrayMaths minus(byte[] var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var2 = new ArrayMaths();
        var2.array = new ArrayList();
        var2.length = this.length;
        int var14;
        BigInteger var15;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
                double[] var3 = this.getArray_as_double();

                for(int var18 = 0; var18 < this.length; ++var18) {
                    var2.array.add(new Double(var3[var18] - (double)var1[var18]));
                }

                var2.type = 0;
                break;
            case 4:
                long var4 = this.getMaximum_as_long();
                ArrayMaths var6 = new ArrayMaths(var1);
                long var7 = var6.getMaximum_as_long();
                long[] var9 = this.getArray_as_long();
                int var19;
                if (9223372036854775807L - var4 >= var7) {
                    for(var19 = 0; var19 < this.length; ++var19) {
                        var2.array.add(new Long(var9[var19] - (long)var1[var19]));
                    }

                    var2.type = 4;
                } else {
                    for(var19 = 0; var19 < this.length; ++var19) {
                        var2.array.add(new Double((double)var9[var19] - (double)var1[var19]));
                    }

                    var2.type = 0;
                }
                break;
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                byte var10 = this.getMaximum_as_byte();
                ArrayMaths var11 = new ArrayMaths(var1);
                byte var12 = var11.getMaximum_as_byte();
                byte[] var13 = this.getArray_as_byte();
                if (2147483647 - var10 >= var12) {
                    for(var14 = 0; var14 < this.length; ++var14) {
                        var2.array.add(new Integer(var13[var14] - var1[var14]));
                    }

                    var2.type = 6;
                } else {
                    for(var14 = 0; var14 < this.length; ++var14) {
                        var2.array.add(new Double((double)var13[var14] - (double)var1[var14]));
                    }

                    var2.type = 0;
                }
                break;
            case 12:
                for(var14 = 0; var14 < this.length; ++var14) {
                    BigDecimal var20 = (BigDecimal)((BigDecimal)this.array.get(var14));
                    var20 = var20.subtract(new BigDecimal((double)var1[var14]));
                    var2.array.add(var20);
                    var15 = null;
                }

                var2.type = 12;
                break;
            case 13:
                for(var14 = 0; var14 < this.length; ++var14) {
                    var15 = (BigInteger)((BigInteger)this.array.get(var14));
                    BigInteger var16 = var15.subtract(new BigInteger(Integer.toString(var1[var14])));
                    var2.array.add(var16);
                    var15 = null;
                    var16 = null;
                }

                var2.type = 13;
                break;
            case 14:
                for(var14 = 0; var14 < this.length; ++var14) {
                    var2.array.add(((Complex)this.array.get(var14)).minus((double)var1[var14]));
                }

                var2.type = this.type;
                break;
            case 15:
                for(var14 = 0; var14 < this.length; ++var14) {
                    var2.array.add(((Phasor)this.array.get(var14)).minus(new Phasor((double)var1[var14])));
                }

                var2.type = this.type;
                break;
            case 16:
                throw new IllegalArgumentException("a byte cannot be subtracted from a char");
            case 17:
                throw new IllegalArgumentException("a byte cannot be subtracted from a Character");
            case 18:
                throw new IllegalArgumentException("a byte cannot be subtracted from a String");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        int[] var17 = new int[2];
        findMinMax(var2.getArray_as_Object(), var2.minmax, var17, var2.typeName, var2.type);
        var2.maxIndex = var17[0];
        var2.minIndex = var17[1];
        Conv.restoreMessages();
        return var2;
    }

    public ArrayMaths minus(Byte[] var1) {
        int var2 = var1.length;
        if (this.length != var2) {
            throw new IllegalArgumentException("The argument array [length = " + var2 + "], must be of the same length as this instance array [length = " + this.length + "]");
        } else {
            byte[] var3 = new byte[this.length];

            for(int var4 = 0; var4 < this.length; ++var4) {
                var3[var4] = var1[var4];
            }

            return this.minus(var3);
        }
    }

    public ArrayMaths minus(BigDecimal[] var1) {
        int var2 = var1.length;
        if (this.length != var2) {
            throw new IllegalArgumentException("The argument array [length = " + var2 + "], must be of the same length as this instance array [length = " + this.length + "]");
        } else {
            if (this.suppressMessages) {
                Conv.suppressMessages();
            }

            ArrayMaths var3 = new ArrayMaths();
            var3.array = new ArrayList();
            var3.length = this.length;
            switch(this.type) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                    BigDecimal[] var4 = this.getArray_as_BigDecimal();

                    for(int var9 = 0; var9 < this.length; ++var9) {
                        var3.array.add(var4[var9].add(var1[var9]));
                    }

                    Conv.restoreMessages();
                    var3.type = 12;
                    break;
                case 14:
                    Complex[] var5 = this.getArray_as_Complex();

                    for(int var10 = 0; var10 < this.length; ++var10) {
                        var3.array.add(var5[var10].minus(var1[var10].doubleValue()));
                    }

                    var3.type = this.type;
                    break;
                case 15:
                    Phasor[] var6 = this.getArray_as_Phasor();

                    for(int var7 = 0; var7 < this.length; ++var7) {
                        var3.array.add(var6[var7].minus(new Phasor(var1[var7].doubleValue())));
                    }

                    var3.type = this.type;
                    break;
                case 16:
                    throw new IllegalArgumentException("a BigDecimal cannot be subtracted from a char");
                case 17:
                    throw new IllegalArgumentException("a BigDecimal cannot be subtracted from a Character");
                case 18:
                    throw new IllegalArgumentException("a BigDecimalcannot be subtracted from a String");
                default:
                    throw new IllegalArgumentException("Data type not identified by this method");
            }

            int[] var8 = new int[2];
            findMinMax(var3.getArray_as_Object(), var3.minmax, var8, var3.typeName, var3.type);
            var3.maxIndex = var8[0];
            var3.minIndex = var8[1];
            Conv.restoreMessages();
            return var3;
        }
    }

    public ArrayMaths minus(BigInteger var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var2 = new ArrayMaths();
        var2.array = new ArrayList();
        var2.length = this.length;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 12:
                BigDecimal var3 = Conv.convert_BigInteger_to_BigDecimal(var1);
                BigDecimal[] var4 = this.getArray_as_BigDecimal();

                for(int var10 = 0; var10 < this.length; ++var10) {
                    var2.array.add(var4[var10].add(var3));
                }

                var2.type = 12;
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 13:
                BigInteger[] var5 = this.getArray_as_BigInteger();

                for(int var11 = 0; var11 < this.length; ++var11) {
                    var2.array.add(var5[var11].subtract(var1));
                }

                var2.type = 13;
                break;
            case 14:
                Complex[] var6 = this.getArray_as_Complex();

                for(int var12 = 0; var12 < this.length; ++var12) {
                    var2.array.add(var6[var12].minus(Conv.convert_BigInteger_to_double(var1)));
                }

                var2.type = this.type;
                break;
            case 15:
                Phasor[] var7 = this.getArray_as_Phasor();

                for(int var8 = 0; var8 < this.length; ++var8) {
                    var2.array.add(var7[var8].minus(new Phasor(Conv.convert_BigInteger_to_double(var1))));
                }

                var2.type = this.type;
                break;
            case 16:
                throw new IllegalArgumentException("a BigInteger cannot be subtracted from a char");
            case 17:
                throw new IllegalArgumentException("a BigInteger cannot be subtracted from a Character");
            case 18:
                throw new IllegalArgumentException("a BigInteger cannot be subtracted from a String");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        int[] var9 = new int[2];
        findMinMax(var2.getArray_as_Object(), var2.minmax, var9, var2.typeName, var2.type);
        var2.maxIndex = var9[0];
        var2.minIndex = var9[1];
        Conv.restoreMessages();
        return var2;
    }

    public ArrayMaths minus(BigInteger[] var1) {
        int var2 = var1.length;
        if (this.length != var2) {
            throw new IllegalArgumentException("The argument array [length = " + var2 + "], must be of the same length as this instance array [length = " + this.length + "]");
        } else {
            if (this.suppressMessages) {
                Conv.suppressMessages();
            }

            ArrayMaths var3 = new ArrayMaths();
            var3.array = new ArrayList();
            var3.length = this.length;
            switch(this.type) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 12:
                    BigDecimal[] var4 = this.getArray_as_BigDecimal();

                    for(int var10 = 0; var10 < this.length; ++var10) {
                        var3.array.add(var4[var10].add(Conv.convert_BigInteger_to_BigDecimal(var1[var10])));
                    }

                    var3.type = 12;
                    break;
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 13:
                    BigInteger[] var5 = this.getArray_as_BigInteger();

                    for(int var11 = 0; var11 < this.length; ++var11) {
                        var3.array.add(var5[var11].add(var1[var11]));
                    }

                    var3.type = 13;
                    break;
                case 14:
                    Complex[] var6 = this.getArray_as_Complex();

                    for(int var12 = 0; var12 < this.length; ++var12) {
                        var3.array.add(var6[var12].minus(Conv.convert_BigInteger_to_double(var1[var12])));
                    }

                    var3.type = this.type;
                    break;
                case 15:
                    Phasor[] var7 = this.getArray_as_Phasor();

                    for(int var8 = 0; var8 < this.length; ++var8) {
                        var3.array.add(var7[var8].minus(new Phasor(Conv.convert_BigInteger_to_double(var1[var8]))));
                    }

                    var3.type = this.type;
                    break;
                case 16:
                    throw new IllegalArgumentException("a BigInteger cannot be subtracted from a char");
                case 17:
                    throw new IllegalArgumentException("a BigInteger cannot be subtracted from a Character");
                case 18:
                    throw new IllegalArgumentException("a BigInteger cannot be subtracted from a String");
                default:
                    throw new IllegalArgumentException("Data type not identified by this method");
            }

            int[] var9 = new int[2];
            findMinMax(var3.getArray_as_Object(), var3.minmax, var9, var3.typeName, var3.type);
            var3.maxIndex = var9[0];
            var3.minIndex = var9[1];
            Conv.restoreMessages();
            return var3;
        }
    }

    public ArrayMaths minus(Complex var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var2 = new ArrayMaths();
        var2.array = new ArrayList();
        var2.length = this.length;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
                Complex[] var3 = this.getArray_as_Complex();

                for(int var6 = 0; var6 < this.length; ++var6) {
                    var2.array.add(var3[var6].minus(var1));
                }

                var2.type = 14;
                break;
            case 15:
                Phasor[] var4 = this.getArray_as_Phasor();

                for(int var5 = 0; var5 < this.length; ++var5) {
                    var2.array.add(var4[var5].minus(Conv.convert_Complex_to_Phasor(var1)));
                }

                var2.type = this.type;
                break;
            case 16:
                throw new IllegalArgumentException("a Complex cannot be subtracted from a char");
            case 17:
                throw new IllegalArgumentException("a Complex cannot be subtracted from a Character");
            case 18:
                throw new IllegalArgumentException("a Complex cannot be subtracted from a String");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var2;
    }

    public ArrayMaths minus(Complex[] var1) {
        int var2 = var1.length;
        if (this.length != var2) {
            throw new IllegalArgumentException("The argument array [length = " + var2 + "], must be of the same length as this instance array [length = " + this.length + "]");
        } else {
            if (this.suppressMessages) {
                Conv.suppressMessages();
            }

            ArrayMaths var3 = new ArrayMaths();
            var3.array = new ArrayList();
            var3.length = this.length;
            switch(this.type) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                    Complex[] var4 = this.getArray_as_Complex();

                    for(int var7 = 0; var7 < this.length; ++var7) {
                        var3.array.add(var4[var7].minus(var1[var7]));
                    }

                    var3.type = 14;
                    break;
                case 15:
                    Phasor[] var5 = this.getArray_as_Phasor();

                    for(int var6 = 0; var6 < this.length; ++var6) {
                        var3.array.add(var5[var6].minus(Conv.convert_Complex_to_Phasor(var1[var6])));
                    }

                    var3.type = this.type;
                    break;
                case 16:
                    throw new IllegalArgumentException("a Complex cannot be subtracted from a char");
                case 17:
                    throw new IllegalArgumentException("a Complex cannot be subtracted from a Character");
                case 18:
                    throw new IllegalArgumentException("a Complex cannot be subtracted from a String");
                default:
                    throw new IllegalArgumentException("Data type not identified by this method");
            }

            Conv.restoreMessages();
            return var3;
        }
    }

    public ArrayMaths minus(Phasor var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var2 = new ArrayMaths();
        var2.array = new ArrayList();
        var2.length = this.length;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 15:
                Phasor[] var3 = this.getArray_as_Phasor();

                for(int var6 = 0; var6 < this.length; ++var6) {
                    var2.array.add(var3[var6].minus(var1));
                }

                var2.type = 15;
                break;
            case 14:
                Complex[] var4 = this.getArray_as_Complex();

                for(int var5 = 0; var5 < this.length; ++var5) {
                    var2.array.add(var4[var5].minus(Conv.convert_Phasor_to_Complex(var1)));
                }

                var2.type = this.type;
                break;
            case 16:
                throw new IllegalArgumentException("a Phasor cannot be subtracted from a char");
            case 17:
                throw new IllegalArgumentException("a Phasor cannot be subtracted from a Character");
            case 18:
                throw new IllegalArgumentException("a Phasor cannot be subtracted from a String");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var2;
    }

    public ArrayMaths minus(Phasor[] var1) {
        int var2 = var1.length;
        if (this.length != var2) {
            throw new IllegalArgumentException("The argument array [length = " + var2 + "], must be of the same length as this instance array [length = " + this.length + "]");
        } else {
            if (this.suppressMessages) {
                Conv.suppressMessages();
            }

            ArrayMaths var3 = new ArrayMaths();
            var3.array = new ArrayList();
            var3.length = this.length;
            switch(this.type) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 15:
                    Phasor[] var4 = this.getArray_as_Phasor();

                    for(int var7 = 0; var7 < this.length; ++var7) {
                        var3.array.add(var4[var7].minus(var1[var7]));
                    }

                    var3.type = 15;
                    break;
                case 14:
                    Complex[] var5 = this.getArray_as_Complex();

                    for(int var6 = 0; var6 < this.length; ++var6) {
                        var3.array.add(var5[var6].minus(Conv.convert_Phasor_to_Complex(var1[var6])));
                    }

                    var3.type = this.type;
                    break;
                case 16:
                    throw new IllegalArgumentException("a Phasor cannot be subtracted from a char");
                case 17:
                    throw new IllegalArgumentException("a Phasor cannot be subtracted from a Character");
                case 18:
                    throw new IllegalArgumentException("a Phasor cannot be subtracted from a String");
                default:
                    throw new IllegalArgumentException("Data type not identified by this method");
            }

            Conv.restoreMessages();
            return var3;
        }
    }

    public ArrayMaths minus(Vector<Object> var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        new ArrayMaths();
        ArrayMaths var3 = new ArrayMaths(var1);
        ArrayMaths var2;
        switch(var3.type) {
            case 0:
            case 1:
            case 2:
            case 3:
                double[] var4 = var3.getArray_as_double();
                var2 = this.minus(var4);
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                long[] var5 = var3.getArray_as_long();
                var2 = this.minus(var5);
                break;
            case 12:
                BigDecimal[] var6 = var3.getArray_as_BigDecimal();
                var2 = this.minus(var6);
                break;
            case 13:
                BigInteger[] var7 = var3.getArray_as_BigInteger();
                var2 = this.minus(var7);
                break;
            case 14:
                Complex[] var8 = var3.getArray_as_Complex();
                var2 = this.minus(var8);
                break;
            case 15:
                Phasor[] var9 = var3.getArray_as_Phasor();
                var2 = this.minus(var9);
                break;
            case 16:
                throw new IllegalArgumentException("ArrayList/char subtraction not allowed");
            case 17:
                throw new IllegalArgumentException("ArrayList/Character subtraction not allowed");
            case 18:
                throw new IllegalArgumentException("ArrayList/String subtraction not allowed");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        int[] var10 = new int[2];
        findMinMax(var2.getArray_as_Object(), var2.minmax, var10, var2.typeName, var2.type);
        var2.maxIndex = var10[0];
        var2.minIndex = var10[1];
        Conv.restoreMessages();
        return var2;
    }

    public ArrayMaths minus(ArrayList<Object> var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        new ArrayMaths();
        ArrayMaths var3 = new ArrayMaths(var1);
        ArrayMaths var2;
        switch(var3.type) {
            case 0:
            case 1:
            case 2:
            case 3:
                double[] var4 = var3.getArray_as_double();
                var2 = this.minus(var4);
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                long[] var5 = var3.getArray_as_long();
                var2 = this.minus(var5);
                break;
            case 12:
                BigDecimal[] var6 = var3.getArray_as_BigDecimal();
                var2 = this.minus(var6);
                break;
            case 13:
                BigInteger[] var7 = var3.getArray_as_BigInteger();
                var2 = this.minus(var7);
                break;
            case 14:
                Complex[] var8 = var3.getArray_as_Complex();
                var2 = this.minus(var8);
                break;
            case 15:
                Phasor[] var9 = var3.getArray_as_Phasor();
                var2 = this.minus(var9);
                break;
            case 16:
                throw new IllegalArgumentException("ArrayList/char subtraction not allowed");
            case 17:
                throw new IllegalArgumentException("ArrayList/Character subtraction not allowed");
            case 18:
                throw new IllegalArgumentException("ArrayList/String subtraction not allowed");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        int[] var10 = new int[2];
        findMinMax(var2.getArray_as_Object(), var2.minmax, var10, var2.typeName, var2.type);
        var2.maxIndex = var10[0];
        var2.minIndex = var10[1];
        Conv.restoreMessages();
        return var2;
    }

    public ArrayMaths minus(ArrayMaths var1) {
        ArrayList var2 = var1.getArray_as_ArrayList();
        return this.minus(var2);
    }

    public ArrayMaths minus(Stat var1) {
        ArrayList var2 = var1.getArray_as_ArrayList();
        return this.minus(var2);
    }

    public ArrayMaths times(double var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var3 = new ArrayMaths();
        var3.array = new ArrayList();
        var3.length = this.length;
        int var5;
        BigInteger var6;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                double[] var4 = this.getArray_as_double();

                for(var5 = 0; var5 < this.length; ++var5) {
                    var3.array.add(new Double(var4[var5] * var1));
                }

                var3.type = 0;
                break;
            case 12:
                for(var5 = 0; var5 < this.length; ++var5) {
                    BigDecimal var9 = (BigDecimal)((BigDecimal)this.array.get(var5));
                    var9 = var9.multiply(new BigDecimal(var1));
                    var3.array.add(var9);
                    var6 = null;
                }

                var3.type = this.type;
                break;
            case 13:
                for(var5 = 0; var5 < this.length; ++var5) {
                    var6 = (BigInteger)((BigInteger)this.array.get(var5));
                    BigDecimal var7 = (new BigDecimal(var6)).multiply(new BigDecimal(var1));
                    var3.array.add(var7);
                    var6 = null;
                    var7 = null;
                }

                var3.type = 12;
                break;
            case 14:
                for(var5 = 0; var5 < this.length; ++var5) {
                    var3.array.add(((Complex)this.array.get(var5)).times(var1));
                }

                var3.type = this.type;
                break;
            case 15:
                for(var5 = 0; var5 < this.length; ++var5) {
                    var3.array.add(((Phasor)this.array.get(var5)).times(new Complex(var1)));
                }

                var3.type = this.type;
                break;
            case 16:
                throw new IllegalArgumentException("a double or float cannot be multiplied by a char");
            case 17:
                throw new IllegalArgumentException("a double or float cannot be multiplied by a Character");
            case 18:
                throw new IllegalArgumentException("a double or float cannot be multiplied by a String");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        int[] var8 = new int[2];
        findMinMax(var3.getArray_as_Object(), var3.minmax, var8, var3.typeName, var3.type);
        var3.maxIndex = var8[0];
        var3.minIndex = var8[1];
        Conv.restoreMessages();
        return var3;
    }

    public ArrayMaths times(Double var1) {
        return this.times(var1);
    }

    public ArrayMaths times(float var1) {
        double var2 = (double)var1;
        return this.times(var2);
    }

    public ArrayMaths times(Float var1) {
        return this.times(var1);
    }

    public ArrayMaths times(long var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var3 = new ArrayMaths();
        var3.array = new ArrayList();
        var3.length = this.length;
        int var8;
        BigInteger var9;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
                double[] var4 = this.getArray_as_double();

                for(int var12 = 0; var12 < this.length; ++var12) {
                    var3.array.add(new Double(var4[var12] * (double)var1));
                }

                var3.type = 0;
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                long var5 = this.getMaximum_as_long();
                long[] var7 = this.getArray_as_long();
                if (9223372036854775807L - var5 >= var1) {
                    for(var8 = 0; var8 < this.length; ++var8) {
                        var3.array.add(new Long(var7[var8] * var1));
                    }

                    var3.type = 4;
                } else {
                    for(var8 = 0; var8 < this.length; ++var8) {
                        var3.array.add(new Double((double)var7[var8] * (double)var1));
                    }

                    var3.type = 0;
                }
                break;
            case 12:
                for(var8 = 0; var8 < this.length; ++var8) {
                    BigDecimal var13 = (BigDecimal)((BigDecimal)this.array.get(var8));
                    var13 = var13.multiply(new BigDecimal((double)var1));
                    var3.array.add(var13);
                    var9 = null;
                }

                var3.type = 12;
                break;
            case 13:
                for(var8 = 0; var8 < this.length; ++var8) {
                    var9 = (BigInteger)((BigInteger)this.array.get(var8));
                    BigInteger var10 = var9.multiply(new BigInteger(Long.toString(var1)));
                    var3.array.add(var10);
                    var9 = null;
                    var10 = null;
                }

                var3.type = 13;
                break;
            case 14:
                for(var8 = 0; var8 < this.length; ++var8) {
                    var3.array.add(((Complex)this.array.get(var8)).times((double)var1));
                }

                var3.type = this.type;
                break;
            case 15:
                for(var8 = 0; var8 < this.length; ++var8) {
                    var3.array.add(((Phasor)this.array.get(var8)).times(new Phasor((double)var1)));
                }

                var3.type = this.type;
                break;
            case 16:
                throw new IllegalArgumentException("a long cannot be multiplied by a char");
            case 17:
                throw new IllegalArgumentException("a long cannot be multiplied by a Character");
            case 18:
                throw new IllegalArgumentException("a long cannot be multiplied by a String");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        int[] var11 = new int[2];
        findMinMax(var3.getArray_as_Object(), var3.minmax, var11, var3.typeName, var3.type);
        var3.maxIndex = var11[0];
        var3.minIndex = var11[1];
        Conv.restoreMessages();
        return var3;
    }

    public ArrayMaths times(Long var1) {
        long var2 = var1;
        return this.times(var2);
    }

    public ArrayMaths times(int var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var2 = new ArrayMaths();
        var2.array = new ArrayList();
        var2.length = this.length;
        int var7;
        int var9;
        BigInteger var10;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
                double[] var3 = this.getArray_as_double();

                for(int var13 = 0; var13 < this.length; ++var13) {
                    var2.array.add(new Double(var3[var13] * (double)var1));
                }

                var2.type = 0;
                break;
            case 4:
                long var4 = this.getMaximum_as_long();
                long[] var6 = this.getArray_as_long();
                if (9223372036854775807L - var4 >= (long)var1) {
                    for(var7 = 0; var7 < this.length; ++var7) {
                        var2.array.add(new Long(var6[var7] * (long)var1));
                    }

                    var2.type = 4;
                } else {
                    for(var7 = 0; var7 < this.length; ++var7) {
                        var2.array.add(new Double((double)var6[var7] * (double)var1));
                    }

                    var2.type = 0;
                }
                break;
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                var7 = this.getMaximum_as_int();
                int[] var8 = this.getArray_as_int();
                if (2147483647 - var7 >= var1) {
                    for(var9 = 0; var9 < this.length; ++var9) {
                        var2.array.add(new Integer(var8[var9] * var1));
                    }

                    var2.type = 6;
                } else {
                    for(var9 = 0; var9 < this.length; ++var9) {
                        var2.array.add(new Double((double)var8[var9] * (double)var1));
                    }

                    var2.type = 0;
                }
                break;
            case 12:
                for(var9 = 0; var9 < this.length; ++var9) {
                    BigDecimal var14 = (BigDecimal)((BigDecimal)this.array.get(var9));
                    var14 = var14.multiply(new BigDecimal((double)var1));
                    var2.array.add(var14);
                    var10 = null;
                }

                var2.type = 12;
                break;
            case 13:
                for(var9 = 0; var9 < this.length; ++var9) {
                    var10 = (BigInteger)((BigInteger)this.array.get(var9));
                    BigInteger var11 = var10.multiply(new BigInteger(Integer.toString(var1)));
                    var2.array.add(var11);
                    var10 = null;
                    var11 = null;
                }

                var2.type = 13;
                break;
            case 14:
                for(var9 = 0; var9 < this.length; ++var9) {
                    var2.array.add(((Complex)this.array.get(var9)).times((double)var1));
                }

                var2.type = this.type;
                break;
            case 15:
                for(var9 = 0; var9 < this.length; ++var9) {
                    var2.array.add(((Phasor)this.array.get(var9)).times(new Phasor((double)var1)));
                }

                var2.type = this.type;
                break;
            case 16:
                throw new IllegalArgumentException("an int cannot be multiplied by a char");
            case 17:
                throw new IllegalArgumentException("an int cannot be multiplied by a Character");
            case 18:
                throw new IllegalArgumentException("an int cannot be multiplied by a String");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        int[] var12 = new int[2];
        findMinMax(var2.getArray_as_Object(), var2.minmax, var12, var2.typeName, var2.type);
        var2.maxIndex = var12[0];
        var2.minIndex = var12[1];
        Conv.restoreMessages();
        return var2;
    }

    public ArrayMaths times(Integer var1) {
        int var2 = var1;
        return this.times(var2);
    }

    public ArrayMaths times(short var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var2 = new ArrayMaths();
        var2.array = new ArrayList();
        var2.length = this.length;
        int var9;
        BigInteger var10;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
                double[] var3 = this.getArray_as_double();

                for(int var13 = 0; var13 < this.length; ++var13) {
                    var2.array.add(new Double(var3[var13] * (double)var1));
                }

                var2.type = 0;
                break;
            case 4:
                long var4 = this.getMaximum_as_long();
                long[] var6 = this.getArray_as_long();
                int var14;
                if (9223372036854775807L - var4 >= (long)var1) {
                    for(var14 = 0; var14 < this.length; ++var14) {
                        var2.array.add(new Long(var6[var14] * (long)var1));
                    }

                    var2.type = 4;
                } else {
                    for(var14 = 0; var14 < this.length; ++var14) {
                        var2.array.add(new Double((double)var6[var14] * (double)var1));
                    }

                    var2.type = 0;
                }
                break;
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                short var7 = this.getMaximum_as_short();
                short[] var8 = this.getArray_as_short();
                if (2147483647 - var7 >= var1) {
                    for(var9 = 0; var9 < this.length; ++var9) {
                        var2.array.add(new Integer(var8[var9] * var1));
                    }

                    var2.type = 6;
                } else {
                    for(var9 = 0; var9 < this.length; ++var9) {
                        var2.array.add(new Double((double)var8[var9] * (double)var1));
                    }

                    var2.type = 0;
                }
                break;
            case 12:
                for(var9 = 0; var9 < this.length; ++var9) {
                    BigDecimal var15 = (BigDecimal)((BigDecimal)this.array.get(var9));
                    var15 = var15.multiply(new BigDecimal((double)var1));
                    var2.array.add(var15);
                    var10 = null;
                }

                var2.type = 12;
                break;
            case 13:
                for(var9 = 0; var9 < this.length; ++var9) {
                    var10 = (BigInteger)((BigInteger)this.array.get(var9));
                    BigInteger var11 = var10.multiply(new BigInteger(Integer.toString(var1)));
                    var2.array.add(var11);
                    var10 = null;
                    var11 = null;
                }

                var2.type = 13;
                break;
            case 14:
                for(var9 = 0; var9 < this.length; ++var9) {
                    var2.array.add(((Complex)this.array.get(var9)).times((double)var1));
                }

                var2.type = this.type;
                break;
            case 15:
                for(var9 = 0; var9 < this.length; ++var9) {
                    var2.array.add(((Phasor)this.array.get(var9)).times(new Phasor((double)var1)));
                }

                var2.type = this.type;
                break;
            case 16:
                throw new IllegalArgumentException("a short cannot be multiplied by a char");
            case 17:
                throw new IllegalArgumentException("a short cannot be multiplied by a Character");
            case 18:
                throw new IllegalArgumentException("a short cannot be multiplied by a String");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        int[] var12 = new int[2];
        findMinMax(var2.getArray_as_Object(), var2.minmax, var12, var2.typeName, var2.type);
        var2.maxIndex = var12[0];
        var2.minIndex = var12[1];
        Conv.restoreMessages();
        return var2;
    }

    public ArrayMaths times(Short var1) {
        short var2 = var1;
        return this.times(var2);
    }

    public ArrayMaths times(BigDecimal var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var2 = new ArrayMaths();
        var2.array = new ArrayList();
        var2.length = this.length;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
                BigDecimal[] var3 = this.getArray_as_BigDecimal();

                for(int var8 = 0; var8 < this.length; ++var8) {
                    var2.array.add(var3[var8].multiply(var1));
                }

                var2.type = 12;
                break;
            case 14:
                Complex[] var4 = this.getArray_as_Complex();

                for(int var9 = 0; var9 < this.length; ++var9) {
                    var2.array.add(var4[var9].times(var1.doubleValue()));
                }

                var2.type = this.type;
                break;
            case 15:
                Phasor[] var5 = this.getArray_as_Phasor();

                for(int var6 = 0; var6 < this.length; ++var6) {
                    var2.array.add(var5[var6].times(new Phasor(var1.doubleValue())));
                }

                var2.type = this.type;
                break;
            case 16:
                throw new IllegalArgumentException("a BigDecimal cannot be multiplied by a char");
            case 17:
                throw new IllegalArgumentException("a BigDecimal cannot be multiplied by a Character");
            case 18:
                throw new IllegalArgumentException("a BigDecimal cannot be multiplied by a String");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        int[] var7 = new int[2];
        findMinMax(var2.getArray_as_Object(), var2.minmax, var7, var2.typeName, var2.type);
        var2.maxIndex = var7[0];
        var2.minIndex = var7[1];
        Conv.restoreMessages();
        return var2;
    }

    public ArrayMaths times(byte var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var2 = new ArrayMaths();
        var2.array = new ArrayList();
        var2.length = this.length;
        int var9;
        BigInteger var10;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
                double[] var3 = this.getArray_as_double();

                for(int var13 = 0; var13 < this.length; ++var13) {
                    var2.array.add(new Double(var3[var13] * (double)var1));
                }

                var2.type = 0;
                break;
            case 4:
                long var4 = this.getMaximum_as_long();
                long[] var6 = this.getArray_as_long();
                int var14;
                if (9223372036854775807L - var4 >= (long)var1) {
                    for(var14 = 0; var14 < this.length; ++var14) {
                        var2.array.add(new Long(var6[var14] * (long)var1));
                    }

                    var2.type = 4;
                } else {
                    for(var14 = 0; var14 < this.length; ++var14) {
                        var2.array.add(new Double((double)var6[var14] * (double)var1));
                    }

                    var2.type = 0;
                }
                break;
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                byte var7 = this.getMaximum_as_byte();
                byte[] var8 = this.getArray_as_byte();
                if (2147483647 - var7 >= var1) {
                    for(var9 = 0; var9 < this.length; ++var9) {
                        var2.array.add(new Integer(var8[var9] * var1));
                    }

                    var2.type = 6;
                } else {
                    for(var9 = 0; var9 < this.length; ++var9) {
                        var2.array.add(new Double((double)var8[var9] * (double)var1));
                    }

                    var2.type = 0;
                }
                break;
            case 12:
                for(var9 = 0; var9 < this.length; ++var9) {
                    BigDecimal var15 = (BigDecimal)((BigDecimal)this.array.get(var9));
                    var15 = var15.multiply(new BigDecimal((double)var1));
                    var2.array.add(var15);
                    var10 = null;
                }

                var2.type = 12;
                break;
            case 13:
                for(var9 = 0; var9 < this.length; ++var9) {
                    var10 = (BigInteger)((BigInteger)this.array.get(var9));
                    BigInteger var11 = var10.multiply(new BigInteger(Integer.toString(var1)));
                    var2.array.add(var11);
                    var10 = null;
                    var11 = null;
                }

                var2.type = 13;
                break;
            case 14:
                for(var9 = 0; var9 < this.length; ++var9) {
                    var2.array.add(((Complex)this.array.get(var9)).times((double)var1));
                }

                var2.type = this.type;
                break;
            case 15:
                for(var9 = 0; var9 < this.length; ++var9) {
                    var2.array.add(((Phasor)this.array.get(var9)).times(new Phasor((double)var1)));
                }

                var2.type = this.type;
                break;
            case 16:
                throw new IllegalArgumentException("a byte cannot be multiplied by a char");
            case 17:
                throw new IllegalArgumentException("a byte cannot be multiplied by a Character");
            case 18:
                throw new IllegalArgumentException("a byte cannot be multiplied by a String");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        int[] var12 = new int[2];
        findMinMax(var2.getArray_as_Object(), var2.minmax, var12, var2.typeName, var2.type);
        var2.maxIndex = var12[0];
        var2.minIndex = var12[1];
        Conv.restoreMessages();
        return var2;
    }

    public ArrayMaths times(Byte var1) {
        byte var2 = var1;
        return this.times(var2);
    }

    public ArrayMaths times(BigInteger var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var2 = new ArrayMaths();
        var2.array = new ArrayList();
        var2.length = this.length;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 12:
                BigDecimal var3 = Conv.convert_BigInteger_to_BigDecimal(var1);
                BigDecimal[] var4 = this.getArray_as_BigDecimal();

                for(int var10 = 0; var10 < this.length; ++var10) {
                    var2.array.add(var4[var10].add(var3));
                }

                var2.type = 12;
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 13:
                BigInteger[] var5 = this.getArray_as_BigInteger();

                for(int var11 = 0; var11 < this.length; ++var11) {
                    var2.array.add(var5[var11].multiply(var1));
                }

                var2.type = 13;
                break;
            case 14:
                Complex[] var6 = this.getArray_as_Complex();

                for(int var12 = 0; var12 < this.length; ++var12) {
                    var2.array.add(var6[var12].times(Conv.convert_BigInteger_to_double(var1)));
                }

                var2.type = this.type;
                break;
            case 15:
                Phasor[] var7 = this.getArray_as_Phasor();

                for(int var8 = 0; var8 < this.length; ++var8) {
                    var2.array.add(var7[var8].times(new Phasor(Conv.convert_BigInteger_to_double(var1))));
                }

                var2.type = this.type;
                break;
            case 16:
                throw new IllegalArgumentException("a BigInteger cannot be multiplied by a char");
            case 17:
                throw new IllegalArgumentException("a BigInteger cannot be multiplied by a Character");
            case 18:
                throw new IllegalArgumentException("a BigInteger cannot be multiplied by a String");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        int[] var9 = new int[2];
        findMinMax(var2.getArray_as_Object(), var2.minmax, var9, var2.typeName, var2.type);
        var2.maxIndex = var9[0];
        var2.minIndex = var9[1];
        Conv.restoreMessages();
        return var2;
    }

    public ArrayMaths times(Complex var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var2 = new ArrayMaths();
        var2.array = new ArrayList();
        var2.length = this.length;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
                Complex[] var3 = this.getArray_as_Complex();

                for(int var6 = 0; var6 < this.length; ++var6) {
                    var2.array.add(var3[var6].times(var1));
                }

                var2.type = 14;
                break;
            case 15:
                Phasor[] var4 = this.getArray_as_Phasor();

                for(int var5 = 0; var5 < this.length; ++var5) {
                    var2.array.add(var4[var5].times(Conv.convert_Complex_to_Phasor(var1)));
                }

                var2.type = this.type;
                break;
            case 16:
                throw new IllegalArgumentException("a Complex cannot be multiplied by a char");
            case 17:
                throw new IllegalArgumentException("a Complex cannot be multiplied by a Character");
            case 18:
                throw new IllegalArgumentException("a Complex cannot be multiplied by a String");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var2;
    }

    public ArrayMaths times(Phasor var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var2 = new ArrayMaths();
        var2.array = new ArrayList();
        var2.length = this.length;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 15:
                Phasor[] var3 = this.getArray_as_Phasor();

                for(int var6 = 0; var6 < this.length; ++var6) {
                    var2.array.add(var3[var6].times(var1));
                }

                var2.type = 15;
                break;
            case 14:
                Complex[] var4 = this.getArray_as_Complex();

                for(int var5 = 0; var5 < this.length; ++var5) {
                    var2.array.add(var4[var5].times(Conv.convert_Phasor_to_Complex(var1)));
                }

                var2.type = this.type;
                break;
            case 16:
                throw new IllegalArgumentException("a Phasor cannot be multiplied by a char");
            case 17:
                throw new IllegalArgumentException("a Phasor cannot be multiplied by a Character");
            case 18:
                throw new IllegalArgumentException("a Phasor cannot be multiplied by a String");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var2;
    }

    public ArrayMaths over(double var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var3 = new ArrayMaths();
        var3.array = new ArrayList();
        var3.length = this.length;
        int var5;
        BigInteger var6;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                double[] var4 = this.getArray_as_double();

                for(var5 = 0; var5 < this.length; ++var5) {
                    var3.array.add(new Double(var4[var5] / var1));
                }

                var3.type = 0;
                break;
            case 12:
                for(var5 = 0; var5 < this.length; ++var5) {
                    BigDecimal var9 = (BigDecimal)((BigDecimal)this.array.get(var5));
                    var9 = var9.divide(new BigDecimal(var1), 4);
                    var3.array.add(var9);
                    var6 = null;
                }

                var3.type = this.type;
                break;
            case 13:
                for(var5 = 0; var5 < this.length; ++var5) {
                    var6 = (BigInteger)((BigInteger)this.array.get(var5));
                    BigDecimal var7 = (new BigDecimal(var6)).divide(new BigDecimal(var1), 4);
                    var3.array.add(var7);
                    var6 = null;
                    var7 = null;
                }

                var3.type = 12;
                break;
            case 14:
                for(var5 = 0; var5 < this.length; ++var5) {
                    var3.array.add(((Complex)this.array.get(var5)).over(var1));
                }

                var3.type = this.type;
                break;
            case 15:
                for(var5 = 0; var5 < this.length; ++var5) {
                    var3.array.add(((Phasor)this.array.get(var5)).over(new Complex(var1)));
                }

                var3.type = this.type;
                break;
            case 16:
                throw new IllegalArgumentException("a double or float cannot be divided by a char");
            case 17:
                throw new IllegalArgumentException("a double or float cannot be divided by a Character");
            case 18:
                throw new IllegalArgumentException("a double or float cannot be divided by a String");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        int[] var8 = new int[2];
        findMinMax(var3.getArray_as_Object(), var3.minmax, var8, var3.typeName, var3.type);
        var3.maxIndex = var8[0];
        var3.minIndex = var8[1];
        Conv.restoreMessages();
        return var3;
    }

    public ArrayMaths over(Double var1) {
        return this.over(var1);
    }

    public ArrayMaths over(float var1) {
        double var2 = (double)var1;
        return this.over(var2);
    }

    public ArrayMaths over(Float var1) {
        return this.over(var1);
    }

    public ArrayMaths over(long var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var3 = new ArrayMaths();
        var3.array = new ArrayList();
        var3.length = this.length;
        int var8;
        BigInteger var9;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
                double[] var4 = this.getArray_as_double();

                for(int var12 = 0; var12 < this.length; ++var12) {
                    var3.array.add(new Double(var4[var12] / (double)var1));
                }

                var3.type = 0;
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                long var5 = this.getMaximum_as_long();
                long[] var7 = this.getArray_as_long();
                if (9223372036854775807L - var5 >= var1) {
                    for(var8 = 0; var8 < this.length; ++var8) {
                        var3.array.add(new Long(var7[var8] / var1));
                    }

                    var3.type = 4;
                } else {
                    for(var8 = 0; var8 < this.length; ++var8) {
                        var3.array.add(new Double((double)var7[var8] / (double)var1));
                    }

                    var3.type = 0;
                }
                break;
            case 12:
                for(var8 = 0; var8 < this.length; ++var8) {
                    BigDecimal var13 = (BigDecimal)((BigDecimal)this.array.get(var8));
                    var13 = var13.divide(new BigDecimal((double)var1), 4);
                    var3.array.add(var13);
                    var9 = null;
                }

                var3.type = 12;
                break;
            case 13:
                for(var8 = 0; var8 < this.length; ++var8) {
                    var9 = (BigInteger)((BigInteger)this.array.get(var8));
                    BigInteger var10 = var9.divide(new BigInteger(Long.toString(var1)));
                    var3.array.add(var10);
                    var9 = null;
                    var10 = null;
                }

                var3.type = 13;
                break;
            case 14:
                for(var8 = 0; var8 < this.length; ++var8) {
                    var3.array.add(((Complex)this.array.get(var8)).over((double)var1));
                }

                var3.type = this.type;
                break;
            case 15:
                for(var8 = 0; var8 < this.length; ++var8) {
                    var3.array.add(((Phasor)this.array.get(var8)).over(new Phasor((double)var1)));
                }

                var3.type = this.type;
                break;
            case 16:
                throw new IllegalArgumentException("a long cannot be divided by a char");
            case 17:
                throw new IllegalArgumentException("a long cannot be divided by a Character");
            case 18:
                throw new IllegalArgumentException("a long cannot be divided by a String");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        int[] var11 = new int[2];
        findMinMax(var3.getArray_as_Object(), var3.minmax, var11, var3.typeName, var3.type);
        var3.maxIndex = var11[0];
        var3.minIndex = var11[1];
        Conv.restoreMessages();
        return var3;
    }

    public ArrayMaths over(Long var1) {
        long var2 = var1;
        return this.over(var2);
    }

    public ArrayMaths over(int var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var2 = new ArrayMaths();
        var2.array = new ArrayList();
        var2.length = this.length;
        int var7;
        int var9;
        BigInteger var10;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
                double[] var3 = this.getArray_as_double();

                for(int var13 = 0; var13 < this.length; ++var13) {
                    var2.array.add(new Double(var3[var13] / (double)var1));
                }

                var2.type = 0;
                break;
            case 4:
                long var4 = this.getMaximum_as_long();
                long[] var6 = this.getArray_as_long();

                for(var7 = 0; var7 < this.length; ++var7) {
                    var2.array.add(new Long(var6[var7] / (long)var1));
                }

                var2.type = 4;
                break;
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                var7 = this.getMaximum_as_int();
                int[] var8 = this.getArray_as_int();

                for(var9 = 0; var9 < this.length; ++var9) {
                    var2.array.add(new Integer(var8[var9] / var1));
                }

                var2.type = 6;
                break;
            case 12:
                for(var9 = 0; var9 < this.length; ++var9) {
                    BigDecimal var14 = (BigDecimal)((BigDecimal)this.array.get(var9));
                    var14 = var14.divide(new BigDecimal((double)var1), 4);
                    var2.array.add(var14);
                    var10 = null;
                }

                var2.type = 12;
                break;
            case 13:
                for(var9 = 0; var9 < this.length; ++var9) {
                    var10 = (BigInteger)((BigInteger)this.array.get(var9));
                    BigInteger var11 = var10.divide(new BigInteger(Integer.toString(var1)));
                    var2.array.add(var11);
                    var10 = null;
                    var11 = null;
                }

                var2.type = 13;
                break;
            case 14:
                for(var9 = 0; var9 < this.length; ++var9) {
                    var2.array.add(((Complex)this.array.get(var9)).over((double)var1));
                }

                var2.type = this.type;
                break;
            case 15:
                for(var9 = 0; var9 < this.length; ++var9) {
                    var2.array.add(((Phasor)this.array.get(var9)).over(new Phasor((double)var1)));
                }

                var2.type = this.type;
                break;
            case 16:
                throw new IllegalArgumentException("an int cannot be divided by a char");
            case 17:
                throw new IllegalArgumentException("an int cannot be divided by a Character");
            case 18:
                throw new IllegalArgumentException("an int cannot be divided by a String");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        int[] var12 = new int[2];
        findMinMax(var2.getArray_as_Object(), var2.minmax, var12, var2.typeName, var2.type);
        var2.maxIndex = var12[0];
        var2.minIndex = var12[1];
        Conv.restoreMessages();
        return var2;
    }

    public ArrayMaths over(Integer var1) {
        int var2 = var1;
        return this.over(var2);
    }

    public ArrayMaths over(short var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var2 = new ArrayMaths();
        var2.array = new ArrayList();
        var2.length = this.length;
        int var9;
        BigInteger var10;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
                double[] var3 = this.getArray_as_double();

                for(int var13 = 0; var13 < this.length; ++var13) {
                    var2.array.add(new Double(var3[var13] / (double)var1));
                }

                var2.type = 0;
                break;
            case 4:
                long var4 = this.getMaximum_as_long();
                long[] var6 = this.getArray_as_long();

                for(int var14 = 0; var14 < this.length; ++var14) {
                    var2.array.add(new Long(var6[var14] / (long)var1));
                }

                var2.type = 4;
                break;
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                short var7 = this.getMaximum_as_short();
                short[] var8 = this.getArray_as_short();

                for(var9 = 0; var9 < this.length; ++var9) {
                    var2.array.add(new Integer(var8[var9] / var1));
                }

                var2.type = 6;
                break;
            case 12:
                for(var9 = 0; var9 < this.length; ++var9) {
                    BigDecimal var15 = (BigDecimal)((BigDecimal)this.array.get(var9));
                    var15 = var15.divide(new BigDecimal((double)var1), 4);
                    var2.array.add(var15);
                    var10 = null;
                }

                var2.type = 12;
                break;
            case 13:
                for(var9 = 0; var9 < this.length; ++var9) {
                    var10 = (BigInteger)((BigInteger)this.array.get(var9));
                    BigInteger var11 = var10.divide(new BigInteger(Integer.toString(var1)));
                    var2.array.add(var11);
                    var10 = null;
                    var11 = null;
                }

                var2.type = 13;
                break;
            case 14:
                for(var9 = 0; var9 < this.length; ++var9) {
                    var2.array.add(((Complex)this.array.get(var9)).over((double)var1));
                }

                var2.type = this.type;
                break;
            case 15:
                for(var9 = 0; var9 < this.length; ++var9) {
                    var2.array.add(((Phasor)this.array.get(var9)).over(new Phasor((double)var1)));
                }

                var2.type = this.type;
                break;
            case 16:
                throw new IllegalArgumentException("a short cannot be divided by a char");
            case 17:
                throw new IllegalArgumentException("a short cannot be divided by a Character");
            case 18:
                throw new IllegalArgumentException("a short cannot be divided by a String");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        int[] var12 = new int[2];
        findMinMax(var2.getArray_as_Object(), var2.minmax, var12, var2.typeName, var2.type);
        var2.maxIndex = var12[0];
        var2.minIndex = var12[1];
        Conv.restoreMessages();
        return var2;
    }

    public ArrayMaths over(Short var1) {
        short var2 = var1;
        return this.over(var2);
    }

    public ArrayMaths over(BigDecimal var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var2 = new ArrayMaths();
        var2.array = new ArrayList();
        var2.length = this.length;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
                BigDecimal[] var3 = this.getArray_as_BigDecimal();

                for(int var8 = 0; var8 < this.length; ++var8) {
                    var2.array.add(var3[var8].divide(var1, 4));
                }

                var2.type = 12;
                break;
            case 14:
                Complex[] var4 = this.getArray_as_Complex();

                for(int var9 = 0; var9 < this.length; ++var9) {
                    var2.array.add(var4[var9].over(var1.doubleValue()));
                }

                var2.type = this.type;
                break;
            case 15:
                Phasor[] var5 = this.getArray_as_Phasor();

                for(int var6 = 0; var6 < this.length; ++var6) {
                    var2.array.add(var5[var6].over(new Phasor(var1.doubleValue())));
                }

                var2.type = this.type;
                break;
            case 16:
                throw new IllegalArgumentException("a BigDecimal cannot be divided by a char");
            case 17:
                throw new IllegalArgumentException("a BigDecimal cannot be divided by a Character");
            case 18:
                throw new IllegalArgumentException("a BigDecimal cannot be divided by a String");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        int[] var7 = new int[2];
        findMinMax(var2.getArray_as_Object(), var2.minmax, var7, var2.typeName, var2.type);
        var2.maxIndex = var7[0];
        var2.minIndex = var7[1];
        Conv.restoreMessages();
        return var2;
    }

    public ArrayMaths over(byte var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var2 = new ArrayMaths();
        var2.array = new ArrayList();
        var2.length = this.length;
        int var9;
        BigInteger var10;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
                double[] var3 = this.getArray_as_double();

                for(int var13 = 0; var13 < this.length; ++var13) {
                    var2.array.add(new Double(var3[var13] / (double)var1));
                }

                var2.type = 0;
                break;
            case 4:
                long var4 = this.getMaximum_as_long();
                long[] var6 = this.getArray_as_long();

                for(int var14 = 0; var14 < this.length; ++var14) {
                    var2.array.add(new Long(var6[var14] / (long)var1));
                }

                var2.type = 4;
                break;
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                byte var7 = this.getMaximum_as_byte();
                byte[] var8 = this.getArray_as_byte();

                for(var9 = 0; var9 < this.length; ++var9) {
                    var2.array.add(new Double((double)var8[var9] / (double)var1));
                }

                var2.type = 0;
                break;
            case 12:
                for(var9 = 0; var9 < this.length; ++var9) {
                    BigDecimal var15 = (BigDecimal)((BigDecimal)this.array.get(var9));
                    var15 = var15.divide(new BigDecimal((double)var1), 4);
                    var2.array.add(var15);
                    var10 = null;
                }

                var2.type = 12;
                break;
            case 13:
                for(var9 = 0; var9 < this.length; ++var9) {
                    var10 = (BigInteger)((BigInteger)this.array.get(var9));
                    BigInteger var11 = var10.divide(new BigInteger(Integer.toString(var1)));
                    var2.array.add(var11);
                    var10 = null;
                    var11 = null;
                }

                var2.type = 13;
                break;
            case 14:
                for(var9 = 0; var9 < this.length; ++var9) {
                    var2.array.add(((Complex)this.array.get(var9)).over((double)var1));
                }

                var2.type = this.type;
                break;
            case 15:
                for(var9 = 0; var9 < this.length; ++var9) {
                    var2.array.add(((Phasor)this.array.get(var9)).over(new Phasor((double)var1)));
                }

                var2.type = this.type;
                break;
            case 16:
                throw new IllegalArgumentException("a byte cannot be divided by a char");
            case 17:
                throw new IllegalArgumentException("a byte cannot be divided by a Character");
            case 18:
                throw new IllegalArgumentException("a byte cannot be divided by a String");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        int[] var12 = new int[2];
        findMinMax(var2.getArray_as_Object(), var2.minmax, var12, var2.typeName, var2.type);
        var2.maxIndex = var12[0];
        var2.minIndex = var12[1];
        Conv.restoreMessages();
        return var2;
    }

    public ArrayMaths over(Byte var1) {
        byte var2 = var1;
        return this.over(var2);
    }

    public ArrayMaths over(BigInteger var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var2 = new ArrayMaths();
        var2.array = new ArrayList();
        var2.length = this.length;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 12:
                BigDecimal var3 = Conv.convert_BigInteger_to_BigDecimal(var1);
                BigDecimal[] var4 = this.getArray_as_BigDecimal();

                for(int var10 = 0; var10 < this.length; ++var10) {
                    var2.array.add(var4[var10].add(var3));
                }

                var2.type = 12;
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 13:
                BigInteger[] var5 = this.getArray_as_BigInteger();

                for(int var11 = 0; var11 < this.length; ++var11) {
                    var2.array.add(var5[var11].divide(var1));
                }

                var2.type = 13;
                break;
            case 14:
                Complex[] var6 = this.getArray_as_Complex();

                for(int var12 = 0; var12 < this.length; ++var12) {
                    var2.array.add(var6[var12].over(Conv.convert_BigInteger_to_double(var1)));
                }

                var2.type = this.type;
                break;
            case 15:
                Phasor[] var7 = this.getArray_as_Phasor();

                for(int var8 = 0; var8 < this.length; ++var8) {
                    var2.array.add(var7[var8].over(new Phasor(Conv.convert_BigInteger_to_double(var1))));
                }

                var2.type = this.type;
                break;
            case 16:
                throw new IllegalArgumentException("a BigInteger cannot be divided by a char");
            case 17:
                throw new IllegalArgumentException("a BigInteger cannot be divided by a Character");
            case 18:
                throw new IllegalArgumentException("a BigInteger cannot be divided by a String");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        int[] var9 = new int[2];
        findMinMax(var2.getArray_as_Object(), var2.minmax, var9, var2.typeName, var2.type);
        var2.maxIndex = var9[0];
        var2.minIndex = var9[1];
        Conv.restoreMessages();
        return var2;
    }

    public ArrayMaths over(Complex var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var2 = new ArrayMaths();
        var2.array = new ArrayList();
        var2.length = this.length;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
                Complex[] var3 = this.getArray_as_Complex();

                for(int var6 = 0; var6 < this.length; ++var6) {
                    var2.array.add(var3[var6].over(var1));
                }

                var2.type = 14;
                break;
            case 15:
                Phasor[] var4 = this.getArray_as_Phasor();

                for(int var5 = 0; var5 < this.length; ++var5) {
                    var2.array.add(var4[var5].over(Conv.convert_Complex_to_Phasor(var1)));
                }

                var2.type = this.type;
                break;
            case 16:
                throw new IllegalArgumentException("a Complex cannot be divided by a char");
            case 17:
                throw new IllegalArgumentException("a Complex cannot be divided by a Character");
            case 18:
                throw new IllegalArgumentException("a Complex cannot be divided by a String");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var2;
    }

    public ArrayMaths over(Phasor var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var2 = new ArrayMaths();
        var2.array = new ArrayList();
        var2.length = this.length;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 15:
                Phasor[] var3 = this.getArray_as_Phasor();

                for(int var6 = 0; var6 < this.length; ++var6) {
                    var2.array.add(var3[var6].over(var1));
                }

                var2.type = 15;
                break;
            case 14:
                Complex[] var4 = this.getArray_as_Complex();

                for(int var5 = 0; var5 < this.length; ++var5) {
                    var2.array.add(var4[var5].over(Conv.convert_Phasor_to_Complex(var1)));
                }

                var2.type = this.type;
                break;
            case 16:
                throw new IllegalArgumentException("a Phasor cannot be divided by a char");
            case 17:
                throw new IllegalArgumentException("a Phasor cannot be divided by a Character");
            case 18:
                throw new IllegalArgumentException("a Phasor cannot be divided by a String");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var2;
    }

    public ArrayMaths truncate(int var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var2 = new ArrayMaths();
        var2.array = new ArrayList();
        var2.length = this.length;
        switch(this.type) {
            case 0:
            case 1:
                double[] var3 = this.getArray_as_double();

                for(int var17 = 0; var17 < this.length; ++var17) {
                    var2.array.add(new Double(Fmath.truncate(var3[var17], var1)));
                }

                var2.type = this.type;
                break;
            case 2:
            case 3:
                float[] var4 = this.getArray_as_float();

                for(int var18 = 0; var18 < this.length; ++var18) {
                    var2.array.add(new Float(Fmath.truncate(var4[var18], var1)));
                }

                var2.type = this.type;
                break;
            case 4:
            case 5:
                long[] var5 = this.getArray_as_long();

                for(int var19 = 0; var19 < this.length; ++var19) {
                    var2.array.add(new Long(var5[var19]));
                }

                var2.type = this.type;
                break;
            case 6:
            case 7:
                int[] var6 = this.getArray_as_int();

                for(int var20 = 0; var20 < this.length; ++var20) {
                    var2.array.add(new Long((long)var6[var20]));
                }

                var2.type = this.type;
                break;
            case 8:
            case 9:
                short[] var7 = this.getArray_as_short();

                for(int var21 = 0; var21 < this.length; ++var21) {
                    var2.array.add(new Short(var7[var21]));
                }

                var2.type = this.type;
                break;
            case 10:
            case 11:
                byte[] var8 = this.getArray_as_byte();

                for(int var22 = 0; var22 < this.length; ++var22) {
                    var2.array.add(new Byte(var8[var22]));
                }

                var2.type = this.type;
                break;
            case 12:
                BigDecimal[] var9 = this.getArray_as_BigDecimal();

                for(int var23 = 0; var23 < this.length; ++var23) {
                    var2.array.add(var9[var23].setScale(var1, 4));
                }

                var2.type = this.type;
                break;
            case 13:
                BigInteger[] var10 = this.getArray_as_BigInteger();

                for(int var24 = 0; var24 < this.length; ++var24) {
                    var2.array.add(var10[var24]);
                }

                var2.type = this.type;
                break;
            case 14:
                Complex[] var11 = this.getArray_as_Complex();

                for(int var25 = 0; var25 < this.length; ++var25) {
                    var2.array.add(Complex.truncate(var11[var25], var1));
                }

                var2.type = this.type;
                break;
            case 15:
                Phasor[] var12 = this.getArray_as_Phasor();

                for(int var26 = 0; var26 < this.length; ++var26) {
                    var2.array.add(Phasor.truncate(var12[var26], var1));
                }

                var2.type = this.type;
                break;
            case 16:
            case 17:
                char[] var13 = this.getArray_as_char();

                for(int var27 = 0; var27 < this.length; ++var27) {
                    var2.array.add(new Character(var13[var27]));
                }

                var2.type = this.type;
                break;
            case 18:
                String[] var14 = this.getArray_as_String();

                for(int var15 = 0; var15 < this.length; ++var15) {
                    var2.array.add(var14[var15]);
                }

                var2.type = this.type;
                break;
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        int[] var16 = new int[2];
        findMinMax(var2.getArray_as_Object(), var2.minmax, var16, var2.typeName, var2.type);
        var2.maxIndex = var16[0];
        var2.minIndex = var16[1];
        Conv.restoreMessages();
        return var2;
    }

    public ArrayMaths floor() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var1 = new ArrayMaths();
        var1.array = new ArrayList();
        var1.length = this.length;
        switch(this.type) {
            case 0:
            case 1:
                double[] var2 = this.getArray_as_double();

                for(int var16 = 0; var16 < this.length; ++var16) {
                    var1.array.add(new Double(Math.floor(var2[var16])));
                }

                var1.type = this.type;
                break;
            case 2:
            case 3:
                float[] var3 = this.getArray_as_float();

                for(int var17 = 0; var17 < this.length; ++var17) {
                    var1.array.add(new Float(Math.floor((double)var3[var17])));
                }

                var1.type = this.type;
                break;
            case 4:
            case 5:
                long[] var4 = this.getArray_as_long();

                for(int var18 = 0; var18 < this.length; ++var18) {
                    var1.array.add(new Long(var4[var18]));
                }

                var1.type = this.type;
                break;
            case 6:
            case 7:
                int[] var5 = this.getArray_as_int();

                for(int var19 = 0; var19 < this.length; ++var19) {
                    var1.array.add(new Long((long)var5[var19]));
                }

                var1.type = this.type;
                break;
            case 8:
            case 9:
                short[] var6 = this.getArray_as_short();

                for(int var20 = 0; var20 < this.length; ++var20) {
                    var1.array.add(new Short(var6[var20]));
                }

                var1.type = this.type;
                break;
            case 10:
            case 11:
                byte[] var7 = this.getArray_as_byte();

                for(int var21 = 0; var21 < this.length; ++var21) {
                    var1.array.add(new Byte(var7[var21]));
                }

                var1.type = this.type;
                break;
            case 12:
                BigDecimal[] var8 = this.getArray_as_BigDecimal();

                for(int var22 = 0; var22 < this.length; ++var22) {
                    var1.array.add(var8[var22].setScale(0, 1));
                }

                var1.type = this.type;
                break;
            case 13:
                BigInteger[] var9 = this.getArray_as_BigInteger();

                for(int var23 = 0; var23 < this.length; ++var23) {
                    var1.array.add(var9[var23]);
                }

                var1.type = this.type;
                break;
            case 14:
                Complex[] var10 = this.getArray_as_Complex();

                for(int var24 = 0; var24 < this.length; ++var24) {
                    var1.array.add(new Complex(Math.floor(var10[var24].getReal()), Math.floor(var10[var24].getImag())));
                }

                var1.type = this.type;
                break;
            case 15:
                Phasor[] var11 = this.getArray_as_Phasor();

                for(int var25 = 0; var25 < this.length; ++var25) {
                    var1.array.add(new Phasor(Math.floor(var11[var25].getMagnitude()), Math.floor(var11[var25].getPhaseInDegrees())));
                }

                var1.type = this.type;
                break;
            case 16:
            case 17:
                char[] var12 = this.getArray_as_char();

                for(int var26 = 0; var26 < this.length; ++var26) {
                    var1.array.add(new Character(var12[var26]));
                }

                var1.type = this.type;
                break;
            case 18:
                String[] var13 = this.getArray_as_String();

                for(int var14 = 0; var14 < this.length; ++var14) {
                    var1.array.add(var13[var14]);
                }

                var1.type = this.type;
                break;
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        int[] var15 = new int[2];
        findMinMax(var1.getArray_as_Object(), var1.minmax, var15, var1.typeName, var1.type);
        var1.maxIndex = var15[0];
        var1.minIndex = var15[1];
        Conv.restoreMessages();
        return var1;
    }

    public ArrayMaths ceil() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var1 = new ArrayMaths();
        var1.array = new ArrayList();
        var1.length = this.length;
        switch(this.type) {
            case 0:
            case 1:
                double[] var2 = this.getArray_as_double();

                for(int var16 = 0; var16 < this.length; ++var16) {
                    var1.array.add(new Double(Math.ceil(var2[var16])));
                }

                var1.type = this.type;
                break;
            case 2:
            case 3:
                float[] var3 = this.getArray_as_float();

                for(int var17 = 0; var17 < this.length; ++var17) {
                    var1.array.add(new Float(Math.ceil((double)var3[var17])));
                }

                var1.type = this.type;
                break;
            case 4:
            case 5:
                long[] var4 = this.getArray_as_long();

                for(int var18 = 0; var18 < this.length; ++var18) {
                    var1.array.add(new Long(var4[var18]));
                }

                var1.type = this.type;
                break;
            case 6:
            case 7:
                int[] var5 = this.getArray_as_int();

                for(int var19 = 0; var19 < this.length; ++var19) {
                    var1.array.add(new Long((long)var5[var19]));
                }

                var1.type = this.type;
                break;
            case 8:
            case 9:
                short[] var6 = this.getArray_as_short();

                for(int var20 = 0; var20 < this.length; ++var20) {
                    var1.array.add(new Short(var6[var20]));
                }

                var1.type = this.type;
                break;
            case 10:
            case 11:
                byte[] var7 = this.getArray_as_byte();

                for(int var21 = 0; var21 < this.length; ++var21) {
                    var1.array.add(new Byte(var7[var21]));
                }

                var1.type = this.type;
                break;
            case 12:
                BigDecimal[] var8 = this.getArray_as_BigDecimal();

                for(int var22 = 0; var22 < this.length; ++var22) {
                    var1.array.add(var8[var22].setScale(0, 0));
                }

                var1.type = this.type;
                break;
            case 13:
                BigInteger[] var9 = this.getArray_as_BigInteger();

                for(int var23 = 0; var23 < this.length; ++var23) {
                    var1.array.add(var9[var23]);
                }

                var1.type = this.type;
                break;
            case 14:
                Complex[] var10 = this.getArray_as_Complex();

                for(int var24 = 0; var24 < this.length; ++var24) {
                    var1.array.add(new Complex(Math.ceil(var10[var24].getReal()), Math.ceil(var10[var24].getImag())));
                }

                var1.type = this.type;
                break;
            case 15:
                Phasor[] var11 = this.getArray_as_Phasor();

                for(int var25 = 0; var25 < this.length; ++var25) {
                    var1.array.add(new Phasor(Math.ceil(var11[var25].getMagnitude()), Math.ceil(var11[var25].getPhaseInDegrees())));
                }

                var1.type = this.type;
                break;
            case 16:
            case 17:
                char[] var12 = this.getArray_as_char();

                for(int var26 = 0; var26 < this.length; ++var26) {
                    var1.array.add(new Character(var12[var26]));
                }

                var1.type = this.type;
                break;
            case 18:
                String[] var13 = this.getArray_as_String();

                for(int var14 = 0; var14 < this.length; ++var14) {
                    var1.array.add(var13[var14]);
                }

                var1.type = this.type;
                break;
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        int[] var15 = new int[2];
        findMinMax(var1.getArray_as_Object(), var1.minmax, var15, var1.typeName, var1.type);
        var1.maxIndex = var15[0];
        var1.minIndex = var15[1];
        Conv.restoreMessages();
        return var1;
    }

    public ArrayMaths rint() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var1 = new ArrayMaths();
        var1.array = new ArrayList();
        var1.length = this.length;
        switch(this.type) {
            case 0:
            case 1:
                double[] var2 = this.getArray_as_double();

                for(int var16 = 0; var16 < this.length; ++var16) {
                    var1.array.add(new Double(Math.rint(var2[var16])));
                }

                var1.type = this.type;
                break;
            case 2:
            case 3:
                float[] var3 = this.getArray_as_float();

                for(int var17 = 0; var17 < this.length; ++var17) {
                    var1.array.add(new Float(Math.rint((double)var3[var17])));
                }

                var1.type = this.type;
                break;
            case 4:
            case 5:
                long[] var4 = this.getArray_as_long();

                for(int var18 = 0; var18 < this.length; ++var18) {
                    var1.array.add(new Long(var4[var18]));
                }

                var1.type = this.type;
                break;
            case 6:
            case 7:
                int[] var5 = this.getArray_as_int();

                for(int var19 = 0; var19 < this.length; ++var19) {
                    var1.array.add(new Long((long)var5[var19]));
                }

                var1.type = this.type;
                break;
            case 8:
            case 9:
                short[] var6 = this.getArray_as_short();

                for(int var20 = 0; var20 < this.length; ++var20) {
                    var1.array.add(new Short(var6[var20]));
                }

                var1.type = this.type;
                break;
            case 10:
            case 11:
                byte[] var7 = this.getArray_as_byte();

                for(int var21 = 0; var21 < this.length; ++var21) {
                    var1.array.add(new Byte(var7[var21]));
                }

                var1.type = this.type;
                break;
            case 12:
                BigDecimal[] var8 = this.getArray_as_BigDecimal();

                for(int var22 = 0; var22 < this.length; ++var22) {
                    var1.array.add(var8[var22].setScale(0, 6));
                }

                var1.type = this.type;
                break;
            case 13:
                BigInteger[] var9 = this.getArray_as_BigInteger();

                for(int var23 = 0; var23 < this.length; ++var23) {
                    var1.array.add(var9[var23]);
                }

                var1.type = this.type;
                break;
            case 14:
                Complex[] var10 = this.getArray_as_Complex();

                for(int var24 = 0; var24 < this.length; ++var24) {
                    var1.array.add(new Complex(Math.rint(var10[var24].getReal()), Math.rint(var10[var24].getImag())));
                }

                var1.type = this.type;
                break;
            case 15:
                Phasor[] var11 = this.getArray_as_Phasor();

                for(int var25 = 0; var25 < this.length; ++var25) {
                    var1.array.add(new Phasor(Math.rint(var11[var25].getMagnitude()), Math.rint(var11[var25].getPhaseInDegrees())));
                }

                var1.type = this.type;
                break;
            case 16:
            case 17:
                char[] var12 = this.getArray_as_char();

                for(int var26 = 0; var26 < this.length; ++var26) {
                    var1.array.add(new Character(var12[var26]));
                }

                var1.type = this.type;
                break;
            case 18:
                String[] var13 = this.getArray_as_String();

                for(int var14 = 0; var14 < this.length; ++var14) {
                    var1.array.add(var13[var14]);
                }

                var1.type = this.type;
                break;
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        int[] var15 = new int[2];
        findMinMax(var1.getArray_as_Object(), var1.minmax, var15, var1.typeName, var1.type);
        var1.maxIndex = var15[0];
        var1.minIndex = var15[1];
        Conv.restoreMessages();
        return var1;
    }

    public ArrayMaths reverse() {
        ArrayMaths var1 = new ArrayMaths();
        var1.array = new ArrayList();
        var1.length = this.length;
        var1.type = this.type;
        var1.sortedIndices = new int[this.length];

        for(int var2 = 0; var2 < this.length; ++var2) {
            var1.array.add(this.array.get(this.length - var2 - 1));
            var1.sortedIndices[var2] = this.length - var2 - 1;
        }

        if (this.type != 18) {
            int[] var3 = new int[2];
            findMinMax(var1.getArray_as_Object(), var1.minmax, var3, var1.typeName, var1.type);
            var1.maxIndex = var3[0];
            var1.minIndex = var3[1];
        }

        return var1;
    }

    public ArrayMaths log() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var1 = new ArrayMaths();
        var1.array = new ArrayList();
        var1.length = this.length;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 16:
            case 17:
            case 18:
                double[] var2 = this.getArray_as_double();

                for(int var7 = 0; var7 < this.length; ++var7) {
                    var1.array.add(new Double(Math.log(var2[var7])));
                }

                var1.type = 1;
                break;
            case 14:
                Complex[] var3 = this.getArray_as_Complex();

                for(int var8 = 0; var8 < this.length; ++var8) {
                    var1.array.add(Complex.log(var3[var8]));
                }

                var1.type = this.type;
                break;
            case 15:
                Phasor[] var4 = this.getArray_as_Phasor();

                for(int var5 = 0; var5 < this.length; ++var5) {
                    var1.array.add(Phasor.log(var4[var5]));
                }

                var1.type = 15;
                break;
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        int[] var6 = new int[2];
        findMinMax(var1.getArray_as_Object(), var1.minmax, var6, var1.typeName, var1.type);
        var1.maxIndex = var6[0];
        var1.minIndex = var6[1];
        Conv.restoreMessages();
        return var1;
    }

    public ArrayMaths log2() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var1 = new ArrayMaths();
        var1.array = new ArrayList();
        var1.length = this.length;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 16:
            case 17:
            case 18:
                double[] var2 = this.getArray_as_double();

                for(int var7 = 0; var7 < this.length; ++var7) {
                    var1.array.add(new Double(Fmath.log2(var2[var7])));
                }

                var1.type = 1;
                break;
            case 14:
                Complex[] var3 = this.getArray_as_Complex();

                for(int var8 = 0; var8 < this.length; ++var8) {
                    var1.array.add(Complex.log(var3[var8].over(Math.log(2.0D))));
                }

                var1.type = this.type;
                break;
            case 15:
                Phasor[] var4 = this.getArray_as_Phasor();

                for(int var5 = 0; var5 < this.length; ++var5) {
                    var1.array.add(Phasor.log(var4[var5].over(Math.log(2.0D))));
                }

                var1.type = 15;
                break;
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        int[] var6 = new int[2];
        findMinMax(var1.getArray_as_Object(), var1.minmax, var6, var1.typeName, var1.type);
        var1.maxIndex = var6[0];
        var1.minIndex = var6[1];
        Conv.restoreMessages();
        return var1;
    }

    public ArrayMaths log10() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var1 = new ArrayMaths();
        var1.array = new ArrayList();
        var1.length = this.length;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 16:
            case 17:
            case 18:
                double[] var2 = this.getArray_as_double();

                for(int var7 = 0; var7 < this.length; ++var7) {
                    var1.array.add(new Double(Math.log10(var2[var7])));
                }

                var1.type = 1;
                break;
            case 14:
                Complex[] var3 = this.getArray_as_Complex();

                for(int var8 = 0; var8 < this.length; ++var8) {
                    var1.array.add(Complex.log(var3[var8].over(Math.log(10.0D))));
                }

                var1.type = this.type;
                break;
            case 15:
                Phasor[] var4 = this.getArray_as_Phasor();

                for(int var5 = 0; var5 < this.length; ++var5) {
                    var1.array.add(Phasor.log(var4[var5].over(Math.log(10.0D))));
                }

                var1.type = 15;
                break;
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        int[] var6 = new int[2];
        findMinMax(var1.getArray_as_Object(), var1.minmax, var6, var1.typeName, var1.type);
        var1.maxIndex = var6[0];
        var1.minIndex = var6[1];
        Conv.restoreMessages();
        return var1;
    }

    public ArrayMaths antilog10() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var1 = new ArrayMaths();
        var1.array = new ArrayList();
        var1.length = this.length;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 16:
            case 17:
            case 18:
                double[] var2 = this.getArray_as_double();

                for(int var7 = 0; var7 < this.length; ++var7) {
                    var1.array.add(new Double(Math.pow(10.0D, var2[var7])));
                }

                var1.type = 1;
                break;
            case 14:
                Complex[] var3 = this.getArray_as_Complex();

                for(int var8 = 0; var8 < this.length; ++var8) {
                    var1.array.add(Complex.pow(10.0D, var3[var8]));
                }

                var1.type = this.type;
                break;
            case 15:
                Complex[] var4 = this.getArray_as_Complex();

                for(int var5 = 0; var5 < this.length; ++var5) {
                    var1.array.add(Conv.convert_Complex_to_Phasor(Complex.pow(10.0D, var4[var5])));
                }

                var1.type = 15;
                break;
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        int[] var6 = new int[2];
        findMinMax(var1.getArray_as_Object(), var1.minmax, var6, var1.typeName, var1.type);
        var1.maxIndex = var6[0];
        var1.minIndex = var6[1];
        Conv.restoreMessages();
        return var1;
    }

    public ArrayMaths xLog2x() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var1 = new ArrayMaths();
        var1.array = new ArrayList();
        var1.length = this.length;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 16:
            case 17:
            case 18:
                double[] var2 = this.getArray_as_double();

                for(int var7 = 0; var7 < this.length; ++var7) {
                    var1.array.add(new Double(var2[var7] * Fmath.log2(var2[var7])));
                }

                var1.type = 1;
                break;
            case 14:
                Complex[] var3 = this.getArray_as_Complex();

                for(int var8 = 0; var8 < this.length; ++var8) {
                    var1.array.add(var3[var8].times(Complex.log(var3[var8].over(Math.log(2.0D)))));
                }

                var1.type = this.type;
                break;
            case 15:
                Phasor[] var4 = this.getArray_as_Phasor();

                for(int var5 = 0; var5 < this.length; ++var5) {
                    var1.array.add(var4[var5].times(Phasor.log(var4[var5].over(Math.log(2.0D)))));
                }

                var1.type = 15;
                break;
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        int[] var6 = new int[2];
        findMinMax(var1.getArray_as_Object(), var1.minmax, var6, var1.typeName, var1.type);
        var1.maxIndex = var6[0];
        var1.minIndex = var6[1];
        Conv.restoreMessages();
        return var1;
    }

    public ArrayMaths xLogEx() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var1 = new ArrayMaths();
        var1.array = new ArrayList();
        var1.length = this.length;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 16:
            case 17:
            case 18:
                double[] var2 = this.getArray_as_double();

                for(int var7 = 0; var7 < this.length; ++var7) {
                    var1.array.add(new Double(var2[var7] * Math.log(var2[var7])));
                }

                var1.type = 1;
                break;
            case 14:
                Complex[] var3 = this.getArray_as_Complex();

                for(int var8 = 0; var8 < this.length; ++var8) {
                    var1.array.add(var3[var8].times(Complex.log(var3[var8])));
                }

                var1.type = this.type;
                break;
            case 15:
                Phasor[] var4 = this.getArray_as_Phasor();

                for(int var5 = 0; var5 < this.length; ++var5) {
                    var1.array.add(var4[var5].times(Phasor.log(var4[var5])));
                }

                var1.type = 15;
                break;
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        int[] var6 = new int[2];
        findMinMax(var1.getArray_as_Object(), var1.minmax, var6, var1.typeName, var1.type);
        var1.maxIndex = var6[0];
        var1.minIndex = var6[1];
        Conv.restoreMessages();
        return var1;
    }

    public ArrayMaths xLog10x() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var1 = new ArrayMaths();
        var1.array = new ArrayList();
        var1.length = this.length;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 16:
            case 17:
            case 18:
                double[] var2 = this.getArray_as_double();

                for(int var7 = 0; var7 < this.length; ++var7) {
                    var1.array.add(new Double(var2[var7] * Math.log10(var2[var7])));
                }

                var1.type = 1;
                break;
            case 14:
                Complex[] var3 = this.getArray_as_Complex();

                for(int var8 = 0; var8 < this.length; ++var8) {
                    var1.array.add(var3[var8].times(Complex.log(var3[var8].over(Math.log(10.0D)))));
                }

                var1.type = this.type;
                break;
            case 15:
                Phasor[] var4 = this.getArray_as_Phasor();

                for(int var5 = 0; var5 < this.length; ++var5) {
                    var1.array.add(var4[var5].times(Phasor.log(var4[var5].over(Math.log(10.0D)))));
                }

                var1.type = 15;
                break;
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        int[] var6 = new int[2];
        findMinMax(var1.getArray_as_Object(), var1.minmax, var6, var1.typeName, var1.type);
        var1.maxIndex = var6[0];
        var1.minIndex = var6[1];
        Conv.restoreMessages();
        return var1;
    }

    public ArrayMaths minusxLog2x() {
        ArrayMaths var1 = this.xLog2x();
        return var1.negate();
    }

    public ArrayMaths minusxLogEx() {
        ArrayMaths var1 = this.xLogEx();
        return var1.negate();
    }

    public ArrayMaths minusxLog10x() {
        ArrayMaths var1 = this.xLog10x();
        return var1.negate();
    }

    public ArrayMaths sqrt() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var1 = new ArrayMaths();
        var1.array = new ArrayList();
        var1.length = this.length;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 16:
            case 17:
            case 18:
                double[] var2 = this.getArray_as_double();

                for(int var7 = 0; var7 < this.length; ++var7) {
                    var1.array.add(new Double(Math.sqrt(var2[var7])));
                }

                var1.type = 1;
                break;
            case 14:
                Complex[] var3 = this.getArray_as_Complex();

                for(int var8 = 0; var8 < this.length; ++var8) {
                    var1.array.add(Complex.sqrt(var3[var8]));
                }

                var1.type = this.type;
                break;
            case 15:
                Phasor[] var4 = this.getArray_as_Phasor();

                for(int var5 = 0; var5 < this.length; ++var5) {
                    var1.array.add(Phasor.sqrt(var4[var5]));
                }

                var1.type = 15;
                break;
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        int[] var6 = new int[2];
        findMinMax(var1.getArray_as_Object(), var1.minmax, var6, var1.typeName, var1.type);
        var1.maxIndex = var6[0];
        var1.minIndex = var6[1];
        Conv.restoreMessages();
        return var1;
    }

    public ArrayMaths oneOverSqrt() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var1 = new ArrayMaths();
        var1.array = new ArrayList();
        var1.length = this.length;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 16:
            case 17:
            case 18:
                double[] var2 = this.getArray_as_double();

                for(int var7 = 0; var7 < this.length; ++var7) {
                    var1.array.add(new Double(1.0D / Math.sqrt(var2[var7])));
                }

                var1.type = 1;
                break;
            case 14:
                Complex[] var3 = this.getArray_as_Complex();

                for(int var8 = 0; var8 < this.length; ++var8) {
                    var1.array.add(Complex.sqrt(var3[var8]).inverse());
                }

                var1.type = this.type;
                break;
            case 15:
                Phasor[] var4 = this.getArray_as_Phasor();

                for(int var5 = 0; var5 < this.length; ++var5) {
                    var1.array.add(Phasor.sqrt(var4[var5]).inverse());
                }

                var1.type = 15;
                break;
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        int[] var6 = new int[2];
        findMinMax(var1.getArray_as_Object(), var1.minmax, var6, var1.typeName, var1.type);
        var1.maxIndex = var6[0];
        var1.minIndex = var6[1];
        Conv.restoreMessages();
        return var1;
    }

    public ArrayMaths abs() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var1 = new ArrayMaths();
        var1.array = new ArrayList();
        var1.length = this.length;
        switch(this.type) {
            case 0:
            case 1:
                double[] var2 = this.getArray_as_double();

                for(int var16 = 0; var16 < this.length; ++var16) {
                    var1.array.add(new Double(Math.abs(var2[var16])));
                }

                var1.type = this.type;
                break;
            case 2:
            case 3:
                float[] var3 = this.getArray_as_float();

                for(int var17 = 0; var17 < this.length; ++var17) {
                    var1.array.add(new Float(Math.abs(var3[var17])));
                }

                var1.type = this.type;
                break;
            case 4:
            case 5:
                long[] var4 = this.getArray_as_long();

                for(int var18 = 0; var18 < this.length; ++var18) {
                    var1.array.add(new Long(Math.abs(var4[var18])));
                }

                var1.type = this.type;
                break;
            case 6:
            case 7:
                int[] var5 = this.getArray_as_int();

                for(int var19 = 0; var19 < this.length; ++var19) {
                    var1.array.add(new Integer(Math.abs(var5[var19])));
                }

                var1.type = this.type;
                break;
            case 8:
            case 9:
                int[] var6 = this.getArray_as_int();

                for(int var20 = 0; var20 < this.length; ++var20) {
                    var1.array.add(new Short((short)Math.abs(var6[var20])));
                }

                var1.type = this.type;
                break;
            case 10:
            case 11:
                int[] var7 = this.getArray_as_int();

                for(int var21 = 0; var21 < this.length; ++var21) {
                    var1.array.add(new Byte((byte)Math.abs(var7[var21])));
                }

                var1.type = this.type;
                break;
            case 12:
                BigDecimal[] var8 = this.getArray_as_BigDecimal();

                for(int var22 = 0; var22 < this.length; ++var22) {
                    var1.array.add(var8[var22].abs());
                }

                var1.type = this.type;
                break;
            case 13:
                BigInteger[] var9 = this.getArray_as_BigInteger();

                for(int var23 = 0; var23 < this.length; ++var23) {
                    var1.array.add(var9[var23].abs());
                }

                var1.type = this.type;
                break;
            case 14:
                Complex[] var10 = this.getArray_as_Complex();

                for(int var24 = 0; var24 < this.length; ++var24) {
                    var1.array.add(Complex.abs(var10[var24]));
                }

                var1.type = this.type;
                break;
            case 15:
                Phasor[] var11 = this.getArray_as_Phasor();

                for(int var25 = 0; var25 < this.length; ++var25) {
                    var1.array.add(var11[var25].abs());
                }

                var1.type = 15;
                break;
            case 16:
            case 17:
                int[] var12 = this.getArray_as_int();

                for(int var26 = 0; var26 < this.length; ++var26) {
                    var1.array.add(new Integer(Math.abs(var12[var26])));
                }

                var1.type = this.type;
                break;
            case 18:
                double[] var13 = this.getArray_as_double();

                for(int var14 = 0; var14 < this.length; ++var14) {
                    var1.array.add(new Double(Math.abs(var13[var14])));
                }

                var1.type = 1;
                break;
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        int[] var15 = new int[2];
        findMinMax(var1.getArray_as_Object(), var1.minmax, var15, var1.typeName, var1.type);
        var1.maxIndex = var15[0];
        var1.minIndex = var15[1];
        Conv.restoreMessages();
        return var1;
    }

    public ArrayMaths exp() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var1 = new ArrayMaths();
        var1.array = new ArrayList();
        var1.length = this.length;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 16:
            case 17:
            case 18:
                double[] var2 = this.getArray_as_double();

                for(int var7 = 0; var7 < this.length; ++var7) {
                    var1.array.add(new Double(Math.exp(var2[var7])));
                }

                var1.type = 1;
                break;
            case 14:
                Complex[] var3 = this.getArray_as_Complex();

                for(int var8 = 0; var8 < this.length; ++var8) {
                    var1.array.add(Complex.exp(var3[var8]));
                }

                var1.type = this.type;
                break;
            case 15:
                Phasor[] var4 = this.getArray_as_Phasor();

                for(int var5 = 0; var5 < this.length; ++var5) {
                    var1.array.add(Phasor.exp(var4[var5]));
                }

                var1.type = 15;
                break;
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        int[] var6 = new int[2];
        findMinMax(var1.getArray_as_Object(), var1.minmax, var6, var1.typeName, var1.type);
        var1.maxIndex = var6[0];
        var1.minIndex = var6[1];
        Conv.restoreMessages();
        return var1;
    }

    public ArrayMaths invert() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var1 = new ArrayMaths();
        var1.array = new ArrayList();
        var1.length = this.length;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 16:
            case 17:
            case 18:
                double[] var2 = this.getArray_as_double();

                for(int var8 = 0; var8 < this.length; ++var8) {
                    var1.array.add(new Double(1.0D / var2[var8]));
                }

                var1.type = 1;
                break;
            case 12:
            case 13:
                BigDecimal[] var3 = this.getArray_as_BigDecimal();

                for(int var9 = 0; var9 < this.length; ++var9) {
                    var1.array.add(BigDecimal.ONE.divide(var3[var9], 4));
                }

                var1.type = 12;
                var3 = null;
                break;
            case 14:
                Complex[] var4 = this.getArray_as_Complex();

                for(int var10 = 0; var10 < this.length; ++var10) {
                    var1.array.add(Complex.plusOne().over(var4[var10]));
                }

                var1.type = 14;
                break;
            case 15:
                Phasor[] var5 = this.getArray_as_Phasor();

                for(int var6 = 0; var6 < this.length; ++var6) {
                    var1.array.add(Phasor.plusOne().over(var5[var6]));
                }

                var1.type = 15;
                break;
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        int[] var7 = new int[2];
        findMinMax(var1.getArray_as_Object(), var1.minmax, var7, var1.typeName, var1.type);
        var1.maxIndex = var7[0];
        var1.minIndex = var7[1];
        Conv.restoreMessages();
        return var1;
    }

    public ArrayMaths pow(int var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var2 = new ArrayMaths();
        var2.array = new ArrayList();
        var2.length = this.length;
        int var13;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 16:
            case 17:
            case 18:
                double[] var3 = this.getArray_as_double();

                for(int var11 = 0; var11 < this.length; ++var11) {
                    var2.array.add(new Double(Math.pow(var3[var11], (double)var1)));
                }

                var2.type = 1;
                break;
            case 12:
                BigDecimal[] var4 = this.getArray_as_BigDecimal();
                BigDecimal var5 = BigDecimal.ONE;

                for(int var12 = 0; var12 < this.length; ++var12) {
                    for(var13 = 0; var13 < var1; ++var13) {
                        var5 = var5.multiply(var4[var12]);
                    }

                    var2.array.add(var5);
                }

                var4 = null;
                var5 = null;
                var2.type = 12;
                break;
            case 13:
                BigInteger[] var6 = this.getArray_as_BigInteger();

                for(var13 = 0; var13 < this.length; ++var13) {
                    var2.array.add(var6[var13].pow(var1));
                }

                var2.type = 13;
                var6 = null;
                break;
            case 14:
                Complex[] var7 = this.getArray_as_Complex();

                for(int var14 = 0; var14 < this.length; ++var14) {
                    var2.array.add(Complex.pow(var7[var14], var1));
                }

                var2.type = this.type;
                break;
            case 15:
                Phasor[] var8 = this.getArray_as_Phasor();

                for(int var9 = 0; var9 < this.length; ++var9) {
                    var2.array.add(Phasor.pow(var8[var9], var1));
                }

                var2.type = 15;
                break;
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        int[] var10 = new int[2];
        findMinMax(var2.getArray_as_Object(), var2.minmax, var10, var2.typeName, var2.type);
        var2.maxIndex = var10[0];
        var2.minIndex = var10[1];
        Conv.restoreMessages();
        return var2;
    }

    public ArrayMaths pow(double var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var3 = new ArrayMaths();
        var3.array = new ArrayList();
        var3.length = this.length;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 16:
            case 17:
            case 18:
                double[] var4 = this.getArray_as_double();

                for(int var9 = 0; var9 < this.length; ++var9) {
                    var3.array.add(new Double(Math.pow(var4[var9], var1)));
                }

                var3.type = 1;
                break;
            case 14:
                Complex[] var5 = this.getArray_as_Complex();

                for(int var10 = 0; var10 < this.length; ++var10) {
                    var3.array.add(Complex.pow(var5[var10], var1));
                }

                var3.type = this.type;
                break;
            case 15:
                Phasor[] var6 = this.getArray_as_Phasor();

                for(int var7 = 0; var7 < this.length; ++var7) {
                    var3.array.add(Phasor.pow(var6[var7], var1));
                }

                var3.type = 15;
                break;
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        int[] var8 = new int[2];
        findMinMax(var3.getArray_as_Object(), var3.minmax, var8, var3.typeName, var3.type);
        var3.maxIndex = var8[0];
        var3.minIndex = var8[1];
        Conv.restoreMessages();
        return var3;
    }

    public ArrayMaths pow(float var1) {
        double var2 = (double)var1;
        return this.pow(var2);
    }

    public ArrayMaths pow(long var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var3 = new ArrayMaths();
        var3.array = new ArrayList();
        var3.length = this.length;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 16:
            case 17:
            case 18:
                double[] var4 = this.getArray_as_double();

                for(int var13 = 0; var13 < this.length; ++var13) {
                    var3.array.add(new Double(Math.pow(var4[var13], (double)var1)));
                }

                var3.type = 1;
                break;
            case 12:
                BigDecimal[] var5 = this.getArray_as_BigDecimal();
                BigDecimal var6 = BigDecimal.ONE;

                for(int var14 = 0; var14 < this.length; ++var14) {
                    for(long var15 = 0L; var15 < var1; var6 = var6.multiply(var5[var14])) {
                        ;
                    }

                    var3.array.add(var6);
                }

                var5 = null;
                var6 = null;
                var3.type = 12;
                break;
            case 13:
                BigInteger[] var7 = this.getArray_as_BigInteger();
                BigInteger var8 = BigInteger.ONE;

                for(int var16 = 0; var16 < this.length; ++var16) {
                    for(long var18 = 0L; var18 < var1; var8 = var8.multiply(var7[var16])) {
                        ;
                    }

                    var3.array.add(var8);
                }

                var7 = null;
                var8 = null;
                var3.type = 13;
                break;
            case 14:
                Complex[] var9 = this.getArray_as_Complex();

                for(int var17 = 0; var17 < this.length; ++var17) {
                    var3.array.add(Complex.pow(var9[var17], (double)var1));
                }

                var3.type = this.type;
                break;
            case 15:
                Phasor[] var10 = this.getArray_as_Phasor();

                for(int var11 = 0; var11 < this.length; ++var11) {
                    var3.array.add(Phasor.pow(var10[var11], (double)var1));
                }

                var3.type = 15;
                break;
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        int[] var12 = new int[2];
        findMinMax(var3.getArray_as_Object(), var3.minmax, var12, var3.typeName, var3.type);
        var3.maxIndex = var12[0];
        var3.minIndex = var12[1];
        Conv.restoreMessages();
        return var3;
    }

    public ArrayMaths pow(short var1) {
        return this.pow((int)var1);
    }

    public ArrayMaths pow(byte var1) {
        return this.pow((int)var1);
    }

    public ArrayMaths pow(Number var1) {
        boolean var2 = integers.containsKey(var1.getClass());
        if (var2) {
            if (var1 instanceof Long) {
                return this.pow(var1.longValue());
            } else {
                return var1 instanceof BigInteger ? this.pow(var1.doubleValue()) : this.pow(var1.intValue());
            }
        } else {
            return this.pow(var1.doubleValue());
        }
    }

    public ArrayMaths negate() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var1 = new ArrayMaths();
        var1.array = new ArrayList();
        var1.length = this.length;
        switch(this.type) {
            case 0:
            case 1:
            case 16:
            case 17:
            case 18:
                double[] var2 = this.getArray_as_double();

                for(int var14 = 0; var14 < this.length; ++var14) {
                    var1.array.add(new Double(-var2[var14]));
                }

                var1.type = 1;
                break;
            case 2:
            case 3:
                float[] var3 = this.getArray_as_float();

                for(int var15 = 0; var15 < this.length; ++var15) {
                    var1.array.add(new Float(-var3[var15]));
                }

                var1.type = 3;
                break;
            case 4:
            case 5:
                long[] var4 = this.getArray_as_long();

                for(int var16 = 0; var16 < this.length; ++var16) {
                    var1.array.add(new Long(-var4[var16]));
                }

                var1.type = 5;
                break;
            case 6:
            case 7:
                int[] var5 = this.getArray_as_int();

                for(int var17 = 0; var17 < this.length; ++var17) {
                    var1.array.add(new Integer(-var5[var17]));
                }

                var1.type = 7;
                break;
            case 8:
            case 9:
                short[] var6 = this.getArray_as_short();

                for(int var18 = 0; var18 < this.length; ++var18) {
                    var1.array.add(new Short((short)(-var6[var18])));
                }

                var1.type = 9;
                break;
            case 10:
            case 11:
                byte[] var7 = this.getArray_as_byte();

                for(int var19 = 0; var19 < this.length; ++var19) {
                    var1.array.add(new Byte((byte)(-var7[var19])));
                }

                var1.type = 11;
                break;
            case 12:
                BigDecimal[] var8 = this.getArray_as_BigDecimal();

                for(int var20 = 0; var20 < this.length; ++var20) {
                    var1.array.add(var8[var20].negate());
                }

                var1.type = 12;
                var8 = null;
                break;
            case 13:
                BigInteger[] var9 = this.getArray_as_BigInteger();

                for(int var21 = 0; var21 < this.length; ++var21) {
                    var1.array.add(var9[var21].negate());
                }

                var1.type = 13;
                var9 = null;
                break;
            case 14:
                Complex[] var10 = this.getArray_as_Complex();

                for(int var22 = 0; var22 < this.length; ++var22) {
                    var1.array.add(var10[var22].negate());
                }

                var1.type = 14;
                break;
            case 15:
                Phasor[] var11 = this.getArray_as_Phasor();

                for(int var12 = 0; var12 < this.length; ++var12) {
                    var1.array.add(var11[var12].negate());
                }

                var1.type = 15;
                break;
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        int[] var13 = new int[2];
        findMinMax(var1.getArray_as_Object(), var1.minmax, var13, var1.typeName, var1.type);
        var1.maxIndex = var13[0];
        var1.minIndex = var13[1];
        Conv.restoreMessages();
        return var1;
    }

    protected void calcSum() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 18:
                double[] var1 = this.getArray_as_double();
                double var2 = 0.0D;

                for(int var17 = 0; var17 < this.length; ++var17) {
                    var2 += var1[var17];
                }

                this.summ.add(new Double(var2));
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 16:
            case 17:
                long[] var4 = this.getArray_as_long();
                long var5 = 0L;
                boolean var7 = false;

                for(int var18 = 0; var18 < this.length; ++var18) {
                    if (9223372036854775807L - var5 < var4[var18]) {
                        var7 = true;
                    }

                    var5 += var4[var18];
                }

                if (var7) {
                    double[] var19 = this.getArray_as_double();
                    double var20 = 0.0D;

                    for(int var22 = 0; var22 < this.length; ++var22) {
                        var20 += var19[var22];
                    }

                    this.summ.add(new Double(var20));
                    this.sumlongToDouble = true;
                } else {
                    this.summ.add(new Long(var5));
                }
                break;
            case 12:
                BigDecimal[] var8 = this.getArray_as_BigDecimal();
                BigDecimal var9 = new BigDecimal(0.0D);

                for(int var21 = 0; var21 < this.length; ++var21) {
                    var9.add(var8[var21]);
                }

                this.summ.add(var9);
                var8 = null;
                var9 = null;
                break;
            case 13:
                BigInteger[] var10 = this.getArray_as_BigInteger();
                BigInteger var11 = BigInteger.ZERO;

                for(int var23 = 0; var23 < this.length; ++var23) {
                    var11.add(var10[var23]);
                }

                this.summ.add(var11);
                var10 = null;
                var11 = null;
                break;
            case 14:
                Complex[] var12 = this.getArray_as_Complex();
                Complex var13 = Complex.zero();

                for(int var24 = 0; var24 < this.length; ++var24) {
                    var13.plus(var12[var24]);
                }

                this.summ.add(var13);
                break;
            case 15:
                Phasor[] var14 = this.getArray_as_Phasor();
                Phasor var15 = Phasor.zero();

                for(int var16 = 0; var16 < this.length; ++var16) {
                    var15.plus(var14[var16]);
                }

                this.summ.add(var15);
                break;
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        this.sumDone = true;
        Conv.restoreMessages();
    }

    public double sum() {
        return this.getSum_as_double();
    }

    public double sum_as_double() {
        return this.getSum_as_double();
    }

    public double getSum() {
        return this.getSum_as_double();
    }

    public double getSum_as_double() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        if (!this.sumDone) {
            this.calcSum();
        }

        double var1 = 0.0D;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 18:
                var1 = (Double)this.summ.get(0);
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 16:
            case 17:
                if (this.sumlongToDouble) {
                    var1 = (Double)this.summ.get(0);
                } else {
                    var1 = Conv.convert_Long_to_double((Long)this.summ.get(0));
                }
                break;
            case 12:
                var1 = Conv.convert_BigDecimal_to_double((BigDecimal)this.summ.get(0));
                break;
            case 13:
                var1 = Conv.convert_BigInteger_to_double((BigInteger)this.summ.get(0));
                break;
            case 14:
            case 15:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a sum as double is meaningful/supported");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var1;
    }

    public Double sum_as_Double() {
        return this.getSum_as_Double();
    }

    public Double getSum_as_Double() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        if (!this.sumDone) {
            this.calcSum();
        }

        new Double(0.0D);
        Double var1;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 18:
                var1 = (Double)this.summ.get(0);
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 16:
            case 17:
                if (this.sumlongToDouble) {
                    var1 = (Double)this.summ.get(0);
                } else {
                    var1 = Conv.convert_Long_to_Double((Long)this.summ.get(0));
                }
                break;
            case 12:
                var1 = Conv.convert_BigDecimal_to_Double((BigDecimal)this.summ.get(0));
                break;
            case 13:
                var1 = Conv.convert_BigInteger_to_Double((BigInteger)this.summ.get(0));
                break;
            case 14:
            case 15:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a sum as Double is meaningful/supported");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var1;
    }

    public float sum_as_float() {
        return this.getSum_as_float();
    }

    public float getSum_as_float() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        if (!this.sumDone) {
            this.calcSum();
        }

        float var1 = 0.0F;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 18:
                var1 = Conv.convert_Double_to_float((Double)this.summ.get(0));
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 16:
            case 17:
                if (this.sumlongToDouble) {
                    var1 = Conv.convert_Double_to_float((Double)this.summ.get(0));
                } else {
                    var1 = Conv.convert_Long_to_float((Long)this.summ.get(0));
                }
                break;
            case 12:
                var1 = Conv.convert_BigDecimal_to_float((BigDecimal)this.summ.get(0));
                break;
            case 13:
                var1 = Conv.convert_BigInteger_to_float((BigInteger)this.summ.get(0));
                break;
            case 14:
            case 15:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a sum as float is meaningful/supported");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var1;
    }

    public Float sum_as_Float() {
        return this.getSum_as_Float();
    }

    public Float getSum_as_Float() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        if (!this.sumDone) {
            this.calcSum();
        }

        new Float(0.0F);
        Float var1;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 18:
                var1 = Conv.convert_Double_to_Float((Double)this.summ.get(0));
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 16:
            case 17:
                if (this.sumlongToDouble) {
                    var1 = Conv.convert_Double_to_Float((Double)this.summ.get(0));
                } else {
                    var1 = Conv.convert_Long_to_Float((Long)this.summ.get(0));
                }
                break;
            case 12:
                var1 = Conv.convert_BigDecimal_to_Float((BigDecimal)this.summ.get(0));
                break;
            case 13:
                var1 = Conv.convert_BigInteger_to_Float((BigInteger)this.summ.get(0));
                break;
            case 14:
            case 15:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a sum as Float is meaningful/supported");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var1;
    }

    public long sum_as_long() {
        return this.getSum_as_long();
    }

    public long getSum_as_long() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        if (!this.sumDone) {
            this.calcSum();
        }

        long var1 = 0L;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 18:
                var1 = Conv.convert_Double_to_long((Double)this.summ.get(0));
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 16:
            case 17:
                if (this.sumlongToDouble) {
                    var1 = Conv.convert_Double_to_long((Double)this.summ.get(0));
                } else {
                    var1 = (Long)this.summ.get(0);
                }
                break;
            case 12:
                var1 = Conv.convert_BigDecimal_to_long((BigDecimal)this.summ.get(0));
                break;
            case 13:
                var1 = Conv.convert_BigInteger_to_long((BigInteger)this.summ.get(0));
                break;
            case 14:
            case 15:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a sum as long is meaningful/supported");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var1;
    }

    public Long sum_as_Long() {
        return this.getSum_as_Long();
    }

    public Long getSum_as_Long() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        if (!this.sumDone) {
            this.calcSum();
        }

        new Long(0L);
        Long var1;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 18:
                var1 = Conv.convert_Double_to_Long((Double)this.summ.get(0));
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 16:
            case 17:
                if (this.sumlongToDouble) {
                    var1 = Conv.convert_Double_to_Long((Double)this.summ.get(0));
                } else {
                    var1 = (Long)this.summ.get(0);
                }
                break;
            case 12:
                var1 = Conv.convert_BigDecimal_to_Long((BigDecimal)this.summ.get(0));
                break;
            case 13:
                var1 = Conv.convert_BigInteger_to_Long((BigInteger)this.summ.get(0));
                break;
            case 14:
            case 15:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a sum as Long is meaningful/supported");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var1;
    }

    public int sum_as_int() {
        return this.getSum_as_int();
    }

    public int getSum_as_int() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        if (!this.sumDone) {
            this.calcSum();
        }

        boolean var1 = false;
        int var2;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 18:
                var2 = Conv.convert_Double_to_int((Double)this.summ.get(0));
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 16:
            case 17:
                if (this.sumlongToDouble) {
                    var2 = Conv.convert_Double_to_int((Double)this.summ.get(0));
                } else {
                    var2 = Conv.convert_Long_to_int((Long)this.summ.get(0));
                }
                break;
            case 12:
                var2 = Conv.convert_BigDecimal_to_int((BigDecimal)this.summ.get(0));
                break;
            case 13:
                var2 = Conv.convert_BigInteger_to_int((BigInteger)this.summ.get(0));
                break;
            case 14:
            case 15:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a sum as int is meaningful/supported");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var2;
    }

    public Integer sum_as_Integer() {
        return this.getSum_as_Integer();
    }

    public Integer getSum_as_Integer() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        if (!this.sumDone) {
            this.calcSum();
        }

        new Integer(0);
        Integer var1;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 18:
                var1 = Conv.convert_Double_to_Integer((Double)this.summ.get(0));
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 16:
            case 17:
                if (this.sumlongToDouble) {
                    var1 = Conv.convert_Double_to_Integer((Double)this.summ.get(0));
                } else {
                    var1 = Conv.convert_Long_to_Integer((Long)this.summ.get(0));
                }
                break;
            case 12:
                var1 = Conv.convert_BigDecimal_to_Integer((BigDecimal)this.summ.get(0));
                break;
            case 13:
                var1 = Conv.convert_BigInteger_to_Integer((BigInteger)this.summ.get(0));
                break;
            case 14:
            case 15:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a sum as Integer is meaningful/supported");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var1;
    }

    public short sum_as_short() {
        return this.getSum_as_short();
    }

    public short getSum_as_short() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        if (!this.sumDone) {
            this.calcSum();
        }

        boolean var1 = false;
        short var2;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 18:
                var2 = Conv.convert_Double_to_short((Double)this.summ.get(0));
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 16:
            case 17:
                if (this.sumlongToDouble) {
                    var2 = Conv.convert_Double_to_short((Double)this.summ.get(0));
                } else {
                    var2 = Conv.convert_Long_to_short((Long)this.summ.get(0));
                }
                break;
            case 12:
                var2 = Conv.convert_BigDecimal_to_short((BigDecimal)this.summ.get(0));
                break;
            case 13:
                var2 = Conv.convert_BigInteger_to_short((BigInteger)this.summ.get(0));
                break;
            case 14:
            case 15:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a sum as short is meaningful/supported");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var2;
    }

    public Short sum_as_Short() {
        return this.getSum_as_Short();
    }

    public Short getSum_as_Short() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        if (!this.sumDone) {
            this.calcSum();
        }

        new Short((short)0);
        Short var1;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 18:
                var1 = Conv.convert_Double_to_Short((Double)this.summ.get(0));
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 16:
            case 17:
                if (this.sumlongToDouble) {
                    var1 = Conv.convert_Double_to_Short((Double)this.summ.get(0));
                } else {
                    var1 = Conv.convert_Long_to_Short((Long)this.summ.get(0));
                }
                break;
            case 12:
                var1 = Conv.convert_BigDecimal_to_Short((BigDecimal)this.summ.get(0));
                break;
            case 13:
                var1 = Conv.convert_BigInteger_to_Short((BigInteger)this.summ.get(0));
                break;
            case 14:
            case 15:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a sum as Short is meaningful/supported");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var1;
    }

    public byte sum_as_byte() {
        return this.getSum_as_byte();
    }

    public byte getSum_as_byte() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        if (!this.sumDone) {
            this.calcSum();
        }

        boolean var1 = false;
        byte var2;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 18:
                var2 = Conv.convert_Double_to_byte((Double)this.summ.get(0));
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 16:
            case 17:
                if (this.sumlongToDouble) {
                    var2 = Conv.convert_Double_to_byte((Double)this.summ.get(0));
                } else {
                    var2 = Conv.convert_Long_to_byte((Long)this.summ.get(0));
                }
                break;
            case 12:
                var2 = Conv.convert_BigDecimal_to_byte((BigDecimal)this.summ.get(0));
                break;
            case 13:
                var2 = Conv.convert_BigInteger_to_byte((BigInteger)this.summ.get(0));
                break;
            case 14:
            case 15:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a sum as byte is meaningful/supported");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var2;
    }

    public Byte sum_as_Byte() {
        return this.getSum_as_Byte();
    }

    public Byte getSum_as_Byte() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        if (!this.sumDone) {
            this.calcSum();
        }

        new Byte((byte)0);
        Byte var1;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 18:
                var1 = Conv.convert_Double_to_Byte((Double)this.summ.get(0));
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 16:
            case 17:
                if (this.sumlongToDouble) {
                    var1 = Conv.convert_Double_to_Byte((Double)this.summ.get(0));
                } else {
                    var1 = Conv.convert_Long_to_Byte((Long)this.summ.get(0));
                }
                break;
            case 12:
                var1 = Conv.convert_BigDecimal_to_Byte((BigDecimal)this.summ.get(0));
                break;
            case 13:
                var1 = Conv.convert_BigInteger_to_Byte((BigInteger)this.summ.get(0));
                break;
            case 14:
            case 15:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a sum as Byte is meaningful/supported");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var1;
    }

    public BigDecimal sum_as_BigDecimal() {
        return this.getSum_as_BigDecimal();
    }

    public BigDecimal getSum_as_BigDecimal() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        if (!this.sumDone) {
            this.calcSum();
        }

        new BigDecimal(0.0D);
        BigDecimal var1;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 18:
                var1 = Conv.convert_Double_to_BigDecimal((Double)this.summ.get(0));
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 16:
            case 17:
                if (this.sumlongToDouble) {
                    var1 = Conv.convert_Double_to_BigDecimal((Double)this.summ.get(0));
                } else {
                    var1 = Conv.convert_Long_to_BigDecimal((Long)this.summ.get(0));
                }
                break;
            case 12:
                var1 = (BigDecimal)this.summ.get(0);
                break;
            case 13:
                var1 = Conv.convert_BigInteger_to_BigDecimal((BigInteger)this.summ.get(0));
                break;
            case 14:
            case 15:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a sum as BigDecimal is meaningful/supported");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var1;
    }

    public BigInteger sum_as_BigInteger() {
        return this.getSum_as_BigInteger();
    }

    public BigInteger getSum_as_BigInteger() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        if (!this.sumDone) {
            this.calcSum();
        }

        BigInteger var1 = BigInteger.ZERO;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 18:
                var1 = Conv.convert_Double_to_BigInteger((Double)this.summ.get(0));
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 16:
            case 17:
                if (this.sumlongToDouble) {
                    var1 = Conv.convert_Double_to_BigInteger((Double)this.summ.get(0));
                } else {
                    var1 = Conv.convert_Long_to_BigInteger((Long)this.summ.get(0));
                }
                break;
            case 12:
                var1 = Conv.convert_BigDecimal_to_BigInteger((BigDecimal)this.summ.get(0));
                break;
            case 13:
                var1 = (BigInteger)this.summ.get(0);
                break;
            case 14:
            case 15:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a sum as BigInteger is meaningful/supported");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var1;
    }

    public Complex sum_as_Complex() {
        return this.getSum_as_Complex();
    }

    public Complex getSum_as_Complex() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        if (!this.sumDone) {
            this.calcSum();
        }

        Complex var1 = Complex.zero();
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 18:
                var1 = new Complex((Double)this.summ.get(0));
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 16:
            case 17:
                if (this.sumlongToDouble) {
                    var1 = new Complex((Double)this.summ.get(0));
                } else {
                    var1 = new Complex(((Long)this.summ.get(0)).doubleValue());
                }
                break;
            case 12:
                var1 = new Complex(((BigDecimal)this.summ.get(0)).doubleValue());
                break;
            case 13:
                var1 = new Complex(((BigInteger)this.summ.get(0)).doubleValue());
                break;
            case 14:
            case 15:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a sum as Complex is meaningful/supported");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var1;
    }

    public Phasor sum_as_Phasor() {
        return this.getSum_as_Phasor();
    }

    public Phasor getSum_as_Phasor() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        if (!this.sumDone) {
            this.calcSum();
        }

        Phasor var1 = Phasor.zero();
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 18:
                var1 = new Phasor((Double)this.summ.get(0));
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 16:
            case 17:
                if (this.sumlongToDouble) {
                    var1 = new Phasor((Double)this.summ.get(0));
                } else {
                    var1 = new Phasor(((Long)this.summ.get(0)).doubleValue());
                }
                break;
            case 12:
                var1 = new Phasor(((BigDecimal)this.summ.get(0)).doubleValue());
                break;
            case 13:
                var1 = new Phasor(((BigInteger)this.summ.get(0)).doubleValue());
                break;
            case 14:
            case 15:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a sum as Phasor is meaningful/supported");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var1;
    }

    public String sum_as_String() {
        return this.getSum_as_String();
    }

    public String getSum_as_String() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        if (!this.sumDone) {
            this.calcSum();
        }

        String var1 = " ";
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 18:
                var1 = Double.toString((Double)this.summ.get(0));
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 16:
            case 17:
                if (this.sumlongToDouble) {
                    var1 = Double.toString((Double)this.summ.get(0));
                } else {
                    var1 = Double.toString(((Long)this.summ.get(0)).doubleValue());
                }
                break;
            case 12:
                var1 = ((BigDecimal)this.summ.get(0)).toString();
                break;
            case 13:
                var1 = ((BigInteger)this.summ.get(0)).toString();
                break;
            case 14:
            case 15:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a sum as String is meaningful/supported");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var1;
    }

    protected void calcProduct() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 18:
                double[] var1 = this.getArray_as_double();
                double var2 = 1.0D;

                for(int var17 = 0; var17 < this.length; ++var17) {
                    var2 *= var1[var17];
                }

                this.productt.add(new Double(var2));
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 16:
            case 17:
                long[] var4 = this.getArray_as_long();
                long var5 = 1L;
                boolean var7 = false;

                for(int var18 = 0; var18 < this.length; ++var18) {
                    if (9223372036854775807L / var5 < var4[var18]) {
                        var7 = true;
                    }

                    var5 += var4[var18];
                }

                if (var7) {
                    double[] var19 = this.getArray_as_double();
                    double var20 = 1.0D;

                    for(int var22 = 0; var22 < this.length; ++var22) {
                        var20 *= var19[var22];
                    }

                    this.productt.add(new Double(var20));
                    this.sumlongToDouble = true;
                } else {
                    this.productt.add(new Long(var5));
                }
                break;
            case 12:
                BigDecimal[] var8 = this.getArray_as_BigDecimal();
                BigDecimal var9 = new BigDecimal(1.0D);

                for(int var21 = 0; var21 < this.length; ++var21) {
                    var9.multiply(var8[var21]);
                }

                this.productt.add(var9);
                var8 = null;
                var9 = null;
                break;
            case 13:
                BigInteger[] var10 = this.getArray_as_BigInteger();
                BigInteger var11 = BigInteger.ONE;

                for(int var23 = 0; var23 < this.length; ++var23) {
                    var11.multiply(var10[var23]);
                }

                this.productt.add(var11);
                var10 = null;
                var11 = null;
                break;
            case 14:
                Complex[] var12 = this.getArray_as_Complex();
                Complex var13 = Complex.plusOne();

                for(int var24 = 0; var24 < this.length; ++var24) {
                    var13.times(var12[var24]);
                }

                this.productt.add(var13);
                break;
            case 15:
                Phasor[] var14 = this.getArray_as_Phasor();
                Phasor var15 = Phasor.plusOne();

                for(int var16 = 0; var16 < this.length; ++var16) {
                    var15.times(var14[var16]);
                }

                this.productt.add(var15);
                break;
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        this.productDone = true;
        Conv.restoreMessages();
    }

    public double product() {
        return this.getProduct_as_double();
    }

    public double product_as_double() {
        return this.getProduct_as_double();
    }

    public double getProduct() {
        return this.getProduct_as_double();
    }

    public double getProduct_as_double() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        if (!this.productDone) {
            this.calcProduct();
        }

        double var1 = 0.0D;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 18:
                var1 = (Double)this.productt.get(0);
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 16:
            case 17:
                if (this.productlongToDouble) {
                    var1 = (Double)this.productt.get(0);
                } else {
                    var1 = Conv.convert_Long_to_double((Long)this.productt.get(0));
                }
                break;
            case 12:
                var1 = Conv.convert_BigDecimal_to_double((BigDecimal)this.productt.get(0));
                break;
            case 13:
                var1 = Conv.convert_BigInteger_to_double((BigInteger)this.productt.get(0));
                break;
            case 14:
            case 15:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a productas double is meaningful/supported");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var1;
    }

    public Double product_as_Double() {
        return this.getProduct_as_Double();
    }

    public Double getProduct_as_Double() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        if (!this.productDone) {
            this.calcProduct();
        }

        new Double(0.0D);
        Double var1;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 18:
                var1 = (Double)this.productt.get(0);
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 16:
            case 17:
                if (this.productlongToDouble) {
                    var1 = (Double)this.productt.get(0);
                } else {
                    var1 = Conv.convert_Long_to_Double((Long)this.productt.get(0));
                }
                break;
            case 12:
                var1 = Conv.convert_BigDecimal_to_Double((BigDecimal)this.productt.get(0));
                break;
            case 13:
                var1 = Conv.convert_BigInteger_to_Double((BigInteger)this.productt.get(0));
                break;
            case 14:
            case 15:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a productas Double is meaningful/supported");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var1;
    }

    public float product_as_float() {
        return this.getProduct_as_float();
    }

    public float getProduct_as_float() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        if (!this.productDone) {
            this.calcProduct();
        }

        float var1 = 0.0F;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 18:
                var1 = Conv.convert_Double_to_float((Double)this.productt.get(0));
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 16:
            case 17:
                if (this.productlongToDouble) {
                    var1 = Conv.convert_Double_to_float((Double)this.productt.get(0));
                } else {
                    var1 = Conv.convert_Long_to_float((Long)this.productt.get(0));
                }
                break;
            case 12:
                var1 = Conv.convert_BigDecimal_to_float((BigDecimal)this.productt.get(0));
                break;
            case 13:
                var1 = Conv.convert_BigInteger_to_float((BigInteger)this.productt.get(0));
                break;
            case 14:
            case 15:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a productas float is meaningful/supported");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var1;
    }

    public Float product_as_Float() {
        return this.getProduct_as_Float();
    }

    public Float getProduct_as_Float() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        if (!this.productDone) {
            this.calcProduct();
        }

        new Float(0.0F);
        Float var1;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 18:
                var1 = Conv.convert_Double_to_Float((Double)this.productt.get(0));
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 16:
            case 17:
                if (this.productlongToDouble) {
                    var1 = Conv.convert_Double_to_Float((Double)this.productt.get(0));
                } else {
                    var1 = Conv.convert_Long_to_Float((Long)this.productt.get(0));
                }
                break;
            case 12:
                var1 = Conv.convert_BigDecimal_to_Float((BigDecimal)this.productt.get(0));
                break;
            case 13:
                var1 = Conv.convert_BigInteger_to_Float((BigInteger)this.productt.get(0));
                break;
            case 14:
            case 15:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a productas Float is meaningful/supported");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var1;
    }

    public long product_as_long() {
        return this.getProduct_as_long();
    }

    public long getProduct_as_long() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        if (!this.productDone) {
            this.calcProduct();
        }

        long var1 = 0L;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 18:
                var1 = Conv.convert_Double_to_long((Double)this.productt.get(0));
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 16:
            case 17:
                if (this.productlongToDouble) {
                    var1 = Conv.convert_Double_to_long((Double)this.productt.get(0));
                } else {
                    var1 = (Long)this.productt.get(0);
                }
                break;
            case 12:
                var1 = Conv.convert_BigDecimal_to_long((BigDecimal)this.productt.get(0));
                break;
            case 13:
                var1 = Conv.convert_BigInteger_to_long((BigInteger)this.productt.get(0));
                break;
            case 14:
            case 15:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a productas long is meaningful/supported");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var1;
    }

    public Long product_as_Long() {
        return this.getProduct_as_Long();
    }

    public Long getProduct_as_Long() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        if (!this.productDone) {
            this.calcProduct();
        }

        new Long(0L);
        Long var1;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 18:
                var1 = Conv.convert_Double_to_Long((Double)this.productt.get(0));
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 16:
            case 17:
                if (this.productlongToDouble) {
                    var1 = Conv.convert_Double_to_Long((Double)this.productt.get(0));
                } else {
                    var1 = (Long)this.productt.get(0);
                }
                break;
            case 12:
                var1 = Conv.convert_BigDecimal_to_Long((BigDecimal)this.productt.get(0));
                break;
            case 13:
                var1 = Conv.convert_BigInteger_to_Long((BigInteger)this.productt.get(0));
                break;
            case 14:
            case 15:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a productas Long is meaningful/supported");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var1;
    }

    public int product_as_int() {
        return this.getProduct_as_int();
    }

    public int getProduct_as_int() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        if (!this.productDone) {
            this.calcProduct();
        }

        boolean var1 = false;
        int var2;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 18:
                var2 = Conv.convert_Double_to_int((Double)this.productt.get(0));
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 16:
            case 17:
                if (this.productlongToDouble) {
                    var2 = Conv.convert_Double_to_int((Double)this.productt.get(0));
                } else {
                    var2 = Conv.convert_Long_to_int((Long)this.productt.get(0));
                }
                break;
            case 12:
                var2 = Conv.convert_BigDecimal_to_int((BigDecimal)this.productt.get(0));
                break;
            case 13:
                var2 = Conv.convert_BigInteger_to_int((BigInteger)this.productt.get(0));
                break;
            case 14:
            case 15:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a productas int is meaningful/supported");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var2;
    }

    public Integer product_as_Integer() {
        return this.getProduct_as_Integer();
    }

    public Integer getProduct_as_Integer() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        if (!this.productDone) {
            this.calcProduct();
        }

        new Integer(0);
        Integer var1;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 18:
                var1 = Conv.convert_Double_to_Integer((Double)this.productt.get(0));
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 16:
            case 17:
                if (this.productlongToDouble) {
                    var1 = Conv.convert_Double_to_Integer((Double)this.productt.get(0));
                } else {
                    var1 = Conv.convert_Long_to_Integer((Long)this.productt.get(0));
                }
                break;
            case 12:
                var1 = Conv.convert_BigDecimal_to_Integer((BigDecimal)this.productt.get(0));
                break;
            case 13:
                var1 = Conv.convert_BigInteger_to_Integer((BigInteger)this.productt.get(0));
                break;
            case 14:
            case 15:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a productas Integer is meaningful/supported");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var1;
    }

    public short product_as_short() {
        return this.getProduct_as_short();
    }

    public short getProduct_as_short() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        if (!this.productDone) {
            this.calcProduct();
        }

        boolean var1 = false;
        short var2;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 18:
                var2 = Conv.convert_Double_to_short((Double)this.productt.get(0));
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 16:
            case 17:
                if (this.productlongToDouble) {
                    var2 = Conv.convert_Double_to_short((Double)this.productt.get(0));
                } else {
                    var2 = Conv.convert_Long_to_short((Long)this.productt.get(0));
                }
                break;
            case 12:
                var2 = Conv.convert_BigDecimal_to_short((BigDecimal)this.productt.get(0));
                break;
            case 13:
                var2 = Conv.convert_BigInteger_to_short((BigInteger)this.productt.get(0));
                break;
            case 14:
            case 15:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a productas short is meaningful/supported");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var2;
    }

    public Short product_as_Short() {
        return this.getProduct_as_Short();
    }

    public Short getProduct_as_Short() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        if (!this.productDone) {
            this.calcProduct();
        }

        new Short((short)0);
        Short var1;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 18:
                var1 = Conv.convert_Double_to_Short((Double)this.productt.get(0));
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 16:
            case 17:
                if (this.productlongToDouble) {
                    var1 = Conv.convert_Double_to_Short((Double)this.productt.get(0));
                } else {
                    var1 = Conv.convert_Long_to_Short((Long)this.productt.get(0));
                }
                break;
            case 12:
                var1 = Conv.convert_BigDecimal_to_Short((BigDecimal)this.productt.get(0));
                break;
            case 13:
                var1 = Conv.convert_BigInteger_to_Short((BigInteger)this.productt.get(0));
                break;
            case 14:
            case 15:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a productas Short is meaningful/supported");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var1;
    }

    public byte product_as_byte() {
        return this.getProduct_as_byte();
    }

    public byte getProduct_as_byte() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        if (!this.productDone) {
            this.calcProduct();
        }

        boolean var1 = false;
        byte var2;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 18:
                var2 = Conv.convert_Double_to_byte((Double)this.productt.get(0));
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 16:
            case 17:
                if (this.productlongToDouble) {
                    var2 = Conv.convert_Double_to_byte((Double)this.productt.get(0));
                } else {
                    var2 = Conv.convert_Long_to_byte((Long)this.productt.get(0));
                }
                break;
            case 12:
                var2 = Conv.convert_BigDecimal_to_byte((BigDecimal)this.productt.get(0));
                break;
            case 13:
                var2 = Conv.convert_BigInteger_to_byte((BigInteger)this.productt.get(0));
                break;
            case 14:
            case 15:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a productas byte is meaningful/supported");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var2;
    }

    public Byte product_as_Byte() {
        return this.getProduct_as_Byte();
    }

    public Byte getProduct_as_Byte() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        if (!this.productDone) {
            this.calcProduct();
        }

        new Byte((byte)0);
        Byte var1;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 18:
                var1 = Conv.convert_Double_to_Byte((Double)this.productt.get(0));
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 16:
            case 17:
                if (this.productlongToDouble) {
                    var1 = Conv.convert_Double_to_Byte((Double)this.productt.get(0));
                } else {
                    var1 = Conv.convert_Long_to_Byte((Long)this.productt.get(0));
                }
                break;
            case 12:
                var1 = Conv.convert_BigDecimal_to_Byte((BigDecimal)this.productt.get(0));
                break;
            case 13:
                var1 = Conv.convert_BigInteger_to_Byte((BigInteger)this.productt.get(0));
                break;
            case 14:
            case 15:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a productas Byte is meaningful/supported");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var1;
    }

    public BigDecimal product_as_BigDecimal() {
        return this.getProduct_as_BigDecimal();
    }

    public BigDecimal getProduct_as_BigDecimal() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        if (!this.productDone) {
            this.calcProduct();
        }

        new BigDecimal(0.0D);
        BigDecimal var1;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 18:
                var1 = Conv.convert_Double_to_BigDecimal((Double)this.productt.get(0));
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 16:
            case 17:
                if (this.productlongToDouble) {
                    var1 = Conv.convert_Double_to_BigDecimal((Double)this.productt.get(0));
                } else {
                    var1 = Conv.convert_Long_to_BigDecimal((Long)this.productt.get(0));
                }
                break;
            case 12:
                var1 = (BigDecimal)this.productt.get(0);
                break;
            case 13:
                var1 = Conv.convert_BigInteger_to_BigDecimal((BigInteger)this.productt.get(0));
                break;
            case 14:
            case 15:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a productas BigDecimal is meaningful/supported");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var1;
    }

    public BigInteger product_as_BigInteger() {
        return this.getProduct_as_BigInteger();
    }

    public BigInteger getProduct_as_BigInteger() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        if (!this.productDone) {
            this.calcProduct();
        }

        BigInteger var1 = BigInteger.ZERO;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 18:
                var1 = Conv.convert_Double_to_BigInteger((Double)this.productt.get(0));
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 16:
            case 17:
                if (this.productlongToDouble) {
                    var1 = Conv.convert_Double_to_BigInteger((Double)this.productt.get(0));
                } else {
                    var1 = Conv.convert_Long_to_BigInteger((Long)this.productt.get(0));
                }
                break;
            case 12:
                var1 = Conv.convert_BigDecimal_to_BigInteger((BigDecimal)this.productt.get(0));
                break;
            case 13:
                var1 = (BigInteger)this.productt.get(0);
                break;
            case 14:
            case 15:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a productas BigInteger is meaningful/supported");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var1;
    }

    public Complex product_as_Complex() {
        return this.getProduct_as_Complex();
    }

    public Complex getProduct_as_Complex() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        if (!this.productDone) {
            this.calcProduct();
        }

        Complex var1 = Complex.zero();
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 18:
                var1 = new Complex((Double)this.productt.get(0));
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 16:
            case 17:
                if (this.productlongToDouble) {
                    var1 = new Complex((Double)this.productt.get(0));
                } else {
                    var1 = new Complex(((Long)this.productt.get(0)).doubleValue());
                }
                break;
            case 12:
                var1 = new Complex(((BigDecimal)this.productt.get(0)).doubleValue());
                break;
            case 13:
                var1 = new Complex(((BigInteger)this.productt.get(0)).doubleValue());
                break;
            case 14:
            case 15:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a productas Complex is meaningful/supported");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var1;
    }

    public Phasor product_as_Phasor() {
        return this.getProduct_as_Phasor();
    }

    public Phasor getProduct_as_Phasor() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        if (!this.productDone) {
            this.calcProduct();
        }

        Phasor var1 = Phasor.zero();
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 18:
                var1 = new Phasor((Double)this.productt.get(0));
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 16:
            case 17:
                if (this.productlongToDouble) {
                    var1 = new Phasor((Double)this.productt.get(0));
                } else {
                    var1 = new Phasor(((Long)this.productt.get(0)).doubleValue());
                }
                break;
            case 12:
                var1 = new Phasor(((BigDecimal)this.productt.get(0)).doubleValue());
                break;
            case 13:
                var1 = new Phasor(((BigInteger)this.productt.get(0)).doubleValue());
                break;
            case 14:
            case 15:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a productas Phasor is meaningful/supported");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var1;
    }

    public String product_as_String() {
        return this.getProduct_as_String();
    }

    public String getProduct_as_String() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        if (!this.productDone) {
            this.calcProduct();
        }

        String var1 = " ";
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 18:
                var1 = Double.toString((Double)this.productt.get(0));
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 16:
            case 17:
                if (this.productlongToDouble) {
                    var1 = Double.toString((Double)this.productt.get(0));
                } else {
                    var1 = Double.toString(((Long)this.productt.get(0)).doubleValue());
                }
                break;
            case 12:
                var1 = ((BigDecimal)this.productt.get(0)).toString();
                break;
            case 13:
                var1 = ((BigInteger)this.productt.get(0)).toString();
                break;
            case 14:
            case 15:
                throw new IllegalArgumentException("The " + this.typeName[this.type] + " is not a numerical type for which a productas String is meaningful/supported");
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var1;
    }

    public ArrayMaths randomize() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var1;
        var1 = new ArrayMaths();
        var1.array = new ArrayList();
        var1.length = this.length;
        var1.type = this.type;
        PsRandom var2 = new PsRandom();
        var1.sortedIndices = var2.uniqueIntegerArray(this.length - 1);
        int var3;
        label118:
        switch(this.type) {
            case 0:
            case 1:
                var3 = 0;

                while(true) {
                    if (var3 >= this.length) {
                        break label118;
                    }

                    var1.array.add((Double)this.array.get(var1.sortedIndices[var3]));
                    ++var3;
                }
            case 2:
            case 3:
                var3 = 0;

                while(true) {
                    if (var3 >= this.length) {
                        break label118;
                    }

                    var1.array.add((Float)this.array.get(var1.sortedIndices[var3]));
                    ++var3;
                }
            case 4:
            case 5:
                var3 = 0;

                while(true) {
                    if (var3 >= this.length) {
                        break label118;
                    }

                    var1.array.add((Long)this.array.get(var1.sortedIndices[var3]));
                    ++var3;
                }
            case 6:
            case 7:
                var3 = 0;

                while(true) {
                    if (var3 >= this.length) {
                        break label118;
                    }

                    var1.array.add((Integer)this.array.get(var1.sortedIndices[var3]));
                    ++var3;
                }
            case 8:
            case 9:
                var3 = 0;

                while(true) {
                    if (var3 >= this.length) {
                        break label118;
                    }

                    var1.array.add((Short)this.array.get(var1.sortedIndices[var3]));
                    ++var3;
                }
            case 10:
            case 11:
                var3 = 0;

                while(true) {
                    if (var3 >= this.length) {
                        break label118;
                    }

                    var1.array.add((Byte)this.array.get(var1.sortedIndices[var3]));
                    ++var3;
                }
            case 12:
                var3 = 0;

                while(true) {
                    if (var3 >= this.length) {
                        break label118;
                    }

                    var1.array.add((BigDecimal)this.array.get(var1.sortedIndices[var3]));
                    ++var3;
                }
            case 13:
                var3 = 0;

                while(true) {
                    if (var3 >= this.length) {
                        break label118;
                    }

                    var1.array.add((BigInteger)this.array.get(var1.sortedIndices[var3]));
                    ++var3;
                }
            case 14:
                var3 = 0;

                while(true) {
                    if (var3 >= this.length) {
                        break label118;
                    }

                    var1.array.add((Complex)this.array.get(var1.sortedIndices[var3]));
                    ++var3;
                }
            case 15:
                var3 = 0;

                while(true) {
                    if (var3 >= this.length) {
                        break label118;
                    }

                    var1.array.add((Phasor)this.array.get(var1.sortedIndices[var3]));
                    ++var3;
                }
            case 16:
            case 17:
                var3 = 0;

                while(true) {
                    if (var3 >= this.length) {
                        break label118;
                    }

                    var1.array.add((Character)this.array.get(var1.sortedIndices[var3]));
                    ++var3;
                }
            case 18:
                var3 = 0;

                while(true) {
                    if (var3 >= this.length) {
                        break label118;
                    }

                    var1.array.add((String)this.array.get(var1.sortedIndices[var3]));
                    ++var3;
                }
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        int[] var4 = new int[2];
        findMinMax(var1.getArray_as_Object(), var1.minmax, var4, var1.typeName, var1.type);
        var1.maxIndex = var4[0];
        var1.minIndex = var4[1];
        Conv.restoreMessages();
        return var1;
    }

    public ArrayMaths randomise() {
        return this.randomize();
    }

    public void ascendingSortEquals() {
        this.sortEquals();
    }

    public void sortEquals() {
        ArrayMaths var1 = this.sort();
        this.array = var1.array;
        this.sortedIndices = var1.sortedIndices;
        this.maxIndex = var1.maxIndex;
        this.minIndex = var1.minIndex;
    }

    public ArrayMaths ascendingSort() {
        return this.sort();
    }

    public ArrayMaths sort() {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var1;
        var1 = new ArrayMaths();
        var1.array = new ArrayList();
        var1.length = this.length;
        var1.type = this.type;
        var1.sortedIndices = new int[this.length];
        label131:
        switch(this.type) {
            case 0:
            case 1:
                double[] var2 = this.getArray_as_double();
                var1.sortedIndices = this.sortWithIndices(var2);
                int var21 = 0;

                while(true) {
                    if (var21 >= this.length) {
                        break label131;
                    }

                    var1.array.add((Double)this.array.get(var1.sortedIndices[var21]));
                    ++var21;
                }
            case 2:
            case 3:
                double[] var3 = this.getArray_as_double();
                var1.sortedIndices = this.sortWithIndices(var3);
                int var22 = 0;

                while(true) {
                    if (var22 >= this.length) {
                        break label131;
                    }

                    var1.array.add((Float)this.array.get(var1.sortedIndices[var22]));
                    ++var22;
                }
            case 4:
            case 5:
                long[] var4 = this.getArray_as_long();
                var1.sortedIndices = this.sortWithIndices(var4);
                int var23 = 0;

                while(true) {
                    if (var23 >= this.length) {
                        break label131;
                    }

                    var1.array.add((Long)this.array.get(var1.sortedIndices[var23]));
                    ++var23;
                }
            case 6:
            case 7:
                long[] var5 = this.getArray_as_long();
                var1.sortedIndices = this.sortWithIndices(var5);
                int var24 = 0;

                while(true) {
                    if (var24 >= this.length) {
                        break label131;
                    }

                    var1.array.add((Integer)this.array.get(var1.sortedIndices[var24]));
                    ++var24;
                }
            case 8:
            case 9:
                long[] var6 = this.getArray_as_long();
                var1.sortedIndices = this.sortWithIndices(var6);
                int var25 = 0;

                while(true) {
                    if (var25 >= this.length) {
                        break label131;
                    }

                    var1.array.add((Short)this.array.get(var1.sortedIndices[var25]));
                    ++var25;
                }
            case 10:
            case 11:
                long[] var7 = this.getArray_as_long();
                var1.sortedIndices = this.sortWithIndices(var7);
                int var26 = 0;

                while(true) {
                    if (var26 >= this.length) {
                        break label131;
                    }

                    var1.array.add((Byte)this.array.get(var1.sortedIndices[var26]));
                    ++var26;
                }
            case 12:
                BigDecimal[] var8 = this.getArray_as_BigDecimal();
                var1.sortedIndices = this.sortWithIndices(var8);
                int var27 = 0;

                while(true) {
                    if (var27 >= this.length) {
                        break label131;
                    }

                    var1.array.add((BigDecimal)this.array.get(var1.sortedIndices[var27]));
                    ++var27;
                }
            case 13:
                BigInteger[] var9 = this.getArray_as_BigInteger();
                var1.sortedIndices = this.sortWithIndices(var9);
                int var28 = 0;

                while(true) {
                    if (var28 >= this.length) {
                        break label131;
                    }

                    var1.array.add((BigInteger)this.array.get(var1.sortedIndices[var28]));
                    ++var28;
                }
            case 14:
                ArrayMaths var10 = this.abs();
                double[] var11 = var10.getArray_as_double();
                var1.sortedIndices = this.sortWithIndices(var11);
                int var29 = 0;

                while(true) {
                    if (var29 >= this.length) {
                        break label131;
                    }

                    var1.array.add((Complex)this.array.get(var1.sortedIndices[var29]));
                    ++var29;
                }
            case 15:
                ArrayMaths var12 = this.abs();
                double[] var13 = var12.getArray_as_double();
                var1.sortedIndices = this.sortWithIndices(var13);
                int var30 = 0;

                while(true) {
                    if (var30 >= this.length) {
                        break label131;
                    }

                    var1.array.add((Phasor)this.array.get(var1.sortedIndices[var30]));
                    ++var30;
                }
            case 16:
            case 17:
                long[] var14 = this.getArray_as_long();
                var1.sortedIndices = this.sortWithIndices(var14);
                int var31 = 0;

                while(true) {
                    if (var31 >= this.length) {
                        break label131;
                    }

                    var1.array.add((Character)this.array.get(var1.sortedIndices[var31]));
                    ++var31;
                }
            case 18:
                String[] var15 = this.getArray_as_String();
                String[] var16 = Conv.copy(var15);

                for(int var17 = 0; var17 < this.length; ++var17) {
                    var15[var17] = Strings.removeAccents(var15[var17]);
                }

                ArrayList var32 = this.alphabeticSort(var15);
                String[] var18 = (String[])((String[])var32.get(0));
                var1.sortedIndices = (int[])((int[])var32.get(1));
                int var19 = 0;

                while(true) {
                    if (var19 >= this.length) {
                        break label131;
                    }

                    var1.array.add(var16[var1.sortedIndices[var19]]);
                    ++var19;
                }
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        if (this.type != 18) {
            int[] var20 = new int[2];
            findMinMax(var1.getArray_as_Object(), var1.minmax, var20, var1.typeName, var1.type);
            var1.maxIndex = var20[0];
            var1.minIndex = var20[1];
        }

        Conv.restoreMessages();
        return var1;
    }

    public void descendingSortEquals() {
        ArrayMaths var1 = this.descendingSort();
        this.array = var1.array;
        this.sortedIndices = var1.sortedIndices;
        this.maxIndex = var1.maxIndex;
        this.minIndex = var1.minIndex;
    }

    public ArrayMaths descendingSort() {
        ArrayMaths var1 = null;
        boolean var2 = false;
        int var4;
        if (this.array.get(0) instanceof Double) {
            double[] var3 = new double[this.array.size()];

            for(var4 = 0; var4 < this.array.size(); ++var4) {
                var3[var4] = (Double)this.array.get(var4);
            }

            var1 = new ArrayMaths(var3);
            var2 = true;
        }

        if (this.array.get(0) instanceof Float) {
            float[] var8 = new float[this.array.size()];

            for(var4 = 0; var4 < this.array.size(); ++var4) {
                var8[var4] = (Float)this.array.get(var4);
            }

            var1 = new ArrayMaths(var8);
            var2 = true;
        }

        if (this.array.get(0) instanceof Long) {
            long[] var9 = new long[this.array.size()];

            for(var4 = 0; var4 < this.array.size(); ++var4) {
                var9[var4] = (Long)this.array.get(var4);
            }

            var1 = new ArrayMaths(var9);
            var2 = true;
        }

        if (this.array.get(0) instanceof Integer) {
            int[] var10 = new int[this.array.size()];

            for(var4 = 0; var4 < this.array.size(); ++var4) {
                var10[var4] = (Integer)this.array.get(var4);
            }

            var1 = new ArrayMaths(var10);
            var2 = true;
        }

        if (this.array.get(0) instanceof Character) {
            char[] var11 = new char[this.array.size()];

            for(var4 = 0; var4 < this.array.size(); ++var4) {
                var11[var4] = (Character)this.array.get(var4);
            }

            var1 = new ArrayMaths(var11);
            var2 = true;
        }

        if (this.array.get(0) instanceof String) {
            String[] var12 = new String[this.array.size()];

            for(var4 = 0; var4 < this.array.size(); ++var4) {
                var12[var4] = (String)this.array.get(var4);
            }

            var1 = new ArrayMaths(var12);
            var2 = true;
        }

        if (this.array.get(0) instanceof Short) {
            short[] var13 = new short[this.array.size()];

            for(var4 = 0; var4 < this.array.size(); ++var4) {
                var13[var4] = (Short)this.array.get(var4);
            }

            var1 = new ArrayMaths(var13);
            var2 = true;
        }

        if (this.array.get(0) instanceof BigDecimal) {
            BigDecimal[] var14 = new BigDecimal[this.array.size()];

            for(var4 = 0; var4 < this.array.size(); ++var4) {
                var14[var4] = (BigDecimal)this.array.get(var4);
            }

            var1 = new ArrayMaths(var14);
            var2 = true;
        }

        if (this.array.get(0) instanceof BigInteger) {
            BigInteger[] var15 = new BigInteger[this.array.size()];

            for(var4 = 0; var4 < this.array.size(); ++var4) {
                var15[var4] = (BigInteger)this.array.get(var4);
            }

            var1 = new ArrayMaths(var15);
            var2 = true;
        }

        if (this.array.get(0) instanceof Complex) {
            Complex[] var16 = new Complex[this.array.size()];

            for(var4 = 0; var4 < this.array.size(); ++var4) {
                var16[var4] = (Complex)this.array.get(var4);
            }

            var1 = new ArrayMaths(var16);
            var2 = true;
        }

        if (this.array.get(0) instanceof Phasor) {
            Phasor[] var17 = new Phasor[this.array.size()];

            for(var4 = 0; var4 < this.array.size(); ++var4) {
                var17[var4] = (Phasor)this.array.get(var4);
            }

            var1 = new ArrayMaths(var17);
            var2 = true;
        }

        if (!var2) {
            var1 = new ArrayMaths(this.array);
        }

        ArrayMaths var18 = var1.sort();
        int[] var20 = var18.originalIndices();
        int[] var5 = DeepCopy.copy(var20);
        int var6 = var20.length;

        for(int var7 = 0; var7 < var6; ++var7) {
            var20[var7] = var5[var6 - var7 - 1];
        }

        ArrayMaths var19 = var18.reverse();
        var19.resetSortedIndices(var20);
        return var19;
    }

    public ArrayList<Object> alphabeticSort(String[] var1) {
        return this.alphabeticAtoZsort(var1);
    }

    public ArrayList<Object> alphabeticAtoZsort(String[] var1) {
        this.nWords = var1.length;
        this.words = new String[this.nWords];
        this.sortedWords = new String[this.nWords];

        int var2;
        for(var2 = 0; var2 < this.nWords; ++var2) {
            this.words[var2] = var1[var2];
            this.sortedWords[var2] = this.words[var2];
        }

        this.alphabeticSortIndices(this.words);

        for(var2 = 0; var2 < this.nWords; ++var2) {
            this.sortedWords[var2] = this.words[this.wordOrder[var2]];
        }

        ArrayList var3 = new ArrayList();
        var3.add(this.sortedWords);
        var3.add(this.wordOrder);
        return var3;
    }

    public ArrayList<Object> alphabeticZtoASort(String[] var1) {
        this.nWords = var1.length;
        this.words = new String[this.nWords];
        this.sortedWords = new String[this.nWords];
        int[] var2 = new int[this.nWords];

        int var3;
        for(var3 = 0; var3 < this.nWords; ++var3) {
            this.words[var3] = var1[var3];
            this.sortedWords[var3] = this.words[var3];
        }

        this.alphabeticSortIndices(this.words);

        for(var3 = 0; var3 < this.nWords; ++var3) {
            this.sortedWords[this.nWords - var3 - 1] = this.words[this.wordOrder[var3]];
            var2[var3] = this.wordOrder[this.nWords - var3 - 1];
        }

        this.wordOrder = (int[])((int[])var2.clone());
        ArrayList var4 = new ArrayList();
        var4.add(this.sortedWords);
        var4.add(this.wordOrder);
        return var4;
    }

    public void alphabeticSortIndices(String[] var1) {
        String[] var2 = new String[this.nWords];
        this.nLength = 0;
        boolean var3 = false;

        int var4;
        for(var4 = 0; var4 < this.nWords; ++var4) {
            int var7 = var1[var4].length();
            if (var7 > this.nLength) {
                this.nLength = var7;
            }

            var2[var4] = var1[var4].toLowerCase();
        }

        this.wordChar = new char[this.nWords][this.nLength];
        this.holdWordChar = new char[this.nWords][this.nLength];

        for(var4 = 0; var4 < this.nWords; ++var4) {
            char[] var5 = var2[var4].toCharArray();

            int var6;
            for(var6 = 0; var6 < var2[var4].length(); ++var6) {
                this.wordChar[var4][var6] = var5[var6];
                this.holdWordChar[var4][var6] = var5[var6];
            }

            for(var6 = var2[var4].length(); var6 < this.nLength; ++var6) {
                this.wordChar[var4][var6] = ' ';
                this.holdWordChar[var4][var6] = ' ';
            }
        }

        this.wordOrder = new int[this.nWords];
        this.holdWordOrder = new int[this.nWords];

        for(var4 = 0; var4 < this.nWords; this.holdWordOrder[var4] = var4++) {
            this.wordOrder[var4] = var4;
        }

        if (this.nWords > 1) {
            this.indexSort(0, 0, this.nWords - 1);
        }

    }

    public void indexSort(int var1, int var2, int var3) {
        int var4 = var3 - var2 + 1;
        char[] var5 = new char[var4];
        int[] var6 = new int[var4];

        for(int var7 = 0; var7 < var4; ++var7) {
            var5[var7] = this.wordChar[var7 + var2][var1];
            var6[var7] = this.wordOrder[var7 + var2];
        }

        int[] var17 = this.sortByColumnInitial(var5);
        int[] var8 = new int[var4];

        int var9;
        for(var9 = 0; var9 < var4; ++var9) {
            var8[var9] = var6[var17[var9]];
        }

        for(var9 = 0; var9 < var4; ++var9) {
            this.holdWordOrder[var9 + var2] = var8[var9];
            this.holdWordChar[var9 + var2] = this.wordChar[var17[var9] + var2];
        }

        for(var9 = 0; var9 < this.nWords; ++var9) {
            this.wordChar[var9] = this.holdWordChar[var9];
            this.wordOrder[var9] = this.holdWordOrder[var9];
        }

        ArrayList var18 = null;
        if (var1 < this.nLength - 1) {
            int[] var10 = new int[var4 + 1];
            int var11 = 1;
            var10[0] = var11;
            int var12 = 1;

            int var13;
            for(var13 = var2 + 1; var13 <= var3; ++var13) {
                if (this.wordChar[var13][var1] == this.wordChar[var13 - 1][var1]) {
                    var10[var12] = var11;
                } else {
                    var11 = -var11;
                    var10[var12] = var11;
                }

                ++var12;
            }

            var18 = new ArrayList();
            var12 = var2;
            var18.add(new Integer(var2));

            for(var13 = 1; var13 <= var4; ++var13) {
                ++var12;
                if (var10[var13] != var10[var13 - 1]) {
                    var18.add(new Integer(var12 - 1));
                    var18.add(new Integer(var12));
                }
            }

            var18.add(new Integer(var3));
            var13 = var18.size();

            for(int var14 = 0; var14 < var13; var14 += 2) {
                int var15 = (Integer)var18.get(var14);
                int var16 = (Integer)var18.get(var14 + 1);
                if (var16 - var15 + 1 > 1) {
                    this.indexSort(var1 + 1, var15, var16);
                }
            }
        }

    }

    public int[] sortByColumnInitial(char[] var1) {
        int var2 = var1.length;
        int[] var3 = new int[var2];

        for(int var4 = 0; var4 < var2; ++var4) {
            var3[var4] = var1[var4];
        }

        ArrayMaths var8 = new ArrayMaths(var3);
        ArrayMaths var5 = var8.sort();
        int[] var6 = var5.array_as_int();
        int[] var7 = var5.originalIndices();
        return var7;
    }

    public void sortEquals(int[] var1) {
        ArrayMaths var2 = this.sort(var1);
        this.array = var2.array;
        this.sortedIndices = var2.sortedIndices;
        this.maxIndex = var2.maxIndex;
        this.minIndex = var2.minIndex;
    }

    public ArrayMaths sort(int[] var1) {
        int var2 = var1.length;
        if (this.length != var2) {
            throw new IllegalArgumentException("The argument array [length = " + var2 + "], must be of the same length as this instance array [length = " + this.length + "]");
        } else {
            if (this.suppressMessages) {
                Conv.suppressMessages();
            }

            ArrayMaths var3;
            var3 = new ArrayMaths();
            var3.array = new ArrayList();
            var3.length = this.length;
            var3.type = this.type;
            var3.sortedIndices = var1;
            int var4;
            label124:
            switch(this.type) {
                case 0:
                case 1:
                    var4 = 0;

                    while(true) {
                        if (var4 >= this.length) {
                            break label124;
                        }

                        var3.array.add((Double)this.array.get(var3.sortedIndices[var4]));
                        ++var4;
                    }
                case 2:
                case 3:
                    var4 = 0;

                    while(true) {
                        if (var4 >= this.length) {
                            break label124;
                        }

                        var3.array.add((Float)this.array.get(var3.sortedIndices[var4]));
                        ++var4;
                    }
                case 4:
                case 5:
                    var4 = 0;

                    while(true) {
                        if (var4 >= this.length) {
                            break label124;
                        }

                        var3.array.add((Long)this.array.get(var3.sortedIndices[var4]));
                        ++var4;
                    }
                case 6:
                case 7:
                    var4 = 0;

                    while(true) {
                        if (var4 >= this.length) {
                            break label124;
                        }

                        var3.array.add((Integer)this.array.get(var3.sortedIndices[var4]));
                        ++var4;
                    }
                case 8:
                case 9:
                    var4 = 0;

                    while(true) {
                        if (var4 >= this.length) {
                            break label124;
                        }

                        var3.array.add((Short)this.array.get(var3.sortedIndices[var4]));
                        ++var4;
                    }
                case 10:
                case 11:
                    var4 = 0;

                    while(true) {
                        if (var4 >= this.length) {
                            break label124;
                        }

                        var3.array.add((Byte)this.array.get(var3.sortedIndices[var4]));
                        ++var4;
                    }
                case 12:
                    var4 = 0;

                    while(true) {
                        if (var4 >= this.length) {
                            break label124;
                        }

                        var3.array.add((BigDecimal)this.array.get(var3.sortedIndices[var4]));
                        ++var4;
                    }
                case 13:
                    var4 = 0;

                    while(true) {
                        if (var4 >= this.length) {
                            break label124;
                        }

                        var3.array.add((BigInteger)this.array.get(var3.sortedIndices[var4]));
                        ++var4;
                    }
                case 14:
                    var4 = 0;

                    while(true) {
                        if (var4 >= this.length) {
                            break label124;
                        }

                        var3.array.add((Complex)this.array.get(var3.sortedIndices[var4]));
                        ++var4;
                    }
                case 15:
                    var4 = 0;

                    while(true) {
                        if (var4 >= this.length) {
                            break label124;
                        }

                        var3.array.add((Phasor)this.array.get(var3.sortedIndices[var4]));
                        ++var4;
                    }
                case 16:
                case 17:
                    var4 = 0;

                    while(true) {
                        if (var4 >= this.length) {
                            break label124;
                        }

                        var3.array.add((Character)this.array.get(var3.sortedIndices[var4]));
                        ++var4;
                    }
                case 18:
                    var4 = 0;

                    while(true) {
                        if (var4 >= this.length) {
                            break label124;
                        }

                        var3.array.add((String)this.array.get(var3.sortedIndices[var4]));
                        ++var4;
                    }
                default:
                    throw new IllegalArgumentException("Data type not identified by this method");
            }

            int[] var5 = new int[2];
            if (this.type != 18) {
                findMinMax(var3.getArray_as_Object(), var3.minmax, var5, var3.typeName, var3.type);
            }

            var3.maxIndex = var5[0];
            var3.minIndex = var5[1];
            Conv.restoreMessages();
            return var3;
        }
    }

    protected int[] sortWithIndices(double[] var1) {
        boolean var2 = false;
        int var3 = -1;
        double var4 = 0.0D;
        boolean var6 = false;
        double[] var7 = new double[this.length];
        int[] var8 = new int[this.length];

        int var9;
        for(var9 = 0; var9 < this.length; var8[var9] = var9++) {
            var7[var9] = var1[var9];
        }

        while(var3 != this.length - 1) {
            int var10 = var3 + 1;

            for(var9 = var3 + 2; var9 < this.length; ++var9) {
                if (var7[var9] < var7[var10]) {
                    var10 = var9;
                }
            }

            ++var3;
            var4 = var7[var10];
            var7[var10] = var7[var3];
            var7[var3] = var4;
            int var11 = var8[var10];
            var8[var10] = var8[var3];
            var8[var3] = var11;
        }

        return var8;
    }

    protected int[] sortWithIndices(long[] var1) {
        boolean var2 = false;
        int var3 = -1;
        long var4 = 0L;
        boolean var6 = false;
        long[] var7 = new long[this.length];
        int[] var8 = new int[this.length];

        int var9;
        for(var9 = 0; var9 < this.length; var8[var9] = var9++) {
            var7[var9] = var1[var9];
        }

        while(var3 != this.length - 1) {
            int var10 = var3 + 1;

            for(var9 = var3 + 2; var9 < this.length; ++var9) {
                if (var7[var9] < var7[var10]) {
                    var10 = var9;
                }
            }

            ++var3;
            var4 = var7[var10];
            var7[var10] = var7[var3];
            var7[var3] = var4;
            int var11 = var8[var10];
            var8[var10] = var8[var3];
            var8[var3] = var11;
        }

        return var8;
    }

    protected int[] sortWithIndices(BigDecimal[] var1) {
        boolean var2 = false;
        int var3 = -1;
        BigDecimal var4 = BigDecimal.ZERO;
        boolean var5 = false;
        BigDecimal[] var6 = new BigDecimal[this.length];
        int[] var7 = new int[this.length];

        int var8;
        for(var8 = 0; var8 < this.length; var7[var8] = var8++) {
            var6[var8] = var1[var8];
        }

        while(var3 != this.length - 1) {
            int var9 = var3 + 1;

            for(var8 = var3 + 2; var8 < this.length; ++var8) {
                if (var6[var8].compareTo(var6[var9]) == -1) {
                    var9 = var8;
                }
            }

            ++var3;
            var4 = var6[var9];
            var6[var9] = var6[var3];
            var6[var3] = var4;
            int var10 = var7[var9];
            var7[var9] = var7[var3];
            var7[var3] = var10;
        }

        var4 = null;
        return var7;
    }

    protected int[] sortWithIndices(BigInteger[] var1) {
        boolean var2 = false;
        int var3 = -1;
        BigInteger var4 = BigInteger.ZERO;
        boolean var5 = false;
        BigInteger[] var6 = new BigInteger[this.length];
        int[] var7 = new int[this.length];

        int var8;
        for(var8 = 0; var8 < this.length; var7[var8] = var8++) {
            var6[var8] = var1[var8];
        }

        while(var3 != this.length - 1) {
            int var9 = var3 + 1;

            for(var8 = var3 + 2; var8 < this.length; ++var8) {
                if (var6[var8].compareTo(var6[var9]) == -1) {
                    var9 = var8;
                }
            }

            ++var3;
            var4 = var6[var9];
            var6[var9] = var6[var3];
            var6[var3] = var4;
            int var10 = var7[var9];
            var7[var9] = var7[var3];
            var7[var3] = var10;
        }

        var4 = null;
        return var7;
    }

    public int[] originalIndices() {
        if (this.sortedIndices == null) {
            System.out.println("method: originalIndices: array has not been sorted: null returned");
        }

        return this.sortedIndices;
    }

    public ArrayMaths concatenate(double[] var1) {
        int var2 = var1.length;
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var3 = new ArrayMaths();
        var3.array = new ArrayList();
        var3.length = var1.length + this.length;
        ArrayMaths var4 = null;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 16:
            case 17:
                double[] var5 = this.getArray_as_double();
                double[] var6 = new double[var3.length];

                int var21;
                for(var21 = 0; var21 < this.length; ++var21) {
                    var6[var21] = var5[var21];
                }

                for(var21 = 0; var21 < var2; ++var21) {
                    var6[var21 + this.length] = var1[var21];
                }

                for(var21 = 0; var21 < var3.length; ++var21) {
                    var3.array.add(new Double(var6[var21]));
                }

                var3.type = 1;
                break;
            case 12:
            case 13:
                BigDecimal[] var7 = this.getArray_as_BigDecimal();
                var4 = new ArrayMaths(var1);
                BigDecimal[] var8 = var4.getArray_as_BigDecimal();
                BigDecimal[] var9 = new BigDecimal[var3.length];

                int var22;
                for(var22 = 0; var22 < this.length; ++var22) {
                    var9[var22] = var7[var22];
                }

                for(var22 = 0; var22 < var2; ++var22) {
                    var9[var22 + this.length] = var8[var22];
                }

                for(var22 = 0; var22 < var3.length; ++var22) {
                    var3.array.add(var9[var22]);
                }

                var7 = null;
                var8 = null;
                var9 = null;
                var3.type = 12;
                break;
            case 14:
                Complex[] var10 = this.getArray_as_Complex();
                var4 = new ArrayMaths(var1);
                Complex[] var11 = var4.getArray_as_Complex();
                Complex[] var12 = new Complex[var3.length];

                int var23;
                for(var23 = 0; var23 < this.length; ++var23) {
                    var12[var23] = var10[var23];
                }

                for(var23 = 0; var23 < var2; ++var23) {
                    var12[var23 + this.length] = var11[var23];
                }

                for(var23 = 0; var23 < var3.length; ++var23) {
                    var3.array.add(var12[var23]);
                }

                var3.type = 14;
                break;
            case 15:
                Phasor[] var13 = this.getArray_as_Phasor();
                var4 = new ArrayMaths(var1);
                Phasor[] var14 = var4.getArray_as_Phasor();
                Phasor[] var15 = new Phasor[var3.length];

                int var24;
                for(var24 = 0; var24 < this.length; ++var24) {
                    var15[var24] = var13[var24];
                }

                for(var24 = 0; var24 < var2; ++var24) {
                    var15[var24 + this.length] = var14[var24];
                }

                for(var24 = 0; var24 < var3.length; ++var24) {
                    var3.array.add(var15[var24]);
                }

                var3.type = 15;
                break;
            case 18:
                String[] var16 = this.getArray_as_String();
                var4 = new ArrayMaths(var1);
                String[] var17 = var4.getArray_as_String();
                String[] var18 = new String[var3.length];

                int var19;
                for(var19 = 0; var19 < this.length; ++var19) {
                    var18[var19] = var16[var19];
                }

                for(var19 = 0; var19 < var2; ++var19) {
                    var18[var19 + this.length] = var17[var19];
                }

                for(var19 = 0; var19 < var3.length; ++var19) {
                    var3.array.add(var18[var19]);
                }

                var3.type = 18;
                break;
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        int[] var20 = new int[2];
        findMinMax(var3.getArray_as_Object(), var3.minmax, var20, var3.typeName, var3.type);
        var3.maxIndex = var20[0];
        var3.minIndex = var20[1];
        Conv.restoreMessages();
        return var3;
    }

    public ArrayMaths concatenate(Double[] var1) {
        double[] var2 = new double[var1.length];

        for(int var3 = 0; var3 < var1.length; ++var3) {
            var2[var3] = var1[var3];
        }

        return this.concatenate(var2);
    }

    public ArrayMaths concatenate(float[] var1) {
        int var2 = var1.length;
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var3 = new ArrayMaths();
        var3.array = new ArrayList();
        var3.length = var1.length + this.length;
        ArrayMaths var4 = null;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
                double[] var5 = this.getArray_as_double();
                double[] var6 = new double[var3.length];

                int var23;
                for(var23 = 0; var23 < this.length; ++var23) {
                    var6[var23] = var5[var23];
                }

                for(var23 = 0; var23 < var2; ++var23) {
                    var6[var23 + this.length] = (double)var1[var23];
                }

                for(var23 = 0; var23 < var3.length; ++var23) {
                    var3.array.add(new Double(var6[var23]));
                }

                var3.type = 1;
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 16:
            case 17:
                float[] var7 = this.getArray_as_float();
                float[] var8 = new float[var3.length];

                int var24;
                for(var24 = 0; var24 < this.length; ++var24) {
                    var8[var24] = var7[var24];
                }

                for(var24 = 0; var24 < var2; ++var24) {
                    var8[var24 + this.length] = var1[var24];
                }

                for(var24 = 0; var24 < var3.length; ++var24) {
                    var3.array.add(new Float(var8[var24]));
                }

                var3.type = 3;
                break;
            case 12:
            case 13:
                BigDecimal[] var9 = this.getArray_as_BigDecimal();
                var4 = new ArrayMaths(var1);
                BigDecimal[] var10 = var4.getArray_as_BigDecimal();
                BigDecimal[] var11 = new BigDecimal[var3.length];

                int var25;
                for(var25 = 0; var25 < this.length; ++var25) {
                    var11[var25] = var9[var25];
                }

                for(var25 = 0; var25 < var2; ++var25) {
                    var11[var25 + this.length] = var10[var25];
                }

                for(var25 = 0; var25 < var3.length; ++var25) {
                    var3.array.add(var11[var25]);
                }

                var9 = null;
                var10 = null;
                var11 = null;
                var3.type = 12;
                break;
            case 14:
                Complex[] var12 = this.getArray_as_Complex();
                var4 = new ArrayMaths(var1);
                Complex[] var13 = var4.getArray_as_Complex();
                Complex[] var14 = new Complex[var3.length];

                int var26;
                for(var26 = 0; var26 < this.length; ++var26) {
                    var14[var26] = var12[var26];
                }

                for(var26 = 0; var26 < var2; ++var26) {
                    var14[var26 + this.length] = var13[var26];
                }

                for(var26 = 0; var26 < var3.length; ++var26) {
                    var3.array.add(var14[var26]);
                }

                var3.type = 14;
                break;
            case 15:
                Phasor[] var15 = this.getArray_as_Phasor();
                var4 = new ArrayMaths(var1);
                Phasor[] var16 = var4.getArray_as_Phasor();
                Phasor[] var17 = new Phasor[var3.length];

                int var27;
                for(var27 = 0; var27 < this.length; ++var27) {
                    var17[var27] = var15[var27];
                }

                for(var27 = 0; var27 < var2; ++var27) {
                    var17[var27 + this.length] = var16[var27];
                }

                for(var27 = 0; var27 < var3.length; ++var27) {
                    var3.array.add(var17[var27]);
                }

                var3.type = 15;
                break;
            case 18:
                String[] var18 = this.getArray_as_String();
                var4 = new ArrayMaths(var1);
                String[] var19 = var4.getArray_as_String();
                String[] var20 = new String[var3.length];

                int var21;
                for(var21 = 0; var21 < this.length; ++var21) {
                    var20[var21] = var18[var21];
                }

                for(var21 = 0; var21 < var2; ++var21) {
                    var20[var21 + this.length] = var19[var21];
                }

                for(var21 = 0; var21 < var3.length; ++var21) {
                    var3.array.add(var20[var21]);
                }

                var3.type = 18;
                break;
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        int[] var22 = new int[2];
        findMinMax(var3.getArray_as_Object(), var3.minmax, var22, var3.typeName, var3.type);
        var3.maxIndex = var22[0];
        var3.minIndex = var22[1];
        Conv.restoreMessages();
        return var3;
    }

    public ArrayMaths concatenate(Float[] var1) {
        float[] var2 = new float[var1.length];

        for(int var3 = 0; var3 < var1.length; ++var3) {
            var2[var3] = var1[var3];
        }

        return this.concatenate(var2);
    }

    public ArrayMaths concatenate(long[] var1) {
        int var2 = var1.length;
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var3 = new ArrayMaths();
        var3.array = new ArrayList();
        var3.length = var1.length + this.length;
        ArrayMaths var4 = null;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
                double[] var5 = this.getArray_as_double();
                double[] var6 = new double[var3.length];

                int var26;
                for(var26 = 0; var26 < this.length; ++var26) {
                    var6[var26] = var5[var26];
                }

                for(var26 = 0; var26 < var2; ++var26) {
                    var6[var26 + this.length] = (double)var1[var26];
                }

                for(var26 = 0; var26 < var3.length; ++var26) {
                    var3.array.add(new Double(var6[var26]));
                }

                var3.type = 1;
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 16:
            case 17:
                long[] var7 = this.getArray_as_long();
                long[] var8 = new long[var3.length];

                int var27;
                for(var27 = 0; var27 < this.length; ++var27) {
                    var8[var27] = var7[var27];
                }

                for(var27 = 0; var27 < var2; ++var27) {
                    var8[var27 + this.length] = var1[var27];
                }

                for(var27 = 0; var27 < var3.length; ++var27) {
                    var3.array.add(new Long(var8[var27]));
                }

                var3.type = 3;
                break;
            case 12:
                BigDecimal[] var9 = this.getArray_as_BigDecimal();
                var4 = new ArrayMaths(var1);
                BigDecimal[] var10 = var4.getArray_as_BigDecimal();
                BigDecimal[] var11 = new BigDecimal[var3.length];

                int var28;
                for(var28 = 0; var28 < this.length; ++var28) {
                    var11[var28] = var9[var28];
                }

                for(var28 = 0; var28 < var2; ++var28) {
                    var11[var28 + this.length] = var10[var28];
                }

                for(var28 = 0; var28 < var3.length; ++var28) {
                    var3.array.add(var11[var28]);
                }

                var9 = null;
                var10 = null;
                var11 = null;
                var3.type = 12;
                break;
            case 13:
                BigInteger[] var12 = this.getArray_as_BigInteger();
                var4 = new ArrayMaths(var1);
                BigInteger[] var13 = var4.getArray_as_BigInteger();
                BigInteger[] var14 = new BigInteger[var3.length];

                int var29;
                for(var29 = 0; var29 < this.length; ++var29) {
                    var14[var29] = var12[var29];
                }

                for(var29 = 0; var29 < var2; ++var29) {
                    var14[var29 + this.length] = var13[var29];
                }

                for(var29 = 0; var29 < var3.length; ++var29) {
                    var3.array.add(var14[var29]);
                }

                var12 = null;
                var13 = null;
                var14 = null;
                var3.type = 13;
                break;
            case 14:
                Complex[] var15 = this.getArray_as_Complex();
                var4 = new ArrayMaths(var1);
                Complex[] var16 = var4.getArray_as_Complex();
                Complex[] var17 = new Complex[var3.length];

                int var30;
                for(var30 = 0; var30 < this.length; ++var30) {
                    var17[var30] = var15[var30];
                }

                for(var30 = 0; var30 < var2; ++var30) {
                    var17[var30 + this.length] = var16[var30];
                }

                for(var30 = 0; var30 < var3.length; ++var30) {
                    var3.array.add(var17[var30]);
                }

                var3.type = 14;
                break;
            case 15:
                Phasor[] var18 = this.getArray_as_Phasor();
                var4 = new ArrayMaths(var1);
                Phasor[] var19 = var4.getArray_as_Phasor();
                Phasor[] var20 = new Phasor[var3.length];

                int var31;
                for(var31 = 0; var31 < this.length; ++var31) {
                    var20[var31] = var18[var31];
                }

                for(var31 = 0; var31 < var2; ++var31) {
                    var20[var31 + this.length] = var19[var31];
                }

                for(var31 = 0; var31 < var3.length; ++var31) {
                    var3.array.add(var20[var31]);
                }

                var3.type = 15;
                break;
            case 18:
                String[] var21 = this.getArray_as_String();
                var4 = new ArrayMaths(var1);
                String[] var22 = var4.getArray_as_String();
                String[] var23 = new String[var3.length];

                int var24;
                for(var24 = 0; var24 < this.length; ++var24) {
                    var23[var24] = var21[var24];
                }

                for(var24 = 0; var24 < var2; ++var24) {
                    var23[var24 + this.length] = var22[var24];
                }

                for(var24 = 0; var24 < var3.length; ++var24) {
                    var3.array.add(var23[var24]);
                }

                var3.type = 18;
                break;
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        int[] var25 = new int[2];
        findMinMax(var3.getArray_as_Object(), var3.minmax, var25, var3.typeName, var3.type);
        var3.maxIndex = var25[0];
        var3.minIndex = var25[1];
        Conv.restoreMessages();
        return var3;
    }

    public ArrayMaths concatenate(Long[] var1) {
        long[] var2 = new long[var1.length];

        for(int var3 = 0; var3 < var1.length; ++var3) {
            var2[var3] = var1[var3];
        }

        return this.concatenate(var2);
    }

    public ArrayMaths concatenate(int[] var1) {
        long[] var2 = new long[var1.length];

        for(int var3 = 0; var3 < var1.length; ++var3) {
            var2[var3] = (long)var1[var3];
        }

        return this.concatenate(var2);
    }

    public ArrayMaths concatenate(Integer[] var1) {
        int[] var2 = new int[var1.length];

        for(int var3 = 0; var3 < var1.length; ++var3) {
            var2[var3] = var1[var3];
        }

        return this.concatenate(var2);
    }

    public ArrayMaths concatenate(short[] var1) {
        long[] var2 = new long[var1.length];

        for(int var3 = 0; var3 < var1.length; ++var3) {
            var2[var3] = (long)var1[var3];
        }

        return this.concatenate(var2);
    }

    public ArrayMaths concatenate(Short[] var1) {
        short[] var2 = new short[var1.length];

        for(int var3 = 0; var3 < var1.length; ++var3) {
            var2[var3] = var1[var3];
        }

        return this.concatenate(var2);
    }

    public ArrayMaths concatenate(byte[] var1) {
        long[] var2 = new long[var1.length];

        for(int var3 = 0; var3 < var1.length; ++var3) {
            var2[var3] = (long)var1[var3];
        }

        return this.concatenate(var2);
    }

    public ArrayMaths concatenate(Byte[] var1) {
        byte[] var2 = new byte[var1.length];

        for(int var3 = 0; var3 < var1.length; ++var3) {
            var2[var3] = var1[var3];
        }

        return this.concatenate(var2);
    }

    public ArrayMaths concatenate(BigDecimal[] var1) {
        int var2 = var1.length;
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var3 = new ArrayMaths();
        var3.array = new ArrayList();
        var3.length = var1.length + this.length;
        ArrayMaths var4 = null;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 16:
            case 17:
                BigDecimal[] var5 = this.getArray_as_BigDecimal();
                var4 = new ArrayMaths(var1);
                BigDecimal[] var6 = var4.getArray_as_BigDecimal();
                BigDecimal[] var7 = new BigDecimal[var3.length];

                int var19;
                for(var19 = 0; var19 < this.length; ++var19) {
                    var7[var19] = var5[var19];
                }

                for(var19 = 0; var19 < var2; ++var19) {
                    var7[var19 + this.length] = var6[var19];
                }

                for(var19 = 0; var19 < var3.length; ++var19) {
                    var3.array.add(var7[var19]);
                }

                var5 = null;
                var6 = null;
                var7 = null;
                var3.type = 12;
                break;
            case 14:
                Complex[] var8 = this.getArray_as_Complex();
                var4 = new ArrayMaths(var1);
                Complex[] var9 = var4.getArray_as_Complex();
                Complex[] var10 = new Complex[var3.length];

                int var20;
                for(var20 = 0; var20 < this.length; ++var20) {
                    var10[var20] = var8[var20];
                }

                for(var20 = 0; var20 < var2; ++var20) {
                    var10[var20 + this.length] = var9[var20];
                }

                for(var20 = 0; var20 < var3.length; ++var20) {
                    var3.array.add(var10[var20]);
                }

                var3.type = 14;
                break;
            case 15:
                Phasor[] var11 = this.getArray_as_Phasor();
                var4 = new ArrayMaths(var1);
                Phasor[] var12 = var4.getArray_as_Phasor();
                Phasor[] var13 = new Phasor[var3.length];

                int var21;
                for(var21 = 0; var21 < this.length; ++var21) {
                    var13[var21] = var11[var21];
                }

                for(var21 = 0; var21 < var2; ++var21) {
                    var13[var21 + this.length] = var12[var21];
                }

                for(var21 = 0; var21 < var3.length; ++var21) {
                    var3.array.add(var13[var21]);
                }

                var3.type = 15;
                break;
            case 18:
                String[] var14 = this.getArray_as_String();
                var4 = new ArrayMaths(var1);
                String[] var15 = var4.getArray_as_String();
                String[] var16 = new String[var3.length];

                int var17;
                for(var17 = 0; var17 < this.length; ++var17) {
                    var16[var17] = var14[var17];
                }

                for(var17 = 0; var17 < var2; ++var17) {
                    var16[var17 + this.length] = var15[var17];
                }

                for(var17 = 0; var17 < var3.length; ++var17) {
                    var3.array.add(var16[var17]);
                }

                var3.type = 18;
                break;
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        int[] var18 = new int[2];
        findMinMax(var3.getArray_as_Object(), var3.minmax, var18, var3.typeName, var3.type);
        var3.maxIndex = var18[0];
        var3.minIndex = var18[1];
        Conv.restoreMessages();
        return var3;
    }

    public ArrayMaths concatenate(BigInteger[] var1) {
        int var2 = var1.length;
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var3 = new ArrayMaths();
        var3.array = new ArrayList();
        var3.length = var1.length + this.length;
        ArrayMaths var4 = null;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 12:
                BigDecimal[] var5 = this.getArray_as_BigDecimal();
                var4 = new ArrayMaths(var1);
                BigDecimal[] var6 = var4.getArray_as_BigDecimal();
                BigDecimal[] var7 = new BigDecimal[var3.length];

                int var22;
                for(var22 = 0; var22 < this.length; ++var22) {
                    var7[var22] = var5[var22];
                }

                for(var22 = 0; var22 < var2; ++var22) {
                    var7[var22 + this.length] = var6[var22];
                }

                for(var22 = 0; var22 < var3.length; ++var22) {
                    var3.array.add(var7[var22]);
                }

                var5 = null;
                var6 = null;
                var7 = null;
                var3.type = 12;
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 13:
            case 16:
            case 17:
                BigInteger[] var8 = this.getArray_as_BigInteger();
                var4 = new ArrayMaths(var1);
                BigInteger[] var9 = var4.getArray_as_BigInteger();
                BigInteger[] var10 = new BigInteger[var3.length];

                int var23;
                for(var23 = 0; var23 < this.length; ++var23) {
                    var10[var23] = var8[var23];
                }

                for(var23 = 0; var23 < var2; ++var23) {
                    var10[var23 + this.length] = var9[var23];
                }

                for(var23 = 0; var23 < var3.length; ++var23) {
                    var3.array.add(var10[var23]);
                }

                var8 = null;
                var9 = null;
                var10 = null;
                var3.type = 13;
                break;
            case 14:
                Complex[] var11 = this.getArray_as_Complex();
                var4 = new ArrayMaths(var1);
                Complex[] var12 = var4.getArray_as_Complex();
                Complex[] var13 = new Complex[var3.length];

                int var24;
                for(var24 = 0; var24 < this.length; ++var24) {
                    var13[var24] = var11[var24];
                }

                for(var24 = 0; var24 < var2; ++var24) {
                    var13[var24 + this.length] = var12[var24];
                }

                for(var24 = 0; var24 < var3.length; ++var24) {
                    var3.array.add(var13[var24]);
                }

                var3.type = 14;
                break;
            case 15:
                Phasor[] var14 = this.getArray_as_Phasor();
                var4 = new ArrayMaths(var1);
                Phasor[] var15 = var4.getArray_as_Phasor();
                Phasor[] var16 = new Phasor[var3.length];

                int var25;
                for(var25 = 0; var25 < this.length; ++var25) {
                    var16[var25] = var14[var25];
                }

                for(var25 = 0; var25 < var2; ++var25) {
                    var16[var25 + this.length] = var15[var25];
                }

                for(var25 = 0; var25 < var3.length; ++var25) {
                    var3.array.add(var16[var25]);
                }

                var3.type = 15;
                break;
            case 18:
                String[] var17 = this.getArray_as_String();
                var4 = new ArrayMaths(var1);
                String[] var18 = var4.getArray_as_String();
                String[] var19 = new String[var3.length];

                int var20;
                for(var20 = 0; var20 < this.length; ++var20) {
                    var19[var20] = var17[var20];
                }

                for(var20 = 0; var20 < var2; ++var20) {
                    var19[var20 + this.length] = var18[var20];
                }

                for(var20 = 0; var20 < var3.length; ++var20) {
                    var3.array.add(var19[var20]);
                }

                var3.type = 18;
                break;
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        int[] var21 = new int[2];
        findMinMax(var3.getArray_as_Object(), var3.minmax, var21, var3.typeName, var3.type);
        var3.maxIndex = var21[0];
        var3.minIndex = var21[1];
        Conv.restoreMessages();
        return var3;
    }

    public ArrayMaths concatenate(Complex[] var1) {
        int var2 = var1.length;
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var3 = new ArrayMaths();
        var3.array = new ArrayList();
        var3.length = var1.length + this.length;
        ArrayMaths var4 = null;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
                Complex[] var5 = this.getArray_as_Complex();
                var4 = new ArrayMaths(var1);
                Complex[] var6 = var4.getArray_as_Complex();
                Complex[] var7 = new Complex[var3.length];

                int var12;
                for(var12 = 0; var12 < this.length; ++var12) {
                    var7[var12] = var5[var12];
                }

                for(var12 = 0; var12 < var2; ++var12) {
                    var7[var12 + this.length] = var6[var12];
                }

                for(var12 = 0; var12 < var3.length; ++var12) {
                    var3.array.add(var7[var12]);
                }

                var3.type = 14;
                break;
            case 18:
                String[] var8 = this.getArray_as_String();
                var4 = new ArrayMaths(var1);
                String[] var9 = var4.getArray_as_String();
                String[] var10 = new String[var3.length];

                int var11;
                for(var11 = 0; var11 < this.length; ++var11) {
                    var10[var11] = var8[var11];
                }

                for(var11 = 0; var11 < var2; ++var11) {
                    var10[var11 + this.length] = var9[var11];
                }

                for(var11 = 0; var11 < var3.length; ++var11) {
                    var3.array.add(var10[var11]);
                }

                var3.type = 18;
                break;
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var3;
    }

    public ArrayMaths concatenate(Phasor[] var1) {
        int var2 = var1.length;
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var3 = new ArrayMaths();
        var3.array = new ArrayList();
        var3.length = var1.length + this.length;
        ArrayMaths var4 = null;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
                Phasor[] var5 = this.getArray_as_Phasor();
                var4 = new ArrayMaths(var1);
                Phasor[] var6 = var4.getArray_as_Phasor();
                Phasor[] var7 = new Phasor[var3.length];

                int var12;
                for(var12 = 0; var12 < this.length; ++var12) {
                    var7[var12] = var5[var12];
                }

                for(var12 = 0; var12 < var2; ++var12) {
                    var7[var12 + this.length] = var6[var12];
                }

                for(var12 = 0; var12 < var3.length; ++var12) {
                    var3.array.add(var7[var12]);
                }

                var3.type = 15;
                break;
            case 18:
                String[] var8 = this.getArray_as_String();
                var4 = new ArrayMaths(var1);
                String[] var9 = var4.getArray_as_String();
                String[] var10 = new String[var3.length];

                int var11;
                for(var11 = 0; var11 < this.length; ++var11) {
                    var10[var11] = var8[var11];
                }

                for(var11 = 0; var11 < var2; ++var11) {
                    var10[var11 + this.length] = var9[var11];
                }

                for(var11 = 0; var11 < var3.length; ++var11) {
                    var3.array.add(var10[var11]);
                }

                var3.type = 18;
                break;
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        Conv.restoreMessages();
        return var3;
    }

    public ArrayMaths concatenate(String[] var1) {
        int var2 = var1.length;
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var3 = new ArrayMaths();
        var3.array = new ArrayList();
        var3.length = var1.length + this.length;
        ArrayMaths var4 = null;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
                String[] var5 = this.getArray_as_String();
                var4 = new ArrayMaths(var1);
                String[] var6 = var4.getArray_as_String();
                String[] var7 = new String[var3.length];

                int var8;
                for(var8 = 0; var8 < this.length; ++var8) {
                    var7[var8] = var5[var8];
                }

                for(var8 = 0; var8 < var2; ++var8) {
                    var7[var8 + this.length] = var6[var8];
                }

                for(var8 = 0; var8 < var3.length; ++var8) {
                    var3.array.add(var7[var8]);
                }

                var3.type = 18;
                Conv.restoreMessages();
                return var3;
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }
    }

    public ArrayMaths concatenate(char[] var1) {
        int var2 = var1.length;
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        ArrayMaths var3 = new ArrayMaths();
        var3.array = new ArrayList();
        var3.length = var1.length + this.length;
        ArrayMaths var4 = null;
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
                double[] var5 = this.getArray_as_double();
                double[] var6 = new double[var3.length];

                int var28;
                for(var28 = 0; var28 < this.length; ++var28) {
                    var6[var28] = var5[var28];
                }

                for(var28 = 0; var28 < var2; ++var28) {
                    var6[var28 + this.length] = (double)var1[var28];
                }

                for(var28 = 0; var28 < var3.length; ++var28) {
                    var3.array.add(new Double(var6[var28]));
                }

                var3.type = 1;
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                long[] var7 = this.getArray_as_long();
                long[] var8 = new long[var3.length];

                int var29;
                for(var29 = 0; var29 < this.length; ++var29) {
                    var8[var29] = var7[var29];
                }

                for(var29 = 0; var29 < var2; ++var29) {
                    var8[var29 + this.length] = (long)var1[var29];
                }

                for(var29 = 0; var29 < var3.length; ++var29) {
                    var3.array.add(new Long(var8[var29]));
                }

                var3.type = 3;
                break;
            case 12:
                BigDecimal[] var9 = this.getArray_as_BigDecimal();
                var4 = new ArrayMaths(var1);
                BigDecimal[] var10 = var4.getArray_as_BigDecimal();
                BigDecimal[] var11 = new BigDecimal[var3.length];

                int var30;
                for(var30 = 0; var30 < this.length; ++var30) {
                    var11[var30] = var9[var30];
                }

                for(var30 = 0; var30 < var2; ++var30) {
                    var11[var30 + this.length] = var10[var30];
                }

                for(var30 = 0; var30 < var3.length; ++var30) {
                    var3.array.add(var11[var30]);
                }

                var9 = null;
                var10 = null;
                var11 = null;
                var3.type = 12;
                break;
            case 13:
                BigInteger[] var12 = this.getArray_as_BigInteger();
                var4 = new ArrayMaths(var1);
                BigInteger[] var13 = var4.getArray_as_BigInteger();
                BigInteger[] var14 = new BigInteger[var3.length];

                int var31;
                for(var31 = 0; var31 < this.length; ++var31) {
                    var14[var31] = var12[var31];
                }

                for(var31 = 0; var31 < var2; ++var31) {
                    var14[var31 + this.length] = var13[var31];
                }

                for(var31 = 0; var31 < var3.length; ++var31) {
                    var3.array.add(var14[var31]);
                }

                var12 = null;
                var13 = null;
                var14 = null;
                var3.type = 13;
                break;
            case 14:
                Complex[] var15 = this.getArray_as_Complex();
                var4 = new ArrayMaths(var1);
                Complex[] var16 = var4.getArray_as_Complex();
                Complex[] var17 = new Complex[var3.length];

                int var32;
                for(var32 = 0; var32 < this.length; ++var32) {
                    var17[var32] = var15[var32];
                }

                for(var32 = 0; var32 < var2; ++var32) {
                    var17[var32 + this.length] = var16[var32];
                }

                for(var32 = 0; var32 < var3.length; ++var32) {
                    var3.array.add(var17[var32]);
                }

                var3.type = 14;
                break;
            case 15:
                Phasor[] var18 = this.getArray_as_Phasor();
                var4 = new ArrayMaths(var1);
                Phasor[] var19 = var4.getArray_as_Phasor();
                Phasor[] var20 = new Phasor[var3.length];

                int var33;
                for(var33 = 0; var33 < this.length; ++var33) {
                    var20[var33] = var18[var33];
                }

                for(var33 = 0; var33 < var2; ++var33) {
                    var20[var33 + this.length] = var19[var33];
                }

                for(var33 = 0; var33 < var3.length; ++var33) {
                    var3.array.add(var20[var33]);
                }

                var3.type = 15;
                break;
            case 16:
            case 17:
                char[] var21 = this.getArray_as_char();
                char[] var22 = new char[var3.length];

                int var34;
                for(var34 = 0; var34 < this.length; ++var34) {
                    var22[var34] = var21[var34];
                }

                for(var34 = 0; var34 < var2; ++var34) {
                    var22[var34 + this.length] = var1[var34];
                }

                for(var34 = 0; var34 < var3.length; ++var34) {
                    var3.array.add(new Character(var22[var34]));
                }

                var3.type = 1;
                break;
            case 18:
                String[] var23 = this.getArray_as_String();
                var4 = new ArrayMaths(var1);
                String[] var24 = var4.getArray_as_String();
                String[] var25 = new String[var3.length];

                int var26;
                for(var26 = 0; var26 < this.length; ++var26) {
                    var25[var26] = var23[var26];
                }

                for(var26 = 0; var26 < var2; ++var26) {
                    var25[var26 + this.length] = var24[var26];
                }

                for(var26 = 0; var26 < var3.length; ++var26) {
                    var3.array.add(var25[var26]);
                }

                var3.type = 18;
                break;
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        int[] var27 = new int[2];
        findMinMax(var3.getArray_as_Object(), var3.minmax, var27, var3.typeName, var3.type);
        var3.maxIndex = var27[0];
        var3.minIndex = var27[1];
        Conv.restoreMessages();
        return var3;
    }

    public ArrayMaths concatenate(Character[] var1) {
        char[] var2 = new char[var1.length];

        for(int var3 = 0; var3 < var1.length; ++var3) {
            var2[var3] = var1[var3];
        }

        return this.concatenate(var2);
    }

    public ArrayMaths concatenate(ArrayMaths var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        int var2 = var1.type;
        new ArrayMaths();
        ArrayMaths var3;
        switch(var1.type) {
            case 0:
            case 1:
                double[] var4 = var1.getArray_as_double();
                var3 = this.concatenate(var4);
                break;
            case 2:
            case 3:
                float[] var5 = var1.getArray_as_float();
                var3 = this.concatenate(var5);
                break;
            case 4:
            case 5:
                long[] var6 = var1.getArray_as_long();
                var3 = this.concatenate(var6);
                break;
            case 6:
            case 7:
                int[] var7 = var1.getArray_as_int();
                var3 = this.concatenate(var7);
                break;
            case 8:
            case 9:
                short[] var8 = var1.getArray_as_short();
                var3 = this.concatenate(var8);
                break;
            case 10:
            case 11:
                byte[] var9 = var1.getArray_as_byte();
                var3 = this.concatenate(var9);
                break;
            case 12:
                BigDecimal[] var10 = var1.getArray_as_BigDecimal();
                var3 = this.concatenate(var10);
                break;
            case 13:
                BigInteger[] var11 = this.getArray_as_BigInteger();
                var3 = this.concatenate(var11);
                break;
            case 14:
                Complex[] var12 = this.getArray_as_Complex();
                var3 = this.concatenate(var12);
                break;
            case 15:
                Phasor[] var13 = this.getArray_as_Phasor();
                var3 = this.concatenate(var13);
                break;
            case 16:
            case 17:
                char[] var14 = this.getArray_as_char();
                var3 = this.concatenate(var14);
                break;
            case 18:
                String[] var15 = this.getArray_as_String();
                var3 = this.concatenate(var15);
                break;
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        int[] var16 = new int[2];
        findMinMax(var3.getArray_as_Object(), var3.minmax, var16, var3.typeName, var3.type);
        var3.maxIndex = var16[0];
        var3.minIndex = var16[1];
        Conv.restoreMessages();
        return var3;
    }

    public ArrayMaths concatenate(Stat var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        int var2 = var1.type;
        new ArrayMaths();
        ArrayMaths var3;
        switch(var1.type) {
            case 0:
            case 1:
                double[] var4 = var1.getArray_as_double();
                var3 = this.concatenate(var4);
                break;
            case 2:
            case 3:
                float[] var5 = var1.getArray_as_float();
                var3 = this.concatenate(var5);
                break;
            case 4:
            case 5:
                long[] var6 = var1.getArray_as_long();
                var3 = this.concatenate(var6);
                break;
            case 6:
            case 7:
                int[] var7 = var1.getArray_as_int();
                var3 = this.concatenate(var7);
                break;
            case 8:
            case 9:
                short[] var8 = var1.getArray_as_short();
                var3 = this.concatenate(var8);
                break;
            case 10:
            case 11:
                byte[] var9 = var1.getArray_as_byte();
                var3 = this.concatenate(var9);
                break;
            case 12:
                BigDecimal[] var10 = var1.getArray_as_BigDecimal();
                var3 = this.concatenate(var10);
                break;
            case 13:
                BigInteger[] var11 = this.getArray_as_BigInteger();
                var3 = this.concatenate(var11);
                break;
            case 14:
                Complex[] var12 = this.getArray_as_Complex();
                var3 = this.concatenate(var12);
                break;
            case 15:
                Phasor[] var13 = this.getArray_as_Phasor();
                var3 = this.concatenate(var13);
                break;
            case 16:
            case 17:
                char[] var14 = this.getArray_as_char();
                var3 = this.concatenate(var14);
                break;
            case 18:
                String[] var15 = this.getArray_as_String();
                var3 = this.concatenate(var15);
                break;
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

        int[] var16 = new int[2];
        findMinMax(var3.getArray_as_Object(), var3.minmax, var16, var3.typeName, var3.type);
        var3.maxIndex = var16[0];
        var3.minIndex = var16[1];
        Conv.restoreMessages();
        return var3;
    }

    public int indexOf(double var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        int var3 = -1;
        if (this.type != 0 && this.type != 1) {
            throw new IllegalArgumentException("Only comparisons between the same data types are supported - you are attempting to compare double or Double with " + this.typeName[this.type]);
        } else {
            double[] var4 = this.getArray_as_double();
            boolean var5 = true;
            int var6 = 0;

            while(var5) {
                if (var4[var6] == var1) {
                    var3 = var6;
                    var5 = false;
                } else {
                    ++var6;
                    if (var6 >= var4.length) {
                        var5 = false;
                    }
                }
            }

            Conv.restoreMessages();
            return var3;
        }
    }

    public int indexOf(Double var1) {
        double var2 = var1;
        return this.indexOf(var2);
    }

    public int indexOf(float var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        int var2 = -1;
        if (this.type != 2 && this.type != 3) {
            throw new IllegalArgumentException("Only comparisons between the same data types are supported - you are attempting to compare float or Float with " + this.typeName[this.type]);
        } else {
            float[] var3 = this.getArray_as_float();
            boolean var4 = true;
            int var5 = 0;

            while(var4) {
                if (var3[var5] == var1) {
                    var2 = var5;
                    var4 = false;
                } else {
                    ++var5;
                    if (var5 >= var3.length) {
                        var4 = false;
                    }
                }
            }

            Conv.restoreMessages();
            return var2;
        }
    }

    public int indexOf(Float var1) {
        float var2 = var1;
        return this.indexOf(var2);
    }

    public int indexOf(long var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        int var3 = -1;
        if (this.type != 4 && this.type != 5) {
            throw new IllegalArgumentException("Only comparisons between the same data types are supported - you are attempting to compare long or Long with " + this.typeName[this.type]);
        } else {
            long[] var4 = this.getArray_as_long();
            boolean var5 = true;
            int var6 = 0;

            while(var5) {
                if (var4[var6] == var1) {
                    var3 = var6;
                    var5 = false;
                } else {
                    ++var6;
                    if (var6 >= var4.length) {
                        var5 = false;
                    }
                }
            }

            Conv.restoreMessages();
            return var3;
        }
    }

    public int indexOf(Long var1) {
        long var2 = var1;
        return this.indexOf(var2);
    }

    public int indexOf(int var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        int var2 = -1;
        if (this.type != 6 && this.type != 7) {
            throw new IllegalArgumentException("Only comparisons between the same data types are supported - you are attempting to compare int or Integer with " + this.typeName[this.type]);
        } else {
            int[] var3 = this.getArray_as_int();
            boolean var4 = true;
            int var5 = 0;

            while(var4) {
                if (var3[var5] == var1) {
                    var2 = var5;
                    var4 = false;
                } else {
                    ++var5;
                    if (var5 >= var3.length) {
                        var4 = false;
                    }
                }
            }

            Conv.restoreMessages();
            return var2;
        }
    }

    public int indexOf(Integer var1) {
        int var2 = var1;
        return this.indexOf(var2);
    }

    public int indexOf(short var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        int var2 = -1;
        if (this.type != 8 && this.type != 9) {
            throw new IllegalArgumentException("Only comparisons between the same data types are supported - you are attempting to compare short or Short with " + this.typeName[this.type]);
        } else {
            short[] var3 = this.getArray_as_short();
            boolean var4 = true;
            int var5 = 0;

            while(var4) {
                if (var3[var5] == var1) {
                    var2 = var5;
                    var4 = false;
                } else {
                    ++var5;
                    if (var5 >= var3.length) {
                        var4 = false;
                    }
                }
            }

            Conv.restoreMessages();
            return var2;
        }
    }

    public int indexOf(Short var1) {
        short var2 = var1;
        return this.indexOf(var2);
    }

    public int indexOf(byte var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        int var2 = -1;
        if (this.type != 10 && this.type != 11) {
            throw new IllegalArgumentException("Only comparisons between the same data types are supported - you are attempting to compare byte or Byte with " + this.typeName[this.type]);
        } else {
            byte[] var3 = this.getArray_as_byte();
            boolean var4 = true;
            int var5 = 0;

            while(var4) {
                if (var3[var5] == var1) {
                    var2 = var5;
                    var4 = false;
                } else {
                    ++var5;
                    if (var5 >= var3.length) {
                        var4 = false;
                    }
                }
            }

            Conv.restoreMessages();
            return var2;
        }
    }

    public int indexOf(Byte var1) {
        byte var2 = var1;
        return this.indexOf(var2);
    }

    public int indexOf(char var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        int var2 = -1;
        if (this.type != 16 && this.type != 17) {
            throw new IllegalArgumentException("Only comparisons between the same data types are supported - you are attempting to compare char or Character with " + this.typeName[this.type]);
        } else {
            char[] var3 = this.getArray_as_char();
            boolean var4 = true;
            int var5 = 0;

            while(var4) {
                if (var3[var5] == var1) {
                    var2 = var5;
                    var4 = false;
                } else {
                    ++var5;
                    if (var5 >= var3.length) {
                        var4 = false;
                    }
                }
            }

            Conv.restoreMessages();
            return var2;
        }
    }

    public int indexOf(Character var1) {
        char var2 = var1;
        return this.indexOf(var2);
    }

    public int indexOf(String var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        int var2 = -1;
        if (this.type == 18) {
            String[] var3 = this.getArray_as_String();
            boolean var4 = true;
            int var5 = 0;

            while(var4) {
                if (var3[var5].equals(var1)) {
                    var2 = var5;
                    var4 = false;
                } else {
                    ++var5;
                    if (var5 >= var3.length) {
                        var4 = false;
                    }
                }
            }

            Conv.restoreMessages();
            return var2;
        } else {
            throw new IllegalArgumentException("Only comparisons between the same data types are supported - you are attempting to compare String with " + this.typeName[this.type]);
        }
    }

    public int indexOf(Complex var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        int var2 = -1;
        if (this.type == 14) {
            Complex[] var3 = this.getArray_as_Complex();
            boolean var4 = true;
            int var5 = 0;

            while(var4) {
                if (var3[var5].equals(var1)) {
                    var2 = var5;
                    var4 = false;
                } else {
                    ++var5;
                    if (var5 >= var3.length) {
                        var4 = false;
                    }
                }
            }

            Conv.restoreMessages();
            return var2;
        } else {
            throw new IllegalArgumentException("Only comparisons between the same data types are supported - you are attempting to compare Complex with " + this.typeName[this.type]);
        }
    }

    public int indexOf(Phasor var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        int var2 = -1;
        if (this.type == 15) {
            Phasor[] var3 = this.getArray_as_Phasor();
            boolean var4 = true;
            int var5 = 0;

            while(var4) {
                if (var3[var5].equals(var1)) {
                    var2 = var5;
                    var4 = false;
                } else {
                    ++var5;
                    if (var5 >= var3.length) {
                        var4 = false;
                    }
                }
            }

            Conv.restoreMessages();
            return var2;
        } else {
            throw new IllegalArgumentException("Only comparisons between the same data types are supported - you are attempting to compare Phasor with " + this.typeName[this.type]);
        }
    }

    public int indexOf(BigDecimal var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        int var2 = -1;
        if (this.type == 12) {
            BigDecimal[] var3 = this.getArray_as_BigDecimal();
            boolean var4 = true;
            int var5 = 0;

            while(var4) {
                if (var3[var5].compareTo(var1) == 0) {
                    var2 = var5;
                    var4 = false;
                } else {
                    ++var5;
                    if (var5 >= var3.length) {
                        var4 = false;
                    }
                }
            }

            Conv.restoreMessages();
            return var2;
        } else {
            throw new IllegalArgumentException("Only comparisons between the same data types are supported - you are attempting to compare BigDecimal with " + this.typeName[this.type]);
        }
    }

    public int indexOf(BigInteger var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        int var2 = -1;
        if (this.type == 13) {
            BigInteger[] var3 = this.getArray_as_BigInteger();
            boolean var4 = true;
            int var5 = 0;

            while(var4) {
                if (var3[var5].compareTo(var1) == 0) {
                    var2 = var5;
                    var4 = false;
                } else {
                    ++var5;
                    if (var5 >= var3.length) {
                        var4 = false;
                    }
                }
            }

            Conv.restoreMessages();
            return var2;
        } else {
            throw new IllegalArgumentException("Only comparisons between the same data types are supported - you are attempting to compare BigInteger with " + this.typeName[this.type]);
        }
    }

    public int[] indicesOf(double var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        int[] var3 = null;
        int var4 = 0;
        if (this.type != 0 && this.type != 1) {
            throw new IllegalArgumentException("Only comparisons between the same data types are supported - you are attempting to compare double or Double with " + this.typeName[this.type]);
        } else {
            double[] var5 = this.getArray_as_double();
            ArrayList var6 = new ArrayList();

            int var7;
            for(var7 = 0; var7 < this.length; ++var7) {
                if (var5[var7] == var1) {
                    ++var4;
                    var6.add(new Integer(var7));
                }
            }

            if (var4 != 0) {
                var3 = new int[var4];

                for(var7 = 0; var7 < var4; ++var7) {
                    var3[var7] = (Integer)var6.get(var7);
                }
            }

            Conv.restoreMessages();
            return var3;
        }
    }

    public int[] indicesOf(Double var1) {
        double var2 = var1;
        return this.indicesOf(var2);
    }

    public int[] indicesOf(float var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        int[] var2 = null;
        int var3 = 0;
        if (this.type != 2 && this.type != 3) {
            throw new IllegalArgumentException("Only comparisons between the same data types are supported - you are attempting to compare float or Float with " + this.typeName[this.type]);
        } else {
            float[] var4 = this.getArray_as_float();
            ArrayList var5 = new ArrayList();

            int var6;
            for(var6 = 0; var6 < this.length; ++var6) {
                if (var4[var6] == var1) {
                    ++var3;
                    var5.add(new Integer(var6));
                }
            }

            if (var3 != 0) {
                var2 = new int[var3];

                for(var6 = 0; var6 < var3; ++var6) {
                    var2[var6] = (Integer)var5.get(var6);
                }
            }

            Conv.restoreMessages();
            return var2;
        }
    }

    public int[] indicesOf(Float var1) {
        float var2 = var1;
        return this.indicesOf(var2);
    }

    public int[] indicesOf(long var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        int[] var3 = null;
        int var4 = 0;
        if (this.type != 4 && this.type != 5) {
            throw new IllegalArgumentException("Only comparisons between the same data types are supported - you are attempting to compare long or Long with " + this.typeName[this.type]);
        } else {
            long[] var5 = this.getArray_as_long();
            ArrayList var6 = new ArrayList();

            int var7;
            for(var7 = 0; var7 < this.length; ++var7) {
                if (var5[var7] == var1) {
                    ++var4;
                    var6.add(new Integer(var7));
                }
            }

            if (var4 != 0) {
                var3 = new int[var4];

                for(var7 = 0; var7 < var4; ++var7) {
                    var3[var7] = (Integer)var6.get(var7);
                }
            }

            Conv.restoreMessages();
            return var3;
        }
    }

    public int[] indicesOf(Long var1) {
        long var2 = var1;
        return this.indicesOf(var2);
    }

    public int[] indicesOf(int var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        int[] var2 = null;
        int var3 = 0;
        if (this.type != 6 && this.type != 7) {
            throw new IllegalArgumentException("Only comparisons between the same data types are supported - you are attempting to compare int or Integer with " + this.typeName[this.type]);
        } else {
            int[] var4 = this.getArray_as_int();
            ArrayList var5 = new ArrayList();

            int var6;
            for(var6 = 0; var6 < this.length; ++var6) {
                if (var4[var6] == var1) {
                    ++var3;
                    var5.add(new Integer(var6));
                }
            }

            if (var3 != 0) {
                var2 = new int[var3];

                for(var6 = 0; var6 < var3; ++var6) {
                    var2[var6] = (Integer)var5.get(var6);
                }
            }

            Conv.restoreMessages();
            return var2;
        }
    }

    public int[] indicesOf(Integer var1) {
        int var2 = var1;
        return this.indicesOf(var2);
    }

    public int[] indicesOf(short var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        int[] var2 = null;
        int var3 = 0;
        if (this.type != 8 && this.type != 9) {
            throw new IllegalArgumentException("Only comparisons between the same data types are supported - you are attempting to compare short or Short with " + this.typeName[this.type]);
        } else {
            short[] var4 = this.getArray_as_short();
            ArrayList var5 = new ArrayList();

            int var6;
            for(var6 = 0; var6 < this.length; ++var6) {
                if (var4[var6] == var1) {
                    ++var3;
                    var5.add(new Integer(var6));
                }
            }

            if (var3 != 0) {
                var2 = new int[var3];

                for(var6 = 0; var6 < var3; ++var6) {
                    var2[var6] = (Integer)var5.get(var6);
                }
            }

            Conv.restoreMessages();
            return var2;
        }
    }

    public int[] indicesOf(Short var1) {
        short var2 = var1;
        return this.indicesOf(var2);
    }

    public int[] indicesOf(byte var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        int[] var2 = null;
        int var3 = 0;
        if (this.type != 10 && this.type != 11) {
            throw new IllegalArgumentException("Only comparisons between the same data types are supported - you are attempting to compare byte or Byte with " + this.typeName[this.type]);
        } else {
            byte[] var4 = this.getArray_as_byte();
            ArrayList var5 = new ArrayList();

            int var6;
            for(var6 = 0; var6 < this.length; ++var6) {
                if (var4[var6] == var1) {
                    ++var3;
                    var5.add(new Integer(var6));
                }
            }

            if (var3 != 0) {
                var2 = new int[var3];

                for(var6 = 0; var6 < var3; ++var6) {
                    var2[var6] = (Integer)var5.get(var6);
                }
            }

            Conv.restoreMessages();
            return var2;
        }
    }

    public int[] indicesOf(Byte var1) {
        byte var2 = var1;
        return this.indicesOf(var2);
    }

    public int[] indicesOf(char var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        int[] var2 = null;
        int var3 = 0;
        if (this.type != 16 && this.type != 17) {
            throw new IllegalArgumentException("Only comparisons between the same data types are supported - you are attempting to compare char or Character with " + this.typeName[this.type]);
        } else {
            char[] var4 = this.getArray_as_char();
            ArrayList var5 = new ArrayList();

            int var6;
            for(var6 = 0; var6 < this.length; ++var6) {
                if (var4[var6] == var1) {
                    ++var3;
                    var5.add(new Integer(var6));
                }
            }

            if (var3 != 0) {
                var2 = new int[var3];

                for(var6 = 0; var6 < var3; ++var6) {
                    var2[var6] = (Integer)var5.get(var6);
                }
            }

            Conv.restoreMessages();
            return var2;
        }
    }

    public int[] indicesOf(Character var1) {
        char var2 = var1;
        return this.indicesOf(var2);
    }

    public int[] indicesOf(String var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        int[] var2 = null;
        int var3 = 0;
        if (this.type != 18) {
            throw new IllegalArgumentException("Only comparisons between the same data types are supported - you are attempting to compare String with " + this.typeName[this.type]);
        } else {
            String[] var4 = this.getArray_as_String();
            ArrayList var5 = new ArrayList();

            int var6;
            for(var6 = 0; var6 < this.length; ++var6) {
                if (var4[var6].equals(var1)) {
                    ++var3;
                    var5.add(new Integer(var6));
                }
            }

            if (var3 != 0) {
                var2 = new int[var3];

                for(var6 = 0; var6 < var3; ++var6) {
                    var2[var6] = (Integer)var5.get(var6);
                }
            }

            Conv.restoreMessages();
            return var2;
        }
    }

    public int[] indicesOf(Complex var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        int[] var2 = null;
        int var3 = 0;
        if (this.type != 14) {
            throw new IllegalArgumentException("Only comparisons between the same data types are supported - you are attempting to compare Complex with " + this.typeName[this.type]);
        } else {
            Complex[] var4 = this.getArray_as_Complex();
            ArrayList var5 = new ArrayList();

            int var6;
            for(var6 = 0; var6 < this.length; ++var6) {
                if (var4[var6].equals(var1)) {
                    ++var3;
                    var5.add(new Integer(var6));
                }
            }

            if (var3 != 0) {
                var2 = new int[var3];

                for(var6 = 0; var6 < var3; ++var6) {
                    var2[var6] = (Integer)var5.get(var6);
                }
            }

            Conv.restoreMessages();
            return var2;
        }
    }

    public int[] indicesOf(Phasor var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        int[] var2 = null;
        int var3 = 0;
        if (this.type != 15) {
            throw new IllegalArgumentException("Only comparisons between the same data types are supported - you are attempting to compare Phasor with " + this.typeName[this.type]);
        } else {
            Phasor[] var4 = this.getArray_as_Phasor();
            ArrayList var5 = new ArrayList();

            int var6;
            for(var6 = 0; var6 < this.length; ++var6) {
                if (var4[var6].equals(var1)) {
                    ++var3;
                    var5.add(new Integer(var6));
                }
            }

            if (var3 != 0) {
                var2 = new int[var3];

                for(var6 = 0; var6 < var3; ++var6) {
                    var2[var6] = (Integer)var5.get(var6);
                }
            }

            Conv.restoreMessages();
            return var2;
        }
    }

    public int[] indicesOf(BigDecimal var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        int[] var2 = null;
        int var3 = 0;
        if (this.type != 12) {
            throw new IllegalArgumentException("Only comparisons between the same data types are supported - you are attempting to compare BigDecimal with " + this.typeName[this.type]);
        } else {
            BigDecimal[] var4 = this.getArray_as_BigDecimal();
            ArrayList var5 = new ArrayList();

            int var6;
            for(var6 = 0; var6 < this.length; ++var6) {
                if (var4[var6].compareTo(var1) == 0) {
                    ++var3;
                    var5.add(new Integer(var6));
                }
            }

            if (var3 != 0) {
                var2 = new int[var3];

                for(var6 = 0; var6 < var3; ++var6) {
                    var2[var6] = (Integer)var5.get(var6);
                }
            }

            Conv.restoreMessages();
            return var2;
        }
    }

    public int[] indicesOf(BigInteger var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        int[] var2 = null;
        int var3 = 0;
        if (this.type != 13) {
            throw new IllegalArgumentException("Only comparisons between the same data types are supported - you are attempting to compare BigInteger with " + this.typeName[this.type]);
        } else {
            BigInteger[] var4 = this.getArray_as_BigInteger();
            ArrayList var5 = new ArrayList();

            int var6;
            for(var6 = 0; var6 < this.length; ++var6) {
                if (var4[var6].compareTo(var1) == 0) {
                    ++var3;
                    var5.add(new Integer(var6));
                }
            }

            if (var3 != 0) {
                var2 = new int[var3];

                for(var6 = 0; var6 < var3; ++var6) {
                    var2[var6] = (Integer)var5.get(var6);
                }
            }

            Conv.restoreMessages();
            return var2;
        }
    }

    public int nearestIndex(double var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        int var3 = 0;
        if (this.type != 0 && this.type != 1) {
            throw new IllegalArgumentException("Only comparisons between the same data types are supported - you are attempting to compare double or Double with " + this.typeName[this.type]);
        } else {
            double[] var4 = this.getArray_as_double();
            double var5 = Math.abs(var4[0] - var1);
            double var7 = var4[0];

            for(int var9 = 1; var9 < var4.length; ++var9) {
                if (Math.abs(var4[var9] - var1) < var5) {
                    var5 = Math.abs(var4[var9] - var1);
                    var3 = var9;
                }
            }

            Conv.restoreMessages();
            return var3;
        }
    }

    public int nearestIndex(Double var1) {
        double var2 = var1;
        return this.nearestIndex(var2);
    }

    public int nearestIndex(float var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        int var2 = 0;
        if (this.type != 2 && this.type != 3) {
            throw new IllegalArgumentException("Only comparisons between the same data types are supported - you are attempting to compare float or Float with " + this.typeName[this.type]);
        } else {
            float[] var3 = this.getArray_as_float();
            float var4 = Math.abs(var3[0] - var1);
            float var5 = var3[0];

            for(int var6 = 1; var6 < var3.length; ++var6) {
                if (Math.abs(var3[var6] - var1) < var4) {
                    var4 = Math.abs(var3[var6] - var1);
                    var2 = var6;
                }
            }

            Conv.restoreMessages();
            return var2;
        }
    }

    public int nearestIndex(Float var1) {
        float var2 = var1;
        return this.nearestIndex(var2);
    }

    public int nearestIndex(long var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        int var3 = 0;
        if (this.type != 4 && this.type != 5) {
            throw new IllegalArgumentException("Only comparisons between the same data types are supported - you are attempting to compare long or Long with " + this.typeName[this.type]);
        } else {
            long[] var4 = this.getArray_as_long();
            long var5 = Math.abs(var4[0] - var1);
            long var7 = var4[0];

            for(int var9 = 1; var9 < var4.length; ++var9) {
                if (Math.abs(var4[var9] - var1) < var5) {
                    var5 = Math.abs(var4[var9] - var1);
                    var3 = var9;
                }
            }

            Conv.restoreMessages();
            return var3;
        }
    }

    public int nearestIndex(Long var1) {
        long var2 = var1;
        return this.nearestIndex(var2);
    }

    public int nearestIndex(int var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        int var2 = 0;
        if (this.type != 6 && this.type != 7) {
            throw new IllegalArgumentException("Only comparisons between the same data types are supported - you are attempting to compare int or Integer with " + this.typeName[this.type]);
        } else {
            int[] var3 = this.getArray_as_int();
            int var4 = Math.abs(var3[0] - var1);
            int var5 = var3[0];

            for(int var6 = 1; var6 < var3.length; ++var6) {
                if (Math.abs(var3[var6] - var1) < var4) {
                    var4 = Math.abs(var3[var6] - var1);
                    var2 = var6;
                }
            }

            Conv.restoreMessages();
            return var2;
        }
    }

    public int nearestIndex(Integer var1) {
        int var2 = var1;
        return this.nearestIndex(var2);
    }

    public int nearestIndex(short var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        int var2 = 0;
        if (this.type != 8 && this.type != 9) {
            throw new IllegalArgumentException("Only comparisons between the same data types are supported - you are attempting to compare short or Short with " + this.typeName[this.type]);
        } else {
            short[] var3 = this.getArray_as_short();
            short var4 = (short)Math.abs(var3[0] - var1);
            short var5 = var3[0];

            for(int var6 = 1; var6 < var3.length; ++var6) {
                if (Math.abs(var3[var6] - var1) < var4) {
                    var4 = (short)Math.abs(var3[var6] - var1);
                    var2 = var6;
                }
            }

            Conv.restoreMessages();
            return var2;
        }
    }

    public int nearestIndex(Short var1) {
        short var2 = var1;
        return this.nearestIndex(var2);
    }

    public int nearestIndex(byte var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        int var2 = 0;
        if (this.type != 10 && this.type != 11) {
            throw new IllegalArgumentException("Only comparisons between the same data types are supported - you are attempting to compare byte or Byte with " + this.typeName[this.type]);
        } else {
            byte[] var3 = this.getArray_as_byte();
            byte var4 = (byte)Math.abs(var3[0] - var1);
            byte var5 = var3[0];

            for(int var6 = 1; var6 < var3.length; ++var6) {
                if (Math.abs(var3[var6] - var1) < var4) {
                    var4 = (byte)Math.abs(var3[var6] - var1);
                    var2 = var6;
                }
            }

            Conv.restoreMessages();
            return var2;
        }
    }

    public int nearestIndex(Byte var1) {
        byte var2 = var1;
        return this.nearestIndex(var2);
    }

    public int nearestIndex(char var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        int var2 = 0;
        if (this.type != 16 && this.type != 17) {
            throw new IllegalArgumentException("Only comparisons between the same data types are supported - you are attempting to compare char or Character with " + this.typeName[this.type]);
        } else {
            int[] var3 = this.getArray_as_int();
            int var4 = Math.abs(var3[0] - var1);
            int var5 = var3[0];

            for(int var6 = 1; var6 < var3.length; ++var6) {
                if (Math.abs(var3[var6] - var1) < var4) {
                    var4 = Math.abs(var3[var6] - var1);
                    var2 = var6;
                }
            }

            Conv.restoreMessages();
            return var2;
        }
    }

    public int nearestIndex(Character var1) {
        char var2 = var1;
        return this.nearestIndex(var2);
    }

    public int nearestIndex(BigDecimal var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        int var2 = 0;
        if (this.type == 12) {
            BigDecimal[] var3 = this.getArray_as_BigDecimal();
            BigDecimal var4 = var3[0].subtract(var1).abs();
            BigDecimal var5 = var3[0];

            for(int var6 = 1; var6 < var3.length; ++var6) {
                if (var3[var6].subtract(var1).abs().compareTo(var4) == -1) {
                    var4 = var3[var6].subtract(var1).abs();
                    var2 = var6;
                }
            }

            var3 = null;
            var4 = null;
            var5 = null;
            Conv.restoreMessages();
            return var2;
        } else {
            throw new IllegalArgumentException("Only comparisons between the same data types are supported - you are attempting to compare BigDecimal with " + this.typeName[this.type]);
        }
    }

    public int nearestIndex(BigInteger var1) {
        if (this.suppressMessages) {
            Conv.suppressMessages();
        }

        int var2 = 0;
        if (this.type == 12) {
            BigInteger[] var3 = this.getArray_as_BigInteger();
            BigInteger var4 = var3[0].subtract(var1).abs();
            BigInteger var5 = var3[0];

            for(int var6 = 1; var6 < var3.length; ++var6) {
                if (var3[var6].subtract(var1).abs().compareTo(var4) == -1) {
                    var4 = var3[var6].subtract(var1).abs();
                    var2 = var6;
                }
            }

            var3 = null;
            var4 = null;
            var5 = null;
            Conv.restoreMessages();
            return var2;
        } else {
            throw new IllegalArgumentException("Only comparisons between the same data types are supported - you are attempting to compare BigInteger with " + this.typeName[this.type]);
        }
    }

    public double nearestValue(double var1) {
        int var3 = this.nearestIndex(var1);
        double var4 = (Double)((Double)this.array.get(var3));
        return var4;
    }

    public Double nearestValue(Double var1) {
        int var2 = this.nearestIndex(var1);
        Double var3 = (Double)((Double)this.array.get(var2));
        return var3;
    }

    public float nearestValue(float var1) {
        int var2 = this.nearestIndex(var1);
        float var3 = (Float)((Float)this.array.get(var2));
        return var3;
    }

    public Float nearestValue(Float var1) {
        int var2 = this.nearestIndex(var1);
        Float var3 = (Float)((Float)this.array.get(var2));
        return var3;
    }

    public long nearestValue(long var1) {
        int var3 = this.nearestIndex(var1);
        long var4 = (Long)((Long)this.array.get(var3));
        return var4;
    }

    public Long nearestValue(Long var1) {
        int var2 = this.nearestIndex(var1);
        Long var3 = (Long)((Long)this.array.get(var2));
        return var3;
    }

    public int nearestValue(int var1) {
        int var2 = this.nearestIndex(var1);
        int var3 = (Integer)((Integer)this.array.get(var2));
        return var3;
    }

    public Integer nearestValue(Integer var1) {
        int var2 = this.nearestIndex(var1);
        Integer var3 = (Integer)((Integer)this.array.get(var2));
        return var3;
    }

    public short nearestValue(short var1) {
        int var2 = this.nearestIndex(var1);
        short var3 = (Short)((Short)this.array.get(var2));
        return var3;
    }

    public Short nearestValue(Short var1) {
        int var2 = this.nearestIndex(var1);
        Short var3 = (Short)((Short)this.array.get(var2));
        return var3;
    }

    public byte nearestValue(byte var1) {
        int var2 = this.nearestIndex(var1);
        byte var3 = (Byte)((Byte)this.array.get(var2));
        return var3;
    }

    public Byte nearestValue(Byte var1) {
        int var2 = this.nearestIndex(var1);
        Byte var3 = (Byte)((Byte)this.array.get(var2));
        return var3;
    }

    public char nearestValue(char var1) {
        int var2 = this.nearestIndex(var1);
        char var3 = (Character)((Character)this.array.get(var2));
        return var3;
    }

    public Character nearestValue(Character var1) {
        int var2 = this.nearestIndex(var1);
        Character var3 = (Character)((Character)this.array.get(var2));
        return var3;
    }

    public BigDecimal nearestValue(BigDecimal var1) {
        int var2 = this.nearestIndex(var1);
        BigDecimal var3 = (BigDecimal)this.array.get(var2);
        return var3;
    }

    public BigInteger nearestValue(BigInteger var1) {
        int var2 = this.nearestIndex(var1);
        BigInteger var3 = (BigInteger)this.array.get(var2);
        return var3;
    }

    public double maximumDifference() {
        return this.getMaximumDifference_as_double();
    }

    public double maximumDifference_as_double() {
        return this.getMaximumDifference_as_double();
    }

    public double getMaximumDifference() {
        return this.getMaximumDifference_as_double();
    }

    public double getMaximumDifference_as_double() {
        double var1 = 0.0D;
        if (this.type != 0 && this.type != 1) {
            throw new IllegalArgumentException("Maximum difference may only be returned as the same type as the type of the internal array - you are trying to return as double or Double the difference for a " + this.typeName[this.type] + "[] array");
        } else {
            double var3 = this.getMaximum_as_double();
            double var5 = this.getMinimum_as_double();
            var1 = var3 - var5;
            return var1;
        }
    }

    public Double maximumDifference_as_Double() {
        return this.getMaximumDifference_as_Double();
    }

    public Double getMaximumDifference_as_Double() {
        return new Double(this.getMaximumDifference_as_double());
    }

    public float maximumDifference_as_float() {
        return this.getMaximumDifference_as_float();
    }

    public float getMaximumDifference_as_float() {
        float var1 = 0.0F;
        if (this.type != 2 && this.type != 3) {
            throw new IllegalArgumentException("Maximum difference may only be returned as the same type as the type of the internal array - you are trying to return as float or Float the difference for a " + this.typeName[this.type] + "[] array");
        } else {
            float var2 = this.getMaximum_as_float();
            float var3 = this.getMinimum_as_float();
            var1 = var2 - var3;
            return var1;
        }
    }

    public Float maximumDifference_as_Float() {
        return this.getMaximumDifference_as_Float();
    }

    public Float getMaximumDifference_as_Float() {
        return new Float(this.getMaximumDifference_as_float());
    }

    public long maximumDifference_as_long() {
        return this.getMaximumDifference_as_long();
    }

    public long getMaximumDifference_as_long() {
        long var1 = 0L;
        if (this.type != 4 && this.type != 5) {
            throw new IllegalArgumentException("Maximum difference may only be returned as the same type as the type of the internal array - you are trying to return as long or Long the difference for a " + this.typeName[this.type] + "[] array");
        } else {
            long var3 = this.getMaximum_as_long();
            long var5 = this.getMinimum_as_long();
            var1 = var3 - var5;
            return var1;
        }
    }

    public Long maximumDifference_as_Long() {
        return this.getMaximumDifference_as_Long();
    }

    public Long getMaximumDifference_as_Long() {
        return new Long(this.getMaximumDifference_as_long());
    }

    public int maximumDifference_as_int() {
        return this.getMaximumDifference_as_int();
    }

    public int getMaximumDifference_as_int() {
        boolean var1 = false;
        if (this.type != 6 && this.type != 7) {
            throw new IllegalArgumentException("Maximum difference may only be returned as the same type as the type of the internal array - you are trying to return as int or Integer the difference for a " + this.typeName[this.type] + "[] array");
        } else {
            int var2 = this.getMaximum_as_int();
            int var3 = this.getMinimum_as_int();
            int var4 = var2 - var3;
            return var4;
        }
    }

    public Integer maximumDifference_as_Integer() {
        return this.getMaximumDifference_as_Integer();
    }

    public Integer getMaximumDifference_as_Integer() {
        return new Integer(this.getMaximumDifference_as_int());
    }

    public short maximumDifference_as_short() {
        return this.getMaximumDifference_as_short();
    }

    public short getMaximumDifference_as_short() {
        boolean var1 = false;
        if (this.type != 8 && this.type != 9) {
            throw new IllegalArgumentException("Maximum difference may only be returned as the same type as the type of the internal array - you are trying to return as short or Short the difference for a " + this.typeName[this.type] + "[] array");
        } else {
            short var2 = this.getMaximum_as_short();
            short var3 = this.getMinimum_as_short();
            short var4 = (short)(var2 - var3);
            return var4;
        }
    }

    public Short maximumDifference_as_Short() {
        return this.getMaximumDifference_as_Short();
    }

    public Short getMaximumDifference_as_Short() {
        return new Short(this.getMaximumDifference_as_short());
    }

    public byte maximumDifference_as_byte() {
        return this.getMaximumDifference_as_byte();
    }

    public byte getMaximumDifference_as_byte() {
        boolean var1 = false;
        if (this.type != 10 && this.type != 11) {
            throw new IllegalArgumentException("Maximum difference may only be returned as the same type as the type of the internal array - you are trying to return as byte or Byte the difference for a " + this.typeName[this.type] + "[] array");
        } else {
            byte var2 = this.getMaximum_as_byte();
            byte var3 = this.getMinimum_as_byte();
            byte var4 = (byte)(var2 - var3);
            return var4;
        }
    }

    public Byte maximumDifference_as_Byte() {
        return this.getMaximumDifference_as_Byte();
    }

    public Byte getMaximumDifference_as_Byte() {
        return new Byte(this.getMaximumDifference_as_byte());
    }

    public BigDecimal maximumDifference_as_BigDecimal() {
        return this.getMaximumDifference_as_BigDecimal();
    }

    public BigDecimal getMaximumDifference_as_BigDecimal() {
        BigDecimal var1 = BigDecimal.ZERO;
        if (this.type == 12) {
            BigDecimal var2 = this.getMaximum_as_BigDecimal();
            BigDecimal var3 = this.getMinimum_as_BigDecimal();
            var1 = var2.subtract(var3);
            var2 = null;
            var3 = null;
            return var1;
        } else {
            throw new IllegalArgumentException("Maximum difference may only be returned as the same type as the type of the internal array - you are trying to return as BigDecimal the difference for a " + this.typeName[this.type] + "[] array");
        }
    }

    public BigInteger maximumDifference_as_BigInteger() {
        return this.getMaximumDifference_as_BigInteger();
    }

    public BigInteger getMaximumDifference_as_BigInteger() {
        BigInteger var1 = BigInteger.ZERO;
        if (this.type == 13) {
            BigInteger var2 = this.getMaximum_as_BigInteger();
            BigInteger var3 = this.getMinimum_as_BigInteger();
            var1 = var2.subtract(var3);
            var2 = null;
            var3 = null;
            return var1;
        } else {
            throw new IllegalArgumentException("Maximum difference may only be returned as the same type as the type of the internal array - you are trying to return as BigInteger the difference for a " + this.typeName[this.type] + "[] array");
        }
    }

    public double minimumDifference() {
        return this.getMinimumDifference_as_double();
    }

    public double minimumDifference_as_double() {
        return this.getMinimumDifference_as_double();
    }

    public double getMinimumDifference() {
        return this.getMinimumDifference_as_double();
    }

    public double getMinimumDifference_as_double() {
        double var1 = 0.0D;
        if (this.type != 0 && this.type != 1) {
            throw new IllegalArgumentException("Minimum difference may only be returned as the same type as the type of the internal array - you are trying to return as double or Double the difference for a " + this.typeName[this.type] + "[] array");
        } else {
            ArrayMaths var3 = this.sort();
            double[] var4 = var3.getArray_as_double();
            var1 = var4[1] - var4[0];
            double var5 = var1;

            for(int var7 = 1; var7 < this.length - 1; ++var7) {
                var1 = var4[var7 + 1] - var4[var7];
                if (var1 < var5) {
                    var5 = var1;
                }
            }

            return var1;
        }
    }

    public Double minimumDifference_as_Double() {
        return this.getMinimumDifference_as_Double();
    }

    public Double getMinimumDifference_as_Double() {
        return new Double(this.getMinimumDifference_as_double());
    }

    public float minimumDifference_as_float() {
        return this.getMinimumDifference_as_float();
    }

    public float getMinimumDifference_as_float() {
        float var1 = 0.0F;
        if (this.type != 2 && this.type != 3) {
            throw new IllegalArgumentException("Minimum difference may only be returned as the same type as the type of the internal array - you are trying to return as float or Float the difference for a " + this.typeName[this.type] + "[] array");
        } else {
            ArrayMaths var2 = this.sort();
            float[] var3 = var2.getArray_as_float();
            var1 = var3[1] - var3[0];
            float var4 = var1;

            for(int var5 = 1; var5 < this.length - 1; ++var5) {
                var1 = var3[var5 + 1] - var3[var5];
                if (var1 < var4) {
                    var4 = var1;
                }
            }

            return var1;
        }
    }

    public Float minimumDifference_as_Float() {
        return this.getMinimumDifference_as_Float();
    }

    public Float getMinimumDifference_as_Float() {
        return new Float(this.getMinimumDifference_as_float());
    }

    public long minimumDifference_as_long() {
        return this.getMinimumDifference_as_long();
    }

    public long getMinimumDifference_as_long() {
        long var1 = 0L;
        if (this.type != 4 && this.type != 5) {
            throw new IllegalArgumentException("Minimum difference may only be returned as the same type as the type of the internal array - you are trying to return as long or Long the difference for a " + this.typeName[this.type] + "[] array");
        } else {
            ArrayMaths var3 = this.sort();
            long[] var4 = var3.getArray_as_long();
            var1 = var4[1] - var4[0];
            long var5 = var1;

            for(int var7 = 1; var7 < this.length - 1; ++var7) {
                var1 = var4[var7 + 1] - var4[var7];
                if (var1 < var5) {
                    var5 = var1;
                }
            }

            return var1;
        }
    }

    public Long minimumDifference_as_Long() {
        return this.getMinimumDifference_as_Long();
    }

    public Long getMinimumDifference_as_Long() {
        return new Long(this.getMinimumDifference_as_long());
    }

    public int minimumDifference_as_int() {
        return this.getMinimumDifference_as_int();
    }

    public int getMinimumDifference_as_int() {
        boolean var1 = false;
        if (this.type != 6 && this.type != 7) {
            throw new IllegalArgumentException("Minimum difference may only be returned as the same type as the type of the internal array - you are trying to return as int or Integer the difference for a " + this.typeName[this.type] + "[] array");
        } else {
            ArrayMaths var2 = this.sort();
            int[] var3 = var2.getArray_as_int();
            int var6 = var3[1] - var3[0];
            int var4 = var6;

            for(int var5 = 1; var5 < this.length - 1; ++var5) {
                var6 = var3[var5 + 1] - var3[var5];
                if (var6 < var4) {
                    var4 = var6;
                }
            }

            return var6;
        }
    }

    public Integer minimumDifference_as_Integer() {
        return this.getMinimumDifference_as_Integer();
    }

    public Integer getMinimumDifference_as_Integer() {
        return new Integer(this.getMinimumDifference_as_int());
    }

    public short minimumDifference_as_short() {
        return this.getMinimumDifference_as_short();
    }

    public short getMinimumDifference_as_short() {
        boolean var1 = false;
        if (this.type != 8 && this.type != 9) {
            throw new IllegalArgumentException("Minimum difference may only be returned as the same type as the type of the internal array - you are trying to return as short or Short the difference for a " + this.typeName[this.type] + "[] array");
        } else {
            ArrayMaths var2 = this.sort();
            short[] var3 = var2.getArray_as_short();
            short var6 = (short)(var3[1] - var3[0]);
            short var4 = var6;

            for(int var5 = 1; var5 < this.length - 1; ++var5) {
                var6 = (short)(var3[var5 + 1] - var3[var5]);
                if (var6 < var4) {
                    var4 = var6;
                }
            }

            return var6;
        }
    }

    public Short minimumDifference_as_Short() {
        return this.getMinimumDifference_as_Short();
    }

    public Short getMinimumDifference_as_Short() {
        return new Short(this.getMinimumDifference_as_short());
    }

    public byte minimumDifference_as_byte() {
        return this.getMinimumDifference_as_byte();
    }

    public byte getMinimumDifference_as_byte() {
        boolean var1 = false;
        if (this.type != 10 && this.type != 11) {
            throw new IllegalArgumentException("Minimum difference may only be returned as the same type as the type of the internal array - you are trying to return as byte or Byte the difference for a " + this.typeName[this.type] + "[] array");
        } else {
            ArrayMaths var2 = this.sort();
            byte[] var3 = var2.getArray_as_byte();
            byte var6 = (byte)(var3[1] - var3[0]);
            byte var4 = var6;

            for(int var5 = 1; var5 < this.length - 1; ++var5) {
                var6 = (byte)(var3[var5 + 1] - var3[var5]);
                if (var6 < var4) {
                    var4 = var6;
                }
            }

            return var6;
        }
    }

    public Byte minimumDifference_as_Byte() {
        return this.getMinimumDifference_as_Byte();
    }

    public Byte getMinimumDifference_as_Byte() {
        return new Byte(this.getMinimumDifference_as_byte());
    }

    public BigDecimal minimumDifference_as_BigDecimal() {
        return this.getMinimumDifference_as_BigDecimal();
    }

    public BigDecimal getMinimumDifference_as_BigDecimal() {
        BigDecimal var1 = BigDecimal.ZERO;
        if (this.type == 12) {
            ArrayMaths var2 = this.sort();
            BigDecimal[] var3 = var2.getArray_as_BigDecimal();
            var1 = var3[1].subtract(var3[0]);
            BigDecimal var4 = var1;

            for(int var5 = 1; var5 < this.length - 1; ++var5) {
                var1 = var3[var5 + 1].subtract(var3[var5]);
                if (var1.compareTo(var4) == -1) {
                    var4 = var1;
                }
            }

            var3 = null;
            var4 = null;
            return var1;
        } else {
            throw new IllegalArgumentException("Minimum difference may only be returned as the same type as the type of the internal array - you are trying to return as BigDecimal the difference for a " + this.typeName[this.type] + "[] array");
        }
    }

    public BigInteger minimumDifference_as_BigInteger() {
        return this.getMinimumDifference_as_BigInteger();
    }

    public BigInteger getMinimumDifference_as_BigInteger() {
        BigInteger var1 = BigInteger.ZERO;
        if (this.type == 12) {
            ArrayMaths var2 = this.sort();
            BigInteger[] var3 = var2.getArray_as_BigInteger();
            var1 = var3[1].subtract(var3[0]);
            BigInteger var4 = var1;

            for(int var5 = 1; var5 < this.length - 1; ++var5) {
                var1 = var3[var5 + 1].subtract(var3[var5]);
                if (var1.compareTo(var4) == -1) {
                    var4 = var1;
                }
            }

            var3 = null;
            var4 = null;
            return var1;
        } else {
            throw new IllegalArgumentException("Minimum difference may only be returned as the same type as the type of the internal array - you are trying to return as BigInteger the difference for a " + this.typeName[this.type] + "[] array");
        }
    }

    public void print() {
        switch(this.type) {
            case 0:
            case 1:
                Double[] var1 = this.getArray_as_Double();
                PrintToScreen.print(var1);
                break;
            case 2:
            case 3:
                Float[] var2 = this.getArray_as_Float();
                PrintToScreen.print(var2);
                break;
            case 4:
            case 5:
                Long[] var3 = this.getArray_as_Long();
                PrintToScreen.print(var3);
                break;
            case 6:
            case 7:
                Integer[] var4 = this.getArray_as_Integer();
                PrintToScreen.print(var4);
                break;
            case 8:
            case 9:
                Short[] var5 = this.getArray_as_Short();
                PrintToScreen.print(var5);
                break;
            case 10:
            case 11:
                Byte[] var6 = this.getArray_as_Byte();
                PrintToScreen.print(var6);
                break;
            case 12:
                BigDecimal[] var7 = this.getArray_as_BigDecimal();
                PrintToScreen.print(var7);
                var7 = null;
                break;
            case 13:
                BigInteger[] var8 = this.getArray_as_BigInteger();
                PrintToScreen.print(var8);
                var8 = null;
                break;
            case 14:
                Complex[] var9 = this.getArray_as_Complex();
                PrintToScreen.print(var9);
                break;
            case 15:
                Phasor[] var10 = this.getArray_as_Phasor();
                PrintToScreen.print(var10);
                break;
            case 16:
            case 17:
                Character[] var11 = this.getArray_as_Character();
                PrintToScreen.print(var11);
                break;
            case 18:
                String[] var12 = this.getArray_as_String();
                PrintToScreen.print(var12);
                break;
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

    }

    public void println() {
        switch(this.type) {
            case 0:
            case 1:
                Double[] var1 = this.getArray_as_Double();
                PrintToScreen.println(var1);
                break;
            case 2:
            case 3:
                Float[] var2 = this.getArray_as_Float();
                PrintToScreen.println(var2);
                break;
            case 4:
            case 5:
                Long[] var3 = this.getArray_as_Long();
                PrintToScreen.println(var3);
                break;
            case 6:
            case 7:
                Integer[] var4 = this.getArray_as_Integer();
                PrintToScreen.println(var4);
                break;
            case 8:
            case 9:
                Short[] var5 = this.getArray_as_Short();
                PrintToScreen.println(var5);
                break;
            case 10:
            case 11:
                Byte[] var6 = this.getArray_as_Byte();
                PrintToScreen.println(var6);
                break;
            case 12:
                BigDecimal[] var7 = this.getArray_as_BigDecimal();
                PrintToScreen.println(var7);
                var7 = null;
                break;
            case 13:
                BigInteger[] var8 = this.getArray_as_BigInteger();
                PrintToScreen.println(var8);
                var8 = null;
                break;
            case 14:
                Complex[] var9 = this.getArray_as_Complex();
                PrintToScreen.println(var9);
                break;
            case 15:
                Phasor[] var10 = this.getArray_as_Phasor();
                PrintToScreen.println(var10);
                break;
            case 16:
            case 17:
                Character[] var11 = this.getArray_as_Character();
                PrintToScreen.println(var11);
                break;
            case 18:
                String[] var12 = this.getArray_as_String();
                PrintToScreen.println(var12);
                break;
            default:
                throw new IllegalArgumentException("Data type not identified by this method");
        }

    }

    public void convertToHighest() {
        switch(this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 16:
            case 17:
            case 18:
                Double[] var1 = this.getArray_as_Double();
                this.array.clear();

                for(int var5 = 0; var5 < this.length; ++var5) {
                    this.array.add(var1[var5]);
                }

                this.type = 1;
                break;
            case 12:
            case 13:
                BigDecimal[] var2 = this.getArray_as_BigDecimal();
                this.array.clear();

                for(int var6 = 0; var6 < this.length; ++var6) {
                    this.array.add(var2[var6]);
                }

                this.type = 12;
                var2 = null;
                break;
            case 14:
            case 15:
                Complex[] var3 = this.getArray_as_Complex();
                this.array.clear();

                for(int var4 = 0; var4 < this.length; ++var4) {
                    this.array.add(var3[var4]);
                }

                this.type = 14;
        }

    }

    public void plot(int var1) {
        if (var1 > 2) {
            throw new IllegalArgumentException("Argument n, " + var1 + ", must be less than 3");
        } else {
            double[] var2 = new double[this.length];

            for(int var3 = 0; var3 < this.length; ++var3) {
                var2[var3] = (double)var3;
            }

            double[] var5 = this.getArray_as_double();
            PlotGraph var4 = new PlotGraph(var2, var5);
            var4.setGraphTitle("ArrayMaths plot method");
            var4.setXaxisLegend("Array element index");
            var4.setYaxisLegend("Array element value");
            var4.setPoint(1);
            switch(var1) {
                case 0:
                    var4.setLine(0);
                    var4.setGraphTitle2("Points only - no line");
                    break;
                case 1:
                    var4.setLine(3);
                    var4.setGraphTitle2("Points joined by straight lines");
                    break;
                case 2:
                    var4.setLine(1);
                    var4.setGraphTitle2("Points joined by cubic spline interpolated line");
                    break;
                default:
                    throw new IllegalArgumentException("Should not be possible to get here!!!");
            }

            var4.plot();
        }
    }

    static {
        integers.put(Integer.class, BigDecimal.valueOf(2147483647L));
        integers.put(Long.class, BigDecimal.valueOf(9223372036854775807L));
        integers.put(Byte.class, BigDecimal.valueOf(127L));
        integers.put(Short.class, BigDecimal.valueOf(32767L));
        integers.put(BigInteger.class, BigDecimal.valueOf(-1L));
    }
}
