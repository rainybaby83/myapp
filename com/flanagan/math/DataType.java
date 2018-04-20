//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.flanagan.math;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;

public class DataType {
    private final String[] typeList = new String[]{"String", "BigDecimal", "BigInteger", "Double", "Float", "Long", "Integer", "Character", "Short", "Byte", "Boolean", "Object", "ArrayList", "LinkedList", "ArrayDeque", "Vector", "Complex", "Phasor", "ErrorProp", "ComplexErrorProp", "Matrix", "ComplexMatrix", "PhasorMatrix", "Polynomial", "ComplexPoly", "ArrayMaths", "VectorMaths", "Point", "BlackBox", "OpenLoop", "ClosedLoop", "Prop", "PropDeriv", "PropInt", "PropIntDeriv", "FirstOrder", "SecondOrder", "Compensator", "LowPassPassive", "HighPassPassive", "DelayLine", "ZeroOrderHold", "Transducer", "AtoD", "DtoA"};
    private final int nTypes;
    private final int nNumerical;
    private Object obj;
    private String objTypeName;
    private int objTypeDim;
    private int objTypeCode;
    private boolean typeFound;
    private Object[] arrayObjects;
    private String[] arrayNames;
    private int[] arrayDims;
    private int[] arrayCodes;
    private int nArrayElements;
    private boolean arrayDone;
    private int arrayFlag;
    private boolean highestDone;
    private String highestName;
    private int highestCode;
    private Object highestArray;

    public DataType() {
        this.nTypes = this.typeList.length;
        this.nNumerical = 9;
        this.obj = null;
        this.objTypeName = null;
        this.objTypeDim = 0;
        this.objTypeCode = -1;
        this.typeFound = false;
        this.arrayObjects = null;
        this.arrayNames = null;
        this.arrayDims = null;
        this.arrayCodes = null;
        this.nArrayElements = 0;
        this.arrayDone = false;
        this.arrayFlag = -1;
        this.highestDone = false;
        this.highestName = null;
        this.highestCode = -1;
        this.highestArray = null;
    }

    public DataType(Object var1) {
        this.nTypes = this.typeList.length;
        this.nNumerical = 9;
        this.obj = null;
        this.objTypeName = null;
        this.objTypeDim = 0;
        this.objTypeCode = -1;
        this.typeFound = false;
        this.arrayObjects = null;
        this.arrayNames = null;
        this.arrayDims = null;
        this.arrayCodes = null;
        this.nArrayElements = 0;
        this.arrayDone = false;
        this.arrayFlag = -1;
        this.highestDone = false;
        this.highestName = null;
        this.highestCode = -1;
        this.highestArray = null;
        this.obj = var1;
        this.inner();
    }

    public final String getTypeName() {
        if (!this.typeFound) {
            this.inner();
        }

        return this.objTypeName;
    }

    public static String getTypeName(Object var0) {
        DataType var1 = new DataType(var0);
        return var1.getTypeName();
    }

    private void inner() {
        this.arrayFlag = 0;
        String var1 = this.obj.getClass().getName();
        boolean var2 = true;
        this.objTypeDim = 0;
        boolean var3 = true;
        int var4 = 0;

        int var7;
        while(var2) {
            var7 = var1.indexOf("[", var4);
            if (var7 == -1) {
                var2 = false;
            } else {
                var4 = var7 + 1;
                ++this.objTypeDim;
            }
        }

        this.objTypeName = null;

        for(var2 = true; var2; var2 = false) {
            var7 = var1.lastIndexOf(".");
            this.objTypeName = var1.substring(var7 + 1);
            if (var1.charAt(var1.length() - 1) == ';') {
                this.objTypeName = this.objTypeName.substring(0, this.objTypeName.length() - 1);
            }
        }

        int var5;
        for(var5 = 0; var5 < this.objTypeDim; ++var5) {
            this.objTypeName = this.objTypeName + "[]";
        }

        var2 = true;
        var5 = 0;

        while(var2) {
            if (var1.indexOf(this.typeList[var5]) != -1) {
                this.objTypeCode = var5;
                var2 = false;
            } else {
                ++var5;
                if (var5 >= this.nTypes) {
                    String var6 = null;
                    if (this.objTypeDim == 0) {
                        var6 = var1;
                    } else {
                        var6 = var1.substring(this.objTypeDim + 1, var1.length() - 1);
                    }

                    System.out.println("Method Object.getTypeCode: the object, " + var6 + ", is not included in the Conv class list of objects, -1 returned");
                    var2 = false;
                }
            }
        }

        this.arrayInner();
        this.typeFound = true;
    }

    private void arrayInner() {
        this.arrayNames = null;
        this.arrayCodes = null;
        this.arrayDims = null;
        if (this.objTypeName.equals("ArrayList")) {
            this.arrayFlag = 1;
            ArrayList var1 = (ArrayList)this.obj;
            this.nArrayElements = var1.size();
            this.arrayObjects = var1.toArray();
            this.fillArray();
        } else if (this.objTypeName.equals("ArrayDeque")) {
            this.arrayFlag = 1;
            ArrayDeque var2 = (ArrayDeque)this.obj;
            this.nArrayElements = var2.size();
            this.arrayObjects = var2.toArray();
            this.fillArray();
        } else if (this.objTypeName.equals("LinkedList")) {
            this.arrayFlag = 1;
            LinkedList var3 = (LinkedList)this.obj;
            this.nArrayElements = var3.size();
            this.arrayObjects = var3.toArray();
            this.fillArray();
        } else if (this.objTypeName.equals("Vector")) {
            this.arrayFlag = 1;
            Vector var4 = (Vector)this.obj;
            this.nArrayElements = var4.size();
            this.arrayObjects = var4.toArray();
            this.fillArray();
        } else if (this.objTypeName.equals("Object[]")) {
            this.arrayFlag = 1;
            this.arrayObjects = (Object[])((Object[])this.obj);
            this.nArrayElements = this.arrayObjects.length;
            this.fillArray();
        }

        this.arrayDone = true;
    }

    private void fillArray() {
        this.arrayNames = new String[this.nArrayElements];
        this.arrayCodes = new int[this.nArrayElements];
        this.arrayDims = new int[this.nArrayElements];
        Object[] var1 = null;

        int var2;
        for(var2 = 0; var2 < this.nArrayElements; ++var2) {
            var1 = getTypeData(this.arrayObjects[var2]);
            this.arrayNames[var2] = (String)var1[0];
            this.arrayDims[var2] = (Integer)var1[1];
            this.arrayCodes[var2] = (Integer)var1[2];
        }

        for(var2 = 0; var2 < this.nArrayElements; ++var2) {
            if (this.arrayCodes[var2] < 10) {
                if (this.arrayDims[var2] > 0) {
                    this.arrayFlag = 2;
                }
            } else {
                this.arrayFlag = 3;
            }

            if (this.arrayFlag == 2 || this.arrayFlag == 3) {
                break;
            }
        }

    }

    public final int getTypeCode() {
        if (!this.typeFound) {
            this.inner();
        }

        return this.objTypeCode;
    }

    public static int getTypeCode(Object var0) {
        DataType var1 = new DataType(var0);
        return var1.getTypeCode();
    }

    public final int getTypeDimension() {
        if (!this.typeFound) {
            this.inner();
        }

        return this.objTypeDim;
    }

    public static int getTypeDimension(Object var0) {
        DataType var1 = new DataType(var0);
        return var1.getTypeDimension();
    }

    public final Object[] getTypeData() {
        Object[] var1 = new Object[3];
        if (!this.typeFound) {
            this.inner();
        }

        var1[0] = this.objTypeName;
        var1[1] = new Integer(this.objTypeDim);
        var1[2] = new Integer(this.objTypeCode);
        return var1;
    }

    public static Object[] getTypeData(Object var0) {
        DataType var1 = new DataType(var0);
        return var1.getTypeData();
    }

    public String[] getArrayNames() {
        String[] var1 = null;
        if (!this.arrayDone) {
            this.arrayInner();
        }

        switch(this.arrayFlag) {
            case 0:
                System.out.println("Method DataType.getArayNames: The entered Object is not a collection  - null returned");
                break;
            case 1:
                var1 = this.arrayNames;
                break;
            case 2:
                System.out.println("Method DataType.getArayNames: The entered Object is an array of collections  - null returned");
        }

        return var1;
    }

    public static String[] getArrayNames(Object var0) {
        DataType var1 = new DataType(var0);
        return var1.getArrayNames();
    }

    public int[] getArrayDimensions() {
        int[] var1 = null;
        if (!this.arrayDone) {
            this.arrayInner();
        }

        switch(this.arrayFlag) {
            case 0:
                System.out.println("Method DataType.getArayDimensions: The entered Object is not a collection  - null returned");
                break;
            case 1:
            case 2:
                var1 = this.arrayDims;
        }

        return var1;
    }

    public static int[] getArrayDimensions(Object var0) {
        DataType var1 = new DataType(var0);
        return var1.getArrayDimensions();
    }

    public int[] getArrayCodes() {
        int[] var1 = null;
        if (!this.arrayDone) {
            this.arrayInner();
        }

        switch(this.arrayFlag) {
            case 0:
                System.out.println("Method DataType.getArrayCodes: The entered Object is not a collection  - null returned");
                break;
            case 1:
            case 2:
                var1 = this.arrayCodes;
        }

        return var1;
    }

    public static int[] getArrayCodes(Object var0) {
        DataType var1 = new DataType(var0);
        return var1.getArrayCodes();
    }

    public final ArrayList<Object> getArrayData() {
        if (!this.typeFound) {
            this.inner();
        }

        ArrayList var1 = new ArrayList();
        var1.add(this.arrayNames);
        var1.add(this.arrayDims);
        var1.add(this.arrayCodes);
        return var1;
    }

    public static ArrayList<Object> getArrayData(Object var0) {
        DataType var1 = new DataType(var0);
        return var1.getArrayData();
    }

    public String highestPrecisionName() {
        String var1 = null;
        if (this.arrayFlag == 0) {
            System.out.println("Method DataType.highestPrecisionName: The entered Object does not contain a collection - null returned");
        } else {
            if (!this.highestDone) {
                this.toHighestPrecision();
            }

            var1 = this.highestName;
        }

        return var1;
    }

    public static String highestPrecisionName(Object var0) {
        DataType var1 = new DataType(var0);
        return var1.highestPrecisionName();
    }

    public int highestPrecisionCode() {
        int var1 = -1;
        if (this.arrayFlag == 0) {
            System.out.println("Method DataType.highestPrecisionCode: The entered Object does not contain a collection - null returned");
        } else {
            if (!this.highestDone) {
                this.toHighestPrecision();
            }

            var1 = this.highestCode;
        }

        return var1;
    }

    public static int highestPrecisionCode(Object var0) {
        DataType var1 = new DataType(var0);
        return var1.highestPrecisionCode();
    }

    public Object convert_to_highestPrecision() {
        return this.convertToHighestPrecision();
    }

    public Object convertToHighestPrecision() {
        Object var1 = null;
        switch(this.arrayFlag) {
            case 0:
                System.out.println("Method DataType.convert_to_highestPrecision: The entered Object is not contain a collection - null returned");
                break;
            case 1:
                if (!this.highestDone) {
                    this.toHighestPrecision();
                }

                var1 = this.highestArray;
                break;
            case 2:
                System.out.println("Method DataType.convert_to_highestPrecision: The entered Object collection contains dimensioned elements - null returned");
                break;
            case 3:
                System.out.println("Method DataType.convert_to_highestPrecision The entered Object array contains an element that is not numerically interconvertable - null returned");
        }

        return var1;
    }

    public static Object convert_to_highestPrecision(Object var0) {
        DataType var1 = new DataType(var0);
        return var1.convertToHighestPrecision();
    }

    public static Object convertToHighestPrecision(Object var0) {
        DataType var1 = new DataType(var0);
        return var1.convertToHighestPrecision();
    }

    private void toHighestPrecision() {
        if (!this.typeFound) {
            this.inner();
        }

        int var1 = this.minCode(this.arrayCodes, this.arrayNames);
        this.highestName = this.typeList[var1];
        this.highestCode = var1;
        if (this.arrayFlag == 1) {
            switch(var1) {
                case 1:
                    BigDecimal[] var2 = new BigDecimal[this.nArrayElements];
                    int var12 = 0;

                    for(; var12 < this.nArrayElements; ++var12) {
                        switch(this.arrayCodes[var12]) {
                            case 0:
                                var2[var12] = Conv.convert_String_to_BigDecimal((String)this.arrayObjects[var12]);
                                break;
                            case 1:
                                var2[var12] = (BigDecimal)((BigDecimal)this.arrayObjects[var12]);
                                break;
                            case 2:
                                var2[var12] = Conv.convert_BigInteger_to_BigDecimal((BigInteger)this.arrayObjects[var12]);
                                break;
                            case 3:
                                var2[var12] = Conv.convert_Double_to_BigDecimal((Double)this.arrayObjects[var12]);
                                break;
                            case 4:
                                var2[var12] = Conv.convert_Float_to_BigDecimal((Float)this.arrayObjects[var12]);
                                break;
                            case 5:
                                var2[var12] = Conv.convert_Long_to_BigDecimal((Long)this.arrayObjects[var12]);
                                break;
                            case 6:
                                var2[var12] = Conv.convert_Integer_to_BigDecimal((Integer)this.arrayObjects[var12]);
                                break;
                            case 7:
                                var2[var12] = Conv.convert_Character_to_BigDecimal((Character)this.arrayObjects[var12]);
                                break;
                            case 8:
                                var2[var12] = Conv.convert_Short_to_BigDecimal((Short)this.arrayObjects[var12]);
                                break;
                            case 9:
                                var2[var12] = Conv.convert_Byte_to_BigDecimal((Byte)this.arrayObjects[var12]);
                        }
                    }

                    this.highestArray = var2;
                    break;
                case 2:
                    BigInteger[] var3 = new BigInteger[this.nArrayElements];

                    for(int var13 = 0; var13 < this.nArrayElements; ++var13) {
                        switch(this.arrayCodes[var13]) {
                            case 0:
                                var3[var13] = Conv.convert_String_to_BigInteger((String)this.arrayObjects[var13]);
                            case 1:
                            default:
                                break;
                            case 2:
                                var3[var13] = (BigInteger)this.arrayObjects[var13];
                                break;
                            case 3:
                                var3[var13] = Conv.convert_Double_to_BigInteger((Double)this.arrayObjects[var13]);
                                break;
                            case 4:
                                var3[var13] = Conv.convert_Float_to_BigInteger((Float)this.arrayObjects[var13]);
                                break;
                            case 5:
                                var3[var13] = Conv.convert_Long_to_BigInteger((Long)this.arrayObjects[var13]);
                                break;
                            case 6:
                                var3[var13] = Conv.convert_Integer_to_BigInteger((Integer)this.arrayObjects[var13]);
                                break;
                            case 7:
                                var3[var13] = Conv.convert_Character_to_BigInteger((Character)this.arrayObjects[var13]);
                                break;
                            case 8:
                                var3[var13] = Conv.convert_Short_to_BigInteger((Short)this.arrayObjects[var13]);
                                break;
                            case 9:
                                var3[var13] = Conv.convert_Byte_to_BigInteger((Byte)this.arrayObjects[var13]);
                        }
                    }

                    this.highestArray = var3;
                    break;
                case 3:
                    Double[] var4 = new Double[this.nArrayElements];

                    for(int var14 = 0; var14 < this.nArrayElements; ++var14) {
                        switch(this.arrayCodes[var14]) {
                            case 0:
                                var4[var14] = Conv.convert_String_to_Double((String)this.arrayObjects[var14]);
                            case 1:
                            case 2:
                            default:
                                break;
                            case 3:
                                var4[var14] = (Double)this.arrayObjects[var14];
                                break;
                            case 4:
                                var4[var14] = Conv.convert_Float_to_Double((Float)this.arrayObjects[var14]);
                                break;
                            case 5:
                                var4[var14] = Conv.convert_Long_to_Double((Long)this.arrayObjects[var14]);
                                break;
                            case 6:
                                var4[var14] = Conv.convert_Integer_to_Double((Integer)this.arrayObjects[var14]);
                                break;
                            case 7:
                                var4[var14] = Conv.convert_Character_to_Double((Character)this.arrayObjects[var14]);
                                break;
                            case 8:
                                var4[var14] = Conv.convert_Short_to_Double((Short)this.arrayObjects[var14]);
                                break;
                            case 9:
                                var4[var14] = Conv.convert_Byte_to_Double((Byte)this.arrayObjects[var14]);
                        }
                    }

                    this.highestArray = var4;
                    break;
                case 4:
                    Float[] var5 = new Float[this.nArrayElements];

                    for(int var15 = 0; var15 < this.nArrayElements; ++var15) {
                        switch(this.arrayCodes[var15]) {
                            case 0:
                                var5[var15] = Conv.convert_String_to_Float((String)this.arrayObjects[var15]);
                            case 1:
                            case 2:
                            case 3:
                            default:
                                break;
                            case 4:
                                var5[var15] = (Float)this.arrayObjects[var15];
                                break;
                            case 5:
                                var5[var15] = Conv.convert_Long_to_Float((Long)this.arrayObjects[var15]);
                                break;
                            case 6:
                                var5[var15] = Conv.convert_Integer_to_Float((Integer)this.arrayObjects[var15]);
                                break;
                            case 7:
                                var5[var15] = Conv.convert_Character_to_Float((Character)this.arrayObjects[var15]);
                                break;
                            case 8:
                                var5[var15] = Conv.convert_Short_to_Float((Short)this.arrayObjects[var15]);
                                break;
                            case 9:
                                var5[var15] = Conv.convert_Byte_to_Float((Byte)this.arrayObjects[var15]);
                        }
                    }

                    this.highestArray = var5;
                    break;
                case 5:
                    Long[] var6 = new Long[this.nArrayElements];

                    for(int var16 = 0; var16 < this.nArrayElements; ++var16) {
                        switch(this.arrayCodes[var16]) {
                            case 0:
                                var6[var16] = Conv.convert_String_to_Long((String)this.arrayObjects[var16]);
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                            default:
                                break;
                            case 5:
                                var6[var16] = (Long)this.arrayObjects[var16];
                                break;
                            case 6:
                                var6[var16] = Conv.convert_Integer_to_Long((Integer)this.arrayObjects[var16]);
                                break;
                            case 7:
                                var6[var16] = Conv.convert_Character_to_Long((Character)this.arrayObjects[var16]);
                                break;
                            case 8:
                                var6[var16] = Conv.convert_Short_to_Long((Short)this.arrayObjects[var16]);
                                break;
                            case 9:
                                var6[var16] = Conv.convert_Byte_to_Long((Byte)this.arrayObjects[var16]);
                        }
                    }

                    this.highestArray = var6;
                    break;
                case 6:
                    Integer[] var7 = new Integer[this.nArrayElements];

                    for(int var17 = 0; var17 < this.nArrayElements; ++var17) {
                        switch(this.arrayCodes[var17]) {
                            case 0:
                                var7[var17] = Conv.convert_String_to_Integer((String)this.arrayObjects[var17]);
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                            case 5:
                            default:
                                break;
                            case 6:
                                var7[var17] = (Integer)this.arrayObjects[var17];
                                break;
                            case 7:
                                var7[var17] = Conv.convert_Character_to_Integer((Character)this.arrayObjects[var17]);
                                break;
                            case 8:
                                var7[var17] = Conv.convert_Short_to_Integer((Short)this.arrayObjects[var17]);
                                break;
                            case 9:
                                var7[var17] = Conv.convert_Byte_to_Integer((Byte)this.arrayObjects[var17]);
                        }
                    }

                    this.highestArray = var7;
                    break;
                case 7:
                    Integer[] var8 = new Integer[this.nArrayElements];

                    for(int var18 = 0; var18 < this.nArrayElements; ++var18) {
                        switch(this.arrayCodes[var18]) {
                            case 0:
                                var8[var18] = Conv.convert_String_to_Integer((String)this.arrayObjects[var18]);
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                            case 5:
                            case 6:
                            default:
                                break;
                            case 7:
                                var8[var18] = Conv.convert_Character_to_Integer((Character)this.arrayObjects[var18]);
                                break;
                            case 8:
                                var8[var18] = Conv.convert_Short_to_Integer((Short)this.arrayObjects[var18]);
                                break;
                            case 9:
                                var8[var18] = Conv.convert_Byte_to_Integer((Byte)this.arrayObjects[var18]);
                        }
                    }

                    this.highestArray = var8;
                    break;
                case 8:
                    Short[] var9 = new Short[this.nArrayElements];

                    for(int var19 = 0; var19 < this.nArrayElements; ++var19) {
                        switch(this.arrayCodes[var19]) {
                            case 0:
                                var9[var19] = Conv.convert_String_to_Short((String)this.arrayObjects[var19]);
                                break;
                            case 8:
                                var9[var19] = (Short)this.arrayObjects[var19];
                                break;
                            case 9:
                                var9[var19] = Conv.convert_Byte_to_Short((Byte)this.arrayObjects[var19]);
                        }
                    }

                    this.highestArray = var9;
                    break;
                case 9:
                    Byte[] var10 = new Byte[this.nArrayElements];

                    for(int var11 = 0; var11 < this.nArrayElements; ++var11) {
                        switch(this.arrayCodes[var11]) {
                            case 0:
                                var10[var11] = Conv.convert_String_to_Byte((String)this.arrayObjects[var11]);
                                break;
                            case 9:
                                var10[var11] = (Byte)this.arrayObjects[var11];
                        }
                    }

                    this.highestArray = var10;
            }

            this.highestDone = true;
        }

    }

    private int minCode(int[] var1, Object[] var2) {
        boolean var3 = true;
        boolean var4 = true;
        int var5 = var1.length;
        int var6 = 0;
        boolean var7 = true;
        boolean var8 = true;

        while(var7) {
            int var10000 = var1[var6];
            this.getClass();
            if (var10000 > 9) {
                System.out.println("The entered Object collection contains Objects that the convert to highest precision methods cannot process - null returned");
                var7 = false;
                var8 = false;
            } else {
                ++var6;
                if (var6 >= var5) {
                    var7 = false;
                }
            }
        }

        for(int var9 = 0; var9 < var5; ++var9) {
            if (var1[var9] == 8) {
                var1[var9] = 6;
            }
        }

        int var32;
        boolean var34;
        if (var8) {
            var34 = true;
            var32 = Fmath.minimum(var1);
            if (var32 == 0) {
                ArrayList var10 = new ArrayList();
                int var11 = 0;

                int var12;
                for(var12 = 0; var12 < var5; ++var12) {
                    if (var1[var12] == 0) {
                        var10.add(new Integer(var12));
                        ++var11;
                    }
                }

                for(var12 = 0; var12 < var11; ++var12) {
                    byte var33 = -1;
                    int var13 = (Integer)var10.get(var12);

                    try {
                        new BigDecimal(((String)var2[var13]).trim());
                        var33 = 1;

                        try {
                            new BigInteger(((String)var2[var13]).trim());
                            var33 = 2;

                            try {
                                double var16 = Double.valueOf((String)var2[var13]);
                                var33 = 3;

                                try {
                                    float var18 = Float.valueOf((String)var2[var13]);
                                    var33 = 4;

                                    try {
                                        long var19 = Long.valueOf((String)var2[var13]);
                                        var33 = 5;

                                        try {
                                            int var21 = Integer.valueOf((String)var2[var13]);
                                            var33 = 6;

                                            try {
                                                short var22 = Short.valueOf((String)var2[var13]);
                                                var33 = 7;

                                                try {
                                                    byte var23 = Byte.valueOf((String)var2[var13]);
                                                    var33 = 9;
                                                } catch (NumberFormatException var24) {
                                                    var34 = false;
                                                }
                                            } catch (NumberFormatException var25) {
                                                var34 = false;
                                            }
                                        } catch (NumberFormatException var26) {
                                            var34 = false;
                                        }
                                    } catch (NumberFormatException var27) {
                                        var34 = false;
                                    }
                                } catch (NumberFormatException var28) {
                                    var34 = false;
                                }
                            } catch (NumberFormatException var29) {
                                var34 = false;
                            }
                        } catch (NumberFormatException var30) {
                            var34 = false;
                        }
                    } catch (NumberFormatException var31) {
                        var34 = false;
                    }

                    var1[var13] = var33;
                }
            }
        }

        var32 = Fmath.minimum(var1);
        if (var32 == 2) {
            var34 = true;
            var6 = 0;

            while(true) {
                while(var34) {
                    if (var1[var6] > 2 && var1[var6] < 5) {
                        var32 = 1;
                        var34 = false;
                    } else {
                        ++var6;
                        if (var6 >= var5) {
                            var34 = false;
                        }
                    }
                }

                return var32;
            }
        } else {
            return var32;
        }
    }

    public double[] convert_to_double() {
        double[] var1;
        var1 = null;
        label88:
        switch(this.arrayFlag) {
            case 0:
                System.out.println("Method DataType.convert_to_double: The entered Object is not contain a collection - null returned");
                break;
            case 1:
                if (!this.highestDone) {
                    this.toHighestPrecision();
                }

                var1 = new double[this.nArrayElements];
                switch(this.highestCode) {
                    case 1:
                        BigDecimal[] var2 = (BigDecimal[])((BigDecimal[])this.convert_to_highestPrecision());

                        for(int var11 = 0; var11 < this.nArrayElements; ++var11) {
                            var1[var11] = Conv.convert_BigDecimal_to_double(var2[var11]);
                        }

                        return var1;
                    case 2:
                        BigInteger[] var3 = (BigInteger[])((BigInteger[])this.convert_to_highestPrecision());

                        for(int var12 = 0; var12 < this.nArrayElements; ++var12) {
                            var1[var12] = Conv.convert_BigInteger_to_double(var3[var12]);
                        }

                        return var1;
                    case 3:
                        Double[] var4 = (Double[])((Double[])this.convert_to_highestPrecision());

                        for(int var13 = 0; var13 < this.nArrayElements; ++var13) {
                            var1[var13] = var4[var13];
                        }

                        return var1;
                    case 4:
                        Float[] var5 = (Float[])((Float[])this.convert_to_highestPrecision());

                        for(int var14 = 0; var14 < this.nArrayElements; ++var14) {
                            var1[var14] = Conv.convert_Float_to_double(var5[var14]);
                        }

                        return var1;
                    case 5:
                        Long[] var6 = (Long[])((Long[])this.convert_to_highestPrecision());

                        for(int var15 = 0; var15 < this.nArrayElements; ++var15) {
                            var1[var15] = Conv.convert_Long_to_double(var6[var15]);
                        }

                        return var1;
                    case 6:
                        Integer[] var7 = (Integer[])((Integer[])this.convert_to_highestPrecision());

                        for(int var16 = 0; var16 < this.nArrayElements; ++var16) {
                            var1[var16] = Conv.convert_Integer_to_double(var7[var16]);
                        }
                        break label88;
                    case 7:
                    default:
                        return var1;
                    case 8:
                        Short[] var8 = (Short[])((Short[])this.convert_to_highestPrecision());

                        for(int var17 = 0; var17 < this.nArrayElements; ++var17) {
                            var1[var17] = Conv.convert_Short_to_double(var8[var17]);
                        }

                        return var1;
                    case 9:
                        Byte[] var9 = (Byte[])((Byte[])this.convert_to_highestPrecision());

                        for(int var10 = 0; var10 < this.nArrayElements; ++var10) {
                            var1[var10] = Conv.convert_Byte_to_double(var9[var10]);
                        }

                        return var1;
                }
            case 2:
                System.out.println("Method DataType.convert_to_double: The entered Object collection contains dimensioned elements - null returned");
                break;
            case 3:
                System.out.println("Method DataType.convert_to_double: The entered Object array contains an element that is not numerically interconvertable - null returned");
        }

        return var1;
    }

    public static double[] convert_to_double(Object var0) {
        DataType var1 = new DataType(var0);
        return var1.convert_to_double();
    }

    public BigDecimal[] convert_to_BigDecimal() {
        BigDecimal[] var1;
        var1 = null;
        label80:
        switch(this.arrayFlag) {
            case 0:
                System.out.println("Method DataType.convert_to_BigDecimal: The entered Object is not contain a collection - null returned");
                break;
            case 1:
                if (!this.highestDone) {
                    this.toHighestPrecision();
                }

                var1 = new BigDecimal[this.nArrayElements];
                switch(this.highestCode) {
                    case 1:
                        var1 = (BigDecimal[])((BigDecimal[])this.convert_to_highestPrecision());
                        return var1;
                    case 2:
                        BigInteger[] var2 = (BigInteger[])((BigInteger[])this.convert_to_highestPrecision());

                        for(int var10 = 0; var10 < this.nArrayElements; ++var10) {
                            var1[var10] = Conv.convert_BigInteger_to_BigDecimal(var2[var10]);
                        }

                        return var1;
                    case 3:
                        Double[] var3 = (Double[])((Double[])this.convert_to_highestPrecision());

                        for(int var11 = 0; var11 < this.nArrayElements; ++var11) {
                            var1[var11] = Conv.convert_Double_to_BigDecimal(var3[var11]);
                        }

                        return var1;
                    case 4:
                        Float[] var4 = (Float[])((Float[])this.convert_to_highestPrecision());

                        for(int var12 = 0; var12 < this.nArrayElements; ++var12) {
                            var1[var12] = Conv.convert_Float_to_BigDecimal(var4[var12]);
                        }

                        return var1;
                    case 5:
                        Long[] var5 = (Long[])((Long[])this.convert_to_highestPrecision());

                        for(int var13 = 0; var13 < this.nArrayElements; ++var13) {
                            var1[var13] = Conv.convert_Long_to_BigDecimal(var5[var13]);
                        }

                        return var1;
                    case 6:
                        Integer[] var6 = (Integer[])((Integer[])this.convert_to_highestPrecision());

                        for(int var14 = 0; var14 < this.nArrayElements; ++var14) {
                            var1[var14] = Conv.convert_Integer_to_BigDecimal(var6[var14]);
                        }
                        break label80;
                    case 7:
                    default:
                        return var1;
                    case 8:
                        Short[] var7 = (Short[])((Short[])this.convert_to_highestPrecision());

                        for(int var15 = 0; var15 < this.nArrayElements; ++var15) {
                            var1[var15] = Conv.convert_Short_to_BigDecimal(var7[var15]);
                        }

                        return var1;
                    case 9:
                        Byte[] var8 = (Byte[])((Byte[])this.convert_to_highestPrecision());

                        for(int var9 = 0; var9 < this.nArrayElements; ++var9) {
                            var1[var9] = Conv.convert_Byte_to_BigDecimal(var8[var9]);
                        }

                        return var1;
                }
            case 2:
                System.out.println("Method DataType.convert_to_BigDecimal: The entered Object collection contains dimensioned elements - null returned");
                break;
            case 3:
                System.out.println("Method DataType.convert_to_BigDecimal: The entered Object array contains an element that is not numerically interconvertable - null returned");
        }

        return var1;
    }

    public static BigDecimal[] convert_to_BigDecimal(Object var0) {
        DataType var1 = new DataType(var0);
        return var1.convert_to_BigDecimal();
    }

    public String[] convert_to_String() {
        String[] var1;
        var1 = null;
        label88:
        switch(this.arrayFlag) {
            case 0:
                System.out.println("Method DataType.convert_to_String: The entered Object is not contain a collection - null returned");
                break;
            case 1:
                if (!this.highestDone) {
                    this.toHighestPrecision();
                }

                var1 = new String[this.nArrayElements];
                switch(this.highestCode) {
                    case 1:
                        BigDecimal[] var2 = (BigDecimal[])((BigDecimal[])this.convert_to_highestPrecision());

                        for(int var11 = 0; var11 < this.nArrayElements; ++var11) {
                            var1[var11] = Conv.convert_BigDecimal_to_String(var2[var11]);
                        }

                        return var1;
                    case 2:
                        BigInteger[] var3 = (BigInteger[])((BigInteger[])this.convert_to_highestPrecision());

                        for(int var12 = 0; var12 < this.nArrayElements; ++var12) {
                            var1[var12] = Conv.convert_BigInteger_to_String(var3[var12]);
                        }

                        return var1;
                    case 3:
                        Double[] var4 = (Double[])((Double[])this.convert_to_highestPrecision());

                        for(int var13 = 0; var13 < this.nArrayElements; ++var13) {
                            var1[var13] = Conv.convert_Double_to_String(var4[var13]);
                        }

                        return var1;
                    case 4:
                        Float[] var5 = (Float[])((Float[])this.convert_to_highestPrecision());

                        for(int var14 = 0; var14 < this.nArrayElements; ++var14) {
                            var1[var14] = Conv.convert_Float_to_String(var5[var14]);
                        }

                        return var1;
                    case 5:
                        Long[] var6 = (Long[])((Long[])this.convert_to_highestPrecision());

                        for(int var15 = 0; var15 < this.nArrayElements; ++var15) {
                            var1[var15] = Conv.convert_Long_to_String(var6[var15]);
                        }

                        return var1;
                    case 6:
                        Integer[] var7 = (Integer[])((Integer[])this.convert_to_highestPrecision());

                        for(int var16 = 0; var16 < this.nArrayElements; ++var16) {
                            var1[var16] = Conv.convert_Integer_to_String(var7[var16]);
                        }
                        break label88;
                    case 7:
                    default:
                        return var1;
                    case 8:
                        Short[] var8 = (Short[])((Short[])this.convert_to_highestPrecision());

                        for(int var17 = 0; var17 < this.nArrayElements; ++var17) {
                            var1[var17] = Conv.convert_Short_to_String(var8[var17]);
                        }

                        return var1;
                    case 9:
                        Byte[] var9 = (Byte[])((Byte[])this.convert_to_highestPrecision());

                        for(int var10 = 0; var10 < this.nArrayElements; ++var10) {
                            var1[var10] = Conv.convert_Byte_to_String(var9[var10]);
                        }

                        return var1;
                }
            case 2:
                System.out.println("Method DataType.convert_to_String: The entered Object collection contains dimensioned elements - null returned");
                break;
            case 3:
                System.out.println("Method DataType.convert_to_String: The entered Object array contains an element that is not numerically interconvertable - null returned");
        }

        return var1;
    }

    public static String[] convert_to_String(Object var0) {
        DataType var1 = new DataType(var0);
        return var1.convert_to_String();
    }
}
