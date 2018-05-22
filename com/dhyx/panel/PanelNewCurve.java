package com.dhyx.panel;

import com.dhyx.MainApp;
import com.dhyx.dbclass.MyDatabase;
import com.dhyx.myclass.*;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Date;


public class PanelNewCurve extends JPanel {
    JDesktopPane jd = new JDesktopPane();
    private Logger logger = LogManager.getLogger();
    private MyDatabase db = MainApp.myDB;
    private PanelQuery panelQuery;
    private MyIconButton btnQuery, btnDel, btnFit;
    private DefaultTableModel dmCurve,dmConcentration;
    private String sqlSelectCurve = "SELECT DISTINCT 项目名称,实验名称,曲线序号,曲线日期,曲线ID,实验ID,项目ID FROM view_project_exp_curve";
    private String sqlSelectConcentration = "SELECT DISTINCT 浓度序号,浓度值,反应值,浓度ID FROM view_curve_concentration";
    private String curveID;
    private MyTable tblCurve,tblConcentration;
    private MyComboBox lstXType,lstYType ;
    private JLabel[] lRight = new JLabel[6];


    public PanelNewCurve() {
        initSelf();
        initQueryPanel();
        initButton();
        initLabel();
        initTableGetModel();
        initTableCreate();
        initTableAddListener();
        initList();
        initOther();
        click_btnQuery("启动窗体调用");
        click_tblCurve("启动窗体调用");
    }



    //初始化面板基本属性
    private void initSelf() {
        this.setLayout(null);
        this.setBackground(Color.white);
        this.setBounds(Const.LEFT_MARGIN, Const.TITLE_HEIGHT, Const.PANEL_WIDHT, Const.PANEL_HEIGHT);
    }


    //初始化查询面板
    private void initQueryPanel() {
        panelQuery = new PanelQuery();
        panelQuery.lblQuery.setText("实验关键字");
        panelQuery.txtQuery.setSize(250, Const.BUTTON_HEIGHT);
        panelQuery.txtQuery.setToolTipText("请输入实验名称.");
        panelQuery.txtQuery.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                //按回车键执行相应操作;
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    click_btnQuery("文本框回车调用");
                    click_tblCurve("文本框回车调用");
                }
            }
        });

        btnQuery = panelQuery.btnQuery;
        btnQuery.setBounds(435, 0, Const.BUTTON_WIDTH, Const.BUTTON_HEIGHT);
        this.add(panelQuery);
    }   // END :  private void initQueryPanel()


    //初始化按钮
    private void initButton() {
        //查询按钮
        btnQuery.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //组件状态可用、并且左键点击，才可以执行代码
                if ((btnQuery.isEnabled()) && (e.getButton() == MouseEvent.BUTTON1)) {
                    logger.trace("点击按钮：生成曲线-查询" + panelQuery.txtQuery.getText());
                    click_btnQuery("监听：鼠标单击查询按钮");
                    click_tblCurve("监听：鼠标单击查询按钮");
                }
            }
        });

        //"删除选中曲线"按钮
        btnDel = new MyIconButton(Const.ICON_CURVE_DEL, Const.ICON_CURVE_DEL_ENABLED, Const.ICON_CURVE_DEL_DISABLED);
        btnDel.setBounds(625, 0, Const.BUTTON_WIDTH, Const.BUTTON_HEIGHT);
        btnDel.setEnabled(false);
        btnDel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if ((btnDel.isEnabled()) && (e.getButton() == MouseEvent.BUTTON1)) {
                    boolean status = click_btnDel();
                    logger.trace("点击按钮：生成曲线-删除选中曲线。曲线ID=" + curveID + "，结果=" + status);
                    click_btnQuery("点击删除按钮后调用");
                }
            }
        });
        panelQuery.add(btnDel);

        //"查看所有拟合"按钮
        btnFit = new MyIconButton(Const.ICON_FIT, Const.ICON_FIT_ENABLED, Const.ICON_FIT_DISABLED);
        btnFit.setBounds(845, 170, Const.BUTTON_WIDTH, Const.BUTTON_HEIGHT);
        btnFit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if ((btnFit.isEnabled()) && (e.getButton() == MouseEvent.BUTTON1)) {
                    logger.trace("点击按钮：生成曲线-查看所有拟合");
                    click_btnFit();
                }
            }
        });
        this.add(btnFit);
    }   // END : private void initButton()
    

    //初始化标签
    private void initLabel() {
        JLabel lblCurve = new JLabel("曲线列表");
        JLabel lblConcentration = new JLabel("所选曲线的浓度(已求平均)");
        JLabel lblFitType = new JLabel("拟合方式");
        JLabel lblFitTitle = new JLabel("选中的拟合结果");
        JLabel lblXType = new JLabel("X处理");
        JLabel lblYType = new JLabel("Y处理");
        JLabel lblFitResult = new JLabel("方法：");

        lblCurve.setBounds(0, 40, 385, 25);
        lblConcentration.setBounds(435, 40, 310, 25);
        lblFitType.setBounds(800, 40, 200, 25);
        lblFitTitle.setBounds(800, 250, 200, 25);
        lblXType.setBounds(815, 85, 45, 25);
        lblYType.setBounds(815, 120, 45, 25);
        lblFitResult.setBounds(820, 295, 160, 25);

        this.add(lblCurve);
        this.add(lblConcentration);
        this.add(lblFitType);
        this.add(lblFitTitle);
        this.add(lblXType);
        this.add(lblYType);
        this.add(lblFitResult);


        JLabel[] lLeft = new JLabel[6];
        String[] title = {"R =", "a =", "b =", "c =", "d =", "e ="};
        for (int i = 0; i < 6; i++) {
            lLeft[i] = new JLabel();
            lLeft[i].setText(title[i]);
            lLeft[i].setFont(new Font("宋体", Font.PLAIN, 14));
            lLeft[i].setBounds(820, 430 + i * 25, 45, 25);
            lLeft[i].setHorizontalAlignment(JLabel.CENTER);
            lLeft[i].setVerticalAlignment(JLabel.BOTTOM);
            this.add(lLeft[i]);

            lRight[i] = new JLabel();
            lRight[i].setText("");
            lRight[i].setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.gray));
            lRight[i].setBounds(880, 430 + i * 25, 85, 25);
            lRight[i].setHorizontalAlignment(JLabel.CENTER);
            this.add(lRight[i]);
        }

    }


    // 1. 创建TableModel
    private void initTableGetModel() {
        dmCurve = TableMethod.getTableModel(sqlSelectCurve + " WHERE 0=1");
        dmConcentration = TableMethod.getDMwithCheck(sqlSelectConcentration + " WHERE 0=1");
    }


    // 2.创建表格并设置属性
    private void initTableCreate() {
        // 2.1 创建表格
        tblCurve = new MyTable(dmCurve);
        tblConcentration = new MyTable(dmConcentration){
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2;
            }

            @Override   // 表格带有单选框
            public Class getColumnClass(int c) {
                Object value = getValueAt(0, c);
                if (value != null) {
                    return value.getClass();
                } else{
                    return super.getClass();
                }
            }
        };
        tblConcentration.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        tblConcentration.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        // 2.2 设置尺寸
        tblCurve.setBounds(0, 65, 385, 550);
        tblConcentration.setBounds(435,65,315,550);

        // 2.3 表格列宽
        tblCurve.setWidth(70, 125, 60, 80, 50, 50, 50);
        tblConcentration.setWidth(30, 75, 105, 105);

        // 2.4 设置滚动面板
        tblCurve.jScrollPane.setBounds(tblCurve.getBounds());
        tblConcentration.jScrollPane.setBounds(tblConcentration.getBounds());
        tblConcentration.jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        //2.5 取得“浓度值”的index，然后对该列设置CellEditor，使其只能输入小数
        int index = dmConcentration.findColumn("浓度值");
        tblConcentration.getColumnModel().getColumn(index).setCellEditor(new FloatCellEditor());

        //2.6 添加弹出菜单
//        tblCurve.jPopupMenu.add(btnDel);

        // 2.7 添加表格到面板
        this.add(tblCurve.jScrollPane);
        this.add(tblConcentration.jScrollPane);
    }


    // 3.添加表格的监听
    private void initTableAddListener() {

        // 曲线列表的单击事件
        tblCurve.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    click_tblCurve("监听：鼠标单击曲线列表");
                }
            }
        });

        // 浓度列表的单击事件
        tblConcentration.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    click_tblConcentration("监听：鼠标单击浓度列表");
                }
            }
        });

        //对浓度表的列“浓度值”添加监听，当编辑结束时，把手写的浓度值写入数据库
        int index = dmConcentration.findColumn("浓度值");
        tblConcentration.getColumnModel().getColumn(index).getCellEditor().addCellEditorListener(new CellEditorListener() {
            @Override
            public void editingStopped(ChangeEvent e) {
                //此处退出编辑状态，如果数据符合要求，则写入数据库
                updateDB_StopEdit("监听：editingStopped");
            }
            @Override
            public void editingCanceled(ChangeEvent e) {
            }
        });


        //点击表头全选的单击事件
        tblConcentration.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int columnIndex = tblConcentration.getTableHeader().columnAtPoint(e.getPoint());
                if (columnIndex == 0) {
                    selectAll(tblConcentration);
                }
            }
        });
    }   // END : private void initTableAddListener()






    // 初始化列表框
    private void initList() {
        String[] value = {"1 不变换", "2 取e为底的对数", "3 取10为底的对数", "4 取2为底的对数"};
        String[] key = {"1", "2", "3", "4"};
        lstXType = new MyComboBox();
        lstYType = new MyComboBox();
        lstXType.setKeyValue(key, value);
        lstYType.setKeyValue(key, value);
        lstXType.setBounds(860, 85, 125, 25);
        lstYType.setBounds(860, 120, 125, 25);
        this.add(lstXType);
        this.add(lstYType);
    }


    private void initOther() {
        //两个方框
        JPanel jp1 = new JPanel();
        JPanel jp2 = new JPanel();
        jp1.setOpaque(false);
        jp2.setOpaque(false);
        jp1.setBounds(800, 65, 200, 160);
        jp2.setBounds(800, 275, 200, 340);
        jp1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.gray));
        jp2.setBorder(jp1.getBorder());
        this.add(jp1);
        this.add(jp2);

        //拟合公式图片

    }


    //查询按钮
    private void click_btnQuery(String msg) {
        btnQuery.setEnabled(false);
        //处理SQL，避免注入。例如   C%' or 1=1 or 项目ID like '%C
        String sql, strQueryKey;
        strQueryKey = panelQuery.txtQuery.getText().trim();
        strQueryKey = StringUtils.replace(strQueryKey, "'", "");
        strQueryKey = StringUtils.replace(strQueryKey, "%", "");
        sql = sqlSelectCurve + " WHERE `实验名称` LIKE '%" + strQueryKey + "%' AND `锁定`='N' ORDER BY `项目ID`,`实验ID`,`曲线序号`";
        dmCurve = TableMethod.getTableModel(sql);
        tblCurve.setModel(dmCurve);
        tblCurve.setFirstRow();

        //后续处理
        panelQuery.txtQuery.requestFocus();
        btnQuery.setEnabled(true);
    }


    /**
     * 删除曲线的代码
     * 取消自动提交
     * 逻辑删除曲线
     * 获取浓度ID集合，循环1
     *      获得1个浓度ID，逻辑删除该浓度
     *      根据该浓度查询所有testID集合，进入循环2
     *          获得1个testID，逻辑删除该test数据
     *          逻辑删除该testID对应的所有test_original
     *      结束循环2
     * 结束循环1
     * 提交
     */
    private boolean click_btnDel() {
        boolean status = false;
        String modifyDate = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");

        try {
            //取消自动提交
            db.conn.setAutoCommit(false);

            //逻辑删除曲线
            String sqlUpdateCurve = "UPDATE curve SET isDeleted = 'Y' WHERE curveID = ?";
            db.pstmtUpdateNotCommit(false, sqlUpdateCurve, curveID);

            //获取浓度ID集合，循环1
            String sqlSelectConcentration = "SELECT DISTINCT concentrationID FROM concentration WHERE curveID = " +
                    curveID + " AND isDeleted = 'N';";
            ResultSet rs1 = db.conn.createStatement().executeQuery(sqlSelectConcentration);

            while (rs1.next()) {
                // 获得1个浓度ID
                String delConcentrationID = rs1.getString("ConcentrationID");
                // 逻辑删除该浓度
                String sqlUpdateConcentration = "UPDATE concentration SET isDeleted = 'Y' WHERE concentrationID = ?";
                db.pstmtUpdateNotCommit(false, sqlUpdateConcentration, delConcentrationID);

                // 根据该浓度查询所有testID集合，进入循环2
                String sqlSelectTest = "SELECT DISTINCT testID FROM test WHERE concentrationID = " +
                        delConcentrationID + " AND isDeleted = 'N';";
                ResultSet rs2 = db.conn.createStatement().executeQuery(sqlSelectTest);
                while (rs2.next()) {

                    // 获得1个testID，逻辑删除该test数据。
                    String testID = rs2.getString("testID");
                    String sqlUpdateTest = "UPDATE test SET isDeleted = 'Y' ,modifyDate = ? WHERE testID = ?";

                    db.pstmtUpdateNotCommit(false, sqlUpdateTest, modifyDate, testID);

                    // 逻辑删除该testID对应的所有test_original
                    String sqlUpdateTestOriginal = "UPDATE test_original SET isDeleted = 'Y' , modifyDate = ? WHERE testID = ?";
                    db.pstmtUpdateNotCommit(false, sqlUpdateTestOriginal, modifyDate, testID);
                }   // END : rs2的循环
            }   // END : rs1的循环

            //提交

            db.conn.commit();
            status = true;
        } catch (SQLException e) {
            try {
                db.conn.rollback();
//                db.conn.commit();
            } catch (SQLException e1) {
                logger.error(e1.getClass().getSimpleName() + "，回滚失败。" + e.getMessage());
            }
            logger.error(e.getClass().getSimpleName() + "，" + e.getMessage());
            JOptionPane.showMessageDialog(null, "删除失败，数据库未作任何修改。请查看日志。");
        }
        return status;
    }


    //点击查看拟合按钮
    private void click_btnFit() {
        //获取被选中的行数，校验是否满足拟合数量要求
        int[] nowRows = tblConcentration.getSelectedRows();
        int minCount = 6;
        if (nowRows.length < minCount) {
            JOptionPane.showMessageDialog(null, "拟合至少需要" + minCount + "个数据，还需再选"
                    + String.valueOf(minCount - nowRows.length) + "个。", "缺少数据", JOptionPane.ERROR_MESSAGE);
        } else {
            //数据按浓度排序，写入数组
            DefaultTableModel dm = (DefaultTableModel) tblConcentration.getModel();
            int index1 = dm.findColumn("浓度值");
            int index2 = dm.findColumn("反应值");
            double[] concentrations = new double[nowRows.length];
            double[] values = new double[nowRows.length];
            int count0 = 0;
            for(int i =0;i<nowRows.length;i++) {
                concentrations[i] = NumberUtils.toDouble(tblConcentration.getValueAt(nowRows[i], index1).toString());
                if (concentrations[i] == 0) {
                    count0++;
                }
                values[i] = NumberUtils.toDouble(tblConcentration.getValueAt(nowRows[i], index2).toString());
            }

            // 校验浓度为0的个数，最多只有1个
            if (count0 > 1) {
                JOptionPane.showMessageDialog(null, "浓度值为0的数据最多只能有1个，当前有"
                        + count0 + "个，请检查。", "数据错误", JOptionPane.ERROR_MESSAGE);
            } else {
                //弹出窗口
                PanelFit panelFit = new PanelFit(concentrations,values);
                panelFit.setVisible(true);
            }



        }
    }



    //表格全选，并打钩
    private void selectAll(MyTable myTable) {
        int rowCount = myTable.getRowCount();
        if (rowCount > 0) {
            myTable.setRowSelectionInterval(0, rowCount - 1);
            for (int i = 0; i < myTable.getRowCount(); i++) {
                myTable.setValueAt(true, i, 0);
            }
        }
    }


    // 用户查看所有拟合后，选定一个拟合，然后新面板调用本面板的receiveFitData()，接收数据。
    public void receiveFitData() {

    }


    //点击曲线列表时的操作
    private void click_tblCurve(String msg) {
        //设置DM默认值，如果符合if条件，则设置为正确的
        dmConcentration = TableMethod.getTableModel(sqlSelectConcentration + " WHERE 0=1");
        btnDel.setEnabled(false);
        btnFit.setEnabled(false);

        if (tblCurve.getRowCount() > 0) {
            int nowRow = tblCurve.getSelectedRow();
            if (nowRow > -1) {
                DefaultTableModel currentDM = (DefaultTableModel) tblCurve.getModel();
                curveID = tblCurve.getValueAt(nowRow, currentDM.findColumn("曲线ID")).toString();
                String sql = sqlSelectConcentration + " WHERE `曲线ID` = '" + curveID + "' AND `锁定`= 'N' ORDER BY `浓度序号`";
                dmConcentration = TableMethod.getDMwithCheck(sql);
                btnDel.setEnabled(true);
                btnFit.setEnabled(true);
            }
        }

        tblConcentration.setModel(dmConcentration);
    }



    private void click_tblConcentration(String msg) {
        //获取所有被选中的行
        int[] nowRows = tblConcentration.getSelectedRows();

        //多选时，复选框打钩的功能
        for (int i = 0; i < tblConcentration.getRowCount(); i++) {
            if (ArrayUtils.contains(nowRows, i)) {
                tblConcentration.setValueAt(true, i, 0);
            } else {
                tblConcentration.setValueAt(false, i, 0);
            }
        }

        tblConcentration.getCellRenderer(1, 2).getTableCellRendererComponent
                (tblConcentration, "123", true, true, 1, 3)
                .addFocusListener(new FocusAdapter() {
                    @Override
                    public void focusLost(FocusEvent e) {
                        JOptionPane.showMessageDialog(null,1);
                    }
                });
    }


    /**
     * 内部类，仅在生成曲线模块，输入浓度时使用。
     * 用于限制JTable的某个单元格只能输入小数。用NumberUtils进行转化，如果不能转化为小数则设置为0
     */
    class FloatCellEditor extends DefaultCellEditor {
        FloatCellEditor() {
            super(new JTextField());
        }

        @Override
        public Object getCellEditorValue() {
            String value = (String) delegate.getCellEditorValue();
            String pattern = "0";
            float tmp = NumberUtils.toFloat(value);
            if (tmp != 0) {
                pattern = "0.0000";
            }
            DecimalFormat df = new DecimalFormat(pattern);
            return df.format(tmp);
        }

    }


    //退出浓度表某个单元格的编辑状态时，将符合条件的浓度写入数据库，不符合的给出提示，不保存
    private void updateDB_StopEdit(String msg) {
        int nowRow = tblConcentration.getSelectedRow();
        DefaultTableModel currentDM = (DefaultTableModel) tblConcentration.getModel();
        String concentrationID = tblConcentration.getValueAt(nowRow, currentDM.findColumn("浓度ID")).toString();
        String concentrationValue = tblConcentration.getValueAt(nowRow, currentDM.findColumn("浓度值")).toString();
        String sqlUpdate = "UPDATE concentration SET concentrationValue = ? WHERE concentrationID = ? ";
        String[] param = {concentrationValue, concentrationID};
        db.pstmtUpdateAndCommit(sqlUpdate, param);
    }



}
