package com.dhyx.myclass;

public class Peak {

    /**
     * 传入峰的参数，计算峰面积
     * @param testData 传入的数据数组，自定义类TestData
     * @param peakTypeID 传入的取峰算法ID
     * @param left  峰的左边界
     * @param right 峰的右边界
     * @param N     峰的取数，并非所有取峰算法都需要
     * @return  返回峰面积，默认为0，调用方会抛出异常，捕获峰面积<=0的情况
     */
    public static float getPeak(TestData testData, String peakTypeID, int left, int right, int N) {
        float result;
        switch (peakTypeID) {
            case "1":
                result = methodTPA(testData, left, right, N);
                break;
            case "2":
                result = methodCovell(testData, left, right, N);
                break;
            case "3":
                result = methodWaston(testData, left, right, N);
                break;
            default:
                result = 0;
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
    private static float methodTPA(TestData td, int left, int right, int n) {

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
    }   // END : private static float methodTPA()


    /**
     * ID=2，Covell（科沃尔）峰面积法
     * @param td 测试数据
     * @param left 峰的左边界
     * @param right 峰的右边界
     * @param n 边界外扩展的用于求本底均值的N，不能小于0
     * @return 返回计算值
     */
    private static float methodCovell(TestData td, int left, int right, int n) {
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
    }   // END : private static float methodCovell()


    /**
     * ID=3，沃森法
     * @param td 测试数据
     * @param left 峰的左边界
     * @param right 峰的右边界
     * @param n 边界外扩展的用于求本底均值的N，不能小于0
     * @return 返回计算值
     */
    private static float methodWaston(TestData td, int left, int right, int n) {
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
    }   // END : private static float methodWaston()

}
