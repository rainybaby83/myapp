package com.dhyx.dbclass;

import com.dhyx.MainApp;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ProjectClass {
    public String projectID, peakTypeID, tcTypeID;
    public String createDate, modifyDate;
    public String projectName, prepareDuration, tcExpession, peakName;
    public String subUnit,subMin, subMax;
    public int x1Left, x2Left, x1Right, x2Right , x1N, x2N;
    private static Connection conn = MainApp.myDB.conn;
    private static Logger logger = LogManager.getLogger();

    public ProjectClass() {
        projectID = "";
        createDate = "";
        modifyDate = "";
        projectName = "";
        prepareDuration = "";
        tcTypeID = "";
        tcExpession = "";
        peakTypeID = "";
        peakName = "";
        subUnit = "";
        subMin = "";
        subMax = "";
        x1Left = 0;
        x2Left = 0;
        x1Right = 0;
        x2Right = 0;
        x1N = 0;
        x2N = 0;
    }

    public void setData(String projectID) {
        if (NumberUtils.toInt(projectID) == 0) {
            JOptionPane.showMessageDialog(null, "项目ID无效。");
        } else {
            this.projectID = projectID;
            try {
                String sql = "SELECT * FROM view_project WHERE 项目ID = " + projectID;
                ResultSet rs = conn.createStatement().executeQuery(sql);
                if (rs.next()) {
                    this.createDate = rs.getString("创建日期");
                    this.modifyDate = rs.getString("修改日期");
                    this.projectName = rs.getString("项目名称");
                    this.prepareDuration = rs.getString("准备时长");
                    this.tcTypeID = rs.getString("TC公式ID");
                    this.tcExpession = rs.getString("TC公式");
                    this.peakTypeID = rs.getString("取峰算法ID");
                    this.peakName = rs.getString("取峰算法");
                    this.subUnit = rs.getString("浓度单位");
                    this.subMin = rs.getString("最小值");
                    this.subMax = rs.getString("最大值");
                    this.x1Left = rs.getInt("X1左边界");
                    this.x1Right = rs.getInt("X1右边界");
                    this.x1N = rs.getInt("X1取数N");
                    this.x2Left = rs.getInt("X2左边界");
                    this.x2Right = rs.getInt("X2右边界");
                    this.x2N = rs.getInt("X2取数N");
                    rs.close();
                }
            } catch (SQLException e) {
                logger.error(e.getClass().getSimpleName() + "，" + e.getMessage());
            }
        }


    }


}
