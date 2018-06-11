package com.dhyx.myclass;


public class TC {

    /**
     * 根据输入的峰面积值、TC比计算方法的ID，计算TC比
     * @param tcTypeID  TC比计算方法的ID
     * @param x1 峰1
     * @param x2 峰1
     * @return T/C的值
     */
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


    /**
     * ID = 1 , 公式 = X1/X2，T线在左，C线在右
     * @param x1 峰1
     * @param x2 峰2
     * @return x1除以x2
     */
    private static float method1(float x1, float x2) {
        // 调用前已判断分母不为0，已throw
        return x1 / x2;
    }


    /**
     * ID = 2 , 公式 = X2/X1，C线在左，T线在右
     * @param x1 峰1
     * @param x2 峰2
     * @return 将x1和x2对调，调用method1()
     */
    private static float method2(float x1,float x2) {
        return method1(x2, x1);
    }
}
