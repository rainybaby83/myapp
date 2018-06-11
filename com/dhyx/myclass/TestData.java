package com.dhyx.myclass;

/**
 * 从设备采集的已加工的数据，二维数组，X为横坐标值，Y为纵坐标值，length为数据数组的长度
 * @author R2
 */
public class TestData {
    public int[] x;
    public float[] y;
    public int length = 0;
    public ProjectClass p;


    TestData(int length) {
        x = new int[length];
        y = new float[length];
        this.length = length;
        p = null;
    }


    public void setProject(ProjectClass projectClass) {
        this.p = projectClass;
    }


    public float getX1Value() throws ArithmeticException{
        float x1Value = Peak.getPeak(this, p.peakTypeID, p.x1Left, p.x1Right, p.x1N);
        if (x1Value <= 0) {
            throw new ArithmeticException("峰面积小于0，流程中止。请调整峰的左右边界及N，或尝试换一种取峰算法。");
        } else {
            return x1Value;
        }
    }


    public float getX2Value() throws ArithmeticException{
        float x2Value = Peak.getPeak(this, p.peakTypeID, p.x2Left, p.x2Right, p.x2N);
        if (x2Value <= 0) {
            throw new ArithmeticException("峰面积小于0，流程中止。请调整峰的左右边界及N，或尝试换一种取峰算法。");
        } else {
            return x2Value;
        }
    }


    public float getMaxY(int left, int right) {
        float maxY = y[left];
        for (int i = left; i <= right; i++) {
            if (y[i] > maxY) {
                maxY = y[i];
            }
        }
        return maxY;
    }


    /**
     * 获得区域内最高值Y对应的X坐标值
     * @param left  X取值区域的左边界
     * @param right X取值区域的右边界
     * @return Y最高值对应的X值
     */
    public int getMaxX(int left, int right) {
        float maxY = y[left];
        int maxX = left;
        for (int i = left; i <= right; i++) {
            if (y[i] > maxY) {
                maxY = y[i];
                maxX = i;
            }
        }
        return maxX;
    }
}
