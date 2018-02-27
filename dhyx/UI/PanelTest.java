package dhyx.UI;

import com.mindfusion.charting.swing.LineChart;
import dhyx.Class.Const;
import dhyx.Class.MyIconButton;
import dhyx.Class.MyTable;
import dhyx.MainApp;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class PanelTest extends JPanel {
    private Logger logger = LogManager.getLogger();
    private ArrayList<Object> array;
    private LineChart lineChart;
    private PanelQuery panelQuery;
    private JLabel lblExperiment, lblCurve, lblTest, lblTestOriginal;
    private MyIconButton btnQuery, btnDel, btnExcel, btnTest;
    private MyTable tblExperiment, tblCurve, tblTest, tblTestOriginal;
    private DefaultTableModel dmExperiment, dmCurve, dmTest, dmTestOriginal;
    private String sqlSelectExp = "SELECT DISTINCT 项目名称,实验ID,实验名称,实验创建日期  FROM view_pro_exp ";
    private String sqlSelectCurve = "SELECT DISTINCT 曲线序号, 曲线ID FROM view_exp_curve_test ";
    private String sqlSelectTest = "SELECT DISTINCT 浓度序号,X1,X2,TC值,实验ID,曲线ID,曲线分录ID,测试ID,测试时间  FROM view_exp_curve_test ";
    private String ExperimentID,curveID;

    public PanelTest() {
        initSelf();
        initQueryPanel();
        initButton();
        initLabel();
        initTable();
        initLineChart();
        initOther();

        btnQueryClicked();

    }

    //初始化面板基本属性
    private void initSelf() {
        this.setLayout(null);
        this.setBackground(Color.white);
        this.setBounds(Const.LEFT_MARGIN, Const.TITLE_HEIGHT, Const.PANEL_WIDHT, Const.PANEL_HEIGHT);
    }   // END :  private void initSelf()

    //初始化查询面板
    private void initQueryPanel() {
        panelQuery = new PanelQuery();

        panelQuery.txtQuery.setSize(250, Const.BUTTON_HEIGHT);
        panelQuery.txtQuery.setToolTipText("请输入实验名称。支持模糊查询，不区分大小写");
        panelQuery.txtQuery.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                //按回车键执行相应操作;
                if(e.getKeyChar()==KeyEvent.VK_ENTER )
                {
                    btnQueryClicked();
                }
            }
        });

        btnQuery = panelQuery.btnQuery;
        btnQuery.setBounds(360, 0, Const.BUTTON_WIDTH, Const.BUTTON_HEIGHT);
        this.add(panelQuery);

    }   // END :  private void initQueryPanel()


    private void initButton() {
        //查询按钮
        btnQuery.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //组件状态可用、并且左键点击，才可以执行代码
                if ((btnQuery.isEnabled()) && (e.getButton() == MouseEvent.BUTTON1)) {
                    logger.trace("点击按钮：测试管理-查询" + panelQuery.txtQuery.getText());
                    btnQueryClicked();
                }
            }
        });


        //删除选中记录按钮
        btnDel = new MyIconButton(Const.ICON_DEL_TEST, Const.ICON_DEL_TEST_ENABLED, Const.ICON_DEL_TEST_DISABLED);
        btnDel.setLocation(btnQuery.getX() + btnQuery.getWidth() + 53, 0);
        btnDel.setEnabled(false);
        btnDel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if ((btnDel.isEnabled()) && (e.getButton() == MouseEvent.BUTTON1)) {
                    logger.trace("点击按钮：实验管理-删除选中记录");
                    btnDelClicked();
                }
            }
        });
        panelQuery.add(btnDel);


        //导出Excel按钮
        btnExcel = new MyIconButton(Const.ICON_EXCEL, Const.ICON_EXCEL_ENABLED, Const.ICON_EXCEL_DISABLED);
        btnExcel.setLocation(btnDel.getX() + btnDel.getWidth() + 53, 0);
        ;
        btnExcel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if ((btnExcel.isEnabled()) && (e.getButton() == MouseEvent.BUTTON1)) {
                    logger.trace("点击按钮：测试管理-导出Excel");
                    btnExcelClicked();
                }
            }
        });
        panelQuery.add(btnExcel);

        //开始测试按钮
        btnTest = new MyIconButton(Const.ICON_TEST, Const.ICON_TEST_ENABLED, Const.ICON_TEST_DISABLED);
        btnTest.setLocation(btnExcel.getX() + btnExcel.getWidth() + 53, 0);
        btnTest.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if ((btnTest.isEnabled()) && (e.getButton() == MouseEvent.BUTTON1)) {
                    logger.trace("点击按钮：测试管理-开始测试");
                    btnStartTest();
                }
            }
        });
        panelQuery.add(btnTest);


    }   // END : private void initButton()


    //初始化4个标签
    private void initLabel() {
        int standardY = panelQuery.getY() + panelQuery.getHeight();
        lblExperiment = new JLabel("实验列表");
        lblExperiment.setBounds(0, standardY, 345, 25);

        lblCurve = new JLabel("曲线列表");
        lblCurve.setBounds(btnQuery.getX(), standardY, 120, 25);

        lblTest = new JLabel("测试记录");
        lblTest.setBounds(btnDel.getX(), standardY, 360, 25);

        lblTestOriginal = new JLabel("原始数据");
        lblTestOriginal.setBounds(0, 325, 325, 25);

        this.add(lblExperiment);
        this.add(lblCurve);
        this.add(lblTest);
        this.add(lblTestOriginal);
    }   // END : private void initLabel()


    //初始化4个表格
    private void initTable() {
        initTableGetModel();
        initTableCreate();
        initTableAddListener();
    }  // END:initTalbe()


    // 1. 创建TableModel
    private void initTableGetModel() {
        dmExperiment = MainApp.myDB.getTableModel(sqlSelectExp + " WHERE 0=1");
        dmCurve = MainApp.myDB.getTableModel(sqlSelectCurve + " WHERE 0=1");
        dmTest = MainApp.myDB.getTableModelWithCheckbox(sqlSelectTest + " WHERE 0=1");
//        dmTestOriginal = MainApp.myDB.getTableModel(sqlSelectTest + " LIMIT 50");
    }


    // 2.初始化表格
    private void initTableCreate() {
        // 2.1 创建表格
        tblExperiment = new MyTable(dmExperiment);
        tblCurve = new MyTable(dmCurve) ;
        tblTestOriginal = new MyTable(dmTest) ;
        tblTest = new MyTable(dmTest) {
            @Override  //表格不可编辑
            public boolean isCellEditable(int row, int column) {
                return column <= 0;
            }

            @Override   // 表格带有单选框
            public Class getColumnClass(int c) {
                Object value = getValueAt(0, c);
                if (value != null) {
                    return value.getClass();
                } else {
                    return super.getClass();
                }
            }
        };
        tblTest.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        // 2.2 设置颜色、尺寸等基本属性
        int standardY = lblExperiment.getY() + lblExperiment.getHeight();
        tblExperiment.setBounds(lblExperiment.getX(), standardY, 325, 250);
        tblCurve.setBounds(lblCurve.getX(), standardY, 120, 250);
        tblTest.setBounds(lblTest.getX(), standardY, 360, 250);
        tblTestOriginal.setBounds(lblTestOriginal.getX(), lblTestOriginal.getY() + lblTestOriginal.getHeight(), 325, 275);

        // 2.3 表格列宽
        tblExperiment.setWidth(70, 65, 90, 85 - 2);
        tblCurve.setWidth(60,60);
        tblTest.setWidth(30, 55, 70, 70, 70, 70, 70, 70, 70, 70);
        tblTestOriginal.setWidth(100,100);

        // 2.4 设置滚动面板
        tblExperiment.jScrollPane.setBounds(tblExperiment.getBounds());
        tblCurve.jScrollPane.setBounds(tblCurve.getBounds());
        tblCurve.jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        tblTest.jScrollPane.setBounds(tblTest.getBounds());
        tblTestOriginal.jScrollPane.setBounds(tblTestOriginal.getBounds());
        this.add(tblExperiment.jScrollPane);
        this.add(tblCurve.jScrollPane);
        this.add(tblTest.jScrollPane);
        this.add(tblTestOriginal.jScrollPane);
    }


    // 添加监听
    private void initTableAddListener() {
        tblExperiment.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                tblExperimentClicked();
            }
        });

//        tblCurve.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseReleased(MouseEvent e) {
//                tblCurveClicked();
//            }
//        });

        tblCurve.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                tblCurveClicked();
            }
        });



        tblTest.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                tblTestClicked();
            }
        });

    }   // END : private void initTableAddListener()


    private void initLineChart() {
        lineChart = new LineChart();
        lineChart.setBounds(tblTest.getX(), tblTestOriginal.getY(), 640, 275);
        lineChart.setBackground(Color.white);
        this.add(lineChart);
    }


    private void initOther() {

    }

    private void btnQueryClicked() {
        btnQuery.setEnabled(false);
        //处理SQL，避免注入。例如   C%' or 1=1 or 项目ID like '%C
        String sql,strQueryKey;
        strQueryKey = panelQuery.txtQuery.getText().trim();
        strQueryKey = StringUtils.replace(strQueryKey, "'", "");
        strQueryKey = StringUtils.replace(strQueryKey, "%", "");
        sql = sqlSelectExp + " WHERE `项目名称` LIKE '%" + strQueryKey + "%' or `实验名称` LIKE '%" + strQueryKey + "%'";

        dmExperiment = MainApp.myDB.getTableModel(sql);
        tblExperiment.setModel(dmExperiment);
        //排序model要同时改变，不然会报错
        tblExperiment.setRowSorter(new TableRowSorter<>(dmExperiment));

        if (tblExperiment.getRowCount() > 0) {
            tblExperiment.setRowSelectionInterval(0, 0);
            tblExperimentClicked();
        }

        //后续处理
        panelQuery.txtQuery.requestFocus();
        btnQuery.setEnabled(true);
    }


    private void btnDelClicked() {

    }


    private void btnExcelClicked() {

    }


    private void btnStartTest() {

    }


    private void tblExperimentClicked() {
        btnDel.setEnabled(false);
        int nowRow = tblExperiment.getSelectedRow();
        String sql;
        if (nowRow != -1) {
            ExperimentID = tblExperiment.getValueAt(nowRow, 1).toString();
            sql = sqlSelectCurve + "WHERE `实验ID` = '" + ExperimentID + "' ORDER BY `曲线ID`";
        } else {
            sql = sqlSelectCurve + "WHERE 0=1";
        }

        dmCurve = MainApp.myDB.getTableModel(sql);
        tblCurve.setModel(dmCurve);
        tblCurve.setRowSorter(new TableRowSorter<>(dmCurve));
        if (tblCurve.getRowCount() > 0) {
            tblCurve.setRowSelectionInterval(0, 0);
        }

    }


    private void tblCurveClicked() {
        btnDel.setEnabled(false);
        int nowRow = tblCurve.getSelectedRow();

        if (nowRow != -1) {
            curveID = tblCurve.getValueAt(nowRow, 1).toString();
        } else {
            curveID = String.valueOf(-1);
        }
        String sql = sqlSelectTest + "WHERE `曲线ID` = '" + curveID + "' ORDER BY `测试ID`";
        dmTest = MainApp.myDB.getTableModelWithCheckbox(sql);
        tblTest.setModel(dmTest);
        tblTest.setRowSorter(new TableRowSorter<>(dmTest));
        if (tblTest.getRowCount() > 0) {
            tblTest.setRowSelectionInterval(0, 0);
        }
    }




    private void tblTestClicked() {
        int[] nowRow = tblTest.getSelectedRows();
        for (int i = 0; i < tblTest.getRowCount(); i++) {
            if (ArrayUtils.contains(nowRow, i)) {
                tblTest.setValueAt(true, i, 0);
            } else {
                tblTest.setValueAt(false, i, 0);
            }
        }
    }

    private void tblTestHeadClicked() {

    }


}