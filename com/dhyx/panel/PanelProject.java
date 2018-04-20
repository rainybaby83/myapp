package com.dhyx.panel;

import com.dhyx.dbclass.MyDatabase;
import com.dhyx.dbclass.ProjectClass;
import com.dhyx.myclass.*;
import com.dhyx.MainApp;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;

/**
 * @author zhangyu
 * 2018.3.9
 */

public class PanelProject extends JPanel {
    private Logger logger = LogManager.getLogger();
    //查询面板
    private PanelQuery panelQuery;

    //下部的面板
    private JPanel subPanel;
    private JLabel lblProject;
    private JTextField[] text = new JTextField[14];
    private JLabel[] label = new JLabel[14];
    private MyTable tblProject;
    private MyIconButton btnQuery, btnNew, btnEdit, btnSave, btnDel;
    private MyComboBox lstPeak, lstTC;
    private String sqlSelectProject;
    private DefaultTableModel dm;
    private int saveState = Const.SAVE_STATE_CANCEL;
    private MyDatabase db = MainApp.myDB;
    private ProjectClass p = new ProjectClass();
    private int standardY;


    public PanelProject() {
        initSelf();
        initQueryPanel();
        initLabel();
        initButton();
        initTable();
        initSubPanel();
        click_btnQuery();
        click_tblProject();
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
        panelQuery.lblQuery.setText("项目关键字");
        panelQuery.txtQuery.setSize(275, Const.BUTTON_HEIGHT);
        panelQuery.txtQuery.setToolTipText("请输入项目名称关键字。支持模糊查询，不区分大小写");
        panelQuery.txtQuery.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                //按回车键执行相应操作;
                if(e.getKeyChar()==KeyEvent.VK_ENTER )
                {
                    click_btnQuery();
                }
            }
        });

        btnQuery = panelQuery.btnQuery;
        btnQuery.setBounds(390, 0, Const.BUTTON_WIDTH, Const.BUTTON_HEIGHT);
        this.add(panelQuery);
    }


    //初始化标签
    private void initLabel() {
        lblProject = new JLabel("项目列表");
        lblProject.setBounds(0, panelQuery.getY() + panelQuery.getHeight(), 850, 25);
        this.add(lblProject);
        standardY = lblProject.getY() + lblProject.getHeight();
    }


    //初始化表格及滚动面板
    private void initTable() {
        // 1.创建TableModel
        sqlSelectProject = "SELECT 项目ID,创建日期,修改日期,项目名称,准备时长,取峰算法,TC公式,浓度单位,最小值,最大值," +
                "X1左边界,X1右边界,X1取数N,X2左边界,X2右边界,X2取数N FROM view_project ";
        dm = TableMethod.getTableModel(sqlSelectProject);

        // 2.用TableModel创建表格，并设置尺寸、位置
        tblProject = new MyTable(dm);
        tblProject.setWidth(60, 80, 80, 130, 80, 110, 110, 70, 70, 70, 70, 70, 70, 70, 70, 70);
        tblProject.setBounds(0, standardY, 840, 325);

        // 3.设置滚动面板尺寸，并添加到窗口面板
        tblProject.jScrollPane.setBounds(tblProject.getBounds());
        this.add(tblProject.jScrollPane);

        //4.添加弹出菜单
//        tblProject.jPopupMenu.add(btnEdit);
//        tblProject.jPopupMenu.add(btnDel);

        //5.添加监听，单击时选中某行，在下方显示明细
        tblProject.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    click_tblProject();
                }
            }
        });
    }  // END:initTalbe()


    //初始化按钮
    private void initButton() {
        //查询按钮
        btnQuery.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //组件状态可用、并且左键点击，才可以执行代码
                if ((btnQuery.isEnabled()) && (e.getButton() == MouseEvent.BUTTON1)) {
                    logger.trace("点击按钮：项目管理-查询" + panelQuery.txtQuery.getText());
                    click_btnQuery();
                }
            }
        });

        //新建按钮
        btnNew = new MyIconButton(Const.ICON_NEW, Const.ICON_NEW_ENABLED, Const.ICON_NEW_DISABLED);
        btnNew.setLocation(this.getWidth() - Const.BUTTON_WIDTH, standardY);
        btnNew.setEnabled(true);
        btnNew.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //组件状态可用、并且左键点击，才可以执行代码
                if ((btnNew.isEnabled()) && (e.getButton() == MouseEvent.BUTTON1)) {
                    logger.trace("点击按钮：项目管理-新建");
                    click_btnNew();
                }
            }
        });


        //编辑按钮
        btnEdit = new MyIconButton(Const.ICON_EDIT, Const.ICON_EDIT_ENABLED, Const.ICON_EDIT_DISABLED);
        btnEdit.setLocation(btnNew.getX(), btnNew.getY() + btnNew.getHeight() + 30);
        btnEdit.setEnabled(false);
        btnEdit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if ((btnEdit.isEnabled()) && (e.getButton() == MouseEvent.BUTTON1)) {
                    logger.trace("点击按钮：项目管理-编辑");
                    click_btnEdit();
                }
            }
        });

        //保存按钮
        btnSave = new MyIconButton(Const.ICON_SAVE, Const.ICON_SAVE_ENABLED, Const.ICON_SAVE_DISABLED);
        btnSave.setLocation(btnNew.getX(), btnEdit.getY() + btnEdit.getHeight() + 30);
        btnSave.setEnabled(false);
        btnSave.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if ((btnSave.isEnabled()) && (e.getButton() == MouseEvent.BUTTON1)) {
                    logger.trace("点击按钮：项目管理-保存");

                    //更新或插入成功，则执行收尾工作
                    if (click_btnSave()) {
                        btnNew.setEnabled(true);
                        btnEdit.setEnabled(true);
                        btnSave.setEnabled(false);
                        resetSubPanel(true, false);
                        click_btnQuery();
                        tblProject.setLastRow();
                    }   // END 5 : if (isSuccess)
                }
            }
        });


        //删除按钮
        btnDel = new MyIconButton(Const.ICON_DEL, Const.ICON_DEL_ENABLED, Const.ICON_DEL_DISABLED);
        btnDel.setLocation(btnNew.getX(), btnSave.getY() + btnSave.getHeight() + 30);
        btnDel.setEnabled(false);
        btnDel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if ((btnDel.isEnabled()) && (e.getButton() == MouseEvent.BUTTON1)) {
                    boolean status = click_btnDel();
                    logger.trace("点击按钮：项目管理-删除。项目ID=" + p.projectID + "，结果=" + status);
                }
            }
        });




        //添加到面板
        this.add(btnNew);
        this.add(btnEdit);
        this.add(btnSave);
        this.add(btnDel);
//        this.add(btnExcel);
    } // END:initButton()


    //初始化最下方的子面板，添加组件
    private void initSubPanel() {
        //创建面板
        subPanel = new JPanel();
        subPanel.setLayout(null);
        subPanel.setOpaque(false);
        subPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder
                (1, 1, 1, 1, Color.gray), "项目明细"));
        subPanel.setBounds(-2, tblProject.getY() + tblProject.getHeight() + 30, 844, 190);

        //初始化子面板的下拉框、标签、文本框、位置
        initSubPanelList();
        initSubPanelLabel();
        initSubPanelText();
        initSubPanelPosition();

        //清空并禁用所有输入组件
        resetSubPanel(true, false);
        this.add(subPanel);

    } // END:initSubPanel()


    //为下方的子面板初始化下拉框
    private void initSubPanelList() {
        //填充下拉框
        String sqlPeak = "SELECT peakTypeID, peakName FROM peakType WHERE isDeleted = 'N' ORDER BY peakTypeID;";
        String sqlTC = "SELECT tcTypeID, tcExpession FROM tcType WHERE isDeleted = 'N' ORDER BY tcTypeID;";
        lstPeak = new MyComboBox();
        lstTC = new MyComboBox();
        lstPeak.setKeyValueBySQL(sqlPeak);
        lstTC.setKeyValueBySQL(sqlTC);
        lstPeak.setForeground(Color.blue);
        lstTC.setForeground(Color.blue);
        lstPeak.setVisible(false);
        lstTC.setVisible(false);
        subPanel.add(lstPeak);
        subPanel.add(lstTC);
    }   // END : private void initSubPanelList()


    //为子面板初始化标签
    private void initSubPanelLabel() {
        //创建标签、文本框，设置字体、位置、颜色等属性
        String[] title = {"项目ID", "项目名称", "取峰算法", "T C 公式", "准备时长", "浓度单位", "最小值", "最大值", "", "左边界", "右边界", "N", "X1", "X2"};
        for (int i = 0; i < label.length; i++) {
            if (i < label.length) {
                label[i] = new JLabel(title[i]);
                label[i].setHorizontalAlignment(JLabel.CENTER);
                subPanel.add(label[i]);
            }   // END :if (i < label.length)
        }   // END : For
    }   // END : private void initSubPanelLabel()


    //为子面板初始化文本框
    private void initSubPanelText() {
        for (int i = 0; i < text.length; i++) {

            text[i] = new JTextField();
            text[i].setHorizontalAlignment(JTextField.CENTER);
            text[i].setBorder(BorderFactory.createMatteBorder
                    (0, 0, 1, 0, new Color(180, 180, 180)));
            //此处设置背景色，但组件透明。当某些验证不通过时，使组件不透明，显示颜色
            text[i].setBackground(Color.pink);
            text[i].setOpaque(false);
            text[i].setForeground(Color.blue);

            // 按需给文本框加监听
            // 1.所有失去焦点时去空格
            text[i].addFocusListener(TextListener.trimFocusListener());

            // 2.个别文本框只能输入整数。同时限制输入位数
            int[] arrayIntNo = {4, 7, 8, 9, 10, 11, 12, 13};
            if (ArrayUtils.contains(arrayIntNo, i)) {
                text[i].addFocusListener(TextListener.integerTextFocusListener());
                text[i].setToolTipText("只能输入正整数");
                if (i == 4 || i == 7) {
                    text[i].addKeyListener(TextListener.countTextCharListener(5));
                } else {
                    text[i].addKeyListener(TextListener.countTextCharListener(3));
                }
            }

            // 3.个别文本框只能输入小数，并限制输入位数
            int[] arrayFloatNo = {6};
            if (ArrayUtils.contains(arrayFloatNo, i)) {
                text[i].setToolTipText("只能输入小数");
                text[i].addFocusListener(TextListener.floatTextFocusListener());
                text[i].addKeyListener(TextListener.countTextCharListener(5));
            }

            //添加所有文本框
            subPanel.add(text[i]);
        }   // END : for
        //第一个text[0]文本框是“项目ID”，不允许修改
        text[0].setEnabled(false);
    }   // END : private void initSubPanelText()


    //为子面板的组件确定位置
    private void initSubPanelPosition() {
        //设置组件位置，4个一列
        //项目名称、准备时长、取峰算法、TC公式
        int standardX = 30, standardY = 35;
        label[0].setBounds(standardX, standardY, 80, Const.BUTTON_HEIGHT);
        label[1].setBounds(standardX, standardY + Const.BUTTON_HEIGHT, 80, Const.BUTTON_HEIGHT);
        label[2].setBounds(standardX, standardY + 2 * Const.BUTTON_HEIGHT, 80, Const.BUTTON_HEIGHT);
        label[3].setBounds(standardX, standardY + 3 * Const.BUTTON_HEIGHT, 80, Const.BUTTON_HEIGHT);

        //文本框、数字文本框、算法下拉框、TC公式下拉框
        standardX = label[0].getX() + label[0].getWidth();
        text[0].setBounds(standardX, standardY, 130, Const.BUTTON_HEIGHT);
        text[1].setBounds(standardX, standardY + Const.BUTTON_HEIGHT, 130, Const.BUTTON_HEIGHT);
        lstPeak.setBounds(standardX, standardY + 2 * Const.BUTTON_HEIGHT + 5, 130, 25);
        text[2].setBounds(standardX, standardY + 2 * Const.BUTTON_HEIGHT, 130, Const.BUTTON_HEIGHT);
        lstTC.setBounds(standardX, standardY + 3 * Const.BUTTON_HEIGHT + 5, 130, 25);
        text[3].setBounds(standardX, standardY + 3 * Const.BUTTON_HEIGHT, 130, Const.BUTTON_HEIGHT);

        //C线位置、浓度单位、最小值、最大值
        standardX = text[0].getX() + text[0].getWidth() + 15;
        label[4].setBounds(standardX, standardY, 80, Const.BUTTON_HEIGHT);
        label[5].setBounds(standardX, standardY + Const.BUTTON_HEIGHT, 80, Const.BUTTON_HEIGHT);
        label[6].setBounds(standardX, standardY + 2 * Const.BUTTON_HEIGHT, 80, Const.BUTTON_HEIGHT);
        label[7].setBounds(standardX, standardY + 3 * Const.BUTTON_HEIGHT, 80, Const.BUTTON_HEIGHT);


        //浓度文本框、最小值文本框、最大值文本框
        standardX = label[4].getX() + label[4].getWidth();
        text[4].setBounds(standardX, standardY, 130, Const.BUTTON_HEIGHT);
        text[5].setBounds(standardX, standardY + Const.BUTTON_HEIGHT, 130, Const.BUTTON_HEIGHT);
        text[6].setBounds(standardX, standardY + 2 * Const.BUTTON_HEIGHT, 130, Const.BUTTON_HEIGHT);
        text[7].setBounds(standardX, standardY + 3 * Const.BUTTON_HEIGHT, 130, Const.BUTTON_HEIGHT);

        //空白标签、左边界、右边界、N，一共4个
        standardX = text[4].getX() + text[4].getWidth() + 50;
        label[8].setBounds(standardX, standardY, 80, Const.BUTTON_HEIGHT);
        label[9].setBounds(standardX, standardY + Const.BUTTON_HEIGHT, 80, Const.BUTTON_HEIGHT);
        label[10].setBounds(standardX, standardY + 2 * Const.BUTTON_HEIGHT, 80, Const.BUTTON_HEIGHT);
        label[11].setBounds(standardX, standardY + 3 * Const.BUTTON_HEIGHT, 80, Const.BUTTON_HEIGHT);

        // X1标签，+3个文本框
        standardX = label[8].getX() + label[8].getWidth();
        label[12].setBounds(standardX, standardY, 80, Const.BUTTON_HEIGHT);
        text[8].setBounds(standardX, standardY + Const.BUTTON_HEIGHT, 80, Const.BUTTON_HEIGHT);
        text[9].setBounds(standardX, standardY + 2 * Const.BUTTON_HEIGHT, 80, Const.BUTTON_HEIGHT);
        text[10].setBounds(standardX, standardY + 3 * Const.BUTTON_HEIGHT, 80, Const.BUTTON_HEIGHT);

        // X2标签，+3个文本框
        standardX = label[12].getX() + label[12].getWidth() + 30;
        label[13].setBounds(standardX, standardY, 80, Const.BUTTON_HEIGHT);
        text[11].setBounds(standardX, standardY + Const.BUTTON_HEIGHT, 80, Const.BUTTON_HEIGHT);
        text[12].setBounds(standardX, standardY + 2 * Const.BUTTON_HEIGHT, 80, Const.BUTTON_HEIGHT);
        text[13].setBounds(standardX, standardY + 3 * Const.BUTTON_HEIGHT, 80, Const.BUTTON_HEIGHT);
    }   // END : private void initSubPanelPosition()


    //查询按钮
    private void click_btnQuery() {
        btnQuery.setEnabled(false);
        PanelProject.this.resetSubPanel(true, false);

        //处理SQL，避免注入。例如   C%' or 1=1 or 项目ID like '%C
        String str = panelQuery.txtQuery.getText().trim();
        str = StringUtils.replace(str, "'", "");
        str = StringUtils.replace(str, "%", "");
        String sql = sqlSelectProject + "WHERE 项目名称 LIKE '%" + str + "%' ";

        dm = TableMethod.getTableModel(sql);
        tblProject.setModel(dm);
        tblProject.setLastRow();

        panelQuery.txtQuery.requestFocus();
        //btnEdit的True状态在选中表格行的单击事件里处理
        btnEdit.setEnabled(false);
        btnSave.setEnabled(false);
        btnDel.setEnabled(false);
        btnQuery.setEnabled(true);
    }   // END : private void click_btnQuery()


    //新建按钮
    private void click_btnNew() {
        //清空子面板的组件，并启用。
        resetSubPanel(true, true);
        text[0].setText("无需填写");

        //设置各按钮的状态
        btnEdit.setEnabled(false);
        btnSave.setEnabled(true);
        saveState = Const.SAVE_STATE_NEW;
    }   // END : private void click_btnNew()


    //编辑按钮
    private void click_btnEdit() {
        //锁住表格组件
        tblProject.setEnabled(false);

        //校验当前数据是否允许编辑
        String sql = "SELECT COUNT(*) FROM `view_project_exp_curve` WHERE `项目ID` = ?";
        if (db.isExistByCount(sql, p.projectID)) {
            //如果存在数据，则不允许编辑，给出提示。
            JOptionPane.showMessageDialog(null, "该项目已有测试数据，不能编辑，否则会影响数据准确性。\n" +
                    "请在“生成曲线”页面尝试删除所有曲线，再返回本页面进行编辑。\n" +
                    "如果曲线太多，建议创建新项目。", "不能编辑", JOptionPane.WARNING_MESSAGE);
        } else {
            //如果不存在数据，则允许编辑
            //启用子面板的各个控件，填充List
            resetSubPanel(false, true);
            //设置各按钮的状态
            btnNew.setEnabled(true);
            btnEdit.setEnabled(false);
            btnSave.setEnabled(true);
            saveState = Const.SAVE_STATE_EDIT;
        }
        //释放表格组件
        tblProject.setEnabled(true);
    }    // END : private  void click_btnEdit()



    /** 保存按钮
     * 1.验证所有必选项是否已填写
     * 2.获取字段数据
     * 3.校验数字的逻辑关系
     * 4.满足逻辑关系时，判断是新建，还是编辑
     */
    private boolean click_btnSave() {
        boolean result = false;
        //1.验证所有必选项是否已填写
        if (checkEmptyForClickSave()) {
            // 如果有空项，返回值为true，则不允许继续。
            JOptionPane.showMessageDialog(null, "请准确填写红色部分，不要有空项。",
                    "输入有空项", JOptionPane.ERROR_MESSAGE);
        } else {
            //2.获取字段数据

            p.projectID = text[0].getText().trim();
            p.projectName = text[1].getText().trim();
            p.peakTypeID = lstPeak.getSelectedKey();
            p.tcTypeID = lstTC.getSelectedKey();
            p.prepareDuration = text[4].getText().trim();
            p.subUnit = text[5].getText().trim();
            p.subMin = text[6].getText().trim();
            p.subMax = text[7].getText().trim();
            p.createDate = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
            p.modifyDate = p.createDate;

            p.x1Left = NumberUtils.toInt(text[8].getText());
            p.x1Right = NumberUtils.toInt(text[9].getText());
            p.x1N = NumberUtils.toInt(text[10].getText());
            p.x2Left = NumberUtils.toInt(text[11].getText());
            p.x2Right = NumberUtils.toInt(text[12].getText());
            p.x2N = NumberUtils.toInt(text[13].getText());

            //3.校验数字的逻辑关系
            boolean isAllowedSave = checkLogicForClickSave();

            //4.满足逻辑关系时，判断是新建，还是编辑
            if (isAllowedSave) {
                if (saveState == Const.SAVE_STATE_NEW) {
                    //如果是新建，则插入数据库。
                    String sqlInsertProject = "INSERT INTO `project`(isDeleted , createDate , modifyDate , projectName , prepareDuration , " +
                            "peakTypeID , tcTypeID , subUnit , subMin , subMax ,X1Left , X1Right , X1N , X2Left , X2Right , X2N ) " +
                            " VALUES ( 'N', ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
                    String[] paraProject = {p.createDate, p.modifyDate, p.projectName, p.prepareDuration, p.peakTypeID, p.tcTypeID,
                            p.subUnit, p.subMin, p.subMax,
                            String.valueOf(p.x1Left), String.valueOf(p.x1Right), String.valueOf(p.x1N),
                            String.valueOf(p.x2Left), String.valueOf(p.x2Right), String.valueOf(p.x2N)};
                    result = db.pstmtUpdateAndCommit(sqlInsertProject, paraProject);
                } else if (saveState == Const.SAVE_STATE_EDIT) {
                    //如果是编辑，按照项目ID更新数据库
                    String sqlUpdateProject = "UPDATE project SET modifyDate = ? , projectName= ? , prepareDuration = ? , " +
                            " peakTypeID = ? , tcTypeID = ? , subUnit = ? , subMin = ? , subMax = ? , " +
                            " X1Left = ? , X1Right = ? , X1N = ? , X2Left = ? , X2Right = ? , X2N = ? " +
                            " WHERE projectID =? AND isDeleted = 'N';";
                    String[] paraProject = {p.modifyDate, p.projectName, p.prepareDuration, p.peakTypeID, p.tcTypeID, p.subUnit, p.subMin, p.subMax,
                            String.valueOf(p.x1Left), String.valueOf(p.x1Right), String.valueOf(p.x1N),
                            String.valueOf(p.x2Left), String.valueOf(p.x2Right), String.valueOf(p.x2N),
                            p.projectID};
                    result = db.pstmtUpdateAndCommit(sqlUpdateProject, paraProject);
                }
            }   // END 4 : if (isAllowedSave)
        }   // END 1 : if (checkComponetReady()==false) else

        return result;
    }   // END : private void click_btnSave()


    //删除按钮
    private boolean click_btnDel() {
        boolean status = false;
        //判断是否允许删除
        String sql = "SELECT COUNT(*) FROM view_project_exp_curve WHERE 项目ID = ?";
        if (db.isExistByCount(sql, p.projectID)) {
            //如果存在数据，则不允许删除，给出提示。
            JOptionPane.showMessageDialog(null, "该项目已有测试数据，不能删除，否则会导致数据混乱。",
                    "不能删除", JOptionPane.WARNING_MESSAGE);
            return false;
        } else {
            //如果不存在数据，则允许删除。需用户二次确认
            int answer = JOptionPane.showConfirmDialog(null, "即将删除项目“" + p.projectName + "”，请再次确认！",
                    "警告", JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
            if (answer == JOptionPane.OK_OPTION) {
                //删除数据，更新projcet、experiment
                String sqlUpdateProject = "UPDATE project SET isDeleted='Y' WHERE projectID = ? AND isDeleted = 'N';";
                status = db.pstmtUpdateAndCommit(sqlUpdateProject, p.projectID);
             }
        }
        return status;
    }   // END : private void click_btnDel()


    // JTable的监听器，用于单击某行后，显示在下方
    private void click_tblProject() {
        resetSubPanel(true, false);
        int nowRow = tblProject.getSelectedRow();
        if (nowRow != -1) {

            btnEdit.setEnabled(true);
            btnSave.setEnabled(false);
            btnDel.setEnabled(true);

            //项目ID，projectID
            DefaultTableModel currentDM = (DefaultTableModel) tblProject.getModel();
            p.projectID = tblProject.getValueAt(nowRow, currentDM.findColumn("项目ID")).toString();
            p.setData(p.projectID);
            text[0].setText(p.projectID);

            // 项目名称projectName
            text[1].setText(p.projectName);
            //取峰算法 peakName
            lstPeak.setSelectedIndexFromValue(p.peakName);
            text[2].setText(p.peakName);
            //TC公式 tcExpession
            lstTC.setSelectedIndexFromValue(p.tcExpession);
            text[3].setText(p.tcExpession);
            // 准备时长 prepareDuration
            text[4].setText(p.prepareDuration);
            //浓度单位 subUnit
            text[5].setText(p.subUnit);
            //最小值 subMin
            text[6].setText(p.subMin);
            //最大值 subMax
            text[7].setText(p.subMax);
            //X1左边界
            text[8].setText(String.valueOf(p.x1Left));
            //X1右边界
            text[9].setText(String.valueOf(p.x1Right));
            //X1取数N
            text[10].setText(String.valueOf(p.x1N));
            //X2左边界
            text[11].setText(String.valueOf(p.x2Left));
            //X2右边界
            text[12].setText(String.valueOf(p.x2Right));
            //X2取数N
            text[13].setText(String.valueOf(p.x2N));
        } else {
            //未选中，清空并禁用
            btnEdit.setEnabled(false);
            btnDel.setEnabled(false);
        }   // END :if (nowRow != -1)
    }   // END : private void click_tblProject()


    /* 重置子面板 */
    private void resetSubPanel(Boolean isReset, Boolean isEnabled) {
        if (isReset) {
            for (int i = 0; i < text.length; i++) {
                text[i].setText("");
            }
            text[5].setText("ug/mL");

            //取峰算法
            lstPeak.setSelectedIndex(-1);
            lstPeak.setBackground(Color.white);
            //TC公式
            lstTC.setSelectedIndex(-1);
            lstTC.setBackground(Color.white);
        }   // END :if (isReset)

        //text[0]为projectID，不可修改，所以i从1开始循环
        for (int i = 1; i < text.length; i++) {
            text[i].setEnabled(isEnabled);
            text[i].setOpaque(false);
        }

        lstPeak.setVisible(isEnabled);
        lstTC.setVisible(isEnabled);
        text[2].setVisible(!isEnabled);
        text[3].setVisible(!isEnabled);
        this.updateUI();
    }  // END : private void resetSubPanel)


    /**
     * 检查各文本框及下拉框，不能为空值
     * @return 有空项，返回false；无空项，返回true
     */
    private boolean checkEmptyForClickSave() {
        boolean isEmpty = false;
        for (int i = 1; i < text.length; i++) {
            //跳过2个不用作编辑的文本框，不做任何处理
            if (i != 2 && i != 3) {
                if (StringUtils.isEmpty(text[i].getText().trim())) {
                    text[i].setOpaque(true);
                    isEmpty = true;
                } else {
                    text[i].setOpaque(false);
                }
            }
        }

        if (lstPeak.getSelectedIndex() == -1) {
            lstPeak.setBackground(Color.pink);
            isEmpty = true;
        } else {
            lstPeak.setBackground(Color.white);
        }

        if (lstTC.getSelectedIndex() == -1) {
            lstTC.setBackground(Color.pink);
            isEmpty = true;
        } else {
            lstTC.setBackground(Color.white);
        }
        this.updateUI();

        return isEmpty;
    }   // END : private boolean checkEmptyForClickSave()


    //校验逻辑关系
    private boolean checkLogicForClickSave() {
        /* 逻辑关系：X1左边界 ＜ X1右边界 ＜ X2左边界 ＜ X2右边界
                   取数N ≤（右边界 - 左边界）
                   最小值 < 最大值
        */
        boolean checkPosition = true;
        boolean checkN = true;
        boolean checkMaxMix = true;
        boolean isAllowedSave;

        if (p.x1Left < p.x1Right) {
            text[8].setOpaque(false);
        } else {
            checkPosition = false;
            text[8].setOpaque(true);
        }

        if (p.x1Right < p.x2Left) {
            text[9].setOpaque(false);
        } else {
            checkPosition = false;
            text[9].setOpaque(true);
        }

        if (p.x2Left < p.x2Right) {
            text[11].setOpaque(false);
        } else {
            checkPosition = false;
            text[11].setOpaque(true);
        }

        if (p.x1N <= (p.x1Right - p.x1Left) && p.x1N > 0) {
            text[10].setOpaque(false);
        } else {
            text[10].setOpaque(true);
            checkN = false;
        }

        if (p.x2N <= (p.x2Right - p.x2Left) && p.x2N > 0) {
            text[13].setOpaque(false);
        } else {
            text[13].setOpaque(true);
            checkN = false;
        }

        if (NumberUtils.toInt(p.subMax) > NumberUtils.toInt(p.subMin)) {
            //正确的情况
            text[6].setOpaque(false);
            text[7].setOpaque(false);
        } else {
            text[6].setOpaque(true);
            text[7].setOpaque(true);
            checkMaxMix = false;
        }

        StringBuffer message = new StringBuffer("红色部分有误，需满足以下逻辑关系：\n");
        if (!checkPosition) {
            message.append("● X1左边界 ＜ X1右边界 ＜ X2左边界 ＜ X2右边界\n");
        }
        if (!checkN) {
            message.append("● 取数N ≤（右边界 - 左边界），且不能为0\n");
        }
        if (!checkMaxMix) {
            message.append("● 最小值 < 最大值\n");
        }

        isAllowedSave = (checkMaxMix && checkN && checkPosition);
        if (!isAllowedSave) {
            JOptionPane.showMessageDialog(null, message, "缺少信息", JOptionPane.WARNING_MESSAGE);
        }
        return isAllowedSave;
    }   // END : private boolean checkLogicForClickSave()


}   // END class