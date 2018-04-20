package com.dhyx.dbclass;

import com.ibatis.RuntimeSqlException;
import com.ibatis.ScriptRunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.sql.*;


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


    /**查找是否存在给定条件的记录，返回true或false
     * @param SQL 要执行的sql
     * @param param sql中的字段实际值
     * @return 是或否
     */
    public boolean isExistByCount(String SQL, String... param) {
        boolean isExist;
        int count = 1;
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            for (int i = 0; i < param.length; i++) {
                pstmt.setString(i + 1, param[i]);
            }
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            count = rs.getInt(1);
        } catch (Exception e) {
            logger.error(e.getClass().getSimpleName() + "，" + e.getMessage());
        }

        if (count == 0) {
            isExist = false;
        } else {
            isExist = true;
        }
        return isExist;
    }   // END : private boolean isExistByCount()



    public boolean pstmtUpdateAndCommit(String sql, String... param) {
        try {
            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            for (int i = 0; i < param.length; i++) {
                pstmt.setString(i + 1, param[i]);
            }
            pstmt.executeUpdate();
            // 更新完毕
//            JOptionPane.showMessageDialog(null, "操作成功!");
            conn.commit();
            return true;
        } catch (Exception e) {
            try {
                conn.rollback();
                conn.commit();
            } catch (Exception e1) {
                logger.error(e1.getClass().getSimpleName() + "，回滚失败。" + e.getMessage());
            }

            logger.error(e.getClass().getSimpleName() + "，" + e.getMessage());
            JOptionPane.showMessageDialog(null, "操作失败，未能更新。请查看日志。\n" + e.getMessage());
            return false;
        } // END : try
    }   // END : private boolean dbUpdate()


    /**不提交，并获得插入后的键
     * @param sql 要执行的SQL，
     * @param param sql中字段的实际值
     * @return 自动生成的键
     * @throws SQLException
     */
    public String pstmtUpdateNotCommit(boolean isInsert, String sql, String... param) throws SQLException {
        conn.setAutoCommit(false);
        PreparedStatement pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        for (int i = 0; i < param.length; i++) {
            pstmt.setString(i+1, param[i]);
        }
        int a = pstmt.executeUpdate();
        if (isInsert) {
            ResultSet rs = pstmt.getGeneratedKeys();
            rs.next();
            return rs.getString(1);
        }
        return "";
        // 更新完毕

    }   // END : private void pstmtUpdateNotCommit()



}   // END: public class MyDatabase




