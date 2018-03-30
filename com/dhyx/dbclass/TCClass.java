package com.dhyx.dbclass;

import com.dhyx.myclass.TestDataClass;

public class TCClass {
    public static float getTCValue(String tcTypeID,float x1,float x2) {

        switch (tcTypeID) {
            case "1":
                return method1(x1, x2);
            case "2":
                return method2(x2, x1);
            default:
                return 0;
        }
    }

    private static float method1(float x1,float x2) {
        return x1/x2;
    }


    private static float method2(float x1,float x2) {
        return x2/x1;
    }
}
