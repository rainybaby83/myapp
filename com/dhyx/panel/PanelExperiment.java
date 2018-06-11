package com.dhyx.panel;

import com.dhyx.myclass.MyDatabase;
import com.dhyx.myclass.*;
import com.dhyx.MainApp;
import org.apache.commons.lang3.StringUtils;
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
public class PanelExperiment extends JPanel {
    private Logger logger = LogManager.getLogger();
    private PanelQuery panelQuery;
    private JLabel lblProject, lblExperiment;
    private MyTable tblProject, tblExperiment;
    private DefaultTableModel dmProject, dmExperiment;
    private MyIconButton btnQuery, btnNew, btnDel;
    private String sqlSelectPro = "SELECT DISTINCT 项目ID,创建日期,修改日期,项目名称 FROM view_project ";
    private String sqlSelectExp = "SELECT DISTINCT 项目名称,实验创建日期,实验名称,实验ID FROM view_pro_exp ";
    private String projectID, projectName, experimentID, experimentName;
    private MyDatabase db = MainApp.myDB;


    public PanelExperiment() {
        initSelf();
        initQueryPanel();
        initLabel();
        initButton();
        initTable();
        clickBtnQuery();
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
        panelQuery.txtQuery.setToolTipText("请输入项目名称。支持模糊查询，不区分大小写");
        panelQuery.txtQuery.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                //按回车键执行相应操作;
                if(e.getKeyChar()==KeyEvent.VK_ENTER )
                {
                    clickBtnQuery();
                }
            }
        });

        btnQuery = panelQuery.btnQuery;
        btnQuery.setBounds(390, 0, Const.BUTTON_WIDTH, Const.BUTTON_HEIGHT);
        this.add(panelQuery);
    }


    //初始化2个标签
    private void initLabel() {
        lblProject = new JLabel("项目列表");
        lblProject.setBounds(0, panelQuery.getY() + panelQuery.getHeight(), 345, 25);

        lblExperiment = new JLabel("实验列表");
        lblExperiment.setBounds(btnQuery.getX(), panelQuery.getY() + panelQuery.getHeight(), 450, 25);

        this.add(lblProject);
        this.add(lblExperiment);
    }


    //初始化按钮
    private void initButton() {
        //查询按钮
        btnQuery.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //组件状态可用、并且左键点击，才可以执行代码
                if ((btnQuery.isEnabled()) && (e.getButton() == MouseEvent.BUTTON1)) {
                    logger.trace("点击按钮：实验管理-查询。" + panelQuery.txtQuery.getText());
                    clickBtnQuery();
                }
            }
        });

        //新建按钮
        btnNew = new MyIconButton(Const.ICON_NEW_EXPERIMENT, Const.ICON_NEW_EXPERIMENT_ENABLED, Const.ICON_NEW_EXPERIMENT_DISABLED);
        btnNew.setLocation(this.getWidth() - Const.BUTTON_WIDTH, lblProject.getY() + lblProject.getHeight());
        btnNew.setEnabled(true);
        btnNew.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //组件状态可用、并且左键点击，才可以执行代码
                if ((btnNew.isEnabled()) && (e.getButton() == MouseEvent.BUTTON1)) {
                    logger.trace("点击按钮：实验管理-新建实验");
                    clickBtnNew();
                    clickBtnQuery();
                }
            }
        });


        //删除按钮
        btnDel = new MyIconButton(Const.ICON_DEL, Const.ICON_DEL_ENABLED, Const.ICON_DEL_DISABLED);
        btnDel.setLocation(btnNew.getX(), btnNew.getY() + btnNew.getHeight() + 30);
        btnDel.setEnabled(false);
        btnDel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if ((btnDel.isEnabled()) && (e.getButton() == MouseEvent.BUTTON1)) {
                    boolean status = clickBtnDel();
                    logger.trace("点击按钮：实验管理-删除。实验ID=" + experimentID + "，结果=" + status);
                    clickTblProject();
                }
            }
        });


        //添加到面板
        this.add(btnNew);
        this.add(btnDel);
    } // END:initButton()


    //初始化2个表格，及滚动条
    private void initTable() {
        initTableGetModel();
        initTableCreateTable();
        initTableAddListener();
    }  // END:initTable()


    // 1. 创建TableModel
    private void initTableGetModel() {
        //从全局变量Const.myDB获得DefaultTableModel，用于创建table
        dmProject = TableMethod.getTableModel(sqlSelectPro + " LIMIT 50");
        dmExperiment = TableMethod.getTableModel(sqlSelectExp + "WHERE `实验ID` is not null LIMIT 50");
    }

    // 2.初始化表格及滚动面板
    private void initTableCreateTable() {

        tblProject = new MyTable(dmProject);
        tblExperiment = new MyTable(dmExperiment) ;

        tblProject.setWidth(65, 70, 70, 140 - 2);
        tblExperiment.setWidth(100, 100, 180, 70 - 2);

        tblProject.setBounds(0, lblProject.getY() + lblProject.getHeight(), 345, 545);
        tblExperiment.setBounds(lblExperiment.getX(), lblExperiment.getY() + lblExperiment.getHeight(), 450, 545);
        tblProject.j.setBounds(tblProject.getBounds());
        tblExperiment.j.setBounds(tblExperiment.getBounds());
        this.add(tblProject.j);
        this.add(tblExperiment.j);
//        tblProject.jPopupMenu.add(btnNew);
    }   // END : private void initTableCreateTable()


    // 3.添加监听
    private void initTableAddListener() {

        tblProject.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    clickTblProject();
                }
            }
        });

        tblExperiment.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    clickTblExperiment();
                }
            }
        });
    }   // END : private void initTableAddListener()




    //查询按钮
    private void clickBtnQuery() {
        btnQuery.setEnabled(false);
        //处理SQL，避免注入。例如   C%' or 1=1 or 项目ID like '%C
        String sql,sql2;
        String strQueryKey = panelQuery.txtQuery.getText().trim();
        strQueryKey = StringUtils.replace(strQueryKey, "'", "");
        strQueryKey = StringUtils.replace(strQueryKey, "%", "");
        sql = sqlSelectPro + " WHERE `项目名称` LIKE '%" + strQueryKey + "%' ORDER BY `项目ID`";

        dmProject = TableMethod.getTableModel(sql);
        tblProject.setModel(dmProject);


        //后续处理
        tblProject.setLastRow();
        clickTblProject();
        panelQuery.txtQuery.requestFocus();
        btnQuery.setEnabled(true);
    }   // END : private void clickBtnQuery()


    //新建实验按钮
    private void clickBtnNew() {
        String createDate = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
        String defautlName = DateFormatUtils.format(new Date(), "yyyyMMdd");
        experimentName = JOptionPane.showInputDialog(null, "当前项目为" + projectID + "." + projectName + "\n请输入新的实验名称：", defautlName);
        experimentName = StringUtils.replace(experimentName, "'", "");
        experimentName = StringUtils.replace(experimentName, "%", "");

        if (StringUtils.isNotEmpty(experimentName)) {
            String insertExperiment = "INSERT INTO `experiment` (`isDeleted`,`createDate`,`modifyDate`,`projectID`,`experimentName`) " +
                    "VALUES ( 'N' , ? , ? , ? , ?);";
            String[] paramsExperiment = {createDate,createDate,projectID,experimentName};
            db.pstmtUpdateAndCommit(insertExperiment, paramsExperiment);
        } else {
            JOptionPane.showMessageDialog(null, "未输入实验名称，停止创建。");
        }
    }   // END : private void clickBtnNew()


    //删除按钮
    private boolean clickBtnDel() {
        boolean status = false;
        String sql = "SELECT COUNT(*) FROM `view_project_exp_curve` WHERE `实验ID` = ?";
        if (db.isExistByCount(sql, experimentID)) {
            //不允许删除则给出提示。
            JOptionPane.showMessageDialog(null, "该项目已有测试数据，不能删除，否则会导致数据混乱。",
                    "不能删除", JOptionPane.WARNING_MESSAGE);
        } // END : if (db.isExistByCount(sql, experimentID))
        else {
            //允许删除则再次确认
            int answer = JOptionPane.showConfirmDialog(null, "即将删除实验“" + experimentName + "”，请再次确认！",
                    "警告", JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
            if (answer == JOptionPane.OK_OPTION) {
                //删除数据，更新experiment
                String updateExperiment = "UPDATE `experiment` SET `isDeleted`='Y' WHERE `experimentID` = ? AND `isDeleted` = 'N';";
                status = db.pstmtUpdateAndCommit(updateExperiment, experimentID);
            }   // END : if (answer == JOptionPane.OK_OPTION)
        }
        return status;
    }   // END : private void clickBtnDel()


    //点击左侧项目列表，自动查找右侧的实验数据。
    private void clickTblProject() {
        if (tblProject.getRowCount() > 0) {
            int nowRow = tblProject.getSelectedRow();
            String sql;
            if (nowRow != -1) {
                //项目ID，projectID
                DefaultTableModel currentDM = (DefaultTableModel) tblProject.getModel();
                projectID = tblProject.getValueAt(nowRow, currentDM.findColumn("项目ID")).toString();
                projectName = tblProject.getValueAt(nowRow, currentDM.findColumn("项目名称")).toString();
                sql = sqlSelectExp + "WHERE `项目ID` = " + projectID +" AND `实验ID` IS NOT NULL";

                dmExperiment = TableMethod.getTableModel(sql);
                tblExperiment.setModel(dmExperiment);
                tblExperiment.setLastRow();
            }
        }else {
            // 查不到实验数据时，清空曲线列表
            dmExperiment = TableMethod.getTableModel(sqlSelectExp + " WHERE 0=1");
            tblExperiment.setModel(dmExperiment);
        }
        clickTblExperiment();
    }   // END : private void clickTblProject()


    //点击实验列表，设置删除键是否可用
    private void clickTblExperiment() {
        if (tblExperiment.getRowCount() > 0) {
            int nowRow = tblExperiment.getSelectedRow();
            DefaultTableModel currentDM = (DefaultTableModel) tblExperiment.getModel();
            experimentID = tblExperiment.getValueAt(nowRow, currentDM.findColumn("实验ID")).toString();
            experimentName = tblExperiment.getValueAt(nowRow, currentDM.findColumn("实验名称")).toString();
            btnDel.setEnabled(true);
        } else {
            btnDel.setEnabled(false);
        }
    }   // END : private  void clickTblExperiment()


}
