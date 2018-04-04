package com.dhyx.myclass;

import com.dhyx.dbclass.PeakClass;
import com.dhyx.dbclass.ProjectClass;
import com.dhyx.dbclass.TCClass;

public class TestDataClass {
    public int[] x;
    public float[] y;
    public int length = 0;
    public ProjectClass p;
    private float x1Value,x2Value;


    TestDataClass(int length) {
        x = new int[length];
        y = new float[length];
        this.length = length;
        p = null;
    }

    public void setProject(ProjectClass projectClass) {
        this.p = projectClass;
    }

    public float getX1Value() {
        x1Value = PeakClass.getPeak(this, p.peakTypeID, p.x1Left, p.x1Right, p.x1N);
        return x1Value;
    }

    public float getX2Value() {
        x2Value = PeakClass.getPeak(this, p.peakTypeID, p.x2Left, p.x2Right, p.x2N);
        return x2Value;
    }

    public float getTCValue() throws Exception {
        return TCClass.getTCValue(p.tcTypeID, getX1Value(), getX2Value());

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
