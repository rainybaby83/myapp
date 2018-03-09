package com.dhyx.panel;


import com.dhyx.myClass.Const;
import com.dhyx.myClass.MyIconButton;
import com.dhyx.myClass.MyTable;
import com.dhyx.MainApp;
import com.mindfusion.charting.*;
import com.mindfusion.charting.swing.Dashboard;
import com.mindfusion.charting.swing.LayoutBuilder;
import com.mindfusion.drawing.DashStyle;
import com.mindfusion.drawing.SolidBrush;
import com.mindfusion.charting.Series;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

public class PanelTest extends JPanel {
    private Logger logger = LogManager.getLogger();
    private PanelQuery panelQuery;
    private JLabel lblExperiment, lblCurve, lblTest, lblTestData;
    private MyIconButton btnQuery, btnDel, btnTest;
    private MyTable tblExperiment, tblCurve, tblTest, tblTestData;
    private DefaultTableModel dmExperiment, dmCurve, dmTest, dmTestData;
    private String sqlSelectExp = "SELECT DISTINCT 项目名称,实验ID,实验名称,实验创建日期  FROM view_pro_exp ";
    private String sqlSelectCurve = "SELECT DISTINCT 曲线序号,曲线ID FROM view_project_exp_curve ";
    private String sqlSelectTest = "SELECT DISTINCT 浓度序号,X1,X2,TC值,实验ID,曲线ID,曲线分录ID,测试ID,测试时间  FROM view_exp_curve_test ";
    private String experimentID, curveID;


    private LineRenderer lineRenderer;
    private List<Series> seriesList;
    private ObservableList<Series> ols;
    private Series2D defaultSeries2D;


    public PanelTest() {
        initSelf();
        initQueryPanel();
        initButton();
        initLabel();
        initTable();
        initChart();
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
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
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

        //开始测试按钮
        btnTest = new MyIconButton(Const.ICON_TEST, Const.ICON_TEST_ENABLED, Const.ICON_TEST_DISABLED);
        btnTest.setLocation(btnDel.getX() + btnDel.getWidth() + 53, 0);
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
        lblTest.setBounds(lblCurve.getX() + lblCurve.getWidth() + 30, standardY, 360, 25);

        lblTestData = new JLabel("原始数据");
        lblTestData.setBounds(0, 325, 325, 25);

        this.add(lblExperiment);
        this.add(lblCurve);
        this.add(lblTest);
        this.add(lblTestData);
    }   // END : private void initLabel()


    //初始化图表
    private void initChart() {
        Dashboard dashboard = new Dashboard();
        LayoutBuilder layoutBuilder = new LayoutBuilder(dashboard);

        Axis xAxis = new Axis();
        xAxis.setMaxValue(300.0);
        xAxis.setMinValue(0.0);
        xAxis.setInterval(50);
        xAxis.setTitle("");
        Axis yAxis = new Axis();
        yAxis.setMinValue(0.0);
        yAxis.setMaxValue(12000.0);
        yAxis.setInterval(2000);
        yAxis.setTitle("");

        //网格
        Plot2D gridArea = new Plot2D();
        //网格区域背景色，None时才显示
        gridArea.setBackground(new SolidBrush(Color.white));
        //网格区域类型
        gridArea.setGridType(GridType.Crossed);
        //网格区域背景色
        gridArea.setGridColor1(Color.white);
        gridArea.setGridColor2(Color.white);
        //网格线类型，实线虚线
        gridArea.setGridLineStyle(DashStyle.Dash);
        //网格线颜色
        gridArea.setGridLineColor(new Color(222, 222, 222));
        //网格线对应的坐标轴
        gridArea.setXAxis(xAxis);
        gridArea.setYAxis(yAxis);


        //折线样式
        UniformSeriesStyle seriesStyle = new UniformSeriesStyle();
        seriesStyle.setUniformStroke(new SolidBrush(Color.blue));

        //默认数据系列，数值为(0,0)
        defaultSeries2D = new Series2D(
                Collections.singletonList(0D),
                Collections.singletonList(0D),
                Collections.singletonList(""));

        //可以拖动的坐标轴。去掉这四行，则不能拖动
        seriesList = Collections.singletonList(defaultSeries2D);
        ols = FXCollections.observableList(seriesList);
        lineRenderer = new LineRenderer(ols);
        lineRenderer.setSeriesStyle(seriesStyle);
        gridArea.getSeriesRenderers().add(lineRenderer);

        XAxisRenderer gridAreaXRenderer = new XAxisRenderer(xAxis);
        YAxisRenderer gridAreaYRenderer = new YAxisRenderer(yAxis);
        layoutBuilder.createAndAddPlotWithBottomAndLeftAxes(gridArea, gridAreaXRenderer, gridAreaYRenderer);

        dashboard.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.blue));
        dashboard.setBounds(tblCurve.getX(), tblTestData.getY(), 640, 275);
        this.add(dashboard);
    }


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
        dmTestData = MainApp.myDB.getTableModelForTestData("1");
    }


    // 2.初始化表格
    private void initTableCreate() {
        // 2.1 创建表格
        tblExperiment = new MyTable(dmExperiment);
        tblCurve = new MyTable(dmCurve);
        tblTestData = new MyTable(dmTestData);
        tblTestData.setEnabled(false);

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
        tblTestData.setBounds(lblTestData.getX(), lblTestData.getY() + lblTestData.getHeight(), 325, 275);

        // 2.3 表格列宽
        tblExperiment.setWidth(70, 65, 90, 85 - 2);
        tblCurve.setWidth(60, 60);
        tblTest.setWidth(30, 55, 70, 70, 70, 70, 70, 70, 70, 70);
        tblTestData.setAutoCreateColumnsFromModel(true);

        // 2.4 设置滚动面板
        tblExperiment.jScrollPane.setBounds(tblExperiment.getBounds());
        tblCurve.jScrollPane.setBounds(tblCurve.getBounds());
        tblCurve.jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        tblTest.jScrollPane.setBounds(tblTest.getBounds());
        tblTestData.jScrollPane.setBounds(tblTestData.getBounds());
        this.add(tblExperiment.jScrollPane);
        this.add(tblCurve.jScrollPane);
        this.add(tblTest.jScrollPane);
        this.add(tblTestData.jScrollPane);
    }


    // 添加监听
    private void initTableAddListener() {

        // 在实验列表释放鼠标，触发实验列表单击事件
        tblExperiment.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    tblExperimentClicked("单击tblExperiment调用tblExperimentClicked");
                }
            }
        });

        //单击监听
        tblCurve.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                tblCurveClicked("单击tblCurve调用tblCurveClicked");
            }
        });

        // 测试列表单击事件，实际调用鼠标释放事件
        tblTest.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    tblTestClicked("单击tblTest调用tblTestClicked");
                }
            }
        });

        //点击表头全选的单击事件
        tblTest.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int columnIndex = tblTest.getTableHeader().columnAtPoint(e.getPoint());
                if (columnIndex == 0) {
                    selectAll(tblTest);
                    updateTestData("表头全选调用");
                    updateChart("表头全选调用");
                }
            }
        });

    }   // END : private void initTableAddListener()


    private void initOther() {

    }


    /**
     * 单击查询按钮时执行的代码
     */
    private void btnQueryClicked() {
        btnQuery.setEnabled(false);
        //处理SQL，避免注入。例如   C%' or 1=1 or 项目ID like '%C
        String sql, strQueryKey;
        strQueryKey = panelQuery.txtQuery.getText().trim();
        strQueryKey = StringUtils.replace(strQueryKey, "'", "");
        strQueryKey = StringUtils.replace(strQueryKey, "%", "");
        sql = sqlSelectExp + " WHERE `实验名称` LIKE '%" + strQueryKey + "%'";

        dmExperiment = MainApp.myDB.getTableModel(sql);
        tblExperiment.setModel(dmExperiment);

        if (tblExperiment.getRowCount() > 0) {
            tblExperiment.setRowSelectionInterval(0, 0);
        }
        //调用表格点击事件，刷新右侧数据
        tblExperimentClicked("查询按钮调用");
        //后续处理
        panelQuery.txtQuery.requestFocus();
        btnQuery.setEnabled(true);
    }


    private void btnDelClicked() {

    }


    private void btnStartTest() {

    }


    // 点击某行实验数据，右侧展示对应的曲线列表数据。如果没有曲线数据，则清空
    private void tblExperimentClicked(String msg) {
        btnDel.setEnabled(false);
        if (tblExperiment.getRowCount() > 0) {
            int nowRow = tblExperiment.getSelectedRow();
            if (nowRow != -1) {
                experimentID = tblExperiment.getValueAt(nowRow, 1).toString();
                String sql = sqlSelectCurve + "WHERE `实验ID` = '" + experimentID + "' ORDER BY `曲线ID`";

                dmCurve = MainApp.myDB.getTableModel(sql);
                tblCurve.setModel(dmCurve);
                if (tblCurve.getRowCount() > 0) {
                    tblCurve.setRowSelectionInterval(0, 0);
                }
            }
        } else {
            // 查不到实验数据时，清空曲线列表
            dmCurve = MainApp.myDB.getTableModel(sqlSelectCurve + " WHERE 0=1");
            tblCurve.setModel(dmCurve);
        }
        tblCurveClicked("单击实验表格，方法内调用");
    }   // END : private void tblExperimentClicked()


    // 点击曲线某行数据，则在右侧测试数据列表展示对应数据
    private void tblCurveClicked(String msg) {
        btnDel.setEnabled(false);
        if (tblExperiment.getRowCount() > 0) {
            int nowRow = tblCurve.getSelectedRow();
            if (nowRow != -1) {
                int index = ((DefaultTableModel) tblCurve.getModel()).findColumn("曲线ID");
                curveID = tblCurve.getValueAt(nowRow, index).toString();
            } else {
                // 单击实验列表时，调用本方法，如果此时曲线列表没有数据（即getSelectedRow（）=-1），则另curveID=-1，使测试表格被清空
                curveID = String.valueOf(-1);
            }
            String sql = sqlSelectTest + "WHERE `曲线ID` = '" + curveID + "' ORDER BY `测试ID`";
            dmTest = MainApp.myDB.getTableModelWithCheckbox(sql);
            tblTest.setModel(dmTest);
        } else {
            // 查不到实验数据时，测试列表
            dmTest = MainApp.myDB.getTableModel(sqlSelectTest + " WHERE 0=1");
            tblTest.setModel(dmTest);
        }
        //全选测试列表。如果没有数据，不影响
        selectAll(tblTest);
        updateTestData("曲线列表点击调用");
        updateChart("曲线列表点击调用");
    }


    // JTable多选时，复选框打钩的功能
    private void tblTestClicked(String msg) {
        //获取所有被选中的行
        int[] nowRow = tblTest.getSelectedRows();
        for (int i = 0; i < tblTest.getRowCount(); i++) {
            if (ArrayUtils.contains(nowRow, i)) {
                tblTest.setValueAt(true, i, 0);
            } else {
                tblTest.setValueAt(false, i, 0);
            }
        }
        updateTestData(msg + " + \ntblTestClicked内部调用updateTestData");
        updateChart(msg + "+ \ntblTestClicked内部调用updateChart");
    }


    private void tblTestDataRightClicked(String msg) {


    }


    //表格全选，并打钩
    private void selectAll(MyTable myTable) {
        int rowCount = myTable.getRowCount();
        if (rowCount > 0) {
            myTable.setRowSelectionInterval(0, rowCount - 1);
            for (int i = 0; i < myTable.getRowCount(); i++) {
                myTable.setValueAt(true, i, 0);
            }
            btnDel.setEnabled(true);
        } else {
            btnDel.setEnabled(false);
        }
    }


    //更新原始测试数据表格的功能
    private void updateTestData(String msg) {

        // 取得tblTest选中行的索引
        // 取得tblTest选中行的testID
        // 更新sql
        // 更新dm
        if (tblTest.getRowCount() > 0) {
            StringBuffer str = new StringBuffer();

            // 取得tblTest选中行的索引
            int[] nowRow = tblTest.getSelectedRows();
            String[] testID = new String[nowRow.length];

            // 取得tblTest选中行的testID
            int index = ((DefaultTableModel) tblTest.getModel()).findColumn("测试ID");
            for (int i = 0; i < nowRow.length; i++) {
                testID[i] = tblTest.getValueAt(nowRow[i], index).toString();
                str = str.append(testID[i]).append(",");
            }
            str.deleteCharAt(str.length() - 1);

            // 更新dm
            dmTestData = (DefaultTableModel) tblTestData.getModel();
            dmTestData = MainApp.myDB.getTableModelForTestData(testID);
            tblTestData.setModel(dmTestData);
        } else {
            dmTestData = MainApp.myDB.getTableModelForTestData("0");
            tblTestData.setModel(dmTestData);
        }

    }

    private void updateChart(String msg) {
        int rowCount = tblTestData.getRowCount();
        int columnCount = tblTestData.getColumnCount();
//        double xMax=1,yMax=1;

        if (rowCount > 0) {
            Series2D[] series2D = new Series2D[rowCount];
            List<Double> xList = new ArrayList<>();

            //每个series都要有x数据和y轴数据。由于x坐标相同，所以构建一个通用的x数据：xList
            for (int column = 1; column < columnCount; column++) {
                //第0列是TestID，所以从1开始
                xList.add(NumberUtils.toDouble(tblTestData.getColumnName(column)));
            }
//            xMax = NumberUtils.toDouble(tblTestData.getColumnName(columnCount - 1));

            //循环构建series2D数组
            for (int row = 0; row < rowCount; row++) {
                //构建每个series2D的yList
                List<Double> yList = new ArrayList<>();
                for (int column = 1; column < columnCount; column++) {
                    yList.add(NumberUtils.toDouble(tblTestData.getValueAt(row, column).toString()));
                }

                //用xList和yList构建series2D数组
                series2D[row] = new Series2D(xList, yList, null);

                //计算y轴最大值
//                Double tmp = Collections.max(yList);
//                if (tmp > yMax) { yMax = tmp; }
            }
            //全部循环完成后，将series2D构建为seriesList
            seriesList = Arrays.asList(series2D);

        } else {
            //没有任何测试数据时，用默认的点(0,0)构建seriesList
            seriesList = Collections.singletonList(defaultSeries2D);
        }

        ols = FXCollections.observableList(seriesList);
        lineRenderer.setSeries(ols);
        lineRenderer.dataChanged();

    }


}