package com.dhyx.myclass;

import com.dhyx.MainApp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class MyComboBox extends JComboBox<String> {

    public LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
    private Logger logger = LogManager.getLogger();
    private Statement stmt = MainApp.myDB.stmt;


    public MyComboBox() {
        super();
        DefaultListCellRenderer dlcr = new DefaultListCellRenderer();
        dlcr.setHorizontalAlignment(JLabel.CENTER);
        setRenderer(dlcr);
        setOpaque(false);
    }


    //根据SQL取得查询结果，遍历后添加到键值对数组
    public void setKeyValueBySQL(String selectSQL) {
        ResultSet rs;
        String key,value;
        try {
            rs = stmt.executeQuery(selectSQL);
            while (rs.next()) {
                key = rs.getString(1);
                value = rs.getString(2);
                linkedHashMap.put(key, value);
                this.addItem(value);
            } ;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "下拉框初始化失败，请截图后联系开发人员。\n"
                    + e.getClass().getSimpleName()+"\n"+e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            logger.error(e.getClass().getSimpleName() + "，" + e.getMessage());
        }
        this.setSelectedIndex(-1);
    }   // END: public void setKeyValueBySQL(String selectSQL)


    public void setKeyValue(String[] key,String[] value) {
        int length = Math.min(key.length, value.length);
        for (int i = 0; i < length; i++) {
            linkedHashMap.put(key[i], value[i]);
            this.addItem(value[i]);
        }
    }


    public String getSelectedKey() {
        String selectValue = Objects.requireNonNull(super.getSelectedItem()).toString();
        String selectKey = "-1";
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            if (selectValue.equals(entry.getValue().toString())) {
                selectKey= entry.getKey().toString();
                break;
            }
        }
        return selectKey;
    }


    /**遍历JComboBox的Item，取到对应的Index，然后选中它，并break。
     *  此处不用考虑value在Item不存在的情况，selectValue和Item都是数据库id的对应数据。
     */
    public void setSelectedIndexFromValue(String selectValue) {
            for (int i = 0; i < super.getItemCount(); i++) {
                if( selectValue.equals(super.getItemAt(i))){
                    this.setSelectedIndex(i);
                    break;
                }
            }
    }   // END: public void setSelectedIndexFromValue(String value)

}
