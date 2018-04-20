package com.dhyx.myclass;

import org.apache.commons.lang3.math.NumberUtils;

import javax.swing.*;
import java.util.Vector;

public class MyList extends JList<String> {
    public  JScrollPane j;
    private ListModel<String> model;
    public int maxNumber=0;

    public MyList(int start,int end) {
//        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
//        jScrollPane = new JScrollPane(this);
//        jScrollPane.getVerticalScrollBar().setUI(new MyScrollBarUI());
//        model = this.getModel();
        MyList();
        addNumber(start, end);
        model = this.getModel();
    }


    public void MyList() {
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
        j = new JScrollPane(this);
        j.getVerticalScrollBar().setUI(new MyScrollBarUI());

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
        for (i=0; i < model.getSize(); i++) {
            if (number==(NumberUtils.toInt(model.getElementAt(i)))) {
                break;
            }
        }

        if (i != model.getSize()) {
            setSelectedIndex(i);
            ensureIndexIsVisible(i);
        } else {
            clearSelection();
        }
    }


    //选中当前行的下一行。
    public void nextSelectedRow() {
        int nowIndex = this.getSelectedIndex();
        if (nowIndex < model.getSize()) {
            setSelectedIndex(nowIndex + 1);
            ensureIndexIsVisible(nowIndex + 1);
        }
    }



}
