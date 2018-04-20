package com.dhyx.myclass;

import com.dhyx.MainApp;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author R2
 */

public class TableMethod {
    private static Connection conn = MainApp.myDB.conn;
    private static Logger logger = LogManager.getLogger();


    //创建普通的表格Model
    public static DefaultTableModel getTableModel(String sql) {
        int i;
        DefaultTableModel defaultTableModel = null;
        ResultSet rs;
        Vector<Vector> vector = new Vector<>();
        Vector<String> vectorHead = new Vector<>();
        try {
            rs = conn.createStatement().executeQuery(sql);
            int count = rs.getMetaData().getColumnCount();
            for (i = 1; i <= count; i++) {
                vectorHead.add(rs.getMetaData().getColumnLabel(i));
            }
            while (rs.next()) {
                Vector<Object> vectorRow = new Vector<>();
                for (i = 1; i <= count; i++) {
                    vectorRow.add(rs.getString(i));
                }
                vector.addElement(vectorRow);
            }
            defaultTableModel = new DefaultTableModel(vector, vectorHead);
            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "数据获取失败，请截图后联系开发人员。\n"
                    + e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);

            logger.error(e.getClass().getSimpleName() + "，" + e.getMessage());
        }
        // DefaultTableModel若为null，则会抛异常“IllegalArgumentExceptionn: Cannot set a null TableModel”。
        return defaultTableModel;
    }   // END:  public DefaultTableModel getTableModel(String sql)


    //创建带有复选框的表格Model
    public static DefaultTableModel getDMwithCheck(String sql) {
        int i, j;
        DefaultTableModel dm = null;
        ResultSet rs;
        Vector<Vector> vector = new Vector<>();
        Vector<Object> vectorHead = new Vector<>();
        try {
            // 1 构造表头
            rs = conn.createStatement().executeQuery(sql);
            int count = rs.getMetaData().getColumnCount();
            vectorHead.add("□");
            for (j = 1; j <= count; j++) {
                vectorHead.add(rs.getMetaData().getColumnName(j));
            }

            // 2 构造数据
            while (rs.next()) {
                Vector<Object> vectorRow = new Vector<>();
                vectorRow.add(Boolean.FALSE);
                for (i = 1; i <= count; i++) {
                    if (rs.getString(i) == null) {
                        vectorRow.add("");
                    } else {
                        vectorRow.add(rs.getString(i));
                    }

                }
                vector.addElement(vectorRow);
            }
            dm = new DefaultTableModel(vector, vectorHead);
            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "数据获取失败，请截图后联系开发人员。\n"
                    + e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            logger.error(e.getClass().getSimpleName() + "，" + e.getMessage());
        }
        return dm;
    }   // END:  public DefaultTableModel getTableModel(String sql)


    //针对TestData表格的getTableModel方法
    public static DefaultTableModel getDMforTestData(String... testID) {

        DefaultTableModel defaultTableModel;
        ResultSet rs;
        Vector<Vector> vector = new Vector<>();
        Vector<String> vectorHeader = new Vector<>();
        String sql;
        StringBuffer str = new StringBuffer();
        int x, y;
        ArrayList<Integer> header = new ArrayList<>();

        // 1 构造表头
        vectorHeader.add("测试ID");

        // 1.1 构造SQL的参数WHERE testID IN xxx
        for (String aTestID : testID) {
            str = str.append(aTestID).append(",");
        }
        str.deleteCharAt(str.length() - 1);


        // 1.2 获取这些testID对应的所有X，并构造到vectorHeader中
        sql = "SELECT DISTINCT x FROM test_original WHERE testID in (" + str + ") AND isDeleted = 'N' ORDER BY x ";

        try {
            rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                x = rs.getInt("x");
                header.add(x);
                vectorHeader.add(String.valueOf(x));
            }
            rs.close();

            //循环2   同一个testID的不同Y数据
            try {
                x = 0;
                //按照多选的testID个数，进行for循环
                for (String aTestID : testID) {
                    sql = "SELECT DISTINCT x,y FROM test_original WHERE testID = " + aTestID + " AND isDeleted = 'N' ORDER BY x";

                    //必须新建Vector，不能用clear()方法，否则会影响已添加到vector的数据
                    Vector<Object> vectorRow = new Vector<>();
                    vectorRow.add(aTestID);
                    rs = conn.createStatement().executeQuery(sql);

                    if (rs.next()) {
                        x = rs.getInt("x");
                        for (int aHeader : header) {
                            if (aHeader < x || aHeader > x) {
                                //如果某条testID数据的X小于header，则填充为0
                                vectorRow.add(0);
                            } else if (aHeader == x) {
                                //如果X等于header，则把Y添加到vectorRow
                                y = rs.getInt("y");
                                vectorRow.add(y);

                                //如果没有下一条数据，则X不再变动；否则next()之后取一次X
                                if (rs.next()) {
                                    x = rs.getInt("x");
                                }
                            }   // END : if...else if
                        }   // END : for (int aHeader : header)
                        vector.addElement(vectorRow);
                    }   // END : if (rs.next())
                    rs.close();
                }   // END : for
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "创建表格内容失败，请截图后联系开发人员。\n"
                        + e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
                logger.error(e.getClass().getSimpleName() + "，" + e.getMessage());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "创建表头失败，请截图后联系开发人员。\n"
                    + e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            logger.error(e.getClass().getSimpleName() + "，" + e.getMessage());
        }
        defaultTableModel = new DefaultTableModel(vector, vectorHeader);
        return defaultTableModel;
    }


}
