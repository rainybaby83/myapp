package com.dhyx.myclass;

public class MyFitData {
    public double[] c;
    public double[] v;
    public int length;
    public double blank = 0;


    /**构造方法
     * @param length 拟合数据的个数
     */
    public MyFitData(int length) {
        this.c = new double[length];
        this.v = new double[length];
        this.length = length;
    }


    /**
     * 以传入浓度值和反应值的方式，构造实例。暂时没使用此方法。
     * @param concentrations    浓度值数组
     * @param values    反应值数组
     */
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


    /**
     * 处理浓度为0的数据，减本底，并将本底值写入this.blank成员变量;
     * 扣掉本底值的反应值，直接写入this.c
     * 剔除浓度值为0的那个数据，数组长度减1
     * @param ratio 扣本底的系数，最大100%，最小0
     * @return  处理结果成功还是失败
     */
    public boolean removeBlank(double ratio) {

        boolean b ;
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
            double baseBlank = this.v[index];

            for (int i = 0; i < v.length; i++) {
                baseBlank = Math.min(baseBlank, v[i]);
            }

            if (baseBlank != v[index]) {
                //浓度0数据对应的value，不是最小值，返回失败
                b = false;
                this.blank = 0;
            } else {
                this.blank = baseBlank * ratio;
                double[] newV = new double[length - index - 1];
                double[] newC = new double[length - index - 1];
                for (int i = 0; i < index; i++) {
                    newC[i] = c[i];
                    newV[i] = v[i] - this.blank;
                }
                for (int i = index + 1; i < length; i++) {
                    newC[i - 1] = c[i];
                    newV[i - 1] = v[i] - blank;
                }

                c= newC;
                v = newV;
                b = true;
            }
        }

        return b;
    }
}
