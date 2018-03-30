package com.dhyx.dbclass;

import com.dhyx.myclass.TestDataClass;
import org.apache.commons.lang3.RandomUtils;

public class PeakClass {
    public static float getPeak(TestDataClass testDataClass, String peakTypeID, int left, int right, int N) {

        switch (peakTypeID) {
            case "1":
                return method1(testDataClass,left,right,N);
            case "2":
                return method2(testDataClass,left,right,N);
            default:
                return 0;
        }
    }

    private static float method1(TestDataClass testDataClass,int left, int right,int N) {
        float peakValue = RandomUtils.nextInt(0,5);

        return peakValue;
    }

    private static float method2(TestDataClass testDataClass,int left, int right,int N) {
        float peakValue = 0.2F;


        return peakValue;
    }

}
