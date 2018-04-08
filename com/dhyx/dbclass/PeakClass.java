package com.dhyx.dbclass;

import com.dhyx.myclass.TestDataClass;

import javax.swing.*;

public class PeakClass {
    public static float getPeak(TestDataClass testData, String peakTypeID, int left, int right, int N) {

        float result;
        switch (peakTypeID) {
            case "1":
                result = method_TPA(testData, left, right, N);
                break;
            case "2":
                result = method_Covell(testData, left, right, N);
                break;
            case "3":
                result = method_Waston(testData, left, right, N);
                break;
            default:
                result = 0.00001f;
        }

        if (result <= 0) {
            JOptionPane.showMessageDialog(null,"峰面积小于0，请选其它算法，或调整左右边界及取数N。");
        }
        return result;

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
        // 下标从0，序号从1开始，所以下标要减1。left<= 序号 <= right，左右边界都包含
        float areaS = 0;
        for (int i = left - 1; i <= right - 1; i++) {
            areaS += td.y[i];
        }

        // 2 计算底面积
        // 2.1 计算左侧本底，包含左边界，不含右边界
        float areaBL = 0, areaBR = 0, areaB = 0;
        int start1 = left - n > 1 ? left - n : 1;
        int end1 = left;
        for (int i = start1 - 1; i < end1 - 1; i++) {
            areaBL += td.y[i];
        }

        // 2.2 计算右侧本底，不含左边界，包含右边界
        int start2 = right;
        int end2 = right + n < td.x.length ? right + n : td.x.length;
        for (int i = start2 ; i <= end2 - 1; i++) {
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
        // 下标从0，序号从1开始，所以下标要减1。left<= 序号 <= right，左右边界都包含
        float areaS = 0;
        for (int i = mLeft - 1; i <= mRight - 1; i++) {
            areaS += td.y[i];
        }

        // 3 计算底面积
        float areaB = 0;
        areaB = (td.y[mLeft] + td.y[mRight]) * (2 * n + 1) / 2;

        // 4 净峰面积
        return areaS - areaB;
    }   // END : private static float method_Covell()


    /**
     * ID=3，沃森法
     * @param td 测试数据
     * @param left 峰的左边界
     * @param right 峰的右边界
     * @param n 边界外扩展的用于求本底均值的N，不能小于0
     * @return 返回计算值
     */
    private static float method_Waston(TestDataClass td, int left, int right,int n) {
        // 1 计算区间最高值的X位置
        int m = td.getMaxX(left,right);
        int mLeft = m - n > left ? m - n : left;
        int mRight = m + n < right ? m + n : right;

        // 2 计算总面积
        // 下标从0，序号从1开始，所以下标要减1。left<= 序号 <= right，左右边界都包含
        float areaS = 0;
        for (int i = mLeft - 1; i <= mRight - 1; i++) {
            areaS += td.y[i];
        }

        // 3 计算底面积
        float sideLeft,sideRight,areaB;
        sideLeft = td.y[left] + (td.y[right] - td.y[left]) * (m - n - left) / (right - left);
        sideRight = td.y[left] + (td.y[right] - td.y[left]) * (m + n - left) / (right - left);
        areaB = (sideLeft + sideRight) * (2 * n + 1) / 2;

        // 4 净峰面积
        return areaS - areaB;
    }

}
