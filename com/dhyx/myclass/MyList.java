package com.dhyx.myclass;

import org.apache.commons.lang3.math.NumberUtils;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class MyList extends JList<String> {
    public  JScrollPane j;
    private ListModel<String> m;
    public int maxNumber=0;

    public MyList(int start,int end) {
        super();
        addNumber(start, end);
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
        j = new JScrollPane(this);
        j.getVerticalScrollBar().setUI(new MyScrollBarUI());
        m = this.getModel();
    }


    //按照数字范围，添加到列表中
    private void addNumber(int start, int end) {
        this.removeAll();
        Vector<String> vector = new Vector<>();
        for (int i = start; i <= end; i++) {
            vector.addElement(String.valueOf(i));
        }
        this.setListData(vector);
        maxNumber = end;
    }


    //根据传入的序号，遍历下拉框，选中跟传入参数一样的那一行数据
    public void setSelectedByValue(int number) {
        int i = 0;
        for (i=0; i < m.getSize(); i++) {
            if (number==(NumberUtils.toInt(m.getElementAt(i)))) {
                break;
            }
        }

        if (i != m.getSize()) {
            setSelectedIndex(i);
            ensureIndexIsVisible(i);
        } else {
            clearSelection();
        }
    }


    //选中当前行的下一行。
    public void nextSelectedRow() {
        int nowIndex = this.getSelectedIndex();
        if (nowIndex < m.getSize()) {
            setSelectedIndex(nowIndex + 1);
            ensureIndexIsVisible(nowIndex + 1);
        }
    }



}
