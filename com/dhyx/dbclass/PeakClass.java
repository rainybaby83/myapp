package com.dhyx.dbclass;

import com.dhyx.myclass.TestDataClass;
import org.apache.commons.lang3.RandomUtils;

public class PeakClass {
    public static float getPeak(TestDataClass testData, String peakTypeID, int left, int right, int N) {

        switch (peakTypeID) {
            case "1":
                return method_TPA(testData,left,right,N);
            case "2":
                return method_Covell(testData,left,right,N);
            default:
                return 0.00001f;
        }
    }


    /**
     * ID=1，线性本底法，又叫总峰面积法，TPA法
     * @param td 测试数据
     * @param left 峰的左边界
     * @param right 峰的右边界
     * @param n 边界外扩展的用于求本底均值的N
     * @return 返回计算值
     */
    private static float method_TPA(TestDataClass td, int left, int right,int n) {
        // 1 计算总面积
        float areaS = 0;
        for (int i = left; i <= right; i++) {
            areaS += td.y[i];
        }

        // 2 计算底面积
        float areaBL = 0, areaBR = 0, areaB = 0;
        int start1 = left - n > 0 ? left - n : 0;
        int end1 = left;
        for (int i = start1; i < end1; i++) {
            areaBL += td.y[i];
        }

        int start2 = right + 1 < td.x.length ? right + 1 : td.x.length;
        int end2 = right + n < td.x.length ? right + n : td.x.length;
        for (int i = start2; i <= end2; i++) {
            areaBR += td.y[i];
        }

        areaB = (areaBL / n + areaBR / n) * (right - left + 1) / 2;

        // 3 净峰面积
        return areaS - areaB;
    }   // END : private static float method_TPA()



    /**
     * ID=2，Covell（科沃尔）峰面积法
     * @param td 测试数据
     * @param left 峰的左边界
     * @param right 峰的右边界
     * @param n 边界外扩展的用于求本底均值的N，不能小于0
     * @return 返回计算值
     */
    private static float method_Covell(TestDataClass td, int left, int right,int n) {

        // 1 计算区间最高值的X位置
        int m = td.getMaxX(left,right);
        int mLeft = m - n > left ? m - n : left;
        int mRight = m + n < right ? m + n : right;


        // 2 计算总面积
        float areaS = 0;
        for (int i = mLeft; i <= mRight; i++) {
            areaS += td.y[i];
        }

        // 3 计算底面积
        float areaB = 0;
        areaB = (td.y[mLeft] + td.y[mRight]) * (2 * n + 1) / 2;

        // 4 净峰面积
        return areaS - areaB;
    }   // END : private static float method_Covell()




}
