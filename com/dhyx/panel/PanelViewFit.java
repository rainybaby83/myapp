package com.dhyx.panel;

import com.dhyx.myclass.Const;
import com.dhyx.myclass.MyFitData;
import com.dhyx.myclass.MyTable;
import com.flanagan.analysis.Regression;
import com.flanagan.physchem.ImmunoAssay;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.Vector;

public class PanelViewFit extends JDialog {
    private Logger logger = LogManager.getLogger();
    private Regression assayLinear;
    private ImmunoAssay assay4PL, assay5PL;
    private MyTable tblFit;
    private int selectMethod;
    private DecimalFormat df = new DecimalFormat("0.0000");
    private PanelNewCurve parent;
    private boolean checkLinear = true, check4PL = true, check5PL = true;
    private boolean checkLinearR2 = true, check4PLR2 = true, check5PLR2 = true;
    private JPanel panelGragh = new JPanel();
    private static int HEIGHT_SHORT = 320, HEIGHT_TALL = 700;



    PanelViewFit(PanelNewCurve parent, MyFitData data, int xLog, int yLog) {

        //设置窗口弹出的相应属性
        super((Frame) null, "选择一种拟合方法，双击返回结果", true);
        this.parent = parent;
        this.setIconImage(Const.ICON_APP.getImage());
        this.setLayout(null);
        this.setBounds(0, 0, 1050, HEIGHT_SHORT);
        this.frameMoveCenter();
        this.setBackground(Color.white);
        this.getContentPane().setBackground(Color.white);
        this.setResizable(false);

        panelGragh.setLayout(null);
        panelGragh.setOpaque(false);
        panelGragh.setBounds(250, 300, 600, 400);
        this.add(panelGragh);

        //直线拟合处理x对数
        double[] logConcentrations, logValues;

        switch (xLog) {
            case 0:
                logConcentrations = data.c;
                break;
            case 1:
                logConcentrations = log10(data.c);
                break;
            case 2:
                logConcentrations = logE(data.c);
                break;
            default:
                logConcentrations = data.c;
        }

        //直线拟合处理y对数
        switch (yLog) {
            case 0:
                logValues = data.v;
                break;
            case 1:
                logValues = log10(data.v);
                break;
            case 2:
                logValues = logE(data.v);
                break;
            default:
                logValues = data.v;
        }
        assayLinear = new Regression(logConcentrations, logValues);

        //将数组的浓度、反应值，写入曲线拟合类
        assay4PL = new ImmunoAssay(data.c, data.v);
        assay5PL = new ImmunoAssay(data.c, data.v);

        //初始化表格
        createTable();
    }


    //创建表格，设置基本属性
    private void createTable() {
        tblFit = new MyTable(createTableModel()) {
        };
        tblFit.j.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        tblFit.j.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        tblFit.setBounds(40, 20, 950, 233);
        tblFit.setRowHeight(70);
        tblFit.setWidth(40, 200, 80, 80, 80, 80, 80, 80, 80, 150);
        tblFit.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.lightGray));

        //第3列图片，使用自定义的渲染器，以便显示图片
        int index = ((DefaultTableModel) tblFit.getModel()).findColumn("公式");
        tblFit.getColumnModel().getColumn(index).setCellRenderer(new imageRenderer());
        tblFit.getColumnModel().getColumn(index).setResizable(false);

        //第10列按钮，使用自定义编辑器。
        index = ((DefaultTableModel) tblFit.getModel()).findColumn("查看图形");
        tblFit.getColumnModel().getColumn(index).setCellRenderer(new imageRenderer());

        // 添加鼠标单击响应事件
        tblFit.addMouseListener(new tableMouseAdapter());

        //添加到面板
        tblFit.j.setBounds(tblFit.getBounds());
        this.add(tblFit.j);
    }


    //创建表格的TableModel
    private DefaultTableModel createTableModel() {
        // 1 创建表头
        Vector<String> vectorHeader = getTableHeader();
        // 2 添加拟合方法
        Vector<Vector> datas = new Vector<>();
        datas.removeAllElements();
        datas.addElement(getLinear());
        datas.addElement(get4PL());
        datas.addElement(get5PL());

        //创建DefaultTableModel
        DefaultTableModel dm = new DefaultTableModel(datas, vectorHeader);
        return dm;
    }


    //创建表头，createTableModel()调用
    private Vector<String> getTableHeader() {
        //初始化表头
        Vector<String> vecHeader = new Vector<>();
        String[] titles = {"序号", "公式", "拟合方法", "a", "b", "c", "d", "e", "R2", "查看图形"};
        for (String title : titles) {
            vecHeader.addElement(title);
        }
        return vecHeader;
    }


    //返回直线拟合的Vector数据，用于创建表格
    private Vector getLinear() {
        Vector<Object> data = new Vector<>();
        data.addElement("1");
        data.addElement(Const.ICON_LINEAR);
        data.addElement("直线拟合");

        assayLinear.linear();
        double[] para = assayLinear.getBestEstimates();
        for (int i = 0; i < para.length; i++) {
            if (Math.abs(para[i]) < 0.0001) {
                checkLinear = false;
            }
            data.addElement(df.format(para[i]));
        }

        data.addElement("无");
        data.addElement("无");
        data.addElement("无");

        //获得拟合优度R2
        double r2 = assayLinear.getCoefficientOfDetermination();
        if (r2 < 0.6 || Double.isNaN(r2)) {
            checkLinearR2 = false;
        }
        data.addElement(df.format(r2));
        data.addElement(Const.ICON_VIEW_IMAGE);
        return data;
    }


    //四参数拟合
    private Vector get4PL() {
        Vector<Object> data = new Vector<>();
        data.addElement("2");
        data.addElement(Const.ICON_4PL);
        data.addElement("四参数拟合");
        assay4PL.fourParameterLogisticFit();
        double[] para = assay4PL.getModelParameterValues();

        for (int i = 0; i < para.length; i++) {
            if (Math.abs(para[i]) < 0.0001) {
                check4PL = false;
            }
            data.addElement(df.format(para[i]));
        }

        data.addElement("无");
        double r2 = assay4PL.getCoefficientOfDetermination();
        if (r2 < 0.6 || Double.isNaN(r2)) {
            check4PLR2 = false;
        }
        data.addElement(df.format(r2));
        data.addElement(Const.ICON_VIEW_IMAGE);
        return data;
    }


    //五参数拟合
    private Vector get5PL() {
        Vector<Object> data = new Vector<>();
        data.addElement("3");
        data.addElement(Const.ICON_5PL);
        data.addElement("五参数拟合");
        assay5PL.fiveParameterLogisticFit();
        double[] para = assay5PL.getModelParameterValues();

        for (int i = 0; i < para.length; i++) {
            if (Math.abs(para[i]) < 0.0001) {
                check5PL = false;
            }
            data.addElement(df.format(para[i]));
        }

        double r2 = assay5PL.getCoefficientOfDetermination();
        if (r2 < 0.6 || Double.isNaN(r2)) {
            check5PLR2 = false;
        }

        data.addElement(df.format(r2));
        data.addElement(Const.ICON_VIEW_IMAGE);
        return data;
    }


    //左键单击表格中的“查看图形”按钮后，弹出对应的曲线图形
    private void viewGragh(String msg) {
        switch (selectMethod) {
            case 1:
                assayLinear.plotXY();
                panelGragh.removeAll();
                panelGragh.add(assayLinear.graph);
                break;
            case 2:
                assay4PL.plotCurve();
                panelGragh.removeAll();
                panelGragh.add(assay4PL.graph);
                break;
            case 3:
                assay5PL.plotCurve();
                panelGragh.removeAll();
                panelGragh.add(assay5PL.graph);
                break;
            default:
        }
    }


    //关闭本窗口，向上一级窗口传递拟合结果
    private void returnParent(String msg) {

        //顺序：拟合方法,公式图片，a,b,c,d,e,R2
        Vector<Object> fitResults = new Vector<>();
        int nowRow = tblFit.getSelectedRow();
        DefaultTableModel dm = (DefaultTableModel) tblFit.getModel();
        String[] columnName = {"序号","公式", "拟合方法", "a", "b", "c", "d", "e", "R2"};
        for (int i = 0; i < columnName.length; i++) {
            fitResults.addElement(tblFit.getValueAt(nowRow, dm.findColumn(columnName[i])));
        }
        parent.receiveFitData(fitResults);
    }


    private double[] log10(double[] resource) {
        double[] result = new double[resource.length];
        for (int i = 0; i < resource.length; i++) {
            result[i] = Math.log10(resource[i]);
        }
        return result;
    }


    private double[] logE(double[] resource) {
        double[] result = new double[resource.length];
        for (int i = 0; i < resource.length; i++) {
            result[i] = Math.log(resource[i]);
        }
        return result;
    }


    private void frameHideGraph() {
        setSize(getWidth(), HEIGHT_SHORT);
        panelGragh.setVisible(false);
    }

    private void frameDisplayGraph() {
        setSize(getWidth(), HEIGHT_TALL);
        panelGragh.setVisible(true);
    }


    private void frameMoveCenter() {
        Toolkit tk = Toolkit.getDefaultToolkit();
        this.setLocation((tk.getScreenSize().width - this.getWidth()) / 2,
                (tk.getScreenSize().height - this.getHeight()) / 2);
    }

    //内部类 图片渲染器
    class imageRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(
                JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (value instanceof ImageIcon) {
                this.setIcon((Icon) value);
                this.setText("");
            }

            return this;
        }
    }


    //内部类，表格的鼠标单双击监听器
    class tableMouseAdapter extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            //左键
            if (e.getButton() == MouseEvent.BUTTON1) {
                //被选中的行索引，从0开始
                int nowRow = tblFit.getSelectedRow();
                int indexOrder = ((DefaultTableModel) tblFit.getModel()).findColumn("序号");
                selectMethod = NumberUtils.toInt((String) tblFit.getValueAt(nowRow, indexOrder));

                //单击
                if (e.getClickCount() == 1) {
                    // “查看图形”按钮所在的列索引，从0开始
                    int index = ((DefaultTableModel) tblFit.getModel()).findColumn("查看图形");
                    int clickedIndex = tblFit.getTableHeader().columnAtPoint(e.getPoint());
                    if (clickedIndex == index) {
                        frameDisplayGraph();
                        viewGragh("单击“查看图形”");
                        frameMoveCenter();
                        logger.trace("点击按钮：查看拟合图形，" + tblFit.getValueAt(nowRow, 1));
                    } else {
                        frameHideGraph();
                        frameMoveCenter();
                        panelGragh.removeAll();


                    }
                }   // END : if (e.getClickCount() == 1)

                //双击
                else if (e.getClickCount() == 2) {
                    switch (selectMethod) {
                        case 1:
                            if (!checkLinear) {
                                JOptionPane.showMessageDialog(null, "拟合参数的绝对值小于0.0001，请尝试其它拟合方法。。");
                            } else if (!checkLinearR2) {
                                JOptionPane.showMessageDialog(null, "相关系数R2过低，请尝试其它拟合方法。");
                            } else {
                                returnParent("双击单元格某行");
                                PanelViewFit.super.dispose();
                            }
                            break;
                        case 2:
                            if (!check4PL) {
                                JOptionPane.showMessageDialog(null, "拟合参数的绝对值小于0.0001，请尝试其它拟合方法。。");
                            } else if (!check4PLR2) {
                                JOptionPane.showMessageDialog(null, "相关系数R2过低，请尝试其它拟合方法。");
                            } else {
                                returnParent("双击单元格某行");
                                PanelViewFit.super.dispose();
                            }
                            break;
                        case 3:
                            if (!check5PL) {
                                JOptionPane.showMessageDialog(null, "拟合参数的绝对值小于0.0001，请尝试其它拟合方法。。");
                            } else if (!check5PLR2) {
                                JOptionPane.showMessageDialog(null, "相关系数R2过低，请尝试其它拟合方法。");
                            } else {
                                returnParent("双击单元格某行");
                                PanelViewFit.super.dispose();
                            }
                            break;
                        default:

                    }   // END : switch (selectMethod)
                }   // END : else if (e.getClickCount() == 2)
            }   // END : if (e.getButton() == MouseEvent.BUTTON1)
        }   // END : public void mouseClicked()
    }   // END : class tableMouseAdapter
}




