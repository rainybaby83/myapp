package com.dhyx.myclass;

public class MyFitData {
    public double[] c;
    public double[] v;
    public int length;

    public MyFitData(int length) {
        this.c = new double[length];
        this.v = new double[length];
        this.length = length;
    }

    public MyFitData(double[] concentrations, double[] values) {
        if (concentrations.length == values.length) {
            this.c = concentrations;
            this.v = values;
            length = concentrations.length;
        } else {
            int min = Math.min(concentrations.length, values.length);
            this.c = new double[min];
            this.v = new double[min];
            for (int i = 0; i < min; i++) {
                this.c[i] = concentrations[i];
                values[i] = values[i];
            }
            length = min;
        }
    }



    //处理浓度为0的数据，减本底
    public boolean removeBlank(double scale) {

        boolean b = false;
        //调用此函数时，已判断浓度值为0的数据只有1个
        int index = -1;

        //找到0数据的索引
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 0.0D) {
                index = i;
                break;
            }
        }

        //遍历后没有找到0数据，返回成功，不处理数据。
        if (index == -1) {
            b = true;
        } else {
            //有0数据，扣本底
            double blank = this.v[index];

            for (int i = 0; i < v.length; i++) {
                blank = Math.min(blank, v[i]);
            }

            if (blank != v[index]) {
                //浓度0数据对应的value，不是最小值，返回失败
                b = false;
            } else {
                double[] newV = new double[length - index - 1];
                double[] newC = new double[length - index - 1];
                for (int i = 0; i < index; i++) {
                    newC[i] = c[i];
                    newV[i] = v[i] - blank * scale;
                }
                for (int i = index + 1; i < length; i++) {
                    newC[i - 1] = c[i];
                    newV[i - 1] = v[i] - blank * scale;
                }

                c= newC;
                v = newV;
                b = true;
            }
        }

        return b;
    }
}
