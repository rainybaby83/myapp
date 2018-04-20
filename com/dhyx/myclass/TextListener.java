package com.dhyx.myclass;

import org.apache.commons.lang3.math.NumberUtils;

import javax.swing.*;
import java.awt.event.*;

public class TextListener {

    /**
     *  针对所有文本框去空格
     */
    public static FocusListener trimFocusListener() {
        return new FocusAdapter() {
            JTextField jTextField;
            @Override
            public void focusLost(FocusEvent e) {
                jTextField = (JTextField) e.getSource();
                jTextField.setText(jTextField.getText().trim());
            }
        };
    }



    /**
     *  文本框只允许输入整数的FocusListener
     */
    public static FocusListener integerTextFocusListener() {
        return new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {

                JTextField jTextField = (JTextField) e.getSource();
                int inputInt = NumberUtils.toInt(jTextField.getText(),0);
                //负数取绝对值
                if (inputInt > 0) {
                    jTextField.setText(String.valueOf(inputInt));
                }else {
                    //输入负数则取绝对值
                    jTextField.setText(String.valueOf(0 - inputInt));
                }
            }   // END : public void focusLost()
        };  // END : return new FocusListener()
    }   // END : private FocusListener integerTextFocusListener()




    /**
     * 文本框只允许输入小数FocusListener
     */
    public static FocusListener floatTextFocusListener() {
        return new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                JTextField jTextField = (JTextField) e.getSource();
                float inputInt = NumberUtils.toFloat(jTextField.getText(),0.000F);
                //负数取绝对值
                if (inputInt > 0) {
                    jTextField.setText(String.valueOf(inputInt));
                }else {
                    jTextField.setText(String.valueOf(0 - inputInt));
                }
                JOptionPane.showMessageDialog(null,123);
            }   // END : public void focusLost()
        };  // END : return new FocusListener()
    }   // END : private FocusListener floatTextFocusListener()



    /**
     *  文本框只允许规定的字符数
     *  @param count 限制输入的字符数量
     */
    public static KeyListener countTextCharListener(int count) {
        return new KeyAdapter() {
            JTextField jTextField;
            @Override
            public void keyTyped(KeyEvent e) {
                jTextField = (JTextField) e.getSource();
                if (jTextField.getText().length() >= count) {
                    jTextField.setText("");
                    e.consume();
                }
            }
        };
    }

}
