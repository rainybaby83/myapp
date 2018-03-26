package com.dhyx.myclass;

import com.ibatis.RuntimeSqlException;
import com.ibatis.ScriptRunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.mysql.jdbc.exceptions.MySQLSyntaxErrorException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;



public class MyDatabase {
    public Connection conn;
    public Statement stmt;
    private String dbName = "cad";
    private Logger logger = LogManager.getLogger();


    public MyDatabase() {
        dbCheck();
    }


    /**
     * 检查Database的表是否存在。Database存在，运行dbStart()进行连接；Database不存在，运行dbCreate()进行创建
     */
    private void dbCheck() {
        try {
            ResultSet rs;
            //导入第三方包，并注册
            Class.forName("com.mysql.jdbc.Driver");
            String url1 = "jdbc:mysql://localhost:3306/information_schema?user=root&password=11111111&useSSL=false&useUnicode=true&characterEncoding=utf8";
            String sqlTable = "SELECT  DISTINCT `TABLE_SCHEMA` FROM `TABLES` WHERE `TABLE_SCHEMA` = '" + dbName + "'";


            conn = DriverManager.getConnection(url1);
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            try {
                //检查Database的表是否存在。 Database存在，运行dbStart()进行连接；Database不存在，运行dbCreate()进行创建
                rs = stmt.executeQuery(sqlTable);
                if (rs.next()) {
                    dbStart();
                } else {
                    dbCreateBySqlFile();
                }
                rs.close();
            } catch (MySQLSyntaxErrorException e) {
                JOptionPane.showMessageDialog(null, "SQL语法错误，程序中止。\n" + e.getMessage());
                logger.error(e.getClass().getSimpleName() + "，" + e.getMessage());
                System.exit(0);

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "查找数据表失败。\n" + e.getMessage());
                logger.error(e.getClass().getSimpleName() + "，" + e.getMessage());
                System.exit(0);
            }   // END Try: rs = stmt.executeQuery()

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "数据库初始化失败，请截图后联系开发人员。\n" + e.getMessage(),
                    "错误", JOptionPane.ERROR_MESSAGE);
            logger.error(e.getClass().getSimpleName() + "，" + e.getMessage());
            System.exit(0);
        }   // END Try: conn = DriverManager.getConnection(url)
    }   // END : private void dbCheck()


    private void dbStart() {
        try {
            //导入第三方包，并注册
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/"+dbName+"?user=root&password=11111111&useSSL=false&useUnicode=true&characterEncoding=utf8";
            conn = DriverManager.getConnection(url);
            stmt = conn.createStatement();
        } catch (Exception e) {
            logger.error(e.getClass().getSimpleName() + "，" + e.getMessage());
            JOptionPane.showMessageDialog(null, "数据库初始化失败，请截图后联系开发人员。\n"
                    + e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }   // END:  private void dbStart()


    private void dbCreateBySqlFile(){
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.changeToParentDirectory();
        jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jFileChooser.setFileFilter(new FileNameExtensionFilter("SQL脚本文件(.sql)", "sql"));
        int a = jFileChooser.showOpenDialog(null);

        if (a == JFileChooser.APPROVE_OPTION) {
            File file = jFileChooser.getSelectedFile();
            String createDatabase = "CREATE DATABASE IF NOT EXISTS " + dbName + " DEFAULT CHARSET utf8 COLLATE utf8_general_ci";
            String sqlfile = file.getPath();
            try {
                stmt.execute(createDatabase);

                String url = "jdbc:mysql://localhost:3306/" + dbName + "?user=root&password=11111111&useSSL=false&useUnicode=true&characterEncoding=utf8";
                conn = DriverManager.getConnection(url);
                Reader reader = new BufferedReader(new FileReader(sqlfile));
                ScriptRunner runner = new ScriptRunner(conn);
                runner.setSendFullScript(false);
                runner.runScript(reader);
                JOptionPane.showMessageDialog(null, "数据库创建完成。" );
                dbStart();
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, "找不到" + sqlfile);
            } catch (RuntimeSqlException e) {
                //从runner.runScript()的executeLineByLine（）抛出
                JOptionPane.showMessageDialog(null, "SQL脚本文件无效，请重新检查该文件");
                System.exit(0);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "SQL脚本错误，请重新检查SQL脚本");
                System.exit(0);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "初始化失败，请查看日志。");
                System.exit(0);
            }
        } else{
            JOptionPane.showMessageDialog(null, "未选择初始化数据库的SQL脚本，程序即将关闭。");
            System.exit(0);
        }
    }


    public void dbClose(){
        try {
            if(stmt !=null){
                stmt.close();
            }
        }catch (SQLException e) {
            logger.error(e.getClass().getSimpleName() + "，" + e.getMessage());
        }

        try {
            if(conn !=null) {
                conn.close();
            }
        } catch (SQLException e) {
            logger.error(e.getClass().getSimpleName() + "，" + e.getMessage());
        }
    }  // END: private void dbClose()


    public void dbRollback() {
        if (conn != null) {
            try {
                //数据库回滚rollback
                conn.rollback();
                conn.commit();
            } catch (Exception e) {
                logger.error(e.getClass().getSimpleName() + "，回滚失败。" + e.getMessage());
            }   // END : catch之后回滚
        }   // END : if (MainApp.myDB.conn != null)
    }


    //查找是否存在给定条件的记录，返回true或false
    public boolean isExistRecord(String selectSQL, String... field) {
        boolean isExsit;
        int count = 1;
        try {
            PreparedStatement pstmt = conn.prepareStatement(selectSQL);
            for (int i = 0; i < field.length; i++) {
                pstmt.setString(i + 1, field[i]);
            }
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            count = rs.getInt(1);
        } catch (Exception e) {
            logger.error(e.getClass().getSimpleName() + "，" + e.getMessage());
        }

        if (count == 0) {
            isExsit = false;
        } else {
            isExsit = true;
        }
        return isExsit;
    }   // END : private boolean clickEdit_CheckDB()




}   // END: public class MyDatabase




