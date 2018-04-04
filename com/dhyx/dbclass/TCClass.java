package com.dhyx.dbclass;

import com.dhyx.myclass.TestDataClass;
import org.apache.commons.lang3.math.NumberUtils;

public class TCClass {
    public static float getTCValue(String tcTypeID,float x1,float x2)  throws Exception{

        switch (tcTypeID) {
            case "1":
                return method1(x1, x2);
            case "2":
                return method2(x2, x1);
            default:
                return 0;
        }
    }


    /**
     * ID = 1 , 公式 = X1/X2
     */
    private static float method1(float x1,float x2) throws Exception{
        if (-0.000000001f < x2 && x2 < 0.000000001f) {
            throw new Exception("除数不能为0");
        } else {
            return x1 / x2;
        }
    }


    /**
     * ID = 2 , 公式 = X2/X1
     */
    private static float method2(float x1,float x2) throws Exception{
        return method1(x2, x1);
    }
}
