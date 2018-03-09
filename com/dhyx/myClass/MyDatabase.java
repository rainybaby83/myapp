package com.dhyx.myClass;

import org.apache.commons.lang3.math.NumberUtils;
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
    public boolean IsExistRecord(String  selectSQL, String... field){

        boolean isExsit ;
        int count = 1;
        try {
            PreparedStatement pstmt = conn.prepareStatement(selectSQL);
            for (int i = 0; i < field.length; i++) {
                pstmt.setString(i + 1, field[i]);
            }
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            count = NumberUtils.toInt(rs.getString(1));
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


    //创建普通的表格Model
    public DefaultTableModel getTableModel(String sql) {
        int i;
        DefaultTableModel defaultTableModel = null;
        ResultSet rs;
        Vector<Vector> vector = new Vector<>();
        Vector<String> vectorHead = new Vector<>();
        try {
            rs = stmt.executeQuery(sql);
            int count = rs.getMetaData().getColumnCount();
            for (i = 1; i <= count; i++) {
                vectorHead.add(rs.getMetaData().getColumnName(i));
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
    public DefaultTableModel getTableModelWithCheckbox(String sql) {
        int i, j;
        DefaultTableModel dm = null;
        ResultSet rs;
        Vector<Vector> vector = new Vector<>();
        Vector<Object> vectorHead = new Vector<>();
        try {
            // 1 构造表头
            rs = stmt.executeQuery(sql);
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
                    vectorRow.add(rs.getString(i));
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
    public DefaultTableModel getTableModelForTestData(String...testID) {

        DefaultTableModel defaultTableModel ;
        ResultSet rs;
        Vector<Vector> vector = new Vector<>();
        Vector<String> vectorHeader = new Vector<>();
        String sql;
        StringBuffer str = new StringBuffer();
        int x,y;
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
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                x = rs.getInt("x");
                header.add(x);
                vectorHeader.add(String.valueOf(x));
            }
            rs.close();

            //循环2   同一个testID的不同Y数据
            try {
                x=0;
                //按照多选的testID个数，进行for循环
                for (String aTestID : testID) {
                    sql = "SELECT DISTINCT x,y FROM test_original WHERE testID = " + aTestID + " AND isDeleted = 'N' ORDER BY x";

                    //必须新建Vector，不能用clear()方法，否则会影响已添加到vector的数据
                    Vector<Object> vectorRow = new Vector<>();
                    vectorRow.add(aTestID);
                    rs = stmt.executeQuery(sql);

                    if (rs.next())  {
                        x = rs.getInt("x");
                        for (int aHeader : header) {
                            if (aHeader < x || aHeader > x ) {
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
                            }
                        }
                        vector.addElement(vectorRow);
                    }
                    rs.close();



                }
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

        // 2 构造内容
        //循环1  不同testID

        defaultTableModel = new DefaultTableModel(vector, vectorHeader);
        return defaultTableModel;
    }


}   // END: public class MyDatabase




