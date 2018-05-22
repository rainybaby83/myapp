package com.dhyx.panel;

import com.dhyx.myclass.Const;
import com.dhyx.myclass.MyTable;
import com.flanagan.physchem.ImmunoAssay;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.Vector;

public class PanelFit extends JDialog {

    private ImmunoAssay assay = new ImmunoAssay() ;

    PanelFit(double[] concentrations, double[] values) {

        super((Frame) null, "查看所有拟合", true);
        this.setIconImage(Const.ICON_APP.getImage());
        //将数组的浓度、反应值，写入免疫类
        assay.enterAnalyteConcns(concentrations);
        assay.enterResponses(values);

        initSelf();
        initTable();
    }



    private void initSelf() {
        this.setLayout(null);
        this.setBounds(0, 0, 1000, 600);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.white);

    }


    private void initTable() {
        //初始化表头
        Vector<String> vecHeader = new Vector<>();
        String[] titles = {"方法", "公式", "R", "a", "b", "c", "d", "e", "查看图形"};
        for (String title : titles) {
            vecHeader.addElement(title);
        }

        //初始化内容
        Vector datas = fit();


        //重写渲染器
        TableCellRenderer tcr = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent
                    (JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {


                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                if (value instanceof ImageIcon) {
                    this.setIcon((Icon) value);
                } else {
                    setIcon(null);
                }
                return this;

            }
        };

        //创建表格
        DefaultTableModel dm = new DefaultTableModel(datas, vecHeader);
        MyTable table = new MyTable(dm) ;
        table.setDefaultRenderer(Object.class,tcr);

        table.setBounds(40, 20, 900, 500);
        table.setRowHeight(60);
        table.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.lightGray));
        table.jScrollPane.setBounds(table.getBounds());
        this.add(table.jScrollPane);
    }



    private Vector fit() {
        Vector data1 = new Vector<>();
        data1.addElement("五参数拟合");
        data1.addElement(Const.MENU_QUERY_BATCH);
        assay.setPlotStatus(true);
        assay.fiveParameterLogisticFit();
        double[] result1 = assay.getModelParameterValues();
        for (int i = 0; i < result1.length; i++) {
            data1.addElement(result1[i]);
        }



        Vector data2 = new Vector<>();
        data2.addElement("四参数拟合");
        data2.addElement(Const.ICON_DEL);
        assay.fourParameterLogisticFit();
        double[] result2 = assay.getModelParameterValues();
        for (int i = 0; i < result2.length; i++) {
            data2.addElement(result2[i]);
        }

        Vector<Vector> datas= new Vector<>();
        datas.addElement(data1);
        datas.addElement(data2);
        return datas;
    }


}
