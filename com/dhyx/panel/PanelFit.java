package com.dhyx.panel;

import com.dhyx.myclass.Const;
import com.dhyx.myclass.MyIconButton;
import com.dhyx.myclass.MyTable;
import com.flanagan.analysis.Regression;
import com.flanagan.physchem.ImmunoAssay;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class PanelFit extends JDialog {

    private ImmunoAssay assay;
    private MyTable table;
    private int selectMethod;
    MyIconButton btnViewImage;
    private ImageIcon icon = Const.ICON_VIEW_IMAGE;



    PanelFit(double[] concentrations, double[] values) {
        //设置窗口弹出的相应属性
        super((Frame) null, "双击一种拟合后返回", true);
        this.setIconImage(Const.ICON_APP.getImage());
        this.setLayout(null);
        this.setBounds(0, 0, 1100, 600);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.white);

        double[] xArray = {-0.301029996, 0, 0.301029996, 0.602059991, 1.301029996, 2, 2.397940009, 2.698970004, 2.875061263, 3};
        double[] yArray = {-1.210066919, -0.735418271, -0.607127255, -0.410832609, 0.075765878, 0.772915521, 0.939549203, 1.333288038, 1.383134677, 1.624252179};

        //将数组的浓度、反应值，写入直线拟合、曲线拟合类
        assay = new ImmunoAssay(xArray,yArray);
//        assay = new ImmunoAssay(concentrations,values);
        assay.setData();
        assay.setPlotStatus(true);

        //查看图形按钮
        btnViewImage = new MyIconButton(Const.ICON_VIEW_IMAGE, Const.ICON_VIEW_IMAGE_ENABLED, Const.ICON_VIEW_IMAGE);
        btnViewImage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null,1234);
            }
        });
        btnViewImage.setBounds(0,0,120,30);

        //初始化表格
        initTable();
        table.getColumnModel().getColumn(9).setCellEditor(new MyEditor());
    }


    //创建表格，设置基本属性
    private void initTable() {
        //重写渲染器
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (column ==2) {
                    this.setIcon((Icon) value);
                    this.setText("");
                } else {
                    setIcon(null);
                }
                return this;
            }
        };

        renderer.setHorizontalAlignment(JLabel.CENTER);
        table = new MyTable(createTableModel()){
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 9;
            }
        };
        table.setDefaultRenderer(Object.class,renderer);
        table.setBounds(40, 20, 1000, 500);
        table.setRowHeight(70);
        table.setWidth(40,80,200,80,80,80,80,80,80,150);
        table.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.lightGray));
        table.jScrollPane.setBounds(table.getBounds());
        this.add(table.jScrollPane);
    }


    private DefaultTableModel createTableModel() {
        // 1 创建表头
        Vector<String> vectorHeader = getTableHeader();
        // 2 添加拟合方法
        Vector<Vector> datas= new Vector<>();
        datas.addElement(getLinear());
        datas.addElement(get4PL());
        datas.addElement(get5PL());

        //创建DefaultTableModel
        DefaultTableModel dm = new DefaultTableModel(datas, vectorHeader);
        return dm;
    }


    //创建表头，被initTableData()引用
    private Vector<String> getTableHeader() {
        //初始化表头
        Vector<String> vecHeader = new Vector<>();
        String[] titles = {"序号","方法", "公式", "a", "b", "c", "d", "e", "R2","查看图形"};
        for (String title : titles) {
            vecHeader.addElement(title);
        }
        return  vecHeader;
    }



    //返回直线拟合的Vector数据，用于创建表格
    private Vector getLinear(){
        Vector<Object> data = new Vector<>();
        data.addElement("1");
        data.addElement("直线拟合");
        data.addElement(Const.ICON_LINEAR);

        assay.linear();
        double[] result = assay.getBestEstimates();
        for (double aResult : result) {
            data.addElement(aResult);
        }

        data.addElement("无");
        data.addElement("无");
        data.addElement("无");
        data.addElement(assay.getCoefficientOfDetermination());
//        data.addElement(btnViewImage);

        return data;
    }


    //四参数拟合
    private Vector get4PL() {
        Vector<Object> data = new Vector<>();
        data.addElement("2");
        data.addElement("四参数拟合");
        data.addElement(Const.ICON_4PL);
        assay.fourParameterLogisticFit();
        double[] result = assay.getModelParameterValues();
        for (double aResult : result) {
            data.addElement(aResult);
        }

        data.addElement("无");
//        data.addElement(btnViewImage);
        return data;
    }



    //五参数拟合
    private Vector get5PL() {
        Vector<Object> data = new Vector<>();
        data.addElement("3");
        data.addElement("五参数拟合");
        data.addElement(Const.ICON_5PL);
        assay.fiveParameterLogisticFit();
        double[] para = assay.getModelParameterValues();
        for (double aResult1 : para) {
            data.addElement(aResult1);
        }
        return data;
    }

}

class MyEditor extends AbstractCellEditor implements TableCellRenderer, TableCellEditor {
    private static final long serialVersionUID = 1L;
    private MyIconButton btnViewImage;

    MyEditor() {
        btnViewImage = new MyIconButton(Const.ICON_VIEW_IMAGE, Const.ICON_VIEW_IMAGE_ENABLED, Const.ICON_VIEW_IMAGE);
        btnViewImage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null,1234);
            }
        });

    }

    @Override
    public Object getCellEditorValue() {
        return btnViewImage;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        // TODO Auto-generated method stub
        return btnViewImage;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        return btnViewImage;
    }
}
