package com.dhyx.myclass;

public class TestDataClass {
    public int[] x;
    public float[] y;
    public int length = 0;


    TestDataClass(int length) {
        x = new int[length];
        y = new float[length];
        this.length = length;
    }


}
